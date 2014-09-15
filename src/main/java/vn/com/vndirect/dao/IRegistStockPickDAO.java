package vn.com.vndirect.dao;

import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

public interface IRegistStockPickDAO {

	boolean saveCustomerId(String cusID) throws SystemException;

	boolean isExistedCustomer(String cusID) throws SystemException;

	SearchResult<String> getListCustomerId() throws SystemException;
	
}