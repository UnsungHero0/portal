package vn.com.vndirect.domain;

import java.util.Date;

import com.google.code.ssm.api.CacheKeyMethod;

/**
 * WpSubject entity. @author MyEclipse Persistence Tools
 */
public class WpSubject extends BaseBean {

	private static final long serialVersionUID = 6769787525230768829L;
	private Long subjectId;
	private Long productId;
	private String subjectCode;
	private String subjectName;
	private String subjectContent;
	private String subjectOverview;
	private Boolean isDeleted;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private String language;
	private Boolean isPageStatic;

	private WpProduct wpProduct; // table WP_PRODUCT

	/**
	 * @return the subjectOverview
	 */
	public String getSubjectOverview() {
		return subjectOverview;
	}

	/**
	 * @param subjectOverview
	 *            the subjectOverview to set
	 */
	public void setSubjectOverview(String subjectOverview) {
		this.subjectOverview = subjectOverview;
	}

	/**
	 * @return the wpProduct
	 */
	public WpProduct getWpProduct() {
		return wpProduct;
	}

	/**
	 * @param wpProduct
	 *            the wpProduct to set
	 */
	public void setWpProduct(WpProduct wpProduct) {
		this.wpProduct = wpProduct;
	}

	public Long getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the subjectCode
	 */
	public String getSubjectCode() {
		return subjectCode;
	}

	/**
	 * @param subjectCode
	 *            the subjectCode to set
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectContent() {
		return this.subjectContent;
	}

	public void setSubjectContent(String subjectContent) {
		this.subjectContent = subjectContent;
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

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsPageStatic() {
		return isPageStatic;
	}

	public void setIsPageStatic(Boolean isPageStatic) {
		this.isPageStatic = isPageStatic;
	}

	@CacheKeyMethod
	public String createCacheKey() {
		return subjectCode.hashCode() + "@vnd_" + language;
	}

}