<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://www.webapp.com.vn/tags-fmt" prefix="webFmt" %>

<script>
$().ready(function() {

	$.web_autocomplete.symbols('addSymbol4ChartId', URL_SYMBOL_AUTO_SUGGESTION,
			{width : 310
			,callbackResult: function(e, item){
				
			}
			}
	);

});


function _goTo(webNavId, index) {
	try {
		pagingIndex = index;
		doSearchSymbol();
	} catch (e) {
		alert("_goTo(): " + e);
	}
}

function doCheck(checkID, checkAllID) {
	var checkObj = $("#"+checkID);
	var textObj = $("#"+"text" + checkObj.val() + "ID");
	if($("#"+checkID+":checked").val()) {
		var curValue = $("#"+"cur" + checkObj.val() + "ID").html();
		//curValue = replaceAll(curValue);

		textObj.val(curValue);
		var checkerArr = document.getElementsByName('selectItem');
		$("#"+checkAllID).attr('checked', true);
		for (var i=0; i < checkerArr.length; i++ ) {
			if (checkerArr[i].checked == false) {
				$("#"+checkAllID).attr('checked', false);
				break;
			}
		}
	} else {
		textObj.value = "";
		$("#"+checkAllID).attr('checked', false);
	}
}
var pagingIndex = 1;

function doSearchSymbol() {
var params = [
             	{name : 'searchSymbol.symbol', value : $('#addSymbol4ChartId').val()},
             	{name : 'searchSymbol.exchangeCode', value : $('#fSearchSymbol_exchangeCode').val()},
             	{name : 'pagingInfo.indexPage', value : pagingIndex}
             	
            ];
$.ajax({
	   type: "POST",
	   url: URL_SEARCH_SYMBOL_AJAX,
	   data: params,
	   dataType: "json",
	   success: function(data) {
	var model = data.model;	
	var content = '';
	var locale = model.lang;
	content += "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">";
	if (model.result != null && model.result.length > 0){
		for(var index in model.result) {
			var obj = model.result[index];
			
			content += "<tr>";
			
			content += "<td width=\"5%\" class=\"bordertd\"> ";
			content += "<input name='selectItem' type='checkbox' value='"+ $.web_utils.toValue(obj.id.symbol) + "' onclick=\"doCheck('check" +$.web_utils.toValue(obj.id.symbol) + "ID','checkAllID')\" id='check" +$.web_utils.toValue(obj.id.symbol) + "ID'>";
			content += "</td>";
			content += "<td width=\"5%\" class=\"bordertd\"><span class=\"txtText\">";
			content += "<a href=\"javascript:selectSymbol('" + $.web_utils.toValue(obj.id.symbol) + "')\">";
			content +=  $.web_utils.toValue(obj.id.symbol);
			content += "</a>";
			content += "</td>";
			
			content += "<td width=\"15%\" class=\"bordertd\"><span class=\"txtText\">";
			content += "<a href=\"javascript:selectSymbol('" + $.web_utils.toValue(obj.id.symbol) + "')\">";
			content += "<b>";
			content +=  $.web_utils.toValue(obj.id.abbName);
			content += "</b>";
			content += "</a>";
			content += "</td>";
			
			content += "<td width=\"35%\" class=\"bordertd\"><span class=\"txtText\">";
			if (locale = 'vn')
				content +=  $.web_utils.toValue(obj.id.companyName);
			else
				content +=  $.web_utils.toValue(obj.id.companyFullName);
			content += "</td>";
			
			content += "</tr>";
		}
	}else {
		//+++ show message not found
			content += '<tr><td class="company_left"><span class="error">&nbsp;&nbsp;' + SYMBOLPROCESSINGACTION_NOT_FOUND + '</span></td>';
			content += '<td class="company_black"></td><td class="company_gray"></td><td class="company_right"></td></tr>';

	}
	content += "</table>";
	
	$('#fSearchSymbol_result').html(content);
	
	var funcOptions = {
		goto_func: "_goTo"
	};
	var idOptions = {
		navContainer : "fSearchSymbol_paging"
	};
	$.web_paging.showItem(model.pagingInfo, funcOptions, idOptions);	
	   },
	   beforeSend: function(){
	   }
});

}
var ADD_REMOVE_SYMBOL_COMPETITOR_ACTION = "ADD_REMOVE_SYMBOL_COMPETITOR";
function doDraw() {
	try {
		var addedSymbols = getCheckboxValues("selectItem").join("|");
		var removedSymbols = "";
		var paramNames = new Array("selectedCompetitor", "removedCompetitor", "chartAction");

		var paramValues = new Array();
		paramValues[0]= addedSymbols;
		paramValues[1]= removedSymbols;
		paramValues[2]= "DRAW";

		doDrawChart(paramNames, paramValues, ADD_REMOVE_SYMBOL_COMPETITOR_ACTION);
$("#dialog").dialog('close');
	} catch (e) {
		alert("doDraw():" + e);
	}
	return;
}


</script>
<form method="post">
    <div class="pn_main" id="phan_tich_co_ban">
        <div class="tab clearfix">
			<div class="tab_infor">
				<a class="select_tab"><b>Thêm Mã chứng khoán</b></a>
			</div>
			<div style="border:1px solid #616D7E; border-top:none;">
				<table border="0">
					<tr>
						<td>Mã chứng khoán:</td>
						<td>
							<input id="addSymbol4ChartId" name="symbol" value="" />
						</td>
					</tr>
					<tr>
						<td>Sàn GDCK:</td>
						<td>
							<select id="fSearchSymbol_exchangeCode" name="searchSymbol.exchangeCode">
                      			<option value="%">---</option>
                      			<s:iterator value="listIfoExchange">
                      				<option value="${exchangeCode}">${exchangeName}</option>
                      			</s:iterator>
                      		</select>
							
							<span  class="btn_left_inbox">
								<span class="btn_right_inbox">
									<span class="btn_center_inbox">
										<input type="button"  onclick="doSearchSymbol()" value="Tìm kiếm" style="text-transform:uppercase;" />
									</span>
								</span>
							</span>
						</td>
					</tr>
				</table>
				<table width="100%">
					<tr bgcolor="#efefef">
                            <td>
                            	<table cellpadding="0" cellspacing="0" border="0" width="100%">
									<colgroup>			
										<col width="5%" />
										<col width="5%" />
										<col width="15%" />
										<col width="35%" />
										
									</colgroup>
									<tr>
										<td width="5%" align="center" class="bordertd"><b>&nbsp;</b></td>
		                           		<td width="5%" align="center" class="bordertd"><b><s:text name="web.label.SymbolProcessingAction.form.searchresult.symbol">Symbol</s:text></b></td>
		                               	<td width="15%" align="center" class="bordertd"><b><s:text name="web.label.SymbolProcessingAction.form.searchresult.companyName">Company Name</s:text></b></td>
		                               	<td width="35%" align="center" class="bordertd"><b><s:text name="web.label.SymbolProcessingAction.form.searchresult.companyFullName">Company Full Name</s:text></b></td>
									</tr>
							</table>    
                            </td>
                    </tr>
				</table>
							  
				 <div class="table_Market clearfix">
                   	<div id="fSearchSymbol_result"></div>
                      	
                      	<span  class="btn_left_inbox">
								<span class="btn_right_inbox">
									<span class="btn_center_inbox">
										<input type="button"  onclick="doDraw()" value="Tìm kiếm" style="text-transform:uppercase;" />
									</span>
								</span>
							</span>
						<div class="fpCalendar">
							<div class="fpCalendar-left"></div>
							<div class="fpCalendar-right"></div>
							<div align="right" class="fpCalendar-center" id="fSearchSymbol_paging"></div>
						</div>
                   </div>
                     	
			</div>
		</div>
   	</div>
  
</form>