OnlineAccountView = function() {
	this.lastnameElement = $("#lastname");
	this.middlenameElement = $("#middlename");
	this.nameElement = $("#name");
	this.genderElement = $("#gender");
	this.birthdayElement = $("#birthday");
	this.identityNoElement = $("#identityNo");
	this.identityDateElement = $("#identityDate");
	this.identityPlaceElement = $("#identityPlace");
	this.addressElement = $("#address");
	this.provinceElement = $("#province_id");
	this.emailElement = $("#email");
	this.passwordElement = $("#password");
	this.usernameElement = $("#username");
	this.retypePasswordElement = $("#retypePassword");
	this.mobilePass0Element = $("#mobilePass0");
	this.mobilePass1Element = $("#mobilePass1");
	this.mobilePass2Element = $("#mobilePass2");
	this.mobilePass3Element = $("#mobilePass3");
	this.mobilePhoneNoElement = $("#mobilePhoneNo");
	this.transferMoneyNumberElement = $("#transferMoneyNumber");
	this.transferMoneyFullNameElement = $("#transferMoneyFullName");
	this.openAccountForm = $("#openOnlineAccountForm");
	this.openAccountButton = $("#openAccountButton");
	this.cancelButton = $("#cancelButton");
	this.agreeOpenAccountElement = $("#agreeOpenAccount");
	this.errorOpenAccountElement = $("#error_open_account");
	this.identifyNumberLabelElement = $("#identifyNumberId");
	this.emailInfoIdElement = $("#emailInfoId");
	this.usernameInfoIdElement = $("#usernameInfoId");
	this.acceptButtonElement = $("#accept_button");
	// Ten ngan hang, chi nhanh
	this.banksNameElement = $("#bank_id");
	this.branchNameElement = $("#bank_branches_id");
	this.bankNumberLabelId = $("#bankNumberLabelId");
	//Error Message
	this.emptyErrorInput = $("#emptyInputErrorMessage").val();
	this.errorDuplicateIndentityMessage = $("#errorDuplicateIndentityMessage").val();
	this.errorDuplicateEmailMessage = $("#errorDuplicateEmailMessage").val();
	this.errorDuplicateUsernameMessage = $("#errorDuplicateUsernameMessage").val();
	this.agreeRegulationErrorMessage = $("#errorAgreeRegulation").val();
	//Display error message
	this.emailErrorElement = "emailErrorId";
	this.usernameErrorElement = "usernameErrorId";
	this.identityErrorElement = "identifyErrorId";
	this.errorMessageElement = $("#validate_input_error_message");
	this.mandatoryInputFields = [this.nameElement, this.lastnameElement, this.birthdayElement,
	           this.identityNoElement, this.identityDateElement, this.identityPlaceElement,
	           this.emailElement, this.usernameElement, this.passwordElement, this.retypePasswordElement, this.mobilePhoneNoElement,
	           this.mobilePass0Element, this.mobilePass1Element, this.mobilePass2Element,
	           this.mobilePass3Element, this.addressElement, this.provinceElement];
	this.optionalInputFields = [this.middlenameElement, this.banksNameElement, this.branchNameElement, this.transferMoneyFullNameElement, this.transferMoneyNumberElement];
};

OnlineAccountView.prototype = {
	getBranhesList: function(bankCode) {
		var url = $.web_resource.getContext() + "ajax/openaccount/getBranches";
		var self = this;
		$.post(url, {
			"bankCode": bankCode
		}, function(dataResponse){
			var model = dataResponse.model;
			var html = '<option value="">Ch\u1ecdn chi nh\u00e1nh</option>';
			if(model.branchesList != null){
				for(var i=0; i<model.branchesList.length; i++) {
					html += "<option value='"+ model.branchesList[i].branchCode +"'>"+ model.branchesList[i].branchName + "</option>";
				}
				
				self.branchNameElement.html(html);
			}
			
//			if(model.branchListFromFile != null){
//				for(var i=0; i<model.branchListFromFile.length; i++) {
//					html += "<option value='"+ model.branchListFromFile[i].branchCode +"'>"+ model.branchListFromFile[i].branchName + "</option>";
//				}
//				
//				self.branchNameElement.html(html);
//			}
			
		}, "json");
	},
	checkIdentityNumber: function(){
		var identityNo = this.identityNoElement.val();
		var url = $.web_resource.getContext() + "ajax/openaccount/checkIdentityNumber";
		var self = this;
		$.post(url, {
			"account.identityNo": identityNo
		}, function(dataResponse){
			var model = dataResponse.model;
			if(model.duplicateIdentityNo){
				$("#identifyErrorId").remove();
				self.identityNoElement.css("border-color", "red");
				self.identifyNumberLabelElement.append("<label id='identifyErrorId' style='color: red; width: 240px; float: left'>"+self.errorDuplicateIndentityMessage+"</label>");
			} else {
				$("#identifyErrorId").remove();
				self.identityNoElement.css("border-color", "");
			}
		}, "json");
	},
	checkEmailOrUsername: function(){
		var email = this.emailElement.val();
		var username = this.usernameElement.val();
		var url = $.web_resource.getContext() + "ajax/openaccount/checkEmailOrUsername";
		var self = this;
		$.post(url, {
			"account.email": email,
			"account.username": username
		}, function(dataResponse){
			var model = dataResponse.model;
			if(model.existedEmail){
				$("#emailErrorId").remove();
				self.emailInfoIdElement.append("<label id='emailErrorId' style='color: #F39200; width: 240px; float: left'>"+self.errorDuplicateEmailMessage+"</label>");
			} else {
				$("#emailErrorId").remove();
				self.emailElement.css("border-color", "");
			}
			
			if(model.existedUsername){
				$("#usernameErrorId").remove();
				self.usernameElement.css("border-color", "red");
				self.usernameInfoIdElement.append("<label id='usernameErrorId' style='color: red; width: 240px; float: left'>"+self.errorDuplicateUsernameMessage+"</label>");
			} else {
				$("#usernameErrorId").remove();
				self.usernameElement.css("border-color", "");
			}
		}, "json");
	},
	
	validateInput: function(){
		this.openAccountForm.validate({debug: false});
		this.identityDateElement.rules("add", {dateITA:true });
		this.birthdayElement.rules("add", {dateITA:true });
		this.emailElement.rules("add", {email2:true });
		this.mobilePhoneNoElement.rules("add", {number:true, maxlength: 15 });
		this.identityNoElement.rules("add", {number:true, maxlength: 20});
		this.addressElement.rules("add", {maxlength: 100});
		this.transferMoneyFullNameElement.rules("add", {maxlength: 255});
		this.transferMoneyNumberElement.rules("add", {maxlength: 30});
		this.usernameElement.rules("add", {maxlength: 255});
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
		this.mobilePass0Element.keypress(numeric);
		this.mobilePass1Element.keypress(numeric);
		this.mobilePass2Element.keypress(numeric);
		this.mobilePass3Element.keypress(numeric);
	},
	isNotEmptyInput: function(){
		
		var result = true;
		for(var i=0; i<this.mandatoryInputFields.length; i++){
			result = this.processEmptyInput(this.mandatoryInputFields[i]) && result ;
		}
		return result;
		
	},
	
	processEmptyInput: function(element){
		if(element.val() == ""){
			element.css("border-color", "red");
			return false;
		} 
		element.css("border-color", "");
		return true;
	},

	setEventsBlur: function(){
		for(var i=0; i<this.mandatoryInputFields.length; i++){
			this.addEventBlur(this.mandatoryInputFields[i]);
		}
	},
	addEventBlur: function(element){
		$(element).blur(function(){
			if($(this).val() != "") {
				$(this).css("border-color", "");
			}
		});
	},
	
	resetFormInput: function(){
		this.errorMessageElement.text("");
		for(var i=0; i<this.mandatoryInputFields.length; i++){
			this.mandatoryInputFields[i].val("");
			this.mandatoryInputFields[i].css("border-color", "");
		}
		
		for(var j=0; j<this.optionalInputFields.length; j++){
			this.optionalInputFields[j].val("");
		}
	},
	resetFormByElement: function(element, elementErrorMessage){
		element.val("");
		element.css("border-color", "");
		$("#"+elementErrorMessage).remove();
	},
	addEnterTabsEvent: function(){
		var inputs =$('#openOnlineAccountForm').find(':input:enabled:not([readonly]):visible');
		$('#openOnlineAccountForm input, select').keyup(function(e){
			if(e.keyCode == '13'){
				var nextInput = 0;
				if (inputs.index(this) < (inputs.length-1)) nextInput = inputs.index(this)+1;
	            if (inputs.length==1) $(this).blur().focus();
	            else {
	            	inputs.eq(nextInput-1).blur();
	            	inputs.eq(nextInput).focus();
	            	inputs.eq(nextInput).select();
	            }
	        }			
		});
	},
	addEventAutoTabs: function(){
		$("input[name^='account.mobilePassword']").each(function(i, item) {
			$(this).autotab({
				target : 'mobilePass' + (i + 1)
			});
		});
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