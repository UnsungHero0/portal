$().ready(function() {

	$.ajax({
		type: "POST",
		dataType: "json",
		data: "type=VNDConrner&pagingInfo.offset=10",
		url: URL_GET_NEWS_BY_TYPES,
		success: function(data){
			populateContent(data);
		},
		beforeSend: null
	});
});

populateContent = function(data) {
	if (data.model) {
		try {
			var att;
			var cat = 'img';
			var vndCorner = $('#fVndCorner_content');			
			var strContent = '<table cellpadding="0" cellspacing="4" border="0" width="742">';
			var firstOtherNews = true;
			var fileName = '';
			if (data.model.searchResult != null && data.model.searchResult.length > 0) {				
				$.each(data.model.searchResult, function(i, obj){
					if (i%2 == 0) {
						strContent += '<tr>';
					}
					strContent += '<td width="365" valign="top"><div class="clearfix">';
					$.each(obj.imagesList, function(j, obj2){
						var idx = obj2.indexOf("||");
						if (idx > -1 && obj2.substring(0, idx) == 'Img_VNDCorner') {						
							strContent += '<span style="float: left;margin-right: 13px;border: 1px solid #b7b7db"><img src="' + viewDownloadImage(obj2.substring(idx+2), cat, 115, 150) + '" /></span>';
						}
					});
					$.each(obj.pdfFileList, function(j, item){							
							strContent += '<p><b class="bluetext">' + '<a href="javascript:download(\'' + item + '\', \'newsAttach\', \'' + obj.pdfFileNames[j] + '\')">' + obj.newsHeader + '</a></b></p>';
					});				
					strContent += '<p class="textSapo">' + obj.newsDateStr + '</p>';
					strContent += '<p class="textSapo">VNDIRECT</p>';				
					$.each(obj.pdfFileList, function(j, item){							
						strContent += '<p class="downloadMaz"><a href="javascript:download(\'' + item + '\', \'newsAttach\', \'' + obj.pdfFileNames[j] + '\')">' + 'DOWNLOAD</a></p>';
					});
					strContent += '</div></td>';
					
					if (i%2 == 0) {
						strContent += '<td>&nbsp;</td>';
					}
					
					if (i%2 == 1) {
						strContent += '</tr>';
						strContent += '<tr><td colspan="3"><div><img src="' + $.web_resource.getContext() + '/images/front/spacer.gif" width="1" height="20" /></div></td></tr>';
					}
				});				
			}		
			if (data.model.searchResult.length%2 == 1) {
				strContent += '</tr>';
			}
			strContent += '</table>';			
			vndCorner.html(strContent);
			
			var funcOptions = {
	        	goto_func : "_goTo"
			};
	        var idOptions = {
	        	navContainer : "web_navVndCorner"
	        };
	        $.web_paging.showRightNewsItem(data.model.pagingInfo, funcOptions, idOptions);

		} catch(e) {
			alert(e);
		}
	}	
};

function _goTo(webNavId, index) {
	try {
		$.ajax({
			type: "POST",
			dataType: "json",
			data: "type=VNDConrner&pagingInfo.offset=10&pagingInfo.indexPage=" + index,
			url: URL_GET_NEWS_BY_TYPES,
			success: function(data){
				populateContent(data);
			},
			beforeSend: null
		});	
	} catch (e) {
		alert("_goTo(): " + e);
	}
}