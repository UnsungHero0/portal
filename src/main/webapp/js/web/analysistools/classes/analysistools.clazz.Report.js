/*************************************************************************************
* @author  HuyLV
*
* Define report Object
* using the flowing query to extend reportOption
*
* jQuery.extend(jQuery.reportOption, {
* });
*
*************************************************************************************/

(function($) {
$.reportOption = function() {};
$.extend($.reportOption, {
	dailyNewsNavigator : "dailyNewsNavigator",
	dailyVideoNavigator : "dailyVideoNavigator",
	weeklyVideoNavigator : "weeklyVideoNavigator",
	monthlyVideoNavigator : "monthlyVideoNavigator",
	latestReportNavigator : "latestReportNavigator"
});
})(jQuery);

/*************************************************************************************
* @author
*
* @public: OscClazzNewContent Class
*
*************************************************************************************/
function ATClazzReport() {

}

/*************************************************************************************
* init getOption
**************************************************************************************/
ATClazzReport.prototype.getOption = function () {
	return $.reportOption;
};

/*************************************************************************************
* Load Image
**************************************************************************************/
ATClazzReport.prototype.loadImageMarketDailyNews = function () {
	$('#' + $.reportOption.dailyNewsNavigator).empty();
	$("#dailyNews table tbody").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />')
};

ATClazzReport.prototype.loadImageVideoNews = function (id) {
	if (typeof id !== 'undefined') {
		if (id == 'dailyVideo') {
			$('#' + $.reportOption.dailyVideoNavigator).empty();
		} else if (id == 'weeklyVideo') {
			$('#' + $.reportOption.weeklyVideoNavigator).empty();
		} else if (id == 'monthlyVideo') {
			$('#' + $.reportOption.monthlyVideoNavigator).empty();
		}
	}	
	$("#" + id + " table tbody").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />')
};

ATClazzReport.prototype.loadImageLatestReport = function () {
	$('#' + $.reportOption.latestReportNavigator).empty();
	$("#latestReport table tbody").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />')
};

/*************************************************************************************
* Populate Daily News
**************************************************************************************/
ATClazzReport.prototype.populateMarketDailyNews = function (data, newsType, att/*Attachment*/) {
	if (data.model) {
		try{
			var tbody = $("#dailyNews table tbody");
			tbody.empty();
			var owner = this;
			var displayText = '';
			$.each(data.model.searchResult, function(i, item){
				if (typeof att !== 'undefined') {
					displayText = owner.processPdfFileList(item.pdfFileList, item.fileNames[0], item.attachmentsId, item.newsHeader);
				} else {
					displayText = getNewsDetailUrl(item.newsId, item.newsHeader, newsType, att, URL_NEWS_DETAILS_DailyReport);
				}
				tbody.append(
						'<tr>' +
	                        '<td width="80"><span class="txtText">' + item.newsDateStr + '</span></td>' +
	                        '<td align="left"><span class="txtText bluetext">' + displayText + '</span></td>' +
	                        '<td width="75" align="center" class="col_end">' +
	                        	'<span class="txtText">' +
	                        		owner.processPdfFileList(item.pdfFileList, item.fileNames[0], item.attachmentsId) +
								'</span>' +
							'</td>' +
	                    '</tr>'
				);
			})

			var funcOptions = {
                goto_func: 'changePage'
			};
			var idOptions = {
                navContainer : $.reportOption.dailyNewsNavigator
			};
			$.web_paging.showItem(data.model.pagingInfo, funcOptions, idOptions);
		}catch(e) {
			alert(e);
		}
	}
};


/*************************************************************************************
* Populate Daily News
**************************************************************************************/
ATClazzReport.prototype.populateVideoNews = function (data, newsType) {
	if (data.model) {
		try{			
			var tbody = $("#dailyVideo table tbody");
			if (newsType == 'WeeklyReport') {
				tbody = $("#weeklyVideo table tbody");
			} else if (newsType == 'MonthlyReport') {
				tbody = $("#monthlyVideo table tbody");
			}
			tbody.empty();
			var owner = this;
			var displayText = '';			
			var att;
			var strContent = '';
			$.each(data.model.searchResult, function(i, obj){
				if (i%2 == 0) {
					strContent += '<tr>';
				}
				strContent += '<td width="50%" valign="top"><div class="leftinfor">';
				strContent += '<p>' + '<a href="#"><img src="../../../flash/video_bantin.jpg" class="IconArticleVideo" /></a>' + '</p>';
				strContent += '<p class="TotalTimeVideo">' + '</p>';
				strContent += '</div>';
				strContent += '<div class="rightinfor">';
				if (obj.hasVideoAtt) {
					strContent += '<p class="rightinfor_title">' + getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType, att, URL_NEWS_DETAILS_DailyReport) + '</p>';
				} else { 
					strContent += '<p class="rightinfor_title">' + getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType, att, URL_NEWS_DETAILS_DailyReportNoVideo) + '</p>';
				}
				strContent += '<p class="descMaz">' + $.web_utils.fomatLong(obj.attachmentVideoHit) + ' views</p>';
				if (obj.hasVideoAtt) {
					strContent += '<p class="playvideo">' + getNewsDetailUrl(obj.newsId, 'Play Video', obj.newsType, att, URL_NEWS_DETAILS_DailyReport) + '</p>';
				}
				strContent += '</div><div class="ClearBoth"></div></td>';
				
				if (data.model.searchResult.length%2 == 1) {
					strContent += '</tr>';					
				}				
			})
			tbody.append(strContent);
			
			var funcOptions = {
				goto_func: 'changePage'
			};
			var idOptions = {
	            navContainer : $.reportOption.dailyVideoNavigator
			};
			if (newsType == 'WeeklyReport') {
				idOptions = {
					navContainer : $.reportOption.weeklyVideoNavigator
				};
			} else if (newsType == 'MonthlyReport') {
				idOptions = {
					navContainer : $.reportOption.monthlyVideoNavigator
				};
			}
			$.web_paging.showItem(data.model.pagingInfo, funcOptions, idOptions);
		}catch(e) {
			alert(e);
		}
	}
};

/*************************************************************************************
* Populate Daily News
**************************************************************************************/
ATClazzReport.prototype.processPdfFileList = function (pdfFileList, fileName, attachmentsId, header) {
	var s = '';
	$.each(pdfFileList, function(i, item){
		var attachments = ''
		if (typeof attachmentsId !== 'undefined') {attachments += attachmentsId[i]}
		s += '<a href="javascript:download(\'' + pdfFileList[i] + '\', \'newsAttach\', \'' + fileName + '\', \'' + attachments + '\' )">';
		if (header == undefined) {
			if (pdfFileList[i].lastIndexOf('.pdf') != -1) {
				s += '<img src="' + $.web_resource.getContext() + '/images/icons/typepdf.gif" /></a>&nbsp;';
			} else if (pdfFileList[i].lastIndexOf('.doc') != -1) {
				s += '<img src="' + $.web_resource.getContext() + '/images/icons/typedoc.gif" /></a>&nbsp;';
			} else if (pdfFileList[i].lastIndexOf('.xls') != -1) {
				s += '<img src="' + $.web_resource.getContext() + '/images/icons/typeexcel.gif" /></a>&nbsp;';
			} else if (pdfFileList[i].lastIndexOf('.zip') != -1 || pdfFileList[i].lastIndexOf('.rar') != -1) {
				s += '<img src="' + $.web_resource.getContext() + '/images/icons/typezip.gif" /></a>&nbsp;';
			}
		} else {
			s += header + '</a>';
		}
	})
	return s;
}

/*************************************************************************************
* Populate Daily News
**************************************************************************************/
ATClazzReport.prototype.populateLatestReport = function (data) {
	if (data.model) {
		try{
			var tbody = $("#latestReport table tbody");
			tbody.empty();
			var owner = this;
			$.each(data.model.searchResult, function(i, item){
				tbody.append(
						'<tr>' +
                        '<td width="80"><span class="txtText">' + item.newsDateStr + '</span></td>' +
                        '<td align="left"><span class="txtText bluetext">' + owner.processPdfFileList(item.pdfFileList, item.fileNames[0], item.attachmentsId, item.newsHeader) + '</span></td>' +
                        '<td width="190"><span class="txtText cssreport" style="color:#006699">' + item.newsTypeDesc + '</td>' +
                        '<td width="75" align="center" class="col_end">' +
                        	'<span class="txtText">' +
                        		owner.processPdfFileList(item.pdfFileList, item.fileNames[0], item.attachmentsId) +
							'</span>' +
						'</td>' +
                    '</tr>'
				);
			})

			var funcOptions = {
                goto_func: 'changePage'
			};
			var idOptions = {
                navContainer : $.reportOption.latestReportNavigator
			};
			$.web_paging.showItem(data.model.pagingInfo, funcOptions, idOptions);
		}catch(e) {
			alert(e);
		}
	}
};
