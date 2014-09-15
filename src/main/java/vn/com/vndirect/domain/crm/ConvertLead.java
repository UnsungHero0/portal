package vn.com.vndirect.domain.crm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConvertLead", propOrder = { "leadid", "customerid", "errormessage" })
@SuppressWarnings("serial")
public class ConvertLead implements Serializable {

	@XmlElement(name = "leadid", required = false)
	private String leadid;

	@XmlElement(name = "customerid", required = false)
	private String customerid;

	@XmlElement(name = "errormessage", required = false)
	private String errormessage;

	public String getLeadid() {
		return leadid;
	}

	public void setLeadid(String leadid) {
		this.leadid = leadid;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

}
