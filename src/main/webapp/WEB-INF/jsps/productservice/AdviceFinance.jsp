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
							</span>|<span>
								<b>&nbsp;&nbsp;<s:text name="submenu.label.product-service.adviceService">Finance Advice Service</s:text>&nbsp;&nbsp;</b>
							</span>|<span>
								<a href="<web:url value="/home/service/ServiceProduct.shtml"/>"><s:text name="submenu.label.product-service.specialProduct">Special Product</s:text></a>
							</span>|<span>
								<a href="<web:url value="/home/service/ServiceGuide.shtml"/>"><s:text name="submenu.label.product-service.tradingGuide">Trading Guide</s:text></a>
							</span>
						 </li>
					 </ul>
					 <div class="ct-textnone">
						 <div class="clearfix l-block">
							<p class="Title-general"><b><s:text name="Message.product-service.advice-finance.listing"/></b></p>
							<div class="fl l-block-left"><p class="fix-img"><a href="#"><img src='<s:url value="/images/img/icon1.jpg" />' /></a></p></div>
							<div class="fr l-block-right">
								<web:productSubject var="objProdSub" productCode="ADVICE_LISTING"/>
								<c:out value="${objProdSub['product'].productOverview}" escapeXml="false"/>
								<c:if test="${not empty objProdSub['product'].wpSubjectList}">
									<ul id="ulnews">
										<c:forEach var="item" items="${objProdSub['product'].wpSubjectList}">
											<li>
												<a href="<web:url value="/home/service/viewTradingServiceDetail.shtml?subjectCode=${item.subjectCode}&productCode=ADVICE_LISTING"/>">
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
							<p class="Title-general"><b><s:text name="Message.product-service.advice-finance.issuing"/></b></p>
							<div class="fl l-block-left"><p class="fix-img"><a href="#"><img src='<s:url value="/images/img/icon2.jpg" />' /></a></p></div>
							<div class="fr l-block-right">
								<web:productSubject var="objProdSub" productCode="ADVICE_ISSUING"/>
								<c:out value="${objProdSub['product'].productOverview}" escapeXml="false"/>
								<c:if test="${not empty objProdSub['product'].wpSubjectList}">
									<ul id="ulnews">
										<c:forEach var="item" items="${objProdSub['product'].wpSubjectList}">
											<li>
												<a href="<web:url value="/home/service/viewTradingServiceDetail.shtml?subjectCode=${item.subjectCode}&productCode=ADVICE_ISSUING"/>">
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
							<p class="Title-general"><b><s:text name="Message.product-service.advice-finance.underwriting"/></b></p>
							<div class="fl l-block-left"><p class="fix-img"><a href="#"><img src='<s:url value="/images/img/icon3.jpg" />' /></a></p></div>
							<div class="fr l-block-right">
								<web:productSubject var="objProdSub" productCode="ADVICE_UNDERWRITING"/>
								<c:out value="${objProdSub['product'].productOverview}" escapeXml="false"/>
								<c:if test="${not empty objProdSub['product'].wpSubjectList}">
									<ul id="ulnews">
										<c:forEach var="item" items="${objProdSub['product'].wpSubjectList}">
											<li>
												<a href="<web:url value="/home/service/viewTradingServiceDetail.shtml?subjectCode=${item.subjectCode}&productCode=ADVICE_UNDERWRITING"/>">
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
							<p class="Title-general"><b><s:text name="Message.product-service.advice-finance.business"/></b></p>
							<div class="fl l-block-left"><p class="fix-img"><a href="#"><img src='<s:url value="/images/img/icon4.jpg" />' /></a></p></div>
							<div class="fr l-block-right">
								<web:productSubject var="objProdSub" productCode="ADVICE_BUSINESS"/>
								<c:out value="${objProdSub['product'].productOverview}" escapeXml="false"/>
								<c:if test="${not empty objProdSub['product'].wpSubjectList}">
									<ul id="ulnews">
										<c:forEach var="item" items="${objProdSub['product'].wpSubjectList}">
											<li>
												<a href="<web:url value="/home/service/viewTradingServiceDetail.shtml?subjectCode=${item.subjectCode}&productCode=ADVICE_BUSINESS"/>">
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
							<p class="Title-general"><b><s:text name="Message.product-service.advice-finance.restructuring"/></b></p>
							<div class="fl l-block-left"><p class="fix-img"><a href="#"><img src='<s:url value="/images/img/icon5.jpg" />' /></a></p></div>
							<div class="fr l-block-right">
								<web:productSubject var="objProdSub" productCode="ADVICE_RESTRUCTURING"/>
								<c:out value="${objProdSub['product'].productOverview}" escapeXml="false"/>
								<c:if test="${not empty objProdSub['product'].wpSubjectList}">
									<ul id="ulnews">
										<c:forEach var="item" items="${objProdSub['product'].wpSubjectList}">
											<li>
												<a href="<web:url value="/home/service/viewTradingServiceDetail.shtml?subjectCode=${item.subjectCode}&productCode=ADVICE_RESTRUCTURING"/>">
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
							<p class="Title-general"><b><s:text name="Message.product-service.advice-finance.equilization"/></b></p>
							<div class="fl l-block-left"><p class="fix-img"><a href="#"><img src='<s:url value="/images/img/icon6.jpg" />' /></a></p></div>
							<div class="fr l-block-right">
								<web:productSubject var="objProdSub" productCode="ADVICE_EQUITIZATION"/>
								<c:out value="${objProdSub['product'].productOverview}" escapeXml="false"/>
								<c:if test="${not empty objProdSub['product'].wpSubjectList}">
									<ul id="ulnews">
										<c:forEach var="item" items="${objProdSub['product'].wpSubjectList}">
											<li>
												<a href="<web:url value="/home/service/viewTradingServiceDetail.shtml?subjectCode=${item.subjectCode}&productCode=ADVICE_EQUITIZATION"/>">
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
							<p class="Title-general"><b><s:text name="Message.product-service.advice-finance.ir"/></b></p>
							<div class="fl l-block-left"><p class="fix-img"><a href="#"><img src='<s:url value="/images/img/icon7.jpg" />' /></a></p></div>
							<div class="fr l-block-right">
								<web:productSubject var="objProdSub" productCode="ADVICE_IR"/>
								<c:out value="${objProdSub['product'].productOverview}" escapeXml="false"/>
								<c:if test="${not empty objProdSub['product'].wpSubjectList}">
									<ul id="ulnews">
										<c:forEach var="item" items="${objProdSub['product'].wpSubjectList}">
											<li>
												<a href="<web:url value="/home/service/viewTradingServiceDetail.shtml?subjectCode=${item.subjectCode}&productCode=ADVICE_IR"/>">
												<c:out value="${item.subjectName}"/>
												</a>
											</li>
										</c:forEach>
									</ul>
								</c:if>
							</div>
						 </div>				 
						 <!---->
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
