<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<link href="js/tunneldesign/tunnelold.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>E-Form</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">E-Form</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>


		<!-- 		<div class="inst_block simple-instruction"> -->
		<!-- 			<strong>Instruction :</strong><b> Once you click on <a class="main-btn info-btn btn-hover disabled btnsave">Final Submit</a> after that you would not be able to add/update any details.</b> -->
		<!-- 		</div> -->

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<!-- input style start -->
					<form:form
						action="Exp_Excel_Nch_action?${_csrf.parameterName}=${_csrf.token}"
						method="POST" class="form-horizontal"
						modelAttribute="Exp_Excel_Nch_cmd" enctype="multipart/form-data">

						<div id="degre_div">
							<div class="card-style mb-30">
								<div class="custom-field-block">
									<div class="row">
									
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="inst_block simple-instruction">
												<strong>Instruction :</strong> Once you click on <b class="simple-link">Final Submit</b> after that you would not be able to add/update any details.
											</div>
										</div>
										
										<div class="col-12 col-sm-12 col-md-6 col-lg-3">
											<div class="select-style-1">
												<label for="text-input">Degree<span
													class="mandatory">*</span></label>
												<div class="select-position" id="div_role">
													<select class="form-control form-control-lg" name="degree"
														id="degree">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getstu_DegreeList}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-12 col-sm-12 col-md-6 col-lg-4 d-none">
											<div class="input-style-1 mb-0">
												<label>Choose Any One</label>
											</div>
											<div class="custom-choose-one">
												<div class="input-style-form-check">
													<div class="form-check radio-style">
														<input type="radio" class="form-check-input" id="Upload"
															name="Choise" value="Upload"> <label for="Upload"
															class="form-check-label">Upload Through Excel</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" class="form-check-input" id="Fillform"
															name="Choise" value="Fillform" checked="checked">
														<label class="form-check-label" for="Fillform">Fill
															Up Form</label>
													</div>
												</div>
											</div>
										</div>

										<div class="col-12 col-sm-12 col-md-6 col-lg-3">
											<div class="input-style-1 mb-0">
												<label>Total Sanctioned Seat</label>
											</div>




											<ul>
												<li>
													<p class="custom-id custom-count">
														<span class="custom-id-title" id="san_st"></span>
													</p>
												</li>
											</ul>
										</div>
										<div class="col-12 col-sm-12 col-md-6 col-lg-3">
											<div class="input-style-1 mb-0">
												<label>Total Admitted Seat</label>
											</div>
											<ul>
												<li>
													<p class="custom-id custom-count">
														<span class="custom-id-title" id="adm_st"></span>
													</p>
												</li>
											</ul>
										</div>
										<div class="col-12 col-sm-12 col-md-6 col-lg-3">
											<div class="input-style-1 mb-0">
												<label>Total Vacant Seat</label>
											</div>
											<ul>
												<li>
													<p class="custom-id custom-count">
														<span class="custom-id-title" id="vac_st"></span>
													</p>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>


						<div id="excel_div">
							<div class="card-style mb-30">
								<h6 class="mb-25">Upload Student Data</h6>
								<div class="row">

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">UG/PG <span class="mandatory">*</span></label>
											<div class="select-position" id="div_role">
												<select name="role_type" id="role_type">
													<option value="0">--Select--</option>
													<option value="45">Under-Graduation</option>
													<option value="42">Post-Graduation</option>
												</select>
											</div>
										</div>
										<label id="role_type_lbl"></label>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-2">
											<label for="username">Upload File<span
												class="mandatory">*</span></label> <input type="file" accept=".xls"
												id="u_file1" name="u_file1" class="form-control"
												autocomplete="off"> <span class="errorClass"
												id="doc_path_doc1_lbl1">${doc_path_doc1_msg}</span> <span
												class='tikClass' id="doc_path_doc_lbltik1"></span>
										</div>
										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-2">
											<label for="username">Date<span class="mandatory">*</span></label>
											<input type="text" name="upload_date" id="upload_date"
												maxlength="10" class="form-control-sm form-control"
												aria-required="true" autocomplete="off" value="DD/MM/YYYY">
										</div>
									</div>
								</div>
								<ul class="buttons-group mainbtn">
									<!-- <li>
							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button"><i class="lni lni-search-alt"></i>search</a>
							</li> -->
									<li><a class="main-btn secondary-btn btn-hover"
										id="btn-reload" value="Search"><i
											class="lni lni-search-alt"></i>Search</a></li>
									<li><input id="btn-save"
										class="main-btn info-btn btn-hover" type="submit" value="Save">
									</li>
									<li><a href="exp_excel_nch_url"
										class="main-btn dark-btn n btn-hover" type="button"
										value="Reset">Reset</a></li>
									<li><a type="button" id="btnExport"
										class="btn btn-primary btn-sm"
										value="Export Sample Template Format"><i
											class="lni lni-share-alt"> </i> Export Sample Template Format</a>
									</li>
									<li><a href="NchStudentDetailsReport_Url"
										class="main-btn dark-btn btn-hover btn-iconic-icon btnview"
										type="button" value="Reset"
										title="View Uploaded Student Details"><i
											class="lni lni-eye"></i>View</a></li>

								</ul>
							</div>
						</div>
						<!-- end card -->
					</form:form>

					<div id="e_form_div">
						<form:form id="e_form_student" action="personal_details_Action"
							method='POST' commandName="personal_details_CMD"
							enctype="multipart/form-data">
							<section class="single-detail-block">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="card-style mb-30">
											<h6 class="mb-10">Fill Up Form</h6>
											<div
												class="table-wrapper table-responsive custom-table custom-table-v2 hybrid-table">
												<table class="table table-striped" id="family_table">
													<!-- 																				id="addNameOfMed" -->
													<thead>
														<tr>
															<th><h6>Sr No</h6></th>
															<th><h6>
																	Institute Details<span class="mandatory">*</span>
																</h6></th>
															<th><h6>
																	Candidate Full name<span class="mandatory">*</span>
																</h6></th>
															<th><h6>
																	Parent's name<span class="mandatory">*</span>
																</h6></th>
															<th><h6>
																	Identifiable Detail<span class="mandatory">*</span>
																</h6></th>
															<th><h6>
																	NEET Detail<span class="mandatory">*</span>
																</h6></th>
															<th><h6>
																	NEET Marks<span class="mandatory">*</span>
																</h6></th>
															<th><h6>
																	Quota/Category<span class="mandatory">*</span>
																</h6></th>
															<th><h6>
																	Counselling Authority<span class="mandatory">*</span>
																</h6></th>
															<th class="last-sticky">Action</th>
														</tr>
													</thead>
													<tbody id="family_sibtbody">
														<!-- 				id="att_TbbodyNameMed" -->
														<tr id="tr_sibling1">
															<!-- 	id="tr_id_attNameMed" -->
															<td class="min-width">
																<div class="lead">
																	<div class="lead-text">
																		<p>1</p>
																	</div>
																</div>
															</td>

															<td>
																<div class="select-style-1 d-none">
																	<div class="select-position">
																		<select class="form-control form-control-lg"
																			name="name_of_institute1" id="name_of_institute1">
																			<option value="0">-- Select --</option>
																			<c:forEach var="item"
																				items="${getapp_instituteNameList}" varStatus="num">
																				<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																			</c:forEach>
																		</select>
																	</div>
																</div>
																<p>
																	<label class="table-label">Institute Name:</label><span
																		id="p_inst1"></span>
																</p>
																<p>
																	<label class="table-label">Institute ID:</label><span
																		id="p_instid1"></span>
																</p>
																<div class="select-style-1 d-none">
																	<div class="select-position">
																		<select name="state_id1" id="state_id1">
																			<option value="0">-- Select --</option>
																			<c:forEach var="item" items="${getMedStateName}"
																				varStatus="num">
																				<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																			</c:forEach>
																		</select>
																	</div>
																</div>
																<p>
																	<label class="table-label">State:</label><span
																		id="p_state1"></span>
																</p>
															</td>

															<td>
																<div class="row justify-content-center">
																	<!-- <div class="col-12 col-sm-12 col-md-12 col-lg-6">
																					<div class="input-style-1">
																						<label class="table-label">Applicant Name</label> <input
																							type="text" id="cand_name1" name="cand_name1"
																							placeholder="Enter Name of Applicant" maxlength="30"
																							autocomplete="off">
																					</div>
																				</div> -->
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																		<div class="input-style-1">
																			<label class="table-label">First Name</label> <input
																				type="text" id="cand_name1" name="cand_name1"
																				placeholder="Enter First Name" maxlength="30"
																				autocomplete="off">
																		</div>
																	</div>
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																		<div class="input-style-1">
																			<label class="table-label">Middle Name & Last Name</label> <input
																				type="text" id="last_name1" name="last_name1"
																				placeholder="Enter Middle Name & Last Name" maxlength="50"
																				autocomplete="off">
																		</div>
																	</div>

																</div>
															</td>

															<td>
																<div class="row justify-content-center">
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																		<div class="input-style-1">
																			<label class="table-label">Mother Name</label> <input
																				type="text" id="mother_name1" name="mother_name1"
																				placeholder="Enter Mother Name" maxlength="30"
																				autocomplete="off">
																		</div>
																	</div>
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																		<div class="input-style-1">
																			<label class="table-label">Father Name</label> <input
																				type="text" id="father_name1" name="father_name1"
																				placeholder="Enter Father Name" maxlength="30"
																				autocomplete="off">
																		</div>
																	</div>
																</div>
															</td>

															<td>
																<div class="row justify-content-center">
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																		<div class="input-style-2">
																			<label class="table-label">Date of birth</label> <input
																				type="text" id="dob1" name="dob1"
																				placeholder="DD/MM/YYYY" autocomplete="off">
																		</div>
																	</div>
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																		<div class="input-style-1">
																			<label class="table-label">E-mail ID</label> <input
																				type="text" id="email1" name="email1"
																				placeholder="Enter Email" maxlength="70"
																				autocomplete="off">
																		</div>
																	</div>
																</div>
															</td>

															<td>
																<div class="row justify-content-center">
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																		<div class="input-style-1">
																			<label class="table-label">Application No</label> <input
																				type="text" id="neet_application_no1"
																				name="neet_application_no1" maxlength="12"
																				autocomplete="off"
																				placeholder="Enter NEET Application No">
																		</div>
																	</div>
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																		<div class="input-style-1">
																			<label class="table-label">Roll No</label> <input
																				type="text" id="neet_roll_no1" name="neet_roll_no1"
																				maxlength="10" autocomplete="off" minlength="12"
																				maxlength="12" placeholder="Enter NEET Roll No">
																		</div>
																	</div>

																</div>
															</td>

															<td>
																<div class="row justify-content-center">
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																		<div class="input-style-1">
																			<label class="table-label">All India Rank</label> <input
																				type="text" id="neet_rank1" name="neet_rank1"
																				minlength="7" maxlength="7" class="form-control"
																				autocomplete="off"
																				placeholder="Enter NEET All India Rank">
																		</div>
																	</div>
																	<div class="col-12 col-sm-12 col-md-12 col-lg-6">
																		<div class="input-style-1">
																			<label class="table-label">Percentile</label> <input
																				type="text" id="neet_percentile1"
																				name="neet_percentile1" maxlength="5" minlength="5"
																				autocomplete="off"
																				placeholder="Enter NEET Percentile">
																		</div>
																	</div>
																	<div class="col-12 col-sm-12 col-md-12 col-lg-6">
																		<div class="input-style-1">
																			<label class="table-label">Obtained Marks</label> <input
																				type="text" id="neet_marks1" name="neet_marks1"
																				maxlength="3" minlength="3" autocomplete="off"
																				placeholder="Enter NEET Marks">
																		</div>
																	</div>
																</div>
															</td>

															<td>
																<div class="row justify-content-center">
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																		<div class="select-style-1">
																			<label class="table-label">Quota</label>
																			<div class="select-position">
																				<select name="quota_id1" id="quota_id1">
																					<option value="0">-- Select --</option>
																					<c:forEach var="item" items="${getQuotaList}"
																						varStatus="num">
																						<option value="${item.id}" name="${item.quota}">${item.quota}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																	</div>
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																		<div class="select-style-1">
																			<label class="table-label">Category</label>
																			<div class="select-position">
																				<select name="category_id1" id="category_id1">
																					<option value="0">-- Select --</option>
																					<c:forEach var="item" items="${getcategorylist}"
																						varStatus="num">
																						<option value="${item.id}" name="${item.category}">${item.category}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																	</div>
																</div>
															</td>

															<td>
																<div class="select-style-1">
																	<label class="table-label">Counselling Authority</label>
																	<div class="select-position">
																		<select name="counselling_authority1"
																			id="counselling_authority1">
																			<option value="0">-- Select --</option>

																			<c:forEach var="item"
																				items="${getCounselingAuthoList}" varStatus="num">
																				<option value="${item.id}"
																					name="${item.counseling_authority}">${item.counseling_authority}</option>
																			</c:forEach>
																		</select>
																	</div>
																</div>
															</td>

															<td class="last-sticky">
																<div class="action">
																	<ul class="buttons-group mainbtn daobtn">
																		<li><a
																			class="main-btn active-btn btn-hover btn-sm btnsave"
																			value="Save" title="Save" id="family_save1"><i
																				class="lni lni-save"></i></a></li>
																		<li class=""><a
																			class="main-btn success-btn btn-hover btn-sm btnaddmore"
																			value="ADD" title="Add" id="Migrated_Students_add1"><i
																				class="lni lni-plus"></i></a></li>
																		<li class=""><a
																			class="main-btn danger-btn btn-hover btn-sm btnremove"
																			value="Delete" title="Delete"
																			id="Migrated_Students_remove1"><i
																				class="lni lni-trash-can"></i></a></li>
																	</ul>
																	<!-- 																								style="display: none;" -->
																</div> <!-- Hidden Start --> <input type="hidden"
																id="savedid1" name="savedid1" value="0"> <input
																type="hidden" id="institute_id1" name="institute_id1"
																value=""> <input type="hidden" id="userid1"
																name="userid1" value="0"> <!-- Hidden end -->
															</td>
														</tr>
														<!-- end table row -->
													</tbody>
												</table>
												<!-- Hidden Start -->
												<input type="hidden" id="family_saveh1" name="family_saveh1"
													class="form-control autocomplete" value="1">
												<!--<input type="hidden" id="count_hidden_att_name_med" -->
												<!--name="count_hidden_att_name_med" -->
												<!--class="form-control autocomplete" value="1"> -->
												<!--<input type="hidden" id="count_hidden_att_name_med1" -->
												<!-- name="count_hidden_att_name_med1" -->
												<!-- class="form-control autocomplete" value="1"> -->
												<!-- end table -->
												<input type="hidden" id="form_d_id" name="form_d_id"
													value="0" class="form-control autocomplete"
													autocomplete="off"> <input type="hidden"
													id="sib_ch_id1" name="sib_ch_id1" value="0"
													class="form-control autocomplete" autocomplete="off">
												<input type="hidden" id="p_id_quali" name="p_id_quali"
													value="0" class="form-control autocomplete"
													autocomplete="off"> <input type="hidden"
													id="hid_quali1" name="hid_quali1" value="0"
													class="form-control autocomplete" autocomplete="off">
												<!-- Hidden End -->
											</div>
										</div>
									</div>
								</div>
								<!-- Hidden Start -->
								<input type="hidden" id="indno" name="indno" value="0"
									class="form-control autocomplete" autocomplete="off">
								<!-- Hidden End -->

							</section>
							<!-- 									<ul class="buttons-group mainbtn"> -->
							<!-- 										<li><input type="submit" id="save_btn" -->
							<!-- 											class="main-btn info-btn btn-hover" value="Save"> -->
							<!-- 											</li> -->
							<!-- 									</ul> -->
							<input type="hidden" name="e_id" id="e_id" value="0" />
							<input type="hidden" name="degree_hid" id="degree_hid" value="0" />
						</form:form>
					</div>

				</div>
				<!-- <div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p"
						id="qualification_tbl_div">
						<table class="table" id="Exp_Exl_Nch_table">
							<thead>
								<tr>
								<th><h6>Ser No</h6></th>
								<th><h6>Student Name</h6></th>
								<th><h6>Date of Birth</h6></th>
								<th><h6>Aadhaar Number</h6></th>
								<th><h6>Email</h6></th>
								<th><h6>Mobile No.</h6></th>
								<th><h6>Admission Date</h6></th>
								<th><h6>System</h6></th>
								<th><h6>Degree</h6></th>
								<th><h6>Enrollment No.</h6></th>
								<th><h6>Upload Date</h6></th>
								<th><h6>UG/PG</h6></th>
								</tr>
														end table row
							</thead>
							<tbody>
							</tbody>
						</table>
										end table
					</div>
				</div>
						end card
			</div>
				end col
		</div> -->
			</div>


			<div class="form-elements-wrapper">
			
				<div class="row">
				
					<div class="col-12">
						<div class="card-style mb-30">
							<div id="dec_div">
								<div class="row">
									<h5 class="text-medium text-medium mb-10 mt-20">Declaration/Undertaking</h5>
									<div class="custom-choose-one">
										<div class="input-style-form-check_block check-multi-list">
											<div class="form-check checkbox-style">
												<input type="checkbox" id="Declaration" name="Declaration"
													autocomplete="off" maxlength="25" class="form-check-input">
												<input type="hidden" id="hiddenUpdate" name="hiddenUpdate"
													class="form-control autocomplete" value="0"> <label
													class="check-list">I hereby declare that
													information furnished above is true and correct in every
													respect.</label>
													<label class="check-list">Once you click on <b class="simple-link">Final Submit</b> after that you would not be able to add/update any details.</label>
													
													
											</div>
										</div>
									</div>
								</div>
								<div class="btn-bottom">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<ul class="buttons-group mainbtn">
												<li><a id="finale_submit"
													class="main-btn info-btn btn-hover disabled btnsave">Final
														Submit</a></li>
											</ul>
										</div>
									</div>
								</div>

							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div id="freez_msg_div">
									<div class="alert alert-info alert-dismissible">
										<button type="button" class="btn-close"
											data-bs-dismiss="alert"></button>
										<strong>Info!</strong> You have done Final Submit , for View
										details click on View Data Button
									</div>
									<div class="btn-bottom">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<ul class="buttons-group mainbtn">

													<li><a href="NchStudentDetailsReport_Url"
														class="main-btn dark-btn btn-hover btn-iconic-icon btnview">
															<i class="lni lni-eye"></i>View Data
													</a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>



						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</section>

<c:url value="Exp_Excel_Nch" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<%-- <form:form action="Exp_Excel_action?${_csrf.parameterName}=${_csrf.token}" method="POST" class="form-horizontal"
	modelAttribute="Exp_Excel_cmd" enctype="multipart/form-data">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header title">
				<h5>
					<span id="lbladd"></span><b>UPLOAD STUDENT DATA</b>
				</h5>
			 </div>
						<div class="card-body card-block">
							<div class="col-md-12">	 
	              			    <div class="col-md-6">
	              					<div class="row form-group">
									   <div class="col-12 col-lg-6">
											<label for="username">UPLOAD FILE<span class="star_design"></span> <span style="color:#ff0000">*</span></label>
										                                                                                                                                                                                                                                                                                                                                                                                                             </div>
										<div class="col-12 col-lg-6">
											<input type="file" id="u_file1" accept=".pdf" name="u_file1" autocomplete="off" class="form-control"  />
										</div>
									</div>
	              				</div> 
	              				<div class="col-md-6">
	              					<div class="row form-group">
									   <div class="col-md-6">
											<label for="username">Date<span class="star_design"></span> <span style="color:#ff0000">*</span></label>
										                                                                                                                                                                                                                                                                                                                                                                                                             </div>
										<div class="col-12 col-lg-6">
											<input type="text" name="upload_date" id="upload_date"
									maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
									class="form-control-sm form-control"
									style="width: 84%; display: inline;"
									onfocus="this.style.color='#000000'"
									onblur="clickrecall(this,'DD/MM/YYYY');"
									onkeyup="clickclear(this, 'DD/MM/YYYY')"
									onchange="clickrecall(this,'DD/MM/YYYY');"
									aria-required="true" autocomplete="off"
									style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"
									placeholder="Select From Date"> <span
									class="focus-border"><i></i></span>
										</div>
									</div>
	              				</div> 
	              			</div>	
	              		<br><br>
					</div>

			<div class="card-footer" align="center">
				<a href="exp_excel_url" class="btn-clear">Reset</a>
				<input type="submit" class="btn-save" value="Save"onclick="return Validation();"> 
				<i id="btnexcel" class="fa fa-file-excel-o"></i><input type="button" id="btnExport" class="btn btn-primary btn-sm"
			 value="EXPORT EXCEL" onclick="getExcel();">
			</div>
	     </div>
	   </div>
</form:form>

<c:url value="Exp_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>  --%>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		datepicketDate('upload_date');
		$("#excel_div").hide();

		$("#freez_msg_div").hide();

		// 			mockjax1('Exp_Exl_Nch_table');
		// 			table = dataTable('Exp_Exl_Nch_table');
		// 			$('#srch').on('click', function(){	
		// 				table.ajax.reload();
		// 			});

		// 			mockjax1('Exp_Exl_Nch_table');
		// 			table = dataTable('Exp_Exl_Nch_table');
		// 			$('#btn-reload').on('click', function() {
		// 				table.ajax.reload();
		// 			});

		if (window.location.href.includes("msg")) {
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		}

		$("#Fillform").click();

	});

	function getExcel() {

		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('search2').submit();
	}

	function UploadExcel() {
		$("#excel_div").show();
		$("#e_form_div").hide();
	}
	function fillform() {
		$("#excel_div").hide();
		$("#e_form_div").show();
	}

	function setdegreeval() {
		$("#degree_hid").val($("#degree").val());
	}

	document
			.addEventListener(
					'DOMContentLoaded',
					function() {

						//04-02-2023 START
						if (window.performance) {
							var navEntries = window.performance
									.getEntriesByType('navigation');
							if (navEntries.length > 0
									&& navEntries[0].type === 'back_forward') {
								console
										.log('As per API lv2, this page is load from back/forward');
								location.reload();
							} else if (window.performance.navigation
									&& window.performance.navigation.type == window.performance.navigation.TYPE_BACK_FORWARD) {
								console
										.log('As per API lv1, this page is load from back/forward');
								location.reload();
							} else {
								console.log('This is normal page load');
							}
						} else {
							console
									.log("Unfortunately, your browser doesn't support this API");
						}
						//04-02-2023 END

						document.getElementById('degree').onchange = function() {
							setdegreeval();
						};

						document.getElementById('Upload').onclick = function() {
							UploadExcel();
						};

						document.getElementById('Fillform').onclick = function() {
							fillform();
						};

						document.getElementById('u_file1').onchange = function() {
							pdfFileSizeValidation(this, this.value, "u_file1",
									"200kb", "doc_path_doc_lbltik1",
									"doc_path_doc1_lbl1", this.accept)
						};

						document.getElementById('upload_date').onclick = function() {
							clickclear(this, 'DD/MM/YYYY');
						};

						document.getElementById('upload_date').onblur = function() {
							clickrecall(this, 'DD/MM/YYYY');
							validateDate_BackDate(this.value, this);
						};

						document.getElementById('upload_date').onkeyup = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById('upload_date').onchange = function() {
							clickrecall(this, 'DD/MM/YYYY');
							validateDate_FutureDate(this.value, this);
						};

					});

	function Validation() {

		if ($("select#degree").val() == "0") {
			alert("Please Select Degree");
			$("select#degree").focus()
			return false;
		}

		if ($("select#role_type").val() == "0") {
			alert("Please Select UG/PG");
			$("select#role_type").focus()
			return false;
		}
		var u_file1 = $("#u_file1").val();
		var lastDot = u_file1.lastIndexOf('.');
		var fileName = u_file1.substring(0, lastDot);
		var ext = u_file1.substring(lastDot + 1);

		if (ext != "xls") {
			alert("Please Upload .xls Format Excel File");
			$("input#u_file1").focus();
			return false;
		}

		if ($("#u_file1").val().trim() == "") {
			alert("Please Upload the File");
			$("input#u_file1").focus();
			return false;
		}

		if ($("#upload_date").val() == ""
				|| $("#upload_date").val() == "DD/MM/YYYY") {
			alert("Please Select Date");
			$("#upload_date").focus()
			return false;
		}

		return true;
	}

	// function data(Exp_Exl_Nch_table) {
	// 	jsondata = [];
	// 	var table = $('#' + Exp_Exl_Nch_table).DataTable();
	// 	var info = table.page.info();
	// 	var pageLength = info.length;
	// 	var startPage = info.start;
	// 	var endPage = info.end;
	// 	var Search = table.search();
	// 	var order = table.order();
	// 	var orderColunm = $(table.column(order[0][0]).header()).html()
	// 			.toLowerCase();
	// 	var orderType = order[0][1];
	// 	var name = $("#name").val();
	// 	var dob = $("#dob").val();
	// 	var aadhar_card = $("#aadhar_card").val();
	// 	var email = $("#email").val();
	// 	var mobile_no = $("#mobile_no").val();
	// 	var admission_date = $("#admission_date").val();
	// 	var system = $("#system").val();
	// 	var degree = $("#degree").val();
	// 	var enrollment_no = $("#enrollment_no").val();
	// 	var upload_date = $("#upload_date").val();
	// 	var role_type = $("#role_type").val();
	// // 	alert(role_type);

	// 	$.post("getFilterStudent_data?" + key + "=" + value, {
	// 		startPage : startPage,
	// 		pageLength : pageLength,
	// 		Search : Search,
	// 		orderColunm : orderColunm,
	// 		orderType : orderType,
	// 		name : name,
	// 		dob : dob,
	// 		aadhar_card : aadhar_card,
	// 		email : email,
	// 		mobile_no : mobile_no,
	// 		admission_date : admission_date,
	// 		system : system,
	// 		degree : degree,
	// 		upload_date : upload_date,
	// 		role_type : role_type

	// 	}, function(j) {
	// 		if (j.length == 0) {
	// 			$("#jlength").val('1');
	// 		}

	// 	for (var i = 0; i < j.length; i++) {

	// 	jsondata.push([j[i][0],j[i][1],j[i][2],j[i][3],j[i][4],j[i][5],j[i][6],j[i][7],j[i][8],j[i][9],j[i][10],j[i][11]]);

	// 	}
	// });

	// 	$.post("getTotalStudent_dataCount?" + key + "=" + value, {Search:Search}, function(j) {
	// 		FilteredRecords = j;
	// 	});
	// }

	//csp----------------------------

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('btnExport').onclick = function() {
			return getExcel();
		};

		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

	});
</script>


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		datepicketDate('dob1');
		$("#dob1").datepicker("option", "maxDate", new Date());

		$("#Migrated_Students_add1").hide();
		$("#Migrated_Students_remove1").hide();

		$("#san_st").text('${list[0][5]}');
		$("#adm_st").text('${list[0][6]}');
		$("#vac_st").text('${list[0][7]}');

		if ('${list[0][8]}' == "0") {
			filledData();
		}
		if ('${list[0][8]}' == "1") {
			$("#e_form_div").hide();
			$("#dec_div").hide();
			$("#freez_msg_div").show();
		}

		// 	if('${list}' != "[]"){
		// 		 $("#institute_id1").val('${list[0][0]}');
		// 		 $("#name_of_institute1").val('${list[0][3]}');
		// 		 $("#state_id1").val('${list[0][4]}');
		// 		 $("#p_inst1").text($( "#name_of_institute1 option:selected" ).text());
		// 		 $("#p_instid1").text('${list[0][0]}');
		// 		 $("#p_state1").text($( "#state_id1 option:selected" ).text());
		// 		 $("#name_of_institute1").change();

		// // 		 $("#state_id1").attr("readonly","readonly");
		// // 		 $("#institute_id1").attr("readonly","readonly");
		// // 		 $("#name_of_institute1").attr("readonly","readonly");
		// 	}

		// 	$("#p_inst1").text($( "#name_of_institute1 option:selected" ));
	});

	// $("#state_id1").attr("readonly","readonly");

	function checkfordot(ind) {
		if ($("#neet_percentile" + ind).val() > 100) {
			alert("Value should not be greater than 100 for percentage");
			$("#neet_percentile" + ind).val("");
			return false;
		}
		return true;
	}

	function FinalSubmitFn() {
		var confMsg = "";
		if (parseInt($("#adm_st").text()) == 0) {
			alert("You haven't entered any detail");
			return false;
		} else {
			confMsg = "Are you sure you want to Submit ?";
		}
		var entrycount = $("#family_saveh1").val();
		var element = document.getElementById("finale_submit");
		if (element.classList[3] != "disabled"
				&& element.classList[4] != "disabled") {
			if (confirm(confMsg)) {
				$.post('eformFinalsubmit?' + key + "=" + value, {
					entrycount : entrycount
				}).done(function(j) {
					alert(j);
					location.reload();
				});
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	function declarationVal() {

		var checkBox = document.getElementById("Declaration");
		var element = document.getElementById("finale_submit");
		if (checkBox.checked == true) {
			element.classList.remove("disabled");
		} else {
			element.classList.add("disabled");
		}

	}

	function filledData() {
		$.post('fetchFilledData?' + key + "=" + value, {val : ""}).done(
				function(j) {

					if (j.length > 0) {
						$("#degree").val(j[0][18]);
						setdegreeval();
						var ser = 0;
						var ind = 1;
						for (var i = 0; i < j.length; i++) {

							if (ser != "0") {
								formOpenNameMed(ser);
							}

							$("#name_of_institute" + ind).val(j[i][0]);
							$("#institute_id" + ind).val(j[i][1]);
							$("#state_id" + ind).val(j[i][2]);
							$("#p_inst" + ind).text(
									$(
											"#name_of_institute" + ind
													+ " option:selected")
											.text());
							$("#p_instid" + ind).text(j[i][1]);
							$("#p_state" + ind).text(
									$("#state_id" + ind + " option:selected")
											.text());

							$("#name_of_institute1").change();

							$("#cand_name" + ind).val(j[i][3]);
							$("#last_name" + ind).val(j[i][4]);
							$("#mother_name" + ind).val(j[i][5]);
							$("#father_name" + ind).val(j[i][6]);
							var dob = j[i][7].substring(0, 10);
							dob = dob.split("-")[2] + "/" + dob.split("-")[1]
									+ "/" + dob.split("-")[0];
							$("#dob" + ind).val(dob);
							$("#email" + ind).val(j[i][8]);
							$("#neet_application_no" + ind).val(j[i][9]);
							$("#neet_roll_no" + ind).val(j[i][10]);
							$("#neet_rank" + ind).val(j[i][11]);
							$("#neet_percentile" + ind).val(j[i][12]);
							$("#neet_marks" + ind).val(j[i][13]);
							$("#quota_id" + ind).val(j[i][14]);
							$("#category_id" + ind).val(j[i][15]);
							$("#counselling_authority" + ind).val(j[i][16]);
							$("#savedid" + ind).val(j[i][17]);
							$("#userid" + ind).val(j[i][19]);
							$("#hid_quali" + ind).val("1");

							$("#Migrated_Students_remove" + ind).show();

							if (i == (j.length - 1)) {
								$("#Migrated_Students_add" + ind).show();
							}
							
// 							var yr = j[i][21].split("-")[0];
// 							var mo = j[i][21].split("-")[1];
// 							var dy = j[i][21].split("-")[2];
							
// 							const x = new Date(yr,mo,dy);
							
// // 							const y = new Date().toLocaleDateString();
							
// // 							var cyr = y.split("/")[2];
// // 							var cmo = y.split("/")[1];
// // 							var cdy = y.split("/")[0];
							
// 							const y2 = new Date(2023,03,05);
							
// 							if(x < y2){
// 								$("#Migrated_Students_remove" + ind).hide();
// 								$("#Migrated_Students_add" + ind).hide();
// 								$("#family_save" + ind).hide();
// 								if (i == (j.length - 1)) {
// 									$("#Migrated_Students_add" + ind).show();
// 								}
// 							}

							ser = parseInt(ser) + 1;
							ind = parseInt(ind) + 1;

						}
					} else {
						if ('${list}' != "[]") {

							$("#san_st").text('${list[0][5]}');
							$("#adm_st").text('${list[0][6]}');
							$("#vac_st").text('${list[0][7]}');

							$("#institute_id1").val('${list[0][0]}');
							$("#name_of_institute1").val('${list[0][3]}');
							$("#state_id1").val('${list[0][4]}');
							$("#p_inst1").text(
									$("#name_of_institute1 option:selected")
											.text());
							$("#p_instid1").text('${list[0][0]}');
							$("#p_state1").text(
									$("#state_id1 option:selected").text());

							$("#name_of_institute1").change();

						}
					}

				});
	}

	function formOpenNameMed(R) {
		
		if($("#degree").val() == "0" || $("#degree").val() == ""){
			alert("Please Select Degree");
			$("#degree").attr("readonly", false);
			return false;
		}else{
		$("#degree").attr("readonly", true);
		$("#family_table").show();

		att = 0;
		$("#Migrated_Students_add" + R).hide();
		// 			 $("#Migrated_Students_remove"+R).hide();
		att = parseInt($("#family_saveh1").val()) + 1;
		att = parseInt(R) + 1;

		if (att <= parseInt($("#san_st").text())) {

			$("input#family_saveh1").val(att);//current serial No
			$("table#family_table")
					.append(
							'<tr id="tr_sibling1'+att+'">'
									+ '<td class="min-width">'
									+ '	<div class="lead">'
									+ '			<div class="lead-text">' + '				<p>'
									+ att
									+ '</p>'
									+ '			</div>'
									+ '		</div>'
									+ '	</td>'

									+ '	<td>'
									+ '		<div class="select-style-1 d-none">'
									+ '			<div class="select-position">'
									+ '				<select class="form-control form-control-lg"'
							+'					name="name_of_institute'+att+'" id="name_of_institute'+att+'">'
									+ '					<option value="0">-- Select --</option><c:forEach var="item" items="${getapp_instituteNameList}" varStatus="num">'
									+ '						<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select>'
									+ '			</div>'
									+ '		</div>'
									+ '		<p><label class="table-label">Institute Name:</label><span id="p_inst'+att+'"></span></p>'
									+ '		<p><label class="table-label">Institute ID:</label><span id="p_instid'+att+'"></span></p>'
									+ '		<div class="select-style-1 d-none">'
									+ '			<div class="select-position">'
									+ '				<select name="state_id'+att+'" id="state_id'+att+'">'
									+ '					<option value="0">-- Select --</option><c:forEach var="item" items="${getMedStateName}"	varStatus="num">'
									+ '						<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select>'
									+ '			</div>'
									+ '		</div>'
									+ '		<p><label class="table-label">State:</label><span id="p_state'+att+'"></span></p>'
									+ '	</td>'

									+ '	<td>'
									+ '		<div class="row justify-content-center">'
									+ '			<!-- <div class="col-12 col-sm-12 col-md-12 col-lg-6">'
									+ '				<div class="input-style-1">'
									+ '					<label class="table-label">Applicant Name</label> <input'
							+'						type="text" id="cand_name'+att+'" name="cand_name'+att+'"'
							+'						placeholder="Enter Name of Applicant" maxlength="30"'
							+'						autocomplete="off">'
									+ '				</div>'
									+ '			</div> -->'
									+ '			<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
									+ '				<div class="input-style-1">'
									+ '					<label class="table-label">First Name</label> <input'
							+'						type="text" id="cand_name'+att+'" name="cand_name'+att+'"'
							+'						placeholder="Enter First Name" maxlength="30"'
							+'						autocomplete="off">'
									+ '				</div>'
									+ '			</div>'
									+ '			<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
									+ '				<div class="input-style-1">'
									+ '					<label class="table-label">Middle Name & Last Name</label> <input'
							+'						type="text" id="last_name'+att+'" name="last_name'+att+'"'
							+'						placeholder="Enter Middle Name & Last Name" maxlength="50"'
							+'						autocomplete="off">'
									+ '				</div>'
									+ '			</div>'

									+ '		</div>'
									+ '	</td>'

									+ '	<td>'
									+ '		<div class="row justify-content-center">'
									+ '			<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
									+ '				<div class="input-style-1">'
									+ '					<label class="table-label">Mother Name</label> <input'
							+'						type="text" id="mother_name'+att+'" name="mother_name'+att+'"'
							+'						placeholder="Enter Mother Name" maxlength="30"'
							+'						autocomplete="off">'
									+ '				</div>'
									+ '			</div>'
									+ '			<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
									+ '				<div class="input-style-1">'
									+ '					<label class="table-label">Father Name</label> <input'
							+'						type="text" id="father_name'+att+'" name="father_name'+att+'"'
							+'						placeholder="Enter Father Name" maxlength="30"'
							+'						autocomplete="off">'
									+ '				</div>'
									+ '			</div>'
									+ '		</div>'
									+ '	</td>'

									+ '	<td>'
									+ '		<div class="row justify-content-center">		'
									+ '			<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
									+ '				<div class="input-style-2">'
									+ '					<label class="table-label">Date of birth</label> <input'
							+'						type="text" id="dob'+att+'" name="dob'+att+'"'
							+'						placeholder="DD/MM/YYYY" autocomplete="off">'
									+ '				</div>'
									+ '			</div>'
									+ '			<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
									+ '				<div class="input-style-1">'
									+ '					<label class="table-label">E-mail ID</label> <input'
							+'						type="text" id="email'+att+'" name="email'+att+'"'
							+'						placeholder="Enter Email" maxlength="70"'
							+'						autocomplete="off">'
									+ '				</div>'
									+ '			</div>'
									+ '		</div>'
									+ '	</td>'

									+ '	<td>'
									+ '		<div class="row justify-content-center">'
									+ '				<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
									+ '				<div class="input-style-1">'
									+ '					<label class="table-label">Application No</label>'
									+ '					<input type="text" id="neet_application_no'+att+'"'
							+'					name="neet_application_no'+att+'" maxlength="12"'
							+'					autocomplete="off"'
							+'					placeholder="Enter NEET Application No">'
									+ '				</div>'
									+ '			</div>'
									+ '			<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
									+ '				<div class="input-style-1">'
									+ '				<label class="table-label">Roll No</label>'
									+ '				<input type="text" id="neet_roll_no'+att+'"'
							+'				name="neet_roll_no'+att+'" maxlength="10"'
							+'				autocomplete="off" minlength="12" maxlength="12"'
							+'				placeholder="Enter NEET Roll No">'
									+ '			</div>'
									+ '		</div>'

									+ '	</div>'
									+ '</td>'

									+ '<td>'
									+ '	<div class="row justify-content-center">'
									+ '		<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
									+ '			<div class="input-style-1">'
									+ '				<label class="table-label">All India Rank</label>'
									+ '				<input type="text" id="neet_rank'+att+'" name="neet_rank'+att+'"'
							+'				minlength="7" maxlength="7" class="form-control"'
							+'				autocomplete="off"'
							+'				placeholder="Enter NEET All India Rank">'
									+ '			</div>'
									+ '		</div>'
									+ '		<div class="col-12 col-sm-12 col-md-12 col-lg-6">'
									+ '			<div class="input-style-1">'
									+ '				<label class="table-label">Percentile</label>'
									+ '				<input type="text" id="neet_percentile'+att+'"'
							+'				name="neet_percentile'+att+'" maxlength="5" minlength="5"'
							+'				autocomplete="off"'
							+'				placeholder="Enter NEET Percentile">'
									+ '			</div>'
									+ '		</div>'
									+ '		<div class="col-12 col-sm-12 col-md-12 col-lg-6">'
									+ '			<div class="input-style-1">'
									+ '				<label class="table-label">Obtained Marks</label>'
									+ '				<input type="text" id="neet_marks'+att+'" name="neet_marks'+att+'"'
							+'				maxlength="3" minlength="3" autocomplete="off"'
							+'				placeholder="Enter NEET Marks">'
									+ '			</div>'
									+ '		</div>'
									+ '	</div>'
									+ '</td>'

									+ '<td>'
									+ '<div class="row justify-content-center">'
									+ '	<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
									+ '		<div class="select-style-1">'
									+ '			<label class="table-label">Quota</label>'
									+ '			<div class="select-position">'
									+ '				<select name="quota_id'+att+'" id="quota_id'+att+'">'
									+ '					<option value="0">-- Select --</option><c:forEach var="item" items="${getQuotaList}" varStatus="num">'
									+ '						<option value="${item.id}" name="${item.quota}">${item.quota}</option></c:forEach></select>'
									+ '			</div>'
									+ '		</div>'
									+ '	</div>'
									+ '	<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
									+ '			<div class="select-style-1">'
									+ '				<label class="table-label">Category</label>'
									+ '				<div class="select-position">'
									+ '					<select name="category_id'+att+'" id="category_id'+att+'">'
									+ '						<option value="0">-- Select --</option><c:forEach var="item" items="${getcategorylist}" varStatus="num">'
									+ '							<option value="${item.id}" name="${item.category}">${item.category}</option></c:forEach></select>'
									+ '				</div>'
									+ '			</div>'
									+ '		</div>'
									+ '	</div>'
									+ '</td>'

									+ '<td>'
									+ '	<div class="select-style-1">'
									+ '		<label class="table-label">Counselling Authority</label>'
									+ '		<div class="select-position">'
									+ '			<select name="counselling_authority'+att+'"'
							+'				id="counselling_authority'+att+'">'
									+ '				<option value="0">-- Select --</option><c:forEach var="item" items="${getCounselingAuthoList}" varStatus="num">'
									+ '					<option value="${item.id}" name="${item.counseling_authority}">${item.counseling_authority}</option></c:forEach></select>'
									+ '			</div>'
									+ '	</div>'
									+ '</td>'
									+ '<td class="last-sticky">'
									+ '	<div class="action">'
									+ '		<ul class="buttons-group mainbtn daobtn">'
									+ '			<li><a'
							+'				class="main-btn active-btn btn-hover btn-sm btnsave"'
							+'				value="Save" title="Save" id="family_save'+att+'"><i'
							+'					class="lni lni-save"></i></a></li>'
									+ '			<li class=""><a'
							+'				class="main-btn success-btn btn-hover btn-sm btnaddmore"'
							+'				value="ADD" title="Add" id="Migrated_Students_add'+att+'"><i'
							+'					class="lni lni-plus"></i></a></li>'
									+ '			<li class=""><a'
							+'				class="main-btn danger-btn btn-hover btn-sm btnremove"'
							+'				value="Delete" title="Delete"'
							+'				id="Migrated_Students_remove'+att+'"><i'
							+'				class="lni lni-trash-can"></i></a></li>'
									+ '		</ul>'
									+ '	</div> '
									+ '<input type="hidden" id="savedid'+att+'" name="savedid'+att+'" value="0">'
									+ '<input type="hidden" id="institute_id'+att+'" name="institute_id'+att+'" value="">'
									+ '<input type="hidden" id="hid_quali'+att+'" name="hid_quali'+att+'" value="0">'
									+ '<input type="hidden" id="userid'+att+'" name="userid'+att+'" value="0">'
									+ '</td>' + '</tr>');
			addOnclick(att);

			$("#Migrated_Students_add" + att).hide();
			$("#Migrated_Students_remove" + att).hide();
		} else {
			alert("No Vacant Seats are available as per Total Number of Sanctioned Seat");
			att = att - 1;
			$("#Migrated_Students_remove" + att).show();
		}
	}
	}

	function nper(att) {
		var nr = $("#neet_marks" + att).val();
		if (parseInt(nr) > 720) {
			alert("neet marks should be less than 720")
			$("#neet_marks" + att).val("");
			$("#neet_marks" + att).focus();
		}
	}

	document
			.addEventListener(
					'DOMContentLoaded',
					function() {

						document.getElementById('family_save1').onclick = function() {
							return Save_As_Draft_Quali(1);
						};
						document.getElementById('Migrated_Students_add1').onclick = function() {
							return formOpenNameMed(1);
						};
						document.getElementById('Migrated_Students_remove1').onclick = function() {
							return formopen_re_NameMed(1);
						};
						document.getElementById('cand_name1').onkeypress = function() {
							return onlyAlphabetsStringSpace(event, this);
						};
						document.getElementById('last_name1').onkeypress = function() {
							return onlyAlphabetsStringSpace(event, this);
						};
						document.getElementById('mother_name1').onkeypress = function() {
							return onlyAlphabetsStringSpace(event, this);
						};
						document.getElementById('father_name1').onkeypress = function() {
							return onlyAlphabetsStringSpace(event, this);
						};
						document.getElementById('name_of_institute1').onchange = function() {
							getInsCodeListbyInsName(1);
						};
						document.getElementById('neet_application_no1').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('neet_roll_no1').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('neet_rank1').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('neet_percentile1').onkeypress = function() {
							return isNumberKeydecimal(this, event);
						};
						document.getElementById('neet_percentile1').onkeyup = function() {
							return checkfordot(1);
						};

						document.getElementById('neet_marks1').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('neet_marks1').onchange = function() {
							nper(1);
						};

						document.getElementById('email1').onchange = function() {
							return checkgmail(this.value, this.id);
						};

						document.getElementById('dob1').onclick = function() {
							clickclear(this, 'DD/MM/YYYY');
						};

						document.getElementById('dob1').onblur = function() {
							clickrecall(this, 'DD/MM/YYYY');
							validateDate_BackDate(this.value, this);
						};

						document.getElementById('dob1').onkeyup = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById('dob1').onchange = function() {
							clickrecall(this, 'DD/MM/YYYY');
							validateDate_FutureDate(this.value, this);
						};
						document.getElementById('dob1').onchange = function() {
							calculate_age(1);
						};
						document.getElementById('Declaration').onclick = function() {
							declarationVal();
						};

						document.getElementById('finale_submit').onclick = function() {
							FinalSubmitFn();
						};

						document.getElementById('cand_name1').onkeyup = function() {
							return checkdegreeselected();
						};

					});

	function checkdegreeselected() {
		if ($("#degree").val() == "0") {
			alert("Please Select Degree");
			$("#cand_name1").val("");
			$("#degree").focus();
			return false;
		} else {
			return true;
		}
	}

	function addOnclick(att) {
		if ('${list}' != "[]") {
			$("#institute_id" + att).val('${list[0][0]}');
			$("#name_of_institute" + att).val('${list[0][3]}');
			$("#state_id" + att).val('${list[0][4]}');
			$("#p_inst" + att).text(
					$("#name_of_institute" + att + " option:selected").text());
			$("#p_instid" + att).text('${list[0][0]}');
			$("#p_state" + att).text(
					$("#state_id" + att + " option:selected").text());
			$("#name_of_institute" + att).change();
			//	 		 $("#state_id"+att).attr("readonly","readonly");
			//	 		 $("#institute_id"+att).attr("readonly","readonly");
			//	 		 $("#name_of_institute"+att).attr("readonly","readonly");
		}

		datepicketDate('dob' + att);

		$("#dob" + att).datepicker("option", "maxDate", new Date());

		document.getElementById('dob' + att).onclick = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('dob' + att).onblur = function() {
			clickrecall(this, 'DD/MM/YYYY');
			validateDate_BackDate(this.value, this);
		};
		document.getElementById('dob' + att).onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('dob' + att).onchange = function() {
			clickrecall(this, 'DD/MM/YYYY');
			validateDate_FutureDate(this.value, this);
		};
		document.getElementById('dob' + att).onchange = function() {
			calculate_age(att);
		};
		document.getElementById('Migrated_Students_add' + att).onclick = function() {
			formOpenNameMed(att);
		};
		document.getElementById('Migrated_Students_remove' + att).onclick = function() {
			return formopen_re_NameMed(att);
		};
		document.getElementById('family_save' + att).onclick = function() {
			Save_As_Draft_Quali(att);
		};
		document.getElementById('name_of_institute' + att).onchange = function() {
			getInsCodeListbyInsName(att);
		};
		document.getElementById('cand_name' + att).onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('last_name' + att).onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('mother_name' + att).onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('father_name' + att).onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('neet_application_no' + att).onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('neet_roll_no' + att).onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('neet_rank' + att).onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('neet_percentile' + att).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('neet_percentile' + att).onkeyup = function() {
			return checkfordot(att);
		};
		document.getElementById('neet_marks' + att).onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('neet_marks' + att).onchange = function() {
			nper(att);
		};

		document.getElementById('email' + att).onchange = function() {
			return checkgmail(this.value, this.id);
		};

		$('.select2').select2({
			containerCssClass : "wrap"
		});

// 		$("#degree").attr("readonly", true);
	}

	function calculate_age(ind) {

		var obj = $("#dob" + ind).val();
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
		//	 	   $(".get-value").text(""+year+" Years");
		//	 	   $("#yrr").val(year);
		if (year < 17 || year == "" || year == "0") {
			alert("Age Should Be Greater Than 17 Years")
			$("#dob" + ind).focus();
			return false;
		}
	}

	function formopen_re_NameMed(R) {

		if (confirm("Are You Sure You Want to Delete ?")) {
			var stu_id = $("#savedid" + R).val();
			var email_id = $("#email" + R).val();

			$.post('eformDeleteData?' + key + "=" + value, {
				stu_id : stu_id,
				email_id : email_id
			}).done(function(j) {
				alert(j);
				location.reload();
			});
		}

		//			var qualtification_idhid2= $("input#qualtification_id"+R).val();
		//	 	var result = confirm("Are you sure you want to delete?");
		//	 	var p_id = $("input#p_id").val();
		//	 	var hid_quali1 = $("input#hid_quali"+att).val();
		//	 	$.post(
		//	 			"delete_child_quali?" + key + "=" + value,
		//	 			{
		//	 				p_id : p_id,
		//	 				hid_quali1 : hid_quali1
		//	 			},
		//	 			function(j) {
		//	 				alert(j);
		//	 			});

		// 		 $("tr#tr_sibling1"+R).remove();
		// 		 att = att-1;
		// 		 $("input#count_hidden_att_name_med").val(att);
		// 		 $("#Migrated_Students_add"+att).show();
		// 		 $("#Migrated_Students_remove"+att).show();	

	}

	function Save_As_Draft_Quali(ps) {
		//	 	alert(ps);
		$.ajaxSetup({
			async : false
		});

		var type_of_degree_id = $('#institute_id' + ps).val();
		var type_of_degree_id = $('#name_of_institute' + ps).val();

		if ($("select#degree").val().trim() == "0") {
			alert("Please Select Degree");
			$("select#degree").focus();
			return false;
		}

		if ($("input#cand_name" + ps).val().trim() == "") {
			alert("Please Enter First Name");
			$("input#cand_name" + ps).focus();
			return false;
		}

		if ($("input#last_name" + ps).val().trim() == "") {
			alert("Please Enter Middle Name & Last Name");
			$("input#last_name" + ps).focus();
			return false;
		}

		if ($("input#mother_name" + ps).val().trim() == "") {
			alert("Please Enter Mother Name");
			$("input#mother_name" + ps).focus();
			return false;
		}
		if ($("input#father_name" + ps).val().trim() == "") {
			alert("Please Enter Father Name");
			$("input#father_name" + ps).focus();
			return false;
		}

		if ($("#dob" + ps).val().trim() == ""
				|| $("#dob" + ps).val().trim() == "DD/MM/YYYY") {
			alert("Please Enter Date of Birth");
			$("#dob" + ps).focus();
			return false;
		}

		// 		calculate_age(ps);

		if ($("#email" + ps).val().trim() == "") {
			alert("Please Enter Email id");
			$("input#email" + ps).focus();
			return false;
		} else {

			var pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

			if ($("input#email" + ps).val().trim().match(pattern)) {
			} else {
				alert("Please Enter Valid Email Address");
				$("input#email" + ps).focus();
				$("input#email" + ps).val('');
				return false;
			}
		}

		if ($("input#neet_application_no" + ps).val().trim() == "") {
			alert("Please Enter NEET Application Number");
			$("input#neet_application_no" + ps).focus();
			return false;
		}
		if ($("input#neet_application_no" + ps).val().trim().length != 12) {
			alert("Please Enter 12 digit NEET Application Number");
			$("input#neet_application_no" + ps).focus();
			return false;
		}
		if ($("input#neet_roll_no" + ps).val().trim() == "") {
			alert("Please Enter NEET Roll Number");
			$("input#neet_roll_no" + ps).focus();
			return false;
		}
		if ($("input#neet_roll_no" + ps).val().trim().length != 10) {
			alert("Please Enter 10 digit NEET Roll Number");
			$("input#neet_roll_no" + ps).focus();
			return false;
		}

		if ($("input#neet_rank" + ps).val().trim() == "") {
			alert("Please Enter All India NEET Rank");
			$("input#neet_rank" + ps).focus();
			return false;
		}

		if ($("input#neet_rank" + ps).val().trim().length > 7) {
			alert("Please Enter Maximum 7 digit All India NEET Rank");
			$("input#neet_rank" + ps).focus();
			return false;
		}
		if ($("input#neet_percentile" + ps).val().trim() == "") {
			alert("Please Enter NEET Percentile");
			$("input#neet_percentile" + ps).focus();
			return false;
		}

		var np = $("input#neet_percentile" + ps).val();

		if (np.includes(".")) {
			if (np.split('.')[0].length > 2 || np.split('.')[1].length > 2) {
				alert("Please Enter 2 Digits & 2 Decimal NEET Percentile");
				$("input#neet_percentile" + ps).focus();
				return false;
			}
		} else {
			if (parseInt(np) > 100) {
				alert("Please Enter Less than or Equal 100 in NEET Percentile");
				$("input#neet_percentile" + ps).focus();
				return false;
			}
		}
		if ($("input#neet_marks" + ps).val().trim() == "") {
			alert("Please Enter NEET Marks");
			$("input#neet_marks" + ps).focus();
			return false;
		}
		if (parseInt($("input#neet_marks" + ps).val().trim()) > 720) {
			alert("Please Enter NEET marks out of 720 ");
			$("input#neet_marks" + ps).focus();
			return false;
		}
		if ($("select#quota_id" + ps).val().trim() == "0") {
			alert("Please Select Quota");
			$("select#quota_id" + ps).focus();
			return false;
		}
		if ($("select#counselling_authority" + ps).val().trim() == "0") {
			alert("Please Select Counselling Authority");
			$("select#counselling_authority" + ps).focus();
			return false;
		}
		if ($("select#category_id" + ps).val().trim() == "0") {
			alert("Please Select Category");
			$("select#category_id" + ps).focus();
			return false;
		}

		$("#indno").val(ps);
		var form_data = new FormData(document.getElementById("e_form_student"));
		$.ajax({
			url : 'e_form_nch_student_Action?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(data) {

			console.log(data.msg);

			if (data.err != undefined) {
				console.log(data.err);

				//	 	      		 $("#hid_quali1").val(data);
				alert(data.err);
				$("#" + data.field + "" + ps).focus();

				//	 	      		$("#Migrated_Students_add"+ps).show(); 
				//	 		    	$("#Migrated_Students_remove"+ps).show(); 
			} else {
				// 			        	alert(data.msg);

				if (data.savedid > 0) {
					

					$("#san_st").text(data.sanc_seat);
					$("#adm_st").text(data.admt_seat);
					$("#vac_st").text(data.vacn_seat);

					
					if ($("#hid_quali" + ps).val() == "0") {
						$("#Migrated_Students_add" + ps).show();
						$("#Migrated_Students_remove" + ps).show();
					}
					$("#hid_quali" + ps).val(1);
					$("#savedid" + ps).val(data.savedid);
					$("#userid" + ps).val(data.userid);
					
					alert(data.msg);
					
					if (data.msg == "Data Updated Successfully.") {
						location.reload();
					}

				} else {
					alert(data.err);
				}

			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}

	// function getcourselistbydate() {
	function getInsCodeListbyInsName(index) {
		var name_of_institute = $("#name_of_institute" + index).val();

		$.post('getInsCodeListbyInsName_ctrl?' + key + "=" + value, {
			name_of_institute : name_of_institute
		}).done(function(j) {
			for (var i = 0; i < j.length; i++) {
				$("#institute_id" + index).val(j[0].code);
				$("#state_id" + index).val(j[0].state_id);
				$("#state_id" + index).attr("readonly", "readonly");
				$("#institute_id" + index).attr("readonly", "readonly");
			}

		});
	}

	function checkgmail(email1, id) {
		// 			 document.getElementById("email").innerHTML="";
		var pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		//			 var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

		if (email1.match(pattern)) {
		} else {
			alert("Please Enter Valid Email Address");
			$("#" + id).focus();
			$("#" + id).val('');
			return false;
		}
	}
</script>



