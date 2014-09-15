package vn.com.vndirect.domain.struts2.listedmarket;

import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoCompanyNameViewId;

@SuppressWarnings( { "unchecked", "serial" })
public class KeyStatisticModel extends BaseModel {

	private List ifoValuationMeasuresViewList;
	private List keyRatioList;
	private List financialHighlightList;
	private IfoCompanyNameViewId ifoCompanyNameViewId;
	private List ifoInvestorRightsViewList;
	private String symbol;

	public List getKeyRatioList() {
		return keyRatioList;
	}

	public void setKeyRatioList(List keyRatioList) {
		this.keyRatioList = keyRatioList;
	}

	public IfoCompanyNameViewId getIfoCompanyNameViewId() {
		return ifoCompanyNameViewId;
	}

	public void setIfoCompanyNameViewId(IfoCompanyNameViewId ifoCompanyNameViewId) {
		this.ifoCompanyNameViewId = ifoCompanyNameViewId;
	}

	public List getFinancialHighlightList() {
		return financialHighlightList;
	}

	public void setFinancialHighlightList(List financialHighlightList) {
		this.financialHighlightList = financialHighlightList;
	}

	/**
	 * @return the ifoInvestorRightsView
	 */
	public List getIfoInvestorRightsViewList() {
		return ifoInvestorRightsViewList;
	}

	/**
	 * @param ifoInvestorRightsView
	 *            the ifoInvestorRightsView to set
	 */
	public void setIfoInvestorRightsViewList(List ifoInvestorRightsViewList) {
		this.ifoInvestorRightsViewList = ifoInvestorRightsViewList;
	}

	/**
	 * @return the ifoValuationMeasuresViewList
	 */
	public List getIfoValuationMeasuresViewList() {
		return ifoValuationMeasuresViewList;
	}

	/**
	 * @param ifoValuationMeasuresViewList
	 *            the ifoValuationMeasuresViewList to set
	 */
	public void setIfoValuationMeasuresViewList(List ifoValuationMeasuresViewList) {
		this.ifoValuationMeasuresViewList = ifoValuationMeasuresViewList;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
