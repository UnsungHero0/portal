/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Aug 31, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.business;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.code.ssm.api.ReturnDataUpdateContent;
import com.google.code.ssm.api.UpdateSingleCache;

import vn.com.vndirect.boproxyclient.customerservice.GetCustomer;
import vn.com.vndirect.boproxyclient.customerservice.GetCustomerResult;
import vn.com.vndirect.commons.boservice.BOServiceUtils;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.vndsservice.VNDSServiceUtils;
import vn.com.vndirect.domain.OnlineUser;
import vn.com.vndirect.domain.ServiceInfo;
import vn.com.vndirect.wsclient.onlineuser.ChangePassword;
import vn.com.vndirect.wsclient.onlineuser.ChangePasswordResult;
import vn.com.vndirect.wsclient.onlineuser.GetOnlineUser;
import vn.com.vndirect.wsclient.onlineuser.GetOnlineUserResult;
import vn.com.vndirect.wsclient.onlineuser.OnlineUserAuth;
import vn.com.vndirect.wsclient.onlineuser.OnlineUserAuthResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.DateUtils;

/**
 * @author tungnq
 * 
 */
public class UserManager extends BaseManager implements IUserManager {
	private static Logger logger = Logger.getLogger(UserManager.class);

	// +++ =============== /Define Spring Mapping ===============+++++

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.UserManager#getOnlineUserByUserName(java.lang
	 * .String)
	 */
	public OnlineUser checkAuthentication(String userName, String password) throws FunctionalException, SystemException {
		final String LOCATION = "checkAuthentication(userName:" + userName + ", password:" + password + ")";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}
		if (userName == null) {
			throw new SystemException(LOCATION, "userName object is NULL");
		}
		try {
			OnlineUserAuth onlineUserAuth = new OnlineUserAuth();
			onlineUserAuth.setUserName(userName);
			onlineUserAuth.setPassword(password);

			OnlineUserAuthResult onlineUserAuthResult = this.getIOnlineUserServicePortType().checkAuth(
					this.getVndsAuthenticationHeader(), onlineUserAuth);
			VNDSServiceUtils.processMessageStatus(onlineUserAuthResult == null ? null : onlineUserAuthResult.getMsgStatus());

			OnlineUser result = null;
			if (onlineUserAuthResult != null && onlineUserAuthResult.getUserProfile() != null) {
				result = new OnlineUser();
				result.setCreatedDate(DateUtils.getCalendarDate(onlineUserAuthResult.getUserProfile().getCreatedDate()));
				result.setCustomerId(onlineUserAuthResult.getUserProfile().getCustomerId());
				result.setEmail(onlineUserAuthResult.getUserProfile().getEmail());
				result.setFullName(onlineUserAuthResult.getUserProfile().getFullName());
				result.setIdgId(onlineUserAuthResult.getUserProfile().getIdgId());
				result.setOnlineUserId(onlineUserAuthResult.getUserProfile().getOnlineUserId());
				result.setPassword(onlineUserAuthResult.getUserProfile().getPassword());
				result.setStatus(onlineUserAuthResult.getUserProfile().getStatus());
				result.setUserName(onlineUserAuthResult.getUserProfile().getUserName());

				result.setDob(DateUtils.getCalendarDate(onlineUserAuthResult.getUserProfile().getDob()));
				result.setStrDob(DateUtils.dateToString(result.getDob(), DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
				result.setSex(onlineUserAuthResult.getUserProfile().getSex());
				result.setAddress(onlineUserAuthResult.getUserProfile().getAddress());
				result.setProvince(onlineUserAuthResult.getUserProfile().getProvince());
				result.setCountry(onlineUserAuthResult.getUserProfile().getCountry());
				result.setTelephone(onlineUserAuthResult.getUserProfile().getTelephone());

				// HanhBQ Add
				result.setActiveStatus(onlineUserAuthResult.getUserProfile().getActiveStatus());

				if (onlineUserAuthResult.getListServiceInfo() != null) {
					vn.com.vndirect.wsclient.onlineuser.ServiceInfo[] serviceInfo = onlineUserAuthResult.getListServiceInfo();
					if (serviceInfo != null) {
						ServiceInfo[] serviceInfoArray = new ServiceInfo[serviceInfo.length];
						for (int i = 0; i < serviceInfo.length; i++) {
							serviceInfoArray[i] = ServiceInfo.getServiceInfo(serviceInfo[i]);
							if (logger.isDebugEnabled()) {
								logger.debug(LOCATION + ":: serviceInfoArray[" + i + "]: " + serviceInfoArray[i]);
							}
						}
						result.setServiceInfo(serviceInfoArray);
					}
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}
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
	 * vn.com.vndirect.business.IUserManager#updateOnlineUserPassword(vn.com
	 * .vndirect.domain.OnlineUser)
	 */
	public OnlineUser updateOnlineUserPassword(OnlineUser onlineUser) throws FunctionalException, SystemException {
		final String LOCATION = "updateOnlineUserPassword(onlineUser:" + onlineUser + ")";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		if (onlineUser == null) {
			throw new SystemException(LOCATION, "onlineUser object is NULL");
		}
		try {
			ChangePassword changePW = new ChangePassword();
			changePW.setUserName(onlineUser.getUserName());
			changePW.setPassword(onlineUser.getPassword());

			ChangePasswordResult changPWResult = this.getIOnlineUserServicePortType().changePassword(
					getVndsAuthenticationHeader(), changePW);
			VNDSServiceUtils.processMessageStatus(changPWResult == null ? null : changPWResult.getMsgStatus());
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}
			return onlineUser;
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
	 * vn.com.vndirect.business.IUserManager#getOnlineUser(vn.com.vndirect.wsclient
	 * .onlineuser.GetOnlineUser)
	 */
	public GetOnlineUserResult getOnlineUser(GetOnlineUser getOlUser) throws FunctionalException, SystemException {
		final String LOCATION = "getOnlineUser(getOlUser:" + getOlUser + ")";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		if (getOlUser == null) {
			throw new SystemException(LOCATION, "getOlUser object is NULL");
		}
		try {
			GetOnlineUserResult getOnlineUserResult = this.getIOnlineUserServicePortType().getOnlineUser(
					getVndsAuthenticationHeader(), getOlUser);
			VNDSServiceUtils.processMessageStatus(getOnlineUserResult == null ? null : getOnlineUserResult.getMsgStatus());
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}
			return getOnlineUserResult;
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

	public GetCustomerResult getCustomer(GetCustomer getCust) throws FunctionalException, SystemException {
		final String LOCATION = "getCustomer(getCust:" + getCust + ")";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		if (getCust == null) {
			throw new SystemException(LOCATION, "getCust object is NULL");
		}
		try {

			GetCustomerResult getCustomerResult = this.getCustomerServicePortType().getCustomer(this.getBoAuthenticationHeader(),
					getCust);
			BOServiceUtils.processMessageStatus(getCustomerResult == null ? null : getCustomerResult.getStatus());
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}
			return getCustomerResult;
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

	@ReadThroughSingleCache(namespace = "UserManager.readFromCache@", expiration = 600)
	public Object readFromCache(@ParameterValueKeyProvider String key, Object output) {
		return output;
	}

	@UpdateSingleCache(namespace = "UserManager.writeToCache@", expiration = 600)
	@ReturnDataUpdateContent
	public Object writeToCache(@ParameterValueKeyProvider String key, Object output) {
		return output;
	}
}
