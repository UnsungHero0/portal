package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import vn.com.vndirect.dao.IRegistStockPickDAO;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

public class RegistStockPickDAO extends JdbcDaoSupport implements IRegistStockPickDAO{
	
	private static final Logger _LOGGER = Logger.getLogger(RegistStockPickDAO.class);
	
	@Override
	public boolean saveCustomerId(String cusID) throws SystemException{
		final String location = "saveCustomerId(cusID:" + cusID + ")";
		_LOGGER.debug(location + ":: BEGIN");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("CUSTOMER_ID", cusID);
		StringBuilder sql = new StringBuilder("INSERT INTO REGISTED_FOR_STOCK_PICK (CUSTOMER_ID) values (:CUSTOMER_ID)");
		int rows = OracleDAOHelper.update(this.getDataSource(), sql.toString(), paramMap);
		
		_LOGGER.debug(location + ":: END");
		return (rows > 0);
	}

	@Override
	public boolean isExistedCustomer(String cusID) throws SystemException {
		final String location = "isExistedCustomer(cusID:" + cusID + ")";
		_LOGGER.debug(location + ":: BEGIN");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("CUSTOMER_ID", cusID);
		StringBuilder sql = new StringBuilder("SELECT count(1) FROM REGISTED_FOR_STOCK_PICK WHERE CUSTOMER_ID = :CUSTOMER_ID");
		int res = OracleDAOHelper.query4Int(this.getDataSource(),sql.toString()	 , paramMap);
		
		_LOGGER.debug(location + ":: END");
		return res > 0;
	}
	
	@Override
	public SearchResult<String> getListCustomerId() throws SystemException {
		final String location = "getListCustomerId()";
		_LOGGER.debug(location + ":: BEGIN");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder("SELECT CUSTOMER_ID FROM REGISTED_FOR_STOCK_PICK");
		SearchResult<String> res = (SearchResult<String>) OracleDAOHelper.query(this.getDataSource(), sql.toString(), paramMap, objRegistStockPickRowMapper);
		
		_LOGGER.debug(location + ":: END");
		return res;
	}
	
	private final RowMapper<String> objRegistStockPickRowMapper = new RowMapper<String>() {
		public String mapRow(ResultSet arg0, int arg1) throws SQLException {
			return arg0.getString("CUSTOMER_ID");
		}
	};
}
