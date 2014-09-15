/**
 * 
 */
package vn.com.vndirect.domain;

import java.util.Date;

/**
 * @author NguyenDucQuang
 * @version 1.0
 * @created Apr 5, 2010 8:56:50 AM
 * 
 */
public class WpSubjectDocument extends BaseBean {

	private static final long serialVersionUID = -885799230455175350L;
	private Long subjectId;
	private Long documentId;
	private String createdBy;
	private Date createdDate;

	private WpSubject wpSubject; // table WP_SUBJECT
	private WpDocument wpDocument; // table WP_DOCUMENT

	/**
	 * @return the wpSubject
	 */
	public WpSubject getWpSubject() {
		return wpSubject;
	}

	/**
	 * @param wpSubject
	 *            the wpSubject to set
	 */
	public void setWpSubject(WpSubject wpSubject) {
		this.wpSubject = wpSubject;
	}

	/**
	 * @return the wpDocument
	 */
	public WpDocument getWpDocument() {
		return wpDocument;
	}

	/**
	 * @param wpDocument
	 *            the wpDocument to set
	 */
	public void setWpDocument(WpDocument wpDocument) {
		this.wpDocument = wpDocument;
	}

	/**
	 * @return the subjectId
	 */
	public Long getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId
	 *            the subjectId to set
	 */
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

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

}
