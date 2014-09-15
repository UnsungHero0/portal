<div class="macdInputParams hsInputParams">
	<p>
		<span class="name">Slow Period</span>
		<input type="text" id="slowPeriod" value="">
	</p>
	<p>
		<span class="name">Fast Period</span>
		<input type="text" id="fastPeriod" value="">
	</p>
	<p>
		<span class="name">Signal Period</span>
		<input type="text" id="signalPeriod" value="">
	</p>
	<div class="btns">
		<a onclick="$('.hsInputParams .introduce').show();">What is MACD ?</a>
		<input type="button" class="iButton" value="Cancel"
			onclick="closeInputParamsDialog();" />
		<input type="button" class="iButton" value="Draw"
			onclick="closeInputParamsDialog();drawMACD();" />
	</div>
	<div class="introduce">
		You have the option to change the slow,fast and signal periods used
		tocalculate the MACD above. The default slow, fast and signal periods
		are set to 26, 12 and 9, respectively.Moving Average Convergence
		Divergence (MACD) is a trend-following momentum indicator that shows
		the relationship between two moving averages of prices.The default
		MACD is represented as the difference between a 26-day and 12-day EMA
		of the price. A 9-day EMA of the MACD, referred to as the signal (or
		trigger) line, is plotted on top of the MACD to indicate buy/sell
		opportunities.Divergence, the difference between the MACD and the
		signal, is also plotted as a histogram.The MACD is most effective in
		wide-swinging trading markets. There are three standard ways to
		interprete the MACD: * Crossovers The basic MACD trading rule is to
		sell when the MACD falls below its signal line. Similarly, a buy
		signal occurs when the MACD rises above its signal line. It's also
		popular to buy/sell when the MACD goes above/below zero. *
		Overbought/Oversold Conditions The MACD is also useful as an
		overbought/oversold indicator. When the shorter moving average pulls
		away dramatically from the longer moving average (i.e. the MACD
		rises), it's likely that the security price is overextending and will
		soon return to more realistic levels. MACD overbought and oversold
		conditions exist vary from security to security. * Divergences An
		indication that an end to the current trend may be near occurs when
		the MACD diverges from the security. A bearish divergence occurs when
		the MACD is making new lows while prices fail to reach new lows. A
		bullish divergence occurs when the MACD is making new highs while
		prices fail to reach new highs. Both of these divergences are most
		significant when they occur at relatively overbought/oversold levels.-
		"Technical Analysis from A to Z" by Stephen Aechlis
	</div>
</div>

<script type="text/javascript">
$(function() {
    $('.macdInputParams #slowPeriod').val(MACD_INPUTS[0]);
    $('.macdInputParams #fastPeriod').val(MACD_INPUTS[1]);
    $('.macdInputParams #signalPeriod').val(MACD_INPUTS[2]);
});
</script>