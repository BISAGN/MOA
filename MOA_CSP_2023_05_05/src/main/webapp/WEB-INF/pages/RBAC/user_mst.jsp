<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- <script src="js/JS_CSS/jquery-2.2.3.min.js"></script> -->
<link rel="stylesheet" href="js/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<!-- <script src="js/dropDown/select2.min.js" type="text/javascript"></script> -->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>User Creation</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">User
									Creation</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form name="user_mst" id="user_mst" action="user_mstAction"
						method='POST' modelAttribute="user_mstCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">User Creation</h6>
								<div class="row">
								<div class="inst_block">
										<h6 class="mb-1">Instruction</h6>
										<ul class="inst_list">
											<li><p class="inst_text">Password should be a mix of
													alphabets, numerals and special characters ($#^@%_.~!*)
													without any space in between.</p></li>
											<li><p class="inst_text">Password must contain both
													upper and lowercase letters.</p></li>
											<li><p class="inst_text">Password length should be
													between 8 to 28 characters.</p></li>
										</ul>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>User Name<span class="mandatory">*</span></label> <input
												type="text" id="login_name" name="login_name"
												class="autocomplete UpperClassName txt-transupp"
												placeholder="Enter User Name" maxlength="70"
												autocomplete="off" required>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>User Id<span class="mandatory">*</span></label> <input
												type="text" id="user_name" name="user_name" maxlength="30"
												class="autocomplete UpperClassName txt-transupp"
												placeholder=" Enter User Id" autocomplete="off" required>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>Id No<span class="mandatory">*</span></label> <input
												type="text" id="army_no" name="army_no" maxlength="9"
												class="autocomplete UpperClassName txt-transupp"
												placeholder="Enter Id No" autocomplete="off" required>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>Mobile No<span class="mandatory">*</span></label> <input
												type="text" id="mobile_no" name="mobile_no" maxlength="10"
												class="autocomplete UpperClassName txt-transupp"
												placeholder="Enter Mobile No" autocomplete="off" required>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>Email Id<span class="mandatory">*</span></label> <input
												type="email" id="email_id" name="email_id"
												class="form-control" placeholder="abc@example.com"
												autocomplete="off" maxlength="70" value="${email_txt}"
												required>

										</div>
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>Password<span class="mandatory">*</span></label> <input
												id="user_password" type="password" maxlength="28"
												name="user_password" autocomplete="off" required
												pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">

										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>Re-Password<span class="mandatory">*</span></label> <input
												id="user_re_password" type="password" maxlength="28"
												name="user_re_password"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" required
												pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label>Role<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="user_role_id" id="user_role_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getRoleNameList}"
														varStatus="num">
														<option value="${item[0]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3"
										id="university_div">
										<div id="select-style-1" class="select-style-1">
											<label>University Name<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="university_id" id="university_id">
													<option value="0">--Select--</option>
													<%-- 													<c:forEach var="item" items="${getUniversityList}" varStatus="num" > --%>
													<%-- 													<option value="${item[0]}">${item[1]}</option> --%>
													<%-- 												 </c:forEach>					                 --%>
												</select>
											</div>
										</div>
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-3" id="inst_div">
										<div id="select-style-1" class="select-style-1">
											<label class="form-control-label">College Name<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="institute_id" id="institute_id">
													<option value="0">--Select--</option>
													<%-- 														<c:forEach var="item" items="${getapp_instituteNameList}" varStatus="num" > --%>
													<%-- 													<option value="${item[0]}">${item[1]}</option> --%>
													<%-- 												 </c:forEach>	 --%>
												</select>
											</div>
											<input type="hidden" id="id" name="id" value="0"
												class="form-control form-control-lg form-control-a effect-9"
												autocomplete="off" />

										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3"
										id="coursepb_div">
										<div id="select-style-1" class="select-style-1">
											<label>Course Publisher<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="course_pb" id="course_pb">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getCoursePubName}"
														varStatus="num">
														<option value="${item[0]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-4 col-sm-12" id="state_div">
										<div class="select-style-1">
											<label> State <strong class="mandatory">* </strong></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="state_id" id="state_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${MedStateName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}
														</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>


<!-- 									<div class="inst_block"> -->
<!-- 										<h6 class="mb-1">Instruction</h6> -->
<!-- 										<ul class="inst_list"> -->
<!-- 											<li><p class="inst_text">Password should be a mix of -->
<!-- 													alphabets, numerals and special characters ($#^@%_.~!*) -->
<!-- 													without any space in between.</p></li> -->
<!-- 											<li><p class="inst_text">Password must contain both -->
<!-- 													upper and lowercase letters.</p></li> -->
<!-- 											<li><p class="inst_text">Password length should be -->
<!-- 													between 8 to 28 characters.</p></li> -->
<!-- 										</ul> -->
<!-- 									</div> -->
									<!-- 									<div class="row mb-3"> -->
									<!-- 										<label for="passid"> <b>1) Password should be a -->
									<!-- 												mix of alphabets, numerals and special characters -->
									<!-- 												($#^@%_.~!*) without any space in between.</b><br> <b>2) -->
									<!-- 												Password must contain both upper and lowercase letters.</b><br> -->
									<!-- 											<b>3) Password length should be between 8 to 28 -->
									<!-- 												characters.</b> -->
									<!-- 										</label> -->
									<!-- 									</div> -->
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><input class="main-btn info-btn btn-hover btnsave"
												value="Save" id="btn-save" type="submit"></li>
											<li><a href="user_mstUrl"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->

						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	function isValid() {

		if ($("#login_name").val().trim() == "") {
			alert("Please Enter User Name");
			$("#login_name").focus();
			return false;
		}
		if ($("#user_name").val().trim() == "") {
			alert("Please Enter User ID");
			$("#user_name").focus();
			return false;
		}
		if ($("#army_no").val().trim() == "") {
			alert("Please Enter Id No");
			$("#army_no").focus();
			return false;
		}
		if ($("#mobile_no").val().trim() == "") {
			alert("Please Enter Mobile No");
			$("#mobile_no").focus();
			return false;
		}
		if ($("#email_id").val().trim() == "") {
			alert("Please Enter Email Id");
			$("#email_id").focus();
			return false;
		}
		if ($("#user_password").val() == "") {
			alert("Please Enter User Password");
			$("#user_password").focus();
			return false;
		}
		if ($("#user_password").val().length < 8
				| $("#user_password").val().length > 28) {
			alert("Please Enter Password at least 8 to 28 digit");
			$("#user_password").focus();
			return false;
		}
		if ($("#user_re_password").val().trim() == "") {
			alert("Please Enter User Re-Password");
			$("#user_re_password").focus();
			return false;
		}

		if ($("#user_password").val() != $("#user_re_password").val()) {
			alert("Password and Re-Password didn't Match");
			$("#user_re_password").focus();
			return false;
		}

		if ($("#user_re_password").val().length < 8
				| $("#user_re_password").val().length > 28) {
			alert("Please Enter Re-Password at least 8 to 28 digit");
			$("#user_re_password").focus();
			return false;
		}

		if ($("select#user_role_id").val() == "0") {
			alert("Please Select Role");
			$("select#user_role_id").focus();
			return false;
		}

		var user_role_id = $("#user_role_id").val();
		if (user_role_id == "20" || user_role_id == "19") {

			if ($("#university_id").val() == "0") {
				alert("Please Select University Name");
				$("select#university_id").focus();
				return false;
			}
		}
		if (user_role_id == "22" || user_role_id == "23"
				|| user_role_id == "24") {

			if ($("#institute_id").val() == "0") {
				alert("Please Select College Name");
				$("select#institute_id").focus();
				return false;
			}
		}
		if (user_role_id == "31") {

			if ($("#course_pb").val() == "0") {
				alert("Please Select Course Publisher");
				$("select#course_pb").focus();
				return false;
			}
		}

		return true;
	}

	$(document).ready(function() {

		$("input#login_name").val("");
		$("#user_name").val("");
		$("#army_no").val("");
		$("#user_password").val("");
		$("#user_re_password").val("");
		$("#countryState_div").hide();
		$("#coursepb_div").hide();
		$("#university_div").hide();
		$("#inst_div").hide();
		$("#state_div").hide();

		if (window.location.href.includes("msg")) {
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		}

	});

	document.addEventListener('DOMContentLoaded', function() {
		$.ajaxSetup({
			async : false
		});
		document.getElementById('btn-save').onclick = function() {
			return isValid();
		};

		// 		document.getElementById('btn-reset').onclick = function() {
		// 			clearall();
		// 		};

		document.getElementById('email_id').onkeyup = function() {
			return validateEmail(this);
			//checkgmail(this.value);
		};
		document.getElementById('email_id').onchange = function() {
			return checkgmail(this.value);
		};
		document.getElementById('mobile_no').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};

		document.getElementById('mobile_no').onchange = function() {
			return mobileNumber(this);
		};
		document.getElementById('army_no').onkeypress = function() {
			return onlyAlphaNumeric(event, this);
		};
		document.getElementById('army_no').onkeyup = function() {
			return toUpperCase(this.value);
		};
		document.getElementById('user_name').onkeyup = function() {
			debugger;
			return isAlphaNumeric_SpecialsWithSpace(this);
			onlyAlphabetsStringSpace(this, event);
			// 			return onlyAlphaNumeric(event, this);
		};
		document.getElementById('army_no').onkeyup = function() {
			return isAlphaNumeric_SpecialsWithSpace(this);
		};
		document.getElementById('user_role_id').onchange = function() {
			getuniversitylist();
			getInstitutelist();
			hideshw_country_state();
		};
		document.getElementById('login_name').onkeyup = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		// 		document.getElementById('user_role_id').onchange = function() {
		// 			hideshw_country_state();
		// 		};

		// 		document.getElementById('gender_name').onkeypress = function() {
		// 			return onlyAlphabetsStringSpace(this, event);
		// 		};

	});

	function getuniversitylist() {
		var user_role_id = $("#user_role_id").val();
		$
				.post(
						"getuniversitylistrole?" + key + "=" + value,
						{
							user_role_id : user_role_id
						},
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][0]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#university_id").html(options);
							$("#university_id").trigger('change');
						});
	}

	function getInstitutelist() {

		var user_role_id = $("#user_role_id").val();

		$
				.post(
						"getInstitutelistrole?" + key + "=" + value,
						{
							user_role_id : user_role_id
						},
						function(j) {

							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][0]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#institute_id").html(options);
						});
	}

	function hideshw_country_state() {
		if ($("#user_role_id").val() == "11" ) {
			$("#countryState_div").show();
		} else {
			$("#countryState_div").hide();
		}

		if ($("#user_role_id").val() == "31" ) {
			$("#inst_div").hide();
			$("#coursepb_div").show();
		} else {
			$("#inst_div").show();
			$("#coursepb_div").hide();
		}

		if ($("#user_role_id").val() == "19"
				|| $("#user_role_id").val() == "20") {
			$("#university_div").show();
			$("#inst_div").hide();
		} else {
			$("#university_div").hide();
		}
		if ($("#user_role_id").val() == "22"
				|| $("#user_role_id").val() == "21"
				|| $("#user_role_id").val() == "23" ) {
			$("#inst_div").show();
			$("#university_div").hide();
		} else {
			$("#inst_div").hide();

		}

		if ($("#user_role_id").val() == "30") {
			$("#state_div").show();
		} else {
			$("#state_div").hide();
		}

		if ($("#user_role_id").val() == "24") {
			$("#inst_div").show();
			$("#university_div").show();
			$("#state_div").show();
		}

		if ($("#user_role_id").val() == "51"
				|| $("#user_role_id").val() == "52") {
			$("#inst_div").show();
			$("#university_div").show();
			$("#state_div").show();
		}

		if ($("#user_role_id").val() == "30"
				|| $("#user_role_id").val() == "43") {
			$("#state_div").show();
		} else {
			$("#state_div").hide();
		}
		if ($("#user_role_id").val() == "67" || $("#user_role_id").val() == "29"){
			$("#inst_div").show();
		}
	}

	function mobileNumber(obj) {
		if (obj.value.length < 10) {
			alert('Please Enter valid Number');
			$('#' + obj.id).focus();
			$('#' + obj.id).val("");
			return false;
		}
		_mobile = obj.value;

		var regExp = /^[6789]\d{9}$/;
		if (_mobile == '' || !regExp.test(_mobile)) {
			alert('Please Enter Number Start with 6789 Digit');
			$('#' + obj.id).focus();
			$('#' + obj.id).val("");
			return false;
		}
	}

	function checkgmail(email1) {

		document.getElementById("email_id").innerHTML = "";

		var pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

		//		 var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

		if (email1.match(pattern)) {

		} else {

			alert("Please Enter Valid Email Id");
			$("input#email_id").focus();
			$("input#email_id").val('');
			return false;
		}
	}

	function validateEmail(email_id) {
		var email_id = $("#email_id").val();
		for (var i = 0; i < email_id.length; i++) {
			var ch = email_id.substring(i, i + 1);
			if (ch.charCodeAt(0) >= 65 && ch.charCodeAt(0) <= 90) {
				alert("Please Enter Email in Lowercase");
				$("#email_id").val("");
				$("#email_id").focus();
			} else {
				//alert("valid"); 
				true;
			}
		}
	}
	// 	function getinstitutelist() {
	//		var countryval = $("#institute_id").val();

	//		$
	//				.post(
	//						"getStatebyCountry?" + key + "=" + value,
	//						{.trim()
	//							countryval : countryval
	//						},
	//						function(j) {

	//							var options = '<option value="' + "0" + '">'
	//									+ "--Select--" + '</option>';
	//							for (var i = 0; i < j.length; i++) {

	//								options += '<option   value="' + j[i].state_id + '" name="'+j[i].state_id+'" >'
	//										+ j[i].institute_name + '</option>';
	//							}
	//						});
	//	}
</script>