$(document).ready(function() {
	$.each($('.box_slide_lichsupt .box_file .photo'), function(i, obj){
		$(obj).click(function(){
			loadDataHistory($(this));
		});
	});
	
	$("#sequence").jCarouselLite({
		auto : 0,
		speed : 1000,
		visible : 2,
		btnNext: ".next",
		btnPrev: ".prev",
		hoverPause:true
	});
});

function loadDataHistory(obj) {
	resetSlideActive();
	var code = $(obj).attr('ref');
	$(obj).parent().find('h3').addClass('active');
	var url = $.web_resource.getContext() + "ajax/vndirect/history/" + code +".shtml";
	$('#historyContent').load(url);
}
function resetSlideActive(){
	$.each($('.box_slide_lichsupt .box_file h3'), function(i, obj){
		$(obj).removeClass('active');
	});
}