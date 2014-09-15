package vn.com.vndirect.domain;

@SuppressWarnings("serial")
public class IfoCompanyNameView extends BaseBean implements java.io.Serializable {
	// Fields
	private IfoCompanyNameViewId id;
	private String exchangeCode;
	private String exchangeName;
	private String sectorName;

	// Constructors

	/** default constructor */
	public IfoCompanyNameView() {
	}

	public IfoCompanyNameView(IfoCompanyNameViewId id) {
		this.id = id;
	}

	// Property accessors

	public IfoCompanyNameViewId getId() {
		return this.id;
	}

	public void setId(IfoCompanyNameViewId id) {
		this.id = id;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	/**
	 * @return the sectorName
	 */
	public String getSectorName() {
		return sectorName;
	}

	/**
	 * @param sectorName
	 *            the sectorName to set
	 */
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	/**
	 * @return the exchangeCode
	 */
	public String getExchangeCode() {
		return exchangeCode;
	}

	/**
	 * @param exchangeCode
	 *            the exchangeCode to set
	 */
	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

}
