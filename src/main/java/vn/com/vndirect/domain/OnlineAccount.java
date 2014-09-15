/**
 * 
 */
package vn.com.vndirect.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import vn.com.vndirect.commons.utility.CRMConstants;
import vn.com.vndirect.commons.utility.VNDirectDateUtils;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.DateUtils;

/**
 * @author Huy
 * 
 */

@SuppressWarnings("serial")
public class OnlineAccount implements Serializable {

	/*
	 * local or foreign investor
	 */
	private String investorType;

	/*
	 * DIRECT_ACCOUNT or PRIME_ACCOUNT
	 */
	private String accountType;

	/*
	 * name of investor
	 */
	private String name;
	
	private String lastName;
	
	private String middleName;
	
	private String username;
	
	private String password;
	
	private String retypePassword;
	
	private List<String> mobilePassword;
	/*
	 * Birthday of investor
	 */
	private String birthday;
	
	private String leadId;
	/*
	 * Investor's sex
	 */
	private String sex;

	private String province;
	
	/*
	 * Investor's national
	 */
	private String national;

	/*
	 * single or married
	 */
	private String married;

	/*
	 * Identity No, passport No,...
	 */
	private String identityNo;

	/*
	 * Identity, Passport,..
	 */
	private String identityType;

	/*
	 * Date of issue
	 */
	private String identityDate;

	/*
	 * Place of issue
	 */
	private String identityPlace;

	/*
	 * investor's address
	 */
	private String address;

	/*
	 * email
	 */
	private String email;

	/*
	 * phone number
	 */
	private String phoneNo;

	/*
	 * mobile phone number
	 */
	private String mobilePhoneNo;

	/*
	 * the fax
	 */
	private String fax;

	/*
	 * The tax code
	 */
	private String taxCode;

	/*
	 * The online account id
	 */
	private Long onlineAccountId;

	/*
	 * The online report key
	 */
	private String reportKey;

	/**
	 * INDIVIDUAL_ACCOUNT (for feature using)
	 */
	private String accountType1;

	private String spouseName;

	private String spouseCompany;

	private String spousePosition;
	
	//wrapper to OnlineUser
	public  vn.com.vndirect.wsclient.onlineuser.OnlineUser getOnlineUser(){
		vn.com.vndirect.wsclient.onlineuser.OnlineUser onlineUser = new vn.com.vndirect.wsclient.onlineuser.OnlineUser();
		onlineUser.setAddress(address);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		onlineUser.setCreatedDate(calendar);
		
		Calendar calDob = Calendar.getInstance();
		Calendar calIDDate = Calendar.getInstance();
		try {
			calDob.setTime(VNDirectDateUtils.stringToDate(this.birthday));
			calIDDate.setTime(VNDirectDateUtils.stringToDate(this.identityDate));
		} catch (SystemException e) {}
		
		onlineUser.setDob(calDob);
		onlineUser.setIdDate(calIDDate);
		onlineUser.setEmail(email);
		onlineUser.setFullName(getFullName());
		onlineUser.setIdNumber(this.identityNo);
		onlineUser.setIdPlace(this.identityPlace);
		onlineUser.setSex(this.sex);
		onlineUser.setTelephone(this.mobilePhoneNo);
		return onlineUser;
		
	}
	
	public String getFullName(){
		if(StringUtils.isNotEmpty(this.middleName)){
			return (this.lastName + " " + this.middleName + " " + this.name);
		} else {
			return (this.lastName + " " + this.name);
		}
	}
	
	public String getCRMDobFormat(){
		Date dob = DateUtils.stringToDateITA(this.birthday);
		String dateOfBirth = StringUtils.EMPTY;
		try {
			dateOfBirth = DateUtils.dateToString(dob, DateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1);
		} catch (SystemException e) {}
		return dateOfBirth;
	}
	
	public String getCRMIDDateFormat(){
		Date idDateTime = DateUtils.stringToDateITA(this.identityDate);
		String idDate = StringUtils.EMPTY;
		try {
			idDate = DateUtils.dateToString(idDateTime, DateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1);
		} catch (SystemException e) {}
		return idDate;
	}
	/**
	 * INDIVIDUAL_ACCOUNT (for feature using)
	 * 
	 * @return the accountType1
	 */
	public String getAccountType1() {
		return accountType1;
	}

	/**
	 * @return the Spouse Name
	 */
	public String getSpouseName() {
		return spouseName;
	}

	/**
	 * @param Spouse
	 *            Name the spouseName to set
	 */
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	/**
	 * @return the Spouse Company
	 */
	public String getSpouseCompany() {
		return spouseCompany;
	}

	/**
	 * @param Spouse
	 *            Company the spouseCompany to set
	 */
	public void setSpouseCompany(String spouseCompany) {
		this.spouseCompany = spouseCompany;
	}

	/**
	 * @return the Spouse Position
	 */
	public String getSpousePosition() {
		return spousePosition;
	}

	/**
	 * @param Spouse
	 *            Position the Spouse Position to set
	 */
	public void setSpousePosition(String spousePosition) {
		this.spousePosition = spousePosition;
	}

	/**
	 * @param accountType1
	 *            the accountType1 to set
	 */
	public void setAccountType1(String accountType1) {
		this.accountType1 = accountType1;
	}

	/**
	 * @return the investorType
	 */
	public String getInvestorType() {
		return investorType;
	}

	/**
	 * @param investorType
	 *            the investorType to set
	 */
	public void setInvestorType(String investorType) {
		this.investorType = investorType;
	}

	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType
	 *            the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
	 * @return the national
	 */
	public String getNational() {
		return national;
	}

	/**
	 * @param national
	 *            the national to set
	 */
	public void setNational(String national) {
		this.national = national;
	}

	/**
	 * @return the married
	 */
	public String getMarried() {
		return married;
	}

	/**
	 * @param married
	 *            the married to set
	 */
	public void setMarried(String married) {
		this.married = married;
	}

	/**
	 * @return the identityNo
	 */
	public String getIdentityNo() {
		return identityNo;
	}

	/**
	 * @param identityNo
	 *            the identityNo to set
	 */
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	/**
	 * @return the identityType
	 */
	public String getIdentityType() {
		return identityType;
	}

	/**
	 * @param identityType
	 *            the identityType to set
	 */
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	/**
	 * @return the identityDate
	 */
	public String getIdentityDate() {
		return identityDate;
	}

	/**
	 * @param identityDate
	 *            the identityDate to set
	 */
	public void setIdentityDate(String identityDate) {
		this.identityDate = identityDate;
	}

	/**
	 * @return the identityPlace
	 */
	public String getIdentityPlace() {
		return identityPlace;
	}

	/**
	 * @param identityPlace
	 *            the identityPlace to set
	 */
	public void setIdentityPlace(String identityPlace) {
		this.identityPlace = identityPlace;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo
	 *            the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * @return the mobilePhoneNo
	 */
	public String getMobilePhoneNo() {
		return mobilePhoneNo;
	}

	/**
	 * @param mobilePhoneNo
	 *            the mobilePhoneNo to set
	 */
	public void setMobilePhoneNo(String mobilePhoneNo) {
		this.mobilePhoneNo = mobilePhoneNo;
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
	 * @return the taxCode
	 */
	public String getTaxCode() {
		return taxCode;
	}

	/**
	 * @param taxCode
	 *            the taxCode to set
	 */
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Trading account: Listing or OTC
	 */
	private List<String> tradingAccounts;

	/*
	 * Trading methods
	 */
	private List<String> tradingMethods;

	/*
	 * Trading by phone
	 */
	private String tradingPhone;

	/*
	 * Trading phone no
	 */
	private List<String> tradingPhoneNo;

	/*
	 * Statement cycle to receive: month, in a request
	 */
	private List<String> cycleToReceive;

	/*
	 * Method of receiving account statements
	 */
	private List<String> receiveMethods;

	/*
	 * Method of receiving account statements (by email)
	 */
	private String receiveEmail;

	/* Bank Account */
	private String bankAccountNo;
	
	private String bankAccountName;
	
	private String bankName;
	
	private String bankCode;
	
	private String bankBranchName;
	
	private String bankBranchCode;
	
	/* Bank Account 1 */
	private List<String> accountNo1;

	private String accountName1;

	private String bankName1;

	/* Bank Account 2 */
	private List<String> accountNo2;

	private String accountName2;

	private String bankName2;

	/* VN Direct Account */
	private List<String> vndirectAccNo;

	private String vndirectAccName;

	/**
	 * @return the tradingAccounts
	 */
	public List<String> getTradingAccounts() {
		return tradingAccounts;
	}

	/**
	 * @param tradingAccounts
	 *            the tradingAccounts to set
	 */
	public void setTradingAccounts(List<String> tradingAccounts) {
		this.tradingAccounts = tradingAccounts;
	}

	/**
	 * @return the tradingMethods
	 */
	public List<String> getTradingMethods() {
		return tradingMethods;
	}

	/**
	 * @param tradingMethods
	 *            the tradingMethods to set
	 */
	public void setTradingMethods(List<String> tradingMethods) {
		this.tradingMethods = tradingMethods;
	}

	/**
	 * @return the tradingPhone
	 */
	public String getTradingPhone() {
		return tradingPhone;
	}

	/**
	 * @param tradingPhone
	 *            the tradingPhone to set
	 */
	public void setTradingPhone(String tradingPhone) {
		this.tradingPhone = tradingPhone;
	}

	/**
	 * @return the tradingPhoneNo
	 */
	public List<String> getTradingPhoneNo() {
		return tradingPhoneNo;
	}

	/**
	 * @param tradingPhoneNo
	 *            the tradingPhoneNo to set
	 */
	public void setTradingPhoneNo(List<String> tradingPhoneNo) {
		this.tradingPhoneNo = tradingPhoneNo;
	}

	/**
	 * @return the cycleToReceive
	 */
	public List<String> getCycleToReceive() {
		return cycleToReceive;
	}

	/**
	 * @param cycleToReceive
	 *            the cycleToReceive to set
	 */
	public void setCycleToReceive(List<String> cycleToReceive) {
		this.cycleToReceive = cycleToReceive;
	}

	/**
	 * @return the receiveMethods
	 */
	public List<String> getReceiveMethods() {
		return receiveMethods;
	}

	/**
	 * @param receiveMethods
	 *            the receiveMethods to set
	 */
	public void setReceiveMethods(List<String> receiveMethods) {
		this.receiveMethods = receiveMethods;
	}

	/**
	 * @return the receiveEmail
	 */
	public String getReceiveEmail() {
		return receiveEmail;
	}

	/**
	 * @param receiveEmail
	 *            the receiveEmail to set
	 */
	public void setReceiveEmail(String receiveEmail) {
		this.receiveEmail = receiveEmail;
	}

	/**
	 * @return the accountNo1
	 */
	public List<String> getAccountNo1() {
		return accountNo1;
	}

	/**
	 * @param accountNo1
	 *            the accountNo1 to set
	 */
	public void setAccountNo1(List<String> accountNo1) {
		this.accountNo1 = accountNo1;
	}

	/**
	 * @return the accountName1
	 */
	public String getAccountName1() {
		return accountName1;
	}

	/**
	 * @param accountName1
	 *            the accountName1 to set
	 */
	public void setAccountName1(String accountName1) {
		this.accountName1 = accountName1;
	}

	/**
	 * @return the bankName1
	 */
	public String getBankName1() {
		return bankName1;
	}

	/**
	 * @param bankName1
	 *            the bankName1 to set
	 */
	public void setBankName1(String bankName1) {
		this.bankName1 = bankName1;
	}

	/**
	 * @return the accountNo2
	 */
	public List<String> getAccountNo2() {
		return accountNo2;
	}

	/**
	 * @param accountNo2
	 *            the accountNo2 to set
	 */
	public void setAccountNo2(List<String> accountNo2) {
		this.accountNo2 = accountNo2;
	}

	/**
	 * @return the accountName2
	 */
	public String getAccountName2() {
		return accountName2;
	}

	/**
	 * @param accountName2
	 *            the accountName2 to set
	 */
	public void setAccountName2(String accountName2) {
		this.accountName2 = accountName2;
	}

	/**
	 * @return the bankName2
	 */
	public String getBankName2() {
		return bankName2;
	}

	/**
	 * @param bankName2
	 *            the bankName2 to set
	 */
	public void setBankName2(String bankName2) {
		this.bankName2 = bankName2;
	}

	/**
	 * @return the vndirectAccNo
	 */
	public List<String> getVndirectAccNo() {
		return vndirectAccNo;
	}

	/**
	 * @param vndirectAccNo
	 *            the vndirectAccNo to set
	 */
	public void setVndirectAccNo(List<String> vndirectAccNo) {
		this.vndirectAccNo = vndirectAccNo;
	}

	/**
	 * @return the vndirectAccName
	 */
	public String getVndirectAccName() {
		return vndirectAccName;
	}

	/**
	 * @param vndirectAccName
	 *            the vndirectAccName to set
	 */
	public void setVndirectAccName(String vndirectAccName) {
		this.vndirectAccName = vndirectAccName;
	}

	// /////////////////////////////////////////////////////////////////////////

	/*
	 * Management name
	 */
	private String mangName;

	/*
	 * Capital Name
	 */
	private String capitalName;

	/*
	 * Account in the other stock company
	 */
	private String otherAccount;

	/*
	 * Account name in the other stock company
	 */
	private String otherAccName;

	/*
	 * Year
	 */
	private String year;

	/*
	 * Experience
	 */
	private List<String> exprs;

	/*
	 * Investment
	 */
	private List<String> invs;

	/*
	 * Investment criteria
	 */
	private List<String> invCriteria;

	/*
	 * Investment capital availability
	 */
	private List<String> invCapAvail;

	/**
	 * @return the mangName
	 */
	public String getMangName() {
		return mangName;
	}

	/**
	 * @param mangName
	 *            the mangName to set
	 */
	public void setMangName(String mangName) {
		this.mangName = mangName;
	}

	/**
	 * @return the capitalName
	 */
	public String getCapitalName() {
		return capitalName;
	}

	/**
	 * @param capitalName
	 *            the capitalName to set
	 */
	public void setCapitalName(String capitalName) {
		this.capitalName = capitalName;
	}

	/**
	 * @return the otherAccount
	 */
	public String getOtherAccount() {
		return otherAccount;
	}

	/**
	 * @param otherAccount
	 *            the otherAccount to set
	 */
	public void setOtherAccount(String otherAccount) {
		this.otherAccount = otherAccount;
	}

	/**
	 * @return the otherAccName
	 */
	public String getOtherAccName() {
		return otherAccName;
	}

	/**
	 * @param otherAccName
	 *            the otherAccName to set
	 */
	public void setOtherAccName(String otherAccName) {
		this.otherAccName = otherAccName;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the exprs
	 */
	public List<String> getExprs() {
		return exprs;
	}

	/**
	 * @param exprs
	 *            the exprs to set
	 */
	public void setExprs(List<String> exprs) {
		this.exprs = exprs;
	}

	/**
	 * @return the invs
	 */
	public List<String> getInvs() {
		return invs;
	}

	/**
	 * @param invs
	 *            the invs to set
	 */
	public void setInvs(List<String> invs) {
		this.invs = invs;
	}

	/**
	 * @return the invCriteria
	 */
	public List<String> getInvCriteria() {
		return invCriteria;
	}

	/**
	 * @param invCriteria
	 *            the invCriteria to set
	 */
	public void setInvCriteria(List<String> invCriteria) {
		this.invCriteria = invCriteria;
	}

	/**
	 * @return the invCapAvail
	 */
	public List<String> getInvCapAvail() {
		return invCapAvail;
	}

	/**
	 * @param invCapAvail
	 *            the invCapAvail to set
	 */
	public void setInvCapAvail(List<String> invCapAvail) {
		this.invCapAvail = invCapAvail;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * @return the onlineAccountId
	 */
	public Long getOnlineAccountId() {
		return onlineAccountId;
	}

	/**
	 * @param onlineAccountId
	 *            the onlineAccountId to set
	 */
	public void setOnlineAccountId(Long onlineAccountId) {
		this.onlineAccountId = onlineAccountId;
	}

	/**
	 * @return the reportKey
	 */
	public String getReportKey() {
		return reportKey;
	}

	/**
	 * @param reportKey
	 *            the reportKey to set
	 */
	public void setReportKey(String reportKey) {
		this.reportKey = reportKey;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

	public List<String> getMobilePassword() {
		return mobilePassword;
	}

	public void setMobilePassword(List<String> mobilePassword) {
		this.mobilePassword = mobilePassword;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankBranchCode() {
		return bankBranchCode;
	}

	public void setBankBranchCode(String bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}

	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}
