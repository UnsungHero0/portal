$().ready(function() {

	$.ajax({
		type: "POST",
		dataType: "json",
		data: "type=Broker&pagingInfo.offset=15",
		url: URL_GET_NEWS_BY_TYPES,
		success: function(data){
			populateContent(data);
		},
		beforeSend: function() {
			$("#fBroker_content").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />');
		}
	});
});

populateContent = function(data) {
	if (data.model) {
		try {
			var att;
			var cat = 'img';
			var brokerContent = $('#fBroker_content');			
			var strContent = '';
			var firstOtherNews = true;
			if (data.model.searchResult != null && data.model.searchResult.length > 0) {
				$.each(data.model.searchResult, function(i, obj){
					if (i < 5) {
						strContent += '<div class="ListArticle">';
						strContent += '<table cellpadding="0" cellspacing="0" border="0" width="100%"><tr><td valign="top">';
						strContent += '<p class="TitleAR">' + getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType, att, URL_NEWS_DETAILS_Broker) + '</p>';						
						strContent += '<p class="textcreatnews">' + obj.newsDateTimeStr + ' | ' + obj.newsDateStr + ' | <b>' + obj.newsResource + '</b></p>';
						strContent += '<div class="descAR clearfix">';						
						$.each(obj.imagesList, function(j, obj2){
							var idx = obj2.indexOf("||");
							if (idx > -1 && obj2.substring(0, idx) == 'Image') {						
								strContent += '<img src="' + viewDownloadImage(obj2.substring(idx+2), cat, 130, 100) + '" class="imgAR" />';
							}
						});
						strContent += '<p>' + obj.newsAbstract + '</p>';						
						strContent += '</div></td>';
						strContent += '<td valign="top" width="130px">';						
						$.each(obj.imagesList, function(j, obj2){		
							var idx = obj2.indexOf("||");
							if (idx > -1 && obj2.substring(0, idx) == 'Img_Broker') {							
								strContent += '<p><img src="' + viewDownloadImage(obj2.substring(idx+2), cat, 120, 144) + '" class="imgCG" /></p>';
								strContent += '<p class="name-CG"><b>' + obj.resource + ' - VNDIRECT</p>';
							}
						});				
						strContent += '</td></tr></table></div>';						
					} else {
						if (firstOtherNews){
							strContent += '<div class="DivOthernews">';
							strContent += '<p class="othernews-header">' + OTHER_NEWS_MESSAGE + '</p>';
							strContent += '<div class="othernews-ct">';
							firstOtherNews = false;
						}
						strContent += '<p class="p-othernews">&bull;&nbsp;&nbsp;' + getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType, att, URL_NEWS_DETAILS_Commentary);						
						strContent += '<br/><b style="padding-left:25px"> - By: </b><i>' +  obj.resource + ' - VNDIRECT</i>';
						strContent += '<span class="date">(' + obj.newsDateStr + ')</span></p>';						
					}
					
				});
				if (!firstOtherNews) {
					strContent += '</div>';
				}
				strContent += '</div>';				
			}						
			brokerContent.html(strContent);			
		} catch(e) {
			alert(e);
		}
	}	
};