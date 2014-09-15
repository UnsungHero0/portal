<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="content_ttpt" style="min-height: 540px;">
	<div class="content_tab">
		<!-- sub menus -->
		<jsp:include page="/WEB-INF/jsps/about/snippet/aboutNav.jsp"></jsp:include>

		<!-- left content -->
		<div class="content_ttpt boardOfDirectorsContent">
			<div id="b1" style="display: block;">
				<web:productSubject var="objProdSub" productCode="BOARD_1" />
				<c:out value="${objProdSub['product'].productOverview}"
					escapeXml="false" />
			</div>
			<div id="b2" style="display: none;">
				<web:productSubject var="objProdSub" productCode="BOARD_2" />
				<c:out value="${objProdSub['product'].productOverview}"
					escapeXml="false" />
			</div>
			<div id="b3" style="display: none;">
				<web:productSubject var="objProdSub" productCode="BOARD_3" />
				<c:out value="${objProdSub['product'].productOverview}"
					escapeXml="false" />
			</div>
			<div id="b7" style="display: none;">
				<web:productSubject var="objProdSub" productCode="BOARD_7" />
				<c:out value="${objProdSub['product'].productOverview}"
					escapeXml="false" />
			</div>
			<div id="b5" style="display: none;">
				<web:productSubject var="objProdSub" productCode="BOARD_5" />
				<c:out value="${objProdSub['product'].productOverview}"
					escapeXml="false" />
			</div>
			<div id="b9" style="display: none;">
                <web:productSubject var="objProdSub" productCode="BOARD_9" />
                <c:out value="${objProdSub['product'].productOverview}"
                    escapeXml="false" />
            </div>
            <div id="b10" style="display: none;">
                <web:productSubject var="objProdSub" productCode="BOARD_10" />
                <c:out value="${objProdSub['product'].productOverview}"
                    escapeXml="false" />
            </div>
            <div id="b8" style="display: none;">
                <web:productSubject var="objProdSub" productCode="BOARD_8" />
                <c:out value="${objProdSub['product'].productOverview}"
                    escapeXml="false" />
            </div>
            <div id="b11" style="display: none;">
                <web:productSubject var="objProdSub" productCode="BOARD_11" />
                <c:out value="${objProdSub['product'].productOverview}"
                    escapeXml="false" />
            </div>
            <div id="b12" style="display: none;">
                <web:productSubject var="objProdSub" productCode="BOARD_12" />
                <c:out value="${objProdSub['product'].productOverview}"
                    escapeXml="false" />
            </div>
		</div>
		<!-- end left content -->

		<!-- Right content -->
		<div id="c-column" class="width340">
			<div class="box_listnew">
				<div class="titleBar titleBar-margin-icon">
					<span id="title"><s:text name="about.boardOfDirectors.rightHeader">ĐỘI NGŨ LÃNH ĐẠO</s:text></span>
				</div>
				<hr class="hrline" />
				<div class="content_small boardOfDirectors">
					<web:productSubject var="objProdSub"
						productCode="INFO_BOARD_OF_DIRECTORS" />
					<c:out value="${objProdSub['product'].productOverview}"
						escapeXml="false" />
				</div>
			</div>
		</div>
		<!-- End right content -->
	</div>
</div>