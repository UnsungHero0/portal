/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Aug 31, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.business;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import vn.com.vndirect.boproxyclient.AuthenticationHeader;
import vn.com.vndirect.boproxyclient.onlinetrading.Account;
import vn.com.vndirect.boproxyclient.onlinetrading.GetAccountsResult;
import vn.com.vndirect.boproxyclient.onlinetrading.GetUserProfileResult;
import vn.com.vndirect.boproxyclient.onlinetrading.UpdateUserProfileResult;
import vn.com.vndirect.boproxyclient.onlinetrading.UserProfile;
import vn.com.vndirect.commons.boservice.BOServiceUtils;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.vndsservice.VNDSServiceUtils;
import vn.com.vndirect.domain.OnlineUser;
import vn.com.vndirect.domain.RecoverPassword;
import vn.com.vndirect.wsclient.onlineuser.ChangeEmailRequest;
import vn.com.vndirect.wsclient.onlineuser.ChangeEmailResult;
import vn.com.vndirect.wsclient.onlineuser.RecoverPasswordResult;
import vn.com.vndirect.wsclient.onlineuser.SendEmailResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

/**
 * @author tungnq
 * 
 */
public class TradingManager extends BaseManager implements ITradingManager {
	private static Logger logger = Logger.getLogger(TradingManager.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.ITradingManager#getAccountRecords(java.lang.
	 * String)
	 */
	public List<Account> getAccountRecords(String customerId) throws FunctionalException, SystemException {
		final String LOCATION = "getAccountRecords(customerId:" + customerId + ")";
		try {
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: BEGIN");
			}

			List<Account> listAccounts = new ArrayList<Account>();

			// call service
			AuthenticationHeader header = this.getBoAuthenticationHeader();
			GetAccountsResult getAccountsResult = this.getOnlineTradingServicePortType().getAccounts(header, customerId);
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: getAccountsResult:" + getAccountsResult);
			}
			BOServiceUtils.processMessageStatus(getAccountsResult == null ? null : getAccountsResult.getStatus());
			Account[] accounts = getAccountsResult.getGetAccounts();
			if (accounts != null) {
				CollectionUtils.addAll(listAccounts, accounts);
			}
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: listAccounts:" + listAccounts);
			}

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}
			return listAccounts;
		} catch (RemoteException rex) {
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.Trading.REMOTE_ERROR,
			        new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.Trading.REMOTE_ERROR,
			        new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
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
	 * vn.com.vndirect.business.ITradingManager#getUserProfile(java.lang.String,
	 * java.lang.String)
	 */
	public UserProfile getUserProfile(String customerId, String accountNumber) throws FunctionalException, SystemException {
		final String LOCATION = "getUserProfile(customerId:" + customerId + ")";
		try {
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: BEGIN");
			}
			GetUserProfileResult getUserProfileResult = this.getOnlineTradingServicePortType().getUserProfile(
			        this.getBoAuthenticationHeader(), customerId, accountNumber);
			BOServiceUtils.processMessageStatus(getUserProfileResult == null ? null : getUserProfileResult.getStatus());
			UserProfile userProfile = getUserProfileResult.getUserProfile();

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}

			return userProfile;
		} catch (RemoteException rex) {
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.Trading.REMOTE_ERROR,
			        new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.Trading.REMOTE_ERROR,
			        new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
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
	 * vn.com.vndirect.business.ITradingManager#updateUserProfile(vn.com.vndirect
	 * .boproxyclient.onlinetrading.UserProfile)
	 */
	public UpdateUserProfileResult updateUserProfile(UserProfile userProfile) throws FunctionalException, SystemException {
		final String LOCATION = "updateUserProfile()";
		try {
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: BEGIN");
			}
			UpdateUserProfileResult updateUserProfileResult = this.getOnlineTradingServicePortType().updateUserProfile(
			        this.getBoAuthenticationHeader(), userProfile);
			BOServiceUtils.processMessageStatus(updateUserProfileResult == null ? null : updateUserProfileResult.getStatus());

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}
			return updateUserProfileResult;
		} catch (RemoteException rex) {
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.Trading.REMOTE_ERROR,
			        new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.Trading.REMOTE_ERROR,
			        new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
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
	 * vn.com.vndirect.business.ITradingManager#sendEmailAfterChangeTeleTradePwd
	 * (long)
	 */
	public SendEmailResult sendEmailAfterChangeTeleTradePwd(long userOnlineId) throws FunctionalException, SystemException {
		final String LOCATION = "sendEmailAfterChangeTeleTradePwd()";
		try {
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: BEGIN");
			}
			SendEmailResult result = this.getIOnlineUserServicePortType().sendEmailAfterChangeTeleTradePwd(
			        this.getVndsAuthenticationHeader(), userOnlineId);
			VNDSServiceUtils.processMessageStatus(result == null ? null : result.getMsgStatus());

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}
			return result;
		} catch (RemoteException rex) {
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
			        new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
			        new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
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
	 * vn.com.vndirect.business.ITradingManager#updateOnlineUserEmail(vn.com
	 * .vndirect.domain.OnlineUser, java.lang.String, boolean, boolean)
	 */
	public ChangeEmailResult updateOnlineUserEmail(OnlineUser onlineUser, String newEmail, boolean isUpdate, boolean isNotify)
	        throws FunctionalException, SystemException {
		final String LOCATION = "updateOnlineUserEmail()";
		try {
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: BEGIN");
			}
			ChangeEmailRequest changeEmailRequest = new ChangeEmailRequest();
			vn.com.vndirect.wsclient.onlineuser.OnlineUser onlineUser1 = new vn.com.vndirect.wsclient.onlineuser.OnlineUser();
			onlineUser1.setUserName(onlineUser.getUserName());
			onlineUser1.setEmail(onlineUser.getEmail());
			onlineUser1.setCustomerId(onlineUser.getCustomerId());
			onlineUser1.setFullName(onlineUser.getFullName());
			onlineUser1.setOnlineUserId(onlineUser.getOnlineUserId());
			changeEmailRequest.setOnlineUser(onlineUser1);
			changeEmailRequest.setNotify(isNotify);
			changeEmailRequest.setUpdate(isUpdate);
			changeEmailRequest.setNewEmail(newEmail);
			ChangeEmailResult changeEmailResult = this.getIOnlineUserServicePortType().changeEmail(
			        this.getVndsAuthenticationHeader(), changeEmailRequest);
			VNDSServiceUtils.processMessageStatus(changeEmailResult == null ? null : changeEmailResult.getStatus());

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}
			return changeEmailResult;
		} catch (RemoteException rex) {
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.Trading.REMOTE_ERROR,
			        new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.Trading.REMOTE_ERROR,
			        new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
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
	 * vn.com.vndirect.business.ITradingManager#recoverPassword(vn.com.vndirect
	 * .domain.RecoverPassword)
	 */
	public boolean recoverPassword(RecoverPassword recoverPassword) throws FunctionalException, SystemException {
		final String LOCATION = "recoverPassword(RecoverPassword:" + recoverPassword + ")";
		logger.debug(LOCATION + ":: BEGIN");
		if (recoverPassword == null) {
			throw new SystemException(LOCATION, "recoverPassword object is NULL");
		}
		try {
			vn.com.vndirect.wsclient.onlineuser.RecoverPassword recoverPass = recoverPassword.getRecoverPassword();
			RecoverPasswordResult recoverPasswordResult = this.getIOnlineUserServicePortType().recoverPassword(
			        this.getVndsAuthenticationHeader(), recoverPass);
			VNDSServiceUtils.processMessageStatus(recoverPasswordResult == null ? null : recoverPasswordResult.getMsgStatus());

			logger.debug(LOCATION + ":: END");
		} catch (RemoteException rex) {
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
			        new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
			        new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
			throw fex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
		return true;
	}

}