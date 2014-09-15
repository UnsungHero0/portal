<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="vn.com.vndirect.commons.utility.VNDirectWebUtilities"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
<!--
	var isOnTradingTime = <%=VNDirectWebUtilities.isOnTradingTime()%>;
	var firstVideoURL = '<s:property value="%{getURL(news.attachmentVideoNews[0].originalLink)}"/>';
	var attachmentId = '<s:property value="%{news.attachmentVideoNews[0].attachmentsId}"/>';
	var COMMENTARY_MESSAGE = '<s:text name="leftmenu.label.analysisTool.Outlook.Commentaries"/>';
	var BROKER_MESSAGE = '<s:text name="leftmenu.label.analysisTool.Outlook.Brokers"/>';
	var EXPERT_MESSAGE = '<s:text name="leftmenu.label.analysisTool.Outlook.Experts"/>';	
	var COMMENTARY_MAIN_URL = '<web:url value="/analysis/Commentaries.shtml"/>';
	var BROKER_MAIN_URL = '<web:url value="/analysis/Brokers.shtml"/>';
	var EXPERT_MAIN_URL = '<web:url value="/y-kien-chuyen-gia.shtml"/>';
//-->
</script>
<script type="text/javascript"><!--

	function selectTab(num) {
	  	for (var i=1; i <= 3; i++) {
		    $('#tabscontrol' + i).removeClass('selected');
		    $('#boxitem' + i).removeClass('enabled');
		    $('#tablicontrol' + i).removeClass('licontrol_select');		    
	  	}
	  	
	  	$('#tabscontrol' + num).addClass('selected');
	    $('#boxitem' + num).addClass('enabled');
	    $('#tablicontrol' + num).addClass('licontrol_select');
	    
	    if (num == 1) {
			loadTabMostViewedNews();
		} else if (num == 2) {
			loadTabLastestVideos();
		} else if (num == 3) {
			loadTabMostViewedReports();
		}
	}
	$(document).ready(function(){
		
				
	});

--></script>


<c:forEach var="item" items="${model.searchResult}" varStatus="status">
	${item.newsHeader}
</c:forEach>

<div class="pn_main" id="page_TTNY">	
	<div class="tabs_TTNY">		
		<ul id="ui-tabs-nav" class="ui-tabs-nav clearfix">
			
      		<li class="">
      			<a href="<web:url value='/analysis/AnalysisHome.shtml'/>"><b>Tin trong nước</b></a>
     		</li>
				
			<li class="">
				<a href="<web:url value='/analysis/MacroNews_MacWorld.shtml'/>"><b>Tin quốc tế</b></a>
			</li>
			
			
			<li class="">
				<a href="<web:url value='/analysis/Commentaries.shtml'/>"><b>Ý kiến chuyên gia</b></a>
			</li>
			
			
			<li class="">
				<a href="<web:url value='/analysis/MacroNews_Disclousure.shtml'/>"><b>Công bố thông tin</b></a>
			</li>
			
			<li class="">
				<a href="<web:url value='/analysis/MarketCalendar.shtml'/>"><b>Lịch sự kiện</b></a>
			</li>
		</ul>				
		<!--tab1-->
	</div>	
</div>

