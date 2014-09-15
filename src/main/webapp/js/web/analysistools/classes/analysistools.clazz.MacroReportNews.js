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
$.options = function() {};
$.extend($.options, {
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: OscClazzNewContent Class
*
*************************************************************************************/
function MacroReportNews() {
	
}

/*************************************************************************************
* init getOption
**************************************************************************************/
MacroReportNews.prototype.getOption = function () {
	return $.options;
};

/*************************************************************************************
* Load Image
**************************************************************************************/
MacroReportNews.prototype.loadImage = function () {
	$("#macroReportNews table tbody").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />')
};

/*************************************************************************************
* Populate Daily News
**************************************************************************************/
MacroReportNews.prototype.processPdfFileList = function (pdfFileList, fileNames, header) {
	var s = '';
	$.each(pdfFileList, function(i, item){
		s += '<a href="javascript:download(\'' + pdfFileList[i] + '\', \'research\', \'' + fileNames[i] + '\')">';
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
MacroReportNews.prototype.populateMacroReportNews = function (data, att/*Attachment*/) {
	if (data.model) {
		try{
			var tbody = $("#macroReportNews table tbody");
			tbody.empty();
			var owner = this;
			$.each(data.model.searchResult, function(i, item){
				tbody.append(
					'<tr>' + 
                        '<td width="80"><span class="txtText">' + item.newsDateStr + '</span></td>' +
                        '<td align="left"><span class="txtText bluetext">' + owner.processPdfFileList(item.pdfFileList, item.fileNames, item.newsHeader) + '</span></td>' +
                        '<td width="140"><span class="txtText">' + item.newsResource + '</span></td>' + 
                        '<td width="100" align="center"><span class="txtText">' + eval("LANGUAGES." + item.locale) + '</span></td>' + 
                        '<td width="55" align="center" class="col_end">' + 
                        	'<span class="txtText">' + 
                        		owner.processPdfFileList(item.pdfFileList, item.fileNames) +
							'</span>' + 
						'</td>' +
                    '</tr>'
				);
			})
			
			var funcOptions = {
                goto_func: 'changePage'
			};
			var idOptions = {
                navContainer : 'macroReportNewsNav'
			};
			$.web_paging.showItem(data.model.pagingInfo, funcOptions, idOptions);
		}catch(e) {
			alert(e);
		}
	}
};
