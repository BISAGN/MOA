<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <script type="text/javascript" src="js/watermark/common.js"></script> -->
<!-- <link rel="stylesheet" href="js/InfiniteScroll_old/css/site.css"> -->
<!-- datatable style and js start-->
<!-- <link rel="stylesheet" -->
<!-- 	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css"> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css"> -->
<!-- <script type="text/javascript" -->
<!-- 	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script> -->
<!-- <script type="text/javascript" -->
<!-- 	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script> -->
<!-- datatable style and js end-->
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>

<!-- <script src="js/Pdf_View/pdf.js" type="text/javascript"></script> -->
<!-- <script src="js/Pdf_View/pdf.worker.js" type="text/javascript"></script> -->
<!-- <link rel="stylesheet" type="text/css" href="js/Pdf_View/pdfview.css"> -->


<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Admission E-Form</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Admission
									E-Form</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<form:form
						action="Exp_Excel_action?${_csrf.parameterName}=${_csrf.token}"
						method="POST" class="form-horizontal"
						modelAttribute="Exp_Excel_cmd" enctype="multipart/form-data">
						<div id="degre_div">
							<div class="card-style mb-30">
								<div class="custom-field-block">
									<div class="row">
									
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="inst_block simple-instruction">
												<strong>Instruction :</strong> Once you click on <b class="simple-link">Submit Data to NCISM</b> after that you would not be able to add/update any details.
											</div>
										</div>
										
										<div class="col-12 col-sm-12 col-md-6 col-lg-3">
											<div class="select-style-1">
												<label for="text-input">Degree<span
													class="mandatory">*</span></label>
												<div class="select-position" id="div_role">
													<select class=" form-control form-control-lg" name="degree"
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

										<div class="col-12 col-sm-12 col-md-6 col-lg-2">
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
										<div class="col-12 col-sm-12 col-md-6 col-lg-2">
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
										<div class="col-12 col-sm-12 col-md-6 col-lg-2">
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
										<div class="col-12 col-sm-12 col-md-6 col-lg-2" id="vadBtndiv">
											<ul class="buttons-group mainbtn">
												<li><a href="StudentDetailsReport_Url"
												class="main-btn dark-btn btn-hover btn-iconic-icon btnview">
												<i class="lni lni-eye"></i>View All Details
												</a></li>
											</ul>
										</div>

									</div>
								</div>
							</div>
						</div>

						<div id="excel_div">
							<div class="card-style mb-30">
								<h6 class="text-medium mb-10">Upload Through Excel form</h6>
								<div class="custom-field-block">
									<div class="row">
										<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="select-style-1">
												<label for="text-input">UG/PG <span
													class="mandatory">*</span></label>
												<div class="select-position" id="div_role">
													<select class="singleselect form-control form-control-lg"
														name="role_type" id="role_type">
														<option value="0">--Select--</option>
														<option value="26">Student</option>
														<option value="46">Admission - Under-Graduation</option>
														<option value="44">Admission - Post-Graduation</option>
													</select>
												</div>
											</div>
											<label id="role_type_lbl"></label>
										</div>

										<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="input-style-1">
												<label for="username">Upload File<span
													class="mandatory">*</span></label> <input type="file" accept=".xls"
													id="u_file1" name="u_file1" class="form-control"
													autocomplete="off"> <input type="hidden"
													accept=".xls" id="u_file1h" name="u_file1h"
													class="form-control" autocomplete="off">
											</div>
										</div>

										<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="input-style-2">
												<label for="username">Date<span class="mandatory">*</span></label>
												<input type="text" name="upload_date" id="upload_date"
													class="form-control-sm form-control effect-9 "
													aria-required="true" autocomplete="off" value="DD/MM/YYYY">
												<!--onclick="clickclear(this, 'DD/MM/YYYY')" -->
												<!--onfocus="this.style.color='#000000'"  -->
												<!--onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"  -->
												<!--onkeyup="clickclear(this, 'DD/MM/YYYY')"  -->
												<!--onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this); " -->
											</div>
										</div>
									</div>
								</div>

								<div class="btn-bottom">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12">
											<ul class="buttons-group mainbtn">

												<!-- <li><a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button"><i class="lni lni-search-alt"></i>search</a>
									</li> -->
												<li><input id="btn-save"
													class="main-btn info-btn btn-hover" type="submit"
													value="Save" title="Save"> <!-- onclick="return Validation();" --></li>
												<li><a href="exp_excel_url"
													class="main-btn dark-btn n btn-hover" type="button"
													value="Reset" title="Reset">Reset</a></li>
												<li><a id="btnExport"
													class="main-btn active-btn btn-hover btn-iconic-icon btnexport"
													title="Export Sample Template Format"><i
														class="lni lni-share-alt"> </i>Export Sample Template
														Format</a></li>
												<li><a href="StudentDetailsReport_Url"
													class="main-btn dark-btn btn-hover btn-iconic-icon btnview"
													type="button" title="View Uploaded Student Details"><i
														class="lni lni-eye"></i>View</a></li>
											</ul>
										</div>
									</div>
								</div>
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
											<h6 class="text-medium mb-10">Fill Up Form</h6>
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
																	Candidate Full Name<span class="mandatory">*</span>
																</h6></th>
															<th><h6>
																	Parent's Name<span class="mandatory">*</span>
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
																
																<th><h6>
																	Fees Detail<span class="mandatory">*</span>
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
																				placeholder="Enter Email Id" maxlength="70"
																				autocomplete="off">
																		</div>
																	</div>
																</div>
															</td>

															<td>
																<div class="row justify-content-center">
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																		<div class="input-style-1">
																			<label class="table-label"> NEET Application
																				No</label> <input type="text" id="neet_application_no1"
																				name="neet_application_no1" maxlength="12"
																				autocomplete="off" placeholder="NEET Application No">
																		</div>
																	</div>
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																		<div class="input-style-1">
																			<label class="table-label">NEET Roll No</label> <input
																				type="text" id="neet_roll_no1" name="neet_roll_no1"
																				maxlength="10" autocomplete="off" minlength="12"
																				maxlength="12" placeholder="NEET Roll No">
																		</div>
																	</div>

																</div>
															</td>

															<td>
																<div class="row justify-content-center">
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																		<div class="input-style-1">
																			<label class="table-label">NEET All India
																				Rank</label> <input type="text" id="neet_rank1"
																				name="neet_rank1" minlength="7" maxlength="7"
																				class="form-control" autocomplete="off"
																				placeholder="NEET All India Rank">
																		</div>
																	</div>
																	<div class="col-12 col-sm-12 col-md-12 col-lg-6">
																		<div class="input-style-1">
																			<label class="table-label">NEET Percentile</label> <input
																				type="text" id="neet_percentile1"
																				name="neet_percentile1" maxlength="5" minlength="5"
																				autocomplete="off" placeholder="NEET Percentile">
																		</div>
																	</div>
																	<div class="col-12 col-sm-12 col-md-12 col-lg-6">
																		<div class="input-style-1">
																			<label class="table-label">Obtained Marks</label> <input
																				type="text" id="neet_marks1" name="neet_marks1"
																				maxlength="3" minlength="3" autocomplete="off"
																				placeholder="Enter Obtained Marks">
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

															<td>
																<div class="row justify-content-center">
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																		<div class="input-style-2">
																			<label class="table-label">Date of Fees
																				Payment</label> <input type="text" id="fees_date1"
																				name="fees_date1" placeholder="DD/MM/YYYY"
																				autocomplete="off">
																		</div>
																	</div>
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																		<div class="input-style-1">
																			<label for="fees_receipt1" class="table-label">Fees
																				Receipt</label>
																			<div class="input-group custom-input-group">
																				<input type="file" accept=".pdf" id="fees_receipt1"
																					name="fees_receipt1" class="form-control">
																				<ul class="buttons-group custom-d-none"
																					id="feeview1">
																					<li><a
																						class="main-btn dark-btn btn-hover btn-sm btnview"
																						id="view_fees_receipt1"
																						title="View Receipt"> <i class="lni lni-eye"><input
																								type="hidden" id="receiptid1" value=""></i>
																					</a></li>
																				</ul>

																				<div class="note-text ">
																					<span class="errorClass" id="receipt_path_err1"></span>
																					<span class='tikClass' id="receipt_path_lbltik1"></span>
																				</div>

																				<input type="hidden" accept=".pdf"
																					id="hid_fees_receipt1" name="hid_fees_receipt1"
																					class="form-control" autocomplete="off">
																			</div>
																		</div>
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
																id="savedid1" name="savedid1" value="0"> 
																<input
																type="hidden" id="institute_id1" name="institute_id1"
																value=""> 
																<input type="hidden" id="userid1"
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

					<div class="row">
				
					
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="card-style mb-30">
								<div id="dec_div">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<h5 class="text-medium mb-10">Declaration/Undertaking</h5>
											<div class="custom-choose-one">
												<div class="input-style-form-check_block check-multi-list">
													<div class="form-check checkbox-style">
														<input type="checkbox" id="Declaration" name="Declaration"
															autocomplete="off" maxlength="25"
															class="form-check-input"> <input type="hidden"
															id="hiddenUpdate" name="hiddenUpdate"
															class="form-control autocomplete" value="0"> <label
															class="check-list" for="Declaration">I hereby
															declare that information furnished above is true and
															correct in every respect.</label>
															 <label
															class="check-list" for="Declaration"> Once you click on <b class="simple-link">Submit Data to NCISM</b> after that you would not be able to add/update any details.</label>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="btn-bottom">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<ul class="buttons-group mainbtn">
													<li><a id="finale_submit"
														class="main-btn info-btn btn-hover disabled btnsave">Submit Data to NCISM</a></li>
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
											<strong>Info!</strong> Data Submitted to NCISM , for View
											details click on View Data Button
										</div>
										<div class="btn-bottom">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><a href="StudentDetailsReport_Url"
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



		<c:url value="Exp_Excel" var="searchUrl" />
		<form:form action="${searchUrl}" method="post" id="search2"
			name="search2" modelAttribute="comd1">
			<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
		</form:form>

	</div>
</section>





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
	$(document).ready(function() {
		
		$("#category_id1 option[value='15']").remove();
		$("#category_id1 option[value='16']").remove();

		datepicketDate('upload_date');

		$("#upload_date").datepicker("option", "maxDate", new Date());

		$("#excel_div").hide();

		$("#freez_msg_div").hide();

		// 		if('${msg}' != ""){
		// 			alert('${msg}');
		// 		}

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

	// 	function getFileNameWithExt(event) {
	// 			var u_file1 = $("#u_file1").val();
	// 			var lastDot = u_file1.lastIndexOf('.');
	// 			var fileName = u_file1.substring(0, lastDot);
	// 			var ext = u_file1.substring(lastDot + 1);
	// 	}
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

	function setdegreeval() {
		$("#degree_hid").val($("#degree").val());
	}

	// start csp
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

						document.getElementById('Upload').onclick = function() {
							UploadExcel();
						};

						document.getElementById('Fillform').onclick = function() {
							fillform();
						};

						document.getElementById('degree').onchange = function() {
							setdegreeval();
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

						document.getElementById('btn-save').onclick = function() {
							return Validation();
						};

						document.getElementById('btnExport').onclick = function() {
							getExcel();
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

	// end csp
</script>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		datepicketDate('dob1');
		$("#dob1").datepicker("option", "maxDate", new Date());
		
		datepicketDate('fees_date1');
		$("#fees_date1").datepicker("option", "maxDate", new Date());

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
			$("#vadBtndiv").hide();
			$("#freez_msg_div").show();
		}

	});

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
							
							
							if(j[i][7] != null && j[i][7] != ""){
								var dob = j[i][7].substring(0, 10);
								dob = dob.split("-")[2] + "/" + dob.split("-")[1]
										+ "/" + dob.split("-")[0];
								$("#dob" + ind).val(dob);
							}
							
							
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

							////// Rajdip Change
							
							if(j[i][22] != null && j[i][22] != ""){
								var dofp = j[i][22].substring(0, 10);
								dofp = dofp.split("-")[2] + "/" + dofp.split("-")[1]
										+ "/" + dofp.split("-")[0];
								$("#fees_date" + ind).val(dofp);
							}
							
							if(j[i][23] != null && j[i][23] != ""){
							$("#hid_fees_receipt"+ind).val(j[i][23]);
							$("#feeview"+ind).show();
							$("#receiptid"+ind).val(j[i][17]);
							}
							//////////////////////
							
							$("#Migrated_Students_remove" + ind).show();

							if (i == (j.length - 1)) {
								$("#Migrated_Students_add" + ind).show();
							}
// 							debugger;
							
							var yr = j[i][21].split("-")[0];
							var mo = j[i][21].split("-")[1];
							var dy = j[i][21].split("-")[2];
							
							const x = new Date(yr,mo,dy);
// 							const y = new Date().toLocaleDateString();
							
// 							var cyr = y.split("/")[2];
// 							var cmo = y.split("/")[1];
// 							var cdy = y.split("/")[0];
							
							const y2 = new Date(2023,03,05);
							
							if(x < y2){
								$("#Migrated_Students_remove" + ind).hide();
								$("#Migrated_Students_add" + ind).hide();
								$("#family_save" + ind).hide();
								if (i == (j.length - 1)) {
									$("#Migrated_Students_add" + ind).show();
								}
							}

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
		// 		 $("#Migrated_Students_remove"+R).hide();
		att = parseInt($("#family_saveh1").val()) + 1;
		att = parseInt(R) + 1;

		if (att <= parseInt($("#san_st").text())) {

			$("input#family_saveh1").val(att);//current serial No
			$('table#family_table')
			.append(
					"<tr id='tr_sibling1"+att+"'>"
							+ "<td class='min-width'>"
							+ "	<div class='lead'>"
							+ "			<div class='lead-text'>" + "				<p>"
							+ att
							+ "</p>"
							+ "			</div>"
							+ "		</div>"
							+ "	</td>"

							+ "	<td>"
							+ "		<div class='select-style-1 d-none'>"
							+ "			<div class='select-position'>"
							+ "				<select class='form-control form-control-lg'"
							+ "					name='name_of_institute"+att+"' id='name_of_institute"+att+"'>"
							+ "					<option value='0'>-- Select --</option><c:forEach var='item' items='${getapp_instituteNameList}' varStatus='num'>"
							+ "						<option value='${item[0]}' name='${item[1]}'>${item[1]}</option></c:forEach></select>"
							+ "			</div>"
							+ "		</div>"
							+ "		<p><label class='table-label'>Institute Name:</label><span id='p_inst"+att+"'></span></p>"
							+ "		<p><label class='table-label'>Institute ID:</label><span id='p_instid"+att+"'></span></p>"
							+ "		<div class='select-style-1 d-none'>"
							+ "			<div class='select-position'>"
							+ "				<select name='state_id"+att+"' id='state_id"+att+"'>"
							+ "					<option value='0'>-- Select --</option><c:forEach var='item' items='${getMedStateName}'	varStatus='num'>"
							+ "						<option value='${item[0]}' name='${item[1]}'>${item[1]}</option></c:forEach></select>"
							+ "			</div>"
							+ "		</div>"
							+ "		<p><label class='table-label'>State:</label><span id='p_state"+att+"'></span></p>"
							+ "	</td>"

							+ "	<td>"
							+ "		<div class='row justify-content-center'>"
							+ "			<!-- <div class='col-12 col-sm-12 col-md-12 col-lg-6'>"
							+ "				<div class='input-style-1'>"
							+ "					<label class='table-label'>Applicant Name</label> <input"
		+"						type='text' id='cand_name"+att+"' name='cand_name"+att+"'"
		+"						placeholder='Enter Name of Applicant' maxlength='30'"
		+"						autocomplete='off'>"
							+ "				</div>"
							+ "			</div> -->"
							+ "			<div class='col-12 col-sm-12 col-md-12 col-lg-12'>"
							+ "				<div class='input-style-1'>"
							+ "					<label class='table-label'>First Name</label> <input"
		+"						type='text' id='cand_name"+att+"' name='cand_name"+att+"'"
		+"						placeholder='Enter First Name' maxlength='30'"
		+"						autocomplete='off'>"
							+ "				</div>"
							+ "			</div>"
							+ "			<div class='col-12 col-sm-12 col-md-12 col-lg-12'>"
							+ "				<div class='input-style-1'>"
							+ "					<label class='table-label'>Middle Name & Last Name</label> <input"
		+"						type='text' id='last_name"+att+"' name='last_name"+att+"'"
		+"						placeholder='Enter Middle Name & Last Name' maxlength='50'"
		+"						autocomplete='off'>"
							+ "				</div>"
							+ "			</div>"

							+ "		</div>"
							+ "	</td>"

							+ "	<td>"
							+ "		<div class='row justify-content-center'>"
							+ "			<div class='col-12 col-sm-12 col-md-12 col-lg-12'>"
							+ "				<div class='input-style-1'>"
							+ "					<label class='table-label'>Mother Name</label> <input"
		+"						type='text' id='mother_name"+att+"' name='mother_name"+att+"'"
		+"						placeholder='Enter Mother Name' maxlength='30'"
		+"						autocomplete='off'>"
							+ "				</div>"
							+ "			</div>"
							+ "			<div class='col-12 col-sm-12 col-md-12 col-lg-12'>"
							+ "				<div class='input-style-1'>"
							+ "					<label class='table-label'>Father Name</label> <input"
		+"						type='text' id='father_name"+att+"' name='father_name"+att+"'"
		+"						placeholder='Enter Father Name' maxlength='30'"
		+"						autocomplete='off'>"
							+ "				</div>"
							+ "			</div>"
							+ "		</div>"
							+ "	</td>"

							+ "	<td>"
							+ "		<div class='row justify-content-center'>		"
							+ "			<div class='col-12 col-sm-12 col-md-12 col-lg-12'>"
							+ "				<div class='input-style-2'>"
							+ "					<label class='table-label'>Date of birth</label> <input"
		+"						type='text' id='dob"+att+"' name='dob"+att+"'"
		+"						placeholder='DD/MM/YYYY' autocomplete='off'>"
							+ "				</div>"
							+ "			</div>"
							+ "			<div class='col-12 col-sm-12 col-md-12 col-lg-12'>"
							+ "				<div class='input-style-1'>"
							+ "					<label class='table-label'>E-mail ID</label> <input"
		+"						type='text' id='email"+att+"' name='email"+att+"'"
		+"						placeholder='Enter Email Id' maxlength='70'"
		+"						autocomplete='off'>"
							+ "				</div>"
							+ "			</div>"
							+ "		</div>"
							+ "	</td>"

							+ "	<td>"
							+ "		<div class='row justify-content-center'>"
							+ "				<div class='col-12 col-sm-12 col-md-12 col-lg-12'>"
							+ "				<div class='input-style-1'>"
							+ "					<label class='table-label'>Application No</label>"
							+ "					<input type='text' id='neet_application_no"+att+"'"
		+"					name='neet_application_no"+att+"' maxlength='12'"
		+"					autocomplete='off'"
		+"					placeholder='Enter NEET Application No'>"
							+ "				</div>"
							+ "			</div>"
							+ "			<div class='col-12 col-sm-12 col-md-12 col-lg-12'>"
							+ "				<div class='input-style-1'>"
							+ "				<label class='table-label'>Roll No</label>"
							+ "				<input type='text' id='neet_roll_no"+att+"'"
		+"				name='neet_roll_no"+att+"' maxlength='10'"
		+"				autocomplete='off' minlength='12' maxlength='12'"
		+"				placeholder='Enter NEET Roll No'>"
							+ "			</div>"
							+ "		</div>"

							+ "	</div>"
							+ "</td>"

							+ "<td>"
							+ "	<div class='row justify-content-center'>"
							+ "		<div class='col-12 col-sm-12 col-md-12 col-lg-12'>"
							+ "			<div class='input-style-1'>"
							+ "				<label class='table-label'>All India Rank</label>"
							+ "				<input type='text' id='neet_rank"+att+"' name='neet_rank"+att+"'"
		+"				minlength='7' maxlength='7' class='form-control'"
		+"				autocomplete='off'"
		+"				placeholder='Enter NEET All India Rank'>"
							+ "			</div>"
							+ "		</div>"
							+ "		<div class='col-12 col-sm-12 col-md-12 col-lg-6'>"
							+ "			<div class='input-style-1'>"
							+ "				<label class='table-label'>Percentile</label>"
							+ "				<input type='text' id='neet_percentile"+att+"'"
		+"				name='neet_percentile"+att+"' maxlength='5' minlength='5'"
		+"				autocomplete='off'"
		+"				placeholder='Enter NEET Percentile'>"
							+ "			</div>"
							+ "		</div>"
							+ "		<div class='col-12 col-sm-12 col-md-12 col-lg-6'>"
							+ "			<div class='input-style-1'>"
							+ "				<label class='table-label'>Obtained Marks</label>"
							+ "				<input type='text' id='neet_marks"+att+"' name='neet_marks"+att+"'"
		+"				maxlength='3' minlength='3' autocomplete='off'"
		+"				placeholder='Enter NEET Marks'>"
							+ "			</div>"
							+ "		</div>"
							+ "	</div>"
							+ "</td>"

							+ "<td>"
							+ "<div class='row justify-content-center'>"
							+ "	<div class='col-12 col-sm-12 col-md-12 col-lg-12'>"
							+ "		<div class='select-style-1'>"
							+ "			<label class='table-label'>Quota</label>"
							+ "			<div class='select-position'>"
							+ "				<select name='quota_id"+att+"' id='quota_id"+att+"'>"
							+ "					<option value='0'>-- Select --</option><c:forEach var='item' items='${getQuotaList}' varStatus='num'>"
							+ "						<option value='${item.id}' name='${item.quota}'>${item.quota}</option></c:forEach></select>"
							+ "			</div>"
							+ "		</div>"
							+ "	</div>"
							+ "	<div class='col-12 col-sm-12 col-md-12 col-lg-12'>"
							+ "			<div class='select-style-1'>"
							+ "				<label class='table-label'>Category</label>"
							+ "				<div class='select-position'>"
							+ "					<select name='category_id"+att+"' id='category_id"+att+"'>"
							+ "						<option value='0'>-- Select --</option><c:forEach var='item' items='${getcategorylist}' varStatus='num'>"
							+ "							<option value='${item.id}' name='${item.category}'>${item.category}</option></c:forEach></select>"
							+ "				</div>"
							+ "			</div>"
							+ "		</div>"
							+ "	</div>"
							+ "</td>"

							+ "<td>"
							+ "	<div class='select-style-1'>"
							+ "		<label class='table-label'>Counselling Authority</label>"
							+ "		<div class='select-position'>"
							+ "			<select name='counselling_authority"+att+"'"
							+ "				id='counselling_authority"+att+"'>"
							+ "				<option value='0'>-- Select --</option><c:forEach var='item' items='${getCounselingAuthoList}' varStatus='num'>"
							+ "					<option value='${item.id}' name='${item.counseling_authority}'>${item.counseling_authority}</option></c:forEach></select>"
							+ "			</div>"
							+ "	</div>"
							+ "</td>"
							
							//// Rajdip Change
							
							+"	<td>"
							+"		<div class='row justify-content-center'>"
							+"		<div class='col-12 col-sm-12 col-md-12 col-lg-12'>"
							+"			<div class='input-style-2'>"
							+"				<label for='fees_date"+att+"' class='table-label'>Date of Fees"
							+"					Payment</label> <input type='text' id='fees_date"+att+"'"
							+"					name='fees_date"+att+"' placeholder='DD/MM/YYYY'"
							+"					autocomplete='off'>"
							+"			</div>"
							+"		</div>"
							+"		<div class='col-12 col-sm-12 col-md-12 col-lg-12'>"
							+"										<div class='input-style-1'>"
							+"				<label for='fees_receipt"+att+"' class='table-label'>Fees"
							+"					Receipt</label>" 
							+"<div class='input-group custom-input-group'><input type='file' accept='.pdf'"
							+"					id='fees_receipt"+att+"' name='fees_receipt"+att+"'"
							+"					class='form-control'>"
							+"	<ul class='buttons-group custom-d-none'"
							+"		id='feeview"+att+"'>"
							+"		<li><a"
							+"		class='main-btn dark-btn btn-hover btn-sm btnview'"
							+"		 id='view_fees_receipt"+att+"'"
							+"		title='View Receipt'> <i class='lni lni-eye'><input"
							+"				type='hidden' id='receiptid"+att+"' value=''></i>"
							+"		</a></li>"
							+"	</ul>"
							+"<div class='note-text'>"
							+"<span class='errorClass' id='receipt_path_err"+att+"'></span>"
							+"<span class='tikClass' id='receipt_path_lbltik"+att+"'></span>"
							+" </div>"
							+"					<input type='hidden'"
							+"					accept='.pdf' id='hid_fees_receipt"+att+"' name='hid_fees_receipt"+att+"'"
							+"					class='form-control' autocomplete='off'>"
							+"			</div>"
							+"		</div>"
							+"	</div>"
							+"	</td>"
							//////////////////////////////////////
							
							+ "<td class='last-sticky'>"
							+ "	<div class='action'>"
							+ "		<ul class='buttons-group mainbtn daobtn'>"
							+ "			<li><a"
							+"				class='main-btn active-btn btn-hover btn-sm btnsave'"
							+"				value='Save' title='Save' id='family_save"+att+"'><i"
							+"					class='lni lni-save'></i></a></li>"
							+"			<li class=''><a"
							+"				class='main-btn success-btn btn-hover btn-sm btnaddmore'"
							+"				value='ADD' title='Add' id='Migrated_Students_add"+att+"'><i"
							+"					class='lni lni-plus'></i></a></li>"
							+"			<li class=''><a"
							+"				class='main-btn danger-btn btn-hover btn-sm btnremove'"
							+"				value='Delete' title='Delete'"
							+"				id='Migrated_Students_remove"+att+"'><i"
							+"				class='lni lni-trash-can'></i></a></li>"
							+ "		</ul>"
							+ "	</div> "
							+ "<input type='hidden' id='savedid"
							+ att
							+ "' name='savedid"
							+ att
							+ "' value='0'>"
							+ "<input type='hidden' id='institute_id"+att+"' name='institute_id"+att+"' value=''>"
							+ "<input type='hidden' id='hid_quali"+att+"' name='hid_quali"+att+"' value='0'>"
							+ "<input type='hidden' id='userid"+att+"' name='userid"+att+"' value='0'>"
							+ "</td>" + "</tr>");
			addOnclick(att);

			$("#Migrated_Students_add" + att).hide();
			$("#Migrated_Students_remove" + att).hide();
			
			$("#category_id"+att+" option[value='15']").remove();
			$("#category_id"+att+" option[value='16']").remove();

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
						
						////  rajdip change
						
						document.getElementById('fees_date1').onclick = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById('fees_date1').onblur = function() {
							clickrecall(this, 'DD/MM/YYYY');
							validateDate_BackDate(this.value, this);
						};
						document.getElementById('fees_date1').onkeyup = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById('fees_date1').onchange = function() {
							clickrecall(this, 'DD/MM/YYYY');
							validateDate_FutureDate(this.value, this);
						};
						
						document.getElementById('view_fees_receipt1').onclick = function() {
							var gdid = document.getElementById('receiptid1').value;
								file_view(gdid,7,4);
						};
						
						document.getElementById('fees_receipt1').onchange = function() {
							pdfFileSizeValidation(this,this.value,"fees_receipt1","200kb","receipt_path_lbltik1","receipt_path_err1",this.accept)
						};
						
						//////////////////////////
						
						document.getElementById('Declaration').onclick = function() {
							declarationVal();
						};

						document.getElementById('finale_submit').onclick = function() {
							FinalSubmitFn();
						};

					});

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
		}

		datepicketDate('dob' + att);
		datepicketDate('fees_date' + att);

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
		
		////// Rajdip Change
		
		document.getElementById('fees_date' + att).onclick = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('fees_date' + att).onblur = function() {
			clickrecall(this, 'DD/MM/YYYY');
			validateDate_BackDate(this.value, this);
		};
		document.getElementById('fees_date' + att).onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('fees_date' + att).onchange = function() {
			clickrecall(this, 'DD/MM/YYYY');
			validateDate_FutureDate(this.value, this);
		};
		document.getElementById('view_fees_receipt'+ att).onclick = function() {
			var gdid = document.getElementById('receiptid'+ att).value;
				file_view(gdid,7,4);
		};
		document.getElementById('fees_receipt'+ att).onchange = function() {
			pdfFileSizeValidation(this,this.value,"fees_receipt"+ att,"200kb","receipt_path_lbltik"+ att,"receipt_path_err"+ att,this.accept)
		};
		
		//////////////////////////////
		
		
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

		// 	$('.select2').select2({
		//     	containerCssClass: "wrap"
		// 	});
		//	$("#degree").attr("readonly", true);
	
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

		//		var qualtification_idhid2= $("input#qualtification_id"+R).val();
		// 	var result = confirm("Are you sure you want to delete?");
		// 	var p_id = $("input#p_id").val();
		// 	var hid_quali1 = $("input#hid_quali"+att).val();
		// 	$.post(
		// 			"delete_child_quali?" + key + "=" + value,
		// 			{
		// 				p_id : p_id,
		// 				hid_quali1 : hid_quali1
		// 			},
		// 			function(j) {
		// 				alert(j);
		// 			});

		// 	 $("tr#tr_sibling1"+R).remove();
		// 	 att = att-1;
		// 	 $("input#count_hidden_att_name_med").val(att);
		// 	 $("#Migrated_Students_add"+att).hide();
		// 	 $("#Migrated_Students_remove"+att).show();	

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
		//	   $(".get-value").text(""+year+" Years");
		//	   $("#yrr").val(year);
		if (year < 17 || year == "" || year == "0") {
			alert("Age Should Be Greater Than 17 Years")
			$("#dob" + ind).focus();
			return false;
		}
	}

	function Save_As_Draft_Quali(ps) {
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

		if ($("input#email" + ps).val().trim() == "") {
			alert("Please Enter Email id");
			$("input#email" + ps).focus();
			return false;
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

		if ($("#fees_date" + ps).val().trim() == "" || $("#fees_date" + ps).val().trim() == "DD/MM/YYYY") {
			alert("Please Select Date of Fees Payment");
			$("#fees_date" + ps).focus();
			return false;
		}
		
		if ($("#fees_receipt" + ps).val().trim() == "") {
			alert("Please Upload Fees Receipt");
			$("#fees_receipt" + ps).focus();
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
			// 		debugger;
			if (data.err != undefined) {
				console.log(data.err);

				// 	      		 $("#hid_quali1").val(data);
				alert(data.err);
				$("#" + data.field + "" + ps).focus();
				// 	      		$("#Migrated_Students_add"+ps).show(); 
				// 		    	$("#Migrated_Students_remove"+ps).show(); 
			} else {
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
					
					if ($("#fees_receipt" + ps).val() !="") {
						$("#feeview" + ps).show();
						$("#receiptid" + ps).val(data.savedid);
					}
					
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
		//		 document.getElementById("email").innerHTML="";
		var pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		//		 var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
		if (email1.match(pattern)) {
		} else {
			alert("Please Enter Valid Email Address");
			$("#" + id).focus();
			$("#" + id).val('');
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
		function file_view(id,val,field) {
			 $("#PicturePDFId").val(id);
			 $("#val1").val(val);
			 $("#fildname1").val(field);
			 
			var pdf1="kmlFileDownloadFinalDynemic44?kmlId2="+id+"&val444="+val+"&fildname1="+field+" ";
			PDFView(pdf1,id,val,field);
			
		}	
	
		function PDFView(path1,idx,val,field){
			
			console.log(path1);
			
			$("#pdfmodelcanvas").empty();
			$("#modal-footer").empty();
			
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
										+'<li><a id="downloadbtn'+idx+'" type="button"'
										+'class="main-btn info-btn btn-hover btn-sm btndownload"><i class="lni lni-download mr-5">'
										+'</i>Download</a></li>'
										+'</ul>'
										+'</div>';


					$("#modal-footer").append(btn);
					 $("#exampleModal").modal('show');
	
	var pdfDoc = null,
    pageNum = 1,
    pageRendering = false,
    pageNumPending = null,
    scale = 1.5,
    canvas = document.getElementById('pdf_renderer'+idx),
    ctx = canvas.getContext('2d');

function renderPage(num) {
  pageRendering = true;
  pdfDoc.getPage(num).then(function(page) {
    var viewport = page.getViewport({scale: scale});
    canvas.height = viewport.height;
    canvas.width = viewport.width;

    var renderContext = {
      canvasContext: ctx,
      viewport: viewport
    };
    var renderTask = page.render(renderContext);

    renderTask.promise.then(function() {
      pageRendering = false;
      if (pageNumPending !== null) {
        renderPage(pageNumPending);
        pageNumPending = null;
      }
    });
  });

  document.getElementById('page_num'+idx).textContent = num;
}


function queueRenderPage(num) {
  if (pageRendering) {
    pageNumPending = num;
  } else {
    renderPage(num);
  }
}

function onPrevPage() {
  if (pageNum <= 1) {
    return;
  }
  pageNum--;
  queueRenderPage(pageNum);
}

document.getElementById('go_previous'+idx).addEventListener('click', onPrevPage);

 
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
	 
	 if (scale!= 1) {
		 scale -= 0.5;
	}
	    queueRenderPage(pageNum);
});

document.getElementById('downloadbtn'+idx).addEventListener('click', (e) => {
	download_file();
});


 
pdfjsLib.getDocument(path1).promise.then(function(pdfDoc_) {

	 if (pdfDoc) {
         pdfDoc.destroy();
     }
     pdfDoc = pdfDoc_;
  document.getElementById('page_count'+idx).textContent = pdfDoc.numPages;
  renderPage(pageNum);
});
	
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	

	
	
		function download_file() {
			var id = $("#PicturePDFId").val();
			var val= $("#val1").val();
			var fildname= $("#fildname1").val();
			var pdfView="kmlFileDownloadFinalDynemic44?kmlId2="+id+"&val444="+val+"&fildname1="+fildname+"";
			
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
	
	
	
	
	
</script>






