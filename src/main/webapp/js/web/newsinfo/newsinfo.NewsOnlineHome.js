var _newsOnlineClazzHome = new NewsOnlineClazzHome();

$().ready(function() {
	//Populate IPONews
	//_newsOnlineClazzHome.loadIPOSNews();
	//Populate VNDSNews
	_newsOnlineClazzHome.loadVNDSNews();
	
	//Market News
	//_newsOnlineClazzHome.loadAnalysisOfVNDirect();
	
	//Lastest Market News
//	_newsOnlineClazzHome.loadLastestMarketNews();
});