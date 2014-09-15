package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoDataRef;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class CmsDataRef.
 * 
 * @see vn.com.vndirect.domain.IfoDataRef
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings("unchecked")
public class IfoDataRefDAO extends BaseDAOImpl {
	private static final Log log = LogFactory.getLog(IfoDataRefDAO.class);

	// property constants
	public interface Fields {
		public static final String TABLE_NAME = "IFO_DATA_REF";

		public static final String DATA_REF_ID = "DATA_REF_ID";

		public static final String GROUP_CODE = "GROUP_CODE";

		public static final String ITEM_CODE = "ITEM_CODE";

		public static final String DESCRIPTION = "DESCRIPTION";

		public static final String LOCALE = "LOCALE";
	}

	/**
	 * Retrieve all MyPortfolio data
	 */
	@SuppressWarnings("rawtypes")
	private RowMapper IfoDataRefRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			IfoDataRef obj = new IfoDataRef();
			obj.setDataRefId(new Long(arg0.getLong(Fields.DATA_REF_ID)));
			obj.setGroupCode(arg0.getString(Fields.GROUP_CODE));
			obj.setItemCode(arg0.getString(Fields.ITEM_CODE).trim());
			obj.setDescription(arg0.getString(Fields.DESCRIPTION));

			obj.setLocale(arg0.getString(Fields.LOCALE));
			return obj;
		}
	};

	private final static String SEARCH_IFO_DATA_REF = "SELECT * FROM " + Fields.TABLE_NAME + " WHERE (1=1) ";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndsservices.dao.MyPortfoliosDAO#searchMyPortfolio(vn.com.vndsservices
	 * .domain.portfolios.MyPortfolio, vn.com.vndsservices.domain.PagingInfo)
	 */
	public SearchResult<IfoDataRef> searchIfoDataRef(IfoDataRef ifoDataRef) throws SystemException {
		final String LOCATION = "searchIfoDataRef(ifoDataRef: " + ifoDataRef + ")";
		try {
			log.debug(LOCATION + ":: BEGIN");
			StringBuffer sqlBuf = new StringBuffer(SEARCH_IFO_DATA_REF);
			// populate parameter
			Map<String, Object> paramMap = new HashMap<String, Object>();

			if (ifoDataRef.getDataRefId() != null && ifoDataRef.getDataRefId().longValue() > 0) {
				paramMap.put(Fields.DATA_REF_ID, ifoDataRef.getDataRefId());
				sqlBuf.append(" AND " + Fields.DATA_REF_ID).append("=:" + Fields.DATA_REF_ID);
			}

			if (ifoDataRef.getGroupCode() != null && ifoDataRef.getGroupCode().length() > 0) {
				paramMap.put(Fields.GROUP_CODE, ifoDataRef.getGroupCode().toUpperCase());
				sqlBuf.append(" AND UPPER(" + Fields.GROUP_CODE + ")").append("=:" + Fields.GROUP_CODE);
			}

			if (ifoDataRef.getItemCode() != null && ifoDataRef.getItemCode().length() > 0) {
				paramMap.put(Fields.ITEM_CODE, ifoDataRef.getItemCode().toUpperCase());
				sqlBuf.append(" AND UPPER(" + Fields.ITEM_CODE + ")").append("=:" + Fields.ITEM_CODE);
			}

			if (ifoDataRef.getLocale() != null && ifoDataRef.getLocale().length() > 0) {
				paramMap.put(Fields.LOCALE, ifoDataRef.getLocale().toUpperCase());
				sqlBuf.append(" AND UPPER(" + Fields.LOCALE + ")").append("=:" + Fields.LOCALE);
			}

			sqlBuf.append(" ORDER BY ").append((ifoDataRef.getOrderby() != null) ? ifoDataRef.getOrderby() : Fields.DATA_REF_ID);
			// count item in the database
			SearchResult<IfoDataRef> result = OracleDAOHelper.query(this.getDataSource(), sqlBuf.toString(), paramMap,
			        IfoDataRefRowMapper);

			log.debug(LOCATION + ":: END - result:" + result);
			return result;
		} catch (SystemException ex) {
			ex.getThrowable().printStackTrace();
			throw new SystemException(LOCATION, ex);
		}
	}
}