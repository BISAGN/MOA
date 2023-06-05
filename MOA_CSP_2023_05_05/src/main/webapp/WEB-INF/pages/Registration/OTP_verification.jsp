<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
    <script type="text/javascript" src="admin/js/amin_module/rbac/jquery-1.12.3.js"></script> 
<link rel="stylesheet" href="">
<!-- <script src="admin/js/cue/cueWatermark.js" type="text/javascript"></script> -->
	<link rel="stylesheet" href="admin/js/assets/css/bootstrap.min.css">
  	<link rel="stylesheet" href="admin/js/assets/css/font-awesome.min.css">
    
    <style>
    
    .card-header {
    border-bottom: 5px solid rgb(25 84 130);
    background-image: repeating-linear-gradient(to bottom right, #3f95ea 0%, #52d3aa 100%);
    text-align: center;
    color: white;
}

/* .card-footer {
    border-top: 5px solid rgb(25 84 130) !important;
    background-image: repeating-linear-gradient(to top left, #3f95ea 0%, #52d3aa 100%);
    text-align: center;
} */

 #card-footer i { 
 	position: absolute; 
 	color: #fff; 
 	bottom: 28px;
 	margin-left: 8px; 
} 

#card-footer input[type="button"]{
    padding-left: 25px !important;
} 
 #card-footer .btn { 
   padding: 3px 15px; 
    margin-right: 5px; 
    border-radius: 5px; 
    box-shadow: 1px 1px 2px 1px #030; 
 }  
</style>

<script type="text/javascript">
window.history.forward();
function noBack() {
	window.history.forward();
} 
$(document).ready(function () {
	
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	 
	$("div#divPwd").hide();
	$("div#passdivn").hide();  // label change in pass
	$("div#otpdivn").show();  // label change in otp
  	$("div#divrepwd").hide();
  	$("div#divnote").hide();
	$("div#divsubmit").hide();
	
   if($("input#verifyotpri").val() != "") {
   	$("div#divPwd").show();
  	$("div#divrepwd").show();
  	$("div#divnote").show();
	$("div#divotp").hide();
	$("div#divotpbtn").hide();
	$("div#divsubmit").show();
	$("div#passdivn").show(); //label change in pass
	$("div#otpdivn").hide(); 
     }
	try{
		var m=document.getElementById("msg").value;
		if(m !=null)
		{
			var url = window.location.href.split("?msg")[0];
			var mmm=window.location.href.split("?msg=")[1]; 
			document.getElementById('mainForm').submit(); 
		}
	} 
	catch (e) {
		// TODO: handle exception
	}  
}); 

</script>
</head>
<body>

<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" />
</c:if>

<c:url value="otpveriUrl" var="otpUrl" />
<form:form action="${otpUrl}"  id="mainForm"  name="mainForm" modelAttribute="email">
</form:form> 

<br>
      
 <form:form name="otp_veri" id="otp_veri" action="otp_veriAction" method="POST"  commandName="otp_vericmd" >
<div class="container-fluid" align="center" style="width: 50% !important;" >
	<div class="card">
		<div class="card-header" id="otpdivn" style="display: none"> <h5>OTP Verification</h5></div>
		<div class="card-header" id="passdivn" style="display: none"> <h5>Set Your Password</h5></div>
		<div class="card-body card-block">
		
			<div class="col-md-12" id="divotp">	
				<div class="col-md-11">
	             	<div class="row form-group"> 
	             		<div class="col col-md-6">
	             		<input type="hidden" id="email1" name="email1" class="form-control" autocomplete="off" value="${email1}"/>
               				<input type="hidden" id="candiatename1" name="candiatename1" class="form-control" autocomplete="off" value="${candiatename1}" />
		                	<input type="hidden" id="pers_aadhar_nocom1" name="pers_aadhar_nocom1" class="form-control" autocomplete="off" value="${pers_aadhar_nocom1}" />
		                	<label for="text-input" class=" form-control-label">Email OTP <strong style="color: red;">*</strong></label>
		                </div>
	             		<div class="col col-md-6 ">
               				<input type="password" id="recr_otp" name="recr_otp" class="form-control" autocomplete="off" value="${recr_otp1}" maxlength="4" onkeypress="" placeholder="&#xf023; Enter OTP" style="font-family:Arial, FontAwesome;"/>
               			<!-- 	<input type="hidden" id="getOTPNo" class="form-control" autocomplete="off" value="3181"/> -->
               			<lable>${cotpSession1}</lable>
               			
               				<span class="errorClass" id="getOTPNo_lbl"></span>
						</div>
	             	</div>
             	</div>
           	</div> 
           	
           	<div class="col-md-12" align="center" id="divotpbtn" >	
				<div class="col-md-11" align="right" id="card-footer">
	             		 <input type="hidden" id="verify" name="verify" class="form-control" />
	             		<i class="fa fa-check"></i>
	             		<input type="button" class="btn btn-primary btn-sm"  id = "veributton" name = "verifyb" value="Verify" onclick="return verifyotp();" >
	             		<i class="fa fa-repeat"></i><input type="button" class="btn btn-success btn-sm"  id = "resend" name = "resend" value="Resend OTP"  onclick="return resendOtp();">
             			<i class="fa fa-times"></i><input type="button" class="btn btn-danger btn-sm" value="Cancel" onclick="if (confirm('Are you sure want to cancel registration?') ){clearall();}else{ return false;}">
             	<br/><br/>
             	</div> 
           	</div>  

		<div class="col-md-12" id="divPwd">	
				<div class="col-md-11">
	             	<div class="row form-group"> 
	             		<div class="col col-md-6">
		                	<label for="text-input" class=" form-control-label">Password <strong style="color: red;">*</strong></label>
		                </div>
	             		<div class="col col-md-6 ">
	             		 <input type="hidden" id="verifyotpri" name="verifyotpri"  value="${verifyotpri}"/>	             		
	             		<input type="hidden" id="gettype" name="gettype" value="${type1}">
 							 <input type="password" id="recr_pwd" name="recr_pwd" class="form-control" 
 							 pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$#^@&%_.~!*])(?!.*\\s)(?!.*\\).{8,28}"   maxlength="28" autocomplete="off"
 							  required placeholder="&#xf084; Enter Password" style="font-family:Arial, FontAwesome;" />
							<span class="noteClass" id="">Ex. Abc@1234</span>
							<span class="errorClass" id="recr_pwd_lbl">${recr_pwd_lbl}</span>
							<span class="errorClass">${pwdtxt_msg}</span>
						</div>
	             	</div>
             	</div>
           	</div> 
           <div class="col-md-12" id="divrepwd">	
				<div class="col-md-11">
	             	<div class="row form-group"> 
	             		<div class="col col-md-6">
		                	<label for="text-input" class=" form-control-label">Confirm Password <strong style="color: red;">*</strong></label>
		                </div>
	             		<div class="col col-md-6 ">
               				 <input type="password" id="recr_repwd" name="recr_repwd" class="form-control" 
               				 pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$#^@&%_.~!*])(?!.*\\s)(?!.*\\).{8,28}" maxlength="28" autocomplete="off" 
               				 required placeholder="&#xf084; Enter Confirm Password" style="font-family:Arial, FontAwesome;"/> 
               			
               				 <span class="errorClass" id="recr_repwd_lbl">${recr_repwd_lbl}</span>
						</div>
						
	             	</div>
             	</div>
           	</div> 
	
	<div class="col-md-12" align="left" id="divnote">
                   		<span class="line"></span>
                   	</div>
				<div class="col-md-12" align="left" id="divnote">
                   		<label for="passid" style="color:red; text-align: justify;">
                   			1) Password should be a mix of alphabets, numerals and special characters [$#^@&%_.~!*] without any space in between.<br>
							2) Password must contain both upper and lower case letters.<br>
							3) Password length should be between 8 to 28 characters.
						</label>
                   </div>
		</div>
		
		<div class="card-footer" align="center" id="divsubmit"> 
           		<i class="fa fa-save"></i><input type="submit" class="btn btn-primary btn-sm" value="Submit" onclick="return isValidateClientSide();" >
           		<i class="fa fa-times"></i><input type="button" class="btn btn-danger btn-sm" value="Cancel" onclick="if (confirm('Are you sure want to cancel registration?') ){clearall();}else{ return false;}">
        </div> 	
	</div>
</div>
</form:form>
<c:url value="registrationUrl" var="mainFormUrl1" />
<form:form action="${mainFormUrl1}"  id="mainForm1" name="mainForm1" ></form:form>

<script>

function clearall()
{	
	document.getElementById('mainForm1').submit();
}

function verifyotp()
{	
	document.getElementById("getOTPNo_lbl").innerHTML="";
 	var otp=document.getElementById("recr_otp");
 	if(otp.value == "")
 	{
 		document.getElementById("getOTPNo_lbl").innerHTML="<i class='fa fa-exclamation'></i>Please Enter OTP";
		otp.focus();
		return false;
 	} 
	
 	else{
 		
 		var cotp=otp.value;
		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		
		$.ajaxSetup({
		    async: false
		});
		$.getJSON("checkOtpEmail", {cotp:cotp}, function(j) {	
			 debugger;
		
			if(j == '2')
			{
				document.getElementById("getOTPNo_lbl").innerHTML="<i class='fa fa-exclamation'></i>Please Enter Correct OTP";
				otp.focus();
				return false;
			}
			else
			{
				$("div#divPwd").show();
			  	$("div#divrepwd").show();
			  	$("div#divnote").show();
				$("div#divotp").hide();
				$("div#divotpbtn").hide();
				$("div#divsubmit").show();
				$("div#passdivn").show(); //label change in pass
				$("div#otpdivn").hide();  // label chnage in otp
			  	$("input#verify").val("verify");
			  	return true;
			} 
		});	
		$.ajaxSetup({
		    async: false
		});
	}
	
 return true;
}
</script>
<script>
function isValidateClientSide()
{
	var errCount=0;
	document.getElementById("getOTPNo_lbl").innerHTML="";
	document.getElementById("recr_pwd_lbl").innerHTML="";
	document.getElementById("recr_repwd_lbl").innerHTML="";
   if($("input#recr_otp").val() == "")
   {
	   document.getElementById("getOTPNo_lbl").innerHTML="<i class='fa fa-exclamation'></i>Please Enter OTP";
	   
		 errCount++;
   }  
  else{ 
	   if($("input#recr_pwd").val() == "")
	    {
	       document.getElementById("recr_pwd_lbl").innerHTML="<i class='fa fa-exclamation'></i>Please Enter Password";
		   errCount++;
	    } 
	   
	    if($("input#recr_repwd").val() == "")
	    {
	       document.getElementById("recr_repwd_lbl").innerHTML="<i class='fa fa-exclamation'></i>Please Enter Confirm Password";
	       errCount++;
	    } 
	    if($("input#recr_pwd").val() != $("input#recr_repwd").val())
	    {
	       document.getElementById("recr_repwd_lbl").innerHTML="<i class='fa fa-exclamation'></i>Password and Confirm Password Didn't Match";
	       errCount++;
	    } 
  } 
  if(errCount > 0)
		return false;
	
  return true;	
} 
//-------- resent otp -----------
// function resendOtp() {
// 	 var key = "${_csrf.parameterName}";
// 	 var value = "${_csrf.token}";
// 	document.getElementById("getOTPNo_lbl").innerHTML="";
// 	if('${email1}' != "" && '${candiatename1}' != "")
// 	{
// 		$.getJSON("checkOtpEmail", {cotp:cotp}, function(j) {			
// 			if(j == '2')
// 			{
// 				document.getElementById("getOTPNo_lbl").innerHTML="<i class='fa fa-exclamation'></i>Please Enter Correct OTP";
// 				otp.focus();
// 				return false;
// 			}
// 			else
// 			{
// 				$("div#divPwd").show();
// 			  	$("div#divrepwd").show();
// 			  	$("div#divnote").show();
// 				$("div#divotp").hide();
// 				$("div#divotpbtn").hide();
// 				$("div#divsubmit").show();
// 				$("div#passdivn").show(); //label change in pass
// 				$("div#otpdivn").hide();  // label chnage in otp
// 			  	$("input#verify").val("verify");
// 			  	return true;
// 			} 
// 		});
// 	 	return true;
// 	} 
// 	else
// 		return false;
// 	return true;
// }

function resendOtp() {
	
	window.location.href = "otpveriUrl";
	}
</script>

    