<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<div id="page_searchSymbol" style="padding-top: 50px;">
	<div class="titleBar">
		<span id="title" style="margin-left: 25px;"><s:text name="analysis.stockInfo.company.searchSymbol.br">Chọn mã chứng khoán</s:text>
		</span>
	</div>

	<input id="forwardUrl" type="hidden"
		value="<%=session.getAttribute("LISTED_MARKET_FROM")%>" />
	<form id="fSearchSymbol" name="fSearchSymbol"
		action="<web:url value="/ajax/common/SymbolProcessing_SearchSymbol.shtml"/>"
		method="post">
		<input type="hidden" id="fSearchSymbol_pagingInfo_indexPage"
			name="pagingInfo.indexPage" value="1" />
		<input type="hidden" id="symbolSuggestionId" name="symbolSuggestion"
			value="" />
		<div class="table_Market clearfix">
			<table cellpadding="0" cellspacing="6" border="0" width="100%"
				style="border: 1px solid #B0B0B0; border-radius: 5px 5px 0 0;">
				<tr>
					<td width="4">
					</td>
					<td width="80">
						<s:text
							name="web.label.SymbolProcessingAction.form.searchresult.symbol">Mã CK</s:text>
					</td>
					<td width="150">
						<s:text
							name="web.label.SymbolProcessingAction.form.symbolsearch.companyName">Company Name</s:text>
					</td>
					<td width="150">
						<s:text
							name="web.label.SymbolProcessingAction.form.symbolsearch.sector">Sector</s:text>
					</td>
					<td width="200">
						<s:text
							name="web.label.SymbolProcessingAction.form.symbolsearch.stockExchange">Stock Exchange</s:text>
					</td>
					<td width="100">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>

					</td>
					<td>
						<input type="text" id="fSearchSymbol_symbol"
							name="searchSymbol.symbol" value="" size="8" />
					</td>
					<td>
						<input type="text" id="fSearchSymbol_companyName"
							name="searchSymbol.companyName" size="20">
					</td>
					<td>
						<select id="fSearchSymbol_sectorCode"
							name="searchSymbol.sectorCode">
							<option value="%">
								---
							</option>
							<s:iterator value="listSectorCode">
								<option value="${id.sectorCode}">
									${id.sectorName}
								</option>
							</s:iterator>
						</select>
					</td>
					<td>
						<select id="fSearchSymbol_exchangeCode"
							name="searchSymbol.exchangeCode">
							<option value="%">
								---
							</option>
							<s:iterator value="listIfoExchange">
								<option value="${exchangeCode}">
									${exchangeName}
								</option>
							</s:iterator>
						</select>
					</td>
					<td>
						<input type="button" class="iButton"
							id="fSearchSymbol_btnSymbolSearch"
							value='<s:text name="button.search"/>'>
					</td>
					<td>
					</td>
				</tr>
			</table>
			<div id="fSearchSymbol_result">
				<table cellpadding="0" cellspacing="0" border="0" width="100%"
					style="border: 1px solid #B0B0B0; border-top: 0px; border-radius: 0 0 5px 5px;">
					<colgroup>
						<col width="7%" />
						<col width="25%" />
						<col width="35%" />
						<col width="21%" />
						<col width="15%" />
					</colgroup>
					<thead>
						<tr>
							<td width="5%" align="center" class="bordertd_">
								<b><s:text
										name="web.label.SymbolProcessingAction.form.searchresult.symbol">Symbol</s:text>
								</b>
							</td>
							<td width="15%" align="center" class="bordertd_">
								<b><s:text
										name="web.label.SymbolProcessingAction.form.searchresult.companyName">Company Name</s:text>
								</b>
							</td>
							<td width="35%" align="center" class="bordertd_">
								<b><s:text
										name="web.label.SymbolProcessingAction.form.searchresult.companyFullName">Company Full Name</s:text>
								</b>
							</td>
							<td width="30" align="center" class="bordertd_">
								<b><s:text
										name="web.label.SymbolProcessingAction.form.searchresult.sector">Sector</s:text>
								</b>
							</td>
							<td width="15%" class="col_end_" align="center">
								<b><s:text
										name="web.label.SymbolProcessingAction.form.searchresult.stockExchange">Stock Exchange</s:text>
								</b>
							</td>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
		<div id="fSearchSymbol_paging" style="margin-top: 10px;"></div>
	</form>
</div>

