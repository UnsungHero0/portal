<div class="wrInputParams hsInputParams">
	<p>
		<span class="name">W%R Period</span>
		<input type="text" id="period" value="14">
	</p>
	<div class="btns">
		<a onclick="$('.hsInputParams .introduce').show();">What is WR ?</a>
		<input type="button" class="iButton" value="Cancel"
			onclick="closeInputParamsDialog();" />
		<input type="button" class="iButton" value="Draw"
			onclick="closeInputParamsDialog();drawWR();" />
	</div>
	<div class="introduce">
		You have the option to change the period of the Williams %R above. The
		default period is set to 14. The Williams %R indicator seeks to
		measure overbought/oversold levels."Readings in the range of 80 to
		100% indicate that the security is oversold while readings in the 0 to
		20% range suggest that it is overbought."- "Technical Analysis from A
		to Z" by Stephen Aechlis
	</div>
</div>

<script type="text/javascript">
$(function() {
    $('.wrInputParams #period').val(WR_INPUTS[0]);
});
</script>