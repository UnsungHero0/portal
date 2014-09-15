package vn.com.vndirect.business;

import vn.com.vndirect.wsclient.onlineuser.OnlineUser;
import vn.com.vndirect.wsclient.onlineuser.SendEmailForSupporterType;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

public interface IEmailServiceManager {
	void sendEmailForDVKH(OnlineUser onlineUser, SendEmailForSupporterType type);
}
