/*--------------------------------------------------------------------------*
 | Modification Logs:
 | DATE          AUTHOR     DESCRIPTION
 | ------------------------------------------------
 | Dec 31, 2007      ThangNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.dao.DAOUtils;
import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class IfoCompanyCalcViewDAO extends BaseDAOImpl {
	private static final Log log = LogFactory.getLog(IfoCompanyCalcViewDAO.class);

	private final RowMapper ifoCompanyCalcViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {

			IfoCompanyCalcView obj = new IfoCompanyCalcView();

			obj.setSecCode(rs.getString("SEC_CODE"));
			obj.setIndustryCode(rs.getString("INDUSTRY_CODE"));
			obj.setIndustryName(rs.getString("INDUSTRY_NAME"));
			obj.setSectorCode(rs.getString("SECTOR_CODE"));
			obj.setSectorName(rs.getString("SECTOR_NAME"));
			obj.setPe(rs.getString("PE"));
			obj.setPb(rs.getString("PB"));
			obj.setScopeMaketCap(rs.getString("SCOPE_MAKET_CAP"));
			obj.setScopeAsset(rs.getString("SCOPE_ASSET"));
			obj.setScopeEquity(rs.getString("SCOPE_EQUITY"));
			obj.setGrowthAsset(rs.getString("GROWTH_ASSET"));
			obj.setGrowthRevenue(rs.getString("GROWTH_REVENUE"));
			obj.setRoa(rs.getString("ROA"));
			obj.setRoe(rs.getString("ROE"));
			obj.setProfitMargin(rs.getString("PROFIT_MARGIN"));
			obj.setDebtEquity(rs.getString("DEBT_EQUITY"));
			obj.setCurrentRatio(rs.getString("CURRENT_RATIO"));
			obj.setEbitda(rs.getString("EBITDA"));
			obj.setLocale(rs.getString("LOCALE"));
			return obj;
		}
	};
	private final RowMapper relatedCompanyRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {

			IfoCompanyCalcView obj = new IfoCompanyCalcView();

			obj.setSecCode(rs.getString("SEC_CODE"));
			obj.setIndustryCode(rs.getString("INDUSTRY_CODE"));
			obj.setIndustryName(rs.getString("INDUSTRY_NAME"));
			obj.setSectorCode(rs.getString("SECTOR_CODE"));
			obj.setSectorName(rs.getString("SECTOR_NAME"));

			obj.setMarketCapital(DAOUtils.valueOfDouble(rs.getString("MARKET_CAPITAL")));
			// obj.setLocale(rs.getString("LOCALE"));
			return obj;
		}
	};

	/**
	 * 
	 * @param company
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoCompanyCalcView> getListCompanies(IfoCompanyCalcView company) throws FunctionalException,
	        SystemException {
		final String LOCATION = "getListCompanies(ifoCompanyCalcView: " + company + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (company == null) {
			throw new FunctionalException(LOCATION, "ifoCompanyCalcView is NULL...");
		}
		try {
			String SEARCH_SQL = "SELECT * FROM IFO_COMPANY_CALC_VIEW "
			        + " WHERE SECTOR_CODE =:SECTOR_CODE AND INDUSTRY_CODE = :INDUSTRY_CODE AND UPPER(LOCALE) = UPPER(:LOCALE) AND EXCHANGE_CODE IN ('HNX', 'HOSTC') ";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("SECTOR_CODE", company.getSectorCode());
			paramMap.put("INDUSTRY_CODE", company.getIndustryCode());
			paramMap.put("LOCALE", company.getLocale());

			if (!StringUtils.isEmpty(company.getSortField())) {
				SEARCH_SQL += " ORDER BY " + company.getSortField();
				if (!StringUtils.isEmpty(company.getOrder())) {
					SEARCH_SQL += "ASC".equals(company.getOrder()) ? " ASC" : " DESC";
				}
			} else {
				SEARCH_SQL += " ORDER BY UPPER(SEC_CODE)";
			}

			SearchResult<IfoCompanyCalcView> results = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoCompanyCalcViewRowMapper);

			log.debug(LOCATION + ":: END - " + results);
			return results;
		} catch (RuntimeException re) {
			log.error(LOCATION + ":: RuntimeException " + re);
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			log.error(LOCATION + ":: Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param ifoCompanyCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult getListCompaniesWithPaging(IfoCompanyCalcView ifoCompanyCalcView, PagingInfo pagingInfo)
	        throws FunctionalException, SystemException {
		final String LOCATION = "getListCompaniesWithPaging(ifoCompanyCalcView: " + ifoCompanyCalcView + "; pagingInfo:"
		        + pagingInfo + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoCompanyCalcView == null) {
			throw new FunctionalException(LOCATION, "ifoCompanyCalcView is NULL...");
		}
		log.debug(LOCATION + ":: SectorCode: " + ifoCompanyCalcView.getSectorCode() + ", IndustryCode: "
		        + ifoCompanyCalcView.getIndustryCode() + ", Locale: " + ifoCompanyCalcView.getLocale());
		try {
			String SEARCH_SQL = "SELECT a.SEC_CODE, a.SECTOR_CODE, a.SECTOR_NAME, a.INDUSTRY_CODE, a.INDUSTRY_NAME, c.MARKET_CAPITAL "
			        + " FROM (IFO_COMPANY_CALC_VIEW a LEFT JOIN IFO_COMPANY_NAME_VIEW b ON a.SEC_CODE = b.SYMBOL ) "
			        + " LEFT JOIN IFO_COMPANY_SNAPSHOT_VIEW c ON b.COMPANY_ID = c.COMPANY_ID"
			        + " where a.SECTOR_CODE =:SECTOR_CODE AND a.INDUSTRY_CODE = :INDUSTRY_CODE AND UPPER(a.LOCALE) = :LOCALE AND UPPER(c.LOCALE) = :LOCALE AND a.EXCHANGE_CODE IN ('HNX', 'HOSTC') ";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("SECTOR_CODE", ifoCompanyCalcView.getSectorCode());
			paramMap.put("INDUSTRY_CODE", ifoCompanyCalcView.getIndustryCode());
			paramMap.put("LOCALE", ifoCompanyCalcView.getLocale().toUpperCase());

			// ++++ sort by field name
			String sortField = "SEC_CODE";
			if (ifoCompanyCalcView.getSortField() != null && ifoCompanyCalcView.getSortField().trim().length() > 0) {
				sortField = ifoCompanyCalcView.getSortField();
			}

			SEARCH_SQL += " ORDER BY UPPER(" + sortField + ") ";
			// SearchResult results = OracleDAOHelper.queryWithBaseBean(this
			// .getDataSource(), SEARCH_SQL, paramMap,
			// relatedCompanyRowMapper, ifoCompanyCalcView);
			SearchResult results = OracleDAOHelper.queryWithPagging(this.getDataSource(), SEARCH_SQL, paramMap,
			        relatedCompanyRowMapper, pagingInfo);

			log.debug(LOCATION + ":: END - " + results);
			return results;
		} catch (RuntimeException re) {
			log.error(LOCATION + ":: RuntimeException " + re);
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			log.error(LOCATION + ":: Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param ifoCompanyCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public Collection<IfoCompanyCalcView> downloadCompanies(IfoCompanyCalcView ifoCompanyCalcView) throws FunctionalException,
	        SystemException {
		final String LOCATION = "downloadCompanies(ifoCompanyCalcView: " + ifoCompanyCalcView + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoCompanyCalcView == null) {
			throw new FunctionalException(LOCATION, "ifoCompanyCalcView is NULL...");
		}
		log.debug(LOCATION + ":: SectorCode: " + ifoCompanyCalcView.getSectorCode() + ", IndustryCode: "
		        + ifoCompanyCalcView.getIndustryCode() + ", Locale: " + ifoCompanyCalcView.getLocale());
		try {
			String SEARCH_SQL = "SELECT * FROM IFO_COMPANY_CALC_VIEW "
			        + " WHERE SECTOR_CODE =:SECTOR_CODE AND INDUSTRY_CODE = :INDUSTRY_CODE AND UPPER(LOCALE) = :LOCALE AND EXCHANGE_CODE IN ('HNX', 'HOSTC')";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("SECTOR_CODE", ifoCompanyCalcView.getSectorCode());
			paramMap.put("INDUSTRY_CODE", ifoCompanyCalcView.getIndustryCode());
			paramMap.put("LOCALE", ifoCompanyCalcView.getLocale().toUpperCase());

			// ++++ sort by field name
			String sortField = "SEC_CODE";
			if (ifoCompanyCalcView.getSortField() != null && ifoCompanyCalcView.getSortField().trim().length() > 0) {
				// TODO: update sort field late
				sortField = ifoCompanyCalcView.getSortField();
			}
			SEARCH_SQL += " ORDER BY UPPER(" + sortField + ") ";
			SearchResult<IfoCompanyCalcView> results = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoCompanyCalcViewRowMapper);

			log.debug(LOCATION + ":: END - " + results);
			return results == null ? new ArrayList<IfoCompanyCalcView>() : results;
		} catch (RuntimeException re) {
			log.error(LOCATION + ":: RuntimeException " + re);
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			log.error(LOCATION + ":: Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param ifoCompanyCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoCompanyCalcView> getIndustryCompanies(IfoCompanyCalcView ifoCompanyCalcView)
	        throws FunctionalException, SystemException {
		final String LOCATION = "getIndustryCompanies(ifoCompanyCalcView: " + ifoCompanyCalcView + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoCompanyCalcView == null) {
			throw new FunctionalException(LOCATION, "ifoCompanyCalcView is NULL...");
		}
		log.debug(LOCATION + ":: Locale: " + ifoCompanyCalcView.getLocale());
		try {
			String SEARCH_SQL = "SELECT * FROM IFO_COMPANY_CALC_VIEW WHERE UPPER(LOCALE) = :LOCALE  AND EXCHANGE_CODE IN ('HNX', 'HOSTC') ";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", ifoCompanyCalcView.getLocale().toUpperCase());

			// ++++ sort by field name
			String sortField = "SEC_CODE";
			if (ifoCompanyCalcView.getSortField() != null && ifoCompanyCalcView.getSortField().trim().length() > 0) {
				sortField = ifoCompanyCalcView.getSortField();
			}
			SEARCH_SQL += " ORDER BY UPPER(" + sortField + ") ";

			// SearchResult results = OracleDAOHelper.queryWithBaseBean(this
			// .getDataSource(), SEARCH_SQL, paramMap,
			// ifoCompanyCalcViewRowMapper);
			SearchResult<IfoCompanyCalcView> results = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoCompanyCalcViewRowMapper);

			log.debug(LOCATION + ":: END - " + results);
			return results;
		} catch (RuntimeException re) {
			log.error(LOCATION + ":: RuntimeException " + re);
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			log.error(LOCATION + ":: Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	// ////////////////////////////// For Caching
	// ///////////////////////////////////////////

	/**
	 * 
	 * @param ifoCompanyCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public IfoCompanyCalcView getIfoCompanyCalcViewBySymbol(IfoCompanyCalcView ifoCompanyCalcView) throws FunctionalException,
	        SystemException {
		final String LOCATION = "getIfoCompanyCalcViewBySymbol(ifoCompanyCalcView: " + ifoCompanyCalcView + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoCompanyCalcView == null || ifoCompanyCalcView.getSecCode() == null) {
			throw new FunctionalException(LOCATION, "ifoCompanyCalcView is NULL...");
		}
		try {
			String SEARCH_SQL = "SELECT * FROM IFO_COMPANY_CALC_VIEW WHERE UPPER(SEC_CODE) = :SEC_CODE AND UPPER(LOCALE) = :LOCALE AND EXCHANGE_CODE IN ('HNX', 'HOSTC') ";

			Map<String, Object> paramMap = new HashMap<String, Object>();

			paramMap.put("SEC_CODE", ifoCompanyCalcView.getSecCode().trim().toUpperCase());
			paramMap.put("LOCALE", ifoCompanyCalcView.getLocale().toUpperCase());

			IfoCompanyCalcView results = (IfoCompanyCalcView) OracleDAOHelper.querySingle(this.getDataSource(), SEARCH_SQL,
			        paramMap, ifoCompanyCalcViewRowMapper);
			log.debug(LOCATION + ":: END - " + results);
			return results == null ? new IfoCompanyCalcView() : results;
		} catch (RuntimeException re) {
			log.error(LOCATION + ":: RuntimeException " + re);
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			log.error(LOCATION + ":: Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param sql
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List<String> searchStockScreener(String sql) throws FunctionalException, SystemException {
		final String LOCATION = "searchStockScreener()";
		log.debug(LOCATION + ":: BEGIN");
		SearchResult results = OracleDAOHelper.query(this.getDataSource(), sql, new HashMap<String, Object>(),
		        stockScreenerRowMapper);
		log.debug(LOCATION + ":: END - " + results);
		return results;
	}

	private final RowMapper stockScreenerRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			return rs.getString("SYMBOL");
		}
	};

	public SearchResult<IfoCompanyCalcView> getCompaniesBySectorCode(IfoCompanyCalcView company, PagingInfo pagingInfo)
	        throws FunctionalException, SystemException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM IFO_COMPANY_CALC_VIEW ");
		sql.append("WHERE SECTOR_CODE = :SECTOR_CODE and UPPER(LOCALE) = UPPER(:LOCALE) AND EXCHANGE_CODE IN ('HNX', 'HOSTC')");

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("SECTOR_CODE", company.getSectorCode());
		paramMap.put("LOCALE", company.getLocale());

		// sort by field name
		String sortField = "SEC_CODE";
		if (!StringUtils.isEmpty(company.getSortField())) {
			sortField = company.getSortField();
		}

		sql.append(" ORDER BY UPPER(" + sortField + ") ");

		return OracleDAOHelper.queryWithPagging(getDataSource(), sql.toString(), paramMap, ifoCompanyCalcViewRowMapper,
		        pagingInfo);
	}
}