package vn.com.vndirect.business;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.com.vndirect.service.onlineuserservice.IOnlineUserService;
import vn.com.vndirect.wsclient.onlineuser.AssignCardException;
import vn.com.vndirect.wsclient.onlineuser.AssignCardResult;
import vn.com.vndirect.wsclient.onlineuser.CreateIDGException;
import vn.com.vndirect.wsclient.onlineuser.OnlineUser;
import vn.com.vndirect.wsclient.onlineuser.RegisterOnlineUserResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

@Component
public class OnlineUserServiceManager implements IOnlineUserServiceManager {

	private static Logger logger = Logger.getLogger(OnlineUserServiceManager.class);

	@Autowired
	private IOnlineUserService onlineUserService;

	/* (non-Javadoc)
	 * @see vn.com.vndirect.business.IOnlineUserServiceManager#getOnlineUserByActiveKey(java.lang.String)
	 */
	public RegisterOnlineUserResult getOnlineUserByActiveKey(String activeKey) throws FunctionalException, SystemException {
		logger.debug("getOnlineUserByActiveKey(activeKey: " + activeKey + ")");
		
		return onlineUserService.getOnlineUserByActiveKey(activeKey);
	}

	/* (non-Javadoc)
	 * @see vn.com.vndirect.business.IOnlineUserServiceManager#updateFreeToTradingAccountSyncToDB(vn.com.vndirect.wsclient.onlineuser.OnlineTradingUser)
	 */
	public boolean updateFreeToTradingAccountSyncToDB(OnlineUser onlineUser) throws FunctionalException,
			SystemException {
		logger.debug("updateFreeToTradingAccountSyncToDB(onlineUser: " + onlineUser + ")");
		
		return onlineUserService.updateFreeToTradingAccountSyncToDB(onlineUser);
	}

	/* (non-Javadoc)
	 * @see vn.com.vndirect.business.IOnlineUserServiceManager#createIDGAccount(vn.com.vndirect.wsclient.onlineuser.OnlineUser)
	 */
	public void createIDGAccount(OnlineUser onlineUser) throws CreateIDGException {
		logger.debug("createIDGAccount(onlineUser: " + onlineUser + ")");
		
		onlineUserService.createIDGAccount(onlineUser);
	}

	/* (non-Javadoc)
	 * @see vn.com.vndirect.business.IOnlineUserServiceManager#assignedCardToAccount(vn.com.vndirect.wsclient.onlineuser.OnlineUser)
	 */
	public AssignCardResult assignedCardToAccount(OnlineUser onlineUser) throws AssignCardException {
		logger.debug("assignedCardToAccount(OnlineUser: " + onlineUser + ")");
		
		return onlineUserService.assignedCardToAccount(onlineUser);
	}

	@Override
	public void updateUserAccountState(String activeKey, String state) throws FunctionalException, SystemException {
		final String location = "updateUserAccountState(activeKey: " + activeKey + ", state: " + state + ")";
		logger.debug(location);
		boolean isSuccessUpdate = onlineUserService.updateUserAccountState(activeKey, state);
		if(!isSuccessUpdate){
			throw new SystemException(location, "updateUserAccountState fail");
		}
	}

}
