var _marketOverviewClazzFreeRegister = new MarketOverviewClazzFreeRegister();

$().ready(function() {
    //Populate Market Overview
	_marketOverviewClazzFreeRegister.loadMarketOverviewNews();
});

function refresh() {
    try {
    	_marketOverviewClazzFreeRegister.loadMarketOverviewNews();
    } catch (e) {
          alert("_goTo(): " + e);
    }
}