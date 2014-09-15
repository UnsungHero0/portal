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

function viewOrganization(){
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
		<s:if test="%{!errorMessage.isEmpty()}">
			<div class="opentAccNote" style="margin-top: 20px;">
	          <h2 style="color: red;">${errorMessage}</h2>
	        </div>
		</s:if>
		<s:else>
	    <p class="title">Quý khách đã mở tài khoản giao dịch thành công tại VNDIRECT!</p>
		</s:else>
		<div>
		      <p>Các thông tin giao dịch bao gồm: số tài khoản, số lưu ký, mật khẩu giao dịch sẽ được gửi tới Quý khách hàng qua email. 
             Ngay sau đó, Quý khách có thể tiến hành truy cập vào tài khoản và giao dịch.</p>
             <div class="arrow_icons_up"></div>
             <div class="box_button_guidle">
                <div class="mtk_btn"><a href='<%=vn.com.web.commons.servercfg.ServerConfig.getOnlineValue("/server-config/sso-url")%>' class="white_coior mtk_btn_link">Đăng nhập</a></div>
                <div class="mtk_btn"><a href='<web:url value="tro-giup/hoi-dap-huong-dan.shtml"/>' class="white_coior mtk_guide_btn_link">Hướng dẫn giao dịch</a></div>
             </div>
             <div class="arrow_icons_down"></div>
		</div>
		<p style="padding-top: 55px;"><strong>Để hoàn thiện thủ tục mở tài khoản</strong>, Quý khách vui lòng:</p>
		<ul class="notice_mtk">
	        <li><p>In <strong>02</strong> bộ <strong><a href="javascript:void(0);" onclick="doPreview1();" class="orange">Hợp đồng mở tài khoản</a></strong>
	        , ký nháy vào hợp đồng, kèm theo <strong>bản photo CMND</strong></p></li>
	        <li><p>Chuyển về cho chúng tôi qua <a href="javascript:void(0);" onclick="viewOrganization();" class="orange"><strong>các văn phòng, chi nhánh của VNDIRECT</strong></a> trên toàn quốc.</p></li>
	        <li><p>Thời gian: <strong>2 tuần</strong></p></li>
	     </ul>
	     <br/>
	     <div>Trân trọng,</div>
	     <div><strong>CÔNG TY CỔ PHẦN CHỨNG KHOÁN VNDIRECT.</strong></div>
    </div>
	<!--/boxInfo-->
	<div class="openAccR">
        <jsp:include page="snippet/RightOpenAccountContent.jsp"></jsp:include>
    </div>
	<!--/openAccR-->
	<div class="clear"></div>
</div>