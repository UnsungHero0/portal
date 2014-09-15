$(document).ready(function() {
	//chart
	$.ajax({
	    type: "POST",
	    url: $.web_resource.getContext() + "ajax/analysis/SectorIndustryIndexChart.shtml",
	    data: $('#industryDetail-industryCode').val(),
	    dataType: "json",
	    success: function(data){
			var idxs = data.model.lstIndexCalc;
			var chartData = [];
			if(idxs){
				$.each(idxs, function(i, idx){
					var temp = [idx.transDateInMiliseconds , idx.numericValue];
					chartData.push(temp);
				});
			}
		
			chart = new Highcharts.Chart({
	            chart: {
	                renderTo: 'divSectorIndustryChart',
	                type: 'line',
	                height: 400,
	                backgroundColor: '#FAFAFA',
	            },
	            credits: {
	                enabled: false
	            },
	            title: {
	                text: ''
	            },
	            plotOptions: {
            		series: {
                		marker: {
                    		enabled: false
                		}
            		}
        		},
	            xAxis: {
	                type: 'datetime',
            	},
            	yAxis: {
	                title: {
	                    text: null
	                }
            	},
	            series: [{
                	name: $('#industryDetail-industryName').val(),
                	data: chartData
            	}],
	   		});
		}
	});
});