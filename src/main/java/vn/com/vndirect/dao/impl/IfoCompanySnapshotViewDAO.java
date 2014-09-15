package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.dao.DAOUtils;
import vn.com.vndirect.domain.IfoCompanySnapshotViewId;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.IfoCompanySnapshotViewExt;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoCompanySnapshotView.
 * 
 * @see vn.com.vndirect.domain.IfoCompanySnapshotView
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings("rawtypes")
public class IfoCompanySnapshotViewDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoCompanySnapshotViewDAO.class);

	public static IfoCompanySnapshotViewDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IfoCompanySnapshotViewDAO) ctx.getBean("IfoCompanySnapshotViewDAO");
	}

	/**
	 * process all attributes of table
	 */
	private final RowMapper ifoCompanySnapshotViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoCompanySnapshotViewExt obj = new IfoCompanySnapshotViewExt();
			IfoCompanySnapshotViewId objId = new IfoCompanySnapshotViewId();

			objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objId.setExchangeCode(rs.getString("EXCHANGE_CODE"));
			objId.setWeekHigh(DAOUtils.valueOfDouble(rs.getString("WEEK_HIGH")));
			objId.setWeekLow(DAOUtils.valueOfDouble(rs.getString("WEEK_LOW")));
			objId.setMarketCapital(DAOUtils.valueOfDouble(rs.getString("MARKET_CAPITAL")));
			objId.setShareOutstanding(DAOUtils.valueOfDouble(rs.getString("SHARE_OUTSTANDING")));
			objId.setListedShares(DAOUtils.valueOfDouble(rs.getString("LISTED_SHARES")));
			objId.setDeclaredDividend(DAOUtils.valueOfDouble(rs.getString("DECLARED_DIVIDEND")));
			objId.setDividendYield(DAOUtils.valueOfDouble(rs.getString("DIVIDEND_YIELD")));
			objId.setExDividendDate(rs.getDate("EX_DIVIDEND_DATE"));
			objId.setDividendPayableDate(rs.getDate("DIVIDEND_PAYABLE_DATE"));
			objId.setEps(DAOUtils.valueOfDouble(rs.getString("EPS")));
			objId.setEpsYear(DAOUtils.valueOfDouble(rs.getString("YEAR_EPS")));
			objId.setRoa(DAOUtils.valueOfDouble(rs.getString("ROA")));
			objId.setRoe(DAOUtils.valueOfDouble(rs.getString("ROE")));
			objId.setLeverage(DAOUtils.valueOfDouble(rs.getString("LEVERAGE")));
			objId.setEarningsValue(DAOUtils.valueOfDouble(rs.getString("EARNINGS_VALUE")));
			objId.setBookValue(DAOUtils.valueOfDouble(rs.getString("BOOK_VALUE")));
			objId.setBeta(DAOUtils.valueOfDouble(rs.getString("BETA")));
			objId.setAverageVolumn(DAOUtils.valueOfDouble(rs.getString("AVERAGE_VOLUMN")));
			objId.setPe(DAOUtils.valueOfDouble(rs.getString("PE")));
			objId.setPb(DAOUtils.valueOfDouble(rs.getString("PB")));
			objId.setForeignOwnership(DAOUtils.valueOfDouble(rs.getString("FOREIGN_OWNERSHIP")));
			objId.setForeignSoldVol(DAOUtils.valueOfDouble(rs.getString("FOREIGN_SOLD_VOL")));
			objId.setForeignBoughtVol(DAOUtils.valueOfDouble(rs.getString("FOREIGN_BOUGHT_VOL")));
			objId.setLocale(rs.getString("LOCALE"));

			try {
				objId.setCompanyOverview(OracleDAOHelper.getClobColumn(rs, "COMPANY_OVERVIEW"));
			} catch (Exception e) {
				objId.setCompanyOverview("");
			}

			try {
				objId.setOverviewStatus(rs.getInt("OVERVIEW_STATUS"));
			} catch (Exception e) {
				objId.setOverviewStatus(100);
			}
			obj.setId(objId);
			return obj;
		}
	};

	public IfoCompanySnapshotViewExt findByCurrentCompany(CurrentCompanyForQuote criteriaObject) throws SystemException {
		final String LOCATION = "findByCurrentCompany(criteriaObject:" + criteriaObject + ")";
		log.debug(LOCATION + ":: BEGIN");

		IfoCompanySnapshotViewExt result = new IfoCompanySnapshotViewExt();
		try {

			// List params = new ArrayList();
			Map<String, Object> paramMap = new HashMap<String, Object>();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ifoCompanySnapshotView.* ");
			sql.append("FROM IFO_COMPANY_SNAPSHOT_VIEW ifoCompanySnapshotView ");

			// add companyId's value
			sql.append("WHERE ifoCompanySnapshotView.COMPANY_ID = :COMPANY_ID ");
			// params.add(new Parameter(":COMPANY_ID",
			// criteriaObject.getCompanyId()));
			paramMap.put("COMPANY_ID", criteriaObject.getCompanyId());

			// add ExchangeCode's value
			String str = criteriaObject.getLocale() == null ? "" : criteriaObject.getLocale().trim();
			if (str.length() > 0) {
				str = str.toUpperCase();
				sql.append("AND UPPER(ifoCompanySnapshotView.LOCALE) = :LOCALE ");
				// params.add(new Parameter(":LOCALE", str));
				paramMap.put("LOCALE", str);
			}

			// add locale
			str = criteriaObject.getCurrentExchangeCode() == null ? "" : criteriaObject.getCurrentExchangeCode().trim();
			if (str.length() > 0) {
				str = str.toUpperCase();
				sql.append("AND UPPER(ifoCompanySnapshotView.EXCHANGE_CODE) = :EXCHANGE_CODE ");
				// params.add(new Parameter(":EXCHANGE_CODE", str));
				paramMap.put("EXCHANGE_CODE", str);
			}

			log.debug(LOCATION + ":: " + sql.toString());

			// Connection conn = this.getCurrentConnection();
			// EasyStatement statement = new EasyStatement(sql.toString());
			//
			// result = (IfoCompanySnapshotViewExt)statement.getSingle(conn
			// , (Parameter[])params.toArray(new Parameter[params.size()])
			// , ifoCompanySnapshotViewProcessor);

			result = (IfoCompanySnapshotViewExt) OracleDAOHelper.querySingle(this.getDataSource(), sql.toString(), paramMap,
			        ifoCompanySnapshotViewRowMapper);

		} catch (RuntimeException sysex) {
			log.debug("RuntimeException: ", sysex);
			throw new SystemException(LOCATION, sysex);
		} catch (Exception ex) {
			throw new SystemException(LOCATION, ex);
		}

		log.debug(LOCATION + ":: END");
		return result;
	}

}