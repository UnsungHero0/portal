package vn.com.vndirect.web.struts2.osc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.boproxyclient.customerservice.GetCustomer;
import vn.com.vndirect.boproxyclient.customerservice.GetCustomerResult;
import vn.com.vndirect.boproxyclient.onlinetrading.Account;
import vn.com.vndirect.boproxyclient.onlinetrading.UserProfile;
import vn.com.vndirect.business.ICommonManager;
import vn.com.vndirect.business.ITradingManager;
import vn.com.vndirect.business.IUserManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.commons.web.AppContext;
import vn.com.vndirect.commons.web.AppContextHolder;
import vn.com.vndirect.domain.IfoDataRef;
import vn.com.vndirect.domain.OnlineUser;
import vn.com.vndirect.domain.RecoverPassword;
import vn.com.vndirect.domain.struts2.osc.UserProfileModel;
import vn.com.vndirect.web.security.VNDSCasUser;
import vn.com.vndirect.web.security.VNDSCasUser.Online;
import vn.com.vndirect.wsclient.onlineuser.GetOnlineUser;
import vn.com.vndirect.wsclient.onlineuser.GetOnlineUserResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.security.AppEncryptedTextProvider;
import vn.com.web.commons.security.PasswordEncode;
import vn.com.web.commons.servercfg.ServerConfig;
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
public class UserProfileAction extends ActionSupport implements ModelDriven<UserProfileModel> {

	/* Message */
	private static final String STR_INDEX_0 = "{0}";
	private static final String STR_INDEX_1 = "{1}";

	/* class logger */
	private static Logger logger = Logger.getLogger(UserProfileAction.class);

	/* data model */
	private UserProfileModel model = new UserProfileModel();

	private static String VIEW_USER_PROFILE = "ViewUserProfile";
	private static String CHANGE_TELETRADE_FONES_FW = "ChangeTeleTradeFones";
	private static String CHANGE_TELETRADE_PASSWORD_FW = "ChangeTeleTradePassword";
	private static String CHANGE_USER_PROFILE_FW = "ChangeUserProfile";
	private static String CHANGE_INTERNET_EMAIL_FW = "ChangeInternetEmail";
	private static String CHANGE_SUCCESS_FW = "ChangeSuccess";
	private static String CONFIRM_ACTION = "CONFIRM";
	private static String USER_PROFILE_CACHE_KEY = "$USER_PROFILE_CACHE_KEY$";

	private ITradingManager tradingManager;
	private IUserManager userManager;
	@Autowired
	private ICommonManager commonManager;

	private String navigation;

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

	public void setCommonManager(ICommonManager commonManager) {
		this.commonManager = commonManager;
	}

	/**
	 * @return the model
	 */
	public UserProfileModel getModel() {
		return model;
	}

	/**
	 * get teleTradeFones and display on screen
	 * 
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public String changeUserProfile() {
		final String LOCATION = "changeUserProfile()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}
		String successKey = "";
		OnlineUser onlineUser = null;
		try {
			if (Validation.isEmpty(model.getCaller()) && Validation.isEmpty(model.getUserAction())) {
				// prepare data
				prepareData();
			}

			// navigation
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			if (navigation.equals("profile")) {
				breadcrumbs.add(this.getText("user.inforProfile"));
			} else if (navigation.equals("tradding")) {
				breadcrumbs.add(this.getText("user.inforTrading"));
			} else {
				breadcrumbs.add(this.getText("no"));
			}

			breadcrumbs.add(this.getText("user.myPage"));
			breadcrumbs.add("#");
			breadcrumbs.add(this.getText("user.inforManage"));
			breadcrumbs.add("/thong-tin-co-ban.shtml");
			model.setBreadcrumbs(breadcrumbs);

			if (navigation.equals("profile")) {
				// seopage
				model.setPageTitle(this.getText("user.inforProfile.title"));
				model.setPageDescription(this.getText("user.inforProfile.desc"));
				model.setPageKeywords(this.getText("user.inforProfile.keywords"));
			} else if (navigation.equals("tradding")) {
				// seopage
				model.setPageTitle(this.getText("user.inforTrading.title"));
				model.setPageDescription(this.getText("user.inforTrading.desc"));
				model.setPageKeywords(this.getText("user.inforTrading.keywords"));
			}

			// validate
			updateData();

			// get userProfile and update
			UserProfile userProfile = (UserProfile) model.getFromCache(USER_PROFILE_CACHE_KEY);
			String customerId = SessionManager.OnlineUsers.getBOCustomerId();
			String action = model.getUserAction();
			if (userProfile == null && customerId != null && customerId.length() > 0) {
				// get from service
				userProfile = tradingManager.getUserProfile(customerId, model.getSelectedAccountNumber());
				model.addToCache(USER_PROFILE_CACHE_KEY, userProfile);
			}

			// online User infomation
			VNDSCasUser vndsCasUser = SessionManager.OnlineUsers.getCurrentOnlineUser();
			onlineUser = SessionManager.OnlineUsers.convert(vndsCasUser);

			model.setOnlineUser(onlineUser);
			model.setUserProfile(userProfile);

			// check if do active
			HttpServletRequest request = ServletActionContext.getRequest();
			String key = request.getParameter("key");
			if (!Validation.isEmpty(key)) {
				logger.debug(LOCATION + ":: KEY=" + key);
				// check validate key
				AppEncryptedTextProvider encryptedProvider = new AppEncryptedTextProvider();
				encryptedProvider.parseEncryptedData(key, "email");
				String newEmail = encryptedProvider.getValue("emai_encode");
				// System.out.println("newEmail:: " + newEmail);
				String[] keyArr = newEmail.split("#");

				if (keyArr.length < 2 || !keyArr[1].equals(userProfile.getCustomerId())) {
					this.addActionMessage("Messages.Account.CellNames.MyInfo.InvalidKey");
				} else {
					tradingManager.updateOnlineUserEmail(onlineUser, key, true, false);
					this.addActionMessage("Messages.Account.CellNames.MyInfo.ChangeEmailSuccess");

					vndsCasUser.put(Online.EMAIL.key, keyArr[0]);
					AppContext appCtx = AppContextHolder.get();
					appCtx.addAttr(VNDSCasUser.CAS_USER_APP_CTX_KEY, vndsCasUser);
					onlineUser = SessionManager.OnlineUsers.convert(vndsCasUser);
					model.setOnlineUser(onlineUser);
				}
				model.setUserName(onlineUser.getUserName());
				if (logger.isDebugEnabled()) {
					logger.debug(LOCATION + ":: END");
				}
				return VIEW_USER_PROFILE;
			}

			// return
			String caller = model.getCaller();

			if (!Validation.isEmpty(caller) && !Validation.isEmpty(action)) {
				if (CHANGE_INTERNET_EMAIL_FW.equalsIgnoreCase(caller)) {
					tradingManager.updateOnlineUserEmail(onlineUser, model.getNewEmail() + "#" + userProfile.getCustomerId(), false, true);
				} else {
					// do update
					tradingManager.updateUserProfile(userProfile);
				}

				// show mess when update success.
				if (CHANGE_TELETRADE_FONES_FW.equalsIgnoreCase(model.getCaller())) {
					successKey = getText("web.message.userProfile.UpdateTeleTraddingSuccess");
				} else if (CHANGE_TELETRADE_PASSWORD_FW.equalsIgnoreCase(model.getCaller())) {
					successKey = getText("web.message.userProfile.UpdateTeleTradPwSuccess");
					// send mail after change teletrade password
					tradingManager.sendEmailAfterChangeTeleTradePwd(onlineUser.getOnlineUserId());
				} else if (CHANGE_USER_PROFILE_FW.equalsIgnoreCase(model.getCaller())) {
					successKey = getText("web.message.userProfile.UpdateUserProfileSuccess");
				} else if (CHANGE_INTERNET_EMAIL_FW.equalsIgnoreCase(model.getCaller())) {
					successKey = getText("web.message.userProfile.NotifyEmailSuccess");
				} else {
					successKey = getText("web.message.userProfile.ChangeSuccessful");
				}

			}

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (CONFIRM_ACTION.equalsIgnoreCase(model.getUserAction())) {
			model.setSuccessKey(successKey);
			return CHANGE_SUCCESS_FW;
		} else {
			this.addActionMessage(successKey);
		}

		if (CHANGE_TELETRADE_FONES_FW.equalsIgnoreCase(model.getCaller())) {
			return CHANGE_TELETRADE_FONES_FW;
		} else if (CHANGE_TELETRADE_PASSWORD_FW.equalsIgnoreCase(model.getCaller())) {
			return CHANGE_TELETRADE_PASSWORD_FW;
		} else if (CHANGE_USER_PROFILE_FW.equalsIgnoreCase(model.getCaller())) {
			return CHANGE_USER_PROFILE_FW;
		} else if (CHANGE_INTERNET_EMAIL_FW.equalsIgnoreCase(model.getCaller())) {
			return CHANGE_INTERNET_EMAIL_FW;
		} else {
			if (onlineUser != null)
				model.setUserName(onlineUser.getUserName());
			return VIEW_USER_PROFILE;
		}

	}

	/**
	 * validate trading password upgrade new data to userProfile
	 * 
	 * @return message
	 */
	private void updateData() {
		// get list accounts
		getListAccounts(model);

		// get online user
		// online User infomation
		VNDSCasUser vndsCasUser = SessionManager.OnlineUsers.getCurrentOnlineUser();
		OnlineUser onlineUser = SessionManager.OnlineUsers.convert(vndsCasUser);

		model.setOnlineUser(onlineUser);

		if (!Validation.isEmpty(model.getUserAction())) {
			// update data to userProfile
			UserProfile userProfile = (UserProfile) model.getFromCache(USER_PROFILE_CACHE_KEY);
			UserProfile userProfile2Update = model.getUserProfile();
			String caller = model.getCaller();
			if (CHANGE_TELETRADE_FONES_FW.equalsIgnoreCase(caller)) {
				// update userprofile in session
				userProfile.setTradeFone1((userProfile2Update.getTradeFone1()));
				userProfile.setTradeFone2(userProfile2Update.getTradeFone2());
				userProfile.setSmsFone(userProfile2Update.getSmsFone());
				model.addToCache(USER_PROFILE_CACHE_KEY, userProfile);
				model.setUserProfile(userProfile);
			} else if (CHANGE_TELETRADE_PASSWORD_FW.equalsIgnoreCase(caller)) {
				// set telepass
				userProfile.setTradePassword(model.getTelePasswordNew());
				model.addToCache(USER_PROFILE_CACHE_KEY, userProfile);
				model.setUserProfile(userProfile);
			} else if (CHANGE_USER_PROFILE_FW.equalsIgnoreCase(caller)) {
				// update userprofile in session
				userProfile.setAddress(userProfile2Update.getAddress());
				userProfile.setEmailConfirm(userProfile2Update.getEmailConfirm());
				userProfile.setHomeFone(userProfile2Update.getHomeFone());
				userProfile.setMobileFone(userProfile2Update.getMobileFone());
				model.addToCache(USER_PROFILE_CACHE_KEY, userProfile);
				model.setUserProfile(userProfile);
			} else if (CHANGE_INTERNET_EMAIL_FW.equalsIgnoreCase(caller)) {
				// do nothing
			}
		}
	}

	/**
	 * prepare data for userprofile action get list accounts by customerId and set to sessions
	 * 
	 * @param userProfileModel
	 * @param request
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	private void prepareData() throws FunctionalException, SystemException {
		List<Account> listAccounts = null;
		String custId = SessionManager.OnlineUsers.getBOCustomerId();
		if (custId != null && custId.length() > 0) {
			// get list accounts from webservices.
			listAccounts = tradingManager.getAccountRecords(custId);
		} else {
			listAccounts = new ArrayList<Account>();
		}
		// add list of accounts to session
		SessionManager.OnlineUsers.setListAccounts(listAccounts);

	}

	/**
	 * get list accounts
	 * 
	 * @param vndirectForm
	 */
	private void getListAccounts(UserProfileModel vndirectForm) {
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
	 * forward to change password screen
	 * 
	 * @return
	 */
	public String viewChangePassword() {
		// navigation
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("user.inforTrading"));
		breadcrumbs.add(this.getText("user.myPage"));
		breadcrumbs.add("#");
		breadcrumbs.add(this.getText("user.inforManage"));
		breadcrumbs.add("/thong-tin-co-ban.shtml");
		model.setBreadcrumbs(breadcrumbs);

		// seopage
		model.setPageTitle(this.getText("user.inforTrading.title"));
		model.setPageDescription(this.getText("user.inforTrading.desc"));
		model.setPageKeywords(this.getText("user.inforTrading.keywords"));

		return SUCCESS;
	}

	/**
	 * change password login
	 * 
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public String changePassword() {
		final String LOCATION = "changePassword()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}
		try {
			// online User infomation
			VNDSCasUser vndsCasUser = SessionManager.OnlineUsers.getCurrentOnlineUser();
			OnlineUser onlineUser = SessionManager.OnlineUsers.convert(vndsCasUser);

			onlineUser.setPassword(model.getNewPassword());
			userManager.updateOnlineUserPassword(onlineUser);

			// reset encrypt password for next changing password
			onlineUser.setPassword(PasswordEncode.encryptPassword(model.getNewPassword()));

			// set message
			model.setSuccessKey(getText("web.message.userProfile.ChangePassSuccess"));

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		// navigation
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("user.inforTrading"));
		breadcrumbs.add(this.getText("user.myPage"));
		breadcrumbs.add("#");
		breadcrumbs.add(this.getText("user.inforManage"));
		breadcrumbs.add("/thong-tin-co-ban.shtml");
		model.setBreadcrumbs(breadcrumbs);

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String viewRecoverUserInfo() throws FunctionalException, SystemException {
		final String LOCATION = "viewRecoverUserInfo()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}
		try {
			// do SEO on-page
			model.setPageTitle(this.getText("home.recoverPassword.title"));

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("home.recoverPassword.br"));
			model.setBreadcrumbs(breadcrumbs);

			// init data
			IfoDataRef searchRefDataObj = new IfoDataRef();
			searchRefDataObj.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			searchRefDataObj.setGroupCode(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.GroupCodes.IDENTIFICATION_TYPE));
			List tempList;
			tempList = commonManager.getDataRefByGroupCode(searchRefDataObj);
			model.setIdentificationTypeList(tempList);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String executeRecoverUserInfo() {
		final String LOCATION = "executeRecoverUserInfo()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		// do SEO on-page
		model.setPageTitle(this.getText("home.recoverPassword.title"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("home.recoverPassword.br"));
		model.setBreadcrumbs(breadcrumbs);

		// init data
		IfoDataRef searchRefDataObj = new IfoDataRef();
		searchRefDataObj.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
		searchRefDataObj.setGroupCode(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.GroupCodes.IDENTIFICATION_TYPE));
		List tempList;
		try {
			tempList = commonManager.getDataRefByGroupCode(searchRefDataObj);
			model.setIdentificationTypeList(tempList);
		} catch (Exception e) {
			logger.error(LOCATION, e);
		}
		if (validateDataForRecoverUserInfo()) {
			RecoverPassword recoverPassword = model.getRecoverPassword();
			Map<String, String> userInfo = new HashMap<String, String>();
			boolean isFound = false;
			try {
				if ("VNDIRECTUser".equals(model.getAccountType())) {
					try {
						// For online user
						recoverPassword.setUserType("O");
						// Load OnlineUser
						GetOnlineUser getOlUser = new GetOnlineUser();
						getOlUser.setUserName(recoverPassword.getUserName());
						GetOnlineUserResult onlineUserAuthResult = userManager.getOnlineUser(getOlUser);
						if (onlineUserAuthResult != null && onlineUserAuthResult.getOnlineUser() != null && "ONLINE_ACTIVE".equals(onlineUserAuthResult.getOnlineUser().getStatus())) {
							userInfo.put("FullName", onlineUserAuthResult.getOnlineUser().getFullName());
							userInfo.put("Email", onlineUserAuthResult.getOnlineUser().getEmail());
							GetCustomer getCust = new GetCustomer();
							getCust.setCustomerId(onlineUserAuthResult.getOnlineUser().getCustomerId());
							GetCustomerResult custResult = userManager.getCustomer(getCust);
							if (custResult != null && custResult.getCustomerInfo() != null) {
								if (!recoverPassword.getIdentificationType().equals(convertIdentificationType(custResult.getCustomerInfo().getIdentificationType()))) {
									// Define message for invalid type
									this.addActionError(getText("web.label.userProfile.RecoverUserInfo.Message.InvalidIdentificationType"));
									return INPUT;
								}
								if (!recoverPassword.getIdentificationCode().equals(custResult.getCustomerInfo().getIdentificationCode())) {
									// Define message for invalid code
									this.addActionError(getText("web.label.userProfile.RecoverUserInfo.Message.InvalidIdentificationCode"));
									return INPUT;
								}
								if (!this.hasActionErrors()) {
									isFound = true;
								}
							} else {
								this.addActionError(getText("web.label.userProfile.RecoverUserInfo.Message.UnExitUser"));
								return INPUT;
							}
						}
					} catch (Exception e) {
						logger.error(LOCATION, e);
					}
				} else {
					try {
						// For free user
						recoverPassword.setUserType("F");
						GetOnlineUser getOlUser = new GetOnlineUser();
						getOlUser.setUserName(recoverPassword.getUserName());
						GetOnlineUserResult onlineUserAuthResult = userManager.getOnlineUser(getOlUser);
						if (onlineUserAuthResult != null && onlineUserAuthResult.getOnlineUser() != null && "ONLINE_FREE_REGISTER".equals(onlineUserAuthResult.getOnlineUser().getStatus())) {
							userInfo.put("FullName", onlineUserAuthResult.getOnlineUser().getFullName());
							userInfo.put("Email", onlineUserAuthResult.getOnlineUser().getEmail());
							isFound = true;
						}
					} catch (Exception e) {
						logger.error(LOCATION, e);
					}
				}
				boolean isSuccess = false;
				if (isFound) {
					isSuccess = tradingManager.recoverPassword(recoverPassword);
				}

				if (isSuccess) {

					String messageFullName = this.getText("web.label.userProfile.RecoverUserInfo.Message2VNDSUser.FullName");
					String messageEmail = this.getText("web.label.userProfile.RecoverUserInfo.Message2VNDSUser.Email");

					messageFullName = VNDirectWebUtilities.replaceString(messageFullName, STR_INDEX_0, userInfo.get("FullName").toString());
					messageEmail = VNDirectWebUtilities.replaceString(messageEmail, STR_INDEX_1, userInfo.get("Email").toString());
					model.setMessageFullName(messageFullName);
					model.setMessageEmail(messageEmail);
				} else {
					this.addActionError(getText("web.label.userProfile.RecoverUserInfo.Message.UnExitUser"));
					return INPUT;
				}

			} catch (Exception e) {
				Utilities.processErrors(this, e);
				logger.error(LOCATION + ":: Exception:: " + e);
				return INPUT;
			}
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	protected boolean validateDataForRecoverUserInfo() {
		final String LOCATION = "validateDataForRecoverUserInfo()";
		logger.debug(LOCATION + ":: BEGIN");

		RecoverPassword recoverPassword = model.getRecoverPassword();
		String str = recoverPassword.getUserName();
		if (Validation.isEmpty(str)) {
			this.addActionError(getText("web.label.userProfile.RecoverUserInfo.Message.NoUser"));
		}
		str = model.getAccountType();
		if (Validation.isEmpty(str)) {
			this.addActionError(getText("web.label.userProfile.RecoverUserInfo.Message.NoAccountType"));
		}
		if ("VNDIRECTUser".equals(model.getAccountType()) || "WebAgent".equals(model.getAccountType())) {
			str = recoverPassword.getContractNumber();
			if (Validation.isEmpty(str)) {
				this.addActionError(getText("web.label.userProfile.RecoverUserInfo.Message.NoContractNumber"));
			}
			str = recoverPassword.getIdentificationType();
			if (Validation.isEmpty(str)) {
				this.addActionError(getText("web.label.userProfile.RecoverUserInfo.Message.NoIdentificationType"));
			}
			str = recoverPassword.getIdentificationCode();
			if (Validation.isEmpty(str)) {
				this.addActionError(getText("web.label.userProfile.RecoverUserInfo.Message.NoIdentificationCode"));
			}
			str = recoverPassword.getIdgSerialCard();
			if (Validation.isEmpty(str)) {
				this.addActionError(getText("web.label.userProfile.RecoverUserInfo.Message.NoSerialCard"));
			}
		}
		logger.debug(LOCATION + ":: END");
		return this.hasActionErrors() ? false : true;
	}

	private String convertIdentificationType(String identificationType) {
		String sReturn = "";
		if ("001".equals(identificationType)) {
			sReturn = "IDCARD";
		} else {
			sReturn = "PASSPORT";
		}
		return sReturn;
	}

	public String getNavigation() {
		return navigation;
	}

	public void setNavigation(String navigation) {
		this.navigation = navigation;
	}
}
