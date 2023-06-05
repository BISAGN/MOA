<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <script src="js/miso/miso_js/jquery-2.2.3.min.js"></script> -->
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- <script type="text/javascript" -->
<!-- 	src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<script type="text/javascript" src="js\watermark\common.js"></script>
<!-- <script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script> -->
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							Update Faculty Detail<span class="text-red font-size12 enter_by"></span>
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update Faculty Detail</li>
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
					<form:form name="Edit_FacDetail" id="Edit_FacDetail"
						action="Edit_FacDetail_Action" method="post"
						modelAttribute="Edit_FacDetailCMD">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Update Faculty Detail</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Name<span class="mandatory">*</span></label> <input
											type="text" id="name" name="name" maxlength="100"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" placeholder="Enter Name">
											<input type="hidden" name="editId" id="editId" value="0" />
											<input type="hidden" name="facuserid" id="facuserid" value="0" />
									</div>
									<!-- end input -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Aadhaar Number<span class="mandatory">*</span></label> <input
											type="text" id="aadhaar" name="aadhaar" maxlength="12"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" placeholder="Enter Aadhaar Number">
									</div>
									<!-- end input -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Email<span class="mandatory">*</span></label> <input
											type="text" id="email" name="email" maxlength="100"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" placeholder="Enter Email">
									</div>
									<!-- end input -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Mobile no.<span class="mandatory">*</span></label> <input
											type="text" id="mobile" name="mobile" maxlength="10"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" placeholder="Enter Mobile no.">
									</div>
									<!-- end input -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4" id="teacher_code_div">
									<div class="input-style-2">
										<label>Teacher Code<span class="mandatory">*</span></label> <input
											type="text" id="teachercode" name="teachercode" maxlength="12"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" placeholder="Enter Teacher Code">
									</div>
									<!-- end input -->
								</div>
								
							</div>
							</div>
							
							
						<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
								<ul class="buttons-group d-flex justify-content-center">
									<li><a href="exp_excel_faculty_url"
										class="main-btn dark-btn-outline   btn-hover btn-iconic-icon btnback">
											<i class="lni lni-chevron-left"></i>Back
									</a></li>
									<li><input type="submit" id="btn-update" value="Update"
										class="main-btn deactive-btn btn-hover btnupda" /></li>
								</ul>
								</div>
							</div>
						</div>
						</div>

						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>

	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">

	$(document).ready(function() {
		if('${role}' == 'Institute_NCH' || '${role}' == 'Principal_NCH'){
			$("div#teacher_code_div").hide();
		}
		
		$("#name").val('${Facdata.username}');
		$("#aadhaar").val('${Facdata.aadhar_card}');
		$("#email").val('${Facdata.email}');
		$("#mobile").val('${Facdata.mobile_no}');
		$("#teachercode").val('${Facdata.teacher_code}');
		$("#editId").val('${Facdata.id}');
		$("#facuserid").val('${Facdata.userid}');

	});

	
	function Validation() {
		if ($("input#name").val().trim() == "") {
			alert("Please Enter Name");
			$("input#name").focus();
			return false;
		}
		if ($("input#aadhaar").val().trim() == "") {
			alert("Please Enter Aadhaar Number");
			$("input#aadhaar").focus();
			return false;
		}
		if ($("input#aadhaar").val().trim().length != 12) {
			alert("Please Enter Valid Aadhaar Number");
			$("input#aadhaar").focus();
			return false;
		}
		if ($("input#email").val().trim() == "") {
			alert("Please Enter Email");
			$("input#email").focus();
			return false;
		}
		if ($("input#mobile").val().trim().length != 10) {
			alert("Please Enter Valid Mobile no.");
			$("input#mobile").focus();
			return false;
		}
		if ($("input#mobile").val().trim() == "") {
			alert("Please Enter Mobile no.");
			$("input#mobile").focus();
			return false;
		}
		
		var mob = $("#mobile");
		if (mob.val().length < 10) {
			alert('Please Enter Valid Mobile Number');
			// 		        $('#'+mob.attr("id")).focus();
			// 		        $('#'+mob.attr("id")).val("");
			mob.focus();
			mob.val("");
			return false;
		}
		_mobile = mob.val();
		var regExp = /^[6789]\d{9}$/;
		if (_mobile == '' || !regExp.test(_mobile)) {
			alert('Please Enter Number Start with 6,7,8,9 Digit');
			mob.focus();
			mob.val("");
			return false;
		}

		
		if('${role}' == 'Institute_NCISM' || '${role}' == 'Principal_NCISM'){
			if ($("input#teachercode").val().trim() == "") {
				alert("Please Enter Teacher Code");
				$("input#teachercode").focus();
				return false;
			}
		}
		return true;
	}

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('btn-update').onclick = function() {
			return Validation();
		};

		document.getElementById('name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		
		document.getElementById('email').onchange = function() {
			return checkgmail(this.value, this.id);
		};
		
		document.getElementById('mobile').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('aadhaar').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};

	});
	
	
	function checkgmail(email1) {
		
		 document.getElementById("email").innerHTML="";
		var pattern=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		
//		 var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
		 
		if(email1.match(pattern)) {
		}else{
			
			alert("Please Enter Valid Email Address");
			$("input#email").focus();
			$("input#email").val('');
			return false ;
		}
	}
</script>

