var obj = new ATClazzReport();
var opts = obj.getOption();

$().ready(function() {

		if (firstVideoURL != '' && isOnTradingTime == false) {
			makePlayer(firstVideoURL);
		}

		// date picker
		$('#date-pick').datepicker();

		// add event handler for video partial
		$('.section').click(function() {
			var desc = $(this).metadata().desc;
			$('#videoDesc').html(desc);
			var videoURL = encodeURIComponent($(this).metadata().videoURL);
			attachmentId = $(this).metadata().id;
			makePlayer(videoURL);
		});

		// add event handler for search button
		$('#search').click(
				function() {
					$.ajax( {
						type : "POST",
						url : URL_MARKET_DAILY_NEWS_AJAX,
						data : 'newsType=' + $('#newsType').val() + '&date='
								+ $('#date-pick').val(),
						dataType : "json",
						success : function(data) {
							if ($('#newsType').val() != 'DailyReport') {// that's bad :(
								obj.populateMarketDailyNews(data, $('#newsType').val(), true);
							} else {
								obj.populateMarketDailyNews(data, $('#newsType').val());
							}
							$("#title").html(
									$('#newsType option:selected').text());
							
							$('.current').removeClass();
							$('#' + $('#newsType option:selected').val()).attr('class', 'current');
						},
						beforeSend : obj.loadImageMarketDailyNews
					});
				});

		$.ajax( {
			type : "POST",
			url : URL_MARKET_DAILY_NEWS_AJAX,
			data : 'newsType=' + $('#newsType').val() + '&date='
					+ $('#date-pick').val(),
			dataType : "json",
			success : function(data) {
				if ($('#newsType').val() != 'DailyReport') {// that's bad :(
					obj.populateMarketDailyNews(data, $('#newsType').val(), true);
				} else {
					obj.populateMarketDailyNews(data, $('#newsType').val());
				}
			},
			beforeSend : obj.loadImageMarketDailyNews
		});

		$.ajax( {
			type : "POST",
			url : URL_LATEST_REPORT_AJAX,
			dataType : "json",
			success : function(data) {
				obj.populateLatestReport(data);
			},
			beforeSend : obj.loadImageLatestReport
		});

	});

var player;
function playerReady(obj) {
	player = document.getElementById(obj['id']);
	player.addModelListener("STATE", "stateListener");
};

function stateListener(obj) {
	oldState = obj.oldstate;
	currentState = obj.newstate;
	if (currentState == "PLAYING") {
		updateHit(attachmentId);
	}
}

function makePlayer(videoURL) {
	jwplayer = new SWFObject("../../../flash/player.swf",
			"FLVVideoPlayer", "300", "200", "9", "#FFFFFF");
	jwplayer.addParam("allowfullscreen", "true");
	jwplayer.addParam("allowscriptaccess", "always");
	jwplayer.addParam("flashvars", "file=" + videoURL
			+ "&type=video&image=../../../flash/video_bantin.jpg");
	jwplayer.write("player");
}

function changePage(containerId, page) {
	if (containerId == obj.getOption().dailyNewsNavigator) {
		$.ajax( {
			type : "POST",
			url : URL_MARKET_DAILY_NEWS_AJAX,
			data : 'newsType=' + $('#newsType').val() + '&date='
					+ $('#date-pick').val() + '&pagingInfo.indexPage=' + page,
			dataType : "json",
			success : function(data) {
				obj.populateMarketDailyNews(data);
			},
			beforeSend : obj.loadImageMarketDailyNews
		});
	}

	if (containerId == obj.getOption().latestReportNavigator) {
		$.ajax( {
			type : "POST",
			url : URL_LATEST_REPORT_AJAX,
			data : "pagingInfo.indexPage=" + page,
			dataType : "json",
			success : function(data) {
				obj.populateLatestReport(data, true);
			},
			beforeSend : obj.loadImageLatestReport
		});
	}
}
