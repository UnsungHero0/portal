package vn.com.vndirect.domain;

@SuppressWarnings("serial")
public class IfoStockExchangeViewId extends BaseBean implements java.io.Serializable {
	// Fields

	private String symbol;
	private String exchangeCode;
	private String exchangeName;

	// Constructors

	/** default constructor */
	public IfoStockExchangeViewId() {
	}

	/** full constructor */
	public IfoStockExchangeViewId(String symbol, String exchangeCode, String exchangeName) {
		this.symbol = symbol;
		this.exchangeCode = exchangeCode;
		this.exchangeName = exchangeName;
	}

	// Property accessors

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getExchangeCode() {
		return this.exchangeCode;
	}

	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

	public String getExchangeName() {
		return this.exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IfoStockExchangeViewId))
			return false;
		IfoStockExchangeViewId castOther = (IfoStockExchangeViewId) other;

		return ((this.getSymbol() == castOther.getSymbol()) || (this.getSymbol() != null && castOther.getSymbol() != null && this.getSymbol().equals(castOther.getSymbol())))
				&& ((this.getExchangeCode() == castOther.getExchangeCode()) || (this.getExchangeCode() != null && castOther.getExchangeCode() != null && this.getExchangeCode().equals(
						castOther.getExchangeCode())))
				&& ((this.getExchangeName() == castOther.getExchangeName()) || (this.getExchangeName() != null && castOther.getExchangeName() != null && this.getExchangeName().equals(
						castOther.getExchangeName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getSymbol() == null ? 0 : this.getSymbol().hashCode());
		result = 37 * result + (getExchangeCode() == null ? 0 : this.getExchangeCode().hashCode());
		result = 37 * result + (getExchangeName() == null ? 0 : this.getExchangeName().hashCode());
		return result;
	}

}