/*************************************************************************************
* @author 
*
* Define Home Object
* using the flowing query to extend CompanyEventsOption
*
* jQuery.extend(jQuery.companyEventsOption, {
* });
*
*************************************************************************************/
var objNewsHelper = new NewsHelper();
var page = 1;

(function($) {
$.companyEventsOption = function() {};
$.extend($.companyEventsOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},
	funcName : {
		navFuncName : "_goTo" // function _goTo(id, index)
	},
	form : {
		name : "#fCompanyEvents",
		divs : {
			content				: "#fCompanyEvents_content"
		},
		navDivs : {
			navContainer		: "fSearchSymbol_paging"
		},
		fields : {
			pagingIndex 		: "#fCompanyEvents_pagingInfo_indexPage",
			fromDate	 		: "#fCompanyEvents_fromDateId",
			toDate		 		: "#fCompanyEvents_toDateId"
		}
	},
	buttons : {
		marketsNews				: "#fCompanyEvents_buttonMarketsNews",
		companyEvents			: "#fCompanyEvents_buttonCompanyEvents",
		search					: "#fCompanyEvents_searchButton"
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: CompanyEvents Class
*
*************************************************************************************/
function CompanyEvents() {
	this.validator = null;
	
	jQuery.extend(jQuery.companyEventsOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
CompanyEvents.prototype.getOption = function () {
	return $.companyEventsOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
CompanyEvents.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();
	
	_this.resetInitData();
};

/*************************************************************************************
* resetInitData functionality
**************************************************************************************/
CompanyEvents.prototype.resetInitData = function() {
	var _this = this;
	_this.validator = null;
};

CompanyEvents.prototype.searchEvents = function() {
	var opts = _companyEvents.getOption();
	page = $(opts.form.fields.pagingIndex).val();
	var divContent = $(opts.form.divs.content);
	var formFields = {
		"pageUrl"						: $('#fCompanyEvents_content').attr('rel'),
		"type" 							: "Event" + ',' + "MacVN",
		"currentSymbol"					: $('#fCompanyEvents_currentSymbol').val(),
		"searchIfoNews.strFromNewsDate" : $(opts.form.fields.fromDate).val(),
		"searchIfoNews.strToNewsDate" 	: $(opts.form.fields.toDate).val(),
        "pagingInfo.offset" 			: 10,
        "pagingInfo.indexPage" 			: $(opts.form.fields.pagingIndex).val()
	 };
	
	 var options = {
		 action : $.web_resource.getContext() + "/ajax/news/News_GetCompanyEventNews.shtml",
         callbackPostSubmit : function (responseText, statusText) {
             try {
            	 if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {
                     $.web_message.error(null, responseText.error);
                     //+++ update ipo news
            	 } else {
            		//Companys Events processing
            		 var strContent = '';

            		 divContent.html("");
	                 var model = responseText.model;
	                 //clear content
	                 if (model.messageFromNewsDate == FOR_EVENT_FROMNEWSDATE_FIELD_INVALID && model.messageToNewsDate == null ) {
	                	 strContent += '<span class="error">' + FOR_EVENT_FROMNEWSDATE_FIELD_INVALID + '</span>';	
	                 } else if (model.messageToNewsDate == FOR_EVENT_TONEWSDATE_FIELD_INVALID && model.messageFromNewsDate == null) {
	                	 strContent += '<span class="error">' + FOR_EVENT_TONEWSDATE_FIELD_INVALID + '</span>' ;
	                 } else if (model.messageFromNewsDate == FOR_EVENT_FROMNEWSDATE_FIELD_INVALID && model.messageToNewsDate == FOR_EVENT_TONEWSDATE_FIELD_INVALID) {
	                	 strContent += '<span class="error">' + FOR_EVENT_FROMNEWSDATE_FIELD_INVALID + '</span>' + '<BR>';
	                	 strContent += '<span class="error">' + FOR_EVENT_TONEWSDATE_FIELD_INVALID + '</span>';
	                 } else if (model != null && model.searchResult != null && model.searchResult.length > 0) {
	                	 strContent = '<ul class="n_list_other list_detail_tt_nd">';
	                	 strContent += '<li class="title bgTitleBar">' + $('#localNewsTitle').val() + '</li>';
						 strContent += '<div class="n_list_other_detail">';
	                	 $.each(model.searchResult, function(i, newsinfo){
                			 strContent += '<li class="n_other_news_list">';
	                		 strContent += '<a href="' + newsinfo.urlDetail + '">' 
	                					+ newsinfo.newsHeader + ' (' + newsinfo.newsResource +')</a>' + '<span class="newsDate"> - ' 
	                					+ $.web_utils.dateFormat2Show(newsinfo.newsDate, 'dd/mm/yyyy') + '</span>';
	                		 strContent += '</li>';
	         			});
	                	strContent += '</div></ul>';
	                	
	                	$('#totalPage').val(model.pagingInfo.totalPage);
	                	if(model.pagingInfo.totalPage == 1){
	                		$('.content_bar').hide();
	                	}else {
	                		$('.content_bar').show();
	                	}
	                 } else {
	                	 strContent += '<span class="error">' + COMMON_NOT_FOUND + '</span>';
	                	 $('.content_bar').hide();
	                 }  
	                 
	                 divContent.html(strContent);
	                 var totalPage = $('#totalPage').val();
	    			 if(page < totalPage){
	    				 $('.barLoadMore').css("display", "block");
	    			 } 
//                     var funcOptions = {
//	                	goto_func : opts.funcName.navFuncName
//	                 };		                 
//	                 var idOptions = {
//	                	navContainer : opts.form.navDivs.navContainer
//	                 };
//	                 $.web_paging.showItem(model.pagingInfo, funcOptions, idOptions);
            	 }
             } catch (e) {
                 alert(e);
             }
         }
	 };
	 $.web_formAways.ajaxNav(formFields, options);
}

CompanyEvents.prototype.loadMoreNews = function() {
	page ++;
	if(page <= $('#totalPage').val()){
		var opts = _companyEvents.getOption();
		var divContent = $(opts.form.divs.content);
		var formFields = {
			"pageUrl"						: $('#fCompanyEvents_content').attr('rel'),
			"type" 							: "Event" + ',' + "MacVN",
			"currentSymbol"					: $('#fCompanyEvents_currentSymbol').val(),
			"searchIfoNews.strFromNewsDate" : $(opts.form.fields.fromDate).val(),
			"searchIfoNews.strToNewsDate" 	: $(opts.form.fields.toDate).val(),
	        "pagingInfo.offset" 			: 10,
	        "pagingInfo.indexPage" 			: page
		 };
		
		 var options = {
			 action : $.web_resource.getContext() + "/ajax/news/News_GetCompanyEventNews.shtml",
	         callbackPostSubmit : function (responseText, statusText) {
	             try {
            		 var strContent = '';
	                 var model = responseText.model;
	                 if (model != null && model.searchResult != null && model.searchResult.length > 0) {
	                	 strContent = '<ul class="n_list_other list_detail_tt_nd" id="appendNews' + page + '">';
						 strContent += '<div class="n_list_other_detail">';
	                	 $.each(model.searchResult, function(i, newsinfo){
                			 strContent += '<li class="n_other_news_list">';
	                		 strContent += '<a href="' + newsinfo.urlDetail + '">' 
	                					+ newsinfo.newsHeader + ' (' + newsinfo.newsResource +')</a>' + '<span class="newsDate"> - ' 
	                					+ $.web_utils.dateFormat2Show(newsinfo.newsDate, 'dd/mm/yyyy') + '</span>';
	                		 strContent += '</li>';
	         			});
	                	strContent += '</div></ul>';
	                 } else {
	                	 strContent += '<span class="error">' + COMMON_NOT_FOUND + '</span>';
	                 }  
	                 
	                 divContent.append(strContent);
	                 $("#loadingMoreImage").empty();
	                 
	                 $('#appendNews' + page).css('background-color', '#FFFFCC');
	                 setTimeout(function() {
	                	$('#appendNews' + page).css('background-color', '');
					 }, 1000);
	             } catch (e) {
	                 alert(e);
	             }
	         },
             beforeSend: function() {
            	$("#loadingMoreImage").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />');
             }
		 };
		 $.web_formAways.ajaxNav(formFields, options);
	}
	if(page == $('#totalPage').val()){
		$('.barLoadMore').css("display", "none");
    }
}