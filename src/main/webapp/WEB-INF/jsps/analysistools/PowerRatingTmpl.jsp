<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.vndirect.com.vn/online-brokerage/utility" prefix="vndirectUtil" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>

<div class="content_ttpt" >
	    <ul class="ui-tabs-nav tab_ttpt">                               
	       <li <web:menuOut code='subMenuAnalysis_ResearchTool_StockScreenerView' classValue='ui-tabs-selected'/>><a href="<web:url value='/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml'/>">Sàng lọc cổ phiếu</a></li>
	       <li <web:menuOut code='subMenuAnalysis_ResearchTool_StockWizardView' classValue='ui-tabs-selected'/>><a href="<web:url value='/cong-cu-phan-tich-chung-khoan/stock-wizard.shtml'/>">Stock wizard</a></li>
	       <li <web:menuOut code='subMenuAnalysis_ResearchTool_PowerRatingView' classValue='ui-tabs-selected'/>><a href="<web:url value='/y-tuong-dau-tu/power-ratings.shtml'/>">Power Rating</a></li>
	       <li <web:menuOut code='subMenuAnalysis_ResearchTool_FlashChart' classValue='ui-tabs-selected'/>><a href="<web:url value='/cong-cu-phan-tich-chung-khoan/bieu-do-ky-thuat.shtml'/>">Biểu đồ kỹ thuật</a></li>
	    </ul>         
	    
	    <div class="clear"></div>
	       	    	      
		<div class="content_small">
			<div class="top_inner clearfix">
				<div class="right fr"></div>
			</div>
			<div class="center_inner clearfix">
				<!---->
				<table cellpadding="0" cellspacing="0" border="0" width="100%"
					height="54"
					style="background: url(../images/img/inbox_small_tbl_01.gif) repeat-x; padding: 0px 11px;">
					<tr>
						<td>
						
								<span class="btn_left_SQ" id="descFirstSpan"> <span class="btn_right_SQ" id="descSecondSpan">
										<span class="btn_center_SQ" id="descThirdSpan"> <input type="button" onclick="changeDescriptionContent(0)" value="<s:text name="web.label.PowerRating.Button.PowerRatingDef"/>" />
									</span> </span> </span>

							
								<span class="btn_left_inbox" id="advFirstSpan"> <span
									class="btn_right_inbox" id="advSecondSpan"> <span class="btn_center_inbox" id="advThirdSpan">
											<input type="button" onclick="changeDescriptionContent(1)" value="<s:text name="web.label.PowerRating.Button.PowerRatingAdvantage"/>" />
									</span> </span> </span>
							

							<!-- button 3 -->

							
								<span class="btn_left_inbox" id="userFirstSpan"> <span
									class="btn_right_inbox" id="userSecondSpan"> <span class="btn_center_inbox" id="userThirdSpan">
											<input type="button" onclick="changeDescriptionContent(2)" value="<s:text name="web.label.PowerRating.Button.PowerRatingUser"/>" />
									</span> </span> </span>
							
							<!-- button 4 -->

						
								<span class="btn_left_inbox" id="menuFirstSpan"> <span
									class="btn_right_inbox" id="menuSecondSpan"> <span class="btn_center_inbox" id="menuThirdSpan">
											<input type="button" onclick="changeDescriptionContent(3)" value="<s:text name="web.label.PowerRating.Button.PowerRatingMenu"/>" />
									</span> </span> </span>
							
						</td>
					</tr>
				</table>
				<div class="Tbl-Search-Sector clearfix" id="prDefinitionContent" style="display: block">
					 
					<web:productSubject var="objProdSub" productCode="PRS_DES"/>
					<c:out value="${objProdSub['product'].productOverview}" escapeXml="false"/>
				</div>
				<div class="Tbl-Search-Sector clearfix" id="prAdvantageContent" style="display: none">
					
					<web:productSubject var="objProdSub" productCode="PRS_ADVANTAGE"/>
					<c:out value="${objProdSub['product'].productOverview}" escapeXml="false"/>
				</div>
				<div class="Tbl-Search-Sector clearfix" id="prUserContent" style="display: none">
					
					<web:productSubject var="objProdSub" productCode="PRS_USE"/>
					<c:out value="${objProdSub['product'].productOverview}" escapeXml="false"/>
				</div>
				<div class="Tbl-Search-Sector clearfix" id="prMenuContent" style="display: none">
					
					<web:productSubject var="objProdSub" productCode="PRS_MENU"/>
					<c:out value="${objProdSub['product'].productOverview}" escapeXml="false"/> 
				</div>
				<tiles:insertAttribute name="MainContent"></tiles:insertAttribute>
				<!---->
			</div>
			<div class="bottom_inner clearfix">
				<div class="left fl"></div>
				<div class="right fr"></div>
			</div>
		</div>
</div>