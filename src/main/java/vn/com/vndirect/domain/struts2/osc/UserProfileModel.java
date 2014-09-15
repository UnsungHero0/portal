package vn.com.vndirect.domain.struts2.osc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import vn.com.vndirect.boproxyclient.onlinetrading.Account;
import vn.com.vndirect.boproxyclient.onlinetrading.UserProfile;
import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.OnlineUser;
import vn.com.vndirect.domain.RecoverPassword;

@SuppressWarnings( { "unchecked", "serial" })
public class UserProfileModel extends BaseModel {
	private UserProfile userProfile = new UserProfile();
	private List<Account> listAccounts = new ArrayList<Account>();
	private String tradingPassword;
	private String selectedAccountNumber;
	private OnlineUser onlineUser = new OnlineUser();
	private String telePasswordNew;
	private String newEmail;
	private String userName;
	private String caller;
	private String userAction;
	private String successKey;
	private String newPassword; // using for case change password login

	// Use for Recover User Info
	private RecoverPassword recoverPassword = new RecoverPassword();
	private Collection identificationTypeList = new ArrayList();
	private String accountType;
	private String messageFullName;
	private String messageEmail;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getSuccessKey() {
		return successKey;
	}

	public void setSuccessKey(String successKey) {
		this.successKey = successKey;
	}

	public String getUserAction() {
		return userAction;
	}

	public void setUserAction(String userAction) {
		this.userAction = userAction;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public String getTelePasswordNew() {
		return telePasswordNew;
	}

	public void setTelePasswordNew(String telePasswordNew) {
		this.telePasswordNew = telePasswordNew;
	}

	public String getSelectedAccountNumber() {
		return selectedAccountNumber;
	}

	public void setSelectedAccountNumber(String selectedAccountNumber) {
		this.selectedAccountNumber = selectedAccountNumber;
	}

	public List<Account> getListAccounts() {
		return listAccounts;
	}

	public void setListAccounts(List<Account> listAccounts) {
		this.listAccounts = listAccounts;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public String getTradingPassword() {
		return tradingPassword;
	}

	public void setTradingPassword(String tradingPassword) {
		this.tradingPassword = tradingPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public OnlineUser getOnlineUser() {
		return onlineUser;
	}

	public void setOnlineUser(OnlineUser onlineUser) {
		this.onlineUser = onlineUser;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	// Getters and Setters for Recover User Info
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Collection getIdentificationTypeList() {
		return identificationTypeList;
	}

	public void setIdentificationTypeList(Collection identificationTypeList) {
		this.identificationTypeList = identificationTypeList;
	}

	public RecoverPassword getRecoverPassword() {
		return recoverPassword;
	}

	public void setRecoverPassword(RecoverPassword recoverPassword) {
		this.recoverPassword = recoverPassword;
	}

	public String getMessageFullName() {
		return messageFullName;
	}

	public void setMessageFullName(String messageFullName) {
		this.messageFullName = messageFullName;
	}

	public String getMessageEmail() {
		return messageEmail;
	}

	public void setMessageEmail(String messageEmail) {
		this.messageEmail = messageEmail;
	}
}
