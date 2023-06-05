<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

  
	 <script type="text/javascript" src="admin/js/JS_CSS/wepe_cce.js"></script>
	<script type="text/javascript" src="admin/js/watermark/common.js"></script>

<link rel="stylesheet" href="admin/js/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="admin/js/assets/css/font-awesome.min.css">
<link href="admin/js/Calender/jquery-ui.css" rel="Stylesheet"></link>

<link rel="stylesheet" href="admin/js/Calender/Calender_jquery-ui.css">
<script src="admin/js/Calender/jquery-ui.js"></script>
<script src="admin/js/Calender/datePicketValidation.js"></script>

	
<style>

div#ui-datepicker-div {
/*     width: min-content !important; */
}

div#ui-datepicker-div table thead {
    width: 100% !important;
    background-color: #050d4f;
}
div#ui-datepicker-div table tbody {
    height: auto !important;
}
.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-all {
    background: #198754 !important;
}

.ui-datepicker {
    width: 23em;
    }
    
div#ui-datepicker-div {
    position: absolute;
    top: 390.141 px;
    left: 374.766 px;
    z-index: 1;
   /*  display: block; */
}

.card-header {
    border-bottom: 5px solid rgb(25 84 130);
    background-image: repeating-linear-gradient(to bottom right, #3f95ea 0%, #52d3aa 100%);
    text-align: center;
    color: white;
}

.card-footer {
    border-top: 5px solid rgb(25 84 130) !important;
    background-image: repeating-linear-gradient(to top left, #3f95ea 0%, #52d3aa 100%);
    text-align: center;
}

.inputNew{
	border: 0;
	background-color: transparent;
	width: 100%;
	text-align: center;
}
.table th,.table td{
   padding-left: 0;
   padding-right: 0;
   text-align: center;
}
.card-body.card-block .row {
    align-items: flex-start;
    justify-content: center;
}
.declaration p{
  color: #000;
  font-size: 13px;
  margin-bottom: 0;
  font-weight: bold;
  text-align: justify;
}
select#father_prefix {
    width: 20%;
    float: left;
    margin-right:10px;
}
select#candidate_prifix {
    width: 20%;
    float: left;
    margin-right:10px;
}
select#mother_prifix {
    width: 20%;
    float: left;
    margin-right:10px;
}

input#recr_father_name {
    width: 75%;
}
input#recr_name {
    width: 75%;
}
input#recr_mother_name {
    width: 75%;
}

</style> 
    
<form:form name="registration_Action" id="registration_Action" action="registration_Action" method="POST"  modelAttribute="registrationcmd">
<div class="container-fluid" align="center" style="width: 70%">
	<div class="card">
		<div class="card-header"> <h5>REGISTRATION</h5></div>
		<div class="card-body card-block"t>

			<div class="row" id="divname">
           		<div class="col-md-6 form-group">  
               	<label for="text-input" class=" form-control-label">Candidate Name<strong style="color: red;">*</strong>
               </label>
               </div>
               
           	    <div class="col-md-5 form-group">
           	     <select id="candidate_prifix" name="candidate_prifix" class="form-control" onchange="">
								<option value="0">--Select--</option>
								<option value="1"> Mr </option>
								<option value="2"> Mrs </option>
								<option value="3"> Ms </option>
<%-- 								<c:forEach var="item" items="${prifix}" varStatus="num"> --%>
<%-- 									<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 								</c:forEach> --%>
		             		</select>
		             		<form:input  id="recr_name" path="recr_name" class="form-control"  value="${recr_name_text}" maxlength="50"   minlength="3"  
		             		placeholder="Enter Candidate Name" onkeypress=" return onlyAlphabetsStringSpace(event,this);"  autocomplete="off"></form:input>
			    <span class="errorClass" id="recr_name_lbl"></span>  
			   <form:errors id="recr_name" name="recr_name" cssClass="errorClass1"></form:errors>
			   </div>
				</div>
				<div class="row" id="divfname">	
	             		<div class="col-md-6 form-group">
		                	<label for="text-input" class=" form-control-label">Father Name<strong style="color: red;">*</strong></label>
		                </div>
		                
	             		<div class="col-md-5 form-group">
	             		 <select id="father_prefix" name="father_prefix" class="form-control" onchange="">
								<option value="0">--Select--</option>
								<option value="1"> Mr </option>
<%-- 								<c:forEach var="item" items="${prifixmale}" varStatus="num"> --%>
<%-- 									<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 								</c:forEach> --%>
		             		</select>
		             		<form:input id="recr_father_name" path="recr_father_name" class="form-control" value="${recr_father_txt}"   minlength="3"  maxlength="50" 
		             		 autocomplete="off" placeholder="Enter Father Name" onkeypress="return onlyAlphabetsStringSpace(event,this)"></form:input>
							 <form:errors id="recr_father_name" name="recr_father_name" cssClass="errorClass1"></form:errors>
							 <span class="errorClass" id="recr_father_name_lbl"></span>
						</div>
				</div>
				<div class="row" id="divmname">	
	             		<div class="col-md-6 form-group">
		                	<label for="text-input" class=" form-control-label">Mother Name<strong style="color: red;">*</strong></label>
		                	 
		                </div>
	             		<div class="col-md-5 form-group">
	             		<select id="mother_prifix" name="mother_prifix" class="form-control" onchange="">
								<option value="0">--Select--</option>
								<option value="1"> Mrs </option>
<%-- 								<c:forEach var="item" items="${prifixfemale}" varStatus="num"> --%>
<%-- 									<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 								</c:forEach> --%>
		             		</select>
		             		 <form:input id="recr_mother_name" path="recr_mother_name" class="form-control"  minlength="3"  maxlength="50" 
		             		 autocomplete="off" placeholder="Enter Mother Name" onkeypress="return onlyAlphabetsStringSpace(event,this)"></form:input>
							 <form:errors id="recr_mother_name" name="recr_mother_name" cssClass="errorClass1"></form:errors>
							 <span class="errorClass" id="recr_mother_name_lbl"></span>
						</div>
	         	</div>
	         	<div class="row" id="divdob">
             		<div class="col-md-6 form-group">
	                	<label for="text-input" class=" form-control-label">Date of Birth<strong style="color: red;">*</strong>
	                	</label>
	                </div>
             		<div class="col-md-5 form-group">
             					<input type="text" name="recr_dob" id="recr_dob"
										maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
										class="form-control-sm form-control effect-9 " style="width: 83%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this); "
										aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"/>
           				<%-- <form:input type="date" id="recr_dob" path="recr_dob" class="form-control" placeholder="dd-mm-yyyy" min="1975-01-01"  max="2021-08-31"  ></form:input> --%>
           				
						 <form:errors id="recr_dob" name="recr_dob" cssClass="errorClass1"></form:errors>
						 <span class="errorClass" id="recr_dob_lbl"></span>
						<span class="errorClass" id="recr_dob_lbl_1"></span>
					</div>
	          	</div> 
	          	 <div class="row">	
             		<div class="col-md-6 form-group">
	                	<label for="text-input" class=" form-control-label">Aadhaar Number<strong style="color: red;">*</strong></label>
		                	  
	                </div>
             		<div class="col-md-5 form-group">
           					<input style="font-size: 14px;color: #030080;background-color: #e6f3ff;border: 1px solid #2e689f;padding: .375rem 0.25rem;border-radius: .25rem;width: 20%;"  id="pers_aadhar_no11" name="pers_aadhar_no11"  type="text" value="${pers_aadhar_no11_val}"  autocomplete="off"  maxlength="4"  onkeyup="movetoNext(this, 'pers_aadhar_no2'),lengthadhar()" onkeypress="return isNumberKey(event, this);"  >
							<input style="font-size: 14px;color: #030080;background-color: #e6f3ff;border: 1px solid #2e689f;padding: .375rem 0.25rem;border-radius: .25rem;width: 20%;"  id="pers_aadhar_no2" name="pers_aadhar_no2"  type="text"  value="${pers_aadhar_no2_val}" autocomplete="off"   maxlength="4"  onkeyup="movetoNext(this, 'pers_aadhar_no3'),lengthadhar()"  onkeypress="return isNumberKey(event, this);" >
							<input style="font-size: 14px;color: #030080;background-color: #e6f3ff;border: 1px solid #2e689f;padding: .375rem 0.25rem;border-radius: .25rem;width: 20%;"  id="pers_aadhar_no3" name="pers_aadhar_no3"  type="text" value="${pers_aadhar_no3_val}"  autocomplete="off"  maxlength="4" onkeyup="lengthadhar()" onkeypress="return isNumberKey(event, this);">
							<span class="errorClass" id="pers_aadhar_no_lbl">${pers_aadhar_no_msg}</span>
           					<label class="noteClass">[Note: 12 Digits]</label>
               		</div>
	          	</div>
				<div class="row" id="divemail">	
             		<div class="col-md-6 form-group">
	                	<label for="text-input" class=" form-control-label">Email Address<strong style="color: red;">*</strong>
	                </label></div>
             		<div class="col-md-5 form-group">
             		<form:input type="text" id="recr_email" path="recr_email" maxlength="70" onchange="checkgmail(this.value)" pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"  
           				 class="form-control" value="${email_txt}"  autocomplete="off"></form:input>
           				 <label class="noteClass">Ex. abc@example.com</label>	
           				  <form:errors id="recr_email" name="recr_email" cssClass="errorClass1"></form:errors>
           				  <span class="errorClass" id="recr_email_lbl"></span>
					</div>
	          	</div>
			 	<div class="row" id="divmb">	
	             		<div class="col-md-6 form-group">
		                	<label for="text-input" class=" form-control-label">Mobile Number<strong style="color: red;">*</strong>
		              </label>  </div>
	             		<div class="col-md-5 form-group">
	             		 <form:input id="recr_mobileno" path="recr_mobileno" class="form-control"  maxlength="10" 
               				  onkeypress="return isNumberKey(event, this);"  onchange="mobileNumber(this);"  autocomplete="off"></form:input>
							<label class="noteClass">[Note: 10 Digits]</label>
							<form:errors id="recr_mobileno" name="recr_mobileno" cssClass="errorClass1"></form:errors>
							<span class="errorClass" id="recr_mobileno_lbl"></span> 
						</div>
	          </div>
            	
		</div>
		<div class="card-footer" align="center">
          <i class="fa fa-save"></i><input type="submit" class="btn btn-primary btn-sm" value="Submit" onclick="return isValid();"  /> 
        	<i class="fa fa-trash-o"></i><input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="if (confirm('This will erase all the entered data, are you sure to proceed?') ){clearall();}else{ return false;}"/>
        	<i class="fa fa-home"></i><a href="login" class="btn btn-info btn-sm">Home</a>
        </div> 	
	</div>
</div>
</form:form>

<script type="text/javascript">
$(document).ready(function () {	 
	
	if('${msg}'!= ""){
		alert('${msg}');
	}
	 	
		datepicketDater('recr_dob');
		
		function datepicketDater(inpt){
			$('#'+inpt).datepicker({showOn: 'both', buttonImageOnly: true,
				buttonImage: 'admin/js/Calender/cal_ico.png',
				dateFormat: 'dd/mm/yy',
				changeMonth: true,
				changeYear: true,
				yearRange: '1890:2099',
				maxDate: new Date()
			});
			
			$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
		}
	
	$('#recr_name').keyup(function() {
       this.value = this.value.toUpperCase();
   	});
	$('#recr_father_name').keyup(function() {
       this.value = this.value.toUpperCase();
   	});
	$('#recr_mother_name').keyup(function() {
       this.value = this.value.toUpperCase();
   	});
	
	if('${recr_check_txt}' == "on"){
		 $("#recr_check").prop("checked", true);
	}
	   var recr_email = $(this).data('recr_email');        
	    if (recr_email != undefined && recr_email != null) {
// 	        window.location = 'recr_email=' + recr_email;
	    }
	 try{
			var m=document.getElementById("getmsg").value;
// 			if(m !=null)
// 			{
// 				 var url = window.location.href.split("?msg")[0];
// 				var mmm=window.location.href.split("?msg=")[1]; 
// 				window.location = url;	
				
// 			}		
	} 
	catch (e) {
		// TODO: handle exception
	} 		
}); 

 $(function(){
	
    var dtToday = new Date();
    var month = dtToday.getMonth() + 1;
    var day = dtToday.getDate();
    var year = dtToday.getFullYear()-18;

    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();

    var maxDate = year + '-' + month + '-' + day; 
    $('#recr_dob').attr('max', maxDate);   	
});

function clearall()
{	
	document.getElementById('mainForm1').submit();
}

function lengthadhar() {
	document.getElementById("pers_aadhar_no11").maxLength = "4";
	document.getElementById("pers_aadhar_no11").minLength = "4";
	document.getElementById("pers_aadhar_no2").maxLength = "4";
	document.getElementById("pers_aadhar_no2").minLength = "4";
	document.getElementById("pers_aadhar_no3").maxLength = "4";
	document.getElementById("pers_aadhar_no3").minLength = "4";
} 

function movetoNext(current, nextFieldID) {  
	if (current.value.length >= current.maxLength) {  
		document.getElementById(nextFieldID).focus();  
	}  
}

function isValidateClientSide()
{
	document.getElementById("recr_name_lbl").innerHTML="";
	document.getElementById("recr_father_name_lbl").innerHTML="";
	document.getElementById("recr_mother_name_lbl").innerHTML="";
	document.getElementById("recr_dob_lbl").innerHTML="";
	document.getElementById("pers_aadhar_no_lbl").innerHTML=""; 	
	document.getElementById("recr_dob_lbl_1").innerHTML="";
	document.getElementById("recr_email_lbl").innerHTML="";
	document.getElementById("recr_mobileno_lbl").innerHTML="";
	document.getElementById("recr_check_lbl").innerHTML=""; 
	
	var errCount=0;
	var a = document.createElement('a');
	
	
	if($("select#candidate_prifix").val() == "0")
    {
    	alert("Please Select Prefix For Candidate Name");
    	$("select#candidate_prifix").focus();
  		return false;
  	

    }
	
	if($("input#recr_name").val().trim() == "")
    {
  		alert("Please Enter Candidate Name");
  		$("input#recr_name").focus();
  		return false;
  	
  		errCount++;
    } 
	var maxLength = 50;
	 var charLength = $("input#recr_name").val().length;
	
       if(charLength > maxLength){
       	
       	alert(charLength)
       
       	alert("Please Enter Candidate Name should be less then 50 Characters");
			$("input#recr_name").focus();
			return false;
       }  
  	
  	
       var minlength = 3;
  	 var charLength = $("input#recr_name").val().length;
  	
         if(charLength < minlength){
         		alert("Please Enter Candidate Name should not be less then 3 Characters");
  			$("input#recr_name").focus();
  			return false;
         }  
  	 

    
  	if($("select#father_prefix").val() == "0")
    {
    	alert("Please Select Prefix For Father Name");
  		errCount++;
  		$("select#father_prefix").focus();
    	 return false;

    }
      
    if($("input#recr_father_name").val() == "")
    {
    	
    	alert("Please Enter Father Name");
    	$("input#recr_father_name").focus();
    	return false;

         errCount++;
    }
    var minlength = 3;
 	 var charLength = $("input#recr_father_name").val().length;
 	
       if(charLength < minlength){
       		alert("Please Enter Father Name should not be less then 3 Characters");
 			$("input#recr_father_name").focus();
 			return false;
       } 
       var maxLength = 50;
  	 var charLength = $("input#recr_father_name").val().length;
  	
         if(charLength > maxLength){
      	   
      	  
         	alert("Please Enter Candidate Name should be less then 50 Characters");
  			$("input#recr_father_name").focus();
  			return false;
         }
  
    if($("select#mother_prifix").val() == "0")
    {
    alert("Please Select Prefix For Mother Name");
    	$("select#mother_prifix").focus();
    	 return false; 
  		//errCount++;

    }
    if($("input#recr_mother_name").val() == "")
    {
    	alert("Please Enter Mother Name");
    	$("input#recr_mother_name").focus();
    	return false;
   
         errCount++;
    } 
    
    
    var maxLength = 50;
	 var charLength = $("input#recr_mother_name").val().length;
	
       if(charLength > maxLength){
    	   
    	   
       	alert("Please Enter Mother Name should be less then 50 Characters");
			$("input#recr_mother_name").focus();
			return false;
       }
       var minlength = 3;
    	 var charLength = $("input#recr_mother_name").val().length;
    	
          if(charLength < minlength){
          		alert("Please Enter Mother Name should not be less then 3 Characters");
    			$("input#recr_mother_name").focus();
    			return false;
          } 

    
    if($("input#recr_dob").val() == "")
    {
    	alert("Select Date of Birth");
    	$("input#recr_dob").focus();
    	return false;
    
         errCount++;
    }
    if($("input#pers_aadhar_no11").val() == "")
    {
    	alert("Please Enter Aadhar Number should be same as mentioned in Aadhar Card");
    	$("input#pers_aadhar_no11").focus();
    	return false;
    	
         errCount++;
    } 
    
    if($("input#pers_aadhar_no2").val() == "")
    {
    	alert("Please Enter Aadhar Number should be same as mentioned in Aadhar Card");
    	$("input#pers_aadhar_no2").focus();
    	return false;
    	
         errCount++;
    } 
    
    if($("input#pers_aadhar_no3").val() == "")
    {
    	alert("Please Enter Aadhar Number should be same as mentioned in Aadhar Card");
    	$("input#pers_aadhar_no3").focus();
    	return false;
    	
         errCount++;
    } 
   
    
    if($("#adhar_certy").val() == ""){
	 		alert("Please Upload Aadhar Certificate");
	 		 $("input#adhar_certy").focus();
	 	
	 return false;
	 	}
    if($("input#recr_email").val() == "")
    {
    	alert("Please Enter Email Address");
    	$("input#recr_email").focus();
    	return false;
    	
         errCount++;
    } 
    
    if($("input#recr_mobileno").val() == "")
    {
    	alert("Please Enter Mobile Number");
    	$("input#recr_mobileno").focus();
    	return false;
    	
         errCount++;
    } 
    
    if($('[name="recr_check"]:checked').length == 0)
	{
    	alert("Please Select Declaration Checkbox");
    	$("select#recr_check").focus();
    	return false;
    	
     	errCount++;
	}
    if(errCount > 0)
    	return false;	
   
   return true;	
}

</script>

<script>

function mobileNumber(obj){

	_mobile = obj.value;
	
	var regExp =/^[6789]\d{9}$/;
    if(_mobile == '' || !regExp.test(_mobile)){
        alert('Please Enter Number Start with 6,7,8,9 Digit');
        $('#'+obj.id).focus();
        $('#'+obj.id).val('');
        return false;
    }

}

function checkgmail(email1) {
	
	 document.getElementById("recr_email").innerHTML="";
	if(/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
		
	}else{
		
		alert("Please Enter Valid Email Address");
		$("input#recr_email").focus();
		
		$("input#recr_email").val('');
	        
		return false ;
	}
}  

 function isValid(){
	
	if ($("#candidate_prifix").val() == "0") {
		alert("Please Select the Candidate Prifix");
		$("#candidate_prifix").focus();
		return false;
	    }
	    
	if ($("#recr_name").val().trim() == "") {
		alert("Please Enter the Candidate Name");
		$("#recr_name").focus();
		return false;
	}


	if ($("#father_prefix").val() == "0") {
		alert("Please Select the Father Prifix");
		$("#father_prefix").focus();
		return false;
	    }
	    
	if ($("#recr_father_name").val().trim() == "") {
		alert("Please Enter the Father Name");
		$("#recr_father_name").focus();
		return false;
	}


	if ($("#mother_prifix").val() == "0") {
		alert("Please Select the Mother Prifix");
		$("#mother_prifix").focus();
		return false;
	    }
	    
	if ($("#recr_mother_name").val().trim() == "") {
		alert("Please Enter the Mother Name");
		$("#recr_mother_name").focus();
		return false;
	   }
		
	if ($("#recr_dob").val() == "" || $("#recr_dob").val() == "DD/MM/YYYY") {
		alert("Please Select the Date Of Birth");
		$("#recr_dob").focus();
		return false;
	}

	if($("#pers_aadhar_no11").val() == "")
	{
		alert("Please Enter Aadhar Number should be same as mentioned in Aadhar Card");
		$("#pers_aadhar_no11").focus();
		return false;
	} 
	if($("#pers_aadhar_no2").val() == "")
	{
		alert("Please Enter Aadhar Number should be same as mentioned in Aadhar Card");
		$("#pers_aadhar_no2").focus();
		return false;
	} 
	if($("#pers_aadhar_no3").val() == "")
	{
		alert("Please Enter Aadhar Number should be same as mentioned in Aadhar Card");
		$("#pers_aadhar_no3").focus();
		return false;
	}
	if($("#recr_email").val() == "")
	{
		alert("Please Enter Email Address");
		$("#recr_email").focus();
		return false;
	}
	if($("#recr_mobileno").val() == "")
	{
		alert("Please Enter Mobile Number");
		$("#recr_mobileno").focus();
		return false;
	}

	} 
</script>
