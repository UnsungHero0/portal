<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<div class="box_load">
	<a class="left" href="<web:url value=''/>"><img
			src="<web:url value='/images/thumbs/thuim_iphone.png'/>" /> </a>
	<ul class="list_menu_sup line right">
		<li class="title">
		    <a href="<web:url value='/to-chuc-dau-tu/tong-quan.shtml'/>">
			<s:text name="institutions.investors">DỊCH VỤ GIAO DỊCH</s:text>
			</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuIsntitutions_Invest_Overview' classValue='active'/>
				href="<web:url value='/to-chuc-dau-tu/tong-quan.shtml'/>"><s:text
					name="institutions.overview">Giới thiệu</s:text> </a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuIsntitutions_Invest_PS' classValue='active'/>
				href="<web:url value='/to-chuc-dau-tu/san-pham-dich-vu.shtml'/>"><s:text
					name="institutions.productsAndServices">Sản phẩm – Dịch vụ</s:text>
			</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuIsntitutions_Invest_Consultants' classValue='active'/>
				href="<web:url value='/to-chuc-dau-tu/doi-ngu-chuyen-gia.shtml'/>"><s:text
					name="institutions.consultants">Đội ngũ chuyên gia</s:text> </a>
		</li>
	</ul>
	<ul class="list_menu_sup line right" style="width: 165px;">
		<li class="title">
		    <a href="<web:url value='/to-chuc-phat-hanh/tong-quan.shtml'/>">
			    <s:text name="institutions.issuers">DỊCH VỤ NGÂN HÀNG ĐẦU TƯ</s:text>
			</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuIsntitutions_Issuers_Overview' classValue='active'/>
				href="<web:url value='/to-chuc-phat-hanh/tong-quan.shtml'/>"><s:text
					name="institutions.overview">Giới thiệu</s:text> </a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuIsntitutions_Issuers_PS' classValue='active'/>
				href="<web:url value='/to-chuc-phat-hanh/san-pham-dich-vu.shtml'/>"><s:text
					name="institutions.productsAndServices">Sản phẩm – Dịch vụ</s:text>
			</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuIsntitutions_Issuers_Consultants' classValue='active'/>
				href="<web:url value='/to-chuc-phat-hanh/doi-ngu-chuyen-gia.shtml'/>"><s:text
					name="institutions.consultants">Đội ngũ chuyên gia</s:text> </a>
		</li>
	</ul>
	<a class="icon_dow_supmenu"></a>
</div>