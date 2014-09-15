$(document).ready(function(){
	OnlineAccountMain.init();
});

OnlineAccountMain = {
	init: function(){
		$("#web_action_errors").hide();
		this.createOnlineAccountComp();
		this.validateInput();
		$('#birthday').datepicker({
	        changeMonth: true,
	        changeYear: true,
	        yearRange : "1930:2020",
	        onSelect: function (){return false;},
	        onClose: function (){return false;}
	    });
		
		$('#identityDate').datepicker({
	        changeMonth: true,
	        changeYear: true,
	        yearRange : "1930:2020",
	        onSelect: function (){return false;},
	        onClose: function (){return false;}
	    });
		this.addEnterTabsEvent();
		this.addEventBlur();
	},
	viewContract: function(){
			var url = $.web_resource.getContext() + "common/viewContentDetail.shtml?productCode=SP_DKMTK";
			window.open(url, "_blank", "");
			return;
	},
	createOnlineAccountComp: function(){
		this.onlineAccountComp = new OnlineAccountComp();
	},
	validateInput: function(){
		this.onlineAccountComp.validateInput();
	}, 
	addEventBlur: function(){
		this.onlineAccountComp.addEventBlur();
	},
	addEnterTabsEvent: function(){
		this.onlineAccountComp.addEnterTabsEvent();
	}
};