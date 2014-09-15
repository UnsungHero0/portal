package vn.com.vndirect.domain.crm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SynToBO", propOrder = { "custodynumber", "accountnumber", "bosyn", "syncErrorMessage" })
@SuppressWarnings("serial")
public class SynToBO implements Serializable {

	@XmlElement(name = "custodynumber", required = false)
	private String custodynumber;

	@XmlElement(name = "accountnumber", required = false)
	private String accountnumber;

	@XmlElement(name = "bosyn", required = false)
	private String bosyn;

	@XmlElement(name = "syncErrorMessage", required = false)
	private String syncErrorMessage;

	public String getCustodynumber() {
		return custodynumber;
	}

	public void setCustodynumber(String custodynumber) {
		this.custodynumber = custodynumber;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getBosyn() {
		return bosyn;
	}

	public void setBosyn(String bosyn) {
		this.bosyn = bosyn;
	}

	public String getSyncErrorMessage() {
		return syncErrorMessage;
	}

	public void setSyncErrorMessage(String syncErrorMessage) {
		this.syncErrorMessage = syncErrorMessage;
	}

}
