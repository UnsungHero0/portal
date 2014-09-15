var obj = new StockScreenerSummary();
var opts = obj.getOption();
var actions = [
               	{
               		name : "summary", 
               		func : obj.buildSummaryTable,
               		sort : [
	               		        {field : 'COMPANY_NAME', order : 'ASC'},
	               		        {field : 'COMPANY_FULL_NAME', order : 'ASC'},
	               		        {field : 'ITEM_NAME', order : 'ASC'},
	               		        {field : 'CLOSE_PRICE', order : 'ASC'},
	               		        {field : 'f1000002', order : 'ASC'},
	               		        {field : 'f1000003', order : 'ASC'},
	               		        {field : 'f1000006', order : 'ASC'},
	               		        {field : 'f51003', order : 'ASC'}
               		       ],
               		field : '', //current field
               		order : '', //current order
               		page  : '' //current page
               	}
				, 
				{
					name : "pricePerformance", 
					func : obj.buildPricePerformanceTable,
					sort :  [
			        			{field : 'f1000002', order : 'ASC'},
			        			{field : 'f1000003', order : 'ASC'},
			        			{field : 'f1000007', order : 'ASC'},
			        			{field : 'f1000008', order : 'ASC'},
			        			{field : 'f1000010', order : 'ASC'},
			        			{field : 'f51007', order : 'ASC'}
					        ],
               		field : '', //current field
               		order : '', //current order
               		page  : '' //current page
				}
				, 
				{
					name : "fundamentals", 
					func : obj.buildFundamentalsTable,
					sort :  [
							 	{field : 'f51009', order : 'ASC'},
							 	{field : 'f51011', order : 'ASC'},
							 	{field : 'f51012', order : 'ASC'},
							 	{field : 'f51010', order : 'ASC'},
							 	{field : 'f53004', order : 'ASC'},
							 	{field : 'f52002', order : 'ASC'},
							 	{field : 'f52001', order : 'ASC'}
							],
               		field : '', //current field
               		order : '', //current order
               		page  : '' //current page
				}
				, 
				{
					name : "earningsDividends", 
					func : obj.buildEarningsDividendsTable,
					sort :  [
							 	{field : 'f53009', order : 'ASC'},
							 	{field : 'f53030', order : 'ASC'},
							 	{field : 'f53039', order : 'ASC'},
							 	{field : 'f53033', order : 'ASC'},
							 	{field : 'f51005', order : 'ASC'},
							 	{field : 'f53003', order : 'ASC'}
							],
               		field : '', //current field
               		order : '', //current order
               		page  : '' //current page
				}
				, 
				{
					name : "technicals", 
					func : obj.buildTechnicalsTable,
					sort :  [
							 	{field : 'f1000006', order : 'ASC'},
							 	{field : 'vs_sma_13_day', order : 'ASC'},
							 	{field : 'vs_sma_50_day', order : 'ASC'},
							 	{field : 'PCT_BELOW_52_WEEK_HIGH', order : 'ASC'},
							 	{field : 'PCT_ABOVE_52_WEEK_LOW', order : 'ASC'}
							],
               		field : '', //current field
               		order : '', //current order
               		page  : '' //current page
				}
			];
var action = actions[0];
$().ready(function() {
	//$('#container-1').tabs();
	
//	$()
	//load summary data for the first time
	action.field = action.sort[0].field;
	action.order = action.sort[0].order;
	action.page = 1;
	
	displayData();
	
	$('.sortBy').click(function(){
		sortField = $(this).metadata().sortField;
		
		//change the order and field
		$.each(action.sort, function(i, item){
			if (sortField == item.field) {
				action.field = item.field;
				action.order = item.order = (item.order == "ASC" ? "DESC" : "ASC"); //???
				return;
			}
		});
		
		//reload data
		displayData();
	});
	
	$('.action').click(function(){		
		var name = $(this).metadata().name;
		
		$('span.redtext').attr('class', 'bluetext');
		$(this).parent().attr('class', 'redtext');
		
		$('#tab_menusup_slcp li').removeClass("ui-tabs-selected");
		$(this).parent().addClass("ui-tabs-selected");
		
		//change the action
		action = $.grep(actions, function(v) {return v.name == name;})[0];
		
		action.page = 1; //reset it to 1
		//reload data
		displayData();
	});
	$('.modify').click(function(){
		$('#cacheForm').attr("action", URL_STOCK_SCREENER);
		$('#cacheForm').submit();
	});
	
	$('.save').click(function(){
		$('#cacheForm').attr("action", URL_STOCK_SCREENER_SAVING);
		$('#cacheForm').submit();
	});
	
});

function changePage(id, page) {
	action.page = page;
	displayData();
}

/**
 * Get data which was applied the criteria
 * @param data Data to be sent to the server
 */
function displayData() {
	//alert("run");
		var params = [ 
			  { name: 'sortField', value: action.field }, 
			  { name: 'sortOrder', value: action.order }, 
			  { name: 'cacheData', value: $('#cacheData').val() }, 
			  { name: 'pagingInfo.indexPage', value: action.page }
			];
	$.ajax({
   		   type: "POST",
   		   url: URL_STOCK_SCREENER_SUMMARY_SORTING_AJAX,
   		   data: params,
   		   dataType: "json",
   		   success: function(data) {
				if(data.error.actionErrors.length == 0) {
		   			//alert('a');
		   			action.func.call(obj, data);
				} else {
					alert(data.error.actionErrors[0]);
				}
   			},
   		   beforeSend: obj.loadImage
	});
	//alert("end");
}

function viewIndustry(symbol) {
	location.replace(URL_LIST_SECTOR + "?symbol=" + symbol);
}
