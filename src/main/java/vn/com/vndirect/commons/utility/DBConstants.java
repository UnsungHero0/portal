/*--------------------------------------------------------------------------*
 | Modification Logs:
 | DATE          AUTHOR     DESCRIPTION
 | ------------------------------------------------
 | Sep 26, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.commons.utility;

/**
 * @author thangnq
 * 
 */
public interface DBConstants {

	public interface SEQ {
		final String IFO_DOCUMENT_SEQ = "IFO_DOCUMENT_SEQ";
		final String WP_TOPIC_QUESTION_SEQ = "WP_TOPIC_QUESTION_SEQ";
	}

	public interface IFO_SEC_PRICE {

		public static final int EXCHANGE_CODE_SIZE = 10;

		public static final int SEC_CODE_SIZE = 10;

		public static final int CREATED_BY_SIZE = 10;

		public static final int MODIFIED_BY_SIZE = 10;

		String TABLE_NAME = "IFO_SEC_PRICE_VIEW";
		String COMPANY_ID = "COMPANY_ID";
		String COMPANY_NAME = "COMPANY_NAME";
		String EXCHANGE_CODE = "EXCHANGE_CODE";
		String SYMBOL = "SYMBOL";
		String OPEN_PRICE = "OPEN_PRICE";
		String HIGH_PRICE = "HIGH_PRICE";
		String LOW_PRICE = "LOW_PRICE";
		String CLOSE_PRICE = "CLOSE_PRICE";
		String VOLUME = "VOLUME";
		String TRANS_DATE = "TRANS_DATE";

		String AVERAGE_PRICE = "AVERAGE_PRICE";
		String AD_OPEN_PRICE = "AD_OPEN_PRICE";
		String AD_HIGH_PRICE = "AD_HIGH_PRICE";
		String AD_LOW_PRICE = "AD_LOW_PRICE";
		String AD_CLOSE_PRICE = "AD_CLOSE_PRICE";
		String AD_AVERAGE_PRICE = "AD_AVERAGE_PRICE";
	}

	public interface IFO_SEC_CODE {

		public static final int SEC_CODE_SIZE = 10;

		public static final int SEC_NAME_SIZE = 100;

		public static final int ISIN_SIZE = 10;

		public static final int CREATED_BY_SIZE = 10;

		public static final int MODIFIED_BY_SIZE = 10;

		String SECTOR_CODE = "SECTOR_CODE";
		String SECTOR_NAME = "SECTOR_NAME";
		String LOCALE = "LOCALE";
	}

	public interface IFO_NEWS_SOURCE {

		public static final int SOURCE_NAME_SIZE = 60;

		public static final int CREATED_BY_SIZE = 10;

		public static final int MODIFIED_BY_SIZE = 10;
	}

	public interface IFO_NEWS_KEYWORD {

		public static final int CREATED_BY_SIZE = 10;

		public static final int MODIFIED_BY_SIZE = 10;
	}

	public interface IFO_NEWS {

		public static final int NEWS_TYPE_SIZE = 20;

		public static final int NEWS_HEADER_SIZE = 255;

		public static final int NEWS_ABSTRACT_SIZE = 1000;

		public static final int CREATED_BY_SIZE = 10;

		public static final int MODIFIED_BY_SIZE = 10;

		public static final int STATUS_SIZE = 50;
	}

	public interface IFO_KEYWORD {

		public static final int KEYWORD_TEXT_SIZE = 255;

		public static final int CREATED_BY_SIZE = 10;

		public static final int MODIFIED_BY_SIZE = 10;
	}

	public interface IFO_EXCHANGE_CODE {

		public static final int EXCHANGE_CODE_SIZE = 10;

		public static final int EXCHANGE_NAME_SIZE = 60;

		public static final int COUNTRY_CODE_SIZE = 3;

		public static final int CREATED_BY_SIZE = 10;

		public static final int MODIFIED_BY_SIZE = 10;
	}

	public interface IFO_DOCUMENT {

		public static final int DOCUMENT_TYPE_SIZE = 20;

		public static final int TITLE_SIZE = 255;

		public static final int CONTRIBUTOR_SIZE = 150;

		public static final int AUTHOR_SIZE = 150;

		public static final int ABSTRACT_SIZE = 2400;

		public static final int FILE_NAME_SIZE = 150;

		public static final int FILE_PATH_SIZE = 300;

		public static final int STATUS_SIZE = 20;

		public static final int CREATED_BY_SIZE = 10;

		public static final int MODIFIED_BY_SIZE = 10;
	}

	public interface IFO_DATA_REF {

		public static final int GROUP_CODE_SIZE = 20;

		public static final int ITEM_CODE_SIZE = 20;

		public static final int DESCRIPTION_SIZE = 250;

		public static final int LOCALE_SIZE = 20;
	}

	public interface IFO_COMPANY_NEWS {

		public static final int CREATED_BY_SIZE = 10;

		public static final int MODIFIED_BY_SIZE = 10;
	}

	public interface IFO_COMPANY_KEYWORD {

		public static final int CREATED_BY_SIZE = 10;

		public static final int MODIFIED_BY_SIZE = 10;
	}

	public interface IFO_COMPANY_DOCUMENT {

		public static final int CREATED_BY_SIZE = 10;

		public static final int MODIFIED_BY_SIZE = 10;
	}

	public interface IFO_COMPANY {

		public static final int COMPANY_NAME_SIZE = 255;

		public static final int CREATED_BY_SIZE = 10;

		public static final int MODIFIED_BY_SIZE = 10;

		public static final int SYMBOL_SIZE = 50;
	}

	public interface IFO_ACCESS_TYPE {

		public static final int ACCESS_DESC_SIZE = 60;

		public static final int CREATED_BY_SIZE = 10;

		public static final int MODIFIED_BY_SIZE = 10;
	}

	public interface CMS_USERS {

		public static final int USER_NAME_SIZE = 150;

		public static final int PASSWORD_SIZE = 100;

		public static final int FULL_NAME_SIZE = 250;

		public static final int EMAIL_SIZE = 250;

		public static final int ADDRESS_SIZE = 500;

		public static final int PHONE_SIZE = 50;

		public static final int MOBILE_SIZE = 50;

		public static final int STATUS_SIZE = 20;

		public static final int ROLE_SIZE = 20;
	}

	public interface CMS_MESSAGE_BOARD {

		public static final int SUBJECT_SIZE = 250;

		public static final int AUTHOR_SIZE = 200;

		public static final int SENTIMENT_TYPE_SIZE = 20;

		public static final int TOPIC_ID_SIZE = 50;

		public static final int CONTENT_SIZE = 2400;
	}

	public interface CMS_MASTER_LINK {

		public static final int NAME_SIZE = 125;

		public static final int HREF_SIZE = 250;

		public static final int STATUS_SIZE = 20;

		public static final int TYPE_SIZE = 20;

		public static final int CREATED_BY_SIZE = 10;
	}

	public interface CMS_FORM_APPLICATION {

		public static final int TITLE_SIZE = 250;

		public static final int TYPE_SIZE = 20;

		public static final int FILE_NAME_SIZE = 150;

		public static final int FILE_PATH_SIZE = 250;

		public static final int CREATED_BY_SIZE = 10;
	}

	public interface ONLINE_ACCOUNT {

		String ONLINE_ACCOUNT_ID = "ONLINE_ACCOUNT_ID";
		String LEAD_ID = "LEAD_ID";
		String ONLINE_USER_ID = "ONLINE_USER_ID";
		String USERNAME = "USERNAME";
		String PASSWORD = "PASSWORD";
		String MOBILEPASSWORD = "MOBILEPASSWORD";
		String ACCOUNT_TYPE = "ACCOUNT_TYPE";
		String IS_VN_INVESTER = "IS_VN_INVESTER";
		String HEARD_OF_VNDIRECT = "HEARD_OF_VNDIRECT";
		String REF_CODE = "REF_CODE";
		String FULLNAME = "FULLNAME";
		String DATE_OF_BIRTH = "DATE_OF_BIRTH";
		String SEX = "SEX";
		String ID_TYPE = "ID_TYPE";
		String ID_NUMBER = "ID_NUMBER";
		String ID_ISSUE_DATE = "ID_ISSUE_DATE";
		String ID_EXPIRE_DATE = "ID_EXPIRE_DATE";
		String ID_ISSUE = "ID_ISSUE";
		String MARITAL_STATUS = "MARITAL_STATUS";
		String SPOUSE_NAME = "SPOUSE_NAME";
		String SPOUSE_COMPANY = "SPOUSE_COMPANY";
		String SPOUSE_POSITION = "SPOUSE_POSITION";
		String STREET_ADDRESS = "STREET_ADDRESS";
		String PROVINCE = "PROVINCE";
		String COUNTRY = "COUNTRY";
		String EMAIL = "EMAIL";
		String HOME_PHONE = "HOME_PHONE";
		String MOBILE_PHONE = "MOBILE_PHONE";
		String EDUCATION_LVL = "EDUCATION_LVL";
		String COMPANY = "COMPANY";
		String POSITION = "POSITION";
		String COMPANY_PHONE = "COMPANY_PHONE";
		String COMPANY_FAX = "COMPANY_FAX";
		String BANK_NUMBER = "BANK_NUMBER";
		String BANK_NAME = "BANK_NAME";
		String BANK_CODE = "BANK_CODE";
		String BANK_LOCATION = "BANK_LOCATION";
		String BANK_MANAGER = "BANK_MANAGER";
		String PUBLIC_MANAGE_COMPANY = "PUBLIC_MANAGE_COMPANY";
		String PUBLIC_COMPANY_HOLDING = "PUBLIC_COMPANY_HOLDING";
		String TRANSACTION_TYPE = "TRANSACTION_TYPE";
		String ISDELEGATE_ACCOUNT = "ISDELEGATE_ACCOUNT";
		String DELEGATE_NAME = "DELEGATE_NAME";
		String DELEGATE_PHONE = "DELEGATE_PHONE";
		String STATEMENT_CYCLE = "STATEMENT_CYCLE";
		String STATEMENT_METHOD = "STATEMENT_METHOD";
		String ORDER_RESULT_METHOD = "ORDER_RESULT_METHOD";
		String ORDER_RESULT_MOBILE_PHONE = "ORDER_RESULT_MOBILE_PHONE";
		String NOTIFICATION_METHOD = "NOTIFICATION_METHOD";
		String NOTIFICATION_MOBILE_PHONE = "NOTIFICATION_MOBILE_PHONE";
		String IS_DELEGATE_ORDER = "IS_DELEGATE_ORDER";
		String DELEGATE_FULLNAME = "DELEGATE_FULLNAME";
		String DELEGATE_DATE_OF_BIRTH = "DELEGATE_DATE_OF_BIRTH";
		String DELEGATE_SEX = "DELEGATE_SEX";
		String DELEGATE_ID_TYPE = "DELEGATE_ID_TYPE";
		String DELEGATE_ID_NUMBER = "DELEGATE_ID_NUMBER";
		String DELEGATE_ID_ISSUE_DATE = "DELEGATE_ID_ISSUE_DATE";
		String DELEGATE_ID_EXPIRE_DATE = "DELEGATE_ID_EXPIRE_DATE";
		String DELEGATE_ID_ISSUE = "DELEGATE_ID_ISSUE";
		String DELEGATE_MARITAL_STATUS = "DELEGATE_MARITAL_STATUS";
		String DELEGATE_SPOUSE_NAME = "DELEGATE_SPOUSE_NAME";
		String DELEGATE_SPOUSE_COMPANY = "DELEGATE_SPOUSE_COMPANY";
		String DELEGATE_SPOUSE_POSITION = "DELEGATE_SPOUSE_POSITION";
		String DELEGATE_STREET_ADDRESS = "DELEGATE_STREET_ADDRESS";
		String DELEGATE_PROVINCE = "DELEGATE_PROVINCE";
		String DELEGATE_COUNTRY = "DELEGATE_COUNTRY";
		String DELEGATE_EMAIL = "DELEGATE_EMAIL";
		String DELEGATE_HOME_PHONE = "DELEGATE_HOME_PHONE";
		String DELEGATE_MOBILE_PHONE = "DELEGATE_MOBILE_PHONE";
		String DELEGATE_COMPANY = "DELEGATE_COMPANY";
		String DELEGATE_POSITION = "DELEGATE_POSITION";
		String DELEGATE_COMPANY_PHONE = "DELEGATE_COMPANY_PHONE";
		String DELEGATE_COMPANY_FAX = "DELEGATE_COMPANY_FAX";
		String IS_REMOTE_TRADING = "IS_REMOTE_TRADING";
		String REMOTE_TRADING_IS_OWNER = "REMOTE_TRADING_IS_OWNER";
		String REMOTE_TRADING_IS_DELEGATE = "REMOTE_TRADING_IS_DELEGATE";
		String REMOTE_TRADING_EMAIL = "REMOTE_TRADING_EMAIL";
		String IS_TEL_TRADING = "IS_TEL_TRADING";
		String TEL_TRADING_IS_OWNER_ACCOUNT = "TEL_TRADING_IS_OWNER_ACCOUNT";
		String TEL_TRADING_IS_DELEGATE = "TEL_TRADING_IS_DELEGATE";
		String TRADING_PHONE_NUMBER = "TRADING_PHONE_NUMBER";
		String TRADING_PASS_CONFIRM = "TRADING_PASS_CONFIRM";
		String INVESTMENT_OBJECT_INCOME = "INVESTMENT_OBJECT_INCOME";
		String INVESTMENT_OBJECT_LONG_TERM = "INVESTMENT_OBJECT_LONG_TERM";
		String INVESTMENT_OBJECT_NOMAL_TERM = "INVESTMENT_OBJECT_NOMAL_TERM";
		String INVESTMENT_OBJECT_SHORT_TERM = "INVESTMENT_OBJECT_SHORT_TERM";
		String INVESTMENT_RISK_HIGH = "INVESTMENT_RISK_HIGH";
		String INVESTMENT_RISK_NOMAL = "INVESTMENT_RISK_NOMAL";
		String INVESTMENT_RISK_LOW = "INVESTMENT_RISK_LOW";
		String ASSET_SHORT_TERM = "ASSET_SHORT_TERM";
		String ASSET_FIXED = "ASSET_FIXED";
		String ASSET_CURRENT = "ASSET_CURRENT";
		String ASSET_MONTHLY = "ASSET_MONTHLY";
		String INVESTMENT_KNOWLEDGE = "INVESTMENT_KNOWLEDGE";
		String INVESTMENT_EXPERIENCE = "INVESTMENT_EXPERIENCE";
		String ACCOUNT_STATUS = "ACCOUNT_STATUS";
		String CREATED_DATE = "CREATED_DATE";
		String REPORT_KEY = "REPORT_KEY";

		String FAX = "FAX";
		String TAX_NUMBER = "TAX_NUMBER";
		String NATIONALITY = "NATIONALITY";
		String IS_FLOOR_TRADING = "IS_FLOOR_TRADING";
		String BANK_NUMBER2 = "BANK_NUMBER2";
		String BANK_NAME2 = "BANK_NAME2";
		String BANK_MANAGER2 = "BANK_MANAGER2";
		String CORRESPONDENCE_ACCOUNT_NUMBER = "CORRESPONDENCE_ACCOUNT_NUMBER";
		String CORRESPONDENCE_ACCOUNT_NAME = "CORRESPONDENCE_ACCOUNT_NAME";
		String IS_OTHER_COMPANY_ACCOUNT = "IS_OTHER_COMPANY_ACCOUNT";
		String OTHER_COMPANY_ACCOUNT_NAME = "OTHER_COMPANY_ACCOUNT_NAME";
		String ENTER_MARKET_YEAR = "ENTER_MARKET_YEAR";
		String INVESTMENT_CAPITAL = "INVESTMENT_CAPITAL";
		String INVESTMENT_AIM = "INVESTMENT_AIM";
		String IS_SELECT_STAFF = "IS_SELECT_STAFF";
		String SERVICE_PACK = "SERVICE_PACK";
		String BANK_BRANCH_CODE = "BANK_BRANCH_CODE";
		String BANK_BRANCH_NAME = "BANK_BRANCH_NAME";
		
		String ACTIVED_STATUS = "FINISH";
		String DENIED_STATUS = "DENIED";
	}

	/**
	 * fields for VOTE table
	 */
	public interface VOTE {
		String VOTE_ID = "VOTE_ID";
		String VOTE_NAME = "VOTE_NAME";
		String TOTAL = "TOTAL";
		String IS_DELETED = "IS_DELETED";
		String CREATED_BY = "CREATED_BY";
		String CREATED_DATE = "CREATED_DATE";
		String MODIFIED_BY = "MODIFIED_BY";
		String MODIFIED_DATE = "MODIFIED_DATE";
	}
}
