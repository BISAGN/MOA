<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="admin/js/common/commonmethod.js"></script>
<script type="text/javascript" src="admin/js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="admin/js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="admin/js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript" src="admin/js/watermark/common.js"></script>
<link rel="stylesheet" href="admin/js/autoComplate/autoComplate.css">
<link href="admin/js/jquery/jquery-ui.css" rel="Stylesheet"></link>
<script src="admin/js/jquery/jquery-ui.js" type="text/javascript"></script>

<!-- Datepicker start -->
<link rel="stylesheet" href="admin/js/Calender/Calender_jquery-ui.css">
<script src="admin/js/Calender/jquery-ui.js"></script>
<script src="admin/js/Calender/datePicketValidation.js"></script>
<!-- Datepicker End -->

<!-- single select, search with select start -->
<link href="admin/js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="admin/js/dropDown/select2.min.js" type="text/javascript"></script>
<!-- single select, search with select end -->

<!-- common style start -->
<link rel="stylesheet"
	href="admin/assets/vendor/common_custom_style.css">
<!-- common style end -->


<!--  Intro Single  -->
<section class="intro-single">
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-lg-8">
				<div class="title-single-box">
					<h1 class="title-single">NCH Practitioner Sign Up</h1>
				</div>
			</div>
			<div class="col-md-12 col-lg-4">
				<nav aria-label="breadcrumb"
					class="breadcrumb-box d-flex justify-content-lg-end">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="landingpage">Home</a></li>
						<li class="breadcrumb-item active" aria-current="page">NCH
							Practitioner Sign Up</li>
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
									src="admin/assets/img/grid-logo.png" alt="Ayush Grid"
									title="Ayush Grid"></span>
								<h4 class="intro-title">Welcome to Practitioner Sign Up</h4>
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
						<form:form name="linkmaster" id="linkmaster"
							action="practitionner_signup_Action" method="post"
							modelAttribute="practitionner_signup_CMD"
							enctype="multipart/form-data" class="form-inner-common">
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
<!-- 									----17_02_2023 -->
										<label>Full Name <strong class="mandatory">*</strong></label> <input
											id="name" name="name" class="form-control form-control-lg"
											autocomplete="off" maxlength="50" placeholder="Enter Full Name">
										<input type="hidden" id="id" name="id" class="form-control"
											value="0" autocomplete="off">
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>Date Of Birth <strong class="mandatory">*
										</strong></label> <input type="text" name="dob" id="dob" maxlength="10"
											class="form-control form-control-lg effect-9"
											aria-required="true" autocomplete="off" value="DD/MM/YYYY">
											<input type="hidden" id = "yrr" name ="yrr" value="">
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>Aadhaar Number<strong class="mandatory">*</strong></label>
										<input type="text" id="aadhar_card" name="aadhar_card"
											maxlength="12" minlength="12"
											class="form-control form-control-lg" autocomplete="off"
											placeholder="Enter Aadhaar Number">

									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label class=" form-control-label">Email Id<span
											class="mandatory">*</span></label> <input id="email" name="email"
											class="form-control form-control-lg" autocomplete="off"
											placeholder="abc@example.com" maxlength="50"
											pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
											value="">
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>Mobile Number<strong class="mandatory">*
										</strong></label> <input type="text" id="mobile_no" name="mobile_no"
											class="form-control form-control-lg" maxlength="10"
											minlength="10" placeholder="Enter Mobile Number">
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label for="fname">Gender <strong class="mandatory">*
										</strong></label> <select name="gender" id="gender" class="form-control form-select">
											<!-- style="text-transform: uppercase" -->
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getgenderList}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>Internship Completion Date 
											 </label> <input type="text"
											name="internship_completion_date"
											id="internship_completion_date" maxlength="10"
											class="form-control form-control-lg effect-9"
											aria-required="true" autocomplete="off" value="DD/MM/YYYY">
									 		<input type="hidden" id = "yrr" name ="yrr" value="">
											
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>Registration State <strong class="mandatory">*
										</strong></label> <select name="reg_state" id="reg_state"
											class="form-control form-control-lg disablecopypaste form-select">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${MedStateName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
<!-- 								----17_02_2023 -->
							   <div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>State Registration Number <strong
											class="mandatory">*</strong></label> <input type="text"
											id="state_reg_num" name="state_reg_num"
											class="form-control form-control-lg" maxlength="20"
											placeholder="Enter State Registration Number">
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1 d-none">
									<div class="form-group ">
										<label class=" form-control-label">System<span
											class="mandatory">*</span></label> <select name="system_id"
											id="system_id"
											class=" form-control form-control-lg select2 narrow wrap">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getSystemForHomeopathy}"
												varStatus="num">
												<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
											</c:forEach>
										</select> <input type="hidden" id="id" name="id" value="0"
											class="form-control form-control-lg" autocomplete="off" />

									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label class=" form-control-label">University<span
											class="mandatory">*</span></label> <select name="university_id"
											id="university_id"
											class="form-control form-control-lg select2 narrow wrap">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getUniversityList}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
<!-- 											<option value="OTHER">OTHER</option> -->
										</select>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1 custom-d-none" id="university_name_otherDiv">
									<div class="form-group">
										<label>Other University<strong class="mandatory">*</strong></label>
										<input type="text" id="university_name_other" name="university_name_other"
											maxlength="100" 
											class="form-control form-control-lg" autocomplete="off"
											placeholder="Enter Other University Name">
<!-- --02-03-2023 -->
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label class=" form-control-label">Institute/College<span
											class="mandatory">*</span></label> <select name="institute_id"
											id="institute_id"
											class="form-control form-control-lg select2 narrow wrap">
											<option value="0">---Select---</option>
<%-- 											<c:forEach var="item" items="${getinstitutelist}" --%>
<%-- 												varStatus="num"> --%>
<%-- 												<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 											</c:forEach> --%>
<!-- 											<option value="OTHER">OTHER</option> -->
										</select> <input type="hidden" id="id" name="id" value="0"
											class="form-control form-control-lg" autocomplete="off" />
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1 custom-d-none" id="institute_name_otherDiv">
									<div class="form-group">
										<label>Other Institute/College<strong class="mandatory">*</strong></label>
										<input type="text" id="institute_name_other" name="institute_name_other"
											maxlength="100" 
											class="form-control form-control-lg" autocomplete="off"
											placeholder="Enter Other Institute Name">

									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-1">
									<div class="form-group">
										<label>Year Of Admission <strong class="mandatory">*
										</strong></label>
<!-- 										 <input type="text" name="date_of_admission" id="date_of_admission" maxlength="10" -->
<!-- 											class="form-control form-control-lg effect-9" -->
<!-- 											aria-required="true" autocomplete="off" value="DD/MM/YYYY"> -->
											
											<select name="date_of_admission"
											id="date_of_admission"
											class="form-control form-control-lg select2 narrow wrap">
										</select>
											<input type="hidden" id = "date_of_admission_hid" name ="date_of_admission_hid" value="">
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 mb-1 justify-content-center d-flex">
									<div class="custom-btn footer-btn">
										<ul class="footer-btn-list">
											<li class="f-btn"><a href="portalsignin"
												class="link-color">Sign in instead</a></li>
											<li class="f-btn"><input type="submit"
												class="btn btn-a ghost" id="draft" name="draft"
												value="Sign Up"></li>											
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


<script nonce="${cspNonce}" type="text/javascript">
	// ==URMIK
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	$(document).ready(function() {

				$('#btn-reload').on('click', function() {
					table.ajax.reload();
				});
				inyear();
		$.ajaxSetup({
			async : false
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
				try {
					var msg = document.getElementById("msg").value;
					if (msg != null) {
						alert(msg);
					}
				} catch (e) {
				}
		datepicketDate('dob');
		datepicketDate('internship_completion_date');
		datepicketDate('upload_date');
		datepicketDate('date_of_admission');
	});

	function checkgmail(email1) {
		document.getElementById("email").innerHTML = "";
		if (/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
		} else {
			alert("Please Enter Valid Email Id");
			$("input#email").focus();
			$("input#email").val('');
			return false;
		}
	}
	function mobileNumber(obj) {
		_mobile = obj.value;
		var regExp = /^[6789]\d{9}$/;
		if (_mobile == '' || !regExp.test(_mobile)) {
			alert('Please Enter Valid Number');
			$('#' + obj.id).focus();
			$('#' + obj.id).val('');
			return false;
		}
	}
	
	function calculate_age(obj){ 
    var from_d=$("#"+obj).val();
     var from_d1=from_d.substring(6,10);
    var from_d2=from_d.substring(3,5);
    var from_d3=from_d.substring(0,2);
    var frm_d = from_d3+"-"+from_d2+"-"+from_d1;         
    var today=new Date();
    var to_d3 = today.getDate();
    var to_d2 = today.getMonth() + 1;
    var to_d1 = today.getFullYear();        
    var to_d0 = to_d3+"-"+to_d2+"-"+to_d1;
    if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3){
    var year = to_d1 - from_d1     
     
    var month = to_d2 - from_d2
    }
    if(to_d2 > from_d2 && to_d3 < from_d3){
            var year = to_d1 - from_d1        
            var month = to_d2 - from_d2 - 1
            }
    if(from_d2 > to_d2){
            var year = to_d1 - from_d1 - 1
            var month1 = from_d2 - to_d2
            var month = 12 - month1
    }
    if(from_d2 == to_d2 && from_d3 > to_d3){
            var year = to_d1 - from_d1 - 1
            var days = from_d3 - to_d3
    }
    if(from_d2 == to_d2 && to_d3 > from_d3){
            var year = to_d1 - from_d1 
            var days =  to_d3 - from_d3 
    }
    if(from_d2 == to_d2 && to_d3 == from_d3){
            var year = to_d1 - from_d1 
            var days = 0
    }
    //days
    if(from_d2 > to_d2 && from_d3 > to_d3){
            var m = from_d2 - to_d2 
            var n = m * 30
            var m1 = from_d3 - to_d3 
            var m2 = n+m1
            var days =  m2
    }
    if(from_d2 > to_d2 && to_d3 > from_d3){
            var m = from_d2 - to_d2 
            var n = m * 30
            var m1 =  to_d3 - from_d3 
            var m2 = n+m1
            var days =  m2
    }
    if(to_d2 > from_d2   && to_d3 > from_d3){
            var m =  to_d2 - from_d2 
            var n = m * 30
            var m1 =  to_d3 - from_d3 
            var m2 = n+m1        
            var days =  m2 
    }
    if(to_d2 >  from_d2 && from_d3 > to_d3){
            var m = to_d2 - from_d2   
            var n = m * 30
            var m1 = from_d3 - to_d3 
            var m2 = n+m1
            var days =  m2
    }
    $(".get-value").text(""+year+" Years");
  	 return(year);
}
	
	function calculate_ageDiff(obj,obj2){ debugger
	    var from_d=$("#"+obj).val();
	    if (from_d.length == 4) {
	    	from_d = "01-"+"01-"+from_d;
		}
	     var from_d1=from_d.substring(6,10);
	    var from_d2=from_d.substring(3,5);
	    var from_d3=from_d.substring(0,2);
	    var frm_d = from_d3+"-"+from_d2+"-"+from_d1;   
	    
	    var to_d=$("#"+obj2).val();
	    if (to_d.length == 4) {
	    	to_d = "01-"+"01-"+to_d;
		}
	     var to_d1=to_d.substring(6,10);
	    var to_d2=to_d.substring(3,5);
	    var to_d3=to_d.substring(0,2);
	    var to_d0 = to_d3+"-"+to_d2+"-"+to_d1;
	    if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3){
	    var year = to_d1 - from_d1     
	     
	    
	    
	    var month = to_d2 - from_d2
	    }
	    if(to_d2 > from_d2 && to_d3 < from_d3){
	            var year = to_d1 - from_d1        
	            var month = to_d2 - from_d2 - 1
	            }
	    if(from_d2 > to_d2){
	            var year = to_d1 - from_d1 - 1
	            var month1 = from_d2 - to_d2
	            var month = 12 - month1
	    }
	    if(from_d2 == to_d2 && from_d3 > to_d3){
	            var year = to_d1 - from_d1 - 1
	            var days = from_d3 - to_d3
	    }
	    if(from_d2 == to_d2 && to_d3 > from_d3){
	            var year = to_d1 - from_d1 
	            var days =  to_d3 - from_d3 
	    }
	    if(from_d2 == to_d2 && to_d3 == from_d3){
	            var year = to_d1 - from_d1 
	            var days = 0
	    }
	    //days
	    if(from_d2 > to_d2 && from_d3 > to_d3){
	            var m = from_d2 - to_d2 
	            var n = m * 30
	            var m1 = from_d3 - to_d3 
	            var m2 = n+m1
	            var days =  m2
	    }
	    if(from_d2 > to_d2 && to_d3 > from_d3){
	            var m = from_d2 - to_d2 
	            var n = m * 30
	            var m1 =  to_d3 - from_d3 
	            var m2 = n+m1
	            var days =  m2
	    }
	    if(to_d2 > from_d2   && to_d3 > from_d3){
	            var m =  to_d2 - from_d2 
	            var n = m * 30
	            var m1 =  to_d3 - from_d3 
	            var m2 = n+m1        
	            var days =  m2 
	    }
	    if(to_d2 >  from_d2 && from_d3 > to_d3){
	            var m = to_d2 - from_d2   
	            var n = m * 30
	            var m1 = from_d3 - to_d3 
	            var m2 = n+m1
	            var days =  m2
	    }
	    $(".get-value").text(""+year+" Years");
	  	 return(year);
	}
	
	function Validation() {
// --23-02-2023

		if( $("#internship_completion_date").val() == "DD/MM/YYYY"){
			$("#internship_completion_date").val("");
		}

		const Cdob = changeDateFormate($("#dob").val());
				
				const CAdm = changeDateFormateyear($("#date_of_admission").val());
				
				if($("#internship_completion_date").val() != "" ){
					var Cintern = changeDateFormate($("#internship_completion_date").val());
				if(Date.parse(Cdob) >= Date.parse(Cintern))  {
					alert("Internship Completion Date Can Not be Before Date of Birth.");
		 			$("#internship_completion_date").focus();
					return false;
			 	}
				}
				if(Date.parse(Cdob) >= Date.parse(CAdm))  {
					alert("Year Of Admission can not be before date of Birth.");
		 			$("#date_of_admission").focus();
					return false;
			 	}
				if($("#internship_completion_date").val() != "" ){
				const Cintern = changeDateFormate($("#internship_completion_date").val());
				if(Date.parse(CAdm) >= Date.parse(Cintern))  {
					alert("Internship Completion Date can Not be Before Year Of Admission.");
		 			$("#date_of_admission").focus();
					return false;
			 	}
				}
		if ($("#name").val().trim() == "") {
			alert("Please Enter Full Name");
// 			16_02_2022 changes
			$("input#name").focus();
			return false;
		}
				if( $("#dob").val() == "" ||  $("#dob").val() == "DD/MM/YYYY"){
					alert("Please Enter Date of Birth");
					$("#dob").focus();
					return false;
			   	}
				
				  var today = new Date();
				    var ad = $("#admmisson_dt_hidden_whole").val();
// 				    --22-02-2022
				var yrr=calculate_age("dob");
				if(yrr <= 16 || yrr == "" || yrr == "0"){
						alert("Age Should Be Greater Than 17 Years");
				    	$("#dob").focus();
				    	return false;
				 }
				if ($("#aadhar_card").val().trim() == "") {
					alert("Please Enter Aadhaar Number"); 
					$("input#aadhar_card").focus();
					return false;
				}
// 				--22_02_2022
				if ($("#aadhar_card").val().trim().length <12) {
					alert("Enter Proper Aadhaar card Number");
					$("#aadhar_card").val("")
					return false
				}
				if ($("#email").val().trim() == "") {
					alert("Please Enter Email Id");
					$("input#email").focus();
					return false;
				}
				if ($("#mobile_no").val().trim() == "") {
					alert("Please Enter Mobile Number");
					$("input#mobile_no").focus();
					return false;
				}
				if ($("#gender").val().trim() == "0") {
					alert("Please Select Gender");
					$("select#gender").focus();
					return false;
				}
// --23-03-2023
debugger;
// 				var yrr=calculate_age("dob");
				if($("#internship_completion_date").val() != "" ){
					var yrrintern=calculate_ageDiff("dob","internship_completion_date");
					if(yrrintern <= 16 && yrrintern != "" && yrrintern != "0"){
							alert("Internship Completion Date Should Be Greater Than 17 Years");
					    	$("#internship_completion_date").focus();
					    	return false;
					 }
				}
				
// 				----17_02_2023
// 				if( $("#internship_completion_date").val() == "" ||  $("#internship_completion_date").val() == "DD/MM/YYYY"){
// 					alert("Please Enter Internship Completion Date");
// 					$("#internship_completion_date").focus();
// 					return false;
// 			   	}
				if ($("#reg_state").val().trim() == "0") {
					alert("Please Select Registration State");
					$("select#reg_state").focus();
					return false;
				}
				if ($("#state_reg_num").val().trim() == "") {
					alert("Please Enter State Registration Number");
//		 			16_02_2022 changes
					$("input#state_reg_num").focus();
					return false;
				}
// 				if ($("#system_id").val().trim() == "0") {
// 					alert("Please Select System");
// 					$("select#system_id").focus();
// 					return false;
// 				}
				if ($("#university_id").val().trim() == "0") {
					alert("Please Select University");
					$("select#university_id").focus();
					return false;
				}
				if ($("#institute_id").val().trim() == "0") {
					alert("Please Select Institute/College");
					$("select#institute_id").focus();
					return false;
				}
// 				--02-03-2023
				if ($("#university_id").val().trim() == "OTHER") {
				if ($("#university_name_other").val().trim() == "") {
					alert("Please Enter Other University");
					$("input#university_name_other").focus();
					return false;
				}
				if ($("#institute_name_other").val().trim() == "") {
					alert("Please Enter Other Institute/College");
					$("input#institute_name_other").focus();
					return false;
				}
				}
				if ($("#institute_id").val().trim() == "OTHER") {
					if ($("#institute_name_other").val().trim() == "") {
						alert("Please Enter Other Institute/College");
						$("input#institute_name_other").focus();
						return false;
					}
					}
// 				--23-03-2023
				var yrradmit=calculate_ageDiff("dob","date_of_admission");
				if(yrradmit <= 16 || yrradmit == "" || yrradmit == "0"){
						alert(" Year of Admission Should Be Greater Than 17 Years");
				    	$("#date_of_admission").focus();
				    	return false;
				 }
				if( $("#date_of_admission").val() == "" ||  $("#date_of_admission").val() == "DD/MM/YYYY"){
					alert("Please Enter Year of Admission");
					$("#date_of_admission").focus();
					return false;
			   	}
// 				--23-02-2023

		 
		return true;
	}
	// --15-03-2023
	function changeDateFormate(date){
		var date2 = date.split("/");
		var fdate = date2[2]+"-"+date2[1]+"-"+date2[0];
		return fdate;
	}
	function changeDateFormateyear(date){
		var date2 = date.split("/");
// 		var fdate = date2[2]+"-"+date2[1]+"-"+date2[0];
		var fdate = date+"-"+"01"+"-"+"01";
		return fdate;
	}
	
</script>
<script type="text/javascript" nonce="${cspNonce}">
	function getDistrictper() {

		var selval = $("#per_state").val();
		$("select#per_district").empty();

		$
				.post(
						"getDistrictOnstate?" + key + "=" + value,
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
							$("select#per_district").html(options);
						});
	}

	function getDegreeName(obj, R) {
		var DegreeName = $("#" + obj.id).val();
		$
				.post(
						'getDegreeName?' + key + "=" + value,
						{
							DegreeName : DegreeName
						},
						function(k) {
							var options = '';
							for (var i = 0; i < k.length; i++) {
								options += '<option value="'+k[i].id+'" name="' + k[i].degree_name+ '" >'
										+ k[i].degree_name + '</option>';
							}
							$("#DegreeName" + R).html(options);
							//			for (var i = 0; i < data.length - 1; i++) {
							//				datap = data[i].split(":");
							//				options += '<option value="'+datap[i].id+'" name="' + datap[]+ '" >'+ datap[0] + '</option>';
							//			}
							//			$("#rank").html(options);
							//			var q = '${list.rank}';
							//			if(q != ""){
							//				$("#rank").val(q);
							//			}
						});
	}
	
	
function getUniversity() {
		
		var selval = $("#system_id").val();
			
		$
				.post(
						"getUniversitylistbySystemforpractitioner?" + key + "=" + value,
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
// 							 options += '<option value="' + "OTHER" + '">'+ "OTHER" + '</option>';
							$("select#university_id").html(options);
						});
	}
	
function get_inst_by_uni_nch(val) {
	$.ajaxSetup({
		async : false
	});
	
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	$.post('get_inst_by_uni_nch_ctrl_practitioneer?' + key + "=" + value,{university_id : val},function(j) {
// 		alert(j);
			var options = '<option value="' + "0" + '">'
						+ "--Select--" + '</option>';
					for (var i = 0; i < j.length; i++) {
						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
								+ j[i][1] + '</option>';
					}
// 					 options += '<option value="' + "OTHER" + '">'+ "OTHER" + '</option>';
				$("select#institute_id").html(options);
		
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
}
// --23-02-2023
function myKeyPress(e){
    var keynum;
    if(window.event) { // IE                    
      keynum = e.keyCode;
    } else if(e.which){ // Netscape/Firefox/Opera                   
      keynum = e.which;
    }
    if(keynum==106 || keynum==107 || keynum==108 ||keynum==189 || keynum==190 || keynum==106 || keynum==32  || keynum==62 || keynum==60 || keynum==64 || keynum==34 || keynum==35 || keynum==36 || keynum==37 || keynum==43 || keynum==47 || keynum==91 || keynum==92 || keynum==93 || keynum==94)
        e.preventDefault();
  }
  
//   --13-03-2023
window.onload = () => {
	 const myInput = document.getElementById('state_reg_num');
	 myInput.onpaste = e => e.preventDefault();
	 const myInput1 = document.getElementById('aadhar_card');
	 myInput1.onpaste = e => e.preventDefault();
	  
}

	document.addEventListener('DOMContentLoaded', function() {
	debugger;
		document.getElementById('name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		document.getElementById('name').oninput = function() {
			this.value = this.value.toUpperCase()
		};
		
		document.getElementById('dob').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('dob').onfocus = function() {
			return this.style.color = '#000000';
		};
		document.getElementById('dob').onblur = function() {
			return clickrecall(this, 'DD/MM/YYYY');
			validateDate_BackDate(this.value, this);
		};
		document.getElementById('dob').onkeyup = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('dob').onchange = function() {
			return clickrecall(this, 'DD/MM/YYYY');
		};
		document.getElementById('dob').onchange = function() {
 			return calculate_age('dob');
 			
 		};
 				
 		
 		document.getElementById('date_of_admission').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_of_admission').onfocus = function() {
			return this.style.color = '#000000';
		};
		document.getElementById('date_of_admission').onblur = function() {
			return clickrecall(this, 'DD/MM/YYYY');
			validateDate_BackDate(this.value, this);
		};
		document.getElementById('date_of_admission').onkeyup = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_of_admission').onchange = function() {
			return clickrecall(this, 'DD/MM/YYYY');
		};
		
// 		document.getElementById('date_of_admission').onchange = function() {
// 			if($("#internship_completion_date").val() != "" ){
// 			var yrradmit=calculate_ageDiff("dob","date_of_admission");
// 			if(yrradmit <= 16 || yrradmit == "" || yrradmit == "0"){
// 					alert(" Year of Admission Should Be Greater Than 17 Years");
// 			    	$("#date_of_admission").focus();
// 			    	return false;
// 			 }
// 			}
//  			return true;
// 		};
 			document.getElementById('date_of_admission').onchange = function() {
		
			var Cdob = changeDateFormate($("#dob").val());
			
			var CAdm = changeDateFormateyear($("#date_of_admission").val());
			if(Date.parse(Cdob) >= Date.parse(CAdm))  {
				alert("Year Of Admission can not be before date of Birth.");
	 			$("#date_of_admission").focus();
				return false;
		 	}
			
			var yrradmit=calculate_ageDiff("dob","date_of_admission");
			if(yrradmit <= 16 || yrradmit == "" || yrradmit == "0"){
					alert(" Year of Admission Should Be Greater Than 17 Years");
			    	$("#date_of_admission").focus();
			    	return false;
			 }
 			return true;
		};
		
		
		document.getElementById('aadhar_card').onkeypress = function() {
			return isNumberKey0to9(event);
		};
		document.getElementById('aadhar_card').oninput = function() {
			this.value = this.value.toUpperCase()
		};
		document.getElementById('email').onchange = function() {
			return checkgmail(this.value);
		};
		document.getElementById('email').oninput = function() {
			this.value = this.value.toLowerCase()
		};
		document.getElementById('mobile_no').oninput = function() {
			this.value = this.value.toUpperCase()
		};
		document.getElementById('mobile_no').onkeypress = function() {
			return isNumberKey0to9(event);
		};
		document.getElementById('mobile_no').onchange = function() {
			return mobileNumber(this);
		};
// 		--13/03/2023
		document.getElementById('aadhar_card').onchange = function() {
			if (this.value.length <12) {
				alert("Enter Proper Aadhaar card Number");
				this.value = "";
				return false
			}
		};
		document.getElementById('internship_completion_date').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('internship_completion_date').onfocus = function() {
			return this.style.color = '#000000';
		};
		document.getElementById('internship_completion_date').onblur = function() {
			return clickrecall(this, 'DD/MM/YYYY');
			validateDate_BackDate(this.value, this);
		};
		document.getElementById('internship_completion_date').onkeyup = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('internship_completion_date').onchange = function() {
			return clickrecall(this, 'DD/MM/YYYY');
		};
//  		document.getElementById('internship_completion_date').onchange = function() {
//  			var yrrintern=calculate_ageDiff("dob","internship_completion_date");
// 			if(yrrintern <= 16 || yrrintern == "" || yrrintern == "0"){
// 					alert("Internship Completion Date Should Be Greater Than 17 Years");
// 			    	$("#internship_completion_date").focus();
// 			    	return false;
// 			 }
//  			return true;
// 		};

			document.getElementById('internship_completion_date').onchange = function() {
 			if($("#internship_completion_date").val() != "" && $("#internship_completion_date").val() != "DD/MM/YYYY"){
 				
			var Cdob = changeDateFormate($("#dob").val());
				
				
					var Cintern = changeDateFormate($("#internship_completion_date").val());
				if(Date.parse(Cdob) >= Date.parse(Cintern))  {
					alert("Internship Completion Date Can Not be Before Date of Birth.");
		 			$("#internship_completion_date").focus();
					return false;
			 	}
 			var yrrintern=calculate_ageDiff("dob","internship_completion_date");
			if(yrrintern <= 16 || yrrintern == "" || yrrintern == "0"){
					alert("Internship Completion Date Should Be Greater Than 17 Years.");
			    	$("#internship_completion_date").focus();
			    	return false;
			 }
 			}
 			return true;
		};
		
		document.getElementById('draft').onclick = function() {
			return Validation();
		};
		document.getElementById('system_id').onchange = function() {
			getUniversity();
		};
// 		--01-03-2023
// 		document.getElementById('institute_name_other').oninput = function() {
// 			this.value = this.value.toUpperCase()
// 		};
// 		document.getElementById('university_name_other').oninput = function() {
// 			this.value = this.value.toUpperCase()
// 		};
		document.getElementById('university_id').onchange = function() {
			if (this.value.trim() == "OTHER") {
				$("#university_name_otherDiv").show();
				$("#institute_id").val("OTHER");
				$("#institute_id").trigger("change");
			}else{
				get_inst_by_uni_nch(this.value);
				$("#university_name_otherDiv").hide();
				$("#institute_id").trigger("change");
			}
			
		};
		document.getElementById('institute_id').onchange = function() {
			if (this.value.trim() == "OTHER") {
				$("#institute_name_otherDiv").show();
				
			}
			else {
				$("#institute_name_otherDiv").hide();
			}
		};
// 		--23-02-2023
		document.getElementById('state_reg_num').onkeypress = function() {
			return myKeyPress(event);
		};
		
	});
	
	function inyear() {
		 var currentYear = new Date().getFullYear()
		    max = currentYear 
		    var option = "";
		    for (var year = currentYear-70 ; year <= max; year++) {
		        var option = document.createElement("option");
		        option.text = year;
		        option.value = year;	        
		        document.getElementById("date_of_admission").appendChild(option);
		        $("#date_of_admission").val(currentYear);  
		    }
		   // document.getElementById("passing_year").value = currentYear;	
	}


</script>
