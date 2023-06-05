<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="admin/js/watermark/common.js"></script>
<!-- single select, search with select start -->
<link href="admin/js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="admin/js/dropDown/select2.min.js" type="text/javascript"></script>
<!-- single select, search with select end -->

<!-- common style start -->
<link rel="stylesheet" href="admin/assets/vendor/common_custom_style.css">
<!-- common style end -->

<!--  Intro Single  -->
<section class="intro-single">
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-lg-8">
				<div class="title-single-box">
					<h1 class="title-single">College Sign Up</h1>
				</div>
			</div>
			<div class="col-md-12 col-lg-4">
				<nav aria-label="breadcrumb"
					class="breadcrumb-box d-flex justify-content-lg-end">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="landingpage">Home</a></li>
						<li class="breadcrumb-item active" aria-current="page">College
							Sign Up</li>
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
								<h4 class="intro-title">Welcome to College Sign Up</h4>
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
						<form:form action="institute_registration_url_action"
							method='POST' modelAttribute="Category_cmd"
							enctype="multipart/form-data" id="myFormId" class="form-inner-common">

							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>College Name<span
											class="mandatory">*</span></label> <input id="institute_name"
											name="institute_name"
											class="form-control form-control-lg"
											autocomplete="off" maxlength="100" placeholder="Enter College Name">

									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label> College Abbreviation<span class="mandatory">*</span>
										</label> <input id="college_abbr" name="college_abbr"
											class="form-control form-control-lg"
											autocomplete="off" value="" maxlength="10"
											placeholder="Enter College Abbreviation">
									</div>
								</div>
								
							<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>Code<span
											class="mandatory">*</span></label> 
											<input id="code" name="code"
											class="form-control form-control-lg"
											autocomplete="off" value="" maxlength="7"
											placeholder="Enter Code">
<!-- 											<span><strong class="mandatory">E.g. AYU0001</strong></span> -->
									</div>
								</div>
								
								
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>Email Id<span
											class="mandatory">*</span></label> <input id="institute_email"
											name="institute_email"
											class="form-control form-control-lg"
											autocomplete="off" placeholder="abc@example.com"
											maxlength="50" onchange="checkgmail(this.value)"
											pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
											value="${email_txt}">

									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>Mobile No.<span
											class="mandatory">*</span></label> <input id="institute_mob_no"
											name="institute_mob_no"
											class="form-control form-control-lg"
											autocomplete="off" maxlength="10" minlength="10"
											placeholder="Enter Mobile No">


									</div>
								</div>
								
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>Total Sanction Seat<span
											class="mandatory">*</span></label> <input id="total_sanction_seat"
											name="total_sanction_seat"
											class="form-control form-control-lg"
											autocomplete="off" maxlength="3" 
											placeholder="Enter Total Sanction Seat">


									</div>
								</div>
								
								
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>System<span
											class="mandatory">*</span></label> <select name="system_id"
											id="system_id"
											class="singleselect form-control form-control-lg">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getSystemForAll}"
												varStatus="num">
												<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
											</c:forEach>
										</select>
										<!-- 														<input type="hidden" id="id" name="id" value="0" -->
										<!-- 															class="form-control form-control-lg form-control-a effect-9" autocomplete="off" /> -->

									</div>
								</div>
							

								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>University<span
											class="mandatory">*</span></label> <select class="select2" name="university_id"
											id="university_id"
											class="select2 form-control form-control-lg">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getUniversityList}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>


									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>Country<span
											class="mandatory">*</span></label> <select class="singleselect" name="country_id"
											id="country_id" 
											class="select2 form-control form-control-lg">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${MedCountryName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select> <input type="hidden" id="id" name="id" value="0"
											class="form-control form-control-lg"
											autocomplete="off" />

									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>State<span
											class="mandatory">*</span></label> <select name="state_id"
											id="state_id"
											class="select2 form-control form-control-lg">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${MedStateName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>


									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>District<span
											class="mandatory">*</span></label> <select name="district_id"
											id="district_id"
											class="select2 form-control form-control-lg">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${MedDistrictName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select> <input type="hidden" id="id" name="id" value="0"
											class="form-control form-control-lg"
											autocomplete="off" />

									</div>
								</div>
								
							

								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>Upload College Logo<span class="mandatory">*</span></label>
										<input type="file" accept="image/*" id="upload_image"
											name="upload_image" class="form-control form-control-lg">
<!-- 											<label class="mandatory">[Maximum file size upto 50 kb and Allowed only *.jpg/png File]</label> -->
										
										<input type="hidden" id="upload_image_hid"
											name="upload_image_hid" class="form-control"> <input
											type="hidden" id="upload_image_forV" name="upload_image_forV"
											class="form-control" value="0">
										<!-- 											 -->
										<div class="note-text">
											<span class="errorClass" id="upload_image_doc_lbl">${exp_path_msg}</span> 
											<span class="tikClass" id="upload_file_lbltik"></span>
										</div>
<!-- 										<div class="note-text"> -->
<!-- 											<span class="mandatory note-text" id="upload_image_doc_lbl"></span> -->
<!-- 										</div> -->
									</div>
								</div>
								
								<div class="col-lg-12 col-md-12 col-sm-12 mb-1">
									<div class="form-group input-style-2">
										<label class="">Address<span
											class="mandatory">*</span></label>
										<textarea id="address" name="address" rows="3" cols="50"
											maxlength="250"
											class="form-control form-control-lg"
											autocomplete="off" placeholder="Enter Address"></textarea>
										<input type="hidden" id="id" name="id" value="0"
											class="form-control form-control-lg"
											autocomplete="off" />
									</div>
								</div>

<!-- 								<div class="col-lg-6 col-md-6 col-sm-12 mb-1" -->
<!-- 									style="display: none;"> -->
<!-- 									<div class="form-group"> -->

<!-- 										<label class=" form-control-label" for="username">STATUS<span -->
<!-- 											class="mandatory">*</span></label> <select name="status" id="status" -->
<!-- 											class="form-control form-control-lg form-control-a effect-9 select2 narrow wrap"> -->
<%-- 											<c:forEach var="item" items="${ActiveInActiveList}" --%>
<%-- 												varStatus="num"> --%>
<%-- 												<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 											</c:forEach> --%>
<!-- 										</select> <input type="hidden" id="id" name="id" value="0" -->
<!-- 											class="form-control form-control-lg form-control-a effect-9" -->
<!-- 											autocomplete="off" /> -->

<!-- 									</div> -->
<!-- 								</div> -->
								<div class="col-md-12 mb-1">
									<div class="row align-items-end">
										<div class="col-lg-6 col-md-6 col-sm-12">
											<div class="form-group captchadiv enter_captcha">
												<label for="password">Captcha</label> <input type='text'
													class="form-control form-control-lg disablecopypaste"
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
								<div
									class="col-lg-12 col-md-12 col-sm-12 mb-1 justify-content-center d-flex">
									<div class="custom-btn footer-btn">
										<ul class="footer-btn-list">
											<li class="f-btn"><a href="portalsignin"
												class="link-color">Sign in instead</a></li>
											<li class="f-btn"><input type="submit"
												class="btn btn-a ghost" id="save_btn" value="Sign Up"></li>
										</ul>
									</div>
								</div>


							</div>

							<input type="hidden" id="hidden_id" name="id"
								class="form-control form-control-lg">

						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!--  End Register Section  -->

<input type="hidden" id="csrfIdSet" name="" value="" />


<script nonce="${cspNonce}">

	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	
	
	var csrfparname ="${_csrf.parameterName}";
	var csrfvalue="${_csrf.token}";
	var yuji = "<c:url value='/auth/login_check?targetUrl=${targetUrl}' />";

	$(document).ready(function() {
		 $.ajaxSetup({
		    async: false
		});

		try {
			var msg = document.getElementById("msg").value;
			if (msg != null) {
				alert(msg);
			}
		} catch (e) {
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

	});
	
	
	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('institute_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this);
		};
		document.getElementById('institute_mob_no').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		
		document.getElementById('institute_mob_no').onchange = function() {
			return mobileNumber(this);
		};
		document.getElementById('total_sanction_seat').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('system_id').onchange = function() {
			getUniversity();
		};
		document.getElementById('university_id').onchange = function() {
			
			getCountry();
		};
		
		document.getElementById('country_id').onchange = function() {
			getState();
		};
		document.getElementById('state_id').onchange = function() {
			getDistrict();
		};
		document.getElementById('save_btn').onclick = function() {
			return Validation();
		};
		document.getElementById('upload_image').onchange = function() {
 			
 			return imgFileSizeValidation(this,this.value,'upload_image','50kb','upload_file_lbltik','upload_image_doc_lbl');
 			//imgFileSizeValidation(this,this.value,'photograph','50kb','upload_photo_doc_lbl');
 		};
 		
//  		document.getElementById('code').onchange = function() {
//  			return isCod(this);
//  		};
		
		
	});

	 function Validation() {

		  if ($("#institute_name").val().trim() == "") {
				alert("Please Enter the College Name");
				$("#institute_name").focus();
				return false;
		 }
		 if ($("#college_abbr").val().trim() == "") {
				alert("Please Enter the College Abbreviation");
				$("#college_abbr").focus();
				return false;
			   }
		 if ($("#code").val().trim() == "") {
				alert("Please Enter the Code");
				$("#code").focus();
				return false;
			   }
		 
// 		 if (parseInt(nr) <= 0) {
// 				alert("Please Enter Valid Code");
// 				$("#code").focus();
// 				return false;
// 			}
		
		 var maxLength = 7;
		 var charLength = $("#code").val().length;
		 
	       if(charLength > maxLength){
	       	alert("Code Should Contain Maximum 7 Characters");
				$("input#code").focus();
				return false;
	       } 
		 
		 if ($("#institute_email").val() == "") {
				alert("Please Enter Email Id");
				$("#institute_email").focus();
				return false;
		 }
		 if ($("#institute_mob_no").val().trim() == "") {
				alert("Please Enter Mobile No.");
				$("#institute_mob_no").focus();
				return false;
		 }
		
		 if ($("#total_sanction_seat").val().trim() == "") {
				alert("Please Enter Total Sanction Seat");
				$("#total_sanction_seat").focus();
				return false;
		 }
		 if( $("#system_id").val() == "0" ){
				alert("Please Select System");
				$("#system_id").focus();
				return false;
		   }
		
		 if( $("#university_id").val() == "0" ){
				alert("Please Select University Name");
				$("#university_id").focus();
				return false;
		  }
		 
		 if( $("#country_id").val() == "0" ){
				alert("Please Select Country");
				$("#country_id").focus();
				return false;
		  }
		 if( $("#state_id").val() == "0" ){
				alert("Please Select State");
				$("#state_id").focus();
				return false;
		  }
		 if( $("#district_id").val() == "0" ){
				alert("Please Select District");
				$("#district_id").focus();
				return false;
		   }
		
		
		 if($("#upload_image").val() == ""){  
			 	alert("Please Upload College Logo");
			 	$("input#upload_image").focus();
				return false;
			 }
		 
		 if ($("#address").val().trim() == "") {
					alert("Please Enter the Address");
					$("#address").focus();
					return false;
			}
		 
		 var iCapcha = removeSpaces(jQuery('#txtInput').val());
			if(iCapcha == "" || iCapcha.length != 5){
				alert("Enter valid Captcha!");
				jQuery('#txtInput').focus();
				return false;
			}
			if(iCapcha != ""){
				var test = ValidCaptcha(iCapcha);
				if(test == "0"){
					alert("Captcha Validation failed!");
					jQuery('#txtInput').focus();
					return false;
				}
			}

		return true;
	} 
	 
	// Validate the Entered input aganist the generated security code function   
		function ValidCaptcha(iCapcha){
// 		deugger;
			var test = "0";
	    	try{
				$.ajax({
					url : "checkCapchaCode?"+csrfparname+"="+csrfvalue,
					type : 'POST',
					data : {iCapcha:iCapcha},
					success : function(data) {
						if(data){
							test = data;
			     		}
					},
					async : false,
				});
	    	}catch(err){
	    		console.log(err.message);
	    	}
			return test;
	    }
	 
	 function removeSpaces(string)
		{
		    return string.split(' ').join('');
		}

	function getState() {
		var selval = $("#country_id").val();
	
		
		$
				.post(
						"getStateUrl?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {

							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i].state_id + '" name="'+j[i].state_id+'" >'
										+ j[i].state_name + '</option>';
							}
							$("select#state_id").html(options);
						});
	}

	function getDistrict() {
		var selval = $("#state_id").val();
		
		$
				.post(
						"getDistrictUrl?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {

							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i].district_id + '" name="'+j[i].district_id+'" >'
										+ j[i].district_name + '</option>';
							}
							$("select#district_id").html(options);
						});
	}
	
	function getUniversity() {
		
		var selval = $("#system_id").val();
			
		$
				.post(
						"getUniversitylistbySystem?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {
						

							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i].id + '" name="'+j[i].university_name+'" >'
										+ j[i].university_name + '</option>';
							}
							$("select#university_id").html(options);
						});
	}
	
function getCountry() {
		var selval = $("#university_id").val();
				
		$
				.post(
						"getCountrylistbyUniversity?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {
						

							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#country_id").html(options);
						});
	}
	
	

	
	function mobileNumber(obj){
		
		_mobile = obj.value;
		
		var regExp =/^[6789]\d{9}$/;
	    if(_mobile == '' || !regExp.test(_mobile)){
	        alert('Please Enter Number Start with 6789 Digit');
	        $('#'+obj.id).focus();
	        $('#'+obj.id).val('');
	        return false;
	    }
	}

	function checkgmail(email1) {
		
		 document.getElementById("institute_email").innerHTML="";
		if(/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
			
		}else{
			
			alert("Please Enter Valid Email Address");
			$("input#institute_email").focus();
			$("input#institute_email").val('');
			return false ;
		}
	}
</script>



