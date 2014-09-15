<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach var="item" items="${searchResult}">
	<li>
		<a href='${item.urlDetail}'>${item.newsHeader}<span class="time">(<fmt:formatDate pattern="dd/MM/yyyy" value="${item.newsDate}" />)</span></a>
	</li>
</c:forEach>
<c:if test="${pagingInfo.indexPage < pagingInfo.totalPage}">
	<div class="jscroll-next-parent" style="display: none;">
		<a class="jscroll-next" href='<web:url value="/ajax/news/NewsGetVNDSHomepage.shtml"/>?currentIndex=${pagingInfo.indexPage + 1}'>next</a>
	</div>
</c:if> 