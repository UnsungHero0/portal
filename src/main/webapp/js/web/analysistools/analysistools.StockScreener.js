var stockScreenerClass = new StockScreenerClass();
var opts = stockScreenerClass.getOption();

var scr_btn_left = $.web_resource.getContext() + "/images/web/btn_left.png";
var scr_btn_right = $.web_resource.getContext() + "/images/web/btn_right.png";
var scr_btn_left_d = $.web_resource.getContext() + "/images/web/btn_left_d.png";
var scr_btn_right_d = $.web_resource.getContext() + "/images/web/btn_right_d.png";

var listOfItems = new Array(
	"asset_class_1","asset_class_2","asset_class_3","asset_class_4","component_1","component_2","component_3",
	"price_change_1","price_change_2","price_change_3","price_change_4","price_change_5","price_performance_1","price_performance_2","price_performance_3","price_performance_4","price_performance_5","price_performance_6",
	"over_1","over_2","over_3","pe_ratio_1","pe_ratio_2","pe_ratio_3","pe_ratio_4","pe_ratio_5","pe_ratio_6","pe_ratio_7","peg_1","peg_2",
	"profit_margin_1","profit_margin_2","profit_margin_3","profit_margin_4","profit_margin_5","profit_margin_and_1","profit_margin_and_2","price_sale_ratio_1",
	"price_sale_ratio_2","price_sale_ratio_3","price_sale_ratio_4","price_sale_ratio_5","price_sale_ratio_6","price_sale_ratio_7","price_book_ratio_1","price_book_ratio_2","price_book_ratio_3","price_book_ratio_4","price_book_ratio_5",
	"return_equity_5","return_equity_1","return_equity_2","return_equity_3","return_equity_4","return_equity_and_1","return_equity_and_2","return_asset_5","return_asset_1","return_asset_2","return_asset_3","return_asset_4","return_asset_and_1","return_asset_and_2",
	"eps_growth_annual_1","eps_growth_annual_2","eps_growth_annual_3","eps_growth_annual_4","eps_growth_annual_5","eps_growth_annual_6","eps_growth_annual_7",
	"eps_growth_annual_8","revenue_growth_annual_1","revenue_growth_annual_2","revenue_growth_annual_3","revenue_growth_annual_4","revenue_growth_annual_5","revenue_growth_annual_6",
	"revenue_growth_annual_7","revenue_growth_annual_8","eps_growth_quarterly_1","eps_growth_quarterly_2","eps_growth_quarterly_3","eps_growth_quarterly_4","eps_growth_quarterly_5",
	"eps_growth_quarterly_6","eps_growth_quarterly_7","eps_growth_quarterly_8","revenue_growth_quarterly_1","revenue_growth_quarterly_2","revenue_growth_quarterly_3",
	"revenue_growth_quarterly_4","revenue_growth_quarterly_5","revenue_growth_quarterly_6","revenue_growth_quarterly_7","revenue_growth_quarterly_8","dividend_growth_5_year_1",
	"dividend_growth_5_year_2","dividend_growth_5_year_3","dividend_growth_5_year_4","dividend_growth_5_year_5","dividend_yield_1","dividend_yield_2","dividend_yield_3","dividend_yield_4",
	"dividend_yield_5","dividend_yield_6","dividend_yield_and_1","dividend_yield_and_2","_52_week_high_1","_52_week_low_1","price_closes_sam_1","price_closes_sam_2",
	"price_closes_sam_3","price_closes_sam_4","price_closes_sam_5","price_closes_sam_6","price_closes_sam_7","price_closes_sam_8","above_below_1","above_below_2",
	"_13_day_sma_1",
	"_13_day_sma_2"
);

function doSummary(){
	if (document.getElementById("cnt_all_c_result_1").innerHTML != '0') {
		document.vndirectCntFrm.method = 'post';
		document.vndirectCntFrm.action = $.web_resource.getContext() + "cong-cu-phan-tich-chung-khoan/ket-qua-sang-loc-co-phieu.shtml";
		document.vndirectCntFrm.submit();
	} else {
		return;
	}
}

// change color #1B327D to #333333 
function changeColor(id) {
	var color = $('#' + id).css("color").toLowerCase();
	if (color == 'rgb(51, 51, 51)' || color == '#333333') {
		color = '#FF0000';
	} else {
		color = '#333333';
	}
	$('#' + id).css("color", color);
}

function toggleStatus(obj) {
	var id = obj.id;
	changeColor(id);
	calculateData(id);
}

function calculateData(id) {
	// setdata
	var ditem = document.getElementById(id+".id");
	if (ditem.value == '1') {
		ditem.value = '';
	} else {
		ditem.value = '1';
	}
	//validate before submit
	var _stockScreenerClass = new StockScreenerClass;
	_stockScreenerClass.execute();
}

function inputText(id) {
	// setdata
	if (id == 'sector') {
		document.getElementById("industry.id").value = '';
	}
	document.getElementById(id+".id").value = document.getElementById(id).value;
	//validate before submit
	var _stockScreenerClass = new StockScreenerClass;
	_stockScreenerClass.execute();
}

//+++ add show industry
function getListIndustry() {
	if ("" == document.getElementById("sector.id").value) {
		document.getElementById("industry.id").value = '';
		document.getElementById("divShowIndustryListName_id").innerHTML="<select onchange='javascript:inputText(\"industry\");' id=\"industry\"></select>";
		$("#divShowIndustryListName_id").hide();
	} else {
		doGetListIndustry(document.getElementById("sector.id"), '',document.getElementById("divShowIndustryListName_id"), document.getElementById("request_locale.id"));
	}
}

function listSaveSearch() {
	document.vndirectFrm.action="<vndirect:uriRewrite uri='../stock_screen/SaveSearch.do'/>";
	document.vndirectFrm.USERACTION.value = "LIST_SEARCH";
	document.vndirectFrm.submit();
	return;
}

function doClear(){
	try {
		// Clear text box & combobox
		document.getElementById("sector").value='';
		document.getElementById("divShowIndustryListName_id").innerHTML="<select onchange='javascript:inputText(\"industry\");' id=\"industry\"></select>";
		$("#divShowIndustryListName_id").hide();
		document.getElementById("share_price_from").value='';
		document.getElementById("share_price_to").value='';
		document.getElementById("price_change_from").value='';
		document.getElementById("average_volume_from").value='';
		document.getElementById("average_volume_to").value='';
		document.getElementById("_52_week_high").value='';
		document.getElementById("_52_week_low").value='';

		document.getElementById("sector.id").value='';
		document.getElementById("share_price_from.id").value='';
		document.getElementById("share_price_to.id").value='';
		document.getElementById("price_change_from.id").value='';
		document.getElementById("average_volume_from.id").value='';
		document.getElementById("average_volume_to.id").value='';
		document.getElementById("_52_week_high.id").value='';
		document.getElementById("_52_week_low.id").value='';
		document.getElementById("industry.id").value = '';

		var i;
		// mark color
		for(i=0; i< listOfItems.length; i++) {
			document.getElementById(listOfItems[i]+".id").value ='';
			$('#' + listOfItems[i]).css("color", "#333333");
		}

		// Fill combobox
		document.getElementById("sector.id").value = '';

		// Do search
		var _stockScreenerClass = new StockScreenerClass;
		_stockScreenerClass.execute();
	} catch(e) {
		alert(e);
	}
	return;
}

// fix here
function doGetListIndustry(sectorCode, industryCode, divShowIndustryListName) {
	var sCode = (!sectorCode ? '' : sectorCode.value);
	var iCode = (!industryCode ? '' : industryCode.value);
	var lCode = $("input[name=request_locale]").val();
	// fix when hide multi languate
	lCode = (lCode == null) ? 'vn' : lCode;
	$.ajax({
		  type: "POST",
		  url: URL_STOCK_SCREENER_GET_LIST_INDUSTRY_AJAX,
		  data: "ifoIndustryCalcView.sectorCode=" + sCode + "&ifoIndustryCalcView.industryCode=" + iCode + '&ifoIndustryCalcView.locale=' + lCode,
		  dataType: "json",
		  success: function(data) {
			$("#divShowIndustryListName_id").show();
			
			var arrIndustryList = new Array();
			// add option choose all
			arrIndustryList.push(new Array('', $('#allIndustriesText').val()));
			var obj;
			var arrOjb;
			for(var index in data.model.ifoIndustryCalcViews) {
				obj = data.model.ifoIndustryCalcViews[index];
				arrOjb = new Array(obj.industryCode, obj.industryName);
				arrIndustryList.push(arrOjb);
			}
			$("#industry").comboboxManager_removeAll();
			$("#industry").comboboxManager_addItems(arrIndustryList);
			inputText("industry");
		  }
	});
}

$().ready(function() {
	$('#container-1').tabs();
	$('#container-2').tabs();
	$('#container-3').tabs();
	
	try {
		var i;
		// mark color
		for(i=0; i< listOfItems.length; i++) {
			if (document.getElementById(listOfItems[i]+".id").value =='1') {
				changeColor(listOfItems[i]);
			}
		}
		// Fill combobox
		if ("" != document.getElementById("sector.id").value) {
			doGetListIndustry(document.getElementById("sector.id"), document.getElementById("industry.id"));
		}
		// Do search
		var _stockScreenerClass = new StockScreenerClass;
		_stockScreenerClass.execute();

		// Do alert
		var alertMsg = $("#alertMsg").text();
		if (alertMsg == '1') {
			$.web_utils.showTextBox("", '#TB_inline?height=150&width=300&modal=true&inlineId=waringMgs_id');
		}
	} catch(e) {
		alert(e);
	}
});
