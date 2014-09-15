package vn.com.vndirect.domain.struts2.listedmarket;

import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.extend.SearchMarketStatisticsView;
import vn.com.web.commons.domain.db.SearchResult;

@SuppressWarnings( { "unchecked", "serial" })
public class MarketStatisticsModel extends BaseModel {

	/**
	 * 
	 */

	// Use all for Market Statistics
	private String strFromDate;
	private String strToDate;
	private SearchResult searchResult = new SearchResult();
	private SearchMarketStatisticsView searchMarketStatisticsView = new SearchMarketStatisticsView();

	// Use for Trading Statistics
	private List listMarket;
	private String strTradingDate;

	private String downloadType; // using for download report
	private String symbol;

	/**
	 * @return the downloadType
	 */
	public String getDownloadType() {
		return downloadType;
	}

	/**
	 * @param downloadType
	 *            the downloadType to set
	 */
	public void setDownloadType(String downloadType) {
		this.downloadType = downloadType;
	}

	// Getters and Setter for all Market Statistics
	public String getStrFromDate() {
		return strFromDate;
	}

	public void setStrFromDate(String strFromDate) {
		this.strFromDate = strFromDate;
	}

	public String getStrToDate() {
		return strToDate;
	}

	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
	}

	public SearchResult getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(SearchResult searchResult) {
		this.searchResult = searchResult;
	}

	public SearchMarketStatisticsView getSearchMarketStatisticsView() {
		return searchMarketStatisticsView;
	}

	public void setSearchMarketStatisticsView(SearchMarketStatisticsView searchMarketStatisticsView) {
		this.searchMarketStatisticsView = searchMarketStatisticsView;
	}

	// Getter and Setter for Trading Statistics
	public List getListMarket() {
		return listMarket;
	}

	public void setListMarket(List listMarket) {
		this.listMarket = listMarket;
	}

	public String getStrTradingDate() {
		return strTradingDate;
	}

	public void setStrTradingDate(String strTradingDate) {
		this.strTradingDate = strTradingDate;
	}

	public void setSearchMarket(String market) {
		if (searchMarketStatisticsView != null) {
			searchMarketStatisticsView.setMarket(market);
		}
	}

	public String getSearchMarket() {
		if (searchMarketStatisticsView != null) {
			return searchMarketStatisticsView.getMarket();
		} else {
			return null;
		}
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
