<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>


<script type="text/javascript">
var CHANGE_ACCOUNT_ACTION = '<s:url action="UserProfileAJAX_ChangeSelectedAccount" namespace="/ajax/user"></s:url>';
var URL_CHANGE_USER_PROFILE = '<s:url action="thong-tin-giao-dich" namespace="/"></s:url>';
var VALIDATE_TRADING_PASSWORD = '<s:url action="UserProfileAJAX_ValidateTradingPassword" namespace="/ajax/user"></s:url>';
</script>

<!-- Begin Content -->
<s:form name="fChangeInternetEmail" id="fChangeInternetEmail" action="thong-tin-giao-dich" namespace="/" method="post">
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
        <!--++ content left-->        
        <div class="content_mypage" id="page2">
            <div class="box_tk">            
                <c:choose>
                    <c:when test="${isFreeUser}">
                        <%-- free user --%>
                    </c:when>
                    <c:otherwise>
		                <span class="padding-left20 padding-right10"><strong><s:text name="web.label.userProfile.select.account"></s:text></strong></span>                
		                <s:select list="model.listAccounts" theme="simple"
		                    listKey="accountNumber"
		                    listValue="accountNumber + ' - ' + userFullName + '[' + accountType + ']'"
		                    name="model.selectedAccountNumber"
		                    id="fChangeInternetEmail_accountNumber">
		                </s:select>
                    </c:otherwise>
                </c:choose>                         
            </div>  
            
            <div class="clear"></div>
            <div class="contentsmall">
                <div id="fChangeInternetEmail_errorMessage"></div>
                <ul class="list">
                    <li class="title">&nbsp;</li>
                    <li>
                        <div class="rowa"><s:text name="web.label.userProfile.myInfo.InternetEmail"></s:text></div>
                        <div class="rowb"><s:property value="model.onlineUser.email"/></div>
                    </li>
                    
                    <li>
                        <div class="rowa"><s:text name="web.label.userProfile.myInfo.InternetEmailNew"/></div>
                        <div class="rowb"><input type="text" name="model.newEmail" id="fChangeInternetEmail_newEmail"  class="input250" /></div>
                    </li>
                                        
                    <li>
                        <div class="rowa"><s:text name="web.label.userProfile.myInfo.TradingPassword"></s:text></div>
                        <div class="rowb"><input class="input250" type="password" name="model.tradingPassword" id="fChangeInternetEmail_tradingPassword"></div>
                    </li>            
                </ul>   
                
                <div class="clear"></div>
                
                <div style="text-align:center">
                    <span class="buttons">
                        <input type="button" value='<s:text name="web.button.userProfile.Confirm"/>' id="fChangeInternetEmail_confirm">
                    </span>
                    <span class="buttons">
                        <input type="button" id="fChangeInternetEmail_change" value='<s:text name="web.button.userProfile.Cancel"></s:text>'>
                    </span>
                </div>                             
            </div>                  
        </div>        
        <!--//end content left-->
        
        <!--++c column-->
        <div class="width315" id="c-column">
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
	<s:hidden name="model.cacheData" id="fChangeInternetEmail_cacheData"></s:hidden>
	<s:hidden name="model.caller" id="fChangeInternetEmail_caller"></s:hidden>
	<s:hidden name="model.userAction" id="fChangeInternetEmail_userAction"></s:hidden>
</s:form>