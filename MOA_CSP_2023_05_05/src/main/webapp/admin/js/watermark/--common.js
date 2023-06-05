
$(document).ready(function () {

    //Enabled cut copy paste
    
   
    //Enabled mouse right click
   
});


function printDivOptimize(divName,header,allLbl,allVal,statusCol) {
	$('.lastCol').hide();
	$("div#divShow").empty();
	$("div#divShow").show();
	
 	var row="";
 	var printLbl = allLbl; 
 	var printVal = allVal; 
	    row +='<div class="print_content"> ';
	 	row +='<div class="print_logo"> ';
		row +='<div class="row"> ';
		row +='<div class="col-3 col-sm-3 col-md-3"><img src="js/images/indianarmy_smrm5aaa.jpg"></div> ';
		row +='<div class="col-6 col-sm-6 col-md-6"><h3>'+header+'</h3> </div> ';
		row +='<div class="col-3 col-sm-3 col-md-3" align="right"><img src="js/images/dgis-logo.png"></div> ';
		row +='</div> ';
		row +='</div> ';
		row +='	<div class="print_text"> ';
		row +='<div class="row"> ';
		
		var i;
		for (i = 0; i < printLbl.length; i++) {
			row +='<div class="col-12 col-sm-6 col-md-6"><label class=" label_left form-control-label" id="lbluc">'+ printLbl[i]+'</label> ';
			row +='<label class="label_right" id="uploaded_wepelbl">'+ printVal[i]+'</label> </div>';
		}
		row +='</div> ';
		row +='</div> ';
		row +='</div> ';
		
	 $("div#divShow").append(row); 
	 let popupWinindow
	    let innerContents = document.getElementById(divName).innerHTML;
	    popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	    popupWinindow.document.open();
	    popupWinindow.oncontextmenu = function () {
		 	 return false;
		 }
	   // popupWinindow.document.write('<html><head><link rel="stylesheet" href="layout_file/css/bootstrap.min.css"><link rel="stylesheet" href="layout_file/css/style.css"><style>table td{font-size:12px;} table th{font-size:13px !important;}</style></head><body onload="window.print()"  oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' + innerContents + '</html>');
       popupWinindow.document.write('<html><head><link rel="stylesheet" href="layout_file/css/bootstrap.min.css"><link rel="stylesheet" href="layout_file/css/style.css"><link rel="stylesheet" href="js/watermark/printwatermark_old.css"><style> table td{font-size:12px;} table th{font-size:13px !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
	   watermarkreport();
	   
	   popupWinindow.document.close();
	   $("div#divShow").hide();
	   $('.lastCol').show();
}

function printDivOptimizehelp(divName,header,allLbl,allVal,statusCol) {
	$('.lastCol').hide();
	$("div#divShow").empty();
	$("div#divShow").show();
	
 	var row="";
 	var printLbl = allLbl; 
 	var printVal = allVal; 
	    row +='<div class="print_content"> ';
	 	row +='<div class="print_logo"> ';
		row +='<div class="row"> ';
		row +='<div class="col-3 col-sm-3 col-md-3"><img src="js/images/indianarmy_smrm5aaa.jpg"></div> ';
		row +='<div class="col-6 col-sm-6 col-md-6"><h3>'+header+'</h3> </div> ';
		row +='<div class="col-3 col-sm-3 col-md-3" align="right"><img src="js/images/dgis-logo.png"></div> ';
		row +='</div> ';
		row +='</div> ';
		row +='	<div class="print_text"> ';
		row +='<div class="row"> ';
		
		var i;
		for (i = 0; i < printLbl.length; i++) {
			/*row +='<div class="col-6 col-sm-3 col-md-3"><label class=" label_left form-control-label" id="lbluc">'+ printLbl[i]+'</label> ';*/
			row +='<div class=col-6 col-6"><label class=" label_left form-control-label" id="lbluc">'+ printLbl[i]+'</label> ';
			row +='<label class="label_right" id="uploaded_wepelbl">'+ printVal[i]+'</label> </div>';
		}
		row +='</div> ';
		row +='</div> ';
		row +='</div> ';
		
	 $("div#divShow").append(row); 
	 let popupWinindow
	    let innerContents = document.getElementById(divName).innerHTML;
	    popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	    popupWinindow.document.open();
	    popupWinindow.oncontextmenu = function () {
		 	 return false;
		}
	    popupWinindow.document.write('<html><head><link rel="stylesheet" href="layout_file/css/bootstrap.min.css"><link rel="stylesheet" href="layout_file/css/style.css"><link rel="stylesheet" href="js/watermark/printwatermark_old.css"><style> table td{font-size:12px;} table th{font-size:13px !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
		   watermarkreport();
	   
	   popupWinindow.document.close();
	   $("div#divShow").hide();
	   $('.lastCol').show();
}



//======================== Validation For String with space
var globalSpace="";
function onlyAlphabetsStringSpace(e, t) {
	
    try { 
   
        if (window.event) {
            var charCode = window.event.keyCode;
        }
        else if (e) {
            var charCode = e.which;
        }
        else { return true; }
        
	         
	    if(t.value=="" &&  charCode==32){
	    	$("#"+t.id).val("");
	    	return false;
	    }	
	    if (((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode==32)){
	        	if(charCode==32 && globalSpace==32){
	        		return false;
	        	}else{
	        	 	globalSpace=charCode;
	             	return true;
	        	}
	           }
	        else{
	            return false;
	            }
	    }
    catch (err) {
       // alert(err.Description);
    }
} 

//======================== Validation For Only 1 to 9 accept
function isNumberKey1to9(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 49 || charCode > 57)) {
	        return false;
	} else {
	    if (charCode == 46) {
	        return false;
	    }
	}
	return true;
}

//======================== Validation For Empty input

function checkEmptyval(){
	
var elements = document.getElementsByClassName("empty_class");
var names = '';
for(var i = 0; i < elements.length; i++) {
    	names = elements[i].value;
    	if(names=="" || name==undefined || name==null){alert("hii");
    		alert(elements[i].placeholder);break;
    	
    }
}
}

//======================== Validation For length
function checkLength(id,minleg){

	 var charLength = $("input#id").val().length;
	
       if(charLength < minleg){
       		alert($(id).attr('placeholder')); 
			
       }  
}

//======================== Validation For Only 0 to 9 accept
function isNumberKey0to9(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	} else {
	    if (charCode == 46) {
	        return false;	
	    }
	}
	return true;
}

//============================== Validation For String and Number only
function onlyAlphaNumeric(e, t) {	
    return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || (e.charCode >= 48 && e.charCode <= 57 ) || e.charCode == 32;   
}

function getformatedDate(dateString){
			var dateParts = dateString.split("/");
			return new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]); 
		}





