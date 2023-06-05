<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet"
	href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">

<section class="dashboard-page">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>College Staff Information</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>

								<li class="breadcrumb-item active" aria-current="page">
									College Staff Information</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->
		<div class="form-elements-wrapper custom_v_tab">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="card-style mb-30">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="inst_block simple-instruction">
									<strong>Instruction :</strong> If any of the value is not
									available or not applicable then please put it as 0
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-12 col-12">
								<div class="custom-data-value custom-data-value-form">
									<label>Institution Name :</label> <span class="value-bind">${roleloginName }</span>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-12 col-12">
								<div class="custom-data-value custom-data-value-form">
									<label>College Code :</label> <span class="value-bind">${username}</span>
								</div>

							</div>
						</div>
					</div>
				</div>
				<!-- ===========================
							College Staff Information Start
						=========================== -->
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">

					<div class="card-style mb-30">
						<div class="tabs content_h900">
							<!-- Information of Teaching Staff Start -->
							<div class="tab">
								<button class="tab-toggle" id="defaultOpen">Staff
									Information</button>
							</div>
							<div class="content content_h975">
								<h4 class="heading">Staff Information</h4>
								<form:form name="clg_staff_info" id="clg_staff_info" action=""
									method="post" class="" commandName="">
									<div class="row">
										<input type="hidden" id="hid_staff_info" name="hid_staff_info"
											value="0">
											<input type="hidden" id="basic_info_id" name="basic_info_id" value="0">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="accordion accordion-flush"
												id="accordionFlushExample1">
												<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-headingthree">
														<button class="accordion-button accordion-secondary-btn"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapsethree"
															aria-expanded="true" aria-controls="flush-collapsethree">Teaching
															Staff (UG)</button>
													</h2>
													<div id="flush-collapsethree"
														class="accordion-collapse collapse show"
														aria-labelledby="flush-headingOne"
														data-bs-parent="#accordionFlushExample1">
														<div class="accordion-body">
															<div class="row">
																<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																	<div
																		class="table-wrapper table-responsive custom-table">
																		<table class="table" id="techstaff_ug">
																			<thead>
																				<tr>
																					<th rowspan="3"><h6>
																							Sr No<strong class="mandatory">*</strong>
																						</h6></th>
																					<th rowspan="3"><h6>
																							Department<strong class="mandatory">*</strong>
																						</h6></th>
																					<th colspan="6"><h6>
																							No of Existing Teacher<strong class="mandatory">*</strong>
																						</h6></th>
																					<th rowspan="3"><h6>
																							Total<strong class="mandatory">*</strong>
																						</h6></th>
																				</tr>
																				<tr>
																					<th colspan="2"><h6>
																							Professor<strong class="mandatory">*</strong>
																						</h6></th>
																					<th colspan="2"><h6>
																							Associate Professors / Readers<strong
																								class="mandatory">*</strong>
																						</h6></th>
																					<th colspan="2"><h6>
																							Assistant Professors / Lecturers<strong
																								class="mandatory">*</strong>
																						</h6></th>
																				</tr>
																				<tr>
																					<th colspan="2"><h6>
																							Full time<strong class="mandatory">*</strong>
																						</h6></th>
																					<!-- 																					<th><h6> -->
																					<!-- 																							Guest Faculty<strong class="mandatory">*</strong> -->
																					<!-- 																						</h6></th> -->
																					<th colspan="2"><h6>
																							Full time<strong class="mandatory">*</strong>
																						</h6></th>
																					<!-- 																					<th><h6> -->
																					<!-- 																							Guest Faculty<strong class="mandatory">*</strong> -->
																					<!-- 																						</h6></th> -->
																					<th colspan="2"><h6>
																							Full time<strong class="mandatory">*</strong>
																						</h6></th>
																				</tr>
																				<!-- end table row-->
																			</thead>
																			<tbody id="">
																				${listString}
																				<!-- end table row -->
																			</tbody>
																			<tr>
																				<td colspan="2">
																					<p>Total</p>
																				</td>
																				<td colspan="2">
																					<div class="input-style-1">
																						<input type="text"
																							name="total_professor_full_time"
																							id="total_professor_full_time"
																							class="form-control"
																							placeholder="Enter Full time" value="0"
																							maxlength="20" readonly>
																					</div>
																				</td>

																				<td colspan="2">
																					<div class="input-style-1">
																						<input type="text"
																							name="total_ass_professor_full_time"
																							id="total_ass_professor_full_time"
																							class="form-control"
																							placeholder="Enter Full time" value="0"
																							maxlength="20" readonly>
																					</div>
																				</td>

																				<td colspan="2">
																					<div class="input-style-1">
																						<input type="text"
																							name="total_lect_professor_full_time"
																							id="total_lect_professor_full_time"
																							class="form-control"
																							placeholder="Enter Full time" value="0"
																							maxlength="20" readonly>
																					</div>
																				</td>
																				<td colspan="2">
																					<div class="input-style-1">
																						<input type="text" name="total_total"
																							id="total_total" class="form-control"
																							placeholder="Enter Total" value="0"
																							maxlength="20" readonly>
																					</div>
																				</td>
																			</tr>

																		</table>
																	</div>
																</div>
															</div>
															<hr>
															<div class="row">

																<div class="col-lg-4 col-md-6 col-sm-12">
																	<div class="input-style-1">
																		<label for="fname">Total Full Time<strong
																			class="mandatory">*</strong>
																		</label> <input type="text" name="total_full_time"
																			id="total_full_time" class="form-control"
																			placeholder="Total Full Time" value="0"
																			maxlength="50" readonly>
																	</div>
																</div>

																<div class="col-lg-4 col-md-6 col-sm-12">
																	<div class="input-style-1">
																		<label for="fname">Teacher/Consultant of
																			Modern Medicine<strong class="mandatory">*</strong>
																		</label> <input type="text" name="modern_medicine"
																			id="modern_medicine" class="form-control"
																			placeholder="Teacher/Consultant of Modern Medicine"
																			maxlength="100">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label for="fname">Upload Stamp Acquittance
																			Role of Teaching Staff<strong class="mandatory">*</strong>
																		</label> <input type="file" id="teaching_acquittance1"
																			name="teaching_acquittance1" class="form-control"
																			accept=".pdf"> <input type="hidden"
																			id="hid_teaching_acquittance1"
																			name="hid_teaching_acquittance1" class="form-control"
																			value="" accept=".pdf">
																		<div class="note-text">
																			<span class="errorClass"
																				id="teaching_acquittancet_lbl">${doc_path_doc1_msg}</span>
																			<span class='tikClass'
																				id="teaching_acquittance_lbltik"></span>
																		</div>
																	</div>
																</div>

															</div>
														</div>
													</div>
												</div>
												<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-headingfour">
														<button
															class="accordion-button accordion-secondary-btn collapsed"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapsefour"
															aria-expanded="false" aria-controls="flush-collapsefour">Teaching
															Staff (PG)</button>
													</h2>
													<div id="flush-collapsefour"
														class="accordion-collapse collapse"
														aria-labelledby="flush-headingTwo"
														data-bs-parent="#accordionFlushExample1">
														<div class="accordion-body">
															<div class="row">

																<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																	<div
																		class="table-wrapper table-responsive custom-table">
																		<table class="table" id="">
																			<thead>
																				<tr>
																					<th rowspan="3"><h6>
																							Sr No<strong class="mandatory">*</strong>
																						</h6></th>
																					<th rowspan="3"><h6>
																							Department<strong class="mandatory">*</strong>
																						</h6></th>
																					<th colspan="3"><h6>
																							No of Existing Teacher<strong class="mandatory">*</strong>
																						</h6></th>
																					<th rowspan="3"><h6>
																							Total<strong class="mandatory">*</strong>
																						</h6></th>
																				</tr>
																				<tr>
																					<th><h6>
																							Professor<strong class="mandatory">*</strong>
																						</h6></th>
																					<th><h6>
																							Associate Professors / Readers<strong
																								class="mandatory">*</strong>
																						</h6></th>
																					<th><h6>
																							Assistant Professors / Lecturers<strong
																								class="mandatory">*</strong>
																						</h6></th>
																				</tr>
																				<!-- end table row-->
																			</thead>
																			<tbody id="">${listStringpg}

																			</tbody>
																			<tr>
																				<td colspan="2">
																					<p>Total</p>
																				</td>
																				<td colspan="4">
																					<div class="input-style-1">
																						<input type="text" name="total_teaching_staff"
																							id="total_teaching_staff" class="form-control"
																							placeholder="Total Teaching Staff" value="0"
																							maxlength="50" readonly>
																					</div>
																				</td>
																			</tr>

																			<!-- end table row -->

																		</table>
																	</div>
																	<!-- end card -->
																</div>
																<!-- end col -->
															</div>
															<!-- end row -->
														</div>
													</div>
												</div>
												<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-headingsix">
														<button
															class="accordion-button accordion-secondary-btn collapsed"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapsesix" aria-expanded="false"
															aria-controls="flush-collapsesix">Non-Teaching
															Staff</button>
													</h2>
													<div id="flush-collapsesix"
														class="accordion-collapse collapse"
														aria-labelledby="flush-headingsix"
														data-bs-parent="#accordionFlushExample1">
														<div class="accordion-body">
															<div class="row">
																<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																	<div
																		class="table-wrapper table-responsive custom-table">
																		<table class="table" id="teaching_faculty_table">
																			<thead>
																				<tr>
																					<th><h6>Sr No</h6></th>
																					<th><h6>
																							Post<strong class="mandatory">*</strong>
																						</h6></th>
																					<th><h6>
																							Available Staff<strong class="mandatory">*</strong>
																						</h6></th>
																				</tr>
																				<!-- end table row-->
																			</thead>
																			<tbody id="">${listStringPost}


																				<!-- ==========================================TANVI============================-->

																				<tr id="tr_sibling_teaching_faculty1">

																					<td class="sr-no">
																						<p></p>
																					</td>
																					<td>
																						<div class="input-style-1">

																							<input type="hidden" id="hid_other_post1"
																								name="hid_other_post1" value="0"> <input
																								type="text" name="other_post1" id="other_post1"
																								class="form-control" placeholder="Other Post">
																						</div>
																					</td>

																					<td>
																						<div class="input-style-1">
																							<input type="text" name="other_admin_staff1"
																								id="other_admin_staff1"
																								class="form-control grand_post"
																								placeholder="Total Administrative Staff">
																							<a
																								class="main-btn success-btn btn-hover btn-sm addminusbut"
																								value="ADD" title="ADD"
																								id="add_teaching_aaa_faculty1"> <i
																								class="lni lni-plus"></i></a>
																						</div>


																					</td>

																				</tr>

																				<!-- ==========================================TANVI============================-->
																		</table>
																		<input type="hidden" id="total_extra"
																			name="total_extra" value="1">
																	</div>
																	<!-- end card -->
																</div>
																<div class="col-lg-6 col-md-6 col-sm-12">
																	<div class="input-style-1">
																		<label for="fname">Total<strong
																			class="mandatory">*</strong>
																		</label> <input type="text" name="total-staff"
																			id="total-staff" class="form-control"
																			placeholder="Total Staff" value="0" maxlength="50"
																			readonly>
																	</div>
																</div>
																<div class="col-lg-6 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label for="fname">Upload Stamp Acquittance
																			Role of Non-teaching Staff<strong class="mandatory">*</strong>
																		</label> <input type="file" id="nonteaching_acquittance1"
																			name="nonteaching_acquittance1" class="form-control"
																			accept=".pdf"> <input type="hidden"
																			id="hid_nonteaching_acquittance1"
																			name="hid_nonteaching_acquittance1"
																			class="form-control" value="" accept=".pdf">
																		<div class="note-text">
																			<span class="errorClass"
																				id="nonteaching_acquittance_lbl">${doc_path_doc1_msg}</span>
																			<span class='tikClass'
																				id="nonteaching_acquittance_lbltik"></span>
																		</div>
																	</div>
																</div>
																<!-- end col -->
															</div>
															<!-- end row -->


															<!-- end row -->
														</div>
													</div>
												</div>

												<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-headingfive">
														<button
															class="accordion-button accordion-secondary-btn collapsed"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapsefive"
															aria-expanded="false" aria-controls="flush-collapsefive">Last
															Academic Year Retired/Re-Employed/Newly Staff Details</button>
													</h2>
													<div id="flush-collapsefive"
														class="accordion-collapse collapse"
														aria-labelledby="flush-headingfive"
														data-bs-parent="#accordionFlushExample1">
														<div class="accordion-body">
															<div class="row">
																<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																	<div
																		class="table-wrapper table-responsive custom-table">
																		<table class="table" id="">
																			<thead>
																				<tr>
																					<th><h6>Sr No</h6></th>
																					<th><h6>
																							Information of Teachers<strong class="mandatory">*</strong>
																						</h6></th>
																					<th><h6>
																							Professors<strong class="mandatory">*</strong>
																						</h6></th>
																					<th><h6>
																							Associate Professors / Readers<strong
																								class="mandatory">*</strong>
																						</h6></th>
																					<th><h6>
																							Assistant Professors / Lecturers<strong
																								class="mandatory">*</strong>
																						</h6></th>
																					<th><h6>
																							Total<strong class="mandatory">*</strong>
																						</h6></th>
																				</tr>
																				<!-- end table row-->
																			</thead>
																			<tbody id="">${listStringAcademicdtls}
																			</tbody>
																		</table>
																	</div>
																</div>
																<!-- end card -->
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label for="fname">Upload Eligible Guide list
																			as approved by the University<strong
																			class="mandatory">*</strong>
																		</label> <input type="file" id="guide_list1"
																			name="guide_list1" class="form-control" accept=".pdf">
																		<input type="hidden" id="hid_guide_list1"
																			name="hid_guide_list1" class="form-control" value=""
																			accept=".pdf">
																		<div class="note-text">
																			<span class="errorClass" id="guide_list_lbl">${doc_path_doc1_msg}</span>
																			<span class='tikClass' id="guide_list_lbltik"></span>
																		</div>
																	</div>

																</div>

																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label for="fname">Upload Attendance Register
																			& biometric attendance of last academic session of
																			Teaching Staff</label> <input type="file"
																			id="teaching_attendance1" name="teaching_attendance1"
																			class="form-control" accept=".pdf"> <input
																			type="hidden" id="hid_teaching_attendance1"
																			name="hid_teaching_attendance1" class="form-control"
																			value="" accept=".pdf">
																		<div class="note-text">
																			<span class="errorClass" id="teaching_attendance_lbl">${doc_path_doc1_msg}</span>
																			<span class='tikClass'
																				id="teaching_attendance_lbltik"></span>
																		</div>

																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label for="fname">Upload Attendance Register
																			& biometric attendance of last academic session of
																			Non-teaching Staff<strong class="mandatory">*</strong>
																		</label> <input type="file" id="non_teaching_attendance1"
																			name="non_teaching_attendance1" class="form-control"
																			accept=".pdf"> <input type="hidden"
																			id="hid_non_teaching_attendance1"
																			name="hid_non_teaching_attendance1"
																			class="form-control" value="" accept=".pdf">
																		<div class="note-text">
																			<span class="errorClass"
																				id="non_teaching_attendance_lbl">${doc_path_doc1_msg}</span>
																			<span class='tikClass'
																				id="non_teaching_attendance_lbltik"></span>
																		</div>
																	</div>
																</div>
															</div>
															<!-- end col -->
														</div>
														<!-- end row -->
													</div>
												</div>
											</div>
										</div>
										<!-- end col -->
									</div>
									<!-- end row -->

									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover btnsave"
														id="staff_info_save" type="button" value="Save"></li>
													<li><a
														class="main-btn dark-btn-outline btn-hover btn-iconic-icon viewData d-none"><i
															class="lni lni-eye"></i>View</a> <input type='hidden'
														id="viewId${parent_id}" value="${institude}"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>

							</div>
							<!-- Information of Teaching Staff End -->

							<!-- Salary Information Start -->
							<div class="tab">
								<button class="tab-toggle">Salary Information</button>
							</div>
							<div class="content">
								<h4 class="heading">Salary Information</h4>
								<form:form name="" id="staff_salary_details"
									action="staff_salary_details" method="post" class=""
									commandName="">

									<div class="row">
										<input type="hidden" id="hid_salary_dtls"
											name="hid_salary_dtls" value="0">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="table-wrapper table-responsive custom-table">
												<table class="table" id="salary_infotable">
													<thead>
														<tr>
															<th><h6>Sr No</h6></th>
															<th><h6>
																	Salary Details<strong class="mandatory">*</strong>
																</h6></th>
															<th><h6>
																	Provide Appropriate Information<strong
																		class="mandatory">*</strong>
																</h6></th>
															<th><h6>Remarks/Please mention the Reason for
																	No</h6></th>
															<th><h6>
																	Attachment<strong class="mandatory">*</strong>
																</h6></th>
														</tr>
														<!-- end table row-->
													</thead>
													<tbody id="">
														<tr>
															<td class="sr-no">
																<p></p>
															</td>
															<td>
																<p>Is the mode of payment through Bank?</p> <input
																type="hidden" id="hid_payment" name="hid_payment"
																value="0"> <input type="hidden"
																name="salary_dtls" id="salary_dtls" class="form-control"
																value="Is the mode of payment through Bank?">
															</td>
															<td>
																<div class="input-style-form-check">
																	<input type="hidden" id="hid_bankpay" name=hid_bankpay
																		class="form-control" value="0">
																	<div class="form-check radio-style">

																		<input type="radio" id="bankpayYes"
																			name="bankpaycheck" class="form-check-input"
																			value="YES"> <label for="bankpayYes"
																			class="form-check-label">Yes</label>
																	</div>
																	<div class="form-check radio-style">
																		<input type="radio" id="bankpayNo" name="bankpaycheck"
																			class="form-check-input" value="NO"> <label
																			for="bankpayNo" class="form-check-label">No</label>
																	</div>
																</div>
															</td>
															<td>
																<div class="input-style-1">
																	<input type="hidden" id="hid_bankpay_remark"
																		name=hid_bankpay_remark class="form-control" value="">
																	<input type="text" name="bankpayremarks"
																		id="bankpayremarks" class="form-control"
																		placeholder="Remarks" maxlength="100">
																</div>
															</td>
															<td>
																<div class="input-style-1">
																	<input type="file" id="bankpay_attachment"
																		name="bankpay_attachment" class="form-control"
																		accept=".pdf"> <input type="hidden"
																		id="hid_bankpay_attachment1"
																		name="hid_bankpay_attachment1" value="0">
																	<div class="note-text">
																		<span class="errorClass" id="bankpay_attachment_lbl">${doc_path_doc1_msg}</span>
																		<span class='tikClass' id="bankpay_attachment_lbltik"></span>
																	</div>
																</div>
															</td>
														</tr>
														<tr>
															<td class="sr-no">
																<p></p>
															</td>
															<td>
																<p>Is GPF & EPF deducted from the salary?</p> <input
																type="hidden" id="hid_payment2" name="hid_payment2"
																value="0"> <input type="hidden"
																name="salary_dtls2" id="salary_dtls2"
																class="form-control"
																value="Is GPF & EPF deducted from the salary?">
															</td>
															<td>
																<div class="input-style-form-check">
																	<input type="hidden" id="hid_bankpay1"
																		name=hid_bankpay1 class="form-control" value="0">
																	<div class="form-check radio-style">
																		<input type="radio" id="gpfdeductYes"
																			name="gpfdeductcheck" class="form-check-input"
																			value="YES"> <label for="gpfdeductYes"
																			class="form-check-label">Yes</label>
																	</div>
																	<div class="form-check radio-style">
																		<input type="radio" id="gpfdeductNo"
																			name="gpfdeductcheck" class="form-check-input"
																			value="NO"> <label for="gpfdeductNo"
																			class="form-check-label">No</label>
																	</div>
																</div>
															</td>
															<td>
																<div class="input-style-1">
																	<input type="text" name="gpfdeductremarks"
																		id="gpfdeductremarks" class="form-control"
																		placeholder="Remarks" maxlength="100">
																</div>
															</td>
															<td>
																<div class="input-style-1">
																	<input type="file" id="gpfdeduct_attachment"
																		name="gpfdeduct_attachment" class="form-control"
																		accept=".pdf"> <input type="hidden"
																		id="hid_gpfdeduct_attachment1"
																		name="hid_gpfdeduct_attachment1" value="0">
																	<div class="note-text">
																		<span class="errorClass" id="gpfdeduct_attachment_lbl">${doc_path_doc1_msg}</span>
																		<span class='tikClass'
																			id="gpfdeduct_attachment_lbltik"></span>
																	</div>

																</div>
															</td>
														</tr>
														<tr>
															<td class="sr-no">
																<p></p>
															</td>
															<td>
																<p>Is the teachers promotion policy as per norms of
																	CCH?</p> <input type="hidden" id="hid_payment3"
																name="hid_payment3" value="0"> <input
																type="hidden" name="salary_dtls3" id="salary_dtls3"
																class="form-control"
																value="Is the teachers promotion policy as per norms of CCH?">
															</td>
															<td>
																<div class="input-style-form-check">
																	<div class="form-check radio-style">
																		<input type="radio" id="cchnormsYes"
																			name="cchnormscheck" class="form-check-input"
																			value="YES"> <label for="cchnormsYes"
																			class="form-check-label">Yes</label>
																	</div>
																	<div class="form-check radio-style">
																		<input type="radio" id="cchnormsNo"
																			name="cchnormscheck" class="form-check-input"
																			value="NO"> <label for="cchnormsNo"
																			class="form-check-label">No</label>
																	</div>
																</div>
															</td>
															<td>
																<div class="input-style-1">
																	<input type="text" name="cchnormsremarks"
																		id="cchnormsremarks" class="form-control"
																		placeholder="Remarks" maxlength="100">
																</div>
															</td>
															<td>
																<div class="input-style-1">
																	<input type="file" id="cchnorms_attachment"
																		name="cchnorms_attachment" class="form-control"
																		accept=".pdf"> <input type="hidden"
																		id="hid_cchnorms_attachment1"
																		name="hid_cchnorms_attachment1" value="0">
																	<div class="note-text">
																		<span class="errorClass" id="cchnorms_attachment_lbl">${doc_path_doc1_msg}</span>
																		<span class='tikClass' id="cchnorms_attachment_lbltik"></span>
																	</div>
																</div>
															</td>
														</tr>
														<tr>
															<td class="sr-no">
																<p></p>
															</td>
															<td>
																<p>The existing pay scale of teaching staff is as
																	per regulation enforce.</p> <input type="hidden"
																id="hid_payment4" name="hid_payment4" value="0">
																<input type="hidden" name="salary_dtls4"
																id="salary_dtls4" class="form-control"
																value="The existing pay scale of teaching staff is as per regulation enforce.">
															</td>

															<td>
																<div class="input-style-1">
																	<input type="text" name="staff-payscale"
																		id="staff-payscale" class="form-control"
																		placeholder="Pay Scale" maxlength="100">
																</div>
															</td>

															<td>
																<div class="input-style-1">
																	<input type="text" name="staff_payscale_remarks"
																		id="staff_payscale_remarks" class="form-control"
																		placeholder="Remarks" maxlength="100">
																</div>
															</td>
															<td>
																<div class="input-style-1">
																	<input type="file" id="staff_payscale_attachment"
																		name="staff_payscale_attachment" class="form-control"
																		accept=".pdf"> <input type="hidden"
																		id="hid_staff_payscale_attachment1"
																		name="hid_staff_payscale_attachment1" value="0">
																	<div class="note-text">
																		<span class="errorClass"
																			id="staff_payscale_attachment_lbl">${doc_path_doc1_msg}</span>
																		<span class='tikClass'
																			id="staff_payscale_attachment_lbltik"></span>
																	</div>
																</div>
															</td>
														</tr>
														<tr>
															<td class="sr-no">
																<p></p>
															</td>
															<td>
																<p>Pay Scale and Grade pay of Professor</p> <input
																type="hidden" id="hid_payment5" name="hid_payment5"
																value="0"> <input type="hidden"
																name="salary_dtls5" id="salary_dtls5"
																class="form-control"
																value="Pay Scale and Grade pay of Professor">

															</td>
															<td>
																<div class="input-style-1">
																	<input type="text" name="payscalegradepay"
																		id="payscalegradepay" class="form-control"
																		placeholder="Pay Scale and Grade pay of Professor"
																		maxlength="100">
																</div>
															</td>
															<td>
																<div class="input-style-1">
																	<input type="text" name="payscalegradepay_remarks"
																		id="payscalegradepay_remarks" class="form-control"
																		placeholder="Remarks" maxlength="100">
																</div>
															</td>
															<td>
																<div class="input-style-1">
																	<input type="file" id="payscalegradepay_attachment"
																		name="payscalegradepay_attachment"
																		class="form-control" accept=".pdf"> <input
																		type="hidden" id="hid_payscalegradepay_attachment1"
																		name="hid_payscalegradepay_attachment1" value="0">
																	<div class="note-text">
																		<span class="errorClass"
																			id="payscalegradepay_attachment_lbl">${doc_path_doc1_msg}</span>
																		<span class='tikClass'
																			id="payscalegradepay_attachment_lbltik"></span>
																	</div>
																</div>
															</td>
														</tr>
														<tr>
															<td class="sr-no">
																<p></p>
															</td>
															<td>
																<p>Pay Scale and Grade pay of Reader/Associate
																	Professor</p> <input type="hidden" id="hid_payment6"
																name="hid_payment6" value="0"> <input
																type="hidden" name="salary_dtls6" id="salary_dtls6"
																class="form-control"
																value="Pay Scale and Grade pay of Reader/Associate
																	Professor">
															</td>
															<td>
																<div class="input-style-1">
																	<input type="text" name="ass-pro-pay" id="ass-pro-pay"
																		class="form-control"
																		placeholder="Pay Scale and Grade pay of Reader/Associate"
																		maxlength="100">
																</div>
															</td>
															<td>
																<div class="input-style-1">
																	<input type="text" name="ass-pro-pay-remarks"
																		id="ass-pro-pay-remarks" class="form-control"
																		placeholder="Remarks" maxlength="100">
																</div>
															</td>
															<td>
																<div class="input-style-1">
																	<input type="file" id="ass_pro_payattachment"
																		name="ass_pro_payattachment" class="form-control"
																		accept=".pdf"> <input type="hidden"
																		id="hid_ass_pro_payattachment1"
																		name="hid_ass_pro_payattachment1" value="0">
																	<div class="note-text">
																		<span class="errorClass"
																			id="ass_pro_payattachment_lbl">${doc_path_doc1_msg}</span>
																		<span class='tikClass'
																			id="ass_pro_payattachment_lbltik"></span>
																	</div>
																</div>
															</td>
														</tr>
														<tr>
															<td class="sr-no">
																<p></p>
															</td>

															<td>
																<p>Pay Scale and Grade pay of Lecturer/Assistant
																	Professor</p> <input type="hidden" id="hid_payment7"
																name="hid_payment7" value="0"> <input
																type="hidden" name="salary_dtls7" id="salary_dtls7"
																class="form-control"
																value="Pay Scale and Grade pay of Lecturer/Assistant
																	Professor">
															</td>
															<td>
																<div class="input-style-1">
																	<input type="text" name="lectass-pro-pay"
																		id="lectass-pro-pay" class="form-control"
																		placeholder="Pay Scale and Grade pay of Lecturer/Assistant Professor"
																		maxlength="100">
																</div>
															</td>
															<td>
																<div class="input-style-1">
																	<input type="text" name="lectass-pro-pay-remarks"
																		id="lectass-pro-pay-remarks" class="form-control"
																		placeholder="Remarks" maxlength="100">
																</div>
															</td>
															<td>
																<div class="input-style-1">
																	<input type="file" id="lectass_pro_payattachment"
																		name="lectass_pro_payattachment" class="form-control"
																		accept=".pdf"> <input type="hidden"
																		id="hid_lectass_pro_payattachment1"
																		name="hid_lectass_pro_payattachment1" value="0">
																	<div class="note-text">
																		<span class="errorClass"
																			id="lectass_pro_payattachment_lbl">${doc_path_doc1_msg}</span>
																		<span class='tikClass'
																			id="lectass_pro_payattachment_lbltik"></span>
																	</div>
																</div>
															</td>
														</tr>

														<!-- end table row -->
													</tbody>
												</table>
											</div>
											<!-- end card -->
										</div>
										<!-- end col -->
									</div>
									<!-- end row -->


									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover btnsave"
														id="staff_salarydetails_save" type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>
							</div>
							<!-- Salary Information End -->
							<div class="tab">
								<button class="tab-toggle">Teacher Promotion</button>
							</div>
							<div class="content">
								<h4 class="heading">Teacher Promotion</h4>
								<form:form name="teacher_promo" id="teacher_promo" action=""
									method="post" class="" commandName="">
									<div class="row">
										<input type="hidden" id="hid_teacherpromo"
											name="hid_teacherpromo" value="0"> <input
											type="hidden" id="indno_library" name="indno_library"
											value="0">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="table-wrapper table-responsive custom-table">
												<table class="table" id="teacher_promo_table">
													<thead>
														<tr>
															<th><h6>Sr No</h6></th>

															<th><h6>
																	Name of Faculty<strong class="mandatory">*</strong>
																</h6></th>
															<th><h6>
																	Designation<strong class="mandatory">*</strong>
																</h6></th>
															<th><h6>
																	Upload Document<strong class="mandatory">*</strong>
																</h6></th>
															<th><h6>Action</h6></th>
														</tr>
														<!-- end table row-->
													</thead>
													<tbody id="">
														<tr id="teacher_promotion1">
															<td>
																<p>1</p>
															</td>
															<td>
																<div class="input-style-1">
																	<input type="text" name="faculty1" id="faculty1"
																		class="form-control" placeholder="Name of Faculty"
																		maxlength="100">
																</div>
															</td>

															<td>
																<div class="select-style-1">
																	<div class="select-position">
																		<select name="designation1" id="designation1"
																			class="form-control">
																			<option value="0" selected="selected">--
																				Select --</option>
																			<c:forEach var="item" items="${getDesignationList}"
																				varStatus="num">
																				<option value="${item.id}"
																					name="${item.designation}">${item.designation}</option>
																			</c:forEach>
																		</select>
																	</div>
																</div>
															</td>

															<td>
																<div class="input-style-1">
																	<input type="file" id="document1" name="document1"
																		class="form-control" accept=".pdf"> <input
																		type="hidden" id="hid_document1" name="hid_document1"
																		class="form-control" value="0">
																	<div class="note-text">
																		<span class="errorClass" id="document_lbl">${doc_path_doc1_msg}</span>
																		<span class='tikClass' id="document_lbltik"></span>
																	</div>

																</div>
															</td>
															<td class="min-width addminusbut">
																<ul class="buttons-group mainbtn daobtn action">
																	<li><a
																		class="main-btn success-btn btn-hover btn-sm btnapprove"
																		value="Save" title="SAVE"
																		id="family_save_teacher_promo1"> <i
																			class="lni lni-checkmark"></i>
																	</a></li>
																	<li><a
																		class="main-btn success-btn btn-hover btn-sm addminusbut custom-d-none"
																		value="ADD" title="ADD" id="add_teacher_promo1"> <i
																			class="lni lni-plus"></i>
																	</a></li>

																	<li><a
																		class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"
																		value="Delete" title="Delete" id="library_remove1"><i
																			class="lni lni-trash-can"></i></a></li>

																</ul>
															</td>
														</tr>
														<!-- end table row -->
													</tbody>
												</table>
												<input type="hidden" id="family_save_libraryh1"
													name="family_save_libraryh1"
													class="form-control autocomplete" value="1"> <input
													type="hidden" id="hid_teacherpromo1"
													name="hid_teacherpromo1" value="0"
													class="form-control autocomplete" autocomplete="off">
											</div>
											<!-- end card -->
										</div>
										<!-- end col -->
									</div>

									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">

												</ul>
											</div>
										</div>
									</div>
								</form:form>
							</div>
							<!-- Upload Document End -->
						</div>
					</div>
				</div>
				<!-- ===========================
							College Staff Information End
						=========================== -->
			</div>
		</div>
		<!-- end row -->
	</div>
	<!-- end container -->
</section>
<!-- components end -->
<c:url value="View_College_Staff_InfoUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="clg_staff_info_view_id">
	<input type="hidden" name="clg_staff_info_id"
		id="clg_staff_info_view_id" value="0" />
	<input type="hidden" name="statusview" id="statusview" value="0" />
</form:form>
<script type="text/javascript"
	src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>
</body>
<script type="text/javascript" nonce="${cspNonce}">
$(document)
.ready(
		function() {
			if ('${role}'=='NCH') {
				$(".viewData").addClass("d-none")
			}
			if ('${role}'=='Institute_NCH') {
				$(".viewData").removeClass("d-none")
			} 
			
			getAllSalary_Details();
			getPg_Details();
			getUg_Details();
			getNonteaching_Details();
			getAcademic_Details();
			getTeacher_Promotion();
			getNonTeaching_Staff_Details();
			$("#basic_info_id").val('${basic_info_id}');
			
			var dataparent_promotion = '${dataparent_promotion}';
			if (dataparent_promotion != "[]") {

				$("#hid_teacher_promo").val(
						'${dataparent_promotion[0].id}');
				$("#faculty").val(
						'${dataparent_promotion[0].faculty}');
				$("#designation").val(
						'${dataparent_promotion[0].designation}');
				$("#hid_document").val(
						'${dataparent_promotion[0].document}');
			} 
			
			
			
		});
//SAVE Staff Salary DETAILS---------------------------------------------------------
function Save_As_Draft_Staff_Info_Details() {
	$.ajaxSetup({
		async : false
	});
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	if ($("#teaching_acquittance1").val().trim() == "") {
		alert("Please Upload Stamp Acquittance Role of Teaching Staff in Teaching Staff UG Tab");
		$("#teaching_acquittance1").focus();
		return false;
	}
	if ($("#nonteaching_acquittance1").val().trim() == "") {
		alert("Please Upload Stamp Acquittance Role of Non-teaching Staff in Non-Teaching Staff Tab");
		$("#nonteaching_acquittance1").focus();
		return false;
	}
	
	if ($("#guide_list1").val().trim() == "") {
		alert("Please Upload Eligible Guide list as approved by the University in Last Academic Year Tab ");
		$("#guide_list1").focus();
		return false;
	}
	if ($("#teaching_attendance1").val().trim() == "") {
		alert("Please Upload Attendance Register & biometric attendance of last academic session of Teaching Staff in Last Academic Year Tab.");
		$("#teaching_attendance1").focus();
		return false;
	}
	if ($("#non_teaching_attendance1").val().trim() == "") {
		alert("Please Upload Attendance Register & biometric attendance of last academic session of Non-teaching Staff in Last Academic Year Tab");
		$("#non_teaching_attendance1").focus();
		return false;
	}

	var form_data = new FormData(document
			.getElementById("clg_staff_info"));
	$.ajax({
		url : 'College_Staff_UG_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j) {
		if (j > 0) {
			$("#hid_staff_info").val(j);
			
			alert("Data Save Sucessfully");
			location.reload();
		} else {
			alert(j);
			location.reload();
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}
//SAVE Staff Salary DETAILS------------------------------------------------------------------------------
function Save_As_Draft_Staff_Salary_Info_Details() {
	$.ajaxSetup({
		async : false
	});
// 	var hid_staff_info = $("#hid_staff_info").val();
// 	if(hid_staff_info == "0"){
		
// 		alert("Please Save Staff Information First");
	
// 		return false;
// 	}

	var bankpaycheck = $("input[name='bankpaycheck']:checked").val();
	if( bankpaycheck == null ){
		alert("Please Select Bank Pay Check Yes/No in Salary Information Tab");
		return false;
   	}
	if ($("#bankpay_attachment").val().trim() == "") {
		alert("Please Upload Mode of Payment Document in Salary Information Tab");
		$("#bankpay_attachment").focus();
		return false;
	}
	var gpfdeductcheck = $("input[name='gpfdeductcheck']:checked").val();
	if( gpfdeductcheck == null ){
		alert("Please Select GPF & EPF deducted from the Salary Check Yes/No in Salary Information Tab");
		return false;
   	}
	if ($("#gpfdeduct_attachment").val().trim() == "") {
		alert("Please Upload GPF & EPF deducted Document in Salary Information Tab");
		$("#gpfdeduct_attachment").focus();
		return false;
	}
	
	var cchnormscheck = $("input[name='cchnormscheck']:checked").val();
	if( cchnormscheck == null ){
		alert("Please Select Teachers Promotion Policy as per norms of CCH Check Yes/No in Salary Information Tab");
		return false;
   	}

	if ($("#cchnorms_attachment").val().trim() == "") {
		alert("Please Upload Teachers Promotion Document in Salary Information Tab");
		$("#cchnorms_attachment").focus();
		return false;
	}
	if ($("#staff-payscale").val().trim() == "") {
		alert("Please Enter pay scale of teaching staff in Salary Information Tab");
		$("#staff-payscale").focus();
		return false;
	}

	if ($("#staff_payscale_attachment").val().trim() == "") {
		alert("Please Upload Pay Scale of Teaching Staff Document in Salary Information Tab");
		$("#staff_payscale_attachment").focus();
		return false;
	}
	
	var res = CheckNullorBlank('payscalegradepay');
	if (res !== "true") {
		alert(res + "Pay Scale and Grade pay of Professor in Salary Information Tab");
		$('#payscalegradepay').focus();
		return false;
	}
	if ($("#payscalegradepay_attachment").val().trim() == "") {
		alert("Please Upload Pay Scale and Grade pay of Professor Document in Salary Information Tab");
		$("#payscalegradepay_attachment").focus();
		return false;
	}
	var res = CheckNullorBlank('ass-pro-pay');
	if (res !== "true") {
		alert(res + "Pay Scale and Grade pay of Reader/Associate Professor in Salary Information Tab");
		$('#ass-pro-pay').focus();
		return false;
	}
	if ($("#ass_pro_payattachment").val().trim() == "") {
		alert("Please Upload Pay Scale and Grade pay of Reader/Associate Professor Document in Salary Information Tab");
		$("#ass_pro_payattachment").focus();
		return false;
	}
	var res = CheckNullorBlank('lectass-pro-pay');
	if (res !== "true") {
		alert(res + "Pay Scale and Grade pay of Lecturer/Assistant Professor in Salary Information Tab");
		$('#lectass-pro-pay').focus();
		return false;
	}
	if ($("#lectass_pro_payattachment").val().trim() == "") {
		alert("Please Upload Pay Scale and Grade pay of Lecturer/Assistant Professor Document in Salary Information Tab.");
		$("#lectass_pro_payattachment").focus();
		return false;
	}
	
	var form_data = new FormData(document
			.getElementById("staff_salary_details"));
	$.ajax({
		url : 'Staff_Salary_Infor_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j) {
		if (j > 0) {
			$("#hid_salary_dtls").val(j);
			
			alert("Data Save Sucessfully");
			location.reload();
		} else {
			alert(j);
// 			location.reload();
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}



//FETCH PG Details----------------------------------------------------------
function getPg_Details() {
	$.ajaxSetup({
		async : false
	});
	$.post("getAllPg_Details?" + key + "=" + value, {
		
	}, function(j) {
		
		for (var i = 0; i < j.length; i++) {
			
			
			$("#hid_staff_info_pg"+j[i].department_id).val(j[i].id);
			$("#homoeopathicug_professor_full_time"+j[i].department_id).val(j[i].full_time_prof);
			$("#homoeopathicug_professor_guest_faculty"+j[i].department_id).val(j[i].asso_guest_faculty);
			$("#ass_professor_lect"+j[i].department_id).val(j[i].assis_lect);
			$("#total_teaching_staff").val(j[i].total_teaching_staff);
			$("#homoeopathicug_total"+j[i].department_id).val(j[i].each_total);
		}
		
	});
}
//FETCH UG Details----------------------------------------------------------
function getUg_Details() {
	$.ajaxSetup({
		async : false
	});
	$.post("getAllUg_Details?" + key + "=" + value, {
		
	}, function(j) {
		
		for (var i = 0; i < j.length; i++) {
			
			$("#hid_staff_info"+j[i].department_id).val(j[i].id);
			$("#anatomy_professor_full_time"+j[i].department_id).val(j[i].prof_full_time);
			$("#anatomy_ass_professor_full_time"+j[i].department_id).val(j[i].associate_prof_full_time);
			$("#anatomy_lect_professor_full_time"+j[i].department_id).val(j[i].assistant_prof_full_time);
			$("#anatomy_total"+j[i].department_id).val(j[i].total);
			$("#total_professor_full_time").val(j[i].total_professor_full_time);
			$("#total_ass_professor_full_time").val(j[i].total_ass_professor_full_time);
			$("#total_lect_professor_full_time").val(j[i].total_lect_professor_full_time);
			$("#total_total").val(j[i].total_total);
			$("#total_full_time").val(j[i].total_full_time);
			$("#modern_medicine").val(j[i].consultant_modern_medi);
			$("#hid_teaching_acquittance1").val(j[i].teaching_acquittance);
			
		}
		
	});
}

//FETCH Non Teaching Details----------------------------------------
function getNonteaching_Details() {
	$.ajaxSetup({
		async : false
	});
	
	$.post("getAllNonteaching_Details?" + key + "=" + value, {
		
	}, function(j) {
		
		for (var i = 0; i < j.length; i++) {
			
			$("#hid_staff_info_nonteaching"+j[i].post_id).val(j[i].id);
			$("#admin_staff"+j[i].post_id).val(j[i].available_staff);
			$("#total-staff").val(j[i].total_staff);
			$("#hid_nonteaching_acquittance1").val(j[i].nonteaching_acquittance);
			
		}
		
	});
}

//FETCH Academic Details-------------------------------------------------
function getAcademic_Details() {
	$.ajaxSetup({
		async : false
	});
	
	$.post("getAllAcademic_Details?" + key + "=" + value, {
		
	}, function(j) {
		
		for (var i = 0; i < j.length; i++) {
			
			$("#hid_staff_info_academic"+j[i].teacher_info_id).val(j[i].id);
			$("#ret-professer"+j[i].teacher_info_id).val(j[i].professor);
			$("#ret-reader"+j[i].teacher_info_id).val(j[i].associate_prof);
			$("#ret-lecturers"+j[i].teacher_info_id).val(j[i].assistant_prof);
			$("#retired_total"+j[i].teacher_info_id).val(j[i].total);
			$("#hid_guide_list1").val(j[i].guide_list);
			$("#hid_teaching_attendance1").val(j[i].teaching_attendance);
			$("#hid_non_teaching_attendance1").val(j[i].non_teaching_attendance);
			
		}
		
	});
}


function GrandSum(){
	var grantotal=0;
	const collection = document.getElementsByClassName("grand");
	for (let i = 0; i < collection.length; i++) {
	  if(collection[i].value!=''){
		  grantotal=grantotal+ parseInt(collection[i].value)
	  }
	}
	$("#total_teaching_staff").val(grantotal);
	
}
function GrandSum_post(){
	
	var grantotal=0;
	const collection = document.getElementsByClassName("grand_post");
	for (let i = 0; i < collection.length; i++) {
	  if(collection[i].value!=''){
		  grantotal=grantotal+ parseInt(collection[i].value)
	  }
	}
	$("#total-staff").val(grantotal);
	
}
document.addEventListener('DOMContentLoaded',function() {

			document.getElementById('staff_info_save').onclick = function() {
				return Save_As_Draft_Staff_Info_Details();
			};
			document.getElementById('faculty1').onkeypress = function() {
				return onlyAlphabetsStringSpace(event, this);
			};
		
			document.getElementById('bankpayremarks').onkeypress = function() {
				return onlyAlphaNumericwithslash(event, this);
			};
			document.getElementById('gpfdeductremarks').onkeypress = function() {
				return onlyAlphaNumericwithslash(event, this);
			};
			document.getElementById('cchnormsremarks').onkeypress = function() {
				return onlyAlphaNumericwithslash(event, this);
			};
			document.getElementById('staff_payscale_remarks').onkeypress = function() {
				return onlyAlphaNumericwithslash(event, this);
			};
			document.getElementById('payscalegradepay_remarks').onkeypress = function() {
				return onlyAlphaNumericwithslash(event, this);
			};
			document.getElementById('ass-pro-pay-remarks').onkeypress = function() {
				return onlyAlphaNumericwithslash(event, this);
			};
			document.getElementById('lectass-pro-pay-remarks').onkeypress = function() {
				return onlyAlphaNumericwithslash(event, this);
			};
			document.getElementById('payscalegradepay').onkeypress = function() {
				return onlyAlphaNumericwithslash(event, this);
			};
			document.getElementById('ass-pro-pay').onkeypress = function() {
				return onlyAlphaNumericwithslash(event, this);
			};
			document.getElementById('lectass-pro-pay').onkeypress = function() {
				return onlyAlphaNumericwithslash(event, this);
			};
		
			document.getElementById('staff_salarydetails_save').onclick = function() {
				return Save_As_Draft_Staff_Salary_Info_Details();
			};
		
			
			document.getElementById('bankpayYes').onclick = function() {
				bank_pay_check();
			};
			document.getElementById('bankpayNo').onclick = function() {
				bank_pay_check();
			};
			
			document.getElementById('gpfdeductYes').onclick = function() {
				bank_pay_check();
			};
			document.getElementById('gpfdeductNo').onclick = function() {
				bank_pay_check();
			};
			document.getElementById('cchnormsYes').onclick = function() {
				bank_pay_check();
			};
			document.getElementById('cchnormsNo').onclick = function() {
				bank_pay_check();
			};
			document.getElementById('teaching_attendance1').onchange = function() {
				return pdfFileSizeValidation(this,this.value,"teaching_attendance1","200kb","teaching_attendance_lbltik","teaching_attendance_lbl",this.accept)
			};
			document.getElementById('non_teaching_attendance1').onchange = function() {
				return pdfFileSizeValidation(this,this.value,"non_teaching_attendance1","200kb","non_teaching_attendance_lbltik","non_teaching_attendance_lbl",this.accept)
			};
			document.getElementById('teaching_acquittance1').onchange = function() {
				return pdfFileSizeValidation(this,this.value,"teaching_acquittance1","200kb","teaching_acquittance_lbltik","teaching_acquittancet_lbl",this.accept)
			};
			document.getElementById('nonteaching_acquittance1').onchange = function() {
				return pdfFileSizeValidation(this,this.value,"nonteaching_acquittance1","200kb","nonteaching_acquittance_lbltik","nonteaching_acquittance_lbl",this.accept)
			};
			document.getElementById('guide_list1').onchange = function() {
				return pdfFileSizeValidation(this,this.value,"guide_list1","200kb","guide_list_lbltik","guide_list_lbl",this.accept)
			};

			document.getElementById('document1').onchange = function() {
				return pdfFileSizeValidation(this,this.value,"document1","200kb","document_lbltik","document_lbl",this.accept)
			}; 
			
			
			document.getElementById('add_teacher_promo1').onclick = function() {
				return formOpen_teacher_promo(1);
			};

			document.getElementById('library_remove1').onclick = function() {
				return Delete_teacher_promo(1);
			};

			document.getElementById('family_save_teacher_promo1').onclick = function() {
				return Save_As_Draft_teacher_promo(1);
			};
			
			
			document.getElementById('bankpay_attachment').onchange = function() {
				return pdfFileSizeValidation(this,this.value,"bankpay_attachment","200kb","bankpay_attachment_lbltik","bankpay_attachment_lbl",this.accept)
			};
			
			document.getElementById('gpfdeduct_attachment').onchange = function() {
				return pdfFileSizeValidation(this,this.value,"gpfdeduct_attachment","200kb","gpfdeduct_attachment_lbltik","gpfdeduct_attachment_lbl",this.accept)
			};
			
			document.getElementById('cchnorms_attachment').onchange = function() {
				return pdfFileSizeValidation(this,this.value,"cchnorms_attachment","200kb","cchnorms_attachment_lbltik","cchnorms_attachment_lbl",this.accept)
			};
			
			document.getElementById('staff_payscale_attachment').onchange = function() {
				return pdfFileSizeValidation(this,this.value,"staff_payscale_attachment","200kb","staff_payscale_attachment_lbltik","staff_payscale_attachment_lbl",this.accept)
			};
			
			document.getElementById('payscalegradepay_attachment').onchange = function() {
				return pdfFileSizeValidation(this,this.value,"payscalegradepay_attachment","200kb","payscalegradepay_attachment_lbltik","payscalegradepay_attachment_lbl",this.accept)
			};
			
			document.getElementById('ass_pro_payattachment').onchange = function() {
				return pdfFileSizeValidation(this,this.value,"ass_pro_payattachment","200kb","ass_pro_payattachment_lbltik","ass_pro_payattachment_lbl",this.accept)
			};
			document.getElementById('lectass_pro_payattachment').onchange = function() {
				return pdfFileSizeValidation(this,this.value,"lectass_pro_payattachment","200kb","lectass_pro_payattachment_lbltik","lectass_pro_payattachment_lbl",this.accept)
			};
			
			<c:forEach var="j" items="${department_list}" varStatus="num">
			document.getElementById('anatomy_professor_full_time'+${j[0]}).onkeypress = function() {
				return isNumberKey0to9(event, this);
			};
			</c:forEach>
			
			<c:forEach var="j" items="${department_list}" varStatus="num">
			document.getElementById('anatomy_ass_professor_full_time'+${j[0]}).onkeypress = function() {
				return isNumberKey0to9(event, this);
			};
			</c:forEach>
			
			
			<c:forEach var="j" items="${department_list}" varStatus="num">
			document.getElementById('anatomy_lect_professor_full_time'+${j[0]}).onkeypress = function() {
				return isNumberKey0to9(event, this);
			};
			</c:forEach>
			
			
			<c:forEach var="j" items="${getAllCourse_PG}" varStatus="num">
			document.getElementById('homoeopathicug_professor_full_time'+${j[0]}).onkeypress = function() {
				return isNumberKey0to9(event, this);
			};
			</c:forEach>
		
			<c:forEach var="j" items="${getAllCourse_PG}" varStatus="num">
			document.getElementById('ass_professor_lect'+${j[0]}).onkeypress = function() {
				return isNumberKey0to9(event, this);
			};
			</c:forEach>
			
			<c:forEach var="j" items="${academic_list}" varStatus="num">
			document.getElementById('ret-professer'+${j[0]}).onkeypress = function() {
				return isNumberKey0to9(event, this);
			};
			</c:forEach>
			
			<c:forEach var="j" items="${academic_list}" varStatus="num">
			document.getElementById('ret-reader'+${j[0]}).onkeypress = function() {
				return isNumberKey0to9(event, this);
			};
			</c:forEach>
			
			<c:forEach var="j" items="${academic_list}" varStatus="num">
			document.getElementById('ret-lecturers'+${j[0]}).onkeypress = function() {
				return isNumberKey0to9(event, this);
			};
			</c:forEach>
			
			
			document.querySelectorAll('.grand').forEach((items, index) => {
				items.addEventListener('blur', event => {
					GrandSum();
				})
				
			});
			document.querySelectorAll('.grand_post').forEach((items, index) => {
				items.addEventListener('blur', event => {
					GrandSum_post();
				})
			});
			
			<c:forEach var="j" items="${department_list}" varStatus="num">
			document.getElementById('anatomy_professor_full_time'+${j[0]}).onblur = function() { 
				anatomy_firsttotal();
				total_professor_full_time();
				total_total_all();
				full_time_prof_Total();
			
			};
			</c:forEach>
			
			<c:forEach var="j" items="${department_list}" varStatus="num">
			document.getElementById('anatomy_ass_professor_full_time'+${j[0]}).onblur = function() {
				anatomy_firsttotal();
				total_ass_full_time();
				total_total_all();
				full_time_prof_Total();
				
			};
			</c:forEach>
			
			
			<c:forEach var="j" items="${department_list}" varStatus="num">
			document.getElementById('anatomy_lect_professor_full_time'+${j[0]}).onblur = function() {
				anatomy_firsttotal();
				total_lect_fulltime();
				total_total_all();
				full_time_prof_Total();				
			};
			</c:forEach>
			<c:forEach var="j" items="${department_list}" varStatus="num">
			document.getElementById('anatomy_lect_professor_full_time'+${j[0]}).onblur = function() {
				anatomy_firsttotal();
				total_lect_fulltime();
				total_total_all();
				full_time_prof_Total();			
			};
			</c:forEach>
			
			<c:forEach var="j" items="${getAllCourse_PG}" varStatus="num">
			
			document.getElementById('homoeopathicug_professor_full_time'+${j[0]}).onblur = function() {
				homoeopathi_total();
				
			};
			document.getElementById('homoeopathicug_professor_guest_faculty'+${j[0]}).onblur = function() {
				homoeopathi_total();
				
			};
			document.getElementById('ass_professor_lect'+${j[0]}).onblur = function() {
				homoeopathi_total();
				
			};
			</c:forEach>
			
			<c:forEach var="j" items="${academic_list}" varStatus="num">
			
			document.getElementById('ret-professer'+${j[0]}).onblur = function() {
				academic_total();
				
			};
			document.getElementById('ret-reader'+${j[0]}).onblur = function() {
				academic_total();
				
			};
			document.getElementById('ret-lecturers'+${j[0]}).onblur = function() {
				academic_total();
				
			};
			</c:forEach>
			
			document.getElementById('add_teaching_aaa_faculty1').onclick = function() {
				return formOpen_Teaching_Faculty(1);
			};
			
			
		});
		
//--------------------------------Start Total of Full_time------------------------------------------------
function total_professor_full_time() { 
	total_fulltime_prof=0;
	<c:forEach var="j" items="${department_list}" varStatus="num">
		if($("#anatomy_professor_full_time"+${j[0]}).val()!=''){
			total_fulltime_prof =total_fulltime_prof+parseInt( $("#anatomy_professor_full_time"+${j[0]}).val());
		}
	</c:forEach>
	$("#total_professor_full_time").val(total_fulltime_prof);

// 	Total_Income_lastyear();
}
//--------------------------------End Total of Full_time------------------------------------------------



//--------------------------------Start Total Associate of Full Time------------------------------------------------
	function total_ass_full_time() {
		
		total_fulltime_asso=0;
		<c:forEach var="j" items="${department_list}" varStatus="num">
			if($("#anatomy_ass_professor_full_time"+${j[0]}).val()!=''){
				total_fulltime_asso =total_fulltime_asso+parseInt( $("#anatomy_ass_professor_full_time"+${j[0]}).val());
			}
		</c:forEach>
		$("#total_ass_professor_full_time").val(total_fulltime_asso);
		
		
	}
 // 	Total_Income_lastyear();

//--------------------------------End Total Associate of Full Time--------------------------------------------------

//--------------------------------Start Total lect of Full Time---------------------------------------------------
function total_lect_fulltime() {

	total_fulltime_lect=0;
	<c:forEach var="j" items="${department_list}" varStatus="num">
		if($("#anatomy_lect_professor_full_time"+${j[0]}).val()!=''){
			total_fulltime_lect =total_fulltime_lect+parseInt( $("#anatomy_lect_professor_full_time"+${j[0]}).val());
		}
	</c:forEach>
	$("#total_lect_professor_full_time").val(total_fulltime_lect);
	
	
}


function total_total_all() {
	total_all=0;
	<c:forEach var="j" items="${department_list}" varStatus="num">
		if($("#anatomy_total"+${j[0]}).val()!=''){
			total_all =total_all+parseInt( $("#anatomy_total"+${j[0]}).val());
		}
	</c:forEach>
	$("#total_total").val(total_all);
	
	
}
//--------------------------------End Total lect of  Full Time----------------------------------------------------

//--------------------------------Start Total anatomy_total--1---------------------------------------------
function anatomy_firsttotal() {
// 	total_fulltime_prof=0;
// 	total_fulltime_prof1=0;
// 	abc = 0;
	<c:forEach var="j" items="${department_list}" varStatus="num">
	
	total_fulltime_prof=0;
	total_fulltime_prof2=0;
	total_fulltime_prof4=0;
	abc = 0;
	$("#anatomy_professor_full_time"+${j[0]}).val();
	$("#anatomy_ass_professor_full_time"+${j[0]}).val();
	$("#anatomy_lect_professor_full_time"+${j[0]}).val();
	
		if($("#anatomy_professor_full_time"+${j[0]}).val()!=''){
			total_fulltime_prof =total_fulltime_prof + parseInt( $("#anatomy_professor_full_time"+${j[0]}).val());
		}
		
		if($("#anatomy_ass_professor_full_time"+${j[0]}).val()!=''){
			total_fulltime_prof2 =total_fulltime_prof2 + parseInt( $("#anatomy_ass_professor_full_time"+${j[0]}).val());
		}
		
		if($("#anatomy_lect_professor_full_time"+${j[0]}).val()!=''){
			total_fulltime_prof4 =total_fulltime_prof4 + parseInt( $("#anatomy_lect_professor_full_time"+${j[0]}).val());
		}
		var abc = total_fulltime_prof + total_fulltime_prof2 + total_fulltime_prof4 ;
		$("#anatomy_total"+${j[0]}).val(abc);
	</c:forEach>
	
}
//--------------------------------End Total anatomy_total---1----------------------------------------------
function full_time_prof_Total() {
// 	<c:forEach var="j" items="${department_list}" varStatus="num">
	
	total_fulltime=0;
	total_fulltime1=0;
	total_fulltime2=0;
	abc = 0;
	
	$("#total_professor_full_time").val();
	$("#total_ass_professor_full_time").val();
	$("#total_lect_professor_full_time").val();
	
		if($("#total_professor_full_time").val()!=''){
			total_fulltime =total_fulltime + parseInt( $("#total_professor_full_time").val());
		}
		if($("#total_ass_professor_full_time").val()!=''){
			total_fulltime1 =total_fulltime1 + parseInt( $("#total_ass_professor_full_time").val());
		}
		if($("#total_lect_professor_full_time").val()!=''){
			total_fulltime2 =total_fulltime2 + parseInt( $("#total_lect_professor_full_time").val());
		}
		
		var abc = total_fulltime + total_fulltime1 + total_fulltime2;
		$("#total_full_time").val(abc);
// 	</c:forEach>
	
}


function homoeopathi_total() {

// 	total_fulltime_prof=0;
// 	total_fulltime_prof1=0;
// 	abc = 0;
	<c:forEach var="j" items="${getAllCourse_PG}" varStatus="num">
	
	total_fulltime_prof=0;
	total_fulltime_prof1=0;
	total_fulltime_prof2=0;
	abc = 0;
	$("#homoeopathicug_professor_full_time"+${j[0]}).val();
	$("#homoeopathicug_professor_guest_faculty"+${j[0]}).val();
	$("#ass_professor_lect"+${j[0]}).val();
	
		if($("#homoeopathicug_professor_full_time"+${j[0]}).val()!=''){
			total_fulltime_prof =total_fulltime_prof + parseInt( $("#homoeopathicug_professor_full_time"+${j[0]}).val());
		}
		if($("#homoeopathicug_professor_guest_faculty"+${j[0]}).val()!=''){
			total_fulltime_prof1 =total_fulltime_prof1 + parseInt( $("#homoeopathicug_professor_guest_faculty"+${j[0]}).val());
		}
		if($("#ass_professor_lect"+${j[0]}).val()!=''){
			total_fulltime_prof2 =total_fulltime_prof2 + parseInt( $("#ass_professor_lect"+${j[0]}).val());
		}
		
		var abc = total_fulltime_prof + total_fulltime_prof1 + total_fulltime_prof2;
		$("#homoeopathicug_total"+${j[0]}).val(abc);
	</c:forEach>
	
}

function academic_total() {

// 	total_fulltime_prof=0;
// 	total_fulltime_prof1=0;
// 	abc = 0;
	<c:forEach var="j" items="${academic_list}" varStatus="num">
	
	total_academic=0;
	total_academic1=0;
	total_academic2=0;
	abc = 0;
	$("#ret-professer"+${j[0]}).val();
	$("#ret-reader"+${j[0]}).val();
	$("#ret-lecturers"+${j[0]}).val();
	
		if($("#ret-professer"+${j[0]}).val()!=''){
			total_academic =total_academic + parseInt( $("#ret-professer"+${j[0]}).val());
		}
		if($("#ret-reader"+${j[0]}).val()!=''){
			total_academic1 =total_academic1 + parseInt( $("#ret-reader"+${j[0]}).val());
		}
		if($("#ret-lecturers"+${j[0]}).val()!=''){
			total_academic2 =total_academic2 + parseInt( $("#ret-lecturers"+${j[0]}).val());
		}
		
		var abc = total_academic + total_academic1 + total_academic2;
		$("#retired_total"+${j[0]}).val(abc);
	</c:forEach>
	
}

function bank_pay_check(){
	
	var bankpay_check = $('input:radio[name=bankpaycheck]:checked').val();
	if(bankpay_check == "1"){
        $("div#hid_bankpay").show();
	}
	else if(bankpay_check == "2"){
        $("div#hid_bankpay").hide();
	}
	
	
	var gpfdeductcheck = $('input:radio[name=gpfdeductcheck]:checked').val();
	if(gpfdeductcheck == "1"){
        $("div#hid_bankpay1").show();
	}
	else if(gpfdeductcheck == "2"){
        $("div#hid_bankpay1").hide();
	}
	
	var cchnormscheck = $('input:radio[name=cchnormscheck]:checked').val();
	if(cchnormscheck == "1"){
        $("div#hid_bankpay2").show();
	}
	else if(cchnormscheck == "2"){
        $("div#hid_bankpay2").hide();
	}
}

//FETCH Salary DETAILS------------------------------------------------------------
function getAllSalary_Details() {
	$.ajaxSetup({
		async : false
	});
	
	$.post("getAllSalary_Details?" + key + "=" + value, {
		
	}, function(j) {
		$("#hid_salary_dtls").val(j[0][0]);
		$("#bankpayremarks").val(j[0][2]);
		$("#gpfdeductremarks").val(j[0][4]);
		$("#cchnormsremarks").val(j[0][6]);
		$("#staff-payscale").val(j[0][7]);
		$("#staff_payscale_remarks").val(j[0][8]);
		$("#payscalegradepay").val(j[0][9]);
		$("#payscalegradepay_remarks").val(j[0][10]);
		$("#ass-pro-pay").val(j[0][11]);
		$("#ass-pro-pay-remarks").val(j[0][12]);
		$("#lectass-pro-pay").val(j[0][13]);
		$("#lectass-pro-pay-remarks").val(j[0][14]);
		$("#hid_bankpay_attachment1").val(j[0][15]);
		$("#hid_gpfdeduct_attachment1").val(j[0][16]);
		$("#hid_cchnorms_attachment1").val(j[0][17]);
		$("#hid_staff_payscale_attachment1").val(j[0][18]);
		$("#hid_payscalegradepay_attachment1").val(j[0][19]);
		$("#hid_ass_pro_payattachment1").val(j[0][20]);
		$("#hid_lectass_pro_payattachment1").val(j[0][21]);

		var bankpaycheck = j[0][1];
		$("input[name='bankpaycheck'][value='"+bankpaycheck+"']").prop('checked', true);
		
		var gpfdeductcheck = j[0][3];
		$("input[name='gpfdeductcheck'][value='"+gpfdeductcheck+"']").prop('checked', true);
		
		var cchnormscheck = j[0][5];
		$("input[name='cchnormscheck'][value='"+cchnormscheck+"']").prop('checked', true);
		
	
	});
}
	
	
	document.querySelectorAll('.viewData').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var hid = document.getElementById('viewId${parent_id}').value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				ViewData(hid);
			} else {
				return false;
			}
		})
	});

	function ViewData(id){

		$("#clg_staff_info_view_id").val(id);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
	}
	//SAVE COLLEGE Bank Details-----------------------------------

	function formOpen_teacher_promo(R) {

		$("#teacher_promo_table").show();

		att = 0;

		$("#add_teacher_promo" + R).hide();
		$("#library_remove" + R).hide();
		att = parseInt($("#family_save_libraryh1").val()) + 1;
		att = parseInt(R) + 1;
// 		alert(att);

		if (att < 21) {

			$("input#family_save_libraryh1").val(att);//current serial No
			$("table#teacher_promo_table")
					.append(
							'<tr id="teacher_promotion1'+att+'">'

									+ '<td class="min-width"><div class="lead"><div class="lead-text"><p>'
									+ att
									+ '</p></div></div></td>'
									+ '<td><div class="input-style-1"><input type="text" id="faculty'+att+'" name="faculty'+att+'" class="form-control"' 
					+'autocomplete="off" placeholder="Name of Faculty"></div></td>'


									+ '<td><div class="select-style-1"><div class="select-position"><select id="designation'+att+'" name="designation'+att+'" class=""><option value="0">--Select--</option><c:forEach var="item" items="${getDesignationList}" varStatus="num"><option value="${item.id}" name="${item.designation}">${item.designation}</option>'
									+'</c:forEach></select></div></div></td>'

					+'<td><div class="input-style-1"><input type="file" id="document'+att+'" name="document'+att+'" class="form-control" accept=".pdf">'
					+'<input type=hidden id="hid_document'+att+'" name="hid_document'+att+'" class="form-control" value="">'
					+'<div class="note-text"><span class="errorClass" id="document_lbl'+att+'">${doc_path_doc1_msg}</span>'
				 	+'<span class="tikClass" id="document_lbltik'+att+'"></span></div></div></td>'
				 	
									+ '<td class="addminusbut"><ul class="buttons-group mainbtn daobtn action"><li><a class="main-btn success-btn btn-hover btn-sm btnapprove"'
					+'value="Save" title="SAVE" id="family_save_library'+att+'"><i class="lni lni-checkmark"></i></a></li>'
									+ '<li><a class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none" value="ADD" title="ADD" id="add_teacher_promo'+att+'">'
									+ '<i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"'
					+'value="Delete" title="Delete" id="library_remove'+att+'">'
									+ '<input type="hidden" id="family_save_libraryh'+att+'" name="family_save_libraryh'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
									+ '<input type="hidden" id="hid_teacherpromo'+att+'" name="hid_teacherpromo'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
									+ '<i class="lni lni-trash-can"></i></a></li></ul></td>'
									+ '</tr>');


			addOnclick_bankacct(att);

			$('#btnmodel' + att).click(function() {
				$('#modelWindow').modal('show');
				dynamicTable(att, 1);
			});

			$('#closeModel').click(function() {
				$('#modelWindow').modal('hide');
			});
			$('#closeModel2').click(function() {
				$('#modelWindow').modal('hide');
			});

			$("#family_save_libraryh1").val(att);

		} else {
			alert("You can not enter more than twenty times");
			if (att == 21) {
				att = att - 1;
				$("#rp_id_remove" + att).show();
			}
		}
	}

	function addOnclick_bankacct(att) {
		document.getElementById('add_teacher_promo' + att).onclick = function() {
			return formOpen_teacher_promo(att);
		};
		document.getElementById('library_remove' + att).onclick = function() {
			return Delete_teacher_promo(att);
		};
		document.getElementById('family_save_library' + att).onclick = function() {
			Save_As_Draft_teacher_promo(att);
		};
		document.getElementById('faculty'+ att).onkeypress = function() {
			return onlyAlphaNumericwithslash(event, this);
		};
		document.getElementById('document'+ att).onchange = function() {
			return pdfFileSizeValidation(this,this.value,"document"+ att,"200kb","document_lbltik"+ att,"document_lbl"+ att,this.accept)
		};
	}
	
	
	function Save_As_Draft_teacher_promo(ps) {
		$.ajaxSetup({
			async : false
		});
// 		var hid_staff_info = $("#hid_staff_info").val();
// 		if(hid_staff_info == "0"){
			
// 			alert("Please Save Staff Information First");
		
// 			return false;
// 		}
		var res = CheckNullorBlank('faculty' + ps);
		if (res !== "true") {
			alert(res + "Name of Faculty in Teacher Promotion Tab");
			$('#faculty' + ps).focus();
			return false;
		}
		if ($("#designation" + ps).val().trim() == "0") {
			alert("Please Select Designation in Teacher Promotion Tab");
			$("#designation").focus();
			return false;
		}
	
		if ($("#document"+ ps).val().trim() == "") {
			alert("Please Upload Document in Teacher Promotion Tab");
			$("#document").focus();
			return false;
		}

			$("#indno_library").val(ps);
		var form_data = new FormData(document.getElementById("teacher_promo"));
		$.ajax({
			url : 'College_Teacher_Promotion_Action?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(j) {
					if(j>0){
						$("#hid_teacherpromo"+ps).val(j);
						alert("Data Saved Successfully");
						$("#add_teacher_promo" + ps).show();
						$("#library_remove" + ps).show();
		          }
		          else{
		        	  alert(j);
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});

	}

	function Delete_teacher_promo(R) {

		if (confirm('Are you sure you want to delete?')) {

			var hid_teacherpromo = $('#hid_teacherpromo' + R).val();

			$.post("delete_teacher_promo?" + key + "=" + value, {
				hid_teacherpromo : hid_teacherpromo
			}, function(j) {
				alert(j);
			});

			$("tr#teacher_promotion1" + R).remove();
			R = R - 1;
			$("input#count_hidden_att_name_med").val(att);
			$("#add_teacher_promo" + R).show();
			$("#library_remove" + R).show();
		} else {
			return false;
		}
	}

	function getTeacher_Promotion() {

		$.ajaxSetup({
			async : false
		});

		var ser = 0;

		$.post("getTeacher_Promotion?" + key + "=" + value, {

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				ser = i + 1;

				if (j.length > 0) {

					if (ser > 1) {

						$("#add_teacher_promo" + (ser - 1)).click();
					}

					$("#hid_teacherpromo" + ser).val(j[i].id);
					$("#faculty" + ser).val(j[i].faculty);
// 					alert(j[i].faculty);
					$("#designation" + ser).val(j[i].designation);
					$("#hid_document" + ser).val(j[i].document);
					$("#add_teacher_promo" + ser).show();
					$("#library_remove" + ser).show();
				}
			}
		});
	}
	
	
	function formOpen_Teaching_Faculty(R){
		
		 $("#teaching_faculty_table").show();
			 
			 att=0;
	 		 $("#add_teaching_aaa_faculty"+R).hide();
//	 		 $("#delete_teaching_faculty"+R).hide();
			 att=parseInt($("#save_teaching_facultyh1").val())+1;
			 att= parseInt(R)+1;
			
			 if(att < 21){
						 
				 $("input#save_teaching_facultyh1").val(att);//current serial No
				 $("table#teaching_faculty_table").append('<tr id="tr_sibling_teaching_faculty1'+att+'">'

						+'<td class="min-width sr-no"><div class="lead"><div class="lead-text"><p></p></div></div></td>' 
						
						+'<td>'
						+' <div class="input-style-1">'
						+'<input type="hidden" id="hid_other_post'+att+'" name="hid_other_post'+att+'" value="0">'
						+' <input type="text" name="other_post'+att+'" id="other_post'+att+'"'
						+' class="form-control" placeholder="Other Post">'
						+' </div>'
						+'</td>'

						+'<td>'
						+' <div class="input-style-1">'
						+'<input type="text" name="other_admin_staff'+att+'" id="other_admin_staff'+att+'"'
						+'class="form-control grand_post" placeholder="Total Administrative Staff">'
						+' <a class="main-btn success-btn btn-hover btn-sm addminusbut"'
						+'	value="ADD" title="ADD" id="add_teaching_aaa_faculty'+att+'"> <i'
						+'	class="lni lni-plus"></i></a>'
						+' </div>'
						+'</td>'
						 
						+ '</tr>');
				 document.querySelectorAll('.grand_post').forEach((items, index) => {
						items.addEventListener('blur', event => {
							GrandSum_post();
						})
					});
				 $('.UpperClassName').keyup(function() {
						this.value = this.value.toUpperCase();
					});
				 addOnclick_Teaching_Faculty(att);
				 $("#total_extra").val(att);
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
				$("#save_teaching_facultyh1").val(att);
				
			 }else{
						alert("You can not enter more than hundred times");
						 if ( att == 100){
							 att = att-1; 
							 $("#rp_id_remove"+att).show();
						 }	   
				}
//			 			hidesystem_degree(att);
		}
	
	function addOnclick_Teaching_Faculty(att) {
		
		
		document.getElementById('add_teaching_aaa_faculty'+att).onclick = function() {
			return formOpen_Teaching_Faculty(att);
		};
	}
	
	
	function getNonTeaching_Staff_Details() {
		
		$.ajaxSetup({
			async : false
		});
		
		var ser = 0;

		$.post("getNonTeaching_Staff_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				ser = i + 1;

				if (j.length > 0) {

					if (ser > 1) {
						
						$("#add_teaching_aaa_faculty" + (ser - 1)).click();
					}
					
					$("#hid_other_post" + ser).val(j[i].id);
					$("#other_post" + ser).val(j[i].post);
					$("#other_admin_staff" + ser).val(j[i].available_staff);
				}
			}
			GrandSum_post();
		});
	}
</script>




