package vn.com.vndirect.domain.crm;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import vn.com.vndirect.domain.crm.cases.CSD;

@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings("serial")
public class Details implements Serializable {
	@XmlElement(name = "CaseId", required = false)
	private String caseId;
	@XmlElement(name = "LeadId", required = false)
	private String leadId;
	@XmlElement(name = "LeadIdKey", required = false)
	private String leadIdKey;

	@XmlElement(name = "CaseDetails", required = false)
	private List<CSD> caseDetails;
	@XmlElement(name = "ErrMsg", required = false)
	private String errMsg;

	/**
	 * @return the leadId
	 */
	public String getLeadId() {
		return leadId;
	}

	public String getLeadIdKey() {
		return leadIdKey;
	}
	
	public void setLeadIdKey(String leadIdKey) {
		this.leadIdKey = leadIdKey;
	}
	/**
	 * @param leadId
	 *            the leadId to set
	 */
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	/**
	 * @return the errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * @param errMsg
	 *            the errMsg to set
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

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
	 * @return the caseDetails
	 */
	public List<CSD> getCaseDetails() {
		return caseDetails;
	}

	/**
	 * @param caseDetails
	 *            the caseDetails to set
	 */
	public void setCaseDetails(List<CSD> caseDetails) {
		this.caseDetails = caseDetails;
	}
}
