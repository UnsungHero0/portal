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
		navFuncStockInfo : "_goToStockInfo", // function _goTo(index)
		navFuncVNDSNews : "_goToVNDSNews" // function _goTo(index)	
	},
	form : {
		name : "#fFreeRegisterHomePage",
		divs : {
			iponews				: "divIPONews.id",
			vndsnews			: "divVNDSNews.id"
		},
	
		navDivs : {
			navIponews			: "web_navIPONews",
			navVndsnews			: "web_navVNDSNews"
		},
		
		fields : {
			pagingIndex 	: "#fFreeRegisterHomePage_indexPage"
		}
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: NewsOnlineClazzFreeRegister Class
*
*************************************************************************************/
function NewsOnlineClazzFreeRegister() {
	this.validator = null;
	
	jQuery.extend(jQuery.newsinfoOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
NewsOnlineClazzFreeRegister.prototype.getOption = function () {
	return $.newsinfoOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
NewsOnlineClazzFreeRegister.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();
};

/*************************************************************************************
* load VNDS News functionality
**************************************************************************************/
NewsOnlineClazzFreeRegister.prototype.loadVNDSNews = function() {
	var opts = _newsOnlineClazzFreeRegister.getOption();
	var vndsnewsDiv = $(opts.form.divs.vndsnews);
	var divShowVNDSNews = document.getElementById(vndsnewsDiv.selector);
	var formFields = {
             "type" : objNewsHelper.TYPE_VNDS_NEWS,
             "showin" : objNewsHelper.SHOWIN_FREEREGISTER_HOME,
             "pagingInfo.offset" : objNewsHelper.FREEREGISTER_HOME_NUMBER_ITEM,
             "pagingInfo.indexPage" : $(opts.form.fields.pagingIndex).val()
	 };
	 var options = {
             action : objNewsHelper.URL_VNDS_NEWS,
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
		                		 strContent += '<p><span class="date">' + $.web_utils.dateFormat2Show(newsinfo.newsDate, 'dd/mm/yyyy') + '</span>';
		                		 strContent +=  getNewsDetailUrl(newsinfo.newsId, newsinfo.newsHeader, newsinfo.newsType) + '</p>'; 
		         			})
                         } 
		                 divShowVNDSNews.innerHTML = strContent;
		                 
		                 var funcOptions = {
 		                	goto_func : opts.funcName.navFuncVNDSNews	 
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
NewsOnlineClazzFreeRegister.prototype.loadIPONews = function() {
	var opts = _newsOnlineClazzFreeRegister.getOption();
	var iponewsDiv = $(opts.form.divs.iponews);
	var divShowIPONews = document.getElementById(iponewsDiv.selector);
	var formFields = {
             "type" : objNewsHelper.TYPE_IPO_NEWS,
             "showin" : objNewsHelper.SHOWIN_FREEREGISTER_HOME,
             "pagingInfo.offset" : objNewsHelper.FREEREGISTER_HOME_NUMBER_ITEM,
             "pagingInfo.indexPage" : $(opts.form.fields.pagingIndex).val()
	 };
	 var options = {
             action : objNewsHelper.URL_STOCKINFORMATION,
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
		                		 strContent += '<p><span class="date">' + $.web_utils.dateFormat2Show(newsinfo.newsDate, 'dd/mm/yyyy') + '</span>';
		                		 strContent +=  getNewsDetailUrl(newsinfo.newsId, newsinfo.newsHeader, newsinfo.newsType) + '</p>'; 
		         			})
                         } 
                         divShowIPONews.innerHTML = strContent;
                         
                         var funcOptions = {
 		                	goto_func : opts.funcName.navFuncStockInfo	 
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