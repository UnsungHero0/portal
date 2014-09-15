<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="vn.com.vndirect.commons.utility.VNDirectWebUtilities"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils"%> 

<script type="text/javascript">

    var REQ = '<%= request.getParameter("req")%>';

	var urlLazyLoading_ = "";
	function lazyLoadingNews(scrollId) {
		var url = $(scrollId).find('div.jscroll-next-parent:last>a').attr('href');
		if (urlLazyLoading_ != url) {
			urlLazyLoading_ = url;
			$(scrollId + ' .ajax_loading').css("display", "block");
			$.get(url, function(data){
				$(scrollId).find("ul").append(data);
				$(scrollId + ' .ajax_loading').css("display", "none");
				$(scrollId).getNiceScroll().resize();
			});
		} 
	}
	$(document).ready(function() {
		$.subscribe("pullDownUpMCK",function(data){
			$("#scroll_newhome").getNiceScroll().resize();
			$("#scroll_tinvnd").getNiceScroll().resize();
		});
		$(".tab_list_new").tabs();
		$('.tab_list_new>ul>li>a').click(function(){
			var tabId = $(this).attr("href");
			$("div.content_list_new>div.scrollable").hide();
			$(tabId).show();
			if ($(tabId).find("ul").html() == "") {
				$(tabId).find("ul").load($(tabId).attr("link"), function(){
					$(tabId).find('.ajax_loading_top').remove();
					$(tabId).getNiceScroll().resize();
					$(tabId).find('li:first').addClass("noline");
					$(tabId).niceScroll({horizrailenabled:false, scrollId: "#scroll_tinvnd", callbackScrollBottom: lazyLoadingNews});
				});
			}
		});
		var slectedTabId = '#scroll_newhome';
		$(slectedTabId).find('ul').load('<web:url value="/ajax/news/NewsGetNewsHomePage.shtml"/>', function(){
			$("#scroll_newhome").niceScroll({horizrailenabled:false, scrollId: "#scroll_newhome", callbackScrollBottom: lazyLoadingNews});
			$(slectedTabId).find('.ajax_loading_top').remove();
			$("#scroll_newhome").getNiceScroll().resize();
			$("#scroll_newhome").find('li:first').addClass("noline");
		}); 
		// slide image in homepage
		setInterval('imageRotator()', 4000);
		
		// to open dialog
	    if(REQ == 'cptd'){
	        openStockHighlightsDialog();
	    }
	});
</script>

<div id="main" class="clearfix">
	<!--++slide-->
	<div class="box_silde_home box_865">
		<div class="content_slide">
			<div id="box1" class="box_content">
				<div class="boximg">
					<web:productSubject var="objProdSub" productCode="VNDIRECT_BANNER" />
					<c:set value="${objProdSub['product'].productOverview}" var="htmlObj"/>
					<%pageContext.setAttribute("variableHtml", StringEscapeUtils.unescapeHtml((String)pageContext.getAttribute("htmlObj")));%> 
					<c:out value="${variableHtml}" escapeXml="false"></c:out>
				</div>
				<%-- <div class="menu_box_p">
					<web:productSubject var="objProdSub" productCode="VNDIRECTLINKS" />
					<c:set value="${objProdSub['product'].productOverview}" var="htmlObj"/>  
					<%pageContext.setAttribute("variableHtml", StringEscapeUtils.unescapeHtml((String)pageContext.getAttribute("htmlObj")));%> 
					<c:out value="${variableHtml}" escapeXml="false"></c:out>
				</div> --%>
			</div>		
			<div id="box2" class="box_content" style="display: none;">
				<div class="boximg">
					<web:document var="imgDoc" productCode="BANNER_DAUTUMOI"
						download="true" />
					<img src="${imgDoc['document'].documentUri}" />
				</div>
				<%-- <div class="menu_box_p">
					<web:productSubject var="objProdSub" productCode="DAUTUMOILINKS" />
					<c:set value="${objProdSub['product'].productOverview}" var="htmlObj"/>  
					<%pageContext.setAttribute("variableHtml", StringEscapeUtils.unescapeHtml((String)pageContext.getAttribute("htmlObj")));%> 
					<c:out value="${variableHtml}" escapeXml="false"></c:out>
				</div> --%>
			</div>
			<div id="box3" class="box_content" style="display: none;">
				<div class="boximg">
					<web:document var="imgDoc" productCode="BANNER_KHONGCHUYEN"
						download="true" />
					<img src="${imgDoc['document'].documentUri}" />
				</div>
				<%-- <div class="menu_box_p">
					<web:productSubject var="objProdSub" productCode="KHONGCHUYENLINKS" />
					<c:set value="${objProdSub['product'].productOverview}" var="htmlObj"/>  
					<%pageContext.setAttribute("variableHtml", StringEscapeUtils.unescapeHtml((String)pageContext.getAttribute("htmlObj")));%> 
					<c:out value="${variableHtml}" escapeXml="false"></c:out>
				</div> --%>
			</div>
			<div id="box4" class="box_content" style="display: none;">
				<div class="boximg">
					<web:document var="imgDoc" productCode="BANNER_CHUYENNGHIEP"
						download="true" />
					<img src="${imgDoc['document'].documentUri}" />
				</div>
				<%-- <div class="menu_box_p">
					<web:productSubject var="objProdSub"
						productCode="CHUYENNGHIEPLINKS" />
					<c:set value="${objProdSub['product'].productOverview}" var="htmlObj"/>  
					<%pageContext.setAttribute("variableHtml", StringEscapeUtils.unescapeHtml((String)pageContext.getAttribute("htmlObj")));%> 
					<c:out value="${variableHtml}" escapeXml="false"></c:out>
				</div> --%>
			</div>	
			<div class="tab_list_new">
				<ul class="tab_new_home">
                	<li><a href="#scroll_newhome" ><s:text name="home.news.tabs.title"/></a></li>
                    <li><a href="#scroll_tinvnd" ><s:text name="home.vnds.news.tabs.title"/></a></li>
                </ul>
            	<div class="content_list_new">
                	<div id="scroll_newhome" class="scrollable" link="<web:url value="/ajax/news/NewsGetNewsHomePage.shtml"/>">  
                		<div class="ajax_loading_top"><img src="<web:url value='/images/icons/ajax_loading.gif'/>"></div>
                        <div  class="content_small_slide jscroll_newshome">
                           <ul class="list_new_home"></ul>
                        </div> 
                        <div class="ajax_loading"><img src="<web:url value='/images/icons/ajax_loading.gif'/>"></div>   
                	 </div>
                	 <div id="scroll_tinvnd" class="scrollable" link="<web:url value="/ajax/news/NewsGetVNDSHomepage.shtml"/>">  
                	 	<div class="ajax_loading_top"><img src="<web:url value='/images/icons/ajax_loading.gif'/>"></div>                  	
                        <div class="content_small_slide jscroll_newsVNDS">
                           <ul class="list_new_home"></ul>
                        </div>    
                        <div class="ajax_loading" style="display: none;"><img src="<web:url value='/images/icons/ajax_loading.gif'/>"></div>   
                	 </div>
                </div>
			</div>	
		</div>
		<ul class="menu_slide_new_07">			
			<li class="vnd margin-left border-left" id="is_1" onclick="window.location.href='<web:url value="/vndirect/gioi-thieu-vndirect.shtml"/>'" onmouseover="if(!$(this).hasClass('selected')){ $(this).find('#bannerTitle').css('text-decoration','underline')}"
			             onmouseout="$(this).find('#bannerTitle').css('text-decoration','none')">
				<span class="icon_slide_1"></span>
				<div class="box">
					<h1 id="bannerTitle"><s:text name="home.body.menuSlide.vndirect"/></h1>
					<h2><s:text name="home.body.menuSlide.vndirect.title"/></h2>
				</div>
			</li>
            <li class="def" id="is_2" onclick="window.location.href='<web:url value="/mo-tai-khoan.shtml"/>'" onmouseover="if(!$(this).hasClass('selected')){ $(this).find('#bannerTitle').css('text-decoration','underline')}"
			             onmouseout="$(this).find('#bannerTitle').css('text-decoration','none')">
				<span class="icon_slide_2"></span>
				<div class="box">
					<h1 id="bannerTitle"><s:text name="home.body.menuSlide.newInvestor"/></h1>
					<h2><s:text name="home.body.menuSlide.newInvestor.title"/></h2>
				</div>
			</li>
			<li class="def" id="is_3" onclick="window.location.href='<web:url value="/tro-giup/hoi-dap-chuyen-khoan-chung-khoan.shtml"/>'" onmouseover="if(!$(this).hasClass('selected')){ $(this).find('#bannerTitle').css('text-decoration','underline')}"
			             onmouseout="$(this).find('#bannerTitle').css('text-decoration','none')">
				<span class="icon_slide_3"></span>
				<div class="box left">
					<h1 id="bannerTitle"><s:text name="home.body.menuSlide.notProInvestor"/></h1>
					<h2><s:text name="home.body.menuSlide.notProInvestor.title"/></h2>
				</div>
			</li>
			<li class="def" id="is_4" onclick="window.open('http://nangdong.vndirect.com.vn/', '_blank');" onmouseover="if(!$(this).hasClass('selected')){ $(this).find('#bannerTitle').css('text-decoration','underline')}"
			             onmouseout="$(this).find('#bannerTitle').css('text-decoration','none')">
				<span class="icon_slide_4"></span>
				<div class="box">
					<h1 id="bannerTitle"><s:text name="home.body.menuSlide.proInvestor"/></h1>
					<h2><s:text name="home.body.menuSlide.proInvestor.title"/></h2>
				</div>
			</li>
		</ul>
		
		<%-- <a id="toggle-tong-quan-thi-truong-hidden">Tổng quan thị trường</a>
		<div id="tong-quan-thi-truong-hidden" class="invisible">
		  <jsp:include page="/WEB-INF/jsps/home/market-snapshot/ListedMarketOnHomepage.jsp"></jsp:include>
		</div> --%>
	</div>
	<ul class="box_right_home_new">
			<li><a onclick="window.open('http://banggia.vndirect.com.vn', '_blank');" target="_blank">
					<img src="<web:url value='/images/icons/ico_banggia.png' />">
					<h2><s:text name="home.txt.directBoard" /></h2>
				</a>
			</li>
			<li>
			<a href="<web:url value='/cong-cu-phan-tich-chung-khoan/bieu-do-ky-thuat.shtml' />">
				<img src="<web:url value='/images/icons/iconbieudo.png' />">
				<h2><s:text name="analysis.stockInfo.charts" /></h2>
			</a>
			</li>
			<li><a href="<web:url value='/nhan-dinh-thi-truong.shtml' />">
				<img src="<web:url value='/images/icons/iconNDTT.png' />">
				<h2><s:text name="analysis.news.marketCommentary" /></h2>
				</a>
			</li>
			<li><a href="<web:url value='/y-tuong-dau-tu/co-phieu-khuyen-nghi.shtml'/>">
                <img src="<web:url value='/images/icons/iconcpkn.png' />">
                <h2><s:text name="label.stockpick">Cổ phiếu khuyến nghị</s:text></h2>
                </a>
            </li>
			<%-- <li><a href="<web:url value='/co-phieu-tam-diem.shtml'/>">
				<img src="<web:url value='/images/icons/iconcptd.png' />">
				<h2><s:text name="home.txt.news" /></h2>
				</a>
			</li> --%>
	</ul>
	<!--//end slide-->
</div>
