$().ready(function() {
	
	newsType = 'Disclousure';
	
	
	url = URL_NEWS_DETAILS_Disclousure;
	
	
	// article news
	$.ajax({
		type: "POST",
		dataType: "json",
		data: "type=" + newsType + "&pagingInfo.offset=11",
		url: URL_GET_NEWS_BY_TYPES,
		success: function(data){
			populateContent(data);
		},
		beforeSend: function() {
			$("#aritcleListNews").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />');
		}
	});
	
	// get list most viewed news
	$.ajax({
		type: "POST",
		dataType: "json",
		data: "type=" + newsType + "&pagingInfo.offset=10",
		url: URL_GET_MOST_VIEWED_NEWS_BY_TYPES,		
		success : function(data) {
			populateMostViewedNews(data);
		},
		beforeSend : function() {
			$("#mostViewNews").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />');
		}
	});	
});

var att;
var url;
var newsType;

populateContent = function(data, go2) {
	if (data.model) {
		try {			
			var cat = 'img';			
			var strContent = '<table cellpadding="0" cellspacing="0" border="0" width="742">';			
			if (data.model.searchResult != null && data.model.searchResult.length > 0) {				
				$.each(data.model.searchResult, function(i, obj){					
					if (i == 0 && (typeof go2 == 'undefined' || (typeof go2 !== 'undefined' && data.model.pagingInfo.indexPage == 1))) {												
						return true;
					}					
					var j = i-1;
					if (typeof go2 !== 'undefined' && data.model.pagingInfo.indexPage > 1)
						j = i;
					if (j%2 == 0) {
						strContent += '<tr>';
					}
					strContent += '<td width="365" valign="top"><div style="margin-left: 13px; padding: 10px 0;">';
					if (obj.imagesList != null && obj.imagesList.length > 0) {
						$.each(obj.imagesList, function(j, obj2){
							var idx = obj2.indexOf("||");
							if (idx > -1 && obj2.substring(0, idx) == 'Image') {						
								strContent += '<img src="' + viewDownloadImage(obj2.substring(idx+2), cat, 130, 100) + '" class="img-aritcle-bottom" />';
							}
						});
					} else {
						strContent += '<img src="' + $.web_resource.getContext() + '/images/img/directNews.jpg" class="img-aritcle-bottom" />';
					}
					strContent += '<p class="TitleAR">' + getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType, att, url) + '</p>';						
					strContent += '<p class="textcreatnews">' + obj.newsDateTimeStr + ' | ' + obj.newsDateStr + ' | <b>' + obj.newsResource + '</b></p>';
					strContent += '<div class="descAR" style="margin-left: 122px;">';
					strContent += '<p>' + obj.newsAbstract + '</p>';						
					strContent += '</div><div class="ClearBoth"></div>';
					strContent += '</div></td>';
					
					if (j%2 == 0) {
						strContent += '<td>&nbsp;</td>';
					}
					
					if (j%2 == 1) {
						strContent += '</tr>';
						strContent += '<tr><td style="line-height: 10px"><div  style="border-bottom: 1px solid #CCCCCC; width: 95%; margin: 0 13px;"></div></td>';
						strContent += '<td style="line-height: 10px">&nbsp;</td><td><div  style="border-bottom: 1px solid #CCCCCC; width: 95%; margin: 0 13px;"></div></td></tr>';
					}

					if (data.model.searchResult.length == i+1) {
						$('#latestNewsListId').val(obj.newsId);
					}
				});
				
				if (data.model.searchResult.length%2 == 1) {
					strContent += '</tr>';
				}								
			}
			
			strContent += '</table>';
			$('#aritcleListNews').html(strContent);
			
			var funcOptions = {
	        	goto_func : "_goTo"
			};
	        var idOptions = {
	        	navContainer : "web_navAritcleList"
	        };
	        $.web_paging.showItem(data.model.pagingInfo, funcOptions, idOptions);
	        
	        populateOtherNews($('#latestNewsListId').val(), $('#currentNewsType').val());			
		} catch(e) {
			alert(e);
		}
	}	
};

function _goTo(webNavId, index) {
	try {
//		$("#fNews_indexPage").val(index);
		var offset = 10;
		if (index == 1)
			offset = 11;
		$.ajax({
			type: "POST",
			dataType: "json",
			data: "type=" + $('#currentNewsType').val() + "&pagingInfo.offset=" + offset + "&pagingInfo.indexPage=" + index,
			url: URL_GET_NEWS_BY_TYPES,
			success: function(data){
				populateContent(data, true);
			},
			beforeSend: function() {
				$("#aritcleListNews").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />');
			}
		});
	} catch (e) {
		alert("_goTo(): " + e);
	}
}

function populateOtherNews(newsId, newsType) {
	if (newsId) {		
		$.ajax({
			   type: "POST",
			   url: URL_GET_OTHER_NEWS,
			   data: 'newsId=' + newsId + '&type='+newsType + '&pagingInfo.offset=18',
			   dataType: "json",
			   success: function(data) {
					var strListOtherNews = '';	
					$.each(data.model.searchResult, function(i, obj){
						strListOtherNews +='<p class="p-othernews">&bull;&nbsp;&nbsp;';
						strListOtherNews += getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType, att, url);
						strListOtherNews +='<span class="date">(' + obj.newsDateStr + ')</span></p>';						
					});
					$('#otherNews').html(strListOtherNews);
			   },
			   beforeSend: function(){
				   $("#otherNews").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />');
			   }
		});
	}
}

populateMostViewedNews = function(data) {
	if (data.model) {
		try {			
			var strContent = '';	
			$.each(data.model.searchResult, function(i, obj){
				strContent +='<p class="p-othernews">&bull;&nbsp;&nbsp;';
				strContent += getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType, att, url);
				strContent +='<span class="date">(' + obj.newsDateStr + ')</span></p>';						
			});
			
			$('#mostViewNews').html(strContent);					
		} catch(e) {
			alert(e);
		}
	}
};