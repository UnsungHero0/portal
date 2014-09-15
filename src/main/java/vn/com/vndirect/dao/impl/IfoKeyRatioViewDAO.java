package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.dao.DAOUtils;
import vn.com.vndirect.domain.IfoKeyRatioView;
import vn.com.vndirect.domain.IfoKeyRatioViewId;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoKeyRatioView.
 * 
 * @see vn.com.vndirect.domain.IfoKeyRatioView
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings("unchecked")
public class IfoKeyRatioViewDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoKeyRatioViewDAO.class);

	// ////////////////////////////////// Using JDBC
	// /////////////////////////////////////

	/**
	 * process all attributes of table
	 */
	// private final ResultSetProcessor ifoKeyRatioViewProcessor = new
	// ResultSetProcessor() {
	// public Object processRow(ResultSet rs) throws SQLException {
	// IfoKeyRatioView obj = new IfoKeyRatioView();
	// IfoKeyRatioViewId objId = new IfoKeyRatioViewId();
	// objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
	// objId.setDataHeaderId(new Long(rs.getLong("DATA_HEADER_ID")));
	// objId.setCurrencyCode(rs.getString("CURRENCY_CODE"));
	// objId.setDisplayLevel(new Long(rs.getLong("DISPLAY_LEVEL")));
	// objId.setDisplayOrder(new Long(rs.getLong("DISPLAY_ORDER")));
	// // objId.setFiscalDate(rs.getDate("FISCAL_DATE"));
	// objId.setItemCode(new Long(rs.getLong("ITEM_CODE")));
	// objId.setItemName(rs.getString("ITEM_NAME"));
	// objId.setNumericValue(DAOUtils.valueOfDouble(rs.getString("NUMERIC_VALUE")));
	// objId.setTextValue(rs.getString("TEXT_VALUE"));
	// objId.setLocale(rs.getString("LOCALE"));
	// // objId.setReportDate(rs.getDate("REPORT_DATE"));
	// objId.setReportType(rs.getString("REPORT_TYPE"));
	//
	// obj.setId(objId);
	// return obj;
	// }
	// };
	@SuppressWarnings("rawtypes")
	private final RowMapper ifoKeyRatioViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoKeyRatioView obj = new IfoKeyRatioView();
			IfoKeyRatioViewId objId = new IfoKeyRatioViewId();
			objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objId.setDataHeaderId(new Long(rs.getLong("DATA_HEADER_ID")));
			objId.setCurrencyCode(rs.getString("CURRENCY_CODE"));
			objId.setDisplayLevel(new Long(rs.getLong("DISPLAY_LEVEL")));
			objId.setDisplayOrder(new Long(rs.getLong("DISPLAY_ORDER")));
			// objId.setFiscalDate(rs.getDate("FISCAL_DATE"));
			objId.setItemCode(rs.getString("ITEM_CODE"));
			objId.setItemName(rs.getString("ITEM_NAME"));
			objId.setNumericValue(DAOUtils.valueOfDouble(rs.getString("NUMERIC_VALUE")));
			objId.setTextValue(rs.getString("TEXT_VALUE"));
			objId.setLocale(rs.getString("LOCALE"));
			// objId.setReportDate(rs.getDate("REPORT_DATE"));
			objId.setReportType(rs.getString("REPORT_TYPE"));

			obj.setId(objId);
			return obj;
		}
	};

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SystemException
	 */
	public List<IfoKeyRatioView> findByCompanyId(IfoKeyRatioViewId id) throws SystemException {
		final String LOCATION = "findByCompanyId(id:" + id + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (id == null) {
			throw new SystemException(LOCATION, "KeyRatioViewId is NULL...");
		}
		try {
			final StringBuffer sqlBuffer = new StringBuffer("SELECT ifoKeyRatioView.* "
			        + " FROM IFO_KEY_RATIO_VIEW ifoKeyRatioView " + " WHERE (ifoKeyRatioView.COMPANY_ID = :COMPANY_ID) "
			        + " AND ((ifoKeyRatioView.display_level = 0)"
			        + " OR ((ifoKeyRatioView.numeric_value is not null) AND (ifoKeyRatioView.numeric_value <> 0))) ");

			// List params = new ArrayList();
			// params.add(new Parameter(":COMPANY_ID",
			// id.getCompanyId().longValue()));

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("COMPANY_ID", id.getCompanyId());
			if (id.getLocale() != null && id.getLocale().trim().length() > 0) {
				sqlBuffer.append(" AND (upper(ifoKeyRatioView.LOCALE)= :LOCAL)");
				paramMap.put("LOCAL", id.getLocale().toUpperCase());
				// params.add(new Parameter(":LOCAL",
				// id.getLocale().toUpperCase()));
			}

			// Connection conn = this.getCurrentConnection();
			// EasyStatement stat = new EasyStatement(sqlBuffer.toString());
			//
			// Collection obj = stat.getList(conn
			// , (Parameter[])params.toArray(new Parameter[params.size()])
			// , ifoKeyRatioViewProcessor);

			// SearchResult result =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// sqlBuffer.toString(), paramMap, ifoKeyRatioViewRowMapper, null);
			SearchResult<IfoKeyRatioView> result = OracleDAOHelper.query(this.getDataSource(), sqlBuffer.toString(), paramMap,
			        ifoKeyRatioViewRowMapper);

			log.debug(LOCATION + ":: END - result:" + result.size());
			return result;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(LOCATION, e);
		}
	}
}