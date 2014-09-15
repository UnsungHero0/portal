<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>

 <c:choose>
     <c:when test="${isFreeUser}">
         <script type="text/javascript">
            // check if account free user redirect to account free
            window.location.href = "<web:url value='/thong-tin-co-ban-tai-khoan-mien-phi.shtml'/>";
        </script>
     </c:when>
     <c:otherwise>
     </c:otherwise>
 </c:choose>  


<script type="text/javascript">
	var CHANGE_ACCOUNT_ACTION = '<s:url action="UserProfileAJAX_ChangeSelectedAccount" namespace="/ajax/user"></s:url>';
	var CHANGE_USER_PROFILE = '<s:url action="thong-tin-co-ban" namespace="/"></s:url>';
	var VIEW_USER_PROFILE = "ViewUserProfile";
	var CHANGE_TELETRADE_FONES_FW = "ChangeTeleTradeFones";
	var CHANGE_TELETRADE_PASSWORD_FW = "ChangeTeleTradePassword";
	var CHANGE_USER_PROFILE_FW = "ChangeUserProfile";
	var CHANGE_INTERNET_EMAIL_FW = "ChangeInternetEmail";
	var Messages_Commons_Buttons_Yes = '<s:text name="web.button.userProfile.Yes"/>';
	var Messages_Commons_Buttons_No = '<s:text name="web.button.userProfile.No"/>';
    
	var page = 1;
	$(document).ready(function() {
        //page = '<s:property value="page"/>';
        //if (isNaN(page) || page == 'undefined') {
        page = 1;
        //}
                        
        display();
    })
	
	
	function display() {
        $('#page1').hide();
        $('#page2').hide();
        $('#page' + page).show();
        for (i =1; i<=2;i++) {
            if (i == page) {
                $('#linkpage' +page).addClass('');
            } else {
                $('#linkpage' +i).removeClass('ui-tabs-selected');                             
            }
        }
    }
	
	function gotoPage(newPage) {        
        page = newPage;
        display();
    }
</script>

<s:form id="fUserProfile" name="fUserProfile" action="thong-tin-co-ban" namespace="/" method="post">


<div id="content_ttpt"> 
                                           
	<ul class="ui-tabs-nav tab_ttpt" id="container-thongtincanhan">
	    <li class="ui-tabs-selected"><a href="<web:url value="/thong-tin-co-ban.shtml"/>"><label class="icon_active"></label><s:text name="myPage.infor.basic"/></a></li>
	    <li class=""><a href="<web:url value="/thong-tin-giao-dich.shtml"/>"><label class="icon_active"></label><s:text name="myPage.infor.trading"/></a></li>
	    <%--
	    <li class=""><a id="myCustomTrigger" href="<web:url value="/user/help.shtml"/>"><label class="icon_active"></label>Gửi phản hồi của bạn tới VNDIRECT</a></li>
	    <li class=""><a href="#tab-3" class=""><label class="icon_active"></label> Thông tin nâng cao </a></li>
	    <li class=""><a href="#tab-4" class=""><label class="icon_active"></label> Quản lý dịch vụ</a></li>
        --%>
	</ul>
    
    <div class="clear"></div>
    
    <div id="tab-1" class="ui-tabs-container" style="">
    
        <!--++ content left-->
        <!-- Thong tin co ban -->
        <div class="content_mypage" id="page1">
            <div style="border:1px solid #FF6600; background-color: #FFFF80; padding: 5px;">
            <s:text name="web.message.userProfile.Warning"></s:text></div>            
            
            <div class="box_tk">
                <span class="padding-left20 padding-right10"><strong><s:text name="web.label.userProfile.select.account"></s:text></strong></span>
                <s:select list="model.listAccounts" theme="simple"
                    listKey="accountNumber"
                    listValue="accountNumber + ' - ' + userFullName + '[' + accountType + ']'"
                    name="model.selectedAccountNumber"
                    id="fUserProfile_accountNumber">
                </s:select>
                <%--<span class="buttons"><input type="button" value="Thay đổi thông tin cơ bản"></span>--%>
            </div>
            
            <div class="clear"></div>
            
            <div class="contentsmall">
                <ul class="list">
                    <li class="title">&nbsp;</li>
                    <li>
                        <div class="rowa"><s:text name="web.label.userProfile.myInfo.FullName"></s:text></div>
                        <div class="rowb"><s:property value="model.userProfile.fullName"/></div>
                    </li>
                    <li>
                        <div class="rowa"><s:text name="web.label.userProfile.myInfo.AccountNumber"></s:text></div>
                        <div class="rowb"><s:property value="model.userProfile.accountNumber"/></div>
                    </li>
                    <li>
                        <div class="rowa"><s:text name="web.label.userProfile.myInfo.CustodyCode"></s:text></div>
                        <div class="rowb"><s:property value="model.userProfile.custodyCode"/></div>
                    </li>
                    <li>
                        <div class="rowa"><s:text name="web.label.userProfile.myInfo.MailingAddress"></s:text></div>
                        <div class="rowb"><s:property value="model.userProfile.address"/></div>
                    </li>
                    <li>
                        <div class="rowa"><s:text name="web.label.userProfile.myInfo.HomePhone"></s:text></div>
                        <div class="rowb"><s:property value="model.userProfile.homeFone"/></div>
                    </li>
                    <li>
                        <div class="rowa"><s:text name="web.label.userProfile.myInfo.Mobiphone"></s:text></div>
                        <div class="rowb"><s:property value="model.userProfile.mobileFone"/></div>
                    </li>
                </ul>
            </div>
        </div>
                      
        <!--//end content left-->
        
        
        <!--++c column-->
        <div class="width315" id="c-column" style="margin-top:0px;">
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
        <div class="clear"></div>
        <!--//c column-->
    </div>
</div>







<!-- ------------------------------------------------------------------------------------------- -->

<%--<table width="750"><tr><td><div class="tab clearfix">
	<div class="tab_infor">
		<a href="#" class="select_tab"><b><s:text name="web.label.userProfile.tab.myInfo"></s:text></b> </a>
	</div>
		
	<div style="border: 1px solid #616D7E;border-top: none;">
	<center><table cellpadding="0" cellspacing="0" width="720">
		<tr height="20">
			<td>
			
			</td>
		</tr>
		<tr>
			<td>
			<div style="border:1px solid #FF6600; background-color: #FFFF80; padding: 5px;"><s:text name="web.message.userProfile.Warning"></s:text></div>
			</td>
		</tr>
		</table>
	</center>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr height="20"></tr>
			<tr>
				<td>
				&nbsp;&nbsp;&nbsp;<span style="color:#028"><strong><s:text name="web.label.userProfile.select.account"></s:text></strong>&nbsp;</span>
				<s:select list="model.listAccounts" theme="simple"
					listKey="accountNumber"
					listValue="accountNumber + ' - ' + userFullName + '[' + accountType + ']'"
					name="model.selectedAccountNumber"
					id="fUserProfile_accountNumber"></s:select>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		<center><table cellpadding="0" cellspacing="0" width="720"><tr><td>
		<div class="LineTop"> <div class="LineBottom"> <div class="LineLeft"> <div class="LineRight"> <div class="conner1"> <div class="conner2"> <div class="conner3"> <div class="conner4 clearfix">
			<table cellpadding="0" cellspacing="0" width="100%">
					<colgroup>
						<col width="40%" />
						<col width="50%" />
						<col width="10%" align="center" />
					</colgroup>
					<tr style="background:url(<s:url value='/images/img/app_small_tbl_02.gif'/>) repeat-x scroll 0; border:none;">
						<th colspan="2" style="border-right: 1px solid rgb(176, 176, 176)">
							<span style="float:left;font-size:13px;font-weight: bold;"><s:text name="web.label.userProfile.myInfo.AccountInfoTitle"></s:text></span>
						</th>
						<th >&nbsp;
							
							[<a href="javascript:doChangeUserProfile();" class="blue"><s:text name="web.button.userProfile.Change"></s:text></a>]
							 
						</th>
					</tr>
					<tr>
						<td class="datagrid_data1">
							<b><s:text name="web.label.userProfile.myInfo.FullName"></s:text></b>
						</td>
						<td class="datagrid_data1">
							<div id="fUserProfile_fullName"><s:property value="model.userProfile.fullName"/></div>
						</td>
						<td class="datagrid_data2">
						</td>
					</tr>
					<tr>
						<td class="datagrid_data1">
							<b><s:text name="web.label.userProfile.myInfo.AccountNumber"></s:text></b>
						</td>
						<td class="datagrid_data1">
							<div id="fUserProfile_selectedAccountNumber"><s:property value="model.userProfile.accountNumber"/></div>
						</td>
						<td class="datagrid_data2">
						</td>
					</tr>
					<tr>
						<td class="datagrid_data1">
							<b><s:text name="web.label.userProfile.myInfo.CustodyCode"></s:text></b>
						</td>
						<td class="datagrid_data1">
						<div id="fUserProfile_custodyCode"><s:property value="model.userProfile.custodyCode"/></div>
						</td>
						<td class="datagrid_data2">
						</td>
					</tr>
					
					<tr>
						<td class="datagrid_data1">
							<b><s:text name="web.label.userProfile.myInfo.MailingAddress"></s:text></b>
						</td>
						<td class="datagrid_data1">
							<div id="fUserProfile_address"><s:property value="model.userProfile.address"/></div>
						</td>
						<td class="datagrid_data2" align="center">
						</td>
					</tr>
					<tr>
						<td class="datagrid_data1">
							<b><s:text name="web.label.userProfile.myInfo.HomePhone"></s:text></b>
						</td>
						<td class="datagrid_data1">
							<div id="fUserProfile_homeFone"><s:property value="model.userProfile.homeFone"/></div>
						</td>
						<td class="datagrid_data2" align="center">
							
						</td>
					</tr>
					
					<tr>
						<td class="datagrid_data1">
							<b><s:text name="web.label.userProfile.myInfo.Mobiphone"></s:text></b>
						</td>
						<td class="datagrid_data1">
							<div id="fUserProfile_mobileFone"><s:property value="model.userProfile.mobileFone"/></div>
						</td>
						<td class="datagrid_data2" align="center">
						</td>
					</tr>
					
					<tr>
						<td class="datagrid_data1">
							<b><s:text name="web.label.userProfile.myInfo.TradingConfirmationStatement"></s:text></b>
						</td>
						<td class="datagrid_data1">
							<div id="fUserProfile_emailConfirm"><s:property value="model.userProfile.emailConfirm"/></div>
						</td>
						<td class="datagrid_data2" align="center">
						</td>
					</tr>
					<tr>
						<td colspan="3"></td>
					</tr>
					
					<tr>
						<td colspan="3" style="border-top: 1px solid rgb(176, 176, 176)">
							<span style="float:left;font-size:13px"><strong><s:text name="web.label.userProfile.myInfo.ServiceRegisted"></s:text></strong></span>
						</td>
					</tr>
					<tr>
						<td  colspan="3"></td>
					</tr>
					<tr>
						<td class="datagrid_data1">
							<b><span style="color: #7BB5B5"><s:text name="web.label.userProfile.myInfo.FloorTrading"></s:text></span></b>
						</td>
						<td class="datagrid_data1">
							<div id="fUserProfile_floorTrading">
								<s:if test='%{"Y"== model.userProfile.floorTrading}'>
									<s:text name="web.button.userProfile.Yes"></s:text>
								</s:if>
								<s:else>
									<s:text name="web.button.userProfile.No"></s:text>
								</s:else>
							</div>
						</td>
						<td class="datagrid_data2">
							
						</td>
					</tr>
					<tr>
						<td class="datagrid_data1">
							<b><span style="color: #7BB5B5"><s:text name="web.label.userProfile.myInfo.TeleTrading"></s:text></span></b>
						</td>
						<td class="datagrid_data1">
							<div id="fUserProfile_teleTrading">
								<s:if test='%{"Y"== model.userProfile.teleTrading}'>
									<s:text name="web.button.userProfile.Yes"></s:text>
								</s:if>
								<s:else>
									<s:text name="web.button.userProfile.No"></s:text>
								</s:else>
							</div>
						</td>
						<td class="datagrid_data2">
							
						</td>
					</tr>
					<tr><td colspan="3"><div id="fUserProfile_teleTrading_detail" <s:if test='%{model.userProfile.teleTrading != "Y"}'>style="display: none"</s:if>>
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<colgroup>
							<col width="40%" />
							<col width="50%" />
							<col width="10%" align="center" />
						</colgroup>
						<tr>
							<td class="datagrid_data1">
								<b><s:text name="web.label.userProfile.myInfo.SMSPhone"></s:text></b>
							</td>
							<td class="datagrid_data1">
								<div id="fUserProfile_smsFone"><s:property value="model.userProfile.smsFone"/></div>
							</td>
							<td class="datagrid_data2" align="center">&nbsp;
								
								[ <a href="javascript:doChangeTeleTradeFone();" class="blue"><s:text name="web.button.userProfile.Change"></s:text></a> ]
								 
							</td>
						</tr>
						
						<tr>
							<td class="datagrid_data1">
								<b><s:text name="web.label.userProfile.myInfo.TradeFone1"></s:text></b>
							</td>
							<td class="datagrid_data1">
								<div id="fUserProperties_tradeFone1"><s:property value="model.userProfile.tradeFone1"/></div>
							</td>
							<td class="datagrid_data2" align="center">&nbsp;
								
								[ <a href="javascript:doChangeTeleTradeFone();" class="blue"><s:text name="web.button.userProfile.Change"></s:text></a> ]
								 
							</td>
						</tr>
						<tr>
							<td class="datagrid_data1">
								<b><s:text name="web.label.userProfile.myInfo.TradeFone2"></s:text></b>
							</td>
							<td class="datagrid_data1">
								<div id="fUserProfile_tradeFone2"><s:property value="model.userProfile.tradeFone2"/></div>
							</td>
							<td class="datagrid_data2" align="center">&nbsp;
								
								[ <a href="javascript:doChangeTeleTradeFone();" class="blue"><s:text name="web.button.userProfile.Change"></s:text></a> ]
								 
							</td>
						</tr>
						<tr>
							<td class="datagrid_data1">
								<b><s:text name="web.label.userProfile.myInfo.TeleTradePassword"></s:text></b>
							</td>
							<td class="datagrid_data1">
								****
							</td>
							<td class="datagrid_data2" align="center">&nbsp;
								
								[ <a href="javascript:doChangeTeleTradePassword();" class="blue"><s:text name="web.button.userProfile.Change"></s:text></a> ]
								 
							</td>
						</tr>
					</table>
					</div></td></tr>
					<tr>
						<td class="datagrid_data1">
							<b><span style="color: #7BB5B5"><s:text name="web.label.userProfile.myInfo.OnlineTrading"></s:text></span></b>
						</td>
						<td class="datagrid_data1">
							<div id="fUserProfile_onlineTrading">
								<s:if test='%{"Y" == model.userProfile.onlineTrading}'>
									<s:text name="web.button.userProfile.Yes"></s:text>
								</s:if>
								<s:else>
									<s:text name="web.button.userProfile.No"></s:text>
								</s:else>
							</div>
						</td>
						<td class="datagrid_data2">
							
						</td>
					</tr>
				
					<tr><td colspan="3"><div id="fUserProfile_onlineTrading_detail" <s:if test='%{model.userProfile.onlineTrading != "Y"}'>style="display: none"</s:if>>
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<colgroup>
							<col width="40%" />
							<col width="50%" />
							<col width="10%" align="center" />
						</colgroup>
						<tr>
							<td class="datagrid_data1">
								<b><s:text name="web.label.userProfile.myInfo.UserName"></s:text></b>
							</td>
							<td class="datagrid_data1">
								<div id="fUserProfile_userName"><s:property value="model.onlineUser.userName"/></div>
							</td>
							<td class="datagrid_data2" align="center">
							</td>
						</tr>
						<tr>
							<td class="datagrid_data1">
								<b><s:text name="web.label.userProfile.myInfo.Password"></s:text></b>
							</td>
							<td class="datagrid_data1">
								******
							</td>
							<td class="datagrid_data2" align="center">&nbsp;
								[ <a href='<web:url value="/user/viewChangePassword.shtml"></web:url>' class="blue"><s:text name="web.button.userProfile.Change"></s:text></a> ]
							</td>
						</tr>
						
						<tr>
							<td class="datagrid_data1">
								<b><s:text name="web.label.userProfile.myInfo.InternetEmail"></s:text></b>
							</td>
							<td class="datagrid_data1">
								<div id="fUserProfile_email"><s:property value="model.onlineUser.email"/></div>
							</td>
							<td class="datagrid_data2" align="center">&nbsp;
								[ <a href="javascript:doChangeInternetEmail();" class="blue"><s:text name="web.button.userProfile.Change"></s:text></a> ]
							</td>
						</tr>
					</table>
				</div></td></tr>
			</table>
			</div></div></div></div></div></div></div></div></td></tr></table></center>
		<br/>
	</div></div>
</td></tr>
</table>--%>
	<s:hidden name="model.cacheData" id="fUserProfile_cacheData"></s:hidden>
	<input type="hidden" name="model.caller" value="" id="fUserProfile_caller"/>
	<input type="hidden" name="model.userAction" value="" id="fUserProfile_userAction"/>
</s:form>