package vn.com.vndirect.commons.utility;

public interface CRMConstants {

	public interface YES_NO {
		final String YES = "Yes";
		final String NO = "No";
	}

	public interface PRODUCT_CODE {
		final String DIRECT_ACCOUNT = "1000";
		final String PRIME_ACCOUNT = "2000";
	}

	public interface SEX {
		final String MALE = "Male";
		final String FEMALE = "Female";
	}

	public interface MARITAL_STATUS {
		final String SINGLE = "Single";
		final String MARRIED = "Married";
		final String DIVORSED = "Divorsed";
	}

	public interface ID_TYPE {
		final String IDCARD = "IDCARD";
		final String PASSPORT = "PASSPORT";
		final String OTHER_CERTIFICATE = "OTHER_CERTIFICATE";
		final String BUSINESS_REGISTRATION = "BUSINESS_REGISTRATION";
	}
	
	public enum ID_TYPES {
		IDCARD("IDCARD", "001"), PASSPORT("PASSPORT", "002"), 
		OTHER_CERTIFICATE("OTHER_CERTIFICATE", "004"), BUSINESS_REGISTRATION("BUSINESS_REGISTRATION", "005");
		
		private String key;
		private String value;
		
		ID_TYPES (String key, String value){
			this.key = key;
			this.value = value;
		}
		
		public String getKey(){
			return this.key;
		}
		
		public String getValue() {
			return this.value;
		}
	}

	public interface LEAD_SOURCE {
		final String Advertisement = "Advertisement";
		final String Internet = "Internet";
		final String Friends = "Friends";
		final String Others = "Others";
	}

	public interface EDUCATION_LEVEL {
		final String HighSchool = "HighSchool";
		final String University = "University";
		final String PostGraduate = "Post-Graduate";
		final String Other = "Other";
	}

	public interface INVESTMENT_KNOWLEDGE {
		final String Expert = "Expert";
		final String Good = "Good";
		final String Limited = "Limited";
		final String None = "None";
	}

	public interface INVESTMENT_CAPITAL {
		final String Till_100M = "100000000";
		final String Till_500M = "500000000";
		final String Till_1B = "1000000000";
		final String Over_1B = "1000000001";
	}

	public interface INVESTMENT_AIM {
		final String Valued_Ticker = "Valued Ticker";
		final String Increased_Value_Shares = "Increased Value Shares";
		final String Short_Term = "Short Term";
		final String Fixed_Interest = "Fixed Interest";
	}

	public interface ACCOUNT_TYPE {
		final String AccForListed = "AccForListedAndOTC";
		final String AccForOTC = "AccForOTC";
	}

	public interface STATEMENT_CYCLE {
		final String Daily = "Daily";
		final String Weekly = "Weekly";
		final String BiWeekly = "BiWeekly";
		final String Monthly = "Monthly";
		final String Quaterly = "Quaterly";
		final String SemiAnnually = "SemiAnnually";
		final String Yearly = "Yearly";
	}

	public interface RECEIVE_VIA {
		final String Floor = "Floor";
		final String Email = "Email";
		final String Post = "Post";
		final String Fax = "Fax";
	}

	public interface ORDER_RESULT_RECEIVE_BY {
		final String SMS = "SMS";
		final String Email = "Email";
		final String Telephone = "Telephone";
		final String Other = "Other";
	}

	public interface NOTIFY_RECEIVE_BY {
		final String SMS = "SMS";
		final String Email = "Email";
	}

	public interface ADDR_TYPE {
		final String CurrentResidential = "CurrentResidential";
		final String HeadQuaters = "HeadQuaters";
		final String PermanentResidential = "PermanentResidential";
		final String Office = "Office";
		final String Others = "Others";
	}

	public interface SERVICE_PACK {
		final String DIRECT_ACCOUNT = "1000";
		final String PRIME_ACCOUNT = "2000";
	}

	public interface RESULT_STATUS {
		final String SUCCESS = "Success";
		final String FAILURE = "Failure";
		
		final String SUCCESS_CONVERT_LEAD = "0";
		final String FAILURE_CONVERT_LEAD = "1";
		
	}

	public interface REQUEST_CATEGORY {
		final String CUSTOMER_COMPLAIN = "SRC0001";
		final String CUSTOMER_QUERY = "SRC0002";
		final String PRODUCT_RELATED = "SRC0003";
		final String CUSTOMER_CHANGE_REQUEST = "SRC0004";
		final String OTHER_CATEGORY = "SRC0005";
	}

	public interface REQUEST_TYPE {
		final String ACCOUNT_OPENING = "SRT0001";
		final String TRANSACTION = "SRT0002";
		final String GENERAL_NEWS = "SRT0003";
		final String CONTACT_INFORMATION = "SRT0004";
		final String CUSTOMER_CONTRACT_CLOSURE = "SRT0006";
		final String ACCOUNT_RELATED = "SRT0007";
		final String BROKER_AE_ISSUES = "SRT0008";
		final String SERVICE_QUALITY = "SRT0009";
		final String REPUTATION_CREDIBILITY = "SRT0010";
		final String RESEARCH_NEWS = "SRT0011";
		final String IT_RELATED = "SRT0012";
		final String CHANNEL_RELATED = "SRT0013";
		final String TRANSACTION_RELATED = "SRT0014";
		final String PRODUCT_UPGRADE_CLOSURE = "SRT0015";
		final String REQUEST_FOR_NEW_PRODUCT = "SRT0016";
		final String BROKER_AE_CHANGE = "SRT0017";
		final String PROFILE_CHANGE = "SRT0018";
		final String CONTRACT_CHANGE = "SRT0019";
		final String OTHER_TYPES = "SRT0020";
	}

	public interface REQUEST_ACTION {
		final String COMPLAINT_TRADING_ACCOUNT = "SRA0012";
		final String COMPLAINT_BROKERAGES = "SRA0013";
		final String COMPLAINT_OTHER_ACCOUNT_RELATED_ISSUES = "SRA0014";
		final String COMPLAINT_LACK_OF_CORRECT_INVESTMENT_ADVICE_FROM_BROKER_AE = "SRA0015";
		final String COMPLAINT_DELAY_IN_RESPONSE_FROM_BROKER_AE_SERVICES = "SRA0016";
		final String COMPLAINT_LACK_OF_RESPONSE_FROM_BROKER_AE_SERVICES = "SRA0017";
		final String COMPLAINT_OTHERS_BROKER_AE_ISSUES = "SRA0018";
		final String COMPLAINT_CUSTOMER_NOT_INFORMED_ON_EXECUTED_TRANSACTIONS = "SRA0019";
		final String COMPLAINT_OTHER_SERVICE_QUALITY_PROBLEMS = "SRA0020";
		final String COMPLAINT_UNAUTHORIZED_TRADE = "SRA0023";
		final String COMPLAINT_TRADING_MALPRACTICES = "SRA0024";
		final String COMPLAINT_INSIDER_TRADING = "SRA0025";
		final String COMPLAINT_OTHERS_REPUTATION_CREDIBILITY_ISSUES = "SRA0026";
		final String COMPLAINT_OUTDATED_NEWS_RESEARCH_SENT = "SRA0027";
		final String COMPLAINT_NEWS_RESEARCH_RESENT = "SRA0028";
		final String COMPLAINT_IRRELEVANT_NEWS_RESEARCH_SENT = "SRA0029";
		final String COMPLAINT_OTHERS_RESEARCH_NEWS_ISSUES = "SRA0030";
		final String COMPLAINT_VN_DIRECT_WEB_PORTAL_NOT_WORKING = "SRA0031";
		final String COMPLAINT_UNABLE_TO_DOWNLOAD_ACCOUNT_OPENING_FORM = "SRA0032";
		final String COMPLAINT_DID_NOT_RECEIVE_PASSWORD_PIN = "SRA0033";
		final String COMPLAINT_UNABLE_TO_LOGIN_TO_WEB_PORTAL = "SRA0034";
		final String COMPLAINT_OTHERS_IT_RELATED_ISSUES = "SRA0035";
		final String COMPLAINT_NOT_GETTING_MARKET_NEWS_INFORMATION_THROUGH_SMS_EMAIL = "SRA0036";
		final String COMPLAINT_NOT_GETTING_PRIVATE_MESSAGES_THROUGH_EMAIL = "SRA0037";
		final String COMPLAINT_NOT_GETTING_TRASACTION_NOTIFICATION_THROUGH_SMS_EMAIL = "SRA0038";
		final String COMPLAINT_OTHERS_SMS_RELATED = "SRA0039";
		final String COMPLAINT_OTHERS_EMAIL_RELATED = "SRA0040";
		final String COMPLAINT_OTHER_CHANNEL_RELATED_ISSUES = "SRA0041";
		final String COMPLAINT_BROKERAGES_DEDUCTED_MORE_THAN_PROMISED = "SRA0042";
		final String COMPLAINT_OTHERS_TRANSACTION_RELATED_ISSUES = "SRA0043";
		final String COMPLAINT_TRADING_LIMIT = "SRA0113";
		final String COMPLAINT_LONG_TIME_TO_OPEN_TRADING_ACCOUNT = "SRA0218";
		final String COMPLAINT_DIFFERENCES_IN_NUMBER_OF_SHARES_TRANSACTED = "SRA0411";

		final String REQUEST_OPEN_TRADING_ACCOUNT = "SRA0001";
		final String REQUEST_OTHER_ACCOUNT_OPENING_QUERIES = "SRA0002";
		final String REQUEST_TRADING_RELATED_QUERIES = "SRA0003";
		final String REQUEST_OTHER_TRANSACTION_RELATED_QUERIES = "SRA0004";
		final String REQUEST_CURRENT_MARKET_SITUATION = "SRA0005";
		final String REQUEST_MARKET_FORECAST = "SRA0006";
		final String REQUEST_OTHER_GENERAL_MARKET_RELATED_QUERIES = "SRA0007";
		final String REQUEST_VN_DIRECT_OFFICE_CONTACTS = "SRA0008";
		final String REQUEST_OTHER_CONTACT_INFO_QUERIES = "SRA0009";
		final String REQUEST_CONTRACT_ACCOUNT_CLOSURE = "SRA0010";
		final String REQUEST_CUSTOMER_CLOSURE = "SRA0011";

		final String COMPLAINT_REGARDING_SERVICE_OFFERED = "SRA0045";
		final String COMPLAINT_REGARDING_SERVICE_UPGRADES = "SRA0046";
		final String COMPLAINT_REGARDING_SERVICE_ENTITLEMENTS = "SRA0047";
		final String COMPLAINT_REGARDING_SERVICE_CLOSURES = "SRA0048";
		final String COMPLAINT_OTHER_PRODUCT_RELATED_REQUESTS = "SRA0049";
		final String REQUEST_UPGRADE_TO_NEW_PRODUCT = "SRA0051";
		final String REQUEST_HOW_TO_UPGRADE_TO_NEW_PRODUCT = "SRA0052";
		final String REQUEST_HOW_TO_DOWNGRADE_TO_A_PRODUCT = "SRA0053";
		final String REQUEST_INFORMATION_ON_VNDIRECT_LIST_OF_PRODUCTS = "SRA0054";
		final String REQUEST_OTHER_NEW_PRODUCT_RELATED_REQUESTS = "SRA0055";
		final String REQUEST_DOWNGRADE_PRODUCT = "SRA0150";

		final String REQUEST_CHANGE_TRADING_PHONE = "CRA0072";
		final String REQUEST_CHANGE_INDIVIDUAL_CUSTOMER_DEMOGRAPHIC_DETAILS = "SRA0056";
		final String REQUEST_ADD_INDIVIDUAL_CUSTOMER_DEMOGRAPHIC_DETAILS = "SRA0057";
		final String REQUEST_CHANGE_INSTITUTIONAL_CUSTOMER_DEMOGRAPHIC_DETAILS = "SRA0058";
		final String REQUEST_ADD_INSTITUTIONAL_CUSTOMER_DEMOGRAPHIC_DETAILS = "SRA0059";
		final String REQUEST_CHANGE_MORE_ADDRESSES = "SRA0060";
		final String REQUEST_ADD_MORE_ADDRESSES = "SRA0061";
		final String REQUEST_CHANGE_BANK_ACCOUNT_DETAILS = "SRA0062";
		final String REQUEST_ADD_BANK_ACCOUNT_DETAILS = "SRA0063";
		final String REQUEST_CHANGE_IDENTIFICATION_DETAILS = "SRA0064";
		final String REQUEST_ADD_IDENTIFICATION_DETAILS = "SRA0065";
		final String REQUEST_CHANGE_RESET_PIN = "SRA0066";
		final String REQUEST_CHANGE_OF_BROKER_ACCOUNT_EXECUTIVE = "SRA0067";
		final String REQUEST_ADD_ENTRUSTED_CONTACT = "SRA0070";
		final String REQUEST_REMOVE_ENTRUSTED_CONTACT = "SRA0071";
		final String REQUEST_ADD_CORRESPONDENCE_ACCOUNT = "SRA0072";
		final String REQUEST_REMOVE_CORRESPONDENCE_ACCOUNT = "SRA0073";
		final String REQUEST_CHANGE_TELEPHONE = "SRA0074";
		final String REQUEST_CHANGE_EMAIL = "SRA0075";
		final String REQUEST_REGISTER_FOR_MONEY_WITHDRAWAL_THROUGH_TELE = "SRA0076";
		final String REQUEST_REMOVE_MONEY_WITHDRAWAL_THROUGH_TELE = "SRA0077";
		final String REQUEST_CHANGE_MOBILEFONE = "SRA0080";
		final String REQUEST_CHANGE_HOMEPHONE = "SRA0081";
		final String CHANGE_NAME = "SRA0412";
		final String CONTRACT_HO_TRO_LAI_XUAT = "SRA082";

		final String REQUEST_UNDEFINED_CATEGORY_TYPE = "SRA0068";
		final String COMPLAINT_UNDEFINED_CATEGORY_TYPE = "SRA0069";
	}

	public interface ACCOUNT_CLOSURE_DETAILS {
		final String NoBranchNearNewResidence = "NoBranchNearNewResidence";
		final String NoBranchInNewCity = "NoBranchInNewCity";
		final String BranchNotSuitableInLocation = "BranchNotSuitableInLocation";
		final String DivorceOrMarriage = "DivorceOrMarriage";
		final String Death = "Death";
		final String Bankruptcy = "Bankruptcy";
		final String TooManyServiceErrors = "TooManyServiceErrors";
		final String InconsiderateOrInattentiveStaff = "InconsiderateOrInattentiveStaff";
		final String WaitTimeIsTooLong = "WaitTimeIsTooLong";
		final String InconvenientHours = "InconvenientHours";
		final String SlowOrPoorFollowUp = "SlowOrPoorFollowUp";
		final String CouldNotFindRightPerson = "CouldNotFindRightPerson";
		final String UnhappyWithService = "UnhappyWithService";
		final String UnhappyWithProducts = "UnhappyWithProducts";
		final String FeeRelatedDispute = "FeeRelatedDispute";
		final String InterestRatesDispute = "InterestRatesDispute";
		final String TransactionRelatedDisputes = "TransactionRelatedDisputes";
		final String LevelOfServiceChargesUnacceptable = "LevelOfServiceChargesUnacceptable";
		final String BorrowingFacilitiesDeclined = "BorrowingFacilitiesDeclined";
		final String IHaveOtherAccountWithVNDS = "IHaveOtherAccountWithVNDS";
		final String INoLongerHoldAnyAccountsWithVNDS = "INoLongerHoldAnyAccountsWithVNDS";
		final String PurchaseOtherCompanyProducts = "PurchaseOtherCompanyProducts";
	}

	public interface CASE_STATUS {
		final String All = "All";
		final String Open = "Open";
		final String InProgress = "In Progress";
		final String EscalatedToLevel1 = "Escalated to Level1";
		final String EscalatedToLevel2 = "Escalated to Level2";
		final String EscalatedToLevel3 = "Escalated to Level3";
		final String EscalatedToLevel4 = "Escalated to Level4";
		final String Death = "Death";
		final String Bankruptcy = "Bankruptcy";
		final String TooManyServiceErrors = "TooManyServiceErrors";
	}

}
