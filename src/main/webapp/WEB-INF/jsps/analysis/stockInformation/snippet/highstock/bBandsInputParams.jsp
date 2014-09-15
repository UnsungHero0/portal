<div class="bBandsInputParams hsInputParams">
	<p>
		<span class="name">Period</span>
		<input type="text" id="period" value="">
	</p>
	<p>
		<span class="name">Deviation</span>
		<input type="text" id="deviation" value="">
	</p>
	<div class="btns">
		<a onclick="$('.hsInputParams .introduce').show();">What is BBands
			?</a>
		<input type="button" class="iButton" value="Cancel"
			onclick="closeInputParamsDialog();" />
		<input type="button" class="iButton" value="Draw"
			onclick="closeInputParamsDialog();drawBBands();" />
	</div>
	<div class="introduce">
		Bollinger Bands are curves drawn in and around the price structure
		that define high and low on a relative basis. The base of the bands is
		a simple moving average. A measure of volatility, standard deviation,
		is used to set the width of the bands making them fully adaptive to
		changing market conditions. The defaults are bands spread above and
		below a 20-day simple moving average by two standard deviations.
	</div>
</div>

<script type="text/javascript">
$(function() {
    $('.bBandsInputParams #period').val(BBANDS_INPUTS[0]);
    $('.bBandsInputParams #deviation').val(BBANDS_INPUTS[1]);
});
</script>