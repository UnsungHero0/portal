$(document).ready(function() {
	$("input[name^='account.otherAccount']").click(function() {
		if ($(this).val() == 1) {
			$('#otherAccNameArea').show();
		} else {
			$('#otherAccNameArea').hide();
		}
	});
	
	// validate signup form on keyup and submit
	/*$("#next").validate({
		rules: {
			'account.mangName': {
		      required: true,
		      maxlength: 99
		    }
		},
		messages: {
			'account.mangName': "D\u1EEF li\u1EC7u kh\u00F4ng h\u1EE3p l\u1EC7"
		}
	});*/
});