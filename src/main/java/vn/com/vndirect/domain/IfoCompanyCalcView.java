/**
 * 
 */
package vn.com.vndirect.domain;

import vn.com.vndirect.commons.utility.VNDirectWebUtilities;

/**
 * @author Huy
 * 
 */
public class IfoCompanyCalcView extends IfoIndustryCalcView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8392711533936346562L;
	private String secCode;
	private Double marketCapital;
	private String exchangCode;
	private Double closePrice;
	private Double adClosePrice;
	private String companyName;
	private String companyFullName;
	private Double revenueGrowthAnnual;

	/*
	 * The price was changed in percent
	 */
	private java.lang.Double chgIndex;
	private java.lang.Double pctIndex;

	/**
	 * @return the chgIndex
	 */
	public java.lang.Double getChgIndex() {
		return chgIndex;
	}

	/**
	 * @param chgIndex
	 *            the chgIndex to set
	 */
	public void setChgIndex(java.lang.Double chgIndex) {
		this.chgIndex = chgIndex;
	}

	/**
	 * @return the pctIndex
	 */
	public java.lang.Double getPctIndex() {
		return pctIndex;
	}

	/**
	 * @param pctIndex
	 *            the pctIndex to set
	 */
	public void setPctIndex(java.lang.Double pctIndex) {
		this.pctIndex = pctIndex;
	}

	/**
	 * @return the revenueGrowthAnnual
	 */
	public Double getRevenueGrowthAnnual() {
		return revenueGrowthAnnual;
	}

	/**
	 * @param revenueGrowthAnnual
	 *            the revenueGrowthAnnual to set
	 */
	public void setRevenueGrowthAnnual(Double revenueGrowthAnnual) {
		this.revenueGrowthAnnual = revenueGrowthAnnual;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the companyFullName
	 */
	public String getCompanyFullName() {
		return companyFullName;
	}

	/**
	 * @param companyFullName
	 *            the companyFullName to set
	 */
	public void setCompanyFullName(String companyFullName) {
		this.companyFullName = companyFullName;
	}

	/**
	 * @return the secCode
	 */
	public String getSecCode() {
		return secCode;
	}

	/**
	 * @param secCode
	 *            the secCode to set
	 */
	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	/**
	 * @return the marketCapital
	 */
	public Double getMarketCapital() {
		return marketCapital;
	}

	/**
	 * @param marketCapital
	 *            the marketCapital to set
	 */
	public void setMarketCapital(Double marketCapital) {
		this.marketCapital = marketCapital;
	}

	/**
	 * @return the exchangCode
	 */
	public String getExchangCode() {
		return exchangCode;
	}

	/**
	 * @param exchangCode
	 *            the exchangCode to set
	 */
	public void setExchangCode(String exchangCode) {
		this.exchangCode = exchangCode;
	}

	/**
	 * @return the closePrice
	 */
	public Double getClosePrice() {
		return closePrice;
	}

	/**
	 * @param closePrice
	 *            the closePrice to set
	 */
	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}

	/**
	 * @return the adClosePrice
	 */
	public Double getAdClosePrice() {
		return adClosePrice;
	}

	/**
	 * @param adClosePrice
	 *            the adClosePrice to set
	 */
	public void setAdClosePrice(Double adClosePrice) {
		this.adClosePrice = adClosePrice;
	}

	public String getStrMarketCapital() {
		String strMarketCapital = "";
		strMarketCapital = VNDirectWebUtilities.getStrDoubleWithScale(this.getMarketCapital(), 0);
		return strMarketCapital == null ? "" : strMarketCapital;
	}
}
