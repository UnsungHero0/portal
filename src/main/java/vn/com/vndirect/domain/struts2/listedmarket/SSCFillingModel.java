package vn.com.vndirect.domain.struts2.listedmarket;

import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.extend.SearchIfoDocument;
import vn.com.web.commons.domain.db.SearchResult;

@SuppressWarnings( { "unchecked", "serial" })
public class SSCFillingModel extends BaseModel {

	/**
	 * 
	 */

	private SearchIfoDocument searchIfoDocument = new SearchIfoDocument();
	private int numberItem = Constants.Paging.NUMBER_ITEMS;
	private int pagingIndex = 0;
	private String symbol;
	private SearchResult searchResult;

	public SearchIfoDocument getSearchIfoDocument() {
		return searchIfoDocument;
	}

	public void setSearchIfoDocument(SearchIfoDocument searchIfoDocument) {
		this.searchIfoDocument = searchIfoDocument;
	}

	public SearchResult getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(SearchResult searchResult) {
		this.searchResult = searchResult;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getNumberItem() {
		return numberItem;
	}

	public void setNumberItem(int numberItem) {
		this.numberItem = numberItem;
	}

	public int getPagingIndex() {
		return pagingIndex;
	}

	public void setPagingIndex(int pagingIndex) {
		this.pagingIndex = pagingIndex;
	}
}
