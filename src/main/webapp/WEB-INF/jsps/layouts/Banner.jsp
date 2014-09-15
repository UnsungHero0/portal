<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="vn.com.vndirect.commons.i18n.I18NUtility"%>
<%@ page import="vn.com.vndirect.commons.i18n.Language"%>
<%@ page import="vn.com.web.commons.utility.DateUtils"%>
<%@ page
	import="vn.com.vndirect.commons.usercounter.WebStatisticsHolder"%>
<%@ page import="vn.com.vndirect.commons.utility.VNDirectWebUtilities"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript">
var IS_AUTH = false;
//var URL_GET_LOGIN_TICKET = '<web:url value="/getlt.shtml"/>';
</script>

<!-- fix all -->

<c:set var="isAuth" value="false" scope="request" />
<authz:authorize
	ifAnyGranted="ROLE_ONLINE_FREE_REGISTER,ROLE_ONLINE_UPDATE_ACCOUNT_INFO,ROLE_OPEN_AN_ACCOUNT,ROLE_ONLINE_VIEW_ACCOUNT_INFO,ROLE_ONLINE_TRADING">
	<c:set var="isAuth" value="true" scope="request" />
	<script type="text/javascript">
        IS_AUTH = true;
</script>
</authz:authorize>

<c:set var="isFreeUser" value="false" scope="request" />
<authz:authorize ifAnyGranted="ROLE_ONLINE_FREE_REGISTER">
	<c:set var="isFreeUser" value="true" scope="request" />
</authz:authorize>

<c:set var="isTradingRole" value="false" />
<authz:authorize ifAnyGranted="ROLE_ONLINE_TRADING">
	<c:set var="isTradingRole" value="true" scope="request" />
</authz:authorize>

<c:set var="isViewAccountRole" value="false" />
<authz:authorize
	ifAnyGranted="ROLE_ONLINE_UPDATE_ACCOUNT_INFO,ROLE_ONLINE_VIEW_ACCOUNT_INFO">
	<c:set var="isViewAccountRole" value="true" scope="request" />
</authz:authorize>
<%
	Date date = DateUtils.getCurrentDate();
	String strDate = "";
	try {
		strDate = DateUtils.dateToString(date,
				DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY_HHMMAM);
	} catch (Exception e) {
		strDate = date.toString();
	}
%>
<%
	String locale = I18NUtility.getCurrentLocale();
	locale = locale == null ? "" : locale;
%>

<script type="text/javascript">
<!--
var LOCALE = '<%=locale%>';
	//-->
	$(document).ready(function() {
		setCookie("c_redirectPageAfterLogin", "trading", 10000);
		registerBackWhenLogin();
		$("#log-on-help-options-close").click(function() {
			$("#log-on-help-options").css('display', 'none');
			$("#fLoginPopup_btnLogin").css('color', '#FFFFFF');
		});

		$("body").click(function(e) {
			if (!$(e.target).parents('.log-on-form').length)
				$("#log-on-help-options").css('display', 'none');
		});
		
		//Get script variable lt
		var inputs = $('.log-on-form').find(':input:enabled:not(select)');
		$.each(inputs, function(){
			$(this).click(function(){
				var url_ajax = '<%=vn.com.web.commons.servercfg.ServerConfig.getOnlineValue("/server-config/sso-url")%>&getlt=true';
				var scriptGetLt = document.createElement('script');
				scriptGetLt.src = url_ajax;
				document.body.appendChild(scriptGetLt);
				document.body.removeChild(document.body.lastChild);
			});
		});
	});
	function changeTarget() {
		setCookie("c_redirectPageAfterLogin", $("#target_id").val(), 10000);
		registerBackWhenLogin();
		return;
	}
	function showLoginForm() {
		$("#log-on-help-options").css('display', 'block');
		//$("#login_ticket").load(URL_GET_LOGIN_TICKET);
	}
	function doLoginPopup() {
		setCookie("c_redirectPageAfterLogin", $("#target_id").val(), 10000);
		try {
			$("#fLogin input[name=lt]").val(lt);
			$("#fLogin").submit();
		} catch (e) {
			location.href = "<web:url value='/thong-tin-co-ban.shtml'/>";
		}
	}
	function registerBackWhenLogin() {
		var redirectPage = getCookie("c_redirectPageAfterLogin");
		if (redirectPage == "trading") {
			redirectPage = "trading";
		} else if (redirectPage == "#") {
			redirectPage = location.href;
		}

		var ref = '<web:url value="/xac-thuc.shtml"/>?ref=' + redirectPage;
		$.post(ref);
	}
	function enterKeyPressAction(e, actionNext) {
		var key;
		if (window.event) {
			key = window.event.keyCode;
		}//IE
		else {
			key = e.which;//firefox
		}
		if (key == 13) {
			eval(actionNext);
		}
	}
</script>
<div id="header-wrapper">
	<div id="header_new">
		<div class="box_hotline_and_language">
			<div class="text_hotline">Hotline: 1900 5454 09</div>
			<div class="language">
				<%-- <span class="margin-right15 left"><%=strDate%></span> <span
					style="color: #9B9B9B">|</span> <span class="text_serverinfo"><%=VNDirectWebUtilities.getServerNumber()%>
					A : <%=WebStatisticsHolder.getWebStatistics().getTotalActiveSession()%></span> --%> 
				<div class="right">
					<web:currentLang />
				</div>
			</div>
			<div class="login_new">
				<c:choose>
					<c:when test="${isAuth}">
						<script>
                                setCookie("hadLogined","true",15000);
                        </script>
						<div class="sau_login_new">
							<div class="border">
								<img class="avatar" src="<s:url value="/images/thumbs/avatar.png" />" /> <span
									class="name_user"><web:webUser code="fullName" /></span> <span
									class="icon_row"></span>
							</div>
							<div class="setting_25_5">
								<div class="border_top"></div>
								<div class="content_setting">
									<ul class="list_setting">
										<c:choose>
											<c:when test="${isFreeUser}">
												<li><a
													href="<web:url value='/thay-doi-mat-khau.shtml'/>"><s:text
															name="home.userProfile.Change"></s:text>&nbsp; <s:text
															name="home.userProfile.myInfo.Password"></s:text></a></li>
												<li class="noline"><a
													href="<s:url value="/online/sec_logout.shtml"/>"><s:text
															name="banner.btlogout">Logout</s:text></a></li>
											</c:when>
											<c:otherwise>
												<li><a
													href="<web:url value='/thong-tin-co-ban.shtml'/>"><s:text
															name="banner.UpdateProfile">Update Profile</s:text></a></li>
												<li class="noline"><a
													href="<s:url value="/online/sec_logout.shtml"/>"><s:text
															name="banner.btlogout">Logout</s:text></a></li>
											</c:otherwise>
										</c:choose>

									</ul>
								</div>
							</div>

						</div>
					</c:when>
					<c:otherwise>
						<script>
                                //var isLogin = getCookie("hadLogined");
                                //if (isLogin=='true') {
                                //    location.href= '<web:url value="/xac-thuc.shtml"/>?rc=true&ref=' + location.href;
                                //}
                                setCookie("hadLogined","",15000);
                            </script>
						<form id="fLogin" name="fLogin"
							action="<%=vn.com.web.commons.servercfg.ServerConfig
							.getOnlineValue("/server-config/sso-url")%>"
							method="post">
							<div class="log-on-form">
								<input type="text" autocomplete="off" name="username"
									id="fLoginPopup_username" class="user_new" value=""
									onfocus="showLoginForm();"
									placeholder='<s:text name="home.userName"/>'
									onkeypress="enterKeyPressAction(event, 'doLoginPopup()');">
								<input type="password" id="fLoginPopup_password"
									autocomplete="off" name="password" class="password_new"
									onfocus="showLoginForm();" value=""
									placeholder='<s:text name="home.passWord"/>'
									onkeypress="enterKeyPressAction(event, 'doLoginPopup()');">
								<input type="hidden" name="_eventId" value="submit" autocomplete="off"> 
								<input type="hidden" name="lt" id="lt_check_security" value="" autocomplete="off"> 
								<input style="" tabindex="3" type="button" value='<s:text name="button.login">Login</s:text>'
									   class="buttonlogin" id="fLoginPopup_btnLogin"
									   onclick="doLoginPopup(event);" />
								<div id="log-on-help-options" class="log_options_new_25_5" style="display: none">
									<div class="bg_t">
										<div class="box_inputtext"> 
                                    		<s:text name="banner.boxlogin.label.goto">Chuyển Tới</s:text> 
                           				</div>
										<div class="clearfix box_select">
											<select onchange="changeTarget();" tabindex="4" name="target"
												id="target_id">
												<%-- <option value="<web:url value='/home/RedirectUrl.shtml?code=TradingOnlinePage&ticket=true'/>"  selected="selected"> --%>
												<option value="trading" selected="selected">
													<s:text name="banner.boxlogin.redirectPage.trade">Giao dịch trực tuyến</s:text>
												</option>
												<option value="<web:url value="/thong-tin-co-ban.shtml"/>">
													<s:text name="banner.boxlogin.redirectPage.userProfile">Thông tin người dùng</s:text>
												</option>
												<option value="#">
													<s:text name="banner.boxlogin.redirectPage.currentPage">Trang hiện tại</s:text>
												</option>
											</select>
										</div>
										<ul>
											<li><a href="<web:url value='/mo-tai-khoan.shtml'/>"><s:text
														name="banner.boxlogin.notHaveAccountYet">Chưa có tài khoản giao dịch</s:text></a>
											</li>
											<li><a href="<web:url value='/quen-mat-khau.shtml'/>"><s:text
														name="banner.boxlogin.forgetPassword">Quên mật khẩu</s:text></a>&nbsp;&nbsp;
												|&nbsp;&nbsp; <a
												href="<web:url value='/tro-giup/hoi-dap-huong-dan.shtml'/>"><s:text
														name="banner.boxlogin.help">Trợ giúp</s:text></a></li>
											<li><a href="https://www.globalsign.com/" target=_blank
												title="SSL"><img alt="SSL" border=0 id="ss_img"
													src="//seal.globalsign.com/SiteSeal/images/gs_noscript_130-65_en.gif"></a></span>
												<script type="text/javascript"
													src="//seal.globalsign.com/SiteSeal/gs_image_130-65_en.js"></script><br />
												<a href="https://www.globalsign.com/" target=_blank
												style="color: #000000; text-decoration: none; font: bold 8px arial; margin: 0px; padding: 0px;"></a>
											</li>
										</ul>
									</div>
									<div class="bg_bt">
										<a tabindex="9" id="log-on-help-options-close" href="#"
											onclick="" class="close">Close</a>
									</div>
									<div id="login_ticket" style="display: none">
										<script
											src="<%=vn.com.web.commons.servercfg.ServerConfig.getOnlineValue("/server-config/sso-url")%>&getlt=true"></script>
										<script type="text/javascript"> 
                                        </script>
									</div>
								</div>
							</div>
						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>