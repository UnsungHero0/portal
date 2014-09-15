
$().ready(function() {

	if (firstVideoURL != '' && isOnTradingTime == false) {
		makePlayer(firstVideoURL);
	}

	// date picker
//	$('#date-pick').datepicker();

	// add event handler for video partial
	$('.section').click(function() {
		var desc = $(this).metadata().desc;
		$('#videoDesc').html(desc);
		var videoURL = encodeURIComponent($(this).metadata().videoURL);
		attachmentId = $(this).metadata().id;
		makePlayer(videoURL);
	});
	
	if (LOCALE == 'vn') {
		$.ajax( {
			type : "POST",
			dataType : "json",
			data : 'type=Commentary&pagingInfo.offset=3',
			url : URL_GET_NEWS_BY_TYPES,
			success : function(data) {
				populateComponentNews(data, 1);
			},
			beforeSend : function() {
				$("#commentaryId").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />');
			}
		});
		$.ajax( {
			type : "POST",
			dataType : "json",
			data : 'type=Broker&pagingInfo.offset=3',
			url : URL_GET_NEWS_BY_TYPES,						
			success : function(data) {
				populateComponentNews(data, 2);
			},
			beforeSend : function() {
				$("#brokerId").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />');
			}
		});
		$.ajax( {
			type : "POST",
			dataType : "json",
			data : 'type=Expert&pagingInfo.offset=3',
			url : URL_GET_NEWS_BY_TYPES,						
			success : function(data) {
				populateComponentNews(data, 3);
			},
			beforeSend : function() {
				$("#expertId").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />');
			}
		});
	}
	
	if (LOCALE == 'vn') {
		loadTabMostViewedNews();
	}
});

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
	jwplayer = new SWFObject("../../../flash/player.swf",
			"FLVVideoPlayer", "400", "255", "9", "#FFFFFF");
	jwplayer.addParam("allowfullscreen", "true");
	jwplayer.addParam("allowscriptaccess", "always");
	jwplayer.addParam("flashvars", "file=" + videoURL
			+ "&type=video&image=../../../flash/video_bantin.jpg");
	jwplayer.write("player");
}

function loadTabLastestVideos() {
	// get list lastest videos
	$.ajax({
		type: "POST",
		dataType: "json",
		data : 'newsType=DailyReport&pagingInfo.offset=5',
		url: URL_MARKET_DAILY_NEWS_AJAX,		
		success : function(data) {
			populateTabContent(data, 2);
		},
		beforeSend : function() {
			$("#boxitem2").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />');
		}
	});
}

function loadTabMostViewedNews() {
	// get list most viewed news
	$.ajax({
		type: "POST",
		dataType: "json",
		data : 'type=Commentary,Broker,Expert&pagingInfo.offset=5',
		url: URL_GET_MOST_VIEWED_NEWS_BY_TYPES,		
		success : function(data) {
			populateTabContent(data, 1);
		},
		beforeSend : function() {
			$("#boxitem1").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />');
		}
	});
}

function loadTabMostViewedReports() {
	// get list most viewed reports
	$.ajax({
		type: "POST",
		dataType: "json",
		data : 'pagingInfo.offset=5',
		url: URL_MOST_VIEWED_REPORT_AJAX,		
		success : function(data) {
			populateTabContent(data, 3);
		},
		beforeSend : function() {
			$("#boxitem3").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />');
		}
	});
}

populateComponentNews = function (data, type) {
	if (data.model) {
		try{
			var att;
			var idx;
			var imageUrl;
			var cat = 'img';
			var newsContent;
			if (type == 1)
				newsContent = $('#commentaryId');
			else if (type == 2)
				newsContent = $('#brokerId');
			else
				newsContent = $('#expertId');
			newsContent.html("");
			
			var imageClass = '';
			if (type == 1) {
				imageClass = 'img-thumbsnail';
			} else if (type == 2) {
				imageClass = 'img-thumbsnail-2';
			} else {
				imageClass = 'img-thumbsnail-3';
			}
			
			var strContent = '<div class="textnone_2 clearfix">';
			if (data.model.searchResult != null && data.model.searchResult.length > 0) {
				$.each(data.model.searchResult, function(i, obj){
					if (i == 0) {
						if (type == 1) {							
							strContent += '<h5><b><a href="' + COMMENTARY_MAIN_URL + '">' + COMMENTARY_MESSAGE + '</a></b></h5>';
							//strContent += '<div class="BgrShadowTop"><div class="clearfix BgrShadowBot">';							
							strContent += '<div><div class="clearfix">';
						} else if (type == 2) {
							strContent += '<h5><b><a href="' + BROKER_MAIN_URL + '">' + BROKER_MESSAGE + '</a></b></h5>';
							//strContent += '<div class="BgrShadowTop"><div class="clearfix BgrShadowBot">';
							strContent += '<div><div class="clearfix">';
						} else {
							strContent += '<h5><b><a href="' + EXPERT_MAIN_URL + '">' + EXPERT_MESSAGE + '</a></b></h5>';
						}
						$.each(obj.imagesList, function(j, obj2){
							idx = obj2.indexOf("||");
							if (idx > -1 && obj2.substring(0, idx) == 'Image') {		
								imageUrl = '<img src="' + viewDownloadImage(obj2.substring(idx+2), cat) + '" class="' + imageClass + '"/>';
								if (type == 1) {
									strContent += getNewsDetailUrl(obj.newsId, imageUrl, obj.newsType, att, URL_NEWS_DETAILS_Commentary);									
								} else if (type == 2) {
									strContent += getNewsDetailUrl(obj.newsId, imageUrl, obj.newsType, att, URL_NEWS_DETAILS_Broker);
								} else {
									strContent += getNewsDetailUrl(obj.newsId, imageUrl, obj.newsType, att, URL_NEWS_DETAILS_Expert);
								}								
							}
						});
						if (type == 1) {						
							strContent += '<p><span class="liText-title">' + getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType, att, URL_NEWS_DETAILS_Commentary) + '</span></p>';							
						} else if (type == 2) {
							strContent += '<p><span class="liText-title">' + getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType, att, URL_NEWS_DETAILS_Broker) + '</span></p>';
						} else {							
							strContent += '<span><strong class="Title">"' + obj.newsAbstract + '"</strong>';
							strContent += '<img src="' + $.web_resource.getContext() + '/images/img/new.gif" /> </span>';
							strContent += '<span><u>' + getNewsDetailUrl(obj.newsId, DETAILS_TEXT, obj.newsType, att, URL_NEWS_DETAILS_Expert) +'</u></span>';							
						}
						strContent += '<p class="textcreatnews">' + obj.newsDateTimeStr + ' | ' + obj.newsDateStr + ' | <b>' + obj.newsResource + '</b></p>';
						
						if (type == 2)
							strContent += '<p class="postperson">' + obj.resource + '</p>';
						else if (type == 3)
							strContent += '<div style="margin-left: 100px">' + obj.resource + '</div>';

						if (type == 1) {
							strContent += '<div class="descAR" align="justify">' + obj.newsAbstract + ' <span><u>' + getNewsDetailUrl(obj.newsId, DETAILS_TEXT, obj.newsType, att, URL_NEWS_DETAILS_Commentary) +'</u></span></div>';
							strContent += '</div></div>'; 
						} else if (type == 2) {
							strContent += '<div class="descAR" align="justify">' + obj.newsAbstract + ' <span><u>' + getNewsDetailUrl(obj.newsId, DETAILS_TEXT, obj.newsType, att, URL_NEWS_DETAILS_Broker) +'</u></span></div>';
							strContent += '</div></div>'; 
						}
						
						strContent += '<div class="textnoneTool">';
						
						//if (type == 1 || type == 2)
							//strContent += '<ul class="othernews" style="background:none; margin-top:7px;">';							
						//else
							strContent += '<ul class="othernews">';
					} else {
						if (type == 1) {
							strContent += '<li>' + getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType, att, URL_NEWS_DETAILS_Commentary);
							strContent += '<span class="date">(' + obj.newsDateStr + ')</span></li>';
						} else if (type == 2) {
							strContent += '<li>' + getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType, att, URL_NEWS_DETAILS_Broker);
							strContent += '<span class="date">(' + obj.newsDateStr + ')</span>';
							strContent += '<br/><b style="padding-left:25px;color:#FF6600"> - ' + BY +': </b>' +  obj.resource + ' - VNDIRECT</li>';
						} else {						
							strContent += '<li>' + getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType, att, URL_NEWS_DETAILS_Expert);
							strContent += '<span class="date">(' + obj.newsDateStr + ')</span></li>';
						}
					}
				});
				strContent += '</ul></div>';
			}
						
			strContent += '</div>';
			newsContent.html(strContent);
			
		}catch(e) {
			alert(e);
		}
	}
};

populateTabContent = function (data, type) {
	if (data.model) {
		try{
			var att;
			var idx;
			var imageUrl;
			var cat = 'img';
			var tabContent;
			if (type == 1)
				tabContent = $('#boxitem1');
			else if (type == 2)
				tabContent = $('#boxitem2');
			else
				tabContent = $('#boxitem3');
			tabContent.html("");
			
			var strContent = '<div class="tabcontrol-infor">';
			strContent += '<ul class="box-top-video">';
			if (data.model.searchResult != null && data.model.searchResult.length > 0) {
				$.each(data.model.searchResult, function(i, obj){					
					strContent += '<li class="clearfix">';
					if (type == 1) {
						$.each(obj.imagesList, function(j, obj2){
							idx = obj2.indexOf("||");
							if (idx > -1 && obj2.substring(0, idx) == 'Image') {		
								imageUrl = '<img class="img-top-news" src="' + viewDownloadImage(obj2.substring(idx+2), cat) + '" />';
								if (obj.newsType == 'Commentary') {
									strContent += getNewsDetailUrl(obj.newsId, imageUrl, obj.newsType, att, URL_NEWS_DETAILS_Commentary);									
								} else if (obj.newsType == 'Broker') {
									strContent += getNewsDetailUrl(obj.newsId, imageUrl, obj.newsType, att, URL_NEWS_DETAILS_Broker);
								} else if (obj.newsType == 'Expert') {
									strContent += getNewsDetailUrl(obj.newsId, imageUrl, obj.newsType, att, URL_NEWS_DETAILS_Expert);
								}								
							}
						});
						
						if (obj.newsType == 'Commentary') {
							strContent += '<p align="justify"><span class="liText-title">' + getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType, att, URL_NEWS_DETAILS_Commentary) + '</span> </p>';
						} else if (obj.newsType == 'Broker') {
							strContent += '<p align="justify"><span class="liText-title">' + getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType, att, URL_NEWS_DETAILS_Broker) + '</span> </p>';
						} else if (obj.newsType == 'Expert') {
							strContent += '<p align="justify"><span class="liText-title">' + getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType, att, URL_NEWS_DETAILS_Expert) + '</span> </p>';
						}						
					} else if (type == 2) {						
						strContent += '<img class="img-top-news" src="' + $.web_resource.getContext() + '/images/img/img_38.jpg" />';
						strContent += '<p><span class="liText-title">' + getNewsDetailUrl(obj.newsId, obj.newsHeader, 'DailyReport', att, URL_NEWS_DETAILS_DailyReport) + '</span> </p>';					
					} else { 
						strContent += '<img class="img-top-news" src="' + $.web_resource.getContext() + '/images/img/img_36.jpg" />';						
						$.each(obj.pdfFileList, function(j, item){
							strContent += '<p><span class="liText-title">' + '<a href="javascript:download(\'' + item + '\', \'newsAttach\', \'' + obj.fileNames + '\')">' + obj.newsHeader + '</a></span> </p>';
						});
					}
					
					if (type == 1) {					
						strContent += '<p class="textcreatnews">' + $.web_utils.fomatLong(obj.hit) + ' views</p>';
						strContent += '<p class="textcreatnews">' + obj.newsDateTimeStr + ' | ' + obj.newsDateStr + ' | <b>' + obj.newsResource + '</b></p>';						
					} else if (type == 2) {
						strContent += '<p class="watchvideo">' + getNewsDetailUrl(obj.newsId, '<span>Xem video</span>', obj.newsType, att, URL_NEWS_DETAILS_DailyReport) + '</p>';
					} else { 
						strContent += '<p class="textcreatnews">' + obj.newsDateTimeStr + ' | ' + obj.newsDateStr + ' | <b>' + obj.newsResource + '</b></p>';
						$.each(obj.pdfFileList, function(j, item){
							strContent += '<p class="downloadMaz"><a href="javascript:download(\'' + item + '\', \'newsAttach\', \'' + obj.fileNames + '\')">' + 'DOWNLOAD</a></p>';
						});
					}
					strContent += '</li>';
				});
			}
			strContent += '</ul></div>';
			tabContent.html(strContent);
			
		}catch(e) {
			alert(e);
		}
	}
};