<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.js"></script>
<script src="js/miso/commonJS/addmorefunctionality.js"
	type="text/javascript"></script>

<body>
	<!--**********************************
        Main wrapper start
    ***********************************-->

	<section class="search_regulation dashboard-page">
		<div class="container-fluid">
			<!-- title-wrapper start -->
			<div class="title-wrapper pt-30">
				<div class="row align-items-center">
					<div class="col-lg-6 col-md-6 col-sm-12" id="header_print1">
						<div class="title mb-30">
							<h2>View Faculty Details</h2>
						</div>
					</div>
					<!-- end col -->
					<div class="col-lg-6 col-md-6 col-sm-12">
						<div class="breadcrumb-wrapper mb-30">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
									<li class="breadcrumb-item active" aria-current="page">
										View Faculty Details</li>
								</ol>
							</nav>
						</div>
					</div>
				</div>
			</div>
			<!-- row -->
			<div class="form-elements-wrapper preview-form">
				<form
					action="View_tea_dtlAction?${_csrf.parameterName}=${_csrf.token}"
					name="View_teacher_dtl" method='POST'
					modelAttribute="View_tea_dtlCMD" enctype="multipart/form-data">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="card-style mb-30" id="card_view1">
							<ul class="custom-uniqueid d-none" id="t_code">
							<li><p class="custom-id custom-active-badge ">
									<span class="custom-id-title ">Teacher Code:</span><span class="custom-id-value " id="teacher_code">AYU123456789</span>
								</p></li>
							
						</ul>
								<div class="field-box">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="custom-data-value custom-title custom-title-bg">
												<h5 class="custom-title-tag">Personal Details</h5>
											</div>
										</div>

										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>First Name :</label>
												<!-- <span class="value-bind" id="cand_prefix1"></span> -->

												<div
													class="form-group stepform-group stepform-groupadd d-none">
													<select name="cand_prefix" id="cand_prefix"
														class="qualification">
														<option value="1">Mr</option>
														<option value="2">Ms</option>
														<option value="3">Mrs</option>
													</select>
												</div>
												<span class="value-bind" id="first_name"></span> <input
													type="hidden" id="id" name="id" class="form-control"
													value="0" autocomplete="off">
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Middle Name :</label> <span class="value-bind"
													id="middle_name"></span>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Last Name :</label> <span class="value-bind"
													id="last_name"></span>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Gender :</label> <span class="value-bind"
													id="gender1"></span>
												<div class="form-group stepform-group custom-d-none">
													<select name="gender" id="gender" class="form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getgenderList}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Date of Birth :</label> <span class="value-bind"
													id="date_of_birth" name="date_of_birth"></span> <input
													type="hidden" id="date_of_birth" name="date_of_birth"
													class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Father's Name :</label> <span class="value-bind"
													id="father_name"></span>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Mother's Name :</label> <span class="value-bind"
													id="mother_name"></span>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Spouse's Name :</label> <span class="value-bind"
													id="spouse_name"></span>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Mobile No. :</label> <span class="value-bind"
													id="mobile_no"></span>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Email Id :</label> <span class="value-bind"
													id="email"></span>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Aadhaar Card :</label> <span class="value-bind">
													<span id="aadhar_no1"></span> <span id="aadhar_no2"></span>
													<span id="aadhar_no3"></span>
												</span>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>PAN No :</label> <span class="value-bind" id="pan_no"></span>
											</div>
										</div>
									</div>
								</div>
								<div class="field-box">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="custom-data-value custom-title custom-title-bg">
												<h5 class="custom-title-tag">Present Address</h5>
											</div>
										</div>

										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Address Line 1 :</label> <span class="value-bind"
													id="per_add_line1"></span>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Address Line 2 :</label> <span class="value-bind"
													id="per_add_line2"></span>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>State :</label> <span class="value-bind"
													id="per_state1"></span>
												<div class="form-group stepform-group custom-d-none">
													<select name="per_state" id="per_state"
														class="form-control"">
														<option value="0" selected="selected">--Select
															State--</option>
														<c:forEach var="item" items="${getMedStateName}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>District :</label> <span class="value-bind"
													id="per_district1"></span>
												<div class="form-group stepform-group custom-d-none">
													<select name="per_district" id="per_district"
														class="form-control">
														<option value="0">--Select District--</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>City/Village :</label> <span class="value-bind"
													id="per_village"></span>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Pin Code :</label> <span class="value-bind"
													id="per_pincode"></span>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Phone No :</label> <span class="value-bind"
													id="per_phn_no"></span>
											</div>
										</div>

									</div>
								</div>
								<div class="field-box">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="custom-data-value custom-title custom-title-bg">
												<h5 class="custom-title-tag">Correspondence Address</h5>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Address Line 1 :</label> <span class="value-bind"
													id="present_add_line1"></span>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Address Line 2 :</label> <span class="value-bind"
													id="present_add_line2"></span>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>State :</label> <span class="value-bind"
													id="present_state1"></span>
												<div class="form-group stepform-group custom-d-none">
													<select name="present_state" id="present_state"
														class="form-control">
														<option value="0" selected="selected">-- Select
															State --</option>
														<c:forEach var="item" items="${getMedStateName}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>District :</label> <span class="value-bind"
													id="present_district1"></span>
												<div class="form-group stepform-group custom-d-none">
													<select name="present_district" id="present_district"
														class="form-control">
														<option value="0">--Select District--</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>City/Village :</label> <span class="value-bind"
													id="present_village"></span>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Pin Code :</label> <span class="value-bind"
													id="present_pincode"></span>
											</div>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Phone No :</label> <span class="value-bind"
													id="present_phn_no"></span>
											</div>
										</div>
									</div>
								</div>


								<div class="field-box">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="custom-data-value custom-title custom-title-bg">
												<h5 class="custom-title-tag">Medical Qualification</h5>
											</div>
										</div>

										<div class="col-lg-12 col-md-12 col-sm-12 col-12"
											id="quali_Tb">
											<div class="tables-wrapper">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>Name of Exam/Degree</h6></th>
																<th><h6>Course</h6></th>
																<th><h6>Subject</h6></th>
																<th><h6>Name of Institute/College</h6></th>
																<th><h6>Name of Affiliated University</h6></th>
																<th><h6>Month & Year of Passing</h6></th>
																<th><h6>Doc. Attachments</h6></th>
															</tr>
															<!-- end table row-->
														</thead>
														<tbody id="quali_Tbbody">
															<c:forEach var="j" items="${View_expchild_4}"
																varStatus="num">
																<tr id="tr_id_quali">
																	<td><p>${num.index+1}</p></td>
																	<%-- <td><p>${j[0]}</p></td> --%>
																	<td><p>${j[7]}</p></td>
																	<td><p>${j[8]}</p></td>
																	<td><p>${j[9]}</p></td>
																	<td><p>${j[4]}</p></td>
																	<td><p>${j[5]}</p></td>
																	<td><p>${j[6]}</p></td>
																	<td class="min-width ">
																		<ul class="buttons-group mainbtn action">
																			<li class=""><a
																				class="main-btn success-btn-outline btn-hover btn-sm pdfouter"
																				id="qualificationdown${num.index+1}"
																				title="Document Attachments"> <i class="bi bi-file-pdf"></i>
																			</a> <input type="hidden" id="id_hidden${num.index+1}"
																				name="id_hidden${num.index+1}"
																				class="form-control autocomplete" value="${j[0]}">
																				<input type="hidden" id="id_parent_hidden"
																				name="id_parent_hidden"
																				class="form-control autocomplete" value="${j[11]}">
																			</li>
																		</ul>
																	</td>

																	<%-- 																					<td><p>${j[13]}</p></td> --%>
																	<%-- <td class="min-width addminusbut">
													<ul class="buttons-group mainbtn action">
														<li><a class="main-btn success-btn-outline btn-hover btn-sm addminusbut"
															onclick="getDownloadPdfUploadFile(${j[6]});" title="Downlaod">
															<i class="bi bi-file-pdf"></i></a>
														</li>
													</ul>
												</td> --%>
																</tr>
															</c:forEach>
															<tr id="tr_id_quali">
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="field-box" id="oth_quali">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="custom-data-value custom-title custom-title-bg">
												<h5 class="custom-title-tag">Other Qualification</h5>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="tables-wrapper">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="otherquali_Tb">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>Name of Exam/Degree</h6></th>
																<th><h6>Subject</h6></th>
																<th><h6>Name of University/Institute</h6></th>
																<th><h6>Name of affiliated University</h6></th>
																<th><h6>Year of Passing</h6></th>
																<th><h6>Attachment</h6></th>
															</tr>

														</thead>
														<tbody id="otherquali_Tbbody">

															<c:forEach var="j" items="${View_otherexpchild_3}"
																varStatus="num">

																<%-- 											 	<c:if test="${j != 0}"> --%>
																<tr id="tr_id_otherquali">
																	<td><p>${num.index+1}</p></td>
																	<%-- <td><p>${j[0]}</p></td> --%>
																	<td><p>${j[1]}</p></td>
																	<td><p>${j[2]}</p></td>
																	<td><p>${j[3]}</p></td>
																	<td><p>${j[4]}</p></td>
																	<td><p>${j[5]}</p></td>
																	<td class="min-width ">
																		<ul class="buttons-group mainbtn action">
																			<li class=""><a
																				class="main-btn success-btn-outline btn-hover btn-sm pdfouterother"
																				id="otherqualificationdown${num.index+1}"
																				title="Downlaod"> <i class="bi bi-file-pdf"></i></a>
																				<input type="hidden" id="id_hiddenoth${num.index+1}"
																				name="id_hiddenoth${num.index+1}"
																				class="form-control autocomplete" value="${j[0]}">

																			</li>
																		</ul>
																	</td>
																</tr>
																<%-- 												</c:if> --%>
															</c:forEach>
															<tr id="tr_id_otherquali">
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="field-box">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="custom-data-value custom-title custom-title-bg">
												<h5 class="custom-title-tag">Registration Details</h5>
											</div>
										</div>

										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
											<div class="custom-data-value">
												<label>Registration Type :</label> <span class="value-bind"
													id="registration_type1"></span>
												<div class="form-group stepform-group custom-d-none">
													<select name="registration_type" id="registration_type"
														class="form-control">
														<option value="0">--Select--</option>
														<option value="1">State Registration</option>
														<option value="2">Direct Registration</option>
													</select>
												</div>
											</div>
										</div>
										<div id="sr" class="row">
											<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
												<div class="custom-data-value">
													<label>State Registration No. :</label> <span
														class="value-bind" id="state_reg_no"></span>
												</div>
											</div>
											<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
												<div class="custom-data-value">
													<label>Name of the State Board :</label> <span
														class="value-bind" id="state_board_name"></span>
												</div>
											</div>
											<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
												<div class="custom-data-value">
													<label>Date of Registration :</label> <span
														class="value-bind" id="date_of_reg" name="date_of_reg"></span>
													<input type="hidden" id="date_of_reg" name="date_of_reg"
														class="form-control autocomplete" autocomplete="off">
												</div>
											</div>
											<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
												<div class="custom-data-value">
													<label>Central Registration No. :</label> <span
														class="value-bind" id="central_reg_no"></span>
												</div>
											</div>
											<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
												<div class="custom-data-value">
													<label>Adjunct Registration No. :</label> <span
														class="value-bind" id="adjunct_registration_no"></span>
												</div>
											</div>
											<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
												<div class="custom-data-value">
													<label>Adjunct State Name :</label> <span
														class="value-bind" id="adjunct_state_no"></span>
												</div>
											</div>
											<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
												<div class="custom-data-value">
													<label>Validity Upto :</label> <span class="value-bind"
														id="state_validity_upto" name="state_validity_upto"></span>
													<input type="hidden" id="state_validity_upto"
														name="state_validity_upto"
														class="form-control autocomplete" autocomplete="off">
												</div>
											</div>
										</div>
										<div id="dr" class="row">
											<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
												<div class="custom-data-value">
													<label>Direct Registration No. :</label> <span
														class="value-bind" id="direct_reg_no"></span>
												</div>
											</div>
											<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
												<div class="custom-data-value">
													<label>Date of Registration :</label> <span
														class="value-bind" id="direct_date_of_reg"
														name="direct_date_of_reg"></span> <input type="hidden"
														id="direct_date_of_reg" name="direct_date_of_reg"
														class="form-control autocomplete" autocomplete="off">
												</div>
											</div>
											<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
												<div class="custom-data-value">
													<label>Name of Department/Organization :</label> <span
														class="value-bind" id="name_of_department"></span>
												</div>
											</div>
											<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
												<div class="custom-data-value">
													<label>Validity Upto :</label> <span class="value-bind"
														id="direct_validity_upto" name="direct_validity_upto"></span>
													<input type="hidden" id="direct_validity_upto"
														name="direct_validity_upto"
														class="form-control autocomplete" autocomplete="off">
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="field-box">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="custom-data-value custom-title custom-title-bg">
												<h5 class="custom-title-tag">Academic Experience</h5>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="tables-wrapper">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="att_Tb">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>Organization Name</h6></th>
																<th><h6>Department Name</h6></th>
																<th><h6>Designation</h6></th>
																<th><h6>Employment Type</h6></th>
																<th><h6>From Date</h6></th>
																<th><h6>To Date</h6></th>
																<th><h6>Year of Experience</h6></th>
																<th><h6>Honorarium</h6></th>
																<th><h6>Upload File</h6></th>
																<!-- <th><h6>Action</h6></th> -->
															</tr>
															<!-- end table row-->
														</thead>
														<tbody id="att_Tbbody">
															<c:forEach var="j" items="${View_otherexpchild_4}"
																varStatus="num">
																<tr id="tr_id_att">
																	<td><p>${num.index+1}</p></td>
																	<td><p>${j[0]}</p></td>
																	<td><p>${j[1]}</p></td>
																	<td><p>${j[2]}</p></td>
																	<td><p>${j[8]}</p></td>
																	<td><p>${j[3]}</p></td>
																	<td><p>${j[4]}</p></td>
																	<td><p>${j[7]}</p></td>
																	<td><p>${j[9]}</p></td>
																	<td class="addminusbut">
																		<ul class="buttons-group mainbtn action">
																			<li><a
																				class="main-btn success-btn-outline btn-hover btn-sm addminusbut"
																				id="experience" title="Downlaod"> <i
																					class="bi bi-file-pdf"></i></a></li>
																		</ul>
																	</td>
																</tr>
															</c:forEach>
															<tr id="tr_id_att">
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								
								
								
								<div class="field-box">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="custom-data-value custom-title custom-title-bg">
												<h5 class="custom-title-tag">Profession Experience</h5>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="tables-wrapper">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="att_Tb">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>Organization Name</h6></th>
																<th><h6>From Date</h6></th>
																<th><h6>To Date</h6></th>
																<th><h6>Year of Experience</h6></th>
																<th><h6>Designation</h6></th>
																<th><h6>Type Of Experience</h6></th>
<!-- 																<th><h6>Upload File</h6></th> -->
																<!-- <th><h6>Action</h6></th> -->
															</tr>
															<!-- end table row-->
														</thead>
														<tbody id="att_Tbbody">
															<c:forEach var="j" items="${Type_of_exp_acadView}"
																varStatus="num">
																<tr id="tr_id_att">
																	<td><p>${num.index+1}</p></td>
																	<td><p>${j[3]}</p></td>
																	<td><p>${j[4]}</p></td>
																	<td><p>${j[5]}</p></td>
																	<td><p>${j[7]}</p></td>
																	<td><p>${j[6]}</p></td>
																	<td><p>${j[1]}</p></td>
																	
<%-- 																	<td><p>${j[9]}</p></td> --%>
<!-- 																	<td class="addminusbut"> -->
<!-- 																		<ul class="buttons-group mainbtn action"> -->
<!-- 																			<li><a -->
<!-- 																				class="main-btn success-btn-outline btn-hover btn-sm addminusbut" -->
<!-- 																				id="experience" title="Downlaod"> <i -->
<!-- 																					class="bi bi-file-pdf"></i></a></li> -->
<!-- 																		</ul> -->
<!-- 																	</td> -->
																</tr>
															</c:forEach>
															<tr id="tr_id_att">
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								


								<!-- <h4>Degree and Supporting Document</h4> -->
								<div class="field-box">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="custom-data-value custom-title custom-title-bg">
												<h5 class="custom-title-tag">Degree and Supporting
													Document</h5>
											</div>
										</div>

										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="tables-wrapper">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="doc_Tb">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>Document Name</h6></th>
																<th><h6>Document Type</h6></th>
																<th><h6>Upload</h6></th>
																<!-- <th><h6>Action</h6></th> -->
															</tr>
															<!-- end table row-->
														</thead>
														<tbody id="doc_Tbbody">
															<c:forEach var="j" items="${View_expchild_2}"
																varStatus="num">
																<tr id="tr_id_doc">
																	<td><p>${num.index+1}</p></td>
																	<td><p>${j[0]}</p></td>
																	<td><p>${j[1]}</p></td>
																	<td class="addminusbut">
																		<ul class="buttons-group mainbtn action">
																			<li><a
																				class="main-btn success-btn-outline btn-hover btn-sm addminusbut"
																				id="degree1" title="Downlaod"> <i
																					class="bi bi-file-pdf"></i></a></li>
																		</ul>
																	</td>
																</tr>
															</c:forEach>
															<tr id="tr_id_doc">
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>

								</div>


<!-- 								<div class="field-box"> -->
<!-- 									<div class="row"> -->
<!-- 										<div class="col-lg-12 col-md-12 col-sm-12 col-12"> -->
<!-- 											<div class="custom-data-value custom-title custom-title-bg"> -->
<!-- 												<h5 class="custom-title-tag">Confirmation</h5> -->
<!-- 											</div> -->
<!-- 										</div> -->

<!-- 										<div class="col-lg-12 col-md-12 col-sm-12 col-12"> -->
<!-- 											<div class="custom-choose-one"> -->
<!-- 												<div class="input-style-form-check_block check-multi-list"> -->
<!-- 																										<div class="form-check checkbox-style"> -->
<!-- 													<label class="check-list">I hereby confirm that the -->
<!-- 														information provided herein is accurate, correct and -->
<!-- 														complete and that the documents submitted along with this -->
<!-- 														application form are genuine. I understand and agree that -->
<!-- 														this declaration is final and irrevocable, and that it is -->
<!-- 														not subject to cancellation .</label> -->
<!-- 																										</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
									
<!-- 								</div> -->
								
								<input type="hidden" id="count_hidden_att"
										name="count_hidden_att" class="form-control autocomplete"
										value="1"> <input type="hidden" id="count_hidden_doc"
										name="count_hidden_doc" class="form-control autocomplete"
										value="1"> <input type="hidden"
										id="count_hidden_quali" name="count_hidden_quali"
										class="form-control autocomplete" value="1">

								<!-- Bottom Button Start -->
								<div class="btn-bottom">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12">
											<ul class="buttons-group mainbtn">
												<li><a href="commonDashboard"
													class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
														<i class="lni lni-chevron-left" id="backId"></i>Back
												</a></li>
												<li class="custom-d-none"><a href="#0"
													class="main-btn success-btn-outline btn-hover btn-iconic-icon btnprint"
													id="printc"> <i id="printId" value="PDF"
														class="lni lni-printer" title="Export to PDF"></i>Print
												</a></li>
												<li><a href="#0" class="main-btn success-btn-outline btn-hover btn-iconic-icon btnprint" id="prints" ><i class="lni lni-printer"></i>Print</a></li>
													
												<li id="Approve_princi" class="d-none"><input
													type="button"
													class="main-btn success-btn btn-hover btnappr"
													value="Approve">
												<li id="Reject_princi" class="d-none"><input
													type="button"
													class="main-btn danger-btn btn-hover btnreject"
													value="Reject"> <input type="hidden"
													name="logo_path" id="logo_path" value="" /></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		
		
		<div class="modal fade custom-modal bd-example-modal-lg" tabindex="-1"
		role="dialog" aria-labelledby="myLargeModalLabel" id="modelWindow"
		aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="myLargeModalLabel">Document
						Attachment</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body custom-modal-table">
					<div class="custom-modal-inner">
						<div class="table-wrapper table-responsive custom-table">
							<table class="table model-table" id="Students_MarksheetUrl">
								<thead>
									<tr>
										<th>Sr No</th>
										<th>Name Of Attachment</th>
										<th>Certificate Type</th>
										<th>Attachment</th>

										<!-- 									<th>Total Marks</th> -->
									</tr>
								</thead>
								<tbody id="att_TbbodyNameMed">
								</tbody>
							</table>
						</div>
					</div>
					</div>
					<div class="modal-footer">
						<ul class="buttons-group">
							<li><button type="button"
									class="main-btn dark-btn btn-hover" data-bs-dismiss="modal"
									data-dismiss="modal" aria-label="Close">Close</button></li>
						</ul>
					</div>
				
			</div>
		</div>
	</div>


	<div class="modal fade custom-modal bd-example-modal-lg" tabindex="-1"
		role="dialog" aria-labelledby="myLargeModalLabel"
		id="modelWindowother" aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="myLargeModalLabel">Document
						Attachment</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body custom-modal-table">
					<div class="custom-modal-inner">
						<div class="table-wrapper table-responsive custom-table">
							<table class="table model-table" id="Students_MarksheetUrl">
								<thead>
									<tr>
										<th>Sr No</th>
										<th>Name Of Attachment</th>

										<th>Attachment</th>

										
									</tr>
								</thead>
								<tbody id="att_TbbodyNameMedother">
								</tbody>
							</table>
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
	</section>


	



	<c:url value="getDownloadUrlforexp_child" var="downloadUrl" />
	<form:form action="${downloadUrl}" method="post" id="search1"
		name="search1" modelAttribute="upload_file2">
		<input type="hidden" name="pageUrl" id="pageUrl"
			value="redirect:Search_teacher_detail_url" />
		<input type="hidden" name="upload_file2" id="upload_file2" value="" />
	</form:form>

	<c:url value="getDownloadUrlforDoc" var="downloadUrl" />
	<form:form action="${downloadUrl}" method="post" id="search2"
		name="search2" modelAttribute="upload_doc2">
		<input type="hidden" name="pageUrl" id="pageUrl"
			value="redirect:Search_teacher_detail_url" />
		<input type="hidden" name="upload_doc2" id="upload_doc2" value="" />
	</form:form>

	<c:url value="getDownloadUrlforDocAttsub" var="downloadUrl" />
	<form:form action="${downloadUrl}" method="post" id="search3"
		name="search3" modelAttribute="upload_docattsub">
		<input type="hidden" name="pageUrl" id="pageUrl"
			value="redirect:Search_teacher_detail_url" />
		<input type="hidden" name="upload_docattsub" id="upload_docattsub"
			value="" />
	</form:form>

	<c:url value="getDownloadUrlforDocAttsubother" var="downloadUrl" />
	<form:form action="${downloadUrl}" method="post" id="search4"
		name="search4" modelAttribute="upload_docattsubother">
		<input type="hidden" name="pageUrl" id="pageUrl"
			value="redirect:Search_teacher_detail_url" />
		<input type="hidden" name="upload_docattsubother"
			id="upload_docattsubother" value="" />
	</form:form>

	<!--**********************************tushar_11_07_22***********************************-->
	<c:url value="getfacultyList" var="getfacultyList" />
	<form:form action="${getfacultyList}" method="post" id="downloadForm"
		name="downloadForm" modelAttribute="doid1">
		<input type="hidden" name="typeReport" id="typeReport" value="0" />
		<input type="hidden" name="emp_id3" id="emp_id3" value="0" />
	</form:form>



	<c:url value="faculty_principal_Approve_url"
		var="faculty_principal_Approve_url" />
	<form:form action="${faculty_principal_Approve_url}" method="post"
		id="AcceptFaculty" name="AcceptFaculty" modelAttribute="Acceptid">
		<input type="hidden" name="Acceptid" id="Acceptid" value="0" />
	</form:form>

	<c:url value="faculty_principal_Reject_url"
		var="faculty_principal_Reject_url" />
	<form:form action="${faculty_principal_Reject_url}" method="post"
		id="RejectFaculty" name="RejectFaculty" modelAttribute="Rejecttid">
		<input type="hidden" name="Rejectid" id="Rejectid" value="0" />
	</form:form>


	<!--**********************************
        Main wrapper end
    ***********************************-->



	<!-- <script type="text/javascript" src="js/stepform/common.min.js" ></script> -->
	<!--  <script type="text/javascript" src="js/stepform/custom.min.js"></script> -->
	<!--  <script type="text/javascript" src="js/stepform/jquery.validate.min.js"></script> -->
	<!--   <script type="text/javascript" src="js/stepform/jquery.steps.min.js"></script> -->
	<!--  <script type="text/javascript" src="js/stepform/jquery-steps-init.js"></script> -->

	<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
	<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
	<script src="js/Calender/jquery-ui.js"></script>
	<script src="js/Calender/datePicketValidation.js"></script>

</body>
<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		
		//alert('${View_otherexpchild_3}.size');
		
// 		alert('${View_expchild_2}');
		
// 		if('${statusview}'!= ""){
// 			app_status='${statusview}';
// 		}

 $.ajaxSetup({
			async : false
		});
 
 
 
				 if('${role}'=="Faculty_NCH" || '${role}'=="Faculty_NCISM") {
						
					 $("#prints").show();
				 }else{
					 $("#prints").hide();
					 
					
				}



				$("select#cand_prefix").val(${View_tea_dtlCMD.cand_prefix});
				 var service = $('select#cand_prefix').find(":selected").text();
				$("#cand_prefix1").text(service);
				
				$("#first_name").text('${View_tea_dtlCMD.first_name}');
				$("#middle_name").text('${View_tea_dtlCMD.middle_name}');
				$("#last_name").text('${View_tea_dtlCMD.last_name}');
				
				$("select#gender").val(${View_tea_dtlCMD.gender});
				 var service = $('select#gender').find(":selected").text();
				$("#gender1").text(service);
				
				$("#id").text('${View_tea_dtlCMD.id}');
				
				var date_of_birth = '${View_tea_dtlCMD.date_of_birth}';
		        var dob= date_of_birth.substring(0,10);
		        var dob_y = dob.substring(0,4);
		        var dob_m = dob.substring(5,7);
		        var dob_d = dob.substring(8,10);
		         var dob_t = dob_d+"/"+dob_m+"/"+dob_y;
		        $("#date_of_birth").text(dob_t);

				$("#father_name").text('${View_tea_dtlCMD.father_name}');
				$("#mother_name").text('${View_tea_dtlCMD.mother_name}');
				$("#spouse_name").text('${View_tea_dtlCMD.spouse_name}');
				$("#mobile_no").text('${View_tea_dtlCMD.mobile_no}');
				
			//	var email=$('#email').toLowerCase();
				$("#email").text('${View_tea_dtlCMD.email}');
				$("#email").css("text-transform", "lowercase");
				$("#teacher_code").text('${View_tea_dtlCMD.teacher_code}');
				
				var adhar = '${View_tea_dtlCMD.aadhar_no}';
				var a1 = adhar.substring(0,4);
	 			var a2 = adhar.substring(4,8);
	 			var a3 = adhar.substring(8,12);
	 			$("#aadhar_no1").text(a1);
	 			$("#aadhar_no2").text(a2);
	 			$("#aadhar_no3").text(a3);
	 		//	 $("#adhar_no").text(isAadharAppend('${listp[0].adhar_card_no}'));
				
				$("#pan_no").text('${View_tea_dtlCMD.pan_no}');
				$("#per_add_line1").text('${View_tea_dtlCMD.per_add_line1}');
				$("#per_add_line2").text('${View_tea_dtlCMD.per_add_line2}');
				$("#per_village").text('${View_tea_dtlCMD.per_village}');
				
				$("select#per_state").val(${View_tea_dtlCMD.per_state});
				 var service = $('select#per_state').find(":selected").text();
				$("#per_state1").text(service);
				//$("#per_state").change();
				getDistrict();
				
				$("select#per_district").val(${View_tea_dtlCMD.per_district});
				 var service = $('select#per_district').find(":selected").text();
				$("#per_district1").text(service);
				$("#per_district").change();
				
				$("#per_pincode").text('${View_tea_dtlCMD.per_pincode}');
				$("#per_phn_no").text('${View_tea_dtlCMD.per_phn_no}');
				
				 if("${View_tea_dtlCMD.present_add_line1}" == "${View_tea_dtlCMD.per_add_line1}" && "${View_tea_dtlCMD.present_add_line2}" == "${View_tea_dtlCMD.per_add_line2}" && "${View_tea_dtlCMD.present_village}" == "${View_tea_dtlCMD.per_village}"
						&& "${View_tea_dtlCMD.per_state}" == "${View_tea_dtlCMD.present_state}" && "${View_tea_dtlCMD.per_district}" == "${View_tea_dtlCMD.present_district}" 
						&& "${View_tea_dtlCMD.per_pincode}" == "${View_tea_dtlCMD.present_pincode}" && "${View_tea_dtlCMD.per_phn_no}" == "${View_tea_dtlCMD.present_phn_no}"){
				 }
				
				$("#present_add_line1").text('${View_tea_dtlCMD.present_add_line1}');
				$("#present_add_line1").attr('readonly',true);
				$("#present_add_line2").text('${View_tea_dtlCMD.present_add_line2}');
				$("#present_add_line2").attr('readonly',true);
				$("#present_village").text('${View_tea_dtlCMD.present_village}');
				$("#present_village").attr('readonly',true);
				
				$("select#present_state").val(${View_tea_dtlCMD.present_state});
				 var service = $('select#present_state').find(":selected").text();
				$("#present_state1").text(service);
				$("#present_state").attr('readonly',true);
				//$("#present_state").change();
				getDistrict1();
				
				$("select#present_district").val('${View_tea_dtlCMD.present_district}');
				 var service = $('select#present_district').find(":selected").text();
				$("#present_district1").text(service);
				$("#present_district").attr('readonly',true);
				$("#present_district").change();
				
				$("#present_pincode").text('${View_tea_dtlCMD.present_pincode}');
				$("#present_pincode").attr('readonly',true);
				$("#present_phn_no").text('${View_tea_dtlCMD.present_phn_no}');
				$("#present_phn_no").attr('readonly',true);
				
				$("select#registration_type").val(${View_tea_dtlCMD.registration_type});
				 var service = $('select#registration_type').find(":selected").text();
				$("#registration_type1").text(service);
				
				//$("#registration_type").val('${View_tea_dtlCMD.registration_type}');
				if('${View_tea_dtlCMD.registration_type}'== 1 ){
					$("#state_reg_no").text('${View_tea_dtlCMD.state_reg_no}');
					
					var state_board_name = "${getregView[0][0]}";
					$("#state_board_name").text(state_board_name);
					//$("#state_board_name").text('${View_tea_dtlCMD.state_board_name}');
					
					var date_of_reg = '${View_tea_dtlCMD.date_of_reg}';
			        var dob= date_of_reg.substring(0,10);
			        var dob_y = dob.substring(0,4);
			        var dob_m = dob.substring(5,7);
			        var dob_d = dob.substring(8,10);
			         var dob_t = dob_d+"/"+dob_m+"/"+dob_y;
			        $("#date_of_reg").text(dob_t);
			        debugger;
			        
			        var creg= '${View_tea_dtlCMD.central_reg_no}';
			        
			        if(creg =="" ){
			        	$("#central_reg_no").text('-');
			        }else{
			        $("#central_reg_no").text('${View_tea_dtlCMD.central_reg_no}');
			        }
			        
			        var adjunct= '${View_tea_dtlCMD.adjunct_registration_no}';
			        
			        if(adjunct =="" ){
			        	$("#adjunct_registration_no").text('-');
			        }else{
			        $("#adjunct_registration_no").text('${View_tea_dtlCMD.adjunct_registration_no}');
			        }
			        
			        $("#adjunct_state_no").text('${View_tea_dtlCMD.adjunct_state_no}');
			        
			        var state_validity_upto = '${View_tea_dtlCMD.state_validity_upto}';
			        var dob= state_validity_upto.substring(0,10);
			        var dob_y = dob.substring(0,4);
			        var dob_m = dob.substring(5,7);
			        var dob_d = dob.substring(8,10);
			         var dob_state = dob_d+"/"+dob_m+"/"+dob_y;
			        $("#state_validity_upto").text(dob_state);
			        
						 $("div#sr").show();
// 							$("div#dr").hide();
					 }
					 else{
						 $("div#sr").hide();
// 							$("div#dr").hide();
					 }
				
				$("select#registration_type").val(${View_tea_dtlCMD.registration_type});
				 var service = $('select#registration_type').find(":selected").text();
				$("#registration_type1").text(service);
				
				if('${View_tea_dtlCMD.registration_type}'== 2 ){
					$("#direct_reg_no").text('${View_tea_dtlCMD.direct_reg_no}');
					
					var direct_date_of_reg = '${View_tea_dtlCMD.direct_date_of_reg}';
			        var dob= direct_date_of_reg.substring(0,10);
			        var dob_y = dob.substring(0,4);
			        var dob_m = dob.substring(5,7);
			        var dob_d = dob.substring(8,10);
			         var dob_d = dob_d+"/"+dob_m+"/"+dob_y;
			        $("#direct_date_of_reg").text(dob_d);
			        
			        $("#name_of_department").text('${View_tea_dtlCMD.name_of_department}');
			        
			        var direct_validity_upto = '${View_tea_dtlCMD.direct_validity_upto}';
			        var dob= direct_validity_upto.substring(0,10);
			        var dob_y = dob.substring(0,4);
			        var dob_m = dob.substring(5,7);
			        var dob_d = dob.substring(8,10);
			         var dob_direct = dob_d+"/"+dob_m+"/"+dob_y;
			        $("#direct_validity_upto").text(dob_direct);
// 			        $("div#sr").hide();
					$("div#dr").show();
					 }
					 else{
// 						 $("div#sr").hide();
							$("div#dr").hide();
					 }
				
				$("#cand_prefix").text('${View_tea_dtlCMD.cand_prefix}');
				
				$("select#academic_quali").val(${View_tea_dtlCMD.academic_quali});
				 var service = $('select#academic_quali').find(":selected").text();
				$("#academic_quali1").text(service);
				
				 if('${View_tea_dtlCMD.academic_quali}'== 2 ){
						$("#subject").text('${View_tea_dtlCMD.subject}');
							 $("#md_detl").show();
						 }
						 else{
							 $("#md_detl").hide();
						 }
				 FnMd_dtl();
				 
				 $("select#academic_quali").val(${View_tea_dtlCMD.academic_quali});
				 var service = $('select#academic_quali').find(":selected").text();
				$("#academic_quali1").text(service);
				
					if('${View_tea_dtlCMD.academic_quali}'== 1 ){
						
						$("select#sub_quali_degree").val(${View_tea_dtlCMD.sub_quali_degree});
						var service = $('select#sub_quali_degree').find(":selected").text();
						$("#sub_quali_degree1").text(service);
						
						$("select#sub_deg_course").val(${View_tea_dtlCMD.sub_deg_course});
						var service = $('select#sub_deg_course').find(":selected").text();
						$("#sub_deg_course1").text(service);
						
							 $("#hideshw_Degree").show();
						 }
						 else{
							 $("#hideshw_Degree").hide();
						 }
					hideshw_Degree();
					
					//var data =  '${View_tea_dtlCMD}';
					debugger;
					var data='${data}';
					
					if('${data[0].principal_status}'== 1){
						
							$("#t_code").removeClass("d-none")
					
						
						
					}
					
				//addmoredatafetch1();
				//addmoredatafetch2();
				
// 				datepicketDate('date_of_birth');
// 				datepicketDate('date_of_reg');
				
// 				datepicketDate('from_date1');
// 				datepicketDate('to_date1');
				
				setTimeout(setTimeLoadForTable, 1000);
				
				
				
	if(${View_otherexpchild_3.size()} == 0){
					
					$("div#oth_quali").hide();
				}

			});
	
	
	
	
	

	document.querySelectorAll('.pdfouter').forEach((items, index) => {
		
		var val=parseInt(index)+1;
		items.addEventListener('click', event => {
			
		
			
			var sid = document.getElementById('qualificationdown'+val).value;
			//console.log(index,val,sid)
			Pop_Up_History1(val);

		})
	});
	
	
	document.querySelectorAll('.pdfouterother').forEach((items, index) => {
		
		var val=parseInt(index)+1;
		items.addEventListener('click', event => {
			
		
			
			var sid = document.getElementById('otherqualificationdown'+val).value;
			//console.log(index,val,sid)
			Pop_Up_Historyother(val);

		})
	});
	
	
	
		function setTimeLoadForTable() {
			

// 			per_state
// 			onchange="changeAddress(); getDistrict();
			document.getElementById('per_state').onchange = function() {
				changeAddress(); getDistrict();
			};
			//per_district
			// onchange="changeAddress()"
			document.getElementById('per_district1').onchange = function() {
				changeAddress();
			};
			//check_address
			//onclick="copy_address()"
// 			document.getElementById('check_address').onclick = function() {
// 				copy_address();
// 			};
			//present_state
			//onchange="changeAddress(); getDistrict1();"
			document.getElementById('present_state').onchange = function() {
				changeAddress();getDistrict1();
			};
			//present_district1
			//onchange="changeAddress()"
			document.getElementById('present_district').onchange = function() {
				changeAddress();
				//registration_type
				//onchange="hideshw_Registration();"
			};
			document.getElementById('registration_type').onchange = function() {
				hideshw_Registration();
				}
			//onclick="getDownloadPdfDoc(${j[3]});"
			document.getElementById('degree1').onclick = function() {
				getDownloadPdfDoc('${View_expchild_2[0][3]}');
				}
			//experience
			//onclick="getDownloadPdfUploadFile(${j[6]});"
			document.getElementById('experience').onclick = function() {
				getDownloadPdfUploadFile('${View_expchild[0][6]}');
				}
			
			//printc
			
			document.getElementById('printc').onclick = function() {
				printDiv();
				}
			//prints
			//onclick="DownloadData('${View_tea_dtlCMD.id}');"
			
			document.getElementById('prints').onclick = function() {
				DownloadData('${View_tea_dtlCMD.id}');
				}
			
			
			document.getElementById('Approve_princi').onclick = function() {
				return Accepturl();
				}
			
			document.getElementById('Reject_princi').onclick = function() {
				return Rejecturl();
				}

	
		}

</script>

<script nonce="${cspNonce}" type="text/javascript">

function GetTypeFromDoc(obj) {
	
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

function getDistrict() {
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

 function getDistrict1() {
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
	
	_mobile = obj.value;
	
	var regExp =/^[6789]\d{9}$/;
    if(_mobile == '' || !regExp.test(_mobile)){
        alert('Please Enter Number Start with 6789 Digit');
        $('#'+obj.id).focus();
        $('#'+obj.id).val('');
        return false;
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
		$("#present_state").attr('readonly',true);
		
		$("#present_district").val($("#per_district").val());
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
		
		$("#present_state1").val("0");
		$('#present_state1').attr('readonly', false);
		
		$("#present_district1").val("0");
		$('#present_district1').attr('readonly', false);
		
		$("#present_pincode").val("");
		$('#present_pincode').attr('readonly', false);
		
 		$("#present_phn_no").val("");
		$('#present_phn_no').attr('readonly', false);
		 
	}
}

function getDownloadPdfUploadFile(a){  
 
	$("#upload_file2").val(a); 
	document.getElementById("search1").submit();
} 

function getDownloadPdfDoc(a){  
	 
	$("#upload_doc2").val(a); 
	document.getElementById("search2").submit();
}

function getDownloadPdfattsub(x){
	
	
	$("#upload_docattsub").val($("#qualiinbtnhid"+x).val()); 
	document.getElementById("search3").submit();
} 

function getDownloadPdfattsubother(x){
	
	
	$("#upload_docattsubother").val($("#qualiinbtnhidother"+x).val()); 
	document.getElementById("search4").submit();
} 




//teacher-pf ------Tushar_08_07_22
function DownloadData(id) {	

	$("#emp_id3").val(id);		
	document.getElementById('typeReport').value='pdfL';
	document.getElementById('downloadForm').submit();
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
		$("div#Type_of_degree").show();
		$("div#sub_degree").show();
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
function printDiv(){
	
	var divName = 'card_view1'
	var printContents = document.getElementById(divName).innerHTML;
	var originalContents = document.body.innerHTML;
	

// 	$(".body").css("display", "block");
   $('section').attr('style','');
	document.body.innerHTML = document.getElementById(divName).innerHTML;
	$("#header_print1").show();
	
	window.print();
	
	
	document.body.innerHTML = originalContents;
	window.location.reload();

}

function Pop_Up_History1(index) {
	

	var userid = '${userId}' 
	var parent_id=$("#id_hidden"+index).val();
	
//		alert(userid    +","+ course_name +","+ setname);
	
	$('tbody#att_TbbodyNameMed').empty();
		$
				.post( 
						"quali_attachment?" + key
								+ "=" + value,
						{
							userid : userid,
							parent_id : parent_id
							
						},
						function(j) {
								//alert(j)
							for (var i = 0; i < j.length; i++) {
								var ser = parseInt(i) + 1;
								
								$("tbody#att_TbbodyNameMed")
										.append(
												'<tr id="tr_id_attNameMed'+ser+'"><td><p>'
														+ ser
														+ '</p></td>'
														+ '<td><p>'
														+ j[i][2]
														+ '</p></td>'
														+ '<td><p>'
														+ j[i][4]
														+ '</p></td>'
														+ '<td>'
														+' <ul class="buttons-group mainbtn action">'
														+'<li><a class="main-btn success-btn-outline btn-hover btn-sm addminusbut" value='+j[i][0]+' id="qualiinbtn'+ser+'" title="Downlaod" >'
														+'	<i class="bi bi-file-pdf"></i><input type="hidden" value='+j[i][0]+' id="qualiinbtnhid'+ser+'"></a>'
														+'</li>'
														+'</ul>'
														+ '</td>'
														+ '</tr>');
																
								document.getElementById('qualiinbtn'+ser).onclick = function() {
									getDownloadPdfattsub(ser);
									}
								
								
							}
						});
		
		  $('#modelWindow').modal('show');
	}


function Pop_Up_Historyother(index) {
	

	var userid = '${userId}' 
	var parent_id=$("#id_hiddenoth"+index).val();
	
	
	//	alert(parent_id);
//		alert(userid    +","+ course_name +","+ setname);
	
	$('tbody#att_TbbodyNameMedother').empty();
		$
				.post( 
						"quali_attachmentother?" + key
								+ "=" + value,
						{
							userid : userid,
							parent_id : parent_id
							
						},
						function(j) {
								//alert(j)
							for (var i = 0; i < j.length; i++) {
								var ser = parseInt(i) + 1;
								
								$("tbody#att_TbbodyNameMedother")
										.append(
												'<tr id="tr_id_attNameMed'+ser+'"><td><p>'
														+ ser
														+ '</p></td>'
														+ '<td><p>'
														+ j[i][2]
														+ '</p></td>'
// 														+ '<td>'
// 														+ j[i][4]
// 														+ '</td>'
														+ '<td>'
														+' <ul class="buttons-group mainbtn action">'
														+'<li><a class="main-btn success-btn-outline btn-hover btn-sm addminusbut" value='+j[i][0]+' id="qualiinbtnother'+ser+'" title="Downlaod" >'
														+'	<i class="bi bi-file-pdf"></i><input type="hidden" value='+j[i][0]+' id="qualiinbtnhidother'+ser+'"></a>'
														+'</li>'
														+'</ul>'
														+ '</td>'
														+ '</tr>');
																
								document.getElementById('qualiinbtnother'+ser).onclick = function() {
									getDownloadPdfattsubother(ser);
									}
								
								
							}
						});
		
		  $('#modelWindowother').modal('show');
	}

function Accepturl() {
	var p_id=$("#id_parent_hidden").val();
	
	if (confirm('Are You Sure You Want to Approve Detail ?')) {
		$("#Acceptid").val(p_id);
		document.getElementById('AcceptFaculty').submit();
	} else {
		return false;
	}
		}

function Rejecturl() {
	var p_id=$("#id_parent_hidden").val();
	
	
	if (confirm('Are You Sure You Want to Reject Detail ?')) {
		$("#Rejectid").val(p_id);
		document.getElementById('RejectFaculty').submit();
	} else {
		return false;
	}
	
	
	}

</script>

