<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="https://fonts.googleapis.com/css?family=Poppins:400,600,700" rel="stylesheet" /> -->
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->
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
<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<!-- <script type="text/javascript" src="js/watermark/common.js"></script> -->
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/Pdf_View/pdf.js" type="text/javascript"></script>
<script src="js/Pdf_View/pdf.worker.js" type="text/javascript"></script>
<link href="js/Pdf_View/pdfview.css" rel="Stylesheet"></link>

<!-- <!-- INTERNAL REMOVE START-->
<!-- <link rel="stylesheet" href="assets/vendor/internal_css.css"> -->
<!-- <!-- INTERNAL REMOVE END-->	 

<!-- INTERNAL REMOVE START (NEED TO ADD IN PARTICULAR JSP)-->
			<link rel="stylesheet" href="assets/vendor/internal_css.css">
<!-- INTERNAL REMOVE END--> 
<section class="dashboard-page regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Regulation Preview</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Regulation Preview</li>
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

		<div class="form-elements-wrapper preview-form">
			<div class="row">

				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form action="RegulationPreview_Action" id="uploadForm"
						method="POST" class="form-horizontal"
						modelAttribute="RegulationPreviewCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">

							<div class="field-box">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
										<div class="auto-fill-form">

											<input type="hidden" id="userId" name="userId" value="0">
											<input type="hidden" id="aayushid" name="aayushid" value="">
											<input type="hidden" id="abha_no" name="abha_no" value="">

											<div class="upload_image">

												<img id="identity_image_preview" alt="Officer Image"
													src="assets/db_img/noimage.jpeg" /> <input type="hidden"
													id="upload_img_hid" name="upload_img_hid"
													class="form-control">

											</div>
										</div>
									</div>
								</div>
							</div>


							<div class="field-box">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
										<div class="custom-data-value custom-title custom-title-bg">
											<h5 class="custom-title-tag">Personal details</h5>
										</div>
									</div>
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Full Name</label> <span class="value-bind"
												id="first_name"></span>
										</div>
									</div>
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Father's Full Name</label> <span class="value-bind"
												id="father_name"></span>
										</div>
									</div>
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Date Of Birth</label> <span class="value-bind"
												id="dob1"></span> <input type="hidden" name="dob" id="dob"
												maxlength="10" aria-required="true" autocomplete="off"
												value="DD/MM/YYYY">
											<div class="info-value">
												<b>Age :</b><span class="text-heighlight get-value" id="age"
													name="age"></span>
											</div>
										</div>
										<input type="hidden" id="yrr" name="yrr" value="">
									</div>

									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Gender</label> <span class="value-bind" id="gender"></span>
										</div>
									</div>

 												 
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Aadhaar Number</label> <span class="value-bind"
												id="aadhaar_no"></span>
										</div>
									</div>
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Mobile Number</label> <span class="value-bind"
												id="mo_no"></span> <input type="hidden" id="u_id"
												name="u_id">
										</div>
									</div>
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Email Id</label> <span class="value-bind email-text-lowercase"
												id="email_id"></span>

										</div>
									</div>

									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Nationality</label> <span class="value-bind" id="nat"></span>
											<div class="select-position  custom-d-none">
												<select name="nationality" id="nationality"
													class="singleselect form-control form-control-lg custom_nationality">
													<option value="0" selected="selected">-- Select
														Country --</option>
													<c:forEach var="item" items="${getMedNationality}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>

											</div>
										</div>
									</div>
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Alternative Mobile Number 1</label> <span
												class="value-bind" id="alt_mo_no1"></span>
										</div>
									</div>

									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Alternative Mobile Number 2</label> <span
												class="value-bind" id="alt_mo_no2"></span>
										</div>
									</div>

									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Alternative Email ID 1</label> <span
												class="value-bind email-text-lowercase" id="alt_email_id1"></span>
										</div>
									</div>

									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Alternative Email ID 2</label> <span
												class="value-bind email-text-lowercase" id="alt_email_id2"></span>
										</div>
									</div>
								</div>
							</div>
							<div class="field-box">
								<div class="row">

									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
										<div class="custom-data-value custom-title custom-title-bg">
											<h5 class="custom-title-tag">Address details</h5>
										</div>
									</div>

									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Permanent address</label> <span class="value-bind">
												<span id="per_address"></span> <span id="per_address2"></span>
												<span id="per_address3"></span> <span id="per_state"></span>
												<span id="per_district"></span> <span id="per_pincode"></span>
											</span>
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
											<div class="select-position custom-d-none">
												<select name="per_district" id="per_district"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
												</select>

											</div>
										</div>
									</div>
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Present Address</label> <span class="value-bind">
												<span id="pre_address"></span> <span id="pre_address2"></span>
												<span id="pre_address3"></span> <span id="pre_state"></span>
												<span id="pre_district"></span> <span id="pre_pincode"></span>
											</span>
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
											<div class="select-position custom-d-none">
												<select name="pre_district" id="pre_district"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
												</select>

											</div>
										</div>
									</div>

									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Corresponding Address</label> <span class="value-bind">
												<span id="curr_address"></span> <span id="curr_address2"></span>
												<span id="curr_address3"></span> <span id="curr_state"></span>
												<span id="curr_district"></span> <span id="curr_pincode"></span>
											</span>
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
											<div class="select-position  custom-d-none">
												<select name="curr_district" id="curr_district"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
									</div>

								</div>

								<div class="field-box">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="custom-data-value custom-title custom-title-bg">
												<h5 class="custom-title-tag">Name of medical degree graduate / postgraduate / diploma obtained</h5>
											</div>
										</div>


										<div id="my_pdf_viewer" class="custom-d-none">
											<div class="row">
												<div class="col-md-12 custom_uni_pro_pre_data_divp">


													<div id="canvas_container">


														<div id="zoom_controls"
															class="custom_uni_pro_pre_data_divc">

															<div class="custom_uni_pro_pre_data_divc0">
																<label class="btn btn-success btn-sm pdfbtnzoom"
																	value=""></label><i id="zoom_in"
																	class="lni lni-zoom-in custom_uni_pro_pre_data_divci"
																	title='Zoom in'></i>
															</div>
															<br />
															<div class="custom_uni_pro_pre_data_divc0">
																<label class="btn btn-success btn-sm pdfbtnzoom"
																	value=""></label><i id="zoom_out"
																	class="lni lni-zoom-out custom_uni_pro_pre_data_divci"
																	title='Zoom out'></i>
															</div>
														</div>
														<canvas id="pdf_renderer"></canvas>
														<input type="hidden" value="0" id="PicturePDFId" /> <input
															type="hidden" value="0" id="val1" /> <input
															type="hidden" value="0" id="fildname1" />

													</div>

													<div class="custom_uni_pro_pre_data_divc1">
														<i id="go_previous"
															class="fa fa-angle-double-left pdfbtnzoom"
															title='Previous'></i>
													</div>
													<input id="current_page" value="1" type="number"
														class="custom_uni_pro_pre_data_inp" />
													<div class="custom_uni_pro_pre_data_divc0">
														<i id="go_next"
															class="lni lni lni-chevron-right pdfbtnzoom pdfbtnzoom-next custom_uni_pro_pre_data_i1"
															title='Next'></i>
													</div>
													<div id="downloadbtn">
														<i id="downloadbtn" title="Download"
															class='fa fa-download custom_uni_pro_pre_data_i2'></i>
													</div>

													<div id="downloadbtnview">
														<i id="downloadbtnview" title="Download"
															class='fa fa-download custom_uni_pro_pre_data_i2'></i>
													</div>



												</div>
											</div>
										</div>
									</div>
								</div>




								<section class="single-detail-block">
									<div class="tables-wrapper">
										<div class="row">
											<div class="col-lg-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="addNameOfMed">
														<thead>
															<tr>


																<th>Sr No</th>
																<th>Type of degree<strong class="mandatory"></strong></th>
																<th>Degree name<strong class="mandatory"></strong></th>
																<th>Month and Year<strong class="mandatory"></strong></th>
																<th>Name of institute / board<strong
																	class="mandatory"></strong></th>
																<th>Download<strong class="mandatory"></strong></th>
																<th>View attachment<strong class="mandatory"></strong></th>
															</tr>
														</thead>
														<tbody id="att_TbbodyNameMed">
															<tr id="tr_id_attNameMed">
																<td>
																	
																			<p>1</p>
																	
																</td>
																<td>
																	<p id="typeOfDegree1"></p>
																</td>
																<td>
																	<p id="DegreeName1"></p>
																</td>
																<td>
																	<p id="monthYearOfDegree1"></p>
																</td>
																<!-- 									<td> -->
																<!-- 										<span class="auto-input" id="NameOfUniversity1"></span> -->
																<!-- 									</td> -->

																<td>
																	<!-- 									 			<p id="NameOfUniversity1"></p>								  -->
																	<select name="NameOfUniversity1" id="NameOfUniversity1"
																	class="auto-input custom-d-none" disabled="disabled">
																		<option value="0">--Select--</option>
																		<p>
																			<c:forEach var="item" items="${getInstituteList}"
																				varStatus="num">
																				<option value="${item.id}"
																					name="${item.university_name}">${item.university_name}</option>
																			</c:forEach>
																</select> <!-- 								<input type="hidden" id="NameOfUniversity1_hid" name="NameOfUniversity1_hid" value="0"  autocomplete="off" /> -->
																	<p id="NameOfUniversity1_hid1"></p>
																</td>

																<td class="min-width addminusbut">
																	<ul class="buttons-group mainbtn action daobtn">
																		<li><a
																			class="main-btn info-btn btn-hover btn-sm btndownload addminusbut"
																			id="downloadbtn1" title="Downlaod"> <i
																				class="lni lni-download pdfdown"></i></a> <input
																			type="hidden" id="file_id1" name="file_id1"></li>
																	</ul>
																</td>

																<td class="min-width addminusbut">
																	<ul class="buttons-group mainbtn action">
																		<li id="viewattachment"><a
																			class="main-btn success-btn-outline btn-hover btn-sm addminusbut"
																			title="Downlaod"> <i class="bi bi-file-pdf"></i></a>
																			<input type="hidden" id="file_id1" name="file_id1"></li>
																	</ul>
																</td>

																<td class="min-width custom-d-none">
																	<div class="action">
																		<ul class="buttons-group mainbtn">
																			<li><a
																				class="main-btn info-btn-outline btn-hover btn-sm addminusbut"
																				value="ADD" title="ADD" id="id_add_attNameMed1">
																					<i class="lni lni-plus"></i>
																			</a></li>
																		</ul>
																	</div>
																</td>
															</tr>
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
												<!-- end card -->
											</div>
											<!-- end col -->
										</div>
										<!-- end row -->
									</div>
								</section>


								<div class="field-box">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="custom-data-value custom-title custom-title-bg">
												<h5 class="custom-title-tag">Institute / office / clinic / hospital</h5>
											</div>
										</div>
										<section class="single-detail-block">
											<div class="tables-wrapper">
												<div class="row">
													<div class="col-lg-12">
														<div class="table-wrapper table-responsive custom-table">
															<table class="table" id="addHospital">
																<thead>
																	<tr>
																		<th>Sr No</th>
																		<th>Place of working</th>
																		<th>Name of place</th>
																		<th>Adjunct place</th>
																		<th>Landline No</th>
																		<th>Mobile No</th>
																		<th>Email</th>
																		<th>Address</th>
																		<th>State</th>
																		<th>District</th>
																		<th>Authority type</th>
																		<th>Name of responsible owner</th>
																		<!-- <th>Date of Practice From</th>
																			<th>Date of Practice To</th> -->
																		<!-- 																		<th>Action</th> -->
																	</tr>
																	<!-- end table row-->
																</thead>
																<tbody id="att_TbbodyNameMedhosp">
																	<tr id="tr_id_attNameMed">
																		<td>
																		
																					<p>1</p>
																				
																		</td>
																		<td>
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
																		<td>
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
																		<td>
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

																		<td>
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
																		<td>
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
																		<td>
																			<div class="input-style-2">
																				<!-- 																				<label name="email1" id="email1"></label> -->
																				<p id="email1"></p>
																				<!-- 																					<input type="text" -->
																				<!-- 																						class="form-control autocomplete" id="email1" -->
																				<!-- 																						name="email1" placeholder="Enter Email Id" /> -->
																			</div>
																		</td>
																		<!-- 		<td>

																				<div class="input-style-2">
																					<input type="text" placeholder="Address"
																						id="address1" name="address1">
																				</div> <textarea rows="" cols="" id="address1"
																			name="address1"></textarea>
																			</td> -->

																		<td>
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
																		<td>
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

																		<td>
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
																		<td>

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
																		<td>
																			<div class="input-style-2">
																				<!-- 																				<label name="name_of_res_p1" id=name_of_res_p1></label> -->
																				<p id="name_of_res_p1"></p>
																				<!-- 																					<input type="text" id="name_of_res_p1" -->
																				<!-- 																						class="form-control autocomplete" -->
																				<!-- 																						name="name_of_res_p1" -->
																				<!-- 																						placeholder="Enter Name of Responsible Owner" /> -->
																			</div>

																		</td>


																		<td class="min-width addminusbut custom-d-none">
																			<ul class="buttons-group mainbtn action">
																				<li><a
																					class="main-btn info-btn-outline btn-hover btn-sm addminusbut"
																					value="ADD" title="ADD" id="id_add_attHospital1"><i
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
										</section>
									</div>
								</div>


								<ul class="buttons-group mainbtn">
									<!-- 								<li><a href="Regulation_Url"><input type="button" id="preview" -->
									<!-- 									class="main-btn secondary-btn btn-hover btn-save" value="Back" -->
									<!-- 									></a></li> -->
									<c:if test="${role == 'Practitioner_NCH'}">
										<li><a href="Practitioner_Form_A_URL" type="button"
											id="preview"
											class="main-btn dark-btn-outline  btn-hover btn-iconic-icon"><i
												class="lni lni-chevron-left"></i>Back</a></li>
									</c:if>

									<c:if test="${role == 'Intern_NCH'}">
										<li><a href="Regulation_Url" type="button" id="preview"
											class="main-btn dark-btn-outline  btn-hover btn-iconic-icon"><i
												class="lni lni-chevron-left"></i>Back</a></li>
									</c:if>

									<c:if test="${role == 'Student_NCH'}">
										<li><a href="intern_Regulation_Url" type="button"
											id="preview"
											class="main-btn dark-btn-outline  btn-hover btn-iconic-icon"><i
												class="lni lni-chevron-left"></i>Back</a></li>
									</c:if>
								</ul>



								<input type="hidden" id="Regulationstatus"
									name="Regulationstatus"> <input type="hidden" id="p_id"
									name="p_id" value="${p_id}"> <input type="hidden"
									id="parentid" name="parentid" value="${parentid}"> <input
									type="hidden" id="count_hidden_att_Hospital"
									name="count_hidden_att_Hospital"
									class="form-control autocomplete" value="1"> <input
									type="hidden" id="count_hidden_att_His"
									name="count_hidden_att_His" class="form-control autocomplete"
									value="1"> <input type="hidden" id="SaveDraft"
									name="SaveDraft" class="form-control autocomplete" value="0">

								<!-- end card -->

							</div>
						</div>
					</form:form>
				</div>

			</div>
		</div>
	</div>
</section>

<!-- =====URMIK CHANGES LATEST -->
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
<!-- regulation components end -->

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
	 
function validationAdd(obj){
	
// 	alert($("#count_hidden_att_doc1").val());
	
	if(validateAddMoreMedical(1)){
		if(validateAddMoreHospital(1)){
			if(obj == 0){
				$("#uploadForm").submit(); 
				}
			if(obj == 1){
			$("#uploadForm").submit(); 
			}
			if(obj == 2){
				return true

			}
			}else {
				return false;
			}
	}
}

// $("#modelWindow").on("load", function() {
// 	   $('#modelWindow').modal('hide');

// });
	
$(document).ready(function() {
	
// 	 alert("${hid}");
	$("#userId").val(${userId}); 
	//newdatavalid();
// 	$("#p_id").val("${p_id}");

    var userId =	${userId};
	if("${setAddMoreHospCMD.size()}" > 0){
		$("#count_hidden_att_Hospital").val("${setAddMoreHospCMD.size()}");
		$("#count_hidden_att_His").val("${setAddMoreHospCMD.size()}");
		var i = 1;
		<c:forEach var="j" items="${setAddMoreHospCMD}" varStatus="num">
//			formOpenHospital(i);
// 		var id= "${j[0]}";
// 		var place_of_working = "${j[1]}";
		var place_of_working_practitioner = "${j[1]}";
		var landline = "${j[2]}";
		var email = "${j[3]}";
		var authority_type = "${j[4]}";
		var name_of_res_p = "${j[5]}";
		var hos_address1 = "${j[6]}";
		var hos_address2 = "${j[7]}";
		var hos_address3 = "${j[8]}";
		var hos_state = "${j[9]}";
		var hos_district = "${j[10]}";
		var date_pract_from = "${j[11]}";
		var date_pract_to = "${j[12]}";
		var place_of_working_name = "${j[13]}";
		var adjunct_place = "${j[14]}";
		var mobile_no = "${j[15]}";
		if(place_of_working_practitioner != null || place_of_working_practitioner != ""){
			$("#place_of_working"+i).text(place_of_working_practitioner);
		}
		if(place_of_working_name != null || place_of_working_name != ""){
			$("#place_of_working_name"+i).text(place_of_working_name);
		}
			if(adjunct_place != null || adjunct_place != ""){
				$("#adjunct_place"+i).text(adjunct_place);
			}
		if(landline != null || landline != ""){
			$("#landline"+i).text(landline);
		}
		if(mobile_no != null || mobile_no != ""){
			$("#mobileHosp"+i).text(mobile_no);
		}
		if(email != null || email != ""){
			$("#email"+i).text(email);
		}
		if(authority_type != null || authority_type != ""){
			$("#authority_type"+i).text(authority_type);
		}
		if(hos_address1 != null || hos_address1 != ""){
			$("#hos_address1_"+i).text(hos_address1);
		}
		if(hos_address2 != null || hos_address2 != ""){
			$("#hos_address2_"+i).text(hos_address2);
		}
		if(hos_address3 != null || hos_address3 != ""){
			$("#hos_address3_"+i).text(hos_address3);
		}
		if(hos_state != null || hos_state != ""){
			$("#hos_state"+i).text(hos_state);
		}
		getDistricthos(i);
		if(hos_district != null || hos_district != ""){
			$("#hos_district"+i).text(hos_district);
		}
		if(name_of_res_p != null || name_of_res_p != ""){
			$("#name_of_res_p"+i).text(name_of_res_p);
		}
	if("${setAddMoreHospCMD.size()}" > i){
	$("#id_add_attHospital"+i).click();	
	}
	i++;
	</c:forEach>
 	}else{
		$("#att_TbbodyNameMedhosp").empty();
		$("#att_TbbodyNameMedhosp").append('<tr id="tr_id_attNameMed"><td colspan="12" ><p class="no-data">No Data Available</p></td></tr>');
	}
 	$.post('getayusAbhaDatalist?' + key + "=" + value,{userId:userId},function(k) {
	 	 
//  		for (var i = 0; i < k.length; i++) {
 		  
 		var aayushid =	k[0]["aayushid"];
 		var abha_no =	k[0]["abha_no"];
 		var first_name = k[0]["name"];
 		
 		//587462415524
// alert("name-----"+first_name)
 		
 		if(aayushid!= null && aayushid.trim()!=""){ 
 			$("#aayushid").val(aayushid);
 		}
 		if(abha_no!= null && abha_no.trim()!=""){ 
 			$("#abha_no").val(abha_no);
 		}
 		 if(first_name!= null && first_name.trim()!=" "){ 
 			$("#first_name").text(first_name);
 		 }
//  		}
	});
   
//  	$(window).on('load', function(){
// 		  // When the page has loaded
// 		   $('#modelWindow').modal('hide');
// 		}); 
//    var first_name = '${setRegAuth[0].first_name}';
// 	$("#first_name").val(first_name);
// 	if("${setRegAuth[0].state_name}" != ""){
// 	$("#reg_auth").text("${setRegAuth[0].state_name}");
// 	$("#reg_auth").attr("readonly","readonly");
// 	}
	
	if("${setRegAuth[0].email_id}" != ""){
	$("#email_id").text("${setRegAuth[0].email_id}");
	$("#email_id").attr("readonly","readonly");
	}
	if("${setRegAuth[0].aadhaar_no}" != ""){
		$("#aadhaar_no").text("${setRegAuth[0].aadhaar_no}");
		$("#aadhaar_no").attr("readonly","readonly");
		}
	
	if("${setRegAuth[0].name}" != ""){
		$("#name").val("${setRegAuth[0].name}");
		$("#name").attr("readonly","readonly");
		}

	$("#nationality").val(6);
		
		$.ajaxSetup({
			async : false
		});

		$.ajaxSetup({
			async : false
		});
	//	datepicketDate('date_of_reg');
	//	datepicketDate('valid_up_to');
	//	datepicketDate('date_pract_to1');
	//	datepicketDate('date_pract_from1');
	 
		if("${setDataCMD.size()}" != 0){
			
		setAll();	
// 		setTimeout(function() { 
// 			$("#closeModel2").click();
// 	    }, 500);
		$("#final_submit").show();
		$("#draft").hide();
		$("#update").show();
		}
		if("${hid}" == "1"){
			$("#draft").hide();
			$("#final_submit").hide();
			$("#update").hide();

		}
		
		function Pop_Up_Multi_Upload(a) {

			var x = screen.width/2 - 1100/2;
		    var y = screen.height/2 - 900/2;
		    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
			window.onfocus = function () { 
			}
			$("input#popid").val(a);
			document.getElementById('degreepopup_Form').submit();
			
		}
	
// 		------------------04/07/22 

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


function validateUptoR(){
	var date_reg = $("#date_of_reg").val();
	
	if(date_reg == "" || date_reg == "DD/MM/YYYY"){
		alert("Please Select Date Of First Registration First");
	}else{
		$("#valid_up_to").datepicker("option", "minDate", date_reg);
		
		$("#valid_up_to").val(date_reg);
		$('#valid_up_to').datepicker('option', 'maxDate', null);

// 		setMaxDate("valid_up_to",);
// 		$("#valid_up_to").datepicker("option", "maxDate", "+12m"); 
	}
}
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
	//HET CHANGES
	function formOpenNameMed(R){
//janki
		debugger
		if(validateAddMoreMedical(R)){
		 $("#addNameOfMed").show();
		
			 $("#id_add_attNameMed"+R).hide();
			 $("#att_id_removeNameMed"+R).hide();
			 
			 att=0;
			 att= parseInt(R)+1;
			
			 if(att < 51){
				 $("input#count_hidden_att_name_med").val(att);//current serial No
				 $("table#addNameOfMed").append('<tr align="center" id="tr_id_attNameMed'+att+'"><td><p>'+att+'<p></td>'
				 	+'<td><p id="typeOfDegree'+att+'"></p></td>'
				 	
				 	/*  <select name="typeOfDegree'+att+'" id="typeOfDegree'+att+'" class="form-control autocomplete" onchange="getDegreeName(this,'+att+')"> <option value="0">--Select--</option> <c:forEach var="item" items="${TypeOfDegree}" varStatus="num">	<option value="${item.id}" name="${item.type_of_degree}">${item.type_of_degree}</option> </c:forEach></select></td>' */
// 				 	+'<td><div class="select-style-1"><div class="select-position"><select name="DegreeName'+att+'" id="DegreeName'+att+'" class="form-control autocomplete"><option value="0">--Select--</option> </select></div></div></td>'
					+'<td><p id="DegreeName'+att+'"></p></td>'
				 	
				 	/* <select name="DegreeName'+att+'" id="DegreeName'+att+'" class="form-control autocomplete"> <option value="0">--Select--</option> </select> </td>' */
// 				 	+'<td><div class="input-style-2"><input type="month" id="monthYearOfDegree'+att+'" name="monthYearOfDegree'+att+'" class="form-control-sm form-control effect-9 hasDatepicker"></div</td>'
				 	+'<td><p id="monthYearOfDegree'+att+'"></p></td>'
				 	/* <input type="month" id="monthYearOfDegree'+att+'" name="monthYearOfDegree'+att+'"></td>' */
//  				 	+'<td><span class="auto-input" id="NameOfUniversity'+att+'"></span></td>'
				 	
				 	//janki
					+'<td>'
  					+'<select id="NameOfUniversity'+att+'" i name="NameOfUniversity'+att+'"  class="auto-input custom-d-none" value="">'
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
				 	+'id="downloadbtn'+att+'" title="Downlaod">'
				 	+'<i class="lni lni-download pdfdown"></i></a>'
				 	+'<input type="hidden" id="file_id'+att+'" name="file_id'+att+'"></li></ul>'
				 	+'	</td>'
				 	+'<td class="min-width addminusbut">'
				 	+'<ul class="buttons-group mainbtn action">'
				 	+'<li id="viewattachment">'
				 	+'<a class="main-btn success-btn-outline btn-hover btn-sm addminusbut" '
				 	+'id="downloadbtnview'+att+'" title="Downlaod">'
				 	+'<i class="bi bi-file-pdf"></i></a>'
				 	+'<input type="hidden" id="file_id1" name="file_id1">'
				 	+'</li>'
				 	+'	</ul>'
				 	+'</td>'
				 	+'<td class="custom-d-none"><ul class="buttons-group mainbtn"><li><a class="main-btn success-btn btn-hover btn-sm addminusbut value = "ADD" title = "ADD" id = "id_add_attNameMed'+att+'">'
                   	+'<i class="lni lni-plus"></i></a></li></ul></td>'
				 	
				 	/* <input type="text" id="NameOfUniversity'+att+'" name="NameOfUniversity'+att+'" value="" class="form-control autocomplete" autocomplete="off" onkeypress="AutoCompleteNameOfUniversity(this);"></td>' */
// 				 	+'<td><div class="input-style-2"><input type="button" id="btnmodel'+att+'" class="main-btn secondary-btn btn-hover btn-save" value="ATTACHMENT"></div></td>'
				 	/* <input type="file" id="attachment'+att+'" name="attachment'+att+'" accept="image/*" onchange="attachmentDetails(this,'+att+');"></td>'  */
// 				 	+'<td><div class="input-style-2"><input type="button" id="btnmodel'+att+'" class="main-btn secondary-btn btn-hover btn-save" value="ATTACHMENT"></div></td>'
				 	
// 				 	+'<td><div class="action"><ul class="buttons-group mainbtn"><li><a class="main-btn info-btn-outline btn-hover btn-sm addminusbut" value = "ADD" title = "ADD" id = "id_add_attNameMed'+att+'" onclick="formOpenNameMed('+att+');"><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn-outline btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeNameMed'+att+'" onclick="formopen_re_NameMed('+att+');"><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
				 	/* <a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_attNameMed'+att+'" onclick="formOpenNameMed('+att+');" ><i class="fa fa-plus"></i></a> <a style="margin-right: 0px;"class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeNameMed'+att+'" onclick="formopen_re_NameMed('+att+');"><i class="fa fa-trash"></a></td>'  */
				 	+'</tr>');
				     addOnclick(att);
				
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
		}
	}	
		
	function formopen_re_NameMed(R){
		
		 $("tr#tr_id_attNameMed"+R).remove();
		 att = att-1;
		 $("input#count_hidden_att_name_med").val(att);
		 $("#id_add_attNameMed"+att).show();
		 $("#att_id_removeNameMed"+att).show();	
		 if(att != null && att != "" && att == "${setAddMoreMedicalCMD.size()}")
		 $("#att_id_removeNameMed"+att).hide();	
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
	
// 	function AutoCompleteNameOfUniversity(obj){
// 		alert(obj.id);
// 	}
	
	function formOpenHospital(R){
		if(validateAddMoreHospital(R)){
		 $("#addHospital").show();
			 $("#id_add_attHospital"+R).hide();
			 $("#att_id_removeHospital"+R).hide();
			 att=0;
			 att= parseInt(R)+1;
			 if(att < 51){
				 $("input#count_hidden_att_Hospital").val(att);//current serial No

// 				 '<tr align="center" id="tr_id_attNameMed'+att+'"><td>'+att+'</td>'
// 				 	+'<td><span class="auto-input" id="typeOfDegree'+att+'"></span></td>'
				 
				 
				 $("table#addHospital").append('<tr align="center" id="tr_id_attHospital'+att+'"><td><p>'+att+'</p></td>'
				 	+'<td><p id="place_of_working'+att+'"></p></td>'
				 	+'<td><p id="place_of_working_name'+att+'"></p></td>'
				 	+'<td><p id="adjunct_place'+att+'"></p></td>'
				 	+'<td><p id="landline'+att+'"></p></td>'
				 	+'<td><p id="mobileHosp'+att+'"></p></td>'
				 	+'<td><p id="email'+att+'"></p></td>'
				 	+'<td><p id="hos_address1_'+att+'"></p><p id="hos_address2_'+att+'"></p><p id="hos_address3_'+att+'"></p></td>'
				 	+'<td><p id="hos_state'+att+'"></p></td>'
				 	+'<td><p id="hos_district'+att+'"></p></td>'
				 	+'<td><p id="authority_type'+att+'"></p></td>'
				 	+'<td><p id="name_of_res_p'+att+'"></p></td>'
				 	+'<td class="min-width custom-d-none"><td class="min-width addminusbut custom-d-none"><ul class="buttons-group mainbtn action"><li><a class="main-btn info-btn-outline btn-hover btn-sm addminusbut" value="ADD" title="ADD" id="id_add_attHospital'+att+'"><i class="lni lni-plus"></i></a></li></ul></td></td>'
				 	+'</tr>');
				    addpreOnclick(att);
			 }
		}
	}
	
	function formopen_re_Hospital(R){
		
		 $("tr#tr_id_attHospital"+R).remove();
		 att = att-1;
		 $("input#count_hidden_att_Hospital").val(att);
		 $("#id_add_attHospital"+att).show();
		 $("#att_id_removeHospital"+att).show();	
		 
		 if(att != null && att != "" && att == "${setAddMoreHospCMD.size()}")
		 $("#att_id_removeHospital"+"${setAddMoreHospCMD.size()}").hide();	
	}
	//	------------22/06/2022 urmik
	function getDegreeName(obj,R){
		var typeofdegree = $("#typeOfDegree"+R).val();
		
  	//	alert(typeofdegree+"---if")
		$.post('getDegreedetailsPreview?' + key + "=" + value,{typeofdegree:typeofdegree},function(k) {
			var options = '';
			for (var i = 0; i < k.length; i++) {
				options += '<option value="'+k[i].id+'" name="' + k[i].degree_name+ '" >'+ k[i].degree_name + '</option>';
			}
			$("#DegreeName"+R).html(options);
		});
	}
	
	function getDegreeNameAddMore(obj,R){
	
		var typeofdegree = $("#typeOfDegree"+R).val();
	//	alert(typeofdegree+"---else")
		$.post('getDegreedetailsPreview?' + key + "=" + value,{typeofdegree:typeofdegree},function(k) {
			var options = '';
			for (var i = 0; i < k.length; i++) {
				options += '<option value="'+k[i].id+'" name="' + k[i].degree_name+ '" >'+ k[i].degree_name + '</option>';
			}
			$("#DegreeName"+R).html(options);
		});	
	}
	
	function Validate() {
		/* personal details */
			var yrr=$("#yrr").val()
		if(validationAdd(2)){
return true;
		}else{
			return false;
		}
	}

	//pending
	function calculate_age(obj){    	
// 		debugger;
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
// 	    alert(year);
	    $(".get-value").text(""+year+" Years");
	    
	    $("#yrr").val(year);
	    
//	    document.getElementById("age_of_joining").value=year+"-"+month+"-"+days; 
	    //document.getElementById('age_year1').value = year;
	    
// 	    if (month == undefined)
// 	            {
// 	            month = 0;
// 	            }
// 	    document.getElementById('age_month').value = month;
	    
	}
	
	function FunctionState(val){
		var roleid = $("#"+val.id).val();

		$.post('getDistrictOnstatePreview?' + key + "=" + value,{roleid:roleid},function(k) {
// 			debugger;

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
	 	
		//alert("selval1--"+selval)
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
		
		$ .post(
						"getDistrictOnstatePreview?" + key + "=" + value,
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
		$
				.post(
						"getDistrictOnstatePreview?" + key + "=" + value,
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
// 		if("${setDataCMD[0]['dob']}" != null || "${setDataCMD[0]['dob']}" != ""){
// 			$("#dob").val("${setDataCMD[0]['dob']}");
// 		}
// 		if("${setDataCMD[0]['gender']}" != null || "${setDataCMD[0]['gender']}" != ""){
// 			$("input[name = 'gender']").val("${setDataCMD[0]['gender']}").click();
// 		}
// 		if("${setDataCMD[0]['mo_no']}" != null || "${setDataCMD[0]['mo_no']}" != ""){
// 			$("#mo_no").val("${setDataCMD[0]['mo_no']}");
// 		}
// 		if("${setDataCMD[0]['email_id']}" != null || "${setDataCMD[0]['email_id']}" != ""){
// 			$("#email_id").val("${setDataCMD[0]['email_id']}");
// 		}
		
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
		
// 		var g = ${setDataCMD[0]['gender']};
		$("#gender").text('${setDataCMD[0]['gender_name']}');
// 		alert(g);
// 		if(g== "1"){
// 			$("#gender").text("MALE");
// 		}
// 		else if(g== "7"){
// 			$("#gender").text("FEMALE");
// 		}
// 		else{
// 			$("#gender").text("TRANSGENDER");
// 		}
		
// 		if("${setDataCMD[0]['gender']}" != null && "${setDataCMD[0]['gender']}" != ""){
// 			$("#gender").text("${setDataCMD[0]['gender']}");
// 		} 
		
// 		$("select#gender").val(${View_tea_dtlCMD.gender});
// 		 var service = $('select#gender').find(":selected").text();
// 		$("#gender1").text(service);
		
		
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
		//getDistrictper();

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
		//getDistrictpre();

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
		//getDistrictcurr();

		if("${setDataCMD[0]['curr_district']}" != null && "${setDataCMD[0]['curr_district']}" != ""){
			$("#curr_district").text("${setDataCMD[0]['curr_district']}");
		}
		
		if("${setDataCMD[0]['curr_pincode']}" != null && "${setDataCMD[0]['curr_pincode']}" != ""){
			$("#curr_pincode").text("${setDataCMD[0]['curr_pincode']}");
		}
		
		if("${setDataCMD[0]['alt_mo_no1']}" != null && "${setDataCMD[0]['alt_mo_no1']}" != "0"){
			$("#alt_mo_no1").text("${setDataCMD[0]['alt_mo_no1']}");
		}
// 		----21/02/2022 URMIK
		if("${setDataCMD[0]['alt_mo_no1']}" == "0"){
			$("#alt_mo_no1").text("NA");
		}
		
		if("${setDataCMD[0]['alt_mo_no2']}" != null && "${setDataCMD[0]['alt_mo_no2']}" != "0"){
			$("#alt_mo_no2").text("${setDataCMD[0]['alt_mo_no2']}");
		}
// 		----21/02/2022 URMIK
		if("${setDataCMD[0]['alt_mo_no2']}" == "0"){
			$("#alt_mo_no2").text("NA");
		}
		
		if("${setDataCMD[0]['alt_email_id1']}" != null && "${setDataCMD[0]['alt_email_id1']}" != ""){
			$("#alt_email_id1").text("${setDataCMD[0]['alt_email_id1']}");
		}
// 		----21/02/2022 URMIK
		if("${setDataCMD[0]['alt_email_id1']}" == ""){
			$("#alt_email_id1").text("NA");
		}
		
		if("${setDataCMD[0]['alt_email_id2']}" != null && "${setDataCMD[0]['alt_email_id2']}" != ""){
			$("#alt_email_id2").text("${setDataCMD[0]['alt_email_id2']}");
		}
// 		----21/02/2022 URMIK
		if("${setDataCMD[0]['alt_email_id2']}" == ""){
			$("#alt_email_id2").text("NA");
		}
		
		if("${setDataCMD[0]['photo_path']}" != null && "${setDataCMD[0]['photo_path']}" != ""){
			$("#upload_img_hid").val("${setDataCMD[0]['photo_path']}");
			
			var idforimg = '${setDataCMD[0].id}';
			 $('#identity_image_preview').attr("src", "MedicalImagePath11?i_id="+idforimg+" ");
			
// 			var idforimg = '${setDataCMD[0]['photo_path']}';
// 			 $('#upload_img_hid').attr("src", "MedicalImagePath11?i_id="+idforimg+" ");
		}
		
		
// 		if("${setDataCMD[0]['per_pincode']}" != null || "${setDataCMD[0]['per_pincode']}" != ""){
// 			$("#per_pincode").val("${setDataCMD[0]['per_pincode']}");
// 		}
		
		
// 		if("${setDataCMD[0]['per_pincode']}" != null || "${setDataCMD[0]['per_pincode']}" != ""){
// 			$("#per_pincode").val("${setDataCMD[0]['per_pincode']}");
// 		}
		
		
// 		IF("${SETDATACMD[0]['REG_NO']}" != NULL && "${SETDATACMD[0]['REG_NO']}" != ""){
// 			$("#REG_NO").TEXT("${SETDATACMD[0]['REG_NO']}");
// 		}
		
		if("${setDataCMD[0]['date_of_reg']}" != null && "${setDataCMD[0]['date_of_reg']}" != ""){
			$("#date_of_reg").text("${setDataCMD[0]['date_of_reg']}");
			validateUptoR();
		}
		
		if("${setDataCMD[0]['registration_for_type']}" != null && "${setDataCMD[0]['registration_for_type']}" != ""){
			$("input[name = 'registration_for'][value='${setDataCMD[0]['registration_for_type']}']").click();
			if("${setDataCMD[0]['registration_for_type']}" == "1"){
				$("#valid").show();
				if("${setDataCMD[0]['valid_up_to']}" != null && "${setDataCMD[0]['valid_up_to']}" != ""){
					$("#valid_up_to").text("${setDataCMD[0]['valid_up_to']}");
				}	
			}
		}
		
// 		if("${setDataCMD[0]['attachment_path']}" != null || "${setDataCMD[0]['attachment_path']}" != ""){
// 			$("#attachment1").val("${setDataCMD[0]['attachment_path']}");
// 		}
		
		
// 		alert("${setDataCMD[0]['place_of_working']}");
		
		
		
// 		alert("${setDataCMD[0]['photo_path']}");
		
// 		if("${setDataCMD[0]['photo_path']}" != null || "${setDataCMD[0]['photo_path']}" != ""){
// 			$("#upload_img_hid").val("${setDataCMD[0]['photo_path']}");
// 		}
		
		//ADDMORE MEDICAL
		if("${setAddMoreMedicalCMD.size()}" > 0){
			
			$("#count_hidden_att_name_med").text("${setAddMoreMedicalCMD.size()}");
			$("#count_hidden_att_name_med1").text("${setAddMoreMedicalCMD.size()}");

debugger;
// 			alert('${setAddMoreMedicalCMD.size()}');
			var i = 1;
			<c:forEach var="j" items="${setAddMoreMedicalCMD}" varStatus="num">
// 			alert("urmikkkkkkkkkk4343"+'${setAddMoreMedicalCMD}');
			if (i>1) {
				formOpenNameMed(i-1);
			}
// 			alert('${setAddMoreMedicalCMD.size()}');
			var id = "${j[0]}";
			var name_of_institute = "${j[1]}";
// 			var attachment_path = "${j[2]}";
			var month_and_year_of_degree = "${j[2]}";
			var type_of_degree = "${j[3]}";
			var degree_name = "${j[4]}";
			var NameOfUniversity1_hid = "${j[5]}";
			
	 		if(type_of_degree != null && type_of_degree.trim() != ""){
				$("#typeOfDegree"+i).text(type_of_degree);
				getDegreeNameAddMore("typeOfDegree"+i,i);
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
// 			 $("#id_add_attNameMed"+i).hide();
		}

		//ADDMORE HOSPITAL
		if("${setAddMoreHospCMD.size()}" > 0){
		//	alert("${setAddMoreHospCMD.size()}")
			$("#count_hidden_att_Hospital").val("${setAddMoreHospCMD.size()}");
			$("#count_hidden_att_His").val("${setAddMoreHospCMD.size()}");
			var i = 1;
// 			alert("${setAddMoreHospCMD}");
			<c:forEach var="j" items="${setAddMoreHospCMD}" varStatus="num">
		//	formOpenHospital(i);
			
			
	//		debugger;
			
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
			var mobile_no = "${j[15]}";
			var adjunct_place = "${j[14]}";
			
			if(place_of_working != null || place_of_working != ""){
				$("#place_of_working"+i).text(place_of_working);
			}
			
			
			if(place_of_working_name != null || place_of_working_name != ""){
				$("#place_of_working_name"+i).text(place_of_working_name);
			}
			
			if(adjunct_place != null || adjunct_place != ""){
				$("#adjunct_place"+i).text(adjunct_place);
			}
			
			if(landline != null || landline != ""){
				$("#landline"+i).text(landline);
			}
			
			
			if(mobile_no != null || mobile_no != ""){
				$("#mobileHosp"+i).text(mobile_no);
			}
			if(email != null || email != ""){
				$("#email"+i).text(email);
			}
			
			if(authority_type != null || authority_type != ""){
				$("#authority_type"+i).text(authority_type);
			}
			
			if(hos_address1 != null || hos_address1 != ""){
				$("#hos_address1_"+i).text(hos_address1);
			}
			
			if(hos_address2 != null || hos_address2 != ""){
				$("#hos_address2_"+i).text(hos_address2);
			}
			
			
			if(hos_address3 != null || hos_address3 != ""){
				$("#hos_address3_"+i).text(hos_address3);
			}
			
			
			
			if(hos_state != null || hos_state != ""){
				$("#hos_state"+i).text(hos_state);
			}
			getDistricthos(i);
			
			if(hos_district != null || hos_district != ""){
				$("#hos_district"+i).text(hos_district);
			}
			
			if(name_of_res_p != null || name_of_res_p != ""){
				$("#name_of_res_p"+i).text(name_of_res_p);
			}
			
			
			
			
// 			if(date_pract_from != null || date_pract_from != ""){
// 				$("#date_pract_from"+i).val(date_pract_from);
// 			}
			
// 			if(date_pract_to != null || date_pract_to != ""){
// 				$("#date_pract_to"+i).val(date_pract_to);
// 			}
		
		if("${setAddMoreHospCMD.size()}" > i){
		 
		$("#id_add_attHospital"+i).click();	
		}
		i++;
		</c:forEach>
// 		alert("place_of_working-uuuuuuu-"+place_of_working)
	 		
		
	 		
// 	 		if (valid_dt  <= today  && '${hid}' == "3"){

// 				 $("#att_id_removeHospital"+"${setAddMoreHospCMD.size()}").hide();	
// 		 	   }else{
// 					if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
// 						 $("#att_id_removeHospital"+"${setAddMoreHospCMD.size()}").hide();	

// 			   }
		}
		
	}
	
	
	function photoValidate(){
		var path = $("#photo_path").val();
		$("#upload_img_hid").val(path); 
	}
	
	
	
// // 	------------------------latest 


	function copy_address() {
		
		if($("#check_address").is(":checked") == true){
			$("#pre_address").val($("#per_address").val());
			$("#pre_address").attr('readonly',true);
			
			$("#pre_address2").val($("#per_address2").val());
			$("#pre_address2").attr('readonly',true);
			
			$("#pre_address3").val($("#per_address3").val());
			$("#pre_address3").attr('readonly',true);
			
			$("#pre_state").val($("#per_state").val());
			$("#pre_state").change();
			$("#pre_state").attr('readonly',true);
			
			$("#pre_district").val($("#per_district").val());
			$("#pre_district").change();
			$("#pre_district").attr('readonly',true);
			
			$("#pre_pincode").val($("#per_pincode").val());
			$("#pre_pincode").attr('readonly',true);
			
		}
		
		if($("#check_address").is(":checked") == false){
			$("#pre_address").val("");
			$("#pre_address").attr('readonly',false);
			
			$("#pre_address2").val("");
			$("#pre_address2").attr('readonly',false);
			
			$("#pre_address3").val("");
			$("#pre_address3").attr('readonly',false);
			
			$("#pre_state").val("0");
			$("#pre_state").attr('readonly',false);
			
			$("#pre_district").val("0");
			$("#pre_district").attr('readonly',false);
			
			$("#pre_pincode").val("");
			$("#pre_pincode").attr('readonly',false);
			
			
		}
	}

	function changeAddress() {
		
		if($("#check_address").is(":checked") == true){
			$("#check_address").prop("checked", false);
			
			$("#pre_address").val("");
			$('#pre_address').attr('readonly', false);
			
			$("#pre_address2").val("");
			$('#pre_address2').attr('readonly', false);
			 
			$("#pre_address3").val("");
			$('#pre_address3').attr('readonly', false);
			
			$("#pre_state").val("0");
			$('#pre_state').attr('readonly', false);
			
			$("#pre_district").val("0");
			$('#pre_district').attr('readonly', false);
			
			$("#pre_pincode").val("");
			$('#pre_pincode').attr('readonly', false);
		}
	}
	
	function renewFunction(){ 
		
		if(validateAddMoreMedical(1)){
		$("#Regulationstatus").val(1);

// 		 var parentid = $("#parentid").val();
// 		 var count_hidden_att_name_med=$("#count_hidden_att_name_med").val();
	 var form = new FormData($("#uploadForm")[0]);

		 $.ajax({
		        type: "POST",
		        enctype: "multipart/form-data",
		        url: 'Renew_Data_Action?' + key + "=" + value,
		        data: form,
		        processData: false,
		        contentType: false,
		        cache: false,
		        timeout: 600000,
		        success: function (data) {
		        	 if(data=="0"){
				      	  alert("Your Record for Renew has been Forwarded Successfully. ");
// 				        else if(parseInt(data)>0)
// 					       	  {	    	   
// // 					        	alert("Data Saved Successfully");
					         
// 					        	//$("#id_hid").val(data)	        	        	
					   	        	 	        	
// 					          }
// 					          else
// 					          {
// 					        	  alert(data);
					        	    location.reload();

					          }
		        },
		        error: function (e) {
		          console.log("ERROR : ", e);
		        },
		    }); 
		 } 
	}

	//HET CHANGES COMPLETE
	
	//HET CHANGES
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
				 $("table#addAttDoc"+R).append('<tr align="center" id="tr_id_attDoc'+seq+'"><td>'+att+'</td>'
				 	+'<td><div class="input-style-2"><input type="text" id="NameOfAttachment'+seq+'" name="NameOfAttachment'+seq+'" value="" class="form-control autocomplete" autocomplete="off" placeholder="Name of Attachment"></div></td>'
				 	/* <input type="text" id="NameOfUniversity'+att+'" name="NameOfUniversity'+att+'" value="" class="form-control autocomplete" autocomplete="off" onkeypress="AutoCompleteNameOfUniversity(this);"></td>' */
				 	+'<td><div class="input-style-2"><input type="file" id="attachmentDocument'+seq+'" name="attachmentDocument'+seq+'" accept=".PDF" class="form-control"><input type="hidden" id="attachmentDoc_hid'+seq+'" name="attachmentDoc_hid'+seq+'" class="form-control"></div></td>' 
				 	/* <input type="file" id="attachment'+att+'" name="attachment'+att+'" accept="image/*" onchange="attachmentDetails(this,'+att+');"></td>'  */
				 	+'<td><div class="action"><ul class="buttons-group mainbtn"><li><a class="main-btn info-btn-outline btn-hover btn-sm addminusbut" value = "ADD" title = "ADD" id = "id_add_attDoc'+seq+'" name = "id_add_attDoc'+seq+'" ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn-outline btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeattDoc'+seq+'"><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
				 	/* <a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_attNameMed'+att+'" onclick="formOpenNameMed('+att+');" ><i class="fa fa-plus"></i></a> <a style="margin-right: 0px;"class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeNameMed'+att+'" onclick="formopen_re_NameMed('+att+');"><i class="fa fa-trash"></a></td>'  */
				 	+'</tr>');
				 adddoconclick(seq);
				 attachmentt(R,att);
			 
				}else{
						alert("Please Enter max 50 Quantity");
						 if ( att == 51){
							 att = att-1; 
							 $("#att_id_removeattDoc"+att).show();
						 }	   
				}
		}
		
	function formopen_re_attDoc(R,index){
		
// 		debugger;
		 $("tr#tr_id_attDoc"+R+"_"+index).remove();
		 att = index-1;
		 $("input#count_hidden_att_doc"+R).val(att);
		 $("#id_add_attDoc"+R+"_"+att).show();
		 $("#att_id_removeattDoc"+R+"_"+att).show();	
		 
// 		 if("#att_id_removeattDoc"+R+"_"+att)
// 			$("#att_id_removeattDoc"+i+"_"+temp).hide();	
	}
	
	function validateAddMoreMedical(R){
		var count = $("#count_hidden_att_name_med").val();
// 		for(var i=0;i<count;i++){
			
			var typedegree = $("#typeOfDegree"+R).val();
			var degreename = $("#DegreeName"+R).val();
			var monthYear = $("#monthYearOfDegree"+R).val();
			var nameUni = $("#NameOfUniversity"+R).val();
			
// 			if (typedegree == null || typedegree == "" || typedegree.trim() == "" || typedegree.trim() == "0") {
// 				alert("Please Select Type of Degree");
// 				$("#typeOfDegree"+R).focus();
// 				return false;
// 			}
// 			if (degreename == null || degreename == "" || degreename.trim() == "" || degreename.trim() == "0") {
// 				alert("Please Select Degree Name");
// 				$("#DegreeName"+R).focus();
// 				return false;
// 			}
			
// 			if (monthYear == null || monthYear == "" || monthYear.trim() == "") {
// 				alert("Please Enter Month And Year");
// 				$("#monthYearOfDegree"+R).focus();
// 				return false;
// 			}
			
			
// 			if (nameUni == null || nameUni == "" || nameUni.trim() == "") {
// 				alert("Please Enter Name of Institute/Board");
// 				$("#NameOfUniversity"+R).focus();
// 				return false;
// 			}
		
// 		}
return true;
	}
	
	function validateAddMoreHospital(R){
		var count = $("#count_hidden_att_name_med").val();
		
// 		for(var i=0;i<count;i++){
			
			var place_of_working = $("#place_of_working"+R).val();
			var place_of_working_name = $("#place_of_working_name"+R).val();
			var hos_address1 = $("#hos_address1_"+R).val();
// 			var hos_address2 = $("#hos_address2_"+R).val();
// 			var hos_address3 = $("#hos_address3_"+R).val();
			var landline = $("#landline"+R).val();
			var mobileHosp = $("#mobileHosp"+R).val();
			var email = $("#email"+R).val();
			
			var hos_state = $("#hos_state"+R).val();
			var hos_district = $("#hos_district"+R).val();
			var authority_type = $("#authority_type"+R).val();
			var name_of_res_p = $("#name_of_res_p"+R).val();
			
// 			if (place_of_working == null || place_of_working == "" || place_of_working.trim() == "" || place_of_working.trim() == "0") {
// 				alert("Please Select Place Of Working");
// 				$("#place_of_working"+R).focus();
// 				return false;
// 			}
// // 			---------------------19-07-22 testing changes
// 			if (place_of_working_name == null || place_of_working_name == "" || place_of_working_name.trim() == "" ) {
// 				alert("Please Enter Place of Working");
// 				$("#place_of_working_name"+R).focus();
// 				return false;
// 			}
			
// // 			---------------------19-07-22 testing changes
// 			if (landline == null || landline == "" || landline.trim() == "" ) {
// 				alert("Please Enter Landline Number");
// 				$("#landline"+R).focus();
// 				return false;
// 			}
			
			
// // 			---------------------19-07-22 testing changes
// 			if (mobileHosp == null || mobileHosp == "" || mobileHosp.trim() == "") {
// 				alert("Please Enter Mobile Number");
// 				$("#mobileHosp"+R).focus();
// 				return false;
// 			}
			
			
// // 			---------------------19-07-22 testing changes
// 			if (email == null || email == "" || email.trim() == "") {
// 				alert("Please Enter Email");
// 				$("#email"+R).focus();
// 				return false;
// 			}
			
// 			if (hos_address1 == null || hos_address1 == "" || hos_address1.trim() == "") {
// 				alert("Please Enter Hospital Address");
// 				$("#hos_address1_"+R).focus();
// 				return false;
// 			}
			
			
// 			if (hos_state == null || hos_state == "" || hos_state.trim() == "" || hos_state.trim() == "0") {
// 				alert("Please Select Hospital State");
// 				$("#hos_state"+R).focus();
// 				return false;
// 			}
		
			
// 			if (hos_district == null || hos_district == "" || hos_district.trim() == "" || hos_district.trim() == "0") {
// 				alert("Please Select Hospital District");
// 				$("#hos_district"+R).focus();
// 				return false;
// 			}
			
// 			if (authority_type == null || authority_type == "" || authority_type.trim() == "" || authority_type.trim() == "0") {
// 				alert("Please Select Authority Type");
// 				$("#authority_type"+R).focus();
// 				return false;
// 			}
		
			
// 			if (name_of_res_p == null || name_of_res_p == "" || name_of_res_p.trim() == "") {
// 				alert("Please Enter Name Of Responsible Person");
// 				$("#name_of_res_p"+R).focus();
// 				return false;
// 			}
			
// 		}
return true;
	}
	
	function dynamicTable(R,index){
	//	debugger;

// alert(R+"--"+index);
		var length = $("#count_hidden_att_name_med").val();
		for(var i = 1 ;i<=length;i++){
		$("#dynamicDataTable"+i).hide();
		}
		if($("#dynamicDataTable"+R).length){
			$("#dynamicDataTable"+R).show();
		}else{
		$("#dynamicDataTable").append("<div id='dynamicDataTable"+R+"'></div>");

		var seq = ""+R+"_"+index+"";
// 		var r1 = "'"+R+"_"+"'";

		$("div#dynamicDataTable"+R).append('<table class="table model-table" id="addAttDoc'+R+'"><thead><tr><th>Ser No</th>'
				+'<th>Name of Attachment</th>'
				+'<th>Attachment</th>'
				+'<th>Action</th>'
				+'</tr>'
				+'</thead>'
				+'<tbody id="att_TbbodyattDoc'+seq+'">'
				+'	<tr id="tr_id_attDoc'+seq+'">'
				+'		<td>'
				
				+'					<p>1</p>'
				
				+'		</td>'
				+'	<td>'
				+'			<div class="input-style-2">'
				+'			<input type="text" id="NameOfAttachment'+seq+'"'
				+'				name="NameOfAttachment'+seq+'" value=""'
				+'				class="form-control autocomplete" autocomplete="off"'
				+'			placeholder="Name of Attachments">'
				+'	</div> </td>'
				+'	<td>'
				+'		<div class="input-style-2">'
				+'			<input type="file" id="attachmentDocument'+seq+'" name="attachmentDocument'+seq+'"'
				+'				accept=".PDF"'
				+'			class="form-control"> <input type="hidden"'
				+'			id="attachmentDoc_hid'+seq+'" name="attachmentDoc_hid'+seq+'"'
				+'			class="form-control">'
				+'</div> '
				+'</td>'
				+'<td class="min-width addminusbut">'
				+'	<div class="action">'
				+'	<ul class="buttons-group mainbtn">'
				+'		<li><a'
				+'			class="main-btn info-btn-outline btn-hover btn-sm addminusbut"'
				+'		value="ADD" title="ADD" id="id_add_attDoc'+seq+'" name="id_add_attDoc'+seq+'"' 
				+'		><i'
				+'			class="lni lni-plus"></i></a></li>'
				+'	</ul>'
				+'	</div>'
				+'</td> </tr> </tbody> </table>'
				+'<input type="hidden" id="count_hidden_att_doc'+R+'" name="count_hidden_att_doc'+R+'" class="form-control autocomplete" value="1">');
// 				adddynamiconclick(R,index);
// 				attachmentt1(seq);
		}
// 		$('#modelWindow').modal('show');

// 		alert($("#count_hidden_att_doc1").val());
	}
	
	var filelength = 0;
	var xml_file_name = '';
	function onfileselected(event){
		filelength = event.target.files.length;
		var arpanlist = '';
		var arr = [];
		
		var arry = new Array();
		
		for(var i = 0;i< filelength;i++){
			var selectedfile = event.target.files[i];
			xml_file_name = selectedfile.name;
			arry.push(xml_file_name);
		}
	   $("#my_attachment_hid").val(arry);
	}
	
	//HET CHANGES COMPLETE
	
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
//		debugger;
//	 	var id = $("#file_id").val(); 
		var val= $("#file_id"+obj).val();
//	 	var fildname= $("#fildname1").val();
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
			 
//				 console.log("p-------->    "+p)
			 
			 $("#dlall_pdf").append(
						'<a class="main-btn success-btn-outline btn-hover btn-sm addminusbut" id="downloadbtnnn'+p+'" title="Downlaod">'
		   				+'<i class="bi bi-file-pdf"></i></a><input type="hidden" id="fid'+p+'" name="fid'+p+'" value="'+k[p][1]+'">'
				);
			 adddownloadOnclick(p);
			
		 }
		
	});
 		
	var pdf1="attachmentfiledownload?kmlId2="+val;
//		$("#my_pdf_viewer").show();
	
	$("#exampleModal").modal('show');
	PDFView(pdf1);
 	
}

function download_file_view2(obj) {
	
	var val= $("#fid"+obj).val();
	var pdf1="attachmentfiledownload?kmlId2="+val;
	PDFView(pdf1,val,0,0);
 		
}
 
// 	===================for view

function PDFView(path1,idx,val,field){
	
// 	$("div#my_pdf_viewer").show();
//		if ($("#my_pdf_viewer").hasClass("d-none")) {
//		 $( "#my_pdf_viewer" ).removeClass("d-none")
//	}

	
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
	
//		  Update page counters
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
	
// 	document.getElementById('downloadbtn'+idx).addEventListener('click', (e) => {
// 		download_file();
// 	});

//document.getElementById('btn-close-pdf').addEventListener('click', (e) => {
//	hide_PDF();
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
	
		document.addEventListener('DOMContentLoaded', function() {
			document.getElementById('dob').onclick = function() {
	 			return clickclear(this, 'DD/MM/YYYY');
	 		};
	 		document.getElementById('dob').onfocus = function() {
	 			return this.style.color='#000000';
	 		};
	 		document.getElementById('dob').onblur = function() {
	 			return clickrecall(this,'DD/MM/YYYY');
	 		};
	 		document.getElementById('dob').onblur = function() {
	 			return validateDate_BackDate(this.value,this);
	 		};
	 		document.getElementById('dob').onkeyup = function() {
	 			return clickclear(this, 'DD/MM/YYYY');
	 		};
	 		document.getElementById('dob').onchange = function() {
	 			return clickrecall(this,'DD/MM/YYYY');
	 		};
	 		document.getElementById('dob').onchange = function() {
	 			validateDate_FutureDate(this.value,this);
	 		};
	 		document.getElementById('dob').onchange = function() {
	 			return calculate_age('dob');
	 		};
	 		document.getElementById('per_state').onchange = function() {
	 			getDistrictper();
	 		};
	 		document.getElementById('per_state').onchange = function() {
	 			changeAddress();
	 		};
	 		document.getElementById('per_district').onchange = function() {
	 			changeAddress();
	 		};
	 		document.getElementById('pre_state').onchange = function() {
	 			getDistrictpre();
	 		};
	 		document.getElementById('curr_state').onchange = function() {
	 			getDistrictpre();
	 		};
	 		document.getElementById('downloadbtn').onclick = function() {
	 			download_file();
	 		};
	 		document.getElementById('downloadbtnview').onclick = function() {
	 			download_file_view();
	 		};
	 		document.getElementById('downloadbtn1').onclick = function() {
	 			download_file(1);
	 		};
	 		document.getElementById('viewattachment').onclick = function() {
	 			download_file_view(1);
// 	 			View_hide();
	 		};
	 		document.getElementById('id_add_attNameMed1').onclick = function() {
	 			formOpenNameMed(1);
	 		};
	 		document.getElementById('addHospital').onclick = function() {
	 			formOpenHospital();
	 		};
	 		document.getElementById('id_add_attHospital1').onclick = function() {
	 			formOpenHospital(1);
	 		};
	 		document.getElementById('downloadbtnview').onclick = function() {
	 			View_hide();
	 		};
// 	 		document.getElementById('NameOfAttachment').onkeypress = function() {
// 				 AutoCompleteNameOfUniversity(this);
// 			};
			
// 			document.getElementById('NameOfAttachment').onkeypress = function() {
// 				 AutoCompleteNameOfUniversity(this);
// 			};
	 		
	 		
		});
		function addOnclick(index) {
			document.getElementById('downloadbtn' + index).onclick = function() {
				download_file(index);
			};
			document.getElementById('downloadbtnview' + index).onclick = function() {
				download_file_view(index);
			};
			document.getElementById('id_add_attNameMed' + index).onclick = function() {
				formOpenNameMed(index);
			};
		}
		function addpreOnclick(index) {
			document.getElementById('id_add_attHospital' + index).onclick = function() {
				formOpenHospital(index);
			};
		}
		function adddoconclick(seq) { 
			document.getElementById('attachmentDocument' + seq).onclick = function() {
				attachmentDetails(this,seq);
			};
		}
		function attachmentt(R,att){
			  document.getElementById('id_add_attDoc'+R+"_"+att).onclick = function () {
					 formOpenattDoc(R,att);
			  };
			  document.getElementById('att_id_removeattDoc'+R+"_"+att).onclick = function () {
				  formopen_re_attDoc(R,att);
			  };
		}
// 		function adddynamiconclick(R,index){
		  
// 			document.getElementById('id_add_attDoc'+R+'_'+index).onclick = function () {
// 				formOpenattDoc(R,1);
// 			};
// 		}
// 		function attachmentt1(seq){
// 			document.getElementById('attachmentDocument'+seq).onchange = function () {
// 				attachmentDetails(this,1);
// 			};
// 		}
		function  adddownloadOnclick(p){
			 console.log("p--1------>    "+p)
			document.getElementById('downloadbtnnn'+p).onclick = function() {
				 console.log("p---2----->    "+p)
				download_file_view2(p);
			};	
		}
	
	</script>