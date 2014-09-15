package vn.com.vndirect.domain.struts2.common;

public class BankBranch {
	public BankBranch(String branchCode, String branchName) {
		this.branchCode = branchCode;
		this.branchName = branchName;
	}

	private String branchCode;
	private String branchName;

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
}
