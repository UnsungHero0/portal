package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoBreakdownView;
import vn.com.vndirect.domain.IfoBreakdownViewId;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoBreakdownView.
 * 
 * @see vn.com.vndirect.domain.IfoBreakdownView
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings("rawtypes")
public class IfoBreakdownViewDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoBreakdownViewDAO.class);

	// ++ Using SpringDAO ++ //
	private final RowMapper ifoBreakdownViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			IfoBreakdownView obj = new IfoBreakdownView();
			IfoBreakdownViewId objId = new IfoBreakdownViewId();
			objId.setCompanyId(new Long(arg0.getLong("COMPANY_ID")));
			objId.setStateOwnership(new Double(arg0.getDouble("STATE_OWNERSHIP")));
			objId.setInternalManagement(new Double(arg0.getDouble("INTERNAL_MANAGEMENT")));
			objId.setStrategicInvestor(new Double(arg0.getDouble("STRATEGIC_INVESTOR")));
			objId.setForeignOwnership(new Double(arg0.getDouble("FOREIGN_OWNERSHIP")));
			objId.setOther(new Double(arg0.getDouble("OTHER")));

			obj.setId(objId);
			return obj;
		}
	};

	/**
	 * 
	 * @param companyId
	 * @return
	 * @throws SystemException
	 */
	public IfoBreakdownView findByCompanyId(long companyId) throws SystemException {
		final String LOCATION = "findByCompanyId(companyId:" + companyId + ")";
		if (log.isDebugEnabled()) {
			log.debug(LOCATION + ":: BEGIN");
		}

		try {
			final String SELECT_SQL = "SELECT ifoBreakdownView.* " + " FROM IFO_BREAKDOWN_VIEW ifoBreakdownView "
			        + " WHERE ifoBreakdownView.COMPANY_ID = :COMPANY_ID ";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			// add CompanyId
			paramMap.put("COMPANY_ID", new Long(companyId));

			IfoBreakdownView obj = (IfoBreakdownView) OracleDAOHelper.querySingle(this.getDataSource(), SELECT_SQL, paramMap,
			        ifoBreakdownViewRowMapper);

			if (log.isDebugEnabled()) {
				log.debug(LOCATION + ":: END - obj:" + obj);
			}
			return obj;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
	// -- Using SpringDAO -- //
}