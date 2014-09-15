<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script>
$(function() {
	BasicAnalysis.init();
});
</script>

<div class="content_ttpt">
	<!--Start All Tab menu  -->
	<div class="content_small">
		<div class="content_doanhnghiep" style="width: 750px;">
			<c:if test="${not empty pasicAnalysis}">
				<input type="hidden" id="_symbol"
					value="${pasicAnalysis.companyQuote.symbol}" />
				<hr>
				<span style="color: green; font-size: 14px;">${pasicAnalysis.companyQuote.companyName}
					(${pasicAnalysis.companyQuote.symbol} -
					${pasicAnalysis.companyQuote.currentExchangeCode})</span>
				<br>
				<br>
				<a target="_blank"
					href="<web:url value='/bieu-do-ky-thuat/${pasicAnalysis.companyQuote.symbol}.shtml'/>">Biểu
					đồ kỹ thuật</a>
				<br>
				<br>
				<hr>
				<span style="color: blue; font-size: 14px;">Tăng trưởng EPS
					theo quý</span>
				<br>
				<br>
				<table id="basic-analysis"
					style="width: 100%; border-collapse: collapse;" border="1">

					<thead style="background: #EDEDED;">
						<tr>
							<c:forEach items="${pasicAnalysis.earningHeadList}"
								var="earningHead">
								<th align="center"><b>${earningHead}</b></th>
							</c:forEach>
						</tr>
					</thead>
					<tr>
						<c:forEach items="${pasicAnalysis.earningList}" var="earning">
							<td align="right"><b>${earning} tỷ</b></td>
						</c:forEach>
					</tr>
					<tr>
						<c:forEach items="${pasicAnalysis.epsIncreasePercentList}"
							var="eps">
							<c:set var="epsClass" value="green" />
							<c:choose>
								<c:when test="${fn:contains(eps, '-')}">
									<c:set var="epsClass" value="red" />
								</c:when>
							</c:choose>
							<td align="right" class="${epsClass}"><b>${eps} %</b></td>
						</c:forEach>
					</tr>
				</table>


				<br>
				<hr>
				<br>
				<div style="width: 100%; display: inline-block;">
					<!-- Lãi ròng hàng năm -->
					<div style="float: left; width: 45%;">
						<span style="color: blue; font-size: 14px;">Lãi ròng hàng
							năm</span>
						<p style="padding: 10px;">
							2013: <span id="_income2013"
								style="color: green; font-size: 14px; font-weight: bold;"></span>
						</p>
						<p style="padding: 10px;">
							2012: <span id="_income2012"
								style="color: green; font-size: 14px; font-weight: bold;"></span>
						</p>
						<p style="padding: 10px;">
							2011: <span id="_income2011"
								style="color: green; font-size: 14px; font-weight: bold;"></span>
						</p>
					</div>

					<!-- ROE hàng năm -->
					<div style="float: left; width: 45%;">
						<span style="color: blue; font-size: 14px;">ROE hàng năm</span>
						<p style="padding: 10px;">
							2013: <span id="_roe2013"
								style="color: green; font-size: 14px; font-weight: bold;"></span>
						</p>
						<p style="padding: 10px;">
							2012: <span id="_roe2012"
								style="color: green; font-size: 14px; font-weight: bold;"></span>
						</p>
						<p style="padding: 10px;">
							2011: <span id="_roe2011"
								style="color: green; font-size: 14px; font-weight: bold;"></span>
						</p>
					</div>
				</div>
			</c:if>
		</div>

		<div
			style="float: left; margin-left: 100px; width: 100px; padding: 20px 7px"
			id="ba-seeMoreComp">
			<ol>
				<li><a href="<web:url value='/asd/BCE.shtml'/>">BCE</a></li>
				<li><a href="<web:url value='/asd/CII.shtml'/>">CII</a></li>
				<li><a href="<web:url value='/asd/CLG.shtml'/>">CLG</a></li>
				<li><a href="<web:url value='/asd/CMX.shtml'/>">CMX</a></li>
				<li><a href="<web:url value='/asd/CTS.shtml'/>">CTS</a></li>
				<li><a href="<web:url value='/asd/DXG.shtml'/>">DXG</a></li>
				<li><a href="<web:url value='/asd/HAG.shtml'/>">HAG</a></li>
				<li><a href="<web:url value='/asd/HCM.shtml'/>">HCM</a></li>
				<li><a href="<web:url value='/asd/IDI.shtml'/>">IDI</a></li>
				<li><a href="<web:url value='/asd/IJC.shtml'/>">IJC</a></li>
				<li><a href="<web:url value='/asd/KLF.shtml'/>">KLF</a></li>
				<li><a href="<web:url value='/asd/NTL.shtml'/>">NTL</a></li>
				<li><a href="<web:url value='/asd/PVG.shtml'/>">PVG</a></li>
				<li><a href="<web:url value='/asd/SD5.shtml'/>">SD5</a></li>
				<li><a href="<web:url value='/asd/TS4.shtml'/>">TS4</a></li>
				<li><a href="<web:url value='/asd/VCB.shtml'/>">VCB</a></li>
				<li><a href="<web:url value='/asd/VCG.shtml'/>">VCG</a></li>
				<li><a href="<web:url value='/asd/VCS.shtml'/>">VCS</a></li>
				<li><a href="<web:url value='/asd/VSC.shtml'/>">VSC</a></li>
			</ol>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>

    <hr>

	<div style="width: 100%;">
		<p id="_hxnSymbols"></p>
		<br>
		<hr>
		<br>
		<p id="_hoseSymbols"></p>
	</div>
</div>