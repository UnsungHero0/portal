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
								<b>&nbsp;&nbsp;<s:text name="submenu.label.product-service.infoService">Info Service</s:text>&nbsp;&nbsp;</b>
							</span>|<c:if test="${locale == 'vn'}"><span>							
								<a href="<web:url value="/home/service/ServiceAdvice.shtml"/>"><s:text name="submenu.label.product-service.adviceService">Finance Advice Service</s:text></a>							
							</span>|</c:if><span>
								<a href="<web:url value="/home/service/ServiceProduct.shtml"/>"><s:text name="submenu.label.product-service.specialProduct">Special Product</s:text></a>
							</span>|<span>
								<a href="<web:url value="/home/service/ServiceGuide.shtml"/>"><s:text name="submenu.label.product-service.tradingGuide">Trading Guide</s:text></a>
							</span>
						   </li>
					   </ul>
					   <div class="ct-textnone">
							<div class="clearfix l-block">
								<p class="Title-general"><b><s:text name="Message.product-service.research-center"/></b></p>
								<div class="fl l-block-left"><p class="fix-img"><a href="#"><img src='<s:url value="/images/img/dvtt_01.gif" />' /></a></p>
									<p class="tt_center" align="center"><b><a href="#"><s:text name="submenu.label.product-service.research-center"/></a></b></p>
								</div>
								<div class="fr l-block-right">
									<!-- Begin: View Product -->
									<web:productSubject var="objProdSub" productCode="ANALYSIS_CENTER"/>
									<c:out value="${objProdSub['product'].productOverview}" escapeXml="false"/>
									<div class="center_inner clearfix">
										<div style="font-weight: bold;"><c:out value="${objProdSub['product'].productName}"/></span></div>
										<ul id="ulnews">
											<c:forEach var="item" items="${objProdSub['product'].wpSubjectList}">
												<li>
													<a href="<web:url value="/home/service/viewInfoServiceDetail.shtml?subjectCode=${item.subjectCode}&productCode=ANALYSIS_CENTER"/>">
													<c:out value="${item.subjectName}"/>
													</a>
												</li>
											</c:forEach>
										</ul>
									</div>
									<!-- End: View Product -->
								</div>
							</div>
						   <!---->
							<div class="clearfix l-block-end">
								<p class="Title-general"><b><s:text name="rightmenu.label.info.investment.support.content"/>:</b></p>
								<div class="fl l-block-left"><p class="fix-img"><a href="#"><img src='<s:url value="/images/img/dvtt_02.gif" />' /></a></p>
									<p class="tt_center" align="center"><b><a href="#"><s:text name="submenu.label.product-service.view-video"/></a></b></p>
								</div>
								<div class="fr l-block-right">
									<!-- Begin: View Product -->
									<web:productSubject var="objProdSub" productCode="ADVISORY_INVESMENT"/>
									<c:out value="${objProdSub['product'].productOverview}" escapeXml="false"/>
									<div class="center_inner clearfix">
										<div style="font-weight: bold;"><c:out value="${objProdSub['product'].productName}"/></span></div>
										<ul id="ulnews">
											<c:forEach var="item" items="${objProdSub['product'].wpSubjectList}">
												<li>
													<a href="<web:url value="/home/service/viewInfoServiceDetail.shtml?subjectCode=${item.subjectCode}&productCode=ADVISORY_INVESMENT"/>">
													<c:out value="${item.subjectName}"/>
													</a>
												</li>
											</c:forEach>
										</ul>
									</div>
									<!-- End: View Product -->

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
