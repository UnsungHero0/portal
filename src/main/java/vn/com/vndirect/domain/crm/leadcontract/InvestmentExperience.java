package vn.com.vndirect.domain.crm.leadcontract;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import vn.com.vndirect.commons.utility.CRMConstants;

@XmlType(name = "InvestmentExperience", propOrder = { "stock", "bond", "treasuryCredit", "shortSell", "otherSecurities", "none", "gold", "currency", "estate" })
@SuppressWarnings("serial")
public class InvestmentExperience implements Serializable {

	private String stock;
	private String bond;
	private String treasuryCredit;
	private String shortSell;
	private String otherSecurities;
	private String none;
	private String gold;
	private String currency;
	private String estate;

	public static InvestmentExperience createDefaultInvestmentExperience() {
		InvestmentExperience investmentExperience = new InvestmentExperience();
		investmentExperience.setBond(CRMConstants.YES_NO.NO);
		investmentExperience.setCurrency(CRMConstants.YES_NO.NO);
		investmentExperience.setEstate(CRMConstants.YES_NO.NO);
		investmentExperience.setGold(CRMConstants.YES_NO.NO);
		investmentExperience.setNone(CRMConstants.YES_NO.NO);
		investmentExperience.setOtherSecurities(CRMConstants.YES_NO.NO);
		investmentExperience.setShortSell(CRMConstants.YES_NO.NO);
		investmentExperience.setStock(CRMConstants.YES_NO.NO);
		investmentExperience.setTreasuryCredit(CRMConstants.YES_NO.NO);
		return investmentExperience;
	}
	
	
	
	/**
	 * @return the stock
	 */
	public String getStock() {
		return stock;
	}

	/**
	 * @param stock
	 *            the stock to set
	 */
	public void setStock(String stock) {
		this.stock = stock;
	}

	/**
	 * @return the bond
	 */
	public String getBond() {
		return bond;
	}

	/**
	 * @param bond
	 *            the bond to set
	 */
	public void setBond(String bond) {
		this.bond = bond;
	}

	/**
	 * @return the treasuryCredit
	 */
	public String getTreasuryCredit() {
		return treasuryCredit;
	}

	/**
	 * @param treasuryCredit
	 *            the treasuryCredit to set
	 */
	public void setTreasuryCredit(String treasuryCredit) {
		this.treasuryCredit = treasuryCredit;
	}

	/**
	 * @return the shortSell
	 */
	public String getShortSell() {
		return shortSell;
	}

	/**
	 * @param shortSell
	 *            the shortSell to set
	 */
	public void setShortSell(String shortSell) {
		this.shortSell = shortSell;
	}

	/**
	 * @return the otherSecurities
	 */
	public String getOtherSecurities() {
		return otherSecurities;
	}

	/**
	 * @param otherSecurities
	 *            the otherSecurities to set
	 */
	public void setOtherSecurities(String otherSecurities) {
		this.otherSecurities = otherSecurities;
	}

	/**
	 * @return the none
	 */
	public String getNone() {
		return none;
	}

	/**
	 * @param none
	 *            the none to set
	 */
	public void setNone(String none) {
		this.none = none;
	}

	/**
	 * @return the gold
	 */
	public String getGold() {
		return gold;
	}

	/**
	 * @param gold
	 *            the gold to set
	 */
	public void setGold(String gold) {
		this.gold = gold;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the estate
	 */
	public String getEstate() {
		return estate;
	}

	/**
	 * @param estate
	 *            the estate to set
	 */
	public void setEstate(String estate) {
		this.estate = estate;
	}

}
