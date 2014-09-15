package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoCompanyOfficersView;
import vn.com.vndirect.domain.IfoCompanyOfficersViewId;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoCompanyOfficersView.
 * 
 * @see vn.com.vndirect.domain.IfoCompanyOfficersView
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IfoCompanyOfficersViewDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoCompanyOfficersViewDAO.class);

	// ////////////////////////////////// Using JDBC
	// /////////////////////////////////////

	/**
	 * process all attributes of table
	 */
	// private final ResultSetProcessor ifoCompanyOfficesViewProcessor = new
	// ResultSetProcessor() {
	// public Object processRow(ResultSet rs) throws SQLException {
	// IfoCompanyOfficersView obj = new IfoCompanyOfficersView();
	// IfoCompanyOfficersViewId objId = new IfoCompanyOfficersViewId();
	// objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
	// objId.setName(rs.getString("NAME"));
	// objId.setPosition(rs.getString("POSITION"));
	// objId.setLocale(rs.getString("LOCALE"));
	// objId.setDisplayOrder(Utility.getLong(rs.getLong("DISPLAY_ORDER")));
	//
	// obj.setId(objId);
	// return obj;
	// }
	// };
	private final RowMapper ifoCompanyOfficesViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoCompanyOfficersView obj = new IfoCompanyOfficersView();
			IfoCompanyOfficersViewId objId = new IfoCompanyOfficersViewId();
			objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objId.setName(rs.getString("NAME"));
			objId.setPosition(rs.getString("POSITION"));
			objId.setLocale(rs.getString("LOCALE"));
			objId.setDisplayOrder(VNDirectWebUtilities.getLong(rs.getLong("DISPLAY_ORDER")));

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
	public List<IfoCompanyOfficersView> findByCompanyId(IfoCompanyOfficersViewId id) throws SystemException {
		final String LOCATION = "findByJustCompanyId(id:" + id + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (id == null) {
			throw new SystemException(LOCATION, "IfoCompanyOfficersViewId is NULL...");
		}
		try {
			final String SEARCH_SQL = "SELECT distinct ifoCompanyOfficersView.* "
			        + " FROM IFO_COMPANY_OFFICERS_VIEW ifoCompanyOfficersView "
			        + " WHERE ifoCompanyOfficersView.COMPANY_ID = :COMPANY_ID  "
			        + " AND UPPER(ifoCompanyOfficersView.LOCALE) = :LOCALE "
			        + " ORDER BY ifoCompanyOfficersView.DISPLAY_ORDER ASC";

			// List params = new ArrayList();
			// params.add(new Parameter(":COMPANY_ID",
			// id.getCompanyId().longValue()));
			// params.add(new Parameter(":LOCALE", id.getLocale() == null ? "" :
			// id.getLocale().toUpperCase()));
			//
			// Connection conn = this.getCurrentConnection();
			// EasyStatement stat = new EasyStatement(SEARCH_SQL);
			//
			// Collection obj = stat.getList(conn
			// , (Parameter[])params.toArray(new Parameter[params.size()])
			// , ifoCompanyOfficesViewProcessor);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("COMPANY_ID", id.getCompanyId());
			paramMap.put("LOCALE", id.getLocale() == null ? "" : id.getLocale().toUpperCase());

			// SearchResult result =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// SEARCH_SQL, paramMap, ifoCompanyOfficesViewRowMapper, null);
			SearchResult<IfoCompanyOfficersView> results = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoCompanyOfficesViewRowMapper);

			log.debug(LOCATION + ":: END - result:" + results.size());
			return results;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	public static IfoCompanyOfficersViewDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IfoCompanyOfficersViewDAO) ctx.getBean("IfoCompanyOfficersViewDAO");
	}
}