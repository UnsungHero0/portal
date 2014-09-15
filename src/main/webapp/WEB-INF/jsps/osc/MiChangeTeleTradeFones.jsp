<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>

<script type="text/javascript">
	var CHANGE_ACCOUNT_ACTION = '<s:url action="UserProfileAJAX_ChangeSelectedAccount" namespace="/ajax/user"></s:url>';
	var URL_CHANGE_USER_PROFILE = '<s:url action="thong-tin-co-ban" namespace="/"></s:url>';
	var VALIDATE_TRADING_PASSWORD = '<s:url action="UserProfileAJAX_ValidateTradingPassword" namespace="/ajax/user"></s:url>';
</script>

<s:actionmessage/>
<!-- Begin Content -->
<s:form name="fChangeTeleTradeFones" id="fChangeTeleTradeFones" action="thong-tin-co-ban" namespace="/" method="post">
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
						id="fChangeTeleTradeFones_accountNumber"></s:select>
				</td>
			</tr>
			<tr>
				<td><br/><div id="fChangeTeleTradeFones_errorMessage"></div></td>
			</tr>
		</table>		
		<center><table cellpadding="0" cellspacing="0" width="720"><tr><td>
		<div class="LineTop"> <div class="LineBottom"> <div class="LineLeft"> <div class="LineRight"> <div class="conner1"> <div class="conner2"> <div class="conner3"> <div class="conner4 clearfix">
			<table cellpadding="0" cellspacing="0" width="720">
				<colgroup>
					<col width="40%" align="right"/>
					<col width="60%" align="left" />
				</colgroup>
				<tr style="background:url(<s:url value='/images/img/app_small_tbl_02.gif'/>) repeat-x scroll 0; border:none;">
					<th colspan="2">
						<span style="float:left;font-size:12px"><strong><s:text name="web.button.userProfile.ChangeRegistedServices"></s:text></strong></span>
					</th>
				</tr>
				<tr>
					<td class="datagrid_data1">
						<b><span style="color: #7BB5B5"><s:text name="web.label.userProfile.myInfo.TeleTrading"></s:text></span></b>
					</td>
					<td class="datagrid_data2">
						<div id="fChangeTeleTradeFones_teleTrading"><s:property value="model.userProfile.teleTrading"/></div>
						<s:hidden name="model.userProfile.teleTrading"></s:hidden>
					</td>
				</tr>
				<tr>
					<td class="datagrid_data1"><b><s:text name="web.label.userProfile.myInfo.SMSPhone"></s:text></b></td>
					<td class="datagrid_data2">
						<s:textfield name="model.userProfile.smsFone" id="fChangeTeleTradeFones_smsFone" theme="simple"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="datagrid_data1">
						<b><s:text name="web.label.userProfile.myInfo.TradeFone1"></s:text></b>
					</td>
					<td class="datagrid_data2">
						<s:textfield name="model.userProfile.tradeFone1" id="fChangeTeleTradeFones_tradeFone1" theme="simple"></s:textfield>
					</td>
				</tr>
				<tr>
					<td style="border: 1px solid rgb(176, 176, 176);border-left: none" align="left">
						<b><s:text name="web.label.userProfile.myInfo.TradeFone2"></s:text></b>
					</td>
					<td style="border: 1px solid rgb(176, 176, 176);border-left: none;border-right: none" align="left">
						<s:textfield name="model.userProfile.tradeFone2" id="fChangeTeleTradeFones_tradeFone2" theme="simple"></s:textfield>
					</td>
				</tr>
			</table>
			<table width="720">
				<tr>
					<td>
						<div class="note pn_main clearfix">
							<table width="100%" cellspacing="0" cellpadding="0" border="0">
								<tr>
									<td width="52"><img height="68" width="52" src='<s:url value="/images/front/note2_left.gif" />'></td>
									<td class="ct_note_2">
										<s:text name="web.button.userProfile.ChangeNote1"></s:text>
									</td>
									<td width="3"><img height="68" width="3" src='<s:url value="/images/front/note2_right.gif" />'></td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr height="10"></tr>
				<tr>
					<td align="center" >
						<b><s:text name="web.label.userProfile.myInfo.TradingPassword"></s:text></b>
							<input type="password" name="model.tradingPassword" id="fChangeTeleTradeFones_tradingPassword">
					</td>
				</tr>
				<tr>
					<td align="center" >
					<span class="btn_left_inbox"> <span class="btn_right_inbox"> <span class="btn_center_inbox">
						<input type="button" value='<s:text name="web.button.userProfile.Confirm"/>' id="fChangeTeleTradeFones_confirm">
					</span></span></span>
					&nbsp;
					<span class="btn_left_inbox"> <span class="btn_right_inbox"> <span class="btn_center_inbox">
						<input type="button" id="fChangeTeleTradeFones_change" value='<s:text name="web.button.userProfile.Cancel"></s:text>'>
					</span></span></span>
					</td>
				</tr>
			</table></div></div></div></div></div></div></div></div></td></tr></table></center>
		<br/>
</div></div></td></tr></table>
	<s:hidden name="model.cacheData" id="fChangeTeleTradeFones_cacheData"></s:hidden>
	<s:hidden name="model.caller" id="fChangeTeleTradeFones_caller"></s:hidden>
	<s:hidden name="model.userAction" id="fChangeTeleTradeFones_userAction"></s:hidden>
</s:form>