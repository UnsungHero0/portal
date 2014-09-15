package vn.com.vndirect.service.bank;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import vn.com.vndirect.business.BaseManager;
import vn.com.vndirect.commons.utility.BankCodeUtils;
import vn.com.vndirect.domain.struts2.common.BankBranch;
import vn.com.vndirect.domain.struts2.common.Banks;

import com.homedirect.proxy.PaydirectWebservice.Bank;
import com.homedirect.proxy.PaydirectWebservice.Branch;

import edu.emory.mathcs.backport.java.util.Arrays;

@Component
public class BanksServiceManager extends BaseManager implements IBanksServiceManager {
	private static Logger logger = Logger.getLogger(BanksServiceManager.class);

	@Override
	public List<Bank> getBanksList() throws Exception {
		final String location = "getBanksList(): ";
		Bank[] bankList = null;
		initPolicy();
		bankList = getPayDirectBankingService().getBankList(getHomedirectAuthHeader());
		if (bankList == null) {
			return new ArrayList<Bank>();
		}
		return Arrays.asList(bankList);
	}

	@Override
	public List<Branch> getBranchList(String bankCode) throws Exception {
		final String location = "getBranchList(): ";
		Branch[] branchList = null;
		initPolicy();
		branchList = getPayDirectBankingService().getBranchList(getHomedirectAuthHeader(), bankCode);
		if (branchList == null) {
			return new ArrayList<Branch>();
		}
		return Arrays.asList(branchList);
	}

	@Override
	public List<Banks> getBankListFromFile() {
		return BankCodeUtils.listBanks;
	}

	@Override
	public List<BankBranch> getBranchListFromFile(String bankCode) {
		return BankCodeUtils.listBranches.get(bankCode);
	}

}
