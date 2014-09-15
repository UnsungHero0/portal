var _oscClazzChangeUserProfile = new OSCClazzChangeUserProfile();


$().ready(function() {
	var opts = _oscClazzChangeUserProfile.getOption();
	
	$(opts.form.fields.accountNumber).change(function(){
		// set action to form
		$(opts.form.name).attr("action", CHANGE_ACCOUNT_ACTION);
		// ajax submit
		_oscClazzChangeUserProfile.execute();
	});
	
	$(opts.buttons.confirm).click(function() {
		$(opts.form.fields.errorMessage).html("");
		_oscClazzChangeUserProfile.init();
		var fValid = $(opts.form.name).valid();
		if (fValid) {
			var tradingPassword = $(opts.form.fields.tradingPassword).val();
			// validate trading password
			$.ajax({
				type: "POST",
				dataType: "json",
				url: VALIDATE_TRADING_PASSWORD,
				data : 'model.tradingPassword=' + tradingPassword,
				success : function(responseText, statusText) {
					var model = responseText.model;
					if (model.errorMessage == "" || model.errorMessage == null) {
						$(opts.form.fields.userAction).val("CONFIRM");
						$(opts.form.name).attr("action", URL_CHANGE_USER_PROFILE);
						$(opts.form.name).submit();
					} else {
						$(opts.form.fields.errorMessage).html('&nbsp;&nbsp;&nbsp;<font color="red">' + model.errorMessage + '</font>');
						$(opts.form.fields.tradingPassword).val(""); // clean password
					}
				}
			});
		} else {
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