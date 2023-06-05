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
							<h2>College Infrastructure</h2>
						</div>
					</div>
					<!-- end col -->
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="breadcrumb-wrapper mb-30">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
									<li class="breadcrumb-item active" aria-current="page">
										College Infrastructure</li>
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
									<div class="inst_block">
										<h5>Instruction</h5>
										<ul class="inst_list">
											<li><p class="inst_text">If any of the value is not
													available or not applicable then please put it as 0</p></li>
											<li><p class="inst_text">Please click on 'Download
													templates' to download the templates being uploaded in
													applicable sections of the form.</p></li>
											<li><p class="inst_text">For uploading the required
													files, take a print on the letterhead of the Institution,
													and after getting it stamped and signed, upload the scanned
													copy by clicking at the appropriate option.</p></li>
										</ul>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 col-12">
								<div class="custom-data-value custom-data-value-form">
										<label>Institution Name :</label> <span class="value-bind">${roleloginName }</span> 
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 col-12">
								<div class="custom-data-value custom-data-value-form">
										<label>College Code :</label> <span class="value-bind">${username}</span> 
									</div>
								
								</div>
							</div>
						</div>					
					</div>
				
				<!-- ===========================
							College Infrastructure Start
						=========================== -->
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="card-style mb-30">
						<div class="tabs content_h900">
							<div class="tab">
								<button class="tab-toggle" id="defaultOpen">College
									Council & Website Details</button>
							</div>
							<div class="content ">
								<h4 class="heading">College Council & Website Details</h4>
								<form:form name="college_council" id="college_council" action="" method="post" class=""
									commandName="">
									<div class="row">
									
									<input type="hidden" id="hid_college_council" name="hid_college_council" value="0">
									<input type="hidden" id="basic_info_id" name="basic_info_id" value="0">

										<div class="col-lg-12 col-md-12 col-sm-12">
											<div class="accordion accordion-flush"
												id="accordionFlushExample">
												<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-headingOne">
														<button class="accordion-button accordion-secondary-btn"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapseOne" aria-expanded="true"
															aria-controls="flush-collapseOne">College
															Council & Website Details</button>
													</h2>
													<div id="flush-collapseOne"
														class="accordion-collapse collapse show"
														aria-labelledby="flush-headingOne"
														data-bs-parent="#accordionFlushExample">
														<div class="accordion-body">

															<div class="row">
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<label>Is College Council
																		available?<strong class="mandatory">*</strong>
																	</label>
																	<div class="input-style-form-check">

																		<div class="form-check radio-style">
																			<input type="radio" id="council_yes" name="council_check"
																				class="form-check-input" value="1"> <label
																				for="council_Yes" class="form-check-label">Yes</label>
																		</div>
																		<div class="form-check radio-style">
																			<input type="radio" id="council_no" name="council_check"
																				class="form-check-input" value="2"> <label
																				for="council_No" class="form-check-label">No</label>
																		</div>
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hidden_council_documnet">
																	<div class="input-style-1">
																		<label>If Council available upload
																			details here <strong class="mandatory">*</strong></label> <input type="file" id="council_document" name="council_document"
																			class="form-control" accept=".pdf"><input type="hidden"
																				id="hid_council_document" name="hid_council_document"
																				class="form-control" value="">
																		<div class="note-text">
																			<span class="errorClass" id="council_document_lbl">${doc_path_doc1_msg}</span>
																			<span class='tikClass' id="council_document_lbltik"></span>
																		</div>
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<label>Is College website is as per
																		MSR and All UG/PG students details updated on college
																		website?<strong class="mandatory">*</strong>
																	</label>
																	<div class="input-style-form-check">

																		<div class="form-check radio-style">
																			<input type="radio" id="college_website_yes" name="college_website"
																				class="form-check-input" value="1"> <label
																				for="Yes" class="form-check-label">Yes</label>
																		</div>
																		<div class="form-check radio-style">
																			<input type="radio" id="college_website_no" name="college_website"
																				class="form-check-input" value="2"> <label
																				for="No" class="form-check-label">No</label>
																		</div>

																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-2">
																		<label>Last website update date<strong
																			class="mandatory">* </strong>
																		</label> <input type="text" name="website_update_date" id="website_update_date"
																			class="form-control-sm form-control effect-9 "
																			aria-required="true" autocomplete="off"
																			value="DD/MM/YYYY">
																	</div>
																	<input type="hidden" id="yrr" name="yrr" value="">
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<label>Is CCTV installed as per CCH/NCH
																		instruction ?<strong class="mandatory">*</strong>
																	</label>
																	<div class="input-style-form-check">

																		<div class="form-check radio-style">
																			<input type="radio" id="cctv_status_yes" name="cctv_status"
																				class="form-check-input" value="1"> <label
																				for="CCHYes" class="form-check-label">Yes</label>
																		</div>
																		<div class="form-check radio-style">
																			<input type="radio" id="cctv_status_no" name="cctv_status"
																				class="form-check-input" value="2"> <label
																				for="CCHNo" class="form-check-label">No</label>
																		</div>

																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">

																	<div class="input-style-1">
																		<label>Login Url<strong class="mandatory">*</strong></label>
																		<input type="text" id="login_url" name="login_url"
																			class="form-control" placeholder="Login Url" maxlength="50">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Username<strong class="mandatory">*</strong></label>
																		<input type="text" id="username_ci" name="username_ci"
																			class="form-control" placeholder="Username" maxlength="50">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Password<strong class="mandatory">*</strong></label>
																		<input type="password" id="password_ci" name="password_ci"
																			class="form-control" placeholder="Password" maxlength="50">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<label>Is Biometric available?<strong
																		class="mandatory">*</strong></label>
																	<div class="input-style-form-check">

																		<div class="form-check radio-style">
																			<input type="radio" id="biometric_status_yes" name="biometric_status"
																				class="form-check-input" value="1"> <label
																				for="BiometricYes" class="form-check-label">Yes</label>
																		</div>
																		<div class="form-check radio-style">
																			<input type="radio" id="biometric_status_no" name="biometric_status"
																				class="form-check-input" value="2"> <label
																				for="BiometricNo" class="form-check-label">No</label>
																		</div>
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Working hours of College<strong
																			class="mandatory">*</strong></label> <input type="text"
																			name="college_working_hours" id="college_working_hours"
																			class="form-control"
																			placeholder="Working hours of College" maxlength="2">
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="accordion-item accordion-itemflush custom-d-none" id="camera_location_id">
													<h2 class="accordion-header" id="flush-headingTwo">
														<button
															class="accordion-button accordion-secondary-btn collapsed"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapseTwo" aria-expanded="false"
															aria-controls="flush-collapseTwo">Camera
															Location</button>
													</h2>
													<div id="flush-collapseTwo"
														class="accordion-collapse collapse"
														aria-labelledby="flush-headingTwo"
														data-bs-parent="#accordionFlushExample">
														<div class="accordion-body">
															<div class="tables-wrapper">
																<div class="row">
																
																<input type="hidden" id="indno_camera" name="indno_camera" value="0">
																<input type="hidden" id="camera_count" name="camera_count" value="0">
																
																	<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																	<div class="inst_block simple-instruction">
								<strong>Instruction :</strong> Kindly upload photographs of 5 locations
							</div>
																
																	</div>
																	<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																		<div
																			class="table-wrapper table-responsive custom-table">
																			<table class="table" id="locationphoto_table">
																				<thead>
																					<tr>
																						<th><h6>Sr No</h6></th>
																						<th><h6>
																								Camera Location<strong class="mandatory">*</strong>
																							</h6></th>

																						<th><h6>Action</h6></th>
																					</tr>
																					<!-- end table row-->
																				</thead>
																				<tbody id="">
																					<tr id="tr_sibling_camera1">
																						<td>

																							<p>1</p>

																						</td>
																						<td>
																							<div class="input-style-1">
																								<input type="file" id="camera_location1"
																									name="camera_location1" class="form-control" accept="jpg,jpeg,png">
																									<input type=hidden id="hid_camera_location1" name="hid_camera_location1"
																									class="form-control" value="">
																									
																									<div class="note-text">
																										<span class="errorClass" id="camera_location_lbl1">${doc_path_doc1_msg}</span>
																										<span class='tikClass' id="camera_location_lbltik1"></span>
																									</div>
																							</div>
																						</td>
																						<td class="addminusbut">
																							<ul class="buttons-group mainbtn daobtn action">
																								<li><a
																									class="main-btn success-btn btn-hover btn-sm btnapprove"
																									value="Save" title="SAVE" id="family_save_camera1">
																										<i class="lni lni-checkmark"></i>
																								</a></li>
																								<li><a
																									class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none"
																									value="ADD" title="ADD" id="camera_add1">
																										<i class="lni lni-plus"></i>
																								</a></li>
																								<li><a
																									class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"
																									value="Delete" title="Delete" id="camera_remove1"><i
																										class="lni lni-trash-can"></i></a></li>
																							</ul>
																						</td>
																					</tr>
																					<!-- end table row -->
																				</tbody>
																			</table>
																			
																			<input type="hidden" id="family_save_camerah1"
																						name="family_save_camerah1"
																						class="form-control autocomplete" value="1">
																		<input type="hidden" id="hid_camera1" name="hid_camera1" value="0" class="form-control autocomplete" autocomplete="off">
																			
																			
																		</div>
																		<!-- end card -->
																	</div>
																	<!-- end col -->
																</div>
																<!-- end row -->
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover btnsave" id="college_council_details_save"
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
							<!-- College Council & Website Details End-->
							<!-- Progress of Institution Start-->
							<div class="tab" id="progress_institution_btn">
								<button class="tab-toggle">Progress of Institution</button>
							</div>
							<div class="content">
								<h4 class="heading">Progress of Institution</h4>
								<form:form name="progress_institution" id="progress_institution" action="" method="post" class=""
									commandName="">
									<div class="row">
									
									<input type="hidden" name="hid_progress_institution" id="hid_progress_institution" value="0">
									
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="inst_block simple-instruction">
								<strong>Instruction :</strong> Please give a descriptive answer of progress made by the institution in the last two years on salient points.
							</div>
										</div>
										<hr>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Construction of college & hospital building</label>
												<textarea placeholder="Message" rows="5"
													id="cons_clg_hospital" name="cons_clg_hospital" maxlength="250"></textarea>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Appointment of Teaching staff</label>
												<textarea placeholder="Message" rows="5" id="app_teaching_staff"
													name="app_teaching_staff" maxlength="250"></textarea>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Appointment of Non-Teaching staff</label>
												<textarea placeholder="Message" rows="5"
													id="app_non_teaching_staff" name="app_non_teaching_staff" maxlength="250"></textarea>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Appointment of Paramedical and other Hospital
													staff
												</label>
												<textarea placeholder="Message" rows="5"
													id="app_paramedical" name="app_paramedical" maxlength="250"></textarea>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Expansion of various departments of college</label>
												<textarea placeholder="Message" rows="5"
													id="expansion_various_dept" name="expansion_various_dept" maxlength="250"></textarea>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Expansion of Herbal Garden, Plantation of New
													Plants
												</label>
												<textarea placeholder="Message" rows="5"
													id="expansion_herbal_ganden" name="expansion_herbal_ganden" maxlength="250"></textarea>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Hospital OPD</label>
												<textarea placeholder="Message" rows="5" id="hospital_opd"
													name="hospital_opd" maxlength="250"></textarea>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Hospital IPD</label>
												<textarea placeholder="Message" rows="5" id="hospital_ipd"
													name="hospital_ipd" maxlength="250"></textarea>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Any national/international/state level
													seminars, ROTP, CME etc
												</label>
												<textarea placeholder="Message" rows="5"
													id="seminars" name="seminars" maxlength="250"></textarea>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Publication by college and teaching staff</label>
												<textarea placeholder="Message" rows="5"
													id="pulication_by_clg" name="pulication_by_clg" maxlength="250"></textarea>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Research activities if any</label>
												<textarea placeholder="Message" rows="5"
													id="research_activities" name="research_activities" maxlength="250"></textarea>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Awards won by teaching staffs and students</label>
												<textarea placeholder="Message" rows="5"
													id="award_details" name="award_details" maxlength="250"></textarea>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<ul class="buttons-group mainbtn">
												<li><input class="main-btn info-btn btn-hover btnsave" id="progress_institution_details_save"
													type="button" value="Save"></li>
											</ul>
										</div>
									</div>
								</form:form>
							</div>
							<!-- Progress of Institution End-->
							<!-- Constructed Area Start-->
							<div class="tab" id="constructed_area_btn">
								<button class="tab-toggle">Constructed Area</button>
							</div>
							<div class="content ">
								<form:form name="constructed_area" id="constructed_area" action="" method="post" class=""
									commandName="">
									<div class="row">
									
									<input type="hidden" id="hid_constructed_area" name="hid_constructed_area" value="0">
									
										<h4 class="heading">Constructed Area</h4>
										<div class="col-lg-12 col-md-12 col-sm-12">
											<div class="accordion accordion-flush"
												id="accordionFlushExample1">
												<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-headingthree">
														<button class="accordion-button accordion-secondary-btn"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapsethree"
															aria-expanded="true" aria-controls="flush-collapsethree">Details
															of Various Components of College other than Teaching Department (in Sq. mtr.)</button>
													</h2>
													<div id="flush-collapsethree"
														class="accordion-collapse collapse show"
														aria-labelledby="flush-headingOne"
														data-bs-parent="#accordionFlushExample1">
														<div class="accordion-body">
															<div class="row">
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Construction of college &
																			hospital building<strong class="mandatory">*</strong>
																		</label> <input type="text" name="college_const"
																			id="college_const" class="form-control" value="0"
																			placeholder="Available Area (in Sq. mtr.)" maxlength="10">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Total Area of Lecturer
																			Halls<strong class="mandatory">*</strong>
																		</label> <input type="text" name="lecturer_hall"
																			id="lecturer_hall" class="form-control" value="0"
																			placeholder="Available Area (in Sq. mtr.)" maxlength="10">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Auditorium/Seminar
																			Hall/Exam hall<strong class="mandatory">*</strong>
																		</label> <input type="text" name="exam_hall"
																			id="exam_hall" class="form-control" value="0"
																			placeholder="Available Area (in Sq. mtr.)" maxlength="10">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Central Library<strong
																			class="mandatory">*</strong>
																		</label> <input type="text" name="central_library"
																			id="central_library" class="form-control" value="0"
																			placeholder="Available Area (in Sq. mtr.)" maxlength="10">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Common Rooms for Boys<strong
																			class="mandatory">*</strong>
																		</label> <input type="text" name="boys_common_room"
																			id="boys_common_room" class="form-control" value="0"
																			placeholder="Available Area (in Sq. mtr.)" maxlength="10">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Common Rooms for Girls<strong
																			class="mandatory">*</strong>
																		</label> <input type="text" name="girl_common_room"
																			id="girl_common_room" class="form-control" value="0"
																			placeholder="Available Area (in Sq. mtr.)" maxlength="10">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Canteen<strong
																			class="mandatory">*</strong>
																		</label> <input type="text" name="canteen" id="canteen"
																			class="form-control" value="0"
																			placeholder="Available Area (in Sq. mtr.)" maxlength="10">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Administrative section<strong
																			class="mandatory">*</strong>
																		</label> <input type="text" name="administrative_section"
																			id="administrative_section" class="form-control" value="0"
																			placeholder="Available Area (in Sq. mtr.)" maxlength="10">
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-headingfour">
														<button
															class="accordion-button accordion-secondary-btn collapsed"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapsefour"
															aria-expanded="false" aria-controls="flush-collapsefour">Details
															of Teaching Department</button>
													</h2>
													<div id="flush-collapsefour"
														class="accordion-collapse collapse"
														aria-labelledby="flush-headingTwo"
														data-bs-parent="#accordionFlushExample1">
														<div class="accordion-body">
															<div class="row">
															<div
																		class="table-wrapper table-responsive custom-table">
																		<table class="table" id="department_table">
																			<thead>
																				<tr>
																					<th><h6>Sr No</h6></th>
																					<th><h6>
																							Department Name<strong class="mandatory">*</strong>
																						</h6></th>
																					<th><h6>
																							Area of Department (in Sq. mtr.)<strong class="mandatory">*</strong>
																						</h6></th>
																					
																				</tr>
																				<!-- end table row-->
																			</thead>
																			<tbody id="">
																			${getAllDepartment}
																			</tbody>
																		</table>
																		
																	</div>
															
															<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Total Constructed Area of
																			College (in Sq. mtr.)<strong class="mandatory">*</strong>
																		</label> <input type="text" name="total_area_of_college"
																			id="total_area_of_college" class="form-control" value="0"
																			placeholder="Available Area (in Sq. mtr.)" maxlength="10">
																	</div>
															</div>
															<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																<div class="input-style-1">
																	<label>Total Lecture Halls <strong
																		class="mandatory">*</strong></label> <input type="text" value="0"
																		name="total_lecture_hall" id="total_lecture_hall" class="form-control"
																		placeholder="Total Lecture Halls" maxlength="10">
																</div>
															</div>
														</div>
														<!-- end row -->
														</div>
													</div>
												</div>
												<!-- <div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-headingfive">
														<button
															class="accordion-button accordion-secondary-btn collapsed"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapsefive"
															aria-expanded="false" aria-controls="flush-collapsefive">Total
															Details</button>
													</h2>
													<div id="flush-collapsefive"
														class="accordion-collapse collapse"
														aria-labelledby="flush-headingfive"
														data-bs-parent="#accordionFlushExample1">
														<div class="accordion-body">
															<div class="row">
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Total Constructed Area of
																			College (in Sq. mtr.)<strong class="mandatory">*</strong>
																		</label> <input type="text" name="total_area_of_college"
																			id="total_area_of_college" class="form-control" value="0"
																			placeholder="Available Area (in Sq. mtr.)" maxlength="10">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Total Lecture Halls <strong
																			class="mandatory">*</strong></label> <input type="text" value="0"
																			name="total_lecture_hall" id="total_lecture_hall" class="form-control"
																			placeholder="Total Lecture Halls" maxlength="10">
																	</div>
																</div>
															</div>
															end row
														</div>
													</div>
												</div> -->
											</div>
										</div>
										<!-- end col -->
									</div>
									<!-- end row -->
									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover btnsave" id="constructed_area_details_save"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>
							</div>
							<!-- Constructed Area End-->
							<!-- Central Library Start-->
							<div class="tab" id="central_library_form_btn">
								<button class="tab-toggle">Central Library</button>
							</div>
							<div class="content ">
								<h4 class="heading">Central Library</h4>
								<form:form name="central_library_form" id="central_library_form" action="" method="post" class=""
									commandName="">
									<div class="row">
									
									<input type="hidden" name="hid_central_library" id="hid_central_library" value="0">
									
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="accordion accordion-flush"
												id="accordionFlushExample2">
												<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-headingsix">
														<button class="accordion-button accordion-secondary-btn"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapsesix" aria-expanded="true"
															aria-controls="flush-collapsesix">Books</button>
													</h2>
													<div id="flush-collapsesix"
														class="accordion-collapse collapse show"
														aria-labelledby="flush-headingsix"
														data-bs-parent="#accordionFlushExample2">
														<div class="accordion-body">
															<div class="row">
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Text Book<strong class="mandatory">*</strong></label>
																		<input type="text" id="text_book" name="text_book"
																			class="form-control" autocomplete="off"
																			placeholder="Text Book" maxlength="100">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Reference Book<strong class="mandatory">*</strong></label>
																		<input type="text" id="regerence_book"
																			name="regerence_book" class="form-control"
																			autocomplete="off" placeholder="Reference Book" maxlength="100">

																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>CCH/NCH, CCRH & Government Publications
																			available<strong class="mandatory">*</strong>
																		</label> <input type="text" id="govn_publication"
																			name="govn_publication" class="form-control"
																			autocomplete="off"
																			placeholder="CCH/NCH, CCRH & Government Publications" maxlength="100">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>New Addition<strong class="mandatory">*</strong></label>
																		<input type="text" id="new_addition" name="new_addition"
																			class="form-control" autocomplete="off"
																			placeholder="New Addition" maxlength="100">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Book Bank<strong class="mandatory">*</strong></label>
																		<input type="text" id="book_bank" name="book_bank"
																			class="form-control" autocomplete="off"
																			placeholder="Book Bank" maxlength="100">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Total No. of books<strong
																			class="mandatory">*</strong></label> <input type="text"
																			id="total_book_no" name="total_book_no"
																			class="form-control" autocomplete="off"
																			placeholder="Total No. of books" maxlength="10">
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-headingseven">
														<button
															class="accordion-button accordion-secondary-btn collapsed"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapseseven"
															aria-expanded="false" aria-controls="flush-collapseseven">Number
															of Journal (Indian/Foreign)</button>
													</h2>
													<div id="flush-collapseseven"
														class="accordion-collapse collapse"
														aria-labelledby="flush-headingseven"
														data-bs-parent="#accordionFlushExample2">
														<div class="accordion-body">
															<div class="row">
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Subscribed<strong class="mandatory">*</strong></label>
																		<input type="text" id="subscribed_no" name="subscribed_no"
																			class="form-control" autocomplete="off"
																			placeholder="Subscribed" maxlength="10">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Complementary<strong class="mandatory">*</strong></label>
																		<input type="text" id="complementary_no"
																			name="complementary_no" class="form-control"
																			autocomplete="off" placeholder="Complementary" maxlength="10">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>News Paper<strong class="mandatory">*</strong></label>
																		<input type="text" id="news_paper_no" name="news_paper_no"
																			class="form-control" autocomplete="off"
																			placeholder="News Paper" maxlength="10">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Back Issue<strong class="mandatory">*</strong></label>
																		<input type="text" id="back_issue_no" name="back_issue_no"
																			class="form-control" autocomplete="off"
																			placeholder="Back Issue" maxlength="10">
																	</div>
																</div>
															</div>
															<!-- end row -->
														</div>
													</div>
												</div>
												<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-headingeight">
														<button
															class="accordion-button accordion-secondary-btn collapsed"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapseeight"
															aria-expanded="false" aria-controls="flush-collapseeight">Library
															Details</button>
													</h2>
													<div id="flush-collapseeight"
														class="accordion-collapse collapse"
														aria-labelledby="flush-headingeight"
														data-bs-parent="#accordionFlushExample2">
														<div class="accordion-body">
															<div class="row">
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Working Hours<strong class="mandatory">*</strong></label>
																		<input type="text" id="library_working_hours"
																			name="library_working_hours" class="form-control"
																			autocomplete="off" placeholder="Working Hours" maxlength="2">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Reading Room for Students with Capacity<strong
																			class="mandatory">*</strong>
																		</label> <input type="text" id="student_reading_room_capacity" name="student_reading_room_capacity"
																			class="form-control" autocomplete="off"
																			placeholder="Reading Room Capacity" maxlength="100">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Reading Room for Teaching Faculty<strong
																			class="mandatory">*</strong></label> <input type="text"
																			id="faculty_reading_room_capacity" name="faculty_reading_room_capacity"
																			class="form-control" autocomplete="off"
																			placeholder="Reading Room Capacity" maxlength="100">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Reading Room Purpose<strong
																			class="mandatory">*</strong></label> <input type="text"
																			id="rading_room_purpose" name="rading_room_purpose" class="form-control"
																			autocomplete="off" placeholder="Reading Room Purpose" maxlength="100">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Home Lending<strong class="mandatory">*</strong></label>
																		<input type="text" id="home_lending"
																			name="home_lending" class="form-control"
																			autocomplete="off" placeholder="Home Lending" maxlength="100">
																	</div>
																</div>

																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Photocopying Facility<strong
																			class="mandatory">*</strong></label> <input type="text"
																			id="photocopying_facility" name="photocopying_facility" class="form-control"
																			autocomplete="off"
																			placeholder="Photocopying Facility" maxlength="100">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<label>Computers with Internet
																		Facility<strong class="mandatory">*</strong>
																	</label>
																	<div class="input-style-form-check">

																		<div class="form-check radio-style">
																			<input type="radio" id="computers_facility_yes" name="computers_facility"
																				class="form-check-input" value="1"> <label
																				for="internetfaciYes" class="form-check-label">Yes</label>
																		</div>
																		<div class="form-check radio-style">
																			<input type="radio" id="computers_facility_no" name="computers_facility"
																				class="form-check-input" value="2"> <label
																				for="internetfaciNo" class="form-check-label">No</label>
																		</div>
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<label>Whether Cataloguing of books
																		done?<strong class="mandatory">*</strong>
																	</label>
																	<div class="input-style-form-check">

																		<div class="form-check radio-style">
																			<input type="radio" id="cataloguing_books_yes" name="cataloguing_books"
																				class="form-check-input" value="1"> <label
																				for="CataloguingYes" class="form-check-label">Yes</label>
																		</div>
																		<div class="form-check radio-style">
																			<input type="radio" id="cataloguing_books_no" name="cataloguing_books"
																				class="form-check-input" value="2"> <label
																				for="CataloguingNo" class="form-check-label">No</label>
																		</div>
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hidden_cataloguing_books">
																	<div class="input-style-1">
																		<label>System of Cataloguing<strong
																			class="mandatory">*</strong></label> <input type="text"
																			id="cataloguing_system" name="cataloguing_system"
																			class="form-control" autocomplete="off"
																			placeholder="System of Cataloguing" maxlength="100">

																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Name of Library Assistant<strong
																			class="mandatory">*</strong></label> <input type="text"
																			id="librarian_name" name="librarian_name"
																			class="form-control" autocomplete="off"
																			placeholder="Name of Librarian" maxlength="100">

																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Qualification of Librarian<strong
																			class="mandatory">*</strong></label> <input type="text"
																			id="librarian_qualification"
																			name="librarian_qualification" class="form-control"
																			autocomplete="off"
																			placeholder="Qualification of Librarian" maxlength="100">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<label>Is E-Library Facility available?<strong class="mandatory">*</strong>
																	</label>
																	<div class="input-style-form-check">

																		<div class="form-check radio-style">
																			<input type="radio" id="elibrary_check_yes" name="elibrary_check"
																				class="form-check-input" value="1"> <label
																				for="CataloguingYes" class="form-check-label">Yes</label>
																		</div>
																		<div class="form-check radio-style">
																			<input type="radio" id="elibrary_check_no" name="elibrary_check"
																				class="form-check-input" value="2"> <label
																				for="CataloguingNo" class="form-check-label">No</label>
																		</div>
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_total_computers">
																	<div class="input-style-1">
																		<label>Total No. of Computers<strong
																			class="mandatory">*</strong></label> <input type="text"
																			id="total_computers"
																			name="total_computers" class="form-control"
																			autocomplete="off" value="0"
																			placeholder="Total Computers" maxlength="10">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_total_subscription">
																	<div class="input-style-1">
																		<label>Total No. of Subscriptions<strong
																			class="mandatory">*</strong></label> <input type="text"
																			id="total_subscription"
																			name="total_subscription" class="form-control"
																			autocomplete="off"
																			placeholder="Total Subscriptions" value="0" maxlength="10">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_total_ebooks">
																	<div class="input-style-1">
																		<label>Total No. of E-Books<strong
																			class="mandatory">*</strong></label> <input type="text"
																			id="total_ebooks"
																			name="total_ebooks" class="form-control"
																			autocomplete="off"
																			placeholder="Total eBooks" value="0" maxlength="10">
																	</div>
																</div>
															</div>
															<!-- end row -->
														</div>
													</div>
												</div>
												<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-headingnine">
														<button
															class="accordion-button accordion-secondary-btn collapsed"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapsenine"
															aria-expanded="false" aria-controls="flush-collapsenine">Library
															Assistants</button>
													</h2>
													<div id="flush-collapsenine"
														class="accordion-collapse collapse"
														aria-labelledby="flush-headingnine"
														data-bs-parent="#accordionFlushExample2">
														<div class="accordion-body">
															<div class="row">
															
															<input type="hidden" id="hid_library_assistants" name="hid_library_assistants" value="0">
															<input type="hidden" id="indno_library" name="indno_library" value="0">
																<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																	<div
																		class="table-wrapper table-responsive custom-table">
																		<table class="table" id="library_assi_table">
																			<thead>
																				<tr>
																					<th><h6>Sr No</h6></th>
																					<th><h6>
																							Name<strong class="mandatory">*</strong>
																						</h6></th>
																					<th><h6>
																							Qualification<strong class="mandatory">*</strong>
																						</h6></th>
																					<th><h6>Action</h6></th>
																				</tr>
																				<!-- end table row-->
																			</thead>
																			<tbody id="">
																				<tr id="tr_sibling_library1">
																					<td>
																						<p>1</p>
																					</td>
																					<td>
																						<div class="input-style-1">
																							<input type="text" id="library_assistants_name1"
																								name="library_assistants_name1" class="form-control"
																								autocomplete="off"
																								placeholder="Name of Assistants" maxlength="100">
																						</div>
																					</td>
																					<td>
																						<div class="input-style-1">
																							<input type="text" id="assistants_qualification1"
																								name="assistants_qualification1"
																								class="form-control" autocomplete="off"
																								placeholder="Qualification of Assistants" maxlength="100">
																						</div>
																					</td>
																					<td class="addminusbut">
																						<ul class="buttons-group mainbtn daobtn action">
																								<li><a
																									class="main-btn success-btn btn-hover btn-sm btnapprove"
																									value="Save" title="SAVE" id="family_save_library1">
																										<i class="lni lni-checkmark"></i>
																								</a></li>
																								<li><a
																									class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none"
																									value="ADD" title="ADD" id="library_add1">
																										<i class="lni lni-plus"></i>
																								</a></li>
																								<li><a
																									class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"
																									value="Delete" title="Delete" id="library_remove1"><i
																										class="lni lni-trash-can"></i></a></li>
																							</ul>
																					</td>
																				</tr>
																				<!-- end table row -->
																			</tbody>
																		</table>
																		
																		<input type="hidden" id="family_save_libraryh1"
																						name="family_save_libraryh1"
																						class="form-control autocomplete" value="1">
																		<input type="hidden" id="hid_library1" name="hid_library1" value="0" class="form-control autocomplete" autocomplete="off">
																		
																	</div>
																	<!-- end card -->
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Other information (if any)</label> <input type="text"
																			id="other_information" name="other_information"
																			class="form-control" autocomplete="off"
																			placeholder="Other Information" maxlength="100">
																	</div>
																</div>
															</div>
															<!-- end row -->
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- end col -->
									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover btnsave" id="central_library_details_save"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>
							</div>
							<!-- Central Library End-->
							<!-- Details Of Hostel Start-->
							<div class="tab" id="hostel_facility_btn">
								<button class="tab-toggle">Details Of Hostel</button>
							</div>
							<div class="content ">
								<h4 class="heading">Details Of Hostel</h4>
								<form:form name="hostel_facility" id="hostel_facility" action="" method="post" class=""
									commandName="">
									<div class="tables-wrapper">
										<div class="row">
										
										<input type="hidden" name="hid_hostel_facility" id="hid_hostel_facility" value="0">
										
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="hostel_table">
														<thead>
															<tr>
																<th><h6>Hostel</h6></th>
																<th><h6>
																		Area (sq.mtr.)<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Own / Rented<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Total Rooms<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Capacity per Room<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Total Occupied Room<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Mess Facility<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Warden Facility<strong class="mandatory">*</strong>
																	</h6></th>
															</tr>
															<!-- end table row-->
														</thead>
														<tbody id="">
															<tr>
																<td>

																	<p>Boys</p>

																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="boys_area" id="boys_area"
																			class="form-control" placeholder="Area (sq.mtr.)" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="boys_own_rented"
																				id="boys_own_rented" class="form-control">

																				<option value="0" selected="selected">--
																					Select --</option>
																				<option value="1">Own</option>
																				<option value="2">Rented</option>
																			</select>
																		</div>
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="boys_room_no" id="boys_room_no"
																			class="form-control" placeholder="No. of Rooms" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="boys_capacity" id="boys_capacity"
																			class="form-control" placeholder="Capacity per Room" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="boys_occupied_room" id="boys_occupied_room"
																			class="form-control" placeholder="Occupied Room" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="boys_mess_facility" id="boys_mess_facility"
																				class="form-control">

																				<option value="0" selected="selected">--
																					Select --</option>
																				<option value="1">Yes</option>
																				<option value="2">No</option>
																			</select>
																		</div>
																	</div>
																</td>
																<td>
																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="boys_warden_facility" id="boys_warden_facility"
																				class="form-control">

																				<option value="0" selected="selected">--
																					Select --</option>
																				<option value="1">Yes</option>
																				<option value="2">No</option>
																			</select>
																		</div>
																	</div>
																</td>
															</tr>
															<tr>
																<td>
																	<p>Girls</p>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="girls_area" id="girls_area"
																			class="form-control" placeholder="Area (sq.mtr.)" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="girls_own_rented"
																				id="girls_own_rented" class="form-control">

																				<option value="0" selected="selected">--
																					Select --</option>
																				<option value="1">Own</option>
																				<option value="2">Rented</option>
																			</select>
																		</div>
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="girls_room_no" id="girls_room_no"
																			class="form-control" placeholder="No. of Rooms" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="girls_capacity" id="girls_capacity"
																			class="form-control" placeholder="Capacity per Room" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="girls_occupied_room" id="girls_occupied_room"
																			class="form-control" placeholder="Occupied Rooms" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="girls_mess_facility" id="girls_mess_facility"
																				class="form-control">
																				<option value="0" selected="selected">--
																					Select --</option>
																				<option value="1">Yes</option>
																				<option value="2">No</option>
																			</select>
																		</div>
																	</div>
																</td>
																<td>
																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="girls_warden_facility" id="girls_warden_facility"
																				class="form-control">
																				<option value="0" selected="selected">--
																					Select --</option>
																				<option value="1">Yes</option>
																				<option value="2">No</option>
																			</select>
																		</div>
																	</div>
																</td>
															</tr>
															<tr>
																<td>
																	<p>Guest</p>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="guest_area" id="guest_area"
																			class="form-control" placeholder="Area (sq.mtr.)" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="guest_own_rented"
																				id="guest_own_rented" class="form-control">

																				<option value="0" selected="selected">--
																					Select --</option>
																				<option value="1">Own</option>
																				<option value="2">Rented</option>
																			</select>
																		</div>
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="guest_room_no" id="guest_room_no"
																			class="form-control" placeholder="No. of Rooms" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="guest_capacity" id="guest_capacity"
																			class="form-control" placeholder="Capacity per Room" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="guest_occupied_room" id="guest_occupied_room"
																			class="form-control" placeholder="Occupied Rooms" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="guest_mess_facility" id="guest_mess_facility"
																				class="form-control">
																				<option value="0" selected="selected">--
																					Select --</option>
																				<option value="1">Yes</option>
																				<option value="2">No</option>
																			</select>
																		</div>
																	</div>
																</td>
																<td>
																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="guest_warden_facility" id="guest_warden_facility"
																				class="form-control">
																				<option value="0" selected="selected">--
																					Select --</option>
																				<option value="1">Yes</option>
																				<option value="2">No</option>
																			</select>
																		</div>
																	</div>
																</td>
															</tr>
															<!-- end table row -->
														</tbody>
													</table>
												</div>
												<!-- end card -->
											</div>
											<!-- end col -->
										</div>
										<!-- end row -->
									</div>
									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover btnsave" id="hostel_details_save"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>
							</div>
							<!-- Details Of Hostel End-->
							<!-- Mess Details Start-->
							<div class="tab" id="mess_facility_btn">
								<button class="tab-toggle">Details Of Mess</button>
							</div>
							<div class="content ">
								<h4 class="heading">Details Of Mess</h4>
								<form:form name="mess_details" id="mess_details" action="" method="post" class=""
									commandName="">
									<div class="row">
									
									<input type="hidden" id="hid_mess_details" name="hid_mess_details" value="0">
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="select-style-1">
												<label>Types of Kitchen<strong class="mandatory">*</strong></label>
												<div class="select-position">
													<select name="type_of_mess" id="type_of_mess"
														class="form-control">

														<option value="0" selected="selected">-- Select
															--</option>
														<option value="1">Straight</option>
														<option value="2">Parallel</option>
														<option value="3">L-Shaped</option>
														<option value="4">U-Shaped</option>
														<option value="5">Island</option>
														<option value="6">Peninsula</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Total area of Mess
													(in sq.mt)<strong class="mandatory">*</strong>
												</label> <input type="text" name="mess_total_area" id="mess_total_area"
													class="form-control"
													placeholder="Total area of mess" maxlength="10">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Total No. of Cooks<strong class="mandatory">*</strong>
												</label> <input type="text" name="mess_total_cooks" id="mess_total_cooks"
													class="form-control"
													placeholder="Total number of cooks" maxlength="10">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Capacity of Mess<strong class="mandatory">*</strong>
												</label> <input type="text" name="mess_total_capacity" id="mess_total_capacity"
													class="form-control"
													placeholder="Total Capacity" maxlength="10">
											</div>
										</div>
									</div>
									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover btnsave" id="mess_details_save"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>
							</div>
							<!-- Mess Details End-->
							<!-- Herbal Garden Start-->
							<div class="tab" id="herbal_garden_btn">
								<button class="tab-toggle">Herbal Garden</button>
							</div>
							<div class="content ">
								<h4 class="heading">Herbal Garden</h4>
								<form:form name="herbal_garden" id="herbal_garden" action="" method="post" class=""
									commandName="">
									<div class="row">
									
									<input type="hidden" id="hid_herbal_garden" name="hid_herbal_garden" value="0">
									
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Total area of the Herbal Garden
													(in sq.mt)<strong class="mandatory">*</strong>
												</label> <input type="text" name="total_area" id="total_area"
													class="form-control"
													placeholder="Total area of the Herbal Garden" maxlength="10">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Total cultivated species in the
													herbal garden<strong class="mandatory">*</strong>
												</label> <input type="text" name="total_cultivated_species" id="total_cultivated_species"
													class="form-control"
													placeholder="Total number of cultivated species" maxlength="10">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>List of plant species with names</label>
												<input type="file" id="plant_species_list" name="plant_species_list" class="form-control" accept=".pdf">
												<input type="hidden" id="hid_plant_species_list" name="hid_plant_species_list"
													class="form-control" value="">
												<div class="note-text">
													<span class="errorClass" id="plant_species_list_lbl">${doc_path_doc1_msg}</span>
													<span class='tikClass' id="plant_species_list_lbltik"></span>
												</div>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Photograph and list of herbal
													garden & medicinal plants with names</label> <input type="file"
													id="herbal_garden_list" name="herbal_garden_list" class="form-control" accept=".pdf">
													<input type="hidden" id="hid_herbal_garden_list" name="hid_herbal_garden_list"
													class="form-control" value="">
												<div class="note-text">
													<span class="errorClass" id="herbal_garden_list_lbl">${doc_path_doc1_msg}</span>
													<span class='tikClass' id="herbal_garden_list_lbltik"></span>
												</div>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="select-style-1">
												<label>Is irrigation facility available?<strong class="mandatory">*</strong></label>
												<div class="select-position">
													<select name="irrigation_facility" id="irrigation_facility"
														class="form-control">

														<option value="0" selected="selected">-- Select
															--</option>
														<option value="1">Yes</option>
														<option value="2">No</option>
													</select>
												</div>
											</div>
										</div>
									</div>
									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover btnsave" id="herbal_garden_save"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>
							</div>
							<!-- Herbal Garden End-->
							<!-- Additional Information Start-->
							<div class="tab" id="additional_information_btn">
								<button class="tab-toggle">Additional Information</button>
							</div>
							<div class="content ">
								<h4 class="heading">Additional Information</h4>
								<form:form name="additional_information" id="additional_information" action="" method="post" class=""
									commandName="">
									<div class="row">
									
									<input type="hidden" id="hid_additional_information" name="hid_additional_information" value="0">
									
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<label>Is transport Facility available?<strong
												class="mandatory">*</strong>
											</label>
											<div class="input-style-form-check">

												<div class="form-check radio-style">
													<input type="radio" id="trasport_facility_yes" name="trasport_facility"
														class="form-check-input" value="1"> <label
														for="transportYes" class="form-check-label">Yes</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" id="trasport_facility_no" name="trasport_facility"
														class="form-check-input" value="2"> <label
														for="transportNo" class="form-check-label">No</label>
												</div>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<label>Is sports & games Facility
												available?<strong class="mandatory">*</strong>
											</label>
											<div class="input-style-form-check">

												<div class="form-check radio-style">
													<input type="radio" id="sports_facility_yes" name="sports_facility"
														class="form-check-input" value="1"> <label
														for="sportsYes" class="form-check-label">Yes</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" id="sports_facility_no" name="sports_facility"
														class="form-check-input" value="2"> <label
														for="sportsNo" class="form-check-label">No</label>
												</div>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<label>Is there any due for previous
												visitation/ inspection pending for payment from the college?<strong
												class="mandatory">*</strong>
											</label>
											<div class="input-style-form-check">

												<div class="form-check radio-style">
													<input type="radio" id="inspection_pending_yes" name="inspection_pending"
														class="form-check-input" value="1"> <label
														for="visitationYes" class="form-check-label">Yes</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" id="inspection_pending_no" name="inspection_pending"
														class="form-check-input" value="2"> <label
														for="visitationNo" class="form-check-label">No</label>
												</div>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<label>Is there any penalty amount due
												from the college for payment?<strong class="mandatory">*</strong></label>
											<div class="input-style-form-check">

												<div class="form-check radio-style">
													<input type="radio" id="penalty_amount_yes" name="penalty_amount"
														class="form-check-input" value="1"> <label
														for="paymentYes" class="form-check-label">Yes</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" id="penalty_amount_no" name="penalty_amount"
														class="form-check-input" value="2"> <label
														for="paymentNo" class="form-check-label">No</label>
												</div>

											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<label>Has the college implemented
												Swasthya Rakshan programme by adopting 5 villages/colonies?<strong class="mandatory">*</strong></label>
											<div class="input-style-form-check">

												<div class="form-check radio-style">
													<input type="radio" id="swasthya_rakshan_programme_yes" name="swasthya_rakshan_programme"
														class="form-check-input" value="1"> <label
														for="programmeYes" class="form-check-label">Yes</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" id="swasthya_rakshan_programme_no" name="swasthya_rakshan_programme"
														class="form-check-input" value="2"> <label
														for="programmeNo" class="form-check-label">No</label>
												</div>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<label>Has the compliance report of the
												shortcomings been submitted to Council?<strong class="mandatory">*</strong></label>
											<div class="input-style-form-check">
												<div class="form-check radio-style">
													<input type="radio" id="compliance_report_yes" name="compliance_report"
														class="form-check-input" value="1"> <label
														for="complianceYes" class="form-check-label">Yes</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" id="compliance_report_no" name="compliance_report"
														class="form-check-input" value="2"> <label
														for="complianceNo" class="form-check-label">No</label>
												</div>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<label>Is Ambulance Facility
												available?<strong class="mandatory">*</strong></label>
											<div class="input-style-form-check">
												<div class="form-check radio-style">
													<input type="radio" id="ambulance_facility_yes" name="ambulance_facility"
														class="form-check-input" value="1"> <label
														for="complianceYes" class="form-check-label">Yes</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" id="ambulance_facility_no" name="ambulance_facility"
														class="form-check-input" value="2"> <label
														for="complianceNo" class="form-check-label">No</label>
												</div>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<label>Is Compliance Report
												available?<strong class="mandatory">*</strong></label>
											<div class="input-style-form-check">
												<div class="form-check radio-style">
													<input type="radio" id="compliance_report_check_yes" name="compliance_report_check"
														class="form-check-input" value="1"> <label
														for="complianceYes" class="form-check-label">Yes</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" id="compliance_report_check_no" name="compliance_report_check"
														class="form-check-input" value="2"> <label
														for="complianceNo" class="form-check-label">No</label>
												</div>
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="compliance_report_check_id">
											<div class="input-style-1">
												<label>Upload Compliance Report<strong class="mandatory">*</strong></label> <input type="file"
													id="compliance_report_doc" name="compliance_report_doc" class="form-control" accept=".pdf">
													<input type="hidden" id="hid_compliance_report_doc" name="hid_compliance_report_doc"
													class="form-control" value="">
												<div class="note-text">
													<span class="errorClass" id="compliance_report_doc_lbl">${doc_path_doc1_msg}</span>
													<span class='tikClass' id="compliance_report_doc_lbltik"></span>
												</div>
											</div>
										</div>
									</div>

									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn success-btn btn-hover btnsubmit" id="additional_information_details_save"
														type="button" value="Submit"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>
							</div>
							<!-- Additional Information End-->
						</div>
					</div>
				</div>
				<!-- ===========================
							College Infrastructure End
						=========================== -->
			</div>
		</div>
		<!-- end row -->
		</div>
		<!-- end container -->
	</section>
	<!-- regulation components end -->
	<c:url value="View_Search_College_InfrastructureUrl" var="viewUrl" />
	<form:form action="${viewUrl}" method="post" id="viewForm"
		name="viewForm" modelAttribute="college_infra_view_id">
		<input type="hidden" name="college_infra_id" id="college_infra_view_id" value="0" />
<!-- 		<input type="hidden" name="statusview" id="statusview" value="0" /> -->
	</form:form>
	<script type="text/javascript"
		src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>
</body>
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
			datepicketDate('website_update_date');		
		}
		
		$("#computers_facility_no").prop("checked", true);
		$("#cataloguing_books_no").prop("checked", true);
		$("#elibrary_check_no").prop("checked", true);
		$("#basic_info_id").val('${basic_info_id}');
		
		
	//GET COLLEGE COUNCIL DETAILS
	var getCollege_Council_Details ='${getCollege_Council_Details}';
	if(getCollege_Council_Details != "[]"){
		$("#hid_college_council").val('${getCollege_Council_Details[0][0]}');
		$("#login_url").val('${getCollege_Council_Details[0][6]}');
		$("#username_ci").val('${getCollege_Council_Details[0][7]}');
		$("#password_ci").val('${getCollege_Council_Details[0][8]}');
		$("#college_working_hours").val('${getCollege_Council_Details[0][10]}');
		$("#hid_council_document").val('${getCollege_Council_Details[0][2]}');
		$("#website_update_date").val('${getCollege_Council_Details[0][4]}');
		
		var council_check = '${getCollege_Council_Details[0][1]}';
		$("input[name='council_check'][value='"+council_check+"']").prop('checked', true);
		if(council_check == "1"){
			 $("div#hidden_council_documnet").show();
		}else{
			 $("div#hidden_council_documnet").hide();
		}
		
		var college_website = '${getCollege_Council_Details[0][3]}';
		$("input[name='college_website'][value='"+college_website+"']").prop('checked', true);
		
		var cctv_status = '${getCollege_Council_Details[0][5]}';
		$("input[name='cctv_status'][value='"+cctv_status+"']").prop('checked', true);
		if(cctv_status == "1"){
			 $("div#camera_location_id").show();
		}else{
			 $("div#camera_location_id").hide();
		}
		
		var biometric_status = '${getCollege_Council_Details[0][9]}';
		$("input[name='biometric_status'][value='"+biometric_status+"']").prop('checked', true);
	}
	
	//GET PROGRASS OF INSTITUTION DETAILS
	var GetProgress_of_Institution_Details ='${GetProgress_of_Institution_Details}';
	if(GetProgress_of_Institution_Details != "[]"){
		$("#hid_progress_institution").val('${GetProgress_of_Institution_Details[0].id}');
		$("#cons_clg_hospital").val('${GetProgress_of_Institution_Details[0].cons_clg_hospital}');
		$("#app_teaching_staff").val('${GetProgress_of_Institution_Details[0].app_teaching_staff}');
		$("#app_non_teaching_staff").val('${GetProgress_of_Institution_Details[0].app_non_teaching_staff}');
		$("#app_paramedical").val('${GetProgress_of_Institution_Details[0].app_paramedical}');
		$("#expansion_various_dept").val('${GetProgress_of_Institution_Details[0].expansion_various_dept}');
		$("#expansion_herbal_ganden").val('${GetProgress_of_Institution_Details[0].expansion_herbal_ganden}');
		$("#hospital_opd").val('${GetProgress_of_Institution_Details[0].hospital_opd}');
		$("#hospital_ipd").val('${GetProgress_of_Institution_Details[0].hospital_ipd}');
		$("#seminars").val('${GetProgress_of_Institution_Details[0].seminars}');
		$("#pulication_by_clg").val('${GetProgress_of_Institution_Details[0].pulication_by_clg}');
		$("#research_activities").val('${GetProgress_of_Institution_Details[0].research_activities}');
		$("#award_details").val('${GetProgress_of_Institution_Details[0].award_details}');
	}
	
	
	//GET CONSTRUCTED AREA DETAILS
	var getConstructed_Area_Details ='${getConstructed_Area_Details}';
	if(getConstructed_Area_Details != "[]"){
		$("#hid_constructed_area").val('${getConstructed_Area_Details[0].id}');
		$("#college_const").val('${getConstructed_Area_Details[0].college_const}');
		$("#lecturer_hall").val('${getConstructed_Area_Details[0].lecturer_hall}');
		$("#exam_hall").val('${getConstructed_Area_Details[0].exam_hall}');
		$("#central_library").val('${getConstructed_Area_Details[0].central_library}');
		$("#boys_common_room").val('${getConstructed_Area_Details[0].boys_common_room}');
		$("#girl_common_room").val('${getConstructed_Area_Details[0].girl_common_room}');
		$("#canteen").val('${getConstructed_Area_Details[0].canteen}');
		$("#administrative_section").val('${getConstructed_Area_Details[0].administrative_section}');
		$("#total_area_of_college").val('${getConstructed_Area_Details[0].total_area_of_college}');
		$("#total_lecture_hall").val('${getConstructed_Area_Details[0].total_lecture_hall}');
	}
	
	//GET CENTRAL LIBRARY DETAILS
	var getCentral_Library_Details ='${getCentral_Library_Details}';
	if(getCentral_Library_Details != "[]"){
		$("#hid_central_library").val('${getCentral_Library_Details[0].id}');
		$("#text_book").val('${getCentral_Library_Details[0].text_book}');
		$("#regerence_book").val('${getCentral_Library_Details[0].regerence_book}');
		$("#govn_publication").val('${getCentral_Library_Details[0].govn_publication}');
		$("#new_addition").val('${getCentral_Library_Details[0].new_addition}');
		$("#book_bank").val('${getCentral_Library_Details[0].book_bank}');
		$("#total_book_no").val('${getCentral_Library_Details[0].total_book_no}');
		$("#subscribed_no").val('${getCentral_Library_Details[0].subscribed_no}');
		$("#complementary_no").val('${getCentral_Library_Details[0].complementary_no}');
		$("#news_paper_no").val('${getCentral_Library_Details[0].news_paper_no}');
		$("#back_issue_no").val('${getCentral_Library_Details[0].back_issue_no}');
		$("#library_working_hours").val('${getCentral_Library_Details[0].library_working_hours}');
		$("#student_reading_room_capacity").val('${getCentral_Library_Details[0].student_reading_room_capacity}');
		$("#faculty_reading_room_capacity").val('${getCentral_Library_Details[0].faculty_reading_room_capacity}');
		$("#rading_room_purpose").val('${getCentral_Library_Details[0].rading_room_purpose}');
		$("#home_lending").val('${getCentral_Library_Details[0].home_lending}');
		$("#photocopying_facility").val('${getCentral_Library_Details[0].photocopying_facility}');
		$("#cataloguing_system").val('${getCentral_Library_Details[0].cataloguing_system}');
		$("#librarian_name").val('${getCentral_Library_Details[0].librarian_name}');
		$("#librarian_qualification").val('${getCentral_Library_Details[0].librarian_qualification}');
		$("#other_information").val('${getCentral_Library_Details[0].other_information}');
		
		var computers_facility = '${getCentral_Library_Details[0].computers_facility}';
		$("input[name='computers_facility'][value='"+computers_facility+"']").prop('checked', true);
		
		var cataloguing_books = '${getCentral_Library_Details[0].cataloguing_books}';
		$("input[name='cataloguing_books'][value='"+cataloguing_books+"']").prop('checked', true);
		if(cataloguing_books == "1"){
			 $("div#hidden_cataloguing_books").show();
		}else{
			 $("div#hidden_cataloguing_books").hide();
		}
		
		var elibrary_check = '${getCentral_Library_Details[0].elibrary_check}';
		$("input[name='elibrary_check'][value='"+elibrary_check+"']").prop('checked', true);
		if(elibrary_check == "1"){
	        $("div#hid_total_computers").show();
	        $("div#hid_total_subscription").show();
	        $("div#hid_total_ebooks").show();
		}
		else{
			$("div#hid_total_computers").hide();
	        $("div#hid_total_subscription").hide();
	        $("div#hid_total_ebooks").hide();
		}
		
		$("#total_computers").val('${getCentral_Library_Details[0].total_computers}');
		$("#total_subscription").val('${getCentral_Library_Details[0].total_subscription}');
		$("#total_ebooks").val('${getCentral_Library_Details[0].total_ebooks}');
	}
	
	//GET HOSTEL DETAILS
	var getHostelDetails ='${getHostelDetails}';
	if(getHostelDetails != "[]"){
		$("#hid_hostel_facility").val('${getHostelDetails[0].id}');
		$("#boys_area").val('${getHostelDetails[0].boys_area}');
		$("#boys_own_rented").val('${getHostelDetails[0].boys_own_rented}');
		$("#boys_room_no").val('${getHostelDetails[0].boys_room_no}');
		$("#boys_capacity").val('${getHostelDetails[0].boys_capacity}');
		$("#boys_mess_facility").val('${getHostelDetails[0].boys_mess_facility}');
		$("#boys_warden_facility").val('${getHostelDetails[0].boys_warden_facility}');
		$("#girls_area").val('${getHostelDetails[0].girls_area}');
		$("#girls_own_rented").val('${getHostelDetails[0].girls_own_rented}');
		$("#girls_room_no").val('${getHostelDetails[0].girls_room_no}');
		$("#girls_capacity").val('${getHostelDetails[0].girls_capacity}');
		$("#girls_mess_facility").val('${getHostelDetails[0].girls_mess_facility}');
		$("#girls_warden_facility").val('${getHostelDetails[0].girls_warden_facility}');
		$("#guest_area").val('${getHostelDetails[0].guest_area}');
		$("#guest_own_rented").val('${getHostelDetails[0].guest_own_rented}');
		$("#guest_room_no").val('${getHostelDetails[0].guest_room_no}');
		$("#guest_capacity").val('${getHostelDetails[0].guest_capacity}');
		$("#guest_mess_facility").val('${getHostelDetails[0].guest_mess_facility}');
		$("#guest_warden_facility").val('${getHostelDetails[0].guest_warden_facility}');
		$("#boys_occupied_room").val('${getHostelDetails[0].boys_occupied_room}');
		$("#girls_occupied_room").val('${getHostelDetails[0].girls_occupied_room}');
		$("#guest_occupied_room").val('${getHostelDetails[0].guest_occupied_room}');
	}
	
	//GET MESS DETAILS
	var getMessDetails ='${getMessDetails}';
	if(getMessDetails != "[]"){
		$("#hid_mess_details").val('${getMessDetails[0].id}');
		$("#type_of_mess").val('${getMessDetails[0].type_of_mess}');
		$("#mess_total_area").val('${getMessDetails[0].mess_total_area}');
		$("#mess_total_cooks").val('${getMessDetails[0].mess_total_cooks}');
		$("#mess_total_capacity").val('${getMessDetails[0].mess_total_capacity}');
	}
	
	//GET HERBAL GARDEN DETAILS
	var getHerbal_Garden_Details ='${getHerbal_Garden_Details}';
	if(getHerbal_Garden_Details != "[]"){
		$("#hid_herbal_garden").val('${getHerbal_Garden_Details[0].id}');
		$("#total_area").val('${getHerbal_Garden_Details[0].total_area}');
		$("#total_cultivated_species").val('${getHerbal_Garden_Details[0].total_cultivated_species}');
		$("#hid_plant_species_list").val('${getHerbal_Garden_Details[0].plant_species_list}');
		$("#hid_herbal_garden_list").val('${getHerbal_Garden_Details[0].herbal_garden_list}');
		$("#irrigation_facility").val('${getHerbal_Garden_Details[0].irrigation_facility}');
	}
	
	//GET ADDITIONAL INFORMATION DETAILS
	var getAdd_Info_Details ='${getAdd_Info_Details}';
	if(getAdd_Info_Details != "[]"){
		$("#hid_additional_information").val('${getAdd_Info_Details[0].id}');
		
		var trasport_facility = '${getAdd_Info_Details[0].trasport_facility}';
		$("input[name='trasport_facility'][value='"+trasport_facility+"']").prop('checked', true);
		
		var sports_facility = '${getAdd_Info_Details[0].sports_facility}';
		$("input[name='sports_facility'][value='"+sports_facility+"']").prop('checked', true);
		
		var inspection_pending = '${getAdd_Info_Details[0].inspection_pending}';
		$("input[name='inspection_pending'][value='"+inspection_pending+"']").prop('checked', true);
		
		var penalty_amount = '${getAdd_Info_Details[0].penalty_amount}';
		$("input[name='penalty_amount'][value='"+penalty_amount+"']").prop('checked', true);
		
		var swasthya_rakshan_programme = '${getAdd_Info_Details[0].swasthya_rakshan_programme}';
		$("input[name='swasthya_rakshan_programme'][value='"+swasthya_rakshan_programme+"']").prop('checked', true);
		
		var compliance_report = '${getAdd_Info_Details[0].compliance_report}';
		$("input[name='compliance_report'][value='"+compliance_report+"']").prop('checked', true);
		
		var ambulance_facility = '${getAdd_Info_Details[0].ambulance_facility}';
		$("input[name='ambulance_facility'][value='"+ambulance_facility+"']").prop('checked', true);
		
		var compliance_report_check = '${getAdd_Info_Details[0].compliance_report_check}';
		$("input[name='compliance_report_check'][value='"+compliance_report_check+"']").prop('checked', true);
		if(compliance_report_check == "1"){
			$("div#compliance_report_check_id").show();
		}else{
			$("div#compliance_report_check_id").hide();
		}
		
		$("#hid_compliance_report_doc").val('${getAdd_Info_Details[0].compliance_report_doc}');
	}
	
	
		
		getCamera_Details();
		getConstructed_Area_Department_Details();
		getLibrarian_Details();
		
		
// 	 	var hid_college_council = $("#hid_college_council").val();
// 	 	if(hid_college_council == 0){
// 	 		$("#progress_institution_btn").hide();
// 	 		$("#constructed_area_btn").hide();
// 	 		$("#central_library_form_btn").hide();
// 	 		$("#hostel_facility_btn").hide();
// 	 		$("#mess_facility_btn").hide();
// 	 		$("#herbal_garden_btn").hide();
// 	 		$("#additional_information_btn").hide();
// 	 	}else{
// 	 	 	$("#progress_institution_btn").show();
// 	 	 	$("#constructed_area_btn").show();
// 	 	 	$("#central_library_form_btn").show();
// 	 	 	$("#hostel_facility_btn").show();
// 	 	 	$("#mess_facility_btn").show();
// 	 	 	$("#herbal_garden_btn").show();
// 	 	 	$("#additional_information_btn").show();	
// 	 	}
		
	});
	

	
	
	function Coincil_Yes(){
		
		var council_check = $('input:radio[name=council_check]:checked').val();
		if(council_check == "1"){
	        $("div#hidden_council_documnet").show();
		}
		else if(council_check == "2"){
	        $("div#hidden_council_documnet").hide();
		}
	}
	function Cataloguing_Books_Yes(){
		
		var cataloguing_books = $('input:radio[name=cataloguing_books]:checked').val();
		if(cataloguing_books == "1"){
	        $("div#hidden_cataloguing_books").show();
		}
		else if(cataloguing_books == "2"){
	        $("div#hidden_cataloguing_books").hide();
		}
	}
	function Cctv_Status_Yes(){
		
		var cctv_status = $('input:radio[name=cctv_status]:checked').val();
		if(cctv_status == "1"){
	        $("div#camera_location_id").show();
		}
		else if(cctv_status == "2"){
	        $("div#camera_location_id").hide();
		}
	}
	function Compliance_Report_Yes(){
		
		var compliance_report_check = $('input:radio[name=compliance_report_check]:checked').val();
		if(compliance_report_check == "1"){
	        $("div#compliance_report_check_id").show();
		}
		else if(compliance_report_check == "2"){
	        $("div#compliance_report_check_id").hide();
		}
	}
	function Elibrary_Status_Yes(){
		
		var elibrary_check = $('input:radio[name=elibrary_check]:checked').val();
		if(elibrary_check == "1"){
	        $("div#hid_total_computers").show();
	        $("div#hid_total_subscription").show();
	        $("div#hid_total_ebooks").show();
		}
		else if(elibrary_check == "2"){
			$("div#hid_total_computers").hide();
	        $("div#hid_total_subscription").hide();
	        $("div#hid_total_ebooks").hide();
		}
	}
	
	
// 	--------------------------------------------START-->COLLEGE COUNCIL------------------------------------------
	
	//SAVE COLLEGE COUNCIL DETAILS
	function Save_As_Draft_College_Council_Details(sd) {
		$.ajaxSetup({
		    async: false
		});
		
		var basic_info_id = $("#basic_info_id").val();
		if(basic_info_id == "0"){
			if(sd != -1){
			alert("Please Save Basic details First");
			}
			return false;
		}
		var hid_college_council = $("#hid_college_council").val();
		if(hid_college_council == "0"){
			if(sd != -1){
			alert("Please Save College Council and Website Details First");
			}
			return false;
		}
		
		var council_check = $("input[name='council_check']:checked").val();
		if( council_check == null ){
			alert("Please Select College Council Available");
			return false;
	   	}
		if(council_check == "1"){
			var hid_biometricattendance = CheckNullorBlank('hid_council_document');
			if(hid_biometricattendance !== "true"){
				var res = CheckNullorBlank('council_document');
				if (res !== "true") {
					alert("Please Upload Document of College Council");
					$('#council_document').focus();
					return false;
				}
			}
		}
		var college_website = $("input[name='college_website']:checked").val();
		if( college_website == null ){
			alert("Please Select College Website");
			return false;
	   	}
		var res = CheckNullorBlank('website_update_date');
		if (res !== "true") {
			alert(res +"Website Last Update Date");
			$('#website_update_date').focus();
			return false;
		}
		var cctv_status = $("input[name='cctv_status']:checked").val();
		if( cctv_status == null ){
			alert("Please Select CCTV Check");
			return false;
	   	}
		var res = CheckNullorBlank('login_url');
		if (res !== "true") {
			alert(res +"Login Url");
			$('#login_url').focus();
			return false;
		}
		var res = CheckNullorBlank('username_ci');
		if (res !== "true") {
			alert(res +"Username");
			$('#username_ci').focus();
			return false;
		}
		var res = CheckNullorBlank('password_ci');
		if (res !== "true") {
			alert(res +"Password");
			$('#password_ci').focus();
			return false;
		}
		var biometric_status = $("input[name='biometric_status']:checked").val();
		if( biometric_status == null ){
			alert("Please Select Biometric Available");
			return false;
	   	}
		var res = CheckNullorBlank('college_working_hours');
		if (res !== "true") {
			alert(res +"College Working Hours");
			$('#college_working_hours').focus();
			return false;
		}
// 		if(cctv_status == "1"){
// 			var camera_count = getCollege_Council_Camera_Details();
// 			if(camera_count != true){
// 			alert("Kindly upload photographs of 5 locations");
// 			return false;
// 		}
// 		}
		
	 	
// 			var form = $("#college_council").serialize();
			var form_data = new FormData(document.getElementById("college_council"));
			$.ajax({
				url : 'College_Council_Details_Save_Draft_Action?_csrf=' + value,
				type : "POST",
				data : form_data,
				enctype : 'multipart/form-data',
				processData : false,
				contentType : false
			}).done(function(j){
	          if(j>0){
	        		$("#hid_college_council").val(j);
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
	
	//FETCH COLLEGE COUNCIL CAMERA DETAILS
	function getCollege_Council_Camera_Details() {
		$.ajaxSetup({
			async : false
		});
		
		$.post("getCamera_Location_Details?" + key + "=" + value, {
			
		}, function(j) {
			$("#camera_count").val(j.length);
			
		});
		
		var intern_count = $("#camera_count").val();
		if(intern_count > 4){
			return true;
		}
		else{
			return false;
		}
	}
// 	--------------------------------------------END-->COLLEGE COUNCIL------------------------------------------
	
// 	--------------------------------------------START-->PROGRESS OF INSTITUTION------------------------------------------
	
	//SAVE PROGRESS OF INSTITUTION DETAILS
	function Save_As_Draft_Progress_Institution_Details(sd) {
		
		$.ajaxSetup({
		    async: false
		});	
		var hid_college_council = $("#hid_college_council").val();
		if(hid_college_council == "0"){
			if(sd != -1){
			alert("Please Save College Council and Website Details First");
			}
			return false;
		}
// 		var res = CheckNullorBlank('cons_clg_hospital');
// 		if (res !== "true") {
// 			alert(res +"Construction of College & Hospital Building");
// 			$('#cons_clg_hospital').focus();
// 			return false;
// 		}
// 		var res = CheckNullorBlank('app_teaching_staff');
// 		if (res !== "true") {
// 			alert(res +"Appointment of Teaching Staff");
// 			$('#app_teaching_staff').focus();
// 			return false;
// 		}
// 		var res = CheckNullorBlank('app_non_teaching_staff');
// 		if (res !== "true") {
// 			alert(res +"Appointment of Non-Teaching Staff");
// 			$('#app_non_teaching_staff').focus();
// 			return false;
// 		}
// 		var res = CheckNullorBlank('app_paramedical');
// 		if (res !== "true") {
// 			alert(res +"Appointment of Paramedical and other Hospital Staff");
// 			$('#app_paramedical').focus();
// 			return false;
// 		}
// 		var res = CheckNullorBlank('expansion_various_dept');
// 		if (res !== "true") {
// 			alert(res +"Expansion of various departments of college");
// 			$('#expansion_various_dept').focus();
// 			return false;
// 		}
// 		var res = CheckNullorBlank('expansion_herbal_ganden');
// 		if (res !== "true") {
// 			alert(res +"Expansion of Herbal Garden");
// 			$('#expansion_herbal_ganden').focus();
// 			return false;
// 		}
// 		var res = CheckNullorBlank('hospital_opd');
// 		if (res !== "true") {
// 			alert(res +"Hospital OPD");
// 			$('#hospital_opd').focus();
// 			return false;
// 		}
// 		var res = CheckNullorBlank('hospital_ipd');
// 		if (res !== "true") {
// 			alert(res +"Hospital IPD");
// 			$('#hospital_ipd').focus();
// 			return false;
// 		}
// 		var res = CheckNullorBlank('seminars');
// 		if (res !== "true") {
// 			alert(res +"Any National/International/State Level Seminars");
// 			$('#seminars').focus();
// 			return false;
// 		}
// 		var res = CheckNullorBlank('pulication_by_clg');
// 		if (res !== "true") {
// 			alert(res +"Publication by College and Teaching Staff");
// 			$('#pulication_by_clg').focus();
// 			return false;
// 		}
// 		var res = CheckNullorBlank('research_activities');
// 		if (res !== "true") {
// 			alert(res +"Research Activities");
// 			$('#research_activities').focus();
// 			return false;
// 		}
// 		var res = CheckNullorBlank('award_details');
// 		if (res !== "true") {
// 			alert(res +"Awards Won by Teaching Staffs and Students");
// 			$('#award_details').focus();
// 			return false;
// 		}
		
		var form = $("#progress_institution").serialize();
		$.post(
				'Progress_Institution_Details_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        		$("#hid_progress_institution").val(j);
		        		if(sd != -1){
		        	  alert("Data Save Sucessfully");
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
	
// 	--------------------------------------------END-->PROGRESS OF INSTITUTION------------------------------------------

// 	--------------------------------------------START-->CONSTRUCTED AREA---------------------------------------------
	
	//SAVE CONSTRUCTED AREA DETAILS
	function Save_As_Draft_Constructed_Area_Details(sd) {
		
		$.ajaxSetup({
		    async: false
		});	
		var hid_college_council = $("#hid_college_council").val();
		if(hid_college_council == "0"){
			if(sd != -1){
			alert("Please Save College Council and Website Details First");
			}
			return false;
		}
		var res = CheckNullorBlankZero('college_const');
		if (res !== "true") {
			alert(res +"Construction of College");
			$('#college_const').focus();
			return false;
		}
		var res = CheckNullorBlankZero('lecturer_hall');
		if (res !== "true") {
			alert(res +"Total Area of Lecturer Halls");
			$('#lecturer_hall').focus();
			return false;
		}
		var res = CheckNullorBlankZero('exam_hall');
		if (res !== "true") {
			alert(res +"Auditorium/Seminar Hall/Exam hall");
			$('#exam_hall').focus();
			return false;
		}
		var res = CheckNullorBlankZero('central_library');
		if (res !== "true") {
			alert(res +"Central Library");
			$('#central_library').focus();
			return false;
		}
		var res = CheckNullorBlankZero('boys_common_room');
		if (res !== "true") {
			alert(res +"Common Rooms for Boys");
			$('#boys_common_room').focus();
			return false;
		}
		var res = CheckNullorBlankZero('girl_common_room');
		if (res !== "true") {
			alert(res +"Common Rooms for Girls");
			$('#girl_common_room').focus();
			return false;
		}
		var res = CheckNullorBlankZero('canteen');
		if (res !== "true") {
			alert(res +"Canteen");
			$('#canteen').focus();
			return false;
		}
		var res = CheckNullorBlankZero('administrative_section');
		if (res !== "true") {
			alert(res +"Administrative section");
			$('#administrative_section').focus();
			return false;
		}
		
		<c:forEach var="j" items="${getDepartmentList}" varStatus="num">
		
		var res = CheckNullorBlankZero('area_of_department_'+${j[0]});
		if (res !== "true") {
			alert(res +"Area of ${j[1]}");
			$('#area_of_department_'+${j[0]}).focus();
			return false;
		}
		
		</c:forEach>
		
		
		var res = CheckNullorBlankZero('total_area_of_college');
		if (res !== "true") {
			alert(res +"Total Constructed Area of College");
			$('#total_area_of_college').focus();
			return false;
		}
		var res = CheckNullorBlankZero('total_lecture_hall');
		if (res !== "true") {
			alert(res +"Total Lecture Halls");
			$('#total_lecture_hall').focus();
			return false;
		}
		
		var form = $("#constructed_area").serialize();
		console.log(form);
		$.post(
				'Constructed_Area_Details_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        		$("#hid_constructed_area").val(j);
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
	
	//FETCH CONSTRUCTED AREA DEPARTMENT DETAILS
	function getConstructed_Area_Department_Details() {
		$.ajaxSetup({
			async : false
		});
		
		$.post("getConstructed_Area_Department_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
				$("#hid_department_area_"+j[i].department_id).val(j[i].id);
				$("#area_of_department_"+j[i].department_id).val(j[i].area_of_department);
				
			}
			
		});
	}
	
	function Total_Constructed_Area(){
		
		var Grand_Total = 0;
		const collection = document.getElementsByClassName("grand");
		for (let i = 0; i < collection.length; i++) {
		  if(collection[i].value!=''){
			  Grand_Total=Grand_Total+ parseInt(collection[i].value)
		  }
		}
		$("#total_area_of_college").val(Grand_Total);
		
	}
	
// 	--------------------------------------------END-->CONSTRUCTED AREA---------------------------------------------

// 	--------------------------------------------START-->CENTRAL LIBRARY---------------------------------------------
	
	//SAVE CENTRAL LIBRARY DETAILS
	function Save_As_Draft_Central_Library_Details(sd) {
		
		$.ajaxSetup({
		    async: false
		});	
		var hid_college_council = $("#hid_college_council").val();
		if(hid_college_council == "0"){
			if(sd != -1){
			alert("Please Save College Council and Website Details First");
			}
			return false;
		}
		var res = CheckNullorBlank('text_book');
		if (res !== "true") {
			alert(res +"Text Book");
			$('#text_book').focus();
			return false;
		}
		var res = CheckNullorBlank('regerence_book');
		if (res !== "true") {
			alert(res +"Reference Book");
			$('#regerence_book').focus();
			return false;
		}
		var res = CheckNullorBlank('govn_publication');
		if (res !== "true") {
			alert(res +"Government Publications");
			$('#govn_publication').focus();
			return false;
		}
		var res = CheckNullorBlank('new_addition');
		if (res !== "true") {
			alert(res +"New Addition");
			$('#new_addition').focus();
			return false;
		}
		var res = CheckNullorBlank('book_bank');
		if (res !== "true") {
			alert(res +"Book Bank");
			$('#book_bank').focus();
			return false;
		}
		var res = CheckNullorBlank('total_book_no');
		if (res !== "true") {
			alert(res +"Total Number of Books");
			$('#total_book_no').focus();
			return false;
		}
		var res = CheckNullorBlankZero('subscribed_no');
		if (res !== "true") {
			alert(res +"Number of Subscribed Journal");
			$('#subscribed_no').focus();
			return false;
		}
		var res = CheckNullorBlankZero('complementary_no');
		if (res !== "true") {
			alert(res +"Number of Complementary Journal");
			$('#complementary_no').focus();
			return false;
		}
		var res = CheckNullorBlankZero('news_paper_no');
		if (res !== "true") {
			alert(res +"Number of News Paper");
			$('#news_paper_no').focus();
			return false;
		}
		var res = CheckNullorBlankZero('back_issue_no');
		if (res !== "true") {
			alert(res +"Number of Back Issue Journal");
			$('#back_issue_no').focus();
			return false;
		}
		var res = CheckNullorBlank('library_working_hours');
		if (res !== "true") {
			alert(res +"Working Hours");
			$('#library_working_hours').focus();
			return false;
		}
		var res = CheckNullorBlank('student_reading_room_capacity');
		if (res !== "true") {
			alert(res +"Reading Room Capacity for Students");
			$('#student_reading_room_capacity').focus();
			return false;
		}
		var res = CheckNullorBlank('faculty_reading_room_capacity');
		if (res !== "true") {
			alert(res +"Reading Room Capacity for Faculty");
			$('#faculty_reading_room_capacity').focus();
			return false;
		}
		var res = CheckNullorBlank('rading_room_purpose');
		if (res !== "true") {
			alert(res +"Reading Room Purpose");
			$('#rading_room_purpose').focus();
			return false;
		}
		var res = CheckNullorBlank('home_lending');
		if (res !== "true") {
			alert(res +"Home Lending");
			$('#home_lending').focus();
			return false;
		}
		var res = CheckNullorBlank('photocopying_facility');
		if (res !== "true") {
			alert(res +"Photocopying Facility");
			$('#photocopying_facility').focus();
			return false;
		}
		var computers_facility = $("input[name='computers_facility']:checked").val();
		if( computers_facility == null ){
			alert("Please Select Computers with Internet Facility");
			return false;
	   	}
		var cataloguing_books = $("input[name='cataloguing_books']:checked").val();
		if( cataloguing_books == null ){
			alert("Please Select Cataloguing of Books");
			return false;
	   	}
		
		if(cataloguing_books == "1"){
		var res = CheckNullorBlank('cataloguing_system');
		if (res !== "true") {
			alert(res +"System of Cataloguing");
			$('#cataloguing_system').focus();
			return false;
		}
		}
		var res = CheckNullorBlank('librarian_name');
		if (res !== "true") {
			alert(res +"Name of Librarian");
			$('#librarian_name').focus();
			return false;
		}
		var res = CheckNullorBlank('librarian_qualification');
		if (res !== "true") {
			alert(res +"Qualification of Librarian");
			$('#librarian_qualification').focus();
			return false;
		}
		var elibrary_check = $("input[name='elibrary_check']:checked").val();
		if( elibrary_check == null ){
			alert("Please Select E-Library Facility");
			return false;
	   	}
		if(elibrary_check == "1"){
			var res = CheckNullorBlankZero('total_computers');
			if (res !== "true") {
				alert(res +"Total Number of Computers");
				$('#total_computers').focus();
				return false;
			}
			var res = CheckNullorBlankZero('total_subscription');
			if (res !== "true") {
				alert(res +"Total Number of Subscription");
				$('#total_subscription').focus();
				return false;
			}
			var res = CheckNullorBlankZero('total_ebooks');
			if (res !== "true") {
				alert(res +"Total Number of E-Books");
				$('#total_ebooks').focus();
				return false;
			}
		}
		
		var form = $("#central_library_form").serialize();
		console.log(form);
		$.post(
				'Central_Library_Details_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        		$("#hid_central_library").val(j);
		        		if(sd != -1){
		        	  alert("Data Save Sucessfully");
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
	
// 	--------------------------------------------END-->CENTRAL LIBRARY---------------------------------------------

// 	--------------------------------------------START-->HOSTEL DETAILS---------------------------------------------
	
	//SAVE HOSTEL DETAILS
	function Save_As_Draft_Hostel_Details(sd) {
		
		$.ajaxSetup({
		    async: false
		});	
		var hid_college_council = $("#hid_college_council").val();
		if(hid_college_council == "0"){
			if(sd != -1){
			alert("Please Save College Council and Website Details First");
			}
			return false;
		}
		var res = CheckNullorBlankZero('boys_area');
		if (res !== "true") {
			alert(res +"Boys Hostel Area");
			$('#boys_area').focus();
			return false;
		}
		var res = CheckNullorBlank('boys_own_rented');
		if (res !== "true") {
			alert(res +"Boys Hostel Own/Rented");
			$('#boys_own_rented').focus();
			return false;
		}
		var res = CheckNullorBlankZero('boys_room_no');
		if (res !== "true") {
			alert(res +"Total Room Number of Boys Hostel");
			$('#boys_room_no').focus();
			return false;
		}
		var res = CheckNullorBlankZero('boys_capacity');
		if (res !== "true") {
			alert(res +"Boys Hostel Capacity");
			$('#boys_capacity').focus();
			return false;
		}
		var res = CheckNullorBlankZero('boys_occupied_room');
		if (res !== "true") {
			alert(res +"Occupied Room in Boys Hostel");
			$('#boys_occupied_room').focus();
			return false;
		}
		var res = CheckNullorBlank('boys_mess_facility');
		if (res !== "true") {
			alert(res +"Boys Hostel Mess Facility");
			$('#boys_mess_facility').focus();
			return false;
		}
		var res = CheckNullorBlank('boys_warden_facility');
		if (res !== "true") {
			alert(res +"Boys Hostel Warden Facility");
			$('#boys_warden_facility').focus();
			return false;
		}
		var res = CheckNullorBlankZero('girls_area');
		if (res !== "true") {
			alert(res +"Girls Hostel Area");
			$('#girls_area').focus();
			return false;
		}
		var res = CheckNullorBlank('girls_own_rented');
		if (res !== "true") {
			alert(res +"Girls Hostel Own/Rented");
			$('#girls_own_rented').focus();
			return false;
		}
		var res = CheckNullorBlankZero('girls_room_no');
		if (res !== "true") {
			alert(res +"Total Room Number of Girls Hostel");
			$('#girls_room_no').focus();
			return false;
		}
		var res = CheckNullorBlankZero('girls_capacity');
		if (res !== "true") {
			alert(res +"Girls Hostel Capacity");
			$('#girls_capacity').focus();
			return false;
		}
		var res = CheckNullorBlankZero('girls_occupied_room');
		if (res !== "true") {
			alert(res +"Occupied Room in Girls Hostel");
			$('#girls_occupied_room').focus();
			return false;
		}
		var res = CheckNullorBlank('girls_mess_facility');
		if (res !== "true") {
			alert(res +"Girls Hostel Mess Facility");
			$('#girls_mess_facility').focus();
			return false;
		}
		var res = CheckNullorBlank('girls_warden_facility');
		if (res !== "true") {
			alert(res +"Girls Hostel Warden Facility");
			$('#girls_warden_facility').focus();
			return false;
		}
		
		var res = CheckNullorBlankZero('guest_area');
		if (res !== "true") {
			alert(res +"Guest Hostel Area");
			$('#guest_area').focus();
			return false;
		}
		var res = CheckNullorBlank('guest_own_rented');
		if (res !== "true") {
			alert(res +"Guest Hostel Own/Rented");
			$('#guest_own_rented').focus();
			return false;
		}
		var res = CheckNullorBlankZero('guest_room_no');
		if (res !== "true") {
			alert(res +"Total Room Number of Guest Hostel");
			$('#guest_room_no').focus();
			return false;
		}
		var res = CheckNullorBlankZero('guest_capacity');
		if (res !== "true") {
			alert(res +"Guest Hostel Capacity");
			$('#guest_capacity').focus();
			return false;
		}
		var res = CheckNullorBlankZero('guest_occupied_room');
		if (res !== "true") {
			alert(res +"Occupied Room in Guest Hostel");
			$('#guest_occupied_room').focus();
			return false;
		}
		var res = CheckNullorBlank('guest_mess_facility');
		if (res !== "true") {
			alert(res +"Guest Hostel Mess Facility");
			$('#guest_mess_facility').focus();
			return false;
		}
		var res = CheckNullorBlank('guest_warden_facility');
		if (res !== "true") {
			alert(res +"Guest Hostel Warden Facility");
			$('#guest_warden_facility').focus();
			return false;
		}
		
		var form = $("#hostel_facility").serialize();
		console.log(form);
		$.post(
				'Hostel_Details_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        		$("#hid_hostel_facility").val(j);
		        		if(sd != -1){
		        	  alert("Data Save Sucessfully");
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
// 	--------------------------------------------END-->HOSTEL DETAILS---------------------------------------------

// 	--------------------------------------------START-->MESS DETAILS---------------------------------------------
	
	//SAVE MESS DETAILS
	function Save_As_Draft_Mess_Details(sd) {
		
		$.ajaxSetup({
		    async: false
		});	
		var hid_college_council = $("#hid_college_council").val();
		if(hid_college_council == "0"){
			if(sd != -1){
			alert("Please Save College Council and Website Details First");
			}
			return false;
		}
		var res = CheckNullorBlank('type_of_mess');
		if (res !== "true") {
			alert(res +"Type of Mess");
			$('#type_of_mess').focus();
			return false;
		}
		var res = CheckNullorBlankZero('mess_total_area');
		if (res !== "true") {
			alert(res +"Toal Area of Mess");
			$('#mess_total_area').focus();
			return false;
		}
		var res = CheckNullorBlankZero('mess_total_cooks');
		if (res !== "true") {
			alert(res +"Total No. of Cooks");
			$('#mess_total_cooks').focus();
			return false;
		}
		var res = CheckNullorBlankZero('mess_total_capacity');
		if (res !== "true") {
			alert(res +"Total Capacity of Mess");
			$('#mess_total_capacity').focus();
			return false;
		}
		
		var form = $("#mess_details").serialize();
		console.log(form);
		$.post(
				'Mess_Details_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        		$("#hid_mess_details").val(j);
		        		if(sd != -1){
		        	  alert("Data Save Sucessfully");
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
	
// 	--------------------------------------------END-->MESS DETAILS---------------------------------------------

// 	--------------------------------------------START-->HERBAL GARDEN------------------------------------------
	
	//SAVE HERBAL GARDEN DETAILS
	function Save_As_Draft_Herbal_Garden_Details(sd) {
		
		$.ajaxSetup({
		    async: false
		});	
		var hid_college_council = $("#hid_college_council").val();
		if(hid_college_council == "0"){
			if(sd != -1){
			alert("Please Save College Council and Website Details First");
			}
			return false;
		}
		var res = CheckNullorBlank('total_area');
		if (res !== "true") {
			alert(res +"Total area of the Herbal Garden");
			$('#total_area').focus();
			return false;
		}
		var res = CheckNullorBlankZero('total_cultivated_species');
		if (res !== "true") {
			alert(res +"Total cultivated species");
			$('#total_cultivated_species').focus();
			return false;
		}
	 	var res = CheckNullorBlank('irrigation_facility');
		if (res !== "true") {
			alert(res +"irrigation facility");
			$('#irrigation_facility').focus();
			return false;
		}
		
			var form_data = new FormData(document.getElementById("herbal_garden"));
			$.ajax({
				url : 'Herbal_Garden_Details_Save_Draft_Action?_csrf=' + value,
				type : "POST",
				data : form_data,
				enctype : 'multipart/form-data',
				processData : false,
				contentType : false
			}).done(function(j){
	          if(j>0){
	        		$("#hid_herbal_garden").val(j);
	        		if(sd != -1){
	        	  alert("Data Save Sucessfully");
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
	
// 	--------------------------------------------END-->>HERBAL GARDEN------------------------------------------

// 	--------------------------------------------START-->ADDITIONAL INFORMATION DETAILS---------------------------------------------
	
	//SAVE ADDITIONAL INFORMATION DETAILS
	function Save_As_Draft_Add_Info_Details() {
		
		$.ajaxSetup({
		    async: false
		});	
// 		var hid_college_council = $("#hid_college_council").val();
// 		if(hid_college_council == "0"){
// 			alert("Please Save College Council and Website Details First");
// 			return false;
// 		}
		var trasport_facility = $("input[name='trasport_facility']:checked").val();
		if( trasport_facility == null ){
			alert("Please Select Trasport Facility");
			return false;
	   	}
		var sports_facility = $("input[name='sports_facility']:checked").val();
		if( sports_facility == null ){
			alert("Please Select Sport Facility");
			return false;
	   	}
		var inspection_pending = $("input[name='inspection_pending']:checked").val();
		if( inspection_pending == null ){
			alert("Please Select Inspection Pending for Payment");
			return false;
	   	}
		var penalty_amount = $("input[name='penalty_amount']:checked").val();
		if( penalty_amount == null ){
			alert("Please Select Penalty Amount");
			return false;
	   	}
		var swasthya_rakshan_programme = $("input[name='swasthya_rakshan_programme']:checked").val();
		if( swasthya_rakshan_programme == null ){
			alert("Please Select Swasthya Rakshan Programme");
			return false;
	   	}
		var compliance_report = $("input[name='compliance_report']:checked").val();
		if( compliance_report == null ){
			alert("Please Select Compliance Report");
			return false;
	   	}
		var ambulance_facility = $("input[name='ambulance_facility']:checked").val();
		if( ambulance_facility == null ){
			alert("Please Select Ambulance Facility");
			return false;
	   	}
		var compliance_report_check = $("input[name='compliance_report_check']:checked").val();
		if( compliance_report_check == null ){
			alert("Please Select Compliance Report Check");
			return false;
	   	}
		if(compliance_report_check == "1"){
			var hid_compliance_report_doc = CheckNullorBlank('hid_compliance_report_doc');
			if(hid_compliance_report_doc !== "true"){
				var res = CheckNullorBlank('compliance_report_doc');
				if (res !== "true") {
					alert(res +"Document of Compliance Report");
	 				$('#compliance_report_doc').focus();
					return false;
				}
			}
		}
		
		var form_data = new FormData(document.getElementById("additional_information"));
		$.ajax({
			url : 'Add_Info_Details_Save_Draft_Action?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(j){
          if(j>0){
        		$("#hid_additional_information").val(j);
        	  alert("Final Submit Sucessfully");
          }
          else{
        	  alert(j);
          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
			
	 }
	
	
// 	--------------------------------------------END-->ADDITIONAL INFORMATION DETAILS---------------------------------------------
	//==============================CSP START==============================

	document.addEventListener('DOMContentLoaded', function() {
		
		//COLLEGE COUNCIL START
		document.getElementById('college_council_details_save').onclick = function() {
			return Save_As_Draft_College_Council_Details();	
		};
		document.getElementById('username_ci').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('password_ci').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('college_working_hours').onkeypress = function() {
	 		return isNumberKey0to9(event, this);
		};
		document.getElementById('council_yes').onclick = function() {
			Coincil_Yes();
		};
		document.getElementById('council_no').onclick = function() {
			Coincil_Yes();
		};
		document.getElementById('cctv_status_yes').onclick = function() {
			Cctv_Status_Yes();
		};
		document.getElementById('cctv_status_no').onclick = function() {
			Cctv_Status_Yes();
		};
		document.getElementById('council_document').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"council_document","200kb","council_document_lbltik","council_document_lbl",this.accept)
		};
		
			//ADD MORE CAMERA LOCATION DETAILS
		document.getElementById('family_save_camera1').onclick = function() {
			return Save_As_Draft_Camera(1);
		};
		document.getElementById('camera_add1').onclick = function() {
			return formOpen_Camera(1);
		};
		document.getElementById('camera_remove1').onclick = function() {
			return delete_Camera(1);
		};
		document.getElementById('camera_location1').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"camera_location1","200kb","camera_location_lbltik1","camera_location_lbl1",this.accept)
		};
		
		
		//PROGRESS INSTITUTE START
		document.getElementById('progress_institution_details_save').onclick = function() {
			return Save_As_Draft_Progress_Institution_Details();	
		};
		document.getElementById('cons_clg_hospital').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('app_teaching_staff').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('app_non_teaching_staff').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('app_paramedical').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('expansion_various_dept').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('expansion_herbal_ganden').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('hospital_opd').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('hospital_ipd').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('seminars').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('pulication_by_clg').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('research_activities').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('award_details').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		
		//CONSTRUCTED AREA START
		document.getElementById('constructed_area_details_save').onclick = function() {
			return Save_As_Draft_Constructed_Area_Details();	
		};
		document.getElementById('college_const').onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('lecturer_hall').onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('exam_hall').onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('central_library').onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('boys_common_room').onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('girl_common_room').onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('canteen').onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('administrative_section').onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		
		<c:forEach var="j" items="${getDepartmentList}" varStatus="num">
			
		document.getElementById('area_of_department_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		
		</c:forEach>
		
		document.getElementById('total_area_of_college').onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('total_lecture_hall').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.querySelectorAll('.grand').forEach((items, index) => {
			items.addEventListener('blur', event => {
				Total_Constructed_Area();
			})
			
		});
		
		
		//START LIBRARY DETAILS
		document.getElementById('central_library_details_save').onclick = function() {
			return Save_As_Draft_Central_Library_Details();	
		};
		document.getElementById('cataloguing_books_yes').onclick = function() {
			Cataloguing_Books_Yes();
		};
		document.getElementById('cataloguing_books_no').onclick = function() {
			Cataloguing_Books_Yes();
		};
		document.getElementById('text_book').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('regerence_book').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('govn_publication').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('new_addition').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('book_bank').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('total_book_no').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('subscribed_no').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('complementary_no').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('news_paper_no').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('back_issue_no').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('student_reading_room_capacity').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('faculty_reading_room_capacity').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('rading_room_purpose').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('home_lending').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('photocopying_facility').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('cataloguing_system').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('library_working_hours').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('librarian_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('librarian_qualification').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('other_information').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('elibrary_check_yes').onclick = function() {
			Elibrary_Status_Yes();
		};
		document.getElementById('elibrary_check_no').onclick = function() {
			Elibrary_Status_Yes();
		};
		document.getElementById('total_computers').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('total_subscription').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('total_ebooks').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
				//ADD MORE LIBRARIAN DETAILS
		document.getElementById('family_save_library1').onclick = function() {
			return Save_As_Draft_Library(1);
		};
		document.getElementById('library_add1').onclick = function() {
			return formOpen_Library(1);
		};
		document.getElementById('library_remove1').onclick = function() {
			return delete_Library(1);
		};
		document.getElementById('library_assistants_name1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('assistants_qualification1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		
		//STRAT HOSTEL DETAILS
		document.getElementById('hostel_details_save').onclick = function() {
			return Save_As_Draft_Hostel_Details();	
		};
		document.getElementById('boys_area').onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('boys_room_no').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('boys_capacity').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('girls_area').onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('girls_room_no').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('girls_capacity').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		
		
		//START MESS DETAILS
		document.getElementById('mess_details_save').onclick = function() {
			return Save_As_Draft_Mess_Details();	
		};
		document.getElementById('mess_total_area').onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('mess_total_cooks').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('mess_total_capacity').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		
		//START HERBAL GARDEN
		document.getElementById('herbal_garden_save').onclick = function() {
			return Save_As_Draft_Herbal_Garden_Details();	
		};
		document.getElementById('total_area').onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('total_cultivated_species').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('plant_species_list').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"plant_species_list","200kb","plant_species_list_lbltik","plant_species_list_lbl",this.accept)
		};
		document.getElementById('herbal_garden_list').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"herbal_garden_list","200kb","herbal_garden_list_lbltik","herbal_garden_list_lbl",this.accept)
		};
		
		//START ADDITIONAL INFORMATION
		document.getElementById('additional_information_details_save').onclick = function() {
			return Validation();	
		};
		document.getElementById('compliance_report_check_yes').onclick = function() {
			Compliance_Report_Yes();
		};
		document.getElementById('compliance_report_check_no').onclick = function() {
			Compliance_Report_Yes();
		};
		document.getElementById('compliance_report_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"compliance_report_doc","200kb","compliance_report_doc_lbltik","compliance_report_doc_lbl",this.accept)
		};
		
	});
	
	//==============================CSP END==============================
		
		
		
//START----> ADD MORE FOR LIBRARIAN DETAILS

function formOpen_Library(R){
	
	 $("#library_assi_table").show();
		 
		 att=0;
		 $("#library_add"+R).hide();
		 $("#library_remove"+R).hide();
		 att=parseInt($("#family_save_libraryh1").val())+1;
		 att= parseInt(R)+1;
		
		 if(att < 21){
			 
			 $("input#family_save_libraryh1").val(att);//current serial No
			 $("table#library_assi_table").append('<tr id="tr_sibling_library1'+att+'">'
//					+'<td class="min-width">'+att+'</td>'

					+'<td class="min-width"><div class="lead"><div class="lead-text"><p>'+att+'</p></div></div></td>'
					+'<td><div class="input-style-1"><input type="text" id="library_assistants_name'+att+'" name="library_assistants_name'+att+'" class="form-control"' 
					+'autocomplete="off" placeholder="Name of Assistants" maxlength="100"></div></td>'
					
					+'<td><div class="input-style-1"><input type="text" id="assistants_qualification'+att+'" name="assistants_qualification'+att+'"'
					+'class="form-control" autocomplete="off" placeholder="Qualification of Assistants" maxlength="100"></div></td>'
					
					+'<td class="addminusbut"><ul class="buttons-group mainbtn daobtn action"><li><a class="main-btn success-btn btn-hover btn-sm btnapprove"'
					+'value="Save" title="SAVE" id="family_save_library'+att+'"><i class="lni lni-checkmark"></i></a></li>'
					+'<li><a class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none" value="ADD" title="ADD" id="library_add'+att+'">'
					+'<i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"'
					+'value="Delete" title="Delete" id="library_remove'+att+'">'
					+'<input type="hidden" id="family_save_libraryh'+att+'" name="family_save_libraryh'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
					+'<input type="hidden" id="hid_library'+att+'" name="hid_library'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
					+'<i class="lni lni-trash-can"></i></a></li></ul></td>'
					+ '</tr>');
			
			  addOnclick_library(att);
			 
			 $('#btnmodel'+att).click(function() {
				   $('#modelWindow').modal('show');
				   dynamicTable(att,1);
				});
			 
			
			
			$('#closeModel').click(function() {
				   $('#modelWindow').modal('hide');
				});
			$('#closeModel2').click(function() {
				   $('#modelWindow').modal('hide');
				});
			$("#family_save_libraryh1").val(att);
			
		 }else{
					alert("You can not enter more than twenty times");
					 if ( att == 21){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
// 			hidesystem_degree(att);
	}
	
	
function addOnclick_library(att) {
	
	
	document.getElementById('library_add'+att).onclick = function() {
		return formOpen_Library(att);
	};
	document.getElementById('library_remove'+att).onclick = function() {
		return delete_Library(att);
	};
	document.getElementById('family_save_library' + att).onclick = function() {
		Save_As_Draft_Library(att);
	};
	document.getElementById('library_assistants_name'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('assistants_qualification'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};

}
	
function Save_As_Draft_Library(ps) {
	$.ajaxSetup({
		async : false
	});
	
	var hid_college_council = $("#hid_college_council").val();
	if(hid_college_council == "0"){
		alert("Please Save College Council and Website Details First");
		return false;
	}
	var res = CheckNullorBlank('library_assistants_name' + ps);
	if (res !== "true") {
		alert(res + "Name of Assistant");
		$('#library_assistants_name' + ps).focus();
		return false;
	}
	var res = CheckNullorBlank('assistants_qualification' + ps);
	if (res !== "true") {
		alert(res + "Qualification of Assistant");
		$('#assistants_qualification' + ps).focus();
		return false;
	}
	
	$("#indno_library").val(ps);
	var form = $("#central_library_form").serialize();
	$.post(
			'Librarian_Details_Save_Draft_Action?' + key + "="
					+ value, form, function(j) {
				if(j>0){
					$("#hid_library"+ps).val(j);
					alert("Data Saved Successfully");
					$("#library_add" + ps).show();
					$("#library_remove" + ps).show();
	          }
	          else{
	        	  alert(j);
	          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	
}


function delete_Library(R) {
	
	// 		var qualtification_idhid2= $("input#qualtification_id"+R).val();
	if(confirm('Are you sure you want to delete?')) {

	var hid_library = $('#hid_library' + R).val();
	
	$.post("delete_Librarian_Details?" + key + "=" + value, {
		hid_library : hid_library
	}, function(j) {
		alert(j);
	});

	$("tr#tr_sibling_library1" + R).remove();
	R = R - 1;
	$("input#count_hidden_att_name_med").val(att);
	$("#library_add" + R).show();
	$("#library_remove" + R).show();
	}else {
		return false;
	}
}


function getLibrarian_Details() {
	
	$.ajaxSetup({
		async : false
	});
	
	var ser = 0;

	$.post("getLibrarian_Details?" + key + "=" + value, {
		
	}, function(j) {
		
		for (var i = 0; i < j.length; i++) {
			ser = i + 1;

			if (j.length > 0) {

				if (ser > 1) {
					
					$("#library_add" + (ser - 1)).click();
				}
				
				$("#hid_library" + ser).val(j[i].id);
				$("#library_assistants_name" + ser).val(j[i].library_assistants_name);
				$("#assistants_qualification" + ser).val(j[i].assistants_qualification);
				$("#library_add" + ser).show();
				$("#library_remove" + ser).show();
			}
		}
	});
}

//END----> ADD MORE FOR LIBRARIAN DETAILS


//START----> ADD MORE FOR CAMERA LOCATION DETAILS

function formOpen_Camera(R){
	
	 $("#locationphoto_table").show();
		 
		 att=0;
		 $("#camera_add"+R).hide();
		 $("#camera_remove"+R).hide();
		 att=parseInt($("#family_save_camerah1").val())+1;
		 att= parseInt(R)+1;
		
		 if(att < 21){
			 
			 $("input#family_save_camerah1").val(att);//current serial No
			 $("table#locationphoto_table").append('<tr id="tr_sibling_camera1'+att+'">'
//					+'<td class="min-width">'+att+'</td>'

					+'<td class="min-width"><div class="lead"><div class="lead-text"><p>'+att+'</p></div></div></td>'
					
					
					+'<td><div class="input-style-1"><input type="file" id="camera_location'+att+'" name="camera_location'+att+'" class="form-control" accept="jpg,jpeg,png">'
					+'<input type=hidden id="hid_camera_location'+att+'" name="hid_camera_location'+att+'" class="form-control" value="">'
					+'<div class="note-text"><span class="errorClass" id="camera_location_lbl'+att+'">${doc_path_doc1_msg}</span>'
					+'<span class="tikClass" id="camera_location_lbltik'+att+'"></span></div></div></td>'
					
					+'<td class="addminusbut"><ul class="buttons-group mainbtn daobtn action"><li><a class="main-btn success-btn btn-hover btn-sm btnapprove"'
					+'alue="Save" title="SAVE" id="family_save_camera'+att+'"><i class="lni lni-checkmark"></i></a></li>'
						
					+'<li><a class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none" value="ADD" title="ADD" id="camera_add'+att+'"><i class="lni lni-plus"></i>'
					+'</a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none" value="Delete" title="Delete" id="camera_remove'+att+'">'
					+'<input type="hidden" id="family_save_camerah'+att+'" name="family_save_camerah'+att+'" class="form-control autocomplete" value="1">'
					+'<input type="hidden" id="hid_camera'+att+'" name="hid_camera'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
					+'<i class="lni lni-trash-can"></i></a></li></ul></td>'
					+ '</tr>');
			
			 addOnclick_Camera(att);
			 
			 $('#btnmodel'+att).click(function() {
				   $('#modelWindow').modal('show');
				   dynamicTable(att,1);
				});
			 
			
			
			$('#closeModel').click(function() {
				   $('#modelWindow').modal('hide');
				});
			$('#closeModel2').click(function() {
				   $('#modelWindow').modal('hide');
				});
			$("#family_save_camerah1").val(att);
			
		 }else{
					alert("You can not enter more than hundread times");
					 if ( att == 100){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
// 			hidesystem_degree(att);
	}
	
	
function addOnclick_Camera(att) {
	
	
	document.getElementById('camera_add'+att).onclick = function() {
		return formOpen_Camera(att);
	};
	document.getElementById('camera_remove'+att).onclick = function() {
		return delete_Camera(att);
	};
	document.getElementById('family_save_camera' + att).onclick = function() {
		Save_As_Draft_Camera(att);
	};
	document.getElementById('camera_location'+ att).onchange = function() {
		return pdfFileSizeValidation(this,this.value,"camera_location"+ att,"200kb","camera_location_lbltik"+ att,"camera_location_lbl"+ att,this.accept)
	};

}
	
function Save_As_Draft_Camera(ps) {
	$.ajaxSetup({
		async : false
	});
	var hid_college_council = $("#hid_college_council").val();
	if(hid_college_council == "0"){
		alert("Please Save College Council and Website Details First");
		return false;
	}
	var hid_camera_location = CheckNullorBlank('hid_camera_location'+ps);
	if(hid_camera_location !== "true"){
		var res = CheckNullorBlank('camera_location'+ps);
		if (res !== "true") {
			alert("Please Upload Camera Location Image");
				$('#camera_location'+ps).focus();
			return false;
		}
	}
	
	$("#indno_camera").val(ps);
	var form_data = new FormData(document.getElementById("college_council"));
	$.ajax({
		url : 'Camera_Location_Save_Draft_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j) {
				if(j>0){
					$("#hid_camera"+ps).val(j);
					alert("Data Saved Successfully");
					$("#camera_add" + ps).show();
					$("#camera_remove" + ps).show();
	          }
	          else{
	        	  alert(j);
	          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	
}


function delete_Camera(R) {
	
	// 		var qualtification_idhid2= $("input#qualtification_id"+R).val();
	if(confirm('Are you sure you want to delete?')) {

	var hid_camera = $('#hid_camera' + R).val();
	
	$.post("delete_Camera_Location_Details?" + key + "=" + value, {
		hid_camera : hid_camera
	}, function(j) {
		alert(j);
	});

	$("tr#tr_sibling_camera1" + R).remove();
	R = R - 1;
	$("input#count_hidden_att_name_med").val(att);
	$("#camera_add" + R).show();
	$("#camera_remove" + R).show();
	}else {
		return false;
	}
}


function getCamera_Details() {
	
	$.ajaxSetup({
		async : false
	});
	
	var ser = 0;

	$.post("getCamera_Location_Details?" + key + "=" + value, {
		
	}, function(j) {
		
		for (var i = 0; i < j.length; i++) {
			ser = i + 1;

			if (j.length > 0) {

				if (ser > 1) {
					
					$("#camera_add" + (ser - 1)).click();
				}
				
				$("#hid_camera" + ser).val(j[i].id);
				$("#hid_camera_location" + ser).val(j[i].camera_location);
				$("#camera_add" + ser).show();
				$("#camera_remove" + ser).show();
			}
		}
	});
}

//END----> ADD MORE FOR CAMERA LOCATION DETAILS


function Validation() {
	
	var hid_college_council = $("#hid_college_council").val();
	if(hid_college_council == "0"){
		alert("Please Save College Council and Website Details First");
		return false;
	}
	//COLLEGE COUNCIL
	var council_check = $("input[name='council_check']:checked").val();
	if( council_check == null ){
		alert("Please Select College Council Available in College Council and Website Details");
		return false;
   	}
	if(council_check == "1"){
		var hid_biometricattendance = CheckNullorBlank('hid_council_document');
		if(hid_biometricattendance !== "true"){
			var res = CheckNullorBlank('council_document');
			if (res !== "true") {
				alert("Please Upload Document of College Council in College Council and Website Details");
				$('#council_document').focus();
				return false;
			}
		}
	}
	var college_website = $("input[name='college_website']:checked").val();
	if( college_website == null ){
		alert("Please Select College Website in College Council and Website Details");
		return false;
   	}
	var res = CheckNullorBlank('website_update_date');
	if (res !== "true") {
		alert(res +"Website Last Update Date in College Council and Website Details");
		$('#website_update_date').focus();
		return false;
	}
	var cctv_status = $("input[name='cctv_status']:checked").val();
	if( cctv_status == null ){
		alert("Please Select CCTV Check in College Council and Website Details");
		return false;
   	}
	var res = CheckNullorBlank('login_url');
	if (res !== "true") {
		alert(res +"Login Url in College Council and Website Details");
		$('#login_url').focus();
		return false;
	}
	var res = CheckNullorBlank('username_ci');
	if (res !== "true") {
		alert(res +"Username in College Council and Website Details");
		$('#username_ci').focus();
		return false;
	}
	var res = CheckNullorBlank('password_ci');
	if (res !== "true") {
		alert(res +"Password in College Council and Website Details");
		$('#password_ci').focus();
		return false;
	}
	var biometric_status = $("input[name='biometric_status']:checked").val();
	if( biometric_status == null ){
		alert("Please Select Biometric Available in College Council and Website Details");
		return false;
   	}
	var res = CheckNullorBlank('college_working_hours');
	if (res !== "true") {
		alert(res +"College Working Hours in College Council and Website Details");
		$('#college_working_hours').focus();
		return false;
	}
	
	//CONSTRUCTED AREA
	var res = CheckNullorBlankZero('college_const');
	if (res !== "true") {
		alert(res +"Construction of College in Constructed Area");
		$('#college_const').focus();
		return false;
	}
	var res = CheckNullorBlankZero('lecturer_hall');
	if (res !== "true") {
		alert(res +"Total Area of Lecturer Halls in Constructed Area");
		$('#lecturer_hall').focus();
		return false;
	}
	var res = CheckNullorBlankZero('exam_hall');
	if (res !== "true") {
		alert(res +"Auditorium/Seminar Hall/Exam hall in Constructed Area");
		$('#exam_hall').focus();
		return false;
	}
	var res = CheckNullorBlankZero('central_library');
	if (res !== "true") {
		alert(res +"Central Library in Constructed Area");
		$('#central_library').focus();
		return false;
	}
	var res = CheckNullorBlankZero('boys_common_room');
	if (res !== "true") {
		alert(res +"Common Rooms for Boys in Constructed Area");
		$('#boys_common_room').focus();
		return false;
	}
	var res = CheckNullorBlankZero('girl_common_room');
	if (res !== "true") {
		alert(res +"Common Rooms for Girls in Constructed Area");
		$('#girl_common_room').focus();
		return false;
	}
	var res = CheckNullorBlankZero('canteen');
	if (res !== "true") {
		alert(res +"Canteen in Constructed Area");
		$('#canteen').focus();
		return false;
	}
	var res = CheckNullorBlankZero('administrative_section');
	if (res !== "true") {
		alert(res +"Administrative section in Constructed Area");
		$('#administrative_section').focus();
		return false;
	}
	
	<c:forEach var="j" items="${getDepartmentList}" varStatus="num">
	
	var res = CheckNullorBlankZero('area_of_department_'+${j[0]});
	if (res !== "true") {
		alert(res +"Area of ${j[1]} in Constructed Area");
		$('#area_of_department_'+${j[0]}).focus();
		return false;
	}
	
	</c:forEach>
	
	
	var res = CheckNullorBlankZero('total_area_of_college');
	if (res !== "true") {
		alert(res +"Total Constructed Area of College in Constructed Area");
		$('#total_area_of_college').focus();
		return false;
	}
	var res = CheckNullorBlankZero('total_lecture_hall');
	if (res !== "true") {
		alert(res +"Total Lecture Halls in Constructed Area");
		$('#total_lecture_hall').focus();
		return false;
	}
	
 	//CENTRAL LIBRARY
	var res = CheckNullorBlank('text_book');
	if (res !== "true") {
		alert(res +"Text Book in Central Library");
		$('#text_book').focus();
		return false;
	}
	var res = CheckNullorBlank('regerence_book');
	if (res !== "true") {
		alert(res +"Reference Book in Central Library");
		$('#regerence_book').focus();
		return false;
	}
	var res = CheckNullorBlank('govn_publication');
	if (res !== "true") {
		alert(res +"Government Publications in Central Library");
		$('#govn_publication').focus();
		return false;
	}
	var res = CheckNullorBlank('new_addition');
	if (res !== "true") {
		alert(res +"New Addition in Central Library");
		$('#new_addition').focus();
		return false;
	}
	var res = CheckNullorBlank('book_bank');
	if (res !== "true") {
		alert(res +"Book Bank in Central Library");
		$('#book_bank').focus();
		return false;
	}
	var res = CheckNullorBlank('total_book_no');
	if (res !== "true") {
		alert(res +"Total Number of Books in Central Library");
		$('#total_book_no').focus();
		return false;
	}
	var res = CheckNullorBlankZero('subscribed_no');
	if (res !== "true") {
		alert(res +"Number of Subscribed Journal in Central Library");
		$('#subscribed_no').focus();
		return false;
	}
	var res = CheckNullorBlankZero('complementary_no');
	if (res !== "true") {
		alert(res +"Number of Complementary Journal in Central Library");
		$('#complementary_no').focus();
		return false;
	}
	var res = CheckNullorBlankZero('news_paper_no');
	if (res !== "true") {
		alert(res +"Number of News Paper in Central Library");
		$('#news_paper_no').focus();
		return false;
	}
	var res = CheckNullorBlankZero('back_issue_no');
	if (res !== "true") {
		alert(res +"Number of Back Issue Journal in Central Library");
		$('#back_issue_no').focus();
		return false;
	}
	var res = CheckNullorBlank('library_working_hours');
	if (res !== "true") {
		alert(res +"Working Hours in Central Library");
		$('#library_working_hours').focus();
		return false;
	}
	var res = CheckNullorBlank('student_reading_room_capacity');
	if (res !== "true") {
		alert(res +"Reading Room Capacity for Students in Central Library");
		$('#student_reading_room_capacity').focus();
		return false;
	}
	var res = CheckNullorBlank('faculty_reading_room_capacity');
	if (res !== "true") {
		alert(res +"Reading Room Capacity for Faculty in Central Library");
		$('#faculty_reading_room_capacity').focus();
		return false;
	}
	var res = CheckNullorBlank('rading_room_purpose');
	if (res !== "true") {
		alert(res +"Reading Room Purpose in Central Library");
		$('#rading_room_purpose').focus();
		return false;
	}
	var res = CheckNullorBlank('home_lending');
	if (res !== "true") {
		alert(res +"Home Lending in Central Library");
		$('#home_lending').focus();
		return false;
	}
	var res = CheckNullorBlank('photocopying_facility');
	if (res !== "true") {
		alert(res +"Photocopying Facility in Central Library");
		$('#photocopying_facility').focus();
		return false;
	}
	var computers_facility = $("input[name='computers_facility']:checked").val();
	if( computers_facility == null ){
		alert("Please Select Computers with Internet Facility in Central Library");
		return false;
   	}
	var cataloguing_books = $("input[name='cataloguing_books']:checked").val();
	if( cataloguing_books == null ){
		alert("Please Select Cataloguing of Books in Central Library");
		return false;
   	}
	
	if(cataloguing_books == "1"){
	var res = CheckNullorBlank('cataloguing_system');
	if (res !== "true") {
		alert(res +"System of Cataloguing in Central Library");
		$('#cataloguing_system').focus();
		return false;
	}
	}
	var res = CheckNullorBlank('librarian_name');
	if (res !== "true") {
		alert(res +"Name of Librarian in Central Library");
		$('#librarian_name').focus();
		return false;
	}
	var res = CheckNullorBlank('librarian_qualification');
	if (res !== "true") {
		alert(res +"Qualification of Librarian in Central Library");
		$('#librarian_qualification').focus();
		return false;
	}
	var elibrary_check = $("input[name='elibrary_check']:checked").val();
	if( elibrary_check == null ){
		alert("Please Select E-Library Facility in Central Library");
		return false;
   	}
	if(elibrary_check == "1"){
		var res = CheckNullorBlankZero('total_computers');
		if (res !== "true") {
			alert(res +"Total Number of Computers in Central Library");
			$('#total_computers').focus();
			return false;
		}
		var res = CheckNullorBlankZero('total_subscription');
		if (res !== "true") {
			alert(res +"Total Number of Subscription in Central Library");
			$('#total_subscription').focus();
			return false;
		}
		var res = CheckNullorBlankZero('total_ebooks');
		if (res !== "true") {
			alert(res +"Total Number of E-Books in Central Library");
			$('#total_ebooks').focus();
			return false;
		}
	}
	
	
	//HOSTEL DETAILS
		var res = CheckNullorBlankZero('boys_area');
		if (res !== "true") {
			alert(res +"Boys Hostel Area in Details of Hostel");
			$('#boys_area').focus();
			return false;
		}
		var res = CheckNullorBlank('boys_own_rented');
		if (res !== "true") {
			alert(res +"Boys Hostel Own/Rented in Details of Hostel");
			$('#boys_own_rented').focus();
			return false;
		}
		var res = CheckNullorBlankZero('boys_room_no');
		if (res !== "true") {
			alert(res +"Total Room Number of Boys Hostel in Details of Hostel");
			$('#boys_room_no').focus();
			return false;
		}
		var res = CheckNullorBlankZero('boys_capacity');
		if (res !== "true") {
			alert(res +"Boys Hostel Capacity in Details of Hostel");
			$('#boys_capacity').focus();
			return false;
		}
		var res = CheckNullorBlankZero('boys_occupied_room');
		if (res !== "true") {
			alert(res +"Occupied Room in Boys Hostel in Details of Hostel");
			$('#boys_occupied_room').focus();
			return false;
		}
		var res = CheckNullorBlank('boys_mess_facility');
		if (res !== "true") {
			alert(res +"Boys Hostel Mess Facility in Details of Hostel");
			$('#boys_mess_facility').focus();
			return false;
		}
		var res = CheckNullorBlank('boys_warden_facility');
		if (res !== "true") {
			alert(res +"Boys Hostel Warden Facility in Details of Hostel");
			$('#boys_warden_facility').focus();
			return false;
		}
		var res = CheckNullorBlankZero('girls_area');
		if (res !== "true") {
			alert(res +"Girls Hostel Area in Details of Hostel");
			$('#girls_area').focus();
			return false;
		}
		var res = CheckNullorBlank('girls_own_rented');
		if (res !== "true") {
			alert(res +"Girls Hostel Own/Rented in Details of Hostel");
			$('#girls_own_rented').focus();
			return false;
		}
		var res = CheckNullorBlankZero('girls_room_no');
		if (res !== "true") {
			alert(res +"Total Room Number of Girls Hostel in Details of Hostel");
			$('#girls_room_no').focus();
			return false;
		}
		var res = CheckNullorBlankZero('girls_capacity');
		if (res !== "true") {
			alert(res +"Girls Hostel Capacity in Details of Hostel");
			$('#girls_capacity').focus();
			return false;
		}
		var res = CheckNullorBlankZero('girls_occupied_room');
		if (res !== "true") {
			alert(res +"Occupied Room in Girls Hostel in Details of Hostel");
			$('#girls_occupied_room').focus();
			return false;
		}
		var res = CheckNullorBlank('girls_mess_facility');
		if (res !== "true") {
			alert(res +"Girls Hostel Mess Facility in Details of Hostel");
			$('#girls_mess_facility').focus();
			return false;
		}
		var res = CheckNullorBlank('girls_warden_facility');
		if (res !== "true") {
			alert(res +"Girls Hostel Warden Facility in Details of Hostel");
			$('#girls_warden_facility').focus();
			return false;
		}
		
		var res = CheckNullorBlankZero('guest_area');
		if (res !== "true") {
			alert(res +"Guest Hostel Area in Details of Hostel");
			$('#guest_area').focus();
			return false;
		}
		var res = CheckNullorBlank('guest_own_rented');
		if (res !== "true") {
			alert(res +"Guest Hostel Own/Rented in Details of Hostel");
			$('#guest_own_rented').focus();
			return false;
		}
		var res = CheckNullorBlankZero('guest_room_no');
		if (res !== "true") {
			alert(res +"Total Room Number of Guest Hostel in Details of Hostel");
			$('#guest_room_no').focus();
			return false;
		}
		var res = CheckNullorBlankZero('guest_capacity');
		if (res !== "true") {
			alert(res +"Guest Hostel Capacity in Details of Hostel");
			$('#guest_capacity').focus();
			return false;
		}
		var res = CheckNullorBlankZero('guest_occupied_room');
		if (res !== "true") {
			alert(res +"Occupied Room in Guest Hostel in Details of Hostel");
			$('#guest_occupied_room').focus();
			return false;
		}
		var res = CheckNullorBlank('guest_mess_facility');
		if (res !== "true") {
			alert(res +"Guest Hostel Mess Facility in Details of Hostel");
			$('#guest_mess_facility').focus();
			return false;
		}
		var res = CheckNullorBlank('guest_warden_facility');
		if (res !== "true") {
			alert(res +"Guest Hostel Warden Facility in Details of Hostel");
			$('#guest_warden_facility').focus();
			return false;
		}
	
	//DETAILS OF MESS
		var res = CheckNullorBlank('type_of_mess');
		if (res !== "true") {
			alert(res +"Type of Mess in Details of Mess");
			$('#type_of_mess').focus();
			return false;
		}
		var res = CheckNullorBlankZero('mess_total_area');
		if (res !== "true") {
			alert(res +"Toal Area of Mess in Details of Mess");
			$('#mess_total_area').focus();
			return false;
		}
		var res = CheckNullorBlankZero('mess_total_cooks');
		if (res !== "true") {
			alert(res +"Total No. of Cooks in Details of Mess");
			$('#mess_total_cooks').focus();
			return false;
		}
		var res = CheckNullorBlankZero('mess_total_capacity');
		if (res !== "true") {
			alert(res +"Total Capacity of Mess in Details of Mess");
			$('#mess_total_capacity').focus();
			return false;
		}
	
// 	//HERBAL GRADEN
		var res = CheckNullorBlank('total_area');
		if (res !== "true") {
			alert(res +"Total area of the Herbal Garden in Herbal Garden");
			$('#total_area').focus();
			return false;
		}
		var res = CheckNullorBlankZero('total_cultivated_species');
		if (res !== "true") {
			alert(res +"Total cultivated species in Herbal Garden");
			$('#total_cultivated_species').focus();
			return false;
		}
	 	var res = CheckNullorBlank('irrigation_facility');
		if (res !== "true") {
			alert(res +"irrigation facility in Herbal Garden");
			$('#irrigation_facility').focus();
			return false;
		}
	
	
	//ADDITIONAL INFORMATION
// 		var trasport_facility = $("input[name='trasport_facility']:checked").val();
// 		if( trasport_facility == null ){
// 			alert("Please Select Trasport Facility");
// 			return false;
// 	   	}
// 		var sports_facility = $("input[name='sports_facility']:checked").val();
// 		if( sports_facility == null ){
// 			alert("Please Select Sport Facility");
// 			return false;
// 	   	}
// 		var inspection_pending = $("input[name='inspection_pending']:checked").val();
// 		if( inspection_pending == null ){
// 			alert("Please Select Inspection Pending for Payment");
// 			return false;
// 	   	}
// 		var penalty_amount = $("input[name='penalty_amount']:checked").val();
// 		if( penalty_amount == null ){
// 			alert("Please Select Penalty Amount");
// 			return false;
// 	   	}
// 		var swasthya_rakshan_programme = $("input[name='swasthya_rakshan_programme']:checked").val();
// 		if( swasthya_rakshan_programme == null ){
// 			alert("Please Select Swasthya Rakshan Programme");
// 			return false;
// 	   	}
// 		var compliance_report = $("input[name='compliance_report']:checked").val();
// 		if( compliance_report == null ){
// 			alert("Please Select Compliance Report");
// 			return false;
// 	   	}
// 		var ambulance_facility = $("input[name='ambulance_facility']:checked").val();
// 		if( ambulance_facility == null ){
// 			alert("Please Select Ambulance Facility");
// 			return false;
// 	   	}
// 		var compliance_report_check = $("input[name='compliance_report_check']:checked").val();
// 		if( compliance_report_check == null ){
// 			alert("Please Select Compliance Report Check");
// 			return false;
// 	   	}
// 		if(compliance_report_check == "1"){
// 			var hid_compliance_report_doc = CheckNullorBlank('hid_compliance_report_doc');
// 			if(hid_compliance_report_doc !== "true"){
// 				var res = CheckNullorBlank('compliance_report_doc');
// 				if (res !== "true") {
// 					alert(res +"Document of Compliance Report");
// 	 				$('#compliance_report_doc').focus();
// 					return false;
// 				}
// 			}
// 		}
	
	Save_As_Draft_College_Council_Details(-1);
	Save_As_Draft_Progress_Institution_Details(-1);
	Save_As_Draft_Constructed_Area_Details(-1);
	Save_As_Draft_Central_Library_Details(-1);
	Save_As_Draft_Hostel_Details(-1);
	Save_As_Draft_Mess_Details(-1);
	Save_As_Draft_Herbal_Garden_Details(-1);
	Save_As_Draft_Add_Info_Details();
	
}

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

$("#college_infra_view_id").val(id);
$("#statusview").val($("#statusA").val());
document.getElementById('viewForm').submit();
}


</script>

