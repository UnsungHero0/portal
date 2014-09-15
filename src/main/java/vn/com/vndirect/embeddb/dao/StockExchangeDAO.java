package vn.com.vndirect.embeddb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.domain.IfoCompanyNameView;
import vn.com.vndirect.domain.IfoCompanyNameViewId;
import vn.com.vndirect.domain.IfoStockExchangeView;
import vn.com.vndirect.domain.IfoStockExchangeViewId;
import vn.com.vndirect.domain.SearchStockExchangeIndexingBean;
import vn.com.vndirect.domain.embeddb.SearchStockExchange;
import vn.com.vndirect.domain.embeddb.StockExchange;
import vn.com.vndirect.lucene.finfodb.beans.StockExchangeIndexingBean;
import vn.com.web.commons.dao.h2db.H2DBUtils;
import vn.com.web.commons.dao.h2db.H2DBUtils.SearchOption;
import vn.com.web.commons.dao.jdbc.H2DAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

@SuppressWarnings("unchecked")
public class StockExchangeDAO extends JdbcDaoSupport {
	private static final Log log = LogFactory.getLog(StockExchangeDAO.class);

	public interface Field {
		String EXCHANGE_CODE = "EXCHANGE_CODE";
		String SYMBOL = "SYMBOL";
		String EXCHANGE_NAME = "EXCHANGE_NAME";
		String COMPANY_ID = "COMPANY_ID";
		String COMPANY_NAME = "COMPANY_NAME";
		String COMPANY_FULL_NAME = "COMPANY_FULL_NAME";
		String ABB_NAME = "ABB_NAME";
		String FIRST_TRADING_DATE = "FIRST_TRADING_DATE";
		String INDUSTRY_CODE = "INDUSTRY_CODE";
		String INDUSTRY_GROUP_CODE = "INDUSTRY_GROUP_CODE";
		String SECTOR_CODE = "SECTOR_CODE";
		String SECTOR_GROUP_CODE = "SECTOR_GROUP_CODE";
	}

	private final static String SQL_INSERT_STOCK_EXCHANGE = "INSERT INTO STOCK_EXCHANGE(EXCHANGE_CODE, SYMBOL, EXCHANGE_NAME, COMPANY_ID, COMPANY_NAME, COMPANY_FULL_NAME, ABB_NAME, FIRST_TRADING_DATE, INDUSTRY_CODE, INDUSTRY_GROUP_CODE, SECTOR_CODE, SECTOR_GROUP_CODE) "
			+ "VALUES (:EXCHANGE_CODE, :SYMBOL, :EXCHANGE_NAME, :COMPANY_ID, :COMPANY_NAME, :COMPANY_FULL_NAME, :ABB_NAME, :FIRST_TRADING_DATE, :INDUSTRY_CODE, :INDUSTRY_GROUP_CODE, :SECTOR_CODE, :SECTOR_GROUP_CODE)";

	/**
	 * 
	 * @param bean
	 * @throws SystemException
	 */
	public void insert(StockExchange bean) throws SystemException {
		final String LOCATION = "insert(StockExchange)";
		log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(Field.EXCHANGE_CODE, bean.getExchangeCode());
			paramMap.put(Field.SYMBOL, bean.getSymbol());
			paramMap.put(Field.EXCHANGE_NAME, bean.getExchangeName());
			paramMap.put(Field.COMPANY_ID, new Long(bean.getCompanyId()));
			paramMap.put(Field.COMPANY_NAME, bean.getCompanyName());
			paramMap.put(Field.COMPANY_FULL_NAME, bean.getCompanyFullName());
			paramMap.put(Field.ABB_NAME, bean.getAbbName());
			paramMap.put(Field.FIRST_TRADING_DATE, bean.getFirstTradingDate());
			paramMap.put(Field.INDUSTRY_CODE, bean.getIndustryCode());
			paramMap.put(Field.INDUSTRY_GROUP_CODE, bean.getIndustryGroupCode());
			paramMap.put(Field.SECTOR_CODE, bean.getSectorCode());
			paramMap.put(Field.SECTOR_GROUP_CODE, bean.getSectorGroupCode());

			H2DAOHelper.update(this.getDataSource(), SQL_INSERT_STOCK_EXCHANGE, paramMap);
			log.debug(LOCATION + ":: END");
		} catch (SystemException syse) {
			throw syse;
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
     * 
     */
	private final RowMapper stockExchangeIndexingRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			StockExchangeIndexingBean stockExchangeCachingInfo = new StockExchangeIndexingBean();

			// +++ get IfoStockExchangeView information
			IfoStockExchangeViewId stockExchangeObjId = new IfoStockExchangeViewId();
			stockExchangeObjId.setSymbol(rs.getString(Field.SYMBOL));
			stockExchangeObjId.setExchangeCode(rs.getString(Field.EXCHANGE_CODE));
			stockExchangeObjId.setExchangeName(rs.getString(Field.EXCHANGE_NAME));

			stockExchangeCachingInfo.setIfoStockExchangeView(new IfoStockExchangeView(stockExchangeObjId));

			// +++ get IfoCompanyNameView information
			long compId = rs.getLong(Field.COMPANY_ID);
			if (compId != 0) {
				// ++++ get sector & industry codes info
				stockExchangeCachingInfo.setSectorGroupCode(rs.getString(Field.SECTOR_GROUP_CODE));
				stockExchangeCachingInfo.setSectorCode(rs.getString(Field.SECTOR_CODE));
				stockExchangeCachingInfo.setIndustryGroupCode(rs.getString(Field.INDUSTRY_GROUP_CODE));
				stockExchangeCachingInfo.setIndustryCode(rs.getString(Field.INDUSTRY_CODE));

				// ++++ get IfoCompanyNameView info
				IfoCompanyNameViewId compNameObjId = new IfoCompanyNameViewId();
				compNameObjId.setCompanyId(compId);
				compNameObjId.setSymbol(stockExchangeObjId.getSymbol());
				compNameObjId.setCompanyName(rs.getString(Field.COMPANY_NAME));
				compNameObjId.setCompanyFullName(rs.getString(Field.COMPANY_FULL_NAME));
				compNameObjId.setAbbName(rs.getString(Field.ABB_NAME));
				compNameObjId.setFirstTradingDate(rs.getDate(Field.FIRST_TRADING_DATE));

				stockExchangeCachingInfo.setIfoCompanyNameView(new IfoCompanyNameView(compNameObjId));
				stockExchangeCachingInfo.getIfoCompanyNameView().setExchangeCode(stockExchangeObjId.getExchangeCode());
				stockExchangeCachingInfo.getIfoCompanyNameView().setExchangeName(stockExchangeObjId.getExchangeName());
				stockExchangeCachingInfo.getIfoCompanyNameView().setSectorName(stockExchangeCachingInfo.getSectorCode());
			}
			return stockExchangeCachingInfo;
		}
	};

	/**
	 * 
	 * @param searchStockExchangeIndexing
	 * @param pagingInfo
	 * @return
	 * @throws SystemException
	 */
	public List<StockExchangeIndexingBean> search(SearchStockExchangeIndexingBean searchStockExchangeIndexing, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "getAllStockExchange()";
		log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// Map params = new HashMap();

			StringBuffer queryString = new StringBuffer(" SELECT * FROM STOCK_EXCHANGE WHERE (COMPANY_ID >0) ");

			String[] arrTemp;
			// +++ build search with markets
			arrTemp = searchStockExchangeIndexing.getExchangeCodes();
			if (arrTemp != null && arrTemp.length > 0) {
				queryString.append(" AND ( ").append(H2DBUtils.buildQuery(Field.EXCHANGE_CODE, arrTemp, SearchOption.NONE, params)).append(" ) ");
			}
			// +++ build search with symbols
			arrTemp = searchStockExchangeIndexing.getSymbols();
			if (arrTemp != null && arrTemp.length > 0) {
				queryString.append(" AND ( ").append(H2DBUtils.buildQuery(Field.SYMBOL, arrTemp, SearchOption.WIlDCARD, params)).append(" ) ");
			}
			// +++ build search with companyIds
			long[] companyIds = searchStockExchangeIndexing.getCompanyIds();
			if (companyIds != null && companyIds.length > 0) {
				queryString.append(" AND ( ").append(H2DBUtils.buildQuery(Field.COMPANY_ID, companyIds, params)).append(" ) ");
			}

			// +++ build search with sectorGroupCodes & sectorCodes
			arrTemp = searchStockExchangeIndexing.getSectorGroupCodes();
			if (arrTemp != null && arrTemp.length > 0) {
				queryString.append(" AND ( ").append(H2DBUtils.buildQuery(Field.SECTOR_GROUP_CODE, arrTemp, SearchOption.NONE, params)).append(" ) ");
			}

			arrTemp = searchStockExchangeIndexing.getSectorCodes();
			if (arrTemp != null && arrTemp.length > 0) {
				queryString.append(" AND ( ").append(H2DBUtils.buildQuery(Field.SECTOR_CODE, arrTemp, SearchOption.NONE, params)).append(" ) ");
			}

			// +++ build search with industryGroupCodes & industryCodes
			arrTemp = searchStockExchangeIndexing.getIndustryGroupCodes();
			if (arrTemp != null && arrTemp.length > 0) {
				queryString.append(" AND ( ").append(H2DBUtils.buildQuery(Field.INDUSTRY_GROUP_CODE, arrTemp, SearchOption.NONE, params)).append(" ) ");
			}

			arrTemp = searchStockExchangeIndexing.getIndustryCodes();
			if (arrTemp != null && arrTemp.length > 0) {
				queryString.append(" AND ( ").append(H2DBUtils.buildQuery(Field.INDUSTRY_CODE, arrTemp, SearchOption.NONE, params)).append(" ) ");
			}

			queryString.append(" ORDER BY SYMBOL");
			SearchResult<StockExchangeIndexingBean> result = H2DAOHelper.query(this.getDataSource(), queryString.toString(), params, stockExchangeIndexingRowMapper, pagingInfo);

			log.debug(LOCATION + ":: END - result: " + result.size());
			return result;
		} catch (SystemException syse) {
			throw new SystemException(LOCATION, syse);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * @param searchStockExchange
	 * @param pagingInfo
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<StockExchange> search(SearchStockExchange searchStockExchange, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "search()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// Map params = new HashMap();

			StringBuffer queryString = new StringBuffer(" SELECT * FROM STOCK_EXCHANGE WHERE (COMPANY_ID >0) ");

			String[] arrTemp;
			// +++ build search with markets
			arrTemp = searchStockExchange.getExchangeCodes();
			if (arrTemp != null && arrTemp.length > 0) {
				queryString.append(" AND ( ").append(H2DBUtils.buildQuery(Field.EXCHANGE_CODE, arrTemp, SearchOption.NONE, params)).append(" ) ");
			}
			// +++ build search with symbols
			arrTemp = searchStockExchange.getSymbols();
			if (arrTemp != null && arrTemp.length > 0) {
				queryString.append(" AND ( ").append(H2DBUtils.buildQuery(Field.SYMBOL, arrTemp, SearchOption.WIlDCARD, params)).append(" ) ");
			}

			// +++ build search with IgnoreSymbols
			arrTemp = searchStockExchange.getIgnoreSymbols();
			if (arrTemp != null && arrTemp.length > 0) {
				queryString.append(" AND ( ").append(H2DBUtils.buildQuery_NotIn(Field.SYMBOL, arrTemp, params)).append(" ) ");
			}

			// +++ build search with companyIds
			long[] companyIds = searchStockExchange.getCompanyIds();
			if (companyIds != null && companyIds.length > 0) {
				queryString.append(" AND ( ").append(H2DBUtils.buildQuery(Field.COMPANY_ID, companyIds, params)).append(" ) ");
			}

			// +++ build search with sectorGroupCodes & sectorCodes
			arrTemp = searchStockExchange.getSectorGroupCodes();
			if (arrTemp != null && arrTemp.length > 0) {
				queryString.append(" AND ( ").append(H2DBUtils.buildQuery(Field.SECTOR_GROUP_CODE, arrTemp, SearchOption.NONE, params)).append(" ) ");
			}

			arrTemp = searchStockExchange.getSectorCodes();
			if (arrTemp != null && arrTemp.length > 0) {
				queryString.append(" AND ( ").append(H2DBUtils.buildQuery(Field.SECTOR_CODE, arrTemp, SearchOption.NONE, params)).append(" ) ");
			}

			// +++ build search with industryGroupCodes & industryCodes
			arrTemp = searchStockExchange.getIndustryGroupCodes();
			if (arrTemp != null && arrTemp.length > 0) {
				queryString.append(" AND ( ").append(H2DBUtils.buildQuery(Field.INDUSTRY_GROUP_CODE, arrTemp, SearchOption.NONE, params)).append(" ) ");
			}

			arrTemp = searchStockExchange.getIndustryCodes();
			if (arrTemp != null && arrTemp.length > 0) {
				queryString.append(" AND ( ").append(H2DBUtils.buildQuery(Field.INDUSTRY_CODE, arrTemp, SearchOption.NONE, params)).append(" ) ");
			}

			// +++ search Company Name
			String locale = searchStockExchange.getLocale();
			arrTemp = searchStockExchange.getCompanyNames();
			if (locale != null && arrTemp != null && arrTemp.length > 0) {
				if (Constants.Lang.EN.equalsIgnoreCase(locale) || Constants.Lang.JP.equalsIgnoreCase(locale)) {
					queryString.append(" AND ( ").append(H2DBUtils.buildQuery(Field.ABB_NAME, arrTemp, SearchOption.WIlDCARD, params)).append(" ) ");
				} else {
					queryString.append(" AND ( ").append(H2DBUtils.buildQuery(Field.COMPANY_NAME, arrTemp, SearchOption.WIlDCARD, params)).append(" ) ");
				}
			}

			// +++ add order by
			queryString.append(" ORDER BY SYMBOL");
			SearchResult<StockExchange> result = H2DAOHelper.query(this.getDataSource(), queryString.toString(), params, stockExchangeIndexingRowMapper, pagingInfo);

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END - result: " + result.size());
			return result;
		} catch (SystemException syse) {
			throw new SystemException(LOCATION, syse);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

}
