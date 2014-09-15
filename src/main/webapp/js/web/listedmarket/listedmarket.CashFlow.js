/**
* All javascript code of Login functionality store in this file.
*/

var _cashFlowClazzSearchListing = new CashFlowClazzSearchListing();   

$(document).ready(function(){			
	var otherStreet = false;
	
	//+++ init CashFlowClazzSearchListing
	_cashFlowClazzSearchListing.execute();
	
	var opts = _cashFlowClazzSearchListing.getOption();
		
	//+++ search
	$(opts.buttons.btView).click(function(){ 
		var fValid = $(opts.form.name).valid();
		if(fValid) {
			_cashFlowClazzSearchListing.execute();
		}
	});
	
	//+++ combobox selected
	$(opts.form.fields.moneyUnit).click(function(){ 
		var fValid = $(opts.form.name).valid();
		if(fValid) {
			_cashFlowClazzSearchListing.execute();
		}
	});
});
