<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
						<h1 class="title-single">Sign In</h1>
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="landingpage">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">Sign
								In</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Intro Single-->

	<!--  Register Section  -->
	<section class="section-register">
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
										New here? <a href="institute_registration_url"
											data-bs-toggle="modal" data-bs-target="#exampleModal">Sign
											Up</a>
									</p>
									<p>
										Forgot your password? <a href="portalforgotpassword">click
											here</a>
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

							<form role="form" name='loginForm' action="#" method='POST'
								id="myFormId" class="login-form inputHeight form-inner-common">
								<!-- /login?error=true -->
<%-- 								<c:if test='<%=request.getParameter("error") != null%>'> --%>

									<div id="login_fail" class="custom-d-none" >
																			
										<div class="alert alert-danger alert-dismissible">
										
<!-- 								<button type="button" class="btn-close" data-bs-dismiss="alert"></button> -->
										<strong>Login Failed!</strong>

										<%--   <c:if test="${#session!= null and #session.getAttribute('SPRING_SECURITY_LAST_EXCEPTION') != null}"> --%>
<%-- 										<c:if	test='<%=session != null && session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") != null%>'> --%>
											<span id="error_msg">${login_error}</span>
<%-- 											<span>Your credentials is incorrect. Please try again. </span> --%>
<%-- 										</c:if> --%>
                                         </div>
									</div>
<%-- 								</c:if> --%>
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 mb-1">
										<div class="form-group">
											<label class=" form-control-label" for="practfactnch">Select Your
												Category <span class="mandatory">*</span>
											</label> <select id="practfactnch" name="practfactnch"
												class="singleselect form-control form-control-lg"
												aria-label=".form-select-lg example">
												<option value=" " id=" " name=" ">--Select--</option>
												<option value="ncism_council" id="ncism_council" name="ncism_council">NCISM</option>
												<option value="nch_council" id="nch_council" name="nch_council">NCH</option>
												<option value="Institute" id="Institute" name="Institute">Institute</option>
												<option value="council" id="council" name="council">State Council</option>
												<option value="27" id="nchpract" name="practfactregistration" >Practitioner</option>
												<option value="24" id="nchfact" name="practfactregistration">Faculty</option> 
												<option value="25" id="Student" name="Student">Student</option>
												<option value="35" id="Intern" name="Intern">Intern</option>
												<option value="Principal" id="Principal" name="Principal">Principal</option>
												<option value="University" id="University" name="University">University</option>
												
												<option value="board" id="board" name="ncism_council">Board</option>
												
												<option value="cour_pub" id="cour_pub" name="cour_pub">Course Publisher</option>
												<option value="alumni" id="alumni" name="alumni">Alumni</option>
												<option value="tpo" id="tpo" name="tpo">TPO</option>
												<option value="counselor" id="counselor" name="counselor">Counselor</option>


											</select>
										</div>
									</div>
								</div>
								<div class="custom-d-none" id="new_pract">
								<div class="row"  >
									<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 mb-1">
										<div class="form-group">
											<label for="newusername" id="adn">Aadhaar No<span class="mandatory">*</span></label> <input type="text"
												id="newusername" name='newusername' maxlength="14"
												autocomplete="off"
												class="form-control form-control-lg form-control-a disablecopypaste"
												placeholder="Enter Aadhaar No">
										</div>
									</div>

<!-- 									<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 mb-1"> -->
<!-- 										<div class="form-group"> -->
<!-- 											<label for="mobile">Mobile No</label> <input type="text" -->
<!-- 												id="newmobile_no" name='newmobile_no' -->
<!-- 												class="form-control form-control-lg form-control-a disablecopypaste" -->
<!-- 												placeholder="Your Mobile No" maxlength="10" -->
<!-- 												autocomplete="off" readonly='true' value="98XXXXX58"> -->
<!-- 										</div> -->
<!-- 									</div> -->

									<!-- 	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 mb-1  text-center">
											<button type="button" id="gtOTP" name="gtOTP"
												class="btn btn-a ghost">Get-OTP</button>

										</div> -->
										
	
											
                                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 mb-1">
										<div id="otpdiv">									
											<div class="form-group">
											<label for="newpassword">OTP<span class="mandatory">*</span></label>
												<div class="input-group custom-input-group">											 
													<button type="button" name="gtOTP" class="btn btn-a"
														id="gtOTP">Get-OTP</button>
														<input id="newpassword" name="newpassword"
														class="otp form-control form-control-lg form-control-a disablecopypaste"
														maxlength=6 placeholder="Enter OTP">
														<input type="hidden" id="fortimer" name="fortimer" value="0">
												</div>											
													<div class="note-text" id="resendotpdiv"><p class="input-text-below">Resend OTP?<a type="button" id="click_here"> Click Here</a></p></div>	
													<div class="note-text" id="timerotpdiv"><p id="demo" class="input-text-below mandatory"></p></div>																							
											</div>
											</div>
											
									<!-- <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 mb-1 "  >
										<div class="form-group">
											<label>Enter OTP</label>
										</div>
										<input id="newpassword" name="newpassword"
											class="otp form-control form-control-lg form-control-a disablecopypaste"
											maxlength=6>

									</div> -->
									</div>

									

								</div>
								</div>

								<!-- 								/////////////////////// -->
								<div class="custom-d-none" id="council_login">
								<div class="row " >

									<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 mb-1">
										<div class="form-group">
											<label for="username">Username<span class="mandatory">*</span></label> <input type="text"
												id="username" name='username' maxlength="30" size="35"
												autocomplete="off"
												class="form-control form-control-lg form-control-a disablecopypaste"
												placeholder="Your Username">
										</div>
									</div>

									<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 mb-1">
										<div class="form-group input-style-2 custom-icon-input ">
											<label for="password" class="">Password<span class="mandatory">*</span></label> <input type="password"
												id="password" name='password'
												class="form-control form-control-lg form-control-a disablecopypaste"
												placeholder="Your Password" maxlength="28" size="35"
												autocomplete="off">
												<span class="icon"><i class="bi bi-eye-slash" id="togglePassword"></i></span>
											
										</div>
									</div>

									<input type="hidden" id="csrfIdSet" name="" value="" />

								</div>
						</div>
						
						
						<!-- sign bt change 24/01/23 for captch -->
						<div class="custom-d-none" id="captch_div">
						<div class="col-md-12 mb-1">
										<div class="row align-items-end">
											<div class="col-lg-6 col-md-6 col-sm-12">
												<div class="form-group captchadiv enter_captcha">
													<label for="txtInput">Captcha<span class="mandatory">*</span></label> <input type='text'
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
						
								<div class="custom-d-none" id="signin_div">
									<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 mb-1  text-center">
										<button type="submit" id="stOTP"
											name="stOTP" class="btn btn-a ghost" >Sign
											In</button>
									</div>
								</div>

								<!-- 	============================For Aadhaar Authentication ============================-->

								<div class="custom-d-none" id="adhar_div">

									<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 mb-1">
										<div class="inst_block auth_inst">
											<h6 class="mb-1">Steps Instruction</h6>
											<ul class="inst_list">
												<li><p class="inst_text">Please click on the <b>Aadhaar Verify</b> button below.</p></li>
												<li><p class="inst_text">You will be redirected to the <b>Sign In to your account via DigiLocker</b> form on <b>Meri Pehchaan Portal.</b></p></li>
												<li><p class="inst_text">Fill in your details using appropriate credentials and sign in.</p></li>
												<li><p class="inst_text">If you are a new user, you need to sign up for <b class="concat-string"><a href="https://digilocker.meripehchaan.gov.in/signup/" class="text-heighlight" target="_blank">Meri Pehchaan</a>.</b></p></li>												
												<li><p class="inst_text">DigiLocker will send an OTP to your registered mobile number(XXXXXX1234) and Email(dummy**@gmail.com).</p></li>
												<li><p class="inst_text">Click on <b>Allow</b> to access the DigiLocker document with the Single Window System.</p></li>
												<li><p class="inst_text">Finally, You will be signed in according to your user's category.</p></li>
											</ul>
										</div>
										<div class="d-flex justify-content-center">
											<button type="button" id="adharlogin" name="adharlogin"
												class="btn btn-a ghost">Aadhaar Verify</button>
										</div>
									</div>
									
								</div>
								
								
								<div class="custom-d-none" id="council_login">		
						

									<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 my-3">
										<div class="mb-1">
											<label> <input type="checkbox"
												name="remember"> Remember me
											</label>
										</div>
									</div>
									<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 text-center">
										<button class="btn btn-a ghost" id="signin_submit" name="submit" type="submit"
											value="Login" >Sign In</button>
									</div>
							</div>
									
						<!-- sign bt change 24/01/23 for captch end -->						

							</form>




						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--  End Register Section  -->
</section>
	<!-- Signin jsp script start-->
<script nonce="${cspNonce}" type="text/javascript">
		var csrfparname = "${_csrf.parameterName}";
		var csrfvalue = "${_csrf.token}";
		var yuji = "<c:url value='/auth/login_check?targetUrl=${targetUrl}' />";

		jQuery(document).ready(function() {

			<c:if test='<%=request.getParameter("error") != null%>'>
			<c:if test='<%=session != null && session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") != null%>'>
			$("#login_fail").show();
			</c:if>
			</c:if>
			$("#newusername").val("");
			var msg = "";
			$("#practfactnch").select();

			msg = jQuery('label#msg').text();
			if ('${error}' != "") {
				jQuery("div#errorDiv").show();
			}
			if ('${msg}' != "") {
				window.alert = function(al, $) {
					return function(msg) {
						al.call(window, msg);
						$(window).trigger("okbuttonclicked");
					};
				}(window.alert, window.jQuery);

				$(window).on("okbuttonclicked", function() {
					console.log("you clicked ok");
					window.location = window.location.href.split("?")[0];
				});
				alert('${msg}');
				jQuery("div#errorDiv").show();
			}

			// Start Canvas Capcha
			function captcha() {
				$("#capcha").attr("src", "genCapchaCode");
			}
			;
			function clear() {
				$("#txtInput").val("");
			}
			;
			$("#btnrefresh").click(function() {
				clear();
				captcha();
			})
			// End Canvas Capcha

			/* jQuery(document).on('keypress', function(event) {
			var regex = new RegExp("^[a-zA-Z0-9\\[\\] \\+ \\* \\-.,/ ~!@#$^&%_]+$");
			  var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
			  if (!regex.test(key)) {
			     event.preventDefault();
			     return false;
			  } 
			}); */
			
			$("div#resendotpdiv").hide();
			
		});

		
		document.addEventListener('DOMContentLoaded', function() {

			document.getElementById('practfactnch').onchange = function() {
				roleHide(this.value);
			};
			
			document.getElementById('click_here').onclick = function() {
				$("#gtOTP").click();
			};
			document.getElementById('gtOTP').onclick = function() {
// 				alert($("#fortimer").val());
				if($("#fortimer").val() == "0" || $("#fortimer").val() == "1"){
					genrateOTP();
				}
				/* alert("You have requested for an OTP too frequently. Please wait for a few minutes and try again!"); */
				if($("#fortimer").val() == "2"){
					alert("You have requested for an OTP. Please wait for few seconds and try again!");
				}
			};	
		});
		
		
		function validation() {
			var ck_username = /^[A-Za-z0-9_]{1,20}$/;
			var ck_password = /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/;
			var a = document.getElementById("username");
			if (a.value == "" || a.value == "'" || a.value == null
					|| a.value.toString().trim() == "" || a.value == "'''") {
				alert("Enter username");
				a.focus();
				return false;
			}
			var b = document.getElementById("password");
			if (b.value == "" || b.value == "'" || b.value == null
					|| b.value.toString().trim() == "") {
				alert("Enter password");
				b.focus();
				return false;
			}
			var iCapcha = removeSpaces(jQuery('#txtInput').val());
			if (iCapcha == "" || iCapcha.length != 5) {
				alert("Enter valid Captcha!");
				jQuery('#txtInput').focus();
				return false;
			}
			if (iCapcha != "") {
				var test = ValidCaptcha(iCapcha);
				if (test != "0") {
					jQuery('#csrfIdSet').attr('name', csrfparname);
					jQuery('#csrfIdSet').attr('value', csrfvalue);
					jQuery('#myFormId').attr('action', yuji);
					return true;
				} else {
					$("#login_fail").show();
					$("#error_msg").html("Captcha Validation failed!");
// 					alert("Captcha Validation failed!");
					jQuery('#txtInput').focus();
					return false;
				}
			}
			return false;
		}
		// Validate the Entered input aganist the generated security code function   
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
		// Remove the spaces from the entered and generated code
		function removeSpaces(string) {
			return string.split(' ').join('');
		}
	</script>
	<!-- Signin jsp script end-->
<script type="text/javascript"
		src="admin/assets/js/jquery-3.6.0.min.js"></script>
		
<script nonce="${cspNonce}" type="text/javascript">

jQuery(document).ready(function(){
	
	
	$("#newusername").val("");
	
		var msg = "";
		msg = jQuery('label#msg').text();
		if('${error}' != ""){
		jQuery("div#errorDiv").show();
	}
	if('${msg}' != ""){
		window.alert = function(al, $){
		    return function(msg) {
		        al.call(window,msg);
		        $(window).trigger("okbuttonclicked");
		    };
		}(window.alert, window.jQuery);

		$(window).on("okbuttonclicked", function() {
		    console.log("you clicked ok");
		    window.location = window.location.href.split("?")[0];
		});
		alert('${msg}');
		jQuery("div#errorDiv").show();
	}	
	
	// Start Canvas Capcha
	function captcha() {
		$("#capcha").attr("src", "genCapchaCode");
	};
	function clear() {
		$("#txtInput").val("");
	};
	$("#btnrefresh").click(function() {
	    clear();
	    captcha();
	})
		// End Canvas Capcha
		
		/* jQuery(document).on('keypress', function(event) {
 		var regex = new RegExp("^[a-zA-Z0-9\\[\\] \\+ \\* \\-.,/ ~!@#$^&%_]+$");
 	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
 	    if (!regex.test(key)) {
 	       event.preventDefault();
 	       return false;
 	    } 
 	}); */
 	
 	
	
 	document.getElementById('adharlogin').onclick = function() {
		 AdharOTP();
	};
 	document.getElementById('stOTP').onclick = function() {
 		return OTPVerify();
	};
	
	document.getElementById('signin_submit').onclick = function() {
		return validation();
	};
	
	
 	
});	

// function OTPVerify(){
	 
// 	var u = $("#newusername").val();
// 	$("#username").val(u);
// 	var m = $("#newmobile_no").val();
// 	$("#mobile_no").val(m);
// 	var p = $("#newpassword").val();
// 	$("#password").val(p);
// 	try{
// 		var aadhar_no = $("#username").val();
// 		var otpField = $("#password").val();
// 		var mobile_no = $("#mobile_no").val();
// 		var login_role = $("#practfactnch").val();
// 		var status=0;
// 		$.ajax({
// 			url : "VerifyOTP?"+csrfparname+"="+csrfvalue,
// 			type : 'POST',
// 			data : {aadhar_no:aadhar_no,otpField:otpField,mobile_no:mobile_no,login_role:login_role},
// 			success : function(data) {
// 				if(data == true){
// 					status = 1;
// 					jQuery('#csrfIdSet').attr('name',csrfparname);
// 			    	jQuery('#csrfIdSet').attr('value',csrfvalue);
// 			    	jQuery('#myFormId').attr('action', yuji);
// 			    	return true;
// 				}else{
// 					status = 0;
// 					return false;

// 				}

// 			},
// 			async : false,
// 		});
// 	}catch(err){
// 		console.log(err.message);
// 	}
	
// }
// function newdatavalid(obj) {
	 
// 	var key = "${_csrf.parameterName}";
// 	var value = "${_csrf.token}";
//  	var newusername = $("#newusername").val();
	  
// 	$.post("newdatavalidfetch?" + key + "=" + value, { newusername : newusername }, function(j) {
		 
// 		if (j.length == 0) {
			
// 			alert("Please enter Valid Aadhaar Card number.");
// 			$("#newusername").val("");
//  		//$("#username").val(j[0][4]);
// // 			$("#email_id").val(j[0][1]);
// // 			$("#institute_name").val(j[0][2]);
// // 			$("#institute_state").val(j[0][3]);
// // 			$("#regisration_state").val(j[0][4]);
// 		}
// 		else{
			 
// 			genrateOTP();
// 		}
// 	});
// }
var   csrfparname ="${_csrf.parameterName}";
var csrfvalue="${_csrf.token}";
var yuji = "<c:url value='/auth/login_check?targetUrl=${targetUrl}' />";

function roleHideOLD(val){
	
	$("#newusername").val("");
	$("#username").val("");
	$("#password").val("");
// 	$("#newusername").val("");
// 	$("#newmobile_no").val("");
	
	if(val=="27" || val=="25" || val=="35")
	{
		$("div#council_login").hide(); 
		$("#new_pract").show(); 
		$("#gtOTP").show(); 
		if (val=="25") {
			$("#adn").text('Email Id <span class="mandatory">*</span>'); 
			$("#newusername").attr('placeholder','Enter Email Id'); 
			$("#newusername").attr('maxlength','50'); 
		}else {
			$("#adn").html('Aadhaar No <span class="mandatory">*</span>'); 
			$("#newusername").attr('placeholder','Enter Aadhaar No'); 
			$("#newusername").attr('maxlength','12');
		}
		$('#stOTP').removeAttr("type").attr("type", "submit");
		$('#signin_submit').removeAttr("type").attr("type", "button");
		$("div#captch_div").show();
		$("#adhar_div").hide();
	}else if(val=="24"){
		$("#adhar_div").show();
		$("div#captch_div").hide();
		$("div#council_login").hide();
		$("div#new_pract").hide();
	}else{
 		$("div#new_pract").hide();
		$("div#council_login").show();
		$("div#signin_div").hide();
		$("div#otpdiv").hide();
		$('#signin_submit').removeAttr("type").attr("type", "submit");
		$('#stOTP').removeAttr("type").attr("type", "button");
		$("div#captch_div").show();
		$("#adhar_div").hide();
	}
}

function roleHide(val){
	$("#newusername").val("");
	$("#username").val("");
	$("#password").val("");
	if(val=="24" || val =="27"){
		$("#adhar_div").show();
		$("div#captch_div").hide();
		$("div#council_login").hide();
		$("div#new_pract").hide();
	}else if( val=="35"){
		$("div#council_login").hide(); 
		$("#new_pract").show(); 
		$("#gtOTP").show(); 
		$("div#otpdiv").show();
		$("#adn").html('Aadhaar No <span class="mandatory">*</span>'); 
		$("#newusername").attr('placeholder','Enter Aadhaar No'); 
		$("#newusername").attr('maxlength','12');
		$('#stOTP').removeAttr("type").attr("type", "submit");
		$('#signin_submit').removeAttr("type").attr("type", "button");
		$("div#captch_div").show();
		$("#adhar_div").hide();
	}else if (val=="25" || val=="alumni") {
		$("#adn").html('Email Id  <span class="mandatory">*</span>'); 
		$("#newusername").attr('placeholder','Enter Email Id'); 
		$("#newusername").attr('maxlength','50');
		$("div#council_login").hide();
		$("#adhar_div").hide();
		$("#new_pract").show(); 
		$("#gtOTP").show();
		$("div#otpdiv").show();
		$("div#captch_div").show();
	}else{
 		$("div#new_pract").hide();
		$("div#council_login").show();
		$("div#signin_div").hide();
		$("div#otpdiv").hide();
		$('#signin_submit').removeAttr("type").attr("type", "submit");
		$('#stOTP').removeAttr("type").attr("type", "button");
		$("div#captch_div").show();
		$("#adhar_div").hide();
	}
}

function fnayusNrhAbhaDataFetch() {
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
 	var ayus = $("#ayus").val();
	// 		alert(nrh_no+"==dd");
	$.post("ayus_abha_nrh_data_fetch?" + key + "=" + value, {
		ayus : ayus
	}, function(j) {
		if (j.length != 0) {
 		$("#username").val(j[0][4]);
// 			$("#email_id").val(j[0][1]);
// 			$("#institute_name").val(j[0][2]);
// 			$("#institute_state").val(j[0][3]);
// 			$("#regisration_state").val(j[0][4]);
		}
		else{
			alert("Please enter valid number ");
			$("#ayus").val("");
		}
	});
 }
 
function genrateOTP(){
	var practfactnch = $("#practfactnch").val(); 
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
 	var newusername = $("#newusername").val();
 	
 	if($("#practfactnch").val() == "27" || $("#practfactnch").val() == "35"){
 		$.ajaxSetup({
 			async : false
 		});
 		
 		$.post("newdatavalidfetch?" + key + "=" + value, { newusername : newusername }, function(j) {
 	 		if (j.length == 0) {
 	 			alert("Please Enter Valid Aadhaar No.");
 				$("#newusername").val("");
 				$("#newusername").focus();
 	 		}
 			else{
 		var pract_type = $("#new").val();
 		var u = $("#newusername").val();
 		$("#username").val(u);
 		var m = $("#newmobile_no").val();
 		$("#mobile_no").val(m);
 		try{
 			var aadhar_no = $("#username").val();
 			var mobile_no = $("#newmobile_no").val();
 			 
 			var  csrfparname ="${_csrf.parameterName}";
 		  	var csrfvalue="${_csrf.token}";
 			var yuji = "<c:url value='/auth/login_check?targetUrl=${targetUrl}' />";
 			 
 			if(aadhar_no != null && aadhar_no != ""){
			    $.ajaxSetup({
					async : false
				});	
 			$.ajax({
 				url : "genrateOTP?"+csrfparname+"="+csrfvalue,
 				type : 'POST',
 				data : {aadhar_no:aadhar_no,mobile_no:mobile_no,practfactnch:practfactnch},
 				success : function(data) {
 			 
 					if(data == "User Not Found"){
 						alert(data);
 					}else{
 						
 						otptimer();
 						$("div#resendotpdiv").hide();
 					    $("div#timerotpdiv").show();
 						
 						$("#gtOTP").hide();
 	 					$("#otpdiv").show();
 	 					$("#signin_div").show();

 	 					$("#oldgtOTP").hide();
 	 					$("#oldotpdiv").hide();
 	 					$("#oldstOTP").hide();
 					}
 					
 				},
 				async : false,
 			});
 			}else{
 				alert("Please Enter Aadhaar No.");
 				$('#newusername').focus();
 			}
 		}catch(err){
 			console.log(err.message);
 		}
 		}
 	});
 	} 
 	else{
 		var pract_type = $("#new").val();
 		var u = $("#newusername").val();
 		$("#username").val(u);
 		var m = $("#newmobile_no").val();
 		$("#mobile_no").val(m);
 		try{
 			var aadhar_no = $("#username").val();
 			var mobile_no = $("#newmobile_no").val();
 			 
 			var  csrfparname ="${_csrf.parameterName}";
 		  	var csrfvalue="${_csrf.token}";
 			var yuji = "<c:url value='/auth/login_check?targetUrl=${targetUrl}' />";
 			
 			if(aadhar_no != null && aadhar_no != ""){
 			$.ajax({
 				url : "genrateOTP?"+csrfparname+"="+csrfvalue,
 				type : 'POST',
 				data : {aadhar_no:aadhar_no,mobile_no:mobile_no,practfactnch:practfactnch},
 				success : function(data) {
 					if(data == "User Not Found"){
 						alert(data);
 					}else{
 						otptimer();
 						$("div#resendotpdiv").hide();
 					    $("div#timerotpdiv").show();
 						
 						$("#gtOTP").hide();
 	 					$("#otpdiv").show();
 	 					$("#signin_div").show();

 	 					$("#oldgtOTP").hide();
 	 					$("#oldotpdiv").hide();
 	 					$("#oldstOTP").hide();
 					}
 					
 				},
 				async : false,
 			});
 			}else{
 				if($("#practfactnch").val() == "27"){
 					alert("Please Enter Aadhaar No.");
 					$('#newusername').focus();
 				}
 				if($("#practfactnch").val() == "25" || $("#practfactnch").val() == "alumni"){
 					alert("Please Enter Email Id");
 					$('#newusername').focus();
 				}
 			}
 		}catch(err){
 			console.log(err.message);
 		}
 	}
	}

function OTPVerify(){
	
	var u = $("#newusername").val();
	$("#username").val(u);
	var m = $("#newmobile_no").val();
	$("#mobile_no").val(m);
	var p = $("#newpassword").val();
	if (p == null || p=="null" || p == "") {
		alert("Enter valid OTP!");
		$('#newpassword').focus();
		return false;
	}
	$("#password").val(p);
	try{
		var aadhar_no = $("#username").val();
		var otpField = $("#password").val();
		var mobile_no = $("#mobile_no").val();
		var login_role = $("#practfactnch").val();
		var status=0;
		var iCapcha = removeSpaces(jQuery('#txtInput').val());
		if (iCapcha == "" || iCapcha.length != 5) {
			alert("Enter valid Captcha!");
			jQuery('#txtInput').focus();
			return false;
		}
		if (iCapcha != "") {
			var test = ValidCaptcha(iCapcha);
			if (test != "0") {
				$.ajax({
					url : "VerifyOTP?"+csrfparname+"="+csrfvalue,
					type : 'POST',
					data : {aadhar_no:aadhar_no,otpField:otpField,mobile_no:mobile_no,login_role:login_role},
					success : function(data) {
						if(data == true){
							status = 1;
							jQuery('#csrfIdSet').attr('name',csrfparname);
					    	jQuery('#csrfIdSet').attr('value',csrfvalue);
					    	jQuery('#myFormId').attr('action', yuji);
					    	return true;
						}else{
							status = 0;
							return false;

						}

					},
					async : false,
				});
			} else {
				$("#login_fail").show();
				$("#error_msg").html("Captcha Validation failed!");
//					alert("Captcha Validation failed!");
				jQuery('#txtInput').focus();
				return false;
			}
		}
	}catch(err){
		console.log(err.message);
	}
// 	return false;
}

</script>

<!--  Password with Icon Start  -->
<script nonce="${cspNonce}">
        const togglePassword = document
            .querySelector('#togglePassword');
  
        const password = document.querySelector('#password');
  
        togglePassword.addEventListener('click', () => {
  
            // Toggle the type attribute using
            // getAttribure() method
            const type = password
                .getAttribute('type') === 'password' ?
                'text' : 'password';
                  
            password.setAttribute('type', type);
  
            // Toggle the eye and bi-eye icon
            togglePassword.classList.toggle('bi-eye');
        });
        
        
function AdharOTP(){
			
			var addhar_number=$("#newusername").val();
			
//		 	$.post("getAdharSession?" + key + "=" + value, {
//		 		addhar_number : addhar_number
//		 	}, function(j) {
//		 		alert(j);
			
//		 	});

/* BISAG_LIVE */		 
const url="https://digilocker.meripehchaan.gov.in/public/oauth2/1/authorize?response_type=code&client_id=73E079BB&redirect_uri=https://apps.bisag.co.in/BISAG_LIVE/getUserDigilocker_API&state=HELLO"; 

/*  BISAG */ 
/* const url="https://digilocker.meripehchaan.gov.in/public/oauth2/1/authorize?response_type=code&client_id=85606FF1&redirect_uri=https://apps.bisag.co.in/BISAG/getUserDigilocker_API&state=HELLO"; */  

/*  AYUSH_EDU */
 /* const url="https://digilocker.meripehchaan.gov.in/public/oauth2/1/authorize?response_type=code&client_id=85606FF1&redirect_uri=https://ayushedu.bisag-n.gov.in/getUserDigilocker_API&state=HELLO"; */ 

		    window.location.href = url; 
		}
		
function otptimer(){		
	
	var hcv = $("#fortimer").val();
	hcv = parseInt(hcv)+1;
	
	if(hcv == 1){
		$("#fortimer").val(hcv);
	}
	if(hcv == 2){
		$("#fortimer").val(hcv);
		return false;
	}
	
	var t = new Date();
	t.setSeconds(t.getSeconds() + 10);
	
	// Set the date we're counting down to
	var countDownDate = new Date(t).getTime();

// 	var countDownDate = d.getSeconds()+60;
	
	// Update the count down every 1 second
	var x = setInterval(function() {

	  // Get today's date and time
	  var now = new Date().getTime();
	    
	  // Find the distance between now and the count down date
	  var distance = countDownDate - now;
	    
	  // Time calculations for days, hours, minutes and seconds
	  var days = Math.floor(distance / (1000 * 60 * 60 * 24));
	  var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
	  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
	  var seconds = Math.floor((distance % (1000 * 60)) / 1000);
	    
	  // Output the result in an element with id="demo"
	  document.getElementById("demo").innerHTML = "Resend OTP after " + seconds + " Seconds ";
	    
	  // If the count down is over, write some text 
	  if (distance < 0) {
	    clearInterval(x);
	    document.getElementById("demo").innerHTML = "";
	    $("#fortimer").val(0);
	    $("div#resendotpdiv").show();
	    $("div#timerotpdiv").hide();
	  }
	}, 1000);
}




    </script>
<!--  Password with Icon End  -->
