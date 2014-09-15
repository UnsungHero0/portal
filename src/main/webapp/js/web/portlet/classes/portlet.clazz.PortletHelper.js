function NewsOnlineHelper() {
    this.SHOWIN_VNMARKET = "VNMarket";
    this.SHOWIN_HOME = "Home";
    this.SHOWIN_SNAPSHOT = "Snapshot";
    
    this.TYPE_NOT_VNDS_NEWS = "NOT-VNDS-NEWS";
    this.TYPE_VNDS_NEWS = "VNDS-NEWS";
    this.TYPE_PUBLIC_INFO = "PUBLIC-INFO";
    this.TYPE_NEWS = "NEWS";    
    this.TYPE_MARKET_NEWS = "MARKET-NEWS";
    this.TYPE_EVENT = "EVENT";
    
    this.IS_HOT_NEWS = "Y";
    this.DISPLAY_SHORT = "short";    
    this.URL = $.web_resource.getContext() + "/ajax/news/NewsOnlineAJAX_GetNews.shtml";

}

function MarketNewsHelper() { 
    this.URL = $.web_resource.getContext() + "/ajax/news/MarketNewsAJAX_MarketNewsHome.shtml";
}

function LatestMarketNewsHelper() {
	this.SHOWIN_HOME = "Home";
    this.URL = $.web_resource.getContext() + "/ajax/news/LastestMarketNewsAJAX_GetLastestNews.shtml";
}

