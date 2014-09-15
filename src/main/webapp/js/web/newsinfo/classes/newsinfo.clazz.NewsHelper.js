function NewsHelper() {
    this.SHOWIN_HOME = "Home";
    this.SHOWIN_SNAPSHOT = "Snapshot";
    this.DISPLAY_SHORT = "short";
    this.NUMBER_ITEM = '5';
    this.LISTED_MARKET_NUMBER_ITEM = '7';
	this.NORMAL_NUMBER_ITEM = '10';
	
    this.TYPE_NOT_VNDS_NEWS = "NOT-VNDS-NEWS";
    this.TYPE_VNDS_NEWS = "VNDIRECT";
    this.TYPE_PUBLIC_INFO = "MacVN";
    this.TYPE_NEWS = "Disclousure";    
    this.TYPE_ANALYSIS_MARKET_NEWS = "Research";
    this.TYPE_LATEST_MARKET_NEWS = "DailyReport";
    this.TYPE_EVENT = "Event";
    this.TYPE_IPO_NEWS = "IPO";
    this.TYPE_SHAREHOLDER_NEWS = "IR";
    
    /**Listed Market New Type**/
    this.TYPE_MARKET_NEWS = "MacVN";
    this.TYPE_DISCLOSURE_NEWS = "Disclousure";
    this.TYPE_SHARES_NEWS = "IPO";
    this.TYPE_ANALYSIS_NEWS = "DailyReport, Research";    
    
    this.URL_MARKET_NEWS = $.web_resource.getContext() + "/ajax/news/News_GetMarketNews.shtml";
    this.URL_ANALYSIS_OF_VNDIRECT = $.web_resource.getContext() + "/ajax/news/News_GetCenterAnalysisOfVNDirect.shtml";
    this.URL_LASTEST_MARKET_NEWS = $.web_resource.getContext() + "/ajax/news/News_GetLatestMarketNews.shtml";
    this.URL_IPO_NEWS = $.web_resource.getContext() + "/ajax/news/News_GetIPONews.shtml";
    this.URL_STOCKINFORMATION = $.web_resource.getContext() + "/ajax/news/News_GetStockInformation.shtml";
    this.URL_VNDS_NEWS = $.web_resource.getContext() + "/ajax/news/News_GetVNDSNews.shtml";
    this.URL_VND_SHAREHOLDER_NEWS = $.web_resource.getContext() + "/ajax/news/News_GetShareholderNews.shtml";
    this.URL_NOT_VNDS_NEWS = $.web_resource.getContext() + "/ajax/news/News_GetNotVNDSNews.shtml";
    this.URL_NEWS = $.web_resource.getContext() + "/ajax/news/News_GetNews.shtml";
    this.URL_EVENT = $.web_resource.getContext() + "/ajax/news/News_GetEvent.shtml";
    this.URL_PUBLIC_INFO = $.web_resource.getContext() + "/ajax/news/News_GetPublicInfo.shtml";
    this.URL_SYMBOL_TYPE_LIST = $.web_resource.getContext() + "/ajax/news/News_GetNewsForSymbolAndTypeList.shtml";
    
    this.URL_SECTOR_NEWS = $.web_resource.getContext() + "/ajax/news/News_GetSectorNews.shtml";
    this.URL_INDUSTRY_NEWS = $.web_resource.getContext() + "/ajax/news/News_GetIndustryNews.shtml";
    
    this.SHOWIN_VNMARKET = "VNMarket";
    this.SHOWIN_LISTED_MARKET = "ListedMarket";
	
}

function MarketOverviewHelper() {
    this.PARAM_SHOWIN = "showin";
    this.SHOWIN_ACCOUNT = "Account";
    this.SHOWIN_HOME = "Home";
    this.SHOWIN_PLACEORDER = "PlaceOrder";
    this.URL = $.web_resource.getContext() + "/ajax/news/MarketOverviewAJAX_MarketOverviewHome.shtml";
}


function MarketNewsHelper() { 
    this.URL = $.web_resource.getContext() + "/ajax/news/MarketNewsAJAX_MarketNewsHome.shtml";
}

function LatestMarketNewsHelper() {
	this.SHOWIN_HOME = "Home";
    this.URL = $.web_resource.getContext() + "/ajax/news/LastestMarketNewsAJAX_GetLastestNews.shtml";
}

function MarketOverviewHelper() {
    this.PARAM_SHOWIN = "showin";
    this.SHOWIN_ACCOUNT = "Account";
    this.SHOWIN_HOME = "Home";
    this.SHOWIN_PLACEORDER = "PlaceOrder";
    this.URL = $.web_resource.getContext() + "/ajax/news/MarketOverviewAJAX_MarketOverviewHome.shtml";
    this.URL_WORLD_MARKET = $.web_resource.getContext() + "/ajax/news/MarketOverviewAJAX_GetWorldMarketOverviewHome.shtml";
}