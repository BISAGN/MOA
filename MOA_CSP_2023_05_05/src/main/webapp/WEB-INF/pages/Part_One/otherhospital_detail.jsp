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

	<section class="dashboard-page">
		<div class="container-fluid">
			<!-- title-wrapper start -->
			<div class="title-wrapper pt-30">
				<div class="row align-items-center">
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="title mb-30">
							<h2>Other Hospital Details</h2>
						</div>
					</div>
					<!-- end col -->
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="breadcrumb-wrapper mb-30">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
									<li class="breadcrumb-item active" aria-current="page">
										Other Hospital Details</li>
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
							Other Hospital Details Details Start
						=========================== -->
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<!-- input style start -->
						<div class="card-style mb-30">
							<div class="tabs content_h750">
								<!-- Maintenance of Records Start -->
								<div class="tab">
									<button class="tab-toggle" id="defaultOpen">Maintenance of Records</button>
								</div>
								<div class="content">
									<h4 class="heading">Maintenance of Records</h4>
									<form:form name="maintenance_record" id="maintenance_record" action="" method="post" class=""
										commandName="">
										
										<div class="row">
										
										<input type="hidden" id="hid_maintenance_record" name="hid_maintenance_record" value="0">
										<input type="hidden" id="basic_info_id" name="basic_info_id" value="0">
										
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="select-style-1">
												<label>Central Registration<strong class="mandatory">*
												</strong></label>
												<div class="select-position">
													<select name="central_registration" id="central_registration"
														class="form-control">
														<option value="0" selected="selected">-- Select --</option>
														<option value="1">Select 1</option>
														<option value="2">Select 2</option>
													</select>
												</div>
											</div>
										</div>
										
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="select-style-1">
												<label>OPD Records<strong class="mandatory">*
												</strong></label>
												<div class="select-position">
													<select name="opd_records" id="opd_records"
														class="form-control">
														<option value="0" selected="selected">-- Select --</option>
														<option value="1">Select 1</option>
														<option value="2">Select 2</option>
													</select>
												</div>
											</div>
										</div>
										
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="select-style-1">
												<label>IPD Records<strong class="mandatory">*
												</strong></label>
												<div class="select-position">
													<select name="ipd_records" id="ipd_records"
														class="form-control">
														<option value="0" selected="selected">-- Select --</option>
														<option value="1">Select 1</option>
														<option value="2">Select 2</option>
													</select>
												</div>
											</div>
										</div>
										
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="select-style-1">
												<label>Medical record room<strong class="mandatory">*
												</strong></label>
												<div class="select-position">
													<select name="medical_record_room" id="medical_record_room"
														class="form-control">
														<option value="0" selected="selected">-- Select --</option>
														<option value="1">Select 1</option>
														<option value="2">Select 2</option>
													</select>
												</div>
											</div>
										</div>
										
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="select-style-1">
												<label>Account Section<strong class="mandatory">*
												</strong></label>
												<div class="select-position">
													<select name="account_section" id="account_section"
														class="form-control">
														<option value="0" selected="selected">-- Select --</option>
														<option value="1">Select 1</option>
														<option value="2">Select 2</option>
													</select>
												</div>
											</div>
										</div>
									</div>
										
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover btnsave" id="maintenance_record_save"
															type="button" value="Save"></li>
															
														<li><a class="main-btn dark-btn-outline btn-hover btn-iconic-icon viewData d-none"><i
																	class="lni lni-eye"></i>View</a>
																	<input type='hidden' id="viewId${parent_id}" value="${institude}"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- Maintenance of Records End -->
								<!-- Labour Room Start -->
								<div class="tab" id="labour_room_btn">
									<button class="tab-toggle">Labour Room</button>
								</div>
								<div class="content">
									<h4 class="heading">Labour Room</h4>
									<form:form name="labour_room_form" id="labour_room_form" action="" method="post" class=""
										commandName="">
										<div class="row">
										
										<input type="hidden" id="hid_labour_room" name="hid_labour_room" value="0">
										
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<label>Is Labour Room available?<strong class="mandatory">*</strong>
													</label>
												<div class="input-style-form-check">
													<div class="form-check radio-style">
														<input type="radio" id="labour_room_yes" name="labour_room" class="form-check-input" value="YES"> 
														<label for="labouravailableYes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="labour_room_no" name="labour_room" class="form-check-input" value="NO"> 
														<label for="labouravailableNo" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_antenatal_room">
											<label>Is Antenatal Room with attached Toilet functional?<strong class="mandatory">*</strong>
													</label>
												<div class="input-style-form-check">
													<div class="form-check radio-style">
														<input type="radio" id="antenatal_room_yes" name="antenatal_room" class="form-check-input" value="YES"> 
														<label for="antenaavailableYes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="antenatal_room_no" name="antenatal_room" class="form-check-input" value="NO"> 
														<label for="antenaavailableNo" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_neonatal_care">
											<label>Are Facilities for Neonatal care available?<strong class="mandatory">*</strong>
													</label>
												<div class="input-style-form-check">
													<div class="form-check radio-style">
														<input type="radio" id="neonatal_care_yes" name="neonatal_care" class="form-check-input" value="YES"> 
														<label for="neonavailableYes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="neonatal_care_no" name="neonatal_care" class="form-check-input" value="NO"> 
														<label for="neonavailableNo" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_other_facilities">
												<label>Are other facilities, Equipments, Instruments available?<strong class="mandatory">*</strong>
													</label>
												<div class="input-style-form-check">
													<div class="form-check radio-style">
														<input type="radio" id="other_facilities_yes" name="other_facilities" class="form-check-input" value="YES"> 
														<label for="laotheravailableYes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="other_facilities_no" name="other_facilities" class="form-check-input" value="NO"> 
														<label for="laotheravailableNo" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_total_deliveries">
												<div class="input-style-1">
													<label>Total number of Deliveries conducted in the last calendar year<strong class="mandatory">*</strong>
													</label> <input type="text" name="total_deliveries" id="total_deliveries" class="form-control" placeholder="Total Deliveries" maxlength="10">
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_total_procedures">
												<div class="input-style-1">
													<label>Number of other procedures conducted in the last calendar year<strong class="mandatory">*</strong>
													</label> <input type="text" name="total_procedures" id="total_procedures" class="form-control" placeholder="Total Procedures" maxlength="10">
												</div>
											</div>
										</div>
										
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover" id="labour_room_save"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- Labour Room End-->
								<!-- Operation Theatre Start -->
								<div class="tab" id="operation_theatre_btn">
									<button class="tab-toggle">Operation Theatre</button>
								</div>
								<div class="content">
									<h4 class="heading">Operation Theatre</h4>
									<form:form name="operation_theatre_form" id="operation_theatre_form" action="" method="post" class=""
										commandName="">
										<div class="row">
										
										<input type="hidden" id="hid_operation_theatre" name="hid_operation_theatre" value="0">
										
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<label>Is Operation Theatre Available ?<strong class="mandatory">*</strong>
													</label>
												<div class="input-style-form-check">
													<div class="form-check radio-style">
														<input type="radio" id="operation_theatre_yes" name="operation_theatre" class="form-check-input" value="YES"> 
														<label for="otavailableYes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="operation_theatre_no" name="operation_theatre" class="form-check-input" value="NO"> 
														<label for="otavailableNo" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_air_condition">
											<label>Does Air Conditioning exists ?<strong class="mandatory">*</strong>
													</label>
												<div class="input-style-form-check">
													<div class="form-check radio-style">
														<input type="radio" id="air_condition_yes" name="air_condition" class="form-check-input" value="YES"> 
														<label for="acavailableYes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="air_condition_no" name="air_condition" class="form-check-input" value="NO"> 
														<label for="acavailableNo" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_pre_operative_room">
											<label>Does Pre Operative Room with attached toilet exists?<strong class="mandatory">*</strong>
													</label>
												<div class="input-style-form-check">
													<div class="form-check radio-style">
														<input type="radio" id="pre_operative_room_yes" name="pre_operative_room" class="form-check-input" value="YES"> 
														<label for="preopeavailableYes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="pre_operative_room_no" name="pre_operative_room" class="form-check-input" value="NO"> 
														<label for="preopeavailableNo" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_sterilization_room">
											<label>Does Sterilization Room exists?<strong class="mandatory">*</strong>
													</label>
												<div class="input-style-form-check">
													<div class="form-check radio-style">
														<input type="radio" id="sterilization_room_yes" name="sterilization_room" class="form-check-input" value="YES"> 
														<label for="sterilizationavailableYes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="sterilization_room_no" name="sterilization_room" class="form-check-input" value="NO"> 
														<label for="sterilizationavailableNo" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_wash_room">
												<label>Does Changing and wash room with attached toilet exists?<strong class="mandatory">*</strong>
													</label>
												<div class="input-style-form-check">
													<div class="form-check radio-style">
														<input type="radio" id="wash_room_yes" name="wash_room" class="form-check-input" value="YES"> 
														<label for="washavailableYes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="wash_room_no" name="wash_room" class="form-check-input" value="NO"> 
														<label for="washavailableNo" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_other_facility">
												<label>Are other facilities, Equipments, Instruments available?<strong class="mandatory">*</strong>
													</label>
												<div class="input-style-form-check">
													<div class="form-check radio-style">
														<input type="radio" id="other_facility_yes" name="other_facility" class="form-check-input" value="YES"> 
														<label for="otheravailableYes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="other_facility_no" name="other_facility" class="form-check-input" value="NO"> 
														<label for="otheravailableNo" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_fumigation_facility">
												<label>Is Fumigation facility available?<strong class="mandatory">*</strong>
													</label>
												<div class="input-style-form-check">
													<div class="form-check radio-style">
														<input type="radio" id="fumigation_facility_yes" name="fumigation_facility" class="form-check-input" value="YES"> 
														<label for="fumigaavailableYes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="fumigation_facility_no" name="fumigation_facility" class="form-check-input" value="NO"> 
														<label for="fumigaavailableNo" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_total_operations">
												<div class="input-style-1">
													<label>Total Number of operations done in the last calendar year<strong class="mandatory">*</strong>
													</label> <input type="text" name="total_operations" id="total_operations" class="form-control" placeholder="Total Operations" maxlength="10">
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_total_minor_procedures">
												<div class="input-style-1">
													<label>Total number of minor procedures done in last calender year<strong class="mandatory">*</strong>
													</label> <input type="text" name="total_minor_procedures" id="total_minor_procedures" class="form-control" placeholder="Total Minor Procedures" maxlength="10">
												</div>
											</div>
										</div>
										
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover" id="operation_theatre_save"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- Operation Theatre End-->
								<!-- Investigations Start -->
								<div class="tab" id="investigation_btn">
									<button class="tab-toggle">Investigations</button>
								</div>
								<div class="content">
									<h4 class="heading">Investigations</h4>
									<form:form name="investigation_form" id="investigation_form" action="" method="post" class=""
										commandName="">
												<div class="row">
												
												<input type="hidden" id="hid_investigation" name="hid_investigation" value="0">
												
													<div class="col-lg-12 col-md-12 col-sm-12 col-12">
														<div class="table-wrapper table-responsive custom-table">
															<table class="table" id="investigations_table">
																<thead>
																	<tr>
																		<th><h6>Sr No</h6></th>
																		<th><h6>Investigations done in the last calendar year</h6></th>
																		<th><h6>
																				Number<strong class="mandatory">*</strong>
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
																			<p>Total number of X-rays done</p>

																		</td>
																		<td>
																			<div class="input-style-1">
																				<input type="text" name="total_xray" id="total_xray"
																					class="form-control" placeholder="Total Number" maxlength="10">
																			</div>

																		</td>
																	</tr>
																	
																	<tr>
																		<td class="sr-no">

																			<p></p>

																		</td>
																		<td>
																			<p>Total number of ECG done</p>

																		</td>
																		<td>
																			<div class="input-style-1">
																				<input type="text" name="total_ecg" id="total_ecg"
																					class="form-control" placeholder="Total Number" maxlength="10">
																			</div>

																		</td>
																	</tr>
																	
																	<tr>
																		<td class="sr-no">

																			<p></p>

																		</td>
																		<td>
																			<p>Total number of USG done</p>

																		</td>
																		<td>
																			<div class="input-style-1">
																				<input type="text" name="total_usg" id="total_usg"
																					class="form-control" placeholder="Total Number" maxlength="10">
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
										
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover" id="investigation_save"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- Investigations End -->
								<!-- Clinical Laboratory Start -->
								<div class="tab" id="clinical_laboratory_btn">
									<button class="tab-toggle">Clinical Laboratory</button>
								</div>
								<div class="content">
									<h4 class="heading">Clinical Laboratory</h4>
									<form:form name="clinical_laboratory_form" id="clinical_laboratory_form" action="" method="post" class=""
										commandName="">
										
											<div class="row">
											
											<input type="hidden" id="hid_clinical_laboratory" name="hid_clinical_laboratory" value="0">
											
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
														<div class="table-wrapper table-responsive custom-table">
															<table class="table" id="clinicallab_table">
																<thead>
																	<tr>
																		<th><h6>Sr No</h6></th>
																		<th><h6>Investigations done in the last calendar year</h6></th>
																		<th><h6>
																				Number<strong class="mandatory">*</strong>
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
																			<p>Total Number of Hematological Tests</p>

																		</td>
																		<td>
																			<div class="input-style-1">
																				<input type="text" name="hematological_test" id="hematological_test"
																					class="form-control" placeholder="Total Number" maxlength="10">
																			</div>

																		</td>
																	</tr>
																	
																	<tr>
																		<td class="sr-no">

																			<p></p>

																		</td>
																		<td>
																			<p>Total Number of Bio-Chemical Tests</p>

																		</td>
																		<td>
																			<div class="input-style-1">
																				<input type="text" name="bio_chemical_test" id="bio_chemical_test"
																					class="form-control" placeholder="Total Number" maxlength="10">
																			</div>

																		</td>
																	</tr>
																	
																	<tr>
																		<td class="sr-no">

																			<p></p>

																		</td>
																		<td>
																			<p>Total Number of Serological Test</p>

																		</td>
																		<td>
																			<div class="input-style-1">
																				<input type="text" name="serological_test" id="serological_test"
																					class="form-control" placeholder="Total Number" maxlength="10">
																			</div>

																		</td>
																	</tr>
																	
																	<tr>
																		<td class="sr-no">

																			<p></p>

																		</td>
																		<td>
																			<p>Total Number of Microbiological Tests</p>

																		</td>
																		<td>
																			<div class="input-style-1">
																				<input type="text" name="microbiological_test" id="microbiological_test"
																					class="form-control" placeholder="Total Number" maxlength="10">
																			</div>

																		</td>
																	</tr>
																	
																	<tr>
																		<td class="sr-no">

																			<p></p>

																		</td>
																		<td>
																			<p>Total number of Orthology Tests</p>

																		</td>
																		<td>
																			<div class="input-style-1">
																				<input type="text" name="orthology_test" id="orthology_test"
																					class="form-control" placeholder="Total Number" maxlength="10">
																			</div>

																		</td>
																	</tr>
																	
																	<tr>
																		<td class="sr-no">

																			<p></p>

																		</td>
																		<td>
																			<p>Total number of Investigations done in the last calendar year</p>

																		</td>
																		<td>
																			<div class="input-style-1">
																				<input type="text" name="total_investigation" id="total_investigation"
																					class="form-control" placeholder="Total Number" maxlength="10">
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
															<li><input class="main-btn info-btn btn-hover btnsave" id="clinical_laboratory_save"
																type="button" value="Save"></li>
														</ul>
													</div>
												</div>
											</div>
									</form:form>
								</div>
								<!--Clinical Laboratory End -->
									<!-- Verification of Functionality Start -->
								<div class="tab" id="verification_functionality_btn">
									<button class="tab-toggle">Verification of Functionality</button>
								</div>
								<div class="content">
									<h4 class="heading">Verification of Functionality</h4>
									<form:form name="verification_functionality_form" id="verification_functionality_form" action="" method="post" class=""
										commandName="">
										
											<div class="row">
											
											<input type="hidden" id="hid_verification_functionality" name="hid_verification_functionality" value="0">
											
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
														<div class="table-wrapper table-responsive custom-table">
															<table class="table" id="vefication_table">
																<thead>
																	<tr>
																		<th><h6>Sr No</h6></th>
																		<th><h6>Records/Documents</h6></th>
																		<th><h6>Available/Not available<strong class="mandatory">*</strong></h6></th>
																		<th><h6>Remarks<strong class="mandatory">*</strong></h6></th>
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
																		<td>
																			<div class="select-style-1">
																				<div class="select-position">
																					<select name="name_of_opd" id="name_of_opd"
																						class="form-control">
																						<option value="0" selected="selected">--
																							Select--</option>
																						<option value="1">Available</option>
																						<option value="2">Not Available</option>
			
																					</select>
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="input-style-2">
																				<textarea name="name_of_opd_remark" id="name_of_opd_remark"
																					placeholder="Remarks" maxlength="100"></textarea>
																			</div>

																		</td>
																	</tr>
																	<tr>
																		<td class="sr-no">
																			<p></p>
																		</td>
																		<td>
																			<p>Original OPD case register</p>

																		</td>
																		<td>
																			<div class="select-style-1">
																				<div class="select-position">
																					<select name="opd_case_register" id="opd_case_register"
																						class="form-control">
																						<option value="0" selected="selected">--
																							Select--</option>
																						<option value="1">Available</option>
																						<option value="2">Not Available</option>
			
																					</select>
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="input-style-2">
																				<textarea name="opd_case_register_remark" id="opd_case_register_remark"
																					placeholder="Remarks" maxlength="100"></textarea>
																			</div>

																		</td>
																	</tr>
																	<tr>
																		<td class="sr-no">
																			<p></p>
																		</td>
																		<td>
																			<p>OPD Medicine dispensing register</p>

																		</td>
																		<td>
																			<div class="select-style-1">
																				<div class="select-position">
																					<select name="opd_medicine" id="opd_medicine"
																						class="form-control">
																						<option value="0" selected="selected">--
																							Select--</option>
																						<option value="1">Available</option>
																						<option value="2">Not Available</option>
			
																					</select>
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="input-style-2">
																				<textarea name="opd_medicine_remark" id="opd_medicine_remark"
																					placeholder="Remarks" maxlength="100"></textarea>
																			</div>

																		</td>
																	</tr>
																	
																		<tr>
																		<td class="sr-no">
																			<p></p>
																		</td>
																		<td>
																			<p>Cash receipts for OPD charges/lab charges</p>

																		</td>
																		<td>
																			<div class="select-style-1">
																				<div class="select-position">
																					<select name="case_receipt_opd" id="case_receipt_opd"
																						class="form-control">
																						<option value="0" selected="selected">--
																							Select--</option>
																						<option value="1">Available</option>
																						<option value="2">Not Available</option>
			
																					</select>
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="input-style-2">
																				<textarea name="case_receipt_opd_remark" id="case_receipt_opd_remark"
																					placeholder="Remarks" maxlength="100"></textarea>
																			</div>

																		</td>
																	</tr>
																	
																		<tr>
																		<td class="sr-no">
																			<p></p>
																		</td>
																		<td>
																			<p>Number and Name of the IPD wards along with no. of beds</p>

																		</td>
																		<td>
																			<div class="select-style-1">
																				<div class="select-position">
																					<select name="name_of_ipd" id="name_of_ipd"
																						class="form-control">
																						<option value="0" selected="selected">--
																							Select--</option>
																						<option value="1">Available</option>
																						<option value="2">Not Available</option>
			
																					</select>
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="input-style-2">
																				<textarea name="name_of_ipd_remark" id="name_of_ipd_remark"
																					placeholder="Remarks" maxlength="100"></textarea>
																			</div>
																		</td>
																	</tr>
																	
																		<tr>
																		<td class="sr-no">
																			<p></p>
																		</td>
																		<td>
																			<p>Original IPD case sheets</p>

																		</td>
																		<td>
																			<div class="select-style-1">
																				<div class="select-position">
																					<select name="ipd_case_sheets" id="ipd_case_sheets"
																						class="form-control">
																						<option value="0" selected="selected">--
																							Select--</option>
																						<option value="1">Available</option>
																						<option value="2">Not Available</option>
			
																					</select>
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="input-style-2">
																				<textarea name="ipd_case_sheets_remark" id="ipd_case_sheets_remark"
																					placeholder="Remarks" maxlength="100"></textarea>
																			</div>
																		</td>
																	</tr>
																	
																	<tr>
																		<td class="sr-no">
																			<p></p>
																		</td>
																		<td>
																			<p>Discharge Cards</p>

																		</td>
																		<td>
																			<div class="select-style-1">
																				<div class="select-position">
																					<select name="discharge_cards" id="discharge_cards"
																						class="form-control">
																						<option value="0" selected="selected">--
																							Select--</option>
																						<option value="1">Available</option>
																						<option value="2">Not Available</option>
			
																					</select>
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="input-style-2">
																				<textarea name="discharge_cards_remark" id="discharge_cards_remark"
																					placeholder="Remarks" maxlength="100"></textarea>
																			</div>
																		</td>
																	</tr>
																	
																	<tr>
																		<td class="sr-no">
																			<p></p>
																		</td>
																		<td>
																			<p>IP Medicine indent register</p>

																		</td>
																		<td>
																			<div class="select-style-1">
																				<div class="select-position">
																					<select name="ip_medicine" id="ip_medicine"
																						class="form-control">
																						<option value="0" selected="selected">--
																							Select--</option>
																						<option value="1">Available</option>
																						<option value="2">Not Available</option>
			
																					</select>
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="input-style-2">
																				<textarea name="ip_medicine_remark" id="ip_medicine_remark"
																					placeholder="Remarks" maxlength="100"></textarea>
																			</div>
																		</td>
																	</tr>
																	
																	<tr>
																		<td class="sr-no">
																			<p></p>
																		</td>
																		<td>
																			<p>Nursing staff duty roster</p>

																		</td>
																		<td>
																			<div class="select-style-1">
																				<div class="select-position">
																					<select name="nursing_duty_roster" id="nursing_duty_roster"
																						class="form-control">
																						<option value="0" selected="selected">--
																							Select--</option>
																						<option value="1">Available</option>
																						<option value="2">Not Available</option>
			
																					</select>
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="input-style-2">
																				<textarea name="nursing_duty_roster_remark" id="nursing_duty_roster_remark"
																					placeholder="Remarks" maxlength="100"></textarea>
																			</div>
																		</td>
																	</tr>
																	
																	<tr>
																		<td class="sr-no">
																			<p></p>
																		</td>
																		<td>
																			<p>Doctors Duty Roster</p>

																		</td>
																		<td>
																			<div class="select-style-1">
																				<div class="select-position">
																					<select name="doctor_duty_roaster" id="doctor_duty_roaster"
																						class="form-control">
																						<option value="0" selected="selected">--
																							Select--</option>
																						<option value="1">Available</option>
																						<option value="2">Not Available</option>
			
																					</select>
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="input-style-2">
																				<textarea name="doctor_duty_roaster_remark" id="doctor_duty_roaster_remark"
																					placeholder="Remarks" maxlength="100"></textarea>
																			</div>
																		</td>
																	</tr>
																	
																	<tr>
																		<td class="sr-no">
																			<p></p>
																		</td>
																		<td>
																			<p>IP Diet Register</p>

																		</td>
																		<td>
																			<div class="select-style-1">
																				<div class="select-position">
																					<select name="ip_diet_register" id="ip_diet_register"
																						class="form-control">
																						<option value="0" selected="selected">--
																							Select--</option>
																						<option value="1">Available</option>
																						<option value="2">Not Available</option>
			
																					</select>
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="input-style-2">
																				<textarea name="ip_diet_register_remark" id="ip_diet_register_remark"
																					placeholder="Remarks" maxlength="100"></textarea>
																			</div>
																		</td>
																	</tr>
																	
																	<tr>
																		<td class="sr-no">
																			<p></p>
																		</td>
																		<td>
																			<p>Cash Receipts for IPD charges/ Lab charges</p>

																		</td>
																		<td>
																			<div class="select-style-1">
																				<div class="select-position">
																					<select name="cash_receipt_ipd" id="cash_receipt_ipd"
																						class="form-control">
																						<option value="0" selected="selected">--
																							Select--</option>
																						<option value="1">Available</option>
																						<option value="2">Not Available</option>
			
																					</select>
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="input-style-2">
																				<textarea name="cash_receipt_ipd_remark" id="cash_receipt_ipd_remark"
																					placeholder="Remarks" maxlength="100"></textarea>
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
											<br>
											<h4 class="heading">Details of the Activity in MoU Hospital in the Last Year</h4>
											<!-- <hr> -->
											<div class="row">
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Name of Multispecialty Hospital of Modern Medicine with which the College has MoU.<strong class="mandatory">*</strong>
													</label> <input type="text" id="name_of_multispecialty_hospital" name="name_of_multispecialty_hospital" class="form-control" placeholder="Enter Name" maxlength="100">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Address of Multispecialty Hospital of Modern Medicine with which the College has MoU.<strong class="mandatory">*</strong>
													</label> <input type="text" id="address_of_multispecialty_hospital" name="address_of_multispecialty_hospital" class="form-control" placeholder="Enter Address" maxlength="100">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Upload document for MoU with Multispeciality Hospital<strong class="mandatory">*</strong>
													</label> <input type="file" id="doc_of_multispecialty_hospital" name="doc_of_multispecialty_hospital" class="form-control" accept=".pdf">
													<input type="hidden" id="hid_doc_of_multispecialty_hospital" name="hid_doc_of_multispecialty_hospital" value="">
													
												<div class="note-text">
													<span class="errorClass" id="doc_of_multispecialty_hospital_lbl">${doc_path_doc1_msg}</span>
													<span class='tikClass' id="doc_of_multispecialty_hospital_lbltik"></span>
												</div>
													
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Please Upload IPD Diet Register<strong class="mandatory">*</strong>
													</label> <input type="file" id="ipd_diet_register_doc" name="ipd_diet_register_doc" class="form-control" accept=".pdf">
													<input type="hidden" id="hid_ipd_diet_register_doc" name="hid_ipd_diet_register_doc" value="">
													
												<div class="note-text">
													<span class="errorClass" id="ipd_diet_register_doc_lbl">${doc_path_doc1_msg}</span>
													<span class='tikClass' id="ipd_diet_register_doc_lbltik"></span>
												</div>
													
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-2">
													<label>Date of MoU Sign<strong class="mandatory">* </strong></label> 
													<input type="text" name="date_of_mou_sign" id="date_of_mou_sign" class="form-control-sm form-control effect-9 " 
													aria-required="true" autocomplete="off" placeholder="DD/MM/YYYY">
												</div>
												<input type="hidden" id="yrr" name="yrr" value="">
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Validity of MoU<strong class="mandatory">*</strong>
													</label> <input type="text" id="validity_of_mou" name="validity_of_mou" class="form-control" placeholder="Enter Validity" maxlength="100">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Area in Which MoU Centered<strong class="mandatory">*</strong>
													</label> <input type="text" id="area_of_mou" name="area_of_mou" class="form-control" placeholder="Enter Area" maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Investigation Attend in the MoU Hospital<strong class="mandatory">*</strong>
													</label> <input type="text" id="investigation_of_hospital" name="investigation_of_hospital" class="form-control" placeholder="Enter Attended Investigation" maxlength="100">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Training Provided for MoU Hospital Classes Taken by the Doctors from MoU Hospital for Student of the Homeopathy
													<strong class="mandatory">*</strong>
													</label> <input type="text" id="training_details" name="training_details" class="form-control" placeholder="Enter Training Details" maxlength="100">
												</div>
											</div>
										</div>
											<!-- end row -->
											<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn success-btn btn-hover btn btnsubmit" id="verification_functionality_save"
															type="button" value="Submit"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- Verification of Functionality End -->
								
							</div>
						</div>
					</div>
					<!-- ===========================
							Hospital OPD & IPD Details End
						=========================== -->
				</div>
			</div>
			<!-- end row -->
		</div>
		<!-- end container -->
	</section>
	<!-- regulation components end -->
	
	<!--=========================================== -->
   
   <c:url value="otherhospital_detail_view" var="viewUrl" />
	<form:form action="${viewUrl}" method="post" id="viewForm"
		name="viewForm" modelAttribute="other_hos_dtl_view_id">
		<input type="hidden" name="other_hos_dtl_id" id="other_hos_dtl_view_id" value="0" />
		<input type="hidden" name="statusview" id="statusview" value="0" />
	</form:form> 
   
    <!--===========================================-->
	
	
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
			datepicketDate('date_of_mou_sign');
	}
	
	$("#basic_info_id").val('${basic_info_id}');
	
	//GET MAINTANANCE RECORD DETAILS
	var GetMaintance_Record_Details ='${GetMaintance_Record_Details}';
	if(GetMaintance_Record_Details != "[]"){
		$("#hid_maintenance_record").val('${GetMaintance_Record_Details[0].id}');
		$("#central_registration").val('${GetMaintance_Record_Details[0].central_registration}');
		$("#opd_records").val('${GetMaintance_Record_Details[0].opd_records}');
		$("#ipd_records").val('${GetMaintance_Record_Details[0].ipd_records}');
		$("#medical_record_room").val('${GetMaintance_Record_Details[0].medical_record_room}');
		$("#account_section").val('${GetMaintance_Record_Details[0].account_section}');
	}
	
	//GET LABOUR ROOM DETAILS
	var getLabour_Room_Details ='${getLabour_Room_Details}';
	if(getLabour_Room_Details != "[]"){
		$("#hid_labour_room").val('${getLabour_Room_Details[0].id}');
		
		var labour_room = '${getLabour_Room_Details[0].labour_room}';
		$("input[name='labour_room'][value='"+labour_room+"']").prop('checked', true);
		if(labour_room == "YES"){
			$("div#hid_antenatal_room").show();
	        $("div#hid_neonatal_care").show();
	        $("div#hid_other_facilities").show();
	        $("div#hid_total_deliveries").show();
	        $("div#hid_total_procedures").show();
		}else{
			$("div#hid_antenatal_room").hide();
	        $("div#hid_neonatal_care").hide();
	        $("div#hid_other_facilities").hide();
	        $("div#hid_total_deliveries").hide();
	        $("div#hid_total_procedures").hide();
		}
		
		var antenatal_room = '${getLabour_Room_Details[0].antenatal_room}';
		$("input[name='antenatal_room'][value='"+antenatal_room+"']").prop('checked', true);
		
		var neonatal_care = '${getLabour_Room_Details[0].neonatal_care}';
		$("input[name='neonatal_care'][value='"+neonatal_care+"']").prop('checked', true);
		
		var other_facilities = '${getLabour_Room_Details[0].other_facilities}';
		$("input[name='other_facilities'][value='"+other_facilities+"']").prop('checked', true);
		
		$("#total_deliveries").val('${getLabour_Room_Details[0].total_deliveries}');
		$("#total_procedures").val('${getLabour_Room_Details[0].total_procedures}');
		
	}
	
	//GET OPERATION THEATRE DETAILS
	var getOperation_Theatre_Details ='${getOperation_Theatre_Details}';
	if(getOperation_Theatre_Details != "[]"){
		$("#hid_operation_theatre").val('${getOperation_Theatre_Details[0].id}');
		
		var operation_theatre = '${getOperation_Theatre_Details[0].operation_theatre}';
		$("input[name='operation_theatre'][value='"+operation_theatre+"']").prop('checked', true);
		if(operation_theatre == "YES"){
			$("div#hid_air_condition").show();
	        $("div#hid_pre_operative_room").show();
	        $("div#hid_sterilization_room").show();
	        $("div#hid_wash_room").show();
	        $("div#hid_other_facility").show();
	        $("div#hid_fumigation_facility").show();
	        $("div#hid_total_operations").show();
	        $("div#hid_total_minor_procedures").show();
		}else{
			$("div#hid_air_condition").hide();
	        $("div#hid_pre_operative_room").hide();
	        $("div#hid_sterilization_room").hide();
	        $("div#hid_wash_room").hide();
	        $("div#hid_other_facility").hide();
	        $("div#hid_fumigation_facility").hide();
	        $("div#hid_total_operations").hide();
	        $("div#hid_total_minor_procedures").hide();
		}
		
		var air_condition = '${getOperation_Theatre_Details[0].air_condition}';
		$("input[name='air_condition'][value='"+air_condition+"']").prop('checked', true);
		
		var pre_operative_room = '${getOperation_Theatre_Details[0].pre_operative_room}';
		$("input[name='pre_operative_room'][value='"+pre_operative_room+"']").prop('checked', true);
		
		var sterilization_room = '${getOperation_Theatre_Details[0].sterilization_room}';
		$("input[name='sterilization_room'][value='"+sterilization_room+"']").prop('checked', true);
		
		var wash_room = '${getOperation_Theatre_Details[0].wash_room}';
		$("input[name='wash_room'][value='"+wash_room+"']").prop('checked', true);
		
		var other_facility = '${getOperation_Theatre_Details[0].other_facility}';
		$("input[name='other_facility'][value='"+other_facility+"']").prop('checked', true);
		
		var fumigation_facility = '${getOperation_Theatre_Details[0].fumigation_facility}';
		$("input[name='fumigation_facility'][value='"+fumigation_facility+"']").prop('checked', true);
		
		$("#total_operations").val('${getOperation_Theatre_Details[0].total_operations}');
		$("#total_minor_procedures").val('${getOperation_Theatre_Details[0].total_minor_procedures}');
	}
	
	
	//GET INVESTING DETAILS
	var getInvestigation_Details ='${getInvestigation_Details}';
	if(getInvestigation_Details != "[]"){
		$("#hid_investigation").val('${getInvestigation_Details[0].id}');
		$("#total_xray").val('${getInvestigation_Details[0].total_xray}');
		$("#total_ecg").val('${getInvestigation_Details[0].total_ecg}');
		$("#total_usg").val('${getInvestigation_Details[0].total_usg}');
	}
	
	//GET CLINICAL LABORATORY DETAILS
	var getClinical_Laboratory_Details ='${getClinical_Laboratory_Details}';
	if(getClinical_Laboratory_Details != "[]"){
		$("#hid_clinical_laboratory").val('${getClinical_Laboratory_Details[0].id}');
		$("#hematological_test").val('${getClinical_Laboratory_Details[0].hematological_test}');
		$("#bio_chemical_test").val('${getClinical_Laboratory_Details[0].bio_chemical_test}');
		$("#serological_test").val('${getClinical_Laboratory_Details[0].serological_test}');
		$("#microbiological_test").val('${getClinical_Laboratory_Details[0].microbiological_test}');
		$("#orthology_test").val('${getClinical_Laboratory_Details[0].orthology_test}');
		$("#total_investigation").val('${getClinical_Laboratory_Details[0].total_investigation}');
	}
	
	//GET VERIFICATION FUNCTIONALITY DETAILS
	var GetFunctionality_Details ='${GetFunctionality_Details}';
	if(GetFunctionality_Details != "[]"){
		$("#hid_verification_functionality").val('${GetFunctionality_Details[0].id}');
		$("#name_of_opd").val('${GetFunctionality_Details[0].name_of_opd}');
		$("#name_of_opd_remark").val('${GetFunctionality_Details[0].name_of_opd_remark}');
		$("#opd_case_register").val('${GetFunctionality_Details[0].opd_case_register}');
		$("#opd_case_register_remark").val('${GetFunctionality_Details[0].opd_case_register_remark}');
		$("#opd_medicine").val('${GetFunctionality_Details[0].opd_medicine}');
		$("#opd_medicine_remark").val('${GetFunctionality_Details[0].opd_medicine_remark}');
		$("#case_receipt_opd").val('${GetFunctionality_Details[0].case_receipt_opd}');
		$("#case_receipt_opd_remark").val('${GetFunctionality_Details[0].case_receipt_opd_remark}');
		$("#name_of_ipd").val('${GetFunctionality_Details[0].name_of_ipd}');
		$("#name_of_ipd_remark").val('${GetFunctionality_Details[0].name_of_ipd_remark}');
		$("#ipd_case_sheets").val('${GetFunctionality_Details[0].ipd_case_sheets}');
		$("#ipd_case_sheets_remark").val('${GetFunctionality_Details[0].ipd_case_sheets_remark}');
		$("#discharge_cards").val('${GetFunctionality_Details[0].discharge_cards}');
		$("#discharge_cards_remark").val('${GetFunctionality_Details[0].discharge_cards_remark}');
		$("#ip_medicine").val('${GetFunctionality_Details[0].ip_medicine}');
		$("#ip_medicine_remark").val('${GetFunctionality_Details[0].ip_medicine_remark}');
		$("#nursing_duty_roster").val('${GetFunctionality_Details[0].nursing_duty_roster}');
		$("#nursing_duty_roster_remark").val('${GetFunctionality_Details[0].nursing_duty_roster_remark}');
		$("#doctor_duty_roaster").val('${GetFunctionality_Details[0].doctor_duty_roaster}');
		$("#doctor_duty_roaster_remark").val('${GetFunctionality_Details[0].doctor_duty_roaster_remark}');
		$("#ip_diet_register").val('${GetFunctionality_Details[0].ip_diet_register}');
		$("#ip_diet_register_remark").val('${GetFunctionality_Details[0].ip_diet_register_remark}');
		$("#cash_receipt_ipd").val('${GetFunctionality_Details[0].cash_receipt_ipd}');
		$("#cash_receipt_ipd_remark").val('${GetFunctionality_Details[0].cash_receipt_ipd_remark}');
		$("#name_of_multispecialty_hospital").val('${GetFunctionality_Details[0].name_of_multispecialty_hospital}');
		$("#address_of_multispecialty_hospital").val('${GetFunctionality_Details[0].address_of_multispecialty_hospital}');
		$("#hid_doc_of_multispecialty_hospital").val('${GetFunctionality_Details[0].doc_of_multispecialty_hospital}');
		$("#hid_ipd_diet_register_doc").val('${GetFunctionality_Details[0].ipd_diet_register_doc}');
		$("#date_of_mou_sign").val('${GetFunctionality_Details[0].date_of_mou_sign}');
		$("#validity_of_mou").val('${GetFunctionality_Details[0].validity_of_mou}');
		$("#area_of_mou").val('${GetFunctionality_Details[0].area_of_mou}');
		$("#investigation_of_hospital").val('${GetFunctionality_Details[0].investigation_of_hospital}');
		$("#training_details").val('${GetFunctionality_Details[0].training_details}');
	}
	
//  	var hid_maintenance_record = $("#hid_maintenance_record").val();
//  	if(hid_maintenance_record == 0){
//  		$("#labour_room_btn").hide();
//  		$("#operation_theatre_btn").hide();
//  		$("#investigation_btn").hide();
//  		$("#clinical_laboratory_btn").hide();
//  		$("#verification_functionality_btn").hide();
//  	}else{
//  	 	$("#labour_room_btn").show();
//  	 	$("#operation_theatre_btn").show();
//  	 	$("#investigation_btn").show();
//  	 	$("#clinical_laboratory_btn").show();
//  	 	$("#verification_functionality_btn").show();	
//  	}
	
});


//SAVE CONSTRUCTED AREA DETAILS
function Save_As_Draft_Maintenance_Records_Details(sd) {
	
	$.ajaxSetup({
	    async: false
	});	
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		if(sd != -1){
		alert("Please Save Basic details First");
		}
		return false;
	}
	var hid_maintenance_record = $("#hid_maintenance_record").val();
	if(hid_maintenance_record == "0"){
		if(sd != -1){
		alert("Please Save Maintenance of Reocrds First");
		}
		return false;
	}
	var res = CheckNullorBlank('central_registration');
	if (res !== "true") {
		alert(res +"Central Registration");
		$('#central_registration').focus();
		return false;
	}
	var res = CheckNullorBlank('opd_records');
	if (res !== "true") {
		alert(res +"OPD Records");
		$('#opd_records').focus();
		return false;
	}
	var res = CheckNullorBlank('ipd_records');
	if (res !== "true") {
		alert(res +"IPD Records");
		$('#ipd_records').focus();
		return false;
	}
	var res = CheckNullorBlank('medical_record_room');
	if (res !== "true") {
		alert(res +"Medical Record Room");
		$('#medical_record_room').focus();
		return false;
	}
	var res = CheckNullorBlank('account_section');
	if (res !== "true") {
		alert(res +"Account Section");
		$('#account_section').focus();
		return false;
	}
	
	var form = $("#maintenance_record").serialize();
	$.post(
			'Maintenance_Records_Details_Save_Draft_Action?' + key + "="
					+ value, form, function(j) {
				
				if(j>0){
	        		$("#hid_maintenance_record").val(j);
	        			if(sd != -1){
	        	  			alert("Data Save Sucessfully");
	        			}
	        	  	location.reload();
	         	 }
	         	 else{
	        	  	if(sd != -1){
	        	  		alert(j);
	        	  	}
	          	}
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
		
 }
 
 
//SAVE LABOUR ROOM DETAILS
function Save_As_Draft_Labour_Room_Details(sd) {
	
	$.ajaxSetup({
	    async: false
	});	
	var hid_maintenance_record = $("#hid_maintenance_record").val();
	if(hid_maintenance_record == "0"){
		if(sd != -1){
		alert("Please Save Maintenance of Reocrds First");
		}
		return false;
	}
	var labour_room = $("input[name='labour_room']:checked").val();
	if( labour_room == null ){
		alert("Please Select Labour Room");
		return false;
   	}
	if(labour_room == "YES"){
		var antenatal_room = $("input[name='antenatal_room']:checked").val();
		if( antenatal_room == null ){
			alert("Please Select Antenatal Room");
			return false;
	   	}
		var neonatal_care = $("input[name='neonatal_care']:checked").val();
		if( neonatal_care == null ){
			alert("Please Select Neonatal Care");
			return false;
	   	}
		var other_facilities = $("input[name='other_facilities']:checked").val();
		if( other_facilities == null ){
			alert("Please Select Other Facility");
			return false;
	   	}
		var res = CheckNullorBlankZero('total_deliveries');
		if (res !== "true") {
			alert(res +"Total Number of Deliveries");
			$('#total_deliveries').focus();
			return false;
		}
		var res = CheckNullorBlankZero('total_procedures');
		if (res !== "true") {
			alert(res +"Total Number of Other Procedures");
			$('#total_procedures').focus();
			return false;
		}
	}
	
	var form = $("#labour_room_form").serialize();
	$.post(
			'Labour_Room_Details_Save_Draft_Action?' + key + "="
					+ value, form, function(j) {
				
				if(j>0){
	        		$("#hid_labour_room").val(j);
	        			if(sd != -1){
	        	  			alert("Data Save Sucessfully");
	        			}
	         	 }
	         	 else{
	        	  	if(sd != -1){
	        	  		alert(j);
	        	  	}
	          	}
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
		
 }
 
//SAVE OPERATION THEATRE DETAILS
function Save_As_Draft_Operation_Theatre_Details(sd) {
	
	$.ajaxSetup({
	    async: false
	});	
	var hid_maintenance_record = $("#hid_maintenance_record").val();
	if(hid_maintenance_record == "0"){
		if(sd != -1){
		alert("Please Save Maintenance of Reocrds First");
		}
		return false;
	}
	var operation_theatre = $("input[name='operation_theatre']:checked").val();
	if( operation_theatre == null ){
		alert("Please Select Operation Theatre");
		return false;
   	}
	if(operation_theatre == "YES"){
		var air_condition = $("input[name='air_condition']:checked").val();
		if( air_condition == null ){
			alert("Please Select Air Conditioning");
			return false;
	   	}
		var pre_operative_room = $("input[name='pre_operative_room']:checked").val();
		if( pre_operative_room == null ){
			alert("Please Select Pre Operative Room");
			return false;
	   	}
		var sterilization_room = $("input[name='sterilization_room']:checked").val();
		if( sterilization_room == null ){
			alert("Please Select Sterilization Room");
			return false;
	   	}
		var wash_room = $("input[name='wash_room']:checked").val();
		if( wash_room == null ){
			alert("Please Select Wash Room");
			return false;
	   	}
		var other_facility = $("input[name='other_facility']:checked").val();
		if( other_facility == null ){
			alert("Please Select Other Facility");
			return false;
	   	}
		var fumigation_facility = $("input[name='fumigation_facility']:checked").val();
		if( fumigation_facility == null ){
			alert("Please Select Fumigation Facility");
			return false;
	   	}
		var res = CheckNullorBlankZero('total_operations');
		if (res !== "true") {
			alert(res +"Total Number of Operations");
			$('#total_operations').focus();
			return false;
		}
		var res = CheckNullorBlankZero('total_minor_procedures');
		if (res !== "true") {
			alert(res +"Total Number of Minor Procedures");
			$('#total_minor_procedures').focus();
			return false;
		}
		
	}
	
	var form = $("#operation_theatre_form").serialize();
	$.post(
			'Operation_Theatre_Details_Save_Draft_Action?' + key + "="
					+ value, form, function(j) {
				
				if(j>0){
	        		$("#hid_operation_theatre").val(j);
	        			if(sd != -1){
	        	  			alert("Data Save Sucessfully");
	        			}
	         	 }
	         	 else{
	        	  	if(sd != -1){
	        	  		alert(j);
	        	  	}
	          	}
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
		
 }
 

//SAVE INVESTIGATION DETAILS
function Save_As_Draft_Investigation_Details(sd) {
	
	$.ajaxSetup({
	    async: false
	});	
	var hid_maintenance_record = $("#hid_maintenance_record").val();
	if(hid_maintenance_record == "0"){
		if(sd != -1){
		alert("Please Save Maintenance of Reocrds First");
		}
		return false;
	}
	var res = CheckNullorBlankZero('total_xray');
	if (res !== "true") {
		alert(res +"Total Number of X-rays");
		$('#total_xray').focus();
		return false;
	}
	var res = CheckNullorBlankZero('total_ecg');
	if (res !== "true") {
		alert(res +"Total Number of ECG");
		$('#total_ecg').focus();
		return false;
	}
	var res = CheckNullorBlankZero('total_usg');
	if (res !== "true") {
		alert(res +"Total Number of USG");
		$('#total_usg').focus();
		return false;
	}
	
	var form = $("#investigation_form").serialize();
	$.post(
			'Investigation_Details_Save_Draft_Action?' + key + "="
					+ value, form, function(j) {
				
				if(j>0){
	        		$("#hid_investigation").val(j);
	        			if(sd != -1){
	        	  			alert("Data Save Sucessfully");
	        			}
	         	 }
	         	 else{
	        	  	if(sd != -1){
	        	  		alert(j);
	        	  	}
	          	}
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
		
 }
 

//SAVE CLINICAL_LABORATORY DETAILS
function Save_As_Draft_Clinical_Laboratory_Details(sd) {
	
	$.ajaxSetup({
	    async: false
	});	
	var hid_maintenance_record = $("#hid_maintenance_record").val();
	if(hid_maintenance_record == "0"){
		if(sd != -1){
		alert("Please Save Maintenance of Reocrds First");
		}
		return false;
	}
	var res = CheckNullorBlankZero('hematological_test');
	if (res !== "true") {
		alert(res +"Total Number of Hematological Tests");
		$('#hematological_test').focus();
		return false;
	}
	var res = CheckNullorBlankZero('bio_chemical_test');
	if (res !== "true") {
		alert(res +"Total Number of Bio-Chemical Tests");
		$('#bio_chemical_test').focus();
		return false;
	}
	var res = CheckNullorBlankZero('serological_test');
	if (res !== "true") {
		alert(res +"Total Number of Serological Test");
		$('#serological_test').focus();
		return false;
	}
	var res = CheckNullorBlankZero('microbiological_test');
	if (res !== "true") {
		alert(res +"Total Number of Microbiological Tests");
		$('#microbiological_test').focus();
		return false;
	}
	var res = CheckNullorBlankZero('orthology_test');
	if (res !== "true") {
		alert(res +"Total Number of Orthology Tests");
		$('#orthology_test').focus();
		return false;
	}
	var res = CheckNullorBlankZero('total_investigation');
	if (res !== "true") {
		alert(res +"Total Number of Investigations");
		$('#total_investigation').focus();
		return false;
	}
	
	var form = $("#clinical_laboratory_form").serialize();
	$.post(
			'Clinical_Laboratory_Details_Save_Draft_Action?' + key + "="
					+ value, form, function(j) {
				
				if(j>0){
	        		$("#hid_clinical_laboratory").val(j);
	        			if(sd != -1){
	        	  			alert("Data Save Sucessfully");
	        			}
	         	 }
	         	 else{
	        	  	if(sd != -1){
	        	  		alert(j);
	        	  	}
	          	}
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
		
 }
 

//SAVE VERIFICATION FUNCTIONALITY DETAILS
function Save_As_Draft_Functionality_Details(sd) {
	
	$.ajaxSetup({
	    async: false
	});	
 	
	var form_data = new FormData(document.getElementById("verification_functionality_form"));
	$.ajax({
		url : 'Functionality_Details_Save_Draft_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j){
      if(j>0){
    		$("#hid_verification_functionality").val(j);
    	 	 alert("Final Submit Sucessfully");
      }
      else{
    	  alert(j);
      }
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
		
 }
 
 function Validation() {
	
    var hid_maintenance_record = $("#hid_maintenance_record").val();
		if(hid_maintenance_record == "0"){
		alert("Please Save Maintenance of Reocrds First");
		return false;
	}
	var res = CheckNullorBlank('central_registration');
	if (res !== "true") {
		alert(res +"Central Registration in Maintenance of Records");
		$('#central_registration').focus();
		return false;
	}
	var res = CheckNullorBlank('opd_records');
	if (res !== "true") {
		alert(res +"OPD Records in Maintenance of Records");
		$('#opd_records').focus();
		return false;
	}
	var res = CheckNullorBlank('ipd_records');
	if (res !== "true") {
		alert(res +"IPD Records in Maintenance of Records");
		$('#ipd_records').focus();
		return false;
	}
	var res = CheckNullorBlank('medical_record_room');
	if (res !== "true") {
		alert(res +"Medical Record Room in Maintenance of Records");
		$('#medical_record_room').focus();
		return false;
	}
	var res = CheckNullorBlank('account_section');
	if (res !== "true") {
		alert(res +"Account Section in Maintenance of Records");
		$('#account_section').focus();
		return false;
	}
	var labour_room = $("input[name='labour_room']:checked").val();
	if( labour_room == null ){
		alert("Please Select Labour Room in Labour Room");
		return false;
   	}
	if(labour_room == "YES"){
		var antenatal_room = $("input[name='antenatal_room']:checked").val();
		if( antenatal_room == null ){
			alert("Please Select Antenatal Room in Labour Room");
			return false;
	   	}
		var neonatal_care = $("input[name='neonatal_care']:checked").val();
		if( neonatal_care == null ){
			alert("Please Select Neonatal Care in Labour Room");
			return false;
	   	}
		var other_facilities = $("input[name='other_facilities']:checked").val();
		if( other_facilities == null ){
			alert("Please Select Other Facility in Labour Room");
			return false;
	   	}
		var res = CheckNullorBlankZero('total_deliveries');
		if (res !== "true") {
			alert(res +"Total Number of Deliveries in Labour Room");
			$('#total_deliveries').focus();
			return false;
		}
		var res = CheckNullorBlankZero('total_procedures');
		if (res !== "true") {
			alert(res +"Total Number of Other Procedures in Labour Room");
			$('#total_procedures').focus();
			return false;
		}
	}
	var operation_theatre = $("input[name='operation_theatre']:checked").val();
	if( operation_theatre == null ){
		alert("Please Select Operation Theatre in Operation Theatre");
		return false;
   	}
	if(operation_theatre == "YES"){
		var air_condition = $("input[name='air_condition']:checked").val();
		if( air_condition == null ){
			alert("Please Select Air Conditioning in Operation Theatre");
			return false;
	   	}
		var pre_operative_room = $("input[name='pre_operative_room']:checked").val();
		if( pre_operative_room == null ){
			alert("Please Select Pre Operative Room in Operation Theatre");
			return false;
	   	}
		var sterilization_room = $("input[name='sterilization_room']:checked").val();
		if( sterilization_room == null ){
			alert("Please Select Sterilization Room in Operation Theatre");
			return false;
	   	}
		var wash_room = $("input[name='wash_room']:checked").val();
		if( wash_room == null ){
			alert("Please Select Wash Room in Operation Theatre");
			return false;
	   	}
		var other_facility = $("input[name='other_facility']:checked").val();
		if( other_facility == null ){
			alert("Please Select Other Facility in Operation Theatre");
			return false;
	   	}
		var fumigation_facility = $("input[name='fumigation_facility']:checked").val();
		if( fumigation_facility == null ){
			alert("Please Select Fumigation Facility in Operation Theatre");
			return false;
	   	}
		var res = CheckNullorBlankZero('total_operations');
		if (res !== "true") {
			alert(res +"Total Number of Operations in Operation Theatre");
			$('#total_operations').focus();
			return false;
		}
		var res = CheckNullorBlankZero('total_minor_procedures');
		if (res !== "true") {
			alert(res +"Total Number of Minor Procedures in Operation Theatre");
			$('#total_minor_procedures').focus();
			return false;
		}
		
	}
	var res = CheckNullorBlankZero('total_xray');
	if (res !== "true") {
		alert(res +"Total Number of X-rays in Investigations");
		$('#total_xray').focus();
		return false;
	}
	var res = CheckNullorBlankZero('total_ecg');
	if (res !== "true") {
		alert(res +"Total Number of ECG in Investigations");
		$('#total_ecg').focus();
		return false;
	}
	var res = CheckNullorBlankZero('total_usg');
	if (res !== "true") {
		alert(res +"Total Number of USG in Investigations");
		$('#total_usg').focus();
		return false;
	}
	var res = CheckNullorBlankZero('hematological_test');
	if (res !== "true") {
		alert(res +"Total Number of Hematological Tests in Clinical Laboratory");
		$('#hematological_test').focus();
		return false;
	}
	var res = CheckNullorBlankZero('bio_chemical_test');
	if (res !== "true") {
		alert(res +"Total Number of Bio-Chemical Tests in Clinical Laboratory");
		$('#bio_chemical_test').focus();
		return false;
	}
	var res = CheckNullorBlankZero('serological_test');
	if (res !== "true") {
		alert(res +"Total Number of Serological Test in Clinical Laboratory");
		$('#serological_test').focus();
		return false;
	}
	var res = CheckNullorBlankZero('microbiological_test');
	if (res !== "true") {
		alert(res +"Total Number of Microbiological Tests in Clinical Laboratory");
		$('#microbiological_test').focus();
		return false;
	}
	var res = CheckNullorBlankZero('orthology_test');
	if (res !== "true") {
		alert(res +"Total Number of Orthology Tests in Clinical Laboratory");
		$('#orthology_test').focus();
		return false;
	}
	var res = CheckNullorBlankZero('total_investigation');
	if (res !== "true") {
		alert(res +"Total Number of Investigations in Clinical Laboratory");
		$('#total_investigation').focus();
		return false;
	}
	var res = CheckNullorBlank('name_of_opd');
	if (res !== "true") {
		alert(res +"Availability of Name of OPD Department");
		$('#name_of_opd').focus();
		return false;
	}
	var res = CheckNullorBlank('name_of_opd_remark');
	if (res !== "true") {
		alert(res +"Remarks of OPD Department");
		$('#name_of_opd_remark').focus();
		return false;
	}
	var res = CheckNullorBlank('opd_case_register');
	if (res !== "true") {
		alert(res +"Availability of Original OPD Case Register");
		$('#opd_case_register').focus();
		return false;
	}
	var res = CheckNullorBlank('opd_case_register_remark');
	if (res !== "true") {
		alert(res +"Remarks of Original OPD case register");
		$('#opd_case_register_remark').focus();
		return false;
	}
	var res = CheckNullorBlank('opd_medicine');
	if (res !== "true") {
		alert(res +"Availability of OPD Medicine");
		$('#opd_medicine').focus();
		return false;
	}
	var res = CheckNullorBlank('opd_medicine_remark');
	if (res !== "true") {
		alert(res +"Remarks of OPD Medicine");
		$('#opd_medicine_remark').focus();
		return false;
	}
	var res = CheckNullorBlank('case_receipt_opd');
	if (res !== "true") {
		alert(res +"Availability of Cash receipts for OPD");
		$('#case_receipt_opd').focus();
		return false;
	}
	var res = CheckNullorBlank('case_receipt_opd_remark');
	if (res !== "true") {
		alert(res +"Remarks of Cash receipts for OPD");
		$('#case_receipt_opd_remark').focus();
		return false;
	}
	var res = CheckNullorBlank('name_of_ipd');
	if (res !== "true") {
		alert(res +"Availability of Name of IPD");
		$('#name_of_ipd').focus();
		return false;
	}
	var res = CheckNullorBlank('name_of_ipd_remark');
	if (res !== "true") {
		alert(res +"Remarks of Name of IPD");
		$('#name_of_ipd_remark').focus();
		return false;
	}
	var res = CheckNullorBlank('ipd_case_sheets');
	if (res !== "true") {
		alert(res +"Availability of IPD Case sheets");
		$('#ipd_case_sheets').focus();
		return false;
	}
	var res = CheckNullorBlank('ipd_case_sheets_remark');
	if (res !== "true") {
		alert(res +"Remarks of IPD Case sheets");
		$('#ipd_case_sheets_remark').focus();
		return false;
	}
	var res = CheckNullorBlank('discharge_cards');
	if (res !== "true") {
		alert(res +"Availability of Discharge Cards");
		$('#discharge_cards').focus();
		return false;
	}
	var res = CheckNullorBlank('discharge_cards_remark');
	if (res !== "true") {
		alert(res +"Remarks of Discharge Cards");
		$('#discharge_cards_remark').focus();
		return false;
	}
	var res = CheckNullorBlank('ip_medicine');
	if (res !== "true") {
		alert(res +"Availability of IP Medicine");
		$('#ip_medicine').focus();
		return false;
	}
	var res = CheckNullorBlank('ip_medicine_remark');
	if (res !== "true") {
		alert(res +"Remarks of IP Medicine");
		$('#ip_medicine_remark').focus();
		return false;
	}
	var res = CheckNullorBlank('nursing_duty_roster');
	if (res !== "true") {
		alert(res +"Availability of Nursing Staff Duty Roster");
		$('#nursing_duty_roster').focus();
		return false;
	}
	var res = CheckNullorBlank('nursing_duty_roster_remark');
	if (res !== "true") {
		alert(res +"Remarks of Nursing Staff Duty Roster");
		$('#nursing_duty_roster_remark').focus();
		return false;
	}
	var res = CheckNullorBlank('doctor_duty_roaster');
	if (res !== "true") {
		alert(res +"Availability of Doctor Duty Roster");
		$('#doctor_duty_roaster').focus();
		return false;
	}
	var res = CheckNullorBlank('doctor_duty_roaster_remark');
	if (res !== "true") {
		alert(res +"Remarks of Doctor Duty Roster");
		$('#doctor_duty_roaster_remark').focus();
		return false;
	}
	var res = CheckNullorBlank('ip_diet_register');
	if (res !== "true") {
		alert(res +"Availability of IP Diet Register");
		$('#ip_diet_register').focus();
		return false;
	}
	var res = CheckNullorBlank('ip_diet_register_remark');
	if (res !== "true") {
		alert(res +"Remarks of IP Diet Register");
		$('#ip_diet_register_remark').focus();
		return false;
	}
	var res = CheckNullorBlank('cash_receipt_ipd');
	if (res !== "true") {
		alert(res +"Availability of Cash Receipt for IPD");
		$('#cash_receipt_ipd').focus();
		return false;
	}
	var res = CheckNullorBlank('cash_receipt_ipd_remark');
	if (res !== "true") {
		alert(res +"Remarks of Cash Receipt for IPD");
		$('#cash_receipt_ipd_remark').focus();
		return false;
	}
	
	var res = CheckNullorBlank('name_of_multispecialty_hospital');
	if (res !== "true") {
		alert(res +"Name of Multispecialty Hospital");
		$('#name_of_multispecialty_hospital').focus();
		return false;
	}
	var res = CheckNullorBlank('address_of_multispecialty_hospital');
	if (res !== "true") {
		alert(res +"Address of Multispecialty Hospital");
		$('#address_of_multispecialty_hospital').focus();
		return false;
	}
	var hid_doc_of_multispecialty_hospital = CheckNullorBlank('hid_doc_of_multispecialty_hospital');
	if(hid_doc_of_multispecialty_hospital !== "true"){
		var res = CheckNullorBlank('doc_of_multispecialty_hospital');
		if (res !== "true") {
			alert(res +"Document of Multispeciality Hospital");
			$('#doc_of_multispecialty_hospital').focus();
			return false;
		}
	}
	var hid_ipd_diet_register_doc = CheckNullorBlank('hid_ipd_diet_register_doc');
	if(hid_ipd_diet_register_doc !== "true"){
		var res = CheckNullorBlank('ipd_diet_register_doc');
		if (res !== "true") {
			alert(res +"Document of IPD Diet Register");
			$('#ipd_diet_register_doc').focus();
			return false;
		}
	}
	var res = CheckNullorBlank('date_of_mou_sign');
	if (res !== "true") {
		alert(res +"Date of MoU Sign");
		$('#date_of_mou_sign').focus();
		return false;
	}
	var res = CheckNullorBlank('validity_of_mou');
	if (res !== "true") {
		alert(res +"Validity of MoU");
		$('#validity_of_mou').focus();
		return false;
	}
	var res = CheckNullorBlank('area_of_mou');
	if (res !== "true") {
		alert(res +"Area in Which MoU Centered");
		$('#area_of_mou').focus();
		return false;
	}
	var res = CheckNullorBlank('investigation_of_hospital');
	if (res !== "true") {
		alert(res +"Investigation Attend in the MoU Hospital");
		$('#investigation_of_hospital').focus();
		return false;
	}
	var res = CheckNullorBlank('training_details');
	if (res !== "true") {
		alert(res +"Training Provided for MoU Hospital Classes");
		$('#training_details').focus();
		return false;
	}
	
	Save_As_Draft_Maintenance_Records_Details(-1);
	Save_As_Draft_Labour_Room_Details(-1);
	Save_As_Draft_Operation_Theatre_Details(-1);
	Save_As_Draft_Investigation_Details(-1);
	Save_As_Draft_Clinical_Laboratory_Details(-1);
	Save_As_Draft_Functionality_Details();
	
}
 

function Labour_Room_Yes(){
		
	var labour_room = $('input:radio[name=labour_room]:checked').val();
	if(labour_room == "YES"){
        $("div#hid_antenatal_room").show();
        $("div#hid_neonatal_care").show();
        $("div#hid_other_facilities").show();
        $("div#hid_total_deliveries").show();
        $("div#hid_total_procedures").show();
	}
	else if(labour_room == "NO"){
        $("div#hid_antenatal_room").hide();
        $("div#hid_neonatal_care").hide();
        $("div#hid_other_facilities").hide();
        $("div#hid_total_deliveries").hide();
        $("div#hid_total_procedures").hide();
	}
}

function Operation_Theatre_Yes(){
	
	var operation_theatre = $('input:radio[name=operation_theatre]:checked').val();
	if(operation_theatre == "YES"){
		$("div#hid_air_condition").show();
        $("div#hid_pre_operative_room").show();
        $("div#hid_sterilization_room").show();
        $("div#hid_wash_room").show();
        $("div#hid_other_facility").show();
        $("div#hid_fumigation_facility").show();
        $("div#hid_total_operations").show();
        $("div#hid_total_minor_procedures").show();
	}
	else if(operation_theatre == "NO"){
		$("div#hid_air_condition").hide();
        $("div#hid_pre_operative_room").hide();
        $("div#hid_sterilization_room").hide();
        $("div#hid_wash_room").hide();
        $("div#hid_other_facility").hide();
        $("div#hid_fumigation_facility").hide();
        $("div#hid_total_operations").hide();
        $("div#hid_total_minor_procedures").hide();
	}
}
	
	
// ================================CSP START=================================
	
	document.addEventListener('DOMContentLoaded', function() {
		
		//MAINTANANCE RECORD
		document.getElementById('maintenance_record_save').onclick = function() {
			return Save_As_Draft_Maintenance_Records_Details();	
		};
		
		//LABOUR ROOM START
		document.getElementById('labour_room_save').onclick = function() {
			return Save_As_Draft_Labour_Room_Details();	
		};
		document.getElementById('total_deliveries').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('total_procedures').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('labour_room_yes').onclick = function() {
			Labour_Room_Yes();
		};
		document.getElementById('labour_room_no').onclick = function() {
			Labour_Room_Yes();
		};
		
		//OPERATION THEATRE START
		document.getElementById('operation_theatre_save').onclick = function() {
			return Save_As_Draft_Operation_Theatre_Details();	
		};
		document.getElementById('total_operations').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('total_minor_procedures').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('operation_theatre_yes').onclick = function() {
			Operation_Theatre_Yes();
		};
		document.getElementById('operation_theatre_no').onclick = function() {
			Operation_Theatre_Yes();
		};
		
		
		//INVESTIGATION START
		document.getElementById('investigation_save').onclick = function() {
			return Save_As_Draft_Investigation_Details();	
		};
		document.getElementById('total_xray').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('total_ecg').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('total_usg').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		
		
		//CLINICAL_LABORATORY START
		document.getElementById('clinical_laboratory_save').onclick = function() {
			return Save_As_Draft_Clinical_Laboratory_Details();	
		};
		document.getElementById('hematological_test').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('bio_chemical_test').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('serological_test').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('microbiological_test').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('orthology_test').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('total_investigation').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		
		
		//CLINICAL_LABORATORY START
		document.getElementById('verification_functionality_save').onclick = function() {
			return Validation();	
		};
		document.getElementById('name_of_opd_remark').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('opd_case_register_remark').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('opd_medicine_remark').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('case_receipt_opd_remark').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('name_of_ipd_remark').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('ipd_case_sheets_remark').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('discharge_cards_remark').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('ip_medicine_remark').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('nursing_duty_roster_remark').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('doctor_duty_roaster_remark').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('ip_diet_register_remark').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('cash_receipt_ipd_remark').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('name_of_multispecialty_hospital').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('address_of_multispecialty_hospital').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('validity_of_mou').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('area_of_mou').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('investigation_of_hospital').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('training_details').onkeypress = function() {
			return onlyAlphaNumeric(event,this);
		};
		document.getElementById('doc_of_multispecialty_hospital').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"doc_of_multispecialty_hospital","200kb","doc_of_multispecialty_hospital_lbltik","doc_of_multispecialty_hospital_lbl",this.accept)
		};
		document.getElementById('ipd_diet_register_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"ipd_diet_register_doc","200kb","ipd_diet_register_doc_lbltik","ipd_diet_register_doc_lbl",this.accept)
		};
		
	});
	
// ================================CSP END=================================

	
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

		$("#other_hos_dtl_view_id").val(id);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
	}
	
	
	
</script>
