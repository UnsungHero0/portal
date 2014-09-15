$(document).ready(function() {
	$('.flash_chart_link').live('click', function(){	      
       var quickSearchSymbol = $(this).attr('data_symbol');
       var url = $(this).attr('href');
        try {
            var formFields = {
                "symbol" : quickSearchSymbol
            };
            var options = {
                action : URL_SYMBOL_QUICK_SEARCH,
                callbackExecuteFail : function(error) {
                    $.log("doQuickSearchSymbol() - " + error);
                },
                callbackPostSubmit : function(responseText, statusText) {
                    window.document.location.href=url;
                }
            }
            $.web_formAways.ajaxNav(formFields, options);
        } catch(ex){
            alert(ex);
        }
       return false;
    });
			
	//chart
	$.ajax({
	    type: "POST",
	    url: $.web_resource.getContext() + "ajax/analysis/power-rating-breakdown.shtml",
	    dataType: "json",
	    success: function(data){
			chart = new Highcharts.Chart({
	            chart: {
	                renderTo: 'PowerRatingChart',
	                plotBackgroundColor: '#fafafa',
	                plotBorderWidth: null,
	                plotShadow: false,
	                height:220,
	                backgroundColor: '#fafafa',
	            },
	            credits: {
	                enabled: false
	            },
	            title: {
	                text: ''
	            },
	            tooltip: {
	                pointFormat: '{series.name}: <b>{point.percentage}%</b>',
	                percentageDecimals: 2
	            },
	            plotOptions: {
	                pie: {
	                    allowPointSelect: true,
	                    cursor: 'pointer',
	                    dataLabels: {
	                        enabled: true,
	                        color: '#333333',
	                        connectorColor: '#F39200',
	                    },
	                    //showInLegend: true
	                }
	            },
	            series: [{
	                type: 'pie',
	                name: '',
	                data: [
	                    [$('#pr-firstLevel-text').val(), data.model.firstLevel],
	                    [$('#pr-secondLevel-text').val(), data.model.secondLevel],
	                    [$('#pr-thirdLevel-text').val(), data.model.thirdLevel],
	                    [$('#pr-fourthLevel-text').val(), data.model.fourthLevel],
	                    [$('#pr-fifthLevel-text').val(), data.model.fifthLevel],
	                    [$('#pr-sixthLevel-text').val(), data.model.sixthLevel]
	                ]
	            }]
	   		});
		}
	});
});