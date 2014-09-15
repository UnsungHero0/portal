<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script>
$(document).ready(function(){
	$('#nextButton').val('<s:text name="br.service.openaccount.finish" />');
});
</script>

<!-- popup -->
<div id="processingOpenAccountDialog" title="Đang xử lý">
    <p style="text-align: center">Xin vui lòng chờ trong giây lát.Yêu cầu của quý khách hiện đang được hệ thống xử lý.</p>
    <br/>
    <p style="text-align: center"><img src="<web:url value='/images/icons/processingOpenAcc.gif'/>"/></p>
</div>

<ul class="list_b4 clearfix">
	<li>
		<strong><s:text
				name="web.label.online.account.general.provisions">Các điều khoản chung</s:text>
		</strong>
		<div class="box_dk_mtk">
			<c:choose>
				<c:when test="${locale == 'vn'}">
					<iframe style="border: #1B327D solid 1px"
						src="<s:url value="/htmls/IndividualAgreementAndConfirm_vn.html"/>"
						width="600" height="300">
					</iframe>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${locale == 'jp'}">
							<iframe style="border: #1B327D solid 1px"
								src="<s:url value="/htmls/IndividualAgreementAndConfirm_jp.html"/>"
								width="600" height="300">
							</iframe>
						</c:when>
						<c:otherwise>
							<iframe style="border: #1B327D solid 1px"
								src="<s:url value="/htmls/IndividualAgreementAndConfirm_en_GB.html"/>"
								width="600" height="300">
							</iframe>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>
	</li>
	<li>
		<div class="rowa">
			<input type="radio" name="decision" class="option" checked="checked" onclick="stateNextButtonWithDecision(true);"
				value="true">
			<s:text name="web.label.online.account.agree">Đồng ý</s:text>
		</div>
		<div class="rowb">
			<input type="radio" name="decision" class="option" value="false" onclick="stateNextButtonWithDecision(false);">
			<s:text name="web.label.online.account.disagree">Không đồng ý</s:text>
		</div>
	</li>
	<li class="tienich">
		<p>
			<strong><s:text
					name="web.label.online.account.review.the.information.in.the.declaration.and.registration">Xem lại thông tin khai báo và in các bản đăng ký</s:text>
			</strong>
		</p>
		<p class="padding-top10">
			<span class="padding-right20"><s:text
					name="web.label.online.account.contract.account">Hợp đồng mở tài khoản</s:text>
			</span>
			<input type="button" class="iButton" style="width:88px;"
				onclick="javascript:doPreview1();"
				value='<s:text name="web.label.online.account.preview">Xem trước</s:text>'>&nbsp;&nbsp;
			<input type="button" class="iButton" onclick="javascript:doPrint1();"  style="width:50px;"
				value='<s:text name="web.label.online.account.print">In ra</s:text>'>
		</p>

		<s:if test="%{#prime}">
			<p class="padding-top10">
				<span class="padding-right20"><s:text
						name="web.label.online.account.the.registration.cards.care.package.separate.account">Phiếu đăng ký gói dịch vụ chăm sóc tài khoản riêng</s:text>
				</span>
				<input type="button" class="iButton"
					onclick="javascript:doPreview3();"
					value='<s:text name="web.label.online.account.preview">Xem trước</s:text>'>
				<input type="button" class="iButton"
					onclick="javascript:doPrint3();"
					value='<s:text name="web.label.online.account.print">In ra</s:text>'>
			</p>
		</s:if>
	</li>
</ul>