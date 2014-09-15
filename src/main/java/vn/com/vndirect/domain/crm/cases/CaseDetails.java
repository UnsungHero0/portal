package vn.com.vndirect.domain.crm.cases;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "CaseDetails", propOrder = { "title", "customerId", "accountNo", "sRCategoryId", "sRTypeId", "sRActionsId", "description", "pinDetails", "productDetails", "accountClosureDetails" })
public class CaseDetails {
	private String title;
	private String customerId;
	private String accountNo;
	private String sRCategoryId;
	private String sRTypeId;
	private String sRActionsId;
	private String description;
	private PinDetails pinDetails;
	private ProductDetails productDetails;
	private AccountClosureDetails accountClosureDetails;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo
	 *            the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * @return the sRCategoryId
	 */
	public String getsRCategoryId() {
		return sRCategoryId;
	}

	/**
	 * @param sRCategoryId
	 *            the sRCategoryId to set
	 */
	public void setsRCategoryId(String sRCategoryId) {
		this.sRCategoryId = sRCategoryId;
	}

	/**
	 * @return the sRTypeId
	 */
	public String getsRTypeId() {
		return sRTypeId;
	}

	/**
	 * @param sRTypeId
	 *            the sRTypeId to set
	 */
	public void setsRTypeId(String sRTypeId) {
		this.sRTypeId = sRTypeId;
	}

	/**
	 * @return the sRActionsId
	 */
	public String getsRActionsId() {
		return sRActionsId;
	}

	/**
	 * @param sRActionsId
	 *            the sRActionsId to set
	 */
	public void setsRActionsId(String sRActionsId) {
		this.sRActionsId = sRActionsId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the pinDetails
	 */
	public PinDetails getPinDetails() {
		return pinDetails;
	}

	/**
	 * @param pinDetails
	 *            the pinDetails to set
	 */
	public void setPinDetails(PinDetails pinDetails) {
		this.pinDetails = pinDetails;
	}

	/**
	 * @return the productDetails
	 */
	public ProductDetails getProductDetails() {
		return productDetails;
	}

	/**
	 * @param productDetails
	 *            the productDetails to set
	 */
	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	/**
	 * @return the accountClosureDetails
	 */
	public AccountClosureDetails getAccountClosureDetails() {
		return accountClosureDetails;
	}

	/**
	 * @param accountClosureDetails
	 *            the accountClosureDetails to set
	 */
	public void setAccountClosureDetails(AccountClosureDetails accountClosureDetails) {
		this.accountClosureDetails = accountClosureDetails;
	}
}
