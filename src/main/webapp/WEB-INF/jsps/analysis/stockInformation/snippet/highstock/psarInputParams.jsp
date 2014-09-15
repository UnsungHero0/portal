<div class="psarInputParams hsInputParams">
	<p>
		<span class="name">Step Period</span>
		<input type="text" id="stepPeriod" value="">
	</p>
	<p>
		<span class="name">Max Step Period</span>
		<input type="text" id="maxStepPeriod" value="">
	</p>
	<div class="btns">
		<a onclick="$('.hsInputParams .introduce').show();">What is PSAR ?</a>
		<input type="button" class="iButton" value="Cancel"
			onclick="closeInputParamsDialog();" />
		<input type="button" class="iButton" value="Draw"
			onclick="closeInputParamsDialog();drawPSAR();" />
	</div>
	<div class="introduce">
		You have the option to change the step period and max step period used
		to calculate the Parabolic SAR above. The default step and max periods
		are set to 0.02 and 0.20, respectively.Also known as the"Parabolic
		Time/Price System,"this indicator is used to set trailing price stops
		and is usually referred to as the "SAR" (stop-and-reversal)."The
		Parabolic SAR provides excellent exit points.You should close long
		positions when the price falls below the SAR and close short positions
		when the price rises above the SAR.If you are long (i.e. the price is
		above the SAR), the SAR will move up every day, regardless of the
		direction the price is moving.The amount the SAR moves up depends on
		the amount that prices move. - "Technical Analysis from A to Z" by
		Stephen Aechlis
	</div>
</div>

<script type="text/javascript">
$(function() {
    $('.psarInputParams #stepPeriod').val(PSAR_INPUTS[0]);
    $('.psarInputParams #maxStepPeriod').val(PSAR_INPUTS[1]);
});
</script>