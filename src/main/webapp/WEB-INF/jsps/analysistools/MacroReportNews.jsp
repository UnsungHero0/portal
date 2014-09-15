<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<s:set var="commentaryNews" value="true" scope="action"></s:set>
<s:set var="brokerNews" value="true" scope="action"></s:set>
<s:set var="expertNews" value="true" scope="action"></s:set>
<s:set var="macroReport" value="false" scope="action"></s:set>

<tiles:insertDefinition name="Analysis.ExpertIdeaTmpl">
	<tiles:putAttribute name="MainContent">
		<div style="color: #F00; display: none;font-weight: bold" id="recordNotFound"><s:text name="commons.label.record.not.found"></s:text>!</div>
		<!--
		<div class="Tbl-Search-Sector clearfix">
			<table cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td align="left">
						<s:text name="web.label.analysisTool.macro.report.symbol"></s:text>
					</td>
					<td style="padding: 0 5px">
						<s:textfield name="symbol" theme="simple" id="symbol" cssStyle="color: #999999; font-size: 11px; padding: 2px;" size="25"/>
					</td>
					<td align="left" style="padding: 0">
						<span class="btn_left_inbox"> 
							<span class="btn_right_inbox"> 
								<span class="btn_center_inbox">
									<input id="search" type="button" value="<s:text name="button.search"></s:text>">
								</span>
							</span>
						</span>
					</td>
				</tr>
			</table>
		</div>
		-->
		<!---->
		<div class="clearfix" style="padding: 5px">
			<!---->
			<div class="table_company clearfix" style="padding-top: 5px" id="macroReportNews">
				<p style="margin: 0 0 10px 0; color: #000">
					<s:text name="web.label.analysisTool.macro.report.title"></s:text>
				</p>
				<table cellpadding="0" cellspacing="0" border="0" width="742"
					style="border-top: 1px solid #B0B0B0">
					<thead>
						<tr
							style="background: url(images/img/imgbgr_firstline.gif) repeat-x left bottom">
							<td width="80">
								<span class="txtText">
									<b><s:text name="web.label.date"></s:text> </b>
								</span>
							</td>
							<td align="left">
								<span class="txtText">
									<b class="bluetext"><s:text name="web.label.analysisTool.macro.report"></s:text></b>
								</span>
							</td>
							<td width="140">
								<span class="txtText">
									<b><s:text name="web.label.analysisTool.macro.report.source"></s:text> </b>
								</span>
							</td>
							<td width="100" align="center">
								<span class="txtText">
									<b><s:text name="web.label.analysisTool.macro.report.language"></s:text> </b>
								</span>
							</td>
							<td width="55" align="center" class="col_end">
								<span class="txtText">
									<b>File</b>
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<div class="fpCalendar">
					<div class="fpCalendar-left"></div>
					<div class="fpCalendar-right"></div>
					<div align="right" class="fpCalendar-center" id="macroReportNewsNav"></div>
				</div>
			</div>
		</div>
		<!---->
	</tiles:putAttribute>
</tiles:insertDefinition>