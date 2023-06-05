
$.noConflict();jQuery(document).ready(
		function($){"use strict";
		[].slice.call(document.querySelectorAll('select.cs-select')).forEach(function(el){
			new SelectFx(el)});
jQuery('.selectpicker').selectpicker;
$('#menuToggle').on('click',function(event){
	$('body').toggleClass('open');
	$('.navbar.navbar-expand-sm.navbar-default').toggle()
});
$('.search-trigger').on('click',function(event){
	event.preventDefault();
	event.stopPropagation();
	$('.search-trigger').parent('.header-left').addClass(
)});
$('.search-close').on('click',function(event){
	event.preventDefault();
	event.stopPropagation();
	$('.search-trigger').parent('.header-left').removeClass('open')
});

$(window).scroll(function(){
	if($(document).scrollTop()>50){
		$(".header_bottom").addClass("head_nav")
	}else{
		$(".header_bottom").removeClass("head_nav")
	}
});
$(".login_button").click(function(){$(".login-content").toggle()});
$('.navbar .dropdown-item').on('click',function(e){
	var $el=$(this).children('.dropdown-toggle');
	var $parent=$el.offsetParent(".dropdown-menu");
	$(this).parent("li").toggleClass('open');
	if(!$parent.parent().hasClass('navbar-nav')){
		if($parent.hasClass('show')){
			$parent.removeClass('show');
			$el.next().removeClass('show');
			$el.next().css({"top":-999,"left":-999})
		}else{
			$parent.parent().find('.show').removeClass('show');
			$parent.addClass('show');$el.next().addClass('show');
			$el.next().css({"top":$el[0].offsetTop,"left":$parent.outerWidth()-4})
		}e.stopPropagation()
	}
});
$('.navbar .dropdown').on('hidden.bs.dropdown',function(){
	$(this).find('li.dropdown').removeClass('show open');
	$(this).find('ul.dropdown-menu').removeClass('show open')
});
	
$("#rejectModal").bind('cut copy paste',function(e){e.stopPropagation()})


// ============================================================== 
// Auto select left navbar
// ============================================================== 
$(function() {
    "use strict";
     var url = window.location + "";
        var path = url.replace(window.location.protocol + "//" + window.location.host + "/", "");
        var element = $('ul.navbar-nav a').filter(function() {
            return this.href === url || this.href === path;// || url.href.indexOf(this.href) === 0;
        });
        element.parentsUntil(".dropdown-item.show").each(function (index)
        {
            if($(this).is("li") && $(this).children("a").length !== 0)
            {
                $(this).children("a").addClass("active");
                $(this).parent("ul.navbar-nav").length === 0
                    ? $(this).addClass("active")
                    : $(this).addClass("selected");
            }
            else if(!$(this).is("ul") && $(this).children("a").length === 0)
            {
                $(this).addClass("selected");
                
            }
            else if($(this).is("ul")){
                $(this).addClass('in');
            }
            
        });

    element.addClass("active"); 
    $('.navbar-nav a').on('click', function (e) {        
            if (!$(this).hasClass("active")) {
                // hide any open menus and remove all other classes
                $("ul", $(this).parents("ul:first")).removeClass("in , show");
                $("a", $(this).parents("ul:first")).removeClass("active");
                
                // open our new menu and add the open class
                $(this).next("ul").addClass("in");
                $(this).addClass("active");
                
            }
            else if ($(this).hasClass("active")) {
                $(this).removeClass("active");
                $(this).parents("ul:first").removeClass("active");
                $(this).next("ul").removeClass("in");
            }
    })
    $('.navbar-nav >li >a.dropdown-toggle').on('click', function (e) {
        e.preventDefault();
    });
    
});



});
