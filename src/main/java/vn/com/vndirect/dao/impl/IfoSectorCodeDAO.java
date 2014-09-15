package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.commons.utility.DBConstants;
import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoSectorCode;
import vn.com.vndirect.domain.IfoSectorCodeId;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoSectorCode.
 * 
 * @see test.IfoSectorCode
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings("unchecked")
public class IfoSectorCodeDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoSectorCodeDAO.class);

	// ++ Using SpringDAO ++ //
	@SuppressWarnings("rawtypes")
	private final RowMapper ifoSectorCodeRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			IfoSectorCode obj = new IfoSectorCode();
			IfoSectorCodeId objId = new IfoSectorCodeId();

			objId.setSectorCode(arg0.getString(DBConstants.IFO_SEC_CODE.SECTOR_CODE));
			objId.setSectorName(arg0.getString(DBConstants.IFO_SEC_CODE.SECTOR_NAME));
			objId.setLocale(arg0.getString(DBConstants.IFO_SEC_CODE.LOCALE));

			obj.setId(objId);

			return obj;
		}
	};

	/**
	 * 
	 * @param searchObj
	 * @return
	 * @throws SystemException
	 */
	public List<IfoSectorCode> find(IfoSectorCodeId searchObj) throws SystemException {
		final String LOCATION = "find(searchObj:" + searchObj + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		if (searchObj == null) {
			throw new SystemException(LOCATION, "searchObj is NULL...");
		}
		try {
			final String SEARCH_SQL = "select distinct sector.* " + " from IFO_SECTOR_CODE sector "
			        + " where upper(sector.LOCALE) like :LOCALE " + " ORDER BY sector.SECTOR_NAME ";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			// add LOCALE & SECTOR_CODE
			String str = searchObj.getLocale();
			str = (str == null || str.trim().length() == 0 ? "%" : str.toUpperCase().trim());
			paramMap.put("LOCALE", str);

			SearchResult<IfoSectorCode> searchResult = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoSectorCodeRowMapper);

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END - result:" + searchResult.size());
			return searchResult;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
}