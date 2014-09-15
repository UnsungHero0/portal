<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<div id="wrapper">
	<div id="container">
		<div id="content">
			<jsp:include page="/WEB-INF/jsps/home/help/snippet/helpNav.jsp"></jsp:include>

			<div class="introduction clearfix">
				<jsp:include page="ContentDetail.jsp" />
			</div>
		</div>
	</div>
</div>
