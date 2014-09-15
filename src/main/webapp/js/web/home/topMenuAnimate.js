var clickedTopMenuObj = null;
var w;
$(document).ready(function() {
	$(window).resize(function() {
  		w = $(window).width();
  		$("#wrapperSubmenuContents").css('width', w * $('.menu_top_new li').length);
		$('.supmenutop_new .content_menu_top_new').css('width', w);
	});
	
	w = $(window).width();
	$("#wrapperSubmenuContents").css('width', w * $('.menu_top_new li').length);
	$('.supmenutop_new .content_menu_top_new').css('width', w);
	deactiveSubmenu();

	$.each($('.menu_top_new li'), function(i, topmenu){
		if(!$(topmenu).hasClass('openAcc')){
			$(topmenu).click(function(){
				if(!($(this).find('a').hasClass('openaccoutitsme'))){
					slideTopMenu($(this), i);
				}
			});
		}
	});
	
	$('.icon_dow_supmenu').click(function(){
		if(clickedTopMenuObj != null){
				clickedTopMenuObj.css('background-color', '');
				clickedTopMenuObj.removeClass('opened');
		}
		$('.supmenutop_new').slideUp('normal', function(){
			scrollResize();
		});
	});
	
	// slideUp when click outside
	slideUpSubMenuWhenClickOutside();
	
	// update: add more functions when user click on the root navigation
	$('#ttvndRoot').click(function(){
		slideTopMenu($('#thongtinVnDirectTopMenu'), 0);
	});
	$('#spdvRoot').click(function(){
		slideTopMenu($('#sanphamdichvuTopMenu'), 1);
	});
	$('#gdcpRoot').click(function(){
		slideTopMenu($('#giaodichcophieuTopMenu'), 2);
	});
	$('#ttptRoot').click(function(){
		slideTopMenu($('#trungtamphantichTopMenu'), 3);
	});
	$('#khtcRoot').click(function(){
		slideTopMenu($('#khachhangtochucTopMenu'), 4);
	});
});
function slideUpSubMenuWhenClickOutside(){
	var mouse_is_inside = false;
	$('#navi_top_new').hover(function(){
        mouse_is_inside=true; },
        function(){
        mouse_is_inside=false;
    });
	$('#ttvndRoot').hover(function(){
        mouse_is_inside=true; },
        function(){
        mouse_is_inside=false;
    });
	$('#spdvRoot').hover(function(){
        mouse_is_inside=true; },
        function(){
        mouse_is_inside=false;
    });
	$('#gdcpRoot').hover(function(){
        mouse_is_inside=true; },
        function(){
        mouse_is_inside=false;
    });
	$('#ttptRoot').hover(function(){
        mouse_is_inside=true; },
        function(){
        mouse_is_inside=false;
    });
	$('#khtcRoot').hover(function(){
        mouse_is_inside=true; },
        function(){
        mouse_is_inside=false;
    });
    $('body').mouseup(function() {
		if (!mouse_is_inside) {
			if(clickedTopMenuObj != null){
				clickedTopMenuObj.css('background-color', '');
				clickedTopMenuObj.removeClass('opened');
				clickedTopMenuObj.removeClass('logo_click_active');
			}
			$('.supmenutop_new').slideUp('normal', function(){
				scrollResize();
			});
		}
	});
}
function activeSubmenu(){
	$('.supmenutop_new').css('height', 205);
	$('#submenuContents').show();
	$('.supmenutop_new').slideDown('normal', function(){
		scrollResize();
	});
}
function deactiveSubmenu(){
	$('.supmenutop_new').slideUp('normal', function(){
		scrollResize();
	});
	$('.supmenutop_new').css('height', 0);
	$('#submenuContents').hide();
}
function slideTopMenu(obj, index){
	if(obj.hasClass('opened')){
		obj.css('background-color', '');
		obj.removeClass('opened');
		$('.supmenutop_new').slideUp('normal', function(){
			scrollResize();
		});
	} else if(obj.hasClass('logo_click_active')){
		obj.css('background-color', '');
		obj.removeClass('logo_click_active');
		$('.supmenutop_new').slideUp('normal', function(){
			scrollResize();
		});
	} else {
		
		// remove class cua cai truoc
		if(clickedTopMenuObj != null){
			clickedTopMenuObj.css('background-color', '');
			clickedTopMenuObj.removeClass('opened');
			clickedTopMenuObj.removeClass('logo_click_active');
		}
		clickedTopMenuObj = obj;
		// add class vao cai hien tai
		obj.addClass('opened');
		if(obj.attr('id') == 'thongtinVnDirectTopMenu'){
			obj.addClass('logo_click_active');
		}
		// animate
		activeSubmenu();
		params = { marginLeft : (-w * index) };
		$("#wrapperSubmenuContents").stop().animate(params);
	}
}

function scrollResize(){
	try {
		$("#scroll_newhome").getNiceScroll().resize();
		$("#scroll_tinvnd").getNiceScroll().resize();
	} catch (e){
		//Not existed scroll, do nothing
	}
}