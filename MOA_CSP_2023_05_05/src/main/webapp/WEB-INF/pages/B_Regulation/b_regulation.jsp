<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script src="js/Calender/jquery-ui.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">
<link rel="stylesheet" href="admin/assets/db_css/db_custom_style.css">
<script src="js/miso/commonJS/addmorefunctionality.js"
	type="text/javascript"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section class="dashboard-page b_regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>Form B Regulation</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#0">Start View</a></li>
								<li class="breadcrumb-item"><a href="#0">Regulation
										Form B</a></li>
								<!-- <li class="breadcrumb-item active" aria-current="page">
									Form A</li> -->
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->


		<div class="form-elements-wrapper">
			<div class="row">

				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form action="b_Regulation_Action" method="POST"
						class="form-horizontal" modelAttribute="b_RegulationCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">

							<div class="accordion" id="accordionPanelsStayOpenExample">
								<div class="accordion-item accordion-itemstyle">
									<h2 class="accordion-header" id="panelsStayOpen-headingOne">
										<button
											class="accordion-button accordion-itemstylena accordion-primary-button "
											type="button" data-bs-toggle="collapse"
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



													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>First Name <strong class="mandatory">*
															</strong></label> <input type="text" id="first_name" name="first_name"
																placeholder="First Name"
																oninput="this.value = this.value.toUpperCase()"
																maxlength="50" class="form-control" autocomplete="off">

														</div>
													</div>

													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Father's Name <strong class="mandatory">*
															</strong>
															</label> <input type="text" id="father_name" name="father_name"
																placeholder="Father's Name"
																oninput="this.value = this.value.toUpperCase()"
																maxlength="50" class="form-control" autocomplete="off">

														</div>
													</div>

													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Date Of Birth <strong class="mandatoryccc">*
															</strong></label> <input type="text" name="dob" id="dob" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control-sm form-control effect-9 "
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this); calculate_age('dob');"
																aria-required="true" autocomplete="off"
																value="DD/MM/YYYY">
<div class="info-value"><b>Age :</b><span class="text-heighlight get-value" id="age" name="age"></span></div>	
														</div>
																													
									
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-form-check">
															<label>Gender<strong class="mandatory">*
															</strong>
															</label>
															<div class="form-check radio-style">
															<input type="radio" id="male" name="gender" class="form-check-input" value="0" required />
															<label for="male" class="form-check-label">Male</label>																													
															</div>
															<div class="form-check radio-style">															
															<input type="radio" id="female" name="gender" class="form-check-input" value="1" required />
															<label for="female" class="form-check-label">Female</label> 														
															</div>
															<div class="form-check radio-style">
															<input type="radio" id="other" name="gender"class="form-check-input" value="2" required />
															<label for="other" class="form-check-label">Other</label>															
															</div>															
														</div>
													</div>
													<!-- end input -->



													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Aadhaar Number <strong class="mandatory">*
															</strong></label> <input type="text" id="aadhaar_no" name="aadhaar_no"
																onkeypress="return isAadhar(this,event);"
																oninput="this.value = this.value.toUpperCase()"
																maxlength="14" class="form-control" autocomplete="off"
																placeholder="Aadhaar Number">
														</div>
													</div>

													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Mobile Number<strong class="mandatory">*
															</strong>
															</label> <input type="text" id="mo_no" name="mo_no"
																onkeypress="return isNumberOnly(event)"
																oninput="this.value = this.value.toUpperCase()"
																maxlength="10" class="form-control" autocomplete="off"
																placeholder="Mobile Number">

														</div>
													</div>

													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Email Id <strong class="mandatory">*
															</strong></label> <input type="text" id="email_id" name="email_id"
																class="form-control" autocomplete="off">
														</div>

													</div>
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Upload Photograph <strong
																class="mandatory">* </strong></label> <input type="file"
																accept="image/*" id="photo_path" name="photo_path"
																onchange="photoValidate();" class="form-control">
															<input type="hidden" id="upload_img_hid"
																name="upload_img_hid" class="form-control">

														</div>
													</div>

													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="select-style-1">
															<label>Nationality <strong class="mandatory">*
															</strong></label>
															<div class="select-position">
																<select name="nationality" id="nationality"
																	style="text-transform: uppercase" class="form-control">
																	<option value="0" selected="selected">--
																		Select Country --</option>
																	<c:forEach var="item" items="${getMedNationality}"
																		varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>
																</select>

															</div>
														</div>
													</div>

													<div class="col-lg-12 col-md-12 col-sm-12">
														<h6 class="mb-25">Alternat Fields</h6>
													</div>
													<!-- </div> -->

													<div class="col-lg-3 col-md-6 col-sm-12">
														<div class="input-style-2">


															<label>Alternative Mobile Number 1</label> <input
																type="text" id="alt_mo_no1" name="alt_mo_no1"
																onkeypress="return isNumberOnly(event)"
																oninput="this.value = this.value.toUpperCase()"
																maxlength="10" class="form-control mb2"
																autocomplete="off"
																placeholder="Alternate Mobile Number 1">

														</div>
													</div>

													<div class="col-lg-3 col-md-6 col-sm-12">
														<div class="input-style-2">


															<label>Alternative Mobile Number 2 </label> <input
																type="text" id="alt_mo_no2" name="alt_mo_no2"
																onkeypress="return isNumberOnly(event)"
																oninput="this.value = this.value.toUpperCase()"
																maxlength="10" class="form-control mb2"
																autocomplete="off"
																placeholder="Alternate Mobile Number 2">
														</div>
													</div>

													<div class="col-lg-3 col-md-6 col-sm-12">
														<div class="input-style-2">


															<label>Alternative EMAIL ID 1 </label> <input type="text"
																id="alt_email_id1" name="alt_email_id1"
																class="form-control mb2" autocomplete="off"
																placeholder="Alternate Email Id 1">

														</div>
													</div>

													<div class="col-lg-3 col-md-6 col-sm-12">
														<div class="input-style-2">


															<label>Alternative EMAIL ID 2 </label> <input type="text"
																id="alt_email_id2" name="alt_email_id2"
																class="form-control mb2" autocomplete="off"
																placeholder="Alternate Email Id 2">

														</div>
													</div>
													
													<div class="col-lg-4 col-md-6 col-sm-12"></div>
													<div class="col-lg-4 col-md-6 col-sm-12"></div>



												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="accordion-item accordion-itemstyle">
									<h2 class="accordion-header" id="panelsStayOpen-headingTwo">
										<button
											class="accordion-button accordion-itemstylena accordion-primary-button collapsed"
											type="button" data-bs-toggle="collapse"
											data-bs-target="#panelsStayOpen-collapseTwo"
											aria-expanded="false"
											aria-controls="panelsStayOpen-collapseTwo">ADDRESS
											DETAILS</button>
									</h2>
								<div id="panelsStayOpen-collapseTwo"
										class="accordion-collapse collapse"
										aria-labelledby="panelsStayOpen-headingTwo">
										<div class="accordion-body">
											<div class="card-style mb-30">

												<div class="row">
													<div class="col-lg-12 col-sm-12">
														<h6 class="mb-25">Permanent Address</h6>
													</div>

													<div class="col-lg-12 col-sm-12">
														<div class="select-style-1">
															<label> Address <strong class="mandatory">*
															</strong></label>
														</div>
													</div>
													<div class="col-lg-4  col-sm-12">

														<div class="input-style-2">

															<input type="text" id="per_address" name="per_address"
																maxlength="100" class="form-control autocomplete"
																autocomplete="off" placeholder="Address Line 1">
														</div>
													</div>
													<div class="col-lg-4  col-sm-12">
														<div class="input-style-2">

															<input type="text" id="per_address2" name="per_address2"
																maxlength="100" class="form-control autocomplete"
																autocomplete="off" placeholder="Address Line 2">
														</div>
													</div>
													<div class="col-lg-4  col-sm-12">
														<div class="input-style-2">

															<input type="text" id="per_address3" name="per_address3"
																maxlength="100" class="form-control autocomplete"
																autocomplete="off" placeholder="Address Line 3">

														</div>
													</div>


													<div class="col-lg-4 col-sm-12">


														<div class="select-style-1">
															<label> State <strong class="mandatory">*
															</strong></label>
															<div class="select-position">
																<select name="per_state" id="per_state"
																	class="form-control autocomplete"
																	onchange="getDistrictper();">
																	<option value="0">--Select--</option>
																	<c:forEach var="item" items="${MedStateName}"
																		varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>

																</select>

															</div>
														</div>
													</div>
													<div class="col-lg-4 col-sm-12">
														<div class="select-style-1">
															<label>District<strong class="mandatory">*
															</strong></label>
															<div class="select-position">
																<select name="per_district" id="per_district"
																	class="form-control autocomplete">

																</select>

															</div>
														</div>
													</div>

													<div class="col-lg-4 col-sm-12">
														<div class="input-style-2">
															<label> Pin Code<strong class="mandatory">*
															</strong></label> <input type="text" id="per_pincode" name="per_pincode"
																onkeypress="return isNumberOnly(event)"
																oninput="this.value = this.value.toUpperCase()"
																maxlength="6" class="form-control" autocomplete="off"
																placeholder="Pin Code">

														</div>



													</div>
												</div>
												<div class="col-lg-12 col-sm-12">
													<div class="row">
														<div class="col-lg-12 col-md-12 col-sm-12">
															<div class="form-check checkbox-style mb-20">
																<input class="form-check-input" type="checkbox"
																	id="check_address" name="check_address"
																	onclick="copy_address()"> <label
																	class="form-check-label"> <strong
																	class="text-heighlight"> Same as Permanent Address</strong>
																</label>
															</div>
														</div>
														<div class="col-lg-12 col-md-12 col-sm-12">
															<h6 class="mb-25">Present Address</h6>
														</div>


													</div>


													<div class="row">
														<div class="col-lg-12 col-sm-12">
															<div class="select-style-1">
																<label> Address <strong class="mandatory">*
																</strong></label>
															</div>
														</div>
														<div class="col-lg-4 col-sm-12">
															<div class="input-style-2">
																<input type="text" id="pre_address" name="pre_address"
																	maxlength="100" class="form-control autocomplete"
																	autocomplete="off" placeholder="Address Line 1">
															</div>
														</div>
														<div class="col-lg-4 col-sm-12">
															<div class="input-style-2">
																<input type="text" id="pre_address2" name="pre_address2"
																	maxlength="100" class="form-control autocomplete"
																	autocomplete="off" placeholder="Address Line 2">
															</div>
														</div>
														<div class="col-lg-4 col-sm-12">
															<div class="input-style-2">
																<input type="text" id="pre_address3" name="pre_address3"
																	maxlength="100" class="form-control autocomplete"
																	autocomplete="off" placeholder="Address Line 3">
															</div>
														</div>
													
														<div class="col-lg-4 col-sm-12">
															<div class="select-style-1">
																<label> State <strong class="mandatory">*
																</strong></label>
																<div class="select-position">
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
															</div>
														</div>
														
														<div class="col-lg-4 col-sm-12">
															<div class="select-style-1">
																<label>District<strong class="mandatory">*
																</strong></label>
																<div class="select-position">
																	<select name="pre_district" id="pre_district"
																		class="form-control autocomplete">
																	
																	</select>

																</div>
															</div>
														</div>
														<div class="col-lg-4 col-sm-12">
															<div class="input-style-2">
																<label> Pin Code<strong class="mandatory">*
																</strong></label> <input type="text" id="pre_pincode" name="pre_pincode"
																	onkeypress="return isNumberOnly(event)"
																	oninput="this.value = this.value.toUpperCase()"
																	maxlength="6" class="form-control" autocomplete="off"
																	placeholder="Pin Code">

															</div>
														</div>

													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!--                         HET CHANGES                        -->
								<div class="accordion-item accordion-itemstyle">
									<h2 class="accordion-header" id="panelsStayOpen-headingThree">
										<button
											class="accordion-button accordion-itemstylena accordion-primary-button collapsed"
											type="button" data-bs-toggle="collapse"
											data-bs-target="#panelsStayOpen-collapseThree"
											aria-expanded="false"
											aria-controls="panelsStayOpen-collapseThree">NAME OF
											MEDICAL DEGREE GRADUATE/POSTGRADUATE/DIPLOMA OBTAINED</button>
									</h2>
									<div id="panelsStayOpen-collapseThree"
										class="accordion-collapse collapse"
										aria-labelledby="panelsStayOpen-headingThree">
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
																			<th>Type Of Degree</th>
																			<th>Degree Name</th>
																			<th>Month and Year</th>
																			<th>Name of Institute/Board</th>

																			<th>Attachment</th>
																			<th>Action</th>
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

																					<div class="select-position">
																						<select name="typeOfDegree1" id="typeOfDegree1"
																							class="form-control autocomplete"
																							onchange="getDegreeName(this,1);">
																							<option value="0">--Select--</option>

																							<c:forEach var="item" items="${TypeOfDegree}"
																								varStatus="num">
																								<option value="${item.id}"
																									name="${item.type_of_degree}">${item.type_of_degree}</option>
																							</c:forEach>

																						</select>

																					</div>
																				</div>
																			</td>
																			<td class="min-width">
																		

																				<div class="select-style-1">

																					<div class="select-position">
																						<select name="DegreeName1" id="DegreeName1"
																							class="form-control autocomplete">
																							<option value="0">--Select--</option>

																						</select>

																					</div>
																				</div>
																			</td>
																			<td class="min-width">
																			
																				<div class="input-style-2">
																					<input type="month" id="monthYearOfDegree1"
																						name="monthYearOfDegree1"
																						class="form-control-sm form-control effect-9 hasDatepicker" />

																				</div>
																			</td>
																			<td class="min-width">

																				<div class="input-style-2">
																					<input type="text" id="NameOfUniversity1"
																						name="NameOfUniversity1" value=""
																						class="form-control autocomplete"
																						autocomplete="off"
																						onkeypress="AutoCompleteNameOfUniversity(this);"
																						placeholder="Name of Institute/Board">

																				</div> 
																			</td>
																			<td class="min-width">

																				<div class="input-style-2">
																					<input type="file" id="attachment1"
																						name="attachment1" accept=".PDF"
																						onchange="attachmentDetails(this,1);"
																						class="form-control">

																				</div> 
																			</td>
																			<td class="min-width addminusbut">
																				<div class="action">

																					<ul class="buttons-group mainbtn">


																						<li><a
																							class="main-btn info-btn-outline btn-hover btn-sm addminusbut"
																							value="ADD" title="ADD" id="id_add_attNameMed1"
																							onclick="formOpenNameMed(1);"><i
																								class="lni lni-plus"></i></a></li>

																					</ul>


																				</div>
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

						

								<div class="accordion-item accordion-itemstyle">
									<h2 class="accordion-header" id="panelsStayOpen-headingFive">
										<button
											class="accordion-button accordion-itemstylena accordion-primary-button collapsed"
											type="button" data-bs-toggle="collapse"
											data-bs-target="#panelsStayOpen-collapseFive"
											aria-expanded="false"
											aria-controls="panelsStayOpen-collapseFive">
											INSTITUTE/OFFICE/CLINIC/HOSPITAL</button>
									</h2>
									<div id="panelsStayOpen-collapseFive"
										class="accordion-collapse collapse"
										aria-labelledby="panelsStayOpen-headingFive">
										<div class="accordion-body">
											<div class="card-style mb-30">

												<div class="tables-wrapper">
													<div class="row">
														<div class="col-lg-12">


															<div class="table-wrapper table-responsive custom-table">
																<table class="table" id="addHospital">
																	<thead>
																		<tr>
																			<th>Ser No</th>
																			<th>Place of Working</th>
																			<th>Name of Place</th>
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
																			<th>Action</th>

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

																					<div class="select-position">
																						<select name="place_of_working1"
																							id="place_of_working1"
																							class="form-control autocomplete">
																							<option value="0">--Select--</option>
																							<c:forEach var="item" items="${PlaceOfWorking}"
																								varStatus="num">
																								<option value="${item.id}"
																									name="${item.place_of_working_practitioner}">${item.place_of_working_practitioner}</option>
																							</c:forEach>
																						</select>

																					</div>
																				</div>
																			</td>
																			<td class="min-width">
																				
																				<div class="input-style-2">
																					<input type="text"
																						class="form-control autocomplete"
																						id="place_of_working_name1"
																						name="place_of_working_name1"
																						class="form-control autocomplete"
																						placeholder="Enter Name Of Place" />
																				</div>
																				</div>
																			</td>
																			<td class="min-width">
																			

																				<div class="input-style-2">
																					<input type="text"
																						class="form-control autocomplete" id="landline1"
																						name="landline1" maxlength="10"
																						placeholder="Landline No" />
																				</div>
																			</td>
																			<td class="min-width">
																				<div class="input-style-2">
																					<input type="text"
																						class="form-control autocomplete" id="mobileHosp1"
																						name="mobileHosp1" maxlength="10"
																						placeholder="Mobile No" />
																				</div>
																			</td>
																			<td class="min-width">
																				<div class="input-style-2">
																					<input type="text"
																						class="form-control autocomplete" id="email1"
																						name="email1" placeholder="Enter Email Id" />
																				</div>
																			</td>
																			
																			<td class="min-width">
																				<div class="input-style-2">
																					<input type="text" id="hos_address1_1"
																						name="hos_address1_1" maxlength="100"
																						class="form-control autocomplete mb2"
																						autocomplete="off" placeholder="Address Line 1">
																					<input type="text" id="hos_address2_1"
																						name="hos_address2_1" maxlength="100"
																						class="form-control autocomplete mb2"
																						autocomplete="off" placeholder="Address Line 2">
																					<input type="text" id="hos_address3_1"
																						name="hos_address3_1" maxlength="100"
																						class="form-control autocomplete mb2s"
																						autocomplete="off" placeholder="Address Line 3">
																				</div>
																			</td>



																			<td class="min-width">


																				<div class="select-style-1">

																					<div class="select-position">
																						<select name="hos_state1" id="hos_state1"
																							class="form-control autocomplete"
																							onchange="getDistricthos(1);">
																							<option value="0">--Select--</option>
																							<c:forEach var="item" items="${MedStateName}"
																								varStatus="num">
																								<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																							</c:forEach>

																						</select>

																					</div>
																				</div>
																			</td>

																			<td class="min-width">
																				<div class="select-style-1">

																					<div class="select-position">
																						<select name="hos_district1" id="hos_district1"
																							class="form-control autocomplete"><option
																								value="0">--Select--</option>
																						</select>
																			</td>





																			<td class="min-width">

																				<div class="select-style-1">

																					<div class="select-position">
																						<select name="authority_type1"
																							id="authority_type1"
																							class="form-control autocomplete">
																							<option value="0">--Select--</option>
																							<c:forEach var="item" items="${NameOfResOwner}"
																								varStatus="num">
																								<option value="${item.id}"
																									name="${item.name_of_res_owner}">${item.name_of_res_owner}</option>
																							</c:forEach>
																						</select>

																					</div>
																				</div> 
																			</td>
																			<td class="min-width">
																				<div class="input-style-2">
																					<input type="text" id="name_of_res_p1"
																						class="form-control autocomplete"
																						name="name_of_res_p1"
																						placeholder="Enter Name of Responsible Owner" />
																				</div>

																			</td>

																			<td class="min-width addminusbut">


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

							<ul class="buttons-group mainbtn">


								<li><input type="submit"
									class="main-btn success-btn  btn-hover btn-save" id="draft"
									value="Save as Draft" ></li>
								<li><input type="submit"
									class="main-btn success-btn  btn-hover btn-save" id="update"
									value="Update" style="display: none" ></li>
								<li>
								<li><input type="submit" id="final_submit"
									style="display: none"
									class="main-btn secondary-btn btn-hover btn-save"
									value="Final Submit" onclick="return Validate();"></li>
							</ul>
						</div>
						<input type="hidden" id="NRHstatus" name="NRHstatus">

						<input type="hidden" id="Regulationstatus" name="Regulationstatus">

						<input type="hidden" id="p_id" name="p_id" value="${p_id}">

						<input type="hidden" id="count_hidden_att_Hospital"
							name="count_hidden_att_Hospital"
							class="form-control autocomplete" value="1">
							<input type="hidden" id="SaveDraft"
							name="SaveDraft"
							class="form-control autocomplete" value="0">

						<!-- end card -->
					</form:form>
				</div>
				<!-- end col -->

			</div>
			<!-- end row -->
		</div>


	</div>
	<!-- end container -->
</section>
<!-- b_regulation components end -->
</body>

<SCRIPT>

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
	
	
// 	alert('${p_id}'+"---------");
// 	$("#p_id").val("${p_id}");

// 	   $("#p_id").val($("#p_id").val());
	
	if("${setRegAuth[0].state_name}" != ""){
	$("#reg_auth").val("${setRegAuth[0].state_name}");
	$("#reg_auth").attr("readonly","readonly");
	}
	
	if("${setRegAuth[0].email_id}" != ""){
	$("#email_id").val("${setRegAuth[0].email_id}");
	$("#email_id").attr("readonly","readonly");

	}
	
	
	if("${setRegAuth[0].aadhaar_no}" != ""){
		$("#aadhaar_no").val("${setRegAuth[0].aadhaar_no}");
		$("#aadhaar_no").attr("readonly","readonly");

		}


	$("#nationality").val(6);
		
		$.ajaxSetup({
			async : false
		});
		

		$.ajaxSetup({
			async : false
		});
		datepicketDate('date_of_reg');
		datepicketDate('valid_up_to');
		datepicketDate('dob');
		datepicketDate('date_pract_to1');
		datepicketDate('date_pract_from1');

		if("${setDataCMD.size()}" != 0){
		setAll();	
		$("#final_submit").show();
		$("#draft").hide();
		$("#update").show();
		}
		if("${hid}" == "1"){
			$("#draft").hide();
			$("#final_submit").hide();
			$("#update").hide();

		}
		
		if("${CheckNRH.size()}" != 0){
			$("#NRHstatus").val("${CheckNRH[0]['registration']}");
			}else{
				$("#NRHstatus").val("0");
			}
		

	});
	

$('#draft').click(function(){
	$("#SaveDraft").val();
});
$('#update').click(function(){
	$("#Regulationstatus").val(0);
});
$('#final_submit').click(function(){
	$("#Regulationstatus").val(1);
});



function validateUptoR(){
	var date_reg = $("#date_of_reg").val();
	
	if(date_reg == "" || date_reg == "DD/MM/YYYY"){
		alert("Please Select Date Of First Registration First");
	}else{
		$("#valid_up_to").datepicker("option", "minDate", date_reg);
		
		$("#valid_up_to").val(date_reg);
		$('#valid_up_to').datepicker('option', 'maxDate', null);


	}
	
	
	
	
}
	</SCRIPT>
<script>


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
	
	
	//copy address
	function copy_address(){
    if($("#check_address").prop('checked') == true){        
    	 
            $("#pre_address").val($("#per_address").val());
            $("#pre_address2").val($("#per_address2").val());
            $("#pre_address3").val($("#per_address3").val());
            $("#pre_state").val($("#per_state").val());
//             $("#pre_district").val($("#per_district").val());
			getDistrictpre();
            $("#pre_district").val($("#per_district").val());
            $("#pre_pincode").val($("#per_pincode").val());
           // fn_pers_addr_Country();
           //  fn_pers_addr_state();
        
    }
    else{
    
            $("#pre_address").val("");
            $("#pre_address2").val("");
            $("#pre_address3").val("");
    	  $("#pre_state").val("");
          $("#pre_district").val("");
          $("#pre_district").val("");
          $("#pre_pincode").val("");
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
	
	
	
	//HET CHANGES
	function formOpenNameMed(R){
		
		
		 $("#addNameOfMed").show();

		
			 $("#id_add_attNameMed"+R).hide();
			 $("#att_id_removeNameMed"+R).hide();
			 
			 att=0;
			 att= parseInt(R)+1;
			
			 if(att < 51){
				 $("input#count_hidden_att_name_med").val(att);//current serial No
				 $("table#addNameOfMed").append('<tr align="center" id="tr_id_attNameMed'+att+'"><td>'+att+'</td>'
				 	+'<td class="min-width"><div class="select-style-1"><div class="select-position"><select name="typeOfDegree'+att+'" id="typeOfDegree'+att+'" class="form-control autocomplete" onchange="getDegreeName(this,'+att+')"><option value="0">--Select--</option> <c:forEach var="item" items="${TypeOfDegree}" varStatus="num">	<option value="${item.id}" name="${item.type_of_degree}">${item.type_of_degree}</option> </c:forEach></select></select></div></div></td>'
				 	
				 	/*  <select name="typeOfDegree'+att+'" id="typeOfDegree'+att+'" class="form-control autocomplete" onchange="getDegreeName(this,'+att+')"> <option value="0">--Select--</option> <c:forEach var="item" items="${TypeOfDegree}" varStatus="num">	<option value="${item.id}" name="${item.type_of_degree}">${item.type_of_degree}</option> </c:forEach></select></td>' */
				 	+'<td class="min-width"><div class="select-style-1"><div class="select-position"><select name="DegreeName'+att+'" id="DegreeName'+att+'" class="form-control autocomplete"><option value="0">--Select--</option> </select></div></div></td>' 
				 	/* <select name="DegreeName'+att+'" id="DegreeName'+att+'" class="form-control autocomplete"> <option value="0">--Select--</option> </select> </td>' */
				 	+'<td class="min-width"><div class="input-style-2"><input type="month" id="monthYearOfDegree'+att+'" name="monthYearOfDegree'+att+'" class="form-control-sm form-control effect-9 hasDatepicker"></div</td>'
				 	/* <input type="month" id="monthYearOfDegree'+att+'" name="monthYearOfDegree'+att+'"></td>' */
				 	+'<td class="min-width"><div class="input-style-2"><input type="text" id="NameOfUniversity'+att+'" name="NameOfUniversity'+att+'" value="" class="form-control autocomplete" autocomplete="off" onkeypress="AutoCompleteNameOfUniversity(this);" placeholder="Name of Institute/Board"></div></td>'
				 	/* <input type="text" id="NameOfUniversity'+att+'" name="NameOfUniversity'+att+'" value="" class="form-control autocomplete" autocomplete="off" onkeypress="AutoCompleteNameOfUniversity(this);"></td>' */
				 	+'<td class="min-width"><div class="input-style-2"><input type="file" id="attachment'+att+'" name="attachment'+att+'" accept="image/*" onchange="attachmentDetails(this,'+att+');" class="form-control"></div></td>' 
				 	/* <input type="file" id="attachment'+att+'" name="attachment'+att+'" accept="image/*" onchange="attachmentDetails(this,'+att+');"></td>'  */
				 	+'<td class="min-width"><div class="action"><ul class="buttons-group mainbtn"><li><a class="main-btn info-btn-outline btn-hover btn-sm addminusbut" value = "ADD" title = "ADD" id = "id_add_attNameMed'+att+'" onclick="formOpenNameMed('+att+');"><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn-outline btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeNameMed'+att+'" onclick="formopen_re_NameMed('+att+');"><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
				 	/* <a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_attNameMed'+att+'" onclick="formOpenNameMed('+att+');" ><i class="fa fa-plus"></i></a> <a style="margin-right: 0px;"class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeNameMed'+att+'" onclick="formopen_re_NameMed('+att+');"><i class="fa fa-trash"></a></td>'  */
				 	+'</tr>');
			 
				}else{
						alert("Please Enter max 50 Quantity");
						 if ( att == 51){
							 att = att-1; 
							 $("#rp_id_remove"+att).show();
						 }	   
				}
		
		}
		
		
	function formopen_re_NameMed(R){
		
		 $("tr#tr_id_attNameMed"+R).remove();
		 att = att-1;
		 $("input#count_hidden_att_name_med").val(att);
		 $("#id_add_attNameMed"+att).show();
		 $("#att_id_removeNameMed"+att).show();	
	}
	
	
	
// 	function AutoCompleteNameOfUniversity(obj){
// 		alert(obj.id);
// 	}

	
	function formOpenHospital(R){
		
		
		 $("#addHospital").show();

		 
			 $("#id_add_attHospital"+R).hide();
			 $("#att_id_removeHospital"+R).hide();
			 
			 att=0;
			 att= parseInt(R)+1;
			
			 if(att < 51){
				// debugger;
				 $("input#count_hidden_att_Hospital").val(att);//current serial No
				 $("table#addHospital").append('<tr align="center" id="tr_id_attHospital'+att+'"><td>'+att+'</td>'
				 	+'<td  class="min-width"><div class="select-style-1"><div class="select-position"><select name="place_of_working'+att+'" id="place_of_working'+att+'" class="form-control autocomplete"><option value="0">--Select--</option>  <c:forEach var="item" items="${PlaceOfWorking}" varStatus="num"> <option value="${item.id}"  name="${item.place_of_working_practitioner}">${item.place_of_working_practitioner}</option> </c:forEach> </select></div></div></td>'
				 	/* <select name="place_of_working'+att+'" id="place_of_working'+att+'" class="form-control autocomplete" >  <option value="0">--Select--</option>  <c:forEach var="item" items="${PlaceOfWorking}" varStatus="num"> <option value="${item.id}"  name="${item.place_of_working_practitioner}">${item.place_of_working_practitioner}</option> </c:forEach> </select> </td>' */
				 	+'<td class="min-width"><div class="input-style-2"><input type="text" class="form-control autocomplete" placeholder="Enter Name Of Place" class="" id="place_of_working_name'+att+'"	name="place_of_working_name'+att+'" /></div></td>'
				 	/* <input type="text" class="form-control autocomplete" placeholder="Enter Name Of Place" style="width: 90%" class="" id="place_of_working_name'+att+'"	name="place_of_working_name'+att+'" /></td>' */
				 	+'<td class="min-width"><div class="input-style-2"><input type="text" class="form-control autocomplete"  placeholder="Landline No" id="landline'+att+'" name="landline'+att+'" /></div></td>' /* <input type="text" class="form-control autocomplete"  placeholder="Landline No" style="width: 90%" id="landline'+att+'" name="landline'+att+'" /> </td>' */
				 	+'<td class="min-width"><div class="input-style-2"><input type="text" class="form-control autocomplete" id="mobileHosp'+att+'" name="mobileHosp'+att+'" placeholder="Mobile No" /></div></td>'
				 	/* <input type="text" class="form-control autocomplete" id="mobileHosp'+att+'" name="mobileHosp'+att+'" style="width: 90%" 	placeholder="Mobile No" /> </td>' */					
				 	+'<td class="min-width"><div class="input-style-2"><input type="text" class="form-control autocomplete" placeholder="Email Id" id="email'+att+'" name="email'+att+'"></div></td>'
				 	/* <input type="text" class="form-control autocomplete" placeholder="Enter Email Id" style="width: 90%" id="email'+att+'" name="email'+att+'"> </td>'  */
				 	+'<td class="min-width"><div class="input-style-2"><input type="text" id="hos_address1_'+att+'" name="hos_address1_'+att+'" maxlength="100" class="form-control autocomplete mb2" autocomplete="off" placeholder="Address Line 1"><input type="text" id="hos_address2_'+att+'"	name="hos_address2_'+att+'" maxlength="100" class="form-control autocomplete mb2" autocomplete="off"	placeholder="Address Line 2"><input type="text" id="hos_address3_'+att+'"	 name="hos_address3_'+att+'" maxlength="100" class="form-control autocomplete mb2" autocomplete="off"	placeholder="Address Line 3" ></div></td>'
				 	/* <input type="text" id="hos_address1_'+att+'" name="hos_address1_'+att+'" maxlength="100" class="form-control autocomplete" autocomplete="off" placeholder="Address Line 1" style="width: 90%">	<input type="text" id="hos_address2_'+att+'"	name="hos_address2_'+att+'" maxlength="100" class="form-control autocomplete" autocomplete="off"	placeholder="Address Line 2" style="width: 90%"> <input type="text" id="hos_address3_'+att+'"	 name="hos_address3_'+att+'" maxlength="100" class="form-control autocomplete" autocomplete="off"	placeholder="Address Line 3" style="width: 90%"></td>' */
				 	+'<td class="min-width"><div class="select-style-1"><div class="select-position"><select name="hos_state'+att+'" id="hos_state'+att+'" class="form-control autocomplete" onchange="getDistricthos('+att+');"><option value="0">--Select--</option>	<c:forEach var="item" items="${MedStateName}" varStatus="num"> <option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach> </select></div></div></td>'
				 	/* <select name="hos_state'+att+'" id="hos_state'+att+'" class="form-control autocomplete" onchange="getDistricthos('+att+');">	<option value="0">--Select--</option>	<c:forEach var="item" items="${MedStateName}" varStatus="num"> <option value="${item[0]}" name="${item[1]}">${item[1]}</option> 	</c:forEach> </select></td>' */
				 	+'<td class="min-width"><div class="select-style-1"><div class="select-position"><select name="hos_district'+att+'"  id="hos_district'+att+'"	class="form-control autocomplete"><option value="0">--Select--</option></select></div></div></td>'
				 	/* <select name="hos_district'+att+'"  id="hos_district'+att+'"	class="form-control autocomplete"><option value="0">--Select--</option> </select></td>' */
				 	+'<td class="min-width"><div class="select-style-1"><div class="select-position"><select name="authority_type'+att+'" id="authority_type'+att+'" class="form-control autocomplete"><option value="0">--Select--</option> <c:forEach var="item" items="${NameOfResOwner}" varStatus="num"> <option value="${item.id}" 	name="${item.name_of_res_owner}">${item.name_of_res_owner}</option>	</c:forEach></select></div></div> </td>'
				 	/* <select name="authority_type'+att+'" id="authority_type'+att+'" class="form-control autocomplete" > <option value="0">--Select--</option> <c:forEach var="item" items="${NameOfResOwner}" varStatus="num"> <option value="${item.id}" 	name="${item.name_of_res_owner}">${item.name_of_res_owner}</option>	</c:forEach> </select>	</td>' */
				 	+'<td class="min-width"><div class="input-style-2"><input  class="form-control autocomplete"  placeholder="Responsible Owner" type="text" id="name_of_res_p'+att+'"  name="name_of_res_p'+att+'" /></div></td>' 
				 	/* <input class="form-control autocomplete"  placeholder="Enter Name of Responsible Owner" style="width: 90%" type="text" id="name_of_res_p'+att+'"  name="name_of_res_p'+att+'" /> </td>' */
// 				 	+'<td class="min-width"><div class="input-style-2"><input type="text" name="date_pract_from'+att+'" id="date_pract_from'+att+'" maxlength="10" onclick="clickclear(this, "DD/MM/YYYY")"	class="form-control-sm form-control effect-9" 	onfocus="this.style.color="#000000" onblur="clickrecall(this,"DD/MM/YYYY");validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, "DD/MM/YYYY")"	onchange="clickrecall(this,"DD/MM/YYYY");validateDate_FutureDate(this.value,this);"	aria-required="true" autocomplete="off" 	value="DD/MM/YYYY"> </div></td>' /* <input type="text" name="date_pract_from'+att+'" id="date_pract_from'+att+'" maxlength="10" onclick="clickclear(this, "DD/MM/YYYY")"	class="form-control-sm form-control effect-9 " 	onfocus="this.style.color="#000000" onblur="clickrecall(this,"DD/MM/YYYY");validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, "DD/MM/YYYY")"	onchange="clickrecall(this,"DD/MM/YYYY");validateDate_FutureDate(this.value,this);"	aria-required="true" autocomplete="off" 	value="DD/MM/YYYY">	</td>' */
// 				 	+'<td class="min-width"><div class="input-style-2"><input type="text" name="date_pract_to'+att+'" id="date_pract_to'+att+'" maxlength="10" onclick="clickclear(this, "DD/MM/YYYY")"	class="form-control-sm form-control effect-9 " 	onfocus="this.style.color="#000000" onblur="clickrecall(this,"DD/MM/YYYY");validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, "DD/MM/YYYY")"	onchange="clickrecall(this,"DD/MM/YYYY");validateDate_FutureDate(this.value,this);"	aria-required="true" autocomplete="off" 	value="DD/MM/YYYY"></div></td>' /* <input type="text" name="date_pract_to'+att+'" id="date_pract_to'+att+'" maxlength="10" onclick="clickclear(this, "DD/MM/YYYY")"	class="form-control-sm form-control effect-9 " 	onfocus="this.style.color="#000000" onblur="clickrecall(this,"DD/MM/YYYY");validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, "DD/MM/YYYY")"	onchange="clickrecall(this,"DD/MM/YYYY");validateDate_FutureDate(this.value,this);"	aria-required="true" autocomplete="off" 	value="DD/MM/YYYY">	</td>' */

				 	+'<td class="min-width"><div class="action"><ul class="buttons-group mainbtn"><li><a class="main-btn info-btn-outline btn-hover btn-sm addminusbut" value = "ADD" title = "ADD" id = "id_add_attHospital'+att+'" onclick="formOpenHospital('+att+');" ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn-outline btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeHospital'+att+'" onclick="formopen_re_Hospital('+att+');"><i class="lni lni-trash-can"></i></a></li></ul></div></td>' /* <a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_attHospital'+att+'" onclick="formOpenHospital('+att+');" ><i class="fa fa-plus"></i></a> <a style="margin-right: 0px;"class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeHospital'+att+'" onclick="formopen_re_Hospital('+att+');"><i class="fa fa-trash"></a></td>'  */
				 	+'</tr>');
// 				 	datepicketDate('date_pract_from'+att);
// 					datepicketDate('date_pract_to'+att);
			 }else{
						alert("Please Enter max 50 Quantity");
						 if ( att == 51){
							 att = att-1; 
							 $("#rp_id_remove"+att).show();
						 }	   
				}
		
		}
	
	





	
	
	
	function formopen_re_Hospital(R){
		
		 $("tr#tr_id_attHospital"+R).remove();
		 att = att-1;
		 $("input#count_hidden_att_Hospital").val(att);
		 $("#id_add_attHospital"+att).show();
		 $("#att_id_removeHospital"+att).show();	
	}


	
	//	------------22/06/2022 urmik
	function getDegreeName(obj,R){
		var typeofdegree = $("#typeOfDegree"+R).val();
// 		alert(typeofdegree)
		$.post('getDegreedetailsb?' + key + "=" + value,{typeofdegree:typeofdegree},function(k) {
			var options = '';
			for (var i = 0; i < k.length; i++) {
				options += '<option value="'+k[i].id+'" name="' + k[i].degree_name+ '" >'+ k[i].degree_name + '</option>';
			}
			$("#DegreeName"+R).html(options);
		});
	}
	
	
	
	function getDegreeNameAddMore(obj,R){
		var DegreeName = $("#"+obj).val();
		$.post('getDegreeName?' + key + "=" + value,{DegreeName:DegreeName},function(k) {
			var options = '';
			for (var i = 0; i < k.length; i++) {
				options += '<option value="'+k[i].id+'" name="' + k[i].degree_name+ '" >'+ k[i].degree_name + '</option>';
			}
			$("#DegreeName"+R).html(options);

		});
	
	}
	

	function Validate() {
		
		/* personal details */
		
		
			if ($("#first_name").val().trim() == "") {
				alert("Please Enter First Name");
				$("input#first_name").focus();
				return false;
			}
			if ($("#father_name").val().trim() == "") {
				alert("Please Enter Father Name");
				$("input#father_name").focus();
				return false;
			}
			
			if ($("#dob").val().trim() == "") {
				alert("Please Enter Date Of Birth");
				$("input#dob").focus();
				return false;
			}
			var a = $('input:radio[name=gender]:checked').val();
			if(a == undefined) {
				alert("Please Select Gender");
				return false;
			}
			if ($("#aadhaar_no").val().trim() == "") {
				alert("Please Enter Aadhaar Number");
				$("input#aadhaar_no").focus();
				return false;
			}
			if ($("#mo_no").val().trim() == "") {
				alert("Please Enter Mobile Number");
				$("input#mo_no").focus();
				return false;
			}
			if ($("#email_id").val().trim() == "") {
				alert("Please Enter Email Id");
				$("input#email_id").focus();
				return false;
			}
			if ($("#upload_img_hid").val().trim() == "") {
				alert("Please Select Photo");
				$("input#photo_path").focus();
				return false;
			}
			
			if ($("#nationality").val().trim() == "0") {
				alert("Please Select Nationality");
				$("input#nationality").focus();
				return false;
			}
			
			
			/* address details */
			
			if ($("#per_address").val().trim() == "") {
				alert("Please Enter Permanent Address Line 1");
				$("input#per_address").focus();
				return false;
			}
			if ($("#per_state").val().trim() == "0") {
				alert("Please Select Permanent State");
				$("input#per_state").focus();
				return false;
			}
			
			if ($("#per_district").val().trim() == "0") {
				alert("Please Select Permanent District");
				$("input#per_district").focus();
				return false;
			}
			if ($("#per_pincode").val().trim() == "") {
				alert("Please Enter Permanent Pincode");
				$("input#per_pincode").focus();
				return false;
			}

			if ($("#pre_address").val().trim() == "") {
				alert("Please Enter Present Address Line 1");
				$("input#pre_address").focus();
				return false;
			}
			
			if ($("#pre_state").val().trim() == "0") {
				alert("Please Select Present State");
				$("input#pre_state").focus();
				return false;
			}
			if ($("#pre_district").val().trim() == "0") {
				alert("Please Select Present District");
				$("input#pre_district").focus();
				return false;
			}
			if ($("#pre_pincode").val().trim() == "") {
				alert("Please Enter Present Pincode");
				$("input#pre_pincode").focus();
				return false;
			}

			return true;
		}

	
	//pending
	function calculate_age(obj){    	
	//	debugger;
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
// 	    alert(year);
	    $(".get-value").text(""+year+" Years");
	    
	}
	
	function FunctionState(val){
		var roleid = $("#"+val.id).val();

		$.post('getDistrictOnstate?' + key + "=" + value,{roleid:roleid},function(k) {
		//	debugger;

			var options = '';
			for (var i = 0; i < k.length; i++) {
				options += '<option value="'+k[i].district_id+'" name="' + k[i].district_name+ '" >'+ k[i].district_name + '</option>';
			}
			$("#per_district").html(options);
			$("#pre_district").html(options);


		});		
		

}
	function getDistrictper() {
		var selval = $("#per_state").val();
		$("select#per_district").empty();

		$
				.post(
						"getDistrictOnstate?" + key + "=" + value,
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
	
	
	function getDistrictpre() {
		var selval = $("#pre_state").val();
		$("select#pre_district").empty();
		$
				.post(
						"getDistrictOnstate?" + key + "=" + value,
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
// 		$("select#hos_district"+R).empty();
		$
				.post(
						"getDistrictOnstate?" + key + "=" + value,
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
							$("select#hos_district"+R).html(options);
							
						});
	}
	
	function setAll(){
		if("${setDataCMD[0]['first_name']}" != null && "${setDataCMD[0]['first_name']}" != ""){
			$("#first_name").val("${setDataCMD[0]['first_name']}");
		}
		if("${setDataCMD[0]['father_name']}" != null && "${setDataCMD[0]['father_name']}" != ""){
			$("#father_name").val("${setDataCMD[0]['father_name']}");
		}

		
		if("${setDataCMD[0]['dob']}" != null && "${setDataCMD[0]['dob']}" != ""){
			$("#dob").val("${setDataCMD[0]['dob']}");
		}
		calculate_age("dob");
		if("${setDataCMD[0]['gender']}" != null && "${setDataCMD[0]['gender']}" != ""){
			$("input[name = 'gender'][value='${setDataCMD[0]['gender']}']").click();
		}
		if("${setDataCMD[0]['mo_no']}" != null && "${setDataCMD[0]['mo_no']}" != ""){
			$("#mo_no").val("${setDataCMD[0]['mo_no']}");
		}
		if("${setDataCMD[0]['email_id']}" != null && "${setDataCMD[0]['email_id']}" != ""){
			$("#email_id").val("${setDataCMD[0]['email_id']}");
		}
		if("${setDataCMD[0]['aadhaar_no']}" != null && "${setDataCMD[0]['aadhaar_no']}" != ""){
			$("#aadhaar_no").val("${setDataCMD[0]['aadhaar_no']}");
		}
		
		
		if("${setDataCMD[0]['per_state']}" != null && "${setDataCMD[0]['per_state']}" != ""){
			$("#per_state").val("${setDataCMD[0]['per_state']}");
		}
		getDistrictper();

		if("${setDataCMD[0]['per_district']}" != null && "${setDataCMD[0]['per_district']}" != ""){
			$("#per_district").val("${setDataCMD[0]['per_district']}");
		}
		
		if("${setDataCMD[0]['per_address_details1']}" != null && "${setDataCMD[0]['per_address_details1']}" != ""){
			$("#per_address").val("${setDataCMD[0]['per_address_details1']}");
		}
		if("${setDataCMD[0]['per_address_details2']}" != null && "${setDataCMD[0]['per_address_details2']}" != ""){
			$("#per_address2").val("${setDataCMD[0]['per_address_details2']}");
		}
		if("${setDataCMD[0]['per_address_details3']}" != null && "${setDataCMD[0]['per_address_details3']}" != ""){
			$("#per_address3").val("${setDataCMD[0]['per_address_details3']}");
		}
		
		
		if("${setDataCMD[0]['per_pincode']}" != null && "${setDataCMD[0]['per_pincode']}" != ""){
			$("#per_pincode").val("${setDataCMD[0]['per_pincode']}");
		}

		
		if("${setDataCMD[0]['pre_address_details1']}" == "${setDataCMD[0]['per_address_details1']}" && "${setDataCMD[0]['pre_address_details2']}" == "${setDataCMD[0]['per_address_details2']}"  && "${setDataCMD[0]['pre_address_details3']}" == "${setDataCMD[0]['per_address_details3']}" && "${setDataCMD[0]['pre_state']}" == "${setDataCMD[0]['per_state']}" && "${setDataCMD[0]['per_district']}" == "${setDataCMD[0]['pre_district']}"   && "${setDataCMD[0]['per_pincode']}" == "${setDataCMD[0]['pre_pincode']}")
		{
			$("#check_address").click();
		}
		
		if("${setDataCMD[0]['pre_state']}" != null && "${setDataCMD[0]['pre_state']}" != ""){
			$("#pre_state").val("${setDataCMD[0]['pre_state']}");
		}
		getDistrictpre();

		if("${setDataCMD[0]['pre_district']}" != null && "${setDataCMD[0]['pre_district']}" != ""){
			$("#pre_district").val("${setDataCMD[0]['pre_district']}");
		}
		
		if("${setDataCMD[0]['pre_address_details1']}" != null && "${setDataCMD[0]['pre_address_details1']}" != ""){
			$("#pre_address").val("${setDataCMD[0]['pre_address_details1']}");
		}
		if("${setDataCMD[0]['pre_address_details2']}" != null && "${setDataCMD[0]['pre_address_details2']}" != ""){
			$("#pre_address2").val("${setDataCMD[0]['pre_address_details2']}");
		}
		if("${setDataCMD[0]['pre_address_details3']}" != null && "${setDataCMD[0]['pre_address_details3']}" != ""){
			$("#pre_address3").val("${setDataCMD[0]['pre_address_details3']}");
		}
		
		
		if("${setDataCMD[0]['pre_pincode']}" != null && "${setDataCMD[0]['pre_pincode']}" != ""){
			$("#pre_pincode").val("${setDataCMD[0]['pre_pincode']}");
		}
		
		
		if("${setDataCMD[0]['reg_no']}" != null && "${setDataCMD[0]['reg_no']}" != ""){
			$("#reg_no").val("${setDataCMD[0]['reg_no']}");
		}
		
		if("${setDataCMD[0]['date_of_reg']}" != null && "${setDataCMD[0]['date_of_reg']}" != ""){
			$("#date_of_reg").val("${setDataCMD[0]['date_of_reg']}");
			validateUptoR();
		}
		
		if("${setDataCMD[0]['registration_for_type']}" != null && "${setDataCMD[0]['registration_for_type']}" != ""){
			$("input[name = 'registration_for'][value='${setDataCMD[0]['registration_for_type']}']").click();
			if("${setDataCMD[0]['registration_for_type']}" == "1"){
				$("#valid").show();
				if("${setDataCMD[0]['valid_up_to']}" != null && "${setDataCMD[0]['valid_up_to']}" != ""){
					$("#valid_up_to").val("${setDataCMD[0]['valid_up_to']}");
				}	
			}
			
		}
		
		
		
		if("${setDataCMD[0]['photo_path']}" != null || "${setDataCMD[0]['photo_path']}" != ""){
			$("#upload_img_hid").val("${setDataCMD[0]['photo_path']}");
		}
		
		
		//ADDMORE MEDICAL
		if("${setAddMoreMedicalCMD.size()}" > 0){
			var i = 1;
			<c:forEach var="j" items="${setAddMoreMedicalCMD}" varStatus="num">
		//	debugger;
			
			var name_of_institute = "${j[1]}";
			var attachment_path = "${j[2]}";
			var month_and_year_of_degree = "${j[3]}";
			var type_of_degree = "${j[4]}";
			var degree_name = "${j[5]}";
			
			
	 		if(type_of_degree != null || type_of_degree != ""){
			$("#typeOfDegree"+i).val(type_of_degree);
			getDegreeNameAddMore("typeOfDegree"+i,i);
	}
	 		if(degree_name != null || degree_name != ""){
			$("#DegreeName"+i).val(degree_name);
		}
		
		
		if(name_of_institute != null || name_of_institute != ""){
			$("#NameOfUniversity"+i).val(name_of_institute);
		}
		
		if(month_and_year_of_degree != null || month_and_year_of_degree != ""){
			$("#monthYearOfDegree"+i).val(month_and_year_of_degree);
		}
		
		if("${setAddMoreMedicalCMD.size()}" > i)
		$("#id_add_attNameMed"+i).click();	

			i++;

	 		</c:forEach>

		}

		//ADDMORE HOSPITAL
		if("${setAddMoreHospCMD.size()}" > 0){
			var i = 1;
			<c:forEach var="j" items="${setAddMoreHospCMD}" varStatus="num">
		//	debugger;
			
			var place_of_working = "${j[1]}";
			var landline = "${j[2]}";
			var email = "${j[3]}";
			var authority_type = "${j[4]}";
			var name_of_res_p = "${j[5]}";
			var hos_address1 = "${j[6]}";
			var hos_address2 = "${j[7]}";
			var hos_address3 = "${j[8]}";
			var hos_state = "${j[9]}";
			var hos_district = "${j[10]}";
// 			var date_pract_from = "${j[11]}";
// 			var date_pract_to = "${j[12]}";
			var place_of_working_name = "${j[13]}";
			var mobile_no = "${j[14]}";
			
			if(place_of_working != null || place_of_working != ""){
				$("#place_of_working"+i).val(place_of_working);
			}
			
			
			if(place_of_working_name != null || place_of_working_name != ""){
				$("#place_of_working_name"+i).val(place_of_working_name);
			}
			
			if(landline != null || landline != ""){
				$("#landline"+i).val(landline);
			}
			
			
			if(mobile_no != null || mobile_no != ""){
				$("#mobileHosp"+i).val(mobile_no);
			}
			if(email != null || email != ""){
				$("#email"+i).val(email);
			}
			
			if(authority_type != null || authority_type != ""){
				$("#authority_type"+i).val(authority_type);
			}
			
			if(hos_address1 != null || hos_address1 != ""){
				$("#hos_address1_"+i).val(hos_address1);
			}
			
			if(hos_address2 != null || hos_address2 != ""){
				$("#hos_address2_"+i).val(hos_address2);
			}
			
			
			if(hos_address3 != null || hos_address3 != ""){
				$("#hos_address3_"+i).val(hos_address3);
			}
			
			
			
			if(hos_state != null || hos_state != ""){
				$("#hos_state"+i).val(hos_state);
			}
			getDistricthos(i);
			
			if(hos_district != null || hos_district != ""){
				$("#hos_district"+i).val(hos_district);
			}
			
			if(name_of_res_p != null || name_of_res_p != ""){
				$("#name_of_res_p"+i).val(name_of_res_p);
			}
			
			
		
		if("${setAddMoreHospCMD.size()}" > i)
		$("#id_add_attHospital"+i).click();	

			i++;

	 		</c:forEach>

		}
		
		
		
		
	}
	
	
	function photoValidate(){
		var path = $("#photo_path").val();
		$("#upload_img_hid").val(path); 
	}
	
	
	
	//HET CHANGES COMPLETE

	</script>

</html>