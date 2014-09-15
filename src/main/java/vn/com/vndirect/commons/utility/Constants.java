package vn.com.vndirect.commons.utility;

import vn.com.web.commons.servercfg.ServerConfig;

public interface Constants {
	public static final String NULL_VALUE = "";

	public interface SessionKeys {
		String NEWS_ABSTRACT_KEY = "$NEWS_ABSTRACT_KEY$";
		String NEWS_CONTENT_KEY = "$NEWS_CONTENT_KEY$";
		String FW_URI_AFTER_AUTH = "fwUriAfterAuth";
	}

	public interface RequestKeys {
		String REQUEST_URL = "$REQUEST_URL$";
		String REQUEST_ACTION_FORM_URL = "$REQUEST_ACTION_FORM_URL$";
		String REQUEST_CURRENT_COMPANY_OBJECT = "$Request.REQUEST_CURRENT_COMPANY_OBJECT$";
		String CURRENT_COMPANY_FOR_QUOTES = "CURRENT_COMPANY_FOR_QUOTES";
	}

	public interface RequestParams {
		String CALLER = "caller";
		String ACTION_FOR = "actionFor";
		String FOR_MARKET = "forMarket";
		String URI_PARAM = "uriParam";
	}

	public interface VelocityTemplate {
		String MULTI_LANGUAGE_TAG_KEY = "MULTI_LANGUAGE";
		String QUOTES_COMPANY_SNAPSHOT = "QUOTES_COMPANY_SNAPSHOT";
		String QUOTES_COMPANY_SNAPSHOT_FOR_CHART = "QUOTES_COMPANY_SNAPSHOT_FOR_CHART";
		String QUOTES_COMPANY_INFO = "QUOTES_COMPANY_INFO";
		String SLIDE_NEWS = "SLIDE_NEWS";
	}

	public interface CacheKey4ChangeLang {
		String CACHE_KEY_4_CHANGE_LANG = "_CacheChangeLang";
		String PREVIOUS_URI = "$PREVIOUS_URI";
		String CALLER = "$CALLER";
		String ACTION_FOR = "$ACTION_FOR";
		String FOR_MARKET = "$FOR_MARKET";
		String URI_PARAM = "$URI_PARAM";
		String CHANGE_LANG_ACTION = "CHANGE_LANG_ACTION";
		String VNINDEX_SELECTED_LANG = "VNIndexSelectedLang";
	}

	public interface SpringBeanNames {
		String COMMON_MANAGER = "CommonManager";
		String QUOTES_MANAGER = "QuotesManager";
		String ACCOUNT_MANAGER = "AccountManager";
		String ONLINE_SERVICE_MANAGER = "OnlineServiceManager";

		String CHART_MANAGER_SERVICE = "ChartManager";
		String QUOTES_MANAGER_SERVICE = "QuotesManager";
		String TRADING_MANAGER_SERVICE = "TradingManager";
		String RESEARCH_MANAGER_SERVICE = "ResearchManager";
		String NEWS_INFO_MANAGER = "NewsInfoManager";
		String USER_MANAGER_SERVICE = "UserManager";
		String WEB_AGENT_SERVICE = "WebAgentManager";
		String CHARTS_MANAGER_BEAN = "ChartsManager";
		String OSCACHE_BEAN = "OSCache";
		String FILES_ADAPTER_BEAN = "FilesAdapter";
		String FIFO_MANAGER_SERVICE = "FinfoDBManager";
	}

	public interface Roles {
		public String DEFAULT = "ROLE_GUEST";
	}

	public interface Lang {
		String DEFAULT = "vn";
		String VN = "vn";
		String EN = "en";
		String JP = "jp";
	}

	public interface Commons {
		String NO_RECORD_FOUND = "/Messages/Commons/No-Record";
		String NO_ITEM_SELECTED = "/Messages/Commons/No-Item-Selected";
		String ACTION_SUCCESS = "/Messages/Commons/ActionSuccess";
		String ACTION_SAVE_SUCCESS = "/Messages/Commons/SaveSuccess";
	}

	public interface NewsDate {
		String SUN = "Commons.NewsDate.Sun";
		String MON = "Commons.NewsDate.Mon";
		String TUE = "Commons.NewsDate.Tue";
		String WED = "Commons.NewsDate.Wed";
		String THU = "Commons.NewsDate.Thu";
		String FRI = "Commons.NewsDate.Fri";
		String SAT = "Commons.NewsDate.Sat";
	}

	/**
	 * 
	 * @author
	 * 
	 */
	public interface CaptchaKey {
		String CAPTCHA_4_LOGIN = "j_captcha_4_login";
		String CAPTCHA_4_PROJ_SUBMISSION = "j_captcha_4_proj_submission";
		String CAPTCHA_4_COMMENTS = "j_captcha_4_comments";
	}

	/**
	 * Define deleted or undeleted key
	 * 
	 * @author
	 * 
	 */
	public interface DeletedOption {
		Integer DELETED = new Integer(1);
		Integer UN_DELETED = new Integer(0);

		Integer UNKNOW = new Integer(-1);

		Integer[] ALL = new Integer[] { DELETED, UN_DELETED };
	}

	/**
	 * 
	 * @author
	 * 
	 */
	public interface DisplayOption {
		Integer DISPLAY = new Integer(1);
		Integer UN_DISPLAY = new Integer(0);

		Integer UNKNOW = new Integer(-1);

		Integer[] ALL = new Integer[] { DISPLAY, UN_DISPLAY };
	}

	/**
	 * 
	 * @author
	 * 
	 */
	public interface DefaultOption {
		Integer IS_DEFAULT = new Integer(1);
		Integer NOT_DEFAULT = new Integer(0);

		Integer UNKNOW = new Integer(-1);

		Integer[] ALL = new Integer[] { IS_DEFAULT, NOT_DEFAULT };
	}

	/**
	 * 
	 * @author
	 * 
	 */
	public interface EnabledOption {
		Integer ENABLED = new Integer(1);
		Integer UN_ENABLED = new Integer(0);

		Integer UNKNOW = new Integer(-1);

		Integer[] ALL = new Integer[] { ENABLED, UN_ENABLED };
	}

	/**
	 * 
	 * @author
	 * 
	 */
	public interface LockedOption {
		Integer LOCKED = new Integer(1);
		Integer UN_LOCKED = new Integer(0);

		Integer UNKNOW = new Integer(-1);

		Integer[] ALL = new Integer[] { LOCKED, UN_LOCKED };
	}

	/**
	 * 
	 * @author
	 * 
	 */
	public interface SharedOption {
		Integer SHARED = new Integer(1);
		Integer UN_SHARED = new Integer(0);

		Integer UNKNOW = new Integer(-1);

		Integer[] ALL = new Integer[] { SHARED, UN_SHARED };
	}

	/**
	 * 
	 * @author
	 * 
	 */
	public interface ThumbnailOption {
		Integer IS_THUMBNAIL = new Integer(1);
		Integer IS_NOT_THUMBNAIL = new Integer(0);

		Integer UNKNOW = new Integer(-1);

		Integer[] ALL = new Integer[] { IS_THUMBNAIL, IS_NOT_THUMBNAIL };
	}

	/**
	 * 
	 * @author tungnq.nguyen
	 * 
	 */
	// public interface DeploymentModel {
	// int UNKOWN = -1;
	// int TRADING_ONLINE = 1;
	// int WEBAGENT = 2;
	// }

	/**
	 * 
	 * @author
	 * 
	 */
	public interface GroupCode {

		public interface ReferenceData {
		}
	}

	/**
	 * 
	 * @author
	 * 
	 */
	public interface ItemCode {

	}

	public interface ErrorKeys {
		public interface WebAgent {
			String REMOTE_ERROR = "/ErrorMessage/WebAgent/Service_errors/RemoteError";

			public interface SearchAccount {
				String CANNOT_FIND_ACCOUNT = "/ErrorMessage/WebAgent/SearchAccount/CannotFindAccount";
				String ACCOUNT_EMPTY = "/ErrorMessage/WebAgent/SearchAccount/AccountNumberEmpty";
			}
		}

		public interface Commons {
			public static final String LENGTH_OF_FIELD_TOO_LONG = "/ErrorMessage/Commons/length_of_field_too_long";
			String SYSTEM_ERROR = "/ErrorMessage/Commons/SystemError";
			String INVALID_AUTH_LEVEL1 = "/ErrorMessage/Commons/InvalidAuthLevel1";
			String INVALID_AUTH_LEVEL2 = "/ErrorMessage/Commons/InvalidAuthLevel2";
			String INPUT_WARNING_MSG = "/ErrorMessage/Commons/InputWarnMsg";
		}

		public interface IDG {
			String REMOTE_ERROR = "/ErrorMessage/IDG/RemoteError";
			String AUTH_FAULT = "/ErrorMessage/IDG/AuthFault";
		}

		public interface AJAX {
			public interface QuickSearchSymbol {
				String SYMBOL_INCORRECT = "/ErrorMessage/AJAX/QuickSearchSymbol/symbol-incorrect";
			}
		}

		public interface FLASH {
			String INVALID_METHOD = "ErrorMessage.FLASH.InvalidMethod";
			String INVALID_PERIODICITY = "ErrorMessage.FLASH.InvalidPeriodicity";
			String INVALID_DATEFORMAT = "ErrorMessage.FLASH.InvalidDateFormat";
			String INVALID_SYMBOL = "ErrorMessage.FLASH.InvalidSymbol";
			String INVALID_INDICATOR = "ErrorMessage.FLASH.InvalidIndicator";
		}

		/**
		 * Define error keys for Home section
		 * 
		 * @author tungnq
		 * 
		 */
		public interface Home {
			interface OnlineAccount {
				String FIELD_INVALID = "/ErrorMessage/Home/OnlineAccount/fields-invalid";
				String FIELD_REQUIRED = "/ErrorMessage/Home/OnlineAccount/fields-required";
				String AGE_INVALID = "/ErrorMessage/Home/OnlineAccount/age-invalid";
				String INVALID_BETWEEN_DOB_DOI = "/ErrorMessage/Home/OnlineAccount/invalid-between-dob-doi";
			}
		}

		/**
		 * Define error for Account module
		 * 
		 * @author tungnq
		 * 
		 */
		public interface Account {
		}

		/**
		 * Define error keys for Trading module
		 * 
		 * @author tungnq
		 * 
		 */
		public interface Trading {
			String REMOTE_ERROR = "/ErrorMessage/BOService/RemoteError";
			String REMOTE_PLACE_ORDER_ERROR = "/ErrorMessage/BOService/RemoteError-PlaceOrder";
			String SESSION_ID_NULL = "/ErrorMessage/BOService/SessionIdNull";
			String ONLINE_CUSTOMER_ID_NULL = "/ErrorMessage/BOService/Online-CustomerIDNull";
			String WEBAGENT_CUSTOMER_ID_NULL = "/ErrorMessage/BOService/WebAgent-CustomerIDNull";
			String PORTFOLIO_SYMBOL_NAME_EMPTY = "/ErrorMessage/Trading/Porfolio/CreateNewProfolio/SymbolNameEmpty";
			String PORTFOLIO_SYMBOL_EMPTY = "/ErrorMessage/Trading/Porfolio/CreateNewProfolio/SymbolEmpty";
			String PORTFOLIO_PRICE_INVALID = "/ErrorMessage/Trading/Porfolio/CreateNewProfolio/PriceInvalidNumber";
			String PORTFOLIO_SHARE_INVALID = "/ErrorMessage/Trading/Porfolio/CreateNewProfolio/ShareInvalidNumber";
			String PORTFOLIO_SYMBOL_INVALID = "/ErrorMessage/Trading/Porfolio/CreateNewProfolio/SymbolInvalid";
			String PORTFOLIO_SYMBOL_UNCHECK = "/ErrorMessage/Trading/Porfolio/CreateNewProfolio/UncheckToDelete";
			String PORTFOLIO_UNCHECK = "/ErrorMessage/Trading/Porfolio/CreateNewProfolio/UncheckToDeleteMyPort";
		}

		public interface AlertSetting {
			String PER_HIGH_TARGET_INVALID = "/ErrorMessage/Quotes/Alert/PerHighTarget_Invalid";
			String PER_LOW_TARGET_INVALID = "/ErrorMessage/Quotes/Alert/PerLowTarget_Invalid";
			String HIGH_TARGET_INVALID = "/ErrorMessage/Quotes/Alert/HighTarget_Invalid";
			String LOW_TARGET_INVALID = "/ErrorMessage/Quotes/Alert/LowTarget_Invalid";
			String VOLUME_INVALID = "/ErrorMessage/Quotes/Alert/Volume_Invalid";
		}

		/**
		 * Define error keys for Quotes & Research module.
		 * 
		 * @author tungnq
		 * 
		 */
		public interface Quotes {
		}
	}

	public interface WebStatistic {
		String VISTED = "VISTED";

		// ++++ get this information from application context
		String CURRENT_ONLINE = "ONLINE";
		String CURRENT_GUEST = "GUEST";
	}

	public interface MessageStatusCode {
		String ERROR = "-1";
		String OK = "1";
	}

	public interface MessageStatus {
		String OK = "OK";
		String SUCCESS = "0";
	}

	public interface FunctionalExceptionCode {
		int OK = 0;

		public interface ChangePassword {
			int WRONG_PASSWORD = 1;
		}
	}

	public interface CommonType {
		String TRUE_VALUE = "TRUE";
		String FALSE_VALUE = "FALSE";
	}

	/**
	 * The action type transfer to service.
	 * 
	 * @author thanh.vu
	 * 
	 */
	public interface ActionType {
		// signal Add New
		final int NEW = 1;
		// signal Update
		final int UPDATE = 2;
	}

	public interface ShortExchangeCode {
		String HA = "HA";
		String HO = "HO";
		String OTC = "OTC";
		String UPCOM = "UPCOM";
		String VN30 = "VN30";
		String HNX30 = "HNX30";
	}

	public interface IServerConfig {
		public interface CASServiceTicket {
			String CAS_SERVICES_URL = "/server-config/CASServices";
		}

		/**
		 * Define money rate for Finance report
		 * 
		 * @author DucNM
		 * 
		 */
		public static final String MONEY_RATE = "/server-config/MoneyRate";
		String FORMAT_DATE = "/server-config/FormatDate";
		String VERISIGN_URL = "/server-config/VeriSign-url";
		String VNDS_RESOURCE_DOWNLOADER = "/server-config/VNDSResourceDownloader";
		String CUSTOMER_RESOURCE_DOWNLOADER = "/server-config/CustomerResourceDownloader";
		String NUMBER_OF_RELATED_COMPANIES = "/server-config/number-of-related-companies";
		Integer STOCK_HIGHLIGHTS_SYSDATE_FROM = ServerConfig.getOnlineIntValue("/server-config/stockHighlightsSysdateFrom");
		String STOCK_HIGHLIGHTS_FREE_REPORTS = ServerConfig.getOnlineValue("/server-config/stockHighlightsFreeReports");

		String WEB_RESOURCE_DOWNLOAD = "/server-config/WebResourceDownload";
		String WEB_RESOURCE_DOWNLOAD_THUMBNAIL = "/server-config/WebResourceDownloadThumbnail";

		public interface Audit {
			String SEARCH_IN_DAY = "/server-config/audit/search-in-day";
			String SEARCH_IN_WEEK = "/server-config/audit/search-in-week";
			String SEARCH_IN_MONTH = "/server-config/audit/search-in-month";
			String SEARCH_IN_YEAR = "/server-config/audit/search-in-year";
			String NUMBER_OF_QUOTES = "/server-config/audit/number-of-quotes";
			String SEARCH_SYMBOL_TYPE = "/server-config/audit/search-symbol-type";
		}

		public interface DefaulValueInSearchingPage {
			String NUMBER_OF_MONTHS = "/server-config/default-values-in-seaching/number-of-months";
		}

		public interface DeploymentModel {
			String ONE_VNDIRECT = "/server-config/deployment-model/one-vndirect";
			String INSTANCE_FOR = "/server-config/deployment-model/instance-for";
			String WEBAGENT_MODEL = "/server-config/deployment-model/models/webagent";
			String TRAING_ONLINE_MODEL = "/server-config/deployment-model/models/trading-online";
		}

		public interface Paging {
			String ITEMS_PER_PAGE = "/server-config/paging/items-per-page";
			String NUM_PER_PAGE = "/server-config/paging/num-per-page";
			String ITEMS_PER_LIST = "/server-config/paging/items-per-list";
			String ITEMS_OF_VNDS = "/server-config/paging/items-of-vnds";
			String ITEMS_OF_RESEARCH = "/server-config/paging/items-of-research";
			String ITEMS_OF_AGENT_REPORT = "/server-config/paging/items-of-agent-report";
		}

		public interface HttpSSLConfig {
			String ENABEDED = "/server-config/http-ssl-config/enableded";
			String HTTP_PORT = "/server-config/http-ssl-config/http-port";
			String HTTPS_PORT = "/server-config/http-ssl-config/https-port";
		}

		public interface FileServer {
			String FILE_URL = "/server-config/files-server/upload-url";
			String RESEARCH_FILE_URL = "/server-config/files-server/research-url";
			String FORM_APP_FILE_URL = "/server-config/files-server/form-app-url";
			String NEWS_ATTACHMENT_FILE_URL = "/server-config/files-server/news-attachment-url";
			String CACHE_URL = "/server-config/files-server/cache-url";
			String CACHE_CHART_URI = "/server-config/files-server/uri/chart-cache";

			public interface Reports {
				String BALANCES_URL_PATTERN = "/server-config/files-server/reports/balances";
				String STATEMENTS_URL_PATTERN = "/server-config/files-server/reports/statements";
				String TRADE_CONFIRMATIONS_URL_PATTERN = "/server-config/files-server/reports/trade-confirmations";
			}

			public interface StaticPages {
				String USING_WEBCONTEXT = "/server-config/files-server/static-pages/using-webcontext";
				String URI = "/server-config/files-server/static-pages/uri";
			}

			public interface OSCache {
				String USING_CACHE_STATIC_PAGES = "/server-config/oscache/cache-static-page";
				String STATIC_PAGES_REFRESH_PERIOD = "/server-config/oscache/static-page-refresh-period";
				String CHARTS_REFRESH_PERIOD = "/server-config/oscache/chart-refresh-period";
				String TACHARTS_REFRESH_PERIOD = "/server-config/oscache/tachart-refresh-period";
				String LATEST_NEWS_REFRESH_PERIOD = "/server-config/oscache/latest-news-refresh-period";
				String INDUSTRY_CENTER_REFRESH_PERIOD = "/server-config/oscache/industry-center-refresh-period";
				String FLASH_TACHARTS_REFRESH_PERIOD = "/server-config/oscache/flash-tachart-refresh-period";
				String FLASH_EVENTS_REFRESH_PERIOD = "/server-config/oscache/flash-events-refresh-period";
				String DATA_REF_REFRESH_PERIOD = "/server-config/oscache/data-ref-refresh-period";
				String PORFOLIO_REFRESH_PERIOD = "/server-config/oscache/porfolio-refresh-period";
			}

			public interface DownloadTemplate {
				String URI = "/server-config/files-server/download-template/uri";
			}
		}

		public interface PagingInfo {
			String OFFSET_NUMBER = "/server-config/PagingInfo/OffsetNumber";
		}

		public interface ErrorMessage {
			String ERROR_STRUCTURE = "/server-config/error-message/error-structure";
			String ERROR_DETAIL = "/server-config/error-message/error-detail";
		}

		public interface MarketSummary {
			String NUMBER_STOCK_OF_HASTC = "/server-config/MarketSummary/NumberStockHASTC";
			String NUMBER_STOCK_OF_HOSTC = "/server-config/MarketSummary/NumberStockHOSTC";
			String NUMBER_STOCK_OF_OTC = "/server-config/MarketSummary/NumberStockOTC";
		}

		/**
		 * Define ExchangeCode & Symbol of VNMarket
		 * 
		 * @author tungnq
		 * 
		 */
		public interface VNMarket {
			String MARKET_UNIT_PRICE = "/server-config/VNMarket/MarketUnitPrice";

			/**
             *
             */
			public interface TransferTime {
				String OPEN_TIME = "/server-config/VNMarket/TransferTime/OpenTime";
				String CLOSE_TIME = "/server-config/VNMarket/TransferTime/CloseTime";
			}

			public interface TransferMoney {
				String MIN_TRANSFER_ALLOW = "/server-config/MinimunTransferAllow";
			}

			public interface PaymentAdvanceTime {
				String PAYMENT_ADVANCE_TIME = "/server-config/VNMarket/PaymentAdvanceTime";
			}

			public interface ExchangeCode {
				String HANOI_STC_INDEX = "/server-config/VNMarket/ExchangeCode/HanoiSTC";
				String HCM_STC_INDEX = "/server-config/VNMarket/ExchangeCode/HCMSTC";
				String VN30_INDEX = "/server-config/VNMarket/ExchangeCode/VN30";
				String HNX30_INDEX = "/server-config/VNMarket/ExchangeCode/HNX30";
				String OTC_INDEX = "/server-config/VNMarket/ExchangeCode/OTC";
				String UPCOM_INDEX = "/server-config/VNMarket/ExchangeCode/UPCOM";
			}

			public interface IndexCode {
				String HANOI_STC_INDEX = "/server-config/VNMarket/IndexCode/HanoiSTC";
				String HCM_STC_INDEX = "/server-config/VNMarket/IndexCode/HCMSTC";
				String VN30_INDEX = "/server-config/VNMarket/IndexCode/VN30";
				String HNX30_INDEX = "/server-config/VNMarket/IndexCode/HNX30";
				String OTC_INDEX = "/server-config/VNMarket/IndexCode/OTC";
				String UPCOM_INDEX = "/server-config/VNMarket/IndexCode/UPCOM";
				String LIST_INDEX = "/server-config/VNMarket/IndexCode/IndexList";

				String DOW_JONE_INDEX = "/server-config/VNMarket/IndexCode/DOWJONE";
				String NASDAQ_INDEX = "/server-config/VNMarket/IndexCode/NASDAQ";
				String SP_500_INDEX = "/server-config/VNMarket/IndexCode/SP500";
				String LIST_WORLD_INDEX = "/server-config/VNMarket/IndexCode/WorldIndexList";
			}

			public interface FloorCode {
				String HANOI_STC_INDEX = "/server-config/VNMarket/FloorCode/HanoiSTC";
				String HCM_STC_INDEX = "/server-config/VNMarket/FloorCode/HCMSTC";
				String VN30_INDEX = "/server-config/VNMarket/FloorCode/VN30";
				String HNX30_INDEX = "/server-config/VNMarket/FloorCode/HNX30";
				String OTC_INDEX = "/server-config/VNMarket/FloorCode/OTC";
				String UPCOM_INDEX = "/server-config/VNMarket/FloorCode/UPCOM";
			}

			public interface UpdateCancelTime {
				public interface SendingOrder {
					String OPEN_TIME = "/server-config/VNMarket/UpdateCancelTime/SendingOrder/OpenTime";
					String CLOSE_TIME = "/server-config/VNMarket/UpdateCancelTime/SendingOrder/CloseTime";
				}

				public interface ForbiddenCancelATO {
					String OPEN_TIME = "/server-config/VNMarket/UpdateCancelTime/ForbiddenCancelATO/OpenTime";
					String CLOSE_TIME = "/server-config/VNMarket/UpdateCancelTime/ForbiddenCancelATO/CloseTime";
				}
			}

			public interface TradingTime {
				String HOLIDAY = "/server-config/VNMarket/TradingTime/Holiday";
				String ADVANCE_PLACE_ORDER_TIME = "/server-config/VNMarket/TradingTime/AdvancePlaceOrderTime";

				public interface HanoiSTC {
					String OPEN_TIME = "/server-config/VNMarket/TradingTime/HanoiSTC/OpenTime";
					String CLOSE_TIME = "/server-config/VNMarket/TradingTime/HanoiSTC/CloseTime";
					String PLACE_ORDER_OPEN_TIME = "/server-config/VNMarket/TradingTime/HanoiSTC/PlaceOrderOpenTime";
					String PLACE_ORDER_CLOSE_TIME = "/server-config/VNMarket/TradingTime/HanoiSTC/PlaceOrderCloseTime";

					public interface Session {
						public interface P {
							String OPEN_TIME = "/server-config/VNMarket/TradingTime/HanoiSTC/Sessions/P/OpenTime";
							String CLOSE_TIME = "/server-config/VNMarket/TradingTime/HanoiSTC/Sessions/P/CloseTime";
							String ORDER_TYPES = "/server-config/VNMarket/TradingTime/HanoiSTC/Sessions/P/OrderTypes";
						}
					}
				}

				public interface HCMSTC {
					String OPEN_TIME = "/server-config/VNMarket/TradingTime/HCMSTC/OpenTime";
					String CLOSE_TIME = "/server-config/VNMarket/TradingTime/HCMSTC/CloseTime";
					String PLACE_ORDER_OPEN_TIME = "/server-config/VNMarket/TradingTime/HCMSTC/PlaceOrderOpenTime";
					String PLACE_ORDER_CLOSE_TIME = "/server-config/VNMarket/TradingTime/HCMSTC/PlaceOrderCloseTime";

					public interface Session {
						public interface P1 {
							int NUMBER = 1;
							String OPEN_TIME = "/server-config/VNMarket/TradingTime/HCMSTC/Sessions/P1/OpenTime";
							String CLOSE_TIME = "/server-config/VNMarket/TradingTime/HCMSTC/Sessions/P1/CloseTime";
							String ORDER_TYPES = "/server-config/VNMarket/TradingTime/HCMSTC/Sessions/P1/OrderTypes";
						}

						public interface P2 {
							int NUMBER = 2;
							String OPEN_TIME = "/server-config/VNMarket/TradingTime/HCMSTC/Sessions/P2/OpenTime";
							String CLOSE_TIME = "/server-config/VNMarket/TradingTime/HCMSTC/Sessions/P2/CloseTime";
							String ORDER_TYPES = "/server-config/VNMarket/TradingTime/HCMSTC/Sessions/P2/OrderTypes";
						}

						public interface P3 {
							int NUMBER = 3;
							String OPEN_TIME = "/server-config/VNMarket/TradingTime/HCMSTC/Sessions/P3/OpenTime";
							String CLOSE_TIME = "/server-config/VNMarket/TradingTime/HCMSTC/Sessions/P3/CloseTime";
							String ORDER_TYPES = "/server-config/VNMarket/TradingTime/HCMSTC/Sessions/P3/OrderTypes";
						}
					}
				}
			}

			public String CHART_DYNAMIC_PRICE_TIME = "/server-config/VNMarket/ChartDynamicPriceTime";
		}

		public interface TradingAuth {
			String LDAP = "/server-config/Trading-Authentication/LDAP";
			String IDENTITY_GUARD_AUTH = "/server-config/Trading-Authentication/IdentityGuardAuth";
		}

		/**
		 * Define type for Finance report
		 * 
		 * @author DucNM
		 * 
		 */
		public interface ReportType {
			String ANNUAL = "/server-config/ReportType/Annual";
			String QUARTER = "/server-config/ReportType/Quarter";
		}

		/**
		 * Define types for FinancialHighlight report
		 * 
		 * @author ThachPN
		 * 
		 */
		public interface FinancialHighlightType {
			String INCOME_PATH = "/server-config/FinancialHighlight/Income";
			String BALANCE_PATH = "/server-config/FinancialHighlight/BalanceSheet";
			String CASHFLOW_PATH = "/server-config/FinancialHighlight/CashFlow";
		}

		/**
		 * 
		 * @author tungnq
		 * 
		 */
		public interface InfoMessage {
			String INFO_STRUCTURE = "/server-config/info-message/info-structure";
			String INFO_DETAIL = "/server-config/info-message/info-detail";
		}

		/**
		 * 
		 * @author tungnq
		 * 
		 */
		public interface Trading {
			String RESULT_PER_PAGE = "/server-config/Trading/ResultPerPage";

			public interface PlaceOrder {
				String FACTOR_FEE = "/server-config/Trading/PlaceOrder/FactorFee";
				String LOW_LIMIT_FEE = "/server-config/Trading/PlaceOrder/LowLimitFee";
				String UNIT_PRICE = "/server-config/Trading/PlaceOrder/UnitPrice";
				String DUPLICATED_ORDER_ERROR_CODE = "/server-config/Trading/PlaceOrder/DuplidatedOrderErrorCode";
				String OPTIMIZE_FLG = "/server-config/Trading/PlaceOrder/optimize-flg";
				String MAX_EXPIRED_DATE_DURATION = "/server-config/Trading/PlaceOrder/MaxExpiredDateDuration";
				String GTC_FOR_HNX_FLG = "/server-config/Trading/PlaceOrder/gtc_for_hnx_flg";
				String PRE_ORDER_FOR_HNX_FLG = "/server-config/Trading/PlaceOrder/pre_order_for_hnx_flg";
			}

			public interface BOCode {
				String ATO = "/server-config/Trading/BOCode/ATO";
				String ATC = "/server-config/Trading/BOCode/ATC";
				String LO = "/server-config/Trading/BOCode/LO";
			}

			public interface Search {
				String FROM_YEAR = "/server-config/Trading/Search/FromYear";
				String TO_YEAR = "/server-config/Trading/Search/ToYear";
			}

			public interface ExchangeCode {
				String HANOI_STC_INDEX = "/server-config/Trading/ExchangeCode/HanoiSTC";
				String HCM_STC_INDEX = "/server-config/Trading/ExchangeCode/HCMSTC";
				String OTC_INDEX = "/server-config/Trading/ExchangeCode/OTC";
				String UPCOM_INDEX = "/server-config/Trading/ExchangeCode/UPCOM";
			}
		}

		/**
		 * 
		 * @author hanhbq
		 * 
		 */
		public interface CoreBank {
			String ONLINE_ACC_TYPE = "/server-config/core-bank/type/online-account";
			String BANK_ACC_TYPE = "/server-config/core-bank/type/bank-account";
		}

		public interface Signature {
			String MAX_SIZE = "/server-config/signature/max-size";
			String EXTEND_ALLOW = "/server-config/signature/extend-support";
		}

		public interface SearchStockPurchaseOpt {
			String ALL_TYPE = "/server-config/SearchStockPurchaseOption/Type_All";
			String ALL_STATUS = "/server-config/SearchStockPurchaseOption/Status_All";
		}

		public interface OpenOnlineUser {
			public interface ActiveStatus{
				String DEFAULT = "/server-config/open-account/active-status/default";
				String PENDING = ServerConfig.getOnlineValue("/server-config/open-account/active-status/pending");
				String DENIED = "/server-config/open-account/active-status/denied";
				String ERROR = ServerConfig.getOnlineValue("/server-config/open-account/active-status/error");
				String PROCESSING = ServerConfig.getOnlineValue("/server-config/open-account/active-status/processing");
				String FINISH = ServerConfig.getOnlineValue("/server-config/open-account/active-status/finish");
			}
		}
		
		/**
		 * 
		 * @author tungnq
		 * 
		 */
		public interface DataRef {
			/**
			 * 
			 * @author tungnq
			 * 
			 */
			public interface GroupCodes {
				String USER_ROLE = "/server-config/data-ref/group-codes/user-role";
				String USER_STATUS = "/server-config/data-ref/group-codes/user-status";
				String ONLINE_USER_STATUS = "/server-config/data-ref/group-codes/online-user-status";
				String MASTER_LINK = "/server-config/data-ref/group-codes/master-link";
				String MASTER_STATUS = "/server-config/data-ref/group-codes/master-status";
				String FORM_APP_TYPE = "/server-config/data-ref/group-codes/form-app-type";
				String FORM_APP_STATUS = "/server-config/data-ref/group-codes/form-app-status";
				String DOCUMENT_STATUS = "/server-config/data-ref/group-codes/document-status";
				String FORUM_STATUS = "/server-config/data-ref/group-codes/forum-status";
				String NEWS_STATUS = "/server-config/data-ref/group-codes/news-status";
				String NEWS_TYPE = "/server-config/data-ref/group-codes/news-type";
				String LANGUAGE = "/server-config/data-ref/group-codes/language";
				String SEX = "/server-config/data-ref/group-codes/sex";
				String MARRIED_STATUS = "/server-config/data-ref/group-codes/married_status";
				String PROVINCE = "/server-config/data-ref/group-codes/province";
				String COUNTRY = "/server-config/data-ref/group-codes/country";
				String EDUCATION_LEVEL = "/server-config/data-ref/group-codes/education_level";
				String OCCUPATION = "/server-config/data-ref/group-codes/occupation";
				String TOTAL_ASSERT = "/server-config/data-ref/group-codes/total_assert";
				String IDENTIFICATION_TYPE = "/server-config/data-ref/group-codes/identification_type";
				String BUSINESS_TYPE = "/server-config/data-ref/group-codes/business_type";
				String ECONOMIC_SECTOR = "/server-config/data-ref/group-codes/economic_sector";
				String ACCOUNT_TYPE = "/server-config/data-ref/group-codes/account_type";
				String ACCOUNT_STATUS = "/server-config/data-ref/group-codes/account_status";
				String TRANSACTION_TYPE = "/server-config/data-ref/group-codes/transaction_type";
				String ORDER_TYPE = "/server-config/data-ref/group-codes/order-type";
				String ORDER_PRICE_TYPE = "/server-config/data-ref/group-codes/order-price-type";
				String ORDER_TERM = "/server-config/data-ref/group-codes/order-term";
				String ORDER_STATUS = "/server-config/data-ref/group-codes/order-status";
				String ORDER_MEANS = "/server-config/data-ref/group-codes/order-means";
				String SEARCH_DATE_TYPE = "/server-config/data-ref/group-codes/search-date-type";
				String SEARCH_EVENT_TYPE = "/server-config/data-ref/group-codes/search-event-type";
				String SEARCH_TRADING_TRANS_TYPE = "/server-config/data-ref/group-codes/search-trading-transaction-type";
				String SEARCH_TRADING_TRANS_MONEY_TYPE = "/server-config/data-ref/group-codes/search-trading-transaction-money-type";
				String SEARCH_TRADING_TRANS_SYMBOL_TYPE = "/server-config/data-ref/group-codes/search-trading-transaction-symbol-type";
				String TRADING_TRANS_TYPE = "/server-config/data-ref/group-codes/trading-transaction-type";

				String QUARTER_TYPE = "/server-config/data-ref/group-codes/quarter-type";

				// 15 Feb 2008 Add CF-STATUS:
				String CF_STATUS = "/server-config/data-ref/group-codes/customer-contract-status";

				// HanhBQ add agent code//01-11-2007//
				String BPS_TRANSACTION_DES = "/server-config/data-ref/group-codes/bps-transaction-desc";

				// CUSTOMER
				String AGENT_CUST_IDTYPE = "/server-config/data-ref/group-codes/agent-cust-IDType";
				String AGENT_CUST_TYPE = "/server-config/data-ref/group-codes/agent-cust-type";
				String AGENT_CUST_MARRIED_STATUS = "/server-config/data-ref/group-codes/agent-cust-married-status";
				String AGENT_CUST_RESIDENT = "/server-config/data-ref/group-codes/agent-cust-resident";
				String AGENT_CUST_SEX = "/server-config/data-ref/group-codes/agent-cust-sex";
				String AGENT_CUST_STAFF = "/server-config/data-ref/group-codes/agent-cust-staff";
				String AGENT_CUST_POSITION = "/server-config/data-ref/group-codes/agent-cust-position";
				String AGENT_CUST_BUSINESS_TYPE = "/server-config/data-ref/group-codes/agent-cust-business-type";
				String AGENT_CUST_INVEST_TYPE = "/server-config/data-ref/group-codes/agent-cust-invest-type";
				String AGENT_CUST_GROUP_INVESTOR = "/server-config/data-ref/group-codes/agent-cust-group-investor";
				String AGENT_CUST_INCOME_RANGE = "/server-config/data-ref/group-codes/agent-cust-income-range";
				String AGENT_CUST_INVEST_RANGE = "/server-config/data-ref/group-codes/agent-cust-invest_range";
				String AGENT_CUST_TIME_2_JOINT = "/server-config/data-ref/group-codes/agent-cust-time-2-joint";
				String AGENT_CUST_CLASS = "/server-config/data-ref/group-codes/agent-cust-class";
				String AGENT_CUST_EXPERIENCE_TYPE = "/server-config/data-ref/group-codes/agent-cust-experience-type";
				String AGENT_CUST_FOCUS_TYPE = "/server-config/data-ref/group-codes/agent-cust-focus-type";
				String AGENT_CUST_ASSET_RANGE = "/server-config/data-ref/group-codes/agent-cust-asset-range";
				String AGENT_CUST_SECTOR = "/server-config/data-ref/group-codes/agent-cust-sector";
				String AGENT_CUST_EDUCATION = "/server-config/data-ref/group-codes/agent-cust-education";
				String AGENT_CUST_OCCUPTATION = "/server-config/data-ref/group-codes/agent-cust-occuptation";

				// CONTRACT
				String AGENT_CONTR_BANKNAME = "/server-config/data-ref/group-codes/agent-cont-bank-name";
				String AGENT_CUST_AFTYPE = "/server-config/data-ref/group-codes/agent-cust-af-type";

				// --HanhBQ add agent code//01-11-2007//

				// transac type
				// String HISTORY_TRANSACTION_STOCK_CODE =
				// "/server-config/data-ref/history-trans-group/stock-code";
				// String HISTORY_TRANSACTION_STOCK_VALUE =
				// "/server-config/data-ref/history-trans-group/stock-value";

				// Sao ke tien
				String HISTORY_TRANSACTION_MONEY_BUY_SELL_CODE = "/server-config/data-ref/history-trans-group/transaction-money-buy-sell-code";
				String HISTORY_TRANSACTION_MONEY_BUY_SELL_VALUE = "/server-config/data-ref/history-trans-group/transaction-money-buy-sell-value";
				String HISTORY_TRANSACTION_MONEY_CREDIT_DEPOSIT_CODE = "/server-config/data-ref/history-trans-group/transaction-money-credit-deposit-code";
				String HISTORY_TRANSACTION_MONEY_CREDIT_DEPOSIT_VALUE = "/server-config/data-ref/history-trans-group/transaction-money-credit-deposit-value";
				String HISTORY_TRANSACTION_MONEY_STOCK_OPTION_CODE = "/server-config/data-ref/history-trans-group/transaction-money-stock-option-code";
				String HISTORY_TRANSACTION_MONEY_STOCK_OPTION_VALUE = "/server-config/data-ref/history-trans-group/transaction-money-stock-option-value";

				// Sao ke chung khoan
				String HISTORY_TRANSACTION_SYMBOL_BUY_SELL_CODE = "/server-config/data-ref/history-trans-group/transaction-symbol-buy-sell-code";
				String HISTORY_TRANSACTION_SYMBOL_BUY_SELL_VALUE = "/server-config/data-ref/history-trans-group/transaction-symbol-buy-sell-value";
				String HISTORY_TRANSACTION_SYMBOL_CREDIT_DEPOSIT_CODE = "/server-config/data-ref/history-trans-group/transaction-symbol-credit-deposit-code";
				String HISTORY_TRANSACTION_SYMBOL_CREDIT_DEPOSIT_VALUE = "/server-config/data-ref/history-trans-group/transaction-symbol-credit-deposit-value";
				String HISTORY_TRANSACTION_SYMBOL_STOCK_OPTION_CODE = "/server-config/data-ref/history-trans-group/transaction-symbol-stock-option-code";
				String HISTORY_TRANSACTION_SYMBOL_STOCK_OPTION_VALUE = "/server-config/data-ref/history-trans-group/transaction-symbol-stock-option-value";

				// 06 dec 2008 CA code: type-status
				String CA_TYPE = "/server-config/data-ref/group-codes/CA-type";
				String CA_STATUS = "/server-config/data-ref/group-codes/CA-status";
			}
			
			/**
			 * 
			 * @author tungnq
			 * 
			 */
			public interface ItemCodes {
				public interface UserRoles {
					String STAFF = "/server-config/data-ref/item-codes/user-role/staff";
					String CONENT_MANAGER = "/server-config/data-ref/item-codes/user-role/conent-manager";
					String ADMINSTRATOR = "/server-config/data-ref/item-codes/user-role/adminstrator";
				}

				public interface UserStatus {
					String DISABLED = "/server-config/data-ref/item-codes/user-status/disabled";
					String LOCKED = "/server-config/data-ref/item-codes/user-status/locked";
					String ACTIVE = "/server-config/data-ref/item-codes/user-status/active";
				}

				public interface OnlineUserStatus {
					String ONLINE_DISABLED = "/server-config/data-ref/item-codes/online-user-status/online-disabled";
					String ONLINE_ACTIVE = "/server-config/data-ref/item-codes/online-user-status/online-active";
					String ONLINE_FREE_REGISTER = "/server-config/data-ref/item-codes/online-user-status/online-free-register";
				}

				public interface MarsterLinkType {
					String CLIENT = "/server-config/data-ref/item-codes/master-link/client";
					String CORPORATE = "/server-config/data-ref/item-codes/master-link/corporate";
					String INDIVIDUAL = "/server-config/data-ref/item-codes/master-link/individual";
					String COMPANY = "/server-config/data-ref/item-codes/master-link/company";
				}

				public interface MasterLinkStatus {
					String UNPUBLISHED = "/server-config/data-ref/item-codes/master-status/unpublished";
					String PUBLISHED = "/server-config/data-ref/item-codes/master-status/published";
				}

				public interface FormAppType {
					String DISTRIBUTION = "/server-config/data-ref/item-codes/form-app-type/distribution";
					String TAX = "/server-config/data-ref/item-codes/form-app-type/tax";
					String AUTHORIZATION = "/server-config/data-ref/item-codes/form-app-type/authorization";
					String APPLICATION = "/server-config/data-ref/item-codes/form-app-type/application";
				}

				public interface FormAppStatus {
					String UNPUBLISHED = "/server-config/data-ref/item-codes/form-app-status/unpublished";
					String PUBLISHED = "/server-config/data-ref/item-codes/form-app-status/published";
				}

				public interface DocStatus {
					String UNPUBLISHED = "/server-config/data-ref/item-codes/document-status/unpublished";
					String PUBLISHED = "/server-config/data-ref/item-codes/document-status/published";
				}

				public interface ForumStatus {
					String UNPUBLISHED = "/server-config/data-ref/item-codes/forum-status/unpublished";
					String PUBLISHED = "/server-config/data-ref/item-codes/forum-status/published";
				}

				public interface NewsStatus {
					String REJECTED = "/server-config/data-ref/item-codes/news-status/rejected";
					String APPROVED = ServerConfig.getOnlineValue("/server-config/data-ref/item-codes/news-status/approved");
					String PENDING_FOR_APPROVAL = "/server-config/data-ref/item-codes/news-status/pending-for-approval";
					String WORK_IN_PROGRESS = "/server-config/data-ref/item-codes/news-status/work-in-progress";
				}

				public interface NewsType {
					String EVENT = "/server-config/data-ref/item-codes/news-type/event";
					String NEWS = "/server-config/data-ref/item-codes/news-type/news";
					String IPO_NEWS = "/server-config/data-ref/item-codes/news-type/ipo-news";
					String MARKET_NEWS = "/server-config/data-ref/item-codes/news-type/market-news";
					String PUBLIC_INFO = "/server-config/data-ref/item-codes/news-type/public-info";
					String VNDS_NEWS = "/server-config/data-ref/item-codes/news-type/vnds-news";
					String NOT_VNDS_NEWS = "/server-config/data-ref/item-codes/news-type/not-vnds-news";
					String OTC_NEWS = "/server-config/data-ref/item-codes/news-type/otc-news";
					String MARKET_CALENDAR = "/server-config/data-ref/item-codes/news-type/market-calendar";
					String VNDS_RESEARCH_NEWS = "/server-config/data-ref/item-codes/news-type/vnds-research-news";
					String VNDS_RESEARCH_MARKET_NEWS = "/server-config/data-ref/item-codes/news-type/vnds-research-news_market";
					String MARTKET_REVIEW = "/server-config/data-ref/item-codes/news-type/market-review";

					String WORLD_NEWS = "/server-config/data-ref/item-codes/news-type/world-news";
					String EXPERT_NEWS = "/server-config/data-ref/item-codes/news-type/expert-news";
					String SHAREHOLDER_NEWS = "/server-config/data-ref/item-codes/news-type/shareholder-news";
					String IS_HOT_NEWS = "/server-config/data-ref/item-codes/news-type/is-hot-news";

					String VNDS_DAILY_NEWS = ServerConfig.getOnlineValue("/server-config/data-ref/item-codes/news-type/vnds-daily-news");
					String VNDS_WEEKLY_NEWS = "/server-config/data-ref/item-codes/news-type/vnds-weekly-news";
					String VNDS_MONTHLY_NEWS = "/server-config/data-ref/item-codes/news-type/vnds-monthly-news";
					String VNDS_STRATEGIC = "/server-config/data-ref/item-codes/news-type/vnds-strategic";
					String VNDS_COMPANY = "/server-config/data-ref/item-codes/news-type/vnds-company";
					String VNDS_QUARTER = "/server-config/data-ref/item-codes/news-type/vnds-quarter";
					String VNDS_RISKALERT = "/server-config/data-ref/item-codes/news-type/vnds-riskalert";
					String VNDS_QUICKREPORT = "/server-config/data-ref/item-codes/news-type/vnds-quickreport";
					String VNDS_STASTIC = "/server-config/data-ref/item-codes/news-type/vnds-stastic";

					String VNDS_RESEARCH_OTHER = "/server-config/data-ref/item-codes/news-type/vnds-research-other";

					String VNDS_ANNOUNCEMENT = "/server-config/data-ref/item-codes/news-type/vnds-announcement";
					String VNDS_FINANCIAL_STATEMENT = "/server-config/data-ref/item-codes/news-type/vnds-financial-statement";

					String VNDS_STOCK_HIGHLIGHTS = ServerConfig.getOnlineValue("/server-config/data-ref/item-codes/news-type/vnds-stock-highlights");
					String VNDS_STOCK_PICK = ServerConfig.getOnlineValue("/server-config/data-ref/item-codes/news-type/vnds-stock-pick");
				}

				public interface DocType {
					String RESEARCH = "/server-config/data-ref/item-codes/doc-type/research";
					String SSC_FILLING = "/server-config/data-ref/item-codes/doc-type/ssc-filling";
				}

				public interface OnlineAccountStatus {
					String FINISH = "/server-config/data-ref/item-codes/account-status/finish";
					String PENDING = "/server-config/data-ref/item-codes/account-status/pending";
					String PROCESSING = "/server-config/data-ref/item-codes/account-status/processing";
				}

				public interface Order {
					public interface Type {
						String NORMAL_BUY = "/server-config/data-ref/item-codes/order/order-type/normal-buy";
						String NORMAL_SELL = "/server-config/data-ref/item-codes/order/order-type/normal-sell";

						// MS
						String MORTGAGE_SELL = "/server-config/data-ref/item-codes/order/order-type/mortgage-sell";
						String SHORT_SALE = "/server-config/data-ref/item-codes/order/order-type/short-sale";
						String BUY_TO_COVER = "/server-config/data-ref/item-codes/order/order-type/buy-to-cover";
					}

					public interface PriceType {
						String LIMIT_ORDER = "/server-config/data-ref/item-codes/order/order-price-type/limit-order";
						String MARKET_ORDER = "/server-config/data-ref/item-codes/order/order-price-type/market-order";
						String ATO = "/server-config/data-ref/item-codes/order/order-price-type/ATO";
						String ATC = "/server-config/data-ref/item-codes/order/order-price-type/ATC";
						String MP = "/server-config/data-ref/item-codes/order/order-price-type/MP";
					}

					public interface Term {
						String TODAY = "/server-config/data-ref/item-codes/order/order-term/today";
						String GOOD_TILL_CANCEL_ = "/server-config/data-ref/item-codes/order/order-term/good-till-cancel-";
						// String IMMEDIATE_OR_CANCEL =
						// "/server-config/data-ref/item-codes/order/order-term/immediate-or-cancel";
					}

					public interface Status {
						String OPEN = "/server-config/data-ref/item-codes/order/order-status/open";
						String SENT = "/server-config/data-ref/item-codes/order/order-status/sent";
						String CANCEL = "/server-config/data-ref/item-codes/order/order-status/cancel";
						String EXECUTED = "/server-config/data-ref/item-codes/order/order-status/executed";
						String EXPIRED = "/server-config/data-ref/item-codes/order/order-status/expired";
						String REJECTED = "/server-config/data-ref/item-codes/order/order-status/rejected";
						String COMPLETED = "/server-config/data-ref/item-codes/order/order-status/completed";
						String PENDING = "/server-config/data-ref/item-codes/order/order-status/pending";
						String SENDING = "/server-config/data-ref/item-codes/order/order-status/sending";
						String MODIFIED = "/server-config/data-ref/item-codes/order/order-status/modified";
						String NEW_ORDER_PBS = "/server-config/data-ref/item-codes/order/bps-order-status/new-order-place";
						String PARTLY_EXECUTE_PBS = "/server-config/data-ref/item-codes/order/bps-order-status/partly-executed";
					}

					public interface Means {
						String FLOOR = "/server-config/data-ref/item-codes/order/order-means/floor";
						String AGENT = "/server-config/data-ref/item-codes/order/order-means/agent";
						String ONLINE = "/server-config/data-ref/item-codes/order/order-means/online";
						String TELEPHONE = "/server-config/data-ref/item-codes/order/order-means/telephone";
					}
				}
			}
		}

		/**
		 * 
		 * @author tungnq
		 * 
		 */
		public interface ChartSetting {
			public interface Color {
				String BGCHART = "/server-config/ChartSetting/Color/bgchart";
				String BORDER_CHART = "/server-config/ChartSetting/Color/border";
				String GRIDLINE_CHART = "/server-config/ChartSetting/Color/gridline";
			}

			public interface TAChartHeight {
				String MAIN_CHART = "/server-config/ChartSetting/TAChartHeight/MainChart";
				String SUB_CHART = "/server-config/ChartSetting/TAChartHeight/SubChart";
			}
		}
	}

	/**
	 * 
	 * @author tungnq
	 * 
	 */
	public interface Forums {
		Long PUBLISH_STATUS = new Long(1);
		Long UNPUBLISH_STATUS = new Long(0);
		Long ROOT_TOPIC = new Long(0);
	}

	/**
	 * 
	 * @author tungnq
	 * 
	 */
	public abstract class Paging {
		public static int NUMBER_ITEMS = 15;

		// int NUMBER_ITEMS = 3;
		public static final int NO_NEXT_ITEM = -1;
		public static final int NO_PREVIOUS_ITEM = -1;
		public static final String DEFAULT_KEY = "$VNDIRECT_PAGING_KEY$";
		public static final String FROM_SCC_FILLING = "$FROM_SCC_FILLING";
		public static final String STR_INDEX_0 = "{0}";
		public static final String STR_INDEX_1 = "{1}";
		public static final String STR_INDEX_2 = "{2}";
		public static final String STR_INDEX_3 = "{3}";
		public static final String STR_INDEX_4 = "{4}";
	}

	public interface COMPANY_PROFILE_ITEMS {
		public final static String COMPANY_NAME_VIEW = "COMPANY_NAME_VIEW";
		public final static String COMPANY_OFFICE_VIEW = "COMPANY_OFFICE_VIEW";
		public final static String COMPANY_PROFILE_VIEW = "COMPANY_PROFILE_VIEW";
		public final static String SEC_INDEX = "SEC_INDEX";
	}

	public interface KEY_STATIC_ITEMS {
		public final static String VALUATION_MEASURES = "VALUATION_MEASURES";
		public final static String KEY_RATIO = "KEY_RATIO";
		public final static String FINANCIAL_HIGHLIGHT = "FINANCIAL_HIGHLIGHT";
		public final static String TRADING_INFORMATION = "TRADING_INFORMATION";
	}

	public interface MAJOR_HOLDERS_ITEMS {
		public final static String MAJOR_HOLDERS = "MAJOR_HOLDERS";
		public final static String BREAK_DOWN = "BREAK_DOWN";
	}

	public interface MarketSummary {
		String HASTC_MARKET_INFO = "HASTC_MARKET_INFO";
		String HOSTC_MARKET_INFO = "HOSTC_MARKET_INFO";
		String VN30_MARKET_INFO = "VN30_MARKET_INFO";
		String HNX30_MARKET_INFO = "HNX30_MARKET_INFO";
		String OTC_MARKET_INFO = "OTC_MARKET_INFO";
		String UPCOM_MARKET_INFO = "UPCOM_MARKET_INFO";
		String LIST_HASTC_COMPANY_SUMMARY = "LIST_HASTC_COMPANY_SUMMARY";
		String LIST_HOSTC_COMPANY_SUMMARY = "LIST_HOSTC_COMPANY_SUMMARY";
		String LIST_VN30_COMPANY_SUMMARY = "LIST_VN30_COMPANY_SUMMARY";
		String LIST_HNX30_COMPANY_SUMMARY = "LIST_HNX30_COMPANY_SUMMARY";
		String LIST_OTC_COMPANY_SUMMARY = "LIST_OTC_COMPANY_SUMMARY";
		String LIST_UPCOM_COMPANY_SUMMARY = "LIST_UPCOM_COMPANY_SUMMARY";
	}

	public interface Statistics {
		public static final String TRADING_STATISTICS_DOWNLOAD_TYPE = "$TDS_DL_TYPE$";
		public static final String TRADING_STATISTICS_4SYMBOL_DOWNLOAD_TYPE = "$TDS_4SB_DL_TYPE$";
		public static final String HISTORICAL_PRICE_DOWNLOAD_TYPE = "$HP_DL_TYPE$";
	}

	public interface PriceInfo {
		String PRICE_FROM_DATABASE = "/server-config/PriceInfo/FromDatabase";
		String RUNNING_BOARD_TIME = "/server-config/PriceInfo/RunningBoardTime";
	}
}
