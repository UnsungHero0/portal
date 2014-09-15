package vn.com.vndirect.domain.struts2.listedmarket;

import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.extend.SearchMarketStatisticsView;
import vn.com.web.commons.domain.db.SearchResult;

@SuppressWarnings( { "unchecked", "serial" })
public class TradingStatisticModel extends BaseModel {

	private SearchMarketStatisticsView searchMarketStatisticsView = new SearchMarketStatisticsView();

	private SearchResult SearchResult;
	private List listMarket;
	private String strTradingDate;

	public SearchMarketStatisticsView getSearchMarketStatisticsView() {
		return searchMarketStatisticsView;
	}

	public void setSearchIfoTradingStatisticsView(SearchMarketStatisticsView searchMarketStatisticsView) {
		this.searchMarketStatisticsView = searchMarketStatisticsView;
	}

	public SearchResult getSearchResult() {
		return SearchResult;
	}

	public void setSearchResult(SearchResult SearchResult) {
		this.SearchResult = SearchResult;
	}

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

}