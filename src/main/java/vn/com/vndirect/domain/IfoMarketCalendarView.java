package vn.com.vndirect.domain;

import java.util.Date;

import vn.com.web.commons.utility.DateUtils;

public class IfoMarketCalendarView extends BaseBean implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4463860782922523859L;
	private Long companyId;
	private String symbol;
	private Date registerDate;
	private Date rightsDate;
	private Date eventDate;
	private String eventType;
	private String eventDesc;
	private String local;
	private String eventNote;
	private Date fromEventDate;
	private Date toEventDate;
	private Date fromRightsDate;
	private Date toRightsDate;
	private String searchTypeOfDate;
	private String strMonth;
	private String[] listSymbols;

	public Date getRightsDate() {
		return rightsDate;
	}

	public void setRightsDate(Date rightsDate) {
		this.rightsDate = rightsDate;
	}

	public String getStrMonth() {
		return strMonth;
	}

	public void setStrMonth(String strMonth) {
		this.strMonth = strMonth;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventDesc() {
		return eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	public String getEventNote() {
		return eventNote;
	}

	public void setEventNote(String eventNote) {
		this.eventNote = eventNote;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSearchTypeOfDate() {
		return searchTypeOfDate;
	}

	public void setSearchTypeOfDate(String searchTypeOfDate) {
		this.searchTypeOfDate = searchTypeOfDate;
	}

	public Date getFromEventDate() {
		return fromEventDate;
	}

	public void setFromEventDate(Date fromEventDate) {
		this.fromEventDate = fromEventDate;
	}

	public Date getToEventDate() {
		return toEventDate;
	}

	public void setToEventDate(Date toEventDate) {
		this.toEventDate = toEventDate;
	}

	public Date getFromRightsDate() {
		return fromRightsDate;
	}

	public void setFromRightsDate(Date fromRightsDate) {
		this.fromRightsDate = fromRightsDate;
	}

	public Date getToRightsDate() {
		return toRightsDate;
	}

	public void setToRightsDate(Date toRightsDate) {
		this.toRightsDate = toRightsDate;
	}

	/**
	 * 
	 * @return
	 */
	public String getEventDateStr() {
		String strDate = "";
		try {
			strDate = DateUtils.dateToString(this.eventDate, DateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD);
		} catch (Exception e) {
		}
		return strDate;
	}

	/**
	 * 
	 * @return
	 */
	public String getDisplayEventDate() {
		String strDate = "";
		try {
			strDate = DateUtils.dateToString(this.eventDate, "E, MMM dd yyyy");
		} catch (Exception e) {
		}
		return strDate;
	}

	/**
	 * @return the listSymbols
	 */
	public String[] getListSymbols() {
		return this.listSymbols;
	}

	/**
	 * @param listSymbols
	 *            the listSymbols to set
	 */
	public void setListSymbols(String[] listSymbols) {
		this.listSymbols = listSymbols;
	}
}
