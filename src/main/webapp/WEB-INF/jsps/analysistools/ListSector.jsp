<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
	var SECTOR = '<s:text name="web.label.sector"></s:text>';
	var INDUSTRIES = '<s:text name="web.label.list.sector.Industries"></s:text>';
	var COMPANIES = '<s:text name="web.label.list.sector.Companies"></s:text>';
	//-->
	var codeSelector = 0;
	$(document).ready(function(){
	    codeSelector = '<s:property value="sectorCodeSelected"/>';
		if(isNaN(codeSelector) || codeSelector == 'undefined')
			codeSelector = 0;
		if(codeSelector != 0){
			var selectedIndustry = new Industry();
            selectedIndustry.sectorCode = codeSelector;
            selectedIndustry.load();
		}
	});
	
</script>

<s:set var="btnClassification" value="true" scope="action"></s:set>
<s:set var="btnIndex" value="false" scope="action"></s:set>
<s:set var="btnOverview" value="true" scope="action"></s:set>

<tiles:insertDefinition name="Analysis.FundamentalityTmpl">
	<tiles:putAttribute name="MainContent">
		<div style="color: #f00; display: none;font-weight: bold" id="recordNotFound"><s:text name="commons.label.record.not.found"></s:text>!</div>
		
		<div class="box_tk_mck">
		    <s:text name="web.label.list.sector.EnterTheSymbol" />
		    				
			<input type="text" class="input145" id="searchSymbolId" name="SymbolCode" value="">
			<!--<span class="button_long">
			    <input type="button" id="viewIndustry" value="<s:text name="web.label.list.sector.ViewIndustry" />">                                            
            </span>
            -->
			<input type="button" class="iButton"
                        id="viewIndustry"
                        value='<s:text name="web.label.list.sector.ViewIndustry" />'>
		</div>
        
		<div class="back_slcp">
		    <%--
			<a href="#"><span class="icon_back left"></span><span
				class="left"> Quay lại</span>
			</a>
			<span class="right orange font"><s:text name="web.label.list.sector.Title2" /></span>
			--%>
			
		</div>

        <div class="clearfix"></div>
        
		<div id="navigator" style="display: none; margin-bottom: 5px">
			<b><s:text name="web.label.sector" />:&nbsp;</b>
			<a href="javascript:void(0);"><span id="navigator_sectorName"></span></a> &nbsp; -&gt;&nbsp;
			<b><s:text name="web.label.list.sector.Industries" />:&nbsp;</b>
			<span id="navigator_industryName"></span>
		</div>
		
		<p class="right" style="margin-bottom: 10px;"><s:text name="web.label.list.sector.Title2" /></p>
		<div class="clearfix"></div>
		
		<div class="content_phantichnganh padding-bottom10 listSectors" id="listOfSectors">
		    <table width="100%" cellspacing="0" cellpadding="3" style="border-left:1px solid #b0b0b0; border-right:1px solid #b0b0b0">
                <thead>
                    <tr class="rowfirst">
                        <td width="107"
                            rowspan="2" class="firstparent border_bottom_table">
                            <center>
                                <b><a href="<web:url value='/phan-tich-nganh/chi-so-nganh.shtml'/>"><s:text name="web.label.sector" />
                                </a></b>
                            </center>
                        </td>
                        <td width="82" style="border-left: medium none;"
                            colspan="2" class="parent colspantd">
                            <center>
                                <s:text
                                    name="web.label.list.sector.Valuation" />
                            </center>
                        </td>
                        <td width="202" colspan="3" class="parent colspantd">
                            <center>
                                <s:text
                                    name="web.label.list.sector.Scope" />
                            </center>
                        </td>
                        <td width="90" colspan="2" class="parent colspantd">
                            <center>
                                <s:text
                                    name="web.label.list.sector.Growth" />
                            </center>
                        </td>

                        <td width="123" colspan="3" class="parent colspantd">
                            <center>
                                <s:text
                                    name="web.label.list.sector.Profitability" />
                            </center>
                        </td>
                        <td width="145" colspan="3" class="parent colspantd">
                            <center>
                                <s:text
                                    name="web.label.list.sector.Ratio" />
                            </center>
                        </td>
                    </tr>
                    <tr class="secondrow border_bottom_table">
                        <td align="center" width="40">
                            <a href="javascript:void(0);">
                                <span class="sorting {sortField: 'PE'}">
                                    <s:text name="web.label.list.sector.P-E" />
                                </span>
                            </a>
                            
                        </td>
                        <td align="center" width="40"
                            style="border-left: 1px solid rgb(176, 176, 176); border-right: 1px solid rgb(176, 176, 176);">
                            <a href="javascript:void(0);">
                                <span class="sorting {sortField: 'PB'}">
                                    <s:text name="web.label.list.sector.P-B" />
                                </span>
                            </a>
                        </td>
                        <td align="center" width="60">
                            <a href="javascript:void(0);">
                                <span class="sorting {sortField: 'SCOPE_MAKET_CAP'}">
                                    <s:text name="web.label.list.sector.Maket-Cap" />
                                </span>
                            </a>
                        </td>
                        <td align="center" width="75"
                            style="border-left: 1px solid rgb(176, 176, 176);">
                            <a href="javascript:void(0);">
                                <span class="sorting {sortField: 'SCOPE_ASSET'}">
                                    <s:text name="web.label.list.sector.Assets" />
                                </span>
                            </a>
                        </td>
                        <td align="center" width="67"
                            style="border-left: 1px solid rgb(176, 176, 176); border-right: 1px solid rgb(176, 176, 176);">
                            <a href="javascript:void(0);">
                                <span class="sorting {sortField: 'SCOPE_EQUITY'}">
                                    <s:text name="web.label.list.sector.Equities" />
                                </span>
                            </a>
                        </td>
                        <td align="center" width="50">
                            <a href="javascript:void(0);">
                                <span class="sorting {sortField: 'GROWTH_ASSET'}">
                                    <s:text name="web.label.list.sector.Asset" />
                                </span>
                            </a>
                        </td>
                        <td align="center" width="40"
                            style="border-left: 1px solid rgb(176, 176, 176); border-right: 1px solid rgb(176, 176, 176);">
                            <a href="javascript:void(0);">
                                <span class="sorting {sortField: 'GROWTH_REVENUE'}">
                                    <s:text name="web.label.list.sector.Revenue" />
                                </span>
                            </a>
                        </td>
                        <td align="center" width="43">
                            <a href="javascript:void(0);">
                                <span class="sorting {sortField: 'ROA'}">
                                    <s:text name="web.label.list.sector.ROA" />
                                </span>
                            </a>
                        </td>
                        <td align="center" width="42"
                            style="border-left: 1px solid rgb(176, 176, 176);">
                            <a href="javascript:void(0);">
                                <span class="sorting {sortField: 'ROE'}">
                                    <s:text name="web.label.list.sector.ROE" />
                                </span>
                            </a>
                        </td>
                        <td align="center" width="38"
                            style="border-left: 1px solid rgb(176, 176, 176); border-right: 1px solid rgb(176, 176, 176);">
                            <a href="javascript:void(0);">
                                <span class="sorting {sortField: 'PROFIT_MARGIN'}">
                                    <s:text name="web.label.list.sector.Profit-margin" />
                                </span>
                            </a>
                        </td>
                        <td align="center" width="35">
                            <a href="javascript:void(0);">
                                <span class="sorting {sortField: 'DEBT_EQUITY'}">
                                    <s:text name="web.label.list.sector.Debt-Equity" />
                                </span>
                            </a>
                        </td>
                        <td align="center" width="40"
                            style="border-left: 1px solid rgb(176, 176, 176);">
                            <a href="javascript:void(0);">
                                <span class="sorting {sortField: 'CURRENT_RATIO'}">
                                    <s:text name="web.label.list.sector.Current-Ratio" />
                                </span>
                            </a>
                        </td>
                        <td align="center" width="70"
                            style="border-left: 1px solid rgb(176, 176, 176);">
                            <a href="javascript:void(0);">
                                <span class="sorting {sortField: 'EBITDA'}">
                                    <s:text name="web.label.list.sector.EBITDA" />
                                </span>
                            </a>
                        </td>
                    </tr>
                </thead>
                <tbody>

                </tbody>		    
		    </table>
		</div>
												
	</tiles:putAttribute>
</tiles:insertDefinition>