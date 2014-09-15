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

import vn.com.vndirect.domain.embeddb.IndustryItemCalcView;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

@SuppressWarnings("unchecked")
public class IndustryItemCalcViewDAO extends JdbcDaoSupport {
	private static final Log log = LogFactory.getLog(IndustryItemCalcViewDAO.class);

	public interface Field {
		String INDUSTRY_CODE = "INDUSTRY_CODE";
		String ITEM_CODE = "ITEM_CODE";
		String TEXT_VALUE = "TEXT_VALUE";
		String NUMERIC_VALUE = "NUMERIC_VALUE";
		String TRANS_DATE = "TRANS_DATE";
	}

	private final static String SQL_INSERT_INDUSTRY_ITEM_CALC_VIEW = "INSERT INTO INDUSTRY_ITEM_CALC_VIEW(INDUSTRY_CODE, ITEM_CODE, TEXT_VALUE, NUMERIC_VALUE, TRANS_DATE) "
			+ "VALUES (:INDUSTRY_CODE, :ITEM_CODE, :TEXT_VALUE, :NUMERIC_VALUE, :TRANS_DATE)";

	/**
	 * 
	 * @param bean
	 * @throws SystemException
	 */
	public void insert(IndustryItemCalcView bean) throws SystemException {
		final String LOCATION = "insert(IndustryItemCalcView)";
		log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(Field.INDUSTRY_CODE, bean.getIndustryCode());
			paramMap.put(Field.ITEM_CODE, bean.getItemCode());
			paramMap.put(Field.TEXT_VALUE, bean.getTextValue());
			paramMap.put(Field.NUMERIC_VALUE, bean.getNumericValue());
			paramMap.put(Field.TRANS_DATE, bean.getTransDate());

			OracleDAOHelper.update(this.getDataSource(), SQL_INSERT_INDUSTRY_ITEM_CALC_VIEW, paramMap);
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
	private final RowMapper industryItemCalcViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IndustryItemCalcView obj = new IndustryItemCalcView();
			obj.setIndustryCode(rs.getString(Field.INDUSTRY_CODE));
			obj.setItemCode(rs.getString(Field.ITEM_CODE));
			obj.setTextValue(rs.getString(Field.TEXT_VALUE));
			obj.setNumericValue(rs.getDouble(Field.NUMERIC_VALUE));
			obj.setTransDate(rs.getTimestamp(Field.TRANS_DATE));
			return obj;
		}
	};

	private final static String SQL_GET_ALL_INDUSTRY_ITEM_CAL_VIEW = "SELECT * FROM INDUSTRY_ITEM_CALC_VIEW ORDER BY INDUSTRY_CODE";

	/**
	 * 
	 * @param itemCodes
	 * @return
	 */
	public List<IndustryItemCalcView> getAllIndustryItemCalcView() throws SystemException {
		final String LOCATION = "getAllIndustryItemCalcView()";
		log.debug(LOCATION + ":: BEGIN");
		try {
			SearchResult<IndustryItemCalcView> result = OracleDAOHelper.query(this.getDataSource(), SQL_GET_ALL_INDUSTRY_ITEM_CAL_VIEW, null, industryItemCalcViewRowMapper);

			log.debug(LOCATION + ":: END - result:" + result);
			return (result == null ? new ArrayList<IndustryItemCalcView>() : result);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
}
