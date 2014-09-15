package vn.com.vndirect.business;

import vn.com.vndirect.wsclient.onlineuser.AssignCardException;
import vn.com.vndirect.wsclient.onlineuser.AssignCardResult;
import vn.com.vndirect.wsclient.onlineuser.CreateIDGException;
import vn.com.vndirect.wsclient.onlineuser.OnlineUser;
import vn.com.vndirect.wsclient.onlineuser.RegisterOnlineUserResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

public interface IOnlineUserServiceManager {
	RegisterOnlineUserResult getOnlineUserByActiveKey(String activeKey) throws FunctionalException, SystemException;

	boolean updateFreeToTradingAccountSyncToDB(OnlineUser onlineUser) throws FunctionalException, SystemException;

	void createIDGAccount(OnlineUser onlineUser) throws CreateIDGException;

	AssignCardResult assignedCardToAccount(OnlineUser onlineUser) throws AssignCardException;
	
	void updateUserAccountState(String activeKey, String state) throws FunctionalException, SystemException; 
}
