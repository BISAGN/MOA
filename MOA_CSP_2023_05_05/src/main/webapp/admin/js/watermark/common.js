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
	debugger; 
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

function checkEmptyval() {

	var elements = document.getElementsByClassName("empty_class");
	var names = '';
	for (var i = 0; i < elements.length; i++) {
		names = elements[i].value;
		if (names == "" || name == undefined || name == null) {
			alert("hii");
			alert(elements[i].placeholder); break;
		}
	}
}

//======================== Validation For length
function checkLength(id,minleg){
	var charLength = $("#"+id).val().length;
	   if(charLength < minleg){
	   		alert($("#"+id).attr('placeholder')); 
	   }  
}

//============================ percentage

function checkpercentage(val){
	
	var re = /^((0|[1-9]\d?)(\.\d{1,2})?|100(\.00?)?)$/;
	
	if(!re.test(val))
{
    alert("Enter only % value");
}
	//var charLength = $("#"+id).val().length;
	   
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


function onlyAlphaNumericAbhiCustom(e, t) {	
    return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || (e.charCode >= 48 && e.charCode <= 57 ) || e.charCode == 32 || 
     (e.charCode != 34 && e.charCode != 39 && e.charCode != 60 &&  e.charCode != 62);   
}

function getformatedDate(dateString){
			var dateParts = dateString.split("/");
			return new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]); 
		}


//=============== pdf file and size validation ========================
function pdfFileSizeValidation(val,myfile,id,size,lbltik_id,lbl_id,ac) {
	
		
		//@psp
	document.getElementById(lbl_id).innerHTML="";
	document.getElementById(lbltik_id).innerHTML="";
	var ext = myfile.split('.').pop();
	var acceptlist = ac.split(',');
	for (var i =0; i<acceptlist.length; i++){
		acceptlist[i] = acceptlist[i].trim().toLowerCase().split('.').pop();
	}
	if(!acceptlist.includes(ext.toLowerCase())){
			//alert('Please Only Allowed *.pdf File Extension');
			$("#"+id).val("");
			document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>Please Only Allowed *"+ac+" File Extension";
		}

	else if (size == "100mb" && val.files[0].size > 104857600) {
		//alert("File Size Upto 2 MB!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 100 MB!";
	}
	else if (size == "2mb" && val.files[0].size > 2097152) {
		//alert("File Size Upto 2 MB!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 2 MB!";
	}
	else if (size == "200kb" && val.files[0].size > 204800) {
		//alert("File Size Upto 200 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 200 Kb!";
	}
	else if (size == "50kb" && val.files[0].size > 51200) {
		//alert("File Size Upto 50 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 50 Kb!";
	}
	else if (size == "20kb" && val.files[0].size > 20480) {
		//alert("File Size Upto 20 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 20 Kb!";
	}
	else if (size == "2kb" && val.files[0].size > 2048) {
		//alert("File Size Upto 2 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 2 Kb!";
	}else if (size == "15mb" && val.files[0].size > 15728640) {
		//alert("File Size Upto 2 MB!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 15 MB!";
	}else if (size == "1024kb" && val.files[0].size > 1048576) {
		//alert("File Size Upto 2 MB!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 1024 KB!";
	}
	else{
		document.getElementById(lbltik_id).innerHTML="<i class='fa fa-exclamationstik'></i>File Upload";
	}
}


//=============== image file and size validation ========================
//<input type="file" id="photographs" name="photographs" class="form-control" accept="image/*"  onchange="imgFileSizeValidation(this,this.value,"photographs","2kb");">


function imgFileSizeValidation(val,myfile,id,size,lbl_id) {
	debugger;
//	--10-04-2023-MONDAY
		var path = $("#photo_path").val();
		$("#upload_img_hid").val(path); 
//		---END 10-04-2023-MONDAY

	document.getElementById(lbl_id).innerHTML="";
	$("#"+lbl_id).addClass("errorClass");
		$("#"+lbl_id).removeClass("tikClass");
	var ext = myfile.split('.').pop();
	if(ext.toLowerCase() !="png" && ext.toLowerCase() !="jpg" && ext.toLowerCase() !="jpeg"){
		//alert('Please Only Allowed *.png/.jpeg/.jpg File Extension');
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>Please Only Allowed *.png/.jpeg/.jpg File Extension";
		}
	else if (size == "200kb" && val.files[0].size > 204800) {
		//alert("File Size Upto 200 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 200 Kb!";
	}
	else if (size == "50kb" && val.files[0].size > 51200) {
		//alert("File Size Upto 50 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 50 Kb!";
	}
	else if (size == "20kb" && val.files[0].size > 20480) {
		//alert("File Size Upto 20 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 20 Kb!";
	}
	else if (size == "2kb" && val.files[0].size > 2048) {
		//alert("File Size Upto 2 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 2 Kb!";
	}
	else{
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>Image Upload";
		$("#"+lbl_id).removeClass("errorClass");
		$("#"+lbl_id).addClass("tikClass");
		
	}
} 
////=============== image file and size validation ========================
////<input type="file" id="photographs" name="photographs" class="form-control" accept="image/*"  onchange="imgFileSizeValidation(this,this.value,"photographs","2kb");">
//function imgFileSizeValidation(val,myfile,id,size,lbl_id) {
//	document.getElementById(lbl_id).innerHTML="";
//	var ext = myfile.split('.').pop();
//	if(ext.toLowerCase() !="png" && ext.toLowerCase() !="jpg" && ext.toLowerCase() !="jpeg"){
//		//alert('Please Only Allowed *.png/.jpeg/.jpg File Extension');
//		$("#"+id).val("");
//		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>Please Only Allowed *.png/.jpeg/.jpg File Extension";
//		$("#upload_img_forV").val("1");
//	}
//	else if (size == "200kb" && val.files[0].size > 204800) {
//		//alert("File Size Upto 200 Kb!");
//		$("#"+id).val("");
//		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 200 Kb!";
//	}
//	else if (size == "50kb" && val.files[0].size > 51200) {
//		//alert("File Size Upto 50 Kb!");
//		$("#"+id).val("");
//		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 50 Kb!";
//	}
//	else if (size == "20kb" && val.files[0].size > 20480) {
//		//alert("File Size Upto 20 Kb!");
//		$("#"+id).val("");
//		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 20 Kb!";
//	}
//	else if (size == "2kb" && val.files[0].size > 2048) {
//		//alert("File Size Upto 2 Kb!");
//		$("#"+id).val("");
//		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 2 Kb!";
//	}
//}


//=============== Pan Card validation ========================
function isPAN(e) {    
	var inputvalues = e.value;      
	  var regex = /[A-Z]{5}[0-9]{4}[A-Z]{1}$/;    
	  if(!regex.test(inputvalues)){      
	  e.value="";   
	  alert("Invalid PAN Number");    
	  var iddd = e.id;
	  $("#"+iddd).focus();
	  }    
	}
	
	//======================== Validation For Only 0 to 9 accept with decimal

function isNumberKeydecimal(txt, evt) {
	
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode == 46) {
      //Check if the text already contains the . character
      if (txt.value.indexOf('.') === -1) {
        return true;
      } else {
        return false;
      }
    } else {
      if (charCode > 31 &&
        (charCode < 48 || charCode > 57))
        return false;
    }
    return true;
  }
  
  function isAlphaNumeric_SpecialsWithSpace(e){
		if($(e).val().length >1 && $(e).val().substring(parseInt($(e).val().length)-1) == " " 
			&& $(e).val().substring(parseInt($(e).val().length)-2, parseInt($(e).val().length)-1) == " "){
		$(e).val($(e).val().substring(0,parseInt($(e).val().length)-1));
		}
	}
	
//------CheckNullorBlank
function CheckNullorBlank(id) {
	var id = $('#' + id).val().trim();
	var message = "";
	if (id == "" || id == null || id == undefined || id == '0' || id == 'DD/MM/YYYY') {
		message = "Please Enter ";
	} else {
		message = "true"
	}
	return message;
}

//===placement

function companyimgFileSizeValidation(val,myfile,id,size,lbl_id) {
	document.getElementById(lbl_id).innerHTML="";
	var ext = myfile.split('.').pop();
	if(ext.toLowerCase() !="png" && ext.toLowerCase() !="jpg" && ext.toLowerCase() !="jpeg"){
		//alert('Please Only Allowed *.png/.jpeg/.jpg File Extension');
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>Please Only Allowed *.png/.jpeg/.jpg File Extension";
	$("#upload_img_forV").val("1");
	}
	else if(ext.toLowerCase() =="png" || ext.toLowerCase() =="jpg" || ext.toLowerCase() =="jpeg"){
		$("#upload_img_forV").val("0");
	}
	else if (size == "200kb" && val.files[0].size > 204800) {
		//alert("File Size Upto 200 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 200 Kb!";
	}
	else if (size == "50kb" && val.files[0].size > 51200) {
		//alert("File Size Upto 50 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 50 Kb!";
	}
	else if (size == "20kb" && val.files[0].size > 20480) {
		//alert("File Size Upto 20 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 20 Kb!";
	}
	else if (size == "2kb" && val.files[0].size > 2048) {
		//alert("File Size Upto 2 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 2 Kb!";
	}
}




function companyimgFileSizeValidationplacement(val,myfile,id,size,lbl_id) {
	document.getElementById(lbl_id).innerHTML="";
	var ext = myfile.split('.').pop();
	if(ext.toLowerCase() !="png" && ext.toLowerCase() !="jpg" && ext.toLowerCase() !="jpeg"){
		//alert('Please Only Allowed *.png/.jpeg/.jpg File Extension');
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>Please Only Allowed *.png/.jpeg/.jpg File Extension";
		$("#upload_img_forV").val("1");
	}
	else if(ext.toLowerCase() =="png" || ext.toLowerCase() =="jpg" || ext.toLowerCase() =="jpeg"){
		$("#upload_img_forV").val("0");
	}
	else if (size == "200kb" && val.files[0].size > 204800) {
		//alert("File Size Upto 200 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 200 Kb!";
	}
	else if (size == "50kb" && val.files[0].size > 51200) {
		//alert("File Size Upto 50 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 50 Kb!";
	}
	else if (size == "20kb" && val.files[0].size > 20480) {
		//alert("File Size Upto 20 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 20 Kb!";
	}
	else if (size == "2kb" && val.files[0].size > 2048) {
		//alert("File Size Upto 2 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 2 Kb!";
	}
}

function imgFileSizeCVValidation(val,myfile,id,size,lbl_id) {
	document.getElementById(lbl_id).innerHTML="";
	var ext = myfile.split('.').pop();
	if(ext.toLowerCase() !="pdf" && ext.toLowerCase() !="jar" && ext.toLowerCase() !="xlsx" && ext.toLowerCase() !="xls" ){
		//alert('Please Only Allowed *.png/.jpeg/.jpg File Extension');
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>Please Only Allowed *.pdf/.xlsx/.xls File Extension";
		$("#upload_cv_forV").val("1");
	}
	else if(ext.toLowerCase() =="pdf" || ext.toLowerCase() =="xls" || ext.toLowerCase() =="xlsx"){
		$("#upload_cv_forV").val("0");
	}
	else if (size == "200kb" && val.files[0].size > 204800) {
		//alert("File Size Upto 200 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 200 Kb!";
	}
	else if (size == "50kb" && val.files[0].size > 51200) {
		//alert("File Size Upto 50 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 50 Kb!";
	}
	else if (size == "20kb" && val.files[0].size > 20480) {
		//alert("File Size Upto 20 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 20 Kb!";
	}
	else if (size == "2kb" && val.files[0].size > 2048) {
		//alert("File Size Upto 2 Kb!");
		$("#"+id).val("");
		document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto 2 Kb!";
	}
	}
	
	var globalSpace="";
	function OnlyAlphaNumericTrim(e, t) {
	    try { 
		debugger;
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
		 	    	else if(charCode==32 && globalSpace==32){
	 	        		return false;
	 	        	}else if((charCode > 33 && charCode < 48) || (charCode > 57 && charCode < 65) || 
	 	        			(charCode > 90 && charCode < 97) || (charCode > 122 && charCode < 127)){
	 	        		return false;
	 	        	}else{
	 	        	 	globalSpace=charCode;
	 	             	return true;
	 	        	}
		    }
	    catch (err) {
	       // alert(err.Description);
	    }
	}
	
	
	
	//Curriculum No Space with special characters===================	
	function noSpace(e,t){
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
		 	    	else if(charCode==32 && globalSpace==32){
	 	        		return false;
	 	        	
	 	        	}else{
	 	        	 	globalSpace=charCode;
	 	             	return true;
	 	        	}
		    }
	    catch (err) {
	       // alert(err.Description);
	    }
	}
	
	//======================== Validation For String with space with Slash   
var globalSpace="";
function onlyAlphabetsStringSpacewithslash(e, t) {
	
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
	    
	    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode==32) || (charCode==47)){
	        	if(charCode==32 && globalSpace==32){
	        		return false;
	        	}
	        	else if(charCode==47 && globalSpace==47){
	        		return false;
	        	}
	        	else{
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

//============================== Validation For String and Number only AND Slash  
function onlyAlphaNumericwithslash(e, t) {	
    return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || (e.charCode >= 48 && e.charCode <= 57 ) || (e.charCode == 32) || (e.charCode == 47);   
}

/////////////// end
	
//	
//	function isCod(e) {    
////		debugger;
//	var inputvalues = e.value;      
//	  var regex = /[A-Z]{3}[0-9]{4}$/;    
//	  if(!regex.test(inputvalues)){      
//	  e.value="";   
//	  alert("Invalid Code Number");    
//	  var iddd = e.id;
//	  $("#"+iddd).focus();
//	  }    
//	}
//

//------CheckNullorBlank WITH Zero
function CheckNullorBlankZero(id) {
	var id = $('#' + id).val().trim();
	var message = "";
	if (id == "" || id == null || id == undefined || id == 'DD/MM/YYYY') {
		message = "Please Enter ";
	} else {
		message = "true"
	}
	return message;
}

  
