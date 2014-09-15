package vn.com.vndirect.embeddb.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import vn.com.web.commons.dao.jdbc.OracleDAOHelper;

public class RunDBScriptDAO extends JdbcDaoSupport {
	private static final Log log = LogFactory.getLog(RunDBScriptDAO.class);

	/**
	 * 
	 * @param fileName
	 * @param options
	 * @throws SQLException
	 * @throws IOException
	 * @throws Exception
	 */
	public void processRunscript(String fileName, String options) throws SQLException, IOException, Exception {
		final String LOCATION = "processRunscript(fileName, options)";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());
		String sql = "RUNSCRIPT FROM '" + fileName + "' " + options;
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: strSQL:" + sql);
		jdbcTemplate.execute(sql);
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: END");
	}

	/**
	 * 
	 * @param table
	 * @throws SQLException
	 * @throws IOException
	 * @throws Exception
	 */
	public void processDropTable(String table) throws SQLException, IOException, Exception {
		final String LOCATION = "processDropTable(table)";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN - " + table);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());
		String sql = "DROP TABLE IF EXISTS `" + table + "`;";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: strSQL:" + sql);
		jdbcTemplate.execute(sql);
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: END");
	}

	/**
	 * 
	 * @param table
	 * @param fields
	 * @throws SQLException
	 * @throws IOException
	 * @throws Exception
	 */
	public void processCreateTable(String table, Map<String, String> fields, List<String> options) throws SQLException, IOException, Exception {
		final String LOCATION = "processCreateTable(table, fields, options)";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");

		StringBuilder strSQL = new StringBuilder("CREATE  TABLE IF NOT EXISTS `" + table + "` (");
		int count = 0;
		int size = fields.size();
		for (String key : fields.keySet()) {
			count++;
			strSQL.append(key).append(" ").append(fields.get(key)).append(count < size ? "," : "");
		}
		if (options != null && options.size() > 0) {
			for (String option : options) {
				strSQL.append(",").append(option);
			}
		}
		strSQL.append(");");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: strSQL:" + strSQL);

		jdbcTemplate.execute(strSQL.toString());
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: END");
	}

	/**
	 * 
	 * @param sqlScript
	 * @throws SQLException
	 * @throws IOException
	 * @throws Exception
	 */
	public void processScript(String sqlScript) throws SQLException, IOException, Exception {
		final String LOCATION = "processScript(sqlScript)";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: strSQL:" + sqlScript);
		jdbcTemplate.execute(sqlScript);
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: END");
	}

	/**
	 * 
	 * @param sqlScript
	 * @param params
	 * @throws SQLException
	 * @throws IOException
	 * @throws Exception
	 */
	public void processScript(String sqlScript, Map<String, Object> params) throws SQLException, IOException, Exception {
		final String LOCATION = "processScript(sqlScript)";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(this.getDataSource());
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: strSQL:" + sqlScript);
		jdbcTemplate.update(sqlScript, params == null ? new HashMap<String, Object>() : params);
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: END");
	}

	/**
	 * 
	 * @param tableName
	 * @return
	 */
	public boolean hasTableInDB(String tableName) {
		final String LOCATION = "hasTableInDB(sqlScript)";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		boolean rs = false;
		try {
			String SQL = "SELECT count(*) FROM INFORMATION_SCHEMA.TABLES tb WHERE tb.TABLE_NAME = :TABLE_NAME";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("TABLE_NAME", tableName);
			int count = OracleDAOHelper.query4Int(this.getDataSource(), SQL, params);
			rs = (count > 0);
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug(LOCATION, e);
		}
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		return rs;
	}

}
