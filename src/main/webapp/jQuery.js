$(document).ready(function() {
	$('.menu li a').click(function(e) {
		 var $this = $(this);
		 if (!$this.hasClass('active')) {
		   $this.addClass('active');
	 }e.preventDefault();
	});
})