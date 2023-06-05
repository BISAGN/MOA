<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet"
	href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<body>
	<section class="dashboard-page">
		<div class="container-fluid">
			<!-- title-wrapper start -->
			<div class="title-wrapper pt-30">
				<div class="row align-items-center">
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="title mb-30">
							<h2>Add Staff</h2>
						</div>
					</div>
					<!-- end col -->
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="breadcrumb-wrapper mb-30">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="#0">Dashboard</a></li>

									<li class="breadcrumb-item active" aria-current="page">
										Add Staff</li>
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
								<div class="col-lg-6 col-md-6 col-sm-12 col-12">
									<div class="custom-data-value custom-data-value-form">
										<label>Institution Name :</label> <span class="value-bind">${login_name}</span>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 col-12">
									<div class="custom-data-value custom-data-value-form">
										<label>College Code :</label> <span class="value-bind">${dataforinstnc[0][1]}</span>
									</div>

								</div>
							</div>
						</div>
					</div>
					<!--    ===========================
		Add Staff Start
===========================     -->
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card-style mb-30">
							<div class="tabs">
								<!-- 	Add Teaching Faculty Start -->
								<div class="tab">
									<button class="tab-toggle" id="defaultOpen">Teaching
										Faculty</button>
								</div>
								<div class="content">
									<h4 class="heading">Teaching Faculty</h4>
									<form:form name="teaching_faculty" id="teaching_faculty" action="" method="post" class=""
										commandName="">
										<div class="row mt-4">
										
										<input type="hidden" id="indno_teaching_faculty" name="indno_teaching_faculty" value="0">
										<input type="hidden" id="basic_info_id" name="basic_info_id" value="0">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="inst_block simple-instruction">
													<strong>Instruction :</strong> Upload educational,
													experience, appointment, joining, ESI, PF, Form 16, account
													statement in single zip/pdf
												</div>
											</div>
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table custom-table-v2 hybrid-table">
													<table class="table table-striped" id="teaching_faculty_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>
																		Full Name<span class="mandatory">*</span>
																	</h6></th>
																<th><h6>
																		Appointment Detail<span class="mandatory">*</span>
																	</h6></th>
																<th><h6>
																		Employee Detail<span class="mandatory">*</span>
																	</h6></th>
																<th><h6>
																		Document Detail<span class="mandatory">*</span>
																	</h6></th>
																<th><h6>
																		Registration Detail<span class="mandatory">*</span>
																	</h6></th>
																<th class="last-sticky">Action</th>
															</tr>
														</thead>
														<tbody id="">
															<tr id="tr_sibling_teaching_faculty1">
																<td>
																	<p>1</p>
																</td>
																<td>
																	<div class="row justify-content-center">
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="buttonmerge table-label">First
																					Name</label>
																			</div>
																			<div class="groupadd">
																				<div class="select-style-1 ">
																					<div class="select-position">
																						<select name="prefix_id1"
																							id="prefix_id1" class="form-control">
																							<option value="1">Mr</option>
																							<option value="2">Ms</option>
																						</select>
																					</div>
																				</div>
																				<div
																					class="input-style-1 form-group buttonmergeinput">
																					<input type="text" id="first_name1"
																						name="first_name1" class="form-control"
																						placeholder="First Name" maxlength="100">
																				</div>
																			</div>
																		</div>

																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="table-label">Middle Name</label> <input
																					type="text" id="middle_name1" name="middle_name1"
																					class="form-control" placeholder="Middle Name" maxlength="100">
																			</div>
																		</div>

																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="table-label">Last Name</label> <input
																					type="text" id="last_name1" name="last_name1"
																					class="form-control" placeholder="Last Name" maxlength="100">
																			</div>
																		</div>

																	</div>
																</td>

																<td>
																	<div class="row justify-content-center">
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-2">
																				<label class="table-label">Date of
																					Appointment
																				</label> <input type="text" name="date_of_appoinment1" id="date_of_appoinment1"
																					class="form-control-sm form-control effect-9 "
																					aria-required="true" value="DD/MM/YYYY">
																			</div>
																		</div>
																		<div class="select-style-1 ">
																		<label class="table-label">Nature of Appointment</label>
																			<div class="select-position">
																				<select name="nature_of_appoinment1" id="nature_of_appoinment1" class="form-control">
																				<option value="0" selected="selected">--Select--</option>
																				<c:forEach var="item" items="${getNature_of_Appoinment}" varStatus="num">
																					<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																				</c:forEach>
																				</select>
																			</div>
																		</div>
																		<!-- <div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="table-label">Nature of
																					Appointment
																				</label> <input type="text" id="nature_of_appoinment1"
																					name="nature_of_appoinment1" class="form-control"
																					placeholder="Nature of Appointment">
																			</div>

																		</div> -->
																	</div>
																</td>

																<td>
																	<div class="row justify-content-center">
																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-1">
																				<label class="table-label">Employee ID</label> <input type="text"
																					name="emp_id1" id="emp_id1"
																					class="form-control" placeholder="Employee ID">
																			</div>
																		</div>
																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-1">
																				<label class="table-label">Department
																				</label> <input type="text" name="emp_department1"
																					id="emp_department1" class="form-control"
																					placeholder="Working Department" maxlength="100">
																			</div>
																		</div>
																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-1">
																				<label class="table-label">Qualification</label> <input type="text"
																					id="emp_qualification1" name="emp_qualification1"
																					placeholder="Qualification" maxlength="100">
																			</div>
																		</div>
																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-1">
																				<label class="table-label">Pay Scale</label> <input type="text"
																					id="emp_pay_scale1" name="emp_pay_scale1"
																					placeholder="Pay Scale" maxlength="10">
																			</div>
																		</div>

																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-1">
																				<label class="table-label">Designation</label> <input type="text"
																					name="emp_designation1" id="emp_designation1"
																					class="form-control" placeholder="Designation" maxlength="100">
																			</div>
																			
																		</div>
																		
																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-form-check_block">
																				<div class="form-check radio-style">
																				<input type="radio" id="ug_check1" name="ug_check_name1" class="form-check-input" value="UG"> 
																				<label for="ug" class="form-check-label">UG</label>
																				<input type="radio" id="pg_check1" name="ug_check_name1" class="form-check-input" value="PG"> 
																				<label for="pg" class="form-check-label">PG</label>
																				</div>
																			</div>
																		</div>
																	</div>
																</td>
																
																<td>
																	<div class="row justify-content-center">
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="table-label">PAN</label> <input type="text"
																					name="pan_no1" id="pan_no1"
																					class="form-control UpperClassName" placeholder="PAN">
																			</div>
																		</div>
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="table-label">Aadhaar No.</label> <input type="text"
																					name="aadhar_no1" id="aadhar_no1"
																					class="form-control" placeholder="Aadhaar No." maxlength="12">
																			</div>

																		</div>
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																			<label class="table-label">Attachment</label> 
																				<input type="file" id="teaching_attachment1" name="teaching_attachment1"
																				class="form-control" accept=".pdf,.zip">
																				<input type="hidden" id="hid_teaching_attachment1" name="hid_teaching_attachment1"
																				class="form-control" value="">
																		<div class="note-text">
																			<span class="errorClass" id="teaching_attachment_lbl1">${doc_path_doc1_msg}</span>
																			<span class='tikClass' id="teaching_attachment_lbltik1"></span>
																		</div>
																			</div>
																		</div>

																	</div>
																</td>
																
																<td>
																	<div class="row justify-content-center">
																		<div class="select-style-1 ">
																		<label class="table-label">Registration Authority</label>
																			<div class="select-position">
																				<select name="reg_authority1" id="reg_authority1" class="form-control">
																				<option value="0" selected="selected">--Select--</option>
																				<option value="MCI/NMC">MCI/NMC</option>
																				<option value="CCH/NCH">CCH/NCH</option>
																				</select>
																			</div>
																		</div>
																	</div>
																	
																	<div class="row justify-content-center">
																		<div class="select-style-1 ">
																		<label class="table-label">Registration Type</label>
																			<div class="select-position">
																				<select name="reg_type1" id="reg_type1" class="form-control">
																				<option value="0" selected="selected">--Select--</option>
																				<option value="State">State</option>
																				<option value="Central">Central</option>
																				</select>
																			</div>
																		</div>
																	</div>
																	
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="table-label">Registration Number</label> <input type="text"
																					name="reg_no1" id="reg_no1"
																					class="form-control UpperClassName" placeholder="Registration Authority" maxlength="12">
																			</div>
																	</div>
																</td>
																<td class="last-sticky">
																	<div class="action">
																		<ul class="buttons-group mainbtn daobtn">
																			<li><a
																				class="main-btn active-btn btn-hover btn-sm btnsave"
																				title="Save" id="save_teaching_faculty1"><i
																					class="lni lni-save"></i></a></li>
																			<li class=""><a
																				class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none"
																				title="Add" id="add_teaching_faculty1"><i
																					class="lni lni-plus"></i></a></li>
																			<li class=""><a
																				class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"
																				title="Delete"
																				id="delete_teaching_faculty1"><i
																					class="lni lni-trash-can"></i></a></li>
																		</ul>
																		<!-- 																								style="display: none;" -->
																	</div>
																</td>
															</tr>
															<!-- end table row -->
														</tbody>
													</table>
													
													<input type="hidden" id="save_teaching_facultyh1" name="save_teaching_facultyh1"  value="1">
													<input type="hidden" id="hid_teaching_faculty1" name="hid_teaching_faculty1" value="0">
													
													
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<ul class="buttons-group mainbtn">
													<!-- <li><input
														class="main-btn success-btn btn-hover btnsubmit"
														type="button" value="Submit"></li> -->
														<li><a class="main-btn dark-btn-outline btn-hover btn-iconic-icon viewData d-none"><i
															class="lni lni-eye"></i>View</a>
															<input type='hidden' id="viewId${parent_id}" value="${institude}"></li>
												</ul>
											</div>
										</div>
									</form:form>
								</div>
								<!-- 	Add Teaching Faculty End -->

								<!-- 	Add Guest Faculty Start -->
								<div class="tab">
									<button class="tab-toggle">Guest Faculty</button>
								</div>
								<div class="content">
									<h4 class="heading">Guest Faculty</h4>
									<form:form name="" id="guest_faculty" action="guest_faculty" method="post" class=""
										commandName="">
										<div class="row mt-4">
										
										<input type="hidden" id="indno_guest_faculty" name="indno_guest_faculty" value="0">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="inst_block simple-instruction">
													<strong>Instruction :</strong> Upload educational,
													 ESI, PF, Form 16, account
													statement in single zip/pdf
												</div>
											</div>
										<div class="table-wrapper table-responsive custom-table custom-table-v2 hybrid-table">
													<table class="table table-striped" id="guest_faculty_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>
																		Full Name<span class="mandatory">*</span>
																	</h6></th>
																<th><h6>
																		Appointment Detail<span class="mandatory">*</span>
																	</h6></th>
																<th><h6>
																		Employee Detail<span class="mandatory">*</span>
																	</h6></th>
																<th><h6>
																		Document Detail<span class="mandatory">*</span>
																	</h6></th>
																<th class="last-sticky">Action</th>
															</tr>
														</thead>
														<tbody id="">
															<tr id="tr_sibling_guest_faculty1">
																<td>
																	<p>1</p>
																</td>
																<td>
																	<div class="row justify-content-center">
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="buttonmerge table-label">First
																					Name</label>
																			</div>
																			<div class="groupadd">
																				<div class="select-style-1 ">
																					<div class="select-position">
																						<select name="guest_prefix_id1" id="guest_prefix_id1" class="form-control">
																							<option value="0" selected="selected">Select</option>
																							<c:forEach var="item" items="${getPrefixList}" varStatus="num">
																								<option value="${item.id}" name="${item.prefix}">${item.prefix}</option>
																							</c:forEach>
																						</select>
																					</div>
																				</div>
																				<div
																					class="input-style-1 form-group buttonmergeinput">
																					<input type="text" id="guest_first_name1"
																						name="guest_first_name1" class="form-control"
																						placeholder="First Name" maxlength="100">
																				</div>
																			</div>
																		</div>

																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="table-label">Middle Name</label> <input
																					type="text" id="guest_middle_name1" name="guest_middle_name1"
																					class="form-control" placeholder="Middle Name" maxlength="100">
																			</div>
																		</div>

																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="table-label">Last Name</label> <input
																					type="text" id="guest_last_name1" name="guest_last_name1"
																					class="form-control" placeholder="Last Name" maxlength="100">
																			</div>
																		</div>

																	</div>
																</td>

																<td>
																	<div class="row justify-content-center">
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-2">
																				<label class="table-label">Date of
																					Appointment
																				</label> <input type="text" name="guest_date_of_appoinment1" id="guest_date_of_appoinment1"
																					class="form-control-sm form-control effect-9 "
																					aria-required="true" value="DD/MM/YYYY">
																			</div>
																		</div>
																		<div class="select-style-1 ">
																		<label class="table-label">Nature of Appointment</label>
																			<div class="select-position">
																				<select name="guest_nature_of_appoinment1" id="guest_nature_of_appoinment1" class="form-control">
																				<option value="0" selected="selected">--Select--</option>
																				<c:forEach var="item" items="${getNature_of_Appoinment}" varStatus="num">
																					<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																				</c:forEach>
																				</select>
																			</div>
																		</div>
																		<!-- <div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="table-label">Nature of
																					Appointment
																				</label> <input type="text" id="guest_nature_of_appoinment1"
																					name="guest_nature_of_appoinment1" class="form-control"
																					placeholder="Nature of Appointment">
																			</div>

																		</div> -->
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																			<label class="table-label">Appoinment Letter</label> 
																				<input type="file" id="appoinment_letter1" name="appoinment_letter1"
																				class="form-control" accept=".pdf">
																				<input type="hidden" id="hid_appoinment_letter1" name="hid_appoinment_letter1"
																				class="form-control" value="">
																		<div class="note-text">
																			<span class="errorClass" id="appoinment_letter_lbl1">${doc_path_doc1_msg}</span>
																			<span class='tikClass' id="appoinment_letter_lbltik1"></span>
																		</div>
																			</div>
																		</div>
																	</div>
																</td>

																<td>
																	<div class="row justify-content-center">
																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-1">
																				<label class="table-label">Employee ID</label> <input type="text"
																					name="guest_emp_id1" id="guest_emp_id1"
																					class="form-control" placeholder="Employee ID" maxlength="20">
																			</div>
																		</div>
																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-1">
																				<label class="table-label">Department
																				</label> <input type="text" name="guest_emp_department1"
																					id="guest_emp_department1" class="form-control"
																					placeholder="Working Department" maxlength="100">
																			</div>
																		</div>
																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-1">
																				<label class="table-label">Qualification</label> <input type="text"
																					id="guest_emp_qualification1" name="guest_emp_qualification1"
																					placeholder="Qualification" maxlength="100">
																			</div>
																		</div>
																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-1">
																				<label class="table-label">Pay Scale</label> <input type="text"
																					id="guest_emp_pay_scale1" name="guest_emp_pay_scale1"
																					placeholder="Pay Scale" maxlength="10">
																			</div>
																		</div>

																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-1">
																				<label class="table-label">Designation</label> <input type="text"
																					name="guest_emp_designation1" id="guest_emp_designation1"
																					class="form-control" placeholder="Designation" maxlength="100">
																			</div>
																		</div>
																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-1">
																			<label class="table-label">Attachment</label> 
																				<input type="file" id="guest_teaching_attachment1" name="guest_teaching_attachment1"
																				class="form-control" accept=".pdf,.zip">
																				<input type="hidden" id="hid_guest_teaching_attachment1" name="hid_guest_teaching_attachment1"
																				class="form-control" value="">
																		<div class="note-text">
																			<span class="errorClass" id="guest_teaching_attachment_lbl1">${doc_path_doc1_msg}</span>
																			<span class='tikClass' id="guest_teaching_attachment_lbltik1"></span>
																		</div>
																			</div>
																		</div>
																	</div>
																</td>

																<td>
																	<div class="row justify-content-center">
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="table-label">PAN</label> <input type="text"
																					name="guest_pan_no1" id="guest_pan_no1"
																					class="form-control UpperClassName" placeholder="PAN">
																			</div>
																		</div>
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="table-label">Aadhaar No.</label> <input type="text"
																					name="guest_aadhar_no1" id="guest_aadhar_no1"
																					class="form-control" placeholder="Aadhaar No." maxlength="12">
																			</div>

																		</div>
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																			<label class="table-label">Experience Certificate</label> 
																				<input type="file" id="exe_certi1" name="exe_certi1"
																				class="form-control" accept=".pdf">
																				<input type="hidden" id="hid_exe_certi1" name="hid_exe_certi1"
																				class="form-control" value="">
																		<div class="note-text">
																			<span class="errorClass" id="exe_certi_lbl1">${doc_path_doc1_msg}</span>
																			<span class='tikClass' id="exe_certi_lbltik1"></span>
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
																				title="Save" id="save_guest_faculty1"><i
																					class="lni lni-save"></i></a></li>
																			<li class=""><a
																				class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none"
																				title="Add" id="add_guest_faculty1"><i
																					class="lni lni-plus"></i></a></li>
																			<li class=""><a
																				class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"
																				title="Delete"
																				id="delete_guest_faculty1"><i
																					class="lni lni-trash-can"></i></a></li>
																		</ul>
																		<!-- 																								style="display: none;" -->
																	</div>
																</td>
															</tr>
														</tbody>
													</table>
													
													<input type="hidden" id="save_guest_facultyh1" name="save_guest_facultyh1"  value="1">
													<input type="hidden" id="hid_guest_faculty1" name="hid_guest_faculty1" value="0">
													
												</div>
											
											
										</div>
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<ul class="buttons-group mainbtn">
													<!-- <li><input
														class="main-btn success-btn btn-hover btnsubmit"
														type="button" value="Submit"></li> -->
												</ul>
											</div>
										</div>
									</form:form>
								</div>
								<!-- 	Add Guest Faculty End -->

								<!-- 	Add Non Teaching Faculty Start -->
								<div class="tab">
									<button class="tab-toggle">Non Teaching Staff</button>
								</div>
								<div class="content">
									<h4 class="heading">Non Teaching Staff</h4>
									<form:form name="non_teaching_staff" id="non_teaching_staff" action="" method="post" class=""
										commandName="">
										<div class="row mt-4">
										
										<input type="hidden" id="indno_non_teaching_staff" name="indno_non_teaching_staff" value="0">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="inst_block simple-instruction">
													<strong>Instruction :</strong> Upload educational,
													 ESI, PF, Form 16, account
													statement in single zip/pdf
												</div>
											</div>
										<div class="table-wrapper table-responsive custom-table custom-table-v2 hybrid-table">
													<table class="table table-striped" id="non_teaching_staff_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>
																		Full Name<span class="mandatory">*</span>
																	</h6></th>
																<th><h6>
																		Appointment Detail<span class="mandatory">*</span>
																	</h6></th>
																<th><h6>
																		Employee Detail<span class="mandatory">*</span>
																	</h6></th>
																<th><h6>
																		Document Detail<span class="mandatory">*</span>
																	</h6></th>
																<th class="last-sticky">Action</th>
															</tr>
														</thead>
														<tbody id="">
															<tr id="tr_sibling_non_teaching_staff1">
																<td>
																	<p>1</p>
																</td>
																<td>
																	<div class="row justify-content-center">
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="buttonmerge table-label">First
																					Name</label>
																			</div>
																			<div class="groupadd">
																				<div class="select-style-1 ">
																					<div class="select-position">
																						<select name="non_prefix_id1" id="non_prefix_id1" class="form-control">
																						<option value="0" selected="selected">Select</option>
																						<c:forEach var="item" items="${getPrefixList}" varStatus="num">
																							<option value="${item.id}" name="${item.prefix}">${item.prefix}</option>
																						</c:forEach>
																						</select>
																					</div>
																				</div>
																				<div
																					class="input-style-1 form-group buttonmergeinput">
																					<input type="text" id="non_first_name1"
																						name="non_first_name1" class="form-control"
																						placeholder="First Name" maxlength="100">
																				</div>
																			</div>
																		</div>

																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="table-label">Middle Name</label> <input
																					type="text" id="non_middle_name1" name="non_middle_name1"
																					class="form-control" placeholder="Middle Name" maxlength="100">
																			</div>
																		</div>

																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="table-label">Last Name</label> <input
																					type="text" id="non_last_name1" name="non_last_name1"
																					class="form-control" placeholder="Last Name" maxlength="100">
																			</div>
																		</div>

																	</div>
																</td>

																<td>
																	<div class="row justify-content-center">
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-2">
																				<label class="table-label">Date of
																					Appointment
																				</label> <input type="text" name="non_date_of_appoinment1" id="non_date_of_appoinment1"
																					class="form-control-sm form-control effect-9 "
																					aria-required="true" value="DD/MM/YYYY">
																			</div>
																		</div>
																		<div class="select-style-1 ">
																		<label class="table-label">Nature of Appointment</label>
																			<div class="select-position">
																				<select name="non_nature_of_appoinment1" id="non_nature_of_appoinment1" class="form-control">
																				<option value="0" selected="selected">--Select--</option>
																				<c:forEach var="item" items="${getNature_of_Appoinment}" varStatus="num">
																					<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																				</c:forEach>
																				</select>
																			</div>
																		</div>
																		<!-- <div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="table-label">Nature of
																					Appointment
																				</label> <input type="text" id="non_nature_of_appoinment1"
																					name="non_nature_of_appoinment1" class="form-control"
																					placeholder="Nature of Appointment">
																			</div>

																		</div> -->
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																			<label class="table-label">Appoinment Letter</label> 
																				<input type="file" id="non_appoinment_letter1" name="non_appoinment_letter1"
																				class="form-control" accept=".pdf">
																				<input type="hidden" id="hid_non_appoinment_letter1" name="hid_non_appoinment_letter1"
																				class="form-control" value="">
																		<div class="note-text">
																			<span class="errorClass" id="non_appoinment_letter_lbl1">${doc_path_doc1_msg}</span>
																			<span class='tikClass' id="non_appoinment_letter_lbltik1"></span>
																		</div>
																			</div>
																		</div>
																	</div>
																</td>

																<td>
																	<div class="row justify-content-center">
																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-1">
																				<label class="table-label">Employee ID</label> <input type="text"
																					name="non_emp_id1" id="non_emp_id1"
																					class="form-control" placeholder="Employee ID" maxlength="20">
																			</div>
																		</div>
																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-1">
																				<label class="table-label">Department
																				</label> <input type="text" name="non_emp_department1"
																					id="non_emp_department1" class="form-control"
																					placeholder="Working Department" maxlength="100">
																			</div>
																		</div>
																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-1">
																				<label class="table-label">Qualification</label> <input type="text"
																					id="non_emp_qualification1" name="non_emp_qualification1"
																					placeholder="Qualification" maxlength="100">
																			</div>
																		</div>
																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-1">
																				<label class="table-label">Pay Scale</label> <input type="text"
																					id="non_emp_pay_scale1" name="non_emp_pay_scale1"
																					placeholder="Pay Scale" maxlength="10">
																			</div>
																		</div>

																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-1">
																				<label class="table-label">Designation</label> <input type="text"
																					name="non_emp_designation1" id="non_emp_designation1"
																					class="form-control" placeholder="Designation" maxlength="100">
																			</div>
																		</div>
																		
																		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
																			<div class="input-style-1">
																			<label class="table-label">Attachment</label> 
																				<input type="file" id="non_teaching_attachment1" name="non_teaching_attachment1"
																				class="form-control" accept=".pdf,.zip">
																				<input type="hidden" id="hid_non_teaching_attachment1" name="hid_non_teaching_attachment1"
																				class="form-control" value="">
																		<div class="note-text">
																			<span class="errorClass" id="non_teaching_attachment_lbl1">${doc_path_doc1_msg}</span>
																			<span class='tikClass' id="non_teaching_attachment_lbltik1"></span>
																		</div>
																			</div>
																		</div>
																		
																	</div>
																</td>

																<td>
																	<div class="row justify-content-center">
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="table-label">PAN</label> <input type="text"
																					name="non_pan_no1" id="non_pan_no1"
																					class="form-control UpperClassName" placeholder="PAN">
																			</div>
																		</div>
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																				<label class="table-label">Aadhaar No.</label> <input type="text"
																					name="non_aadhar_no1" id="non_aadhar_no1"
																					class="form-control" placeholder="Aadhaar No." maxlength="12">
																			</div>

																		</div>
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="input-style-1">
																			<label class="table-label">Experience Certificate</label> 
																				<input type="file" id="non_exe_certi1" name="non_exe_certi1"
																				class="form-control" accept=".pdf">
																				<input type="hidden" id="hid_non_exe_certi1" name="hid_non_exe_certi1"
																				class="form-control" value="">
																		<div class="note-text">
																			<span class="errorClass" id="non_exe_certi_lbl1">${doc_path_doc1_msg}</span>
																			<span class='tikClass' id="non_exe_certi_lbltik1"></span>
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
																				title="Save" id="save_non_teaching_staff1"><i
																					class="lni lni-save"></i></a></li>
																			<li class=""><a
																				class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none"
																				title="Add" id="add_non_teaching_staff1"><i
																					class="lni lni-plus"></i></a></li>
																			<li class=""><a
																				class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"
																				title="Delete"
																				id="delete_non_teaching_staff1"><i
																					class="lni lni-trash-can"></i></a></li>
																		</ul>
																		<!-- 																								style="display: none;" -->
																	</div>
																</td>
															</tr>
														</tbody>
													</table>
													
													<input type="hidden" id="save_non_teaching_staffh1" name="save_non_teaching_staffh1"  value="1">
													<input type="hidden" id="hid_non_teaching_staff1" name="hid_non_teaching_staff1" value="0">
													
												</div>

										</div>
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<ul class="buttons-group mainbtn">
													<!-- <li><input
														class="main-btn success-btn btn-hover btnsubmit"
														type="button" value="Submit"></li> -->
												</ul>
											</div>
										</div>
									</form:form>
								</div>
								<!-- 	Add Non Teaching Faculty End -->
							</div>
						</div>
					</div>
					<!--    ===========================
		Add Staff End
===========================     -->
				</div>
			</div>
			<!-- end row -->
		</div>
		<!-- end container -->
	</section>
	<!--components end -->
	<c:url value="College_Staff_List_ViewUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="basic_clg_staff_list_id">
	<input type="hidden" name="basic_clg_staff_list_id" id="basic_clg_staff_list_id" value="0" />
	<input type="hidden" name="statusview" id="statusview" value="0" />
</form:form>


	<script type="text/javascript"
		src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>



	<script type="text/javascript" nonce="${cspNonce}">
		$(document).ready(function() {
			if ('${role}'=='NCH') {
				$(".viewData").addClass("d-none")
			}
			if ('${role}'=='Institute_NCH') {
				$(".viewData").removeClass("d-none")
			} 
			var valid_dt = '${valid_dt}';
			var y = valid_dt.substring(0, 4);
			var m = valid_dt.substring(5, 7);
			var d = valid_dt.substring(8, 10);
			var valid_dt1 = d + "/" + m + "/" + y;
			var today = new Date();
			var yyyy = today.getFullYear();
			var mm = today.getMonth() + 1; // Months start at 0!
			var dd = today.getDate();
			if (dd < 10)
				dd = '0' + dd;
			if (mm < 10)
				mm = '0' + mm;
			today = dd + '/' + mm + '/' + yyyy;
			var valid_dt2 = valid_dt.split('/').reverse().join('-');
			var today2 = today.split('/').reverse().join('-');
			if ("${hid}" != "3" && valid_dt2 <= today2) {
				datepicketDate('date_of_appoinment1');
				datepicketDate('guest_date_of_appoinment1');
				datepicketDate('non_date_of_appoinment1');
			}
			
			$('.UpperClassName').keyup(function() {
				this.value = this.value.toUpperCase();
			});
			$("#basic_info_id").val('${basic_info_id}');
			getTeaching_Faculty_Details();
			getGuest_Faculty_Details();
			getNon_Teaching_Staff_Details();
			
		});



//START----> ADD MORE FOR TEACHING FACULTY DETAILS

function formOpen_Teaching_Faculty(R){
	
	 $("#teaching_faculty_table").show();
		 
		 att=0;
// 		 $("#add_teaching_faculty"+R).hide();
// 		 $("#delete_teaching_faculty"+R).hide();
		 att=parseInt($("#save_teaching_facultyh1").val())+1;
		 att= parseInt(R)+1;
		
		 if(att < 21){
					 
			 $("input#save_teaching_facultyh1").val(att);//current serial No
			 $("table#teaching_faculty_table").append('<tr id="tr_sibling_teaching_faculty1'+att+'">'

					+'<td class="min-width"><div class="lead"><div class="lead-text"><p>'+att+'</p></div></div></td>' 
					 
					+'<td><div class="row justify-content-center"><div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1">'
					+'<label class="buttonmerge table-label">First Name</label></div>'
					+'<div class="groupadd"><div class="select-style-1 "><div class="select-position"><select name="prefix_id'+att+'" id="prefix_id'+att+'" class="form-control">'
					+'<option value="1">Mr</option><option value="2">Ms</option></select></div></div>'
					
					+'<div class="input-style-1 form-group buttonmergeinput"><input type="text" id="first_name'+att+'" name="first_name'+att+'" class="form-control" placeholder="First Name" maxlength="100">'
					+'</div></div></div>'

					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1"><label class="table-label">Middle Name</label> '
					+'<input type="text" id="middle_name'+att+'" name="middle_name'+att+'" class="form-control" placeholder="Middle Name" maxlength="100"></div></div>'

					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1"><label class="table-label">Last Name</label> '
					+'<input type="text" id="last_name'+att+'" name="last_name'+att+'" class="form-control" placeholder="Last Name" maxlength="100"></div></div></div></td>'

					+'<td><div class="row justify-content-center"><div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-2">'
					+'<label class="table-label">Date of Appointment</label> '
					+'<input type="text" name="date_of_appoinment'+att+'" id="date_of_appoinment'+att+'" class="form-control-sm form-control effect-9 "aria-required="true" value="DD/MM/YYYY">'
					+'</div></div>'
					
					+'<div class="select-style-1 "><label class="table-label">Nature of Appointment</label><div class="select-position">'
					+'<select name="nature_of_appoinment'+att+'" id="nature_of_appoinment'+att+'" class="form-control">'
					+'<option value="0" selected="selected">--Select--</option><c:forEach var="item" items="${getNature_of_Appoinment}" varStatus="num">'
					+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></div></div></div></td>'
					
					
// 					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1"><label class="table-label">Nature of Appointment</label> '
// 					+'<input type="text" id="nature_of_appoinment'+att+'" name="nature_of_appoinment'+att+'" class="form-control" placeholder="Nature of Appointment"></div>'
// 					+'</div>'

					+'<td><div class="row justify-content-center"><div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-1">'
					+'<label class="table-label">Employee ID</label> <input type="text" name="emp_id'+att+'" id="emp_id'+att+'" class="form-control" placeholder="Employee ID">'
					+'</div></div>'
					
					+'<div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-1"><label class="table-label">Department</label> '
					+'<input type="text" name="emp_department'+att+'" id="emp_department'+att+'" class="form-control" placeholder="Working Department" maxlength="100"></div></div>'
					
					+'<div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-1"><label class="table-label">Qualification</label> '
					+'<input type="text" id="emp_qualification'+att+'" name="emp_qualification'+att+'" placeholder="Qualification" maxlength="100"></div></div>'
					
					+'<div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-1"><label class="table-label">Pay Scale</label> '
					+'<input type="text" id="emp_pay_scale'+att+'" name="emp_pay_scale'+att+'" placeholder="Pay Scale" maxlength="10"></div></div>'

					+'<div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-1"><label class="table-label">Designation</label> '
					+'<input type="text" name="emp_designation'+att+'" id="emp_designation'+att+'" class="form-control" placeholder="Designation" maxlength="100"></div></div>'
					
					+'<div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-form-check_block">'
					+'<div class="form-check radio-style">'
					+'<input type="radio" id="ug_check'+att+'" name="ug_check_name'+att+'" class="form-check-input" value="UG"> '
					+'<label for="ug" class="form-check-label">UG</label>'
					+'<input type="radio" id="pg_check'+att+'" name="ug_check_name'+att+'" class="form-check-input" value="PG"> '
					+'<label for="pg" class="form-check-label">PG</label>'
					+'</div></div></div></div></td>'
					
					+'<td><div class="row justify-content-center"><div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1">'
					+'<label class="table-label">PAN</label> <input type="text" name="pan_no'+att+'" id="pan_no'+att+'" class="form-control UpperClassName" placeholder="PAN"></div></div>'
					
					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1"><label class="table-label">Aadhaar No.</label> '
					+'<input type="text" name="aadhar_no'+att+'" id="aadhar_no'+att+'" class="form-control" placeholder="Aadhaar No." maxlength="12"></div></div>'
			
					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1"><label class="table-label">Attachment</label> '
					+'<input type="file" id="teaching_attachment'+att+'" name="teaching_attachment'+att+'" class="form-control" accept=".pdf">'
					+'<input type="hidden" id="hid_teaching_attachment'+att+'" name="hid_teaching_attachment'+att+'" class="form-control" value="">'
					+'<div class="note-text"><span class="errorClass" id="teaching_attachment_lbl'+att+'">${doc_path_doc1_msg}</span>'
					+'<span class="tikClass" id="teaching_attachment_lbltik'+att+'"></span></div></div></div></div></td>'
					
					
					+'<td><div class="row justify-content-center"><div class="select-style-1 "><label class="table-label">Registration Authority</label>'
					+'<div class="select-position"><select name="reg_authority'+att+'" id="reg_authority'+att+'" class="form-control">'
					+'<option value="0" selected="selected">--Select--</option><option value="MCI/NMC">MCI/NMC</option><option value="CCH/NCH">CCH/NCH</option>'
					+'</select></div></div></div>'
					
					+'<div class="row justify-content-center"><div class="select-style-1 "><label class="table-label">Registration Type</label>'
					+'<div class="select-position"><select name="reg_type'+att+'" id="reg_type'+att+'" class="form-control">'
					+'<option value="0" selected="selected">--Select--</option><option value="State">State</option><option value="Central">Central</option>'
					+'</select></div></div></div>'
					
					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1">'
					+'<label class="table-label">Registration Number</label> <input type="text" name="reg_no'+att+'" id="reg_no'+att+'" class="form-control UpperClassName" placeholder="Registration Authority" maxlength="12">'
					+'</div></div></td>'
					
					
					+'<td class="last-sticky"><div class="action"><ul class="buttons-group mainbtn daobtn"><li>'
					+'<a class="main-btn active-btn btn-hover btn-sm btnsave" title="Save" id="save_teaching_faculty'+att+'"><i class="lni lni-save"></i></a></li>'
					+'<li class=""><a class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none" title="Add" id="add_teaching_faculty'+att+'">'
					+'<i class="lni lni-plus"></i></a></li><li class=""><a class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none" title="Delete" id="delete_teaching_faculty'+att+'">'
					+'<i class="lni lni-trash-can"></i></a></li></ul></div></td>'
					 
					+'<input type="hidden" id="save_teaching_facultyh'+att+'" name="save_teaching_facultyh'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
					+'<input type="hidden" id="hid_teaching_faculty'+att+'" name="hid_teaching_faculty'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
					+'<i class="lni lni-trash-can"></i></a></li></ul></td>'
					+ '</tr>');
			 datepicketDate('date_of_appoinment'+att);
			 $('.UpperClassName').keyup(function() {
					this.value = this.value.toUpperCase();
				});
			 addOnclick_Teaching_Faculty(att);
			 
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
					alert("You can not enter more than twenty times");
					 if ( att == 21){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
//		 			hidesystem_degree(att);
	}
			
			
function addOnclick_Teaching_Faculty(att) {
	
	
	document.getElementById('add_teaching_faculty'+att).onclick = function() {
		return formOpen_Teaching_Faculty(att);
	};
	document.getElementById('delete_teaching_faculty'+att).onclick = function() {
		return delete_Teaching_Faculty(att);
	};
	document.getElementById('save_teaching_faculty' + att).onclick = function() {
		Save_As_Draft_Teaching_Faculty(att);
	};
	document.getElementById('first_name'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('middle_name'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('last_name'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
// 	document.getElementById('nature_of_appoinment'+ att).onkeypress = function() {
// 		return onlyAlphabetsStringSpace(event, this);
// 	};
	document.getElementById('emp_id'+ att).onkeypress = function() {
		return onlyAlphaNumeric(event,this);
	};
	document.getElementById('emp_department'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('emp_qualification'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('emp_pay_scale'+ att).onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('emp_designation'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('pan_no'+ att).onkeypress = function() {
		return onlyAlphaNumeric(event,this);
	};
	document.getElementById('aadhar_no'+ att).onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('teaching_attachment'+ att).onchange = function() {
		return pdfFileSizeValidation(this,this.value,"teaching_attachment"+ att,"200kb","teaching_attachment_lbltik"+ att,"teaching_attachment_lbl"+ att,this.accept)
	};
	document.getElementById('reg_no'+ att).onkeypress = function() {
		return onlyAlphaNumeric(event,this);
	};

}
	
function Save_As_Draft_Teaching_Faculty(ps) {
	$.ajaxSetup({
		async : false
	});
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	var res = CheckNullorBlank('first_name'+ps);
	if (res !== "true") {
		alert(res +"First Name");
		$('#first_name'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('middle_name'+ps);
	if (res !== "true") {
		alert(res +"Middle Name");
		$('#middle_name'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('last_name'+ps);
	if (res !== "true") {
		alert(res +"Last Name");
		$('#last_name'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('date_of_appoinment'+ps);
	if (res !== "true") {
		alert(res +"Date of Appointment");
		$('#date_of_appoinment'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('nature_of_appoinment'+ps);
	if (res !== "true") {
		alert(res +"Nature of Appoinment");
		$('#nature_of_appoinment'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('emp_id'+ps);
	if (res !== "true") {
		alert(res +"Employee ID");
		$('#emp_id'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('emp_department'+ps);
	if (res !== "true") {
		alert(res +"Employee Department");
		$('#emp_department'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('emp_qualification'+ps);
	if (res !== "true") {
		alert(res +"Employee Qualification");
		$('#emp_qualification'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('emp_pay_scale'+ps);
	if (res !== "true") {
		alert(res +"Employee Pay Scale");
		$('#emp_pay_scale'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('emp_designation'+ps);
	if (res !== "true") {
		alert(res +"Employee Designation");
		$('#emp_designation'+ps).focus();
		return false;
	}
	var ug_check_name = $("input[name='ug_check_name"+ps+"']:checked").val();
	if( ug_check_name == null ){
		alert("Please Select UG/PG");
		$('#ug_check'+ps).focus();
		return false;
   	}
	var res = CheckNullorBlank('pan_no'+ps);
	if (res !== "true") {
		alert(res +"PAN Number");
		$('#pan_no'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('aadhar_no'+ps);
	if (res !== "true") {
		alert(res +"AADHAR Number");
		$('#aadhar_no'+ps).focus();
		return false;
	}
	var an = $("#aadhar_no"+ps).val();
	var minLength = 11;
	var charLength = an.length;
	if (charLength < minLength) {
		alert("Please Enter Valid Aadhar No.");
		$("input#aadhar_no"+ps).focus();
		return false;
	}
	var hid_teaching_attachment = CheckNullorBlank('hid_teaching_attachment'+ps);
	if(hid_teaching_attachment !== "true"){
		var res = CheckNullorBlank('teaching_attachment'+ps);
		if (res !== "true") {
			alert("Please Upload Attachment");
			$('#teaching_attachment'+ps).focus();
			return false;
		}
	}
	var res = CheckNullorBlank('reg_authority'+ps);
	if (res !== "true") {
		alert(res +"Registration Authority");
		$('#reg_authority'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('reg_type'+ps);
	if (res !== "true") {
		alert(res +"Registration Type");
		$('#reg_type'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('reg_no'+ps);
	if (res !== "true") {
		alert(res +"Registration Number");
		$('#reg_no'+ps).focus();
		return false;
	}
	
	$("#indno_teaching_faculty").val(ps);
	var form_data = new FormData(document.getElementById("teaching_faculty"));
	$.ajax({
		url : 'Teaching_Faculty_Details_Save_Draft_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j){
      if(j>0){
    		$("#hid_teaching_faculty"+ps).val(j);
    	  	alert("Data Save Sucessfully");
//     	  	$("#add_guest_faculty" + ps).show();
// 			$("#delete_guest_faculty" + ps).show();
      }
      else{
    	  alert(j);
      }
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
	
}


function delete_Teaching_Faculty(R) {
	
	// 		var qualtification_idhid2= $("input#qualtification_id"+R).val();
	if(confirm('Are you sure you want to delete?')) {

	var hid_teaching_faculty = $('#hid_teaching_faculty' + R).val();
	
	$.post("delete_Teaching_Faculty_Details?" + key + "=" + value, {
		hid_teaching_faculty : hid_teaching_faculty
	}, function(j) {
		alert(j);
	});

	$("tr#tr_sibling_teaching_faculty1" + R).remove();
	R = R - 1;
	$("input#count_hidden_att_name_med").val(att);
// 	$("#add_teaching_faculty" + R).show();
// 	$("#delete_teaching_faculty" + R).show();
	}else {
		return false;
	}
}


function getTeaching_Faculty_Details() {
	
	$.ajaxSetup({
		async : false
	});
	
	var ser = 0;

	$.post("getTeaching_Faculty_Details?" + key + "=" + value, {
		
	}, function(j) {
		
		for (var i = 0; i < j.length; i++) {
			ser = i + 1;

			if (j.length > 0) {

				if (ser > 1) {
					
					$("#add_teaching_faculty" + (ser - 1)).click();
				}
				
				$("#hid_teaching_faculty" + ser).val(j[i][14]);
				$("#prefix_id" + ser).val(j[i][1]);
				$("#first_name" + ser).val(j[i][2]);
				$("#middle_name" + ser).val(j[i][3]);
				$("#last_name" + ser).val(j[i][4]);
				$("#date_of_appoinment" + ser).val(j[i][5]);
				$("#nature_of_appoinment" + ser).val(j[i][6]);
				$("#emp_id" + ser).val(j[i][13]);
				$("#emp_department" + ser).val(j[i][7]);
				$("#emp_qualification" + ser).val(j[i][11]);
				$("#emp_pay_scale" + ser).val(j[i][12]);
				$("#emp_designation" + ser).val(j[i][10]);
				$("#pan_no" + ser).val(j[i][9]);
				$("#aadhar_no" + ser).val(j[i][8]);
				$("#hid_teaching_attachment" + ser).val(j[i][16]);
				$("#reg_authority" + ser).val(j[i][17]);
				$("#reg_type" + ser).val(j[i][18]);
				$("#reg_no" + ser).val(j[i][19]);
				
				var ug_pg_check = j[i][15];
				$("input[name='ug_check_name"+ser+"'][value='"+ug_pg_check+"']").prop('checked', true);
				
// 				$("#add_teaching_faculty" + ser).show();
// 				$("#delete_teaching_faculty" + ser).show();
			}
		}
	});
}

//END----> ADD MORE FOR TEACHING FACULTY DETAILS




//START----> ADD MORE FOR GUEST FACULTY DETAILS

function formOpen_Guest_Faculty(R){
	
	 $("#guest_faculty_table").show();
		 
		 att=0;
		 $("#add_guest_faculty"+R).hide();
		 $("#delete_guest_faculty"+R).show();
		 att=parseInt($("#save_guest_facultyh1").val())+1;
		 att= parseInt(R)+1;
		
		 if(att < 21){
					 
			 $("input#save_guest_facultyh1").val(att);//current serial No
			 $("table#guest_faculty_table").append('<tr id="tr_sibling_guest_faculty1'+att+'">'

					+'<td class="min-width"><div class="lead"><div class="lead-text"><p>'+att+'</p></div></div></td>' 
					 
					+'<td><div class="row justify-content-center"><div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1">'
					+'<label class="buttonmerge table-label">First Name</label></div>'
					+'<div class="groupadd"><div class="select-style-1 "><div class="select-position"><select name="guest_prefix_id'+att+'" id="guest_prefix_id'+att+'" class="form-control">'
					+'<option value="0" selected="selected">Select</option><c:forEach var="item" items="${getPrefixList}" varStatus="num">'
					+'<option value="${item.id}" name="${item.prefix}">${item.prefix}</option></c:forEach></select></div></div>'
					
					+'<div class="input-style-1 form-group buttonmergeinput"><input type="text" id="guest_first_name'+att+'" name="guest_first_name'+att+'" class="form-control" placeholder="First Name" maxlength="100">'
					+'</div></div></div>'

					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1"><label class="table-label">Middle Name</label> '
					+'<input type="text" id="guest_middle_name'+att+'" name="guest_middle_name'+att+'" class="form-control" placeholder="Middle Name" maxlength="100"></div></div>'

					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1"><label class="table-label">Last Name</label> '
					+'<input type="text" id="guest_last_name'+att+'" name="guest_last_name'+att+'" class="form-control" placeholder="Last Name" maxlength="100"></div></div></div></td>'

					+'<td><div class="row justify-content-center"><div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-2">'
					+'<label class="table-label">Date of Appointment</label> '
					+'<input type="text" name="guest_date_of_appoinment'+att+'" id="guest_date_of_appoinment'+att+'" class="form-control-sm form-control effect-9 "aria-required="true" value="DD/MM/YYYY">'
					+'</div></div>'
					
// 					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1"><label class="table-label">Nature of Appointment</label> '
// 					+'<input type="text" id="guest_nature_of_appoinment'+att+'" name="guest_nature_of_appoinment'+att+'" class="form-control" placeholder="Nature of Appointment"></div>'
// 					+'</div>'
					
					+'<div class="select-style-1 "><label class="table-label">Nature of Appointment</label><div class="select-position">'
					+'<select name="guest_nature_of_appoinment'+att+'" id="guest_nature_of_appoinment'+att+'" class="form-control">'
					+'<option value="0" selected="selected">--Select--</option><c:forEach var="item" items="${getNature_of_Appoinment}" varStatus="num">'
					+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></div></div>'
					
					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1"><label class="table-label">Appoinment Letter</label> '
					+'<input type="file" id="appoinment_letter'+att+'" name="appoinment_letter'+att+'" class="form-control" accept=".pdf">'
					+'<input type="hidden" id="hid_appoinment_letter'+att+'" name="hid_appoinment_letter'+att+'" class="form-control" value="">'
					+'<div class="note-text"><span class="errorClass" id="appoinment_letter_lbl'+att+'">${doc_path_doc1_msg}</span>'
					+'<span class="tikClass" id="appoinment_letter_lbltik'+att+'"></span></div></div></div></div></td>'

					+'<td><div class="row justify-content-center"><div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-1">'
					+'<label class="table-label">Employee ID</label> <input type="text" name="guest_emp_id'+att+'" id="guest_emp_id'+att+'" class="form-control" placeholder="Employee ID" maxlength="20">'
					+'</div></div>'
					
					+'<div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-1"><label class="table-label">Department</label> '
					+'<input type="text" name="guest_emp_department'+att+'" id="guest_emp_department'+att+'" class="form-control" placeholder="Working Department" maxlength="100"></div></div>'
					
					+'<div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-1"><label class="table-label">Qualification</label> '
					+'<input type="text" id="guest_emp_qualification'+att+'" name="guest_emp_qualification'+att+'" placeholder="Qualification" maxlength="100"></div></div>'
					
					+'<div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-1"><label class="table-label">Pay Scale</label> '
					+'<input type="text" id="guest_emp_pay_scale'+att+'" name="guest_emp_pay_scale'+att+'" placeholder="Pay Scale" maxlength="10"></div></div>'

					+'<div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-1"><label class="table-label">Designation</label> '
					+'<input type="text" name="guest_emp_designation'+att+'" id="guest_emp_designation'+att+'" class="form-control" placeholder="Designation" maxlength="100"></div></div>'

					+'<div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-1"><label class="table-label">Attachment</label> '
					+'<input type="file" id="guest_teaching_attachment'+att+'" name="guest_teaching_attachment'+att+'" class="form-control" accept=".pdf,.zip">'
					+'<input type="hidden" id="hid_guest_teaching_attachment'+att+'" name="hid_guest_teaching_attachment'+att+'" class="form-control" value="">'
					+'<div class="note-text"><span class="errorClass" id="guest_teaching_attachment_lbl'+att+'">${doc_path_doc1_msg}</span>'
					+'<span class="tikClass" id="guest_teaching_attachment_lbltik'+att+'"></span></div></div></div></div></td>'
					
					+'<td><div class="row justify-content-center"><div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1">'
					+'<label class="table-label">PAN</label> <input type="text" name="guest_pan_no'+att+'" id="guest_pan_no'+att+'" class="form-control UpperClassName" placeholder="PAN"></div></div>'
					
					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1"><label class="table-label">Aadhaar No.</label> '
					+'<input type="text" name="guest_aadhar_no'+att+'" id="guest_aadhar_no'+att+'" class="form-control" placeholder="Aadhaar No." maxlength="12"></div></div>'
					
					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1"><label class="table-label">Experience Certificate</label> '
					+'<input type="file" id="exe_certi'+att+'" name="exe_certi'+att+'" class="form-control" accept=".pdf">'
					+'<input type="hidden" id="hid_exe_certi'+att+'" name="hid_exe_certi'+att+'" class="form-control" value="">'
					+'<div class="note-text"><span class="errorClass" id="exe_certi_lbl'+att+'">${doc_path_doc1_msg}</span>'
					+'<span class="tikClass" id="exe_certi_lbltik'+att+'"></span></div></div></div></div></td>'
			
					+'<td class="last-sticky"><div class="action"><ul class="buttons-group mainbtn daobtn"><li>'
					+'<a class="main-btn active-btn btn-hover btn-sm btnsave" title="Save" id="save_guest_faculty'+att+'"><i class="lni lni-save"></i></a></li>'
					+'<li class=""><a class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none" title="Add" id="add_guest_faculty'+att+'">'
					+'<i class="lni lni-plus"></i></a></li><li class=""><a class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none" title="Delete" id="delete_guest_faculty'+att+'">'
					+'<i class="lni lni-trash-can"></i></a></li></ul></div></td>'
					 
					+'<input type="hidden" id="save_guest_facultyh'+att+'" name="save_guest_facultyh'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
					+'<input type="hidden" id="hid_guest_faculty'+att+'" name="hid_guest_faculty'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
					+'<i class="lni lni-trash-can"></i></a></li></ul></td>'
					+ '</tr>');
			 
			 datepicketDate('guest_date_of_appoinment'+att);
			 $('.UpperClassName').keyup(function() {
					this.value = this.value.toUpperCase();
				});
			 addOnclick_Guest_Faculty(att);
			 
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
			$("#save_guest_facultyh1").val(att);
			
		 }else{
					alert("You can not enter more than twenty times");
					 if ( att == 21){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
//		 			hidesystem_degree(att);
	}
			
			
function addOnclick_Guest_Faculty(att) {
	
	
	document.getElementById('add_guest_faculty'+att).onclick = function() {
		return formOpen_Guest_Faculty(att);
	};
	document.getElementById('delete_guest_faculty'+att).onclick = function() {
		return delete_Guest_Faculty(att);
	};
	document.getElementById('save_guest_faculty' + att).onclick = function() {
		Save_As_Draft_Guest_Faculty(att);
	};
	document.getElementById('guest_first_name'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('guest_middle_name'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('guest_last_name'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
// 	document.getElementById('guest_nature_of_appoinment'+ att).onkeypress = function() {
// 		return onlyAlphabetsStringSpace(event, this);
// 	};
	document.getElementById('guest_emp_id'+ att).onkeypress = function() {
		return onlyAlphaNumeric(event,this);
	};
	document.getElementById('guest_emp_department'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('guest_emp_qualification'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('guest_emp_pay_scale'+ att).onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('guest_emp_designation'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('guest_pan_no'+ att).onkeypress = function() {
		return onlyAlphaNumeric(event,this);
	};
	document.getElementById('guest_aadhar_no'+ att).onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('appoinment_letter'+ att).onchange = function() {
		return pdfFileSizeValidation(this,this.value,"appoinment_letter"+ att,"200kb","appoinment_letter_lbltik"+ att,"appoinment_letter_lbl"+ att,this.accept)
	};
	document.getElementById('exe_certi'+ att).onchange = function() {
		return pdfFileSizeValidation(this,this.value,"exe_certi"+ att,"200kb","exe_certi_lbltik"+ att,"exe_certi_lbl"+ att,this.accept)
	};
	document.getElementById('guest_teaching_attachment'+ att).onchange = function() {
		return pdfFileSizeValidation(this,this.value,"guest_teaching_attachment"+ att,"200kb","guest_teaching_attachment_lbltik"+ att,"guest_teaching_attachment_lbl1"+ att,this.accept)
	};
}
	
function Save_As_Draft_Guest_Faculty(ps) {
	$.ajaxSetup({
		async : false
	});
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	var res = CheckNullorBlank('guest_prefix_id'+ps);
	if (res !== "true") {
		alert(res +"Your Prefix");
		$('#guest_prefix_id'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('guest_first_name'+ps);
	if (res !== "true") {
		alert(res +"First Name");
		$('#guest_first_name'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('guest_middle_name'+ps);
	if (res !== "true") {
		alert(res +"Middle Name");
		$('#guest_middle_name'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('guest_last_name'+ps);
	if (res !== "true") {
		alert(res +"Last Name");
		$('#guest_last_name'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('guest_date_of_appoinment'+ps);
	if (res !== "true") {
		alert(res +"Date of Appointment");
		$('#guest_date_of_appoinment'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('guest_nature_of_appoinment'+ps);
	if (res !== "true") {
		alert(res +"Nature of Appoinment");
		$('#guest_nature_of_appoinment'+ps).focus();
		return false;
	}
	var hid_appoinment_letter = CheckNullorBlank('hid_appoinment_letter'+ps);
	if(hid_appoinment_letter !== "true"){
		var res = CheckNullorBlank('appoinment_letter'+ps);
		if (res !== "true") {
			alert("Please Upload Document of Appoinment Letter");
			$('#appoinment_letter'+ps).focus();
			return false;
		}
	}
	var res = CheckNullorBlank('guest_emp_id'+ps);
	if (res !== "true") {
		alert(res +"Employee ID");
		$('#guest_emp_id'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('guest_emp_department'+ps);
	if (res !== "true") {
		alert(res +"Employee Department");
		$('#guest_emp_department'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('guest_emp_qualification'+ps);
	if (res !== "true") {
		alert(res +"Employee Qualification");
		$('#guest_emp_qualification'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('guest_emp_pay_scale'+ps);
	if (res !== "true") {
		alert(res +"Employee Pay Scale");
		$('#guest_emp_pay_scale'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('guest_emp_designation'+ps);
	if (res !== "true") {
		alert(res +"Employee Designation");
		$('#guest_emp_designation'+ps).focus();
		return false;
	}
	var hid_guest_teaching_attachment = CheckNullorBlank('hid_guest_teaching_attachment'+ps);
	if(hid_guest_teaching_attachment !== "true"){
		var res = CheckNullorBlank('guest_teaching_attachment'+ps);
		if (res !== "true") {
			alert("Please Upload Attachment");
			$('#guest_teaching_attachment'+ps).focus();
			return false;
		}
	}
	var res = CheckNullorBlank('guest_pan_no'+ps);
	if (res !== "true") {
		alert(res +"PAN Number");
		$('#guest_pan_no'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('guest_aadhar_no'+ps);
	if (res !== "true") {
		alert(res +"AADHAR Number");
		$('#guest_aadhar_no'+ps).focus();
		return false;
	}
	var an = $("#guest_aadhar_no"+ps).val();
	var minLength = 12;
	var charLength = an.length;
	if (charLength < minLength) {
		alert("Please Enter Valid Aadhar No.");
		$("input#guest_aadhar_no"+ps).focus();
		return false;
	}
	var hid_exe_certi = CheckNullorBlank('hid_exe_certi'+ps);
	if(hid_exe_certi !== "true"){
		var res = CheckNullorBlank('exe_certi'+ps);
		if (res !== "true") {
			alert("Please Upload Document of Experience Certificate");
			$('#exe_certi'+ps).focus();
			return false;
		}
	}
	
	$("#indno_guest_faculty").val(ps);
	var form_data = new FormData(document.getElementById("guest_faculty"));
	$.ajax({
		url : 'Guest_Faculty_Details_Save_Draft_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j){
      if(j>0){
    		$("#hid_guest_faculty"+ps).val(j);
    	  	alert("Data Save Sucessfully");
    	  	$("#add_guest_faculty" + ps).show();
			$("#delete_guest_faculty" + ps).show();
      }
      else{
    	  alert(j);
      }
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
	
}


function delete_Guest_Faculty(R) {
	
	// 		var qualtification_idhid2= $("input#qualtification_id"+R).val();
	if(confirm('Are you sure you want to delete?')) {

	var hid_guest_faculty = $('#hid_guest_faculty' + R).val();
	
	$.post("delete_Guest_Faculty_Details?" + key + "=" + value, {
		hid_guest_faculty : hid_guest_faculty
	}, function(j) {
		alert(j);
	});

	$("tr#tr_sibling_guest_faculty1" + R).remove();
	R = R - 1;
	$("input#count_hidden_att_name_med").val(att);
	$("#add_guest_faculty" + R).show();
	$("#delete_guest_faculty" + R).show();
	}else {
		return false;
	}
}


function getGuest_Faculty_Details() {
	
	$.ajaxSetup({
		async : false
	});
	
	var ser = 0;

	$.post("getGuest_Faculty_Details?" + key + "=" + value, {
		
	}, function(j) {
		
		for (var i = 0; i < j.length; i++) {
			ser = i + 1;

			if (j.length > 0) {

				if (ser > 1) {
					
					$("#add_guest_faculty" + (ser - 1)).click();
				}
				
				$("#hid_guest_faculty" + ser).val(j[i][0]);
				$("#guest_prefix_id" + ser).val(j[i][3]);
				$("#guest_first_name" + ser).val(j[i][4]);
				$("#guest_middle_name" + ser).val(j[i][5]);
				$("#guest_last_name" + ser).val(j[i][6]);
				$("#guest_date_of_appoinment" + ser).val(j[i][7]);
				$("#guest_nature_of_appoinment" + ser).val(j[i][8]);
				$("#guest_emp_id" + ser).val(j[i][9]);
				$("#guest_emp_department" + ser).val(j[i][10]);
				$("#guest_emp_qualification" + ser).val(j[i][11]);
				$("#guest_emp_pay_scale" + ser).val(j[i][12]);
				$("#guest_emp_designation" + ser).val(j[i][13]);
				$("#guest_pan_no" + ser).val(j[i][14]);
				$("#guest_aadhar_no" + ser).val(j[i][15]);
				$("#hid_appoinment_letter" + ser).val(j[i][16]);
				$("#hid_exe_certi" + ser).val(j[i][17]);
				$("#hid_guest_teaching_attachment" + ser).val(j[i][18]);
				$("#add_guest_faculty" + ser).show();
				$("#delete_guest_faculty" + ser).show();
			}
		}
	});
}

//END----> ADD MORE FOR GUEST FACULTY DETAILS


//START----> ADD MORE FOR NON TEACHING STAFF DETAILS

function formOpen_Non_Teaching_Staff(R){
	
	 $("#non_teaching_staff_table").show();
		 
		 att=0;
		 $("#add_non_teaching_staff"+R).hide();
		 $("#delete_non_teaching_staff"+R).show();
		 att=parseInt($("#save_non_teaching_staffh1").val())+1;
		 att= parseInt(R)+1;
		
		 if(att < 21){
					 
			 $("input#save_non_teaching_staffh1").val(att);//current serial No
			 $("table#non_teaching_staff_table").append('<tr id="tr_sibling_non_teaching_staff1'+att+'">'

					+'<td class="min-width"><div class="lead"><div class="lead-text"><p>'+att+'</p></div></div></td>' 
					 
					+'<td><div class="row justify-content-center"><div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1">'
					+'<label class="buttonmerge table-label">First Name</label></div>'
					+'<div class="groupadd"><div class="select-style-1 "><div class="select-position"><select name="non_prefix_id'+att+'" id="non_prefix_id'+att+'" class="form-control">'
					+'<option value="0" selected="selected">Select</option><c:forEach var="item" items="${getPrefixList}" varStatus="num">'
					+'<option value="${item.id}" name="${item.prefix}">${item.prefix}</option></c:forEach></select></div></div>'
					
					+'<div class="input-style-1 form-group buttonmergeinput"><input type="text" id="non_first_name'+att+'" name="non_first_name'+att+'" class="form-control" placeholder="First Name" maxlength="100">'
					+'</div></div></div>'

					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1"><label class="table-label">Middle Name</label> '
					+'<input type="text" id="non_middle_name'+att+'" name="non_middle_name'+att+'" class="form-control" placeholder="Middle Name" maxlength="100"></div></div>'

					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1"><label class="table-label">Last Name</label> '
					+'<input type="text" id="non_last_name'+att+'" name="non_last_name'+att+'" class="form-control" placeholder="Last Name" maxlength="100"></div></div></div></td>'

					+'<td><div class="row justify-content-center"><div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-2">'
					+'<label class="table-label">Date of Appointment</label> '
					+'<input type="text" name="non_date_of_appoinment'+att+'" id="non_date_of_appoinment'+att+'" class="form-control-sm form-control effect-9 "aria-required="true" value="DD/MM/YYYY">'
					+'</div></div>'
					
// 					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1"><label class="table-label">Nature of Appointment</label> '
// 					+'<input type="text" id="non_nature_of_appoinment'+att+'" name="non_nature_of_appoinment'+att+'" class="form-control" placeholder="Nature of Appointment"></div>'
// 					+'</div>'
					
					+'<div class="select-style-1 "><label class="table-label">Nature of Appointment</label><div class="select-position">'
					+'<select name="non_nature_of_appoinment'+att+'" id="non_nature_of_appoinment'+att+'" class="form-control">'
					+'<option value="0" selected="selected">--Select--</option><c:forEach var="item" items="${getNature_of_Appoinment}" varStatus="num">'
					+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></div></div>'
					
					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1"><label class="table-label">Appoinment Letter</label> '
					+'<input type="file" id="non_appoinment_letter'+att+'" name="non_appoinment_letter'+att+'" class="form-control" accept=".pdf">'
					+'<input type="hidden" id="hid_non_appoinment_letter'+att+'" name="hid_non_appoinment_letter'+att+'" class="form-control" value="">'
					+'<div class="note-text"><span class="errorClass" id="non_appoinment_letter_lbl'+att+'">${doc_path_doc1_msg}</span>'
					+'<span class="tikClass" id="non_appoinment_letter_lbltik'+att+'"></span></div></div></div></div></td>'

					+'<td><div class="row justify-content-center"><div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-1">'
					+'<label class="table-label">Employee ID</label> <input type="text" name="non_emp_id'+att+'" id="non_emp_id'+att+'" class="form-control" placeholder="Employee ID" maxlength="20">'
					+'</div></div>'
					
					+'<div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-1"><label class="table-label">Department</label> '
					+'<input type="text" name="non_emp_department'+att+'" id="non_emp_department'+att+'" class="form-control" placeholder="Working Department" maxlength="100"></div></div>'
					
					+'<div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-1"><label class="table-label">Qualification</label> '
					+'<input type="text" id="non_emp_qualification'+att+'" name="non_emp_qualification'+att+'" placeholder="Qualification" maxlength="100"></div></div>'
					
					+'<div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-1"><label class="table-label">Pay Scale</label> '
					+'<input type="text" id="non_emp_pay_scale'+att+'" name="non_emp_pay_scale'+att+'" placeholder="Pay Scale" maxlength="10"></div></div>'

					+'<div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-1"><label class="table-label">Designation</label> '
					+'<input type="text" name="non_emp_designation'+att+'" id="non_emp_designation'+att+'" class="form-control" placeholder="Designation" maxlength="100"></div></div>'

					+'<div class="col-12 col-sm-12 col-md-6 col-lg-6"><div class="input-style-1"><label class="table-label">Attachment</label> '
					+'<input type="file" id="non_teaching_attachment'+att+'" name="non_teaching_attachment'+att+'" class="form-control" accept=".pdf,.zip">'
					+'<input type="hidden" id="hid_non_teaching_attachment'+att+'" name="hid_non_teaching_attachment'+att+'" class="form-control" value="">'
					+'<div class="note-text"><span class="errorClass" id="non_teaching_attachment_lbl'+att+'">${doc_path_doc1_msg}</span>'
					+'<span class="tikClass" id="non_teaching_attachment_lbltik'+att+'"></span></div></div></div></div></td>'
					
					+'<td><div class="row justify-content-center"><div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1">'
					+'<label class="table-label">PAN</label> <input type="text" name="non_pan_no'+att+'" id="non_pan_no'+att+'" class="form-control UpperClassName" placeholder="PAN"></div></div>'
					
					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1"><label class="table-label">Aadhaar No.</label> '
					+'<input type="text" name="non_aadhar_no'+att+'" id="non_aadhar_no'+att+'" class="form-control" placeholder="Aadhaar No." maxlength="12"></div></div>'
			
					+'<div class="col-12 col-sm-12 col-md-12 col-lg-12"><div class="input-style-1"><label class="table-label">Experience Certificate</label> '
					+'<input type="file" id="non_exe_certi'+att+'" name="non_exe_certi'+att+'" class="form-control" accept=".pdf">'
					+'<input type="hidden" id="hid_non_exe_certi'+att+'" name="hid_non_exe_certi'+att+'" class="form-control" value="">'
					+'<div class="note-text"><span class="errorClass" id="non_exe_certi_lbl'+att+'">${doc_path_doc1_msg}</span>'
					+'<span class="tikClass" id="non_exe_certi_lbltik'+att+'"></span></div></div></div></div></td>'
					
					+'<td class="last-sticky"><div class="action"><ul class="buttons-group mainbtn daobtn"><li>'
					+'<a class="main-btn active-btn btn-hover btn-sm btnsave" title="Save" id="save_non_teaching_staff'+att+'"><i class="lni lni-save"></i></a></li>'
					+'<li class=""><a class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none" title="Add" id="add_non_teaching_staff'+att+'">'
					+'<i class="lni lni-plus"></i></a></li><li class=""><a class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none" title="Delete" id="delete_non_teaching_staff'+att+'">'
					+'<i class="lni lni-trash-can"></i></a></li></ul></div></td>'
					 
					+'<input type="hidden" id="save_non_teaching_staffh'+att+'" name="save_non_teaching_staff'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
					+'<input type="hidden" id="hid_non_teaching_staff'+att+'" name="hid_non_teaching_staff'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
					+'<i class="lni lni-trash-can"></i></a></li></ul></td>'
					+ '</tr>');
			 datepicketDate('non_date_of_appoinment'+att);
			 $('.UpperClassName').keyup(function() {
					this.value = this.value.toUpperCase();
				});
			 addOnclick_Non_Teaching_Staff(att);
			 
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
			$("#save_non_teaching_staffh1").val(att);
			
		 }else{
					alert("You can not enter more than twenty times");
					 if ( att == 21){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
//		 			hidesystem_degree(att);
	}
			
			
function addOnclick_Non_Teaching_Staff(att) {
	
	
	document.getElementById('add_non_teaching_staff'+att).onclick = function() {
		return formOpen_Non_Teaching_Staff(att);
	};
	document.getElementById('delete_non_teaching_staff'+att).onclick = function() {
		return delete_Non_Teaching_Staff(att);
	};
	document.getElementById('save_non_teaching_staff' + att).onclick = function() {
		Save_As_Draft_Non_Teaching_Staff(att);
	};
	document.getElementById('non_first_name'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('non_middle_name'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('non_last_name'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
// 	document.getElementById('non_nature_of_appoinment'+ att).onkeypress = function() {
// 		return onlyAlphabetsStringSpace(event, this);
// 	};
	document.getElementById('non_emp_id'+ att).onkeypress = function() {
		return onlyAlphaNumeric(event,this);
	};
	document.getElementById('non_emp_department'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('non_emp_qualification'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('non_emp_pay_scale'+ att).onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('non_emp_designation'+ att).onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('non_pan_no'+ att).onkeypress = function() {
		return onlyAlphaNumeric(event,this);
	};
	document.getElementById('non_aadhar_no'+ att).onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('non_appoinment_letter'+ att).onchange = function() {
		return pdfFileSizeValidation(this,this.value,"non_appoinment_letter"+ att,"200kb","non_appoinment_letter_lbltik"+ att,"non_appoinment_letter_lbl"+ att,this.accept)
	};
	document.getElementById('non_exe_certi'+ att).onchange = function() {
		return pdfFileSizeValidation(this,this.value,"non_exe_certi"+ att,"200kb","non_exe_certi_lbltik"+ att,"non_exe_certi_lbl"+ att,this.accept)
	};
	document.getElementById('non_teaching_attachment'+ att).onchange = function() {
		return pdfFileSizeValidation(this,this.value,"non_teaching_attachment"+ att,"200kb","non_teaching_attachment_lbltik1"+ att,"non_teaching_attachment_lbl1"+ att,this.accept)
	};
}
	
function Save_As_Draft_Non_Teaching_Staff(ps) {
	$.ajaxSetup({
		async : false
	});
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	var res = CheckNullorBlank('non_prefix_id'+ps);
	if (res !== "true") {
		alert(res +"Your Prefix");
		$('#non_prefix_id'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('non_first_name'+ps);
	if (res !== "true") {
		alert(res +"First Name");
		$('#non_first_name'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('non_middle_name'+ps);
	if (res !== "true") {
		alert(res +"Middle Name");
		$('#non_middle_name'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('non_last_name'+ps);
	if (res !== "true") {
		alert(res +"Last Name");
		$('#non_last_name'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('non_date_of_appoinment'+ps);
	if (res !== "true") {
		alert(res +"Date of Appointment");
		$('#non_date_of_appoinment'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('non_nature_of_appoinment'+ps);
	if (res !== "true") {
		alert(res +"Nature of Appoinment");
		$('#non_nature_of_appoinment'+ps).focus();
		return false;
	}
	var hid_non_appoinment_letter = CheckNullorBlank('hid_non_appoinment_letter'+ps);
	if(hid_non_appoinment_letter !== "true"){
		var res = CheckNullorBlank('non_appoinment_letter'+ps);
		if (res !== "true") {
			alert("Please Upload Document of Appoinment Letter");
			$('#non_appoinment_letter'+ps).focus();
			return false;
		}
	}
	var res = CheckNullorBlank('non_emp_id'+ps);
	if (res !== "true") {
		alert(res +"Employee ID");
		$('#non_emp_id'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('non_emp_department'+ps);
	if (res !== "true") {
		alert(res +"Employee Department");
		$('#non_emp_department'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('non_emp_qualification'+ps);
	if (res !== "true") {
		alert(res +"Employee Qualification");
		$('#non_emp_qualification'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('non_emp_pay_scale'+ps);
	if (res !== "true") {
		alert(res +"Employee Pay Scale");
		$('#non_emp_pay_scale'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('non_emp_designation'+ps);
	if (res !== "true") {
		alert(res +"Employee Designation");
		$('#non_emp_designation'+ps).focus();
		return false;
	}
	var hid_non_teaching_attachment = CheckNullorBlank('hid_non_teaching_attachment'+ps);
	if(hid_non_teaching_attachment !== "true"){
		var res = CheckNullorBlank('non_teaching_attachment'+ps);
		if (res !== "true") {
			alert("Please Upload Attachment");
			$('#non_teaching_attachment'+ps).focus();
			return false;
		}
	}
	var res = CheckNullorBlank('non_pan_no'+ps);
	if (res !== "true") {
		alert(res +"PAN Number");
		$('#non_pan_no'+ps).focus();
		return false;
	}
	var res = CheckNullorBlank('non_aadhar_no'+ps);
	if (res !== "true") {
		alert(res +"AADHAR Number");
		$('#non_aadhar_no'+ps).focus();
		return false;
	}
	var an = $("#non_aadhar_no"+ps).val();
	var minLength = 12;
	var charLength = an.length;
	if (charLength < minLength) {
		alert("Please Enter Valid Aadhar No.");
		$("input#non_aadhar_no"+ps).focus();
		return false;
	}
	var hid_non_exe_certi = CheckNullorBlank('hid_non_exe_certi'+ps);
	if(hid_non_exe_certi !== "true"){
		var res = CheckNullorBlank('non_exe_certi'+ps);
		if (res !== "true") {
			alert("Please Upload Document of Experience Certificate");
			$('#non_exe_certi'+ps).focus();
			return false;
		}
	}
	
	$("#indno_non_teaching_staff").val(ps);
	var form_data = new FormData(document.getElementById("non_teaching_staff"));
	$.ajax({
		url : 'Non_Teaching_Staff_Details_Save_Draft_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j){
      if(j>0){
    		$("#hid_non_teaching_staff"+ps).val(j);
    	  	alert("Data Save Sucessfully");
    	  	$("#add_non_teaching_staff" + ps).show();
			$("#delete_non_teaching_staff" + ps).show();
      }
      else{
    	  alert(j);
      }
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
	
}


function delete_Non_Teaching_Staff(R) {
	
	// 		var qualtification_idhid2= $("input#qualtification_id"+R).val();
	if(confirm('Are you sure you want to delete?')) {

	var hid_non_teaching_staff = $('#hid_non_teaching_staff' + R).val();
	
	$.post("delete_Non_Teaching_Staff_Details?" + key + "=" + value, {
		hid_non_teaching_staff : hid_non_teaching_staff
	}, function(j) {
		alert(j);
	});

	$("tr#tr_sibling_non_teaching_staff1" + R).remove();
	R = R - 1;
	$("input#count_hidden_att_name_med").val(att);
	$("#add_non_teaching_staff" + R).show();
	$("#delete_non_teaching_staff" + R).show();
	}else {
		return false;
	}
}


function getNon_Teaching_Staff_Details() {
	
	$.ajaxSetup({
		async : false
	});
	
	var ser = 0;

	$.post("getNon_Teaching_Staff_Details?" + key + "=" + value, {
		
	}, function(j) {
		
		for (var i = 0; i < j.length; i++) {
			ser = i + 1;

			if (j.length > 0) {

				if (ser > 1) {
					
					$("#add_non_teaching_staff" + (ser - 1)).click();
				}
				
				$("#hid_non_teaching_staff" + ser).val(j[i][0]);
				$("#non_prefix_id" + ser).val(j[i][3]);
				$("#non_first_name" + ser).val(j[i][4]);
				$("#non_middle_name" + ser).val(j[i][5]);
				$("#non_last_name" + ser).val(j[i][6]);
				$("#non_date_of_appoinment" + ser).val(j[i][7]);
				$("#non_nature_of_appoinment" + ser).val(j[i][8]);
				$("#non_emp_id" + ser).val(j[i][9]);
				$("#non_emp_department" + ser).val(j[i][10]);
				$("#non_emp_qualification" + ser).val(j[i][11]);
				$("#non_emp_pay_scale" + ser).val(j[i][12]);
				$("#non_emp_designation" + ser).val(j[i][13]);
				$("#non_pan_no" + ser).val(j[i][14]);
				$("#non_aadhar_no" + ser).val(j[i][15]);
				$("#hid_non_appoinment_letter" + ser).val(j[i][16]);
				$("#hid_non_exe_certi" + ser).val(j[i][17]);
				$("#hid_non_teaching_attachment" + ser).val(j[i][18]);
				$("#add_non_teaching_staff" + ser).show();
				$("#delete_non_teaching_staff" + ser).show();
			}
		}
	});
}

//END----> ADD MORE FOR NON TEACHING STAFF DETAILS

		
//==============================CSP START==============================

		document.addEventListener('DOMContentLoaded', function() {	
		
		//ADD MORE TEACHING FACULTY DETAILS
		document.getElementById('save_teaching_faculty1').onclick = function() {
			return Save_As_Draft_Teaching_Faculty(1);
		};
		document.getElementById('add_teaching_faculty1').onclick = function() {
			return formOpen_Teaching_Faculty(1);
		};
		document.getElementById('delete_teaching_faculty1').onclick = function() {
			return delete_Teaching_Faculty(1);
		};
		document.getElementById('first_name1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('middle_name1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('last_name1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
// 		document.getElementById('nature_of_appoinment1').onkeypress = function() {
// 			return onlyAlphabetsStringSpace(event, this);
// 		};
		document.getElementById('emp_id1').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('emp_department1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('emp_qualification1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('emp_pay_scale1').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('emp_designation1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('pan_no1').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('aadhar_no1').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('teaching_attachment1').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"teaching_attachment1","200kb","teaching_attachment_lbltik1","teaching_attachment_lbl1",this.accept)
		};
		document.getElementById('reg_no1').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		
		//ADD MORE GUEST FACULTY DETAILS
		document.getElementById('save_guest_faculty1').onclick = function() {
			return Save_As_Draft_Guest_Faculty(1);
		};
		document.getElementById('add_guest_faculty1').onclick = function() {
			return formOpen_Guest_Faculty(1);
		};
		document.getElementById('delete_guest_faculty1').onclick = function() {
			return delete_Guest_Faculty(1);
		};
		document.getElementById('guest_first_name1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('guest_middle_name1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('guest_last_name1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
// 		document.getElementById('guest_nature_of_appoinment1').onkeypress = function() {
// 			return onlyAlphabetsStringSpace(event, this);
// 		};
		document.getElementById('guest_emp_id1').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('guest_emp_department1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('guest_emp_qualification1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('guest_emp_pay_scale1').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('guest_emp_designation1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('guest_pan_no1').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('guest_aadhar_no1').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('appoinment_letter1').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"appoinment_letter1","200kb","appoinment_letter_lbltik1","appoinment_letter_lbl1",this.accept)
		};
		document.getElementById('exe_certi1').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"exe_certi1","200kb","exe_certi_lbltik1","exe_certi_lbl1",this.accept)
		};
		document.getElementById('guest_teaching_attachment1').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"guest_teaching_attachment1","200kb","guest_teaching_attachment_lbltik1","guest_teaching_attachment_lbl1",this.accept)
		};
		
		//ADD MORE NON TEACHING STAFF DETAILS
		document.getElementById('save_non_teaching_staff1').onclick = function() {
			return Save_As_Draft_Non_Teaching_Staff(1);
		};
		document.getElementById('add_non_teaching_staff1').onclick = function() {
			return formOpen_Non_Teaching_Staff(1);
		};
		document.getElementById('delete_non_teaching_staff1').onclick = function() {
			return delete_Non_Teaching_Staff(1);
		};
		document.getElementById('non_first_name1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('non_middle_name1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('non_last_name1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
// 		document.getElementById('non_nature_of_appoinment1').onkeypress = function() {
// 			return onlyAlphabetsStringSpace(event, this);
// 		};
		document.getElementById('non_emp_id1').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('non_emp_department1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('non_emp_qualification1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('non_emp_pay_scale1').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('non_emp_designation1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('non_pan_no1').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('non_aadhar_no1').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('non_appoinment_letter1').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"non_appoinment_letter1","200kb","non_appoinment_letter_lbltik1","non_appoinment_letter_lbl1",this.accept)
		};
		document.getElementById('non_exe_certi1').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"non_exe_certi1","200kb","non_exe_certi_lbltik1","non_exe_certi_lbl1",this.accept)
		};
		document.getElementById('non_teaching_attachment1').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"non_teaching_attachment1","200kb","non_teaching_attachment_lbltik1","non_teaching_attachment_lbl1",this.accept)
		};
		
		
	});
		
//==============================CSP END==============================	
	
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

$("#basic_clg_staff_list_id").val(id);
$("#statusview").val($("#statusA").val());
document.getElementById('viewForm').submit();
}
		
		
	</script>