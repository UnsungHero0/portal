<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<span class="newdetails"><b><s:text name="home.news"/>:&nbsp;&nbsp;</b>



<span id="newsLink">&nbsp;&nbsp;<img src="<web:url value="images/ajax-loader.gif"/>" /></span></span>

 <c:forEach var="item" varStatus="i" items="${model.searchResult}">
   <input type="hidden" id="linkDetail${i.count}" value="<a href='${item.urlDetail}'>${item.newsHeader}</a><span style='margin-left: 5px;' class='icon-new'></span>"/>
</c:forEach> 