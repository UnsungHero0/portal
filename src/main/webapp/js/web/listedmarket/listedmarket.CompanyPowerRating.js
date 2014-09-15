$().ready(function() {
	loadWaitImage();
	$.ajax( {
		type : "POST",
		dataType : "json",
		url : URL_GET_COMPANY_POWER_RATING,
		success : function(data) {
			hideWaitImage();
			populateContent(data);
		},
		beforeSend : null
	});
	
	//getRelatedCompany();
});

function getRelatedCompany() {
	var url = $.web_resource.getContext()
			+ "/ajax/listed/SearchRelatedCompany.shtml";

	$('#analysisRelatedCompany').load(url);
}

function loadWaitImage() {
	$('#loadWaitImg').html('<img src="' + AJAX_IMAGE_LOADING + '" />');
	$('#loadWaitImg').show();
}

function hideWaitImage() {
	$("#loadWaitImg").hide();
}

populateContent = function(data) {
	if (data.model) {
		try {
			if (data.model.currentCompanyPr != null) {
				$('.box_chiso_PR #message').hide();
				$('.box_chiso_PR #todayPwr').html(
						data.model.currentCompanyPr.mark);
				$('.box_chiso_PR #lastFourPwr').html(
						data.model.currentCompanyPr.markB4days);
			} else {
				$('.box_chiso_PR #index').hide();
				var html = '"' + $('#noResultTitlePre').html() + ' '
						+ data.model.mostRecentDate + ' '
						+ $('#noResultTitlePost').html() + '"';

				$('.box_chiso_PR #message').html(html);
			}
		} catch (e) {
		}
	}
};

function navigateToCompanyNews(secCode) {
	doQuickSearchSymbol(secCode, 8);
}