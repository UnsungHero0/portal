package vn.com.vndirect.web.struts2.osc;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.boproxyclient.onlinetrading.Account;
import vn.com.vndirect.boproxyclient.onlinetrading.UserProfile;
import vn.com.vndirect.business.ITradingManager;
import vn.com.vndirect.business.IUserManager;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.domain.OnlineUser;
import vn.com.vndirect.domain.struts2.osc.UserProfileAJAXModel;
import vn.com.vndirect.web.security.VNDSCasUser;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.Utilities;
import vn.com.web.commons.utility.Validation;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author NguyenDucQuang
 * @version 1.0
 * @created Mar 1, 2010 5:24:07 PM
 */
@SuppressWarnings("serial")
public class UserProfileAJAXAction extends ActionSupport implements ModelDriven<UserProfileAJAXModel> {

	/* class logger */
	private static Logger logger = Logger.getLogger(UserProfileAJAXAction.class);
	private static String USER_PROFILE_CACHE_KEY = "$USER_PROFILE_CACHE_KEY$";

	/* data model */
	private UserProfileAJAXModel model = new UserProfileAJAXModel();
	
	private ITradingManager tradingManager;
	
	private IUserManager userManager;

	/**
	 * @param userManager
	 *            the userManager to set
	 */
	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	/**
	 * @param tradingManager
	 *            the tradingManager to set
	 */
	public void setTradingManager(ITradingManager tradingManager) {
		this.tradingManager = tradingManager;
	}

	/**
	 * @return the model
	 */
	public UserProfileAJAXModel getModel() {
		return model;
	}

	/**
	 * get new userProfile when change selectedAccount
	 * 
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public String executeChangeSelectedAccount() throws FunctionalException, SystemException {
		final String LOCATION = "executeChangeSelectedAccount()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			// get listAccounts
			getListAccounts(model);

			// get userProfile with new account
			String customerId = SessionManager.OnlineUsers.getBOCustomerId();
			UserProfile userProfile = tradingManager.getUserProfile(customerId, model.getSelectedAccountNumber());
			// add userProfile to session
			model.addToCache(USER_PROFILE_CACHE_KEY, userProfile);
			// add userProfile to model
			model.setUserProfile(userProfile);

			// online User infomation
			VNDSCasUser vndsCasUser = SessionManager.OnlineUsers.getCurrentOnlineUser();
			OnlineUser onlineUser = SessionManager.OnlineUsers.convert(vndsCasUser);

			model.setOnlineUser(onlineUser);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	/**
	 * get list account with online user
	 * 
	 * @param vndirectForm
	 */
	private void getListAccounts(UserProfileAJAXModel vndirectForm) {
		final String LOCATION = "getListAccounts()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}
		List<Account> listAccounts = SessionManager.OnlineUsers.getListAccounts();
		vndirectForm.setListAccounts(listAccounts);

		if (listAccounts != null && listAccounts.size() > 0) {
			String accountNumber = vndirectForm.getSelectedAccountNumber();
			if (accountNumber == null || accountNumber.length() == 0) {
				accountNumber = SessionManager.OnlineUsers.getCurrentAccountNumber();
			}

			SessionManager.OnlineUsers.setCurrentAccountNumber(accountNumber);
			vndirectForm.setSelectedAccountNumber(accountNumber);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
	}

	/**
	 * validate trading password
	 * 
	 * @return SUCCESS : if password is true ERROR-MESSAGE : if password is false
	 */
	public String executeValidateTradingPassword() {
		final String LOCATION = "executeValidateTradingPassword()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}
		String errorMessage = "";
		// get list accounts
		getListAccounts(model);

		// get online user
		VNDSCasUser vndsCasUser = SessionManager.OnlineUsers.getCurrentOnlineUser();
		OnlineUser onlineUser = SessionManager.OnlineUsers.convert(vndsCasUser);
		model.setOnlineUser(onlineUser);

		// check teleTradePassword
		UserProfile userProfile = (UserProfile) model.getFromCache(USER_PROFILE_CACHE_KEY);
		if (!Validation.isEmpty(model.getTelePasswordOld())) {
			if (!model.getTelePasswordOld().equals(userProfile.getTradePassword())) {
				errorMessage = this.getText("web.error.userProfile.old-tele-trade-pw-incorrect");
				model.setErrorMessage(errorMessage);
				return SUCCESS;
			}
		}

		// check onlinetrading password
		try {
			OnlineUser onlineUserNew = userManager.checkAuthentication(onlineUser.getUserName(), model.getTradingPassword());
			if (onlineUserNew == null) {
				errorMessage = this.getText("web.error.userProfile.trading-pw-incorrect");
			}
		} catch (Exception e) {
			errorMessage = this.getText("web.error.userProfile.trading-pw-incorrect");
		}

		model.setErrorMessage(errorMessage);
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}
}
