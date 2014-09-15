package vn.com.vndirect.domain.struts2.common;

import java.util.Date;
import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.web.commons.domain.db.SearchResult;

@SuppressWarnings( { "unchecked", "serial" })
public class SitemapModel extends BaseModel {
	private Date currentDate;
	private SearchResult vndirectNewsSearchResult;
	private SearchResult irDisclosureNewsSearchResult;
	private SearchResult irCompanyEventsNewsSearchResult;
	private SearchResult macVnNewsSearchResult;
	private SearchResult worldNewsSearchResult;
	private SearchResult dailyReportSearchResult;
	private SearchResult expertNewsSearchResult;
	private SearchResult disclosureNewsSearchResult;
	private SearchResult stockList;
	private SearchResult siDisclosureNewsSearchResult;
	private SearchResult siCompanyEventsNewsSearchResult;
	private List<List<List<String>>> listSectorsAndIndustries;

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public SearchResult getVndirectNewsSearchResult() {
		return vndirectNewsSearchResult;
	}

	public void setVndirectNewsSearchResult(SearchResult vndirectNewsSearchResult) {
		this.vndirectNewsSearchResult = vndirectNewsSearchResult;
	}

	public SearchResult getIrDisclosureNewsSearchResult() {
		return irDisclosureNewsSearchResult;
	}

	public void setIrDisclosureNewsSearchResult(SearchResult irDisclosureNewsSearchResult) {
		this.irDisclosureNewsSearchResult = irDisclosureNewsSearchResult;
	}

	public SearchResult getIrCompanyEventsNewsSearchResult() {
		return irCompanyEventsNewsSearchResult;
	}

	public void setIrCompanyEventsNewsSearchResult(SearchResult irCompanyEventsNewsSearchResult) {
		this.irCompanyEventsNewsSearchResult = irCompanyEventsNewsSearchResult;
	}

	public SearchResult getMacVnNewsSearchResult() {
		return macVnNewsSearchResult;
	}

	public void setMacVnNewsSearchResult(SearchResult macVnNewsSearchResult) {
		this.macVnNewsSearchResult = macVnNewsSearchResult;
	}

	public SearchResult getWorldNewsSearchResult() {
		return worldNewsSearchResult;
	}

	public void setWorldNewsSearchResult(SearchResult worldNewsSearchResult) {
		this.worldNewsSearchResult = worldNewsSearchResult;
	}

	public SearchResult getExpertNewsSearchResult() {
		return expertNewsSearchResult;
	}

	public void setExpertNewsSearchResult(SearchResult expertNewsSearchResult) {
		this.expertNewsSearchResult = expertNewsSearchResult;
	}

	public SearchResult getDisclosureNewsSearchResult() {
		return disclosureNewsSearchResult;
	}

	public void setDisclosureNewsSearchResult(SearchResult disclosureNewsSearchResult) {
		this.disclosureNewsSearchResult = disclosureNewsSearchResult;
	}

	public SearchResult getDailyReportSearchResult() {
		return dailyReportSearchResult;
	}

	public void setDailyReportSearchResult(SearchResult dailyReportSearchResult) {
		this.dailyReportSearchResult = dailyReportSearchResult;
	}

	public SearchResult getStockList() {
		return stockList;
	}

	public void setStockList(SearchResult stockList) {
		this.stockList = stockList;
	}

	public SearchResult getSiDisclosureNewsSearchResult() {
		return siDisclosureNewsSearchResult;
	}

	public void setSiDisclosureNewsSearchResult(SearchResult siDisclosureNewsSearchResult) {
		this.siDisclosureNewsSearchResult = siDisclosureNewsSearchResult;
	}

	public SearchResult getSiCompanyEventsNewsSearchResult() {
		return siCompanyEventsNewsSearchResult;
	}

	public void setSiCompanyEventsNewsSearchResult(SearchResult siCompanyEventsNewsSearchResult) {
		this.siCompanyEventsNewsSearchResult = siCompanyEventsNewsSearchResult;
	}

	public List<List<List<String>>> getListSectorsAndIndustries() {
		return listSectorsAndIndustries;
	}

	public void setListSectorsAndIndustries(List<List<List<String>>> listSectorsAndIndustries) {
		this.listSectorsAndIndustries = listSectorsAndIndustries;
	}
}
