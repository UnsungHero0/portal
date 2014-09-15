<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<jsp:include page="/WEB-INF/jsps/listedmarket/snippet/ir-main-nav.jsp"></jsp:include>

<ul class="ui-tabs-nav tab_button" style="margin-bottom: 15px;">
	<li
		<web:menuOut code='home_ir_financial_keyStatistic' classValue='ui-tabs-selected'/>>
		<a
			href="<web:url value='/quan-he-co-dong-vndirect/thong-ke-co-ban.shtml' />"><span><s:text
				name="br.ir.finance.basic">Thống
				kê cơ bản</s:text></span>
		</a>
	</li>
	<li
		<web:menuOut code='home_ir_financial_financeReport' classValue='ui-tabs-selected'/>>
		<a
			href="<web:url value='/quan-he-co-dong-vndirect/bao-cao-tai-chinh.shtml' />"><span><s:text
				name="br.ir.finance.report">Báo
				cáo tài chính</s:text> </span></a>
	</li>
	<li
		<web:menuOut code='home_ir_financial_annualReport' classValue='ui-tabs-selected'/>>
		<a
			href="<web:url value='/quan-he-co-dong-vndirect/bao-cao-thuong-nien-cao-bach.shtml' />"><span><s:text
				name="br.ir.finance.annual">Báo
				cáo thường niên và cáo bạch</s:text> </span></a>
	</li>
</ul>

<div class="clear"></div>