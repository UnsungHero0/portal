<div class="mfiInputParams hsInputParams">
	<p>
		<span class="name">Period</span>
		<input type="text" id="period" value="">
	</p>
	<div class="btns">
		<a onclick="$('.hsInputParams .introduce').show();">What is MFI ?</a>
		<input type="button" class="iButton" value="Cancel"
			onclick="closeInputParamsDialog();" />
		<input type="button" class="iButton" value="Draw"
			onclick="closeInputParamsDialog();drawMFI();" />
	</div>
	<div class="introduce">
		You have the option to change the period of the Money Flow Index (MFI)
		above. The default period is set to 14. Measuring the strength of
		money flowing in and out of a security, the Money Flow Index (MFI) is
		a momentum indicator similar in function to the Relative Strength
		Index (RSI). The primary difference being that while the RSI only
		incorporates prices, the MFI accounts for volume."Look for divergence
		between the MFI and the price action. If the price trends higher and
		the MFI trends lower (or vice versa), a reversal may be imminent. Look
		for market tops to occur when the MFI is above 80. Look for market
		bottoms to occur when the MFI is below 20." - "Technical Analysis from
		A to Z" by Stephen Aechlis
	</div>
</div>

<script type="text/javascript">
$(function() {
    $('.mfiInputParams #period').val(MFI_INPUTS[0]);
});
</script>