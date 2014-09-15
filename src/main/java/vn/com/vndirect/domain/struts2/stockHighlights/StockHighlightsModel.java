package vn.com.vndirect.domain.struts2.stockHighlights;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoDocument;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.domain.db.SearchResult;

@SuppressWarnings("serial")
public class StockHighlightsModel extends BaseModel {

	private SearchResult<SearchIfoNews> searchIfoNewsResult;
	private SearchIfoNews searchIfoNews;
	private String newsId;
	private ArrayList<String> listSymbolsHaveReports;
	private ArrayList<String> listSymbolsFreeReports;
	private String filePath;

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public SearchResult<SearchIfoNews> getSearchIfoNewsResult() {
		return searchIfoNewsResult;
	}

	public void setSearchIfoNewsResult(SearchResult<SearchIfoNews> searchIfoNewsResult) {
		this.searchIfoNewsResult = searchIfoNewsResult;
	}

	public ArrayList<String> getListSymbolsHaveReports() {
		return listSymbolsHaveReports;
	}

	public void setListSymbolsHaveReports(ArrayList<String> listSymbolsHaveReports) {
		this.listSymbolsHaveReports = listSymbolsHaveReports;
	}

	public ArrayList<String> getListSymbolsFreeReports() {
		return listSymbolsFreeReports;
	}

	public void setListSymbolsFreeReports(ArrayList<String> listSymbolsFreeReports) {
		this.listSymbolsFreeReports = listSymbolsFreeReports;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public SearchIfoNews getSearchIfoNews() {
		return searchIfoNews;
	}

	public void setSearchIfoNews(SearchIfoNews searchIfoNews) {
		this.searchIfoNews = searchIfoNews;
	}
}
