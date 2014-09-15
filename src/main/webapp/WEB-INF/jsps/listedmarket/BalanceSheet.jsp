<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script type="text/javascript">
$(function() {
	FROM_MODULE = URL_BALANCE_SHEET;
});

function doView() {
	document.fBalanceSheet.submit();
}
function doViewItemDetail(index) {
	try {
		var divDetailId = document.getElementById("divViewItemDetail_" + index);
		var imgDetailId = document.getElementById("imgViewItemDetailId_"
				+ index);
		if (divDetailId.style.display == "inline") {
			divDetailId.style.display = "none";
			imgDetailId.src = $.web_resource.getContext()
					+ "/images/icons/BalanceSheet_expand.gif";
		} else {
			divDetailId.style.display = "inline";
			imgDetailId.src = $.web_resource.getContext()
					+ "/images/icons/BalanceSheet_collapse.gif";
		}
	} catch (e) {
	}
}
</script>
<form name="fBalanceSheet" id="fBalanceSheet" action='<s:url value=""/>'
	method="post">
	<input type="hidden" name="cacheData"
		value="<s:property value="model.cacheData"/>" />

	<div class="content_ttpt">
		<!-- nav -->
		<jsp:include
			page="/WEB-INF/jsps/listedmarket/snippet/si-report-nav.jsp"></jsp:include>

		<!--Start All Tab menu  -->
		<div class="content_small">
			<div class="content_tab" style="padding-top: 15px;">
				<div class="si_tab_new">
					<div>
						<h2>
							<span><s:text
									name="web.label.ListedMarket.form.CellNames.View">Kỳ báo cáo</s:text>
							</span>
							<select name="searchObject.fiscalQuarter" style="height: 20px;">
								<c:forEach var="item" items="${model.quarterList}">
									<c:choose>
										<c:when
											test="${item.itemCode == model.searchObject.fiscalQuarter}">
											<option value="<c:out value="${item.itemCode}" />"
												selected="selected">
												<c:out value="${item.description}" />
											</option>
										</c:when>
										<c:otherwise>
											<option value="<c:out value="${item.itemCode}" />">
												<c:out value="${item.description}" />
											</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							<select name="searchObject.fiscalYear" style="height: 20px;">
								<c:forEach var="item" items="${model.yearList}">
									<c:choose>
										<c:when test="${item == model.searchObject.fiscalYear}">
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
							<span style="margin-left: 20px;"><s:text
									name="web.label.ListedMarket.form.CellNames.NumberTerm" /> </span>
							<select name="searchObject.numberTerm" style="height: 20px; min-width:40px;">
								<c:forEach var="item" items="${model.termList}">
									<c:choose>
										<c:when test="${item == model.searchObject.numberTerm}">
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
							<input style="margin-left:10px;width:50px;" type="button" class="iButton autoHeight" onclick="doView()"
								value='<s:text name="web.label.ListedMarket.form.Buttons.View"/>'>
							<span style="margin-left: 250px;"><s:text
									name="web.label.ListedMarket.form.CellNames.MoneyUnit" /> </span>
							<select name="searchObject.moneyRate"
								onchange="javascript:doView();" style="height: 20px;">
								<c:forEach var="item" items="${model.moneyRateCol}">
									<c:choose>
										<c:when test="${item == model.searchObject.moneyRate}">
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
							<s:text name="web.label.ListedMarket.form.CellNames.MoneyType" />
						</h2>
					</div>
				</div>
				<!-- content -->
				<div class="table_Market clearfix">
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<!--Content here  -->
							<td>
								<table cellpadding="0" cellspacing="0" border="0" width="100%">
									<colgroup>
										<col width="28%" />
										<col width="13%" />
										<col width="13%" />
										<col width="13%" />
										<col width="13%" />
										<col width="13%" />
									</colgroup>
									<tr bgcolor="#efefef">
										<td class="bordertd">
											&nbsp;&nbsp;&nbsp;
										</td>
										<td align="center" class="bordertd">
											<b><s:property
													value="model.financeInfoFirst.strFiscalDate1" /> </b>
										</td>
										<td align="center" class="bordertd">
											<b><s:property
													value="model.financeInfoFirst.strFiscalDate2" /> </b>
										</td>
										<td align="center" class="bordertd">
											<b><s:property
													value="model.financeInfoFirst.strFiscalDate3" /> </b>
										</td>
										<td align="center" class="bordertd">
											<b><s:property
													value="model.financeInfoFirst.strFiscalDate4" /> </b>
										</td>
										<td align="center" class="col_end">
											<b><s:property
													value="model.financeInfoFirst.strFiscalDate5" /> </b>
										</td>
									</tr>

									<c:set var="divLevel1" value="-1" />
									<c:set var="divLevel2" value="-1" />
									<c:set var="divLevel3" value="-1" />
									<c:set var="level" value="-1" />
									<c:forEach var="financeInfo" items="${model.financeInfoList}"
										varStatus="status">
										<c:choose>
											<c:when test="${financeInfo.displayLevel == 0}">
												<tr>
													<td class="bordertd">
														<div
															style="text-align: left; font-weight: bold; text-transform: uppercase;">
															&nbsp;
															<c:out value="${financeInfo.itemName}" />
														</div>
													</td>
													<td class="bordertd">
														<div style="text-align: right; font-weight: bold;">
															&nbsp;
														</div>
													</td>
													<td class="bordertd">
														<div style="text-align: right; font-weight: bold;">
															&nbsp;
														</div>
													</td>
													<td class="bordertd">
														<div style="text-align: right; font-weight: bold;">
															&nbsp;
														</div>
													</td>
													<td class="bordertd">
														<div style="text-align: right; font-weight: bold;">
															&nbsp;
														</div>
													</td>
													<td class="col_end">
														<div style="text-align: right; font-weight: bold;">
															&nbsp;
														</div>
													</td>
												</tr>
											</c:when>
											<c:when test="${financeInfo.displayLevel == 1}">
												<c:if test="${divLevel1 > -1}">
													<c:choose>
														<c:when test="${level== 1}">
								</table>

							</td>
						</tr>
						</c:when>
						<c:when test="${level == 2}">
					</table>
				</div>
				</td>
				</tr>
				</table>

				</td>
				</tr>
				</c:when>
				<c:when test="${level == 3}">
					</table>
					</tr>
					</table>
					</td>
					</tr>
					</table>

					</td>
					</tr>
				</c:when>
				</c:choose>
				</c:if>
				<c:set var="divLevel1" value="${financeInfo.displayLevel}" />
				<tr>
					<td class="bordertd">
						<div style="text-align: left; font-weight: bold;">
							<c:choose>
								<c:when test="${financeInfo.hasChild}">
									<a
										href="javascript:doViewItemDetail('<c:out value='${status.count}' />');">
										<img
											id="imgViewItemDetailId_<c:out value='${status.count}' />"
											border="0px;" width="13" height="13" align="absmiddle"
											src="<web:url value='/images/icons/BalanceSheet_collapse.gif'/>" />
										<c:out value="${financeInfo.itemName}" /> </a>
								</c:when>
								<c:otherwise>
									<img id="imgViewItemDetailId_<c:out value='${status.count}' />"
										border="0px;" width="13" height="13" align="absmiddle"
										src="<web:url value='/images/icons/BalanceSheet_dummy.gif'/>" />
									<c:out value="${financeInfo.itemName}" />
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td class="bordertd">
						<div style="text-align: right; font-weight: bold;">
							<c:out value="${financeInfo.strNumericValue1}" />
							&nbsp;
						</div>
					</td>
					<td class="bordertd">
						<div style="text-align: right; font-weight: bold;">
							<c:out value="${financeInfo.strNumericValue2}" />
							&nbsp;
						</div>
					</td>
					<td class="bordertd">
						<div style="text-align: right; font-weight: bold;">
							<c:out value="${financeInfo.strNumericValue3}" />
							&nbsp;
						</div>
					</td>
					<td class="bordertd">
						<div style="text-align: right; font-weight: bold;">
							<c:out value="${financeInfo.strNumericValue4}" />
							&nbsp;
						</div>
					</td>
					<td class="col_end">
						<div style="text-align: right; font-weight: bold;">
							<c:out value="${financeInfo.strNumericValue5}" />
							&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div id="divViewItemDetail_<c:out value='${status.count}' />"
							style="display: inline;">
							<table class="nomal" cellpadding="0px" cellspacing="0px"
								width="100%">
								<colgroup>
									<col width="28%" />
									<col width="13%" />
									<col width="13%" />
									<col width="13%" />
									<col width="13%" />
									<col width="13%" />
								</colgroup>
								<c:set var="level" value="${financeInfo.displayLevel}" />
								</c:when>
								<c:when test="${financeInfo.displayLevel == 2}">
									<c:if test="${divLevel2 > -1}">
										<c:choose>
											<c:when test="${level == 2}">
							</table>
						</div>
					</td>
				</tr>
				</c:when>
				<c:when test="${level == 3}">
					</table>
					</td>
					</tr>
					</table>

					</td>
					</tr>
				</c:when>
				</c:choose>
				</c:if>
				<c:set var="divLevel2" value="${financeInfo.displayLevel}" />
				<tr>
					<td class="bordertd">
						<div style="text-align: left; margin-left: 5px">
							&nbsp;
							<c:choose>
								<c:when test="${financeInfo.hasChild}">
									<a
										href="javascript:doViewItemDetail('<c:out value='${status.count}' />');">
										<img
											id="imgViewItemDetailId_<c:out value='${status.count}' />"
											border="0px;" width="13" height="13" align="absmiddle"
											src="<web:url value='/images/icons/BalanceSheet_expand.gif'/>" />
										<c:out value="${financeInfo.itemName}" /> </a>
								</c:when>
								<c:otherwise>
									<img id="imgViewItemDetailId_<c:out value='${status.count}' />"
										border="0px;" width="13" height="13" align="absmiddle"
										src="<web:url value='/images/icons/BalanceSheet_collapse.gif'/>" />
									<c:out value="${financeInfo.itemName}" />
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td class="bordertd">
						<div style="text-align: right; font-weight: bold;">
							<c:out value="${financeInfo.strNumericValue1}" />
							&nbsp;
						</div>
					</td>
					<td class="bordertd">
						<div style="text-align: right; font-weight: bold;">
							<c:out value="${financeInfo.strNumericValue2}" />
							&nbsp;
						</div>
					</td>
					<td class="bordertd">
						<div style="text-align: right; font-weight: bold;">
							<c:out value="${financeInfo.strNumericValue3}" />
							&nbsp;
						</div>
					</td>
					<td class="bordertd">
						<div style="text-align: right; font-weight: bold;">
							<c:out value="${financeInfo.strNumericValue4}" />
							&nbsp;
						</div>
					</td>
					<td class="col_end">
						<div style="text-align: right; font-weight: bold;">
							<c:out value="${financeInfo.strNumericValue5}" />
							&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div id="divViewItemDetail_<c:out value='${status.count}' />"
							style="display: none;">
							<table class="nomal" cellpadding="0px" cellspacing="0px"
								width="100%">
								<colgroup>
									<col width="28%" />
									<col width="13%" />
									<col width="13%" />
									<col width="13%" />
									<col width="13%" />
									<col width="13%" />
								</colgroup>
								<c:set var="level" value="${financeInfo.displayLevel}" />
								</c:when>
								<c:when test="${financeInfo.displayLevel == 3}">
									<c:if test="${divLevel3 > -1 && level == 3}">
							</table>
						</div>
					</td>
				</tr>
				</c:if>
				<c:set var="divLevel3" value="${financeInfo.displayLevel}" />
				<tr>
					<td class="bordertd">
						<div style="text-align: left; margin-left: 10px">
							&nbsp;
							<c:choose>
								<c:when test="${financeInfo.hasChild}">
									<a
										href="javascript:doViewItemDetail('<c:out value='${status.count}' />');">
										<img
											id="imgViewItemDetailId_<c:out value='${status.count}' />"
											border="0px;" width="13" height="13" align="absmiddle"
											src="<web:url value='/images/icons/BalanceSheet_expand.gif'/>" />
										<c:out value="${financeInfo.itemName}" /> </a>
								</c:when>
								<c:otherwise>
									<img id="imgViewItemDetailId_<c:out value='${status.count}' />"
										border="0px;" width="13" height="13" align="absmiddle"
										src="<web:url value='/images/icons/BalanceSheet_dummy.gif'/>" />
									<c:out value="${financeInfo.itemName}" />
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td class="bordertd">
						<div style="text-align: right;">
							<c:out value="${financeInfo.strNumericValue1}" />
							&nbsp;
						</div>
					</td>
					<td class="bordertd">
						<div style="text-align: right;">
							<c:out value="${financeInfo.strNumericValue2}" />
							&nbsp;
						</div>
					</td>
					<td class="bordertd">
						<div style="text-align: right;">
							<c:out value="${financeInfo.strNumericValue3}" />
							&nbsp;
						</div>
					</td>
					<td class="bordertd">
						<div style="text-align: right;">
							<c:out value="${financeInfo.strNumericValue4}" />
							&nbsp;
						</div>
					</td>
					<td class="col_end">
						<div style="text-align: right;">
							<c:out value="${financeInfo.strNumericValue5}" />
							&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div id="divViewItemDetail_<c:out value='${status.count}' />"
							style="display: none;">
							<table class="nomal" cellpadding="0px" cellspacing="0px"
								width="100%">
								<colgroup>
									<col width="28%" />
									<col width="13%" />
									<col width="13%" />
									<col width="13%" />
									<col width="13%" />
									<col width="13%" />
								</colgroup>
								<c:set var="level" value="${financeInfo.displayLevel}" />
								</c:when>
								<c:otherwise>
									<tr>
										<td class="bordertd">
											<div style="text-align: left; margin-left: 15px;">
												&nbsp;
												<em><c:out value="${financeInfo.itemName}" /> </em>
											</div>
										</td>
										<td class="bordertd">
											<div style="text-align: right; font-weight: Italic;">
												<c:out value="${financeInfo.strNumericValue1}" />
												&nbsp;
											</div>
										</td>
										<td class="bordertd">
											<div style="text-align: right; font-weight: Italic;">
												<c:out value="${financeInfo.strNumericValue2}" />
												&nbsp;
											</div>
										</td>
										<td class="bordertd">
											<div style="text-align: right; font-weight: Italic;">
												<c:out value="${financeInfo.strNumericValue3}" />
												&nbsp;
											</div>
										</td>
										<td class="bordertd">
											<div style="text-align: right; font-weight: Italic;">
												<c:out value="${financeInfo.strNumericValue4}" />
												&nbsp;
											</div>
										</td>
										<td class="col_end">
											<div style="text-align: right; font-weight: Italic;">
												<c:out value="${financeInfo.strNumericValue5}" />
												&nbsp;
											</div>
										</td>
									</tr>
								</c:otherwise>
								</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${level == 2}">
							</table>
						</div>
					</td>
				</tr>
				</table>
				</td>
				</tr>
				</c:when>
				<c:when test="${level == 3}">
					</table>
					</td>
					</tr>
					</table>
					</td>
					</tr>
					</table>

					</td>
					</tr>
				</c:when>
				</c:choose>
				</table>
				</td>
				<!--End Content here  -->
				</tr>
				</table>

				<div id="Footer_4_QuotesResearch" class="note pn_main clearfix"
					align="justify" style="margin-bottom:10px;">
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td width="39">
								<img height="52" width="39"
									src='<s:url value="/images/front/note_left.gif" />'>
							</td>
							<td class="ct_note">
								<div style="font-size: 11px">
									<s:text name="footer.label.notice"></s:text>
								</div>
							</td>
							<td width="3">
								<img height="52" width="3"
									src='<s:url value="/images/front/note_right.gif" />'>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!--End BalanceSheet-->
			</td>
			</tr>
			</table>

		</div>
	</div>
	<!--End All Tab menu  -->
</form>
</div>