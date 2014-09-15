<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${pagingInfo.indexPage eq 1}">
    <li style="display:none">
        <input type="hidden" id="relate_indexPage" value="${pagingInfo.indexPage}" />
        <input type="hidden" id="relate_total" value="${pagingInfo.totalPage}" />
    </li>
</c:if>

<c:if test="${not empty model.searchIfoNewsResult}">
	<c:forEach var="doc" items="${model.searchIfoNewsResult}">
	    <li>
	        <a onclick="showDocumentOnlineByFlexPaper('${doc.filePath}', '${doc.newsId}', '${doc.newsHeader}');">${doc.newsHeader}</a>
	    </li>
	</c:forEach>
</c:if>
