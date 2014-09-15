package vn.com.vndirect.business;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.com.vndirect.service.emailservice.IEmailService;
import vn.com.vndirect.service.onlineuserservice.IOnlineUserService;
import vn.com.vndirect.wsclient.onlineuser.AssignCardException;
import vn.com.vndirect.wsclient.onlineuser.CreateIDGException;
import vn.com.vndirect.wsclient.onlineuser.OnlineTradingUser;
import vn.com.vndirect.wsclient.onlineuser.OnlineUser;
import vn.com.vndirect.wsclient.onlineuser.RegisterOnlineUserResult;
import vn.com.vndirect.wsclient.onlineuser.SendEmailForSupporterType;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

@Component
public class EmailServiceManager implements IEmailServiceManager {

	private static Logger logger = Logger.getLogger(EmailServiceManager.class);

	@Autowired
	private IEmailService emailService;

	/* (non-Javadoc)
	 * @see vn.com.vndirect.business.IOnlineUserServiceManager#sendEmailForDVKH(vn.com.vndirect.wsclient.onlineuser.OnlineUser)
	 */
	public void sendEmailForDVKH(OnlineUser onlineUser, SendEmailForSupporterType type){
		logger.debug("sendEmailForDVKH()");
		try {
			emailService.sendEmailForDVKH(onlineUser, type);
		} catch (FunctionalException e) {
			logger.error(e.getMessage());
		} catch (SystemException e) {
			logger.error(e.getMessage());
		}
	}
}
