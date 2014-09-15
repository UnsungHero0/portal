var _oscClazzOSCAfterLogin = new OSCClazzOSCAfterLogin();

$().ready(function() {
	_oscClazzOSCAfterLogin.init();
	var opts = _oscClazzOSCAfterLogin.getOption();
	
//	loadTabOnlineServiceCenter();
//	loadTabFAQs();
//	loadTabFormAndApplication();
		
	$('#osc_validation').dialog({
		width: 450,
		autoOpen: false,
		resizable: false,
		buttons: {
			Close: function(){				
				$(this).dialog('close');
			}
		}
	});	
});

function loadTabOnlineServiceCenter() {
	// get list products for service your account
	$.ajax({
	   type: "POST",
	   dataType: "json",
	   url: URL_GET_PRODUCT_DETAIL,
	   data: "wpProduct.productCode=SERVICE_ACCOUNT",
	   success: _oscClazzOSCAfterLogin.genSubjectList
	});
	
	// get list products for manage your cash
	$.ajax({
	   type: "POST",
	   dataType: "json",
	   url: URL_GET_PRODUCT_DETAIL,
	   data: "wpProduct.productCode=MANAGE_CASH",
	   success: _oscClazzOSCAfterLogin.genSubjectList
	});
}

function loadTabFAQs() {
	// get list products for FAQ
	$.ajax({
	   type: "POST",
	   dataType: "json",
	   url: URL_GET_PRODUCT_DETAIL,
	   data: "wpProduct.productCode=FAQs",
	   success: _oscClazzOSCAfterLogin.genSubjectList
	});
}

function loadTabFormAndApplication() {
	// get list products for form and application
	$.ajax({
	   type: "POST",
	   dataType: "json",
	   url: URL_GET_DOCUMENT_PRODUCT,
	   data: "wpDocument.wpProduct.wpProductGroup.productGroupType=2",
	   success: _oscClazzOSCAfterLogin.genFormAndApplication
	});
}

function loadTabContactUs() {
	$.ajax({
	   type: "POST",
	   dataType: "json",
	   url: URL_GET_PRODUCT_DETAIL,
	   data: "wpProduct.productCode=CONTACT_US",
	   success: _oscClazzOSCAfterLogin.genContactUs
	});
}

function getFileExtension(filename) {
  var ext = /^.+\.([^.]+)$/.exec(filename);
  return ext == null ? "" : ext[1];
}

function viewContentDetail(productCode, subjectId) {
	document.getElementById(productCode + '|' + subjectId).removeAttribute('style');
//	$('#' + productCode + '|' + subjectId).removeAttr('style');
	
	
}