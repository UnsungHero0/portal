var _companyEvents = new CompanyEvents();
var opts = _companyEvents.getOption();

$().ready(function() {
	_companyEvents.init();
	
	$(opts.buttons.search).click(function(){
		_companyEvents.searchEvents();
	});
	
	// pick date for field fromDate
	var d = new Date();
	var currentyear = d.getFullYear();
	$(opts.form.fields.fromDate).datepicker( {
		changeMonth : true,
		changeYear : true,
		yearRange: "1930:" + currentyear,
		onSelect : function() {
			if($('#fCompanyEvents_toDateId').val() != ""){
				_companyEvents.searchEvents();
			}
			return false;
		},
		onClose : function() {
			return false;
		}
	});
	
	// pick date for field toDate
	$(opts.form.fields.toDate).datepicker( {
		changeMonth : true,
		changeYear : true,
		yearRange: "1930:" + currentyear,
		onSelect : function() {
			if($('#fCompanyEvents_fromDateId').val() != ""){
				_companyEvents.searchEvents();
			}
			return false;
		},
		onClose : function() {
			return false;
		}
	});
	
	_companyEvents.searchEvents();
	
	$('.barLoadMore #loadMore').click(function(){
		_companyEvents.loadMoreNews();
	});
});

function _goTo(id, index) {
	try {
		$(opts.form.fields.pagingIndex).val(index);
		_companyEvents.searchEvents();
	} catch (e) {
		alert("_goTo(): " + e);
	}
}