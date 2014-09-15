/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Aug 31, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.business;

import java.util.List;

import vn.com.vndirect.boproxyclient.onlinetrading.Account;
import vn.com.vndirect.boproxyclient.onlinetrading.UpdateUserProfileResult;
import vn.com.vndirect.boproxyclient.onlinetrading.UserProfile;
import vn.com.vndirect.domain.OnlineUser;
import vn.com.vndirect.domain.RecoverPassword;
import vn.com.vndirect.wsclient.onlineuser.ChangeEmailResult;
import vn.com.vndirect.wsclient.onlineuser.SendEmailResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

public interface ITradingManager extends IBaseManager {
	/**
	 * this method is used to get all Account of customer.
	 * 
	 * @param header
	 * @param customerId
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List<Account> getAccountRecords(String customerId) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param header
	 * @param customerId
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public UserProfile getUserProfile(String customerId, String accountNumber) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param header
	 * @param userProfile
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public UpdateUserProfileResult updateUserProfile(UserProfile userProfile) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param header
	 * @param userOnlineId
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SendEmailResult sendEmailAfterChangeTeleTradePwd(long userOnlineId) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param header
	 * @param onlineUser
	 * @param newEmail
	 * @param isUpdate
	 * @param isNotify
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public ChangeEmailResult updateOnlineUserEmail(OnlineUser onlineUser, String newEmail, boolean isUpdate, boolean isNotify)
	        throws FunctionalException, SystemException;

	/**
	 * @param vndsAuthHeader
	 * @param recoverPassword
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public boolean recoverPassword(RecoverPassword recoverPassword) throws FunctionalException, SystemException;
}