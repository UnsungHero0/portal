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
import vn.com.vndirect.domain.IfoInsiderTransactionView;
import vn.com.vndirect.domain.IfoInsiderTransactionViewId;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoInsiderTransactionView.
 * 
 * @see vn.com.vndirect.domain.IfoInsiderTransactionView
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings("unchecked")
public class IfoInsiderTransactionViewDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoInsiderTransactionViewDAO.class);

	/**
	 * process all attributes of table
	 */
	@SuppressWarnings("rawtypes")
    private final RowMapper ifoInsiderTransactionViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoInsiderTransactionView obj = new IfoInsiderTransactionView();
			IfoInsiderTransactionViewId objId = new IfoInsiderTransactionViewId();

			objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objId.setInsider(rs.getString("INSIDER"));
			objId.setPosition(rs.getString("POSITION"));
			objId.setPrice(new Double(rs.getDouble("PRICE")));
			objId.setSharesHeld(new Double(rs.getDouble("SHARES_HELD")));
			objId.setTransaction(rs.getString("TRANSACTION"));
			objId.setTransactionDate(rs.getDate("TRANSACTION_DATE"));
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
	public List<IfoInsiderTransactionView> findByCompanyId(IfoInsiderTransactionViewId id) throws SystemException {
		final String LOCATION = "findByCompanyId(id:" + id + ")";
		if (log.isDebugEnabled()) {
			log.debug(LOCATION + ":: BEGIN");
		}
		if (id == null) {
			throw new SystemException(LOCATION, "IfoInsiderTransactionViewId is NULL...");
		}
		try {
			final String SEARCH_SQL = "SELECT ifoInsiderTransactionView.* " + " FROM IFO_INSIDER_TRANSACTION_VIEW ifoInsiderTransactionView "
					+ " WHERE ifoInsiderTransactionView.COMPANY_ID = :COMPANY_ID  " + " AND UPPER(ifoInsiderTransactionView.LOCALE) = :LOCALE "
					+ " ORDER BY ifoInsiderTransactionView.TRANSACTION_DATE DESC";
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("COMPANY_ID", id.getCompanyId());
			paramMap.put("LOCALE", id.getLocale() == null ? "" : id.getLocale().toUpperCase());

			SearchResult<IfoInsiderTransactionView> result = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap, ifoInsiderTransactionViewRowMapper);

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