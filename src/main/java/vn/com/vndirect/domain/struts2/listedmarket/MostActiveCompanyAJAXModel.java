package vn.com.vndirect.domain.struts2.listedmarket;

import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.extend.CompanySummary;
import vn.com.vndirect.domain.extend.MarketOption;

/**
 * @author NguyenDucQuang
 * @version 1.0
 * @created Mar 19, 2010 3:33:39 PM
 */
@SuppressWarnings("serial")
public class MostActiveCompanyAJAXModel extends BaseModel {
	private List<CompanySummary> listHostcSummary;
	private List<CompanySummary> listHastcSummary;
	private List<CompanySummary> listUpComSummary;
	private MarketOption marketOption = new MarketOption();
	private String floorCode; // store previous status view : HASTC, HOSTC or UPCOM

	/**
	 * @return the listHastcSummary
	 */
	public List<CompanySummary> getListHastcSummary() {
		return listHastcSummary;
	}

	/**
	 * @param listHastcSummary
	 *            the listHastcSummary to set
	 */
	public void setListHastcSummary(List<CompanySummary> listHastcSummary) {
		this.listHastcSummary = listHastcSummary;
	}

	/**
	 * @return the listHostcSummary
	 */
	public List<CompanySummary> getListHostcSummary() {
		return listHostcSummary;
	}

	/**
	 * @param listHostcSummary
	 *            the listHostcSummary to set
	 */
	public void setListHostcSummary(List<CompanySummary> listHostcSummary) {
		this.listHostcSummary = listHostcSummary;
	}

	/**
	 * @return the listUpComSummary
	 */
	public List<CompanySummary> getListUpComSummary() {
		return listUpComSummary;
	}

	/**
	 * @param listUpComSummary
	 *            the listUpComSummary to set
	 */
	public void setListUpComSummary(List<CompanySummary> listUpComSummary) {
		this.listUpComSummary = listUpComSummary;
	}

	/**
	 * @return the marketOption
	 */
	public MarketOption getMarketOption() {
		return marketOption;
	}

	/**
	 * @param marketOption
	 *            the marketOption to set
	 */
	public void setMarketOption(MarketOption marketOption) {
		this.marketOption = marketOption;
	}

	/**
	 * @return the floorCode
	 */
	public String getFloorCode() {
		return floorCode;
	}

	/**
	 * @param floorCode
	 *            the floorCode to set
	 */
	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}

}
