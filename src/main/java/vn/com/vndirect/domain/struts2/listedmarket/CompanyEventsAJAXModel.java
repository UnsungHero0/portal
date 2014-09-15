package vn.com.vndirect.domain.struts2.listedmarket;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.domain.db.SearchResult;

/**
 * @author NguyenDucQuang
 * @version 1.0
 * @created Mar 18, 2010 10:47:53 AM
 */
@SuppressWarnings("serial")
public class CompanyEventsAJAXModel extends BaseModel {
	private SearchIfoNews searchIfoNews = new SearchIfoNews();
	private SearchResult<SearchIfoNews> searchResult;

	public SearchIfoNews getSearchIfoNews() {
		return searchIfoNews;
	}

	public void setSearchIfoNews(SearchIfoNews searchIfoNews) {
		this.searchIfoNews = searchIfoNews;
	}

	public SearchResult<SearchIfoNews> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(SearchResult<SearchIfoNews> searchResult) {
		this.searchResult = searchResult;
	}
}
