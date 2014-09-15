<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<ul class="ui-tabs-nav tab_ttpt">
	<%-- <li
		<web:menuOut code='subMenuAnalysis_News_MacVN' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/tin-trong-nuoc.shtml'/>"> <label
			class="icon_active"></label>
		<s:text name="analysis.news.localNews">Tin trong nước</s:text>
	</a>
	</li>
	<li
		<web:menuOut code='subMenuAnalysis_News_MacWorld' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/tin-quoc-te.shtml'/>"> <label
			class="icon_active"></label>
		<s:text name="analysis.news.internationalNews">Tin quốc tế</s:text>
	</a>
	</li> --%>
	<li
		<web:menuOut code='subMenuAnalysis_News_MarketCommentary' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/nhan-dinh-thi-truong.shtml'/>"> <label
			class="icon_active"></label>
		<s:text name="analysis.news.marketCommentary">Nhận định TT</s:text>
	   </a>
	</li>
	<li
		<web:menuOut code='subMenuAnalysis_News_Experts' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/y-kien-chuyen-gia.shtml'/>"> <label
			class="icon_active"></label>
		<s:text name="analysis.news.expertOpinions">Ý kiến chuyên gia</s:text>
	   </a>
	</li>
	<%-- <li
        <web:menuOut code='subMenuAnalysis_StockHighLights' classValue='ui-tabs-selected'/>>
        <a href="<web:url value='/co-phieu-tam-diem.shtml'/>"> <label
            class="icon_active"></label>
        <s:text name="analysis.news.stockHighlights">CPTD</s:text>
       </a>
    </li> --%>
	<%-- <li
		<web:menuOut code='subMenuAnalysis_News_Disclousure' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/cong-bo-thong-tin.shtml'/>"> <label
			class="icon_active"></label>
		<s:text name="analysis.news.disclosureInformation">Công bố thông tin</s:text>
	</a>
	</li>
	<li
		<web:menuOut code='subMenuAnalysis_News_Marketcalendar' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/lich-su-kien.shtml'/>"> <label
			class="icon_active"></label>
		<s:text name="analysis.news.marketCalendar">Lịch sự kiện</s:text>
	</a>
	</li> --%>
</ul>