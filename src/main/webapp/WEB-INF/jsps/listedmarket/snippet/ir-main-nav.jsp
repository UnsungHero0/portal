<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<ul class="ui-tabs-nav tab_ttpt">
	<li <web:menuOut code='home_ir_snapshot' classValue='ui-tabs-selected'/>>
		<a
			href="<web:url value='/quan-he-co-dong-vndirect/tong-quan.shtml' />"><label
				class="icon_active"></label> <s:text name="irSnapshot.topmenu">Tổng quan</s:text>
		</a>
	</li>
	<li <web:menuOut code='home_ir_news' classValue='ui-tabs-selected'/>>
		<a
			href="<web:url value='/quan-he-co-dong-vndirect/cong-bo-thong-tin.shtml' />"><label
				class="icon_active"></label> <s:text name="br.ir.news">Tin tức sự kiện</s:text>
		</a>
	</li>
	<li <web:menuOut code='home_ir_si' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/quan-he-co-dong-vndirect/thong-tin-co-ban.shtml' />"><label
				class="icon_active"></label> <s:text
				name="home.topMenu.analysis.stockInfo">Thông
                tin cổ phiếu</s:text> </a>
	</li>
	<li
		<web:menuOut code='home_ir_financial' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/quan-he-co-dong-vndirect/thong-ke-co-ban.shtml' />"><label
				class="icon_active"></label> <s:text name="br.ir.finance">Thông tin tài chính</s:text>
		</a>
	</li>
	<li
		<web:menuOut code='home_ir_management' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/quan-he-co-dong-vndirect/dieu-le-cong-ty.shtml' />"><label
				class="icon_active"></label> <s:text name="br.ir.management">Thông
                tin quản trị</s:text> </a>
	</li>
	<%--
	<li <web:menuOut code='home_ir_qa' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/quan-he-co-dong-vndirect/hoi-dap.shtml' />"><label
				class="icon_active"></label> <s:text name="br.ir.contact">Hỏi
                đáp</s:text> </a>
	</li>
    --%>
</ul>

<div class="clear"></div>