var _oscClazzChangeTeleTradePassword = new OSCClazzChangeTeleTradePassword();


$().ready(function() {
	var opts = _oscClazzChangeTeleTradePassword.getOption();
	
	$(opts.form.fields.accountNumber).change(function(){
		// set action to form
		$(opts.form.name).attr("action", CHANGE_ACCOUNT_ACTION);
		// ajax submit
		_oscClazzChangeTeleTradePassword.execute();
	});
	
	$(opts.buttons.confirm).click(function() {
		_oscClazzChangeTeleTradePassword.init();
		$(opts.form.fields.errorMessage).html("");
		var fValid = $(opts.form.name).valid();
		var telePasswordNew = $(opts.form.fields.telePasswordNew).val();
		var telePasswordReType = $(opts.form.fields.telePasswordReType).val();
		if (fValid && telePasswordNew.length != 4) {
			$(opts.form.fields.errorMessage).html('&nbsp;&nbsp;&nbsp;<font color="red">' + TELE_TRADE_PW_INVALID + '</font>');
			fValid = false;
		}
		
		if (fValid && (telePasswordNew != telePasswordReType)) {
			$(opts.form.fields.errorMessage).html('&nbsp;&nbsp;&nbsp;<font color="red">' + RETYPE_TELE_TRADE_PW_INVALID + '</font>');
			fValid = false;
		}
		
		if (fValid) {
			// validate trading password
			var options = {
				type: "POST",
				dataType: "json",
				url: VALIDATE_TRADING_PASSWORD,
				beforeSubmit : function(formData, jqForm, options) {
					var queryString = $.param(formData); 
				},
				success : function(responseText, statusText) {
					var model = responseText.model;
					if (model.errorMessage == "" || model.errorMessage == null) {
						$(opts.form.fields.userAction).val("CONFIRM");
						$(opts.form.name).attr("action", URL_CHANGE_USER_PROFILE);
						$(opts.form.name).submit();
					} else {
						$(opts.form.fields.errorMessage).html('&nbsp;&nbsp;&nbsp;<font color="red">' + model.errorMessage + '</font>');
						$(opts.form.fields.telePasswordOld).val(""); // clean password
						$(opts.form.fields.tradingPassword).val(""); // clean password
					}
				}
			};
			
			$(opts.form.name).ajaxSubmit(options);
		} else {
			$(opts.form.fields.telePasswordOld).val(""); // clean password
			$(opts.form.fields.tradingPassword).val(""); // clean password
		}
	});
	
	$(opts.buttons.change).click(function(){
		// set value to caller
		$(opts.form.fields.caller).val("");
		// set value to user action
		$(opts.form.fields.userAction).val("");
		// url to view user profile
		$(opts.form.name).attr("action", URL_CHANGE_USER_PROFILE);
		// submit form
		$(opts.form.name).unbind().submit();
	});
});