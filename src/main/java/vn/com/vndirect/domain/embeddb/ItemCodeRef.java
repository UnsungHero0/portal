package vn.com.vndirect.domain.embeddb;

@SuppressWarnings("serial")
public class ItemCodeRef implements java.io.Serializable {

	// Fields

	private String groupCode;
	private String itemCode;
	private String itemName;
	private String locale;

	// Constructors

	/** default constructor */
	public ItemCodeRef() {
	}

	/** full constructor */
	public ItemCodeRef(String groupCode, String itemCode, String itemName, String locale) {
		this.groupCode = groupCode;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.locale = locale;
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

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getLocale() {
		return this.locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ItemCodeRef))
			return false;
		ItemCodeRef castOther = (ItemCodeRef) other;

		return ((this.getGroupCode() == castOther.getGroupCode()) || (this.getGroupCode() != null && castOther.getGroupCode() != null && this.getGroupCode().equals(castOther.getGroupCode())))
				&& ((this.getItemCode() == castOther.getItemCode()) || (this.getItemCode() != null && castOther.getItemCode() != null && this.getItemCode().equals(castOther.getItemCode())))
				&& ((this.getItemName() == castOther.getItemName()) || (this.getItemName() != null && castOther.getItemName() != null && this.getItemName().equals(castOther.getItemName())))
				&& ((this.getLocale() == castOther.getLocale()) || (this.getLocale() != null && castOther.getLocale() != null && this.getLocale().equals(castOther.getLocale())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getGroupCode() == null ? 0 : this.getGroupCode().hashCode());
		result = 37 * result + (getItemCode() == null ? 0 : this.getItemCode().hashCode());
		result = 37 * result + (getItemName() == null ? 0 : this.getItemName().hashCode());
		result = 37 * result + (getLocale() == null ? 0 : this.getLocale().hashCode());
		return result;
	}

}