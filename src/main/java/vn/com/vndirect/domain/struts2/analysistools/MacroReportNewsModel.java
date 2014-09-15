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
public class MacroReportNewsModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4346479162170429479L;
	private SearchResult<SearchIfoNews> searchResult;
	private String symbol;

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
}
