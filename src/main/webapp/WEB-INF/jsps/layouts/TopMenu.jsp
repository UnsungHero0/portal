<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>

<!-- fix topMenu -->	
	
<div id="navi_top_new">
	<!-- main menu -->
	<jsp:include page="/WEB-INF/jsps/topmenu/snippet/mainMenu.jsp"></jsp:include>
	<!-- submenu content -->
	<div class="supmenutop_new">
		<ul id="submenuContents" class="">
			<div id="wrapperSubmenuContents">
				<li id="thongtinVndirect" class="content_menu_top_new">
					<jsp:include
						page="/WEB-INF/jsps/topmenu/snippet/thongtinVndirectSubmenu.jsp"></jsp:include>
				</li>
				<%-- <li id="sanphamdichvu" class="content_menu_top_new">
                    <jsp:include
                        page="/WEB-INF/jsps/topmenu/snippet/sanphamdichvuSubmenu.jsp"></jsp:include>
                </li> --%>
				<li id="giaodichcophieu" class="content_menu_top_new">
					<jsp:include
						page="/WEB-INF/jsps/topmenu/snippet/giaodichcophieuSubmenu.jsp"></jsp:include>
				</li>
				<li id="trungtamphantich" class="content_menu_top_new">
					<jsp:include
						page="/WEB-INF/jsps/topmenu/snippet/trungtamphantichSubmenu.jsp"></jsp:include>
				</li>
				<li id="trungtamtuvan" class="content_menu_top_new">
					<jsp:include
						page="/WEB-INF/jsps/topmenu/snippet/trungtamtuvanSubmenu.jsp"></jsp:include>
				</li>
				<%-- <li id="khachhangtochuc" class="content_menu_top_new">
					<jsp:include
						page="/WEB-INF/jsps/topmenu/snippet/khachhangtochucSubmenu.jsp"></jsp:include>
				</li> --%>
				<li id="trangcuatoi" class="content_menu_top_new">
					<jsp:include
						page="/WEB-INF/jsps/topmenu/snippet/trangcuatoiSubmenu.jsp"></jsp:include>
				</li>
			</div>
		</ul>
	</div>
</div>
<div class="menu_hienthi_luachon" style="display: none;">
	<div class="content_hienthi">
		<c:if test="${not empty breadcrumbs}">
			<span class="text"><c:choose>
					<c:when test="${fn:length(breadcrumbs) gt 1}">
						<c:forEach items="${model.breadcrumbs}" varStatus="i">
							<c:if test="${i.count gt 2 && i.count mod 2 eq 1}">
								<c:choose>
									<c:when test="${i.count eq 3}">
									   <a style="cursor: pointer;" id="${breadcrumbs[i.count-1]}">${breadcrumbs[i.count-2]}</a> >
									</c:when>
									<c:otherwise>
										<a href="<web:url value='${breadcrumbs[i.count-1]}'/>">${breadcrumbs[i.count-2]}</a> >
                                </c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
                  ${breadcrumbs[0]}
             </c:when>
					<c:otherwise>
                ${breadcrumbs[0]}
             </c:otherwise>
				</c:choose> </span>
		</c:if>
	</div>
</div>
