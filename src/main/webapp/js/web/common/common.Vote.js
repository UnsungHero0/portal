var _commonClazzVote = new CommonClazzVote();
var opts = _commonClazzVote.getOption();
var arrColor = new Array("#FF0000","#00FFFF","#0000FF","#800080","#FF0080","#FFFF00","#00FF00","#FF00FF");

$().ready(function() {
	// load Vote data
	loadVote();
	
	$("#fVote_voteButton").click(function(){
		var votedId = $('[name=chk]').fieldValue();
		if (votedId == "" || votedId == null) {
			alert(SELECT_ONE_ANSWER);
		} else {
			$("#fVote_votedId").val(votedId);
			$("#fVote_captchar").val(""); // clear previous captchar
			$("#fVote_message").html(""); // clear message
			reloadCaptcha(); // refresh captchar
			$(function(){
				/*
				 * if out of expire times : reset message, voted to default and show survey dialog.
				 * else show message.
				 */
				if (checkCookie('POPsurvey')) {
					$("#fVoteResult_message").hide();
					$("#fVoteResult_voted").val("false");
					$('#survey').dialog({
						width: 500,
						resizable: false,
						buttons: {
							Close: function(){
								$('[name=chk]').removeAttr('checked');
								$('#survey').dialog('close');
							},
							Submit: function(){
								$("#fVote_message").html(""); // clear message
								var opts = _commonClazzVote.getOption();
								var formFields = {
									"vote.voteId" 		: $("#fVote_votedId").val(),
									"captchar"			: $("#fVote_captchar").val()
								};
							    var options = {
									action : URL_SUBMIT_VOTE,
									callbackPostSubmit : function (responseText, statusText) {
							    		var model = responseText.model;
							    		if (model.message == null || model.message == "") {
							    			generateContentSurveyThanks(responseText.model);
							    			$("#fVoteResult_voted").val("true"); // had been voted
							    		} else { // wrong captchar
							    			$("#fVote_captchar").val("");
							    			$("#fVote_message").html(model.message);
							    			reloadCaptcha(); // refresh captchar
							    		}
							         }
								 };
								 $.web_formAways.ajaxNav(formFields, options);
							}
						}
					});
					$('#survey').dialog('open');
				} else {
					showSurveyThanks();
					$("#fVoteResult_message").show();
				}
			});
		}
	});
	
	$("#fVote_resultButton").click(function(){
		/*
		 * if out of expire times : reset message, voted to default.
		 * else show message.
		 */
		if (checkCookie('POPsurvey')) {
			$("#fVoteResult_message").hide();
			$("#fVoteResult_voted").val("false");
		} else {
			$("#fVoteResult_message").show();
		}
		showSurveyThanks(); // show survey result
	}); // end of click button result
	
	$(function(){
		$('#survey_thanks').dialog({
			width: 500,
			autoOpen: false,
			resizable: false,
			buttons: {
				Close: function(){
					if ($("#fVoteResult_voted").val() == "true") { // if voted then set expire times : 30 minutes
						setCookie('POPsurvey','POPsurvey', 30);
					}
					$('[name=chk]').removeAttr('checked');
					$(this).dialog('close');
				}
			}
		});
	});
});

function showSurveyThanks() {
	var opts = _commonClazzVote.getOption();
	var formFields = {
	};
    var options = {
		action : URL_VOTE,
		callbackPostSubmit : function (responseText, statusText) {
    		generateContentSurveyThanks(responseText.model);
     	}
	 };
	 $.web_formAways.ajaxNav(formFields, options);
}

function generateContentSurveyThanks(model) {
	/* display date time */
	var myDate = new Date();
	var displayDate = myDate.format('mmmm dd, yyyy   h:MM TT');
	$("#fVoteResult_dateTime").html(displayDate);
	
	var divContent = $("#fVoteResult_content");
	divContent.html("");
	var strContent = "";
	
	if(model.searchResult != null && model.searchResult.length > 0) {
		strContent += '<table cellpadding="0" cellspacing="0" style="border: 1px solid rgb(176, 176, 176);border-top: none" width="100%">';
		var tdClass = "";
		$.each(model.searchResult, function(i, vote){
			if (i == 0) {
   	 			tdClass = "col2";
   	 		} else {
   	 			tdClass = "col3";
   	 		}
			strContent += '<tr>';
			strContent += '<td class="datagrid_data1" style="padding-left: 5px" align="left" ><b>' + vote.voteName + '</b></td>';
			strContent += '<td class="datagrid_data2" ><table><tr><td bgcolor="' + arrColor[i%arrColor.length] + '" width="' + vote.rateVote*6 + '">&nbsp;</td><td class="VoteShow">&nbsp;' + $.web_utils.fomatNumberWithScale(vote.rateVote, 2) + '%</td></tr></table></td>';
			strContent += '<td style="border-top: 1px solid rgb(176, 176, 176);border-left: 1px solid rgb(176, 176, 176);padding-right: 5px" align="right" >' + vote.total + ITEM_VOTE + '</td>';
			strContent += '</tr>';
		});
		strContent += '</table>';
	}
	divContent.html(strContent);
	$("#fVoteResult_total").html("<b>" + model.totalVote + " " + ITEM_VOTE + "</b>");
	
	$('#survey').dialog('close');
	$('#survey_thanks').dialog('open');
}

function loadVote() {
	var opts = _commonClazzVote.getOption();
	var formFields = {
	};
    var options = {
		action : URL_VOTE,
		callbackPostSubmit : function (responseText, statusText) {
    		var model = responseText.model;
    		var divContent = $(opts.form.fields.content);
    		divContent.html("");
    		
    		var strContent = "";
    		if(model.searchResult != null && model.searchResult.length > 0) {
    			strContent += '<table cellpadding="2" cellspacing="2" border="0">';
	    		$.each(model.searchResult, function(i, vote){
	    			strContent += '<tr><td valign="top"><input type="radio" name="chk" value="' + vote.voteId + '"  class="chk" /></td>';
	    			strContent += '<td valign="middle">' + vote.voteName + '</td>';
	    			strContent += '</tr>';
	    			
	    		});
	    		strContent += '</table>';
    		}
    		divContent.html(strContent);
         }
	 };
	 $.web_formAways.ajaxNav(formFields, options);
	 
};

function setCookie(c_name, value, expireMinutes) {
	var exdate = new Date();
	exdate.setMinutes(exdate.getMinutes() + expireMinutes);
	document.cookie = c_name + "=" + escape(value) + ((expireMinutes == null) ? "" : ";expires=" + exdate.toGMTString());
}

function getCookie(c_name) {
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(c_name + "=");
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1;
			c_end = document.cookie.indexOf(";", c_start);
			if (c_end == -1)
				c_end = document.cookie.length;
			return unescape(document.cookie.substring(c_start, c_end));
		}
	}
	return "";
}

function checkCookie(c_name) {
	cookie_value = getCookie(c_name);
	if (cookie_value == "") {
		return true;
	} else {
		return false;
	}
}