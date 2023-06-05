<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link href="js/tunneldesign/tunnelold.css" rel="Stylesheet"></link>
<!-- <link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css"> -->
<!-- <script src="js/Calender/jquery-ui.js"></script> -->
<script src="js/Calender/datePicketValidation.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Student Details</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Student Details</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="tunnel_design" id="updatediv">
							<div class="square tunnel_active">
								<h5 class="tunnel_text" id="tunnel_1">Personal Details</h5>
							</div>
							<div class="square">
								<a href="#" class="tunnel_text" id="tunnel_2">Graduation
									Details</a>
							</div>
							<div class="square">
								<a href="#" class="tunnel_text" id="tunnel_3"> Upload
									Document </a>
							</div>
							<div class="square">
								<a href="#" class="tunnel_text" id="tunnel_4"> Declaration </a>
							</div>
						</div>
					</div>

					<!-- input style start -->
					<div class="card-style mb-30">
						<!-- row -->
						<div class="row">
							
							<div class="col-lg-12 col-md-12 col-sm-12  custom-d-none">
								<ul class="custom-uniqueid">
							<li><p class="custom-id custom-active-badge">
								<span class="custom-id-title">Ayush ID:</span><span class="custom-id-value" id="ayush_id"></span>
							</p></li>
						</ul>
						</div>
							
							

							<div class="col-lg-12 col-md-12 col-sm-12">
								<form:form name="personal_details_PG" id="personal_details_PG"
									action="personal_details_PG_Action" method='POST'
									commandName="personal_details_PG_CMD"
									enctype="multipart/form-data">
									<section class="detail-block">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<div class="custom-data-value custom-title custom-title-bg">
													<h5 class="custom-title-tag">Personal Details</h5>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label>First Name <span class="mandatory">*</span></label>
													<input type="text" id="first_name" name="first_name"
														class="form-control" placeholder="Enter First Name"
														maxlength="30" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label>Middle Name </label> <input type="text"
														id="middel_name" name="middel_name"
														placeholder="Enter Middle Name" maxlength="30"
														autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label>Surname <span class="mandatory">*</span></label> <input
														type="text" id="surname" name="surname" maxlength="30"
														placeholder="Enter Surname" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label>Father's Name <span class="mandatory">*</span></label>
													<input type="text" id="father_name" name="father_name"
														maxlength="30" placeholder="Enter Father's Name"
														autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label>Mother's Name <span class="mandatory">*</span></label>
													<input type="text" id="mother_name" name="mother_name"
														maxlength="30" placeholder="Enter Mother's Name"
														autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">Gender <span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="gender" id="gender"
															class=" form-control form-control-lg">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getgenderList}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label>Date Of Birth <span class="mandatory">*</span></label>
													<input type="text" name="date_of_birth" id="date_of_birth"
														maxlength="10" class="form-control-sm form-control"
														aria-required="true" autocomplete="off" value="DD/MM/YYYY" />
													<!-- Hidden Start -->
													<input type="hidden" id="yrr" name="yrr" value="">
													<!-- Hidden End -->
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Mobile Number<span
														class="mandatory">*</span></label> <input type="text" id="mob_no"
														name="mob_no" maxlength="10" placeholder="Enter Mobile Number" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Email Id <span
														class="mandatory">*</span>
													</label> <input type="text" id="email" name="email" maxlength="30"
														autocomplete="off" readonly="readonly">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">Category <span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="category" id="category" class="form-control">
															<option value="0">-- Select --</option>
															<c:forEach var="item" items="${getcategorylist}"
																varStatus="num">
																<option value="${item.id}" name="${item.category}">${item.category}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">Religion <span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="religion" id="religion">
															<option value="0" selected="selected">-- Select
																--</option>
															<c:forEach var="item" items="${getreligionListdata}"
																varStatus="num">
																<option value="${item.id}" name="${item.religion}">${item.religion}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">Marital Status <span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="marital_status" id="marital_status"
															class=" form-control form-control-lg">
															<option value="0" selected="selected">-- Select
																--</option>
															<c:forEach var="item" items="${getmsList}"
																varStatus="num">
																<option value="${item.id}" name="${item.marital_status}">${item.marital_status}</option>
															</c:forEach>
														</select>
													</div>
													<!-- Hidden Start -->
													<input type="hidden" id="maritalstatus_txt"
														name="maritalstatus_txt" value="${maritalstatus_txt}">
													<!-- Hidden End -->

												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">Nationality <span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="nationality" id="nationality">
															<option value="0">-- Select --</option>
															<c:forEach var="item" items="${getMedCountryName}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Aadhaar No. <span
														class="mandatory">*</span></label>
													<div class="form-group stepform-groupadd">
														<input id="aadhar_no1" name="aadhar_no1" type="text"
															autocomplete="off"
															placeholder="Enter First Four Digit Of Aadhaar No"
															minlength="4" maxlength="4"> <input
															id="aadhar_no2" name="aadhar_no2" type="text"
															autocomplete="off"
															placeholder="Enter Second Four Digit Of Aadhaar No"
															minlength="4" maxlength="4"> <input
															id="aadhar_no3" name="aadhar_no3" type="text"
															autocomplete="off"
															placeholder="Enter Last Four Digit Of Aadhaar No"
															minlength="4" maxlength="4">
													</div>

												</div>
											</div>
											
											
												<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Quota <span
														class="mandatory">*</span></label>
													<div class="select-position d-none">
														<select id="quota_id">
															<option value="0">-- Select --</option>
															<c:forEach var="item" items="${getQuotaList}"
																varStatus="num">
																<option value="${item.id}" name="${item.quota}">${item.quota}</option>
															</c:forEach>
														</select>
													</div>
													 <input type="text" readonly="readonly" id="quota_input"
														 maxlength="50" value="" autocomplete="off">
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Counselling Authority <span
														class="mandatory">*</span></label>
													<div class="select-position d-none">
														<select name="counselling_authority"
															id="counselling_authority">
															<option value="0">-- Select --</option>
															<c:forEach var="item" items="${getCounselingAuthoList}"
																varStatus="num">
																<option value="${item.id}"
																	name="${item.counseling_authority}">${item.counseling_authority}</option>
															</c:forEach>
														</select>
													</div>
													 <input type="text" readonly="readonly" id="counselling_input"
														 maxlength="50" value="" autocomplete="off">
												</div>
											</div>
											
										</div>
									</section>
									<section class="detail-block">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<div class="custom-data-value custom-title custom-title-bg">
													<h5 class="custom-title-tag">Permanent Address</h5>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">House No/Name <span
														class="mandatory">*</span></label> <input type="text"
														id="permanent_house_no" name="permanent_house_no"
														maxlength="30" autocomplete="off"
														placeholder="Enter House No/Name">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Permanent Address Line 1<span
														class="mandatory">*</span></label> <input type="text"
														id="permanent_add_line1" name="permanent_add_line1"
														maxlength="50" autocomplete="off"
														placeholder="Enter Permanent Address Line 1">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Permanent Address Line 2<span
														class="mandatory">*</span></label> <input type="text"
														id="permanent_add_line2" name="permanent_add_line2"
														maxlength="50" autocomplete="off"
														placeholder="Enter Permanent Address Line 2">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">State<span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="permanent_state" id="permanent_state"
															class=" form-control form-control-lg">
															<option value="0" selected="selected">-- Select --</option>

															<c:forEach var="item" items="${getMedStateName}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
													<!-- Hidden Start -->
													<input type="hidden" id="permanent_state_txt"
														name="permanent_state_txt" value="${permanent_state_txt}"
														autocomplete="off" />
													<!-- Hidden End -->
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">District <span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="permanent_district" id="permanent_district"
															class=" form-control form-control-lg">
															<option value="0" selected="selected">-- Select --</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Village/City<span
														class="mandatory">*</span></label> <input type="text"
														id="permanent_village" name="permanent_village"
														maxlength="30" autocomplete="off"
														placeholder="Enter Village/City">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Landmark<span
														class="mandatory">*</span></label> <input type="text"
														id="permanent_lendmark" name="permanent_lendmark"
														maxlength="30" autocomplete="off"
														placeholder="Enter Landmark">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1 input-sm">
													<label for="text-input">Pincode<span
														class="mandatory">*</span></label> <input type="text"
														id="permanent_pincode" name="permanent_pincode"
														maxlength="6" autocomplete="off"
														placeholder="Enter Pincode">
												</div>
											</div>
										</div>
									</section>
									<section class="detail-block">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<div class="custom-choose-one">
													<div class="input-style-form-check">
														<div class="form-check checkbox-style">
															<input type="checkbox" class="form-check-input"
																id="check_address" name="check_address"> <label
																class="form-check-label" for="check_address">Same
																as Permanent Address</label>
														</div>
													</div>
												</div>
											</div>
										</div>
									</section>
									<section class="detail-block">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<div class="custom-data-value custom-title custom-title-bg">
													<h5 class="custom-title-tag">Present Address</h5>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">House No/Name <span
														class="mandatory">*</span></label> <input type="text"
														id="present_house_no" name="present_house_no"
														maxlength="30" autocomplete="off"
														placeholder="Enter House No/Name">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Present Address Line 1<span
														class="mandatory">*</span></label> <input type="text"
														id="present_add_line1" name="present_add_line1"
														maxlength="50" autocomplete="off"
														placeholder="Enter present Address Line 1">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Present Address Line 2<span
														class="mandatory">*</span></label> <input type="text"
														id="present_add_line2" name="present_add_line2"
														maxlength="30" autocomplete="off"
														placeholder="Enter present Address Line 2">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">State<span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="present_state" id="present_state"
															class=" form-control form-control-lg">
															<option value="0" selected="selected">-- Select
																--</option>

															<c:forEach var="item" items="${getMedStateName}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">District <span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="present_district" id="present_district"
															class=" form-control form-control-lg">
															<option value="0" selected="selected">-- Select
																--</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Village/City <span
														class="mandatory">*</span></label> <input type="text"
														id="present_village" name="present_village" maxlength="30"
														autocomplete="off" placeholder="Enter Village/City">
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Landmark <span
														class="mandatory">*</span></label> <input type="text"
														id="present_lendmark" name="present_lendmark"
														maxlength="30" autocomplete="off"
														placeholder="Enter Landmark">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1 input-sm">
													<label for="text-input">Pincode<span
														class="mandatory">*</span></label> <input type="text"
														id="present_pincode" name="present_pincode" maxlength="6"
														autocomplete="off" placeholder="Enter Pincode">
												</div>
											</div>
										</div>
									</section>
									<section class="detail-block">
										<div class="custom-choose-one">
											<div class="row">
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-form-check">
														<div class="form-check radio-style">
															<input type="radio" class="form-check-input"
																id="check_address1" name="check_add"> <label
																class="form-check-label" for="check_address1">Same
																as Permanent Address</label>
														</div>
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-form-check">
														<div class="form-check radio-style">
															<input type="radio" class="form-check-input"
																id="check_address2" name="check_add"> <label
																class="form-check-label" for="check_address2">Same
																as Present Address</label>
														</div>
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-form-check">
														<div class="form-check radio-style">
															<input type="radio" class="form-check-input"
																id="check_address3" name="check_add" checked="checked">
															<label class="form-check-label" for="check_address3">Other</label>
														</div>
													</div>
												</div>
												<div class="col-lg-12 col-md-12 col-sm-12">
													<!-- Hidden Start -->
													<input class="form-check-radio" type="hidden"
														id="chk_add_val" name="chk_add_val" value="">
													<!-- Hidden End -->
												</div>
											</div>
										</div>
									</section>
									<section class="detail-block">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<div class="custom-data-value custom-title custom-title-bg">
													<h5 class="custom-title-tag">Corresponding Address</h5>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">House No/Name<span
														class="mandatory">*</span>
													</label> <input type="text" id="corre_house_no"
														name="corre_house_no" maxlength="30" autocomplete="off"
														placeholder="Enter House No/Name">
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Corresponding Address Line 1<span
														class="mandatory">*</span></label> <input type="text"
														id="corre_add_line1" name="corre_add_line1" maxlength="50"
														autocomplete="off"
														placeholder="Enter corresponding Address Line 1">
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Corresponding Address Line 2<span
														class="mandatory">*</span></label> <input type="text"
														id="corre_add_line2" name="corre_add_line2" maxlength="50"
														autocomplete="off"
														placeholder="Enter corresponding Address Line 2">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">State<span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="corre_state" id="corre_state"
															class=" form-control form-control-lg">
															<option value="0" selected="selected">-- Select
																--</option>
															<c:forEach var="item" items="${getMedStateName}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">District <span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="corre_district" id="corre_district"
															class=" form-control form-control-lg">
															<option value="0" selected="selected">-- Select
																--</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Village/City <span
														class="mandatory">*</span></label> <input type="text"
														id="corre_village" name="corre_village" maxlength="30"
														autocomplete="off" placeholder="Enter Village/City">
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Landmark <span
														class="mandatory">*</span></label> <input type="text"
														id="corre_lendmark" name="corre_lendmark" maxlength="30"
														autocomplete="off" placeholder="Enter Landmark">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1 input-sm">
													<label for="text-input">Pincode<span
														class="mandatory">*</span></label> <input type="text"
														id="corre_pincode" name="corre_pincode" maxlength="6"
														autocomplete="off" placeholder="Enter Pincode">
												</div>
											</div>
										</div>
									</section>
									<section class="detail-block" id="aiapget_details" >
										<div class="row">
											<div class="col-lg-12 col-sm-12">
												<div class="custom-data-value custom-title custom-title-bg">
													<h5 class="custom-title-tag">AIAPGET Details</h5>
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">AIAPGET Roll No<span
														class="mandatory">*</span></label> <input type="text"
														id="neet_roll_no" name="neet_roll_no" maxlength="10"
														autocomplete="off" placeholder="Enter AIAPGET Roll No">
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">AIAPGET Application No<span
														class="mandatory">*</span></label> <input type="text"
														id="neet_application_no" name="neet_application_no"
														maxlength="12" autocomplete="off"
														placeholder="Enter AIAPGET Application No">
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">AIAPGET All India Rank<span
														class="mandatory">*</span></label> <input type="text"
														id="neet_rank" name="neet_rank" maxlength="7"
														class="form-control" autocomplete="off"
														placeholder="Enter AIAPGET All India Rank">
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">AIAPGET Marks<span
														class="mandatory">*</span></label> <input type="text"
														id="neet_marks" name="neet_marks" maxlength="3"
														autocomplete="off" placeholder="Enter AIAPGET Marks">
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">AIAPGET Percentile<span
														class="mandatory">*</span></label> <input type="text"
														id="neet_percentile" name="neet_percentile" maxlength="5"
														autocomplete="off" placeholder="Enter AIAPGET Percentile">
												</div>
											</div>
										
										</div>
									</section>
									
									
									
									<!-- Bottom Button Start -->
									<div class="btn-bottom">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
<!-- 												<ul class="buttons-group mainbtn"> -->
<!-- 													<li><input type="submit" id="save_btn" -->
<!-- 														class="main-btn info-btn btn-hover" value="Save"><a -->
<!-- 														id="update_btn" -->
<!-- 														class="main-btn deactive-btn btn-hover btnupda" -->
<!-- 														value="Update">Update</a></li> -->
<!-- 													<li><a id="aIdNext" -->
<!-- 														class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left btnnext">Next<i -->
<!-- 															class="lni lni-chevron-right"></i></a></li> -->
<!-- 												</ul> -->
												
												<ul class="buttons-group mainbtn">
													<li><input type="submit" id="save_btn"
														class="main-btn deactive-btn btn-hover btnupda"
														value="Save &amp; Next"> <a id="update_btn"
														class="main-btn deactive-btn btn-hover btnupda"
														value="Update &amp; Next">Update & Next</a></li>
												</ul>
												
												
											</div>
										</div>
									</div>
									<!-- Bottom Button End -->
									<!-- Hidden Start -->
									<input type="hidden" name="e_id" id="e_id" value="0" />
									<input type="hidden" name="hidden_email" id="hidden_email" value="" />
									
									<!-- Hidden End -->
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>


<c:url value="Graduation_Det_PG_Url" var="mainFormUrl1" />
<form:form action="${mainFormUrl1}" method="GET" id="mainForm11"
	name="mainForm11">
	<input type="hidden" name="eid" id="eid" value="0" />
	<!-- <hiiden? -->
</form:form>

<c:url value="Total_Exp_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm4"
	name="applicationUrlForm4" modelAttribute="exper_hid">
	<input type="hidden" name="tp_eid" id="tp_eid" value="0" />
</form:form>

<c:url value="doc_uploadUrl" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm5"
	name="applicationUrlForm5" modelAttribute="exper_hid">
	<input type="hidden" name="doc_eid" id="doc_eid" value="0" />
</form:form>

<c:url value="Reshuffling_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm6"
	name="applicationUrlForm6" modelAttribute="exper_hid">
	<input type="hidden" name="ch_eid" id="ch_eid" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(
			function() {

				datepicketDate('date_of_birth');

				if ('${pddata}' != "[]") {
					$("#first_name").val('${pddata[0][0]}');
					$("#father_name").val('${pddata[0][1]}');
					$("#mother_name").val('${pddata[0][2]}');
					// 		$("#date_of_birth").val('${pddata[0][3]}');
					// 		var date_of_birth = '${pddata[0][3]}'.substring(0,10);
					// 		$("#date_of_birth").val(date_of_birth);
					var date_of_birth = '${pddata[0][3]}';
					var dob = date_of_birth.substring(0, 10);
					var dob_y = dob.substring(0, 4);
					var dob_m = dob.substring(5, 7);
					var dob_d = dob.substring(8, 10);
					var dob_t = dob_d + "/" + dob_m + "/" + dob_y;
					$("#date_of_birth").val(dob_t);
					calculate_age(dob_t);

					// alert('${pddata[0][4]}');
					$("#gender").val('${pddata[0][4]}');
					$("#gender").change();

					$("#mob_no").val('${pddata[0][5]}');
					$("#email").val('${pddata[0][6]}');
					$("#hidden_email").val('${pddata[0][6]}');
					$("#category").val('${pddata[0][7]}');

				
					$("#religion").val('${pddata[0][8]}');
					$("#marital_status").val('${pddata[0][9]}');
					$("#marital_status").change();
					$("#nationality").val('${pddata[0][10]}');
					$("#nationality").change();
					// 	getState(); 
					// 	$("#state_id").val('${pddata[0][11]}');
					// 	$("#state_id").change();
					// 	getDistrict();
					// 	$.ajaxSetup({
					// 		async : false
					// 	});
					// 	$("#district_id").val('${pddata[0][12]}');
// 					$("#village").val('${pddata[0][13]}');
					var adhar_card2 = '${pddata[0][14]}';
					var a1 = adhar_card2.substring(0, 4);
					var a2 = adhar_card2.substring(4, 8);
					var a3 = adhar_card2.substring(8, 12);
					$("#aadhar_no1").val(a1);
					$("#aadhar_no2").val(a2);
					$("#aadhar_no3").val(a3);

// 					$("#aadhar_no1").attr('readonly', true);
// 					$("#aadhar_no2").attr('readonly', true);
// 					$("#aadhar_no3").attr('readonly', true);
// 					debugger;
					$("#permanent_house_no").val('${pddata[0][15]}');
					$("#permanent_village").val('${pddata[0][16]}');
					$("#permanent_add_line1").val('${pddata[0][17]}');
					$("#permanent_add_line2").val('${pddata[0][18]}');
					// 	$("#permanent_policestation").val('${pddata[0][19]}');
					$("#permanent_state").val('${pddata[0][20]}');
					$('#permanent_state').change();

					getpermDistrict();
					$("#permanent_district").val('${pddata[0][21]}');
					$('#permanent_district').change();

					$("#permanent_pincode").val('${pddata[0][22]}');
					$("#permanent_lendmark").val('${pddata[0][23]}');

					if (('${pddata[0][15]}' == '${pddata[0][24]}')
							&& ('${pddata[0][16]}' == '${pddata[0][25]}')
							&& ('${pddata[0][17]}' == '${pddata[0][26]}')
							&& ('${pddata[0][18]}' == '${pddata[0][27]}')
							&& ('${pddata[0][20]}' == '${pddata[0][30]}')
							&& ('${pddata[0][21]}' == '${pddata[0][29]}')
							&& ('${pddata[0][22]}' == '${pddata[0][31]}')
							&& ('${pddata[0][23]}' == '${pddata[0][32]}')) {

						$("#check_address").prop("checked", true);

						$("#present_house_no").val('${pddata[0][24]}');
						$("#present_house_no").attr('readonly', true);
						$("#present_village").val('${pddata[0][25]}');
						$("#present_village").attr('readonly', true);
						$("#present_add_line1").val('${pddata[0][26]}');
						$("#present_add_line1").attr('readonly', true);
						$("#present_add_line2").val('${pddata[0][27]}');
						$("#present_add_line2").attr('readonly', true);
						$("#present_state").val('${pddata[0][30]}');
						$('#present_state').change();
						getpresDistrict();
						$("#present_state").attr('tabindex', '-1');
						$("#present_state").attr('readonly', true);
						$("#present_district").val('${pddata[0][29]}');

						$('#present_district').change();
						$("#present_district").attr('readonly', true);
						$("#present_district").attr('tabindex', '-1');
						$("#present_pincode").val('${pddata[0][31]}');
						$("#present_pincode").attr('readonly', true);
						$("#present_lendmark").val('${pddata[0][32]}');
						$("#present_lendmark").attr('readonly', true);

					} else {
						$("#present_house_no").val('${pddata[0][24]}');
						$("#present_village").val('${pddata[0][25]}');
						$("#present_add_line1").val('${pddata[0][26]}');
						$("#present_add_line2").val('${pddata[0][27]}');
						$("#present_state").val('${pddata[0][30]}');
						$('#present_state').change();
						getpresDistrict();

						$("#present_district").val('${pddata[0][29]}');
						$('#present_district').change();
						$("#present_pincode").val('${pddata[0][31]}');
						$("#present_lendmark").val('${pddata[0][32]}');

					}

					if (('${pddata[0][15]}' == '${pddata[0][45]}')
							&& ('${pddata[0][16]}' == '${pddata[0][50]}')
							&& ('${pddata[0][17]}' == '${pddata[0][46]}')
							&& ('${pddata[0][18]}' == '${pddata[0][47]}')
							&& ('${pddata[0][20]}' == '${pddata[0][48]}')
							&& ('${pddata[0][21]}' == '${pddata[0][49]}')
							&& ('${pddata[0][22]}' == '${pddata[0][51]}')
							&& ('${pddata[0][23]}' == '${pddata[0][52]}')) {

						$("#check_address1").prop("checked", true);

						$("#corre_house_no").val('${pddata[0][45]}');
						$("#corre_house_no").attr('readonly', true);
						$("#corre_village").val('${pddata[0][50]}');
						$("#corre_village").attr('readonly', true);
						$("#corre_add_line1").val('${pddata[0][46]}');
						$("#corre_add_line1").attr('readonly', true);
						$("#corre_add_line2").val('${pddata[0][47]}');
						$("#corre_add_line2").attr('readonly', true);
						$("#corre_state").val('${pddata[0][48]}');
						$('#corre_state').change();
						getCorsDistrict();
						$("#corre_state").attr('readonly', true);
						$("#corre_state").attr('tabindex', '-1');
						$("#corre_district").val('${pddata[0][49]}');
						$('#corre_district').change();
						$("#corre_district").attr('readonly', true);
						$("#corre_district").attr('tabindex', '-1');
						$("#corre_pincode").val('${pddata[0][51]}');
						$("#corre_pincode").attr('readonly', true);
						$("#corre_lendmark").val('${pddata[0][52]}');
						$("#corre_lendmark").attr('readonly', true);

					}

					else if (('${pddata[0][24]}' == '${pddata[0][45]}')
							&& ('${pddata[0][25]}' == '${pddata[0][50]}')
							&& ('${pddata[0][26]}' == '${pddata[0][46]}')
							&& ('${pddata[0][27]}' == '${pddata[0][47]}')
							&& ('${pddata[0][29]}' == '${pddata[0][49]}')
							&& ('${pddata[0][30]}' == '${pddata[0][48]}')
							&& ('${pddata[0][31]}' == '${pddata[0][51]}')
							&& ('${pddata[0][32]}' == '${pddata[0][52]}')) {

						$("#check_address2").prop("checked", true);

						$("#corre_house_no").val('${pddata[0][45]}');
						$("#corre_house_no").attr('readonly', true);
						$("#corre_village").val('${pddata[0][50]}');
						$("#corre_village").attr('readonly', true);
						$("#corre_add_line1").val('${pddata[0][46]}');
						$("#corre_add_line1").attr('readonly', true);
						$("#corre_add_line2").val('${pddata[0][47]}');
						$("#corre_add_line2").attr('readonly', true);
						$("#corre_state").val('${pddata[0][48]}');
						$('#corre_state').change();
						getCorsDistrict();
						$("#corre_state").attr('readonly', true);
						$("#corre_state").attr('tabindex', '-1');
						$("#corre_district").val('${pddata[0][49]}');
						$('#corre_district').change();
						$("#corre_district").attr('readonly', true);
						$("#corre_district").attr('tabindex', '-1');
						$("#corre_pincode").val('${pddata[0][51]}');
						$("#corre_pincode").attr('readonly', true);
						$("#corre_lendmark").val('${pddata[0][52]}');
						$("#corre_lendmark").attr('readonly', true);

					} else {

						$("#check_address3").prop("checked", true);

						$("#corre_house_no").val('${pddata[0][45]}');
						$("#corre_add_line1").val('${pddata[0][46]}');
						$("#corre_add_line2").val('${pddata[0][47]}');
						$("#corre_state").val('${pddata[0][48]}');
						$('#corre_state').change();
						getCorsDistrict();
						$("#corre_district").val('${pddata[0][49]}');
						$('#corre_district').change();
						$("#corre_village").val('${pddata[0][50]}');
						$("#corre_pincode").val('${pddata[0][51]}');
						$("#corre_lendmark").val('${pddata[0][52]}');
					}

					//corresponding add
					$("#e_id").val('${pddata[0][33]}');
// 					$("#cand_prifix").val('${pddata[0][34]}');
					$("#middel_name").val('${pddata[0][40]}');
					$("#surname").val('${pddata[0][41]}');
					
					// 	$("#father_title").val('1');
					// 	$("#mother_title").val('1');

// 					$("#neet_rank").val('${pddata[0][37]}');
// 					$("#neet_marks").val('${pddata[0][38]}');
// 					$("#neet_percentile").val('${pddata[0][39]}');

// 					$("#neet_roll_no").val('${pddata[0][43]}');
// 					$("#neet_application_no").val('${pddata[0][44]}');

					// 							$('#save_btn').hide();
					if (!$("#save_btn").hasClass('d-none')) {
						$("#save_btn").addClass("d-none")
					}

					// 							$('#update_btn').show();
					if ($("#update_btn").hasClass("d-none")) {
						$("#update_btn").removeClass("d-none")
					}
					getquatainfo();
					
					get_p_id_info();

				} else {
					// 							$('#save_btn').show();

					if ($("#save_btn").hasClass("d-none")) {
						$("#save_btn").removeClass("d-none")
					}

					// 							$('#update_btn').hide();

					if (!$("#update_btn").hasClass('d-none')) {
						$("#update_btn").addClass("d-none")
					}

					getbesicinfo();
				}
				// 						$('#updatediv').show();

				if ($("#updatediv").hasClass("d-none")) {
					$("#updatediv").removeClass("d-none")
				}

			});
	
	
	function getquatainfo() {
		
		var userid = "${userid}";
		$.post('getBesicdetails_PG_ctrl?' + key + "=" + value, {
			userid : userid
		}, function(j) {
			
			$("#neet_roll_no").val(j[0][5]);
			$("#neet_application_no").val(j[0][6]);
			$("#neet_marks").val(j[0][7]);
			$("#neet_percentile").val(j[0][8]);
			$("#neet_rank").val(j[0][9]);
			$("#neet_roll_no").attr('readonly',true);
			$("#neet_roll_no").attr('tabindex','-1');	
			$("#neet_application_no").attr('readonly',true);
			$("#neet_application_no").attr('tabindex','-1');	
			$("#neet_marks").attr('readonly',true);
			$("#neet_marks").attr('tabindex','-1');	
			$("#neet_percentile").attr('readonly',true);
			$("#neet_percentile").attr('tabindex','-1');
			$("#neet_rank").attr('readonly',true);
			$("#neet_rank").attr('tabindex','-1');
			
				$("#quota_id").val(j[0][13]);
				$("#quota_id").attr('readonly',true);
				$("#quota_id").attr('tabindex','-1');
				$("#quota_input").val($("#quota_id option:selected").text());
				
				$("#counselling_authority").val(j[0][14]);
				$("#counselling_authority").attr('readonly',true);
				$("#counselling_authority").attr('tabindex','-1');	
				$("#counselling_input").val($("#counselling_authority option:selected").text());
				
				if (j[0][16] == 2) {
//			$('#aiapget_details').hide();
						if (!$("#aiapget_details").hasClass('d-none')) {
							$("#aiapget_details").addClass("d-none")
						}
					}
	       })
		}

	function get_p_id_info() {
		$.ajaxSetup({
			async : false
		});

		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		var userid = "${userid}";
		$.post('get_p_id_info_PG_ctrl?' + key + "=" + value, {
			userid : userid
		}, function(j) {
			//alert(j)
			$("#e_id").val(j[0][0]);
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});

	}

	function getbesicinfo() {
		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		var userid = "${userid}";
		$.post('getBesicdetails_PG_ctrl?' + key + "=" + value, {
			userid : userid
		}, function(j) {
			$("#mob_no").val(j[0][0]);
			$("#email").val(j[0][1]);
			$("#hidden_email").val(j[0][1]);
			$("#first_name").val(j[0][2]);
			var ad = j[0][3];
			if (ad != null && ad != "" && ad.length == "12") {
				$("#aadhar_no1").val(ad.substring(0, 4));
				$("#aadhar_no2").val(ad.substring(4, 8));
				$("#aadhar_no3").val(ad.substring(8));
// 				$("#aadhar_no1").attr('readonly', true);
// 				$("#aadhar_no2").attr('readonly', true);
// 				$("#aadhar_no3").attr('readonly', true);
			}

			$("#date_of_birth").val(j[0][4]);
			calculate_age(j[0][4]);
			
			$("#neet_roll_no").val(j[0][5]);
			$("#neet_application_no").val(j[0][6]);
			$("#neet_marks").val(j[0][7]);
			$("#neet_percentile").val(j[0][8]);
			$("#neet_rank").val(j[0][9]);
			$("#neet_roll_no").attr('readonly',true);
			$("#neet_roll_no").attr('tabindex','-1');	
			$("#neet_application_no").attr('readonly',true);
			$("#neet_application_no").attr('tabindex','-1');	
			$("#neet_marks").attr('readonly',true);
			$("#neet_marks").attr('tabindex','-1');	
			$("#neet_percentile").attr('readonly',true);
			$("#neet_percentile").attr('tabindex','-1');
			$("#neet_rank").attr('readonly',true);
			$("#neet_rank").attr('tabindex','-1');
		
	//27-01-23
				$("#father_name").val(j[0][10]);
				$("#mother_name").val(j[0][11]);
				$("#surname").val(j[0][12]);
				$("#quota_id").val(j[0][13]);
				$("#quota_id").attr('readonly',true);
				$("#quota_id").attr('tabindex','-1');
				$("#quota_input").val($("#quota_id option:selected").text());
				
				
				$("#counselling_authority").val(j[0][14]);
				$("#counselling_authority").attr('readonly',true);
				$("#counselling_authority").attr('tabindex','-1');	
				$("#counselling_input").val($("#counselling_authority option:selected").text());
				$("#category").val(j[0][15]);
				$("#category").attr('readonly',true);
				$("#category").attr('tabindex','-1');	
				
// 				intake_id
// 				$("#intake_id").val(j[0][16]);
				if (j[0][16] == 2) {
// 							$('#aiapget_details').hide();
					if (!$("#aiapget_details").hasClass('d-none')) {
						$("#aiapget_details").addClass("d-none")
					}
				}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}

	function saveData() {
		if ($("#first_name").val() == "") {
			alert("Please Enter First Name");
			$("#first_name").focus();
			return false;
		}
		if ($("#middel_name").val() == "") {
			$("#middel_name").val("N/A");
		}
		if ($("#surname").val() == "") {
			alert("Please Enter Surname");
			$("#surname").focus();
			return false;
		}
		if ($("#father_name").val() == "") {
			alert("Please Enter Father Name");
			$("#father_name").focus();
			return false;
		}
		if ($("#mother_name").val() == "") {
			alert("Please Enter Mother Name");
			$("#mother_name").focus();
			return false;
		}
		if ($("#gender").val() == "0") {
			alert("Please Select Gender");
			$("#gender").focus();
			return false;
		}
		if ($("#date_of_birth").val() == ""
				|| $("#date_of_birth").val() == "DD/MM/YYYY") {
			alert("Please Select Date of Birth");
			$("#date_of_birth").focus();
			return false;
		}
		var yrr = $("#yrr").val();
		if (yrr < 17 || yrr == "" || yrr == "0") {
			alert("Age Should Be Greater Than 17 Years")
			$("#date_of_birth").focus();
			return false;
		}
		var mob = $("#mob_no");
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
		if ($("#email").val() == "") {
			alert("Please Enter Email Id");
			$("#email").focus();
			return false;
		}
		if ($("#category").val() == "0") {
			alert("Please Select Category");
			$("#category").focus();
			return false;
		}
		if ($("#religion").val() == "0") {
			alert("Please Select Religion");
			$("#religion").focus();
			return false;
		}
		if ($("#marital_status").val() == "0") {
			alert("Please Select Marital Status");
			$("#marital_status").focus();
			return false;
		}
		if ($("#nationality").val() == "0") {
			alert("Please Select Nationality");
			$("#nationality").focus();
			return false;
		}
		var an1 = $("#aadhar_no1").val();
		if (an1 == "") {
			alert("Enter First Four Digit Of Aadhaar No");
			$("#aadhar_no1").focus();
			return false;
		}
		if (parseInt(an1) <= 0) {
			alert("Please Enter Valid First Four Digit of Aadhaar No");
			$("#aadhar_no1").focus();
			return false;
		}
		var minLength = 4;
		var charLength = an1.length;
		if (charLength < minLength) {
			alert("Please Enter Valid First Four Digit of Aadhaar No");
			$("input#aadhar_no1").focus();
			return false;
		}
		var an2 = $("#aadhar_no2").val();
		if (an2 == "") {
			alert("Enter Second Four Digit Of Aadhaar No");
			$("#aadhar_no2").focus();
			return false;
		}
		if (parseInt(an2) <= 0) {
			alert("Please Enter Valid Second Four Digit Of Aadhaar No");
			$("#aadhar_no2").focus();
			return false;
		}
		var minLength = 4;
		var charLength = an2.length;
		if (charLength < minLength) {
			alert("Please Enter Valid Second Four Digit Of Aadhaar No");
			$("input#aadhar_no2").focus();
			return false;
		}
		var an3 = $("#aadhar_no3").val();
		if (an3 == "") {
			alert("Enter Last Four Digit Of Aadhaar No");
			$("#aadhar_no3").focus();
			return false;
		}
		if (parseInt(an3) <= 0) {
			alert("Please Enter Valid Last Four Digit Of Aadhaar No");
			$("#aadhar_no3").focus();
			return false;
		}
		var minLength = 4;
		var charLength = an3.length;
		if (charLength < minLength) {
			alert("Please Enter Valid Last Four Digit Of Aadhaar No");
			$("input#aadhar_no3").focus();
			return false;
		}
		if ($("#permanent_house_no").val().trim() == "") {
			alert("Please Enter the Permanent House No/Name");
			$("#permanent_house_no").focus();
			return false;
		}
		if ($("#permanent_add_line1").val().trim() == "") {
			alert("Please Enter Permanent Address Line 1");
			$("#permanent_add_line1").focus();
			return false;
		}
		if ($("#permanent_add_line2").val().trim() == "") {
			alert("Please Enter Permanent Address Line 2");
			$("#permanent_add_line2").focus();
			return false;
		}
		if ($("#permanent_state").val().trim() == "0") {
			alert("Please Select Permanent State");
			$("#permanent_state").focus();
			return false;
		}
		if ($("#permanent_district").val().trim() == "0") {
			alert("Please Select Permanent District");
			$("#permanent_district").focus();
			return false;
		}
		if ($("#permanent_village").val().trim() == "") {
			alert("Please Enter Permanent Village/City");
			$("#permanent_village").focus();
			return false;
		}
		if ($("#permanent_lendmark").val().trim() == "") {
			alert("Please Enter Permanent Landmark");
			$("#permanent_lendmark").focus();
			return false;
		}
		var pin_perm = $("#permanent_pincode").val().trim();
		if (pin_perm == "") {
			alert("Please Enter the Permanent Pincode");
			$("#permanent_pincode").focus();
			return false;
		}
		if (parseInt(pin_perm) <= 0) {
			alert("Please Enter Valid Permanent Pincode");
			$("#permanent_pincode").focus();
			return false;
		}
		var minLength = 6;
		var charLength = pin_perm.length;
		if (charLength < minLength) {
			alert("Permanent Pincode Should Contain Minimum 6 Digit");
			$("input#permanent_pincode").focus();
			return false;
		}
		var maxLength = 6;
		var charLength = pin_perm.length;
		if (charLength > maxLength) {
			alert("Permanent Pincode Should Contain Maximum 6 Digit");
			$("input#permanent_pincode").focus();
			return false;
		}
		if (document.getElementById('check_address').checked == false) {
			if ($("#present_house_no").val().trim() == "") {
				alert("Please Enter Present House No/Name");
				$("#present_house_no").focus();
				return false;
			}
			if ($("#present_add_line1").val().trim() == "") {
				alert("Please Enter Present Address Line 1");
				$("#present_add_line1").focus();
				return false;
			}
			if ($("#present_add_line2").val().trim() == "") {
				alert("Please Enter Present Address Line 2");
				$("#present_add_line2").focus();
				return false;
			}
			if ($("#present_state").val().trim() == "0") {
				alert("Please Select Present State");
				$("#present_state").focus();
				return false;
			}
			if ($("#present_district").val().trim() == "0") {
				alert("Please Select Present District");
				$("#present_district").focus();
				return false;
			}
			if ($("#present_village").val().trim() == "") {
				alert("Please Enter Present Village/City");
				$("#present_village").focus();
				return false;
			}
			if ($("#present_lendmark").val().trim() == "") {
				alert("Please Enter Present Landmark");
				$("#present_lendmark").focus();
				return false;
			}
			if ($("#present_pincode").val().trim() == "") {
				alert("Please Enter Present Pincode");
				$("#present_pincode").focus();
				return false;
			}
			var pin_pres = $("#present_pincode").val().trim();
			if (pin_pres == "") {
				alert("Please Enter Present Pincode");
				$("#present_pincode").focus();
				return false;
			}
			if (parseInt(pin_pres) <= 0) {
				alert("Please Enter Valid Present Pincode");
				$("#present_pincode").focus();
				return false;
			}
			var minLength = 6;
			var charLength = pin_pres.length;
			if (charLength < minLength) {
				alert("Present Pincode Should Contain Minimum 6 Digit");
				$("input#present_pincode").focus();
				return false;
			}
			var maxLength = 6;
			var charLength = pin_pres.length;
			if (charLength > maxLength) {
				alert("Present Pincode Should Contain Maximum 6 Digit");
				$("input#present_pincode").focus();
				return false;
			}
		}

		//  		var redval = 	$('input[name="check_add"]:checked').val();
		// 		 if(redval == undefined || redval == 'undefined'){
		// 			 alert("Please Select the Radio Button");
		// 			 return false;
		// 		}	

		if (document.getElementById('check_address3').checked == true) {
			if ($("#corre_house_no").val().trim() == "") {
				alert("Please Enter Corresponding House No/Name");
				$("#corre_house_no").focus();
				return false;
			}
			if ($("#corre_add_line1").val().trim() == "") {
				alert("Please Enter Corresponding Address Line 1");
				$("#corre_add_line1").focus();
				return false;
			}
			if ($("#corre_add_line2").val().trim() == "") {
				alert("Please Enter Corresponding Address Line 2");
				$("#corre_add_line2").focus();
				return false;
			}
			if ($("#corre_state").val() == "0") {
				alert("Please Select Corresponding State");
				$("#corre_state").focus();
				return false;
			}
			if ($("#corre_district").val() == "0") {
				alert("Please Select Corresponding District");
				$("#corre_district").focus();
				return false;
			}
			if ($("#corre_village").val().trim() == "") {
				alert("Please Enter Corresponding Village/City");
				$("#corre_village").focus();
				return false;
			}
			if ($("#corre_lendmark").val().trim() == "") {
				alert("Please Enter Corresponding Landmark");
				$("#corre_lendmark").focus();
				return false;
			}
			var pin_pre = $("#corre_pincode").val().trim();

			if (pin_pre == "") {
				alert("Please Enter Corresponding Pincode");
				$("#corre_pincode").focus();
				return false;
			}

			if (parseInt(pin_pre) <= 0) {
				alert("Please Enter Valid Corresponding Pincode");
				$("#corre_pincode").focus();
				return false;
			}

			var minLength = 6;
			var charLength = pin_pre.length;
			if (charLength < minLength) {
				alert("Corresponding Pincode Should Contain Minimum 6 Digit");
				$("input#corre_pincode").focus();
				return false;
			}
			var maxLength = 6;
			var charLength = pin_pre.length;
			if (charLength > maxLength) {
				alert("Corresponding Pincode Should Contain Maximum 6 Digit");
				$("input#corre_pincode").focus();
				return false;
			}

		
		}

		// 		var nrno = $("#neet_roll_no").val().trim();

		// 		if (nrno == "") {
		// 			alert("Please Enter NEET Roll No");
		// 			$("#neet_roll_no").focus();
		// 			return false;
		// 		}

		// 		if (parseInt(nrno) <= 0) {
		// 			alert("Please Enter Valid NEET Roll No");
		// 			$("#neet_roll_no").focus();
		// 			return false;
		// 		}

		//  		 var maxLength = 6;
		// 		 var charLength = nrno.length;

		// 	       if(charLength > maxLength){
		// 	       	alert("NEET Roll No Should Contain Maximum 6 Characters");
		// 				$("input#neet_roll_no").focus();
		// 				return false;
		// 	       } 

		// 	       var nano = $("#neet_application_no").val().trim();

		// 			if (nano == "") {
		// 				alert("Please Enter NEET Application No");
		// 				$("#neet_application_no").focus();
		// 				return false;
		// 			}

		// 			if (parseInt(nano) <= 0) {
		// 				alert("Please Enter Valid NEET Application No");
		// 				$("#neet_application_no").focus();
		// 				return false;
		// 			}

		// 	 		 var maxLength = 6;
		// 			 var charLength = nano.length;

		// 		       if(charLength > maxLength){
		// 		       	alert("NEET Application No Should Contain Maximum 6 Characters");
		// 					$("input#neet_application_no").focus();
		// 					return false;
		// 		       } 

		// 		var nr = $("#neet_rank").val().trim();

		// 			if (nr == "") {
		// 				alert("Please Enter NEET All India Rank");
		// 				$("#neet_rank").focus();
		// 				return false;
		// 			}

		// 			if (parseInt(nr) <= 0) {
		// 				alert("Please Enter Valid NEET All India Rank");
		// 				$("#neet_rank").focus();
		// 				return false;
		// 			}

		// 	 		 var maxLength = 6;
		// 			 var charLength = nr.length;

		// 		       if(charLength > maxLength){
		// 		       	alert("NEET All India Rank Should Contain Maximum 6 Characters");
		// 					$("input#neet_rank").focus();
		// 					return false;
		// 		       } 

		// 		     var nm = $("#neet_marks").val().trim();

		// 		if (nm == "") {
		// 			alert("Please Enter NEET Marks");
		// 			$("#neet_marks").focus();
		// 			return false;
		// 		}

		// 		if (nm <= 0) {
		// 			alert("Please Enter Valid NEET Marks");
		// 			$("#neet_marks").focus();
		// 			return false;
		// 		}

		// 		  var maxLength = 3;
		// 			 var charLength = nm.length;

		// 		       if(charLength > maxLength){
		// 		       	alert("NEET Marks Contain Maximum 3 Characters");
		// 					$("input#neet_marks").focus();
		// 					return false;
		// 		       } 

		// 		 var om =  $("#neet_percentile").val().trim()

		// 	       if (om == "") {				
		// 	   		alert("Please Enter NEET Percentile");
		// 	   		 $("#neet_percentile").focus();
		// 	   		return false;
		// 	   	}
		// 	       if (parseFloat(om) <= 0 ) {				
		// 	      		alert("Please Enter Valid NEET Percentile");
		// 	      		 $("#neet_percentile").focus();
		// 	      		return false;
		// 	      	}

		// 	       var maxLength = 5;
		// 			 var charLength = om.length;

		// 		       if(charLength > maxLength){
		// 		       	alert("NEET Percentile Contain Maximum 5 Characters");
		// 					$("input#neet_percentile").focus();
		// 					return false;
		// 		       } 

		// 		       if (parseFloat(om) > 100 ) {	
		// 		      		alert("Percentage Of Marks Can't Have More Than 100 Percent");
		// 		      		 $("#neet_percentile").focus();
		// 		      		return false;
		// 		      	}

		if ($("#e_id").val() == 0) {
			return true;
		} else {
			var key = "${_csrf.parameterName}";
			var value = "${_csrf.token}";
			var e_id = $("#e_id").val();
			var first_name = $("#first_name").val();
			var father_name = $("#father_name").val();
			var mother_name = $("#mother_name").val();
			var date_of_birth = $("#date_of_birth").val();
			var gender = $("#gender").val();
			var mob_no = $("#mob_no").val();
			var email = $("#hidden_email").val();
			var category = $("#category").val();
			var religion = $("#religion").val();
			var marital_status = $("#marital_status").val();
			var nationality = $("#nationality").val();
			// 			var state_id =  $("#state_id").val();
			// 			var district_id =  $("#district_id").val();
			// 			var village =  $("#village").val();
			var aadhar_no1 = $("#aadhar_no1").val();
			var aadhar_no2 = $("#aadhar_no2").val();
			var aadhar_no3 = $("#aadhar_no3").val();
			//var aadhar_no = a1+a2+a3;
			var permanent_house_no = $("#permanent_house_no").val();
			var permanent_village = $("#permanent_village").val();
			var permanent_add_line1 = $("#permanent_add_line1").val();
			var permanent_add_line2 = $("#permanent_add_line2").val();
			// 			var permanent_policestation =  $("#permanent_policestation").val();
			var permanent_district = $("#permanent_district").val();
			var permanent_state = $("#permanent_state").val();
			var permanent_pincode = $("#permanent_pincode").val();
			var permanent_lendmark = $("#permanent_lendmark").val();

			var present_house_no = $("#present_house_no").val();
			var present_village = $("#present_village").val();
			var present_add_line1 = $("#present_add_line1").val();
			var present_add_line2 = $("#present_add_line2").val();
			// 			var present_policestation =  $("#present_policestation").val();
			var present_district = $("#present_district").val();
			var present_state = $("#present_state").val();
			var present_pincode = $("#present_pincode").val();
			var present_lendmark = $("#present_lendmark").val();
			var cand_prifix = $("#cand_prifix").val();
			var father_title = $("#father_title").val();
			var mother_title = $("#mother_title").val();
			var neet_roll_no = $("#neet_roll_no").val();
			var neet_application_no = $("#neet_application_no").val();
			var neet_rank = $("#neet_rank").val();
			var neet_marks = $("#neet_marks").val();
			var neet_percentile = $("#neet_percentile").val();
			var middel_name = $("#middel_name").val();
			var surname = $("#surname").val();

			var corre_house_no = $("#corre_house_no").val();
			var corre_add_line1 = $("#corre_add_line1").val();
			var corre_add_line2 = $("#corre_add_line2").val();
			var corre_state = $("#corre_state").val();
			var corre_district = $("#corre_district").val();
			var corre_village = $("#corre_village").val();
			var corre_pincode = $("#corre_pincode").val();
			var corre_lendmark = $("#corre_lendmark").val();

			$.post('getUpdatePerDetails_PG_ctrl?' + key + "=" + value, {
				e_id : e_id,
				first_name : first_name,
				father_name : father_name,
				mother_name : mother_name,
				date_of_birth : date_of_birth,
				gender : gender,
				mob_no : mob_no,
				email : email,
				category : category,
				religion : religion,
				marital_status : marital_status,
				nationality : nationality,
				aadhar_no1 : aadhar_no1,
				aadhar_no2 : aadhar_no2,
				aadhar_no3 : aadhar_no3,
				permanent_house_no : permanent_house_no,
				permanent_village : permanent_village,
				permanent_add_line1 : permanent_add_line1,
				permanent_add_line2 : permanent_add_line2,
				permanent_district : permanent_district,
				permanent_state : permanent_state,
				permanent_pincode : permanent_pincode,
				permanent_lendmark : permanent_lendmark,
				present_house_no : present_house_no,
				present_village : present_village,
				present_add_line1 : present_add_line1,
				present_add_line2 : present_add_line2,
				present_district : present_district,
				present_state : present_state,
				present_pincode : present_pincode,
				present_lendmark : present_lendmark,
				middel_name : middel_name,
				surname : surname,
				yrr : yrr,
				corre_house_no : corre_house_no,
				corre_add_line1 : corre_add_line1,
				corre_add_line2 : corre_add_line2,
				corre_state : corre_state,
				corre_district : corre_district,
				corre_village : corre_village,
				corre_pincode : corre_pincode,
				corre_lendmark : corre_lendmark

			}, function(j) {

				if (j == "Data Updated Successfully") {
					alert(j);
					$("#eid").val($("#e_id").val());
					document.getElementById('mainForm11').submit();
				} else {
					var mesag = j.split(",")[0];
					var id_for_foc = j.split(",")[1];
					alert(mesag);
					$("#" + id_for_foc).focus();
					return false;
				}

			}).fail(function(xhr, textStatus, errorThrown) {
				alert("fail to Update Data")
			});
		}
	}
	// function isNumberKey0to9(evt) {
	// 	var charCode = (evt.which) ? evt.which : evt.keyCode;
	// 	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
	// 		return false;
	// 	}else{
	// 		if(charCode == 46){
	// 			return false;
	// 		}
	// 	}
	// 	return true;
	// }

	function mobileNumber(obj) {
		if (obj.value.length < 10) {
			alert('Please Enter Valid Mobile Number');
			$('#' + obj.id).focus();
			$('#' + obj.id).val("");
			return false;
		}
		_mobile = obj.value;
		var regExp = /^[6789]\d{9}$/;
		if (_mobile == '' || !regExp.test(_mobile)) {
			alert('Please Enter Mobile Number Start with 6,7,8,9 Digit');
			$('#' + obj.id).focus();
			$('#' + obj.id).val("");
			return false;
		}
	}

	function copy_address() {

		if ($("#check_address").is(":checked") == true) {

			$("#present_house_no").val($("#permanent_house_no").val());
			$("#present_house_no").attr('readonly', true);

			$("#present_village").val($("#permanent_village").val());
			$("#present_village").attr('readonly', true);

			$("#present_add_line1").val($("#permanent_add_line1").val());
			$("#present_add_line1").attr('readonly', true);

			$("#present_add_line2").val($("#permanent_add_line2").val());
			$("#present_add_line2").attr('readonly', true);

			$("#present_state").val($("#permanent_state").val());
			$("#present_state").attr('readonly', true);
			getpresDistrict();
			$("#present_state").attr('tabindex', '-1');

			$("#present_district").val($("#permanent_district").val());
			$("#present_district").attr('readonly', true);
			$("#present_district").attr('tabindex', '-1');

			$("#present_pincode").val($("#permanent_pincode").val());
			$("#present_pincode").attr('readonly', true);

			$("#present_lendmark").val($("#permanent_lendmark").val());
			$("#present_lendmark").attr('readonly', true);

			
			
		}

		if ($("#check_address").is(":checked") == false) {
			$("#present_house_no").val("");
			$("#present_house_no").attr('readonly', false);

			$("#present_village").val("");
			$("#present_village").attr('readonly', false);

			$("#present_add_line1").val("");
			$("#present_add_line1").attr('readonly', false);

			$("#present_add_line2").val("");
			$("#present_add_line2").attr('readonly', false);

			$("#present_state").val("0");
			$("#present_state").attr('readonly', false);
			getpresDistrict();

			$("#present_state").attr('tabindex', '0');
			$("#present_district").val("0");
			$("#present_district").attr('readonly', false);
			$("#present_district").attr('tabindex', '0');
			$("#present_pincode").val("");
			$("#present_pincode").attr('readonly', false);
			$("#present_lendmark").val("");
			$("#present_lendmark").attr('readonly', false);

			var samas = $('input[type=radio][name=check_add]:checked').attr('id');
			if (samas == "check_address2") {
					$('#check_address3').click();
			}
		}
	}

	function changeAddress() {

		if ($("#check_address").is(":checked") == true) {
			$("#check_address").prop("checked", false);

			$("#present_house_no").val("");
			$('#present_house_no').attr('readonly', false);

			$("#present_village").val("");
			$('#present_village').attr('readonly', false);

			$("#present_add_line1").val("");
			$('#present_add_line1').attr('readonly', false);

			$("#present_add_line2").val("");
			$('#present_add_line2').attr('readonly', false);

			$("#present_state").val("0");
			$('#present_state').attr('readonly', false);
			$("#present_state").attr('tabindex', '0');

			$("select#present_district").val("0");
			$('select#present_district').attr('readonly', false);
			$("#present_district").attr('tabindex', '0');

			$("#present_pincode").val("");
			$("#present_pincode").attr('readonly', false);

			$("#present_lendmark").val("");
			$("#present_lendmark").attr('readonly', false);
			
			var samas = $('input[type=radio][name=check_add]:checked').attr('id');
			if (samas == "check_address2") {
					$('#check_address3').click();
			}
		}
		var samas = $('input[type=radio][name=check_add]:checked').attr('id');
		if (samas == "check_address1") {
				$('#check_address3').click();
		}
	}

	/* function getPreviousPage()
	 {  
	 $("#exper_hid").val("${adv_details_session}");
	 document.getElementById("mainForm11").submit();
	 } */

	function getPage() {
		$("#exper_hid").val("${adv_details_session}");
		document.getElementById("applicationUrlForm4").submit();
	}

	function getEduPage() {
		if ('${pddata}' != "[]") {
			$("#eid").val($("#e_id").val());
			$("#exper_hid").val("${adv_details_session}");
			document.getElementById("mainForm11").submit();
		}else {
			alert('Please Save Personal Details Form First');
		}
	}
	function getExpPage() {
		$("#tp_eid").val($("#e_id").val());
		$("#exper_hid").val("${adv_details_session}");
		document.getElementById("applicationUrlForm4").submit();
	}

	function getDocPage() {
		$("#doc_eid").val($("#e_id").val());
		$("#exper_hid").val("${adv_details_session}");
		document.getElementById("applicationUrlForm5").submit();
	}

	function getchoicePage() {
		$("#ch_eid").val($("#e_id").val());
		$("#exper_hid").val("${adv_details_session}");
		document.getElementById("applicationUrlForm6").submit();
	}

	// function getState() {
	// 	$.ajaxSetup({
	// 		async : false
	// 	});
	// 	var selval = $("#nationality").val();
	// 	$
	// 			.post(
	// 					"getStateUrl?" + key + "=" + value,
	// 					{
	// 						selval : selval
	// 					},
	// 					function(j) {

	// 						var options = '<option value="' + "0" + '">'
	// 								+ "--Select--" + '</option>';
	// 						for (var i = 0; i < j.length; i++) {

	// 							options += '<option   value="' + j[i].state_id + '" name="'+j[i].state_id+'" >'
	// 									+ j[i].state_name + '</option>';
	// 						}
	// 						$("select#state_id").html(options);
	// 					});
	// }
	function getDistrict() {
		$.ajaxSetup({
			async : false
		});
		var selval = $("#state_id").val();
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
							$("select#district_id").html(options);
						});
	}

	function getpermDistrict() {
		$.ajaxSetup({
			async : false
		});
		var selval = $("#permanent_state").val();
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
							$("select#permanent_district").html(options);
						});
	}
	function getpresDistrict() {
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

	function getCorsDistrict() {
		$.ajaxSetup({
			async : false
		});
		// 		debugger;
		var selval = $("#corre_state").val();
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
							$("select#corre_district").html(options);
						});
	}

	//Age Calculate	
	function calculate_age(obj) {

		var from_d = obj;

		var from_d1 = from_d.substring(6, 10);
		var from_d2 = from_d.substring(3, 5);
		var from_d3 = from_d.substring(0, 2);

		var frm_d = from_d3 + "-" + from_d2 + "-" + from_d1;
		/* var to_d=$("#dt_of_joining").val();
		var to_d1=to_d.substring(0,4);
		var to_d2=to_d.substring(7,5);
		var to_d3=to_d.substring(10,8); */
		var today = new Date();
		var to_d3 = today.getDate();
		var to_d2 = today.getMonth() + 1;
		var to_d1 = today.getFullYear();
		var to_d0 = to_d3 + "-" + to_d2 + "-" + to_d1;

		if (to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3) {
			var year = to_d1 - from_d1
			var month = to_d2 - from_d2
		}
		if (to_d2 > from_d2 && to_d3 < from_d3) {
			var year = to_d1 - from_d1
			var month = to_d2 - from_d2 - 1
		}
		if (from_d2 > to_d2) {
			var year = to_d1 - from_d1 - 1
			var month1 = from_d2 - to_d2
			var month = 12 - month1
		}
		if (from_d2 == to_d2 && from_d3 > to_d3) {
			var year = to_d1 - from_d1 - 1
			var days = from_d3 - to_d3
		}
		if (from_d2 == to_d2 && to_d3 > from_d3) {
			var year = to_d1 - from_d1
			var days = to_d3 - from_d3

		}
		if (from_d2 == to_d2 && to_d3 == from_d3) {
			var year = to_d1 - from_d1
			var days = 0
		}
		//days
		if (from_d2 > to_d2 && from_d3 > to_d3) {
			var m = from_d2 - to_d2
			var n = m * 30
			var m1 = from_d3 - to_d3
			var m2 = n + m1
			var days = m2
		}
		if (from_d2 > to_d2 && to_d3 > from_d3) {
			var m = from_d2 - to_d2
			var n = m * 30
			var m1 = to_d3 - from_d3
			var m2 = n + m1
			var days = m2
		}
		if (to_d2 > from_d2 && to_d3 > from_d3) {
			var m = to_d2 - from_d2
			var n = m * 30
			var m1 = to_d3 - from_d3
			var m2 = n + m1
			var days = m2

		}
		if (to_d2 > from_d2 && from_d3 > to_d3) {
			var m = to_d2 - from_d2
			var n = m * 30
			var m1 = from_d3 - to_d3
			var m2 = n + m1
			var days = m2

		}
		$(".get-value").text("" + year + " Years");
		$("#yrr").val(year);
		if (year < 17 || year == "" || year == "0") {
			alert("Age Should Be Greater Than 17 Years")
			$("#date_of_birth").focus();
			return false;
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
	// function isNumberKey(txt, evt) {
	//     var charCode = (evt.which) ? evt.which : evt.keyCode;
	//     if (charCode == 46) {
	//       //Check if the text already contains the . character
	//       if (txt.value.indexOf('.') === -1) {
	//         return true;
	//       } else {
	//         return false;
	//       }
	//     } else {
	//       if (charCode > 31 &&
	//         (charCode < 48 || charCode > 57))
	//         return false;
	//     }
	//     return true;
	//   }

	function copy_permanent_address() {

		if ($("#check_address1").is(":checked") == true) {

			$("#corre_house_no").val($("#permanent_house_no").val());
			$("#corre_house_no").attr('readonly', true);
			$("#corre_add_line1").val($("#permanent_add_line1").val());
			$("#corre_add_line1").attr('readonly', true);
			$("#corre_add_line2").val($("#permanent_add_line2").val());
			$("#corre_add_line2").attr('readonly', true);
			$("#corre_state").val($("#permanent_state").val());
			$("#corre_state").change();
			$("#corre_state").attr('readonly', true);
			$("#corre_state").attr('tabindex', '-1');
			$("#corre_district").val($("#permanent_district").val());
			$("#corre_district").change();
			$("#corre_district").attr('readonly', true);
			$("#corre_district").attr('tabindex', '-1');
			$("#corre_village").val($("#permanent_village").val());
			$("#corre_village").attr('readonly', true);
			$("#corre_pincode").val($("#permanent_pincode").val());
			$("#corre_pincode").attr('readonly', true);
			$("#corre_lendmark").val($("#permanent_lendmark").val());
			$("#corre_lendmark").attr('readonly', true);
			$("#chk_add_val").val("0");

		}

		if ($("#check_address1").is(":checked") == false) {
			$("#corre_house_no").val("");
			$("#corre_house_no").attr('readonly', false);

			$("#corre_add_line1").val("");
			$("#corre_add_line1").attr('readonly', false);

			$("#corre_add_line2").val("");
			$("#corre_add_line2").attr('readonly', false);

			$("#corre_village").val("");
			$("#corre_village").attr('readonly', false);

			$("#corre_state").val("0");
			$("#corre_state").attr('readonly', false);

			$("#corre_district").val("0");
			$("#corre_district").attr('readonly', false);

			$("#corre_pincode").val("");
			$("#corre_pincode").attr('readonly', false);

			$("#corre_lendmark").val("");
			$("#corre_lendmark").attr('readonly', false);

		}
	}

	function copy_present_address() {

		if ($("#check_address2").is(":checked") == true) {

			$("#corre_house_no").val($("#present_house_no").val());
			$("#corre_house_no").attr('readonly', true);

			$("#corre_village").val($("#present_village").val());
			$("#corre_village").attr('readonly', true);

			$("#corre_add_line1").val($("#present_add_line1").val());
			$("#corre_add_line1").attr('readonly', true);

			$("#corre_add_line2").val($("#present_add_line2").val());
			$("#corre_add_line2").attr('readonly', true);

			$("#corre_state").val($("#present_state").val());
			$("#corre_state").change();
			$("#corre_state").attr('readonly', true);

			$("#corre_state").attr('tabindex', '-1');
			getCorsDistrict();
			$("#corre_district").val($("#present_district").val());
			$("#corre_district").change();

			$("#corre_district").attr('readonly', true);
			$("#corre_district").attr('tabindex', '-1');

			$("#corre_pincode").val($("#present_pincode").val());
			$("#corre_pincode").attr('readonly', true);

			$("#corre_lendmark").val($("#present_lendmark").val());
			$("#corre_lendmark").attr('readonly', true);
			$("#chk_add_val").val("1");
		}

		if ($("#check_address2").is(":checked") == false) {
			$("#corre_house_no").val("");
			$("#corre_house_no").attr('readonly', false);

			$("#corre_village").val("");
			$("#corre_village").attr('readonly', false);

			$("#corre_add_line1").val("");
			$("#corre_add_line1").attr('readonly', false);

			$("#corre_add_line2").val("");
			$("#corre_add_line2").attr('readonly', false);

			$("#corre_state").val("0");
			$("#corre_state").attr('readonly', false);

			$("#corre_district").val("0");
			$("#corre_district").attr('readonly', false);

			$("#corre_pincode").val("");
			$("#corre_pincode").attr('readonly', false);

			$("#corre_lendmark").val("");
			$("#corre_lendmark").attr('readonly', false);

		}
	}
	function other_address() {
		$("#corre_house_no").val("");
		$("#corre_house_no").attr('readonly', false);

		$("#corre_village").val("");
		$("#corre_village").attr('readonly', false);

		$("#corre_add_line1").val("");
		$("#corre_add_line1").attr('readonly', false);

		$("#corre_add_line2").val("");
		$("#corre_add_line2").attr('readonly', false);

		$("#corre_state").val("0");
		$("#corre_state").change();
		$("#corre_state").attr('readonly', false);

		$("#corre_district").val("0");
		$("#corre_district").attr('readonly', false);
		$("#corre_district").change();

		$("#corre_pincode").val("");
		$("#corre_pincode").attr('readonly', false);

		$("#corre_lendmark").val("");
		$("#corre_lendmark").attr('readonly', false);

		$("#chk_add_val").val("2");

	}

	// function changeCurrespongAddress() {
	// 	if(document.getElementById('check_address2').checked == true) {   
	// 		$("#check_address2").prop("checked", false);

	// 			$("#curr_address").val("");
	// 			$('#curr_address').attr('readonly', false);

	// 			$("#curr_address2").val("");
	// 			$('#curr_address2').attr('readonly', false);

	// 			$("#curr_address3").val("");
	// 			$('#curr_address3').attr('readonly', false);

	// 			$("#corre_state").val("0");
	// 			$("#corre_state").attr('style','');
	// 			$("#corre_state").attr('readonly',false);

	// 			$("#corre_district").val("0");
	// 	  		$("#corre_district").attr('readonly',false);
	// 			$("#corre_district").attr('style','');
	// 			$("#corre_pincode").val("");
	// 			$('#corre_pincode').attr('readonly', false);

	// 		}
	// }

	// csp----------------------------

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('save_btn').onclick = function() {
			return saveData();
		};
		document.getElementById('update_btn').onclick = function() {
			// 			if(confirm('Are you sure you want to Proceed?')){return saveData(); }else{return false;}
			saveData();
		};

		document.getElementById('first_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('middel_name').onkeypress = function() {
			return onlyAlphabetsStringSpacewithslash(event, this);
		};
		document.getElementById('surname').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('father_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('mother_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('date_of_birth').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_of_birth').onfocus = function() {
			this.style.color = '#000000';
		};
		document.getElementById('date_of_birth').onblur = function() {
			clickrecall(this, 'DD/MM/YYYY');
			validateDate_BackDate(this.value, this);
		};
		document.getElementById('date_of_birth').onkeyup = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_of_birth').onchange = function() {
			clickrecall(this, 'DD/MM/YYYY');
			validateDate_FutureDate(this.value, this);
			calculate_age(this.value);
		};
		document.getElementById('mob_no').onkeypress = function() {
			return isNumberKey0to9(event);
		};
		document.getElementById('mob_no').onchange = function() {
			return mobileNumber(this);
		};
		// 	document.getElementById('nationality').onchange = function() {
		// 		return getState();
		// 	};
		// 	document.getElementById('state_id').onchange = function() {
		// 		return getDistrict();
		// 	};
		// 	document.getElementById('village').onkeypress = function() {
		// 		return onlyAlphabetsStringSpace(event, this);
		// 	};
		document.getElementById('aadhar_no1').onkeypress = function() {
			lengthadhar();
			return isNumberKey0to9(event);
		};
		document.getElementById('aadhar_no1').onfocus = function() {
			return this.select();
		};
		document.getElementById('aadhar_no1').onchange = function() {
			return checkLength(this.id, 4);
		};
		document.getElementById('aadhar_no1').onkeyup = function() {
			return movetoNext(this, 'aadhar_no2');
		};
		document.getElementById('aadhar_no2').onfocus = function() {
			return this.select();
		};
		document.getElementById('aadhar_no2').onkeypress = function() {
			lengthadhar();
			return isNumberKey0to9(event);
		};
		document.getElementById('aadhar_no2').onchange = function() {
			return checkLength(this.id, 4);
		};
		document.getElementById('aadhar_no2').onkeyup = function() {
			return movetoNext(this, 'aadhar_no3');
		};
		document.getElementById('aadhar_no3').onkeypress = function() {
			lengthadhar();
			return isNumberKey0to9(event);
		};
		document.getElementById('aadhar_no3').onfocus = function() {
			return this.select();
		};
		document.getElementById('aadhar_no3').onchange = function() {
			return checkLength(this.id, 4);
		};

		document.getElementById('permanent_house_no').onkeypress = function() {
			changeAddress();
					return onlyAlphaNumericwithslash(event, this);
		};
		document.getElementById('permanent_village').onkeypress = function() {
			changeAddress();
			return onlyAlphabetsStringSpace(event, this);
		};

		document.getElementById('permanent_add_line1').onkeypress = function() {
			changeAddress();
					return onlyAlphaNumeric(event, this);
		};

		document.getElementById('permanent_add_line2').onkeypress = function() {
			changeAddress();
					return onlyAlphaNumeric(event, this);
		};

		document.getElementById('permanent_state').onchange = function() {
			changeAddress();
			getpermDistrict();
		};

		document.getElementById('permanent_pincode').onchange = function() {
			return checkLength(this.id, 6);
		};
		document.getElementById('permanent_pincode').onkeypress = function() {
			changeAddress();
			return isNumberKey0to9(event);
		};
		document.getElementById('permanent_lendmark').onkeypress = function() {
			changeAddress();
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('check_address').onclick = function() {
			return copy_address();
		};
		
		document.getElementById('present_house_no').onkeypress = function() {
					return onlyAlphaNumericwithslash(event, this);
		};
		document.getElementById('present_village').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('present_add_line1').onkeypress = function() {
					return onlyAlphaNumeric(event, this);
		};
		document.getElementById('present_add_line2').onkeypress = function() {
					return onlyAlphaNumeric(event, this);
		};
		document.getElementById('present_state').onchange = function() {
			return getpresDistrict();
		};
		document.getElementById('present_pincode').onchange = function() {
			return checkLength(this.id, 6);
		};
		document.getElementById('present_pincode').onkeypress = function() {
			return isNumberKey0to9(event);
		};
		document.getElementById('present_lendmark').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};

		// 	document.getElementById('neet_roll_no').onkeypress = function() {
		// 		return isNumberKey0to9(event);
		// 	};
		// 	document.getElementById('neet_application_no').onkeypress = function() {
		// 		return isNumberKey0to9(event);
		// 	};
		// 	document.getElementById('neet_rank').onkeypress = function() {
		// 		return isNumberKey0to9(event);
		// 	};
		// 	document.getElementById('neet_marks').onkeypress = function() {
		// 		return isNumberKey0to9(event);
		// 	};
		// 	document.getElementById('neet_percentile').onkeypress = function() {
		// 		return isNumberKeydecimal(this, event);
		// 	};

		document.getElementById('tunnel_2').onclick = function() {
			// 		 if(confirm('Are you sure you want to Proceed?')){getEduPage();}else{return false;}
			getEduPage();
		};

// 		document.getElementById('aIdNext').onclick = function() {
			// 		if (confirm('Are you sure you want to Proceed?')) {
// 			return getEduPage();
			// 		} else {
			// 			return false;
			// 		}
// 		};
		
		//corresponding address
		document.getElementById('check_address1').onclick = function() {
			copy_permanent_address();
		};
		document.getElementById('check_address2').onclick = function() {
			copy_present_address();
		};
		document.getElementById('check_address3').onclick = function() {
			other_address();
		};
		document.getElementById('corre_state').onchange = function() {
			getCorsDistrict();
		};
		document.getElementById('corre_pincode').onchange = function() {
			return checkLength(this.id, 6);
		};
		document.getElementById('corre_pincode').onkeypress = function() {
			return isNumberKey0to9(event);
		};
		document.getElementById('corre_house_no').onkeypress = function() {
			return onlyAlphaNumericwithslash(event,this);
		};
		document.getElementById('corre_add_line1').onkeypress = function() {
					return onlyAlphaNumeric(event, this);
		};
		document.getElementById('corre_add_line2').onkeypress = function() {
					return onlyAlphaNumeric(event, this);
		};
		document.getElementById('corre_lendmark').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};

	});
</script>
