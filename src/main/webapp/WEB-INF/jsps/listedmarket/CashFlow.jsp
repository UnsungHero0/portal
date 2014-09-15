<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<script type="text/javascript">
$(function() {
	FROM_MODULE = URL_CASH_FLOW;
});
</script>
<form name="fCashFlow" id="fCashFlow" method="post">
	<div class="content_ttpt">
		<!-- nav -->
		<jsp:include
			page="/WEB-INF/jsps/listedmarket/snippet/si-report-nav.jsp"></jsp:include>

		<div class="content_small">
			<div class="content_tab" style="padding-top: 15px;">
				<div class="si_tab_new">
					<div>
						<h2>
							<span><s:text
									name="web.label.ListedMarket.form.CellNames.View">Kỳ báo cáo</s:text>
							</span>
							<select name="searchObject.fiscalQuarter"
								id="fCashFlow_fiscalQuarter" style="height: 20px;">
								<s:iterator value="model.quarterList" status="status">
									<s:if test="itemCode == model.searchObject.fiscalQuarter">
										<option value="<s:property value="itemCode"/>"
											selected="selected">
											<s:property value="description" />
										</option>
									</s:if>
									<s:else>
										<option value="<s:property value="itemCode"/>">
											<s:property value="description" />
										</option>
									</s:else>
								</s:iterator>
							</select>
							<select name="searchObject.fiscalYear" id="fCashFlow_fiscalYear"
								style="height: 20px;">
								<s:iterator id="item" value="model.yearList" status="status">
									<s:if test="#item == model.searchObject.fiscalYear">
										<option value="<s:property value="#item"/>"
											selected="selected">
											<s:property value="#item" />
										</option>
									</s:if>
									<s:else>
										<option value="<s:property value="#item"/>">
											<s:property value="#item" />
										</option>
									</s:else>
								</s:iterator>
							</select>
							<span style="margin-left: 20px;"><s:text
									name="web.label.ListedMarket.form.CellNames.NumberTerm">Số kỳ hiển thị</s:text>
							</span>
							<select name="searchObject.numberTerm" id="fCashFlow_numberTerm"
								style="height: 20px; min-width:40px;">
								<s:iterator id="item" value="model.termList" status="status">
									<s:if test="#item == model.searchObject.numberTerm">
										<option value="<s:property value="#item"/>"
											selected="selected">
											<s:property value="#item" />
										</option>
									</s:if>
									<s:else>
										<option value="<s:property value="#item"/>">
											<s:property value="#item" />
										</option>
									</s:else>
								</s:iterator>
							</select>
							<input type="button" class="iButton autoHeight"
								id="fCashFlow_View"
								value='<s:text name="web.label.ListedMarket.form.Buttons.View"/>'>
							<span style="margin-left: 250px;"><s:text
									name="web.label.ListedMarket.form.CellNames.MoneyUnit">Đơn vị</s:text>
							</span>
							<select name="searchObject.moneyRate" id="fCashFlow_MoneyUnit"
								style="height: 20px;">
								<s:iterator id="item" value="model.moneyRateCol" status="status">
									<s:if test="#item == model.searchObject.moneyRate">
										<option value="<s:property value="#item"/>"
											selected="selected">
											<s:property value="#item" />
										</option>
									</s:if>
									<s:else>
										<option value="<s:property value="#item"/>">
											<s:property value="#item" />
										</option>
									</s:else>
								</s:iterator>
							</select>
							<s:text name="web.label.ListedMarket.form.CellNames.MoneyType" />
						</h2>
					</div>
				</div>
				<!-- content -->
				<div class="table_Market clearfix" style="padding-bottom: 20px;">
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td>
								<table class="nomal" cellpadding="0px" cellspacing="0px"
									width="100%" id="Listed_CashFlow_tableResult">
									<colgroup>
										<col width="40%" />
										<col width="12%" />
										<col width="12%" />
										<col width="12%" />
										<col width="12%" />
										<col width="200" />
									</colgroup>
									<thead>
										<tr bgcolor="#efefef">
											<td class="bordertd">
												&nbsp;&nbsp;&nbsp;
											</td>
											<td class="bordertd">
												<div style="font-weight: bold; text-align: center;"
													id="divFiscalDate1">
													&nbsp;
												</div>
											</td>
											<td class="bordertd">
												<div style="font-weight: bold; text-align: center;"
													id="divFiscalDate2">
													&nbsp;
												</div>
											</td>
											<td class="bordertd">
												<div style="font-weight: bold; text-align: center;"
													id="divFiscalDate3">
													&nbsp;
												</div>
											</td>
											<td class="bordertd">
												<div style="font-weight: bold; text-align: center;"
													id="divFiscalDate4">
													&nbsp;
												</div>
											</td>
											<td class="col_end">
												<div style="font-weight: bold; text-align: center;"
													id="divFiscalDate5">
													&nbsp;
												</div>
											</td>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<!-- end content -->
			</div>
		</div>
	</div>
</form>