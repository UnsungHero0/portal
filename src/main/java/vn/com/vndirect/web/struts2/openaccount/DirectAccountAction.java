/**
 * 
 */
package vn.com.vndirect.web.struts2.openaccount;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IAccountManager;
import vn.com.vndirect.business.IEmailServiceManager;
import vn.com.vndirect.business.IOnlineUserServiceManager;
import vn.com.vndirect.commons.utility.CRMConstants;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.DBConstants;
import vn.com.vndirect.commons.utility.VNDirectDateUtils;
import vn.com.vndirect.commons.vndsservice.VNDSMessageKeyUtils;
import vn.com.vndirect.domain.OnlineAccount;
import vn.com.vndirect.domain.crm.leadcontract.ConvertLeadToCustomerResult;
import vn.com.vndirect.domain.crm.leadcontract.DisableLeadCustomerResult;
import vn.com.vndirect.domain.crm.leadcontract.ProxyCreateLeadContractOutput;
import vn.com.vndirect.domain.struts2.analysistools.DirectAccountModel;
import vn.com.vndirect.service.bank.IBanksServiceManager;
import vn.com.vndirect.service.crm.ICRMServiceManager;
import vn.com.vndirect.wsclient.onlineuser.AssignCardException;
import vn.com.vndirect.wsclient.onlineuser.AssignCardResult;
import vn.com.vndirect.wsclient.onlineuser.CheckExistedUserName;
import vn.com.vndirect.wsclient.onlineuser.CheckExistedUserNameResult;
import vn.com.vndirect.wsclient.onlineuser.CreateIDGException;
import vn.com.vndirect.wsclient.onlineuser.OnlineUser;
import vn.com.vndirect.wsclient.onlineuser.RegisterOnlineUserResult;
import vn.com.vndirect.wsclient.onlineuser.SendEmailForSupporterType;
import vn.com.vndirect.wsclient.openaccount.OnlineAccountAttribute;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

import com.homedirect.proxy.PaydirectWebservice.Bank;
import com.homedirect.proxy.PaydirectWebservice.Branch;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * @author Huy
 * 
 */
public class DirectAccountAction extends ActionSupport implements ModelDriven<DirectAccountModel> {
	private static Logger logger = Logger.getLogger(DirectAccountAction.class);
	private static final long serialVersionUID = 2928310215851864320L;
	private static final String pendingActiveStatus = Constants.IServerConfig.OpenOnlineUser.ActiveStatus.PENDING;
	private static final String processingActiveStatus = Constants.IServerConfig.OpenOnlineUser.ActiveStatus.PROCESSING;
	private static final String finishActiveStatus = Constants.IServerConfig.OpenOnlineUser.ActiveStatus.FINISH;
	private static final String errorActiveStatus = Constants.IServerConfig.OpenOnlineUser.ActiveStatus.ERROR;
	private DirectAccountModel model = new DirectAccountModel();
	private final String DIRECT_ACCOUNT = "$DIRECT_ACCOUNT$";
	private IAccountManager manager;

	@Autowired
	private IOnlineUserServiceManager onlineUserServiceManager;

	@Autowired
	private IEmailServiceManager emailServiceManager;

	@Autowired
	private ICRMServiceManager crmServiceManager;
	
	@Autowired
	private ReCaptcha reCaptcha;
	
	@Autowired
	private IBanksServiceManager banksServiceManager;

	/**
	 * @param manager
	 *            the manager to set
	 */
	public void setManager(IAccountManager manager) {
		this.manager = manager;
	}

	@Deprecated
	public String showOnlineRegistedUsers() {
		doSEOpage();
		final String LOCATION = "showOnlineRegistedUsers()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		OnlineAccount account = (OnlineAccount) model.getFromCache(DIRECT_ACCOUNT);
		if (account == null) {
			// set default selected
			account = new OnlineAccount();
			account.setSex("MALE");
			account.setMarried("SINGLE");

			account.setNational("VNM");
			account.setInvestorType("local");
			account.setTradingAccounts(Arrays.asList(new String[] { "1", "2" }));
			account.setTradingMethods(Arrays.asList(new String[] { "Online", "Phone" }));
		}
		model.setAccount(account);

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}

		return SUCCESS;
	}

	/**
	 * Display direct account input screen in step 1
	 * 
	 * @return SUCCESS
	 */
	@Deprecated
	public String stepOne() {
		final String LOCATION = "stepOne()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		// do SEO on-page
		model.setPageTitle(this.getText("home.account.open.directAccount.step1.title"));
		model.setPageDescription(this.getText("home.account.open.directAccount.step1.desc"));
		model.setPageKeywords(this.getText("home.account.open.directAccount.step1.keywords"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.service.openaccount.direct1"));
		breadcrumbs.add(this.getText("br.service"));
		breadcrumbs.add("spdvRoot");
		breadcrumbs.add(this.getText("br.service.openaccount"));
		breadcrumbs.add("/mo-tai-khoan.shtml");
		breadcrumbs.add(this.getText("br.service.openaccount.direct"));
		breadcrumbs.add("/mo-tai-khoan-nha-dau-tu.shtml");
		model.setBreadcrumbs(breadcrumbs);

		OnlineAccount account = (OnlineAccount) model.getFromCache(DIRECT_ACCOUNT);
		if (account == null) {
			// set default selected
			account = new OnlineAccount();
			account.setSex("MALE");
			account.setMarried("SINGLE");
		}
		model.setAccount(account);

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

	/**
	 * Display direct account input screen in step 2
	 * 
	 * @return SUCCESS
	 */
	@Deprecated
	public String stepTwo() throws Exception {
		final String LOCATION = "stepTwo()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		// do SEO on-page
		model.setPageTitle(this.getText("home.account.open.directAccount.step2.title"));
		model.setPageDescription(this.getText("home.account.open.directAccount.step2.desc"));
		model.setPageKeywords(this.getText("home.account.open.directAccount.step2.keywords"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.service.openaccount.direct2"));
		breadcrumbs.add(this.getText("br.service"));
		breadcrumbs.add("spdvRoot");
		breadcrumbs.add(this.getText("br.service.openaccount"));
		breadcrumbs.add("/mo-tai-khoan.shtml");
		breadcrumbs.add(this.getText("br.service.openaccount.direct"));
		breadcrumbs.add("/mo-tai-khoan-nha-dau-tu.shtml");
		model.setBreadcrumbs(breadcrumbs);

		// copy data from input screen to existing data
		OnlineAccount account = (OnlineAccount) model.getFromCache(DIRECT_ACCOUNT);
		merge(account, model.getAccount());

		if (model.getAccount() != null) {
			// process for next button
			logger.debug("step 2 => Account = " + model.getAccount());

			model.addToCache(DIRECT_ACCOUNT, model.getAccount());
		} else {
			// process for back button (step 3 to 2)
			model.setAccount((OnlineAccount) model.getFromCache(DIRECT_ACCOUNT));
			logger.debug("step 2 => Back => Account = " + model.getAccount());
		}

		// return to step 1
		if (model.getAccount() == null) {
			return INPUT;
		}

		// set default selected
		if (StringUtils.isEmpty(model.getAccount().getTradingPhone())) {
			model.getAccount().setTradingPhone("any");
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

	/**
	 * Display direct account input screen in step 3
	 * 
	 * @return SUCCESS
	 */
	@Deprecated
	public String stepThree() throws Exception {
		final String LOCATION = "stepThree()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		// do SEO on-page
		model.setPageTitle(this.getText("home.account.open.directAccount.step3.title"));
		model.setPageDescription(this.getText("home.account.open.directAccount.step3.desc"));
		model.setPageKeywords(this.getText("home.account.open.directAccount.step3.keywords"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.service.openaccount.direct3"));
		breadcrumbs.add(this.getText("br.service"));
		breadcrumbs.add("spdvRoot");
		breadcrumbs.add(this.getText("br.service.openaccount"));
		breadcrumbs.add("/mo-tai-khoan.shtml");
		breadcrumbs.add(this.getText("br.service.openaccount.direct"));
		breadcrumbs.add("/mo-tai-khoan-nha-dau-tu.shtml");
		model.setBreadcrumbs(breadcrumbs);

		if (model.getAccount() != null) {
			// process for next button
			OnlineAccount account = (OnlineAccount) model.getFromCache(DIRECT_ACCOUNT);
			logger.debug("step 3 => Account = " + account);
			if (account == null) {
				return INPUT;
			}

			// copy data from input screen to existing data
			merge(account, model.getAccount());

			model.addToCache(DIRECT_ACCOUNT, model.getAccount());
			logger.debug("Step 3 => account in cache = " + model.getAccount());

		} else {
			// process for back button (step 4 to 3)
			model.setAccount((OnlineAccount) model.getFromCache(DIRECT_ACCOUNT));
		}

		// return to step 1
		if (model.getAccount() == null) {
			return INPUT;
		}

		// set default selected
		if (StringUtils.isEmpty(model.getAccount().getOtherAccount())) {
			model.getAccount().setOtherAccount("0");
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

	/**
	 * Display direct account input screen in step 4
	 * 
	 * @return SUCCESS
	 */
	@Deprecated
	public String stepFour() throws Exception {
		final String LOCATION = "stepFour()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		// do SEO on-page
		model.setPageTitle(this.getText("home.account.open.directAccount.step4.title"));
		model.setPageDescription(this.getText("home.account.open.directAccount.step4.desc"));
		model.setPageKeywords(this.getText("home.account.open.directAccount.step4.keywords"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.service.openaccount.direct4"));
		breadcrumbs.add(this.getText("br.service"));
		breadcrumbs.add("spdvRoot");
		breadcrumbs.add(this.getText("br.service.openaccount"));
		breadcrumbs.add("/mo-tai-khoan.shtml");
		breadcrumbs.add(this.getText("br.service.openaccount.direct"));
		breadcrumbs.add("/mo-tai-khoan-nha-dau-tu.shtml");
		model.setBreadcrumbs(breadcrumbs);

		if (model.getAccount() != null) {
			// process for next button
			OnlineAccount account = (OnlineAccount) model.getFromCache(DIRECT_ACCOUNT);
			if (account == null) {
				return INPUT;
			}

			// copy data from input screen to existing data
			merge(account, model.getAccount());

			model.addToCache(DIRECT_ACCOUNT, model.getAccount());
			logger.debug("Step 4 => account = " + model.getAccount());
		} else {
			// process for back button (step finish to 4)
			model.setAccount((OnlineAccount) model.getFromCache(DIRECT_ACCOUNT));
		}

		// return to step 1
		if (model.getAccount() == null) {
			return INPUT;
		}

		List<OnlineAccountAttribute> params = getParams(model.getAccount());
		Map<String, String> obj = new HashMap<String, String>();
		for (OnlineAccountAttribute param : params) {
			obj.put(param.getKey(), param.getValue());
		}

		ActionContext.getContext().getSession().put("ACCOUNT_DATA", obj);
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

	/**
	 * Process to finish
	 * 
	 * @return SUCCESS
	 */
	@Deprecated
	public String finish() {
		final String LOCATION = "finish()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		// do SEO on-page
		model.setPageTitle(this.getText("home.account.open.directAccount.finish.title"));
		model.setPageDescription(this.getText("home.account.open.directAccount.finish.desc"));
		model.setPageKeywords(this.getText("home.account.open.directAccount.finish.keywords"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.service.openaccount.direct5"));
		breadcrumbs.add(this.getText("br.service"));
		breadcrumbs.add("spdvRoot");
		breadcrumbs.add(this.getText("br.service.openaccount"));
		breadcrumbs.add("/mo-tai-khoan.shtml");
		breadcrumbs.add(this.getText("br.service.openaccount.direct"));
		breadcrumbs.add("/mo-tai-khoan-nha-dau-tu.shtml");
		model.setBreadcrumbs(breadcrumbs);

		OnlineAccount account = (OnlineAccount) model.getFromCache(DIRECT_ACCOUNT);
		if (account == null) {
			// unable to get data from cache
			return "startOver";
		}
		// call web service to store account
		try {
			// manager.createOnlineAccount(getParams(account));

			ProxyCreateLeadContractOutput result = manager.createOnlineAccount(getParams(account));
			if (CRMConstants.RESULT_STATUS.FAILURE.equalsIgnoreCase(result.getStatus())) {
				addActionError(getText("web.error.account.exception"));
				return INPUT;
			}

		} catch (FunctionalException fe) {
			logger.error(LOCATION, fe);
			// connect to web service fail
			addActionError(getText("web.error.account.exception"));
			return INPUT;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			// unknown exception
			addActionError(getText("web.error.account.exception"));
			return INPUT;
		}
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

	/**
	 * Process to finishAll
	 * 
	 * @return SUCCESS
	 */
	@Deprecated
	public String finishAll() {
		final String LOCATION = "finishAll()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		OnlineAccount account = model.getAccount();

		if (account == null) {
			return INPUT;
		}
		try {
			ProxyCreateLeadContractOutput result = manager.createOnlineAccount(getParams(account));
			if (CRMConstants.RESULT_STATUS.FAILURE.equalsIgnoreCase(result.getStatus())) {
				addActionError(getText("web.error.account.exception"));
				return INPUT;
			}

		} catch (FunctionalException fe) {
			logger.error(LOCATION, fe);
			// connect to web service fail
			addActionError(getText("web.error.account.exception"));
			return INPUT;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(LOCATION, e);
			// unknown exception
			addActionError(getText("web.error.account.exception"));
			return INPUT;
		}
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

	public String openFreeOnlineAccount() {
		// do SEO on-page
		model.setPageTitle(this.getText("home.account.open.directAccount.freeOnline.title"));
		model.setAccount(new OnlineAccount());
//		model.setBankListFromFile(banksServiceManager.getBankListFromFile());
		model.setBanksList(getBankList());
		model.setProvinceList(crmServiceManager.getProvinceList());
		Map<String, String> obj = new HashMap<String, String>();
		ActionContext.getContext().getSession().put("ACCOUNT_DATA", obj);
		return SUCCESS;
	}

	public String getBranches() {
//		model.setBranchListFromFile(banksServiceManager.getBranchListFromFile(model.getBankCode()));
		model.setBranchesList(getBranchList());
		return SUCCESS;
	}

	public String registerOnlineAccount() {
		final String location = "finishOpenAccount()";
		model.setPageTitle(this.getText("home.account.open.directAccount.freeOnline.title"));
//		model.setBankListFromFile(banksServiceManager.getBankListFromFile());
		model.setBanksList(getBankList());
		model.setProvinceList(crmServiceManager.getProvinceList());
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String challangeField = request.getParameter("recaptcha_challenge_field");
		String responseField = request.getParameter("recaptcha_response_field");
		ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(request.getRemoteAddr(), challangeField, responseField);
		if(!reCaptchaResponse.isValid()){
			addActionError(getText("web.error.account.invalidCaptcha"));
			return INPUT;
		}
		
		OnlineAccount account = model.getAccount();
		
		try {
			if (!isValidInput(account)) {
				return INPUT;
			}

			String leadId = crmServiceManager.createLead(account);

			if (StringUtils.isNotEmpty(leadId)) {
				account.setLeadId(leadId);
				manager.createFreeOnlineAccount(getParams(account));
				ActionContext.getContext().getSession().put("ACCOUNT_FREE_REGISTER_DATA", account);
				return SUCCESS;
			} else {
				addActionError(getText("web.error.account.exception"));
				emailServiceManager.sendEmailForDVKH(account.getOnlineUser(), SendEmailForSupporterType.CONVERT_LEAD_ERROR);
				return INPUT;
			}

		} catch (FunctionalException fe) {
			logger.error(location, fe);
			addActionError(getText("web.error.account.exception"));
			return INPUT;
		} catch (SystemException sysex) {
			logger.error(location, sysex);
			addActionError(getText("web.error.account.exception"));
			return INPUT;
		}
	}

	public String finishOpenAccount() {
		// do SEO on-page
		model.setPageTitle(this.getText("home.account.open.directAccount.finish.title"));
		model.setPageDescription(this.getText("home.account.open.directAccount.finish.desc"));
		model.setPageKeywords(this.getText("home.account.open.directAccount.finish.keywords"));
		OnlineAccount account = (OnlineAccount) ActionContext.getContext().getSession().get("ACCOUNT_FREE_REGISTER_DATA");
		model.setAccount(account);
		return SUCCESS;
	}

	public String activateFreeOnlineUser() {
		final String location = "activateFreeOnlineUserAction():";
		// do SEO on-page
		model.setPageTitle(this.getText("home.account.open.directAccount.activated.title"));
		String activeKey = model.getActiveKey();
		if (StringUtils.isEmpty(activeKey)) {
			model.setErrorMessage(getText("home.account.open.directAccount.activate.error"));
			return ERROR;
		}
		RegisterOnlineUserResult registerOnlineUserResult = null;
		OnlineUser onlineUser = new OnlineUser();
		try {
			registerOnlineUserResult = onlineUserServiceManager.getOnlineUserByActiveKey(activeKey);
			if (registerOnlineUserResult == null || registerOnlineUserResult.getRegistedOnlineUser() == null) {
				model.setErrorMessage(getText("home.account.open.directAccount.activate.error"));
				return ERROR;
			}
			onlineUser = registerOnlineUserResult.getRegistedOnlineUser();
			String activeStatus = onlineUser.getActiveStatus();
			// Chan khong cho khach hang kich xac nhan dang ky nhieu lan
			if (!pendingActiveStatus.equalsIgnoreCase(activeStatus)) {
				model.setErrorMessage(getText("home.account.open.directAccount.activate.error"));
				return ERROR;
			} else {
				//Update active status to processing
				onlineUserServiceManager.updateUserAccountState(activeKey, processingActiveStatus);
			}
			
			ConvertLeadToCustomerResult convertLeadToCustomerResult = crmServiceManager
					.convertLeadToCustomerAndSynToBo(onlineUser.getLeadId());
			// Loi Dong bo sang BO
			if (!CRMConstants.RESULT_STATUS.SUCCESS_CONVERT_LEAD.equalsIgnoreCase(convertLeadToCustomerResult.getStatus())) {
				model.setErrorMessage(getText("web.error.activated.account"));
				emailServiceManager.sendEmailForDVKH(onlineUser, SendEmailForSupporterType.SYNC_LEAD_TO_BO_ERROR);
				updateActiveErrorStatus(activeKey);
				return ERROR;
			}
			onlineUser.setCustodyCode(convertLeadToCustomerResult.getSyntobo().getCustodynumber());
			onlineUser.setCustomerId(convertLeadToCustomerResult.getConvertLead().getCustomerid());
			onlineUser.setContractNumber(convertLeadToCustomerResult.getSyntobo().getAccountnumber());
			boolean isUpgradeSuccess = upgradeFreeUserToTradingUserSyncToDB(onlineUser);
			if (!isUpgradeSuccess) {
				model.setErrorMessage(getText("web.error.activated.account"));
				updateActiveErrorStatus(activeKey);
				return ERROR;
			}
			
			onlineUserServiceManager.createIDGAccount(onlineUser);
			
			AssignCardResult assignCardResult = onlineUserServiceManager.assignedCardToAccount(onlineUser);
			if(assignCardResult != null && !VNDSMessageKeyUtils.Code.SYSTEM_OK_CODE.equalsIgnoreCase(assignCardResult.getMsgStatus().getCode())){
				if(StringUtils.isNotEmpty(assignCardResult.getOnlineUser().getSerialCard())) {
					onlineUser.setSerialCard(assignCardResult.getOnlineUser().getSerialCard());
				}
				logger.error(location+":"+assignCardResult.getMsgStatus().getMessage());
				emailServiceManager.sendEmailForDVKH(onlineUser, SendEmailForSupporterType.ASSIGN_VTOS_ERROR);
				model.setErrorMessage(getText("web.error.activated.account"));
				updateActiveErrorStatus(activeKey);
				return ERROR;
			}
			//Update active status to finish
			onlineUserServiceManager.updateUserAccountState(activeKey, finishActiveStatus);
		} catch (CreateIDGException e) {
			emailServiceManager.sendEmailForDVKH(onlineUser, SendEmailForSupporterType.CREATE_IDG_CARD_ERROR);
			model.setErrorMessage(getText("web.error.activated.account"));
			updateActiveErrorStatus(activeKey);
			return ERROR;
		} catch (AssignCardException e) {
			emailServiceManager.sendEmailForDVKH(onlineUser, SendEmailForSupporterType.ASSIGN_VTOS_ERROR);
			model.setErrorMessage(getText("web.error.activated.account"));
			updateActiveErrorStatus(activeKey);
			return ERROR;
		} catch (FunctionalException e) {
			logger.error(location, e);
			model.setErrorMessage(getText("web.error.activated.account"));
			updateActiveErrorStatus(activeKey);
			return ERROR;
		} catch (SystemException e) {
			logger.error(location, e);
			model.setErrorMessage(getText("web.error.activated.account"));
			updateActiveErrorStatus(activeKey);
			return ERROR;
		} catch (Exception e) {
			logger.error(location, e);
			model.setErrorMessage(getText("web.error.activated.account"));
			updateActiveErrorStatus(activeKey);
			return ERROR;
		}
		
		this.setUserInfoToContract(registerOnlineUserResult.getRegistedOnlineUser());
		return SUCCESS;
	}

	private void updateActiveErrorStatus(String activeKey){
		final String location = "updateActiveErrorStatus(activeKey: "+ activeKey + ")";
		//Update active status to finish
		try {
			onlineUserServiceManager.updateUserAccountState(activeKey, errorActiveStatus);
		} catch (FunctionalException e) {
			logger.error(location, e);
		} catch (SystemException e) {
			logger.error(location, e);
		}
	}
	public String checkIdentityNumber() {
		final String location = "checkIdentityNumber()";
		// Kiem tra trung CMND
		OnlineAccount account = model.getAccount();
		try {
			if (crmServiceManager.isDuplicateIdentification(account.getIdentityNo())) {
				model.setDuplicateIdentityNo(true);
			}
		} catch (FunctionalException e) {
			logger.error(location, e);
		} catch (SystemException e) {
			logger.error(location, e);
		}
		return SUCCESS;
	}

	public String denieActivatingOnlineUser() {
		final String location = "denieActivatingOnlineUser():";
		model.setPageTitle(this.getText("home.account.open.directAccount.denied.activated.title"));
//		model.setBankListFromFile(banksServiceManager.getBankListFromFile());
		model.setBanksList(getBankList());
		model.setProvinceList(crmServiceManager.getProvinceList());
		String activeKey = model.getActiveKey();
		if (StringUtils.isEmpty(activeKey)) {
			this.addActionError(getText("home.account.open.directAccount.activate.error"));
			return SUCCESS;
		}
		try {
			RegisterOnlineUserResult registerOnlineUserResult = manager.getUserByActiveKey(activeKey);
			// Chan khong cho khach hang kich huy dang ky nhieu lan
			if (registerOnlineUserResult == null
					|| (registerOnlineUserResult != null && registerOnlineUserResult.getRegistedOnlineUser() == null)) {
				this.addActionError(getText("home.account.open.directAccount.deniedactivate.error"));
				return SUCCESS;
			}
			String activeStatus = registerOnlineUserResult.getRegistedOnlineUser().getActiveStatus();
			String finishActiveStatus = Constants.IServerConfig.OpenOnlineUser.ActiveStatus.FINISH;
			if (finishActiveStatus.equalsIgnoreCase(activeStatus)) {
				this.addActionError(getText("home.account.open.directAccount.deniedactivate.error"));
				return SUCCESS;
			}

			DisableLeadCustomerResult disableLeadCustomerResult = manager.deniedActivatingOnlineUser(registerOnlineUserResult.getRegistedOnlineUser());
			if (!CRMConstants.RESULT_STATUS.SUCCESS_CONVERT_LEAD.equalsIgnoreCase(disableLeadCustomerResult
					.getDisableConvertLead().getStatus())) {
				this.addActionError(getText("web.error.denied.activated.account"));
				logger.error(location + " : " + disableLeadCustomerResult.getDisableConvertLead().getErrormessage());
				return SUCCESS;
			}

		} catch (FunctionalException e) {
			logger.error(location, e);
			this.addActionError(getText("web.error.denied.activated.account"));
		} catch (SystemException e) {
			logger.error(location, e);
			this.addActionError(getText("web.error.denied.activated.account"));
		}
		this.addActionMessage(getText("web.successful.denied.activated.account"));
		return SUCCESS;
	}

	public String checkEmailOrUsername() {
		final String location = "checkEmailOrUsername() : ";
		OnlineAccount account = model.getAccount();
		CheckExistedUserName checkExistedUserName = new CheckExistedUserName();
		checkExistedUserName.setUserName(account.getUsername());
		checkExistedUserName.setEmaill(account.getEmail());
		try {
			CheckExistedUserNameResult checkExistedUserNameResult = manager.checkExistedUsername(checkExistedUserName);
			if (checkExistedUserNameResult.getExistedEmail()) {
				model.setExistedEmail(true);
			} else {
				model.setExistedEmail(false);
			}

			if (checkExistedUserNameResult.getExistedUserName()) {
				model.setExistedUsername(true);
			} else {
				model.setExistedUsername(false);
			}
		} catch (FunctionalException e) {
			logger.error(location + e.getMessage());
		} catch (SystemException e) {
			logger.error(location + e.getMessage());
		}
		return SUCCESS;
	}

	public DirectAccountModel getModel() {
		return model;
	}

	/**
	 * Create an array attributes of an account to use in web service and export
	 * to pdf
	 * 
	 * @param account
	 * @return
	 */
	private List<OnlineAccountAttribute> getParams(OnlineAccount account) {
		final String LOCATION = "getParams()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		List<OnlineAccountAttribute> params = new ArrayList<OnlineAccountAttribute>();
		OnlineAccountAttribute param;
		param = new OnlineAccountAttribute();
		// Dummy username, password
		param.setKey(DBConstants.ONLINE_ACCOUNT.USERNAME);
		param.setValue(account.getUsername());
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setKey(DBConstants.ONLINE_ACCOUNT.LEAD_ID);
		param.setValue(account.getLeadId());
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setKey(DBConstants.ONLINE_ACCOUNT.PASSWORD);
		param.setValue(account.getPassword());
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.ONLINE_ACCOUNT_ID);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getAccountType1());
		param.setKey(DBConstants.ONLINE_ACCOUNT.ACCOUNT_TYPE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("0");
		param.setKey(DBConstants.ONLINE_ACCOUNT.IS_VN_INVESTER);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.HEARD_OF_VNDIRECT);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.REF_CODE);
		params.add(param);

		param = new OnlineAccountAttribute();
		String fullName = StringUtils.EMPTY;
		if (StringUtils.isNotEmpty(account.getMiddleName())) {
			fullName = account.getLastName() + " " + account.getMiddleName() + " " + account.getName();
		} else {
			fullName = account.getLastName() + " " + account.getName();
		}
		param.setValue(fullName);
		param.setKey(DBConstants.ONLINE_ACCOUNT.FULLNAME);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getBirthday());
		param.setKey(DBConstants.ONLINE_ACCOUNT.DATE_OF_BIRTH);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getSex());
		param.setKey(DBConstants.ONLINE_ACCOUNT.SEX);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getIdentityType());
		param.setKey(DBConstants.ONLINE_ACCOUNT.ID_TYPE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getIdentityNo());
		param.setKey(DBConstants.ONLINE_ACCOUNT.ID_NUMBER);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getIdentityDate());
		param.setKey(DBConstants.ONLINE_ACCOUNT.ID_ISSUE_DATE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.ID_EXPIRE_DATE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getIdentityPlace());
		param.setKey(DBConstants.ONLINE_ACCOUNT.ID_ISSUE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getMarried());
		param.setKey(DBConstants.ONLINE_ACCOUNT.MARITAL_STATUS);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getSpouseName());
		param.setKey(DBConstants.ONLINE_ACCOUNT.SPOUSE_NAME);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getSpouseCompany());
		param.setKey(DBConstants.ONLINE_ACCOUNT.SPOUSE_COMPANY);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getSpousePosition());
		param.setKey(DBConstants.ONLINE_ACCOUNT.SPOUSE_POSITION);
		params.add(param);

		// NOTE required field
		param = new OnlineAccountAttribute();
		param.setValue(account.getAddress());
		param.setKey(DBConstants.ONLINE_ACCOUNT.STREET_ADDRESS);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.PROVINCE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.COUNTRY);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getEmail());
		param.setKey(DBConstants.ONLINE_ACCOUNT.EMAIL);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getPhoneNo());
		param.setKey(DBConstants.ONLINE_ACCOUNT.HOME_PHONE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getMobilePhoneNo());
		param.setKey(DBConstants.ONLINE_ACCOUNT.MOBILE_PHONE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(StringUtils.join(account.getMobilePassword(), ""));
		param.setKey(DBConstants.ONLINE_ACCOUNT.MOBILEPASSWORD);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.EDUCATION_LVL);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.COMPANY);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.POSITION);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.COMPANY_PHONE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.COMPANY_FAX);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getBankAccountNo());
		param.setKey(DBConstants.ONLINE_ACCOUNT.BANK_NUMBER);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getBankName());
		param.setKey(DBConstants.ONLINE_ACCOUNT.BANK_NAME);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getBankBranchName());
		param.setKey(DBConstants.ONLINE_ACCOUNT.BANK_LOCATION);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getBankCode());
		param.setKey(DBConstants.ONLINE_ACCOUNT.BANK_CODE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getBankAccountName());
		param.setKey(DBConstants.ONLINE_ACCOUNT.BANK_MANAGER);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getBankBranchCode());
		param.setKey(DBConstants.ONLINE_ACCOUNT.BANK_BRANCH_CODE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getBankBranchName());
		param.setKey(DBConstants.ONLINE_ACCOUNT.BANK_BRANCH_NAME);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getMangName());
		param.setKey(DBConstants.ONLINE_ACCOUNT.PUBLIC_MANAGE_COMPANY);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(account.getCapitalName());
		param.setKey(DBConstants.ONLINE_ACCOUNT.PUBLIC_COMPANY_HOLDING);
		params.add(param);

		param = new OnlineAccountAttribute();
		List<String> tradingAccount = new ArrayList<String>();
		// default: chon ca 2 loai tai khoan
		tradingAccount.add("1");// 1: TK giao dich niem yet
		tradingAccount.add("2");// 2: TK giao dich OTC
		param.setValue(StringUtils.join(tradingAccount, ""));
		param.setKey(DBConstants.ONLINE_ACCOUNT.TRANSACTION_TYPE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.ISDELEGATE_ACCOUNT);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_NAME);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_PHONE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(StringUtils.join(account.getCycleToReceive(), ""));
		param.setKey(DBConstants.ONLINE_ACCOUNT.STATEMENT_CYCLE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue(StringUtils.join(account.getReceiveMethods(), ""));
		param.setKey(DBConstants.ONLINE_ACCOUNT.STATEMENT_METHOD);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.ORDER_RESULT_METHOD);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.ORDER_RESULT_MOBILE_PHONE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.NOTIFICATION_METHOD);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.NOTIFICATION_MOBILE_PHONE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.IS_DELEGATE_ORDER);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_FULLNAME);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_DATE_OF_BIRTH);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_SEX);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_ID_TYPE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_ID_NUMBER);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_ID_ISSUE_DATE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_ID_EXPIRE_DATE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_ID_ISSUE);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_MARITAL_STATUS);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_SPOUSE_NAME);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_SPOUSE_COMPANY);
		params.add(param);

		param = new OnlineAccountAttribute();
		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_SPOUSE_POSITION);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_STREET_ADDRESS);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_PROVINCE);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_COUNTRY);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_EMAIL);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_HOME_PHONE);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_MOBILE_PHONE);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_COMPANY);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_POSITION);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_COMPANY_PHONE);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.DELEGATE_COMPANY_FAX);
		params.add(param);

		param = new OnlineAccountAttribute();

		// param.setValue(account.getTradingMethods().contains("Online") ? "1" :
		// "0");
		// Default chon phuong thuc giao dich truc tuyen
		param.setValue("1");
		param.setKey(DBConstants.ONLINE_ACCOUNT.IS_REMOTE_TRADING);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.REMOTE_TRADING_IS_OWNER);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.REMOTE_TRADING_IS_DELEGATE);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.REMOTE_TRADING_EMAIL);
		params.add(param);

		param = new OnlineAccountAttribute();

		// param.setValue(account.getTradingMethods().contains("Phone") ? "1" :
		// "0");
		// Default chon phuong thuc giao dich qua dien thoai
		param.setValue("1");
		param.setKey(DBConstants.ONLINE_ACCOUNT.IS_TEL_TRADING);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.TEL_TRADING_IS_OWNER_ACCOUNT);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.TEL_TRADING_IS_DELEGATE);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue(account.getMobilePhoneNo());
		param.setKey(DBConstants.ONLINE_ACCOUNT.TRADING_PHONE_NUMBER);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.TRADING_PASS_CONFIRM);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.INVESTMENT_OBJECT_INCOME);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.INVESTMENT_OBJECT_LONG_TERM);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.INVESTMENT_OBJECT_NOMAL_TERM);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.INVESTMENT_OBJECT_SHORT_TERM);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.INVESTMENT_RISK_HIGH);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.INVESTMENT_RISK_NOMAL);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.INVESTMENT_RISK_LOW);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.ASSET_SHORT_TERM);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.ASSET_FIXED);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.ASSET_CURRENT);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.ASSET_MONTHLY);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue(StringUtils.join(account.getExprs(), ""));
		param.setKey(DBConstants.ONLINE_ACCOUNT.INVESTMENT_KNOWLEDGE);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue(StringUtils.join(account.getInvs(), ""));
		param.setKey(DBConstants.ONLINE_ACCOUNT.INVESTMENT_EXPERIENCE);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.ACCOUNT_STATUS);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.CREATED_DATE);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("");
		param.setKey(DBConstants.ONLINE_ACCOUNT.REPORT_KEY);

		params.add(param);

		// ----------------------------------------------------------------------------------//

		param = new OnlineAccountAttribute();

		param.setValue(account.getFax());
		param.setKey(DBConstants.ONLINE_ACCOUNT.FAX);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue(account.getTaxCode());
		param.setKey(DBConstants.ONLINE_ACCOUNT.TAX_NUMBER);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("VNM");// Default Viet Nam
		param.setKey(DBConstants.ONLINE_ACCOUNT.NATIONALITY);
		params.add(param);

		param = new OnlineAccountAttribute();

		// param.setValue(account.getTradingMethods().contains("Property") ? "1"
		// : "0");
		// Default chon phuong thuc giao dich tai san
		param.setValue("1");
		param.setKey(DBConstants.ONLINE_ACCOUNT.IS_FLOOR_TRADING);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue(StringUtils.join(account.getAccountNo2(), ""));
		param.setKey(DBConstants.ONLINE_ACCOUNT.BANK_NUMBER2);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue(account.getBankName2());
		param.setKey(DBConstants.ONLINE_ACCOUNT.BANK_NAME2);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue(account.getAccountName2());
		param.setKey(DBConstants.ONLINE_ACCOUNT.BANK_MANAGER2);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue(StringUtils.join(account.getVndirectAccNo(), ""));
		param.setKey(DBConstants.ONLINE_ACCOUNT.CORRESPONDENCE_ACCOUNT_NUMBER);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue(account.getVndirectAccName());
		param.setKey(DBConstants.ONLINE_ACCOUNT.CORRESPONDENCE_ACCOUNT_NAME);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue(account.getOtherAccount());
		param.setKey(DBConstants.ONLINE_ACCOUNT.IS_OTHER_COMPANY_ACCOUNT);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue(account.getOtherAccName());
		param.setKey(DBConstants.ONLINE_ACCOUNT.OTHER_COMPANY_ACCOUNT_NAME);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue(account.getYear());
		param.setKey(DBConstants.ONLINE_ACCOUNT.ENTER_MARKET_YEAR);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue(StringUtils.join(account.getInvCapAvail(), ""));
		param.setKey(DBConstants.ONLINE_ACCOUNT.INVESTMENT_CAPITAL);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue(StringUtils.join(account.getInvCriteria(), ""));
		param.setKey(DBConstants.ONLINE_ACCOUNT.INVESTMENT_AIM);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue("0");
		param.setKey(DBConstants.ONLINE_ACCOUNT.IS_SELECT_STAFF);
		params.add(param);

		param = new OnlineAccountAttribute();

		param.setValue(account.getAccountType());// add new field
		param.setKey(DBConstants.ONLINE_ACCOUNT.SERVICE_PACK);
		params.add(param);

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return params;
	}

	/**
	 * Merge the properties which are not null of two object of the same class
	 * 
	 * @param <T>
	 * @param o1
	 *            object 1
	 * @param o2
	 *            object 2
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static <T> void merge(T o1, T o2) throws IllegalArgumentException, IllegalAccessException {
		if (o1 == null || o2 == null)
			return;

		// get field
		Field[] f1 = o1.getClass().getDeclaredFields();
		Field[] f2 = o2.getClass().getDeclaredFields();

		for (int i = 0; i < f1.length; i++) {
			f1[i].setAccessible(true);
			f2[i].setAccessible(true);

			// copy property
			if (f1[i].get(o1) == null && f2[i].get(o2) != null) {
				f1[i].set(o1, f2[i].get(o2));
			}

			if (f1[i].get(o1) != null && f2[i].get(o2) == null) {
				f2[i].set(o2, f1[i].get(o1));
			}
		}
	}

	/**
	 * Get all years from 2000 to present
	 * 
	 * @return array of years
	 */
	public String[] getYears() {
		Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		if (curYear - 2000 > 0) {
			String[] years = new String[curYear - 2000 + 1];

			for (int i = 0; i < years.length; i++) {
				years[i] = 2000 + i + "";
			}
			return years;
		} else {
			return new String[] {};
		}
	}

	private void doSEOpage() {
		if (StringUtils.isNotEmpty(model.getPageTitle())) {
			model.setPageTitle(this.getText(model.getPageTitle()));
		}
		if (StringUtils.isNotEmpty(model.getPageDescription())) {
			model.setPageDescription(this.getText(model.getPageDescription()));
		}
		if (StringUtils.isNotEmpty(model.getPageKeywords())) {
			model.setPageKeywords(this.getText(model.getPageKeywords()));
		}
	}

	private void setUserInfoToContract(OnlineUser onlineUser) {
		// View contract info
		Map<String, String> obj = new HashMap<String, String>();
		if (onlineUser != null) {
			OnlineAccount onlineAccount = this.convertToOnlineAccount(onlineUser);
			List<OnlineAccountAttribute> params = getParams(onlineAccount);
			for (OnlineAccountAttribute param : params) {
				obj.put(param.getKey(), param.getValue());
			}
		}
		ActionContext.getContext().getSession().put("ACCOUNT_DATA", obj);
	}

	private OnlineAccount convertToOnlineAccount(OnlineUser onlineUser) {
		OnlineAccount onlineAccount = new OnlineAccount();
		onlineAccount.setLastName(onlineUser.getFullName());
		onlineAccount.setName("");
		onlineAccount.setAddress(onlineUser.getAddress());
		try {
			onlineAccount.setBirthday(VNDirectDateUtils.dateToString(onlineUser.getDob(), "dd/MM/yyyy"));
			onlineAccount.setIdentityDate(VNDirectDateUtils.dateToString(onlineUser.getIdDate(), "dd/MM/yyyy"));
		} catch (SystemException e) {
		}
		onlineAccount.setIdentityNo(onlineUser.getIdNumber());
		onlineAccount.setMobilePhoneNo(onlineUser.getTelephone());
		onlineAccount.setEmail(onlineUser.getEmail());
		onlineAccount.setIdentityPlace(onlineUser.getIdPlace());
		return onlineAccount;
	}

	private boolean upgradeFreeUserToTradingUserSyncToDB(OnlineUser onlineUser) {
		boolean result = false;
		try {
			result = onlineUserServiceManager.updateFreeToTradingAccountSyncToDB(onlineUser);
		} catch (Exception e) {
			logger.error("updateFreeToTradingAccountSyncToDB fail", e);
		}

		return result;
	}

	private boolean isValidInput(OnlineAccount account) throws FunctionalException, SystemException {
		boolean isValid = true;
		// Kiem tra trung CMND
		if (crmServiceManager.isDuplicateIdentification(account.getIdentityNo())) {
			this.addActionError(getText("web.error.openaccount.invalid.identification"));
			isValid = false;
		}

		// Check password
		if (account.getPassword() != null && !account.getPassword().equals(account.getRetypePassword())) {
			this.addActionError(getText("web.error.openaccount.password.notmatched"));
			isValid = false;
		}
		
		// Kiem tra trung username hoac email
		CheckExistedUserName checkExistedUserName = new CheckExistedUserName();
		checkExistedUserName.setUserName(account.getUsername());
		checkExistedUserName.setEmaill(account.getEmail());
		CheckExistedUserNameResult checkExistedUserNameResult = manager.checkExistedUsername(checkExistedUserName);
		if (checkExistedUserNameResult.getExistedUserName()) {
			this.addActionError(getText("ErrorMessage.VNDSService.OnlineUser.ERR_EMAIL_USED_USERNAME"));
			isValid = false;
		}

		return isValid;
	}

	public List<Bank> getBankList(){
		final String location = "getBankList(): ";
		List<Bank> bankList = new ArrayList<Bank>();
		try {
			bankList = banksServiceManager.getBanksList();
		} catch (Exception e) {
			logger.error(location + e.getMessage());
			model.setErrorMessage(e.getMessage());
		}		
		return bankList;	
	}
	
	public List<Branch> getBranchList(){
		final String location = "getBankList(): ";
		List<Branch> branchList = new ArrayList<Branch>();
		try {
			branchList = banksServiceManager.getBranchList(model.getBankCode());
		} catch (Exception e) {
			logger.error(location + e.getMessage());
			model.setErrorMessage(e.getMessage());
		}		
		return branchList;	
	}
}
