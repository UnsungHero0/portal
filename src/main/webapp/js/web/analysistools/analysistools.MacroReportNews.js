var obj = new MacroReportNews();
var opts = obj.getOption();
var curPage = 1;
var symbol = '';
$().ready(function() {
	
	//add event handler for search button
//	$('#search').click(function() {
//		$("#recordNotFound").hide();
//		curPage = 1;
//		symbol = $("#symbol").val();
//		displayData();
//	});
//	
//	$.web_autocomplete.symbols('symbol', URL_SYMBOL_AUTO_SUGGESTION,
//			{
//				width : 310, 
//				callbackResult: function(event, item) {
//					//curPage = 1;
//					//symbol = item.symbol;
//					//displayData();
//				}
//			}
//	);
//	
//	$('#symbol').bind("keypress",
//		function(event) {
//			if (event.keyCode == 13) {
//				$('#search').click();
//			};
//		}
//	);
//	
	//load data
	displayData();
});

function changePage(containerId, page) {
	curPage = page;
	displayData();
}

function displayData() {
	params = [
		          {name : 'symbol', value : symbol},
		          {name : 'pagingInfo.indexPage', value : curPage},
		          {name : 'pagingInfo.offset', value : 20}
	          ];
	$.ajax({
   		   type: "POST",
   		   url: URL_MACRO_REPORT_NEWS_AJAX,
   		   data: params,
   		   dataType: "json",
   		   success: function(data) {
				//recordNotFound
				if (data.model.searchResult.length == 0) {
					$("#recordNotFound").show();
				}
   				obj.populateMacroReportNews(data, true);
   			},
   		   beforeSend: obj.loadImage
	});
}