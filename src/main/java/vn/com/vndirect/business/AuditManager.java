package vn.com.vndirect.business;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.vndsservice.VNDSServiceUtils;
import vn.com.vndirect.wsclient.audit.CreateAuditSymbolRequest;
import vn.com.vndirect.wsclient.audit.CreateAuditSymbolResult;
import vn.com.vndirect.wsclient.audit.SearchMostPopularSymbolRequest;
import vn.com.vndirect.wsclient.audit.SearchMostPopularSymbolResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

public class AuditManager extends BaseManager implements IAuditManager {
	private static Logger logger = Logger.getLogger(AuditManager.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAuditManager#searchMostPopularSymbol(vn.com
	 * .vndirect.wsclient.audit.SearchMostPopularSymbolRequest)
	 */
	public SearchMostPopularSymbolResult searchMostPopularSymbol(SearchMostPopularSymbolRequest searchMostPopularSymbolRequest)
	        throws FunctionalException, SystemException {
		final String LOCATION = "searchMostPopularSymbol(searchMostPopularSymbolRequest:" + searchMostPopularSymbolRequest + ")";
		try {
			SearchMostPopularSymbolResult result = this.getIAuditServicePortType().searchMostPopularSymbol(
			        getVndsAuthenticationHeader(), searchMostPopularSymbolRequest);
			return result;
		} catch (RemoteException rex) {
			logger.error(LOCATION, rex);
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
			        new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			logger.error(LOCATION, srvex);
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
			        new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
			logger.error(LOCATION, fex);
			throw fex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAuditManager#createAuditSymbol(vn.com.vndirect
	 * .wsclient.audit.CreateAuditSymbolRequest)
	 */
	public CreateAuditSymbolResult createAuditSymbol(CreateAuditSymbolRequest createAuditSymbolRequest)
	        throws FunctionalException, SystemException {
		final String LOCATION = "createAuditSymbol(createAuditSymbolRequest:" + createAuditSymbolRequest + ")";
		try {
			CreateAuditSymbolResult result = this.getIAuditServicePortType().createAuditSymbol(getVndsAuthenticationHeader(),
			        createAuditSymbolRequest);
			VNDSServiceUtils.processMessageStatus(result == null ? null : result.getMsgStatus());
			return result;
		} catch (RemoteException rex) {
			logger.error(LOCATION, rex);
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
			        new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			logger.error(LOCATION, srvex);
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
			        new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
			logger.error(LOCATION, fex);
			throw fex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

}
