<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<s:set var="btnClassification" value="true" scope="action"></s:set>
<s:set var="btnIndex" value="true" scope="action"></s:set>
<s:set var="btnOverview" value="false" scope="action"></s:set>
<script type="text/javascript">
<!--
	var sectorCode = '<s:property value="sector.values.iterator().next().sectorCode"/>';
	var COMP_RECORD_INFO = '<s:text name="web.label.analysis.sector.overview.paging.title"></s:text><b><s:property value="sector.values.iterator().next().sectorName"/></b>'
//-->
</script>
<tiles:insertDefinition name="Analysis.FundamentalityTmpl">
	<tiles:putAttribute name="MainContent">

		<div class="content_phantichnganh">
			<div class="box_left">
				<h1 class="title_tongquang_ttn">
					<s:property value="sector.values.iterator().next().sectorName" />
				</h1>
				<p class="tongquang_ttn_ct">
					<s:text name="web.label.analysis.sector.overview.composite.value"></s:text>
					:
					<strong> <s:if
							test='sector.get("1000017").numericValue != null'>
							<s:push value='sector.get("1000017").numericValue'>
								<s:text name="format.double" var="num">
									<s:param name="value" value="[0].top" />
								</s:text>
								<s:property value='#num' />
							</s:push>
						</s:if> <s:else>
	                       -
	                   </s:else> </strong>
					<s:text name="web.label.analysis.sector.overview.today.change"></s:text>
					:
					<span class="green"> <s:if
							test='sector.get("1000028").numericValue != null'>
							<b
								class="<s:property value='sector.get("1000028").numericValue < 0 ? "vl_today cl_red" : "vl_today"'/>">
								<s:text name="format.double">
									<s:param name="value"
										value='sector.get("1000032").numericValue' />
								</s:text> <s:push value='sector.get("1000028").numericValue * 100'>
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
				<input type="hidden" id="sectorDetail-sectorCode"
					value="pm=3&idxCode=<s:property value='sector.values.iterator().next().sectorCode'/>" />
				<input type="hidden" id="sectorDetail-sectorName"
					value="<s:property
                            value='sector.values.iterator().next().sectorName' />" />
				<p style="margin-top: 35px;"></p>
				<div id="divSectorIndustryChart" class="left"></div>
				<p style="margin-bottom: 35px;"></p>

				<div class="clear"></div>
				<div class="hd">
					<div class="hd-center">
						<div class="heading_pr">
							<div class="rownganh">
								<strong><s:text
										name="web.label.analysis.sector.overview.today.industries.highlight"></s:text>
								</strong>
							</div>
							<div class="line">
								|
							</div>
							<div class="rowcs">
								<strong><s:text
										name="web.label.analysis.sector.overview.index"></s:text>
								</strong>
							</div>
							<div class="line">
								|
							</div>
							<div class="rowtoday">
								<strong><s:text
										name="web.label.analysis.sector.overview.today.change"></s:text>
								</strong>
							</div>
							<div class="line">
								|
							</div>
							<div class="rowm">
								<strong><s:text
										name="web.label.analysis.sector.overview.5days"></s:text>
								</strong>
							</div>
							<div class="line">
								|
							</div>
						</div>
					</div>
				</div>
				<ul class="list_tt">

					<s:iterator value="industriesHighlight" var="industry">
						<li>
							<div class="rownganh">
								<a
									href='<web:url value="/phan-tich-nganh/chi-tiet-nganh.shtml"/>?sectorCode=<s:property value="sector.values.iterator().next().sectorCode"/>&industryCode=<s:property value="#industry.values.iterator().next().industryCode"/>'>
									<s:property
										value="#industry.values.iterator().next().industryName" /> </a>
							</div>

							<s:if test="#industry.get('1000017').numericValue != null">
								<s:push value="#industry.get('1000017').numericValue">
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

							<s:if test="#industry.get('1000028').numericValue != null">
								<s:push value="#industry.get('1000028').numericValue * 100">
									<div
										class="rowtoday <s:property value='[0].top < 0 ? "vl_3month cl_red" : "vl_3month"'/>">
										<s:text name="format.double" var="num">
											<s:param name="value" value="[0].top" />
										</s:text>
										<s:property value='[0].top > 0 ? "+" + #num : #num' />
										%
									</div>
								</s:push>
							</s:if>
							<s:else>
								<div class="rowtoday" align="center">
									-
								</div>
							</s:else>

							<s:if test="#industry.get('1000029').numericValue != null">
								<s:push value="#industry.get('1000029').numericValue * 100">
									<div
										class="rowm-1 <s:property value='[0].top < 0 ? "vl_6month cl_red" : "vl_6month"'/>">
										<s:text name="format.double" var="num">
											<s:param name="value" value="[0].top" />
										</s:text>
										<s:property value='[0].top > 0 ? "+" + #num : #num' />
										%
									</div>
								</s:push>
							</s:if>
							<s:else>
								<div class="rowm-1" align="center">
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
			<div class="titleBar bgTitleBar text-upper">
				<span id="margin-title"><s:text
						name="web.label.analysis.sector.overview.subcategories"></s:text>
				</span>
			</div>

			<ul class="danhsanhnganh">
				<s:iterator value="subIndustries" var="industries">
					<s:iterator value="#industries">
						<li>
							<a
								href="<web:url value="/phan-tich-nganh/chi-so-nganh.shtml"/>?sectorCode=<s:property value="sectorCode"/>&industryCode=<s:property value="industryCode"/>">
								<s:property value="industryName" /> </a>
						</li>
					</s:iterator>
				</s:iterator>
			</ul>
			<div class="clear"></div>
			<%--
              <div class="padding0px">
                  <div class="ct_Subcategories clearfix">
                      <table cellpadding="0" cellspacing="0" border="0" width="100%">
                          <tr>
                              <s:iterator value="subIndustries" var="industries">
                                  <td width="22%" valign="top">
                                      <s:iterator value="#industries">
                                          <p>
                                              <a href="<web:url value="/phan-tich-nganh/chi-so-nganh.shtml"/>?sectorCode=<s:property value="sectorCode"/>&industryCode=<s:property value="industryCode"/>" class="link_rightcl">
                                                  <s:property value="industryName"/>
                                              </a>
                                          </p>
                                      </s:iterator>
                                  </td>
                              </s:iterator>
                          </tr>
                      </table>
                  </div>
              </div>
              --%>
			<div class="clear"></div>
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
												name="web.label.company"></s:text>
									</b> </a>
									<img src='<s:url value="/images/s.gif"/>'>
								</div>
							</td>
							<td class="rowb">
								<div class="header txtText">
									<b><s:text
											name="web.label.analysis.sector.overview.last.trade"></s:text>
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
