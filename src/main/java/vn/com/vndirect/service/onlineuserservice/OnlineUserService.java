package vn.com.vndirect.service.onlineuserservice;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import vn.com.vndirect.business.BaseManager;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.wsclient.onlineuser.AssignCardException;
import vn.com.vndirect.wsclient.onlineuser.AssignCardResult;
import vn.com.vndirect.wsclient.onlineuser.CreateIDGException;
import vn.com.vndirect.wsclient.onlineuser.OnlineUser;
import vn.com.vndirect.wsclient.onlineuser.RegisterOnlineUserResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

@Component
public class OnlineUserService extends BaseManager implements IOnlineUserService {
	private static Logger logger = Logger.getLogger(OnlineUserService.class);

	/* (non-Javadoc)
	 * @see vn.com.vndirect.service.onlineuserservice.IOnlineUserService#getOnlineUserByActiveKey(java.lang.String)
	 */
	public RegisterOnlineUserResult getOnlineUserByActiveKey(String activeKey) throws FunctionalException, SystemException {
		final String location = "getOnlineUserByActiveKey(activeKey: " + activeKey + ")";
		try {
			RegisterOnlineUserResult result = this.getIOnlineUserServicePortType().getOnlineUserByActiveKey(this.getVndsAuthenticationHeader(), activeKey);
			
			return result == null ? new RegisterOnlineUserResult() : result;
		} catch (RemoteException rex) {
			logger.error(location, rex);
			throw new FunctionalException(location, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
					new String[] { rex.getMessage() });
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new FunctionalException(location, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/* (non-Javadoc)
	 * @see vn.com.vndirect.service.onlineuserservice.IOnlineUserService#updateFreeToTradingAccountSyncToDB(vn.com.vndirect.wsclient.onlineuser.OnlineTradingUser)
	 */
	public boolean updateFreeToTradingAccountSyncToDB(OnlineUser onlineUser) throws FunctionalException,
			SystemException {
		final String location = "updateFreeToTradingAccountSyncToDB(onlineUser: " + onlineUser + ")";

		try {
			return this.getIOnlineUserServicePortType().updateFreeToTradingAccountSyncToDB(this.getVndsAuthenticationHeader(),
					onlineUser);
		} catch (FunctionalException rex) {
			logger.error(location, rex);
			throw new FunctionalException(location, "updateFreeToTradingAccountSyncToDB fail");
		} catch (RemoteException rex) {
			logger.error(location, rex);
			throw new FunctionalException(location, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
					new String[] { rex.getMessage() });
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new FunctionalException(location, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/* (non-Javadoc)
	 * @see vn.com.vndirect.service.onlineuserservice.IOnlineUserService#createIDGAccount(vn.com.vndirect.wsclient.onlineuser.OnlineUser)
	 */
	public void createIDGAccount(OnlineUser onlineUser) throws CreateIDGException {
		final String location = "createIDGAccount(onlineUser: " + onlineUser + ")";
		
		try {
			this.getIOnlineUserServicePortType().createIDGAccount(this.getVndsAuthenticationHeader(), onlineUser);
		} catch (RemoteException rex) {
			logger.error(location, rex);
			throw new CreateIDGException();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(location, e);
			throw new CreateIDGException();
		} 
	}

	/* (non-Javadoc)
	 * @see vn.com.vndirect.service.onlineuserservice.IOnlineUserService#assignedCardToAccount(vn.com.vndirect.wsclient.onlineuser.OnlineUser)
	 */
	public AssignCardResult assignedCardToAccount(OnlineUser onlineUser) throws AssignCardException {
		final String location = "assignedCardToAccount(onlineUser: " + onlineUser + ")";
		AssignCardResult result = new AssignCardResult();
		try {
			result = this.getIOnlineUserServicePortType().assignedCardToAccount(this.getVndsAuthenticationHeader(), onlineUser);
			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new AssignCardException();
		}
	}

	@Override
	public boolean updateUserAccountState(String activeKey, String state) throws FunctionalException, SystemException {
		final String location = "updateUserAccountState(activeKey: " + activeKey + ", state: " + state + ")";
		try {
			return this.getIOnlineUserServicePortType().updateUserAccountState(this.getVndsAuthenticationHeader(), activeKey, state);
		} catch (Exception e) {
			logger.error(location, e);
			throw new FunctionalException(location, "updateUserAccountState fail");
		}
	}
}
