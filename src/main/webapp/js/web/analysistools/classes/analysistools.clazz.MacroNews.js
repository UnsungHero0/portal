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
function MacroNews() {
	
}

/*************************************************************************************
* init getOption
**************************************************************************************/
MacroNews.prototype.getOption = function () {
	return $.options;
};

/*************************************************************************************
* Load Image
**************************************************************************************/
MacroNews.prototype.loadImage = function (div) {
	$(div).empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />');
};

/*************************************************************************************
* Populate Daily News
**************************************************************************************/
MacroNews.prototype.populateData = function (data, options) {
	if (data.model) {
		try{
			var result = data.model.searchResult;
			var div = $(options.div).empty();
			var length = result.length;

			var str = "";
			str += '<ul class="list_detail_tt_nd">';
			str += '<div class="n_list_other_detail">';
				$.each(result, function(i, item){
					if(i == length - 1){
						str += '<li class="n_other_news_list">';
					}else{						
						str += '<li class="n_other_news_list">';
					}
						str += '<a href="'+item.urlDetail+'">';
							str += '<span class="newsDate">' + item.newsDateStr + ': ' +'</span>' + item.newsHeader;
						str += '</a>';
					str += '</li>';
				});
			str +="</div></ul>";
				
			div.append(str);
			var funcOptions = {
                goto_func: 'changePage'
			};
			var idOptions = {
                navContainer : options.navigator
			};
			$.web_paging.showNewsItem(data.model.pagingInfo, funcOptions, idOptions);
		}catch(e) {
			alert(e);
		}
	}
};
