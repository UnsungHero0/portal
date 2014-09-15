package vn.com.vndirect.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings( { "unchecked", "serial" })
public class IfoAccessType extends BaseBean implements java.io.Serializable {
	// Fields
	private Long accessType;
	private String accessDesc;
	private Date createdDate;
	private String createdBy;
	private Date modifiedDate;
	private String modifiedBy;
	private Set ifoDocuments = new HashSet(0);
	private Set ifoNewses = new HashSet(0);

	// Constructors

	/** default constructor */
	public IfoAccessType() {
	}

	/** full constructor */
	public IfoAccessType(String accessDesc, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy, Set ifoDocuments, Set ifoNewses) {
		this.accessDesc = accessDesc;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		this.ifoDocuments = ifoDocuments;
		this.ifoNewses = ifoNewses;
	}

	// Property accessors

	public Long getAccessType() {
		return this.accessType;
	}

	public void setAccessType(Long accessType) {
		this.accessType = accessType;
	}

	public String getAccessDesc() {
		return this.accessDesc;
	}

	public void setAccessDesc(String accessDesc) {
		this.accessDesc = accessDesc;
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

	public Set getIfoDocuments() {
		return this.ifoDocuments;
	}

	public void setIfoDocuments(Set ifoDocuments) {
		this.ifoDocuments = ifoDocuments;
	}

	public Set getIfoNewses() {
		return this.ifoNewses;
	}

	public void setIfoNewses(Set ifoNewses) {
		this.ifoNewses = ifoNewses;
	}

}
