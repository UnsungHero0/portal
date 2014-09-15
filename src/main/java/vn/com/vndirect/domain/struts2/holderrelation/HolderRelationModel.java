package vn.com.vndirect.domain.struts2.holderrelation;

import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.vndirect.domain.IfoDocument;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.IfoCompanySnapshotViewExt;
import vn.com.vndirect.domain.extend.SecuritiesInfoForQuote;
import vn.com.web.commons.domain.db.SearchResult;

/**
 *
 */
@SuppressWarnings("serial")
public class HolderRelationModel extends BaseModel {

	private IfoCompanySnapshotViewExt ifoComSnapshotViewExt = new IfoCompanySnapshotViewExt();

	private IfoCompanyCalcView ifoCompanyCalcView = new IfoCompanyCalcView();

	private String symbol;

	private CurrentCompanyForQuote companyInfo;

	private SecuritiesInfoForQuote secuInfoForQuote;

	private SearchResult<IfoDocument> searchResult;

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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public CurrentCompanyForQuote getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CurrentCompanyForQuote companyInfo) {
		this.companyInfo = companyInfo;
	}

	public SecuritiesInfoForQuote getSecuInfoForQuote() {
		return secuInfoForQuote;
	}

	public void setSecuInfoForQuote(SecuritiesInfoForQuote secuInfoForQuote) {
		this.secuInfoForQuote = secuInfoForQuote;
	}

	public String getCurrentPrice() {
		String result = "-";
		if (secuInfoForQuote != null) {
			result = VNDirectWebUtilities.getStrDoubleWithScale2(secuInfoForQuote.getCurrentPrice());
		}
		return result;
	}

	public SearchResult<IfoDocument> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(SearchResult<IfoDocument> searchResult) {
		this.searchResult = searchResult;
	}

}
