<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet"
	href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<body>
	<section class="dashboard-page">
		<div class="container-fluid">
			<!-- title-wrapper start -->
			<div class="title-wrapper pt-30">
				<div class="row align-items-center">
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="title mb-30">
							<h2>Student Details</h2>
						</div>
					</div>
					<!-- end col -->
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="breadcrumb-wrapper mb-30">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="#0">Dashboard</a></li>

									<li class="breadcrumb-item active" aria-current="page">
										Student Details</li>
								</ol>
							</nav>
						</div>
					</div>
					<!-- end col -->
				</div>
				<!-- end row -->
			</div>
			<!-- title-wrapper end -->
			<div class="form-elements-wrapper custom_v_tab">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="card-style mb-30">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="inst_block simple-instruction">
									<strong>Instruction :</strong> If any of the value is not
									available or not applicable then please put it as 0
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-12 col-12">
								<div class="custom-data-value custom-data-value-form">
									<label>Institution Name :</label> <span class="value-bind">Test
										Homoeopathic Medical College and Hospital-JHARKHAND</span>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-12 col-12">
								<div class="custom-data-value custom-data-value-form">
									<label>Institution Name :</label> <span class="value-bind">Jharkhand</span>
								</div>

							</div>
						</div>
					</div>
				</div>
					<!-- ===========================
							Student Details Start
						=========================== -->
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						
						<div class="card-style mb-30">
							<div class="tabs content_h750">
							<!-- Information of Admitted Students Start -->
								<div class="tab">
									<button class="tab-toggle" id="defaultOpen">Information
										of Admitted Students</button>
								</div>
								<div class="content">
									<h4 class="heading">Information of Admitted Students</h4>
									<form:form name="admitted_student" id="admitted_student" action="" method="post" class=""
										commandName="">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12 col-12">
														<div class="table-wrapper table-responsive custom-table">
															<table class="table" id="admitstudent_table">
																
															</table>
														</div>
														<!-- end card -->
													</div>
													<!-- end col -->
												
											
											<hr>
											<div class="row">
												<div class="col-lg-6 col-md-6 col-sm-12">
												<label >Whether college has intimated
															the students regarding the admission under court order</label>
															
													<input type="hidden" name="hid_admitted_details_check" id="hid_admitted_details_check" value="0">
															
													<div class="input-style-form-check">
														
														<div class="form-check radio-style">
															<input type="radio" id="intimatedYes" name="intimatedcheck"
																class="form-check-input" value="1"> <label
																for="intimatedYes" class="form-check-label">Yes</label>
														</div>
														<div class="form-check radio-style">
															<input type="radio" id="intimatedNo" name="intimatedcheck"
																class="form-check-input" value="2"> <label
																for="intimatedNo" class="form-check-label">No</label>
														</div>
													</div>
												</div>
												<div class="col-lg-6 col-md-6 col-sm-12 custom-d-none" id="hid_undertakingcheck">
												<label >Undertaking has been taken from
															students (If admitted though court order)</label>
													<div class="input-style-form-check">
														
														<div class="form-check radio-style">
															<input type="radio" id="undertakingYes" name="undertakingcheck"
																class="form-check-input" value="1"> <label
																for="undertakingYes" class="form-check-label">Yes</label>
														</div>
														<div class="form-check radio-style">
															<input type="radio" id="undertakingNo" name="undertakingcheck"
																class="form-check-input" value="2"> <label
																for="undertakingNo" class="form-check-label">No</label>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover" id="admitted_student_details_save"
															type="button" value="Save"></li>
																<li><a 
																class="main-btn dark-btn-outline btn-hover btn-iconic-icon viewData d-none"><i
																	class="lni lni-eye"></i>View</a>
																	<input type='hidden' id="viewId${parent_id}" value="${institude}"></li>
																	
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
									<!-- Information of Admitted Students end -->
									<!-- Upload Document Start -->
								<div class="tab custom-d-none" id="hide_upload_doc_tab">
									<button class="tab-toggle">Upload Document</button>
								</div>
								<div class="content">
									<h4 class="heading">Upload Document</h4>
									<form:form name="upload_document" id="upload_document" action="" method="post" class=""
										commandName="">
										<div class="row">
										
										<input type="hidden" id="hid_upload_document" name="hid_upload_document" value="0">
										
											<div class="col-lg-6 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label >Upload the list of PG students
														allotted to individual guide in the last 5 academic years
														<strong class="mandatory">*</strong>
													</label> <input type="file" id="lastfiveguide" name="lastfiveguide"
														class="form-control" accept="pdf">
														<input type="hidden" id="hid_lastfiveguide" name="hid_lastfiveguide"
															class="form-control" value="">
													<div class="note-text">
														<span class="errorClass" id="lastfiveguide_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="lastfiveguide_lbltik"></span>
													</div>
												</div>
												
											</div>
											<div class="col-lg-6 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label >Upload Name, Mobile No. and
														Email Id of all UG/PG admitted students with their
														NEET/AIAPGET score/admission criteria in the last 5
														academic years<strong class="mandatory">*</strong>
													</label> <input type="file" id="neet_score" name="neet_score"
														class="form-control" accept="pdf">
														<input type="hidden" id="hid_neet_score" name="hid_neet_score"
															class="form-control" value="">
													<div class="note-text">
														<span class="errorClass" id="neet_score_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="neet_score_lbltik"></span>
													</div>
												</div>
											</div>
											<div class="col-lg-6 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label >Upload the undertaking of the
														students (About the provisional nature of admission)<strong
														class="mandatory">*</strong>
													</label> <input type="file" id="undertakingofstudent"
														name="undertakingofstudent" class="form-control" accept="pdf">
														<input type="hidden" id="hid_undertakingofstudent" name="hid_undertakingofstudent"
															class="form-control" value="">
													<div class="note-text">
														<span class="errorClass" id="undertakingofstudent_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="undertakingofstudent_lbltik"></span>
													</div>
												</div>
											</div>
											<div class="col-lg-6 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label >Upload PG students attendance
														register/Biometric attendance<strong class="mandatory">*</strong>
													</label> <input type="file" id="biometricattendance"
														name="biometricattendance" class="form-control" accept="pdf">
														<input type="hidden" id="hid_biometricattendance" name="hid_biometricattendance"
															class="form-control" value="">
													<div class="note-text">
														<span class="errorClass" id="biometricattendance_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="biometricattendance_lbltik"></span>
													</div>
												</div>
											</div>
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover" id="upload_document_details_save"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- Upload Document End -->
								<!-- Passed out Students Info. Start -->
								<div class="tab custom-d-none" id="hide_passes_student_tab">
									<button class="tab-toggle">Passed out Students Info.</button>
								</div>
								<div class="content">
									<h4 class="heading">Passed out Students Info.</h4>
									<form:form name="passed_student" id="passed_student" action="" method="post" class=""
										commandName="">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12 col-12">
														<div class="table-wrapper table-responsive custom-table">
															<table class="table" id="pass_std_info_table">
																
															</table>
														</div>
														<!-- end card -->
													</div>
													<!-- end col -->
												</div>
												<!-- end row -->
												<div class="footer_btn">
													<div class="row">
														<div class="col-lg-12 col-md-12 col-sm-12">
															<ul class="buttons-group mainbtn">
																<li><input class="main-btn info-btn btn-hover" id="passed_student_details_save"
																	type="button" value="Save"></li>
															</ul>
														</div>
													</div>
												</div>
									</form:form>
								</div>
								<!-- Passed out Students Info. End -->
								<!-- Internship & Housejob Details Start -->
								<div class="tab custom-d-none" id="hide_internship_details_tab">
									<button class="tab-toggle">Internship & Housejob
										Details</button>
								</div>
								<div class="content">
									<h4 class="heading">Internship & Housejob Details</h4>
									<form:form name="internship_details" id="internship_details" action="" method="post" class=""
										commandName="">
										<div class="row">
										
										<input type="hidden" id="hid_internship_details" name="hid_internship_details" value="0">
										
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label >Total number of Interns (Upload
														list Batch-wise)</label> <input type="file" id="total_intern"
														name="total_intern" class="form-control" accept="pdf">
														<input type="hidden" id="hid_total_intern" name="hid_total_intern"
															class="form-control" value="">
													<div class="note-text">
														<span class="errorClass" id="total_intern_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="total_intern_lbltik"></span>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<label >Whether they have Provisional
														Registration with State Homoeopathic Board/ Council ?<strong
														class="mandatory">*</strong>
													</label>
												<div class="input-style-form-check">
													
													<div class="form-check radio-style">
														<input type="radio" id="pro_reg_Yes" name="pro_regcheck"
															class="form-check-input" value="1"> <label
															for="pro_reg_Yes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="pro_reg_No" name="pro_regcheck"
															class="form-check-input" value="2"> <label
															for="pro_reg_No" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<label >Rotation Programme ?<strong
														class="mandatory">*</strong></label>
												<div class="input-style-form-check">
													
													<div class="form-check radio-style">
														<input type="radio" id="rotation_Yes" name="rotationcheck"
															class="form-check-input" value="1"> <label
															for="rotation_Yes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="rotation_No" name="rotationcheck"
															class="form-check-input" value="2"> <label
															for="rotation_No" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											<!-- <div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Total interns in O.P.D<strong
														class="mandatory">*</strong></label> <input type="text"
														id="internsopd" name="internsopd" class="form-control"
														placeholder="Total interns in O.P.D">
												</div>
											</div> -->
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Interns Duty hour/day in O.P.D<strong
														class="mandatory">*</strong></label> <input type="text"
														id="internsdutyopd" name="internsdutyopd" class="form-control"
														placeholder="Duty hour/day in O.P.D" maxlength="2">
												</div>
											</div>
											<!-- <div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
													<label>Total interns in IPD<strong
														class="mandatory">*</strong></label> <input type="text"
														id="internsipd" name="internsipd" class="form-control"
														placeholder="Total interns in IPD">
												</div>
											</div> -->
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Interns Duty hour/day in IPD<strong
														class="mandatory">*</strong></label> <input type="text"
														id="internsdutyipd" name="internsdutyipd"
														class="form-control" placeholder="Duty hour/day in IPD" maxlength="2">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<label >Any migration of intern from/to
														College.<strong class="mandatory">*</strong>
													</label>
												<div class="input-style-form-check">
													
													<div class="form-check radio-style">
														<input type="radio" id="migration_Yes" name="migrationcheck"
															class="form-check-input" value="1"> <label
															for="migration_Yes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="migration_No" name="migrationcheck"
															class="form-check-input" value="2"> <label
															for="migration_No" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_migrationcheck">
												<div class="input-style-1">
													<label >Migration List</label> <input
														type="file" id="migration_list" name="migration_list"
														class="form-control" accept="pdf">
														<input type="hidden" id="hid_migration_list" name="hid_migration_list"
															class="form-control" value="">
													<div class="note-text">
														<span class="errorClass" id="migration_list_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="migration_list_lbltik"></span>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Whether intern students prescribe medicine<strong
														class="mandatory">*</strong></label> <input type="text"
														id="prescribe" name="prescribe" class="form-control"
														placeholder="Prescribe Medicine" maxlength="100">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Seminar for Internee<strong
														class="mandatory">*</strong></label> <input type="text"
														id="seminar" name="seminar" class="form-control"
														placeholder="Seminar for Internee" maxlength="100">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Number of students who have not completed
														internship for more than two years of passing final BHMS
														examination.<strong class="mandatory">*</strong>
													</label> <input type="text" id="internship_not_completed" name="internship_not_completed"
														class="form-control" placeholder="Number of students" maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>House Job<strong class="mandatory">*</strong></label>
													<input type="text" id="house_job" name="house_job"
														class="form-control" placeholder="House Job" maxlength="100">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Number of Students undergoing House Job.<strong
														class="mandatory">*</strong></label> <input type="text"
														id="no_house_job" name="no_house_job" class="form-control"
														placeholder="Number of Students undergoing House Job" maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Graded teaching work performed by P.G.
														Students.(described in short)<strong class="mandatory">*</strong>
													</label>
													<textarea
														placeholder="Graded teaching work performed by P.G. Students"
														id="graded_teaching" name="graded_teaching"
														rows="5" maxlength="500"></textarea>
												</div>
											</div>
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn success-btn btn-hover btnsubmit" id="internship_details_save"
															type="button" value="Submit"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- Internship & Housejob Details End -->
							</div>
						</div>
					</div>
					<!-- ===========================
							Student Details End
						=========================== -->
				</div>
			</div>
			<!-- end row -->
		</div>
		<!-- end container -->
	</section>
	<!-- regulation components end -->
			<c:url value="View_Search_Student_DetailsUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="student_dtl_view_id">
	<input type="hidden" name="student_dtl_id" id="student_dtl_view_id" value="0" />
	<input type="hidden" name="statusview" id="statusview" value="0" />
</form:form>
	<script type="text/javascript"
		src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>

<script type="text/javascript" nonce="${cspNonce}">
	$(document).ready(function() {
		if ('${role}'=='NCH') {
			$(".viewData").addClass("d-none")
		}
		if ('${role}'=='Institute_NCH') {
			$(".viewData").removeClass("d-none")
		} 
		var valid_dt = '${valid_dt}';
		var y = valid_dt.substring(0, 4);
		var m = valid_dt.substring(5, 7);
		var d = valid_dt.substring(8, 10);
		var valid_dt1 = d + "/" + m + "/" + y;
		var today = new Date();
		var yyyy = today.getFullYear();
		var mm = today.getMonth() + 1; // Months start at 0!
		var dd = today.getDate();
		if (dd < 10)
			dd = '0' + dd;
		if (mm < 10)
			mm = '0' + mm;
		today = dd + '/' + mm + '/' + yyyy;
		var valid_dt2 = valid_dt.split('/').reverse().join('-');
		var today2 = today.split('/').reverse().join('-');
		if ("${hid}" != "3" && valid_dt2 <= today2) {
			datepicketDate('lsad1');
			datepicketDate('lsad2');
			datepicketDate('lsad3');
			datepicketDate('lsad4');
			datepicketDate('lsad5');	
		}
		
		var yemp="";
		
		yemp+="<thead>"+
		"<tr>"+
		"<th><h6>Sr No</h6></th>"+
			"<th><h6>Year of Admission</h6></th>"+
			"<th><h6>"+
					""+lastyear(1)+"-"+lastyear(0)+"<strong class='mandatory'>*</strong>"+
				"</h6></th>"+
			"<th><h6>"+
					""+lastyear(2)+"-"+lastyear(1)+"<strong class='mandatory'>*</strong>"+
				"</h6></th>"+
			"<th><h6>"+
					""+lastyear(3)+"-"+lastyear(2)+"<strong class='mandatory'>*</strong>"+
				"</h6></th>"+
			"<th><h6>"+
					""+lastyear(4)+"-"+lastyear(3)+"<strong class='mandatory'>*</strong>"+
				"</h6></th>"+
			"<th><h6>"+
					""+lastyear(5)+"-"+lastyear(4)+"<strong class='mandatory'>*</strong>"+
			"	</h6></th>"+
		"</tr>"+
		<!-- end table row-->
	"</thead>"+
	"<tbody id=''>"+
	"<tr>"+
		"<td class='sr-no'>"+
			"<p></p>"+
		"</td>"+
		"<td>"+
					"<p>Total Students admitted for UG Course with Govt Quota</p>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='govt_quota_ug_"+lastyear(0)+"' id='govt_quota_ug_"+lastyear(0)+"' class='form-control' placeholder='' maxlength='6'></div>"+
			"<input type='hidden' id='hid_admitted_student_"+lastyear(0)+"' name='hid_admitted_student_"+lastyear(0)+"' value='0'>"+
		"</td>"+
		
		"<td>"+
			"<div class='input-style-1'><input type='text' name='govt_quota_ug_"+lastyear(1)+"' id='govt_quota_ug_"+lastyear(1)+"' class='form-control' placeholder='' maxlength='6'></div>"+
			"<input type='hidden' id='hid_admitted_student_"+lastyear(1)+"' name='hid_admitted_student_"+lastyear(1)+"' value='0'>"+
		"</td>"+
		
		"<td>"+
			"<div class='input-style-1'><input type='text' name='govt_quota_ug_"+lastyear(2)+"' id='govt_quota_ug_"+lastyear(2)+"' class='form-control' placeholder='' maxlength='6'></div>"+
			"<input type='hidden' id='hid_admitted_student_"+lastyear(2)+"' name='hid_admitted_student_"+lastyear(2)+"' value='0'>"+
		"</td>"+
		
		"<td>"+
			"<div class='input-style-1'><input type='text' name='govt_quota_ug_"+lastyear(3)+"' id='govt_quota_ug_"+lastyear(3)+"' class='form-control' placeholder='' maxlength='6'></div>"+
			"<input type='hidden' id='hid_admitted_student_"+lastyear(3)+"' name='hid_admitted_student_"+lastyear(3)+"' value='0'>"+
		"</td>"+
		
		"<td>"+
			"<div class='input-style-1'><input type='text' name='govt_quota_ug_"+lastyear(4)+"' id='govt_quota_ug_"+lastyear(4)+"' class='form-control' placeholder='' maxlength='6'></div>"+
			"<input type='hidden' id='hid_admitted_student_"+lastyear(4)+"' name='hid_admitted_student_"+lastyear(4)+"' value='0'>"+
		"</td>"+
		
		
	
	"</tr>"+
	
	"<tr>"+
		"<td class='sr-no'>"+
			"<p></p>"+
		"</td>"+
		"<td>"+
					"<p>Total Students admitted for UG Course with Management Quota</p>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='mang_quota_ug_"+lastyear(0)+"' id='mang_quota_ug_"+lastyear(0)+"' class='form-control' placeholder='' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='mang_quota_ug_"+lastyear(1)+"' id='mang_quota_ug_"+lastyear(1)+"' class='form-control' placeholder='' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='mang_quota_ug_"+lastyear(2)+"' id='mang_quota_ug_"+lastyear(2)+"' class='form-control' placeholder='' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='mang_quota_ug_"+lastyear(3)+"' id='mang_quota_ug_"+lastyear(3)+"' class='form-control' placeholder='' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='mang_quota_ug_"+lastyear(4)+"' id='mang_quota_ug_"+lastyear(4)+"' class='form-control' placeholder='' maxlength='6'></div>"+
		"</td>"+
	"</tr>"+
	
	"<tr>"+
	"<td class='sr-no'>"+
		"<p></p>"+
	"</td>"+
		"<td>"+
			"<p>Total Students admitted for PG Course with Govt Quota</p>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='govt_quota_pg_"+lastyear(0)+"' id='govt_quota_pg_"+lastyear(0)+"' class='form-control' placeholder='' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='govt_quota_pg_"+lastyear(1)+"' id='govt_quota_pg_"+lastyear(1)+"' class='form-control' placeholder='' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='govt_quota_pg_"+lastyear(2)+"' id='govt_quota_pg_"+lastyear(2)+"' class='form-control' placeholder='' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='govt_quota_pg_"+lastyear(3)+"' id='govt_quota_pg_"+lastyear(3)+"' class='form-control' placeholder='' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='govt_quota_pg_"+lastyear(4)+"' id='govt_quota_pg_"+lastyear(4)+"' class='form-control' placeholder='' maxlength='6'></div>"+
		"</td>"+
	"</tr>"+
	
	"<tr>"+
	"<td class='sr-no'>"+
		"<p></p>"+
	"</td>"+
		"<td>"+
			"<p>Total Students admitted for PG Course with Management Quota</p>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='mang_quota_pg_"+lastyear(0)+"' id='mang_quota_pg_"+lastyear(0)+"' class='form-control' placeholder='' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='mang_quota_pg_"+lastyear(1)+"' id='mang_quota_pg_"+lastyear(1)+"' class='form-control' placeholder='' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='mang_quota_pg_"+lastyear(2)+"' id='mang_quota_pg_"+lastyear(2)+"' class='form-control' placeholder='' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='mang_quota_pg_"+lastyear(3)+"' id='mang_quota_pg_"+lastyear(3)+"' class='form-control' placeholder='' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='mang_quota_pg_"+lastyear(4)+"' id='mang_quota_pg_"+lastyear(4)+"' class='form-control' placeholder='' maxlength='6'></div>"+
		"</td>"+
	"</tr>"+
	
	"<tr>"+
	"<td class='sr-no'>"+
		"<p></p>"+
	"</td>"+
		"<td>"+
			"<p>Students admitted by Court order</p>"+
		"</td>"+
		"<td>"+
			"<div class='select-style-1'><div class='select-position'><select name='court_order_"+lastyear(0)+"' id='court_order_"+lastyear(0)+"' class='form-control'>"+
						"<option value='0' selected='selected'>--Select --</option>"+
						"<option value='1'>Yes</option>"+
						"<option value='2'>No</option>"+
					"</select>"+
				"</div>"+
			"</div>"+
		"</td>"+
		"<td>"+
			"<div class='select-style-1'><div class='select-position'><select name='court_order_"+lastyear(1)+"' id='court_order_"+lastyear(1)+"' class='form-control'>"+
						"<option value='0' selected='selected'>--Select --</option>"+
						"<option value='1'>Yes</option>"+
						"<option value='2'>No</option>"+
					"</select>"+
				"</div>"+
			"</div>"+
		"</td>"+
		"<td>"+
			"<div class='select-style-1'><div class='select-position'><select name='court_order_"+lastyear(2)+"' id='court_order_"+lastyear(2)+"' class='form-control'>"+
						"<option value='0' selected='selected'>--Select --</option>"+
						"<option value='1'>Yes</option>"+
						"<option value='2'>No</option>"+
					"</select>"+
				"</div>"+
			"</div>"+
		"</td>"+
		"<td>"+
			"<div class='select-style-1'><div class='select-position'><select name='court_order_"+lastyear(3)+"' id='court_order_"+lastyear(3)+"' class='form-control'>"+
						"<option value='0' selected='selected'>--Select --</option>"+
						"<option value='1'>Yes</option>"+
						"<option value='2'>No</option>"+
					"</select>"+
				"</div>"+
			"</div>"+
		"</td>"+
		"<td>"+
			"<div class='select-style-1'><div class='select-position'><select name='court_order_"+lastyear(4)+"' id='court_order_"+lastyear(4)+"' class='form-control'>"+
						"<option value='0' selected='selected'>--Select --</option>"+
						"<option value='1'>Yes</option>"+
						"<option value='2'>No</option>"+
					"</select>"+
				"</div>"+
			"</div>"+
		"</td>"+
	"</tr>"+
	
	"<tr>"+
	"<td class='sr-no'>"+
		"<p></p>"+
	"</td>"+
		"<td>"+
			"<p>Name of the Last Student Admitted</p>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='last_student_"+lastyear(0)+"' id='last_student_"+lastyear(0)+"' class='form-control' placeholder='Last Student Name' maxlength='50'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='last_student_"+lastyear(1)+"' id='last_student_"+lastyear(1)+"' class='form-control' placeholder='Last Student Name' maxlength='50'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='last_student_"+lastyear(2)+"' id='last_student_"+lastyear(2)+"' class='form-control' placeholder='Last Student Name' maxlength='50'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='last_student_"+lastyear(3)+"' id='last_student_"+lastyear(3)+"' class='form-control' placeholder='Last Student Name' maxlength='50'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='last_student_"+lastyear(4)+"' id='last_student_"+lastyear(4)+"' class='form-control' placeholder='Last Student Name' maxlength='50'></div>"+
		"</td>"+
	"</tr>"+
	
	"<tr>"+
	"<td class='sr-no'>"+
		"<p></p>"+
	"</td>"+
		"<td>"+
			"<p>Date of Admission of the last Student</p>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-2'><input type='text' name='last_stu_add_date_"+lastyear(0)+"' id='last_stu_add_date_"+lastyear(0)+"' class='form-control-sm form-control effect-9' aria-required='true' autocomplete='off'"+
					"placeholder='DD/MM/YYYY'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-2'><input type='text' name='last_stu_add_date_"+lastyear(1)+"' id='last_stu_add_date_"+lastyear(1)+"' class='form-control-sm form-control effect-9' aria-required='true' autocomplete='off'"+
					"placeholder='DD/MM/YYYY'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-2'><input type='text' name='last_stu_add_date_"+lastyear(2)+"' id='last_stu_add_date_"+lastyear(2)+"' class='form-control-sm form-control effect-9' aria-required='true' autocomplete='off'"+
					"placeholder='DD/MM/YYYY'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-2'><input type='text' name='last_stu_add_date_"+lastyear(3)+"' id='last_stu_add_date_"+lastyear(3)+"' class='form-control-sm form-control effect-9' aria-required='true' autocomplete='off'"+
					"placeholder='DD/MM/YYYY'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-2'><input type='text' name='last_stu_add_date_"+lastyear(4)+"' id='last_stu_add_date_"+lastyear(4)+"' class='form-control-sm form-control effect-9' aria-required='true' autocomplete='off'"+
					"placeholder='DD/MM/YYYY'></div>"+
		"</td>"+
	"</tr>"+
	<!-- end table row -->
"</tbody>";
	
	$('#admitstudent_table').append(yemp);
	
	var pass_student="";
	pass_student+="<thead>"+
	"<tr>"+
	"<th><h6>Sr No</h6></th>"+
		"<th><h6>Year of Admission</h6></th>"+
		"<th><h6>"+lastyear(1)+"-"+lastyear(0)+"<strong class='mandatory'>*</strong></h6></th>"+
		"<th><h6>"+lastyear(2)+"-"+lastyear(1)+"<strong class='mandatory'>*</strong></h6></th>"+
		"<th><h6>"+lastyear(3)+"-"+lastyear(2)+"<strong class='mandatory'>*</strong></h6></th>"+
		"<th><h6>"+lastyear(4)+"-"+lastyear(3)+"<strong class='mandatory'>*</strong></h6></th>"+
		"<th><h6>"+lastyear(5)+"-"+lastyear(4)+"<strong class='mandatory'>*</strong></h6></th>"+
	"</tr>"+
"</thead>"+
"<tbody id=''>"+
	"<tr>"+
	"<td class='sr-no'>"+
		"<p></p>"+
	"</td>"+
		"<td><p>Total Final Year students appeared for exams for UG Course</p></td>"+
		
		"<td>"+
			"<div class='input-style-1'><input type='text' name='appeared_stu_ug_"+lastyear(0)+"' id='appeared_stu_ug_"+lastyear(0)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
			"<input type='hidden' id='hid_passed_student_"+lastyear(0)+"' name='hid_passed_student_"+lastyear(0)+"' value='0'>"+		
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='appeared_stu_ug_"+lastyear(1)+"' id='appeared_stu_ug_"+lastyear(1)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
			"<input type='hidden' id='hid_passed_student_"+lastyear(1)+"' name='hid_passed_student_"+lastyear(1)+"' value='0'>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='appeared_stu_ug_"+lastyear(2)+"' id='appeared_stu_ug_"+lastyear(2)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
			"<input type='hidden' id='hid_passed_student_"+lastyear(2)+"' name='hid_passed_student_"+lastyear(2)+"' value='0'>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='appeared_stu_ug_"+lastyear(3)+"' id='appeared_stu_ug_"+lastyear(3)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
			"<input type='hidden' id='hid_passed_student_"+lastyear(3)+"' name='hid_passed_student_"+lastyear(3)+"' value='0'>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='appeared_stu_ug_"+lastyear(4)+"' id='appeared_stu_ug_"+lastyear(4)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
			"<input type='hidden' id='hid_passed_student_"+lastyear(4)+"' name='hid_passed_student_"+lastyear(4)+"' value='0'>"+
		"</td>"+
	"</tr>"+
	"<tr>"+
	"<td class='sr-no'>"+
		"<p></p>"+
	"</td>"+
		"<td><p>Total Final Year students passed out in exams for UG Course</p></td>"+
		
		"<td>"+
			"<div class='input-style-1'><input type='text' name='passed_stu_ug_"+lastyear(0)+"' id='passed_stu_ug_"+lastyear(0)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='passed_stu_ug_"+lastyear(1)+"' id='passed_stu_ug_"+lastyear(1)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='passed_stu_ug_"+lastyear(2)+"' id='passed_stu_ug_"+lastyear(2)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='passed_stu_ug_"+lastyear(3)+"' id='passed_stu_ug_"+lastyear(3)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='passed_stu_ug_"+lastyear(4)+"' id='passed_stu_ug_"+lastyear(4)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
		"</td>"+
	"</tr>"+
	"<tr>"+
	"<td class='sr-no'>"+
		"<p></p>"+
	"</td>"+
		"<td><p>Total Final Year students appeared for exams for PG Course</p></td>"+
		
		"<td>"+
			"<div class='input-style-1'><input type='text' name='appeared_stu_pg_"+lastyear(0)+"' id='appeared_stu_pg_"+lastyear(0)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='appeared_stu_pg_"+lastyear(1)+"' id='appeared_stu_pg_"+lastyear(1)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='appeared_stu_pg_"+lastyear(2)+"' id='appeared_stu_pg_"+lastyear(2)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='appeared_stu_pg_"+lastyear(3)+"' id='appeared_stu_pg_"+lastyear(3)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='appeared_stu_pg_"+lastyear(4)+"' id='appeared_stu_pg_"+lastyear(4)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
		"</td>"+
	"</tr>"+
	"<tr>"+
	"<td class='sr-no'>"+
		"<p></p>"+
	"</td>"+
		"<td><p>Total Final Year students passed out in exams for PG Course</p>"+
		"</td>"+
		
		"<td>"+
			"<div class='input-style-1'><input type='text' name='passed_stu_pg_"+lastyear(0)+"' id='passed_stu_pg_"+lastyear(0)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='passed_stu_pg_"+lastyear(1)+"' id='passed_stu_pg_"+lastyear(1)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='passed_stu_pg_"+lastyear(2)+"' id='passed_stu_pg_"+lastyear(2)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='passed_stu_pg_"+lastyear(3)+"' id='passed_stu_pg_"+lastyear(3)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
		"</td>"+
		"<td>"+
			"<div class='input-style-1'><input type='text' name='passed_stu_pg_"+lastyear(4)+"' id='passed_stu_pg_"+lastyear(4)+"' class='form-control' placeholder='' value='0' maxlength='6'></div>"+
		"</td>"+
	"</tr>"+
"</tbody>";
$('#pass_std_info_table').append(pass_student);
	
	datepicketDate('last_stu_add_date_'+lastyear(0));
	datepicketDate('last_stu_add_date_'+lastyear(1));
	datepicketDate('last_stu_add_date_'+lastyear(2));
	datepicketDate('last_stu_add_date_'+lastyear(3));
	datepicketDate('last_stu_add_date_'+lastyear(4));
	
	
	getAdmitted_Student_Details();
	var get_dtl = $('#hid_admitted_student_'+lastyear(0)).val();
	if(get_dtl != "0"){
	getAdmitted_Student_Check_Details();
	getPassed_Student_Details();
	getUpload_Document_Details();
	getInternship_Details();
	}
// 	abc(0);
// 	abc(1);
// 	abc(2);
// 	abc(3);
// 	abc(4);
	
	
	var hid_tab = $('#hid_admitted_student_'+lastyear(0)).val();
	if(hid_tab == "0"){
		$("#hide_upload_doc_tab").hide();
		$("#hide_passes_student_tab").hide();
		$("#hide_internship_details_tab").hide();
	}else{
		$("#hide_upload_doc_tab").show();
		$("#hide_passes_student_tab").show();
		$("#hide_internship_details_tab").show();
	}
	//ADMITTED STUDENT VALIDATION
	for (var i = 0; i < 5; i++) {
		
			document.getElementById('govt_quota_ug_'+lastyear(i)).onkeypress = function() {
				return isNumberKey0to9(event, this);
			};
			document.getElementById('mang_quota_ug_'+lastyear(i)).onkeypress = function() {
				return isNumberKey0to9(event, this);
			};
			document.getElementById('govt_quota_pg_'+lastyear(i)).onkeypress = function() {
				return isNumberKey0to9(event, this);
			};
			document.getElementById('mang_quota_pg_'+lastyear(i)).onkeypress = function() {
				return isNumberKey0to9(event, this);
			};
			document.getElementById('last_student_'+lastyear(i)).onkeypress = function() {
				return onlyAlphabetsStringSpace(event, this);
			};
		
			}
	
	//PASSED STUDENT VALIDATION
	for (var i = 0; i < 5; i++) {
		
		document.getElementById('appeared_stu_ug_'+lastyear(i)).onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('passed_stu_ug_'+lastyear(i)).onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('appeared_stu_pg_'+lastyear(i)).onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('passed_stu_pg_'+lastyear(i)).onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
	
		}
	
	
	});
	
	function lastyear(val){
		var date = new Date();
		date.setDate( date.getDate() - 6 );
		date.setFullYear( date.getFullYear() - val );
		return date.getFullYear();
	}
	
// 	function abc(val){
// 		var date = new Date();
// 		var month = date.getMonth();
// 		var year = date.getFullYear();
// 		alert(month);
// 		if(month > 1){
// 			var year_add = year + 1;
// 			var abc = year_add - val
// 			alert(abc);
// 			return true;
// 		}
// 		var abcd = date.getFullYear() - val;
// 		alert(abcd);
// 	}
	
	function Intimated_Yes(){
		
		var intimatedcheck = $('input:radio[name=intimatedcheck]:checked').val();
		if(intimatedcheck == "1"){
	        $("div#hid_undertakingcheck").show();
		}
		else if(intimatedcheck == "2"){
	        $("div#hid_undertakingcheck").hide();
		}
	}
	
	function Migration_Yes(){
		
		var migrationcheck = $('input:radio[name=migrationcheck]:checked').val();
		if(migrationcheck == "1"){
	        $("div#hid_migrationcheck").show();
		}
		else if(migrationcheck == "2"){
	        $("div#hid_migrationcheck").hide();
		}
	}
// 	--------------------------------------------START-->ADMITTED STUDENT---------------------------------------------
	
	//SAVE ADMITTED STUDENT DETAILS
	function Save_As_Draft_Admitted_Student_Details(sd) {
		
		$.ajaxSetup({
		    async: false
		});	
	 	
		for (var i = 0; i < 5; i++) {
		
		var res = CheckNullorBlankZero('govt_quota_ug_'+lastyear(i));
		if (res !== "true") {
			alert(res +"Total Students Admitted for UG With Govt Quota in "+lastyear(i));
			$('#govt_quota_ug_'+lastyear(i)).focus();
			return false;
		}
		var res = CheckNullorBlankZero('mang_quota_ug_'+lastyear(i));
		if (res !== "true") {
			alert(res +"Total Students Admitted for UG With Management Quota in "+lastyear(i));
			$('#mang_quota_ug_'+lastyear(i)).focus();
			return false;
		}
		var res = CheckNullorBlankZero('govt_quota_pg_'+lastyear(i));
		if (res !== "true") {
			alert(res +"Total Students Admitted for PG With Govt Quota in "+lastyear(i));
			$('#govt_quota_pg_'+lastyear(i)).focus();
			return false;
		}
		var res = CheckNullorBlankZero('mang_quota_pg_'+lastyear(i));
		if (res !== "true") {
			alert(res +"Total Students Admitted for PG With Management Quota in "+lastyear(i));
			$('#mang_quota_pg_'+lastyear(i)).focus();
			return false;
		}
		var res = CheckNullorBlank('court_order_'+lastyear(i));
		if (res !== "true") {
			alert(res +"Students Admitted by Court Order in "+lastyear(i));
			$('#court_order_'+lastyear(i)).focus();
			return false;
		}
		var res = CheckNullorBlank('last_student_'+lastyear(i));
		if (res !== "true") {
			alert(res +"Name of the Last Admitted Student in "+lastyear(i));
			$('#last_student_'+lastyear(i)).focus();
			return false;
		}
		var res = CheckNullorBlank('last_stu_add_date_'+lastyear(i));
		if (res !== "true") {
			alert(res +"Date of the Last Admitted Student in "+lastyear(i));
			$('#last_stu_add_date_'+lastyear(i)).focus();
			return false;
		}
		
		}
		
		var intimatedcheck = $("input[name='intimatedcheck']:checked").val();
		if( intimatedcheck == null ){
			alert("Please Select Intimated Check");
			return false;
	   	}
		
		var intimatedcheck = $("input[name='intimatedcheck']:checked").val();
		if(intimatedcheck == "1"){
		var undertakingcheck = $("input[name='undertakingcheck']:checked").val();
		if( undertakingcheck == null ){
			alert("Please Select Under Taking Check");
			return false;
	   	}
		}
		
		var form = $("#admitted_student").serialize();
		console.log(form);
		$.post(
				'Admitted_Student_Details_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        		$("#hid_admitted_details_check").val(j);
		        	if(sd != -1){
		        	  alert("Data Save Sucessfully");
		        	}
		        	  location.reload();
		          }
		          else{
		        	  if(sd != -1){
		        	  alert(j);
		        	  }
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
			
	 }
	 
	 
	//FETCH ADMITTED STUDENT DETAILS
	function getAdmitted_Student_Details() {
		$.ajaxSetup({
			async : false
		});
		
		$.post("getAdmitted_Student_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
				
				$("#hid_admitted_student_"+j[i][2]).val(j[i][0]);
				$("#govt_quota_ug_"+j[i][2]).val(j[i][4]);
				$("#mang_quota_ug_"+j[i][2]).val(j[i][5]);
				$("#govt_quota_pg_"+j[i][2]).val(j[i][6]);
				$("#mang_quota_pg_"+j[i][2]).val(j[i][7]);
				$("#court_order_"+j[i][2]).val(j[i][8]);
				$("#last_student_"+j[i][2]).val(j[i][9]);
				$("#last_stu_add_date_"+j[i][2]).val(j[i][10]);
				
			}
			
		});
	}
	 
	
	//FETCH ADMITTED STUDENT CHECK DETAILS
	function getAdmitted_Student_Check_Details() {
		$.ajaxSetup({
			async : false
		});
		
		$.post("getAdmitted_Student_Check_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			
			$("#hid_admitted_details_check").val(j[0].id);
			$("#hid_upload_document").val(j[0].id);
			$("#hid_internship_details").val(j[0].id);
			var intimatedcheck = j[0].intimatedcheck;
			$("input[name='intimatedcheck'][value='"+intimatedcheck+"']").prop('checked', true);
			if(intimatedcheck == "1"){
				 $("div#hid_undertakingcheck").show();
			}else{
				 $("div#hid_undertakingcheck").hide();
			}
			
			var undertakingcheck = j[0].undertakingcheck;
			$("input[name='undertakingcheck'][value='"+undertakingcheck+"']").prop('checked', true);
			
			
		});
	}
	 
// 	--------------------------------------------END-->ADMITTED STUDENT---------------------------------------------



// 	--------------------------------------------START-->PASSED STUDENT---------------------------------------------
	
	//SAVE PASSED STUDENT DETAILS
	function Save_As_Draft_Passed_Student_Details(sd) {
		
		$.ajaxSetup({
		    async: false
		});	

		
		var form_data = new FormData(document.getElementById("passed_student"));
		$.ajax({
			url : 'Passed_Student_Details_Save_Draft_Action?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(j){
          if(j>0){
        	  alert("Data Save Sucessfully");
          }
          else{
        	  if(sd != -1){
        	  alert(j);
        	  }
        	  var hid_value = $('#hid_passed_student_'+lastyear(0)).val();
        	  if(hid_value== "0"){
        	  location.reload();
        	  }
          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
			
	 }
	 
	 
	//FETCH PASSED STUDENT DETAILS
	function getPassed_Student_Details() {
		$.ajaxSetup({
			async : false
		});
		
		$.post("getPassed_Student_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
				
				$("#hid_passed_student_"+j[i].year).val(j[i].id);
				$("#appeared_stu_ug_"+j[i].year).val(j[i].appeared_stu_ug);
				$("#passed_stu_ug_"+j[i].year).val(j[i].passed_stu_ug);
				$("#appeared_stu_pg_"+j[i].year).val(j[i].appeared_stu_pg);
				$("#passed_stu_pg_"+j[i].year).val(j[i].passed_stu_pg);
				
			}
			
		});
	}
	 
	 
// 	--------------------------------------------END-->PASSED STUDENT---------------------------------------------
	


// 	--------------------------------------------START-->UPLOAD DOCUMENT---------------------------------------------
	
	//SAVE UPLOAD DOCUMNET DETAILS
	function Save_As_Draft_Upload_Documnet_Details(sd) {
	
	$.ajaxSetup({
		async : false
	});
	
	var form_data = new FormData(document.getElementById("upload_document"));
	$.ajax({
		url : 'Upload_Documnet_Save_Draft_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j) {
				if(j>0){
					$("#hid_upload_document").val(j);
					if(sd != -1){
					alert("Data Saved Successfully");
					}
	          }
	          else{
	        	  if(sd != -1){
	        	  alert(j);
	        	  }
	          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	
}


	//FETCH UPLOAD DOCUMNET DETAILS
	function getUpload_Document_Details() {
		$.ajaxSetup({
			async : false
		});
		
		$.post("getUpload_Document_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			
			$("#hid_lastfiveguide").val(j[0].lastfiveguide);
			$("#hid_neet_score").val(j[0].neet_score);
			$("#hid_undertakingofstudent").val(j[0].undertakingofstudent);
			$("#hid_biometricattendance").val(j[0].biometricattendance);
			
		});
	}


// 	--------------------------------------------END-->UPLOAD DOCUMENT---------------------------------------------



// 	--------------------------------------------START-->INTERNSHIP DETAILS---------------------------------------------
	
	//SAVE INTERNSHIP DETAILS
	function Save_As_Draft_Internship_Details() {
	
	$.ajaxSetup({
		async : false
	});
	
	var form_data = new FormData(document.getElementById("internship_details"));
	$.ajax({
		url : 'Internship_Details_Save_Draft_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j) {
				if(j>0){
					$("#hid_internship_details").val(j);
					alert("Data Saved Successfully");
	          }
	          else{
	        	  alert(j);
	          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	
	}


	//FETCH INTERNSHIP DETAILS
	function getInternship_Details() {
		$.ajaxSetup({
			async : false
		});
		
		$.post("getInternship_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			
			$("#hid_total_intern").val(j[0].id);
			$("#hid_total_intern").val(j[0].total_intern);
// 			$("#internsopd").val(j[0].internsopd);
			$("#internsdutyopd").val(j[0].internsdutyopd);
// 			$("#internsipd").val(j[0].internsipd);
			$("#internsdutyipd").val(j[0].internsdutyipd);
			$("#hid_migration_list").val(j[0].migration_list);
			$("#prescribe").val(j[0].prescribe);
			$("#seminar").val(j[0].seminar);
			$("#internship_not_completed").val(j[0].internship_not_completed);
			$("#house_job").val(j[0].house_job);
			$("#no_house_job").val(j[0].no_house_job);
			$("#graded_teaching").val(j[0].graded_teaching);
			
			var pro_regcheck = j[0].pro_regcheck;
			$("input[name='pro_regcheck'][value='"+pro_regcheck+"']").prop('checked', true);
			
			var rotationcheck = j[0].rotationcheck;
			$("input[name='rotationcheck'][value='"+rotationcheck+"']").prop('checked', true);
			
			var migrationcheck = j[0].migrationcheck;
			$("input[name='migrationcheck'][value='"+migrationcheck+"']").prop('checked', true);
			if(migrationcheck == "1"){
				$("div#hid_migrationcheck").show();
			}
			
		});
	}


// 	--------------------------------------------END-->INTERNSHIP DETAILS---------------------------------------------
	
	
	function Validation() {
	
	for (var i = 0; i < 5; i++) {
		
		var res = CheckNullorBlankZero('govt_quota_ug_'+lastyear(i));
		if (res !== "true") {
			alert(res +"Total Students Admitted for UG With Govt Quota in "+lastyear(i));
			$('#govt_quota_ug_'+lastyear(i)).focus();
			return false;
		}
		var res = CheckNullorBlankZero('mang_quota_ug_'+lastyear(i));
		if (res !== "true") {
			alert(res +"Total Students Admitted for UG With Management Quota in "+lastyear(i));
			$('#mang_quota_ug_'+lastyear(i)).focus();
			return false;
		}
		var res = CheckNullorBlankZero('govt_quota_pg_'+lastyear(i));
		if (res !== "true") {
			alert(res +"Total Students Admitted for PG With Govt Quota in "+lastyear(i));
			$('#govt_quota_pg_'+lastyear(i)).focus();
			return false;
		}
		var res = CheckNullorBlankZero('mang_quota_pg_'+lastyear(i));
		if (res !== "true") {
			alert(res +"Total Students Admitted for PG With Management Quota in "+lastyear(i));
			$('#mang_quota_pg_'+lastyear(i)).focus();
			return false;
		}
		var res = CheckNullorBlank('court_order_'+lastyear(i));
		if (res !== "true") {
			alert(res +"Students Admitted by Court Order in "+lastyear(i));
			$('#court_order_'+lastyear(i)).focus();
			return false;
		}
		var res = CheckNullorBlank('last_student_'+lastyear(i));
		if (res !== "true") {
			alert(res +"Name of the Last Admitted Student in "+lastyear(i));
			$('#last_student_'+lastyear(i)).focus();
			return false;
		}
		var res = CheckNullorBlank('last_stu_add_date_'+lastyear(i));
		if (res !== "true") {
			alert(res +"Date of the Last Admitted Student in "+lastyear(i));
			$('#last_stu_add_date_'+lastyear(i)).focus();
			return false;
		}
		}
		
		var intimatedcheck = $("input[name='intimatedcheck']:checked").val();
		if( intimatedcheck == null ){
			alert("Please Select Intimated Check");
			return false;
	   	}
		
		var intimatedcheck = $("input[name='intimatedcheck']:checked").val();
		if(intimatedcheck == "1"){
		var undertakingcheck = $("input[name='undertakingcheck']:checked").val();
		if( undertakingcheck == null ){
			alert("Please Select Under Taking Check");
			return false;
	   	}
		}	
	
	
		var res_hid_lastfiveguide = CheckNullorBlank('hid_lastfiveguide');
		if(res_hid_lastfiveguide !== "true"){
			var res = CheckNullorBlank('lastfiveguide');
			if (res !== "true") {
				alert(res +"List of PG Student Document");
// 				$('#lastfiveguide').focus();
				return false;
			}
		}
		
		var hid_neet_score = CheckNullorBlank('hid_neet_score');
		if(hid_neet_score !== "true"){
			var res = CheckNullorBlank('neet_score');
			if (res !== "true") {
				alert(res +"Student Details Document");
// 				$('#neet_score').focus();
				return false;
			}
		}
		
		var hid_undertakingofstudent = CheckNullorBlank('hid_undertakingofstudent');
		if(hid_undertakingofstudent !== "true"){
			var res = CheckNullorBlank('undertakingofstudent');
			if (res !== "true") {
				alert(res +"Upload the Undertaking of the Students");
// 				$('#undertakingofstudent').focus();
				return false;
			}
		}
		
		var hid_biometricattendance = CheckNullorBlank('hid_biometricattendance');
		if(hid_biometricattendance !== "true"){
			var res = CheckNullorBlank('biometricattendance');
			if (res !== "true") {
				alert(res +"Document of PG Student Attendance");
// 				$('#biometricattendance').focus();
				return false;
			}
		}
	
		for (var i = 0; i < 5; i++) {
			
			var res = CheckNullorBlankZero('appeared_stu_ug_'+lastyear(i));
			if (res !== "true") {
				alert(res +"Total Students Appeared in Exam for UG Course in "+lastyear(i));
				$('#appeared_stu_ug_'+lastyear(i)).focus();
				return false;
			}
			var res = CheckNullorBlankZero('passed_stu_ug_'+lastyear(i));
			if (res !== "true") {
				alert(res +"Total Students Passed in Exam for UG Course in "+lastyear(i));
				$('#passed_stu_ug_'+lastyear(i)).focus();
				return false;
			}
			var res = CheckNullorBlankZero('appeared_stu_pg_'+lastyear(i));
			if (res !== "true") {
				alert(res +"Total Students Appeared in Exam for PG Course in "+lastyear(i));
				$('#appeared_stu_pg_'+lastyear(i)).focus();
				return false;
			}
			var res = CheckNullorBlankZero('passed_stu_pg_'+lastyear(i));
			if (res !== "true") {
				alert(res +"Total Students Passed in Exam for PG Course in "+lastyear(i));
				$('#passed_stu_pg_'+lastyear(i)).focus();
				return false;
			}
		}
		
		
		var pro_regcheck = $("input[name='pro_regcheck']:checked").val();
		if( pro_regcheck == null ){
			alert("Please Select Provisional Registration");
			return false;
	   	}
		var rotationcheck = $("input[name='rotationcheck']:checked").val();
		if( rotationcheck == null ){
			alert("Please Select Rotation Programme");
			return false;
	   	}
// 		var res = CheckNullorBlank('internsopd');
// 		if (res !== "true") {
// 			alert(res +"Total Intern in O.P.D");
// 			$('#internsopd').focus();
// 			return false;
// 		}
		var res = CheckNullorBlank('internsdutyopd');
		if (res !== "true") {
			alert(res +"Interns Duty in O.P.D");
			$('#internsdutyopd').focus();
			return false;
		}
// 		var res = CheckNullorBlank('internsipd');
// 		if (res !== "true") {
// 			alert(res +"Total Intern in IPD");
// 			$('#internsipd').focus();
// 			return false;
// 		}
		var res = CheckNullorBlank('internsdutyipd');
		if (res !== "true") {
			alert(res +"Interns Duty in IPD");
			$('#internsdutyipd').focus();
			return false;
		}
		var migrationcheck = $("input[name='migrationcheck']:checked").val();
		if( migrationcheck == null ){
			alert("Please Select Migration of Student from/to College");
			return false;
	   	}
		
		if(migrationcheck == "1"){
		var hid_migration_list = CheckNullorBlank('hid_migration_list');
			if(hid_migration_list !== "true"){
				var res = CheckNullorBlank('migration_list');
				if (res !== "true") {
				alert(res +"Migration List");
				$('#migration_list').focus();
				return false;
				}
			}
		}
		var res = CheckNullorBlank('prescribe');
		if (res !== "true") {
			alert(res +"Prescribe Medicine");
			$('#prescribe').focus();
			return false;
		}
		var res = CheckNullorBlank('seminar');
		if (res !== "true") {
			alert(res +"Seminar for Internee");
			$('#seminar').focus();
			return false;
		}
		var res = CheckNullorBlank('internship_not_completed');
		if (res !== "true") {
			alert(res +"Number of Students who have not Completed Internship");
			$('#internship_not_completed').focus();
			return false;
		}
		var res = CheckNullorBlank('house_job');
		if (res !== "true") {
			alert(res +"House Job");
			$('#house_job').focus();
			return false;
		}
		var res = CheckNullorBlank('no_house_job');
		if (res !== "true") {
			alert(res +"Number of Students undergoing House Job");
			$('#no_house_job').focus();
			return false;
		}
		var res = CheckNullorBlank('graded_teaching');
		if (res !== "true") {
			alert(res +"Graded Teaching Work");
			$('#graded_teaching').focus();
			return false;
		}
		
		Save_As_Draft_Admitted_Student_Details(-1);
		Save_As_Draft_Upload_Documnet_Details(-1);
		Save_As_Draft_Passed_Student_Details(-1);
		Save_As_Draft_Internship_Details();
		
	}
	
	
//==============================CSP START==============================

	document.addEventListener('DOMContentLoaded', function() {
		
		//ADMITTED STUDENT START
		document.getElementById('admitted_student_details_save').onclick = function() {
			return Save_As_Draft_Admitted_Student_Details();	
		};
		document.getElementById('intimatedYes').onclick = function() {
			Intimated_Yes();
		};
		document.getElementById('intimatedNo').onclick = function() {
			Intimated_Yes();
		};
		
		
		//UPLOAD DOCUMENT START
		document.getElementById('upload_document_details_save').onclick = function() {
			return Save_As_Draft_Upload_Documnet_Details();	
		};
		document.getElementById('lastfiveguide').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"lastfiveguide","200kb","lastfiveguide_lbltik","lastfiveguide_lbl",this.accept)
		};
		document.getElementById('neet_score').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"neet_score","200kb","neet_score_lbltik","neet_score_lbl",this.accept)
		};
		document.getElementById('undertakingofstudent').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"undertakingofstudent","200kb","undertakingofstudent_lbltik","undertakingofstudent_lbl",this.accept)
		};
		document.getElementById('biometricattendance').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"biometricattendance","200kb","biometricattendance_lbltik","biometricattendance_lbl",this.accept)
		};
		
		//PASSED STUDENT START
		document.getElementById('passed_student_details_save').onclick = function() {
			return Save_As_Draft_Passed_Student_Details();	
		};
		
		//INTERNSHIP DETAILS START
		document.getElementById('internship_details_save').onclick = function() {
			return Validation();	
		};
		document.getElementById('migration_Yes').onclick = function() {
			Migration_Yes();
		};
		document.getElementById('migration_No').onclick = function() {
			Migration_Yes();
		};
// 		document.getElementById('internsopd').onkeypress = function() {
// 			return isNumberKey0to9(event, this);
// 		};
// 		document.getElementById('internsipd').onkeypress = function() {
// 			return isNumberKey0to9(event, this);
// 		};
		document.getElementById('internsdutyopd').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('internsdutyipd').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('prescribe').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('seminar').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('internship_not_completed').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('house_job').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('no_house_job').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('graded_teaching').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('total_intern').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"total_intern","200kb","total_intern_lbltik","total_intern_lbl",this.accept)
		};
		document.getElementById('migration_list').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"migration_list","200kb","migration_list_lbltik","migration_list_lbl",this.accept)
		};

		
	});
	
//==============================CSP END==============================
	
	document.querySelectorAll('.viewData').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var hid = document.getElementById('viewId${parent_id}').value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				ViewData(hid);
			} else {
				return false;
			}
		})
	});
	
function ViewData(id){
	
	$("#student_dtl_view_id").val(id);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
}
	
</script>

