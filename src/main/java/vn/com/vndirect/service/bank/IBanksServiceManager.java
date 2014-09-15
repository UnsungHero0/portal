package vn.com.vndirect.service.bank;

import java.util.List;

import vn.com.vndirect.domain.struts2.common.BankBranch;
import vn.com.vndirect.domain.struts2.common.Banks;

import com.homedirect.proxy.PaydirectWebservice.Bank;
import com.homedirect.proxy.PaydirectWebservice.Branch;

public interface IBanksServiceManager {
	
	List<Bank> getBanksList() throws Exception;

	List<Branch> getBranchList(String bankCode) throws Exception;

	List<Banks> getBankListFromFile();
	List<BankBranch> getBranchListFromFile(String bankCode);
}