<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>
<script type="text/javascript">
	var CONFIRM_PASSWORD_INVALID = '<s:text name="web.error.userProfile.securityPasswords.MiChangePass.confirm-pw-invalid"/>';
	var VALIDATE_TRADING_PASSWORD = '<s:url action="UserProfileAJAX_ValidateTradingPassword" namespace="/ajax/user"></s:url>';
	var CHANGE_PASSWORD = '<s:url action="thay-doi-thanh-cong" namespace="/"></s:url>';
</script>
<form name="fChangePassword" id="fChangePassword" method="post">


<div id="content_ttpt">                                           
    <ul class="ui-tabs-nav tab_ttpt" id="container-thongtincanhan">
        <c:choose>
            <c:when test="${isFreeUser}">
                <li class=""><a href="<web:url value="/thong-tin-co-ban-tai-khoan-mien-phi.shtml"/>"><label class="icon_active"></label><s:text name="myPage.infor.basic"/></a></li>
            </c:when>
            <c:otherwise>
                <li class=""><a href="<web:url value="/thong-tin-co-ban.shtml"/>"><label class="icon_active"></label><s:text name="myPage.infor.basic"/></a></li>
            </c:otherwise>
        </c:choose>  
        <li class="ui-tabs-selected"><a href="<web:url value="/thong-tin-giao-dich.shtml"/>"><label class="icon_active"></label><s:text name="myPage.infor.trading"/></a></li>
    </ul>
    
    <div class="clear"></div>
    <div id="tab-1" class="ui-tabs-container" style="">
	    <div class="content_mypage">
		    <div class="contentsmall">
		       <div id="fChangePassword_errorMessage"></div>
		       <ul class="list">
		           <li class="title"><h2>Thay đổi mật khẩu giao dịch qua internet</h2></li>
		         
		            <li>
		               <div class="rowa"><s:text name="web.label.userProfile.securityPasswords.EnterCurrentLogOnPassword"></s:text></div>
		               <div class="rowb-c"><input type="password" class="input250" name="model.currentPassword" id="fChangePassword_currentPassword">&nbsp;<b><font color="#FF0000" size="3">*</font></b></div>
		           </li>
		            <li>
		               <div class="rowa"><s:text name="web.label.userProfile.securityPasswords.EnterNewLogOnPassword"></s:text></div>		               
		               <div class="rowb-c"><input type="password" class="input250" name="model.newPassword" id="fChangePassword_newPassword"></div>
		           </li>
		            <li>
		               <div class="rowa"><s:text name="web.label.userProfile.securityPasswords.ReEnterNewLogOnPassword"></s:text></div>
		               <div class="rowb-c"><input type="password" class="input250" name="model.confirmPassword" id="fChangePassword_confirmPassword"></div>
		           </li>
		       </ul>
		        <div class="clear"></div>
		        <div style="text-align:center">
		           <span class="buttons">
		              <input type="button" value='<s:text name="web.button.userProfile.Save"></s:text>' id="fChangePassword_save">
		           </span>
		        </div>
		    </div>
	    </div>
	    
	    <!--++c column-->
        <div class="width315" id="c-column" style="margin-top: 0px;">
            <div class="boxcontentcolumn">
                <img src='<s:url value="/images/thumbs/img_qc.png" />'/>
            </div>
            <div class="boxcontentcolumn">
                <ul>
                    <li>
                        <span class="icon_question_fast left"></span>
                        <div class="box_text_tienich line">
                            <a href="<web:url value="/tro-giup/hoi-dap-huong-dan.shtml"/>"><s:text name="myPage.leftColumn.qA"></s:text></a>
                        </div>
                    </li>
                    <li>
                        <span class="icon_guide left "></span>
                        <div class="line box_text_tienich">
                            <a href="<web:url value="/tro-giup/hoi-dap-huong-dan.shtml"/>"><s:text name="myPage.leftColumn.guide"></s:text></a>
                        </div>
                    </li>
                     <li>
                        <span class="icon_logo_vnd left"></span>
                        <div class="box_text_tienich">
                            <a href="<web:url value="/vndirect/san-pham-dich-vu.shtml"/>"><s:text name="myPage.leftColumn.newsVnds"></s:text></a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <!--//c column-->
    </div>
</div>

</form>