/*--------------------------------------------------------------------------*
 | Modification Logs:
 | DATE        AUTHOR      DESCRIPTION
 | ------------------------------------------------
 | Oct 9, 2007   TungNQ     First generate code
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.domain;

import vn.com.vndirect.boproxyclient.webagentservice.WebAgentAttributes;

/**
 * @author tungnq.nguyen
 * 
 */
@SuppressWarnings("serial")
public class WebAgentOnlineUser extends OnlineUser {
	private java.lang.Long webAgentUserId;
	private java.lang.String boBrankCode;
	private java.lang.String boCarebyId;
	private java.lang.String boPassword;
	private java.lang.String boReferenceId;
	private java.lang.String boType;
	private java.lang.String boUserCode;
	private java.lang.String boUserName;

	/*
	 * Inherit from OnlineUser private String customerId; private java.lang.String address; private java.lang.String createdBy; private java.util.Date createdDate; private java.util.Date dob; private
	 * java.lang.String email; private java.lang.String fullName; private java.lang.String idgId; private java.lang.String password; private java.lang.String status; private java.lang.String userName;
	 */

	/**
	 * @return the boBrankCode
	 */
	public java.lang.String getBoBrankCode() {
		return this.boBrankCode;
	}

	/**
	 * @param boBrankCode
	 *            the boBrankCode to set
	 */
	public void setBoBrankCode(java.lang.String boBrankCode) {
		this.boBrankCode = boBrankCode;
	}

	/**
	 * @return the boCarebyId
	 */
	public java.lang.String getBoCarebyId() {
		return this.boCarebyId;
	}

	/**
	 * @param boCarebyId
	 *            the boCarebyId to set
	 */
	public void setBoCarebyId(java.lang.String boCarebyId) {
		this.boCarebyId = boCarebyId;
	}

	/**
	 * @return the boPassword
	 */
	public java.lang.String getBoPassword() {
		return this.boPassword;
	}

	/**
	 * @param boPassword
	 *            the boPassword to set
	 */
	public void setBoPassword(java.lang.String boPassword) {
		this.boPassword = boPassword;
	}

	/**
	 * @return the boReferenceId
	 */
	public java.lang.String getBoReferenceId() {
		return this.boReferenceId;
	}

	/**
	 * @param boReferenceId
	 *            the boReferenceId to set
	 */
	public void setBoReferenceId(java.lang.String boReferenceId) {
		this.boReferenceId = boReferenceId;
	}

	/**
	 * @return the boType
	 */
	public java.lang.String getBoType() {
		return this.boType;
	}

	/**
	 * @param boType
	 *            the boType to set
	 */
	public void setBoType(java.lang.String boType) {
		this.boType = boType;
	}

	/**
	 * @return the boUserCode
	 */
	public java.lang.String getBoUserCode() {
		return this.boUserCode;
	}

	/**
	 * @param boUserCode
	 *            the boUserCode to set
	 */
	public void setBoUserCode(java.lang.String boUserCode) {
		this.boUserCode = boUserCode;
	}

	/**
	 * @return the boUserName
	 */
	public java.lang.String getBoUserName() {
		return this.boUserName;
	}

	/**
	 * @param boUserName
	 *            the boUserName to set
	 */
	public void setBoUserName(java.lang.String boUserName) {
		this.boUserName = boUserName;
	}

	/**
	 * @return the webAgentUserId
	 */
	public java.lang.Long getWebAgentUserId() {
		return this.webAgentUserId;
	}

	/**
	 * @param webAgentUserId
	 *            the webAgentUserId to set
	 */
	public void setWebAgentUserId(java.lang.Long webAgentUserId) {
		this.webAgentUserId = webAgentUserId;
	}

	public WebAgentAttributes getWebAgentAttributes() {
		WebAgentAttributes webAgentAttr = new WebAgentAttributes();
		webAgentAttr.setBranchCode(this.boBrankCode);
		webAgentAttr.setCareById(this.boCarebyId);
		webAgentAttr.setReferenceId(this.boReferenceId);
		webAgentAttr.setType(this.boType);
		webAgentAttr.setCustomerId(this.getCustomerId());
		return webAgentAttr;
	}
}
