package vn.com.vndirect.service.emailservice;

import vn.com.vndirect.wsclient.onlineuser.AssignCardException;
import vn.com.vndirect.wsclient.onlineuser.CreateIDGException;
import vn.com.vndirect.wsclient.onlineuser.OnlineTradingUser;
import vn.com.vndirect.wsclient.onlineuser.OnlineUser;
import vn.com.vndirect.wsclient.onlineuser.RegisterOnlineUserResult;
import vn.com.vndirect.wsclient.onlineuser.SendEmailForSupporterType;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

public interface IEmailService {
	void sendEmailForDVKH(OnlineUser onlineUser, SendEmailForSupporterType type) throws FunctionalException, SystemException;
}
