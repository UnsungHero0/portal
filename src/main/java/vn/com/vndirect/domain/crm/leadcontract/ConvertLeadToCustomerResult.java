package vn.com.vndirect.domain.crm.leadcontract;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import vn.com.vndirect.domain.crm.ConvertLead;
import vn.com.vndirect.domain.crm.SynToBO;

@XmlRootElement(name = "results")
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings("serial")
public class ConvertLeadToCustomerResult implements Serializable {

	@XmlElement(name = "convertLead", required = false)
	private ConvertLead convertLead;

	@XmlElement(name = "syntobo", required = false)
	private SynToBO syntobo;

	@XmlElement(name = "status", required = false)
	private String status;

	public ConvertLead getConvertLead() {
		return convertLead;
	}

	public void setConvertLead(ConvertLead convertLead) {
		this.convertLead = convertLead;
	}

	public SynToBO getSyntobo() {
		return syntobo;
	}

	public void setSyntobo(SynToBO syntobo) {
		this.syntobo = syntobo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
