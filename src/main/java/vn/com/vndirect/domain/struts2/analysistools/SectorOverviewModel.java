/**
 * 
 */
package vn.com.vndirect.domain.struts2.analysistools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.vndirect.domain.IfoIndustryCalcView;
import vn.com.vndirect.domain.IfoSectorCalcView;
import vn.com.vndirect.wsclient.streamquotes.SecInfo;
import vn.com.web.commons.domain.db.SearchResult;

/**
 * @author Huy
 * 
 */
public class SectorOverviewModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8396409668777887844L;

	private String sectorCode;
	private List<IfoSectorCalcView> hotSectors;
	private List<IfoSectorCalcView> thrMonHighPerfSectors;
	private Map<String, IfoSectorCalcView> sector;
	private List<Map<String, IfoSectorCalcView>> sectors;

	private String industryCode;
	private IfoIndustryCalcView topIndustry;
	private Map<String, IfoIndustryCalcView> industry;
	private SearchResult<IfoIndustryCalcView> subIndustries;
	private SearchResult<IfoIndustryCalcView> topIndustries;
	private List<IfoIndustryCalcView> hotIndustries;
	private List<IfoIndustryCalcView> thrMonHighPerfIndustries;
	private List<Map<String, IfoIndustryCalcView>> industriesHighlight;

	private String symbol;

	private IfoCompanyCalcView topCompany;
	/*
	 * Companies from embed database and web service
	 */
	private List<IfoCompanyCalcView> companiesCombo;

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

	/**
	 * @return the topIndustry
	 */
	public IfoIndustryCalcView getTopIndustry() {
		return topIndustry;
	}

	/**
	 * @param topIndustry
	 *            the topIndustry to set
	 */
	public void setTopIndustry(IfoIndustryCalcView topIndustry) {
		this.topIndustry = topIndustry;
	}

	/**
	 * @return the topCompany
	 */
	public IfoCompanyCalcView getTopCompany() {
		return topCompany;
	}

	/**
	 * @param topCompany
	 *            the topCompany to set
	 */
	public void setTopCompany(IfoCompanyCalcView topCompany) {
		this.topCompany = topCompany;
	}

	/**
	 * Companies from web service
	 */
	private SecInfo[] companies;

	/**
	 * @return the companiesCombo
	 */
	public List<IfoCompanyCalcView> getCompaniesCombo() {
		return companiesCombo;
	}

	/**
	 * @param companiesCombo
	 *            the companiesCombo to set
	 */
	public void setCompaniesCombo(List<IfoCompanyCalcView> companiesCombo) {
		this.companiesCombo = companiesCombo;
	}

	/**
	 * @return the companies
	 */
	public SecInfo[] getCompanies() {
		return companies;
	}

	/**
	 * @param companies
	 *            the companies to set
	 */
	public void setCompanies(SecInfo[] companies) {
		this.companies = companies;
	}

	/**
	 * @return the sector
	 */
	public Map<String, IfoSectorCalcView> getSector() {
		return sector;
	}

	/**
	 * @param sector
	 *            the sector to set
	 */
	public void setSector(Map<String, IfoSectorCalcView> sector) {
		this.sector = sector;
	}

	/**
	 * @return the sectors
	 */
	public List<Map<String, IfoSectorCalcView>> getSectors() {
		return sectors;
	}

	/**
	 * @param sectors
	 *            the sectors to set
	 */
	public void setSectors(List<Map<String, IfoSectorCalcView>> sectors) {
		this.sectors = sectors;
	}

	/**
	 * @return the sectorCode
	 */
	public String getSectorCode() {
		return sectorCode;
	}

	/**
	 * @param sectorCode
	 *            the sectorCode to set
	 */
	public void setSectorCode(String sectorCode) {
		this.sectorCode = sectorCode;
	}

	public void setSubIndustries(SearchResult<IfoIndustryCalcView> subIndustries) {
		this.subIndustries = subIndustries;
	}

	/**
	 * Get divided industries to display horizontal
	 * 
	 * @return
	 */
	public List<List<IfoIndustryCalcView>> getSubIndustries() {
		List<List<IfoIndustryCalcView>> results = new ArrayList<List<IfoIndustryCalcView>>();
		if (subIndustries != null) {
			int size = subIndustries.size();
			int rows = size / 4; // number item per column
			if (rows * 4 < size) {
				rows++;
			}
			if (rows < 3)
				rows = 3;// minimum 3 records per row

			for (int i = 0; i < size; i++) {
				List<IfoIndustryCalcView> result = new ArrayList<IfoIndustryCalcView>();
				for (int j = 0; j < rows; j++) {
					try {
						result.add(subIndustries.get(i++));
					} catch (Exception e) {
						// ignore it
					}
				}
				if (i != size)
					i--;
				results.add(result);
			}
		}
		return results;
	}

	/**
	 * @return the hotIndustries
	 */
	public List<IfoIndustryCalcView> getHotIndustries() {
		return hotIndustries;
	}

	/**
	 * @param hotIndustries
	 *            the hotIndustries to set
	 */
	public void setHotIndustries(List<IfoIndustryCalcView> hotIndustries) {
		this.hotIndustries = hotIndustries;
	}

	/**
	 * @return the hotSectors
	 */
	public List<IfoSectorCalcView> getHotSectors() {
		return hotSectors;
	}

	/**
	 * @param hotSectors
	 *            the hotSectors to set
	 */
	public void setHotSectors(List<IfoSectorCalcView> hotSectors) {
		this.hotSectors = hotSectors;
	}

	/**
	 * @return the thrMonHighPerfSectors
	 */
	public List<IfoSectorCalcView> getThrMonHighPerfSectors() {
		return thrMonHighPerfSectors;
	}

	/**
	 * @param thrMonHighPerfSectors
	 *            the thrMonHighPerfSectors to set
	 */
	public void setThrMonHighPerfSectors(List<IfoSectorCalcView> thrMonHighPerfSectors) {
		this.thrMonHighPerfSectors = thrMonHighPerfSectors;
	}

	/**
	 * @return the thrMonHighPerfIndustries
	 */
	public List<IfoIndustryCalcView> getThrMonHighPerfIndustries() {
		return thrMonHighPerfIndustries;
	}

	/**
	 * @param thrMonHighPerfIndustries
	 *            the thrMonHighPerfIndustries to set
	 */
	public void setThrMonHighPerfIndustries(List<IfoIndustryCalcView> thrMonHighPerfIndustries) {
		this.thrMonHighPerfIndustries = thrMonHighPerfIndustries;
	}

	/**
	 * @return the industriesHighlight
	 */
	public List<Map<String, IfoIndustryCalcView>> getIndustriesHighlight() {
		return industriesHighlight;
	}

	/**
	 * @param industriesHighlight
	 *            the industriesHighlight to set
	 */
	public void setIndustriesHighlight(List<Map<String, IfoIndustryCalcView>> industriesHighlight) {
		this.industriesHighlight = industriesHighlight;
	}

	/**
	 * @return the topIndustries
	 */
	public SearchResult<IfoIndustryCalcView> getTopIndustries() {
		return topIndustries;
	}

	/**
	 * @param topIndustries
	 *            the topIndustries to set
	 */
	public void setTopIndustries(SearchResult<IfoIndustryCalcView> topIndustries) {
		this.topIndustries = topIndustries;
	}

	/**
	 * @return the industry
	 */
	public Map<String, IfoIndustryCalcView> getIndustry() {
		return industry;
	}

	/**
	 * @param industry
	 *            the industry to set
	 */
	public void setIndustry(Map<String, IfoIndustryCalcView> industry) {
		this.industry = industry;
	}

	/**
	 * @return the industryCode
	 */
	public String getIndustryCode() {
		return industryCode;
	}

	/**
	 * @param industryCode
	 *            the industryCode to set
	 */
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
}
