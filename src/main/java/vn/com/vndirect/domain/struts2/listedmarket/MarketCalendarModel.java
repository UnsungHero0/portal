package vn.com.vndirect.domain.struts2.listedmarket;

import java.util.ArrayList;
import java.util.Collection;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoMarketCalendarView;
import vn.com.web.commons.domain.db.SearchResult;

@SuppressWarnings( { "unchecked", "serial" })
public class MarketCalendarModel extends BaseModel {

	private SearchResult<IfoMarketCalendarView> searchResult;
	private String srtEventDate;
	private String symbol;
	private String searchTypeOfDate;
	private String eventType;
	private String srtRightsDate;
	public String rightsDateStr;
	private String eventDay;
	private Collection typeList = new ArrayList();
	private Collection dateTypeList = new ArrayList();

	private IfoMarketCalendarView ifoMarketCalendar = new IfoMarketCalendarView();

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getSearchTypeOfDate() {
		return searchTypeOfDate;
	}

	public void setSearchTypeOfDate(String searchTypeOfDate) {
		this.searchTypeOfDate = searchTypeOfDate;
	}

	public String getSymbol() {
		return symbol;
	}

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

	public String getSrtRightsDate() {
		return srtRightsDate;
	}

	public void setSrtRightsDate(String srtRightsDate) {
		this.srtRightsDate = srtRightsDate;
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

	public Collection getTypeList() {
		return typeList;
	}

	public void setTypeList(Collection typeList) {
		this.typeList = typeList;
	}

	public Collection getDateTypeList() {
		return dateTypeList;
	}

	public void setDateTypeList(Collection dateTypeList) {
		this.dateTypeList = dateTypeList;
	}
}
