var obj = new OscClazzListSectorIndex();
var opts = obj.getOption();
$().ready(function() {
	
	//load all the sector
	$.ajax({
		   type: "POST",
		   url: URL_LIST_SECTOR_INDUSTRIES_AJAX,
		   dataType: "json",
		   success: function(data){
				obj.populateDataIndex(data);
				industryName_onClick();
			},
		   beforeSend: obj.loadImage
	});
	
	$(".displayType").click(function(){
		$.ajax({
			   type: "POST",
			   url: URL_LIST_SECTOR_INDUSTRIES_AJAX,
			   dataType: "json",
			   data: "ifoIndustryCalcView.sortField=" + $(this).metadata().orderField,
			   success: function(data){
					obj.populateDataIndex(data);
					industryName_onClick();
				},
			   beforeSend: obj.loadImage
		});
	}); 
	
	function industryName_onClick() {
		$(".viewIndustry").click(function(){
			location.replace(URL_LIST_SECTOR + "?industryCode=" + $(this).metadata().industryCode + "&sectorCode=" + $(this).metadata().sectorCode);
		});
	}
});
