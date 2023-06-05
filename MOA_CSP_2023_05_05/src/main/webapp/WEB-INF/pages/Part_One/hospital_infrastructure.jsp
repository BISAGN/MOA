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
						<h2>Hospital Infrastructure</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>

								<li class="breadcrumb-item active" aria-current="page">
									Hospital Infrastructure</li>
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
							Hospital Infrastructure Start
						=========================== -->
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<!-- input style start -->
					<div class="card-style mb-30">
						<div class="tabs content_h1100">
							<!-- Hospital Administration Start -->
							<div class="tab">
								<button class="tab-toggle" id="defaultOpen">Hospital
									Administration</button>
							</div>
							
							
							<div class="content">
								<h4 class="heading">Hospital Administration</h4>
								<form:form name="add_info" id="add_info" action="" method="post" class=""
									commandName="">
									<div class="row">
									
										<input type="hidden" id="basic_info_id" name="basic_info_id" value="0">
									
									<input type="hidden" id="hid_add_info" name="hid_add_info" value="0">
									
									
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
										<div class="input-style-1 mb-0">
										<div class="custom-choose-one">
											<label>Does the teaching hospital of the college
												fulfill the Statutory Requirements of the concerned
												State/Union Territory/Local Authority to establish and run
												the hospital?<strong class="mandatory">*</strong>
											</label>
											<div class="input-style-form-check">

												<div class="form-check radio-style">
													<input type="radio" id="statytory_Yes"
														name="statytorycheck" class="form-check-input" value="1">
													<label for="statytory_Yes" class="form-check-label">Yes</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" id="statytory_No" name="statytorycheck"
														class="form-check-input" value="0"> <label
														for="statytory_No" class="form-check-label">No</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" id="statytory_No_req"
														name="statytorycheck" class="form-check-input" value="2">
													<label for="statytory_No_req" class="form-check-label">Not
														Required</label>
												</div>
											</div>
											</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="table-wrapper table-responsive custom-table">
												<table class="table" id="anatomy_table">
													<thead>
														<tr>
															<th><h6>Sr No</h6></th>
																<th><h6>Departments <strong class="mandatory">*</strong> </h6></th>
															<th><h6>
																	Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
																</h6></th>
														</tr>

													</thead>
													<tbody id="">
													
														${dataforHA}
														
																	<input type="hidden" id="hid_admin_${j[0]}" name="hid_admin_${j[0]}" value="0">
																	<input type="hidden" id="hospital_department_name_${j[0]}" name="hospital_department_name_${j[0]}" value="${j[1]}">
																	<input type="hidden" id="department_id_${j[0]}" name="department_id_${j[0]}" value="${j[0]}">
														
														<input type="hidden" id="statu_detail_hidden"
															name="statu_detail_hidden" value="0" class="form-control ">		
														
														
														<tr>
															
															<td colspan="2">
																<p>Total Constructed Area in Admin Block (in Sq. mtr) </p>
															</td>

															<td>
																<div class="input-style-1">
																<input type="text" name="admin_constructed_area"
																		id="admin_constructed_area" class="form-control"
																		placeholder="Total Available Area (in Sq. mtr.)">
																</div>
															</td>
														</tr> 

														<!-- end table row -->
													</tbody>
												</table>
											</div>
										</div>
										<!-- end col -->
									</div>
									<!-- end row -->
									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover btnsave" id="hos_adm_save"
														type="button" value="Save"></li>
														<li><a 
																class="main-btn dark-btn-outline btn-hover btn-iconic-icon viewData d-none"><i
																	class="lni lni-eye"></i>View</a>
																	<input type='hidden' id="viewId${parent_id}" value="${institude}"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>

							</div>
							<!-- Hospital Administration End -->
							<!-- Hospital OPD Start -->
							<div class="tab">
								<button class="tab-toggle">Hospital OPD</button>
							</div>
							<div class="content">
								<h4 class="heading">Hospital OPD</h4>
								<form:form name="add_opd" id="add_opd" action="" method="post" class=""
									commandName="">
									
									
									<input type="hidden" id="hid_add_opd" name="hid_add_opd" value="0">
									
									<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="table-wrapper table-responsive custom-table">
												<table class="table" id="opd_table">
													<thead>
														<tr>
															<th><h6>Sr No</h6></th>
															<th><h6>Departments<strong class="mandatory">*</strong></h6></th>
															<th><h6>
																	Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
																</h6></th>
														</tr>

													</thead>
													<tbody id="">
													
													${dataforOPD}
													
													<input type="hidden" id="hid_opd_${j[0]}" name="hid_opd_${j[0]}" value="0">
													<input type="hidden" id="opd_department_name_${j[0]}" name="opd_department_name_${j[0]}" value="${j[1]}">
													<input type="hidden" id="department_id_${j[0]}" name="department_id_${j[0]}" value="${j[0]}">
													
														
														<tr>
															
															<td colspan="2">
																<p>Total Constructed Area in OPD Block</p>
															</td>

															<td>
																<div class="input-style-1">
																	<input type="text" name="opd_constructed_area"
																			id="opd_constructed_area" class="form-control"
																			placeholder="Total Available Area (in Sq. mtr.)">
																</div>
															</td>
														</tr>
														<!-- end table row -->
													</tbody>
												</table>
											</div>
										</div>
										<!-- end col -->
									</div>
									<!-- end row -->
									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover btnsave" id="hos_opd_save"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>

							</div>
							<!-- Hospital OPD End -->
							<!-- Hospital IPD Start -->
							<div class="tab">
								<button class="tab-toggle">Hospital IPD</button>
							</div>
							<div class="content">
								<h4 class="heading">Hospital IPD</h4>
								<form:form name="add_ipd" id="add_ipd" action="" method="post" class=""
									commandName="">
									
									<input type="hidden" id="hid_add_ipd" name="hid_add_ipd" value="0">
									
									<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="table-wrapper table-responsive custom-table">
												<table class="table" id="ipd_table">
													<thead>
														<tr>
															<th><h6>Sr No</h6></th>
															<th><h6>Departments<strong class="mandatory">*</strong></h6></th>
															<th><h6>
																	Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
																</h6></th>
														</tr>

													</thead>
													<tbody id="">
														${dataforIPD}
														
														<input type="hidden" id="hid_ipd_${j[0]}" name="hid_ipd_${j[0]}" value="0">
																	<input type="hidden" id="ipd_department_name_${j[0]}" name="ipd_department_name_${j[0]}" value="${j[1]}">
																	<input type="hidden" id="department_id_${j[0]}" name="department_id_${j[0]}" value="${j[0]}">
														
														
														<input type="hidden" id="another_detail_cs_hidden"
															name="another_detail_cs_hidden" value="0" class="form-control ">	
														
														<tr>
															<td colspan="2">
																<p>Total Constructed Area in IPD Block </p>
															</td>

															<td>
																<div class="input-style-1">
																	  <input type="text" name="ipd_constructed_area"
																			id="ipd_constructed_area" class="form-control"
																			placeholder="Total Available Area (in Sq. mtr.)">
																</div>
															</td>
														</tr>
														
														<!-- end table row -->
													</tbody>
												</table>
											</div>
										</div>
										<!-- end col -->
									</div>
									<!-- end row -->
									
									<div class="row">
									
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">

											<div class="input-style-form-check">
												<label>Casualty Department<strong class="mandatory">*</strong>
												</label>
												<div class="form-check radio-style">
													<input type="radio" id="ipd_casualty_Yes"
														name="ipdcasualtycheck" class="form-check-input" value="1">
													<label for="ipd_casualty_Yes" class="form-check-label">Yes</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" id="ipd_casualty_No"
														name="ipdcasualtycheck" class="form-check-input" value="0">
													<label for="ipd_casualty_No" class="form-check-label">No</label>
												</div>

											</div>
										</div>
										
										<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hidden_casultyservice_document">
																	<div class="input-style-1">
																		<label>If Casulty Services available upload
																			details here <strong class="mandatory">*</strong></label> <input type="file" id="casultyservice_document" name="casultyservice_document"
																			class="form-control" accept=".pdf"><input type="hidden"
																				id="hid_casultyservice_document" name="hid_casultyservice_document"
																				class="form-control" value="">
																		<div class="note-text">
																			<span class="errorClass" id="casultyservice_document_lbl">${doc_path_doc1_msg}</span>
																			<span class='tikClass' id="casultyservice_document_lbltik"></span>
																		</div>
																	</div>
																</div>
										
										
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">

											<div class="input-style-form-check">
												<label>Publication Treatment Outcome in Ipd/Opd<strong class="mandatory">*</strong>
												</label>
												<div class="form-check radio-style">
													<input type="radio" id="ipd_treatment_Yes"
														name="ipdtreatmentcheck" class="form-check-input" value="1">
													<label for="ipd_treatment_Yes" class="form-check-label">Yes</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" id="ipd_treatment_No"
														name="ipdtreatmentcheck" class="form-check-input" value="0">
													<label for="ipd_treatment_No" class="form-check-label">No</label>
												</div>

											</div>
										</div>
										
										
										<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hidden_treat_outcome_document">
																	<div class="input-style-1">
																		<label>If Publication Treatment Outcome in Ipd/Opd  available upload
																			details here <strong class="mandatory">*</strong></label> <input type="file" id="treatment_outcome_ipd_document" name="treatment_outcome_ipd_document"
																			class="form-control" accept=".pdf"><input type="hidden"
																				id="hid_treat_outcome_document" name="hid_treat_outcome_document"
																				class="form-control" value="">
																		<div class="note-text">
																			<span class="errorClass" id="treatoutcome_document_lbl">${doc_path_doc1_msg}</span>
																			<span class='tikClass' id="treatoutcome_document_lbltik"></span>
																		</div>
																	</div>
																</div>
										
								</div>
									
									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover btnsave" id="hos_ipd_save"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>

							</div>
							<!-- Hospital IPD End -->
							<!-- Operation Theatre Start -->
							<div class="tab">
								<button class="tab-toggle">Operation Theatre</button>
							</div>
							<div class="content">
								<h4 class="heading">Operation Theatre</h4>
								<form:form name="add_ot" id="add_ot" action="" method="post" class=""
									commandName="">
								
									<input type="hidden" id="hid_add_ot" name="hid_add_ot" value="0">
									
									<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="table-wrapper table-responsive custom-table">
												<table class="table" id="ot_table">
													<thead>
														<tr>
															<th><h6>Sr No</h6></th>
															<th><h6>Departments<strong class="mandatory">*</strong></h6></th>
															<th><h6>
																	Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
																</h6></th>
														</tr>

													</thead>
													<tbody id="">
													
													${dataforOT}
													
													<input type="hidden" id="hid_ot_${j[0]}" name="hid_ot_${j[0]}" value="0">
													<input type="hidden" id="ot_department_name_${j[0]}" name="ot_department_name_${j[0]}" value="${j[1]}">
													<input type="hidden" id="department_id_${j[0]}" name="department_id_${j[0]}" value="${j[0]}">
														
													
														
														<tr>
															
															<td colspan="2">
																<p>Total Constructed Area in Operation
																			Theatre Block</p>
															</td>

															<td>
																<div class="input-style-1">
																 <input type="text" name="ot_constructed_area"
																			id="ot_constructed_area" class="form-control"
																			placeholder="Total Available Area (in Sq. mtr.)">
																</div>
															</td>
														</tr>
														<!-- end table row -->
													</tbody>
												</table>
											</div>
										</div>
										<!-- end col -->
									</div>
									<!-- end row -->
									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover btnsave"  id="hos_ot_save"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>

							</div>
							<!-- Operation Theatre End -->
							<!-- Rehabilitation Unit Start -->
							<div class="tab">
								<button class="tab-toggle">Rehabilitation Unit</button>
							</div>
							<div class="content">
								<h4 class="heading">Rehabilitation Unit including
									Physiotherapy & Yoga</h4>
								<form:form name="add_ru" id="add_ru" action="" method="post" class=""
									commandName="">
									
									<input type="hidden" id="hid_add_ru" name="hid_add_ru" value="0">
									
									<div class="row">
									
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="table-wrapper table-responsive custom-table">
												<table class="table" id="reh_py_yoga_table">
													<thead>
														<tr>
															<th><h6>Sr No</h6></th>
															<th><h6>Departments<strong class="mandatory">*</strong></h6></th>
															<th><h6>
																	Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
																</h6></th>
														</tr>

													</thead>
													<tbody id="">
													
														${dataforRU}
														
														<input type="hidden" id="hid_ru_${j[0]}" name="hid_ru_${j[0]}" value="0">
																	<input type="hidden" id="department_name_${j[0]}" name="department_name_${j[0]}" value="${j[1]}">
																	<input type="hidden" id="department_id_${j[0]}" name="department_id_${j[0]}" value="${j[0]}">
														
														
														<tr>
															<td colspan="2">
																<p>Total Constructed Area in
																			Rehabilitation Unit including Physiotherapy & Yoga</p>
															</td>

															<td>
																<div class="input-style-1">
																	 <input type="text" name="reh_py_area"
																			id="reh_py_area" class="form-control"
																			placeholder="Total Available Area (in Sq. mtr.)">
																</div>
															</td>
														</tr> 
														
														<!-- end table row -->
													</tbody>
												</table>
											</div>
										</div>
										
										<!-- end col -->
									</div>
									<!-- end row -->
									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input
														class="main-btn info-btn btn-hover btnsave" id="hos_ru_save"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>

							</div>
							<!-- Rehabilitation Unit End -->
							<!-- Clinical Laboratory Start -->
							<div class="tab">
								<button class="tab-toggle">Clinical Laboratory</button>
							</div>
							<div class="content">
								<h4 class="heading">Clinical Laboratory</h4>
								<form:form name="add_cl" id="add_cl" action="" method="post" class=""
									commandName="">
									<input type="hidden" id="hid_add_cl" name="hid_add_cl" value="0">
									
									<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="table-wrapper table-responsive custom-table">
												<table class="table" id="cl_pm_table">
													<thead>
														<tr>
															<th><h6>Sr No</h6></th>
															<th><h6>Departments<strong class="mandatory">*</strong></h6></th>
															<th><h6>
																	Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
																</h6></th>
														</tr>

													</thead>
													<tbody id="">
													
														${dataforCL}
														
														<input type="hidden" id="hid_cl_${j[0]}" name="hid_cl_${j[0]}" value="0">
																	<input type="hidden" id="department_name_${j[0]}" name="department_name_${j[0]}" value="${j[1]}">
																	<input type="hidden" id="department_id_${j[0]}" name="department_id_${j[0]}" value="${j[0]}">

														
														<tr>
															<td colspan="2">
																<p>Total Constructed Area in Clinical
																			Laboratory </p>
															</td>
															<td>
																<div class="input-style-1">
																<input type="text" name="cl_area" id="cl_area"
																			class="form-control"
																			placeholder="Total Available Area (in Sq. mtr.)">
																</div>
															</td>
														</tr>
														<!-- end table row -->
													</tbody>
												</table>
											</div>
										</div>
										<!-- end col -->
									</div>
									<!-- end row -->
									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input
														class="main-btn info-btn btn-hover btnsave" id="hos_cl_save"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>

							</div>
							<!-- Clinical Laboratory End -->
							<!-- Radiology or Sonography Start -->
							<div class="tab">
								<button class="tab-toggle">Radiology or Sonography</button>
							</div>
							<div class="content">
								<h4 class="heading">Radiology or Sonography</h4>
								<form:form name="add_rs" id="add_rs" action="" method="post" class=""
									commandName="">
									
									<input type="hidden" id="hid_add_rs" name="hid_add_rs" value="0">
									<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="table-wrapper table-responsive custom-table">
												<table class="table" id="radiologist_table">
													<thead>
														<tr>
															<th><h6>Sr No</h6></th>
															<th><h6>Departments<strong class="mandatory">*</strong></h6></th>
															<th><h6>
																	Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
																</h6></th>
														</tr>

													</thead>
													<tbody id="">
															${dataforRS}
															
																<input type="hidden" id="hid_rs_${j[0]}" name="hid_rs_${j[0]}" value="0">
																<input type="hidden" id="department_name_${j[0]}" name="department_name_${j[0]}" value="${j[1]}">
																<input type="hidden" id="department_id_${j[0]}" name="department_id_${j[0]}" value="${j[0]}">
													
														
														<tr>
															
															<td colspan="2">
																<p>Total Constructed Area in Radiology or
																			Sonography Block </p>
															</td>

															<td>
																<div class="input-style-1">
																<input type="text" name="rs_area" id="rs_area"
																			class="form-control"
																			placeholder="Total Available Area (in Sq. mtr.)">
																</div>
															</td>
														</tr> 
													
														<!-- end table row -->
													</tbody>
												</table>
											</div>
										</div>
										<!-- end col -->
									</div>
									<!-- end row -->
									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover btnsave" id="hos_rs_save"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>

							</div>
							<!-- Radiology or Sonography End -->
							
							<!-- Hospital Kitchen and Canteen Block Start -->
							<div class="tab">
								<button class="tab-toggle">Hospital Kitchen and Canteen Block</button>
							</div>
							<div class="content">
								<h4 class="heading">Hospital Kitchen and Canteen Block Details</h4>
								<form:form name="add_hk" id="add_hk" action="" method="post" class=""
									commandName="">
									
									<input type="hidden" id="hid_add_hk" name="hid_add_hk" value="0">
									
									<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="table-wrapper table-responsive custom-table">
												<table class="table" id="kcs_table">
													<thead>
														<tr>
															<th><h6>Sr No</h6></th>
															<th><h6>Departments<strong class="mandatory">*</strong></h6></th>
															<th><h6>
																	Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
																</h6></th>
														</tr>

													</thead>
													<tbody id="">
													${dataforHK} 
													
												<input type="hidden" id="hid_hk_${j[0]}" name="hid_hk_${j[0]}" value="0">
												<input type="hidden" id="department_name_${j[0]}" name="department_name_${j[0]}" value="${j[1]}">
												<input type="hidden" id="department_id_${j[0]}" name="department_id_${j[0]}" value="${j[0]}">
														
													
													<!-- 	 <tr>
															<td class="sr-no">
																<p></p>
															</td>
															<td>
																<p>Kitchen & Canteen</p>
															</td>

															<td>
																<div class="input-style-1">
																	<input type="text" name="kcs_kitchencanteen"
																			id="kcs_kitchencanteen" class="form-control"
																			placeholder="Available Area (in Sq. mtr.)">
																</div>
															</td>
														</tr> -->
														
																<tr>
															
															<td colspan="2">
																<p>Total Constructed Area in Kitchen and Canteen Block (in Sq. mtr) </p>
															</td>

															<td>
																<div class="input-style-1">
																<input type="text" name="kitcan_total_area"
																			id="kitcan_total_area" class="form-control"
																			placeholder="Constructed Area (in Sq. mtr.)">
																</div>
															</td>
														</tr> 
														
													</tbody>
												</table>
											</div>
										</div>
										<!-- end col -->
									</div>

									<!-- end row -->
									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input
														class="main-btn info-btn btn-hover btnsave" id="hos_hk_save"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>

							</div>
							<!--Hospital Kitchen and Canteen Block End -->
							
							<!-- Stores Start -->
							
							<div class="tab">
								<button class="tab-toggle">Stores Details</button>
							</div>
							<div class="content">
								<h4 class="heading">Stores Details</h4>
								<form:form name="add_hs" id="add_hs" action="" method="post" class=""
									commandName="">
									
									<input type="hidden" id="hid_add_hs" name="hid_add_hs" value="0">
									
									<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="table-wrapper table-responsive custom-table">
												<table class="table" id="kcs_table">
													<thead>
														<tr>
															<th><h6>Sr No</h6></th>
															<th><h6>Departments<strong class="mandatory">*</strong></h6></th>
															<th><h6>
																	Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
																</h6></th>
														</tr>

													</thead>
													<tbody id="">
												${dataforHS} 
												
												<input type="hidden" id="hid_hs_${j[0]}" name="hid_hs_${j[0]}" value="0">
												<input type="hidden" id="store_department_name_${j[0]}" name="store_department_name_${j[0]}" value="${j[1]}">
												<input type="hidden" id="department_id_${j[0]}" name="department_id_${j[0]}" value="${j[0]}">
													
														
														
															<tr>
															
															<td colspan="2">
																<p>Total Constructed Area in Store Block (in Sq. mtr) </p>
															</td>

															<td>
																<div class="input-style-1">
																<input type="text" name="store_total_area"
																			id="store_total_area" class="form-control"
																			placeholder="Constructed Area (in Sq. mtr.)">
																</div>
															</td>
														</tr> 
														
														
															<tr>
															
															<td colspan="2">
																<p>Grand Total Constructed Area of the Hospital (in Sq. mtr)</p>
															</td>

															<td>
																<div class="input-style-1">
																 <input type="text" name="hos_grandtotal_area"
																			id="hos_grandtotal_area" class="form-control"
																			placeholder="Available Area (in Sq. mtr.)">
																</div>
															</td>
														</tr> 
														<!-- end table row -->
													</tbody>
												</table>
											</div>
										</div>
										<!-- end col -->
									</div>

									<!-- end row -->
									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input
														class="main-btn info-btn btn-hover btnsave" id="hos_hs_save"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>

							</div>
							<!--Stores End -->
							
							
							<!-- Other Infrastructure Details Start -->
							<div class="tab">
								<button class="tab-toggle">Other Infrastructure Details</button>
							</div>
							<div class="content">
								<h4 class="heading">Other Infrastructure Details</h4>
								<form:form name="add_oi" id="add_oi" action="" method="post" class=""
									commandName="">
									
									<input type="hidden" id="hid_add_oi" name="hid_add_oi" value="0">
									
									<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="table-wrapper table-responsive custom-table">
												<table class="table" id="kcs_table">
													<thead>
														<tr>
															<th><h6>Sr No</h6></th>
															<th><h6>Departments<strong class="mandatory">*</strong></h6></th>
															<th><h6>Licence</h6></th>
															<th><h6>
																	Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
																</h6></th>
														</tr>

													</thead>
													<tbody id="">
													
												${dataforOI} 
												
												<input type="hidden" id="hid_other_infra_detail_${j[0]}" name="hid_other_infra_detail_${j[0]}" value="0">
												<input type="hidden" id="department_name_${j[0]}" name="department_name_${j[0]}" value="${j[1]}">
												<input type="hidden" id="department_id_${j[0]}" name="department_id_${j[0]}" value="${j[0]}">
												
												
													<input type="hidden" id="another_detail_hidden"
															name="another_detail_hidden" value="0" class="form-control ">		
															
															
														
														<!-- end table row -->
													</tbody>
												</table>
												
												
															
														 
											</div>
										</div>
										<!-- end col -->
									</div>
									<div class="row">
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">

											<div class="input-style-form-check">
												<label>Ambulance Services<strong class="mandatory">*</strong>
												</label>
												<div class="form-check radio-style">
													<input type="radio" id="ambulance_Yes"
														name="ambulancecheck" class="form-check-input" value="1">
													<label for="ambulance_Yes" class="form-check-label">Yes</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" id="ambulance_No"
														name="ambulancecheck" class="form-check-input" value="0">
													<label for="ambulance_No" class="form-check-label">No</label>
												</div>

											</div>
										</div>
										
											<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hidden_ambulance_documnet">
																	<div class="input-style-1">
																		<label>If Ambulance Services available upload
																			details here <strong class="mandatory">*</strong></label> <input type="file" id="ambulance_document" name="ambulance_document"
																			class="form-control" accept=".pdf"><input type="hidden"
																				id="hid_ambulance_document" name="hid_ambulance_document"
																				class="form-control" value="">
																		<div class="note-text">
																			<span class="errorClass" id="ambulance_document_lbl">${doc_path_doc1_msg}</span>
																			<span class='tikClass' id="ambulance_document_lbltik"></span>
																		</div>
																	</div>
																</div>
										
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">

											<div class="input-style-form-check">
												<label>Sitting arrangement for internees/students in various out Patient Department<strong class="mandatory">*</strong>
												</label>
												<div class="form-check radio-style">
													<input type="radio" id="sitting_Yes"
														name="sittingcheck" class="form-check-input" value="1">
													<label for="sitting_Yes" class="form-check-label">Yes</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" id="sitting_No"
														name="sittingcheck" class="form-check-input" value="0">
													<label for="sitting_No" class="form-check-label">No</label>
												</div>

											</div>
										</div>
										
										
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">

											<div class="input-style-form-check">
												<label>Central Research Laboratory<strong class="mandatory">*</strong>
												</label>
												<div class="form-check radio-style">
													<input type="radio" id="researchlab_Yes"
														name="researchlabcheck" class="form-check-input" value="1">
													<label for="researchlab_Yes" class="form-check-label">Yes</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" id="researchlab_No"
														name="researchlabcheck" class="form-check-input" value="0">
													<label for="researchlab_No" class="form-check-label">No</label>
												</div>

											</div>
										</div>
										
										
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">

											<div class="input-style-form-check">
												<label>Casualty Department<strong class="mandatory">*</strong>
												</label>
												<div class="form-check radio-style">
													<input type="radio" id="casualty_Yes"
														name="casualtycheck" class="form-check-input" value="1">
													<label for="casualty_Yes" class="form-check-label">Yes</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" id="casualty_No"
														name="casualtycheck" class="form-check-input" value="0">
													<label for="casualty_No" class="form-check-label">No</label>
												</div>

											</div>
										</div>
										
										<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hidden_council_documnet">
																	<div class="input-style-1">
																		<label>If Casualty Department Services available upload
																			details here <strong class="mandatory">*</strong></label> <input type="file" id="casulty_document" name="casulty_document"
																			class="form-control" accept=".pdf"><input type="hidden"
																				id="hid_casulty_document" name="hid_casulty_document"
																				class="form-control" value="">
																		<div class="note-text">
																			<span class="errorClass" id="casulty_document_lbl">${doc_path_doc1_msg}</span>
																			<span class='tikClass' id="casulty_document_lbltik"></span>
																		</div>
																	</div>
																</div>

									</div>
									

									<!-- end row -->
									<div class="footer_btn">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input
														class="main-btn success-btn btn-hover btnsubmit" id="hos_oi_save"
														type="button" value="Submit"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>

							</div>
							<!--Other Infrastructure Details End -->
						</div>
					</div>
				</div>
				<!-- ===========================
							Hospital Infrastructure End
						=========================== -->
			</div>
		</div>
		<!-- end row -->
	</div>
	<!-- end container -->
</section>
<!-- regulation components end -->
<c:url value="hospital_infrastructure_view" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="hos_infra_view_id">
	<input type="hidden" name="hos_infra_id" id="hos_infra_view_id" value="0" />
	<input type="hidden" name="statusview" id="statusview" value="0" />
</form:form>

<script type="text/javascript"
	src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>
	
<script type="text/javascript" nonce="${cspNonce}">
	$(document).ready(function() {
		
		$.ajaxSetup({
			async : false
		});	
		
		
		if ('${role}'=='NCH') {
			$(".viewData").addClass("d-none")
		}
		if ('${role}'=='Institute_NCH') {
			$(".viewData").removeClass("d-none")
		} 
		
		$("#basic_info_id").val('${basic_info_id}');
		
	var dataofstatuatory ='${dataofstatuatory}';
	
		if(dataofstatuatory != "[]"){
			
				$("#statu_detail_hidden").val('${dataofstatuatory[0].id}');
				$("#hid_add_info").val('${dataofstatuatory[0].id}');
				var statytorycheck = '${dataofstatuatory[0].statutory_requirements_for_hospital}';
				$("input[name='statytorycheck'][value='"+statytorycheck+"']").prop('checked', true);
				
			}
		
	var dataanotheripd ='${dataanotheripd}';
		
		if(dataanotheripd != "[]"){
			
				$("#another_detail_cs_hidden").val('${dataanotheripd[0].id}');
				$("#hid_add_ipd").val('${dataanotheripd[0].id}');
				
				var ipdcasualtycheck = '${dataanotheripd[0].ipd_casulty_service}';
				$("input[name='ipdcasualtycheck'][value='"+ipdcasualtycheck+"']").prop('checked', true);
				
				if(ipdcasualtycheck == "1"){
					 $("div#hidden_casultyservice_document").show();
				}else{
					 $("div#hidden_casultyservice_document").hide();
				}
				
				
				var ipdtreatmentcheck = '${dataanotheripd[0].treatment_outcome_ipd}';
				$("input[name='ipdtreatmentcheck'][value='"+ipdtreatmentcheck+"']").prop('checked', true);
				
				if(ipdtreatmentcheck == "1"){
					 $("div#hidden_treat_outcome_document").show();
				}else{
					 $("div#hidden_treat_outcome_document").hide();
				}
			
				$("#hid_casultyservice_document").val('${dataanotheripd[0].ipd_casulty_document}');
				$("#hid_treat_outcome_document").val('${dataanotheripd[0].treatment_outcome_ipd_document}');
				
			}
		
		
		var dataanother ='${dataanother}';
		
		if(dataanother != "[]"){
			
			debugger;
			
				$("#another_detail_hidden").val('${dataanother[0].id}');
				$("#hid_add_oi").val('${dataanother[0].id}');
				var ambulancecheck = '${dataanother[0].ambulance_serv}';
				$("input[name='ambulancecheck'][value='"+ambulancecheck+"']").prop('checked', true);
				
				if(ambulancecheck == "1"){
					 $("div#hidden_ambulance_documnet").show();
				}else{
					 $("div#hidden_ambulance_documnet").hide();
				}
				
				var sittingcheck = '${dataanother[0].sitting_arrangment}';
				$("input[name='sittingcheck'][value='"+sittingcheck+"']").prop('checked', true);
			
				var researchlabcheck = '${dataanother[0].central_research_lab}';
				$("input[name='researchlabcheck'][value='"+researchlabcheck+"']").prop('checked', true);
				
				var casualtycheck = '${dataanother[0].casualty_dept}';
				$("input[name='casualtycheck'][value='"+casualtycheck+"']").prop('checked', true);
				
				$("#hid_ambulance_document").val('${dataanother[0].ambulance_document}');
				
			}
		
	});
	
	
	//=== Fetch all details ===
	getAdministration_Details();
	getOpd_Details();
	getIpd_Details();
	getOt_Details();
	getRu_Details();
	getCl_Details();
	getRs_Details();
	getHk_Details();
	getHs_Details();
	getOther_Infrastructure_Details();
	
	
	function Ipd_Casualty_Yes(){
		
		var ipdcasualtycheck = $('input:radio[name=ipdcasualtycheck]:checked').val();
		if(ipdcasualtycheck == "1"){
			 $("div#hidden_casultyservice_document").show();
		}else{
			 $("div#hidden_casultyservice_document").hide();
		}
	}
	
	function Ipd_treatment_Yes(){
		
		var ipdtreatmentcheck = $('input:radio[name=ipdtreatmentcheck]:checked').val();
		if(ipdtreatmentcheck == "1"){
			 $("div#hidden_treat_outcome_document").show();
		}else{
			 $("div#hidden_treat_outcome_document").hide();
		}
	}
	
	function Ambulance_Yes(){
		
		var ambulancecheck = $('input:radio[name=ambulancecheck]:checked').val();
		if(ambulancecheck == "1"){
			 $("div#hidden_ambulance_documnet").show();
		}else{
			 $("div#hidden_ambulance_documnet").hide();
		}
	}
	
	
	function getOpd_Details() {
		$.ajaxSetup({
			async : false
		});
		$.post("getOpd_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
				$("#hid_opd_"+j[i].department_id).val(j[i].id);
				$("#hid_course_opd_"+j[i].department_id).val(j[i].id);
				$("#intake_cap_course_opd_"+j[i].department_id).val(j[i].available_area);
				$("#opd_constructed_area").val(j[i].total_avail_area);
				
			}
		});
	}
	
	function getAdministration_Details() {
		$.ajaxSetup({
			async : false
		});
		$.post("getAdministration_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
				$("#hid_admin_"+j[i].department_id).val(j[i].id);
				$("#hid_course_ha_"+j[i].department_id).val(j[i].id);
				$("#intake_cap_course_ha_"+j[i].department_id).val(j[i].available_area);
				$("#admin_constructed_area").val(j[i].total_avail_area);
			
			}
		});
	}
	
	function getIpd_Details() {
		$.ajaxSetup({
			async : false
		});
		$.post("getIpd_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
				$("#hid_ipd_"+j[i].department_id).val(j[i].id);
				$("#hid_course_ipd_"+j[i].department_id).val(j[i].id);
				$("#intake_cap_course_ipd_"+j[i].department_id).val(j[i].available_area);
				$("#ipd_constructed_area").val(j[i].total_avail_area);
			
			}
		});
	}
	
	function getOt_Details() {
		$.ajaxSetup({
			async : false
		});
		$.post("getOt_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
				$("#hid_ot_"+j[i].department_id).val(j[i].id);
				$("#hid_course_ot_"+j[i].department_id).val(j[i].id);
				$("#intake_cap_course_ot_"+j[i].department_id).val(j[i].available_area);
				$("#ot_constructed_area").val(j[i].total_avail_area);
			
			}
		});
	}
	
	function getRu_Details() {
		$.ajaxSetup({
			async : false
		});
		$.post("getRu_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
				$("#hid_ru_"+j[i].department_id).val(j[i].id);
				$("#hid_course_ru_"+j[i].department_id).val(j[i].id);
				$("#intake_cap_course_ru_"+j[i].department_id).val(j[i].available_area);
				$("#reh_py_area").val(j[i].total_avail_area);
			
			}
		});
	}
	
	function getCl_Details() {
		$.ajaxSetup({
			async : false
		});
		$.post("getCl_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
				$("#hid_cl_"+j[i].department_id).val(j[i].id);
				$("#hid_course_cl_"+j[i].department_id).val(j[i].id);
				$("#intake_cap_course_cl_"+j[i].department_id).val(j[i].available_area);
				$("#cl_area").val(j[i].total_avail_area);				
			}
		});
	}
	
	function getRs_Details() {
		$.ajaxSetup({
			async : false
		});
		$.post("getRs_Details?" + key + "=" + value, {
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				$("#hid_rs_"+j[i].department_id).val(j[i].id);
				$("#hid_course_rs_"+j[i].department_id).val(j[i].id);
				$("#intake_cap_course_rs_"+j[i].department_id).val(j[i].available_area);
				$("#rs_area").val(j[i].total_avail_area);	
			}
		});
	}
	function getHk_Details() {
		$.ajaxSetup({
			async : false
		});
		$.post("getHk_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
				$("#hid_hk_"+j[i].department_id).val(j[i].id);
				$("#hid_course_hk_"+j[i].department_id).val(j[i].id);
				$("#intake_cap_course_hk_"+j[i].department_id).val(j[i].available_area);
				$("#kitcan_total_area").val(j[i].total_avail_area);					
			}
		});
	}
	function getHs_Details() {
		$.ajaxSetup({
			async : false
		});
		$.post("getHs_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
				$("#hid_hs_"+j[i].department_id).val(j[i].id);
				$("#hid_course_hs_"+j[i].department_id).val(j[i].id);
				$("#intake_cap_course_hs_"+j[i].department_id).val(j[i].available_area);
				$("#store_total_area").val(j[i].total_avail_area);	
				$("#hos_grandtotal_area").val(j[i].total_avail_area);	
			}
		});
	}
	
	function getOther_Infrastructure_Details() {
		$.ajaxSetup({
			async : false
		});
		$.post("getOther_Infrastructure_Details?" + key + "=" + value, {
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				$("#hid_course_oi_"+j[i].department_id).val(j[i].id);
				$("#hid_other_infra_detail_"+j[i].department_id).val(j[i].id);
				$("#intake_cap_course_oi_"+j[i].department_id).val(j[i].available_area);
				
			}
		});
	}
	
	
function Save_As_Draft_Add_Info() {
		
		$.ajaxSetup({
		    async: false
		});	
	 	
		var form = $("#add_info").serialize();
		console.log(form);
		$.post(
				'hospital_administrative_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        		$("#hid_add_info").val(j);
		        	  alert("Data Save Sucessfully");
		        	  location.reload();
		          }
		          else{
		        	  alert(j);
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
			
	 }
	 
	 
function Save_As_Draft_Add_Opd() {
	
	$.ajaxSetup({
	    async: false
	});	
 	
	var form = $("#add_opd").serialize();
	console.log(form);
	$.post(
			'hospital_opd_Action?' + key + "="
					+ value, form, function(j) {
				if(j>0){
	        		$("#hid_add_opd").val(j);
	        	  alert("Data Save Sucessfully");
	        	  location.reload();
	          }
	          else{
	        	  alert(j);
	        	  location.reload();
	          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
		
 }	

 
function Save_As_Draft_Add_Ipd() {
	$.ajaxSetup({
	    async: false
	});	
	
	var form_data = new FormData(document.getElementById("add_ipd"));
	$.ajax({
		url : 'hospital_ipd_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j){
      if(j>0){
    		$("#hid_add_ipd").val(j);
    	  alert("Data Save Sucessfully");
      }
      else{
    	  alert(j);
      }
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
		
 }
 
 
function Save_As_Draft_Add_Ot() {
	
	$.ajaxSetup({
	    async: false
	});	
 	
	var form = $("#add_ot").serialize();
	console.log(form);
	$.post(
			'hospital_ot_Action?' + key + "="
					+ value, form, function(j) {
				if(j>0){
	        		$("#hid_add_ot").val(j);
	        	  alert("Data Save Sucessfully");
	        	  location.reload();
	          }
	          else{
	        	  alert(j);
	        	  location.reload();
	          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
		
 }
 
function Save_As_Draft_Add_Ru() {
	
	$.ajaxSetup({
	    async: false
	});	
 	
	var form = $("#add_ru").serialize();
	console.log(form);
	$.post(
			'hospital_ru_Action?' + key + "="
					+ value, form, function(j) {
				if(j>0){
	        		$("#hid_add_ru").val(j);
	        	  alert("Data Save Sucessfully");
	        	  location.reload();
	          }
	          else{
	        	  alert(j);
	        	  location.reload();
	        	  
	          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
		
 }
 
function Save_As_Draft_Add_Cl() {
	$.ajaxSetup({
	    async: false
	});	
 	
	var form = $("#add_cl").serialize();
	console.log(form);
	$.post(
			'hospital_cl_Action?' + key + "="
					+ value, form, function(j) {
				if(j>0){
	        		$("#hid_add_cl").val(j);
	        	  alert("Data Save Sucessfully");
	        	  location.reload();
	          }
	          else{
	        	  alert(j);
	        	  location.reload();
	          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
		
 }
 
function Save_As_Draft_Add_Rs() {
	
	$.ajaxSetup({
	    async: false
	});	
 	
	var form = $("#add_rs").serialize();
	console.log(form);
	$.post(
			'hospital_rs_Action?' + key + "="
					+ value, form, function(j) {
				if(j>0){
	        		$("#hid_add_rs").val(j);
	        	  alert("Data Save Sucessfully");
	        	  location.reload();
	          }
	          else{
	        	  alert(j);
	        	  location.reload();
	          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
		
 }
 
function Save_As_Draft_Add_Hk() {
	
	$.ajaxSetup({
	    async: false
	});	
 	
	var form = $("#add_hk").serialize();
	console.log(form);
	$.post(
			'hospital_hk_Action?' + key + "="
					+ value, form, function(j) {
				if(j>0){
	        		$("#hid_add_hk").val(j);
	        	  alert("Data Save Sucessfully");
	        	  location.reload();
	          }
	          else{
	        	  alert(j);
	        	  location.reload();
	          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
		
 }
 
function Save_As_Draft_Add_Hs() {
	
	$.ajaxSetup({
	    async: false
	});	
 	
	var form = $("#add_hs").serialize();
	console.log(form);
	$.post(
			'hospital_hs_Action?' + key + "="
					+ value, form, function(j) {
				if(j>0){
	        		$("#hid_add_hs").val(j);
	        	  alert("Data Save Sucessfully");
	        	  location.reload();
	          }
	          else{
	        	  alert(j);
	        	  location.reload();
	          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
 }
 
function Save_As_Draft_Add_Oi() {
	$.ajaxSetup({
	    async: false
	});	
	
	var form_data = new FormData(document.getElementById("add_oi"));
	$.ajax({
		url : 'hospital_oi_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j){
      if(j>0){
    		$("#hid_add_oi").val(j);
    	  alert("Data Save Sucessfully");
      }
      else{
    	  alert(j);
      }
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
		
 }
	
	
function saveDraft_Hospital_Administrative_list() {
		
		$.ajaxSetup({
		    async: false
		});	
	
			
			var form = $("#hos_admin").serialize();
			console.log(form);
			$.post(
					'hospital_administrative_Action?' + key + "="
							+ value, form, function(j) {
						if(j>0){
			        	  alert("Data Save Sucessfully");
			        	  location.reload();
			          }
			          else{
			        	  alert(j);
			        	  location.reload();
			          }
				}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
				});
			 
	 }
	 
	 
function GrandSum(){
	var grantotal=0;
	const collection = document.getElementsByClassName("grand");
	console.log("collection"+collection.length);
	for (let i = 0; i < collection.length; i++) {
	  if(collection[i].value!=''){
		  grantotal=grantotal+ parseInt(collection[i].value)
	  }
	}
	$("#admin_constructed_area").val(grantotal);
	
}

function GrandSumopd(){
	var grantotal=0;
	const collection = document.getElementsByClassName("grandopd");
	console.log("collection"+collection.length);
	for (let i = 0; i < collection.length; i++) {
	  if(collection[i].value!=''){
		  grantotal=grantotal+ parseInt(collection[i].value)
	  }
	}
	$("#opd_constructed_area").val(grantotal);
	
}

function GrandSumipd(){
	var grantotal=0;
	const collection = document.getElementsByClassName("grandipd");
	console.log("collection"+collection.length);
	for (let i = 0; i < collection.length; i++) {
	  if(collection[i].value!=''){
		  grantotal=grantotal+ parseInt(collection[i].value)
	  }
	}
	$("#ipd_constructed_area").val(grantotal);
	
}
function GrandSumot(){
	var grantotal=0;
	const collection = document.getElementsByClassName("grandot");
	console.log("collection"+collection.length);
	for (let i = 0; i < collection.length; i++) {
	  if(collection[i].value!=''){
		  grantotal=grantotal+ parseInt(collection[i].value)
	  }
	}
	$("#ot_constructed_area").val(grantotal);
	
}

function GrandSumru(){
	var grantotal=0;
	const collection = document.getElementsByClassName("grandru");
	console.log("collection"+collection.length);
	for (let i = 0; i < collection.length; i++) {
	  if(collection[i].value!=''){
		  grantotal=grantotal+ parseInt(collection[i].value)
	  }
	}
	$("#reh_py_area").val(grantotal);
	
}

function GrandSumcl(){
	var grantotal=0;
	const collection = document.getElementsByClassName("grandcl");
	console.log("collection"+collection.length);
	for (let i = 0; i < collection.length; i++) {
	  if(collection[i].value!=''){
		  grantotal=grantotal+ parseInt(collection[i].value)
	  }
	}
	$("#cl_area").val(grantotal);
	
}

function GrandSumrs(){
	var grantotal=0;
	const collection = document.getElementsByClassName("grandrs");
	console.log("collection"+collection.length);
	for (let i = 0; i < collection.length; i++) {
	  if(collection[i].value!=''){
		  grantotal=grantotal+ parseInt(collection[i].value)
	  }
	}
	$("#rs_area").val(grantotal);
	
}
function GrandSumhk(){
	var grantotal=0;
	const collection = document.getElementsByClassName("grandhk");
	console.log("collection"+collection.length);
	for (let i = 0; i < collection.length; i++) {
	  if(collection[i].value!=''){
		  grantotal=grantotal+ parseInt(collection[i].value)
	  }
	}
	$("#kitcan_total_area").val(grantotal);
	
}

function GrandSumhs(){
	var grantotal=0;
	const collection = document.getElementsByClassName("grandhs");
	console.log("collection"+collection.length);
	for (let i = 0; i < collection.length; i++) {
	  if(collection[i].value!=''){
		  grantotal=grantotal+ parseInt(collection[i].value)
	  }
	}
	$("#store_total_area").val(grantotal);
	
}

function GrandSumhss(){
	var grantotal=0;
	const collection = document.getElementsByClassName("grandhss");
	console.log("collection"+collection.length);
	for (let i = 0; i < collection.length; i++) {
	  if(collection[i].value!=''){
		  grantotal=grantotal+ parseInt(collection[i].value)
	  }
	}
	$("#hos_grandtotal_area").val(grantotal);
	
}


function Validation() {
	
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	
	var statytorycheck = $("input[name='statytorycheck']:checked").val();
	if( statytorycheck == null ){
		alert("Please Select Statutory Requirements");
		return false;
   	}
	<c:forEach var="j" items="${getHospital_department_administrative_list}" varStatus="num">
	
	var res = CheckNullorBlank('intake_cap_course_ha_'+${j[0]});
	if (res !== "true") {
// 		alert(res +"Hospital Administration's Available Area");
		alert(res +"Hospital Administration's Available Area In ${j[1]}");
		$('#intake_cap_course_ha_'+${j[0]}).focus();
		return false;
	}
	</c:forEach>
	Save_As_Draft_Add_Info();
}
function ValidationofOpd() {
	
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	
	<c:forEach var="j" items="${getHospital_department_opd_list}" varStatus="num">
	
	var res = CheckNullorBlank('intake_cap_course_opd_'+${j[0]});
	if (res !== "true") {
		alert(res +"OPD's Available Area In ${j[1]}");
		$('#intake_cap_course_opd_'+${j[0]}).focus();
		return false;
	}
	</c:forEach>
	return Save_As_Draft_Add_Opd();	
}
function ValidationofIpd() {
	
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	
	
	<c:forEach var="j" items="${getHospital_department_ipd_list}" varStatus="num">
	
	var res = CheckNullorBlank('intake_cap_course_ipd_'+${j[0]});
	if (res !== "true") {
		alert(res +"IPD's Available Area In ${j[1]}");
		$('#intake_cap_course_ipd_'+${j[0]}).focus();
		return false;
	}
	</c:forEach>
	return Save_As_Draft_Add_Ipd();	
}

function ValidationofOt() {
	
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	
	<c:forEach var="j" items="${getHospital_department_ot_list}" varStatus="num">
	
	var res = CheckNullorBlank('intake_cap_course_ot_'+${j[0]});
	if (res !== "true") {
		alert(res +"Operation Theatre's Available Area In ${j[1]} ");
		$('#intake_cap_course_ot_'+${j[0]}).focus();
		return false;
	}
	</c:forEach>
	return Save_As_Draft_Add_Ot();	
}

function ValidationofRehabilitation() {
	
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	
	<c:forEach var="j" items="${getHospital_department_ru_list}" varStatus="num">
	
	var res = CheckNullorBlank('intake_cap_course_ru_'+${j[0]});
	if (res !== "true") {
		alert(res +"Rehabilitation's Available Area In ${j[1]}");
		$('#intake_cap_course_ru_'+${j[0]}).focus();
		return false;
	}
	</c:forEach>
	return Save_As_Draft_Add_Ru();	
}

function ValidationofClinical() {
	
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	
	<c:forEach var="j" items="${getHospital_department_cl_list}" varStatus="num">
	
	var res = CheckNullorBlank('intake_cap_course_cl_'+${j[0]});
	if (res !== "true") {
		alert(res +"Clinical's Available Area In ${j[1]}");
		$('#intake_cap_course_cl_'+${j[0]}).focus();
		return false;
	}
	</c:forEach>
	
	return Save_As_Draft_Add_Cl();	
}

function ValidationofRadiology() {
	
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	
	<c:forEach var="j" items="${getHospital_department_rs_list}" varStatus="num">
	
	var res = CheckNullorBlank('intake_cap_course_rs_'+${j[0]});
	if (res !== "true") {
		alert(res +"Radiology's Available Area In ${j[1]}");
		$('#intake_cap_course_rs_'+${j[0]}).focus();
		return false;
	}
	</c:forEach>
	return Save_As_Draft_Add_Rs();	
}

function Validationofkitchen() {
	
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	
	<c:forEach var="j" items="${getHospital_department_hk_list}" varStatus="num">
	
	var res = CheckNullorBlank('intake_cap_course_hk_'+${j[0]});
	if (res !== "true") {
		alert(res +"Kitchen's Available Area In ${j[1]}");
		$('#intake_cap_course_hk_'+${j[0]}).focus();
		return false;
	}
	</c:forEach>
	return Save_As_Draft_Add_Hk();	
}

function Validationofstore() {
	
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	
	<c:forEach var="j" items="${getHospital_department_hs_list}" varStatus="num">
	
	var res = CheckNullorBlank('intake_cap_course_hs_'+${j[0]});
	if (res !== "true") {
		alert(res +"Store's  Available Area In ${j[1]}");
		$('#intake_cap_course_hs_'+${j[0]}).focus();
		return false;
	}
	</c:forEach>
	return Save_As_Draft_Add_Hs();	
}
function Validationofcheck() {
	
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	
	
	<c:forEach var="j" items="${getHospital_department_oi_list}" varStatus="num">
	
	var res = CheckNullorBlank('intake_cap_course_oi_'+${j[0]});
	if (res !== "true") {
		alert(res +"Other Infrastructure Details's Available Area In ${j[1]}");
		$('#intake_cap_course_oi_'+${j[0]}).focus();
		return false;
	}
	</c:forEach>
	
	var ambulancecheck = $("input[name='ambulancecheck']:checked").val();
	if( ambulancecheck == null ){
		alert("Please Select Ambulance Service Requirement ");
		return false;
   	}
	
	var sittingcheck = $("input[name='sittingcheck']:checked").val();
	if( sittingcheck == null ){
		alert("Please Select Sitting Arrangment Service");
		return false;
   	}
	
	var researchlabcheck = $("input[name='researchlabcheck']:checked").val();
	if( researchlabcheck == null ){
		alert("Please Select Central Research Lab Services");
		return false;
   	}
	var casualtycheck = $("input[name='casualtycheck']:checked").val();
	if( casualtycheck == null ){
		alert("Please Select Casualty Department");
		return false;
   	}
	return Save_As_Draft_Add_Oi();
}

	 
	document.addEventListener('DOMContentLoaded', function() {
		
		
		document.getElementById('hos_adm_save').onclick = function() {
			return Validation();	
// 			return Save_As_Draft_Add_Info();	
		};
		
		document.getElementById('hos_opd_save').onclick = function() {
			return ValidationofOpd();
// 			return Save_As_Draft_Add_Opd();	
		};
		
		document.getElementById('hos_ipd_save').onclick = function() {
			return ValidationofIpd();
// 			return Save_As_Draft_Add_Ipd();	
		};
		
		document.getElementById('ipd_casualty_Yes').onclick = function() {
			Ipd_Casualty_Yes();
		};
		document.getElementById('ipd_casualty_No').onclick = function() {
			Ipd_Casualty_Yes();
		};
		
		document.getElementById('casultyservice_document').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"casultyservice_document","200kb","casultyservice_document_lbltik","casultyservice_document_lbl",this.accept)
		};
		
		document.getElementById('ipd_treatment_Yes').onclick = function() {
			Ipd_treatment_Yes();
		};
		document.getElementById('ipd_treatment_No').onclick = function() {
			Ipd_treatment_Yes();
		};
		document.getElementById('treatment_outcome_ipd_document').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"treatment_outcome_ipd_document","200kb","treatoutcome_document_lbltik","treatoutcome_document_lbl",this.accept)
		};
		
		document.getElementById('hos_ot_save').onclick = function() {
			return ValidationofOt();
// 			return Save_As_Draft_Add_Ot();	
		};
		
		document.getElementById('hos_ru_save').onclick = function() {
			return ValidationofRehabilitation();
// 			return Save_As_Draft_Add_Ru();	
		};
		
		document.getElementById('hos_cl_save').onclick = function() {
			return ValidationofClinical();
// 			return Save_As_Draft_Add_Cl();	
		};
		
		document.getElementById('hos_rs_save').onclick = function() {
			return ValidationofRadiology();
// 			return Save_As_Draft_Add_Rs();	
		};
		
		document.getElementById('hos_hk_save').onclick = function() {
			return Validationofkitchen();
// 			return Save_As_Draft_Add_Hk();	
		};
		document.getElementById('hos_hs_save').onclick = function() {
			return Validationofstore();
// 			return Save_As_Draft_Add_Hs();	
		};
		
		document.getElementById('hos_oi_save').onclick = function() {
			return Validationofcheck();
// 			return Save_As_Draft_Add_Oi();	
		};
		document.getElementById('ambulance_Yes').onclick = function() {
			Ambulance_Yes();
		};
		document.getElementById('ambulance_No').onclick = function() {
			Ambulance_Yes();
		};
		
		document.getElementById('ambulance_document').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"ambulance_document","200kb","ambulance_document_lbltik","ambulance_document_lbl",this.accept)
		};
		
		document.querySelectorAll('.grand').forEach((items, index) => {
			items.addEventListener('blur', event => {
				GrandSum();
			})
		});
		
		document.querySelectorAll('.grandopd').forEach((items, index) => {
			items.addEventListener('blur', event => {
				GrandSumopd();
			})
			
		});
		
		document.querySelectorAll('.grandipd').forEach((items, index) => {
			items.addEventListener('blur', event => {
				GrandSumipd();
			})
			
		});
		
		document.querySelectorAll('.grandot').forEach((items, index) => {
			items.addEventListener('blur', event => {
				GrandSumot();
			})
			
		});
		
		document.querySelectorAll('.grandru').forEach((items, index) => {
			items.addEventListener('blur', event => {
				GrandSumru();
			})
			
		});
		document.querySelectorAll('.grandcl').forEach((items, index) => {
			items.addEventListener('blur', event => {
				GrandSumcl();
			})
			
		});
		document.querySelectorAll('.grandrs').forEach((items, index) => {
			items.addEventListener('blur', event => {
				GrandSumrs();
			})
			
		});
		document.querySelectorAll('.grandhk').forEach((items, index) => {
			items.addEventListener('blur', event => {
				GrandSumhk();
			})
		});
		
		document.querySelectorAll('.grandhs').forEach((items, index) => {
			items.addEventListener('blur', event => {
				GrandSumhs();
			})
		});
		
		document.querySelectorAll('.grandhss').forEach((items, index) => {
			items.addEventListener('blur', event => {
				GrandSumhss();
			})
		});
		
		<c:forEach var="j" items="${getHospital_department_administrative_list}" varStatus="num">
		
		document.getElementById('intake_cap_course_ha_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		
		</c:forEach>
		
		<c:forEach var="j" items="${getHospital_department_opd_list}" varStatus="num">
		
		document.getElementById('intake_cap_course_opd_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		
		</c:forEach>
		
		<c:forEach var="j" items="${getHospital_department_ipd_list}" varStatus="num">
		
		document.getElementById('intake_cap_course_ipd_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		
		</c:forEach>
		
	<c:forEach var="j" items="${getHospital_department_ot_list}" varStatus="num">
		
		document.getElementById('intake_cap_course_ot_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		
		</c:forEach>
		
	<c:forEach var="j" items="${getHospital_department_ru_list}" varStatus="num">
		document.getElementById('intake_cap_course_ru_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		
		</c:forEach>
		
	<c:forEach var="j" items="${getHospital_department_cl_list}" varStatus="num">
		
		document.getElementById('intake_cap_course_cl_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		
		</c:forEach>
		
	<c:forEach var="j" items="${getHospital_department_rs_list}" varStatus="num">
		
		document.getElementById('intake_cap_course_rs_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		
		</c:forEach>
		
	<c:forEach var="j" items="${getHospital_department_hk_list}" varStatus="num">
		
		document.getElementById('intake_cap_course_hk_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		
		</c:forEach>
		
	<c:forEach var="j" items="${getHospital_department_hs_list}" varStatus="num">
		
		document.getElementById('intake_cap_course_hs_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		
		</c:forEach>
		
	<c:forEach var="j" items="${getHospital_department_oi_list}" varStatus="num">
		
		document.getElementById('intake_cap_course_oi_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		
		</c:forEach>
		
	});
	
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

		$("#hos_infra_view_id").val(id);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
	} 
	
	</script>
