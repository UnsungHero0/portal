<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="vndirect"%>

<input type="hidden" id="fCompanyEvents_pagingInfo_indexPage" value="1" />

<div id="content_ttpt">
	<!-- nav -->
	<jsp:include
		page="/WEB-INF/jsps/listedmarket/snippet/ir-statistic-nav.jsp"></jsp:include>

	<div class="content_small">
		<div class="content_tab">
			<div class="box_thongkecoban">
				<!-- left -->
				<div class="box_left">
					<div class="tab_new">
						<div>
							<h2>
								<span><s:text
										name="web.label.KeyStatisticAction.form.ValidationMeasure">CHỈ TIÊU TÀI CHÍNH</s:text>
								</span>
							</h2>
						</div>
					</div>
					<!-- Chỉ số định giá -->
					<ul class="listct clearfix">
						<c:forEach var="valuationItem"
							items="${model.ifoValuationMeasuresViewList}" varStatus="status">
							<c:choose>
								<c:when test="${valuationItem.id.displayLevel == 0}">
									<li class="title">
										<span>${valuationItem.id.itemName}</span>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<div class="rowa">
											${valuationItem.id.itemName}
										</div>
										<div class="rowb">
											<c:if test="${valuationItem.id.numericValue != null}">
												<fmt:formatNumber pattern="###,##0.##">
													<c:out value="${valuationItem.id.numericValue}"
														escapeXml="false" />
												</fmt:formatNumber>
											</c:if>
										</div>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
					<!-- End Chỉ số định giá -->

					<div class="clear"></div>
					<div class="tab_new">
						<div>
							<h2>
								<span><s:text
										name="web.label.KeyStatisticAction.form.TradingInfo">THÔNG TIN QUYỀN CỔ ĐÔNG</s:text>
								</span>
							</h2>
						</div>
					</div>

					<table cellpadding="0" cellspacing="0" class="ttbctaichinh">
						<tr class="title">
							<td>
								<strong><s:text
										name="web.label.KeyStatisticAction.form.TradingInfo.CellNames.Xdate">Ngày chốt quyền</s:text>
								</strong>
							</td>
							<td>
								<strong><s:text
										name="web.label.KeyStatisticAction.form.TradingInfo.CellNames.Ratio">Tỷ lệ</s:text>
								</strong>
							</td>
							<td>
								<strong><s:text
										name="web.label.KeyStatisticAction.form.TradingInfo.CellNames.Type">Kiểu</s:text>
								</strong>
							</td>
						</tr>
						<c:forEach var="investorRigtItem"
							items="${model.ifoInvestorRightsViewList}" varStatus="status">
							<tr>
								<td>
									${investorRigtItem.id.XDateStr}
								</td>
								<td>
									${investorRigtItem.id.ratio}
								</td>
								<td>
									${investorRigtItem.id.sharesActionDesc}
								</td>
							</tr>
						</c:forEach>
					</table>
					<div class="clear"></div>
					<div class="tab_new">
						<div>
							<h2>
								<span><s:text
										name="web.label.KeyStatisticAction.form.FinancialHighlights">BÁO CÁO TÀI CHÍNH TÓM TẮT</s:text>
								</span>
							</h2>
						</div>
					</div>
					<ul class="listct clearfix">
						<c:forEach var="financialItem"
							items="${model.financialHighlightList}" varStatus="status">
							<c:choose>
								<c:when test="${financialItem.id.displayLevel == 0}">
									<li class="title">
										<span><c:out value="${financialItem.id.itemName}"
												escapeXml="false">Bảng cân đối kế toán</c:out> </span>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<div class="rowc">
											${financialItem.id.itemName}
										</div>
										<div class="rowd">
											<fmt:formatNumber pattern="###,##0.##">
												<c:out value="${financialItem.id.numericValue}"
													escapeXml="false" />
											</fmt:formatNumber>
										</div>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
					<div class="clear"></div>
				</div>
				<!-- end left -->

				<!-- right -->
				<div class="box_right">
					<div class="tab_new">
						<div>
							<h2>
								<span><s:text
										name="web.label.KeyStatisticAction.form.KeyRatio">Chi so co ban</s:text>
								</span>
							</h2>
						</div>
					</div>
					<ul class="listct">
						<c:forEach var="keyRatio" items="${model.keyRatioList}"
							varStatus="status">
							<c:choose>
								<c:when test="${keyRatio.id.displayLevel == 0}">
									<li class="title">
										<span><c:out value="${keyRatio.id.itemName}"
												escapeXml="false">Quy mô</c:out> </span>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<div class="rowa">
											<c:out value="${keyRatio.id.itemName}" escapeXml="false" />
										</div>
										<div class="rowb">
											<vndirect:analysisIndexing
												numberData="${keyRatio.id.numericValue}"
												itemName="${keyRatio.id.itemCode}" pattern="###,##0.##" />
										</div>
									</li>

								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
				</div>
				<!-- end right -->
			</div>
		</div>
	</div>
</div>
