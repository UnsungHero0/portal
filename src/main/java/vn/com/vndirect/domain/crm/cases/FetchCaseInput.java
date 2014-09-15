package vn.com.vndirect.domain.crm.cases;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import vn.com.vndirect.domain.crm.Header;

@XmlRootElement
@XmlType(name = "FetchCaseInput", propOrder = { "header", "customerId", "caseStatus", "fromDate", "toDate" })
public class FetchCaseInput {
	private Header header;
	private String customerId;
	private String caseStatus;
	private String fromDate;
	private String toDate;

	/**
	 * @return the header
	 */
	public Header getHeader() {
		return header;
	}

	/**
	 * @param header
	 *            the header to set
	 */
	public void setHeader(Header header) {
		this.header = header;
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
	 * @return the caseStatus
	 */
	public String getCaseStatus() {
		return caseStatus;
	}

	/**
	 * @param caseStatus
	 *            the caseStatus to set
	 */
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}
