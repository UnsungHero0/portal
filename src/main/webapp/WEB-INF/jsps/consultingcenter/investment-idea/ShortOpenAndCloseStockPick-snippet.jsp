<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="recommendBox" style="">
	<h3 class="title">Hiệu quả khuyến nghị</h3>
	<div id="recommend-tabs" class="recommendBoxMain">
		<ul class="navkhuyennghi">
			<li class="active"><a href="#openStockPick"
				id="openStockPickTab">Khuyến nghị đang mở</a></li>
			<li>|</li>
			<li><a href="#closeStockPick" id="closeStockPickTab">Khuyến
					nghị đã đóng</a></li>
		</ul>

		<div id="closeStockPick">
			<web:productSubject var="objProdSub" productCode="STOCK_PICK_CLOSE_SHORT" isUsingCache="false"/>
			<c:out value="${objProdSub['product'].productOverview}"
				escapeXml="false" />
		</div>
		<div id="openStockPick">
			<web:productSubject var="objProdSub" productCode="STOCK_PICK_OPEN_SHORT" isUsingCache="false"/>
			<c:out value="${objProdSub['product'].productOverview}"
				escapeXml="false" />
		</div>
	</div>
	<div class="clear"></div>
</div>
<div class="clear"></div>





