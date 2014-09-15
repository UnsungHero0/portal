	$(document).ready(function() {
	$("input[name^='account.accountNo1']").each(function(i, item) {
		$(this).autotab({
			target : 'account_accountNo1_' + (i + 1) + '_'
			//,format : 'numeric'
		});
	});

	$("input[name^='account.accountNo2']").each(function(i, item) {
		$(this).autotab({
			target : 'account_accountNo2_' + (i + 1) + '_'
			//,format : 'numeric'
		});
	});

	$("input[name^='account.vndirectAccNo']")
			.each(
					function(i, item) {
						$(this)
								.autotab(
										{
											target : 'account_vndirectAccNo_' + (i + 1) + '_'
											//,format : 'numeric'
										});
					});

	$("input[name^='account.tradingPhone']").click(
			function() {
				if ($(this).val() == 'any') {
					$('#twoPhonesArea').hide();
					/*$("input[name^='account.phoneNo']").attr(
							"readonly", "readonly");
					$("input[name^='account.mobilePhoneNo']")
							.attr("readonly", "readonly");*/
				} else {
					$('#twoPhonesArea').show();
					/*$("input[name^='account.phoneNo']")
							.removeAttr("readonly");
					$("input[name^='account.mobilePhoneNo']")
							.removeAttr("readonly");*/
				}
			});
					
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
					
					$("#account_phoneNo").keypress(numeric);
					$("#account_mobilePhoneNo").keypress(numeric);
					
					$('input#phone').click(function() {
						if (!this.checked) {
							// this.checked=true;
							$('#phoneArea').hide();
						} else {
							// this.checked=false;
							$('#phoneArea').show();
						}
					});
					
					$('input#phone').click(function() {
						if (!this.checked) {
							// this.checked=true;
							$('#phoneArea').hide();
						} else {
							// this.checked=false;
							$('#phoneArea').show();
						}
					});
					
//					$("#tooltipTradingOl").tooltip({ 
//					    bodyHandler: function() { 
//					        return $('#tooltipArea').html(); 
//					    }, 
//					    showURL: false 
//					});
					
					$('#next')
							.submit(
									function() {
										var message = '';
										var result = true;
										if ($("input[name^='account.tradingAccounts']:checked").length == 0) {
											message = 'Qu\u00FD kh\u00E1ch ch\u01B0a ch\u1ECDn lo\u1EA1i t\u00E0i kho\u1EA3n';
											result = false;
										} else if ($("input[name^='account.cycleToReceive']:checked").length == 0) {
											message = 'Qu\u00FD kh\u00E1ch ch\u01B0a ch\u1ECDn Chu k\u1EF3 nh\u1EADn sao k\u00EA';
											result = false;
										} else if ($("input[name^='account.receiveMethods']:checked").length == 0) {
											message = 'Qu\u00FD kh\u00E1ch ch\u01B0a ch\u1ECDn Ph\u01B0\u01A1ng th\u1EE9c nh\u1EADn sao k\u00EA';
											result = false;
										} else if ($("#phone").is(":checked") && $("#phoneNo").is(":checked") && (jQuery.trim($("#account_phoneNo").val()).length == 0 && jQuery.trim($("#account_mobilePhoneNo").val()).length == 0)) {
											message = "Qu\u00FD kh\u00E1ch ch\u01B0a \u0111i\u1EC1n s\u1ED1 \u0111i\u1EC7n tho\u1EA1i giao d\u1ECBch qua \u0111i\u1EC7n tho\u1EA1i";
											result = false;
										}

										if (!result) {
											$("div.error span").html(message);
											$("div.error").show();
										} else {
											$("div.error").hide();
										}

										return result;
									});
				});

function WireAutoTab(CurrentElementID, NextElementID, FieldLength) {
    //Get a reference to the two elements in the tab sequence.
    var CurrentElement = $('#' + CurrentElementID);
    var NextElement = $('#' + NextElementID);
 
    CurrentElement.keyup(function(e) {
        //Retrieve which key was pressed.
        var KeyID = (window.event) ? event.keyCode : e.keyCode;
 
        //If the user has filled the textbox to the given length and
        //the user just pressed a number or letter, then move the
        //cursor to the next element in the tab sequence.   
        if (CurrentElement.val().length >= FieldLength
            && ((KeyID >= 48 && KeyID <= 90) ||
            (KeyID >= 96 && KeyID <= 105)))
            NextElement.focus();
    });
}