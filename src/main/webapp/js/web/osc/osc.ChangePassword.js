var _oscClazzChangePassword = new OSCClazzChangePassword();

$().ready(function() {
	_oscClazzChangePassword.init();
	var opts = _oscClazzChangePassword.getOption();
	
	$(opts.buttons.save).click(function() {
		var fValid = $(opts.form.name).valid();
		var newPassword = $(opts.form.fields.newPassword).val();
		var confirmPassword = $(opts.form.fields.confirmPassword).val();
		if (fValid && (newPassword != confirmPassword)) {
			$(opts.form.fields.errorMessage).html('&nbsp;&nbsp;&nbsp;<font color="red">' + CONFIRM_PASSWORD_INVALID + '</font>');
			fValid = false;
		}
		
		if (fValid) {
			var currentPassword = $(opts.form.fields.currentPassword).val();
			// validate trading password
			$.ajax({
				type: "POST",
				dataType: "json",
				url: VALIDATE_TRADING_PASSWORD,
				data : 'model.tradingPassword=' + currentPassword,
				success : function(responseText, statusText) {
					var model = responseText.model;
					if (model.errorMessage == "" || model.errorMessage == null) {
						$(opts.form.name).attr("action", CHANGE_PASSWORD);
						$(opts.form.name).submit();
					} else {
						$(opts.form.fields.errorMessage).html('&nbsp;&nbsp;&nbsp;<font color="red">' + model.errorMessage + '</font>');
						$(opts.form.fields.currentPassword).val(""); // clean password
						$(opts.form.fields.newPassword).val(""); // clean password
						$(opts.form.fields.confirmPassword).val(""); // clean password
					}
				}
			});
		} else {
			$(opts.form.fields.currentPassword).val(""); // clean password
			$(opts.form.fields.newPassword).val(""); // clean password
			$(opts.form.fields.confirmPassword).val(""); // clean password
		}
	});
});