<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<ul class="ui-tabs-nav tab_ttpt">
    <li <web:menuOut code='subMenuAnalysis_MarketStatistics_Overview' classValue='ui-tabs-selected'/>>
        <a href="<web:url value='/thong-ke-thi-truong-chung-khoan/tong-quan.shtml'/>">
            <label class="icon_active"></label><s:text name="home.topMenu.analysis.marketOverview"/>
       </a>
    </li>                                         
    <li <web:menuOut code='subMenuAnalysis_MarketStatistics_HistoricalPrice' classValue='ui-tabs-selected'/>>
        <a href="<web:url value='/thong-ke-thi-truong-chung-khoan/lich-su-gia.shtml'/>">
            <label class="icon_active"></label><s:text name="web.label.ListedMarket.Tab.MarketStatistics.Button.HistoricalPrice"/>
       </a>
    </li>
    <li <web:menuOut code='subMenuAnalysis_MarketStatistics_TradingStatistics' classValue='ui-tabs-selected'/>>
        <a href="<web:url value='/thong-ke-thi-truong-chung-khoan/ket-qua-giao-dich.shtml'/>">
            <label class="icon_active"></label><s:text name="web.label.ListedMarket.Tab.MarketStatistics.Button.TradingStatistics"/>
        </a>
   </li>                                                                      
    <li <web:menuOut code='subMenuAnalysis_MarketStatistics_ForeignTrading' classValue='ui-tabs-selected'/>>
        <a href="<web:url value='/thong-ke-thi-truong-chung-khoan/giao-dich-nha-dau-tu-nuoc-ngoai.shtml'/>">
            <label class="icon_active"></label><s:text name="web.label.ListedMarket.Tab.MarketStatistics.Button.ForeignTrading"/>
        </a>
    </li>
</ul> 
<div class="clear"></div>