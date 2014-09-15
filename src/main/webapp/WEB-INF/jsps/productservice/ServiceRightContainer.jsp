<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<c:choose>
	<c:when test="${locale == 'vn'}">
		<web:document var="flashDoc" productCode="FLASH_PHI_GIAO_DICH" />
		<div class="r-block">
			<object width="260" height="197">
				<param name="movie" value="<c:out value="${flashDoc['document'].documentUri}" escapeXml="false"/>">
				<embed src="<c:out value="${flashDoc['document'].documentUri}" escapeXml="false"/>" width="265" height="267"  type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer"></embed>
			</object>
		</div>				
		<div class="r-block pn_main">
	</c:when>
	<c:otherwise>
		<div class="r-block">
	</c:otherwise>
</c:choose>
	<div><img src='<s:url value="/images/front/group_3_top.gif" />' width="270" height="3" /></div>
	<div class="r-block-ct">
		<ul class="lengthtitle">
			<li class="lengthtitle-left"></li>
			<li class="lengthtitle-right"></li>
			<li class="lengthtitle-center"><h4><a href="#"><s:text name="rightmenu.label.info.vision.reasons">Reasons to open an account at VNDirect</s:text></a></h4></li>
		</ul>
		<ul class="bp clearfix">
			<li><div class="r-block-item clearfix"><a href="#"><img src='<s:url value="/images/img/img_05.gif" />' class="fr-img" /></a>
				<p class="blue_bold"><b><s:text name="rightmenu.label.info.investment.support">Investment Support</s:text></b></p>
				<cite><s:text name="rightmenu.label.info.investment.support.content">VNDirect provide investment support needs</s:text></cite>
			</div></li>
			<li><div class="r-block-item clearfix"><a href="#"><img src='<s:url value="/images/img/img_06.gif" />' class="fr-img" /></a>
				<p class="blue_bold"><b><s:text name="rightmenu.label.info.utility">Utility</s:text></b></p>
				<cite><s:text name="rightmenu.label.utility.content">VNDirect provide investment support needs</s:text></cite>
			</div></li>
			<li><div class="r-block-item end clearfix"><a href="#"><img src='<s:url value="/images/img/img_07.gif" />' class="fr-img" /></a>
				<p class="blue_bold"><b><s:text name="rightmenu.label.service">Services</s:text></b></p>
				<cite><s:text name="rightmenu.label.service.content">VNDirect provide investment support needs</s:text></cite>
			</div></li>
		</ul>
	</div>
	<div><img src='<s:url value="/images/front/group_3_bottom.gif" />' width="270" height="3" /></div>
</div>
<!---->
<div class="r-block pn_main">
	<a href="<web:url value='/home/RedirectUrl.shtml?code=HomeDirectPage&ticket=true'/>">
		<img src='<s:url value="/images/img/hd-spdv.gif" />'/>
	</a>	
</div>
<!---->
<div class="r-block pn_main">
	<a href="<web:url value="/common/viewContentDetail.shtml?productCode=DICHVU_VNDS" />"><div class="boxHelp2" >		
	</div></a>
</div>
<!---->
<div class="r-block pn_main">
	<div class="money_service"><span><a href="javascript:downloadDocument('BIEU_PHI_MOI.pdf');"><s:text name="Message.about-vndirect.vndirect-info.services-fee"/></a></span></div>
</div>
<!--
<div class="r-block pn_main">
	<span><a href="#"><img src='<s:url value="/images/img/img_29.jpg" />'/></a></span>
</div>
-->
<!---->
<div class="Control pn_main">
	<div class="control_set">
		<h2><s:text name="rightmenu.label.info.place.orders.anytime.anywhere">Đặt lệnh mọi lúc mọi nơi</s:text></h2>
		<ul>
			<li><b><s:text name="rightmenu.label.info.place.orders.at.the.floor">Đặt lệnh tại sàn</s:text></b></li>
			<li><b><s:text name="rightmenu.label.info.place.orders.by.phone">Đặt lệnh qua điện thoại</s:text><br />
					<span class="hotline">1900-5454-09</span>
				</b>
			</li>
			<li><s:text name="rightmenu.label.info.place.orders.via.internet">Đặt lệnh qua internet</s:text>: <a href="#"><b>www.vndirect.com.vn</b></a></li>
			<li><s:text name="rightmenu.label.info.place.orders.via.mobidirect">Đặt lệnh qua MobiDirect</s:text>: <a href="#"><b>mobile.vndirect.com.vn</b></a></li>
			<li><s:text name="rightmenu.label.info.lookup">Tra cứu</s:text>&nbsp;<b>SMS 6089</b></li>
		</ul>
		<c:if test="${!isAuth}">
			<div align="center">
				<a href="<web:url value="/osc/OSCAfterLogin.shtml"/>">
					<c:choose>
						<c:when test="${locale == 'vn'}">
							<img src='<s:url value="/images/button/set-login.gif" />' />
						</c:when>
						<c:otherwise>
							<img src='<s:url value="/images/button/login.jpg" />' />
						</c:otherwise>
					</c:choose>
				</a>
			</div>
		</c:if>
	</div>
</div>

<c:if test="${locale == 'vn'}">
	<div id="vndCorner" align="center">		
	</div>
	
	<script type="text/javascript">
	$().ready(function() {
		$.ajax({
			type: "POST",
			dataType: "json",
			data: "type=VNDConrner&pagingInfo.offset=1",
			url: URL_GET_NEWS_BY_TYPES,
			success: function(data){
				populateContent(data);
			},
			beforeSend: null
		})	
	});
	
	populateContent = function(data) {
		if (data.model) {
			try {
				var att;
				var cat = 'img';
				var vndCorner = $('#vndCorner');			
				var strContent = '<table>';
				var firstOtherNews = true;
				var fileName = '';
				if (data.model.searchResult != null && data.model.searchResult.length > 0) {				
					$.each(data.model.searchResult, function(i, obj){
						strContent += '<tr>';
						strContent += '<td><div class="clearfix" align="center">';
						$.each(obj.imagesList, function(j, obj2){
							var idx = obj2.indexOf("||");
							if (idx > -1 && obj2.substring(0, idx) == 'Img_VNDCorner') {						
								var imageUrl = '<img src="' + viewDownloadImage(obj2.substring(idx+2), cat, 200, 300) + '"/>';
								$.each(obj.pdfFileList, function(j, item){							
									strContent += '<a href="javascript:download(\'' + item + '\', \'newsAttach\', \'' + obj.pdfFileNames[j] + '\')">' + imageUrl + '</a>';
								})	
							}
						})
						$.each(obj.pdfFileList, function(j, item){							
								strContent += '<p><b class="link">' + '<a href="javascript:download(\'' + item + '\', \'newsAttach\', \'' + obj.pdfFileNames[j] + '\')">' + VNDIRECT_CORNER + '</a></b></p>';
						})					
						strContent += '<b>' + obj.newsHeader + '</b>';					
						strContent += '</div></td>';
						
						strContent += '</tr>';
					})				
				}		
	
				strContent += '</table>';			
				vndCorner.html(strContent);
				
			} catch(e) {
				alert(e);
			}
		}	
	}
	</script>
</c:if>
