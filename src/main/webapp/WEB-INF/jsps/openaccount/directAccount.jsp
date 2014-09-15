<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils"%> 

<div id="content_ttpt">
	<div class="content_tab">
		<jsp:include
			page="/WEB-INF/jsps/openaccount/snippet/openAccountTop.jsp"></jsp:include>
		<!-- left -->
		<div class="content_ttpt openAccount">
			<web:productSubject var="objProdSub"
				productCode="OPEN_INVESTOR_ACCOUNT" />
				<c:set value="${objProdSub['product'].productOverview}" var="htmlObj"/>
				
				<%pageContext.setAttribute("variableHtml", StringEscapeUtils.unescapeHtml((String)pageContext.getAttribute("htmlObj")));%>
				<%-- <% String variableHtml= StringEscapeUtils.unescapeHtml((String)pageContext.getAttribute("htmlObj"));
					pageContext.setAttribute("variableHtml", variableHtml);%> --%>
			<%-- <c:out value="${variableHtml}"	escapeXml="false" /> --%>
			<s:property value="#attr.variableHtml" escape="false"/>
			<div class="clearfix padding-top10" style="text-align: center">
				<input class="iButton" type="button"
					value="<s:text name='home.account.open'>Đăng ký</s:text>"
					onclick="doRegister();" />
			</div>
		</div>
		<!-- end left -->
		<!-- right -->
		<jsp:include
			page="/WEB-INF/jsps/openaccount/snippet/openAccountRightColumn.jsp"></jsp:include>
		<!-- End right -->
	</div>
</div>
<script type="text/javascript">
	function doRegister() {
		window.location = '<web:url value="/mo-tai-khoan-nha-dau-tu/tai-khoan-giao-dich.shtml" />';
	}
</script>
