package vn.com.vndirect.service.crm;

import java.util.List;

import vn.com.vndirect.crmclient.leadContract.VndsProvince;
import vn.com.vndirect.domain.OnlineAccount;
import vn.com.vndirect.domain.crm.leadcontract.ConvertLeadToCustomerResult;
import vn.com.vndirect.domain.crm.leadcontract.ProxyCreateLeadContractOutput;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

public interface ICRMServiceManager {
	
	ProxyCreateLeadContractOutput parseXmlCRMOutput(String xmlCRM) throws FunctionalException;

	String getCrmXmlInputCreateLead(OnlineAccount user) throws FunctionalException;

	String createLead(OnlineAccount onlineAccount);
	
	ConvertLeadToCustomerResult convertLeadToCustomerAndSynToBo(String leadId) throws FunctionalException;
	
	boolean isDuplicateIdentification(String IdentificationNum) throws FunctionalException, SystemException;
	
	List<VndsProvince> getProvinceList();
	
}