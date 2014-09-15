<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://www.webapp.com.vn/tags-fmt" prefix="webFmt"%>


<script type="text/javascript">
$(function() {
	FROM_MODULE = URL_FORECAST;
	
	var symbol = $("#symbolId").val();
	$("#linerChartId").attr("src",
			$.web_resource.urlViewChart() + symbol + "&w=217&h=146&pm=-3");
});
function doViewIncomeForecast() {
	var searchDate = $('#selectSearchDate').val();
	$('#searchDate').val(searchDate);
	document.fIncomeStatementForecast.submit(); 
	
}
</script>

<div class="content_ttpt">
	<form name="fIncomeStatementForecast" id="fIncomeStatementForecast"
		action='<s:url value="/chi-tieu-ke-hoach/%{model.symbol.toLowerCase()}.shtml"/>' method="post">
		<input type="hidden" id="symbolId" name="symbol"
			value="<s:property value="model.symbol"/>" />
		<input type="hidden" name="cacheData"
			value="<s:property value="model.cacheData"/>" />
		<input type="hidden" name="searchDate" value="<s:property value="model.searchDate"/>" id="searchDate"/>
		<!-- nav -->
		<jsp:include
			page="/WEB-INF/jsps/listedmarket/snippet/si-company-nav.jsp"></jsp:include>

		<div class="content_small">
			<div class="content_tab"
				style="margin: 15px 0;">
				<table width="100%" class="tb3" border="1" bordercolor="#D5D8E1"
					cellspacing="0" cellpadding="0" style="border-collapse: collapse;"
					align="left">
					<colgroup>
						<col width="23%" />
						<col width="17%" />
						<col width="17%" />
						<col width="17%" />
						<col width="13%" />
						<col width="13%" />
					</colgroup>
					<tr>
						<th colspan="4">
							<s:text
									name="web.label.IncomeStatementForecastAction.form.CellNames.Forecast" />
								<s:property value="model.symbol" /> <s:text
									name="web.label.IncomeStatementForecastAction.form.CellNames.FiscalDate" />
							

							<select name="ifoEstimateView.searchDate" style="height: 22px;" id="selectSearchDate"
								onchange="javascript:doViewIncomeForecast()">
								<c:forEach var="item" items="${model.listYear}">
									<c:choose>
										<c:when test="${item == model.ifoEstimateView.searchDate}">
											<option value="<c:out value="${item}" />" selected="selected">
												<c:out value="${item}" />
											</option>
										</c:when>
										<c:otherwise>
											<option value="<c:out value="${item}" />">
												<c:out value="${item}" />
											</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>


						</th>
						<th colspan="2">
							<s:text
									name="web.label.IncomeStatementForecastAction.form.CellNames.FiscalCompletePercentage" />
							
						</th>
					</tr>
					<tr>
						<td style="text-align: center">
							<b><s:text
									name="web.label.IncomeStatementForecastAction.form.CellNames.Norm" />
							</b>
						</td>
						<td style="text-align: center">
							<b><s:text
									name="web.label.IncomeStatementForecastAction.form.CellNames.CompanyNorm" />
							</b>
						</td>
						<td style="text-align: center">
							<b><s:text
									name="web.label.IncomeStatementForecastAction.form.CellNames.ThirdPartyNorm" />
							</b>
						</td>
						<td style="text-align: center">
							<b><s:text
									name="web.label.IncomeStatementForecastAction.form.CellNames.RealNorm" />
							</b>
						</td>
						<td style="text-align: center">
							<b><s:text
									name="web.label.IncomeStatementForecastAction.form.CellNames.CompanyPercentage" />
							</b>
						</td>
						<td style="text-align: center">
							<b><s:text
									name="web.label.IncomeStatementForecastAction.form.CellNames.ThirdPartyPercentage" />
							</b>
						</td>
					</tr>
					<tr>
						<th>
							<s:property value="model.ifoEstimateView.label21001" />
						</th>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.item21001 > 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.item21001}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.l_analyst_21001 > 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.l_analyst_21001}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>

						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.l_actual_21001 > 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.l_actual_21001}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.pct_result_21001 > 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.pct_result_21001}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when
									test="${model.ifoEstimateView.analyst_pct_result_21001 > 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out
											value="${model.ifoEstimateView.analyst_pct_result_21001}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>


					</tr>
					<tr>
						<th>
							<c:out value="${model.ifoEstimateView.label23800}"
								escapeXml="false" />
						</th>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.item23800 != 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.item23800}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.l_analyst_23800 != 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.l_analyst_23800}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.l_actual_23800 != 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.l_actual_23800}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.pct_result_23800 != 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.pct_result_23800}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when
									test="${model.ifoEstimateView.analyst_pct_result_23800 != 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out
											value="${model.ifoEstimateView.analyst_pct_result_23800}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>

					</tr>
					<tr>
						<th>
							<c:out value="${model.ifoEstimateView.label23000}"
								escapeXml="false" />
						</th>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.item23000 != 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.item23000}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.l_analyst_23000 != 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.l_analyst_23000}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>

						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.l_actual_23000 != 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.l_actual_23000}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.pct_result_23000 != 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.pct_result_23000}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when
									test="${model.ifoEstimateView.analyst_pct_result_23000 != 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out
											value="${model.ifoEstimateView.analyst_pct_result_23000}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th>
							<c:out value="${model.ifoEstimateView.label400001}"
								escapeXml="false" />
						</th>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.item400001 > 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.item400001}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.l_analyst_400001 > 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.l_analyst_400001}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>

						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.l_actual_400001 > 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.l_actual_400001}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.pct_result_400001 > 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.pct_result_400001}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when
									test="${model.ifoEstimateView.analyst_pct_result_400001 > 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out
											value="${model.ifoEstimateView.analyst_pct_result_400001}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>

					</tr>
					<tr>
						<th>
							<c:out value="${model.ifoEstimateView.label40000}"
								escapeXml="false" />
						</th>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.item40000 > 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.item40000}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.l_analyst_40000 > 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.l_analyst_40000}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>

						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							<c:out value="${model.ifoEstimateView.label40001}"
								escapeXml="false" />
						</th>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.item40001 > 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.item40001}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.l_analyst_40001 > 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.l_analyst_40001}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>

						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							<c:out value="${model.ifoEstimateView.label51021}"
								escapeXml="false" />
						</th>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.item51021 > 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.item51021}"
											escapeXml="false" />
									</fmt:formatNumber>%
                                        </c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>
						<td align="right">
							&nbsp;
							<c:choose>
								<c:when test="${model.ifoEstimateView.l_analyst_51021 > 0}">
									<fmt:formatNumber pattern="###,###,###,##0.##">
										<c:out value="${model.ifoEstimateView.l_analyst_51021}"
											escapeXml="false" />
									</fmt:formatNumber>
								</c:when>
								<c:otherwise>
                                            N/A
                                        </c:otherwise>
							</c:choose>
						</td>

						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
				<div class="clear"></div>
			</div>
		</div>
	</form>
</div>