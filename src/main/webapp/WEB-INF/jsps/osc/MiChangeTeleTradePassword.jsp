<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>


<script type="text/javascript">
	var CHANGE_ACCOUNT_ACTION = '<s:url action="UserProfileAJAX_ChangeSelectedAccount" namespace="/ajax/user"></s:url>';
	var URL_CHANGE_USER_PROFILE = '<s:url action="thong-tin-co-ban" namespace="/"></s:url>';
	var VALIDATE_TRADING_PASSWORD = '<s:url action="UserProfileAJAX_ValidateTradingPassword" namespace="/ajax/user"></s:url>';

	var TELE_TRADE_PW_INVALID = '<s:text name="web.error.userProfile.tele-trade-pw-invalid"></s:text>';
	var RETYPE_TELE_TRADE_PW_INVALID = '<s:text name="web.error.userProfile.retype-tele-trade-pw-invalid"></s:text>';
</script>
<s:actionmessage/>
<!-- Begin Content -->
<s:form name="fChangeTeleTradePassword" id="fChangeTeleTradePassword" action="thong-tin-co-ban" namespace="/" method="post">
<table width="750"><tr><td><div class="tab clearfix">
	<div class="tab_infor">
		<a href='<web:url value="/thong-tin-co-ban.shtml"/>' class="select_tab"><b><s:text name="web.label.userProfile.tab.myInfo"></s:text></b> </a>
	</div>
	<div style="border: 1px solid #616D7E;border-top: none;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr height="20"></tr>
			<tr>
				<td>
					&nbsp;&nbsp;&nbsp;<span style="color:#028"><strong><s:text name="web.label.userProfile.select.account"></s:text></strong>&nbsp;</span>
					<s:select list="model.listAccounts" theme="simple"
						listKey="accountNumber"
						listValue="accountNumber + ' - ' + userFullName + '[' + accountType + ']'"
						name="model.selectedAccountNumber"
						id="fChangeTeleTradePassword_accountNumber"></s:select>
				</td>
			</tr>
			<tr>
				<td><br/><div id="fChangeTeleTradePassword_errorMessage"></div></td>
			</tr>
		</table>		
		<center><table cellpadding="0" cellspacing="0" width="720"><tr><td>
		<div class="LineTop"> <div class="LineBottom"> <div class="LineLeft"> <div class="LineRight"> <div class="conner1"> <div class="conner2"> <div class="conner3"> <div class="conner4 clearfix">
			<table cellpadding="0" cellspacing="0" width="720">
				<colgroup>
					<col width="40%" align="right"/>
					<col width="60%" align="left" />
				</colgroup>
				<tr style="background-color:#EEEEEE; border:none;">
					<th colspan="2">
						<span style="float:left;font-size:12px"><strong><s:text name="web.button.userProfile.ChangeRegistedServices"></s:text></strong></span>
					</th>
				</tr>
				<tr>
					<td class="datagrid_data1">
						<b><span style="color: #7BB5B5"><s:text name="web.label.userProfile.myInfo.TeleTrading"></s:text></span></b>
					</td>
					<td class="datagrid_data2">
						<div id="fChangeTeleTradePassword_teleTrading"><s:property value="model.userProfile.teleTrading"/></div>
						<s:hidden name="model.userProfile.teleTrading"></s:hidden>
					</td>
				</tr>
				<tr>
					<td class="datagrid_data1">
						<b><s:text name="web.label.userProfile.myInfo.TradeFone1"></s:text></b>
					</td>
					<td class="datagrid_data2">
						<div id="fChangeTeleTradePassword_tradeFone1"><s:property value="model.userProfile.tradeFone1"/></div>
						<s:hidden name="model.userProfile.tradeFone1"></s:hidden>
					</td>
				</tr>
				<tr>
					<td class="datagrid_data1">
						<b><s:text name="web.label.userProfile.myInfo.TradeFone2"></s:text></b>
					</td>
					<td class="datagrid_data2">
						<div id="fChangeTeleTradePassword_tradeFone2"><s:property value="model.userProfile.tradeFone2"/></div>
						<s:hidden name="model.userProfile.tradeFone2"></s:hidden>
					</td>
				</tr>
				<tr>
					<td class="datagrid_data1">
						<b><s:text name="web.label.userProfile.myInfo.OldTelePassword"></s:text></b>
					</td>
					<td class="datagrid_data2">
						<input type="password" id="fChangeTeleTradePassword_telePasswordOld" name="model.telePasswordOld" maxlength="4">
					</td>
				</tr>
				<tr>
					<td class="datagrid_data1">
						<b><s:text name="web.label.userProfile.myInfo.NewTelePassword"></s:text></b>
					</td>
					<td class="datagrid_data2">
						<input type="password" name="model.telePasswordNew" id="fChangeTeleTradePassword_telePasswordNew" maxlength="4">
					</td>
				</tr>
				<tr>
					<td style="border: 1px solid rgb(176, 176, 176);border-left: none" align="left">
						<b><s:text name="web.label.userProfile.myInfo.ReTypeOldTelePassword"></s:text></b>
					</td>
					<td style="border: 1px solid rgb(176, 176, 176);border-left: none;border-right: none" align="left">
						<input type="password" name="model.telePasswordReType" id="fChangeTeleTradePassword_telePasswordReType" maxlength="4">
					</td>
				</tr>
			</table>
			<table width="720" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<div class="note pn_main clearfix">
							<table width="100%" cellspacing="0" cellpadding="0" border="0">
								<tr>
									<td width="52"><img height="68" width="52" src='<s:url value="/images/front/note2_left.gif" />'></td>
									<td class="ct_note_2">
										<s:text name="web.button.userProfile.ChangeNote2"></s:text>
									</td>
									<td width="3"><img height="68" width="3" src='<s:url value="/images/front/note2_right.gif" />'></td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr height="10"></tr>
				<tr>
					<td align="center">
						<b><s:text name="web.label.userProfile.myInfo.TradingPassword"></s:text></b>
						<input type="password" name="model.tradingPassword" id="fChangeTeleTradePassword_tradingPassword">
					</td>
				</tr>
				<tr>
					<td align="center">
						<span class="btn_left_inbox"> <span class="btn_right_inbox"> <span class="btn_center_inbox">
							<input type="button" value='<s:text name="web.button.userProfile.Confirm"/>' id="fChangeTeleTradePassword_confirm">
						</span></span></span>
						&nbsp;
						<span class="btn_left_inbox"> <span class="btn_right_inbox"> <span class="btn_center_inbox">
							<input type="button" id="fChangeTeleTradePassword_change" value='<s:text name="web.button.userProfile.Cancel"></s:text>'>
						</span></span></span>
					</td>
				</tr>
			</table></div></div></div></div></div></div></div></div></td></tr></table></center>
		<br/>
</div></div></td></tr></table>
	<s:hidden name="model.cacheData" id="fChangeTeleTradePassword_cacheData"></s:hidden>
	<s:hidden name="model.caller" id="fChangeTeleTradePassword_caller"></s:hidden>
	<s:hidden name="model.userAction" id="fChangeTeleTradePassword_userAction"></s:hidden>
</s:form>