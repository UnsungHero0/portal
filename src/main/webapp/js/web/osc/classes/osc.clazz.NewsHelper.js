function NewsHelper() {
    
    this.TYPE_VNDS_NEWS = "VNDIRECT";
    this.TYPE_IPO_NEWS = "IPO";
    
    this.URL_STOCKINFORMATION = $.web_resource.getContext() + "/ajax/news/News_GetStockInformation.shtml";
    this.URL_VNDS_NEWS = $.web_resource.getContext() + "/ajax/news/News_GetVNDSNews.shtml";
    this.URL_MARKET_NEWS = $.web_resource.getContext() + "/ajax/news/MarketOverviewAJAX_MarketOverviewHome.shtml";
    
    this.SHOWIN_FREEREGISTER_HOME = "FreeRegisterHomePage";
	this.FREEREGISTER_HOME_NUMBER_ITEM = '7';
	this.NORMAL_NUMBER_ITEM = '10';
}

