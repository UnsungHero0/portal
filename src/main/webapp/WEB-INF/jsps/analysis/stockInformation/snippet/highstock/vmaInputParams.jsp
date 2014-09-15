<div class="vmaInputParams hsInputParams">
	<p>
		<span class="name">MA Period</span>
		<input type="text" id="period" value="">
	</p>
	<div class="btns">
		<a onclick="$('.hsInputParams .introduce').show();">What is VMA ?</a>
		<input type="button" class="iButton" value="Cancel"
			onclick="closeInputParamsDialog();" />
		<input type="button" class="iButton" value="Draw"
			onclick="closeInputParamsDialog();drawVMA();" />
	</div>
	<div class="introduce">
		You have the option to change the period of the Volume + MA above. The
		default period is set to 13. The Volume + MA represents the number of
		shares transacted every day. In addition, a 13-day volume Exponential
		Moving Average (EMA) overlay is used to provide additional information
		on the volume trend.
	</div>
</div>

<script type="text/javascript">
$(function() {
    $('.vmaInputParams #period').val(VMA_INPUTS[0]);
});
</script>