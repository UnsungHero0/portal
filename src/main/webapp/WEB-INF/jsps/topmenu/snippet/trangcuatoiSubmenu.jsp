<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>
<div class="box_load">
	<div class="bannerTrangCuaToi">
		<a class="left" style=""><img
				src="<web:url value='/images/thumbs/bannerTrangCuaToi.png'/>" /> </a>
		<span class="info1"><s:text name="banner.trangcuatoi.info1">QUẢN LÝ THÔNG TIN DỄ DÀNG</s:text>
		</span>
		<span class="info2"><s:text name="banner.trangcuatoi.info2">MỌI LÚC - MỌI NƠI</s:text>
		</span>
		<div class="clear"></div>
	</div>
	<ul class="list_menu_sup line right">
		<c:choose>
			<c:when test="${isAuth}">
				<c:choose>
					<c:when test="${isFreeUser}">
						<li class="title">
							<a href="<web:url value='/thong-tin-co-ban-tai-khoan-mien-phi.shtml'/>"><s:text name="myPage.infor.manage"/></a>
						</li>
						<li>
							<a href="<web:url value='/thong-tin-co-ban-tai-khoan-mien-phi.shtml'/>"><s:text name="myPage.infor.basic"/></a>
						</li>
						<li>
							<a href="<web:url value='/thong-tin-giao-dich.shtml'/>"><s:text name="myPage.infor.trading"/></a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="title">
							<a href="<web:url value='/thong-tin-co-ban.shtml'/>"><s:text name="myPage.infor.manage"/></a>
						</li>
						<li>
							<a href="<web:url value='/thong-tin-co-ban.shtml'/>"><s:text name="myPage.infor.basic"/></a>
						</li>
						<li>
							<a href="<web:url value='/thong-tin-giao-dich.shtml'/>"><s:text name="myPage.infor.trading"/></a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:when>
		</c:choose>
	</ul>
	<a class="icon_dow_supmenu"></a>
</div>