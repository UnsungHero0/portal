package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoPowerRatingBean;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

public class IfoPowerRatingViewDAO extends BaseDAOImpl {
	private static final Log log = LogFactory.getLog(IfoPowerRatingViewDAO.class);

	/**
	 * 
	 * @param ifoSectorCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */

	@SuppressWarnings("rawtypes")
	private RowMapper prRowMapper = new RowMapper<IfoPowerRatingBean>() {
		public IfoPowerRatingBean mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoPowerRatingBean obj = new IfoPowerRatingBean();
			obj.setSymbol(rs.getString("SEC_CODE"));
			obj.setMark(rs.getInt("MARK"));
			obj.setClosePrice(rs.getDouble("CLOSE_PRICE"));
			obj.setTransDate(rs.getDate("TRANS_DATE"));
			obj.setMarkB4days(rs.getInt("MARK_B4DAYS"));
			obj.setClosePriceB4days(rs.getDouble("CLOSE_PRICE_B4DAYS"));
			obj.setPctMarkChange(rs.getDouble("PCT_MARK_CHANGE"));
			obj.setPctPriceChange(rs.getDouble("PCT_PRICE_CHANGE"));
			return obj;
		}
	};
	/*
	 * private static String SEARCH_SQL_TOP_10 =
	 * "SELECT * FROM ( SELECT * FROM  IFO_POWER_RATING_VIEW WHERE TRANS_DATE IN ( SELECT MAX(TRANS_DATE) FROM IFO_POWER_RATING_VIEW ) AND SEC_CODE NOT IN ('VNIndex', 'HNX') ORDER BY MARK DESC) WHERE ROWNUM < 11"
	 * ;
	 * 
	 * @SuppressWarnings("unchecked") public List<IfoPowerRatingBean>
	 * getTopTenPr() throws FunctionalException, SystemException { final String
	 * LOCATION = "getTopTenPr( )"; if (log.isDebugEnabled()) log.debug(LOCATION
	 * + ":: BEGIN"); try { // get most recent power rating list
	 * List<IfoPowerRatingBean> todayList =
	 * OracleDAOHelper.query(getDataSource(), SEARCH_SQL_TOP_10, new
	 * HashMap<String, Object>(), prRowMapper);
	 * 
	 * if (log.isDebugEnabled()) log.debug(LOCATION + ":: END - " + todayList);
	 * return todayList; } catch (RuntimeException re) { log.error(LOCATION +
	 * ":: RuntimeException " + re); throw new SystemException(LOCATION, re); }
	 * catch (Exception e) { log.error(LOCATION + ":: Exception " + e); throw
	 * new SystemException(LOCATION, e); } }
	 * 
	 * private static String SEARCH_SQL_BOTTOM_10 =
	 * "SELECT * FROM ( SELECT * FROM  IFO_POWER_RATING_VIEW WHERE TRANS_DATE IN ( SELECT MAX(TRANS_DATE) FROM IFO_POWER_RATING_VIEW ) AND SEC_CODE NOT IN ('VNIndex', 'HNX') ORDER BY MARK ASC) WHERE ROWNUM < 11"
	 * ;
	 * 
	 * @SuppressWarnings("unchecked") public List<IfoPowerRatingBean>
	 * getBottomTenPr() throws FunctionalException, SystemException { final
	 * String LOCATION = "getBottomTenPr( )"; if (log.isDebugEnabled())
	 * log.debug(LOCATION + ":: BEGIN"); try {
	 * 
	 * List<IfoPowerRatingBean> todayList =
	 * OracleDAOHelper.query(getDataSource(), SEARCH_SQL_BOTTOM_10, new
	 * HashMap<String, Object>(), prRowMapper); if (log.isDebugEnabled())
	 * log.debug(LOCATION + ":: END - " + todayList); return todayList; } catch
	 * (RuntimeException re) { log.error(LOCATION + ":: RuntimeException " +
	 * re); throw new SystemException(LOCATION, re); } catch (Exception e) {
	 * log.error(LOCATION + ":: Exception " + e); throw new
	 * SystemException(LOCATION, e); } }
	 * 
	 * private static String SEARCH_SQL_TOP_10_UP =
	 * "SELECT * FROM ( SELECT * FROM  IFO_POWER_RATING_VIEW WHERE TRANS_DATE IN ( SELECT MAX(TRANS_DATE) FROM IFO_POWER_RATING_VIEW ) AND SEC_CODE NOT IN ('VNIndex', 'HNX') ORDER BY PCT_MARK_CHANGE DESC NULLS LAST, MARK DESC) WHERE ROWNUM < 11"
	 * ;
	 * 
	 * @SuppressWarnings("unchecked") public List<IfoPowerRatingBean>
	 * getUpgradedTenPr() throws FunctionalException, SystemException { final
	 * String LOCATION = "getUpgradedTenPr( )"; if (log.isDebugEnabled())
	 * log.debug(LOCATION + ":: BEGIN"); try { List<IfoPowerRatingBean>
	 * todayList = OracleDAOHelper.query(getDataSource(), SEARCH_SQL_TOP_10_UP,
	 * new HashMap<String, Object>(), prRowMapper);
	 * 
	 * if (log.isDebugEnabled()) log.debug(LOCATION + ":: END - " + todayList);
	 * return todayList; } catch (RuntimeException re) { log.error(LOCATION +
	 * ":: RuntimeException " + re); throw new SystemException(LOCATION, re); }
	 * catch (Exception e) { log.error(LOCATION + ":: Exception " + e); throw
	 * new SystemException(LOCATION, e); } }
	 * 
	 * private static String SEARCH_SQL_TOP_10_DOWN =
	 * "SELECT * FROM ( SELECT * FROM  IFO_POWER_RATING_VIEW WHERE TRANS_DATE IN ( SELECT MAX(TRANS_DATE) FROM IFO_POWER_RATING_VIEW ) AND SEC_CODE NOT IN ('VNIndex', 'HNX') ORDER BY PCT_MARK_CHANGE ASC NULLS LAST, MARK DESC) WHERE ROWNUM < 11"
	 * ;
	 * 
	 * @SuppressWarnings("unchecked") public List<IfoPowerRatingBean>
	 * getDowngradedTenPr() throws FunctionalException, SystemException { final
	 * String LOCATION = "getDowngradedTenPr( )"; if (log.isDebugEnabled())
	 * log.debug(LOCATION + ":: BEGIN"); try { List<IfoPowerRatingBean>
	 * todayList = OracleDAOHelper.query(getDataSource(),
	 * SEARCH_SQL_TOP_10_DOWN, new HashMap<String, Object>(), prRowMapper);
	 * 
	 * if (log.isDebugEnabled()) log.debug(LOCATION + ":: END - " + todayList);
	 * return todayList; } catch (RuntimeException re) { log.error(LOCATION +
	 * ":: RuntimeException " + re); throw new SystemException(LOCATION, re); }
	 * catch (Exception e) { log.error(LOCATION + ":: Exception " + e); throw
	 * new SystemException(LOCATION, e); } }
	 * 
	 * private static String SEARCH_SQL_MARKET =
	 * "SELECT * FROM ( SELECT * FROM  IFO_POWER_RATING_VIEW WHERE TRANS_DATE IN ( SELECT MAX(TRANS_DATE) FROM IFO_POWER_RATING_VIEW ) AND SEC_CODE IN ('VNIndex', 'HNX') ORDER BY SEC_CODE ASC) WHERE ROWNUM < 11"
	 * ;
	 * 
	 * @SuppressWarnings("unchecked") public List<IfoPowerRatingBean>
	 * getGeneralMarketPr() throws FunctionalException, SystemException { final
	 * String LOCATION = "getGeneralMarketPr( )"; if (log.isDebugEnabled())
	 * log.debug(LOCATION + ":: BEGIN"); try { List<IfoPowerRatingBean>
	 * todayList = OracleDAOHelper.query(getDataSource(), SEARCH_SQL_MARKET, new
	 * HashMap<String, Object>(), prRowMapper);
	 * 
	 * if (log.isDebugEnabled()) log.debug(LOCATION + ":: END - " + todayList);
	 * return todayList; } catch (RuntimeException re) { log.error(LOCATION +
	 * ":: RuntimeException " + re); throw new SystemException(LOCATION, re); }
	 * catch (Exception e) { log.error(LOCATION + ":: Exception " + e); throw
	 * new SystemException(LOCATION, e); } }
	 * 
	 * private static String SEARCH_SQL_COUNT =
	 * "SELECT COUNT(*) FROM  IFO_POWER_RATING_VIEW WHERE TRANS_DATE IN ( SELECT MAX(TRANS_DATE) FROM IFO_POWER_RATING_VIEW ) AND SEC_CODE NOT IN ('VNIndex', 'HNX')"
	 * ;
	 * 
	 * public int getRatedCodeNumber() throws FunctionalException,
	 * SystemException { final String LOCATION = "getGeneralMarketPr( )"; if
	 * (log.isDebugEnabled()) log.debug(LOCATION + ":: BEGIN"); try { // get
	 * most recent power rating list int cntRated =
	 * OracleDAOHelper.query4Int(getDataSource(), SEARCH_SQL_COUNT, new
	 * HashMap<String, Object>()); if (log.isDebugEnabled()) log.debug(LOCATION
	 * + ":: END"); return cntRated; } catch (RuntimeException re) {
	 * log.error(LOCATION + ":: RuntimeException " + re); throw new
	 * SystemException(LOCATION, re); } catch (Exception e) { log.error(LOCATION
	 * + ":: Exception " + e); throw new SystemException(LOCATION, e); } }
	 * 
	 * private static String SEARCH_SQL_AVG_MARK =
	 * "SELECT AVG(MARK) AS AVG_MARK FROM  IFO_POWER_RATING_VIEW WHERE TRANS_DATE IN ( SELECT MAX(TRANS_DATE) FROM IFO_POWER_RATING_VIEW ) AND SEC_CODE NOT IN ('VNIndex', 'HNX')"
	 * ;
	 * 
	 * public double getAvgGeneralMarketPr() throws FunctionalException,
	 * SystemException { final String LOCATION = "getAvgGeneralMarketPr( )"; if
	 * (log.isDebugEnabled()) log.debug(LOCATION + ":: BEGIN"); try { // get
	 * most recent power rating list Double todayAvg =
	 * OracleDAOHelper.query4Double(getDataSource(), SEARCH_SQL_AVG_MARK, new
	 * HashMap<String, Object>());
	 * 
	 * if (log.isDebugEnabled()) log.debug(LOCATION + ":: END"); return
	 * todayAvg; } catch (RuntimeException re) { log.error(LOCATION +
	 * ":: RuntimeException " + re); throw new SystemException(LOCATION, re); }
	 * catch (Exception e) { log.error(LOCATION + ":: Exception " + e); throw
	 * new SystemException(LOCATION, e); } }
	 * 
	 * private static String SEARCH_SQL_LIST_4_DAYS =
	 * "SELECT * FROM ( SELECT * FROM IFO_POWER_RATING_VIEW WHERE TRANS_DATE IN ( SELECT TRANS_DATE FROM ( SELECT TRANS_DATE, ROWNUM R FROM ( SELECT DISTINCT TRANS_DATE FROM IFO_POWER_RATING_VIEW ORDER BY TRANS_DATE DESC)) WHERE R = 5))"
	 * ;
	 * 
	 * @SuppressWarnings("unchecked") public List<IfoPowerRatingBean>
	 * getFourDayPrs() throws FunctionalException, SystemException { final
	 * String LOCATION = "getFourDayPrs( )"; if (log.isDebugEnabled())
	 * log.debug(LOCATION + ":: BEGIN"); try { List<IfoPowerRatingBean> result =
	 * OracleDAOHelper.query(getDataSource(), SEARCH_SQL_LIST_4_DAYS, new
	 * HashMap<String, Object>(), prRowMapper); if (log.isDebugEnabled())
	 * log.debug(LOCATION + ":: END - " + result); return result; } catch
	 * (RuntimeException re) { log.error(LOCATION + ":: RuntimeException " +
	 * re); throw new SystemException(LOCATION, re); } catch (Exception e) {
	 * log.error(LOCATION + ":: Exception " + e); throw new
	 * SystemException(LOCATION, e); } }
	 */
	private static String SEARCH_SQL_LIST = "SELECT * FROM  IFO_POWER_RATING_VIEW WHERE TRANS_DATE = (SELECT MAX(TRANS_DATE) FROM IFO_POWER_RATING_VIEW) AND MARK IS NOT NULL ORDER BY MARK DESC, PCT_PRICE_CHANGE DESC";

	@SuppressWarnings("unchecked")
	public List<IfoPowerRatingBean> getListOfPrs() throws FunctionalException, SystemException {
		final String LOCATION = "getListOfPrs( )";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			// get most recent power rating list
			List<IfoPowerRatingBean> result = OracleDAOHelper.query(getDataSource(), SEARCH_SQL_LIST,
			        new HashMap<String, Object>(), prRowMapper);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END - " + result);
			return result;
		} catch (RuntimeException re) {
			log.error(LOCATION + ":: RuntimeException " + re);
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			log.error(LOCATION + ":: Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

}
