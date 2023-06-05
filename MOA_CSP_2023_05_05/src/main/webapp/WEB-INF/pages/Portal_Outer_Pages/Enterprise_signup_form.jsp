<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<link href="admin/assets/img/favicon.ico" rel="icon">
<link href="admin/assets/font/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="admin/assets/vendor/animate.css/animate.min.css"
	rel="stylesheet">
<link href="admin/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="admin/assets/vendor/swiper/swiper-bundle.min.css"
	rel="stylesheet">
<script type="text/javascript" src="admin/js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="admin/js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="admin/js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="admin/js/autoComplate/autoComplate.css">

<script type="text/javascript" src="admin/assets/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="admin/js/watermark/common.js"></script>

<link rel="stylesheet" href="admin/js/autoComplate/autoComplate.css">
<link href="admin/js/dropDown/select2.min.css" rel="Stylesheet"></link>

<script src="admin/js/dropDown/select2.min.js" type="text/javascript"></script>

<link href="admin/assets/vendor/hover.css" rel="stylesheet" media="all">
<link href="admin/assets/css/style.css" rel="stylesheet">
<link href="admin/assets/css/custom_pages_style.css" rel="stylesheet">
<link href="admin/assets/css/responsive.css" rel="stylesheet">
<link rel="stylesheet"
	href="admin/assets/vendor/common_custom_style.css">
<link href="admin/assets/vendor/svg-animation.css" rel="stylesheet"
	media="all">

<section class="page-content">
<section class="intro-single">
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-lg-8">
				<div class="title-single-box">
					<h1 class="title-single">Enterprise/Hospital/Other-Registration
						Form</h1>
					<!--  <span class="color-text-a">Your message has been sent. Thank you! </span> -->
				</div>
			</div>
			<div class="col-md-12 col-lg-4">
				<nav aria-label="breadcrumb"
					class="breadcrumb-box d-flex justify-content-lg-end">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="landingpage">Home</a></li>
						<li class="breadcrumb-item active" aria-current="page">Enterprise/Hospital/Other-Registration
						Form</li>
					</ol>
				</nav>
			</div>
		</div>
	</div>
</section>
<section class="contact">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="card-style">
					<form:form name="linkmaster" id="linkmaster"
						action="placement_company_reg_Action" method="post"
						class="form-horizontal" modelAttribute="placement_company_reg_CMD"
						enctype="multipart/form-data">
						<div class="row">

							<div class="col-lg-3 col-md-6 col-sm-12 col-12">
								<div class="form-group">
									<label>Enterprise Name <span class="mandatory">*</span></label> <input
										type="text" id="company_name" name="company_name"
										placeholder="Enterprise Name" maxlength="50"
										class="form-control form-control-lg form-control-a"
										autocomplete="off">
								</div>
							</div>

							<div class="col-lg-3 col-md-6 col-sm-12 col-12">
								<div class="form-group">
									<label>Owner's Name <span class="mandatory">*</span></label> <input type="text" id="name" name="name"
										placeholder="Owner's Name" maxlength="50"
										class="form-control form-control-lg form-control-a"
										autocomplete="off">
								</div>
							</div>

							<div class="col-lg-3 col-md-6 col-sm-12 col-12">
								<div class="form-group">
									<label>Email Id <strong class="mandatory">*</strong></label> <input
										type="email" id="email_id" name="email_id"
										pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
										class="form-control form-control-lg form-control-a"
										autocomplete="off" placeholder="Email Id">
								</div>
							</div>

							<div class="col-lg-3 col-md-6 col-sm-12 col-12">
								<div class="form-group">
									<label>Mobile Number <span class="mandatory">*</span></label>
									<input type="text" id="mobile_no" name="mobile_no"
										class="form-control form-control-lg form-control-a"
										maxlength="10" minlength="10" placeholder="Mobile Number">
								</div>
							</div>

							<div class="col-lg-3 col-md-6 col-sm-12 col-12">
								<div class="form-group">
									<label>Landline Number <span class="mandatory">*</span></label>
									<input type="text" id="ph_no" name="ph_no"
										class="form-control form-control-lg form-control-a"
										maxlength="10" minlength="10" placeholder="Landline Number">
								</div>
							</div>

							<div class="col-lg-3 col-md-6 col-sm-12 col-12">
								<div class="form-group">
									<label>Adress <span class="mandatory">*</span></label> <input
										type="text" id="address" name="address"
										placeholder="Address" maxlength="50"
										class="form-control form-control-lg form-control-a"
										autocomplete="off">
								</div>
							</div>

							<div class="col-lg-3 col-md-6 col-sm-12 col-12">
								<div class="form-group">
									<label for="username">State<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="state" id="state"
											class="form-control form-control-lg form-control-a">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${MedStateName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
									<input type="hidden" id="id" name="id" class="form-control"
										value="0" autocomplete="off">
								</div>
							</div>

							<div class="col-lg-3 col-md-6 col-sm-12 col-12">
								<div class="form-group">
									<label for="username">District<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="per_district" id="per_district"
											class="form-control form-control-lg form-control-a">
											<option value="0">--Select--</option>
										</select>
									</div>
								</div>
							</div>

							<div class="col-lg-3 col-md-6 col-sm-12 col-12">
								<div class="form-group">
									<label>Pin Code <span class="mandatory">*</span></label>  <input type="text" id="pincode"
										name="pincode" maxlength="6" minlength="6"
										class="form-control form-control-lg form-control-a"
										autocomplete="off" placeholder="Pin Code">

								</div>
							</div>

							<div class="col-lg-3 col-md-6 col-sm-12 col-12">
								<div class="form-group">
									<label>Awards </label> <input type="text" id="awards"
										name="awards" maxlength="50"
										class="form-control form-control-lg form-control-a"
										autocomplete="off" placeholder="Awards">

								</div>
							</div>



							<div class="col-lg-3 col-md-6 col-sm-12 col-12">
								<div class="form-group">
									<label>Hours Of Operation From  <span class="mandatory">*</span></label> 
									<input type="time" data-component="range"
										class="form-control form-control-lg form-control-a"
										id="hours_from" name="hours_from" size="5"
										placeholder="Hours Of Operation From" autocomplete="off">

								</div>
							</div>

							<div class="col-lg-3 col-md-6 col-sm-12 col-12">
								<div class="form-group">
									<label>Hours Of Operation To <span class="mandatory">*</span></label> <input type="time" data-component="range"
										class="form-control form-control-lg form-control-a"
										id="hours_to" name="hours_to" size="5" autocomplete="off"
										placeholder="Hours Of Operation To">

								</div>
							</div>

							<div class="col-lg-3 col-md-6 col-sm-12 col-12">
								<div class="form-group">
									<label>Website URL  <span class="mandatory">*</span></label> <input type="text"
										id="web_url" name="web_url" maxlength="50"
										class="form-control form-control-lg form-control-a"
										autocomplete="off" placeholder="Website URL">

								</div>
							</div>

							<div class="col-lg-3 col-md-6 col-sm-12 col-12">
								<div class="form-group">
									<label>Logo  <span class="mandatory">*</span></label>  <input type="file" accept="image/*"
										id="photo_path" name="photo_path" class="form-control">
									<input type="hidden" id="upload_img_hid" name="upload_img_hid"
										class="form-control"> <input type="hidden"
										id="upload_img_forV" name="upload_img_forV"
										class="form-control" value="0"> <span
										class="errorClass" id="upload_photo_doc_lbl"></span>

								</div>
							</div>

							<div class="col-lg-3 col-md-6 col-sm-12 col-12">
								<div class="form-group">
									<label>Picture  <span class="mandatory">*</span></label> <input
										type="file" accept="image/*" id="photo_path_pic"
										name="photo_path_pic" class="form-control"> <input
										type="hidden" id="upload_img_pic_hid"
										name="upload_img_pic_hid" class="form-control"> <input
										type="hidden" id="upload_img_pic_forV"
										name="upload_img_pic_forV" class="form-control" value="0">
									<span class="errorClass" id="upload_photo_doc_pic_lbl"></span>

								</div>
							</div>

							<!-- 							  <div class="col-lg-3 col-md-6 col-sm-12 col-12"> -->
							<!-- 								<div class="input-style-1"> -->
							<!-- 							 	<label for="username">Do You Want To Get Notify For Intern Details</label> -->
							<!-- 									<span -->
							<!-- 										class="mandatory">*</span> -->
							<!-- 										<input type="hidden" id="notified_hid" name="notified_hid" value="0">  -->
							<!-- 										<input type="checkbox" id="notified" name="notified" checked = "checked"> -->

							<!-- 								</div> -->
							<!-- 							</div> -->

							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="input-style-form-check_block">
									<div class="form-check checkbox-style form-group">
										<input type="hidden" id="notified_hid" name="notified_hid"
											value="0"> <input class="form-check-input"
											type="checkbox" value="" id="notified" name="notified"
											checked="checked"> <label class="form-check-label"
											for="notified">Do You Want To Get Email For Intern
											Details</label> <input type="hidden" id="notified_hid"
											name="notified_hid" value="0">
									</div>
								</div>

								<!-- 							<div class="col-lg-12 col-md-12 col-sm-12 mb-3 justify-content-center d-flex"> -->

								<!-- 								<div -->
								<!-- 									class="col-lg-12 col-md-12 col-sm-12 mb-3 justify-content-center d-flex"> -->
								<!-- 									<div class="custom-btn footer-btn"> -->
								<!-- 										<ul class="footer-btn-list"> -->
								<!-- 											<li class="main-btn secondary-btn btn-hover btn-save"><input type="submit" -->
								<!-- 												class="btn btn-bh" id="btn-reload" value="Save" id="save_reg" name="save_reg" ></li> -->
								<!-- 											<li class="main-btn dark-btn n btn-hover"><a href="company_signup_Url" -->
								<!-- 												value="Reset" type="button">Reset</a></li> -->
								<!-- 										</ul> -->
								<!-- 									</div> -->
								<!-- 								</div> -->

								<!-- 							</div> -->

							</div>
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="custom-main-btn">
										<ul class="buttons-group mainbtn justify-content-center">


											<li><input type="submit" id="btn-reload" value="Save"
												id="save_reg" name="save_reg"
												class="main-btn success-btn btn-hover"></li>
											<li><a class="main-btn dark-btn n btn-hover"
												href="company_signup_Url" value="Reset" type="button">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>
							</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

</section>


<%-- <c:url value="Edit_edu_reg_mstrUrl" var="Edit_edu_reg_mstrUrl" /> --%>
<%-- <form:form action="${Edit_edu_reg_mstrUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id1"> --%>
<!-- 	<input type="hidden" name="id1" id="id1" value="0" /> -->
<%-- </form:form> --%>
<%--  <c:url value="delete_edu_reg_mstr_Url" var="delete_edu_reg_mstr_Url" /> --%>
<%-- <form:form action="${delete_edu_reg_mstr_Url}" method="post" id="deleteForm" --%>
<%-- 	name="deleteForm" modelAttribute="id2"> --%>
<!-- 	<input type="hidden" name="id2" id="id2" value="0" /> -->
<%-- </form:form> --%>
<%-- <c:url value="Excel_Auth_Posted_query" var="excelUrl" /> --%>
<%-- <form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="cont_comd_ex"> --%>
<!-- 	 <input type="hidden" name="cont_comd_ex" id="cont_comd_ex"  value="0"> -->
<!-- 	   <input type="hidden" name="cont_corps_ex" id="cont_corps_ex" value="0"> -->
<!-- 	   <input type="hidden" name="cont_div_ex" id="cont_div_ex" value="0"> -->
<!-- 	   <input type="hidden" name="cont_bde_ex" id="cont_bde_ex" value="0"> -->
<!-- 	   <input type="hidden" name="cont_comd_txt" id="cont_comd_txt" > -->
<!-- 	   <input type="hidden" name="cont_corps_txt" id="cont_corps_txt"> -->
<!-- 	   <input type="hidden" name="cont_div_txt" id="cont_div_txt"> -->
<!-- 	   <input type="hidden" name="cont_bde_txt" id="cont_bde_txt"> -->
<!-- 	   <input type="hidden" name="unit_name_ex" id="unit_name_ex"> -->
<!-- 	   <input type="hidden" name="sus_no_ex" id="sus_no_ex"> -->
<!-- 	   <input type="hidden" name="typeReport1" id="typeReport1" value="0" /> -->
<%-- </form:form>  --%>

<!-- The Modal -->
<div id="myModal" class="modal">
	<span class="close">&times;</span> <img class="modal-content"
		id="img01">
	<div id="caption"></div>
</div>

<c:url value="Ga_Po_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	// ==URMIK
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";

	$(document).ready(function() {

		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
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
	});

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('company_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this)
		};
		document.getElementById('company_name').oninput = function() {
			this.value = this.value.toUpperCase()
		};
		document.getElementById('name').oninput = function() {
			this.value = this.value.toUpperCase()
		};
		document.getElementById('name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this)
		};
		document.getElementById('email_id').onchange = function() {
			return checkgmail(this.value);
		};
		document.getElementById('mobile_no').oninput = function() {
			this.value = this.value.toUpperCase()
		};
		document.getElementById('mobile_no').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('mobile_no').onchange = function() {
			return mobileNumber(this);
		};
		document.getElementById('ph_no').oninput = function() {
			this.value = this.value.toUpperCase()
		};
		document.getElementById('ph_no').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('ph_no').onchange = function() {
			return landlineNumber(this);
		};
		document.getElementById('address').oninput = function() {
			this.value = this.value.toUpperCase()
		};
		document.getElementById('state').onchange = function() {
			getDistrictper();
		};
		document.getElementById('pincode').onchange = function() {
			checkMinLength(this.id, 6);
			checkMaxLength(this.id, 6);
		};
		document.getElementById('pincode').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};

		document.getElementById('awards').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this)
		};
		document.getElementById('awards').oninput = function() {
			this.value = this.value.toUpperCase()
		};
		//  		document.getElementById('product').oninput = function () {
		// 			this.value = this.value.toUpperCase()
		// 		};
		document.getElementById('photo_path').onchange = function() {
			photoValidate();
			return imgFileSizeValidation(this, this.value, 'photo_path',
					'50kb', 'upload_photo_doc_lbl');
		};
		document.getElementById('photo_path_pic').onchange = function() {
			company_photoValidate();
			return companyimgFileSizeValidation(this, this.value,
					'photo_path_pic', '50kb', 'upload_photo_doc_pic_lbl');
		};
		document.getElementById('btn-reload').onclick = function() {
			return Validate();
		};
	});

	function getDistrictper() {
		var selval = $("#state").val();
		$("select#per_district").empty();
		$
				.post(
						"getDistrictOnPlacementreg?" + key + "=" + value,
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

	function mobileNumber(obj) {
		_mobile = obj.value;
		var regExp = /^[6789]\d{9}$/;
		if (_mobile == '' || !regExp.test(_mobile)) {
			alert('Please Enter Valid Number');
			$('#' + obj.id).focus();
			$('#' + obj.id).val("");
			return false;
		}
	}

	function landlineNumber(obj) {
		_mobile = obj.value;
		var regExp = /^[6789]\d{9}$/;
		if (_mobile == '' || !regExp.test(_mobile)) {
			alert('Please Enter Valid Number');
			$('#' + obj.id).focus();
			$('#' + obj.id).val("");
			return false;
		}
	}

	function checkgmail(email1) {
		document.getElementById("email_id").innerHTML = "";
		if (/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
		} else {
			alert("Please Enter Valid Email Id");
			$("input#email_id").focus();
			$("input#email_id").val('');
			return false;
		}
	}

	function checkgmail2(email1) {
		document.getElementById("email_id").innerHTML = "";
		if (/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
		} else {
			alert("Please Enter Valid Email Id");
			$("input#fm_email").focus();
			$("input#fm_email").val('');
			return false;
		}
	}

	function checkgmail4(email1) {
		document.getElementById("email_id").innerHTML = "";
		if (/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
		} else {
			alert("Please Enter Valid Email Id");
			$("input#fi_email").focus();
			$("input#fi_email").val('');
			return false;
		}
	}

	function checkMinLength(id, minleg) {
		var charLength = $("#" + id).val().length;
		if (charLength < minleg) {
			alert('Pincode Should Contain Minimum ' + minleg + ' Digit');
			$("#" + id).val("");
			$("#" + id).focus();
		}
	}

	function checkMaxLength(id, maxleg) {
		var charLength = $("#" + id).val().length;
		if (charLength > maxleg) {
			alert('Pincode Should Contain Maximum ' + maxleg + ' Digit');
			$("#" + id).val("");
			$("#" + id).focus();
		}
	}

	function photoValidate() {
		debugger;
		var path = $("#photo_path").val();
		$("#upload_img_hid").val(path);
	}

	function company_photoValidate() {
		var path = $("#photo_path_pic").val();
		$("#upload_img_pic_hid").val(path);
	}

	function CVValidate() {
		var path = $("#upload_cv").val();
		$("#upload_cv_hid").val(path);
	}

	// function setTimeLoadForTable(){
	// 	document.getElementById('btn-save').onclick = function() {
	// 		return Validation();
	// 	};

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}

	function Validate() {

		if ($("#notified").is(":checked") == true) {
			$("#notified_hid").val(1);
		} else {
			$("#notified_hid").val(0);
		}
		if ($("#company_name").val().trim() == "") {
			alert("Please Enter Enterprise Name");
			$("input#company_name").focus();
			return false;
		}
		if ($("#name").val().trim() == "") {
			alert("Please Enter Full Name");
			$("input#name").focus();
			return false;
		}
		if ($("#email_id").val().trim() == "") {
			alert("Please Enter Email Id");
			$("input#email_id").focus();
			return false;
		}
		if ($("#mobile_no").val().trim() == "") {
			alert("Please Enter Mobile Number");
			$("input#mobile_no").focus();
			return false;
		}
		if ($("#ph_no").val().trim() == "") {
			alert("Please Enter Landline Number");
			$("input#ph_no").focus();
			return false;
		}
		if ($("#address").val().trim() == "") {
			alert("Please Enter Permanet Address");
			$("input#address").focus();
			return false;
		}
		if ($("#state").val().trim() == "0") {
			alert("Please Select State");
			$("select#state").focus();
			return false;
		}
		if ($("#per_district").val().trim() == "0") {
			alert("Please Select District");
			$("select#per_district").focus();
			return false;
		}
		if ($("#pincode").val().trim() == "") {
			alert("Please Enter Pincode");
			$("input#pincode").focus();
			return false;
		}
		if ($("#hours_from").val().trim() == "") {
			alert("Please Enter Hours Of Operation From");
			$("input#hours_from").focus();
			return false;
		}
		if ($("#hours_to").val().trim() == "") {
			alert("Please Enter Hours Of Operation To");
			$("input#hours_to").focus();
			return false;
		}
		// 	if ($("#product").val().trim() == "") {
		// 		alert("Please Enter Product And Services");
		// 		$("input#product").focus();
		// 		return false;
		// 	}
		if ($("#web_url").val().trim() == "") {
			alert("Please Enter Website URL");
			$("input#web_url").focus();
			return false;
		}
		if ($("#upload_img_forV").val().trim() == "1") {
			alert("Please Upload Logo");
			$("input#upload_img_forV").focus();
			return false;
		}
		if ($("#upload_img_hid").val().trim() == "") {
			alert("Please Upload Logo");
			$("input#photo_path").focus();
			return false;
		}
		if ($("#upload_img_pic_forV").val().trim() == "1") {
			alert("Please Upload Picture");
			$("input#upload_img_pic_forV").focus();
			return false;
		}
		if ($("#upload_img_pic_hid").val().trim() == "") {
			alert("Please Upload Picture");
			$("input#photo_path_pic").focus();
			return false;
		}

		return true;
	}
</script>




