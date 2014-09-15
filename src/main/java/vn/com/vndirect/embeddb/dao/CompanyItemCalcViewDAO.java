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

import vn.com.vndirect.domain.embeddb.CompanyItemCalcView;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

@SuppressWarnings("unchecked")
public class CompanyItemCalcViewDAO extends JdbcDaoSupport {
	private static final Log log = LogFactory.getLog(CompanyItemCalcViewDAO.class);

	public interface Field {
		String SYMBOL = "SYMBOL";
		String ITEM_CODE = "ITEM_CODE";
		String TEXT_VALUE = "TEXT_VALUE";
		String NUMERIC_VALUE = "NUMERIC_VALUE";
		String TRANS_DATE = "TRANS_DATE";
	}

	private final static String SQL_INSERT_COMPANY_ITEM_CALC_VIEW = "INSERT INTO COMPANY_ITEM_CALC_VIEW(SYMBOL, ITEM_CODE, TEXT_VALUE, NUMERIC_VALUE, TRANS_DATE) "
			+ "VALUES (:SYMBOL, :ITEM_CODE, :TEXT_VALUE, :NUMERIC_VALUE, :TRANS_DATE)";

	/**
	 * 
	 * @param bean
	 * @throws SystemException
	 */
	public void insert(CompanyItemCalcView bean) throws SystemException {
		final String LOCATION = "insert(CompanyItemCalcView)";
		log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(Field.SYMBOL, bean.getSymbol());
			paramMap.put(Field.ITEM_CODE, bean.getItemCode());
			paramMap.put(Field.TEXT_VALUE, bean.getTextValue());
			paramMap.put(Field.NUMERIC_VALUE, bean.getNumericValue());
			paramMap.put(Field.TRANS_DATE, bean.getTransDate());

			OracleDAOHelper.update(this.getDataSource(), SQL_INSERT_COMPANY_ITEM_CALC_VIEW, paramMap);
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
	private final RowMapper companyItemCalcViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			CompanyItemCalcView obj = new CompanyItemCalcView();
			obj.setSymbol(rs.getString(Field.SYMBOL));
			obj.setItemCode(rs.getString(Field.ITEM_CODE));
			obj.setTextValue(rs.getString(Field.TEXT_VALUE));
			obj.setNumericValue(rs.getDouble(Field.NUMERIC_VALUE));
			obj.setTransDate(rs.getTimestamp(Field.TRANS_DATE));
			return obj;
		}
	};

	private final static String SQL_GET_ALL_COMPANY_ITEM_CAL_VIEW = "SELECT * FROM COMPANY_ITEM_CALC_VIEW ORDER BY SYMBOL";

	/**
	 * 
	 * @param itemCodes
	 * @return
	 */
	public List<CompanyItemCalcView> getAllCompanyItemCalcView() throws SystemException {
		final String LOCATION = "getAllAnalysisView()";
		log.debug(LOCATION + ":: BEGIN");
		try {
			SearchResult<CompanyItemCalcView> result = OracleDAOHelper.query(this.getDataSource(), SQL_GET_ALL_COMPANY_ITEM_CAL_VIEW, null, companyItemCalcViewRowMapper);

			log.debug(LOCATION + ":: END - result:" + result);
			return (result == null ? new ArrayList<CompanyItemCalcView>() : result);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
}
