<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<%
	String _productCode = request.getParameter("productCode");
	_productCode = (_productCode == null ? "" : _productCode.trim());
	request.setAttribute("productCode", _productCode == null ? "" : _productCode);

	String _subjectCode = request.getParameter("subjectCode");
	_subjectCode = (_subjectCode == null ? "" : _subjectCode.trim());
	request.setAttribute("subjectCode", _subjectCode == null ? "" : _subjectCode);
%>

<div id="phan_tich_co_ban">
	<div class="clearfix content_left">
		<web:productSubject var="objProdSub" subjectCode="${subjectCode}"
			productCode="${productCode}" />
		<div class="text_title_detail_hoidap">
			<%
				if (_productCode.length() > 0 && _subjectCode.length() == 0) {
			%>
			<c:out value=">> ${objProdSub['product'].productName}"
				escapeXml="false" />
			<%
				} else {
			%>
			<c:out value=">> ${objProdSub['subject'].subjectName}"
				escapeXml="false" />
			<%
				}
			%>
		</div>

		<div class="center_inner clearfix">
			<div class="clearfix">
				<%
					if (_productCode.length() > 0 && _subjectCode.length() == 0) {
				%>
				<table cellpadding="10" cellspacing="5" border="0" width="100%">
					<tr>
						<td>
							<c:out value="${objProdSub['product'].productOverview}"
								escapeXml="false" />
						</td>
					</tr>
				</table>
				<%
					} else {
				%>
				<table cellpadding="10" cellspacing="5" border="0" width="100%">
					<tr>
						<td>
							<c:out value="${objProdSub['subject'].subjectContent}"
								escapeXml="false" />
						</td>
					</tr>
				</table>
				<%
					}
				%>

				<s:set var="url">
					<web:url value="/home/service/viewContentDetail.shtml?productCode=" />
					<s:property value="#parameters[\"productCode\"][0]" escape="false" />
				</s:set>
				<s:if
					test="#parameters[\"productCode\"][0] == 'BASIC_TRADING' || #parameters[\"productCode\"][0] == 'HELP_FUND' || #parameters[\"productCode\"][0] == 'PRIVATE_ACCOUNT' || #parameters[\"productCode\"][0] == 'ACTIVE_TRADING'">
					<s:set var="url">
						<web:url
							value="/home/service/viewTradingServiceDetail.shtml?productCode=" />
						<s:property value="#parameters[\"productCode\"][0]" escape="false" />
					</s:set>
				</s:if>
				<s:elseif
					test="#parameters[\"productCode\"][0] == 'ANALYSIS_CENTER' || #parameters[\"productCode\"][0] == 'ADVISORY_INVESMENT'">
					<s:set var="url">
						<web:url
							value="/home/service/viewInfoServiceDetail.shtml?productCode=" />
						<s:property value="#parameters[\"productCode\"][0]" escape="false" />
					</s:set>
				</s:elseif>
				<s:elseif
					test="#parameters[\"productCode\"][0] == 'ADVICE_LISTING' || #parameters[\"productCode\"][0] == 'ADVICE_ISSUING' || #parameters[\"productCode\"][0] == 'ADVICE_UNDERWRITING' || #parameters[\"productCode\"][0] == 'ADVICE_BUSINESS' || #parameters[\"productCode\"][0] == 'ADVICE_RESTRUCTURING' || #parameters[\"productCode\"][0] == 'ADVICE_EQUITIZATION' || #parameters[\"productCode\"][0] == 'ADVICE_IR'">
					<s:set var="url">
						<web:url
							value="/home/service/viewAdviceServiceDetail.shtml?productCode=" />
						<s:property value="#parameters[\"productCode\"][0]" escape="false" />
					</s:set>
				</s:elseif>
				<s:elseif
					test="#parameters[\"productCode\"][0] == 'VISTA_DIRECT' || #parameters[\"productCode\"][0] == 'STOCK_SCREENER' || #parameters[\"productCode\"][0] == 'STOCK_WIZARD' || #parameters[\"productCode\"][0] == 'FLASH_CHART' || #parameters[\"productCode\"][0] == 'SMS_DIRECT'">
					<s:set var="url">
						<web:url
							value="/home/service/viewSpecialProductDetail.shtml?productCode=" />
						<s:property value="#parameters[\"productCode\"][0]" escape="false" />
					</s:set>
				</s:elseif>
				<s:elseif
					test="#parameters[\"productCode\"][0] == 'INVESTOR_GUIDELINE' || #parameters[\"productCode\"][0] == 'DIRECT_TRADING' || #parameters[\"productCode\"][0] == 'TRADING_KNOWLEDGE' || #parameters[\"productCode\"][0] == 'FAQs'">
					<s:set var="url">
						<web:url value="/home/help/trading-guide.shtml?productCode=" />
						<s:property value="#parameters[\"productCode\"][0]" escape="false" />
					</s:set>
				</s:elseif>
			</div>
		</div>
	</div>
	<c:if test="${not empty objProdSub['product'].wpSubjectList}">
		<div id="c-column" class="width340">
			<div class="box_listnew">
				<h2 class="title">
					<s:text name="commons.staticpage.related">Related</s:text>
				</h2>
				<div class="content_small">
					
						<ul class="list15">
							<c:forEach var="item"
								items="${objProdSub['product'].wpSubjectList}" varStatus="i">
								<c:set var="noline" value=""></c:set>
								<c:if test="${i.count == 1}">
									<c:set var="noline" value="noline"></c:set>
								</c:if>
								<li class="${noline}">
									<a
										href="<s:property value="#url"/>&subjectCode=${item.subjectCode}">
										> <c:out value="${item.subjectName}" /> </a>
								</li>
							</c:forEach>
						</ul>
					
				</div>
			</div>
		</div>
	</c:if>
</div>
