// slide news on top (DisplayNews.jsp)
var curr = 0;
var urlArr = new Array();
var intervalID = "";
var OPENED_ANSWER = null;
//default font size of news detail content
var defaultSize = 2;
var defaultSpanFontSize = '13px';
var defaultPFontSize = '13px';

$(document).ready(function(){
	
	// load news
	$('.boxnewhot_top .newsContent').load($.web_resource.getContext() + "ajax/news/NewsGetVNDSNews.shtml", function(){
		// slide news on top
		slideVNDSNews();		
	});	
	
	renderFunctionForQuyTrinhMoTK();
	
	$('#keywordInputOnBanner').keypress(function(e) {
        if (e.keyCode == 13 && $('#keywordInputOnBanner').val() != "") {
            document.fSearchNews_banner.submit();
        }
    });
});

function doSearchNews() {
    if ($('#keywordInputOnBanner').val() != $('#commonSearchNewsText').val()) {
        document.fSearchNews_banner.submit();
    }
}


// Requires jQuery!
/*
$.ajax({
    url: "https://www.vndirect.com.vn/s/en_US-ctwtiu/664/18/1.0.23-beta/_/download/batch/com.atlassian.jira.collector.plugin.jira-issue-collector-plugin:issuecollector/com.atlassian.jira.collector.plugin.jira-issue-collector-plugin:issuecollector.js?collectorId=e6baa50f",
    type: "get",
    cache: true,
    dataType: "script"
});
*/
/** */
function slideVNDSNews(){
	var urlArr = [];
	urlArr.push($('#linkDetail1').val());
	urlArr.push($('#linkDetail2').val());
	urlArr.push($('#linkDetail3').val());
	urlArr.push($('#linkDetail4').val());
	urlArr.push($('#linkDetail5').val());

	 slideNews(urlArr);
	 intervalID = setInterval(function() {
		 slideNews(urlArr);
	 }, 8888);
		 
	 $('.boxnewhot_top .newdetails #newsLink').hover(function() {
		if (intervalID != "") {
			clearInterval(intervalID);
			intervalID = "";
		}
	}, function() {
		if (intervalID == "") {
			slideNews(urlArr);
			intervalID = setInterval(function() {
				slideNews(urlArr);
			}, 8888);
		}
	});
}
/** */
function slideNews(urlArr){
	$('.boxnewhot_top .newdetails #newsLink').fadeOut(1000, function(){
		$('.boxnewhot_top .newdetails #newsLink').html(urlArr[curr % urlArr.length]);
	});
	
	$('.boxnewhot_top .newdetails #newsLink').fadeIn('fast');
	
	curr += 1;
}

/**
 * set a Cookie
 */
function setCookie(name, value, expire) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + expire);
	var cookieValue = escape(value) + ";expires=" + exdate.toUTCString()+";path=/";
	document.cookie = name + "=" + cookieValue;
}
/**
 * get a Cookie
 */
function getCookie(name) {
	var i, x, y, ARRcookies = document.cookie.split(";");
	for (i = 0; i < ARRcookies.length; i++) {
		x = ARRcookies[i].substr(0, ARRcookies[i].indexOf("="));
		y = ARRcookies[i].substr(ARRcookies[i].indexOf("=") + 1);
		x = x.replace(/^\s+|\s+$/g, "");
		if (x == name) {
			return unescape(y);
		}
	}
	return '';
}
/** ************* */
function doQuickSearchSymbol(quickSearchSymbol, fromModule) {
	if(quickSearchSymbol.toUpperCase()=='VNINDEX') {
		window.document.location.href=$.web_resource.getContext() + "thong-ke-thi-truong-chung-khoan/tong-quan.shtml?id=1";
	} else if(quickSearchSymbol.toUpperCase()=='VN30'){
		window.document.location.href=$.web_resource.getContext() + "thong-ke-thi-truong-chung-khoan/tong-quan.shtml?id=2";
	} else if(quickSearchSymbol.toUpperCase()=='HNX'){
		window.document.location.href=$.web_resource.getContext() + "thong-ke-thi-truong-chung-khoan/tong-quan.shtml?id=3";
	} else if(quickSearchSymbol.toUpperCase()=='HNX30'){
		window.document.location.href=$.web_resource.getContext() + "thong-ke-thi-truong-chung-khoan/tong-quan.shtml?id=4";
	} else if(quickSearchSymbol.toUpperCase()=='UPCOM'){
		window.document.location.href=$.web_resource.getContext() + "thong-ke-thi-truong-chung-khoan/tong-quan.shtml?id=5";
	} else {
		var url = $.web_resource.getContext();
		if(fromModule == URL_PROFILE) {
			url +=  "ho-so-doanh-nghiep/" + quickSearchSymbol.toLowerCase();
		} else if(fromModule == URL_KEY_STATISTIC) {
			url +=  "thong-ke-co-ban/" + quickSearchSymbol.toLowerCase();
		} else if(fromModule == URL_FINANCIAL_REPORT) {
			url +=  "bao-cao-tai-chinh/" + quickSearchSymbol.toLowerCase();
		} else if(fromModule == URL_FORECAST) {
			url +=  "chi-tieu-ke-hoach/" + quickSearchSymbol.toLowerCase();
		}else if(fromModule == URL_MAJOR_HOLDER){
			url +=  "co-dong-chinh/" + quickSearchSymbol.toLowerCase();
		}else if(fromModule == URL_INSIDE_TRANSACTION){
			url +=  "giao-dich-noi-bo/" + quickSearchSymbol.toLowerCase();
		}else if(fromModule == URL_FLASH_CHART){
			url +=  "bieu-do-ky-thuat/" + quickSearchSymbol.toLowerCase();
		}else if(fromModule == URL_COMPANY_NEWS){
			url +=  "tin-doanh-nghiep/" + quickSearchSymbol.toLowerCase();
		}else if(fromModule == URL_COMPANY_EVENTS) {
			url +=  "tin-lien-quan/" + quickSearchSymbol.toLowerCase();
		} else if(fromModule == URL_BALANCE_SHEET) {
			url +=  "bang-can-doi-ke-toan/" + quickSearchSymbol.toLowerCase();
		} else if(fromModule == URL_INCOME_STATEMENT) {
			url +=  "bao-cao-ket-qua-kinh-doanh/" + quickSearchSymbol.toLowerCase();
		} else if(fromModule == URL_CASH_FLOW) {
			url +=  "bao-cao-luu-chuyen-tien-te/" + quickSearchSymbol.toLowerCase();
		} else if(fromModule == URL_HISTORY_PRICE) {
			url +=  "lich-su-gia/" + quickSearchSymbol.toLowerCase();
		} else if(fromModule == URL_FOREIGN_TRADING) {
			url +=  "giao-dich-nha-dau-tu-nuoc-ngoai/" + quickSearchSymbol.toLowerCase();
		} else if(fromModule == URL_TRADING_STATISTC) {
			url +=  "ket-qua-giao-dich/" + quickSearchSymbol.toLowerCase();
		} else {
			url += "tong-quan/" + quickSearchSymbol.toLowerCase();
		}
		url += ".shtml";

		window.document.location.href = url;
		return;
	}
}
/** */
function selectSymbol(symbol) {
	doQuickSearchSymbol(symbol);
	return;
}
/** */
function doCheckExistedSymbol(url) {
	try {
		var formFields = {};
		var options = {
			action : URL_SYMBOL_CHECK_EXIST,
			callbackExecuteFail : function(error) {
				$.log("doCheckExistedSymbol() - " + error);
			},
			callbackPostSubmit : function(responseText, statusText) {
				if (responseText.error !== null
						&& responseText.error !== 'undefined'
						&& responseText.error.hasErrors) {
					$.web_message.error(null, responseText.error);
				} else {
					//var symbol = responseText.model.symbol;
					if (responseText.model !== null
							&& responseText.model.symbol !== null)
						window.document.location.href = url;
					else
						window.document.location.href = URL_SYMBOL_SEARCH_SYMBOL;
				}
			}
		};
		$.web_formAways.ajaxNav(formFields, options);
	} catch (e) {
		if (DEBUG) {
			$.log("doCheckExistedSymbol() - " + e);
		}
	}
}
/** */
function doViewSector(sectorCode) {
	try {
		var formFields = {};
		var options = {
			action : URL_LIST_SECTOR + '?sectorCode=' + sectorCode
		};
		jQuery.web_formAways.nav(formFields, options);
	} catch (e) {
		alert("__doViewSector: " + e);
	}
}
/** */
function doViewIndustry(sectorCode, industryCode) {
	try {
		var formFields = {};
		var options = {
			action : URL_LIST_SECTOR + '?sectorCode=' + sectorCode
					+ '&industryCode=' + industryCode
		};
		jQuery.web_formAways.nav(formFields, options);
	} catch (e) {
		alert("__doViewIndustry: " + e);
	}
}

/**
 * Download a file
 * @param url
 * @param cat
 * @param filename
 * @param id attachmentsId 
 * @param cat one of these value in (docs, newsAttch, formApp, research)
 */
function download(fileUri, cat, filename, id) {
/*	
	var str = URL_REPORT_DOWNLOAD;
	if (typeof fileUri !== 'undefined') {
		str += "?inputPath=" + fileUri
	}
	
	if (typeof cat !== 'undefined') {
		str += "&category=" + cat
	}
	
	if (typeof filename !== 'undefined') {
		str += "&filename=" + filename
	}
*/
	
	var str = $.web_resource.urlDownload();	
	if (typeof cat !== 'undefined') {
		if (cat == 'newsAttach' || fileUri.indexOf("nextcom") > -1) {
			str += 'news-attachment/';
		} else if (cat == 'research') {
			str += 'research/';
		}
	}	
	if (typeof fileUri !== 'undefined') {
		str += fileUri;
	}
	
	if (typeof filename !== 'undefined') {
		str += '&fileName=' + filename;
	}	
	
	str += '&catType=REPORT';
	var index=filename.lastIndexOf(".");
	var fileType = filename.substr(index+1, filename.length - index - 1).trim();
	if(fileType.toLowerCase() != "doc" && fileType.toLowerCase() != "docx"){
		str += '&contentDispositionFromRequest=inline';
	}
	window.open(str, "_blank","");
	//location.replace(str);
	
	if (typeof id !== 'undefined') {
		updateHit(id);
	}
}

function downloadDocument(fileUri, filename) {
	var str = $.web_resource.urlDownload();	
	if (typeof fileUri !== 'undefined') {
		str += 'documents/' + fileUri;
	}	
	if (typeof filename !== 'undefined') {
		str += '&fileName=' + filename;
	}	
	str += '&catType=REPORT';
	location.replace(str);
}

function downloadResource(fileUri, filename) {
	var str = $.web_resource.urlDownload();	
	if (typeof fileUri !== 'undefined') {
		str += fileUri;
	}	
	if (typeof filename !== 'undefined') {
		str += '&fileName=' + filename;
	}	
//	str += '&catType=RESOURCE';
	location.replace(str);
}

function viewDownloadImage(fileUri, cat, width, height) {
	var str = $.web_resource.urlDownloadThumbnail();	
	var report = false;
	if (typeof cat !== 'undefined') {
		if (cat == 'img') {
			str += 'image/';
			report = true;
		}
	}
	if (typeof fileUri !== 'undefined') {
		str += fileUri;
	}	
	if (report) {
		str += '&catType=REPORT';
	}
	if (typeof width !== 'undefined') {
		str += '&thumbWidth=' + width;
	}
	if (typeof height !== 'undefined') {
		str += '&thumbHeight=' + height;
	}
	return str;
}

function openStreamQuotes(url) {
	var properties = "titlebar=yes,status=yes,menubar=no,toolbar=no,scrollbars=yes,resizable=yes,location=no,title='VNDirect Online...'";
	var newName = "StreamQuotes"; // + suffix;
	var win = window.open(url, newName, properties, true);
	win.focus();
	return;
}

function reloadCaptcha() {
	$("#captcha-image").attr("src", URL_CAPTCHA	+ "?rnd=" + Math.random());
}

function openNewWindow(url, width, height){
    var nLeft = (window.screen.width - width)/2;
    var nTop = (window.screen.height - height)/2;
    var properties = "width=" + width
                     + ",height=" + height 
                     + ",top="+ nTop
                     + ",left=" + nLeft 
                     + ",titlebar=yes,status=yes,menubar=no,toolbar=no,scrollbars=yes,resizable=yes,location=no,title='VNDirect Online...'";
	var newName = "newWin"; // + suffix;
	var win = window.open(url, newName , properties, true);	
	win.focus();    
    return;
}

function doPreview1() {
	var url = "OnlineAccount.toPdf?PDF_TYPE=1";
	window.open(url, "_blank", "");
	return;
}
function doPrint1() {
	var url = "OnlineAccount.toPdf?PDF_TYPE=4";
	window.open(url, "_blank", "");
	return;
}
function doPreview2() {
	var url = "OnlineAccount.toPdf?PDF_TYPE=2";
	window.open(url, "_blank", "");
	return;
}
function doPrint2() {
	var url = "OnlineAccount.toPdf?PDF_TYPE=5";
	window.open(url, "_blank", "");
	return;
}
function doPreview3() {
	var url = "OnlineAccount.toPdf?PDF_TYPE=3";
	window.open(url, "_blank", "");
	return;
}
function doPrint3() {
	var url = "OnlineAccount.toPdf?PDF_TYPE=6";
	window.open(url, "_blank", "");
	return;
}
function doGrantPower() {
	var url = "OnlineAccount.toPdf?PDF_TYPE=7";
	window.open(url, "_blank", "");
	return;
}

function updateHit(id) {
	$.ajax({
		   type: "POST",
		   url: URL_HIT,
		   data: [{name : 'attachmentId', value : id}],
		   dataType: "json"
	});
}

function getPosLeft(obj) /*:int*/ {

    var oNode = obj;
    var iLeft = 0;
    
    //while(oNode.tagName != "BODY") {
    while(oNode){
        iLeft += oNode.offsetLeft;
        oNode = oNode.offsetParent;        
    }    
    return iLeft;
}
/** function for show/hide QA quy trinh mo tai khoan */
function renderFunctionForQuyTrinhMoTK(){
	var lines = $('.quytrinhmotaikhoan-Line');
	$.each(lines, function(i,line){
		$(line).find('.ask').click(function(){
			var aId = $(line).find('.ask').attr('id');
			var theAnswer = $(line).find('#answer'+aId);
			if(!theAnswer.hasClass('opened')){
				
				if(OPENED_ANSWER != null){
					OPENED_ANSWER.removeClass('opened');
					OPENED_ANSWER.hide();
					$(OPENED_ANSWER).parent().parent().find('.ask').css('color','#333333');
				}
				theAnswer.addClass('opened');
				theAnswer.show();
				//re-assign
				OPENED_ANSWER = theAnswer;
				$(line).find('.ask').css('color','#f39200');
			} else {
				$(line).find('.ask').css('color','#333333');
				theAnswer.removeClass('opened');
				theAnswer.hide();
			}
		});
	});
}
// this function support for highstock only
function getTickInterval(max, min){
	var yAxisStep =(parseInt(max) - parseInt(min))/5;
	if(yAxisStep < 1){
	    return 0.5;
	} else {
		yAxisStep = parseInt(yAxisStep);
	}
	if(yAxisStep < 10){
        return 5;
    } else {
    	return parseInt(yAxisStep / 5) * 5;
    }
	
	return yAxisStep;
}
/** Add for new portal GUI. zoom-in, zoom-out buttons. */
function doZoomNewsDetail() {
	$('.detail_tt_nd .zoomButtons #print').click(function() {
		$('.zoomButtons').hide();
		w = window.open();
		w.document.write($('.detail_tt_nd').html());
		w.print();
		$('.zoomButtons').show();
		w.close();
	});

	$('.detail_tt_nd .zoomButtons #zoomOut').click(function() {
		doNewsDetailZoomOut();	
	});
	
	$('.detail_tt_nd .zoomButtons #noZoom').click(function() {
		doNewsDetailNoZoom();
	});
	$('.detail_tt_nd .zoomButtons #zoomIn').click(function() {
		doNewsDetailZoomIn();
	});
}
function doNewsDetailZoomIn(){
	// apply for <font... element
	var fonts = $('.detail_tt_nd').find('font');
	$.each(fonts, function(i, font) {
		var size = parseInt($(font).attr('size')) + 1;
		$(font).attr('size', size);
		// with font-size stylesheet
		var size2 = $(font).css('font-size');
		size2 = (parseInt(size2.substring(0, size2.length - 2)) + 1) + 'px';
		$(font).css('font-size',size2);
	});

	// apply for <span... element
	var iSpans = $('.detail_tt_nd').find('span');
	$.each(iSpans, function(i, iSpan) {
		var size = $(iSpan).css('font-size');
		size = (parseInt(size.substring(0, size.length - 2)) + 1) + 'px';
		$(iSpan).css('font-size',size);
	});
	
	// apply for <p... element
	var iPs = $('.detail_tt_nd').find('p');
	$.each(iPs, function(i, iP) {
		var size = $(iP).css('font-size');
		size = (parseInt(size.substring(0, size.length - 2)) + 1) + 'px';
		$(iP).css('font-size',size);
	});
}
function doNewsDetailZoomOut(){
	// apply for <font... element
	var fonts = $('.detail_tt_nd').find('font');
	$.each(fonts, function(i, font) {
		var size = parseInt($(font).attr('size')) - 1;
		$(font).attr('size', size);
		// with font-size stylesheet
		var size2 = $(font).css('font-size');
		size2 = (parseInt(size2.substring(0, size2.length - 2)) - 1) + 'px';
		$(font).css('font-size',size2);
	});
	
	// apply for <span... element
	var iSpans = $('.detail_tt_nd').find('span');
	$.each(iSpans, function(i, iSpan) {
		var size = $(iSpan).css('font-size');
		size = (parseInt(size.substring(0, size.length - 2)) - 1) + 'px';
		$(iSpan).css('font-size',size);
	});
	
	// apply for <p... element
	var iPs = $('.detail_tt_nd').find('p');
	$.each(iPs, function(i, iP) {
		var size = $(iP).css('font-size');
		size = (parseInt(size.substring(0, size.length - 2)) - 1) + 'px';
		$(iP).css('font-size',size);
	});
}
function doNewsDetailNoZoom(){
	// apply for <font... element
	var fonts = $('.detail_tt_nd').find('font');
	$.each(fonts, function(i, font) {
		$(font).attr('size', defaultSize);
		$(font).css('font-size',defaultSpanFontSize);
	});
	
	// apply for <span... element
	var iSpans = $('.detail_tt_nd').find('span');
	$.each(iSpans, function(i, iSpan) {
		$(iSpan).css('font-size',defaultSpanFontSize);
	});
	
	// apply for <p... element
	var iPs = $('.detail_tt_nd').find('p');
	$.each(iPs, function(i, iP) {
		$(iP).css('font-size',defaultPFontSize);
	});
}