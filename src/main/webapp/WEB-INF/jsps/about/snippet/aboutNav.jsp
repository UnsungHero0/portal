<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!-- sub menus -->
<ul class="ui-tabs-nav tab_ttpt">
	<li
		<web:menuOut code='subMenuHome_Info_Vision' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/vndirect/gioi-thieu-vndirect.shtml'/>"><label
				class="icon_active"></label>
			<s:text name="aboutVndirect.aboutUs">Giới thiệu
			VNDIRECT</s:text> </a>
	</li>
	<li
		<web:menuOut code='subMenuHome_Info_History' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/vndirect/lich-su-phat-trien.shtml'/>"><label
				class="icon_active"></label>
			<s:text name="aboutVndirect.history">Lịch sử phát
			triển</s:text> </a>
	</li>
	<li
		<web:menuOut code='subMenuHome_Info_Board' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/vndirect/doi-ngu-lanh-dao.shtml'/>"><label
				class="icon_active"></label>
			<s:text name="aboutVndirect.boards">Đội
			ngũ lãnh đạo</s:text> </a>
	</li>
    <li
        <web:menuOut code='subMenuHome_VnDirect_news' classValue='ui-tabs-selected'/>>
        <a href="<web:url value='/vndirect/tin-vndirect.shtml'/>"><label
                class="icon_active"></label><s:text name="aboutVndirect.vndirectNews">Tin VNDIRECT</s:text></a>
    </li>	
</ul>
