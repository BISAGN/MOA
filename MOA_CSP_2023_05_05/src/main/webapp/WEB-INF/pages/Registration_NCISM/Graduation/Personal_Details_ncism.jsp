<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link href="js/tunneldesign/tunnelold.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<%-- <c:if test="${not empty msg}"> --%>
<%-- 	<input type="hidden" name="msg" id="msg" value="${msg}"/> --%>
<%-- </c:if> --%>
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
		<!-- title-wrapper end -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="tunnel_design" id="updatediv">
							<div class="square tunnel_active">
								<h5 class="tunnel_text" id="tunnel_1">Personal Details</h5>
							</div>
							<div class="square">
								<a href="#" class="tunnel_text" id="tunnel_2">Basic
									Education Details</a>
							</div>
							<div class="square">
								<a href="#" class="tunnel_text" id="tunnel_3"> Upload
									Document</a>
							</div>
							<div class="square">
								<a href="#" class="tunnel_text" id="tunnel_4"> Declaration </a>
							</div>
						</div>
					</div>
					<div class="card-style mb-30">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="custom-ayushid custom-d-none">
									<ul class="custom-uniqueid">
										<li><p class="custom-id custom-active-badge custom-d-none">
												<span class="custom-id-title">Ayush ID:</span> <span
													class="custom-id-value" id="ayush_id"></span>
											</p></li>
									</ul>
								</div>
							</div>

							<div class="col-lg-12 col-md-12 col-sm-12">
								<form:form name="personal_details" id="personal_details"
									action="personal_details_Ncism_Action" method='POST'
									commandName="personal_details_Ncism_CMD"
									enctype="multipart/form-data">
									<section class="detail-block">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<div class="custom-data-value custom-title custom-title-bg">
													<h5 class="custom-title-tag">Personal Details</h5>
												</div>
											</div>


											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label>Name of Applicant <span class="mandatory">*</span></label>
													<div class="stepform-groupadd">
														<div class="select-style-1">
															<div class="select-position">
																<select name="cand_prifix" id="cand_prifix"
																	class="form-control">
																	<option value="0">--Select--</option>
																	<c:forEach var="item" items="${getPrefixList}"
																		varStatus="num">
																		<option value="${item.id}" name="${item.id}">${item.prefix}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
														<div class="input-style-1 buttonmergeinput">
															<input type="text" name="pers_name" id="pers_name"
																class="form-control " placeholder="Enter First Name"
																maxlength="50">
														</div>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label>Middle Name</label> <input type="text"
														id="pers_middel_name" name="pers_middel_name"
														placeholder="Enter Middle Name" maxlength="50"
														autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label>Surname <span class="mandatory">*</span></label> <input
														type="text" id="pers_surname" name="pers_surname"
														maxlength="50" placeholder="Enter Surname"
														autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label>Father's Name <span class="mandatory">*</span></label>
													<div class="form-group stepform-groupadd">
														<div class="input-style-1 form-group buttonmergeinput">
															<input type="text" id="pers_father_name"
																name="pers_father_name" maxlength="50"
																placeholder="Enter Father's Name" autocomplete="off">
														</div>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label>Mother's Name <span class="mandatory">*</span></label>
													<div class="form-group stepform-groupadd">
														<div class="input-style-1 form-group buttonmergeinput ">
															<input type="text" id="pers_mother_name"
																name="pers_mother_name" class="form-control"
																maxlength="30" placeholder="Enter Mother's Name"
																autocomplete="off">
														</div>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">Gender <span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="pers_gender" id="pers_gender"
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
													<input type="text" name="pers_date_of_birth"
														id="pers_date_of_birth" maxlength="10"
														class="form-control-sm form-control" aria-required="true"
														autocomplete="off" value="DD/MM/YYYY" />
													<!-- Hidden Start  -->
													<input type="hidden" id="yrr" name="yrr" value="">
													<!-- Hidden End  -->
												</div>
											</div>
											
<!-- 											//===27-01-2023 -->

											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Mobile Number<span
														class="mandatory">*</span></label> <input type="text"
														id="pers_mob_no" name="pers_mob_no" maxlength="10" placeholder="Enter Mobile Number"
														autocomplete="off">
												</div>
											</div>
											
											
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Email Id <span
														class="mandatory">*</span>
													</label> <input type="text" id="pers_email" name="pers_email"
														maxlength="50" autocomplete="off" readonly="readonly">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">Category <span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="pers_category" id="pers_category">
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
														<select name="pers_religion" id="pers_religion"
															class="form-control">
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
														<select name="pers_marital_status"
															id="pers_marital_status"
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
													<input type="hidden" id="pers_maritalstatus_txt"
														name="pers_maritalstatus_txt"
														value="${pers_maritalstatus_txt}">
													<!-- Hidden End -->
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">Nationality <span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="pers_nationality" id="pers_nationality"
															onchange="getState();" class="form-control">
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
												<div class="select-style-1">
													<label for="text-input">State/UT (Domicile) <span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="state_id" id="state_id"
															class=" form-control form-control-lg">
															<option value="0">-- Select --</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">District <span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="district_id" id="district_id"
															class=" form-control form-control-lg">
															<option value="0">-- Select --</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Village/City<span
														class="mandatory">*</span></label> <input type="text" id="village"
														name="village" maxlength="50" class="form-control"
														placeholder="Enter Village/City" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Aadhaar No. <span
														class="mandatory">*</span></label>
													<div class="form-group stepform-groupadd">
														<input id="pers_aadhar_no1" name="pers_aadhar_no1"
															type="text" autocomplete="off"
															placeholder="Enter First Four Digit Of Aadhaar No"
															minlength="4" maxlength="4"> <input
															id="pers_aadhar_no2" name="pers_aadhar_no2" type="text"
															autocomplete="off"
															placeholder="Enter Second Four Digit Of Aadhaar No"
															minlength="4" maxlength="4"> <input
															id="pers_aadhar_no3" name="pers_aadhar_no3" type="text"
															autocomplete="off"
															placeholder="Enter Last Four Digit Of Aadhaar No"
															minlength="4" maxlength="4">
													</div>
												</div>
											</div>


											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">Quota <span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="quota_id" id="quota_id">
															<option value="0">-- Select --</option>

															<c:forEach var="item" items="${getQuotaList}"
																varStatus="num">
																<option value="${item.id}" name="${item.quota}">${item.quota}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>


											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">Counselling Authority <span
														class="mandatory">*</span></label>
													<div class="select-position">
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
														id="pers_permanent_house_no"
														name="pers_permanent_house_no" maxlength="50"
														autocomplete="off" placeholder="Enter House No/Name">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Village/City <span
														class="mandatory">*</span></label> <input type="text"
														id="pers_permanent_village" name="pers_permanent_village"
														maxlength="50" autocomplete="off"
														placeholder="Enter Village/City">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Post Office <span
														class="mandatory">*</span></label> <input type="text"
														id="pers_permanent_postoffice"
														name="pers_permanent_postoffice" maxlength="50"
														autocomplete="off" placeholder="Enter Post Office">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Tehsil <span
														class="mandatory">*</span></label> <input type="text"
														id="pers_permanent_tehsil" name="pers_permanent_tehsil"
														maxlength="50" autocomplete="off"
														placeholder="Enter Tehsil">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Police Station <span
														class="mandatory">*</span></label> <input type="text"
														id="pers_permanent_policestation"
														name="pers_permanent_policestation" maxlength="50"
														autocomplete="off" placeholder="Enter Police Station">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">State <span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="pers_permanent_state"
															id="pers_permanent_state"
															class=" form-control form-control-lg">
															<option value="0" selected="selected">-- Select
																State --</option>
															<c:forEach var="item" items="${getMedStateName}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
													<!-- Hidden Start -->
													<input type="hidden" id="pers_permanent_state_txt"
														name="pers_permanent_state_txt"
														value="${pers_permanent_state_txt}" autocomplete="off" />
													<!-- Hidden End -->
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">District <span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="pers_permanent_district"
															id="pers_permanent_district"
															class=" form-control form-control-lg">
															<option value="0" selected="selected">-- Select
																--</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Landmark <span
														class="mandatory">*</span></label> <input type="text"
														id="pers_permanent_lendmark"
														name="pers_permanent_lendmark" maxlength="50"
														class="form-control" autocomplete="off"
														placeholder="Enter Landmark">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1 input-sm">
													<label for="text-input">Pincode <span
														class="mandatory">*</span></label> <input type="text"
														id="pers_permanent_pincode" name="pers_permanent_pincode"
														maxlength="6" class="form-control" autocomplete="off"
														placeholder="Pincode">
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
														id="pers_present_house_no" name="pers_present_house_no"
														maxlength="50" autocomplete="off"
														placeholder="Enter House No/Name">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Village/City <span
														class="mandatory">*</span></label> <input type="text"
														id="pers_present_village" name="pers_present_village"
														maxlength="50" autocomplete="off"
														placeholder="Enter Village/City">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Post Office <span
														class="mandatory">*</span></label> <input type="text"
														id="pers_present_postoffice"
														name="pers_present_postoffice" maxlength="50"
														autocomplete="off" placeholder="Enter Post Office">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Tehsil <span
														class="mandatory">*</span></label> <input type="text"
														id="pers_present_tehsil" name="pers_present_tehsil"
														maxlength="50" autocomplete="off"
														placeholder="Enter Tehsil">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Police Station <span
														class="mandatory">*</span></label> <input type="text"
														id="pers_present_policestation"
														name="pers_present_policestation" maxlength="50"
														autocomplete="off" placeholder="Enter Police Station">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">State <span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="pers_present_state" id="pers_present_state"
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
														<select name="pers_present_district"
															id="pers_present_district"
															class=" form-control form-control-lg">
															<option value="0" selected="selected">-- Select
																--</option>

														</select>
													</div>
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Landmark <span
														class="mandatory">*</span></label> <input type="text"
														id="pers_present_lendmark" name="pers_present_lendmark"
														maxlength="50" autocomplete="off"
														placeholder="Enter Landmark">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1 input-sm">
													<label for="text-input ">Pincode <span
														class="mandatory">*</span></label> <input type="text"
														id="pers_present_pincode" name="pers_present_pincode"
														maxlength="6" autocomplete="off"
														placeholder="Enter Pincode">
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
													<label for="text-input">House No/Name <span
														class="mandatory">*</span></label> <input type="text"
														id="corre_house_no" name="corre_house_no" maxlength="50"
														autocomplete="off" placeholder="Enter House No/Name">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Village/City <span
														class="mandatory">*</span></label> <input type="text"
														id="corre_village" name="corre_village" maxlength="50"
														autocomplete="off" placeholder="Enter Village/City">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Post Office <span
														class="mandatory">*</span></label> <input type="text"
														id="corre_postoffice" name="corre_postoffice"
														maxlength="50" autocomplete="off"
														placeholder="Enter Post Office">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Tehsil <span
														class="mandatory">*</span></label> <input type="text"
														id="corre_tehsil" name="corre_tehsil" maxlength="50"
														autocomplete="off" placeholder="Enter Tehsil">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">Police Station <span
														class="mandatory">*</span></label> <input type="text"
														id="corre_policestation" name="corre_policestation"
														maxlength="50" autocomplete="off"
														placeholder="Enter Police Station">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="text-input">State <span
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
													<label for="text-input">Landmark <span
														class="mandatory">*</span></label> <input type="text"
														id="corre_lendmark" name="corre_lendmark" maxlength="50"
														autocomplete="off" placeholder="Enter Landmark">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1 input-sm">
													<label for="text-input">Pincode <span
														class="mandatory">*</span></label> <input type="text"
														id="corre_pincode" name="corre_pincode" maxlength="6"
														class="form-control" autocomplete="off"
														placeholder="Enter Pincode">
												</div>
											</div>
										</div>
									</section>
									<section class="detail-block">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<div class="custom-data-value custom-title custom-title-bg">
													<h5 class="custom-title-tag">NEET Details</h5>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">NEET Roll No<span
														class="mandatory">*</span></label> <input type="text"
														id="neet_roll_no" name="neet_roll_no" maxlength="10"
														autocomplete="off" placeholder="Enter NEET Roll No">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">NEET Application No<span
														class="mandatory">*</span></label> <input type="text"
														id="neet_application_no" name="neet_application_no"
														maxlength="12" autocomplete="off"
														placeholder="Enter NEET Application No">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">NEET All India Rank<span
														class="mandatory">*</span></label> <input type="text"
														id="neet_rank" name="neet_rank" maxlength="7"
														autocomplete="off" placeholder="Enter NEET All India Rank">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">NEET Marks<span
														class="mandatory">*</span></label> <input type="text"
														id="neet_marks" name="neet_marks" maxlength="3"
														autocomplete="off" placeholder="Enter NEET Marks">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="text-input">NEET Percentile<span
														class="mandatory">*</span></label> <input type="text"
														id="neet_percentile" name="neet_percentile" maxlength="5"
														autocomplete="off" placeholder="Enter NEET Percentile">
												</div>
											</div>
										</div>
										<!-- Hidden Start -->
												<input type="hidden" name="e_id" id="e_id" value="0" />
												<input type="hidden" name="hemail" id="hemail" value="0"/>
												<!-- Hidden End -->
									</section>
									<!-- Bottom Button Start -->
									<div class="btn-bottom">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">

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
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<c:url value="Edu_Det_Ncism_Url" var="mainFormUrl1" />
<form:form action="${mainFormUrl1}" method="GET" id="mainForm11"
	name="mainForm11">
	<input type="hidden" name="eid" id="eid" value="0" />
	<!-- <hiiden? -->
</form:form>

<c:url value="Total_Exp_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm4"
	name="applicationUrlForm4" modelAttribute="pers_exper_hid">
	<input type="hidden" name="tp_eid" id="tp_eid" value="0" />
</form:form>

<c:url value="doc_uploadNcism_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm5"
	name="applicationUrlForm5" modelAttribute="pers_exper_hid">
	<input type="hidden" name="doc_eid" id="doc_eid" value="0" />
</form:form>

<c:url value="Reshuffling_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm6"
	name="applicationUrlForm6" modelAttribute="pers_exper_hid">
	<input type="hidden" name="ch_eid" id="ch_eid" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document)
			.ready(
					function() {
						datepicketDate('pers_date_of_birth');
						$("#cand_prifix").find('[value="1"]').remove();
						if ('${pddata}' != "[]") {
							$("#pers_name").val('${pddata[0][0]}');
							$("#pers_father_name").val('${pddata[0][1]}');
							$("#pers_mother_name").val('${pddata[0][2]}');
							// 		$("#pers_date_of_birth").val('${pddata[0][3]}');
							// 		var pers_date_of_birth = '${pddata[0][3]}'.substring(0,10);
							// 		$("#pers_date_of_birth").val(pers_date_of_birth);
							var pers_date_of_birth = '${pddata[0][3]}';
							var dob = pers_date_of_birth.substring(0, 10);
							var dob_y = dob.substring(0, 4);
							var dob_m = dob.substring(5, 7);
							var dob_d = dob.substring(8, 10);
							var dob_t = dob_d + "/" + dob_m + "/" + dob_y;
							$("#pers_date_of_birth").val(dob_t);
							calculate_age(dob_t);

							$("#pers_gender").val('${pddata[0][4]}');
							$("#pers_gender").change();
							
							$("#pers_mob_no").val('${pddata[0][5]}');
							$("#pers_email").val('${pddata[0][6]}');
							$("#hemail").val('${pddata[0][6]}');
							$("#pers_category").val('${pddata[0][7]}');
							$("#pers_category").attr('readonly',true);
							$("#pers_category").attr('tabindex', '-1');
						
							$("#pers_religion").val('${pddata[0][8]}');
							$("#pers_marital_status").val('${pddata[0][9]}');
							$("#pers_marital_status").change();
							$("#pers_nationality").val('${pddata[0][10]}');
							$("#pers_nationality").change();
							getState();
							$("#state_id").val('${pddata[0][11]}');
							$("#state_id").change();
							getDistrict();
							$.ajaxSetup({
								async : false
							});
							$("#district_id").val('${pddata[0][12]}');
							$("#district_id").change();
							$("#village").val('${pddata[0][13]}');
							var adhar_card2 = '${pddata[0][14]}';
							var a1 = adhar_card2.substring(0, 4);
							var a2 = adhar_card2.substring(4, 8);
							var a3 = adhar_card2.substring(8, 12);
							$("#pers_aadhar_no1").val(a1);
							$("#pers_aadhar_no2").val(a2);
							$("#pers_aadhar_no3").val(a3);
// 							$("#pers_aadhar_no1").attr('readonly', true);
// 							$("#pers_aadhar_no2").attr('readonly', true);
// 							$("#pers_aadhar_no3").attr('readonly', true);

							// 	quota and councelling
							$("#quota_id").val('${pddata[0][54]}');
							$("#quota_id").attr('readonly',true);
							$("#quota_id").attr('tabindex', '-1');
							
							$("#counselling_authority").val('${pddata[0][55]}');
							$("#counselling_authority").attr('readonly',true);
							$("#counselling_authority").attr('tabindex', '-1');

							$("#pers_permanent_house_no").val(
									'${pddata[0][15]}');
							$("#pers_permanent_village")
									.val('${pddata[0][16]}');
							$("#pers_permanent_postoffice").val(
									'${pddata[0][17]}');
							$("#pers_permanent_tehsil").val('${pddata[0][18]}');
							$("#pers_permanent_policestation").val(
									'${pddata[0][19]}');
							$("#pers_permanent_state").val('${pddata[0][20]}');
							$('#pers_permanent_state').change();
							getpermDistrict();
							$("#pers_permanent_district").val(
									'${pddata[0][21]}');
							$('#pers_permanent_district').change();
							$("#pers_permanent_pincode")
									.val('${pddata[0][22]}');
							$("#pers_permanent_lendmark").val(
									'${pddata[0][23]}');

							if (('${pddata[0][15]}' == '${pddata[0][24]}')
									&& ('${pddata[0][16]}' == '${pddata[0][25]}')
									&& ('${pddata[0][17]}' == '${pddata[0][26]}')
									&& ('${pddata[0][18]}' == '${pddata[0][27]}')
									&& ('${pddata[0][19]}' == '${pddata[0][28]}')
									&& ('${pddata[0][20]}' == '${pddata[0][30]}')
									&& ('${pddata[0][21]}' == '${pddata[0][29]}')
									&& ('${pddata[0][22]}' == '${pddata[0][31]}')
									&& ('${pddata[0][23]}' == '${pddata[0][32]}')) {
								$("#check_address").prop("checked", true);
								$("#pers_present_house_no").val(
										'${pddata[0][24]}');
								$("#pers_present_house_no").attr('readonly',
										true);
								$("#pers_present_village").val(
										'${pddata[0][25]}');
								$("#pers_present_village").attr('readonly',
										true);
								$("#pers_present_postoffice").val(
										'${pddata[0][26]}');
								$("#pers_present_postoffice").attr('readonly',
										true);
								$("#pers_present_tehsil").val(
										'${pddata[0][27]}');
								$("#pers_present_tehsil")
										.attr('readonly', true);
								$("#pers_present_policestation").val(
										'${pddata[0][28]}');
								$("#pers_present_policestation").attr(
										'readonly', true);
								$("#pers_present_state")
										.val('${pddata[0][30]}');
								$('#pers_present_state').change();

								getpresDistrict();
								$("#pers_present_state").attr('tabindex', '-1');
								$("#pers_present_state").attr('readonly', true);
								$("#pers_present_district").val(
										'${pddata[0][29]}');

								$('#pers_present_district').change();
								$("#pers_present_district").attr('readonly',
										true);
								$("#pers_present_district").attr('tabindex',
										'-1');
								$("#pers_present_pincode").val(
										'${pddata[0][31]}');
								$("#pers_present_pincode").attr('readonly',
										true);
								$("#pers_present_lendmark").val(
										'${pddata[0][32]}');
								$("#pers_present_lendmark").attr('readonly',
										true);

							} else {
								$("#pers_present_house_no").val(
										'${pddata[0][24]}');
								$("#pers_present_village").val(
										'${pddata[0][25]}');
								$("#pers_present_postoffice").val(
										'${pddata[0][26]}');
								$("#pers_present_tehsil").val(
										'${pddata[0][27]}');
								$("#pers_present_policestation").val(
										'${pddata[0][28]}');
								$("#pers_present_state")
										.val('${pddata[0][30]}');
								$('#pers_present_state').change();
								getpresDistrict();
								$("#pers_present_district").val(
										'${pddata[0][29]}');
								$('#pers_present_district').change();
								$("#pers_present_pincode").val(
										'${pddata[0][31]}');
								$("#pers_present_lendmark").val(
										'${pddata[0][32]}');
							}

							//corresponding add

							if (('${pddata[0][15]}' == '${pddata[0][45]}')
									&& ('${pddata[0][16]}' == '${pddata[0][46]}')
									&& ('${pddata[0][17]}' == '${pddata[0][47]}')
									&& ('${pddata[0][18]}' == '${pddata[0][48]}')
									&& ('${pddata[0][19]}' == '${pddata[0][49]}')
									&& ('${pddata[0][20]}' == '${pddata[0][51]}')
									&& ('${pddata[0][21]}' == '${pddata[0][50]}')
									&& ('${pddata[0][22]}' == '${pddata[0][52]}')
									&& ('${pddata[0][23]}' == '${pddata[0][53]}')) {

								$("#check_address1").prop("checked", true);

								$("#corre_house_no").val('${pddata[0][45]}');
								$("#corre_house_no").attr('readonly', true);
								$("#corre_village").val('${pddata[0][46]}');
								$("#corre_village").attr('readonly', true);
								$("#corre_postoffice").val('${pddata[0][47]}');
								$("#corre_postoffice").attr('readonly', true);
								$("#corre_tehsil").val('${pddata[0][48]}');
								$("#corre_tehsil").attr('readonly', true);
								$("#corre_policestation").val(
										'${pddata[0][49]}');
								$("#corre_policestation")
										.attr('readonly', true);
								$("#corre_state").val('${pddata[0][51]}');

								$('#corre_state').change();
								getCorsDistrict();
								$("#corre_state").attr('readonly', true);
								$("#corre_state").attr('tabindex', '-1');
								$("#corre_district").val('${pddata[0][50]}');
								$('#corre_district').change();
								$("#corre_district").attr('readonly', true);
								$("#corre_district").attr('tabindex', '-1');
								$("#corre_pincode").val('${pddata[0][52]}');
								$("#corre_pincode").attr('readonly', true);
								$("#corre_lendmark").val('${pddata[0][53]}');
								$("#corre_lendmark").attr('readonly', true);
							}

							// 		else {
							// 			$("#corre_house_no").val('${pddata[0][45]}');
							// 			$("#corre_village").val('${pddata[0][46]}');
							// 			$("#corre_postoffice").val('${pddata[0][47]}');
							// 			$("#corre_tehsil").val('${pddata[0][48]}');
							// 			$("#corre_policestation").val('${pddata[0][49]}');
							// 			$("#corre_state").val('${pddata[0][51]}');
							// 			getCorsDistrict();
							// 			$("#corre_district").val('${pddata[0][50]}');
							// 			$("#corre_pincode").val('${pddata[0][52]}');
							// 			$("#corre_lendmark").val('${pddata[0][53]}');
							// 		}

							else if (('${pddata[0][24]}' == '${pddata[0][45]}')
									&& ('${pddata[0][25]}' == '${pddata[0][46]}')
									&& ('${pddata[0][26]}' == '${pddata[0][47]}')
									&& ('${pddata[0][27]}' == '${pddata[0][48]}')
									&& ('${pddata[0][28]}' == '${pddata[0][49]}')
									&& ('${pddata[0][30]}' == '${pddata[0][51]}')
									&& ('${pddata[0][29]}' == '${pddata[0][50]}')
									&& ('${pddata[0][31]}' == '${pddata[0][52]}')
									&& ('${pddata[0][32]}' == '${pddata[0][53]}')) {

								$("#check_address2").prop("checked", true);

								$("#corre_house_no").val('${pddata[0][45]}');
								$("#corre_house_no").attr('readonly', true);
								$("#corre_village").val('${pddata[0][46]}');
								$("#corre_village").attr('readonly', true);
								$("#corre_postoffice").val('${pddata[0][47]}');
								$("#corre_postoffice").attr('readonly', true);
								$("#corre_tehsil").val('${pddata[0][48]}');
								$("#corre_tehsil").attr('readonly', true);
								$("#corre_policestation").val(
										'${pddata[0][49]}');
								$("#corre_policestation")
										.attr('readonly', true);
								$("#corre_state").val('${pddata[0][51]}');
								$('#corre_state').change();
								getCorsDistrict();
								$("#corre_state").attr('readonly', true);
								$("#corre_state").attr('tabindex', '-1');
								$("#corre_district").val('${pddata[0][50]}');
								$('#corre_district').change();
								$("#corre_district").attr('readonly', true);
								$("#corre_district").attr('tabindex', '-1');
								$("#corre_pincode").val('${pddata[0][52]}');
								$("#corre_pincode").attr('readonly', true);
								$("#corre_lendmark").val('${pddata[0][53]}');
								$("#corre_lendmark").attr('readonly', true);
							} else {

								$("#check_address3").prop("checked", true);
								$("#corre_house_no").val('${pddata[0][45]}');
								$("#corre_village").val('${pddata[0][46]}');
								$("#corre_postoffice").val('${pddata[0][47]}');
								$("#corre_tehsil").val('${pddata[0][48]}');
								$("#corre_policestation").val(
										'${pddata[0][49]}');
								$("#corre_state").val('${pddata[0][51]}');
								$('#corre_state').change();
								getCorsDistrict();
								$("#corre_district").val('${pddata[0][50]}');
								$('#corre_district').change();
								$("#corre_pincode").val('${pddata[0][52]}');
								$("#corre_lendmark").val('${pddata[0][53]}');
							}

							$("#e_id").val('${pddata[0][33]}');
							$("#cand_prifix").val('${pddata[0][34]}');
							// 	$("#pers_father_title").val('1');
							// 	$("#pers_mother_title").val('1');
							$("#neet_rank").val('${pddata[0][37]}');
							$("#neet_marks").val('${pddata[0][38]}');
							$("#neet_percentile").val('${pddata[0][39]}');
							$("#pers_middel_name").val('${pddata[0][40]}');
							$("#pers_surname").val('${pddata[0][41]}');
							$("#neet_roll_no").val('${pddata[0][43]}');
							$("#neet_application_no").val('${pddata[0][44]}');

							$('#save_btn').hide();
							$('#update_btn').show();

							get_p_id_pers_info();

						} else {
							$('#save_btn').show();
							$('#update_btn').hide();
							//getPerDetails();
							getbesicinfo();
						}
						$('#updatediv').show();
					});

	function get_p_id_pers_info() {
		$.ajaxSetup({
			async : false
		});

		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		var userid = "${userid}";
		$.post('get_p_id_Ncism_pers_info_ctrl?' + key + "=" + value, {
			userid : userid
		}, function(j) {
			//alert(j)
			$("#e_id").val(j[0][0]);
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});

		$.post('get_ayush_id_Ncism_ctrl?' + key + "=" + value, {
			userid : userid
		}, function(j) {
			
			
			console.log(j[0][0])
			
			if (j.length > 0) {
				if (j[0][0] != null && j[0][0]!="") {
					$("#ayush_id").html(j[0][0]);
					 $(".custom-uniqueid").parent().show();
				}
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
	
	function getbesicinfo() {
		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		var userid = "${userid}";
		$.post('getNcism_Besicdetails_ctrl?' + key + "=" + value, {
			userid : userid
		}, function(j) {
			$("#pers_mob_no").val(j[0][0]);
			$("#pers_email").val(j[0][1]);
			$("#hemail").val(j[0][1]);
			$("#pers_name").val(j[0][2]);

			var ad = j[0][3];
			if (ad != null && ad != "" && ad.length == "12") {
				$("#pers_aadhar_no1").val(ad.substring(0, 4));
				$("#pers_aadhar_no2").val(ad.substring(4, 8));
				$("#pers_aadhar_no3").val(ad.substring(8));
				$("#pers_aadhar_no1").attr('readonly', true);
				$("#pers_aadhar_no2").attr('readonly', true);
				$("#pers_aadhar_no3").attr('readonly', true);
			}

			$("#neet_roll_no").val(j[0][4]);
			$("#neet_application_no").val(j[0][5]);
			$("#neet_marks").val(j[0][6]);
			$("#neet_percentile").val(j[0][7]);
			$("#neet_rank").val(j[0][8]);
			$("#pers_date_of_birth").val(j[0][9]);
			calculate_age(j[0][9]);
			//27-01-23
			
			$("#pers_father_name").val(j[0][10]);
			$("#pers_mother_name").val(j[0][11]);
			$("#pers_surname").val(j[0][12]);
			
			if (j[0][13] != null && j[0][13] != "") {
				$("#quota_id").val(j[0][13]);
				$("#quota_id").attr('readonly',true);
				$("#quota_id").attr('tabindex', '-1');
			}
			if (j[0][14] != null && j[0][14] != "") {
				$("#counselling_authority").val(j[0][14]);
				$("#counselling_authority").attr('readonly',true);
				$("#counselling_authority").attr('tabindex', '-1');
			}
			if (j[0][15] != null && j[0][15] != "") {
				$("#pers_category").val(j[0][15]);
				$("#pers_category").attr('readonly',true);
				$("#pers_category").attr('tabindex', '-1');
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}

	function saveData() {

		if ($("#cand_prifix").val() == "0") {
			alert("Please Select Name Prefix");
			$("#cand_prifix").focus();
			return false;
		}

		if ($("#pers_name").val() == "") {
			alert("Please Enter First Name");
			$("#pers_name").focus();
			return false;
		}

		if ($("#pers_middel_name").val() == "") {
			$("#pers_middel_name").val("N/A");
		}
		
		if ($("#pers_surname").val() == "") {
			alert("Please Enter Surname");
			$("#pers_surname").focus();
			return false;
		}

		if ($("#pers_father_name").val() == "") {
			alert("Please Enter Father Name");
			$("#pers_father_name").focus();
			return false;
		}
		if ($("#pers_mother_name").val() == "") {
			alert("Please Enter Mother Name");
			$("#pers_mother_name").focus();
			return false;
		}

		if ($("#pers_gender").val() == "0") {
			alert("Please Select Gender");
			$("#pers_gender").focus();
			return false;
		}
		if ($("#pers_date_of_birth").val() == ""
				|| $("#pers_date_of_birth").val() == "DD/MM/YYYY") {
			alert("Please Select Date of Birth");
			$("#pers_date_of_birth").focus();
			return false;
		}

		var yrr = $("#yrr").val();
		if (yrr < 17 || yrr == "" || yrr == "0") {
			alert("Age Should Be Greater Than 17 Years")
			$("#pers_date_of_birth").focus();
			return false;
		}

		var mob = $("#pers_mob_no");

		if (mob.val().length < 10) {
			alert('Please Enter Valid Number');
			//	 		        $('#'+mob.attr("id")).focus();
			//	 		        $('#'+mob.attr("id")).val("");
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

		if ($("#pers_category").val() == "0") {
			alert("Please Select Category");
			$("#pers_category").focus();
			return false;
		}

		if ($("#pers_religion").val() == "0") {
			alert("Please Select Religion");
			$("#pers_religion").focus();
			return false;
		}
		if ($("#pers_marital_status").val() == "0") {
			alert("Please Select Marital Status");
			$("#pers_marital_status").focus();
			return false;
		}

		if ($("#pers_nationality").val() == "0") {
			alert("Please Select Nationality");
			$("#pers_nationality").focus();
			return false;
		}
		if ($("#state_id").val() == "0") {
			alert("Please Select State");
			$("#state_id").focus();
			return false;
		}
		if ($("#district_id").val() == "0") {
			alert("Please Select District");
			$("#district_id").focus();
			return false;
		}

		if ($("#village").val() == "") {
			alert("Please Enter Village/City");
			$("#village").focus();
			return false;
		}

		var an1 = $("#pers_aadhar_no1").val();
		if (an1 == "") {
			alert("Enter First Four Digit Of Aadhaar No");
			$("#pers_aadhar_no1").focus();
			return false;
		}

		if (parseInt(an1) <= 0) {
			alert("Please Enter Valid First Four Digit of Aadhar No");
			$("#pers_aadhar_no1").focus();
			return false;
		}

		var minLength = 4;
		var charLength = an1.length;
		if (charLength < minLength) {
			alert("Please Enter Valid First Four Digit of Aadhar No");
			$("input#pers_aadhar_no1").focus();
			return false;
		}

		var an2 = $("#pers_aadhar_no2").val();
		if (an2 == "") {
			alert("Enter Second Four Digit Of Aadhaar No");
			$("#pers_aadhar_no2").focus();
			return false;
		}
		if (parseInt(an2) <= 0) {
			alert("Please Enter Valid Second Four Digit Of Aadhar No");
			$("#pers_aadhar_no2").focus();
			return false;
		}

		var minLength = 4;
		var charLength = an2.length;
		if (charLength < minLength) {
			alert("Please Enter Valid Second Four Digit Of Aadhar No");
			$("input#pers_aadhar_no2").focus();
			return false;
		}

		var an3 = $("#pers_aadhar_no3").val();
		if (an3 == "") {
			alert("Enter Last Four Digit Of Aadhaar No");
			$("#pers_aadhar_no3").focus();
			return false;
		}
		if (parseInt(an3) <= 0) {
			alert("Please Enter Valid Last Four Digit Of Aadhar No");
			$("#pers_aadhar_no3").focus();
			return false;
		}
		var minLength = 4;
		var charLength = an3.length;
		if (charLength < minLength) {
			alert("Please Enter Valid Last Four Digit Of Aadhar No");
			$("input#pers_aadhar_no3").focus();
			return false;
		}

		if ($("#quota_id").val() == "0") {
			alert("Please Select Quota");
			$("#quota_id").focus();
			return false;
		}
		if ($("#counselling_authority").val() == "0") {
			alert("Please Select Counselling Authority");
			$("#counselling_authority").focus();
			return false;
		}

		if ($("#pers_permanent_house_no").val().trim() == "") {
			alert("Please Enter the Permanent House No/Name");
			$("#pers_permanent_house_no").focus();
			return false;
		}
		if ($("#pers_permanent_village").val().trim() == "") {
			alert("Please Enter the Permanent Village/City");
			$("#pers_permanent_village").focus();
			return false;
		}
		if ($("#pers_permanent_postoffice").val().trim() == "") {
			alert("Please Enter the Permanent Post Office");
			$("#pers_permanent_postoffice").focus();
			return false;
		}
		if ($("#pers_permanent_tehsil").val().trim() == "") {
			alert("Please Enter the Permanent Tehsil");
			$("#pers_permanent_tehsil").focus();
			return false;
		}
		if ($("#pers_permanent_policestation").val().trim() == "") {
			alert("Please Enter the Permanent Police Station");
			$("#pers_permanent_policestation").focus();
			return false;
		}
		if ($("#pers_permanent_state").val().trim() == "0") {
			alert("Please Select the Permanent State");
			$("#pers_permanent_state").focus();
			return false;
		}
		if ($("#pers_permanent_district").val().trim() == "0") {
			alert("Please Select the Permanent District");
			$("#pers_permanent_district").focus();
			return false;
		}
		
		
		if ($("#pers_permanent_lendmark").val().trim() == "") {
			alert("Please Enter the Permanent Landmark");
			$("#pers_permanent_lendmark").focus();
			return false;
		}
		
		var pin_perm = $("#pers_permanent_pincode").val().trim();

		if (pin_perm == "") {
			alert("Please Enter the Permanent Pincode");
			$("#pers_permanent_pincode").focus();
			return false;
		}

		if (parseInt(pin_perm) <= 0) {
			alert("Please Enter Valid Permanent Pincode");
			$("#pers_permanent_pincode").focus();
			return false;
		}

		var minLength = 6;
		var charLength = pin_perm.length;
		if (charLength < minLength) {
			alert("Permanent Pincode Should Contain Minimum 6 Digit");
			$("input#pers_permanent_pincode").focus();
			return false;
		}

		var maxLength = 6;
		var charLength = pin_perm.length;
		if (charLength > maxLength) {
			alert("Permanent Pincode Should Contain Maximum 6 Digit");
			$("input#pers_permanent_pincode").focus();
			return false;
		}

		

		if (document.getElementById('check_address').checked == false) {
			if ($("#pers_present_house_no").val().trim() == "") {
				alert("Please Enter the Present House No/Name");
				$("#pers_present_house_no").focus();
				return false;
			}
			if ($("#pers_present_village").val().trim() == "") {
				alert("Please Enter the Present Village/City");
				$("#pers_present_village").focus();
				return false;
			}
			if ($("#pers_present_postoffice").val().trim() == "") {
				alert("Please Enter the Present Post Office");
				$("#pers_present_postoffice").focus();
				return false;
			}
			if ($("#pers_present_tehsil").val().trim() == "") {
				alert("Please Enter the Present Tehsil");
				$("#pers_present_tehsil").focus();
				return false;
			}
			if ($("#pers_present_policestation").val().trim() == "") {
				alert("Please Enter the Present Police Station");
				$("#pers_present_policestation").focus();
				return false;
			}
			if ($("#pers_present_state").val().trim() == "0") {
				alert("Please Select the Present State");
				$("#pers_present_state").focus();
				return false;
			}
			if ($("#pers_present_district").val().trim() == "0") {
				alert("Please Select the Present District");
				$("#pers_present_district").focus();
				return false;
			}
			
			if ($("#pers_present_lendmark").val().trim() == "") {
				alert("Please Enter the Present Landmark");
				$("#pers_present_lendmark").focus();
				return false;
			}
			
			if ($("#pers_present_pincode").val().trim() == "") {
				alert("Please Enter the Present Pincode");
				$("#pers_present_pincode").focus();
				return false;
			}

			var pin_pres = $("#pers_present_pincode").val().trim();

			if (pin_pres == "") {
				alert("Please Enter the Present Pincode");
				$("#pers_present_pincode").focus();
				return false;
			}

			if (parseInt(pin_pres) <= 0) {
				alert("Please Enter Valid Present Pincode");
				$("#pers_present_pincode").focus();
				return false;
			}

			var minLength = 6;
			var charLength = pin_pres.length;
			if (charLength < minLength) {
				alert("Present Pincode Should Contain Minimum 6 Digit");
				$("input#pers_present_pincode").focus();
				return false;
			}

			var maxLength = 6;
			var charLength = pin_pres.length;
			if (charLength > maxLength) {
				alert("Present Pincode Should Contain Maximum 6 Digit");
				$("input#pers_present_pincode").focus();
				return false;
			}
		
		}

		if (document.getElementById('check_address').checked == true) {
			if ($("#corre_house_no").val().trim() == "") {
				alert("Please Enter the Corresponding House No/Name");
				$("#corre_house_no").focus();
				return false;
			}
			if ($("#corre_village").val().trim() == "") {
				alert("Please Enter the Corresponding Village/City");
				$("#corre_village").focus();
				return false;
			}
			if ($("#corre_postoffice").val().trim() == "") {
				alert("Please Enter the Corresponding Post Office");
				$("#corre_postoffice").focus();
				return false;
			}
			if ($("#corre_tehsil").val().trim() == "") {
				alert("Please Enter the Corresponding Tehsil");
				$("#corre_tehsil").focus();
				return false;
			}
			if ($("#corre_policestation").val().trim() == "") {
				alert("Please Enter the Corresponding Police Station");
				$("#corre_policestation").focus();
				return false;
			}
			if ($("#corre_state").val().trim() == "0") {
				alert("Please Select the Corresponding State");
				$("#corre_state").focus();
				return false;
			}
			if ($("#corre_district").val().trim() == "0") {
				alert("Please Select the Corresponding District");
				$("#corre_district").focus();
				return false;
			}
			
			if ($("#corre_lendmark").val().trim() == "") {
				alert("Please Enter the Corresponding Landmark");
				$("#corre_lendmark").focus();
				return false;
			}

			var pin_pre = $("#corre_pincode").val().trim();

			if (pin_pre == "") {
				alert("Please Enter the Corresponding Pincode");
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
		 var redval = 	$('input[name="check_add"]:checked').val();
		 if(redval == undefined || redval == 'undefined'){
			 alert("Please Select the Radio Button");
			 return false;
		}	
		
//	 		if(document.getElementById('check_address3').checked == true) { 
			if ($("#corre_house_no").val().trim() == "") {
				alert("Please Enter the Corresponding House No/Name");
				$("#corre_house_no").focus();
				return false;
			} 
		    if ($("#corre_village").val().trim() == "") {
				alert("Please Enter the Corresponding Village/City");
				$("#corre_village").focus();
				return false;
			} 
		    if ($("#corre_postoffice").val().trim() == "") {
				alert("Please Enter the Corresponding Post Office");
				$("#corre_postoffice").focus();
				return false;
			} 
		    if ($("#corre_tehsil").val().trim() == "") {
				alert("Please Enter the Corresponding Tehsil");
				$("#corre_tehsil").focus();
				return false;
			} 
		    if ($("#corre_policestation").val().trim() == "") {
				alert("Please Enter the Corresponding Police Station");
				$("#corre_policestation").focus();
				return false;
			} 
		    if ($("#corre_state").val().trim() == "0") {
				alert("Please Select the Corresponding State");
				$("#corre_state").focus();
				return false;
			}
		    if ($("#corre_district").val().trim() == "0") {
				alert("Please Select the Corresponding District");
				$("#corre_district").focus();
				return false;
			}
			if ($("#corre_lendmark").val().trim() == "") {
				alert("Please Enter the Corresponding Landmark");
				$("#corre_lendmark").focus();
				return false;
			}
		    var pin_pre =$("#corre_pincode").val().trim();
			 if (pin_pre == "") {
					alert("Please Enter the Corresponding Pincode");
					$("#corre_pincode").focus();
					return false;
				} 
			 if(parseInt(pin_pre) <= 0){
					alert("Please Enter Valid Corresponding Pincode");
					$("#corre_pincode").focus();
					return false;
			   	}
			 var minLength = 6;
			 var charLength = pin_pre.length;
		       if(charLength < minLength){
		       	alert("Corresponding Pincode Should Contain Minimum 6 Digit");
					$("input#corre_pincode").focus();
					return false;
		       }
			  var maxLength = 6;
				 var charLength = pin_pre.length;
			       if(charLength > maxLength){
			       	alert("Corresponding Pincode Should Contain Maximum 6 Digit");
						$("input#corre_pincode").focus();
						return false;
			       }
//		}
		var nrno = $("#neet_roll_no").val().trim();

		if (nrno == "") {
			alert("Please Enter NEET Roll No");
			$("#neet_roll_no").focus();
			return false;
		}
		if (parseInt(nrno) <= 0) {
			alert("Please Enter Valid NEET Roll No");
			$("#neet_roll_no").focus();
			return false;
		}
		var maxLength = 10;
		var charLength = nrno.length;

		if (charLength > maxLength) {
			alert("NEET Roll No Should Contain Maximum 10 Characters");
			$("input#neet_roll_no").focus();
			return false;
		}

		var nano = $("#neet_application_no").val().trim();
		if (nano == "") {
			alert("Please Enter NEET Application No");
			$("#neet_application_no").focus();
			return false;
		}
		if (parseInt(nano) <= 0) {
			alert("Please Enter Valid NEET Application No");
			$("#neet_application_no").focus();
			return false;
		}
		var maxLength = 12;
		var charLength = nano.length;
		if (charLength > maxLength) {
			alert("NEET Application No Should Contain Maximum 12 Characters");
			$("input#neet_application_no").focus();
			return false;
		}

		var nr = $("#neet_rank").val().trim();
		if (nr == "") {
			alert("Please Enter NEET All India Rank");
			$("#neet_rank").focus();
			return false;
		}

		if (parseInt(nr) <= 0) {
			alert("Please Enter Valid NEET All India Rank");
			$("#neet_rank").focus();
			return false;
		}

		var maxLength = 7;
		var charLength = nr.length;
		if (charLength > maxLength) {
			alert("NEET All India Rank Should Contain Maximum 7 Characters");
			$("input#neet_rank").focus();
			return false;
		}

		var nm = $("#neet_marks").val().trim();
		if (nm == "") {
			alert("Please Enter NEET Marks");
			$("#neet_marks").focus();
			return false;
		}
		if (nm <= 0) {
			alert("Please Enter Valid NEET Marks");
			$("#neet_marks").focus();
			return false;
		}
		var maxLength = 3;
		var charLength = nm.length;
		if (charLength > maxLength) {
			alert("NEET Marks Contain Maximum 3 Characters");
			$("input#neet_marks").focus();
			return false;
		}

		var om = $("#neet_percentile").val().trim()
		if (om == "") {
			alert("Please Enter NEET Percentile");
			$("#neet_percentile").focus();
			return false;
		}
		if (parseFloat(om) <= 0) {
			alert("Please Enter Valid NEET Percentile");
			$("#neet_percentile").focus();
			return false;
		}

		var maxLength = 5;
		var charLength = om.length;
		if (charLength > maxLength) {
			alert("NEET Percentile Contain Maximum 5 Characters");
			$("input#neet_percentile").focus();
			return false;
		}

		if (parseFloat(om) > 100) {
			alert("Percentage Of Marks Can't Have More Than 100 Percent");
			$("#neet_percentile").focus();
			return false;
		}

		if ($("#e_id").val() == 0) {
			return true;
		} else {
			var key = "${_csrf.parameterName}";
			var value = "${_csrf.token}";
			var e_id = $("#e_id").val();
			var pers_name = $("#pers_name").val();
			var pers_father_name = $("#pers_father_name").val();
			var pers_mother_name = $("#pers_mother_name").val();
			var pers_date_of_birth = $("#pers_date_of_birth").val();
			var pers_gender = $("#pers_gender").val();
			var pers_mob_no = $("#pers_mob_no").val();
			var pers_email = $("#hemail").val();
			var pers_category = $("#pers_category").val();
			var pers_religion = $("#pers_religion").val();
			var pers_marital_status = $("#pers_marital_status").val();
			var pers_nationality = $("#pers_nationality").val();
			var state_id = $("#state_id").val();
			var district_id = $("#district_id").val();
			var village = $("#village").val();
			var pers_aadhar_no1 = $("#pers_aadhar_no1").val();
			var pers_aadhar_no2 = $("#pers_aadhar_no2").val();
			var pers_aadhar_no3 = $("#pers_aadhar_no3").val();

			var quota_id = $("#quota_id").val();
			var counselling_authority = $("#counselling_authority").val();

			//var pers_aadhar_no = a1+a2+a3;
			var pers_permanent_house_no = $("#pers_permanent_house_no").val();
			var pers_permanent_village = $("#pers_permanent_village").val();
			var pers_permanent_postoffice = $("#pers_permanent_postoffice")
					.val();
			var pers_permanent_tehsil = $("#pers_permanent_tehsil").val();
			var pers_permanent_policestation = $(
					"#pers_permanent_policestation").val();
			var pers_permanent_district = $("#pers_permanent_district").val();
			var pers_permanent_state = $("#pers_permanent_state").val();
			var pers_permanent_pincode = $("#pers_permanent_pincode").val();
			var pers_permanent_lendmark = $("#pers_permanent_lendmark").val();
			var pers_present_house_no = $("#pers_present_house_no").val();
			var pers_present_village = $("#pers_present_village").val();
			var pers_present_postoffice = $("#pers_present_postoffice").val();
			var pers_present_tehsil = $("#pers_present_tehsil").val();
			var pers_present_policestation = $("#pers_present_policestation")
					.val();
			var pers_present_district = $("#pers_present_district").val();
			var pers_present_state = $("#pers_present_state").val();
			var pers_present_pincode = $("#pers_present_pincode").val();
			var pers_present_lendmark = $("#pers_present_lendmark").val();
			var cand_prifix = $("#cand_prifix").val();
			var pers_father_title = $("#pers_father_title").val();
			var pers_mother_title = $("#pers_mother_title").val();
			var neet_roll_no = $("#neet_roll_no").val();
			var neet_application_no = $("#neet_application_no").val();
			var neet_rank = $("#neet_rank").val();
			var neet_marks = $("#neet_marks").val();
			var neet_percentile = $("#neet_percentile").val();
			var pers_middel_name = $("#pers_middel_name").val();
			var pers_surname = $("#pers_surname").val();

			var corre_house_no = $("#corre_house_no").val();
			var corre_village = $("#corre_village").val();
			var corre_postoffice = $("#corre_postoffice").val();
			var corre_tehsil = $("#corre_tehsil").val();
			var corre_policestation = $("#corre_policestation").val();
			var corre_district = $("#corre_district").val();
			var corre_state = $("#corre_state").val();
			var corre_pincode = $("#corre_pincode").val();
			var corre_lendmark = $("#corre_lendmark").val();

			$.post('getUpdateNcism_PerDetails?' + key + "=" + value, {
				e_id : e_id,
				pers_name : pers_name,
				pers_father_name : pers_father_name,
				pers_mother_name : pers_mother_name,
				pers_date_of_birth : pers_date_of_birth,
				pers_gender : pers_gender,
				pers_mob_no : pers_mob_no,
				pers_email : pers_email,
				pers_category : pers_category,
				pers_religion : pers_religion,
				pers_marital_status : pers_marital_status,
				pers_nationality : pers_nationality,
				state_id : state_id,
				district_id : district_id,
				village : village,
				pers_aadhar_no1 : pers_aadhar_no1,
				pers_aadhar_no2 : pers_aadhar_no2,
				pers_aadhar_no3 : pers_aadhar_no3,
				quota_id : quota_id,
				counselling_authority : counselling_authority,
				pers_permanent_house_no : pers_permanent_house_no,
				pers_permanent_village : pers_permanent_village,
				pers_permanent_postoffice : pers_permanent_postoffice,
				pers_permanent_tehsil : pers_permanent_tehsil,
				pers_permanent_policestation : pers_permanent_policestation,
				pers_permanent_district : pers_permanent_district,
				pers_permanent_state : pers_permanent_state,
				pers_permanent_pincode : pers_permanent_pincode,
				pers_permanent_lendmark : pers_permanent_lendmark,
				pers_present_house_no : pers_present_house_no,
				pers_present_village : pers_present_village,
				pers_present_postoffice : pers_present_postoffice,
				pers_present_tehsil : pers_present_tehsil,
				pers_present_policestation : pers_present_policestation,
				pers_present_district : pers_present_district,
				pers_present_state : pers_present_state,
				pers_present_pincode : pers_present_pincode,
				pers_present_lendmark : pers_present_lendmark,
				cand_prifix : cand_prifix,
				pers_father_title : pers_father_title,
				pers_mother_title : pers_mother_title,
				neet_rank : neet_rank,
				neet_marks : neet_marks,
				neet_percentile : neet_percentile,
				pers_middel_name : pers_middel_name,
				pers_surname : pers_surname,
				yrr : yrr,
				neet_roll_no : neet_roll_no,
				neet_application_no : neet_application_no,
				corre_house_no : corre_house_no,
				corre_village : corre_village,
				corre_postoffice : corre_postoffice,
				corre_tehsil : corre_tehsil,
				corre_policestation : corre_policestation,
				corre_district : corre_district,
				corre_state : corre_state,
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
			alert('Please Enter Valid Number');
			$('#' + obj.id).focus();
			$('#' + obj.id).val("");
			return false;
		}
		_mobile = obj.value;
		var regExp = /^[6789]\d{9}$/;
		if (_mobile == '' || !regExp.test(_mobile)) {
			alert('Please Enter Number Start with 6,7,8,9 Digit');
			$('#' + obj.id).focus();
			$('#' + obj.id).val("");
			return false;
		}
	}

	function copy_address() {

		if ($("#check_address").is(":checked") == true) {
			$("#pers_present_house_no")
					.val($("#pers_permanent_house_no").val());
			$("#pers_present_house_no").attr('readonly', true);

			$("#pers_present_village").val($("#pers_permanent_village").val());
			$("#pers_present_village").attr('readonly', true);

			$("#pers_present_postoffice").val(
					$("#pers_permanent_postoffice").val());
			$("#pers_present_postoffice").attr('readonly', true);

			$("#pers_present_tehsil").val($("#pers_permanent_tehsil").val());
			$("#pers_present_tehsil").attr('readonly', true);

			$("#pers_present_policestation").val(
					$("#pers_permanent_policestation").val());
			$("#pers_present_policestation").attr('readonly', true);

			$("#pers_present_state").val($("#pers_permanent_state").val());
			$("#pers_present_state").attr('readonly', true);
			$('#pers_present_state').change();
			getpresDistrict();
			$("#pers_present_state").attr('tabindex', '-1');

			$("#pers_present_district")
					.val($("#pers_permanent_district").val());
			$('#pers_present_district').change();
			$("#pers_present_district").attr('readonly', true);
			$("#pers_present_district").attr('tabindex', '-1');

			$("#pers_present_pincode").val($("#pers_permanent_pincode").val());
			$("#pers_present_pincode").attr('readonly', true);

			$("#pers_present_lendmark")
					.val($("#pers_permanent_lendmark").val());
			$("#pers_present_lendmark").attr('readonly', true);

		}

		if ($("#check_address").is(":checked") == false) {
			$("#pers_present_house_no").val("");
			$("#pers_present_house_no").attr('readonly', false);

			$("#pers_present_village").val("");
			$("#pers_present_village").attr('readonly', false);

			$("#pers_present_postoffice").val("");
			$("#pers_present_postoffice").attr('readonly', false);

			$("#pers_present_tehsil").val("");
			$("#pers_present_tehsil").attr('readonly', false);

			$("#pers_present_policestation").val("");
			$("#pers_present_policestation").attr('readonly', false);

			$("#pers_present_state").val("0");
			$('#pers_present_state').change();
			$("#pers_present_state").attr('readonly', false);
			getpresDistrict();
			$("#pers_present_state").attr('tabindex', '0');
			$("#pers_present_district").val("0");
			$('#pers_present_district').change();
			$("#pers_present_district").attr('readonly', false);
			$("#pers_present_district").attr('tabindex', '0');
			$("#pers_present_pincode").val("");
			$("#pers_present_pincode").attr('readonly', false);
			$("#pers_present_lendmark").val("");
			$("#pers_present_lendmark").attr('readonly', false);

		}
	}

	function changeAddress() {

		if ($("#check_address").is(":checked") == true) {
			$("#check_address").prop("checked", false);

			$("#pers_present_house_no").val("");
			$('#pers_present_house_no').attr('readonly', false);

			$("#pers_present_village").val("");
			$('#pers_present_village').attr('readonly', false);

			$("#pers_present_postoffice").val("");
			$('#pers_present_postoffice').attr('readonly', false);

			$("#pers_present_tehsil").val("");
			$('#pers_present_tehsil').attr('readonly', false);

			$("#pers_present_policestation").val("");
			$('#pers_present_policestation').attr('readonly', false);

			$("#pers_present_state").val("0");
			$('#pers_present_state').attr('readonly', false);
			$("#pers_present_state").attr('tabindex', '0');

			$("select#pers_present_district").val("0");
			$('select#pers_present_district').attr('readonly', false);
			$("#pers_present_district").attr('tabindex', '0');

			$("#pers_present_pincode").val("");
			$("#pers_present_pincode").attr('readonly', false);

			$("#pers_present_lendmark").val("");
			$("#pers_present_lendmark").attr('readonly', false);
		}
		
		var samas = $('input[type=radio][name=check_add]:checked').attr('id');
		if (samas != "check_address3") {
			$('#check_address3').click();
		}
	}

	/* function getPreviousPage()
	 {  
	 $("#pers_exper_hid").val("${pers_adv_details_session}");
	 document.getElementById("mainForm11").submit();
	 } */

	function getPage() {
		$("#pers_exper_hid").val("${pers_adv_details_session}");
		document.getElementById("applicationUrlForm4").submit();
	}

	function getEduPage() {
		if('${pddata}' != "[]"){
		$("#eid").val($("#e_id").val());
		$("#pers_exper_hid").val("${pers_adv_details_session}");
		document.getElementById("mainForm11").submit();
		}else {
			alert('Please Save Personal Details Form First');
			return false;
		}
	}

	function getExpPage() {
		$("#tp_eid").val($("#e_id").val());
		$("#pers_exper_hid").val("${pers_adv_details_session}");
		document.getElementById("applicationUrlForm4").submit();
	}

	function getDocPage() {
		$("#doc_eid").val($("#e_id").val());
		$("#pers_exper_hid").val("${pers_adv_details_session}");
		document.getElementById("applicationUrlForm5").submit();
	}

	function getchoicePage() {
		$("#ch_eid").val($("#e_id").val());
		$("#pers_exper_hid").val("${pers_adv_details_session}");
		document.getElementById("applicationUrlForm6").submit();
	}

	function getState() {
		$.ajaxSetup({
			async : false
		});
		var selval = $("#pers_nationality").val();
		$
				.post(
						"getStateUrl?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {

							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i].state_id + '" name="'+j[i].state_id+'" >'
										+ j[i].state_name + '</option>';
							}
							$("select#state_id").html(options);
						});
	}
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
		var selval = $("#pers_permanent_state").val();
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
							$("select#pers_permanent_district").html(options);
						});
	}
	function getpresDistrict() {
		$.ajaxSetup({
			async : false
		});
		var selval = $("#pers_present_state").val();
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
							$("select#pers_present_district").html(options);
						});
	}

	function getCorsDistrict() {
		$.ajaxSetup({
			async : false
		});

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
			$("#pers_date_of_birth").focus();
			return false;
		}
	}

	function lengthadhar() {
		document.getElementById("pers_aadhar_no1").maxLength = "4";
		document.getElementById("pers_aadhar_no1").minLength = "4";
		document.getElementById("pers_aadhar_no2").maxLength = "4";
		document.getElementById("pers_aadhar_no2").minLength = "4";
		document.getElementById("pers_aadhar_no3").maxLength = "4";
		document.getElementById("pers_aadhar_no3").minLength = "4";
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

			$("#corre_house_no").val($("#pers_permanent_house_no").val());
			$("#corre_house_no").attr('readonly', true);

			$("#corre_village").val($("#pers_permanent_village").val());
			$("#corre_village").attr('readonly', true);

			$("#corre_postoffice").val($("#pers_permanent_postoffice").val());
			$("#corre_postoffice").attr('readonly', true);

			$("#corre_tehsil").val($("#pers_permanent_tehsil").val());
			$("#corre_tehsil").attr('readonly', true);

			$("#corre_policestation").val(
					$("#pers_permanent_policestation").val());
			$("#corre_policestation").attr('readonly', true);

			$("#corre_state").val($("#pers_permanent_state").val());
			$("#corre_state").change();
			$("#corre_state").attr('readonly', true);
			$("#corre_state").attr('tabindex', '-1');

			$("#corre_district").val($("#pers_permanent_district").val());
			$("#corre_district").change();
			$("#corre_district").attr('readonly', true);
			$("#corre_district").attr('tabindex', '-1');

			$("#corre_pincode").val($("#pers_permanent_pincode").val());
			$("#corre_pincode").attr('readonly', true);

			$("#corre_lendmark").val($("#pers_permanent_lendmark").val());
			$("#corre_lendmark").attr('readonly', true);
			$("#chk_add_val").val("0");
		}

		if ($("#check_address1").is(":checked") == false) {
			$("#corre_house_no").val("");
			$("#corre_house_no").attr('readonly', false);

			$("#corre_village").val("");
			$("#corre_village").attr('readonly', false);

			$("#corre_postoffice").val("");
			$("#corre_postoffice").attr('readonly', false);

			$("#corre_tehsil").val("");
			$("#corre_tehsil").attr('readonly', false);

			$("#corre_policestation").val("");
			$("#corre_policestation").attr('readonly', false);

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

			$("#corre_house_no").val($("#pers_present_house_no").val());
			$("#corre_house_no").attr('readonly', true);

			$("#corre_village").val($("#pers_present_village").val());
			$("#corre_village").attr('readonly', true);

			$("#corre_postoffice").val($("#pers_present_postoffice").val());
			$("#corre_postoffice").attr('readonly', true);

			$("#corre_tehsil").val($("#pers_present_tehsil").val());
			$("#corre_tehsil").attr('readonly', true);

			$("#corre_policestation").val(
					$("#pers_present_policestation").val());
			$("#corre_policestation").attr('readonly', true);

			$("#corre_state").val($("#pers_present_state").val());
			$("#corre_state").change();
			$("#corre_state").attr('readonly', true);
			$("#corre_state").attr('tabindex', '-1');
			getCorsDistrict();
			$("#corre_district").val($("#pers_present_district").val());
			$("#corre_district").change();
			$("#corre_district").attr('readonly', true);
			$("#corre_district").attr('tabindex', '-1');

			$("#corre_pincode").val($("#pers_present_pincode").val());
			$("#corre_pincode").attr('readonly', true);

			$("#corre_lendmark").val($("#pers_present_lendmark").val());
			$("#corre_lendmark").attr('readonly', true);
			$("#chk_add_val").val("1");

		}

		if ($("#check_address2").is(":checked") == false) {
			$("#corre_house_no").val("");
			$("#corre_house_no").attr('readonly', false);

			$("#corre_village").val("");
			$("#corre_village").attr('readonly', false);

			$("#corre_postoffice").val("");
			$("#corre_postoffice").attr('readonly', false);

			$("#corre_tehsil").val("");
			$("#corre_tehsil").attr('readonly', false);

			$("#corre_policestation").val("");
			$("#corre_policestation").attr('readonly', false);

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

		$("#corre_postoffice").val("");
		$("#corre_postoffice").attr('readonly', false);

		$("#corre_tehsil").val("");
		$("#corre_tehsil").attr('readonly', false);

		$("#corre_policestation").val("");
		$("#corre_policestation").attr('readonly', false);

		$("#corre_state").val("0");
		$("#corre_state").change();
		$("#corre_state").attr('readonly', false);

		$("#corre_district").val("0");
		$("#corre_district").change();
		$("#corre_district").attr('readonly', false);

		$("#corre_pincode").val("");
		$("#corre_pincode").attr('readonly', false);

		$("#corre_lendmark").val("");
		$("#corre_lendmark").attr('readonly', false);

		$("#chk_add_val").val("2");

	}

	function changeCurrespongAddress() {
		if (document.getElementById('check_address2').checked == true) {
			$("#check_address2").prop("checked", false);
			$("#curr_address").val("");
			$('#curr_address').attr('readonly', false);
			$("#curr_address2").val("");
			$('#curr_address2').attr('readonly', false);
			$("#curr_address3").val("");
			$('#curr_address3').attr('readonly', false);
			$("#curr_state").val("0");
			$("#curr_state").attr('readonly', false);
			$("#curr_district").val("0");
			$("#curr_district").attr('readonly', false);
			$("#curr_pincode").val("");
			$('#curr_pincode').attr('readonly', false);

		}
	}

	// csp----------------------------

	document
			.addEventListener(
					'DOMContentLoaded',
					function() {

						document.getElementById('save_btn').onclick = function() {
							return saveData();
						};
						document.getElementById('update_btn').onclick = function() {
							// 			if(confirm('Are you sure you want to Proceed?')){return saveData(); }else{return false;}
							return saveData();
						};

						document.getElementById('pers_name').onkeypress = function() {
							return onlyAlphabetsStringSpace(event, this);
						};
												
						document.getElementById('pers_middel_name').onkeypress = function () {
							return onlyAlphabetsStringSpacewithslash(event,this);
						};
						
						document.getElementById('pers_surname').onkeypress = function() {
							return onlyAlphabetsStringSpace(event, this);
						};
						
						document.getElementById('pers_father_name').onkeypress = function() {
							return onlyAlphabetsStringSpace(event, this);
						};
						document.getElementById('pers_mother_name').onkeypress = function() {
							return onlyAlphabetsStringSpace(event, this);
						};
						document.getElementById('pers_date_of_birth').onclick = function() {
							return clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById('pers_date_of_birth').onfocus = function() {
							this.style.color = '#000000';
						};
						document.getElementById('pers_date_of_birth').onblur = function() {
							clickrecall(this, 'DD/MM/YYYY');
							validateDate_BackDate(this.value, this);
						};
						document.getElementById('pers_date_of_birth').onkeyup = function() {
							return clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById('pers_date_of_birth').onchange = function() {
							clickrecall(this, 'DD/MM/YYYY');
							validateDate_FutureDate(this.value, this);
							calculate_age(this.value);
						};
						document.getElementById('pers_mob_no').onkeypress = function() {
							return isNumberKey0to9(event);
						};
						document.getElementById('pers_mob_no').onchange = function() {
							return mobileNumber(this);
						};
						document.getElementById('pers_nationality').onchange = function() {
							return getState();
						};
						document.getElementById('state_id').onchange = function() {
							return getDistrict();
						};
						document.getElementById('village').onkeypress = function() {
							return onlyAlphabetsStringSpace(event, this);
						};
						document.getElementById('pers_aadhar_no1').onkeypress = function() {
							lengthadhar();
							return isNumberKey0to9(event);
						};
						document.getElementById('pers_aadhar_no1').onfocus = function() {
							return this.select();
						};
						document.getElementById('pers_aadhar_no1').onchange = function() {
							return checkLength(this.id, 4);
						};
						document.getElementById('pers_aadhar_no1').onkeyup = function() {
							return movetoNext(this, 'pers_aadhar_no2');
						};
						document.getElementById('pers_aadhar_no2').onfocus = function() {
							return this.select();
						};
						document.getElementById('pers_aadhar_no2').onkeypress = function() {
							lengthadhar();
							return isNumberKey0to9(event);
						};
						document.getElementById('pers_aadhar_no2').onchange = function() {
							return checkLength(this.id, 4);
						};
						document.getElementById('pers_aadhar_no2').onkeyup = function() {
							return movetoNext(this, 'pers_aadhar_no3');
						};
						document.getElementById('pers_aadhar_no3').onkeypress = function() {
							lengthadhar();
							return isNumberKey0to9(event);
						};
						document.getElementById('pers_aadhar_no3').onfocus = function() {
							return this.select();
						};
						document.getElementById('pers_aadhar_no3').onchange = function() {
							return checkLength(this.id, 4);
						};

						document.getElementById('pers_permanent_house_no').onkeypress = function() {
							changeAddress();
							return onlyAlphaNumericwithslash(event,this);
						};

						document.getElementById('pers_permanent_village').onkeypress = function() {
							changeAddress();
							return onlyAlphabetsStringSpace(event, this);
						};

						document.getElementById('pers_permanent_postoffice').onkeypress = function() {
							changeAddress();
							return onlyAlphaNumeric(event, this);
						};

						document.getElementById('pers_permanent_tehsil').onkeypress = function() {
							changeAddress();
							return onlyAlphabetsStringSpace(event, this);
						};

						document.getElementById('pers_permanent_policestation').onkeypress = function() {
							changeAddress();
							return onlyAlphaNumeric(event, this);
						};
						document.getElementById('pers_permanent_state').onchange = function() {
							changeAddress();
							getpermDistrict();
						};
						document.getElementById('pers_permanent_district').onchange = function() {
							changeAddress();
						};
						document.getElementById('pers_permanent_pincode').onchange = function() {
							return checkLength(this.id, 6);
						};
						document.getElementById('pers_permanent_pincode').onkeypress = function() {
							changeAddress();
							return isNumberKey0to9(event);
						};
						document.getElementById('pers_permanent_lendmark').onkeypress = function() {
							changeAddress();
							return onlyAlphabetsStringSpace(event, this);
						};
						document.getElementById('check_address').onclick = function() {
							return copy_address();
						};
						document.getElementById('pers_present_house_no').onkeypress = function() {
							return onlyAlphaNumericwithslash(event,this);
						};
						document.getElementById('pers_present_village').onkeypress = function() {
							return onlyAlphabetsStringSpace(event, this);
						};
						document.getElementById('pers_present_postoffice').onkeypress = function() {
							changeAddress();
							return onlyAlphaNumeric(event, this);
						};
						document.getElementById('pers_present_tehsil').onkeypress = function() {
							return onlyAlphabetsStringSpace(event, this);
						};
						document.getElementById('pers_present_policestation').onkeypress = function() {
							changeAddress();
							return onlyAlphaNumeric(event, this);
						};

						document.getElementById('pers_present_state').onchange = function() {
							return getpresDistrict();
						};
						// 	document.getElementById('pers_present_pincode').onchange = function() {
						// 		return checkLength(this.id,6);
						// 	};
						document.getElementById('pers_present_pincode').onkeypress = function() {
							return isNumberKey0to9(event);
						};
						document.getElementById('pers_present_lendmark').onkeypress = function() {
							return onlyAlphabetsStringSpace(event, this);
						};
						
						document.getElementById('corre_house_no').onkeypress = function() {
							return onlyAlphaNumericwithslash(event,this);
						};
						document.getElementById('corre_village').onkeypress = function() {
							return onlyAlphabetsStringSpace(event, this);
						};
						document.getElementById('corre_postoffice').onkeypress = function() {
							return onlyAlphaNumeric(event, this);
						};
						document.getElementById('corre_tehsil').onkeypress = function() {
							return onlyAlphabetsStringSpace(event, this);
						};
						document.getElementById('corre_policestation').onkeypress = function() {
							return onlyAlphaNumeric(event, this);
						};
						document.getElementById('corre_state').onchange = function() {
							getCorsDistrict();
						};
						document.getElementById('corre_pincode').onchange = function() {
							return checkLength(this.id, 6);
						};
						document.getElementById('corre_lendmark').onkeypress = function() {
							return onlyAlphabetsStringSpace(event, this);
						};
						document.getElementById('corre_pincode').onkeypress = function() {
							return isNumberKey0to9(event);
						};
						
						document.getElementById('neet_roll_no').onkeypress = function() {
							return isNumberKey0to9(event);
						};
						document.getElementById('neet_application_no').onkeypress = function() {
							return isNumberKey0to9(event);
						};
						document.getElementById('neet_rank').onkeypress = function() {
							return isNumberKey0to9(event);
						};
						document.getElementById('neet_marks').onkeypress = function() {
							return isNumberKey0to9(event);
						};
						document.getElementById('neet_percentile').onkeypress = function() {
							return isNumberKeydecimal(this, event);
						};
						document.getElementById('tunnel_2').onclick = function() {
							// 		 if(confirm('Are you sure you want to Proceed?')){getEduPage();}else{return false;}
							getEduPage();
						};
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
				

					});
</script>
