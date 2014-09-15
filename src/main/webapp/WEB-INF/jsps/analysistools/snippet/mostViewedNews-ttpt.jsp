<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Most viewed news content of TTPT -->
<div class="titleBar titleBar-margin-icon">
	<span id="title"><s:text name="analysis.news.mostWatched">TIN XEM NHIỀU</s:text></span>
</div>
<hr class="hrline" />
<div class="content_small">
	<ul class="list">
		<c:forEach var="item" items="${model.searchMostView}">
			<li><a href="${item.urlDetail}">${item.newsHeader}</a> <span
				class="textcreatnews"> (${item.newsDateStr})</span></li>
		</c:forEach>
	</ul>
</div>