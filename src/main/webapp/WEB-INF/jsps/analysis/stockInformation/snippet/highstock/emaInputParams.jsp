<div class="emaInputParams hsInputParams">
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
		<a onclick="$('.hsInputParams .introduce').show();">What is EMA ?</a>
		<input type="button" class="iButton" value="Cancel"
			onclick="closeInputParamsDialog();" />
		<input type="button" class="iButton" value="Draw"
			onclick="closeInputParamsDialog();drawEMA();" />
	</div>
	<div class="introduce">
		You have the option to chart up to 3 Exponential Moving Averages
		(EMAs) at the same time. The 3 input boxes above represent the periods
		of the Exponential Moving Averages you wish to chart. If you are
		charting on minute, daily or weekly periods, the EMA periods you
		indicate above will be for minutes, days and weeks, respectively. An
		EMA differs slightly from a Simple Moving Average (SMA) in that it
		gives extra weight to more recent price data. This allows investors to
		track and respond quickly to recent price trends that might take more
		time to appear in an SMA. The formula for an EMA is: EMA = price today
		* K + EMA yest * (1-K) where K = 2 / (N+1). Like an SMA, it smooths
		out a data series, making it easier to spot trends.
	</div>
</div>

<script type="text/javascript">
$(function() {
    $('.emaInputParams #period1').val(EMA_INPUTS[0]);
    $('.emaInputParams #period2').val(EMA_INPUTS[1]);
    $('.emaInputParams #period3').val(EMA_INPUTS[2]);
});
</script>