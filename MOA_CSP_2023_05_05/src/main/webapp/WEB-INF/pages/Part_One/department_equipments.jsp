<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
							<h2>Department Equipments</h2>
						</div>
					</div>
					<!-- end col -->
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="breadcrumb-wrapper mb-30">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>

									<li class="breadcrumb-item active" aria-current="page">
										Department Equipments</li>
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
										<label>Institution Name :</label> <span class="value-bind">Test
											Homoeopathic Medical College and Hospital-JHARKHAND</span>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 col-12">
									<div class="custom-data-value custom-data-value-form">
										<label>College Code :</label> <span class="value-bind">JH005</span>
									</div>

								</div>

							</div>
						</div>
					</div>


					<!--    ===========================
		Department Equipments Start
===========================     -->
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<!-- input style start -->
						<div class="card-style mb-30">
							<div class="tabs content_h1100">
							
								
							
							<!-- DEPARTMENT OF OBSTETRIC & GYNAECOLOGY START -->
							<div class="tab" id="obstetric_gynac_dtl">
									<button class="tab-toggle">Obstetric & Gynacology</button>
								</div>
								<div class="content">
									<h4 class="heading">Obstetric & Gynacology</h4>
									<form:form name="obstetric_gynacology_form" id="obstetric_gynacology_form" action="" method="post" 
										commandName="">
										<div class="row">
										<input type="hidden" id="basic_info_id" name="basic_info_id" value="0">
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Number of Equipments for Identification<strong
														class="mandatory">*</strong></label> <input type="text"
														id="og_equipments" name="og_equipments"
														class="form-control"
														placeholder="Number of Equipments for Identification" maxlength="10">
														
														<input type="hidden" id="deprt_equip_hidden"
																		name="deprt_equip_hidden" value="0"
																		class="form-control ">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Additional Items<strong class="mandatory">*</strong></label>
													<input type="text" id="og_additionalitem"
														name="og_additionalitem" class="form-control"
														placeholder="Total Additional Items" maxlength="250">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label for="fname">Copy of Alcohol License, Anatomy
														Act<strong class="mandatory">*</strong></label> <input type="file" id="og_alco_lice_doc"
														name="og_alco_lice_doc" class="form-control" accept=".pdf">
												<input type="hidden" id="hid_og_alco_lice" name="hid_og_alco_lice" class="form-control" value="">
												
												<div class="note-text">
																	<span class="errorClass" id="og_alco_lice_doc">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="og_alco_lice_doctik"></span>
															</div>
												
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Photographs of Cadavers & Mummified Bodies<strong
														class="mandatory">*</strong></label> <input type="file"
														id="og_photo_cm_doc" name="og_photo_cm_doc" class="form-control" accept=".pdf">
												<input type="hidden" id="hid_og_photo_cm" name="hid_og_photo_cm" class="form-control" value="">
												
												<div class="note-text">
																	<span class="errorClass" id="og_photo_cm_doc">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="og_photo_cm_doctik"></span>
															</div>	
												
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Upload Purchase Bill of
														Departmental Equipments<strong class="mandatory">*</strong>
													</label> <input type="file" id="og_bill_equipment_doc"
														name="og_bill_equipment_doc" class="form-control" accept=".pdf">
													<input type="hidden" id="hid_og_bill_equipment" name="hid_og_bill_equipment" class="form-control" value="">
													
													<div class="note-text">
																	<span class="errorClass" id="og_bill_equipment_doc">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="og_bill_equipment_doctik"></span>
															</div>		
													
												</div>
											</div>
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover" id="save_obstetric"
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
							
							
								<!-- DEPARTMENT OF ANATOMY START -->
								<div class="tab" id="anatomy_dtl">
									<button class="tab-toggle" id="defaultOpen">Anatomy</button>
								</div>
								<div class="content">
									<h4 class="heading">Anatomy</h4>
									<form:form name="anatomy_form" id="anatomy_form" action="" method="post" > 
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="anatomy_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>Items</h6></th>
																<th><h6>
																		Available(Number/ Quantity)<strong class="mandatory">*</strong>
																	</h6></th>
															</tr>
														</thead>
														<tbody id="">
														${dataforAnatomy}
                                                             <!--	end table row -->
														</tbody>
													</table>
												</div>
												<hr>
											</div>


											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Total Additional Items<strong
														class="mandatory">*</strong></label> <input type="text"
														id="ana_add_item" name="ana_add_item"
														placeholder="Total Additional Items" maxlength="10">
														
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label for="fname">Total Cadavers Available<strong
														class="mandatory">*</strong></label> <input type="text"
														name="ana_cada_ava" id="ana_cada_ava" class="form-control"
														placeholder="Total Cadavers Available" maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="select-style-1">
													<label>Whether College covered under the provisions
														of Anatomy Act.<strong class="mandatory">*</strong>
													</label>
													<div class="select-position">
														<select name="anatomy_act" id="anatomy_act"
															class="form-control">
															<option value="0" selected="selected">-- Select
																--</option>
															<option value="1">Yes</option>
															<option value="2">No</option>
														</select>
													</div>
												</div>
												<input type="hidden" id="hid_ana_child" name="hid_ana_child" class="form-control" value="0">
											</div>
											
												
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover" id="save_anatomy"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>

									</form:form>
								</div>
								<!-- DEPARTMENT OF ANATOMY END -->
								<!-- DEPARTMENT OF COMMUNITY MEDICINE START -->
								<div class="tab" id="community_med_dtl">
									<button class="tab-toggle">Community Medicine</button>
								</div>
								<div class="content">
									<h4 class="heading">Community Medicine</h4>
									<form:form name="community_form" id="community_form" action="" method="post" 
										commandName="">
										<div class="row">

											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="comm_med_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>Items</h6></th>
																<th><h6>
																		Available(Number/ Quantity)<strong class="mandatory">*</strong>
																	</h6></th>
															</tr>

														</thead>
														<tbody id="">
														${datacommunity}

															<!-- end table row -->
														</tbody>
													</table>
												</div>
												<hr>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label for="fname">Family Welfare devices<strong
														class="mandatory">*</strong></label> <input type="text"
														name="comm_welfaredevices" id="comm_welfaredevices"
														class="form-control" 
														placeholder=" Family Welfare devices" maxlength="250">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label for="fname">Sources of Nutrition<strong
														class="mandatory">*</strong></label> <input type="text"
														name="comm_src_nutrition" id="comm_src_nutrition"
														class="form-control"
														placeholder=" Sources of Nutrition" maxlength="250">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label for="fname">Sources of Vitamins<strong
														class="mandatory">*</strong></label> <input type="text"
														name="comm_src_vitamins" id="comm_src_vitamins"
														class="form-control" 
														placeholder=" Sources of Vitamins" maxlength="250">
												</div>
											</div>
										</div>
										
										<input type="hidden" id="hid_cm_child" name="hid_cm_child" class="form-control" value="0">	
										
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover" id="save_cm"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									
									</form:form>
								</div>
								<!-- DEPARTMENT OF COMMUNITY MEDICINE END -->
								<!-- DEPARTMENT OF FORENSIC MEDICINE & TOXICOLOGY START -->
								<div class="tab" id="forensic_med_dtl">
									<button class="tab-toggle">Forensic Medicine &
										Toxicology</button>
								</div>
								<div class="content">
									<h4 class="heading">Forensic Medicine & Toxicology</h4>
									<form:form name="forensic_eq" id="forensic_eq" action="" method="post" class=""
										commandName="">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<div class="accordion accordion-flush"
													id="accordionFlushExample">
													<div class="accordion-item accordion-itemflush">
														<h2 class="accordion-header" id="flush-headingOne">
															<button class="accordion-button accordion-secondary-btn"
																type="button" data-bs-toggle="collapse"
																data-bs-target="#flush-collapseOne" aria-expanded="true"
																aria-controls="flush-collapseOne">Equipments
																Details</button>
														</h2>
														<div id="flush-collapseOne"
															class="accordion-collapse collapse show"
															aria-labelledby="flush-headingOne"
															data-bs-parent="#accordionFlushExample">
															<div class="accordion-body">
																<div class="row">
																	<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																		<div
																			class="table-wrapper table-responsive custom-table">
																			<table class="table" id="fometo_table">
																				<thead>
																					<tr>
																						<th><h6>Sr No</h6></th>
																						<th><h6>Items</h6></th>
																						<th><h6>
																								Available(Number/ Quantity)<strong
																									class="mandatory">*</strong>
																							</h6></th>
																					</tr>

																				</thead>
																				<tbody id="">
																				${dataforensic_equipments}
																					<!-- end table row -->
																				</tbody>
																			</table>
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
																data-bs-target="#flush-collapseTwo"
																aria-expanded="false" aria-controls="flush-collapseTwo">Acts
																/ Legislations (Including Medico Legal) & Regulations
																Available.</button>
														</h2>
														<div id="flush-collapseTwo"
															class="accordion-collapse collapse"
															aria-labelledby="flush-headingTwo"
															data-bs-parent="#accordionFlushExample">
															<div class="accordion-body">
																<div class="row">
																	<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																		<div
																			class="table-wrapper table-responsive custom-table">
																			<table class="table" id="actfometo_table">
																				<thead>
																					<tr>
																						<th><h6>Sr No</h6></th>
																						<th><h6>Items</h6></th>
																						<th><h6>
																								Available(Number/ Quantity)<strong
																									class="mandatory">*</strong>
																							</h6></th>
																					</tr>

																				</thead>
																				<tbody id="">
																				${dataforensic_act}
																					<!-- end table row -->
																				</tbody>
																			</table>
																		</div>
																	</div>
																</div>
																<!-- end row -->
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
														<li><input class="main-btn info-btn btn-hover" id="save_forensic"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- DEPARTMENT OF FORENSIC MEDICINE & TOXICOLOGY END -->
								<!-- DEPARTMENT OF HOMOEOPATHIC PHARMACY START -->
								<div class="tab" id="homeo_pharm_dtl">
									<button class="tab-toggle">Homeopathic Pharmacy</button>
								</div>
								<div class="content">
									<h4 class="heading">Homeopathic Pharmacy</h4>
									<form:form name="homeo_ph" id="homeo_ph" action="" method="post" class=""
										commandName="">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="hoph_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>Items</h6></th>
																<th><h6>
																		Available(Number/ Quantity)<strong class="mandatory">*</strong>
																	</h6></th>
															</tr>

														</thead>
														<tbody id="">
														${datahomeophatic_pharmacy}
															<!-- end table row -->
														</tbody>
													</table>
												</div>
											</div>

										</div>
										<hr>
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<h4 class="heading">Herbal Garden</h4>
												
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label for="fname">Number of Species Planted<strong
														class="mandatory">*</strong></label> <input type="text"
														name="hergar_soecies" id="hergar_soecies"
														class="form-control"
														placeholder="Number of Species Planted" maxlength="10">
														
													<input type="hidden" id="hid_ph_child" name="hid_ph_child" class="form-control" value="0">	
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label for="fname">Plants on Pots<strong
														class="mandatory">*</strong></label> <input type="text"
														name="hergar_plantpot" id="hergar_plantpot"
														class="form-control" placeholder="Total Plants on Pots" maxlength="250">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<label for="fname">Irrigation Facility<strong
													class="mandatory">*</strong>
												</label>
												<div class="input-style-form-check">

													<div class="form-check radio-style">
														<input type="radio" id="hergarirrigation_Yes"
															name="hergarirrigationcheck" class="form-check-input"
															value="0" > <label for="hergarirrigation_Yes"
															class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="hergarirrigation_No"
															name="hergarirrigationcheck" class="form-check-input"
															value="1"> <label for="hergarirrigation_No"
															class="form-check-label">No</label>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<label for="fname">Educational Tour Conducted<strong
													class="mandatory">*</strong>
												</label>
												<div class="input-style-form-check">

													<div class="form-check radio-style">
														<input type="radio" id="hergar_edu_tour_Yes"
															name="hergar_edu_tourcheck" class="form-check-input"
															value="0"> <label for="hergar_edu_tour_Yes"
															class="form-check-label">Yes</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" id="hergar_edu_tour_No"
															name="hergar_edu_tourcheck" class="form-check-input"
															value="1"> <label for="hergar_edu_tour_No"
															class="form-check-label" >No</label>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover" id="save_Hpharma"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</form:form>
								</div>
								<!-- DEPARTMENT OF HOMOEOPATHIC PHARMACY END -->
								<!-- DEPARTMENT OF ORGANON OF MEDICINE START -->
								<div class="tab" id="organon_med_dtl">
									<button class="tab-toggle">Organon of Medicine</button>
								</div>
								<div class="content">
									<h4 class="heading">Organon of Medicine</h4>
									<form:form name="organ_med" id="organ_med" action="" method="post" class=""
										commandName="" enctype="multipart/form-data">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="organmed_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>Founder & Philosophers Name<strong class="mandatory">*</strong></h6></th>
																<th><h6>Photographs<strong class="mandatory">*</strong></h6></th>
																<th><h6>Date of Birth<strong class="mandatory">*</strong></h6></th>
																<th><h6>Date of Death<strong class="mandatory">*</strong></h6></th>
																<th><h6>Available(Number/ Quantity)<strong class="mandatory">*</strong></h6></th>
																<th><h6>Action</h6></th>
															</tr>

														</thead>
														<tbody id="">
														<tr id="tr_sibling_Organon1">
															<td>1</td>
														<td class="min-width">
														<div class="input-style-1">
															<input type="text" id="founder_name1"
																name="founder_name1" 
																class="form-control" autocomplete="off"
																placeholder="Founder & Philosophers Name" maxlength="50">
														</div>
													   </td>
													   
													   <td class="min-width">
														<div class="input-style-1">
																	<input type="file" accept="image/*" id="photo_path1" name="photo_path1"  class="form-control">
<!-- 															<label  id = "img_up">  <strong   class="mandatory"> Image Not Uploaded </strong> </label>  -->
															
															<input type="hidden" id="upload_img_hid1" name="upload_img_hid" class="form-control">
															 <input type="hidden" id="upload_img_forV1" name="upload_img_forV" class="form-control" value="0">
															<span class="errorClass" id="upload_photo_doc_lbl1"></span> 

																</div>
													   </td>
													   
													    
													   <td class="min-width">
														<div class="input-style-1">
																	<input type="text" name="date_of_birth1"
																		id="date_of_birth1"
																		class="form-control-sm form-control custom_attend_exp_excl"
																		aria-required="true" autocomplete="off"
																		value="DD/MM/YYYY"
																		placeholder="Date Of Birth">
																</div>
													   </td>
													   
													     <td class="min-width">
														<div class="input-style-1">
																	<input type="text" name="date_of_death1"
																		id="date_of_death1"
																		class="form-control-sm form-control custom_attend_exp_excl"
																		aria-required="true" autocomplete="off"
																		value="DD/MM/YYYY"
																		placeholder="Date Of Death">
																</div>
													   </td>
													   
													   <td class="min-width">
														<div class="input-style-1">
															<input type="text" id="avail_num1"
																name="avail_num1" 
																class="form-control" autocomplete="off"
																placeholder="Available (Number/Quantity)"  maxlength="10">
														</div>
													   </td>
													   
													   <input type="hidden" id="hid_or_med" name="hid_or_med" class="form-control" value="0">
													   
													   <td>
														<ul class="buttons-group">
															<li value="ADD" title="ADD" id="id_add_att1"><a
																class="main-btn success-btn btn-hover btn-sm btnaddmore" title="Add"><i
																	class="lni lni-plus"></i></a></li>
														</ul>
														
														<input type="hidden" id="count_hidden_att"
											name="count_hidden_att" class="form-control autocomplete"
											value="1"> <input type='hidden' id='id' name="id"
											value='0' />
													</td>
													</tr>
															<!-- end table row -->
														</tbody>
													</table>
												</div>
											</div>

										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover" id="save_organ"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- DEPARTMENT OF ORGANON OF MEDICINE END -->
								<!-- DEPARTMENT OF PATHOLOGY & MICROBIOLOGY START -->
								<div class="tab" id="patho_micro_dtl">
									<button class="tab-toggle">Pathalogy & Microbiology</button>
								</div>
								<div class="content">
									<h4 class="heading">Pathalogy & Microbiology</h4>
									<form:form name="path_microbio" id="path_microbio" action="" method="post" class=""
										commandName="">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="pamicro_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>Items</h6></th>
																<th><h6>
																		Available(Number/ Quantity)<strong class="mandatory">*</strong>
																	</h6></th>
															</tr>

														</thead>
														<tbody id="">
														${datapatho_micro}
															<!-- end table row -->
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover" id="save_pathology"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- DEPARTMENT OF PATHOLOGY & MICROBIOLOGY END -->
								<!-- DEPARTMENT OF PHYSIOLOGY & BIOCHEMISTRY START -->
								<div class="tab" id="physio_bio_dtl">
									<button class="tab-toggle">Physiology & Biochemistry</button>
								</div>
								<div class="content">
									<h4 class="heading">Physiology & Biochemistry</h4>
									<form:form name="path_bio" id="path_bio" action="" method="post" class=""
										commandName="">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="phybio_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>Items</h6></th>
																<th><h6>
																		Available(Number/ Quantity)<strong class="mandatory">*</strong>
																	</h6></th>
															</tr>

														</thead>
														<tbody id="">
                                                         ${datapatho_bio}
															<!-- end table row -->
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover" id="save_physiology"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</form:form>
								</div>
								<!-- DEPARTMENT OF PHYSIOLOGY & BIOCHEMISTRY END -->
								<!-- DEPARTMENT OF BIOCHEMISTRY START -->
								<div class="tab" id="biochem_dtl">
									<button class="tab-toggle">Biochemistry</button>
								</div>
								<div class="content">
									<h4 class="heading">Biochemistry</h4>
									<form:form name="bio" id="bio" action="" method="post" class=""
										commandName="">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="phybio_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>Items</h6></th>
																<th><h6>
																		Available(Number/ Quantity)<strong class="mandatory">*</strong>
																	</h6></th>
															</tr>

														</thead>
														<tbody id="">
                                                          ${data_bio}
															<!-- end table row -->
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover" id="save_biochemistry"
														type="button" value="Save"></li>
												</ul>
											</div>
										</div>
									</form:form>
								</div>
								<!-- DEPARTMENT OF BIOCHEMISTRY END -->
								<!-- DEPARTMENT OF PRACTICE OF MEDICINE START -->
								<div class="tab" id="prac_med_dtl">
									<button class="tab-toggle">Practice of Medicine</button>
								</div>
								<div class="content">
									<h4 class="heading">Practice of Medicine</h4>
									<form:form name="pract_medi" id="pract_medi" action="" method="post" class=""
										commandName="">
										<div class="row">
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Number of Equipments for
														Identification<strong class="mandatory">*</strong>
													</label> <input type="text" id="pra_medicine_equide"
														name="pra_medicine_equide" class="form-control" 
														placeholder="Total Equipments for Identification" maxlength="10">
												</div>
												
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Additional Items<strong
														class="mandatory">*</strong>
													</label> <input type="text" id="pra_medicine_add_item"
														name="pra_medicine_add_item" class="form-control"
														placeholder="Total Additional Items" maxlength="250">
												</div>
											</div>
										</div>
										<input type="hidden" id="hid_med" name="hid_med" class="form-control" value="0">	
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover" id="save_practice"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- DEPARTMENT OF PRACTICE OF MEDICINE END -->
								<!-- DEPARTMENT OF REPERTORY START -->
								<div class="tab" id="repertory_dtl">
									<button class="tab-toggle">Repertory</button>
								</div>
								<div class="content">
									<h4 class="heading">Repertory</h4>
									<form:form name="reper" id="reper" action="" method="post" class=""
										commandName="">
										<div class="row">
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Total Number of Computers<strong
														class="mandatory">*</strong>
													</label> <input type="text" id="repertory_computer" 
														name="repertory_computer" class="form-control"
														placeholder="Total Number of Computers" maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Total Number of Software<strong
														class="mandatory">*</strong>
													</label> <input type="text" id="repertory_software"
														name="repertory_software" class="form-control"
														placeholder="Total Number of Software" maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Details of Computer Lab<strong
														class="mandatory">*</strong>
													</label> <input type="text" id="repertory_detail_lab"
														name="repertory_detail_lab" class="form-control"
														placeholder="Details of Computer Lab" maxlength="250">
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Other Items<strong
														class="mandatory">*</strong>
													</label> <input type="text" id="repertory_other_item"
														name="repertory_other_item" class="form-control" 
														placeholder="Total Other Items" maxlength="250">
												</div>
											</div>
										</div>
										
										<input type="hidden" id="hid_rep" name="hid_rep" class="form-control" value="0">	
										
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover" id="save_reportary"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- DEPARTMENT OF REPERTORY END -->
								<!-- DEPARTMENT OF SURGERY START -->
								<div class="tab" id="surgery_dtl">
									<button class="tab-toggle">Surgery</button>
								</div>
								<div class="content">
									<h4 class="heading">Surgery</h4>
									<form:form name="surg" id="surg" action="" method="post" class=""
										commandName="">
										<div class="row">
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Number of Equipments for
														Identification<strong class="mandatory">*</strong>
													</label> <input type="text" id="surgery_equide" 
														name="surgery_equide" class="form-control"
														placeholder="Total Equipments for Identification" maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Additional Items<strong
														class="mandatory">*</strong>
													</label> <input type="text" id="surgery_item" name="surgery_item" 
														class="form-control" placeholder="Total Additional Items" maxlength="250">
												</div>
											</div>
										</div>
										
										<input type="hidden" id="hid_sur" name="hid_sur" class="form-control" value="0">	
										
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover" id="save_surgery"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- DEPARTMENT OF SURGERY END -->
								<!-- DEPARTMENT OF HOMOEOPATHIC MATERIA MEDICA START -->
								<div class="tab" id="homeo_materia_medi_dtl">
									<button class="tab-toggle">Homoeopathic Materia Medica</button>
								</div>
								<div class="content">
									<h4 class="heading">Homoeopathic Materia Medica</h4>
									<form:form name="homeo_mm" id="homeo_mm" action="" method="post" class=""
										commandName="">
										<div class="row">
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Charts on medicines in Hom.
														Materia Medica interesting charts on Ophidia groups,
														Spider family, Nosodes and Sarcodes.<strong
														class="mandatory">*</strong>
													</label> <input type="text" id="hommm_inter_charts"
														name="hommm_inter_charts" class="form-control"
														placeholder="Charts on medicines in Hom.
														Materia Medica interesting charts on Ophidia groups,
														Spider family, Nosodes and Sarcodes." maxlength="250">
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Total Number of Charts for
														Typical Picture-wise presentation of drugs.<strong
														class="mandatory">*</strong>
													</label> <input type="text" id="hommm_pic_wise_charts" 
														name="hommm_pic_wise_charts" class="form-control"
														placeholder="Total Number of Charts for
														Typical Picture-wise presentation of drugs." maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Additional Items<strong
														class="mandatory">*</strong>
													</label> <input type="text" id="hommm_item" name="hommm_item" 
														class="form-control" placeholder=" Additional Items" maxlength="250">
												</div>
											</div>
										</div> 
										
										<input type="hidden" id="hid_hmm" name="hid_hmm" class="form-control" value="0">	
										
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover" id="save_hom_materia"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- DEPARTMENT OF HOMOEOPATHIC MATERIA MEDICA END -->
								<!-- DEPARTMENT OF PSYCHIATRY (For P.G. Course) START -->
								<div class="tab" id="psychiatry_dtl">
									<button class="tab-toggle">Psychiatry (PG)</button>
								</div>
								<div class="content">
									<h4 class="heading">Psychiatry (PG)</h4>
									<form:form name="psy" id="psy" action="" method="post" class=""
										commandName="">
										<div class="row">
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Total number of Charts<strong
														class="mandatory">*</strong>
													</label> <input type="text" id="psychiatry_charts"
														name="psychiatry_charts" class="form-control"
														placeholder="Total number of Charts" maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Total Number of Models<strong
														class="mandatory">*</strong>
													</label> <input type="text" id="psychiatry_models"
														name="psychiatry_models" class="form-control"
														placeholder="Total Number of Models" maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Number of books in Departmental
														Library<strong class="mandatory">*</strong>
													</label> <input type="text" id="psychiatry_librarybook"
														name="psychiatry_librarybook" class="form-control"
														placeholder="Total Number of books in Departmental Library" maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Other Items<strong
														class="mandatory">*</strong>
													</label> <input type="text" id="psychiatry_item" 
														name="psychiatry_item" class="form-control"
														placeholder="Total Other Items" maxlength="250">
												</div>
											</div>
										</div>
										
											<input type="hidden" id="hid_py" name="hid_py" class="form-control" value="0">	
										
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover" id="save_pg"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- DEPARTMENT OF PSYCHIATRY (For P.G. Course) END -->
								<!-- DEPARTMENT OF PEDIATRICS (For P.G. Course) START -->
								<div class="tab" id="pediatrics_dtl">
									<button class="tab-toggle">Pediatrics (PG)</button>
								</div>
								<div class="content">
									<h4 class="heading">Pediatrics (PG)</h4>
									<form:form name="pedia" id="pedia" action="" method="post" class=""
										commandName="">
										<div class="row">
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Total number of Charts<strong
														class="mandatory">*</strong>
													</label> <input type="text" id="pediatrics_charts" 
														name="pediatrics_charts" class="form-control"
														placeholder="Total number of Charts"  maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Total Number of Models<strong
														class="mandatory">*</strong>
													</label> <input type="text" id="pediatrics_models" 
														name="pediatrics_models" class="form-control"
														placeholder="Total Number of Models"  maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Number of books in Departmental
														Library<strong class="mandatory">*</strong>
													</label> <input type="text" id="pediatrics_librarybook"
														name="pediatrics_librarybook" class="form-control" 
														placeholder="Total Number of books in Departmental Library"  maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Other Items<strong
														class="mandatory">*</strong>
													</label> <input type="text" id="pediatrics_item"
														name="pediatrics_item" class="form-control"
														placeholder="Total Other Items"  maxlength="250">
												</div>
											</div>
										</div>
										
										<input type="hidden" id="hid_pd" name="hid_pd" class="form-control" value="0">	
										
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn success-btn btn-hover" id="save_Pediatrics"
															type="button" value="Submit"></li>
													</ul>
												</div>
											</div>

										</div>
									</form:form>
								</div>
								<!-- DEPARTMENT OF PEDIATRICS (For P.G. Course) END -->
							</div>
						</div>
					</div>
					<!--    ===========================
		Department Equipments End
===========================     -->
				</div>
			</div>
			<!-- end row -->
		</div>
		<!-- end container -->
	</section>
	<!-- regulation components end -->
	
	<c:url value="View_Department_Equipment_ReportUrl" var="viewUrl" />
	<form:form action="${viewUrl}" method="post" id="viewForm"
		name="viewForm" modelAttribute="department_eqp_view_id">
		<input type="hidden" name="department_eqp_id" id="department_eqp_view_id" value="0" />
		<input type="hidden" name="statusview" id="statusview" value="0" />
	</form:form>
	

	<script type="text/javascript"
		src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>


</body>

<script type="text/javascript" nonce="${cspNonce}">
	$(document).ready(function() {
		if ('${role}'=='NCH') {
			$(".viewData").addClass("d-none")
		}
		if ('${role}'=='Institute_NCH') {
			$(".viewData").removeClass("d-none")
		} 
		
		$("#basic_info_id").val('${basic_info_id}');
		
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
			datepicketDate('doa');
			datepicketDate('date_of_death1');
			datepicketDate('date_of_birth1');
		}
		
		<c:forEach var="j" items="${dataforAnatomy_id}" varStatus="num">
		document.getElementById('available_num_ana_'+"${dataforAnatomy_id[num.index][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	    </c:forEach>
		
		<c:forEach var="j" items="${datacommunity_id}" varStatus="num">
		document.getElementById('available_num_cm_'+"${datacommunity_id[num.index][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	    </c:forEach>
	    
	    <c:forEach var="j" items="${dataforensic_equipments_id}" varStatus="num">
		document.getElementById('available_num_quant'+"${dataforensic_equipments_id[num.index][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	    </c:forEach>
	    
	    <c:forEach var="j" items="${dataforensic_equipments_id}" varStatus="num">
		document.getElementById('available_num_quant'+"${dataforensic_equipments_id[num.index][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	    </c:forEach>
	    
	    <c:forEach var="j" items="${dataforensic_act_id}" varStatus="num">
		document.getElementById('act_available_num_quant'+"${dataforensic_act_id[num.index][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	    </c:forEach>
	    
	    <c:forEach var="j" items="${datahomeophatic_pharmacy_id}" varStatus="num">
		document.getElementById('available_num_quant_ph'+"${datahomeophatic_pharmacy_id[num.index][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	    </c:forEach>
	    
	    <c:forEach var="j" items="${datapatho_micro_id}" varStatus="num">
		document.getElementById('available_num_quant_pm'+"${datapatho_micro_id[num.index][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	    </c:forEach>
	    
	    <c:forEach var="j" items="${datapatho_bio_id}" varStatus="num">
		document.getElementById('available_num_quant_pb'+"${datapatho_bio_id[num.index][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	    </c:forEach>
	    
	    <c:forEach var="j" items="${data_bio_id}" varStatus="num">
		document.getElementById('available_num_quant_b'+"${data_bio_id[num.index][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	    </c:forEach>
	    
	    <c:forEach var="j" items="${data_bio_id}" varStatus="num">
		document.getElementById('available_num_quant_b'+"${data_bio_id[num.index][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	    </c:forEach>
	    
	    
		getAnatomt();
		getCommunity();
	    getForensic_Eq();
	    getForensic_Acts();
	    getHomeophatic_Ph();
	    getPatho_Micro();
	    getPatho_Biochem();
	    get_Biochem();
	    getOrganon_of_Medicine();
	    
	});
	
	
	
	var dataobst ='${dataobst}';
	//alert('${dataobst}');
	if(dataobst != "[]"){
		
		$("#deprt_equip_hidden").val('${dataobst[0].id}');
		$("#og_equipments").val('${dataobst[0].no_of_equip_identi}');
		$("#og_additionalitem").val('${dataobst[0].additional_item}');
		$("#hid_og_alco_lice").val('${dataobst[0].copy_of_alchoho}');
		$("#hid_og_photo_cm").val('${dataobst[0].photographs_of_cadavers}');
		$("#hid_og_bill_equipment").val('${dataobst[0].upload_purchase_bill}');
		
		
	}
	
	
	
// 	var deprt_equip_hidden = $("#deprt_equip_hidden").val()
//  	if(deprt_equip_hidden == 0){
// //  		$("#obstetric_gynac_dtl").hide();
//  		$("#anatomy_dtl").hide();
//  		$("#community_med_dtl").hide();
//  		$("#forensic_med_dtl").hide();
//  		$("#homeo_pharm_dtl").hide();
//  		$("#organon_med_dtl").hide();
//  		$("#patho_micro_dtl").hide();
//  		$("#physio_bio_dtl").hide();
//  		$("#biochem_dtl").hide();
//  		$("#prac_med_dtl").hide();
//  		$("#repertory_dtl").hide();
//  		$("#surgery_dtl").hide();
//  		$("#homeo_materia_medi_dtl").hide();
//  		$("#psychiatry_dtl").hide();
//  		$("#pediatrics_dtl").hide();
 		
 		
//  	}else{
// //  	 	$("#obstetric_gynac_dtl").show();
//  	 	$("#anatomy_dtl").show();
//  	 	$("#community_med_dtl").show();
//  	 	$("#forensic_med_dtl").show();
//  	 	$("#homeo_pharm_dtl").show();
//  	 	$("#organon_med_dtl").show();
//  	 	$("#patho_micro_dtl").show();
//  	 	$("#physio_bio_dtl").show();
//  	 	$("#biochem_dtl").show();
//  	 	$("#prac_med_dtl").show();
//  		$("#repertory_dtl").show();
//  		$("#surgery_dtl").show();
//  		$("#homeo_materia_medi_dtl").show();
//  		$("#psychiatry_dtl").show();
//  		$("#pediatrics_dtl").show();
//  	}
	
	
	
	var dataanatomy ='${dataanatomy}';
	if(dataanatomy != "[]"){
		
		$("#hid_ana_child").val('${dataanatomy[0].id}');
		$("#ana_add_item").val('${dataanatomy[0].total_add_item}');
		$("#ana_cada_ava").val('${dataanatomy[0].total_cadavers_avai}');
 		$("#anatomy_act").val('${dataanatomy[0].anatomy_act}');
	}
	
   var datacomm ='${datacomm}';
	
	if(datacomm != "[]"){
		
		$("#hid_cm_child").val('${datacomm[0].id}');
		$("#comm_welfaredevices").val('${datacomm[0].family_welfare}');
		$("#comm_src_nutrition").val('${datacomm[0].source_nutrition}');
		$("#comm_src_vitamins").val('${datacomm[0].source_vitamin}');
	}
	
	var datahomeo_ph ='${datahomeo_ph}';
	
    if(datahomeo_ph != "[]"){
		
		$("#hid_ph_child").val('${datahomeo_ph[0].id}');
		$("#hergar_soecies").val('${datahomeo_ph[0].no_species_planted}');
		$("#hergar_plantpot").val('${datahomeo_ph[0].no_pots}');
		if('${datahomeo_ph[0].irrigation_facility}' == '0'){
			$("#hergarirrigation_Yes").click();
		}
		if('${datahomeo_ph[0].irrigation_facility}' == '1'){
			$("#hergarirrigation_No").click();
		}
		if('${datahomeo_ph[0].educational_tour_conducted}' == '0'){
			$("#hergar_edu_tour_Yes").click();
		}
		if('${datahomeo_ph[0].educational_tour_conducted}' == '1'){
			$("#hergar_edu_tour_No").click();
		}
		
	}
	
	var datamed ='${datamed}';
	
	if(datamed != "[]"){
		
		$("#hid_med").val('${datamed[0].id}');
		$("#pra_medicine_equide").val('${datamed[0].no_equip_identi}');
		$("#pra_medicine_add_item").val('${datamed[0].addi_item}');
	}
	
   var datareper ='${datareper}';
	
	if(datareper != "[]"){
		
		$("#hid_rep").val('${datareper[0].id}');
		$("#repertory_computer").val('${datareper[0].total_num_comp}');
		$("#repertory_software").val('${datareper[0].total_num_software}');
		$("#repertory_detail_lab").val('${datareper[0].detail_comp_lab}');
		$("#repertory_other_item").val('${datareper[0].other_item}');
	}
	
	var datasurgery ='${datasurgery}';
	
		if(datasurgery != "[]"){
			
		$("#hid_sur").val('${datasurgery[0].id}');
		$("#surgery_equide").val('${datasurgery[0].no_equip_identi}');
		$("#surgery_item").val('${datasurgery[0].addi_item}');
	}
		
	var datahomeo_mat_med ='${datahomeo_mat_med}';
	
	if(datahomeo_mat_med != "[]"){
		
		$("#hid_hmm").val('${datahomeo_mat_med[0].id}');
		$("#hommm_inter_charts").val('${datahomeo_mat_med[0].charts}');
		$("#hommm_pic_wise_charts").val('${datahomeo_mat_med[0].total_charts}');
		$("#hommm_item").val('${datahomeo_mat_med[0].addi_item}');
	}
	
   var datapsychiatry ='${datapsychiatry}';
	
	if(datapsychiatry != "[]"){
		
		$("#hid_py").val('${datapsychiatry[0].id}');
		$("#psychiatry_charts").val('${datapsychiatry[0].total_charts}');
		$("#psychiatry_models").val('${datapsychiatry[0].total_models}');
		$("#psychiatry_librarybook").val('${datapsychiatry[0].num_depart_lib}');
		$("#psychiatry_item").val('${datapsychiatry[0].other_items}');
	}
	
	 var datapediatric ='${datapediatric}';
		
		if(datapediatric != "[]"){
			
			$("#hid_pd").val('${datapediatric[0].id}');
			$("#pediatrics_charts").val('${datapediatric[0].total_charts}');
			$("#pediatrics_models").val('${datapediatric[0].total_models}');
			$("#pediatrics_librarybook").val('${datapediatric[0].num_depart_lib}');
			$("#pediatrics_item").val('${datapediatric[0].other_items}');
	}
		
		 
		
	
	
	
	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('save_obstetric').onclick = function() {
			
			saveDraft_obstetric_gynacology();
				
		};
		
		document.getElementById('save_anatomy').onclick = function() {
						
			Save_As_Draft_Anatomy();
							
		};
					
		document.getElementById('save_cm').onclick = function() {
						
			Save_As_Draft_Community();
									
		};
				
		document.getElementById('save_forensic').onclick = function() {
								
			Save_Forensic_Equipment_Details();
											
		};	
				
		document.getElementById('save_Hpharma').onclick = function() {
					
			Save_As_Homeophatic_Pharmacy();
												
		};
		
		document.getElementById('save_organ').onclick = function() {
							
			Save_Organon_of_Medicine();
													
		};
							
		document.getElementById('save_pathology').onclick = function() {
								
			Save_Pathology_MicroBiology_Details();
														
		};
		
		document.getElementById('save_physiology').onclick = function() {
			
			Save_Pathology_Biochemistry_Details();
														
		};	
		document.getElementById('save_biochemistry').onclick = function() {
			
			Save_Biochemistry_Details();
													
		};
		
		document.getElementById('save_practice').onclick = function() {
			
			Save_Practice_Medicine_Details();
														
		};
						
		document.getElementById('save_reportary').onclick = function() {
			
			Save_Repertory();
														
		};
							
		document.getElementById('save_surgery').onclick = function() {
			
			Save_Surgery();
														
		};	
		
		document.getElementById('save_hom_materia').onclick = function() {
			
			Save_Homeo_MM();
														
		};
		
		document.getElementById('save_pg').onclick = function() {
			
			Save_Psychiatry();
														
		};	
		
		document.getElementById('save_Pediatrics').onclick = function() {
			
			Save_Pediatrics();
														
		};
		
		document.getElementById('id_add_att1').onclick = function() {
			formopen_att(1)
		};
		
		document.getElementById('og_alco_lice_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"og_alco_lice_doc","200kb","og_alco_lice_doctik","og_alco_lice_doc",this.accept)
		};
		document.getElementById('og_photo_cm_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"og_photo_cm_doc","200kb","og_photo_cm_doctik","og_photo_cm_doc",this.accept)
		};
		document.getElementById('og_bill_equipment_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"og_bill_equipment_doc","200kb","og_bill_equipment_doctik","og_bill_equipment_doc",this.accept)
		};
		
												
	});
	
	
	
	//================================SaveDraft FOR Obstretic and Gynacology==============================
	
	function saveDraft_obstetric_gynacology() {
		
		var basic_info_id = $("#basic_info_id").val();
		if(basic_info_id == "0"){
			alert("Please Save Basic details First");
			return false;
		}
		
		var res = CheckNullorBlank('og_equipments');
		if (res !== "true") {
			alert(res +"Number of Equipments for Identification.");
			$('#og_equipments').focus();
			return false;
		}
		
		var res = CheckNullorBlank('og_additionalitem');
		if (res !== "true") {
			alert(res +"Additional Items.");
			$('#og_additionalitem').focus();
			return false;
		}
		
		if($("#og_alco_lice_doc").val()==''){
			if($("#hid_og_alco_lice").val()==''){
					alert("Please Upload Copy of Alcohol License, Anatomy Act. ");
					$("#og_alco_lice_doc").focus();
					return false;
			}
		}
		
		if($("#og_photo_cm_doc").val()==''){
			if($("#hid_og_photo_cm").val()==''){
					alert("Please Upload Photographs of Cadavers & Mummified Bodies. ");
					$("#og_photo_cm_doc").focus();
					return false;
			}
		}
		
		if($("#og_bill_equipment_doc").val()==''){
			if($("#hid_og_bill_equipment").val()==''){
					alert("Please Upload Purchase Bill of Departmental Equipments. ");
					$("#og_bill_equipment_doc").focus();
					return false;
			}
		}
		
		$.ajaxSetup({
		    async: false
		});	
		
		//var pernt_id = $("#inst_basic_hidden").val();

		var form_data = new FormData(document.getElementById("obstetric_gynacology_form"));
		
	//	form_data.append("pernt_id",$("#inst_basic_hidden").val());
	 		
			$.ajax({
		        url: 'obstetric_gynacology_Action?' + key + "=" + value,
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
	
	
	//SAVE ANATOMY==========================================
	function Save_As_Draft_Anatomy(sd) {
		
		var deprt_equip_hidden = $("#deprt_equip_hidden").val();
		if(deprt_equip_hidden == "0"){
			alert("Please Save Obstetric & Gynacology First");
			return false;
		}

		
		<c:forEach var="j" items="${dataforAnatomy_id}" varStatus="num">
		
			var res = CheckNullorBlank('available_num_ana_${j[0]}');
			if (res !== "true") {
				alert(res +"Available(Number/Qunatity) of ${j[1]}");
				$('#available_num_ana_${j[0]}').focus();
				return false;
			}
			
			</c:forEach>
			
			var res = CheckNullorBlank('ana_add_item');
			if (res !== "true") {
				alert(res +"Total Additional Items.");
				$('#ana_add_item').focus();
				return false;
			}
			
			var res = CheckNullorBlank('ana_cada_ava');
			if (res !== "true") {
				alert(res +"Total Cadavers Available.");
				$('#ana_cada_ava').focus();
				return false;
			}
			
// 			if($("input#anatomy_act").val().trim()=="0" || $("input#anatomy_act").val().trim()=="0"){
// 				alert("Please Enter Whether College covered under the provisions of Anatomy Act");
// 				$("#anatomy_act").focus();
// 				return false;
// 			}
			
		$.ajaxSetup({
		    async: false
		});	
	 	
		var form = $("#anatomy_form").serialize();
		$.post(
				'Anatomy_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        	//	$("#hid_constructed_area").val(j);
		        	//	if(sd != -1){
		        	  alert("Data Save Sucessfully");
		        //		}
		        //	  location.reload();
		          }
		          else{
		        	  if(sd != -1){
		        	  alert(j);
		        	  }
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
			
	 }
	
	
	
	//FETCH ANATOMY========================================
	function getAnatomt() {
		debugger;
		$.ajaxSetup({
			async : false
		});
		
		$.post("getAnatomy_data?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
						
				$("#hid_item_ana_"+j[i].item_id).val(j[i].id);
		//		alert(j[i].course_id);
				$("#available_num_ana_"+j[i].item_id).val(j[i].available_num_ana);
		//		alert(j[i].intake_capacity);
				
			}
			
		});
	}
	
	
	//SAVE  Community Medicine==========================================
	function Save_As_Draft_Community(sd) {
		
		var deprt_equip_hidden = $("#deprt_equip_hidden").val();
		if(deprt_equip_hidden == "0"){
			alert("Please Save Obstetric & Gynacology First");
			return false;
		}

		
		<c:forEach var="j" items="${datacommunity_id}" varStatus="num">
		
		var res = CheckNullorBlank('available_num_cm_'+${j[0]});
		if (res !== "true") {
			alert(res +"Available(Number/ Quantity) of ${j[1]}");
			$('#available_num_cm_'+${j[0]}).focus();
			return false;
		}
		
		</c:forEach>
		
		var res = CheckNullorBlank('comm_welfaredevices');
		if (res !== "true") {
			alert(res +"Family Welfare devices.");
			$('#comm_welfaredevices').focus();
			return false;
		}
		
		var res = CheckNullorBlank('comm_src_nutrition');
		if (res !== "true") {
			alert(res +"Sources of Nutrition.");
			$('#comm_src_nutrition').focus();
			return false;
		}
		
		var res = CheckNullorBlank('comm_src_vitamins');
		if (res !== "true") {
			alert(res +"Sources of Vitamins.");
			$('#comm_src_vitamins').focus();
			return false;
		}
		
// 		debugger;
		$.ajaxSetup({
		    async: false
		});	
	 	
		var form = $("#community_form").serialize();
		console.log(form);
		$.post(
				'Community_medicine_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        	//	$("#hid_constructed_area").val(j);
		        	//	if(sd != -1){
		        	  alert("Data Save Sucessfully");
		        //		}
		        //	  location.reload();
		          }
		          else{
		        	  if(sd != -1){
		        	  alert(j);
		        	  }
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
			
	 }
	
	
	//FETCH Community Medicine========================================
	function getCommunity() {
// 		debugger;
		$.ajaxSetup({
			async : false
		});
		
		$.post("getcommunitymedicine_data?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
						
				$("#hid_item_cm_"+j[i].item_id).val(j[i].id);
		//		alert(j[i].course_id);
				$("#available_num_cm_"+j[i].item_id).val(j[i].available_num_cm);
		//		alert(j[i].intake_capacity);
				
			}
			
		});
	}
	
	//SAVE  Forensic_Equipment_Details==========================================
	function Save_Forensic_Equipment_Details(sd) {
		
		var deprt_equip_hidden = $("#deprt_equip_hidden").val();
		if(deprt_equip_hidden == "0"){
			alert("Please Save Obstetric & Gynacology First");
			return false;
		}

		
        <c:forEach var="j" items="${dataforensic_equipments_id}" varStatus="num">
		
		var res = CheckNullorBlank('available_num_quant'+${j[0]});
		if (res !== "true") {
			alert(res +"Available(Number/ Quantity) of ${j[1]}");
			$('#available_num_quant'+${j[0]}).focus();
			return false;
		}
		
		</c:forEach>
		
		 <c:forEach var="j" items="${dataforensic_act_id}" varStatus="num">
			
			var res = CheckNullorBlank('act_available_num_quant${j[0]}');
			if (res !== "true") {
				console.log(res +'Available(Number/ Quantity) of ${j[1]}');
				$('#act_available_num_quant${j[0]}').focus();
				return false;
			}
			
			</c:forEach>
		
		
// 		debugger;
		$.ajaxSetup({
		    async: false
		});	
	 	
		var form = $("#forensic_eq").serialize();
		console.log(form);
		$.post(
				'Forensic_Equipment_Details_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        	//	$("#hid_constructed_area").val(j);
		        	//	if(sd != -1){
		        	  alert("Data Save Sucessfully");
		        //		}
		        //	  location.reload();
		          }
		          else{
		        	  if(sd != -1){
		        	  alert(j);
		        	  }
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
			
	 }
	
	//FETCH Forensic_Medicines========================================
	function getForensic_Eq() {
// 		debugger;
		$.ajaxSetup({
			async : false
		});
		
		$.post("getforensic_eq_data?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
						
				$("#hid_item_fm_eq"+j[i].item_id).val(j[i].id);
		//		alert(j[i].course_id);
				$("#available_num_quant"+j[i].item_id).val(j[i].available_num_quant);
		//		alert(j[i].intake_capacity);
				
			}
			
		});
	}
	
	
	//FETCH  Forensic_acts========================================
	function getForensic_Acts() {
// 		debugger;
		$.ajaxSetup({
			async : false
		});
		
		$.post("getforensic_acts_data?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
						
				$("#hid_item_fm_act"+j[i].item_id).val(j[i].id);
		//		alert(j[i].course_id);
				$("#act_available_num_quant"+j[i].item_id).val(j[i].act_available_num_quant);
		//		alert(j[i].intake_capacity);
				
			}
			
		});
	}
			
	
	//SAVE Homeophatic_Pharmacy==========================================
		
	function Save_As_Homeophatic_Pharmacy(sd) {
		
		var deprt_equip_hidden = $("#deprt_equip_hidden").val();
		if(deprt_equip_hidden == "0"){
			alert("Please Save Obstetric & Gynacology First");
			return false;
		}
		
		 <c:forEach var="j" items="${datahomeophatic_pharmacy_id}" varStatus="num">
			
			var res = CheckNullorBlank('available_num_quant_ph'+${j[0]});
			if (res !== "true") {
				alert(res +"Available(Number/ Quantity) of ${j[1]}");
				$('#available_num_quant_ph'+${j[0]}).focus();
				return false;
			}
			
			</c:forEach>
			
			var res = CheckNullorBlank('hergar_soecies');
			if (res !== "true") {
				alert(res +"Number of Species Planted.");
				$('#hergar_soecies').focus();
				return false;
			}
			
			var res = CheckNullorBlank('hergar_plantpot');
			if (res !== "true") {
				alert(res +"Plants on Pots.");
				$('#hergar_plantpot').focus();
				return false;
			}
			
			var hergarirrigationcheck = $("input[name='hergarirrigationcheck']:checked").val();
			if( hergarirrigationcheck == null ){
				alert("Please Select Irrigation Facility");
				return false;
		   	}
			
			var hergar_edu_tourcheck = $("input[name='hergar_edu_tourcheck']:checked").val();
			if( hergar_edu_tourcheck == null ){
				alert("Please Select Educational Tour Conducted");
				return false;
		   	}

		
		$.ajaxSetup({
		    async: false
		});	
	 	
		var form = $("#homeo_ph").serialize();
		console.log(form);
		$.post(
				'Homeophatic_Pharmacy_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        	//	$("#hid_constructed_area").val(j);
		        	//	if(sd != -1){
		        	  alert("Data Save Sucessfully");
		        //		}
		        //	  location.reload();
		          }
		          else{
		        	  if(sd != -1){
		        	  alert(j);
		        	  }
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
			
	 }	
	
	//FETCH Homeophatic_Pharmacy========================================
	function getHomeophatic_Ph() {
		debugger;
		$.ajaxSetup({
			async : false
		});
		
		$.post("getHomeophatic_Pharmacy_data?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
						
				$("#hid_item_ph"+j[i].item_id).val(j[i].id);
		//		alert(j[i].course_id);
				$("#available_num_quant_ph"+j[i].item_id).val(j[i].available_num_quant);
		//		alert(j[i].intake_capacity);
				
			}
			
		});
	}	
			
	//SAVE  Pathology_MicroBiology==========================================
	function Save_Pathology_MicroBiology_Details(sd) {
		
		var deprt_equip_hidden = $("#deprt_equip_hidden").val();
		if(deprt_equip_hidden == "0"){
			alert("Please Save Obstetric & Gynacology First");
			return false;
		}
		
		 <c:forEach var="j" items="${datapatho_micro_id}" varStatus="num">
			
			var res = CheckNullorBlank('available_num_quant_pm'+${j[0]});
			if (res !== "true") {
				alert(res +"Available(Number/ Quantity) of ${j[1]}");
				$('#available_num_quant_pm'+${j[0]}).focus();
				return false;
			}
			
			</c:forEach>
		
// 		debugger;
		$.ajaxSetup({
		    async: false
		});	
	 	
		var form = $("#path_microbio").serialize();
		console.log(form);
		$.post(
				'Pathology_MicroBiology_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        	//	$("#hid_constructed_area").val(j);
		        	//	if(sd != -1){
		        	  alert("Data Save Sucessfully");
		        //		}
		        //	  location.reload();
		          }
		          else{
		        	  if(sd != -1){
		        	  alert(j);
		        	  }
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
			
	 }	
	
	//FETCH Pathology_MicroBiology========================================
	function getPatho_Micro() {
		debugger;
		$.ajaxSetup({
			async : false
		});
		
		$.post("getPathology_MicroBiology_data?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
						
				$("#hid_item_pm"+j[i].item_id).val(j[i].id);
		//		alert(j[i].course_id);
				$("#available_num_quant_pm"+j[i].item_id).val(j[i].available_num_quant);
		//		alert(j[i].intake_capacity);
				
			}
			
		});
	}	
	
//SAVE  Pathology_Biochemistry==========================================
	function Save_Pathology_Biochemistry_Details(sd) {
	
		var deprt_equip_hidden = $("#deprt_equip_hidden").val();
		if(deprt_equip_hidden == "0"){
			alert("Please Save Obstetric & Gynacology First");
			return false;
		}
	
		 <c:forEach var="j" items="${datapatho_bio_id}" varStatus="num">
			
			var res = CheckNullorBlank('available_num_quant_pb'+${j[0]});
			if (res !== "true") {
				alert(res +"Available(Number/ Quantity) of ${j[1]}");
				$('#available_num_quant_pb'+${j[0]}).focus();
				return false;
			}
			
			</c:forEach>
			
// 		debugger;
		$.ajaxSetup({
		    async: false
		});	
	 	
		var form = $("#path_bio").serialize();
		console.log(form);
		$.post(
				'Pathology_Biochemistry_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        	//	$("#hid_constructed_area").val(j);
		        	//	if(sd != -1){
		        	  alert("Data Save Sucessfully");
		        //		}
		        //	  location.reload();
		          }
		          else{
		        	  if(sd != -1){
		        	  alert(j);
		        	  }
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
			
	 }	
	 
	//FETCH Pathology_Biochemistry========================================
	function getPatho_Biochem() {
// 		debugger;
		$.ajaxSetup({
			async : false
		});
		
		$.post("getPathology_Biochemistry_data?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
						
				$("#hid_item_pb"+j[i].item_id).val(j[i].id);
		//		alert(j[i].course_id);
				$("#available_num_quant_pb"+j[i].item_id).val(j[i].available_num_quant);
		//		alert(j[i].intake_capacity);
				
			}
			
		});
	}
	
//SAVE  Biochemistry==========================================
	function Save_Biochemistry_Details(sd) {
	
		var deprt_equip_hidden = $("#deprt_equip_hidden").val();
		if(deprt_equip_hidden == "0"){
			alert("Please Save Obstetric & Gynacology First");
			return false;
		}
	
		<c:forEach var="j" items="${data_bio_id}" varStatus="num">
		
		var res = CheckNullorBlank('available_num_quant_b'+${j[0]});
		if (res !== "true") {
			alert(res +"Available(Number/ Quantity) of ${j[1]}");
			$('#available_num_quant_b'+${j[0]}).focus();
			return false;
		}
		
		</c:forEach>
		
// 		debugger;
		$.ajaxSetup({
		    async: false
		});	
	 	
		var form = $("#bio").serialize();
		console.log(form);
		$.post(
				'Biochemistry_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        	//	$("#hid_constructed_area").val(j);
		        	//	if(sd != -1){
		        	  alert("Data Save Sucessfully");
		        //		}
		        //	  location.reload();
		          }
		          else{
		        	  if(sd != -1){
		        	  alert(j);
		        	  }
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
			
	 }	
	 
//FETCH Biochemistry========================================
	function get_Biochem() {
// 		debugger;
		$.ajaxSetup({
			async : false
		});
		
		$.post("get_Biochemistry_data?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
						
				$("#hid_item_b"+j[i].item_id).val(j[i].id);
		//		alert(j[i].course_id);
				$("#available_num_quant_b"+j[i].item_id).val(j[i].available_num_quant);
		//		alert(j[i].intake_capacity);
				
			}
			
		});
	}
	
//SAVE  Practice_Medicine==========================================
	function Save_Practice_Medicine_Details(sd) {
	
		var deprt_equip_hidden = $("#deprt_equip_hidden").val();
		if(deprt_equip_hidden == "0"){
			alert("Please Save Obstetric & Gynacology First");
			return false;
		}
	
		var res = CheckNullorBlank('pra_medicine_equide');
		if (res !== "true") {
			alert(res +" Number of Equipments for Identification.");
			$('#pra_medicine_equide').focus();
			return false;
		}
		
		var res = CheckNullorBlank('pra_medicine_add_item');
		if (res !== "true") {
			alert(res +" Additional Items.");
			$('#pra_medicine_add_item').focus();
			return false;
		}
	
		
// 		debugger;
		$.ajaxSetup({
		    async: false
		});	
		
		var form = $("#pract_medi").serialize();
		console.log(form);
		$.post(
				'Practice_of_Medicine_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        	//	$("#hid_constructed_area").val(j);
		        	//	if(sd != -1){
		        	  alert("Data Save Sucessfully");
		        //		}
		        //	  location.reload();
		          }
		          else{
		        	  if(sd != -1){
		        	  alert(j);
		        	  }
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
		
	 }	
	 
//SAVE  Repertory==========================================
	function Save_Repertory(sd) {
	
		var deprt_equip_hidden = $("#deprt_equip_hidden").val();
		if(deprt_equip_hidden == "0"){
			alert("Please Save Obstetric & Gynacology First");
			return false;
		}
	
		var res = CheckNullorBlank('repertory_computer');
		if (res !== "true") {
			alert(res +"  Total Number of Computers.");
			$('#repertory_computer').focus();
			return false;
		}
		
		var res = CheckNullorBlank('repertory_software');
		if (res !== "true") {
			alert(res +"  Total Number of Software.");
			$('#repertory_software').focus();
			return false;
		}
		
		var res = CheckNullorBlank('repertory_detail_lab');
		if (res !== "true") {
			alert(res +" Details of Computer Lab.");
			$('#repertory_detail_lab').focus();
			return false;
		}
		
		var res = CheckNullorBlank('repertory_other_item');
		if (res !== "true") {
			alert(res +" Other Items.");
			$('#repertory_other_item').focus();
			return false;
		}
		
// 		debugger;
		$.ajaxSetup({
		    async: false
		});	
	 	
		var form = $("#reper").serialize();
		console.log(form);
		$.post(
				'Repertory_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        	//	$("#hid_constructed_area").val(j);
		        	//	if(sd != -1){
		        	  alert("Data Save Sucessfully");
		        //		}
		        //	  location.reload();
		          }
		          else{
		        	  if(sd != -1){
		        	  alert(j);
		        	  }
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
			
	 }	
	 
//SAVE  Surgery==========================================
	function Save_Surgery(sd) {
	
		var deprt_equip_hidden = $("#deprt_equip_hidden").val();
		if(deprt_equip_hidden == "0"){
			alert("Please Save Obstetric & Gynacology First");
			return false;
		}
	
		var res = CheckNullorBlank('surgery_equide');
		if (res !== "true") {
			alert(res +" Number of Equipments for Identification.");
			$('#surgery_equide').focus();
			return false;
		}
		
		var res = CheckNullorBlank('surgery_item');
		if (res !== "true") {
			alert(res +" Additional Items.");
			$('#surgery_item').focus();
			return false;
		}
		
// 		debugger;
		$.ajaxSetup({
		    async: false
		});	
	 	
		var form = $("#surg").serialize();
		console.log(form);
		$.post(
				'Surgery_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        	//	$("#hid_constructed_area").val(j);
		        	//	if(sd != -1){
		        	  alert("Data Save Sucessfully");
		        //		}
		        //	  location.reload();
		          }
		          else{
		        	  if(sd != -1){
		        	  alert(j);
		        	  }
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
			
	 }	
	 
//SAVE  Homeophathy_Materia_Medica==========================================
	function Save_Homeo_MM(sd) {
	
		var deprt_equip_hidden = $("#deprt_equip_hidden").val();
		if(deprt_equip_hidden == "0"){
			alert("Please Save Department Equipments First");
			return false;
		}
	
		var res = CheckNullorBlank('hommm_inter_charts');
		if (res !== "true") {
			alert(res +"  Charts on medicines in Hom. Materia Medica interesting charts on Ophidia groups, Spider family, Nosodes and Sarcodes.");
			$('#hommm_inter_charts').focus();
			return false;
		}
		
		var res = CheckNullorBlank('hommm_pic_wise_charts');
		if (res !== "true") {
			alert(res +" Total Number of Charts for Typical Picture-wise presentation of drugs.");
			$('#hommm_pic_wise_charts').focus();
			return false;
		}
		
		var res = CheckNullorBlank('hommm_item');
		if (res !== "true") {
			alert(res +" Additional Items.");
			$('#hommm_item').focus();
			return false;
		}
		
// 		debugger;
		$.ajaxSetup({
		    async: false
		});	
	 	
		var form = $("#homeo_mm").serialize();
		console.log(form);
		$.post(
				'HMM_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        	//	$("#hid_constructed_area").val(j);
		        	//	if(sd != -1){
		        	  alert("Data Save Sucessfully");
		        //		}
		        //	  location.reload();
		          }
		          else{
		        	  if(sd != -1){
		        	  alert(j);
		        	  }
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
			
	 }	
	 
//SAVE  Psychiatry==========================================
	function Save_Psychiatry(sd) {
	
		var deprt_equip_hidden = $("#deprt_equip_hidden").val();
		if(deprt_equip_hidden == "0"){
			alert("Please Save Obstetric & Gynacology First");
			return false;
		}
	
		var res = CheckNullorBlank('psychiatry_charts');
		if (res !== "true") {
			alert(res +"  Total number of Charts.");
			$('#psychiatry_charts').focus();
			return false;
		}
		
		var res = CheckNullorBlank('psychiatry_models');
		if (res !== "true") {
			alert(res +" Total Number of Models.");
			$('#psychiatry_models').focus();
			return false;
		}
		
		var res = CheckNullorBlank('psychiatry_librarybook');
		if (res !== "true") {
			alert(res +" Number of books in Departmental Library.");
			$('#psychiatry_librarybook').focus();
			return false;
		}
		
		var res = CheckNullorBlank('psychiatry_item');
		if (res !== "true") {
			alert(res +" Other Items.");
			$('#psychiatry_item').focus();
			return false;
		}
	
	
// 		debugger;
		$.ajaxSetup({
		    async: false
		});	
	 	
		var form = $("#psy").serialize();
		console.log(form);
		$.post(
				'Psychiatry_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        	//	$("#hid_constructed_area").val(j);
		        	//	if(sd != -1){
		        	  alert("Data Save Sucessfully");
		        //		}
		        //	  location.reload();
		          }
		          else{
		        	  if(sd != -1){
		        	  alert(j);
		        	  }
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
			
	 }	
	 
	//SAVE  Pediatrics==========================================
	function Save_Pediatrics(sd) {
		
		var deprt_equip_hidden = $("#deprt_equip_hidden").val();
		if(deprt_equip_hidden == "0"){
			alert("Please Save Obstetric & Gynacology First");
			return false;
		}
		
		var res = CheckNullorBlank('pediatrics_charts');
		if (res !== "true") {
			alert(res +"  Total number of Charts.");
			$('#pediatrics_charts').focus();
			return false;
		}
		
		var res = CheckNullorBlank('pediatrics_models');
		if (res !== "true") {
			alert(res +" Total Number of Models.");
			$('#pediatrics_models').focus();
			return false;
		}
		
		var res = CheckNullorBlank('pediatrics_librarybook');
		if (res !== "true") {
			alert(res +" Number of books in Departmental Library.");
			$('#pediatrics_librarybook').focus();
			return false;
		}
		
		var res = CheckNullorBlank('pediatrics_item');
		if (res !== "true") {
			alert(res +" Other Items.");
			$('#pediatrics_item').focus();
			return false;
		}
		
// 		debugger;
		$.ajaxSetup({
		    async: false
		});	
	 	
		var form = $("#pedia").serialize();
		console.log(form);
		$.post(
				'Pediatrics_Save_Draft_Action?' + key + "="
						+ value, form, function(j) {
					if(j>0){
		        	//	$("#hid_constructed_area").val(j);
		        	//	if(sd != -1){
		        	  alert("Data Save Sucessfully");
		        //		}
		        //	  location.reload();
		          }
		          else{
		        	  if(sd != -1){
		        	  alert(j);
		        	  }
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
			
	 }	
	
	//SAVE  Organon_of_Medicine==========================================
	function Save_Organon_of_Medicine(sd) {
		
		var deprt_equip_hidden = $("#deprt_equip_hidden").val();
		if(deprt_equip_hidden == "0"){
			alert("Please Save Obstetric & Gynacology First");
			return false;
		}
		
		var res = CheckNullorBlank('founder_name1');
		if (res !== "true") {
			alert(res +"Founder & Philosophers Name.");
			$('#founder_name1').focus();
			return false;
		}
		
		if($("#photo_path1").val()==''){
			if($("#upload_img_hid1").val()==''){
					alert("Please Upload Photographs. ");
					$("#photo_path1").focus();
					return false;
			}
		}
		
		if($("input#date_of_birth1").val().trim()=="" || $("input#date_of_birth1").val().trim()=="DD/MM/YYYY"){
			alert("Please Enter Date of Birth");
			$("#date_of_birth1").focus();
			return false;
		}
		
		if($("input#date_of_death1").val().trim()=="" || $("input#date_of_death1").val().trim()=="DD/MM/YYYY"){
			alert("Please Enter Date of Death");
			$("#date_of_death1").focus();
			return false;
		}
		
		var res = CheckNullorBlank('avail_num1');
		if (res !== "true") {
			alert(res +"Available (Number/Quantity).");
			$('#avail_num1').focus();
			return false;
		}
		
		
		debugger;
		$.ajaxSetup({
		    async: false
		});	
	 	
		var form_data = new FormData(document.getElementById("organ_med"));
		$.ajax({
			url : 'Organon_of_Medicine_Save_Draft_Action?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(data) {

			if(data>0){
	        	//	$("#hid_constructed_area").val(j);
	        	//	if(sd != -1){
	        	  alert("Data Save Sucessfully");
	        //		}
	        //	  location.reload();
	          }
	          else{
	        	  if(sd != -1){
	        	  alert(data);
	        	  }
	          }

		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
		
			
	 }	
	
	//FETCH Organon_of_Medicine===================================

	function getOrganon_of_Medicine() {
		
		$.ajaxSetup({
			async : false
		});
		
		var ser = 0;

		$.post("getAdd_More_Organon_of_Medicine_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				ser = i + 1;

				if (j.length > 0) {

					if (ser > 1) {
						
						$("#id_add_att" + (ser - 1)).click();
					}
					
					var dtDOB = new Date(j[i].date_of_birth);
					var dtDOD = new Date(j[i].date_of_death);
					
					$("#hid_or_med" + ser).val(j[i].id);
					$("#founder_name" + ser).val(j[i].founder_philoso_name);
					$("#upload_img_hid" + ser).val(j[i].photo_path);
					$("#date_of_birth" + ser).val(convertDate(dtDOB));
					$("#date_of_death" + ser).val(convertDate(dtDOD));
					$("#avail_num" + ser).val(j[i].available_num_quant);
					$("#id_add_att" + ser).show();
					$("#att_id_remove" + ser).show();
					
				}
			}
		});
	}
	
	function convertDate(inputFormat) {
		  function pad(s) { return (s < 10) ? '0' + s : s; }
		  var d = new Date(inputFormat)
		  return [pad(d.getDate()), pad(d.getMonth()+1), d.getFullYear()].join('/')
		}
	
	// DELETE Organon_of_Medicine========================================================
	
	function delete_Organon_of_Medicine(R) {
		
		// 		var qualtification_idhid2= $("input#qualtification_id"+R).val();
		if(confirm('Are you sure you want to delete?')) {

		var hid_library = $('#hid_or_med' + R).val();
		
		$.post("delete_Organon_of_Medicine_Details?" + key + "=" + value, {
			hid_library : hid_library
		}, function(j) {
			alert(j);
		});

		$("tr#tr_sibling_Organon1" + R).remove();
		R = R - 1;
		$("input#count_hidden_att_name_med").val(att);
		$("#id_add_att" + R).show();
		$("#att_id_remove" + R).show();
		}else {
			return false;
		}
	}
	
	
	//Add-More-Add
	var att=1;
	function formopen_att(R){
		debugger;
		$("#organmed_table").show();
		
			 $("#id_add_att"+R).hide();
			 $("#att_id_remove"+R).hide();
			 
			 att=0;
			 att= parseInt(R)+1;
			 
			 if(att < 51){
					 
					 $("input#count_hidden_att").val(att);//current serial No
					 $("table#organmed_table").append('<tr align="center" id="tr_id_att'+att+'"><td>'+att+'</td>'
						 +'<td class="min-width"><div class="input-style-1"><input type="text" id="founder_name'+att+'" name="founder_name'+att+'"   class="form-control"  autocomplete="off" placeholder="Founder & Philosophers Name" ></div></td>'
						 +'<td class="min-width"><div class="input-style-1"><input type="file" accept="image/*" id="photo_path'+att+'" name="photo_path'+att+'"  class="form-control">'
						 +'<input type="hidden" id="upload_img_hid'+att+'" name="upload_img_hid'+att+'" class="form-control"></div></td>'
						 +'<td class="min-width"><div class="input-style-1"><input type="text" id="date_of_birth'+att+'" name="date_of_birth'+att+'"   class="form-control"  autocomplete="off" placeholder="DD/MM/YYYY" ></div></td>'
						 +'<td class="min-width"><div class="input-style-1"><input type="text" id="date_of_death'+att+'" name="date_of_death'+att+'"  class="form-control"  autocomplete="off" placeholder="DD/MM/YYYY" ></div></td>'
						 +'<td class="min-width"><div class="input-style-1"><input type="text" id="avail_num'+att+'" name="avail_num'+att+'"   class="form-control"  autocomplete="off" placeholder="Available (Number/Quantity)" ></div></td>'
						 +'<td><ul class="buttons-group"><li value = "ADD" title = "ADD" id = "id_add_att'+att+'" ><a class="main-btn success-btn btn-hover btn-sm"><i class="lni lni-plus"></i></a></li><li value="REMOVE" title = "REMOVE" id = "att_id_remove'+att+'" ><a class="main-btn danger-btn btn-hover btn-sm"><i class="lni lni-trash-can"></i></a></li></td>'
			   		     +'</tr>');
					 
					 
					     addOnclick(att);
					     removeOnclick(att);
					     
							datepicketDate("date_of_birth"+att);
							datepicketDate("date_of_death"+att);
							
				}else{
						alert("Please Enter max 50 Quantity");
						 if ( att == 51){
							 att = att-1; 
							 $("#rp_id_remove"+att).show();
						 }	   
				}
			 var curcnt = $("#count_hidden_att").val();
			 $("#new_count_hidden").val(curcnt);
	}
	//Add-More-Remove
	function formopen_re_att(R){
		var del_index = $("#idofprocedure"+R).val();
		if(String(del_index) == "undefined"){
			del_index="0";
		}else{
			del_index = del_index;
		}
		 var del_field_val = $("#del_id_hidden").val();
		 
		 if(del_field_val == "0,undefined"){
			 $("#del_id_hidden").val(del_index);
		 }else{
			 $("#del_id_hidden").val(del_field_val+","+del_index);
		 }
		 $("tr#tr_id_att"+R).remove();
		 att = att-1;
		 $("input#count_hidden_att").val(att);
		 if(R > 2){
			 $("#id_add_att"+att).show();
			 $("#att_id_remove"+att).show();
		 }
		 if(R == 2){
			 $("#id_add_att"+att).show();
		 }
		 var ncc = $("#new_count_hidden").val();
		 ncc = ncc-1;
		 $("#new_count_hidden").val(ncc);
	}
	
	function addOnclick(index){
		document.getElementById('id_add_att'+index).onclick = function() {
			formopen_att(index);
		};
		
	}
		
	function removeOnclick(index){
		document.getElementById('att_id_remove'+index).onclick = function() {
			formopen_re_att(index);
		};
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
		
	$("#department_eqp_view_id").val(id);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
	}
	
	
	
	
</script>


