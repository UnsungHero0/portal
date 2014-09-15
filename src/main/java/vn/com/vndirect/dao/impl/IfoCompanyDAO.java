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
import vn.com.vndirect.domain.IfoCompany;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoCompany.
 * 
 * @see vn.tets.IfoCompany
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings("unchecked")
public class IfoCompanyDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoCompanyDAO.class);

	protected void initDao() {
		// do nothing
	}

	public static IfoCompanyDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IfoCompanyDAO) ctx.getBean("IfoCompanyDAO");
	}

	// ++ Using SpringDAO ++ //
	@SuppressWarnings("rawtypes")
	private final RowMapper ifoCompanyRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			IfoCompany ifoCompany = new IfoCompany();

			ifoCompany.setCompanyId(new Long(arg0.getLong("COMPANY_ID")));
			ifoCompany.setCompanyName(arg0.getString("COMPANY_NAME"));
			ifoCompany.setRegistrationDate(arg0.getDate("REGISTRATION_DATE"));
			ifoCompany.setCompanyStatus(arg0.getString("COMPANY_STATUS"));
			ifoCompany.setClosedDate(arg0.getDate("CLOSED_DATE"));
			ifoCompany.setCreatedDate(arg0.getDate("CREATED_DATE"));
			ifoCompany.setCreatedBy(arg0.getString("CREATED_BY"));
			ifoCompany.setModifiedDate(arg0.getDate("MODIFIED_DATE"));
			ifoCompany.setModifiedBy(arg0.getString("MODIFIED_BY"));
			ifoCompany.setForumStatus(new Long(arg0.getLong("FORUM_STATUS")));

			return ifoCompany;
		}
	};

	// -- Using SpringDAO -- //

	/**
	 * 
	 * @param ifoCompany
	 * @return
	 */
	public SearchResult<IfoCompany> searchByNameSymbol(IfoCompany ifoCompany, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchByNameSymbol(ifoCompany:" + ifoCompany + "; pagingInfo:" + pagingInfo + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoCompany == null) {
			throw new SystemException(LOCATION, "ifoCompany is NULL...");
		}

		String SQL = "SELECT IC.* FROM IFO_COMPANY IC" + " WHERE IC.COMPANY_NAME LIKE :COMPANY_NAME"
		        + " ORDER BY IC.COMPANY_NAME ASC";

		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("COMPANY_NAME", ifoCompany.getCompanyName());

			// SearchResultBean result =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(), SQL,
			// paramMap, ifoCompanyRowMapper, ifoCompany);
			SearchResult<IfoCompany> result = OracleDAOHelper.queryWithPagging(this.getDataSource(), SQL, paramMap,
			        ifoCompanyRowMapper, pagingInfo);

			log.debug(LOCATION + ":: END");
			return result;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @author DucNM
	 * @param ifoCompany
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<IfoCompany> searchBySymbolStatus(IfoCompany ifoCompany, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchBySymbolStatus(ifoCompany:" + ifoCompany + "; pagingInfo:" + pagingInfo + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoCompany == null) {
			throw new SystemException(LOCATION, "ifoCompany is NULL...");
		}

		String SQL = "SELECT IC.* FROM IFO_COMPANY IC" + " WHERE IC.FORUM_STATUS = :FORUM_STATUS"
		        + " ORDER BY IC.COMPANY_NAME ASC";

		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("FORUM_STATUS", ifoCompany.getForumStatus());

			// SearchResultBean result =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(), SQL,
			// paramMap, ifoCompanyRowMapper, ifoCompany);
			SearchResult<IfoCompany> result = OracleDAOHelper.queryWithPagging(this.getDataSource(), SQL, paramMap,
			        ifoCompanyRowMapper, pagingInfo);

			log.debug(LOCATION + ":: END");
			return result;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * @param ifoCompany
	 * @throws SystemException
	 */
	public void updateForumStatus(IfoCompany ifoCompany) throws SystemException {
		final String LOCATION = "updateForumStatus(ifoCompany:" + ifoCompany + ")";
		log.debug(LOCATION + ":: BEGIN");

		String SQL = "UPDATE IFO_COMPANY " + "SET FORUM_STATUS = :FORUM_STATUS " + "WHERE COMPANY_ID = :COMPANY_ID";

		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("FORUM_STATUS", ifoCompany.getForumStatus());
			paramMap.put("COMPANY_ID", ifoCompany.getCompanyId());

			int intNum = OracleDAOHelper.update(this.getDataSource(), SQL, paramMap);

			log.debug(LOCATION + "::END intNum" + intNum);
		} catch (RuntimeException sysEx) {
			log.debug("query error!!!");
			throw sysEx;
		}
	}

	// ////////////////////////////////// Using JDBC
	// /////////////////////////////////////

	/**
	 * process all attributes of table
	 */
	// private final ResultSetProcessor ifoCompanyProcessor = new
	// ResultSetProcessor() {
	// public Object processRow(ResultSet rs) throws SQLException {
	// IfoCompany ifoCompany = new IfoCompany();
	//
	// ifoCompany.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
	// ifoCompany.setCompanyName(rs.getString("COMPANY_NAME"));
	// ifoCompany.setRegistrationDate(rs.getDate("REGISTRATION_DATE"));
	// // ifoCompany.setCompanyStatus(new Long(rs.getLong("COMPANY_STATUS")));
	// ifoCompany.setCompanyStatus(rs.getString("COMPANY_STATUS"));
	// ifoCompany.setClosedDate(rs.getDate("CLOSED_DATE"));
	// ifoCompany.setCreatedDate(rs.getDate("CREATED_DATE"));
	// ifoCompany.setCreatedBy(rs.getString("CREATED_BY"));
	// ifoCompany.setModifiedDate(rs.getDate("MODIFIED_DATE"));
	// ifoCompany.setModifiedBy(rs.getString("MODIFIED_BY"));
	// ifoCompany.setForumStatus(new Long(rs.getLong("FORUM_STATUS")));
	//
	// return ifoCompany;
	// }
	// };
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SystemException
	 */
	public IfoCompany findByCompanyId(Long id) throws SystemException {
		final String LOCATION = "findByCompanyId(id:" + id + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (id == null) {
			throw new SystemException(LOCATION, "id is NULL...");
		}
		try {
			final String SEARCH_SQL = "SELECT ifoCompany.* " + " FROM IFO_COMPANY ifoCompany "
			        + " WHERE ifoCompany.COMPANY_ID = :COMPANY_ID  ";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("COMPANY_ID", id);

			Object obj = OracleDAOHelper.querySingle(this.getDataSource(), SEARCH_SQL, paramMap, ifoCompanyRowMapper);

			log.debug(LOCATION + ":: END - obj:" + obj);
			return (IfoCompany) obj;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(LOCATION, e);
		}
	}

	@SuppressWarnings("rawtypes")
	public SearchResult getAllStockCode() throws SystemException {
		final String LOCATION = "getAllStockCode()";
		try {
			log.debug(LOCATION + ":: BEGIN");
			StringBuffer sqlBuf = new StringBuffer(
			        "SELECT SEC_CODE FROM IFO_SEC_CODE WHERE EXCHANGE_CODE IN ('HOSTC', 'HNX', 'UPCOM')");
			sqlBuf.append(" ORDER BY SEC_CODE ASC");

			SearchResult<String> result = OracleDAOHelper.query(this.getDataSource(), sqlBuf.toString(), null, new RowMapper() {
				public Object mapRow(ResultSet rs, int index) throws SQLException {
					return rs.getString("SEC_CODE");
				}
			}, null);

			log.debug(LOCATION + ":: END - result:" + result);
			return result;
		} catch (SystemException ex) {
			ex.getThrowable().printStackTrace();
			throw new SystemException(LOCATION, ex);
		}
	}
	// -- Using SpringDAO -- //
}