package vn.com.vndirect.domain.struts2.home;

import vn.com.vndirect.domain.IfoDocument;
import vn.com.vndirect.domain.extend.IfoCompanySnapshotViewExt;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.vndirect.domain.extend.SecuritiesInfoForQuote;
import vn.com.vndirect.wsclient.streamquotes.SecInfo;
import vn.com.web.commons.domain.db.SearchResult;

public class IRSnapshotModel {
	private SecuritiesInfoForQuote securitiesInfoForQuote;
	private IfoCompanySnapshotViewExt ifoCompanySnapshotViewExt;
	private SearchResult<SearchIfoNews> mostViewResult;
	private Boolean isDataFromDatabase;
	private String symbol;
	private SecInfo secInfo;

	public SecInfo getSecInfo() {
		return secInfo;
	}

	public void setSecInfo(SecInfo secInfo) {
		this.secInfo = secInfo;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Boolean getIsDataFromDatabase() {
		return isDataFromDatabase;
	}

	public void setIsDataFromDatabase(Boolean isDataFromDatabase) {
		this.isDataFromDatabase = isDataFromDatabase;
	}

	SearchResult<IfoDocument> IfoDocumentResult;

	public SecuritiesInfoForQuote getSecuritiesInfoForQuote() {
		return securitiesInfoForQuote;
	}

	public void setSecuritiesInfoForQuote(SecuritiesInfoForQuote securitiesInfoForQuote) {
		this.securitiesInfoForQuote = securitiesInfoForQuote;
	}

	public IfoCompanySnapshotViewExt getIfoCompanySnapshotViewExt() {
		return ifoCompanySnapshotViewExt;
	}

	public void setIfoCompanySnapshotViewExt(IfoCompanySnapshotViewExt ifoCompanySnapshotViewExt) {
		this.ifoCompanySnapshotViewExt = ifoCompanySnapshotViewExt;
	}

	public SearchResult<SearchIfoNews> getMostViewResult() {
		return mostViewResult;
	}

	public void setMostViewResult(SearchResult<SearchIfoNews> mostViewResult) {
		this.mostViewResult = mostViewResult;
	}

	public SearchResult<IfoDocument> getIfoDocumentResult() {
		return IfoDocumentResult;
	}

	public void setIfoDocumentResult(SearchResult<IfoDocument> ifoDocumentResult) {
		IfoDocumentResult = ifoDocumentResult;
	}
}
