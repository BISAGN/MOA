(function () {
	$('.hamburger-menu .bar').on('click', function() {
		$('.bar').toggleClass('animate');
    $('.mobile-menu').toggleClass('active');
    return false;
	})
  $('.has-children').on ('click', function() {
		   $(this).children('ul').slideToggle('slow', 'swing');
       $('.icon-arrow').toggleClass('open');
	});
})();