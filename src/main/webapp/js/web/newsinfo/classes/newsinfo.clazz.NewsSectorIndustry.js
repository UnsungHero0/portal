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
		navFuncName : "changePage" // function _goTo(index)
	},
	form : {
		name : "#fSectorIndustry",
		divs : {
			sectorNews			: "divSectorNews",
			industryNews 		: "divIndustryNews"
		},
	
		navDivs : {
			navSectorNews		: "businessInformation",
			navIndustryNews  	: "web_navIndustryNews"
		},
		
		fields : {
			sectorGroupCode		: "#sectorGroupCodeId",
			industryGroupCode	: "#industryGroupCodeId",
			pagingIndex 		: "#index"
		}
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: NewsClazzSectorIndustry Class
*
*************************************************************************************/
function NewsClazzSectorIndustry() {
	this.validator = null;
	
	jQuery.extend(jQuery.newsinfoOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
NewsClazzSectorIndustry.prototype.getOption = function () {
	return $.newsinfoOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
NewsClazzSectorIndustry.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();
};

/*************************************************************************************
* load Sector News functionality
**************************************************************************************/
NewsClazzSectorIndustry.prototype.loadSectorNews = function() {
	var opts = this.getOption();
	var sectorNewsDiv = $(opts.form.divs.sectorNews);
	var divShowSectorNews = document.getElementById(sectorNewsDiv.selector);
	var formFields = {
             "type" : objNewsHelper.TYPE_NEWS,
             "sectorGroupCode" : $(opts.form.fields.sectorGroupCode).val(),
             "pagingInfo.offset" : objNewsHelper.NORMAL_NUMBER_ITEM,
             "pagingInfo.indexPage" : $(opts.form.fields.pagingIndex).val()
	 };
	 var options = {
             action : objNewsHelper.URL_SECTOR_NEWS,
             callbackPostSubmit : function (responseText, statusText) {
		 		
	             try {
	            	 if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {                                                                                                
                         $.web_message.error(null, responseText.error);
                         //+++ update ipo news
	            	 } else {
	            		//IPO News processing
	            		 var strContent = '';

	            		 divShowSectorNews.innerHTML = '';
		                 var model = responseText.model;
		                 //clear News content
		                 if(model != null && model.searchResult != null && model.searchResult != null) {
		                	 $.each(model.searchResult, function(i, newsinfo){
		                		 strContent += '<p><span class="date" style="float:left;border:1px #fff solid">' + $.web_utils.dateFormat2Show(newsinfo.newsDate, 'dd/mm/yyyy') + '</span>';
		                		 strContent +=  getNewsDetailUrl(newsinfo.newsId, newsinfo.newsHeader, newsinfo.newsType) + '</p>'; 
		         			})
                         } 
                         divShowSectorNews.innerHTML = strContent;
                         
                         var funcOptions = {
 		                	goto_func : opts.funcName.navFuncName	 
 		                 };		                 
 		                 var idOptions = {
 		                	navContainer : opts.form.navDivs.navSectorNews
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
* load Industry News functionality
**************************************************************************************/
NewsClazzSectorIndustry.prototype.loadIndustryNews = function() {
	var opts = _newsOnlineClazzListedMarket.getOption();
	var industryNewsDiv = $(opts.form.divs.industryNews);
	var divShowIndustryNews = document.getElementById(industryNewsDiv.selector);
	var formFields = {
             "type" : objNewsHelper.TYPE_NEWS,
             "industryGroupCode" : $(opts.form.fields.industryGroupCode).val(),
             "pagingInfo.offset" : objNewsHelper.NORMAL_NUMBER_ITEM,
             "pagingInfo.indexPage" : $(opts.form.fields.pagingIndex).val()
	 };
	 var options = {
             action : objNewsHelper.URL_INDUSTRY_NEWS,
             callbackPostSubmit : function (responseText, statusText) {
		 		
	             try {
	            	 if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {                                                                                                
                         $.web_message.error(null, responseText.error);
                         //+++ update ipo news
	            	 } else {
	            		//Public News processing
	            		 var strContent = '';

	            		 divShowIndustryNews.innerHTML = '';
		                 var model = responseText.model;
		                 //clear News content
		                 if(model != null && model.searchResult != null && model.searchResult != null) {
		                	 $.each(model.searchResult, function(i, newsinfo){
		                		 strContent += '<p><span class="date" style="float:left;border:1px #fff solid">' + $.web_utils.dateFormat2Show(newsinfo.newsDate, 'dd/mm/yyyy') + '</span>';
		                		 strContent +=  getNewsDetailUrl(newsinfo.newsId, newsinfo.newsHeader, newsinfo.newsType) + '</p>'; 
		         			})
                         } 
		                 divShowIndustryNews.innerHTML = strContent;
		                 
		                 var funcOptions = {
		                	goto_func : opts.funcName.navFuncName	 
		                 };		                 
		                 var idOptions = {
		                	navContainer : opts.form.navDivs.navIndustryNews
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

