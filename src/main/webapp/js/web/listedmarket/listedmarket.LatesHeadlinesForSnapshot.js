var _latesHeadlinesClazzForSnapshot = new LatesHeadlinesForSnapshot();

$().ready(function() {
	//Lastest Headlines
	_latesHeadlinesClazzForSnapshot.loadLastestHeadlines();
		
	//Public Info
	_latesHeadlinesClazzForSnapshot.loadPublicInfo();
	
	//Companys Events
	_latesHeadlinesClazzForSnapshot.loadCompanysEvents();
});

function _goTo(webNavId, index) {
	try {
		var opts = _latesHeadlinesClazzForSnapshot.getOption();
		$("#LatestHeadlines_pagingInfo_indexPage").val(index);
		
		if (webNavId == opts.form.navDivs.navLastestHeadlines) {
			_latesHeadlinesClazzForSnapshot.loadLastestHeadlines();
		} else if (webNavId == opts.form.navDivs.navPublicinfo) {
			_latesHeadlinesClazzForSnapshot.loadPublicInfo();
		} else if (webNavId == opts.form.navDivs.navCompanysevents) {
			_latesHeadlinesClazzForSnapshot.loadCompanysEvents();
		}
	} catch (e) {
		alert("_goTo(): " + e);
	}
}