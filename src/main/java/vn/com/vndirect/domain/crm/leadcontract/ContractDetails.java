package vn.com.vndirect.domain.crm.leadcontract;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.config.entities.ActionConfig.Builder;

import vn.com.vndirect.commons.utility.CRMConstants;

@XmlType(name = "ContractDetails", propOrder = { "productCode", "oTCAccountType", "isFloorTrader", "isOnlineTrader", "isPhoneTrader", "isDelegateAccount", "delegatorCustomerId",
		"corresAccountNumber", "payeeBenifRelation", "isOtherCompAccount", "otherCompAccountName", "enteringYrInMarket", "investmentCapital", "investmentAim", "managementPositioningPublicCompany",
		"fivePercentShareholdingPublicCompany", "tradingPhoneNo", "tradingMobileNo", "statementCycle", "receiveVia", "orderResultReceiveBy", "notifReceiveBy", "smstrading", "smstradingphone" })
@SuppressWarnings("serial")
public class ContractDetails implements Serializable {
	private String productCode;
	private String oTCAccountType;
	private String isFloorTrader;
	private String isOnlineTrader;
	private String isPhoneTrader;
	private String isDelegateAccount;
	private String delegatorCustomerId;
	private String corresAccountNumber;
	private String payeeBenifRelation;
	private String isOtherCompAccount;
	private String otherCompAccountName;
	private String enteringYrInMarket;
	private String investmentCapital;
	private String investmentAim;
	private String managementPositioningPublicCompany;
	private String fivePercentShareholdingPublicCompany;
	private String tradingPhoneNo;
	private String tradingMobileNo;
	private String statementCycle;
	private String receiveVia;
	private String orderResultReceiveBy;
	private String notifReceiveBy;
	private String smstrading;
	private String smstradingphone;
	
	public ContractDetails(){
		super();
	}
	
	public static class ContractBuilder{
		private String productCode = StringUtils.EMPTY;
		private String oTCAccountType = CRMConstants.ACCOUNT_TYPE.AccForListed;
		private String isFloorTrader = CRMConstants.YES_NO.YES;
		private String isOnlineTrader = CRMConstants.YES_NO.YES;
		private String isPhoneTrader = CRMConstants.YES_NO.YES;
		private String isDelegateAccount = CRMConstants.YES_NO.NO;
		private String orderResultReceiveBy = CRMConstants.ORDER_RESULT_RECEIVE_BY.SMS;
		private String investmentAim = CRMConstants.INVESTMENT_AIM.Valued_Ticker;
		private String statementCycle = CRMConstants.STATEMENT_CYCLE.Daily;
		private String receiveVia = CRMConstants.RECEIVE_VIA.Email;
		private String isOtherCompAccount = CRMConstants.YES_NO.NO;
		private String notifReceiveBy = CRMConstants.NOTIFY_RECEIVE_BY.SMS;
		private String delegatorCustomerId = StringUtils.EMPTY;
		private String corresAccountNumber = StringUtils.EMPTY;
		private String payeeBenifRelation = StringUtils.EMPTY;
		private String otherCompAccountName = StringUtils.EMPTY;
		private String enteringYrInMarket = StringUtils.EMPTY;
		private String investmentCapital = StringUtils.EMPTY;
		private String tradingPhoneNo = StringUtils.EMPTY;
		private String tradingMobileNo = StringUtils.EMPTY;
		private String managementPositioningPublicCompany = StringUtils.EMPTY;
		private String fivePercentShareholdingPublicCompany = StringUtils.EMPTY;
		private String smstrading = "1";//Default 1: yes, 0 no
		private String smstradingphone;
		
		public ContractBuilder(String tradingPhoneNo){
			this.tradingPhoneNo = tradingPhoneNo;
			this.smstradingphone = tradingPhoneNo;
		}
		
		public ContractBuilder productCode(String productCode){
			this.productCode = productCode;
			return this;
		}
		public ContractBuilder oTCAccountType(String oTCAccountType){
			this.oTCAccountType = oTCAccountType;
			return this;
		}
		public ContractBuilder isFloorTrader(String isFloorTrader){
			this.isFloorTrader = isFloorTrader;
			return this;
		}
		public ContractBuilder isOnlineTrader(String isOnlineTrader){
			this.isOnlineTrader = isOnlineTrader;
			return this;
		}
		public ContractBuilder isPhoneTrader(String isPhoneTrader){
			this.isPhoneTrader = isPhoneTrader;
			return this;
		}
		public ContractBuilder isDelegateAccount(String isDelegateAccount){
			this.isDelegateAccount = isDelegateAccount;
			return this;
		}
		public ContractBuilder delegatorCustomerId(String delegatorCustomerId){
			this.delegatorCustomerId = delegatorCustomerId;
			return this;
		}
		public ContractBuilder corresAccountNumber(String corresAccountNumber){
			this.corresAccountNumber = corresAccountNumber;
			return this;
		}
		public ContractBuilder payeeBenifRelation(String payeeBenifRelation){
			this.payeeBenifRelation = payeeBenifRelation;
			return this;
		}
		public ContractBuilder isOtherCompAccount(String isOtherCompAccount){
			this.isOtherCompAccount = isOtherCompAccount;
			return this;
		}
		public ContractBuilder otherCompAccountName(String otherCompAccountName){
			this.otherCompAccountName = otherCompAccountName;
			return this;
		}
		public ContractBuilder enteringYrInMarket(String enteringYrInMarket){
			this.enteringYrInMarket = enteringYrInMarket;
			return this;
		}
		public ContractBuilder investmentCapital(String investmentCapital){
			this.investmentCapital = investmentCapital;
			return this;
		}
		public ContractBuilder investmentAim(String investmentAim){
			this.investmentAim = investmentAim;
			return this;
		}
		public ContractBuilder managementPositioningPublicCompany(String managementPositioningPublicCompany){
			this.managementPositioningPublicCompany = managementPositioningPublicCompany;
			return this;
		}
		public ContractBuilder fivePercentShareholdingPublicCompany(String fivePercentShareholdingPublicCompany){
			this.fivePercentShareholdingPublicCompany = fivePercentShareholdingPublicCompany;
			return this;
		}
		
		public ContractBuilder tradingMobileNo(String tradingMobileNo){
			this.tradingMobileNo = tradingMobileNo;
			return this;
		}
		public ContractBuilder statementCycle(String statementCycle){
			this.statementCycle = statementCycle;
			return this;
		}
		public ContractBuilder receiveVia(String receiveVia){
			this.receiveVia = receiveVia;
			return this;
		}
		public ContractBuilder orderResultReceiveBy(String orderResultReceiveBy){
			this.orderResultReceiveBy = orderResultReceiveBy;
			return this;
		}
		public ContractBuilder notifReceiveBy(String notifReceiveBy){
			this.notifReceiveBy = notifReceiveBy;
			return this;
		}
		public ContractBuilder smstrading(String smstrading){
			this.smstrading = smstrading;
			return this;
		}
		private ContractBuilder smstradingphone(String smstradingphone){
			this.smstradingphone = smstradingphone;
			return this;
		}
		public ContractDetails build(){
			return new ContractDetails(this);
		}
	}
	
	
	public ContractDetails(ContractBuilder builder){
		this.productCode = builder.productCode;
		this.oTCAccountType = builder.oTCAccountType;
		this.isFloorTrader = builder.isFloorTrader;
		this.isOnlineTrader = builder.isOnlineTrader;
		this.isPhoneTrader = builder.isPhoneTrader;
		this.isDelegateAccount = builder.isDelegateAccount;
		this.delegatorCustomerId = builder.delegatorCustomerId;
		this.corresAccountNumber = builder.corresAccountNumber;
		this.payeeBenifRelation = builder.payeeBenifRelation;
		this.isOtherCompAccount = builder.isOtherCompAccount;
		this.otherCompAccountName = builder.otherCompAccountName;
		this.enteringYrInMarket = builder.enteringYrInMarket;
		this.investmentCapital = builder.investmentCapital;
		this.investmentAim = builder.investmentAim;
		this.managementPositioningPublicCompany = builder.managementPositioningPublicCompany;
		this.fivePercentShareholdingPublicCompany = builder.fivePercentShareholdingPublicCompany;
		this.tradingPhoneNo = builder.tradingPhoneNo;
		this.tradingMobileNo = builder.tradingMobileNo;
		this.statementCycle = builder.statementCycle;
		this.receiveVia = builder.receiveVia;
		this.orderResultReceiveBy = builder.orderResultReceiveBy;
		this.notifReceiveBy = builder.notifReceiveBy;
		this.smstrading = builder.smstrading;
		this.smstradingphone = builder.smstradingphone;
	}
	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode
	 *            the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the oTCAccountType
	 */
	public String getoTCAccountType() {
		return oTCAccountType;
	}

	/**
	 * @param oTCAccountType
	 *            the oTCAccountType to set
	 */
	public void setoTCAccountType(String oTCAccountType) {
		this.oTCAccountType = oTCAccountType;
	}

	/**
	 * @return the isFloorTrader
	 */
	public String getIsFloorTrader() {
		return isFloorTrader;
	}

	/**
	 * @param isFloorTrader
	 *            the isFloorTrader to set
	 */
	public void setIsFloorTrader(String isFloorTrader) {
		this.isFloorTrader = isFloorTrader;
	}

	/**
	 * @return the isOnlineTrader
	 */
	public String getIsOnlineTrader() {
		return isOnlineTrader;
	}

	/**
	 * @param isOnlineTrader
	 *            the isOnlineTrader to set
	 */
	public void setIsOnlineTrader(String isOnlineTrader) {
		this.isOnlineTrader = isOnlineTrader;
	}

	/**
	 * @return the isPhoneTrader
	 */
	public String getIsPhoneTrader() {
		return isPhoneTrader;
	}

	/**
	 * @param isPhoneTrader
	 *            the isPhoneTrader to set
	 */
	public void setIsPhoneTrader(String isPhoneTrader) {
		this.isPhoneTrader = isPhoneTrader;
	}

	/**
	 * @return the isDelegateAccount
	 */
	public String getIsDelegateAccount() {
		return isDelegateAccount;
	}

	/**
	 * @param isDelegateAccount
	 *            the isDelegateAccount to set
	 */
	public void setIsDelegateAccount(String isDelegateAccount) {
		this.isDelegateAccount = isDelegateAccount;
	}

	/**
	 * @return the delegatorCustomerId
	 */
	public String getDelegatorCustomerId() {
		return delegatorCustomerId;
	}

	/**
	 * @param delegatorCustomerId
	 *            the delegatorCustomerId to set
	 */
	public void setDelegatorCustomerId(String delegatorCustomerId) {
		this.delegatorCustomerId = delegatorCustomerId;
	}

	/**
	 * @return the corresAccountNumber
	 */
	public String getCorresAccountNumber() {
		return corresAccountNumber;
	}

	/**
	 * @param corresAccountNumber
	 *            the corresAccountNumber to set
	 */
	public void setCorresAccountNumber(String corresAccountNumber) {
		this.corresAccountNumber = corresAccountNumber;
	}

	/**
	 * @return the payeeBenifRelation
	 */
	public String getPayeeBenifRelation() {
		return payeeBenifRelation;
	}

	/**
	 * @param payeeBenifRelation
	 *            the payeeBenifRelation to set
	 */
	public void setPayeeBenifRelation(String payeeBenifRelation) {
		this.payeeBenifRelation = payeeBenifRelation;
	}

	/**
	 * @return the isOtherCompAccount
	 */
	public String getIsOtherCompAccount() {
		return isOtherCompAccount;
	}

	/**
	 * @param isOtherCompAccount
	 *            the isOtherCompAccount to set
	 */
	public void setIsOtherCompAccount(String isOtherCompAccount) {
		this.isOtherCompAccount = isOtherCompAccount;
	}

	/**
	 * @return the otherCompAccountName
	 */
	public String getOtherCompAccountName() {
		return otherCompAccountName;
	}

	/**
	 * @param otherCompAccountName
	 *            the otherCompAccountName to set
	 */
	public void setOtherCompAccountName(String otherCompAccountName) {
		this.otherCompAccountName = otherCompAccountName;
	}

	/**
	 * @return the enteringYrInMarket
	 */
	public String getEnteringYrInMarket() {
		return enteringYrInMarket;
	}

	/**
	 * @param enteringYrInMarket
	 *            the enteringYrInMarket to set
	 */
	public void setEnteringYrInMarket(String enteringYrInMarket) {
		this.enteringYrInMarket = enteringYrInMarket;
	}

	/**
	 * @return the investmentCapital
	 */
	public String getInvestmentCapital() {
		return investmentCapital;
	}

	/**
	 * @param investmentCapital
	 *            the investmentCapital to set
	 */
	public void setInvestmentCapital(String investmentCapital) {
		this.investmentCapital = investmentCapital;
	}

	/**
	 * @return the investmentAim
	 */
	public String getInvestmentAim() {
		return investmentAim;
	}

	/**
	 * @param investmentAim
	 *            the investmentAim to set
	 */
	public void setInvestmentAim(String investmentAim) {
		this.investmentAim = investmentAim;
	}

	/**
	 * @return the managementPositioningPublicCompany
	 */
	public String getManagementPositioningPublicCompany() {
		return managementPositioningPublicCompany;
	}

	/**
	 * @param managementPositioningPublicCompany
	 *            the managementPositioningPublicCompany to set
	 */
	public void setManagementPositioningPublicCompany(String managementPositioningPublicCompany) {
		this.managementPositioningPublicCompany = managementPositioningPublicCompany;
	}

	/**
	 * @return the fivePercentShareholdingPublicCompany
	 */
	public String getFivePercentShareholdingPublicCompany() {
		return fivePercentShareholdingPublicCompany;
	}

	/**
	 * @param fivePercentShareholdingPublicCompany
	 *            the fivePercentShareholdingPublicCompany to set
	 */
	public void setFivePercentShareholdingPublicCompany(String fivePercentShareholdingPublicCompany) {
		this.fivePercentShareholdingPublicCompany = fivePercentShareholdingPublicCompany;
	}

	/**
	 * @return the tradingPhoneNo
	 */
	public String getTradingPhoneNo() {
		return tradingPhoneNo;
	}

	/**
	 * @param tradingPhoneNo
	 *            the tradingPhoneNo to set
	 */
	public void setTradingPhoneNo(String tradingPhoneNo) {
		this.tradingPhoneNo = tradingPhoneNo;
	}

	/**
	 * @return the tradingMobileNo
	 */
	public String getTradingMobileNo() {
		return tradingMobileNo;
	}

	/**
	 * @param tradingMobileNo
	 *            the tradingMobileNo to set
	 */
	public void setTradingMobileNo(String tradingMobileNo) {
		this.tradingMobileNo = tradingMobileNo;
	}

	/**
	 * @return the statementCycle
	 */
	public String getStatementCycle() {
		return statementCycle;
	}

	/**
	 * @param statementCycle
	 *            the statementCycle to set
	 */
	public void setStatementCycle(String statementCycle) {
		this.statementCycle = statementCycle;
	}

	/**
	 * @return the receiveVia
	 */
	public String getReceiveVia() {
		return receiveVia;
	}

	/**
	 * @param receiveVia
	 *            the receiveVia to set
	 */
	public void setReceiveVia(String receiveVia) {
		this.receiveVia = receiveVia;
	}

	/**
	 * @return the orderResultReceiveBy
	 */
	public String getOrderResultReceiveBy() {
		return orderResultReceiveBy;
	}

	/**
	 * @param orderResultReceiveBy
	 *            the orderResultReceiveBy to set
	 */
	public void setOrderResultReceiveBy(String orderResultReceiveBy) {
		this.orderResultReceiveBy = orderResultReceiveBy;
	}

	/**
	 * @return the notifReceiveBy
	 */
	public String getNotifReceiveBy() {
		return notifReceiveBy;
	}

	/**
	 * @param notifReceiveBy
	 *            the notifReceiveBy to set
	 */
	public void setNotifReceiveBy(String notifReceiveBy) {
		this.notifReceiveBy = notifReceiveBy;
	}
	public String getSmstrading() {
		return smstrading;
	}
	public void setSmstrading(String smstrading) {
		this.smstrading = smstrading;
	}
	public String getSmstradingphone() {
		return smstradingphone;
	}
	public void setSmstradingphone(String smstradingphone) {
		this.smstradingphone = smstradingphone;
	}
}
