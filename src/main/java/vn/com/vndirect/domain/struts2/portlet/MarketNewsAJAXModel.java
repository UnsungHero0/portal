package vn.com.vndirect.domain.struts2.portlet;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.domain.db.SearchResult;

@SuppressWarnings( { "unchecked", "serial" })
public class MarketNewsAJAXModel extends BaseModel {

	private SearchIfoNews searchIfoNews = new SearchIfoNews();
	private SearchResult searchResult;

	public SearchIfoNews getSearchIfoNews() {
		return searchIfoNews;
	}

	public void setSearchIfoNews(SearchIfoNews searchIfoNews) {
		this.searchIfoNews = searchIfoNews;
	}

	public SearchResult getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(SearchResult searchResult) {
		this.searchResult = searchResult;
	}
}
