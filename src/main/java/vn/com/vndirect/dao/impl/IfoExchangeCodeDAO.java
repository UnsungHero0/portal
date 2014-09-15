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
import vn.com.vndirect.domain.IfoExchangeCode;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoExchangeCode.
 * 
 * @see vn.com.vndirect.domain.IfoExchangeCode
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IfoExchangeCodeDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoExchangeCodeDAO.class);

	// property constants
	// public static final String EXCHANGE_NAME = "exchangeName";
	// public static final String COUNTRY_CODE = "countryCode";
	// public static final String CREATED_BY = "createdBy";
	// public static final String MODIFIED_BY = "modifiedBy";

	// //////////////////////////////////Using JDBC
	// /////////////////////////////////////

	private final RowMapper ifoExchangeCodeRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoExchangeCode obj = new IfoExchangeCode();

			obj.setExchangeCode(rs.getString("EXCHANGE_CODE"));
			obj.setExchangeName(rs.getString("EXCHANGE_NAME"));
			obj.setCountryCode(rs.getString("COUNTRY_CODE"));
			// obj.setCreatedDate(rs.getDate("CREATED_DATE"));
			// obj.setCreatedBy(rs.getString("CREATED_BY"));
			// obj.setModifiedDate(rs.getDate("MODIFIED_DATE"));
			// obj.setModifiedBy(rs.getString("MODIFIED_BY"));

			return obj;
		}
	};

	public List<IfoExchangeCode> findByExample(IfoExchangeCode instance) throws SystemException {
		final String LOCATION = "findByExample(instance:" + instance + ")";
		log.debug(LOCATION + ":: BEGIN");

		try {
			if (instance == null) {
				throw new SystemException(LOCATION, "instance is NULL...");
			}

			Map<String, Object> paramMap = new HashMap<String, Object>();

			StringBuffer sql = new StringBuffer("SELECT distinct t0.* FROM IFO_EXCHANGE_CODE t0 ");
			StringBuffer where = new StringBuffer();

			String str;
			// param: EXCHANGE_CODE
			str = instance.getExchangeCode();
			if (str != null) {
				where.append(where.length() == 0 ? "" : " AND ").append(" t0.EXCHANGE_CODE like :EXCHANGE_CODE ");

				str = (str.length() == 0 ? "%" : str);
				paramMap.put("EXCHANGE_CODE", str);
			}

			// param: EXCHANGE_CODE
			str = instance.getExchangeName();
			if (str != null) {
				where.append(where.length() == 0 ? "" : " AND ").append(" t0.EXCHANGE_NAME like :EXCHANGE_NAME ");

				str = (str.length() == 0 ? "%" : str);
				paramMap.put("EXCHANGE_NAME", str);
			}

			// param: EXCHANGE_CODE
			str = instance.getCountryCode();
			if (str != null) {
				where.append(where.length() == 0 ? "" : " AND ").append(" t0.COUNTRY_CODE like :COUNTRY_CODE ");

				str = (str.length() == 0 ? "%" : str);
				paramMap.put("COUNTRY_CODE", str);
			}

			sql.append(where.length() == 0 ? "" : " WHERE ").append(where).append(" ORDER BY t0.EXCHANGE_CODE ");

			SearchResult<IfoExchangeCode> result = OracleDAOHelper.query(this.getDataSource(), sql.toString(), paramMap,
			        ifoExchangeCodeRowMapper);

			log.debug(LOCATION + ":: END - result: " + result);
			return result;

		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * @return
	 * @throws SystemException
	 */
	public List<IfoExchangeCode> findAllExchangeCode() throws SystemException {
		final String LOCATION = "findAllExchangeCode()";
		log.debug(LOCATION + ":: BEGIN");

		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			StringBuffer sql = new StringBuffer("SELECT distinct t0.* FROM IFO_EXCHANGE_CODE t0 ");
			SearchResult<IfoExchangeCode> result = OracleDAOHelper.query(this.getDataSource(), sql.toString(), paramMap,
			        ifoExchangeCodeRowMapper);

			log.debug(LOCATION + ":: END - result: " + result);
			return result;

		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

}