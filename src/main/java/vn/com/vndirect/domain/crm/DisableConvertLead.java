package vn.com.vndirect.domain.crm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DisableConvertLead", propOrder = { "leadid", "errormessage", "status" })
@SuppressWarnings("serial")
public class DisableConvertLead implements Serializable {

	@XmlElement(name = "leadid", required = false)
	private String leadid;

	@XmlElement(name = "errormessage", required = false)
	private String errormessage;
	
	@XmlElement(name = "status", required = false)
	private String status;

	public String getLeadid() {
		return leadid;
	}

	public void setLeadid(String leadid) {
		this.leadid = leadid;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
