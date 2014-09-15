package vn.com.vndirect.domain.struts2.osc;

import java.util.ArrayList;
import java.util.List;

import vn.com.vndirect.boproxyclient.onlinetrading.Account;
import vn.com.vndirect.boproxyclient.onlinetrading.UserProfile;
import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.OnlineUser;

/**
 * @author NguyenDucQuang
 * @version 1.0
 * @created Mar 5, 2010 4:10:45 PM
 */
@SuppressWarnings("serial")
public class UserProfileAJAXModel extends BaseModel {
	private UserProfile userProfile = new UserProfile();
	private List<Account> listAccounts = new ArrayList<Account>();
	private String selectedAccountNumber;
	private OnlineUser onlineUser = new OnlineUser();
	private String caller;
	private String userAction;
	private String tradingPassword;
	private String telePasswordOld;
	private String errorMessage;

	public String getTelePasswordOld() {
		return telePasswordOld;
	}

	public void setTelePasswordOld(String telePasswordOld) {
		this.telePasswordOld = telePasswordOld;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getTradingPassword() {
		return tradingPassword;
	}

	public void setTradingPassword(String tradingPassword) {
		this.tradingPassword = tradingPassword;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public String getUserAction() {
		return userAction;
	}

	public void setUserAction(String userAction) {
		this.userAction = userAction;
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

	public OnlineUser getOnlineUser() {
		return onlineUser;
	}

	public void setOnlineUser(OnlineUser onlineUser) {
		this.onlineUser = onlineUser;
	}

}
