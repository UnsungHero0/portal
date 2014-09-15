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
//var objNewsHelper = new NewsHelper();

(function($) {
$.mostActiveOption = function() {};
$.extend($.mostActiveOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},	
	form : {
		name : "#fListedMarket"
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: ListedMarketClazzMostActive Class
*
*************************************************************************************/
function ListedMarketClazzMostActive() {
	this.validator = null;
	
	jQuery.extend(jQuery.mostActiveOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
ListedMarketClazzMostActive.prototype.getOption = function () {
	return $.mostActiveOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
ListedMarketClazzMostActive.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();
};