OnlineAccountComp = function() {
	this.onlineAccountView = new OnlineAccountView();
	this.onlineAccountModel = new OnlineAccountModel();
	this.listenFromView();
	this.listenFromModel();
};

OnlineAccountComp.prototype = {

	listenFromView : function() {
		this.onlineAccountView.openAccountButton.click(function(){
			$(".errorMessage").hide();
			if(!this.onlineAccountView.agreeOpenAccountElement.is(":checked")){
				this.onlineAccountView.errorMessageElement.html(this.onlineAccountView.agreeRegulationErrorMessage);
				this.onlineAccountView.errorMessageElement.show();
				$(window).scrollTop(0);
				return;
			} else {
				this.onlineAccountView.errorMessageElement.html("");
				this.onlineAccountView.errorMessageElement.hide();
			}
			var isValidInput = false;
			isValidInput = this.onlineAccountView.isNotEmptyInput();
			if(isValidInput) {
				this.onlineAccountView.openAccountForm.submit();
				this.onlineAccountView.errorMessageElement.html("");
				this.onlineAccountView.errorMessageElement.hide();
			} else {
				$(window).scrollTop(0);
				this.onlineAccountView.errorMessageElement.html(this.onlineAccountView.emptyErrorInput);
				this.onlineAccountView.errorMessageElement.show();
			}
		}.bind(this));
		
		this.onlineAccountView.cancelButton.click(function(){
			this.onlineAccountView.resetFormInput();
		}.bind(this));
		
		this.onlineAccountView.banksNameElement.change(function(){
			this.onlineAccountModel.setBankName(this.onlineAccountView.banksNameElement.val());
		}.bind(this));
		
		this.onlineAccountView.identityNoElement.blur(function(){
			if(this.onlineAccountView.identityNoElement.val() != "") {
				this.onlineAccountView.checkIdentityNumber();
			} else {
				this.onlineAccountView.resetFormByElement(this.onlineAccountView.identityNoElement,
						this.onlineAccountView.identityErrorElement);
			}
			
		}.bind(this));
		
		this.onlineAccountView.emailElement.blur(function(){
			if(this.onlineAccountView.emailElement.val() != "") {
				this.onlineAccountView.checkEmailOrUsername();
			} else {
				this.onlineAccountView.resetFormByElement(this.onlineAccountView.emailElement, this.onlineAccountView.emailErrorElement);
			}
		}.bind(this));
		
		this.onlineAccountView.usernameElement.blur(function(){
			if(this.onlineAccountView.usernameElement.val() != "") {
				this.onlineAccountView.checkEmailOrUsername();
			} else {
				this.onlineAccountView.resetFormByElement(this.onlineAccountView.usernameElement, this.onlineAccountView.usernameErrorElement);
			}
			
		}.bind(this));
		
		this.onlineAccountView.birthdayElement.focus(function(){
			this.onlineAccountView.birthdayElement.removeAttr("placeHolder");
		}.bind(this));
		this.onlineAccountView.birthdayElement.blur(function(){
			if(this.onlineAccountView.birthdayElement.val() == ""){
				this.onlineAccountView.birthdayElement.attr("placeHolder", "dd/mm/yyyy");
			}
		}.bind(this));
		
		this.onlineAccountView.identityDateElement.focus(function(){
			this.onlineAccountView.identityDateElement.removeAttr("placeHolder");
		}.bind(this));
		this.onlineAccountView.identityDateElement.blur(function(){
			if(this.onlineAccountView.identityDateElement.val() == ""){
				this.onlineAccountView.identityDateElement.attr("placeHolder", "dd/mm/yyyy");
			}
		}.bind(this));
	},

	listenFromModel : function() {
		this.onlineAccountModel.on("onChangeBankName", function(){
			this.onlineAccountView.getBranhesList(this.onlineAccountModel.bankName);
		}.bind(this));
	},
	validateInput: function(){
		this.onlineAccountView.validateInput();
	},
	addEventBlur: function(){
		this.onlineAccountView.setEventsBlur();
	},

	addEnterTabsEvent: function(){
		this.onlineAccountView.addEnterTabsEvent();
		this.onlineAccountView.addEventAutoTabs();
	},
	emit : function(msgName, msgData) {
		$(this).trigger({
			type : msgName,
			message : msgData,
			time : new Date()
		});
	},

	on : function(msgName, callback) {
		$(this).bind(msgName, callback);
	}
};