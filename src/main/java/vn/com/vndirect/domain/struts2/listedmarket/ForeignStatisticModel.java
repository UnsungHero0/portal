package vn.com.vndirect.domain.struts2.listedmarket;

import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.SearchIfoForeignTradingView;
import vn.com.web.commons.domain.db.SearchResult;

@SuppressWarnings( { "unchecked", "serial" })
public class ForeignStatisticModel extends BaseModel {
	private SearchIfoForeignTradingView searchIfoForeignTradingView = new SearchIfoForeignTradingView();
	private SearchResult SearchResult;
	private List listMarket;
	private String strTradingDate;

	public SearchIfoForeignTradingView getSearchIfoForeignTradingView() {
		return searchIfoForeignTradingView;
	}

	public void setSearchIfoForeignTradingView(SearchIfoForeignTradingView searchIfoForeignTradingView) {
		this.searchIfoForeignTradingView = searchIfoForeignTradingView;
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
