<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link href="js/tunneldesign/tunnelold.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Declaration</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a id="coman"
									href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Declaration</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		
		<div class="form-elements-wrapper preview-form">
			<form:form name="student_declar" id="student_declar_view"
				action="Student_Personal_Ncism_Details_Action" method="post"
				 modelAttribute="student_declar_ncismviewCMD">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card-style mb-30" id="card_view">
							<div class="tunnel_design">
								<div class="square tunnel_visited">
									<a href="#" class="tunnel_text" id="tunnel_1"> Personal
										Details</a>
								</div>
								<div class="square tunnel_visited">
									<a href="#" class="tunnel_text" id="tunnel_2"
										href="Edu_Det_Ncism_Url"> Basic Education Details</a>
								</div>
								<div class="square tunnel_visited">
									<a href="#" class="tunnel_text" id="tunnel_3"
										href="doc_uploadNcism_Url"> Upload Document</a>
								</div>
								<div class="square tunnel_active">
									<h5 class="tunnel_text">Declaration</h5>
								</div>
							</div>
						</div>
						<div class="card-style mb-30" id="card_view">
							<div class="field-box">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
										<div class="auto-fill-form">
											<div class="upload_image">
												<img id="upload_photo_doc_preview" class="" />
											</div>
										</div>
										<!-- <div class="custom-data-value custom-title custom-title-bb">
										<h4 class="custom-title-tag">Student Detail Form </h4>
									</div> -->
									</div>
								</div>
							</div>
							<div class="field-box">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="custom-data-value custom-title custom-title-bg">
										<h5 class="custom-title-tag">Personal Details</h5>
									</div>
								</div>

								<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value">
										<label>Name of Applicant</label> <span class="value-bind"
											id="pers_name_label"> </span> <input type="hidden"
											id="pers_name" name="pers_name" class="form-control"
											autocomplete="off" maxlength="25"> <input
											type="hidden" id="viewid" name="viewid" class="form-control"
											value="0" autocomplete="off"> <select hidden="hidden"
											id="cand_prifix" name="cand_prifix" class="form-control"
											onchange="">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getPrefixList}"
												varStatus="num">
												<option value="${item.id}" name="${item.id}">${item.prefix}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value">
										<label>Middle Name </label> <span class="value-bind"
											id="pers_middel_name_label"></span> <input type="hidden"
											id="pers_middel_name" name="pers_middel_name"
											class="form-control" autocomplete="off" maxlength="25">
									</div>
								</div>

								<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value">
										<label>Surname </label> <span class="value-bind"
											id="pers_surname_label"></span> <input type="hidden"
											id="pers_surname" name="pers_surname" class="form-control"
											autocomplete="off" maxlength="25">
									</div>
								</div>


								<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value">
										<label>Father's Name </label> <span class="value-bind"
											id="pers_father_name_label"></span> <input type="hidden"
											id="pers_father_name" name="pers_father_name"
											class="form-control" autocomplete="off" maxlength="25">
									</div>
								</div>

								<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value">
										<label>Mother's Name </label> <span class="value-bind"
											id="pers_mother_name_label"></span> <input type="hidden"
											id="pers_mother_name" name="pers_mother_name"
											class="form-control" autocomplete="off" maxlength="25">
									</div>
								</div>
								<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value">
										<label>Date of Birth </label> <span class="value-bind"
											id="pers_date_of_birth_label"></span> <input type="hidden"
											id="pers_date_of_birth" name="pers_date_of_birth"
											class="form-control" autocomplete="off" maxlength="25">
									</div>
								</div>
								<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value">
										<label>Gender</label> <span class="value-bind"
											id="pers_gender_label"></span> <select name="pers_gender"
											id="pers_gender" class="form-control custom-d-none">
											<option value="0">-- Select --</option>
											<c:forEach var="item" items="${getgenderList}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value">
										<label>Mobile Number </label> <span class="value-bind"
											id="pers_mob_no_label"></span> <input type="hidden"
											id="pers_mob_no" name="pers_mob_no" class="form-control"
											autocomplete="off" maxlength="25">

									</div>
								</div>
								<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value">
										<label>E-Mail ID </label> <span class="value-bind"
											id="pers_email_label"></span> <input type="hidden"
											id="pers_email" name="pers_email" class="form-control"
											autocomplete="off" maxlength="25">

									</div>
								</div>
								<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value">
										<label>Category </label> <span class="value-bind"
											id="pers_category_label"></span> <select type="hidden"
											name="pers_category" id="pers_category"
											class="form-control custom-d-none">
											<option value="0" selected="selected">-- Select --</option>
											<c:forEach var="item" items="${getcategorylist}"
												varStatus="num">
												<option value="${item.id}" name="${item.category}">${item.category}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value">
										<label>Religion </label> <span class="value-bind"
											id="pers_religion_label"></span> <select hidden="hidden"
											name="pers_religion" id="pers_religion"
											class="form-control custom-d-none">
											<c:forEach var="item" items="${getreligionListdata}"
												varStatus="num">
												<option value="${item.id}" name="${item.religion}">${item.religion}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value">
										<label>Marital Status </label> <span class="value-bind"
											id="pers_marital_status_label"></span> <select
											hidden="hidden" name="pers_marital_status"
											id="pers_marital_status" class="form-control custom-d-none">
											<option value="0" selected="selected">-- Select
												Marital Status --</option>
											<c:forEach var="item" items="${getmsList}" varStatus="num">
												<option value="${item.id}" name="${item.marital_status}">${item.marital_status}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value">
										<label>Nationality </label> <span class="value-bind"
											id="pers_nationality_label"></span> <select hidden="hidden"
											name="pers_nationality" id="pers_nationality"
											class="form-control custom-d-none">
											<option value="0" selected="selected">-- Select --</option>
											<c:forEach var="item" items="${getMedCountryName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value">
										<label>State/UT (Domicile) </label> <span class="value-bind"
											id="state_id_label"></span> <select hidden="hidden"
											name="state_id" id="state_id" class="form-control custom-d-none">
											<option value="0" selected="selected">-- Select
												State --</option>
											<c:forEach var="item" items="${getMedStateName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value">
										<label>District </label> <span class="value-bind"
											id="district_id_label"></span> <select hidden="hidden"
											name="district_id" id="district_id"
											class="form-control custom-d-none">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value">
										<label>Village </label> <span class="value-bind"
											id="village_label"></span> <select hidden="hidden"
											name="village" id="village" class="form-control custom-d-none">
											<option value="0" selected="selected">-- Select
												village --</option>
											<option value="1">Visnagar</option>
											<option value="2">Mansa</option>
											<option value="3">Kalol</option>
											<option value="3">Chandkheda</option>
										</select>
									</div>
								</div>
								<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value">
										<label>Aadhaar Number </label> <span class="value-bind"
											id="pers_aadhar_no_label"></span> <input type="hidden"
											id="pers_aadhar_no" name="pers_aadhar_no"
											class="form-control" autocomplete="off" maxlength="12">
									</div>
								</div>
							</div>
							</div>

							<div class="field-box">
								<div class="row">
									<div class="col-lg-12 col-sm-12">
										<div class="custom-data-value custom-title custom-title-bg">
											<h5 class="custom-title-tag">Address</h5>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Permanent Address </label> <span
												class="value-bind address" id="pers_permanent_address_label"></span>
											
											<select hidden="hidden"
											name="pers_permanent_state" id="pers_permanent_state" class="form-control custom-d-none">
											<option value="0">-- Select
												State --</option>
											<c:forEach var="item" items="${getMedStateName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
										
										 <select hidden="hidden"
											name="district_id" id="pers_permanent_district"
											class="form-control custom-d-none">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
											
											
										</div>
									</div>

									<div class="col-lg-4 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Present Address </label> <span
												class="value-bind address" id="pers_present_address"></span>
										
										
										<select hidden="hidden"
											name="pers_present_state" id="pers_present_state" class="form-control custom-d-none">
											<option value="0">-- Select
												State --</option>
											<c:forEach var="item" items="${getMedStateName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
										
										 <select hidden="hidden"
											name="pers_present_district" id="pers_present_district"
											class="form-control custom-d-none">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
										
										</div>
									</div>
									


									<div class="col-lg-4 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Corresponding Address </label> <span
												class="value-bind address" id="corre_address_label"></span>
											
											
											
											<select hidden="hidden" name="corre_state"
											id="corre_state" class="form-control custom-d-none">
											<option value="0">-- Select State --</option>
											<c:forEach var="item" items="${getMedStateName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
										<select hidden="hidden" name="corre_district"
											id="corre_district" class="form-control custom-d-none">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
											
											
										</div>
									</div>
									</div>
								</div>

								<div class="field-box">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="custom-data-value custom-title custom-title-bg">
												<h5 class="custom-title-tag">NEET Details</h5>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<span class="value-bind no-data custom-d-none" id="no_neet_data"></span>
										</div>

										<div class="col-lg-12 col-md-12 col-sm-12 col-12"
											id="neettable">
											<div class="table-wrapper table-responsive simple-table">
												<table class="table">
													<thead>
														<tr>
															<th><h6>Roll No</h6></th>
															<th><h6>Application No</h6></th>
															<th><h6>All India Rank</h6></th>
															<th><h6>Marks</h6></th>
															<th><h6>Percentile</h6></th>

														</tr>
													</thead>
													<tbody id="neetdata">
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
								
								<div class="field-box">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="custom-data-value custom-title custom-title-bg">
												<h5 class="custom-title-tag">Education Details</h5>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<span class="value-bind no-data custom-d-none" id="no_edu_data"></span>
										</div>

										<div class="col-lg-12 col-md-12 col-sm-12 col-12"
											id="edutable">
											<div class="table-wrapper table-responsive custom-table">
												<table class="table">
													<thead>
														<tr>
															<th><h6>Sr No</h6></th>
															<th><h6>Name of Exam</h6></th>
															<th><h6>Board/University</h6></th>
															<th><h6>School/College</h6></th>
															<th><h6>Subject</h6></th>
															<th><h6>Year of Passing</h6></th>
															<th><h6>Percentage of Marks</h6></th>
															<th><h6>Division</h6></th>

														</tr>
													</thead>
													<tbody id="trdata">
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
								
								<div class="field-box">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="custom-data-value custom-title custom-title-bg">
												<h5 class="custom-title-tag">Admission Details</h5>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<span class="value-bind no-data custom-d-none" id="no_adm_data"></span>
										</div>

										<div class="col-lg-12 col-md-12 col-sm-12 col-12"
											id="table_adm">
											<div class="table-wrapper table-responsive simple-table">
												<table class="table">
													<thead>
														<tr>
															<th><h6>Ayush Id</h6></th>
															<th><h6>University</h6></th>
															<th><h6>Institute</h6></th>
															<th><h6>System</h6></th>
															<th><h6>Degree</h6></th>
															<th><h6>University Enrollment Number</h6></th>
														</tr>
													</thead>
													<tbody id="trdata_adm">
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>

								<!--  Hidden Start -->
								<input type="hidden" id="system_id" name="system_id"
									class="form-control" value="0"> <input type="hidden"
									id="late_admission_status" name="late_admission_status"
									class="form-control" value="0">
								<!--  Hidden End -->
								<div class="field-box">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="custom-data-value custom-title custom-title-bg">
												<h5 class="custom-title-tag">Undertaking</h5>
												<input type="hidden" id="reg_no" name="reg_no"
													class="form-control" autocomplete="off" maxlength="25">
											</div>
										</div>

										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="custom-choose-one">
												<div class="input-style-form-check_block check-multi-list">
													<div class="form-check checkbox-style">
														<input type="checkbox" id="Declaration" name="Declaration"
															autocomplete="off" maxlength="25"
															class="form-check-input"> <input type="hidden"
															id="hiddenUpdate" name="hiddenUpdate"
															class="form-control autocomplete" value="0"> <label
															class="check-list">I have carefully read the
															concept and rules regarding my admission, I fully
															understand that my admission is provisional and is
															subject to final approval and enrollment by the
															University. I also understand that my provisional
															admission is without prejudice to the directives/rules
															and regulations/orders/confirmation from the designated
															and competent authorities of the state/central government
															or the hon'ble court. </label> <label class="check-list">
															I hereby agree to abide by the terms and conditions or
															the rules pertaining to admission as prescribed by the
															competent authorities and admit that they are binding
															upon me legally and legitimately.</label> <label
															class="check-list"> I undertake to pay the fees
															fixed by the Competent Authority, University.</label> <label
															class="check-list">I undertake to see daily
															notices exhibited on the noticed board of the college,
															observe and maintain a strict discipline as the student
															and otherwise, in the college premises including hostel
															and campus.</label>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

								<!-- 
										<div class="col-lg-12 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value mb-0 mt-30">
												<label>Undertaking </label>
																					<span class="value-bind" id="reg_no1"></span>
												<input type="hidden" id="reg_no" name="reg_no"
													class="form-control" autocomplete="off" maxlength="25">
											</div>
										</div>

										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="d-flex">
												<div class="mr-5">
													<input type="checkbox" id="Declaration" name="Declaration"
														autocomplete="off" maxlength="25" class="form-check-input">

													<input type="hidden" id="hiddenUpdate" name="hiddenUpdate"
														class="form-control autocomplete" value="0">
												</div>

												<div class="">
													<div class="col-lg-12 col-12">
														<p class="und_text">
															<i class="bi bi-chevron-right"></i>I have carefully read
															the concept and rules regarding my admission, I fully
															understand that my admission is provisional and is
															subject to final approval and enrollment by the
															University. I also understand that my provisional
															admission is without prejudice to the directives/rules
															and regulations/orders/confirmation from the designated
															and competent authorities of the state/central government
															or the hon'ble court.
														</p>
													</div>

													<div class="col-lg-12 col-12">
														<p class="und_text">
															<i class="bi bi-chevron-right"></i>I hereby agree to
															abide by the terms and conditions or the rules pertaining
															to admission as prescribed by the competent authorities
															and admit that they are binding upon me legally and
															legitimately.
														</p>
													</div>

													<div class="col-lg-12 col-12">
														<p class="und_text">
															<i class="bi bi-chevron-right"></i>I undertake to pay the
															fees fixed by the Competent Authority, University.
														</p>
													</div>

													<div class="col-lg-12 col-12">
														<p class="und_text">
															<i class="bi bi-chevron-right"></i>I undertake to see
															daily notices exhibited on the noticed board of the
															college, observe and maintain a strict discipline as the
															student and otherwise, in the college premises including
															hostel and campus.
														</p>
													</div>
												</div>
											</div>
											<div class="float-end my-3">
												<div class="upload_sign">
																				 <div class="fill_image">
													<img id="upload_signature_doc_preview" class="" />
																							</div>
												</div>
											</div>

																		<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
																			<div class="custom-data-value" >
																				<label id="hos_name_add1"></label>
																				<input type="checkbox" id="Declaration" name="Declaration" 
																					autocomplete="off" maxlength="25" >
																		</div>
																	</div>
										</div> -->
								<div class="field-box">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="auto-fill-form">
												<div class="upload_sign">
													<p class="sign-label">
														Signature..<i class="lni lni-pencil"></i>
													</p>
													<img id="upload_signature_doc_preview" class="" />
												</div>
											</div>
											<!-- Hidden Start -->
					<input type="hidden" id="p_id" value="0">
					<!-- Hidden End -->
										</div>
									</div>
								</div>

                                  <!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a id="aIdPrevious"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i
													class="lni lni-chevron-left"></i> Previous</a></li>
											<li><a type="button"
												class="main-btn info-btn btn-hover btnsubmit" value="Submit"
												id="finalsubmit">Submit</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
							</div>

						</div>
					</div>
					
					</form:form>
				</div>
		</div>
	
</section>

<c:url value="doc_uploadNcism_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm5"
	name="applicationUrlForm5" modelAttribute="doc_eid">
	<input type="hidden" name="doc_eid" id="doc_eid" value="0" />
</form:form>

<c:url value="Personal_Details_Ncism_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm"
	name="applicationUrlForm" modelAttribute="pers_details_hid">
</form:form>

<c:url value="Edu_Det_Ncism_Url" var="mainFormUrl1" />
<form:form action="${mainFormUrl1}" method="GET" id="mainForm11"
	name="mainForm11" modelAttribute="eid">
	<input type="hidden" name="eid" id="eid" value="0" />
</form:form>

<c:url value="Edit_edu_reg_mstrUrl" var="Edit_edu_reg_mstrUrl" />
<form:form action="${Edit_edu_reg_mstrUrl}" method="post"
	id="updateForm" name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="delete_edu_reg_mstr_Url" var="delete_edu_reg_mstr_Url" />
<form:form action="${delete_edu_reg_mstr_Url}" method="post"
	id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>
<c:url value="Excel_Auth_Posted_query" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm"
	name="ExcelForm" modelAttribute="cont_comd_ex">
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
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<c:url value="Student_Personal_Ncism_Details_Action"
	var="Student_Personal_Ncism_Details_Action" />
<form:form action="${Student_Personal_Ncism_Details_Action}"
	method="POST" id="student_submit_form" name="student_submit_form"
	modelAttribute="eid3">
	<input type="hidden" name="status2" id="status2" value="0" />
	<input type="hidden" name="eid3" id="eid3" value="0" />
	<input type="hidden" name="system_id2" id="system_id2" value="0" />

</form:form>

<!-- The Modal -->
<div id="myModal" class="modal">
	<span class="close">&times;</span> <img class="modal-content"
		id="img01">
	<div id="caption"></div>
</div>

<script nonce="${cspNonce}">
	// Std_dtl_viewCMD

	$(document)
			.ready(
					function() {

						var cand_prifix = '${Std_dtl_viewCMD.cand_prifix}';
						var pers_name = '${Std_dtl_viewCMD.pers_name}';
						var pers_middel_name = '${Std_dtl_viewCMD.pers_middel_name}';
						var pers_surname = '${Std_dtl_viewCMD.pers_surname}';

						$("#cand_prifix").val(cand_prifix);
						var cp = $("#cand_prifix :selected").text();

						var name = cp + ". " + pers_name;

						$("#pers_name").val(name);
						$("#pers_name_label").html(name);

						$("#pers_middel_name").val(pers_middel_name);
						$("#pers_middel_name_label").html(pers_middel_name);

						$("#pers_surname").val(pers_surname);
						$("#pers_surname_label").html(pers_surname);

						var pers_father_name = '${Std_dtl_viewCMD.pers_father_name}';
						$("#pers_father_name_label").html(pers_father_name);

						var pers_mother_name = '${Std_dtl_viewCMD.pers_mother_name}';
						$("#pers_mother_name_label").html(pers_mother_name);

						var pers_date_of_birth = '${Std_dtl_viewCMD.pers_date_of_birth}';
						const date = new Date(pers_date_of_birth);
						const formattedDate = date.toLocaleDateString('en-GB',
								{
									day : '2-digit',
									month : 'short',
									year : 'numeric'
								}).replace(/ /g, '/');

						$("#pers_date_of_birth_label").html(formattedDate);

						var pers_gender = '${Std_dtl_viewCMD.pers_gender}';
						$("#pers_gender").val(pers_gender);
						$("#pers_gender_label").html(
								$("#pers_gender :selected").text());

						var pers_mob_no = '${Std_dtl_viewCMD.pers_mob_no}';
						$("#pers_mob_no_label").html(pers_mob_no);

						var pers_email = '${Std_dtl_viewCMD.pers_email}';
						$("#pers_email_label").html(pers_email);

						var pers_category = '${Std_dtl_viewCMD.pers_category}';
						//$("#pers_category_label").html(pers_category);
						$("#pers_category").val(pers_category);
						$("#pers_category_label").html(
								$("#pers_category :selected").text());

						var pers_religion = '${Std_dtl_viewCMD.pers_religion}';
						$("#pers_religion").val(pers_religion);
						$("#pers_religion_label").html(
								$("#pers_religion :selected").text());

						var pers_marital_status = '${Std_dtl_viewCMD.pers_marital_status}';
						$("#pers_marital_status").val(pers_marital_status);
						$("#pers_marital_status_label").html(
								$("#pers_marital_status :selected").text());

						var pers_nationality = '${Std_dtl_viewCMD.pers_nationality}';
						$("#pers_nationality").val(pers_nationality);
						// 	$("#pers_nationality").val('33');
						$("#pers_nationality_label").html(
								$("#pers_nationality :selected").text());

						var state_id = '${Std_dtl_viewCMD.state_id}';
						$("#state_id").val(state_id);
						// 	$("#state_id").val('60');
						$("#state_id_label").html(
								$("#state_id :selected").text());

						var district_id = '${Std_dtl_viewCMD.district_id}';
						$("#district_id").val(district_id);
						// 	$("#district_id").val('46');
						$("#district_id_label").html(
								$("#district_id :selected").text());

						var village = '${Std_dtl_viewCMD.village}';
						//$("#village").val(village);
						$("#village_label").html(village);

						var pers_aadhar_no = '${Std_dtl_viewCMD.pers_aadhar_no}';
						$("#pers_aadhar_no_label").html(pers_aadhar_no);

						var pers_permanent_house_no = '${Std_dtl_viewCMD.pers_permanent_house_no}';
						var pers_permanent_village = '${Std_dtl_viewCMD.pers_permanent_village}';
						var pers_permanent_postoffice = '${Std_dtl_viewCMD.pers_permanent_postoffice}';
						var pers_permanent_tehsil = '${Std_dtl_viewCMD.pers_permanent_tehsil}';
						var pers_permanent_policestation = '${Std_dtl_viewCMD.pers_permanent_policestation}';
						var pers_permanent_district = '${Std_dtl_viewCMD.pers_permanent_district}';
						var pers_permanent_state = '${Std_dtl_viewCMD.pers_permanent_state}';
						var pers_permanent_pincode = '${Std_dtl_viewCMD.pers_permanent_pincode}';
						var pers_permanent_lendmark = '${Std_dtl_viewCMD.pers_permanent_lendmark}';

						$("#pers_permanent_state").val(pers_permanent_state);
						var parm_st =	$("#pers_permanent_state :selected").text();
						$("#pers_permanent_district").val(pers_permanent_district);
						var parm_dest =	$("#pers_permanent_district :selected").text();
					
						var pers_permanent_address = pers_permanent_house_no + " " 
													+ pers_permanent_village + " "
													+ pers_permanent_postoffice + " " 
													+ pers_permanent_tehsil+" "
													+ parm_dest +" " 
													+ parm_st + " "
													+ pers_permanent_lendmark+" "
													+ pers_permanent_pincode;
					
						$("#pers_permanent_address_label").html(
								pers_permanent_address);
						var pers_present_house_no = '${Std_dtl_viewCMD.pers_present_house_no}';
						var pers_present_village = '${Std_dtl_viewCMD.pers_present_village}';
						var pers_present_postoffice = '${Std_dtl_viewCMD.pers_present_postoffice}';
						var pers_present_tehsil = '${Std_dtl_viewCMD.pers_present_tehsil}';
						var pers_present_policestation = '${Std_dtl_viewCMD.pers_present_policestation}';
						var pers_present_district = '${Std_dtl_viewCMD.pers_present_district}';
						var pers_present_state = '${Std_dtl_viewCMD.pers_present_state}';
						var pers_present_pincode = '${Std_dtl_viewCMD.pers_present_pincode}';
						var pers_present_lendmark = '${Std_dtl_viewCMD.pers_present_lendmark}';

						$("#pers_present_state").val(pers_present_state);
						var pres_st = $("#pers_present_state :selected").text();
						$("#pers_present_district").val(pers_present_district);
						var pres_dest = $("#pers_present_district :selected").text();

						var pers_present_address = pers_present_house_no + " "
												+ pers_present_village + " "
												+ pers_present_postoffice + " "
												+ pers_present_tehsil + " " + pres_dest + " "
												+ pres_st + " " 
												+ pers_present_lendmark + " "
												+ pers_present_pincode;
						$("#pers_present_address").html(pers_present_address);

						//corresponding

						var corre_house_no = '${Std_dtl_viewCMD.corre_house_no}';
						var corre_village = '${Std_dtl_viewCMD.corre_village}';
						var corre_postoffice = '${Std_dtl_viewCMD.corre_postoffice}';
						var corre_tehsil = '${Std_dtl_viewCMD.corre_tehsil}';
						var corre_policestation = '${Std_dtl_viewCMD.corre_policestation}';
						var corre_district = '${Std_dtl_viewCMD.corre_district}';
						var corre_state = '${Std_dtl_viewCMD.corre_state}';
						var corre_pincode = '${Std_dtl_viewCMD.corre_pincode}';
						var corre_lendmark = '${Std_dtl_viewCMD.corre_lendmark}';

						$("#corre_state").val(corre_state);
						var corr_st = $("#corre_state :selected").text();
						$("#corre_district").val(corre_district);
						var corr_dest = $("#corre_district :selected").text();

						var corre_address = corre_house_no + " "
								+ corre_village + " " 
								+ corre_postoffice + " "
								+ corre_tehsil + " " 
								+ corr_dest + " "
								+ corr_st + " " 
								+ corre_lendmark + " "
								+ corre_pincode;
						
						$("#corre_address_label").html(corre_address);

						var neet_roll_no = '${Std_dtl_viewCMD.neet_roll_no}';
						var neet_application_no = '${Std_dtl_viewCMD.neet_application_no}';
						var neet_rank = '${Std_dtl_viewCMD.neet_rank}';
						var neet_marks = '${Std_dtl_viewCMD.neet_marks}';
						var neet_percentile = '${Std_dtl_viewCMD.neet_percentile}';

						// 	$("#neet_details_label").html("NEET Rank : "+neet_rank+ ",   NEET Marks : "+neet_marks+",   NEET Percentile : "+neet_percentile);

						neetdata = '<tr>' + '<td><p>' + neet_roll_no
								+ '</p></td>' + '<td><p>' + neet_application_no
								+ '</p></td>' + '<td><p>' + neet_rank
								+ '</p></td>' + '<td><p>' + neet_marks
								+ '</p></td>' + '<td><p>' + neet_percentile
								+ '</p></td>' + '</tr>';
						//$('div#edutable').empty();
						$("#neetdata").append(neetdata);
						// 		$('div#table_adm').empty();
						// 		$("#no_neet_data").html("----  NO DATA AVAILABLE ----")

						var id = '${Std_dtl_viewCMD.id}';
						// 	var p_id = '${Std_dtl_viewCMD.p_id}';
						// 	$("#pres_state").val(prest_name);
						// alert(prest_name);

						if (id != "") {
							getEdu_Detail(id);
							//getImagethumb(id);
							$("#p_id").val(id);
							var signaturephoto = "ViewRefImageFileNcism_Download5?kmlId="
									+ id + "&kmlfildname=signature";
							$("img#upload_signature_doc_preview").attr('src',
									signaturephoto);

							var photographphoto = "ViewRefImageFileNcism_Download5?kmlId="
									+ id + "&kmlfildname=photograph";
							$("img#upload_photo_doc_preview").attr('src',
									photographphoto);
						}

						getadmDetails();

					});

	/////////personal_details  from registration table/////Start
	function getadmDetails() {

		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		var userid = "${userId}";
		$.post(
				'get_ayush_id_Ncism_ctrl?' + key + "=" + value,
				{
					userid : userid
				},
				function(j) {

					strdata = "";
					if (j.length > 0) {
						for (var i = 0; i < j.length; i++) {
							strdata += '<tr>' + '<td><p>' + j[i][0]
									+ '</p></td>' + '<td><p>' + j[i][1]
									+ '</p></td>' + '<td><p>' + j[i][2]
									+ '</p></td>' + '<td><p>' + j[i][3]
									+ '</p></td>' + '<td><p>' + j[i][4]
									+ '</p></td>' + '</tr>';

							$("#system_id").val(j[i][5]);
							$("#late_admission_status").val(j[i][6]);
						}
						//$('div#edutable').empty();
						$("#trdata_adm").append(strdata);
					} else {
						$('div#table_adm').empty();

						if ($("#no_adm_data").hasClass("d-none")) {
							$("#no_adm_data").removeClass("d-none");
							$("#no_adm_data").addClass("d-block");
						}

						$("#no_adm_data").html("----  NO DATA AVAILABLE ----")
					}
				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});

	}
	/////////////End
	function getEdu_Detail(p_id) {
		//	alert("pid------->   "+p_id);
		$.post("getEdu_Detail_Ncism_Ctrl?" + key + "=" + value, {
			p_id : p_id
		}, function(j) {
			strdata = "";
			//var x= 0;

			if (j.length > 0) {
				for (var i = 0; i < j.length; i++) {
					strdata += '<tr>' + '<td><p>' + j[i][0] + '</p></td>'
							+ '<td><p>' + j[i][1] + '</p></td>' + '<td><p>'
							+ j[i][2] + '</p></td>' + '<td><p>' + j[i][3]
							+ '</td>' + '<td><p>' + j[i][4] + '</p></td>'
							+ '<td><p>' + j[i][5] + '</p></td>' + '<td>'
							+ j[i][6] + '</p></td>' + '<td><p>' + j[i][7]
							+ '</p></td>' + '</tr>';
				}
				//$('div#edutable').empty();
				$("#trdata").append(strdata);

			} else {
				$('div#edutable').empty();
				if ($("#no_edu_data").hasClass("d-none")) {
					$("#no_edu_data").removeClass("d-none");
					$("#no_edu_data").addClass("d-block");
				}
				$("#no_edu_data").html("----  NO DATA AVAILABLE ----")
			}
		});
	}

	function getpersdetailsPage() {
		$("#pers_details_hid").val($("#p_id").val());
		document.getElementById("applicationUrlForm").submit();
	}
	function getEduPage() {
		$("#eid").val($("#p_id").val());
		$("#pers_exper_hid").val("${pers_adv_details_session}");
		document.getElementById("mainForm11").submit();
	}

	function getPreviousPage() {
		$("#doc_eid").val($("#p_id").val());
		$("#pers_exper_hid").val("${pers_adv_details_session}");
		document.getElementById("applicationUrlForm5").submit();
	}

	function FinalSubmit() {
		var paramvar = $('input[name="Declaration"]:checkbox:checked').map(
				function() {
					return this.value;
				}).get();

		if (paramvar == "on") {
			$("#hiddenUpdate").val(1);
			$("#system_id2").val($("#system_id").val());
			$("#eid3").val('${Std_dtl_viewCMD.id}');
			document.getElementById('student_submit_form').submit();

			// 		alert("Data Submitted Successfully ");
			// 		document.getElementById('coman').click();
		} else {
			alert("Please Accept the Undertaking");
			return false;
		}
	}

	// Get the modal
	function imageView(obj) {
		var modal = document.getElementById("myModal");
		var span = document.getElementsByClassName("close")[0];

		// Get the image and insert it inside the modal - use its "alt" text as a caption
		var img = document.getElementById("myImg" + obj);
		var modalImg = document.getElementById("img01");
		var captionText = document.getElementById("caption");

		img.onclick = function() {
			modal.style.display = "block";
			modalImg.src = this.src;
			//captionText.innerHTML = this.alt;
		}

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
		}

	}

	//csp----------------------------
	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('tunnel_1').onclick = function() {
			// 				if(confirm('Are you sure you want to Proceed?')){getpersdetailsPage();}else{return false;}
			getpersdetailsPage();
		};

		document.getElementById('tunnel_2').onclick = function() {
			// 				if(confirm('Are you sure you want to Proceed?')){getEduPage();}else{return false;}
			getEduPage();
		};
		document.getElementById('tunnel_3').onclick = function() {
			// 				if(confirm('Are you sure you want to Proceed?')){getPreviousPage();}else{return false;}
			getPreviousPage();
		};
		document.getElementById('aIdPrevious').onclick = function() {
			// 				if (confirm('Are you sure you want to Proceed?')) {
			return getPreviousPage();
			// 				} else {
			// 					return false;
			// 				}
		};
		document.getElementById('finalsubmit').onclick = function() {
			// 				if (confirm('Are you sure you want to Submit Data?')) {
			return FinalSubmit();
			// 				} else {
			// 					return false;
			// 				}
		};
	});

	function draftSave(val) {

		if (val == "Final Submit") {
			$("#hiddenUpdate").val(1);

		}

	}
</script>












