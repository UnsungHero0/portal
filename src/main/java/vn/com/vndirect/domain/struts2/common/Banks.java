package vn.com.vndirect.domain.struts2.common;

public class Banks {

	private String bankCode;
	private String bankShortName;
	private String bankFullName;

	public Banks(String bankCode, String bankShortName, String bankFullName) {
		this.bankCode = bankCode;
		this.bankShortName = bankShortName;
		this.bankFullName = bankFullName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankShortName() {
		return bankShortName;
	}

	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}

	public String getBankFullName() {
		return bankFullName;
	}

	public void setBankFullName(String bankFullName) {
		this.bankFullName = bankFullName;
	}
}
