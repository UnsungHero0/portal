/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Aug 31, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.business;

import java.util.List;
import java.util.Map;

import vn.com.vndirect.domain.OnlineAccount;
import vn.com.vndirect.domain.OnlineUser;
import vn.com.vndirect.domain.crm.leadcontract.ConvertLeadToCustomerResult;
import vn.com.vndirect.domain.crm.leadcontract.DisableLeadCustomerResult;
import vn.com.vndirect.domain.crm.leadcontract.ProxyCreateLeadContractOutput;
import vn.com.vndirect.wsclient.onlineuser.CheckExistedUserName;
import vn.com.vndirect.wsclient.onlineuser.CheckExistedUserNameResult;
import vn.com.vndirect.wsclient.onlineuser.RegisterOnlineUserResult;
import vn.com.vndirect.wsclient.openaccount.OnlineAccountAttribute;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

/**
 * @author tungnq
 * 
 */
public interface IAccountManager extends IBaseManager {

	/**
	 * 
	 * @param onlineUser
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public boolean createFreeRegistedUsers(OnlineUser onlineUser) throws FunctionalException, SystemException;

	/**
	 * Create an online account and store it into database
	 * 
	 * @param account
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	// public void createOnlineAccount(List<OnlineAccountAttribute> params)
	// throws FunctionalException, SystemException;
	public ProxyCreateLeadContractOutput createOnlineAccount(List<OnlineAccountAttribute> params) throws FunctionalException,
			SystemException;

	/**
	 * @param onlineAccount
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public Map<String, String> retrievePendingAccount(OnlineAccount onlineAccount) throws FunctionalException, SystemException;
	
	public CheckExistedUserNameResult checkExistedUsername(CheckExistedUserName checkExistedUserName) throws FunctionalException, SystemException;
	
	public ConvertLeadToCustomerResult activateFreeOnlineUser(String leadId) throws FunctionalException, SystemException;
	
	public RegisterOnlineUserResult getUserByActiveKey(String activeKey) throws FunctionalException, SystemException;
	
	public DisableLeadCustomerResult deniedActivatingOnlineUser(vn.com.vndirect.wsclient.onlineuser.OnlineUser onlineUser) throws FunctionalException, SystemException;
	
	public void createFreeOnlineAccount(List<OnlineAccountAttribute> params) throws FunctionalException, SystemException;
}
