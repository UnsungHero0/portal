package vn.com.vndirect.domain.struts2.consultingcenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.utility.DateUtils;

public class StockPickModel extends BaseModel {
	private String date;
	private List<SearchIfoNews> searchIfoNews;
	private SearchIfoNews ifoNews;
	private List<String> datesByyyyymmddFormat;
	private List<String> datesByddMMyyyyFormat;
	private SearchIfoNews marketNews;
	private String registerStatus;
	private boolean isCommonNews;

	public List<SearchIfoNews> getSearchIfoNews() {
		return searchIfoNews;
	}

	public void setSearchIfoNews(List<SearchIfoNews> searchIfoNews) {
		this.searchIfoNews = searchIfoNews;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<String> getDatesByyyyymmddFormat() {
		return datesByyyyymmddFormat;
	}

	public void setDatesByyyyymmddFormat(List<String> datesByyyyymmddFormat) {
		this.datesByyyyymmddFormat = datesByyyyymmddFormat;
	}

	public List<String> getDatesByddMMyyyyFormat() {
		return datesByddMMyyyyFormat;
	}

	public void setDatesByddMMyyyyFormat(List<String> datesByddMMyyyyFormat) {
		this.datesByddMMyyyyFormat = datesByddMMyyyyFormat;
	}

	public SearchIfoNews getMarketNews() {
		return marketNews;
	}

	public void setMarketNews(SearchIfoNews marketNews) {
		this.marketNews = marketNews;
	}

	public SearchIfoNews getIfoNews() {
		return ifoNews;
	}

	public void setIfoNews(SearchIfoNews ifoNews) {
		this.ifoNews = ifoNews;
	}

	public String getRegisterStatus() {
		return registerStatus;
	}

	public void setRegisterStatus(String registerStatus) {
		this.registerStatus = registerStatus;
	}

	public boolean isCommonNews() {
		return isCommonNews;
	}

	public void setCommonNews(boolean isCommonNews) {
		this.isCommonNews = isCommonNews;
	}
}
