<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.vndirect.com.vn/online-brokerage/utility" prefix="vndirectUtil" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>


<div id="content_ttpt" >
	<ul class="ui-tabs-nav tab_ttpt">
		<li
			<web:menuOut code='subMenuAnalysis_ResearchTool_StockScreenerView' classValue='ui-tabs-selected'/>>
			<a href="<web:url value='/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml'/>">
				<label class="icon_active"></label><s:text name="leftmenu.label.analysisTool.Refine.StockScreener"/>
			</a>
		</li>
		<li
			<web:menuOut code='subMenuAnalysis_ResearchTool_StockWizardView' classValue='ui-tabs-selected'/>>
			<a href="<web:url value='/cong-cu-phan-tich-chung-khoan/stock-wizard.shtml'/>">
				<label class="icon_active"></label><s:text name="leftmenu.label.analysisTool.Refine.StockWizard"/>
			</a>
		</li>
		<li
			<web:menuOut code='subMenuAnalysis_ResearchTool_PowerRatingView' classValue='ui-tabs-selected'/>>
			<a href="<web:url value='/y-tuong-dau-tu/power-ratings.shtml'/>">
			    <label class="icon_active"></label><s:text name="leftmenu.label.analysisTool.Refine.PowerRating"/>
			</a>
		</li>
		<li
			<web:menuOut code='subMenuAnalysis_ResearchTool_FlashChart' classValue='ui-tabs-selected'/>>
			<a href="<web:url value='/cong-cu-phan-tich-chung-khoan/bieu-do-ky-thuat.shtml'/>">
			    <label class="icon_active"></label><s:text name="leftmenu.label.analysisTool.Technical.Charts"/>
			</a>
		</li>
	</ul>           
        
    <div class="clear"></div>   
		
	<div class="content_small">
        <!---->
        <div id="tab-1" class="content_tab ui-tabs-container ui-tabs-hide" style="display: block;">
        
        <tiles:insertAttribute name="ButtonNavigator" ignore="true"></tiles:insertAttribute>
	    <tiles:insertAttribute name="MainContent"></tiles:insertAttribute>
	    
	    </div>
        <!---->
	</div>
</div>