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

import vn.com.vndirect.domain.embeddb.DbEmbedConfig;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

@SuppressWarnings("unchecked")
public class DbEmbedConfigDAO extends JdbcDaoSupport {
	private static final Log log = LogFactory.getLog(DbEmbedConfigDAO.class);

	public interface Field {
		String GROUP_CODE = "GROUP_CODE";
		String ITEM_CODE = "ITEM_CODE";
		String TEXT_VALUE = "TEXT_VALUE";
		String NUMBER_VALUE = "NUMBER_VALUE";
		String DATE_VALUE = "DATE_VALUE";
	}

	private final RowMapper dbEmbedConfigRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			DbEmbedConfig obj = new DbEmbedConfig();
			obj.setGroupCode(rs.getString(Field.GROUP_CODE));
			obj.setItemCode(rs.getString(Field.ITEM_CODE));
			obj.setTextValue(rs.getString(Field.TEXT_VALUE));
			obj.setNumberValue(rs.getDouble(Field.NUMBER_VALUE));
			obj.setDateValue(rs.getTimestamp(Field.DATE_VALUE));
			return obj;
		}
	};

	/**
	 * 
	 * @param bean
	 * @return
	 * @throws SystemException
	 */
	public List<DbEmbedConfig> getDbEmbedConfig(DbEmbedConfig bean) throws SystemException {
		final String LOCATION = "getDbEmbedConfig()";
		log.debug(LOCATION + ":: BEGIN");
		try {
			StringBuilder strSQL = new StringBuilder("SELECT * FROM DB_EMBED_CONFIG WHERE ");
			Map<String, Object> param = new HashMap<String, Object>();

			strSQL.append(Field.GROUP_CODE).append(" = :").append(Field.GROUP_CODE);
			param.put(Field.GROUP_CODE, bean == null ? "" : bean.getGroupCode());

			if (bean != null && bean.getItemCode() != null) {
				strSQL.append(" AND ").append(Field.ITEM_CODE).append(" = :").append(Field.ITEM_CODE);
				param.put(Field.ITEM_CODE, bean == null ? "" : bean.getItemCode());
			}

			SearchResult<DbEmbedConfig> result = OracleDAOHelper.query(this.getDataSource(), strSQL.toString(), param, dbEmbedConfigRowMapper);

			log.debug(LOCATION + ":: END - result:" + result);
			return (result == null ? new ArrayList<DbEmbedConfig>() : result);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
}
