var _listedMarketClazzMarketCalendar = new ListedMarketClazzMarketCalendar();
var opts = _listedMarketClazzMarketCalendar.getOption();

$().ready(function() {
	// search marketCalendar
	_listedMarketClazzMarketCalendar.loadMarketCalendar();
	
	$("#fListedMarket_marketCalendar_searchButton").click(function(){
		$('#fListedMarket_marketCalendar_indexPage').val(1);
		_listedMarketClazzMarketCalendar.loadMarketCalendar();
	});
});

function _goToMarketCalendar(webNavId, index) {
	try {
		$("#fListedMarket_marketCalendar_indexPage").val(index);
		_listedMarketClazzMarketCalendar.loadMarketCalendar();
	} catch (e) {
		alert("_goTo(): " + e);
	}
}