<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<form name="fHistoricalPrice" id="fHistoricalPrice" method="post">
	<input type="hidden" name="model.downloadType" id="downloadType">
  	<input id="fHistoricalPrice_pagingInfo_indexPage" type="hidden" name="pagingInfo.indexPage" value="1"/>
         
   	<div class="pn_main" id="page_TTNY">
   	<!--Start Breadcrumbs  -->
	<web:quotesCompanyInfo/>
   	<!--End Breadcrumbs  -->
       
<!--Start All Tab menu  -->
   	<div class="tabs_TTNY" id="container-1" >
      <div style="clear:both"></div>
          <ul id="ui-tabs-nav" class="ui-tabs-nav clearfix">
          	 <li><a href="<web:url value="/listed/snapshot.shtml"/>"><b><s:text name="web.label.ListedMarket.Tab.Company">Company</s:text></b></a></li>
             <li><a href="<web:url value="/listed/major-holders.shtml"/>"><b><s:text name="web.label.ListedMarket.Tab.Ownership">Ownership</s:text></b></a></li>
             <li><a href="<web:url value="/listed/company-news.shtml"/>"><b><s:text name="web.label.ListedMarket.Tab.News&Info">News & Info</s:text></b></a></li>
             <li><a href="<web:url value="/listed/flash-chart.shtml"/>"><b><s:text name="web.label.ListedMarket.Tab.Charts">Charts</s:text></b></a></li>
             <li><a href="<web:url value="/listed/balance-sheet.shtml"/>"><b><s:text name="web.label.ListedMarket.Tab.Financial">Financial</s:text></b></a></li>
             <li class="ui-tabs-selected"><a><b><s:text name="web.label.ListedMarket.Tab.MarketStatistics">Market Statistics</s:text></b></a></li>
         </ul>
        <!--tabs 1-->
        <div class="ctTab_TTNY" id="fragment-1" align="left">
        	<div class="top_inner clearfix">
            	<div class="right fr"></div>
          </div>
          <div class="center_inner_2 clearfix">
          	<!---->
              <div class="firstline_sector clearfix">
              	<table cellpadding="0" cellspacing="0" border="0">
                  	<tr>
                  		<td><span class="btn_left_SQ"><span class="btn_right_SQ"><span class="btn_center_SQ"><input type="button" value="<s:text name="web.label.ListedMarket.Tab.MarketStatistics.Button.HistoricalPrice">Historical Price</s:text>" ></span>
                           </span></span>
                           <span class="btn_left_inbox"><span class="btn_right_inbox"><span class="btn_center_inbox"><input type="button" value="<s:text name="web.label.ListedMarket.Tab.MarketStatistics.Button.ForeignTrading">Foreign Trading</s:text>" onclick="window.location='<web:url value="/listed/foreign-trading.shtml"/>'"></span>
                           </span></span>
                           <span class="btn_left_inbox"><span class="btn_right_inbox"><span class="btn_center_inbox"><input type="button" value="<s:text name="web.label.ListedMarket.Tab.MarketStatistics.Button.TradingStatistics">Trading Statistics</s:text>" onclick="window.location='<web:url value="/listed/TradingStatisticsForSymbol.shtml"/>'"></span>
                           </span></span></td>
                      </tr>
                  </table>
              </div>
              <!---->
      <div class="center_inner">
	     <div class="center_inner clearfix">
	       <div class="tableNganh clearfix">
	           <div class="tableNganh_Title" style="padding:5px 15px">
	           	<table cellpadding="0" cellspacing="0" border="0" width="100%">
               		<tr>
                       <td align="right">
                  			<a href="javascript:doDownload();" style="text-decoration: none"><img src="<web:url value='/images/img/ico_11.gif'/>" hspace="5" />
                  			<b style="color:#f60"><s:text name="button.downloadFile"/></b></a>
                        </td>
                   	</tr>
                </table>
	           </div>                                             
	       </div>
     
    <!--Start HistoricalPriceForSymbol-->
	<div class="table_Market clearfix">
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
   	<tr><td class="td_title_Calendar">
           <table cellpadding="0" cellspacing="0" border="0" width="100%" height="40">
           	<tr><td width="4"><img src="<web:url value='/images/img/imgleft_Title_lich_2.gif'/>" /></td>
               	<td width="30">&nbsp;</td>
               	<td width="340"><b class="bluetext"><s:text name="web.label.symbol"/></b>
       				<input type="text" name="searchMarketStatisticsView.symbol" id="symbolID" disabled="disabled" value="<c:out value='${model.searchMarketStatisticsView.symbol}'/>" style="height: 18px; width:130px; font-weight: bold;"> 
					<input type="hidden" name="searchMarketStatisticsView.symbol" id="symbolID" value="<c:out value='${model.searchMarketStatisticsView.symbol}'/>">  			
               	</td>
               <td width="280"><s:text name="web.label.MarketStatisticsAction.form.Fromdate"/>
               		<input name="strFromDate" type="text" class="textshortwidth" id="fHistoricalPrice_FromDate" style="height: 18px; width:130px;"/>
               </td>
                <td width="380"><s:text name="web.label.MarketStatisticsAction.form.Todate"/>
                	<input name="strToDate" type="text" class="textshortwidth" id="fHistoricalPrice_ToDate" style="height: 18px; width:130px;"/>			
                </td>
                <td width="100">
                	<span class="btn_left_inbox"><span class="btn_right_inbox"><span class="btn_center_inbox">
                          <input type="button" id="fHistoricalPrice_View" value="<s:text name="button.search"/>">
                    </span></span></span></td>
                <td width="3"><img src="<web:url value='/images/img/imgright_Title_lich_2.gif'/>" /></td>
       </tr></table>
 	</td></tr>
	 <tr>
	 <!--Content here  -->
	 <td>
	 <table class="nomal" cellpadding="0px" cellspacing="0px" width="100%" id="Listed_HistoricalPrice_tableResult">		
			<colgroup>
					<col width="9%"/>
					<col width="13%"/>
					<col width="9%"/>
					<col width="9%"/>
					<col width="9%"/>
					<col width="9%"/>
					<col width="9%"/>
					<col width="9%"/>
					<col width="12%"/>
					<col width="12%"/>				
			</colgroup>
			<thead>
				<tr bgcolor="#efefef">
					<td class="bordertd"><div style="font-weight: bold; text-align: center" ><s:text name="web.label.date"/></div></td>
					<td class="bordertd"><div style="font-weight: bold; text-align: center"><s:text name="web.label.MarketStatisticsAction.form.HistoricalPrice.CellNames.Change"/></div></td>
					<td class="bordertd"><div style="font-weight: bold; text-align: center"><s:text name="web.label.MarketStatisticsAction.form.CellNames.Open"/></div></td>
					<td class="bordertd"><div style="font-weight: bold; text-align: center"><s:text name="web.label.MarketStatisticsAction.form.CellNames.High"/></div></td>
					<td class="bordertd" ><div style="font-weight: bold; text-align: center"><s:text name="web.label.MarketStatisticsAction.form.CellNames.Low"/></div></td>
					<td class="bordertd"><div style="font-weight: bold; text-align: center"><s:text name="web.label.MarketStatisticsAction.form.CellNames.Close"/></div></td>
					<td class="bordertd"><div style="font-weight: bold; text-align: center"><s:text name="web.label.MarketStatisticsAction.form.HistoricalPrice.CellNames.AveragePrice"/></div></td>
					<td class="bordertd" ><div style="font-weight: bold; text-align: center"><s:text name="web.label.MarketStatisticsAction.form.HistoricalPrice.CellNames.AdjustPrice"/></div></td>
					<td class="bordertd" ><div style="font-weight: bold; text-align: center"><s:text name="web.label.MarketStatisticsAction.form.HistoricalPrice.CellNames.Volume"/></div></td>
					<td class="col_end" ><div style="font-weight: bold; text-align: center"><s:text name="web.label.MarketStatisticsAction.form.HistoricalPrice.CellNames.PtVolume"/></div></td>
				</tr>
			</thead>
			<tbody>
			<tr>
			<td>
			<div id="imageHistoricalPriceForSymbol" style="height: 300px;"><img src="<web:url value='/images/ajax-loader.gif'/>"/></div>
			</td>
			</tr>
			</tbody>
		</table>
	</td>	
	 <!--End Content here  -->
	</tr>                        	
	</table>           				
	<div class="fpCalendar">
		<div class="fpCalendar-left"></div>
		<div class="fpCalendar-right"></div>
		<div align="right" class="fpCalendar-center" id="fSearchSymbol_paging"></div>
	</div>	   
	   </div>
	 <!-- End HistoricalPriceForSymbol -->
		<P><BR><span style="color: red;"><s:text name="web.label.MarketStatisticsAction.form.HistoricalPrice.Description"></s:text></span></P>
	  </div></div><div class="bottom_inner clearfix"><div class="left fl"></div><div class="right fr"></div></div>
	 </div></div>
	 </div>
	 </div>
	  <!--End All Tab menu  -->
</form>