<div class="rocInputParams hsInputParams">
	<p>
		<span class="name">Period</span>
		<input type="text" id="period" value="">
	</p>
	<div class="btns">
		<a onclick="$('.hsInputParams .introduce').show();">What is ROC ?</a>
		<input type="button" class="iButton" value="Cancel"
			onclick="closeInputParamsDialog();" />
		<input type="button" class="iButton" value="Draw"
			onclick="closeInputParamsDialog();drawROC();" />
	</div>
	<div class="introduce">
		You have the option to change the period of the Rate of Change (ROC)
		above. The default period is set to 12. The Rate of Change (ROC)
		indicator displays the difference between the current price and the
		price x-time periods ago. The difference can be displayed in either
		points or as a percentage."The 12-day ROC is an excellent short to
		intermediate term overbought/oversold indicator.The higher the ROC,
		the more overbought the security; the lower the ROC, the more likely a
		rally.However, as with all overbought/over-sold indicators, it is
		prudent to wait for the market to begin to correct (i.e. turn up or
		down) before placing your trade. A market that appears overbought may
		remain overbought for some time. In fact, extremely
		overbought/oversold readings usually imply a continuation of the
		current trend.The 12-day ROC tends to be very cyclical, oscillating
		back and forth in a fairly regular cycle. Often, price changes can be
		anticipated by studying the previous cycles of the ROC and relating
		the previous cycles to the current market."- "Technical Analysis from
		A to Z" by Stephen Aechlis
	</div>
</div>

<script type="text/javascript">
$(function() {
    $('.rocInputParams #period').val(ROC_INPUTS[0]);
});
</script>