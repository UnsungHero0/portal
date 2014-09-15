var clickedObj = null;

/** Slide content on page 'Sản phẩm dịch vụ' */
$(document).ready(function() {
	loadDataNgang();
	loadDataDoc();
	
	var h = 690; // height 690px
	var w = 533; // width 533px
	
	$("#wrapperSlideDoc").css('height', w * $("#slideDocContent li").length);
	$("#wrapperSlideNgang").css('width', h * $("#slideNgangContent li").length);
	$(".bg_bo_top_product .list .title .rowb").click(function() {
		if(!$(this).hasClass('opened')){
			//reset popup layout
			$('#tradingServicePopupN').hide();
			$("#wrapperSlideNgang").stop().animate({ marginLeft : 0});
			$('#tradingServicePopupD').show();
			// add clicked
			if(clickedObj != null){
				clickedObj.removeClass('opened');
				clickedObj.removeClass('active');
			}
			clickedObj = $(this);
			$(this).addClass('active');
			// animate
			var index = $(this).find('a').attr('rel');
			params = { marginTop : w * (-index) };
			$("#wrapperSlideDoc").stop().animate(params);
			
			$(this).addClass('opened');
		} else {
			$(this).removeClass('opened');
			clickedObj.removeClass('active');
			clickedObj.removeClass('opened');
			closePopup();
		}
	});
	
	$('.bg_bo_top_product .list li .data').click(function(){
		if(!$(this).hasClass('opened')){
			// reset popup layout
			$('#tradingServicePopupD').hide();
			$("#wrapperSlideDoc").stop().animate({ marginTop : 0 });
			$('#tradingServicePopupN').show();
			// add clicked
			if(clickedObj != null){
				clickedObj.removeClass('opened');
				clickedObj.removeClass('active');
			}
			clickedObj = $(this);
			$(this).addClass('active');
			// animate
			var index = $(this).find('a').attr('rel');
			params = { marginLeft : h * (-index) };
			$("#wrapperSlideNgang").stop().animate(params);
			
			$(this).addClass('opened');
		} else {
			clickedObj.removeClass('opened');
			clickedObj.removeClass('active');
			$(this).removeClass('opened');
			closePopup();
		}
	});
	
	// close pop-up when click outside
	var mouse_is_inside = false;
	$('.box_popup').hover(function(){
        mouse_is_inside=true; },
        function(){
        mouse_is_inside=false;
    });
	$('.bg_bo_top_product .list li .data').hover(function(){
        mouse_is_inside=true; },
        function(){
        mouse_is_inside=false;
    });
	$(".bg_bo_top_product .list .title .rowb").hover(function(){
        mouse_is_inside=true; },
        function(){
        mouse_is_inside=false;
    });
    $('body').mouseup(function() {
		if (!mouse_is_inside) {
			clickedObj.removeClass('opened');
			clickedObj.removeClass('active');
			$("#wrapperSlideNgang").stop().animate({ marginLeft : 0});
			$("#wrapperSlideDoc").stop().animate({ marginTop : 0 });
			closePopup();
		}
	});
});
/** to slide doc */
function loadDataNgang() {
	$('#tradingServicePopupD .content #empty1 .box-text').html('');

	var url1 = $.web_resource.getContext() + "ajax/home/service/DirectAccount.shtml";
	var url2 = $.web_resource.getContext() + "ajax/home/service/PrimeAccount.shtml";
	var url3 = $.web_resource.getContext() + "ajax/home/service/SpecialAccount.shtml";
	var url4 = $.web_resource.getContext() + "ajax/home/service/PMDirect.shtml";

	$('#tradingServicePopupD .content #1 .box-text').load(url1);
	$('#tradingServicePopupD .content #2 .box-text').load(url2);
	$('#tradingServicePopupD .content #3 .box-text').load(url3);
	$('#tradingServicePopupD .content #4 .box-text').load(url4);
}
/** to slide ngang */
function loadDataDoc() {
	$('#tradingServicePopupN .content #empty1 .box-text').html('');

	var url5 = $.web_resource.getContext() + "ajax/home/service/phiGiaoDich.shtml";
	var url6 = $.web_resource.getContext() + "ajax/home/service/chuyenVienTuVan.shtml";
	var url7 = $.web_resource.getContext() + "ajax/home/service/sanPhamGiaoDich.shtml";
	var url8 = $.web_resource.getContext() + "ajax/home/service/sanPhamHoTroTaiChinh.shtml";
	var url9 = $.web_resource.getContext() + "ajax/home/service/sanPhamThongTinCoBan.shtml";
	var url10 = $.web_resource.getContext() + "ajax/home/service/banTinChienLuocDauTu.shtml";
	var url11 = $.web_resource.getContext() + "ajax/home/service/phanMemHoTro.shtml";
	var url12 = $.web_resource.getContext() + "ajax/home/service/mobiDirect.shtml";
	var url13 = $.web_resource.getContext() + "ajax/home/service/smsDirect.shtml";
	var url14 = $.web_resource.getContext() + "ajax/home/service/directBoard.shtml";
	var url15 = $.web_resource.getContext() + "ajax/home/service/activeDirect.shtml";
	var url16 = $.web_resource.getContext() + "ajax/home/service/brokerDesk.shtml";

	$('#tradingServicePopupN .content #5 .box-text').load(url5);
	$('#tradingServicePopupN .content #6 .box-text').load(url6);
	$('#tradingServicePopupN .content #7 .box-text').load(url7);
	$('#tradingServicePopupN .content #8 .box-text').load(url8);
	$('#tradingServicePopupN .content #9 .box-text').load(url9);
	$('#tradingServicePopupN .content #10 .box-text').load(url10);
	$('#tradingServicePopupN .content #11 .box-text').load(url11);
	$('#tradingServicePopupN .content #12 .box-text').load(url12);
	$('#tradingServicePopupN .content #13 .box-text').load(url13);
	$('#tradingServicePopupN .content #14 .box-text').load(url14);
	$('#tradingServicePopupN .content #15 .box-text').load(url15);
	$('#tradingServicePopupN .content #16 .box-text').load(url16);
}
/** */
function prepareClosePopup(){
	clickedObj.removeClass('opened');
	clickedObj.removeClass('active');
	$("#wrapperSlideNgang").stop().animate({ marginLeft : 0});
	$("#wrapperSlideDoc").stop().animate({ marginTop : 0 });
	closePopup();
}
function closePopup() {
	$('#tradingServicePopupD').fadeOut('fast');
	$('#tradingServicePopupN').fadeOut('fast');
}