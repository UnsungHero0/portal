var news = new MacroNews();
var metadata;

$(document).ready(function(){
	var $tabs2 = $('#container-2').tabs({
	   select: function(event, ui) {
		  metadata = $(ui.tab).metadata();
	   	  load(metadata);
	   }
	});
		
	var selectedIdx = $tabs2.tabs('option', 'selected'); // => 0
	if(selectedIdx == 0) {
		metadata = $('#container-2').find("a:first").metadata();
		load(metadata);//This is a tricky :(
	} else {
		$tabs2.tabs('select', 0); // switch to first tab
	}
	
	getWeeklyInvestmentStrategy(1);
});

function load(metadata, _page) {
	 var page = _page || 1;
	 var params = [
 	               	{name : 'type', value : metadata.type},
 	               	{name : 'showin', value : metadata.showin},
 	               	{name : 'pagingInfo.indexPage', value : page},
 	               	{name : 'pagingInfo.offset', value : 8}
 	              ];
	 var action = {div : metadata.div, navigator : metadata.navigator, key: metadata.type};
	 $.ajax({
		   type: "POST",
		   url: eval(metadata.url),
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

function changePage(navigator, page) {
	load(metadata, page);
}