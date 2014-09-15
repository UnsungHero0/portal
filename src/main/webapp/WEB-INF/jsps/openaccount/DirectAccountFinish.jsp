<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<script type="text/javascript">
   $(document).ready(function(){
	  $("#accept_button").click(function(){
		  var email = $(this).attr("data");
		  if(email.indexOf("@yahoo") > -1){
			  window.open("https://login.yahoo.com/","_blank");
		  } else {
			  window.open("https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail","_blank");
		  }
	  });
   });
</script>
<div class="openaccInfo-wp">
    <h1 class="title">MỞ TÀI KHOẢN TRỰC TUYẾN</h1>
    <div class="finished_openaccount_box">
        <div class="step_info">
           <img src='<web:url value="/images/icons/mtk_step2.png"/>'/>
        </div>
        <p class="title margin-left10">Cảm ơn Quý khách đã đăng ký mở tài khoản tại VNDIRECT!</p>
        <ul>
            <li><p>Tài khoản của Quý khách đã được tạo. Quý khách có thể bắt đầu đăng nhập vào website VNDIRECT 
                   và trải nghiệm các tiện ích Sàng lọc cổ phiểu, Quản lý danh mục đầu tư cùng VNDIRECT</p></li>
            <li style="padding-top: 10px;"><div style="width:415px; float: left; font-style: italic;">Để <strong>bắt đầu giao dịch</strong>, Quý khách vui lòng "<strong>Xác nhận</strong>"
                    các thông tin đã đăng ký qua đường dẫn gửi tới <strong>email của Quý khách</strong>.</div>
                <div class="accept_button" id="accept_button" data="${account.email}"></div>        
            </li>
        </ul>
        <br/>
        <div class="margin-left10">Trân trọng</div>
        <div class="margin-left10"><strong>CÔNG TY CỔ PHẦN CHỨNG KHOÁN VNDIRECT.</strong></div>
    </div>
    <!--/boxInfo-->
    <div class="openAccR">
        <jsp:include page="snippet/RightOpenAccountContent.jsp"></jsp:include>
    </div>
    <!--/openAccR-->
    <div class="clear"></div>
</div>