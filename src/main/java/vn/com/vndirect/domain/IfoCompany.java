package vn.com.vndirect.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * IfoCompany generated by MyEclipse - Hibernate Tools
 */
@SuppressWarnings( { "unchecked", "serial" })
public class IfoCompany extends BaseBean implements java.io.Serializable {

	// Fields

	private Long companyId;
	private String companyName;
	private Date registrationDate;
	private String companyStatus;
	private Date closedDate;
	private Date createdDate;
	private String createdBy;
	private Date modifiedDate;
	private String modifiedBy;
	private Long forumStatus;
	private String locale;

	private Set ifoCompanyNewses = new HashSet(0);
	private Set ifoCompanyDocuments = new HashSet(0);
	private Set cmsMessageBoards = new HashSet(0);

	// Constructors

	/** default constructor */
	public IfoCompany() {
	}

	/** minimal constructor */
	public IfoCompany(Long companyId, String companyName, Date registrationDate) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.registrationDate = registrationDate;
	}

	/** full constructor */
	public IfoCompany(Long companyId, String companyName, Date registrationDate, String companyStatus, Date closedDate, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy,
			String symbol, Long forumStatus, Set ifoCompanyNewses, Set ifoCompanyDocuments, Set cmsMessageBoards) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.registrationDate = registrationDate;
		this.companyStatus = companyStatus;
		this.closedDate = closedDate;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		this.forumStatus = forumStatus;
		this.ifoCompanyNewses = ifoCompanyNewses;
		this.ifoCompanyDocuments = ifoCompanyDocuments;
		this.cmsMessageBoards = cmsMessageBoards;
	}

	// Property accessors

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getCompanyStatus() {
		return this.companyStatus;
	}

	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}

	public Date getClosedDate() {
		return this.closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getForumStatus() {
		return this.forumStatus;
	}

	public void setForumStatus(Long forumStatus) {
		this.forumStatus = forumStatus;
	}

	public Set getIfoCompanyNewses() {
		return this.ifoCompanyNewses;
	}

	public void setIfoCompanyNewses(Set ifoCompanyNewses) {
		this.ifoCompanyNewses = ifoCompanyNewses;
	}

	public Set getIfoCompanyDocuments() {
		return this.ifoCompanyDocuments;
	}

	public void setIfoCompanyDocuments(Set ifoCompanyDocuments) {
		this.ifoCompanyDocuments = ifoCompanyDocuments;
	}

	public Set getCmsMessageBoards() {
		return this.cmsMessageBoards;
	}

	public void setCmsMessageBoards(Set cmsMessageBoards) {
		this.cmsMessageBoards = cmsMessageBoards;
	}

	public String toString() {

		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("companyId", this.companyId).append("companyName", this.companyName).append("companyStatus",
				this.companyStatus).append("forumStatus", this.forumStatus);
		return sb.toString();

	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

}