<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="vn.com.vndirect.commons.usercounter.WebStatisticsHolder"%>
<%@ page import="vn.com.vndirect.commons.utility.VNDirectWebUtilities"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@ page import="vn.com.web.commons.utility.DateUtils"%>
<%@page import="java.util.Date"%>
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
<script type="text/javascript">
    /* live-chat script */
	var lhnAccountN = 17791;
    var lhnButtonN = 5101;
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

<input id="fastClickGuide" type="hidden" value="<s:text name='quickLink.fastClick.guide'></s:text>"/>
<input id="notFastClickGuide" type="hidden" value="<s:text name='quickLink.notFastClick.guide'></s:text>"/>

<div id="footer">
    <div class="ft lineft">
        <div class="footer">
            <div class="icon_support print"></div>
            <div class="support_ft" style="padding-left:40px;">
                <h2>
                    <a href="<web:url value='/tro-giup/hoi-dap-huong-dan.shtml' />"><s:text name="footer.help">TRỢ GIÚP</s:text></a>                    
                </h2>

                <ul class="list_sp" style="width:300px;">
                    <li class="title">
                        <a href="<web:url value='/tro-giup/hoi-dap-huong-dan.shtml' />"><s:text name="footer.help.qa">Hỏi đáp về</s:text></a>
                    </li>
                    <web:productSubject var="objProdSub" productCode="tro-giup" />
                    <c:out value="${objProdSub['product'].productOverview}"
                        escapeXml="false" />
                    <c:if test="${not empty objProdSub['product'].wpSubjectList}">
                        <c:forEach var="item"
                            items="${objProdSub['product'].wpSubjectList}">
                            <li>
                                <a target="blank"
                                    href="<web:url value="/${objProdSub['product'].productCode}/${item.subjectCode}.shtml"/>">
                                    - <c:out value="${item.subjectName}" /> </a>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>

                <ul class="list_sp" style="margin-left:50px;width:320px;">
                    <li class="title">
                        <a href="<web:url value='/tro-giup/hoi-dap-huong-dan.shtml' />"><s:text name="footer.help.usingGuide">Hướng dẫn sử dụng</s:text></a>
                    </li>
                    <web:productSubject var="objProdSub"
                        productCode="tro-giup-san-pham-dich-vu" />
                    <c:out value="${objProdSub['product'].productOverview}"
                        escapeXml="false" />
                    <c:if test="${not empty objProdSub['product'].wpSubjectList}">
                        <c:forEach var="item"
                            items="${objProdSub['product'].wpSubjectList}">
                            <li>
                                <a target="blank"
                                    href="<web:url value="/${objProdSub['product'].productCode}/${item.subjectCode}.shtml"/>">
                                    - <c:out value="${item.subjectName}" /> </a>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>

                <ul class="list_sp" style="width:200px;float:right;">
                    <li class="title">
                        <a href="<web:url value='/tro-giup/hoi-dap-huong-dan.shtml' />"><s:text name="footer.help.tradingGuide">Hướng dẫn giao dịch</s:text></a>
                    </li>
                    <web:productSubject var="objProdSub" productCode="tro-giup-giao-dich" />
                    <c:out value="${objProdSub['product'].productOverview}"
                        escapeXml="false" />
                    <c:if test="${not empty objProdSub['product'].wpSubjectList}">
                        <c:forEach var="item"
                            items="${objProdSub['product'].wpSubjectList}">
                            <li>
                                <a target="blank"
                                    href="<web:url value="/${objProdSub['product'].productCode}/${item.subjectCode}.shtml"/>">
                                    - <c:out value="${item.subjectName}" /> </a>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>

    <div class="ft lineft">
        <div class="footer">
            <div class="support_ft" id="logoAdHomepage">
                <web:productSubject var="objProdSub" productCode="LOGO_AD_HOMEPAGE" />
                <c:out value="${objProdSub['product'].productOverview}"
                    escapeXml="false" />
            </div>
        </div>
    </div>

    <div class="ft lineft" id="xemmangluoiId">
        <div class="footer">
            <div class="icon_contact_ft print"></div>
            <div class="contact_ft">
                <div class="rowa">
                    <web:productSubject var="objProdSub" subjectCode="HOISO"
                        productCode="QUANLYMANGLUOI" />
                    <c:set value="${objProdSub['subject'].subjectContent}" var="htmlObj"/>    
                    <%-- <c:out value="${objProdSub['subject'].subjectContent}"
                        escapeXml="false" /> --%>
                     <%pageContext.setAttribute("variableHtml", StringEscapeUtils.unescapeHtml((String)pageContext.getAttribute("htmlObj")));%>
                     <c:out value="${variableHtml}" escapeXml="false"/>   
                </div>

                <div class="rowb">
                    <web:productSubject var="objProdSub" subjectCode="CHINHANH"
                        productCode="QUANLYMANGLUOI" />
                    <c:set value="${objProdSub['subject'].subjectContent}" var="htmlObj"/>  
                    <%pageContext.setAttribute("variableHtml", StringEscapeUtils.unescapeHtml((String)pageContext.getAttribute("htmlObj")));%>
                    <c:out value="${variableHtml}"
                        escapeXml="false" />
                </div>

            </div>
            <div class="icon_contact_phone print"></div>
        </div>
    </div>

    <div class="ft lineft">
        <div class="footer">
            <div class="text_copyright">
                <span class="left"><s:text name="footer.copyright">Bản quyền © Công ty cổ phần chứng khoán VNDIRECT</s:text></span>
                <span class="margin-right15 left" style="margin-left: 105px;"><%=strDate%></span> <span
					style="color: #9B9B9B">|</span> <span class="text_serverinfo"><%=VNDirectWebUtilities.getServerNumber()%>
					A : <%=WebStatisticsHolder.getWebStatistics().getTotalActiveSession()%></span>
                <span class="text_serverinfo" style="float:right;"><a href="<web:url value="/dieu-khoan-su-dung-website.shtml" />"><s:text name="footer.label.disclamer">Disclamer</s:text></a></span>
            </div>
        </div>
    </div>

    <!--++menufooter-->
    <div class="footer">
        <div class="wrapper_footer">
            <span class="print collapse" id="collapse-expand-icon"></span>
            <div class="menu_footer">
                <ul class="fl">
                    <li class="line">
                        <div id="menusupfooter" style="display: none" class="bg_top_ft">
                            <div class="bg_bt">
                                <div class="bg_ct">
                                    <div class="list">
                                        <div class="line title noline">
                                            <span class="icon-supmenuft print left"></span>&nbsp;ITEM
                                            LIST
                                        </div>
                                        <span class="print closeChooseQuickLink"
                                            onclick="closeQuickLinkMenufooter();"></span>
                                        <web:productSubject var="obj" productCode="QUICKLINKS" />
                                        <c:set var="openDiv"
                                            value="<ul class='line' id='quickLinksList'>"></c:set>
                                        <c:set var="closeDiv" value="</ul>"></c:set>
                                        <c:set var="isClosed" value="false"></c:set>
                                        ${openDiv}
                                        <c:forEach var="subject"
                                            items="${obj['product'].wpSubjectList}" varStatus="i">
                                            <c:if test="${i.count % 5 eq 1 && isClosed}">
                                                 ${openDiv}
                                                 <c:set var="isClosed"
                                                    value="false"></c:set>
                                            </c:if>
                                            <li class="box_icon">
                                                ${subject.subjectContent}
                                                <span id=title>${subject.subjectName}</span>
                                            </li>
                                            <c:if test="${i.count % 5 eq 0}">
                                              ${closeDiv}
                                              <c:set var="isClosed"
                                                    value="true"></c:set>
                                            </c:if>
                                        </c:forEach>
                                        <c:choose>
                                            <c:when test="${isClosed}">
                                        ${openDiv}
                                            <li
                                                    class="box_icon_delete inactive" id="removeQuickLink">
                                                    <p class="print icon"></p>
                                                    <span id=title>Gỡ bỏ</span>
                                                </li>
                                        ${closeDiv}
                                       </c:when>
                                            <c:otherwise>
                                                <li class="box_icon_delete inactive" id="removeQuickLink">
                                                    <p class="print icon"></p>
                                                    <span id=title>Gỡ bỏ</span>
                                                </li>
                                           ${closeDiv}
                                       </c:otherwise>
                                        </c:choose>
                                       <span id="huongdanquicklink">Kéo thả vào vị trí tương ứng để chọn/ Click để truy cập nhanh.</span>
                                       <span id="huongdanchitiet">
                                           <a href="<web:url value='/tro-giup-san-pham-dich-vu/huong-dan-su-dung-website-vndirect-phien-ban-2013.shtml' />" style="color: #F39200"><s:text name="quickLink.guide"></s:text></a>                                           
                                       </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <a title="<s:text name='footer.directBar.setting.title'/>" class="icon-supmenuft margin" onclick="showQuickLinkMenufooter();" id="icon-supmenuft"></a>
                    </li>
                    <div id='placedQuickLinksList'>
                        <li class="quickLinkIcon" id="1"
                            onclick="addQuickLinkMenufooter($(this), 66);">
                            <span class="print"
                                onclick="editQuickLinkMenufooter($(this), 66);"></span>
                            <addButton></addButton>
                            <a href=""><img src="" style="display: none;" />
                                <p></p> </a>
                        </li>
                        <li class="quickLinkIcon" id="2"
                            onclick="addQuickLinkMenufooter($(this), 182);">
                            <span class="print"
                                onclick="editQuickLinkMenufooter($(this), 182);"></span>
                            <addButton></addButton>
                            <a href=""><img src="" style="display: none;" />
                                <p></p> </a>
                        </li>
                        <li class="quickLinkIcon" id="3"
                            onclick="addQuickLinkMenufooter($(this), 300);">
                            <span class="print"
                                onclick="editQuickLinkMenufooter($(this), 300);"></span>
                            <addButton></addButton>
                            <a href=""><img src="" style="display: none;" />
                                <p></p> </a>
                        </li>
                        <li class="quickLinkIcon" id="4"
                            onclick="addQuickLinkMenufooter($(this), 418);">
                            <span class="print"
                                onclick="editQuickLinkMenufooter($(this), 418);"></span>
                            <addButton></addButton>
                            <a href=""><img src="" style="display: none;" />
                                <p></p> </a>
                        </li>
                        <li class="quickLinkIcon" id="5"
                            onclick="addQuickLinkMenufooter($(this), 536);">
                            <span class="print"
                                onclick="editQuickLinkMenufooter($(this), 536);"></span>
                            <addButton></addButton>
                            <a href=""><img src="" style="" />
                                <p></p> </a>
                        </li>
                    </div>
                    <li class="left footer-phanhoi-wrap">
                        <%-- <div class="footer-phanhoi">
                            <a class="icon-mail" href="#" id="myCustomTrigger"></a>
                            <span><s:text name="footer.directBar.phanhoi">Phản hồi</s:text></span>
                        </div>
                        <a class="icon-ym" href="ymsgr:sendim?vnds_customerservice"></a>
                        <a class="icon-skype" href="skype:vnds_customerservice?chat"></a> --%>
                        <div class="footer-phanhoi">
                            <a class="icon-mail" href="mailto:support@vndirect.com.vn"></a>
                            <div class="footer-phanhoi-text"><span>Email</span></div>
                             <div class="clear"></div>
                        </div>
                        <div class="footer-phanhoi" id="lhnContainer">
                            <p class="icon-livechat" id="lhnChatButton"></p>
                            <div class="footer-phanhoi-text"><span><s:text name="footer.directBar.liveChat">Hỗ trợ trực tuyến</s:text></span></div>
                             <div class="clear"></div>
                        </div>
                    </li>
                     <li class="right directBar-restoreDefault">
                        <span title="<s:text name='footer.directBar.restoreDefault.title'/>" class="inactive"></span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!--//end menufooter-->
</div>

<script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-2025955-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = 'https://ssl.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>
