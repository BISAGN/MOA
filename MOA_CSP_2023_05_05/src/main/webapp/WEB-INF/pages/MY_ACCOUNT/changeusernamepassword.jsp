

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>

<title>Ministry of Ayush</title>

 
<script>
	var username = "${username}";
</script>
<style>
.sidebar-nav-wrapper {
    -webkit-transition: none;
    -moz-transition: nones;
    -ms-transition: nones;
    -o-transition: none;
    transition: none;
    transition-property: none;
    transition-duration: none;
    transition-timing-function: none;
    width: 0;
    opacity: 0; 
}
.main-wrapper {
     -webkit-transition: none;
    -moz-transition: nones;
    -ms-transition: nones;
    -o-transition: none;
    transition: none;
}
</style>
</head>
<section class="dashboard-page edu_fill_dtl">
      <div class="container-fluid">
        <!-- title-wrapper start -->
        <div class="title-wrapper pt-30">
          <div class="row align-items-center">
            <div class="col-md-6">
              <div class="title mb-30">
                <h2> change username and password</h2>
              </div>
            </div>
            <!-- end col -->
            <div class="col-md-6">
              <div class="breadcrumb-wrapper mb-30">
                <nav aria-label="breadcrumb">
                  <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                      <a href="#0">Start View</a>
                    </li>
<!--                     <li class="breadcrumb-item"><a href="#0">Regulation Forms</a></li> -->
                    <li class="breadcrumb-item active" aria-current="page">
                    change username and password
                    </li>
                  </ol>
                </nav>
              </div>
            </div>
            <!-- end col -->
          </div>
          <!-- end row -->
        </div>
        <!-- title-wrapper end -->
        
             <div class="search-regulation-wrapper">
          <div class="row">
          
          <div class="col-lg-12">
              
                   <form:form name="changeunPass" id="changeunPass"  action="changeusernamePassword_Action" method='POST'> 
					<div class="card-style mb-30">
					<h6 class="mb-25">Change Password</h6>
						<div class="row align-items-center">
								
								
								
							<div class="col-12 col-sm-12 col-md-6 col-lg-6">
								<div class="input-style-2">
									<label for="username">Name</label>
		                        	<span class="auto-input">${userDetails.login_name}</span>
		                       	</div>
								<!-- end input -->
								<span class="mandatory" id ="namecheck"></span>
							</div>

							<div class="col-12 col-sm-12 col-md-6 col-lg-6">
								
								<div class="input-style-2">
									<label for="text-input">User Name <span class="mandatory">*        Note: This Username Used for Further Login Process</span></label>
									<input type="text" id="username" name="username" class="" 
										autocomplete="off" maxlength="28" placeholder="Enter Username" />
										<span class="mandatory" id ="uncheck"></span>
								</div>
								
<!-- 								<div class="input-style-2"> -->
<!-- 									<label for="username">User Name</label> -->
<%-- 		                        	<span class="auto-input">${userDetails.userName}</span> --%>
<!-- 		                       	</div> -->
								<!-- end input -->
							</div>
	
<!-- 							<div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
<!-- 								<div class="input-style-2"> -->
<!-- 									<label for="text-input">Old Password<span class="mandatory">*</span></label> -->
<!-- 									<input type="password" id="old_pass" name="old_pass" class="autocomplete UpperClassName txt-transupp" -->
<!-- 										autocomplete="off" maxlength="28" placeholder="Enter Old Password" required/> -->
<!-- 								</div> -->
<!-- 								end input -->
<!-- 							</div> -->

							<div class="col-12 col-sm-12 col-md-6 col-lg-6">
								<div class="input-style-2">
									<label for="text-input">New Password<span class="mandatory">*</span></label>
									<input type="password" id="new_pass" name="new_pass" class="autocomplete UpperClassName txt-transupp" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!^\\&_.~*]).{8,28}$" 
										autocomplete="off" maxlength="28" placeholder="Enter New Password" required/>
										
								</div>
								<!-- end input -->
							</div>
                       		
							<div class="col-12 col-sm-12 col-md-6 col-lg-6">
								<div class="input-style-2">
									<label for="text-input">Confirm New Password<span class="mandatory">*</span></label>
									<input type="password" id="c_password" name="c_password" class="autocomplete UpperClassName txt-transupp" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!^\\&_.~*]).{8,28}$"
										autocomplete="off" maxlength="28" placeholder="Enter Confirm Password" required/>
								</div>
								<!-- end input -->
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-6">
								<div class="input-style-2">
									<label for="passid">
			                   			<b>1) Password should be a mix of alphabets, numerals and special characters ( $#^@\%_.~!*) without any space in between.</b><br>
										<b>2) Password must contain both upper and lowercase letters.</b><br>
										<b>3) Password length should be between 8 to 28 characters.</b>
									</label>
								</div>
								<!-- end input -->
							</div>
						</div>		

						<ul class="buttons-group mainbtn">
							 <li>
								<input type="submit" id="btn-save" class="main-btn info-btn btn-hover" value="Submit" onclick="return isValid();">
							</li> 
							
						</ul>
				</div>
				</form:form>
            </div>
          </div>
          <!-- end row -->
        </div>

	 <br>
 
       
        
            </div>
        </section>
<script>

//-----------------------------23-06-22 urmik autocomplete
$(document).ready(function() {
	
	
    $("#menu-toggle").hide();
	$(".sidebar-nav-wrapper").hide();
	$(".main-wrapper").css("margin-left","0"); 
	
});

</script>

<script>






function isValid(){ 
	
// 	if ($("#ayush_id").val().trim() == "") {
// 		alert("Please Enter Ayush Id");
// 		$("input#ayush_id").focus();
// 		return false;
// 	}
	
	if ($("#username").val().trim() == "") {
		alert("Please Enter User Name");
		$("input#username").focus();
		return false;
	}
	
// 	if ($("#email_id").val().trim() == "") {
// 		alert("Please Enter Email Id");
// 		$("input#email_id").focus();
// 		return false;
// 	}
	
	if ($("#new_pass").val().trim() == "") {
		alert("Please Enter New Password");
		$("input#new_pass").focus();
		return false;
	}
	
	if ($("#c_password").val() == "") {
		alert("Please Enter Confirm New Password");
		$("#c_password").focus();
		return false;
	}
}

document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('btn-save').onclick = function() {
		return isValid();
	};
	
	document.getElementById('username').onkeyup = function() {
		removewhitespace(this);
		return	usernamevalid(this.value);
	};

});

function removewhitespace(val) {
	var x = val.value.split(' ').join('');
	$("#"+val.id).val(x);
}


function usernamevalid(val) {
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
 	var newusername = val;
	   
	$.post("newusernamevalidfetch_ctrl?" + key + "=" + value, { newusername : newusername}, function(j) {
		 
		if (j > 0) {
			$("#uncheck").text("This Username Already Used");
// 			$("#regTitle").html("Hello World");
 		}
		if (j == 0) {
			$("#uncheck").text("");
		}
		 
	});
}

</script>
</body>
</html>