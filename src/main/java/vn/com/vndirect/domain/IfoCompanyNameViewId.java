package vn.com.vndirect.domain;

import java.util.Date;

public class IfoCompanyNameViewId extends BaseBean implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8139272814410770410L;
	private Long companyId;
	private String symbol;
	private String companyName;
	private String companyFullName;
	private String locale;
	private Date firstTradingDate;

	private String abbName;

	// Constructors

	/** default constructor */
	public IfoCompanyNameViewId() {
	}

	/** minimal constructor */
	public IfoCompanyNameViewId(Long companyId) {
		this.companyId = companyId;
	}

	/** full constructor */
	public IfoCompanyNameViewId(Long companyId, String symbol, String companyName, String companyFullName) {
		this.companyId = companyId;
		this.symbol = symbol;
		this.companyName = companyName;
		this.companyFullName = companyFullName;
	}

	// Property accessors

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyFullName() {
		return this.companyFullName;
	}

	public void setCompanyFullName(String companyFullName) {
		this.companyFullName = companyFullName;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IfoCompanyNameViewId))
			return false;
		IfoCompanyNameViewId castOther = (IfoCompanyNameViewId) other;

		return ((this.getCompanyId() == castOther.getCompanyId()) || (this.getCompanyId() != null && castOther.getCompanyId() != null && this.getCompanyId().equals(castOther.getCompanyId())))
				&& ((this.getSymbol() == castOther.getSymbol()) || (this.getSymbol() != null && castOther.getSymbol() != null && this.getSymbol().equals(castOther.getSymbol())))
				&& ((this.getCompanyName() == castOther.getCompanyName()) || (this.getCompanyName() != null && castOther.getCompanyName() != null && this.getCompanyName().equals(
						castOther.getCompanyName())))
				&& ((this.getCompanyFullName() == castOther.getCompanyFullName()) || (this.getCompanyFullName() != null && castOther.getCompanyFullName() != null && this.getCompanyFullName().equals(
						castOther.getCompanyFullName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCompanyId() == null ? 0 : this.getCompanyId().hashCode());
		result = 37 * result + (getSymbol() == null ? 0 : this.getSymbol().hashCode());
		result = 37 * result + (getCompanyName() == null ? 0 : this.getCompanyName().hashCode());
		result = 37 * result + (getCompanyFullName() == null ? 0 : this.getCompanyFullName().hashCode());
		return result;
	}

	/**
	 * @return the abbName
	 */
	public String getAbbName() {
		return abbName;
	}

	/**
	 * @param abbName
	 *            the abbName to set
	 */
	public void setAbbName(String abbName) {
		this.abbName = abbName;
	}

	/**
	 * @return the firstTradingDate
	 */
	public Date getFirstTradingDate() {
		return firstTradingDate;
	}

	/**
	 * @param firstTradingDate
	 *            the firstTradingDate to set
	 */
	public void setFirstTradingDate(Date firstTradingDate) {
		this.firstTradingDate = firstTradingDate;
	}

}