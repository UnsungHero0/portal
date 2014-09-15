package vn.com.vndirect.basicanalysis;

import java.io.Serializable;
import java.util.List;

import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;

public class PasicAnalysis implements Serializable {

	private CurrentCompanyForQuote companyQuote;
	private List<String> epsIncreasePercentList;
	private List<String> earningList;
	private List<String> earningHeadList;
	private List<String> roeList;
	private List<String> incomeList;

	public CurrentCompanyForQuote getCompanyQuote() {
		return companyQuote;
	}

	public void setCompanyQuote(CurrentCompanyForQuote companyQuote) {
		this.companyQuote = companyQuote;
	}

	public List<String> getEpsIncreasePercentList() {
		return epsIncreasePercentList;
	}

	public List<String> getEarningList() {
		return earningList;
	}

	public void setEpsIncreasePercentList(List<String> epsIncreasePercentList) {
		this.epsIncreasePercentList = epsIncreasePercentList;
	}

	public void setEarningList(List<String> earningList) {
		this.earningList = earningList;
	}

	public List<String> getEarningHeadList() {
		return earningHeadList;
	}

	public void setEarningHeadList(List<String> earningHeadList) {
		this.earningHeadList = earningHeadList;
	}

	public List<String> getRoeList() {
		return roeList;
	}

	public void setRoeList(List<String> roeList) {
		this.roeList = roeList;
	}

	public List<String> getIncomeList() {
		return incomeList;
	}

	public void setIncomeList(List<String> incomeList) {
		this.incomeList = incomeList;
	}
}
