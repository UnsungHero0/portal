<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script type="text/javascript">
	function doPreview1() {
		var url = "OnlineAccount.toPdf?PDF_TYPE=1";
		window.open(url, "_blank", "");
		return;
	}

	function viewOrganization() {
		var index = $("#xemmangluoiId").position().top;
		$(window).scrollTop(index);
	}
</script>
<div class="openaccInfo-wp">
    <h1 class="title">MỞ TÀI KHOẢN TRỰC TUYẾN</h1>
	<div class="finished_openaccount_box">
		<div class="step_info">
           <img src='<web:url value="/images/icons/mtk_step3.png"/>'/>
        </div>
		<div class="opentAccNote" style="margin-top: 20px;">
		  <h2 style="color: red;">${errorMessage}</h2>
		</div>
		<p>
			Quý khách vui lòng liên hệ tổng đài <strong
				class="orange">1900 54 54 09</strong> dể được hỗ trợ.
		</p>
		</li> <br />
		<div>Trân trọng,</div>
		<div>
			<strong>CÔNG TY CỔ PHẦN CHỨNG KHOÁN VNDIRECT.</strong>
		</div>
	</div>
	<!--/boxInfo-->
	<div class="openAccR">
		<jsp:include page="snippet/RightOpenAccountContent.jsp"></jsp:include>
	</div>
	<!--/openAccR-->
	<div class="clear"></div>
</div>