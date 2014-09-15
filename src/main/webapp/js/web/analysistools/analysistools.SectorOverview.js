$().ready(function() {
	
	var companyHandler = function(obj/*div content*/, model/*json object returned from server*/){
		var str = '';
		str	+= '<table>' +
					'<tr>' +
						'<td>' +
							'<img class="node-icon" src="' + WEB_CONTEXT + '/images/s.gif">' +
						'</td>' +
						'<td>' +
							'<span class="bluetext"><a href="' + URL_COMPANY_SNAPSHOT + '?viewSymbol=' + model.topCompany.secCode + '">' + model.topCompany.secCode + '</a></span>' +
						'</td>' +
					'</tr>' +
				'</table>';
		obj.html(str);
	};
	
	var industryHandler = function(obj/*div content*/, model/*json object returned from server*/){
		var str = '';
		str	+= '<table>' +
					'<tr>' +
						'<td>' +
							'<img class="node-icon" src="' + WEB_CONTEXT + '/images/s.gif">' +
						'</td>' +
						'<td>' + 
							'<span class="bluetext"><a href="' + URL_INDUSTRY_DETAILS + '?sectorCode=' + model.topIndustry.sectorCode + '&industryCode=' + model.topIndustry.industryCode + '">' + model.topIndustry.industryName + '</a></span>' +
						'</td>' +
					'</tr>' +
				'</table>';
		obj.html(str);
	};
	
	$('.topIndustry').toggle(
		function(){
			var sectorCode = $(this).metadata().sectorCode;
			showData(URL_TOP_INDUSTRY_AJAX, [{name : 'sectorCode', value : sectorCode}], $(this).children('div'), industryHandler);
			
			$(this).children('img:first').addClass('node-expanded');
			$(this).children('div').show();
		},
		function(event){
			var $target=$(event.target);
			if($target.is('a')){
				window.location = event.target.href;
			} else {
				$(this).children('img:first').removeClass('node-expanded');
				$(this).children('div').hide();
			}
		}
	);
	
	$('.topCompany').toggle(
			function(event){
				var industryCode = $(this).metadata().industryCode;
				showData(URL_TOP_COMPANY_AJAX, [{name : 'industryCode', value : industryCode}], $(this).children('div'), companyHandler);
				
				$(this).children('img:first').addClass('node-expanded');
				$(this).children('div').show();
			},
			function(event){
				var $target=$(event.target);
				if($target.is('a')){
					window.location = event.target.href;
				} else {
					$(this).children('img:first').removeClass('node-expanded');
					$(this).children('div').hide();
				}
			}
		);
	
	/*$("form[name='CompanyDetails']").bind("submit", function() {
		$('#symbol').val($('#symbol').val().toUpperCase());
	});*/
	
	function showData(url/*URL*/, params /*parameters*/, obj/*div content*/, handler/*function*/) {
		$.ajax({
	   		   type: "POST",
	   		   url: url,
	   		   data: params,
	   		   dataType: "json",
	   		   success: function(data) {
					if(data.error.actionErrors.length == 0) {
						handler(obj, data.model);
					} else {
						alert(data.error.actionErrors[0]);
					}
	   			},
	   		   beforeSend: function() {
	   				obj.empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />');
	   			}
		});
	}
	
});