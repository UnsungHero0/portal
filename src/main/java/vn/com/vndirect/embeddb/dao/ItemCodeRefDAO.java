/**
 * 
 */
package vn.com.vndirect.embeddb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import vn.com.vndirect.domain.embeddb.ItemCodeRef;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * @author Blue9Frog
 * 
 */
@SuppressWarnings("unchecked")
public class ItemCodeRefDAO extends JdbcDaoSupport {
	private static final Log log = LogFactory.getLog(ItemCodeRefDAO.class);

	public interface Field {
		String GROUP_CODE = "GROUP_CODE";
		String ITEM_CODE = "ITEM_CODE";
		String ITEM_NAME = "ITEM_NAME";
		String LOCALE = "LOCALE";
	}

	private final static String SQL_INSERT_ITEM_CODE_REF = "INSERT INTO ITEM_CODE_REF(GROUP_CODE, ITEM_CODE, ITEM_NAME, LOCALE) " + "VALUES (:GROUP_CODE, :ITEM_CODE, :ITEM_NAME, :LOCALE)";

	/**
	 * 
	 * @param bean
	 * @throws SystemException
	 */

	public void insert(ItemCodeRef bean) throws SystemException {
		final String LOCATION = "insert(ItemCodeRef)";
		log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(Field.GROUP_CODE, bean.getGroupCode());
			paramMap.put(Field.ITEM_CODE, bean.getItemCode());
			paramMap.put(Field.ITEM_NAME, bean.getItemName());
			paramMap.put(Field.LOCALE, bean.getLocale());

			OracleDAOHelper.update(this.getDataSource(), SQL_INSERT_ITEM_CODE_REF, paramMap);
			log.debug(LOCATION + ":: END");
		} catch (SystemException syse) {
			throw syse;
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
     * 
     */
	private final RowMapper itemCodeRefRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			ItemCodeRef obj = new ItemCodeRef();
			obj.setGroupCode(rs.getString(Field.GROUP_CODE));
			obj.setItemCode(rs.getString(Field.ITEM_CODE));
			obj.setItemName(rs.getString(Field.ITEM_NAME));
			obj.setLocale(rs.getString(Field.LOCALE));
			return obj;
		}
	};

	private final static String SQL_SEARCH_BY_GROUP_CODE = "SELECT * FROM ITEM_CODE_REF WHERE GROUP_CODE=:GROUP_CODE AND UPPER(LOCALE)=:LOCALE ";

	/**
	 * 
	 * @param groupCode
	 * @param locale
	 * @return
	 */
	public List<ItemCodeRef> searchByGroupCode(String groupCode, String locale) throws SystemException {
		final String LOCATION = "searchByGroupCode(groupCode, locale)";
		log.debug(LOCATION + ":: BEGIN : groupCode=" + groupCode + " , locale=" + locale);
		if (locale == null) {
			throw new SystemException(LOCATION, "locale is NULL...");
		}
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("GROUP_CODE", groupCode);
			paramMap.put("LOCALE", locale.toUpperCase());

			SearchResult<ItemCodeRef> result = OracleDAOHelper.query(this.getDataSource(), SQL_SEARCH_BY_GROUP_CODE, paramMap, itemCodeRefRowMapper);

			log.debug(LOCATION + ":: END - " + result);
			return (result == null ? new ArrayList<ItemCodeRef>() : result);
		} catch (RuntimeException re) {
			log.error(LOCATION + ":: RuntimeException " + re);
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			log.error(LOCATION + ":: Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}
}
