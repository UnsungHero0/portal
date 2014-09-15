var clickedObj;

$(document).ready(function() {
	clickedObj = $('.boardOfDirectors #b1');
	$('.boardOfDirectors #b1').addClass('active');
	
	$('.boardOfDirectors #b1').click(function(){
		if(clickedObj != null){
			clickedObj.removeClass('active');
		}
		clickedObj = $(this);
		$(this).addClass('active');

		hideAll();
		$('.boardOfDirectorsContent #b1').show();
	});
	
	$('.boardOfDirectors #b2').click(function(){
		if(clickedObj != null){
			clickedObj.removeClass('active');
		}
		clickedObj = $(this);
		$(this).addClass('active');
		
		hideAll();
		$('.boardOfDirectorsContent #b2').show();
	});
	
	$('.boardOfDirectors #b3').click(function(){
		if(clickedObj != null){
			clickedObj.removeClass('active');
		}
		clickedObj = $(this);
		$(this).addClass('active');
		
		hideAll();
		$('.boardOfDirectorsContent #b3').show();
	});
	
	$('.boardOfDirectors #b4').click(function(){
		if(clickedObj != null){
			clickedObj.removeClass('active');
		}
		clickedObj = $(this);
		$(this).addClass('active');
		
		hideAll();
		$('.boardOfDirectorsContent #b4').show();
	});
	
	$('.boardOfDirectors #b5').click(function(){
		if(clickedObj != null){
			clickedObj.removeClass('active');
		}
		clickedObj = $(this);
		$(this).addClass('active');
		
		hideAll();
		$('.boardOfDirectorsContent #b5').show();
	});
	
	$('.boardOfDirectors #b6').click(function(){
		if(clickedObj != null){
			clickedObj.removeClass('active');
		}
		clickedObj = $(this);
		$(this).addClass('active');
		
		hideAll();
		$('.boardOfDirectorsContent #b6').show();
	});
	
	$('.boardOfDirectors #b7').click(function(){
		if(clickedObj != null){
			clickedObj.removeClass('active');
		}
		clickedObj = $(this);
		$(this).addClass('active');
		
		hideAll();
		$('.boardOfDirectorsContent #b7').show();
	});
	
	$('.boardOfDirectors #b8').click(function(){
		if(clickedObj != null){
			clickedObj.removeClass('active');
		}
		clickedObj = $(this);
		$(this).addClass('active');
		
		hideAll();
		$('.boardOfDirectorsContent #b8').show();
	});
	
	$('.boardOfDirectors #b9').click(function(){
		if(clickedObj != null){
			clickedObj.removeClass('active');
		}
		clickedObj = $(this);
		$(this).addClass('active');
		
		hideAll();
		$('.boardOfDirectorsContent #b9').show();
	});
	
	$('.boardOfDirectors #b10').click(function(){
		if(clickedObj != null){
			clickedObj.removeClass('active');
		}
		clickedObj = $(this);
		$(this).addClass('active');
		
		hideAll();
		$('.boardOfDirectorsContent #b10').show();
	});
	
	$('.boardOfDirectors #b11').click(function(){
		if(clickedObj != null){
			clickedObj.removeClass('active');
		}
		clickedObj = $(this);
		$(this).addClass('active');
		
		hideAll();
		$('.boardOfDirectorsContent #b11').show();
	});
	
	$('.boardOfDirectors #b12').click(function(){
		if(clickedObj != null){
			clickedObj.removeClass('active');
		}
		clickedObj = $(this);
		$(this).addClass('active');
		
		hideAll();
		$('.boardOfDirectorsContent #b12').show();
	});
});

function hideAll(){
	$('.boardOfDirectorsContent #b1').hide();
	$('.boardOfDirectorsContent #b2').hide();
	$('.boardOfDirectorsContent #b3').hide();
	$('.boardOfDirectorsContent #b4').hide();
	$('.boardOfDirectorsContent #b5').hide();
	$('.boardOfDirectorsContent #b6').hide();
	$('.boardOfDirectorsContent #b7').hide();
	$('.boardOfDirectorsContent #b8').hide();
	$('.boardOfDirectorsContent #b9').hide();
	$('.boardOfDirectorsContent #b10').hide();
	$('.boardOfDirectorsContent #b11').hide();
	$('.boardOfDirectorsContent #b12').hide();
	
}