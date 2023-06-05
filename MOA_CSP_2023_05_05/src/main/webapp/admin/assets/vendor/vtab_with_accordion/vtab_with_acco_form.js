// ----------------- Variables

wrapper   = $(".tabs");
tabs      = wrapper.find(".tab");
tabToggle = wrapper.find(".tab-toggle");


// ----------------- Functions

function openTab() {
	var content     = $(this).parent().next(".content"),
		activeItems = wrapper.find(".active1");
	
	if(!$(this).hasClass('active1')) {
		$(this).add(content).add(activeItems).toggleClass('active1');		
		wrapper.css('min-height', content.outerHeight());
	}
};

// ----------------- Interactions

tabToggle.on('click', openTab);

// ----------------- Constructor functions

/*$(window).load(function(){
  tabToggle.first().trigger('click');  
});*/

$(window).on('load', function() {
 tabToggle.first().trigger('click');  
});


