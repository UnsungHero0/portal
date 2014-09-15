<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<ul class="ui-tabs-nav tab_ttpt">
	<li
		<web:menuOut code='subMenuAnalysis_ResearchTool_StockPick' classValue='ui-tabs-selected'/>>
		<a
		href="<web:url value='/y-tuong-dau-tu/co-phieu-khuyen-nghi.shtml'/>">
			<label class="icon_active"></label> <s:text name="label.stockpick">Cổ phiếu khuyến nghị</s:text>
	</a>
	</li>
	<li
		<web:menuOut code='subMenuAnalysis_ResearchTool_PowerRatingView' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/y-tuong-dau-tu/power-ratings.shtml'/>">
			<label class="icon_active"></label> Power Rating
	</a>
	</li>
</ul>

<div class="clear"></div>