var _openaccountclazzFreeRegistedUsers = new OpenaccountClazzFreeRegistedUsers();

$(document).ready(function(){
	var otherStreet = false;
	
	//+++ init OpenaccountClazzFreeRegistedUsers
	_openaccountclazzFreeRegistedUsers.init();
	
	var opts = _openaccountclazzFreeRegistedUsers.getOption();
	
	//+++Date
	var d = new Date();
	var currentyear = d.getFullYear();
    $('#fFreeRegistedUsers_dateofBirth').datepicker({
        changeMonth: true,
        changeYear: true,
        yearRange : "1900:" + currentyear,
        onSelect: function (){return false;},
        onClose: function (){return false;}
    });
	
    //+++Create
	$(opts.form.buttons.save).click(function(){ 
		var fValid = $(opts.form.name).valid();
		if(fValid) {
			_openaccountclazzFreeRegistedUsers.execute();
		}		
	});	
	
	//+++ Disable And Enable Save button
	$('#fFreeRegistedUsers_agreement').click(function() {
        if (this.checked) {
        	$('#fFreeRegistedUsers_btSave').removeAttr('disabled');               
        } else {
	    	$('#fFreeRegistedUsers_btSave').attr("disabled", "disabled");
	    }   

    });
});