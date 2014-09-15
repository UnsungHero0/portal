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
import vn.com.vndirect.domain.IfoMaijorHolderView;
import vn.com.vndirect.domain.IfoMaijorHolderViewId;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoMaijorHolderView.
 * 
 * @see vn.com.vndirect.domain.IfoMaijorHolderView
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings("unchecked")
public class IfoMaijorHolderViewDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoMaijorHolderViewDAO.class);

	// ////////////////////////////////// Using JDBC
	// /////////////////////////////////////

	/**
	 * process all attributes of table
	 */
	// private final ResultSetProcessor ifoMaijorHolderViewProcessor = new
	// ResultSetProcessor() {
	// public Object processRow(ResultSet rs) throws SQLException {
	// IfoMaijorHolderView obj = new IfoMaijorHolderView();
	// IfoMaijorHolderViewId objId = new IfoMaijorHolderViewId();
	//
	// objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
	// objId.setHolders(rs.getString("HOLDERS"));
	// objId.setPosition(rs.getString("POSITION"));
	// objId.setShares(new Double(rs.getDouble("SHARES")));
	// objId.setReported(rs.getDate("REPORTED"));
	// objId.setOwnerRatio(Utility.getDouble(rs.getDouble("OWNERSHIP")));
	// objId.setLocale(rs.getString("LOCALE"));
	//
	// obj.setId(objId);
	// return obj;
	// }
	// };
	@SuppressWarnings("rawtypes")
	private final RowMapper ifoMaijorHolderViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoMaijorHolderView obj = new IfoMaijorHolderView();
			IfoMaijorHolderViewId objId = new IfoMaijorHolderViewId();

			objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objId.setHolders(rs.getString("HOLDERS"));
			objId.setPosition(rs.getString("POSITION"));
			objId.setShares(new Double(rs.getDouble("SHARES")));
			objId.setReported(rs.getDate("REPORTED"));
			objId.setOwnerRatio(DAOUtils.getDouble(rs.getDouble("OWNERSHIP")));
			objId.setLocale(rs.getString("LOCALE"));

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
	public List<IfoMaijorHolderView> findByCompanyId(IfoMaijorHolderViewId id) throws SystemException {
		final String LOCATION = "findByCompanyId(id:" + id + ")";
		if (log.isDebugEnabled()) {
			log.debug(LOCATION + ":: BEGIN");
		}
		if (id == null) {
			throw new SystemException(LOCATION, "IfoMajorHolderView is NULL...");
		}
		try {
			final String SEARCH_SQL = "SELECT ifoMajorHolderView.* " + " FROM IFO_MAJOR_HOLDER_VIEW ifoMajorHolderView "
			        + " WHERE ifoMajorHolderView.COMPANY_ID = :COMPANY_ID  " + " AND UPPER(ifoMajorHolderView.LOCALE) = :LOCALE "
			        + " ORDER BY ifoMajorHolderView.OWNERSHIP DESC, ifoMajorHolderView.SHARES DESC ";
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("COMPANY_ID", id.getCompanyId());
			paramMap.put("LOCALE", id.getLocale() == null ? "" : id.getLocale().toUpperCase());

			SearchResult<IfoMaijorHolderView> result = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoMaijorHolderViewRowMapper);

			if (log.isDebugEnabled()) {
				log.debug(LOCATION + ":: END - result: " + result.size());
			}
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