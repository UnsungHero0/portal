<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<jsp:include page="/WEB-INF/jsps/listedmarket/snippet/ir-main-nav.jsp"></jsp:include>

<ul class="ui-tabs-nav tab_button" style="margin-bottom: 15px;">
	<li <web:menuOut code='home_ir_news_disclosure' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/quan-he-co-dong-vndirect/cong-bo-thong-tin.shtml' />"><span><s:text name="home.ir.disclosure">Công bố thông tin</s:text></span>
		</a>
	</li>
	<li <web:menuOut code='home_ir_news_companyEvent' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/quan-he-co-dong-vndirect/tin-doanh-nghiep.shtml' />"><span><s:text name="home.ir.companyEvents">Tin doanh nghiệp</s:text></span>
		</a>
	</li>
	<li <web:menuOut code='home_ir_news_MarketCalendar' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/quan-he-co-dong-vndirect/lich-su-kien.shtml' />"><span><s:text name="home.ir.calendar">Lịch sự kiện</s:text></span>
		</a>
	</li>
</ul>
<div class="clear"></div>