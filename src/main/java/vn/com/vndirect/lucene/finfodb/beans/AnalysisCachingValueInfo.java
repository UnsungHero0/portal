package vn.com.vndirect.lucene.finfodb.beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AnalysisCachingValueInfo implements Serializable {
	private String itemCode;
	private Double numericValue;
	private String textValue;
	private String defaultItemName;

	public AnalysisCachingValueInfo() {
	}

	public AnalysisCachingValueInfo(String itemCode, Double numberValue) {
		this.itemCode = itemCode;
		this.numericValue = numberValue;
		if (numberValue != null) {
			this.textValue = numberValue.toString();
		}
	}

	public AnalysisCachingValueInfo(String itemCode, Double numberValue, String textValue) {
		this.itemCode = itemCode;
		this.numericValue = numberValue;
		this.textValue = textValue;
	}

	/**
	 * @return the itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * @param itemCode
	 *            the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * @return the numberValue
	 */
	public Double getNumericValue() {
		return numericValue;
	}

	/**
	 * @param numberValue
	 *            the numberValue to set
	 */
	public void setNumericValue(Double numberValue) {
		this.numericValue = numberValue;
	}

	/**
	 * @return the textValue
	 */
	public String getTextValue() {
		return textValue;
	}

	/**
	 * @param textValue
	 *            the textValue to set
	 */
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasNumericValue() {
		return (numericValue != null);
	}

	/**
	 * @return the defaultItemName
	 */
	public String getDefaultItemName() {
		return this.defaultItemName;
	}

	/**
	 * @param defaultItemName
	 *            the defaultItemName to set
	 */
	public void setDefaultItemName(String defaultItemName) {
		this.defaultItemName = defaultItemName;
	}

	/**
	 * 
	 */
	public String toString() {
		return "AnalysisValueBean-[itemCode:" + itemCode + ", numberValue:" + numericValue + ", textValue:" + textValue + ", defaultItemName:" + defaultItemName + "]";
	}

}
