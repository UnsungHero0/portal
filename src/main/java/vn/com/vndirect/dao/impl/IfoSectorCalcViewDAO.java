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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoIndustryCalcView;
import vn.com.vndirect.domain.IfoSectorCalcView;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class IfoSectorCalcViewDAO extends BaseDAOImpl {
	private static final Log log = LogFactory.getLog(IfoSectorCalcViewDAO.class);

	/**
     *
     */
	@SuppressWarnings("")
	private final RowMapper ifoSectorCalcViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {

			IfoSectorCalcView obj = new IfoSectorCalcView();
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

	/**
	 * 
	 * @param ifoSectorCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoSectorCalcView> getListSectors(IfoSectorCalcView sector) throws FunctionalException, SystemException {
		final String LOCATION = "getListSectors(ifoSectorCalcView: " + sector + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (sector == null) {
			throw new FunctionalException(LOCATION, "ifoSectorCalcView is NULL...");
		}
		log.debug(LOCATION + ":: Locale: " + sector.getLocale());
		try {
			String SEARCH_SQL = "SELECT * FROM IFO_SECTOR_CALC_VIEW WHERE UPPER(LOCALE) = UPPER(:LOCALE) ";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", sector.getLocale());

			if (!StringUtils.isEmpty(sector.getSortField())) {
				SEARCH_SQL += " ORDER BY " + sector.getSortField();
				if (!StringUtils.isEmpty(sector.getOrder())) {
					SEARCH_SQL += "ASC".equals(sector.getOrder()) ? " ASC" : " DESC";
				}
			} else {
				SEARCH_SQL += " ORDER BY UPPER(SECTOR_NAME)";
			}

			SearchResult<IfoSectorCalcView> results = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoSectorCalcViewRowMapper);

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
	 * @param ifoSectorCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public Collection<IfoSectorCalcView> downloadSectors(IfoSectorCalcView ifoSectorCalcView) throws FunctionalException,
	        SystemException {
		final String LOCATION = "downloadSectors(ifoSectorCalcView: " + ifoSectorCalcView + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoSectorCalcView == null) {
			throw new FunctionalException(LOCATION, "ifoSectorCalcView is NULL...");
		}
		log.error(LOCATION + ":: Locale: " + ifoSectorCalcView.getLocale());
		try {
			String SEARCH_SQL = "SELECT * FROM IFO_SECTOR_CALC_VIEW WHERE LOCALE = :LOCALE ";
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", ifoSectorCalcView.getLocale().toUpperCase());

			// ++++ sort by field name
			String sortField = "SECTOR_NAME";
			if (ifoSectorCalcView.getSortField() != null && ifoSectorCalcView.getSortField().trim().length() > 0) {
				// : update sort field late
				sortField = ifoSectorCalcView.getSortField();
			}
			SEARCH_SQL += " ORDER BY " + sortField;

			SearchResult<IfoSectorCalcView> results = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoSectorCalcViewRowMapper);

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

	// ////////////////////// For Caching ////////////////////////

	/**
	 *
	 */
	private final RowMapper ifoSectorNamesRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoSectorCalcView obj = new IfoSectorCalcView();
			obj.setSectorCode(rs.getString("SECTOR_CODE"));
			obj.setSectorName(rs.getString("SECTOR_NAME"));
			obj.setLocale(rs.getString("LOCALE"));
			return obj;
		}
	};

	private final RowMapper firstCharOfSectorNameRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			return rs.getString("FIRST_CHAR");
		}
	};

	private final String SQL_SELECT_FIRST_CHAR_SECTOR = "SELECT DISTINCT SUBSTR(SECTOR_NAME, 0, 1) AS FIRST_CHAR FROM IFO_INDUSTRY_CALC_VIEW WHERE  UPPER(LOCALE) = :LOCALE ORDER BY FIRST_CHAR ";

	/**
	 * 
	 * @param ifoSectorCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List getListFirstCharOfSectorName(IfoSectorCalcView ifoSectorCalcView) throws FunctionalException, SystemException {
		final String LOCATION = "getListFirstCharOfSectorName(ifoSectorCalcView:" + ifoSectorCalcView + ")";
		log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", ifoSectorCalcView.getLocale().toUpperCase());
			SearchResult results = OracleDAOHelper.query(this.getDataSource(), SQL_SELECT_FIRST_CHAR_SECTOR, paramMap,
			        firstCharOfSectorNameRowMapper);

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

	private final String SQL_SELECT_SECTOR_NAME_LIST = "SELECT DISTINCT SECTOR_CODE, SECTOR_NAME, LOCALE FROM IFO_SECTOR_CALC_VIEW WHERE UPPER(LOCALE) = UPPER(:LOCALE) ORDER BY SECTOR_NAME ";

	/**
	 * 
	 * @param ifoSectorCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List<IfoSectorCalcView> getListSectorsName(IfoSectorCalcView ifoSectorCalcView) throws FunctionalException,
	        SystemException {
		final String LOCATION = "getListSectorsName(ifoSectorCalcView:" + ifoSectorCalcView + ")";
		log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", ifoSectorCalcView.getLocale());
			SearchResult results = OracleDAOHelper.query(this.getDataSource(), SQL_SELECT_SECTOR_NAME_LIST, paramMap,
			        ifoSectorNamesRowMapper);

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
	 * @param ifoSectorCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public IfoSectorCalcView getIfoSectorCalcViewByCode(IfoSectorCalcView ifoSectorCalcView) throws FunctionalException,
	        SystemException {
		final String LOCATION = "getIfoSectorCalcViewByCode(ifoSectorCalcView: " + ifoSectorCalcView + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoSectorCalcView == null) {
			throw new FunctionalException(LOCATION, "ifoSectorCalcView is NULL...");
		}
		log.debug(LOCATION + ":: Locale: " + ifoSectorCalcView.getLocale());
		try {
			String SEARCH_SQL = "SELECT * FROM IFO_SECTOR_CALC_VIEW WHERE UPPER(LOCALE) = :LOCALE AND SECTOR_CODE = :SECTOR_CODE";
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", ifoSectorCalcView.getLocale().toUpperCase());
			paramMap.put("SECTOR_CODE", ifoSectorCalcView.getSectorCode());

			// ++++ sort by field name
			String sortField = "SECTOR_NAME";
			if (ifoSectorCalcView.getSortField() != null && ifoSectorCalcView.getSortField().trim().length() > 0) {
				// : update sort field late
				sortField = ifoSectorCalcView.getSortField();
			}
			SEARCH_SQL += " ORDER BY UPPER(" + sortField + ") ";

			IfoSectorCalcView results = (IfoSectorCalcView) OracleDAOHelper.querySingle(this.getDataSource(), SEARCH_SQL,
			        paramMap, ifoSectorCalcViewRowMapper);

			log.debug(LOCATION + ":: END - " + results);
			return results == null ? new IfoSectorCalcView() : results;
		} catch (RuntimeException re) {
			log.error(LOCATION + ":: RuntimeException " + re);
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			log.error(LOCATION + ":: Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * Get a sector for overview
	 * 
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @param sectorCode
	 * @return map of a sector that contains item code(1000017, 1000028,
	 *         1000029, 1000030, 1000031, 1000032) as a key
	 * @throws SystemException
	 */
	public Map<String, IfoSectorCalcView> getSector(String locale, String sectorCode) throws SystemException {
		// build the sql
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT sector_GROUP_name SECTOR_NAME, sector_GROUP_code SECTOR_CODE, item_code, numeric_value ");
		buffer.append("FROM ifo_sector_group_code a, ifo_index_calc_view b ");
		buffer.append("WHERE a.sector_GROUP_code = b.index_code ");
		buffer.append("AND UPPER(locale) = UPPER(:LOCALE) ");
		buffer.append("AND item_code IN (1000017, 1000028, 1000029, 1000030, 1000031, 1000032) ");
		buffer.append("AND b.trans_date = ");
		buffer.append("(SELECT MAX (trans_date) ");
		buffer.append("FROM ifo_index_calc_view ");
		buffer.append("WHERE item_code IN (1000017)) ");
		buffer.append("AND SECTOR_GROUP_CODE = :SECTOR_CODE ");
		buffer.append("ORDER BY sector_name, item_code");
		// add parameters
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("LOCALE", locale);
		paramMap.put("SECTOR_CODE", sectorCode);

		// get the result from data source
		SearchResult<IfoSectorCalcView> result = OracleDAOHelper.query(getDataSource(), buffer.toString(), paramMap,
		        new RowMapper<IfoSectorCalcView>() {
			        public IfoSectorCalcView mapRow(ResultSet rs, int arg1) throws SQLException {
				        IfoSectorCalcView obj = new IfoSectorCalcView();
				        obj.setSectorName(rs.getString("SECTOR_NAME"));
				        obj.setSectorCode(rs.getString("SECTOR_CODE"));
				        obj.setItemCode(rs.getString("ITEM_CODE"));
				        obj.setNumericValue(rs.getDouble("NUMERIC_VALUE"));
				        return obj;
			        }
		        });
		// transform the result
		Map<String, IfoSectorCalcView> sector = new HashMap<String, IfoSectorCalcView>();
		for (IfoSectorCalcView elem : result) {
			sector.put(elem.getItemCode(), elem);
		}

		return sector;
	}

	/**
	 * Get all of sector for overview
	 * 
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @return the list of map of a sector that contains item code(1000017,
	 *         1000028, 1000029, 1000030, 1000031, 1000032) as a key
	 * @throws SystemException
	 */
	public List<Map<String, IfoSectorCalcView>> getListOfSector(String locale) throws SystemException {
		// build the sql
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT sector_GROUP_name SECTOR_NAME, sector_GROUP_code SECTOR_CODE, item_code, numeric_value ");
		buffer.append("FROM ifo_sector_group_code a, ifo_index_calc_view b ");
		buffer.append("WHERE a.sector_GROUP_code = b.index_code ");
		buffer.append("AND UPPER(locale) = UPPER(:LOCALE) ");
		buffer.append("AND item_code IN (1000017, 1000028, 1000029, 1000030, 1000031, 1000032) ");
		buffer.append("AND b.trans_date = ");
		buffer.append("(SELECT MAX (trans_date) ");
		buffer.append("FROM ifo_index_calc_view ");
		buffer.append("WHERE item_code IN (1000017)) ");
		buffer.append("ORDER BY sector_name, item_code");
		// add parameters
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("LOCALE", locale);

		// get the result from data source
		SearchResult<IfoSectorCalcView> result = OracleDAOHelper.query(getDataSource(), buffer.toString(), paramMap,
		        new RowMapper<IfoSectorCalcView>() {
			        public IfoSectorCalcView mapRow(ResultSet rs, int arg1) throws SQLException {
				        IfoSectorCalcView obj = new IfoSectorCalcView();
				        obj.setSectorName(rs.getString("SECTOR_NAME"));
				        obj.setSectorCode(rs.getString("SECTOR_CODE"));
				        obj.setItemCode(rs.getString("ITEM_CODE"));
				        obj.setNumericValue(rs.getDouble("NUMERIC_VALUE"));
				        return obj;
			        }
		        });
		// transform the result
		List<Map<String, IfoSectorCalcView>> sectors = new ArrayList<Map<String, IfoSectorCalcView>>();
		if (!CollectionUtils.isEmpty(result)) {
			String sectorCode = result.get(0).getSectorCode();
			Map<String, IfoSectorCalcView> sector = new HashMap<String, IfoSectorCalcView>();
			for (IfoSectorCalcView elem : result) {
				String tmp = elem.getSectorCode();
				if (tmp.equals(sectorCode)) {
					sector.put(elem.getItemCode(), elem);
				} else {
					sectors.add(sector);
					sector = new HashMap<String, IfoSectorCalcView>();
					sectorCode = elem.getSectorCode();
					sector.put(elem.getItemCode(), elem);
				}
			}
			if (!sector.isEmpty()) {
				sectors.add(sector);
			}
		}

		return sectors;
	}

	/**
	 * Get a list of hot sectors
	 * 
	 * @param limit
	 *            number of sectors
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @return list of sectors
	 * @throws SystemException
	 */
	// @SuppressWarnings("unchecked")
	// public <T extends IfoSectorCalcView> List<T> getHotSectors(int limit,
	// String locale) throws SystemException {
	// //build the sql
	// StringBuffer buffer = new StringBuffer();
	//
	// buffer.append("select ");
	// buffer.append("  k.sector_GROUP_name SECTOR_NAME, ");
	// buffer.append("  k.sector_GROUP_code SECTOR_CODE, ");
	// buffer.append("  l.industry_group_name INDUSTRY_NAME, ");
	// buffer.append("  l.industry_group_code INDUSTRY_CODE, ");
	// buffer.append("  sector_value ");
	// buffer.append(" from ");
	// buffer.append(" ( ");
	// buffer.append("  select ");
	// buffer.append("    * from ");
	// buffer.append("        ( ");
	// buffer.append("        select ");
	// buffer.append("          sector_GROUP_name, ");
	// buffer.append("          sector_GROUP_code, ");
	// buffer.append("          numeric_value sector_value ");
	// buffer.append("        from ");
	// buffer.append("          ifo_sector_group_code a, ");
	// buffer.append("          IFO_INDEX_CALC_VIEW b ");
	// buffer.append("        where  ");
	// buffer.append("          a.sector_GROUP_code = b.index_code ");
	// buffer.append("          and item_code = '1000029' ");
	// buffer.append("          AND locale = UPPER(:LOCALE) ");
	// buffer.append("        order by ");
	// buffer.append("          numeric_value desc ");
	// buffer.append("        ) where rownum <= :LIMIT) k ");
	// buffer.append("    , ");
	// buffer.append("    ( ");
	// buffer.append("    SELECT ");
	// buffer.append("        a.industry_group_name, ");
	// buffer.append("        a.industry_group_code, ");
	// buffer.append("        b.item_code, ");
	// buffer.append("        b.numeric_value industry_value, ");
	// buffer.append("        s.sector_group_code ");
	// buffer.append("    FROM  ");
	// buffer.append("        ifo_industry_group_code a, ");
	// buffer.append("        ifo_index_calc_view b, ");
	// buffer.append("        ifo_sector_code s ");
	// buffer.append("    WHERE ");
	// buffer.append("        a.industry_group_code = b.index_code ");
	// buffer.append("        AND a.sector_CODE = s.SECTOR_CODE    ");
	// buffer.append("        AND a.locale = s.locale ");
	// buffer.append("        AND s.locale = UPPER(:LOCALE) ");
	// buffer.append("        AND item_code = '1000029' ");
	// buffer.append("        AND b.trans_date = ");
	// buffer.append("            (SELECT MAX (trans_date) ");
	// buffer.append("                        FROM ifo_index_calc_view ");
	// buffer.append("                        WHERE item_code = '1000029') ");
	// buffer.append("                    ");
	// buffer.append("    ORDER BY ");
	// buffer.append("        numeric_value DESC ) l ");
	// buffer.append(" where ");
	// buffer.append(" k.sector_GROUP_code = l.SECTOR_GROUP_CODE ");
	//
	// //add parameters
	// Map<String, Object> paramMap = new HashMap<String, Object>();
	// paramMap.put("LOCALE", locale);
	// paramMap.put("LIMIT", limit);
	//
	// //get the result from data source
	// List<T> result = OracleDAOHelper.query(
	// getDataSource(),
	// buffer.toString(),
	// paramMap,
	// new RowMapper<T>() {
	//
	// @Override
	// public T mapRow(ResultSet rs, int arg1)
	// throws SQLException {
	// IfoIndustryCalcView obj = new IfoIndustryCalcView();
	// obj.setSectorName(rs.getString("SECTOR_NAME"));
	// obj.setSectorCode(rs.getString("SECTOR_CODE"));
	// obj.setIndustryCode(rs.getString("INDUSTRY_CODE"));
	// obj.setIndustryName(rs.getString("INDUSTRY_NAME"));
	// obj.setNumericValue(rs.getDouble("SECTOR_VALUE"));
	// return (T) obj;
	// }
	// });
	// //select top industry only
	// List<T> lst = new ArrayList<T>();
	// String sectorCode = "";
	// for (T t : result) {
	// if (!sectorCode.equals(t.getSectorCode())) {
	// sectorCode = t.getSectorCode();
	// lst.add(t);
	// }
	// }
	//
	//
	// return lst;
	// }

	/**
	 * Get a list of hot sectors
	 * 
	 * @param limit
	 *            number of sectors
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @return list of sectors
	 * @throws SystemException
	 */
	public <T extends IfoSectorCalcView> List<T> getHotSectors(int limit, String locale) throws SystemException {
		final String LOCATION = "getHotSectors(limit: " + limit + "-- locale: )" + locale;
		if (logger.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		// build the sql
		StringBuffer buffer = new StringBuffer();

		buffer.append("SELECT * ");
		buffer.append("FROM ( ");
		buffer.append("  SELECT i.SECTOR_GROUP_CODE, i.SECTOR_GROUP_NAME, v.NUMERIC_VALUE ");
		buffer.append("  FROM ifo_index_calc_view v, ifo_sector_group_code i ");
		buffer.append("  WHERE  i.sector_GROUP_CODE = v.index_code ");
		buffer.append("  AND i.locale = UPPER(:LOCALE) ");
		buffer.append("  AND v.item_code = '1000029' ");
		buffer.append("  AND v.trans_date = (SELECT MAX (trans_date) FROM ifo_index_calc_view WHERE item_code = '1000029') ");
		buffer.append("  ORDER BY v.numeric_value desc) ");
		buffer.append("WHERE  rownum <= :LIMIT ");

		// add parameters
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("LOCALE", locale);
		paramMap.put("LIMIT", limit);

		// get the result from data source
		List<T> result = OracleDAOHelper.query(getDataSource(), buffer.toString(), paramMap, new RowMapper<T>() {
			public T mapRow(ResultSet rs, int arg1) throws SQLException {
				IfoIndustryCalcView obj = new IfoIndustryCalcView();
				obj.setSectorName(rs.getString("SECTOR_GROUP_NAME"));
				obj.setSectorCode(rs.getString("SECTOR_GROUP_CODE"));
				// obj.setIndustryCode(rs.getString("INDUSTRY_CODE"));
				// obj.setIndustryName(rs.getString("INDUSTRY_NAME"));
				obj.setNumericValue(rs.getDouble("NUMERIC_VALUE"));
				return (T) obj;
			}
		});
		// select top industry only
		// List<T> lst = new ArrayList<T>();
		// String sectorCode = "";
		// for (T t : result) {
		// if (!sectorCode.equals(t.getSectorCode())) {
		// sectorCode = t.getSectorCode();
		// lst.add(t);
		// }
		// }
		if (logger.isDebugEnabled())
			log.debug(LOCATION + ":: END");
		return result;
	}

	/**
	 * Get sectors which are high performance in three month
	 * 
	 * @param limit
	 *            number of sectors
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @return list of sectors
	 * @throws SystemException
	 */
	public List<IfoSectorCalcView> getThrMonHighPerfSectors(int limit, String locale) throws SystemException {
		// build the sql
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from (select sector_GROUP_name SECTOR_NAME, sector_GROUP_code SECTOR_CODE, numeric_value from ifo_sector_group_code a, IFO_INDEX_CALC_VIEW b ");
		buffer.append("where  a.sector_GROUP_code = b.index_code and item_code = 1000030 AND UPPER(locale) = UPPER(:LOCALE) AND b.trans_date = (SELECT MAX (trans_date)  FROM ifo_index_calc_view  WHERE item_code = '1000030') and numeric_value > 0 ");
		buffer.append("order by numeric_value desc) ");
		buffer.append("where rownum <= :LIMIT ");
		// add parameters
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("LOCALE", locale);
		paramMap.put("LIMIT", limit);

		// get the result from data source
		return OracleDAOHelper.query(getDataSource(), buffer.toString(), paramMap, new RowMapper<IfoSectorCalcView>() {
			public IfoSectorCalcView mapRow(ResultSet rs, int arg1) throws SQLException {
				IfoSectorCalcView obj = new IfoSectorCalcView();
				obj.setSectorName(rs.getString("SECTOR_NAME"));
				obj.setSectorCode(rs.getString("SECTOR_CODE"));
				obj.setNumericValue(rs.getDouble("NUMERIC_VALUE"));
				return obj;
			}
		});
	}

}