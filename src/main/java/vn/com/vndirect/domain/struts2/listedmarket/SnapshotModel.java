package vn.com.vndirect.domain.struts2.listedmarket;

import java.util.ArrayList;
import java.util.List;

import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.vndirect.domain.IfoCompanyIndustryView;
import vn.com.vndirect.domain.IfoInsiderTransactionView;
import vn.com.vndirect.domain.extend.IfoCompanySnapshotViewExt;
import vn.com.vndirect.domain.extend.InfoCompanyExt;
import vn.com.web.commons.domain.db.SearchResult;

public class SnapshotModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7247812725279669386L;
	
	private String exchangeCode;

	private IfoCompanySnapshotViewExt ifoComSnapshotViewExt = new IfoCompanySnapshotViewExt();
	private List<IfoInsiderTransactionView> ifoInsiderTransactionViewList;

	private IfoCompanyCalcView ifoCompanyCalcView = new IfoCompanyCalcView();


	// Related Company
	private IfoCompanyIndustryView ifoCompanyIndustryView = new IfoCompanyIndustryView();
	private List<InfoCompanyExt> listQuote = new ArrayList<InfoCompanyExt>();
	@SuppressWarnings("unchecked")
	private SearchResult searchResult;

	public IfoCompanyCalcView getIfoCompanyCalcView() {
		return ifoCompanyCalcView;
	}

	public void setIfoCompanyCalcView(IfoCompanyCalcView ifoCompanyCalcView) {
		this.ifoCompanyCalcView = ifoCompanyCalcView;
	}

	public IfoCompanySnapshotViewExt getIfoComSnapshotViewExt() {
		return ifoComSnapshotViewExt;
	}

	public void setIfoComSnapshotViewExt(IfoCompanySnapshotViewExt ifoComSnapshotViewExt) {
		this.ifoComSnapshotViewExt = ifoComSnapshotViewExt;
	}

	public String getStrRoa() {
		String strRoa = "";
		if (ifoComSnapshotViewExt.getId() != null) {
			strRoa = VNDirectWebUtilities.getStrDoubleWithScale2(ifoComSnapshotViewExt.getId().getRoa() * 100);
		}
		return strRoa == null ? "" : strRoa;
	}

	public String getStrRoe() {
		String strRoe = "";
		if (ifoComSnapshotViewExt.getId() != null) {
			strRoe = VNDirectWebUtilities.getStrDoubleWithScale2(ifoComSnapshotViewExt.getId().getRoe() * 100);
		}
		return strRoe == null ? "" : strRoe;
	}

	// Related Company
	public IfoCompanyIndustryView getIfoCompanyIndustryView() {
		return ifoCompanyIndustryView;
	}

	public void setIfoCompanyIndustryView(IfoCompanyIndustryView ifoCompanyIndustryView) {
		this.ifoCompanyIndustryView = ifoCompanyIndustryView;
	}

	public List<InfoCompanyExt> getListQuote() {
		return listQuote;
	}

	public void setListQuote(List<InfoCompanyExt> listQuote) {
		this.listQuote = listQuote;
	}

	@SuppressWarnings("unchecked")
	public SearchResult getSearchResult() {
		return searchResult;
	}

	@SuppressWarnings("unchecked")
	public void setSearchResult(SearchResult searchResult) {
		this.searchResult = searchResult;
	}

	public List<IfoInsiderTransactionView> getIfoInsiderTransactionViewList() {
		return ifoInsiderTransactionViewList;
	}

	public void setIfoInsiderTransactionViewList(List<IfoInsiderTransactionView> ifoInsiderTransactionViewList) {
		this.ifoInsiderTransactionViewList = ifoInsiderTransactionViewList;
	}

	public String getExchangeCode() {
		return exchangeCode;
	}

	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}
}
