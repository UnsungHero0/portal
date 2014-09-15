package vn.com.vndirect.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.code.ssm.api.CacheKeyMethod;

/**
 * WpProduct entity. @author MyEclipse Persistence Tools
 */

public class WpProduct implements Serializable {
	
	private static final long serialVersionUID = -9094447966768479727L;
	
	private Long productId;
	private Long productGroupId;
	private String productCode;
	private String productName;
	private String productOverview;
	private Boolean isDeleted;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private String language;

	private WpProductGroup wpProductGroup; // table WP_PRODUCT_GROUP
	private List<WpSubject> wpSubjectList; // table WP_SUBJECT

	/**
	 * @return the productGroupId
	 */
	public Long getProductGroupId() {
		return productGroupId;
	}

	/**
	 * @param productGroupId
	 *            the productGroupId to set
	 */
	public void setProductGroupId(Long productGroupId) {
		this.productGroupId = productGroupId;
	}

	/**
	 * @return the wpProductGroup
	 */
	public WpProductGroup getWpProductGroup() {
		return wpProductGroup;
	}

	/**
	 * @param wpProductGroup
	 *            the wpProductGroup to set
	 */
	public void setWpProductGroup(WpProductGroup wpProductGroup) {
		this.wpProductGroup = wpProductGroup;
	}

	/**
	 * @return the wpSubjectList
	 */
	public List<WpSubject> getWpSubjectList() {
		return wpSubjectList;
	}

	/**
	 * @param wpSubjectList
	 *            the wpSubjectList to set
	 */
	public void setWpSubjectList(List<WpSubject> wpSubjectList) {
		this.wpSubjectList = wpSubjectList;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productOverview
	 */
	public String getProductOverview() {
		return productOverview;
	}

	/**
	 * @param productOverview
	 *            the productOverview to set
	 */
	public void setProductOverview(String productOverview) {
		this.productOverview = productOverview;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	@CacheKeyMethod
	public String createCacheKey(){
		return productCode.hashCode() + "@vnd_" + language;
	}

}