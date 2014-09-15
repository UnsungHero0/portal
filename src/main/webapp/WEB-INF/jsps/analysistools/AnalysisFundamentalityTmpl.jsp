<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<!-- ############### BEGIN: MAIN CONTENT ##################### -->

<div id="content_ttpt">

	<ul class="ui-tabs-nav tab_ttpt" id="tab_menu_phantichnganh">
		<li
			<web:menuOut code='subMenuAnalysis_IndustryAnalysis_SectorOverview' classValue='ui-tabs-selected'/>>
			<a
				href="<web:url value='/phan-tich-nganh/tong-quan-nganh.shtml'/>"><label
					class="icon_active"></label> <s:text
					name="leftmenu.label.analysisTool.Sector.Overview"></s:text> </a>
		</li>
		<li
			<web:menuOut code='subMenuAnalysis_IndustryAnalysis_ListSectorView' classValue='ui-tabs-selected'/>>
			<a
				href="<web:url value='/phan-tich-nganh/chi-so-nganh.shtml'/>"><label
					class="icon_active"></label> <s:text
					name="leftmenu.label.analysisTool.Sector.Index"></s:text> </a>
		</li>
		<li
			<web:menuOut code='subMenuAnalysis_IndustryAnalysis_ListSectorIndex' classValue='ui-tabs-selected'/>>
			<a
				href="<web:url value='/phan-tich-nganh/phan-loai-nganh.shtml'/>"><label
					class="icon_active"></label> <s:text
					name="leftmenu.label.analysisTool.Sector.Classification"></s:text>
			</a>
		</li>
		<%--<li <web:menuOut code='subMenuAnalysis_IndustryAnalysis_' classValue='ui-tabs-selected'/>><a href="<web:url value='/phan-tich-nganh/phan-loai-nganh.shtml'/>">So sánh</a></li>--%>
	</ul>

	<div class="clear"></div>

	<div class="content_small">
		<div id="tab-1" class="content_tab ui-tabs-container ui-tabs-hide"
			style="display: block;">
			<s:if test="%{sectors != null}">
				<div class="box_tk_mck">
					<s:actionerror theme="simple" cssStyle="color:red;"/>
					<form method="get" id="IndustryDetails"
						action="<web:url value="/phan-tich-nganh/chi-tiet-nganh.shtml"/>">
						<script>
						$().ready(function() {
							$('#IndustryDetails').bind("submit", function() {
								$('#symbol').val($('#symbol').val().toUpperCase());
							});
							$.web_autocomplete.symbols('symbol', URL_SYMBOL_AUTO_SUGGESTION, {
								width : 310,
								callbackResult : function(e, item) {
									$('#IndustryDetails').submit();
								}
							});
						});
						</script>

						<s:text name="web.label.analysis.sector.overview.search.for"></s:text>
						<s:bean name="java.util.ArrayList" var="items">
							<s:iterator value="sectors" var="sector">
								<s:set
									value="#items.add(new org.apache.commons.collections.keyvalue.DefaultKeyValue(#sector.values.iterator().next().sectorCode, #sector.values.iterator().next().sectorName))"></s:set>
							</s:iterator>
						</s:bean>
						<s:select id="sectorCode" name="sectorCode" list="#items"
							headerKey="" headerValue="..." listKey="key" listValue="value"
							cssStyle="height: 22px; margin-left: 10px; margin-right: 40px; width: 200px;">
						</s:select>

						<!-- ------------------------------------------------------------------------------------- -->
						<s:text name="web.label.analysis.sector.overview.search.by.symbol"></s:text>
						<s:textfield name="symbol" id="symbol"
							cssStyle="height: 22px;margin-left: 10px;margin-right: 10px;width: 145px;">
						</s:textfield>
						<!--<span class="button_long"> <input id="search" type="submit"
								value="<s:text name="button.search" />"> </span>
						-->
						<input type="submit" class="iButton" id="search"
							value='<s:text name="web.label.list.sector.ViewIndustry"/>'>
					</form>
				</div>
			</s:if>

			<div class="clearfix"></div>
			<!---->
			<tiles:insertAttribute name="MainContent" ignore="true"></tiles:insertAttribute>
		</div>
	</div>
</div>
<!-- ############### END: MAIN CONTENT ##################### -->

