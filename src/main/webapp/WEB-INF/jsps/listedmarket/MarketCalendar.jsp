<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://www.webapp.com.vn/tags-fmt" prefix="webFmt" %>

<script type="text/javascript">
<!--
$().ready(function() {	
	$.web_autocomplete.symbols('symbolID', URL_SYMBOL_AUTO_SUGGESTION );
});

function doSearch(){
	document.location.hrf="<web:url value="../listed/MarketCalendar.shtml"/>";
	document.fMarketCalendar.submit();
	return;
}

function _goTo(index){
	document.location.hrf="<web:url value="../listed/MarketCalendar.shtml"/>";
	document.getElementById("fMarketCalendar_pagingInfo_indexPage").value = index;
	document.fMarketCalendar.submit();
	return;
}

function doSearchByDate(strDate){
	document.location.hrf="<web:url value="../listed/MarketCalendar.shtml"/>";
	document.getElementById("RightsDate").value=strDate;
	document.getElementById("Type_Of_Date").value="";
	document.fMarketCalendar.submit();
	return;
}
//-->
</script>
<div id="content_ttpt">    

    <ul class="ui-tabs-nav tab_ttpt">
        <li
            <web:menuOut code='subMenuAnalysis_News_MacVN' classValue='ui-tabs-selected'/>>
            <a href="<web:url value='/tin-trong-nuoc.shtml'/>">
                <label class="icon_active"></label><s:text name="analysis.news.localNews">Tin trong nước</s:text>
            </a>
        </li>
        <li
            <web:menuOut code='subMenuAnalysis_News_MacWorld' classValue='ui-tabs-selected'/>>
            <a href="<web:url value='/tin-quoc-te.shtml'/>">
                <label class="icon_active"></label><s:text name="analysis.news.internationalNews">Tin quốc tế</s:text>                     
            </a>
        </li>
        <%-- <li
            <web:menuOut code='subMenuAnalysis_News_MarketCommentary' classValue='ui-tabs-selected'/>>
            <a href="<web:url value='/nhan-dinh-thi-truong'/>">
                <label class="icon_active"></label><s:text name="analysis.news.marketCommentary">Nhận định TT</s:text>
            </a>
        </li>
        <li
            <web:menuOut code='subMenuAnalysis_News_Experts' classValue='ui-tabs-selected'/>>
            <a href="<web:url value='/y-kien-chuyen-gia.shtml'/>">
                <label class="icon_active"></label><s:text name="analysis.news.expertOpinions">Ý kiến chuyên gia</s:text>                      
            </a>
        </li> --%>
        <li
            <web:menuOut code='subMenuAnalysis_News_Disclosure' classValue='ui-tabs-selected'/>>
            <a href="<web:url value='/cong-bo-thong-tin.shtml'/>">
                <label class="icon_active"></label><s:text name="analysis.news.disclosureInformation">Công bố thông tin</s:text>           
            </a>
        </li>
        <li
            <web:menuOut code='subMenuAnalysis_News_Marketcalendar' classValue='ui-tabs-selected'/>>
            <a href="<web:url value='/lich-su-kien.shtml'/>">
                <label class="icon_active"></label><s:text name="analysis.news.marketCalendar">Lịch sự kiện</s:text>           
            </a>
        </li>
    </ul>
    
    <form name="fMarketCalendar" id="fMarketCalendar"  method="post">
        <input id="fMarketCalendar_pagingInfo_indexPage" type="hidden" name="pagingInfo.indexPage" value="1" />
        <input type="hidden" name="srtRightsDate"  value="" id="RightsDate" />
	    
	    <div class="clear"></div>
		
		<div class="content_small">
			<div class="content_tab" id="tab-1">
				<div class="content_ttpt" style="width: 695px;">
				    <h1 class="title_lichsucp"><s:text name="analysis.news.calendarEventOn">LỊCH SỰ KIỆN CỦA CỔ PHIẾU</s:text></h1>
					<div class="paging">
						<web:showPaging usingURLForNav="false" navAction="_goTo" pagingInfo="${pagingInfo}"></web:showPaging>
					</div>
					
					<div class="box_lichsukien">
						<s:actionerror cssStyle="color: red; display: inline; text-align: left; font-weight: bold;"></s:actionerror>
		                <table cellpadding="0" cellspacing="0" border="0" width="100%">
		                    <tr>
		                        <td class="rowa"><strong><s:text name="web.label.MarketCalendarAction.form.CellNames.RightsDate"/></strong></td>
		                        <td class="rowb"><strong><s:text name="web.label.MarketCalendarAction.form.CellNames.RegisterDate"/></strong></td>
		                        <td class="rowc"><strong><s:text name="web.label.MarketCalendarAction.form.CellNames.EventDate"/></strong></td>
		                        <td class="rowd"><strong><s:text name="web.label.symbol"/></strong></td>
		                        <td class="rowe"><strong><s:text name="web.label.MarketCalendarAction.form.CellNames.EventType"/></strong></td>
		                        <td class="rowf"><strong><s:text name="web.label.detail"/></strong></td>
		                       </tr>
		                       <s:iterator value="model.searchResult" status="status"> 
		                       <tr>
		                          <td class="rowa"><s:date name="rightsDate" format="dd/MM/yyyy"/>&nbsp;</td>
		                          <td class="rowb"><s:date name="registerDate" format="dd/MM/yyyy"/>&nbsp;</td>
		                          <td class="rowc"><s:date name="eventDate" format="dd/MM/yyyy"/>&nbsp;</td>
		                          <td class="rowd"><s:property value="symbol"/></td>
		                          <td class="rowe" valign="top"><s:property value="eventDesc"/>&nbsp;</td>
		                          <td class="rowf"><s:property value="eventNote"/>&nbsp;</td>
		                       </tr>
		                       </s:iterator>
		               </table>
					</div>				
				</div>	
				
				<div id="c-column" class="width340" style="width: 263px;">
                        <div class="boxcalender" id="calendar-container"></div>
                             
                        <ul class="locsukien">
                            <li class="title"><span class="iconloc"></span><s:text name="button.search">Lọc sự kiện</s:text></li>
                            <li>
                                <span class="roa"><s:text name="web.label.symbol"/></span>
							    <span class="rob">
							         <input  type="text" name="ifoMarketCalendar.symbol" id="symbolID" class="input" value="<c:out value='${model.ifoMarketCalendar.symbol}'/>"/>
							    </span>
							</li>
							<li>
							    <span class="roa"><s:text name="web.label.time"/></span>
							    <span class="rob">
								      <web:refData var="identities" group="MATKET_CAL_DATE_TYPE"/>
		                              <s:select   
		                              headerKey="" 
		                              headerValue="%{getText('web.label.MarketCalendarAction.form.SelectAll')}"  
		                              name="ifoMarketCalendar.searchTypeOfDate" 
		                              list="identities" 
		                              listValue="description" 
		                              listKey="itemCode" 
		                              theme="simple"
		                              cssStyle="width:100%; float:left; height:18px;"
		                              id="Type_Of_Date"/>
							    </span>							 
							</li>
							
							<li>
                                <span class="roa"><s:text name="web.label.MarketCalendarAction.form.SearchEventType"/></span>
                                <span class="rob">
                                      <web:refData var="identities" group="MATKET_CAL_DATE_TYPE"/>
                                      <web:refData var="identities" group="MARK_CAL_EVENT_TYPE" orderBy="DESCRIPTION"/>
                                      <s:select   
                                      headerKey="" 
                                      headerValue="%{getText('web.label.MarketCalendarAction.form.SelectAll')}"  
                                      name="ifoMarketCalendar.eventType" 
                                      list="identities" 
                                      listValue="description" 
                                      listKey="itemCode" 
                                      theme="simple"
                                      cssStyle="width:100%; float:left; height:18px;"                                      
                                      id="Event_Type"/>
                                </span>                          
                            </li>
                            	
                            <li>
							     <span class="roa">&nbsp;</span>

							<%--<span class="button_long" style="text-align: center;">
							         <input type="button" onclick="doSearch()" value="<s:text name="button.search">Tìm kiếm</s:text>">
							     </span>
							     --%>
							<input type="button" class="iButton"
                        onclick="doSearch()"
                        value='<s:text name="button.search"/>'>
							</li>                                                       		
                        </ul>                                                  
                    </div>
					
			</div>
		</div>
	</form>
	  
</div>

<!---->
<style type="text/css">
.special { background: #FF9700; color: #FFFFFF; font-weight: bold; padding: 1px 1px 1px 1px;}
</style>
<script type="text/javascript">

var SPECIAL_DAYS = <c:out value='${model.eventDay}' />;

var oMarketCalendar = null;
function updateSpecialDays(oMarketCalendarBean) {
	// alert("oMarketCalendarBean: " + oMarketCalendarBean);
	if(oMarketCalendarBean != null) {
		var key = oMarketCalendarBean.year + "." + oMarketCalendarBean.month;
		// alert(key + " --- " +  oMarketCalendarBean.days);
		SPECIAL_DAYS[key] = oMarketCalendarBean.days;
	}
};

/**
*
*/
function dateIsSpecial(year, month, day) {
	var key = year + "." + month;	
    var m = SPECIAL_DAYS[key];
    if (!m) return false;
    for (var i in m)  {
    	if (m[i] == day) return true;
    }
    return false;
  };

  function dateChanged(calendar) {
	doSearchByDate(calendar.date.print("%Y/%m/%d"));
  };

  function ourDateStatusFunc(date, y, m, d) {
    if (dateIsSpecial(y, m, d))
      return "special";
    else
      return false; // other dates are enabled
      // return true if you want to disable other dates
  };

  Calendar.setup(
    {
      flat         : "calendar-container", // ID of the parent element
      flatCallback : dateChanged,          // our callback function
      date			: "<c:out value='${model.srtRightsDate}' />",
      dateStatusFunc : ourDateStatusFunc
    }
  );
</script>