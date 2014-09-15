package vn.com.vndirect.embeddb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import vn.com.vndirect.commons.Code;
import vn.com.vndirect.dao.DAOUtils;
import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.vndirect.domain.embeddb.CompanyCalcView;
import vn.com.web.commons.dao.jdbc.H2DAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

@SuppressWarnings("unchecked")
public class CompanyCalcViewDAO extends JdbcDaoSupport {
	private static final Log log = LogFactory.getLog(IndustryCalcViewDAO.class);

	public interface Field {
		String EXCHANGE_CODE = "EXCHANGE_CODE";
		String SEC_CODE = "SEC_CODE";
		String INDUSTRY_CODE = "INDUSTRY_CODE";
		String SECTOR_CODE = "SECTOR_CODE";
		String PE = "PE";
		String PB = "PB";
		String SCOPE_MAKET_CAP = "SCOPE_MAKET_CAP";
		String SCOPE_ASSET = "SCOPE_ASSET";
		String SCOPE_EQUITY = "SCOPE_EQUITY";
		String GROWTH_ASSET = "GROWTH_ASSET";
		String GROWTH_REVENUE = "GROWTH_REVENUE";
		String ROA = "ROA";
		String ROE = "ROE";
		String PROFIT_MARGIN = "PROFIT_MARGIN";
		String DEBT_EQUITY = "DEBT_EQUITY";
		String CURRENT_RATIO = "CURRENT_RATIO";
		String EBITDA = "EBITDA";
	}

	private final static String SQL_INSERT_COMPANY_CALC_VIEW = "INSERT INTO COMPANY_CALC_VIEW(EXCHANGE_CODE, SEC_CODE, INDUSTRY_CODE, SECTOR_CODE, PE, PB, SCOPE_MAKET_CAP, SCOPE_ASSET, SCOPE_EQUITY, GROWTH_ASSET, GROWTH_REVENUE, ROA, ROE, PROFIT_MARGIN, DEBT_EQUITY, CURRENT_RATIO, EBITDA) "
	        + "VALUES (:EXCHANGE_CODE, :SEC_CODE, :INDUSTRY_CODE, :SECTOR_CODE, :PE, :PB, :SCOPE_MAKET_CAP, :SCOPE_ASSET, :SCOPE_EQUITY, :GROWTH_ASSET, :GROWTH_REVENUE, :ROA, :ROE, :PROFIT_MARGIN, :DEBT_EQUITY, :CURRENT_RATIO, :EBITDA)";

	/**
	 * 
	 * @param bean
	 * @throws SystemException
	 */
	public void insert(CompanyCalcView bean) throws SystemException {
		final String LOCATION = "insert(CompanyCalcView)";
		log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(Field.EXCHANGE_CODE, bean.getExchangeCode());
			paramMap.put(Field.SEC_CODE, bean.getSecCode());
			paramMap.put(Field.INDUSTRY_CODE, bean.getIndustryCode());
			paramMap.put(Field.SECTOR_CODE, bean.getSectorCode());
			paramMap.put(Field.PE, bean.getPe());
			paramMap.put(Field.PB, bean.getPb());
			paramMap.put(Field.SCOPE_MAKET_CAP, bean.getScopeMaketCap());
			paramMap.put(Field.SCOPE_ASSET, bean.getScopeAsset());
			paramMap.put(Field.SCOPE_EQUITY, bean.getScopeEquity());
			paramMap.put(Field.GROWTH_ASSET, bean.getScopeAsset());
			paramMap.put(Field.GROWTH_REVENUE, bean.getGrowthRevenue());
			paramMap.put(Field.ROA, bean.getRoa());
			paramMap.put(Field.ROE, bean.getRoe());
			paramMap.put(Field.PROFIT_MARGIN, bean.getProfitMargin());
			paramMap.put(Field.DEBT_EQUITY, bean.getDebtEquity());
			paramMap.put(Field.CURRENT_RATIO, bean.getCurrentRatio());
			paramMap.put(Field.EBITDA, bean.getEbitda());

			H2DAOHelper.update(this.getDataSource(), SQL_INSERT_COMPANY_CALC_VIEW, paramMap);
			log.debug(LOCATION + ":: END");
		} catch (SystemException syse) {
			throw syse;
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	private final RowMapper rowIfoCompanyCalcMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {

			IfoCompanyCalcView obj = new IfoCompanyCalcView();

			obj.setSecCode(rs.getString("SEC_CODE"));
			obj.setIndustryCode(rs.getString("INDUSTRY_CODE"));
			obj.setSectorCode(rs.getString("SECTOR_CODE"));
			obj.setMarketCapital(DAOUtils.valueOfDouble(rs.getString("SCOPE_MAKET_CAP")));

			obj.setExchangCode(rs.getString("EXCHANGE_CODE"));
			obj.setClosePrice(DAOUtils.valueOfDouble(rs.getString("CLOSE_PRICE")));
			obj.setAdClosePrice(DAOUtils.valueOfDouble(rs.getString("AD_CLOSE_PRICE")));

			return obj;
		}
	};

	final static String SQL_SELECT_COMPANY_CALC_VIEW = "select SEC_CODE,INDUSTRY_CODE,SECTOR_CODE,SCOPE_MAKET_CAP,b.EXCHANGE_CODE,CLOSE_PRICE,AD_CLOSE_PRICE from COMPANY_CALC_VIEW a, SEC_LAST_PRICE_VIEW b "
	        + "where a.SEC_CODE = b.SYMBOL and a.industry_code =:INDUSTRY_CODE and  a.sector_code =:SECTOR_CODE ORDER BY UPPER(SEC_CODE) ";

	/**
	 * 
	 * @param searchObj
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<IfoCompanyCalcView> getListCompaniesByIndustry(IfoCompanyCalcView ifoCompanyCalcView,
	        PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "getListCompaniesByIndustry(IfoCompanyCalcView, PagingInfo )";
		log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("SECTOR_CODE", ifoCompanyCalcView.getSectorCode());
			paramMap.put("INDUSTRY_CODE", ifoCompanyCalcView.getIndustryCode());
			SearchResult<IfoCompanyCalcView> results = H2DAOHelper.queryWithPagging(this.getDataSource(),
			        SQL_SELECT_COMPANY_CALC_VIEW, paramMap, rowIfoCompanyCalcMapper, pagingInfo);

			log.debug(LOCATION + ":: END");
			return results;
		} catch (SystemException syse) {
			throw syse;
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * Get all the symbols by code
	 * 
	 * @param code
	 *            the code
	 * @param type
	 *            <code>vn.com.vndirect.commons.Code</code>
	 * @param limit
	 * @return list of symbols
	 * @throws SystemException
	 */
	public String[] getListOfSymbols(String code, Code type, int limit) throws SystemException {

		// build sql
		StringBuffer buffer = new StringBuffer();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		buffer.append("SELECT SEC_CODE FROM COMPANY_CALC_VIEW WHERE EXCHANGE_CODE IN ('HOSTC', 'HNX') AND ");

		// pass parameter
		if (type == Code.INDUSTRY) {
			buffer.append("INDUSTRY_CODE = :INDUSTRY_CODE ");
			paramMap.put("INDUSTRY_CODE", code);
		} else if (type == Code.SECTOR) {
			buffer.append("SECTOR_CODE =:SECTOR_CODE ");
			paramMap.put("SECTOR_CODE", code);
		}
		buffer.append("ORDER BY SEC_CODE LIMIT :LIMIT");

		paramMap.put("LIMIT", limit);

		// get data
		List<String> result = H2DAOHelper.query(getDataSource(), buffer.toString(), paramMap, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("SEC_CODE");
			}
		});
		return result.toArray(new String[] {});
	}

	/**
	 * Get list of companies by code
	 * 
	 * @param code
	 *            the code
	 * @param type
	 *            <code>vn.com.vndirect.commons.Code</code>
	 * @return list of companies
	 * @throws SystemException
	 */
	public SearchResult<IfoCompanyCalcView> getListOfCompanies(String code, Code type, PagingInfo pagingInfo, String sortField,
	        String order) throws SystemException {
		// build sql
		StringBuffer buffer = new StringBuffer();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		buffer.append("SELECT B.COMPANY_NAME, B.COMPANY_FULL_NAME ,A.SYMBOL, A.F53030 REVENUE, A.F23000 INCOME, A.F51003 MARKETCAP ");
		buffer.append("FROM COMPANY_ITEM_CALC_VIEW_TEMP A ,STOCK_EXCHANGE B ");
		buffer.append("WHERE A.SYMBOL = B.SYMBOL AND ");
		buffer.append("B.EXCHANGE_CODE in('HNX', 'HOSTC') AND ");

		if (type == Code.INDUSTRY) {
			buffer.append("A.INDUSTRY_CODE = :INDUSTRY_CODE ");
			// pass parameter
			paramMap.put("INDUSTRY_CODE", code);
		} else if (type == Code.SECTOR) {
			buffer.append("A.SECTOR_CODE = :SECTOR_CODE ");
			// pass parameter
			paramMap.put("SECTOR_CODE", code);
		}
		if (StringUtils.isEmpty(sortField) || StringUtils.isEmpty(order)) {
			buffer.append("ORDER BY SYMBOL ASC");
		} else {
			buffer.append("ORDER BY " + sortField + " " + order);
		}

		// get data
		return H2DAOHelper.queryWithPagging(getDataSource(), buffer.toString(), paramMap, new RowMapper<IfoCompanyCalcView>() {
			public IfoCompanyCalcView mapRow(ResultSet rs, int arg1) throws SQLException {
				IfoCompanyCalcView obj = new IfoCompanyCalcView();
				obj.setCompanyName(rs.getString("COMPANY_NAME"));
				obj.setCompanyFullName(rs.getString("COMPANY_FULL_NAME"));
				obj.setSecCode(rs.getString("SYMBOL"));
				obj.setRevenueGrowthAnnual(rs.getDouble("REVENUE") * 100);
				obj.setIncome(rs.getDouble("INCOME"));
				obj.setMarketCap(rs.getDouble("MARKETCAP"));
				return obj;
			}
		}, pagingInfo);
	}

	/**
	 * Get top company(The hottest company that has the highest price after 5
	 * days) by industry code from embed database
	 * 
	 * @param type
	 *            <code>vn.com.vndirect.commons.Code</code>
	 * @param code
	 *            the sector code
	 * @return list of companies
	 * @throws SystemException
	 */
	public IfoCompanyCalcView getTopCompanyEDB(String code) throws SystemException {
		// build sql
		StringBuffer buffer = new StringBuffer();
		Map<String, Object> paramMap = new HashMap<String, Object>();

		buffer.append("SELECT B.COMPANY_NAME, B.COMPANY_FULL_NAME ,A.SYMBOL, F1000002 ");
		buffer.append("FROM COMPANY_ITEM_CALC_VIEW_TEMP A ,STOCK_EXCHANGE B ");
		buffer.append("WHERE A.SYMBOL = B.SYMBOL AND A.INDUSTRY_CODE = :INDUSTRY_CODE ");
		buffer.append("ORDER BY F1000002 DESC ");

		paramMap.put("INDUSTRY_CODE", code);

		// get data
		return (IfoCompanyCalcView) H2DAOHelper.querySingle(getDataSource(), buffer.toString(), paramMap,
		        new RowMapper<IfoCompanyCalcView>() {
			        public IfoCompanyCalcView mapRow(ResultSet rs, int arg1) throws SQLException {
				        IfoCompanyCalcView obj = new IfoCompanyCalcView();
				        obj.setCompanyName(rs.getString("COMPANY_NAME"));
				        obj.setCompanyFullName(rs.getString("COMPANY_FULL_NAME"));
				        obj.setSecCode(rs.getString("SYMBOL"));
				        // set 5 days change
				        obj.setNumericValue(rs.getDouble("F1000002"));
				        return obj;
			        }
		        });
	}

	/**
	 * Get industry_code and sector_code of a company by its symbol
	 * 
	 * @param symbol
	 *            symbol of a company
	 * @return a Company object if data found otherwise <code>null</code>
	 * @throws SystemException
	 */
	public IfoCompanyCalcView getCompanyEDB(String symbol) throws SystemException {
		// build sql
		StringBuffer buffer = new StringBuffer();
		Map<String, Object> paramMap = new HashMap<String, Object>();

		buffer.append("SELECT ");
		buffer.append("INDUSTRY_CODE, SECTOR_CODE ");
		buffer.append("FROM ");
		buffer.append("COMPANY_CALC_VIEW ");
		buffer.append("WHERE ");
		buffer.append("SEC_CODE = :SYMBOL");

		paramMap.put("SYMBOL", symbol);
		// get data
		return (IfoCompanyCalcView) H2DAOHelper.querySingle(getDataSource(), buffer.toString(), paramMap,
		        new RowMapper<IfoCompanyCalcView>() {
			        public IfoCompanyCalcView mapRow(ResultSet rs, int arg1) throws SQLException {
				        IfoCompanyCalcView obj = new IfoCompanyCalcView();
				        obj.setIndustryCode(rs.getString("INDUSTRY_CODE"));
				        obj.setSectorCode(rs.getString("SECTOR_CODE"));
				        return obj;
			        }
		        });
	}
}
