package vn.com.vndirect.domain.struts2.productservice;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.WpSubject;

@SuppressWarnings("serial")
public class ProductServiceCRUDModel extends BaseModel {
	private Long subjectId;
	private String subjectCode;
	private WpSubject subject;

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

	/**
	 * @return the subject
	 */
	public WpSubject getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(WpSubject subject) {
		this.subject = subject;
	}

}
