/**
 * 
 */
package vn.com.vndirect.domain.struts2.analysistools;

import java.util.List;

import vn.com.vndirect.crmclient.leadContract.VndsProvince;
import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.OnlineAccount;
import vn.com.vndirect.domain.struts2.common.BankBranch;
import vn.com.vndirect.domain.struts2.common.Banks;

import com.homedirect.proxy.PaydirectWebservice.Bank;
import com.homedirect.proxy.PaydirectWebservice.Branch;

/**
 * @author Huy
 * 
 */
public class DirectAccountModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 904449715686048967L;

	private OnlineAccount account;

	private List<Bank> banksList;
	
	private List<Branch> branchesList;
	
	private List<Banks> bankListFromFile;

	private List<BankBranch> branchListFromFile;
	
	private List<VndsProvince> provinceList;
	
	private String bankCode;
	
	private String activeKey;
	
	private boolean isDuplicateIdentityNo;
	
	private boolean existedEmail;

	private boolean existedUsername;
	
	private String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isDuplicateIdentityNo() {
		return isDuplicateIdentityNo;
	}

	public void setDuplicateIdentityNo(boolean isDuplicateIdentityNo) {
		this.isDuplicateIdentityNo = isDuplicateIdentityNo;
	}

	/**
	 * @return the account
	 */
	public OnlineAccount getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(OnlineAccount account) {
		this.account = account;
	}

	public List<Bank> getBanksList() {
		return banksList;
	}

	public void setBanksList(List<Bank> banksList) {
		this.banksList = banksList;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public List<Branch> getBranchesList() {
		return branchesList;
	}

	public void setBranchesList(List<Branch> branchesList) {
		this.branchesList = branchesList;
	}

	public String getActiveKey() {
		return activeKey;
	}

	public void setActiveKey(String activeKey) {
		this.activeKey = activeKey;
	}

	public boolean isExistedEmail() {
		return existedEmail;
	}

	public void setExistedEmail(boolean existedEmail) {
		this.existedEmail = existedEmail;
	}

	public boolean isExistedUsername() {
		return existedUsername;
	}

	public void setExistedUsername(boolean existedUsername) {
		this.existedUsername = existedUsername;
	}

	public List<VndsProvince> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<VndsProvince> provinceList) {
		this.provinceList = provinceList;
	}

	public List<Banks> getBankListFromFile() {
		return bankListFromFile;
	}

	public void setBankListFromFile(List<Banks> bankListFromFile) {
		this.bankListFromFile = bankListFromFile;
	}

	public List<BankBranch> getBranchListFromFile() {
		return branchListFromFile;
	}

	public void setBranchListFromFile(List<BankBranch> branchListFromFile) {
		this.branchListFromFile = branchListFromFile;
	}

}
