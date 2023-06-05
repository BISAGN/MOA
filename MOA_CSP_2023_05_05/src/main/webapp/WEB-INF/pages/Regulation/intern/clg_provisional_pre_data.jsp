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
<!-- <link rel="stylesheet" href="js/assets/collapse/1collapse.css"> -->
<!-- <link rel="stylesheet" href="../assets/db_css/db_custom_style.css"> -->
<!-- <link rel="stylesheet" href="assets/db_css/db_custom_style.css"> -->
<script src="js/miso/commonJS/addmorefunctionality.js"
	type="text/javascript"></script>
<script src="Pages_JS/Policy_Creation.js" type="text/javascript"></script>
<!-- <script type="text/javascript" src="js/watermark/common.js"></script> -->
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/Pdf_View/pdf.js" type="text/javascript"></script>
<script src="js/Pdf_View/pdf.worker.js" type="text/javascript"></script>
<!-- 	<script src="js/sweetalert/sweetalert.min.js"></script> -->
<!-- <link rel="stylesheet" href="assets/vendor/internal_css.css"> -->
<section class="dashboard-page regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>View And Verify Attachment</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">View
									& Verify Attachment</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper preview-form">
			<div class="row">

				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form action="ProvisionalRegulationPreview_Action"
						id="uploadForm" method="POST"
						modelAttribute="provisionalRegulationPreviewCMD"
						enctype="multipart/form-data">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="card-style mb-30">
									<div class="field-box">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="auto-fill-form">
													<div class="upload_image">
														<img id="identity_image_preview" alt="Officer Image"
															src="assets/db_img/noimage.jpeg" /> <input type="hidden"
															id="upload_img_hid" name="upload_img_hid"
															class="form-control"> <input type="hidden"
															id="userId" name="userId" value="0"> <input
															type="hidden" id="aayushid" name="aayushid" value="">
														<input type="hidden" id="abha_no" name="abha_no" value="">
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
														<b>Age :</b><span class="text-heighlight get-value"
															id="age" name="age"></span>
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
													<label>Nationality</label> <span class="value-bind"
														id="nat"></span>
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
													<label>Alternative Email Id 1</label> <span
														class="value-bind email-text-lowercase" id="alt_email_id1"></span>
												</div>
											</div>

											<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
												<div class="custom-data-value">
													<label>Alternative Email Id 2</label> <span
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
													<label>Permanent Address</label> <span class="value-bind">
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
													<label>Corresponding Address</label> <span
														class="value-bind"> <span id="curr_address"></span>
														<span id="curr_address2"></span> <span id="curr_address3"></span>
														<span id="curr_state"></span> <span id="curr_district"></span>
														<span id="curr_pincode"></span>
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
									</div>

									<div class="field-box">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="custom-data-value custom-title custom-title-bg">
													<h5 class="custom-title-tag">Name Of Medical Degree
														Graduate/PostGraduate/Diploma Obtained</h5>
												</div>
											</div>

											<div
												class="col-md-12 custom_uni_pro_pre_data_divp custom-d-none"
												id="my_pdf_viewer">

												<div id="canvas_container">
													<div id="zoom_controls"
														class="custom_uni_pro_pre_data_divc">
														<div class="custom_uni_pro_pre_data_divc0">
															<label class="btn btn-success btn-sm pdfbtnzoom" value=""></label><i
																id="zoom_in"
																class="lni lni-zoom-in custom_uni_pro_pre_data_divci"
																title='Zoom in'></i>
														</div>
														<br />
														<div class="custom_uni_pro_pre_data_divc0">
															<label class="btn btn-success btn-sm pdfbtnzoom" value=""></label><i
																id="zoom_out"
																class="lni lni-zoom-out custom_uni_pro_pre_data_divci"
																title='Zoom out'></i>
														</div>
													</div>
													<canvas id="pdf_renderer"></canvas>
													<input type="hidden" value="0" id="PicturePDFId" /> <input
														type="hidden" value="0" id="val1" /> <input type="hidden"
														value="0" id="fildname1" />
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

												<ul class="buttons-group mainbtn" id="dlall_pdf">
												</ul>

											</div>
  <section class="single-detail-block">
											<div class="tables-wrapper">
												<div class="row">
													<div class="col-12 col-lg-12 com-md-12 col-sm-12">

														<div class="inst_block simple-instruction">
															<strong>Instruction :</strong> Please View and Verify
															Attachment.
														</div>
														<div class="col-12 col-lg-12 com-md-12 col-sm-12">
															<div class="table-wrapper table-responsive custom-table">
																<table class="table" id="addNameOfMed">
																	<thead>
																		<tr>
																			<th>Sr No</th>
																			<th>Select<strong class="mandatory"></strong></th>
																			<th>Type of degree<strong class="mandatory"></strong></th>
																			<th>Degree name<strong class="mandatory"></strong></th>
																			<!-- 							<th>Month and Year<strong class="mandatory"></strong></th> -->
																			<th>Name of institute / board<strong
																				class="mandatory"></strong></th>
																			<th>Verification status<strong class="mandatory"></strong></th>
																			<th>View attachment<strong class="mandatory"></strong></th>
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

																			<input type="hidden" id="CheckVal" name="CheckVal">
																			<td class="min-width">
																				<div id="ck1"></div>
																			</td>

																			<td class="min-width">
																				<p id="typeOfDegree1"></p>
																			</td>
																			<td class="min-width">
																				<p id="DegreeName1"></p>
																			</td>


																			<td class="min-width"><select
																				name="NameOfUniversity1" id="NameOfUniversity1"
																				class="custom-d-none auto-input">
																					<option value="0">--Select--</option>
																					<c:forEach var="item" items="${getInstituteList}"
																						varStatus="num">
																						<option value="${item.id}"
																							name="${item.university_name}">${item.university_name}</option>
																					</c:forEach>
																			</select> <!-- 								<input type="hidden" id="NameOfUniversity1_hid" name="NameOfUniversity1_hid" value="0"  autocomplete="off" /> -->


																				<p id="NameOfUniversity1_hid1"></p></td>

																			<td class="min-width">
																				<p id="v_status1"></p>
																			</td>

																			<td class="min-width addminusbut">
																				<ul class="buttons-group mainbtn action">
																					<li id="viewattachment"><a
																						class="main-btn success-btn-outline btn-hover btn-sm addminusbut"
																						id="downloadbtnview1" title="Download"> <i
																							class="bi bi-file-pdf"></i></a> <input type="hidden"
																						id="file_id1" name="file_id1"></li>
																				</ul>
																			</td>

																			<td class="min-width addminusbut">
																				<ul class="buttons-group mainbtn action">
																					<li><a
																						class="main-btn info-btn btn-hover btn-sm btndownload addminusbut"
																						id="downloadbtn1" title="Download"> <i
																							class="lni lni-download pdfdown"></i></a> <input
																						type="hidden" id="file_id1" name="file_id1"></li>
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
															</div>
															<!-- Bottom Button Start -->
															<div class="btn-bottom">
																<div class="row">
																	<div class="col-lg-12 col-md-12 col-sm-12">
																		<ul class="buttons-group mainbtn">
																			<li  id="apprvbt"><input type="button"
																				class="main-btn success-btn  btn-hover btnappr "
																				value="Approve"></li>
																			<li id="rejbt" ><input type="button"
																				class="main-btn danger-btn btn-hover btnreject"
																				value="Reject" name="rejbt"></li>
																		</ul>
																	</div>
																</div>
															</div>

														</div>
														
													</div>
												</div>
											</div>
											</section>
										</div>
										<div class="modal fade custom-modal bd-example-modal-lg"
											tabindex="-1" role="dialog"
											aria-labelledby="myLargeModalLabel" aria-hidden="true"
											id="modelWindowR">
											<div class="modal-dialog modal-xl modal-dialog-scrollable">
												<div class="modal-content">
													<div class="modal-header">
														<h3 class="modal-title" id="reject_remarks">Enter
															Remarks</h3>

														<button type="button" class="btn-close" id="cls"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>

													<div class="modal-body custom-modal-table">

														<div class="custom-modal-inner">
															<div class="table-wrapper table-responsive custom-table">
																<table class="table model-table" id="addAttDoc">
																	<thead>
																		<tr>
																			<th>Sr No.</th>
																			<th>Degree Name</th>
																			<th>Remarks</th>
																		</tr>
																	</thead>
																</table>
																<div id="dynamicDataTableR"></div>
															</div>
														</div>

													</div>
													<div class="modal-footer">
														<ul class="buttons-group">
														<li><a type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal">Close</a></li>
															<li><button type="button"
																	class="main-btn success-btn  btn-hover" 
																	data-bs-dismiss="modal" data-dismiss="modal"
																	aria-label="Close" id="ok">Submit</button></li>
														</ul>
													</div>
													
												</div>
											</div>
										</div>
									</div>

									<div class="field-box">
										<section class="single-detail-block">
											<div id="pl_wr">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12 col-12">
														<div
															class="custom-data-value custom-title custom-title-bg">
															<h5 class="custom-title-tag">Institute/Office/Clinic/Hospital</h5>
														</div>
													</div>
													<div class="tables-wrapper">
														<div class="row">
															<div class="col-12 col-lg-12 com-md-12 col-sm-12">
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
																						<p id="place_of_working1"></p>
																					</div>
																				</td>
																				<td class="min-width">

																					<div class="input-style-1">
																						<p id="place_of_working_name1"></p>
																					</div>

																				</td>
																				<td class="min-width">

																					<div class="input-style-1">
																						<p id="adjunct_place1"></p>
																					</div>

																				</td>

																				<td class="min-width">
																					<div class="input-style-1">
																						<p id="landline1"></p>
																					</div>
																				</td>
																				<td class="min-width">
																					<div class="input-style-1">
																						<p id="mobileHosp1"></p>
																					</div>
																				</td>
																				<td class="min-width">
																					<div class="input-style-1">
																						<p id="email1"></p>
																					</div>
																				</td>
																				<td class="min-width">
																					<div class="input-style-1">
																						<p id="hos_address1_1"></p>
																						<p id="hos_address2_1"></p>
																						<p id="hos_address3_1"></p>
																					</div> <!--  																	<textarea rows="" cols="" id="address1" name="address1"></textarea>  -->
																				</td>
																				<td class="min-width">
																					<div class="select-style-1">
																						<p id="hos_state1"></p>
																					</div>
																				</td>
																				<td class="min-width">
																					<div class="select-style-1">
																						<p id="hos_district1"></p>
																					</div>
																				</td>
																				<td class="min-width">
																					<div class="select-style-1">
																						<p id="authority_type1"></p>
																					</div>

																				</td>
																				<td class="min-width">
																					<div class="input-style-1">
																						<p id="name_of_res_p1"></p>
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
																		</tbody>
																	</table>

																	<input type="hidden" id="count_hidden_att_name_med"
																		name="count_hidden_att_name_med"
																		class="form-control autocomplete" value="1">
																</div>
															</div>
														</div>

													</div>
												</div>
											</div>
										</section>
									</div>

									<!-- Bottom Button Start -->
									<div class="btn-bottom">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><a href="edu_search_pro_clg_reg_url" type="button"
														id="preview"
														class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
															<i class="lni lni-chevron-left"></i>Back
													</a></li>
												</ul>
											</div>
										</div>
									</div>
									<!-- Bottom Button End -->


									<input type="hidden" id="Regulationstatus"
										name="Regulationstatus"> <input type="hidden"
										id="p_id" name="p_id" value="${p_id}"> <input
										type="hidden" id="parentid" name="parentid"
										value="${parentid}"> <input type="hidden"
										id="count_hidden_att_Hospital"
										name="count_hidden_att_Hospital"
										class="form-control autocomplete" value="1"> <input
										type="hidden" id="count_hidden_att_His"
										name="count_hidden_att_His" class="form-control autocomplete"
										value="1"> <input type="hidden" id="SaveDraft"
										name="SaveDraft" class="form-control autocomplete" value="0">
									<!-- end card -->
									<div class="modal fade custom-modal bd-example-modal-lg"
										tabindex="-1" role="dialog"
										aria-labelledby="myLargeModalLabel" aria-hidden="true"
										id="modelWindow">
										<div class="modal-dialog modal-xl modal-dialog-scrollable">
											<div class="modal-content">
												<div class="modal-header">
													<h3 class="modal-title" id="myLargeModalLabel">Upload
														Attachments</h3>
													<i data-dismiss="modal" id="closeModel" aria-label="Close"
														class="bi bi-x-lg"></i>
												</div>
												<div class="modal-body custom-modal-table">
													<div class="custom-modal-inner">
														<div class="table-wrapper table-responsive custom-table">
															<div id="dynamicDataTable"></div>
														</div>
													</div>

												</div>
												<div class="modal-footer">
													<ul class="buttons-group">
														<li><button type="button"
																class="main-btn dark-btn n btn-hover"
																data-bs-dismiss="modal" data-dismiss="modal"
																aria-label="Close">Close</button></li>
													</ul>
												</div>

											</div>
										</div>
									</div>

								</div>
							</div>
						</div>

					</form:form>

				</div>

			</div>

		</div>

	</div>

</section>

<script nonce="${cspNonce}" type="text/javascript">

function findselected(){
	 
 	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		return $(this).attr('id');
	}).get();
 	var b=nrSel.join(':');
	$("#CheckVal").val(nrSel);
	 
	 
}

function setRejectStatus(){
		findselected();
		var a = $("#CheckVal").val();hos_address1_1
		var tempSt='';
	var f = a.split(",");
	
	 var upto2 = $("#CheckVal2").val();
	for (var i = 0; i < f.length; i++) {
	 			if($("#remarks"+f[i]).val() == ""){
			alert("Please Enter the Remarks for Revert Back"); 
			return false;
		}
		 
		if(i==0){
			tempSt+=$("#remarks"+f[i]).val();
		}else{
			tempSt+=","+$("#remarks"+f[i]).val();
		}
	 
	}
	
	if(tempSt == ""){
		}else{
				$.post("Clg_Reject_Degree?"+key+"="+value, {a:a,status:"2",tempSt:tempSt}).done(function(j) {
					alert("Revert Back Successfully");
					window.location.href="edu_search_pro_clg_reg_url";
		}); 
	}
}


function setApproveStatus(){
findselected();
 var a = $("#CheckVal").val();
	 var u_id = $("#u_id").val();
 
if(a == ""){
	alert("Please Select the Data for Approval"); 
} 
else{
		$.post("Clg_Approve_Degree?"+key+"="+value, {a:a,status:"1",u_id:u_id}).done(function(j) {
		
			alert("Approved Successfully");

		location.reload();
	}); 
}
}




$(document).ready(function() {
	
	
	
	document.getElementById('apprvbt').onclick = function() {
		return setApproveStatus();
	};
	
document.getElementById('ok').onclick = function() {
		
		return setRejectStatus();
	};
	document.getElementById('rejbt').onclick = function() {
 
		   $('#modelWindowR').modal('show');
 		  findselected();
			var a = $("#CheckVal").val();
			 
 			if(a == ""){
				alert("Please Select the Data for Revert Back"); 
			}else{
   
				 dynamicTableR(a);

			}
		   
 	};

 
	
	$("#u_id").val(${u_id}); 
 	$("#userId").val(${userId}); 
    var userId =	${userId};
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
	  
		if("${setDataCMD.size()}" != 0){
 		setAll();	
 		}
		 
 
var valid_dt = '${valid_dt}' ;
  
	});
 
  

	//==============================CSP Start==============================

	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('dob').onfocus = function() {
			this.style.color='#000000';
		};
		document.getElementById('dob').onchange = function() {
				clickrecall(this,'DD/MM/YYYY');
				validateDate_FutureDate(this.value,this);
				calculate_age('dob');  
		};
		document.getElementById('dob').onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
	    };
	    document.getElementById('dob').onblur = function() {
	    	clickrecall(this,'DD/MM/YYYY');
	    	validateDate_BackDate(this.value,this);
	    };
	    document.getElementById('per_state').onchange = function() {
	    	getDistrictper(); 
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
	    
	   document.getElementById('downloadbtnview1').onclick = function() {
		 download_file_view(1); 
		  View_hide();
		};
		
		document.getElementById('downloadbtn1').onclick = function() {
			download_file(1);
	 		};
	 		
	 	document.getElementById('id_add_attNameMed1').onclick = function() {
	 		formOpenNameMed(1);
	 	 		};
	 	 		
	 	document.getElementById('id_add_attHospital1').onclick = function() {
	 	 			formOpenHospital(1);
	 	 }; 
	 	document.getElementById('downloadbtn').onclick = function() {
	 		download_file();
 };
 
	document.getElementById('downloadbtnview').onclick = function() {
	 download_file_view();
 };
 
document.getElementById('addHospital').onchange = function() {
	formOpenHospital();
};


	 	 
	});
	function formOpenNameMed(R){
		if(validateAddMoreMedical(R)){
		 $("#addNameOfMed").show();
			 $("#id_add_attNameMed"+R).hide();
			 $("#att_id_removeNameMed"+R).hide();
			 
			 att=0;
			 att= parseInt(R)+1;
			 if(att < 51){
				 
				   
				 $("input#count_hidden_att_name_med").val(att);//current serial No
				 $("table#addNameOfMed").append('<tr align="center" id="tr_id_attNameMed'+att+'"><td>'+att+'</td>'
 				 
// 					janki	 
// 					 +'<td><input type="checkbox"  class="form-check-input" id="cbox'+att+'" value=" " ></td>'
					 +'<td class="min-width"><div id ="ck'+att+'"> </div></td><td class="min-width"><p id="typeOfDegree'+att+'"></p></td>'
						+'<td class="min-width"><p  id="DegreeName'+att+'"></p></td>'
					 	+'<td class="min-width"><p id="monthYearOfDegree'+att+'"></p></td>'
						
					 	+'<td class="min-width" >'
	  					+'<select id="NameOfUniversity'+att+'" i name="NameOfUniversity'+att+'"  class="auto-input custom-d-none"  value=""  >'
	 				 	+'	<option value="0">--Select--</option>'
	 				 	+'	<c:forEach var="item" items="${getInstituteList}" varStatus="num">'
	 				 	+'	<option value="${item.id}" name="${item.university_name}">${item.university_name}</option>'
	 				 	+'</c:forEach>'
						+'	</select>'
						 
						+' <p  id="NameOfUniversity1_hid'+att+'" ></p> '
						+' 	</td>  '
						 +'<td class="min-width"><p  id="v_status'+att+'"></p></td>'
					 	
						+'<td class="min-width addminusbut">'
					 	+'<ul class="buttons-group mainbtn action">'
					 	+'<li><a class="main-btn success-btn-outline btn-hover btn-sm addminusbut" '
					 	+' id="downloadbtn' +att+'" title="Download">'
//	 				 	+'onclick="download_file_view('+att+');" id="downloadbtnview' +att+ '" title="Download">'
					 	+'<i class="bi bi-file-pdf"></i></a>'
					 	+'<input type="hidden" id="file_id'+att+'" name="file_id'+att+'"></li></ul>'
					 	+'</td>'
					 	+'<td class="min-width addminusbut">'
					 	+'<ul class="buttons-group mainbtn action">'
					 	+'<li><a class="main-btn success-btn-outline btn-hover btn-sm addminusbut" '
					 	+'id="downloadbtn' +att+'" title="Download">'
//	 				 	+'onclick="download_file_view('+att+');" id="downloadbtnview' +att+ '" title="Download">'
					 	+'<i class="lni lni-download pdfdown"></i></a>'
					 	+'<input type="hidden" id="file_id'+att+'" name="file_id'+att+'"></li></ul>'
					 	+'	</td>'
					 	+'<td class="custom-d-none"><ul class="buttons-group mainbtn"><li><a class="main-btn info-btn-outline btn-hover btn-sm addminusbut value = "ADD" title = "ADD" id = "id_add_attNameMed'+att+'">'
	                   	+'<i class="lni lni-plus"></i></a></li></ul></td>'
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
	
	function addOnclick(att){
		document.getElementById('downloadbtn'+att).onclick = function() {
			download_file_view(att);
		};
		
		document.getElementById('downloadbtn'+att).onclick = function() {
			download_file(att);
		};
		document.getElementById('id_add_attNameMed'+att).onclick = function() {
			formOpenNameMed(att);
		};
	}
	
	function formOpenHospital(R){
		 
		//if(validateAddMoreHospital(R)){
		 $("#addHospital").show();
			 $("#id_add_attHospital"+R).hide();
			 $("#att_id_removeHospital"+R).hide();
			 att=0;
			 att= parseInt(R)+1;
			 if(att < 51){
				 $("input#count_hidden_att_Hospital").val(att);//current serial No
				 $("table#addHospital").append('<tr align="center" id="tr_id_attHospital'+att+'"><td>'+att+'</td>'
				 	+'<td class="min-width"><p id="place_of_working'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="place_of_working_name'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="adjunct_place'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="landline'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="mobileHosp'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="email'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="hos_address1_'+att+'"></p><span id="hos_address2_'+att+'"></p><span id="hos_address3_'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="hos_state'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="hos_district'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="authority_type'+att+'"></p></td>'
				 	+'<td class="min-width"><p id="name_of_res_p'+att+'"></p></td>'
				 	+'<td class="min-width custom-d-none"><td class="min-width addminusbut custom-d-none"><ul class="buttons-group mainbtn action"><li><a class="main-btn info-btn-outline btn-hover btn-sm addminusbut" value="ADD" title="ADD" id="id_add_attHospital'+att+'"><i class="lni lni-plus"></i></a></li></ul></td></td>'
				 	+'</tr>');
				 addOnclick1(att);
			 }
		//}
	}
	function addOnclick1(att){
		document.getElementById('id_add_attHospital'+att).onclick = function() {
			formOpenHospital(att);
// 			onclick="formOpenHospital('+att+');"
		};
// 		document.getElementById('att_id_remove'+att).onclick = function() {
// 			formopen_re_att(att);
// 		};
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
			 $('#identity_image_preview').attr("src", "MedicalImagePath1?i_id="+idforimg+" ");
			 
		}
		 
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
			var status = "${j[5]}";
			var 	NameOfUniversity1_hid = "${j[6]}"; 
			 
			 if(status != "Not Verified" &&  status !="Reverted"){
				// document.getElementById("cbox1").disabled = true;
				 $("#ck"+i).hide();
				 $("#apprvbt").hide();
				 $("#rejbt").hide();
			 }
			 
			
			//document.getElementById("myCheck").disabled = true;
			
// 			 $("#cbox"+i).val(id);
 			$("#ck"+i).append(" <input class='nrCheckBox' type='checkbox' id='"+id+"'   name='"+id+"'  value='"+id+"' /> ");
			
			$("#v_status"+i).text(status);
	 		if(type_of_degree != null && type_of_degree.trim() != ""){
				$("#typeOfDegree"+i).text(type_of_degree);
 			}else{
				$("#typeOfDegree"+i).text(0);
			}
	 		if(NameOfUniversity1_hid != null && NameOfUniversity1_hid.trim() != ""){
				$("#NameOfUniversity1_hid"+i).text(NameOfUniversity1_hid);
 			}else{
				$("#NameOfUniversity1_hid"+i).text(0);
			}
	 		
	 		
	 		
	 		if(degree_name != null && degree_name.trim() != ""){
				$("#DegreeName"+i).text(degree_name);
			}else{
				$("#DegreeName"+i).text(0);
	
			} 
	 		if(name_of_institute != null && name_of_institute.trim() != ""){
				$("select#NameOfUniversity"+i).val(name_of_institute);
				 $("#NameOfUniversity"+i).attr("disabled","disabled");
			}else{
				$("#NameOfUniversity"+i).text("");
				 $("#NameOfUniversity"+i).attr("disabled","disabled");
			}
 
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
			

// 			$("#dlall_pdf").append(
// 					'<a class="main-btn success-btn-outline btn-hover btn-sm addminusbut"onclick="download_file_view('+k+');" id="downloadbtn'+k+'" title="Download">'
//        				+'<i class="bi bi-file-pdf"></i></a><input type="hidden" id="file_idd'+k+'" name="file_idd'+k+'">'
// 			);
		
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
 
if("${setAddMoreHospCMD.size()}" == 0){
 	$("#pl_wr").hide();
}


 		//ADDMORE HOSPITAL
		if("${setAddMoreHospCMD.size()}" > 0){
			$("#count_hidden_att_Hospital").val("${setAddMoreHospCMD.size()}");
			$("#count_hidden_att_His").val("${setAddMoreHospCMD.size()}");
			var i = 1;
			<c:forEach var="j" items="${setAddMoreHospCMD}" varStatus="num">
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
				 	+'<td class="min-width"><div class="input-style-1"><input type="text" id="NameOfAttachment'+seq+'" name="NameOfAttachment'+seq+'" value="" class="form-control autocomplete" autocomplete="off" onkeypress="AutoCompleteNameOfUniversity(this);" placeholder="Name of Attachment"></div></td>'
				 	/* <input type="text" id="NameOfUniversity'+att+'" name="NameOfUniversity'+att+'" value="" class="form-control autocomplete" autocomplete="off" onkeypress="AutoCompleteNameOfUniversity(this);"></td>' */
				 	+'<td class="min-width"><div class="input-style-1"><input type="file" id="attachmentDocument'+seq+'" name="attachmentDocument'+seq+'" accept=".PDF" onchange="attachmentDetails(this,'+seq+');" class="form-control"><input type="hidden" id="attachmentDoc_hid'+seq+'" name="attachmentDoc_hid'+seq+'" class="form-control"></div></td>' 
				 	/* <input type="file" id="attachment'+att+'" name="attachment'+att+'" accept="image/*" onchange="attachmentDetails(this,'+att+');"></td>'  */
				 	+'<td class="min-width"><div class="action"><ul class="buttons-group mainbtn"><li><a class="main-btn info-btn-outline btn-hover btn-sm addminusbut" value = "ADD" title = "ADD" id = "id_add_attDoc'+seq+'" name = "id_add_attDoc'+seq+'"><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn-outline btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeattDoc'+seq+'"><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
				 	/* <a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_attNameMed'+att+'" onclick="formOpenNameMed('+att+');" ><i class="fa fa-plus"></i></a> <a style="margin-right: 0px;"class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeNameMed'+att+'" onclick="formopen_re_NameMed('+att+');"><i class="fa fa-trash"></a></td>'  */
				 	+'</tr>');
				 addOnclick(R,att,seq);
				 
			 
				}else{
						alert("Please Enter max 50 Quantity");
						 if ( att == 51){
							 att = att-1; 
							 $("#att_id_removeattDoc"+att).show();
						 }	   
				}
		
		}
		
	function addOnclick(R,att,seq){  
		document.getElementById('att_id_removeattDoc'+seq).onclick = function() {
			formopen_re_attDoc(R,att);
		};
		document.getElementById('id_add_attDoc'+seq).onclick = function() {
			formOpenattDoc(R,att);
		};
	}
	
	function validateAddMoreMedical(R){
		var count = $("#count_hidden_att_name_med").val();
		 
			var typedegree = $("#typeOfDegree"+R).val();
			var degreename = $("#DegreeName"+R).val();
			var monthYear = $("#monthYearOfDegree"+R).val();
			var nameUni = $("#NameOfUniversity"+R).val();
 
return true;
	
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
				+'		<td class="min-width">'
				+'			<div class="lead">'

				+'				<div class="lead-text">'
				+'					<p>1</p>'
				+'				</div>'
				+'			</div>'
					+'		</td>'
				
					+'	<td class="min-width">'
 					+'			<div class="input-style-1">'
					+'			<input type="text" id="NameOfAttachment'+seq+'"'
							+'				name="NameOfAttachment'+seq+'" value=""'
							+'				class="form-control autocomplete" autocomplete="off"'
							+'				onkeypress="AutoCompleteNameOfUniversity(this);"'
							+'			placeholder="Name of Attachments">'
							+'	</div> </td>'
							+'	<td class="min-width">'

							+'		<div class="input-style-1">'
							+'			<input type="file" id="attachmentDocument'+seq+'" name="attachmentDocument'+seq+'"'
							+'				accept=".PDF" onchange="attachmentDetails(this,1);"'
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
// 		 addOnclick(R,seq);

		}
  		
	}
	
// 	function  addOnclick(R,seq){
// 		document.getElementById('id_add_attDoc'+seq).onclick = function() {
// 			formOpenattDoc(R,1);
// 		};
// 		document.getElementById('id_add_attDoc'+seq).onclick = function() {
// 			formOpenattDoc(R,att);
// 		};
// 	}
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
	
  
	function download_file(obj) {
 		var val= $("#file_id"+obj).val();
 		
 		$.post('getFilePath?' + key + "=" + value,{id:val},function(k) {
		 
			var file = k.split(",");
			for(var i=0;i<file.length;i++){

			var pdfView="kmlFileDownloadDemo?path="+file[i];
			
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
			 $("#dlall_pdf").append(
						'<a class="main-btn success-btn-outline btn-hover btn-sm addminusbut" id="downloadbtn-view'+p+'" title="Download">'
		   				+'<i class="bi bi-file-pdf"></i></a><input type="hidden" id="fid'+p+'" name="fid'+p+'" value="'+k[p][1]+'">'
				);
			 addOnclick(p);
		 }

	});
	
	var pdf1="attachmentfiledownload?kmlId2="+val;
	PDFView(pdf1);
 
	
	function  addOnclick(p){
		document.getElementById('downloadbtn-view'+p).onclick = function() {
			download_file_view2(p);
			
		};
	}
	
	
 		
}

function download_file_view2(obj) {
	
	var val= $("#fid"+obj).val();
	var pdf1="attachmentfiledownload?kmlId2="+val;
	PDFView(pdf1);
 		
}
 
	
	
// 	===================for view

function PDFView(path1){
	
	if ($("#my_pdf_viewer").hasClass("d-none")) {
		 $( "#my_pdf_viewer" ).removeClass("d-none")
	}
		

		var pdfDoc = null,
	    pageNum = 1,
	    pageRendering = false,
	    pageNumPending = null,
	    scale = 1,
	    canvas = document.getElementById('pdf_renderer'),
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
		
		  // Update page counters
//	 	  document.getElementById('page_num').textContent = num;
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
		
		document.getElementById('go_previous').addEventListener('click', onPrevPage);
		
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
		document.getElementById('go_next').addEventListener('click', onNextPage);
		
		document.getElementById('zoom_in').addEventListener('click', (e) => {
		    if(pdfDoc == null) return;
		    if (scale!= 4) {
		    scale += 0.5;
		   }
		    queueRenderPage(pageNum);
		});
		
		document.getElementById('zoom_out').addEventListener('click', (e) => {
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
//	 	  document.getElementById('page_count'+idx).textContent = pdfDoc.numPages;
		  // Initial/first page rendering
		  renderPage(pageNum);
		});
}



	
	function View_hide() {
		
		   $("#my_pdf_viewer").show();
	}
 
	
	function dynamicTableR(a){
	 
		var R = a.split(",");
 		  
		$("div#dynamicDataTableR").html("");
		
		for(var i = 1 ; i <= R.length ;i ++ ){
			 
			var  seq =   i;
	 			$("#dynamicDataTableR").append("<div id='dynamicDataTableR"+seq+"'></div>");
 			var R = a.split(",");
			 
			var idx = R[i-1]
			var nam = "";
			 
				var type_of_degree = "";
				var degree_name = "";
		$.post("get_degrrename_Reject_id?" + key + "=" + value, {
			id:idx
			}, function(p) {
					nam = p;
			});
			  
			$("div#dynamicDataTableR"+seq).append(
					'<table class="table model-table" id="addAttDoc'+seq+'">'
 
					+'<tbody id="att_TbbodyattDoc'+seq+'">'
					+'	<tr id="tr_id_attDoc'+seq+'">'
					+'		<td class="min-width">'
					+'			<div class="lead">'
	 				+'				<div class="lead-text">'
					+'					<p>'+i+'</p>'
					+'				</div>'
					+'			</div>'
						+'		</td>'
								+'	<td class="min-width">'
	 							+'	<div class="input-style-1">'
	 							+'<label id="name'+R[i-1]+'" name="name'+R[i-1]+'"  value="'+nam+'">'+nam+'</label>'
 								+'	<input type="text" hidden="hidden" id="client'+seq+'" name="client'+seq+'"  value="'+R[(i - 1)]+'"> '
	 							+'</div> '
								+'</td>'
								+'	<td class="min-width">'
	 							+'	<div class="input-style-1">'
								+'	<input type="text" id="remarks'+R[i-1]+'" name="remarks'+R[i-1]+'" placeholder= "Enter Your Remarks"  class="form-control"> '
	 							+'</div> '
								+'</td>'
								 +'</tr> </tbody> </table>' 
				);
	 	 
		}
 
		}

</script>
