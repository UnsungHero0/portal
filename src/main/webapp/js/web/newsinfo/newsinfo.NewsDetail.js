$().ready(function() {
	//+++Date
    $('#fHistoricalPrice_FromDate').datepicker({
        changeMonth: true,
        changeYear: true,
        onSelect: function (){return false;},
        onClose: function (){return false;}
    });
	
    $('#fHistoricalPrice_ToDate').datepicker({
        changeMonth: true,
        changeYear: true,
        onSelect: function (){return false;},
        onClose: function (){return false;}
    });
    
    // load most viewed news
    loadMostViewedNews_TTPT($('#news-newsType').val());
    
    //+++ Search news with date
	if (firstVideoURL != '') {
		makePlayer(firstVideoURL);
	}
	
	$('.section').click(function() {
		var desc = $(this).metadata().desc;
		$('#videoDesc').html(desc);
		var videoURL = encodeURIComponent($(this).metadata().videoURL);
		attachmentId = $(this).metadata().id;
		makePlayer(videoURL);
	});
	
	$('.back').click(function(){
		var url = $.web_utils.getUrlVar('url');
		if($.web_utils.isNotEmpty(url)) {
			window.location = eval(url) + window.location.href.substring(window.location.href.indexOf('?'));
		} else {
			history.go(-1);
		}
	});
	newsId = $('#NewsDetail_NewsId').val();
	newsType = $('#NewsDetail_NewsType').val();
	locale= $('#NewsDetail_Local').val();
	var url;
	if (newsType == 'VNDIRECT' || newsType == 'IR')
		url = URL_NEWS_DETAILS_2;
	else if (newsType == 'Expert')
		url = URL_NEWS_DETAILS_Expert;
	else if (newsType == 'MacVN')
		url = URL_NEWS_DETAILS_MacVN;
	else if (newsType == 'MacWorld')
		url = URL_NEWS_DETAILS_MacWorld;
	else if (newsType == 'Disclousure')
		url = URL_NEWS_DETAILS_Disclousure;
	else if (newsType == 'IPO')
		url = URL_NEWS_DETAILS_IPO;
	else if (newsType == 'DailyReport')
		url = URL_NEWS_DETAILS_DailyReport;
	else if (newsType == 'PM')
		url = URL_NEWS_DETAILS_PM;
	
	var loadMoreNewsChoice = $('#loadMoreNewsChoice').val();
	if(loadMoreNewsChoice == 'IR-Disclosure'){
		loadMoreNews_Disclosure('quan-he-co-dong-vndirect', 'VND');
	} else if(loadMoreNewsChoice == 'SI-Disclosure'){
		loadMoreNews_Disclosure('thong-tin-co-phieu', '');
	} else if(loadMoreNewsChoice == 'IR-CompanyEvent'){
		loadMoreNews_CompanyEvents('quan-he-co-dong-vndirect', 'VND');
	} else if(loadMoreNewsChoice == 'SI-CompanyEvent'){
		loadMoreNews_CompanyEvents('thong-tin-co-phieu', '');
	} else {
		loadOtherInDayNews(newsId, newsType, locale, url);
		loadOtherNews(newsId, newsType, locale, url);
	}

	// register doZoomNewsDetail function
	doZoomNewsDetail();
});

var att;

var player;
function playerReady(obj) {
	player = document.getElementById(obj['id']);
	player.addModelListener("STATE", "stateListener");
};

function stateListener(obj) {
	oldState = obj.oldstate;
	currentState = obj.newstate;
	if (currentState == "PLAYING") {
		updateHit(attachmentId);
	}
}

function makePlayer(videoURL) {
	jwplayer = new SWFObject("../../../flash/player.swf","ply","250","200","9","#FFFFFF");
	jwplayer.addParam("allowfullscreen","true");
	jwplayer.addParam("allowscriptaccess","always");
	jwplayer.addParam("flashvars","file=" + videoURL + "&type=video&image=../../../flash/playerStart.jpg");
	jwplayer.write("player");
}
/** Load 'Tin khác' */
function loadOtherNews(newsId, newsType, locale, url) {
	if (newsId) {		
		$.ajax({
		   type: "POST",
		   url: URL_GET_OTHER_NEWS,
		   data: 'newsId=' + newsId + '&type=' + newsType + '&pagingInfo.offset=10',
		   dataType: "json",
		   success: function(data) {
				var strListOtherNews = '';
				if(data.model.searchResult != null){
					for (i=0;i<data.model.searchResult.length;i++) {
						infoNews = data.model.searchResult[i];
						strListOtherNews +='';
						strListOtherNews +='<li class="n_other_news_list">';
						strListOtherNews += '<a href="'+infoNews.urlDetail+'">'+infoNews.newsHeader+'</a>'+ '<span class="newsDate"> - ' 
	                					+ $.web_utils.dateFormat2Show(infoNews.newsDate, 'dd/mm/yyyy') + '</span>' ;
						//strListOtherNews += getNewsDetailUrl(infoNews.newsId, infoNews.newsHeader, infoNews.newsType, att, url) + ' (' + infoNews.displayNewsDate + ')';
						strListOtherNews +='</li>';
					}
					
					if(strListOtherNews != ''){
						$('#NewsDetail_ListOtherNews_All_Content').show();
						$('#NewsDetail_ListOtherNews').html(strListOtherNews);
					} else {
						$('#NewsDetail_ListOtherNews_All_Content').hide();
					}
					
					if (data.model.searchResult.length==0) {
						$('#NewsDetail_ListOtherNews_All_Content').html('');
					}
			   	}
			}
		});
	}
}

function loadOtherInDayNews(newsId, newsType, locale, url) {
	if (newsId) {
		$.ajax({
		   type: "POST",
		   url: URL_GET_OTHER_INDAY_NEWS,
		   data: 'newsId=' + newsId + '&type=' + newsType + '&pagingInfo.offset=10',
		   dataType: "json",
		   success: function(data) {
				var strListOtherNews = '';
				if(data.model.searchResult != null){
					for (var i=0;i<data.model.searchResult.length;i++) {
						infoNews = data.model.searchResult[i];
						if (infoNews.isHotNews=='Y') {
							strListOtherNews +='';
							strListOtherNews += '<li class="n_other_news_list">';
							strListOtherNews += '<a href="'+infoNews.urlDetail+'">'+infoNews.newsHeader+'</a>' + '<span class="newsDate"> - ' 
	            					+ $.web_utils.dateFormat2Show(infoNews.newsDate, 'dd/mm/yyyy') + '</span>'  + ' <span class="icon-hot"></span>';
							strListOtherNews += '</li>';
						} else {
							strListOtherNews +='';
							strListOtherNews += '<li class="n_other_news_list">';
							strListOtherNews += '<a href="'+infoNews.urlDetail+'">'+infoNews.newsHeader+'</a>'+ '<span class="newsDate"> - ' 
	            					+ $.web_utils.dateFormat2Show(infoNews.newsDate, 'dd/mm/yyyy') + '</span>' ;
							strListOtherNews += '</li>';
						}
					}
				
					if(strListOtherNews != ''){
						$('#NewsDetail_ListOtherInDayNews_All_Content').show();
						$('#NewsDetail_ListOtherInDayNews').html(strListOtherNews);	
					} else {
						$('#NewsDetail_ListOtherInDayNews_All_Content').hide();
					}
					
					
					if (data.model.searchResult.length==0) {
						$('#NewsDetail_ListOtherInDayNews_All_Content').html('');
					}
				}
		   }
		});
	}
}

function loadMoreNews_Disclosure(pageURL, symbol) {
	var formFields = {
		"pageUrl" : pageURL,
		"type" : 'Disclousure',
		"pagingInfo.offset" : '10',
		"pagingInfo.indexPage" : '1',
		"currentSymbol" : symbol,
	};
	var options = {
		action : $.web_resource.getContext() + "ajax/news/News_GetNewsForSymbolAndTypeList.shtml",
		callbackPostSubmit : function(responseText, statusText) {
			try {
				if (responseText.error !== null
						&& responseText.error !== 'undefined'
						&& responseText.error.hasErrors) {
					$.web_message.error(null, responseText.error);
					
				} else {
					$('#irDisclosureNewsDetail').html("");
					$('#irDisclosureNewsDetailTitleBar').hide();
					var strContent = '';
					var model = responseText.model;
					if (model != null && model.searchResult != null
							&& model.searchResult != null) {
						strContent = '<ul class="n_list_other list_detail_tt_nd">';
						strContent += '<div class="n_list_other_detail">';
						$.each(model.searchResult, function(i, newsinfo) {
							strContent += '<li class="n_other_news_list">';
							strContent += '<a href="'
									+ newsinfo.urlDetail
									+ '">'
									+ newsinfo.newsHeader
									+ ' ('
									+ newsinfo.newsResource
									+ ')</a>'
									+ '<span class="newsDate"> - '
									+ $.web_utils.dateFormat2Show(
											newsinfo.newsDate, 'dd/mm/yyyy')
									+ '</span>';
							strContent += '</li>';
						});
						strContent += '</div></ul>';
					}

					$('#irDisclosureNewsDetail').html(strContent);
					$('#irDisclosureNewsDetailTitleBar').show();
				}
			} catch (e) {
			}
		}
	};
	$.web_formAways.ajaxNav(formFields, options);
}

function loadMoreNews_CompanyEvents(pageURL, symbol) {
	var formFields = {
		"pageUrl" : pageURL,
		"type" : 'MacVN',
		"pagingInfo.offset" : '10',
		"pagingInfo.indexPage" : '1',
		"currentSymbol" : symbol,
	};
	var options = {
		action : $.web_resource.getContext() + "ajax/news/News_GetNewsForSymbolAndTypeList_CompanyEvent.shtml",
		callbackPostSubmit : function(responseText, statusText) {
			try {
				if (responseText.error !== null
						&& responseText.error !== 'undefined'
						&& responseText.error.hasErrors) {
					$.web_message.error(null, responseText.error);
					
				} else {
					$('#irDisclosureNewsDetail').html("");
					$('#irDisclosureNewsDetailTitleBar').hide();
					var strContent = '';
					var model = responseText.model;
					if (model != null && model.searchResult != null
							&& model.searchResult != null) {
						strContent = '<ul class="n_list_other list_detail_tt_nd">';
						strContent += '<div class="n_list_other_detail">';
						$.each(model.searchResult, function(i, newsinfo) {
							strContent += '<li class="n_other_news_list">';
							strContent += '<a href="'
									+ newsinfo.urlDetail
									+ '">'
									+ newsinfo.newsHeader
									+ ' ('
									+ newsinfo.newsResource
									+ ')</a>'
									+ '<span class="newsDate"> - '
									+ $.web_utils.dateFormat2Show(
											newsinfo.newsDate, 'dd/mm/yyyy')
									+ '</span>';
							strContent += '</li>';
						});
						strContent += '</div></ul>';
					}

					$('#irDisclosureNewsDetail').html(strContent);
					$('#irDisclosureNewsDetailTitleBar').show();
				}
			} catch (e) {
			}
		}
	};
	$.web_formAways.ajaxNav(formFields, options);
}

function loadMostViewedNews_TTPT(newsType){
	var url = $.web_resource.getContext() + "ajax/news/getMostViewedNewsAjax.shtml?newsType=" + newsType;
	$('#ttpt-mostViewedNews-box').load(url);
}