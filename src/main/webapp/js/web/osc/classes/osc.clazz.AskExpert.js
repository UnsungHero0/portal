/*************************************************************************************
* @author  HuyLV
*
* Define question Object
* using the flowing query to extend AskExpert
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
* @public: AskExpert Class
*
*************************************************************************************/
function AskExpert() {
	
}

/*************************************************************************************
* init getOption
**************************************************************************************/
AskExpert.prototype.getOption = function () {
	return $.options;
};

/*************************************************************************************
* Load Image
**************************************************************************************/
AskExpert.prototype.loadImage = function (div) {
	$(div).empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />')
};

/*************************************************************************************
* Populate questions
**************************************************************************************/
AskExpert.prototype.populateData = function (data, options) {
	if (data.model) {
		try{
			var result = data.model.questions;
			var div = $(options.div).empty();
			var owner = this;
			var length = result.length;
			var str = '';
			$.each(result, function(i, item){
				str +=
						'<li>' + 
							'<b style="color:#FF8C00;">' + ($(item.questionContent).text() == '' ? item.questionContent : $(item.questionContent).text()) + ' <span style="color:#000000;font-weight:bold">(' + item.createdBy + ')</span> ' + '</b>' + 
							'<p>' + item.answerContent + '</p>' +
						'</li>'
				;
			})
			div.append(str);
			var funcOptions = {
                goto_func: 'changePage'
			};
			var idOptions = {
                navContainer : 'navigator'
			};
			$.web_paging.showVInfoItem(data.model.pagingInfo, funcOptions, idOptions);
		}catch(e) {
			alert(e);
		}
	}
};
