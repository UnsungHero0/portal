<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="vn.com.vndirect.commons.utility.VNDirectWebUtilities"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script type="text/javascript">
$(function() {
	loadTabFormAndApplication();
});
</script>

<div id="content">
	<jsp:include page="/WEB-INF/jsps/home/help/snippet/helpNav.jsp"></jsp:include>

	<div class="content_bieumauud">
		<div class="padding-bottom10 line">
			<p>
				<s:text name="help.downloadForm.text1">Các mẫu biểu dành cho Nhà đầu tư khi giao dịch tại</s:text>
				<strong> VNDIRECT</strong>
			</p>
			<p>
				<s:text name="help.downloadForm.text2">Sử dụng</s:text>
				<strong><a href="http://get.adobe.com/reader/"
					target="_blank">Adobe Acrobat</a> </strong>
				<s:text name="help.downloadForm.text3">để xem các mẫu biểu này</s:text>
			</p>
		</div>
		<div class="clear"></div>
		<div class="clearfix content_bieumauud">
			<div id="listFormAndApplication"></div>
		</div>

		<div class="clear"></div>
		<p class="padding-top10">
			<i><s:text name="help.downloadForm.note">(*) Hầu hết các form được chấp nhận khi gửi qua fax. Một số form yêu cầu phải gửi bản gốc</s:text>
			</i>
		</p>
	</div>
</div>
