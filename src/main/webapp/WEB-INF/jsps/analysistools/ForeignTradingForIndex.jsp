<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<form name="fForeignTradingForSymbol" id="fForeignTradingForSymbol" method="post">
  	<input id="fForeignTradingForSymbol_pagingInfo_indexPage" type="hidden" name="pagingInfo.indexPage" value="1"/> 	
         
   	<div class="pn_main" id="page_TTNY">
   	       
		<!--Start All Tab menu  -->
	   	<div class="tabs_TTNY" id="container-1" >
	     	 <div style="clear:both"></div>
	          	<ul id="ui-tabs-nav" class="ui-tabs-nav clearfix">
	          	 	<li><a href="<web:url value="/analysis/FlashChart.shtml"/>"><b><s:text name="leftmenu.label.analysisTool.Technical.Analysis">Technical Analysis</s:text></b></a></li>
	             	<li class="ui-tabs-selected"><a><b><s:text name="leftmenu.label.analysisTool.IndexStatistics">Index Statistics</s:text></b></a></li>
	         	</ul>
	        	<!--tabs 1-->
	        	<div class="ctTab_TTNY" id="fragment-1" align="left">
	        		<div class="top_inner clearfix">
	            		<div class="right fr"></div>
	          	</div>
	          	<div class="center_inner_2 clearfix">
	              	<div class="firstline_sector clearfix">
	              		<table cellpadding="0" cellspacing="0" border="0">
	                  		<tr>
		                  		<td><span class="btn_left_inbox"><span class="btn_right_inbox"><span class="btn_center_inbox"><input type="button" value="<s:text name="web.label.ListedMarket.Tab.MarketStatistics.Button.HistoricalPrice">Historical Price</s:text>" onclick="window.location='<web:url value="/analysis/historical-price.shtml"/>'"></span>
		                           </span></span>
		                           <span class="btn_left_SQ"><span class="btn_right_SQ"><span class="btn_center_SQ"><input type="button" value="<s:text name="web.label.ListedMarket.Tab.MarketStatistics.Button.ForeignTrading">Foreign Trading</s:text>"></span>
		                           </span></span>
		                           <span class="btn_left_inbox"><span class="btn_right_inbox"><span class="btn_center_inbox"><input type="button" value="<s:text name="web.label.ListedMarket.Tab.MarketStatistics.Button.TradingStatistics">Trading Statistics</s:text>" onclick="window.location='<web:url value="/analysis/TradingStatisticsForSymbol.shtml"/>'"></span>
		                           </span></span>
		                        </td>
	                      	</tr>
	                  	</table>
	              	</div>
	      			<div class="center_inner">
		     			<div class="center_inner clearfix">
	     
	    				<!--Start Foreign Trading For Symbol-->
		 					<div class="table_Market clearfix">
								<table cellpadding="0" cellspacing="0" border="0" width="100%">
	   								<tr><td class="td_title_Calendar">
	          							<table cellpadding="0" cellspacing="0" border="0" width="100%" height="40">
	           								<tr><td width="4"><img src="<web:url value='/images/img/imgleft_Title_lich_2.gif'/>" /></td>
	               								<td width="30">&nbsp;</td>
	               								<td width="340"><b class="bluetext"><s:text name="web.label.symbol"/></b>
	       											<input type="text" name="searchMarketStatisticsView.symbol" id="fForeignTradingForSymbol_symbolID" disabled="disabled" value="<c:out value='${model.searchMarketStatisticsView.symbol}'/>" style="height: 18px; width:130px; font-weight: bold;">   			
	               								</td>
	               								<td width="280"><s:text name="web.label.MarketStatisticsAction.form.Fromdate"/>
	               									<input name="strFromDate" type="text" class="textshortwidth" id="fForeignTradingForSymbol_FromDate" style="height: 18px; width:130px;"/>
	               								</td>
	                							<td width="380"><s:text name="web.label.MarketStatisticsAction.form.Todate"/>
	                								<input name="strToDate" type="text" class="textshortwidth" id="fForeignTradingForSymbol_ToDate" style="height: 18px; width:130px;"/> 			
								                </td>
								                <td width="100">
								                	<span class="btn_left_inbox"><span class="btn_right_inbox"><span class="btn_center_inbox">
								                          <input type="button" id="fForeignTradingForSymbol_View" value="<s:text name="button.search"/>">
								                    </span></span></span></td>
								                <td width="3"><img src="<web:url value='/images/img/imgright_Title_lich_2.gif'/>" /></td>
	       									</tr>
	       								</table>
	 								</td></tr>
		 							<tr><td>
									 	<table class="nomal" cellpadding="0px" cellspacing="0px" width="100%" id="Listed_ForeignTradingForSymbol_tableResult">		
											<colgroup>
													<col width="10%"/>
													<col width="15%"/>
													<col width="15%"/>
													<col width="13%"/>
													<col width="17%"/>
													<col width="13%"/>					
													<col width="17%"/>
											</colgroup>
											<thead>
												<tr bgcolor="#efefef">
													<td class="bordertd" style="font-weight: bold; text-align: center;" rowspan="2"><s:text name="web.label.date"/></td>
													<td class="bordertd" style="font-weight: bold; text-align: center;" rowspan="2"><s:text name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.TotalRoom"/></td>
													<td class="bordertd" style="font-weight: bold; text-align: center;" rowspan="2"><s:text name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.CurrentRoom"/></td>
													<td class="bordertd" style="font-weight: bold; text-align: center;" colspan="2"><s:text name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.BuyingVolume"/></td>
													<td class="col_end" style="font-weight: bold; text-align: center;" colspan="2"><s:text name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.SellingVolume"/></td>
												</tr>
												<tr bgcolor="#efefef">
													<td class="bordertd" style="font-weight: bold; text-align: center;"><s:text name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.Bids"/></td>
													<td class="bordertd" style="font-weight: bold; text-align: center;"><s:text name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.Value"/></td>
													<td class="bordertd" style="font-weight: bold; text-align: center;"><s:text name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.Offers"/></td>
													<td class="col_end" style="font-weight: bold; text-align: center;"><s:text name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.Value"/></td>
												</tr>				
											</thead>
											<tbody >
											<tr>
											<td>
											<div id="imageForeignTradingForSymbol" style="height: 300px;"><img src="<web:url value='/images/ajax-loader.gif'/>"/></div>
											</td>
											</tr>
											</tbody>
										</table>
									</td></tr>                        	
								</table>           				
								<div class="fpCalendar">
									<div class="fpCalendar-left"></div>
									<div class="fpCalendar-right"></div>
									<div align="right" class="fpCalendar-center" id="fSearchSymbol_paging"></div>
								</div>		   						
		   					</div>
		 				<!-- End Foreign Trading For Symbol -->
		  				</div>
		  			</div>
		  			<div class="bottom_inner clearfix"><div class="left fl"></div><div class="right fr"></div></div>
		 		</div>
		 	</div>
		</div>
	</div>
	<!--End All Tab menu  -->
</form>