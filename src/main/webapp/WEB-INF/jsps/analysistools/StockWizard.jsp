<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<script type="text/javascript">
<!--
      $().ready(function() {
			
			$.web_autocomplete.symbols('symbol', URL_SYMBOL_AUTO_SUGGESTION,
					{width : 310, 
						callbackResult: function(e, item){
							$('#stockWizard').submit();
					}}
			);
			
			$('#stockWizard').bind("submit", function() {
				$('#symbol').val($('#symbol').val().toUpperCase());
			});
      });
//-->
</script>

<s:set name="stockScreener" value="true" scope="action"></s:set>
<s:set name="listedScreenEnable" value="false" scope="action"></s:set>
<s:set name="stockWizard" value="false" scope="action"></s:set>

<tiles:insertDefinition name="Analysis.InvestmentIdeasTmpl">
	<tiles:putAttribute name="MainContent">
		<form method="post" id="stockWizard" action="<web:url value="/cong-cu-phan-tich-chung-khoan/stock-wizard-company-detail.shtml"/>">
            <div id="tab-2" class="content_tab ui-tabs-container" style="">
                <div class="box_taobieudo"><s:text name="web.label.stock.wizard.Name_Of_Symbol"/>  
                    <s:textfield name="symbol" id="symbol" style="height: 21px;margin-left: 10px;margin-right: 10px;width: 145px;"></s:textfield>
                    <input type="submit" class="iButton" value="<s:text name="web.label.stock.wizard.Find"></s:text>">
                </div>
                
                <div class="clear"></div>
                
                <div class="padding-bottom10 line_top_stock_wizard padding-top10">
	                <ul class="list12">	                    
	                    <li>
	                        <h2 style="font-size: 14px;"><s:text name="web.label.stock.wizard.ad2">How it works</s:text></h2>
	                        <p class="iconDStockWizard"><span class="textDStockWinzard"><s:text name="web.label.stock.wizard.ad3">Enter a stock symbol and click Go.</span></s:text></p>
	                        <p class="iconDStockWizard"><span class="textDStockWinzard"><s:text name="web.label.stock.wizard.ad4">Step through each page and any additional links and functions provided.</span></s:text></p>
	                        <p class="iconDStockWizard"><span class="textDStockWinzard"><s:text name="web.label.stock.wizard.ad5">On the final page, compare your chosen stock to any other stock.</span></s:text></p>
	                    </li>
	                    
	                    <li>
	                        <h2 style="font-size: 14px;"><s:text name="web.label.stock.wizard.ad6">Stock Research Wizard helps investor look at five vital issues:</s:text></h2>
	                        <p><s:text name="web.label.stock.wizard.ad1">How it works</s:text></p>
	                    </li>
	                    
	                    <li>
	                        <table border="0" cellspacing="0" cellpadding="10">
		                        <tr>
		                            <td>
		                                <img src="<web:url value='/images/web/issue01.png'/>" style="width: 60px;"/>
		                            </td>
		                            <td valign="top" style="padding-left: 20px;">
		                                <s:text name="web.label.stock.wizard.Fudamentals" />
		                            </td>
		                        </tr>
	                        </table>
	                    </li>
	                    
	                    <li>
	                        <table border="0" cellspacing="0" cellpadding="10">
	                            <tr>
		                            <td>
		                                <img src="<web:url value='/images/web/issue02.png'/>" style="width: 60px;"/>
		                            </td>
		                            <td valign="top" style="padding-left: 20px;">
		                                <s:text name="web.label.stock.wizard.Price_History" />
		                            </td>
	                            </tr>
	                        </table>
	                    </li>
	                    
	                    <li>
	                        <table border="0" cellspacing="0" cellpadding="10">
	                            <tr>
		                            <td>
		                                <img src="<web:url value='/images/web/issue03.png'/>" style="width: 60px;"/>
		                            </td>
		                            <td valign="top" style="padding-left: 20px;">
		                                <s:text name="web.label.stock.wizard.Price_Target" />
		                            </td>
	                            </tr>
	                        </table>
	                    </li>
	                    
	                    <li>
	                        <table border="0" cellspacing="0" cellpadding="10">
	                            <tr>
		                            <td>
		                                <img src="<web:url value='/images/web/issue04.png'/>" style="width: 60px;"/>
		                            </td>
		                            <td valign="top" style="padding-left: 20px;">
		                                <s:text name="web.label.stock.wizard.Comparison" />
		                            </td>
	                            </tr>
	                        </table>
	                    </li>
	                </ul>    
                </div>                                                
            </div>
                        		
		</form>
	</tiles:putAttribute>
</tiles:insertDefinition>

