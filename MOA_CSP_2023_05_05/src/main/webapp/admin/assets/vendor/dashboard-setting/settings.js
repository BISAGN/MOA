(function($) {
  'use strict';
  $(function() {
//    $(".nav-settings").on("click", function() {
//      $("#right-sidebar").toggleClass("open");
//    });

    $(".settings-close").on("click", function() {
      $("#right-sidebar,#theme-settings").removeClass("open");
    });

    $("#settings-trigger").on("click" , function(){
      $("#theme-settings").toggleClass("open");
    });

    //background constants
    var navbar_classes = "navbar-danger navbar-success navbar-warning navbar-dark navbar-primary navbar-info";
    var aside_classes = "navbar-danger1 navbar-success1 navbar-warning1 navbar-dark1 navbar-primary1 navbar-info1";
    
    var navbar_classes_n2 = "navbar-danger-n2 navbar-success-n2 navbar-warning-n2 navbar-dark-n2 navbar-primary-n2 navbar-info-n2";
    var aside_classes_s2 = "navbar-danger-s2 navbar-success-s2 navbar-warning-s2 navbar-dark-s2 navbar-primary-s2 navbar-info-s2";
    
    var sidebar_classes = "db-light-mode db-dark-mode";
    var $body = $("body");

    //db-theme dark/light mode
    document.getElementById("sidebar-light-theme").addEventListener("click", dblighttheme);
    document.getElementById("sidebar-dark-theme").addEventListener("click", dbdarktheme);       
    
    function dblighttheme(){		
	  $body.removeClass(sidebar_classes);
      $body.addClass("db-light-mode");
      $(".sidebar-bg-options").removeClass("selected");
      $("#sidebar-light-theme").addClass("selected");
      localStorage.setItem('dbthememode', 'dblightmode');
	}

	function dbdarktheme(){	
	  $body.removeClass(sidebar_classes);
	  $body.addClass("db-dark-mode");
	  $(".sidebar-bg-options").removeClass("selected");
	  $("#sidebar-dark-theme").addClass("selected");
	  localStorage.setItem('dbthememode', 'dbdarkmode');    
	}

    //header Backgrounds  
    document.getElementById("h-tile-def").addEventListener("click", h_tile_def_fun);
    document.getElementById("h-tile-suc").addEventListener("click", h_tile_suc_fun);
    document.getElementById("h-tile-war").addEventListener("click", h_tile_war_fun);
    document.getElementById("h-tile-dan").addEventListener("click", h_tile_dan_fun);
    document.getElementById("h-tile-inf").addEventListener("click", h_tile_inf_fun);
    document.getElementById("h-tile-dark").addEventListener("click", h_tile_dark_fun);     

	function h_tile_def_fun(){	 
      $(".header").removeClass(navbar_classes).removeClass(navbar_classes_n2);
      $(".header").addClass("navbar-primary");
      $(".tiles").removeClass("selected");
      $("#h-tile-def").addClass("selected");
      localStorage.removeItem('mixh_tile');	
      localStorage.setItem('h_tile', 'h_tile_default'); 
	}	
	function h_tile_suc_fun(){    
      $(".header").removeClass(navbar_classes).removeClass(navbar_classes_n2);
      $(".header").addClass("navbar-success");
      $(".tiles").removeClass("selected");
      $("#h-tile-suc").addClass("selected");
      localStorage.removeItem('mixh_tile');
      localStorage.setItem('h_tile', 'h_tile_success');   
	}	
	function h_tile_war_fun(){    
      $(".header").removeClass(navbar_classes).removeClass(navbar_classes_n2);
      $(".header").addClass("navbar-warning");
      $(".tiles").removeClass("selected");
      $("#h-tile-war").addClass("selected");
      localStorage.removeItem('mixh_tile');
      localStorage.setItem('h_tile', 'h_tile_warning');  
	}	
	function h_tile_dan_fun(){    
      $(".header").removeClass(navbar_classes).removeClass(navbar_classes_n2);
      $(".header").addClass("navbar-danger");
      $(".tiles").removeClass("selected");
      $("#h-tile-dan").addClass("selected");
      localStorage.removeItem('mixh_tile');
      localStorage.setItem('h_tile', 'h_tile_danger');  
	}
	
	function h_tile_inf_fun(){    
      $(".header").removeClass(navbar_classes).removeClass(navbar_classes_n2);
      $(".header").addClass("navbar-info");
      $(".tiles").removeClass("selected");
      $("#h-tile-inf").addClass("selected");
      localStorage.removeItem('mixh_tile');
      localStorage.setItem('h_tile', 'h_tile_info');  
	}
	function h_tile_dark_fun(){    
      $(".header").removeClass(navbar_classes).removeClass(navbar_classes_n2);
      $(".header").addClass("navbar-dark");
      $(".tiles").removeClass("selected");
      $("#h-tile-dark").addClass("selected");
      localStorage.removeItem('mixh_tile');
      localStorage.setItem('h_tile', 'h_tile_dark');  
	}	
	  
    
    //Sidebar Backgrounds  
    document.getElementById("s-tile-def").addEventListener("click", s_tile_def_fun);
    document.getElementById("s-tile-suc").addEventListener("click", s_tile_suc_fun);
    document.getElementById("s-tile-war").addEventListener("click", s_tile_war_fun);
    document.getElementById("s-tile-dan").addEventListener("click", s_tile_dan_fun);
    document.getElementById("s-tile-inf").addEventListener("click", s_tile_inf_fun);
    document.getElementById("s-tile-dark").addEventListener("click", s_tile_dark_fun); 
      
    function s_tile_def_fun(){    
        $(".sidebar-nav-wrapper").removeClass(aside_classes).removeClass(aside_classes_s2);
        $(".sidebar-nav-wrapper").addClass("navbar-primary1");
        $(".tiles2").removeClass("selected");             
        $("#s-tile-def").addClass("selected");
        localStorage.removeItem('mixsbar_tile');
      	localStorage.setItem('s_tile', 's_tile_default'); 
	}	
	function s_tile_suc_fun(){    
        $(".sidebar-nav-wrapper").removeClass(aside_classes).removeClass(aside_classes_s2);
      	$(".sidebar-nav-wrapper").addClass("navbar-success1");
      	$(".tiles1").removeClass("selected");
      	$("#s-tile-suc").addClass("selected");
      	localStorage.removeItem('mixsbar_tile');
      	localStorage.setItem('s_tile', 's_tile_success'); 
	}
	function s_tile_war_fun(){    
        $(".sidebar-nav-wrapper").removeClass(aside_classes).removeClass(aside_classes_s2);
      	$(".sidebar-nav-wrapper").addClass("navbar-warning1");
      	$(".tiles1").removeClass("selected");
      	$("#s-tile-war").addClass("selected");
      	localStorage.removeItem('mixsbar_tile');
      	localStorage.setItem('s_tile', 's_tile_warning'); 
	}
	function s_tile_dan_fun(){    
        $(".sidebar-nav-wrapper").removeClass(aside_classes).removeClass(aside_classes_s2);
      	$(".sidebar-nav-wrapper").addClass("navbar-danger1");
      	$(".tiles1").removeClass("selected");
      	$("#s-tile-dan").addClass("selected");
      	localStorage.removeItem('mixsbar_tile');
      	localStorage.setItem('s_tile', 's_tile_danger'); 
	}
	function s_tile_inf_fun(){    
        $(".sidebar-nav-wrapper").removeClass(aside_classes).removeClass(aside_classes_s2);
      	$(".sidebar-nav-wrapper").addClass("navbar-info1");
      	$(".tiles1").removeClass("selected");
      	$("#s-tile-inf").addClass("selected");
      	localStorage.removeItem('mixsbar_tile');
      	localStorage.setItem('s_tile', 's_tile_info'); 
	}
	function s_tile_dark_fun(){    
        $(".sidebar-nav-wrapper").removeClass(aside_classes).removeClass(aside_classes_s2);
      	$(".sidebar-nav-wrapper").addClass("navbar-dark1");
      	$(".tiles1").removeClass("selected");
      	$("#s-tile-dark").addClass("selected");
      	localStorage.removeItem('mixsbar_tile');
      	localStorage.setItem('s_tile', 's_tile_dark'); 
	}      
        
    //header + Sidebar Backgrounds Mixer    
        
    //mix header Backgrounds
    document.getElementById("mix-tile-def").addEventListener("click", mixh_tile_def_fun);
    document.getElementById("mix-tile-suc").addEventListener("click", mixh_tile_suc_fun);
    document.getElementById("mix-tile-war").addEventListener("click", mixh_tile_war_fun);
    document.getElementById("mix-tile-dan").addEventListener("click", mixh_tile_dan_fun);
    document.getElementById("mix-tile-inf").addEventListener("click", mixh_tile_inf_fun);
    document.getElementById("mix-tile-dark").addEventListener("click", mixh_tile_dark_fun);
    
    function mixh_tile_def_fun(){   		
      $(".header").removeClass(navbar_classes_n2).removeClass(navbar_classes);
      $(".header").addClass("navbar-primary-n2");
      $(".tiles2").removeClass("selected");
      $(".tiles1").removeClass("selected");
      $(".tiles").removeClass("selected");
      $("#mix-tile-def").addClass("selected");
      localStorage.removeItem('h_tile');
      localStorage.setItem('mixh_tile', 'mixh_tile_default');	  
	}
	function mixh_tile_suc_fun(){    		
      $(".header").removeClass(navbar_classes_n2).removeClass(navbar_classes);
      $(".header").addClass("navbar-success-n2");
      $(".tiles2").removeClass("selected");
      $(".tiles1").removeClass("selected");
      $(".tiles").removeClass("selected");
      $("#mix-tile-suc").addClass("selected");
      localStorage.removeItem('h_tile');
      localStorage.setItem('mixh_tile', 'mixh_tile_success');	  
	}
	function mixh_tile_war_fun(){   	
      $(".header").removeClass(navbar_classes_n2).removeClass(navbar_classes);
      $(".header").addClass("navbar-warning-n2");
      $(".tiles2").removeClass("selected");
      $(".tiles1").removeClass("selected");
      $(".tiles").removeClass("selected");
      $("#mix-tile-war").addClass("selected");
      localStorage.removeItem('h_tile');
      localStorage.setItem('mixh_tile', 'mixh_tile_warning');	 
	}
	function mixh_tile_dan_fun(){  		
      $(".header").removeClass(navbar_classes_n2).removeClass(navbar_classes);
      $(".header").addClass("navbar-danger-n2");
      $(".tiles2").removeClass("selected");
      $(".tiles1").removeClass("selected");
      $(".tiles").removeClass("selected");
      $("#mix-tile-dan").addClass("selected");
      localStorage.removeItem('h_tile');
      localStorage.setItem('mixh_tile', 'mixh_tile_danger');	  
	}
	function mixh_tile_inf_fun(){  		
      $(".header").removeClass(navbar_classes_n2).removeClass(navbar_classes);
      $(".header").addClass("navbar-info-n2");
      $(".tiles2").removeClass("selected");
      $(".tiles1").removeClass("selected");
      $(".tiles").removeClass("selected");
      $("#mix-tile-inf").addClass("selected");
      localStorage.removeItem('h_tile');
      localStorage.setItem('mixh_tile', 'mixh_tile_info');	  
	}
	function mixh_tile_dark_fun(){   		
      $(".header").removeClass(navbar_classes_n2).removeClass(navbar_classes);
      $(".header").addClass("navbar-dark-n2");
      $(".tiles2").removeClass("selected");
      $(".tiles1").removeClass("selected");
      $(".tiles").removeClass("selected");
      $("#mix-tile-dark").addClass("selected");
      localStorage.removeItem('h_tile');
      localStorage.setItem('mixh_tile', 'mixh_tile_dark');	  
	}
	
    
    //mix sidebar Backgrounds  
    document.getElementById("mix-tile-def").addEventListener("click", mixsbar_tile_def_fun);
    document.getElementById("mix-tile-suc").addEventListener("click", mixsbar_tile_suc_fun);
    document.getElementById("mix-tile-war").addEventListener("click", mixsbar_tile_war_fun);
    document.getElementById("mix-tile-dan").addEventListener("click", mixsbar_tile_dan_fun);
    document.getElementById("mix-tile-inf").addEventListener("click", mixsbar_tile_inf_fun);
    document.getElementById("mix-tile-dark").addEventListener("click", mixsbar_tile_dark_fun);
      
    function mixsbar_tile_def_fun(){    		
      $(".sidebar-nav-wrapper").removeClass(aside_classes_s2).removeClass(aside_classes);
      $(".sidebar-nav-wrapper").addClass("navbar-primary-s2");
      $(".tiles2").removeClass("selected");
      $(".tiles1").removeClass("selected");
      $(".tiles").removeClass("selected");
      $("#mix-tile-def").addClass("selected");
      localStorage.removeItem('s_tile');
      localStorage.setItem('mixsbar_tile', 'mixsbar_tile_default');      	 
	}
	
	function mixsbar_tile_suc_fun(){  		
      $(".sidebar-nav-wrapper").removeClass(aside_classes_s2).removeClass(aside_classes);
      $(".sidebar-nav-wrapper").addClass("navbar-success-s2");
      $(".tiles2").removeClass("selected");
      $(".tiles1").removeClass("selected");
      $(".tiles").removeClass("selected");
      $("#mix-tile-suc").addClass("selected");
      localStorage.removeItem('s_tile');
      localStorage.setItem('mixsbar_tile', 'mixsbar_tile_success');      	 
	}
	
	function mixsbar_tile_war_fun(){ 		
      $(".sidebar-nav-wrapper").removeClass(aside_classes_s2).removeClass(aside_classes);
      $(".sidebar-nav-wrapper").addClass("navbar-warning-s2");
      $(".tiles2").removeClass("selected");
      $(".tiles1").removeClass("selected");
      $(".tiles").removeClass("selected");
      $("#mix-tile-war").addClass("selected");
      localStorage.removeItem('s_tile');
      localStorage.setItem('mixsbar_tile', 'mixsbar_tile_warning');       
	}
	
	function mixsbar_tile_dan_fun(){  		
      $(".sidebar-nav-wrapper").removeClass(aside_classes_s2).removeClass(aside_classes);
      $(".sidebar-nav-wrapper").addClass("navbar-danger-s2");
      $(".tiles2").removeClass("selected");
      $(".tiles1").removeClass("selected");
      $(".tiles").removeClass("selected");
      $("#mix-tile-dan").addClass("selected");
      localStorage.removeItem('s_tile');
      localStorage.setItem('mixsbar_tile', 'mixsbar_tile_danger');      	 
	}
	
	function mixsbar_tile_inf_fun(){  		
      $(".sidebar-nav-wrapper").removeClass(aside_classes_s2).removeClass(aside_classes);
      $(".sidebar-nav-wrapper").addClass("navbar-info-s2");
      $(".tiles2").removeClass("selected");
      $(".tiles1").removeClass("selected");
      $(".tiles").removeClass("selected");
      $("#mix-tile-inf").addClass("selected");
      localStorage.removeItem('s_tile');
      localStorage.setItem('mixsbar_tile', 'mixsbar_tile_info');      	 
	}
	
	function mixsbar_tile_dark_fun(){ 		
      $(".sidebar-nav-wrapper").removeClass(aside_classes_s2).removeClass(aside_classes);
      $(".sidebar-nav-wrapper").addClass("navbar-dark-s2");
      $(".tiles2").removeClass("selected");
      $(".tiles1").removeClass("selected");
      $(".tiles").removeClass("selected");
      $("#mix-tile-dark").addClass("selected");
      localStorage.removeItem('s_tile');
      localStorage.setItem('mixsbar_tile', 'mixsbar_tile_dark');       
	}


function setselectedtheme(){	
		// header background only
		if(localStorage.getItem('h_tile') == 'h_tile_default'){ 				
				h_tile_def_fun();							
		}
		else if(localStorage.getItem('h_tile') == 'h_tile_success'){
				h_tile_suc_fun();				
		}
		else if(localStorage.getItem('h_tile') == 'h_tile_warning'){
				h_tile_war_fun();				
		}
		else if(localStorage.getItem('h_tile') == 'h_tile_danger'){
				h_tile_dan_fun();					
		}
		else if(localStorage.getItem('h_tile') == 'h_tile_info'){
				h_tile_inf_fun();				
		}
		else if(localStorage.getItem('h_tile') == 'h_tile_dark'){
				h_tile_dark_fun();				
		}
		
		// sidebar background only
		if(localStorage.getItem('s_tile') == 's_tile_default'){ 				
				s_tile_def_fun();							
		}
		else if(localStorage.getItem('s_tile') == 's_tile_success'){
				s_tile_suc_fun();				
		}
		else if(localStorage.getItem('s_tile') == 's_tile_warning'){
				s_tile_war_fun();					
		}
		else if(localStorage.getItem('s_tile') == 's_tile_danger'){
				s_tile_dan_fun();				
		}
		else if(localStorage.getItem('s_tile') == 's_tile_info'){
				s_tile_inf_fun();				
		}
		else if(localStorage.getItem('s_tile') == 's_tile_dark'){
				s_tile_dark_fun();				
		}			

		// mixer header + sidebar background
		// mix header bg			
		if(localStorage.getItem('mixh_tile') == 'mixh_tile_default'){				
				mixh_tile_def_fun();						
		}
		else if(localStorage.getItem('mixh_tile') == 'mixh_tile_success'){			
				mixh_tile_suc_fun();										
		}
		else if(localStorage.getItem('mixh_tile') == 'mixh_tile_warning'){			
				mixh_tile_war_fun();								
		}
		else if(localStorage.getItem('mixh_tile') == 'mixh_tile_danger'){			
				mixh_tile_dan_fun();									
		}
		else if(localStorage.getItem('mixh_tile') == 'mixh_tile_info'){			
				mixh_tile_inf_fun();										
		}
		else if(localStorage.getItem('mixh_tile') == 'mixh_tile_dark'){			
				mixh_tile_dark_fun();								
		}	
										
		// mix sidebar bg
		if(localStorage.getItem('mixsbar_tile') == 'mixsbar_tile_default'){ 					
				mixsbar_tile_def_fun();							
		}
		else if(localStorage.getItem('mixsbar_tile') == 'mixsbar_tile_success'){			
				mixsbar_tile_suc_fun();							
		}
		else if(localStorage.getItem('mixsbar_tile') == 'mixsbar_tile_warning'){			
				mixsbar_tile_war_fun();							
		}
		else if(localStorage.getItem('mixsbar_tile') == 'mixsbar_tile_danger'){			
				mixsbar_tile_dan_fun();							
		}
		else if(localStorage.getItem('mixsbar_tile') == 'mixsbar_tile_info'){			
				mixsbar_tile_inf_fun();							
		}
		else if(localStorage.getItem('mixsbar_tile') == 'mixsbar_tile_dark'){			
				mixsbar_tile_dark_fun();												
		}
			
}
   
       
jQuery(document).ready(function() {
		
	setselectedtheme();
	
//	theme mode (front) dark/light start		
	if(localStorage.getItem('dbthememode') == 'dblightmode'){	 				
		dblighttheme();	 				
	}
	else if(localStorage.getItem('dbthememode') == 'dbdarkmode'){	 				
		dbdarktheme();	 				
	}
//	theme mode (front) dark/light end

	
});		
	
		    
  });
})(jQuery);	
