<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!-- single select, search with select start -->
<link href="admin/js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="admin/js/dropDown/select2.min.js" type="text/javascript"></script>
<!-- single select, search with select end -->

<!-- common style start -->
<link rel="stylesheet" href="admin/assets/vendor/common_custom_style.css">
<link rel="stylesheet" href="admin/assets/vendor/common_custom_responsive.css">
<!-- common style end -->

<section class="page-content">
<!--  Intro Single  -->
<section class="intro-single">
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-lg-8">
				<div class="title-single-box">
					<h1 class="title-single">Forgot Password</h1>
					<!--  <p>Enter your email address and we will send you a link to reset your password.</p> -->
				</div>
			</div>
			<div class="col-md-12 col-lg-4">
				<nav aria-label="breadcrumb"
					class="breadcrumb-box d-flex justify-content-lg-end">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="landingpage">Home</a></li>
						<li class="breadcrumb-item active" aria-current="page">
							Forgot Password</li>
					</ol>
				</nav>
			</div>
		</div>
	</div>
</section>
<!-- End Intro Single-->

<!--  Forgot Section  -->
<section class="section-fogot">
	<div class="container">
	<div class="form-main-block">
		
				<div class="row justify-content-center">
				<div class="col-lg-5 col-md-12 col-sm-12">
						<div class="area form-bg">
							<div class="form-intro white-text">
								<div class="text-welcome">
									<span class="img-intro"><img
										src="admin/assets/img/grid-logo.png" alt="Ayush Grid" title="Ayush Grid"></span>
									<h4 class="intro-title">Welcome to Ayush Education</h4>
								</div>
								<div class="login-part">
									<p>
										<a href="portalsignin"><i class="bi bi-chevron-left"></i> Back to Sign In</a>
									</p>
								</div>
							</div>
							<ul class="circles">
								<li><img src="admin/assets/img/ayushgridleaf.png" /></li>
								<li><img src="admin/assets/img/ayushgridleaf.png" /></li>
								<li><img src="admin/assets/img/ayushgridleaf.png" /></li>
								<li><img src="admin/assets/img/ayushgridleaf.png" /></li>
								<li><img src="admin/assets/img/ayushgridleaf.png" /></li>
								<li><img src="admin/assets/img/ayushgridleaf.png" /></li>
								<li><img src="admin/assets/img/ayushgridleaf.png" /></li>
								<li><img src="admin/assets/img/ayushgridleaf.png" /></li>
								<li><img src="admin/assets/img/ayushgridleaf.png" /></li>
								<li><img src="admin/assets/img/ayushgridleaf.png" /></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-5 col-md-12 col-sm-12">
						<div class="login-form-main form-min-height">
								
							<div class="col-lg-12 col-md-12 col-sm-12 mb-1">
								<div class="form-group">
									<label class=" form-control-label">Select Your
										Category <span class="mandatory">*</span>
									</label> <select id="category" name="category"
										class="singleselect form-control form-control-lg"
										aria-label=".form-select-lg example">
										<option value="0" id="0" name=" ">--Select--</option>
										<option value="ncism" id="ncism" name="ncism">NCISM</option>
										<option value="nch" id="nch" name="nch">NCH</option>
										<option value="institute" id="institute" name="institute">Institute</option>
<!-- 										<option value="state_council" id="state_council" name="council">State Council</option> -->
<!-- 										<option value="practitioner" id="practitioner" name="practitioner" >Practitioner</option> -->
														<option value="24" id="nchfact" name="practfactregistration">Faculty</option> 
														<option value="25" id="Student" name="Student">Student</option>
		<!-- 												<option value="35" id="Intern" name="Intern">Intern</option> -->
		<!-- 												<option value="Principal" id="Principal" name="Principal">Principal</option> -->
		<!-- 												<option value="University" id="University" name="University">University</option> -->
										
		<!-- 												<option value="board" id="board" name="ncism_council">Board</option> -->
										
		<!-- 												<option value="cour_pub" id="cour_pub" name="cour_pub">Course Publisher</option> -->
		<!-- 												<option value="alumni" id="alumni" name="alumni">Alumni</option> -->
		<!-- 												<option value="tpo" id="tpo" name="tpo">TPO</option> -->
		<!-- 												<option value="counselor" id="counselor" name="counselor">Counselor</option> -->
		
		
									</select>
								</div>
							</div>
						
							<form:form action="ForgotPassword_Action" class="login-form inputHeight custom-d-none" id="aadhar_dob_div">
								<div class="row">
									<div class="col-md-12 mb-1">
										<div class="form-group">
											<label for="email-1">Enter Addhaar No</label> <input
												type="text" name="aadhaar_no"
												class="form-control form-control-lg form-control-a"
												maxlength="12" placeholder="Enter Your Addhaar Number"
												id="aadhaar_no">
										</div>
									</div>
									<div class="col-md-12 mb-1">
										<div class="input-style-1">
											<label>Date Of Birth</label> <input type="date" name="dob"
												id="dob" maxlength="10"
												class="form-control form-control-lg form-control-a "
												aria-required="true" autocomplete="off" value="DD/MM/YYYY">
										</div>
									</div>
								</div>
								
								<div class="col-md-12 text-center">
									<button type="button" class="btn btn-a" id="btn-submit-adhar-dob"
										value="Reset Password">Submit</button>
								</div>
								
							</form:form>
							
							<form:form action="ForgotPassword_Action" class="login-form inputHeight custom-d-none" id="username_email_div">
							<div class="row" id="">
									<div class="col-md-12 mb-1">
										<div class="form-group">
											<label for="email-1">Enter Username <span class="mandatory">*</span></label> <input
												type="text" name="username_inst"
												class="form-control form-control-lg form-control-a"
												maxlength="70" placeholder="Enter Your Username"
												id="username_inst">
										</div>
									</div>
									<div class="col-md-12 mb-1">
										<div class="input-style-1">
											<label>Email Id <span class="mandatory">*</span></label> <input
												type="text" name="email_inst"
												class="form-control form-control-lg form-control-a"
												maxlength="70" placeholder="Enter Your Email Id"
												id="email_inst">
										</div>
									</div>
							</div>
							
							<div class="col-md-12 text-center">
								<button type="button" class="btn btn-a" id="btn-submit-username-email"
									value="Reset Password">Submit</button>
							</div>
							
							</form:form>
							
						</div>
					</div>
				</div>
			</div>
			</div>
		
</section>
<!--  End Forgot Section  -->
</section>
<div class="modal fade custom-modal forgot-pass-modal bd-example-modal-lg" tabindex="-1"
	role="dialog" aria-labelledby="myLargeModalLabel" id="modelWindow"
	aria-hidden="true">
	<div class="modal-dialog modal-md modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="myLargeModalLabel">Forgot Password</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body custom-modal-table">
				<div class="custom-modal-inner">
					
						<div class="col-12 col-sm-12 col-md-12 col-lg-12">
							<div class="inst_block">
								<h6 class="mb-1">Instruction</h6>
								<ul class="inst_list">
									<li><p class="inst_text">Password Should Be A Mix
											Of Alphabets, Numerals And Special Characters (
											$#^@\%_.~!*) Without Any Space In Between.</p></li>
									<li><p class="inst_text">Password Must Contain Both
											Upper And Lowercase Letters.</p></li>
									<li><p class="inst_text">Password Length Should Be
											Between 8 To 28 Characters.</p></li>
								</ul>
							</div>
						</div>

						<div class="row" id="m_aadhar_div">

							<div class="col-12 col-sm-12 col-md-6 col-lg-6">
								<div class="form-group mb-3">
									<label for="text-input">User Name<span
										class="mandatory">*</span></label> <input type="text"
										readonly="readonly" id="username" name="username"
										class="form-control form-control-lg form-control-a autocomplete UpperClassName txt-transupp"
										autocomplete="off" maxlength="28" />
								</div>
								<!-- end input -->
							</div>

							<div class="col-12 col-sm-12 col-md-6 col-lg-6">
								<div class="form-group mb-3">
									<label for="text-input">Aadhaar No<span
										class="mandatory">*</span></label> <input type="text"
										readonly="readonly" id="aadhaar" name="aadhaar"
										class="form-control form-control-lg form-control-a autocomplete UpperClassName txt-transupp"
										autocomplete="off" maxlength="28" />
								</div>
								<!-- end input -->
							</div>

						</div>
						
						<div class="row" id="m_username_div">

							<div class="col-12 col-sm-12 col-md-12 col-lg-12">
								<div class="form-group mb-3">
<!-- 									<label for="text-input" id="m_inst_name">Your Institute Name : </label> -->
								<p><b>Name: </b><span id="m_inst_name"></span></p>
<!-- 									<label for="text-input" id="m_inst_name"></label> -->
									<!-- <div class="note-text">
										<span class="mandatory">Please Varify Your Institute Name</span>
									</div> -->
								</div>
								<!-- end input -->
							</div>


						</div>

						<div class="row">

							<div class="col-12 col-sm-12 col-md-6 col-lg-6">
								<div class="form-group mb-3">
									<label for="text-input">New Password<span
										class="mandatory">*</span></label> <input type="password"
										id="new_pass" name="new_pass"
										class="form-control form-control-lg form-control-a autocomplete UpperClassName txt-transupp"
										pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!^\\&_.~*]).{8,28}$"
										autocomplete="off" maxlength="28"
										placeholder="Enter New Password" required />
								</div>
								<!-- end input -->
							</div>

							<div class="col-12 col-sm-12 col-md-6 col-lg-6">
								<div class="form-group mb-3">
									<label for="text-input">Confirm New Password<span
										class="mandatory">*</span></label> <input type="password"
										id="c_password" name="c_password"
										class="form-control form-control-lg form-control-a autocomplete UpperClassName txt-transupp"
										pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!^\\&_.~*]).{8,28}$"
										autocomplete="off" maxlength="28"
										placeholder="Enter Confirm New Password" required />
								</div>
								<!-- end input -->
							</div>
							<div class="col-lg-6 col-md-6 col-sm-12">
												<div class="form-group captchadiv enter_captcha">
													<label for="password">Captcha</label> <input type='text'
														class="form-control form-control-lg form-control-a disablecopypaste"
														size="35" id="txtInput" name="txtInput"
														placeholder="Enter Captcha" maxlength="5"
														autocomplete="off" />
												</div>
											</div>
											<div class="col-lg-6 col-md-6 col-sm-12">
												<div class="form-group captcha img_captcha">
													<div class="input-group input-group  align-items-center">
														<img id="capcha" src="genCapchaCode"
															class="form-control disablecopypaste p-0" /> <span
															class="input-group-btn">
															<button class="btn btn-b-n" id="btnrefresh" tabindex="-1"
																type="button">
																<i class="bi bi-arrow-clockwise"></i>
															</button>
														</span>
													</div>
												</div>
											</div>
						</div>


					
				</div>
				</div>
				<div class="modal-footer">
					<!-- 					<ul class="buttons-group"> -->
					<!-- 					 <li> -->
						
					
					
					<ul class="buttons-group">


										<li class="custom-d-none"><input type="hidden" id="user_id" name="user_id"
							class="autocomplete" autocomplete="off" maxlength="8" value="0" /></li>	
											<li><button type="button" class="main-btn success-btn btn-hover" id="btn-changepass"
							value="Reset Password">Reset Password</button></li>
							<li><button type="button" class="main-btn dark-btn btn-hover" data-bs-dismiss="modal"
							data-dismiss="modal" aria-label="Close" id="cancel-btn">Cancel</button></li>
										</ul>
										
<!-- 						<input type="hidden" id="user_id" name="user_id" -->
<!-- 							class="autocomplete" autocomplete="off" maxlength="8" value="0" /> -->
							
							

<!-- 						<button type="button" class="main-btn dark-btn btn-hover" id="btn-changepass" -->
<!-- 							value="Reset Password">Reset Password</button> -->
<!-- 														  </li> -->

<!-- 												<li> -->
<!-- 						<button type="button" class="btn btn-a" data-bs-dismiss="modal" -->
<!-- 							data-dismiss="modal" aria-label="Close" id="cancel-btn">Cancel</button> -->

					

					<!-- 								</li> -->
					<!-- 					</ul> -->
				</div>
			</div>
		</div>
	</div>




<c:url value="changepassbyforgot_Url" var="appUrl" />
<form:form action="${appUrl}" method="post" id="applicationForm4"
	name="applicationForm4" modelAttribute="user_id4">
	<input type="hidden" name="user_id4" id="user_id4" value="0" />
	<input type="hidden" name="new_pass4" id="new_pass4" value="0" />
	<input type="hidden" name="txtInput1" id="txtInput1" value="" />
</form:form>


<script nonce="${cspNonce}" type="text/javascript">
var csrfparname = "${_csrf.parameterName}";
var csrfvalue = "${_csrf.token}";

	jQuery(document).ready(function() {
		
		$("#aadhar_dob_div").hide();
		$("#username_email_div").hide();
		$("#m_aadhar_div").hide();
		$("#m_username_div").hide();
		
		
		$('#modelWindow').modal({
	           backdrop: 'static',
	           keyboard: false
	    })
		
		try {
			var msg = '${msg}';
			if (msg != null && msg != "") {
				alert(msg);
			}
		} catch (e) {
		}
		
		var course_name="";
		var setname="";
		
		function captcha() {
			$("#capcha").attr("src", "genCapchaCode");
		}
		function clear() {
			$("#txtInput").val("");
		}
		$("#btnrefresh").click(function() {
		    clear();
		    captcha();
		})
		
		if('${msg}' != ""){
			if(window.location.href.includes("msg=")){
	 			var url = window.location.href.split("?")[0];
	 			window.location.href=url;
	 		} 
		}
		
		
		
	});
	
	
// 	if (confirm('Are You Sure You Want to View Detail ?')) {
		
		
// 	} else {
// 		return false;
// 	}

document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('btn-submit-adhar-dob').onclick = function () {
			return isValid();
		};
		
		document.getElementById('cancel-btn').onclick = function () {
			$("#new_pass").val('');
			$("#c_password").val('');
			$("#txtInput").val('');
		};
		
		
		
		document.getElementById('btn-submit-username-email').onclick = function () {
			return isValid();
		};
		
	document.getElementById('btn-changepass').onclick = function() {
			return curlsubmit();
		};
		
		document.getElementById('category').onchange = function() {
			roleHide(this.value);
		};
		
		
// 		document.getElementById('aadhaar_no').onkeypress = function() {
// 			return isNumberKey0to9(event);
// 		};
		
// 		document.getElementById('aadhaar_no').onchange = function() {
// 			return checkLength(this.id,12);
// 		};
		
	});
	
function roleHide(val){

	if(val == "0"){
		$("#aadhar_dob_div").hide();
		$("#username_email_div").hide();
	}else if(val == "institute" || val == "ncism" || val == "nch" || val == "24" || val == "25"){
		$("#username_email_div").show();
		$("#aadhar_dob_div").hide();
// 		$("#m_aadhar_div").hide();
// 		$("#m_username_div").show();
	}else{
		$("#aadhar_dob_div").show();
		$("#username_email_div").hide();
// 		$("#m_aadhar_div").show();
// 		$("#m_username_div").hide();
	}
	
}
	

	// 
	function isValid() {
		
		var cat = $("#category").val();
		
		if(cat == "institute" || cat == "ncism" || cat == "nch" || cat == "24" || cat == "25"){
			
			if ($("#username_inst").val() == "") {
				alert("Please Enter Username");
				$("#username_inst").focus();
				return false;
			}
			if ($("#email_inst").val() == "") {
				alert("Please Enter Email Id");
				$("#email_inst").focus();
				return false;
			}
			callfnUsername();
			return true;
			
		}else{
		
			if ($("#aadhaar_no").val() == "") {
				alert("Please Enter Aadhaar No");
				$("#aadhaar_no").focus();
				return false;
			}
			if ($("#dob").val() == "" || $("#dob").val() == "DD/MM/YYYY" ) {
				alert("Please Enter Date Of Birth");
				$("#dob").focus();
				return false;
			}
			
			  var pin_pres = $("#aadhaar_no").val().trim();
				 
				 if(parseInt(pin_pres) <= 0){
						alert("Please Enter Valid Aadhaar No");
						$("#aadhaar_no").focus();
						return false;
				   	}
			
			var minLength = 12;
			 var charLength = pin_pres.length;
		       if(charLength < minLength){
		       	alert("Aadhaar No Should Contain Minimum 12 Digit");
					$("input#aadhaar_no").focus();
					return false;
		       } 
		       
			  var maxLength = 12;
				 var charLength = pin_pres.length;
			       if(charLength > maxLength){
			       	alert("Aadhaar No Should Contain Maximum 12 Digit");
						$("input#aadhaar_no").focus();
						return false;
			       } 
			
			callfn();
			return true;
		}
	}
	
	function callfnUsername() {
// 		debugger;
		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		var username = $("#username_inst").val();
		var email = $("#email_inst").val();
		var cat = $("#category").val();
		
		if(username == "" || email == "" || cat == "" || cat == "0"){
			alert("Please Fill all the details first");
		}else{
			$.post("forgotpassUsernameEmail?" + key + "=" + value, {
				username : username,email : email,cat : cat
			}, function(j) {
	// 			debugger;
				if(j[0][0] == "1"){
					$("#capcha").attr("src", "genCapchaCode");
					$('#modelWindow').modal('show');
					$("#m_username_div").show();
					$("#m_aadhar_div").hide();
					$("#m_inst_name").text(j[0][1]);
					$("#user_id").val(j[0][2]);
				}
				if(j[0][0] == "0"){
					alert(j[0][1]);
				}
			
			});
		}
	}

	function callfn() {
		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		var aadhaar_no = $("#aadhaar_no").val();

		$.post("ForgotPassword_ctrl?" + key + "=" + value, {
			aadhaar_no : aadhaar_no,
		}, function(j) {
			
		if (j[0][0] == "0") {
				Pop_Up_History1(j[0][1]);
				$("#user_id").val(j[0][1]);
				$("#username").val(j[0][2]);
				$("#aadhaar").val(j[0][3]);
			}
			if (j[0][0] == "1") {
				alert(j[0][1]);
			}
		});
	}

	function curlsubmit() {

		var new_pass = $("#new_pass").val();
		var c_password = $("#c_password").val();
		var u_id = $("#user_id").val();
		var pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!^\\&_.~*]).{8,28}$";
		
		if ($("#new_pass").val() == "") {
			alert("Please Enter New Password");
			$("#new_pass").focus();
			return false;
		}
		
		if (new_pass.match(pattern) == null) {
			alert("New Password doesn't matches to pattern");
			$("#new_pass").focus();
			return false;
		}
		
		if ($("#c_password").val() == "") {
			alert("Please Enter Confirm New Password");
			$("#c_password").focus();
			return false;
		}
		
		if (c_password.match(pattern) == null) {
			alert("Confirm Password doesn't matches to pattern");
			$("#new_pass").focus();
			return false;
		}

		if (new_pass == c_password) {
			var iCapcha = removeSpaces(jQuery('#txtInput').val());
			if (iCapcha == "" || iCapcha.length != 5) {
				alert("Enter valid Captcha!");
				jQuery('#txtInput').focus();
				return false;
			}
			if (iCapcha != "") {
				var test = ValidCaptcha(iCapcha);
				if (test != "0") {
								$("#new_pass4").val(new_pass);
								$("#user_id4").val(u_id);
								$("#txtInput1").val($("#txtInput").val());
								
								document.getElementById("applicationForm4").submit();
						    	return true;
				} else {
					alert("Captcha Validation failed!");
					jQuery('#txtInput').focus();
					return false;
				}
			}
			
		} else {
			alert("Confirm Password Does Not Match with New Password");
			$("#c_password").focus();
			return false;
		}

	}
	function removeSpaces(string) {
		return string.split(' ').join('');
	}
	function ValidCaptcha(iCapcha) {
		var test = "0";
		try {
			$.ajax({
				url : "checkCapchaCode?" + csrfparname + "=" + csrfvalue,
				type : 'POST',
				data : {
					iCapcha : iCapcha
				},
				success : function(data) {
					if (data) {
						test = data;
					}
				},
				async : false,
			});
		} catch (err) {
			console.log(err.message);
		}
		return test;
	}
	
	function Pop_Up_History1(userid) {

		$("#new_pass").val('');
		$("#c_password").val('');
		// 			$.post("getusername_ctrl?" + key + "=" + value, {
		// 			aadhaar_no : aadhaar_no,
		// 		}, function(j) {
		// 			if (j.length < 50) {
		// 	 			Pop_Up_History1(j);	
		// 	 			 $("#user_id").val(j);
		// 			}else {
		// 				alert(j);
		// 			}
		// 		});
		$('#modelWindow').modal('show');
	}

	/* Validation for 
	function Validation() {
	if ($("#new_pass").val().trim() == "0") {
		alert("Please Enter New Password.");
		$("input#new_pass").focus();
		return false;
	}
	function Validation() {
		if ($("#c_password").val().trim() == "0") {
			alert("Please Enter Confirm New Password.");
			$("input#c_password").focus();
			return false;
		}
	} */
</script>
