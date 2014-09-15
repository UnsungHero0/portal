$(document).ready(function(){
	$('#account_birthday').datepicker({
        changeMonth: true,
        changeYear: true,
        yearRange : "1930:2020",
        onSelect: function (){return false;},
        onClose: function (){return false;}
    });
	
	$('#account_identityDate').datepicker({
        changeMonth: true,
        changeYear: true,
        yearRange : "1930:2020",
        onSelect: function (){return false;},
        onClose: function (){return false;}
    });
	
	/*$('#account_name').rules("add", { required:true });
	$('#account_identityNo').rules("add", { required:true });
	$('#account_identityPlace').rules("add", { required:true });
	$('#account_address').rules("add", { required:true });
	$('#account_email').rules("add", { required:true, email:true });
	$('#account_phoneNo').rules("add", { required:true, number:true });
	$('#account_mobilePhoneNo').rules("add", { required:true, number:true });
	$('#account_identityDate').rules("add", { required:true });
	$('#account_birthday').rules("add", { required:true });*/
	
	var numeric = function(event) {
	  // Backspace, tab, enter, end, home, left, right
	  // We don't support the del key in Opera because del == . == 46.
	  var controlKeys = [8, 9, 13, 35, 36, 37, 39];
	  // IE doesn't support indexOf
	  var isControlKey = controlKeys.join(",").match(new RegExp(event.which));
	  // Some browsers just don't raise events for control keys. Easy.
	  // e.g. Safari backspace.
	  if (!event.which || // Control keys in most browsers. e.g. Firefox tab is 0
	      (48 <= event.which && event.which <= 57) || // Always 0 through 9
	      //(48 == event.which && $(this).attr("value")) || // No 0 first digit
	      isControlKey) { // Opera assigns values for control keys.
	    return;
	  } else {
	    event.preventDefault();
	  }
	};
	
	$("#account_fax").keypress(numeric);
	$("#account_taxCode").keypress(numeric);
	$("#account_phoneNo").keypress(numeric);
	
//	$("#oversea").tooltip({ 
//	    bodyHandler: function() { 
//	        return $('#tooltipArea').html(); 
//	    }, 
//	    showURL: false 
//	});
	
//	$("#organized").tooltip({ 
//	    bodyHandler: function() { 
//	        return $('#tooltipArea').html(); 
//	    }, 
//	    showURL: false 
//	});
	
	$("input[name^='account.married']").click(
			function() {
				if ($(this).val() == 'MARRIED') {
					$("#married").show();
				} else {
					$("#married").hide();
				}
			});
	
	// validate signup form on keyup and submit
	$("#next").validate({debug: false});
	$('#account_name').rules("add", { required:true });
	/*$('#account_middleName').rules("add", { required:true });
	$('#account_lastName').rules("add", { required:true });*/
	
	$('#account_identityNo').rules("add", { required:true });
	$('#account_identityPlace').rules("add", { required:true });
	$('#account_address').rules("add", { required:true });
	$('#account_email').rules("add", { required:true, email2:true });
	$('#account_mobilePhoneNo').rules("add", { required:true, number:true });
	$('#account_identityDate').rules("add", { required:true, dateITA:true });
	$('#account_birthday').rules("add", { required:true, dateITA:true });
	
//	$("#next").validate({
//		rules: {
//			'account.lastName': {required: true},
//			'account.name': {required: true},
//			'account.identityNo' : {required: true},
//			'account.address' : {required: true},
//			'account.email' : {required: true, email2:true},
//			'account.mobilePhoneNo' : {required: true, number:true},
//			'account.identityDate' : { required:true, dateITA:true },
//			'account.birthday' : { required:true, dateITA:true },
//			'account.identityPlace' : {required: true}
//		},
//		messages: {
//			'account.name': "Qu\u00FD kh\u00E1ch ch\u01B0a \u0111i\u1EC1n t\u00EAn c\u00E1 nh\u00E2n",
//			'account.identityNo': "Qu\u00FD kh\u00E1ch ch\u01B0a \u0111i\u1EC1n s\u1ED1 CMND/H\u1ED9 chi\u1EBFu",
//			'account.address': "Qu\u00FD kh\u00E1ch ch\u01B0a \u0111i\u1EC1n \u0111\u1ECBa ch\u1EC9",
//			'account.email': "Email kh\u00F4ng h\u1EE3p l\u1EC7",
//			'account.mobilePhoneNo': "Qu\u00FD kh\u00E1ch ch\u01B0a \u0111i\u1EC1n t\u00EAn c\u00E1 nh\u00E2n",
//			'account.identityDate': "Ng\u00E0y c\u1EA5p ch\u01B0a h\u1EE3p l\u1EC7",
//			'account.birthday': "Ng\u00E0y sinh ch\u01B0a h\u1EE3p l\u1EC7",
//			'account.identityPlace': "Q\u00FAy kh\u00E1ch ch\u01B0a \u0111i\u1EC1n n\u01A1i c\u1EA5p"
//		}
//	});

});