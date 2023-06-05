<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
						<h1 class="title-single">Help</h1>
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="landingpage">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">Help</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Intro Single-->

	<!--  help desk Section start-->
	<section class="section-help">
		<div class="container">
			<div class="row">
				<div class="col-12 col-lg-12 col-md-12 col-sm-12">
					<ul class="nav nav-pills custom-pill-tablist" id="pills-tab"
						role="tablist">
						<li class="nav-item" role="presentation">
							<button class="nav-link active" id="pills-report-issue-tab"
								data-bs-toggle="pill" data-bs-target="#pills-report-issue"
								type="button" role="tab" aria-controls="pills-report-issue"
								aria-selected="true">
								<i class="bi bi-bug"></i>Report your Inquiry
							</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="pills-check-status-tab"
								data-bs-toggle="pill" data-bs-target="#pills-check-status"
								type="button" role="tab" aria-controls="pills-check-status"
								aria-selected="false">
								<i class="bi bi-clipboard2-check"></i>Check case status
							</button>
						</li>
					</ul>
					<div class="tab-content" id="pills-tabContent">
						<div class="tab-pane fade show active" id="pills-report-issue"
							role="tabpanel" aria-labelledby="pills-report-issue-tab">
							<div class="custom-tab-inner">
								<div class="card-style">
									<div class="row">
										<div class="col-lg-6 col-md-12 col-sm-12 col-12">
											<div class="custom-img">
												<img src="admin/assets/img/svg/report-cause-img.gif" alt="report-cause-img" class="img-fluid">
											</div>
										</div>
										<div class="col-12 col-sm-12 col-md-12 col-lg-6">
											<form:form name="linkmaster" id="linkmaster"
												action="Helpdesk_Inqiry_Action" method="post"
												modelAttribute="Helpdesk_Inqiry_CMD"
												enctype="multipart/form-data" class="form-inner-common">
												<div class="row">
													<div class="col-12 col-sm-12 col-md-12 col-lg-12">
														<div class="alert alert-success custom-d-none" id="case_succ_msg_div">
															<strong>Successfully!</strong> Your case is reported. Please, check case status after some period of duration.
														</div>
													</div>
													<div class="col-12 col-sm-12 col-md-6 col-lg-6">													
									 <div class="form-group">
										<label class=" form-control-label">Ayush system<span
											class="mandatory">*</span></label><select name="system_id"
											id="system_id"
											class=" form-control form-control-lg select2 narrow wrap">
											<option value="0">-- Select --</option>
											<c:forEach var="item" items="${getsysList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
										</select> <input type="hidden" id="id" name="id" value="0" class="form-control form-control-lg" autocomplete="off" />
									</div>
								</div>
												<div class="col-12 col-sm-12 col-md-6 col-lg-6">
													<div class="form-group">
														<label>Choose category<span class="mandatory">*</span>
														</label><select id="categary_id" name="categary_id"
												class="form-control form-control-lg select2 narrow wrap"
												 >
												<option value="0" id="0" name=" ">-- Select --</option>
												<option value="ncism_council" id="ncism_council" name="ncism_council">NCISM</option>
												<option value="nch_council" id="nch_council" name="nch_council">NCH</option>
												<option value="Institute" id="Institute" name="Institute">Institute</option>
												<option value="council" id="council" name="council">State Council</option>
												<option value="27" id="nchpract" name="practfactregistration" >Practitioner</option>
												<!-- <option value="24" id="nchfact" name="practfactregistration">Faculty</option> 
												<option value="25" id="Student" name="Student">Student</option>
												<option value="35" id="Intern" name="Intern">Intern</option>
												<option value="Principal" id="Principal" name="Principal">Principal</option>
												<option value="University" id="University" name="University">University</option>
												
												<option value="board" id="board" name="ncism_council">Board</option>
												
												<option value="cour_pub" id="cour_pub" name="cour_pub">Course Publisher</option>
												<option value="alumni" id="alumni" name="alumni">Alumni</option>
												<option value="tpo" id="tpo" name="tpo">TPO</option>
												<option value="counselor" id="counselor" name="counselor">Counselor</option> -->
											</select>
														<div class="note-text text-light">
															<p class="input-text-below">
																e.g. Institute, Student etc
															</p>
														</div>
													</div>
												</div>
												<div class="col-12 col-sm-12 col-md-6 col-lg-6">
													<div class="form-group">
														<label>State<span class="mandatory">*</span>
														</label> <select name="state" id="state"
															class="form-control form-control-lg form-control-a">
															<option value="0">-- Select --</option>
															<c:forEach var="item" items="${MedStateName}" varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="col-12 col-sm-12 col-md-6 col-lg-6 d-none">
													<div class="form-group">
														<label>OTP<span class="mandatory">*</span></label>
														<div class="input-group custom-input-group">
															<button type="button" name="gtOTP" class="btn btn-a"
																id="getOTP">Get-OTP</button>
															<input id="newpassword" name="newpassword"
																class="otp form-control form-control-lg form-control-a disablecopypaste"
																maxlength="6" placeholder="Enter OTP">
														</div>
														<div class="note-text text-light">
															<p class="input-text-below">
																Resend OTP? <a href="#" id="click_here">Click Here</a>
															</p>
														</div>
													</div>
												</div>
												<div class="col-12 col-sm-12 col-md-6 col-lg-6">
													<div class="form-group">
														<label>Mobile number<span class="mandatory">*</span></label>
														<input type="text" id="mobile_no" name="mobile_no" maxlength="10"
															class="form-control form-control-lg"
															placeholder="Enter mobile number">
													</div>
												</div>
												<div class="col-12 col-sm-12 col-md-6 col-lg-6">
													<div class="form-group">
														<label>Email ID<span class="mandatory">*</span></label> <input
															type="text" id="email" name="email"
															class="form-control form-control-lg"
															placeholder="Enter Email ID">
													</div>
												</div>
												<div class="col-12 col-sm-12 col-md-6 col-lg-6">
													<div class="form-group">
														<label>Choose case category<span class="mandatory">*</span>
														</label>  <select name="Inq_Cat" id="Inq_Cat" class="form-control form-control-lg select2 narrow wrap">
													<option value="0">-- Select --</option>
													<c:forEach var="item" items="${getInq_CatList}" varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.inq_cat}</option>
													</c:forEach>
												</select>
													</div>
												</div>
												 
												<div class="col-12 col-sm-12 col-md-6 col-lg-6">
													<div class="form-group ">
														<label>Description<span class="mandatory">*</span></label>
														<textarea id="description" name="description"
															class="form-control form-control-lg" rows="3"
															placeholder="Description"></textarea>
													</div>
												</div>
												
												<div class="col-12 col-sm-12 col-md-6 col-lg-6">
													<div class="form-group">
													<label>Any attachment<span class="mandatory"></span></label>  <input type="file" accept="image/*"
														id="attachment" name="attachment" class="form-control">
													<input type="hidden" id="attachment_hid" name="attachment_hid"
														class="form-control"> <input type="hidden"
														id="upload_img_forV" name="upload_img_forV"
														class="form-control" value="0"> <span
														class="errorClass" id="upload_photo_doc_lbl"></span>
													<div class="note-text text-light">
															<p class="input-text-below">
																e.g. Screenshot etc
															</p>
														</div>
													</div>
												</div>
												<div class="col-lg-12 col-md-12 col-sm-12">
													<div class="custom-main-btn">
														<ul class="buttons-group mainbtn justify-content-center">
															<li><input type="submit" class="main-btn info-btn btn-hover btnsubmit"
																id="draft" name="draft" value="Submit"></li>
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
						<div class="tab-pane fade" id="pills-check-status" role="tabpanel"
							aria-labelledby="pills-check-status-tab">
							<div class="custom-tab-inner">
								<div class="card-style">
									<div class="row">
										<div class="col-lg-6 col-md-12 col-sm-12 col-12">
											<div class="custom-img">
												<img src="admin/assets/img/svg/check-status-img.gif" alt="check-status-img" class="img-fluid">
											</div>
										</div>
										<div class="col-12 col-sm-12 col-md-12 col-lg-6">

											<div class="row">
												<div class="col-12 col-sm-12 col-md-12 col-lg-12">
													<div class="form-group">
														<label>Case ID<span class="mandatory">*</span></label>
														<div class="input-group custom-input-group">
															<input id="case_id" name="case_id"
																class="form-control form-control-lg"
																placeholder="Enter Case ID"> <a href="#"
																class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"><i
																class="bi bi-search" id="case_idbt"></i>Check Status</a>
														</div>

													</div>
												</div>
<!-- 												<div class="col-12 col-sm-12 col-md-12 col-lg-12"> -->
<!-- 													<div class="alert alert-success d-block"> -->
<!-- 														<strong>Success!</strong> This alert box could indicate a -->
<!-- 														successful or positive action. -->
<!-- 													</div> -->
<!-- 												</div> -->
												<div class="col-12 col-sm-12 col-md-12 col-lg-12 custom-d-none" id="danger_div">
													<div class="alert alert-danger">
														<strong>Incorrect Case ID!</strong> Please check Case ID and fill it correctly.
													</div>
												</div>											
												<div class="col-12 col-sm-12 col-md-12 col-lg-12 custom-d-none" id="case_status_div">
												<!-- for resolved status -->
													<div class="border-card low" id="case_statusbox_div"> 
														<button class="bg-transparent border-button" type="button">
															<div class="border-card-header">
																<span class="priority">Status: <b id="status_lbl"></b></span>
															</div>
															<div class="border-card-content">
															<h6 class="bcard-title">
																<span class="sub-title">Case Category: </span>
																<span id="case_cat_lbl"></span>
															</h6>	
															<p class="bcard-description">
																<span class="sub-title">Response: </span>
																<span id="desc_note"> </span>
															</p>															
															</div>
														</button>
													</div>
													
													<!-- for in progress status -->
<!-- 													<div class="border-card medium d-none"> -->
<!-- 														<button class="bg-transparent border-button" type="button"> -->
<!-- 															<div class="border-card-header"> -->
<!-- 																<span class="priority">Status: <b id="status_lbl"></b></span> -->
<!-- 															</div> -->
<!-- 															<div class="border-card-content"> -->
<!-- 															<h6 class="bcard-title"> -->
<!-- 																<span class="sub-title">Cause: </span> -->
<!-- 																<span id="desc_note">If haven't received the login credentials</span> -->
<!-- 															</h6>	 -->
<!-- 															<p class="bcard-description"> -->
<!-- 																<span class="sub-title">Solution: </span> -->
<!-- 																<span id="desc_note">Select Category – Institute and enter your <b>Username &amp; Password</b> received in email from commission and enter -->
<!-- 																Captcha code and click on <b>Sign In</b> </span> -->
<!-- 															</p>															 -->
<!-- 															</div> -->
<!-- 														</button> -->
<!-- 													</div> -->
													
												</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--  help desk Section end-->
</section>


<script nonce="${cspNonce}" type="text/javascript">
var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";
	$(document).ready(function() {
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
		$.ajaxSetup({
			async : false
		});
		try {
			var msg = document.getElementById("msg").value;
			if (msg != null) {
				alert(msg);
			}
		} catch (e) {
		}
		
		
		$("#case_succ_msg_div").hide();
		try {
			var inqno = '${inqno}';
			if (inqno != "") {
				$("#case_succ_msg_div").show();
				$("#case_succ_msg_div").html("<strong>Successfully!</strong> Your case is reported. Your <strong>Case ID:</strong> <span class='case_number'>"+inqno+"</span> <br>Please check case status using Case ID after some Time.");
			}
		} catch (e) {
		}
// 		datepicketDate('dob');
	});
	
	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('case_id').onchange = function() {
			search_case();
		};
		document.getElementById('case_idbt').onclick = function() {
			search_case();
		};
		

		
		
		document.getElementById('Inq_Cat').onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this)
		};
		
		document.getElementById('description').oninput = function () {
			this.value = this.value.toUpperCase()
		};
		
		document.getElementById('attachment').onchange = function() {
//  			CVValidate();
 			return imgFileSizeValidation(this,this.value,'photo_path','50kb','upload_photo_doc_lbl');
 		};
 		
 		document.getElementById('email').onchange = function() {
 			return checkgmail(this.value);
 		};
 		
 		document.getElementById('mobile_no').oninput = function () {
 			this.value = this.value.toUpperCase()
		};
		document.getElementById('mobile_no').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('mobile_no').onchange = function() {
			 return mobileNumber(this);
 		};
 		document.getElementById('state').onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this)
		};
 		document.getElementById('draft').onclick = function() {
			return Validate();
		};
	});
	
 		function mobileNumber(obj){
 			_mobile = obj.value;
 			var regExp =/^[6789]\d{9}$/;
 		    if(_mobile == '' || !regExp.test(_mobile)){
 		        alert('Please Enter Valid Number');
 		        $('#'+obj.id).focus();
 		        $('#'+obj.id).val("");
 		        return false;
 		    }
 		}
 		
 		function checkgmail(email1) {
 			 document.getElementById("email").innerHTML="";
 			if(/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
 			}else{
 				alert("Please Enter Valid Email Id");
 				$("input#email").focus();
 				$("input#email").val('');
 				return false ;
 			}
 		}
 		
 		function CVValidate(){
 			var path = $("#attachment").val();
 			$("#attachment_hid").val(path); 
 		}
 		
 		function deleteData(id) {
 			$("#id1").val(id);
 			document.getElementById('deleteForm').submit();
 		}
 		
 		
 	function Validate() {
 		debugger
 		if ($("#system_id").val().trim() == "0") {
 			alert("Please Select System");
 			$("select#system_id").focus();
 			return false;
 		}
 		if ($("#categary_id").val().trim() == "0") {
 			alert("Please Select Category");
 			$("select#categary_id").focus();
 			return false;
 		}
 		if ($("#state").val().trim() == "0") {
 			alert("Please Select State");
 			$("select#state").focus();
 			return false;
 		}
 		if ($("#mobile_no").val().trim() == "") {
 			alert("Please Enter Mobile Number");
 			$("input#mobile_no").focus();
 			return false;
 		}
 		 
 		if ($("#email").val().trim() == "") {
 			alert("Please Enter Email Id");
 			$("input#email").focus();
 			return false;
 		}
 		if ($("#Inq_Cat").val().trim() == "0") {
 			alert("Please Select Case Category");
 			$("select#Inq_Cat").focus();
 			return false;
 		}
 		if ($("#description").val().trim() == "") {
 			alert("Please Enter Description");
 			$("textarea#description").focus();
 			return false;
 		}
//  		if ($("#attachment_hid").val().trim() == "") {
//  			alert("Please Select Attachment");
//  			$("input#attachment").focus();
//  			return false;
//  		}
		
 		
 		
 		return true;
 	}
 	
 	function search_case(){
 	 var case_id  = $("#case_id").val();
 	 if (case_id.trim()=="") {
		alert("Please Enter Your Case ID");
		$("#case_id").focus();
	}
 	$.post("getINQ_Status?" + key + "=" + value, {
 		
 		case_id : case_id
 		
 		
 	}, function(j) {
 		console.log(j)
 		if (j[0]!="") {
 			$("#case_status_div").show();
 			$("#danger_div").hide();
 			
			 if (j[0]==0) {
				 
				 $('#case_statusbox_div').addClass('medium');
				 $('#case_statusbox_div').removeClass('low');
	 			$("#status_lbl").html("In Progress");
	 			$("#desc_note").html("Your case is in-progress. we will update it soon! Please check your <b>Case</b> status periodically.");
	 			$("#case_cat_lbl").html(j[2]);
	 			
			}
	 		else if (j[0]==2) {
	 			 $('#case_statusbox_div').removeClass('medium');
				 $('#case_statusbox_div').addClass('low');
	 			$("#status_lbl").html("Resolved!");
	 			$("#desc_note").html(j[1]);
	 			$("#case_cat_lbl").html(j[2]);
			}
	 		else  {
	 			$('#case_statusbox_div').removeClass('medium');
				 $('#case_statusbox_div').addClass('low');
	 			$("#status_lbl").html("Ongoing Process");
	 			$("#desc_note").html(j[1]);
	 			$("#case_cat_lbl").html(j[2]);
			}
 		}
 		else {
 			$("#danger_div").show();
 			$("#case_status_div").hide();
 			
		}
 	});
 	
 	}
	
</script>