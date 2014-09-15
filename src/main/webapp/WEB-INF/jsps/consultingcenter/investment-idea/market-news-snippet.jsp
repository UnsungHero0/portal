<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<c:if test="${not empty marketNews}">
<li><a href="<web:url value='/nhan-dinh-thi-truong.shtml'/>"><img
		src="<web:url value='/images/icons/khuyennghi/videoicon.png' />" /></a>
	<p>
		<a href="<web:url value='/nhan-dinh-thi-truong.shtml'/>"> ${marketNews.newsHeader} </a>
		<span class="date">(${marketNews.strNewsDate})</span>
	</p></li>
</c:if>




