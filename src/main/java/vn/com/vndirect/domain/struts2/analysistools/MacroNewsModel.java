/**
 * 
 */
package vn.com.vndirect.domain.struts2.analysistools;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.domain.db.SearchResult;

/**
 * @author Huy
 * 
 */
public class MacroNewsModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2355547677086889691L;
	private SearchResult<SearchIfoNews> searchResult;
	private String newsType;
	private SearchIfoNews news;

	/**
	 * @return the searchResult
	 */
	public SearchResult<SearchIfoNews> getSearchResult() {
		return searchResult;
	}

	/**
	 * @param searchResult
	 *            the searchResult to set
	 */
	public void setSearchResult(SearchResult<SearchIfoNews> searchResult) {
		this.searchResult = searchResult;
	}

	/**
	 * @return the newsType
	 */
	public String getNewsType() {
		return newsType;
	}

	/**
	 * @param newsType
	 *            the newsType to set
	 */
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	/**
	 * @return the news
	 */
	public SearchIfoNews getNews() {
		return news;
	}

	/**
	 * @param news
	 *            the news to set
	 */
	public void setNews(SearchIfoNews news) {
		this.news = news;
	}
}
