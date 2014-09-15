package vn.com.vndirect.service.crm;

import java.io.StringReader;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import vn.com.vndirect.business.BaseManager;
import vn.com.vndirect.commons.utility.CRMConstants;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.crmclient.leadContract.ServiceSoap_PortType;
import vn.com.vndirect.crmclient.leadContract.VndsProvince;
import vn.com.vndirect.domain.OnlineAccount;
import vn.com.vndirect.domain.crm.AddrDts;
import vn.com.vndirect.domain.crm.Header;
import vn.com.vndirect.domain.crm.leadcontract.BDts;
import vn.com.vndirect.domain.crm.leadcontract.ContractDetails;
import vn.com.vndirect.domain.crm.leadcontract.ConvertLeadToCustomerResult;
import vn.com.vndirect.domain.crm.leadcontract.IdDts;
import vn.com.vndirect.domain.crm.leadcontract.InvestmentExperience;
import vn.com.vndirect.domain.crm.leadcontract.LeadDetails;
import vn.com.vndirect.domain.crm.leadcontract.ProxyCreateLeadContractInput;
import vn.com.vndirect.domain.crm.leadcontract.ProxyCreateLeadContractOutput;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

@Component
public class CRMServiceManager extends BaseManager implements ICRMServiceManager {

	private static Logger logger = Logger.getLogger(CRMServiceManager.class);
	
	private ServiceSoap_PortType crmServiceSoap;
	
	private LeadDetails leadDetails;
	
	private IdDts idDetails;
	
	private AddrDts addressDetails;
	
	private BDts bankDetails;
	
	private InvestmentExperience investmentExperience;
	
	private ContractDetails contractDetails;
	
	@Override
	public ProxyCreateLeadContractOutput parseXmlCRMOutput(String xmlCRM) throws FunctionalException{
		final String LOCATION = "processOutputXML(outputXML:" + xmlCRM + ")";
		if (StringUtils.isBlank(xmlCRM))
			return null;
		try {
			JAXBContext context2 = JAXBContext.newInstance(ProxyCreateLeadContractOutput.class);
			Unmarshaller m2 = context2.createUnmarshaller();
			xmlCRM = xmlCRM.replaceAll("< ", "<");
			xmlCRM = xmlCRM.replaceAll("</ ", "</");
			StringBuffer xmlStr = new StringBuffer(xmlCRM);
			return (ProxyCreateLeadContractOutput) m2.unmarshal(new StreamSource(new StringReader(xmlStr.toString())));
		} catch (Exception ex) {
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	@Override
	public String getCrmXmlInputCreateLead(OnlineAccount user) throws FunctionalException {
		final String location = "getCrmXmlInputCreateLead()";
		String xmlResult = StringUtils.EMPTY;
		try{
			JAXBContext context = JAXBContext.newInstance(ProxyCreateLeadContractInput.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			ProxyCreateLeadContractInput object = new ProxyCreateLeadContractInput();
			Header header = this.getHeader();
			object.setHeader(header);
			
			idDetails = new IdDts.IdBuilder(user.getIdentityNo(), user.getCRMIDDateFormat(), user.getIdentityPlace()).build();
			
			addressDetails = new AddrDts.AddressBuilder(user.getAddress()).provinceCode(user.getProvince()).countryCode("VNM").build();

			List<BDts> bdtsList = initBankDetailsList(user);

			investmentExperience = InvestmentExperience.createDefaultInvestmentExperience();
			
			leadDetails = new LeadDetails.LeadBuilder(user.getFullName(), user.getCRMDobFormat(), user.getSex(), user.getEmail())
									.bankDetails(bdtsList)
									.addressDetails(addressDetails)
									.idDetails(idDetails)
									.investmentExperience(investmentExperience)
									.teleTradingPin(StringUtils.join(user.getMobilePassword(), StringUtils.EMPTY))
									.mobile(user.getMobilePhoneNo()).build();
			
			contractDetails = new ContractDetails.ContractBuilder(user.getMobilePhoneNo()).build();

			object.setLeadDetails(leadDetails);
			object.setContractDetails(contractDetails);
			
			StringWriter sw = new StringWriter();
			m.marshal(object, sw);
			xmlResult = VNDirectWebUtilities.lowerCase2UpperCase(sw.toString());
			return xmlResult;
			
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new FunctionalException(location, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}
	
	@Override
	public ConvertLeadToCustomerResult convertLeadToCustomerAndSynToBo(String leadId) throws FunctionalException{
		final String location = "convertLeadToCustomerAndSynToBo()";
		ConvertLeadToCustomerResult convertLeadToCustomerResult = new ConvertLeadToCustomerResult();
		try {
			boolean isActive = true;
			String xmlResult = getLeadContractServicePortType().convertLeadToCustomerAndSynToBo(leadId, isActive);
			convertLeadToCustomerResult = processXMLConvertLeadToCustomer(xmlResult);
			return convertLeadToCustomerResult;
		} catch (RemoteException rex) {
			logger.error(location, rex);
			throw new FunctionalException(location, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
					new String[] { rex.getMessage() });
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new FunctionalException(location, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}
	
	private List<BDts> initBankDetailsList(OnlineAccount onlineAccount){
		List<BDts> listResult = new ArrayList<BDts>();
		bankDetails = new BDts.BankBuilder(onlineAccount.getBankAccountName(), onlineAccount.getBankAccountNo())
										  .bankCode(onlineAccount.getBankCode()).bankName(onlineAccount.getBankName())
										  .bankLocation(onlineAccount.getBankBranchCode()).build();
		if(bankDetails.isValidBankAccount()){
			listResult.add(bankDetails);
		}
		return listResult;
	}

	@Override
	public String createLead(OnlineAccount onlineAccount) {
		try {
			crmServiceSoap = this.getLeadContractServicePortType();
			String strXML = getCrmXmlInputCreateLead(onlineAccount);
			String xmlOutput = crmServiceSoap.createLeadContract(strXML);
			ProxyCreateLeadContractOutput proxyCreateLeadContractOutput = parseXmlCRMOutput(xmlOutput);
			if(CRMConstants.RESULT_STATUS.SUCCESS.equalsIgnoreCase(proxyCreateLeadContractOutput.getStatus())){
				return proxyCreateLeadContractOutput.getDetails().getLeadIdKey();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	private ConvertLeadToCustomerResult processXMLConvertLeadToCustomer(String xml) throws FunctionalException{
		final String location = "processXMLConvertLeadToCustomer(): ";
		ConvertLeadToCustomerResult result = null;
		if (StringUtils.isBlank(xml)) {
			return null; 
		}
		
		try {
			JAXBContext context2 = JAXBContext.newInstance(ConvertLeadToCustomerResult.class);
			Unmarshaller m2 = context2.createUnmarshaller();
			xml = xml.replaceAll("< ", "<");
			xml = xml.replaceAll("</ ", "</");
			StringBuffer xmlStr = new StringBuffer(xml);
			result = (ConvertLeadToCustomerResult) m2.unmarshal(new StreamSource(new StringReader(xmlStr.toString())));
		} catch (JAXBException ex) {
			logger.error(location, ex);
			throw new FunctionalException(location, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
		
		return result;
	}

	@Override
	public boolean isDuplicateIdentification(String IdentificationNum) throws FunctionalException, SystemException {
		final String location = "isDuplicateIdentification";
		try {
			return this.getLeadContractServicePortType().isDuplicateIdentification(IdentificationNum);
		} catch (RemoteException rex) {
			logger.error(location, rex);
			throw new FunctionalException(location, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
					new String[] { rex.getMessage() });
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new FunctionalException(location, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}


	@Override
	public List<VndsProvince> getProvinceList() {
		final String location = "getProvinceList(): ";
		List<VndsProvince> result = new ArrayList<VndsProvince>();
		VndsProvince[] vndsProvinces = null;
		try {
			vndsProvinces = this.getLeadContractServicePortType().getProvinces();
		} catch (RemoteException e) {
			logger.error(location, e);
		} catch (Exception e) {
			logger.error(location, e);
		}
		if(vndsProvinces != null){
			result = Arrays.asList(vndsProvinces);
		}
		return result;
	}
	
}
