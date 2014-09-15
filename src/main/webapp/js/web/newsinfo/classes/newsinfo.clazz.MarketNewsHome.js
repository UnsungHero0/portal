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
$.marketNewsOption = function() {};
$.extend($.marketNewsOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},	
	form : {
		name : "#fHome",
		divs : {
			marketnews        			: "divMarketNews.id"
		}
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: MarketNewsClazzHome Class
*
*************************************************************************************/
function MarketNewsClazzHome() {
	this.validator = null;
	
	jQuery.extend(jQuery.marketOverviewOption, {
		clazzHandler : this
	});
}
/*************************************************************************************
* init getOption
**************************************************************************************/
MarketNewsClazzHome.prototype.getOption = function () {
	return $.marketNewsOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
MarketNewsClazzHome.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();
};
/*************************************************************************************
* load Market News functionality
**************************************************************************************/
MarketNewsClazzHome.prototype.loadMarketNews = function() {
	var objMarketNewsHelper = new MarketNewsHelper();
	var _marketNewsClazzHome = new MarketNewsClazzHome();
	
	var opts = _marketNewsClazzHome.getOption();
	var marketNewsDiv = $(opts.form.divs.marketnews);
	var divMarketNews = document.getElementById(marketNewsDiv.selector);
	var formFields = {"showin" : objMarketNewsHelper.SHOWIN_HOME};
    var options = {
             action : objMarketNewsHelper.URL,
             callbackPostSubmit : function (responseText, statusText) {
	             try {
	            	 if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {                                                                                                
                         $.web_message.error(null, responseText.error);
                         //+++ update market news news
	            	 } else {
	            		 //Market News processing
	            		 var strContent = '<table cellpadding="0" cellspacing="0" border="0" width="100%" class="tbl_center">';
	            		 strContent += '<tr bgcolor="#e4e8ec"><td style="border-right:1px solid #ffffff"><b class="color4">' + MARKET_NEWS_COL1 + '</b></td>';
	            		 strContent += '<td style="border-right:1px solid #ffffff"><b class="color4">' + MARKET_NEWS_COL2 + '</b></td>';
	            		 strContent += '<td style="border-right:1px solid #ffffff"><b class="color4"> ' + MARKET_NEWS_COL3 + ' </b></td>';
	            		 strContent += '<td><b class="color4">File</b></td>';
	            		 strContent += '</tr>';
                         
	            		 divMarketNews.innerHTML = '';
		                 var model = responseText.model;
		                 //clear News content
		                 if(model != null && model.searchResult != null && model.searchResult != null) {
		                	 $.each(model.searchResult, function(i, newsinfo){
		                		 strContent += '<tr><td><span class="date">' + $.web_utils.dateFormat2Show(newsinfo.newsDate, 'dd/mm/yyyy') + '</span></td>';
			                	 strContent += '<td><b>' + newsinfo.newsHeader + '</b></td>';
			                	 strContent += '<td> ' + newsinfo.newsResource + ' </td>';
			                	 $.each(newsinfo.newsAttWithoutVideos, function(j, itemFile) {
			                		 if (itemFile!= null)
			                			 strContent += '<td><a href="' + itemFile.uriLink + '"><img src="' + $.web_resource.getContext() + 'images/front/file.gif" /></a></td>';
			                		 else
			                			 strContent += '<td></td>'
			                	 })
			                	 strContent += '</tr>';
		         			})
                         } 
		                 strContent +='<tr><td colspan="4"><div class="clearfix" style="padding-top:7px"><span class="login_2 clearfix fl"><a href="#"><b>' + VNDIRECT_ANALYSIS_LOGIN + '</b></a></span><span class="textnormal fl"> ' + VNDIRECT_ANALYSIS_DESCRIPTION +' </span></div></td></tr>';
		                 strContent +='</table>';
		                 divMarketNews.innerHTML = strContent;
	            	 }
	             } catch (e) {
	                 alert(e);
	             }
             }
	 };
	 $.web_formAways.ajaxNav(formFields, options);
};
