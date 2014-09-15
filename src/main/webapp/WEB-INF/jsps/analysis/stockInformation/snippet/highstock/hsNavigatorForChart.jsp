<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<div class="hsNavigator">
	<input type="button" class="hsButton" id="clearAllBtn" value="Reset"
		style="float: left; margin-right: 5px;" />
	<!-- indicators -->
	<div class="indicators">
		<input type="button" class="hsButton" id="openIndicatorList"
			value="Technical Indicators" />
		<ul class="indicatorList">
			<li id="sma" onclick="openSMAInputParams();">
				Simple Moving Average (SMA)
			</li>
			<li id="ema" onclick="openEMAInputParams();">
				Exponential Moving Average (EMA)
			</li>
			<li id="bBands" onclick="openBBandsInputParams();">
				Bollinger Bands (BBands)
			</li>
			<li id="mfi" onclick="openMFIInputParams();">
				Money Flow Index (MFI)
			</li>
			<li id="macd" onclick="openMACDInputParams();">
				MACD
			</li>
			<li id="psar" onclick="openPSARInputParams();">
				Parabolic SAR (PSAR)
			</li>
			<li id="roc" onclick="openROCInputParams();">
				Rate of Change (ROC)
			</li>
			<li id="rsi" onclick="openRSIInputParams();">
				Relative Strength Index (RSI)
			</li>
			<li id="ss" onclick="openSSInputParams();">
				Slow Stochastic (SS)
			</li>
			<li id="fs" onclick="openFSInputParams();">
				Fast Stochastic (FS)
			</li>
			<li id="vma" onclick="openVMAInputParams();">
				Volume + MA
			</li>
			<li id="wr" onclick="openWRInputParams();">
				Williams %R
			</li>
		</ul>
	</div>

	<!-- chart type -->
	<div class="chartType">
		<input type="button" class="hsButton" id="openChartType"
			value="Chart Type" />
		<ul class="chartTypeList">
			<li id="line" ref="line" onclick="changeChartType($(this));">
				Line
			</li>
			<li id="spline" ref="spline" onclick="changeChartType($(this));">
				SP Line
			</li>
			<li id="candlestick" ref="candlestick"
				onclick="changeChartType($(this));">
				Candlestick
			</li>
			<li id="ohlc" ref="ohlc" onclick="changeChartType($(this));">
				OHLC
			</li>
			<li id="area" ref="area" onclick="changeChartType($(this));">
				Mountain
			</li>
			<li id="areaspline" ref="areaspline"
				onclick="changeChartType($(this));">
				SP Mountain
			</li>
		</ul>
	</div>

	<!-- Compare -->
	<div class="hsCompare">
		<input type="button" class="hsButton" id="openCompare" value="Compare" />
		<div class="processCompare">
			<p style='margin-bottom: 10px;'>
				Compare
				<b><span id="hs-ComparingSymbol"></span></b> with:
			</p>
			<input type="text" id="symbolToCompare" value="" size="10">
			<input style="margin: 0 5px 0 10px;" type="button" class="iButton"
				value="Go" onclick="prepareDrawCompare();" />
			<input style="" type="button" class="iButton" value="Cancel"
				onclick="closeProcessCompare();" />
		</div>
	</div>

	<!-- Tools -->
	<div class="hsTools">
		<input type="button" class="hsButton" id="openTools" value="Tools" />
		<ul class="toolList">
			<li id="dividends" onclick="toogleDividends();">
				Dividends
			</li>
		</ul>
	</div>
    
	<!-- append which are displaying -->
	<ul class="hsDisplayList">
	</ul>

	<!-- input fields dialog -->
	<div class="dialogInputParams" title="">
		<img src="<web:url value='/images/ajax-loader.gif'/>" />
	</div>
</div>
<div class="clear"></div>