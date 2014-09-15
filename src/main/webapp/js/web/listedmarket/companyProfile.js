$(function(){
	$('#history_hsdn_tab').click(function(){
		hideAll();
		$('#history_hsdn_content').show();
		window.location = "#" + $('#hiddendiv').val();
	});
	
	$('#main_business_hsdn_tab').click(function(){
		hideAll();
		$('#main_business_content').show();
		window.location = "#" + $('#hiddendiv').val();
	});
	
	$('#market_position_hsdn_tab').click(function(){
		hideAll();
		$('#market_position_content').show();
		window.location = "#" + $('#hiddendiv').val();
	});
	
	$('#plan_hsdn_tab').click(function(){
		hideAll();
		$('#plan_content').show();
		window.location = "#" + $('#hiddendiv').val();
	});
});

function hideAll(){
	$('#history_hsdn_content').hide();
	$('#main_business_content').hide();
	$('#market_position_content').hide();
	$('#plan_content').hide();
}

//function isProtocol(externalLink) {
//	var protocol = false;
//	externalLink = externalLink.toLowerCase();
//	var listOfProtocol = new Array("http://", "https://");
//	var i;
//	for (i = 0; i < listOfProtocol.length; i++) {
//		if (externalLink.indexOf(listOfProtocol[i]) == 0) {
//			protocol = true;
//			break;
//		}
//	}
//	return protocol;
//}
//
//function openURL(url) {
//	if (!isProtocol(url)) {
//		url = "http://" + url;
//	}
//	window.open(url);
//	return;
//}
