<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<!-- This page is the right content of each page of Open Account Module -->

<!-- right -->
<div id="c-column" class="width340" style="padding-bottom: 20px;">
	<div class="box_listnew">
		
		<div class="titleBar titleBar-margin-icon">
			<span id="title"><s:text name="home.account.right.accountType">LOẠI TÀI KHOẢN</s:text> </span>
		</div>
		<hr class="hrline" />
		<div class="content_small">
			<ul class="listmenu_motaikhoan">
				<li id="first"
					<web:menuOut code='subMenuHome_OpenAccount_DirectAccount' classValue='active'/>>
					<a href="<web:url value='/mo-tai-khoan-nha-dau-tu.shtml' />">
						>> <s:text name="home.account.right.directType">TÀI KHOẢN NHÀ ĐẦU TƯ</s:text></a>
				</li>
				<li
					<web:menuOut code='subMenuHome_OpenAccount_FreeUser' classValue='active'/>>
					<a href="<web:url value='/mo-tai-khoan-mien-phi.shtml' />"> >> <s:text name="home.account.right.freeType">TÀI
						KHOẢN MIỄN PHÍ</s:text></a>
				</li>
				<%--<li
					<web:menuOut code='subMenuHome_OpenAccount_RealEstateTrading' classValue='active'/>>
					<a
						href="<web:url value='/mo-tai-khoan-moi-gioi-bat-dong-san.shtml' />">
						>> <s:text name="home.account.right.realEstateTradingType">TÀI KHOẢN SÀN GD BĐS</s:text></a>
				</li>
				<li
					<web:menuOut code='subMenuHome_OpenAccount_RealEstateBroker' classValue='active'/>>
					<a
						href="<web:url value='/mo-tai-khoan-giao-dich-bat-dong-san.shtml' />">
						>> <s:text name="home.account.right.realEstateBrokerType">TÀI KHOẢN MÔI GIỚI BĐS</s:text></a>
				</li>
			--%></ul>
		</div>
	</div>
</div>
<!-- End right -->
