<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<ul class="ui-tabs-nav tab_ttpt">
	<li
		<web:menuOut code='subMenuAnalysis_ResearchTool_StockScreenerView' classValue='ui-tabs-selected'/>>
		<a
			href="<web:url value='/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml'/>">
			<label class="icon_active"></label> <s:text
				name="leftmenu.label.analysisTool.Refine.StockScreener" /> </a>
	</li>
	<li
		<web:menuOut code='subMenuAnalysis_ResearchTool_StockWizardView' classValue='ui-tabs-selected'/>>
		<a
			href="<web:url value='/cong-cu-phan-tich-chung-khoan/stock-wizard.shtml'/>">
			<label class="icon_active"></label> <s:text
				name="leftmenu.label.analysisTool.Refine.StockWizard" /> </a>
	</li>
	<li
		<web:menuOut code='subMenuAnalysis_ResearchTool_FlashChart' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/cong-cu-phan-tich-chung-khoan/bieu-do-ky-thuat.shtml'/>">
			<label class="icon_active"></label> <s:text
				name="leftmenu.label.analysisTool.Technical.Charts" /> </a>
	</li>
</ul>

<div class="clear"></div>


