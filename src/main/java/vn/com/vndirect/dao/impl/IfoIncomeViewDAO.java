package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.commons.utility.VNDirectDateUtils;
import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoIncomeViewId;
import vn.com.vndirect.domain.extend.IfoBalanceSheetSearch;
import vn.com.vndirect.domain.extend.IfoIncomeViewExt;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoIncomeView.
 * 
 * @see vn.com.vndirect.domain.IfoIncomeView
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IfoIncomeViewDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoIncomeViewDAO.class);

	/**
	 * process all attributes of table
	 */
	private final RowMapper ifoIncomeViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoIncomeViewExt obj = new IfoIncomeViewExt();
			IfoIncomeViewId objId = new IfoIncomeViewId();

			objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objId.setDataHeaderId(new Long(rs.getLong("DATA_HEADER_ID")));
			objId.setReportType(rs.getString("REPORT_TYPE"));
			objId.setReportDate(rs.getDate("REPORT_DATE"));
			objId.setFiscalDate(rs.getDate("FISCAL_DATE"));
			objId.setCurrencyCode(rs.getString("CURRENCY_CODE"));
			objId.setItemCode(new Long(rs.getString("ITEM_CODE")));
			objId.setItemName(rs.getString("ITEM_NAME"));
			obj.setStrNumericValue(rs.getString("NUMERIC_VALUE"));
			objId.setDisplayOrder(new Long(rs.getString("DISPLAY_ORDER")));
			objId.setDisplayLevel(new Long(rs.getString("DISPLAY_LEVEL")));

			obj.setId(objId);
			return obj;
		}
	};

	public List findByCurrentCompany(IfoBalanceSheetSearch searchObject) throws SystemException {
		final String LOCATION = "findByCurrentCompany(criteriaObject:" + searchObject + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");

		// List result = new ArrayList();
		try {
			// List params = new ArrayList();
			Map paramMap = new HashMap();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT distinct t0.* FROM IFO_INCOME_VIEW t0 ");
			sql.append("WHERE t0.COMPANY_ID = :COMPANY_ID ");
			sql.append("AND t0.REPORT_TYPE = :REPORT_TYPE ");
			paramMap.put("REPORT_TYPE", searchObject.getReportType());
			sql.append("AND UPPER(t0.LOCALE) = :LOCALE ");
			paramMap.put("LOCALE", searchObject.getLocale().toUpperCase());

			Date fiscalDateTo;
			Date fiscalDateFrom;
			if (!searchObject.isAnnual()) {
				String fiscalDateToStr = "";
				if ("Q1".equals(searchObject.getFiscalQuarter())) {
					fiscalDateToStr = "31/03/" + searchObject.getFiscalYear();
				} else if ("Q2".equals(searchObject.getFiscalQuarter())) {
					fiscalDateToStr = "30/06/" + searchObject.getFiscalYear();
				} else if ("Q3".equals(searchObject.getFiscalQuarter())) {
					fiscalDateToStr = "30/09/" + searchObject.getFiscalYear();
				} else if ("Q4".equals(searchObject.getFiscalQuarter())) {
					fiscalDateToStr = "31/12/" + searchObject.getFiscalYear();
				}
				fiscalDateTo = VNDirectDateUtils.stringToDate(fiscalDateToStr,
				        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY);
				fiscalDateFrom = VNDirectDateUtils.addMonth(fiscalDateTo, -3 * searchObject.getNumberTerm());
				fiscalDateFrom = VNDirectDateUtils.addDay(fiscalDateFrom, 2);
			} else {
				String fiscalDateToStr = "31/12/" + searchObject.getFiscalYear();
				fiscalDateTo = VNDirectDateUtils.stringToDate(fiscalDateToStr,
				        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY);
				fiscalDateFrom = VNDirectDateUtils.addYear(fiscalDateTo, -searchObject.getNumberTerm());
				fiscalDateFrom = VNDirectDateUtils.addDay(fiscalDateFrom, 2);
			}

			sql.append(" AND t0.FISCAL_DATE BETWEEN :FISCAL_DATE_FROM AND :FISCAL_DATE_TO ");
			sql.append(" ORDER BY t0.DISPLAY_ORDER, t0.FISCAL_DATE DESC ");

			paramMap.put("COMPANY_ID", searchObject.getCompanyId());
			paramMap.put("FISCAL_DATE_FROM", fiscalDateFrom);
			paramMap.put("FISCAL_DATE_TO", fiscalDateTo);

			SearchResult result = OracleDAOHelper.query(this.getDataSource(), sql.toString(), paramMap, ifoIncomeViewRowMapper,
			        null);

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END - result:" + result.size());
			return result;
		} catch (RuntimeException sysex) {
			throw new SystemException(LOCATION, sysex);
		} catch (Exception ex) {
			throw new SystemException(LOCATION, ex);
		}

	}

	private final RowMapper fiscalYearMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			return rs.getString("FISCAL_YEAR");
		}
	};

	/**
	 * 
	 * @param searchObject
	 * @return
	 * @throws SystemException
	 */
	public List fiscalYearList(IfoBalanceSheetSearch searchObject) throws SystemException {
		final String LOCATION = "fiscalYearList(criteriaObject:" + searchObject + " )";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			Map paramMap = new HashMap();
			StringBuffer sql = new StringBuffer(
			        "SELECT DISTINCT TO_CHAR(FISCAL_DATE, 'YYYY') AS FISCAL_YEAR FROM IFO_INCOME_VIEW WHERE COMPANY_ID = :COMPANY_ID AND REPORT_TYPE IN ('QUARTER','ANNUAL')  ORDER BY FISCAL_YEAR DESC");
			paramMap.put("COMPANY_ID", searchObject.getCompanyId());
			SearchResult result = OracleDAOHelper.query(this.getDataSource(), sql.toString(), paramMap, fiscalYearMapper);

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END - result: " + result.size());
			return result;
		} catch (RuntimeException sysex) {
			throw new SystemException(LOCATION, sysex);
		} catch (Exception ex) {
			throw new SystemException(LOCATION, ex);
		}
	}

	private final RowMapper fiscalQuarterMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			return rs.getString("FISCAL_QUARTER");
		}
	};

	/**
	 * 
	 * @param searchObject
	 * @return
	 * @throws SystemException
	 */
	public String fiscalQuarter(IfoBalanceSheetSearch searchObject) throws SystemException {
		final String LOCATION = "fiscalQuarter(criteriaObject:" + searchObject + " )";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			Map paramMap = new HashMap();
			StringBuffer sql = new StringBuffer(
			        "SELECT DECODE(TO_CHAR(FISCAL_DATE, 'MM'),'03', 'Q1','06', 'Q2','09', 'Q3','12', 'Q4') AS FISCAL_QUARTER FROM (SELECT FISCAL_DATE FROM IFO_INCOME_VIEW WHERE COMPANY_ID = :COMPANY_ID AND REPORT_TYPE = 'QUARTER'ORDER BY FISCAL_DATE DESC) WHERE ROWNUM =1");
			paramMap.put("COMPANY_ID", searchObject.getCompanyId());
			String result = (String) OracleDAOHelper.querySingle(this.getDataSource(), sql.toString(), paramMap,
			        fiscalQuarterMapper);

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END - result: " + result);
			return result == null ? "Q1" : result;
		} catch (RuntimeException sysex) {
			throw new SystemException(LOCATION, sysex);
		} catch (Exception ex) {
			throw new SystemException(LOCATION, ex);
		}
	}
}