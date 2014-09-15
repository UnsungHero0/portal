/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Aug 31, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.business;

import java.io.StringReader;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.rpc.ServiceException;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import vn.com.vndirect.commons.utility.CRMConstants;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.DBConstants;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.commons.vndsservice.VNDSServiceUtils;
import vn.com.vndirect.domain.OnlineAccount;
import vn.com.vndirect.domain.OnlineUser;
import vn.com.vndirect.domain.crm.AddrDts;
import vn.com.vndirect.domain.crm.Header;
import vn.com.vndirect.domain.crm.leadcontract.BDts;
import vn.com.vndirect.domain.crm.leadcontract.ContractDetails;
import vn.com.vndirect.domain.crm.leadcontract.ConvertLeadToCustomerResult;
import vn.com.vndirect.domain.crm.leadcontract.DisableLeadCustomerResult;
import vn.com.vndirect.domain.crm.leadcontract.IdDts;
import vn.com.vndirect.domain.crm.leadcontract.InvestmentExperience;
import vn.com.vndirect.domain.crm.leadcontract.LeadDetails;
import vn.com.vndirect.domain.crm.leadcontract.ProxyCreateLeadContractInput;
import vn.com.vndirect.domain.crm.leadcontract.ProxyCreateLeadContractOutput;
import vn.com.vndirect.wsclient.onlineuser.CheckExistedUserName;
import vn.com.vndirect.wsclient.onlineuser.CheckExistedUserNameResult;
import vn.com.vndirect.wsclient.onlineuser.DeniedRegisterOnlineResult;
import vn.com.vndirect.wsclient.onlineuser.OnlineTradingUser;
import vn.com.vndirect.wsclient.onlineuser.RegisterOnlineUserResult;
import vn.com.vndirect.wsclient.openaccount.CreateOnlinePendingAccountMapRequest;
import vn.com.vndirect.wsclient.openaccount.CreateOnlinePendingAccountMapResult;
import vn.com.vndirect.wsclient.openaccount.GetPendingAccount;
import vn.com.vndirect.wsclient.openaccount.GetPendingAccountMapResult;
import vn.com.vndirect.wsclient.openaccount.OnlineAccountAttribute;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.DateUtils;

/**
 * @author tungnq
 * 
 */
public class AccountManager extends BaseManager implements IAccountManager {
	private static Logger logger = Logger.getLogger(AccountManager.class);

	// +++ =============== /Define Spring Mapping ===============+++++
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAccountManager#createFreeRegistedUsers(vn.com
	 * .vndirect.domain.OnlineUser)
	 */
	public boolean createFreeRegistedUsers(OnlineUser onlineUser) throws FunctionalException, SystemException {
		final String LOCATION = "createFreeRegistedUsers(onlineUser::" + onlineUser + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (onlineUser == null) {
			throw new SystemException(LOCATION, "onlineUser object is NULL");
		}
		try {
			vn.com.vndirect.wsclient.onlineuser.OnlineUser onlineUserClient = onlineUser.getOnlineUser();
			RegisterOnlineUserResult registerOnlineUserResult = this.getIOnlineUserServicePortType().registerFreeOnlineUser(
					this.getVndsAuthenticationHeader(), onlineUserClient);
			VNDSServiceUtils.processMessageStatus(registerOnlineUserResult == null ? null : registerOnlineUserResult
					.getMsgStatus());
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return true;
		} catch (RemoteException rex) {
			logger.error(LOCATION, rex);
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
					new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			logger.error(LOCATION, srvex);
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
					new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
			logger.error(LOCATION, fex);
			throw fex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAccountManager#createOnlineAccount(java.util
	 * .List)
	 */
	public ProxyCreateLeadContractOutput createOnlineAccount(List<OnlineAccountAttribute> params) throws FunctionalException,
			SystemException {
		final String LOCATION = "createOnlineAccount()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (params == null) {
			throw new SystemException(LOCATION, "account attributes are NULL");
		}
		
		try {

			String inputXML = generateXML(params);
			logger.info(LOCATION + " || " + inputXML);
			String outputXML = this.getLeadContractServicePortType().createLeadContract(inputXML);
			logger.info(LOCATION + " || " + outputXML);
			ProxyCreateLeadContractOutput output = processOutputXML(outputXML);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: output: " + output);
			if (CRMConstants.RESULT_STATUS.SUCCESS.equalsIgnoreCase(output.getStatus()) 
					&& output.getDetails() != null && StringUtils.isNotEmpty(output.getDetails().getLeadId())) {
				OnlineAccountAttribute param = new OnlineAccountAttribute();
				param.setKey(DBConstants.ONLINE_ACCOUNT.LEAD_ID);
				param.setValue(output.getDetails().getLeadId());
				params.add(param);
				this.createOnlineAccountToDB(params);
			}

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return output;
		} catch (RemoteException rex) {
			logger.error(LOCATION, rex);
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
					new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			logger.error(LOCATION, srvex);
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
					new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
			logger.error(LOCATION, fex);
			throw fex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/**
	 * @param outputXML
	 * @return
	 * @throws FunctionalException
	 */
	private ProxyCreateLeadContractOutput processOutputXML(String outputXML) throws FunctionalException {
		final String LOCATION = "processOutputXML(outputXML:" + outputXML + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		ProxyCreateLeadContractOutput result = null;
		if (StringUtils.isBlank(outputXML))
			return null;
		try {
			JAXBContext context2 = JAXBContext.newInstance(ProxyCreateLeadContractOutput.class);
			Unmarshaller m2 = context2.createUnmarshaller();
			outputXML = outputXML.replaceAll("< ", "<");
			outputXML = outputXML.replaceAll("</ ", "</");
			StringBuffer xmlStr = new StringBuffer(outputXML);
			result = (ProxyCreateLeadContractOutput) m2.unmarshal(new StreamSource(new StringReader(xmlStr.toString())));

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
		return result;
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
	
	private DisableLeadCustomerResult processXMLDisableLeadCustomer(String xml) throws FunctionalException{
		final String location = "processXMLDisableLeadCustomer(): ";
		DisableLeadCustomerResult result = null;
		if (StringUtils.isBlank(xml)) {
			return null; 
		}
		
		try {
			JAXBContext context2 = JAXBContext.newInstance(DisableLeadCustomerResult.class);
			Unmarshaller m2 = context2.createUnmarshaller();
			xml = xml.replaceAll("< ", "<");
			xml = xml.replaceAll("</ ", "</");
			StringBuffer xmlStr = new StringBuffer(xml);
			result = (DisableLeadCustomerResult) m2.unmarshal(new StreamSource(new StringReader(xmlStr.toString())));
		} catch (JAXBException ex) {
			logger.error(location, ex);
			throw new FunctionalException(location, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
		
		return result;
	}
	/**
	 * @param params
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	private String generateXML(List<OnlineAccountAttribute> params) throws FunctionalException, SystemException {
		final String LOCATION = "generateXML(List<OnlineAccountAttribute>:" + params + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			String strXML = "";
			String str = "";
			Date date = null;
			if (params != null && params.size() > 0) {
				Map<String, String> convertedMap = new HashMap<String, String>();
				OnlineAccountAttribute attributeObject = null;
				for (Iterator<OnlineAccountAttribute> iterator = params.iterator(); iterator.hasNext();) {
					attributeObject = (OnlineAccountAttribute) iterator.next();
					convertedMap.put(attributeObject.getKey(), attributeObject.getValue());
				}

				// TODO Auto-generated method stub
				JAXBContext context = JAXBContext.newInstance(ProxyCreateLeadContractInput.class);

				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

				ProxyCreateLeadContractInput object = new ProxyCreateLeadContractInput();
				Header header = this.getHeader();
				object.setHeader(header);

				LeadDetails leadDetails = new LeadDetails();

				leadDetails.setLeadSource(CRMConstants.LEAD_SOURCE.Internet);
				leadDetails.setIsVietInvestor(CRMConstants.YES_NO.YES);
				logger.debug(LOCATION + ":: FULLNAME: " + convertedMap.get(DBConstants.ONLINE_ACCOUNT.FULLNAME));
				leadDetails.setFullName(convertedMap.get(DBConstants.ONLINE_ACCOUNT.FULLNAME));

				IdDts idDetails = new IdDts();
				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.ID_TYPE);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				if (CRMConstants.ID_TYPE.PASSPORT.equalsIgnoreCase(str))
					idDetails.setIdTypeCode("002");
				else if (CRMConstants.ID_TYPE.OTHER_CERTIFICATE.equalsIgnoreCase(str))
					idDetails.setIdTypeCode("004");
				else if (CRMConstants.ID_TYPE.BUSINESS_REGISTRATION.equalsIgnoreCase(str))
					idDetails.setIdTypeCode("005");
				else
					idDetails.setIdTypeCode("001");

				idDetails.setIdCode(convertedMap.get(DBConstants.ONLINE_ACCOUNT.ID_NUMBER));
				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.ID_ISSUE_DATE);
				date = DateUtils.stringToDateITA(str);
				str = DateUtils.dateToString(date, DateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				idDetails.setDateOfIssue(str);
				idDetails.setExpiryDate("");
				idDetails.setIssuingPlace(convertedMap.get(DBConstants.ONLINE_ACCOUNT.ID_ISSUE));
				leadDetails.setIdDetails(idDetails);

				AddrDts addressDetails = new AddrDts();
				addressDetails.setAddrType(CRMConstants.ADDR_TYPE.CurrentResidential);
				addressDetails.setIsCommunicationAddress(CRMConstants.YES_NO.YES);
				addressDetails.setAddress(convertedMap.get(DBConstants.ONLINE_ACCOUNT.STREET_ADDRESS));
				addressDetails.setProvinceCode("");
				addressDetails.setPostCode("");
				addressDetails.setCountryCode("");
				addressDetails.setLandMark("");
				addressDetails.setAddrDesc("");
				addressDetails.setYearsofStay("");
				leadDetails.setAddressDetails(addressDetails);

				List<BDts> bdtsList = new ArrayList<BDts>();
				BDts bankDetails = new BDts();

				boolean hasBank = true;
				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.BANK_NAME);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				if (str.length() < 0)
					hasBank = false;
				bankDetails.setBankName(str);
				
				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.BANK_CODE);
				if(StringUtils.isEmpty(str)) {
					hasBank = false;
				}
				bankDetails.setBankCode(str);
				
				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.BANK_BRANCH_CODE);
				if(StringUtils.isEmpty(str)) {
					hasBank = false;
				}
				bankDetails.setBankLocation(str);
				
				//Set banks account number
				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.BANK_NUMBER);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				if (str.length() < 0)
					hasBank = false;
				bankDetails.setbAccntNumber(str);

				//Set bank account Name
				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.BANK_MANAGER);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				if (str.length() < 0)
					hasBank = false;
				bankDetails.setbAccntName(str);
				
				bankDetails.setbAccntManagedBy("");
				bankDetails.setbAccntDesc("");
				bankDetails.setLicenceNo("");
				bankDetails.setLicenceDate("");
				bankDetails.setLicenceIssuer("");
				bankDetails.setOperationNo("");
				bankDetails.setOperationDate("");
				if (hasBank)
					bdtsList.add(bankDetails);

//				bankDetails = new BDts();
//				hasBank = false;
//				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.BANK_NAME2);
//				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
//				if (str.length() > 0)
//					hasBank = true;
//				bankDetails.setBankName(str);
//				bankDetails.setBankLocation(str);
//				bankDetails.setBankCode("");
//				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.BANK_MANAGER2);
//				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
//				if (str.length() > 0)
//					hasBank = true;
//				bankDetails.setbAccntName(str);
//				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.BANK_NUMBER2);
//				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
//				if (str.length() > 0)
//					hasBank = true;
//				bankDetails.setbAccntNumber(str);
//				bankDetails.setbAccntManagedBy("");
//				bankDetails.setbAccntDesc("");
//				bankDetails.setLicenceNo("");
//				bankDetails.setLicenceDate("");
//				bankDetails.setLicenceIssuer("");
//				bankDetails.setOperationNo("");
//				bankDetails.setOperationDate("");
//				if (hasBank)
//					bdtsList.add(bankDetails);

				leadDetails.setBankDetails(bdtsList);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.DATE_OF_BIRTH);
				date = DateUtils.stringToDateITA(str);
				str = DateUtils.dateToString(date, DateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				leadDetails.setdOB(str);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.SEX);
				if ("MALE".equalsIgnoreCase(str))
					str = CRMConstants.SEX.MALE;
				else if ("FEMALE".equalsIgnoreCase(str))
					str = CRMConstants.SEX.FEMALE;
				leadDetails.setSex(str);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.MARITAL_STATUS);
//				if ("SINGLE".equalsIgnoreCase(str))
//					str = CRMConstants.MARITAL_STATUS.SINGLE;
//				else if ("MARRIED".equalsIgnoreCase(str))
//					str = CRMConstants.MARITAL_STATUS.MARRIED;
//				else
//					str = CRMConstants.MARITAL_STATUS.DIVORSED;
				//default single
				leadDetails.setMaritalStatus(CRMConstants.MARITAL_STATUS.SINGLE);

				leadDetails.setEducationLevel("");
				leadDetails.setCompanyName("");
				leadDetails.setCompanyAddress("");
				leadDetails.setDesignation("");

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.SPOUSE_NAME);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				leadDetails.setSpouseName(str);
				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.SPOUSE_COMPANY);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				leadDetails.setSpouseCompany(str);
				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.SPOUSE_POSITION);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				leadDetails.setSpouseDesignation(str);

				leadDetails.setEmail(convertedMap.get(DBConstants.ONLINE_ACCOUNT.EMAIL));
				leadDetails.setTelephone(convertedMap.get(DBConstants.ONLINE_ACCOUNT.HOME_PHONE));

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.MOBILE_PHONE);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				leadDetails.setMobile(str);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.FAX);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				leadDetails.setFax(str);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.TAX_NUMBER);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				leadDetails.setTaxNumber(str);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.NATIONALITY);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				leadDetails.setNationality(str);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.INVESTMENT_KNOWLEDGE);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				if (str.contains("1"))
					leadDetails.setInvestmentKnowledge(CRMConstants.INVESTMENT_KNOWLEDGE.Good);
				else if (str.contains("3"))
					leadDetails.setInvestmentKnowledge(CRMConstants.INVESTMENT_KNOWLEDGE.Limited);
				else if (str.contains("2"))
					leadDetails.setInvestmentKnowledge(CRMConstants.INVESTMENT_KNOWLEDGE.None);
				else
					leadDetails.setInvestmentKnowledge(CRMConstants.INVESTMENT_KNOWLEDGE.Expert);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.INVESTMENT_EXPERIENCE);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				InvestmentExperience investmentExperience = new InvestmentExperience();
				if (str.contains("1"))
					investmentExperience.setStock(CRMConstants.YES_NO.YES);
				else
					investmentExperience.setStock(CRMConstants.YES_NO.NO);
				investmentExperience.setBond(CRMConstants.YES_NO.NO);
				investmentExperience.setTreasuryCredit(CRMConstants.YES_NO.NO);
				investmentExperience.setShortSell(CRMConstants.YES_NO.NO);
				investmentExperience.setOtherSecurities(CRMConstants.YES_NO.NO);
				investmentExperience.setNone(CRMConstants.YES_NO.NO);
				if (str.contains("2"))
					investmentExperience.setGold(CRMConstants.YES_NO.YES);
				else
					investmentExperience.setGold(CRMConstants.YES_NO.NO);
				if (str.contains("3"))
					investmentExperience.setCurrency(CRMConstants.YES_NO.YES);
				else
					investmentExperience.setCurrency(CRMConstants.YES_NO.NO);
				if (str.contains("4"))
					investmentExperience.setEstate(CRMConstants.YES_NO.YES);
				else
					investmentExperience.setEstate(CRMConstants.YES_NO.NO);
				leadDetails.setInvestmentExperience(investmentExperience);

				leadDetails.setRevenuePercent("");
				leadDetails.setLongTermGrowthPercent("");
				leadDetails.setNormalTermGrowthPercent("");
				leadDetails.setShortTermGrowthPercent("");
				leadDetails.setRiskLowPercent("");
				leadDetails.setRiskNormalPercent("");
				leadDetails.setRiskhighPercent("");
				leadDetails.setShortTermAsset("");
				leadDetails.setFixAsset("");
				leadDetails.setNetAsset("");
				leadDetails.setMonthlyIncome("");
				leadDetails.setTeleTradingPin(convertedMap.get(DBConstants.ONLINE_ACCOUNT.MOBILEPASSWORD));
				leadDetails.setIsSelectStaff(CRMConstants.YES_NO.NO);

				object.setLeadDetails(leadDetails);

				// ContractDetails
				ContractDetails contractDetails = new ContractDetails();

				//Set default la DIRECT_ACCOUNT
//				contractDetails.setProductCode(CRMConstants.SERVICE_PACK.DIRECT_ACCOUNT);
				contractDetails.setProductCode("");

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.TRANSACTION_TYPE);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				if (str.contains("1"))
					contractDetails.setoTCAccountType(CRMConstants.ACCOUNT_TYPE.AccForListed);
				else
					contractDetails.setoTCAccountType(CRMConstants.ACCOUNT_TYPE.AccForOTC);

				contractDetails.setIsFloorTrader(CRMConstants.YES_NO.YES);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.IS_REMOTE_TRADING);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				if (str.equals("1"))
					contractDetails.setIsOnlineTrader(CRMConstants.YES_NO.YES);
				else
					contractDetails.setIsOnlineTrader(CRMConstants.YES_NO.NO);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.IS_TEL_TRADING);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				if (str.equals("1")) {
					contractDetails.setIsPhoneTrader(CRMConstants.YES_NO.YES);
					contractDetails.setTradingPhoneNo(convertedMap.get(DBConstants.ONLINE_ACCOUNT.MOBILE_PHONE));
					contractDetails.setTradingMobileNo(convertedMap.get(DBConstants.ONLINE_ACCOUNT.HOME_PHONE));
				} else {
					contractDetails.setIsPhoneTrader(CRMConstants.YES_NO.NO);
					contractDetails.setTradingPhoneNo("00000");
				}

				contractDetails.setIsDelegateAccount(CRMConstants.YES_NO.NO);
				contractDetails.setDelegatorCustomerId("");

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.CORRESPONDENCE_ACCOUNT_NUMBER);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				contractDetails.setCorresAccountNumber(str);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.CORRESPONDENCE_ACCOUNT_NAME);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				contractDetails.setPayeeBenifRelation(str);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.IS_OTHER_COMPANY_ACCOUNT);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				if (str.equals("1"))
					contractDetails.setIsOtherCompAccount(CRMConstants.YES_NO.YES);
				else
					contractDetails.setIsOtherCompAccount(CRMConstants.YES_NO.NO);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.OTHER_COMPANY_ACCOUNT_NAME);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				contractDetails.setOtherCompAccountName(str);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.ENTER_MARKET_YEAR);
				str = str == null ? "2000-01-01" : str.trim() + "-01-01";
				contractDetails.setEnteringYrInMarket(str);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.INVESTMENT_CAPITAL);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				if (str.contains("1"))
					contractDetails.setInvestmentCapital(CRMConstants.INVESTMENT_CAPITAL.Till_100M);
				else if (str.contains("2"))
					contractDetails.setInvestmentCapital(CRMConstants.INVESTMENT_CAPITAL.Till_500M);
				else if (str.contains("3"))
					contractDetails.setInvestmentCapital(CRMConstants.INVESTMENT_CAPITAL.Till_1B);
				else if (str.contains("4"))
					contractDetails.setInvestmentCapital(CRMConstants.INVESTMENT_CAPITAL.Over_1B);
				else
					contractDetails.setInvestmentCapital(CRMConstants.INVESTMENT_CAPITAL.Till_100M);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.INVESTMENT_AIM);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				String investmentAim = "";
				if (str.contains("1"))
					investmentAim += CRMConstants.INVESTMENT_AIM.Valued_Ticker + ",";
				if (str.contains("2"))
					investmentAim += CRMConstants.INVESTMENT_AIM.Increased_Value_Shares + ",";
				if (str.contains("3"))
					investmentAim += CRMConstants.INVESTMENT_AIM.Short_Term + ",";
				if (str.contains("4"))
					investmentAim += CRMConstants.INVESTMENT_AIM.Fixed_Interest + ",";
				if (investmentAim.length() > 0) {
					investmentAim = investmentAim.substring(0, investmentAim.length() - 1);
				} else
					investmentAim += CRMConstants.INVESTMENT_AIM.Valued_Ticker;
				contractDetails.setInvestmentAim(investmentAim);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.PUBLIC_MANAGE_COMPANY);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				contractDetails.setManagementPositioningPublicCompany(str);
				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.PUBLIC_COMPANY_HOLDING);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				contractDetails.setFivePercentShareholdingPublicCompany(str);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.STATEMENT_CYCLE);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				if (str.contains("1"))
					contractDetails.setStatementCycle(CRMConstants.STATEMENT_CYCLE.Monthly);
				else
					contractDetails.setStatementCycle(CRMConstants.STATEMENT_CYCLE.BiWeekly);

				str = convertedMap.get(DBConstants.ONLINE_ACCOUNT.STATEMENT_METHOD);
				str = StringUtils.isEmpty(str) ? "" : StringUtils.trim(str);
				if (str.contains("1"))
					contractDetails.setReceiveVia(CRMConstants.RECEIVE_VIA.Floor);
				else
					contractDetails.setReceiveVia(CRMConstants.RECEIVE_VIA.Email);

				contractDetails.setOrderResultReceiveBy(CRMConstants.ORDER_RESULT_RECEIVE_BY.SMS);
				contractDetails.setNotifReceiveBy(CRMConstants.NOTIFY_RECEIVE_BY.SMS);

				object.setContractDetails(contractDetails);

				StringWriter sw = new StringWriter();
				m.marshal(object, sw);

				strXML = VNDirectWebUtilities.lowerCase2UpperCase(sw.toString());

				if (logger.isDebugEnabled())
					logger.debug(LOCATION + ":: inputXML = " + strXML);
			}
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return strXML;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAccountManager#createOnlineAccount(java.util
	 * .List)
	 */
	private void createOnlineAccountToDB(List<OnlineAccountAttribute> params) throws FunctionalException, SystemException {
		final String LOCATION = "createOnlineAccountToDB(List<OnlineAccountAttribute>:" + params + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (params == null) {
			throw new SystemException(LOCATION, "onlineAccount object is NULL");
		}
		try {

			CreateOnlinePendingAccountMapRequest request = new CreateOnlinePendingAccountMapRequest();
			request.setOnlinePendingAccountList(params.toArray(new OnlineAccountAttribute[] {}));
			CreateOnlinePendingAccountMapResult result = this.getIOpenAccountServicePortType().createPendingAccount(
					getVndsAuthenticationHeader(), request);
			VNDSServiceUtils.processMessageStatus(result == null ? null : result.getMsgStatus());

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
		} catch (RemoteException rex) {
			logger.error(LOCATION, rex);
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
					new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			logger.error(LOCATION, srvex);
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
					new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
			logger.error(LOCATION, fex);
			throw fex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAccountManager#retrievePendingAccount(vn.com
	 * .vndirect.domain.OnlineAccount)
	 */
	public Map<String, String> retrievePendingAccount(OnlineAccount onlineAccount) throws FunctionalException, SystemException {
		final String LOCATION = "retrievePendingAccount(OnlineAccount:" + onlineAccount + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (onlineAccount == null) {
			throw new SystemException(LOCATION, "onlineAccount object is NULL");
		}
		try {
			GetPendingAccount pendingAccount = new GetPendingAccount();
			pendingAccount.setOnlineAccountId(onlineAccount.getOnlineAccountId());
			pendingAccount.setReportKey(onlineAccount.getReportKey());
			GetPendingAccountMapResult pendingAccountMapResult = this.getIOpenAccountServicePortType().retrievePendingAccount(
					this.getVndsAuthenticationHeader(), pendingAccount);
			VNDSServiceUtils
					.processMessageStatus(pendingAccountMapResult == null ? null : pendingAccountMapResult.getMsgStatus());

			OnlineAccountAttribute[] OnlineAccountAttributeArray = pendingAccountMapResult.getOnlineAccountList();
			Map<String, String> mapResult = new HashMap<String, String>();
			if (OnlineAccountAttributeArray != null && OnlineAccountAttributeArray.length > 0) {
				for (int i = 0; i < OnlineAccountAttributeArray.length; i++) {
					mapResult.put(OnlineAccountAttributeArray[i].getKey(), OnlineAccountAttributeArray[i].getValue());
				}
			}

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return mapResult;
		} catch (RemoteException rex) {
			logger.error(LOCATION, rex);
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
					new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			logger.error(LOCATION, srvex);
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
					new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
			logger.error(LOCATION, fex);
			throw fex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	@Override
	public CheckExistedUserNameResult checkExistedUsername(CheckExistedUserName checkExistedUserName) throws FunctionalException,
			SystemException {
		final String location = "checkExistedUsername()";
		if(checkExistedUserName == null){
			throw new SystemException(location, "checkExistedUserName object is NULL");
		}
		
		try {
			CheckExistedUserNameResult result = this.getIOnlineUserServicePortType().checkExistedUserName(this.getVndsAuthenticationHeader(), checkExistedUserName);
			return result;
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
	public ConvertLeadToCustomerResult activateFreeOnlineUser(String leadId) throws FunctionalException, SystemException {
		final String location = "activateFreeOnlineUser() : ";
		boolean isActive = true;
		ConvertLeadToCustomerResult convertLeadToCustomerResult = new ConvertLeadToCustomerResult();
		//active free online user
		try {
			String xmlResult = this.getLeadContractServicePortType().convertLeadToCustomerAndSynToBo(leadId, isActive);
			convertLeadToCustomerResult = this.processXMLConvertLeadToCustomer(xmlResult);
			if(convertLeadToCustomerResult != null && 
				CRMConstants.RESULT_STATUS.SUCCESS_CONVERT_LEAD.equalsIgnoreCase(convertLeadToCustomerResult.getStatus())) {
				OnlineTradingUser onlineUser = new OnlineTradingUser();
				onlineUser.setCustomerId(convertLeadToCustomerResult.getConvertLead().getCustomerid());
				onlineUser.setCustodyCode(convertLeadToCustomerResult.getSyntobo().getCustodynumber());
				onlineUser.setLeadId(leadId);
				
//				TradingAccountResult tradingAccountResult = this.getIOnlineUserServicePortType().updateFreeToTradingAccount(
//															this.getVndsAuthenticationHeader(), onlineUser);
//				VNDSServiceUtils.processMessageStatus(tradingAccountResult == null ? null : tradingAccountResult.getMessageStatus());
			} 
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

	
	@Override
	public RegisterOnlineUserResult getUserByActiveKey(String activeKey) throws FunctionalException, SystemException {
		final String location = "getUserByActiveKey()";
		RegisterOnlineUserResult result = new RegisterOnlineUserResult();
		try {
			 result = (RegisterOnlineUserResult) this.getIOnlineUserServicePortType().getOnlineUserByActiveKey(this.getVndsAuthenticationHeader(), activeKey);
			 return result;
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
	public DisableLeadCustomerResult deniedActivatingOnlineUser(vn.com.vndirect.wsclient.onlineuser.OnlineUser onlineUser) throws FunctionalException, SystemException {
		final String location = "deniedActivatingOnlineUser() : ";
		DisableLeadCustomerResult result = new DisableLeadCustomerResult();
		boolean isActive = false;
		try {
			String xmlResult = this.getLeadContractServicePortType().convertLeadToCustomerAndSynToBo(onlineUser.getLeadId(), isActive);
			result = this.processXMLDisableLeadCustomer(xmlResult);
			if(result != null && 
					CRMConstants.RESULT_STATUS.SUCCESS_CONVERT_LEAD.equalsIgnoreCase(result.getDisableConvertLead().getStatus())) {
				String onlineUserId = String.valueOf(onlineUser.getOnlineUserId());
				DeniedRegisterOnlineResult deniedRegisterOnlineReult = this.getIOnlineUserServicePortType().deniedRegisterOnline(this.getVndsAuthenticationHeader(), onlineUserId);
				VNDSServiceUtils.processMessageStatus(deniedRegisterOnlineReult == null ? null : deniedRegisterOnlineReult.getMsgStatus());
			}
			
			return result;
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
	public void createFreeOnlineAccount(List<OnlineAccountAttribute> params) throws FunctionalException, SystemException {
		final String LOCATION = "createFreeOnlineAccount(List<OnlineAccountAttribute>:" + params + ")";
		if (params == null) {
			throw new SystemException(LOCATION, "onlineAccount object is NULL");
		}
		try {
			CreateOnlinePendingAccountMapRequest request = new CreateOnlinePendingAccountMapRequest();
			request.setOnlinePendingAccountList(params.toArray(new OnlineAccountAttribute[] {}));
			CreateOnlinePendingAccountMapResult result = this.getIOpenAccountServicePortType().createPendingAccount(
					getVndsAuthenticationHeader(), request);
			VNDSServiceUtils.processMessageStatus(result == null ? null : result.getMsgStatus());

		} catch (RemoteException rex) {
			logger.error(LOCATION, rex);
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
					new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			logger.error(LOCATION, srvex);
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
					new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
			logger.error(LOCATION, fex);
			throw fex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}
}
