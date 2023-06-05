<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- shivali -->
<link rel="stylesheet" href="assets/vendor/hamburger-menu/hemburgermenu.css">

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>View Other Hospital Details</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									View Other Hospital Details</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>


<div class="form-elements-wrapper tunnel-form preview-form custom-mobile-menu">
					<!-- humburger menu for collage regulation - part-1 start -->
			<div class="row viewmenu">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="hamburger-menu">
						<div class="bar" title="Click here for menu">
						</div>
						<div class="hm-title"><h4 class="hm-title-text">Other Hospital Details view form report</h4></div>
					</div>

					<nav class="mobile-menu">
						<h5 class="hm-menu-title">Form Reports Links</h5>
						<ul>
							<li><a id="basics_information_view">Institution Information</a></li>
<!-- 							<li class="has-children">About <span class="icon-arrow"></span> -->
<!-- 								<ul class="children"> -->
<!-- 									<li><a href="submenu1.html">Submenu #1</a></li> -->
<!-- 									<li><a href="submenu2.html">Submenu #2</a></li> -->
<!-- 									<li><a href="submenu3.html">Submenu #3</a></li> -->
<!-- 								</ul> -->
<!-- 							</li> -->



<!-- 							<li><a class="viewData">College Infrastructure</a> -->
<%-- 							<input type='hidden' id="viewId${getInstname[0].id}" value="${getInstname[0].id}"></li> --%>
<!-- 							<li><a href="college_department_view">College Department</a></li> -->
<!-- 							<li><a href="college_financial_view">College Financial</a></li> -->
<!-- 							<li><a href="student_details_view">Student Details</a></li> -->
<!-- 							<li><a href="college_staff_info_view">College Staff Information</a></li> -->
<!-- 							<li><a href="college_staff_list_view">College Staff List</a></li> -->
<!-- 							<li><a href="department_equipments_view">Department Equipments</a></li> -->
<!-- 							<li><a href="hospital_infrastructure_view">Hospital Infrastucture</a></li> -->
<!-- 							<li><a href="hospital_ipdopd_view">Hospital IPD OPD</a></li> -->
<!-- 							<li><a href="otherhospital_detail_view">Other Hospital Details</a></li> -->
<!-- 							<li><a href="hospital_staffdetails_view">Hospital Staff Details</a></li> -->
<!-- 							<li><a href="hospital_staff_list_view">Hospital Staff List</a></li> -->
<!-- 							<li><a href="hospital_equipments_view">Hospital Equipments</a></li> -->
<!-- 							<li><a href="declaration_view">Declaration</a></li> -->

							<li><a id="college_infrastructure_view">College Infrastructure</a></li>
							<li><a id="college_department_view">College Department</a></li>
							<li><a id="college_financial_view">College Financial</a></li>
							<li><a id="student_details_view_page">Student Details</a></li>
							<li><a id="college_staff_info_view">College Staff Information</a></li>
							<li><a id="college_staff_list_view">College Staff List</a></li>
							<li><a id="department_equipments_view">Department Equipments</a></li>
							<li><a id="hospital_infrastructure_view">Hospital Infrastucture</a></li>
							<li><a id="hospital_ipdopd_view">Hospital IPD OPD</a></li>
							<li><a id="otherhospital_detail_view">Other Hospital Details</a></li>
							<li><a id="hospital_staffdetails_view">Hospital Staff Details</a></li>
							<li><a id="hospital_staff_list_view">Hospital Staff List</a></li>
							<li><a id="hospital_equipments_view">Hospital Equipments</a></li>
							<li><a id="declaration_view">Declaration</a></li>
						</ul>
					</nav>
				</div>
			</div>
			<!-- humburger menu for collage regulation - part-1 end -->

		<div class="form-elements-wrapper tunnel-form preview-form">
			<form:form name="otherhospital_detail_view_form" id="otherhospital_detail_view_form" 
			action="" method="post" modelAttribute="">
				<div class="card-style mb-30">
					<!-- ===========================
							Other Hospital Details Details Start
						=========================== -->
					<div class="field-box">
						<div class="row">
						
							<input type="hidden" id="hid_college_infrastructure_remark" name="hid_college_infrastructure_remark" value="0">
						    <input type="hidden" id="institute_id" name="institute_id" value="0">
						    <input type="hidden" id="main_view_id" name="main_view_id" value="0">
						    
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Maintenance of Records</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModalMR"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Central Registration</label><span class="value-bind"
										id="cent_reg_Recordlba">
										${View_Other_hosp_maintenanceCMD.central_registration}</span>
								</div>
								<div class="select-position custom-d-none" >
													<select name="pre_cent_reg" id="pre_cent_reg"
														class="form-control">
														<option value="0" selected="selected">-- Select --</option>
														<option value="1">Select 1</option>
														<option value="2">Select 2</option>
													</select>
												</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>OPD Records</label> <span class="value-bind"
										id="per_opd_Recordlb">
										${View_Other_hosp_maintenanceCMD.opd_records}</span>
								</div>
								<div class="select-position custom-d-none" >
													<select name="pre_opd_Record" id="pre_opd_Record"
														class="form-control">
														<option value="0" selected="selected">-- Select --</option>
														<option value="1">Select 1</option>
														<option value="2">Select 2</option>
													</select>
												</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>IPD Records</label> <span class="value-bind"
										id="per_ipd_Recordlb">
										${View_Other_hosp_maintenanceCMD.ipd_records}</span>
								</div>
								<div class="select-position custom-d-none" >
													<select name="pre_ipd_reg" id="pre_ipd_reg"
														class="form-control">
														<option value="0" selected="selected">-- Select --</option>
														<option value="1">Select 1</option>
														<option value="2">Select 2</option>
													</select>
												</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Medical Record Room</label> <span class="value-bind"
										id="per_med_Recordlb">
										${View_Other_hosp_maintenanceCMD.medical_record_room}</span>
								</div>
								<div class="select-position custom-d-none" >
													<select name="pre_med_reg" id="pre_med_reg"
														class="form-control">
														<option value="0" selected="selected">-- Select --</option>
														<option value="1">Select 1</option>
														<option value="2">Select 2</option>
													</select>
												</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Account Section</label> <span class="value-bind"
										id="per_acc_Recordlb">
										${View_Other_hosp_maintenanceCMD.account_section}</span>
								</div>
								<div class="select-position custom-d-none" >
													<select name="pre_acc_reg" id="pre_acc_reg"
														class="form-control">
														<option value="0" selected="selected">-- Select --</option>
														<option value="1">Select 1</option>
														<option value="2">Select 2</option>
													</select>
												</div>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Labour Room</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModalLR"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Is Labour Room available?</label> <span
										class="value-bind" id="labour_roomlb">${View_Hospital_labour_room_Details2[0].labour_room}</span>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Is Antenatal Room with attached Toilet
										functional?</label> <span class="value-bind" id="">${View_Hospital_labour_room_Details2[0].antenatal_room}</span>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Are Facilities for Neonatal care available?</label> <span
										class="value-bind" id="">${View_Hospital_labour_room_Details2[0].neonatal_care}</span>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Are other facilities, Equipments, Instruments
										available?</label> <span class="value-bind" id=""> ${View_Hospital_labour_room_Details2[0].other_facilities}</span>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total number of Deliveries conducted in the last
										calendar year</label> <span class="value-bind" id="">${View_Hospital_labour_room_Details2[0].total_deliveries}</span>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Number of other procedures conducted in the last
										calendar year</label> <span class="value-bind" id="">${View_Hospital_labour_room_Details2[0].total_procedures}</span>
								</div>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Operation Theatre</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModalOT"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Is Operation Theatre Available ?</label> <span
										class="value-bind" id="">${View_Hospital_Operation_theatre_staff_Details6[0].operation_theatre}</span>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Does Air Conditioning exists ?</label> <span
										class="value-bind" id=""> ${View_Hospital_Operation_theatre_staff_Details6[0].air_condition}</span>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Does Pre Operative Room with attached toilet
										exists?</label> <span class="value-bind" id="">${View_Hospital_Operation_theatre_staff_Details6[0].pre_operative_room}</span>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Does Sterilization Room exists?</label> <span
										class="value-bind" id="">${View_Hospital_Operation_theatre_staff_Details6[0].sterilization_room}</span>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Does Changing and wash room with attached toilet
										exists?</label> <span class="value-bind" id="">${View_Hospital_Operation_theatre_staff_Details6[0].wash_room}</span>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Are other facilities, Equipments, Instruments
										available?</label> <span class="value-bind" id="">${View_Hospital_Operation_theatre_staff_Details6[0].other_facility}</span>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Is Fumigation facility available?</label> <span
										class="value-bind" id="">${View_Hospital_Operation_theatre_staff_Details6[0].fumigation_facility}</span>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total Number of operations done in the last
										calendar year</label> <span class="value-bind" id="">${View_Hospital_Operation_theatre_staff_Details6[0].total_operations}</span>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total number of minor procedures done in last
										calender year</label> <span class="value-bind" id="">${View_Hospital_Operation_theatre_staff_Details6[0].total_minor_procedures}</span>
								</div>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Investigations</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModalInvestigation"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="investigations_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Investigations done in the last calendar
															year</h6></th>
													<th><h6>
															Number<strong class="mandatory">*</strong>
														</h6></th>
												</tr>
											</thead>
											<tbody id="">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Total number of X-rays done</p>
													</td>
													<td><p>${View_Hospital_investigation_Details3[0].total_xray}</p></td>
												</tr>

												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Total number of ECG done</p>

													</td>
													<td><p>${View_Hospital_investigation_Details3[0].total_ecg}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Total number of UCG done</p>
													</td>
													<td><p>${View_Hospital_investigation_Details3[0].total_usg}</p></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</section>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Clinical Laboratory</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModalCL"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="clinicallab_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Investigations done in the last calendar
															year</h6></th>
													<th><h6>
															Number<strong class="mandatory">*</strong>
														</h6></th>
												</tr>
											</thead>
											<tbody id="">
												<tr>
													<td class="sr-no">

														<p></p>

													</td>
													<td>
														<p>Total Number of Hematological Tests</p>

													</td>
													<td><p>${View_Hospital_clinical_laboratory4[0].hematological_test}</p></td>
												</tr>

												<tr>
													<td class="sr-no">

														<p></p>

													</td>
													<td>
														<p>Total Number of Bio-Chemical Tests</p>

													</td>
													<td><p>${View_Hospital_clinical_laboratory4[0].bio_chemical_test}</p></td>
												</tr>

												<tr>
													<td class="sr-no">

														<p></p>

													</td>
													<td>
														<p>Total Number of Serological Test</p>

													</td>
													<td><p>${View_Hospital_clinical_laboratory4[0].serological_test}</p></td>
												</tr>

												<tr>
													<td class="sr-no">

														<p></p>

													</td>
													<td>
														<p>Total Number of Microbiological Tests</p>

													</td>
													<td><p>${View_Hospital_clinical_laboratory4[0].microbiological_test}</p></td>
												</tr>

												<tr>
													<td class="sr-no">

														<p></p>

													</td>
													<td>
														<p>Total number of Investigations done in the last
															calendar year</p>

													</td>
													<td><p>${View_Hospital_clinical_laboratory4[0].total_investigation}</p></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</section>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Verification of Functionality</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModalVF"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="vefication_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Records/Documents</h6></th>
													<th><h6>Available/Not available</h6></th>
													<th><h6>Remarks</h6></th>
												</tr>
												<!-- end table row-->
											</thead>
											<tbody id="">
												<tr>
													<td class="sr-no">

														<p></p>

													</td>
													<td>
														<p>Number and Name of OPD Department</p>

													</td>
													<td><p id="name_of_opdld">${View_Hospital_dtl_functionality5[0].name_of_opd}</p></td>
													<td><p>${View_Hospital_dtl_functionality5[0].name_of_opd_remark}</p></td>
													
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Original OPD case register</p>

													</td>
												<td><p id="opd_case_registerld">${View_Hospital_dtl_functionality5[0].opd_case_register}</p></td>
													<td><p>${View_Hospital_dtl_functionality5[0].opd_case_register_remark}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>OPD Medicine dispensing register</p>

													</td>
													<td><p id="opd_medicineld">${View_Hospital_dtl_functionality5[0].opd_medicine}</p></td>
													<td><p>${View_Hospital_dtl_functionality5[0].opd_medicine_remark}</p></td>
												</tr>

												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Cash receipts for OPD charges/lab charges</p>

													</td>
													<td><p id="case_receipt_opdld">${View_Hospital_dtl_functionality5[0].case_receipt_opd}</p></td>
													<td><p>${View_Hospital_dtl_functionality5[0].case_receipt_opd_remark}</p></td>
												</tr>

												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Number and Name of the IPD wards along with no. of
															beds</p>

													</td>
												<td><p id="name_of_ipdld">${View_Hospital_dtl_functionality5[0].name_of_ipd}</p></td>
													<td><p>${View_Hospital_dtl_functionality5[0].name_of_ipd_remark}</p></td>
												</tr>

												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Original IPD case sheets</p>

													</td>
													<td><p id="ipd_case_sheetsld">${View_Hospital_dtl_functionality5[0].ipd_case_sheets}</p></td>
													<td><p>${View_Hospital_dtl_functionality5[0].ipd_case_sheets_remark}</p></td>
												</tr>

												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Discharge Cards</p>

													</td>
													<td><p id="discharge_cardsld">${View_Hospital_dtl_functionality5[0].discharge_cards}</p></td>
													<td><p>${View_Hospital_dtl_functionality5[0].discharge_cards_remark}</p></td>
												</tr>

												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>IP Medicine indent register</p>

													</td>
													<td><p id="ip_medicineld">${View_Hospital_dtl_functionality5[0].ip_medicine}</p></td>
													<td><p>${View_Hospital_dtl_functionality5[0].ip_medicine_remark}</p></td>
												</tr>

												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Nursing staff duty roster</p>

													</td>
													<td><p id="nursing_duty_rosterld">${View_Hospital_dtl_functionality5[0].nursing_duty_roster}</p></td>
													<td><p>${View_Hospital_dtl_functionality5[0].nursing_duty_roster_remark}</p></td>
												</tr>

												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Doctors Duty Roster</p>

													</td>
													<td><p id="doctor_duty_roasterld">${View_Hospital_dtl_functionality5[0].doctor_duty_roaster}</p></td>
													<td><p>${View_Hospital_dtl_functionality5[0].doctor_duty_roaster_remark}</p></td>
												</tr>

												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>IP Diet Register</p>

													</td>
													<td><p id="ip_diet_registerld">${View_Hospital_dtl_functionality5[0].ip_diet_register}</p></td>
													<td><p>${View_Hospital_dtl_functionality5[0].ip_diet_register_remark}</p></td>
												</tr>

												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Cash Receipts for IPD charges/ Lab charges</p>

													</td>
													<td><p id="cash_receipt_ipdld">${View_Hospital_dtl_functionality5[0].cash_receipt_ipd}</p></td>
													<td><p>${View_Hospital_dtl_functionality5[0].cash_receipt_ipd_remark}</p></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</section>
						</div>
					
																				<div class="select-position custom-d-none">
																					<select name="all" id="all"
																						class="form-control">
																						<option value="0" selected="selected">--
																							Select--</option>
																						<option value="1">Available</option>
																						<option value="2">Not Available</option>
			
																					</select>
																				</div>
																			
					</div>
					
					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">MOU Details</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModalMOU"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Name of Multispecialty Hospital of Modern Medicine with which the College has MOU.</label> <span class="value-bind"
										id="">${View_Hospital_dtl_functionality5[0].name_of_multispecialty_hospital}</span>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Address of Multispecialty Hospital of Modern Medicine with which the College has MOU.</label> <span class="value-bind" 
									id="">${View_Hospital_dtl_functionality5[0].address_of_multispecialty_hospital}</span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Date of MoU Sign</label> <span class="value-bind" 
									id="">${View_Hospital_dtl_functionality5[0].date_of_mou_signl}</span>
								</div>
							</div>
							
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Validity of MoU</label> <span class="value-bind" 
									id="">${View_Hospital_dtl_functionality5[0].validity_of_mou}</span>
								</div>
							</div>
							
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Area in Which MoU Centered</label> <span class="value-bind" 
									id="">${View_Hospital_dtl_functionality5[0].area_of_mou}</span>
								</div>
							</div>
							
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Investigation Attend in the MoU Hospital</label> <span class="value-bind" 
									id="">${View_Hospital_dtl_functionality5[0].investigation_of_hospital}</span>
								</div>
							</div>
							
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Training Provided for MoU Hospital Classes Taken by the Doctors from MoU Hospital for Student of the Homeopathy</label> <span class="value-bind" 
									id="">${View_Hospital_dtl_functionality5[0].training_details}</span>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
							<input type="hidden" id="Other_hospital_detlview_hid" value="${View_Hospital_dtl_functionality5[0].id}" name="Other_hospital_detlview_hid" class="form-control"> 
								<div class="custom-data-value">
									<label>Document for MOU with Multispeciality Hospital</label> 
									<span class="value-bind daobtn" id=""> <a href="#"
										class="main-btn dark-btn btn-hover btn-sm btnview"
										title="View" id="doc_multi_spe_hos_view"><i class="lni lni-eye"></i></a></span>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>IPD Diet Register Document</label>
									<span class="value-bind daobtn" id=""> <a href="#"
										class="main-btn dark-btn btn-hover btn-sm btnview"
										title="View" id="doc_ipd_diet_reg_view"><i class="lni lni-eye"></i></a></span>
								</div>
							</div>

						</div>
					</div>
					
					<!-- ===========================
							Other Hospital Details Details End
						=========================== -->
				</div>
				
				
<!-- Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModalMR">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData1" name="headData1">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="other_hos_dtl_maint_record_rmk" name="other_hos_dtl_maint_record_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="Other_Hospital_MR_btn">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

<!-- Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModalLR">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData1" name="headData1">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="other_hos_dtl_labour_room_rmk" name="other_hos_dtl_labour_room_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="Other_Hospital_LR_btn">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

<!-- Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModalOT">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData1" name="headData1">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="other_hos_dtl_ot_rmk" name="other_hos_dtl_ot_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="Other_Hospital_OT_btn">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

<!-- Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModalInvestigation">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData1" name="headData1">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="other_hos_dtl_investi_rmk" name="other_hos_dtl_investi_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="Other_Hospital_Investi_btn">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->


<!-- Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModalCL">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData1" name="headData1">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="other_hos_dtl_clini_lab_rmk" name="other_hos_dtl_clini_lab_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="Other_Hospital_CL_btn">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

<!-- Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModalVF">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData1" name="headData1">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="other_hos_dtl_verifi_of_func_rmk" name="other_hos_dtl_verifi_of_func_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="Other_Hospital_VF_btn">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

<!-- Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModalMOU">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData1" name="headData1">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="other_hos_dtl_mou_rmk" name="other_hos_dtl_mou_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="Other_Hospital_MOU_btn">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->
			</form:form>
		</div>
	</div>
</section>



<!-- Canvas Modal start -->

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
							<div class="alert alert-success alert-dismissible">
								<button type="button" class="btn-close" data-bs-dismiss="alert"></button>

							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-12 text-center">
                        <embed  src="" id="pdfmodelcanvas" width="1000" height="450">
<!-- 							<div id="pdfmodelcanvas"></div> -->

						</div>
					</div>
				</div>


			</div>
			<div class="modal-footer" id="modal-footer">
			<ul class="buttons-group">
								<li><a type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal">Close</a></li>
							
							</ul>
			
			</div>


		</div>
	</div>
</div>


<!---------canvas end========================= -->


<!-- hemburgermenu js start -->
<script src="assets/vendor/hamburger-menu/hemburgermenu.js" type="text/javascript"></script>
<!-- hemburgermenu js end -->

<%-- <c:url value="View_Search_Basic_InfoUrl" var="viewUrl" /> --%>
<%-- <form:form action="${viewUrl}" method="post" id="viewForm_basic_info" --%>
<%-- 	name="viewForm" modelAttribute="basic_info_id"> --%>
<!-- 	<input type="hidden" name="basic_info_id" id="basic_info_id" value="0" /> -->
<%-- </form:form> --%>
<c:url value="View_Search_Basic_InfoUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_basic_info"
	name="viewForm" modelAttribute="id6">
	<input type="hidden" name="id6" id="id6" value="0" />
	<input type="hidden" name="id7" id="id7" value="0" />
</form:form>
<c:url value="View_Search_College_InfrastructureUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_college_infra"
	name="viewForm" modelAttribute="college_infra_id">
	<input type="hidden" name="college_infra_id" id="college_infra_id" value="0" />
</form:form>
<c:url value="View_Search_College_DepartmentUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_college_department"
	name="viewForm" modelAttribute="college_department_id">
	<input type="hidden" name="college_department_id" id="college_department_id" value="0" />
</form:form>
<c:url value="View_Search_College_FinancialUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_college_financial"
	name="viewForm" modelAttribute="college_financial_id">
	<input type="hidden" name="college_financial_id" id="college_financial_id" value="0" />
</form:form>
<c:url value="View_Search_Student_DetailsUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_student_dtl"
	name="viewForm" modelAttribute="student_dtl_id">
	<input type="hidden" name="student_dtl_id" id="student_dtl_id" value="0" />
</form:form>
<c:url value="View_College_Staff_InfoUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_clg_staff_info"
	name="viewForm" modelAttribute="clg_staff_info_id">
	<input type="hidden" name="clg_staff_info_id" id="clg_staff_info_id" value="0" />
</form:form>
<c:url value="College_Staff_List_ViewUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_basic_clg_staff_list"
	name="viewForm" modelAttribute="basic_clg_staff_list_id">
	<input type="hidden" name="basic_clg_staff_list_id" id="basic_clg_staff_list_id" value="0" />
</form:form>
<c:url value="View_Department_Equipment_ReportUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_department_eqp"
	name="viewForm" modelAttribute="department_eqp_id">
	<input type="hidden" name="department_eqp_id" id="department_eqp_id" value="0" />
</form:form>
<c:url value="hospital_infrastructure_view" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_hos_infra"
	name="viewForm" modelAttribute="hos_infra_id">
	<input type="hidden" name="hos_infra_id" id="hos_infra_id" value="0" />
</form:form>
<c:url value="hospital_ipdopd_view" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_hos_ipd"
	name="viewForm" modelAttribute="hos_ipd_id">
	<input type="hidden" name="hos_ipd_id" id="hos_ipd_id" value="0" />
</form:form>
<c:url value="otherhospital_detail_view" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_other_hos_dtl"
	name="viewForm" modelAttribute="other_hos_dtl_id">
	<input type="hidden" name="other_hos_dtl_id" id="other_hos_dtl_id" value="0" />
</form:form>
<c:url value="hospital_staffdetails_view" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_hos_staff_dtl"
	name="viewForm" modelAttribute="hos_staff_dtl_id">
	<input type="hidden" name="hos_staff_dtl_id" id="hos_staff_dtl_id" value="0" />
</form:form>
<c:url value="hospital_staff_list_view" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_hos_staff_list"
	name="viewForm" modelAttribute="hos_staff_list_id">
	<input type="hidden" name="hos_staff_list_id" id="hos_staff_list_id" value="0" />
</form:form>
<c:url value="hospital_equipments" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_hos_eqp"
	name="viewForm" modelAttribute="hos_eqp_id">
	<input type="hidden" name="hos_eqp_id" id="hos_eqp_id" value="0" />
</form:form>
<c:url value="Declaration_ViewUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_declaration"
	name="viewForm" modelAttribute="declaration_id">
	<input type="hidden" name="declaration_id" id="declaration_id" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	
	if('${role}'!="NCH"){
		$(".btnedit").css("visibility", "hidden");
		$(".viewmenu").addClass("d-none");
	}
	
	///reamrk
	$("#institute_id").val('${inst_id}');
	$("#main_view_id").val('${main_view_id}');

	var getView_idFrom_Institute_id ='${getView_idFrom_Institute_id}';
	if(getView_idFrom_Institute_id != "[]"){
		$("#hid_college_infrastructure_remark").val('${getView_idFrom_Institute_id[0].id}');
		$("#other_hos_dtl_maint_record_rmk").val('${getView_idFrom_Institute_id[0].other_hos_dtl_maint_record_rmk}');
		$("#other_hos_dtl_labour_room_rmk").val('${getView_idFrom_Institute_id[0].other_hos_dtl_labour_room_rmk}');
		$("#other_hos_dtl_ot_rmk").val('${getView_idFrom_Institute_id[0].other_hos_dtl_ot_rmk}');
		$("#other_hos_dtl_investi_rmk").val('${getView_idFrom_Institute_id[0].other_hos_dtl_investi_rmk}');
		$("#other_hos_dtl_clini_lab_rmk").val('${getView_idFrom_Institute_id[0].other_hos_dtl_clini_lab_rmk}');
		$("#other_hos_dtl_verifi_of_func_rmk").val('${getView_idFrom_Institute_id[0].other_hos_dtl_verifi_of_func_rmk}');
		$("#other_hos_dtl_mou_rmk").val('${getView_idFrom_Institute_id[0].other_hos_dtl_mou_rmk}');

		
	}
	
	$("select#pre_cent_reg").val(${View_Other_hosp_maintenanceCMD.central_registration});
	 var service = $('select#pre_cent_reg').find(":selected").text();
	$("#cent_reg_Recordlba").text(service);
	
	$("select#pre_opd_Record").val(${View_Other_hosp_maintenanceCMD.opd_records});
	 var service = $('select#pre_opd_Record').find(":selected").text();
	$("#per_opd_Recordlb").text(service);
	
	$("select#pre_ipd_reg").val(${View_Other_hosp_maintenanceCMD.ipd_records});
	 var service = $('select#pre_ipd_reg').find(":selected").text();
	$("#per_ipd_Recordlb").text(service);
	
	$("select#pre_med_reg").val(${View_Other_hosp_maintenanceCMD.medical_record_room});
	 var service = $('select#pre_med_reg').find(":selected").text();
	$("#per_med_Recordlb").text(service);
	
	$("select#pre_acc_reg").val(${View_Other_hosp_maintenanceCMD.account_section});
	 var service = $('select#pre_acc_reg').find(":selected").text();
	$("#per_acc_Recordlb").text(service);
	
	//=================================================================================//
	
	$("select#all").val(${View_Hospital_dtl_functionality5[0].name_of_opd});
	 var service = $('select#all').find(":selected").text();
	$("#name_of_opdld").text(service);
	
	$("select#all").val(${View_Hospital_dtl_functionality5[0].opd_case_register});
	 var service = $('select#all').find(":selected").text();
	$("#opd_case_registerld").text(service);
	
	$("select#all").val(${View_Hospital_dtl_functionality5[0].opd_medicine});
	 var service = $('select#all').find(":selected").text();
	$("#opd_medicineld").text(service);
	
	$("select#all").val(${View_Hospital_dtl_functionality5[0].case_receipt_opd});
	 var service = $('select#all').find(":selected").text();
	$("#case_receipt_opdld").text(service);
	
	$("select#all").val(${View_Hospital_dtl_functionality5[0].name_of_ipd});
	 var service = $('select#all').find(":selected").text();
	$("#name_of_ipdld").text(service);
	
	$("select#all").val(${View_Hospital_dtl_functionality5[0].ipd_case_sheets});
	 var service = $('select#all').find(":selected").text();
	$("#ipd_case_sheetsld").text(service);
	
	$("select#all").val(${View_Hospital_dtl_functionality5[0].discharge_cards});
	 var service = $('select#all').find(":selected").text();
	$("#discharge_cardsld").text(service);
	
	$("select#all").val(${View_Hospital_dtl_functionality5[0].ip_medicine});
	 var service = $('select#all').find(":selected").text();
	$("#ip_medicineld").text(service);
	
	$("select#all").val(${View_Hospital_dtl_functionality5[0].nursing_duty_roster});
	 var service = $('select#all').find(":selected").text();
	$("#nursing_duty_rosterld").text(service);
	
	$("select#all").val(${View_Hospital_dtl_functionality5[0].doctor_duty_roaster});
	 var service = $('select#all').find(":selected").text();
	$("#doctor_duty_roasterld").text(service);
	
	$("select#all").val(${View_Hospital_dtl_functionality5[0].ip_diet_register});
	 var service = $('select#all').find(":selected").text();
	$("#ip_diet_registerld").text(service);
	
	$("select#all").val(${View_Hospital_dtl_functionality5[0].cash_receipt_ipd});
	 var service = $('select#all').find(":selected").text();
	$("#cash_receipt_ipdld").text(service);
	
	
	
});


document.addEventListener('DOMContentLoaded', function() {
	
	if('${role}'=="NCH"){
	document.getElementById('basics_information_view').onclick = function() {
		ViewData_basic_info();
	};
	document.getElementById('college_infrastructure_view').onclick = function() {
		ViewData_college_infra();
	};
	document.getElementById('college_department_view').onclick = function() {
		ViewData_college_department();
	};
	document.getElementById('college_financial_view').onclick = function() {
		ViewData_college_financial();
	};
	document.getElementById('student_details_view_page').onclick = function() {
		ViewData_student_dtl();
	};
	document.getElementById('college_staff_info_view').onclick = function() {
		ViewData_clg_staff_info();
	};
	document.getElementById('college_staff_list_view').onclick = function() {
		ViewData_clg_staff_list();
	};
	document.getElementById('department_equipments_view').onclick = function() {
		ViewData_department_eqp();
	};
	document.getElementById('hospital_infrastructure_view').onclick = function() {
		ViewData_hos_infra();
	};
	
	document.getElementById('hospital_ipdopd_view').onclick = function() {
		ViewData_hos_ipd();
	};
	document.getElementById('otherhospital_detail_view').onclick = function() {
		ViewData_other_hos_dtl();
	};
	document.getElementById('hospital_staffdetails_view').onclick = function() {
		ViewData_hos_staff_dtl();
	};
	document.getElementById('hospital_staff_list_view').onclick = function() {
		ViewData_hos_staff_list();
	};
	document.getElementById('hospital_equipments_view').onclick = function() {
		ViewData_hos_eqp();
	};
	document.getElementById('declaration_view').onclick = function() {
		ViewData_declaration();
	};
	
	

	////////save modal button////////////////////
	document.getElementById('Other_Hospital_MR_btn').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('Other_Hospital_LR_btn').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('Other_Hospital_OT_btn').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('Other_Hospital_Investi_btn').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('Other_Hospital_CL_btn').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('Other_Hospital_VF_btn').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('Other_Hospital_MOU_btn').onclick = function() {
		Save_As_Draft_Remarks();
	};
	}
	
// 	document View

	document.getElementById('doc_multi_spe_hos_view').onclick = function() {
		file_view($("#Other_hospital_detlview_hid").val(),"clg_reg_other_hos_dtl_functionality","doc_of_multispecialty_hospital");
	};
	document.getElementById('doc_ipd_diet_reg_view').onclick = function() {
		file_view($("#Other_hospital_detlview_hid").val(),"clg_reg_other_hos_dtl_functionality","ipd_diet_register_doc");
	};

});


function ViewData_basic_info(){
	
	var institute_id  = $("#institute_id").val();
// 	$("#basic_info_id").val(institute_id);
	$("#id6").val(institute_id);
	$("#id7").val(institute_id);
	document.getElementById('viewForm_basic_info').submit();
}
function ViewData_college_infra(){
	
	var institute_id  = $("#institute_id").val();
	$("#college_infra_id").val(institute_id);
	document.getElementById('viewForm_college_infra').submit();
}
function ViewData_college_department(){
	
	var institute_id  = $("#institute_id").val();
	$("#college_department_id").val(institute_id);
	document.getElementById('viewForm_college_department').submit();
}
function ViewData_college_financial(){
	
	var institute_id  = $("#institute_id").val();
	$("#college_financial_id").val(institute_id);
	document.getElementById('viewForm_college_financial').submit();
}
function ViewData_student_dtl(){
	
	var institute_id  = $("#institute_id").val();
	$("#student_dtl_id").val(institute_id);
	document.getElementById('viewForm_student_dtl').submit();
}
function ViewData_clg_staff_info(){
	
	var institute_id  = $("#institute_id").val();
	$("#clg_staff_info_id").val(institute_id);
	document.getElementById('viewForm_clg_staff_info').submit();
}
function ViewData_clg_staff_list(){
	
	var institute_id  = $("#institute_id").val();
	$("#basic_clg_staff_list_id").val(institute_id);
	document.getElementById('viewForm_clg_staff_list').submit();
}
function ViewData_department_eqp(){
	
	var institute_id  = $("#institute_id").val();
	$("#department_eqp_id").val(institute_id);
	document.getElementById('viewForm_department_eqp').submit();
}
function ViewData_hos_infra(){
	
	var institute_id  = $("#institute_id").val();
	$("#hos_infra_id").val(institute_id);
	document.getElementById('viewForm_hos_infra').submit();
}
function ViewData_hos_ipd(){
	
	var institute_id  = $("#institute_id").val();
	$("#hos_ipd_id").val(institute_id);
	document.getElementById('viewForm_hos_ipd').submit();
}
function ViewData_other_hos_dtl(){
	
	var institute_id  = $("#institute_id").val();
	$("#other_hos_dtl_id").val(institute_id);
	document.getElementById('viewForm_other_hos_dtl').submit();
}
function ViewData_hos_staff_dtl(){
	
	var institute_id  = $("#institute_id").val();
	$("#hos_staff_dtl_id").val(institute_id);
	document.getElementById('viewForm_hos_staff_dtl').submit();
}
function ViewData_hos_staff_list(){
	
	var institute_id  = $("#institute_id").val();
	$("#hos_staff_list_id").val(institute_id);
	document.getElementById('viewForm_hos_staff_list').submit();
}
function ViewData_hos_eqp(){
	
	var institute_id  = $("#institute_id").val();
	$("#hos_eqp_id").val(institute_id);
	document.getElementById('viewForm_hos_eqp').submit();
}
function ViewData_declaration(){
	
	var institute_id  = $("#institute_id").val();
	$("#declaration_id").val(institute_id);
	document.getElementById('viewForm_declaration').submit();
}
//save Remark
function Save_As_Draft_Remarks() {

	$.ajaxSetup({
	    async: false
	});	
		
		var form_data = new FormData(document.getElementById("otherhospital_detail_view_form"));
		$.ajax({
			url : 'College_Infrastructure_Remark_Save?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(j){
	      if(j>0){
	    		$("#hid_college_infrastructure_remark").val(j);
	    	  	alert("Data Save Sucessfully");
	      }
	      else{
	    	  alert(j);
	      }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	}
//view Document

function file_view(id,val,field) {
	 $("#exampleModal").modal('show');
	 var pdf1="CommonDocPartOne?kmlId2="+id+"&val444="+val+"&fildname1="+field+" ";
	 $("#pdfmodelcanvas") .attr('src',pdf1);
}

</script>


