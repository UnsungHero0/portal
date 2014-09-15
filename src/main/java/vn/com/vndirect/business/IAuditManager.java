package vn.com.vndirect.business;

import vn.com.vndirect.wsclient.audit.CreateAuditSymbolRequest;
import vn.com.vndirect.wsclient.audit.CreateAuditSymbolResult;
import vn.com.vndirect.wsclient.audit.SearchMostPopularSymbolRequest;
import vn.com.vndirect.wsclient.audit.SearchMostPopularSymbolResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

public interface IAuditManager extends IBaseManager {
	/**
	 * 
	 * @param auditSymbolSearch
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchMostPopularSymbolResult searchMostPopularSymbol(SearchMostPopularSymbolRequest searchMostPopularSymbolRequest)
	        throws FunctionalException, SystemException;

	public CreateAuditSymbolResult createAuditSymbol(CreateAuditSymbolRequest createAuditSymbolRequest)
	        throws FunctionalException, SystemException;
}
