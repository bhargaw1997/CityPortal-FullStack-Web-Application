$('.owl-carousel').owlCarousel({
	nestedItemSelector: 'item',
	loop: true,
	nav: false,
	items: 1,
	lazyLoad: true,
	autoplay: true,
	
})
$("a.owl-prev").click(function() {
	$("div.owl-prev").click();
})
$("a.owl-next").click(function() {
	$("div.owl-next").click();
})