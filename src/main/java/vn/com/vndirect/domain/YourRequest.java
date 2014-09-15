package vn.com.vndirect.domain;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import vn.com.web.commons.utility.DateUtils;

public class YourRequest extends BaseBean implements java.io.Serializable {

	private static final long serialVersionUID = 903809833674711124L;
	private String caseId;
	private String accountNo;
	private String categoryId;
	private String typeId;
	private String actionId;
	private String description;
	private String vNDSFeedback;
	private Date createdOn;
	private String strCreatedOn;
	private Date closedOn;
	private String strClosedOn;
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
	public Date getCreatedOn() {
		if (StringUtils.isNotBlank(strCreatedOn)) {
			try {
				createdOn = DateUtils.stringToDate(strCreatedOn, "yyyy-mm-dd hh24:Mi:ss");
			} catch (Exception e) {
			}
		}
		return createdOn;
	}

	/**
	 * @param createdOn
	 *            the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the strCreatedOn
	 */
	public String getStrCreatedOn() {
		return strCreatedOn;
	}

	/**
	 * @param strCreatedOn
	 *            the strCreatedOn to set
	 */
	public void setStrCreatedOn(String strCreatedOn) {
		this.strCreatedOn = strCreatedOn;
	}

	/**
	 * @return the closedOn
	 */
	public Date getClosedOn() {
		if (StringUtils.isNotBlank(strClosedOn)) {
			try {
				closedOn = DateUtils.stringToDate(strClosedOn, "yyyy-mm-dd hh24:Mi:ss");
			} catch (Exception e) {
			}
		}
		return closedOn;
	}

	/**
	 * @param closedOn
	 *            the closedOn to set
	 */
	public void setClosedOn(Date closedOn) {
		this.closedOn = closedOn;
	}

	/**
	 * @return the strClosedOn
	 */
	public String getStrClosedOn() {
		return strClosedOn;
	}

	/**
	 * @param strClosedOn
	 *            the strClosedOn to set
	 */
	public void setStrClosedOn(String strClosedOn) {
		this.strClosedOn = strClosedOn;
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
