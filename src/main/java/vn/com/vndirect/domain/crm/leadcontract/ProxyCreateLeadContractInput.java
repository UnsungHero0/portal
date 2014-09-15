package vn.com.vndirect.domain.crm.leadcontract;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import vn.com.vndirect.domain.crm.Header;

@XmlRootElement
@XmlType(name = "ProxyCreateLeadContractInput", propOrder = { "header", "leadDetails", "contractDetails" })
public class ProxyCreateLeadContractInput {
	private Header header;
	private LeadDetails leadDetails;
	private ContractDetails contractDetails;

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
	 * @return the leadDetails
	 */
	public LeadDetails getLeadDetails() {
		return leadDetails;
	}

	/**
	 * @param leadDetails
	 *            the leadDetails to set
	 */
	public void setLeadDetails(LeadDetails leadDetails) {
		this.leadDetails = leadDetails;
	}

	/**
	 * @return the contractDetails
	 */
	public ContractDetails getContractDetails() {
		return contractDetails;
	}

	/**
	 * @param contractDetails
	 *            the contractDetails to set
	 */
	public void setContractDetails(ContractDetails contractDetails) {
		this.contractDetails = contractDetails;
	}
}
