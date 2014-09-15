/**
 * 
 */
package vn.com.vndirect.domain.struts2.analysistools;

import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.vndirect.domain.IfoIndustryCalcView;
import vn.com.vndirect.domain.IfoSectorCalcView;
import vn.com.web.commons.domain.db.SearchResult;

/**
 * @author Huy
 * 
 */
public class ListSectorModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5285115398376794394L;
	private IfoSectorCalcView ifoSectorCalcView = new IfoSectorCalcView();
	private IfoIndustryCalcView ifoIndustryCalcView = new IfoIndustryCalcView();
	private IfoCompanyCalcView ifoCompanyCalcView = new IfoCompanyCalcView();
	private String sectorCodeSelected = "0";
	
	public static final String FROM_VALUE = "FlashChart";

	/**
	 * used to specify whether display view by sector or alpha
	 */
	public static final String BY_SECTOR = "SECTOR_NAME";
	public static final String ALPHABETICAL = "INDUSTRY_NAME";

	/**
	 * A parameter is used to determine whether <code>SecCode</code> property of the <code>ifoCompanyCalcView</code> object is grabbed from session
	 */
	private String from;

	private SearchResult<?> searchResult;

	/**
	 * The list of industries on the left page
	 */
	private List<IfoIndustryCalcView> industries1;

	/**
	 * The list of industries on the center page
	 */
	private List<IfoIndustryCalcView> industries2;
	
	/**
	 * The list of industries on the right page
	 */
	private List<IfoIndustryCalcView> industries3;
	
	/**
	 * @return the ifoIndustryCalcView
	 */
	public IfoIndustryCalcView getIfoIndustryCalcView() {
		return ifoIndustryCalcView;
	}

	/**
	 * @param ifoIndustryCalcView
	 *            the ifoIndustryCalcView to set
	 */
	public void setIfoIndustryCalcView(IfoIndustryCalcView ifoIndustryCalcView) {
		this.ifoIndustryCalcView = ifoIndustryCalcView;
	}

	/**
	 * @return the ifoCompanyCalcView
	 */
	public IfoCompanyCalcView getIfoCompanyCalcView() {
		return ifoCompanyCalcView;
	}

	/**
	 * @param ifoCompanyCalcView
	 *            the ifoCompanyCalcView to set
	 */
	public void setIfoCompanyCalcView(IfoCompanyCalcView ifoCompanyCalcView) {
		this.ifoCompanyCalcView = ifoCompanyCalcView;
	}

	/**
	 * @return the ifoSectorCalcView
	 */
	public IfoSectorCalcView getIfoSectorCalcView() {
		return ifoSectorCalcView;
	}

	/**
	 * @param ifoSectorCalcView
	 *            the ifoSectorCalcView to set
	 */
	public void setIfoSectorCalcView(IfoSectorCalcView ifoSectorCalcView) {
		this.ifoSectorCalcView = ifoSectorCalcView;
	}

	/**
	 * @return the searchResult
	 */
	public SearchResult<?> getSearchResult() {
		return searchResult;
	}

	/**
	 * @param searchResult
	 *            the searchResult to set
	 */
	public void setSearchResult(SearchResult<?> searchResult) {
		this.searchResult = searchResult;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the industries1
	 */
	public List<IfoIndustryCalcView> getIndustries1() {
		return industries1;
	}

	/**
	 * @param industries1
	 *            the industries1 to set
	 */
	public void setIndustries1(List<IfoIndustryCalcView> industries1) {
		this.industries1 = industries1;
	}

	/**
	 * @return the industries2
	 */
	public List<IfoIndustryCalcView> getIndustries2() {
		return industries2;
	}

	/**
	 * @param industries2
	 *            the industries2 to set
	 */
	public void setIndustries2(List<IfoIndustryCalcView> industries2) {
		this.industries2 = industries2;
	}

	public List<IfoIndustryCalcView> getIndustries3() {
		return industries3;
	}

	public void setIndustries3(List<IfoIndustryCalcView> industries3) {
		this.industries3 = industries3;
	}

	public String getSectorCodeSelected() {
		return sectorCodeSelected;
	}

	public void setSectorCodeSelected(String sectorCodeSelected) {
		this.sectorCodeSelected = sectorCodeSelected;
	}
}
