package vn.com.vndirect.domain;

import java.util.Date;

/**
 * WpProductGroup entity. @author MyEclipse Persistence Tools
 */

public class WpProductGroup extends BaseBean {

	private static final long serialVersionUID = -4493836208234629384L;
	private Long productGroupId;
	private Long productGroupType;
	private String productGroupCode;
	private String productGroupName;
	private String createdBy;
	private Date createdDate;

	private WpProduct wpProduct; // table WP_PRODUCT

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

	public Long getProductGroupId() {
		return this.productGroupId;
	}

	public void setProductGroupId(Long productGroupId) {
		this.productGroupId = productGroupId;
	}

	/**
	 * @return the productGroupType
	 */
	public Long getProductGroupType() {
		return productGroupType;
	}

	/**
	 * @param productGroupType
	 *            the productGroupType to set
	 */
	public void setProductGroupType(Long productGroupType) {
		this.productGroupType = productGroupType;
	}

	public String getProductGroupCode() {
		return this.productGroupCode;
	}

	public void setProductGroupCode(String productGroupCode) {
		this.productGroupCode = productGroupCode;
	}

	public String getProductGroupName() {
		return this.productGroupName;
	}

	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
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

}