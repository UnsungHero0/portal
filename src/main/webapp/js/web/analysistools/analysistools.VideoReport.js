
$().ready(function() {
	
	//+++Date
    $('#videoReport_FromDate').datepicker({

        changeMonth: true,

        changeYear: true,

        onSelect: function (){return false;},

        onClose: function (){return false;}
    });
	
    $('#videoReport_ToDate').datepicker({

        changeMonth: true,

        changeYear: true,

        onSelect: function (){return false;},

        onClose: function (){return false;}
    });
    
    //+++ Search news with date
    
});


/*var obj = new ATClazzReport();

$().ready(function() {
	
		 date picker		$('#date-pick').datepicker();

		 add event handler for search button		$('#search').click(
				function() {
					loadTabVideo('DailyReport');
				}
		);
		
		loadTabVideo('DailyReport');
});

function loadTabVideo(newsType) {
	$.ajax( {
		type : "POST",
		url : URL_MARKET_DAILY_NEWS_AJAX,
		data : 'newsType=' + newsType + '&date=' + $('#date-pick').val(),
		dataType : "json",
		success : function(data) {				
				obj.populateVideoNews(data, newsType);
		},
		beforeSend : function() {
			var id = 'dailyVideo';
			if (newsType == 'WeeklyReport') {
				id = 'weeklyVideo';
			} else if (newsType == 'MonthlyReport') {
				id = 'monthlyVideo';
			}				
			obj.loadImageVideoNews(id);
		}
	});
}

function changePage(containerId, page) {
	var newsType = 'DailyReport';
	var id = 'dailyVideo';
	if (containerId == obj.getOption().weeklyVideoNavigator) {
		newsType = 'WeeklyReport';
		id = 'weeklyVideo';
	} else if (containerId == obj.getOption().monthlyVideoNavigator) {
		newsType = 'MonthlyReport';
		id = 'monthlyVideo';
	}		
	$.ajax( {
		type : "POST",
		url : URL_MARKET_DAILY_NEWS_AJAX,
		data : 'newsType=' + newsType + '&date=' + $('#date-pick').val() + '&pagingInfo.indexPage=' + page,
		dataType : "json",
		success : function(data) {
			obj.populateVideoNews(data, newsType);
		},
		beforeSend : obj.loadImageVideoNews(id)
	});	
}*/