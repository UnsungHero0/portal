/**
 * 
 */
package vn.com.vndirect.domain.struts2.analysistools;

import java.util.ArrayList;
import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.WpSubject;

/**
 * @author NguyenDucQuang
 * @version 1.0
 * @created Apr 12, 2010 11:29:56 AM
 * 
 */
@SuppressWarnings("serial")
public class ExpertsModel extends BaseModel {

	private String productCode;
	private Long subjectId;
	private String subjectCode;
	private WpSubject subject;
	private List<WpSubject> subjectList = new ArrayList<WpSubject>();

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode
	 *            the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
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

	/**
	 * @return the subjectList
	 */
	public List<WpSubject> getSubjectList() {
		return subjectList;
	}

	/**
	 * @param subjectList
	 *            the subjectList to set
	 */
	public void setSubjectList(List<WpSubject> subjectList) {
		this.subjectList = subjectList;
	}

}
