package vn.com.vndirect.domain.struts2.listedmarket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.extend.FinanceReportForQuote;
import vn.com.vndirect.domain.extend.IfoBalanceSheetSearch;

@SuppressWarnings( { "unchecked", "serial" })
public class BalanceSheetModel extends BaseModel {
	/**
	 * 
	 */

	private IfoBalanceSheetSearch searchObject = new IfoBalanceSheetSearch();
	private List quarterList = new ArrayList();
	private List<String> yearList = new ArrayList<String>(); // Arrays.asList("2009", "2008", "2007", "2006");
	private List<Integer> termList = Arrays.asList(1, 2, 3, 4, 5);
	private Collection moneyRateCol;
	private FinanceReportForQuote financeInfoFirst = new FinanceReportForQuote();
	private List<FinanceReportForQuote> financeInfoList = new ArrayList<FinanceReportForQuote>();
	private String symbol;

	public List getQuarterList() {
		return quarterList;
	}

	public void setQuarterList(List quarterList) {
		this.quarterList = quarterList;
	}

	public List<String> getYearList() {
		return yearList;
	}

	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
	}

	public List<Integer> getTermList() {
		return termList;
	}

	public void setTermList(List<Integer> termList) {
		this.termList = termList;
	}

	public Collection getMoneyRateCol() {
		return moneyRateCol;
	}

	public void setMoneyRateCol(Collection moneyRateCol) {
		this.moneyRateCol = moneyRateCol;
	}

	public IfoBalanceSheetSearch getSearchObject() {
		return searchObject;
	}

	public void setSearchObject(IfoBalanceSheetSearch searchObject) {
		this.searchObject = searchObject;
	}

	public FinanceReportForQuote getFinanceInfoFirst() {
		return financeInfoFirst;
	}

	public void setFinanceInfoFirst(FinanceReportForQuote financeInfoFirst) {
		this.financeInfoFirst = financeInfoFirst;
	}

	public List<FinanceReportForQuote> getFinanceInfoList() {
		return financeInfoList;
	}

	public void setFinanceInfoList(List<FinanceReportForQuote> financeInfoList) {
		this.financeInfoList = financeInfoList;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
