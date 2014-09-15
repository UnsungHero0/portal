<div class="rsiInputParams hsInputParams">
	<p>
		<span class="name">Period</span>
		<input type="text" id="period" value="">
	</p>
	<div class="btns">
		<a onclick="$('.hsInputParams .introduce').show();">What is RSI ?</a>
		<input type="button" class="iButton" value="Cancel"
			onclick="closeInputParamsDialog();" />
		<input type="button" class="iButton" value="Draw"
			onclick="closeInputParamsDialog();drawRSI();" />
	</div>
	<div class="introduce">
		You have the option to change the period of the Relative Strength
		Index (RSI) above.The default period is set to 14. The Relative
		Strength Index (RSI) measures the price of a security against its past
		performance in order to determine its internal strength (in an attempt
		to quantify the security's price momentum).popular method of analyzing
		the RSI is to look for a divergence in which the security is making a
		new high, but the RSI is failing to surpass its previous high. This
		divergence is an indication of an impending reversal. When the RSI
		then turns down and falls below its most recent trough, it is said to
		have completed a 'failure swing.' The failure swing is considered a
		confirmation of the impending reversal.- "Technical Analysis from A to
		Z" by Stephen Aechlis
	</div>
</div>

<script type="text/javascript">
$(function() {
    $('.rsiInputParams #period').val(RSI_INPUTS[0]);
});
</script>