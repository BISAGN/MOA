$(document).ready(function(){
	$(".englishvideo").hide();
	 $(".hindivideo").show();
	 
    $('#myselection').on('change', function(){
    	debugger;
    	var demovalue = $(this).val(); 
    	if(demovalue=='hindi'){
    		$(".englishvideo").hide();
   		 $(".hindivideo").show();
   		 
    	}else{
    	
    		 $(".hindivideo").hide();
    		 $(".englishvideo").show();
    	}
       
       // document.getElementsByClassName("myDiv").classList.add("custom-d-none");
       // $('.myDiv').addClass('custom-d-none');
       // $("#show"+demovalue).show();
    });
});