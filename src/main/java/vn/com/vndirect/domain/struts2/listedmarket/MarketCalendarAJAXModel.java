package vn.com.vndirect.domain.struts2.listedmarket;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoMarketCalendarView;
import vn.com.web.commons.domain.db.SearchResult;

/**
 * @author NguyenDucQuang
 * @version 1.0
 * @created Mar 23, 2010 11:31:50 AM
 */
@SuppressWarnings("serial")
public class MarketCalendarAJAXModel extends BaseModel {
	private SearchResult<IfoMarketCalendarView> searchResult;
	private String srtEventDate;
	private String symbol;
	private String searchTypeOfDate;
	private String eventType;

	// Use for Market Calendar
	private String strRightsDate;
	public String rightsDateStr;
	private String eventDay;
	private IfoMarketCalendarView ifoMarketCalendar = new IfoMarketCalendarView();

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType
	 *            the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the searchTypeOfDate
	 */
	public String getSearchTypeOfDate() {
		return searchTypeOfDate;
	}

	/**
	 * @param searchTypeOfDate
	 *            the searchTypeOfDate to set
	 */
	public void setSearchTypeOfDate(String searchTypeOfDate) {
		this.searchTypeOfDate = searchTypeOfDate;
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

	public SearchResult<IfoMarketCalendarView> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(SearchResult<IfoMarketCalendarView> searchResult) {
		this.searchResult = searchResult;
	}

	public String getSrtEventDate() {
		return srtEventDate;
	}

	public void setSrtEventDate(String srtEventDate) {
		this.srtEventDate = srtEventDate;
	}

	// Use for Market Calendar
	public String getStrRightsDate() {
		return strRightsDate;
	}

	public void setStrRightsDate(String strRightsDate) {
		this.strRightsDate = strRightsDate;
	}

	public String getRightsDateStr() {
		return rightsDateStr;
	}

	public void setRightsDateStr(String rightsDateStr) {
		this.rightsDateStr = rightsDateStr;
	}

	public String getEventDay() {
		return eventDay;
	}

	public void setEventDay(String eventDay) {
		this.eventDay = eventDay;
	}

	public IfoMarketCalendarView getIfoMarketCalendar() {
		return ifoMarketCalendar;
	}

	public void setIfoMarketCalendar(IfoMarketCalendarView ifoMarketCalendar) {
		this.ifoMarketCalendar = ifoMarketCalendar;
	}
}
