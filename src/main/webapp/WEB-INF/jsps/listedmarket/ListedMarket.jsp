<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	String pageId = request.getParameter("id");
	if (pageId == null) {
		pageId = "1";
	}
	pageContext.setAttribute("pageId", pageId);
%>

<script type="text/javascript">
    var page = 1;
	$('document').ready(function(){
		getMarketInfoFromDatabase();
        page = '${pageId}';
        if (isNaN(page) || page == 'undefined' || page > 5 || page < 1) {
            page = 1;
        }
	   display();		   
	});

	function doSearchNews(){
		document.fSearchNews.submit();
		return;
	}

	function display() {
        $('.page1').hide();
        $('.page2').hide();
        $('.page3').hide();
        $('.page4').hide();
        $('.page5').hide();
        $('.page' + page).show();
        
        if(page == 1){
            loadMOHOSEChart('${model.hostcMarketInfo.chgIndex}');
            getMarketInfoFromDirectBoard("10");
        } else if(page == 2){
            loadMOVN30Chart('${model.vn30MarketInfo.chgIndex}');
            getMarketInfoFromDirectBoard("11");
        } else if(page == 3){
            loadMOHNXChart('${model.hastcMarketInfo.chgIndex}');
            getMarketInfoFromDirectBoard("02");
        } else if(page == 4){
            loadMOHNX30Chart('${model.hnx30MarketInfo.chgIndex}');
            getMarketInfoFromDirectBoard("12");
        } else if(page == 5){
            loadMOUPCOMChart('${model.upComMarketInfo.chgIndex}');
            getMarketInfoFromDirectBoard("03");
        }
        
        for (var i =1; i<=5;i++) {
            if (i == page) {
                $('#linkpage' +page).addClass('ui-tabs-selected');
                $('#linkpageU' +page).addClass('ui-tabs-selected');
            } else {
                $('#linkpage' +i).removeClass('ui-tabs-selected');
                $('#linkpageU' +i).removeClass('ui-tabs-selected');                             
            }
        }
    }
    
	function gotoPage(newPage) {        
        page = newPage;    
        display();
    }
	
	//LIVE
	var POPUP_URL_ADV = WEB_CONTEXT + "/adv/SupportService.html";
	function showAdvPopup() {
		openNewWindow(POPUP_URL_ADV,800,480);
	}
</script>
<div id="content_ttpt">
	<!-- nav -->
    <jsp:include page="/WEB-INF/jsps/listedmarket/snippet/market-overview-nav.jsp"></jsp:include>

	<div class="content_tongquanthitruong">
		<div class="width505 left">
			<a id="viewRTChartBtn"
				href="<web:url value="/cong-cu-phan-tich-chung-khoan/bieu-do-ky-thuat.shtml"/>">
				<input class="iButton" type="button"
				value='<s:text name="irSnapshot.content.viewChart">Xem biểu đồ chi tiết</s:text>' />
			</a>
			<ul class="ui-tabs-nav tab_button" id="tab_menusup_ttcp_dn">
				<li id="linkpage1"><a href="javascript:gotoPage(1)"><span>HOSE</span>
				</a></span></li>
				<li id="linkpage2"><a href="javascript:gotoPage(2)"><span>VN30</span>
				</a></span></li>
				<li id="linkpage3"><a href="javascript:gotoPage(3)"><span>HNX</span>
				</a></span></li>
				<li id="linkpage4"><a href="javascript:gotoPage(4)"><span>HNX30</span>
				</a></span></li>
				<li id="linkpage5"><a href="javascript:gotoPage(5)"><span>UPCOM</span>
				</a></span></li>
			</ul>
			<div class="clear"></div>
			<!-- HOSE chart -->
			<div class="page1 content_box" id="IndexMarket_10">
                <div style="margin-top: 10px;">
                    <span style="margin-left:10px;"> HOSE: <span id="currentIndex"></span>
                        <span id="changeIndex"></span>
                         ( <span id="changeIndexInPercent"></span> )
                    </span>
                    <div id="hoseHSMarketOverview" class="hsMarketOverview">
                        <img src="<web:url value='/images/ajax-loader.gif'/>" />
                    </div>
                </div>
            </div>
            <!-- VN30 chart -->
            <div class="page2 content_box" id="IndexMarket_11">
                <div style="margin-top: 10px;">
                    <span style="margin-left:10px;"> VN30: <span id="currentIndex"></span>
                        <span id="changeIndex"></span>
                         ( <span id="changeIndexInPercent"></span> )
                    </span>
                    <div id="vn30HSMarketOverview" class="hsMarketOverview">
                        <img src="<web:url value='/images/ajax-loader.gif'/>" />
                    </div>
                </div>
            </div>
            <!-- HNX chart -->
            <div class="page3 content_box" id="IndexMarket_02">
                <div style="margin-top: 10px">
                    <span style="margin-left:10px;"> HNX: <span id="currentIndex"></span>
                        <span id="changeIndex"></span>
                         ( <span id="changeIndexInPercent"></span> )
                    </span>

                    <div id="hnxHSMarketOverview" class="hsMarketOverview">
                        <img src="<web:url value='/images/ajax-loader.gif'/>" />
                    </div>
                </div>
            </div>
            <!-- HNX30 chart -->
            <div class="page4 content_box" id="IndexMarket_12">
                <div style="margin-top: 10px;">
                    <span style="margin-left:10px;"> HNX30: <span id="currentIndex"></span>
                        <span id="changeIndex"></span>
                         ( <span id="changeIndexInPercent"></span> )
                    </span>

                    <div id="hnx30HSMarketOverview" class="hsMarketOverview">
                        <img src="<web:url value='/images/ajax-loader.gif'/>" />
                    </div>
                </div>
            </div>
            <!-- UPCOM chart -->
            <div class="page5 content_box" id="IndexMarket_03">
                <div style="margin-top: 10px;">
                    <span style="margin-left:10px;"> UPCOM: <span id="currentIndex"></span>
                        <span id="changeIndex"></span>
                         ( <span id="changeIndexInPercent"></span> )
                    </span>

                    <div id="upcomHSMarketOverview" class="hsMarketOverview">
                        <img src="<web:url value='/images/ajax-loader.gif'/>" />
                    </div>
                </div>
            </div>
		</div>
		<div class="width440 right">
			<div class="general pn_main" id="Most_active">
				<div class="hd">
					<div class="hd-center">
						<div class="heading_pr">
							<h5>
								<s:text name="analysis.marketStatistics.mostActives">MOST ACTIVES</s:text>
							</h5>
							<a class="right heading_pr_date"
								href='<web:url value="/thong-ke-thi-truong-chung-khoan/ket-qua-giao-dich.shtml"/>'><s:text
									name="web.label.ListedMarketAction.form.mostactive.resultTransactions" />&nbsp;&raquo;</a>
						</div>
					</div>
				</div>
				<div class="bd">
					<div class=content_dv>
						<div class="clearfix  padding10" id="container-3">
							<ul id="ui-tabs-nav" class="ui-tabs-nav sup_tab_class1"
								style="margin-bottom: 10px;">
								<li class=""><a href="#accordion0"><s:text
											name="web.label.ListedMarketAction.form.mostactive.actives"></s:text>
								</a></li>
								<li class="">|</li>
								<li><a href="#accordion1">% <s:text
											name="web.label.ListedMarketAction.form.mostactive.gainers"></s:text>
								</a></li>
								<li class="">|</li>
								<li><a href="#accordion2">% <s:text
											name="web.label.ListedMarketAction.form.mostactive.losers"></s:text>
								</a></li>
								<li class="">|</li>
								<li><a href="#accordion3">$ <s:text
											name="web.label.ListedMarketAction.form.mostactive.gainers"></s:text>
								</a></li>
								<li class="">|</li>
								<li><a href="#accordion4">$ <s:text
											name="web.label.ListedMarketAction.form.mostactive.losers"></s:text>
								</a></li>
							</ul>

							<div class="clear"></div>

							<!-- start tabs-->
							<s:hidden id="fListedMarket_mostActiveFloorCode" value="0"></s:hidden>
							<s:iterator id="index" value="{0 , 1, 2, 3, 4}">
								<div class="ctTab_hotnews" id="accordion${index}" align="left">
									<div class="ct clearfix page1 page2">
										<table cellpadding="0" cellspacing="0" border="0" width="100%"
											class="tbl_most">
											<colgroup>
												<col width="15%">
												<col width="15%">
												<col width="15%">
												<col width="15%">
												<col width="20%">
												<col width="20%">
											</colgroup>
											<tr bgcolor="#eaedf0">
												<td class="col2" align="right"><s:text
														name="web.label.company" /></td>
												<td class="col2" align="right"><s:text
														name="web.label.ListedMarketAction.form.mostactive.highest" />
												</td>
												<td class="col2" align="right"><s:text
														name="web.label.ListedMarketAction.form.mostactive.lowest" />
												</td>
												<td class="col2" align="right"><s:text
														name="web.label.ListedMarketAction.form.mostactive.close" />
												</td>
												<td class="col2" align="right"><s:text
														name="web.label.change" /> &nbsp;&nbsp;&nbsp;</td>
												<td class="col2" align="right"><s:text
														name="web.label.ListedMarketAction.form.mostactive.volume" />
													&nbsp;&nbsp;&nbsp;</td>
											</tr>
										</table>
										<div class="ds_active">
											<div id="fListedMarket_hoseContent${index}"></div>
										</div>
									</div>
									<div class="ct clearfix page3 page4">
										<table cellpadding="0" cellspacing="0" border="0" width="100%"
											class="tbl_most">
											<colgroup>
												<col width="15%">
												<col width="15%">
												<col width="15%">
												<col width="15%">
												<col width="20%">
												<col width="20%">
											</colgroup>
											<tr bgcolor="#eaedf0">
												<td class="col2" align="right"><s:text
														name="web.label.company" /></td>
												<td class="col2" align="right"><s:text
														name="web.label.ListedMarketAction.form.mostactive.highest" />
												</td>
												<td class="col2" align="right"><s:text
														name="web.label.ListedMarketAction.form.mostactive.lowest" />
												</td>
												<td class="col2" align="right"><s:text
														name="web.label.ListedMarketAction.form.mostactive.close" />
												</td>
												<td class="col2" align="right"><s:text
														name="web.label.change" /> &nbsp;&nbsp;&nbsp;</td>
												<td class="col2" align="right"><s:text
														name="web.label.ListedMarketAction.form.mostactive.volume" />
													&nbsp;&nbsp;&nbsp;</td>
											</tr>
										</table>
										<div class="ds_active">
											<div id="fListedMarket_hnxContent${index}"></div>
										</div>
									</div>
									<div class="ct clearfix page5">
										<table cellpadding="0" cellspacing="0" border="0" width="100%"
											class="tbl_most">
											<colgroup>
												<col width="15%">
												<col width="15%">
												<col width="15%">
												<col width="15%">
												<col width="20%">
												<col width="20%">
											</colgroup>
											<tr bgcolor="#eaedf0">
												<td class="col2" align="right"><s:text
														name="web.label.company" /></td>
												<td class="col2" align="right"><s:text
														name="web.label.ListedMarketAction.form.mostactive.highest" />
												</td>
												<td class="col2" align="right"><s:text
														name="web.label.ListedMarketAction.form.mostactive.lowest" />
												</td>
												<td class="col2" align="right"><s:text
														name="web.label.ListedMarketAction.form.mostactive.close" />
												</td>
												<td class="col2" align="right"><s:text
														name="web.label.change" /> &nbsp;&nbsp;&nbsp;</td>
												<td class="col2" align="right"><s:text
														name="web.label.ListedMarketAction.form.mostactive.volume" />
													&nbsp;&nbsp;&nbsp;</td>
											</tr>
										</table>
										<div class="ds_active">
											<div id="fListedMarket_upComContent${index}"></div>
										</div>
									</div>
								</div>
							</s:iterator>
							<!--end tabs-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="content_tongquanthitruong">
	   <!-- Tin Nhận định thị trường mới nhất -->
		<div class="width505 left" id="divLastestMarketNews.id">
			<div class="content_new" style="display: none;">
				<div style="display: inline-block; margin-bottom: 20px;">
					<img class="photo"
						src="<web:url value='/images/icons/iconNDTT.png'/>" />
					<h2 class="left title">
						<a href="" id="tqtt_content_new_header"></a>
					</h2>
				</div>
				<div class="clear"></div>
				<p id="tqtt_content_new_abstract"></p>
				<a class="orange right" href="" id="tqtt_content_new_url_detail"><s:text
						name='analysis.marketOverView.news.detail' /></a>
			</div>
		</div>
        <!-- Chiến lược đầu tư tuần -->
		<div class="width440 right">
			<div class="general pn_main" id="ctn-general">
				<div class="hd">
					<div class="hd-center">
						<div class="heading_pr">
							<h5>
								<s:text
									name="web.label.ListedMarketAction.form.weeklyInvestmentStrategy.title" />
							</h5>
						</div>
					</div>
				</div>
				<div class="bd">
					<div class="content_dv" id="weeklyInvestmentStrategy">
						  <span class="ajaxLoadingIcon"></span>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="content_tongquanthitruong" style="min-height:375px;">
		<div class="width505 left">
			<div class="general pn_main" id="hotnews">
				<div class="hd">
					<div class="hd-center">
						<div class="heading_pr">
							<h5>
								<s:text name="web.label.ListedMarketAction.form.news.title" />
							</h5>
							<a class="right heading_pr_date"
								href="<web:url value="/tin-trong-nuoc.shtml" />"><s:text
									name="web.label.ListedMarketAction.form.news.otherNews" />&nbsp;&raquo;</a>
						</div>
					</div>
				</div>
				<div class="bd">
					<div class="content_dv">
						<div class="clearfix  padding10" id="container-2">
							<ul id="ui-tabs-nav" class="ui-tabs-nav sup_tab_class1">
								<li class=""><a href="#fragment-5"
									class="{type: 'MacVN', showin : 'ListedMarket', div : '.ds_news.tab1', navigator : 'tab1', url : 'URL_NEWS'}">
										<s:text name="web.label.ListedMarketAction.form.news.title1" />
								</a></li>
								<li class="">|</li>
								<li><a href="#fragment-6"
									class="{type: 'MacWorld', showin : 'ListedMarket', div : '.ds_news.tab2', navigator : 'tab2', url : 'URL_MAC_WORLD'}">
										<s:text name="web.label.ListedMarketAction.form.news.title2" />
								</a></li>
								<li class="">|</li>
								<li><a href="#fragment-7"
									class="{type: 'Disclousure', showin : 'VNMarket', div : '.ds_news.tab3', navigator : 'tab3', url : 'URL_IPO_NEWS'}">
										<s:text name="web.label.ListedMarketAction.form.news.title3" />
								</a></li>
								<%--
								<li class="">
									|
								</li>
								<li>
									<a href="#fragment-8"
										class="{type: 'IPO', showin : 'VNMarket', div : '.ds_news.tab4', navigator : 'tab4', url : 'URL_PUBLIC_INFO'}">
										<s:text name="web.label.ListedMarketAction.form.news.title4" />
									</a>
								</li>
							    --%>
							</ul>

							<div class="clear"></div>

							<!--tab1-->
							<div class="ctTab_hotnews" id="fragment-5" align="left">
								<div class="ct clearfix">
									<div class="ds_news tab1"></div>
									<div id="tab1" class="right"></div>
								</div>
							</div>
							<!--tab2-->
							<div class="ctTab_hotnews" id="fragment-6" align="left">
								<div class="ct clearfix">
									<div class="ds_news tab2"></div>
									<div id="tab2" class="right"></div>
								</div>
							</div>
							<!--tab3-->
							<div class="ctTab_hotnews" id="fragment-7" align="left">
								<div class="ct clearfix">
									<div class="ds_news tab3"></div>
									<div id="tab3" class="right"></div>
								</div>
							</div>
							<!--tab4-->
							<!-- <div class="ctTab_hotnews" id="fragment-8" align="left">
								<div class="ct clearfix">
									<div class="ds_news tab4"></div>
									<div id="tab4" class="right"></div>
								</div>
							</div> -->
							<!--end tabs-->
						</div>
					</div>
				</div>
			</div>
		</div>

        <!-- most Popular Symbol -->
		<div class="width440 right">
			<div class="general pn_main" id="ctn-general">
				<div class="hd">
					<div class="hd-center">
						<div class="heading_pr">
							<h5>
								<s:text
									name="web.label.ListedMarketAction.form.mostpopularsymbol.title" />
							</h5>
						</div>
					</div>
				</div>
				<div class="bd">
					<div class="content_dv">
						<div class="clearfix  padding10">
							<div class="ctn_most clearfix">
								<table cellpadding="0" cellspacing="0" border="0" width="100%"
									class="tbl_most">
									<colgroup>
										<col width="25%">
										<col width="25%">
										<col width="25%">
										<col width="25%">
									</colgroup>
									<tr bgcolor="#eaedf0">
										<td class="noborder"><s:text name="web.label.company" />
										</td>
										<td class="noborder"><s:text
												name="web.label.ListedMarketAction.form.mostpopularsymbol.price" />
										</td>
										<td class="noborder" align="right"><s:text
												name="web.label.change" /></td>
										<td class="noborder" align="right"><s:text
												name="web.label.ListedMarketAction.form.mostpopularsymbol.KLGD" />
										</td>
									</tr>
								</table>
								<div id="fListedMarket_mostPopularSymbolContent">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
