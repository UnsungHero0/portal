package vn.com.vndirect.domain.struts2.analysistools;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoCompanyIndustryViewId;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.IfoCompanySnapshotViewExt;
import vn.com.vndirect.wsclient.streamquotes.MarketInfo;
import vn.com.vndirect.wsclient.streamquotes.SecInfo;

public class FlashChartModel extends BaseModel {
	private static final long serialVersionUID = -2412164485846553943L;
	// Use for Company Snapshot Information
	private IfoCompanySnapshotViewExt ifoComSnapshotViewExt = new IfoCompanySnapshotViewExt();
	private MarketInfo marketInfo;
	private SecInfo secInfo;
	private String title;
	private CurrentCompanyForQuote currentCompanyForQuote;
	private IfoCompanyIndustryViewId ifoCompanyIndustryViewId;
	private boolean isShowHoHaInfo;

	private String symbol;

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 *            the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	// Getter and Setter for Company Snapshot Information
	public boolean isShowHoHaInfo() {
		return isShowHoHaInfo;
	}

	public void setShowHoHaInfo(boolean isShowHoHaInfo) {
		this.isShowHoHaInfo = isShowHoHaInfo;
	}

	public IfoCompanySnapshotViewExt getIfoComSnapshotViewExt() {
		return ifoComSnapshotViewExt;
	}

	public void setIfoComSnapshotViewExt(IfoCompanySnapshotViewExt ifoComSnapshotViewExt) {
		this.ifoComSnapshotViewExt = ifoComSnapshotViewExt;
	}

	public MarketInfo getMarketInfo() {
		return marketInfo;
	}

	public void setMarketInfo(MarketInfo marketInfo) {
		this.marketInfo = marketInfo;
	}

	public SecInfo getSecInfo() {
		return secInfo;
	}

	public void setSecInfo(SecInfo secInfo) {
		this.secInfo = secInfo;
	}

	public int getInfoType() {
		if (secInfo != null) {
			return 1;
		}

		if (marketInfo != null) {
			return 2;
		}

		return 0;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CurrentCompanyForQuote getCurrentCompanyForQuote() {
		return currentCompanyForQuote;
	}

	public void setCurrentCompanyForQuote(CurrentCompanyForQuote currentCompanyForQuote) {
		this.currentCompanyForQuote = currentCompanyForQuote;
	}

	public IfoCompanyIndustryViewId getIfoCompanyIndustryViewId() {
		return ifoCompanyIndustryViewId;
	}

	public void setIfoCompanyIndustryViewId(IfoCompanyIndustryViewId ifoCompanyIndustryViewId) {
		this.ifoCompanyIndustryViewId = ifoCompanyIndustryViewId;
	}

}
