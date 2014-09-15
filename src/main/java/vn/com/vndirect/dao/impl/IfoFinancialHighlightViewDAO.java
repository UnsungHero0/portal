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
import vn.com.vndirect.domain.IfoFinancialHighlightView;
import vn.com.vndirect.domain.IfoFinancialHighlightViewId;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoFinancialHighlightView.
 * 
 * @see vn.com.vndirect.domain.IfoFinancialHighlightView
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings("unchecked")
public class IfoFinancialHighlightViewDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoFinancialHighlightViewDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	// ////////////////////////////////// Using JDBC
	// /////////////////////////////////////

	/**
	 * process all attributes of table
	 */
	// private final ResultSetProcessor ifoFinancialHighlightViewProcessor = new
	// ResultSetProcessor() {
	// public Object processRow(ResultSet rs) throws SQLException {
	// IfoFinancialHighlightView obj = new IfoFinancialHighlightView();
	// IfoFinancialHighlightViewId objId = new IfoFinancialHighlightViewId();
	//
	// objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
	// objId.setDataHeaderId(new Long(rs.getLong("DATA_HEADER_ID")));
	// objId.setCurrencyCode(rs.getString("CURRENCY_CODE"));
	// objId.setDisplayLevel(new Long(rs.getLong("DISPLAY_LEVEL")));
	// objId.setDisplayOrder(new Long(rs.getLong("DISPLAY_ORDER")));
	// objId.setFiscalDate(rs.getDate("FISCAL_DATE"));
	// objId.setItemCode(new Long(rs.getLong("ITEM_CODE")));
	// objId.setItemName(rs.getString("ITEM_NAME"));
	// objId.setLocale(rs.getString("LOCALE"));
	// objId.setNumericValue(new Long(rs.getLong("NUMERIC_VALUE")));
	// objId.setReportDate(rs.getDate("REPORT_DATE"));
	// objId.setReportType(rs.getString("REPORT_TYPE"));
	// objId.setTextValue(rs.getString("TEXT_VALUE"));
	//
	// obj.setId(objId);
	// return obj;
	// }
	// };
	@SuppressWarnings("rawtypes")
	private final RowMapper ifoFinancialHighlightViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoFinancialHighlightView obj = new IfoFinancialHighlightView();
			IfoFinancialHighlightViewId objId = new IfoFinancialHighlightViewId();

			objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objId.setDataHeaderId(new Long(rs.getLong("DATA_HEADER_ID")));
			objId.setCurrencyCode(rs.getString("CURRENCY_CODE"));
			objId.setDisplayLevel(new Long(rs.getLong("DISPLAY_LEVEL")));
			objId.setDisplayOrder(new Long(rs.getLong("DISPLAY_ORDER")));
			objId.setFiscalDate(rs.getDate("FISCAL_DATE"));
			objId.setItemCode(new Long(rs.getLong("ITEM_CODE")));
			objId.setItemName(rs.getString("ITEM_NAME"));
			objId.setLocale(rs.getString("LOCALE"));
			objId.setNumericValue(new Long(rs.getLong("NUMERIC_VALUE")));
			objId.setReportDate(rs.getDate("REPORT_DATE"));
			objId.setReportType(rs.getString("REPORT_TYPE"));
			objId.setTextValue(rs.getString("TEXT_VALUE"));

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
	public List<IfoFinancialHighlightView> findByCompanyId(IfoFinancialHighlightViewId id) throws SystemException {
		final String LOCATION = "findByCompanyId(id:" + id + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (id == null) {
			throw new SystemException(LOCATION, "IfoFinancialHighlightView is NULL...");
		}
		try {
			final StringBuffer sqlBuffer = new StringBuffer(
			        "SELECT ifoFinancialHighlightView.* "
			                + " FROM IFO_FINANCIAL_HIGHLIGHT_VIEW ifoFinancialHighlightView "
			                + " WHERE (ifoFinancialHighlightView.COMPANY_ID = :COMPANY_ID) "
			                + " AND ((ifoFinancialHighlightView.display_level = 0)"
			                + " OR ((ifoFinancialHighlightView.numeric_value is not null) AND (ifoFinancialHighlightView.numeric_value <> 0))) ");

			// List params = new ArrayList();
			// params.add(new Parameter(":COMPANY_ID",
			// id.getCompanyId().longValue()));
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("COMPANY_ID", id.getCompanyId());

			if (id.getLocale() != null && id.getLocale().trim().length() > 0) {
				sqlBuffer.append(" AND (upper(ifoFinancialHighlightView.LOCALE)= :LOCAL) ");
				paramMap.put("LOCAL", id.getLocale().toUpperCase());
				// params.add(new Parameter(":LOCAL",
				// id.getLocale().toUpperCase()));
			}

			sqlBuffer.append(" ORDER by ifoFinancialHighlightView.DISPLAY_ORDER ASC ");

			// Connection conn = this.getCurrentConnection();
			// EasyStatement stat = new EasyStatement(sqlBuffer.toString());
			//
			// Collection obj = stat.getList(conn
			// , (Parameter[])params.toArray(new Parameter[params.size()])
			// , ifoFinancialHighlightViewProcessor);

			// SearchResult result =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// sqlBuffer.toString(), paramMap,
			// ifoFinancialHighlightViewRowMapper, null);
			SearchResult<IfoFinancialHighlightView> result = OracleDAOHelper.query(this.getDataSource(), sqlBuffer.toString(),
			        paramMap, ifoFinancialHighlightViewRowMapper);

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