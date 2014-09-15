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
$.mostPopularSymbolOption = function() {};
$.extend($.mostPopularSymbolOption, {
	loading : "#progress_loading_img",
	form : {
		name : "#fListedMarket",
		fields : {
			mostPopularSymbolContent	: "#fListedMarket_mostPopularSymbolContent"
		}
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: ListedMarketClazzMostPopularSymbol Class
*
*************************************************************************************/
function ListedMarketClazzMostPopularSymbol() {
	this.validator = null;
	
	jQuery.extend(jQuery.mostPopularSymbolOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
ListedMarketClazzMostPopularSymbol.prototype.getOption = function () {
	return $.mostPopularSymbolOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
ListedMarketClazzMostPopularSymbol.prototype.init = function() {
	//var _this = this;
	//var opts = _this.getOption();
};

ListedMarketClazzMostPopularSymbol.prototype.loadMostPopularSymbol = function() {
	var _this = this;
	var opts = _this.getOption();
	var formFields = {
		"pagingInfo.offset" 	: '10',
        "searchIn"			 	: '0' // SEARCH_IN_DAY
	};
    var options = {
		action : URL_MOST_POPULAR_SYMBOL,
		callbackPostSubmit : function (responseText, statusText) {
    		var model = responseText.model;
    		var mostPopularSymbolContent = $(opts.form.fields.mostPopularSymbolContent);
    		mostPopularSymbolContent.html("");
    		
    		var strContent = "";
    		strContent += '<table cellpadding="0" cellspacing="0" border="0" width="100%" class="tbl_most">';
    		strContent += '<colgroup width="25%"/><colgroup width="25%"/><colgroup width="25%"/><colgroup width="25%"/>';
    		$.each(model.listQuote, function(i, item){
    			var displayStyle = "color6";
    			if (item.chgIndex > 0) {
    				displayStyle = "green";
    			}
    			
    			var countNumber = 0;
    			$.each(model.listAuditSymbol, function(i, auditItem){
    				if (item.code == auditItem.symbol) {
    					countNumber = auditItem.countNumber;
    				}
    			});
			    
    			strContent += '<tr>';
    			//strContent += '<td><a href="javascript:doQuickSearchSymbol(\'' + item.code + '\');" title="' + countNumber + '">' + item.code + '</a></td>';
    			var url = $.web_resource.getContext() + "tong-quan/" + item.code.toLowerCase() + ".shtml";
    			strContent += '<td><a href="' + url + '" title="' + countNumber + '">' + item.code + '</a></td>';
    			strContent += '<td><span class="' + displayStyle + '">' + $.web_utils.fomatNumberWithScale(item.currentIndex, 1) + '</span></td>';
    			strContent += '<td align="right"><span class="' + displayStyle + '">' + $.web_utils.fomatNumberWithScale(item.chgIndex, 1) + '(' + $.web_utils.fomatNumberWithScale(item.pctIndex, 2) + '%)' + '</span></td>';
    			strContent += '<td align="right">' + $.web_utils.fomatNumberWithScale(item.totalTradingQtty, 0) + '</td>';
    			strContent += '</tr>';
    		});
    		
    		strContent += '</table>';
			mostPopularSymbolContent.html(strContent);
     	}
    };
    $.web_formAways.ajaxNav(formFields, options);
	 
};