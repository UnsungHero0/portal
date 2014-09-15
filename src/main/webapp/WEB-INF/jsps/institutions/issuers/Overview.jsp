<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<div id="content">

	<ul class="ui-tabs-nav tab_ttpt" id="container-5">
        <li class="ui-tabs-selected">
            <a href="<web:url value='/to-chuc-phat-hanh/tong-quan.shtml'/>"><label class="icon_active"></label><s:text
                                        name="institutions.overview"/></a>
        </li>
        <li class="">
            <a href="<web:url value='/to-chuc-phat-hanh/san-pham-dich-vu.shtml'/>" class=""><label class="icon_active"></label><s:text
                                        name="institutions.productsAndServices"/></a>
        </li>
        <li class="">
            <a href="<web:url value='/to-chuc-phat-hanh/doi-ngu-chuyen-gia.shtml'/>" class=""><label class="icon_active"></label><s:text
                                        name="institutions.consultants"/></a>
        </li>
	</ul>
	<div class="clear"></div>
	
	<div class="content_khtc ui-tabs-container" id="tab-1" style="">
	    <!-- image right -->
        <%--<img width="350" class="right" src="images/thumbs/img6.png">--%>
        <web:document var="imgDoc" productCode="BANNER_INSTITUTIONS" download="true" />
        <img width="350" class="right" src="${imgDoc['document'].documentUri}" />
                                       
        <!-- content left -->
        <div class="box_left_khtc">
            <web:productSubject var="objProdSub" productCode="INSTITUTIONS_PUBLISH_INTRO_CONTENT" />
            <c:out value="${objProdSub['product'].productOverview}" escapeXml="false" />
        </div>                                         
    </div>
</div>