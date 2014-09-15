//var objNewsHelper = new NewsHelper();

(function($) {
$.latestMarketNewsOption = function() {};
$.extend($.latestMarketNewsOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},	
	form : {
		name : "#fListedMarket",
		divs : {
		//lastestmarketnews        			: "divLastestMarketNews.id"
		}
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: LatestMarketNewsClazzListedMarket Class
*
*************************************************************************************/
function LatestMarketNewsClazzListedMarket() {
	this.validator = null;
	
	jQuery.extend(jQuery.marketOverviewOption, {
		clazzHandler : this
	});
}
/*************************************************************************************
* init getOption
**************************************************************************************/
LatestMarketNewsClazzListedMarket.prototype.getOption = function () {
	return $.latestMarketNewsOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
LatestMarketNewsClazzListedMarket.prototype.init = function() {
	//var _this = this;
	//var opts = _this.getOption();
};
/*************************************************************************************
* load Market News functionality
**************************************************************************************/
LatestMarketNewsClazzListedMarket.prototype.loadMarketNews = function() {
	//var objLatestMarketNewsHelper = new LatestMarketNewsHelper();
	//var _latestMarketNewsClazzListedMarket = new LatestMarketNewsClazzListedMarket();
	
	//var opts = _latestMarketNewsClazzListedMarket.getOption();
	//var marketNewsDiv = $(opts.form.divs.lastestmarketnews);
	//var divLastestMarketNews = document.getElementById(marketNewsDiv.selector);
	//var formFields = {"showin" : objLatestMarketNewsHelper.SHOWIN_LISTED_MARKET};
	var formFields = {};
    var options = {
        action : $.web_resource.getContext() + "/ajax/news/LastestMarketNewsAJAX_GetLastestNews.shtml",
        callbackPostSubmit : function (responseText, statusText) {
            try {
            	if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {                                                                                                
                    $.web_message.error(null, responseText.error);
                    //+++ update market news news
            	} else {
            		var model = responseText.model;
            		if(model != null && model.ifoNews != null) {
	            		$('#tqtt_content_new_header').attr("href", model.urlDetail);
	            		$('#tqtt_content_new_header').html(model.ifoNews.newsHeader);
	            		$('#tqtt_content_new_abstract').html(model.ifoNews.newsAbstract);
	            		$('#tqtt_content_new_url_detail').attr("href", model.urlDetail);
	            		$('.content_tongquanthitruong .content_new').show();
            		}
            	}
            } catch (e) {
                alert(e);
            }
        }
	};
	$.web_formAways.ajaxNav(formFields, options);
};
