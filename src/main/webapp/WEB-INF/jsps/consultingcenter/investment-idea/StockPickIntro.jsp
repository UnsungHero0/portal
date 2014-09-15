<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script>
$(function() {
    StockPick.initForNotDA();
});
</script>

<div class="recommendDaily">
	<jsp:include
		page="/WEB-INF/jsps/consultingcenter/investment-idea/investment-idea-nav.jsp" />

	<div id="newscontent-tabs">
		<div class="rcmIndex">
			<div class="rcmIndexL">
				<div class="rcmnote">
					Sản phẩm Cổ phiếu khuyến nghị do <b>Môi giới trực tuyến VNDIRECT</b> thực hiện và đang trong thời gian thử nghiệm.
				</div>
				<div class="rcmRegis">
					<p class="txtnote">Vui lòng đăng ký sử dụng sản phẩm thử nghiệm
						tại đây</p>
					<p class="right">
						<a id="registerStockPick_btn" href="https://docs.google.com/forms/d/1__e9MEFJE_-Y44y8fB9oiu6FX0N7NQ04jXAfhvIdRBs/viewform" target="_blank" class="btnRegis">Đăng ký</a> 
						<%-- <a href="<web:url value='/home.shtml' />"
							class="btnReturn">Quay lại</a> --%>
					</p>
				</div>
			</div>
			<div class="rcmIndexR">
				<web:productSubject var="objProdSub" productCode="STOCK_PICK_LAST" />
				<c:out value="${objProdSub['product'].productOverview}"
					escapeXml="false" />
			</div>
		</div><div class="clear"></div>
		<!--rcmIndex-->
	</div>
	
	<div class="clear"></div>
	
	<!-- <h3 class="titleTop">DEMO KHUYẾN NGHỊ MÃ DCS NGÀY 17-03-2014</h3> -->
	<div class="clear"></div>
	<iframe src="<web:url value='/y-tuong-dau-tu/cpkn-demo.shtml' />" 
            width="975" height="580"
            frameborder="0" ></iframe> 
            <div class="clear"></div>
    <hr>
	<div class="clear"></div>
	
	<!-- Khuyến nghị đang mở/đóng -->
	<jsp:include page="/WEB-INF/jsps/consultingcenter/investment-idea/ShortOpenAndCloseStockPick-snippet.jsp"></jsp:include>
</div>





