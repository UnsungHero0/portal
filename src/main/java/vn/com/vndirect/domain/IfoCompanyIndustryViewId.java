package vn.com.vndirect.domain;

@SuppressWarnings("serial")
public class IfoCompanyIndustryViewId extends BaseBean implements java.io.Serializable {
	// Fields
	/*
	 * Group Sector -> Sector -> Group Industry -> Industry.
	 */

	private String industryGroupName;
	private String sectorGroupName;

	private Long companyId;
	private String industryCode;
	private String industryName;
	private String sectorCode;
	private String sectorName;
	private String locale;

	private String industryGroupCode;
	private String sectorGroupCode;

	// Constructors

	/** default constructor */
	public IfoCompanyIndustryViewId() {
	}

	/** full constructor */
	public IfoCompanyIndustryViewId(Long companyId, String industryCode, String inductryName, String sectorCode, String sectorName, String locale) {
		this.companyId = companyId;
		this.industryCode = industryCode;
		this.industryName = inductryName;
		this.sectorCode = sectorCode;
		this.sectorName = sectorName;
		this.locale = locale;
	}

	// Property accessors

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getIndustryCode() {
		return (this.industryCode == null ? "" : this.industryCode);
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getIndustryName() {
		return this.industryName;
	}

	public void setIndustryName(String inductryName) {
		this.industryName = inductryName;
	}

	public String getSectorCode() {
		return (this.sectorCode == null ? "" : this.sectorCode);
	}

	public void setSectorCode(String sectorCode) {
		this.sectorCode = sectorCode;
	}

	public String getSectorName() {
		return this.sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getLocale() {
		return this.locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getIndustryGroupName() {
		return industryGroupName;
	}

	public void setIndustryGroupName(String industryGroupName) {
		this.industryGroupName = industryGroupName;
	}

	public String getSectorGroupName() {
		return sectorGroupName;
	}

	public void setSectorGroupName(String sectorGroupName) {
		this.sectorGroupName = sectorGroupName;
	}

	/**
	 * @return the industryGroupCode
	 */
	public String getIndustryGroupCode() {
		return (industryGroupCode == null ? "" : industryGroupCode);
	}

	/**
	 * @param industryGroupCode
	 *            the industryGroupCode to set
	 */
	public void setIndustryGroupCode(String industryGroupCode) {
		this.industryGroupCode = industryGroupCode;
	}

	/**
	 * @return the sectorGroupCode
	 */
	public String getSectorGroupCode() {
		return (sectorGroupCode == null ? "" : sectorGroupCode);
	}

	/**
	 * @param sectorGroupCode
	 *            the sectorGroupCode to set
	 */
	public void setSectorGroupCode(String sectorGroupCode) {
		this.sectorGroupCode = sectorGroupCode;
	}
}
