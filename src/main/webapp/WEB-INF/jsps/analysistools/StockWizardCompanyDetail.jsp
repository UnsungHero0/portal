<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
<!--
	var page = 1;
	$().ready(function() {
		var $tabs = $('#container-1').tabs();

		page = '<s:property value="page"/>';
		if (isNaN(page) || page == 'undefined') {
		 	page = 1;
		}
		display();
		$.web_autocomplete.symbols('searchSymbolId', URL_SYMBOL_AUTO_SUGGESTION,
				{width : 310,
					callbackResult: function(e, item){
						$('#stockWizard').submit();
				}}
		);

		$('#stockWizard').bind("submit", function() {
			$('#symbolSearch').val($('#searchSymbolId').val().toUpperCase());
			$('#pageId').val(page);
		});
	})

	function display() {
		$('#page1').hide();
		$('#page2').hide();
		$('#page3').hide();
		$('#page' + page).show();
		for (i =1; i<=4;i++) {
			if (i == page) {
			    $('#linkpage' +page).addClass('ui-tabs-selected');
                $('#linkpageU' +page).addClass('ui-tabs-selected');				
			} else {
				$('#linkpage' +i).removeClass('ui-tabs-selected');
				$('#linkpageU' +i).removeClass('ui-tabs-selected');								
			}
		}

	}

	function next() {
		page++;
		if(page == 4) {
			location.replace('<web:url value="/analysis/StockWizardDifference.shtml"/>?symbol=<s:property value="symbol"/>');
		}
		display();
	}

	function back() {
		page--;
		if (page == 0) {
			location.replace('<web:url value="/analysis/StockWizardView.shtml"/>?symbol=<s:property value="symbol"/>');
		}
		display();
	}

	function gotoPage(newPage) {
		
		page = newPage;
		if(page == 4) {
			//location.replace('<web:url value="/analysis/StockWizardDifference.shtml"/>?symbol=<s:property value="symbol"/>');
			$('#StockWizardDifference').submit();
		}
		display();
	}
//-->
</script>

<s:set name="stockScreener" value="true" scope="action"></s:set>
<s:set name="listedScreenEnable" value="false" scope="action"></s:set>
<s:set name="stockWizard" value="false" scope="action"></s:set>

<form method="post" id="stockWizard" action="<web:url value="/cong-cu-phan-tich-chung-khoan/stock-wizard-company-detail.shtml"/>">
	<input type="hidden" name="symbol" id="symbolSearch" value="" />
	<input type="hidden" name="page" id="pageId" value="" />
</form>

<!-- form submit compare -->
<form method="post" id="StockWizardDifference" action="<web:url value="/cong-cu-phan-tich-chung-khoan/stock-wizard-difference.shtml"/>">
    <input type="hidden" name="symbol" id="symbolCompare" value="<s:property value="symbol"/>" />        
</form>

<tiles:insertDefinition name="Analysis.InvestmentIdeasTmpl">
	<tiles:putAttribute name="MainContent">
		<!-- Insert content here -->
		<div>
			<div class="box_taobieudo"><s:text name="web.label.stock.wizard.Name_Of_Symbol" />  
			    <s:textfield name="symbol" id="searchSymbolId" style="height: 21px;margin-left: 10px;margin-right: 10px;width: 145px;"></s:textfield>
			    <input type="button" class="button" value="<s:text name="web.label.stock.wizard.Find"></s:text>" >			    
			</div>
			
			<div class="clear"></div>
			<div class="padding-bottom10 line_top_stock_wizard padding-top10">
				<ul class="ui-tabs-nav tab_button" id="tab_menusup_ttcp_dn">
					<li id="linkpage1">
					   <a href="javascript:gotoPage(1)"><span><s:text name="analysis.stockInfo.stockWizardCompany.companyInformation"/></span></a></span>
					</li>
					<li id="linkpage2">
					   <a href="javascript:gotoPage(2)"><span><s:text name="analysis.stockInfo.stockWizardCompany.stockInformation"/></span></a></span>
					</li>
					<li id="linkpage3">
					   <a href="javascript:gotoPage(3)"><span><s:text name="analysis.stockInfo.stockWizardCompany.newsReports"/></span></a></span>
					</li>
					<li id="linkpage4">
					   <a href="javascript:gotoPage(4)"><span><s:text name="analysis.stockInfo.stockWizardCompany.comparation"/></span></a></span>
					</li>
				</ul>
                
                <div class="clear"></div>						
						
				<!-- ####################################################Page 1#################################################### -->
				<div id="page1" class="ui-tabs-container" style="">
				    <ul class="list12">
				        <li>
				            <h2><strong><s:property value="%{getText('web.label.stock.wizard.Fundamental_Title', {model.symbol})}"escape="false" /></strong></h2>
				            <p>
				                <br><strong class="orange"><s:text name="web.label.stock.wizard.Company_Do" /></strong><br>
				                <c:out value="${ifoCompanyProfileViewId.mainBusiness}" />
				                
				                <br><br>
				                
				                <br><strong class="orange"><s:text name="web.label.stock.wizard.Market_Position" /></strong><br>
	                            <c:out value="${ifoCompanyProfileViewId.marketPosition}" />
	                            
	                            <br><br>
	                            	
	                            <br><strong class="orange"><s:text name="web.label.stock.wizard.How_Sale_Earn" /></strong><br>                              
	                            <div class="table_company clearfix">
				                    <table align="center" cellspacing="0" border="0"
				                        style="border-top: 1px solid #B0B0B0">
				                        <tr
				                            style="background: url(../images/img/imgbgr_firstline.gif) repeat-x left bottom">
				                            <td>
				                                <span class="txtText"> <b><s:text
				                                            name="web.label.stock.wizard.Sale_Income" />
				                                </b> </span>
				                            </td>
				                            <td align="center">
				                                <span class="txtText">
				                                    <b>
				                                        <s:text name="web.label.company" />
				                                    </b>
				                                </span>
				                            </td>
				                            <td class="col_end" align="center">
				                                <span class="txtText">
				                                    <b>
				                                        <s:text name="web.label.stock.wizard.Industry_Group" />
				                                    </b>
				                                </span>
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                <span class="txtText">
				                                    <s:text name="web.label.stock.wizard.Sale" />
				                                </span>
				                            </td>
				                            <td align="right" style="padding-right: 5px">
				                                <span class="txtText">
				                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sales_4_company" showPercent="false" showArrow = "false" pattern="###,##0"/>
				                                </span>
				                            </td>
				                            <td class="col_end" align="right" style="padding-right: 5px">
				                                <span class="txtText">
				                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sales_4_industry_12_months" showPercent="false" showArrow = "false" pattern="###,##0"/>
				                                </span>
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                <span class="txtText">
				                                    <s:text name="web.label.stock.wizard.Income" />
				                                </span>
				                            </td>
				                            <td align="right" style="padding-right: 5px">
				                                <span class="txtText">
				                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="income_4_company_12_months" showPercent="false" showArrow = "false" pattern="###,##0"/>
				                                </span>
				                            </td>
				                            <td class="col_end" align="right" style="padding-right: 5px">
				                                <span class="txtText">
				                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="income_4_industry_12_months" showPercent="false" showArrow = "false" pattern="###,##0"/>
				                                </span>
				                            </td>
				                        </tr>
				                    </table>
				                </div>
				                
				                <br><br>
				                
				                <br><strong class="orange"><s:text name="web.label.stock.wizard.Company_Do" /></strong><br>	
				                <div class="table_company clearfix">
				                    <table align="center" cellspacing="0" border="0"
				                        style="border-top: 1px solid #B0B0B0">
				                        <tr style="background: url(../images/img/imgbgr_firstline.gif) repeat-x left bottom">
				                            <td>
				                                <span class="txtText">
				                                    <b>
				                                        <s:text name="web.label.stock.wizard.Sale_Income" />
				                                    </b>
				                                </span>
				                            </td>
				                            <td align="center">
				                                <span class="txtText">
				                                    <b>
				                                        <s:text name="web.label.company" />
				                                    </b>
				                                </span>
				                            </td>
				                            <td  class="col_end" align="center">
				                                <span class="txtText">
				                                    <b>
				                                        <s:text name="web.label.stock.wizard.Industry_Group" />
				                                    </b>
				                                </span>
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                <span class="txtText">
				                                    <s:text name="web.label.stock.wizard.Sales_Growth" />
				                                </span>
				                            </td>
				                            <td align="right" style="padding-right: 5px">
				                                <span class="txtText">
				                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sales_growth_4_company_12_months" showPercent="true" showArrow = "true"/>
				                                </span>
				                            </td>
				                            <td class="col_end" align="right" style="padding-right: 5px">
				                                <span class="txtText">
				                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sales_growth_4_industry_12_months" showPercent="true" showArrow = "true"/>
				                                </span>
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                <span class="txtText">
				                                    <s:text name="web.label.stock.wizard.Income_Growth" />
				                                </span>
				                            </td>
				                            <td align="right" style="padding-right: 5px">
				                                <span class="txtText">
				                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="income_growth_4_company_12_months" showPercent="true" showArrow = "true"/>
				                                </span>
				                            </td>
				                            <td class="col_end" align="right" style="padding-right: 5px">
				                                <span class="txtText">
				                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="income_growth_4_industry_12_months" showPercent="true" showArrow = "true"/>
				                                </span>
				                            </td>
				                        </tr>
				                    </table>
				                </div>
				                
				                <br><br>
				                
				                <br><strong class="orange"><s:text name="web.label.stock.wizard.How_Profitable" /></strong><br>
				                <div class="table_company clearfix">
				                    <table align="center" cellspacing="0" border="0"
				                        style="border-top: 1px solid #B0B0B0">
				                        <tr style="background: url(../images/img/imgbgr_firstline.gif) repeat-x left bottom">
				                            <td width="169">
				                                &nbsp;
				                            </td>
				                            <td width="99" align="center">
				                                <span class="txtText">
				                                    <b>
				                                        <s:text name="web.label.company" />
				                                    </b>
				                                </span>
				                            </td>
				                            <td width="124" class="col_end" align="center">
				                                <span class="txtText">
				                                    <b>
				                                        <s:text name="web.label.stock.wizard.Industry_Group" />
				                                    </b>
				                                </span>
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                <span class="txtText">
				                                    <s:text name="web.label.stock.wizard.Net_Profit_Margin" />
				                                </span>
				                            </td>
				                            <td align="right" style="padding-right: 5px">
				                                <span class="txtText">
				                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="net_profit_margin_4_company" showPercent="true" showArrow = "false"/>
				                                </span>
				                            </td>
				                            <td class="col_end" align="right" style="padding-right: 5px">
				                                <span class="txtText">
				                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="net_profit_margin_4_industry" showPercent="true" showArrow = "false"/>
				                                </span>
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                <span class="txtText">
				                                    <s:text name="web.label.ROA" />
				                                </span>
				                            </td>
				                            <td align="right" style="padding-right: 5px">
				                                <span class="txtText">
				                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="roa" showPercent="true" showArrow = "false"/>
				                                </span>
				                            </td>
				                            <td class="col_end" align="right" style="padding-right: 5px">
				                                <span class="txtText">
				                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="roa_4_industry" showPercent="true" showArrow = "false"/>
				                                </span>
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                <span class="txtText">
				                                    <s:text name="web.label.ROE" />
				                                </span>
				                            </td>
				                            <td align="right" style="padding-right: 5px">
				                                <span class="txtText">
				                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="roe" showPercent="true" showArrow = "false"/>
				                                </span>
				                            </td>
				                            <td class="col_end" align="right" style="padding-right: 5px">
				                                <span class="txtText">
				                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="roe_4_industry" showPercent="true" showArrow = "false"/>
				                                </span>
				                            </td>
				                        </tr>
				                    </table>
				                </div>
				                
				                <br><br>
				                
				                <br><strong class="orange"><s:text name="web.label.stock.wizard.How_Financial_Health" /></strong><br>
				                <div class="table_company clearfix">
				                    <table align="center" cellspacing="0" border="0"
				                            style="border-top: 1px solid #B0B0B0">
				                        <tr style="background: url(../images/img/imgbgr_firstline.gif) repeat-x left bottom">
				                                &nbsp;
				                            </td>
				                            <td width="95" align="center">
				                                <span class="txtText">
				                                    <b>
				                                        <s:text name="web.label.company" />
				                                    </b>
				                                </span>
				                            </td>
				                            <td width="108" class="col_end" align="center">
				                                <span class="txtText">
				                                    <b>
				                                        <s:text name="web.label.stock.wizard.Industry_Group" />
				                                    </b>
				                                </span>
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                <span class="txtText">
				                                    <s:text name="web.label.stock.wizard.Debt_Equity_Ratio" />
				                                </span>
				                            </td>
				                            <td align="right" style="padding-right: 5px">
				                                <span class="txtText">
				                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="deb_equiry_ratio_4_company" showPercent="false" showArrow = "false"/>
				                                </span>
				                            </td>
				                            <td class="col_end" align="right" style="padding-right: 5px">
				                                <span class="txtText">
				                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="deb_equiry_ratio_4_industry" showPercent="false" showArrow = "false"/>
				                                </span>
				                            </td>
				                        </tr>
				                    </table>
				                </div>
				            </p>
				        </li>
				    </ul>
				</div>
				
				<!-- ####################################################Page 2#################################################### -->
				<div id="page2" class="ui-tabs-container" style="padding-left: 30px;">
					<div class="content_stock_wizard_ttcp">
					    <p class="space orange">
	                        <b><s:text name="web.label.stock.wizard.ad8">How has the stock performed ?</s:text></b>
	                    </p>
	                    <p>
		                    <s:text name="web.label.stock.wizard.ad9">
		                        Investors need to know how a stock has performed relative to all other stocks. Generally they attempt to hold the market's top-performing securities -- those that have done better over the past year than the majority of stocks in their industrial group and all stocks in our database. Look for a positive trend in the 12-month, 6-month and 3-month periods.
		                    </s:text>                    
	                    </p>
	                    <div class="table_company clearfix">
		                    <table align="center" width="300" cellspacing="0" border="0" style="border-top: 1px solid #B0B0B0">
		                        <tr style="background: url(../images/img/imgbgr_firstline.gif) repeat-x left bottom">
		                            <td>&nbsp;</td>
		                            <td align="center">
		                                <span class="txtText">
		                                    <b>
		                                        <s:text name="web.label.company"/>
		                                    </b>
		                                </span>
		                            </td>
		                            <td class="col_end" align="center">
		                                <span class="txtText">
		                                    <b>
		                                        <s:text name="web.label.stock.wizard.Industry_Group"/>
		                                    </b>
		                                </span>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td>
		                                <span class="txtText">
		                                    <s:text name="web.label.stock.wizard.three_months"/>
		                                </span>
		                            </td>
		                            <td align="right" style="padding-right: 5px">
		                                <span class="txtText">
		                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="3_mo_price_change" showPercent="true" showArrow = "true"/>
		                                </span>
		                            </td>
		                            <td class="col_end" align="right" style="padding-right: 5px">
		                                <span class="txtText">
		                                <c:choose>
		                                    <c:when test="${f1000030 > 0}">
		                                        <img src="<web:url value='/images/front/up.gif'/>" />+<fmt:formatNumber pattern="###,##0.##"><c:out value="${100*f1000030}"/></fmt:formatNumber>%
		                                    </c:when>
		                                    <c:when test="${f1000030 < 0}">
		                                        <img src="<web:url value='/images/front/down.gif'/>" />-;<fmt:formatNumber pattern="###,##0.##"><c:out value="${100*f1000030}"/></fmt:formatNumber>%
		                                    </c:when>
		                                    <c:otherwise>
		                                        <img src="<web:url value='/images/front/bang.gif'/>" />&nbsp;<fmt:formatNumber pattern="###,##0.##"><c:out value="${100*f1000030}"/></fmt:formatNumber>%
		                                    </c:otherwise>
		                                </c:choose>
		                                </span>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td>
		                                <span class="txtText">
		                                    <s:text name="web.label.stock.wizard.six_months"/>
		                                </span>
		                            </td>
		                            <td align="right" style="padding-right: 5px">
		                                <span class="txtText">
		                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="6_mo_price_change" showPercent="true" showArrow = "true"/>
		                                </span>
		                            </td>
		                            <td class="col_end" align="right" style="padding-right: 5px">
		                                <span class="txtText">
		                                <c:choose>
		                                    <c:when test="${f1000031 > 0}">
		                                        <img src="<web:url value='/images/front/up.gif'/>" />+<fmt:formatNumber pattern="###,##0.##"><c:out value="${100*f1000031}"/></fmt:formatNumber>%
		                                    </c:when>
		                                    <c:when test="${f1000031 < 0}">
		                                        <img src="<web:url value='/images/front/down.gif'/>" />-;<fmt:formatNumber pattern="###,##0.##"><c:out value="${100*f1000031}"/></fmt:formatNumber>%
		                                    </c:when>
		                                    <c:otherwise>
		                                        <img src="<web:url value='/images/front/bang.gif'/>" />&nbsp;<fmt:formatNumber pattern="###,##0.##"><c:out value="${100*f1000031}"/></fmt:formatNumber>%
		                                    </c:otherwise>
		                                </c:choose>
		                                </span>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td>
		                                <span class="txtText">
		                                    <s:text name="web.label.stock.wizard.twelve_months"/>
		                                </span>
		                            </td>
		                            <td align="right" style="padding-right: 5px">
		                                <span class="txtText">
		                                    <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="52_week_chg" showPercent="true" showArrow = "true"/>
		                                </span>
		                            </td>
		                            <td  class="col_end" align="right" style="padding-right: 5px">
		                                <span class="txtText">
		                                <c:choose>
		                                    <c:when test="${f1000033 > 0}">
		                                        <img src="<web:url value='/images/front/up.gif'/>" />+<fmt:formatNumber pattern="###,##0.##"><c:out value="${100*f1000033}"/></fmt:formatNumber>%
		                                    </c:when>
		                                    <c:when test="${f1000033 < 0}">
		                                        <img src="<web:url value='/images/front/down.gif'/>" />-;<fmt:formatNumber pattern="###,##0.##"><c:out value="${100*f1000033}"/></fmt:formatNumber>%
		                                    </c:when>
		                                    <c:otherwise>
		                                        <img src="<web:url value='/images/front/bang.gif'/>" />&nbsp;<fmt:formatNumber pattern="###,##0.##"><c:out value="${100*f1000033}"/></fmt:formatNumber>%
		                                    </c:otherwise>
		                                </c:choose>
		                                </span>
		                            </td>
		                        </tr>
		                    </table>
		                </div>
	                    
	                    <p class="space orange">
	                        <b><s:text name="web.label.stock.wizard.ad10">Where is the stocks support and resistance?</s:text></b>
	                    </p>
	                    <p>
	                        <s:text name="web.label.stock.wizard.ad11">Investors should note the average prices at which a stock traded over the past 50- and 200-day periods. These "moving averages" tend to provide a floor, or support, for stocks trading above them and a ceiling, or resistance, for stocks trading below them. Stocks that sink below support are in danger of further weakening; stocks that rise above resistance have a shot at new highs.</s:text>               
	                    </p>
	                                        
	                    <div class="block02">
		                    <p>
		                        <b>
		                            <s:property value="%{getText('web.label.stock.wizard.Company_Current_price', {model.symbol})}" escape="false" />
		                        </b>
		                        ${analysisIndexingBean.intraday}
		                    </p>
		                    <c:set var="formatDisplay" value="${strFormatDisplay}"/>
		                    <c:choose>
		                        <c:when test="${formatDisplay == 1}">
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Resistance"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="52_week_high" /> (<s:text name="web.label.stock.wizard._52_Week_High"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Support"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_50" /> (<s:text name="web.label.stock.wizard._50_Days_Moving_Average"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_Second_Support"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_200" /> (<s:text name="web.label.stock.wizard._200_Days_Moving_Average"/>)</p>
		                        </c:when>
		                        <c:when test="${formatDisplay == 2}">
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Resistance"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_200" /> (<s:text name="web.label.stock.wizard._200_Days_Moving_Average"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_Second_Resistance"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_50" /> (<s:text name="web.label.stock.wizard._50_Days_Moving_Average"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Support"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="52_week_low" /> (<s:text name="web.label.stock.wizard._52_Week_Low"/>)</p>
		                        </c:when>
		                        <c:when test="${formatDisplay == 3}">
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Resistance"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_50" /> (<s:text name="web.label.stock.wizard._50_Days_Moving_Average"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_Second_Resistance"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="52_week_high" /> (<s:text name="web.label.stock.wizard._52_Week_High"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Support"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_200" /> (<s:text name="web.label.stock.wizard._200_Days_Moving_Average"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_Second_Support"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="52_week_low" /> (<s:text name="web.label.stock.wizard._52_Week_Low"/>)</p>
		                        </c:when>
		                        <c:when test="${formatDisplay == 4}">
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Support"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_50" /> (<s:text name="web.label.stock.wizard._50_Days_Moving_Average"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_Second_Support"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_200" /> (<s:text name="web.label.stock.wizard._200_Days_Moving_Average"/>)</p>
		                        </c:when>
		                        <c:when test="${formatDisplay == 5}">
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Resistance"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_200" /> (<s:text name="web.label.stock.wizard._200_Days_Moving_Average"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_Second_Resistance"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_50" /> (<s:text name="web.label.stock.wizard._50_Days_Moving_Average"/>)</p>
		                        </c:when>
		                        <c:when test="${formatDisplay == 6}">
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Resistance"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="52_week_high" /> (<s:text name="web.label.stock.wizard._52_Week_High"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Support"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_200" /> (<s:text name="web.label.stock.wizard._200_Days_Moving_Average"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_Second_Support"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_50" /> (<s:text name="web.label.stock.wizard._50_Days_Moving_Average"/>)</p>
		                        </c:when>
		                        <c:when test="${formatDisplay == 7}">
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Resistance"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_50" /> (<s:text name="web.label.stock.wizard._50_Days_Moving_Average"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_Second_Resistance"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_200" /> (<s:text name="web.label.stock.wizard._200_Days_Moving_Average"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Support"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="52_week_low" /> (<s:text name="web.label.stock.wizard._52_Week_Low"/>)</p>
		                        </c:when>
		                        <c:when test="${formatDisplay == 8}">
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Resistance"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_200" /> (<s:text name="web.label.stock.wizard._200_Days_Moving_Average"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_Second_Resistance"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="52_week_high" /> (<s:text name="web.label.stock.wizard._52_Week_High"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Support"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_50" /> (<s:text name="web.label.stock.wizard._50_Days_Moving_Average"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_Second_Support"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="52_week_low" /> (<s:text name="web.label.stock.wizard._52_Week_Low"/>)</p>
		                        </c:when>
		                        <c:when test="${formatDisplay == 9}">
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Support"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_200" /> (<s:text name="web.label.stock.wizard._200_Days_Moving_Average"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_Second_Support"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_50" /> (<s:text name="web.label.stock.wizard._50_Days_Moving_Average"/>)</p>
		                        </c:when>
		                        <c:when test="${formatDisplay == 10}">
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Resistance"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_50" /> (<s:text name="web.label.stock.wizard._50_Days_Moving_Average"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_Second_Resistance"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sma_200" /> (<s:text name="web.label.stock.wizard._200_Days_Moving_Average"/>)</p>
		                        </c:when>
		                        <c:when test="${formatDisplay == 11}">
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Resistance"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="52_week_high" /> (<s:text name="web.label.stock.wizard._52_Week_High"/>)</p>
		                            <p><b><s:text name="web.label.stock.wizard.Company_First_Support"/> <c:out value='${symbol}' />:</b> <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="52_week_low" /> (<s:text name="web.label.stock.wizard._52_Week_Low"/>)</p>
		                        </c:when>
		                    </c:choose>
	                    </div>                             
					</div>							
				</div>
				
				<!-- ####################################################Page 3#################################################### -->
				<div id="page3" class="padding-top10" style="padding-left: 30px;">
				    <div class="padding-top10">
				        <p class="orange">
				            <b><s:text name="web.label.stock.wizard.ad13">What will change an Investor’s opinion of the stock ?</s:text></b>
				        </p>
				        <p class="space">
				            <s:text name="web.label.stock.wizard.ad14">
	                            Stock prices change when investors alter their opinion about a company's prospects. Catalysts for new ideas can come from:
	                        </s:text>
				        </p>
				        
				        <ul class="niceList padding-top10">
	                        <li>
		                          <b>
		                            <s:property value="%{getText('web.label.stock.wizard.ad32', {model.symbol})}" escape="false" />
		                          </b>
		                          <p>
		                            <s:text name="web.label.stock.wizard.ad15">
		                                Look for press releases on new products, partnerships,      strategies or problems that might improve or diminish the company's chances of success, and for news reports on new products, strategies, alliances or trouble
		                            </s:text>
		                          </p>
		
		                          <p class="space" style="text-decoration: underline">
		                            <a href="<web:url value="/tin-doanh-nghiep/"/><c:out  value="${symbol}"/>.shtml">
		                                <s:property value="%{getText('web.label.stock.wizard.ad16', {model.symbol})}" escape="false" />
		                            </a>
		                        </p>
		                    </li>
		                    <li>
		                          <b>
		                            <s:property value="%{getText('web.label.stock.wizard.ad17', {model.symbol})}" escape="false" />
		                          </b>
		                          <p>
		                            <s:text name="web.label.stock.wizard.ad18">
		                                Companies must disclose all material facts about their businesses to federal regulators every 3 months. Read about insider trades and restricted stock sales, institutional ownership changes, special events and other important shareholder issues.
		                            </s:text>
		                          </p>
		                          <p class="space" style="text-decoration: underline">
		                            <a href="<web:url value="/bang-can-doi-ke-toan/"/><c:out  value="${symbol}"/>.shtml"/>
		                                <s:property value="%{getText('web.label.stock.wizard.ad31', {model.symbol})}" escape="false" />
		                            </a>
		                        </p>
		                     </li>
		                     <li>
		                        <b>
		                            <s:property value="%{getText('web.label.stock.wizard.ad19', {model.symbol})}" escape="false" />
		                        </b>
		                          <p>
		                            <s:text name="web.label.stock.wizard.ad20">
		                                Many investors believe that corporate insiders are the savest buyers of their own stock. Insiders can be very early buyers of their stocks at rock-bottom prices.
		                            </s:text>
		                          </p>
		                            <p class="space" style="text-decoration: underline">
		                            <a href="<web:url value="/giao-dich-noi-bo/"/><c:out  value="${symbol}"/>.shtml"/>
		                                <s:property value="%{getText('web.label.stock.wizard.ad21', {model.symbol})}" escape="false" />
		                            </a>
		                        </p>
		                     </li>
				        </ul>
				    </div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>