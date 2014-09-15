var obj = new AskExpert();

$().ready(function() {
	displayData(1);
	
	$('#send').click(function(){
		var params = [
			          {name : 'question.questionContent', value : $('#questionContent').val()},
			          {name : 'question.topicId', value : topicId},
			         ];
		$.ajax({
	   		   type: "POST",
	   		   url: URL_SAVE_QUESTION,
	   		   data: params,
	   		   dataType: "json",
	   		   success: function(data) {
					if(data.error.actionErrors.length == 0) {
						alert('Question saved!');
					} else {
						alert(data.error.actionErrors[0]);
					}
	   			}
		});
	});
	
	$('#close').click(function(){
		$('div.answer').hide();
	});
});

function changePage(containerId, page) {
	displayData(page);
}

function displayData(page) {
	var params = [
		          {name : 'topicId', value : topicId},
		          {name : 'pagingInfo.indexPage', value : page},
		          {name : 'pagingInfo.offset', value : 5}
		         ];
	$.ajax({
   		   type: "POST",
   		   url: URL_SHOW_QUESTION,
   		   data: params,
   		   dataType: "json",
   		   success: function(data) {
				if(data.error.actionErrors.length == 0) {
		   			obj.populateData(data, {div : 'ul.questions'});
				} else {
					alert(data.error.actionErrors[0]);
				}
   			},
   		   beforeSend: obj.loadImage('ul.questions')
	});
}
