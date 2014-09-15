<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="vn.com.vndirect.commons.utility.VNDirectWebUtilities"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<div id="content_ttpt">
	<div class="content_small">
		<div class="content_tab">
			<jsp:include
				page="/WEB-INF/jsps/listedmarket/snippet/si-news-nav.jsp"></jsp:include>

			<jsp:include page="/WEB-INF/jsps/common/snippet/siNewsDetail.jsp"></jsp:include>
		</div>
	</div>
</div>