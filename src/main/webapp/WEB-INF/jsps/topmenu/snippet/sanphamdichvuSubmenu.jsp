<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>

<div class="box_load">
	<ul class="list_menu_sup"
		style="margin-left: 150px; border-right: 1px dotted #333;">
		<li class="title">
			<a href="<web:url value='/vndirect/san-pham-dich-vu.shtml'/>"><s:text
					name="home.topMenu.about.products">Sản phẩm dịch vụ</s:text> </a>
		</li>
		<li>
			<a <web:menuOut code='subMenuHome_Service' classValue='active'/>
				href="<web:url value='/vndirect/san-pham-dich-vu.shtml'/>"><s:text
					name="br.service.about">Giới
                thiệu</s:text> </a>
		</li>
		<li>
			<a <web:menuOut code='subMenuHome_OpenAccount' classValue='active'/>
				href="<web:url value='/mo-tai-khoan.shtml'/>"><s:text
					name="br.service.openaccount">Mở tài khoản</s:text> </a>
		</li>
	</ul>
	<a class="" style="float: right; margin: -15px 30px 0 0;"
		onclick="window.open('https://www.vndirect.com.vn/portal/y-tuong-dau-tu/co-phieu-khuyen-nghi.shtml');">
		<web:document var="imgDoc" productCode="BANNER_SPDV_ON_MENU"
                download="true" />
        <img src="${imgDoc['document'].documentUri}" />
		<%-- <img
			src="<web:url value='/images/thumbs/bannerSmartT.png'/>" /> </a> --%>
	<a class="icon_dow_supmenu"></a>
</div>