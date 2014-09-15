package vn.com.vndirect.service.emailservice;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import vn.com.vndirect.business.BaseManager;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.wsclient.onlineuser.OnlineUser;
import vn.com.vndirect.wsclient.onlineuser.SendEmailForSupporterType;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

@Component
public class EmailService extends BaseManager implements IEmailService {
	private static Logger logger = Logger.getLogger(EmailService.class);

	/* (non-Javadoc)
	 * @see vn.com.vndirect.service.onlineuserservice.IOnlineUserService#sendEmailForDVKH(vn.com.vndirect.wsclient.onlineuser.OnlineUser)
	 */
	public void sendEmailForDVKH(OnlineUser onlineUser, SendEmailForSupporterType type) throws FunctionalException, SystemException {
		String location = "sendEmailForDVKH()";
		try {
			this.getIOnlineUserServicePortType().sendEmailForDVKH(
					this.getVndsAuthenticationHeader(), onlineUser, type);
		} catch (RemoteException rex) {
			logger.error(location, rex);
			throw new FunctionalException(location, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
					new String[] { rex.getMessage() });
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new FunctionalException(location, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}
}
