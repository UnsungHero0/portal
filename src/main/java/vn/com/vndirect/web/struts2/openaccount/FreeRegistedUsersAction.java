package vn.com.vndirect.web.struts2.openaccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import vn.com.vndirect.business.IAccountManager;
import vn.com.vndirect.commons.utility.VNDirectDateUtils;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.OnlineUser;
import vn.com.vndirect.domain.struts2.openaccount.FreeRegistedUsersModel;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.Validation;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FreeRegistedUsersAction extends ActionSupport implements ModelDriven<FreeRegistedUsersModel> {

	private static final long serialVersionUID = -6267288210747469857L;
	private static Logger logger = Logger.getLogger(FreeRegistedUsersAction.class);

	private static final String STR_INDEX_0 = "{0}";
	private static final String STR_INDEX_1 = "{1}";

	private FreeRegistedUsersModel model = new FreeRegistedUsersModel();

	private IAccountManager accountManager;

	public FreeRegistedUsersModel getModel() {
		return model;
	}

	public void setModel(FreeRegistedUsersModel model) {
		this.model = model;
	}

	public void setAccountManager(IAccountManager accountManager) {
		this.accountManager = accountManager;
	}

	public String showFreeRegistedUsers() throws Exception {
		final String LOCATION = "showFreeRegistedUsers()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");

			// this.loadInformation(model);

		}

		// do SEO on-page
		model.setPageTitle(this.getText("home.account.open.free.input.title"));
		model.setPageDescription(this.getText("home.account.open.free.input.desc"));
		model.setPageKeywords(this.getText("home.account.open.free.input.keywords"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.service.openaccount.free1.br"));
		breadcrumbs.add(this.getText("br.service"));
		breadcrumbs.add("spdvRoot");
		breadcrumbs.add(this.getText("br.service.openaccount"));
		breadcrumbs.add("/mo-tai-khoan.shtml");
		breadcrumbs.add(this.getText("br.service.openaccount.free"));
		breadcrumbs.add("/mo-tai-khoan-mien-phi.shtml");
		model.setBreadcrumbs(breadcrumbs);

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

	// private void loadInformation(FreeRegistedUsersModel model) throws
	// FunctionalException, SystemException {
	// final String LOCATION =
	// "firstLoadInformation(FreeRegistedUsersModel model)";
	// logger.debug(LOCATION + ":: BEGIN");
	//
	// HttpServletRequest request = ServletActionContext.getRequest();
	// IfoDataRef searchRefDataObj = new IfoDataRef();
	// searchRefDataObj.setLocale(I18NUtility.getCurrentLanguage(request.getSession()).getCode());
	//
	// List tempList;
	// // get SEX
	// searchRefDataObj.setGroupCode(ServerConfig.getOnlineValue(Constants.ServerConfig.DataRef.GroupCodes.SEX));
	// tempList = commonManager.getDataRefByGroupCode(searchRefDataObj);
	// model.setSexList(tempList);
	// model.addToCache(LIST_SEX_CACHE_KEY, tempList);
	//
	// // get COUNTRY
	// searchRefDataObj.setGroupCode(ServerConfig.getOnlineValue(Constants.ServerConfig.DataRef.GroupCodes.COUNTRY));
	// tempList = commonManager.getDataRefByGroupCode(searchRefDataObj);
	// model.setCountryList(tempList);
	// model.addToCache(LIST_COUNTRY_CACHE_KEY, tempList);
	//
	// logger.debug(LOCATION + ":: END");
	// }

	public String executeFreeRegistedUsers() throws FunctionalException, SystemException {
		final String LOCATION = "executeFreeRegistedUsers()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		// do SEO on-page
		model.setPageTitle(this.getText("home.account.open.free.register.title"));
		model.setPageDescription(this.getText("home.account.open.free.register.desc"));
		model.setPageKeywords(this.getText("home.account.open.free.register.keywords"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.service.openaccount.free2"));
		breadcrumbs.add(this.getText("br.service"));
		breadcrumbs.add("spdvRoot");
		breadcrumbs.add(this.getText("br.service.openaccount"));
		breadcrumbs.add("/mo-tai-khoan.shtml");
		breadcrumbs.add(this.getText("br.service.openaccount.free"));
		breadcrumbs.add("/mo-tai-khoan-mien-phi.shtml");
		model.setBreadcrumbs(breadcrumbs);

		// this.loadInformation(model);
		if (validationFreeRegistedUsersInfo()) {
			try {
				Map<String, String> userInfo = new HashMap<String, String>();
				OnlineUser onlineUser = model.getOnlineUser();
				onlineUser.setPassword(model.getPassWord());
				onlineUser.setUserName(onlineUser.getEmail());

				boolean isSuccess = accountManager.createFreeRegistedUsers(onlineUser);

				if (isSuccess) {
					userInfo.put("FullName", onlineUser.getOnlineUser().getFullName());
					userInfo.put("Email", onlineUser.getOnlineUser().getEmail());

					String messageFullName = this.getText("web.label.FreeRegistedUsersAction.MessageRegisterSuccess.FullName");
					String messageEmail = this.getText("web.label.FreeRegistedUsersAction.MessageRegisterSuccess.Email");

					messageFullName = VNDirectWebUtilities.replaceString(messageFullName, STR_INDEX_0, userInfo.get("FullName")
							.toString());
					messageEmail = VNDirectWebUtilities
							.replaceString(messageEmail, STR_INDEX_1, userInfo.get("Email").toString());

					model.setMessageFullName(messageFullName);
					model.setMessageEmail(messageEmail);
					if (logger.isDebugEnabled()) {
						logger.debug("==========>User Info::" + messageFullName + "--" + messageEmail);
					}
				} else {
					this.addActionError(getText("web.label.FreeRegistedUsersAction.Message.EmailUsed"));
					return INPUT;
				}

			} catch (Exception e) {
				// Utilities.processErrors(this, e);
				this.addActionError(getText("web.label.FreeRegistedUsersAction.Message.EmailUsed"));
				logger.error(LOCATION + ":: Exception:: " + e);
				return INPUT;
			}
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	protected boolean validationFreeRegistedUsersInfo() {
		final String LOCATION = "validationFreeRegistedUsersInfo()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		OnlineUser onlineUser = model.getOnlineUser();
		if (onlineUser != null) {
			String str;
			// check full name
			str = VNDirectWebUtilities.cleanString(onlineUser.getFullName());
			onlineUser.setFullName(str);
			if (Validation.isEmpty(str)) {
				this.addActionError(getText("web.label.FreeRegistedUsersAction.Message.FullNameRequired"));
			}
			// Check email
			str = VNDirectWebUtilities.cleanString(onlineUser.getEmail());
			onlineUser.setEmail(str);
			if (Validation.isEmpty(str)) {
				this.addActionError(getText("web.label.FreeRegistedUsersAction.Message.EmailRequired"));
			} else if (str.length() > 0 && !Validation.isValidEmail(str)) {
				this.addActionError(getText("web.label.FreeRegistedUsersAction.Message.EmailInvalid"));
			}
			// Check password
			str = VNDirectWebUtilities.cleanString(model.getPassWord());
			if (Validation.isEmpty(str)) {
				this.addActionError(getText("web.label.FreeRegistedUsersAction.Message.PasswordRequired"));
			} else if (!str.equals(model.getConfirmPass())) {
				this.addActionError(getText("web.label.FreeRegistedUsersAction.Message.ConfirmPasswordIncorrect"));
			}
			// Password must be 6 to 32 characters long and contain at least one
			// number.
			if (str.length() < 6 || str.length() > 32 || !Validation.isLetterAndDigit(str)) {
				this.addActionError(getText("web.label.FreeRegistedUsersAction.Message.PasswordInvalid"));
			}
			// Check Date of birth
			str = VNDirectWebUtilities.cleanString(onlineUser.getStrDob());
			if (str.length() > 0) {
				if (!VNDirectDateUtils.isValidPattern(str, VNDirectDateUtils.Patterns.DATE_DDMMYYY_SEPARATOR_PATTERN_2))
					this.addActionError(getText("web.label.FreeRegistedUsersAction.Message.DateOfBirthInvalid"));
			}
			// Check Agreement
			str = VNDirectWebUtilities.cleanString(model.getAgreement());
			if (str.length() == 0) {
				this.addActionError(getText("web.label.FreeRegistedUsersAction.Message.AgreementRequired"));
			}

			if (logger.isDebugEnabled()) {
				logger.debug("========>>>FreeRegistedUsersInfo Error::" + this.hasActionErrors());
				logger.debug(LOCATION + ":: END");
			}
		}

		return this.hasActionErrors() ? false : true;
	}

}
