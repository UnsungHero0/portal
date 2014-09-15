package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoMarketCalendarView;
import vn.com.vndirect.domain.extend.SearchIfoMarketCalendar;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class IfoMarketCalendarViewDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoMarketCalendarViewDAO.class);

	// ++ Using SpringDAO ++ //
	private final RowMapper objMarketCalendarRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoMarketCalendarView objMarketCalendar = new IfoMarketCalendarView();
			objMarketCalendar.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objMarketCalendar.setSymbol(rs.getString("SYMBOL"));
			objMarketCalendar.setRegisterDate(rs.getDate("REGISTER_DATE"));
			objMarketCalendar.setEventDate(rs.getDate("EVENT_DATE"));

			objMarketCalendar.setEventType(rs.getString("EVENT_TYPE"));
			objMarketCalendar.setEventDesc(rs.getString("EVENT_DESC"));
			objMarketCalendar.setLocal(rs.getString("LOCALE"));
			objMarketCalendar.setEventNote(rs.getString("EVENT_NOTE"));

			try {
				objMarketCalendar.setRightsDate(rs.getDate("RIGHTS_DATE"));
			} catch (Exception ex) {

			}
			return objMarketCalendar;
		}
	};

	/**
	 * 
	 * @param ifoMarketCalendar
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<IfoMarketCalendarView> searchIfoMarketCalendar(IfoMarketCalendarView ifoMarketCalendar,
	        PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchIfoMarketCalendar(IfoMarketCalendarView:" + ifoMarketCalendar + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + " :: BEGIN");
		if (ifoMarketCalendar == null) {
			throw new SystemException(LOCATION, "ifoMarketCalendar is NULL...");
		}

		SearchResult<IfoMarketCalendarView> results = new SearchResult<IfoMarketCalendarView>();
		StringBuffer SQL_SEARCH = new StringBuffer(
		        "SELECT DISTINCT COMPANY_ID, SYMBOL, REGISTER_DATE, EVENT_DATE, EVENT_TYPE, EVENT_DESC, LOCALE, EVENT_NOTE, RIGHTS_DATE FROM IFO_MARKET_CALENDAR_VIEW ");
		Map paramMap = new HashMap();

		StringBuffer strWhereClase = new StringBuffer();
		String str;

		str = (ifoMarketCalendar.getSymbol() == null ? "" : ifoMarketCalendar.getSymbol().trim());
		if (str.length() > 0) {
			strWhereClase.append(" UPPER(SYMBOL) LIKE :SYMBOL");
			paramMap.put("SYMBOL", "%" + str.toUpperCase() + "%");
		} else if (ifoMarketCalendar.getListSymbols() != null && ifoMarketCalendar.getListSymbols().length > 0) {
			StringBuffer inSQL = new StringBuffer();
			for (int i = 0; i < ifoMarketCalendar.getListSymbols().length; i++) {
				inSQL.append(inSQL.length() == 0 ? "'" : ", '")
				        .append(ifoMarketCalendar.getListSymbols()[i].toUpperCase().trim()).append("'");
			}
			strWhereClase.append(" UPPER(SYMBOL) IN (" + inSQL.toString() + ") ");
		}
		str = (ifoMarketCalendar.getEventType() == null ? "" : ifoMarketCalendar.getEventType().trim());
		if (str.length() > 0) {
			if (str.equalsIgnoreCase("IPO3.8")) {
				strWhereClase.append(strWhereClase.length() == 0 ? "" : " AND ").append(
				        " EVENT_TYPE IN ('IPO3.8','IPO4.8','IPO5.7') ");
			} else {
				strWhereClase.append(strWhereClase.length() == 0 ? "" : " AND ").append(" EVENT_TYPE = :EVENT_TYPE ");
				paramMap.put("EVENT_TYPE", str);
			}
		}
		str = (ifoMarketCalendar.getLocal() == null ? "" : ifoMarketCalendar.getLocal().trim());
		if (str.length() > 0) {
			strWhereClase.append(strWhereClase.length() == 0 ? "" : " AND ").append(" UPPER(LOCALE) = :LOCALE ");
			// log.debug(":: LOCALE=" + str);
			paramMap.put("LOCALE", str.toUpperCase());
		}

		Calendar cal = Calendar.getInstance();
		if (ifoMarketCalendar.getRightsDate() != null) {
			cal.setTime(ifoMarketCalendar.getRightsDate());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			strWhereClase
			        .append(strWhereClase.length() == 0 ? "" : " AND ")
			        .append(" ((RIGHTS_DATE IS NOT NULL AND RIGHTS_DATE >= :RIGHTS_DATE1) OR (RIGHTS_DATE IS NULL AND EVENT_DATE >= :RIGHTS_DATE1))");
			paramMap.put("RIGHTS_DATE1", new Date(cal.getTimeInMillis()));

			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 999);
			strWhereClase
			        .append(" AND ((RIGHTS_DATE IS NOT NULL AND RIGHTS_DATE <= :RIGHTS_DATE2) OR (RIGHTS_DATE IS NULL AND EVENT_DATE <= :RIGHTS_DATE2))");
			paramMap.put("RIGHTS_DATE2", new Date(cal.getTimeInMillis()));
		}
		if (ifoMarketCalendar.getFromRightsDate() != null) {
			cal.setTime(ifoMarketCalendar.getFromRightsDate());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);

			strWhereClase.append(strWhereClase.length() == 0 ? "" : " AND ").append(" RIGHTS_DATE >= :FROM_RIGHTS_DATE ");
			// log.debug(":: FROM_RIGHTS_DATE=" + cal.getTime());
			paramMap.put("FROM_RIGHTS_DATE", new Date(cal.getTimeInMillis()));
		}
		if (ifoMarketCalendar.getToRightsDate() != null) {
			cal.setTime(ifoMarketCalendar.getToRightsDate());
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 999);

			strWhereClase.append(strWhereClase.length() == 0 ? "" : " AND ").append(" RIGHTS_DATE <= :TO_RIGHTS_DATE ");
			log.debug(":: TO_RIGHTS_DATE=" + cal.getTime());
			paramMap.put("TO_RIGHTS_DATE", new Date(cal.getTimeInMillis()));
		}

		// SQL_SEARCH.append(" WHERE ").append(strWhereClase).append(" ORDER BY RIGHTS_DATE DESC NULLS LAST, REGISTER_DATE DESC NULLS LAST, RIGHTS_DATE DESC NULLS LAST, SYMBOL, EVENT_TYPE ");
		SQL_SEARCH
		        .append(" WHERE ")
		        .append(strWhereClase)
		        .append(" ORDER BY EVENT_DATE DESC NULLS LAST, RIGHTS_DATE DESC NULLS LAST, REGISTER_DATE DESC NULLS LAST, SYMBOL, EVENT_TYPE ");

		if (log.isDebugEnabled())
			log.debug(LOCATION + " SQL_SEARCH :: " + SQL_SEARCH);
		try {
			results = OracleDAOHelper.queryWithPagging(this.getDataSource(), SQL_SEARCH.toString(), paramMap,
			        objMarketCalendarRowMapper, pagingInfo);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + results.size());
			return results;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	// ++ Using SpringDAO ++ //
	private final RowMapper eventDateByMonthRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			Date date = arg0.getDate("EVENT_DATE");
			return date;
		}
	};

	// ++ Using SpringDAO ++ //
	private final RowMapper rightsDateByMonthRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			Date date = arg0.getDate("RIGHTS_DATE");
			return date;
		}
	};

	public Collection getRightsDateByMonth(IfoMarketCalendarView ifoMarketCalendar) throws SystemException {
		final String LOCATION = "getRightsDateByMonth(IfoMarketCalendarView:" + ifoMarketCalendar + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + " :: BEGIN ");
		if (ifoMarketCalendar == null) {
			throw new SystemException(LOCATION, "ifoMarketCalendar is NULL...");
		}

		String SQL_SEARCH = "SELECT DISTINCT RIGHTS_DATE FROM IFO_MARKET_CALENDAR_VIEW WHERE RIGHTS_DATE >= :FROM_RIGHTS_DATE AND RIGHTS_DATE <= :TO_RIGHTS_DATE ORDER BY RIGHTS_DATE DESC";
		Map paramMap = new HashMap();

		Calendar cal = Calendar.getInstance();
		cal.setTime(ifoMarketCalendar.getFromRightsDate());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		paramMap.put("FROM_RIGHTS_DATE", new Date(cal.getTimeInMillis()));

		cal.setTime(ifoMarketCalendar.getToRightsDate());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		paramMap.put("TO_RIGHTS_DATE", new Date(cal.getTimeInMillis()));

		try {
			SearchResult searchResult = OracleDAOHelper.query(this.getDataSource(), SQL_SEARCH, paramMap,
			        rightsDateByMonthRowMapper);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : searchResult " + searchResult);
			return (searchResult == null ? new ArrayList() : searchResult);
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * List of event date
	 * 
	 * @param ifoMarketCalendar
	 * @return
	 * @throws SystemException
	 */
	public Collection getEventDateByMonth(IfoMarketCalendarView ifoMarketCalendar) throws SystemException {
		final String LOCATION = "getEventDateByMonth(IfoMarketCalendarView:" + ifoMarketCalendar + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + " :: BEGIN ");
		if (ifoMarketCalendar == null) {
			throw new SystemException(LOCATION, "ifoMarketCalendar is NULL...");
		}

		String SQL_SEARCH = "SELECT DISTINCT EVENT_DATE FROM IFO_MARKET_CALENDAR_VIEW WHERE EVENT_DATE >= :FROM_EVENT_DATE AND EVENT_DATE <= :TO_EVENT_DATE ORDER BY EVENT_DATE DESC";

		Map<String, Object> paramMap = new HashMap<String, Object>();

		Calendar cal = Calendar.getInstance();
		cal.setTime(ifoMarketCalendar.getFromEventDate());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		paramMap.put("FROM_EVENT_DATE", new Date(cal.getTimeInMillis()));

		cal.setTime(ifoMarketCalendar.getToEventDate());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		paramMap.put("TO_EVENT_DATE", new Date(cal.getTimeInMillis()));

		try {
			SearchResult searchResult = OracleDAOHelper.query(this.getDataSource(), SQL_SEARCH, paramMap,
			        eventDateByMonthRowMapper);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : searchResult " + searchResult);
			return (searchResult == null ? new ArrayList() : searchResult);
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param searchIfoMarketCalendar
	 * @return
	 * @throws SystemException
	 */
	public Collection searchSplitAndDividend(SearchIfoMarketCalendar searchIfoMarketCalendar) throws SystemException {
		final String LOCATION = "searchIfoMarketCalendar(IfoMarketCalendarView:" + searchIfoMarketCalendar + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + " :: BEGIN");
		if (searchIfoMarketCalendar == null) {
			throw new SystemException(LOCATION, "ifoMarketCalendar is NULL...");
		}
		// Collection results = new ArrayList();
		StringBuffer SQL_SEARCH = new StringBuffer(
		        "SELECT DISTINCT COMPANY_ID, SYMBOL, RIGHTS_DATE, REGISTER_DATE, EVENT_DATE, EVENT_TYPE, EVENT_DESC, LOCALE, EVENT_NOTE FROM IFO_MARKET_CALENDAR_VIEW ");
		// List params = new ArrayList();
		Map<String, Object> paramMap = new HashMap<String, Object>();

		StringBuffer strWhereClase = new StringBuffer();
		String str;

		str = (searchIfoMarketCalendar.getSymbol() == null ? "" : searchIfoMarketCalendar.getSymbol().trim());
		if (str.length() > 0) {
			strWhereClase.append(" UPPER(SYMBOL) LIKE :SYMBOL");
			// params.add(new Parameter(":SYMBOL", "%" + str.toUpperCase() +
			// "%"));
			paramMap.put("SYMBOL", "%" + str.toUpperCase() + "%");
		}

		str = (searchIfoMarketCalendar.getLang() == null ? "" : searchIfoMarketCalendar.getLang().trim());
		if (str.length() > 0) {
			strWhereClase.append(strWhereClase.length() == 0 ? "" : " AND ").append(" UPPER(LOCALE) = :LOCALE ");
			// logger.debug(":: LOCALE=" + str);
			// params.add(new Parameter(":LOCALE", str.toUpperCase()));
			paramMap.put("LOCALE", str.toUpperCase());
		}

		if (searchIfoMarketCalendar.getStartDate() != null) {
			strWhereClase.append(strWhereClase.length() == 0 ? "" : " AND ").append(" RIGHTS_DATE >= :START_DATE ");
			// params.add(new Parameter(":START_DATE",
			// searchIfoMarketCalendar.getStartDate()));
			paramMap.put("START_DATE", searchIfoMarketCalendar.getStartDate());
		}

		if (searchIfoMarketCalendar.getEndDate() != null) {
			strWhereClase.append(strWhereClase.length() == 0 ? "" : " AND ").append(" RIGHTS_DATE <= :END_DATE ");
			// params.add(new Parameter(":END_DATE",
			// searchIfoMarketCalendar.getEndDate()));
			paramMap.put("END_DATE", searchIfoMarketCalendar.getEndDate());
		}

		SQL_SEARCH
		        .append(" WHERE ")
		        .append(strWhereClase)
		        .append(" ORDER BY RIGHTS_DATE DESC NULLS LAST, REGISTER_DATE DESC NULLS LAST, EVENT_DATE DESC NULLS LAST, SYMBOL, EVENT_TYPE ");

		// log.debug(LOCATION + " SQL_SEARCH :: " + SQL_SEARCH);
		try {
			SearchResult searchResult = OracleDAOHelper.query(this.getDataSource(), SQL_SEARCH.toString(), paramMap,
			        objMarketCalendarRowMapper);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : searchResult " + searchResult);
			return (searchResult == null ? new ArrayList() : searchResult);
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
}
