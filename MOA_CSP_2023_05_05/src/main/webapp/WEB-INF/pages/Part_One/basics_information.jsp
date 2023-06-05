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
							<h2>Institution Information</h2>
						</div>
					</div>
					<!-- end col -->
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="breadcrumb-wrapper mb-30">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
									<li class="breadcrumb-item active" aria-current="page">
										Institution Information</li>
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
		Institution Information Start
===========================     -->
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card-style mb-30">
							<div class="tabs content_h1100">
								<!-- 1. Institution Basic Details Start-->
								<div class="tab">
									<button class="tab-toggle" id="defaultOpen">Institution
										Basic Details</button>
								</div>
								<div class="content">
								<form:form name="institute_info" id="clg_reg_institute_info" class="clg_reg_institute_info"
								 action="institute_info_action?${_csrf.parameterName}=${_csrf.token}" method="post" 
																modelAttribute="institute_info_CMD" enctype="multipart/form-data">
									<h4 class="heading">Institution Basic Details</h4>
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
										
											<div class="accordion accordion-flush"
												id="accordionFlushExample">
												<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-headingOne">
														<button class="accordion-button accordion-secondary-btn"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapseOne" aria-expanded="true"
															aria-controls="flush-collapseOne">Institution
															Address Details</button>
													</h2>
													<div id="flush-collapseOne"
														class="accordion-collapse collapse show"
														aria-labelledby="flush-headingOne"
														data-bs-parent="#accordionFlushExample">
														<div class="accordion-body">
															<input type="hidden" id="inst_basic_hidden"
																		name="inst_basic_hidden" value="0"
																		class="form-control ">
																<div class="row">
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>Address Line 1 <strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="per_add_line1" id="per_add_line1"
																				class="form-control" placeholder="Address Line 1"
																				maxlength="100">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>Address Line 2 <strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="per_add_line2" id="per_add_line2"
																				class="form-control" placeholder="Address Line 2"
																				maxlength="100">
																		</div>
																	</div>
																	
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="select-style-1">
																			<label>State <strong class="mandatory">*
																			</strong></label>
																<div class="select-position">
																<select class="form-control" name="per_state" id="per_state">
																	<!-- style="text-transform: uppercase" -->
																	<option value="0" selected="selected">--Select
																		State --</option>
																	<c:forEach var="item" items="${getMedStateName}"
																		varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>
																</select>
															</div>
																		</div>
																	</div>
																	
																	
																	
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="select-style-1">
																			<label>District <strong class="mandatory">*
																			</strong></label>
																<div class="select-position">
																<select class="form-control" name="per_district" id="per_district">
																	<!-- style="text-transform: uppercase" -->
																	<option value="0" selected="selected">--Select
																		 --</option>
																	
																</select>
															</div>
																		</div>
																	</div>
																	
																	
																	
																	
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>City<strong class="mandatory">*</strong></label>
																			<input type="text" name="city" id="city"
																				class="form-control"
																				placeholder="Enter City" maxlength="100">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>Pincode <strong class="mandatory">*</strong></label>
																			<input type="text" name="per_pincode"
																				id="per_pincode" class="form-control"
																				placeholder="Pincode" maxlength="6">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<label class="buttonmerge">College Phone
																			Number<strong class="mandatory">*</strong>
																		</label>
																		<div class="groupadd">
																			<div class="select-style-1 form-group qualificationdiv">
																				
																				<input type="text" id="cand_prefix_clg" name="cand_prefix_clg"
																					class="form-control" autocomplete="off"
																					placeholder="STD" maxlength="6">
																					
																				
																			</div>
																			<div
																				class="input-style-1 form-group buttonmergeinput">
																				<input type="text" id="alt_mo_no_clg" name="alt_mo_no_clg"
																					class="form-control" autocomplete="off"
																					placeholder="Phone Number" maxlength="10">
																			</div>
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<label class="buttonmerge">Hospital Phone
																			Number<strong class="mandatory">*</strong>
																		</label>
																		<div class="groupadd">
																		
																		
																		<div class="select-style-1 form-group qualificationdiv">
																				
																				<input type="text" id="cand_prefix_hospi" name="cand_prefix_hospi"
																					class="form-control" autocomplete="off"
																					placeholder="STD" maxlength="6">
																						
																			</div>
																			<div
																				class="input-style-1 form-group buttonmergeinput">
																				<input type="text" id="alt_mo_no_hosp" name="alt_mo_no_hosp"
																					class="form-control" autocomplete="off"
																					placeholder="Phone Number" maxlength="10">
																			</div>
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>Mobile Number<strong class="mandatory">*</strong></label>
																			<input type="text" id="mobile_no_addr" name="mobile_no_addr"
																				class="form-control" autocomplete="off"
																				placeholder="Mobile Number" minlength="10" maxlength="10">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<label class="buttonmerge">Fax Number</label>
																		<div class="stepform-groupadd">
																			<div
																				class="input-style-1 form-group qualificationdiv">
																				<input type="text" id="fax_code" name="fax_code"
																					class="form-control" autocomplete="off"
																					placeholder="STD" maxlength="6">
																			</div>
																			<div
																				class="input-style-1 form-group buttonmergeinput">
																				<input type="text" id="fax_no" name="fax_no"
																					class="form-control" autocomplete="off"
																					placeholder="Fax Number" maxlength="10">
																			</div>
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>Email ID <strong class="mandatory">*</strong></label>
																			<input type="text" name="email" id="email"
																				class="form-control" placeholder="abc@example.com"
																				pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>Website<strong class="mandatory">*</strong></label>
																			<input type="text" name="website" id="website"
																				class="form-control"
																				placeholder="https://www.yourwebsite.com"
																				maxlength="70">
																		</div>
																	</div>
																</div>
															
														</div>
													</div>
												</div>
												<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-headingTwo">
														<button
															class="accordion-button accordion-secondary-btn collapsed"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapseTwo" aria-expanded="false"
															aria-controls="flush-collapseTwo">Management
															Contact Details</button>
													</h2>
													<div id="flush-collapseTwo"
														class="accordion-collapse collapse"
														aria-labelledby="flush-headingTwo"
														data-bs-parent="#accordionFlushExample">
														<div class="accordion-body">
															
																<div class="row">
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="select-style-1">
																			<label>Institution Type <strong
																				class="mandatory">* </strong></label>
																			<div class="select-position">
																				<select name="institution_type"
																					id="institution_type" class="form-control">
																					<option value="0" selected="selected">--
																						Select--</option>
																					<option value="1">Government</option>
																					<option value="2">Private</option>

																				</select>
																			</div>
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>Name of the Managing Body<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="managing_body" id="managing_body"
																				class="form-control"
																				placeholder="Name of the Managing Body" maxlength="100">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>Name of the Management Contact<strong
																				class="mandatory">*</strong>
																			</label> <input type="text" name="management_contact"
																				id="management_contact" class="form-control"
																				placeholder="Name of the Management Contact" maxlength="100">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>Address Line 1 <strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="mang_per_add_line1" id="mang_per_add_line1"
																				class="form-control" placeholder="Address Line 1" maxlength="100">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>Address Line 2<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="mang_per_add_line2" id="mang_per_add_line2"
																				class="form-control" placeholder="Address Line 2" maxlength="100">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="select-style-1">
																			<label>State <strong class="mandatory">*
																			</strong></label>
																			<div class="select-position">
																	<select class="form-control" name="mang_per_state" id="mang_per_state">
																	<!-- style="text-transform: uppercase" -->
																	<option value="0" selected="selected">--Select
																		State --</option>
																	<c:forEach var="item" items="${getMedStateName}"
																		varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>
																</select>
															</div>
																		</div>
																	</div>
																	
																	
																	
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="select-style-1">
																			<label>District <strong class="mandatory">*
																			</strong></label>
																<div class="select-position">
																<select class="form-control" name="mang_per_district" id="mang_per_district">
																	<!-- style="text-transform: uppercase" -->
																	<option value="0" selected="selected">--Select
																		 --</option>
																	
																</select>
															</div>
																		</div>
																	</div>
																	
																	
																	
																	
																	

																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>City<strong class="mandatory">*</strong></label>
																			<input type="text" name="mang_city" id="mang_city"
																				class="form-control"
																				placeholder="Enter City" maxlength="100">
																		</div>
																	</div>

																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<label class="buttonmerge">Office Phone Number<strong
																			class="mandatory">*</strong></label>
																		<div class="groupadd">
																			<div class="input-style-1 form-group">
																				<input type="text" id="mang_office_code"
																					name="mang_office_code" class="form-control"
																					autocomplete="off" placeholder="STD" maxlength="6">
																			</div>

																			<div
																				class="input-style-1 form-group buttonmergeinput">
																				<input type="text" id="mang_office_phone"
																					name="mang_office_phone" class="form-control "
																					autocomplete="off"
																					placeholder="Office Phone Number" maxlength="10">
																			</div>
																		</div>
																	</div>

																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<label class="buttonmerge">Residence Phone
																			Number</label>
																		<div class="groupadd">
																			<div
																				class="input-style-1 form-group qualificationdiv">
																				<input type="text" id="mang_residence_code"
																					name="mang_residence_code" class="form-control "
																					autocomplete="off" placeholder="STD" maxlength="6">
																			</div>

																			<div
																				class="input-style-1 form-group buttonmergeinput">
																				<input type="text" id="mang_residence_phone"
																					name="mang_residence_phone" class="form-control "
																					autocomplete="off"
																					placeholder="Residence Phone Number" maxlength="10">
																			</div>
																		</div>
																	</div>

																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>Mobile Number<strong class="mandatory">*</strong></label>
																			<input type="text" id="mobile_no_mngmt_cont_dt" name="mobile_no_mngmt_cont_dt"
																				class="form-control " autocomplete="off"
																				placeholder="Mobile Number" maxlength="10" minlength="10">
																		</div>
																	</div>

																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>Email ID <strong class="mandatory">*</strong></label>
																			<input type="text" name="email_mgmt_contact_dt" id="email_mgmt_contact_dt"
																				class="form-control" placeholder="abc@example.com"
																				pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$" maxlength="50">
																		</div>
																	</div>
																</div>
															
														</div>
													</div>
												</div>
												<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-headingThree">
														<button
															class="accordion-button accordion-secondary-btn collapsed"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapseThree"
															aria-expanded="false" aria-controls="flush-collapseThree">Basic
															Details</button>
													</h2>
													<div id="flush-collapseThree"
														class="accordion-collapse collapse"
														aria-labelledby="flush-headingThree"
														data-bs-parent="#accordionFlushExample">
														<div class="accordion-body">
															
																<div class="row">
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>Name of Society/Trust/Government<strong
																				class="mandatory">*</strong>
																			</label> <input type="text" name="name_of_society" id="name_of_society"
																				class="form-control"
																				placeholder="Name of Society/Trust/Government" maxlength="100">
																		</div>
																	</div>

																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>Society/Trust Registration Number<strong
																				class="mandatory">*</strong>
																			</label> <input type="text" name="s_registration_no"
																				id="s_registration_no" class="form-control"
																				placeholder="Society/Trust Registration Number" maxlength="50">
																		</div>
																	</div>

																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>Year of Establishment of the
																				Society/Trust<strong class="mandatory">*</strong>
																			</label>
																			<input type="month" id="s_establishment"
																				name="s_establishment"
																				class="form-control-sm form-control effect-9 hasDatepicker" />
<!-- 																			 <input type="text" name="s_establishment" -->
<!-- 																				id="s_establishment" class="form-control" -->
<!-- 																				placeholder="Year of Establishment of the Society/Trust"> -->
																		</div>
																	</div>

																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-2">
																			<label>Date of the permission of the State
																				Govt.<strong class="mandatory">* </strong>
																			</label> <input type="text" name="date_permission_s_govt" id="date_permission_s_govt"
																				maxlength="10"
																				class="form-control-sm form-control effect-9 "
																				aria-required="true" autocomplete="off"
																				value="DD/MM/YYYY">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-2">
																			<label>Date of First Permission of
																				CCH/NCH Central Govt.<strong class="mandatory">*
																			</strong>
																			</label> <input type="text" name="date_permission_c_govt" id="date_permission_c_govt"
																				maxlength="10"
																				class="form-control-sm form-control effect-9 "
																				aria-required="true" autocomplete="off"
																				value="DD/MM/YYYY">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>Name of the affiliated University<strong
																				class="mandatory">*</strong>
																			</label> <input type="text" name="university_affiliated"
																				id="university_affiliated" class="form-control"
																				placeholder="Name of the University affiliated" maxlength="100">
																		</div>
																	</div>

																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-2">
																			<label>Date of affiliation of
																				University<strong class="mandatory">* </strong>
																			</label> <input type="text" name="date_affili_uni" id="date_affili_uni"
																				class="form-control-sm form-control effect-9 "
																				aria-required="true" autocomplete="off"
																				value="DD/MM/YYYY">
																		</div>
																	</div>

																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label>Year of Establishment of the College<strong
																				class="mandatory">*</strong>
																			</label>
																			<input type="month" id="establishment_college"
																				name="establishment_college"
																				class="form-control-sm form-control effect-9 hasDatepicker" />
<!-- 																			 <input type="text" name="establishment_college" -->
<!-- 																				id="establishment_college" class="form-control" -->
<!-- 																				placeholder="Year of Establishment of the College"> -->
																		</div>
																	</div>

																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-2">
																			<label>Date of consent of affiliation from
																				the University<strong
																				class="mandatory">*</strong></label> <input type="text" name="date_consent_affili_uni"
																				id="date_consent_affili_uni" maxlength="10"
																				class="form-control-sm form-control effect-9 "
																				aria-required="true" autocomplete="off"
																				value="DD/MM/YYYY">
																			<div class="note-text">
																				<span class="mandatory">( Applicable only in
																					case of new Colleges. )</span>
																			</div>

																		</div>
																	</div>
																	
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-2">
																			<label>Date of last continuous affiliation
																				 University<strong
																				class="mandatory">*</strong></label> <input type="text" name="date_last_affili_uni"
																				id="date_last_affili_uni" maxlength="10"
																				class="form-control-sm form-control effect-9 "
																				aria-required="true" autocomplete="off"
																				value="DD/MM/YYYY">
																			

																		</div>
																	</div>
																	
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Registration certificate of society/trust<strong
																				class="mandatory">*</strong></label> <input
														type="file" id="trustreg_doc" name="trustreg_doc"
														class="form-control" accept=".pdf">
											<input type="hidden" id="hid_trustreg_doc" name="hid_trustreg_doc" class="form-control" value="">
														<div class="note-text">
																	<span class="errorClass" id="trustreg_doc_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="trustreg_doc_lbltik"></span>
															</div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>A copy of the society/trust deed<strong
																				class="mandatory">*</strong></label> <input
														type="file" id="trustdeed_doc" name="trustdeed_doc"
														class="form-control" accept=".pdf">
												<input type="hidden" id="hid_trustdeed_doc" name="hid_trustdeed_doc" class="form-control" value="">
												<div class="note-text">
																	<span class="errorClass" id="trustdeed_doc_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="trustdeed_doc_lbltik"></span>
															</div>		
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Kindly Upload the undertaking letter to
														furnish Bank Guarantee<strong
																				class="mandatory">*</strong></label> <input type="file"
														id="furnish_bank_doc" name="furnish_bank_doc"
														class="form-control" accept=".pdf">
												<input type="hidden" id="hid_furnish_bank_doc" name="hid_furnish_bank_doc" class="form-control" value="">
												<div class="note-text">
																	<span class="errorClass" id="furnish_bank_doc_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="furnish_bank_doc_lbltik"></span>
															</div>			
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Kindly Upload the NOC document from the
														State Govt.<strong class="mandatory">*</strong>
													</label> <input type="file" id="noc_state_doc" name="noc_state_doc"
														class="form-control" accept=".pdf">
												<input type="hidden" id="hid_noc_state_doc" name="hid_noc_state_doc" class="form-control" value="">		
													<div class="note-text">
																	<span class="errorClass" id="noc_state_doc_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="noc_state_doc_lbltik"></span>
															</div>	
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Kindly Upload the consent of affiliation
														document from concern University<strong class="mandatory">*</strong>
													</label> <input type="file" id="affilat_doc" name="affilat_doc"
														class="form-control" accept=".pdf">
											<input type="hidden" id="hid_affilat_doc" name="hid_affilat_doc" class="form-control" value="">
											<div class="note-text">
																	<span class="errorClass" id="affilat_doc_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="affilat_doc_lbltik"></span>
															</div>				
												</div>
											</div>
											
											
											
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>University Approving Letter<strong class="mandatory">*</strong>
													</label> <input type="file" id="uni_app_doc" name="uni_app_doc"
														class="form-control" accept=".pdf">
											<input type="hidden" id="hid_uni_app_doc" name="hid_uni_app_doc" class="form-control" value="">
											<div class="note-text">
																	<span class="errorClass" id="uni_app_doc_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="uni_app_doc_lbltik"></span>
															</div>				
												</div>
											</div>
											
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>University Affiliation inspection of Last Academic Year <strong class="mandatory">*</strong>
													</label> <input type="file" id="inspection_acade_yr_doc" name="inspection_acade_yr_doc"
														class="form-control" accept=".pdf">
											<input type="hidden" id="hid_inspection_acade_yr_doc" name="hid_inspection_acade_yr_doc" class="form-control" value="">
											<div class="note-text">
																	<span class="errorClass" id="inspection_acade_yr_doc_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="inspection_acade_yr_doc_lbltik"></span>
															</div>				
												</div>
											</div>
											
											
																	
																</div>
															
														</div>
													</div>
												</div>
											</div>
											<div class="footer_btn">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12">
														<ul class="buttons-group mainbtn">
															<li><input
																class="main-btn info-btn btn-hover btnsave" id="save_inst_basic"
																type="button" value="Save"></li>
																
																
																	<input type='hidden' id="viewId${parent_id}" value="${institude}">
														</ul>
													</div>
												</div>
											</div>
										</div>
									</div>
									</form:form>
								</div>

								<!-- 1. Institution Basic Details end-->

								<div class="tab" id="head_of_institution_dtl">
									<button class="tab-toggle">Head of Institution Details</button>
								</div>
								
								<div class="content ">
								<form:form name="head_inst_dtl" id="head_inst_dtl" method="post" 
										 action="head_inst_action?${_csrf.parameterName}=${_csrf.token}" 
																modelAttribute="head_institute_CMD" enctype="multipart/form-data">
									<h4 class="heading">Head of Institution Details</h4>

									

										<div class="accordion accordion-flush"
											id="accordionFlushExample1">
											<div class="accordion-item accordion-itemflush">
												<h2 class="accordion-header" id="flush-headingfive">
													<button class="accordion-button accordion-secondary-btn"
														type="button" data-bs-toggle="collapse"
														data-bs-target="#flush-collapsefive" aria-expanded="true"
														aria-controls="flush-collapsefive">Registration
														Number with Registering authority</button>
												</h2>
												<div id="flush-collapsefive"
													class="accordion-collapse collapse show"
													aria-labelledby="flush-headingfive"
													data-bs-parent="#accordionFlushExample1">
													<div class="accordion-body">
														
															<div class="row">
															
															<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Teacher Code <strong
																			class="mandatory"></strong></label> <input type="text"
																			name="teacher_code" id="teacher_code"
																			class="form-control"
																			placeholder="Teacher Code" maxlength="100">
																	</div>
																</div>
															
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Name of Principal / Dean / Director<strong
																			class="mandatory">*</strong>
																		</label> <input type="text" name="name_pdd" id="name_pdd"
																			class="form-control"
																			placeholder="Name of Principal / Dean / Director"
																			maxlength="100">
																	</div>
																	<input type="hidden" id="head_of_inst_hidden"
																		name="head_of_inst_hidden" value="0"
																		class="form-control ">
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>CCH/NCH Registration Number <strong
																			class="mandatory">*</strong></label> <input type="text"
																			name="cch_registration" id="cch_registration"
																			class="form-control"
																			placeholder="CCH/NCH Registration Number" maxlength="100">
																	</div>
																</div>

																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="select-style-1">
																		<label>State Registration<strong
																			class="mandatory">* </strong></label>
																		<div class="select-position">
																	<select class="form-control" name="state_rn_head" id="state_rn_head">
																	
																	<option value="0" selected="selected">--Select
																		State --</option>
																	<c:forEach var="item" items="${getMedStateName}"
																		varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>
																</select>
															</div>
																	</div>
																</div>

																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Name of The State Registration Number <strong
																			class="mandatory">*</strong></label> <input type="text"
																			name="state_registration_no"
																			id="state_registration_no" class="form-control"
																			placeholder="State Registration Number" maxlength="50">
																	</div>
																</div>

																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-2">
																		<label>Date of Birth<strong class="mandatory">*
																		</strong>
																		</label> <input type="text" name="dob_id" id="dob_id" maxlength="10"
																			class="form-control-sm form-control effect-9 "
																			aria-required="true" autocomplete="off"
																			value="DD/MM/YYYY">
																	</div>

																</div>

																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-2">
																		<label>Date of Joining as Principal / Director<strong
																			class="mandatory">* </strong>
																		</label> <input type="text" name="doj_id" id="doj_id" maxlength="10"
																			class="form-control-sm form-control effect-9 "
																			aria-required="true" autocomplete="off"
																			value="DD/MM/YYYY">
																	</div>
																</div>
															</div>
														
													</div>
												</div>
											</div>
										
											<div class="accordion-item accordion-itemflush">
												<h2 class="accordion-header" id="flush-headingsix">
													<button
														class="accordion-button accordion-secondary-btn collapsed"
														type="button" data-bs-toggle="collapse"
														data-bs-target="#flush-collapsesix" aria-expanded="false"
														aria-controls="flush-collapsesix">Qualification</button>
												</h2>
												<div id="flush-collapsesix"
													class="accordion-collapse collapse"
													aria-labelledby="flush-headingsix"
													data-bs-parent="#accordionFlushExample1">
													<div class="accordion-body">

															<div class="tables-wrapper">
																<div class="row">
																<input type="hidden" id="hid_quali_inst" name="hid_quali_inst" value="0">
																<input type="hidden" id="indno_quali" name="indno_quali" value="0">
																
																<input type="hidden" id="hidden_main" name="hidden_main" value="0">
																	<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																		<div
																			class="table-wrapper table-responsive custom-table">
																			<table class="table" id="qualification_table">
																				<thead>
																					<tr>
																						<th><h6>Sr No</h6></th>
																						<th><h6>
																								Qualification Type<strong class="mandatory">*</strong>
																							</h6></th>
																						<th><h6>
																								Awarding Authority<strong class="mandatory">*</strong>
																							</h6></th>
																						<th><h6>
																								Passing Year<strong class="mandatory">*</strong>
																							</h6></th>
																						<th><h6>Action</h6></th>
																					</tr>
																					<!-- end table row-->
																				</thead>
																				<tbody >
																					<tr id="tr_id_quali">
																						<td>
																							<p>1</p>
																						</td>
																						<td>
																						
																		<div class="select-style-1 ">
																			<div class="select-position">
																			
																				<select name="typeOfDegree1" id="typeOfDegree1"
																					class="form-control autocomplete">
																				
																					<option value="0">--Select--</option>
																					<c:forEach var="item" items="${TypeOfDegree}"
																						varStatus="num">card-style
																			<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																					</c:forEach>
																				</select>
																				
																			</div>
<!-- 																				<input type="hidden" name="hid_quali" -->
<!-- 																									id="hid_quali" class="form-control" value="0"> -->
																		</div>
																						</td>
																						<td>
																							<div class="input-style-1">
																								<input type="text" name="awarding_authority1"
																									id="awarding_authority1" class="form-control"
																									placeholder="Awarding Authority">
																							</div>
																						</td>

																						<td>
																							<div class="input-style-1">
																								<input type="month" name="passsing_yr1"
																									id="passsing_yr1" class="form-control"
																									placeholder="Select Year">
																							</div>
																						</td>

																						<td class="addminusbut">


																				<ul class="buttons-group mainbtn daobtn action">
																								<li><a
																									class="main-btn success-btn btn-hover btn-sm btnapprove"
																									value="Save" title="SAVE" id="save_quali1">
																										<i class="lni lni-checkmark"></i>
																								</a></li>
																								<li><a
																									class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none"
																									value="ADD" title="ADD" id="quali_add1">
																										<i class="lni lni-plus"></i>
																								</a></li>
																								<li><a
																									class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"
																									value="Delete" title="Delete" id="quali_remove1"><i
																										class="lni lni-trash-can"></i></a></li>
																							</ul>
																						</td>
																					</tr>
																					<!-- end table row -->
																				</tbody>
																			</table>
																			
																			<input type="hidden" id="save_qualih1"
																						name="save_qualih1"
																						class="form-control autocomplete" value="1">
																						
																<input type="hidden" id="hid_quali1" name="hid_quali1" value="0" class="form-control autocomplete" autocomplete="off">						
																			
<!-- 																			

																		</div>
																		<!-- end card -->
																	</div>
																	<!-- end col -->
																</div>
																<!-- end row -->
															</div>

													</div>
												</div>
											</div>
                                         </div>
											<div class="accordion-item accordion-itemflush">
												<h2 class="accordion-header" id="flush-headingeight">
													<button
														class="accordion-button accordion-secondary-btn collapsed"
														type="button" data-bs-toggle="collapse"
														data-bs-target="#flush-collapseeight"
														aria-expanded="false" aria-controls="flush-collapseeight">Number
														of Years of Experience</button>
												</h2>
												<div id="flush-collapseeight"
													class="accordion-collapse collapse"
													aria-labelledby="flush-headingeight"
													data-bs-parent="#accordionFlushExample1">
													<div class="accordion-body">
														
															<div class="row">
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Professor <strong class="mandatory">*</strong></label>
																		<input type="text" name="professor" id="professor"
																			class="form-control"
																			placeholder="Professor Experience" maxlength="10">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Reader / Associate Professor <strong
																			class="mandatory">*</strong>
																		</label> <input type="text" name="reader" id="reader"
																			class="form-control"
																			placeholder="Reader / Associate Professor Experience" maxlength="10">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Lecturer / Assistant professor <strong
																			class="mandatory">*</strong>
																		</label> <input type="text" name="lecturer" id="lecturer"
																			class="form-control"
																			placeholder="Lecturer / Assistant professor Experience" maxlength="10">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Demonstrator/ Tutor <strong
																			class="mandatory">*</strong></label> <input type="text"
																			name="demonstrator" id="demonstrator"
																			class="form-control"
																			placeholder="Demonstrator/ Tutor Experience" maxlength="10">
																	</div>
																</div>
															</div>
														
													</div>
												</div>
											</div>

											<div class="accordion-item accordion-itemflush">
												<h2 class="accordion-header" id="flush-headingseven">
													<button
														class="accordion-button accordion-secondary-btn collapsed"
														type="button" data-bs-toggle="collapse"
														data-bs-target="#flush-collapseseven"
														aria-expanded="false" aria-controls="flush-collapseseven">Head
														of Institution Address Details</button>
												</h2>
												<div id="flush-collapseseven"
													class="accordion-collapse collapse"
													aria-labelledby="flush-headingseven"
													data-bs-parent="#accordionFlushExample1">
													<div class="accordion-body">
														
															<div class="row">
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Address Line 1 <strong
																			class="mandatory">*</strong></label> <input type="text"
																			name="head_per_add_line1" id="head_per_add_line1"
																			class="form-control" placeholder="Address Line 1" maxlength="100">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Address Line 2<strong
																			class="mandatory">*</strong></label> <input type="text"
																			name="head_per_add_line2" id="head_per_add_line2"
																			class="form-control" placeholder="Address Line 2" maxlength="100">
																	</div>
																</div>

																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="select-style-1">
																		<label>State <strong class="mandatory">*
																		</strong></label>
																		<div class="select-position">
																	<select class="form-control" name="per_state_head_inst"
																		id="per_state_head_inst">
																	<!-- style="text-transform: uppercase" -->
																	<option value="0" selected="selected">--Select
																		State --</option>
																	<c:forEach var="item" items="${getMedStateName}"
																		varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>
																</select>
															</div>
																	</div>
																</div>
																
																
																
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="select-style-1">
																		<label>District <strong class="mandatory">*
																		</strong></label>
																		<div class="select-position">
																	<select class="form-control" name="per_district_head_inst"
																		id="per_district_head_inst">
																	<!-- style="text-transform: uppercase" -->
																	<option value="0" selected="selected">--Select
																		 --</option>
																	
																</select>
															</div>
																	</div>
																</div>
																
																
																
																
																

																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>City<strong class="mandatory">*</strong></label>
																		<input type="text" name="head_city" id="head_city"
																			class="form-control" placeholder="Enter City/Village" maxlength="100">
																	</div>
																</div>

																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Pincode<strong class="mandatory">*</strong></label>
																		<input type="text" name="head_pincode"
																			id="head_pincode" class="form-control"
																			placeholder="Pincode" maxlength="6">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<label class="buttonmerge">Office Phone Number<strong
																		class="mandatory">*</strong></label>
																	<div class="groupadd">
																		<div class="input-style-1 form-group qualificationdiv">
																			<input type="text" id="head_std" name="head_std"
																				class="form-control " autocomplete="off"
																				placeholder="STD" maxlength="6">
																		</div>

																		<div class="input-style-1 form-group buttonmergeinput">
																			<input type="text" id="head_alt_mo_no1"
																				name="head_alt_mo_no1" class="form-control "
																				autocomplete="off" placeholder="Office Phone Number" maxlength="10">
																		</div>
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<label class="buttonmerge">Residence Phone
																		Number</label>
																	<div class="groupadd">
																		<div class="input-style-1 form-group qualificationdiv">
																			<input type="text" id="headre_std" name="headre_std"
																				class="form-control " autocomplete="off"
																				placeholder="STD" maxlength="6">
																		</div>

																		<div class="input-style-1 form-group buttonmergeinput">
																			<input type="text" id="headre_alt_mo_no1"
																				name="headre_alt_mo_no1" autocomplete="off"
																				placeholder="Residence Phone Number" maxlength="10">
																		</div>
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Mobile Number<strong class="mandatory">*</strong></label>
																		<input type="text" id="head_alt_mo_no2"
																			name="head_alt_mo_no2" class="form-control "
																			autocomplete="off" placeholder="Mobile Number" maxlength="10" minlength="10">
																	</div>
																</div>
																<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																	<div class="input-style-1">
																		<label>Email ID <strong class="mandatory">*</strong></label>
																		<input type="text" name="head_email" id="head_email"
																			class="form-control" placeholder="abc@example.com"
																			pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
																			value="">
																	</div>
																</div>
															</div>
														
													</div>
												</div>
											</div>
<!-- 											</div> -->
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><input
															class="main-btn info-btn btn-hover btnsave" type="button" id="save_head_inst"
															value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								

								<div class="tab" id="important_information_of_connectivity">
									<button class="tab-toggle">Important Information of
										Connectivity</button>
								</div>
								<div class="content">
								<h4 class="heading">Important Information of Connectivity</h4>
									<form:form name="info_connectivity" id="info_of_connectivity" 
									action="info_connecti_action?${_csrf.parameterName}=${_csrf.token}" method="post" class="info_of_connectivity"
										modelAttribute="info_conne_CMD" enctype="multipart/form-data">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="accordion accordion-flush2"
													id="accordionFlushExample2">
													<div class="accordion-item accordion-itemflush">
														<h2 class="accordion-header" id="flush-heading-eight">
															<button class="accordion-button accordion-secondary-btn"
																type="button" data-bs-toggle="collapse"
																data-bs-target="#flush-collapse-eight"
																aria-expanded="true"
																aria-controls="flush-collapse-eight">Nearest
																Airport Details</button>
														</h2>
														<div id="flush-collapse-eight"
															class="accordion-collapse collapse show"
															aria-labelledby="flush-heading-eight"
															data-bs-parent="#accordionFlushExample2">
															<div class="accordion-body">
																
																	<div class="row">
																		<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																			<div
																				class="table-wrapper table-responsive custom-table">
																				<table class="table" id="near_air_table">
																					<thead>
																						<tr>
																							<th><h6>Sr No</h6></th>

																							<th><h6>
																									Nearest Airport Name<strong class="mandatory">*</strong>
																								</h6></th>
																							<th><h6>
																									Approx Distance(km)<strong class="mandatory">*</strong>
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
																								<div class="input-style-1">
																									<input type="text" name="nearest_airport"
																										id="nearest_airport" class="form-control"
																										placeholder="Nearest Airport Name">
																								</div>
																								<input type="hidden" id="info_connecti_hidden"
																		name="info_connecti_hidden" value="0"
																		class="form-control ">
																		
																		
																							</td>

																							<td>
																								<div class="input-style-1">
																									<input type="text" name="airport_distance"
																										id="airport_distance" class="form-control"
																										placeholder="Approx Distance" maxlength="10">
																								</div>
																							</td>
																						</tr>
																						<!-- end table row -->
																					</tbody>
																				</table>

																			</div>
																			<!-- end card -->
																		</div>
																	</div>
																
															</div>
														</div>
													</div>
													<div class="accordion-item accordion-itemflush">
														<h2 class="accordion-header" id="flush-heading-Nine">
															<button
																class="accordion-button accordion-secondary-btn collapsed"
																type="button" data-bs-toggle="collapse"
																data-bs-target="#flush-collapse-Nine"
																aria-expanded="false"
																aria-controls="flush-collapse-Nine">Nearest
																Railway Station Details</button>
														</h2>
														<div id="flush-collapse-Nine"
															class="accordion-collapse collapse"
															aria-labelledby="flush-heading-Nine"
															data-bs-parent="#accordionFlushExample2">
															<div class="accordion-body">
																
																	<div class="row">
																		<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																			<div
																				class="table-wrapper table-responsive custom-table">
																				<table class="table" id="near_rail_table">
																					<thead>
																						<tr>
																							<th><h6>Sr No</h6></th>

																							<th><h6>
																									Nearest Railway Station Name<strong
																										class="mandatory">*</strong>
																								</h6></th>
																							<th><h6>
																									Approx Distance(km)<strong class="mandatory">*</strong>
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
																								<div class="input-style-1">
																									<input type="text" name="nearest_railway"
																										id="nearest_railway" class="form-control"
																										placeholder="Nearest Railway Station Name" maxlength="100">
																								</div>
																							</td>

																							<td>
																								<div class="input-style-1">
																									<input type="text" name="railway_distance"
																										id="railway_distance" class="form-control"
																										placeholder="Approx Distance" maxlength="10">
																								</div>
																							</td>
																						</tr>
																						<!-- end table row -->
																					</tbody>
																				</table>
																			</div>
																			<!-- end card -->
																		</div>
																	</div>
																
															</div>
														</div>
													</div>
													<div class="accordion-item accordion-itemflush">
														<h2 class="accordion-header" id="flush-headingTen">
															<button
																class="accordion-button accordion-secondary-btn collapsed"
																type="button" data-bs-toggle="collapse"
																data-bs-target="#flush-collapseTen"
																aria-expanded="false" aria-controls="flush-collapseTen">Other
																Relevant Institution Detail within the radius of 50km</button>
														</h2>
														<div id="flush-collapseTen"
															class="accordion-collapse collapse"
															aria-labelledby="flush-headingTen"
															data-bs-parent="#accordionFlushExample2">
															<div class="accordion-body">
																
																	<div class="row">
																		<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																			<div
																				class="table-wrapper table-responsive custom-table">
																				<table class="table" id="other_inst_table">
																					<thead>
																						<tr>
																							<th><h6>Sr No</h6></th>

																							<th><h6>
																									Nearest Institutions Name<strong
																										class="mandatory">*</strong>
																								</h6></th>
																								<th><h6>
																									State<strong
																										class="mandatory">*</strong>
																								</h6></th>
																								<th><h6>
																									District<strong
																										class="mandatory">*</strong>
																								</h6></th>
																							<th><h6>
																									City<strong class="mandatory">*</strong>
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
																								<div class="input-style-1">
																									<input type="text" name="nearest_institutions"
																										id="nearest_institutions" class="form-control"
																										placeholder="Institutions Name"
																										maxlength="100">
																								</div>
																							</td>
																							
																							
																		<td>					
																	<div class="select-style-1">
																			
																	<select class="form-control" name="nr_inst_state_ic"
																		id="nr_inst_state_ic">
																	<!-- style="text-transform: uppercase" -->
																	<option value="0" selected="selected">--Select
																		State --</option>
																	<c:forEach var="item" items="${getMedStateName}"
																		varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>
																</select>
															
																	</div>
																</td>
																
																
																
																<td>					
																	<div class="select-style-1">
																			
																	<select class="form-control" name="nr_inst_district_ic"
																		id="nr_inst_district_ic">
																	<!-- style="text-transform: uppercase" -->
																	<option value="0" selected="selected">--Select
																		District --</option>
																	
																</select>
															
																	</div>
																</td>
																
																
																							
																							
																							
																							
																							
																							
																							<td>
																								<div class="input-style-1">
																									<input type="text" name="inst_city"
																										id="inst_city" class="form-control"
																										placeholder="City">
																								</div>
																							</td>
																						</tr>
																						<!-- end table row -->
																					</tbody>
																				</table>
																			</div>
																			<!-- end card -->
																		</div>
																	</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<!-- end col -->
										</div>
										<!-- end row -->
												
									<div class="footer_btn">
								
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover btnsave" id="save_info_connect"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</div>
								</form:form>
								</div>
								<div class="tab" id="police_station_dtl">
									<button class="tab-toggle">Police Station Details</button>
								</div>
								<div class="content ">
									<h4 class="heading">Police Station Details</h4>
									<form:form name="" id="police_st_dtl" action="police_st_action?${_csrf.parameterName}=${_csrf.token}" method="post" 
										modelAttribute="police_st_CMD" enctype="multipart/form-data">
										<div class="row">
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Name of Nearest Police Station<strong
														class="mandatory">*</strong></label> <input type="text"
														name="nearest_police" id="nearest_police"
														class="form-control"
														placeholder="Name of Nearest Police Station">
												</div>
											</div>
											<input type="hidden" id="police_station_hidden"
															name="police_station_hidden" value="0" class="form-control ">

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Address Line 1 <strong class="mandatory">*</strong></label>
													<input type="text" name="police_per_add_line1"
														id="police_per_add_line1" class="form-control"
														placeholder="Address Line 1">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Address Line 2<strong class="mandatory">*</strong></label> <input type="text"
														name="police_per_add_line2" id="police_per_add_line2"
														class="form-control" placeholder="Address Line 2">
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="select-style-1">
													<label>State <strong class="mandatory">* </strong></label>
														<div class="select-position">
																	<select class="form-control" name="police_per_state" id="police_per_state">
																	<!-- style="text-transform: uppercase" -->
																	<option value="0" selected="selected">--Select
																		State --</option>
																	<c:forEach var="item" items="${getMedStateName}"
																		varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>
																</select>
															</div>
												</div>
											</div>
											
											
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="select-style-1">
													<label>District <strong class="mandatory">* </strong></label>
														<div class="select-position">
																	<select class="form-control" name="police_per_district" id="police_per_district">
																	<!-- style="text-transform: uppercase" -->
																	<option value="0" selected="selected">--Select
																		 --</option>
																</select>
															</div>
												</div>
											</div>
											
											
											

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>City<strong class="mandatory">*</strong></label> <input
														type="text" name="police_city" id="police_city"
														class="form-control" placeholder="Enter City"
														maxlength="100">
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1 ">
													<label>Pincode <strong class="mandatory">*</strong></label>
													<input type="text" name="police_per_pincode"
														id="police_per_pincode" class="form-control"
														placeholder="Pincode" maxlength="6" minlength="6">
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<label class="buttonmerge">Landline Number<strong
													class="mandatory">*</strong></label>
												<div class="groupadd">
													<div class="input-style-1 form-group qualificationdiv">
														<input type="text" id="police_std" name="police_std"
															class="form-control" autocomplete="off" placeholder="STD" maxlength="6">
													</div>

													<div class="input-style-1 form-group buttonmergeinput">
														<input type="text" id="police_phone_no"
															name="police_phone_no" class="form-control"
															autocomplete="off" placeholder="Landline Number" maxlength="10">
													</div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<label class="buttonmerge">S.P. Office Number/Landline Number<strong
													class="mandatory">*</strong></label>
												<div class="groupadd">
													<div class="input-style-1 form-group qualificationdiv">
														<input type="text" id="sp_office_std" name="sp_office_std"
															class="form-control" autocomplete="off" placeholder="STD" maxlength="6">
													</div>

													<div class="input-style-1 form-group buttonmergeinput">
														<input type="text" id="sp_office_phone_no"
															name="sp_office_phone_no" class="form-control"
															autocomplete="off" placeholder="Landline Number" maxlength="10">
													</div>
												</div>
											</div>
											
											

										</div>

										<div class="footer_btn">
											<div class="row">

												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><input
															class="main-btn info-btn btn-hover btnsave" type="button" id="save_police"
															value="Save"></li>
													</ul>
												</div>
											</div>

										</div>
									</form:form>
								</div>

								<div class="tab" id="intake_capacity">
									<button class="tab-toggle">Intake Capacity</button>
								</div>
								<div class="content ">
									<h4 class="heading">Last 10 years permission with Intake
										Capacity for UG as per order</h4>
									<form:form name="intake_capa" id="intake_capa" action="intake_action?${_csrf.parameterName}=${_csrf.token}" method="post" 
										modelAttribute="intake_cap_CMD" enctype="multipart/form-data">
										
										
										<div class="row">

											<div class="accordion accordion-flush2"
												id="accordionFlushExample3">
												<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-heading-fourteen">
														<button class="accordion-button accordion-secondary-btn"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapse-fourteen"
															aria-expanded="true" aria-controls="flush-collapse-fourteen">UG</button>
													</h2>
													<div id="flush-collapse-fourteen"
														class="accordion-collapse collapse show"
														aria-labelledby="flush-heading-fourteen"
														data-bs-parent="#accordionFlushExample3">
														<div class="accordion-body">
										
										

										<div class="row">
										<input type="hidden" id="hid_intake_assistants" name="hid_intake_assistants" value="0">
										<input type="hidden" id="indno_intake" name="indno_intake" value="0">
										
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="intakecap_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>

																<th><h6>Year<strong class="mandatory">*</strong></h6></th>
																<th><h6>Permission<strong class="mandatory">*</strong></h6></th>
																<th><h6>Intake Capacity<strong class="mandatory">*</strong></h6></th>
																<th><h6>Admitted Seat<strong class="mandatory">*</strong></h6></th>
																<th><h6>Permitted Seat<strong class="mandatory">*</strong></h6></th>
																<th><h6>Sanctioned Seat<strong class="mandatory">*</strong></h6></th>
																<th><h6>Total Students admitted with Govt Quota
																<strong class="mandatory">*</strong></h6></th>
																<th><h6>Total Students admitted with Management Quota
																<strong class="mandatory">*</strong></h6></th>
																<th><h6>Students admitted by Court order
																<strong class="mandatory">*</strong></h6></th>
																<th><h6>Name of the Last Student Admitted
																<strong class="mandatory">*</strong></h6></th>
																<th><h6>Date of Admission of the last Student
																<strong class="mandatory">*</strong></h6></th>
																<th><h6>Total Final Year students appeared for exams
																<strong class="mandatory">*</strong></h6></th>
																<th><h6>Total Final Year students passed out in exams
																<strong class="mandatory">*</strong></h6></th>
																<th><h6>Action</h6></th>
															</tr>
															<!-- end table row-->
														</thead>
														<tbody >
															<tr id="tr_id_intake_cap">
																<td class="sr-no">
																	<p></p>
																</td>

																<td>
																<div class="select-style-1">
																	<div class="select-position">
																		<select class="form-control selectCustom" name="year1" id="year1">
																		<option value="0" selected="selected">--Select Year --</option>												
																		<c:forEach var="item" items="${year}" varStatus="num">
																		<option value="${item[0]}" name="${item[0]}">${item[0]}</option>
																		</c:forEach>
																		</select>
																	</div>
																</div>
																</td>

																<td>
																<div class="select-style-1">
																	<div class="select-position">
																		<select name="per_capacity1" id="per_capacity1" class="form-control">
																			<option value="0" selected="selected">--Select --</option>
																			<option value="1">Yes</option>
																			<option value="2">No</option>
																			</select>
																	</div>
																</div>
																</td>

																<td>
																<div class="input-style-1">
																	<input type="text" name="intake_capacity1" id="intake_capacity1" 
																			class="form-control" placeholder="Intake Capacity" maxlength="6">
																</div>
																</td>
																
																<td>
																<div class="input-style-1">
																	<input type="text" name="amitted_seat1" id="amitted_seat1" 
																			class="form-control" placeholder="Admitted Seat" maxlength="6">
																</div>
																</td>
																
																<td>
																<div class="input-style-1">
																	<input type="text" name="permitted_seat1" id="permitted_seat1" 
																			class="form-control" placeholder="Permitted Seat" maxlength="6">
																</div>
																</td>
																
																<td>
																<div class="input-style-1">
																	<input type="text" name="sanctioned_seat1" id="sanctioned_seat1" 
																			class="form-control" placeholder="Sanctioned Seat" maxlength="6">
																</div>
																</td>
																
																<td>
																<div class="input-style-1">
																	<input type="text" name="govt_quota_ug1" id="govt_quota_ug1" 
																			class="form-control" placeholder="Total Students" maxlength="6">
																</div>
																</td>
																
																<td>
																<div class="input-style-1">
																	<input type="text" name="mang_quota_ug1" id="mang_quota_ug1" 
																			class="form-control" placeholder="Total Students" maxlength="6">
																</div>
																</td>
																
																<td>
																<div class="select-style-1">
																	<div class="select-position">
																		<select name="court_order1" id="court_order1" class="form-control">
																			<option value="0" selected="selected">--Select --</option>
																			<option value="1">Yes</option>
																			<option value="2">No</option>
																			</select>
																	</div>
																</div>
																</td>
																
																<td>
																<div class="input-style-1">
																	<input type="text" name="last_student1" id="last_student1" 
																			class="form-control" placeholder="Name of the Last Student Admitted" maxlength="100">
																</div>
																</td>
																
																<td>
																	<div class="input-style-2"><input type="text" name="last_stu_add_date1" id="last_stu_add_date1" 
																	class="form-control-sm form-control effect-9" aria-required="true" autocomplete="off"
																		placeholder="DD/MM/YYYY"></div>
																</td>
																
																<td>
																<div class="input-style-1">
																	<input type="text" name="appeared_stu_ug1" id="appeared_stu_ug1" 
																			class="form-control" placeholder="Total Students" maxlength="6">
																</div>
																</td>
																
																<td>
																<div class="input-style-1">
																	<input type="text" name="passed_stu_ug1" id="passed_stu_ug1" 
																			class="form-control" placeholder="Total Students" maxlength="6">
																</div>
																</td>
																
																<td class="addminusbut">
																	<ul class="buttons-group mainbtn daobtn action">
																		<li><a
																			class="main-btn success-btn btn-hover btn-sm btnapprove"
																			value="Save" title="SAVE" id="save_int_cap1">
																				<i class="lni lni-checkmark"></i>
																				</a></li>
																		<li><a
																			class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none"
																			value="ADD" title="ADD" id="intake_add1">
																			<i class="lni lni-plus"></i>
																			</a></li>
																			<li><a
																			class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"
																			value="Delete" title="Delete" id="intake_remove1"><i
																				class="lni lni-trash-can"></i></a></li>
																	</ul>
																</td>
															</tr>
															<!-- end table row -->
														</tbody>
													</table>
													
											<input type="hidden" id="save_int_caph1" name="save_int_caph1" class="form-control autocomplete" value="1">
											<input type="hidden" id="hid_intake1" name="hid_intake1" value="0" class="form-control autocomplete" autocomplete="off">

												</div>
												<!-- end card -->
											</div>
											<!-- end col -->
											
											
											
										</div>
										
										
										
									</div>
								</div>		
							</div>
							
							
							<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-heading-fifteen">
														<button
															class="accordion-button accordion-secondary-btn collapsed"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapse-fifteen"
															aria-expanded="false" aria-controls="flush-collapse-fifteen">PG</button>
													</h2>
													<div id="flush-collapse-fifteen"
														class="accordion-collapse collapse"
														aria-labelledby="flush-heading-fifteen"
														data-bs-parent="#accordionFlushExample3">
														<div class="accordion-body">
														
														
											<div class="row">
										<input type="hidden" id="hid_intake_assistants_pg" name="hid_intake_assistants_pg" value="0">
										<input type="hidden" id="indno_intake_pg" name="indno_intake_pg" value="0">
										
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="intakecap_table_pg">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>

																<th><h6>
																		Year<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Permission<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Intake Capacity<strong class="mandatory">*</strong>
																	</h6></th>
																	<th><h6>
																		Admitted Seat<strong class="mandatory">*</strong>
																	</h6></th>
																	<th><h6>
																		Permited Seat<strong class="mandatory">*</strong>
																	</h6></th>
																	<th><h6>
																		Sanction Seat<strong class="mandatory">*</strong>
																	</h6></th>
																	<th><h6>
																		Total Student witg Govt Quota<strong class="mandatory">*</strong>
																	</h6></th>
																	<th><h6>
																		Total Student witg Management Quota<strong class="mandatory">*</strong>
																	</h6></th>
																	<th><h6>
																		 Student Admitted by Court Order<strong class="mandatory">*</strong>
																	</h6></th>
																	<th><h6>
																		 Name of Last Student Admitted<strong class="mandatory">*</strong>
																	</h6></th>
																	<th><h6>
																		 Date of Admission of Last Student<strong class="mandatory">*</strong>
																	</h6></th>
																	<th><h6>
																		Total Final Year students appeared for exams<strong class="mandatory">*</strong>
																	</h6></th>
																	<th><h6>
																		Total Final Year students passed out in exams<strong class="mandatory">*</strong>
																	</h6></th>
																	
																<th><h6>Action</h6></th>
															</tr>
															<!-- end table row-->
														</thead>
														<tbody >
															<tr id="tr_id_intake_cap_pg">
																<td class="sr-no">
																						<p></p>
																					</td>
																<td>


												<div class="select-style-1">
											
											<div class="select-position">
											<select class="form-control selectCustompg" name="year_pg1" id="year_pg1">
													<!-- style="text-transform: uppercase" -->
													<option value="0" selected="selected">--Select
														Year --</option>												
											<c:forEach var="item" items="${year}"
													varStatus="num">
													<option value="${item[0]}" name="${item[0]}">${item[0]}</option>
												</c:forEach>
											</select>
												
											</div>
										</div>

																</td>

																<td>
																	<div class="select-style-1">

																		<div class="select-position">
																			<select name="per_capacity_pg1" id="per_capacity_pg1"
																				class="form-control">
																				<option value="0" selected="selected">--
																					Select --</option>
																				<option value="1">Yes</option>
																				<option value="2">No</option>

																			</select>
																		</div>
																	</div>

																</td>

																<td>
																	<div class="input-style-1">
																		<input type="text" name="intake_capacity_pg1"
																			id="intake_capacity_pg1" class="form-control"
																			placeholder="Intake Capacity" maxlength="6">
																	</div>

																</td>
																
																<td>
																	<div class="input-style-1">
																		<input type="text" name="admited_seat_pg1"
																			id="admited_seat_pg1" class="form-control"
																			placeholder="Admitted Seat" maxlength="6">
																	</div>

																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="permitted_seat_pg1"
																			id="permitted_seat_pg1" class="form-control"
																			placeholder="Permitted Seat" maxlength="6">
																	</div>

																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="sanction_seat_pg1"
																			id="sanction_seat_pg1" class="form-control"
																			placeholder="Sanction Seat" maxlength="6">
																	</div>

																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="stu_govt_quota1"
																			id="stu_govt_quota1" class="form-control"
																			placeholder="Total Student" maxlength="6">
																	</div>

																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="stu_mgmt_quota1"
																			id="stu_mgmt_quota1" class="form-control"
																			placeholder="Total Student" maxlength="6">
																	</div>

																</td>
																<td>
																	<div class="select-style-1">

																		<div class="select-position">
																			<select name="stu_court_order1" id="stu_court_order1"
																				class="form-control">
																				<option value="0" selected="selected">--
																					Select --</option>
																				<option value="1">Yes</option>
																				<option value="2">No</option>

																			</select>
																		</div>
																	</div>

																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="last_stu_admitted1"
																			id="last_stu_admitted1" class="form-control"
																			placeholder="Last Student" maxlength="6">
																	</div>

																</td>
																<td>
																<div class="input-style-1">
																		 <input type="text" name="last_stu_date_admitted1" id="last_stu_date_admitted1" 
																			class="form-control-sm form-control effect-9 "
																			aria-required="true" autocomplete="off"
																			value="DD/MM/YYYY">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="final_stu_app_exam1"
																			id="final_stu_app_exam1" class="form-control"
																			placeholder="Final Student" maxlength="6">
																	</div>

																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="final_stu_pass_exam1"
																			id="final_stu_pass_exam1" class="form-control"
																			placeholder="Final Student Pass" maxlength="6">
																	</div>

																</td>
																<td class="addminusbut">
																	<ul class="buttons-group mainbtn daobtn action">
																		<li><a
																			class="main-btn success-btn btn-hover btn-sm btnapprove"
																			value="Save" title="SAVE" id="save_int_cap_pg1">
																				<i class="lni lni-checkmark"></i>
																				</a></li>
																		<li><a
																			class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none"
																			value="ADD" title="ADD" id="intake_add_pg1">
																			<i class="lni lni-plus"></i>
																			</a></li>
																			<li><a
																			class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"
																			value="Delete" title="Delete" id="intake_remove_pg1"><i
																				class="lni lni-trash-can"></i></a></li>
																	</ul>
																</td>
															</tr>
															<!-- end table row -->
														</tbody>
													</table>
													
											<input type="hidden" id="save_int_caph_pg1" name="save_int_caph_pg1" class="form-control autocomplete" value="1">
											<input type="hidden" id="hid_intake_pg1" name="hid_intake_pg1" value="0" class="form-control autocomplete" autocomplete="off">

												</div>
												<!-- end card -->
											</div>
											<!-- end col -->
											
											
											
										</div>			
														
							</div>
						</div>
					</div>					
						</div>				
					</div>					
										
										
										
										
										
										<div class="footer_btn">
											<div class="row">

												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
<!-- 														<li><input -->
<!-- 															class="main-btn info-btn btn-hover btnsave" type="button"  -->
<!-- 															value="Save"></li> -->
													</ul>
												</div>
											</div>
										</div>
										<!-- end row -->

									</form:form>
								</div>
								
								
								
								
								
								

								<div class="tab" id="course_intake_capacity">
									<button class="tab-toggle">Course Intake Capacity</button>
								</div>
								<div class="content">
									<h4 class="heading">Course intake capacity, irrespective
										of previous year permission</h4>
									<form:form name="intake_course_cap" id="intake_course_cap" action="intake_course_action?${_csrf.parameterName}=${_csrf.token}" method="post" 
										modelAttribute="intake_course_cap_CMD" enctype="multipart/form-data">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<div class="inst_block simple-instruction">
													<strong>Instruction :</strong> For Newly Proposed
													Institutions, Please Fill The Intended Intake Capacity
												</div>

											</div>
										</div>
										<div class="row">

											<div class="accordion accordion-flush"
												id="accordionFlushExample33">
												<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-heading-twelve">
														<button class="accordion-button accordion-secondary-btn"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapse-twelve"
															aria-expanded="true" aria-controls="flush-collapse-twelve">UG</button>
													</h2>
													<div id="flush-collapse-twelve"
														class="accordion-collapse collapse show"
														aria-labelledby="flush-heading-twelve"
														data-bs-parent="#accordionFlushExample33">
														<div class="accordion-body">
															
																<div class="row">

																	<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																		<div
																			class="table-wrapper table-responsive custom-table">
																			<table class="table" id="ug_info_table">
																				<thead>
																					<tr>
																						<th><h6>Sr No</h6></th>

																						<th><h6>
																								Course (Subject wise)<strong class="mandatory">*</strong>
																							</h6></th>
																						<th><h6>
																								Intake Capacity<strong class="mandatory">*</strong>
																							</h6></th>

																					</tr>
																					<!-- end table row-->
																				</thead>
																				<tbody id="">
																				${dataforUG}
<!-- 																					<tr> -->
<!-- 																						<td class="sr-no"> -->
<!-- 																						<p></p> -->
<!-- 																					</td> -->
<!-- 																						<td> -->
<!-- 																							<p>UG - Homeopathy</p> -->
<!-- 																						</td> -->

<!-- 																						<td> -->
<!-- 																							<div class="input-style-1"> -->
<!-- 																								<input type="text" name="intake_capacity" -->
<!-- 																									id="intake_capacity" class="form-control" -->
<!-- 																									placeholder="Intake Capacity" maxlength="6"> -->
<!-- 																							</div> -->
<!-- 																						</td> -->
<!-- 																					</tr> -->
																					<!-- end table row -->
																				</tbody>
																			</table>
																		</div>
																		<!-- end card -->
																	</div>
																</div>
															
														</div>
													</div>
												</div>
												<div class="accordion-item accordion-itemflush">
													<h2 class="accordion-header" id="flush-heading-thirteen">
														<button
															class="accordion-button accordion-secondary-btn collapsed"
															type="button" data-bs-toggle="collapse"
															data-bs-target="#flush-collapse-thirteen"
															aria-expanded="false" aria-controls="flush-collapse-thirteen">PG</button>
													</h2>
													<div id="flush-collapse-thirteen"
														class="accordion-collapse collapse"
														aria-labelledby="flush-heading-thirteen"
														data-bs-parent="#accordionFlushExample33">
														<div class="accordion-body">
															
																<div class="row">

																	<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																		<div
																			class="table-wrapper table-responsive custom-table">
																			<table class="table" id="pg_info_table">
																				<thead>
																					<tr>
																						<th><h6>Sr No</h6></th>

																						<th><h6>
																								Course (Subject wise)<strong class="mandatory">*</strong>
																							</h6></th>
																						<th><h6>
																								Intake Capacity<strong class="mandatory">*</strong>
																							</h6></th>

																					</tr>
																					<!-- end table row-->
																				</thead>
																				<tbody id="">
																				${dataforPG}
<!-- 																					<tr> -->
<!-- 																						<td class="sr-no"> -->
<!-- 																							<p></p> -->
<!-- 																						</td> -->
<!-- 																						<td> -->
<!-- 																							<p>M.D. (Hom.) Homoeopathic Philosophy</p> -->
<!-- 																						</td> -->

<!-- 																						<td> -->
<!-- 																							<div class="input-style-1"> -->
<!-- 																								<input type="text" name="intake_capacity" -->
<!-- 																									id="intake_capacity" class="form-control" -->
<!-- 																									placeholder="Intake Capacity" maxlength="6"> -->
<!-- 																							</div> -->

<!-- 																						</td> -->
<!-- 																					</tr> -->

<!-- 																					<tr> -->
<!-- 																						<td class="sr-no"> -->
<!-- 																							<p></p> -->
<!-- 																						</td> -->
<!-- 																						<td> -->
<!-- 																							<p>M.D. (Hom.) Homoeopathic Materia Medica</p> -->
<!-- 																						</td> -->
<!-- 																						<td> -->
<!-- 																							<div class="input-style-1"> -->
<!-- 																								<input type="text" name="intake_capacity" -->
<!-- 																									id="intake_capacity" class="form-control" -->
<!-- 																									placeholder="Intake Capacity" maxlength="6"> -->
<!-- 																							</div> -->

<!-- 																						</td> -->
<!-- 																					</tr> -->
<!-- 																					<tr> -->
<!-- 																						<td class="sr-no"> -->
<!-- 																							<p></p> -->
<!-- 																						</td> -->
<!-- 																						<td> -->
<!-- 																							<p>M.D. (Hom.) Repertory</p> -->
<!-- 																						</td> -->

<!-- 																						<td> -->
<!-- 																							<div class="input-style-1"> -->
<!-- 																								<input type="text" name="intake_capacity" -->
<!-- 																									id="intake_capacity" class="form-control" -->
<!-- 																									placeholder="Intake Capacity" maxlength="6"> -->
<!-- 																							</div> -->

<!-- 																						</td> -->
<!-- 																					</tr> -->
<!-- 																					<tr> -->
<!-- 																						<td class="sr-no"> -->
<!-- 																							<p></p> -->
<!-- 																						</td> -->
<!-- 																						<td> -->
<!-- 																							<p>M.D. (Hom.) Homoeopathic Pharmacy</p> -->
<!-- 																						</td> -->
<!-- 																						<td> -->
<!-- 																							<div class="input-style-1"> -->
<!-- 																								<input type="text" name="intake_capacity" -->
<!-- 																									id="intake_capacity" class="form-control" -->
<!-- 																									placeholder="Intake Capacity" maxlength="6"> -->
<!-- 																							</div> -->

<!-- 																						</td> -->
<!-- 																					</tr> -->
<!-- 																					<tr> -->
<!-- 																						<td class="sr-no"> -->
<!-- 																							<p></p> -->
<!-- 																						</td> -->
<!-- 																						<td> -->
<!-- 																							<p>M.D. (Hom.) Practice of Medicine</p> -->
<!-- 																						</td> -->
<!-- 																						<td> -->
<!-- 																							<div class="input-style-1"> -->
<!-- 																								<input type="text" name="intake_capacity" -->
<!-- 																									id="intake_capacity" class="form-control" -->
<!-- 																									placeholder="Intake Capacity" maxlength="6"> -->
<!-- 																							</div> -->

<!-- 																						</td> -->
<!-- 																					</tr> -->
<!-- 																					<tr> -->
<!-- 																						<td class="sr-no"> -->
<!-- 																							<p></p> -->
<!-- 																						</td> -->
<!-- 																						<td> -->
<!-- 																							<p>M.D. (Hom.) Pediatrics</p> -->
<!-- 																						</td> -->
<!-- 																						<td> -->
<!-- 																							<div class="input-style-1"> -->
<!-- 																								<input type="text" name="intake_capacity" -->
<!-- 																									id="intake_capacity" class="form-control" -->
<!-- 																									placeholder="Intake Capacity" maxlength="6"> -->
<!-- 																							</div> -->

<!-- 																						</td> -->
<!-- 																					</tr> -->
<!-- 																					<tr> -->
<!-- 																						<td class="sr-no"> -->
<!-- 																							<p></p> -->
<!-- 																						</td> -->
<!-- 																						<td> -->
<!-- 																							<p>M.D. (Hom.) Psychiatry</p> -->
<!-- 																						</td> -->
<!-- 																						<td> -->
<!-- 																							<div class="input-style-1"> -->
<!-- 																								<input type="text" name="intake_capacity" -->
<!-- 																									id="intake_capacity" class="form-control" -->
<!-- 																									placeholder="Intake Capacity" maxlength="6"> -->
<!-- 																							</div> -->

<!-- 																						</td> -->
<!-- 																					</tr> -->
																					<!-- end table row -->
																				</tbody>
																			</table>
																		</div>
																		<!-- end card -->
																	</div>
																	
																	
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Kindly Upload last 10 year AYUSH
														Permission/Denial letter for UG and/or PG course (Please
														zip and upload.)<strong class="mandatory">*</strong>
													</label> <input type="file" id="denial_doc" name="denial_doc"
														class="form-control" accept=".pdf">
											<input type="hidden" id="hid_denial_doc" name="hid_denial_doc" class="form-control" value="">	
											
											<input type="hidden" id="hid_for_doc" name="hid_for_doc" class="form-control" value="0">	
											<div class="note-text">
																	<span class="errorClass" id="denial_doc_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="denial_doc_lbltik"></span>
															</div>			
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>If admitted through court, Upload court
														order for last 10 year (Please zip and upload.)</label> <input
														type="file" id="courtorder_doc" name="courtorder_doc"
														class="form-control" accept=".pdf">
										<input type="hidden" id="hid_courtorder_doc" name="hid_courtorder_doc" class="form-control" value="">
										<div class="note-text">
																	<span class="errorClass" id="courtorder_doc_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="courtorder_doc_lbltik"></span>
															</div>				
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Kindly Upload the undertaking that
														Institution will not admit the students without permission
														from Government of India<strong class="mandatory">*</strong>
													</label> <input type="file" id="per_gov_doc" name="per_gov_doc"
														class="form-control" accept=".pdf">
											<input type="hidden" id="hid_per_gov_doc" name="hid_per_gov_doc" class="form-control" value="">		
															<div class="note-text">
																	<span class="errorClass" id="per_gov_doc_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="per_gov_doc_lbltik"></span>
															</div>	
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Kindly Upload the undertaking that selection
														of students for UG and PG Courses will be made only on
														academic merit as per CCH/NCH regulation<strong
														class="mandatory">*</strong>
													</label> <input type="file" id="cchreg_doc" name="cchreg_doc"
														class="form-control" accept=".pdf">
											<input type="hidden" id="hid_cchreg_doc" name="hid_cchreg_doc" class="form-control" value="">
															<div class="note-text">
																	<span class="errorClass" id="cchreg_doc_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="cchreg_doc_lbltik"></span>
															</div>				
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label >Upload the list of PG students
														allotted to individual guide in the last 10 academic years
														<strong class="mandatory">*</strong>
													</label> <input type="file" id="lastfiveguide" name="lastfiveguide"
														class="form-control" accept=".pdf">
														<input type="hidden" id="hid_lastfiveguide" name="hid_lastfiveguide"
															class="form-control" value="">
													<div class="note-text">
														<span class="errorClass" id="lastfiveguide_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="lastfiveguide_lbltik"></span>
													</div>
												</div>
												
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label >Upload Name, Mobile No. and
														Email Id of all UG/PG admitted students with their
														NEET/AIAPGET score/admission criteria in the last 10
														academic years<strong class="mandatory">*</strong>
													</label> <input type="file" id="neet_score" name="neet_score"
														class="form-control" accept=".pdf">
														<input type="hidden" id="hid_neet_score" name="hid_neet_score"
															class="form-control" value="">
													<div class="note-text">
														<span class="errorClass" id="neet_score_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="neet_score_lbltik"></span>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label >Upload the undertaking of the
														students (About the provisional nature of admission)<strong
														class="mandatory">*</strong>
													</label> <input type="file" id="undertakingofstudent"
														name="undertakingofstudent" class="form-control" accept=".pdf">
														<input type="hidden" id="hid_undertakingofstudent" name="hid_undertakingofstudent"
															class="form-control" value="">
													<div class="note-text">
														<span class="errorClass" id="undertakingofstudent_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="undertakingofstudent_lbltik"></span>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label >Upload PG students attendance
														register/Biometric attendance<strong class="mandatory">*</strong>
													</label> <input type="file" id="biometricattendance"
														name="biometricattendance" class="form-control" accept=".pdf">
														<input type="hidden" id="hid_biometricattendance" name="hid_biometricattendance"
															class="form-control" value="">
													<div class="note-text">
														<span class="errorClass" id="biometricattendance_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="biometricattendance_lbltik"></span>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<label >Whether college has intimated
															the students regarding the admission under court order</label>
															
													<input type="hidden" name="hid_admitted_details_check" id="hid_admitted_details_check" value="0">
															
													<div class="input-style-form-check">
														
														<div class="form-check radio-style">
															<input type="radio" id="intimatedYes" name="intimatedcheck"
																class="form-check-input" value="1"> <label
																for="intimatedYes" class="form-check-label">Yes</label>
														</div>
														<div class="form-check radio-style">
															<input type="radio" id="intimatedNo" name="intimatedcheck"
																class="form-check-input" value="2"> <label
																for="intimatedNo" class="form-check-label">No</label>
														</div>
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_undertakingcheck">
												<label >Undertaking has been taken from
															students (If admitted though court order)</label>
													<div class="input-style-form-check">
														
														<div class="form-check radio-style">
															<input type="radio" id="undertakingYes" name="undertakingcheck"
																class="form-check-input" value="1"> <label
																for="undertakingYes" class="form-check-label">Yes</label>
														</div>
														<div class="form-check radio-style">
															<input type="radio" id="undertakingNo" name="undertakingcheck"
																class="form-check-input" value="2"> <label
																for="undertakingNo" class="form-check-label">No</label>
														</div>
													</div>
												</div>
																	
																</div>
															
															
															
															
														</div>
													</div>
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
															class="main-btn info-btn btn-hover btnsave" type="button" id="savedrft_intake_cap"
															value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>

								<div class="tab" id="details_of_land">
									<button class="tab-toggle">Details of Land</button>
								</div>
								<div class="content ">
									<h4 class="heading">Details of Land</h4>
									<form:form name="detail_of_land" id="detail_of_land" action="police_st_action?${_csrf.parameterName}=${_csrf.token}" method="post" 
										modelAttribute="dtl_of_CMD" enctype="multipart/form-data">
										
										<div class="row">
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Total area of land with Society/Trust/ Govt.
														College (in acres)</label> <input type="text" name="area_of_land "
														id="area_of_land" class="form-control"
														placeholder="Total area of land">
												</div>
											</div>
											
											<input type="hidden" id="detail_land_hidden"
															name="detail_land_hidden" value="0" class="form-control ">


											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="select-style-1">
													<label>Ownership of land </label>
													<div class="select-position">
														<select name="own_land" id="own_land" class="form-control">

															<option value="0" selected="selected">-- Select
																--</option>
															<option value="1">Private</option>
															<option value="2">Government</option>

														</select>
													</div>
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="select-style-1">
													<label>Land in the name of </label>
													<div class="select-position">
														<select name="land_name" id="land_name"
															class="form-control">

															<option value="0" selected="selected">-- Select
																--</option>
															<option value="1">Society/Trust</option>
															<option value="2">President/Secretary</option>
															<option value="3">Government</option>

														</select>
													</div>
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="select-style-1">
													<label>Distribution of entire land<strong
														class="mandatory">*</strong></label>
													<div class="select-position">
														<select name="entire_name" id="entire_name"
															class="form-control">

															<option value="0" selected="selected">-- Select
																--</option>
															<option value="1">One Plot</option>
															<option value="2">More than one</option>


														</select>
													</div>
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="select-style-1">
													<label>Land availability with the Society/Trust is
														entirely for concerned College and attached Hospital or
														for no other Institute<strong class="mandatory">*</strong>
													</label>
													<div class="select-position">
														<select name="land_availability" id="land_availability"
															class="form-control">

															<option value="0" selected="selected">-- Select
																--</option>
															<option value="1">Yes</option>
															<option value="2">No</option>
															<option value="3">Not Applicable</option>


														</select>
													</div>
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="select-style-1">
													<label>Does the same society/trust run any other
														institutions/colleges<strong class="mandatory">*</strong>
													</label>
													<div class="select-position">
														<select name="stic" id="stic" class="form-control">

															<option value="0" selected="selected">-- Select
																--</option>
															<option value="1">Yes</option>
															<option value="2">No</option>
															<option value="3">Not Applicable</option>


														</select>
													</div>
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">

												<div class="input-style-form-check">
													<label>Whether College and Hospital are constructed
														in separate buildings<strong class="mandatory">*</strong>
													</label>
													<div class="form-check radio-style">
														<input type="radio" id="constructedYes" name="constructedcheck"
															class="form-check-input" value="1"> <label
															for="constructedYes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="constructedNo" name="constructedcheck"
															class="form-check-input" value="0"> <label
															for="constructedNo" class="form-check-label">No</label>
													</div>

												</div>

											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">


												<div class="input-style-form-check">
													<label>Whether College and Hospital building are in
														same premises<strong class="mandatory">*</strong>
													</label>
													<div class="form-check radio-style">
														<input type="radio" id="buildingYes" name="buildingcheck"
															class="form-check-input" value="1"> <label
															for="buildingYes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="buildingNo" name="buildingcheck"
															class="form-check-input" value="0"> <label
															for="buildingNo" class="form-check-label">No</label>
													</div>

												</div>

											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Total area of land allotted to the college
														(in acres)<strong class="mandatory">*</strong>
													</label> <input type="text" name="clg_land" id="clg_land"
														class="form-control" placeholder="Total area of land">
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Total area of land allotted to the hospital
														(in acres)<strong class="mandatory">*</strong>
													</label> <input type="text" name="hospital_land" id="hospital_land"
														class="form-control" placeholder="Total area of land">
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Total area of land allotted to the hostels
														(in acres)<strong class="mandatory">*</strong>
													</label> <input type="text" name="hostels_land" id="hostels_land"
														class="form-control" placeholder="Total area of land">
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">

												<div class="input-style-form-check">
													<label>Whether the College and Hospital building
														have ever been shifted to some other place<strong
														class="mandatory">*</strong>
													</label>
													<div class="form-check radio-style">
														<input type="radio" id="shiftedYes" name="shiftedcheck"
															class="form-check-input" value="1"> <label
															for="shiftedYes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="shiftedNo" name="shiftedcheck"
															class="form-check-input" value="0"> <label
															for="shiftedNo" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-form-check">
													<label>Whether the management/society of college
														(in case of private College) has ever changed</label>
													<div class="form-check radio-style">
														<input type="radio" id="changedYes" name="changedcheck"
															class="form-check-input" value="1"> <label
															for="changedYes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="changedNo" name="changedcheck"
															class="form-check-input" value="0"> <label
															for="changedNo" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Land documents of the College<strong
														class="mandatory">*</strong></label> <input type="file"
														id="land_doc_clg" name="land_doc_clg" class="form-control" accept="pdf">
											<input type="hidden" id="hid_land_doc_clg" name="hid_land_doc_clg" class="form-control" value="">	
											<div class="note-text">
																	<span class="errorClass" id="land_doc_clg_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="land_doc_clg_lbltik"></span>
															</div>			
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Building plan (approved by the competent
														authority) of the college Document<strong
														class="mandatory">*</strong>
													</label> <input type="file" id="building_plan_doc"
														name="building_plan_doc" class="form-control" accept="pdf">
											<input type="hidden" id="hid_building_plan_doc" name="hid_building_plan_doc" class="form-control" value="">	
											<div class="note-text">
																	<span class="errorClass" id="building_plan_doc_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="building_plan_doc_lbltik"></span>
															</div>			
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Area statement document certified by
														Architect<strong class="mandatory">*</strong>
													</label> <input type="file" id="architect_doc" name="architect_doc"
														class="form-control" accept="pdf">
										<input type="hidden" id="hid_architect_doc" name="hid_architect_doc" class="form-control" value="">	
										<div class="note-text">
																	<span class="errorClass" id="architect_doc_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="architect_doc_lbltik"></span>
															</div>			
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Kindly Upload the Audited Balance Sheet for
														last 3 years<strong class="mandatory">*</strong>
													</label> <input type="file" id="balancesheet_doc"
														name="balancesheet_doc" class="form-control" accept="pdf">
											<input type="hidden" id="hid_balancesheet_doc" name="hid_balancesheet_doc" class="form-control" value="">
											<div class="note-text">
																	<span class="errorClass" id="balancesheet_doc_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="balancesheet_doc_lbltik"></span>
															</div>				
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Kindly Upload the Annual Report</label><input
														type="file" id="annual_doc" name="annual_doc"
														class="form-control" accept="pdf">
											<input type="hidden" id="hid_annual_doc" name="hid_annual_doc" class="form-control" value="">
											<div class="note-text">
																	<span class="errorClass" id="annual_doc_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="annual_doc_lbltik"></span>
															</div>			
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Any additional document</label> <input type="file"
														id="add_doc" name="add_doc" class="form-control" accept="pdf">
												<input type="hidden" id="hid_add_doc" name="hid_add_doc" class="form-control" value="">	
												<div class="note-text">
																	<span class="errorClass" id="add_doc_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="add_doc_lbltik"></span>
															</div>		
												</div>
											</div>
											
											
											
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><input
															class="main-btn info-btn btn-hover btnsave" type="button" id="save_dtl_land"
															value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- Internship & Housejob Details Start -->
								<div class="tab cutom-d-none" id="hide_internship_details_tab">
									<button class="tab-toggle">Internship & Housejob
										Details</button>
								</div>
								<div class="content">
									<h4 class="heading">Internship & Housejob Details</h4>
									<form:form name="internship_details" id="internship_details" action="" method="post" class=""
										commandName="">
										<div class="row">
										
										<input type="hidden" id="hid_internship_details" name="hid_internship_details" value="0">
										
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label >Total number of Interns (Upload
														list Batch-wise)</label> <input type="file" id="total_intern"
														name="total_intern" class="form-control" accept=".pdf">
														<input type="hidden" id="hid_total_intern" name="hid_total_intern"
															class="form-control" value="">
													<div class="note-text">
														<span class="errorClass" id="total_intern_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="total_intern_lbltik"></span>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<label >Whether they have Provisional
														Registration with State Homoeopathic Board/ Council ?<strong
														class="mandatory">*</strong>
													</label>
												<div class="input-style-form-check">
													
													<div class="form-check radio-style">
														<input type="radio" id="pro_reg_Yes" name="pro_regcheck"
															class="form-check-input" value="1"> <label
															for="pro_reg_Yes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="pro_reg_No" name="pro_regcheck"
															class="form-check-input" value="2"> <label
															for="pro_reg_No" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<label >Rotation Programme ?<strong
														class="mandatory">*</strong></label>
												<div class="input-style-form-check">
													
													<div class="form-check radio-style">
														<input type="radio" id="rotation_Yes" name="rotationcheck"
															class="form-check-input" value="1"> <label
															for="rotation_Yes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="rotation_No" name="rotationcheck"
															class="form-check-input" value="2"> <label
															for="rotation_No" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											<!-- <div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Total interns in O.P.D<strong
														class="mandatory">*</strong></label> <input type="text"
														id="internsopd" name="internsopd" class="form-control"
														placeholder="Total interns in O.P.D">
												</div>
											</div> -->
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Total interns in O.P.D<strong
														class="mandatory">*</strong></label> <input type="text"
														id="internsdutyopd" name="internsdutyopd" class="form-control"
														placeholder="Total interns in O.P.D" maxlength="10">
												</div>
											</div>
											<!-- <div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
													<label>Total interns in IPD<strong
														class="mandatory">*</strong></label> <input type="text"
														id="internsipd" name="internsipd" class="form-control"
														placeholder="Total interns in IPD">
												</div>
											</div> -->
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Total interns in IPD<strong
														class="mandatory">*</strong></label> <input type="text"
														id="internsdutyipd" name="internsdutyipd"
														class="form-control" placeholder="Total interns in IPD" maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<label >Any migration of intern from/to
														College.<strong class="mandatory">*</strong>
													</label>
												<div class="input-style-form-check">
													
													<div class="form-check radio-style">
														<input type="radio" id="migration_Yes" name="migrationcheck"
															class="form-check-input" value="1"> <label
															for="migration_Yes" class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="migration_No" name="migrationcheck"
															class="form-check-input" value="2"> <label
															for="migration_No" class="form-check-label">No</label>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12 custom-d-none" id="hid_migrationcheck">
												<div class="input-style-1">
													<label >Migration List</label> <input
														type="file" id="migration_list" name="migration_list"
														class="form-control" accept=".pdf">
														<input type="hidden" id="hid_migration_list" name="hid_migration_list"
															class="form-control" value="">
													<div class="note-text">
														<span class="errorClass" id="migration_list_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="migration_list_lbltik"></span>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Whether intern students prescribe medicine<strong
														class="mandatory">*</strong></label> <input type="text"
														id="prescribe" name="prescribe" class="form-control"
														placeholder="Prescribe Medicine" maxlength="100">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Seminar for Internee<strong
														class="mandatory">*</strong></label> <input type="text"
														id="seminar" name="seminar" class="form-control"
														placeholder="Seminar for Internee" maxlength="100">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Number of students who have not completed
														internship for more than two years of passing final BHMS
														examination.<strong class="mandatory">*</strong>
													</label> <input type="text" id="internship_not_completed" name="internship_not_completed"
														class="form-control" placeholder="Number of students" maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>House Job<strong class="mandatory">*</strong></label>
													<input type="text" id="house_job" name="house_job"
														class="form-control" placeholder="House Job" maxlength="100">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Number of Students undergoing House Job.<strong
														class="mandatory">*</strong></label> <input type="text"
														id="no_house_job" name="no_house_job" class="form-control"
														placeholder="Number of Students undergoing House Job" maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Graded teaching work performed by P.G.
														Students.(described in short)<strong class="mandatory">*</strong>
													</label>
													<textarea
														placeholder="Graded teaching work performed by P.G. Students"
														id="graded_teaching" name="graded_teaching"
														rows="5" maxlength="500"></textarea>
												</div>
											</div>
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><input
															class="main-btn info-btn btn-hover btnsave" type="button" id="internship_details_save"
															value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
										
									</form:form>
								</div>
								<!-- Internship & Housejob Details End -->
								<!-- Upload Document Start-->
								<div class="tab custom-d-nonr" id="hide_upload_document_tab">
									<button class="tab-toggle">Upload Document</button>
								</div>
								<div class="content">
									<h4 class="heading">Upload Document</h4>
									<form:form name="document_detail" id="document_detail" action="" method="post" class=""
										commandName="">
										<div class="row">
										
										<input type="hidden" id="hid_document_detail" name="hid_document_detail" value="0">
										
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="inst_block simple-instruction">
									<strong>Instruction :</strong> Max Zip file upload size is limited to 15 MB only.
								</div>
							</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Scan copy of duly notarized affidavits of place of working by all the full time teachers
													<strong class="mandatory">*</strong></label> 
													<div class="input-group custom-input-group"> 
														<input type="file" id="full_time_teacher_affidavit" name="full_time_teacher_affidavit" class="form-control" accept=".zip">
														<input  type="hidden" id="hid_full_time_teacher_affidavit" name="hid_full_time_teacher_affidavit" value="0">
														
													<div class="note-text">
														<span class="errorClass" id="full_time_teacher_affidavit_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="full_time_teacher_affidavit_lbltik"></span>
													</div>
													
									<ul class="buttons-group">									
										<li><a  id="full_time_teacher_affidavit_download" href="AttachmentFilePath?i_id=${GetDocument_Details[0][0]}&doc_id=1" class="main-btn info-btn btn-hover btn-sm btndownload" title="Download Template">
										<i class="lni lni-download"></i></a></li>
									</ul></div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Scan copy of duly notarized affidavits of place of working by all the Guest faculty teachers
													<strong class="mandatory">*</strong></label>
													<div class="input-group custom-input-group">  
														<input type="file" id="guest_teacher_affidavit" name="guest_teacher_affidavit" class="form-control" accept=".zip">
														<input  type="hidden" id="hid_guest_teacher_affidavit" name="hid_guest_teacher_affidavit" value="0">
														
													<div class="note-text">
														<span class="errorClass" id="guest_teacher_affidavit_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="guest_teacher_affidavit_lbltik"></span>
													</div>
													
									<ul class="buttons-group">
										<li><a id="guest_teacher_affidavit_download" href="AttachmentFilePath?i_id=${GetDocument_Details[0][0]}&doc_id=2" class="main-btn info-btn btn-hover btn-sm btndownload" title="Download Template">
										<i class="lni lni-download"></i></a></li>
									</ul></div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Miscellaneous upload (documents related to teachers, which may be missed in teachers code portal)</label> 
													<input type="file" id="miscellaneous_doc" name="miscellaneous_doc" class="form-control" accept=".zip">
													<input  type="hidden" id="hid_miscellaneous_doc" name="hid_miscellaneous_doc" value="0">
													
													<div class="note-text">
														<span class="errorClass" id="miscellaneous_doc_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="miscellaneous_doc_lbltik"></span>
													</div>
													
												</div>
											</div>
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover btnsave" id="document_details_save"
															type="button" value="Save"></li>
															<li><a 
																class="main-btn dark-btn-outline btn-hover btn-iconic-icon viewData d-none"><i
																	class="lni lni-eye"></i>View</a></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- Upload Document End-->
<!-- 								<div class="tab" id="undertaking_and_reports"> -->
<!-- 									<button class="tab-toggle">Undertakings & Reports</button> -->
<!-- 								</div> -->
<!-- 								<div class="content "> -->
<!-- 									<h4 class="heading">Undertakings & Reports</h4> -->
<%-- 									<form:form name="undertaking_and_reports" id="undertaking_and_reports" action="undertaking_reports_action?${_csrf.parameterName}=${_csrf.token}" method="post"  --%>
<%-- 										modelAttribute="undertaking_report_CMD" enctype="multipart/form-data"> --%>
										
<!-- 										<div class="row"> -->
<!-- 											<div class="col-lg-12 col-md-12 col-sm-12"> -->
<!-- 												<div class="inst_block"> -->
<!-- 													<h5>Instruction</h5> -->
<!-- 													<ul class="inst_list"> -->
<!-- 														<li><p class="inst_text">Please click on -->
<!-- 																'Download templates' to download the templates being -->
<!-- 																uploaded in applicable sections of the form.</p></li> -->
<!-- 														<li><p class="inst_text">For uploading the -->
<!-- 																required files, take a print on the letterhead of the -->
<!-- 																Institution, and after getting it stamped and signed, -->
<!-- 																upload the scanned copy by clicking at the appropriate -->
<!-- 																option.</p></li> -->
<!-- 													</ul> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<hr> -->
											
<!-- 											<input type="hidden" id="undertaking_report_hidden" -->
<!-- 															name="undertaking_report_hidden" value="0" class="form-control "> -->

											

											

											

											
<!-- 										</div> -->
<!-- 										<div class="row"> -->
<!-- 											<div class="col-lg-12 col-md-12 col-sm-12"> -->
<!-- 												<ul class="buttons-group mainbtn"> -->
<!-- 													<li><input class="main-btn info-btn btn-hover btnsave" id="save_undert_rpo" -->
<!-- 														type="button" value="Save"></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 										</div> -->
<%-- 									</form:form> --%>
<!-- 								</div> -->

								<div class="tab" id="declaration">
									<button class="tab-toggle">Declaration</button>
								</div>
								<div class="content ">
									<h4 class="heading">Declaration</h4>
									<form:form name="" id="" action="" method="post" class=""
										commandName="">
										<div class="top_content">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<div class="input-style-1">
														<label>I, Dr.<input type="text" name="declaration_id"
															id="declaration_id" class="form-control declaration_input"
															placeholder="" maxlength="100"> solemnly confirm
															that if any information provided by me in proforma and
															Annexures found false, I shall be held responsible in the
															matter. I shall have no objection if any legal action is
															taken by the CCH against me.
														</label>
													</div>
													<div class="custom-choose-one">
														<div class="input-style-form-check_block check-multi-list">
															<div class="form-check checkbox-style">
																<input type="checkbox" id="Declaration"
																	name="Declaration" autocomplete="off" maxlength="25"
																	class="form-check-input"> <label
																	class="check-list" for="Declaration">I Agree</label>
															</div>
														</div>
													</div>

												</div>
											</div>
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input
															class="main-btn success-btn btn-hover btnsubmit" id="final_submit"
															type="button" value="Submit"></li>
															<li><a 
																class="main-btn dark-btn-outline btn-hover btn-iconic-icon viewData d-none"><i
																	class="lni lni-eye"></i>View</a></li>
																
															
													</ul>
												</div>
											</div>

										</div>
									</form:form>
								</div>
							</div>

						</div>
					</div>
					<!--    ===========================
		 Institution Information End
===========================     -->
				</div>
			</div>
			<!-- end row -->
		</div>
		<!-- end container -->
	</section>
	<!-- regulation components end -->
		<c:url value="View_Search_Basic_InfoUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="id6">
	<input type="hidden" name="id6" id="id6" value="0" />
	<input type="hidden" name="id7" id="id7" value="0" />
	<input type="hidden" name="statusview" id="statusview" value="0" />
</form:form>

	<script type="text/javascript"
		src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>


<script type="text/javascript" nonce="${cspNonce}">
	$(document).ready(function() {
		//alert('${msg}');     
// 		if ('${msg}'!='') {
// 			alert('${msg}');
// 		}
		if ('${role}'=='NCH') {
			$(".viewData").addClass("d-none")
		}
		if ('${role}'=='Institute_NCH') {
			$(".viewData").removeClass("d-none")
		} 

		$.ajaxSetup({
			async : false
		});
		
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
			
			datepicketDate('date_permission_s_govt');
			datepicketDate('date_permission_c_govt');
			datepicketDate('date_affili_uni');
			datepicketDate('date_consent_affili_uni');
			datepicketDate('date_last_affili_uni');
			datepicketDate('last_stu_date_admitted1');
			
			datepicketDate('dob_id');
			datepicketDate('doj_id');
			datepicketDate('last_stu_add_date1');
		}

// 		document.getElementById('per_add_line2').onkeypress = function() {
// 			return onlyAlphaNumeric(event, this);
// 		};

	var date = new Date();
	 var today = date.toISOString().substring(0,7);
	 $("#s_establishment").attr('max',today);
	 $("#establishment_college").attr('max',today);
	 



	var login_name ='${login_name}';
	
	if(login_name != "[]"){
		
		$("#declaration_id").val('${login_name}');
	}
	
	
	//-------------Start----------------Data Fetch For Basic Institution Details----------------
		var dataparent ='${dataparent}';
		
		if(dataparent != "[]"){
			debugger;
			
// 			if('${dataparent[0].status}'!=0 && '${dataparent[0].status}'!=2 ){
// 				$(".btnsave").hide();
// 				$(".btnsubmit").hide();
// 			}
			

	
	

			$("#inst_basic_hidden").val('${dataparent[0].id}');
			$("#per_add_line1").val('${dataparent[0].address_line1}');
			$("#per_add_line2").val('${dataparent[0].address_line2}');
			$("#per_state").val('${dataparent[0].state_id}');
			getDistrictforIbAdd();
			$("#per_district").val('${dataparent[0].district_inst_add}');
			$("#city").val('${dataparent[0].city}');
			$("#per_pincode").val('${dataparent[0].pincode}');
			$("#cand_prefix_clg").val('${dataparent[0].college_phone_code}');
			$("#alt_mo_no_clg").val('${dataparent[0].college_phone_no}');
			$("#cand_prefix_hospi").val('${dataparent[0].hospital_phone_code}');
			$("#alt_mo_no_hosp").val('${dataparent[0].hospital_phone_no}');
			$("#mobile_no_addr").val('${dataparent[0].mobile_no}');
			$("#fax_no").val('${dataparent[0].fax_no}');
			$("#email").val('${dataparent[0].email_id}');
			$("#website").val('${dataparent[0].website}');
			$("#institution_type").val('${dataparent[0].institute_type}');
			$("#managing_body").val('${dataparent[0].name_of_managing_body}');
			$("#management_contact").val('${dataparent[0].name_of_management_contact}');
			$("#mang_per_add_line1").val('${dataparent[0].mngt_address_line1}');
			$("#mang_per_add_line2").val('${dataparent[0].mngt_address_line2}');
			$("#mang_per_state").val('${dataparent[0].mngt_state}');
			getDistrictforIBMC();
			$("#mang_per_district").val('${dataparent[0].mngt_district}');
			$("#mang_city").val('${dataparent[0].mngt_city}');
			$("#mang_office_phone").val('${dataparent[0].mngt_office_phone_no}');
			$("#mang_residence_phone").val('${dataparent[0].mngt_residence_phone_no}');
			$("#mobile_no_mngmt_cont_dt").val('${dataparent[0].mngt_mobile_no}');
			$("#email_mgmt_contact_dt").val('${dataparent[0].mngt_email_id}');
			$("#name_of_society").val('${dataparent[0].name_of_society}');
			$("#s_registration_no").val('${dataparent[0].society_reg_no}');
			$("#s_establishment").val('${dataparent[0].year_of_establish_society}');
			$("#date_permission_s_govt").val('${dataparent[0].date_of_permission}');
			$("#date_permission_c_govt").val('${dataparent[0].date_of_central}');
			$("#university_affiliated").val('${dataparent[0].name_of_uni_affilate}');
			$("#date_affili_uni").val('${dataparent[0].date_of_first_affilia}');
			$("#date_last_affili_uni").val('${dataparent[0].date_last_consent_affilia}');
			
			
			$("#establishment_college").val('${dataparent[0].year_of_establish_college}');
			$("#date_consent_affili_uni").val('${dataparent[0].date_of_consent_affilia}');
			$("#fax_code").val('${dataparent[0].fax_code}');
			$("#mang_office_code").val('${dataparent[0].mang_office_code}');
			$("#mang_residence_code").val('${dataparent[0].mang_residence_code}');
			
			$("#hid_trustreg_doc").val('${dataparent[0].reg_certi_of_society}');
			$("#hid_trustdeed_doc").val('${dataparent[0].copy_of_society}');
			$("#hid_furnish_bank_doc").val('${dataparent[0].undertaking_letter_furnish_bank_guarantee}');
			$("#hid_noc_state_doc").val('${dataparent[0].noc_doc_state_gov}');
			$("#hid_affilat_doc").val('${dataparent[0].affiliaion_doc_concern_uni}');
			
			$("#hid_uni_app_doc").val('${dataparent[0].uni_app_letter}');
			$("#hid_inspection_acade_yr_doc").val('${dataparent[0].inspection_last_academic_yr}');
			
			
			
		}
		
		
		var inst_basic_hidden = $("#inst_basic_hidden").val()
	 	if(inst_basic_hidden == 0){
	 		$("#head_of_institution_dtl").hide();
	 		$("#important_information_of_connectivity").hide();
	 		$("#police_station_dtl").hide();
	 		$("#intake_capacity").hide();
	 		$("#course_intake_capacity").hide();
	 		$("#details_of_land").hide();
	 		$("#undertaking_and_reports").hide();
	 		$("#hide_internship_details_tab").hide();
	 		$("#hide_upload_document_tab").hide();
	 		$("#declaration").hide();
	 		
	 		
	 	}else{
	 	 	$("#head_of_institution_dtl").show();
	 	 	$("#important_information_of_connectivity").show();
	 	 	$("#police_station_dtl").show();
	 	 	$("#intake_capacity").show();
	 	 	$("#course_intake_capacity").show();
	 	 	$("#details_of_land").show();
	 	 	$("#undertaking_and_reports").show();
	 	 	$("#hide_internship_details_tab").show();
	 	 	$("#hide_upload_document_tab").show();
	 	 	$("#declaration").show();
	 	}
		
		
		
		
			
			//---------EnD------------------Data Fetch For Basic Institution Details----------------		
			
			
			var datainfoconnect ='${datainfoconnect}';
			
			if(datainfoconnect != "[]"){
				
				
				$("#info_connecti_hidden").val('${datainfoconnect[0].id}');
				$("#nearest_airport").val('${datainfoconnect[0].nearest_airport_name}');
				$("#airport_distance").val('${datainfoconnect[0].approx_distance}');
				$("#nearest_railway").val('${datainfoconnect[0].nearest_railway_station}');
				$("#railway_distance").val('${datainfoconnect[0].approx_dist_railway}');
				$("#nearest_institutions").val('${datainfoconnect[0].nearest_inst_name}');
				$("#inst_city").val('${datainfoconnect[0].city}');
				$("#nr_inst_state_ic").val('${datainfoconnect[0].other_state}');
				getDistrict();
				$("#nr_inst_district_ic").val('${datainfoconnect[0].other_district}');
				
				
				
			}
			
	//---------------------------Data Fetch For police station Details-------------------		
			
			
			
			var datapolice ='${datapolice}';
			
			
			
			if(datapolice != "[]"){
				
				
				$("#police_station_hidden").val('${datapolice[0].id}');
				$("#nearest_police").val('${datapolice[0].name_of_nearest_police_station}');
				$("#police_per_add_line1").val('${datapolice[0].addr_line1}');
				$("#police_per_add_line2").val('${datapolice[0].addr_line2}');
				$("#police_per_state").val('${datapolice[0].state}');
				getDistrictForPolice();
				$("#police_per_district").val('${datapolice[0].district_id}');
				$("#police_city").val('${datapolice[0].city}');
				$("#police_per_pincode").val('${datapolice[0].pincode}');
				
				$("#police_std").val('${datapolice[0].police_std_code}');
				$("#police_phone_no").val('${datapolice[0].phone_number}');
				$("#sp_office_std").val('${datapolice[0].sp_std_code}');
				$("#sp_office_phone_no").val('${datapolice[0].sp_phone_number}');
				
				
				
			}
			
//---------------------------------Data Fetch For Head of Institution Details----		
					var datainstdtl ='${datainstdtl}';
			
		if(datainstdtl != "[]"){
				
			
				$("#head_of_inst_hidden").val('${datainstdtl[0].id}');
				$("#name_pdd").val('${datainstdtl[0].name_of_principal}');
				$("#cch_registration").val('${datainstdtl[0].cch_reg_no}');
				$("#state_rn_head").val('${datainstdtl[0].state_reg}');
				$("#state_registration_no").val('${datainstdtl[0].state_reg_no}');
				$("#dob_id").val('${datainstdtl[0].date_of_birth}');
				$("#doj_id").val('${datainstdtl[0].date_of_join}');
				
				$("#professor").val('${datainstdtl[0].professor_exp}');
				$("#reader").val('${datainstdtl[0].reader_exp}');
				$("#lecturer").val('${datainstdtl[0].lecturer_exp}');
				$("#demonstrator").val('${datainstdtl[0].demonstraror_exp}');
				
				$("#head_per_add_line1").val('${datainstdtl[0].address_line1}');
				$("#head_per_add_line2").val('${datainstdtl[0].address_line2}');
				$("#per_state_head_inst").val('${datainstdtl[0].state_add_detai}');
				getDistrictforHIAdd();
				$("#per_district_head_inst").val('${datainstdtl[0].district_add_detai}');
				$("#head_city").val('${datainstdtl[0].city}');
				$("#head_pincode").val('${datainstdtl[0].pincode}');
				$("#head_alt_mo_no1").val('${datainstdtl[0].office_phone_no}');
				$("#headre_alt_mo_no1").val('${datainstdtl[0].residence_phone_no}');
				$("#head_alt_mo_no2").val('${datainstdtl[0].mobile_no}');
				$("#head_email").val('${datainstdtl[0].email_id}');
				$("#head_std").val('${datainstdtl[0].head_std}');
				$("#headre_std").val('${datainstdtl[0].headre_std}');
				$("#teacher_code").val('${datainstdtl[0].teacher_code}');
				
				

			}

		//---------------------------------Data Fetch For  Details of Land----------------
		
		
		var datadtlland ='${datadtlland}';
		
		if(datadtlland != "[]"){
				
			
				$("#detail_land_hidden").val('${datadtlland[0].id}');
				$("#area_of_land").val('${datadtlland[0].total_area_of_land_society}');
				$("#own_land").val('${datadtlland[0].ownership_of_land}');
				$("#land_name").val('${datadtlland[0].land_in_the_name}');
				$("#entire_name").val('${datadtlland[0].distribution_of_entire_land}');
				$("#land_availability").val('${datadtlland[0].land_availability_with_society}');
				$("#stic").val('${datadtlland[0].same_society_trust}');
				
				var constructedcheck = '${datadtlland[0].college_and_hospital_constructed}';
				$("input[name='constructedcheck'][value='"+constructedcheck+"']").prop('checked', true);
				
			//	$("#constructedcheck").val('${datadtlland[0].college_and_hospital_constructed}');
			
				var buildingcheck = '${datadtlland[0].college_and_hospital_building}';
				$("input[name='buildingcheck'][value='"+buildingcheck+"']").prop('checked', true);
			
			
			//	$("#buildingcheck").val('${datadtlland[0].college_and_hospital_building}');
				$("#clg_land").val('${datadtlland[0].total_area_of_land_alloted_clg}');
				$("#hospital_land").val('${datadtlland[0].total_area_of_land_alloted_clg_hospital}');
				$("#hostels_land").val('${datadtlland[0].total_area_of_land_alloted_clg_hostels}');
				
				var shiftedcheck = '${datadtlland[0].college_and_hospital_building_shifted}';
				$("input[name='shiftedcheck'][value='"+shiftedcheck+"']").prop('checked', true);
				//$("#shiftedcheck").val('${datadtlland[0].college_and_hospital_building_shifted}');
				
				var changedcheck = '${datadtlland[0].management_of_college_changed}';
				$("input[name='changedcheck'][value='"+shiftedcheck+"']").prop('checked', true);
				
				//$("#changedcheck").val('${datadtlland[0].management_of_college_changed}');
				$("#hid_land_doc_clg").val('${datadtlland[0].land_document_clg}');
				$("#hid_building_plan_doc").val('${datadtlland[0].building_plan}');
				$("#hid_architect_doc").val('${datadtlland[0].statement_doc_architect}');
				$("#hid_balancesheet_doc").val('${datadtlland[0].audited_balance_sheet}');
				$("#hid_annual_doc").val('${datadtlland[0].annual_report}');
				$("#hid_add_doc").val('${datadtlland[0].additional_document}');
				
				 
				

			}
		
//---------------------Data Fetch For Undertaking reports------------		
		
		var dataundertake ='${dataundertake}';
		
		if(dataundertake != "[]"){
			
			$("#undertaking_report_hidden").val('${dataundertake[0].id}');
// 			$("#hid_trustreg_doc").val('${dataundertake[0].reg_certi_of_society}');
// 			$("#hid_trustdeed_doc").val('${dataundertake[0].copy_of_society}');
// 			$("#hid_furnish_bank_doc").val('${dataundertake[0].undertaking_letter_furnish_bank_guarantee}');
// 			$("#hid_noc_state_doc").val('${dataundertake[0].noc_doc_state_gov}');
// 			$("#hid_affilat_doc").val('${dataundertake[0].affiliaion_doc_concern_uni}');
	//		$("#hid_denial_doc").val('${dataundertake[0].ayush_permission_letter}');
	//		$("#hid_courtorder_doc").val('${dataundertake[0].upload_court_order}');
// 			$("#hid_land_doc_clg").val('${dataundertake[0].land_document_clg}');
// 			$("#hid_building_plan_doc").val('${dataundertake[0].building_plan}');
// 			$("#hid_architect_doc").val('${dataundertake[0].statement_doc_architect}');
	//		$("#hid_per_gov_doc").val('${dataundertake[0].inst_not_admit_stu_without_permission}');
// 			$("#hid_balancesheet_doc").val('${dataundertake[0].audited_balance_sheet}');
// 			$("#hid_annual_doc").val('${dataundertake[0].annual_report}');
	//		$("#hid_cchreg_doc").val('${dataundertake[0].selection_of_students}');
// 			$("#hid_add_doc").val('${dataundertake[0].additional_document}');
			
	
		}
		
		var dataintak_child ='${dataintak_child}';
		
		if(dataintak_child != "[]"){
			
			$("#hid_for_doc").val('${dataintak_child[0].id}');
			$("#hid_denial_doc").val('${dataintak_child[0].ayush_permission_letter}');
			$("#hid_courtorder_doc").val('${dataintak_child[0].upload_court_order}');
			$("#hid_per_gov_doc").val('${dataintak_child[0].inst_not_admit_stu_without_permission}');
			$("#hid_cchreg_doc").val('${dataintak_child[0].selection_of_students}');
			$("#hid_lastfiveguide").val('${dataintak_child[0].lastfiveguide}');
			$("#hid_neet_score").val('${dataintak_child[0].neet_score}');
			$("#hid_undertakingofstudent").val('${dataintak_child[0].undertakingofstudent}');
			$("#hid_biometricattendance").val('${dataintak_child[0].biometricattendance}');
			
			var intimatedcheck = '${dataintak_child[0].intimatedcheck}';
			$("input[name='intimatedcheck'][value='"+intimatedcheck+"']").prop('checked', true);
			if(intimatedcheck == "1"){
				 $("div#hid_undertakingcheck").show();
			}else{
				 $("div#hid_undertakingcheck").hide();
			}
			var undertakingcheck = '${dataintak_child[0].undertakingcheck}';
			$("input[name='undertakingcheck'][value='"+undertakingcheck+"']").prop('checked', true);
			
			
			
		}
		
		
		
		
		//---------------------Data Fetch For quali head of institute------------
		
// 		var dataquali ='${dataquali}';
		
// 		if(dataquali != "[]"){
			
			
// 			$("#hid_quali").val('${dataquali[0].id}');
// 			$("#typeOfDegree1").val('${dataquali[0].quali_type}');
// 			$("#awarding_authority1").val('${dataquali[0].awarding_authority}');
// 			$("#passsing_yr1").val('${dataquali[0].passing_year}');
		
// 		}
		getIntake_Capacity();
		getIntake_Capacity_pg();
		getqualification();
		getCouurse_Intake_Cap_ug();
		getCouurse_Intake_Cap_pg();
		getInternship_Details();
		getDocument_Details();

	});
	
	
	
	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('per_add_line1').onkeypress = function() {
			return onlyAlphaNumericAbhiCustom(event, this);
		};
// 		document.getElementById('year1').onchange = function() {
// 		 yearremove(event,this);
// 		};
		
		document.getElementById('per_add_line2').onkeypress = function() {
			return onlyAlphaNumericAbhiCustom(event, this);
		};
		
		document.getElementById('per_state').onchange = function() {
			getDistrictforIbAdd();
		};
		
		
		document.getElementById('city').onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this);	
		};
		document.getElementById('per_pincode').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('cand_prefix_clg').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('alt_mo_no_clg').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
// 		document.getElementById('alt_mo_no_clg').onkeypress = function() {
// 			return mobileNumber(this);
// 		};
		
		document.getElementById('alt_mo_no_hosp').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('cand_prefix_hospi').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
// 		document.getElementById('alt_mo_no_hosp').onkeypress = function() {
// 			return mobileNumber(this);
// 		};
		
		document.getElementById('fax_code').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('fax_no').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('email').onchange = function() {
			return checkgmail(this.value);
		};
		document.getElementById('managing_body').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		document.getElementById('management_contact').onkeypress = function() {
			return onlyAlphaNumericAbhiCustom(event, this);
		};
		document.getElementById('mang_per_add_line1').onkeypress = function() {
			return onlyAlphaNumericAbhiCustom(event, this);
		};
		document.getElementById('mang_per_add_line2').onkeypress = function() {
			return onlyAlphaNumericAbhiCustom(event, this);	
		};
		document.getElementById('mang_per_state').onchange = function() {
			getDistrictforIBMC();
		};
		
		
		
		
		document.getElementById('mang_city').onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this);	
		};
		document.getElementById('mang_office_code').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('mang_office_phone').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('mobile_no_mngmt_cont_dt').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('mobile_no_mngmt_cont_dt').onchange = function() {
			return mobileNumber(this);
		};
		document.getElementById('email_mgmt_contact_dt').onchange = function() {
			return checkgmail(this.value);
		};
		document.getElementById('mobile_no_addr').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('mobile_no_addr').onchange = function() {
			return mobileNumber(this);
		};
		
		document.getElementById('mang_residence_phone').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('mang_residence_code').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		
		
		document.getElementById('name_of_society').onkeypress = function() {
			return onlyAlphaNumericAbhiCustom(event, this);
		};
		document.getElementById('s_registration_no').onkeypress = function() {
			return onlyAlphaNumericAbhiCustom(event,this);
		};
		document.getElementById('s_establishment').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('date_permission_s_govt').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_permission_s_govt').onfocus = function() {
			return this.style.color='#000000';
		};
		document.getElementById('date_permission_s_govt').onblur = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		document.getElementById('date_permission_s_govt').onkeyup = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_permission_s_govt').onchange = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		
		document.getElementById('date_permission_c_govt').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_permission_c_govt').onfocus = function() {
			return this.style.color='#000000';
		};
		document.getElementById('date_permission_c_govt').onblur = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		document.getElementById('date_permission_c_govt').onkeyup = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_permission_c_govt').onchange = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		document.getElementById('university_affiliated').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		
		document.getElementById('date_affili_uni').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_affili_uni').onfocus = function() {
			return this.style.color='#000000';
		};
		document.getElementById('date_affili_uni').onblur = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		document.getElementById('date_affili_uni').onkeyup = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_affili_uni').onchange = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		document.getElementById('establishment_college').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		
		document.getElementById('nr_inst_state_ic').onchange = function() {
			// changeAddress(); 
			getDistrict();
		};
		
		
		document.getElementById('date_consent_affili_uni').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_consent_affili_uni').onfocus = function() {
			return this.style.color='#000000';
		};
		document.getElementById('date_consent_affili_uni').onblur = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		document.getElementById('date_consent_affili_uni').onkeyup = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_consent_affili_uni').onchange = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		
		document.getElementById('date_last_affili_uni').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_last_affili_uni').onfocus = function() {
			return this.style.color='#000000';
		};
		document.getElementById('date_last_affili_uni').onblur = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		document.getElementById('date_last_affili_uni').onkeyup = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_last_affili_uni').onchange = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		
		document.getElementById('trustreg_doc').onchange = function() {
			debugger;
			return pdfFileSizeValidation(this,this.value,"trustreg_doc","200kb","trustreg_doc_lbltik","trustreg_doc_lbl",this.accept)
		};
		document.getElementById('trustdeed_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"trustdeed_doc","200kb","trustdeed_doc_lbltik","trustdeed_doc_lbl",this.accept)
		};
		document.getElementById('furnish_bank_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"furnish_bank_doc","200kb","furnish_bank_doc_lbltik","furnish_bank_doc_lbl",this.accept)
		};
		document.getElementById('noc_state_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"noc_state_doc","200kb","noc_state_doc_lbltik","noc_state_doc_lbl",this.accept)
		};
		document.getElementById('affilat_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"affilat_doc","200kb","affilat_doc_lbltik","affilat_doc_lbl",this.accept)
		};
		
		document.getElementById('uni_app_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"uni_app_doc","200kb","uni_app_doc_lbltik","uni_app_doc_lbl",this.accept)
		};
		document.getElementById('inspection_acade_yr_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"inspection_acade_yr_doc","200kb","inspection_acade_yr_doc_lbltik","inspection_acade_yr_doc_lbl",this.accept)
		};
		
		document.getElementById('website').onchange = function() {
			return isValidUrl
		};
		document.getElementById('teacher_code').onkeypress = function() {
			return onlyAlphaNumericAbhiCustom(event,this);
		};
		
		document.getElementById('awarding_authority1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this);
		};
		document.getElementById('name_pdd').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		document.getElementById('cch_registration').onkeypress = function() {
			return onlyAlphaNumericAbhiCustom(event,this);
		};
		document.getElementById('state_registration_no').onkeypress = function() {
			return onlyAlphaNumericAbhiCustom(event,this);
		};
		
		document.getElementById('dob_id').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('dob_id').onfocus = function() {
			return this.style.color='#000000';
		};
		document.getElementById('dob_id').onblur = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		document.getElementById('dob_id').onkeyup = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('dob_id').onchange = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		
		
		document.getElementById('doj_id').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('doj_id').onfocus = function() {
			return this.style.color='#000000';
		};
		document.getElementById('doj_id').onblur = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		document.getElementById('doj_id').onkeyup = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('doj_id').onchange = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		
		document.getElementById('professor').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('reader').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('lecturer').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('demonstrator').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('head_per_add_line1').onkeypress = function() {
			return onlyAlphaNumericAbhiCustom(event, this);
		};
		document.getElementById('head_per_add_line2').onkeypress = function() {
			return onlyAlphaNumericAbhiCustom(event, this);
		};
		document.getElementById('per_state_head_inst').onchange = function() {
			return getDistrictforHIAdd();
		};
		
		
		
		
		document.getElementById('head_city').onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this);	
		};
		document.getElementById('head_pincode').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('head_std').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('head_alt_mo_no1').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('headre_std').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('headre_alt_mo_no1').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('head_alt_mo_no2').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('head_alt_mo_no2').onkeypress = function() {
			return mobileNumber(this);
		};
		document.getElementById('head_email').onchange = function() {
			return checkgmail(this.value);
		};
		document.getElementById('nearest_airport').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		document.getElementById('airport_distance').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('nearest_railway').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		document.getElementById('railway_distance').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('nearest_institutions').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		document.getElementById('inst_city').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		document.getElementById('nearest_police').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		document.getElementById('police_per_add_line1').onkeypress = function() {
			return onlyAlphaNumericAbhiCustom(event, this);
		};
		document.getElementById('police_per_add_line2').onkeypress = function() {
			return onlyAlphaNumericAbhiCustom(event, this);
		};
		document.getElementById('police_per_state').onchange = function() {
			return getDistrictForPolice();
		};
		
		
		document.getElementById('police_city').onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this);	
		};
		document.getElementById('police_per_pincode').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
// 		document.getElementById('police_per_pincode').onchange = function() {
// 			return checkLength(this, 6);
// 		};
		
		document.getElementById('police_phone_no').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		
		document.getElementById('area_of_land').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('clg_land').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('hospital_land').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('hostels_land').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('denial_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"denial_doc","200kb","denial_doc_lbltik","denial_doc_lbl",this.accept)
		};
		document.getElementById('courtorder_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"courtorder_doc","200kb","courtorder_doc_lbltik","courtorder_doc_lbl",this.accept)
		};
		document.getElementById('per_gov_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"per_gov_doc","200kb","per_gov_doc_lbltik","per_gov_doc_lbl",this.accept)
		};
		document.getElementById('cchreg_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"cchreg_doc","200kb","cchreg_doc_lbltik","cchreg_doc_lbl",this.accept)
		};
		
		document.getElementById('land_doc_clg').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"land_doc_clg","200kb","land_doc_clg_lbltik","land_doc_clg_lbl",this.accept)
		};
		document.getElementById('building_plan_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"building_plan_doc","200kb","building_plan_doc_lbltik","building_plan_doc_lbl",this.accept)
		};
		document.getElementById('architect_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"architect_doc","200kb","architect_doc_lbltik","architect_doc_lbl",this.accept)
		};
		document.getElementById('balancesheet_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"balancesheet_doc","200kb","balancesheet_doc_lbltik","balancesheet_doc_lbl",this.accept)
		};
		document.getElementById('annual_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"annual_doc","200kb","annual_doc_lbltik","annual_doc_lbl",this.accept)
		};
		document.getElementById('add_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"add_doc","200kb","add_doc_lbltik","add_doc_lbl",this.accept)
		};
		
		
		document.getElementById('save_inst_basic').onclick = function() {
			
			 saveDraft_Institution_basic_detais();
				
		};
		
		document.getElementById('save_head_inst').onclick = function() {
			
			saveDraft_Head_of_Institution_detais();
				
		};
		document.getElementById('save_info_connect').onclick = function() {
			
			saveDraft_information_connectivity_detais();
				
		};
	document.getElementById('save_police').onclick = function() {
			
		saveDraft_police_station_detais();
				
		};
		
		document.getElementById('save_dtl_land').onclick = function() {
			
			saveDraft_detais_of_land();
					
			};
// 			document.getElementById('save_undert_rpo').onclick = function() {
				
// 				saveDraft_undertaking_and_reports();
						
// 				};
				
				document.getElementById('save_quali1').onclick = function() {
					
					saveDraft_Qualification_inst_dtl(1);
							
					};
					document.getElementById('quali_add1').onclick = function() {
						return formOpen_Quali(1);
					};
					document.getElementById('teacher_code').onblur = function() {
						return getfacultydtl();
					};
					
					
					
					//ADD MORE INTAKE CAPACITY
					document.getElementById('save_int_cap1').onclick = function() {
						return Save_As_Draft_Intake(1);
					};
					document.getElementById('intake_add1').onclick = function() {
						return formOpen_Intake_capacity(1);
					};
// 					document.getElementById('intake_remove1').onclick = function() {
// 						return delete_Library(1);
// 					};
					document.getElementById('save_int_cap_pg1').onclick = function() {
						return Save_As_Draft_Intake_pg(1);
					};
					document.getElementById('intake_add_pg1').onclick = function() {
						return formOpen_Intake_capacity_PG(1);
					};
					
					
					
					
					document.getElementById('savedrft_intake_cap').onclick = function() {
					//	Validation();
						Save_As_Draft_course_intake_cap();
						
				};
				

				document.getElementById('final_submit').onclick = function() {
						
						final_submit();
						
				};
// 				document.getElementById('savedrft_intake_cap').onclick = function() {
					
// 					();
					
// 			};
				
				//INTERNSHIP DETAILS
				
				
				//INTERNSHIP DETAILS START
				document.getElementById('internship_details_save').onclick = function() {
				return Save_As_Draft_Internship_Details();	
				};
				document.getElementById('migration_Yes').onclick = function() {
					Migration_Yes();
				};
				document.getElementById('migration_No').onclick = function() {
					Migration_Yes();
				};
//		 		document.getElementById('internsopd').onkeypress = function() {
//		 			return isNumberKey0to9(event, this);
//		 		};
//		 		document.getElementById('internsipd').onkeypress = function() {
//		 			return isNumberKey0to9(event, this);
//		 		};
				document.getElementById('internsdutyopd').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('internsdutyipd').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('prescribe').onkeypress = function() {
					return onlyAlphabetsStringSpace(event, this);
				};
				document.getElementById('seminar').onkeypress = function() {
					return onlyAlphabetsStringSpace(event, this);
				};
				document.getElementById('internship_not_completed').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('house_job').onkeypress = function() {
					return onlyAlphabetsStringSpace(event, this);
				};
				document.getElementById('no_house_job').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('graded_teaching').onkeypress = function() {
					return onlyAlphabetsStringSpace(event, this);
				};
				document.getElementById('total_intern').onchange = function() {
					return pdfFileSizeValidation(this,this.value,"total_intern","200kb","total_intern_lbltik","total_intern_lbl",this.accept)
				};
				document.getElementById('migration_list').onchange = function() {
					return pdfFileSizeValidation(this,this.value,"migration_list","200kb","migration_list_lbltik","migration_list_lbl",this.accept)
				};
					
			
				//INTAKE CAPACITY FOR UG COURSE
				
				document.getElementById('intake_capacity1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('amitted_seat1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('permitted_seat1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('sanctioned_seat1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('govt_quota_ug1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('mang_quota_ug1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('last_student1').onkeypress = function() {
					return onlyAlphabetsStringSpace(event, this);
				};
				
				document.getElementById('appeared_stu_ug1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('passed_stu_ug1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				
				//INTAKE CAPACITY FOR PG COURSE
				document.getElementById('intake_capacity_pg1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('admited_seat_pg1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('permitted_seat_pg1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('sanction_seat_pg1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('stu_govt_quota1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('stu_mgmt_quota1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('last_stu_admitted1').onkeypress = function() {
					return onlyAlphabetsStringSpace(event, this);
				};
				document.getElementById('final_stu_app_exam1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('final_stu_pass_exam1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				
				
				
				//UPLOAD DOCUMENT FROM STUDENT DETAILS
				document.getElementById('lastfiveguide').onchange = function() {
					return pdfFileSizeValidation(this,this.value,"lastfiveguide","200kb","lastfiveguide_lbltik","lastfiveguide_lbl",this.accept)
				};
				document.getElementById('neet_score').onchange = function() {
					return pdfFileSizeValidation(this,this.value,"neet_score","200kb","neet_score_lbltik","neet_score_lbl",this.accept)
				};
				document.getElementById('undertakingofstudent').onchange = function() {
					return pdfFileSizeValidation(this,this.value,"undertakingofstudent","200kb","undertakingofstudent_lbltik","undertakingofstudent_lbl",this.accept)
				};
				document.getElementById('biometricattendance').onchange = function() {
					return pdfFileSizeValidation(this,this.value,"biometricattendance","200kb","biometricattendance_lbltik","biometricattendance_lbl",this.accept)
				};
				
				
				//CHECK RADIO BUTTON FROM STUDENT DETAILS
				document.getElementById('intimatedYes').onclick = function() {
					Intimated_Yes();
				};
				document.getElementById('intimatedNo').onclick = function() {
					Intimated_Yes();
				};
				
				//UPLOAD DOCUMENT FROM COLLEGE STAFF DETAILS
				//UPLOAD DOCUMENT START
				document.getElementById('document_details_save').onclick = function() {
					return Save_As_Draft_Document_Details();	
				};
				document.getElementById('full_time_teacher_affidavit').onchange = function() {
					return pdfFileSizeValidation(this,this.value,"full_time_teacher_affidavit","15mb","full_time_teacher_affidavit_lbltik","full_time_teacher_affidavit_lbl",this.accept)
				};
				document.getElementById('guest_teacher_affidavit').onchange = function() {
					return pdfFileSizeValidation(this,this.value,"guest_teacher_affidavit","15mb","guest_teacher_affidavit_lbltik","guest_teacher_affidavit_lbl",this.accept)
				};
				document.getElementById('miscellaneous_doc').onchange = function() {
					return pdfFileSizeValidation(this,this.value,"miscellaneous_doc","15mb","miscellaneous_doc_lbltik","miscellaneous_doc_lbl",this.accept)
				};
				
				
				document.querySelectorAll('.selectCustom').forEach((items, index) => {
					items.addEventListener('change', event => {
						Remove_Expyear("selectCustom","year");
					})
				});
				
				document.querySelectorAll('.selectCustompg').forEach((items, index) => {
					items.addEventListener('change', event => {
						Remove_ExpyearPG("selectCustompg","year_pg");
					})
				});
		
	});
	
	

	
	
	
	
	
	function saveDraft_Institution_basic_detais() {
		
		debugger;
		$.ajaxSetup({
		    async: false
		});	
		
		
		var res = CheckNullorBlank('per_add_line1');
		if (res !== "true") {
			alert(res +"Address Line 1 ");
			$('#per_add_line1').focus();
			return false;
		}
		var res = CheckNullorBlank('per_add_line2');
		if (res !== "true") {
			alert(res +"Address Line 2 ");
			$('#per_add_line2').focus();
			return false;
		}
		if ($("#per_state").val() == "0") {
			alert("Please Select State");
			$("#per_state").focus();
			return false;
		}
		if ($("#per_district").val() == "0") {
			alert("Please Select District");
			$("#per_district").focus();
			return false;
		}
		var res = CheckNullorBlank('city');
		if (res !== "true") {
			alert(res +"City ");
			$('#city').focus();
			return false;
		}
		var res = CheckNullorBlank('per_pincode');
		if (res !== "true") {
			alert(res +"Pincode ");
			$('#per_pincode').focus();
			return false;
		}
		
		
		var ans =  $("#per_pincode").val();
		var minLength = 6;
		 var charLength = ans.length;
		 if(charLength < minLength){
		       	alert(" Please Enter Valid  Six Digit Pincode In Institution Address Details");
					$("input#per_pincode").focus();
					return false;
		       }
		
		
		
		
		if($("#per_pincode").val() == "000000"){
			alert("In 'Personal Details Tab' Please Enter the Valid Pin Code In Institution Address Detail");
			$("#per_pincode").focus();
			return false;
		}
		var res = CheckNullorBlank('cand_prefix_clg');
		if (res !== "true") {
			alert(res +"College Phone Number Code in Institution Address Detail ");
			$('#cand_prefix_clg').focus();
			return false;
		}
		var res = CheckNullorBlank('alt_mo_no_clg');
		if (res !== "true") {
			alert(res +"College Phone Number ");
			$('#alt_mo_no_clg').focus();
			return false;
		}
		var res = CheckNullorBlank('cand_prefix_hospi');
		if (res !== "true") {
			alert(res +"Hospital Phone Number Code in Institution Address Detail ");
			$('#cand_prefix_hospi').focus();
			return false;
		}
		var res = CheckNullorBlank('alt_mo_no_hosp');
		if (res !== "true") {
			alert(res +"Hospital Phone Number ");
			$('#alt_mo_no_hosp').focus();
			return false;
		}
		var res = CheckNullorBlank('mobile_no_addr');
		if (res !== "true") {
			alert(res +"Mobile Number ");
			$('#mobile_no_addr').focus();
			return false;
		}
// 		var res = CheckNullorBlank('fax_code');
// 		if (res !== "true") {
// 			alert(res +"Fax Number Code ");
// 			$('#fax_code').focus();
// 			return false;
// 		}
// 		var res = CheckNullorBlank('fax_no');
// 		if (res !== "true") {
// 			alert(res +"Fax Number ");
// 			$('#fax_no').focus();
// 			return false;
// 		}
		var res = CheckNullorBlank('email');
		if (res !== "true") {
			alert(res +"Email ID");
			$('#email').focus();
			return false;
		}
		var res = CheckNullorBlank('website');
		if (res !== "true") {
			alert(res +"Website ");
			$('#website').focus();
			return false;
		}
		if ($("#institution_type").val() == "0") {
			alert("Please Select Institution Type in Management Contact Details Tab");
			$("#institution_type").focus();
			return false;
		}
		var res = CheckNullorBlank('managing_body');
		if (res !== "true") {
			alert(res +"Name of the Managing Body in Management Contact Details Tab ");
			$('#managing_body').focus();
			return false;
		}
		var res = CheckNullorBlank('management_contact');
		if (res !== "true") {
			alert(res +"Name of the Management Contact in Management Contact Details Tab ");
			$('#management_contact').focus();
			return false;
		}
		var res = CheckNullorBlank('mang_per_add_line1');
		if (res !== "true") {
			alert(res +"Address Line 1 in Management Contact Details Tab ");
			$('#mang_per_add_line1').focus();
			return false;
		}
		var res = CheckNullorBlank('mang_per_add_line2');
		if (res !== "true") {
			alert(res +"Address Line 2 in Management Contact Details Tab ");
			$('#mang_per_add_line2').focus();
			return false;
		}
		
		if ($("#mang_per_state").val() == "0") {
			alert("Please Select State in Management Contact Details Tab ");
			$("#mang_per_state").focus();
			return false;
		}
		if ($("#mang_per_district").val() == "0") {
			alert("Please Select District in Management Contact Details Tab ");
			$("#mang_per_district").focus();
			return false;
		}
		
		var res = CheckNullorBlank('mang_city');
		if (res !== "true") {
			alert(res +"city  in Management Contact Details Tab ");
			$('#mang_city').focus();
			return false;
		}
		var res = CheckNullorBlank('mang_office_code');
		if (res !== "true") {
			alert(res +"Office Phone Number code in Management Contact Details Tab ");
			$('#mang_office_code').focus();
			return false;
		}
		var res = CheckNullorBlank('mang_office_phone');
		if (res !== "true") {
			alert(res +"Office Phone Number in Management Contact Details Tab ");
			$('#mang_office_phone').focus();
			return false;
		}
// 		var res = CheckNullorBlank('mang_residence_code');
// 		if (res !== "true") {
// 			alert(res +"Residence Phone Number code in Management Contact Details Tab ");
// 			$('#mang_residence_code').focus();
// 			return false;
// 		}
// 		var res = CheckNullorBlank('mang_residence_phone');
// 		if (res !== "true") {
// 			alert(res +"Residence Phone Number  in Management Contact Details Tab ");
// 			$('#mang_residence_phone').focus();
// 			return false;
// 		}
		var res = CheckNullorBlank('mobile_no_mngmt_cont_dt');
		if (res !== "true") {
			alert(res +"Mobile  Number  in Management Contact Details Tab ");
			$('#mobile_no_mngmt_cont_dt').focus();
			return false;
		}
		var res = CheckNullorBlank('email_mgmt_contact_dt');
		if (res !== "true") {
			alert(res +"Email ID  in Management Contact Details Tab ");
			$('#email_mgmt_contact_dt').focus();
			return false;
		}
		var res = CheckNullorBlank('name_of_society');
		if (res !== "true") {
			alert(res +"Name of Society/Trust/Government  in Basic Details Tab ");
			$('#name_of_society').focus();
			return false;
		}
		var res = CheckNullorBlank('s_registration_no');
		if (res !== "true") {
			alert(res +"Society/Trust Registration Number  in Basic Details Tab ");
			$('#s_registration_no').focus();
			return false;
		}
		var res = CheckNullorBlank('s_establishment');
		if (res !== "true") {
			alert(res +"Year of Establishment of the Society/Trust  in Basic Details Tab ");
			$('#s_establishment').focus();
			return false;
		}
		if( $("#date_permission_s_govt").val() == "" ||  $("#date_permission_s_govt").val() == "DD/MM/YYYY"){
			alert("Please Select Date of the permission of the State Govt in Basic Details Tab");
			$("#date_permission_s_govt").focus();
			return false;
	   	}
		if( $("#date_permission_c_govt").val() == "" ||  $("#date_permission_c_govt").val() == "DD/MM/YYYY"){
			alert("Please Select Date of First Permission of CCH/NCH Central Govt in Basic Details Tab");
			$("#date_permission_c_govt").focus();
			return false;
	   	}
		var res = CheckNullorBlank('university_affiliated');
		if (res !== "true") {
			alert(res +"Name of the affiliated University  in Basic Details Tab ");
			$('#university_affiliated').focus();
			return false;
		}
		if( $("#date_affili_uni").val() == "" ||  $("#date_affili_uni").val() == "DD/MM/YYYY"){
			alert("Please Select Date of affiliation of University in Basic Details Tab");
			$("#date_affili_uni").focus();
			return false;
	   	}
		var res = CheckNullorBlank('establishment_college');
		if (res !== "true") {
			alert(res +"Year of Establishment of the College  in Basic Details Tab ");
			$('#establishment_college').focus();
			return false;
		}
		if( $("#date_consent_affili_uni").val() == "" ||  $("#date_consent_affili_uni").val() == "DD/MM/YYYY"){
			alert("Please Select Date of consent of affiliation from the University in Basic Details Tab");
			$("#date_consent_affili_uni").focus();
			return false;
	   	}
		if( $("#date_last_affili_uni").val() == "" ||  $("#date_last_affili_uni").val() == "DD/MM/YYYY"){
			alert("Please Select Date of last continuous affiliation University in Basic Details Tab");
			$("#date_last_affili_uni").focus();
			return false;
	   	}
		
		
		if($("#trustreg_doc").val()==''){
			if($("#hid_trustreg_doc").val()==''){
					alert("Please Upload Registration certificate of society/trust in Basic Details Tab ");
					$("#trustreg_doc").focus();
					return false;
			}
		}
		
		if($("#trustdeed_doc").val()==''){
			if($("#hid_trustdeed_doc").val()==''){
					alert("Please Upload A copy of the society/trust deed in Basic Details Tab ");
					$("#trustdeed_doc").focus();
					return false;
			}
		}
		if($("#furnish_bank_doc").val()==''){
			if($("#hid_furnish_bank_doc").val()==''){
					alert("Please  Kindly Upload the undertaking letter to furnish Bank Guarantee in Basic Details Tab ");
					$("#furnish_bank_doc").focus();
					return false;
			}
		}
		if($("#noc_state_doc").val()==''){
			if($("#hid_noc_state_doc").val()==''){
					alert("Please Kindly Upload the NOC document from the State Govt in Basic Details Tab ");
					$("#noc_state_doc").focus();
					return false;
			}
		}
		if($("#affilat_doc").val()==''){
			if($("#hid_affilat_doc").val()==''){
					alert("Please  Kindly Upload the consent of affiliation document from concern University in Basic Details Tab ");
					$("#affilat_doc").focus();
					return false;
			}
		}
		
		if($("#uni_app_doc").val()==''){
			if($("#hid_uni_app_doc").val()==''){
					alert("Please Upload University Approving Letter in Basic Details Tab ");
					$("#uni_app_doc").focus();
					return false;
			}
		}
		
		if($("#inspection_acade_yr_doc").val()==''){
			if($("#hid_inspection_acade_yr_doc").val()==''){
					alert("Please Upload University Affiliation inspection of Last Academic Year in Basic Details Tab ");
					$("#inspection_acade_yr_doc").focus();
					return false;
			}
		}
		
		
		
		
		
		
	
		var form_data = new FormData(document.getElementById("clg_reg_institute_info"));
		
	 	//	form_data.append("dob",$("#date_of_birth").val());
	 		
			$.ajax({
		        url: 'basics_information_Action?' + key + "=" + value,
		        type: "POST",
		        data: form_data,
		        enctype: 'multipart/form-data',
		        processData: false,
		        contentType: false
		      }).done(function(data) {
		    	  
		    	 alert(data.msg);
		    	  window.location.reload();

	       	
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("Something Went Wrong")
		         
		      }).fail(function(jqXHR, textStatus) {

		      });
	 	
			 
	 }
	
	
function saveDraft_Head_of_Institution_detais() {
		
		debugger;
		$.ajaxSetup({
		    async: false
		});	
		
		var res = CheckNullorBlank('name_pdd');
		if (res !== "true") {
			alert(res +"Name of Principal / Dean / Director  in Registration Number with Registering authority Tab ");
			$('#name_pdd').focus();
			return false;
		}
		var res = CheckNullorBlank('cch_registration');
		if (res !== "true") {
			alert(res +"CCH/NCH Registration Number  in Registration Number with Registering authority Tab ");
			$('#cch_registration').focus();
			return false;
		}
		if ($("#state_rn_head").val() == "0") {
			alert("Please Select State Registration in Registration Number with Registering authority Tab");
			$("#state_rn_head").focus();
			return false;
		}
		var res = CheckNullorBlank('state_registration_no');
		if (res !== "true") {
			alert(res +"Name of The State Registration Number   in Registration Number with Registering authority Tab ");
			$('#state_registration_no').focus();
			return false;
		}
		if( $("#dob_id").val() == "" ||  $("#dob_id").val() == "DD/MM/YYYY"){
			alert("Please Select Date of Birth in Registration Number with Registering authority Tab");
			$("#dob_id").focus();
			return false;
	   	}
		if( $("#doj_id").val() == "" ||  $("#doj_id").val() == "DD/MM/YYYY"){
			alert("Please Select Date of Joining as Principal / Director in Registration Number with Registering authority Tab  ");
			$("#doj_id").focus();
			return false;
	   	}
		var res = CheckNullorBlank('professor');
		if (res !== "true") {
			alert(res +"Professor in Number of Years of Experience Tab ");
			$('#professor').focus();
			return false;
		}
		var res = CheckNullorBlank('reader');
		if (res !== "true") {
			alert(res +"Reader / Associate Professor in Number of Years of Experience Tab ");
			$('#reader').focus();
			return false;
		}
		var res = CheckNullorBlank('lecturer');
		if (res !== "true") {
			alert(res +"Lecturer / Assistant professor in Number of Years of Experience Tab ");
			$('#lecturer').focus();
			return false;
		}
		
		var res = CheckNullorBlank('demonstrator');
		if (res !== "true") {
			alert(res +"Demonstrator/ Tutor  in Number of Years of Experience Tab ");
			$('#demonstrator').focus();
			return false;
		}
		var res = CheckNullorBlank('head_per_add_line1');
		if (res !== "true") {
			alert(res +"Address Line 1  in Head Of Institution Address Details Tab ");
			$('#head_per_add_line1').focus();
			return false;
		}
		var res = CheckNullorBlank('head_per_add_line2');
		if (res !== "true") {
			alert(res +"Address Line 2  in Head Of Institution Address Details Tab ");
			$('#head_per_add_line2').focus();
			return false;
		}
		if ($("#per_state_head_inst").val() == "0") {
			alert("Please Select State  in Head Of Institution Address Details Tab");
			$("#per_state_head_inst").focus();
			return false;
		}
		if ($("#per_district_head_inst").val() == "0") {
			alert("Please Select District  in Head Of Institution Address Details Tab");
			$("#per_district_head_inst").focus();
			return false;
		}
		var res = CheckNullorBlank('head_city');
		if (res !== "true") {
			alert(res +"State  in Head Of Institution Address Details Tab ");
			$('#head_city').focus();
			return false;
		}
		var res = CheckNullorBlank('head_pincode');
		if (res !== "true") {
			alert(res +" Pincode  in Head Of Institution Address Details Tab ");
			$('#head_pincode').focus();
			return false;
		}
		
		
		var ans =  $("#head_pincode").val();
		var minLength = 6;
		 var charLength = ans.length;
		 if(charLength < minLength){
		       	alert(" Please Enter Valid  Six Digit Pincode In Institution Address Details");
					$("input#head_pincode").focus();
					return false;
		       }
		
		
		var res = CheckNullorBlank('head_alt_mo_no1');
		if (res !== "true") {
			alert(res +" Office Phone Number in Head Of Institution Address Details Tab ");
			$('#head_alt_mo_no1').focus();
			return false;
		}
		var res = CheckNullorBlank('head_std');
		if (res !== "true") {
			alert(res +" Office Phone Number code in Head Of Institution Address Details Tab ");
			$('#head_std').focus();
			return false;
		}
		
		var res = CheckNullorBlank('head_alt_mo_no1');
		if (res !== "true") {
			alert(res +" Office Phone Number in Head Of Institution Address Details Tab ");
			$('#head_alt_mo_no1').focus();
			return false;
		}
		
// 		var res = CheckNullorBlank('headre_std');
// 		if (res !== "true") {
// 			alert(res +" Residence Phone Number code in Head Of Institution Address Details Tab ");
// 			$('#headre_std').focus();
// 			return false;
// 		}
// 		var res = CheckNullorBlank('headre_alt_mo_no1');
// 		if (res !== "true") {
// 			alert(res +" Residence Phone Number in Head Of Institution Address Details Tab ");
// 			$('#headre_alt_mo_no1').focus();
// 			return false;
// 		}
		
		var res = CheckNullorBlank('head_alt_mo_no2');
		if (res !== "true") {
			alert(res +" Mobile Number in Head Of Institution Address Details Tab ");
			$('#head_alt_mo_no2').focus();
			return false;
		}
		var res = CheckNullorBlank('head_email');
		if (res !== "true") {
			alert(res +" Email ID in Head Of Institution Address Details Tab ");
			$('#head_email').focus();
			return false;
		}
		
	
		var form_data = new FormData(document.getElementById("head_inst_dtl"));
		
		form_data.append("pernt_id",$("#inst_basic_hidden").val());
		
	 		
			$.ajax({
		        url: 'head_of_Institution_Action?' + key + "=" + value,
		        type: "POST",
		        data: form_data,
		        enctype: 'multipart/form-data',
		        processData: false,
		        contentType: false
		      }).done(function(data) {
		    	  
		    	 alert(data.msg);
		//    	  window.location.reload();

	       	
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("Something Went Wrong")
		         
		      }).fail(function(jqXHR, textStatus) {

		      });
	 	
			 
	 }
	 
	 
function saveDraft_information_connectivity_detais() {
	
	
	$.ajaxSetup({
	    async: false
	});	
	
	
	
	
	
	
	
	var res = CheckNullorBlank('nearest_airport');
	if (res !== "true") {
		alert(res +"Nearest Airport Name in Nearest Airport Details Tab ");
		$('#nearest_airport').focus();
		return false;
	}
	var res = CheckNullorBlank('airport_distance');
	if (res !== "true") {
		alert(res +"Approx Distance in Nearest Airport Details Tab ");
		$('#airport_distance').focus();
		return false;
	}
	
	var res = CheckNullorBlank('nearest_railway');
	if (res !== "true") {
		alert(res +"Nearest Railway Station Name in Nearest Railway Station Details Tab ");
		$('#nearest_railway').focus();
		return false;
	}
	
	var res = CheckNullorBlank('railway_distance');
	if (res !== "true") {
		alert(res +"Approx Distance in Nearest Railway Station Details Tab ");
		$('#railway_distance').focus();
		return false;
	}
	var res = CheckNullorBlank('nearest_institutions');
	if (res !== "true") {
		alert(res +"Nearest Institutions Name in Other Relevant Institutuion Detail within the radius of 50km  Station Details Tab ");
		$('#nearest_institutions').focus();
		return false;
	}
	if ($("#nr_inst_state_ic").val() == "0") {
		alert("Please Select State  in Other Relevant Institution In Important Infornation of Connectivity Tab");
		$("#nr_inst_state_ic").focus();
		return false;
	}
	if ($("#nr_inst_district_ic").val() == "0") {
		alert("Please Select District  in Other Relevant Institution In Important Infornation of Connectivity Tab");
		$("#nr_inst_district_ic").focus();
		return false;
	}
	var res = CheckNullorBlank('inst_city');
	if (res !== "true") {
		alert(res +"City in Other Relevant Institutuion Detail within the radius of 50km  Station Details Tab ");
		$('#inst_city').focus();
		return false;
	}
	
	
	//var pernt_id = $("#inst_basic_hidden").val();

	var form_data = new FormData(document.getElementById("info_of_connectivity"));
	
 		form_data.append("pernt_id",$("#inst_basic_hidden").val());
 		
		$.ajax({
	        url: 'important_information_connectivity_Action?' + key + "=" + value,
	        type: "POST",
	        data: form_data,
	        enctype: 'multipart/form-data',
	        processData: false,
	        contentType: false
	      }).done(function(data) {
	    	  
	    	 alert(data.msg);
	//    	  window.location.reload();

       	
  		}).fail(function(xhr, textStatus, errorThrown) {alert("Something Went Wrong")
	         
	      }).fail(function(jqXHR, textStatus) {

	      });
 	
}
 
 
 
 
function saveDraft_police_station_detais() {
	
	
	$.ajaxSetup({
	    async: false
	});	
	
	
	var res = CheckNullorBlank('nearest_police');
	if (res !== "true") {
		alert(res +"Name of Nearest Police Station In Police Station Details ");
		$('#nearest_police').focus();
		return false;
	}
	
	var res = CheckNullorBlank('police_per_add_line1');
	if (res !== "true") {
		alert(res +"Address Line 1  In Police Station Details ");
		$('#police_per_add_line1').focus();
		return false;
	}
	
	var res = CheckNullorBlank('police_per_add_line2');
	if (res !== "true") {
		alert(res +"Address Line 2  In Police Station Details ");
		$('#police_per_add_line2').focus();
		return false;
	}
	if ($("#police_per_state").val() == "0") {
		alert("Please Select State In Police Station Details ");
		$("#police_per_state").focus();
		return false;
	}
	if ($("#police_per_district").val() == "0") {
		alert("Please Select District In Police Station Details ");
		$("#police_per_district").focus();
		return false;
	}
	
	var res = CheckNullorBlank('police_city');
	if (res !== "true") {
		alert(res +"City In Police Station Details ");
		$('#police_city').focus();
		return false;
	}
	
	var res = CheckNullorBlank('police_per_pincode');
	if (res !== "true") {
		alert(res +"Pincode In Police Station Details ");
		$('#police_per_pincode').focus();
		return false;
	}
	
	var anp =  $("#police_per_pincode").val();
	var minLength = 6;
	 var charLength = anp.length;
	 if(charLength < minLength){
	       	alert(" Please Enter Valid  Six Digit Pin No In Police Station Details");
				$("input#police_per_pincode").focus();
				return false;
	       }
	
	var res = CheckNullorBlank('police_std');
	if (res !== "true") {
		alert(res +"Landline Number code In Police Station Details ");
		$('#police_std').focus();
		return false;
	}
	var res = CheckNullorBlank('police_phone_no');
	if (res !== "true") {
		alert(res +"Landline Number In Police Station Details ");
		$('#police_phone_no').focus();
		return false;
	}
	var res = CheckNullorBlank('sp_office_std');
	if (res !== "true") {
		alert(res +"S.P. Office Number/Landline Number code In Police Station Details ");
		$('#sp_office_std').focus();
		return false;
	}
	var res = CheckNullorBlank('sp_office_phone_no');
	if (res !== "true") {
		alert(res +"S.P. Office Number/Landline Number  In Police Station Details ");
		$('#sp_office_phone_no').focus();
		return false;
	}

	
	
	
	
	//var pernt_id = $("#inst_basic_hidden").val();

	var form_data = new FormData(document.getElementById("police_st_dtl"));
	
	form_data.append("pernt_id",$("#inst_basic_hidden").val());
 		
		$.ajax({
	        url: 'police_station_dtl_Action?' + key + "=" + value,
	        type: "POST",
	        data: form_data,
	        enctype: 'multipart/form-data',
	        processData: false,
	        contentType: false
	      }).done(function(data) {
	    	  
	    	 alert(data.msg);
	    	//  window.location.reload();

       	
  		}).fail(function(xhr, textStatus, errorThrown) {alert("Something Went Wrong")
	         
	      }).fail(function(jqXHR, textStatus) {

	      });
 	
		 
 }
 
 
 //--------------------------------Save draft for details of land---------------------------------
 

function saveDraft_detais_of_land() {
	 
	$.ajaxSetup({
	    async: false
	});	
	 
	var res = CheckNullorBlank('area_of_land ');
	if (res !== "true") {
		alert(res +"Total area of land with Society/Trust/ Govt. College (in acres) In Details of Land ");
		$('#area_of_land ').focus();
		return false;
	}
	if ($("#own_land").val() == "0") {
		alert("Please Select Ownership of land In Details of Land ");
		$("#own_land").focus();
		return false;
	}
	if ($("#land_name").val() == "0") {
		alert("Please Select Land in the name of In Details of Land ");
		$("#land_name").focus();
		return false;
	}
	if ($("#entire_name").val() == "0") {
		alert("Please Select Distribution of entire land In Details of Land ");
		$("#entire_name").focus();
		return false;
	}
	if ($("#land_availability").val() == "0") {
		alert("Please Select Land availability with the Society/Trust In Details of Land ");
		$("#land_availability").focus();
		return false;
	}
	if ($("#stic").val() == "0") {
		alert("Please Select Does the same society/trust In Details of Land ");
		$("#stic").focus();
		return false;
	}
	
	var constructedcheck = $("input[name='constructedcheck']:checked").val();
	if( constructedcheck == null ){
		alert("Please Select Whether College and Hospital are constructed in separate buildings In Details of Land ");
		return false;
   	}
	var buildingcheck = $("input[name='buildingcheck']:checked").val();
	if( buildingcheck == null ){
		alert("Please Select Whether College and Hospital building are in same premises In Details of Land ");
		return false;
   	}
	var res = CheckNullorBlank('clg_land ');
	if (res !== "true") {
		alert(res +"Total area of land allotted to the college (in acres) In Details of Land ");
		$('#clg_land ').focus();
		return false;
	}
	var res = CheckNullorBlank('hospital_land ');
	if (res !== "true") {
		alert(res +"Total area of land allotted to the hospital (in acres) In Details of Land ");
		$('#hospital_land ').focus();
		return false;
	}
	var res = CheckNullorBlank('hostels_land ');
	if (res !== "true") {
		alert(res +"Total area of land allotted to the hostels (in acres) In Details of Land ");
		$('#hostels_land ').focus();
		return false;
	}
	var shiftedcheck = $("input[name='shiftedcheck']:checked").val();
	if( shiftedcheck == null ){
		alert("Please Select Whether the College and Hospital building In Details of Land ");
		return false;
   	}
	var changedcheck = $("input[name='changedcheck']:checked").val();
	if( changedcheck == null ){
		alert("Please Select Whether the management/society of college In Details of Land ");
		return false;
   	}
	if($("#land_doc_clg").val()==''){
		if($("#hid_land_doc_clg").val()==''){
				alert("Please Upload Land documents of the College in Details of Land ");
				$("#land_doc_clg").focus();
				return false;
		}
	}
	if($("#building_plan_doc").val()==''){
		if($("#hid_building_plan_doc").val()==''){
				alert("Please Upload Building plan (approved by the competent authority) in Details of Land ");
				$("#building_plan_doc").focus();
				return false;
		}
	}
	if($("#architect_doc").val()==''){
		if($("#hid_architect_doc").val()==''){
				alert("Please Upload Area statement document certified by Architect in Details of Land ");
				$("#architect_doc").focus();
				return false;
		}
	}
	if($("#balancesheet_doc").val()==''){
		if($("#hid_balancesheet_doc").val()==''){
				alert("Please Upload Kindly Upload the Audited Balance Sheet for last 3 years in Details of Land ");
				$("#balancesheet_doc").focus();
				return false;
		}
	}
// 	if($("#annual_doc").val()==''){
// 		if($("#hid_annual_doc").val()==''){
// 				alert("Please Upload Kindly Upload the Annual Report in Details of Land ");
// 				$("#annual_doc").focus();
// 				return false;
// 		}
// 	}
// 	if($("#add_doc").val()==''){
// 		if($("#hid_add_doc").val()==''){
// 				alert("Please Upload Any additional document in Details of Land ");
// 				$("#hid_add_doc").focus();
// 				return false;
// 		}
// 	}
	
	//var pernt_id = $("#inst_basic_hidden").val();

	var form_data = new FormData(document.getElementById("detail_of_land"));
	
	form_data.append("pernt_id",$("#inst_basic_hidden").val());
 		
		$.ajax({
	        url: 'details_land_Action?' + key + "=" + value,
	        type: "POST",
	        data: form_data,
	        enctype: 'multipart/form-data',
	        processData: false,
	        contentType: false
	      }).done(function(data) {
	    	  
	    	 alert(data.msg);
	    	//  window.location.reload();

       	
  		}).fail(function(xhr, textStatus, errorThrown) {alert("Something Went Wrong")
	         
	      }).fail(function(jqXHR, textStatus) {

	      });
 	
		 
 }
 
// ------------------------Details of Land---------------------
 
 
function saveDraft_undertaking_and_reports() {
	
	
	$.ajaxSetup({
	    async: false
	});	
	
	//var pernt_id = $("#inst_basic_hidden").val();

	var form_data = new FormData(document.getElementById("undertaking_and_reports"));
	
	form_data.append("pernt_id",$("#inst_basic_hidden").val());
 		
		$.ajax({
	        url: 'undertaking_report_Action?' + key + "=" + value,
	        type: "POST",
	        data: form_data,
	        enctype: 'multipart/form-data',
	        processData: false,
	        contentType: false
	      }).done(function(data) {
	    	  
	    	 alert(data.msg);
	    	//  window.location.reload();

       	
  		}).fail(function(xhr, textStatus, errorThrown) {alert("Something Went Wrong")
	         
	      }).fail(function(jqXHR, textStatus) {

	      });
 	
		 
 }
 
 
 
 
			function inyear() {
				 var currentYear = new Date().getFullYear()
				    max = currentYear 
				    var option = "";
				    for (var year = currentYear-10 ; year <= max; year++) {
				        var option = document.createElement("option");
				        option.text = year;
				        option.value = year;	        
				        document.getElementById("passing_year").appendChild(option)	        
				    }
				   // document.getElementById("passing_year").value = currentYear;	
			}
			
			
			
			
			function formOpen_Quali(R){
				
				 $("#qualification_table").show();
					 
					 att=0;
					 $("#quali_add"+R).hide();
					 $("#quali_remove"+R).hide();
					 att=parseInt($("#save_qualih1").val())+1;
					 att= parseInt(R)+1;
					
					 if(att < 21){
						 
				$("input#save_qualih1").val(att);//current serial No
				$("table#qualification_table").append('<tr id="tr_id_quali'+att+'"><td><p>'+att+'</p></td>'
				+'<td><div class="select-style-1 "><div class="select-position"><select name="typeOfDegree'+att+'" id="typeOfDegree'+att+'" class="form-control autocomplete">'
				+'<option value="0">--Select--</option><c:forEach var="item" items="${TypeOfDegree}" varStatus="num">card-style <option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
				+'</c:forEach></select></div><input type="hidden" name="hid_quali'+att+'" id="hid_quali'+att+'" class="form-control" value="0"></div></td>'
				+'<td><div class="input-style-1"><input type="text" name="awarding_authority'+att+'" id="awarding_authority'+att+'" class="form-control" placeholder="Awarding Authority"></div></td>'
				+'<td><div class="input-style-1"><input type="month" name="passsing_yr'+att+'" id="passsing_yr'+att+'" class="form-control" placeholder="Select Year"></div></td><td class="addminusbut">'
				+'<ul class="buttons-group mainbtn daobtn action"><li><a class="main-btn success-btn btn-hover btn-sm btnapprove" value="Save" title="SAVE" id="save_quali'+att+'"><i class="lni lni-checkmark"></i>'												
				+'</a></li><li><a class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none" value="ADD" title="ADD" id="quali_add'+att+'">'
				+'<i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none" value="Delete" title="Delete" id="quali_remove'+att+'"><i class="lni lni-trash-can"></i></a></li>'
				+'<input type="hidden" id="save_qualih'+att+'" name="save_qualih'+att+'" class="form-control autocomplete" value="1">'
				+'<input type="hidden" id="hid_quali_inst'+att+'" name="hid_quali_inst'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
				+'</ul></td></tr>');
						
				addOnclick_quali(att);
						 
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
						$("#family_save_camerah1").val(att);
						
					 }else{
								alert("You can not enter more than hundread times");
								 if ( att == 100){
									 att = att-1; 
									 $("#rp_id_remove"+att).show();
								 }	   
						}
//			 			hidesystem_degree(att);
				}
			
			
			
			function addOnclick_quali(att) {
				
				
				document.getElementById('quali_add'+att).onclick = function() {
					return formOpen_Quali(att);
				};
				document.getElementById('quali_remove'+att).onclick = function() {
					return delete_quali(att);
				};
				document.getElementById('save_quali'+att).onclick = function() {
					saveDraft_Qualification_inst_dtl(att);
				};
				
				
				document.getElementById('awarding_authority'+att).onkeypress = function() {
					return onlyAlphabetsStringSpace(event,this);
				};
				

			}
			
			
			function addOnclick_Intake(att) {
				
				
				document.getElementById('intake_add'+att).onclick = function() {
					return formOpen_Intake_capacity(att);
				};
				document.getElementById('intake_remove'+att).onclick = function() {
					return delete_Intake(att);
				};
				document.getElementById('save_int_cap' + att).onclick = function() {
					Save_As_Draft_Intake(att);
				};
				document.getElementById('intake_capacity'+att).onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('amitted_seat'+att).onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('permitted_seat'+att).onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('sanctioned_seat'+att).onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('govt_quota_ug'+att).onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('mang_quota_ug'+att).onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('last_student'+att).onkeypress = function() {
					return onlyAlphabetsStringSpace(event, this);
				};
				document.getElementById('appeared_stu_ug'+att).onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('passed_stu_ug'+att).onkeypress = function() {
					return isNumberKey0to9(event, this);
				};

			}
			
			
			function addOnclick_Intake_pg(att) {
				
				
				document.getElementById('intake_add_pg'+att).onclick = function() {
					return formOpen_Intake_capacity_PG(att);
				};
				document.getElementById('intake_remove_pg'+att).onclick = function() {
					return delete_Intake_pg(att);
				};
				document.getElementById('save_int_cap_pg' + att).onclick = function() {
					Save_As_Draft_Intake_pg(att);
				};
				document.getElementById('intake_capacity_pg'+ att).onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('admited_seat_pg'+ att).onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('permitted_seat_pg'+ att).onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('sanction_seat_pg'+ att).onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('stu_govt_quota'+ att).onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('stu_mgmt_quota'+ att).onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('last_stu_admitted'+ att).onkeypress = function() {
					return onlyAlphabetsStringSpace(event, this);
				};
				document.getElementById('final_stu_app_exam'+ att).onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				document.getElementById('final_stu_pass_exam'+ att).onkeypress = function() {
					return isNumberKey0to9(event, this);
				};

			}
			
			
			
			
			
		




				function saveDraft_Qualification_inst_dtl(ps) {
	
				
							$.ajaxSetup({
								async : false
							});
							
							
							 for(qua = 1; qua <= $('#save_qualih1').val(); qua++){
							
							if ($("#typeOfDegree"+qua).val() == "0") {
								alert("Please Select Qualification Type Type in "+qua+" row Qualification Tab");
								$("#typeOfDegree"+qua).focus();
								return false;
							}
							
							
							var res = CheckNullorBlank('awarding_authority' + qua);
							if (res !== "true") {
								alert(res + "Awarding Authority in "+qua+" row Qualification Tab ");
								$('#awarding_authority' + qua).focus();
								return false;
							}
							var res = CheckNullorBlank('passsing_yr' + qua);
							if (res !== "true") {
								alert(res + "Passing Year in "+qua+" row Qualification Tab");
								$('#passsing_yr' + qua).focus();
								return false;
							}
							
							
							}	
							 
							var pernt_id = $("#inst_basic_hidden").val();
							
											
							$("#indno_quali").val(ps);
							var form = $("#head_inst_dtl").serialize();
							//form.append("pernt_id",$("#inst_basic_hidden").val());
							$.post(
									'quali_inst_dtl_Action?' + key + "="
											+ value, form, function(j) {
										if(j>0){
											$("#hid_quali"+ps).val(j);
											alert("Data Saved Successfully");
											$("#quali_add" + ps).show();
											$("#quali_remove" + ps).show();
							          }
							          else{
							        	  alert(j);
							          }
								}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
								});
	
}










		
//-----------------------save as Draft for intake capacity-----------------------------


				function Save_As_Draft_Intake(ps) {
	
	
							$.ajaxSetup({
								async : false
							});
							
							
							for(qua = 1; qua <= $('#save_int_caph1').val(); qua++){
								
								if ($("#year"+qua).val() == "0") {
									alert("Please Select Year  in "+qua+" row Intake capacity UG as per order");
									$("#year"+qua).focus();
									return false;
								}
								
								if ($("#per_capacity"+qua).val() == "0") {
									alert("Please Select Permission  in "+qua+" row Intake capacity UG as per order");
									$("#per_capacity"+qua).focus();
									return false;
								}
								var res = CheckNullorBlank('intake_capacity' + qua);
								if (res !== "true") {
									alert(res + "Intake_Capacity in "+qua+" row Intake capacity UG as per order ");
									$('#intake_capacity' + qua).focus();
									return false;
								}
								var res = CheckNullorBlank('amitted_seat' + qua);
								if (res !== "true") {
									alert(res + "Admitted Seat in UG Course ");
									$('#amitted_seat' + qua).focus();
									return false;
								}
								var res = CheckNullorBlank('permitted_seat' + qua);
								if (res !== "true") {
									alert(res + "Permitted Seat in UG Course ");
									$('#permitted_seat' + qua).focus();
									return false;
								}
								var res = CheckNullorBlank('sanctioned_seat' + qua);
								if (res !== "true") {
									alert(res + "Sanctioned Seat in UG Course ");
									$('#sanctioned_seat' + qua).focus();
									return false;
								}
								var res = CheckNullorBlank('govt_quota_ug' + qua);
								if (res !== "true") {
									alert(res + "Total Students admitted with Govt Quota in UG Course");
									$('#govt_quota_ug' + qua).focus();
									return false;
								}
								var res = CheckNullorBlank('mang_quota_ug' + qua);
								if (res !== "true") {
									alert(res + "Total Students admitted with Management Quota in UG Course");
									$('#mang_quota_ug' + qua).focus();
									return false;
								}
								if ($("#court_order"+qua).val() == "0") {
									alert("Please Select Students admitted by Court order in UG Course");
									$("#court_order"+qua).focus();
									return false;
								}
								var res = CheckNullorBlank('last_student' + qua);
								if (res !== "true") {
									alert(res + "Name of the Last Student Admitted in UG Course");
									$('#last_student' + qua).focus();
									return false;
								}
								var res = CheckNullorBlank('last_stu_add_date' + qua);
								if (res !== "true") {
									alert(res + "Date of Admission of the last Student in UG Course");
									$('#last_stu_add_date' + qua).focus();
									return false;
								}
								var res = CheckNullorBlank('appeared_stu_ug' + qua);
								if (res !== "true") {
									alert(res + "Total Final Year students appeared for exams in UG Course");
									$('#appeared_stu_ug' + qua).focus();
									return false;
								}
								var res = CheckNullorBlank('passed_stu_ug' + qua);
								if (res !== "true") {
									alert(res + "Total Final Year students passed out in exams in UG Course");
									$('#passed_stu_ug' + qua).focus();
									return false;
								}
								
								}
							
							
							
							
							
							var pernt_id = $("#inst_basic_hidden").val();
							
											
							$("#indno_intake").val(ps);
							var form = $("#intake_capa").serialize();
							//form.append("pernt_id",$("#inst_basic_hidden").val());
							$.post(
									'intake_capacity_Details_Save_Draft_Action?' + key + "="
											+ value, form, function(j) {
										if(j>0){
											$("#hid_intake"+ps).val(j);
											alert("Data Saved Successfully");
											$("#intake_add" + ps).show();
											$("#intake_remove" + ps).show();
							          }
							          else{
							        	  alert(j);
							          }
								}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
								});
	
}


		function formOpen_Intake_capacity(R) {
				
				
				$("#intakecap_table").show();
				
					att=0;
					$("#intake_add"+R).hide();
				 	$("#intake_remove"+R).hide();
				 	att=parseInt($("#save_int_caph1").val())+1;
				 	att= parseInt(R)+1;
				 	
		if(att < 21){
				 		 
		$("input#save_int_caph1").val(att);//current serial No
		$("table#intakecap_table").append('<tr id="tr_id_intake_cap'+att+'"><td class="sr-no"><p></p></td><td><div class="select-style-1">'
		+'<div class="select-position"><select class="form-control selectCustom" name="year'+att+'" id="year'+att+'"><option value="0" selected="selected">--SelectYear --</option>'
		+'<c:forEach var="item" items="${year}" varStatus="num"><option value="${item[0]}" name="${item[0]}">${item[0]}</option></c:forEach></select></div></div></td>'
		+'<td><div class="select-style-1"><div class="select-position"><select name="per_capacity'+att+'" id="per_capacity'+att+'" class="form-control"><option value="0" selected="selected">--Select --</option>'
		+'<option value="1">Yes</option><option value="2">No</option></select></div></div></td><td><div class="input-style-1"><input type="text" name="intake_capacity'+att+'"id="intake_capacity'+att+'" class="form-control"placeholder="Intake Capacity" maxlength="6"></div></td>'
		
		
		+'<td><div class="input-style-1">'
		+'<input type="text" name="amitted_seat'+att+'" id="amitted_seat'+att+'" class="form-control" placeholder="Admitted Seat" maxlength="6">'
		+'</div></td>'
		
		+'<td><div class="input-style-1">'
		+'<input type="text" name="permitted_seat'+att+'" id="permitted_seat'+att+'" class="form-control" placeholder="Permitted Seat" maxlength="6">'
		+'</div></td>'
		
		+'<td><div class="input-style-1">'
		+'<input type="text" name="sanctioned_seat'+att+'" id="sanctioned_seat'+att+'" class="form-control" placeholder="Sanctioned Seat" maxlength="6">'
		+'</div></td>'
		
		+'<td><div class="input-style-1">'
		+'<input type="text" name="govt_quota_ug'+att+'" id="govt_quota_ug'+att+'" class="form-control" placeholder="Total Students" maxlength="6">'
		+'</div></td>'
		
		+'<td><div class="input-style-1">'
		+'<input type="text" name="mang_quota_ug'+att+'" id="mang_quota_ug'+att+'" class="form-control" placeholder="Total Students" maxlength="6">'
		+'</div></td>'
		
		+'<td><div class="select-style-1"><div class="select-position">'
		+'<select name="court_order'+att+'" id="court_order'+att+'" class="form-control">'
		+'<option value="0" selected="selected">--Select --</option><option value="1">Yes</option><option value="2">No</option></select>'
		+'</div></div></td>'
		
		+'<td><div class="input-style-1">'
		+'<input type="text" name="last_student'+att+'" id="last_student'+att+'" class="form-control" placeholder="Name of the Last Student Admitted" maxlength="100">'
		+'</div></td>'
		
		+'<td><div class="input-style-2"><input type="text" name="last_stu_add_date'+att+'" id="last_stu_add_date'+att+'" '
		+'class="form-control-sm form-control effect-9" aria-required="true" autocomplete="off" placeholder="DD/MM/YYYY">'
		+'</div></td>'
		
		+'<td><div class="input-style-1">'
		+'<input type="text" name="appeared_stu_ug'+att+'" id="appeared_stu_ug'+att+'" class="form-control" placeholder="Total Students" maxlength="6">'
		+'</div></td>'
		
		+'<td><div class="input-style-1">'
		+'<input type="text" name="passed_stu_ug'+att+'" id="passed_stu_ug'+att+'" class="form-control" placeholder="Total Students" maxlength="6">'
		+'</div></td>'
		
		
		
		+'<td class="addminusbut"><ul class="buttons-group mainbtn daobtn action"><li><a class="main-btn success-btn btn-hover btn-sm btnapprove" value="Save" title="SAVE" id="save_int_cap'+att+'">'
		+'<i class="lni lni-checkmark"></i></a></li><li><a class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none" value="ADD" title="ADD" id="intake_add'+att+'">'
		+'<i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none" value="Delete" title="Delete" id="intake_remove'+att+'"><input type="hidden" id="save_int_caph'+att+'" name="save_int_caph'+att+'" class="form-control autocomplete" value="1">'
		+'<input type="hidden" id="hid_intake'+att+'" name="hid_intake'+att+'" value="0" class="form-control autocomplete" autocomplete="off"><i class="lni lni-trash-can"></i></a></li>'
		+'</ul></td></tr>');
		
		
		document.querySelectorAll('.selectCustom').forEach((items, index) => {
			items.addEventListener('change', event => {
				Remove_Expyear("selectCustom","year");
			})
		});
		Remove_Expyear("selectCustom","year");
		
		
		
		datepicketDate('last_stu_add_date'+att);
		 
		addOnclick_Intake(att);
		
				 	 }
	 		 
			}
		
		
		
		function delete_Intake(R) {
			
			// 		var qualtification_idhid2= $("input#qualtification_id"+R).val();
			if(confirm('Are you sure you want to delete?')) {

			var hid_intake = $('#hid_intake' + R).val();
			
			$.post("delete_intake_Capacity?" + key + "=" + value, {
				hid_intake : hid_intake
			}, function(j) {
				alert(j);
			});

			$("tr#tr_id_intake_cap" + R).remove();
			R = R - 1;
		//	 window.location.reload();
			$("input#save_int_caph1").val(att);
			$("#library_add" + R).show();
			$("#library_remove" + R).show();
			}else {
				return false;
			}
		}
		
		
		
		//--------------------Add More Data Fetch Intake Capacity
		
		
	function getIntake_Capacity() {
	debugger;
	$.ajaxSetup({
		async : false
	});
	
	
	var pernt_id = $("#inst_basic_hidden").val();
	
	var ser = 0;

	$.post("getIntake_Cap?" + key + "=" + value, {pernt_id:pernt_id}
		
	, function(j) {
		
		for (var i = 0; i < j.length; i++) {
			ser = i + 1;

			if (j.length > 0) {

				if (ser > 1) {
					
					$("#intake_add" + (ser - 1)).click();
				}
				
				$("#hid_intake" + ser).val(j[i].id);
				$("#year" + ser).val(j[i].year);
				$("#per_capacity" + ser).val(j[i].permission);
				$("#intake_capacity" + ser).val(j[i].intake_capacity);
				
				$("#amitted_seat" + ser).val(j[i].amitted_seat);
				$("#permitted_seat" + ser).val(j[i].permitted_seat);
				$("#sanctioned_seat" + ser).val(j[i].sanctioned_seat);
				$("#govt_quota_ug" + ser).val(j[i].govt_quota_ug);
				$("#mang_quota_ug" + ser).val(j[i].mang_quota_ug);
				$("#court_order" + ser).val(j[i].court_order);
				$("#last_student" + ser).val(j[i].last_student);
				$("#last_stu_add_date" + ser).val(j[i].last_stu_add_date);
				$("#appeared_stu_ug" + ser).val(j[i].appeared_stu_ug);
				$("#passed_stu_ug" + ser).val(j[i].passed_stu_ug);
				
				$("#intake_add" + ser).show();
				$("#intake_remove" + ser).show();
			}
		}
	});
}
		
//-----------------------------------------------Intake Capacity for PG---------------------------------------------------------------------------------------





			function Save_As_Draft_Intake_pg(ps) {
	
	
							$.ajaxSetup({
								async : false
							});
							
							
							
						for(qua = 1; qua <= $('#save_int_caph_pg1').val(); qua++){
								
								if ($("#year_pg"+qua).val() == "0") {
									alert("Please Select Year  in "+qua+" row Intake capacity PG as per order");
									$("#year_pg"+qua).focus();
									return false;
								}
								
								if ($("#per_capacity_pg"+qua).val() == "0") {
									alert("Please Select Permission  in "+qua+" row Intake capacity PG as per order");
									$("#per_capacity_pg"+qua).focus();
									return false;
								}
								var res = CheckNullorBlank('intake_capacity_pg' + qua);
								if (res !== "true") {
									alert(res + "Intake_Capacity in "+qua+" row Intake capacity PG as per order ");
									$('#intake_capacity_pg' + qua).focus();
									return false;
								}
								
								
								
							}
							
							
							
							var pernt_id = $("#inst_basic_hidden").val();
							
											
							$("#indno_intake_pg").val(ps);
							var form = $("#intake_capa").serialize();
							//form.append("pernt_id",$("#inst_basic_hidden").val());
							$.post(
									'intake_capacity_pg_Details_Save_Draft_Action?' + key + "="
											+ value, form, function(j) {
										if(j>0){
											$("#hid_intake_pg"+ps).val(j);
											alert("Data Saved Successfully");
											$("#intake_add_pg" + ps).show();
											$("#intake_remove_pg" + ps).show();
							          }
							          else{
							        	  alert(j);
							          }
								}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
								});
	
}



//---------------------------------------------------------------------------------------------------



		function formOpen_Intake_capacity_PG(R) {
		
				
				$("#intakecap_table_pg").show();
				
					att=0;
					$("#intake_add_pg"+R).hide();
				 	$("#intake_remove_pg"+R).hide();
				 	att=parseInt($("#save_int_caph_pg1").val())+1;
				 	att= parseInt(R)+1;
				 	
		if(att < 21){
				 		 
		 $("input#save_int_caph_pg1").val(att);//current serial No
		$("table#intakecap_table_pg").append('<tr id="tr_id_intake_cap_pg'+att+'"><td class="sr-no"><p></p></td><td><div class="select-style-1">'
		+'<div class="select-position"><select class="form-control selectCustompg" name="year_pg'+att+'" id="year_pg'+att+'"><option value="0" selected="selected">--SelectYear --</option>'
		+'<c:forEach var="item" items="${year}" varStatus="num"><option value="${item[0]}" name="${item[0]}">${item[0]}</option></c:forEach></select></div></div></td>'
		+'<td><div class="select-style-1"><div class="select-position"><select name="per_capacity_pg'+att+'" id="per_capacity_pg'+att+'" class="form-control"><option value="0" selected="selected">--Select --</option>'
		+'<option value="1">Yes</option><option value="2">No</option></select></div></div></td><td><div class="input-style-1"><input type="text" name="intake_capacity_pg'+att+'"id="intake_capacity_pg'+att+'" class="form-control"placeholder="Intake Capacity" maxlength="6"></div></td>'
		+'<td><div class="input-style-1"><input type="text" name="admited_seat_pg'+att+'" id="admited_seat_pg'+att+'" class="form-control" placeholder="Admitted Seat" maxlength="6"></div></td>'
		+'<td><div class="input-style-1"><input type="text" name="permitted_seat_pg'+att+'" id="permitted_seat_pg'+att+'" class="form-control" placeholder="Permitted Seat" maxlength="6"></div></td>'
		+'<td><div class="input-style-1"><input type="text" name="sanction_seat_pg'+att+'" id="sanction_seat_pg'+att+'" class="form-control" placeholder="Sanction Seat" maxlength="6"></div></td>'
		+'<td><div class="input-style-1"><input type="text" name="stu_govt_quota'+att+'" id="stu_govt_quota'+att+'" class="form-control" placeholder="Total Student" maxlength="6"></div></td>'
		+'<td><div class="input-style-1"><input type="text" name="stu_mgmt_quota'+att+'" id="stu_mgmt_quota'+att+'" class="form-control" placeholder="Total Student" maxlength="6"></div></td>'
		+'<td><div class="select-style-1"><div class="select-position"><select name="stu_court_order'+att+'" id="stu_court_order'+att+'" class="form-control"><option value="0" selected="selected">--Select --</option><option value="1">Yes</option><option value="2">No</option></select></div></div></td>'
		+'<td><div class="input-style-1"><input type="text" name="last_stu_admitted'+att+'" id="last_stu_admitted'+att+'" class="form-control" placeholder="Last Student" maxlength="6"></div></td>'
		+'<td><div class="input-style-1"><input type="text" name="last_stu_date_admitted'+att+'" id="last_stu_date_admitted'+att+'" class="form-control-sm form-control effect-9 " aria-required="true" autocomplete="off" value="DD/MM/YYYY"></div></td>'
		+'<td><div class="input-style-1"><input type="text" name="final_stu_app_exam'+att+'" id="final_stu_app_exam'+att+'" class="form-control" placeholder="Final Student" maxlength="6"></div></td>'
		+'<td><div class="input-style-1"><input type="text" name="final_stu_pass_exam'+att+'" id="final_stu_pass_exam'+att+'" class="form-control" placeholder="Final Student Pass" maxlength="6"></div></td>'
		+'<td class="addminusbut"><ul class="buttons-group mainbtn daobtn action"><li><a class="main-btn success-btn btn-hover btn-sm btnapprove" value="Save" title="SAVE" id="save_int_cap_pg'+att+'">'
		+'<i class="lni lni-checkmark"></i></a></li><li><a class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none" value="ADD" title="ADD" id="intake_add_pg'+att+'">'
		+'<i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none" value="Delete" title="Delete" id="intake_remove_pg'+att+'"><input type="hidden" id="save_int_caph_pg'+att+'" name="save_int_caph_pg'+att+'" class="form-control autocomplete" value="1">'
		+'<input type="hidden" id="hid_intake_pg'+att+'" name="hid_intake_pg'+att+'" value="0" class="form-control autocomplete" autocomplete="off"><i class="lni lni-trash-can"></i></a></li>'
		+'</ul></td></tr>');
		 
		 
		document.querySelectorAll('.selectCustompg').forEach((items, index) => {
			items.addEventListener('change', event => {
				Remove_Expyear("selectCustompg","year");
			})
		});
		Remove_ExpyearPG("selectCustompg","year_pg");
		 
		 
		 
		datepicketDate('last_stu_date_admitted'+att+'');
		addOnclick_Intake_pg(att);
		
				 	 }
	 		 
			}

// ---------------------------------delete intake capacity PG
	
	function delete_Intake_pg(R) {
		
		// 		var qualtification_idhid2= $("input#qualtification_id"+R).val();
		if(confirm('Are you sure you want to delete?')) {

		var hid_intake = $('#hid_intake_pg' + R).val();
		
		$.post("delete_intake_Capacity_pg?" + key + "=" + value, {
			hid_intake : hid_intake
		}, function(j) {
			alert(j);
		});

		$("tr#tr_id_intake_cap_pg" + R).remove();
		R = R - 1;
	//	 window.location.reload();
		$("input#save_int_caph_pg1").val(att);
		$("#library_add_pg" + R).show();
		$("#library_remove_pg" + R).show();
		}else {
			return false;
		}
	}








	//--------------------Add More Data Fetch Intake Capacity PG
	
	
	
	function getIntake_Capacity_pg() {
	debugger;
	$.ajaxSetup({
		async : false
	});
	
	
	var pernt_id = $("#inst_basic_hidden").val();
	
	var ser = 0;

	$.post("getIntake_Cap_pg?" + key + "=" + value, {pernt_id:pernt_id}
		
	, function(j) {
		
		for (var i = 0; i < j.length; i++) {
			ser = i + 1;

			if (j.length > 0) {

				if (ser > 1) {
					
					$("#intake_add_pg" + (ser - 1)).click();
				}
				
				$("#hid_intake_pg" + ser).val(j[i].id);
				$("#year_pg" + ser).val(j[i].year);
				$("#per_capacity_pg" + ser).val(j[i].permission);
				$("#intake_capacity_pg" + ser).val(j[i].intake_capacity_pg);
				$("#admited_seat_pg" + ser).val(j[i].admitted_seat);
				$("#permitted_seat_pg" + ser).val(j[i].permitted_seat);
				$("#sanction_seat_pg" + ser).val(j[i].sanction_seat);
				$("#stu_govt_quota" + ser).val(j[i].student_govt_quota);
				$("#stu_mgmt_quota" + ser).val(j[i].student_mgmt_quota);
				$("#stu_court_order" + ser).val(j[i].admitted_court_order);
				$("#last_stu_admitted" + ser).val(j[i].last_stu_admitted);
				$("#last_stu_date_admitted" + ser).val(j[i].date_stu_admitted);
				$("#final_stu_app_exam" + ser).val(j[i].total_stu_app_exam);
				$("#final_stu_pass_exam" + ser).val(j[i].total_stu_pass_exam);
				
				$("#intake_add_pg" + ser).show();
				$("#intake_remove_pg" + ser).show();
			}
		}
	});
}







		
//---------------------------------------------------------------------------------------------------------------------------------------		
	//--------------------Add More Data Fetch quali 
	
	
	function getqualification() {
	debugger;
	$.ajaxSetup({
		async : false
	});
	
	
	var pernt_id = $("#inst_basic_hidden").val();
	
	var ser = 0;

	$.post("getquali?" + key + "=" + value, {pernt_id:pernt_id}
		
	, function(j) {
		
		for (var i = 0; i < j.length; i++) {
			ser = i + 1;

			if (j.length > 0) {

				if (ser > 1) {
					
					$("#quali_add" + (ser - 1)).click();
				}
				
				$("#hid_quali" + ser).val(j[i].id);
				$("#typeOfDegree" + ser).val(j[i].quali_type);
				$("#awarding_authority" + ser).val(j[i].awarding_authority);
				$("#passsing_yr" + ser).val(j[i].passing_year);
				$("#quali_add" + ser).show();
				$("#quali_remove" + ser).show();
			}
		}
	});
}
		
		
		
	function delete_quali(R) {
		
		// 		var qualtification_idhid2= $("input#qualtification_id"+R).val();
		if(confirm('Are you sure you want to delete?')) {

		var hid_quali = $('#hid_quali' + R).val();
		
		$.post("delete_quali?" + key + "=" + value, {
			hid_quali : hid_quali
		}, function(j) {
			alert(j);
		});

		$("tr#tr_id_quali" + R).remove();
		R = R - 1;
	//	 window.location.reload();
		$("input#save_qualih1").val(att);
		$("#quali_add" + R).show();
		$("#quali_remove" + R).show();
		}else {
			return false;
		}
	}
//		function setTimeLoadForTable() {
	
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
	
//	}

function ViewData(id){
	
	$("#id6").val(id);
	$("#id7").val(id);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
}

		
		
			//SAVE Course Intake Capacity 
			function Save_As_Draft_course_intake_cap(sd) {
				
				$.ajaxSetup({
				    async: false
				});	
				
		 		<c:forEach var="j" items="${dataforugsize}" varStatus="num">
				
		 		var res = CheckNullorBlank('intake_cap_course_ug_'+${j[0]});
		 		if (res !== "true") {
		 			alert(res +"Intake Capacity ${j[1]}");
		 			$('#intake_cap_course_ug_'+${j[0]}).focus();
		 			return false;
		 		}
				
		 		</c:forEach>
		 		
		 		<c:forEach var="j" items="${dataforpgsize}" varStatus="num">
				
		 		var res = CheckNullorBlank('intake_cap_course_pg_'+${j[0]});
		 		if (res !== "true") {
		 			alert(res +"Intake Capacity ${j[1]}");
		 			$('#intake_cap_course_pg_'+${j[0]}).focus();
		 			return false;
		 		}
				
		 		</c:forEach>
		 		
		 		
				
				
		 		if($("#denial_doc").val()==''){
		 			if($("#hid_denial_doc").val()==''){
		 					alert("Please Kindly Upload last 10 year AYUSH Permission/Denial in Course Intake Capacity");
		 					$("#denial_doc").focus();
		 					return false;
		 			}
		 		}
		 		
		 		
		 		
		 		if($("#per_gov_doc").val()==''){
		 			if($("#hid_per_gov_doc").val()==''){
		 					alert("Please Kindly Upload the undertaking that Institution will not admit the students without permission from Government of India in Course Intake Capacity");
		 					$("#per_gov_doc").focus();
		 					return false;
		 			}
		 		}
		 		
		 		if($("#cchreg_doc").val()==''){
		 			if($("#hid_cchreg_doc").val()==''){
		 					alert("Please Kindly Upload the undertaking that selection of students for UG and PG Courses will be made only on academic merit as per CCH/NCH regulation in Course Intake Capacity");
		 					$("#per_gov_doc").focus();
		 					return false;
		 			}
		 		}
		 		
		 		
	
				
				var res_hid_lastfiveguide = CheckNullorBlank('hid_lastfiveguide');
				if(res_hid_lastfiveguide !== "true"){
					var res = CheckNullorBlank('lastfiveguide');
					if (res !== "true") {
						alert(res +"List of PG Student Document in Course Intake Capacity");
		 				$('#lastfiveguide').focus();
						return false;
					}
				}
				
				var hid_neet_score = CheckNullorBlank('hid_neet_score');
				if(hid_neet_score !== "true"){
					var res = CheckNullorBlank('neet_score');
					if (res !== "true") {
						alert(res +"Student Details Document in Course Intake Capacity");
		 				$('#neet_score').focus();
						return false;
					}
				}
				
				var hid_undertakingofstudent = CheckNullorBlank('hid_undertakingofstudent');
				if(hid_undertakingofstudent !== "true"){
					var res = CheckNullorBlank('undertakingofstudent');
					if (res !== "true") {
						alert(res +"Upload the Undertaking of the Students in Course Intake Capacity");
		 				$('#undertakingofstudent').focus();
						return false;
					}
				}
				
				var hid_biometricattendance = CheckNullorBlank('hid_biometricattendance');
				if(hid_biometricattendance !== "true"){
					var res = CheckNullorBlank('biometricattendance');
					if (res !== "true") {
						alert(res +"Document of PG Student Attendance in Course Intake Capacity");
		 				$('#biometricattendance').focus();
						return false;
					}
				}
				var intimatedcheck = $("input[name='intimatedcheck']:checked").val();
				if( intimatedcheck == null ){
					alert("Please Select Intimated Check");
					return false;
			   	}
				
				var intimatedcheck = $("input[name='intimatedcheck']:checked").val();
				if(intimatedcheck == "1"){
				var undertakingcheck = $("input[name='undertakingcheck']:checked").val();
				if( undertakingcheck == null ){
					alert("Please Select Under Taking Check");
					return false;
			   	}
				}
				
			 	
			
				var form_data = new FormData(document.getElementById("intake_course_cap"));
		
				$.ajax({
					url : 'Couse_intake_cap_Save_Draft_Action?_csrf=' + value,
					type : "POST",
					data : form_data,
					enctype : 'multipart/form-data',
					processData : false,
					contentType : false
				}).done(function(j){
		          if(j>0){
		     
		       // 	if(sd != -1){
		        	  alert("Data Save Sucessfully");
		       // 	}
		       // 	  location.reload();
		          }
		          else{
		        	  if(sd != -1){
		        	  alert(j);
		        	  }
		          }
				}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
				});

					
			 }
			
			
			//FETCH COURSE INTAKE CAP PG
			function getCouurse_Intake_Cap_ug() {
				debugger;
				$.ajaxSetup({
					async : false
				});
				
				$.post("getCourse_cap_intake_ug?" + key + "=" + value, {
					
				}, function(j) {
					
					for (var i = 0; i < j.length; i++) {
								
						$("#hid_course_ug_"+j[i].course_id).val(j[i].id);
				//		alert(j[i].course_id);
						$("#intake_cap_course_ug_"+j[i].course_id).val(j[i].intake_capacity);
				//		alert(j[i].intake_capacity);
						
					}
					
				});
			}
			
			
			
			
			
			//FETCH COURSE INTAKE CAP PG
			function getCouurse_Intake_Cap_pg() {
				debugger;
				$.ajaxSetup({
					async : false
				});
				
				$.post("getCourse_cap_intake_pg?" + key + "=" + value, {
					
				}, function(j) {
					
					for (var i = 0; i < j.length; i++) {
						
						
						$("#hid_course_pg_"+j[i].course_id).val(j[i].id);
						$("#intake_cap_course_pg_"+j[i].course_id).val(j[i].intake_capacity);
						
					}
					
				});
			}
			
			
		//FINAL SUBMIT	
			
			function final_submit() {
			
				debugger;
				$.ajaxSetup({
					async : false
				});
				
				var checkBox = document.getElementById("Declaration");
				if (checkBox.checked == false){	 
					
					//$("#hiddenUpdate").val(0);
						  alert("Please Accept Terms And Condition");
						  return false;
					}
				
				
				$.post("final_submit_action?" + key + "=" + value, {
					
				}, function(j) {
					
					alert("Form Submit Successfully");
					
				
					
				});
			}
		
		
		
			function getfacultydtl() {
				
				$.ajaxSetup({
					async : false
				});
				var teachercode = $("#teacher_code").val();
				
				if(teachercode != "" && teachercode != null){
					 $.post("getinfofromteacher_ctrl?" + key + "=" + value,{teachercode : teachercode},
								function(j) {
						 
						// alert(j);
						 for (var i = 0; i < j.length; i++) {
							 
						 
						 $("#head_per_add_line1").val(j[i][1]);
						 $("#head_per_add_line2").val(j[i][2]);
						 $("#per_state_head_inst").val(j[i][4]);
						 $("#head_city").val(j[i][3]);
						 $("#head_pincode").val(j[i][5]);
						 $("#head_alt_mo_no1").val(j[i][6]);
						 $("#head_alt_mo_no2").val(j[i][8]);
						 $("#head_email").val(j[i][7]);
						 $("#name_pdd").val(j[i][12]);
						 $("#cch_registration").val(j[i][15]);
						 var state_reg = j[i][10];
							if(state_reg != "" && state_reg != null){
						 $("#state_rn_head").val(j[i][10]);
						//if(j[i][11])
							}
						 $("#state_registration_no").val(j[i][11]);
							
						 $("#dob_id").val(j[i][13]);
						 var doj = j[i][14];
							if(doj != "" && doj != null){
						 $("#doj_id").val(j[i][14]);
							}
 
						 
						 }			
					});
				}
			}
		
		
			function isValidUrl(str) {
				  const pattern = new RegExp(
				    '^([a-zA-Z]+:\\/\\/)?' + // protocol
				      '((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|' + // domain name
				      '((\\d{1,3}\\.){3}\\d{1,3}))' + // OR IP (v4) address
				      '(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*' + // port and path
				      '(\\?[;&a-z\\d%_.~+=-]*)?' + // query string
				      '(\\#[-a-z\\d_]*)?$', // fragment locator
				    'i'
				  );
				  return pattern.test(str);
				}
			
			
// 			function yearremove(event,val) {
// 				debugger;
				
// 				alert("hiiiii");
// 				var sele_temp="";
// 				if(event.target.value=='0'){
// 					sele_temp="<option value='"+event.target.value"' name='"+event.target.value"'>'"+event.target.value"'</option>
// 				}
// 			}

		
//INTERNSIP DETAILS

	function Migration_Yes(){
		
		var migrationcheck = $('input:radio[name=migrationcheck]:checked').val();
		if(migrationcheck == "1"){
	        $("div#hid_migrationcheck").show();
		}
		else if(migrationcheck == "2"){
	        $("div#hid_migrationcheck").hide();
		}
	}	
// 	--------------------------------------------START-->INTERNSHIP DETAILS---------------------------------------------
	
	//SAVE INTERNSHIP DETAILS
	function Save_As_Draft_Internship_Details() {
	
	$.ajaxSetup({
		async : false
	});
	
	var pro_regcheck = $("input[name='pro_regcheck']:checked").val();
	if( pro_regcheck == null ){
		alert("Please Select Provisional Registration");
		return false;
   	}
	var rotationcheck = $("input[name='rotationcheck']:checked").val();
	if( rotationcheck == null ){
		alert("Please Select Rotation Programme");
		return false;
   	}
//		var res = CheckNullorBlank('internsopd');
//		if (res !== "true") {
//			alert(res +"Total Intern in O.P.D");
//			$('#internsopd').focus();
//			return false;
//		}
	var res = CheckNullorBlank('internsdutyopd');
	if (res !== "true") {
		alert(res +"Interns Duty in O.P.D");
		$('#internsdutyopd').focus();
		return false;
	}
//		var res = CheckNullorBlank('internsipd');
//		if (res !== "true") {
//			alert(res +"Total Intern in IPD");
//			$('#internsipd').focus();
//			return false;
//		}
	var res = CheckNullorBlank('internsdutyipd');
	if (res !== "true") {
		alert(res +"Interns Duty in IPD");
		$('#internsdutyipd').focus();
		return false;
	}
	var migrationcheck = $("input[name='migrationcheck']:checked").val();
	if( migrationcheck == null ){
		alert("Please Select Migration of Student from/to College");
		return false;
   	}
	
	if(migrationcheck == "1"){
	var hid_migration_list = CheckNullorBlank('hid_migration_list');
		if(hid_migration_list !== "true"){
			var res = CheckNullorBlank('migration_list');
			if (res !== "true") {
			alert(res +"Migration List");
			$('#migration_list').focus();
			return false;
			}
		}
	}
	var res = CheckNullorBlank('prescribe');
	if (res !== "true") {
		alert(res +"Prescribe Medicine");
		$('#prescribe').focus();
		return false;
	}
	var res = CheckNullorBlank('seminar');
	if (res !== "true") {
		alert(res +"Seminar for Internee");
		$('#seminar').focus();
		return false;
	}
	var res = CheckNullorBlank('internship_not_completed');
	if (res !== "true") {
		alert(res +"Number of Students who have not Completed Internship");
		$('#internship_not_completed').focus();
		return false;
	}
	var res = CheckNullorBlank('house_job');
	if (res !== "true") {
		alert(res +"House Job");
		$('#house_job').focus();
		return false;
	}
	var res = CheckNullorBlank('no_house_job');
	if (res !== "true") {
		alert(res +"Number of Students undergoing House Job");
		$('#no_house_job').focus();
		return false;
	}
	var res = CheckNullorBlank('graded_teaching');
	if (res !== "true") {
		alert(res +"Graded Teaching Work");
		$('#graded_teaching').focus();
		return false;
	}
	
	var form_data = new FormData(document.getElementById("internship_details"));
	$.ajax({
		url : 'Internship_Details_Save_Draft_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j) {
				if(j>0){
					$("#hid_internship_details").val(j);
					alert("Data Saved Successfully");
	          }
	          else{
	        	  alert(j);
	          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	
	}


	//FETCH INTERNSHIP DETAILS
	function getInternship_Details() {
		$.ajaxSetup({
			async : false
		});
		
		$.post("getInternship_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			
			$("#hid_total_intern").val(j[0].id);
			$("#hid_internship_details").val(j[0].id);
			$("#hid_total_intern").val(j[0].total_intern);
			$("#internsdutyopd").val(j[0].internsdutyopd);
			$("#internsdutyipd").val(j[0].internsdutyipd);
			$("#hid_migration_list").val(j[0].migration_list);
			$("#prescribe").val(j[0].prescribe);
			$("#seminar").val(j[0].seminar);
			$("#internship_not_completed").val(j[0].internship_not_completed);
			$("#house_job").val(j[0].house_job);
			$("#no_house_job").val(j[0].no_house_job);
			$("#graded_teaching").val(j[0].graded_teaching);
			
			var pro_regcheck = j[0].pro_regcheck;
			$("input[name='pro_regcheck'][value='"+pro_regcheck+"']").prop('checked', true);
			
			var rotationcheck = j[0].rotationcheck;
			$("input[name='rotationcheck'][value='"+rotationcheck+"']").prop('checked', true);
			
			var migrationcheck = j[0].migrationcheck;
			$("input[name='migrationcheck'][value='"+migrationcheck+"']").prop('checked', true);
			if(migrationcheck == "1"){
				$("div#hid_migrationcheck").show();
			}
			
		});
	}

	function Intimated_Yes(){
		
		var intimatedcheck = $('input:radio[name=intimatedcheck]:checked').val();
		if(intimatedcheck == "1"){
	        $("div#hid_undertakingcheck").show();
		}
		else if(intimatedcheck == "2"){
	        $("div#hid_undertakingcheck").hide();
		}
	}

// 	--------------------------------------------END-->INTERNSHIP DETAILS---------------------------------------------


// 	--------------------------------------------START-->UPLOAD DOCUMENT DETAILS---------------------------------------------
	
	//SAVE TEACHERS DETAILS
	function Save_As_Draft_Document_Details(sd) {
		
		$.ajaxSetup({
		    async: false
		});	

		var hid_full_time_teacher_affidavit = CheckNullorBlank('hid_full_time_teacher_affidavit');
		if(hid_full_time_teacher_affidavit !== "true"){
			var res = CheckNullorBlank('full_time_teacher_affidavit');
			if (res !== "true") {
				alert("Please Upload Scan copy of duly notarized affidavits of place of working by all the full time teachers");
				$('#full_time_teacher_affidavit').focus();
				return false;
			}
		}
		var hid_guest_teacher_affidavit = CheckNullorBlank('hid_guest_teacher_affidavit');
		if(hid_guest_teacher_affidavit !== "true"){
			var res = CheckNullorBlank('guest_teacher_affidavit');
			if (res !== "true") {
				alert("Please Upload Scan copy of duly notarized affidavits of place of working by all the Guest faculty teachers");
				$('#guest_teacher_affidavit').focus();
				return false;
			}
		}
		
		var form_data = new FormData(document.getElementById("document_detail"));
		$.ajax({
			url : 'Document_Details_Save_Draft_Action?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(j){
          if(j>0){
        	  $("#hid_document_detail").val(j);
        	  $("#hid_declaration_detail").val(j);
        	  alert("Data Save Sucessfully");
        	  location.reload();
          }
          
          else{
        	  if(sd != -1){
        	  alert(j);
        	  }
        	  location.reload();
          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
			
	 }
	 
	//FETCH GUEST TEACHERS DETAILS
	function getDocument_Details() {
		$.ajaxSetup({
			async : false
		});
		
		$.post("getDocument_Details?" + key + "=" + value, {
			
		}, function(j) {
				
			$("#hid_document_detail").val(j[0].id);
			$("#hid_full_time_teacher_affidavit").val(j[0].full_time_teacher_affidavit);
			$("#hid_guest_teacher_affidavit").val(j[0].guest_teacher_affidavit);
			$("#hid_miscellaneous_doc").val(j[0].miscellaneous_doc);
			
			
		});
	}
	 
// 	--------------------------------------------END-->UPLOAD DOCUMENT DETAILS---------------------------------------------




function getDistrictforIbAdd() {
	$.ajaxSetup({
		async : false
	});
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






function getDistrictforIBMC() {
	$.ajaxSetup({
		async : false
	});
	var selval = $("#mang_per_state").val();
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
						$("select#mang_per_district").html(options);
					});
}

function getDistrictforHIAdd() {
	$.ajaxSetup({
		async : false
	});
	var selval = $("#per_state_head_inst").val();
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
						$("select#per_district_head_inst").html(options);
					});
}



function getDistrict() {
	$.ajaxSetup({
		async : false
	});
	var selval = $("#nr_inst_state_ic").val();
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
						$("select#nr_inst_district_ic").html(options);
					});
}


function getDistrictForPolice() {
	$.ajaxSetup({
		async : false
	});
	var selval = $("#police_per_state").val();
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
						$("select#police_per_district").html(options);
					});
}



function mobileNumber(obj){
	if(obj.value.length<10){
		 alert('Please Enter valid Number');
	        $('#'+obj.id).focus();
	        $('#'+obj.id).val("");
	        return false;
	}
	_mobile = obj.value;
	
	var regExp =/^[6789]\d{9}$/;
    if(_mobile == '' || !regExp.test(_mobile)){
        alert('Please Enter Number Start with 6,7,8,9 Digit');
        $('#'+obj.id).focus();
        $('#'+obj.id).val("");
        return false;
    }
}



function checkgmail(email1) {
	
	 document.getElementById("email").innerHTML="";
	var pattern=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	
//	 var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	 
	if(email1.match(pattern)) {
	}else{
		
		alert("Please Enter Valid Email Address");
		$("input#email").focus();
		$("input#email").val('');
		return false ;
	}
}




function Remove_Expyear(remove_expyear,year)
{
	const collection = document.getElementsByClassName(remove_expyear);
	var temp=0;
	var count=1;
	var list=[];
	for (let i = 0; i < collection.length; i++) {
		const collection_inner = document.getElementsByClassName(remove_expyear);
		temp=0;
		count=1;
		//const list=[];
		for (let j = 0; j < collection_inner.length; j++) {
				if(collection_inner[j].value=='0' && collection[i].value!='0'){
					if(collection[i].value!=0){
					list.push(collection[i].value);
					}
					$("#year"+count+" option[value='"+collection[i].value+"']").remove();
				}
				if(collection[i].value!=collection_inner[j].value && collection_inner[j].value!='0' ){
					if(collection[i].value!=0){
						list.push(collection[i].value);
						}
					$("#"+year+""+count+" option[value='"+collection[i].value+"']").remove();
				}
				temp++;
			count++;
		}
		
		}

	
}

function Remove_ExpyearPG(remove_expyear,year_pg)
{
	const collection = document.getElementsByClassName(remove_expyear);
	var temp=0;
	var count=1;
	var list=[];
	for (let i = 0; i < collection.length; i++) {
		const collection_inner = document.getElementsByClassName(remove_expyear);
		temp=0;
		count=1;
		//const list=[];
		for (let j = 0; j < collection_inner.length; j++) {
				if(collection_inner[j].value=='0' && collection[i].value!='0'){
					if(collection[i].value!=0){
					list.push(collection[i].value);
					}
					$("#year_pg"+count+" option[value='"+collection[i].value+"']").remove();
				}
				if(collection[i].value!=collection_inner[j].value && collection_inner[j].value!='0' ){
					if(collection[i].value!=0){
						list.push(collection[i].value);
						}
					$("#"+year_pg+""+count+" option[value='"+collection[i].value+"']").remove();
				}
				temp++;
			count++;
		}
		
		}

	
}


// document.querySelectorAll('.remove_expyear').forEach((items, index) => {
// 	items.addEventListener('change', event => {
// 		Remove_Expyear("remove_expyear","expyear");
// 	})
// });

function setTimeLoadForTable(){
	
	document.querySelectorAll('.selectCustom').forEach((items, index) => {
					items.addEventListener('change', event => {
						Remove_Expyear("selectCustom","year");
					})
				});
	
	document.querySelectorAll('.selectCustompg').forEach((items, index) => {
		items.addEventListener('change', event => {
			Remove_ExpyearPG("selectCustompg","year_pg");
		})
	});
	

}
</script>


