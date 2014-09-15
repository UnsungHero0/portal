<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<script type="text/javascript">
<!--
	$().ready(function() {
		$.web_autocomplete.symbols('searchSymbolId', URL_SYMBOL_AUTO_SUGGESTION,
				{width : 310,
					callbackResult: function(e, item){
						$('#stockWizard').submit();
				}}
		);

		$('#stockWizard').bind("submit", function() {
			$('#symbolSearch').val($('#searchSymbolId').val().toUpperCase());
		});
	});
	function back() {
		location.replace('<s:url action="StockWizardCompanyDetail"/>?page=3&symbol=<s:property value="symbol"/>');
	}
	
    function gotoPage(newPage) {                       
           $('#StockWizardCompanyDetail #pageId').attr('value', newPage);
           $('#StockWizardCompanyDetail').submit();
           
    }

//-->
</script>

<form method="post" id="stockWizard" action="<web:url value="/cong-cu-phan-tich-chung-khoan/stock-wizard-difference.shtml"/>">
    <input type="hidden" name="symbol" id="symbolSearch" value="" />
    <input type="hidden" name="page" id="pageId" value="4" />
</form>

<form method="post" id="StockWizardCompanyDetail" action="<web:url value="/cong-cu-phan-tich-chung-khoan/stock-wizard-company-detail.shtml"/>">
    <input type="hidden" name="symbol" id="symbolCompanyDetail" value="<s:property value="symbol"/>" />
    <input type="hidden" name="page" id="pageId" value="4" />
</form>

<s:set name="stockScreener" value="true" scope="action"></s:set>
<s:set name="listedScreenEnable" value="false" scope="action"></s:set>
<s:set name="stockWizard" value="false" scope="action"></s:set>
<tiles:insertDefinition name="Analysis.InvestmentIdeasTmpl">
	<tiles:putAttribute name="MainContent">
		<s:form action="stock-wizard-difference.shtml" theme="simple">
						
			<div class="box_taobieudo">
			    <s:text name="web.label.stock.wizard.Name_Of_Symbol" />
			    <s:textfield name="symbol" id="searchSymbolId" theme="simple" style="height: 21px;margin-left: 10px;margin-right: 10px;width: 145px;"></s:textfield>
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
					<li class="ui-tabs-selected">
						<a href="" class=""><span><s:text name="analysis.stockInfo.stockWizardCompany.comparation"/></span></a>
					</li>
				</ul>
				
				<div class="clear"></div>
				<ul class="list12">
				    <li>
				        <h2><strong><s:property value="%{getText('web.label.stock.wizard.ad22', {model.symbol})}" escape="false" /></strong></h2>
				        <p>
				            <s:text name="web.label.stock.wizard.ad23">How does the stock stack up?</s:text>				            
				            <br>				            
				            <s:text name="web.label.stock.wizard.ad24">
                                Investors want to know how a company matches up in key categories against others in its industry. Select up to two peers to compare. The best figure in each category is highlighted in each row below.
                            </s:text>
				        </p>
				    </li>
				    
				    <li>
				        <div class="box_taobieudo"><s:text name="web.label.stock.wizard.ad25">Enter symbol compare</s:text> 
                            <s:textfield name="symbol1" id="symbol1" style="height: 21px;margin-left: 10px;margin-right: 10px;width: 145px;"></s:textfield> &amp;
                            <s:textfield name="symbol2" id="symbol2" style="height: 21px;margin-left: 10px;margin-right: 10px;width: 145px;"></s:textfield>
                            <input type="submit" class="button" value="<s:property value="getText('web.label.stock.wizard.ad30')"/>">                            
                        </div>
				    </li>
				    
				    <li>
                        <table width="100%" class="tbl_sosanh" width="300" cellspacing="0" border="0" style="border-top: 1px solid #B0B0B0">
		                    <colgroup>
		                        <col width="31%"/>
		                        <col width="23%"/>
		                        <col width="23%"/>
		                        <col width="23%"/>
		                    </colgroup>
		                    <tr class="title">
		                        <td>&nbsp;</td>
		                        <td class="rowa" style="text-align: center;">
		                            <b><c:out value='${symbol}' /></b>
		                        </td>
		                        <td class="rowb" style="text-align: center;">
		                            <b><c:out value='${symbol1}' /></b>		                           
		                        </td>
		                        <td class="rowb" style="text-align: center;">
		                            <b><c:out value='${symbol2}' /></b>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="t8-al">
		                            <s:text name="web.label.stock.wizard.ad26" />
		                        </td>
		                        <td >
		                            <c:out value='${ifoCompanyProfileViewId.vietnameseName}' />
		                        </td>
		                        <td >
		                            <c:out value='${ifoCompanyProfileViewId1.vietnameseName}' />
		                        </td>
		                        <td class="col_end"  style="padding-right: 5px">
		                            <c:out value='${ifoCompanyProfileViewId2.vietnameseName}' />
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="t8-al">
		                            <s:text name="web.label.stock.wizard.ad27" />
		                        </td>
		                        <td >
		                            <c:out value='${ifoCompanyProfileViewId.industry}' />
		                        </td>
		                        <td >
		                            <c:out value='${ifoCompanyProfileViewId1.industry}' />
		                        </td>
		                        <td class="col_end"  style="padding-right: 5px">
		                            <c:out value='${ifoCompanyProfileViewId2.industry}' />
		                        </td>
		                    </tr>
		                    <tr class="t4-color">
		                        <td colspan="4" class="col_end" align="left" style="padding-right: 5px">
	                                <b>
	                                    <s:text name="web.label.stock.wizard.ad28" />
	                                </b>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="t8-al">
		                              <s:text name="web.label.stock.wizard.Maket_Cap_4_Industry" />
		                        </td>
		                        <td align="right">
		                              <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="market_cap" showPercent="false" showArrow = "false" pattern="###,##0"/>
		                        </td>
		                        <td align="right">
		                              <web:analysisIndexing bean="${analysisIndexingBean1}" showNumber ="true" itemName="market_cap" showPercent="false" showArrow = "false" pattern="###,##0"/>
		                        </td>
		                        <td class="col_end" align="right" style="padding-right: 5px">
		                              <web:analysisIndexing bean="${analysisIndexingBean2}" showNumber ="true" itemName="market_cap" showPercent="false" showArrow = "false" pattern="###,##0"/>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="t8-al">
		                              <s:text name="web.label.stock.wizard.Assets" />
		                        </td>
		                        <td align="right">
		                              <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="assets_4_company" showPercent="false" showArrow = "false" pattern="###,##0"/>
		                        </td>
		                        <td align="right">
		                              <web:analysisIndexing bean="${analysisIndexingBean1}" showNumber ="true" itemName="assets_4_company" showPercent="false" showArrow = "false" pattern="###,##0"/>
		                        </td>
		                        <td class="col_end" align="right" style="padding-right: 5px">
		                              <web:analysisIndexing bean="${analysisIndexingBean2}" showNumber ="true" itemName="assets_4_company" showPercent="false" showArrow = "false" pattern="###,##0"/>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="t8-al">
		                              <s:text name="web.label.stock.wizard.Equities" />
		                        </td>
		                        <td align="right">
		                              <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="equities_4_company" showPercent="false" showArrow = "false" pattern="###,##0"/>
		                        </td>
		                        <td align="right">
		                              <web:analysisIndexing bean="${analysisIndexingBean1}" showNumber ="true" itemName="equities_4_company" showPercent="false" showArrow = "false" pattern="###,##0"/>
		                        </td>
		                        <td class="col_end" align="right" style="padding-right: 5px">
		                              <web:analysisIndexing bean="${analysisIndexingBean2}" showNumber ="true" itemName="equities_4_company" showPercent="false" showArrow = "false" pattern="###,##0"/>
		                        </td>
		                    </tr>
		
		                    <tr class="t4-color">
		                        <td colspan="4" class="col_end" align="left" style="padding-right: 5px">		                         
		                              <b><s:text name="web.label.stock.wizard.ad29" /> </b>
		                        </td>
		                    </tr>
		
		                    <tr>
		                        <td class="t8-al">
		                                <s:text name="web.label.stock.wizard.Sales_Growth" />
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="sales_growth_4_company_12_months" showPercent="true" showArrow = "true"/>
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean1}" showNumber ="true" itemName="sales_growth_4_company_12_months" showPercent="true" showArrow = "true"/>
		                        </td>
		                        <td class="col_end" align="right" style="padding-right: 5px">
		                                <web:analysisIndexing bean="${analysisIndexingBean2}" showNumber ="true" itemName="sales_growth_4_company_12_months" showPercent="true" showArrow = "true"/>
		                        </td>
		                    </tr>
		
		                    <tr>
		                        <td class="t8-al">
		                                <s:text name="web.label.stock.wizard.Income_Growth" />
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="income_growth_4_company_12_months" showPercent="true" showArrow = "true"/>
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean1}" showNumber ="true" itemName="income_growth_4_company_12_months" showPercent="true" showArrow = "true"/>
		                        </td>
		                        <td class="col_end" align="right" style="padding-right: 5px">
		                                <web:analysisIndexing bean="${analysisIndexingBean2}" showNumber ="true" itemName="income_growth_4_company_12_months" showPercent="true" showArrow = "true"/>
		                        </td>
		                    </tr>
		
		                    <tr class="t4-color">
		                        <td colspan="4" class="col_end" align="left" style="padding-right: 5px">
		                                <b>
		                                    <s:text name="web.label.stock.wizard.Profitability_ratios_of_company" />
		                                </b>
		                        </td>
		                    </tr>
		
		                    <tr>
		                        <td class="t8-al">
		                                <s:text name="web.label.ROA" />
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="roa" showPercent="true" showArrow = "false"/>
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean1}" showNumber ="true" itemName="roa" showPercent="true" showArrow = "false"/>
		                        </td>
		                        <td class="col_end" align="right" style="padding-right: 5px">
		                                <web:analysisIndexing bean="${analysisIndexingBean2}" showNumber ="true" itemName="roa" showPercent="true" showArrow = "false"/>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="t8-al">
		                                <s:text name="web.label.ROE" />
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="roe" showPercent="true" showArrow = "false"/>
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean1}" showNumber ="true" itemName="roe" showPercent="true" showArrow = "false"/>
		                        </td>
		                        <td class="col_end" align="right" style="padding-right: 5px">
		                                <web:analysisIndexing bean="${analysisIndexingBean2}" showNumber ="true" itemName="roe" showPercent="true" showArrow = "false"/>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="t8-al">
		                                <s:text name="web.label.EPS" />
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="eps" showPercent="false" showArrow = "false" pattern="###,##0"/>
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean1}" showNumber ="true" itemName="eps" showPercent="false" showArrow = "false" pattern="###,##0"/>
		                        </td>
		                        <td class="col_end" align="right" style="padding-right: 5px">
		                                <web:analysisIndexing bean="${analysisIndexingBean2}" showNumber ="true" itemName="eps" showPercent="false" showArrow = "false" pattern="###,##0"/>
		                        </td>
		                    </tr>
		                    <tr class="t4-color">
		                        <td colspan="4" class="col_end" align="left" style="padding-right: 5px">
		                                <b>
		                                    <s:text name="web.label.stock.wizard.Whose_shares_are_price_cheapest" />
		                                </b>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="t8-al">
		                                <s:text name="web.label.stock.wizard.Price_Earning_Ratio" />
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="pe" showPercent="false" showArrow = "false"/>
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean1}" showNumber ="true" itemName="pe" showPercent="false" showArrow = "false"/>
		                        </td>
		                        <td class="col_end" align="right" style="padding-right: 5px">
		                                <web:analysisIndexing bean="${analysisIndexingBean2}" showNumber ="true" itemName="pe" showPercent="false" showArrow = "false"/>
		                        </td>
		                    </tr>
		                    <tr class="t4-color">
		                        <td colspan="4" class="col_end" align="left" style="padding-right: 5px">
		                                <b>
		                                    <s:text name="web.label.stock.wizard.Whose_financial_health_are_strongest" />
		                                </b>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="t8-al">
		                                <s:text name="web.label.stock.wizard.Net_Profit_Margin" />
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="net_profit_margin_4_company" showPercent="true" showArrow = "false"/>
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean1}" showNumber ="true" itemName="net_profit_margin_4_company" showPercent="true" showArrow = "false"/>
		                        </td>
		                        <td class="col_end" align="right" style="padding-right: 5px">
		                                <web:analysisIndexing bean="${analysisIndexingBean2}" showNumber ="true" itemName="net_profit_margin_4_company" showPercent="true" showArrow = "false"/>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="t8-al">
		                                <s:text name="web.label.stock.wizard.Debt_Equity_Ratio" />
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="deb_equiry_ratio_4_company" showPercent="false" showArrow = "false"/>
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean1}" showNumber ="true" itemName="deb_equiry_ratio_4_company" showPercent="false" showArrow = "false"/>
		                        </td>
		                        <td class="col_end" align="right" style="padding-right: 5px">
		                                <web:analysisIndexing bean="${analysisIndexingBean2}" showNumber ="true" itemName="deb_equiry_ratio_4_company" showPercent="false" showArrow = "false"/>
		                        </td>
		                    </tr>
		                    <tr class="t4-color">
		                        <td colspan="4" class="col_end" align="left" style="padding-right: 5px">
		                                <b>
		                                    <s:text name="web.label.stock.wizard.Whose_share_price_performance_best_last_year" />
		                                </b>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="t8-al">
		                                <s:text name="web.label.stock.wizard.Price_Change_3Mon" />
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="3_mo_price_change" showPercent="true" showArrow = "true"/>
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean1}" showNumber ="true" itemName="3_mo_price_change" showPercent="true" showArrow = "true"/>
		                        </td>
		                        <td class="col_end" align="right" style="padding-right: 5px">
		                                <web:analysisIndexing bean="${analysisIndexingBean2}" showNumber ="true" itemName="3_mo_price_change" showPercent="true" showArrow = "true"/>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="t8-al">
		                                <s:text name="web.label.stock.wizard.Price_Change_6Mon" />
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="6_mo_price_change" showPercent="true" showArrow = "true"/>
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean1}" showNumber ="true" itemName="6_mo_price_change" showPercent="true" showArrow = "true"/>
		                        </td>
		                        <td class="col_end" align="right" style="padding-right: 5px">
		                                <web:analysisIndexing bean="${analysisIndexingBean2}" showNumber ="true" itemName="6_mo_price_change" showPercent="true" showArrow = "true"/>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="t8-al">
		                                <s:text name="web.label.stock.wizard.Price_Change_12Mon" />
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean}" showNumber ="true" itemName="52_week_chg" showPercent="true" showArrow = "true"/>
		                        </td>
		                        <td align="right">
		                                <web:analysisIndexing bean="${analysisIndexingBean1}" showNumber ="true" itemName="52_week_chg" showPercent="true" showArrow = "true"/>
		                        </td>
		                        <td class="col_end" align="right" style="padding-right: 5px">
		                                <web:analysisIndexing bean="${analysisIndexingBean2}" showNumber ="true" itemName="52_week_chg" showPercent="true" showArrow = "true"/>
		                        </td>
		                    </tr>
		                </table>
				    </li>
				</ul>
			</div>                        				
		</s:form>
	</tiles:putAttribute>
</tiles:insertDefinition>
