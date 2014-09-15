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
var objNewsHelper = new NewsHelper();

(function($) {
$.newsinfoOption = function() {};
$.extend($.newsinfoOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},	
	funcName : {
		navFuncName : "_goToMarketNews" // function _goTo(index)
	},
	form : {
		name : "#fHome",
		divs : {
			iponews				: "divIPONews.id",
			vndsnews			: "divVNDSNews.id",
			marketnews  		: "divMarketNews.id",
			publicnews  		: "divPublicNews.id",
			lastestmarketnews 	: "divLastestMarketNews.id"
		},
	
		navDivs : {
			navIponews			: "web_navIPONews",
			navVndsnews			: "web_navVNDSNews",
			navMarketnews  		: "web_navMarketNews",
			navPublicnews  		: "web_navPublicNews"
		},
		
		fields : {
			pagingIndex 	: "#fListedMarket_pagingInfo_indexPage"
		}
	},
	buttons : {
		submit : "#fHome_btnSubmit"
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: NewsOnlineClazzListedMarket Class
*
*************************************************************************************/
function NewsOnlineClazzListedMarket() {
	this.validator = null;
	
	jQuery.extend(jQuery.newsinfoOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
NewsOnlineClazzListedMarket.prototype.getOption = function () {
	return $.newsinfoOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
NewsOnlineClazzListedMarket.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();
};

/*************************************************************************************
* load VNDS News functionality
**************************************************************************************/
NewsOnlineClazzListedMarket.prototype.loadVNDSNews = function() {
	var opts = _newsOnlineClazzListedMarket.getOption();
	var vndsnewsDiv = $(opts.form.divs.vndsnews);
	var divShowVNDSNews = document.getElementById(vndsnewsDiv.selector);
	var formFields = {
             "type" : objNewsHelper.TYPE_MARKET_NEWS,
             "showin" : objNewsHelper.SHOWIN_VNMARKET,
             "pagingInfo.offset" : objNewsHelper.LISTED_MARKET_NUMBER_ITEM,
             "pagingInfo.indexPage" : $(opts.form.fields.pagingIndex).val()
	 };
	 var options = {
             action : objNewsHelper.URL_NEWS,
             callbackPostSubmit : function (responseText, statusText) {
		 		
	             try {
	            	 if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {                                                                                                
                         $.web_message.error(null, responseText.error);
                         //+++ update ipo news
	            	 } else {
	            		//IPO News processing
	            		 var strContent = '';

	            		 divShowVNDSNews.innerHTML = '';
		                 var model = responseText.model;
		                 //clear News content
		                 if(model != null && model.searchResult != null && model.searchResult != null) {
		                	 $.each(model.searchResult, function(i, newsinfo){
		                		 strContent += '<p><span class="date" style="float:left;border:1px #fff solid">' + $.web_utils.dateFormat2Show(newsinfo.newsDate, 'dd/mm/yyyy') + '</span>';
		                		 strContent +=  getNewsDetailUrl(newsinfo.newsId, newsinfo.newsHeader, newsinfo.newsType) + '</p>'; 
		         			})
                         } 
		                 divShowVNDSNews.innerHTML = strContent;
		                 
		                 var funcOptions = {
 		                	goto_func : opts.funcName.navFuncName	 
 		                 };		                 
 		                 var idOptions = {
 		                	navContainer : opts.form.navDivs.navVndsnews
 		                 };
 		                 $.web_paging.showNewsItem(model.pagingInfo, funcOptions, idOptions);
	            	 }
	             } catch (e) {
	                 alert(e);
	             }
             }
	 };
	 $.web_formAways.ajaxNav(formFields, options);
};

/*************************************************************************************
* load IPO News functionality
**************************************************************************************/
NewsOnlineClazzListedMarket.prototype.loadIPONews = function() {
	var opts = _newsOnlineClazzListedMarket.getOption();
	var iponewsDiv = $(opts.form.divs.iponews);
	var divShowIPONews = document.getElementById(iponewsDiv.selector);
	var formFields = {
             "type" : objNewsHelper.TYPE_DISCLOSURE_NEWS,
             "showin" : objNewsHelper.SHOWIN_VNMARKET,
             "pagingInfo.offset" : objNewsHelper.LISTED_MARKET_NUMBER_ITEM,
             "pagingInfo.indexPage" : $(opts.form.fields.pagingIndex).val()
	 };
	 var options = {
             action : objNewsHelper.URL_IPO_NEWS,
             callbackPostSubmit : function (responseText, statusText) {
		 		
	             try {
	            	 if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {                                                                                                
                         $.web_message.error(null, responseText.error);
                         //+++ update ipo news
	            	 } else {
	            		//IPO News processing
	            		 var strContent = '';

	            		 divShowIPONews.innerHTML = '';
		                 var model = responseText.model;
		                 //clear News content
		                 if(model != null && model.searchResult != null && model.searchResult != null) {
		                	 $.each(model.searchResult, function(i, newsinfo){
		                		 strContent += '<p><span class="date" style="float:left;border:1px #fff solid">' + $.web_utils.dateFormat2Show(newsinfo.newsDate, 'dd/mm/yyyy') + '</span>';
		                		 strContent +=  getNewsDetailUrl(newsinfo.newsId, newsinfo.newsHeader, newsinfo.newsType) + '</p>'; 
		         			})
                         } 
                         divShowIPONews.innerHTML = strContent;
                         
                         var funcOptions = {
 		                	goto_func : opts.funcName.navFuncName	 
 		                 };		                 
 		                 var idOptions = {
 		                	navContainer : opts.form.navDivs.navIponews
 		                 };
 		                 $.web_paging.showNewsItem(model.pagingInfo, funcOptions, idOptions);
	            	 }
	             } catch (e) {
	                 alert(e);
	             }
             }
	 };
	 $.web_formAways.ajaxNav(formFields, options);
}

/*************************************************************************************
* load IPO News functionality
**************************************************************************************/
NewsOnlineClazzListedMarket.prototype.loadPublicNews = function() {
	var opts = _newsOnlineClazzListedMarket.getOption();
	var publicnewsDiv = $(opts.form.divs.publicnews);
	var divShowPublicNews = document.getElementById(publicnewsDiv.selector);
	var formFields = {
             "type" : objNewsHelper.TYPE_SHARES_NEWS,
             "showin" : objNewsHelper.SHOWIN_VNMARKET,
             "pagingInfo.offset" : objNewsHelper.LISTED_MARKET_NUMBER_ITEM,
             "pagingInfo.indexPage" : $(opts.form.fields.pagingIndex).val()
	 };
	 var options = {
             action : objNewsHelper.URL_PUBLIC_INFO,
             callbackPostSubmit : function (responseText, statusText) {
		 		
	             try {
	            	 if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {                                                                                                
                         $.web_message.error(null, responseText.error);
                         //+++ update ipo news
	            	 } else {
	            		//Public News processing
	            		 var strContent = '';

	            		 divShowPublicNews.innerHTML = '';
		                 var model = responseText.model;
		                 //clear News content
		                 if(model != null && model.searchResult != null && model.searchResult != null) {
		                	 $.each(model.searchResult, function(i, newsinfo){
		                		 strContent += '<p><span class="date" style="float:left;border:1px #fff solid">' + $.web_utils.dateFormat2Show(newsinfo.newsDate, 'dd/mm/yyyy') + '</span>';
		                		 strContent +=  getNewsDetailUrl(newsinfo.newsId, newsinfo.newsHeader, newsinfo.newsType) + '</p>'; 
		         			})
                         } 
		                 divShowPublicNews.innerHTML = strContent;
		                 
		                 var funcOptions = {
		                	goto_func : opts.funcName.navFuncName	 
		                 };		                 
		                 var idOptions = {
		                	navContainer : opts.form.navDivs.navPublicnews
		                 };
		                 $.web_paging.showNewsItem(model.pagingInfo, funcOptions, idOptions);
	            	 }
	             } catch (e) {
	                 alert(e);
	             }
             }
	 };
	 $.web_formAways.ajaxNav(formFields, options);
}


/*************************************************************************************
* load Market News functionality
**************************************************************************************/
NewsOnlineClazzListedMarket.prototype.loadMarketNews = function() {
	var opts = _newsOnlineClazzListedMarket.getOption();
	var marketNewsDiv = $(opts.form.divs.marketnews);
	var divMarketNews = document.getElementById(marketNewsDiv.selector);
	var formFields = {
            "type" : objNewsHelper.TYPE_ANALYSIS_NEWS,
            "showin" : objNewsHelper.SHOWIN_LISTED_MARKET,
            "pagingInfo.offset" : objNewsHelper.LISTED_MARKET_NUMBER_ITEM,
            "pagingInfo.indexPage" : $(opts.form.fields.pagingIndex).val()
	 };
    var options = {
             action : objNewsHelper.URL_MARKET_NEWS,
             callbackPostSubmit : function (responseText, statusText) {
	             try {
	            	 if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {                                                                                                
                         $.web_message.error(null, responseText.error);
                         //+++ update market news news
	            	 } else {
	            		 //Market News processing
	            		 var strContent = '';

	            		 divMarketNews.innerHTML = '';
		                 var model = responseText.model;
		                 //clear News content
		                 if(model != null && model.searchResult != null && model.searchResult != null) {
		                	 $.each(model.searchResult, function(i, newsinfo){
		                		 strContent += '<p><span class="date" style="float:left;border:1px #fff solid">' + $.web_utils.dateFormat2Show(newsinfo.newsDate, 'dd/mm/yyyy') + '</span>';
		                		 strContent += getNewsDetailUrl(newsinfo.newsId, newsinfo.newsHeader, objNewsHelper.TYPE_NOT_VNDS_NEWS) + '</p>'; 
		         			})
                         } 
		                 divMarketNews.innerHTML = strContent;
		                 
		                 var funcOptions = {
		                	goto_func : opts.funcName.navFuncName	 
		                 };		                 
		                 var idOptions = {
		                	navContainer : opts.form.navDivs.navMarketnews
		                 };
		                 $.web_paging.showNewsItem(model.pagingInfo, funcOptions, idOptions);
	            	 }
	             } catch (e) {
	                 alert(e);
	             }
             }
	 };
	 $.web_formAways.ajaxNav(formFields, options);
};


NewsOnlineClazzListedMarket.prototype.loadLastestMarketNews = function() {
	var opts = _newsOnlineClazzListedMarket.getOption();
	var marketNewsDiv = $(opts.form.divs.lastestmarketnews);
	var divLastestMarketNews = document.getElementById(marketNewsDiv.selector);
	var formFields = {"showin" : objNewsHelper.SHOWIN_HOME};
    var options = {
             action : objNewsHelper.URL_LASTEST_MARKET_NEWS,
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
		                	 strContent += '<bdo>' + LATEST_MARKET_NEWS_SUBTITLE + $.web_utils.dateFormat2Show(model.ifoNews.newsDate, 'dd/mm/yyyy') + '</bdo>'; 
		                	 strContent += '<p class="Title">' + getNewsDetailUrl(model.ifoNews.newsId, model.ifoNews.newsHeader, model.ifoNews.newsType) + '</p>';
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
