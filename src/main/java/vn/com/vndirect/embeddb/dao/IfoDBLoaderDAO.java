/**
 * 
 */
package vn.com.vndirect.embeddb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import vn.com.vndirect.domain.embeddb.CompanyCalcView;
import vn.com.vndirect.domain.embeddb.CompanyItemCalcView;
import vn.com.vndirect.domain.embeddb.IndustryCalcView;
import vn.com.vndirect.domain.embeddb.IndustryItemCalcView;
import vn.com.vndirect.domain.embeddb.ItemCodeRef;
import vn.com.vndirect.domain.embeddb.SecPriceView;
import vn.com.vndirect.domain.embeddb.SectorCalcView;
import vn.com.vndirect.domain.embeddb.StockExchange;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

/**
 * @author Blue9Frog
 * 
 */
@SuppressWarnings("unchecked")
public class IfoDBLoaderDAO extends JdbcDaoSupport {
	private static final Log log = LogFactory.getLog(IfoDBLoaderDAO.class);

	private final RowMapper ifoStockExchangeRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			StockExchange stockExchange = new StockExchange();

			stockExchange.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			stockExchange.setSymbol(rs.getString("SYMBOL"));
			stockExchange.setExchangeCode(rs.getString("EXCHANGE_CODE"));
			stockExchange.setExchangeName(rs.getString("EXCHANGE_NAME"));

			stockExchange.setSectorGroupCode(rs.getString("SECTOR_GROUP_CODE"));
			stockExchange.setSectorCode(rs.getString("SECTOR_CODE"));
			stockExchange.setIndustryGroupCode(rs.getString("INDUSTRY_GROUP_CODE"));
			stockExchange.setIndustryCode(rs.getString("INDUSTRY_CODE"));

			stockExchange.setCompanyName(rs.getString("COMPANY_NAME"));
			stockExchange.setCompanyFullName(rs.getString("COMPANY_FULL_NAME"));
			stockExchange.setAbbName(rs.getString("ABB_NAME"));
			stockExchange.setFirstTradingDate(rs.getDate("FIRST_TRADING_DATE"));

			return stockExchange;
		}
	};

	private static final String SQL_GET_ALL_STOCK_EXCHANGE = "SELECT DISTINCT s.EXCHANGE_CODE, s.SYMBOL, s.EXCHANGE_NAME"
			+ ", com.COMPANY_ID, com.COMPANY_NAME, com.COMPANY_FULL_NAME, com.ABB_NAME, com.FIRST_TRADING_DATE" + ", i.INDUSTRY_CODE, i.INDUSTRY_GROUP_CODE, i.SECTOR_CODE, i.SECTOR_GROUP_CODE"
			+ " FROM (IFO_STOCK_EXCHANGE_VIEW s LEFT OUTER JOIN IFO_COMPANY_NAME_VIEW com  ON s.SYMBOL = com.SYMBOL)" + " LEFT OUTER JOIN IFO_COMPANY_INDUSTRY_VIEW i ON com.COMPANY_ID=i.COMPANY_ID"
			+ " ORDER BY EXCHANGE_CODE";

	/**
	 * Support for embedded DB
	 * 
	 * @return
	 * @throws SystemException
	 */
	public List<StockExchange> getAllStockExchange() throws SystemException {
		final String LOCATION = "getAllStockExchange()";
		log.debug(LOCATION + ":: BEGIN");
		try {
			SearchResult<StockExchange> result = (SearchResult) OracleDAOHelper.query(this.getDataSource(), SQL_GET_ALL_STOCK_EXCHANGE, null, ifoStockExchangeRowMapper);

			log.debug(LOCATION + ":: END - result: " + result.size());
			return result;
		} catch (SystemException syse) {
			throw new SystemException(LOCATION, syse);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
     * 
     */
	private final RowMapper ifoItemNameRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			ItemCodeRef itemCodeRef = new ItemCodeRef();

			itemCodeRef.setItemCode(rs.getString("ITEM_CODE"));
			itemCodeRef.setItemName(rs.getString("ITEM_NAME"));
			itemCodeRef.setLocale(rs.getString("LOCALE"));

			return itemCodeRef;
		}
	};

	// ////////////////////////// GET ITEM NAME ////////////////////////////////////
	private static final String SQL_GET_ALL_ITEM_CODE_4_INDUSTRY_ITEM = "SELECT DISTINCT a.ITEM_CODE, a.ITEM_NAME, a.LOCALE FROM IFO_INDUSTRY_ITEM_CALC_VIEW a";
	private static final String SQL_GET_ALL_ITEM_CODE_4_COMPANY_ITEM = "SELECT DISTINCT a.ITEM_CODE, a.ITEM_NAME, a.LOCALE FROM IFO_COMPANY_ITEM_CALC_VIEW a";

	private static final String SQL_GET_ALL_ITEM_CODE_4_SECTOR_CAL = "SELECT DISTINCT a.SECTOR_CODE as ITEM_CODE,  a.SECTOR_NAME as ITEM_NAME, a.LOCALE   FROM IFO_SECTOR_CALC_VIEW a";
	private static final String SQL_GET_ALL_ITEM_CODE_4_INDUSTRY_CAL = "SELECT DISTINCT a.INDUSTRY_CODE as ITEM_CODE, a.INDUSTRY_NAME as ITEM_NAME, LOCALE FROM IFO_INDUSTRY_CALC_VIEW a";

	/**
	 * 
	 * @return
	 * @throws SystemException
	 */
	public List<ItemCodeRef> getAllItemCodeRef4IndustryItem() throws SystemException {
		return getAllItemCodeRef(SQL_GET_ALL_ITEM_CODE_4_INDUSTRY_ITEM);
	}

	/**
	 * 
	 * @return
	 * @throws SystemException
	 */
	public List<ItemCodeRef> getAllItemCodeRef4CompanyItem() throws SystemException {
		return getAllItemCodeRef(SQL_GET_ALL_ITEM_CODE_4_COMPANY_ITEM);
	}

	/**
	 * 
	 * @return
	 * @throws SystemException
	 */
	public List<ItemCodeRef> getAllItemCodeRef4Industry() throws SystemException {
		return getAllItemCodeRef(SQL_GET_ALL_ITEM_CODE_4_INDUSTRY_CAL);
	}

	/**
	 * 
	 * @return
	 * @throws SystemException
	 */
	public List<ItemCodeRef> getAllItemCodeRef4Sector() throws SystemException {
		return getAllItemCodeRef(SQL_GET_ALL_ITEM_CODE_4_SECTOR_CAL);
	}

	/**
	 * 
	 * @param SQL
	 * @return
	 * @throws SystemException
	 */
	private List<ItemCodeRef> getAllItemCodeRef(String SQL) throws SystemException {
		final String LOCATION = "getAllItemCodeRef(SQL)";
		log.debug(LOCATION + ":: BEGIN");
		try {
			SearchResult<ItemCodeRef> result = OracleDAOHelper.query(this.getDataSource(), SQL, null, ifoItemNameRowMapper);
			log.debug(LOCATION + ":: END - result:" + result);
			return (result == null ? new ArrayList<ItemCodeRef>() : result);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	// /////////////////////////////// GET SECTOR CAL //////////////////////////////////
	private final RowMapper ifoSectorCalcViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SectorCalcView obj = new SectorCalcView();
			obj.setSectorCode(rs.getString("SECTOR_CODE"));
			obj.setPe(rs.getDouble("PE"));
			obj.setPb(rs.getDouble("PB"));
			obj.setScopeMaketCap(rs.getDouble("SCOPE_MAKET_CAP"));
			obj.setScopeAsset(rs.getDouble("SCOPE_ASSET"));
			obj.setScopeEquity(rs.getDouble("SCOPE_EQUITY"));
			obj.setGrowthAsset(rs.getDouble("GROWTH_ASSET"));
			obj.setGrowthRevenue(rs.getDouble("GROWTH_REVENUE"));
			obj.setRoa(rs.getDouble("ROA"));
			obj.setRoe(rs.getDouble("ROE"));
			obj.setProfitMargin(rs.getDouble("PROFIT_MARGIN"));
			obj.setDebtEquity(rs.getDouble("DEBT_EQUITY"));
			obj.setCurrentRatio(rs.getDouble("CURRENT_RATIO"));
			obj.setEbitda(rs.getDouble("EBITDA"));
			return obj;
		}
	};

	/**
     * 
     */
	private static final String SQL_GET_ALL_SECTORS_WITHOUT_NAME = "SELECT SECTOR_CODE, PE, PB, SCOPE_MAKET_CAP, SCOPE_ASSET, SCOPE_EQUITY, GROWTH_ASSET, GROWTH_REVENUE, ROA, ROE, PROFIT_MARGIN, DEBT_EQUITY, CURRENT_RATIO, EBITDA "
			+ " FROM IFO_SECTOR_CALC_VIEW WHERE UPPER(LOCALE)=:LOCALE";

	/**
	 * 
	 * @param locale
	 * @return
	 */
	public List<SectorCalcView> loadSectorsWithoutName(String locale) throws FunctionalException, SystemException {
		final String LOCATION = "loadSectorsWithoutName(locale: " + locale + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (locale == null) {
			throw new FunctionalException(LOCATION, "locale is NULL...");
		}
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", locale.toUpperCase());

			SearchResult<SectorCalcView> result = OracleDAOHelper.query(this.getDataSource(), SQL_GET_ALL_SECTORS_WITHOUT_NAME, paramMap, ifoSectorCalcViewRowMapper);

			log.debug(LOCATION + ":: END - " + result);
			return (result == null ? new ArrayList<SectorCalcView>() : result);
		} catch (RuntimeException re) {
			log.error(LOCATION + ":: RuntimeException " + re);
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			log.error(LOCATION + ":: Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	// /////////////////////////////// GET INDUSTRY CAL //////////////////////////////////
	private final RowMapper ifoIndustryCalcViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IndustryCalcView obj = new IndustryCalcView();
			obj.setIndustryCode(rs.getString("INDUSTRY_CODE"));
			obj.setSectorCode(rs.getString("SECTOR_CODE"));
			obj.setPe(rs.getDouble("PE"));
			obj.setPb(rs.getDouble("PB"));
			obj.setScopeMaketCap(rs.getDouble("SCOPE_MAKET_CAP"));
			obj.setScopeAsset(rs.getDouble("SCOPE_ASSET"));
			obj.setScopeEquity(rs.getDouble("SCOPE_EQUITY"));
			obj.setGrowthAsset(rs.getDouble("GROWTH_ASSET"));
			obj.setGrowthRevenue(rs.getDouble("GROWTH_REVENUE"));
			obj.setRoa(rs.getDouble("ROA"));
			obj.setRoe(rs.getDouble("ROE"));
			obj.setProfitMargin(rs.getDouble("PROFIT_MARGIN"));
			obj.setDebtEquity(rs.getDouble("DEBT_EQUITY"));
			obj.setCurrentRatio(rs.getDouble("CURRENT_RATIO"));
			obj.setEbitda(rs.getDouble("EBITDA"));
			return obj;
		}
	};

	/**
     * 
     */
	private static final String SQL_GET_ALL_INDUSTRY_WITHOUT_NAME = "SELECT SECTOR_CODE, INDUSTRY_CODE, PE, PB, SCOPE_MAKET_CAP, SCOPE_ASSET, SCOPE_EQUITY, GROWTH_ASSET, GROWTH_REVENUE, ROA, ROE, PROFIT_MARGIN, DEBT_EQUITY, CURRENT_RATIO, EBITDA "
			+ " FROM IFO_INDUSTRY_CALC_VIEW WHERE UPPER(LOCALE) = :LOCALE";

	/**
	 * 
	 * @param locale
	 * @return
	 */
	public List<IndustryCalcView> loadIndustrysWithoutName(String locale) throws FunctionalException, SystemException {
		final String LOCATION = "loadIndustrysWithoutName(locale: " + locale + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (locale == null) {
			throw new FunctionalException(LOCATION, "locale is NULL...");
		}
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", locale.toUpperCase());

			SearchResult<IndustryCalcView> result = OracleDAOHelper.query(this.getDataSource(), SQL_GET_ALL_INDUSTRY_WITHOUT_NAME, paramMap, ifoIndustryCalcViewRowMapper);

			log.debug(LOCATION + ":: END - " + result);
			return (result == null ? new ArrayList<IndustryCalcView>() : result);
		} catch (RuntimeException re) {
			log.error(LOCATION + ":: RuntimeException " + re);
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			log.error(LOCATION + ":: Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	// /////////////////////////////// GET COMPANY CAL //////////////////////////////////
	private final RowMapper ifoCompanyCalcViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			CompanyCalcView obj = new CompanyCalcView();
			obj.setExchangeCode(rs.getString("EXCHANGE_CODE"));
			obj.setSecCode(rs.getString("SEC_CODE"));
			obj.setIndustryCode(rs.getString("INDUSTRY_CODE"));
			obj.setSectorCode(rs.getString("SECTOR_CODE"));
			obj.setPe(rs.getDouble("PE"));
			obj.setPb(rs.getDouble("PB"));
			obj.setScopeMaketCap(rs.getDouble("SCOPE_MAKET_CAP"));
			obj.setScopeAsset(rs.getDouble("SCOPE_ASSET"));
			obj.setScopeEquity(rs.getDouble("SCOPE_EQUITY"));
			obj.setGrowthAsset(rs.getDouble("GROWTH_ASSET"));
			obj.setGrowthRevenue(rs.getDouble("GROWTH_REVENUE"));
			obj.setRoa(rs.getDouble("ROA"));
			obj.setRoe(rs.getDouble("ROE"));
			obj.setProfitMargin(rs.getDouble("PROFIT_MARGIN"));
			obj.setDebtEquity(rs.getDouble("DEBT_EQUITY"));
			obj.setCurrentRatio(rs.getDouble("CURRENT_RATIO"));
			obj.setEbitda(rs.getDouble("EBITDA"));
			return obj;
		}
	};

	/**
     * 
     */
	private static final String SQL_GET_ALL_COMPANY_CAL_WITHOUT_NAME = "SELECT SECTOR_CODE, INDUSTRY_CODE, EXCHANGE_CODE, SEC_CODE, PE, PB, SCOPE_MAKET_CAP, SCOPE_ASSET, SCOPE_EQUITY, GROWTH_ASSET, GROWTH_REVENUE, ROA, ROE, PROFIT_MARGIN, DEBT_EQUITY, CURRENT_RATIO, EBITDA "
			+ " FROM IFO_COMPANY_CALC_VIEW WHERE UPPER(LOCALE) = :LOCALE";

	/**
	 * 
	 * @param locale
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List<CompanyCalcView> loadCompanyCalWithoutName(String locale) throws FunctionalException, SystemException {
		final String LOCATION = "loadCompanyCalWithoutName(locale: " + locale + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (locale == null) {
			throw new FunctionalException(LOCATION, "locale is NULL...");
		}
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", locale.toUpperCase());

			SearchResult<CompanyCalcView> result = OracleDAOHelper.query(this.getDataSource(), SQL_GET_ALL_COMPANY_CAL_WITHOUT_NAME, paramMap, ifoCompanyCalcViewRowMapper);

			log.debug(LOCATION + ":: END - " + result);
			return (result == null ? new ArrayList<CompanyCalcView>() : result);
		} catch (RuntimeException re) {
			log.error(LOCATION + ":: RuntimeException " + re);
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			log.error(LOCATION + ":: Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	// ///////////////// GET ANALYSIS DATA //////////////////////////
	// private final RowMapper ifoAnalysisViewRowMapper = new RowMapper() {
	// public Object mapRow(ResultSet rs, int arg1) throws SQLException {
	// AnalysisView obj = new AnalysisView();
	// obj.setSymbol(rs.getString("SYMBOL"));
	// obj.setItemCode(rs.getString("ITEM_CODE"));
	// obj.setTextValue(rs.getString("TEXT_VALUE"));
	// obj.setNumericValue(rs.getDouble("NUMERIC_VALUE"));
	// return obj;
	// }
	// };
	//    
	// private final static String SQL_GET_ALL_ANALYSIS_CAL_WITHOUT_NAME = "SELECT b.SYMBOL, a.ITEM_CODE, a.TEXT_VALUE, a.NUMERIC_VALUE "
	// + " FROM (IFO_ANALYSIS_VIEW a INNER JOIN IFO_COMPANY_NAME_VIEW b ON a.COMPANY_ID=b.COMPANY_ID) "
	// + " WHERE UPPER(a.LOCALE)=:LOCALE ";
	//    
	// /**
	// *
	// * @param itemCodes
	// * @return
	// */
	// public List<AnalysisView> loadAnalysisCalWithoutName(String locale, List<String> itemCodes) throws SystemException {
	// final String LOCATION = "loadAnalysisCalWithoutName(locale, itemCodes)";
	// log.debug(LOCATION + ":: BEGIN");
	// try {
	// Map paramMap = new HashMap();
	// paramMap.put("LOCALE", (locale == null ? "" : locale.toUpperCase()));
	// // SearchResult result = OracleDAOHelper.query(this.getDataSource(), SQL_GET_ALL_ANALYSIS_CAL_WITHOUT_NAME, paramMap, ifoAnalysisViewRowMapper);
	//            
	// SearchResult result = null;
	//            
	// if(itemCodes != null && itemCodes.size() > 0) {
	// StringBuilder sqlBuf = new StringBuilder(SQL_GET_ALL_ANALYSIS_CAL_WITHOUT_NAME);
	// sqlBuf.append(" AND a.ITEM_CODE IN (''");
	// for (String itemCode : itemCodes) {
	// sqlBuf.append(", '").append(itemCode == null ? "" : itemCode.trim()).append("'");
	// }
	// sqlBuf.append(")");
	// result = OracleDAOHelper.query(this.getDataSource(), sqlBuf.toString(), paramMap, ifoAnalysisViewRowMapper);
	// } else {
	// result = OracleDAOHelper.query(this.getDataSource(), SQL_GET_ALL_ANALYSIS_CAL_WITHOUT_NAME, paramMap, ifoAnalysisViewRowMapper);
	// }
	//            
	// log.debug(LOCATION + ":: END - result:" + result);
	// return (result == null ? new ArrayList<AnalysisView>() : result.getResult());
	// } catch (Exception e) {
	// throw new SystemException(LOCATION, e);
	// }
	// }

	// /////////////////// GET COMPANY ITEM DATA /////////////////////////////////
	private final RowMapper ifoCompanyItemCalcViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			CompanyItemCalcView obj = new CompanyItemCalcView();
			obj.setSymbol(rs.getString("SYMBOL"));
			obj.setItemCode(rs.getString("ITEM_CODE"));
			obj.setTextValue(rs.getString("TEXT_VALUE"));
			obj.setNumericValue(rs.getDouble("NUMERIC_VALUE"));
			obj.setTransDate(rs.getTimestamp("TRANS_DATE"));

			return obj;
		}
	};

	private final static String SQL_GET_ALL_COMPANY_ITEM_CAL_WITHOUT_NAME = "SELECT b.SYMBOL, a.ITEM_CODE, a.TEXT_VALUE, a.NUMERIC_VALUE, a.TRANS_DATE "
			+ " FROM (IFO_COMPANY_ITEM_CALC_VIEW a INNER JOIN IFO_COMPANY_NAME_VIEW b ON a.COMPANY_ID=b.COMPANY_ID) " + " WHERE UPPER(a.LOCALE)=:LOCALE ";

	/**
	 * 
	 * @param itemCodes
	 * @param locale
	 * @return
	 * @throws SystemException
	 */
	public List<CompanyItemCalcView> loadCompanyItemCalWithoutName(String locale, List<String> itemCodes) throws SystemException {
		final String LOCATION = "loadCompanyItemCalWithoutName(locale, itemCodes)";
		log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", (locale == null ? "" : locale.toUpperCase()));
			SearchResult<CompanyItemCalcView> result = null;

			if (itemCodes != null && itemCodes.size() > 0) {
				StringBuilder sqlBuf = new StringBuilder(SQL_GET_ALL_COMPANY_ITEM_CAL_WITHOUT_NAME);
				sqlBuf.append(" AND a.ITEM_CODE IN (''");
				for (String itemCode : itemCodes) {
					sqlBuf.append(", '").append(itemCode == null ? "" : itemCode.trim()).append("'");
				}
				sqlBuf.append(")");
				result = OracleDAOHelper.query(this.getDataSource(), sqlBuf.toString(), paramMap, ifoCompanyItemCalcViewRowMapper);
			} else {
				result = OracleDAOHelper.query(this.getDataSource(), SQL_GET_ALL_COMPANY_ITEM_CAL_WITHOUT_NAME, paramMap, ifoCompanyItemCalcViewRowMapper);
			}
			log.debug(LOCATION + ":: END - result:" + result);
			return (result == null ? new ArrayList<CompanyItemCalcView>() : result);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	// /////////////////// GET INDUSTRT ITEM DATA /////////////////////////////////
	private final RowMapper ifoIndustryItemCalcViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IndustryItemCalcView obj = new IndustryItemCalcView();
			obj.setIndustryCode(rs.getString("INDUSTRY_CODE"));
			obj.setItemCode(rs.getString("ITEM_CODE"));
			obj.setTextValue(rs.getString("TEXT_VALUE"));
			obj.setNumericValue(rs.getDouble("NUMERIC_VALUE"));
			obj.setTransDate(rs.getTimestamp("TRANS_DATE"));

			return obj;
		}
	};

	private final static String SQL_GET_ALL_INDUSTRY_ITEM_CAL_WITHOUT_NAME = "SELECT a.INDUSTRY_CODE, a.ITEM_CODE, a.TEXT_VALUE, a.NUMERIC_VALUE, a.TRANS_DATE "
			+ " FROM IFO_INDUSTRY_ITEM_CALC_VIEW a WHERE UPPER(a.LOCALE)=:LOCALE ";

	/**
	 * 
	 * @param itemCodes
	 * @param locale
	 * @return
	 * @throws SystemException
	 */
	public List<IndustryItemCalcView> loadIndustryItemCalWithoutName(String locale, List<String> itemCodes) throws SystemException {
		final String LOCATION = "loadIndustryItemCalWithoutName(locale, itemCodes)";
		log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", (locale == null ? "" : locale.toUpperCase()));
			SearchResult<IndustryItemCalcView> result = null;

			if (itemCodes != null && itemCodes.size() > 0) {
				StringBuilder sqlBuf = new StringBuilder(SQL_GET_ALL_INDUSTRY_ITEM_CAL_WITHOUT_NAME);
				sqlBuf.append(" AND a.ITEM_CODE IN (''");
				for (String itemCode : itemCodes) {
					sqlBuf.append(", '").append(itemCode == null ? "" : itemCode.trim()).append("'");
				}
				sqlBuf.append(")");
				result = OracleDAOHelper.query(this.getDataSource(), sqlBuf.toString(), paramMap, ifoIndustryItemCalcViewRowMapper);
			} else {
				result = OracleDAOHelper.query(this.getDataSource(), SQL_GET_ALL_INDUSTRY_ITEM_CAL_WITHOUT_NAME, paramMap, ifoIndustryItemCalcViewRowMapper);
			}
			log.debug(LOCATION + ":: END - result:" + result);
			return (result == null ? new ArrayList<IndustryItemCalcView>() : result);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	// ///////////////// IFO_SEC_PRICE_VIEW ////////////////
	private final RowMapper ifoSecPriceViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SecPriceView obj = new SecPriceView();
			// obj.setCompanyId(rs.getLong("COMPANY_ID"));
			// obj.setSymbol(rs.getString("SYMBOL"));
			obj.setSymbol(rs.getString("SEC_CODE"));
			obj.setExchangeCode(rs.getString("EXCHANGE_CODE"));

			obj.setOpenPrice(rs.getDouble("OPEN_PRICE"));
			obj.setHighPrice(rs.getDouble("HIGH_PRICE"));
			obj.setLowPrice(rs.getDouble("LOW_PRICE"));
			obj.setClosePrice(rs.getDouble("CLOSE_PRICE"));
			obj.setAveragePrice(rs.getDouble("AVERAGE_PRICE"));

			obj.setVolume(rs.getLong("VOLUME"));
			obj.setTransDate(rs.getTimestamp("TRANS_DATE"));

			obj.setAdOpenPrice(rs.getDouble("AD_OPEN_PRICE"));
			obj.setAdHighPrice(rs.getDouble("AD_HIGH_PRICE"));
			obj.setAdLowPrice(rs.getDouble("AD_LOW_PRICE"));
			obj.setAdClosePrice(rs.getDouble("AD_CLOSE_PRICE"));
			obj.setAdAveragePrice(rs.getDouble("AD_AVERAGE_PRICE"));

			// obj.setRightsType(rs.getString("RIGHTS_TYPE"));
			return obj;
		}
	};

	// private final static String SQL_GET_SEC_LATEST_PRICE =
	// "SELECT A.* FROM IFO_SEC_PRICE_VIEW A WHERE TRANS_DATE = (SELECT MAX (TRANS_DATE) FROM IFO_SEC_PRICE_VIEW WHERE SYMBOL = A.SYMBOL AND TRANS_DATE < TRUNC (SYSDATE))";
	private final static String SQL_GET_SEC_LATEST_PRICE = "SELECT A.* FROM IFO_SEC_PRICE A WHERE TRANS_DATE = (SELECT MAX (TRANS_DATE) FROM IFO_SEC_PRICE WHERE SEC_CODE = A.SEC_CODE AND TRANS_DATE < TRUNC (SYSDATE))";

	/**
	 * 
	 * @param itemCodes
	 * @param locale
	 * @return
	 * @throws SystemException
	 */
	public List<SecPriceView> loadSecLatestPrice() throws SystemException {
		final String LOCATION = "loadSecLatestPrice()";
		log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			SearchResult<SecPriceView> result = OracleDAOHelper.query(this.getDataSource(), SQL_GET_SEC_LATEST_PRICE, paramMap, ifoSecPriceViewRowMapper);
			log.debug(LOCATION + ":: END - result:" + result);
			return (result == null ? new ArrayList<SecPriceView>() : result);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
}
