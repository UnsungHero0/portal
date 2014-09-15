OnlineAccountModel = function() {
	this.lastname = "";
	this.middlename = "";
	this.name = "";
	this.gender = "";
	this.birthday = "";
	this.identityNo = "";
	this.identityDate = "";
	this.identityPlace = "";
	this.address = "";
	this.email = "";
	this.password = "";
	this.username = "";
	this.retypePassword = "";
	this.mobilePassword = "";
	this.mobilePhoneNo = "";
	this.transferMoneyNumber = "";
	this.transferMoneyFullName = "";
	this.bankName = "";
	this.branchName = "";
};

OnlineAccountModel.prototype = {
		
	setBankName: function(value) {
		this.bankName = value;
		this.emit("onChangeBankName");
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