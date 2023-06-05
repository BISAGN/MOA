<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- <script src="js/JS_CSS/jquery-2.2.3.min.js"></script> -->
<link rel="stylesheet" href="js/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/watermark/common.js" type="text/javascript"></script>
<!-- <script src="js/dropDown/select2.min.js" type="text/javascript"></script> -->

<!-- Custom tour style and js start-->
<link rel="stylesheet" href="assets/vendor/custom_tour/custom-tour.css">
<script type="text/javascript" src="assets/vendor/custom_tour/custom-tour.js"></script>
<!-- Custom tour style and js End-->

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
								<li class="breadcrumb-item active" aria-current="page">User Creation</li>
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
					<form:form name="user_mst" id="user_mst" action="staff_user_mstAction"
						method='POST' modelAttribute="user_mstCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">User Creation</h6>
								<div class="row">
								<div class="inst_block" id="tg-inst-block-s2">
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
										<div class="input-style-1" id="tg-form-f1-s3">
											<label>User Name<span class="mandatory">*</span></label> <input
												type="text" id="login_name" name="login_name"
												class="autocomplete UpperClassName txt-transupp"
												placeholder="Enter User Name" maxlength="70"
												autocomplete="off" required>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1" id="tg-form-f2-s4">
											<label>User Id<span class="mandatory">*</span></label> <input
												type="text" id="user_name" name="user_name" maxlength="30"
												class="autocomplete UpperClassName txt-transupp"
												placeholder=" Enter User Id" autocomplete="off" required>
										</div>
									</div>
									
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1" id="tg-form-f3-s5">
											<label>Mobile No<span class="mandatory">*</span></label> <input
												type="text" id="mobile_no" name="mobile_no" maxlength="10"
												class="autocomplete UpperClassName txt-transupp"
												placeholder="Enter Mobile No" autocomplete="off" required>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1" id="tg-form-f4-s6">
											<label>Email Id<span class="mandatory">*</span></label> <input
												type="email" id="email_id" name="email_id"
												class="form-control" placeholder="abc@example.com"
												autocomplete="off" maxlength="70" value="${email_txt}"
												required>

										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1" id="tg-form-f5-s7">
											<label>Password<span class="mandatory">*</span></label> <input
												id="user_password" type="password" maxlength="28"
												name="user_password" autocomplete="off" required
												pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">

										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1" id="tg-form-f6-s8">
											<label>Re-Password<span class="mandatory">*</span></label> <input
												id="user_re_password" type="password" maxlength="28"
												name="user_re_password"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" required
												pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
										</div>
									</div>

<!-- 									<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
<!-- 										<div class="select-style-1"> -->
<!-- 											<label>Role<span class="mandatory">*</span></label> -->
<!-- 											<div class="select-position"> -->
<!-- 												<select class="singleselect form-control form-control-lg" -->
<!-- 													name="user_role_id" id="user_role_id"> -->
<!-- 													<option value="0">--Select--</option> -->
<%-- 													<c:forEach var="item" items="${getRoleNameList}" --%>
<%-- 														varStatus="num"> --%>
<%-- 														<option value="${item[0]}">${item[1]}</option> --%>
<%-- 													</c:forEach> --%>
<!-- 												</select> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li id="tg-savebtn-s9"><input class="main-btn info-btn btn-hover btnsave"
												value="Save" id="btn-save" type="submit"></li>
											<li id="tg-resetbtn-s10"><a href="satff_user_mstUrl"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->

						</div>
						<!-- end card -->
					</form:form>
					
					<div class="card-style mb-30">
						<h6 class="mb-10">User credential details</h6>
						<div class="table-wrapper table-responsive custom-table" id="tg-custom-table-s11">
									<table class="table">
										<thead>
											<tr>
												<th><h6>Sr No</h6></th>
												<th><h6>User Name</h6></th>
												<th><h6>User ID</h6> </th>
												<th><h6>Mobile No</h6></th>
												<th><h6>Email</h6></th>												
											</tr>
											<!-- end table row-->
										</thead>
										<tbody>
										<c:if test="${getInstituteListPrincipal.size()==0}">
														<tr id="" >
														<td class="" colspan="5">
																<div id="" >DATA NOT AVAILABLE</div>
															</td>
														</tr>
													</c:if>
										<c:forEach var="item" items="${getInstituteListPrincipal}" varStatus="num">
							<tr>
							
												<td class="">
													<p>${num.index+1}</p> 
												</td>
												<td>
													<p>${item[1]}</p>
												</td>
												<td>
													<p>
														${item[2]}
													</p>
												</td>
												<td>
													<p>${item[3]}</p>
												</td>
												<td>
													<p>${item[4]}</p>
												</td>
											</tr>
					
									</c:forEach>																				
										</tbody>
									</table>
									<!-- end table -->
								</div>
					</div>
					
				</div>
			</div>
		</div>	
		
		<!-- custom-tour button start -->
		<div class="custom-setting-icons">
			<a id="startTourBtn" class="custom-setting-link" title="Explor the tour">
			<i class="bi bi-globe"></i>
			<span class="custom-setting-text">Tour</span></a>
		</div>
		<!-- custom-tour button end -->
			
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

		return true;
	}

	$(document).ready(function() {

// 		$("input#login_name").val("");
// 		$("#user_name").val("");
// 		$("#army_no").val("");
// 		$("#user_password").val("");
// 		$("#user_re_password").val("");
// 		$("#countryState_div").hide();
// 		$("#coursepb_div").hide();
// 		$("#university_div").hide();
// 		$("#inst_div").hide();
// 		$("#state_div").hide();

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
		
		document.getElementById('user_name').onkeypress = function() {
		
			return onlyAlphaNumeric(event, this);

			//onlyAlphabetsStringSpace(this, event);
			// 			return onlyAlphaNumeric(event, this);
		};
		
// 		document.getElementById('user_role_id').onchange = function() {
// 			getuniversitylist();
// 			getInstitutelist();
// 			hideshw_country_state();
// 		};
		document.getElementById('login_name').onkeypress = function() {
			return onlyAlphaNumeric(event, this);
		};
		// 		document.getElementById('user_role_id').onchange = function() {
		// 			hideshw_country_state();
		// 		};

		// 		document.getElementById('gender_name').onkeypress = function() {
	
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

<!-- custom-tour script user creation start -->

<script nonce="${cspNonce}" type="text/javascript">
        var steps = [
            {
            title: "Tour introduction",
            content: "<p><b class='tg-text-heighlight'>Welcome to tour guide!</b><br> These tour guide is for User creation. We are going to explore <b class='tg-text-heighlight'>User creation</b> process for <b class='tg-text-heighlight'>Institute</b>. Please, click on the Next button for explore the tour.</p>",
            url: "",
     		}, 
     		{
                id: "tg-inst-block-s2",
                title: "About instruction",
                content: "<p>Kindly read the instruction.</p>",
                url: "",
            }, 
            {
                id: "tg-form-f1-s3",
                title: "Username field",
                content: "<p>The first field in the user creation process is the <b class='tg-text-heighlight'>Username</b> field. Kindly enter the Username of the institute in the username field.</p>",
                url: "",
            }, 
            {
                id: "tg-form-f2-s4",
                title: "User ID field",
                content: "<p>This field in the user creation process is the <b class='tg-text-heighlight'>User ID</b> field. Kindly enter the User ID of the institute in the User ID field.</p>",
                url: "",
            },
            {
                id: "tg-form-f3-s5",
                title: "Mobile No field",
                content: "<p>This field in the user creation process is the <b class='tg-text-heighlight'>Mobile No</b> field. Kindly enter the Mobile No of the institute in the Mobile No field.</p>",
                url: "",
            },
            {
                id: "tg-form-f4-s6",
                title: "Email ID field",
                content: "<p>This field in the user creation process is the <b class='tg-text-heighlight'>Email ID</b> field. Kindly enter the Email ID of the institute in the Email ID field.</p>",
                url: "",
            },
            {
                id: "tg-form-f5-s7",
                title: "Password field",
                content: "<p>This field in the user creation process is the <b class='tg-text-heighlight'>Password</b> field. Kindly enter the Password in the Password field which you want to create for the institute.</p>",
                url: "",
            },
            {
                id: "tg-form-f6-s8",
                title: "Re-Password field",
                content: "<p>This field in the user creation process is the <b class='tg-text-heighlight'>Re-Password</b> field. Kindly fill the same password in the Re-Password field which you have filled in the password field.</p>",
                url: "",
            },
            {
                id: "tg-savebtn-s9",
                title: "Save button",
                content: "<p>After filling all the required details, you are now required to click on the <b class='tg-text-heighlight'>Save</b> button to create a new user.</p>",
                url: "",
            },
            {
                id: "tg-resetbtn-s10",
                title: "Reset button",
                content: "<p>In case you need to reset the details, you can click on the <b class='tg-text-heighlight'>Reset</b> button.</p>",
                url: "",
            },
            {
				id : "tg-custom-table-s11",
				title : "User credential details",
				content : "<p>Now, here you can see the <b class='tg-text-heighlight'>User credential details</b> table, where you can see all the previously created user's credential details.</p><br><p>Thank you for explore the tour guide.</p>",
				url : "",
			},
            ];
        var tour = new Tour(steps, {
        });
        document.getElementById("startTourBtn").addEventListener("click", function () {
            tour.show();
        });
    </script>
    
    <!-- custom-tour script user creation end -->