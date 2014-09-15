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
$.voteOption = function() {};
$.extend($.voteOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},	
	form : {
		name : "#fVote",
		fields : {
			content 	: "#fVote_content"
		}
	},
	buttons : {
		voteButton 		: "#fVote_voteButton",
		resultButton 	: "#fVote_resultButton"
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: CommonClazzVote Class
*
*************************************************************************************/
function CommonClazzVote() {
	this.validator = null;
	
	jQuery.extend(jQuery.voteOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
CommonClazzVote.prototype.getOption = function () {
	return $.voteOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
CommonClazzVote.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();
};