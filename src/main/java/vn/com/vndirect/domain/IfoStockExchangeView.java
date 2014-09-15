package vn.com.vndirect.domain;

@SuppressWarnings("serial")
public class IfoStockExchangeView extends BaseBean implements java.io.Serializable {
	// Fields
	private IfoStockExchangeViewId id;

	// Constructors
	/** default constructor */
	public IfoStockExchangeView() {
	}

	public IfoStockExchangeView(IfoStockExchangeViewId id) {
		this.id = id;
	}

	// Property accessors
	public IfoStockExchangeViewId getId() {
		return this.id;
	}

	public void setId(IfoStockExchangeViewId id) {
		this.id = id;
	}
}
