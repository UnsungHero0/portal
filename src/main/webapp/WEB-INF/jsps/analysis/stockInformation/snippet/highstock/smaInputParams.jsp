<div class="smaInputParams hsInputParams">
	<p>
		<span class="name">Period 1</span>
		<input type="text" id="period1" value="">
	</p>
	<p>
		<span class="name">Period 2</span>
		<input type="text" id="period2" value="">
		(Optional)
	</p>
	<p>
		<span class="name">Period 3</span>
		<input type="text" id="period3" value="">
		(Optional)
	</p>
	<div class="btns">
		<a onclick="$('.hsInputParams .introduce').show();">What is SMA ?</a>
		<input type="button" class="iButton" value="Cancel"
			onclick="closeInputParamsDialog();" />
		<input type="button" class="iButton" value="Draw"
			onclick="closeInputParamsDialog();drawSMA();" />
	</div>
	<div class="introduce">
		You have the option to chart up to 3 Simple Moving Averages (SMAs) at
		the same time. The 3 input boxes above represent the periods of the
		Simple Moving Averages you wish to chart.If you are charting on
		minute, daily or weekly periods, the SMA periods you indicate above
		will be for minutes, days and weeks, respectively. An SMA is
		calculated by adding the security's prices for the most recent N time
		periods and then dividing by N.It smooths out a data series, making it
		easier to spot trends.
	</div>
</div>

<script type="text/javascript">
$(function() {
    $('.smaInputParams #period1').val(SMA_INPUTS[0]);
    $('.smaInputParams #period2').val(SMA_INPUTS[1]);
    $('.smaInputParams #period3').val(SMA_INPUTS[2]);
});
</script>