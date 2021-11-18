	$('.owl-carousel').owlCarousel({
	    stagePadding: 10,
	    loop: true,
	    margin: 10,
	    navigation: false,
	    center: true,
	    responsive: { 0: { items: 1 } }
	});

$(window).on('scroll touchmove', function() {
	   let scrollToTop = $(document).scrollTop(); // how much scroll have we done?

	   if (scrollToTop > 5) {
	     $('.nav--top').addClass('nav--top--sticky');
	   } else {
	     $('.nav--top').removeClass('nav--top--sticky');
	   }
	});