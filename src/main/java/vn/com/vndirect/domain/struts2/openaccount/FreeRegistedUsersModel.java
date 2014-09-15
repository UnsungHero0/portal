package vn.com.vndirect.domain.struts2.openaccount;

import java.util.ArrayList;
import java.util.Collection;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.OnlineUser;

@SuppressWarnings("unchecked")
public class FreeRegistedUsersModel extends BaseModel {

	private static final long serialVersionUID = 2281755392781882891L;

	private OnlineUser onlineUser = new OnlineUser();

	private String confirmPass;

	private String agreement;

	private String passWord;

	private String messageFullName;

	private String messageEmail;

	private Collection sexList = new ArrayList();

	private Collection countryList = new ArrayList();

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	public OnlineUser getOnlineUser() {
		return onlineUser;
	}

	public void setOnlineUser(OnlineUser onlineUser) {
		this.onlineUser = onlineUser;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Collection getCountryList() {
		return countryList;
	}

	public void setCountryList(Collection countryList) {
		this.countryList = countryList;
	}

	public Collection getSexList() {
		return sexList;
	}

	public void setSexList(Collection sexList) {
		this.sexList = sexList;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
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
