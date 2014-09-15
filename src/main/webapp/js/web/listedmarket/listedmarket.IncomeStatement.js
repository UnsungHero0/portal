/**
* All javascript code of Login functionality store in this file.
*/

var _incomeStatementClazzSearchListing = new IncomeStatementClazzSearchListing();   

$(document).ready(function(){			
	var otherStreet = false;
	
	//+++ init IncomeStatementClazzSearchListing
	_incomeStatementClazzSearchListing.execute();
	
	var opts = _incomeStatementClazzSearchListing.getOption();
		
	//+++ search
	$(opts.buttons.btView).click(function(){ 
		var fValid = $(opts.form.name).valid();
		if(fValid) {
			_incomeStatementClazzSearchListing.execute();
		}
	});
	
	//+++ combobox selected
	$(opts.form.fields.moneyUnit).click(function(){ 
		var fValid = $(opts.form.name).valid();
		if(fValid) {
			_incomeStatementClazzSearchListing.execute();
		}
	});
});
