<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!-- Begin Content -->

<script type="text/javascript">
    $(document).ready(function(){
        $('#feedback_parrent_menu').click(function(){
            $('#myCustomTrigger').click();
        });

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
</script>

<div id="content_ttpt" style="width: 650px;">

    <div class="content_small">
        <div style="display: block; margin-top: 50px; background:none repeat scroll 0 0 #EEEEEE" id="pageNotFound" class="content_tab ui-tabs-container ui-tabs-hide formPageNotFound">
            
            <div class="title">
                <span class="textTitle"><s:text name="ForBidden.textTitle"/></span>
            </div>
            
            <p class="desc_forBidden1"><s:text name="ForBidden.textDesc1"/> <a class="linkAcc" href="<web:url value='/home.shtml'/>"><s:text name="Forbidden.textLinkAcc"/></a></p>
            <p class="desc_forBidden2"><s:text name="ForBidden.textDesc2"/></p>
            <p class="desc_forBidden3"><s:text name="ForBidden.textDesc3"/></p>
                                
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
        
        <table width="100%" cellspacing="0" cellpadding="0" border="0" class="menu404">
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
</div>