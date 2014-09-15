<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script type="text/javascript">

$(function() {
    FROM_MODULE = URL_INSIDE_TRANSACTION;
});

function doViewIncomeForecast() {
    document.fIncomeStatementForecast.submit();
}
</script>
<form name="fInsiderTransactions" action="" method="post">
	<!-- Begin Content -->
	<div class="content_ttpt">
		<!-- nav -->
		<jsp:include
			page="/WEB-INF/jsps/listedmarket/snippet/si-ownership-nav.jsp"></jsp:include>

		<div class="box_giaodichnoibo">
			<div class="title_tab">
				<div class="bg_righttab">
					<h2>
						<s:text name="web.label.InsiderTransactions.InsiderTrans">Giao dịch cổ phiếu nội bộ</s:text>
					</h2>
				</div>
			</div>
			<table cellpadding="0" cellspacing="0" class="boxlist" width="100%">
				<tr class="title">
					<th class="row_time">
						<s:text name="web.label.date">Ngày</s:text>
					</th>
					<th class="row_name">
						<s:text name="web.label.InsiderTransactions.Insider">Giạo dịch cổ đông nội bộ</s:text>
					</th>
					<th class="row_cv">
						<s:text name="web.label.InsiderTransactions.Position">Chức vụ</s:text>
					</th>
					<th class="row_a">
						<s:text name="web.label.InsiderTransactions.SharesHeld">Số cổ phần giao dịch</s:text>
					</th>
					<th class="row_cv">
						<s:text name="web.label.InsiderTransactions.Transaction">Loại giao dịch</s:text>
					</th>
					<th class="row_b">
						<s:text name="web.label.InsiderTransactions.Price">Giá</s:text>
					</th>
				</tr>
				<s:iterator value="model.ifoInsiderTransactionViewList"
					status="status">
					<tr>
						<td class="row_time">
							<s:property value="id.transactionDateStr" />
						</td>
						<td class="row_name">
							<s:property value="id.insider" />
						</td>
						<td class="row_cv">
							<s:property value="id.position" />
						</td>
						<td class="row_a">
							<fmt:formatNumber pattern="##,###,###.##">
								<s:property value="id.sharesHeld" />
							</fmt:formatNumber>
						</td>
						<td class="row_cv">
							<s:text name="web.label.InsiderTransactions.%{id.transaction.toLowerCase()}"></s:text>
						</td>
						<td class="row_b">
							<s:if test="%{id.price >= 0}">
								<fmt:formatNumber pattern="##,###,###.##">
									<s:property value="id.price" />
								</fmt:formatNumber>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</div>
</form>