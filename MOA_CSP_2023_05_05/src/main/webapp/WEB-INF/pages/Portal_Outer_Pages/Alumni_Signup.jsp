<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<link rel="stylesheet" href="admin/js/autoComplate/autoComplate.css">
<link href="admin/js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="admin/js/dropDown/select2.min.js" type="text/javascript"></script>
<link rel="stylesheet"
	href="admin/assets/vendor/common_custom_style.css">
	
	<script type="text/javascript" src="admin/assets/js/jquery-3.6.0.min.js"></script>
	<script src="admin/assets/db_js/CommonValidation.js"></script>
	<script type="text/javascript" src="admin/js/watermark/common.js"></script>
	<script src="admin/js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="admin/js/Calender/datePicketValidation.js"></script>



<section class="page-content">
<!--  Intro Single  -->
<section class="intro-single">
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-lg-8">
				<div class="title-single-box">
					<h1 class="title-single">Alumni Sign Up</h1>
				</div>
			</div>
			<div class="col-md-12 col-lg-4">
				<nav aria-label="breadcrumb"
					class="breadcrumb-box d-flex justify-content-lg-end">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="landingpage">Home</a></li>
						<li class="breadcrumb-item active" aria-current="page">Alumni
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
									src="admin/assets/img/grid-logo.png"></span>
								<h4 class="intro-title">Welcome to Alumni Sign Up</h4>
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
						<form:form action="Alumni_SignUp_Action" method='POST'
							modelAttribute="AlumSignupCmd" enctype="multipart/form-data" class="form-inner-common">

							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label class=" form-control-label">Institute Name<span
											class="mandatory">*</span></label> <select name="institute"
											id="institute"
											class="select2 narrow wrap form-control form-control-lg form-control-a disablecopypaste">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getInstituteList}"
												varStatus="num">
												<option value="${item.id}" name="${item.id}">${item.institute_name.trim()}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								
								
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
								<div class="form-group">
									<label>Country<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="country_id" id="country_id" class="select2 narrow wrap form-control form-control-lg form-control-a">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedCountryName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>

										</select>
									</div>
								</div>								
								
								<!-- end select -->
							</div>

								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
								<div class="form-group">
									<label>State<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="state_id" id="state_id" class="select2 narrow wrap form-control form-control-lg form-control-a">
											<option value="0">--Select--</option>
<%-- 											<c:forEach var="item" items="${getMedStateName}" --%>
<%-- 												varStatus="num"> --%>
<%-- 												<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 											</c:forEach> --%>
										</select>
										</div>
									</div>								
								
								<!-- end select -->
							</div>

							<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="input-style-2">
										<label>Abbreviation<span class="mandatory">*</span></label> <input
											type="text" id="state_abbr" name="state_abbr" 
											class=" form-control UpperClassName txt-transupp" autocomplete="off" maxlength="10" placeholder="Abbreviation"/>
<!-- 											onkeypress="lettersAndSpaceCheck(document.form1.name)" -->
									</div>
									<!-- end input    onkeypress="return onlyAlphabets(event,this);"  -->
								</div>
								
								
								
								
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label class=" form-control-label">Name<span
											class="mandatory">*</span></label> <input id="name" name="name"
											class="form-control form-control-lg form-control-a effect-9"
											autocomplete="off" value="" maxlength="30"
											placeholder="Enter Name">
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label class=" form-control-label">Email Id<span
											class="mandatory">*</span></label> <input id="email" name="email"
											class="form-control form-control-lg form-control-a effect-9"
											autocomplete="off" placeholder="abc@example.com"
											maxlength="50"
											pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
											value="${email_txt}">
<!-- 											onchange="checkgmail(this.value)" -->
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label class=" form-control-label">Mobile No.<span
											class="mandatory">*</span></label> <input id="mob_no" name="mob_no"
											class="form-control form-control-lg form-control-a effect-9"
											autocomplete="off" maxlength="10" minlength="10"
											placeholder="Enter Mobile No">
<!-- 											onkeypress="return isNumberKey0to9(event, this);" -->
<!-- 											onchange="return mobileNumber(this)" -->
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label class=" form-control-label">Aadhar Card No.<span
											class="mandatory">*</span></label> <input id="aadhar_no"
											name="aadhar_no"
											class="form-control form-control-lg form-control-a effect-9"
											autocomplete="off" maxlength="12" minlength="12"
											placeholder="Enter Aadhar No">
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label class=" form-control-label">Year of Leaving
											College<span class="mandatory">*</span>
										</label> <input type="month" id="year_leave" name="year_leave"
											class="form-control form-control-lg form-control-a effect-9">
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group input-style-2">
										<label class=" form-control-label">Address<span
											class="mandatory">*</span></label> 
<!-- 											<input type="text" id="address" -->
<!-- 											name="address" placeholder="Enter Address" -->
<!-- 											class="form-control form-control-lg form-control-a effect-9"> -->
											<textarea id="address"
											name="address" placeholder="Enter Address" class="form-control form-control-lg form-control-a effect-9"
											 rows="2"></textarea>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label class=" form-control-label">Present Status<span
											class="mandatory">*</span></label> <input type="text"
											id="present_status" name="present_status"
											placeholder="Enter Present Status"
											class="form-control form-control-lg form-control-a effect-9">
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label class=" form-control-label">Present Working
											Place<span class="mandatory">*</span>
										</label> <input type="text" id="pre_wrk_plc" name="pre_wrk_plc"
											placeholder="Enter Present Working Place"
											class="form-control form-control-lg form-control-a effect-9">
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label class=" form-control-label">Area of Interest<span
											class="mandatory">*</span></label> <input type="text"
											id="area_intrst" name="area_intrst"
											placeholder="Enter Area of Interest"
											class="form-control form-control-lg form-control-a effect-9">
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group input-style-2">
										<label class=" form-control-label">Nostalgia/Memorable
											Event<span class="mandatory">*</span>
										</label>
										<textarea id="nostalgia" name="nostalgia" class="form-control form-control-lg form-control-a effect-9" placeholder="Enter Nostalgia" rows="2"></textarea>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label class=" form-control-label">Intern Certificate<span
											class="mandatory">*</span></label> <input type="file" accept=".pdf"
											id="intern_certi" name="intern_certi"
											class="form-control form-control-lg form-control-a effect-9">
									</div>
								</div>
								<div
									class="col-lg-12 col-md-12 col-sm-12 mb-1 justify-content-center d-flex">
									<div class="custom-btn footer-btn">
										<ul class="footer-btn-list">
											<li class="f-btn"><a href="ncismsignin"
												class="link-color">Sign in instead</a></li>
											<li class="f-btn"><input type="submit"
												class="btn btn-a ghost" id="save_btn" value="Sign Up"></li>
<!-- 												onclick="return Validation();" -->
										</ul>
									</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!--  End Register Section  -->
</section>
<script type="text/javascript" nonce="${cspNonce}">

	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";

	$(document).ready(function() {

		try {
			var msg = '${msg}';
			if (msg != null && msg !="") {
				alert(msg);
			}
		} catch (e) {
		}

	});

	 function Validation() {

		 if ($("select#institute").val() == "0") {
				alert("Please Select Institute");
				$("select#institute").focus();
				return false;
		 }
		 if( $("select#country_id").val() == "0" ){
				alert("Please Select Country");
				$("select#country_id").focus();
				return false;
		 }
		 if( $("select#state_id").val() == "0" ){
				alert("Please Select State");
				$("select#state_id").focus();
				return false;
		 }
		 if ($("#state_abbr").val().trim() == "") {
				alert("Please Enter the State Abbreviation");
				$("#state_abbr").focus();
				return false;
		 }
		 if ($("#name").val().trim() == "") {
				alert("Please Enter the Name");
				$("#name").focus();
				return false;
		 }
		 if ($("#email").val().trim() == "") {
				alert("Please Enter the Email Address");
				$("#email").focus();
				return false;
		 }
		 if ($("#mob_no").val().trim() == "") {
				alert("Please Enter the Mobile Number");
				$("#mob_no").focus();
				return false;
		 }
		 if ($("#aadhar_no").val().trim() == "") {
				alert("Please Enter the Aadhar No");
				$("#aadhar_no").focus();
				return false;
		 }
		 if ($("#address").val().trim() == "") {
				alert("Please Enter the Address");
				$("#address").focus();
				return false;
		 }
		 if ($("#present_status").val().trim() == "") {
				alert("Please Enter the Present Status");
				$("#present_status").focus();
				return false;
		 }
		 if ($("#pre_wrk_plc").val().trim() == "") {
				alert("Please Enter the Present Working Place");
				$("#pre_wrk_plc").focus();
				return false;
		 }
		 if ($("#area_intrst").val().trim() == "") {
				alert("Please Enter Area Of Interest");
				$("#area_intrst").focus();
				return false;
		 }
		 if ($("#nostalgia").val().trim() == "") {
				alert("Please Enter the Nostalgia Or Memorable Event");
				$("#nostalgia").focus();
				return false;
		 }

		return true;
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
		
// 		 document.getElementById("institute_email").innerHTML="";
		if(/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
			
		}else{
			
			alert("Please Enter Valid Email Address");
			$("input#institute_email").focus();
			$("input#institute_email").val('');
			return false ;
		}
	}
	
////////CSP start================================================	
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('country_id').onchange = function() {
			fn_pre_domicile_Country();
		};
		document.getElementById('save_btn').onclick = function() {
			return Validation();
		};
		document.getElementById('email').onchange = function() {
			checkgmail(this.value);
		};
		document.getElementById('mob_no').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('mob_no').onchange = function() {
			return mobileNumber(this);
		};
// 		document.getElementById('state_abbr').onkeypress = function() {
// 			lettersAndSpaceCheck(document.form1.name);
// 		};
	});
	function fn_pre_domicile_Country() {
		var text = $("#country_id option:selected").text();
		var country_id = $('select#country_id').val();
		$
				.post("getStateListFormcon2?" + key + "=" + value, {
					country_id : country_id
				})
				.done(
						function(j) {
							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#state_id").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}
	function lettersAndSpaceCheck(name)
	{
	   var regEx = /^[a-z][a-z\s]*$/;
	   if(name.value.match(regEx))
	     {
	      return true;
	     }
	   else
	     {
	     alert("Please enter letters and space only.");
	     return false;
	     }
	}  

</script>


