/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Aug 31, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.business;

import vn.com.vndirect.boproxyclient.customerservice.GetCustomer;
import vn.com.vndirect.boproxyclient.customerservice.GetCustomerResult;
import vn.com.vndirect.domain.OnlineUser;
import vn.com.vndirect.wsclient.onlineuser.GetOnlineUser;
import vn.com.vndirect.wsclient.onlineuser.GetOnlineUserResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

import com.google.code.ssm.api.ParameterValueKeyProvider;

/**
 * @author tungnq
 * 
 */
public interface IUserManager extends IBaseManager {
	/**
	 * 
	 * @param userName
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public OnlineUser checkAuthentication(String userName, String password) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param onlineUser
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public OnlineUser updateOnlineUserPassword(OnlineUser onlineUser) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param getOlUser
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public GetOnlineUserResult getOnlineUser(GetOnlineUser getOlUser) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param webAgentUser
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	// public GetWebAgentUserResult getWebAgentUser(GetWebAgentUser
	// webAgentUser) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param getCust
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public GetCustomerResult getCustomer(GetCustomer getCust) throws FunctionalException, SystemException;

	/**
	 * @param key
	 * @param output
	 * @return
	 */
	public Object readFromCache(@ParameterValueKeyProvider String key, Object output);

	/**
	 * @param key
	 * @param output
	 * @return
	 */
	public Object writeToCache(@ParameterValueKeyProvider String key, Object output);
}
