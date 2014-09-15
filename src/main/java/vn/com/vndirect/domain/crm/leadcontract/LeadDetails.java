package vn.com.vndirect.domain.crm.leadcontract;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;

import vn.com.vndirect.commons.utility.CRMConstants;
import vn.com.vndirect.domain.OnlineAccount;
import vn.com.vndirect.domain.crm.AddrDts;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.DateUtils;

@XmlType(name = "LeadDetails", propOrder = { "leadSource", "isVietInvestor", "fullName", "idDetails", "addressDetails", "bankDetails", "dOB", "sex", "maritalStatus", "educationLevel", "companyName",
		"companyAddress", "designation", "spouseName", "spouseCompany", "spouseDesignation", "email", "telephone", "mobile", "fax", "taxNumber", "nationality", "investmentKnowledge",
		"investmentExperience", "revenuePercent", "longTermGrowthPercent", "normalTermGrowthPercent", "shortTermGrowthPercent", "riskLowPercent", "riskNormalPercent", "riskhighPercent",
		"shortTermAsset", "fixAsset", "netAsset", "monthlyIncome", "teleTradingPin", "isSelectStaff" })
@SuppressWarnings("serial")
public class LeadDetails implements Serializable {
	private String leadSource;
	private String isVietInvestor;
	private String fullName;
	private IdDts idDetails;
	private AddrDts addressDetails;
	private List<BDts> bankDetails;
	private String dOB;
	private String sex;
	private String maritalStatus;
	private String educationLevel;
	private String companyName;
	private String companyAddress;
	private String designation;
	private String spouseName;
	private String spouseCompany;
	private String spouseDesignation;
	private String email;
	private String telephone;
	private String mobile;
	private String fax;
	private String taxNumber;
	private String nationality;
	private String investmentKnowledge;
	private InvestmentExperience investmentExperience;
	private String revenuePercent;
	private String longTermGrowthPercent;
	private String normalTermGrowthPercent;
	private String shortTermGrowthPercent;
	private String riskLowPercent;
	private String riskNormalPercent;
	private String riskhighPercent;
	private String shortTermAsset;
	private String fixAsset;
	private String netAsset;
	private String monthlyIncome;
	private String teleTradingPin;
	private String isSelectStaff;
	
	public LeadDetails(){
		super();
	}
	
	public static class LeadBuilder{
		private String leadSource = CRMConstants.LEAD_SOURCE.Internet;
		private String isVietInvestor = CRMConstants.YES_NO.YES;
		private String isSelectStaff = CRMConstants.YES_NO.NO;
		private String maritalStatus = CRMConstants.MARITAL_STATUS.SINGLE;
		private String investmentKnowledge = CRMConstants.INVESTMENT_KNOWLEDGE.Expert;
		private String monthlyIncome = StringUtils.EMPTY;
		private String fullName;
		private IdDts idDetails;
		private AddrDts addressDetails;
		private List<BDts> bankDetails;
		private String dOB;
		private String sex;
		private String telephone;
		private InvestmentExperience investmentExperience;
		private String teleTradingPin;
		private String mobile;

		private String educationLevel = StringUtils.EMPTY;
		private String companyName = StringUtils.EMPTY;
		private String companyAddress = StringUtils.EMPTY;
		private String designation = StringUtils.EMPTY;
		private String spouseName = StringUtils.EMPTY;
		private String spouseCompany = StringUtils.EMPTY;
		private String spouseDesignation = StringUtils.EMPTY;
		private String email = StringUtils.EMPTY;
		private String fax = StringUtils.EMPTY;
		private String taxNumber = StringUtils.EMPTY;
		private String nationality = "VNM";
		private String revenuePercent = StringUtils.EMPTY;
		private String longTermGrowthPercent = StringUtils.EMPTY;
		private String normalTermGrowthPercent = StringUtils.EMPTY;
		private String shortTermGrowthPercent = StringUtils.EMPTY;
		private String riskLowPercent = StringUtils.EMPTY;
		private String riskNormalPercent = StringUtils.EMPTY;
		private String riskhighPercent = StringUtils.EMPTY;
		private String shortTermAsset = StringUtils.EMPTY;
		private String fixAsset = StringUtils.EMPTY;
		private String netAsset = StringUtils.EMPTY;
		
		public LeadBuilder(String fullName, String dOB, String sex, String email){
			this.fullName = fullName;
			this.dOB = dOB;
			this.sex = sex;
			this.email = email;
		}
		
		public LeadBuilder telephone(String telephone) {
			this.telephone = telephone;
			return this;
		}
		public LeadBuilder teleTradingPin(String teleTradingPin) {
			this.teleTradingPin = teleTradingPin;
			return this;
		}
		public LeadBuilder leadSource(String leadSource) {
			this.leadSource = leadSource;
			return this;
		}
		public LeadBuilder isVietInvestor(String isVietInvestor) {
			this.isVietInvestor = isVietInvestor;
			return this;
		}
		public LeadBuilder idDetails(IdDts idDetails) {
			this.idDetails = idDetails;
			return this;
		}
		public LeadBuilder addressDetails(AddrDts addressDetails) {
			this.addressDetails = addressDetails;
			return this;
		}
		public LeadBuilder bankDetails(List<BDts> bankDetails) {
			this.bankDetails = bankDetails;
			return this;
		}
		public LeadBuilder maritalStatus(String maritalStatus) {
			this.maritalStatus = maritalStatus;
			return this;
		}
		public LeadBuilder educationLevel(String educationLevel) {
			this.educationLevel = educationLevel;
			return this;
		}
		public LeadBuilder companyName(String companyName) {
			this.companyName = companyName;
			return this;
		}
		public LeadBuilder companyAddress(String companyAddress) {
			this.companyAddress = companyAddress;
			return this;
		}
		public LeadBuilder designation(String designation) {
			this.designation = designation;
			return this;
		}
		public LeadBuilder mobile(String mobile) {
			this.mobile = mobile;
			return this;
		}
		public LeadBuilder fax(String fax) {
			this.fax = fax;
			return this;
		}
		public LeadBuilder taxNumber(String taxNumber) {
			this.taxNumber = taxNumber;
			return this;
		}
		public LeadBuilder nationality(String nationality) {
			this.nationality = nationality;
			return this;
		}
		public LeadBuilder investmentKnowledge(String investmentKnowledge) {
			this.investmentKnowledge = investmentKnowledge;
			return this;
		}
		public LeadBuilder investmentExperience(InvestmentExperience investmentExperience) {
			this.investmentExperience = investmentExperience;
			return this;
		}
		public LeadBuilder revenuePercent(String revenuePercent) {
			this.revenuePercent = revenuePercent;
			return this;
		}
		public LeadBuilder longTermGrowthPercent(String longTermGrowthPercent) {
			this.longTermGrowthPercent = longTermGrowthPercent;
			return this;
		}
		public LeadBuilder normalTermGrowthPercent(String normalTermGrowthPercent) {
			this.normalTermGrowthPercent = normalTermGrowthPercent;
			return this;
		}
		public LeadBuilder shortTermGrowthPercent(String shortTermGrowthPercent) {
			this.shortTermGrowthPercent = shortTermGrowthPercent;
			return this;
		}
		public LeadBuilder riskLowPercent(String riskLowPercent) {
			this.riskLowPercent = riskLowPercent;
			return this;
		}
		public LeadBuilder riskNormalPercent(String riskNormalPercent) {
			this.riskNormalPercent = riskNormalPercent;
			return this;
		}
		public LeadBuilder riskhighPercent(String riskhighPercent) {
			this.riskhighPercent = riskhighPercent;
			return this;
		}
		public LeadBuilder shortTermAsset(String shortTermAsset) {
			this.shortTermAsset = shortTermAsset;
			return this;
		}
		public LeadBuilder fixAsset(String fixAsset) {
			this.fixAsset = fixAsset;
			return this;
		}
		public LeadBuilder monthlyIncome(String monthlyIncome) {
			this.monthlyIncome = monthlyIncome;
			return this;
		}
		public LeadBuilder isSelectStaff(String isSelectStaff) {
			this.isSelectStaff = isSelectStaff;
			return this;
		}
		
		public LeadDetails build(){
			return new LeadDetails(this);
		}
	}
	
	public LeadDetails(LeadBuilder builder){
		this.leadSource = builder.leadSource;
		this.isVietInvestor = builder.isVietInvestor;
		this.fullName = builder.fullName;
		this.idDetails = builder.idDetails;
		this.addressDetails = builder.addressDetails;
		this.bankDetails = builder.bankDetails;
		this.dOB = builder.dOB;
		this.sex = builder.sex;
		this.maritalStatus = builder.maritalStatus;
		this.educationLevel = builder.educationLevel;
		this.companyName = builder.companyName;
		this.companyAddress = builder.companyAddress;
		this.designation = builder.designation;
		this.spouseName = builder.spouseName;
		this.spouseCompany = builder.spouseCompany;
		this.spouseDesignation = builder.spouseDesignation;
		this.email = builder.email;
		this.telephone = builder.telephone;
		this.mobile = builder.mobile;
		this.fax = builder.fax;
		this.taxNumber = builder.taxNumber;
		this.nationality = builder.nationality;
		this.investmentKnowledge = builder.investmentKnowledge;
		this.investmentExperience = builder.investmentExperience;
		this.revenuePercent = builder.revenuePercent;
		this.longTermGrowthPercent = builder.longTermGrowthPercent;
		this.normalTermGrowthPercent = builder.normalTermGrowthPercent;
		this.shortTermGrowthPercent = builder.shortTermGrowthPercent;
		this.riskLowPercent = builder.riskLowPercent;
		this.riskNormalPercent = builder.riskNormalPercent;
		this.riskhighPercent = builder.riskhighPercent;
		this.shortTermAsset = builder.shortTermAsset;
		this.fixAsset = builder.fixAsset;
		this.netAsset = builder.netAsset;
		this.monthlyIncome = builder.monthlyIncome;
		this.teleTradingPin = builder.teleTradingPin;
		this.isSelectStaff = builder.isSelectStaff;
	}
	
	
	/**
	 * @return the leadSource
	 */
	public String getLeadSource() {
		return leadSource;
	}

	/**
	 * @param leadSource
	 *            the leadSource to set
	 */
	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}

	/**
	 * @return the isVietInvestor
	 */
	public String getIsVietInvestor() {
		return isVietInvestor;
	}

	/**
	 * @param isVietInvestor
	 *            the isVietInvestor to set
	 */
	public void setIsVietInvestor(String isVietInvestor) {
		this.isVietInvestor = isVietInvestor;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the idDetails
	 */
	public IdDts getIdDetails() {
		return idDetails;
	}

	/**
	 * @param idDetails
	 *            the idDetails to set
	 */
	public void setIdDetails(IdDts idDetails) {
		this.idDetails = idDetails;
	}

	/**
	 * @return the addressDetails
	 */
	public AddrDts getAddressDetails() {
		return addressDetails;
	}

	/**
	 * @param sddressDetails
	 *            the sddressDetails to set
	 */
	public void setAddressDetails(AddrDts addressDetails) {
		this.addressDetails = addressDetails;
	}

	/**
	 * @return the bankDetails
	 */
	public List<BDts> getBankDetails() {
		return bankDetails;
	}

	/**
	 * @param bankDetails
	 *            the bankDetails to set
	 */
	public void setBankDetails(List<BDts> bankDetails) {
		this.bankDetails = bankDetails;
	}

	/**
	 * @return the dOB
	 */
	public String getdOB() {
		return dOB;
	}

	/**
	 * @param dOB
	 *            the dOB to set
	 */
	public void setdOB(String dOB) {
		this.dOB = dOB;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus
	 *            the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the educationLevel
	 */
	public String getEducationLevel() {
		return educationLevel;
	}

	/**
	 * @param educationLevel
	 *            the educationLevel to set
	 */
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the companyAddress
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}

	/**
	 * @param companyAddress
	 *            the companyAddress to set
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the spouseName
	 */
	public String getSpouseName() {
		return spouseName;
	}

	/**
	 * @param spouseName
	 *            the spouseName to set
	 */
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	/**
	 * @return the spouseCompany
	 */
	public String getSpouseCompany() {
		return spouseCompany;
	}

	/**
	 * @param spouseCompany
	 *            the spouseCompany to set
	 */
	public void setSpouseCompany(String spouseCompany) {
		this.spouseCompany = spouseCompany;
	}

	/**
	 * @return the spouseDesignation
	 */
	public String getSpouseDesignation() {
		return spouseDesignation;
	}

	/**
	 * @param spouseDesignation
	 *            the spouseDesignation to set
	 */
	public void setSpouseDesignation(String spouseDesignation) {
		this.spouseDesignation = spouseDesignation;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the taxNumber
	 */
	public String getTaxNumber() {
		return taxNumber;
	}

	/**
	 * @param taxNumber
	 *            the taxNumber to set
	 */
	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality
	 *            the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the investmentKnowledge
	 */
	public String getInvestmentKnowledge() {
		return investmentKnowledge;
	}

	/**
	 * @param investmentKnowledge
	 *            the investmentKnowledge to set
	 */
	public void setInvestmentKnowledge(String investmentKnowledge) {
		this.investmentKnowledge = investmentKnowledge;
	}

	/**
	 * @return the investmentExperience
	 */
	public InvestmentExperience getInvestmentExperience() {
		return investmentExperience;
	}

	/**
	 * @param investmentExperience
	 *            the investmentExperience to set
	 */
	public void setInvestmentExperience(InvestmentExperience investmentExperience) {
		this.investmentExperience = investmentExperience;
	}

	/**
	 * @return the revenuePercent
	 */
	public String getRevenuePercent() {
		return revenuePercent;
	}

	/**
	 * @param revenuePercent
	 *            the revenuePercent to set
	 */
	public void setRevenuePercent(String revenuePercent) {
		this.revenuePercent = revenuePercent;
	}

	/**
	 * @return the longTermGrowthPercent
	 */
	public String getLongTermGrowthPercent() {
		return longTermGrowthPercent;
	}

	/**
	 * @param longTermGrowthPercent
	 *            the longTermGrowthPercent to set
	 */
	public void setLongTermGrowthPercent(String longTermGrowthPercent) {
		this.longTermGrowthPercent = longTermGrowthPercent;
	}

	/**
	 * @return the normalTermGrowthPercent
	 */
	public String getNormalTermGrowthPercent() {
		return normalTermGrowthPercent;
	}

	/**
	 * @param normalTermGrowthPercent
	 *            the normalTermGrowthPercent to set
	 */
	public void setNormalTermGrowthPercent(String normalTermGrowthPercent) {
		this.normalTermGrowthPercent = normalTermGrowthPercent;
	}

	/**
	 * @return the shortTermGrowthPercent
	 */
	public String getShortTermGrowthPercent() {
		return shortTermGrowthPercent;
	}

	/**
	 * @param shortTermGrowthPercent
	 *            the shortTermGrowthPercent to set
	 */
	public void setShortTermGrowthPercent(String shortTermGrowthPercent) {
		this.shortTermGrowthPercent = shortTermGrowthPercent;
	}

	/**
	 * @return the riskLowPercent
	 */
	public String getRiskLowPercent() {
		return riskLowPercent;
	}

	/**
	 * @param riskLowPercent
	 *            the riskLowPercent to set
	 */
	public void setRiskLowPercent(String riskLowPercent) {
		this.riskLowPercent = riskLowPercent;
	}

	/**
	 * @return the riskNormalPercent
	 */
	public String getRiskNormalPercent() {
		return riskNormalPercent;
	}

	/**
	 * @param riskNormalPercent
	 *            the riskNormalPercent to set
	 */
	public void setRiskNormalPercent(String riskNormalPercent) {
		this.riskNormalPercent = riskNormalPercent;
	}

	/**
	 * @return the riskhighPercent
	 */
	public String getRiskhighPercent() {
		return riskhighPercent;
	}

	/**
	 * @param riskhighPercent
	 *            the riskhighPercent to set
	 */
	public void setRiskhighPercent(String riskhighPercent) {
		this.riskhighPercent = riskhighPercent;
	}

	/**
	 * @return the shortTermAsset
	 */
	public String getShortTermAsset() {
		return shortTermAsset;
	}

	/**
	 * @param shortTermAsset
	 *            the shortTermAsset to set
	 */
	public void setShortTermAsset(String shortTermAsset) {
		this.shortTermAsset = shortTermAsset;
	}

	/**
	 * @return the fixAsset
	 */
	public String getFixAsset() {
		return fixAsset;
	}

	/**
	 * @param fixAsset
	 *            the fixAsset to set
	 */
	public void setFixAsset(String fixAsset) {
		this.fixAsset = fixAsset;
	}

	/**
	 * @return the netAsset
	 */
	public String getNetAsset() {
		return netAsset;
	}

	/**
	 * @param netAsset
	 *            the netAsset to set
	 */
	public void setNetAsset(String netAsset) {
		this.netAsset = netAsset;
	}

	/**
	 * @return the monthlyIncome
	 */
	public String getMonthlyIncome() {
		return monthlyIncome;
	}

	/**
	 * @param monthlyIncome
	 *            the monthlyIncome to set
	 */
	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	/**
	 * @return the teleTradingPin
	 */
	public String getTeleTradingPin() {
		return teleTradingPin;
	}

	/**
	 * @param teleTradingPin
	 *            the teleTradingPin to set
	 */
	public void setTeleTradingPin(String teleTradingPin) {
		this.teleTradingPin = teleTradingPin;
	}

	/**
	 * @return the isSelectStaff
	 */
	public String getIsSelectStaff() {
		return isSelectStaff;
	}

	/**
	 * @param isSelectStaff
	 *            the isSelectStaff to set
	 */
	public void setIsSelectStaff(String isSelectStaff) {
		this.isSelectStaff = isSelectStaff;
	}

}
