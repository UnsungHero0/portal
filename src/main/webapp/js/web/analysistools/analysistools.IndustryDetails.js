var obj = new IndustryDetails();
var news = new MacroNews();

var newsHelper = new NewsHelper();

var opts = obj.getOption();
var curPage = 1;//current page
var field = ''; //current field
var order = ''; //current order
var action = {
		   		key : newsHelper.TYPE_PUBLIC_INFO, 
		   		containerDiv : '.general.pn_main.businessInformation', 
		   		div : '.ds_news.businessInformation', 
		   		navigator : 'tab1'
		   	 };

$().ready(function() {
	if (industryCode != '') {
		//load data
		displayData();
	}
	
	$('#sector').click(function() {
		$('#SectorDetails').submit();
	});
	
	$('.sortBy').toggle(
			function(){
				//remove up & down icon from another header
				$('.header').children('img').attr('class', '');
				//change the order and field
				field = $(this).metadata().field;
				order = "ASC";
				$(this).parent().children('img:first').removeClass('table-header-down');
				$(this).parent().children('img:first').addClass('table-header-up');
				
				//reload data
				displayData();
			},
			function(){
				//remove up & down icon from another header
				$('.header').children('img').attr('class', '');
				
				//change the order and field
				field = $(this).metadata().field;
				order = "DESC";
				$(this).parent().children('img:first').removeClass('table-header-up');
				$(this).parent().children('img:first').addClass('table-header-down');
				
				//reload data
				displayData();
			}
	);
	load(action);
});

function load(action, page) {
	if (!page) {page = 1;}
	
	var params = [
	          {name : 'industryGroupCode', value : industryCode},
	          {name : 'sectorGroupCode', value : sectorCode},
	          {name : 'pagingInfo.indexPage', value : page},
	          {name : 'newsType', value: action.key}
          ];
	
	if (action) {
		$.ajax({
			   type: "POST",
			   url: newsHelper.URL_INDUSTRY_NEWS,
			   data: params,
			   dataType: "json",
			   success: function(data) {
					news.populateData(data, action);
			   },
			   beforeSend: function(){
				   	news.loadImage(action.div);
			   }
		});
	}
}

function changePage(containerId, page) {
	if(containerId == action.navigator) {
		load(action, page);
	} else {
		curPage = page;
		displayData();
	}
}

function displayData() {
	var params = [
		          {name : 'industryCode', value : industryCode},
		          {name : 'pagingInfo.indexPage', value : curPage},
		          {name : 'field', value: field}, 
				  {name : 'order', value: order}
	          ];
	$.ajax({
   		   type: "POST",
   		   url: URL_INDUSTRY_DETAILS_AJAX,
   		   data: params,
   		   dataType: "json",
   		   success: function(data) {
				if(data.error.actionErrors.length == 0) {
		   			obj.populateCompanyData(data);
				} else {
					alert(data.error.actionErrors[0]);
				}
   			},
   		   beforeSend: obj.loadImage
	});
}