package vn.com.vndirect.domain;

/**
 * IfoTradingStatisticsView entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class IfoTradingStatisticsView extends BaseBean implements java.io.Serializable {

	// Fields

	private IfoTradingStatisticsViewId id;

	// Constructors

	/** default constructor */
	public IfoTradingStatisticsView() {
	}

	/** full constructor */
	public IfoTradingStatisticsView(IfoTradingStatisticsViewId id) {
		this.id = id;
	}

	// Property accessors

	public IfoTradingStatisticsViewId getId() {
		return this.id;
	}

	public void setId(IfoTradingStatisticsViewId id) {
		this.id = id;
	}

}