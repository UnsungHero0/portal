<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<s:set var="btnClassification" value="true" scope="action"></s:set>
<s:set var="btnIndex" value="true" scope="action"></s:set>
<s:set var="btnOverview" value="false" scope="action"></s:set>

<script type="text/javascript">
<!--
	var industryCode = '<s:property value="industry.values.iterator().next().industryCode"/>';
	var sectorCode = '<s:property value="sector.values.iterator().next().sectorCode"/>';
	var COMP_RECORD_INFO = '<s:text name="web.label.analysis.sector.overview.paging.title"></s:text><b><s:property value="industry.values.iterator().next().industryName"/></b>';
//-->
</script>
<tiles:insertDefinition name="Analysis.FundamentalityTmpl">
	<tiles:putAttribute name="MainContent">
		<s:actionerror theme="simple" escape="false" />

		<div class="content_phantichnganh">
			<div class="box_left">
				<p class="title_tongquang_ttn">
					 <a
						href="<web:url value="/phan-tich-nganh/chi-tiet-linh-vuc.shtml"/>?sectorCode=<s:property value="sector.values.iterator().next().sectorCode"/>"
						id="sector"> <b class="bluetext"> <s:property
									value="sector.values.iterator().next().sectorName" /> </b> </a> &gt; <s:property
							value="industry.values.iterator().next().industryName" /> 
				</p>

				<p class="tongquang_ttn_ct">
					<span class="fl"> <s:text
							name="web.label.analysis.sector.overview.composite.value"></s:text>:
						<b style="font-size: 15px; line-height: 20px"> <s:if
								test="industry.get('1000017').numericValue != null">
								<s:push value='industry.get("1000017").numericValue'>
									<s:text name="format.double" var="num">
										<s:param name="value" value="[0].top" />
									</s:text>
									<s:property value='#num' />
								</s:push>
							</s:if> <s:else>
                                  -
                              </s:else> </b> &nbsp; <s:text
							name="web.label.analysis.sector.overview.today.change"></s:text>:
						<s:if test='industry.get("1000028").numericValue != null'>
							<b
								class="<s:property value='industry.get("1000028").numericValue < 0 ? "vl_today cl_red" : "vl_today"'/>">
								<s:text name="format.double">
									<s:param name="value"
										value='industry.get("1000032").numericValue' />
								</s:text> <s:push value='industry.get("1000028").numericValue * 100'>
									<s:text name="format.double" var="num">
										<s:param name="value" value="[0].top" />
									</s:text>
                                      (<s:property
										value='[0].top > 0 ? "+" + #num : #num' />%)
                                  </s:push> </b>
						</s:if> <s:else>
                              -
                          </s:else> </span>
				</p>
				<input type="hidden" id="industryDetail-industryCode"
					value="pm=3&idxCode=<s:property value="industry.values.iterator().next().industryCode"/>" />
				<input type="hidden" id="industryDetail-industryName"
                    value="<s:property
                            value='industry.values.iterator().next().industryName' />" />
				<p style="margin-top: 35px;"></p>
				<div id="divSectorIndustryChart"></div>
				<p style="margin-bottom: 35px;"></p>

				<div class="clear"></div>
				<div class="hd">
					<div class="hd-center">
						<div class="heading_pr">
							<div class="rownganh">
								<strong><s:text
										name="web.label.analysis.sector.overview.top.company.gainer">Top Company Gainer's</s:text>
								</strong>
							</div>
							<div class="line">
								|
							</div>
							<div class="rowcs">
								<strong><s:text
										name="web.label.analysis.sector.overview.last">Last</s:text>
								</strong>
							</div>
							<div class="line">
								|
							</div>
							<div class="rowtoday">
								<strong><s:text
										name="web.label.analysis.sector.overview.today.change">Today's Change</s:text>
								</strong>
							</div>
							<div class="line">
								|
							</div>
							<div class="rowm">
								<strong><s:text
										name="web.label.analysis.sector.overview.volume">Volume</s:text>
								</strong>
							</div>
							<div class="line">
								|
							</div>
						</div>
					</div>
				</div>
				<ul class="list_tt">
					<s:iterator value="companies">
						<li>
							<div class="rownganh">
								<a
									href="<web:url value='/tong-quan/'/><s:property value="code"/>.shtml">
									<s:property value="code" /> </a>
							</div>

							<s:if test="closePrice != null">
								<s:push value="closePrice">
									<div class="rowcs" style="font-weight: bold;" align="right">
										<s:text name="format.double" var="num">
											<s:param name="value" value="[0].top" />
										</s:text>
										<s:property value='#num' />
									</div>
								</s:push>
							</s:if>
							<s:else>
								<div class="rowcs">
									-
								</div>
							</s:else>

							<s:if test="pctIndex != null">
								<s:push value="pctIndex">
									<div
										class="<s:property value='[0].top < 0 ? "vl_3month cl_red" : "vl_3month"'/> rowtoday">
										<s:text name="format.double" var="num">
											<s:param name="value" value="[0].top" />
										</s:text>
										<s:property value='[0].top > 0 ? "+" + #num : #num' />
										%
									</div>
								</s:push>
							</s:if>
							<s:else>
								<div class="rowtoday">
									-
								</div>
							</s:else>

							<s:if test="volumeIndex != null">
								<s:push value="volumeIndex">
									<div class="rowm-1">
										<s:text name="format.double" var="num">
											<s:param name="value" value="[0].top" />
										</s:text>
										<s:property value='#num' />
									</div>
								</s:push>
							</s:if>
							<s:else>
								<div class="rowm-1">
									-
								</div>
							</s:else>
						</li>
					</s:iterator>
				</ul>
			</div>

			<div class="width340" id="c-column">
				<div class="box_listnew">
					<div class="titleBar bgTitleBar text-upper">
						<span id="margin-title"><s:text
								name="web.label.analysisTool.Business.Information.News"></s:text>
						</span>
					</div>
					<div class="content_small">
						<div id="divSectorNews" class="ds_news businessInformation">

						</div>
						<div class="right" id="tab1" style="margin-bottom: 10px;">

						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="content_phantichnganh padding-bottom10 padding-top10">
			<div id="compRecordInfo" style="margin-top: 20px;"></div>
			<div id="company" class="table_company clearfix">
				<table cellpadding="0" cellspacing="0" class="listcompany"
					border="0" width="974px;">
					<thead>
						<tr class="title">
							<td class="rowa">
								<div class="header txtText">
									<a href="javascript:void(0)"
										class="sortBy {field : 'COMPANY_NAME'}"> <b><s:text
												name="web.label.company">Company</s:text>
									</b> </a>
									<img src='<s:url value="/images/s.gif"/>'>
								</div>
							</td>
							<td class="rowb">
								<div class="header txtText">
									<b><s:text
											name="web.label.analysis.sector.overview.last.trade">Last Trade</s:text>
									</b>
								</div>
							</td>
							<td class="rowc">
								<div class="header txtText">
									<b><s:text name="web.label.analysis.sector.overview.change"></s:text>
										+/- (%)</b>
								</div>
							</td>
							<td class="rowc">
								<div class="header txtText">
									<a href="javascript:void(0)"
										class="sortBy {field : 'MARKETCAP'}"> <b><s:text
												name="web.label.analysis.sector.overview.market.cap"></s:text>
									</b> </a>
									<img src='<s:url value="/images/s.gif"/>'>
								</div>
							</td>
							<td class="rowc">
								<div class="header txtText">
									<a href="javascript:void(0)" class="sortBy {field : 'REVENUE'}">
										<b><s:text
												name="web.label.analysis.sector.overview.revenue">Ann. Revenue</s:text>
											(%)</b> </a>
									<img src='<s:url value="/images/s.gif"/>'>
								</div>
							</td>
							<td class="rowc">
								<div class="header txtText">
									<a href="javascript:void(0)" class="sortBy {field : 'INCOME'}">
										<b><s:text
												name="web.label.analysis.sector.overview.income">Ann.Income</s:text>
									</b> </a>
									<img src='<s:url value="/images/s.gif"/>'>
								</div>
							</td>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<div class="fpCalendar">
					<div class="fpCalendar-left"></div>
					<div class="fpCalendar-right"></div>
					<div align="right" class="fpCalendar-center" id="companyNav"></div>
				</div>
			</div>
		</div>
		<!---->
	</tiles:putAttribute>
</tiles:insertDefinition>
