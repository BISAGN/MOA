<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/watermark/common.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.js"></script>
<script src="js/miso/commonJS/addmorefunctionality.js"
	type="text/javascript"></script>
<script src="js/miso/commonJS/addmorefunctionality.js"
	type="text/javascript"></script>
<!-- Datepicker start -->
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- Datepicker End -->
<!-- <link rel="stylesheet" href="assets/vendor/internal_css.css"> -->

<section class="search_regulation dashboard-page">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12">
					<div class="title mb-30">
						<h2>Faculty Details</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Faculty Details</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<!-- title-wrapper end -->

		<div class="form-elements-wrapper tunnel-form">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="card-style mb-30">
						<div class="row">
							<div class="col-md-12">
								<form
									action="tea_dtlAction?${_csrf.parameterName}=${_csrf.token}"
									name="teacher_detail" id="step-form-horizontal"
									class="step-form-horizontal" method='POST'
									modelAttribute="tea_dtl_CMD" enctype="multipart/form-data">
									<div>
<!-- 									<h4>0. Dashboard</h4> -->
<!-- 									<section> -->
									
<!-- 									<div class="detail-block"> -->
<!-- 										<div class="row"> -->
<!-- 												<div class="col-lg-12 col-md-12 col-sm-12"> -->
<!-- 													<div class="custom-data-value custom-title custom-title-bg"> -->
<!-- 														<h5 class="custom-title-tag">Dashboard</h5> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 									</div> -->
									
<!-- 								<h5 class="text-medium text-medium mb-10">Step Instruction</h5>	 -->
<!-- 								<div class="inst_block"> -->
<!-- 								<h6 class="mb-1">Instruction</h6> -->
<!-- 								<ul class="inst_list"> -->
<!-- 									<li><p class="inst_text">1. Kindly use Mozila Firefox(version 14+) or Google Chrome (version 20+)to fill in the Application Form.</p></li> -->
<!-- 									<li><p class="inst_text">2. Fill in the details and click on Next to proceed.</p></li> -->
<!-- 									<li><p class="inst_text">3. Fields marked with * are mandatory.</p></li> -->
<!-- 									<li><p class="inst_text">4. Please Save As Draft each form and proceed to next step. </p></li> -->
<!-- 									<li><p class="inst_text">5. Once you Click on Final Submit in your form you are not be able to edit form. </p></li> -->
<!-- 									<li><p class="inst_text">6. Once you Click on Final Submit in your form you are not be able to edit form. </p></li> -->
<!-- 								</ul> -->
<!-- 							</div> -->
									
									
<!-- 									</div> -->
<!-- 									</section> -->
									
									
										<h4>1. Personal Details</h4>
										<section>
										
										<div class="inst_block">
								<h6 class="mb-1">Instruction</h6>
								<ul class="inst_list">
									<li><p class="inst_text">1. Kindly use Mozila Firefox(version 14+) or Google Chrome (version 20+)to fill in the Application Form.</p></li>
									<li><p class="inst_text">2. Fill in the details and click on Next to proceed.</p></li>
									<li><p class="inst_text">3. Fields marked with * are mandatory.</p></li>
									<li><p class="inst_text">4. Please Save As Draft each form and proceed to next step. </p></li>
									<li><p class="inst_text">5. Once you Click on Final Submit in your form you are not be able to edit form. </p></li>
									
								</ul>
							</div>
										
										<ul class="custom-uniqueid" id="t_code">
										<li><p class="custom-id custom-active-badge ">
										<span class="custom-id-title ">Teacher Code:</span><span class="custom-id-value " id="teacher_code">In Progress</span>
										</p></li>
							
										</ul>
										
										
											<div class="detail-block">
												<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<div class="custom-data-value custom-title custom-title-bg">
														<h5 class="custom-title-tag">Personal Details</h5>
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<label class="buttonmerge">First Name <strong
														class="mandatory">*</strong></label>
													<div class="groupadd">
														<div class="select-style-1">
															<div class="select-position">
																<select name="cand_prefix" id="cand_prefix"
																	class="form-control">
																	<option value="0">--Select--</option>
																	<c:forEach var="item" items="${getPrefixList}"
																		varStatus="num">
																		<option value="${item.id}" name="${item.prefix}">${item.prefix}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
														<div class="input-style-1 form-group buttonmergeinput">
															<input type="text" name="first_name" id="first_name"
																class="form-control " placeholder="Enter First Name"
																maxlength="50" autocomplete="off"> <input type="hidden" value="0"
																name="perentId" id="perentId" class="form-control"
																maxlength="50">
														</div>
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<div class="input-style-1">
														<label>Middle Name <strong class="mandatory"></strong></label>
														<input type="text" name="middle_name" id="middle_name"
															class="form-control" placeholder="Enter Middle Name"
															maxlength="50" autocomplete="off">
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<div class="input-style-1">
														<label>Last Name <strong class="mandatory">*</strong></label>
														<input type="text" name="last_name" id="last_name"
															class="form-control " placeholder="Enter Last Name"
															maxlength="50" autocomplete="off">
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<div class="select-style-1">
														<label for="fname">Gender <strong
															class="mandatory">* </strong></label>
														<div class="select-position">
															<select name="gender" id="gender" class="form-control ">
																<!-- style="text-transform: uppercase" -->
																<option value="0">--Select--</option>
																<c:forEach var="item" items="${getgenderList}"
																	varStatus="num">
																	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																</c:forEach>
															</select>

														</div>
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<div class="input-style-2">
														<label>Date of Birth<strong class="mandatory">*</strong></label>
														<input type="text" name="date_of_birth" id="date_of_birth"
															maxlength="10"
															class="form-control-sm form-control effect-9 "
															aria-required="true" autocomplete="off"
															value="DD/MM/YYYY">
													</div>
													<input type="hidden" id="yrr" name="yrr" value="">
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<div class="input-style-1">
														<label for="fname">Father's Name <strong
															class="mandatory">*</strong></label> <input type="text"
															name="father_name" id="father_name" class="form-control "
															placeholder="Enter Father's Name" maxlength="50" autocomplete="off">
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<div class="input-style-1">
														<label for="fname">Mother's Name <strong
															class="mandatory">*</strong></label> <input type="text"
															name="mother_name" id="mother_name" class="form-control "
															placeholder="Enter Mother's Name" maxlength="50" autocomplete="off">
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<div class="input-style-1">
														<label for="fname">Spouse's Name <strong
															class="mandatory"></strong></label> <input type="text"
															name="spouse_name" id="spouse_name" class="form-control"
															placeholder="Enter Spouse's Name" maxlength="50" autocomplete="off">
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<div class="input-style-1">
														<label for="fname">Mobile No <strong
															class="mandatory">*</strong></label> <input type="text"
															name="mobile_no" id="mobile_no" class="form-control "
															placeholder="Enter Mobile No." maxlength="10"
															minlength="10" autocomplete="off">
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<div class="input-style-1">
														<label for="fname">Email Id <strong
															class="mandatory">*</strong></label> <input type="email"
															name="email" id="email" class="form-control "
															placeholder="abc@example.com" maxlength="70"
															value="${email_txt}" autocomplete="off">
														<!--  pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"-->
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<div class="input-style-1">
														<label for="fname">Aadhaar Card <strong
															class="mandatory">*</strong></label>
														<div class="form-group stepform-groupadd">
															<input type="text" name="aadhar_no1" id="aadhar_no1"
																class="form-control " maxlength="4" autocomplete="off">  <input
																type="text" name="aadhar_no2" id="aadhar_no2"
																class="form-control " maxlength="4" autocomplete="off"> <input
																type="text" name="aadhar_no3" id="aadhar_no3"
																class="form-control " maxlength="4" autocomplete="off"> <input
																type="hidden" id="pers_aadhar_no_hd"
																name="pers_aadhar_no_hd" class="form-control"
																value="${pers_aadhar_no77}" autocomplete="off"></input>
														</div>
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<div class="input-style-1">
														<label for="fname">PAN No <strong
															class="mandatory">*</strong></label> <input type="text"
															name="pan_no" id="pan_no" class="form-control "
															placeholder="Enter PAN No." maxlength="10" minlength="10" autocomplete="off">
														<div class="note-text">
															<span class="mandatory">E.g. ALWPG5809L</span>
														</div>
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<div class="select-style-1">

														<label for="fname">Current Designation <strong
															class="mandatory">*</strong></label>
														<div class="select-position">
															<select name="current_designation"
																id="current_designation" class="form-control valid"
																aria-invalid="false">
																<option value="0" selected="selected">-- Select
																	Designation --</option>

																<c:forEach var="item" items="${getDesignationList}"
																	varStatus="num">
																	<option value="${item.id}" name="${item.id}">${item.designation}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<div class="input-style-2">
														<label>Date of Joining<strong class="mandatory">*</strong></label>
														<input type="text" name="date_of_joining" id="date_of_joining"
															maxlength="10"
															class="form-control-sm form-control effect-9 "
															aria-required="true" autocomplete="off"
															value="DD/MM/YYYY">
													</div>
													<input type="hidden" id="doj" name="doj" value="">
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<div class="input-style-2">
														<label>Nature of Appointment<strong class="mandatory">*</strong></label>
														<input type="text" name="date_of_appoint" id="date_of_appoint"
															maxlength="10"
															class="form-control-sm form-control effect-9 "
															aria-required="true" autocomplete="off"
															value="DD/MM/YYYY">
													</div>
													<input type="hidden" id="doa" name="doa" value="">
												</div>
												</div>
												</div>
												<!-- 												<h4>Present Address</h4> -->
												
										
												
													<!-- 													<div class="col-md-12"> -->
													<!-- 														<div class="line_design"> -->
													<!-- 															<h6 class="line_text mb-25"> -->
													<!-- 																Permanent Address<strong class="mandatory">*</strong> -->
													<!-- 															</h6> -->
													<!-- 															<span class="line"></span> -->
													<!-- 														</div> -->
													<!-- 													</div> -->
													<div class="detail-block">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12">
														<div
															class="custom-data-value custom-title custom-title-bg">
															<h5 class="custom-title-tag">Present Address</h5>
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12 col-12 col-12">
														<div class="input-style-1">
															<label for="fname">Address Line 1 <strong
																class="mandatory">*</strong></label> <input type="text"
																name="per_add_line1" id="per_add_line1"
																class="form-control " placeholder="Address Line 1"
																maxlength="100" autocomplete="off">
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12 col-12 col-12">
														<div class="input-style-1">
															<label for="fname">Address Line 2 <strong
																class="mandatory">*</strong></label> <input type="text"
																name="per_add_line2" id="per_add_line2"
																class="form-control " placeholder="Address Line 2"
																maxlength="100" autocomplete="off">
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12 col-12 col-12">
														<div class="select-style-1">
															<label for="fname">State <strong
																class="mandatory">* </strong></label>
															<div class="select-position">
																<select class="form-control" name="per_state"
																	id="per_state">
																	<!-- style="text-transform: uppercase" -->
																	<option value="0" selected="selected">--Select
																		State --</option>
																	<c:forEach var="item" items="${getMedStateName}"
																		varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12 col-12 col-12">
														<div class="select-style-1">
															<label for="fname">District <strong
																class="mandatory">* </strong></label>
															<div class="select-position">
																<select class="form-control" name="per_district"
																	id="per_district">
																	<option value="0">--Select District--</option>
																</select>
															</div>
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12 col-12 col-12">
														<div class="input-style-1">
															<label for="fname">City/Village <strong
																class="mandatory">*</strong></label> <input type="text"
																name="per_village" id="per_village"
																class="form-control " placeholder="Enter City/Village"
																maxlength="100" autocomplete="off">
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12 col-12 col-12">
														<div class="input-style-1">
															<label for="fname">PinCode <strong
																class="mandatory">*</strong></label> <input type="text"
																name="per_pincode" id="per_pincode"
																class="form-control " placeholder="Enter PinCode"
																maxlength="6" autocomplete="off">
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12 col-12 col-12">
														<div class="input-style-1">
															<label for="fname">Phone No <strong
																class="mandatory"></strong></label> <input type="text"
																name="per_phn_no" id="per_phn_no" class="form-control "
																placeholder="Enter Phone No." maxlength="10"
																minlength="10" autocomplete="off">
														</div>
													</div>
													<!-- 													<div class="col-lg-12 col-md-12 col-sm-12"> -->
													<!-- 														<div -->
													<!-- 															class="custom-data-value custom-title custom-title-bg"> -->
													<!-- 															<h5 class="custom-title-tag">Correspondence Address</h5> -->
													<!-- 														</div> -->
													<!-- 													</div> -->
													<div class="col-lg-12 col-md-12 col-sm-12">

														<div class="custom-choose-one">
															<div class="input-style-form-check">

																<div class="form-check checkbox-style">
																	<input type="checkbox" class="form-check-input"
																		id="check_address" name="Choise" value="Upload">
																	<label for="check_address" class="form-check-label">Correspondence
																		Address</label>
																</div>

															</div>
														</div>

													</div>
													</div>
													</div>
													<div class="detail-block">
										<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12">
														<div
															class="custom-data-value custom-title custom-title-bg">
															<h5 class="custom-title-tag">Correspondence Address</h5>
														</div>
													</div>

													<div class="col-lg-4 col-md-6 col-sm-12 col-12 col-12">
														<div class="input-style-1">
															<label for="fname">Address Line 1 <strong
																class="mandatory">*</strong></label> <input type="text"
																name="present_add_line1" id="present_add_line1"
																class="form-control " placeholder="Enter Address-Line-1"
																maxlength="100" autocomplete="off">
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12 col-12 col-12">
														<div class="input-style-1">
															<label for="fname">Address Line 2 <strong
																class="mandatory">*</strong></label> <input type="text"
																name="present_add_line2" id="present_add_line2"
																class="form-control " placeholder="Enter Address-Line-2"
																maxlength="100" autocomplete="off">
														</div>
													</div>

													<div class="col-lg-4 col-md-6 col-sm-12 col-12 col-12">
														<div class="select-style-1">
															<label for="fname">State <strong
																class="mandatory">* </strong></label>
															<div class="select-position">
																<select name="present_state" id="present_state"
																	class="form-control ">
																	<!-- style="text-transform: uppercase" -->
																	<option value="0" selected="selected">--Select
																		State --</option>
																	<c:forEach var="item" items="${getMedStateName}"
																		varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12 col-12 col-12">
														<div class="select-style-1">
															<label for="fname">District <strong
																class="mandatory">* </strong></label>
															<div class="select-position">
																<select name="present_district" id="present_district"
																	class="form-control ">
																	<option value="0">--Select District--</option>
																</select>
															</div>
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12 col-12 col-12">
														<div class="input-style-1">
															<label for="fname">City/Village <strong
																class="mandatory">*</strong></label> <input type="text"
																name="present_village" id="present_village"
																class="form-control " placeholder="Enter City/Village"
																maxlength="100" autocomplete="off">
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12 col-12 col-12">
														<div class="input-style-1">
															<label for="fname">PinCode <strong
																class="mandatory">*</strong></label> <input type="text"
																name="present_pincode" id="present_pincode"
																class="form-control " placeholder="Enter PinCode"
																maxlength="6" autocomplete="off">
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12 col-12 col-12">
														<div class="input-style-1">
															<label for="fname">Phone No <strong
																class="mandatory"></strong></label> <input type="text"
																name="present_phn_no" id="present_phn_no"
																class="form-control " placeholder="Enter Phone No."
																maxlength="10" minlength="10" autocomplete="off">
														</div>
													</div>
												
</div>
</div>
											
											<!-- Bottom Button Start -->
											<div class="btn-bottom actionhide">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12">
														<ul class="buttons-group mainbtn">
															<li><input id="draft-saveper"
																class="main-btn info-btn btn-hover btnsave"
																type="button" value="Save As Draft"></li>
														</ul>
													</div>
												</div>
											</div>
											<!-- Bottom Button End -->
										</section>
										<h4>2. Qualification</h4>
										<section>
											<div class="detail-block">
										<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<div class="custom-data-value custom-title custom-title-bg">
														<h5 class="custom-title-tag">Medical Qualification</h5>
													</div>
												</div>
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<div class="table-wrapper table-responsive custom-table">
														<table class="table" id="addNameOfMed">
															<thead>
																<tr>
																	<th><h6>Sr No</h6></th>
																	<th><h6>
																			Name of Exam/Degree<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Course<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>Subject</h6></th>
																	<th><h6>
																			Name of Institute/College<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Name of Affiliated University<strong
																				class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Month & Year of Passing<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Doc. Attachments<strong class="mandatory">*</strong>
																		</h6></th>
																	<th class="actionhide"><h6>Action</h6></th>
																</tr>
																<!-- end table row-->
															</thead>
															<tbody id="att_TbbodyNameMed">
																<tr id="tr_id_attNameMed">
																	<td><p>1</p></td>
																	<td>

																		<div class="select-style-1 ">
																			<div class="select-position">
																				<select name="typeOfDegree1" id="typeOfDegree1"
																					class="form-control autocomplete">
																					<option value="0">--Select--</option>
																					<c:forEach var="item" items="${TypeOfDegree}"
																						varStatus="num">card-style
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																	</td>
																	<td>
																		<div class="select-style-1">
																			<div class="select-position">
																				<select name="course1" id="course1"
																					class="form-control autocomplete">
																					<option value="0">--Select--</option>
																					<%-- 																<c:forEach var="item" items="${getDegreeList}" varStatus="num">  --%>
																					<%-- 																	<option value="${item.id}" name="${item.course_name}">${item.course_name}</option>  --%>
																					<%-- 						 										</c:forEach>  --%>
																				</select>
																			</div>
																		</div>
																	</td>
																	<td>
																		<div class="select-style-1 "
																			id="hide_sub1">
																			<div class="select-position">
																				<select name="subject1" id="subject1"
																					class="form-control autocomplete">
																					<option value="0">--Select--</option>
																					<%-- 										<c:forEach var="item" --%>
																					<%-- 						items="${getSubjectList}" varStatus="num"> --%>
																					<%-- 						<option value="${item.id}" --%>
																					<%-- 						name="${item.subject_name}">${item.subject_name}</option> --%>
																					<%-- 						</c:forEach> --%>
																				</select>
																			</div>
																		</div>
																	</td>
																	<td>
																		<!-- 												getUniList -->


																		<div class="select-style-1">
																			<div class="select-position">
																				<select name="NameOfUniversity1"
																					id="NameOfUniversity1"
																					class="form-control form-control-lg form-control-a effect-9 select2 narrow wrap">
																					<option value="0">--Select--</option>
																					<c:forEach var="item" items="${getInsttituteList}"
																						varStatus="num">
																						<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</div> <!-- 													<div class="input-style-2"> --> <!-- 														<input type="text" id="NameOfUniversity1" name="NameOfUniversity1" value="" class="form-control autocomplete"  -->
																		<!-- 															autocomplete="off" maxlength="100" placeholder="Name of Institute/Board"> -->
																		<input type="hidden" id="qualtification_id1"
																		name="qualtification_id1" value="0"
																		class="form-control "> <!-- 													</div> -->
																	</td>
																	<td>

																		<div class="select-style-1">
																			<div class="select-position">
																				<select name="NameOfAffUni1" id="NameOfAffUni1"
																					class="form-control autocomplete">
																					<option value="0">--Select--</option>
																					<c:forEach var="item" items="${getUniList}"
																						varStatus="num">
																						<option value="${item.id}"
																							name="${item.university_name}">${item.university_name}</option>
																					</c:forEach>
																				</select>

																			</div>
																			<input type="text" id="universityother1"
																				name="universityother1"
																				class="form-control autocomplete d-none"
																				autocomplete="off"
																				placeholder="Enter Name of Institute" autocomplete="off">

																		</div> <!-- 													<div class="input-style-2"> --> <!-- 														<input type="text" id="NameOfAffUni1" name="NameOfAffUni1" value="" class="form-control autocomplete" -->
																		<!-- 															autocomplete="off" maxlength="100" placeholder="Name of Affiliated University"> -->
																		<!-- 													</div> -->
																	</td>
																	<td>
																		<div class="input-style-1">
																			<input type="month" id="monthYearOfDegree1"
																				name="monthYearOfDegree1"
																				class="form-control-sm form-control effect-9 hasDatepicker" />
																		</div>
																	</td>
																	<!-- 																			<td> -->

																	<!-- 																				<div class="input-style-2"> -->
																	<!-- 																					<input type="text" id="rollno1" -->
																	<!-- 																						name="rollno1" value="" -->
																	<!-- 																						class="form-control autocomplete" -->
																	<!-- 																						autocomplete="off" -->
																	<!-- 																						onkeypress="AutoCompleteNameOfUniversity(this);" -->
																	<!-- 																						placeholder="Enter Roll No"> -->

																	<!-- 																				</div>  -->
																	<!-- 																			</td> -->


																	<!-- 																			<td> -->

																	<!-- 																				<div class="input-style-2"> -->
																	<!-- 																					<input type="text" id="percentage1" -->
																	<!-- 																						name="percentage1" value="" -->
																	<!-- 																						class="form-control autocomplete" -->
																	<!-- 																						autocomplete="off" -->
																	<!-- 																						onkeypress="AutoCompleteNameOfUniversity(this);" -->
																	<!-- 																						placeholder="Enter Percentage"> -->

																	<!-- 																				</div>  -->
																	<!-- 																			</td> -->
																	<td>
																		<ul class="buttons-group">
																			<li><a href="#"
																				class="main-btn secondary-btn btn-hover btn-sm"
																				id="btnmodel1" value="Attachment">Attachment</a></li>
																		</ul> <!-- <div class="input-style-2">
													<input type="button" id="btnmodel1" class="main-btn secondary-btn btn-hover btn-save"
														value="Attachment">
												</div> -->
																	</td>
																	<td class="addminusbut">
																		<div class="action">
																			<ul class="buttons-group mainbtn">
																				<li><a
																					class="main-btn success-btn btn-hover btn-sm btnaddmore"
																					value="ADD" title="ADD" id="id_add_attNameMed1">
																						<i class="lni lni-plus"></i>
																				</a></li>
																			</ul>
																		</div>
																	</td>
																</tr>
																<!-- end table row -->
															</tbody>
														</table>
														<input type="hidden" id="count_hidden_att_name_med"
															name="count_hidden_att_name_med"
															class="form-control autocomplete" value="1"> <input
															type="hidden" id="count_hidden_att_name_med1"
															name="count_hidden_att_name_med1"
															class="form-control autocomplete" value="1">
														<!-- end table -->
													</div>


												</div>
												<!-- end row -->
												<!-- 							Dynamic modal                                               -->
												<div class="modal fade custom-modal bd-example-modal-lg"
													tabindex="-1" role="dialog"
													aria-labelledby="myLargeModalLabel" aria-hidden="true"
													id="modelWindow">
													<div
														class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable">
														<div class="modal-content">
															<div class="modal-header">
																<h3 class="modal-title" id="myLargeModalLabel">Upload
																	Attachments</h3>
																<button type="button" class="btn-close"
																	data-bs-dismiss="modal" aria-label="Close"></button>
															</div>
															<div class="modal-body custom-modal-table">
																<div class="custom-modal-inner">
																	<div
																		class="table-wrapper table-responsive custom-table">
																		<div id="dynamicDataTable"></div>
																	</div>
																</div>

															</div>
															<div class="modal-footer">
																<ul class="buttons-group">
																	<li><a type="button"
																		class="main-btn dark-btn n btn-hover"
																		data-bs-dismiss="modal">Close</a></li>
																</ul>
															</div>

														</div>
													</div>
												</div>


											</div>
                                                </div>
											<!-- 											<HARSH> -->

                                            <div class="detail-block">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<div class="custom-data-value custom-title custom-title-bg">
													<h5 class="custom-title-tag">Other Qualification</h5>
												</div>
											</div>


											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="addothquali">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>Name of Exam/Degree</h6></th>
																<th><h6>Subject</h6></th>
																<th><h6>Name of University/Institute</h6></th>
																<th><h6>Name of affiliated University</h6></th>
																<th><h6>Year of Passing</h6></th>
																<th><h6>Attachment</h6></th>
																<th class="actionhide"><h6>Action</h6></th>
															</tr>
															<!-- end table row-->
														</thead>
														<tbody id="att_TbbodyOthQuali">
															<tr id="tr_id_attOthQuali">
																<td><p>1</p></td>
																<td>
																	<div class="input-style-1">
																		<input type="text" id="OthnameofExDeg1"
																			name="OthnameofExDeg1"
																			class="form-control autocomplete" autocomplete="off"
																			placeholder="Enter Name of Exam/Degree" autocomplete="off">

																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" id="othSubject1" name="othSubject1"
																			class="form-control autocomplete" autocomplete="off"
																			placeholder="Enter Subject" autocomplete="off">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" id="othUniInst1" name="othUniInst1"
																			class="form-control autocomplete" autocomplete="off"
																			placeholder="Enter Name of University/Institute">
																	</div>
																</td>

																<td>
																	<div class="input-style-1">
																		<input type="text" id="othAffuni1" name="othAffuni1"
																			class="form-control autocomplete" autocomplete="off"
																			placeholder="Enter Name of affiliated University" autocomplete="off">
																		<input type="hidden" id="othquali_id1"
																			name="othquali_id1" value="0"
																			class="form-control autocomplete">
																	</div>
																</td>

																<td>
																	<div class="input-style-1">
																		<input type="month" id="othYrofpass1"
																			name="othYrofpass1"
																			class="form-control-sm form-control effect-9 hasDatepicker">
																	</div>
																</td>

																<td>

																	<ul class="buttons-group">
																		<li><a href="#"
																			class="main-btn secondary-btn btn-hover btn-sm"
																			id="btnmodelOth1" value="Attachment">Attachment</a></li>
																	</ul> <!-- <div class="input-style-1">
														<input type="button" id="btnmodelOth1"
															class="main-btn secondary-btn btn-hover btn-save"
															value="Attachment">
													</div> -->
																</td>

																<td class="min-width addminusbut">
																	<div class="action">
																		<ul class="buttons-group mainbtn">
																			<li><a
																				class="main-btn success-btn btn-hover btn-sm btnaddmore"
																				value="ADD" title="ADD" id="id_add_attOthQuali1"><i
																					class="lni lni-plus"></i></a></li>
																		</ul>
																	</div>
																</td>
															</tr>
															<!-- end table row -->



														</tbody>
													</table>

													<input type="hidden" id="count_hidden_att_oth_quali"
														name="count_hidden_att_oth_quali"
														class="form-control autocomplete" value="1"> <input
														type="hidden" id="count_hidden_att_oth_quali1"
														name="count_hidden_att_oth_quali1"
														class="form-control autocomplete" value="1">
													<!-- end table -->
												</div>

												<!-- end card -->
											</div>


											<!-- 							Dynamic modal                                               -->
											<div class="modal fade custom-modal bd-example-modal-lg"
												tabindex="-1" role="dialog"
												aria-labelledby="myLargeModalLabel" aria-hidden="true"
												id="oth_quali_mw">
												<div
													class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable">
													<div class="modal-content">
														<div class="modal-header">
															<h3 class="modal-title" id="myLargeModalLabel">Upload
																Attachments</h3>
															<button type="button" class="btn-close"
																data-bs-dismiss="modal" aria-label="Close"></button>
														</div>
														<div class="modal-body custom-modal-table">
															<div class="custom-modal-inner">
																<div class="table-wrapper table-responsive custom-table">
																	<div id="dynamicDataTableOQ"></div>
																</div>
															</div>

														</div>
														<div class="modal-footer">
															<ul class="buttons-group">
																<li><a type="button"
																	class="main-btn dark-btn n btn-hover"
																	data-bs-dismiss="modal">Close</a></li>
																<!-- 																<li><button type="button" -->
																<!-- 																		class="main-btn dark-btn btn-hover" -->
																<!-- 																		data-bs-dismiss="modal" data-dismiss="modal" -->
																<!-- 																		aria-label="Close">Close</button></li> -->
															</ul>
														</div>
													</div>
												</div>
											</div>
</div>
</div>
											<!-- Bottom Button Start -->
											<div class="btn-bottom actionhide">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12">
														<ul class="buttons-group mainbtn">

															<li><input id="draft-savequali"
																class="main-btn info-btn btn-hover btnsave"
																type="button" value="Save As Draft"></li>

														</ul>
													</div>
												</div>
											</div>

										</section>
										<h4>3. Registration Details</h4>
										<section>
											<div class="detail-block">
										<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<div class="custom-data-value custom-title custom-title-bg">
														<h5 class="custom-title-tag">Registration Details</h5>
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<div class="select-style-1">
														<label for="fname">Registration Type <strong
															class="mandatory">* </strong></label>
														<div class="select-position">
															<select name="registration_type" id="registration_type"
																class="form-control">
																<!-- style="text-transform: uppercase" -->
																<option value="0">--Select--</option>
																<!-- 												<option value="1" >State Registration</option> -->
																<!-- 												<option value="2" >Direct Registration</option> -->

																<c:forEach var="item" items="${getRegTypeList}"
																	varStatus="num">
																	<option value="${item.id}" name="${item.reg_type}">${item.reg_type}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
</div>
</div>
												<div id="sr" class="custom-d-none">
												<div class="detail-block">
													<div class="row">
														<div class="col-lg-12 col-md-12 col-sm-12">
															<div
																class="custom-data-value custom-title custom-title-bg">
																<h5 class="custom-title-tag">Central Registration</h5>
															</div>
														</div>
														<div class="col-lg-4 col-md-6 col-sm-12 col-12">
															<div class="input-style-1">
																<label for="fname"> Registration No <strong
																	class="mandatory"></strong></label> <input type="text"
																	name="central_reg_no" id="central_reg_no"
																	class="form-control"
																	placeholder="Enter Central Registration No."
																	maxlength="50" autocomplete="off">
															</div>
														</div>


														<div class="col-lg-4 col-md-6 col-sm-12 col-12">
															<div class="input-style-2">
																<label>Date of Registration<strong
																	class="mandatory"></strong>
																</label> <input type="text" name="cs_date_of_reg"
																	id="cs_date_of_reg" maxlength="10" class="form-control"
																	aria-required="true" autocomplete="off"
																	value="DD/MM/YYYY"
																	placeholder="Select Date of Registration">
															</div>
														</div>

													</div>
                                                     </div>
                                                     <div class="detail-block">
													<div class="row">
														<div class="col-lg-12 col-md-12 col-sm-12">
															<div
																class="custom-data-value custom-title custom-title-bg">
																<h5 class="custom-title-tag">State Registration</h5>
															</div>
														</div>
														<div class="col-lg-4 col-md-6 col-sm-12 col-12">
															<div class="input-style-1">
																<label for="fname">State Registration No <strong
																	class="mandatory">*</strong></label> <input type="text"
																	name="state_reg_no" id="state_reg_no"
																	class="form-control"
																	placeholder="Enter State Registration No."
																	maxlength="50" autocomplete="off">
															</div>
														</div>


														<div class="col-lg-4 col-md-6 col-sm-12 col-12">
															<div class="select-style-1">
																<label for="fname">Name of State Board <strong
																	class="mandatory">* </strong></label>
																<div class="select-position">
																	<select name="state_board_name" id="state_board_name"
																		class="state_board_name" autocomplete="off">
																		<!-- style="text-transform: uppercase" -->
																		<option value="0">--Select--</option>

																		<c:forEach var="item" items="${getMedStateName}"
																			varStatus="num">
																			<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</div>




														<div class="col-lg-4 col-md-6 col-sm-12 col-12">
															<div class="input-style-1">
																<label>Date of Registration<strong
																	class="mandatory">*</strong>
																</label> <input type="text" name="date_of_reg" id="date_of_reg"
																	maxlength="10" class="form-control"
																	aria-required="true" autocomplete="off"
																	value="DD/MM/YYYY"
																	placeholder="Select Date of Registration">
															</div>
														</div>



														<div class="col-lg-4 col-md-6 col-sm-12 col-12">
															<div class="input-style-2">
																<label>Valid Upto<strong class="mandatory">*</strong>
																</label> <input type="text" name="state_validity_upto"
																	id=state_validity_upto maxlength="10"
																	class="form-control" aria-required="true"
																	autocomplete="off" value="DD/MM/YYYY"
																	placeholder="Select Date of Validity Upto">
															</div>
														</div>

													</div>

													<div class="row">
														<div class="col-lg-12 col-md-12 col-sm-12">
															<div
																class="custom-data-value custom-title custom-title-bg">
																<h5 class="custom-title-tag">Enroll in other State</h5>
															</div>
														</div>
														<div class="col-lg-4 col-md-6 col-sm-12 col-12">
															<div class="input-style-1">
																<label for="fname"> Registration No <strong
																	class="mandatory"></strong></label> <input type="text"
																	name="adjunct_registration_no"
																	id="adjunct_registration_no" class="form-control"
																	placeholder="Enter Adjunct Registration No."
																	maxlength="50" autocomplete="off">
															</div>
														</div>


														<!-- 						             <div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
														<!-- 							                <div class="input-style-2"> -->
														<!-- 							                  <label for="fname"> State Name <strong class="mandatory"></strong></label> -->
														<!-- 							           <input type="text" name="adjunct_state_no" id="adjunct_state_no" class="form-control" placeholder="Enter Adjunct State Name" -->
														<!-- 							                                  maxlength="50"></div> -->
														<!-- 							                </div> -->
														<div class="col-lg-4 col-md-6 col-sm-12 col-12">
															<div class="select-style-1">
																<label for="fname">State Name <strong
																	class="mandatory"> </strong></label>
																<div class="select-position">
																	<select name="adjunct_state_no" id="adjunct_state_no"
																		class="form-control">
																		<!-- style="text-transform: uppercase" -->
																		<option value="0">--Select--</option>

																		<c:forEach var="item" items="${getMedStateName}"
																			varStatus="num">
																			<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</div>



														<div class="col-lg-4 col-md-6 col-sm-12 col-12">
															<div class="input-style-2">
																<label>Date of Registration<strong
																	class="mandatory"></strong>
																</label> <input type="text" name="other_date_of_reg"
																	id="other_date_of_reg" maxlength="10"
																	class="form-control" aria-required="true"
																	autocomplete="off" value="DD/MM/YYYY"
																	placeholder="Select Date of Registration">
															</div>
														</div>



														<div class="col-lg-4 col-md-6 col-sm-12 col-12">
															<div class="input-style-2">
																<label>Valid Upto<strong class="mandatory"></strong>
																</label> <input type="text" name="other_validity_upto"
																	id=other_validity_upto maxlength="10"
																	class="form-control" aria-required="true"
																	autocomplete="off" value="DD/MM/YYYY"
																	placeholder="Select Date of Validity Upto">
															</div>
														</div>

													</div>

                                                      </div>
												</div>


												<div id="dr" class="custom-d-none">
												<div class="detail-block">
													<div class="row">
														<div class="col-lg-12 col-md-12 col-sm-12">
															<div
																class="custom-data-value custom-title custom-title-bg">
																<h5 class="custom-title-tag">Direct Registration</h5>
															</div>
														</div>
														<div class="col-lg-4 col-md-6 col-sm-12 col-12">
															<div class="input-style-1">
																<label for="fname">Direct Registration No <strong
																	class="mandatory">*</strong></label> <input type="text"
																	name="direct_reg_no" id="direct_reg_no"
																	class="form-control"
																	placeholder="Enter Direct Registration No."
																	maxlength="50" autocomplete="off">
															</div>
														</div>

														<div class="col-lg-4 col-md-6 col-sm-12 col-12">
															<div class="input-style-2">
																<label for="fname">Date of Registration <strong
																	class="mandatory">*</strong></label> <input type="text"
																	name="direct_date_of_reg" id="direct_date_of_reg"
																	class="form-control" maxlength="10"
																	class="form-control" aria-required="true"
																	autocomplete="off" value="DD/MM/YYYY"
																	placeholder="Select Date of Registration">
															</div>
														</div>
														<div class="col-lg-4 col-md-6 col-sm-12 col-12">
															<div class="input-style-1">
																<label for="fname">Name of
																	Department/Organization <strong class="mandatory">*</strong>
																</label> <input type="text" id="name_of_department"
																	maxlength="50" name="name_of_department" value=""
																	class="form-control autocomplete" autocomplete="off"
																	placeholder="Name of Department/organization" >

															</div>
														</div>
														<div class="col-lg-4 col-md-6 col-sm-12 col-12">
															<div class="input-style-2">
																<label>Validity Upto<strong class="mandatory">*</strong>
																</label> <input type="text" name="direct_validity_upto"
																	id=direct_validity_upto maxlength="10"
																	class="form-control" aria-required="true"
																	autocomplete="off" value="DD/MM/YYYY"
																	placeholder="Select Date of Validity Upto">
															</div>
														</div>
														<!-- <div class="col-lg-6 col-md-6 col-sm-12">
																	<div class="input-style-2">
																		<label for="fname">Validity Upto <strong
																			class="mandatory">*</strong></label> <input type="text"
																			id="direct_validity_upto" name="direct_validity_upto"
																			value="" class="form-control autocomplete"
																			autocomplete="off"
																			onkeypress="AutoCompleteNameOfUniversity(this);"
																			placeholder="Enter Validity">

																	</div>
																</div> -->
													</div>
													</div>
												</div>
											
											<div class="btn-bottom actionhide">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12">
														<ul class="buttons-group mainbtn">
															<li><input id="draft-savereg"
																class="main-btn info-btn btn-hover btnsave"
																type="button" value="Save As Draft"></li>
														</ul>
													</div>
												</div>
											</div>

										</section>
										<h4>4. Academic Experience</h4>
										<section>
                                                  <div class="detail-block">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<div class="custom-data-value custom-title custom-title-bg">
														<h5 class="custom-title-tag">Academic Experience</h5>
													</div>
												</div>
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<div class="table-wrapper table-responsive custom-table">
														<table class="table" id="att_Tb">
															<thead>
																<tr>
																	<th><h6>Sr No</h6></th>
																	<th><h6>
																			Organization Name<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Department Name<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Designation / Post<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Employment Type<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			From Date<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			To Date<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Year of Experience<strong class="mandatory"></strong>
																		</h6></th>
																	<th><h6>
																			Honorarium<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Upload File<strong class="mandatory">*</strong><span
																				class="mandatory table-note">Maximum file
																				size upto 200 kb and support file extension .pdf</span>
																		</h6></th>
																	<th class="actionhide"><h6>Action</h6></th>
																</tr>
																<!-- end table row-->
															</thead>
															<tbody id="att_Tbbody">
																<tr id="tr_id_att">
																	<td><p>1</p></td>
																	<td>
																		<div class="input-style-1">
																			<input type="text" id="institute_name1"
																				name="institute_name1" class="form-control"
																				autocomplete="off" maxlength="100"
																				placeholder="Enter Organization Name"> <input
																				type="hidden" id="profession_id1"
																				name="profession_id1" value="0" class="form-control"
																				autocomplete="off" maxlength="100">
																		</div>
																	</td>
																	<td>
																		<div class="select-style-1">
																			<div class="select-position">
																				<select name="depart_name1" id="depart_name1"
																					class="form-control valid" aria-invalid="false">
																					<option value="0" selected="selected">--
																						Select Department --</option>
																					<!-- 																				<option value="1">AYURVEDA</option> -->
																					<!-- 																				<option value="2">UNANI</option> -->
																					<!-- 																				<option value="3">SIDDHA</option> -->
																					<!-- 																				<option value="4">SOWA-RIGPA</option> -->

																					<c:forEach var="item" items="${getSystemList}"
																						varStatus="num">
																						<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																	</td>
																	<td>

																		<div class="select-style-1">
																			<div class="select-position">
																				<select name="desig1" id="desig1"
																					class="form-control valid" aria-invalid="false">
																					<option value="0" selected="selected">--
																						Select Designation --</option>

																					<c:forEach var="item" items="${getDesignationList}"
																						varStatus="num">
																						<option value="${item.id}" name="${item.id}">${item.designation}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																	</td>

																	<td>
																		<div class="select-style-1">
																			<div class="select-position">
																				<select name="employment_type1"
																					id="employment_type1" class="form-control valid"
																					aria-invalid="false">
																					<option value="0" selected="selected">--
																						Select Employement Type --</option>
																					<!-- 																				<option value="1">Full Time</option> -->
																					<!-- 																				<option value="2">Part Time</option> -->
																					<!-- 																				<option value="3">Guest Faculty</option> -->
																					<c:forEach var="item"
																						items="${getemploymnt_typeList}" varStatus="num">
																						<option value="${item.id}" name="${item.id}">${item.empl_type}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																	</td>

																	<td>
																		<div class="input-style-2">
																			<input type="text" name="from_date1" id="from_date1"
																				maxlength="10" class="form-control valid"
																				aria-required="true" autocomplete="off"
																				value="DD/MM/YYYY" placeholder="Select From Date">
																		</div>
																	</td>
																	<td>
																		<div class="input-style-2">
																			<input type="text" name="to_date1" id="to_date1"
																				maxlength="10" class="form-control"
																				aria-required="true" autocomplete="off"
																				value="DD/MM/YYYY" placeholder="Select To Date">

																		</div>
																	</td>
																	<td>
																		<div class="input-style-1">
																			<input id="yr_of_exp1" name="yr_of_exp1"
																				class="form-control" autocomplete="off"
																				readonly="readonly" maxlength="100"
																				placeholder="Year of Experience">
																		</div>
																	</td>

																	<td>
																		<div class="select-style-1">
																			<div class="select-position">
																				<select name="honorarium1" id="honorarium1"
																					class="form-control valid" aria-invalid="false">
																					<option value="0" selected="selected">--
																						Select Salary Paid --</option>
																					<!-- 																				<option value="1">Yes</option> -->
																					<!-- 																				<option value="2">No</option> -->
																					<c:forEach var="item"
																						items="${getsalary_statusList}" varStatus="num">
																						<option value="${item.id}" name="${item.id}">${item.sal_status}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																	</td>
																	<td>
																		<div class="input-style-1">
																			<input type="file" accept=".pdf" id="upload_file1"
																				name="upload_file1" class="form-control"> <input
																				type="hidden" id="upload_file_hid1"
																				name="upload_file_hid1" class="form-control">
																			<div class="note-text">
																				<span class="errorClass " id="upload_file_lbl">${exp_path_msg}</span>
																				<span class='tikClass ' id="upload_file_lbltik"></span>
																			</div>
																		</div>
																	</td>
																	<td class="actionhide">
																		<ul class="buttons-group mainbtn action">
																			<li><a
																				class="main-btn success-btn btn-hover btn-sm btnaddmore"
																				value="ADD" title="ADD" id="id_add_att1"> <i
																					class="lni lni-plus"></i></a></li>
																		</ul>
																	</td>
																</tr>
																<!-- end table row -->
															</tbody>
														</table>
														<!-- end table -->
													</div>
													<!-- end card -->
												</div>
												<!-- end col -->
											</div>
											</div>
											<!-- end row -->


											<div class="btn-bottom actionhide">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12">
														<ul class="buttons-group mainbtn">
															<li><input id="draft-saveacademic"
																class="main-btn info-btn btn-hover btnsave"
																type="button" value="Save As Draft"></li>
														</ul>
													</div>
												</div>
											</div>

										</section>
										<h4>5. Profession Experience</h4>
										<section>

                                               <div class="detail-block">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<div class="custom-data-value custom-title custom-title-bg">
														<h5 class="custom-title-tag">Profession Experience</h5>
													</div>
												</div>
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<div class="table-wrapper table-responsive custom-table">
														<table class="table" id="att_Tb_pro">
															<thead>
																<tr>
																	<th><h6>Sr No</h6></th>
																	<th><h6>
																			Organization Name<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			From Date<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			To Date<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Year of Experience<strong class="mandatory"></strong>
																		</h6></th>
																	<th><h6>
																			Designation<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Type of Experience<strong class="mandatory">*</strong>
																		</h6></th>


																	<th class="actionhide"><h6>Action</h6></th>
																</tr>
																<!-- end table row-->
															</thead>
															<tbody id="att_Tbbody_pro">
																<tr id="tr_id_att_pro">
																	<td><p>1</p></td>
																	<td>
																		<div class="input-style-1">
																			<input type="text" id="organization_name1"
																				name="organization_name1" class="form-control"
																				autocomplete="off" maxlength="100"
																				placeholder="Enter Organization Name"> <input
																				type="hidden" id="profession_exp_id1"
																				name="profession_exp_id1" value="0"
																				class="form-control" autocomplete="off"
																				maxlength="100">
																		</div>
																	</td>


																	<td>
																		<div class="input-style-2">
																			<input type="text" name="from_date_pro1"
																				id="from_date_pro1" maxlength="10"
																				class="form-control valid" aria-required="true"
																				autocomplete="off" value="DD/MM/YYYY"
																				placeholder="Select From Date">
																		</div>
																	</td>
																	<td>
																		<div class="input-style-2">
																			<input type="text" name="to_date_pro1"
																				id="to_date_pro1" maxlength="10"
																				class="form-control" aria-required="true"
																				autocomplete="off" value="DD/MM/YYYY"
																				placeholder="Select To Date">

																		</div>
																	</td>
																	<td>
																		<div class="input-style-1">
																			<input id="yr_of_exp_pro1" name="yr_of_exp_pro1"
																				class="form-control" autocomplete="off"
																				readonly="readonly" maxlength="100"
																				placeholder="Year of Experience">
																		</div>
																	</td>


																	<td>
																		<div class="input-style-1">
																			<input type="text" id="designation_name1"
																				name="designation_name1" class="form-control"
																				autocomplete="off" maxlength="100"
																				placeholder="Enter Designation Name" >


																		</div>
																	</td>

																	<td>
																		<!--                                   						<div class="col-lg-4 col-md-6 col-sm-12 col-12 col-12"> -->
																		<div class="select-style-1">
																			<div class="select-position">
																				<select class="form-control" name="type_of_exp1"
																					id="type_of_exp1">
																					<option value="0" selected="selected">--Select
																						Experience --</option>
																					<c:forEach var="item" items="${Type_of_exp}"
																						varStatus="num">
																						<option value="${item.id}" name="${item.id}">${item.type_of_experience}</option>
																					</c:forEach>
																					<option value="-1" name="OTHER">OTHER</option>
																				</select>
																			</div>
																			<input type="text" id="experienceother1"
																				name="experienceother1"
																				class="form-control autocomplete d-none"
																				autocomplete="off"
																				placeholder="Enter Type Of Experience">

																		</div>



																	</td>




																	<td class="actionhide">
																		<ul class="buttons-group mainbtn action">
																			<li><a
																				class="main-btn success-btn btn-hover btn-sm btnaddmore"
																				value="ADD" title="ADD" id="id_add_att_pro1"> <i
																					class="lni lni-plus"></i>
																			</a></li>
																		</ul>
																	</td>
																</tr>
																<!-- end table row -->
															</tbody>
														</table>
														<!-- end table -->
													</div>
													<!-- end card -->
												</div>
												<!-- end col -->
											</div>
											
											<!-- end row -->
											<input type="hidden" id="count_hidden_att_pro"
												name="count_hidden_att_pro"
												class="form-control autocomplete" value="1"> <input
												type="hidden" id="count_hidden_att_pro1"
												name="count_hidden_att_pro1"
												class="form-control autocomplete" value="1"> <input
												type="hidden" id="academic_exp1" name="academic_exp1"
												value="0" class="form-control" autocomplete="off"
												maxlength="100">
												</div>
											<div class="btn-bottom actionhide">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12">
														<ul class="buttons-group mainbtn">
															<li><input id="draft-savetype_of_exp"
																class="main-btn info-btn btn-hover btnsave"
																type="button" value="Save As Draft"></li>
														</ul>
													</div>
												</div>
											</div>



										</section>
										<h4>6. Additional Documents</h4>
										<section>
										<div class="detail-block">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<div class="custom-data-value custom-title custom-title-bg">
													<h5 class="custom-title-tag">Additional Documents</h5>
												</div>
											</div>

											
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<div class="table-wrapper table-responsive custom-table">
														<table class="table" id="doc_Tb">
															<thead>
																<tr>
																	<th><h6>Sr No</h6></th>
																	<th><h6>
																			Document Name<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Document Type<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Upload File<strong class="mandatory">*</strong>
																		</h6> <span class="mandatory table-note">Maximum
																			file size upto 200 kb and support file extension .pdf</span></th>
																	<th class="actionhide"><h6>Action</h6></th>
																</tr>
																<!-- end table row-->
															</thead>
															<tbody id="doc_Tbbody">
																<tr id="tr_id_doc">
																	<td><p>1</p></td>
																	<td>
																		<div class="select-style-1">
																			<div class="select-position">
																				<select name="doc_name1" id="doc_name1"
																					class="form-control">
																					<option value="0">--Select--</option>
																					<c:forEach var="item" items="${getDocList}"
																						varStatus="num">
																						<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																					</c:forEach>
																				</select> <input type="hidden" id="add_quali1"
																					name="add_quali1" value="0" class="form-control"
																					autocomplete="off" maxlength="100">
																			</div>
																		</div> <!-- 	</div> -->
																	</td>
																	<td>
																		<div class="select-style-1">
																			<div class="select-position">
																				<select name="doc_id1" id="doc_id1"
																					class="form-control">
																					<option value="0">--Select--</option>
																				</select>
																			</div>
																		</div>
																	</td>
																	<td>
																		<div class="input-style-1">
																			<input type="file" accept="" id="upload_doc1"
																				name="upload_doc1" class="form-control"> <input
																				type="hidden" id="doc_upload_hid1"
																				name="doc_upload_hid1" class="form-control">
																			<div class="note-text">
																				<span class="errorClass " id="doc_upload_lbl">${exp_path_msg}</span>
																				<span class='tikClass ' id="doc_upload_lbltik"></span>
																			</div>
																		</div>
																	</td>
																	<td class="min-width addminusbut">
																		<ul class="buttons-group mainbtn action">
																			<li><a
																				class="main-btn success-btn btn-hover btn-sm btnaddmore"
																				value="ADD" title="ADD" id="id_add_doc1"> <i
																					class="lni lni-plus"></i></a></li>
																		</ul>
																	</td>
																</tr>
																<!-- end table row -->

															</tbody>
														</table>
														<!-- end table -->
													</div>
													<!-- end card -->
												</div>
												<!-- end col -->
											</div>
											</div>
											<!-- end row -->

											<div class="btn-bottom actionhide">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12">
														<ul class="buttons-group mainbtn">
															<li><input id="draft-saveexp"
																class="main-btn info-btn btn-hover btnsave"
																type="button" value="Save As Draft"></li>
														</ul>
													</div>
												</div>
											</div>
										</section>
										<h4>7. Confirmation</h4>
										<section>
										<div class="detail-block">
											<div class="row">

												<div class="col-lg-12 col-md-12 col-sm-12 d-none" id="instruction">
													<div class="alert alert-info" id="notrahu"></div>
												</div>
												<!-- <span id="notrahu" class="mandatory"> </span>  -->
												<div class="col-lg-12 col-md-12 col-sm-12">
													<div class="inst_block simple-instruction" id="note">
														<strong>Instruction :</strong> After final submit you
														would not be able to update any details.

													</div>
												</div>
												<div class="col-lg-12 col-md-12 col-sm-12">

													<h5 class="text-medium text-medium mb-10">Declaration</h5>
													<div class="custom-choose-one">
														<div class="input-style-form-check_block check-multi-list">
															<div class="form-check checkbox-style">
																<input type="checkbox" id="checkbox1" name="checkbox1"
																	class="form-check-input"> <label
																	for="checkbox1" class="check-list">I hereby
																	confirm that the information provided herein is
																	accurate, correct and complete and that the documents
																	submitted along with this application form are genuine.
																	I understand and agree that this declaration is final
																	and irrevocable, and that it is not subject to
																	cancellation .</label> <label for="checkbox1"
																	class="check-list">This is to inform that, The
																	information provided can't be changed. Provision for
																	editing information won't be available once data is
																	submitted. It is Requested to fill all the details
																	carefully.</label>
																	<label for="checkbox1"
																	class="check-list">After final submit you would not be able to update any details.</label>
															</div>
														</div>
													</div>

												</div>

											</div>


											<input type="hidden" id="count_hidden_att"
												name="count_hidden_att" class="form-control autocomplete"
												value="1"> <input type="hidden" id="hiddenUpdate"
												name="hiddenUpdate" class="form-control autocomplete"
												value="0"> <input type="hidden"
												id="count_hidden_doc" name="count_hidden_doc"
												class="form-control autocomplete" value="1">
											<!--                                      <input type="hidden" id="count_hidden_doc" name="count_hidden_doc" class="form-control autocomplete" value="1"> -->
											</div>
											
											
											<ul class="buttons-group mainbtn">
									<li>
										<a  class="main-btn dark-btn btn-hover btn-iconic-icon btnview d-none" id="viewData">
										<i class="lni lni-eye"></i>View Faculty Details</a>
										</li>
										</ul>
											
											
										</section>

									</div>


									

																		<ul class="buttons-group mainbtn">

									<!-- 										<li class="savelist" id="save_li"> -->
									<!-- 										</li> -->
									<!-- 										<li class="savelist" id="rest_li"><a -->
									<!-- 											href="Teacher_dtl_Url" -->
									<!-- 											class="main-btn dark-btn n btn-hover d-none" type="button">Reset</a> -->
									<!-- 										</li> -->
																			<li class="custom-d-none"><input id="btn-save"
																				class="main-btn success-btn btn-hover d-none" type="submit"
																				value="Save"> <input id="draftBtn"
																				class="main-btn info-btn btn-hover d-none" type="submit"
																				value="Save"></li>
									<!-- 										<li></li> -->
																		</ul>

									<input type="hidden" name="data_fetch"
										class="form-control autocomplete" value="0"> <input
										type="hidden" id="teachercode_hidden"
										name="teachercode_hidden" class="form-control autocomplete"
										value="0"> <input type="hidden" id="id_hidden"
										name="id_hidden" class="form-control autocomplete" value="0">
									<input type="hidden" id="ayushid_hidden" name="ayushid_hidden"
										class="form-control autocomplete" value="0">
								</form>
							</div>
						</div>
					</div>
					<!-- #/ container -->

				</div>
			</div>
		</div>
	</div>
</section>

<c:url value="View_Teacher_dtlUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
 	name="viewForm" modelAttribute="id6"> 
	<input type="hidden" name="id6" id="id6" value="0" />
	<input type="hidden" name="statusview" id="statusview" value="0" />
</form:form> 


<script type="text/javascript" src="js/stepform/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/stepform/jquery.steps.min.js"></script>
<script type="text/javascript" src="js/stepform/jquery-steps-init.js"></script>

<script nonce="${cspNonce}" type="text/javascript">

function datepicketDateCustom(inpt){
	$('#'+inpt).datepicker({showOn: 'both',
//	buttonImageOnly: false,
//	buttonImage: 'js/Calender/calendar.png',			
	buttonText: '<span class="icon"><i class="lni lni-calendar"></i></span>',
	showButtonPanel: false,
	dateFormat: 'dd/mm/yy',
	changeMonth: true,
	changeYear: true,
	yearRange: '1890:2099',
	//maxDate: new Date()
});

//$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});

}

$(document).ready(function() {
	
	 $("#step-form-horizontal").on("keypress", function (event) {
         var keyPressed = event.keyCode || event.which;
         if (keyPressed === 13) {
             event.preventDefault();
             return false;
         }
     });
	 
	 
// 	 //
// 	var hiddenfie= $('#perentId').val();
// 	 if(hiddenfie=='0'){
		 
// 		 $("#draft-savequali").hide();
// 		 $("#draft-savereg").hide();
// 		 $("#draft-saveacademic").hide();
// 		 $("#draft-saveexp").hide();
// 		 $('a[href="#finish"]').attr("style", "display: none;"); 
// 	 }else{
		 
// 		 $("#draft-savequali").show();
// 		 $("#draft-savereg").show();
// 		 $("#draft-saveacademic").show();
// 		 $("#draft-saveexp").show();
// 		 $('a[href="#finish"]').attr("style", "display:block;");
		 
// 	 }
	 
	 
	 $('#step-form-horizontal').validate({ // initialize the plugin
         rules: {
        	 gender:{ required: true},
      }
   });
	 
	 
	 var date = new Date();
	 var today = date.toISOString().substring(0,7);
	 $("#monthYearOfDegree1").attr('max',today);
	 $("#othYrofpass1").attr('max',today);
	 
	
	 $("#update-button").hide();
	 
		$.ajaxSetup({
			async : false
		});
		

						 datepicketDate('date_of_birth');
						 datepicketDate('date_of_joining');
						 datepicketDate('date_of_appoint');
						datepicketDate('from_date1');
						datepicketDate('to_date1');
						datepicketDate('from_date_pro1');
						datepicketDate('to_date_pro1');
						datepicketDate('date_of_reg');
						datepicketDate('direct_date_of_reg');
						datepicketDate('state_validity_upto');
						datepicketDate('direct_validity_upto');
						datepicketDate('cs_date_of_reg');
						datepicketDate('other_date_of_reg');
						datepicketDate('other_validity_upto');
						
						$( "#state_validity_upto" ).datepicker("option", "maxDate", null);
						$( "#direct_validity_upto" ).datepicker("option", "maxDate", null);
						$( "#other_validity_upto" ).datepicker("option", "maxDate", null);
						login_fetch();			
var data =  '${data}';

if(data != "[]"){
	debugger;
	$("#data_fetch").val(1);
	
	$("#teachercode_hidden").val('${data[0].teacher_code}');
	$("#perentId").val('${data[0].id}');
	$("#ayushid_hidden").val('${data[0].ayush_id}');
	$("#id_hidden").val('${data[0].mainid}');
	
// 	alert('${data[0].mainid}');



	
	$("#first_name").val('${data[0].first_name}');
	$("#middle_name").val('${data[0].middle_name}');
	$("#last_name").val('${data[0].last_name}');
	$("#gender").val('${data[0].gender}');
 	$("#id").val('${data[0].id}');
 	
 	var date_of_birth = '${data[0].date_of_birth}';
    var dot= date_of_birth.substring(0,10);
    var dot_y = dot.substring(0,4);
    var dot_m = dot.substring(5,7);
    var dot_d = dot.substring(8,10);
     var dot_t = dot_d+"/"+dot_m+"/"+dot_y;
    $("#date_of_birth").val(dot_t);
    
    var date_of_joining = '${data[0].date_of_joining}';
    var dot= date_of_joining.substring(0,10);
    var dot_y = dot.substring(0,4);
    var dot_m = dot.substring(5,7);
    var dot_d = dot.substring(8,10);
     var dot_t = dot_d+"/"+dot_m+"/"+dot_y;
    $("#date_of_joining").val(dot_t);
    
    var date_of_appoint = '${data[0].nature_of_appointment}';
    var dot= date_of_joining.substring(0,10);
    var dot_y = dot.substring(0,4);
    var dot_m = dot.substring(5,7);
    var dot_d = dot.substring(8,10);
     var dot_t = dot_d+"/"+dot_m+"/"+dot_y;
    $("#date_of_appoint").val(dot_t);
    
    
    calculate_age("date_of_birth");
    changedatepikettodob(dot_t);
	
    $("#father_name").val('${data[0].father_name}');
	$("#mother_name").val('${data[0].mother_name}');
	$("#spouse_name").val('${data[0].spouse_name}');
	$("#mobile_no").val('${data[0].mobile_no}');
	$("#email").val('${data[0].email}');
	
	
	
	var adhar = '${data[0].aadhar_no}';
	var a1 = adhar.substring(0,4);
		var a2 = adhar.substring(4,8);
		var a3 = adhar.substring(8,12);
		$("#aadhar_no1").val(a1);
		$("#aadhar_no2").val(a2);
		$("#aadhar_no3").val(a3);
		
		$("#pan_no").val('${data[0].pan_no}');
		$("#current_designation").val('${data[0].current_designation}');
		$('#academic_quali').val('${data[0].academic_quali}');
		$("#academic_quali").change();
		$("#per_add_line1").val('${data[0].per_add_line1}');
		$("#per_add_line2").val('${data[0].per_add_line2}');
		$("#per_village").val('${data[0].per_village}');
		
		$('#per_state').val('${data[0].per_state}');
// 		$("#per_state").change();
		getDistrict();
		$('#per_district').val('${data[0].per_district}');
		$("#per_district").change();
		
		$("#per_pincode").val('${data[0].per_pincode}');
		$("#per_phn_no").val('${data[0].per_phn_no}');
		
		if("${data[0].present_add_line1}" == "${data[0].per_add_line1}" && "${data[0].present_add_line2}" == "${data[0].per_add_line2}" && "${data[0].present_village}" == "${data[0].per_village}"
			&& "${data[0].per_state}" == "${data[0].present_state}" && "${data[0].per_district}" == "${data[0].present_district}" 
			&& "${data[0].per_pincode}" == "${data[0].present_pincode}" && "${data[0].per_phn_no}" == "${data[0].present_phn_no}"){
	$("#check_address").prop("checked", true);
	
	
	$("#present_add_line1").attr('readonly',true);
	$("#present_add_line2").attr('readonly',true);
	$("#present_village").attr('readonly',true);
	$("#present_state").attr('readonly',true);
	$("#present_district").attr('readonly',true);
	$("#present_pincode").attr('readonly',true);
	$("#present_phn_no").attr('readonly',true);
	
	
	 }
		
		$("#present_add_line1").val('${data[0].present_add_line1}');
		$("#present_add_line2").val('${data[0].present_add_line2}');
		$("#present_village").val('${data[0].present_village}');
		
		$("#present_state").val('${data[0].present_state}');
// 		$("#present_state").change();
		getDistrict_present();
		$("#present_district").val('${data[0].present_district}');
		$("#present_district").change();
		
		$("#present_pincode").val('${data[0].present_pincode}');
		$("#present_phn_no").val('${data[0].present_phn_no}');
		
////////////////////mirangi_21_07_22///////////Start	

		if('${data[0].registration_type}'!=null && '${data[0].registration_type}'!=""){
			$("#registration_type").val('${data[0].registration_type}');
		}else{
			$("#registration_type").val('0');
		}
		//
		if('${data[0].registration_type}'== 1 ){
			$("#state_reg_no").val('${data[0].state_reg_no}');
			$("select#state_board_name").val('${data[0].state_board_name}');
			if('${data[0].date_of_reg}'!=""){
			var date_of_reg = '${data[0].date_of_reg}';
	        var dob= date_of_reg.substring(0,10);
	        var dob_y = dob.substring(0,4);
	        var dob_m = dob.substring(5,7);
	        var dob_d = dob.substring(8,10);
	         var dob_t = dob_d+"/"+dob_m+"/"+dob_y;
	        $("#date_of_reg").val(dob_t);
			}
	        //rahul
	        if('${data[0].cs_date_of_reg}'!=""){
	        var cs_date_of_reg = '${data[0].cs_date_of_reg}';
	        var dob= cs_date_of_reg.substring(0,10);
	        var dob_y = dob.substring(0,4);
	        var dob_m = dob.substring(5,7);
	        var dob_d = dob.substring(8,10);
	         var dob_t = dob_d+"/"+dob_m+"/"+dob_y;
	        $("#cs_date_of_reg").val(dob_t);
	        }
	        if('${data[0].other_date_of_reg}'!=""){
	        var other_date_of_reg = '${data[0].other_date_of_reg}';
	        var dob= other_date_of_reg.substring(0,10);
	        var dob_y = dob.substring(0,4);
	        var dob_m = dob.substring(5,7);
	        var dob_d = dob.substring(8,10);
	         var dob_t = dob_d+"/"+dob_m+"/"+dob_y;
	        $("#other_date_of_reg").val(dob_t);
	        }
	        if('${data[0].other_validity_upto}'!=""){
	        var other_validity_upto = '${data[0].other_validity_upto}';
	        var dob= other_validity_upto.substring(0,10);
	        var dob_y = dob.substring(0,4);
	        var dob_m = dob.substring(5,7);
	        var dob_d = dob.substring(8,10);
	         var dob_t = dob_d+"/"+dob_m+"/"+dob_y;
	        $("#other_validity_upto").val(dob_t);
	        }
	        
	        
	        $("#central_reg_no").val('${data[0].central_reg_no}');
	        $("#adjunct_registration_no").val('${data[0].adjunct_registration_no}');
	        $("select#adjunct_state_no").val('${data[0].adjunct_state_no}');
	        
	        var state_validity_upto = '${data[0].state_validity_upto}';
	        var dob= state_validity_upto.substring(0,10);
	        var dob_y = dob.substring(0,4);
	        var dob_m = dob.substring(5,7);
	        var dob_d = dob.substring(8,10);
	         var dob_state = dob_d+"/"+dob_m+"/"+dob_y;
	        $("#state_validity_upto").val(dob_state);
				 $("div#sr").show();
// 					$("div#dr").hide();
			 }
			 else{
				 $("div#sr").hide();
// 					$("div#dr").hide();
			 }
		
// 		$("#registration_type").val('${data[0].registration_type}');

		if('${data[0].registration_type}'== 2 ){
			$("#direct_reg_no").val('${data[0].direct_reg_no}');
			
			var direct_date_of_reg = '${data[0].direct_date_of_reg}';
	        var dob= direct_date_of_reg.substring(0,10);
	        var dob_y = dob.substring(0,4);
	        var dob_m = dob.substring(5,7);
	        var dob_d = dob.substring(8,10);
	         var dob_d = dob_d+"/"+dob_m+"/"+dob_y;
	        $("#direct_date_of_reg").val(dob_d);
	        
	        $("#name_of_department").val('${data[0].name_of_department}');
	        
	        var direct_validity_upto = '${data[0].direct_validity_upto}';
	        var dob= direct_validity_upto.substring(0,10);
	        var dob_y = dob.substring(0,4);
	        var dob_m = dob.substring(5,7);
	        var dob_d = dob.substring(8,10);
	         var dob_direct = dob_d+"/"+dob_m+"/"+dob_y;
	        $("#direct_validity_upto").val(dob_direct);
	        
// 	        $("div#sr").hide();
			$("div#dr").show();
			 }
			 else{
// 				 $("div#sr").hide();
					$("div#dr").hide();
			 }
// 		$('.singleselect').trigger('change'); 
// 		$('#per_district').trigger('change'); 
		
////////////////////mirangi_21_07_22////////////End
		
		$("#cand_prefix").val('${data[0].cand_prefix}');
		
		$("#academic_quali").val('${data[0].academic_quali}');
		if('${data[0].academic_quali}'== 2 ){
			$("#subject").val('${data[0].subject}');
				 $("#md_detl").show();
			 }
			 else{
				 $("#md_detl").hide();
			 }
		
		$("#academic_quali").val('${data[0].academic_quali}');
		if('${data[0].academic_quali}'== 1 ){
			$("#sub_quali_degree").val('${data[0].sub_quali_degree}');
			$("#sub_deg_course").val('${data[0].sub_deg_course}');
				 $("#hideshw_Degree").show();
			 }
			 else{
				 $("#hideshw_Degree").hide();
			 }
		
		$('#institute_name1').val('${data[0].institute_name}');
		
		if('${data[0].depart_name1}'!=null && '${data[0].depart_name1}'!=""){
		$('#depart_name1').val('${data[0].depart_name}');
		$("#depart_name1").change();
		}else{
			$("#depart_name1").val('0');
		}
		
		if('${data[0].desig1}'!=null && '${data[0].desig1}'!=""){
		$('#desig1').val('${data[0].desig}');
		$("#desig1").change();
		}else{
			$("#desig1").val('0');
		}
		
		$("#yr_of_exp1").val('${data[0].yr_of_exp}');
		
		if('${data[0].from_date}'!=null && '${data[0].from_date}'!=""){
        var from_date = '${data[0].from_date}';
        var dor= from_date.substring(0,10);
        var dor_y = dor.substring(0,4);
        var dor_m = dor.substring(5,7);
        var dor_d = dor.substring(8,10);
         var dor_t = dor_d+"/"+dor_m+"/"+dor_y;
        $("#from_date1").val(dor_t);
		}else{ 
			$("#from_date").val('DD/MM/YYYY');
		}
        
		
		if('${data[0].to_date}'!=null && '${data[0].to_date}'!=""){
        var to_date = '${data[0].to_date}';
        var dov= to_date.substring(0,10);
        var dov_y = dov.substring(0,4);
        var dov_m = dov.substring(5,7);
        var dov_d = dov.substring(8,10);
         var dov_t = dov_d+"/"+dov_m+"/"+dov_y;
        $("#to_date1").val(dov_t);
		}else{
			$("#to_date").val('DD/MM/YYYY');
		}
       
		
        $("#upload_file_hid").val('${data[0].upload_file}');
        
        if('${data[0].doc_name1}'!=null && '${data[0].doc_name1}'!=""){
    	$('#doc_name1').val('${data[0].doc_name}');
        }else{
        	$("#doc_name1").val('0');
        }
		//$("#doc_name1").change();
	//	GetTypeFromDoc(1);
	 if('${data[0].doc_id1}'!=null && '${data[0].doc_id1}'!=""){
		$('#doc_id1').val('${data[0].doc_id}');
		formateOfDoc(1);
	 }else{
		 $("#doc_id1").val('0');
	 }
// 		$("#doc_id1").change();
		
		 $("#doc_upload_hid").val('${data[0].upload_doc}');
	
		 $("#update-button").show();
		 $(".savelist").hide();
		 
		 
		 
	
		 
		 
// 		 if ($("#experienceother").hasClass("d-none")) {
// 			 $( "#experienceother").removeClass("d-none")
		 			
// 			 $("#experienceother").val('${Type_of_exp_acad[0][2]}');
// 		 }			 
// 	else{
// 		//$("#universityother"+ind).hide();
// 		if (!$( "#experienceother").hasClass('d-none')) {
// 			$( "#experienceother").addClass("d-none")
// 		}
// 	}
		 
// 		 if(type_of_exp== "-1"){
// 				$("#type_of_exp").val(type_of_exp);
// 				$("#experienceother").show();
// 				 $("#experienceother").val('${Type_of_exp_acad[0][2]}');
// 			}else{
// 				$("#experienceother").hide();
// 			}
		 
		 
		 
		
		 
		
			addmoredatafetch1();
			addmoredatafetch2();
			addmoredatafetch3();
			addmoredatafetch4();
			addmoredatafetch5();
			
			if('${data[0].status}'==1){
				$("#btn-save").hide();
				
				$("#btn-update").hide();
				$("#draftBtn").hide();
				
				  $('input[type="text"], textarea').attr('readonly','readonly');
				  $('input[type="email"], textarea').attr('readonly','readonly');
				//  $('input[type="file"]').attr("style", "pointer-events: none;");
				  $('input[type="file"]').attr('readonly','readonly');
				 // $('select').attr("style", "pointer-events: none;");
				  $('select').attr('readonly','readonly');
				  $('span').attr('readonly','readonly');
				 // $('.select2-container').attr('disable', 'disable');
				//  $('span').attr("style", "pointer-events: none;");
				  $('span').attr('readonly','readonly');
				 // $('a').attr("style", "pointer-events: none;");
				//  $('.addminusbut').attr("style", "pointer-events: none;");
				  $('.addminusbut').toggleClass("d-none");
				  
				  
				 // $('.danger-btn-outline').attr("style", "pointer-events: none;");
				 $('.danger-btn-outline').toggleClass("d-none");
				 $('#id_add_att_pro1').toggleClass("d-none");
				 $('#id_add_att1').toggleClass("d-none");
				 $('.select2-selection__rendered').attr('readonly','readonly');
				 $('#btnmodel1').attr('readonly','readonly');
				 
				 $('.actionhide').toggleClass("d-none");
				 
				 $(".ui-datepicker-trigger").attr('disabled', 'true');
				 
				 
				 
				 
				  				 				 				 
				 // $('#date_of_birth').attr("style", "pointer-events: none;");
				// $('input[type="month"]').attr("style", "pointer-events: none;");
				 $('input[type="month"]').attr('readonly','readonly');
				// $('.ui-datepicker').attr("style", "pointer-events: none;");
				 $('.ui-datepicker-trigger').attr('disable', 'disable');
				 //$('#draft-saveper').attr("style", "display: none;");
				if (!$( "#draft-saveper").hasClass('d-none')) {
					$( "#draft-saveper").addClass("d-none")
				}
				// $('#draft-savequali').attr("style", "display: none;");
				 if (!$( "#draft-savequali").hasClass('d-none')) {
						$( "#draft-savequali").addClass("d-none")
				}
				// $('#draft-savereg').attr("style", "display: none;");
				 if (!$( "#draft-savereg").hasClass('d-none')) {
						$( "#draft-savereg").addClass("d-none")
				}
				 
			//	 $('#draft-saveacademic').attr("style", "display: none;");
			 if (!$( "#draft-saveacademic").hasClass('d-none')) {
						$( "#draft-saveacademic").addClass("d-none")
				}
			 if (!$( "#draft-savetype_of_exp").hasClass('d-none')) {
					$( "#draft-savetype_of_exp").addClass("d-none")
			}
			
				 
				// $('#draft-saveexp').attr("style", "display: none;");
				if (!$( "#draft-saveexp").hasClass('d-none')) {
						$( "#draft-saveexp").addClass("d-none")
				}
				
				$( "#btnmodel1").addClass("disabled")
				$( "#btnmodelOth1").addClass("disabled")
				
				document.getElementById("check_address").disabled = true;
				document.getElementById("checkbox1").disabled = true;
				
				
				
// 			 var els = document.getElementsByTagName("a[href='http://domain.example']");
			 	//$('a[href="#finish"]').attr("style", "display: none;");
// 			 	if (!$( 'a[href="#finish"]').hasClass('d-none')) {
						$('a[href="#finish"]').toggleClass("d-none");
// 					var element = document.getElementsByTagName('a[href="#finish"]');
//    						element.classList.add("d-none");
   					// $('a[href="#finish]').addClass("d-none");
						//$( 'a[href="#finish]').addClass("d-none");
// 				}
			//	$('a[href="Teacher_dtl_Url"]').attr("style", "display: none;");
			 	var checkBox = document.getElementById("checkbox1");
				checkBox.checked = true;	
				
				//$('#checkbox1').toggleClass("d-none");
				//$('#instruction').toggleClass("d-none");
				
				//	if (!$( "#instruction").hasClass('d-none')) {
					$( "#instruction").removeClass("d-none")
			//}
				
				
				$('#notrahu').text(" Your data has been forwarded for approval to the principal");
			//	$('#note').attr("style", "display: none;");
				if (!$( "#note").hasClass('d-none')) {
					$( "#note").addClass("d-none")
			}
// 				$('#check_address').attr("style", "display: none;");
// 				if (!$( "#check_address").hasClass('d-none')) {
// 					$( "#check_address").addClass("d-none")
// 			}
				
					$("#viewData").removeClass("d-none")
				
			}
			
		//----------------------------------------------------------- Edit --------------------------------------------------
		
			if('${data[0].status}'==4){
				$("#btn-save").hide();
				
				$("#btn-update").hide();
				$("#draftBtn").hide();
				 $('input[type="text"], textarea').attr('readonly','readonly');
				 $('input[type="email"], textarea').attr('readonly','readonly');
				 $('input[type="file"]').attr('readonly','readonly');
				 $('select').attr('readonly','readonly');
				 $("#per_add_line1").attr("readonly", false);
				 $("#per_add_line2").attr("readonly", false);
				 $("#per_state").attr("readonly", false);
				 $("#per_district").attr("readonly", false);
				 $("#per_village").attr("readonly", false);
				 $("#per_pincode").attr("readonly", false);
				 $("#per_phn_no").attr("readonly", false);
				 $("#mobile_no").attr("readonly", false);
				 $("#email").attr("readonly", false);
				 
				 $('.select2-selection__rendered').attr('readonly','readonly');
				 
				 $('input[type="month"]').attr('readonly','readonly');
				 
				 
				 
				 
				 
			}
		
		
			if('${data[0].principal_status}'== 1){
				
				$("#teacher_code").text('${data[0].teacher_code}');
			
		}

				
		
			
}else{
	
	
	var hiddenfie= $('#perentId').val();
	 if(hiddenfie=='0'){
		 
		 $("#draft-savequali").hide();
		 $("#draft-savereg").hide();
		 $("#draft-saveacademic").hide();
		 $("#draft-saveexp").hide();
		// $('a[href="#finish"]').attr("style", "display: none;"); 
	 }else{
		 
		 $("#draft-savequali").show();
		 $("#draft-savereg").show();
		 $("#draft-saveacademic").show();
		 $("#draft-saveexp").show();
		// $('a[href="#finish"]').attr("style", "display:block;");
		 
	 }
	
}



setTimeout(setTimeLoadForTable, 1000);
				
});


function setTimeLoadForTable() {

	
	
	//onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')"
	
	//onchange="clickrecall(this,\'DD/MM/YYYY\'); onchangeCount('+att+');"
	
// 	document.querySelectorAll('.ADDSystem').forEach((items, index) => {
// 		items.addEventListener('click', event => {
// 			
// 			var val=parseInt(index)+1;
// 			var hid = document.getElementById('apIdAGE'+val).value;
// //				var hname = document.getElementById('apItmeNumber'+val).value;
// //				var hpnum = document.getElementById('apBoqId'+val).value;
// 			if (confirm('Are You Sure You Want to Edit Detail ?')) {
// 				editData(hid);
// 			} else {
// 				return false;
// 			}
// 		})
// 	});
// 	document.querySelectorAll('.deleteOnclick').forEach((items, index) => {
// 		items.addEventListener('click', event => {
// 			
// 			var val=parseInt(index)+1;
			
// 			var hid = document.getElementById('deleteID'+val).value;
// //				var hname = document.getElementById('apItmeNumber'+val).value;
// //				var hpnum = document.getElementById('apBoqId'+val).value;
// 			if (confirm('Are You Sure You Want to Delete Detail ?')) {
// 				deleteData(hid);
// 			} else {
// 				return false;
// 			}
// 		})
// 	});
	
//		document.getElementById('test').onchange =function () {
//			 getEmb_Des();
//		};
}

const pasteBox = document.getElementById("state_reg_no");
pasteBox.onpaste = e => {
	 e.preventDefault();
	 return false;
	};
	

	function isNullEmptyBlank(str){
	    return str === null || str.match(/^ *$/) !== null;
	}

	var addr = '';

// 	if(isNullEmptyBlank(addr)){
// 	  console.log('The value of variable is null, empty or has blank spaces');
// 	}
	
	function addOnclick(att){
		
		//onkeypress="AutoCompleteNameOfUniversity(this);"
		document.getElementById('NameOfUniversity'+att).onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this);
			};
			//NameOfAffUni
				document.getElementById('NameOfAffUni'+att).onkeypress = function() {
						return onlyAlphabetsStringSpace(event,this);
			};
			document.getElementById('NameOfAffUni'+att).onclick = function() {
				 OtherOption(this,att);
				};
				
				
		
				
				document.getElementById('monthYearOfDegree'+att).onchange = function() {
					return Getchekmonthyear(att);	
				};

				
		document.getElementById('id_add_attNameMed'+att).onclick = function() {
			formOpenNameMed(att);
			};
			
			document.getElementById('id_add_attNameMed'+att).onclick = function() {
				formOpenNameMed(att);
			};
			document.getElementById('typeOfDegree'+att).onchange = function() {
				 GetCoursebytype(att);	
			};
			document.getElementById('course'+att).onchange = function() {
				return GetSubfromCourse(att);	
			};
			
			document.getElementById('NameOfUniversity'+att).onchange = function() {
				
				return getuniversitybyinst(att);	
			};
			
			document.getElementById('att_id_removeNameMed'+att).onclick = function() {
				formopen_re_NameMed(att);
			};
			
			
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
			
			
		}

// function removeOnclick(att){
	
// }
// 		function doc_type_oc(index){
// 			document.getElementById('doc_type'+index).onchange = function() {
// 				formateOfDoc(index);
// 			};
// 		}
// 		function upload_document_oc(index){
// 			document.getElementById('upload_document'+index).onchange = function() {
// 				pdfFileSizeValidation(this,this.value,'upload_document'+index+'','200kb','doc_path_doc_lbltik'+index+'','doc_path_doc1_lbl'+index+'',this.accept);
// 			};
// 		}

function addOnclickQuali(index){
	
	document.getElementById('id_add_attOthQuali'+index).onclick = function() {
		formOpenOthQuali(index);
		};
		
		document.getElementById('OthnameofExDeg'+index).onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this);
		};
		
		document.getElementById('othSubject'+index).onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this);
		};
		
		document.getElementById('othUniInst'+index).onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this);
		};
		
		document.getElementById('othAffuni'+index).onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this);
		};
		
		
		// oth quali
		 $('#btnmodelOth'+index).click(function() {
		 	   $('#oth_quali_mw').modal('show');
		 	  dynamicTableOQ(index,1);
		 	});
		 $('#closeOQModel').click(function() {
		 	   $('#oth_quali_mw').modal('hide');
		 	});
		 // $('#closeModel2').click(function() {
//		  	   $('#modelWindow').modal('hide');
//		  	});
		
	}

function removeOnclickQuali(index){
	document.getElementById('att_id_removeOthQuali'+index).onclick = function() {
		formopen_re_OthQuali(index);
	};
}

function addonkeypress(index){
	
	document.getElementById('institute_name'+index).onkeypress = function() {
		return onlyAlphabetsStringSpace(event,this);
	};
	
}


function addonkeypresspro(index){
	
	document.getElementById('organization_name'+index).onkeypress = function() {
		return onlyAlphabetsStringSpace(event,this);
	};
	
}




function dateAddmore(index){
	
	//onchange="clickrecall(this,\'DD/MM/YYYY\'); onchangeCount('+att+');"
	
	document.getElementById('from_date'+index).onclick = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('from_date'+index).onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('from_date'+index).onblur = function() {
		clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('from_date'+index).onkeyup = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('from_date'+index).onchange = function() {
		clickrecall(this,'DD/MM/YYYY');onchangeCount(index);
	};
	document.getElementById('to_date'+index).onclick = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('to_date'+index).onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('to_date'+index).onblur = function() {
		clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('to_date'+index).onkeyup = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('to_date'+index).onchange = function() {
		clickrecall(this,'DD/MM/YYYY');onchangeCount(index);
	};
	
	//onchange="pdfFileSizeValidation(this,this.value,&apos;upload_file'+att+'&apos;,&apos;200kb&apos;,&apos;upload_file_lbltik'+att+'&apos;,&apos;upload_file_lbl'+att+'&apos;);"
	
	document.getElementById('upload_file'+index).onchange = function() {
		pdfFileSizeValidation(this,this.value,'upload_file'+index+'','200kb','upload_file_lbltik'+index+'','upload_file_lbl'+index+'',this.accept);
	};
}



//----------------------------------------------------------------------Profession Experiernce Date Add More---------------------------------

function dateAddmorepro(index){
	
	//onchange="clickrecall(this,\'DD/MM/YYYY\'); onchangeCount('+att+');"
	
	document.getElementById('from_date_pro'+index).onclick = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('from_date_pro'+index).onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('from_date_pro'+index).onblur = function() {
		clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('from_date_pro'+index).onkeyup = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('from_date_pro'+index).onchange = function() {
		clickrecall(this,'DD/MM/YYYY');onchangeCountpro(index);
	};
	document.getElementById('to_date_pro'+index).onclick = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('to_date_pro'+index).onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('to_date_pro'+index).onblur = function() {
		clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('to_date_pro'+index).onkeyup = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('to_date_pro'+index).onchange = function() {
		clickrecall(this,'DD/MM/YYYY');onchangeCountpro(index);
	};
	
	//onchange="pdfFileSizeValidation(this,this.value,&apos;upload_file'+att+'&apos;,&apos;200kb&apos;,&apos;upload_file_lbltik'+att+'&apos;,&apos;upload_file_lbl'+att+'&apos;);"
	
}













//---------------------------------------------------------------Finish-Profession Experiernce Date Add More---------------------------------



function addOnclickacademic(index){
	
	document.getElementById('id_add_att'+index).onclick = function() {
		formopen_att(index);
	};
}

function addOnclickprofession(index){
	
	document.getElementById('id_add_att_pro'+index).onclick = function() {
		formopen_att_pro(index);
	};
}

function removeOnclickacademic(index){
	
	document.getElementById('att_id_remove'+index).onclick = function() {
		formopen_re_att(index);
	};
}

function removeOnclickprofession(index){
	
	document.getElementById('att_id_remove_pro'+index).onclick = function() {
		formopen_re_att_pro(index);
	};
}

			function type_of_expadd(index){
				debugger;
				document.getElementById('type_of_exp'+index).onclick = function() {
					OtherOptionforexp(this,index);
				};
			}

function addmoredocument(index){
	
	//doc_name
	//onchange="GetTypeFromDoc('+doc+');"
	document.getElementById('doc_name'+index).onchange = function() {
		GetTypeFromDoc(index);
	};
	//doc_id
	//onchange="formateOfDoc('+doc+')"
	//onchange="formateOfDoc('+doc+')"
	document.getElementById('doc_id'+index).onchange = function() {
		formateOfDoc(index);
	};
	//upload_doc
	//onchange="pdfFileSizeValidation(this,this.value,&apos;upload_doc'+doc+'&apos;,&apos;200kb&apos;,&apos;doc_upload_lbltik'+doc+'&apos;,&apos;doc_upload_lbl'+doc+'&apos;,this.accept);"
	document.getElementById('upload_doc'+index).onchange = function() {
		pdfFileSizeValidation(this,this.value,'upload_doc'+index+'','200kb','doc_upload_lbltik'+index+'','doc_upload_lbl'+index+'',this.accept);
	};
	//onclick="formopen_doc('+doc+');"
	document.getElementById('id_add_doc'+index).onclick = function() {
		formopen_doc(index);
	};
	//onclick="formopen_re_doc('+doc+');"
	document.getElementById('doc_id_remove'+index).onclick = function() {
		formopen_re_doc(index);
	};
}

function dynamicparent(R,index){
	
	//name_of_attachment
	//onchange="degreehide('+R+','+index+')"
	
	document.getElementById('name_of_attachment'+R+"_"+index).onchange = function() {
		degreehide(R,index);
		};
		
		document.getElementById('attachmentDocument'+R+"_"+index).onchange = function() {
			return   pdfFileSizeValidation(this,this.value,"attachmentDocument"+R+"_"+index,"200kb","attachmentDocument_file_lbltik"+R+"_"+index,"attachmentDocument_file_lbl"+R+"_"+index,this.accept);
		};
		//attachmentDocument
		//onchange="attachmentDetails(this,1);
// 		document.getElementById('attachmentDocument'+R+"_"+index).onchange = function() {
// 			attachmentDetails(R,index);
// 			};
	//id_add_attDoc
	//onclick="formOpenattDoc('+R+',1);"
		document.getElementById('id_add_attDoc'+R+"_"+index).onclick = function() {
			formOpenattDoc(R,1);
			};
}

function dynamicparentother(R,index){
		//attachmentDocumentOQ
		//onchange="attachmentDetails(this,1);"
// 		document.getElementById('attachmentDocumentOQ'+R+"_"+index).onchange = function() {
// 			attachmentDetails(R,index);
// 			};

	//id_add_attOQ
	//onclick="formOpenattOQ('+R+',1);"
	//onclick="formOpenattOQ('+R+',1);"
	document.getElementById('attachmentDocumentOQ'+R+"_"+index).onchange = function() {
			return   pdfFileSizeValidation(this,this.value,"attachmentDocumentOQ"+R+"_"+index,"200kb","attachmentDoc_hidOQ_file_lbltik"+R+"_"+index,"attachmentDoc_hidOQ_file_lbl"+R+"_"+index,this.accept);
		};
			document.getElementById('id_add_attOQ'+R+"_"+index).onclick = function() {
				formOpenattOQ(R,1);
			};

}

	function dynamictablemedical(R,att){

	//id_add_attDoc
	//onclick="formOpenattDoc('+R+','+att+');"
	//name_of_attachment
	//onchange="degreehide('+R+','+att+')"
	document.getElementById('name_of_attachment'+R+"_"+att).onchange = function() {
		degreehide(R,att)
		};
		
		//attachmentDocument
		//onchange="attachmentDetails(this,'+seq+');"
		
// 		document.getElementById('attachmentDocument'+R+"_"+att).onchange = function() {
// 			attachmentDetails(R,att);
// 		};

		document.getElementById('attachmentDocument'+R+"_"+att).onchange = function() {
			return   pdfFileSizeValidation(this,this.value,"attachmentDocument"+R+"_"+att,"200kb","attachmentDocument_file_lbltik"+R+"_"+att,"attachmentDocument_file_lbl"+R+"_"+att,this.accept);
		};
		document.getElementById('id_add_attDoc'+R+"_"+att).onclick = function() {
			formOpenattDoc(R,att);
		};
		//att_id_removeattDoc
		//onclick="formopen_re_attDoc('+R+','+att+');"
		document.getElementById('att_id_removeattDoc'+R+"_"+att).onclick = function() {
			formopen_re_attDoc(R,att);
		};
	}
	
function dynamictablemedicalq(R,att){
	
	//id_add_attOQ
	//onclick="formOpenattOQ('+R+','+att+');"
	//attachmentDoc_hidOQ
	//onchange="attachmentDetails(this,'+seq+');"
// 	document.getElementById('attachmentDoc_hidOQ'+R+"_"+att).onchange = function() {
// 		attachmentDetails(this,att);
// 	};
	
		document.getElementById('attachmentDocumentOQ'+R+"_"+att).onchange = function() {
			return   pdfFileSizeValidation(this,this.value,"attachmentDocumentOQ"+R+"_"+att,"200kb","attachmentDoc_hidOQ_file_lbltik"+R+"_"+att,"attachmentDoc_hidOQ_file_lbl"+R+"_"+att,this.accept);
		};
	document.getElementById('id_add_attOQ'+R+"_"+att).onclick = function() {
		formOpenattOQ(R,att);
	};
	//onclick="formopen_re_attOQ('+R+','+att+');"
	document.getElementById('att_id_removeattOQ'+R+"_"+att).onclick = function() {
		formopen_re_attOQ(R,att);
	};
}

</script>

<script nonce="${cspNonce}" type="text/javascript">

//Add-More-Add //////for previous Experience
var att=1;
function formopen_att(R){
	
	if(R>0){

	if($("#institute_name"+R).val().trim()==''){
		alert("Please Enter Organization Name In "+R+" Row");
		$("#institute_name"+R).focus();
		return false;
	}
	if($("#depart_name"+R).val()=='0'){
		alert("Please Select Department Name In "+R+" Row");
		$("#depart_name"+R).focus();
		return false;
	}
	if($("#desig"+R).val()=='0'){
		alert("Please Select Designation In "+R+" Row");
		$("#desig"+R).focus();
		return false;
	}
	if($("input#from_date"+R).val().trim()=="" || $("input#from_date"+R).val().trim()=="DD/MM/YYYY"){
		alert("Please Enter From Date In"+R+" Row");
		$("#from_date"+R).focus();
		return false;
	}
	if($("input#to_date"+R).val().trim()=="" || $("input#to_date"+R).val().trim()=="DD/MM/YYYY"){
		alert("Please Enter To Date In"+R+" Row");
		$("#to_date"+R).focus();
		return false;
	}
	if(getformatedDate($("input#from_date"+R).val()) > getformatedDate($("#to_date"+R).val())) {
		alert("To Date should be Greater than Or Equal to From Date In "+R+" Row");
		$("#to_date"+R).focus();
		return false;
    }
	/* if($("#data_fetch").val() == "1"){
	 	if($("#upload_file_hid"+R).val() == ""){	
		 		alert("Please Select File "+R+" Row");
		 		 $("input#upload_file"+R).focus();
				 return false;
		}
	}else{
	 	if($("#upload_file"+R).val() == ""){
			 	alert("Please Select File "+R+" Row");
			 	$("input#upload_file"+R).focus();
				return false;
			 }
	 } */

if($("#institute_name"+R).val() != "0" && $("#institute_name"+R).val() != "1")
//	count_classi++;
if($("#institute_name"+R).val() != "1"){
	if($("#institute_name"+R).val().trim()==''){
		alert("Please Enter Organization Name In "+R+" Row");
		$("#institute_name"+R).focus();
		return false;
	}
	if($("#depart_name"+R).val()=='0'){
		alert("Please Select Department Name In "+R+" Row");
		$("#depart_name"+R).focus();
		return false;
	}
	if($("#desig"+R).val()=='0'){
		alert("Please Select Designation In "+R+" Row");
		$("#desig"+R).focus();
		return false;
	}
	if($("input#from_date"+R).val().trim()=="" || $("input#from_date"+R).val().trim()=="DD/MM/YYYY"){
		alert("Please Enter From Date In"+R+" Row");
		$("#from_date"+R).focus();
		return false;
	}
	if($("input#to_date"+R).val().trim()=="" || $("input#to_date"+R).val().trim()=="DD/MM/YYYY"){
		alert("Please Enter To Date In"+R+" Row");
		$("#to_date"+R).focus();
		return false;
	}
	if(getformatedDate($("input#from_date"+R).val()) > getformatedDate($("#to_date"+R).val())) {
		alert("To Date should be Greater than Or Equal to From Date  In"+R+" Row");
		$("#to_date"+R).focus();
		return false;
    }
	
	
}
	/* if($("#data_fetch").val() == "1"){
		 if($("#upload_file_hid"+R).val() == ""){	
			alert("Please Select File "+R+" Row");
			 $("input#upload_file"+R).focus();
			 return false;
		 }
	}else{
	 	 if($("#upload_file"+R).val() == ""){
			 	alert("Please Select File "+R+" Row");
			 	$("input#upload_file"+R).focus();
				return false;
			 }
	 } */
}

	$("#att_Tb").show();
	
		 $("#id_add_att"+R).hide();
		 $("#att_id_remove"+R).hide();
		 
		 att=0;
		 att= parseInt(R)+1;
		 
		 if(att < 51){
				 
				 $("input#count_hidden_att").val(att);//current serial No
				 $("table#att_Tb").append('<tr id="tr_id_att'+att+'"><td><p>'+att+'</p></td>'
						 
						 
						 +'<td><div class="input-style-1"><input type="text" id="institute_name'+att+'" name="institute_name'+att+'"  maxlength="100" class="form-control" autocomplete="off" placeholder="Enter Organization Name"  ><input type="hidden" id="profession_id'+att+'" name="profession_id'+att+'" value="0"   class="form-control" autocomplete="off"  ></div></td>' /* onkeypress="autox('+att+');"  onkeypress="return onlyAlphabetsStringSpace('+att+');"*/
						 +'<td><div class="select-style-1"><div class="select-position"><select name="depart_name'+att+'" class="form-control" id ="depart_name'+att+'" onchange=""><option value="0">--Select Department--</option>'
						 +'<c:forEach var="item" items="${getSystemList}"	varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></div></div></td>'
// 							+'<td><div class="select-style-1"><div class="select-position"><select name="desig'+att+'" class="form-control" id ="desig'+att+'" onchange=""><option value="0">--Select Designation--</option><option value="1">PRT</option><option value="2">TGT</option>'
// 							 +'<option value="3">PGT</option></select></div></div></td>'
								
							+ '<td><div class="select-style-1"><div class="select-position"><select name="desig'+att+'" id="desig'+att+'"class="form-control valid" aria-invalid="false">'
							+'<option value="0" selected="selected"> -- Select Designation -- </option><c:forEach var="item" items="${getDesignationList}"	varStatus="num"><option value="${item.id}" name="${item.id}">${item.designation}</option>'
							+'</c:forEach></select></div></div></td>'
							
							+'<td><div class="select-style-1"><div class="select-position"><select name="employment_type'+att+'" id="employment_type'+att+'"class="form-control valid" aria-invalid="false">'
							+'<option value="0" selected="selected"> -- Select Employement Type -- </option><option value="1">Full Time</option><option value="2">Part Time</option>'
							+'<option value="3">Guest Faculty</option></select></div></div></td>'
						 
							+'<td>' 
						    +' <div class="input-style-2"><input type="text" name="from_date'+att+'" id="from_date'+att+'" maxlength="10" value="DD/MM/YYYY"      class="form-control" '		/*onclick="clickclear(this, \'DD/MM/YYYY\')"  */
						    +'	 '																																								/*onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\'); " onkeyup="clickclear(this, \'DD/MM/YYYY\')"*/
					     	+'	 aria-required="true" autocomplete="off" ></div>'																												/*onchange="clickrecall(this,\'DD/MM/YYYY\'); onchangeCount('+att+');"*/
						    + '</td>'
						    
						    +'<td>' 
						    +' <div class="input-style-2"><input type="text" name="to_date'+att+'" id="to_date'+att+'" maxlength="10" value="DD/MM/YYYY"     class="form-control" '			/*onclick="clickclear(this, \'DD/MM/YYYY\')"*/
						    +'	 '																																							/*onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')"*/
					     	+'	 aria-required="true" autocomplete="off" ></div>'																											/*onchange="clickrecall(this,\'DD/MM/YYYY\'); onchangeCount('+att+');"*/
						    + '</td>'
						    +'<td><div class="input-style-1"><input type="text" id="yr_of_exp'+att+'" name="yr_of_exp'+att+'"  maxlength="100" class="form-control" autocomplete="off"  readonly="readonly"></div></td>' 
						    
						    +'<td><div class="select-style-1"><div class="select-position">'
							+'<select name="honorarium'+att+'" id="honorarium'+att+'" class="form-control valid" aria-invalid="false"><option value="0" selected="selected"> -- Select Salary Paid -- </option>'
							+'<option value="1">Yes</option><option value="2">No</option></select></div></div></td>'
						    
						    +'<td><div class="input-style-1"><input type="file" accept=".pdf" id="upload_file'+att+'" name="upload_file'+att+'"class="form-control" ><div class="note-text"><span class="errorClass" id="upload_file_lbl'+att+'">${exp_path_msg}</span> <span class="tikClass" id="upload_file_lbltik'+att+'"></span></div>'					/*onchange="pdfFileSizeValidation(this,this.value,&apos;upload_file'+att+'&apos;,&apos;200kb&apos;,&apos;upload_file_lbltik'+att+'&apos;,&apos;upload_file_lbl'+att+'&apos;);"*/
						    +'<input type="hidden" id="upload_file_hid'+att+'" name="upload_file_hid'+att+'" class="form-control" ></div></td>'

						 +'<td><div class="action"><ul class="buttons-group mainbtn"><li><a class="main-btn success-btn btn-hover btn-sm btnaddmore" value = "ADD" title = "ADD" id = "id_add_att'+att+'"   ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove" value="REMOVE" title = "REMOVE" id = "att_id_remove'+att+'" ><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
			   		     +'</tr>');
				// onclick="formopen_att('+att+');"
			//	onclick="formopen_re_att('+att+');"
				addonkeypress(att);
				dateAddmore(att);
				 addOnclickacademic(att);
				 removeOnclickacademic(att);
				 
				 datepicketDate("from_date"+att);
				 datepicketDate("to_date"+att);
		 
			}else{
					alert("Please Enter max 50 Quantity");
					 if ( att == 51){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
		 var curcnt = $("#count_hidden_att").val();
		 $("#new_count_hidden").val(curcnt);
// 		 document.querySelectorAll('.Formopen_att').forEach((items, index) => {
// 				items.addEventListener('click', event => {
// 					var val=parseInt(index)+1;
// 					formopen_att(val);
// 				})
// 				});
	
}
//Add-More-Remove////for previous Experience-----------------------------------------------------------------
function formopen_re_att(R){
	var del_index = $("#idofprocedure"+R).val();
	if(String(del_index) == "undefined"){
		del_index="0";
	}else{
		del_index = del_index;
	}
	 var del_field_val = $("#del_id_hidden").val();
	 
	 if(del_field_val == "0,undefined"){
		 $("#del_id_hidden").val(del_index);
	 }else{
		 $("#del_id_hidden").val(del_field_val+","+del_index);
	 }
	 
	 
	 var profession_id = $("#profession_id"+R).val();
	 var result = confirm("Are you sure you want to delete?");
	 
	 if (result) {
			$.post("delete_profession_addmore?" + key + "=" + value, {profession_id:profession_id}, function(j) {
				 alert("Data Deleted Successfully.")
			 	});
	
	 $("tr#tr_id_att"+R).remove();
	 att = att-1;
	 $("input#count_hidden_att").val(att);
	 if(R > 2){
		 $("#id_add_att"+att).show();
		 $("#att_id_remove"+att).show();
	 }
	 if(R == 2){
		 $("#id_add_att"+att).show();
	 }
	 var ncc = $("#new_count_hidden").val();
	 ncc = ncc-1;
	 $("#new_count_hidden").val(ncc);
	 }
}

//----------------------------Finish Add More Profession Experience----------------------------------------------------------------------

function formopen_att_pro(R){
	
	if(R>0){

	if($("#organization_name"+R).val().trim()==''){
		alert("Please Enter Organization Name In "+R+" Row");
		$("#organization_name"+R).focus();
		return false;
	}

	if($("input#from_date_pro"+R).val().trim()=="" || $("input#from_date_pro"+R).val().trim()=="DD/MM/YYYY"){
		alert("Please Enter From Date In"+R+" Row");
		$("#from_date_pro"+R).focus();
		return false;
	}
	if($("input#to_date_pro"+R).val().trim()=="" || $("input#to_date_pro"+R).val().trim()=="DD/MM/YYYY"){
		alert("Please Enter To Date In"+R+" Row");
		$("#to_date_pro1"+R).focus();
		return false;
	}
	if(getformatedDate($("input#from_date_pro"+R).val()) > getformatedDate($("#to_date_pro"+R).val())) {
		alert("To Date should be Greater than Or Equal to From Date  In"+R+" Row");
		$("#to_date_pro"+R).focus();
		return false;
    }


if($("#institute_name"+R).val() != "0" && $("#institute_name"+R).val() != "1")
//	count_classi++;
if($("#organization_name"+R).val() != "1"){
	if($("#organization_name"+R).val().trim()==''){
		alert("Please Enter Organization Name In "+R+" Row");
		$("#organization_name"+R).focus();
		return false;
	}
	if($("input#from_date_pro"+R).val().trim()=="" || $("input#from_date_pro"+R).val().trim()=="DD/MM/YYYY"){
		alert("Please Enter From Date In"+R+" Row");
		$("#from_date_pro"+R).focus();
		return false;
	}
	if($("input#to_date_pro"+R).val().trim()=="" || $("input#to_date_pro"+R).val().trim()=="DD/MM/YYYY"){
		alert("Please Enter To Date In"+R+" Row");
		$("#to_date_pro"+R).focus();
		return false;
	}
	if(getformatedDate($("input#from_date_pro"+R).val()) > getformatedDate($("#to_date_pro"+R).val())) {
		alert("To Date should be Greater than Or Equal to From Date  In"+R+" Row");
		$("#to_date_pro"+R).focus();
		return false;
    }
	
	
}

}

	$("#att_Tb_pro").show();
	
		 $("#id_add_att_pro"+R).hide();
		 $("#att_id_remove_pro"+R).hide();
		 
		 att=0;
		 att= parseInt(R)+1;
		 
		 if(att < 51){
				 
				 $("input#count_hidden_att_pro").val(att);//current serial No
				 $("table#att_Tb_pro").append('<tr id="tr_id_att_pro'+att+'"><td><p>'+att+'</p></td>'
						 
						 
						+'<td><div class="input-style-1"><input type="text" id="organization_name'+att+'" name="organization_name'+att+'" class="form-control" autocomplete="off"   maxlength="100" placeholder="Enter Organization Name">'
						+'<input type="hidden" id="academic_exp'+att+'" name="academic_exp'+att+'" value="0" class="form-control" autocomplete="off"   maxlength="100" ></div></td>'
						+'<td><div class="input-style-2"><input type="text" name="from_date_pro'+att+'" id="from_date_pro'+att+'" maxlength="10" class="form-control valid" aria-required="true" autocomplete="off" value="DD/MM/YYYY" placeholder="Select From Date"></div></td>'
						+'<td><div class="input-style-2"><input type="text" name="to_date_pro'+att+'" id="to_date_pro'+att+'" maxlength="10"  class="form-control"   aria-required="true" autocomplete="off" value="DD/MM/YYYY" placeholder="Select To Date"></div></td>'
						
						+'<td><div class="input-style-1"><input id="yr_of_exp_pro'+att+'" name="yr_of_exp_pro'+att+'" class="form-control" autocomplete="off"  readonly="readonly" maxlength="100" placeholder="Year of Experience"></div></td>'
						
						+'<td><div class="input-style-1"><input type="text" id="designation_name'+att+'" name="designation_name'+att+'" class="form-control" autocomplete="off"   maxlength="100" placeholder="Enter Designation Name"></div></td>'
						
						+'<td><div class="select-style-1"><div class="select-position"><select class="form-control" name="type_of_exp'+att+'" id="type_of_exp'+att+'" ><option value="0" selected="selected">--Select Experience --</option><c:forEach var="item" items="${Type_of_exp}" varStatus="num"><option value="${item.id}" name="${item.id}">${item.type_of_experience}</option></c:forEach><option value="-1" name="OTHER">OTHER</option></select></div>'
						+'<input type="text"  id="experienceother'+att+'" name="experienceother'+att+'" class="form-control autocomplete d-none" autocomplete="off" placeholder="Enter Type Of Experience"></div></td>'

						 +'<td><div class="action"><ul class="buttons-group mainbtn"><li><a class="main-btn success-btn btn-hover btn-sm btnaddmore" value = "ADD" title = "ADD" id = "id_add_att_pro'+att+'"   ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove" value="REMOVE" title = "REMOVE" id = "att_id_remove_pro'+att+'" ><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
			   		     +'</tr>');

 				addonkeypresspro(att);
 				dateAddmorepro(att);
 				 addOnclickprofession(att);
 				 removeOnclickprofession(att);
 				 type_of_expadd(att);
				 
				 datepicketDate("from_date_pro"+att);
				 datepicketDate("to_date_pro"+att);
		 
			}else{
					alert("Please Enter max 50 Quantity");
					 if ( att == 51){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
		 var curcnt = $("#count_hidden_att").val();
		 $("#new_count_hidden").val(curcnt);
// 		 document.querySelectorAll('.Formopen_att').forEach((items, index) => {
// 				items.addEventListener('click', event => {
// 					var val=parseInt(index)+1;
// 					formopen_att(val);
// 				})
// 				});
	
}

//------------------------------Delete Academic Experience-------------------------------------------------------------


function formopen_re_att_pro(R){
	
	var del_index = $("#idofprocedure"+R).val();
	if(String(del_index) == "undefined"){
		del_index="0";
	}else{
		del_index = del_index;
	}
	 var del_field_val = $("#del_id_hidden").val();
	 
	 if(del_field_val == "0,undefined"){
		 $("#del_id_hidden").val(del_index);
	 }else{
		 $("#del_id_hidden").val(del_field_val+","+del_index);
	 }
	 
	 
	 var academic_id = $("#academic_exp"+R).val();
	 var result = confirm("Are you sure you want to delete?");
	 
	 if (result) {
			$.post("delete_academic_addmore?" + key + "=" + value, {academic_id:academic_id}, function(j) {
				 alert("Data Deleted Successfully.")
			 	});
	
	 $("tr#tr_id_att_pro"+R).remove();
	 att = att-1;
	 $("input#count_hidden_att").val(att);
	 if(R > 2){
		 $("#id_add_att_pro"+att).show();
		 $("#att_id_remove_pro"+att).show();
	 }
	 if(R == 2){
		 $("#id_add_att_pro"+att).show();
	 }
	 var ncc = $("#new_count_hidden").val();
	 ncc = ncc-1;
	 $("#new_count_hidden").val(ncc);
	 }
}


//-----------------------------------------------------Finish Academic Profession-----------------------------------------------------------------

</script>

<script nonce="${cspNonce}" type="text/javascript">

//Add-More-Add/////for document
var doc=1;
function formopen_doc(R){
	$("#doc_Tb").show();
	
		 $("#id_add_doc"+R).hide();
		 $("#doc_id_remove"+R).hide();
		 doc=0;
		 doc= parseInt(R)+1;
		 
		 if(doc < 51){
			 
			 $("input#count_hidden_doc").val(doc);//current serial No
			 $("table#doc_Tb").append('<tr id="tr_id_doc'+doc+'"><td><p>'+doc+'</p></td>'
					 +'<td><div class="select-style-1"><div class="select-position"><select id="doc_name'+doc+'" name="doc_name'+doc+'" class="form-control" ><option value="0">--Select--</option>'
						+'<c:forEach var="item" items="${getDocList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach>' 
						+'</select><input type="hidden" id="add_quali'+doc+'" name="add_quali'+doc+'" value="0" class="form-control" autocomplete="off"   maxlength="100" ></div></div> </td>'
				    
					 +'<td><div class="select-style-1"><div class="select-position"><select id="doc_id'+doc+'" name="doc_id'+doc+'"  class="form-control"><option value="0">--Select--</option>'
						+'</select></div></div> </td>'	
					 +'<td><div class="input-style-1"><input type="file" accept="" id="upload_doc'+doc+'" name="upload_doc'+doc+'"class="form-control" ><div class="note-text">'
					+'<span class="errorClass" id="doc_upload_lbl'+doc+'">${exp_path_msg}</span> <span class="tikClass" id="doc_upload_lbltik'+doc+'"></span></div>'
					 +'<input type="hidden" id="doc_upload_hid'+doc+'" name="doc_upload_hid'+doc+'" class="form-control" ></div></td>'
					 +'<td><div class="action"><ul class="buttons-group mainbtn"><li><a class="main-btn success-btn btn-hover btn-sm btnaddmore" value = "ADD" title = "ADD" id = "id_add_doc'+doc+'" ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove" value="REMOVE" title = "REMOVE" id = "doc_id_remove'+doc+'" ><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
		   		     +'</tr>');
			 
			 addmoredocument(doc);
	 
		}else{
					alert("Please Enter max 50 Quantity");
					 if ( doc == 51){
						 doc = doc-1; 
						 $("#rp_id_remove"+doc).show();
					 }	   
			}
		 var curcnt = $("#count_hidden_doc").val();
		 $("#new_count_hidden").val(curcnt);
	
}
//Add-More-Remove
function formopen_re_doc(R){
	
	
	var del_index = $("#idofprocedure"+R).val();
	if(String(del_index) == "undefined"){
		del_index="0";
	}else{
		del_index = del_index;
	}
	 var del_field_val = $("#del_id_hidden").val();
	 
	 if(del_field_val == "0,undefined"){
		 $("#del_id_hidden").val(del_index);
	 }else{
		 $("#del_id_hidden").val(del_field_val+","+del_index);
	 }
	 
	 var add_quali = $("#add_quali"+R).val();
	 
	var result = confirm("Are you sure you want to delete?");
	 
	 if (result) {
			$.post("delete_adddoc_addmore?" + key + "=" + value, {add_quali:add_quali}, function(j) {
				 alert("Data Deleted Successfully.")
			 	});
	
	 
	 
	 
	 $("tr#tr_id_doc"+R).remove();
	 doc = doc-1;
	 $("input#count_hidden_doc").val(doc);
	 if(R > 2){
		 $("#id_add_doc"+doc).show();
		 $("#doc_id_remove"+doc).show();
	 }
	 if(R == 2){
		 $("#id_add_doc"+doc).show();
	 }
	 var ncc = $("#new_count_hidden").val();
	 ncc = ncc-1;
	 $("#new_count_hidden").val(ncc);
	 }
}


function GetTypeFromDoc(obj) {
	$.ajaxSetup({
		async : false
	});
	
	var doc_name = $("select#doc_name"+obj).val();
  $.post("getTypelistFromDoc?" + key + "=" + value,{doc_name : doc_name},
				function(j) {
	  			
					var options = '<option value="' + "0" + '">'
							+ "--Select--" + '</option>';
					for (var i = 0; i < j.length; i++) {

						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
								+ j[i][1]+ '</option>';
					}
					$("select#doc_id"+obj).html(options);
				});
}


function GetCoursebytype(obj) {
	$.ajaxSetup({
		async : false
	});
	var typeofdegree = $("select#typeOfDegree"+obj).val();
	
	if(typeofdegree != "" && typeofdegree != null){
		 $.post("getcoursebytypeOfDegreeList_ctrl?" + key + "=" + value,{typeofdegree : typeofdegree},
					function(j) {
		  			
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
							+ j[i][1]+ '</option>';
						}
						$("select#course"+obj).html(options);
						GetSubfromCourse(obj);
		});
	}
}

function GetSubfromCourse(obj) {
	$.ajaxSetup({
		async : false
	});
	var course = $("select#course"+obj).val();
	if(course != "" && course != null){
		 $.post("getSubjectList?" + key + "=" + value,{course : course},
					function(j) {
			 
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1]+ '</option>';
						}
						$("select#subject"+obj).html(options);
						
						var jl = j.length;
						if ( jl > 0 ) {
							
							$("div#hide_sub"+obj).show();
							
						}else {
							$("div#hide_sub"+obj).hide();
						}
		});
	}
 
}


function getDistrict() {
	$.ajaxSetup({
		async : false
	});
	var selval = $("#per_state").val();
	$
			.post(
					"getDistrictUrl?" + key + "=" + value,
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

function getDistrict_present() {
	$.ajaxSetup({
		async : false
	});
	
	var selval = $("#present_state").val();
	$
			.post(
					"getDistrictUrl?" + key + "=" + value,
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
						$("select#present_district").html(options);
					});
}

function mobileNumber(obj){
	if(obj.value.length<10){
		 alert('Please Enter valid Number');
	        $('#'+obj.id).focus();
	        $('#'+obj.id).val("");
	        return false;
	}
	_mobile = obj.value;
	
	var regExp =/^[6789]\d{9}$/;
    if(_mobile == '' || !regExp.test(_mobile)){
        alert('Please Enter Number Start with 6789 Digit');
        $('#'+obj.id).focus();
        $('#'+obj.id).val("");
        return false;
    }
}

function checkgmail(email1) {
	
	 document.getElementById("email").innerHTML="";
	var pattern=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	
//	 var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	 
	if(email1.match(pattern)) {
	}else{
		
		alert("Please Enter Valid Email Address");
		$("input#email").focus();
		$("input#email").val('');
		return false ;
	}
}



function lengthadhar() {
	document.getElementById("aadhar_no1").maxLength = "4";
	document.getElementById("aadhar_no1").minLength = "4";
	document.getElementById("aadhar_no2").maxLength = "4";
	document.getElementById("aadhar_no2").minLength = "4";
	document.getElementById("aadhar_no3").maxLength = "4";
	document.getElementById("aadhar_no3").minLength = "4";
} 

function movetoNext(current, nextFieldID) {  
	if (current.value.length >= current.maxLength) {  
		document.getElementById(nextFieldID).focus();  
	}  
}

//////////////////////for same as address
function copy_address() {
	
	if($("#check_address").is(":checked") == true){
		$("#present_add_line1").val($("#per_add_line1").val());
		$("#present_add_line1").attr('readonly',true);
		
		$("#present_add_line2").val($("#per_add_line2").val());
		$("#present_add_line2").attr('readonly',true);
		
		$("#present_village").val($("#per_village").val());
		$("#present_village").attr('readonly',true);
		
		$("#present_state").val($("#per_state").val());
		$("#present_state").change();
		$("#present_state").attr('readonly',true);
		
		$("#present_district").val($("#per_district").val());
		$("#present_district").change();
		$("#present_district").attr('readonly',true);
		
		$("#present_pincode").val($("#per_pincode").val());
		$("#present_pincode").attr('readonly',true);
		
		$("#present_phn_no").val($("#per_phn_no").val());
		$("#present_phn_no").attr('readonly',true);
		
	}
	
	if($("#check_address").is(":checked") == false){
		$("#present_add_line1").val("");
		$("#present_add_line1").attr('readonly',false);
		
		$("#present_add_line2").val("");
		$("#present_add_line2").attr('readonly',false);
		
		$("#present_village").val("");
		$("#present_village").attr('readonly',false);
		
		$("#present_state").val("0");
		$("#present_state").attr('readonly',false);
		
		$("#present_district").val("0");
		$("#present_district").attr('readonly',false);
		
		$("#present_pincode").val("");
		$("#present_pincode").attr('readonly',false);
		
		$("#present_phn_no").val("");
		$("#present_phn_no").attr('readonly',false);
		
	}
}

function changeAddress() {
	
	if($("#check_address").is(":checked") == true){
		$("#check_address").prop("checked", false);
		
		$("#present_add_line1").val("");
		$('#present_add_line1').attr('readonly', false);
		
		$("#present_add_line2").val("");
		$('#present_add_line2').attr('readonly', false);
		 
		$("#present_village").val("");
		$('#present_village').attr('readonly', false);
		
		$("#present_state").val("0");
		$('#present_state').attr('readonly', false);
		
		$("#present_district").val("0");
		$('#present_district').attr('readonly', false);
		
		$("#present_pincode").val("");
		$('#present_pincode').attr('readonly', false);
		
 		$("#present_phn_no").val("");
		$('#present_phn_no').attr('readonly', false);
		 
	}
}

function FnMd_dtl(){
	if($("#academic_quali").val() == "2"  ){
		$("div#md_detl").show();
	}
	if($("#academic_quali").val() == "1"  ){
		$("div#md_detl").hide();
	}
	if($("#academic_quali").val() == "3"  ){
		$("div#md_detl").hide();
	}
}

function hideshw_Degree() {
	if($("#academic_quali").val() == "1"  ){
// 		$("div#Type_of_degree").show();
// 		$("div#sub_degree").show();
	}
	if($("#academic_quali").val() == "2"  ){
		$("div#Type_of_degree").hide();
		$("div#sub_degree").hide();
	}
	if($("#academic_quali").val() == "3"  ){
		$("div#Type_of_degree").hide();
		$("div#sub_degree").hide();
	}
	
}

function hideshw_Registration() {
	if($("#registration_type").val() == "1"  ){
		$("div#sr").show();
		$("div#dr").hide();
	}
	if($("#registration_type").val() == "2"  ){
		$("div#dr").show();
		$("div#sr").hide();
	}
	
}

function addmoredatafetch1(){
	
	var ser = 0;
	var ind =1;
	<c:forEach var="j" items="${edit_expchild}" varStatus="num"> 
	//alert('${edit_expchild}');
		if(ser != "0"){
			formopen_att(ser);
		}
		var id = "${j[6]}";
		var inst_name = "${j[0]}";
		
		var depart_name = "${j[1]}";
		var desig = "${j[2]}";
		var employment_type = "${j[8]}";
		var fd = "${j[3]}"
		var td = "${j[4]}";
		var upload_file = "${j[5]}";
		var honorarium = "${j[9]}";
		var yr_of_exp = "${j[7]}";
		
	
// 		fd = fd.substring(0,10);
// 		td = td.substring(0,10);
		
// 		var from_date = fd;
//         var dob= from_date.substring(0,10);
//         var dob_y = dob.substring(0,4);
//         var dob_m = dob.substring(5,7);
//         var dob_d = dob.substring(8,10);
//          var dob_f = dob_d+"/"+dob_m+"/"+dob_y;
         
//          var to_date = td;
//          var dob= to_date.substring(0,10);
//          var dob_y = dob.substring(0,4);
//          var dob_m = dob.substring(5,7);
//          var dob_d = dob.substring(8,10);
//           var dob_t = dob_d+"/"+dob_m+"/"+dob_y;
		
        $("#institute_name"+ind).val(inst_name);
        $("#profession_id"+ind).val(id);
  		$("#desig"+ind).val(desig);
  		$("#employment_type"+ind).val(employment_type);
  		$("#depart_name"+ind).val(depart_name);
  		$("#from_date"+ind).val(fd);
  		$("#to_date"+ind).val(td);
  		$("#yr_of_exp"+ind).val(yr_of_exp);
  		$("#honorarium"+ind).val(honorarium);
  		$("#upload_file_hid"+ind).val(upload_file);
  		
  		if('${data[0].status}'==1){
			
			 $('#id_add_att'+ind).toggleClass("d-none");
			 $('#att_id_remove'+ind).toggleClass("d-none");
			 
		}
	 
		
		ser=parseInt(ser)+1;
		ind=parseInt(ind)+1;
		
	</c:forEach>

}

function addmoredatafetch2(){

	var ser = 0;
	var ind =1;
	<c:forEach var="j" items="${edit_expchild_2}" varStatus="num"> 
		if(ser != "0"){
			formopen_doc(ser);
		}
		
		var doc_name = "${j[0]}";
		var doc_id = "${j[1]}";
		var upload_doc = "${j[2]}";
		var id = "${j[3]}";
		
		
		$("#doc_name"+ind).val(doc_name);
		GetTypeFromDoc(ind);
		 $("#add_quali"+ind).val(id);
		$("#doc_name"+ind).change();
		
		$("#doc_id"+ind).val(doc_id);
		formateOfDoc(ind);
// 		$("#doc_id"+ind).change();
		$("#doc_upload_hid"+ind).val(upload_doc);
		
		if('${data[0].status}'==1){
			
			 $('#id_add_doc'+ind).toggleClass("d-none");
			 $('#doc_id_remove'+ind).toggleClass("d-none");
			 
		}
		
		
		ser=parseInt(ser)+1;
		ind=parseInt(ind)+1;
		
	</c:forEach>

}

////////Riddhi_18_7_22
/* function addmoredatafetch3(){

	var ser = 0;
	var ind =1;
	<c:forEach var="j" items="${edit_expchild_3}" varStatus="num"> 
		if(ser != "0"){
			formOpenNameMed(ser);
		}
		var typeOfDegree = "${j[1]}";
		var degreeName = "${j[2]}";
		var monthYearOfDegree = "${j[3]}";
		var rollno = "${j[4]}";
		var NameOfUniversity = "${j[5]}";
		var percentage = "${j[6]}";

		$("#typeOfDegree"+ind).val(typeOfDegree);
		$("#typeOfDegree"+ind).change();
		
		$("#degreeName"+ind).val(degreeName);
		$("#degreeName"+ind).change();
		
		$("#monthYearOfDegree"+ind).val(monthYearOfDegree);
		$("#monthYearOfDegree"+ind).change();
		
		$("#rollno"+ind).val(rollno);
		$("#rollno"+ind).change();
		
		$("#NameOfUniversity"+ind).val(NameOfUniversity);
		$("#NameOfUniversity"+ind).change();
		
		$("#percentage"+ind).val(percentage);
		$("#percentage"+ind).change();
		
		
		ser=parseInt(ser)+1;
		ind=parseInt(ind)+1;
		
	</c:forEach>

} */

////////Rahul_19_7_22
function addmoredatafetch3(){

	debugger;
	var ser = 0;
	var ind =1;
	<c:forEach var="j" items="${edit_expchild_3}" varStatus="num"> 
		if(ser != "0"){
			formOpenNameMed(ser);
		}
		var id = "${j[0]}";
		var qulification_child_id="${j[0]}";
		var typeOfDegree = "${j[1]}";
		var course = "${j[2]}";
		var subject = "${j[3]}";
		var NameOfUniversity = "${j[4]}";
		
		var NameOfAffUniversity = "${j[5]}";
		
		var monthYearOfDegree = "${j[6]}";
// 		var rollno = "${j[4]}";
// 		var percentage = "${j[6]}";

		$("#typeOfDegree"+ind).val(typeOfDegree);
		$("#typeOfDegree"+ind).change();
		GetCoursebytype(ind);
		$("#course"+ind).val(course);
		GetSubfromCourse(ind);
		
		$("#subject"+ind).val(subject);
		$("#qualtification_id"+ind).val(id);
		$("#monthYearOfDegree"+ind).val(monthYearOfDegree);
		$("#monthYearOfDegree"+ind).change();
		$("#NameOfUniversity"+ind).val(NameOfUniversity);
		$("#NameOfUniversity"+ind).change();
		getuniversitybyinst(ind);
		
		$("#NameOfAffUni"+ind).val(NameOfAffUniversity);
		if(NameOfAffUniversity.toUpperCase()== "OTHER"){
			$("#NameOfUniversity"+ind).val(NameOfUniversity.toUpperCase());
			$("#universityother"+ind).show();
			$("#universityother"+ind).val('${j[10]}');
		}else{
			$("#universityother"+ind).hide();
		}
// 		$("#rollno"+ind).val(rollno);
// 		$("#rollno"+ind).change();
// 		$("#percentage"+ind).val(percentage);
// 		$("#percentage"+ind).change();
		
		if("${getqualificationchildAttchment.size() > 0}"){
			
		var k = 1;
		
			<c:forEach var="k" items="${getqualificationchildAttchment}" varStatus="num">
			
			var parent_id= "${k[1]}";
			var id= "${k[0]}";
			var path_of_att="${k[3]}";
			var name_of_att="${k[2]}";
			var cert_type="${k[4]}";
			
 			if(qulification_child_id == parent_id){
				dynamicTable(ind,k);
				
				if(k > 1){
					$("#id_add_attDoc"+ind+"_"+(k-1)).click();
				}
				
				if(name_of_att != null && name_of_att.trim() != ""){
					$("#name_of_attachment"+ind+"_"+k).val(name_of_att);
					$("#medicalqualificationAtt_id"+ind+"_"+k).val(id);
					degreehide(ind,k);
					if(name_of_att == "3"){
						$("#name_of_degree"+ind+"_"+k).val(cert_type);
					}
				}
				
				if(path_of_att != null && path_of_att.trim() != ""){
					$("#attachmentDoc_hid"+ind+"_"+k).val(path_of_att);
				}
				
				$("#count_hidden_att_doc"+ind).val(k);
				
				k++;
			}
			</c:forEach>			
		}
		
		
		if('${data[0].status}'==1){
			
			 $('#id_add_attNameMed'+ind).toggleClass("d-none");
			 $('#att_id_removeNameMed'+ind).toggleClass("d-none");
			 
		}
		
		ser=parseInt(ser)+1;
		ind=parseInt(ind)+1;
		
	</c:forEach>
}

function addmoredatafetch4(){

	var ser = 0;
	var ind =1;
	<c:forEach var="j" items="${edit_expchild_4}" varStatus="num"> 
		if(ser != "0"){
			formOpenOthQuali(ser);
		}
		var id = "${j[0]}";
		var name_of_exam_degree = "${j[2]}";
		var name_of_uni_inst = "${j[3]}";
		var name_of_aff_uni = "${j[4]}";
		var month_and_year = "${j[5]}";
		var subject = "${j[6]}";

		$("#OthnameofExDeg"+ind).val(name_of_exam_degree);
		$("#othquali_id"+ind).val(id);
		$("#othSubject"+ind).val(subject);
		$("#othUniInst"+ind).val(name_of_uni_inst);
		$("#othAffuni"+ind).val(name_of_aff_uni);
		$("#othYrofpass"+ind).val(month_and_year);
		
		if("${getqualificationchildAttchment.size() > 0}"){
			
		var k = 1;
		
			<c:forEach var="k" items="${getotherQualiAttSubchild}" varStatus="num">
			var parent_id= "${k[1]}";
			var child_id= "${k[0]}";
			var name_of_att="${k[2]}";
			var path_of_att="${k[3]}";
			
			if(parent_id == id){
				dynamicTableOQ(ind,k);
				
				if(k > 1){
					$("#id_add_attOQ"+ind+"_"+(k-1)).click();
				}
				
				if(name_of_att != null && name_of_att.trim() != ""){
					$("#name_of_attachmentOQ"+ind+"_"+k).val(name_of_att);
					$("#otherqualificationAtt_id"+ind+"_"+k).val(child_id);
				}
				
				if(path_of_att != null && path_of_att.trim() != ""){
					$("#attachmentDoc_hidOQ"+ind+"_"+k).val(path_of_att);
				}
				
				$("#count_hidden_att_OQ"+ind).val(k);
				k++;
			}
			</c:forEach>			
		}
		
		
		if('${data[0].status}'==1){
			
			 $('#id_add_attOthQuali'+ind).toggleClass("d-none");
			 $('#att_id_removeOthQuali'+ind).toggleClass("d-none");
			 
		}
		
		
		ser=parseInt(ser)+1;
		ind=parseInt(ind)+1;
		
	</c:forEach>
}



						function addmoredatafetch5(){
							debugger;
							var ser = 0;
							var ind =1;
							<c:forEach var="j" items="${Type_of_exp_acad}" varStatus="num"> 
							//alert('${edit_expchild}');
								if(ser != "0"){
									formopen_att_pro(ser);
								}
								var id = "${j[0]}";
								var organization_name = "${j[3]}";
								var fd = "${j[4]}"
								var td = "${j[5]}";
								var designation = "${j[6]}";
								var type_of_exp="${j[1]}";
								var yr_of_exp = "${j[7]}";
								var otherexp ="${j[2]}";
								
							

						        $("#organization_name"+ind).val(organization_name);
						        $("#academic_exp"+ind).val(id);
						  		$("#from_date_pro"+ind).val(fd);
						  		$("#to_date_pro"+ind).val(td);
						  		$("#yr_of_exp_pro"+ind).val(yr_of_exp);
						  		$("#designation_name"+ind).val(designation);
						  		$("#type_of_exp"+ind).val(type_of_exp);
						  		if(type_of_exp == "-1"){
									$("#type_of_exp"+ind).val(type_of_exp);
									$("#experienceother"+ind).show();
									$("#experienceother"+ind).val(otherexp);
								}else{
									$("#experienceother"+ind).hide();
								}

						  		if('${data[0].status}'==1){
									
									 $('#id_add_att_pro'+ind).toggleClass("d-none");
									 $('#att_id_remove_pro'+ind).toggleClass("d-none");
									 
								}
								ser=parseInt(ser)+1;
								ind=parseInt(ind)+1;
								
							</c:forEach>
							

						}





function isValid() {
	
	 
	if ($("#cand_prefix").val() == "0") {
		alert("In 'Personal Details Tab' Please Select the Candidate Prifix");
		$("#cand_prefix").focus();
		return false;
	    }
	    
	if ($("#first_name").val().trim() == "") {
		alert("In 'Personal Details Tab' Please Enter the First Name");
		$("#first_name").focus();
		return false;
	}

	if ($("#last_name").val().trim() == "") {
		alert("In 'Personal Details Tab'Please Enter the Last Name");
		$("#last_name").focus();
		return false;
	}
	if( $("#gender").val() == "0" ){
		alert("In 'Personal Details Tab' Please Select Gender");
		$("#gender").focus();
		return false;
   	}
	if( $("#date_of_birth").val() == "" ||  $("#date_of_birth").val() == "DD/MM/YYYY"){
		alert("In 'Personal Details Tab' Please Select Date of Birth");
		$("#date_of_birth").focus();
		return false;
   	}
	
	var yrr=$("#yrr").val();
	 if(yrr < 17 || yrr == "" || yrr == "0"){
	    	alert("In 'Personal Details Tab' Age Should Be Greater Than 17 Years")
	    	$("#date_of_birth").focus();
	    	return false;
	    }
// 	if ($("#date_of_birth").val() == "" || $("#date_of_birth").val() == "DD/MM/YYYY") {
// 		alert("Please Select the Date of Birth");
// 		$("#date_of_birth").focus();
// 		return false;
// 	}
// 	var yrr=$("#yrr").val()
// 	 if(yrr < 18 || yrr == "" || yrr == "0"){
// 	    	alert("Please Enter Valid Date of Birth")
// 	    	$("#date_of_birth").focus();
// 	    	return false;
// 	    }
	if ($("#father_name").val().trim() == "") {
		alert("In 'Personal Details Tab' Please Enter the Father's Name");
		$("#father_name").focus();
		return false;
	}
	if ($("#mother_name").val().trim() == "") {
		alert("In 'Personal Details Tab' Please Enter the Mother's Name");
		$("#mother_name").focus();
		return false;
	}

	if($("#mobile_no").val() == ""){
		alert("In 'Personal Details Tab' Please Enter Mobile Number");
		$("#mobile_no").focus();
		return false;
	}
	if($("#email").val() == ""){
		alert("In 'Personal Details Tab' Please Enter Email Address");
		$("#email").focus();
		return false;
	}
	
	var an1 =  $("#aadhar_no1").val();
	if( an1 == "" ){
		alert("In 'Personal Details Tab' Enter First Four Digit Of Aadhaar No");
		$("#aadhar_no1").focus();
		return false;
   	}
	
	if( parseInt(an1) <= 0){
		alert("In 'Personal Details Tab' Please Enter Valid First Four Digit of Aadhar No");
		$("#aadhar_no1").focus();
		return false;
   	}
	 var minLength = 4;
	 var charLength = an1.length;
       if(charLength < minLength){
       	alert("In 'Personal Details Tab' Please Enter Valid First Four Digit of Aadhar No");
			$("input#aadhar_no1").focus();
			return false;
       }
	
	var an2 =  $("#aadhar_no2").val();
	if( an2 == ""){
		alert("In 'Personal Details Tab' Enter Second Four Digit Of Aadhaar No");
		$("#aadhar_no2").focus();
		return false;
   	}
	if(   parseInt(an2) <= 0){
		alert("In 'Personal Details Tab' Please Enter Valid Second Four Digit Of Aadhar No");
		$("#aadhar_no2").focus();
		return false;
   	}
	
	 var minLength = 4;
	 var charLength = an2.length;
       if(charLength < minLength){
       	alert("In 'Personal Details Tab' Please Enter Valid Second Four Digit Of Aadhar No");
			$("input#aadhar_no2").focus();
			return false;
       }
	
	var an3 =  $("#aadhar_no3").val();
	
	if(an3 == ""){
		alert("In 'Personal Details Tab' Enter Last Four Digit Of Aadhaar No");
		$("#aadhar_no3").focus();
		return false;
   	}
	if(parseInt(an3) <= 0){
		alert("In 'Personal Details Tab' Please Enter Valid Last Four Digit Of Aadhar No");
		$("#aadhar_no3").focus();
		return false;
   	}
	var minLength = 4;
	 var charLength = an3.length;
       if(charLength < minLength){
       	alert("In 'Personal Details Tab' Please Enter Valid Last Four Digit Of Aadhar No");
			$("input#aadhar_no3").focus();
			return false;
       }
       
	if($("#pan_no").val() == ""){
		alert("In 'Personal Details Tab' Please Enter PAN No.");
		$("#pan_no").focus();
		return false;
	}

	///////Permanent Address validation
	if ($("#per_add_line1").val().trim() == "") {
		alert("In 'Personal Details Tab' Please Enter the Present Address-Line-1");
		$("#per_add_line1").focus();
		return false;
	} 
	if ($("#per_add_line2").val().trim() == "") {
		alert("In 'Personal Details Tab' Please Enter the Present Address-Line-2");
		$("#per_add_line2").focus();
		return false;
	} 
	if( $("#per_state").val() == "0" ){
		alert("In 'Personal Details Tab' Please Select Present State");
		$("#per_state").focus();
		return false;
   	}
	if( $("#per_district").val() == "0" ){
		alert("In 'Personal Details Tab'Please Select Present District");
		$("#per_district").focus();
		return false;
   	}
	if ($("#per_village").val().trim() == "") {
		alert("In 'Personal Details Tab' Please Enter the Present City/Village");
		$("#per_village").focus();
		return false;
   	}
	if ($("#per_pincode").val().trim() == "") {
		alert("In 'Personal Details Tab' Please Enter the Present Pin code");
		$("#per_pincode").focus();
		return false;
   	}

	if($("#per_pincode").val() == "000000"){
		alert("In 'Personal Details Tab' Please Enter the Valid Present Pin Code");
		$("#per_pincode").focus();
		return false;
	}
// 	if ($("#per_phn_no").val().trim() == "") {
// 		alert("Please Enter the Permanent Phone No.");
// 		$("#per_phn_no").focus();
// 		return false;
//    	}
	if(document.getElementById('check_address').checked == false) { 
		if ($("#present_add_line1").val().trim() == "") {
			alert("In 'Personal Details Tab' Please Enter the Correspondence Address-Line-1");
			$("#present_add_line1").focus();
			return false;
		}
		if ($("#present_add_line2").val().trim() == "") {
			alert("In 'Personal Details Tab' Please Enter the Correspondence Address-Line-2");
			$("#present_add_line2").focus();
			return false;
		}
		if( $("#present_state").val() == "0" ){
			alert("In 'Personal Details Tab' Please Select Correspondence State");
			$("#present_state").focus();
			return false;
	   	}
		if( $("#present_district").val() == "0" ){
			alert("In 'Personal Details Tab' Please Select Correspondence District");
			$("#present_district").focus();
			return false;
	   	}
		if ($("#present_village").val().trim() == "") {
			alert("In 'Personal Details Tab' Please Enter the Correspondence City/Village");
			$("#present_village").focus();
			return false;
	   	}
		if($("#present_pincode").val() ==""){
			alert("In 'Personal Details Tab' Please Enter the Correspondence Pin Code");
			$("#present_pincode").focus();
			return false;
		}
		if($("#present_pincode").val() =="000000"){
			alert("In 'Personal Details Tab' Please Enter the Valid Correspondence Pin Code");
			$("#present_pincode").focus();
			return false;
		}

// 		if ($("#present_phn_no").val().trim() == "") {
// 			alert("Please Enter the Present Phone No.");
// 			$("#present_phn_no").focus();
// 			return false;
// 	   	}
	}

	///Qualification------------------Harsh_27_07_22
	 for(qua = 1; qua <= $('#count_hidden_att_name_med').val(); qua++){
		if($("#typeOfDegree"+qua).val()=='0'){
			alert("In 'Qualification Tab' Please Select Name of Exam/Degree In "+qua+" Row");
			$("#typeOfDegree"+qua).focus();
			return false;
		}
		if($("#course"+qua).val()=='0'){
			alert("In 'Qualification Tab' Please Select Course Name In "+qua+" Row");
			$("#course"+qua).focus();
			return false;
		}
		
// 		if ($("#hide_sub"+qua).css("display") == "block") {
// 			if($("#subject"+qua).val() == '0'){
// 			alert("In 'Qualification Tab' Please Select Subject In "+qua+" Row");
// 			$("#subject"+qua).focus();
// 			return false;
// 		}
	//}
		
		if($("#NameOfUniversity"+qua).val()=='0'){
			alert("In 'Qualification Tab' Please Enter Name of Institute/College In "+qua+" Row");
			$("#NameOfUniversity"+qua).focus();
			return false;
		}
		if($("#NameOfAffUni"+qua).val()=='0'){
			alert("In 'Qualification Tab' Please Enter Name of Affiliated University In "+qua+" Row");
			$("#NameOfAffUni"+qua).focus();
			return false;
		}
		if($("input#monthYearOfDegree"+qua).val().trim()=="" ){
			alert("In 'Qualification Tab' Please Enter Month and Year Of Passing"+qua+" Row");
			$("#monthYearOfDegree"+qua).focus();
			return false;
		}

		dynamicTable(1,qua);
		
		
		console.log("gfdf------>   "+qua)
		
		
		for(a1 = 1; a1 <= $('#count_hidden_att_doc'+1).val(); a1++){
			
			//alert("qua------   "+qua+  "   a1 ------    "+a1);
			
			if($("#name_of_attachment"+qua+"_"+a1).val()=='0'){
					alert("In 'Qualification Tab' Please Select Name of Attachment In "+a1+" Row");
					$("#name_of_attachment"+qua+"_"+a1).focus();
					return false;
			}
			if($("#name_of_attachment"+qua+"_"+a1).val()=='3'){
				if($("#name_of_degree"+qua+"_"+a1).val()=='0'){
						alert("In 'Qualification Tab' Please Select Certificate Type In "+a1+" Row");
						$("#name_of_degree"+qua+"_"+a1).focus();
						return false;
				}
			}
			if($("#attachmentDocument"+qua+"_"+a1).val()==''){
				if($("#attachmentDoc_hid"+qua+"_"+a1).val()==''){
						alert("In 'Qualification Tab' Please Select Attachment File In "+a1+" Row");
						$("#name_of_degree"+qua+"_"+a1).focus();
						return false;
				}
			}
		 }
}
	
// 	 for(oth_qua = 1; oth_qua <= $('#count_hidden_att_oth_quali').val(); oth_qua++){
// 			if($("#OthnameofExDeg"+oth_qua).val()==''){
// 				alert("Please Enter Name of Exam/Degree In other "+oth_qua+" Row");
// 				$("#OthnameofExDeg"+oth_qua).focus();
// 				return false;
// 			}
// 			if($("#othSubject"+oth_qua).val()==''){
// 				alert("Please Enter Subject In "+oth_qua+" Row");
// 				$("#othSubject"+oth_qua).focus();
// 				return false;
// 			}
// 			if($("#othUniInst"+oth_qua).val()==''){
// 				alert("Please Enter Name of University/Institute In "+oth_qua+" Row");
// 				$("#othUniInst"+oth_qua).focus();
// 				return false;
// 			}
// 			if($("#othAffuni"+oth_qua).val()==''){
// 				alert("Please Enter Name of Affiliated University In "+oth_qua+" Row");
// 				$("#othAffuni"+oth_qua).focus();
// 				return false;
// 			}
// 			if($("input#othYrofpass"+oth_qua).val().trim()=="" ){
// 				alert("Please Enter Month and Year Of Degree"+oth_qua+" Row");
// 				$("#othYrofpass"+oth_qua).focus();
// 				return false;
// 			}

// 			for(a1 = 1; a1 <= $('#count_hidden_att_OQ'+oth_qua).val(); a1++){
// 				if($("#name_of_attachmentOQ"+oth_qua+"_"+a1).val()=='0'){
// 						alert("Please Select Name of Attachment In "+a1+" Row");
// 						$("#name_of_attachmentOQ"+oth_qua+"_"+a1).focus();
// 						return false;
// 				}
// // 				if($("#name_of_attachment"+oth_qua+"_"+a1).val()=='3'){
// // 					if($("#name_of_degree"+oth_qua+"_"+a1).val()=='0'){
// // 							alert("Please Select Certificate Type In "+a1+" Row");
// // 							$("#name_of_degree"+oth_qua+"_"+a1).focus();
// // 							return false;
// // 					}
// // 				}
// 				if($("#attachmentDocumentOQ"+oth_qua+"_"+a1).val()==''){
// 					if($("#attachmentDoc_hidOQ"+oth_qua+"_"+a1).val()==''){
// 							alert("Please Select Attachment File In "+a1+" Row");
// 							$("#attachmentDocumentOQ"+oth_qua+"_"+a1).focus();
// 							return false;
// 					}
// 				}
// 			 }
// 	}
	
////////Registration Details	
	if ($("#registration_type").val() == "0") {
		alert("In 'Registration Details Tab' Please Select the Registration Type");
		$("#registration_type").focus();
		return false;
	}
	if ($("#registration_type").val() == "1") {
	
		if ($("#state_reg_no").val().trim() == "") {
			alert("In 'Registration Details Tab' Please Enter the State Registration No.");
			$("#state_reg_no").focus();
			return false;
	   	}
		if($("#state_reg_no").val() =="0" || $("#state_reg_no").val() == " "){
			alert("In 'Registration Details Tab' Please Enter the Valid State Registration No.");
			$("#state_reg_no").focus();
			return false;
		}
		
		if ($("#state_board_name").val().trim() == "0") {
			alert("In 'Registration Details Tab' Please Select the Name of the State Board");
			$("#state_board_name").focus();
			return false;
	   	}
		if ($("#date_of_reg").val() == "" || $("#date_of_reg").val() == "DD/MM/YYYY") {
			alert("In 'Registration Details Tab' Please Select the Date Of Registration");
			$("#date_of_reg").focus();
			return false;
		}
		if ($("#state_validity_upto").val() == "" || $("#state_validity_upto").val() == "DD/MM/YYYY") {
			alert("In 'Registration Details Tab' Please Select the Validity Upto");
			$("#state_validity_upto").focus();
			return false;
		}
		if(getformatedDate($("input#date_of_reg").val()) > getformatedDate($("#state_validity_upto").val())) {
			alert("Validity upto should be Greater than Or Equal to Date of Reg  ");
			$("#state_validity_upto").focus();
			return false;
	    }
// 		if ($("#central_reg_no").val().trim() == "") {
// 			alert("Please Enter the Central Registration No.");
// 			$("#central_reg_no").focus();
// 			return false;
// 	   	}
		
		if($("#central_reg_no").val() =="0"){
			alert("In 'Registration Details Tab' Please Enter the Valid Registration No.");
			$("#central_reg_no").focus();
			return false;
		}
		
		
}	
if ($("#registration_type").val() == "2") {
	
	if ($("#direct_reg_no").val().trim() == "") {
		alert("In 'Registration Details Tab' Please Enter the Direct Registration No.");
		$("#direct_reg_no").focus();
		return false;
   	}
	if($("#direct_reg_no").val() =="0"){
		alert("In 'Registration Details Tab' Please Enter the Valid Direct Registration No.");
		$("#direct_reg_no").focus();
		return false;
	}
	if ($("#direct_date_of_reg").val() == "" || $("#direct_date_of_reg").val() == "DD/MM/YYYY") {
		alert("In 'Registration Details Tab' Please Select the Direct Date Of Registration");
		$("#direct_date_of_reg").focus();
		return false;
	}
	if ($("#name_of_department").val().trim() == "") {
		alert("In 'Registration Details Tab' Please Enter the Name of Department/Organization");
		$("#name_of_department").focus();
		return false;
   	}
	if ($("#direct_validity_upto").val() == "" || $("#direct_validity_upto").val() == "DD/MM/YYYY") {
		alert("In 'Registration Details Tab' Please Select the Validity Upto");
		$("#direct_validity_upto").focus();
		return false;
	}
}
////////Previous Experience		

		for(exp = 1; exp <= $('#count_hidden_att').val(); exp++){
			if($("#institute_name"+exp).val().trim()==''){
				alert("In 'Academic Experience Tab' Please Enter Organization Name In "+exp+" Row");
				$("#institute_name"+exp).focus();
				return false;
			}
			if($("#depart_name"+exp).val()=='0'){
				alert("In 'Academic Experience Tab' Please Select Department Name In "+exp+" Row");
				$("#depart_name"+exp).focus();
				return false;
			}
			if($("#desig"+exp).val()=='0'){
				alert("In 'Academic Experience Tab' Please Select Designation/Post In "+exp+" Row");
				$("#desig"+exp).focus();
				return false;
			}
			if($("#employment_type"+exp).val()=='0'){
				alert("In 'Academic Experience Tab' Please Select Employment Type In "+exp+" Row");
				$("#employment_type"+exp).focus();
				return false;
			}
			
			if($("input#from_date"+exp).val().trim()=="" || $("input#from_date"+exp).val().trim()=="DD/MM/YYYY"){
				alert("In 'Academic Experience Tab' Please Enter From Date In"+exp+" Row");
				$("#from_date"+exp).focus();
				return false;
			}
			if($("input#to_date"+exp).val().trim()=="" || $("input#to_date"+exp).val().trim()=="DD/MM/YYYY"){
				alert("In 'Academic Experience Tab' Please Enter To Date In"+exp+" Row");
				$("#to_date"+exp).focus();
				return false;
			}
			if(getformatedDate($("input#from_date"+exp).val()) > getformatedDate($("#to_date"+exp).val())) {
				alert("In 'Academic Experience Tab' To Date should be Greater than Or Equal to From Date In "+exp+" Row");
				$("#to_date"+exp).focus();
				return false;
		    }
			if($("#honorarium"+exp).val()=='0'){
				alert("In 'Academic Experience Tab' Please Select Honorarium In "+exp+" Row");
				$("#honorarium"+exp).focus();
				return false;
			}
			//upload_file1
			
			 if($("#upload_file"+exp).val().trim() == ""){
				 if($("#upload_file_hid"+exp).val().trim() == ""){
					 alert("In 'Academic Experience Tab' Please Select Upload File In Academic Experience In "+exp+" Row");
					 	$("input#upload_file"+exp).focus();
						return false;
				 }
			}
// 			/*  if($("#data_fetch").val() == "1"){
// 			 	if($("#upload_file_hid"+exp).val() == ""){	
// 				 		alert("Please Select File "+exp+" Row");
// 				 		 $("input#upload_file"+exp).focus();
// 						 return false;
// 				}
// 			}else{
// 			 	if($("#upload_file"+exp).val() == ""){
// 	 			 	alert("Please Select File "+exp+" Row");
// 	 			 	$("input#upload_file"+exp).focus();
// 	 				return false;
// 	 			 }
// 			 }  
		
		if($("#institute_name"+exp).val() != "0" && $("#institute_name"+exp).val() != "1")
		//	count_classi++;
		if($("#institute_name"+exp).val() != "1"){
			if($("#institute_name"+exp).val().trim()==''){
				alert("In 'Academic Experience Tab' Please Enter Organization Name In "+exp+" Row");
				$("#institute_name"+exp).focus();
				return false;
			}
			if($("#depart_name"+exp).val()=='0'){
				alert("In 'Academic Experience Tab' Please Select Department Name In "+exp+" Row");
				$("#depart_name"+exp).focus();
				return false;
			}
			if($("#desig"+exp).val()=='0'){
				alert("In 'Academic Experience Tab' Please Select Designation In "+exp+" Row");
				$("#desig"+exp).focus();
				return false;
			}
			if($("#employment_type"+exp).val()=='0'){
				alert("In 'Academic Experience Tab' Please Select Employment Type In "+exp+" Row");
				$("#employment_type"+exp).focus();
				return false;
			}
			if($("input#from_date"+exp).val().trim()=="" || $("input#from_date"+exp).val().trim()=="DD/MM/YYYY"){
				alert("In 'Academic Experience Tab' Please Enter From Date"+exp+" Row");
				$("#from_date"+exp).focus();
				return false;
			}
			if($("input#to_date"+exp).val().trim()=="" || $("input#to_date"+exp).val().trim()=="DD/MM/YYYY"){
				alert("In 'Academic Experience Tab' Please Enter To Date "+exp+" Row");
				$("#to_date"+exp).focus();
				return false;
			}
			if(getformatedDate($("input#from_date"+exp).val()) > getformatedDate($("#to_date"+exp).val())) {
				alert("In 'Academic Experience Tab' To Date should be Greater than Or Equal to From Date In "+exp+" Row");
				$("#to_date"+exp).focus();
				return false;
		    }
			if($("#honorarium"+exp).val()=='0'){
				alert("In 'Academic Experience Tab' Please Select Honorarium In "+exp+" Row");
				$("#honorarium"+exp).focus();
				return false;
			}
			 if($("#upload_file"+exp).val().trim() == ""){
				 if($("#upload_file_hid"+exp).val().trim() == ""){
					 alert("In 'Academic Experience Tab' Please Select Upload File In Profession/Academic Experience  "+exp+" Row");
					 	$("input#upload_file"+exp).focus();
						return false;
				 }
			}
// 			/* if($("#data_fetch").val() == "1"){
// 				 if($("#upload_file_hid"+exp).val() == ""){	
// 					alert("Please Select File "+exp+" Row");
// 					 $("input#upload_file"+exp).focus();
// 					 return false;
// 				 }
// 			}else{
// 			 	 if($("#upload_file"+exp).val() == ""){
// 	 			 	alert("Please Select File "+exp+" Row");
// 	 			 	$("input#upload_file"+exp).focus();
// 	 				return false;
// 	 			 }
// 			 } 
		}	
	}

/////----------------------------------------------------------------------------------------------




		for(exp = 1; exp <= $('#count_hidden_att_pro').val(); exp++){
			
			
			if($("#organization_name"+exp).val().trim()==''){
				alert("In 'Profession Experience Tab' Please Enter Organization Name In "+exp+" Row");
				$("#organization_name"+exp).focus();
				return false;
			}
			
			
			if($("input#from_date_pro"+exp).val().trim()=="" || $("input#from_date_pro"+exp).val().trim()=="DD/MM/YYYY"){
				alert("In 'Profession Experience Tab' Please Enter From Date In "+exp+" Row");
				$("#from_date_pro"+exp).focus();
				return false;
			}
			if($("input#to_date_pro"+exp).val().trim()=="" || $("input#to_date_pro"+exp).val().trim()=="DD/MM/YYYY"){
				alert("In 'Profession Experience Tab' Please Enter To Date In "+exp+" Row");
				$("#to_date_pro"+exp).focus();
				return false;
			}
			if(getformatedDate($("input#from_date_pro"+exp).val()) > getformatedDate($("#to_date_pro"+exp).val())) {
				alert("In 'Profession Experience Tab' To Date should be Greater than Or Equal to From Date  In "+exp+" Row");
				$("#to_date_pro"+exp).focus();
				return false;
		    }
			
			if($("#designation_name"+exp).val().trim()==''){
				alert("In 'Profession Experience Tab' Please Enter Designation Name In "+exp+" Row");
				$("#designation_name"+exp).focus();
				return false;
			}
			if($("#type_of_exp"+exp).val()=='0'){
				alert("In 'Profession Experience Tab' Please Select Type Of Experiencce In "+exp+" Row");
				$("#type_of_exp"+exp).focus();
				return false;
			}
		
		
	 
		
		if($("#organization_name"+exp).val() != "0" && $("#organization_name"+exp).val() != "1")
		//	count_classi++;
		if($("#organization_name"+exp).val() != "1"){
		
			if($("#organization_name"+exp).val().trim()==''){
				alert("In 'Profession Experience Tab' Please Enter Organization Name In "+exp+" Row");
				$("#organization_name"+exp).focus();
				return false;
			}
			
			
			if($("input#from_date_pro"+exp).val().trim()=="" || $("input#from_date_pro"+exp).val().trim()=="DD/MM/YYYY"){
				alert("In 'Profession Experience Tab' Please Enter From Date In "+exp+" Row");
				$("#from_date_pro"+exp).focus();
				return false;
			}
			if($("input#to_date_pro"+exp).val().trim()=="" || $("input#to_date_pro"+exp).val().trim()=="DD/MM/YYYY"){
				alert("In 'Profession Experience Tab' Please Enter To Date In"+exp+" Row");
				$("#to_date_pro"+exp).focus();
				return false;
			}
			if(getformatedDate($("input#from_date_pro"+exp).val()) > getformatedDate($("#to_date_pro"+exp).val())) {
				alert("In 'Profession Experience Tab' To Date should be Greater than Or Equal to From Date In "+exp+" Row");
				$("#to_date_pro"+exp).focus();
				return false;
		    }
			
			if($("#designation_name"+exp).val().trim()==''){
				alert("In 'Profession Experience Tab' Please Enter Designation Name In "+exp+" Row");
				$("#designation_name"+exp).focus();
				return false;
			}
			if($("#type_of_exp"+exp).val()=='0'){
				alert("In 'Profession Experience Tab' Please Select Type Of Experiencce In "+exp+" Row");
				$("#type_of_exp"+exp).focus();
				return false;
			}

		}	
	}





//////////----------------------------------------------------------------------------------








////////Degree and Supporting Document	


	for(deg = 1; deg <= $('#count_hidden_doc').val(); deg++){
			
			if($("#doc_name"+deg).val()=='0'){
				alert("In 'Additional Documents Tab' Please Select Document Name In "+deg+" Row");
				$("#doc_name"+deg).focus();
				return false;
			}
			if($("#doc_id"+deg).val()=='0'){
				alert("In 'Additional Documents Tab' Please Select Document Type In "+deg+" Row");
				$("#doc_id"+deg).focus();
				return false;
			}
			//upload_doc1
			
			 if($("#upload_doc"+deg).val().trim() == ""){
				 if($("#doc_upload_hid"+deg).val().trim() == ""){
					 alert("In 'Additional Documents Tab' Please Select Upload File In Additional Qalification "+exp+" Row");
					 	$("input#upload_doc"+deg).focus();
						return false;
				 }
			}
			
// 			/*  if($("#data_fetch").val() == "1"){
// 			 	 if($("#doc_upload_hid"+deg).val() == ""){	
// 					 alert("Please Select Document "+deg+" Row");
// 					 $("input#upload_doc"+deg).focus();
// 					 return false;
// 			 	 }
// 			}else{
// 				if($("#upload_doc"+deg).val() == ""){
// 					alert("Please Select Document "+deg+" Row");
// 					$("input#upload_doc"+deg).focus();
// 					return false;
// 				}
// 			}  
		
			if($("#doc_name"+deg).val() != "0" && $("#doc_name"+deg).val() != "1")
			//	count_classi++;
			if($("#doc_name"+deg).val() != "1"){
			if($("#doc_name"+deg).val()=='0'){
				alert("In 'Additional Documents Tab' Please Select Document Name In "+deg+" Row");
				$("#doc_name"+deg).focus();
				return false;
			}
			if($("#doc_id"+deg).val()=='0'){
				alert("In 'Additional Documents Tab' Please Select Document Type In "+deg+" Row");
				$("#doc_id"+deg).focus();
				return false;
			}
			 if($("#upload_doc"+deg).val().trim() == ""){
				 if($("#doc_upload_hid"+deg).val().trim() == ""){
				 	alert("In 'Additional Documents Tab' Please Select Upload File In Additional Qalification "+deg+" Row");
				 	$("input#upload_doc"+deg).focus();
					return false;
				 }
			 }
// 			/*  if($("#data_fetch").val() == "1"){
// 				 if($("#doc_upload_hid"+deg).val() == ""){	
// 					alert("Please Select Document "+deg+" Row");
// 					$("input#upload_doc"+deg).focus();
// 					return false;
// 				 }
// 			}else{
// 				if($("#upload_doc"+deg).val() == ""){
// 					alert("Please Select Document "+deg+" Row");
// 					$("input#upload_doc"+deg).focus();
// 					return false;
// 				}
// 			}  
		}	
	}
	
	var checkBox = document.getElementById("checkbox1");
	if (checkBox.checked == false){	 
		
		//$("#hiddenUpdate").val(0);
			  alert("Please Accept Terms And Condition");
			  return false;
		} else{
			
			$("#hiddenUpdate").val(1);
			return true; 
		}
		
		
}

function onchangeCount(val){
		
		if (document.getElementById("from_date"+val).value != "DD/MM/YYYY" && (document.getElementById("to_date"+val).value != "DD/MM/YYYY")
				&& (document.getElementById("to_date"+val).value != "")) {
		
	var joining=document.getElementById("from_date"+val).value.split('/').reverse().join('-');  
	var dish=document.getElementById("to_date"+val).value.split('/').reverse().join('-'); 
	
	if(dish < joining){
		alert("To Date should be Greater than Or Equal to From Date")
		$("#from_date"+val).val("");
		$("#to_date"+val).val("");
		return false;
		}
	
	var joining2=new Date(joining);
	var dishcharge=new Date(dish);
	
 var a = dateDiffInDays_Months_Years(joining,dish,val)
	}
}


			function onchangeCountpro(val){
				
				if (document.getElementById("from_date_pro"+val).value != "DD/MM/YYYY" && (document.getElementById("to_date_pro"+val).value != "DD/MM/YYYY")
						&& (document.getElementById("to_date_pro"+val).value != "")) {
				
			var joining=document.getElementById("from_date_pro"+val).value.split('/').reverse().join('-');  
			var dish=document.getElementById("to_date_pro"+val).value.split('/').reverse().join('-'); 
			
			if(dish < joining){
				alert("To Date should be Greater than Or Equal to From Date")
				$("#from_date_pro"+val).val("");
				$("#to_date_pro"+val).val("");
				return false;
				}
			
			var joining2=new Date(joining);
			var dishcharge=new Date(dish);
			
			var a = dateDiffInDays_Months_Years_pro(joining,dish,val)
			}
			}

function dateDiffInDays_Months_Years(start,end,val) {
	
    var m1 = new Date(start);
    var m2 = new Date(end);
    var yDiff = m2.getFullYear() - m1.getFullYear();
    
//    var mdiff = m2.getMonth() - m1.getMonth();

   
 //   $("#yr_of_exp1").val(""+yDiff+"."+mdiff);
    var mDiff = m2.getMonth() - m1.getMonth();
   var dDiff = m2.getDate() - m1.getDate();

    if (dDiff < 0) {
        var daysInLastFullMonth = getDaysInLastFullMonth(start);
        if (daysInLastFullMonth < m1.getDate()) {
            dDiff = daysInLastFullMonth + dDiff + (m1.getDate() - daysInLastFullMonth);
        } else {
            dDiff = daysInLastFullMonth + dDiff;
        }
        mDiff--;
    }
    if (mDiff < 0) {
        mDiff = 12 + mDiff;
        yDiff--;
    }
     console.log('Y:', yDiff, ', M:', mDiff, ', D:', dDiff);
    var message = dDiff+"/"+mDiff+"/"+yDiff
    $("#yr_of_exp"+val).val(yDiff+" Year "+mDiff+" Month");
    return message
}



function dateDiffInDays_Months_Years_pro(start,end,val) {
	
    var m1 = new Date(start);
    var m2 = new Date(end);
    var yDiff = m2.getFullYear() - m1.getFullYear();
    
//    var mdiff = m2.getMonth() - m1.getMonth();

   
 //   $("#yr_of_exp1").val(""+yDiff+"."+mdiff);
    var mDiff = m2.getMonth() - m1.getMonth();
   var dDiff = m2.getDate() - m1.getDate();

    if (dDiff < 0) {
        var daysInLastFullMonth = getDaysInLastFullMonth(start);
        if (daysInLastFullMonth < m1.getDate()) {
            dDiff = daysInLastFullMonth + dDiff + (m1.getDate() - daysInLastFullMonth);
        } else {
            dDiff = daysInLastFullMonth + dDiff;
        }
        mDiff--;
    }
    if (mDiff < 0) {
        mDiff = 12 + mDiff;
        yDiff--;
    }
     console.log('Y:', yDiff, ', M:', mDiff, ', D:', dDiff);
    var message = dDiff+"/"+mDiff+"/"+yDiff
    $("#yr_of_exp_pro"+val).val(yDiff+" Year "+mDiff+" Month");
    return message
}



function getDaysInLastFullMonth(day) {
    var d = new Date(day);
    console.log(d.getDay() );
    var lastDayOfMonth = new Date(d.getFullYear(), d.getMonth() + 1, 0);
    console.log('last day of month:', lastDayOfMonth.getDate() );
    return lastDayOfMonth.getDate();
}

function dynamicTable(R,index){
	//alert("r==="+R,"index"+index);
	
	var length = $("#count_hidden_att_name_med").val();
	for(var i = 1 ;i<=length;i++){
	$("#dynamicDataTable"+i).hide();
	}
	
	if($("#dynamicDataTable"+R).length){
		$("#dynamicDataTable"+R).show();

	}else{
		if($('#dynamicDataTable'+R) != null){
			$("#dynamicDataTable").append("<div id='dynamicDataTable"+R+"'></div>");
		}


	$("div#dynamicDataTable"+R).append('<table class="table" id="addAttDoc'+R+'"><thead><tr><th><h6>Sr No</h6></th>'
			+'<th><h6>Name of Attachment</h6><span class="mandatory table-note">Degree Field  is Mandatory in Name of Attachment </span></th>'
			+'<th><h6>Certificate Type</h6></th>'
			+'<th><h6>Attachment</h6><span class="mandatory table-note">Maximum file size upto 200 kb and support file extension .pdf</span></th>'
			+'<th><h6>Action</h6></th>'
			+'</tr>'
			+'</thead>'
			+'<tbody id="att_TbbodyattDoc'+R+"_"+index+'">'
			+'	<tr id="tr_id_attDoc'+R+"_"+index+'">'
			+'		<td><p>'

			+'					1'
		
				+'		</p></td>'
			
// 				+'	<td>'

// 				+'			<div class="input-style-1">'
// 				+'			<input type="text" id="name_of_attachment'+seq_new+'"'
// 						+'				name="name_of_attachment'+seq_new+'" value=""'
// 						+'				class="form-control autocomplete" autocomplete="off"'
// 						+'				onkeypress="AutoCompleteNameOfUniversity(this);"'
// 						+'			placeholder="Name of Attachments">'
// 						+'	</div> </td>'
+'	<td>'
				+'<div class="select-style-1">'
				+'<div class="select-position">'
				+'<select name="name_of_attachment'+R+"_"+index+'" id="name_of_attachment'+R+"_"+index+'" class="form-control autocomplete" >'
				+'<option value="0">--Select--</option>'
				+'<c:forEach var="item" items="${getnameofDoc}" varStatus="num"><option value="${item.id}" name="${item.doc_name}">${item.doc_name}</option></c:forEach></select>'
				+'</div></div>'
				+' </td>'
				+'	<td>'
				+'<div class="select-style-1 hide" id="degreehide'+R+"_"+index+'">'
				+'<div class="select-position">'
				+'<select name="name_of_degree'+R+"_"+index+'" id="name_of_degree'+R+"_"+index+'" class="form-control autocomplete">'
				+'<option value="0" selected="selected"> -- Select -- </option><option value="1">Provisional Degree Certificate</option><option value="2">Final Degree certificate</option></select>'
				+'</div></div>'
				+'</td>'
						+'	<td>'

						+'		<div class="input-style-1">'
						+'			<input type="file" id="attachmentDocument'+R+"_"+index+'" name="attachmentDocument'+R+"_"+index+'"'
						+'				accept=".pdf" '
						+'			class="form-control"><input type="hidden" id="attachmentDocumenthid'+R+"_"+index+'" name="attachmentDocumenthid'+R+"_"+index+'" class="form-control" value="">'
					
						+'<div class="note-text"><span class="errorClass " id="attachmentDocument_file_lbl'+R+"_"+index+'"></span> <span class="tikClass " id="attachmentDocument_file_lbltik'+R+"_"+index+'"></span></div>'
						+'<input type="hidden" id="medicalqualificationAtt_id'+R+"_"+index+'" value="0" name="medicalqualificationAtt_id'+R+"_"+index+'"class="form-control">  <input type="hidden"id="attachmentDoc_hid'+R+"_"+index+'" name="attachmentDoc_hid'+R+"_"+index+'"class="form-control">'

						+'</div> '
						+'</td>'
						+'<td class="min-width addminusbut">'
						+'	<div class="action">'

						+'	<ul class="buttons-group mainbtn">'
						+'		<li><a'
						+'			class="main-btn success-btn btn-hover btn-sm btnaddmore"'
						+'		value="ADD" title="ADD" id="id_add_attDoc'+R+"_"+index+'" name="id_add_attDoc'+R+"_"+index+'"' 
						+'		><i'
						+'			class="lni lni-plus"></i></a></li>'
						+'	</ul>'
						+'	</div>'
				+'</td> </tr> </tbody> </table>'
				+'<input type="hidden" id="count_hidden_att_doc'+R+'" name="count_hidden_att_doc'+R+'" class="form-control autocomplete" value="1">');
	
		dynamicparent(R,index);
	}
//		$('#modelWindow').modal('show');
}

$('#btnmodel1').click(function() {
	   $('#modelWindow').modal('show');
	   dynamicTable(1,1);
	});
$('#closeModel').click(function() {
	   $('#modelWindow').modal('hide');
	});
$('#closeModel2').click(function() {
	   $('#modelWindow').modal('hide');
	});


// oth quali
$('#btnmodelOth1').click(function() {
	   $('#oth_quali_mw').modal('show');
	   dynamicTableOQ(1,1);
	});
$('#closeOQModel').click(function() {
	   $('#oth_quali_mw').modal('hide');
	});
// $('#closeModel2').click(function() {
// 	   $('#modelWindow').modal('hide');
// 	});
	

function formOpenNameMed(R){
	debugger;
	
	if(R>0){

		if($("#typeOfDegree"+R).val()=='0'){
			alert("Please Select Name of Exam/Degree in "+R+" Row");
			$("#typeOfDegree"+R).focus();
			return false;
		}
		if($("#course"+R).val()=='0'){
			alert("Please Select Couse  In "+R+" Row");
			$("#course"+R).focus();
			return false;
		}
// 		if($("#subject"+R).val()=='0'){
// 			alert("Please Select Subject In "+R+" Row");
// 			$("#subject"+R).focus();
// 			return false;
// 		} 
		if($("#NameOfUniversity"+R).val()=='0'){
			alert("Please Select Name of Institute/college In "+R+" Row");
			$("#NameOfUniversity"+R).focus();
			return false;
		}
		
		if($("#NameOfAffUni"+R).val()=='0'){
			alert("Please Select Name of Affiliated University In "+R+" Row");
			$("#NameOfAffUni"+R).focus();
			return false;
		}
		
		if($("#monthYearOfDegree"+R).val()==''){
			alert("Please Select Month & Year of Passing In "+R+" Row");
			$("#monthYearOfDegree"+R).focus();
			return false;
		}
		
	//	for(a1 = 0; a1 <= $('#count_hidden_att_doc'+R).val(); a1++){
			//		for(a1 = 1; a1 <= $('#id_add_attNameMed'+qua).val(); a1++){
					
					//alert("qua------   "+qua+  "   a1 ------    "+a1);
					
					dynamicTable(1,R);
			
		//	console.log("-------->    "+qua  + " ----->     "+ $('#count_hidden_att_doc'+qua).val());
			
			if ($('#count_hidden_att_doc'+R).val() == undefined || $('#count_hidden_att_doc'+R).val() == "undefined" ) {
				alert("Please Select Name of Attachment In "+R+" Row")
				return false;
			}
					
					
					
					
					var a1=1;
					if(a1>0){
					
					if($("#name_of_attachment"+R+"_"+a1).val()=='0'){
							alert("Please Select Name of Attachment In "+a1+" Row");
							$("#name_of_attachment"+R+"_"+a1).focus();
							return false;
					}
					if($("#name_of_attachment"+R+"_"+a1).val()=='3'){
						if($("#name_of_degree"+R+"_"+a1).val()=='0'){
								alert("Please Select Certificate Type In "+a1+" Row");
								$("#name_of_degree"+R+"_"+a1).focus();
								return false;
						}
					}
					if($("#attachmentDocument"+R+"_"+a1).val()==''){
						if($("#attachmentDoc_hid"+R+"_"+a1).val()==''){
								alert("Please Select Attachment File In "+a1+" Row");
								$("#name_of_degree"+R+"_"+a1).focus();
								return false;
						}
					}
				}
			//	 }
		
		
		
		

	if($("#typeOfDegree"+R).val() != "0" && $("#typeOfDegree"+R).val() != "1")
//		count_classi++;
	if($("#typeOfDegree"+R).val() != "1"){
		
		if($("#typeOfDegree"+R).val()=='0'){
			alert("Please Select Name of Exam/Degree in "+R+" Row");
			$("#typeOfDegree"+R).focus();
			return false;
		}
		if($("#course"+R).val()=='0'){
			alert("Please Select Couse  In "+R+" Row");
			$("#course"+R).focus();
			return false;
		}
// 		if($("#subject"+R).val()=='0'){
// 			alert("Please Select Subject In "+R+" Row");
// 			$("#subject"+R).focus();
// 			return false;
// 		}
		if($("#NameOfUniversity"+R).val()=='0'){
			alert("Please Select Name of Institute/college In "+R+" Row");
			$("#NameOfUniversity"+R).focus();
			return false;
		}
		
		if($("#NameOfAffUni"+R).val()=='0'){
			alert("Please Select Name of Affiliated University In "+R+" Row");
			$("#NameOfAffUni"+R).focus();
			return false;
		}
		
		if($("#monthYearOfDegree"+R).val()==''){
			alert("Please Select Month & Year of Passing In "+R+" Row");
			$("#monthYearOfDegree"+R).focus();
			return false;
		}
		
		
	}
		
}
	
	
	
	
	
	
	 $("#addNameOfMed").show();

	
		 $("#id_add_attNameMed"+R).hide();
		 $("#att_id_removeNameMed"+R).hide();
		 
		 att=0;
		 att= parseInt(R)+1;
	
		
		 if(att < 51){
			 $("input#count_hidden_att_name_med").val(att);//current serial No
			 $("table#addNameOfMed").append('<tr id="tr_id_attNameMed'+att+'"><td><p>'+att+'</p></td>'
			 	+'<td><div class="select-style-1"><div class="select-position"><select name="typeOfDegree'+att+'" id="typeOfDegree'+att+'" class="form-control autocomplete" "><option value="0">--Select--</option> <c:forEach var="item" items="${TypeOfDegree}" varStatus="num">	<option value="${item[0]}" name="${item[1]}">${item[1]}</option> </c:forEach></select></select></div></div></td>'
			 	+'<td><div class="select-style-1"><div class="select-position"><select name="course'+att+'" id="course'+att+'" class="form-control autocomplete" onchange="GetSubfromCourse('+att+')" "><option value="0">--Select--</option></select></select></div></div></td>'
			 	
			 	+'<td><div class="select-style-1 " id="hide_sub'+att+'"><div class="select-position">'
				+'<select name="subject'+att+'" id="subject'+att+'"class="form-control autocomplete"><option value="0">--Select--</option>'
				+'</select></div></div></td>'
			 	
				+'<td><div class="select-style-1" ><div class="select-position"><select name="NameOfUniversity'+att+'" id="NameOfUniversity'+att+'" class="form-control form-control-lg form-control-a effect-9 select2 narrow wrap">'
					+'<option value="0">--Select--</option><c:forEach var="item" items="${getInsttituteList}" varStatus="num">'
						+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></div><input type="hidden" id="qualtification_id'+att+'" name="qualtification_id'+att+'" value="0" class="form-control autocomplete"></div></td>'
				
				+'<td>  <div class="select-style-1" ><div class="select-position"><select name="NameOfAffUni'+att+'" id="NameOfAffUni'+att+'" class="form-control autocomplete">'
				+'	<option value="0">--Select--</option></select></div><input type="text"  id="universityother'+att+'" name="universityother'+att+'" class="form-control autocomplete d-none" autocomplete="off" placeholder="Enter Name of Institute"></div> </td>'
				
				
			 	+'<td><div class="input-style-1"><input type="month" id="monthYearOfDegree'+att+'" name="monthYearOfDegree'+att+'" class="form-control-sm form-control effect-9 hasDatepicker"></div</td>'
// 			 	+'<td><div class="input-style-2"><input type="text" id="rollno'+att+'" name="rollno'+att+'" value="" class="form-control autocomplete" autocomplete="off" onkeypress="AutoCompleteNameOfUniversity(this);" placeholder="Enter Roll No"></div></td>'
			 	
// 			 	+'<td><div class="input-style-2"><input type="text" id="percentage'+att+'" name="percentage'+att+'" value="" class="form-control autocomplete" autocomplete="off" onkeypress="AutoCompleteNameOfUniversity(this);" placeholder="Enter Percentage"></div></td>'
			 	+'<td><ul class="buttons-group"><li><a href="#" class="main-btn secondary-btn btn-hover btn-sm" id="btnmodel'+att+'" value="Attachment">Attachment</a></li></ul></td>'
			 	+'<td><div class="action"><ul class="buttons-group mainbtn"><li><a class="main-btn success-btn btn-hover btn-sm btnaddmore" value = "ADD" title = "ADD" id = "id_add_attNameMed'+att+'" ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove" value="REMOVE" title = "REMOVE" id = "att_id_removeNameMed'+att+'" ><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
			 	+'</tr>');
			 
			 addOnclick(att);
// 			 var $select2 = $('.select2').select2({
// 			    	containerCssClass: "wrap"
// 				});
			 
			 
			 var date = new Date();
			 var today = date.toISOString().substring(0,7);
			 $("#monthYearOfDegree"+att).attr('max',today);
			
			 var dob = $("#date_of_birth").val();
			 
			 if (dob != "DD/MM/YYYY" || dob != "") {
				var y = dob.split('/').reverse().join('-');
				var rahu = y.substring(0,7);
			  $("#monthYearOfDegree"+att+"").prop('min',rahu);
			  
			  
			 }
			 
			 
// 			 removeOnclick(att);
// 			 doc_type_oc(att);
// 			 upload_document_oc(att);
			 
			
			
			}else{
					alert("Please Enter max 50 Quantity");
					 if ( att == 51){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
	}
	



function formopen_re_NameMed(R){

	var qualtification_idhid2= $("input#qualtification_id"+R).val();
	var result = confirm("Are you sure you want to delete?");

if (result) {
	$.post("delete_quali_addmore?" + key + "=" + value, {qualtification_idhid2:qualtification_idhid2}, function(j) {
		 alert("Data Deleted Successfully.")
	 	});
	
	 $("tr#tr_id_attNameMed"+R).remove();
	 att = att-1;
	 $("input#count_hidden_att_name_med").val(att);
	 $("#id_add_attNameMed"+att).show();
	 $("#att_id_removeNameMed"+att).show();	
	 
}
}


function formOpenOthQuali(R){
	
	 $("#addothquali").show();

// 	 count_hidden_att_oth_quali----count_hidden_att_oth_quali1
	
		 $("#id_add_attOthQuali"+R).hide();
		 $("#att_id_removeOthQuali"+R).hide();
		 
		 att=0;
		 att= parseInt(R)+1;
		
		 if(att < 51){
			 $("input#count_hidden_att_oth_quali").val(att);//current serial No
			 $("table#addothquali").append('<tr id="tr_id_attOthQuali'+att+'"><td><p>'+att+'</p></td>'
			 	+'<td><div class="input-style-1"><input type="text" id="OthnameofExDeg'+att+'" name="OthnameofExDeg'+att+'" class="form-control autocomplete" placeholder="Enter Name of Exam/Degree"></div</td>'
			 	+'<td><div class="input-style-1"><input type="text" id="othSubject'+att+'" name="othSubject'+att+'" class="form-control autocomplete" placeholder="Enter Subject"></div</td>'
			 	+'<td><div class="input-style-1"><input type="text" id="othUniInst'+att+'" name="othUniInst'+att+'" class="form-control autocomplete" placeholder="Enter Name of University/Institute"></div</td>'
			 	+'<td><div class="input-style-1"><input type="text" id="othAffuni'+att+'" name="othAffuni'+att+'" class="form-control autocomplete" placeholder="Enter Name of affiliated University"><input type="hidden" id="othquali_id'+att+'" name="othquali_id'+att+'" value="0" class="form-control autocomplete" ></div></td>'
			 	+'<td><div class="input-style-1"><input type="month" id="othYrofpass'+att+'" name="othYrofpass'+att+'" class="form-control-sm form-control effect-9 hasDatepicker" ></div></td>'
			 	+'<td><ul class="buttons-group"><li><a href="#" class="main-btn secondary-btn btn-hover btn-sm" id="btnmodelOth'+att+'" value="Attachment">Attachment</a></li></ul></td>'
			 	+'<td><div class="action"><ul class="buttons-group mainbtn"><li><a class="main-btn success-btn btn-hover btn-sm btnaddmore" value = "ADD" title = "ADD" id = "id_add_attOthQuali'+att+'"><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove" value="REMOVE" title = "REMOVE" id = "att_id_removeOthQuali'+att+'" ><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
			 	+'</tr>');
			
			 addOnclickQuali(att);
			 removeOnclickQuali(att);
			 
			 var date = new Date();
			 var today = date.toISOString().substring(0,7);
			 $("#othYrofpass"+att).attr('max',today);
			 
			 
		var dob = $("#date_of_birth").val();
			 
			 if (dob != "DD/MM/YYYY" || dob != "") {
				var y = dob.split('/').reverse().join('-');
				var rahu = y.substring(0,7);
			  $("#othYrofpass"+att+"").prop('min',rahu);
			  
			  
			 }
			
			 
			 
	
			 
			}else{
					alert("Please Enter max 50 Quantity");
					 if ( att == 51){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
	}
	
function formopen_re_OthQuali(R){
	
	var othquali_id = $("#othquali_id"+R).val();
	
	var result = confirm("Are you sure you want to delete?");
	 
	 if (result) {
			$.post("delete_otherquali_addmore?" + key + "=" + value, {othquali_id:othquali_id}, function(j) {
				 alert("Data Deleted Successfully.")
			 	});
	
	
	 $("tr#tr_id_attOthQuali"+R).remove();
	 att = att-1;
	 $("input#count_hidden_att_oth_quali").val(att);
	 $("#id_add_attOthQuali"+att).show();
	 $("#att_id_removeOthQuali"+att).show();
	 }
}

function dynamicTableOQ(R,index){
	//alert("r==="+R+"index"+index);
	

var length = $("#count_hidden_att_oth_quali").val();
for(var i = 1 ;i<=length;i++){
$("#dynamicDataTableOQ"+i).hide();
}

if($("#dynamicDataTableOQ"+R).length){
	$("#dynamicDataTableOQ"+R).show();

}else{

	if($('#dynamicDataTableOQ'+R) != null){
$("#dynamicDataTableOQ").append("<div id='dynamicDataTableOQ"+R+"'></div>");
	}
var seq = ""+R+"_"+index+"";
//	var r1 = "'"+R+"_"+"'";
$("div#dynamicDataTableOQ"+R).append('<table class="table" id="addAttOQ'+R+'"><thead><tr><th><h6>Sr No</h6></th>'
		+'<th><h6>Name of Attachment</h6></th>'
		+'<th><h6>Attachment</h6><span class="mandatory table-note">Maximum file size upto 200 kb and support file extension .pdf</span></th>'
		+'<th><h6>Action</h6></th>'
		+'</tr>'
		+'</thead>'
		+'<tbody id="att_TbbodyattOQ'+seq+'">'
		+'	<tr id="tr_id_attOQ'+seq+'">'
		+'		<td><p>'
	
		+'					1'
	
			+'		</p></td>'
		
			+'	<td>'

// 			+'			<div class="input-style-1">'
// 			+'			<input type="text" id="name_of_attachment'+seq+'"'
// 					+'				name="name_of_attachment'+seq+'" value=""'
// 					+'				class="form-control autocomplete" autocomplete="off"'
// 					+'				onkeypress="AutoCompleteNameOfUniversity(this);"'
// 					+'			placeholder="Name of Attachments">'
// 					+'	</div> </td>'

				+'<div class="select-style-1">'
				+'<div class="select-position">'
				+' <input type="hidden"'
				+'			id="otherqualificationAtt_id'+seq+'" value="0" name="otherqualificationAtt_id'+seq+'"'
				+'			class="form-control"><select name="name_of_attachmentOQ'+seq+'" id="name_of_attachmentOQ'+seq+'" class="form-control autocomplete">'
				+'<option value="0">--Select--</option>'
				+'<c:forEach var="item" items="${getnameofDoc}" varStatus="num"><option value="${item.id}" name="${item.doc_name}">${item.doc_name}</option></c:forEach></select>'
				+'</div></div>'
					+'	<td>'

					+'		<div class="input-style-1">'
					+'			<input type="file" id="attachmentDocumentOQ'+seq+'" name="attachmentDocumentOQ'+seq+'"'
					+'				accept=".pdf" '
					+'			class="form-control"> <input type="hidden"'
					+'			id="attachmentDoc_hidOQ'+seq+'" name="attachmentDoc_hidOQ'+seq+'"'
					+'			class="form-control"><div class="note-text"><span class="errorClass " id="attachmentDoc_hidOQ_file_lbl'+seq+'"></span> <span class="tikClass " id="attachmentDoc_hidOQ_file_lbltik'+seq+'"></span></div>'

					+'</div> '
					+'</td>'
					+'<td class="min-width addminusbut">'
					+'	<div class="action">'

					+'	<ul class="buttons-group mainbtn">'

					+'		<li><a'
					+'			class="main-btn success-btn btn-hover btn-sm btnaddmore"'
					+'		value="ADD" title="ADD" id="id_add_attOQ'+seq+'" name="id_add_attOQ'+seq+'"' 
					+'		><i'
					+'			class="lni lni-plus"></i></a></li>'

					+'	</ul>'

					+'	</div>'
			+'</td> </tr> </tbody> </table>'
			+'<input type="hidden" id="count_hidden_att_OQ'+R+'" name="count_hidden_att_OQ'+R+'" class="form-control autocomplete" value="1">');
			
			dynamicparentother(R,index);
}
//	$('#modelWindow').modal('show');

}

function formOpenattOQ(R,index){
 	
			 $("#addAttOQ"+R).show();

				 $("#id_add_attOQ"+R+"_"+index).hide();
				 $("#att_id_removeattOQ"+R+"_"+index).hide();
				 
				 att=0;
				 att= parseInt(index)+1;
				
				 var seq = ""+R+"_"+att+"";
				 
				 if(att < 51){
					 $("input#count_hidden_att_OQ"+R).val(att);//current serial No
					 $("table#addAttOQ"+R).append('<tr id="tr_id_attOQ'+seq+'"><td><p>'+att+'</p></td>'
// 					 	+'<td><div class="input-style-1"><input type="text" id="name_of_attachmentOQ'+seq+'" name="name_of_attachmentOQ'+seq+'" value="" class="form-control autocomplete" autocomplete="off" onkeypress="AutoCompleteNameOfUniversity(this);" placeholder="Name of Attachment"></div></td>'
				+'<td><div class="select-style-1">'
				+'<div class="select-position">'
				+'<input type="hidden"'
				+'			id="otherqualificationAtt_id'+seq+'" value="0" name="otherqualificationAtt_id'+seq+'"'
				+'			class="form-control"><select name="name_of_attachmentOQ'+seq+'" id="name_of_attachmentOQ'+seq+'" class="form-control autocomplete">'
				+'<option value="0">--Select--</option>'
				+'<c:forEach var="item" items="${getnameofDoc}" varStatus="num"><option value="${item.id}" name="${item.doc_name}">${item.doc_name}</option></c:forEach></select>'
				+'</div></div></td>'
			 	+'<td><div class="input-style-1"><input type="file" id="attachmentDocumentOQ'+seq+'" name="attachmentDocumentOQ'+seq+'" accept=".pdf" onchange="attachmentDetails(this,'+seq+');" class="form-control"><input type="hidden" id="attachmentDoc_hidOQ'+seq+'" name="attachmentDoc_hidOQ'+seq+'" class="form-control"><div class="note-text"><span class="errorClass " id="attachmentDoc_hidOQ_file_lbl'+seq+'"></span> <span class="tikClass " id="attachmentDoc_hidOQ_file_lbltik'+seq+'"></span></div></div></td>' 
			 	+'<td><div class="action"><ul class="buttons-group mainbtn"><li><a class="main-btn success-btn btn-hover btn-sm btnaddmore" value = "ADD" title = "ADD" id = "id_add_attOQ'+seq+'" name = "id_add_attOQ'+seq+'" ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove" value="REMOVE" title = "REMOVE" id = "att_id_removeattOQ'+seq+'" ><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
			 	+'</tr>');
					 
					 dynamictablemedicalq(R,att);
				 
					}else{
							alert("Please Enter max 50 Quantity");
							 if ( att == 51){
								 att = att-1; 
								 $("#att_id_removeattOQ"+att).show();
							 }	   
					}
}

function formopen_re_attOQ(R,index){
	
	
	
var othqualiatt_id = $("#otherqualificationAtt_id"+R+"_"+index).val();
	
	var result = confirm("Are you sure you want to delete?");
	 
	 if (result) {
			$.post("delete_otherqualiatt_addmore?" + key + "=" + value, {othqualiatt_id:othqualiatt_id}, function(j) {
				 alert("Data Deleted Successfully.")
			 	});
	
	
	 $("tr#tr_id_attOQ"+R+"_"+index).remove();
	 att = index-1;
	 $("input#count_hidden_att_OQ"+R).val(att);
	 $("#id_add_attOQ"+R+"_"+att).show();
	 $("#att_id_removeattOQ"+R+"_"+att).show();	
	 }
}

function formOpenattDoc(R,index){
	// var seq = "'"+R+"_"+index+"'"

	// if(R > 1){
		
	// }
			 $("#addAttDoc"+R).show();

				 $("#id_add_attDoc"+R+"_"+index).hide();
				 $("#att_id_removeattDoc"+R+"_"+index).hide();
				 
				 att=0;
				 att= parseInt(index)+1;
				
				 var seq = ""+R+"_"+att+"";
				 
				 if(att < 51){
					 $("input#count_hidden_att_doc"+R).val(att);//current serial No
					 $("table#addAttDoc"+R).append('<tr id="tr_id_attDoc'+seq+'"><td><p>'+att+'</p></td>'
// 					 	+'<td><div class="input-style-1"><input type="text" id="name_of_attachment'+seq+'" name="name_of_attachment'+seq+'" value="" class="form-control autocomplete" autocomplete="off" onkeypress="AutoCompleteNameOfUniversity(this);" placeholder="Name of Attachment"></div></td>'
				+'	<td>'
				+'<div class="select-style-1">'
				+'<div class="select-position">'
				+'<select name="name_of_attachment'+seq+'" id="name_of_attachment'+seq+'" class="form-control autocomplete" >'
				+'<option value="0">--Select--</option>'
				+'<c:forEach var="item" items="${getnameofDoc}" varStatus="num"><option value="${item.id}" name="${item.doc_name}">${item.doc_name}</option></c:forEach></select>'
				+'</div></div>'
				+' </td>'


				+'	<td>'
						+'<div class="select-style-1 hide" id="degreehide'+seq+'">'
						+'<div class="select-position">'
						+'<select name="name_of_degree'+seq+'" id="name_of_degree'+seq+'" class="form-control autocomplete">'
						+'<option value="0" selected="selected"> -- Select Attachment -- </option><option value="1">Provisional Degree Certificate</option><option value="2">Final Degree certificate</option></select>'
						+'</div></div>'
						+'</td>'					 
/* <input type="text" id="NameOfUniversity'+att+'" name="NameOfUniversity'+att+'" value="" class="form-control autocomplete" autocomplete="off" onkeypress="AutoCompleteNameOfUniversity(this);"></td>' */
					 	+'<td><div class="input-style-1"><input type="hidden" id="medicalqualificationAtt_id'+seq+'" value="0" name="medicalqualificationAtt_id'+seq+'"'
						+'				'
						+'			class="form-control"> <input type="file" id="attachmentDocument'+seq+'" name="attachmentDocument'+seq+'" accept=".pdf"  class="form-control"><input type="hidden" id="attachmentDocumenthid'+seq+'" name="attachmentDocumenthid'+seq+'" class="form-control">'
						+'<input type="hidden" id="attachmentDocument_file_hid'+seq+'" name="attachmentDocument_file_hid'+seq+'" class="form-control" value=""><div class="note-text"><span class="errorClass " id="attachmentDocument_file_lbl'+seq+'"></span> <span class="tikClass " id="attachmentDocument_file_lbltik'+seq+'"></span></div>'
						+'</div></td>' 
					 	/* <input type="file" id="attachment'+att+'" name="attachment'+att+'" accept="image/*" onchange="attachmentDetails(this,'+att+');"></td>'  */
					 	+'<td><div class="action"><ul class="buttons-group mainbtn"><li><a class="main-btn success-btn btn-hover btn-sm btnaddmore" value = "ADD" title = "ADD" id = "id_add_attDoc'+seq+'" name = "id_add_attDoc'+seq+'" ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove" value="REMOVE" title = "REMOVE" id = "att_id_removeattDoc'+seq+'" ><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
					 	/* <a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_attNameMed'+att+'" onclick="formOpenNameMed('+att+');" ><i class="fa fa-plus"></i></a> <a style="margin-right: 0px;"class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeNameMed'+att+'" onclick="formopen_re_NameMed('+att+');"><i class="fa fa-trash"></a></td>'  */
					 	+'</tr>');
					 
					 dynamictablemedical(R,att);
				 
					}else{
							alert("Please Enter max 50 Quantity");
							 if ( att == 51){
								 att = att-1; 
								 $("#att_id_removeattDoc"+att).show();
							 }	   
					}
}



function formopen_re_attDoc(R,index){
	
	var qua_att = $("#medicalqualificationAtt_id"+R+"_"+index).val();
	
	var result = confirm("Are you sure you want to delete?");
	 
	 if (result) {
			$.post("delete_qualiattch_addmore?" + key + "=" + value, {qua_att:qua_att}, function(j) {
				 alert("Data Deleted Successfully.")
			 	});
	
	
	 $("tr#tr_id_attDoc"+R+"_"+index).remove();
	 att = index-1;
	// alert(att);
	 $("input#count_hidden_att_doc"+R).val(att);
	 $("#id_add_attDoc"+R+"_"+att).show();
	 $("#att_id_removeattDoc"+R+"_"+att).show();
	 
	 }
}
//Age Calculate	
function calculate_age(obj){    
    var from_d=$("#"+obj).val();
    var from_d1=from_d.substring(6,10);
    var from_d2=from_d.substring(3,5);
    var from_d3=from_d.substring(0,2);
    
    var frm_d = from_d3+"-"+from_d2+"-"+from_d1;         
    /* var to_d=$("#dt_of_joining").val();
    var to_d1=to_d.substring(0,4);
    var to_d2=to_d.substring(7,5);
    var to_d3=to_d.substring(10,8); */
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
    $("#yrr").val(year);
 	 if(year < 17 || year == "" || year == "0"){
 	    	alert("Age Should Be Greater Than 17 Years")
 	    	$("#date_of_birth").focus();
 	    	return false;
 	    }
//     $(".get-value").text(""+year+" Years");
    
//     $("#yrr").val(year);
}

			function formateOfDoc(obj){
				$.ajaxSetup({
					async : false
				});
				
				$("#upload_doc"+obj).val("");
				$("#upload_doc"+obj).attr("accept","."+$("#doc_id"+obj+" option:selected").text().toLowerCase());
				
			}
				function degreehide(R,index){
			
				var val = $("#name_of_attachment"+R+"_"+index).val();
				if(val=="3"){
					$("div#degreehide"+R+"_"+index).show();
				}else{
					$("div#degreehide"+R+"_"+index).hide();
				}
			}
	
				function checkfunctuion() {
						if($('.last').hasClass('current'))
								{
								$("#update-button").show();
								$("#save_li").hide();
								$("#rest_li").hide();
							}
							else {
								$("#update-button").hide();
								$("#save_li").show();
								$("#rest_li").show();
							}
					}
	
// <label class="buttonmerge" for="text-input">
// <span class="mandatory" style="font-size: small;">
// 	[ * fields indicates mandatory ]
// </span>
// </label>
	
//	document.querySelectorAll('.deleteOnclick').forEach((items, index) => {
//	items.addEventListener('click', event => {
//		
//		var val=parseInt(index)+1;
	
//		var hid = document.getElementById('deleteID'+val).value;
////				var hname = document.getElementById('apItmeNumber'+val).value;
////				var hpnum = document.getElementById('apBoqId'+val).value;
//		if (confirm('Are You Sure You Want to Delete Detail ?')) {
//			deleteData(hid);
//		} else {
//			return false;
//		}
//	})
//});
	
document.querySelectorAll('.actions a[href="#previous"]').forEach((items, index) => {
	items.addEventListener('click', event => {
		checkfunctuion();
		})
	});

document.querySelectorAll('.actions a[href="#next"]').forEach((items, index) => {
	items.addEventListener('click', event => {
		checkfunctuion();
	})
});

document.querySelectorAll('[role="tab"]').forEach(function (items, index) {
	items.addEventListener('click', event => {
		checkfunctuion();
		})
	});



document.querySelectorAll('.actions a[href="#next"]').forEach((items, index) => {
	items.addEventListener('click', event => {
		checkfunctuion();
	})
});



document.querySelectorAll('.actions a[href="#finish"]').forEach((items, index) => {
	items.addEventListener('click', event => {
		//saveData();
// 		 isValid();
		 
		 if (isValid() == true) {
			 faculty_transfer();
				 $( "#step-form-horizontal" ).submit();
		}
		 
	})
});



//csp----------------------------

document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn-save').onclick = function() {
		//draftSave("Final Submit");
		return isValid();
		
		finalreadonly();
		
	};
	
	document.getElementById('draft-saveper').onclick = function() {
		//draftSave("Final Submit");
		 saveDraft();	
	};
	
	document.getElementById('draft-savequali').onclick = function() {
		//draftSave("Final Submit");
		 saveDraftforquali();	
	};
	document.getElementById('draft-savereg').onclick = function() {
		//draftSave("Final Submit");
		//return saveDraft();	
	 saveDraftforregi();	
	};
	document.getElementById('draft-saveacademic').onclick = function() {
		//draftSave("Final Submit");
		 saveDraftforacademic();	
	};
	document.getElementById('draft-saveexp').onclick = function() {
		//draftSave("Final Submit");
		 saveDraftforexperience();	
	};
	
	document.getElementById('draftBtn').onclick = function() {
		draftSave("Save as Draft");
		return isValid();
		
	};
	document.getElementById('draft-savetype_of_exp').onclick = function() {
		//draftSave("Final Submit");
		 saveDraftforTypeofexperience();	
	};
	document.getElementById('viewData').onclick = function() {
		
		ViewData();
	};
	
	
	
	document.getElementById('first_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	document.getElementById('middle_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	document.getElementById('last_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	document.getElementById('gender').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	document.getElementById('father_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(event,this);
	};
	document.getElementById('date_of_birth').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_birth').onfocus = function() {
		return this.style.color='#000000';
	};
	document.getElementById('date_of_birth').onblur = function() {
		 clickrecall(this,'DD/MM/YYYY');
		 return validateDate_BackDate(this.value,this);
	};
	document.getElementById('date_of_birth').onkeyup = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_birth').onchange = function() {
		changedatepikettodob(this.value);
		clickrecall(this,'DD/MM/YYYY');
		 calculate_age('date_of_birth');
		 return	validateDate_FutureDate(this.value,this);
		
	};
	document.getElementById('mother_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(event,this);
	};
	document.getElementById('spouse_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(event,this);
	};
	document.getElementById('mobile_no').onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('mobile_no').onchange = function() {
		return mobileNumber(this);
	};
	document.getElementById('email').onchange = function() {
		return checkgmail(this.value);
	};
	document.getElementById('aadhar_no1').onkeyup = function() {
		lengthadhar(); return movetoNext(this, 'aadhar_no2');
	};
	document.getElementById('aadhar_no1').onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('aadhar_no2').onkeyup = function() {
		lengthadhar(); return movetoNext(this, 'aadhar_no3');
	};
	document.getElementById('aadhar_no2').onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('aadhar_no3').onkeyup = function() {
		return lengthadhar();
	};
	document.getElementById('aadhar_no3').onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('pan_no').onchange = function() {
		return isPAN(this);
	};
// 	document.getElementById('academic_quali').onchange = function() {
// 		return  FnMd_dtl(); hideshw_Degree();
// 	};
// 	document.getElementById('sub_quali_degree').onchange = function() {
// 		return  FnMd_dtl(); hideshw_Degree();
// 	};
// 	document.getElementById('subject').onkeypress = function() {
// 		return   onlyAlphaNumeric(event, this);
// 	};
// 	document.getElementById('per_add_line1').onkeypress = function() {
		//return onlyAlphaNumeric(event, this);
// 	};
	document.getElementById('per_add_line1').onkeypress = function() {
		return onlyAlphaNumericAbhiCustom(event);
		//return changeAddress();
		//onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('per_add_line1').onkeyup= function() {
		//return onlyAlphaNumericAbhiCustom(event);
		return changeAddress();
		//onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('per_add_line2').onkeypress = function() {
		return onlyAlphaNumericAbhiCustom(event, this);
		//return changeAddress()
		
	};
	document.getElementById('per_add_line2').onkeyup = function() {
	//	return onlyAlphaNumericAbhiCustom(event, this);
		return changeAddress()
		
	};
	document.getElementById('per_state').onchange = function() {
		 changeAddress(); 
		getDistrict();
	};
	document.getElementById('per_district').onkeypress = function() {
		return changeAddress(); 
	};
	document.getElementById('per_village').onkeypress = function() {
		return onlyAlphabetsStringSpace(event,this);	
	};
	document.getElementById('per_village').onkeypress = function() {
		return changeAddress();	
	};
	
	
	document.getElementById('per_pincode').onkeypress = function() {
		changeAddress();
		return isNumberKey0to9(event, this);	
	};
	
	document.getElementById('per_pincode').onchange = function() {
		 // checkMinLength(this.id,6);
		//  checkMaxLength(this.id,6);
	};
	
	document.getElementById('per_phn_no').onkeypress = function() {
		changeAddress();
		return isNumberKey0to9(event, this);
		
	};
	
	document.getElementById('per_phn_no').onchange = function() {
		 return mobileNumber(this); 
		
	};
	document.getElementById('check_address').onchange = function() {
		return copy_address();	
	};
	
	document.getElementById('present_add_line1').onkeypress = function() {
		return onlyAlphaNumericAbhiCustom(event,this);	
	};
	document.getElementById('present_add_line2').onkeypress = function() {
		return onlyAlphaNumericAbhiCustom(event,this);
		
	};
	document.getElementById('present_state').onchange = function() {
		return getDistrict_present();	
	};
	document.getElementById('present_village').onkeypress = function() {
		return onlyAlphabetsStringSpace(event,this);	
	};
	document.getElementById('present_pincode').onkeypress = function() {
		return isNumberKey0to9(event, this);	
	};
	
	document.getElementById('present_pincode').onchange = function() {
			//  checkMinLength(this.id,6);
			 // checkMaxLength(this.id,6);
		};
	
	document.getElementById('present_phn_no').onchange = function() {
		return mobileNumber(this);	
	};
	document.getElementById('present_phn_no').onkeypress = function() {
		return isNumberKey0to9(event, this);	
	};
	document.getElementById('course1').onchange = function() {
		return GetSubfromCourse(1);	
	};
	
	document.getElementById('typeOfDegree1').onchange = function() {
		return GetCoursebytype(1);	
	};
	
	
// 	document.getElementById('NameOfUniversity1').onkeypress = function() {
// 		return onlyAlphabetsStringSpace(event,this);		/*AutoCompleteNameOfUniversity(this);*/
// 	};
	document.getElementById('NameOfAffUni1').onclick = function() {
		return OtherOption(this,1);		/*AutoCompleteNameOfUniversity(this);*/
	};
	
	document.getElementById('type_of_exp1').onclick = function() {
		return OtherOptionforexp(this,1);
		};
	
	
	
	document.getElementById('id_add_attNameMed1').onclick = function() {
		return formOpenNameMed(1);	
	};
	document.getElementById('id_add_attOthQuali1').onclick = function() {
		return formOpenOthQuali(1);	
	};
	document.getElementById('OthnameofExDeg1').onkeypress = function() {
		return onlyAlphabetsStringSpace(event,this);		
	};
	document.getElementById('othSubject1').onkeypress = function() {
		return onlyAlphabetsStringSpace(event,this);		
	};
	document.getElementById('othUniInst1').onkeypress = function() {
		return onlyAlphabetsStringSpace(event,this);		
	};
	document.getElementById('othAffuni1').onkeypress = function() {
		return onlyAlphabetsStringSpace(event,this);		
	};
	
	
	document.getElementById('registration_type').onchange = function() {
		return hideshw_Registration();
	};
	document.getElementById('state_reg_no').onkeypress = function() {
		return onlyAlphaNumericAbhiCustom(event,this);
	};
// 	return onlyAlphaNumeric(event, this);
// 	document.getElementById('state_reg_no').onkeypress = function() {
// 		return isNumberKey0to9(event, this);
// 	};
// 	document.getElementById('state_reg_no').onkeypress = function() {
// 		return isNullEmptyBlank(str);
// 	};
// 	document.getElementById('state_board_name').onkeypress = function() {
// 		return onlyAlphabetsStringSpace(event,this);
// 	};
	document.getElementById('date_of_reg').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_reg').onfocus = function() {
		return this.style.color='#000000';
	};
	document.getElementById('date_of_reg').onblur = function() {
		return clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('date_of_reg').onkeyup = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_reg').onchange = function() {
		onchangeCountdorvalidupto(this.value);
	//	onchangeCountDateofReg(this.value);
		return clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('cs_date_of_reg').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('cs_date_of_reg').onfocus = function() {
		return this.style.color='#000000';
	};
	document.getElementById('cs_date_of_reg').onblur = function() {
		return clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('cs_date_of_reg').onkeyup = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('cs_date_of_reg').onchange = function() {
		return clickrecall(this,'DD/MM/YYYY');
	};



		document.getElementById('other_date_of_reg').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('other_date_of_reg').onfocus = function() {
		return this.style.color='#000000';
	};
	document.getElementById('other_date_of_reg').onblur = function() {
		return clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('other_date_of_reg').onkeyup = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('other_date_of_reg').onchange = function() {
		onchangeCountOthervalidupto(this.value);
		return clickrecall(this,'DD/MM/YYYY');
	};

	
	document.getElementById('other_validity_upto').onclick = function() {
		return  clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('other_validity_upto').onfocus = function() {
		return  this.style.color='#000000';
	};
	document.getElementById('other_validity_upto').onblur = function() {
		return  clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('other_validity_upto').onkeyup = function() {
		return  clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('other_validity_upto').onchange = function() {
		
		onchangeCountOthervalidupto(this.value);
		return  clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('central_reg_no').onkeypress = function() {
		return onlyAlphaNumericAbhiCustom(event, this);
	};
	document.getElementById('adjunct_registration_no').onkeypress = function() {
		return onlyAlphaNumeric(event, this);
	};
	document.getElementById('adjunct_state_no').onkeypress = function() {
		return  onlyAlphaNumeric(event, this);
	};
	document.getElementById('state_validity_upto').onclick = function() {
		
		return  clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('state_validity_upto').onfocus = function() {
		return  this.style.color='#000000';
	};
	document.getElementById('state_validity_upto').onblur = function() {
		return  clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('state_validity_upto').onkeyup = function() {
		return  clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('state_validity_upto').onchange = function() {
		onchangeCountdorvalidupto(this.value);
		return  clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('direct_reg_no').onkeypress = function() {
		return  onlyAlphaNumericAbhiCustom(event, this);
	};
	document.getElementById('direct_date_of_reg').onclick = function() {
		return  clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('direct_date_of_reg').onfocus = function() {
		return  this.style.color='#000000';
	};
	document.getElementById('direct_date_of_reg').onblur = function() {
		return  clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('direct_date_of_reg').onkeyup = function() {
		return  clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('direct_date_of_reg').onchange = function() {
		onchangeCountDirectvalidupto(this.value);
		return  clickrecall(this,'DD/MM/YYYY');
	};
// 	document.getElementById('name_of_department').onkeypress = function() {
// 		return  AutoCompleteNameOfUniversity(this);
// 	};





document.getElementById('name_of_department').onkeypress = function() {
	return onlyAlphaNumericAbhiCustom(event,this);
	
};
	document.getElementById('direct_validity_upto').onclick = function() {
		return  clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('direct_validity_upto').onfocus = function() {
		return  this.style.color='#000000';
	};
	document.getElementById('direct_validity_upto').onblur = function() {
		return  clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('direct_validity_upto').onkeyup = function() {
		return  clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('direct_validity_upto').onchange = function() {
		onchangeCountDirectvalidupto(this.value);
		return  clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('institute_name1').onkeypress = function() {
		return   onlyAlphabetsStringSpace(event,this);
	};
	document.getElementById('from_date1').onclick = function() {
		return   clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('from_date1').onfocus = function() {
		return   this.style.color='#000000';
	};
	document.getElementById('from_date1').onblur = function() {
		return   clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('from_date1').onkeyup = function() {
		return   clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('from_date1').onchange = function() {
		   clickrecall(this,'DD/MM/YYYY'); onchangeCount(1);
	};
	
	document.getElementById('from_date_pro1').onchange = function() {
		   clickrecall(this,'DD/MM/YYYY'); onchangeCountpro(1);
	};
	
	document.getElementById('to_date1').onfocus = function() {
		return   this.style.color='#000000';
	};
	document.getElementById('to_date1').onfocus = function() {
		return   this.style.color='#000000';
	};
	document.getElementById('to_date1').onblur = function() {
		return   clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('to_date1').onkeyup = function() {
		return   clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('to_date1').onchange = function() {
		   clickrecall(this,'DD/MM/YYYY'); onchangeCount(1);
	};
	document.getElementById('to_date_pro1').onchange = function() {
		   clickrecall(this,'DD/MM/YYYY'); onchangeCountpro(1);
	};
	document.getElementById('upload_file1').onchange = function() {
		return   pdfFileSizeValidation(this,this.value,"upload_file1","200kb","upload_file_lbltik","upload_file_lbl",this.accept);
	};
// 	document.getElementById('id_add_attNameMed1').onclick = function() {
// 		return   formopen_att(1);
// 	};
	document.getElementById('id_add_att1').onclick = function() {
		return   formopen_att(1);
	};
	document.getElementById('id_add_att_pro1').onclick = function() {
		return   formopen_att_pro(1);
	};
	document.getElementById('doc_name1').onchange = function() {
		return GetTypeFromDoc(1);
	};
	document.getElementById('doc_id1').onchange = function() {
		return formateOfDoc(1);
	};
	document.getElementById('upload_doc1').onchange = function() {
		return   pdfFileSizeValidation(this,this.value,"upload_doc1","200kb","doc_upload_lbltik","doc_upload_lbl",this.accept);
	};
	document.getElementById('id_add_doc1').onclick = function() {
		return   formopen_doc(1);
	};
	
	document.getElementById('NameOfUniversity1').onchange = function() {
		return getuniversitybyinst(1);	
	};
	
// 	document.querySelectorAll('.viewData').forEach((items, index) => {
// 		items.addEventListener('click', event => {
			
// 			var val=parseInt(index)+1;
			
// 			var hid = document.getElementById('viewId'+val).value;
			
// 			if (confirm('Are You Sure You Want to View Detail ?')) {
// 				ViewData(hid);
// 			} else {
// 				return false;
// 			}
// 		})
// 	});
	
	
// 	document.getElementById('institute_name'+att+'').onkeypress = function() {
// 		return    onlyAlphabetsStringSpace('+att+');
// 	};

// 	document.getElementById('btn-update').onclick = function() {
// 		return isValid();
// 	};
	
});

function OtherOption(val,index){
	
	if($("select#NameOfAffUni"+index).val()=="OTHER"){
		
		//$("#universityother"+index).show();
		if ($("#universityother"+index).hasClass("d-none")) {
			 $( "#universityother"+index).removeClass("d-none")
		}
	}else{
		//$("#universityother"+index).hide();
		if (!$( "#universityother"+index).hasClass('d-none')) {
			$( "#universityother"+index).addClass("d-none")
		}
	}
	
}



function OtherOptionforexp(val,index){
	debugger;
	if($("select#type_of_exp"+index).val()=="-1"){
		
		
		if ($("#experienceother"+index).hasClass("d-none")) {
			 $("#experienceother"+index).removeClass("d-none")
		}
	}else{
		
		if (!$( "#experienceother"+index).hasClass('d-none')) {
			$( "#experienceother"+index).addClass("d-none")
		}
	}
	
}


function saveData(id) {
	
	document.getElementById('btn-save').submit();
}


function getuniversitybyinst(obj) {
	$.ajaxSetup({
		async : false
	});
	var institute_id = $("select#NameOfUniversity"+obj).val();
	
	if(institute_id != "" && institute_id != null){
		 $.post("getuniversitybyinstlist_ctrl?" + key + "=" + value,{institute_id : institute_id},
					function(j) {
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
							+ j[i][1]+ '</option>';
						}
						options += '<option value="OTHER" name="OTHER">OTHER</option>';
						$("select#NameOfAffUni"+obj).html(options);
		});
	}
}


function draftSave(val){

	if(val=="Final Submit"){
		$("#hiddenUpdate").val(1);
	}
	
	
}




function Getchekmonthyear(att) {
	
	
	
			if (att>1) {
				var att_cur = att;
				var att_prev = parseInt(att) - 1;
		
			var tod_cur =  $("#typeOfDegree"+att_cur).val();
			var tod_prev = $("#typeOfDegree"+att_prev).val();
			var monyr_cur = $("#monthYearOfDegree"+att_cur).val();
			var monyr_prev = $("#monthYearOfDegree"+att_prev).val();
			
				if (tod_cur == "0") {
					alert("Please Select Name of Exam / Degree In "+att_cur+" Row");
					$("#typeOfDegree"+att_cur).focus();
					return false;
				}
				
				if (parseInt(tod_cur) > parseInt(tod_prev)  &&  (monyr_cur) < (monyr_prev)) {
					alert("Please Select Month and Year Grater Then "+$("#typeOfDegree"+att_prev+" option:selected").text()+"'s Month and Year.");
					$("#monthYearOfDegree"+att_cur).val('');
					$("#monthYearOfDegree"+att_cur).focus();
					return false;
				}
				
				if (parseInt(tod_cur) < parseInt(tod_prev)  &&  (monyr_cur) > (monyr_prev)) {
						alert("Please Select Month and Year Smaller Than "+$("#typeOfDegree"+att_prev+" option:selected").text()+"'s Month and Year.");
						$("#monthYearOfDegree"+att_cur).val('');
						$("#monthYearOfDegree"+att_cur).focus();
						return false;
				}
				if (parseInt(tod_cur) > parseInt(tod_prev)  &&  (monyr_cur) == (monyr_prev)) {
					alert("Please Select Month and Year Different Than "+$("#typeOfDegree"+att_prev+" option:selected").text()+"'s Month and Year.");
					$("#monthYearOfDegree"+att_cur).val('');
					$("#monthYearOfDegree"+att_cur).focus();
					return false;
			}
			}
}






function onchangeCountdorvalidupto(val) {
	
	if (document.getElementById("date_of_reg").value != "DD/MM/YYYY"
			&& (document.getElementById("state_validity_upto").value != "DD/MM/YYYY")
			&& (document.getElementById("state_validity_upto").value != "")) {
		var start_date = document.getElementById("date_of_reg").value.split(
				'/').reverse().join('-');
		var end_date = document.getElementById("state_validity_upto").value.split('/')
				.reverse().join('-');
		var start_dt = new Date(start_date);
		var end_dt = new Date(end_date);
		// To calculate the time difference of two dates
		var Difference_In_Time = end_dt.getTime() - start_dt.getTime();
		// To calculate the no. of days between two dates
		var Difference_In_Days = (Difference_In_Time / (1000 * 3600 * 24));
		var Difference_In_Weeks = Math.floor(Difference_In_Days / 7);
		var remainday = (Difference_In_Days) - (Difference_In_Weeks * 7);
		if (Difference_In_Days < 1) {
			alert("Please Select Valid Upto Date Bigger Than Date of Registration Date!");
			//$("input#date_of_reg").val("DD/MM/YYYY");
			$("input#state_validity_upto").val("DD/MM/YYYY");
			$("input#state_validity_upto").focus();
			return false;
		}
	}
}

function onchangeCountOthervalidupto(val) {
	
	if (document.getElementById("other_date_of_reg").value != "DD/MM/YYYY"
			&& (document.getElementById("other_validity_upto").value != "DD/MM/YYYY")
			&& (document.getElementById("other_validity_upto").value != "")) {
		var start_date = document.getElementById("other_date_of_reg").value.split(
				'/').reverse().join('-');
		var end_date = document.getElementById("other_validity_upto").value.split('/')
				.reverse().join('-');
		var start_dt = new Date(start_date);
		var end_dt = new Date(end_date);
		// To calculate the time difference of two dates
		var Difference_In_Time = end_dt.getTime() - start_dt.getTime();
		// To calculate the no. of days between two dates
		var Difference_In_Days = (Difference_In_Time / (1000 * 3600 * 24));
		var Difference_In_Weeks = Math.floor(Difference_In_Days / 7);
		var remainday = (Difference_In_Days) - (Difference_In_Weeks * 7);
		if (Difference_In_Days < 1) {
			alert("Please Select Valid Upto Date Smaller Than Date of Registration Date!");
			//$("input#other_date_of_reg").val("DD/MM/YYYY");
			$("input#other_validity_upto").val("DD/MM/YYYY");
			$("input#other_validity_upto").focus();
			return false;
		}
	}
}
	
	
	
function onchangeCountDirectvalidupto(val) {
	
	if (document.getElementById("direct_date_of_reg").value != "DD/MM/YYYY"
			&& (document.getElementById("direct_validity_upto").value != "DD/MM/YYYY")
			&& (document.getElementById("direct_validity_upto").value != "")) {
		var start_date = document.getElementById("direct_date_of_reg").value.split(
				'/').reverse().join('-');
		var end_date = document.getElementById("direct_validity_upto").value.split('/')
				.reverse().join('-');
		var start_dt = new Date(start_date);
		var end_dt = new Date(end_date);
		// To calculate the time difference of two dates
		var Difference_In_Time = end_dt.getTime() - start_dt.getTime();
		// To calculate the no. of days between two dates
		var Difference_In_Days = (Difference_In_Time / (1000 * 3600 * 24));
		var Difference_In_Weeks = Math.floor(Difference_In_Days / 7);
		var remainday = (Difference_In_Days) - (Difference_In_Weeks * 7);
		if (Difference_In_Days < 1) {
			alert("Please Select Valid Upto Date Smaller Than Date of Registration Date!");
// 			$("input#direct_date_of_reg").val("DD/MM/YYYY");
			$("input#direct_validity_upto").val("DD/MM/YYYY");
			$("input#direct_validity_upto").focus();
			return false;
		}
	}
}




// function onchangeCountDateofReg(val) {
// 	debugger;
// 	if (document.getElementById("monthYearOfDegree1").value != "MM/YYYY"
// 			&& (document.getElementById("date_of_reg").value != "DD/MM/YYYY")
// 			&& (document.getElementById("date_of_reg").value != "")) {
// 		var start_date = document.getElementById("monthYearOfDegree1").value.split(
// 				'/').reverse().join('-');
// 		var end_date = document.getElementById("date_of_reg").value.split('/')
// 				.reverse().join('-');
// 		var start_dt = new Date(start_date);
// 		var end_dt = new Date(end_date);
// 		// To calculate the time difference of two dates
// 		var Difference_In_Time = end_dt.getTime() - start_dt.getTime();
// 		// To calculate the no. of days between two dates
// 		var Difference_In_Days = (Difference_In_Time / (1000 * 3600 * 24));
// 		var Difference_In_Weeks = Math.floor(Difference_In_Days / 7);
// 		var remainday = (Difference_In_Days) - (Difference_In_Weeks * 7);
// 		if (Difference_In_Days < 1) {
// 			alert("Please Select Valid Date of Reg After Month & Year Of Passing!");
// // 			$("input#direct_date_of_reg").val("DD/MM/YYYY");
// 			$("input#date_of_reg").val("DD/MM/YYYY");
// 			$("input#date_of_reg").focus();
// 			return false;
// 		}
// 	}
// }


// function onchangeCountOtherDateofReg(val) {
// 	debugger;
// 	if (document.getElementById("monthYearOfDegree1").value != "MM/YYYY"
// 			&& (document.getElementById("other_date_of_reg").value != "DD/MM/YYYY")
// 			&& (document.getElementById("other_date_of_reg").value != "")) {
// 		var start_date = document.getElementById("monthYearOfDegree1").value.split(
// 				'/').reverse().join('-');
// 		var end_date = document.getElementById("other_date_of_reg").value.split('/')
// 				.reverse().join('-');
// 		var start_dt = new Date(start_date);
// 		var end_dt = new Date(end_date);
// 		// To calculate the time difference of two dates
// 		var Difference_In_Time = end_dt.getTime() - start_dt.getTime();
// 		// To calculate the no. of days between two dates
// 		var Difference_In_Days = (Difference_In_Time / (1000 * 3600 * 24));
// 		var Difference_In_Weeks = Math.floor(Difference_In_Days / 7);
// 		var remainday = (Difference_In_Days) - (Difference_In_Weeks * 7);
// 		if (Difference_In_Days < 1) {
// 			alert("Please Select Valid Date of Reg After Month & Year Of Passing!");
// // 			$("input#direct_date_of_reg").val("DD/MM/YYYY");
// 			$("input#other_date_of_reg").val("DD/MM/YYYY");
// 			$("input#other_date_of_reg").focus();
// 			return false;
// 		}
// 	}
// }

// function onchangeCountDirectDateofReg(val) {
// 	debugger;
// 	if (document.getElementById("monthYearOfDegree1").value != "MM/YYYY"
// 			&& (document.getElementById("direct_date_of_reg").value != "DD/MM/YYYY")
// 			&& (document.getElementById("direct_date_of_reg").value != "")) {
// 		var start_date = document.getElementById("monthYearOfDegree1").value.split(
// 				'/').reverse().join('-');
// 		var end_date = document.getElementById("direct_date_of_reg").value.split('/')
// 				.reverse().join('-');
// 		var start_dt = new Date(start_date);
// 		var end_dt = new Date(end_date);
// 		// To calculate the time difference of two dates
// 		var Difference_In_Time = end_dt.getTime() - start_dt.getTime();
// 		// To calculate the no. of days between two dates
// 		var Difference_In_Days = (Difference_In_Time / (1000 * 3600 * 24));
// 		var Difference_In_Weeks = Math.floor(Difference_In_Days / 7);
// 		var remainday = (Difference_In_Days) - (Difference_In_Weeks * 7);
// 		if (Difference_In_Days < 1) {
// 			alert("Please Select Valid Date of Reg After Month & Year Of Passing!");
// // 			$("input#direct_date_of_reg").val("DD/MM/YYYY");
// 			$("input#direct_date_of_reg").val("DD/MM/YYYY");
// 			$("input#direct_date_of_reg").focus();
// 			return false;
// 		}
// 	}
// }

function  changedatepikettodob(val) {
	
	if (val != "DD/MM/YYYY" || val != "") {
		var x = val.split('/').reverse().join('/');
		$( "#date_of_reg" ).datepicker("option", "minDate", new Date(x));
		$( "#direct_date_of_reg" ).datepicker("option", "minDate", new Date(x));
		$( "#state_validity_upto" ).datepicker("option", "minDate", new Date(x));
		$( "#direct_validity_upto" ).datepicker("option", "minDate", new Date(x));
		$( "#cs_date_of_reg" ).datepicker("option", "minDate", new Date(x));
		$( "#other_date_of_reg" ).datepicker("option", "minDate", new Date(x));
		$( "#other_validity_upto" ).datepicker("option", "minDate", new Date(x));
		
// 		monthYearOfDegree
		
		var y = val.split('/').reverse().join('-');
		var rahu = y.substring(0,7);
		
// 		$("#monthYearOfDegree1").attr("min" : y.substring(0,7));
		  $("#monthYearOfDegree1").prop('min',1950-01);
		  $("#othYrofpass1").prop('min',1950-01);
		  
		
// 		$( "#direct_date_of_reg" ).datepicker("option", "minDate", new Date(x));
		
	}else {
		$( "#date_of_reg" ).datepicker("option", "minDate", 0);
		$( "#date_of_reg" ).datepicker("option", "minDate", 0);
		$( "#direct_date_of_reg" ).datepicker("option", "minDate", 0);
		$( "#state_validity_upto" ).datepicker("option", "minDate", 0);
		$( "#direct_validity_upto" ).datepicker("option", "minDate", 0);
		$( "#cs_date_of_reg" ).datepicker("option", "minDate", 0);
		$( "#other_date_of_reg" ).datepicker("option", "minDate", 0);
		$( "#other_validity_upto" ).datepicker("option", "minDate", 0);
	}
}


// function saveDraft() {
	
// 	var form = $("#step-form-horizontal").serialize();
// 	var dob = $("#date_of_birth").val();
// 	var pre_state=$("#per_state").val();
// 	var pre_dis=$("#per_district").val();
// 	var pre_pin=$("#per_pincode").val();
// 	var cor_state=$("#present_state").val();
// 	var cor_dis=$("#present_district").val();
// 	var cor_pin=$("#present_pincode").val();
	
// 	$.post('teacher_saveDraft_Action?' + key + "=" + value,{form,dob:dob,pre_state:pre_state,pre_dis:pre_dis,pre_pin:pre_pin,cor_state:cor_state,cor_dis,cor_pin},function(j) {

// 		alert("Draft Saved");

		
// 	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
// 	});
// }
//abhihsek

function saveDraft() {
	
	
	$.ajaxSetup({
	    async: false
	});	
	
	
	if ($("#cand_prefix").val() == "0") {
		alert("Please Select the Candidate Prefix");
		$("#cand_prefix").focus();
		return false;
	    }
	    
	if ($("#first_name").val().trim() == "") {
		alert("Please Enter the First Name");
		$("#first_name").focus();
		return false;
	}
	if( $("#middle_name").val() == "" ){
		$("#middle_name").val("N/A");
	}

	if ($("#last_name").val().trim() == "") {
		alert("Please Enter the Last Name");
		$("#last_name").focus();
		return false;
	}
	if( $("#gender").val() == "0" ){
		alert("Please Select Gender");
		$("#gender").focus();
		return false;
   	}
	if( $("#date_of_birth").val() == "" ||  $("#date_of_birth").val() == "DD/MM/YYYY"){
		alert("Please Select Date of Birth");
		$("#date_of_birth").focus();
		return false;
   	}

	if ($("#father_name").val().trim() == "") {
		alert("Please Enter the Father's Name");
		$("#father_name").focus();
		return false;
	}
	if ($("#mother_name").val().trim() == "") {
		alert("Please Enter the Mother's Name");
		$("#mother_name").focus();
		return false;
	}

	if($("#mobile_no").val() == ""){
		alert("Please Enter Mobile Number");
		$("#mobile_no").focus();
		return false;
	}
	if($("#email").val() == ""){
		alert("Please Enter Email Address");
		$("#email").focus();
		return false;
	}
	
	var an1 =  $("#aadhar_no1").val();
	if( an1 == "" ){
		alert("Enter First Four Digit Of Aadhaar No");
		$("#aadhar_no1").focus();
		return false;
   	}
	
	if( parseInt(an1) <= 0){
		alert("Please Enter Valid First Four Digit of Aadhar No");
		$("#aadhar_no1").focus();
		return false;
   	}
	 var minLength = 4;
	 var charLength = an1.length;
       if(charLength < minLength){
       	alert("Please Enter Valid First Four Digit of Aadhar No");
			$("input#aadhar_no1").focus();
			return false;
       }
	
	var an2 =  $("#aadhar_no2").val();
	if( an2 == ""){
		alert("Enter Second Four Digit Of Aadhaar No");
		$("#aadhar_no2").focus();
		return false;
   	}
	if(   parseInt(an2) <= 0){
		alert("Please Enter Valid Second Four Digit Of Aadhar No");
		$("#aadhar_no2").focus();
		return false;
   	}
	
	 var minLength = 4;
	 var charLength = an2.length;
       if(charLength < minLength){
       	alert("Please Enter Valid Second Four Digit Of Aadhar No");
			$("input#aadhar_no2").focus();
			return false;
       }
	
	var an3 =  $("#aadhar_no3").val();
	
	if(an3 == ""){
		alert("Enter Last Four Digit Of Aadhaar No");
		$("#aadhar_no3").focus();
		return false;
   	}
	if(parseInt(an3) <= 0){
		alert("Please Enter Valid Last Four Digit Of Aadhar No");
		$("#aadhar_no3").focus();
		return false;
   	}
	var minLength = 4;
	 var charLength = an3.length;
       if(charLength < minLength){
       	alert("Please Enter Valid Last Four Digit Of Aadhar No");
			$("input#aadhar_no3").focus();
			return false;
       }
       
	if($("#pan_no").val() == ""){
		alert("Please Enter PAN No.");
		$("#pan_no").focus();
		return false;
	}
	
	if ($("#current_designation").val() == "0") {
		alert("Please Enter Current Designation");
		$("#current_designation").focus();
		return false;
	}
	if( $("#date_of_joining").val() == "" ||  $("#date_of_joining").val() == "DD/MM/YYYY"){
		alert("Please Select Date of Joining");
		$("#date_of_joining").focus();
		return false;
   	}
	if( $("#date_of_appoint").val() == "" ||  $("#date_of_appoint").val() == "DD/MM/YYYY"){
		alert("Please Select Nature of Appointment");
		$("#date_of_appoint").focus();
		return false;
   	}

// 	///////Permanent Address validation
	if ($("#per_add_line1").val().trim() == "") {
		alert("Please Enter the Present Address-Line-1");
		$("#per_add_line1").focus();
		return false;
	} 
	if ($("#per_add_line2").val().trim() == "") {
		alert("Please Enter the Present Address-Line-2");
		$("#per_add_line2").focus();
		return false;
	} 
	if( $("#per_state").val() == "0" ){
		alert("Please Select State");
		$("#per_state").focus();
		return false;
   	}
	if( $("#per_district").val() == "0" ){
		alert("Please Select District");
		$("#per_district").focus();
		return false;
   	}
	if ($("#per_village").val().trim() == "") {
		alert("Please Enter the Present City/Village");
		$("#per_village").focus();
		return false;
   	}
// 	if ($("#per_pincode").val().trim() == "") {
// 		alert("Please Enter the Pin code");
// 		$("#per_pincode").focus();
// 		return false;
//    	}
	
// 	var x= $("#per_pincode").val().trim();

// 	if(parseInt(x) == 0 ){
// 		alert("Please Enter the Valid xxxxxxxxxxxx Present Pin Code");
// 		$("#per_pincode").focus();
// 		return false;
// 	}
	
	
	
	  var per_pin = $("#per_pincode").val().trim();
	  
		 if (per_pin == "") {
				alert("Please Enter the Present Pincode");
				$("#per_pincode").focus();
				return false;
			} 
		 
		 if(parseInt(per_pin) <= 0){
				alert("Please Enter Valid Present Pincode");
				$("#per_pincode").focus();
				return false;
		   	}
		 
		 var minLength = 6;
		 var charLength = per_pin.length;
	       if(charLength < minLength){
	       	alert("Present Pincode Should Contain Minimum 6 Digit");
				$("input#per_pincode").focus();
				return false;
	       } 
	       
		  var maxLength = 6;
			 var charLength = per_pin.length;
		       if(charLength > maxLength){
		       	alert("Present Pincode Should Contain Maximum 6 Digit");
					$("input#per_pincode").focus();
					return false;
		       } 

	
	
	
	
	
	
// // 	if ($("#per_phn_no").val().trim() == "") {
// // 		alert("Please Enter the Permanent Phone No.");
// // 		$("#per_phn_no").focus();
// // 		return false;
// //    	}
	if(document.getElementById('check_address').checked == false) { 
		if ($("#present_add_line1").val().trim() == "") {
			alert("Please Enter the Correspondence Address-Line-1");
			$("#present_add_line1").focus();
			return false;
		}
		if ($("#present_add_line2").val().trim() == "") {
			alert("Please Enter the Correspondence Address-Line-2");
			$("#present_add_line2").focus();
			return false;
		}
		if( $("#present_state").val() == "0" ){
			alert("Please Select Correspondence State");
			$("#present_state").focus();
			return false;
	   	}
		if( $("#present_district").val() == "0" ){
			alert("Please Select Correspondence District");
			$("#present_district").focus();
			return false;
	   	}
		if ($("#present_village").val().trim() == "") {
			alert("Please Enter the Correspondence City/Village");
			$("#present_village").focus();
			return false;
	   	}
		
		
		
		  var cor_pin = $("#present_pincode").val().trim();
		  
			 if (cor_pin == "") {
					alert("Please Enter the Correspondence Pincode");
					$("#present_pincode").focus();
					return false;
				} 
			 
			 if(parseInt(cor_pin) <= 0){
					alert("Please Enter Valid Correspondence Pincode");
					$("#present_pincode").focus();
					return false;
			   	}
			 
			 var minLength = 6;
			 var charLength = cor_pin.length;
		       if(charLength < minLength){
		       	alert("Correspondence Pincode Should Contain Minimum 6 Digit");
					$("input#present_pincode").focus();
					return false;
		       } 
		       
			  var maxLength = 6;
				 var charLength = cor_pin.length;
			       if(charLength > maxLength){
			       	alert("Correspondence Pincode Should Contain Maximum 6 Digit");
						$("input#present_pincode").focus();
						return false;
			       }
		
			       return true;


	}
	
	
	var form_data = new FormData(document.getElementById("step-form-horizontal"));
	
 		form_data.append("dob",$("#date_of_birth").val());
 		
		$.ajax({
	        url: 'teacher_saveDraft_Action?' + key + "=" + value,
	        type: "POST",
	        data: form_data,
	        enctype: 'multipart/form-data',
	        processData: false,
	        contentType: false
	      }).done(function(data) {
	    	  
	    	 alert(data.msg);
	    	  window.location.reload();
// 	    	  alert("Data Save As Draft SuccesFully");
// 	    	  if(parseInt(data[0][0])>0){
// 	        	  alert("Data Saved SuccesFully");
// 	        	 window.location='Search_do_Url';
// 	          }else if(data[0][0]=="update" || data[0][0]=="0" ){
// 	        	  alert("Data Updated Successfully");
// 		    	  window.location='Search_do_Url';  
// 	          }else{
// 	        	  alert(data[0][0]);  
// 	          }
       	
  		}).fail(function(xhr, textStatus, errorThrown) {alert("Something Went Wrong")
	         
	      }).fail(function(jqXHR, textStatus) {
	          //alert(jqXHR.responseText);
	        //  alert('File upload failed ...');
	      });
 	//}
		 
 }



// function saveDraftforquali() {
// 	
// 	var form = $("#step-form-horizontal").serialize();
	
	
// 	$.post('teacher_saveDraftforquali_Action?' + key + "=" + value,{form},function(j) {

// 		alert("Draft Saved");

		
// 	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
// 	});
// }


function saveDraftforquali() {
	
	$.ajaxSetup({
	    async: false
	});	

	var form_data = new FormData(document.getElementById("step-form-horizontal"));
	
	
	
	 for(qua = 1; qua <= $('#count_hidden_att_name_med').val(); qua++){
			if($("#typeOfDegree"+qua).val()=='0'){
				alert("Please Select Name of Exam/Degree In "+qua+" Row");
				$("#typeOfDegree"+qua).focus();
				return false;
			}
			if($("#course"+qua).val()=='0'){
				alert("Please Select Course Name In "+qua+" Row");
				$("#course"+qua).focus();
				return false;
			}
			
			if ($("#hide_sub"+qua).css("display") == "block") {
				if($("#subject"+qua).val() == '0'){
				alert("Please Select Subject In "+qua+" Row");
				$("#subject"+qua).focus();
				return false;
			}
		}
			
			if($("#NameOfUniversity"+qua).val()=='0'){
				alert("Please Enter Name of Institute/College In "+qua+" Row");
				$("#NameOfUniversity"+qua).focus();
				return false;
			}
			if($("#NameOfAffUni"+qua).val()=='0'){
				alert("Please Enter Name of Affiliated University In "+qua+" Row");
				$("#NameOfAffUni"+qua).focus();
				return false;
			}
			if($("input#monthYearOfDegree"+qua).val().trim()=="" ){
				alert("Please Enter Month and Year Of Passing In"+qua+" Row");
				$("#monthYearOfDegree"+qua).focus();
				return false;
			}
			dynamicTable(1,qua);
			
			console.log("-------->    "+qua  + " ----->     "+ $('#count_hidden_att_doc'+qua).val());
			
			if ($('#count_hidden_att_doc'+qua).val() == undefined || $('#count_hidden_att_doc'+qua).val() == "undefined" ) {
				alert("Please Select Name of Attachment In "+qua+" Row")
				return false;
			}
			
			
		for(a1 = 1; a1 <= $('#count_hidden_att_doc'+qua).val(); a1++){
		//		for(a1 = 1; a1 <= $('#id_add_attNameMed'+qua).val(); a1++){
				
				//alert("qua------   "+qua+  "   a1 ------    "+a1);
				
				if($("#name_of_attachment"+qua+"_"+a1).val()=='0'){
						alert("Please Select Name of Attachment In "+a1+" Row In Docs Attachment");
						$("#name_of_attachment"+qua+"_"+a1).focus();
						return false;
						
				}
// 				if($("#name_of_attachment"+qua+"_"+a1).val()=='3'){
// 					alert("Please Select Name of Attachment 'Degree' is Mandatory In "+a1+" Row");
// 					$("#name_of_attachment"+qua+"_"+a1).focus();
// 					return false;
// 			}
			
				if($("#name_of_attachment"+qua+"_"+a1).val()=='3'){
					if($("#name_of_degree"+qua+"_"+a1).val()=='0'){
							alert("Please Select Certificate Type In "+a1+" Row In Docs Attachment");
							$("#name_of_degree"+qua+"_"+a1).focus();
							return false;
					}
				}
				if($("#attachmentDocument"+qua+"_"+a1).val()==''){
					if($("#attachmentDoc_hid"+qua+"_"+a1).val()==''){
							alert("Please Select Attachment File In "+a1+" Row In Docs Attachment");
							$("#name_of_degree"+qua+"_"+a1).focus();
							return false;
					}
				}
			 }
	
	}

		$.ajax({
	        url: 'teacher_saveDraftforquali_Action?'+ key + "=" + value,
	        type: "POST",
	        data: form_data,
	        enctype: 'multipart/form-data',
	        processData: false,
	        contentType: false
	      }).done(function(data) {
// 	    	  if(data.get('msg')!="Data Saved SuccesFully" || data.get('msg')!="Data Updated Successfully"){
// 	    		  alert(data.get('msg'));
// 	    	  }else{

	    		  alert(data.msg);
	    		  fetchdataforprofession(1);
	    		 
	    		  fetchdataforprofession(2);
	    		  //window.location.reload();
	    	  //}
// 	    	  if(parseInt(data[0][0])>0){
// 	        	  alert("Data Saved SuccesFully");
	        	 //window.location='Search_do_Url';
// 	          }else if(data[0][0]=="update" || data[0][0]=="0" ){
// 	        	  alert("Data Updated Successfully");
// 		    	  window.location='Search_do_Url';  
// 	          }else{
// 	        	  alert(data[0][0]);  
// 	          }
       	
  		}).fail(function(xhr, textStatus, errorThrown) {alert("Something Went Wrong")
	         
	      }).fail(function(jqXHR, textStatus) {
	          alert(jqXHR.responseText);
	          //alert('File upload failed ...');
	      });

}


function saveDraftforregi() {

	$.ajaxSetup({
	    async: false
	});	

	var form_data = new FormData(document.getElementById("step-form-horizontal"));
	

	
	if ($("#registration_type").val() == "0") {
		alert("Please Select the Registration Type");
		$("#registration_type").focus();
		return false;
	}
	if ($("#registration_type").val() == "1") {
	
		if ($("#state_reg_no").val().trim() == "") {
			alert("Please Enter the State Registration No.");
			$("#state_reg_no").focus();
			return false;
	   	}
		if($("#state_reg_no").val() =="0" || $("#state_reg_no").val() == " "){
			alert("Please Enter the Valid State Registration No.");
			$("#state_reg_no").focus();
			return false;
		}
		
		if ($("#state_board_name").val().trim() == "0") {
			alert("Please Select the Name of the State Board");
			$("#state_board_name").focus();
			return false;
	   	}
		if ($("#date_of_reg").val() == "" || $("#date_of_reg").val() == "DD/MM/YYYY") {
			alert("Please Select the Date Of Registration");
			$("#date_of_reg").focus();
			return false;
		}
		if ($("#state_validity_upto").val() == "" || $("#state_validity_upto").val() == "DD/MM/YYYY") {
			alert("Please Select the Validity Upto");
			$("#state_validity_upto").focus();
			return false;
		}
		if(getformatedDate($("input#date_of_reg").val()) > getformatedDate($("#state_validity_upto").val())) {
			alert("Validity upto should be Greater than Or Equal to Date of Reg   Row");
			$("#state_validity_upto"+exp).focus();
			return false;
	    }
// 		if ($("#central_reg_no").val().trim() == "") {
// 			alert("Please Enter the Central Registration No.");
// 			$("#central_reg_no").focus();
// 			return false;
// 	   	}
		
		if($("#central_reg_no").val() =="0"){
			alert("Please Enter the Valid Registration No.");
			$("#central_reg_no").focus();
			return false;
		}
		
		
}	
if ($("#registration_type").val() == "2") {
	
	if ($("#direct_reg_no").val().trim() == "") {
		alert("Please Enter the Direct Registration No.");
		$("#direct_reg_no").focus();
		return false;
   	}
	if($("#direct_reg_no").val() =="0"){
		alert("Please Enter the Valid Direct Registration No.");
		$("#direct_reg_no").focus();
		return false;
	}
	if ($("#direct_date_of_reg").val() == "" || $("#direct_date_of_reg").val() == "DD/MM/YYYY") {
		alert("Please Select the Direct Date Of Registration");
		$("#direct_date_of_reg").focus();
		return false;
	}
	if ($("#name_of_department").val().trim() == "") {
		alert("Please Enter the Name of Department/Organization");
		$("#name_of_department").focus();
		return false;
   	}
	if ($("#direct_validity_upto").val() == "" || $("#direct_validity_upto").val() == "DD/MM/YYYY") {
		alert("Please Select the Validity Upto");
		$("#direct_validity_upto").focus();
		return false;
	}
}

		$.ajax({
	        url: 'teacher_saveDraftforregi_Action?'+ key + "=" + value,
	        type: "POST",
	        data: form_data,
	        enctype: 'multipart/form-data',
	        processData: false,
	        contentType: false
	      }).done(function(data) {
	    	  
	    	  alert(data.msg);
	    	//  window.location.reload();
	    	//  window.opener.location.reload();
	    	  
// 	    	  alert("Data Save As Draft SuccesFully");
// 	    	  if(parseInt(data[0][0])>0){
// 	        	  alert("Data Saved SuccesFully");
// 	        	 window.location='Search_do_Url';
// 	          }else if(data[0][0]=="update" || data[0][0]=="0" ){
// 	        	  alert("Data Updated Successfully");
// 		    	  window.location='Search_do_Url';  
// 	          }else{
// 	        	  alert(data[0][0]);  
// 	          }
       	
  		}).fail(function(xhr, textStatus, errorThrown) {alert("Something Went Wrong")
	         
	      }).fail(function(jqXHR, textStatus) {
	        //  alert(jqXHR.responseText);
	         // alert('File upload failed ...');
	      });
}


function saveDraftforacademic() {
	
	
	
	$.ajaxSetup({
	    async: false
	});	

	var form_data = new FormData(document.getElementById("step-form-horizontal"));
	
	
	var exp=0;
	for(exp = 1; exp <= $('#count_hidden_att').val(); exp++){
		if($("#institute_name"+exp).val().trim()==''){
			alert("Please Enter Organization Name In "+exp+" Row");
			$("#institute_name"+exp).focus();
			return false;
		}
		if($("#depart_name"+exp).val()=='0'){
			alert("Please Select Department Name In "+exp+" Row");
			$("#depart_name"+exp).focus();
			return false;
		}
		if($("#desig"+exp).val()=='0'){
			alert("Please Select Designation/Post In "+exp+" Row");
			$("#desig"+exp).focus();
			return false;
		}
		if($("#employment_type"+exp).val()=='0'){
			alert("Please Select Employment Type In "+exp+" Row");
			$("#employment_type"+exp).focus();
			return false;
		}
		
		if($("input#from_date"+exp).val().trim()=="" || $("input#from_date"+exp).val().trim()=="DD/MM/YYYY"){
			alert("Please Enter From Date In"+exp+" Row");
			$("#from_date"+exp).focus();
			return false;
		}
		if($("input#to_date"+exp).val().trim()=="" || $("input#to_date"+exp).val().trim()=="DD/MM/YYYY"){
			alert("Please Enter To Date In "+exp+" Row");
			$("#to_date"+exp).focus();
			return false;
		}
		if(getformatedDate($("input#from_date"+exp).val()) > getformatedDate($("#to_date"+exp).val())) {
			alert("To Date should be Greater than Or Equal to From Date  In "+exp+" Row");
			$("#to_date"+exp).focus();
			return false;
	    }
		if($("#honorarium"+exp).val()=='0'){
			alert("Please Select Honorarium In "+exp+" Row");
			$("#honorarium"+exp).focus();
			return false;
		}
		//upload_file1
		
		 if($("#upload_file"+exp).val().trim() == ""){
			 if($("#upload_file_hid"+exp).val().trim() == ""){
				 alert("Please Select Upload File In Profession/Academic Experience In"+exp+" Row");
				 	$("input#upload_file"+exp).focus();
					return false;
			 }
		}
//			/*  if($("#data_fetch").val() == "1"){
//			 	if($("#upload_file_hid"+exp).val() == ""){	
//				 		alert("Please Select File "+exp+" Row");
//				 		 $("input#upload_file"+exp).focus();
//						 return false;
//				}
//			}else{
//			 	if($("#upload_file"+exp).val() == ""){
//	 			 	alert("Please Select File "+exp+" Row");
//	 			 	$("input#upload_file"+exp).focus();
//	 				return false;
//	 			 }
//			 }  
	
	if($("#institute_name"+exp).val() != "0" && $("#institute_name"+exp).val() != "1")
	//	count_classi++;
	if($("#institute_name"+exp).val() != "1"){
		if($("#institute_name"+exp).val().trim()==''){
			alert("Please Enter Organization Name In "+exp+" Row");
			$("#institute_name"+exp).focus();
			return false;
		}
		if($("#depart_name"+exp).val()=='0'){
			alert("Please Select Department Name In "+exp+" Row");
			$("#depart_name"+exp).focus();
			return false;
		}
		if($("#desig"+exp).val()=='0'){
			alert("Please Select Designation In "+exp+" Row");
			$("#desig"+exp).focus();
			return false;
		}
		if($("#employment_type"+exp).val()=='0'){
			alert("Please Select Employment Type In "+exp+" Row");
			$("#employment_type"+exp).focus();
			return false;
		}
		if($("input#from_date"+exp).val().trim()=="" || $("input#from_date"+exp).val().trim()=="DD/MM/YYYY"){
			alert("Please Enter From Date In"+exp+" Row");
			$("#from_date"+exp).focus();
			return false;
		}
		if($("input#to_date"+exp).val().trim()=="" || $("input#to_date"+exp).val().trim()=="DD/MM/YYYY"){
			alert("Please Enter To Date In"+exp+" Row");
			$("#to_date"+exp).focus();
			return false;
		}
		if(getformatedDate($("input#from_date"+exp).val()) > getformatedDate($("#to_date"+exp).val())) {
			alert("To Date should be Greater than Or Equal to From Date In "+exp+" Row");
			$("#to_date"+exp).focus();
			return false;
	    }
		if($("#honorarium"+exp).val()=='0'){
			alert("Please Select Honorarium In "+exp+" Row");
			$("#honorarium"+exp).focus();
			return false;
		}
		 if($("#upload_file"+exp).val().trim() == ""){
			 if($("#upload_file_hid"+exp).val().trim() == ""){
				 alert("Please Select Upload File In Profession/Academic Experience In  "+exp+" Row");
				 	$("input#upload_file"+exp).focus();
					return false;
			 }
		}
//			/* if($("#data_fetch").val() == "1"){
//				 if($("#upload_file_hid"+exp).val() == ""){	
//					alert("Please Select File "+exp+" Row");
//					 $("input#upload_file"+exp).focus();
//					 return false;
//				 }
//			}else{
//			 	 if($("#upload_file"+exp).val() == ""){
//	 			 	alert("Please Select File "+exp+" Row");
//	 			 	$("input#upload_file"+exp).focus();
//	 				return false;
//	 			 }
//			 } 
	}	
}
	

		$.ajax({
	        url: 'teacher_saveDraftforacademic_Action?'+ key + "=" + value,
	        type: "POST",
	        data: form_data,
	        enctype: 'multipart/form-data',
	        processData: false,
	        contentType: false
	      }).done(function(data) {
	    	  alert(data.msg);
	    	  
	    	  fetchdataforprofession(3);
	    	 // window.location.reload();
// 	    	  alert("Data Save As Draft SuccesFully");
// 	    	  if(parseInt(data[0][0])>0){
// 	        	  alert("Data Saved SuccesFully");
// 	        	 window.location='Search_do_Url';
// 	          }else if(data[0][0]=="update" || data[0][0]=="0" ){
// 	        	  alert("Data Updated Successfully");
// 		    	  window.location='Search_do_Url';  
// 	          }else{
// 	        	  alert(data[0][0]);  
// 	          }
       	
  		}).fail(function(xhr, textStatus, errorThrown) {alert("Something Went Wrong")
	         
	      }).fail(function(jqXHR, textStatus) {
	          alert(jqXHR.responseText);
	         // alert('File upload failed ...');
	      });
}


function saveDraftforexperience() {

	$.ajaxSetup({
	    async: false
	});	

	var form_data = new FormData(document.getElementById("step-form-horizontal"));
	
	var exp=0;
	for(deg = 1; deg <= $('#count_hidden_doc').val(); deg++){
		
		if($("#doc_name"+deg).val()=='0'){
			alert("Please Select Document Name In "+deg+" Row");
			$("#doc_name"+deg).focus();
			return false;
		}
		if($("#doc_id"+deg).val()=='0'){
			alert("Please Select Document Type In "+deg+" Row");
			$("#doc_id"+deg).focus();
			return false;
		}
		//upload_doc1
		
		 if($("#upload_doc"+deg).val().trim() == ""){
			 if($("#doc_upload_hid"+deg).val().trim() == ""){
				 alert("Please Select Upload File In Additional Qalification In"+deg+" Row");
				 	$("input#upload_doc"+deg).focus();
					return false;
			 }
		}
		
//			/*  if($("#data_fetch").val() == "1"){
//			 	 if($("#doc_upload_hid"+deg).val() == ""){	
//					 alert("Please Select Document "+deg+" Row");
//					 $("input#upload_doc"+deg).focus();
//					 return false;
//			 	 }
//			}else{
//				if($("#upload_doc"+deg).val() == ""){
//					alert("Please Select Document "+deg+" Row");
//					$("input#upload_doc"+deg).focus();
//					return false;
//				}
//			}  
	
		if($("#doc_name"+deg).val() != "0" && $("#doc_name"+deg).val() != "1")
		//	count_classi++;
		if($("#doc_name"+deg).val() != "1"){
		if($("#doc_name"+deg).val()=='0'){
			alert("Please Select Document Name In "+deg+" Row");
			$("#doc_name"+deg).focus();
			return false;
		}
		if($("#doc_id"+deg).val()=='0'){
			alert("Please Select Document Type In "+deg+" Row");
			$("#doc_id"+deg).focus();
			return false;
		}
		 if($("#upload_doc"+deg).val().trim() == ""){
			 if($("#doc_upload_hid"+deg).val().trim() == ""){
			 	alert("Please Select Upload File In Additional Documents In "+deg+" Row");
			 	$("input#upload_doc"+deg).focus();
				return false;
			 }
		 }
//			/*  if($("#data_fetch").val() == "1"){
//				 if($("#doc_upload_hid"+deg).val() == ""){	
//					alert("Please Select Document "+deg+" Row");
//					$("input#upload_doc"+deg).focus();
//					return false;
//				 }
//			}else{
//				if($("#upload_doc"+deg).val() == ""){
//					alert("Please Select Document "+deg+" Row");
//					$("input#upload_doc"+deg).focus();
//					return false;
//				}
//			}  
	}	
}

		$.ajax({
	        url: 'teacher_saveDraftforexperience_Action?'+ key + "=" + value,
	        type: "POST",
	        data: form_data,
	        enctype: 'multipart/form-data',
	        processData: false,
	        contentType: false
	      }).done(function(data) {
	    	  alert(data.msg);
	    	  fetchdataforprofession(4);
	    	 // window.location.reload();
	    	//  window.opener.location.reload();
// 	    	  alert("Data Save As Draft SuccesFully");
// 	    	  if(parseInt(data[0][0])>0){
// 	        	  alert("Data Saved SuccesFully");
// 	        	 window.location='Search_do_Url';
// 	          }else if(data[0][0]=="update" || data[0][0]=="0" ){
// 	        	  alert("Data Updated Successfully");
// 		    	  window.location='Search_do_Url';  
// 	          }else{
// 	        	  alert(data[0][0]);  
// 	          }
       	
  		}).fail(function(xhr, textStatus, errorThrown) {alert("Something Went Wrong")
	         
	      }).fail(function(jqXHR, textStatus) {
	          alert(jqXHR.responseText);
	          //alert('File upload failed ...');
	      });

}



function fetchdataforprofession(val) {
	$.ajaxSetup({
		async : false
	});
	var pernt_id = $("#perentId").val();
	var user_id = "{$userId}";
	

		 $.post("getprofessiondata?"+ key + "=" + value,{main_id : pernt_id,val : val},
					function(j) {


			 if (j.length == 0) {
					return false;
				}


					if(val==1){
				
						$("#addNameOfMed > tbody").html("");
						$("#dynamicDataTable").html("");
						
			
						var ser = 0;
						var ind =1;
					for (var l = 0; l < j.length; l++) {

								formOpenNameMed(ser);
			
								var id = j[l][0];
								var qulification_child_id = j[l][0];
								var typeOfDegree = j[l][1];
								var course = j[l][2];
								var subject = j[l][3];
								var NameOfUniversity = j[l][4];
								var NameOfAffUniversity = j[l][5];
								var monthYearOfDegree = j[l][6];
					
					
							$("#typeOfDegree"+ind).val(typeOfDegree);
							$("#typeOfDegree"+ind).change();
							GetCoursebytype(ind);
							$("#course"+ind).val(course);
							GetSubfromCourse(ind);
							
							$("#subject"+ind).val(subject);
							$("#qualtification_id"+ind).val(id);
							$("#monthYearOfDegree"+ind).val(monthYearOfDegree);
							$("#monthYearOfDegree"+ind).change();
							$("#NameOfUniversity"+ind).val(NameOfUniversity);
							$("#NameOfUniversity"+ind).change();
							getuniversitybyinst(ind);
							
							$("#NameOfAffUni"+ind).val(NameOfAffUniversity);
							if(NameOfAffUniversity.toUpperCase()== "OTHER"){
								$("#NameOfUniversity"+ind).val(NameOfUniversity.toUpperCase());
								//$("#universityother"+ind).show();
								if ($("#universityother"+ind).hasClass("d-none")) {
									 $( "#universityother"+ind).removeClass("d-none")
										}
								$("#universityother"+ind).val(j[l][10]);
							}else{
								//$("#universityother"+ind).hide();
								if (!$( "#universityother"+ind).hasClass('d-none')) {
									$( "#universityother"+ind).addClass("d-none")
								}
							}
							

							$.post("getaddmoreattchfetch?" + key + "=" + value, {parent_id:user_id,val:val}, function(z) {
							
							if(parseInt(z.length) > 0){
								
								
								
								var k = 1;
								for (var m = 0; m < z.length; m++) {
								
								var parent_id= z[m][1];
								var id= z[m][0];
								var path_of_att=z[m][3];
								var name_of_att=z[m][2];
								var cert_type=z[m][4];
								
					 			if(qulification_child_id == parent_id){
									dynamicTable(ind,k);
									
									if(k > 1){
										$("#id_add_attDoc"+ind+"_"+(k-1)).click();
									}
									
									if(name_of_att != null && name_of_att.trim() != ""){
										$("#name_of_attachment"+ind+"_"+k).val(name_of_att);
										$("#medicalqualificationAtt_id"+ind+"_"+k).val(id);
										degreehide(ind,k);
										if(name_of_att == "3"){
											$("#name_of_degree"+ind+"_"+k).val(cert_type);
										}
									}
									
									if(path_of_att != null && path_of_att.trim() != ""){
										$("#attachmentDoc_hid"+ind+"_"+k).val(path_of_att);
									}
									
									$("#count_hidden_att_doc"+ind).val(k);
									
									k++;
									}
								}	
								
							}
					});
							ser=parseInt(ser)+1;
							ind=parseInt(ind)+1;
						
					}
					
					fetchdataforotherquali(2);
						
					}




			if(val==3){
				
				$("#att_Tb > tbody").html("");
							 
							 var ser = 0;
								var ind =1;
								
								for (var g = 0; g < j.length; g++) {
									
										formopen_att(ser);
				
				var h = j[g][0];
				
									var id = j[g][6];
									var inst_name = j[g][0];
									
									var depart_name = j[g][1];
									var desig = j[g][2];
									var employment_type = j[g][8];
									var fd = j[g][3];
									var td = j[g][4];
									var upload_file = j[g][5];
									var honorarium = j[g][9];
									var yr_of_exp = j[g][7];
									
				
									
							        $("#institute_name"+ind).val(inst_name);
							        $("#profession_id"+ind).val(id);
							  		$("#desig"+ind).val(desig);
							  		$("#employment_type"+ind).val(employment_type);
							  		$("#depart_name"+ind).val(depart_name);
							  		$("#from_date"+ind).val(fd);
							  		$("#to_date"+ind).val(td);
							  		$("#yr_of_exp"+ind).val(yr_of_exp);
							  		$("#honorarium"+ind).val(honorarium);
							  		$("#upload_file_hid"+ind).val(upload_file);
							  		
							  		
									
									ser=parseInt(ser)+1;
									ind=parseInt(ind)+1;
									
				
								}
								
				}	
			
			if(val==4){
				
				$("#doc_Tb > tbody").html("");
							
							
			var ser = 0;
			var ind =1;
			
			for (var k = 0; k < j.length; k++) {
			
			
					formopen_doc(ser);
			
				
				//var  doc_name= "${j[0]}";
				var doc_name = j[k][0];
				var doc_id = j[k][1];
			//	var doc_id = "${j[1]}";
				//var upload_doc = "${j[2]}";
				var upload_doc = j[k][2];
				//var id = "${j[3]}";
				var id = j[k][3];
				
				
				$("#doc_name"+ind).val(doc_name);
				GetTypeFromDoc(ind);
				 $("#add_quali"+ind).val(id);
				$("#doc_name"+ind).change();
				
				$("#doc_id"+ind).val(doc_id);
				formateOfDoc(ind);
			//		$("#doc_id"+ind).change();
				$("#doc_upload_hid"+ind).val(upload_doc);
				
				ser=parseInt(ser)+1;
				ind=parseInt(ind)+1;
					
			}		
											
			}			
						 
		});

}


//--------------------------------------Type Of Experience Save-----------------------------------------

function saveDraftforTypeofexperience() {

	$.ajaxSetup({
	    async: false
	});	

	var form_data = new FormData(document.getElementById("step-form-horizontal"));
	
	var exp=0;
	for(exp = 1; exp <= $('#count_hidden_att_pro').val(); exp++){
		
	
		if($("#organization_name"+exp).val().trim()==''){
			alert("Please Enter Organization Name In "+exp+" Row");
			$("#organization_name"+exp).focus();
			return false;
		}
		
		
		if($("input#from_date_pro"+exp).val().trim()=="" || $("input#from_date_pro"+exp).val().trim()=="DD/MM/YYYY"){
			alert("Please Enter From Date In "+exp+" Row");
			$("#from_date_pro"+exp).focus();
			return false;
		}
		if($("input#to_date_pro"+exp).val().trim()=="" || $("input#to_date_pro"+exp).val().trim()=="DD/MM/YYYY"){
			alert("Please Enter To Date In "+exp+" Row");
			$("#to_date_pro"+exp).focus();
			return false;
		}
		if(getformatedDate($("input#from_date_pro"+exp).val()) > getformatedDate($("#to_date_pro"+exp).val())) {
			alert("To Date should be Greater than Or Equal to From Date  In "+exp+" Row");
			$("#to_date_pro"+exp).focus();
			return false;
	    }
		
		if($("#designation_name"+exp).val().trim()==''){
			alert("Please Enter Designation Name In "+exp+" Row");
			$("#designation_name"+exp).focus();
			return false;
		}
		if($("#type_of_exp"+exp).val()=='0'){
			alert("Please Select Type Of Experience In "+exp+" Row");
			$("#type_of_exp"+exp).focus();
			return false;
		}
	
	
 
	
	if($("#organization_name"+exp).val() != "0" && $("#organization_name"+exp).val() != "1")
	//	count_classi++;
	if($("#organization_name"+exp).val() != "1"){
	
		if($("#organization_name"+exp).val().trim()==''){
			alert("Please Enter Organization Name In "+exp+" Row");
			$("#organization_name"+exp).focus();
			return false;
		}
		
		
		if($("input#from_date_pro"+exp).val().trim()=="" || $("input#from_date_pro"+exp).val().trim()=="DD/MM/YYYY"){
			alert("Please Enter From Date In "+exp+" Row");
			$("#from_date_pro"+exp).focus();
			return false;
		}
		if($("input#to_date_pro"+exp).val().trim()=="" || $("input#to_date_pro"+exp).val().trim()=="DD/MM/YYYY"){
			alert("Please Enter To Date In"+exp+" Row");
			$("#to_date_pro"+exp).focus();
			return false;
		}
		if(getformatedDate($("input#from_date_pro"+exp).val()) > getformatedDate($("#to_date_pro"+exp).val())) {
			alert("To Date should be Greater than Or Equal to From Date In "+exp+" Row");
			$("#to_date_pro"+exp).focus();
			return false;
	    }
		
		if($("#designation_name"+exp).val().trim()==''){
			alert("Please Enter Designation Name In "+exp+" Row");
			$("#designation_name"+exp).focus();
			return false;
		}
		if($("#type_of_exp"+exp).val()=='0'){
			alert("Please Select Type Of Experiencce In "+exp+" Row");
			$("#type_of_exp"+exp).focus();
			return false;
		}

	}	
}
	


		$.ajax({
	        url: 'teacher_saveDraftforacademicexperience_Action?'+ key + "=" + value,
	        type: "POST",
	        data: form_data,
	        enctype: 'multipart/form-data',
	        processData: false,
	        contentType: false
	      }).done(function(data) {
	    	  alert(data.msg);
	    	//  fetchdataforprofession(4);

       	
  		}).fail(function(xhr, textStatus, errorThrown) {alert("Something Went Wrong")
	         
	      }).fail(function(jqXHR, textStatus) {
	         // alert(jqXHR.responseText);
	          //alert('File upload failed ...');
	      });

}




	function fetchdataforotherquali(val) {
		
					$.ajaxSetup({
						async : false
					});
					var pernt_id = $("#perentId").val();
					var user_id = "{$userId}";
					
						 $.post("getprofessiondata?" + key + "=" + value,{main_id : pernt_id,val : val},
									function(p) {
				
					if (p.length == 0) {
						return false;
					}
// 				j.length
				
				if(val==2){
					
				
					$("#addothquali > tbody").html("");
					$("#dynamicDataTableOQ").html("");
					
					var ser = 0;
					var ind =1;
				for (var l = 0; l < p.length; l++) {
				
							formOpenOthQuali(ser);
				
							var id = p[l][0];
							var name_of_exam_degree = p[l][2];
							var name_of_uni_inst = p[l][3];
							var name_of_aff_uni = p[l][4];
							var month_and_year = p[l][5];
							var subject = p[l][6];
				
							$("#OthnameofExDeg"+ind).val(name_of_exam_degree);
							$("#othquali_id"+ind).val(id);
							$("#othSubject"+ind).val(subject);
							$("#othUniInst"+ind).val(name_of_uni_inst);
							$("#othAffuni"+ind).val(name_of_aff_uni);
							$("#othYrofpass"+ind).val(month_and_year);
				
				
						
						$.post("getaddmoreattchfetchother?" + key + "=" + value, {main_id : pernt_id,val:val}, function(o) {
							//HARSH
							
						if(parseInt(o.length) > 0){
							
					
					
							var k = 1;
							
							for (var m = 0; m < o.length; m++) {
								
				
								var parent_id= o[m][1];
								var child_id= o[m][0];
								var name_of_att=o[m][2];
								var path_of_att=o[m][3];
							
							if(parent_id == id){
								dynamicTableOQ(ind,k);
								
								if(k > 1){
									$("#id_add_attOQ"+ind+"_"+(k-1)).click();
								}
								
								if(name_of_att != null && name_of_att.trim() != ""){
									$("#name_of_attachmentOQ"+ind+"_"+k).val(name_of_att);
									$("#otherqualificationAtt_id"+ind+"_"+k).val(child_id);
								}
								
								if(path_of_att != null && path_of_att.trim() != ""){
									$("#attachmentDoc_hidOQ"+ind+"_"+k).val(path_of_att);
								}
								
								$("#count_hidden_att_OQ"+ind).val(k);
								k++;
							}
							}	
							
						}
				});
						ser=parseInt(ser)+1;
						ind=parseInt(ind)+1;
					
				}
					
				}
				
							});
				
	}
						
						
					function login_fetch() {
						
								
								var aadhar_no = "${datafetchlog[0][0]}";
								var a1 = aadhar_no.substring(0, 4);
								var a2 = aadhar_no.substring(4, 8);
								var a3 = aadhar_no.substring(8, 12);
								var  mobile= "${datafetchlog[0][1]}";
								var email = "${datafetchlog[0][2]}";
								var login = "${datafetchlog[0][3]}";
							
								$("#aadhar_no1").val(a1);
								$("#aadhar_no2").val(a2);
								$("#aadhar_no3").val(a3);
								//alert(mobile);
								$("#mobile_no").val(mobile);
								$("#email").val(email);
								$("#first_name").val(login);
	
						}
					
					
					
					function faculty_transfer () {

						$.post('faculty_transfer_action?' + key + "=" + value, {
// 							id:id,
// 							valid_upto:valid_upto
						}).done(function(j) {
								
// 								AfterReject_Final_Submit(id);
// 								alert("Approved Successfully");
// 								location.reload();
						});
					}
													
					function ViewData(id){
						
						var data =  '${data}';
				
						$("#id6").val('${data[0].id}');
						$("#statusview").val($("#statusA").val());
						document.getElementById('viewForm').submit();
					}

</script>

