/*************************************************************************************
* @author 
*
* Define Home Object
* using the flowing query to extend homeOption
*
* jQuery.extend(jQuery.HomeOption, {
* });
*
*************************************************************************************/
(function($) {
$.latestMarketNewsOption = function() {};
$.extend($.latestMarketNewsOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},	
	form : {
		name : "#fHome",
		divs : {
		lastestmarketnews        			: "divLastestMarketNews.id"
		}
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: LatestMarketNewsClazzHome Class
*
*************************************************************************************/
function LatestMarketNewsClazzHome() {
	this.validator = null;
	
	jQuery.extend(jQuery.marketOverviewOption, {
		clazzHandler : this
	});
}
/*************************************************************************************
* init getOption
**************************************************************************************/
LatestMarketNewsClazzHome.prototype.getOption = function () {
	return $.latestMarketNewsOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
LatestMarketNewsClazzHome.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();
};
/*************************************************************************************
* load Market News functionality
**************************************************************************************/
LatestMarketNewsClazzHome.prototype.loadMarketNews = function() {
	var objLatestMarketNewsHelper = new LatestMarketNewsHelper();
	var _latestMarketNewsClazzHome = new LatestMarketNewsClazzHome();
	
	var opts = _latestMarketNewsClazzHome.getOption();
	var marketNewsDiv = $(opts.form.divs.lastestmarketnews);
	var divLastestMarketNews = document.getElementById(marketNewsDiv.selector);
	var formFields = {"showin" : objLatestMarketNewsHelper.SHOWIN_HOME};
    var options = {
             action : objLatestMarketNewsHelper.URL,
             callbackPostSubmit : function (responseText, statusText) {
	             try {
	            	 if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {                                                                                                
                         $.web_message.error(null, responseText.error);
                         //+++ update market news news
	            	 } else {
	            		 //Latest Market News processing
                         var strContent = '<h5><b><a href="#">' + LATEST_MARKET_NEWS_TITLE +  '</a></b></h5>';
	            		 divLastestMarketNews.innerHTML = '';
		                 var model = responseText.model;
		                 //clear News content
		                 if(model != null && model.ifoNews != null) {
		                	 strContent += '<p class="Title"><a href="#"> ' + model.ifoNews.newsHeader + ' </a></p>';
		            		 strContent += '<cite> ' + model.ifoNews.newsAbstract + ' </cite><br />';		                	 
                         } 
		                 divLastestMarketNews.innerHTML = strContent;
	            	 }
	             } catch (e) {
	                 alert(e);
	             }
             }
	 };
	 $.web_formAways.ajaxNav(formFields, options);
};
