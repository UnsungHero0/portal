package vn.com.vndirect.domain.crm.cases;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "AccountClosureDetails", propOrder = { "reason" })
public class AccountClosureDetails {
	private String reason;

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
}
