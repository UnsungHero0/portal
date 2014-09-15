var _analysisTooolsClazzExperts = new AnalysisTooolsClazzExperts();
var opts = _analysisTooolsClazzExperts.getOption();

$().ready(function() {
	viewContentOverview();
});

function _goTo(webNavId, index) {
	try {
		$("#fExperts_indexPage").val(index);
		viewContentOverview();
	} catch (e) {
		alert("_goTo(): " + e);
	}
}

function viewContentOverview() {
	var formFields = {
		"type" 					: "Expert",
		"pagingInfo.indexPage" 	: $("#fExperts_indexPage").val(),
		"pagingInfo.offset"		: 10
	};
	var options = {
		action 				: URL_VIEW_EXPERT_CONTENT,        
		callbackPostSubmit	: _analysisTooolsClazzExperts.execute_PostSubmit
    };
	$.web_formAways.ajaxNav(formFields, options);
};
