package vn.com.vndirect.domain;

import java.util.Date;

import com.google.code.ssm.api.CacheKeyMethod;

/**
 * WpDocument entity. @author MyEclipse Persistence Tools
 */

public class WpDocument {
	// extends BaseBean {

	private static final long serialVersionUID = -3085306834942141767L;
	private Long documentId;
	private String documentName;
	private String documentTitle;
	private String language;
	private String documentUri;
	private String documentThumbnailUri;
	private String documentType;
	private Long productId;
	private Boolean isDeleted;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;

	private WpProduct wpProduct;

	/**
	 * @return the documentId
	 */
	public Long getDocumentId() {
		return documentId;
	}

	/**
	 * @param documentId
	 *            the documentId to set
	 */
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	/**
	 * @return the documentName
	 */
	public String getDocumentName() {
		return documentName;
	}

	/**
	 * @param documentName
	 *            the documentName to set
	 */
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	/**
	 * @return the documentTitle
	 */
	public String getDocumentTitle() {
		return documentTitle;
	}

	/**
	 * @param documentTitle
	 *            the documentTitle to set
	 */
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the documentUri
	 */
	public String getDocumentUri() {
		return documentUri;
	}

	/**
	 * @param documentUri
	 *            the documentUri to set
	 */
	public void setDocumentUri(String documentUri) {
		this.documentUri = documentUri;
	}

	/**
	 * @return the documentThumbnailUri
	 */
	public String getDocumentThumbnailUri() {
		return documentThumbnailUri;
	}

	/**
	 * @param documentThumbnailUri
	 *            the documentThumbnailUri to set
	 */
	public void setDocumentThumbnailUri(String documentThumbnailUri) {
		this.documentThumbnailUri = documentThumbnailUri;
	}

	/**
	 * @return the documentType
	 */
	public String getDocumentType() {
		return documentType;
	}

	/**
	 * @param documentType
	 *            the documentType to set
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the isDeleted
	 */
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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

	@CacheKeyMethod
	public String createCacheKey() {
		if (this.getWpProduct() != null) {
			return this.getWpProduct().hashCode() + "@" + language;
		} else {
			return "FormApp".hashCode() + "@" + language;
		}
	}

}