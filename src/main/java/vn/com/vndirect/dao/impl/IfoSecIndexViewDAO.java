package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoSecIndexView;
import vn.com.vndirect.domain.IfoSecIndexViewId;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoSecIndexView.
 * 
 * @see vn.com.vndirect.domain.IfoSecIndexView
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings("rawtypes")
public class IfoSecIndexViewDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoSecIndexViewDAO.class);

	// ++ Using SpringDAO ++ //
	private final RowMapper ifoCompanySecIndexViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			IfoSecIndexView obj = new IfoSecIndexView();
			IfoSecIndexViewId objId = new IfoSecIndexViewId();
			objId.setIndexName(arg0.getString("INDEX_NAME"));
			objId.setSymbol(arg0.getString("SYMBOL"));

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
	public IfoSecIndexView findBySymbol(IfoSecIndexViewId id) throws SystemException {
		final String LOCATION = "findBySymbol(id:" + id + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (id == null) {
			throw new SystemException(LOCATION, "IfoSecIndexViewId is NULL...");
		}
		try {
			final String SEARCH_SQL = "SELECT distinct ifoSecIndexView.* " + " FROM IFO_SEC_INDEX_VIEW ifoSecIndexView "
			        + " WHERE ifoSecIndexView.SYMBOL = :SYMBOL  ";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("SYMBOL", id.getSymbol());

			IfoSecIndexView obj = (IfoSecIndexView) OracleDAOHelper.querySingle(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoCompanySecIndexViewRowMapper);

			log.debug(LOCATION + ":: END - obj:" + obj);
			return obj;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
	// -- Using SpringDAO -- //
}