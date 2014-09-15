<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script type="text/javascript">
	var CHANGE_ACCOUNT_ACTION = '<s:url action="UserProfileAJAX_ChangeSelectedAccount" namespace="/ajax/user"></s:url>';
	var CHANGE_USER_PROFILE = '<s:url action="thong-tin-giao-dich" namespace="/"></s:url>';
	var VIEW_USER_PROFILE = "ViewUserProfile";
	var CHANGE_TELETRADE_FONES_FW = "ChangeTeleTradeFones";
	var CHANGE_TELETRADE_PASSWORD_FW = "ChangeTeleTradePassword";
	var CHANGE_USER_PROFILE_FW = "ChangeUserProfile";
	var CHANGE_INTERNET_EMAIL_FW = "ChangeInternetEmail";
	var Messages_Commons_Buttons_Yes = '<s:text name="web.button.userProfile.Yes"/>';
	var Messages_Commons_Buttons_No = '<s:text name="web.button.userProfile.No"/>';
	$(document).ready(function() {
		$("#feedBack").click(function() {
			return false;
		});
	});
</script>

<s:form id="fUserProfile" name="fUserProfile" action="thong-tin-co-ban"
	namespace="/" method="post">


	<div id="content_ttpt">
		<ul class="ui-tabs-nav tab_ttpt" id="container-thongtincanhan">
			<c:choose>
				<c:when test="${isFreeUser}">
					<li class=""><a
						href="<web:url value="/thong-tin-co-ban-tai-khoan-mien-phi.shtml"/>"><label
							class="icon_active"></label> <s:text name="myPage.infor.basic" /></a></li>
				</c:when>
				<c:otherwise>
					<li class=""><a
						href="<web:url value="/thong-tin-co-ban.shtml"/>"><label
							class="icon_active"></label> <s:text name="myPage.infor.basic" /></a></li>
				</c:otherwise>
			</c:choose>

			<li class="ui-tabs-selected"><a
				href="<web:url value="/thong-tin-giao-dich.shtml"/>"><label
					class="icon_active"></label> <s:text name="myPage.infor.trading" /></a></li>
		</ul>

		<div class="clear"></div>

		<div id="tab-1" class="ui-tabs-container" style="">
			<!--++ content left-->
			<!-- Thong tin giao dich -->
			<div class="content_mypage" id="page2">
				<div class="box_tk">
					<c:choose>
						<c:when test="${isFreeUser}">
							<%-- free user --%>
						</c:when>
						<c:otherwise>
							<span class="padding-left20 padding-right10"><strong><s:text
										name="web.label.userProfile.select.account"></s:text></strong></span>
							<s:select list="model.listAccounts" theme="simple"
								listKey="accountNumber"
								listValue="accountNumber + ' - ' + userFullName + '[' + accountType + ']'"
								name="model.selectedAccountNumber"
								id="fUserProfile_accountNumber">
							</s:select>
						</c:otherwise>
					</c:choose>
					<!-- remove this button by PORTAL-652 -->
					<%-- <span class="buttons"> <input
						onclick="javascript:doChangeInternetEmail();" type="button"
						value="<s:text name='user.myPage.changeTransInfo'>Thay đổi thông tin giao dịch</s:text>">
					</span> --%>
				</div>

				<div class="clear"></div>

				<div class="contentsmall">
					<ul class="list">
						<li class="title">&nbsp;</li>
						<li>
							<div class="rowa">
								<s:text name="web.label.userProfile.myInfo.FloorTrading"></s:text>
							</div>
							<div class="rowb">
								<s:if test='%{"Y"== model.userProfile.floorTrading}'>
									<s:text name="web.button.userProfile.Yes"></s:text>
								</s:if>
								<s:else>
									<s:text name="web.button.userProfile.No"></s:text>
								</s:else>
							</div>
						</li>

						<li>
							<div class="rowa">
								<s:text name="web.label.userProfile.myInfo.TeleTrading"></s:text>
							</div>
							<div class="rowb">
								<s:if test='%{"Y"== model.userProfile.floorTrading}'>
									<s:text name="web.button.userProfile.Yes"></s:text>
								</s:if>
								<s:else>
									<s:text name="web.button.userProfile.No"></s:text>
								</s:else>
							</div>
						</li>

						<s:if test='%{"Y"== model.userProfile.floorTrading}'>
							<div id="fUserProfile_teleTrading_detail">
								<li>
									<div class="rowa">
										<s:text name="web.label.userProfile.myInfo.SMSPhone"></s:text>
									</div>
									<div class="rowb">
										<div id="fUserProfile_smsFone">
											<s:property value="model.userProfile.smsFone" />
										</div>
									</div>
								</li>

								<li>
									<div class="rowa">
										<s:text name="web.label.userProfile.myInfo.TradeFone1"></s:text>
									</div>
									<div class="rowb">
										<div id="fUserProperties_tradeFone1">
											<s:property value="model.userProfile.tradeFone1" />
										</div>
									</div>
								</li>

								<li>
									<div class="rowa">
										<s:text name="web.label.userProfile.myInfo.TradeFone2"></s:text>
									</div>
									<div class="rowb">
										<div id="fUserProfile_tradeFone2">
											<s:property value="model.userProfile.tradeFone2" />
										</div>
									</div>
								</li>
								<li>
									<div class="rowa">
										<s:text name="web.label.userProfile.myInfo.TeleTradePassword"></s:text>
									</div>
									<div class="rowb">****</div>
								</li>
							</div>
						</s:if>

						<li>
							<div class="rowa">
								<s:text name="web.label.userProfile.myInfo.OnlineTrading"></s:text>
							</div>
							<div class="rowb">
								<s:if test='%{"Y"== model.userProfile.onlineTrading}'>
									<s:text name="web.button.userProfile.Yes"></s:text>
								</s:if>
								<s:else>
									<s:text name="web.button.userProfile.No"></s:text>
								</s:else>
							</div>
						</li>

						<s:if test='%{"Y"== model.userProfile.onlineTrading}'>
							<div id="fUserProfile_onlineTrading_detail">
								<li>
									<div class="rowa">
										<s:text name="web.label.userProfile.myInfo.UserName"></s:text>
									</div>
									<div class="rowb">
										<div id="fUserProfile_userName">
											<s:property value="model.onlineUser.userName" />
										</div>
									</div>
								</li>
								<li>
									<div class="rowa">
										<s:text name="web.label.userProfile.myInfo.Password"></s:text>
									</div>
									<div class="rowb">******</div>
								</li>

								<li>
									<div class="rowa">
										<s:text name="web.label.userProfile.myInfo.InternetEmail"></s:text>
									</div>
									<div class="rowb">
										<div id="fUserProfile_email">
											<s:property value="model.onlineUser.email" />
										</div>
									</div>
								</li>
							</div>
						</s:if>

					</ul>
					<div class="clear"></div>

					<div style="text-align: center">
						<span class="buttons"> <!--  --> <input
							onclick="window.location.href='<web:url value="/thay-doi-mat-khau.shtml" />'"
							type="button"
							value="<s:text name='user.myPage.changeOnlinePassword'>Thay đổi mật khẩu giao dịch qua internet</s:text>">
						</span>
					</div>
				</div>
			</div>

			<!--//end content left-->
			<!--++c column-->
			<div class="width315" id="c-column">
				<div class="boxcontentcolumn">
					<img src='<s:url value="/images/thumbs/img_qc.png" />' />
				</div>
				<div class="boxcontentcolumn">
					<ul>
						<li><span class="icon_question_fast left"></span>
							<div class="box_text_tienich line">
								<a href="<web:url value="/tro-giup/hoi-dap-huong-dan.shtml"/>"><s:text
										name="myPage.leftColumn.qA"></s:text></a>
							</div></li>
						<li><span class="icon_guide left "></span>
							<div class="line box_text_tienich">
								<a href="<web:url value="/tro-giup/hoi-dap-huong-dan.shtml"/>"><s:text
										name="myPage.leftColumn.guide"></s:text></a>
							</div></li>
						<li><span class="icon_logo_vnd left"></span>
							<div class="box_text_tienich">
								<a href="<web:url value="/vndirect/san-pham-dich-vu.shtml"/>"><s:text
										name="myPage.leftColumn.newsVnds"></s:text></a>
							</div></li>
					</ul>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>

	<s:hidden name="model.cacheData" id="fUserProfile_cacheData"></s:hidden>
	<input type="hidden" name="model.caller" value=""
		id="fUserProfile_caller" />
	<input type="hidden" name="model.userAction" value=""
		id="fUserProfile_userAction" />
</s:form>