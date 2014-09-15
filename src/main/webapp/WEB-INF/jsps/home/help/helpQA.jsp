<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="vn.com.vndirect.commons.utility.VNDirectWebUtilities"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<div id="content">
	<jsp:include page="/WEB-INF/jsps/home/help/snippet/helpNav.jsp"></jsp:include>

	<div class="padding-top10">
		<div class="box265">
			<div class="titleBar bgTitleBar text-upper">
				<span id="margin-title"><s:text name="help.guide.fag">CÂU HỎI THƯỜNG GẶP</s:text></span>
			</div>
			<div class="clear"></div>
			<ul class="list15">
				<web:productSubject var="objProdSub" productCode="tro-giup" />
				<c:out value="${objProdSub['product'].productOverview}"
					escapeXml="false" />
				<c:if test="${not empty objProdSub['product'].wpSubjectList}">
					<c:forEach var="item"
						items="${objProdSub['product'].wpSubjectList}">
						<li><a target="blank"
							href="<web:url value="/${objProdSub['product'].productCode}/${item.subjectCode}.shtml"/>">
								<img src="<web:url value='/images/icons/icon-1.png' />" /> <c:out
									value="${item.subjectName}" />
						</a></li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
		<div class="box325">
			<div class="titleBar bgTitleBar text-upper">
				<span id="margin-title"><s:text
						name="help.guide.productsUsingGuide">HƯỚNG DẪN SỬ DỤNG SẢN PHẨM DỊCH VỤ</s:text></span>
			</div>
			<div class="clear"></div>
			<ul class="list15">
				<web:productSubject var="objProdSub"
					productCode="tro-giup-san-pham-dich-vu" />
				<c:out value="${objProdSub['product'].productOverview}"
					escapeXml="false" />
				<c:if test="${not empty objProdSub['product'].wpSubjectList}">
					<c:forEach var="item"
						items="${objProdSub['product'].wpSubjectList}">
						<li><a
							href="<web:url value="/${objProdSub['product'].productCode}/${item.subjectCode}.shtml"/>">
								<img src="<web:url value='/images/icons/icon-1.png' />" /> <c:out
									value="${item.subjectName}" />
						</a></li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
		<div class="box265">
			<div class="titleBar bgTitleBar text-upper">
				<span id="margin-title"><s:text
						name="help.guide.tradingGuide">HƯỚNG DẪN GIAO DỊCH</s:text></span>
			</div>
			<div class="clear"></div>
			<ul class="list15">
				<web:productSubject var="objProdSub"
					productCode="tro-giup-giao-dich" />
				<c:out value="${objProdSub['product'].productOverview}"
					escapeXml="false" />
				<c:if test="${not empty objProdSub['product'].wpSubjectList}">
					<c:forEach var="item"
						items="${objProdSub['product'].wpSubjectList}">
						<li><a
							href="<web:url value="/${objProdSub['product'].productCode}/${item.subjectCode}.shtml"/>">
								<img src="<web:url value='/images/icons/icon-1.png' />" /> <c:out
									value="${item.subjectName}" />
						</a></li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
	</div>
</div>
