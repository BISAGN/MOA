<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <script type="text/javascript" src="assets/js/jquery-3.6.0.min.js"></script> -->
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<!-- <script type="text/javascript" -->
<!-- 	src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<link rel="stylesheet"href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- <script type="text/javascript" src="js/watermark/common.js"></script> -->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

<link rel="stylesheet" href="assets/vendor/internal_css.css">

<section class="search_regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>UnderGraduate Forms</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Degree
									Recognition</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->

		<div class="custom_v_tab">
			<div class="row">
				<div class="col-lg-12">
					<div class="card-style mb-30">
						<div class="tabs content_h500">
						
							<div class="tab tablinks" id="tab_id1">
								<button class="tab-toggle" id="defaultOpen">Undergraduate
									Courses in Homoeopathy In India</button>
							</div>

							<div class="content tabcontent">
								<h4 class="heading">For Undergraduate Course In Homoeopathy
									In India</h4>
								<div id="1" class="form-horizontal">
					
										<h6 class="mb-25">Degree Recognition</h6>
										
		<form:form name="Form_A_details" id="Form_A_details" action="form_a_action" method="post"
						       modelAttribute="UG_FormCMD" enctype="multipart/form-data">
						       
										<div class="row">
										
											<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
													<label>Name Of Applicant University </label> 
													<input type="text" name="name_of_applicant_university"
														id="name_of_applicant_university" class="form-control"
														placeholder="Enter Applicant University granting qualification"
														autocomplete="off" readonly="readonly">
												</div>
											</div>
											
											<input type="hidden" id="userId" name="userId" value="${userId}">
											
											<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
													<label>Full Name Of undergraduate Course</label><input type="text"
														name="name_ug_course" id="name_ug_course"
														class="form-control" readonly="readonly" value="BHMS">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
													<label>Abbreviation of Undergraduate Course</label> <input type="text"
														name="abbre_undergraduate_course"
														id="abbre_undergraduate_course" class="form-control"
														readonly="readonly" value="BHMS">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
													<label>College<strong
														class="mandatory">*</strong></label>
													<div class="select-position">
														<select class="singleselect form-control form-control-lg"
															name="institute_name" id="institute_name">
															<option value="0">--Select--</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
													<label>Academic Year Applied For<strong
														class="mandatory">*</strong></label> <input type="month"
														name="academic_year_applied_for"
														id="academic_year_applied_for" class="form-control">
												</div>
											</div>
										
										<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label for="username">Academic File Upload<span class="mandatory">*</span></label> 
												<div class="with_icon">
												<input type="file" accept=".pdf"
												id="academic_file_upload" name="academic_file_upload" class="form-control"
												autocomplete="off" />
												</div>
										</div>
									</div>
										<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label for="username">Permission From Central Government<span class="mandatory">*</span></label> 
												<div class="with_icon">
												<input type="file" accept=".pdf"
												id="permission_from_central_gov" name="permission_from_central_gov" class="form-control"
												autocomplete="off" />
												</div>
										</div>
									</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
													<label>Admission Intake Capacity Permitted<strong
														class="mandatory">*</strong></label> 
														<input type="text"	name="admission_intake" id="admission_intake"
																									class="form-control" placeholder="Enter Admission Intake Capacity permitted"
																									 autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
													<label>Number Of Student Admitted<strong
														class="mandatory">*</strong></label> 
														<input type="text" name="num_of_student_admitted" id="num_of_student_admitted" 
																							   class="form-control" placeholder="Enter Number of Student Admitted"
																									 autocomplete="off" >
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label>Postal Address<strong class="mandatory">*</strong></label> 
													<textarea name="postal_address" id="postal_address"
														class="form-control" placeholder="Enter Postal Address" 
														autocomplete="off"></textarea>
												</div>
											</div>
<!-- 											<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 												<div class="input-style-2"> -->
<!-- 													<label>Country<strong -->
<!-- 														class="mandatory">*</strong></label>  -->
<!-- 														<input type="text" name="country" id="country" class="form-control"  -->
<!-- 														readonly="readonly" value="India"> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 												<div class="select-style-1"> -->
<!-- 													<label>State</label> -->
<!-- 													<div class="select-position"> -->
<!-- 														<select name="state_id" id="state_id" class="form-control-lg select2 narrow wrap"> -->
<!-- 																<option value="0">---Select State---</option> -->
<%-- 																		<c:forEach var="item" items="${getMedStateName}" varStatus="num"> --%>
<%-- 																				<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 																		</c:forEach> --%>
<!-- 														</select> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 												<div class="select-style-1"> -->
<!-- 													<label>District</label> -->
<!-- 													<div class="select-position"> -->
<!-- 														<select name="district_id" id="district_id" class="form-control-lg select2 narrow wrap"> -->
<!-- 																<option value="0">---Select District---</option> -->
<%-- 																	<c:forEach var="item" items="${getMedDistrictName}" varStatus="num"> --%>
<%-- 																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 																	</c:forEach> --%>
<!-- 														</select> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 												<div class="select-style-1"> -->
<!-- 													<label>City</label> -->
<!-- 													<input type="text" name="city1" id="city1" class="form-control" -->
<!-- 																placeholder="Enter City" autocomplete="off"> -->
<!-- 												</div> -->
<!-- 											</div> -->
											
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label>Email<strong
														class="mandatory">*</strong></label>
													 <input type="text" name="email" id="email"  class="form-control" 
													 placeholder="Enter Email(abc@example.com)" maxlength="30" 
															pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
															value="${email_txt}" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label>Website<strong
														class="mandatory">*</strong></label>
													<input type="text" name="website" id="website"
															class="form-control" placeholder="Enter Website(http://example.com/)" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
											
												<div class="select-style-1">
													<label>Remarks,If any</label>
												<textarea name="remarks" id="remarks"
														class="form-control" placeholder="Enter Remarks, if any" 
														autocomplete="off"></textarea>
												</div>
											</div>
			</div><!-- End row -->
											<ul class="buttons-group mainbtn">
												<li><a href="Degree_rec_Url"
													class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
												<li><input type="button" id="btn-save"
													class="main-btn info-btn btn-hover" value="Save Details">
												<li><a href="UG_Datatable_url" id="btn-reload"
													class="main-btn secondary-btn btn-hover btn-iconic-icon"
													type="button"><i></i>Edit & View Details</a></li>
												<li>
											</ul>
											</form:form>
								</div>
							</div> <!--  End Form A -->

<!-- 							<div class="tab tablinks dg-rec-block" id="tab_id5"> -->
<!-- 								<button class="tab-toggle">Examiners Appointed in For	Examination</button> -->
<!-- 							</div> -->
<!-- 							<div id="Form_E" class="content tabcontent"> -->
<%-- 								<form id="form_E_details"> --%>
<!-- 									<h4 class="heading">Details Of Examiners Appointed In For Examination(Year Wise)</h4> -->
<!-- <!-- 									<div> --> -->
<!-- <!-- 										<span class="mandatory">(All fields are mandatory* )</span> --> -->
<!-- <!-- 									</div> --> -->
<!-- 									<div class="row"> -->
<!-- 										<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 											<div class="input-style-2"> -->
<!-- 												<input type="hidden" id="id_e" name="id_e"> <label>Name -->
<!-- 													Of Examiners External/Internal<strong class="mandatory">*</strong> -->
<!-- 												</label> <input type="text" name="name_of_examiners" -->
<!-- 													id="name_of_examiners" class="form-control" -->
<!-- 													placeholder="Enter name of Examiners External/Internal" -->
<!-- 													autocomplete="off"> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										<input type="hidden" id="form_e_id" name="form_e_id" value="0" -->
<!-- 											class="form-control autocomplete" autocomplete="off"> -->
<!-- 										<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 											<div class="input-style-2"> -->
<!-- 												<label>Subject<strong class="mandatory">*</strong></label> <input -->
<!-- 													type="text" name="subject_examiners" id="subject_examiners" -->
<!-- 													class="form-control" placeholder="Enter Subject" -->
<!-- 													autocomplete="off"> -->
<!-- 											</div> -->
<!-- 										</div> -->


<!-- 										<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 											<div class="input-style-2"> -->
<!-- 												<label>Year<strong class="mandatory">*</strong></label> <input -->
<!-- 													type="month" name="year_examiners" id="year_examiners" -->
<!-- 													maxlength="10" -->
<!-- 													class="form-control-sm form-control effect-9 " -->
<!-- 													aria-required="true" autocomplete="off" value="YYYY"> -->
<!-- 											</div> -->
<!-- 										</div> -->

<!-- 										<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 											<div class="input-style-2"> -->
<!-- 												<label>Qualification<strong class="mandatory">*</strong></label> -->
<!-- 												<input type="text" name="qualification_examiners" -->
<!-- 													id="qualification_examiners" class="form-control" -->
<!-- 													placeholder="Enter Qualification" autocomplete="off"> -->
<!-- 											</div> -->
<!-- 										</div> -->

<!-- 										<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 											<div class="input-style-2"> -->
<!-- 												<label>Teaching Experience<strong class="mandatory">*</strong></label> -->
<!-- 												<input type="text" name="teaching_experience" -->
<!-- 													id="teaching_experience" class="form-control" -->
<!-- 													placeholder="Enter Teaching Experience" autocomplete="off"> -->
<!-- 											</div> -->
<!-- 										</div> -->

<!-- 										<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 											<div class="input-style-2"> -->
<!-- 												<label>Teachers Code(Mention The Number)<strong -->
<!-- 													class="mandatory">*</strong></label> <input type="text" -->
<!-- 													name="teacher_code" id="teacher_code" class="form-control" -->
<!-- 													placeholder="Enter Teachers Code" autocomplete="off"> -->
<!-- 											</div> -->
<!-- 										</div> -->

<!-- 										<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 											<div class="input-style-2"> -->
<!-- 												<label>Registration Number<strong class="mandatory">*</strong></label> -->
<!-- 												<input type="text" name="reg_number" id="reg_number" -->
<!-- 													class="form-control" -->
<!-- 													placeholder="Enter Registration Number" autocomplete="off"> -->
<!-- 											</div> -->
<!-- 										</div> -->

<!-- 										<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 											<div class="input-style-2"> -->
<!-- 												<label>Date Of University Appointment Letter<strong -->
<!-- 													class="mandatory">*</strong></label> <input type="text" -->
<!-- 													name="d_university_appointment" -->
<!-- 													id="d_university_appointment" class="form-control" -->
<!-- 													placeholder="Enter Date Of University Appointment Letter" -->
<!-- 													accept=".pdf" value="DD/MM/YYYY" autocomplete="off"> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										<input type="hidden" id="userId" name="userId" -->
<%-- 											value="${userId}"> --%>

<!-- 										<div class="col-lg-12 col-md-12 col-sm-12"> -->
<!-- 											<ul class="buttons-group mainbtn"> -->
<!-- 												<li><a href="Degree_rec_Url" -->
<!-- 													class="main-btn dark-btn n btn-hover" type="button">Reset</a></li> -->
<!-- 												<li><input type="button" id="btn-save2" -->
<!-- 													class="main-btn info-btn btn-hover" value="Save Details"> -->
<!-- 												<li><a href="UG_Datatable_url" id="btn-reload" -->
<!-- 													class="main-btn secondary-btn btn-hover btn-iconic-icon" -->
<!-- 													type="button"><i></i>Edit & View Details</a></li> -->

<!-- 											</ul> -->
<!-- 										</div> -->
<!-- 									</div> -->
<%-- 								</form> --%>
<!-- 							</div>  End Form B -->
							
						</div>
					</div>
					
				</div>
			</div>
		</div>
		<!--   </body> -->
	</div>
</section>

<script type="text/javascript"
	src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>

<c:url value="UploadPaper_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<c:url value="viewForm_BDataUrl" var="mainFormUrl" />
<form:form action="${mainFormUrl}" method="POST" id="mainForm"
	name="mainForm">
	<input type="hidden" name="id" id="id" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		// alert('${roleid}')
		// alert('${getinstitute_listbyuniversity}')
		if (window.location.href.includes("msg")) {
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		}
	
		datepicketDate('d_university_appointment');
		get_inst_by_uni('${uni_id}');
		
// 		count_student_admitted();
		
		$("#name_of_applicant_university").val('${getuni_dtl[0][0]}');
	});

	document.addEventListener('DOMContentLoaded', function () {	

		document.getElementById('btn-save').onclick = function(){
			if(Validation_form()){
				$("#Form_A_details").submit();
 			}
		};
		document.getElementById('btn-save2').onclick = function() {
			return save_form_e();
		};
// 		document.getElementById('institute_name').onchange = function() {
// 			getDegreeListFromInstitute();
// 		};
		            document.getElementById('website').onkeypress = function(){
		            	return noSpace(event, this);
		            };
		            document.getElementById('postal_address').onkeypress = function(){
		            	return noSpace(event, this);
		            };
		            document.getElementById('admission_intake').onkeypress = function(){
		            	return isNumberKey0to9(event, this);
		            };
		            document.getElementById('num_of_student_admitted').onkeypress = function(){
		            	return isNumberKey0to9(event, this);
		            };
		            document.getElementById('remarks').onkeypress = function(){
		            	return noSpace(event, this);
		            };
		            document.getElementById('name_of_examiners').onkeypress = function(){
		           		 return onlyAlphabetsStringSpace(this,event);
		            };
		            document.getElementById('subject_examiners').onkeypress = function(){
		           	 	return onlyAlphabetsStringSpace(this,event);
		            };
		            document.getElementById('qualification_examiners').onkeypress = function(){
		           	 	return onlyAlphabetsStringSpace(this,event);
		            };
		            document.getElementById('teaching_experience').onkeypress = function(){
		           	 	return noSpace(this,event);
		            };
		            document.getElementById('email').onchange = function(){
			     		   return checkemail(this.value);
			          };
});
// 	function getDegreeListFromInstitute() {

// 		var institute_id = $("select#institute_name").val();
// 		$
// 				.post(
// 						"getDegreeList?" + key + "=" + value,
// 						{

// 							institute_name : institute_name
// 						},
// 						function(j) {
							
// 							var options = '<option value="' + "0" + '">'
// 									+ "--Select--" + '</option>';
// 							for (var i = 0; i < j.length; i++) {

// 								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
// 										+ j[i][1] + '</option>';
// 							}
// 							$("select#degree_id").html(options);
// 						});
// 	}
function Validation_form() {
	
		if($("#institute_name").val() == "0" || $("#institute_name").val() == ""){
			alert("Please Select Institute Name.");
			$("#institute_name").focus();
			return false;
		}
		if ($("#academic_year_applied_for").val().trim() == "") {
			alert("Please Select Academic Year Applied For.");
			$("input#academic_year_applied_for").focus();
			return false;
		}
		if ($("#academic_file_upload").val().trim() == "") {
			alert("Please Upload Academic File");
			$("input#academic_file_upload").focus();
			return false;
		}
		
		if ($("#admission_intake").val().trim() == "") {
			alert("Please Enter Admission Intake Capacity.");
			$("input#admission_intake").focus();
			return false;
		}
		if ($("#num_of_student_admitted").val().trim() == "") {
			alert("Please Enter Number Of Student Admitted.");
			$("input#num_of_student_admitted").focus();
			return false;
		}
		if ($("textarea#postal_address").val().trim() == "") {
			alert("Please Enter Postal Address.");
			$("textarea#postal_address").focus();
			return false;
		}
		if ($("#email").val().trim() == "") {
			alert("Please Enter Email.");
			$("input#email").focus();
			return false;
		}
		if ($("#website").val().trim() == "") {
			alert("Please Enter Website.");
			$("input#website").focus();
			return false;
		}
		return true;
	 }
function checkemail(email) {

	document.getElementById("email").innerHTML = "";
	if (/@gmail.com\s*$/.test(email) || /@yahoo.com\s*$/.test(email)) {

	} else {
		alert("Please Enter Valid Email Address");
		$("input#email").focus();
		$("input#email").val('');
		return false;
	}
}

	/////////////////////// Form E Validation //////////////////

// 	function save_form_e() {

// 		if ($("input#name_of_examiners").val().trim() == "") {
// 			alert("Please Enter Name Of Examiners");
// 			$("input#name_of_examiners").focus();
// 			return false;
// 		}
// 		if ($("input#subject_examiners").val().trim() == "") {
// 			alert("Please Enter Subject");
// 			$("input#subject_examiners").focus();
// 			return false;
// 		}
// 		if ($("input#year_examiners").val().trim() == "") {
// 			alert("Please Enter Year");
// 			$("input#year_examiners").focus();
// 			return false;
// 		}
// 		if ($("input#qualification_examiners").val().trim() == "") {
// 			alert("Please Enter Qualification");
// 			$("input#qualification_examiners").focus();
// 			return false;
// 		}
// 		if ($("input#teaching_experience").val().trim() == "") {
// 			alert("Please Enter Teaching Experience");
// 			$("input#teaching_experience").focus();
// 			return false;
// 		}
// 		if ($("input#teacher_code").val().trim() == "") {
// 			alert("Please Enter Teacher Code");
// 			$("input#teacher_code").focus();
// 			return false;
// 		}
// 		if ($("input#reg_number").val().trim() == "") {
// 			alert("Enter Registration Number");
// 			$("input#reg_number").focus();
// 			return false;
// 		}
// 		if ($("input#d_university_appointment").val().trim() == "DD/MM/YYYY") {
// 			alert("Enter Date Of University Appointment Letter");
// 			$("input#d_university_appointment").focus();
// 			return false;
// 		}

// 		var formvalues = $("#form_E_details").serialize();
// 		var form_e_id = $('#form_e_id').val();
// 		$.post('form_e_action?' + key + "=" + value, formvalues,
// 				function(data) {
// 					if (data.error == null) {
// 						if (data.form_e_id != null) {
// 							$('#form_e_id').val(data.form_e_id);
// 							alert(data.saved);
// 							location.reload();
// 						} else {
// 							alert(data.updated);
// 							location.reload();
// 						}
// 					} else {
// 						alert(data.error);
// 						location.reload();
// 					}
// 				}).fail(function(xhr, textStatus, errorThrown) {
// 			alert("fail to fetch")
// 		});
// 	}
	function get_inst_by_uni(val) {
		$.ajaxSetup({
			async : false
		});

		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		$
				.post(
						'get_inst_by_uni_ctrl?' + key + "=" + value,
						{
							university_id : val
						},
						function(j) {
							//     		alert(j);
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#institute_name").html(options);

						}).fail(function(xhr, textStatus, errorThrown) {
					alert("fail to fetch")
				});

	}
</script>