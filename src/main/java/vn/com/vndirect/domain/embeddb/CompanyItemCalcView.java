package vn.com.vndirect.domain.embeddb;

import java.util.Date;

@SuppressWarnings("serial")
public class CompanyItemCalcView implements java.io.Serializable {

	// Fields

	private String symbol;
	private String itemCode;
	private String textValue;
	private Double numericValue;
	private Date transDate;

	// Constructors

	/** default constructor */
	public CompanyItemCalcView() {
	}

	/** minimal constructor */
	public CompanyItemCalcView(String symbol) {
		this.symbol = symbol;
	}

	/** full constructor */
	public CompanyItemCalcView(String symbol, String itemCode, String textValue, Double numericValue) {
		this.symbol = symbol;
		this.itemCode = itemCode;
		this.textValue = textValue;
		this.numericValue = numericValue;
	}

	// Property accessors
	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
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