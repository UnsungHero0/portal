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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.vndirect.domain.IfoIndustryCalcView;
import vn.com.vndirect.domain.IfoSectorCalcView;
import vn.com.vndirect.lucene.finfodb.beans.AnalysisCachingValueInfo;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class IfoIndustryCalcViewDAO extends BaseDAOImpl {
	private static final Log log = LogFactory.getLog(IfoIndustryCalcViewDAO.class);

	private final RowMapper ifoIndustryCalcViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {

			IfoIndustryCalcView obj = new IfoIndustryCalcView();

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

	private final RowMapper ifoIndustryNameRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoIndustryCalcView obj = new IfoIndustryCalcView();
			obj.setCompanyId(rs.getString("COMPANY_ID"));
			obj.setIndustryName(rs.getString("INDUSTRY_NAME"));
			return obj;
		}
	};

	private final RowMapper ifoIndustryCalcMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			AnalysisCachingValueInfo obj = new AnalysisCachingValueInfo(rs.getString("ITEM_CODE"), rs.getDouble("NUMERIC_VALUE"),
			        rs.getString("TEXT_VALUE"));
			return obj;
		}
	};

	public Map<String, AnalysisCachingValueInfo> getIndustryCalc(Long companyId, String locale) throws FunctionalException,
	        SystemException {
		final String LOCATION = "getIndustryCalc(companyId: " + companyId + " locale " + locale + " )";
		log.debug(LOCATION + ":: BEGIN");

		Map<String, AnalysisCachingValueInfo> mapValues = new HashMap<String, AnalysisCachingValueInfo>();
		try {
			String SEARCH_SQL = "SELECT C.ITEM_CODE,C.NUMERIC_VALUE,C.TEXT_VALUE FROM IFO_INDUSTRY_CODE A, IFO_COMPANY_INDUSTRY B, IFO_INDEX_CALC C WHERE A.INDUSTRY_CODE = B.INDUSTRY_CODE AND B.COMPANY_ID = :COMPANY_ID AND C.INDEX_CODE = A.INDUSTRY_GROUP_CODE AND ITEM_CODE IN (1000030,1000031,1000033) AND UPPER(A.LOCALE) = UPPER(:LOCALE) AND C.TRANS_DATE = (SELECT MAX (TRANS_DATE) FROM IFO_INDEX_CALC WHERE INDEX_CODE = C.INDEX_CODE AND ITEM_CODE = C.ITEM_CODE) ";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("COMPANY_ID", companyId);
			paramMap.put("LOCALE", locale);

			SearchResult<AnalysisCachingValueInfo> results = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoIndustryCalcMapper);
			AnalysisCachingValueInfo tempOjb;
			for (int i = 0; i < results.size(); i++) {
				tempOjb = results.get(i);
				mapValues.put(tempOjb.getItemCode(), tempOjb);
			}
			log.debug(LOCATION + ":: END - " + results);
			return mapValues;
		} catch (Exception e) {
			log.error(LOCATION + ":: Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param ifoIndustryCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoIndustryCalcView> getListIndustries(IfoIndustryCalcView industry) throws FunctionalException,
	        SystemException {
		final String LOCATION = "getListIndustries(ifoIndustryCalcView: " + industry + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (industry == null) {
			throw new FunctionalException(LOCATION, "ifoIndustryCalcView is NULL...");
		}
		try {
			String SEARCH_SQL = "SELECT * FROM IFO_INDUSTRY_CALC_VIEW WHERE SECTOR_CODE =:SECTOR_CODE AND UPPER(LOCALE) = UPPER(:LOCALE) ";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("SECTOR_CODE", industry.getSectorCode());
			paramMap.put("LOCALE", industry.getLocale());

			// ++++ sort by field name
			if (!StringUtils.isEmpty(industry.getSortField())) {
				SEARCH_SQL += " ORDER BY " + industry.getSortField();
				if (!StringUtils.isEmpty(industry.getOrder())) {
					SEARCH_SQL += "ASC".equals(industry.getOrder()) ? " ASC" : " DESC";
				}
			} else {
				SEARCH_SQL += " ORDER BY UPPER(INDUSTRY_NAME)";
			}

			/*
			 * System.out.println("SEARCH_SQL: " + SEARCH_SQL);
			 * System.out.println("paramMap: " + paramMap);
			 */
			SearchResult<IfoIndustryCalcView> results = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoIndustryCalcViewRowMapper);
			/*
			 * for (IfoIndustryCalcView item : results) {
			 * System.out.println(item.getIndustryName()); }
			 */

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
	 * @param ifoIndustryCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public Collection<IfoIndustryCalcView> downloadIndustries(IfoIndustryCalcView ifoIndustryCalcView)
	        throws FunctionalException, SystemException {
		final String LOCATION = "downloadIndustries(ifoIndustryCalcView: " + ifoIndustryCalcView + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoIndustryCalcView == null) {
			throw new FunctionalException(LOCATION, "ifoIndustryCalcView is NULL...");
		}
		log.debug(LOCATION + ":: SectorCode: " + ifoIndustryCalcView.getSectorCode() + ", Locale: "
		        + ifoIndustryCalcView.getLocale());
		try {
			String SEARCH_SQL = "SELECT * FROM IFO_INDUSTRY_CALC_VIEW WHERE SECTOR_CODE =:SECTOR_CODE AND UPPER(LOCALE) = :LOCALE ";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("SECTOR_CODE", ifoIndustryCalcView.getSectorCode());
			paramMap.put("LOCALE", ifoIndustryCalcView.getLocale().toUpperCase());

			// ++++ sort by field name
			String sortField = "INDUSTRY_NAME";
			if (ifoIndustryCalcView.getSortField() != null && ifoIndustryCalcView.getSortField().trim().length() > 0) {
				// : update sort field late
				sortField = ifoIndustryCalcView.getSortField();
			}
			SEARCH_SQL += " ORDER BY UPPER(" + sortField + ") ";

			SearchResult<IfoIndustryCalcView> results = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoIndustryCalcViewRowMapper);
			log.debug(LOCATION + ":: END - " + results);
			return results == null ? new ArrayList<IfoIndustryCalcView>() : results;
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
	 * @param ifoIndustryCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoIndustryCalcView> getSectorIndustries(IfoIndustryCalcView ifoIndustryCalcView)
	        throws FunctionalException, SystemException {
		final String LOCATION = "getSectorIndustries(ifoIndustryCalcView: " + ifoIndustryCalcView + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoIndustryCalcView == null) {
			throw new FunctionalException(LOCATION, "ifoIndustryCalcView is NULL...");
		}

		try {
			String SEARCH_SQL = "SELECT * FROM IFO_INDUSTRY_CALC_VIEW WHERE UPPER(LOCALE) = UPPER(:LOCALE) ";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", ifoIndustryCalcView.getLocale());

			// ++++ sort by field name
			String sortField;
			if ("INDUSTRY_NAME".equals(ifoIndustryCalcView.getSortField())) {
				sortField = "INDUSTRY_NAME";
				// SEARCH_SQL += " AND INDUSTRY_NAME LIKE :INDUSTRY_NAME";
				// paramMap.put("INDUSTRY_NAME",
				// ifoIndustryCalcView.getFirstChar() + "%");
			} else {
				sortField = "SECTOR_NAME";
				// SEARCH_SQL += " AND SECTOR_NAME LIKE :SECTOR_NAME";
				// paramMap.put("SECTOR_NAME",
				// ifoIndustryCalcView.getFirstChar() + "%");
			}

			ifoIndustryCalcView.setSortField(sortField);

			SEARCH_SQL += " ORDER BY UPPER(" + sortField + ") ";
			log.debug("###########################################:: SEARCH_SQL  \n " + SEARCH_SQL);
			// SearchResult results =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// SEARCH_SQL, paramMap, ifoIndustryCalcViewRowMapper);
			SearchResult<IfoIndustryCalcView> results = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoIndustryCalcViewRowMapper);

			log.debug(LOCATION + ":: END - " + results);
			return results;
		} catch (RuntimeException re) {
			log.error(LOCATION + ":: RuntimeException " + re);
			re.printStackTrace();
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			log.error(LOCATION + ":: Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	// ////////////////////////////// For Caching
	// ///////////////////////////////////////////

	private final RowMapper firstCharOfIndustryNameRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			return rs.getString("FIRST_CHAR");
		}
	};

	private final String SQL_SELECT_FIRST_CHAR_INDUSTRY = "SELECT DISTINCT SUBSTR(INDUSTRY_NAME, 0, 1) as FIRST_CHAR FROM IFO_INDUSTRY_CALC_VIEW WHERE  UPPER(LOCALE) = :LOCALE ORDER BY FIRST_CHAR";

	/**
	 * 
	 * @param ifoIndustryCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List getListFirstCharOfIndustryName(IfoIndustryCalcView ifoIndustryCalcView) throws FunctionalException,
	        SystemException {
		final String LOCATION = "getAllIndustryNames()";
		log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", ifoIndustryCalcView.getLocale().toUpperCase());
			SearchResult results = OracleDAOHelper.query(this.getDataSource(), SQL_SELECT_FIRST_CHAR_INDUSTRY, paramMap,
			        firstCharOfIndustryNameRowMapper);
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
	 * @param ifoIndustryCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public IfoIndustryCalcView getIfoIndustryCalcViewByCode(IfoIndustryCalcView ifoIndustryCalcView) throws FunctionalException,
	        SystemException {
		final String LOCATION = "getIfoIndustryCalcViewByCode(ifoIndustryCalcView: " + ifoIndustryCalcView + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoIndustryCalcView == null) {
			throw new FunctionalException(LOCATION, "ifoIndustryCalcView is NULL...");
		}
		log.debug(LOCATION + ":: SectorCode: " + ifoIndustryCalcView.getSectorCode() + ", Locale: "
		        + ifoIndustryCalcView.getLocale());
		try {
			String SEARCH_SQL = "SELECT * FROM IFO_INDUSTRY_CALC_VIEW WHERE SECTOR_CODE =:SECTOR_CODE AND INDUSTRY_CODE =:INDUSTRY_CODE AND UPPER(LOCALE) = :LOCALE ";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("SECTOR_CODE", ifoIndustryCalcView.getSectorCode());
			paramMap.put("INDUSTRY_CODE", ifoIndustryCalcView.getIndustryCode());
			paramMap.put("LOCALE", ifoIndustryCalcView.getLocale().toUpperCase());

			IfoIndustryCalcView results = (IfoIndustryCalcView) OracleDAOHelper.querySingle(this.getDataSource(), SEARCH_SQL,
			        paramMap, ifoIndustryCalcViewRowMapper);
			log.debug(LOCATION + ":: END - " + results);
			return results == null ? new IfoIndustryCalcView() : results;
		} catch (RuntimeException re) {
			log.error(LOCATION + ":: RuntimeException " + re);
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			log.error(LOCATION + ":: Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	public Map<String, String> getIndustryNameByCompanyIds(List companyIds, String locale) throws FunctionalException,
	        SystemException {
		final String LOCATION = "getIndustryNameByCompanyIds(companyIds: " + companyIds + ")";
		if (companyIds == null || companyIds.size() == 0) {
			return null;
		}
		Map<String, String> result = new HashMap<String, String>();
		try {
			String SQL_SEARCH = "SELECT a.INDUSTRY_NAME, a.COMPANY_ID FROM IFO_COMPANY_INDUSTRY_VIEW a WHERE  UPPER(LOCALE) = :LOCALE AND";
			Map<String, Object> paramMap = new HashMap<String, Object>();
			StringBuffer strBuf = new StringBuffer();
			for (Iterator iterator = companyIds.iterator(); iterator.hasNext();) {
				String object = "" + iterator.next();
				strBuf.append(strBuf.length() == 0 ? "" : ", ").append("'").append(object).append("'");
			}
			paramMap.put("LOCALE", locale);
			SQL_SEARCH = SQL_SEARCH + " COMPANY_ID IN(" + strBuf.toString() + ")";
			// SearchResult searchResult = (SearchResult)
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// SQL_SEARCH, paramMap, ifoIndustryNameRowMapper, null);
			SearchResult searchResult = (SearchResult) OracleDAOHelper.query(this.getDataSource(), SQL_SEARCH, paramMap,
			        ifoIndustryNameRowMapper);
			if (searchResult != null) {
				for (Iterator iterator = searchResult.iterator(); iterator.hasNext();) {
					IfoIndustryCalcView object = (IfoIndustryCalcView) iterator.next();
					result.put(object.getCompanyId().trim(), object.getIndustryName());
				}
			}
		} catch (RuntimeException re) {
			log.error(LOCATION + ":: RuntimeException " + re);
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			log.error(LOCATION + ":: Exception " + e);
			throw new SystemException(LOCATION, e);
		}
		return result;
	}

	private final String SQL_SELECT_INDUSTRY_NAME_LIST = "SELECT DISTINCT INDUSTRY_CODE, INDUSTRY_NAME, LOCALE FROM IFO_INDUSTRY_CALC_VIEW WHERE SECTOR_CODE = :SECTOR_CODE AND UPPER(LOCALE) = :LOCALE ORDER BY INDUSTRY_NAME ";
	private final RowMapper ifoIndustryNamesRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {

			IfoIndustryCalcView obj = new IfoIndustryCalcView();
			obj.setIndustryCode(rs.getString("INDUSTRY_CODE"));
			obj.setIndustryName(rs.getString("INDUSTRY_NAME"));
			obj.setLocale(rs.getString("LOCALE"));
			return obj;
		}
	};

	/**
	 * 
	 * @param ifoSectorCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List<IfoIndustryCalcView> getListIndustryName(IfoIndustryCalcView ifoIndustryCalcView) throws FunctionalException,
	        SystemException {
		final String LOCATION = "getListIndustryName(ifoIndustryCalcView:" + ifoIndustryCalcView + ")";
		log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", ifoIndustryCalcView.getLocale().toUpperCase());
			paramMap.put("SECTOR_CODE", ifoIndustryCalcView.getSectorCode());

			SearchResult<IfoIndustryCalcView> results = OracleDAOHelper.query(this.getDataSource(),
			        SQL_SELECT_INDUSTRY_NAME_LIST, paramMap, ifoIndustryNamesRowMapper);

			log.debug(LOCATION + ":: END - " + results.size());
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
	 * Get the limited list of industries
	 * 
	 * @param limit
	 *            the number of industries to get
	 * @return the list of industries
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoIndustryCalcView> getListOfNamedIndustries(int limit, String locale) throws FunctionalException,
	        SystemException {

		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT * FROM (");
		buffer.append("SELECT ");
		buffer.append("DISTINCT SECTOR_CODE, INDUSTRY_CODE, INDUSTRY_NAME, LOCALE ");
		buffer.append("FROM IFO_INDUSTRY_CALC_VIEW ");
		buffer.append("WHERE UPPER(LOCALE) = UPPER(:LOCALE) ");
		buffer.append("ORDER BY INDUSTRY_NAME");
		buffer.append(") WHERE ROWNUM <= :LIMIT");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("LOCALE", locale);
		paramMap.put("LIMIT", limit);

		return OracleDAOHelper.query(this.getDataSource(), buffer.toString(), paramMap, new RowMapper<IfoIndustryCalcView>() {
			public IfoIndustryCalcView mapRow(ResultSet rs, int arg1) throws SQLException {
				IfoIndustryCalcView obj = new IfoIndustryCalcView();
				obj.setSectorCode(rs.getString("SECTOR_CODE"));
				obj.setIndustryCode(rs.getString("INDUSTRY_CODE"));
				obj.setIndustryName(rs.getString("INDUSTRY_NAME"));
				obj.setLocale(rs.getString("LOCALE"));
				return obj;
			}
		});
	}

	/**
	 * Get a list of hot industries
	 * 
	 * @param limit
	 *            number of industries
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @return list of industries
	 * @throws SystemException
	 */
	public <T extends IfoIndustryCalcView> List<T> getHotIndustries(int limit, String locale) throws SystemException {
		// build the sql
		StringBuffer buffer = new StringBuffer();

		buffer.append("select ");
		buffer.append("  distinct x.*, (select symbol from IFO_COMPANY_NAME_VIEW where company_id = x.max_company_id) symbol ");
		buffer.append("from (");
		buffer.append("  select  ");
		buffer.append("    v.industry_group_code ");
		buffer.append("    ,v.industry_group_name ");
		buffer.append("    ,v.industry_value ");
		buffer.append("    ,u.sector_group_code ");
		buffer.append("    ,FIRST_VALUE(u.company_id) over (partition by u.industry_group_code order by company_value desc)  max_company_id ");
		buffer.append("  from ");
		buffer.append("  (select ");
		buffer.append("    a.company_id,     ");
		buffer.append("    b.numeric_value company_value, ");
		buffer.append("    a.industry_group_code, ");
		buffer.append("    a.sector_group_code ");
		buffer.append("  from ");
		buffer.append("    IFO_COMPANY_INDUSTRY_VIEW a, ");
		buffer.append("    IFO_COMPANY_ITEM_CALC_VIEW b ");
		buffer.append("  where ");
		buffer.append("    a.company_id = b.company_id and ");
		buffer.append("    b.item_code = '1000002' and ");
		buffer.append("    UPPER(a.locale) = UPPER(:LOCALE)) u ");
		buffer.append("    ,");
		buffer.append("   (select * from ( ");
		buffer.append("      select ");
		buffer.append("        industry_group_name, industry_group_code, numeric_value industry_value ");
		buffer.append("      from ");
		buffer.append("        ifo_industry_group_code a, IFO_INDEX_CALC_VIEW b ");
		buffer.append("      where ");
		buffer.append("        a.industry_group_code = b.index_code and ");
		buffer.append("        item_code = '1000029' AND ");
		buffer.append("        UPPER(locale) = UPPER(:LOCALE) ");
		buffer.append("        AND trans_date = (SELECT MAX (trans_date) FROM IFO_INDEX_CALC_VIEW WHERE item_code IN ('1000029')) ");
		buffer.append("      order by ");
		buffer.append("        numeric_value desc) ");
		buffer.append("    where rownum <= :LIMIT) v ");
		buffer.append("     where u.industry_group_code = v.industry_group_code ");
		buffer.append(") x    ");
		buffer.append(" order by industry_value desc");

		// add parameters
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("LOCALE", locale);
		paramMap.put("LIMIT", limit);

		// get the result from data source
		return OracleDAOHelper.query(getDataSource(), buffer.toString(), paramMap, new RowMapper<T>() {

			public T mapRow(ResultSet rs, int arg1) throws SQLException {
				IfoCompanyCalcView obj = new IfoCompanyCalcView();
				obj.setSectorCode(rs.getString("SECTOR_GROUP_CODE"));
				obj.setIndustryName(rs.getString("INDUSTRY_GROUP_NAME"));
				obj.setIndustryCode(rs.getString("INDUSTRY_GROUP_CODE"));
				obj.setNumericValue(rs.getDouble("INDUSTRY_VALUE"));
				obj.setSecCode(rs.getString("SYMBOL"));
				return (T) obj;
			}
		});
	}

	public <T extends IfoIndustryCalcView> List<T> getIndustriesForHotSector(String sectorGroupCode, int limit, String locale)
	        throws SystemException {
		final String LOCATION = "getIndustriesForHotSector(sectorGroupCode: " + sectorGroupCode + "-- limit: " + limit
		        + "-- locale: )" + locale;
		if (logger.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		// build the sql
		StringBuffer buffer = new StringBuffer();

		buffer.append("SELECT * ");
		buffer.append("FROM ( ");
		buffer.append("  select i.INDUSTRY_GROUP_CODE, i.INDUSTRY_GROUP_NAME, v.NUMERIC_VALUE");
		buffer.append("  FROM   ifo_index_calc_view v,   ");
		buffer.append("    ifo_industry_group_code i, ");
		buffer.append("    ifo_sector_code s ");
		buffer.append("  WHERE  i.INDUSTRY_GROUP_CODE = v.index_code ");
		buffer.append("  and  s.sector_code = i.sector_code ");
		buffer.append("  AND  s.SECTOR_GROUP_CODE = :SECTOR_GROUP_CODE ");
		buffer.append("  AND i.locale = UPPER(:LOCALE) ");
		buffer.append("  AND s.locale = UPPER(:LOCALE) ");
		buffer.append("  and v.item_code = '1000029'  ");
		buffer.append("  AND v.trans_date = (SELECT MAX (trans_date) FROM ifo_index_calc_view WHERE item_code = '1000029')");
		buffer.append("  ORDER BY v.numeric_value desc)  ");
		buffer.append("WHERE  rownum <= :LIMIT  ");

		// add parameters
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("SECTOR_GROUP_CODE", sectorGroupCode);
		paramMap.put("LOCALE", locale);
		paramMap.put("LIMIT", limit);

		// get the result from data source
		List<T> result = OracleDAOHelper.query(getDataSource(), buffer.toString(), paramMap, new RowMapper<T>() {

			public T mapRow(ResultSet rs, int arg1) throws SQLException {
				IfoCompanyCalcView obj = new IfoCompanyCalcView();
				obj.setIndustryName(rs.getString("INDUSTRY_GROUP_NAME"));
				obj.setIndustryCode(rs.getString("INDUSTRY_GROUP_CODE"));
				obj.setNumericValue(rs.getDouble("NUMERIC_VALUE"));
				return (T) obj;
			}
		});
		if (logger.isDebugEnabled())
			log.debug(LOCATION + ":: END");
		return result;
	}

	/**
	 * Get industries which are high performance in three month
	 * 
	 * @param limit
	 *            number of industries
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @return list of industries
	 * @throws SystemException
	 */
	public List<IfoIndustryCalcView> getThrMonHighPerfIndustries(int limit, String locale) throws SystemException {
		// build the sql
		StringBuffer buffer = new StringBuffer();

		/*
		 * buffer.append(
		 * "select * from (select industry_group_name INDUSTRY_NAME, industry_group_code INDUSTRY_CODE, numeric_value from ifo_industry_group_code a, IFO_INDEX_CALC_VIEW b "
		 * ); buffer.append(
		 * "where a.industry_group_code = b.index_code and item_code = 1000030 AND UPPER(locale) = UPPER(:LOCALE) and numeric_value > 0 "
		 * ); buffer.append("order by numeric_value desc) ");
		 * buffer.append("where rownum <= :LIMIT ");
		 */

		buffer.append("select * from (");
		buffer.append("	select ");
		buffer.append("		(select sector_GROUP_code from ifo_sector_code where sector_code = a.sector_code and rownum = 1) sector_GROUP_code, ");
		buffer.append("		industry_group_name INDUSTRY_NAME, ");
		buffer.append("		industry_group_code INDUSTRY_CODE, ");
		buffer.append("		numeric_value ");
		buffer.append("	from");
		buffer.append("		ifo_industry_group_code a, IFO_INDEX_CALC_VIEW b ");
		buffer.append("	where");
		buffer.append("		a.industry_group_code = b.index_code and ");
		buffer.append("		item_code = 1000030 AND ");
		buffer.append("		UPPER(locale) = UPPER(:LOCALE) ");
		buffer.append("		AND b.trans_date =  (SELECT MAX (trans_date)  FROM ifo_index_calc_view  WHERE item_code = '1000030') ");
		buffer.append("		and numeric_value > 0 ");
		buffer.append("	order by ");
		buffer.append("		numeric_value desc) ");
		buffer.append("where rownum <= :LIMIT ");

		// add parameters
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("LOCALE", locale);
		paramMap.put("LIMIT", limit);

		// get the result from data source
		return OracleDAOHelper.query(getDataSource(), buffer.toString(), paramMap, new RowMapper<IfoSectorCalcView>() {

			public IfoSectorCalcView mapRow(ResultSet rs, int arg1) throws SQLException {
				IfoIndustryCalcView obj = new IfoIndustryCalcView();
				obj.setSectorCode(rs.getString("SECTOR_GROUP_CODE"));
				obj.setIndustryName(rs.getString("INDUSTRY_NAME"));
				obj.setIndustryCode(rs.getString("INDUSTRY_CODE"));
				obj.setNumericValue(rs.getDouble("NUMERIC_VALUE"));
				return obj;
			}
		});
	}

	/**
	 * Get a industry for overview
	 * 
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @param industryCode
	 * @return map of a industry that contains item code(1000017, 1000028,
	 *         1000029, 1000030, 1000031, 1000032) as a key
	 * @throws SystemException
	 */
	public Map<String, IfoIndustryCalcView> getIndustry(String locale, String industryCode) throws SystemException {
		// build the sql
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT a.industry_group_name INDUSTRY_NAME, a.industry_group_code INDUSTRY_CODE, b.item_code, b.numeric_value ");
		buffer.append("FROM  ifo_industry_group_code a, ");
		buffer.append("ifo_index_calc_view b, ");
		buffer.append("ifo_sector_code s ");
		buffer.append("WHERE a.industry_group_code = b.index_code ");
		buffer.append("AND a.sector_CODE = s.SECTOR_CODE ");
		buffer.append("AND UPPER(a.locale) = UPPER(:LOCALE) ");
		buffer.append("AND UPPER(s.locale) = UPPER(:LOCALE) ");
		buffer.append("AND item_code IN (1000017, 1000028, 1000029, 1000030, 1000031, 1000032) ");
		buffer.append("AND b.trans_date = (SELECT MAX (trans_date) ");
		buffer.append("FROM ifo_index_calc_view ");
		buffer.append("WHERE item_code IN (1000017)) ");
		buffer.append("AND a.industry_group_code = :INDUSTRY_CODE ");
		buffer.append("ORDER BY a.industry_group_name, item_code ");

		// add parameters
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("LOCALE", locale);
		paramMap.put("INDUSTRY_CODE", industryCode);

		// get the result from data source
		SearchResult<IfoIndustryCalcView> result = OracleDAOHelper.query(getDataSource(), buffer.toString(), paramMap,
		        new RowMapper<IfoSectorCalcView>() {

			        public IfoSectorCalcView mapRow(ResultSet rs, int arg1) throws SQLException {
				        IfoIndustryCalcView obj = new IfoIndustryCalcView();
				        obj.setIndustryName(rs.getString("INDUSTRY_NAME"));
				        obj.setIndustryCode(rs.getString("INDUSTRY_CODE"));
				        obj.setItemCode(rs.getString("ITEM_CODE"));
				        obj.setNumericValue(rs.getDouble("NUMERIC_VALUE"));
				        return obj;
			        }
		        });
		// transform the result
		Map<String, IfoIndustryCalcView> industry = new HashMap<String, IfoIndustryCalcView>();
		for (IfoIndustryCalcView elem : result) {
			industry.put(elem.getItemCode(), elem);
		}

		return industry;
	}

	/**
	 * Get all of industry for overview
	 * 
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @return list of the list industry that contains item code(1000017,
	 *         1000028, 1000029, 1000030, 1000031, 1000032) as a key
	 * @throws SystemException
	 */
	public List<Map<String, IfoIndustryCalcView>> getListOfIndustry(String locale, String sectorCode) throws SystemException {
		// build the sql
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT a.industry_group_name INDUSTRY_NAME, a.industry_group_code INDUSTRY_CODE, b.item_code, b.numeric_value ");
		buffer.append("FROM  ifo_industry_group_code a, ");
		buffer.append("ifo_index_calc_view b, ");
		buffer.append("ifo_sector_code s ");
		buffer.append("WHERE a.industry_group_code = b.index_code ");
		buffer.append("AND a.sector_CODE = s.SECTOR_CODE ");
		buffer.append("AND UPPER(a.locale) = UPPER(:LOCALE) ");
		buffer.append("AND UPPER(s.locale) = UPPER(:LOCALE) ");
		buffer.append("AND item_code IN (1000017, 1000028, 1000029, 1000030, 1000031, 1000032) ");
		buffer.append("AND b.trans_date = (SELECT MAX (trans_date) ");
		buffer.append("FROM ifo_index_calc_view ");
		buffer.append("WHERE item_code IN (1000017)) ");
		buffer.append("AND s.SECTOR_GROUP_CODE = :SECTOR_CODE ");
		buffer.append("ORDER BY a.industry_group_name, item_code ");

		// add parameters
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("LOCALE", locale);
		paramMap.put("SECTOR_CODE", sectorCode);

		// get the result from data source
		SearchResult<IfoIndustryCalcView> result = OracleDAOHelper.query(getDataSource(), buffer.toString(), paramMap,
		        new RowMapper<IfoSectorCalcView>() {

			        public IfoSectorCalcView mapRow(ResultSet rs, int arg1) throws SQLException {
				        IfoIndustryCalcView obj = new IfoIndustryCalcView();
				        obj.setIndustryName(rs.getString("INDUSTRY_NAME"));
				        obj.setIndustryCode(rs.getString("INDUSTRY_CODE"));
				        obj.setItemCode(rs.getString("ITEM_CODE"));
				        obj.setNumericValue(rs.getDouble("NUMERIC_VALUE"));
				        return obj;
			        }
		        });
		// transform the result
		List<Map<String, IfoIndustryCalcView>> industries = new ArrayList<Map<String, IfoIndustryCalcView>>();
		if (!CollectionUtils.isEmpty(result)) {
			String industryCode = result.get(0).getIndustryCode();
			Map<String, IfoIndustryCalcView> industry = new HashMap<String, IfoIndustryCalcView>();
			for (IfoIndustryCalcView elem : result) {
				String tmp = elem.getIndustryCode();
				if (tmp.equals(industryCode)) {
					industry.put(elem.getItemCode(), elem);
				} else {
					industries.add(industry);
					industry = new HashMap<String, IfoIndustryCalcView>();
					industryCode = elem.getIndustryCode();
					industry.put(elem.getItemCode(), elem);
				}
			}
			if (!industry.isEmpty()) {
				industries.add(industry);
			}
		}

		return industries;
	}

	/**
	 * Get top industry(The hottest industry that has the highest price after 5
	 * days) by sector code from database
	 * 
	 * @param type
	 *            <code>vn.com.vndirect.commons.Code</code>
	 * @param code
	 *            the sector code
	 * @return list of companies
	 * @throws SystemException
	 */
	public IfoIndustryCalcView getTopIndustry(String code, String locale) throws SystemException {
		// build the sql
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT ");
		buffer.append("a.industry_group_name INDUSTRY_NAME, ");
		buffer.append("a.industry_group_code INDUSTRY_CODE, ");
		buffer.append("b.item_code, ");
		buffer.append("b.numeric_value, ");
		buffer.append("s.sector_group_code SECTOR_CODE ");

		buffer.append("FROM  ifo_industry_group_code a, ");
		buffer.append("ifo_index_calc_view b, ");
		buffer.append("ifo_sector_code s ");
		buffer.append("WHERE a.industry_group_code = b.index_code ");
		buffer.append("AND a.sector_CODE = s.SECTOR_CODE ");
		buffer.append("AND a.locale = s.locale ");
		buffer.append("AND UPPER(s.locale) = UPPER(:LOCALE) ");
		buffer.append("AND item_code = '1000029' ");
		buffer.append("AND b.trans_date = (SELECT MAX (trans_date) ");
		buffer.append("FROM ifo_index_calc_view ");
		buffer.append("WHERE item_code = '1000029') ");
		buffer.append("AND s.sector_group_code = :SECTOR_CODE ");
		buffer.append("ORDER BY numeric_value DESC");

		// add parameters
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("LOCALE", locale);
		paramMap.put("SECTOR_CODE", code);

		// get the result from data source
		return (IfoIndustryCalcView) OracleDAOHelper.querySingle(getDataSource(), buffer.toString(), paramMap,
		        new RowMapper<IfoSectorCalcView>() {
			        public IfoSectorCalcView mapRow(ResultSet rs, int arg1) throws SQLException {
				        IfoIndustryCalcView obj = new IfoIndustryCalcView();
				        obj.setIndustryName(rs.getString("INDUSTRY_NAME"));
				        obj.setIndustryCode(rs.getString("INDUSTRY_CODE"));
				        obj.setSectorCode(rs.getString("SECTOR_CODE"));
				        obj.setItemCode(rs.getString("ITEM_CODE"));
				        obj.setNumericValue(rs.getDouble("NUMERIC_VALUE"));
				        return obj;
			        }
		        });
	}
}