<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script>
	$(function() {
		StockPick.initForOnlyBodyEffectBox();
	});
</script>

<div style="width: 975px; margin: auto;">
	<jsp:include
		page="/WEB-INF/jsps/consultingcenter/investment-idea/ShortOpenAndCloseStockPick-snippet.jsp"></jsp:include>
</div>
