<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript">
	$(document).ready(function(){
		var url_path = window.location.pathname;
		var action = url_path.substring(url_path.lastIndexOf('/')+1, url_path.lenght);
		if(action != "home.shtml"){
			$('.openAcc').css("display", "block");
			$('.boxnewhot_top').css("display", "block");
		}
	});
</script>
<div class="menu_top_new">
	<ul>
		<li <web:menuOut code='topMenuHomePage' classValue='logo_active'/>
			id="thongtinVnDirectTopMenu" class="logo">
			<a><h2>
					<label class="icon_active"></label>
					<%-- <s:text name="home.topMenu.about">Thông tin VNDIRECT</s:text> --%>
				</h2> </a>
		</li>
		<%-- <li <web:menuOut code='topMenuHome_Service' classValue='active'/>
			id="sanphamdichvuTopMenu">
			<a><h2>
					<label class="icon_active"></label>
					<s:text name="home.topMenu.about.products">Sản phẩm dịch vụ</s:text>
				</h2> </a>
		</li> --%>
		<li class="" id="giaodichcophieuTopMenu">
			<a><h2>
					<label class="icon_active"></label>
					<s:text name="home.topMenu.onlineTrading">
					Giao dịch trực tuyến</s:text>
				</h2> </a>
		</li>
		<li <web:menuOut code='topMenuAnalysisModule' classValue='active'/>
			id="trungtamphantichTopMenu">
			<a><h2>
					<label class="icon_active"></label>
					<s:text name="home.topMenu.analysis">Trung tâm phân tích</s:text>
				</h2> </a>
		</li>
		<li <web:menuOut code='topMenuConsultantModule' classValue='active'/>
			id="trungtamtuvanTopMenu">
			<a><h2>
					<label class="icon_active"></label>
					<s:text name="home.topMenu.Consultant">Trung tâm tư vấn</s:text>
				</h2> </a>
		</li>
		<%--<li class="">
			<a><h2>
					<label class="icon_active"></label>
					Tư vấn đầu tư
				</h2> </a>
		</li>
		--%>
		<%--<li
			<web:menuOut code='topMenuInstitutionsModule' classValue='active'/>
			id="khachhangtochucTopMenu">
			<a><h2>
					<label class="icon_active"></label>
					<s:text name="home.topMenu.customerOrg">
					Khách hàng tổ chức</s:text>
				</h2> </a>
		</li>
		--%>
		<%--<li class="">
			<a><h2>
					<label class="icon_active"></label>
					E - Learning
				</h2> </a>

		</li>
		--%>
		<%-- <c:if test="${!isAuth}">
			<li class="box_button_mtk openAcc" id="trangcuatoiTopMenu" style="display: none;">
                <button class="button_small" onclick="window.location.href='<web:url value='/mo-tai-khoan.shtml'/>'" ><s:text name="homepage.openaccount.title">Mở tài khoản</s:text></button>
            </li>
		</c:if> --%>
		<c:choose>
			<c:when test="${isAuth}">
				<c:choose>
					<c:when test="${isFreeUser}">
						<li <web:menuOut code='topMenuMyPageModule' classValue='active'/>
							id="trangcuatoiTopMenu">
							<a><h2>
									<label class="icon_active"></label>
									<s:text name="home.topMenu.myPage">Trang của tôi</s:text>
								</h2> </a>
						</li>
					</c:when>
					<c:otherwise>
						<li <web:menuOut code='topMenuMyPageModule' classValue='active'/>
							id="trangcuatoiTopMenu">
							<a><h2>
									<label class="icon_active"></label>
									<s:text name="home.topMenu.myPage">Trang của tôi</s:text>
								</h2> </a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<%-- <li
					<web:menuOut code='subMenuHome_OpenAccount' classValue='active'/>
					class="openAcc" id="trangcuatoiTopMenu">
					<a href="<web:url value='/mo-tai-khoan.shtml'/>"><h2>
							<label class="icon_active"></label>
							<s:text name="home.topMenu.openAcc">Mở tài khoản</s:text>
						</h2> </a>
				</li> --%>
				<li class="box_button_mtk openAcc" id="trangcuatoiTopMenu" style="display: none;">
                	<button class="button_small" onclick="window.location.href='<web:url value='/mo-tai-khoan-nha-dau-tu.shtml'/>'" ><s:text name="homepage.openaccount.title">Mở tài khoản</s:text></button>
           		 </li>
			</c:otherwise>
		</c:choose> 
	</ul>
</div>