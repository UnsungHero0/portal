$(document).ready(function(){
	// register dialog
	$('#processingOpenAccountDialog').dialog({
        autoOpen: false,
        modal: true,
        closeOnEscape: true,
        resizable: false,
        show: 'drop',
        hide: 'drop',
        position: ['center', 220]
    });
	
	$('#next').submit(function() {
		var message = '';
		var result = true;
		if ($("input[name^='decision']:checked").val() != 'true') {
			message = '\u0110\u1EC3 m\u1EDF t\u00E0i kho\u1EA3n, Qu\u00FD kh\u00E1ch ph\u1EA3i \u0111\u1ED3ng \u00FD v\u1EDBi c\u00E1c \u0111i\u1EC1u kho\u1EA3n c\u1EE7a VNDIRECT';
			result = false;
		} 
		if (!result) {
			$("div.error span").html(message);
			$("div.error").show();
		} else {
			$("div.error").hide();
			
			// open processing dialog
			$('#processingOpenAccountDialog').dialog('open');
		}

		return result;
	});
});

function stateNextButtonWithDecision(isAgree){
	if(!isAgree){
		$('#nextButton').removeClass('iButton').addClass('iButtonInactive');
	} else {
		$('#nextButton').removeClass('iButtonInactive').addClass('iButton');
	}
}