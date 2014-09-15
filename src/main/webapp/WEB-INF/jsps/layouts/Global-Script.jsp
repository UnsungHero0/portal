<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="vn.com.web.commons.utility.Utilities"%>
<%@ page import="vn.com.web.commons.servercfg.ServerConfig"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
	String pageUUID = null;
	try {
		pageUUID = Utilities.FormatURL.getPageUUID(request);
	} catch (Exception e) {
	}
	pageUUID = (pageUUID == null ? String.valueOf(System.currentTimeMillis()) : pageUUID);
%>

<script type="text/javascript">

/**
* keep Context of web application
*/
var WEB_CONTEXT = '<web:url value="/"/>';
var TRADE_CONTEXT = '/trade';
var WEB_CONTEXT_RESOURCE = 'https://www.vndirect.com.vn/vndirect-resource/';
var WEB_VNDIRECT_RESOURCE = '<%=ServerConfig.getOnlineValue("/server-config/VnDirectResourceRoot")%>';
var WEB_CONTEXT_CHART = 'vnd_<%=pageUUID%>_';

// parameter for paging-info
var PAGING_FIRST = '<s:text name="paging.first"/>';
var PAGING_BACK = '<s:text name="paging.back"/>';
var PAGING_NEXT = '<s:text name="paging.next"/>';
var PAGING_LAST = '<s:text name="paging.last"/>';
var PAGING_OTHER_COMPANY = '<s:text name="paging.otherCompany"/>';

// parameter for multi-language
var LANGUAGES = {VN : '<s:text name="language.vn"/>', en_GB : '<s:text name="language.en_GB"/>', jp : '<s:text name="language.jp"/>'};

//Resource
var AJAX_IMAGE_LOADING = '<web:url value="/images/ajax-loader.gif"/>';

//Common
var SYMBOLPROCESSINGACTION_NOT_FOUND = '<s:text name="web.label.messages.norecord"/>';
var VNDIRECT_CORNER = '<s:text name="leftmenu.label.analysisTool.Vndirect.Corner"/>';

// Vote
var SELECT_ONE_ANSWER = '<s:text name="leftmenu.message.listedmarket.vote.selectOneAnswer"/>';
var ITEM_VOTE = '<s:text name="leftmenu.label.listedmarket.vote.popup.itemVote"/>';

//Messages for symbol search
var MESSAGE_SYMBOL_SEARCH = '<s:text name="web.label.HomePageAction.form.Messages.SymbolSearch">In put symbol</s:text>';

//using newsinfo.clazz.NewsOnlineHome.js
var MARKET_NEWS_COL1 = '<s:text name="web.label.HomePageAction.form.home.annalysis.tablecol1"/>';
var MARKET_NEWS_COL2 = '<s:text name="web.label.HomePageAction.form.home.annalysis.tablecol2"/>';
var MARKET_NEWS_COL3 = '<s:text name="web.label.HomePageAction.form.home.annalysis.tablecol3"/>';

//using newsinfo.clazz.MarketOverviewHome.js
var WORLD_INDEX_COL1 = '<s:text name="web.label.HomePageAction.form.home.worldindex.tablecol1">Value</s:text>';
var WORLD_INDEX_COL2 = '<s:text name="web.label.HomePageAction.form.home.worldindex.tablecol2">Change</s:text>';
var WORLD_INDEX_COL3 = '<s:text name="web.label.HomePageAction.form.home.worldindex.tablecol3">Change % </s:text>';

//using
var SECTOR_TEXT = '<s:text name="commons.symbolsearch.textbox"/>';
var CK_SEARCH_TEXTBOX = '<s:text name="commons.symbolsearch.textbox"/>';
var LATEST_MARKET_NEWS_SUBTITLE = '<s:text name="web.label.HomePageAction.form.home.marketsummarize.subtitle"/>';

//Listed Market
var COMMON_NOT_FOUND = '<s:text name="web.label.messages.norecord"/>';
var FOR_SYMBOL_FROMNEWSDATE_FIELD_INVALID = '<s:text name="web.label.NewsForSymbolAction.form.Messages.FromNewsDateFieldInvalid"/>';
var FOR_SYMBOL_TONEWSDATE_FIELD_INVALID = '<s:text name="web.label.NewsForSymbolAction.form.Messages.ToNewsDateFieldInvalid"/>';
var FOR_EVENT_FROMNEWSDATE_FIELD_INVALID = '<s:text name="web.label.CompanyEventsAction.form.Messages.FromNewsDateFieldInvalid"/>';
var FOR_EVENT_TONEWSDATE_FIELD_INVALID = '<s:text name="web.label.CompanyEventsAction.form.Messages.ToNewsDateFieldInvalid"/>';
var resultTransactions = '<s:text name="web.label.ListedMarketAction.form.mostactive.resultTransactions"/>';
var HTML_SQ_URL = "http://www3.vndirect.com.vn/directboard";
var JAVA_SQ_URL = "http://directboard1.vndirect.com.vn";
//News Info
var RESULTBYKEYWORD_MESSAGE = '&nbsp;&nbsp;<s:text name="web.label.SearchNewsAction.form.Messages.Resultbykeyword"></s:text>&nbsp;&nbsp;';
var DETAILS_TEXT = '<s:text name="commons.newsinfo.detail">Detail</s:text>';
var BY = '<s:text name="commons.newsinfo.by">By</s:text>';

var URL_SYMBOL_AUTO_SUGGESTION = "<web:url value='/ajax/common/SymbolAutoSuggestionForSearch.shtml'/>";
var URL_SYMBOL_QUICK_SEARCH = "<web:url value='/ajax/common/SymbolProcessing_QuickSearch.shtml'/>";
var URL_SYMBOL_CHECK_EXIST = "<web:url value='/ajax/common/SymbolProcessing_CheckExistedSymbol.shtml'/>";
var URL_SYMBOL_SEARCH_SYMBOL = "<web:url value='/analysis/search-symbol.shtml'/>";

var URL_VOTE = '<web:url value="/ajax/listed/GetVote.shtml"/>';
var URL_SUBMIT_VOTE = '<web:url value="/ajax/listed/SubmitVote.shtml"/>';
var URL_CAPTCHA='<web:url value="/ajax/common/CaptchaImage.shtml"/>';

// Using for Free Register Home Page
var URL_HOSESTOCKBOARD = '<web:url value="/home/RedirectUrl.shtml?code=HoseStockBoard&ticket=true"/>';

var WORLD_INDEX_URL = '<web:url value="/ajax/home/WorldIndex.shtml"/>';

//Login
var URL_LOGIN = '<web:url value="/osc/OSCAfterLogin.shtml"/>';

//Analysis Tools
var URL_LIST_SECTOR = "<web:url value='/phan-tich-nganh/chi-so-nganh.shtml'/>";
var URL_HIT = '<web:url value="/ajax/analysis/Hit.shtml"/>';
var URL_SHOW_SECTOR_DETAILS = '<web:url value="/analysis/SectorDetails.shtml"/>';
var URL_STOCK_SCREENER = '<web:url value="/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml"/>';
var URL_STOCK_SCREENER_SUMMARY = '<web:url value="/cong-cu-phan-tich-chung-khoan/ket-qua-sang-loc-co-phieu.shtml"/>';
var URL_STOCK_SCREENER_SAVING = '<web:url value="/cong-cu-phan-tich-chung-khoan/luu-tieu-chi-sang-loc.shtml"/>';
var URL_STOCK_SCREENER_DELETE = '<web:url value="/cong-cu-phan-tich-chung-khoan/stock-screener-delete.shtml"/>';
var URL_STOCK_SCREENER_HISTORY_SUMMARY = '<web:url value="/cong-cu-phan-tich-chung-khoan/tieu-chi-sang-loc-co-phieu-da-luu.shtml"/>';
var URL_STOCK_SCREENER_HISTORY = '<web:url value="/cong-cu-phan-tich-chung-khoan/stock-screener-history.shtml"/>';

var URL_COMPANY_SNAPSHOT = "<web:url value='/analysis/stock-information/company/snapshot.shtml'/>";
var URL_STOCK_INFO = "<web:url value='/tong-quan/'/>";
var URL_MACRO_NEWS_AJAX = '<web:url value="/ajax/analysis/MacroNews.shtml"/>';
var URL_MACRO_REPORT_NEWS_AJAX = '<web:url value="/ajax/analysis/MacroReportNews.shtml"/>';
var URL_LIST_SECTOR_AJAX = '<web:url value="/ajax/analysis/ListSectorView.shtml"/>';
var URL_LIST_INDUSTRIES_AJAX = '<web:url value="/ajax/analysis/ListIndustryView.shtml"/>';
var URL_LIST_COMPANIES_AJAX = '<web:url value="/ajax/analysis/ListCompanyView.shtml"/>';
var URL_SEARCH_BY_SYMBOL_AJAX = '<web:url value="/ajax/analysis/ListCompanyBySymbol.shtml"/>';
var URL_LIST_SECTOR_INDUSTRIES_AJAX = '<web:url value="/ajax/analysis/ListSectorIndex.shtml"/>';
var URL_STOCK_SCREENER_CALCULATE_AJAX = '<web:url value="/ajax/analysis/StockScreenerAJAXAction_Calculate.shtml"/>';
var URL_STOCK_SCREENER_GET_LIST_INDUSTRY_AJAX = '<web:url value="/ajax/analysis/StockScreenerAJAXAction_GetListOfIndustries.shtml"/>';
var URL_STOCK_SCREENER_SUMMARY_SORTING_AJAX = '<web:url value="/ajax/analysis/StockScreenerAJAXAction_ViewSortedData.shtml"/>';
var URL_STOCK_SCREENER_SUMMARY_DATA_AJAX = '<web:url value="/ajax/analysis/StockScreenerAJAXAction_ViewSummaryData.shtml"/>';
var URL_STOCK_SCREENER_LIST_AJAX = '<web:url value="/ajax/analysis/StockScreenerAJAXAction_ViewListOfStockScreener.shtml"/>';

var URL_MARKET_DAILY_NEWS_AJAX = '<web:url value="/ajax/analysis/MarketDailyNews.shtml"/>';
var URL_LATEST_REPORT_AJAX = '<web:url value="/ajax/analysis/LatestReport.shtml"/>';
var URL_MOST_VIEWED_REPORT_AJAX = '<web:url value="/ajax/analysis/MostViewedReports.shtml"/>';
var URL_RELATED_VIDEO_AJAX = '<web:url value="/ajax/analysis/RelatedVideos.shtml"/>';
var URL_NEWS_DETAILS = '<web:url value="/common/NewsDetail.shtml"/>';
var URL_NEWS_DETAILS_2 = '<web:url value="/home/info/NewsDetail.shtml"/>';
var URL_NEWS_DETAILS_Commentary = '<web:url value="/analysis/NewsDetail_Commentary.shtml"/>';
var URL_NEWS_DETAILS_Broker = '<web:url value="/analysis/NewsDetail_Broker.shtml"/>';
var URL_NEWS_DETAILS_Expert = '<web:url value="/analysis/news/expert/"/>';
var URL_NEWS_DETAILS_DailyReport = '<web:url value="/analysis/NewsDetail_DailyReport.shtml"/>';
var URL_NEWS_DETAILS_DailyReportNoVideo = '<web:url value="/analysis/NewsDetail_DailyReportNoVideo.shtml"/>';
var URL_NEWS_DETAILS_MacVN = '<web:url value="/tin-trong-nuoc/"/>';
var URL_NEWS_DETAILS_MacWorld = '<web:url value="/tin-quoc-te/"/>';
var URL_NEWS_DETAILS_Disclousure = '<web:url value="/analysis/disclosure/"/>';
var URL_NEWS_DETAILS_IPO = '<web:url value="/listed/NewsDetail_IPO.shtml"/>';
var URL_NEWS_DETAILS_PM = '<web:url value="/analysis/NewsDetail_MarketReview.shtml"/>';
var URL_LOAD_MORE_NEWS = "<web:url value='/ajax/LoadMore/News.shtml'/>";
var URL_LOAD_MORE_VIDEOS = "<web:url value='/ajax/LoadMore/Videos.shtml'/>";


var URL_ATTACHMENTS_DETAILS = '<web:url value="/common/AttachmentsDetail.shtml"/>';

var URL_INDUSTRY_DETAILS_AJAX = '<web:url value="/ajax/analysis/IndustryDetails.shtml"/>';
var URL_INDUSTRY_DETAILS = '<web:url value="/analysis/IndustryDetails.shtml"/>';
var URL_SECTOR_DETAILS_AJAX = '<web:url value="/ajax/analysis/SectorDetails.shtml"/>';

var URL_TOP_COMPANY_AJAX = '<web:url value="/ajax/analysis/TopCompany.shtml"/>';
var URL_TOP_INDUSTRY_AJAX = '<web:url value="/ajax/analysis/TopIndustry.shtml"/>';

var URL_VIEW_EXPERT_DETAIL = '<web:url value="/analysis/ViewExpertDetail.shtml?subjectCode=" />';
var URL_VIEW_EXPERT_CONTENT = '<web:url value="/ajax/analysis/ViewExpertContent.shtml" />';

var VNDIRECT_AJAX_URI = "<vndirect:uriRewrite uri='vndirect.ajax'/>";

var URL_TRADINGS = '<web:url value="/listed/TradingStatistics.shtml"/>';
var URL_SEARCH_CASHFLOW = '<web:url value="/ajax/listed/SearchCashFlow.shtml"/>';
var URL_SEARCH_INCOMESTATEMENT = '<web:url value="/ajax/listed/SearchIncomeStatement.shtml"/>';
var URL_HISTORICAL_PRICE_FOR_SYMBOL = '<web:url value="/listed/historical-price.shtml"/>';
var URL_MOST_ACTIVES = '<web:url value="/ajax/listed/GetMostActiveCompany.shtml"/>';
var URL_MARKET_CALENDAR = '<web:url value="/ajax/listed/GetMarketCalendar.shtml"/>';
var URL_MOST_POPULAR_SYMBOL = '<web:url value="/ajax/listed/MostPopularSymbol.shtml"/>';
var URL_SEARCH_RELATEDCOMPANYFORSNAPSHOT = '<web:url value="/ajax/listed/SearchRelatedCompany.shtml"/>';

var URL_SEARCH_TRADINGFOREIGNINVESTORS = '<web:url value="/ajax/listed/TradingForeignInvestors.shtml"/>';
var URL_SEARCH_FOREIGNTRADINGFORSYMBOL = '<web:url value="/ajax/listed/foreign-trading.shtml"/>';
var URL_SEARCH_HISTORICALPRICE = '<web:url value="/ajax/listed/SearchHistoricalPrice.shtml"/>';
var URL_SEARCH_HISTORICALPRICEFORSYMBOL = '<web:url value="/ajax/listed/SearchHistoricalPriceForSymbol.shtml"/>';
var URL_SEARCH_TRADINGSTATISTICS = '<web:url value="/ajax/listed/TradingStatistics.shtml"/>';
var URL_SEARCH_TRADING_FORSMBOL = '<web:url value="/ajax/listed/TradingForSymbol.shtml"/>';
var URL_DOWNLOAD_REPORT_FOR_SYMBOL = '<web:url value="/ajax/listed/DownloadReportForSymbol.shtml"/>';
var URL_DOWNLOAD_REPORT = '<web:url value="/ajax/listed/DownloadTradingStatistics.shtml"/>';

var URL_SEARCH_NEWS = '<web:url value="/ajax/news/SearchNews_FindingInformation.shtml"/>';
var URL_VNDIRECT_NEWS = '<web:url value="/news/NewsOfVNDIRECT.shtml"/>';
var URL_STOCK_INFORMATION = '<web:url value="/listed/MacroNews.shtml"/>';

var URL_NEWS = '<web:url value="/ajax/news/News_GetNews.shtml"/>';
var URL_MAC_WORLD = '<web:url value="/ajax/news/News_GetMacWorld.shtml"/>';
var URL_IPO_NEWS = '<web:url value="/ajax/news/News_GetIPONews.shtml"/>';
var URL_PUBLIC_INFO = '<web:url value="/ajax/news/News_GetPublicInfo.shtml"/>';
var URL_MARKET_NEWS = '<web:url value="/ajax/news/News_GetMarketNews.shtml"/>';
var URL_GET_OTHER_NEWS = '<web:url value="/ajax/news/News_GetOtherNews.shtml"/>';
var URL_GET_OTHER_INDAY_NEWS = '<web:url value="/ajax/news/News_GetOtherInDayNews.shtml"/>';
var URL_GET_NEWS_BY_TYPES = '<web:url value="/ajax/news/News_GetNewsByTypes.shtml"/>';
var URL_GET_MOST_VIEWED_NEWS_BY_TYPES = '<web:url value="/ajax/news/News_GetMostViewedNewsByTypes.shtml"/>';

//Open Account
var URL_CREATE_FREEACCOUNTUSERS = '<web:url value="/ajax/account/CreateFreeUsersAccount.shtml"/>';
var URL_FREEREGISTEDUSERS_SUCCESS = '<web:url value="/account/FreeUsersAccountSuccess.shtml"/>';
var URL_OPEN_ACCOUNT = '<web:url value="/mo-tai-khoan.shtml"/>';
//OSC
var URL_CHANGE_ACCOUNT_ACTION = '<web:url value="/ajax/user/UserProfileAJAX_ChangeSelectedAccount.shtml"/>';
var URL_CHANGE_USER_PROFILE = '<web:url value="/thong-tin-co-ban.shtml"/>';
var URL_VALIDATE_TRADING_PASSWORD = '<web:url value="/ajax/user/UserProfileAJAX_ValidateTradingPassword.shtml"/>';
var URL_VIEW_YOUR_REQUEST = '<web:url value="/ajax/user/UserProfileAJAX_ViewYourRequest.shtml"/>';
var URL_CHANGE_PASSWORD = '<web:url value="/user/changePassword.shtml"/>';
var URL_SHOW_QUESTION = '<web:url value="/ajax/user/ShowQuestion.shtml"/>';
var URL_SAVE_QUESTION = '<web:url value="/ajax/user/SaveQuestion.shtml"/>';

var URL_FLASH_CHART = '<web:url value="/analysis/FlashChart.shtml"/>';

var URL_FLASH_CHART_AJAX = '<web:url value="/ajax/analysis/GetClassicChart.shtml"/>';
var URL_CLASSIC_CHART_ADD_SYMBOL = '<web:url value="/analysis/AddSymbol.shtml"/>';
var URL_SEARCH_SYMBOL_AJAX = '<web:url value="/ajax/common/SymbolProcessing_SearchSymbol.shtml"/>';

// define callback url when search for symbol
var FROM_MODULE;
// company
var URL_PROFILE = 1;
var URL_KEY_STATISTIC = 2;
var URL_FINANCIAL_REPORT = 3;
var URL_FORECAST = 4;
// ownership
var URL_MAJOR_HOLDER = 5;
var URL_INSIDE_TRANSACTION = 6;
// chart
var URL_FLASH_CHART = 7;
// news
var URL_COMPANY_NEWS = 8;
var URL_COMPANY_EVENTS = 9;
// financial report
var URL_BALANCE_SHEET = 10;
var URL_INCOME_STATEMENT = 11;
var URL_CASH_FLOW = 12;
// market statistic
var URL_HISTORY_PRICE = 13;
var URL_FOREIGN_TRADING = 14;
var URL_TRADING_STATISTC = 15;
// -------

var URL_GET_DOCUMENT_PRODUCT = '<web:url value="/ajax/home/service/ProductAJAX_GetDocumentProduct.shtml"/>';
var URL_GET_PRODUCT_DETAIL = '<web:url value="/ajax/home/service/ProductAJAX_GetProductDetail.shtml"/>';

var URL_OSC_CONTETNT_DETAIL = '<web:url value="/osc/viewContentDetail"/>';

// POWER RATING
var URL_GET_POWER_RATING_BY_LEVEL= '<web:url value="/ajax/analysis/ViewPowerRatingLevel.shtml"/>';
var URL_GET_POWER_RATING_BY_WATCHLIST= '<web:url value="/ajax/analysis/ViewPowerRatingWatchList.shtml"/>';
var URL_GET_COMPANY_POWER_RATING= '<web:url value="/ajax/analysis/ViewCompanyPowerRating.shtml"/>';
var POWER_RATING_VIEW_URL = '<web:url value="/analysis/PowerRatingView.shtml"/>';
var URL_POWER_RATING_SYMBOL_QUICK_SEARCH = '<web:url value="/ajax/analysis/SearchSymbolPowerRating.shtml"/>';
//var BANG_GIA_LINK = "https://www.vndirect.com.vn/webservices.vnd?jKey=?";
var BANG_GIA_LINK = "https://uatboard.vndirect.com.vn/webservices.vnd?jKey=?";

var URL_SOCKET_SERVER = "https://prcproxy06.vndirect.com.vn/";
//var URL_SOCKET_SERVER = "http://123.30.240.104:80/,http://123.30.240.113:80/,http://202.160.125.23:80/,http://202.160.125.47:80/,http://202.160.125.24:80/";
// FLEX PAPER
var FLEX_PAPER_KEY = '$a6889f01f1635a39f4d';

//Market key
var CONST_HSX_NAME = "10";
var CONST_HNX_NAME = "02";
var CONST_UPC_NAME = "03";
var CONST_VN30_NAME = "11";
var CONST_HX30_NAME = "12";

</script>

<s:set value='#{
					"DailyReport" 			: getText("leftmenu.label.analysisTool.Video.Daily.News"),
					"WeeklyReport" 			: getText("leftmenu.label.analysisTool.Video.Daily.News"),
					"MonthlyReport"			: getText("leftmenu.label.analysisTool.Video.Daily.News"),
					"Strategy" 				: getText("leftmenu.label.analysisTool.Report.Investment.Strategy"),
					"Company" 				: getText("leftmenu.label.analysisTool.Company.Analysis"),
					"Quarter" 				: getText("leftmenu.label.analysisTool.Updated.Quarterly.Report"),
					"RiskAlert" 			: getText("leftmenu.label.analysisTool.Report"),
					"Break" 				: getText("leftmenu.label.analysisTool.Report.Events"),
					"PM" 					: getText("leftmenu.label.analysisTool.MarketReview"),
					"Call" 					: getText("leftmenu.label.analysisTool.Report.Call"),
					"Statistic" 			: getText("leftmenu.label.analysisTool.Statistical.Products.Market"),
					"Disclousure"			: getText("leftmenu.label.listedmarket.news.business.information"),
					"Research" 				: getText("leftmenu.label.listedmarket.news.business.information"),
					"Event" 				: getText("leftmenu.label.listedmarket.news.business.information.companyEvents"),
					"MacVN"					: getText("leftmenu.label.listedmarket.news.domestic.market.news"),
					"MacWorld"				: getText("leftmenu.label.listedmarket.news.financial.world"),
					"IPO"					: getText("leftmenu.label.listedmarket.news.equitization.and.listing"),
					"VNDIRECT"				: getText("submenu.label.vndirect-info.newsVndirect"),
					"IR"					: getText("web.label.NewAction.AboutUs.InvestorRelations"),
					"Commentary"			: getText("web.label.NewAction.AboutUs.ExpertOpinion"),
					"Broker"				: getText("web.label.NewAction.AboutUs.ExpertOpinion"),
					"Expert"				: getText("web.label.NewAction.AboutUs.ExpertOpinion"),
					"NOT-VNDS-NEWS"			: getText("web.label.ListedMarketAction.form.news.title1")
				}' var="titles">
</s:set>

