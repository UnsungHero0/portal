var clickedObj;

$(document).ready(function() {
	clickedObj = $('.boardOfExperts #b1');
	$('.boardOfExperts #b1').addClass('active');
	
	$('.boardOfExperts #b1').click(function(){
		if(clickedObj != null){
			clickedObj.removeClass('active');			
		}
		clickedObj = $(this);
		$(this).addClass('active');

		hideAll();
		$('.boardOfExpertsContent #b1').show();
		return false;
	});
	
	$('.boardOfExperts #b2').click(function(){
		if(clickedObj != null){
			clickedObj.removeClass('active');
		}
		clickedObj = $(this);
		$(this).addClass('active');
		
		hideAll();
		$('.boardOfExpertsContent #b2').show();
		return false;
	});
	
	$('.boardOfExperts #b3').click(function(){
		if(clickedObj != null){
			clickedObj.removeClass('active');
		}
		clickedObj = $(this);
		$(this).addClass('active');
		
		hideAll();
		$('.boardOfExpertsContent #b3').show();
		return false;
	});
	
	$('.boardOfExperts #b4').click(function(){
		if(clickedObj != null){
			clickedObj.removeClass('active');
		}
		clickedObj = $(this);
		$(this).addClass('active');
		
		hideAll();
		$('.boardOfExpertsContent #b4').show();
		return false;
	});
	
	$('.boardOfExperts #b5').click(function(){
		if(clickedObj != null){
			clickedObj.removeClass('active');
		}
		clickedObj = $(this);
		$(this).addClass('active');
		
		hideAll();
		$('.boardOfExpertsContent #b5').show();
		return false;
	});
});

function hideAll(){
	$('.boardOfExpertsContent #b1').hide();
	$('.boardOfExpertsContent #b2').hide();
	$('.boardOfExpertsContent #b3').hide();
	$('.boardOfExpertsContent #b4').hide();
	$('.boardOfExpertsContent #b5').hide();
}