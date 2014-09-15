<div class="ssInputParams hsInputParams">
	<p>
		<span class="name">%K Period</span>
		<input type="text" id="kPeriod" value="">
	</p>
	<p>
		<span class="name">%D Period</span>
		<input type="text" id="dPeriod" value="3">
	</p>
	<div class="btns">
		<a onclick="$('.hsInputParams .introduce').show();">What is SS ?</a>
		<input type="button" class="iButton" value="Cancel"
			onclick="closeInputParamsDialog();" />
		<input type="button" class="iButton" value="Draw"
			onclick="closeInputParamsDialog();drawSS();" />
	</div>
	<div class="introduce">
		You have the option to change the %K and %D periods of the Slow
		Stochastic above. The default %K and %D periods are set to 15 and 5,
		respectively.The MACD is most effective in wide-swinging trading
		markets. There are three popular ways to interpret the Slow
		Stochastic: Buy when the Oscillator (either %K or %D) falls below a
		specific level (e.g. 20) and then rises above that level. Sell when
		the Oscillator rises above a specific level (e.g. 80) and then falls
		below that level. Buy when the %K line rises above the %D line and
		sell when the %K line falls below the %D line. Look for divergences.
		For example, where prices are making a series of new highs and the
		Slow Stochastic Oscillator is failing to surpass its previous highs.-
		"Technical Analysis from A to Z" by Stephen Aechlis
	</div>
</div>

<script type="text/javascript">
$(function() {
    $('.ssInputParams #kPeriod').val(SS_INPUTS[0]);
    $('.ssInputParams #dPeriod').val(SS_INPUTS[1]);
});
</script>