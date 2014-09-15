package vn.com.vndirect.domain.crm.cases;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import vn.com.vndirect.domain.crm.Header;

@XmlRootElement
@XmlType(name = "CreateCase", propOrder = { "header", "caseDetails" })
public class CreateCase {
	private Header header;
	private CaseDetails caseDetails;

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
	 * @return the caseDetails
	 */
	public CaseDetails getCaseDetails() {
		return caseDetails;
	}

	/**
	 * @param caseDetails
	 *            the caseDetails to set
	 */
	public void setCaseDetails(CaseDetails caseDetails) {
		this.caseDetails = caseDetails;
	}
}
