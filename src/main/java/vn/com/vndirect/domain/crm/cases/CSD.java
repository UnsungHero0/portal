package vn.com.vndirect.domain.crm.cases;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CSD {
	@XmlElement(name = "CaseId", required = false)
	private String caseId;
	@XmlElement(name = "AccountNo", required = false)
	private String accountNo;
	@XmlElement(name = "CategoryId", required = false)
	private String categoryId;
	@XmlElement(name = "TypeId", required = false)
	private String typeId;
	@XmlElement(name = "ActionId", required = false)
	private String actionId;
	@XmlElement(name = "Description", required = false)
	private String description;
	@XmlElement(name = "VNDSFeedback", required = false)
	private String vNDSFeedback;
	@XmlElement(name = "CreatedOn", required = false)
	private String createdOn;
	@XmlElement(name = "ClosedOn", required = false)
	private String closedOn;
	@XmlElement(name = "Status", required = false)
	private String status;

	/**
	 * @return the caseId
	 */
	public String getCaseId() {
		return caseId;
	}

	/**
	 * @param caseId
	 *            the caseId to set
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
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
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the typeId
	 */
	public String getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the actionId
	 */
	public String getActionId() {
		return actionId;
	}

	/**
	 * @param actionId
	 *            the actionId to set
	 */
	public void setActionId(String actionId) {
		this.actionId = actionId;
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
	 * @return the vNDSFeedback
	 */
	public String getvNDSFeedback() {
		return vNDSFeedback;
	}

	/**
	 * @param vNDSFeedback
	 *            the vNDSFeedback to set
	 */
	public void setvNDSFeedback(String vNDSFeedback) {
		this.vNDSFeedback = vNDSFeedback;
	}

	/**
	 * @return the createdOn
	 */
	public String getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn
	 *            the createdOn to set
	 */
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the closedOn
	 */
	public String getClosedOn() {
		return closedOn;
	}

	/**
	 * @param closedOn
	 *            the closedOn to set
	 */
	public void setClosedOn(String closedOn) {
		this.closedOn = closedOn;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
