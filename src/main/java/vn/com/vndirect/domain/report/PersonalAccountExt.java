package vn.com.vndirect.domain.report;

import vn.com.vndirectreports.openaccount.PersonalAccount;

public class PersonalAccountExt extends PersonalAccount {
	private String fixPhone_2 = "";
	private String mobilePhone_2 = "";

	public String getFixPhone_2() {
		return fixPhone_2 == null ? "" : fixPhone_2;
	}

	public void setFixPhone_2(String fixPhone_2) {
		this.fixPhone_2 = fixPhone_2;
	}

	public String getMobilePhone_2() {
		return mobilePhone_2 == null ? "" : mobilePhone_2;
	}

	public void setMobilePhone_2(String mobilePhone_2) {
		this.mobilePhone_2 = mobilePhone_2;
	}

}
