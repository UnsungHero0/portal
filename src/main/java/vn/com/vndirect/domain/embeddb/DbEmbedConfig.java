package vn.com.vndirect.domain.embeddb;

import java.sql.Timestamp;

@SuppressWarnings("serial")
public class DbEmbedConfig implements java.io.Serializable {
	// Fields

	private String groupCode;
	private String itemCode;
	private Double numberValue;
	private String textValue;
	private Timestamp dateValue;

	// Constructors

	/** default constructor */
	public DbEmbedConfig() {
	}

	/** minimal constructor */
	public DbEmbedConfig(String groupCode, String itemCode) {
		this.groupCode = groupCode;
		this.itemCode = itemCode;
	}

	/** full constructor */
	public DbEmbedConfig(String groupCode, String itemCode, Double numberValue, String textValue, Timestamp dateValue) {
		this.groupCode = groupCode;
		this.itemCode = itemCode;
		this.numberValue = numberValue;
		this.textValue = textValue;
		this.dateValue = dateValue;
	}

	// Property accessors

	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Double getNumberValue() {
		return this.numberValue;
	}

	public void setNumberValue(Double numberValue) {
		this.numberValue = numberValue;
	}

	public String getTextValue() {
		return this.textValue;
	}

	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	public Timestamp getDateValue() {
		return this.dateValue;
	}

	public void setDateValue(Timestamp dateValue) {
		this.dateValue = dateValue;
	}
}
