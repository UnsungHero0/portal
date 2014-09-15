<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<div id="wrapper">
	<div id="container">
		<div id="content">
			<jsp:include page="/WEB-INF/jsps/home/help/snippet/helpNav.jsp"></jsp:include>

			<div class="introduction clearfix">
				<div id="phan_tich_co_ban">
					<div class="clearfix content_left">
						<c:set var="isProduct" value="false" />
						<c:if test="${(not empty wpProduct) && (empty wpSubject)}">
							<c:set var="isProduct" value="true" />
						</c:if>
						<div class="center_inner clearfix">
							<div class="clearfix">
								<c:choose>
									<c:when test="${isProduct}">
                                  ${wpProduct.productOverview}
                              </c:when>
									<c:otherwise>
                                  ${wpSubject.subjectContent}
                              </c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div id="c-column" class="width340">
						<div class="box_listnew">
							<div class="titleBar titleBar-margin-icon">
								<span id="title"><s:text name="help.right">CÂU HỎI KHÁC</s:text> </span>
							</div>
							<hr class="hrline" />
							<div class="content_small">
								<c:if test="${not empty wpProduct.wpSubjectList}">
									<ul class="list15">
										<c:forEach var="item" items="${wpProduct.wpSubjectList}"
											varStatus="i">
											<c:set var="noline" value=""></c:set>
											<c:if test="${i.count == 1}">
												<c:set var="noline" value="noline"></c:set>
											</c:if>
											<li class="${noline}">
												<a
													href="<web:url value='/${wpProduct.productCode}/${item.subjectCode}.shtml' />">
													<img src="<web:url value='/images/icons/icon-1.png' />"/> <c:out value="${item.subjectName}" /> </a>
											</li>
										</c:forEach>
									</ul>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


