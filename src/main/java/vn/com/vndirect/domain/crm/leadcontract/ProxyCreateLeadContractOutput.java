package vn.com.vndirect.domain.crm.leadcontract;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import vn.com.vndirect.domain.crm.Details;
import vn.com.vndirect.domain.crm.Header;

@XmlRootElement(name = "ProxyCreateLeadContractOutput")
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings("serial")
public class ProxyCreateLeadContractOutput implements Serializable {
	@XmlElement(name = "Header", required = false)
	private Header header;
	@XmlElement(name = "Status", required = false)
	private String status;
	@XmlElement(name = "Details", required = false)
	private Details details;

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

	/**
	 * @return the details
	 */
	public Details getDetails() {
		return details;
	}

	/**
	 * @param details
	 *            the details to set
	 */
	public void setDetails(Details details) {
		this.details = details;
	}
}
