// define edit or add new quick link
var availablePos = 0;
var availableObj = null;
var expire = 365;
var prefix_cookieName = 'vndirect_qckLnk_';
// the element which is moved (drag)
var dragObj = null;
var DIRECT_BAR_ADD_CLICKED = null;
var DIRECT_BAR_EDIT_CLICKED = null;

// to store UTF-8 icon quick link title, because setCookie does not save UTF-8 character.
var quickLinkTitle = [];
var default1 = null;
var default2 = null;
var default3 = null;
var default4 = null;

var selfDrag = false;
var fastClick = false;

$(function() {
	registerQuickLinks();
});

/**
 * get quick link that was placed from cookie
 */
function loadQuickLinkFromCookie() {
	// data
	try {
		var isExistFromCookie = false;
		for (var i = 1; i <= 5; i++) {
			var existCookie = getCookie(prefix_cookieName + i);
			if ($.web_utils.isNotEmpty(existCookie)) {
				isExistFromCookie = true;
				var arr = existCookie.split(',');
				var obj = $('.menu_footer').find('#' + i);
				obj.find('a').attr('href', arr[0]);
				obj.find('a').attr('target', arr[1]);
				obj.find('img').attr('src', arr[2]);
				obj.find('img').show();
				obj.find('p').html(quickLinkTitle[parseInt(arr[3])]);
				obj.find('span').addClass('filled');
				obj.css('cursor', 'default');
			} else {
				_addingAddButton(i);
			}
		}

		// set default if not exist cookie
		if (!isExistFromCookie) {
			setDefaultQuickLinkIcons();
		}

		// collapse/expand
		if (getCookie(prefix_cookieName + "_collapsed") == "Y") {
			$('.menu_footer').hide();
			$('.wrapper_footer').css('height', '35px');
			$('.footer #collapse-expand-icon').removeClass('collapse').addClass('expand');
		} else {
			$('.menu_footer').show();
			$('.wrapper_footer').css('height', 'auto');
		}
	} catch (e) {
	}
}
/**
 * create the quick link at footer
 */
function registerPlaceQuickLink() {
	$('#menusupfooter .box_icon').click(function(e) {
		if (!fastClick) {
			e.preventDefault();
			var url = $(this).find('p a').attr('href');
			var target = $(this).find('p a').attr('target');
			var img = $(this).find('p img').attr('src');
			var title = $(this).find('#title').html();

			if (availableObj == null) {
				getAvailblePostion();
			}
			availableObj.find('a').attr('href', url);
			availableObj.find('a').attr('target', target);
			availableObj.find('img').attr('src', img);
			availableObj.find('img').show();
			availableObj.find('p').html(title);
			availableObj.find('span').addClass('filled');
			availableObj.css('cursor', 'default');

			//set cookie
			setQuickLinkToCookie(new Array(url, target, img, quickLinkTitle
					.indexOf(title)));

			$("#menusupfooter").fadeOut("fast");
			$('.menu_footer .quickLinkIcon .filled').hover(function() {
				$(this).css('opacity', '1');
			}, function() {
				$(this).css('opacity', '0.5');
			});
			
			$('#placedQuickLinksList #' + availablePos).removeClass('opened');
			_clearAddButton(availablePos);
		}
	});
}
/**
 * remove the quick link at footer
 */
function registerRemoveQuickLink() {
	$('#menusupfooter .box_icon_delete').click(function(e) {
		if (availableObj != null) {
			availableObj.find('a').attr('href', '');
			availableObj.find('a').attr('target', '');
			availableObj.find('img').attr('src', '');
			availableObj.find('img').hide();
			availableObj.find('p').html('');
			availableObj.find('span').removeClass('filled');
			availableObj.css('cursor', 'pointer');
			// delete cookie
			setCookie(prefix_cookieName + availablePos, '', -1);

			$("#menusupfooter").fadeOut("fast");
			$('.menu_footer .quickLinkIcon .filled').hover(function() {
				$(this).css('opacity', '1');
			}, function() {
				$(this).css('opacity', '0.5');
			});
			
			$('#placedQuickLinksList #' + availablePos).removeClass('opened');
			_addingAddButton(availablePos);
		}
	});
}
/**
 * get availble postion to place a quick link
 */
function getAvailblePostion() {
	var objs = $('.menu_footer').find('.quickLinkIcon');
	var i = 1;
	$.each(objs, function() {
		if (!($(this).find('span').hasClass('filled'))) {
			availablePos = i;
			availableObj = $(this);
			return false;
		}
		i++;
	});

	return true;
}
/**
 * set quick link to the computer's cookie
 */
function setQuickLinkToCookie(cookieValue) {
	var cookieName = prefix_cookieName + availablePos;
	setCookie(cookieName, cookieValue, expire);
}
/** */
function addQuickLinkMenufooter(obj, pos) {
	if (!obj.find('span').hasClass('filled')) {
		if(!$(obj).hasClass('opened')){
			$(obj).addClass('opened');
			if(DIRECT_BAR_ADD_CLICKED != null && DIRECT_BAR_ADD_CLICKED.attr('id') != $(obj).attr('id')){
				DIRECT_BAR_ADD_CLICKED.removeClass('opened');
			}
			DIRECT_BAR_ADD_CLICKED = obj;
			availableObj = obj;
			availablePos = obj.attr('id');
			$('#removeQuickLink').removeClass('active').addClass('inactive');
			if ($("#menusupfooter").css('display') == 'none') {
				$("#menusupfooter").css('left', pos);
				$("#menusupfooter").fadeIn("fast");
			}
		} else {
			$(obj).removeClass('opened');
			$("#menusupfooter").fadeOut("fast");
		}
	}
	fastClick = false;
	$('#huongdanquicklink').html($('#notFastClickGuide').val());
}
function showQuickLinkMenufooter() {
	if(!$('.icon-supmenuft').hasClass('opened')){
		$('#removeQuickLink').removeClass('active').addClass('inactive');

		if ($("#menusupfooter").css('display') == 'none') {
			$("#menusupfooter").css('left', 1);
			$("#menusupfooter").fadeIn("fast");
		}
		$('.icon-supmenuft').addClass('opened');
		fastClick = true;
		$('#huongdanquicklink').html($('#fastClickGuide').val());
	} else {
		$('.icon-supmenuft').removeClass('opened');
		$("#menusupfooter").fadeOut("fast");
	}
}
function closeQuickLinkMenufooter() {
	$("#menusupfooter").fadeOut("fast");
}
/** */
function editQuickLinkMenufooter(obj, pos) {
	var parentObj = $(obj).parent();
	if(!$(parentObj).hasClass('opened')){
		$(parentObj).addClass('opened');
		if(DIRECT_BAR_EDIT_CLICKED != null && DIRECT_BAR_EDIT_CLICKED.attr('id') != $(parentObj).attr('id')){
			DIRECT_BAR_EDIT_CLICKED.removeClass('opened');
		}
		DIRECT_BAR_EDIT_CLICKED = $(parentObj);
			
		availableObj = parentObj;
		availablePos = parentObj.attr('id');
		$('#removeQuickLink').removeClass('inactive').addClass('active');
		if ($("#menusupfooter").css('display') == 'none') {
			$("#menusupfooter").css('left', pos);
			$("#menusupfooter").fadeIn("fast");
		}
		fastClick = false;
		$('#huongdanquicklink').html($('#notFastClickGuide').val());
	} else {
		$(parentObj).removeClass('opened');
		$("#menusupfooter").fadeOut("fast");
	}
}
/** Tap hop cac function cho phan quick link */
function registerQuickLinks() {
	if (quickLinkTitle.length == 0) {
		$.each($('#menusupfooter .box_icon'), function(i, obj) {
			quickLinkTitle.push($(obj).find('#title').html());
			if($(obj).find('p').hasClass('default1')){
				default1 = obj;
			} else if($(obj).find('p').hasClass('default2')){
				default2 = obj;
			} else if($(obj).find('p').hasClass('default3')){
				default3 = obj;
			} else if($(obj).find('p').hasClass('default4')){
				default4 = obj;
			}
		});
	}

	closeQuickLinkMenuOnClickOutSide();
	loadQuickLinkFromCookie();
	registerPlaceQuickLink();
	registerRemoveQuickLink();
	registerDraggable();

	$('.menu_footer .quickLinkIcon .filled').hover(function() {
		$(this).css('opacity', '1');
	}, function() {
		$(this).css('opacity', '0.5');
	});

	// collapse/expand button
	$('.footer #collapse-expand-icon').click(function() {
		if ($(this).hasClass('collapse')) {
			$('.menu_footer').hide();
			$('.wrapper_footer').css('height', '35px');
			$(this).removeClass('collapse').addClass('expand');
			setCookie(prefix_cookieName + "_collapsed", "Y", expire);
		} else {
			$('.menu_footer').show();
			$('.wrapper_footer').css('height', 'auto');
			$(this).removeClass('expand').addClass('collapse');
			setCookie(prefix_cookieName + "_collapsed", "", expire);
		}
		return false;
	});
	
	// restore default
	$('.directBar-restoreDefault').hover(function(){
		$(this).find('span').removeClass('inactive').addClass('active');
	}, function(){
		$(this).find('span').removeClass('active').addClass('inactive');
	});
	$('.directBar-restoreDefault').click(function(){
		setDefaultQuickLinkIcons();

		var obj = $('.menu_footer #5');
		obj.find('a').attr('href', "");
		obj.find('a').attr('target', "");
		obj.find('img').attr('src', "");
		obj.find('img').hide();
		obj.find('p').html("");
		obj.find('span').removeClass('filled');
		obj.css('cursor', 'default');
		//set cookie
		availablePos = 5;
		setQuickLinkToCookie("");
		_addingAddButton(5);
	});
	
	// add hover function for add button ("+")
	var lis = $('#placedQuickLinksList li');
	$.each(lis, function(i, li){
		$(li).hover(function(){
			if(!$(li).find('span').hasClass('filled')){
				$(this).find('addButton').removeClass('inactiveAdd').addClass('activeAdd');
			}
			// on hover : set opacity by 1
			$(li).find('img').css('opacity', 1);
			$(li).find('p').css('color', '#F39200');
		}, function(){
			if(!$(li).find('span').hasClass('filled')){
				$(this).find('addButton').removeClass('activeAdd').addClass('inactiveAdd');
			}
			// out hover : set opacity by 0.5
			$(li).find('img').css('opacity', '0.5');
			$(li).find('p').css('color', '');
		});
	});
}
function registerDraggable() {
	$('#quickLinksList li').draggable( {
		helper : "clone",
		start : function() {
			dragObj = $(this);
			selfDrag = false;
		},
		stop : function() {
			dragObj = null;
		}
	});

	$('#quickLinksList #removeQuickLink').draggable('disable');

	$("#placedQuickLinksList li")
			.droppable(
					{
						hoverClass : "borderr",
						drop : function(event, ui) {
							if (dragObj != null) {
								if (selfDrag) {
									selfDrag = false;
									if (dragObj.find('span').hasClass('filled')) {
										var url1 = $(dragObj).find('a').attr(
												'href');
										var target1 = $(dragObj).find('a')
												.attr('target');
										var img1 = $(dragObj).find('img').attr(
												'src');
										var title1 = $(dragObj).find('p')
												.html();
										var url2 = $(this).find('a').attr(
												'href');
										var target2 = $(this).find('a').attr(
												'target');
										var img2 = $(this).find('img').attr(
												'src');
										var title2 = $(this).find('p').html();
										var isFilled = $(this).find('span')
												.hasClass('filled');
										// $(this) is one of the two object which be swapped
										$(this).find('a').attr('href', url1);
										$(this).find('a').attr('target',
												target1);
										$(this).find('img').attr('src', img1);
										$(this).find('img').show();
										$(this).find('p').html(title1);
										$(this).find('span').addClass('filled');
										$(this).css('cursor', 'default');
										// values will be set to the cookie
										var cookieValue = new Array();
										cookieValue[0] = url1;
										cookieValue[1] = target1;
										cookieValue[2] = img1;
										cookieValue[3] = quickLinkTitle
												.indexOf(title1);
										availablePos = $(this).attr('id');
										//set cookie
										setQuickLinkToCookie(cookieValue);

										if (isFilled) {
											// do swap
											// the second
											var secondObj = $('#placedQuickLinksList ' + '#' + dragObj
													.attr('id'));
											secondObj.find('a').attr('href',
													url2);
											secondObj.find('a').attr('target',
													target2);
											secondObj.find('img').attr('src',
													img2);
											secondObj.find('img').show();
											secondObj.find('p').html(title2);
											secondObj.find('span').addClass(
													'filled');
											secondObj.css('cursor', 'default');
											// values will be set to the cookie
											cookieValue = new Array();
											cookieValue[0] = url2;
											cookieValue[1] = target2;
											cookieValue[2] = img2;
											cookieValue[3] = quickLinkTitle
													.indexOf(title2);
											availablePos = $(secondObj).attr(
													'id');
											//set cookie
											setQuickLinkToCookie(cookieValue);
										} else {
											// clear
											// the second
											var secondObj = $('#placedQuickLinksList ' + '#' + dragObj.attr('id'));
											secondObj.find('a').attr('href', '');
											secondObj.find('a').attr('target',
													'');
											secondObj.find('img').attr('src',
													'');
											secondObj.find('img').hide();
											secondObj.find('p').html('');
											secondObj.find('span').removeClass(
													'filled');
											secondObj.css('cursor', 'pointer');
											// delete cookie
											setCookie(prefix_cookieName
													+ $(secondObj).attr('id'),
													'', -1);
											_clearAddButton($(this).attr('id'));
											_addingAddButton(dragObj.attr('id'));
										}
									}
								} else {
									var url = dragObj.find('a').attr('href');
									var target = dragObj.find('a').attr(
											'target');
									var img = dragObj.find('img').attr('src');
									var title = dragObj.find('#title').html();

									$(this).find('a').attr('href', url);
									$(this).find('a').attr('target', target);
									$(this).find('img').attr('src', img);
									$(this).find('img').show();
									$(this).find('p').html(title);
									$(this).find('span').addClass('filled');
									$(this).css('cursor', 'default');

									// values will be set to the cookie
									var cookieValue = new Array();
									cookieValue[0] = url;
									cookieValue[1] = target;
									cookieValue[2] = img;
									cookieValue[3] = quickLinkTitle
											.indexOf(title);
									availablePos = $(this).attr('id');
									//set cookie
									setQuickLinkToCookie(cookieValue);
									_clearAddButton(availablePos);
								}
							}
						}
					});
	$("#placedQuickLinksList .quickLinkIcon").draggable( {
		helper : "clone",
		start : function() {
			selfDrag = true;
			dragObj = $(this);
		},
		stop : function() {
			if (selfDrag) {
				$(this).find('a').attr('href', '');
				$(this).find('a').attr('target', '');
				$(this).find('img').attr('src', '');
				$(this).find('img').hide();
				$(this).find('p').html('');
				$(this).find('span').removeClass('filled');
				$(this).css('cursor', 'pointer');
				// delete cookie
				setCookie(prefix_cookieName + $(this).attr('id'), '', -1);
				
				$('#placedQuickLinksList #' + $(this).attr('id')).removeClass('opened');
				_addingAddButton($(this).attr('id'));
	}
}
	});
}
/** close quick link menu when click outside */
function closeQuickLinkMenuOnClickOutSide() {
	var mouse_is_inside = false;
	$('#menusupfooter').hover(function() {
		mouse_is_inside = true;
	}, function() {
		mouse_is_inside = false;
	});
	$('body').mouseup(function() {
		if (!mouse_is_inside) {
			$('#menusupfooter').hide();
		}
	});
}
function _mappingDataFromOSCToBarIcon(dataFromOsc, id){
	_clearAddButton(id);
	var url = $(dataFromOsc).find('p a').attr('href');
	var target = $(dataFromOsc).find('p a').attr('target');
	var img = $(dataFromOsc).find('p img').attr('src');
	var title = $(dataFromOsc).find('#title').html();

	var obj = $('.menu_footer').find('#' + id);
	obj.find('a').attr('href', url);
	obj.find('a').attr('target', target);
	obj.find('img').attr('src', img);
	obj.find('img').show();
	obj.find('p').html(title);
	obj.find('span').addClass('filled');
	obj.css('cursor', 'default');
	//set cookie
	availablePos = id;
	setQuickLinkToCookie(new Array(url, target, img, quickLinkTitle.indexOf(title)));
}
function setDefaultQuickLinkIcons(){
	// direct board
	_mappingDataFromOSCToBarIcon(default1, 1);

	// nhan dinh TT
	_mappingDataFromOSCToBarIcon(default2, 2);
	
	// tin VNDIRECT
	_mappingDataFromOSCToBarIcon(default3, 3);
	
	// Dat lenh nhanh
	_mappingDataFromOSCToBarIcon(default4, 4);
}
function _addingAddButton(pos){
	$('#placedQuickLinksList #' + pos).find('addButton').addClass('inactiveAdd');
}
function _clearAddButton(pos){
	$('#placedQuickLinksList #' + pos).find('addButton').removeClass('activeAdd').removeClass('inactiveAdd');
}