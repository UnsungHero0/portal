package vn.com.vndirect.domain.crm.leadcontract;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import vn.com.vndirect.domain.crm.DisableConvertLead;

@XmlRootElement(name = "results")
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings("serial")
public class DisableLeadCustomerResult implements Serializable{
	@XmlElement(name = "convertLead", required = false)
	private DisableConvertLead disableConvertLead;

	public DisableConvertLead getDisableConvertLead() {
		return disableConvertLead;
	}

	public void setConvertLead(DisableConvertLead convertLead) {
		this.disableConvertLead = disableConvertLead;
	}
}
