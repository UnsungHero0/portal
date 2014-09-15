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
import vn.com.vndirect.domain.IfoInvestorRightsView;
import vn.com.vndirect.domain.IfoInvestorRightsViewId;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

@SuppressWarnings("unchecked")
public class IfoInvestorRightsViewDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoInvestorRightsViewDAO.class);

	// ////////////////////////////////// Using JDBC
	// /////////////////////////////////////

	/**
	 * process all attributes of table
	 */
	// private final ResultSetProcessor IfoInvestorRightsViewProcessor = new
	// ResultSetProcessor() {
	// public Object processRow(ResultSet rs) throws SQLException {
	// IfoInvestorRightsView obj = new IfoInvestorRightsView();
	// IfoInvestorRightsViewId objId = new IfoInvestorRightsViewId();
	//
	// objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
	// objId.setLocale(rs.getString("LOCALE"));
	// try {
	// objId.setRatio(DAOUtils.valueOfDouble(rs.getString("RATIO")));
	// } catch(Exception e) {
	// }
	// objId.setSecCode(rs.getString("SEC_CODE"));
	// objId.setSharesActionDesc(rs.getString("SHARES_ACTION_DESC"));
	// objId.setXdate(rs.getDate("X_DATE"));
	//
	// obj.setId(objId);
	// return obj;
	// }
	// };
	//
	@SuppressWarnings("rawtypes")
	private final RowMapper IfoInvestorRightsViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoInvestorRightsView obj = new IfoInvestorRightsView();
			IfoInvestorRightsViewId objId = new IfoInvestorRightsViewId();

			objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objId.setLocale(rs.getString("LOCALE"));
			try {
				objId.setRatio(DAOUtils.valueOfDouble(rs.getString("RATIO")));
			} catch (Exception e) {
			}
			objId.setSecCode(rs.getString("SEC_CODE"));
			objId.setSharesActionDesc(rs.getString("SHARES_ACTION_DESC"));
			objId.setXdate(rs.getDate("X_DATE"));

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
	public List<IfoInvestorRightsView> findByCompanyId(IfoInvestorRightsViewId id) throws SystemException {
		final String LOCATION = "findByCompanyId(id:" + id + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (id == null) {
			throw new SystemException(LOCATION, "IfoInvestorRightsView is NULL...");
		}
		try {
			final StringBuffer sqlBuffer = new StringBuffer(
			        "SELECT IfoInvestorRightsView.* FROM IFO_INVESTOR_RIGHTS_VIEW ifoInvestorRightsView WHERE (ifoInvestorRightsView.COMPANY_ID = :COMPANY_ID) ");

			// List params = new ArrayList();
			// params.add(new Parameter(":COMPANY_ID",
			// id.getCompanyId().longValue()));

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("COMPANY_ID", id.getCompanyId());

			if (id.getLocale() != null && id.getLocale().trim().length() > 0) {
				sqlBuffer.append(" AND (upper(ifoInvestorRightsView.LOCALE)= :LOCAL)");
				paramMap.put("LOCAL", id.getLocale().toUpperCase());
				// params.add(new Parameter(":LOCAL",
				// id.getLocale().toUpperCase()));

			}

			sqlBuffer.append(" ORDER by ifoInvestorRightsView.X_DATE DESC ");

			// Connection conn = this.getCurrentConnection();
			// EasyStatement stat = new EasyStatement(sqlBuffer.toString());
			//
			// Collection obj = stat.getList(conn
			// , (Parameter[])params.toArray(new Parameter[params.size()])
			// , IfoInvestorRightsViewProcessor);
			// SearchResult result =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// sqlBuffer.toString(), paramMap, IfoInvestorRightsViewRowMapper,
			// null);
			SearchResult<IfoInvestorRightsView> result = OracleDAOHelper.query(this.getDataSource(), sqlBuffer.toString(),
			        paramMap, IfoInvestorRightsViewRowMapper);

			log.debug(LOCATION + ":: END - result: " + result.size());
			return result;
		} catch (RuntimeException re) {
			log.debug(LOCATION, re);
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			log.debug(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}
}