<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>

<!-- set portal url -->
<web:urlMapping var="tradingUrl" value="TradingOnlinePage"/>


<div class="box_load">
    <ul class="list_menu_sup" style="width:220px;">
        <li class="title">
            <a href="${tradingUrl}/giao-dich-co-phieu/dat-lenh-mua-ban.shtml?ticket=true">
               <s:text name="Message.LeftMenu.OnlineTrading"/>
            </a>
        </li>
        <li>
            <a href="${tradingUrl}/giao-dich-co-phieu/dat-lenh-mua-ban.shtml?ticket=true" <web:menuOut code='subMenuTrading_PlaceOrderItem' classValue='active'/>>
               <s:text name="Message.LeftMenu.PlaceOrder"/>
            </a>
        </li>
        <li>
            <a href="${tradingUrl}/giao-dich-co-phieu/so-lenh-trong-ngay.shtml?ticket=true" <web:menuOut code='subMenuTrading_OrderbookItem' classValue='active'/>>
               <s:text name="Message.LeftMenu.Orderbook"/>
            </a>
        </li>
        <li>
            <a href="${tradingUrl}/giao-dich-co-phieu/lich-su-mua-ban.shtml?ticket=true" <web:menuOut code='subMenuTrading_ViewOrdersItem' classValue='active'/>>
               <s:text name="Message.LeftMenu.ViewOrders"/>
            </a>
        </li>
        <li>
            <a href="${tradingUrl}/giao-dich-co-phieu/tra-cuu-quyen-mua.shtml?ticket=true" <web:menuOut code='subMenuTrading_BuyingRightItem' classValue='active'/>>
               <s:text name="Message.LeftMenu.SearchBuyingRight"/>
            </a>
        </li>
        
        <%-- <li>
            <a href="${tradingUrl}/giao-dich-co-phieu/xac-nhan-phieu-lenh.shtml" <web:menuOut code='subMenuTrading_OrderListConfirmationItem' classValue='active'/>>
               <s:text name="Message.LeftMenu.OrderListConfirmation"/>
            </a>
        </li> --%>
        <%-- <li>
            <a href="${tradingUrl}/giao-dich-co-phieu/dat-lenh-mua-ban-lo-le.shtml?ticket=true" <web:menuOut code='subMenuTrading_BuyingRightItem' classValue='active'/>>
               <s:text name="Message.LeftMenu.PlaceOddOrder"/>
            </a>
        </li>
        <li>
            <a href="${tradingUrl}/giao-dich-co-phieu/lich-su-mua-ban-lo-le-lo.shtml.shtml?ticket=true" <web:menuOut code='subMenuTrading_BuyingRightItem' classValue='active'/>>
               <s:text name="Message.LeftMenu.ViewOddOrders"/>
            </a>
        </li> --%>

    </ul>
    <ul class="list_menu_sup line ">
        <li class="title">
            <a href="${tradingUrl}/giao-dich-tien/chuyen-tien-truc-tuyen.shtml?ticket=true">
                <s:text name="Message.LeftMenu.MoneyTransaction"/>
            </a>
        </li>
        <li>
            <a href="${tradingUrl}/giao-dich-tien/chuyen-tien-truc-tuyen.shtml?ticket=true" <web:menuOut code='subMenuMoney_CompleteViewItem' classValue='active'/>>
               <s:text name="Message.LeftMenu.TransferMoneyOnline"/>
            </a>
        </li>
        <%-- <li>
            <a href="${tradingUrl}/giao-dich-tien/sao-ke-chuyen-tien.shtml?ticket=true" <web:menuOut code='subMenuMoney_TransferMoneyStatementItem' classValue='active'/>>
               <s:text name="Message.LeftMenu.TransferMoneyStatement"/>
            </a>
        </li> --%>
        
		<li>
			<a href="${tradingUrl}/giao-dich-tien/ho-tro-lai-suat.shtml" <web:menuOut code='subMenuMoney_HTLSViewItem' classValue='current'/> >
			<s:text name="Message.LeftMenu.TermDeposit"/>
			</a>
		</li>
			
        <li>
            <a href="${tradingUrl}/giao-dich-tien/ung-truoc-tien-ban.shtml?ticket=true"  <web:menuOut code='subMenuMoney_AdvancePaymentItem' classValue='active'/>>
               <s:text name="Message.LeftMenu.AdvancePayment"/>
            </a>
        </li>
    </ul>
    <ul class="list_menu_sup line ">
        <li class="title">
            <a href="${tradingUrl}/chung-khoan-truc-tuyen-tong-quan.shtml">
               <s:text name="Message.LeftMenu.MamagementReport"/>
            </a>
        </li>
        <li>
            <a href="${tradingUrl}/bao-cao-quan-ly/tong-tai-san.shtml?ticket=true" <web:menuOut code='subMenuTrading_BalancesItem' classValue='active'/> >
                <s:text name="Message.LeftMenu.Balance"/>
            </a>
        </li>
        <%-- <li>
            <a href="${tradingUrl}/bao-cao-quan-ly/trang-thai-chung-khoan.shtml?ticket=true" <web:menuOut code='subMenuTrading_PositionItem' classValue='active'/> >
                <s:text name="Message.LeftMenu.PlPosittion"/>
            </a>
        </li> --%>
        <li>
            <a href="${tradingUrl}/bao-cao-quan-ly/lai-lo-danh-muc.shtml?ticket=true" <web:menuOut code='subMenuPortfolio_GainLossItem' classValue='active'/> >
                <s:text name="Message.LeftMenu.GainLossPortfolio"/>
            </a>
        </li>
        <li>
            <a href="${tradingUrl}/bao-cao-quan-ly/bao-cao-no.shtml" <web:menuOut code='subMenuTrading_BAccountStatusItem' classValue='active'/> >
                <s:text name="Message.LeftMenu.DebtStatement"/>
            </a>
        </li>
        <li>
            <a href="${tradingUrl}/bao-cao-quan-ly/sao-ke-truc-tuyen.shtml?ticket=true"  <web:menuOut code='subMenuTrading_CastInvestStatementItem' classValue='active'/> >
                <s:text name="Message.LeftMenu.OnlineStatement"/>
            </a>
        </li>
    </ul>
    <%-- <ul class="list_menu_sup line ">
        <li class="title">
            <a href="${tradingUrl}/ho-tro-lai-suat/gui-tien.shtml?ticket=true">
               <s:text name="Message.LeftMenu.TermDeposit"/>
            </a>
        </li>
        <li>
             <a href="${tradingUrl}/ho-tro-lai-suat/gui-tien.shtml?ticket=true" <web:menuOut code='subMenuTrading_CreateTermDeposit' classValue='active'/> >
                <s:text name="Message.LeftMenu.CreateTermDeposit"/>
             </a>
        </li>
        <li>
             <a href="${tradingUrl}/ho-tro-lai-suat/tat-toan.shtml?ticket=true" <web:menuOut code='subMenuTrading_WithdrawTermDeposit' classValue='active'/> >
                <s:text name="Message.LeftMenu.WithdrawTermDeposit"/>
             </a>
        </li>
        <li>
             <a href="${tradingUrl}/ho-tro-lai-suat/so-du-tien-gui.shtml?ticket=true" <web:menuOut code='subMenuTrading_TermDepositBalance' classValue='active'/> >
                <s:text name="Message.LeftMenu.TermDepositBalance"/>
             </a>
        </li>
        <li>
             <a href="${tradingUrl}/ho-tro-lai-suat/lich-su-giao-dich.shtml?ticket=true" <web:menuOut code='subMenuTrading_TermDepositInquiry' classValue='active'/> >
                <s:text name="Message.LeftMenu.TermDepositInquiry"/>
             </a>
        </li>
    </ul> --%>
    <ul class="list_menu_sup line ">
        <li class="title">
            <s:text name="topmenu.nav.other">TIỆN ÍCH KHÁC</s:text>
        </li>
        <li>
            <a href="<web:url value='/home/RedirectUrl.shtml?code=HoseStockBoard'/>" target="_blank"><s:text name="Message.LeftMenu.DirectHose"/></a>
        </li>
        <li>
            <a href="<web:url value='/home/RedirectUrl.shtml?code=HastcStockBoard'/>" target="_blank"><s:text name="Message.LeftMenu.DirectHnx"/></a>
        </li>
        <li>
            <a href="<web:url value='/home/RedirectUrl.shtml?code=UpcomStockBoard'/>" target="_blank"><s:text name="Message.LeftMenu.DirectUpcom"/></a>
        </li>
        <li>
            <a href="<web:url value='/home/RedirectUrl.shtml?code=ActiveDirect'/>" target="_blank"><s:text name="Message.LeftMenu.ActiveDirect"/></a>
        </li>
    </ul>
    <a href="#" class="icon_dow_supmenu"></a>
</div>