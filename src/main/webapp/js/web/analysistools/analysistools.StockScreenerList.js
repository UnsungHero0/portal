var obj = new StockScreenerList();
var opts = obj.getOption();
$().ready(function() {
	$('#container-1').tabs();
	
	$.ajax({
		   type: "POST",
		   url: URL_STOCK_SCREENER_LIST_AJAX,
		   dataType: "json",
		   success: function(data){
				if (data.error.actionErrors.length == 0) {
					obj.buildListTable(data);
					addEventHandler();
				}
		   },
		   beforeSend: obj.loadImage
	});
});

function addEventHandler() {
	$('.delete').click(function(){
		var id = $(this).metadata().id;
		location.replace(URL_STOCK_SCREENER_DELETE + "?saveSearchId=" + id);
	});
	
	$('.history').click(function(){
		var id = $(this).metadata().id;
		location.replace(URL_STOCK_SCREENER_HISTORY_SUMMARY + "?saveSearchId=" + id);
	});
	
	$('.modify').click(function(){
		var id = $(this).metadata().id;
		location.replace(URL_STOCK_SCREENER_HISTORY + "?saveSearchId=" + id);
	});
}

function changePage(id, page) {
	$.ajax({
		   type: "POST",
		   url: URL_STOCK_SCREENER_LIST_AJAX,
		   data: 'pagingInfo.indexPage=' + page,
		   dataType: "json",
		   success: function(data){
				obj.buildListTable(data);
				addEventHandler();
		   },
		   beforeSend: obj.loadImage
	});
}