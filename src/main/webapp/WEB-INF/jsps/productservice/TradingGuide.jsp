<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://www.webapp.com.vn/tags-fmt" prefix="webFmt" %>

<div id="main" class="clearfix">
	<table cellpadding="0" cellspacing="0" border="0" width="975">
		<tr>
			<td valign="top" width="699">
				<web:document var="flashDoc" productCode="BANNER_PRODUCT_SERVICE" />
				<div style="padding-bottom:10px">
					<object width="699" height="197">
						<param name="movie" value="<c:out value="${flashDoc['document'].documentUri}" escapeXml="false"/>">
						<embed src="<c:out value="${flashDoc['document'].documentUri}" escapeXml="false"/>" width="699" height="197"  type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer"></embed>
					</object>
				</div>
				<!--
				<div style="padding-bottom:10px"><img src='<s:url value="/images/img/banner_03.jpg"/>' width="696" height="190" /></div>
				 -->
				<div class="introduction clearfix">
					<ul class="hp">
						<li class="hp-left"></li>
						<li class="hp-right"></li>
						<li class="hp-center">
							<span>
								<a href="<web:url value="/vndirect/san-pham-dich-vu.shtml"/>"><s:text name="submenu.label.product-service.tradingService">Trading Service</s:text></a>
							</span>|<span>
								<a href="<web:url value="/home/service/ServiceInfo.shtml"/>"><s:text name="submenu.label.product-service.infoService">Info Service</s:text></a>
							</span>|<c:if test="${locale == 'vn'}"><span>
								<a href="<web:url value="/home/service/ServiceAdvice.shtml"/>"><s:text name="submenu.label.product-service.adviceService">Finance Advice Service</s:text></a>
							</span>|</c:if><span>
								<a href="<web:url value="/home/service/ServiceProduct.shtml"/>"><s:text name="submenu.label.product-service.specialProduct">Special Product</s:text></a>
							</span>|<span>
								<b>&nbsp;&nbsp;<s:text name="submenu.label.product-service.tradingGuide">Trading Guide</s:text></b>
							</span>
						</li>
					</ul>
					<div class="ct-textnone">
					<div class="clearfix l-block">
						<p class="Title-general"><b><s:text name="submenu.label.product-service.instruction"/></b></p>
						<div class="fl l-block-left"><p class="fix-img"><a href="#"><img src='<s:url value="/images/img/hd_01.gif" />' /></a></p>
							<p class="tt_center" align="center"><b><a href="#"><s:text name="submenu.label.product-service.research-center"/></a></b></p>
						</div>
						<div class="fr l-block-right">
							<web:productSubject var="objProdSub" productCode="INVESTOR_GUIDELINE"/>
							<c:out value="${objProdSub['product'].productOverview}" escapeXml="false"/>
							<c:if test="${not empty objProdSub['product'].wpSubjectList}">
								<ul id="ulnews">
									<c:forEach var="item" items="${objProdSub['product'].wpSubjectList}">
										<li>
											<a href="<web:url value="/home/help/trading-guide.shtml?subjectCode=${item.subjectCode}&productCode=INVESTOR_GUIDE"/>">
											<c:out value="${item.subjectName}"/>
											</a>
										</li>
									</c:forEach>
								</ul>
							</c:if>
						</div>
					</div>
					<!---->
					<div class="clearfix l-block">
						<p class="Title-general"><b><s:text name="submenu.label.product-service.onlinetradingGuide"/></b></p>
						<div class="fl l-block-left"><p class="fix-img"><a href="#"><img src='<s:url value="/images/img/hd_02.gif" />' /></a></p>
							<p class="tt_center" align="center"><b><a href="#"><s:text name="submenu.label.product-service.view-video"/></a></b></p>
						</div>
						<div class="fr l-block-right">
							<web:productSubject var="objProdSub" productCode="DIRECT_TRADING"/>
							<c:out value="${objProdSub['product'].productOverview}" escapeXml="false"/>
							<c:if test="${not empty objProdSub['product'].wpSubjectList}">
								<ul id="ulnews">
									<c:forEach var="item" items="${objProdSub['product'].wpSubjectList}">
										<li>
											<a href="<web:url value="/home/help/trading-guide.shtml?subjectCode=${item.subjectCode}&productCode=DIRECT_TRADING"/>">
											<c:out value="${item.subjectName}"/>
											</a>
										</li>
									</c:forEach>
								</ul>
							</c:if>
						</div>
					</div>
					<!---->
					<div class="clearfix l-block-end">
						<p class="Title-general"><b><s:text name="submenu.label.product-service.trading-knowledge"/></b></p>
						<div class="fl l-block-left"><p class="fix-img"><a href="#"><img src='<s:url value="/images/img/hd_03.gif" />' /></a></p>
							<p class="tt_center" align="center"><b><a href="#"><s:text name="submenu.label.product-service.view-video"/></a></b></p>
						</div>
						<div class="fr l-block-right">
							<web:productSubject var="objProdSub" productCode="TRADING_KNOWLEDGE"/>
							<c:out value="${objProdSub['product'].productOverview}" escapeXml="false"/>
							<c:if test="${not empty objProdSub['product'].wpSubjectList}">
								<ul id="ulnews">
									<c:forEach var="item" items="${objProdSub['product'].wpSubjectList}">
										<li>
											<a href="<web:url value="/home/help/trading-guide.shtml?subjectCode=${item.subjectCode}&productCode=TRADING_KNOWLEDGE"/>">
											<c:out value="${item.subjectName}"/>
											</a>
										</li>
									</c:forEach>
								</ul>
							</c:if>
						</div>
					</div>
					</div>
				</div>
			</td>
			<td width="9">&nbsp;</td>
			<td width="270" valign="top">
				<jsp:include page="ServiceRightContainer.jsp" />
			</td>
		</tr>
	</table>
</div>
