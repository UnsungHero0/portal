package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.extend.IfoIndexCalc;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

@SuppressWarnings("unchecked")
public class IfoIndexCalcDAO extends BaseDAOImpl {
	private static final Log log = LogFactory.getLog(IfoIndexCalcDAO.class);

	/**
     *
     */
	private final RowMapper<IfoIndexCalc> ifoIndexCalcRowMapper = new RowMapper<IfoIndexCalc>() {
		public IfoIndexCalc mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoIndexCalc obj = new IfoIndexCalc();

			obj.setCalcId(rs.getLong("CALC_ID"));
			obj.setIndexCode(rs.getString("INDEX_CODE"));
			obj.setTransDate(rs.getDate("TRANS_DATE"));
			obj.setItemCode(rs.getString("ITEM_CODE"));
			obj.setNumericValue(rs.getDouble("NUMERIC_VALUE"));

			return obj;
		}
	};

	private final static String SQL_SEARCH_INDEX_SECTOR_INDUSTRY = "select CALC_ID, INDEX_CODE, TRANS_DATE, ITEM_CODE, NUMERIC_VALUE from IFO_INDEX_CALC where item_code=:item_code and index_code = :index_code";

	/**
	 * 
	 * @param ifoIndexCalc
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoIndexCalc> searchIndexCalcByItemAndIndexCode(IfoIndexCalc ifoIndexCalc) throws FunctionalException,
	        SystemException {
		final String LOCATION = "searchIndexCalcByItemAndIndexCode(ifoIndexCalc: " + ifoIndexCalc + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoIndexCalc == null) {
			throw new FunctionalException(LOCATION, "ifoIndexCalc is NULL...");
		}
		try {
			StringBuffer strSQL = new StringBuffer(SQL_SEARCH_INDEX_SECTOR_INDUSTRY);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("item_code", ifoIndexCalc.getItemCode());
			paramMap.put("index_code", ifoIndexCalc.getIndexCode());

			if (ifoIndexCalc.getFromTransDate() != null) {
				paramMap.put("from_trans_date", ifoIndexCalc.getFromTransDate());
				strSQL.append(" AND trans_date >= :from_trans_date ");
			}

			if (ifoIndexCalc.getToTransDate() != null) {
				paramMap.put("to_trans_date", ifoIndexCalc.getToTransDate());
				strSQL.append(" AND trans_date <= :to_trans_date ");
			}

			strSQL.append(" order by trans_date asc ");

			SearchResult<IfoIndexCalc> results = OracleDAOHelper.query(this.getDataSource(), strSQL.toString(), paramMap,
			        ifoIndexCalcRowMapper);

			log.debug(LOCATION + ":: END - " + results);
			return results;
		} catch (RuntimeException re) {
			log.error(LOCATION + ":: RuntimeException " + re);
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			log.error(LOCATION + ":: Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}
}
