<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<script type="text/javascript">
<!--
$().ready(function() {
	$('.modify').click(function(){
		$('#cacheForm').attr("action", URL_STOCK_SCREENER);
		$('#cacheForm').submit();
	})
	$('.save').click(function(){		
		$('#cacheForm').submit();
	})	
});
//-->
</script>

<s:set name="stockScreener" value="true" scope="action"></s:set>
<s:set name="listedScreenEnable" value="true" scope="action"></s:set>
<s:set name="listedScreen" value="true" scope="action"></s:set>
<s:set name="stockWizard" value="true" scope="action"></s:set>

<tiles:insertDefinition name="Analysis.InvestmentIdeasNavTmpl">
	<tiles:putAttribute name="MainContent">
		<s:property value="searchStockScreenerBean.saveSearchName" />

		<form method="post" id="cacheForm"
			action="<web:url value="/cong-cu-phan-tich-chung-khoan/stock-screener-saving.shtml"/>">

			<div class="box_timtieuchi clearfix">
				<p>
					<strong><s:text
							name="web.label.Result_View.SaveSearchName" />
					</strong>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" class="input330" name="name">
				</p>
				<s:hidden id="cacheData" name="cacheData"></s:hidden>
				<p class="padding-top10">
					<%--<span class="button_long">
                       <input type="button" class="save button" value="<s:text name="button.save"/>" />
                  </span>
                  --%>
					<input type="button" class="save iButton"
						value='<s:text name="button.save"/>'>
					<%--<span class="button_long">
                       <input type="button" class="modify" value="<s:text name="web.label.Result_View.ModifySearch_Button"/>" />
                  </span>
                  --%>
					<input type="button" class="modify iButton"
						value='<s:text name="web.label.Result_View.ModifySearch_Button"/>'>
				</p>
			</div>
		</form>
	</tiles:putAttribute>
</tiles:insertDefinition>