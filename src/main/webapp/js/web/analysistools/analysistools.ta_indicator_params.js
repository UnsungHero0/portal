/**
* paramenters:
<online>
	<params>
		<param name="userAction"<![CDATA[line]]></param>
		<param name="lineType"><![CDATA[line]]></param>
		<param name="chartScale"><![CDATA[linear]]></param>
		<param name="priorDay"><![CDATA[-5]]></param>
		<param name="priorMonth"><![CDATA[-5]]></param>
		<param name="priorYear"><![CDATA[-5]]></param>
		<param name="indicator"><![CDATA[MA|VOLUME|]]></param>
		<param name="competitor"><![CDATA[{list of symbols}]]></param>
		<param name="selectedIndicator"><![CDATA[MA]]></param>		
		<param name="selectedCompetitor"><![CDATA[{list of symbols}]]></param>
	</params>
</online>
*
* result:
<online>
	<status>OK|ERROR</status>
	<error-message></error-message>
	<charts>
		<chart-uri>{image url}</chart-uri>
		<line-type></line-type>
		<chart-scale></chart-scale>
		<list-indicator>{indecator1|indicator2|indicator3}</list-indicator>
		<list-competitor>{competitor1|competitor2|competitor3}</list-competitor>
	</charts>
</online>
*
*/
function doDrawChart(imgChartId, listParamNames, listParamValues, userAction) {	
	var params = [];
	params.push({name : 'userAction', value: userAction});	
	params.push({name : 'priorDay', value: priorDay});	
	params.push({name : 'priorMonth', value: priorMonth});	
	params.push({name : 'priorYear', value: priorYear});	
	if(listParamNames != null && listParamValues != null) {
		var i, size = listParamNames.length;
		for(i=0; i<size; i++) {
			params.push({name : "'"+listParamNames[i]+"'", value: listParamValues[i]});	
		}
	}	
	
	$.ajax({
		   type: "POST",
		   url: URL_FLASH_CHART_AJAX,
		   data: params,
		   dataType: "json",
		   success: function(data) {
				var model = data.model;
				alert(model);
				var chartId = model.taChart.cacheKey;
				alert(chartId);
				var strListIndicator = model.strListIndicator;
				var strListCompetitor = model.strListCompetitor;
				var strLineType = model.chartInfo.lineType;
				var strChartScale = model.chartInfo.chartScale;
				var chartUri = model.chartURL + "?cacheKey=" + chartId;
				opener.doRefreshChartSelectIndicator(chartUri + defaultImg, strListIndicator, strListCompetitor, strLineType, strChartScale);				
				
		   },
		   beforeSend: function(){
		   }
	});
	setTimeout("window.close()", 100);
}
