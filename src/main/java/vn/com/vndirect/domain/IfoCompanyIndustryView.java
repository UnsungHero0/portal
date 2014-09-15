package vn.com.vndirect.domain;

@SuppressWarnings("serial")
public class IfoCompanyIndustryView extends BaseBean implements java.io.Serializable {
	// Fields

	private IfoCompanyIndustryViewId id;

	// Constructors

	/** default constructor */
	public IfoCompanyIndustryView() {
	}

	// Property accessors

	public IfoCompanyIndustryViewId getId() {
		return this.id;
	}

	public void setId(IfoCompanyIndustryViewId id) {
		this.id = id;
	}

}
