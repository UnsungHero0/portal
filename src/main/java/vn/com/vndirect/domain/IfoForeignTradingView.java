package vn.com.vndirect.domain;

/**
 * IfoForeignTradingView entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class IfoForeignTradingView extends BaseBean implements java.io.Serializable {

	// Fields

	private IfoForeignTradingViewId id;

	// Constructors

	/** default constructor */
	public IfoForeignTradingView() {
	}

	/** full constructor */
	public IfoForeignTradingView(IfoForeignTradingViewId id) {
		this.id = id;
	}

	// Property accessors

	public IfoForeignTradingViewId getId() {
		return this.id;
	}

	public void setId(IfoForeignTradingViewId id) {
		this.id = id;
	}

}