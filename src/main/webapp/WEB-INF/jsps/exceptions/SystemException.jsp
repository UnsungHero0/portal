<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.io.StringWriter"%>
<%@page import="java.io.PrintWriter"%>

<%
	Exception exception = (Exception) request.getAttribute("exception");
	StringWriter strWriter = new StringWriter();
	if(exception != null)
  		exception.printStackTrace(new PrintWriter(strWriter));
%>

<script type="text/javascript">
    $(document).ready(function(){
        $('#keywordInputPageNotFound').keypress(function(e) {
            if (e.keyCode == 13) {
                if($('#keywordInputPageNotFound').val() != "")
                   $('#formSearchPageNotFound').submit();
                return false;                           
            }
        });
    });
    
    function doSearchNewsPageNotFound() {
        if ($('#keywordInputPageNotFound').val() != "") {
            $('#formSearchPageNotFound').submit();
        }
    }
    function showhideError(){
        $('#errorLog').toggle();
    }
</script>

<script type="text/javascript">
    /* live-chat script */
    var lhnAccountN = 17791;
    var lhnButtonN = 5283;
    var lhnVersion = 5.3;
    var lhnJsHost = (("https:" == document.location.protocol) ? "https://" : "http://");
    var lhnInviteEnabled = 1;
    var lhnInviteChime = 0;
    var lhnWindowN = 0;
    var lhnDepartmentN = 0;
    var lhnCustomInvitation = '';
    var lhnCustom1 = '';
    var lhnCustom2 = '';
    var lhnCustom3 = '';
    var lhnTrackingEnabled = 't';
    var lhnScriptSrc = lhnJsHost + 'www.livehelpnow.net/lhn/scripts/livehelpnow.aspx?lhnid=' + lhnAccountN + '&iv=' + lhnInviteEnabled + '&d=' + lhnDepartmentN + '&ver=' + lhnVersion + '&rnd=' + Math.random();
    var lhnScript = document.createElement("script"); lhnScript.type = "text/javascript";lhnScript.src = lhnScriptSrc;
    if (window.addEventListener) {
        window.addEventListener('load', function () { document.getElementById('lhnContainer').appendChild(lhnScript); }, false);
    }
    else if (window.attachEvent) {
        window.attachEvent('onload', function () { document.getElementById('lhnContainer').appendChild(lhnScript); });
    }
    /* end live-chat script */

    $(document).ready(function(){
        $('#icon-supmenuft').hover(function(){
            $(this).removeClass('icon-supmenuft');
            $(this).addClass('icon-supmenuftHover');
        }, function(){
            $(this).removeClass('icon-supmenuftHover');
            $(this).addClass('icon-supmenuft');
        });             
    });
</script>

<div id="content_ttpt" style="width: 650px;">
    <div class="content_small">
        <div style="display: block; margin-top: 50px; background:none repeat scroll 0 0 #EEEEEE" id="pageNotFound" class="content_tab ui-tabs-container ui-tabs-hide formPageNotFound">
            
            <div class="title">
                <span class="textTitle"><s:text name="SystemException.textTitle"/></span>
            </div>
            
            <p class="desc_forBidden1"><s:text name="SystemException.desc"/></p>
                                
            <form action="<web:url value='/tim-kiem-tin-tuc.shtml'/>" method="post" id="formSearchPageNotFound">
                <div class="boxSearch clearfix">
                    <p>                    
                       <input name="keyWord" type="text" class="input"                           
                            id="keywordInputPageNotFound" value=""
                            style="width: 430px; height: 25px;">                        
                       <input type="button" value="<s:text name="ForBidden.button.search"/>" class="iButton" onclick="javascript:doSearchNewsPageNotFound();">
                    </p>
                </div>
            </form>     
                         
        </div>
        
        <table width="100%" cellspacing="0" cellpadding="0" border="0" style="margin:30px 0;">
           <tbody>
               <tr class="iconMenu">
                   <td align="center"><a href="javascript:history.back();"><img src="<s:url value="/images/icons/loi01.png" />"/></a></td>
                   <td align="center"><a href="<web:url value="/home.shtml"/>"><img src="<s:url value="/images/icons/loi02.png" />"/></a></td>
                   <%-- <td align="center"><a href="#" id="feedback_parrent_menu"><img src="<s:url value="/images/icons/loi03.png" />"/></a></td> --%>
               </tr>
               
               <tr class="textMenu">
                   <td align="center"><s:text name="ForBidden.menu.back"/></td>
                   <td align="center"><s:text name="ForBidden.menu.home"/></td>
                   <%-- <td align="center"><s:text name="ForBidden.menu.error"/></td> --%>
               </tr>
           </tbody>
        </table>
    </div>
    
    <a style="cursor:pointer;float:right; " onclick="showhideError();"><img src="<web:url value='/images/icons/notice.png' />" /></a>
    <div id="errorLog" style="display:none;"><%=strWriter.toString() %></div>
</div>