<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="https://fonts.googleapis.com/css?family=Poppins:400,600,700" rel="stylesheet" /> -->
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- <link rel="stylesheet" href="js/assets/collapse/collapse.css"> -->
<!-- <link rel="stylesheet" href="../assets/db_css/db_custom_style.css"> -->
<script src="js/miso/commonJS/addmorefunctionality.js"
	type="text/javascript"></script>
	
	 
		
 <script src="Pages_JS/Policy_Creation.js" type="text/javascript"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/Pdf_View/pdf.js" type="text/javascript"></script>
<script src="js/Pdf_View/pdf.worker.js" type="text/javascript"></script>
<link href="js/Pdf_View/pdfview.css" rel="Stylesheet"></link>
<!--  <script src="js/sweetalert/sweetalert.min.js"></script> -->
	

<section class="dashboard-page regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>	Provisional Certificate For Student Preview</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#0">Start View</a></li>
								<li class="breadcrumb-item"><a href="#0">Regulation Forms</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Form A</li>
							</ol>
						</nav>
					</div>
				</div>
				
<!-- 				<input type="file" id="images" name="images" multiple="multiple"> -->
				
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->


		<div class="form-elements-wrapper">
			<div class="row">

				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form action="stuRegulationPreview_Action" id="uploadForm" method="POST"
						class="form-horizontal" modelAttribute="stuRegulationPreviewCMD"
						enctype="multipart/form-data">
						<div class="card-style ">

							<div class="accordion mb-30 auto-fill-form" id="accordionPanelsStayOpenExample">
								<div class="accordion-item accordion-itemstyle">
									<h2 class="accordion-header" id="panelsStayOpen-headingOne">
										<button
											class="accordion-button accordion-itemstylena accordion-primary-button "
											type="button"
											data-bs-target="#panelsStayOpen-collapseOne"
											aria-expanded="true"
											aria-controls="panelsStayOpen-collapseOne">PERSONAL
											DETAILS</button>

									</h2>
									<div id="panelsStayOpen-collapseOne"
										class="accordion-collapse collapse show"
										aria-labelledby="panelsStayOpen-headingOne">
										<div class="accordion-body">
											<div class="card-style mb-30">

												<div class="row">

													<input type="hidden" id="userId" name="userId" value="0">
													<input type="hidden" id="aayushid" name="aayushid" value="">
													<input type="hidden" id="abha_no" name="abha_no" value="">

													<!-- 	19-07-22 testing changes -->
													
													<div class="row">
													<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="col-lg-4 col-md-6 col-sm-12">
													
														<div class="upload_image">
<!-- 															<label>Upload Photograph <strong class="mandatory"> </strong></label>  -->
<!-- 																<input type=file -->
<!-- 																accept="image/*" id="photo_path" name="photo_path" -->
<!-- 																onchange="photoValidate();" class="form-control"> -->
<!-- 																<label id="photo_path" name="photo_path"></label> -->
<!-- 														<img id="photo_path" name="photo_path"> -->
														<img id="identity_image_preview" alt="Officer Image" src="assets/db_img/noimage.jpeg" />
															<input type="hidden" id="upload_img_hid"
																name="upload_img_hid" class="form-control">

														</div>
													</div>
													</div>
													</div>
													
													<div class="col-lg-4 col-md-6 col-sm-12">
													
														<div class="input-style-1 preview_feild">
							<label class="f_label">Full Name</label> <p class="f_value" id="first_name"></p>
						</div>
<!-- <div class="input-style-1">
														<div class="input-with-value">
															<label for="fname">Full Name :-</label> 
															<div class="form-group stepform-group stepform-groupadd" style="display: none;"></div>
															<span id="first_name" name="first_name" class="auto-fill-value"></span>
														</div>
													</div> -->
													</div>


													<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-1 preview_feild">
							<label class="f_label">Father's Full Name</label> <p class="f_value" id="father_name"></p>
						</div>
														<!-- <div class="input-with-value">
															<label>Father's Full Name :-<strong class="mandatory">
															</strong>
															</label> 
															<span id="father_name" name="father_name" class="auto-fill-value"></span>
															<input type="text" id="father_name" name="father_name"
																placeholder="Father's Full Name"
																onkeypress="return onlyAlphabetsStringSpace(event,this)"
																oninput="this.value = this.value.toUpperCase()"
																maxlength="50" class="form-control" autocomplete="off">

														</div> -->
													</div>


<!-- 	19-07-22 testing changes -->
													<div class="col-lg-4 col-md-6 col-sm-12">
													
													<div class="input-style-1 preview_feild">
							<label class="f_label">Date Of Birth</label> <p class="f_value" id="dob1"></p>
							<input type="hidden" name="dob" id="dob" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"  
																class="form-control-sm form-control effect-9 "
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this); calculate_age('dob');  "
																aria-required="true" autocomplete="off"
																value="DD/MM/YYYY">
																<div class="info-value">
																<b>Age :</b><span class="text-heighlight get-value"
																	id="age" name="age"></span>
															</div>
						</div>
														<!-- <div class="input-with-value">
															<label>Date Of Birth :-<strong class="mandatory">
															</strong></label>
															 
															<span name="dob1" id="dob1" class="auto-fill-value"></span>
															 <input type="hidden" name="dob" id="dob" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"  
																class="form-control-sm form-control effect-9 "
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this); calculate_age('dob');  "
																aria-required="true" autocomplete="off"
																value="DD/MM/YYYY">
															<div class="info-value">
																<b>Age :</b><span class="text-heighlight get-value"
																	id="age" name="age"></span>
															</div>
														</div> -->
<input type="hidden" id = "yrr" name ="yrr" value="">
 


													</div>
													 
													
													<div class="col-lg-4 col-md-6 col-sm-12">
													
													<div class="input-style-1 preview_feild">
							<label class="f_label">Gender</label> <p class="f_value" id="gender"></p>
						</div>
														<!-- <div class="input-with-value">
															<label>Gender :-<strong class="mandatory"> </strong></label> 
															<span id="gender" name="gender" class="auto-fill-value"></span>
														</div> -->
													</div>
													
													
													<!-- end input -->


	<!-- 	19-07-22 testing changes -->
															<div class="col-lg-4 col-md-6 col-sm-12">
																		<div class="input-style-1 preview_feild">
							<label class="f_label">Aadhaar Number</label> <p class="f_value" id="aadhaar_no"></p>
						</div>
															
														<!-- <div class="input-with-value">
															<label>Aadhaar Number :-<strong class="mandatory">
															</strong></label>
															<span id="aadhaar_no" name="aadhaar_no" class="auto-fill-value"></span>
															 <input type="text" id="aadhaar_no" name="aadhaar_no"
																oninput="this.value = this.value.toUpperCase()"
																onkeypress="return onlyAlphaNumeric(event, this)
																maxlength="14" minlength="14" class="form-control" autocomplete="off"
																placeholder="Aadhaar Number">
														</div> -->
													</div>

													<div class="col-lg-4 col-md-6 col-sm-12">
													
																	<div class="input-style-1 preview_feild">
							<label class="f_label">Mobile Number</label> <p class="f_value" id="mo_no"></p>
						</div>
													
														<!-- <div class="input-with-value">
															<label>Mobile Number :-<strong class="mandatory">
															</strong>
															</label>
															
															<span id="mo_no" name="mo_no" class="auto-fill-value"></span>
															 <input type="text" id="mo_no" name="mo_no"
																onkeypress="return isNumberOnly(event)"
																oninput="this.value = this.value.toUpperCase()"
																maxlength="10" minlength="10" class="form-control" autocomplete="off"
																placeholder="Mobile Number">

														</div> -->
													</div>

													<!-- 	19-07-22 testing changes -->
													<div class="col-lg-4 col-md-6 col-sm-12">
													
													<div class="input-style-1 preview_feild">
							<label class="f_label">Email Id</label> <p class="f_value" id="email_id"></p>
						</div>
													
														<!-- <div class="input-with-value">
															<label>Email Id :-<strong class="mandatory"></strong></label> 
															<span id="email_id" name="email_id" class="auto-fill-value"></span>
															<input type="email" id="email_id" name="email_id"
																class="form-control" autocomplete="off">
														</div> -->

													</div>
													

													<div class="col-lg-4 col-md-6 col-sm-12">
													
													<div class="input-style-1 preview_feild">
							<label class="f_label">Nationality</label> <p class="f_value" id="nat"></p>
							<div class="select-position custom-d-none custom_intern_pre_data_nationality">
																<select name="nationality" id="nationality" class="singleselect form-control form-control-lg">
																	<option value="0" selected="selected">--Select Country--</option>
																	<c:forEach var="item" items="${getMedNationality}" varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>
																</select>

															</div>
						</div>
													
														<%-- <div class="input-with-value">
															<label>Nationality :-<strong class="mandatory">
															</strong></label>
															
<!-- 															<div class="input-style-2"> -->
<!-- 															 <input type="text" id="nat" name="nat" maxlength="10" class="form-control" autocomplete="off" -->
<!-- 																 value="INDIAN" readonly="readonly"> -->
																	<span id="nat" name="nat" class="auto-fill-value"></span>

<!-- 														</div> -->
															
															
															<div class="select-position"  class="custom-d-none">
																<select name="nationality" id="nationality"  
																	style="text-transform: uppercase ; " class="form-control">
																	<option value="0" selected="selected">--
																		Select Country --</option>
																	<c:forEach var="item" items="${getMedNationality}"
																		varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>
																</select>

															</div>
														</div> --%>
													</div>

<!-- 													<div class="col-lg-12 col-md-12 col-sm-12"> -->
<!-- 														<h6 class="mb-25">Alternat Fields</h6> -->
<!-- 													</div> -->
													<!-- </div> -->

													<div class="col-lg-4 col-md-6 col-sm-12">
													
													<div class="input-style-1 preview_feild">
							<label class="f_label">Alternative Mobile Number 1</label> <p class="f_value"  id="alt_mo_no1"></p>
						</div>
													
														<!-- <div class="input-with-value">
															<label>Alternative Mobile Number 1 :-</label>
															<lable id="alt_mo_no1" name="alt_mo_no1" class="auto-fill-value" ></lable>
															 <input type="text" id="alt_mo_no1" name="alt_mo_no1"
																onkeypress="return isNumberOnly(event)"
																oninput="this.value = this.value.toUpperCase()"
																maxlength="10" class="form-control mb2"
																autocomplete="off"
																placeholder="Alternate Mobile Number 1">

														</div> -->
													</div>

													<div class="col-lg-4 col-md-6 col-sm-12">
													
													<div class="input-style-1 preview_feild">
							<label class="f_label">Alternative Mobile Number 2</label> <p class="f_value"  id="alt_mo_no2"></p>
						</div>
													
														<!-- <div class="input-with-value">


															<label>Alternative Mobile Number 2 :-</label> 
																<span id="alt_mo_no2" name="alt_mo_no2"  class="auto-fill-value"></span>
															<input
																type="text" id="alt_mo_no2" name="alt_mo_no2"
																onkeypress="return isNumberOnly(event)"
																oninput="this.value = this.value.toUpperCase()"
																maxlength="10" class="form-control mb2"
																autocomplete="off"
																placeholder="Alternate Mobile Number 2">
														</div> -->
													</div>

													<div class="col-lg-4 col-md-6 col-sm-12">
													
													<div class="input-style-1 preview_feild">
							<label class="f_label">Alternative EMAIL ID 1</label> <p class="f_value"  id="alt_email_id1"></p>
						</div>
													
														<!-- <div class="input-with-value">
															<label>Alternative EMAIL ID 1 :-</label>
																<span id="alt_email_id1" name="alt_email_id1" class="auto-fill-value" ></span>
														</div> -->
													</div>

													<div class="col-lg-4 col-md-6 col-sm-12">
													
													<div class="input-style-1 preview_feild">
							<label class="f_label">Alternative EMAIL ID 2</label> <p class="f_value"  id="alt_email_id2"></p>
						</div>
													
														<!-- <div class="input-with-value">
															<label>Alternative EMAIL ID 2 :-</label> 
															<span id="alt_email_id2" name="alt_email_id2" class="auto-fill-value" ></span>
														</div> -->
													</div>
													

													<div class="col-lg-4 col-md-6 col-sm-12"></div>
													<div class="col-lg-4 col-md-6 col-sm-12"></div>



												</div>
											</div>
										</div>
									</div>
								</div>
								</div>
								
								<div class="accordion mb-30" id="accordionPanelsStayOpenExample">
								<div class="accordion-item accordion-itemstyle">
									<h2 class="accordion-header" id="panelsStayOpen-headingOne">
										<button
											class="accordion-button accordion-itemstylena accordion-primary-button "
											type="button"
											data-bs-target="#panelsStayOpen-collapseOne"
											aria-expanded="true"
											aria-controls="panelsStayOpen-collapseOne">ADDRESS DETAILS </button>
									</h2>
									
									
									
									
									
									<div id="panelsStayOpen-collapseOne"
										class="accordion-collapse collapse show"
										aria-labelledby="panelsStayOpen-headingOne">
										<div class="accordion-body">
											<div class="card-style mb-30 auto-fill-form">

												<div class="row">
													<div class="col-lg-12 col-sm-12">
														<h6>Permanent Address</h6>
														<br>
													</div>






<!-- 													<div class="col-lg-12 col-sm-12"> -->
<!-- 														<div class="select-style-1"> -->
<!-- 															<label> Address <strong class="mandatory"> -->
<!-- 															</strong></label> -->
<!-- 														</div> -->
<!-- 													</div> -->
													<div class="col-lg-4  col-sm-12">
													
													
													<div class="input-style-1 preview_feild">
							<p class="f_value"  id="per_address"></p>
						</div>

														<!-- <div class="input-with-value">
														<span id="per_address" name="per_address" class="auto-fill-value"></span>

															<input type="text" id="per_address" name="per_address"
																maxlength="100" class="form-control autocomplete"
																onchange="changeAddress()" autocomplete="off"
																placeholder="Address Line 1">
														</div> -->
														
														
														
														
													</div>
													<div class="col-lg-4  col-sm-12">
													
														<div class="input-style-1 preview_feild">
							<p class="f_value"  id="per_address2"></p>
						</div>
													
														<!-- <div class="input-with-value">
														<label id="per_address2" name="per_address2" class="auto-fill-value"></label>

															<input type="text" id="per_address2" name="per_address2"
																maxlength="100" class="form-control autocomplete"
																onchange="changeAddress()" autocomplete="off"
																placeholder="Address Line 2">
														</div> -->
													</div>
													<div class="col-lg-4  col-sm-12">
													
													<div class="input-style-1 preview_feild">
							<p class="f_value"  id="per_address3"></p>
						</div>
													
														<!-- <div class="input-with-value">
														<span id="per_address3" name="per_address3" class="auto-fill-value"></span>

															<input type="text" id="per_address3" name="per_address3"
																maxlength="100" class="form-control autocomplete"
																onchange="changeAddress()" autocomplete="off"
																placeholder="Address Line 3">

														</div> -->
													</div>
													
													
													
													
													<div class="col-lg-4 col-sm-12">
													
													<div class="input-style-1 preview_feild">
							<label class="f_label">State</label> <p class="f_value" id="per_state"></p>
							<div class="select-position custom-d-none">
																<select name="per_state" id="per_state"
																	class="singleselect form-control form-control-lg">
																	<option value="0">--Select--</option>
																	<c:forEach var="item" items="${MedStateName}"
																		varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>

																</select>

															</div>
						</div>


														<%-- <div class="input-with-value">
															<label> State :-<strong class="mandatory">
															</strong></label>
															<span id="per_state" name="per_state" class="auto-fill-value"></span>
															<span id="per_state1" name="per_state1" class="auto-fill-value"></span>
															<div class="select-position"  style="display: none;">
																<select name="per_state" id="per_state"
																	class="form-control autocomplete"
																	onchange="getDistrictper(); changeAddress();">
																	<option value="0">--Select--</option>
																	<c:forEach var="item" items="${MedStateName}"
																		varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>

																</select>

															</div>
														</div> --%>
													</div>
													<div class="col-lg-4 col-sm-12">
													
													<div class="input-style-1 preview_feild">
							<label class="f_label">District</label> <p class="f_value" id="per_district"></p>
							<div class="select-position custom-d-none">
																<select name="per_district" id="per_district" 
																	class="singleselect form-control form-control-lg">
 																	<option value="0">--Select--</option>
																</select>

															</div>
						</div>
													
														<!-- <div class="input-with-value">
															<label>District<strong class="mandatory">
															</strong></label>
															<span id="per_district" name="per_district" class="auto-fill-value"></span>
															<div class="select-position" style="display: none;">
																<select name="per_district" id="per_district" 
																	class="form-control autocomplete"
																	onchange="changeAddress()">
 																	<option value="0">--Select--</option>
																</select>

															</div>
														</div> -->
													</div>
													
													<div class="col-lg-4 col-sm-12">
													
													<div class="input-style-1 preview_feild">
							<label class="f_label">Pin Code</label> <p class="f_value"  id="per_pincode"></p>
						</div>
													
														<!-- <div class="input-with-value">
															<label> Pin Code<strong class="mandatory">
															</strong></label>
															<span id="per_pincode" name="per_pincode" class="auto-fill-value"></span>
															 <input type="text" id="per_pincode" name="per_pincode"
																onkeypress="return isNumberOnly(event)"
																oninput="this.value = this.value.toUpperCase()"
																onchange="changeAddress()" maxlength="6"
																class="form-control" autocomplete="off"
																placeholder="Pin Code">

														</div> -->



													</div>
												</div>
												<div class="col-lg-12 col-sm-12">
													
														<div class="col-lg-12 col-md-12 col-sm-12">
															<h6>Present Address</h6>
															<br>
														</div>


													</div>


													<div class="row">
<!-- 														<div class="col-lg-12 col-sm-12"> -->
<!-- 															<div class="select-style-1"> -->
<!-- 																<label> Address <strong class="mandatory"> -->
<!-- 																</strong></label> -->
<!-- 															</div> -->
<!-- 														</div> -->
														<div class="col-lg-4 col-sm-12">
														<div class="input-style-1 preview_feild">
							<p class="f_value"  id="pre_address"></p>
						</div>
															<!-- <div class="input-with-value">
															<span id="pre_address" name="pre_address" class="auto-fill-value"></span>
																<input type="text" id="pre_address" name="pre_address"
																	maxlength="100" class="form-control autocomplete"
																	autocomplete="off" placeholder="Address Line 1">
															</div> -->
														</div>
														<div class="col-lg-4 col-sm-12">
														
														<div class="input-style-1 preview_feild">
							<p class="f_value"  id="pre_address2"></p>
						</div>
														
															<!-- <div class="input-with-value">
															<span id="pre_address2" name="pre_address2" class="auto-fill-value"></span>
																<input type="text" id="pre_address2" name="pre_address2"
																	maxlength="100" class="form-control autocomplete"
																	autocomplete="off" placeholder="Address Line 2">
															</div> -->
														</div>
														<div class="col-lg-4 col-sm-12">
														
														<div class="input-style-1 preview_feild">
							<p class="f_value"  id="pre_address3"></p>
						</div>
														
															<!-- <div class="input-with-value">
															<span id="pre_address3" name="pre_address3" class="auto-fill-value"></span>
																<input type="text" id="pre_address3" name="pre_address3"
																	maxlength="100" class="form-control autocomplete"
																	autocomplete="off" placeholder="Address Line 3">
															</div> -->
														</div>
														
														<div class="col-lg-4 col-sm-12">
														
														<div class="input-style-1 preview_feild">
							<label class="f_label">State</label> <p class="f_value" id="pre_state"></p>
							<div class="select-position custom-d-none">
																	<select name="pre_state" id="pre_state"
																		class="singleselect form-control form-control-lg">
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${MedStateName}"
																			varStatus="num">
																			<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																		</c:forEach>

																	</select>


																</div>
						</div>
															<%-- <div class="input-with-value">
																<label> State :-<strong class="mandatory">
																</strong></label>
																<span id="pre_state" name="pre_state" class="auto-fill-value"></span>
																<div class="select-position" style="display: none;">
																	<select name="pre_state" id="pre_state"
																		class="form-control autocomplete"
																		onchange="getDistrictpre();">
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${MedStateName}"
																			varStatus="num">
																			<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																		</c:forEach>

																	</select>


																</div>
															</div> --%>
														</div>
													
														<div class="col-lg-4 col-sm-12">
														
														
														
														<div class="input-style-1 preview_feild">
							<label class="f_label">District</label> <p class="f_value" id="pre_district"></p>
							<div class="select-position custom-d-none">
																	<select name="pre_district" id="pre_district"
																		class="singleselect form-control form-control-lg">
																		<option value="0">--Select--</option>

																														<!-- 			<option value="21" name="GANDHINAGAR">GANDHINAGAR</option>

																																	<option value="22" name="MUMBAI">MUMBAI</option>
 -->
																	</select>
														
															<!-- <div class="input-with-value">
																<label>District:-<strong class="mandatory">
																</strong></label>
																<span id="pre_district" name="pre_district" class="auto-fill-value"></span>
																<div class="select-position" style="display: none;">
																	<select name="pre_district" id="pre_district"
																		class="form-control autocomplete">
																		<option value="0">--Select--</option>

																																	<option value="21" name="GANDHINAGAR">GANDHINAGAR</option>

																																	<option value="22" name="MUMBAI">MUMBAI</option>

																	</select>

																</div>
															</div> -->
															</div>
															</div>
														</div>
														<div class="col-lg-4 col-sm-12">
														
														<div class="input-style-1 preview_feild">
							<label class="f_label">Pin Code</label> <p class="f_value"  id="pre_pincode"></p>
						</div>
															<!-- <div class="input-with-value">
																<label> Pin Code:-<strong class="mandatory">
																</strong></label>
																<span id="pre_pincode" name="pre_pincode" class="auto-fill-value"></span>
																 <input type="text" id="pre_pincode" name="pre_pincode"
																	onkeypress="return isNumberOnly(event)"
																	oninput="this.value = this.value.toUpperCase()"
																	maxlength="6" class="form-control" autocomplete="off"
																	placeholder="Pin Code">

															</div> -->
														</div>

														


													</div>
													<div class="col-lg-12 col-sm-12">
													
														<div class="col-lg-12 col-md-12 col-sm-12">
															<h6>Corresponding Address</h6>
															<br>
														</div>

													</div>
													
													<div class="row">
<!-- 														<div class="col-lg-12 col-sm-12"> -->
<!-- 															<div class="select-style-1"> -->
<!-- 																<label> Address <strong class="mandatory"> -->
<!-- 																</strong></label> -->
<!-- 															</div> -->
<!-- 														</div> -->
														<div class="col-lg-4 col-sm-12">
														
														<div class="input-style-1 preview_feild">
							<p class="f_value"  id="curr_address"></p>
						</div>
														
															<!-- <div class="input-with-value">
															<span id="curr_address" name="curr_address" class="auto-fill-value"></span>
																<input type="text" id="pre_address" name="pre_address"
																	maxlength="100" class="form-control autocomplete"
																	autocomplete="off" placeholder="Address Line 1">
															</div> -->
														</div>
														<div class="col-lg-4 col-sm-12">
														
															<div class="input-style-1 preview_feild">
							<p class="f_value"  id="curr_address2"></p>
						</div>
														
															<!-- <div class="input-with-value">
															<span id="curr_address2" name="curr_address2" class="auto-fill-value"></span>
																<input type="text" id="pre_address2" name="pre_address2"
																	maxlength="100" class="form-control autocomplete"
																	autocomplete="off" placeholder="Address Line 2">
															</div> -->
														</div>
														<div class="col-lg-4 col-sm-12">
														
														<div class="input-style-1 preview_feild">
							<p class="f_value"  id="curr_address3"></p>
						</div>
														
															<!-- <div class="input-with-value">
															<span id="curr_address3" name="curr_address3" class="auto-fill-value"></span>
																<input type="text" id="pre_address3" name="pre_address3"
																	maxlength="100" class="form-control autocomplete"
																	autocomplete="off" placeholder="Address Line 3">
															</div> -->
														</div>
														
														<div class="col-lg-4 col-sm-12">
														
														<div class="input-style-1 preview_feild">
							<label class="f_label">State</label> <p class="f_value" id="curr_state"></p>
							<div class="select-position custom-d-none">
																	<select name="curr_state" id="curr_state"
																		class="singleselect form-control form-control-lg">
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${MedStateName}"
																			varStatus="num">
																			<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																		</c:forEach>

																	</select>


																</div>
						</div>
														
															<%-- <div class="input-with-value">
																<label> State :-<strong class="mandatory">
																</strong></label>
																<span id="curr_state" name="curr_state" class="auto-fill-value"></span>
																<div class="select-position" style="display: none;">
																	<select name="curr_state" id="curr_state"
																		class="form-control autocomplete"
																		onchange="getDistrictpre();">
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${MedStateName}"
																			varStatus="num">
																			<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																		</c:forEach>

																	</select>


																</div>
															</div> --%>
														</div>
													
														<div class="col-lg-4 col-sm-12">
														
														<div class="input-style-1 preview_feild">
							<label class="f_label">District</label> <p class="f_value" id="curr_district"></p>
							<div class="select-position custom-d-none">
																	<select name="curr_district" id="curr_district"
																		class="singleselect form-control form-control-lg">
																		<option value="0">--Select--</option>

																	

																	</select>

																</div>
						</div>
														
															<!-- <div class="input-with-value">
																<label>District :-<strong class="mandatory">
																</strong></label>
																<span id="curr_district" name="curr_district" class="auto-fill-value"></span>
																<div class="select-position" style="display: none;">
																	<select name="curr_district" id="curr_district"
																		class="form-control autocomplete">
																		<option value="0">--Select--</option>

																																	<option value="21" name="GANDHINAGAR">GANDHINAGAR</option>

																																	<option value="22" name="MUMBAI">MUMBAI</option>

																	</select>

																</div>
															</div> -->
														</div>
														<div class="col-lg-4 col-sm-12">
														
														<div class="input-style-1 preview_feild">
							<label class="f_label">Pin Code</label> <p class="f_value"  id="curr_pincode"></p>
						</div>
														
															<!-- <div class="input-with-value">
																<label> Pin Code :-<strong class="mandatory">
																</strong></label>
																<span id="curr_pincode" name="curr_pincode" class="auto-fill-value"></span>
																 <input type="text" id="pre_pincode" name="pre_pincode"
																	onkeypress="return isNumberOnly(event)"
																	oninput="this.value = this.value.toUpperCase()"
																	maxlength="6" class="form-control" autocomplete="off"
																	placeholder="Pin Code">

															</div> -->
														</div>

														


													</div>
													
													
												</div>
											</div>
										</div>
									</div>
								</div>
								<!--                         HET CHANGES                        -->
								<div class="accordion mb-30" id="accordionPanelsStayOpenExample">
								<div class="accordion-item accordion-itemstyle mb-30">
									<h2 class="accordion-header" id="panelsStayOpen-headingOne">
									<button
											class="accordion-button accordion-itemstylena accordion-primary-button "
											type="button"
											data-bs-target="#panelsStayOpen-collapseOne"
											aria-expanded="true"
											aria-controls="panelsStayOpen-collapseOne">
										NAME OF
											MEDICAL DEGREE GRADUATE/POSTGRADUATE/DIPLOMA OBTAINED</button>
									</h2>
									<div id="panelsStayOpen-collapseOne"
										class="accordion-collapse collapse show"
										aria-labelledby="panelsStayOpen-headingOne">
								 	
					<div id="my_pdf_viewer" class="custom-d-none">
                    	 <div class="row">
							<div class="col-md-12 custom_uni_pro_pre_data_divp">
								<div id="canvas_container">
									<div id="zoom_controls" class="custom_uni_pro_pre_data_divc">
										<div class="custom_uni_pro_pre_data_divc0">
											<label class="btn btn-success btn-sm pdfbtnzoom" value=""></label><i
												id="zoom_in"
												class="lni lni-zoom-in custom_uni_pro_pre_data_divci" title='Zoom in'></i>
										</div>
										<br />
										<div class="custom_uni_pro_pre_data_divc0">
											<label class="btn btn-success btn-sm pdfbtnzoom" value=""></label><i
												id="zoom_out"
												class="lni lni-zoom-out custom_uni_pro_pre_data_divci" title='Zoom out'></i>
										</div>
									</div>
									<canvas id="pdf_renderer"></canvas>
									<input type="hidden" value="0" id="PicturePDFId" /> <input
										type="hidden" value="0" id="val1" /> <input type="hidden"
										value="0" id="fildname1" />
								</div>
								<div class="custom_uni_pro_pre_data_divc1">
									<i id="go_previous"
										class="fa fa-angle-double-left pdfbtnzoom" title='Previous'></i>
								</div>
								<input id="current_page" value="1" type="number" class="custom_uni_pro_pre_data_inp"/>
								<div class="custom_uni_pro_pre_data_divc0">
									<i id="go_next"
										class="lni lni lni-chevron-right pdfbtnzoom pdfbtnzoom-next custom_uni_pro_pre_data_i1" title='Next'></i>
								</div>
								<div  id="downloadbtn">
									<i id="downloadbtn" title="Download" class='fa fa-download custom_uni_pro_pre_data_i2'></i>
								</div>
								
								<div  id="downloadbtnview">
									<i id="downloadbtnview" title="Download" class='fa fa-download custom_uni_pro_pre_data_i2'></i>
								</div>
								
<!-- 								<ul class="buttons-group mainbtn"  id="dlall_pdf"   > -->
<!--  							 							 </ul> -->
							</div>
						</div>
						</div>
										
										
										<div class="accordion-body">
								
											<div class="card-style mb-30">
											
	

												<div class="tables-wrapper">
													<div class="row">
														<div class="col-lg-12">


															<div class="table-wrapper table-responsive custom-table">
					<table class="table" id="addNameOfMed">
						<thead>
							 <tr>
							<th>Ser No</th>
							<th>Type Of Degree<strong class="mandatory"></strong></th>
							<th>Degree Name<strong class="mandatory"></strong></th>
<!-- 							<th>Month and Year<strong class="mandatory"></strong></th> -->
						 <th class="custom-d-none">Month and Year<strong class="mandatory">* </strong></th>
						 <th>Name Of Institute <strong class="mandatory">  </strong></th>
 						 <th>Name of University <strong class="mandatory">  </strong></th>
							<th>View Attachment<strong class="mandatory"></strong></th>
 							<th>Download<strong class="mandatory"></strong></th>
							</tr>
						</thead>
						<tbody id="att_TbbodyNameMed">
								<tr id="tr_id_attNameMed">
									<td class="min-width">
										<div class="lead">

											<div class="lead-text">
												<p>1</p>
											</div>
										</div>
									</td>
									<td class="min-width">
											<p id="typeOfDegree1"></p>
									</td>
									<td class="min-width">
										<p id="DegreeName1"></p>
									</td>
<!-- 									<td class="min-width"> -->
<!-- 										<p id="monthYearOfDegree1"></p> -->
<!-- 									</td> -->

								<td class="min-width">
										<p id="insti_nam1"></p>
									</td>
<!-- 									<td class="min-width"> -->
<!-- 										<span class="auto-input" id="NameOfUniversity1"></span> -->
<!-- 									</td> -->
									
									 <td class="min-width">
<!-- 									 			<p id="NameOfUniversity1"></p>								  -->
								<select name="NameOfUniversity1" id="NameOfUniversity1"  class="auto-input" disabled="disabled"  class="custom-d-none" >
									<option value="0">--Select--</option>
										<p><c:forEach var="item" items="${getInstituteList}" varStatus="num">
										<option value="${item.id}" name="${item.university_name}">${item.university_name}</option>
									</c:forEach>
								</select>
<!-- 								<input type="hidden" id="NameOfUniversity1_hid" name="NameOfUniversity1_hid" value="0"  autocomplete="off" /> -->
							 <p id="NameOfUniversity1_hid1"></p>	
							  </td>
									
									
									
									
									  <td class="min-width addminusbut">
                               			<ul class="buttons-group mainbtn action">
                               			<li id="viewattachment">
                               			<a class="main-btn success-btn-outline btn-hover btn-sm addminusbut" 
                               			id="downloadbtnview1" title="Downlaod">
                               			<i class="bi bi-file-pdf"></i></a>
                               			<input type="hidden" id="file_id1" name="file_id1">
                               			</li>
                               			</ul>
                               		</td>
									
									
									<td class="min-width addminusbut">
                               			<ul class="buttons-group mainbtn action daobtn">
                               			<li><a class="main-btn success-btn btn-hover btn-sm addminusbut" 
                               		id="downloadbtn1" title="Downlaod">
                               			<i class="lni lni-download pdfdown"></i></a>
                               			<input type="hidden" id="file_id1" name="file_id1"></li></ul>
                               		</td>
                               		<td class="min-width"  class="custom-d-none">
                               		<div class="action">
	                               		<ul class="buttons-group mainbtn">
	                               		<li><a class="main-btn info-btn-outline btn-hover btn-sm addminusbut" 
	                               		value = "ADD" title = "ADD" id = "id_add_attNameMed1" onclick="formOpenNameMed(1);">
	                               		<i class="lni lni-plus"></i></a></li>
	                               		</ul>
                               		</div>
                               		</td>
								</tr>
								
						</tbody>
					</table>

																<input type="hidden" id="count_hidden_att_name_med"
																	name="count_hidden_att_name_med"
																	class="form-control autocomplete" value="1">
																	
																	<input type="hidden" id="count_hidden_att_name_med1"
																	name="count_hidden_att_name_med1"
																	class="form-control autocomplete" value="1">
																<!-- end table -->
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

								

							<div class="accordion mb-30 custom-d-none" id="accordionPanelsStayOpenExample">
								<div class="accordion-item accordion-itemstyle">
									<h2 class="accordion-header" id="panelsStayOpen-headingOne">
									<button
											class="accordion-button accordion-itemstylena accordion-primary-button "
											type="button"
											data-bs-target="#panelsStayOpen-collapseOne"
											aria-expanded="true"
											aria-controls="panelsStayOpen-collapseOne">
										INSTITUTE/OFFICE/CLINIC/HOSPITAL</button>
									</h2>
									<div id="panelsStayOpen-collapseOne"
										class="accordion-collapse collapse show"
										aria-labelledby="panelsStayOpen-headingOne">
										<div class="accordion-body">
											<div class="card-style mb-30">

												<div class="tables-wrapper">
													<div class="row">
														<div class="col-lg-12">


															<div class="table-wrapper table-responsive custom-table">
																<table class="table" id="addHospital" onchange="formOpenHospital();">
																	<thead>
																		<tr>
																			<th>Ser No</th>
																			<th>Place of Working</th>
																			<th>Name of Place</th>
																			<th>Adjunct Place</th>
																			<th>Landline No</th>
																			<th>Mobile No</th>
																			<th>Email</th>
																			<th>Address</th>
																			<th>State</th>
																			<th>District</th>
																			<th>Authority Type</th>
																			<th>Name of Responsible Owner</th>
																			<!-- <th>Date of Practice From</th>
																			<th>Date of Practice To</th> -->
<!-- 																			<th>Action</th> -->

																		</tr>
																		<!-- end table row-->
																	</thead>
																	<tbody id="att_TbbodyNameMed">
																		<tr id="tr_id_attNameMed">
																			<td class="min-width">
																				<div class="lead">

																					<div class="lead-text">
																						<p>1</p>
																					</div>
																				</div>
																			</td>
																			<td class="min-width">
																				<div class="select-style-1">
<!-- 																				<label name="place_of_working1" -->
<!-- 																							id="place_of_working1"></label> -->
																							<p id="place_of_working1"></p>

<!-- 																					<div class="select-position" style="display: none;"> -->
<!-- 																						<select name="place_of_working1" -->
<!-- 																							id="place_of_working1" -->
<!-- 																							class="form-control autocomplete"> -->
<!-- 																							<option value="0">--Select--</option> -->
<%-- 																							<c:forEach var="item" items="${PlaceOfWorking}" --%>
<%-- 																								varStatus="num"> --%>
<%-- 																								<option value="${item.id}" --%>
<%-- 																									name="${item.place_of_working_practitioner}">${item.place_of_working_practitioner}</option> --%>
<%-- 																							</c:forEach> --%>
<!-- 																						</select> -->

<!-- 																					</div> -->
																				</div>
																			</td>
																			<td class="min-width">
																				<!--  <div class="input-style-2" id="pow1"> 
                 <input type="text" class="form-control" id="place_of_working_o1"
																				name="place_of_working_o1" /> -->
																				<div class="input-style-2">
<!-- 																				<label name="place_of_working_name1" -->
<!-- 																							id="place_of_working_name1"></label> -->
																							<p id="place_of_working_name1"></p>
<!-- 																					<input type="text" -->
<!-- 																						class="form-control autocomplete" -->
<!-- 																						id="place_of_working_name1" -->
<!-- 																						name="place_of_working_name1" -->
<!-- 																						class="form-control autocomplete" -->
<!-- 																						placeholder="Enter Name Of Place" /> -->
																				</div>
																				</div>
																			</td>
																				<td class="min-width">
																				<!--  <div class="input-style-2" id="pow1"> 
                 <input type="text" class="form-control" id="place_of_working_o1"
																				name="place_of_working_o1" /> -->
																				<div class="input-style-2">
<!-- 																				<label name="place_of_working_name1" -->
<!-- 																							id="place_of_working_name1"></label> -->
																							<p id="adjunct_place1"></p>
<!-- 																					<input type="text" -->
<!-- 																						class="form-control autocomplete" -->
<!-- 																						id="place_of_working_name1" -->
<!-- 																						name="place_of_working_name1" -->
<!-- 																						class="form-control autocomplete" -->
<!-- 																						placeholder="Enter Name Of Place" /> -->
																				</div>
																				</div>
																			</td>
																			
																			<td class="min-width">
																				<!-- <input type="text" class="form-control" id="landline1"
																		name="landline1" /> -->

																				<div class="input-style-2">
<!-- 																				<label name="landline1" -->
<!-- 																							id="landline1"></label> -->
																							<p id="landline1"></p>
<!-- 																					<input type="text" -->
<!-- 																						class="form-control autocomplete" id="landline1" -->
<!-- 																						name="landline1" maxlength="10" onkeypress="return isNumberOnly(event)" -->
<!-- 																						placeholder="Landline No" /> -->
																				</div>
																			</td>
																			<td class="min-width">
																				<div class="input-style-2">
<!-- 																				<label name="mobileHosp1" -->
<!-- 																							id="mobileHosp1"></label> -->
																							<p id="mobileHosp1"></p>
<!-- 																					<input type="text" -->
<!-- 																						class="form-control autocomplete" id="mobileHosp1" -->
<!-- 																						name="mobileHosp1" maxlength="10" onkeypress="return isNumberOnly(event)" -->
<!-- 																						placeholder="Mobile No" /> -->
																				</div>
																			</td>
																			<td class="min-width">
																				<div class="input-style-2">
<!-- 																				<label name="email1" id="email1"></label> -->
																				<p id="email1"></p>
<!-- 																					<input type="text" -->
<!-- 																						class="form-control autocomplete" id="email1" -->
<!-- 																						name="email1" placeholder="Enter Email Id" /> -->
																				</div>
																			</td>
																			<!-- 		<td class="min-width">

																				<div class="input-style-2">
																					<input type="text" placeholder="Address"
																						id="address1" name="address1">
																				</div> <textarea rows="" cols="" id="address1"
																			name="address1"></textarea>
																			</td> -->

																			<td class="min-width">
																				<div class="input-style-2">
<!-- 																				<label name="hos_address1_1" id="hos_address1_1"></label> -->
																				<p id="hos_address1_1"></p>
<!-- 																					<input type="text" id="hos_address1_1" -->
<!-- 																						name="hos_address1_1" maxlength="100" -->
<!-- 																						class="form-control autocomplete mb2" -->
<!-- 																						autocomplete="off" placeholder="Address Line 1"> -->
<!-- 																						<label name="hos_address2_1" id="hos_address2_1"></label> -->
																						<p id="hos_address2_1"></p>
<!-- 																					<input type="text" id="hos_address2_1" -->
<!-- 																						name="hos_address2_1" maxlength="100" -->
<!-- 																						class="form-control autocomplete mb2" -->
<!-- 																						autocomplete="off" placeholder="Address Line 2"> -->
<!-- 																						<label name="hos_address3_1" id=hos_address3_1></label> -->
																						<p id="hos_address3_1"></p>
<!-- 																					<input type="text" id="hos_address3_1" -->
<!-- 																						name="hos_address3_1" maxlength="100" -->
<!-- 																						class="form-control autocomplete mb2s" -->
<!-- 																						autocomplete="off" placeholder="Address Line 3"> -->
																				</div> <!-- 																	<textarea rows="" cols="" id="address1" name="address1"></textarea> -->
																			</td>



																			<td class="min-width">


																				<div class="select-style-1">
<!-- 																				<label name="hos_state1" id=hos_state1></label> -->
																				<p id="hos_state1"></p>

<!-- 																					<div class="select-position" style="display: none;"> -->
<!-- 																						<select name="hos_state1" id="hos_state1" -->
<!-- 																							class="form-control autocomplete" -->
<!-- 																							onchange="getDistricthos(1);"> -->
<!-- 																							<option value="0">--Select--</option> -->
<%-- 																							<c:forEach var="item" items="${MedStateName}" --%>
<%-- 																								varStatus="num"> --%>
<%-- 																								<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 																							</c:forEach> --%>

<!-- 																						</select> -->

<!-- 																					</div> -->
																				</div>
																			</td>

																					<td class="min-width">
																						<div class="select-style-1">
<!-- 																							<label name="hos_district1" id=hos_district1></label> -->
																							<p id="hos_district1"></p>

<!-- 																							<div class="select-position" style="display: none;"> -->
<!-- 																								<select name="hos_district1" id="hos_district1" -->
<!-- 																									class="form-control autocomplete"><option -->
<!-- 																										value="0">--Select--</option> -->
<!-- 																								</select> -->
<!-- 																							</div> -->
																						</div>
																					</td>





																					<td class="min-width">

																				<div class="select-style-1">
<!-- 																				<label name="authority_type1" id=authority_type1></label> -->
																				<p id="authority_type1"></p>

<!-- 																					<div class="select-position" style="display: none;"> -->
<!-- 																						<select name="authority_type1" -->
<!-- 																							id="authority_type1" -->
<!-- 																							class="form-control autocomplete"> -->
<!-- 																							<option value="0">--Select--</option> -->
<%-- 																							<c:forEach var="item" items="${NameOfResOwner}" --%>
<%-- 																								varStatus="num"> --%>
<%-- 																								<option value="${item.id}" --%>
<%-- 																									name="${item.name_of_res_owner}">${item.name_of_res_owner}</option> --%>
<%-- 																							</c:forEach> --%>
<!-- 																						</select> -->

<!-- 																					</div> -->
																				</div> <%--     <select name="authority_type1" id="authority_type1"
																		class="form-control autocomplete">
																			<option value="0">--Select--</option>
																			<c:forEach var="item" items="${NameOfResOwner}"
																				varStatus="num">
																				<option value="${item.id}"
																					name="${item.name_of_res_owner}">${item.name_of_res_owner}</option>
																			</c:forEach>
																	</select> --%>
																			</td>
																			<td class="min-width">
																				<div class="input-style-2">
<!-- 																				<label name="name_of_res_p1" id=name_of_res_p1></label> -->
																				<p id="name_of_res_p1"></p>
<!-- 																					<input type="text" id="name_of_res_p1" -->
<!-- 																						class="form-control autocomplete" -->
<!-- 																						name="name_of_res_p1" -->
<!-- 																						placeholder="Enter Name of Responsible Owner" /> -->
																				</div>

																			</td>
																			

																			<td class="min-width addminusbut" class="custom-d-none">
																				<ul class="buttons-group mainbtn action">
																					<li><a
																						class="main-btn info-btn-outline btn-hover btn-sm addminusbut"
																						value="ADD" title="ADD" id="id_add_attHospital1"
																						onclick="formOpenHospital(1);"><i
																							class="lni lni-plus"></i></a></li>
																				</ul>
																			</td>
																		</tr>
																		<!-- end table row -->

																	</tbody>
																</table>

																<input type="hidden" id="count_hidden_att_name_med"
																	name="count_hidden_att_name_med"
																	class="form-control autocomplete" value="1">
																<!-- end table -->
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

							<ul class="buttons-group mainbtn">


<!-- 								<li><a href="Regulation_Url"><input type="button" id="preview" -->
<!-- 									class="main-btn secondary-btn btn-hover btn-save" value="Back" -->
<!-- 									></a></li> -->
									
									
									<li><a href="intern_Regulation_Url?id7=${userId}"" type="button" id="preview"
									class="main-btn dark-btn-outline  btn-hover btn-iconic-icon"><i
									class="lni lni-chevron-left"></i>Back</a></li>
									
<!-- 								<li><input type="button" -->
<!-- 									class="main-btn success-btn  btn-hover btn-save" id="update" -->
<!-- 									value="Update" class="custom-d-none" onclick="return validationAdd(1);"></li> -->
<!-- 								<li> -->
<!-- 								<li><input type="submit"  -->
<!-- 									class="custom-d-none" -->
<!-- 									class="main-btn secondary-btn btn-hover btn-save" -->
<!-- 									value="Final Submit" id="final_submit" name="final_submit" onclick="return Validate();"></li> -->


<!-- 								<li><input type="button" id="renew" class="custom-d-none" -->
<!-- 									class="main-btn secondary-btn btn-hover btn-save" value="Renew" -->
<!-- 									onclick="renewFunction();"></li> -->
									
<!-- 									<li><a href="pract_previewUrl"><input type="button" id="preview" -->
<!-- 									class="main-btn secondary-btn btn-hover btn-save" value="Preview" -->
<!-- 									></a></li> -->
									

<!-- 								<li></li> -->

							</ul>

							
							
							
							
<!-- 							<div class="modal fade" id="modelWindow" role="dialog"> -->
<!-- 								<div class="modal-dialog modal-sm vertical-align-center"> -->
<!-- 									<div class="modal-content"> -->
<!-- 										<div class="modal-header"> -->
<!-- 											<button type="button" class="close" id="closeModel" name="closeModel" -->
<!-- 												data-dismiss="modal">&times;</button> -->
<!-- 											<h4 class="modal-title">Upload Documents</h4> -->
<!-- 										</div> -->
<!-- 										<div class="modal-body"> -->
<!-- 											<div class="table-wrapper table-responsive custom-table"> -->
												
												
<!-- 												end table -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										<div class="modal-footer"> -->
<!-- 											<input type="button" id="closeModel2" name="closeModel2" data-dismiss="modal" -->
<!-- 												class="main-btn secondary-btn btn-hover btn-save" value="Close"> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
							<!-- 							HET URMIK CHANGES                                               -->



						</div>
<!-- 						<input type="hidden" id="NRHstatus" name="NRHstatus"> -->

						<input type="hidden" id="Regulationstatus" name="Regulationstatus">

						<input type="hidden" id="p_id" name="p_id" value="${p_id}">
						<input type="hidden" id="parentid" name="parentid"
							value="${parentid}">

						<input type="hidden" id="count_hidden_att_Hospital"
							name="count_hidden_att_Hospital"
							class="form-control autocomplete" value="1">
							<input type="hidden" id="count_hidden_att_His"
							name="count_hidden_att_His"
							class="form-control autocomplete" value="1">
						<input type="hidden" id="SaveDraft" name="SaveDraft"
							class="form-control autocomplete" value="0">

						<!-- end card -->
						
						
						<!-- 							URMIK CHANGES                                               -->
<div class="modal fade custom-modal bd-example-modal-lg" tabindex="-1"
	role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="modelWindow">
	<div class="modal-dialog modal-xl modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="myLargeModalLabel">Upload Attachments</h3>
				<i data-dismiss="modal" id="closeModel" aria-label="Close" class="bi bi-x-lg"></i>
			</div>
			<div class="modal-body custom-modal-table">
				<div class="custom-modal-inner">
					<div class="table-wrapper table-responsive custom-table">
						<div id="dynamicDataTable">
												
												</div>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><button type="button"
							class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
							data-dismiss="modal" aria-label="Close">Close</button></li>
				</ul>
			</div>

		</div>
	</div>
</div>




					</form:form>
				</div>
				<!-- end col -->

			</div>
			<!-- end row -->
		</div>


	</div>
	<!-- end container -->
	
	

</section>
<!-- regulation components end -->
<!-- The Modal -->

<div class="modal fade custom-modal custom-modal-pdf" tabindex="-1"
	aria-labelledby="myLargeModalLabel" id="exampleModal"
	aria-hidden="true">
	<div
		class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Document Preview</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<div class="custom-modal-inner" id="headData1" name="headData1">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<ul class="buttons-group mainbtn"  id="dlall_pdf"   >
 							 							 </ul>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-12 text-center">

							<div id="pdfmodelcanvas"></div>

						</div>
					</div>
				</div>


			</div>
			<div class="modal-footer" id="modal-footer"></div>


		</div>
	</div>
</div>
			



<script nonce="${cspNonce}" type="text/javascript">

function isAadhar(e,evt) {
	if(e.value==0 || e.value=="null" || e.value == null){
		e.value="";
		}

	var bool=isNumber(evt);	
	if(bool){
	  var value = e.value;
	  value = value.replace(/\D/g, "").split(/(?:([\d]{4}))/g).filter(s => s.length >0 ).join("-");
	  e.value=value;
	  return true;
	  }
	  else{
	  return false;}
	}
function isNumber(evt) {
	
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	return false;
	}
	return true;
	}
	 
 
$(document).ready(function() {
	
	 
	$("#userId").val(${userId}); 
     var userId =	${userId};
     
     $("#userId").val(${userId}); 
     var userId =	${username};
  //janki
     $.post('getayusAbhaDatalistStudent?' + key + "=" + value,{userId:userId},function(k) {
   		var institute_name = k[0]["institute_name"];
    	 $("#insti_nam1").text(institute_name);
  	});
      
	$("#nationality").val(6);
 		if("${setDataCMD.size()}" != 0){
 		setAll();	
 		}
	  
 
var valid_dt = '${valid_dt}' ;
var y = valid_dt.substring(0,4);
var m = valid_dt.substring(5,7);
var d = valid_dt.substring(8,10);
var valid_dt1 = d+"/"+m+"/"+y;
 
var today = new Date();
var yyyy = today.getFullYear();
var mm = today.getMonth() + 1; // Months start at 0!
var dd = today.getDate();

if (dd < 10) dd = '0' + dd;
if (mm < 10) mm = '0' + mm;

today = dd + '/' + mm + '/' + yyyy;
 

var valid_dt2=valid_dt.split('/').reverse().join('-');  
var today2=today.split('/').reverse().join('-'); 
   
   
   
   if (valid_dt2  <= today2  && '${hid}' == "3"){
	   $("#renew").show();
	   $("#draft").hide();
		$("#final_submit").hide();
		$("#update").hide();
		 document.getElementById("first_name").readOnly = true;
		 document.getElementById("nationality").readOnly = true;
		 document.getElementById("father_name").readOnly = true;
		 document.getElementById("dob").readOnly = true;
   }
   else	if("${hid}" == "3" && valid_dt2  >= today2){
			$("#draft").hide();
			$("#final_submit").show();
			$("#update").show();
			$("#renew").hide();
		 document.getElementById("first_name").readOnly = true;
		 document.getElementById("father_name").readOnly = true;
		 $("#nationality").attr("disabled","disabled");
		 document.getElementById("dob").readOnly = true;
	}
   
   if("${hid}" != "3" && valid_dt2  <= today2){
	//	datepicketDate('dob');
	}
		
// 		if("${CheckNRH.size()}" != 0){
// 	//		$("#NRHstatus").val("${CheckNRH[0]['registration']}");
// 		}else{
// 				$("#NRHstatus").val("0");
// 		}
		

	});
	
// $("input[name = 'registration_for']").click(function() {     
// 	var input = $("input[name=registration_for]:checked").val();
// 	if(input == 1){
// // 		$("#valid").show();
// 		$("#registration_for_type").val(1);
// 	}else{
// // 		$("#valid").hide();
// 		$("#registration_for_type").val(0);

// 	}
// })

// function validateUptoF(){
// 	var date_reg = $("#date_of_reg").val();
	
// 	if(date_reg == "" || date_reg == "DD/MM/YYYY"){
// 		alert("Please Select Date Of First Registration First");
// 	}
	
	
	
// }
$('#draft').click(function(){
	$("#SaveDraft").val();
});
$('#update').click(function(){

	$("#Regulationstatus").val(0);
});
$('#final_submit').click(function(){
	$("#Regulationstatus").val(1);
});


	</script>

<script nonce="${cspNonce}" type="text/javascript">


	function divN(obj){
		var id = obj.id;
	 
		 var sib_id = $("#"+id).parent().parent().next().attr('id');
		var hasC=$("#"+sib_id).hasClass("show");
			$(".panel-collapse").removeClass("show");
			 $('.droparrow').each(function(i, obj) {
				 $("#"+obj.id).attr("class", "droparrow collapsed");
				 });
		
			
			if (hasC) {	
			$("#"+id).addClass( " collapsed");		 
			}  else {				
				$("#"+sib_id).addClass("show");	
				$("#"+id).removeClass("collapsed");
		    }
 	}

	
	function isNumberOnly(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	    return true;
	}
	 
	function formOpenNameMed(R){
 
		//if(validateAddMoreMedical(R)){
		 $("#addNameOfMed").show();
 			 $("#id_add_attNameMed"+R).hide();
			 $("#att_id_removeNameMed"+R).hide();
			 att=0;
			 att= parseInt(R)+1;
			 if(att < 51){
				 $("input#count_hidden_att_name_med").val(att);//current serial No
				 $("table#addNameOfMed").append('<tr align="center" id="tr_id_attNameMed'+att+'"><td class="min-width"><div class="lead"><div class="lead-text"><p>'+att+'<p></div></div></td>'
				 	+'<td class="min-width"><p id="typeOfDegree'+att+'"></p></td>'
				 	/*  <select name="typeOfDegree'+att+'" id="typeOfDegree'+att+'" class="form-control autocomplete" onchange="getDegreeName(this,'+att+')"> <option value="0">--Select--</option> <c:forEach var="item" items="${TypeOfDegree}" varStatus="num">	<option value="${item.id}" name="${item.type_of_degree}">${item.type_of_degree}</option> </c:forEach></select></td>' */
// 				 	+'<td class="min-width"><div class="select-style-1"><div class="select-position"><select name="DegreeName'+att+'" id="DegreeName'+att+'" class="form-control autocomplete"><option value="0">--Select--</option> </select></div></div></td>'
					+'<td class="min-width"><p id="DegreeName'+att+'"></p></td>'
				 	/* <select name="DegreeName'+att+'" id="DegreeName'+att+'" class="form-control autocomplete"> <option value="0">--Select--</option> </select> </td>' */
// 				 	+'<td class="min-width"><div class="input-style-2"><input type="month" id="monthYearOfDegree'+att+'" name="monthYearOfDegree'+att+'" class="form-control-sm form-control effect-9 hasDatepicker"></div</td>'
				 	+'<td class="min-width"><p id="monthYearOfDegree'+att+'"></p></td>'
				 	/* <input type="month" id="monthYearOfDegree'+att+'" name="monthYearOfDegree'+att+'"></td>' */
//  				 +'<td class="min-width"><span class="auto-input" id="NameOfUniversity'+att+'"></span></td>'
					+'<td class="min-width">'
  					+'<select id="NameOfUniversity'+att+'" i name="NameOfUniversity'+att+'"  class="auto-input" value="" class="custom-d-none">'
 				 	+'	<option value="0">--Select--</option>'
 				 	+'	<c:forEach var="item" items="${getInstituteList}" varStatus="num">'
 				 	+'	<option value="${item.id}" name="${item.university_name}">${item.university_name}</option>'
 				 	+'</c:forEach>'
					+'	</select>'
					+' <p  id="NameOfUniversity1_hid'+att+'" ></p> '
					+'</td>'
				 	
				 	+'<td class="min-width addminusbut">'
				 	+'<ul class="buttons-group mainbtn action daobtn">'
				 	+'<li><a class="main-btn success-btn btn-hover btn-sm addminusbut" '
				 	+'onclick="download_file('+att+');" id="downloadbtn'+att+'" title="Downlaod">'
				 	+'<i class="bi bi-file-pdf"></i></a>'
				 	+'<input type="hidden" id="file_id'+att+'" name="file_id'+att+'"></li></ul>'
				 	+'	</td>'
				 	+'<td class="custom-d-none"><ul class="buttons-group mainbtn"><li><a class="main-btn success-btn btn-hover btn-sm addminusbut value = "ADD" title = "ADD" id = "id_add_attNameMed'+att+'" onclick="formOpenNameMed('+att+');">'
                   	+'<i class="lni lni-plus"></i></a></li></ul></td>'
				 	
				 	/* <input type="text" id="NameOfUniversity'+att+'" name="NameOfUniversity'+att+'" value="" class="form-control autocomplete" autocomplete="off" onkeypress="AutoCompleteNameOfUniversity(this);"></td>' */
// 				 	+'<td class="min-width"><div class="input-style-2"><input type="button" id="btnmodel'+att+'" class="main-btn secondary-btn btn-hover btn-save" value="ATTACHMENT"></div></td>'
				 	/* <input type="file" id="attachment'+att+'" name="attachment'+att+'" accept="image/*" onchange="attachmentDetails(this,'+att+');"></td>'  */
// 				 	+'<td class="min-width"><div class="input-style-2"><input type="button" id="btnmodel'+att+'" class="main-btn secondary-btn btn-hover btn-save" value="ATTACHMENT"></div></td>'
				 	
// 				 	+'<td class="min-width"><div class="action"><ul class="buttons-group mainbtn"><li><a class="main-btn info-btn-outline btn-hover btn-sm addminusbut" value = "ADD" title = "ADD" id = "id_add_attNameMed'+att+'" onclick="formOpenNameMed('+att+');"><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn-outline btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeNameMed'+att+'" onclick="formopen_re_NameMed('+att+');"><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
				 	/* <a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_attNameMed'+att+'" onclick="formOpenNameMed('+att+');" ><i class="fa fa-plus"></i></a> <a style="margin-right: 0px;"class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeNameMed'+att+'" onclick="formopen_re_NameMed('+att+');"><i class="fa fa-trash"></a></td>'  */
				 	+'</tr>');
				
				 
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
				}else{
						alert("Please Enter max 50 Quantity");
						 if ( att == 51){
							 att = att-1; 
							 $("#rp_id_remove"+att).show();
						 }	   
				}
		
		//}
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
	
 
	function formOpenHospital(R){
		//if(validateAddMoreHospital(R)){
		 $("#addHospital").show();
			 $("#id_add_attHospital"+R).hide();
			 $("#att_id_removeHospital"+R).hide();
			 att=0;
			 att= parseInt(R)+1;
			 if(att < 51){
				 $("input#count_hidden_att_Hospital").val(att);//current serial No
				 $("table#addHospital").append('<tr align="center" id="tr_id_attHospital'+att+'"><td class="min-width"><div class="lead"><div class="lead-text"><p>'+att+'</p></div></div></td>'
				 	+'<td class="min-width"><p id="place_of_working'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="place_of_working_name'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="adjunct_place'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="landline'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="mobileHosp'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="email'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="hos_address1_'+att+'"></p><p id="hos_address2_'+att+'"></p><p id="hos_address3_'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="hos_state'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="hos_district'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="authority_type'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="name_of_res_p'+att+'"></p></td>'
				 	+'<td class="min-width" class="custom-d-none"><td class="min-width addminusbut" class="custom-d-none"><ul class="buttons-group mainbtn action"><li><a class="main-btn info-btn-outline btn-hover btn-sm addminusbut" value="ADD" title="ADD" id="id_add_attHospital'+att+'" onclick="formOpenHospital('+att+');"><i class="lni lni-plus"></i></a></li></ul></td></td>'
				 	+'</tr>');
			 }
		//}
	}
  
	function calculate_age(obj){    	
	    var from_d=$("#"+obj).val();
	    var from_d1=from_d.substring(6,10);
	    var from_d2=from_d.substring(3,5);
	    var from_d3=from_d.substring(0,2);
	    var frm_d = from_d3+"-"+from_d2+"-"+from_d1;         
	   
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
	    
	}
	
	function FunctionState(val){
		var roleid = $("#"+val.id).val();
		$.post('getDistrictOnstatePreview?' + key + "=" + value,{roleid:roleid},function(k) {
			var options = '';
			for (var i = 0; i < k.length; i++) {
				options += '<option value="'+k[i].district_id+'" name="' + k[i].district_name+ '" >'+ k[i].district_name + '</option>';
			}
			$("#per_district").html(options);
			$("#per_state1").text(name);
		});		
}
	function getDistrictper() {
		var selval = $("#per_state").val();
		$("select#per_district").empty();
		$ .post( "getDistrictOnstatePreview?" + key + "=" + value, { selval : selval },
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
 	function getDistrictpre() {
		var selval = $("#pre_state").val();
		$("select#pre_district").empty();
		$ .post( "getDistrictOnstatePreview?" + key + "=" + value,
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
							$("select#pre_district").html(options);
						});
	}
	
	
	function getDistricthos(R) {
		var selval = $("#hos_state"+R).val();
 		$ .post( "getDistrictOnstatePreview?" + key + "=" + value,
						{ selval : selval
						},
						function(j) {
 							var options = '<option value="' + "0" + '">'
							+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i].district_id + '" name="'+j[i].district_id+'" >'
										+ j[i].district_name + '</option>';
							}
							$("select#hos_district"+R).html(options);
							
						});
	}
	var valid_dt = '${valid_dt}' ;
	var today = new Date();
	
	function setAll(){
		 
		if("${setDataCMD[0]['first_name']}" != null && "${setDataCMD[0]['first_name']}" != ""){
			$("#first_name").text("${setDataCMD[0]['first_name']}");
			if (valid_dt  <= today  && '${hid}' == "3"){
				$("#first_name").attr("disabled","disabled");
				   }else{
						if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
							$("#first_name").attr("disabled","disabled");

				   }
		}
		if("${setDataCMD[0]['father_name']}" != null && "${setDataCMD[0]['father_name']}" != ""){
			$("#father_name").text("${setDataCMD[0]['father_name']}");
			if (valid_dt  <= today  && '${hid}' == "3"){
				$("#father_name").attr("disabled","disabled");
				   }else{
						if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
							$("#father_name").attr("disabled","disabled");

				   }
		}
 	 	if("${setDataCMD[0]['dob']}" != null && "${setDataCMD[0]['dob']}" != ""){
			$("#dob").val("${setDataCMD[0]['dob']}");
			$("#dob1").text("${setDataCMD[0]['dob']}");
			if (valid_dt  <= today  && '${hid}' == "3"){
				$("#dob").attr("disabled","disabled");
				   }else{
						if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
							$("#dob").attr("disabled","disabled");

				   }
		}
		calculate_age("dob");
		
		
		var g = ${setDataCMD[0]['gender']};
		 
		if(g== "1"){
			$("#gender").text("MALE");
		}
		else if(g== "2"){
			$("#gender").text("FEMALE");
		}
		else{
			$("#gender").text("OTHER");
		}
		
		if("${setDataCMD[0]['mo_no']}" != null && "${setDataCMD[0]['mo_no']}" != ""){
			$("#mo_no").text("${setDataCMD[0]['mo_no']}");
		}
		if("${setDataCMD[0]['email_id']}" != null && "${setDataCMD[0]['email_id']}" != ""){
			$("#email_id").text("${setDataCMD[0]['email_id']}");
		}
		if("${setDataCMD[0]['aadhaar_no']}" != null && "${setDataCMD[0]['aadhaar_no']}" != ""){
			$("#aadhaar_no").text("${setDataCMD[0]['aadhaar_no']}");
			if (valid_dt  <= today  && '${hid}' == "3"){
				$("#aadhaar_no").attr("disabled","disabled");
				   }else{
						if($("#draft").is(":hidden") || $("#update").is(":hidden") || $("#final_submit").is(":hidden"))
							$("#aadhaar_no").attr("disabled","disabled");

				   }
		}
		
		if("${setDataCMD[0]['nationality']}" != null && "${setDataCMD[0]['nationality']}" != ""){
			$("#nat").text("${setDataCMD[0]['nationality']}");
		}
		
		if("${setDataCMD[0]['per_state']}" != null && "${setDataCMD[0]['per_state']}" != ""){
			$("#per_state").text("${setDataCMD[0]['per_state']}");
		}
	 		if("${setDataCMD[0]['per_district']}" != null && "${setDataCMD[0]['per_district']}" != ""){
			$("#per_district").text("${setDataCMD[0]['per_district']}");
		}
		
		if("${setDataCMD[0]['per_address_details1']}" != null && "${setDataCMD[0]['per_address_details1']}" != ""){
			$("#per_address").text("${setDataCMD[0]['per_address_details1']}");
		}
		if("${setDataCMD[0]['per_address_details2']}" != null && "${setDataCMD[0]['per_address_details2']}" != ""){
			$("#per_address2").text("${setDataCMD[0]['per_address_details2']}");
		}
		if("${setDataCMD[0]['per_address_details3']}" != null && "${setDataCMD[0]['per_address_details3']}" != ""){
			$("#per_address3").text("${setDataCMD[0]['per_address_details3']}");
		}
		if("${setDataCMD[0]['per_pincode']}" != null && "${setDataCMD[0]['per_pincode']}" != ""){
			$("#per_pincode").text("${setDataCMD[0]['per_pincode']}");
		}
		if("${setDataCMD[0]['pre_address_details1']}" == "${setDataCMD[0]['per_address_details1']}" && "${setDataCMD[0]['pre_address_details2']}" == "${setDataCMD[0]['per_address_details2']}"  && "${setDataCMD[0]['pre_address_details3']}" == "${setDataCMD[0]['per_address_details3']}" && "${setDataCMD[0]['pre_state']}" == "${setDataCMD[0]['per_state']}" && "${setDataCMD[0]['per_district']}" == "${setDataCMD[0]['pre_district']}"   && "${setDataCMD[0]['per_pincode']}" == "${setDataCMD[0]['pre_pincode']}")
		{
			$("#check_address").click();
		}
		if("${setDataCMD[0]['pre_state']}" != null && "${setDataCMD[0]['pre_state']}" != ""){
			$("#pre_state").text("${setDataCMD[0]['pre_state']}");
		}
		 if("${setDataCMD[0]['pre_district']}" != null && "${setDataCMD[0]['pre_district']}" != ""){
			$("#pre_district").text("${setDataCMD[0]['pre_district']}");
		}
		
		if("${setDataCMD[0]['pre_address_details1']}" != null && "${setDataCMD[0]['pre_address_details1']}" != ""){
			$("#pre_address").text("${setDataCMD[0]['pre_address_details1']}");
		}
		if("${setDataCMD[0]['pre_address_details2']}" != null && "${setDataCMD[0]['pre_address_details2']}" != ""){
			$("#pre_address2").text("${setDataCMD[0]['pre_address_details2']}");
		}
		if("${setDataCMD[0]['pre_address_details3']}" != null && "${setDataCMD[0]['pre_address_details3']}" != ""){
			$("#pre_address3").text("${setDataCMD[0]['pre_address_details3']}");
		}
 		if("${setDataCMD[0]['pre_pincode']}" != null && "${setDataCMD[0]['pre_pincode']}" != ""){
			$("#pre_pincode").text("${setDataCMD[0]['pre_pincode']}");
		}
// 		----------corresponding address

		if("${setDataCMD[0]['curr_address']}" != null && "${setDataCMD[0]['curr_address']}" != ""){
			$("#curr_address").text("${setDataCMD[0]['curr_address']}");
		}
		if("${setDataCMD[0]['curr_address2']}" != null && "${setDataCMD[0]['curr_address2']}" != ""){
			$("#curr_address2").text("${setDataCMD[0]['curr_address2']}");
		}
		if("${setDataCMD[0]['curr_address3']}" != null && "${setDataCMD[0]['curr_address3']}" != ""){
			$("#curr_address3").text("${setDataCMD[0]['curr_address3']}");
		}
		
		if("${setDataCMD[0]['curr_state']}" != null && "${setDataCMD[0]['curr_state']}" != ""){
			$("#curr_state").text("${setDataCMD[0]['curr_state']}");
		}
		 if("${setDataCMD[0]['curr_district']}" != null && "${setDataCMD[0]['curr_district']}" != ""){
			$("#curr_district").text("${setDataCMD[0]['curr_district']}");
		}
		
		if("${setDataCMD[0]['curr_pincode']}" != null && "${setDataCMD[0]['curr_pincode']}" != ""){
			$("#curr_pincode").text("${setDataCMD[0]['curr_pincode']}");
		}
		 
		if("${setDataCMD[0]['alt_mo_no1']}" != null && "${setDataCMD[0]['alt_mo_no1']}" != ""){
			$("#alt_mo_no1").text("${setDataCMD[0]['alt_mo_no1']}");
		}
		
		if("${setDataCMD[0]['alt_mo_no2']}" != null && "${setDataCMD[0]['alt_mo_no2']}" != ""){
			$("#alt_mo_no2").text("${setDataCMD[0]['alt_mo_no2']}");
		}
		
		if("${setDataCMD[0]['alt_email_id1']}" != null && "${setDataCMD[0]['alt_email_id1']}" != ""){
			$("#alt_email_id1").text("${setDataCMD[0]['alt_email_id1']}");
		}
		
		if("${setDataCMD[0]['alt_email_id2']}" != null && "${setDataCMD[0]['alt_email_id2']}" != ""){
			$("#alt_email_id2").text("${setDataCMD[0]['alt_email_id2']}");
		}
		 
		if("${setDataCMD[0]['photo_path']}" != null && "${setDataCMD[0]['photo_path']}" != ""){
			$("#upload_img_hid").val("${setDataCMD[0]['photo_path']}");
 			var idforimg = '${setDataCMD[0].id}';
			 $('#identity_image_preview').attr("src", "MedicalImagePath11?i_id="+idforimg+" ");
 	}
		
//  		if("${setDataCMD[0]['date_of_reg']}" != null && "${setDataCMD[0]['date_of_reg']}" != ""){
// 			$("#date_of_reg").text("${setDataCMD[0]['date_of_reg']}");
// 			validateUptoR();
// 		}
		
// 		if("${setDataCMD[0]['registration_for_type']}" != null && "${setDataCMD[0]['registration_for_type']}" != ""){
// 			$("input[name = 'registration_for'][value='${setDataCMD[0]['registration_for_type']}']").click();
// 			if("${setDataCMD[0]['registration_for_type']}" == "1"){
// 				$("#valid").show();
// 				if("${setDataCMD[0]['valid_up_to']}" != null && "${setDataCMD[0]['valid_up_to']}" != ""){
// 					$("#valid_up_to").text("${setDataCMD[0]['valid_up_to']}");
// 				}	
// 			}
			
// 		}
	 		//ADDMORE MEDICAL
		if("${setAddMoreMedicalCMD.size()}" > 0){
 			$("#count_hidden_att_name_med").text("${setAddMoreMedicalCMD.size()}");
			$("#count_hidden_att_name_med1").text("${setAddMoreMedicalCMD.size()}");
			var i = 1;
			<c:forEach var="j" items="${setAddMoreMedicalCMD}" varStatus="num">
			
			var id = "${j[0]}";
			var name_of_institute = "${j[1]}";
 			var month_and_year_of_degree = "${j[2]}";
			var type_of_degree = "${j[3]}";
			var degree_name = "${j[4]}";
			var NameOfUniversity1_hid = "${j[5]}";
			
	 		if(type_of_degree != null && type_of_degree.trim() != ""){
				$("#typeOfDegree"+i).text(type_of_degree);
				//getDegreeNameAddMore("typeOfDegree"+i,i);
			}else{
				$("#typeOfDegree"+i).text(0);
			}
	 		if(degree_name != null && degree_name.trim() != ""){
				$("#DegreeName"+i).text(degree_name);
			}else{
				$("#DegreeName"+i).text(0);
	
			}
// 			if(name_of_institute != null && name_of_institute.trim() != ""){
// 				$("#NameOfUniversity"+i).text(name_of_institute);
// 			}else{
// 				$("#NameOfUniversity"+i).text("");
// 			}
	 		if(NameOfUniversity1_hid != null && NameOfUniversity1_hid.trim() != ""){
				$("#NameOfUniversity1_hid"+i).text(NameOfUniversity1_hid);
 			}else{
				$("#NameOfUniversity1_hid"+i).text(0);
			}

	 		if(name_of_institute != null && name_of_institute.trim() != ""){
				$("select#NameOfUniversity"+i).val(name_of_institute);
				 $("#NameOfUniversity"+i).attr("disabled","disabled");
			}else{
				$("#NameOfUniversity"+i).text("");
				 $("#NameOfUniversity"+i).attr("disabled","disabled");
			}
// 			if(attachment_path != null && attachment_path.trim() != ""){
// 				$("#attachment_hid"+i).val(attachment_path);
// 			}
			if(month_and_year_of_degree != null && month_and_year_of_degree.trim() != ""){
				$("#monthYearOfDegree"+i).text(month_and_year_of_degree);
			}
			if(id != null && id.trim() != ""){
				$("#file_id"+i).val(id);
			}
 			
		if("${setAddMoreMedicalCMD.size()}" > i)
		$("#id_add_attNameMed"+i).click();	
			if("${setAddMoreMedicalAttachmentChildCMD.size() > 0}"){
			var k = 1;
			<c:forEach var="k" items="${setAddMoreMedicalAttachmentChildCMD}" varStatus="num">
		 	var parent_id = "${k['parent_id']}";
			var path_of_att = "${k['attachment']}";
			var name_of_att = "${k['name_of_attachment']}";
 			if(parent_id == id){
				dynamicTable(i,k);
				if(k > 1){
					$("#id_add_attDoc"+i+"_"+(k-1)).click();
				}
				if(name_of_att != null && name_of_att.trim() != ""){
					$("#NameOfAttachment"+i+"_"+k).val(name_of_att);
					   if (valid_dt  <= today  && '${hid}' == "3"){
					$("#NameOfAttachment"+i+"_"+k).attr("disabled","disabled");
					   }else{
							if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
								$("#NameOfAttachment"+i+"_"+k).attr("disabled","disabled");
 					   }

				}
				
				if(path_of_att != null && path_of_att.trim() != ""){
					$("#attachmentDoc_hid"+i+"_"+k).val(path_of_att);
					   if (valid_dt  <= today  && '${hid}' == "3"){
					$("#attachmentDoc_hid"+i+"_"+k).attr("disabled","disabled");
					   }else{
							if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
								$("#attachmentDoc_hid"+i+"_"+k).attr("disabled","disabled");
					   }
				}
				   if (valid_dt  <= today  && '${hid}' == "3"){
				$("#attachmentDocument"+i+"_"+k).attr("disabled","disabled");
				   }else{
						if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
							$("#attachmentDocument"+i+"_"+k).attr("disabled","disabled");
				   }
				$("#count_hidden_att_doc"+i).val(k);
				k++;
			}
			</c:forEach>
			}
			   if (valid_dt  <= today  && '${hid}' == "3"){
			var temp = k-1;
			$("#att_id_removeattDoc"+i+"_"+temp).hide();	
			   }
			i++;
	 		</c:forEach>
	 	   if (valid_dt  <= today  && '${hid}' == "3"){
			 $("#att_id_removeNameMed"+"${setAddMoreMedicalCMD.size()}").hide();	
	 	   }
		}
  	
	}
 	
	function photoValidate(){
		var path = $("#photo_path").val();
		$("#upload_img_hid").val(path); 
	}
	  
	function formOpenattDoc(R,index){
 		 	 $("#addAttDoc"+R).show();
 			 $("#id_add_attDoc"+R+"_"+index).hide();
			 $("#att_id_removeattDoc"+R+"_"+index).hide();
 			 att=0;
			 att= parseInt(index)+1;
 			 var seq = ""+R+"_"+att+"";
			 if(att < 51){
				 $("input#count_hidden_att_doc"+R).val(att);//current serial No
				 $("table#addAttDoc"+R).append('<tr align="center" id="tr_id_attDoc'+seq+'"><td>'+att+'</td>'
				 	+'<td class="min-width"><div class="input-style-2"><input type="text" id="NameOfAttachment'+seq+'" name="NameOfAttachment'+seq+'" value="" class="form-control autocomplete" autocomplete="off" onkeypress="AutoCompleteNameOfUniversity(this);" placeholder="Name of Attachment"></div></td>'
				 	/* <input type="text" id="NameOfUniversity'+att+'" name="NameOfUniversity'+att+'" value="" class="form-control autocomplete" autocomplete="off" onkeypress="AutoCompleteNameOfUniversity(this);"></td>' */
				 	+'<td class="min-width"><div class="input-style-2"><input type="file" id="attachmentDocument'+seq+'" name="attachmentDocument'+seq+'" accept=".PDF" onchange="attachmentDetails(this,'+seq+');" class="form-control"><input type="hidden" id="attachmentDoc_hid'+seq+'" name="attachmentDoc_hid'+seq+'" class="form-control"></div></td>' 
				 	/* <input type="file" id="attachment'+att+'" name="attachment'+att+'" accept="image/*" onchange="attachmentDetails(this,'+att+');"></td>'  */
				 	+'<td class="min-width"><div class="action"><ul class="buttons-group mainbtn"><li><a class="main-btn info-btn-outline btn-hover btn-sm addminusbut" value = "ADD" title = "ADD" id = "id_add_attDoc'+seq+'" name = "id_add_attDoc'+seq+'" onclick="formOpenattDoc('+R+','+att+');"><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn-outline btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeattDoc'+seq+'" onclick="formopen_re_attDoc('+R+','+att+');"><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
				 	/* <a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_attNameMed'+att+'" onclick="formOpenNameMed('+att+');" ><i class="fa fa-plus"></i></a> <a style="margin-right: 0px;"class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeNameMed'+att+'" onclick="formopen_re_NameMed('+att+');"><i class="fa fa-trash"></a></td>'  */
				 	+'</tr>');
			 
				}else{
						alert("Please Enter max 50 Quantity");
						 if ( att == 51){
							 att = att-1; 
							 $("#att_id_removeattDoc"+att).show();
						 }	   
				}
		
		}
		  
	
	function dynamicTable(R,index){
	 
		var length = $("#count_hidden_att_name_med").val();
		for(var i = 1 ;i<=length;i++){
		$("#dynamicDataTable"+i).hide();
		}
		
		if($("#dynamicDataTable"+R).length){
			$("#dynamicDataTable"+R).show();

		}else{
		
		$("#dynamicDataTable").append("<div id='dynamicDataTable"+R+"'></div>");

		var seq = ""+R+"_"+index+"";
 
		$("div#dynamicDataTable"+R).append('<table class="table model-table" id="addAttDoc'+R+'"><thead><tr><th>Ser No</th>'
				+'<th>Name of Attachment</th>'
				+'<th>Attachment</th>'
				+'<th>Action</th>'
				+'</tr>'
				+'</thead>'
				+'<tbody id="att_TbbodyattDoc'+seq+'">'
				+'	<tr id="tr_id_attDoc'+seq+'">'
				+'	 <td class="min-width">'
				+'	 <div class="lead">'
 				+'	 <div class="lead-text">'
				+'	 <p>1</p>'
				+'	 </div>'
				+'	 </div>'
				+'	 </td>'
				+'	<td class="min-width">'
				+' <div class="input-style-2">'
				+' <input type="text" id="NameOfAttachment'+seq+'"'
				+'	 name="NameOfAttachment'+seq+'" value=""'
				+' class="form-control autocomplete" autocomplete="off"'
				+' onkeypress="AutoCompleteNameOfUniversity(this);"'
				+' placeholder="Name of Attachments">'
				+' </div> </td>'
				+'	<td class="min-width">'
				+' <div class="input-style-2">'
				+' <input type="file" id="attachmentDocument'+seq+'" name="attachmentDocument'+seq+'"'
				+' accept=".PDF" onchange="attachmentDetails(this,1);"'
				+' class="form-control"> <input type="hidden"'
				+' id="attachmentDoc_hid'+seq+'" name="attachmentDoc_hid'+seq+'"'
				+' class="form-control">'
				+' </div> '
				+' </td>'
				+' <td class="min-width addminusbut">'
				+'	<div class="action">'
				+'	<ul class="buttons-group mainbtn">'
				+' <li><a'
				+' class="main-btn info-btn-outline btn-hover btn-sm addminusbut"'
				+' value="ADD" title="ADD" id="id_add_attDoc'+seq+'" name="id_add_attDoc'+seq+'"' 
				+' onclick="formOpenattDoc('+R+',1);"><i'
				+' class="lni lni-plus"></i></a></li>'
 				+'	</ul>'
				+'	</div>'
				+'</td> </tr> </tbody> </table>'
				+'<input type="hidden" id="count_hidden_att_doc'+R+'" name="count_hidden_att_doc'+R+'" class="form-control autocomplete" value="1">');
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
	
	function download_file(obj) {
		var val= $("#file_id"+obj).val();
		$.post('getFilePath2?' + key + "=" + value,{id:val},function(k) {
			var file = k.split(",");
			for(var i=0;i<file.length;i++){
			var pdfView="kmlFileDownloadDemo2?path="+file[i];
		    fileName="TEST_DOC";
		    fileURL=pdfView;
		    if (!window.ActiveXObject) {
		        var save = document.createElement('a');
		        save.href = fileURL;
		        save.target = '_blank';
		        var filename = fileURL.substring(fileURL.lastIndexOf('/')+1);
		        save.download = fileName || filename;
			       if ( navigator.userAgent.toLowerCase().match(/(ipad|iphone|safari)/) && navigator.userAgent.search("Chrome") < 0) {
						document.location = save.href; 
					}else{
				        var evt = new MouseEvent('click', {
				            'view': window,
				            'bubbles': true,
				            'cancelable': false
				        });
				        save.dispatchEvent(evt);
				        (window.URL || window.webkitURL).revokeObjectURL(save.href);
					}	
		    }
		    else if ( !! window.ActiveXObject && document.execCommand)     {
		        var _window = window.open(fileURL, '_blank');
		        _window.document.close();
		        _window.document.execCommand('SaveAs', true, fileName || fileURL)
		        _window.close();
		    }
			}
 	});
		location.reload();
	}
	function download_file_view(obj) {
		
		var val= $("#file_id"+obj).val();
		
		$.post('getattfilesToPreview?' + key + "=" + value,{id:val},function(k) {
			 $("#dlall_pdf").empty();
			 for(var p=0;p<k.length;p++){
				 
// 				 console.log("p-------->    "+p)
				 
				 $("#dlall_pdf").append(
							'<a class="main-btn success-btn-outline btn-hover btn-sm addminusbut" id="downloadbtnnn'+p+'" title="Downlaod">'
			   				+'<i class="bi bi-file-pdf"></i></a><input type="hidden" id="fid'+p+'" name="fid'+p+'" value="'+k[p][1]+'">'
					);
				 adddownloadOnclick(p);
				
			 }
			
		});
	 		
		var pdf1="attachmentfiledownload?kmlId2="+val;
// 		$("#my_pdf_viewer").show();
		
		$("#exampleModal").modal('show');
		
		PDFView(pdf1);
	 	
	}
	
	function download_file_view2(obj) {
		
		var val= $("#fid"+obj).val();
		var pdf1="attachmentfiledownload?kmlId2="+val;
		PDFView(pdf1,val,0,0);
	 		
	}
 
// 	===================for view URMIK

	function PDFView(path1,idx,val,field){
		
//	 	$("div#my_pdf_viewer").show();
// 		if ($("#my_pdf_viewer").hasClass("d-none")) {
// 		 $( "#my_pdf_viewer" ).removeClass("d-none")
// 	}
		$("#pdfmodelcanvas").empty();
	$("#modal-footer").empty();
	$("#exampleModal").modal('show');
	
		var canvas_data = "";
		
		canvas_data += '<div id="my_pdf_viewer'+idx+'" class="custom-pdf-viewer">'										
								+'<div id="canvas_container">'
								+'<div>'
								+'<canvas id="pdf_renderer'+idx+'"  width="600px" height="500px" ></canvas>'
								+'<input type="hidden" value="'+idx+'" id="PicturePDFId" /> <input'
								+'	type="hidden" value="'+val+'" id="val1" /> <input type="hidden"'
								+'	value="'+field+'" id="fildname1" />'
								+'</div>'
								+'</div>'
								+'</div>';

	$("#pdfmodelcanvas").append(canvas_data);
						
		var btn="";
			btn +=   '<div class="modal-footer-left">'
								+'<ul class="buttons-group">'
								+'<li><a  id="zoom_in'+idx+'"	class="main-btn active-btn btn-hover btn-sm btnzoomin"'
								+'title="Zoom In"><i class="lni lni-zoom-in"></i></a></li>'
								+'<li><a  id="zoom_out'+idx+'"	class="main-btn active-btn btn-hover btn-sm btnzoomout"'
								+'title="Zoom Out"><i class="lni lni-zoom-out"></i></a></li>'
								+'</ul>'
								+'</div>'
								+'<div class="modal-footer-center">'
								+'<ul class="buttons-group" >'
								+'<li class="footer_btn_control"><a id="go_previous'+idx+'" type="button"'
								+'class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i class="lni lni-chevron-left"'
								+'></i>Previous</a></li>'
								+'<li><a type="button" class="main-btn dark-btn n "><span '
								+'class="pdf-page">Page: <span id="page_num'+idx+'">1</span>/ <span id="page_count'+idx+'">'
								+'</span></span></a></li>'
								+'<li><a id="go_next'+idx+'" type="button" '
								+'class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left btnnext">Next'
								+'<i class="lni lni-chevron-right"></i>'
								+'</a></li>'
								+'</ul>'
								+'</div>'
								+'<div class="modal-footer-right">'
								+'<ul class="buttons-group">'
								+'<li><a type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal">Close</a></li>'
								+'</ul>'
								+'</div>';


			$("#modal-footer").append(btn);
		
		
		
		

		var pdfDoc = null,
	    pageNum = 1,
	    pageRendering = false,
	    pageNumPending = null,
	    scale = 1,
	    canvas = document.getElementById('pdf_renderer'+idx),
	    ctx = canvas.getContext('2d');

		/**
		 * Get page info from document, resize canvas accordingly, and render page.
		 * @param num Page number.
		 */
		function renderPage(num) {
		  pageRendering = true;
		  // Using promise to fetch the page
		  pdfDoc.getPage(num).then(function(page) {
		    var viewport = page.getViewport({scale: scale});
		    canvas.height = viewport.height;
		    canvas.width = viewport.width;
		
		    // Render PDF page into canvas context
		    var renderContext = {
		      canvasContext: ctx,
		      viewport: viewport
		    };
		    
		    var renderTask = page.render(renderContext);
		
		    // Wait for rendering to finish
		    renderTask.promise.then(function() {
		      pageRendering = false;
		      if (pageNumPending !== null) {
		        // New page rendering is pending
		        renderPage(pageNumPending);
		        pageNumPending = null;
		      }
		    });
		  });
		
// 		  Update page counters
	 	  document.getElementById('page_num'+idx).textContent = num;
		}

			/**
			 * If another page rendering in progress, waits until the rendering is
			 * finised. Otherwise, executes rendering immediately.
			 */
			function queueRenderPage(num) {
			  if (pageRendering) {
			    pageNumPending = num;
			  } else {
			    renderPage(num);
			  }
			}

		/**
		 * Displays previous page.
		 */
		function onPrevPage() {
		  if (pageNum <= 1) {
		    return;
		  }
		  pageNum--;
		  queueRenderPage(pageNum);
		}
		
		document.getElementById('go_previous'+idx).addEventListener('click', onPrevPage);
		
		/**
		 * Displays next page.
		 */
		 
		function onNextPage() {
		  if (pageNum >= pdfDoc.numPages) {
		    return;
		  }
		  pageNum++;
		  queueRenderPage(pageNum);
		}
		document.getElementById('go_next'+idx).addEventListener('click', onNextPage);
		
		document.getElementById('zoom_in'+idx).addEventListener('click', (e) => {
		    if(pdfDoc == null) return;
		    if (scale!= 4) {
		    scale += 0.5;
		   }
		    queueRenderPage(pageNum);
		});
		
		document.getElementById('zoom_out'+idx).addEventListener('click', (e) => {
			 if(pdfDoc == null) return;
			 
			 if (scale!= 0.5) {
				 scale -= 0.5;
			}
			    queueRenderPage(pageNum);
		});
		
//	 	document.getElementById('downloadbtn'+idx).addEventListener('click', (e) => {
//	 		download_file();
//	 	});

	//document.getElementById('btn-close-pdf').addEventListener('click', (e) => {
//		hide_PDF();
	//});
	/**
	* Asynchronously downloads PDF.
	*/
		pdfjsLib.getDocument(path1).promise.then(function(pdfDoc_) {
		
			 if (pdfDoc) {
		         pdfDoc.destroy();
		     }
		     pdfDoc = pdfDoc_;
		//      this.total_pages = this.pdfDoc.numPages;
			
		//   pdfDoc = pdfDoc;
	 	  document.getElementById('page_count'+idx).textContent = pdfDoc.numPages;
		  // Initial/first page rendering
		  renderPage(pageNum);
		});
	}



		
		function View_hide() {
			
// 			   $("#my_pdf_viewer").show();
		}
		
		//----CSP		
		
	document.addEventListener('DOMContentLoaded', function () {	
			
		
		document.getElementById('per_state').onchange = function() 
		{
			getDistrictper(); 
			changeAddress();
		};
		
		
		document.getElementById('per_district').onchange = function() 
		{
			changeAddress();
		};

		
		document.getElementById('pre_state').onchange = function() 
		{
			getDistrictpre();
		};
		
		document.getElementById('curr_state').onchange = function() 
		{
			getDistrictpre();
		};
		
		
			document.getElementById('downloadbtnview').onchange = function() 
			{
						download_file_view(); 
			};
			
			document.getElementById('downloadbtn').onchange = function() 
			{
				download_file(); 
			};
			
			document.getElementById('downloadbtnview1').onclick = function() 
			{
				download_file_view(1); 
			};
			
			document.getElementById('downloadbtn1').onclick = function() 
			{
				download_file(1);; 
			};
 			
	});
		
		
	function  adddownloadOnclick(p){
		 console.log("p--1------>    "+p)
		document.getElementById('downloadbtnnn'+p).onclick = function() {
			 console.log("p---2----->    "+p)
			download_file_view2(p);
		};	
	}						
		
	</script>
