<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<jsp:include page="/WEB-INF/jsps/listedmarket/snippet/ir-main-nav.jsp"></jsp:include>

<ul class="ui-tabs-nav tab_button" style="margin-bottom: 10px;">
	<li
		<web:menuOut code='home_ir_management_regulations' classValue='ui-tabs-selected'/>>
		<a
			href="<web:url value='/quan-he-co-dong-vndirect/dieu-le-cong-ty.shtml' />"><span><s:text
					name="br.ir.management.regulations"> Điều
				lệ công ty</s:text> </span> </a>
	</li>
	<li
		<web:menuOut code='home_ir_management_directorsBoard' classValue='ui-tabs-selected'/>>
		<a
			href="<web:url value='/quan-he-co-dong-vndirect/hoi-dong-quan-tri.shtml' />"><span><s:text
					name="br.ir.management.board">Hội
                đồng quản trị</s:text> </span> </a>
	</li>
	<li
		<web:menuOut code='home_ir_management_inspectionCommittee' classValue='ui-tabs-selected'/>>
		<a
			href="<web:url value='/quan-he-co-dong-vndirect/ban-kiem-soat.shtml' />"><span><s:text
					name="br.ir.management.inspection">Ban
                kiểm soát</s:text> </span> </a>
	</li>
	<!-- li
		<web:menuOut code='home_ir_management_governanceCommittee' classValue='ui-tabs-selected'/>>
		<a
			href="<web:url value='/quan-he-co-dong-vndirect/ban-dieu-hanh.shtml' />"><span><s:text
					name="br.ir.management.governance">Ban
				điều hành</s:text> </span> </a>
	</li-->
</ul>

<div class="clear"></div>