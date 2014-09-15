<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>

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
        <li class="ui-tabs-selected"><a href="<web:url value="/thong-tin-co-ban-tai-khoan-mien-phi.shtml"/>"><label class="icon_active"></label><s:text name="myPage.infor.basic"/></a></li>
        <li class=""><a href="<web:url value="/thong-tin-giao-dich.shtml"/>"><label class="icon_active"></label><s:text name="myPage.infor.trading"/></a></li>
    </ul>
    
    <div class="clear"></div>
    
    <div id="tab-1" class="ui-tabs-container" style="">
    
        <!--++ content left-->
        <!-- Thong tin co ban -->
        <div class="content_mypage" id="page1">
            <div style="border:1px solid #FF6600; background-color: #FFFF80; padding: 5px;">
            <s:text name="web.message.userProfile.Warning"></s:text></div>            
            
            <div class="box_tk">

            </div>
            
            <div class="clear"></div>
            
            <div class="contentsmall">
                <ul class="list">
                    <li class="title">&nbsp;</li>
                    <li>
                        <div class="rowa"><s:text name="web.label.userProfile.myInfo.FullName"></s:text></div>
                        <div class="rowb"><web:webUser code="fullName" /></div>
                    </li>
                    <li>
                        <div class="rowa"><s:text name="web.label.userProfile.myInfo.AccountNumber"></s:text></div>
                        <div class="rowb">&nbsp;</div>
                    </li>
                    <li>
                        <div class="rowa"><s:text name="web.label.userProfile.myInfo.CustodyCode"></s:text></div>
                        <div class="rowb">&nbsp;</div>
                    </li>
                    <li>
                        <div class="rowa"><s:text name="web.label.userProfile.myInfo.MailingAddress"></s:text></div>
                        <div class="rowb">&nbsp;</div>
                    </li>
                    <li>
                        <div class="rowa"><s:text name="web.label.userProfile.myInfo.HomePhone"></s:text></div>
                        <div class="rowb">&nbsp;</div>
                    </li>
                    <li>
                        <div class="rowa"><s:text name="web.label.userProfile.myInfo.Mobiphone"></s:text></div>
                        <div class="rowb">&nbsp;</div>
                    </li>
                </ul>
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
    <s:hidden name="model.cacheData" id="fUserProfile_cacheData"></s:hidden>
    <input type="hidden" name="model.caller" value="" id="fUserProfile_caller"/>
    <input type="hidden" name="model.userAction" value="" id="fUserProfile_userAction"/>
</s:form>