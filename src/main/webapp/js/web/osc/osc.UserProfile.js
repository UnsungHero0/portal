var _oscClazzUserProfile = new OSCClazzUserProfile();
var opts = _oscClazzUserProfile.getOption();

$().ready(function() {
	
	$(opts.form.fields.accountNumber).change(function(){
		// set value to caller
		$(opts.form.fields.caller).val(VIEW_USER_PROFILE);
		// set action to form
		$(opts.form.name).attr("action", CHANGE_ACCOUNT_ACTION);
		// ajax submit
		_oscClazzUserProfile.execute();
	});
});

function doChangeUserProfile() {
	$(opts.form.fields.caller).val(CHANGE_USER_PROFILE_FW);
	$(opts.form.name).attr("action", CHANGE_USER_PROFILE);
	$(opts.form.name).submit();
}

function doChangeTeleTradeFone() {
	$(opts.form.fields.caller).val(CHANGE_TELETRADE_FONES_FW);
	$(opts.form.name).attr("action", CHANGE_USER_PROFILE);
	$(opts.form.name).submit();
}

function doChangeTeleTradePassword() {
	$(opts.form.fields.caller).val(CHANGE_TELETRADE_PASSWORD_FW);
	$(opts.form.name).attr("action", CHANGE_USER_PROFILE);
	$(opts.form.name).submit();
}

function doChangeInternetEmail() {
	$(opts.form.fields.caller).val(CHANGE_INTERNET_EMAIL_FW);
	$(opts.form.name).attr("action", CHANGE_USER_PROFILE);
	$(opts.form.name).submit();
}