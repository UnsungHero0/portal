package vn.com.vndirect.domain.embeddb;

import java.util.Date;

@SuppressWarnings("serial")
public class IndustryItemCalcView implements java.io.Serializable {

	// Fields

	private String industryCode;
	private String itemCode;
	private String textValue;
	private Double numericValue;
	private Date transDate;

	// Constructors

	/** default constructor */
	public IndustryItemCalcView() {
	}

	/** minimal constructor */
	public IndustryItemCalcView(String industryCode) {
		this.industryCode = industryCode;
	}

	/** full constructor */
	public IndustryItemCalcView(String industryCode, String itemCode, String textValue, Double numericValue) {
		this.industryCode = industryCode;
		this.itemCode = itemCode;
		this.textValue = textValue;
		this.numericValue = numericValue;
	}

	// Property accessors
	public String getIndustryCode() {
		return this.industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getTextValue() {
		return this.textValue;
	}

	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	public Double getNumericValue() {
		return this.numericValue;
	}

	public void setNumericValue(Double numericValue) {
		this.numericValue = numericValue;
	}

	/**
	 * @return the transDate
	 */
	public Date getTransDate() {
		return transDate;
	}

	/**
	 * @param transDate
	 *            the transDate to set
	 */
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

}