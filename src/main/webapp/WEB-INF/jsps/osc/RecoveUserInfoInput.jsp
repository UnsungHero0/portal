<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://www.webapp.com.vn/tags-fmt" prefix="webFmt"%>

<%
	String accountType = request.getParameter("accountType");
	String isDisplay = ("VNDIRECTUser".equals(accountType) || "WebAgent".equals(accountType))
			? "block"
			: "none";
%>

<web:url value="/lay-lai-mat-khau.shtml" var="urlRecoverUserInfoFinish"></web:url>
<form name="fRecoveUserInfoInput" id="fRecoveUserInfoInput"
	method="post" action="${urlRecoverUserInfoFinish}">
	<div class="pn_main" id="page_TTNY">

		<div class="etNoticeBox">
			<div class="etNoticeBoxContent">
				<div class="iconNotice"></div>
				<span class="noticeText">Quý khách vui lòng nhập những thông
					tin sau.</span>
				<div class="clear"></div>
			</div>
		</div>
		<form:errors path="*" cssClass="errors" id="status" element="div" />
		<div class="clearfix" style="padding: 15px 0 30px 0px; width: 100%;">
			<s:actionerror
				cssStyle="color: red; display: inline; text-align: left; font-weight: bold;"></s:actionerror>
			<!-- left -->
			<div class="left">
				<div class="formLogin">
					<div class="_boxLoginOuter">
						<div class="_boxLogin">
							<table cellspacing="0" cellpadding="0" border="0"
								style="display: block; padding-top: 15px; padding-left: 10px;">
								<tr>
									<th style="width: 32%; font-size: 13px;">
										<s:text
											name="web.label.userProfile.RecoverUserInfoInput.Username">VNDIRECT Username</s:text>
									</th>
									<td>
										<input id="fRecoveUserInfoInput_userName" size="29"
											maxlength="250" name="recoverPassword.userName" type="text"
											value="<c:out value='${model.recoverPassword.userName}'/>">
									</td>
								</tr>
								<tr>
									<th style="font-size: 13px;">
										Loại tài khoản
									</th>
									<td>
										<div class="boxLogin_radioBtn"
											onclick="javascript:toDisplay();">
											<input class="styledRadioButton"
												id="fRecoveUserInfoInput_accountTypeVNDIRECTUser"
												type="radio" value="VNDIRECTUser" name="accountType"
												<c:if test='${"VNDIRECTUser" == model.accountType}'>checked</c:if> />
											<span class="inactiveStyledRadioButton"></span>
											<div class="radioText">
												<s:text
													name="web.label.userProfile.RecoverUserInfoInput.UserType.OnlineTradingCustomer">VNDIRECT Online Trading Customer</s:text>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<th style="font-size: 13px;">
										&nbsp;
									</th>
									<td>
										<div class="boxLogin_radioBtn"
											onclick="javascript:toHidden();">
											<input class="styledRadioButton"
												id="fRecoveUserInfoInput_accountTypeFreeUser" type="radio"
												value="FreeUser" name="accountType"
												<c:if test='${"FreeUser" == model.accountType}'>checked</c:if> />
											<span class="inactiveStyledRadioButton"></span>
											<div class="radioText">
												<s:text
													name="web.label.userProfile.RecoverUserInfoInput.UserType.FreeRegisteredUser">Free Registered User</s:text>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<th style="font-size: 13px;">
										&nbsp;
									</th>
									<td>
										<div class="boxLogin_radioBtn"
											onclick="javascript:toHidden();">
											<input class="styledRadioButton"
												id="fRecoveUserInfoInput_accountTypeHomeDirect" type="radio"
												value="FreeUser" name="accountType"
												<c:if test='${"FreeUser" == model.accountType}'>checked</c:if> />
											<span class="inactiveStyledRadioButton"></span>
											<div class="radioText">
												<s:text
													name="web.label.userProfile.RecoverUserInfoInput.UserType.HomeDirectUser">HomeDirect User</s:text>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<th style="font-size: 13px;">
										&nbsp;
									</th>
									<td>
										<div class="boxLogin_radioBtn"
											onclick="javascript:toDisplay();">
											<input class="styledRadioButton"
												id="fRecoveUserInfoInput_accountTypeWebAgent" type="radio"
												value="WebAgent" name="accountType"
												<c:if test='${"WebAgent" == model.accountType}'>checked</c:if> />
											<span class="inactiveStyledRadioButton"></span>
											<div class="radioText">
												<s:text
													name="web.label.userProfile.RecoverUserInfoInput.UserType.WebAgent">Web Agent</s:text>
											</div>
										</div>
									</td>
								</tr>
							</table>
							<div style="display: <%=isDisplay%>" id="extra">
								<input id="fRecoveUserInfoInput_contractNumber"
									name="recoverPassword.contractNumber" size="29" maxlength="250"
									type="hidden" value="<c:out value='0000'/>">
								<b style="margin-left: 15px;"><s:text
										name="web.label.userProfile.RecoverUserInfoInput.ConfirmYourExtraInformation">Xác nhận thông tin cá nhân</s:text>
								</b>
								<table cellspacing="0" cellpadding="0" border="0"
									style="display: block; padding-top: 15px; padding-left: 10px;">
									<tr>
										<td style="font-size: 13px; width: 32%;">
											<s:text
												name="web.label.userProfile.RecoverUserInfoInput.IndentificationType">Nhận dạng</s:text>
										</td>
										<td>
											<select name="recoverPassword.identificationType"
												id="fRecoveUserInfoInput_identificationType">
												<option value="">
													- - -
												</option>
												<c:forEach var="item"
													items="${model.identificationTypeList}">
													<option value="<c:out value='${item.itemCode}' />"
														<c:if test="${item.itemCode == model.recoverPassword.identificationType}">selected</c:if>>
														<c:out value='${item.description}' />
													</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td style="font-size: 13px;">
											<s:text
												name="web.label.userProfile.RecoverUserInfoInput.IndentificationCode">Số</s:text>
										</td>
										<td>
											<input id="fRecoveUserInfoInput_identificationCode"
												name="recoverPassword.identificationCode" size="29"
												maxlength="250" type="text"
												value="<c:out value='${model.recoverPassword.identificationCode}'/>">
										</td>
									</tr>
									<tr>
										<td style="font-size: 13px;">
											<s:text
												name="web.label.userProfile.RecoverUserInfoInput.VTOSSericalNumber">VTOS Serical Number</s:text>
										</td>
										<td>
											<input id="fRecoveUserInfoInput_idgSerialCard"
												name="recoverPassword.idgSerialCard" size="29"
												maxlength="250" type="text"
												value="<c:out value='${model.recoverPassword.idgSerialCard}'/>">
										</td>
									</tr>
								</table>
							</div>
							<table cellspacing="0" cellpadding="0" border="0"
								style="display: block; padding-top: 15px; padding-left: 10px;">
								<tr>
									<td style="width: 45%;">
										&nbsp;
									</td>
									<td>
										<web:url value="/home.shtml" var="urlHomePage"></web:url>
										<input type="button"
											value="<s:text name="web.label.userProfile.RecoverUserInfoInput.Button.Back">Back</s:text>"
											onclick="window.location='${urlHomePage}'" class="iButton" />
										<input type="button" style="margin-left: 20px;"
											value="<s:text name="web.label.userProfile.RecoverUserInfoInput.Button.Continue">Continue</s:text>"
											onclick="doNext();" class="iButton" />
									</td>
								</tr>
							</table>
							<div class="clear"></div>
						</div>
					</div>
				</div>

				<!-- Tro giup -->
				<div class="lgHelp">
					<div class='line'>
						<img src="<web:url value='/images/icons/iconTroGiup.png'/>" />
						&nbsp;&nbsp;
						<b style="font-size: 14px;">Trợ giúp</b>
					</div>
					<div class="line">
						<img src="<web:url value='/images/icons/icon-1.png'/>" />
						&nbsp;&nbsp;
						<a href="<web:url value='/tro-giup-giao-dich/huong-dan-mo-tai-khoan-truc-tuyen.shtml' />">Hướng dẫn mở tài khoản</a>
					</div>
					<div class="line">
						<img src="<web:url value='/images/icons/icon-1.png'/>" />
						&nbsp;&nbsp;
						<a href="<web:url value='/tro-giup-giao-dich/huong-dan-thay-doi-thong-tin-tai-khoan.shtml' />">Hướng dẫn thay đổi thông tin tài khoản</a>
					</div>
					<div class="line">
						<img src="<web:url value='/images/icons/icon-1.png'/>" />
						&nbsp;&nbsp;
						<a href="<web:url value='/tro-giup-giao-dich/huong-dan-dat-lenh-truc-tuyen.shtml' />">Hướng dẫn đặt lệnh trực tuyến</a>
					</div>
				</div>

				<!-- Tong dai ho tro -->
				<div class="tongdaihotro">
					<div class="tongdaihotro_content">
						<img class="left"
							src="<web:url value='/images/icons/icon_help.png'/>" />
						<div class="left" style="margin-left: 20px; line-height: 35px;">
							<b style="font-size: 13px;">Tổng đài hỗ trợ</b>
							<br />
							<span style="font-size: 27px; color: #f39200; font-weight: bold;">1900-54-54-09</span>
						</div>
					</div>
				</div>
			</div>

			<!-- right -->
			<div class="left UsedLogin">
				<div class="UsedLoginTitle">
					Quý khách vui lòng làm theo các bước:
				</div>
				<div style="width: 100%; margin-top: 15px;">
					<img class="left" style="padding-top: 15px; padding-left: 5px;"
						src="<web:url value='/images/icons/hdLayLaiMK-1.png'/>"
						title="Giao dịch trực tuyến" />
					<div class="UsedLoginLine">
						<b style="color: #f39200; font-size: 14px;">Nhập tên truy cập:</b>
						<br />
						<div
							style="margin-top: 10px; font-size: 14px; text-align: justify;">
							Tên dùng để đăng nhập của quý khách.
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div style="width: 100%; margin-top: 15px;">
					<img class="left" style="padding-top: 15px; padding-left: 5px;"
						src="<web:url value='/images/icons/hdLayLaiMK-2.png'/>"
						title="Home Direct" />
					<div class="UsedLoginLine">
						<b style="color: #f39200; font-size: 14px;">Chọn loại tài
							khoản:</b>
						<br />
						<div
							style="margin-top: 10px; font-size: 14px; text-align: justify;">
							Quý khách vui lòng chọn đúng loại tài khoản của mình để VNDIRECT
							có thể hỗ trợ quý khách nhanh chóng hơn.
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div style="width: 100%; margin-top: 15px;">
					<img class="left" style="padding-top: 15px; padding-left: 5px;"
						src="<web:url value='/images/icons/hdLayLaiMK-3.png'/>"
						title="Home Direct" />
					<div class="UsedLoginLine" style="min-height: 50px;">
						<b style="color: #f39200; font-size: 14px;">Click vào "Tiếp
							tục"</b>
						<br />
					</div>
					<div class="clear"></div>
				</div>
				<div style="width: 100%; margin-top: 20px; margin-left: 25px;">
					<a onclick="window.open('http://nangdong.vndirect.com.vn/', '_blank');"><img class="fl"
							style="" src="<web:url value='/images/icons/smartT.png'/>"
							title="Smart T+" /> </a>
				</div>
			</div>
		</div>
	</div>
	<!--End Tab menu  -->
</form>


<script type="text/javascript">
var clickedRadioBtn = null;
$(document)
        .ready(
                function() {
                    $
                            .each(
                                    $('.boxLogin_radioBtn'),
                                    function(i, radioBtn) {
                                        $(radioBtn)
                                                .click(
                                                        function() {
                                                            if ($(this)
                                                                    .find(
                                                                            'span')
                                                                    .hasClass(
                                                                            'inactiveStyledRadioButton')) {
                                                                if (clickedRadioBtn != null) {
                                                                    $(
                                                                            clickedRadioBtn)
                                                                            .find(
                                                                                    'span')
                                                                            .removeClass(
                                                                                    'activeStyledRadioButton')
                                                                            .addClass(
                                                                                    'inactiveStyledRadioButton');
                                                                }
                                                                $(this)
                                                                        .find(
                                                                                'span')
                                                                        .removeClass(
                                                                                'inactiveStyledRadioButton')
                                                                        .addClass(
                                                                                'activeStyledRadioButton');
                                                                $(this)
                                                                        .find(
                                                                                'input')
                                                                        .attr(
                                                                                'checked',
                                                                                true);

                                                                clickedRadioBtn = $(this);
                                                            }
                                                        });
                                    });
                });

function toDisplay() {
    $('#extra').show();
    $('._boxLoginOuter').css('height', '420px');
    $('.formLogin').css('margin-bottom', '205px');
}
function toHidden() {
    $('#extra').hide();
    $('._boxLoginOuter').css('height', '285px');
    $('.formLogin').css('margin-bottom', '60px');
}
function doNext() {
    document.fRecoveUserInfoInput.submit();
    return;
}
</script>
