<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- hemburgermenu css start -->
<link rel="stylesheet" href="assets/vendor/hamburger-menu/hemburgermenu.css">
<!-- hemburgermenu css end -->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>View Department Equipments</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									View Department Equipments</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

    <div class="form-elements-wrapper tunnel-form preview-form custom-mobile-menu">
           <!-- humburger menu for collage regulation - part-1 start -->
			<div class="row viewmenu" >
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="hamburger-menu">
						<div class="bar" title="Click here for menu">
						</div>
						<div class="hm-title"><h4 class="hm-title-text">Department Equipments View Form Report</h4></div>
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
			<form:form name="View_Department_Equipments" id="View_Department_Equipments" action="View_Department_EquipmentsAction" method="post" modelAttribute="View_Department_EquipmentsCMD">
				<div class="card-style mb-30">
						<!--    ===========================
		Department Equipments Start
===========================     -->

<div class="field-box">
						<div class="row">
						
						<input type="hidden" id="hid_college_infrastructure_remark" name="hid_college_infrastructure_remark" value="0">
						<input type="hidden" id="institute_id" name="institute_id" value="0">
						<input type="hidden" id="main_view_id" name="main_view_id" value="0">
						
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Obstetric & Gynacology
										</h5>
										<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_obstr_gyna"><i class="lni lni-pencil-alt"></i></a>
								</div>
								
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total Equipments for Identification</label> <span
										class="value-bind" id=""> ${Obstric[0].no_of_equip_identi }</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Additional Items</label> <span class="value-bind" id="">
									${Obstric[0].additional_item }
									</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<input type="hidden" id="View_Alcohol_License_hid" value="${Obstric[0].id}" name="View_Alcohol_License_hid"/>
								<div class="custom-data-value">
									<label>Copy of Alcohol License, Anatomy Act</label> <span
										class="value-bind daobtn" id=""> <a href="#"
										class="main-btn dark-btn btn-hover btn-sm btnview"
										title="View" id="View_Alcohol_License_btn"><i class="lni lni-eye"></i></a></span>
								</div>
							</div>
							
							
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<input type="hidden" id="View_Photographs_hid" value="${Obstric[0].id}" name="View_Photographs_hid"/>
								<div class="custom-data-value">
									<label>Photographs of Cadavers & Mummified Bodies</label> <span
										class="value-bind daobtn" id=""> <a href="#"
										class="main-btn dark-btn btn-hover btn-sm btnview"
										title="View" id="View_Photographs_btn"><i class="lni lni-eye"></i></a></span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<input type="hidden" id="View_Purchase_Bill_hid" value="${Obstric[0].id}" name="View_Purchase_Bill_hid"/>
								<div class="custom-data-value">
									<label>Purchase Bill of Departmental Equipments</label> <span
										class="value-bind daobtn" id=""> <a href="#"
										class="main-btn dark-btn btn-hover btn-sm btnview"
										title="View" id="View_Purchase_Bill_btn" ><i class="lni lni-eye"></i></a></span>
								</div>
							</div>


						</div>
					</div>



					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Anatomy</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_dep_equip_anatomy_rmk"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_anadepeqi"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="anatomy_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Items</h6></th>
													<th><h6>Available(Number/ Quantity)</h6></th>
												</tr>

											</thead>
											<tbody id="">
											<c:forEach var="j" items="${Anatomy}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${j.item_name }</p>
													</td>
													<td><p>${j.available_num_ana }</p></td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</section>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total Additional Items</label> <span class="value-bind"
										id="">${AnatomyChild[0].total_add_item } </span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total Cadavers Available</label> <span
										class="value-bind" id=""> ${AnatomyChild[0].total_cadavers_avai }</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>College covered under the provisions of Anatomy
										Act.</label> <span class="value-bind" id=""> ${AnatomyChild[0].anatomy_act }</span>
								</div>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Community Medicine</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_dep_equip_cm_medi_rmk"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_cmdepeqi"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="comm_med_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Items</h6></th>
													<th><h6>Available(Number/ Quantity)</h6></th>
												</tr><div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
								</div>
							</div>

											</thead>
											<tbody id="">
											<c:forEach var="j" items="${Community_Medicine}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${j.item_name }</p>
													</td>
													<td><p>${j.available_num_cm }</p></td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</section>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Family Welfare devices</label> <span class="value-bind"
										id=""> ${Community_Medicine_Child[0].family_welfare } </span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Sources of Nutrition</label> <span class="value-bind"
										id="">  ${Community_Medicine_Child[0].source_nutrition }</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Sources of Vitamins</label> <span class="value-bind"
										id=""> ${Community_Medicine_Child[0].source_vitamin } </span>
								</div>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Forensic Medicine &
										Toxicology</h5>
										<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_dep_equip_equip_dtl_rmk"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_fmtdepeqi"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="fometo_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Items</h6></th>
													<th><h6>Available(Number/ Quantity)</h6></th>
												</tr>

											</thead>
											<tbody id="">
											<c:forEach var="j" items="${Forensic_Medicine_ToxicologyEquip}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${j.item_name }</p>
													</td>
													<td><p>${j.available_num_quant }</p></td>
												</tr>
												</c:forEach>
												<!-- end table row -->
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
									<h5 class="custom-title-tag">Acts / legislations
										(including Medico legal) & Regulations</h5>
										<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_dep_equip_acts_regu_rmk"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_actfometo"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="actfometo_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Items</h6></th>
													<th><h6>Available(Number/ Quantity)</h6></th>
												</tr>

											</thead>
											<tbody id="">
											<c:forEach var="j" items="${getForensic_Medicine_ToxicologyAct}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${j.act_item_name }</p>
													</td>
													<td><p>${j.act_available_num_quant }</p></td>
												</tr>
												</c:forEach>
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
									<h5 class="custom-title-tag">Homoeopathic Pharmacy
										</h5>
										<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_dep_equip_hom_phar_rmk"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_hophdepeqi"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="hoph_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Items</h6></th>
													<th><h6>Available(Number/ Quantity)</h6></th>
												</tr>

											</thead>
											<tbody id="">
											<c:forEach var="j" items="${getHomeo_Pharm}"
  												varStatus="num">  
												<tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${j.item_name }</p>
													</td>
													<td><p>${j.available_num_quant }</p></td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</section>
							
<!-- 							<div class="col-lg-12 col-md-12 col-sm-12 col-12"> -->
<!-- 								<div class="custom-data-value custom-title custom-title-bg"> -->
<!-- 									<h5 class="custom-title-tag">Herbal Garden</h5> -->
<!-- 								</div> -->
<!-- 							</div> -->

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total Species Planted</label> <span class="value-bind"
										id=""> ${getHomeo_Pharm_Child[0].no_species_planted }</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Plants on Pots</label> <span class="value-bind" id="">
									${getHomeo_Pharm_Child[0].no_pots }
									</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Irrigation Facility</label> <span class="value-bind"
										id="irrigation_facility_label"> ${getHomeo_Pharm_Child[0].irrigation_facility }</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Educational Tour Conducted</label> <span
										class="value-bind" id="educational_tour_conducted_label"> ${getHomeo_Pharm_Child[0].educational_tour_conducted }</span>
								</div>
							</div>
						</div>
					</div>
					
					
                  <div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Organon of Medicine
										</h5>
										<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_dep_equip_organon_medi_rmk"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_ormeddepeqi"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="organmed_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Founder & Philosophers Name</h6></th>
													<th><h6>Photographs</h6></th>
													<th><h6>Date of Birth</h6></th>
													<th><h6>Date of Death</h6></th>
													<th><h6>Available(Number/ Quantity)</h6></th>
												</tr>

											</thead>
											<tbody id="">
											<c:forEach var="j" items="${getEquip_organon_medicine}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${j.founder_philoso_name }</p>
													</td>

													<td><img class="d-block img5050 imageZomm"
														alt="No Image" src="kmlFileDownloadFinalDynemic44?kmlId2=${j.id}&val444=8&fildname1=5"></td>
													<td><p>${j.date_of_birth }</p></td>
													<td><p>${j.date_of_death }</p></td>
													<td><p>${j.available_num_quant }</p></td>
												</tr>
												</c:forEach>
												<tr>

<!-- 													<td colspan="2"> -->
<!-- 														<p> -->
<!-- 															<b>Others</b> -->
<!-- 														</p> -->
<!-- 													</td> -->
<!-- 													<td colspan="4"><p>10</p></td> -->
<!-- 												</tr> -->

<!-- 												<tr> -->

<!-- 													<td colspan="2"> -->
<!-- 														<p> -->
<!-- 															<b>Other Charts on Philosophy</b> -->
<!-- 														</p> -->
<!-- 													</td> -->
<!-- 													<td colspan="4"><p>10</p></td> -->
<!-- 												</tr> -->
<!-- 												<tr> -->

<!-- 													<td colspan="2"> -->
<!-- 														<p> -->
<!-- 															<b>Charts on History of Medicine</b> -->
<!-- 														</p> -->
<!-- 													</td> -->
<!-- 													<td colspan="4"><p>10</p></td> -->
<!-- 												</tr> -->
<!-- 												<tr> -->
<!-- 													<td colspan="2"> -->
<!-- 														<p> -->
<!-- 															<b>Books on History of Medicine</b> -->
<!-- 														</p> -->
<!-- 													</td> -->
<!-- 													<td colspan="4"><p>10</p></td> -->
<!-- 												</tr> -->

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
									<h5 class="custom-title-tag">Pathalogy & Microbiology
										</h5>
										<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_dep_equip_patho_micro_rmk"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_pamicrodepeqi"></span>
							</div>
							<section class="single-detail-block">
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
											<c:forEach var="j" items="${pathology_microbiology}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${j.item_name }</p>
													</td>
													<td><p>${j.available_num_quant }</p></td>
												</tr>
												</c:forEach>
												<!-- end table row -->
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
									<h5 class="custom-title-tag">Physiology & Biochemistry
										</h5>
										<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_dep_equip_physio_biocheme_rmk"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_phymicrodepeqi"></span>
							</div>
							<section class="single-detail-block">
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
											<c:forEach var="j" items="${Physiology_Biochemistry}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${j.item_name }</p>
													</td>
													<td><p>${j.available_num_quant }</p></td>
												</tr>
												</c:forEach>
												<!-- end table row -->
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
									<h5 class="custom-title-tag">Biochemistry </h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_dep_equip_biochemestry_rmk"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_biochedepeqi"></span>
							</div>
							<section class="single-detail-block">
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
											<c:forEach var="j" items="${Biochemistry}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${j.item_name }</p>
													</td>

													<td>
														<p>${j.available_num_quant }</p>
													</td>
												</tr>
												</c:forEach>
												<!-- end table row -->
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
									<h5 class="custom-title-tag">Practice of Medicine
										</h5>
										<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_dep_equip_prac_of_medici_rmk"><i class="lni lni-pencil-alt"></i></a>
								</div>
								
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total Equipments for Identification</label> <span
										class="value-bind" id="">${Practice_MedView[0].no_equip_identi } </span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Additional Items</label> <span class="value-bind" id="">${Practice_MedView[0].addi_item }
									</span>
								</div>
							</div>
						</div>
					</div>


		<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Repertory </h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_dep_equip_repetory_rmk"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total Computers</label> <span class="value-bind" id="">
									${getRepertoryView[0].total_num_comp }</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total Software</label> <span class="value-bind" id="">
									${getRepertoryView[0].total_num_software }</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Details of Computer Lab.</label> <span
										class="value-bind" id=""> ${getRepertoryView[0].detail_comp_lab }</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Other Items</label> <span class="value-bind" id="">
									${getRepertoryView[0].other_item }</span>
								</div>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Surgery </h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_dep_equip_surgery_rmk"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total Equipments for Identification</label> <span
										class="value-bind" id="">${getSurgeryView[0].no_equip_identi } </span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Additional Items</label> <span class="value-bind" id="">${getSurgeryView[0].addi_item }
									</span>
								</div>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Homoeopathic Materia Medica
										</h5>
										<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_dep_equip_hom_mat_medi_rmk"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Charts on medicines in Hom. Materia Medica
										interesting charts on Ophidia groups, Spider family, Nosodes
										and Sarcodes.</label> <span class="value-bind" id="">${getHMMView[0].charts } </span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total Number of Charts for Typical Picture-wise
										presentation of drugs.</label> <span class="value-bind" id="">${getHMMView[0].total_charts }
									</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Additional Items</label> <span class="value-bind" id="">${getHMMView[0].addi_item }
									</span>
								</div>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Psychiatry(PG) </h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_dep_equip_psychiatry_rmk"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total Charts</label> <span class="value-bind" id="">${getPsychView[0].total_charts }
									</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total Models</label> <span class="value-bind" id="">${getPsychView[0].total_models }
									</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total books in Departmental Library</label> <span
										class="value-bind" id="">${getPsychView[0].num_depart_lib } </span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Other Items</label> <span class="value-bind" id="">${getPsychView[0].other_items }
									</span>
								</div>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Pediatrics(PG) </h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_dep_equip_pediatrics_rmk"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total Charts</label> <span class="value-bind" id="">${getPediatricView[0].total_charts }
									</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total Models</label> <span class="value-bind" id="">${getPediatricView[0].total_models }
									</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total books in Departmental Library</label> <span
										class="value-bind" id="">${getPediatricView[0].num_depart_lib } </span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Other Items</label> <span class="value-bind" id="">${getPediatricView[0].other_items }
									</span>
								</div>
							</div>
						</div>
					</div>


<!-- 					<div class="field-box"> -->
<!-- 						<div class="row"> -->
<!-- 							<div class="col-lg-12 col-md-12 col-sm-12 col-12"> -->
<!-- 								<div class="custom-data-value custom-title custom-title-bg"> -->
<!-- 									<h5 class="custom-title-tag">Herbal Garden</h5> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12"> -->
<!-- 								<div class="custom-data-value"> -->
<!-- 									<label>Total Species Planted</label> <span class="value-bind" -->
<%-- 										id=""> ${getHomeo_Pharm_Child[0].no_species_planted }</span> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12"> -->
<!-- 								<div class="custom-data-value"> -->
<!-- 									<label>Plants on Pots</label> <span class="value-bind" id=""> -->
<%-- 									${getHomeo_Pharm_Child[0].no_pots } --%>
<!-- 									</span> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12"> -->
<!-- 								<div class="custom-data-value"> -->
<!-- 									<label>Irrigation Facility</label> <span class="value-bind" -->
<%-- 										id="irrigation_facility_label"> ${getHomeo_Pharm_Child[0].irrigation_facility }</span> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12"> -->
<!-- 								<div class="custom-data-value"> -->
<!-- 									<label>Educational Tour Conducted</label> <span -->
<%-- 										class="value-bind" id="educational_tour_conducted_label"> ${getHomeo_Pharm_Child[0].educational_tour_conducted }</span> --%>
<!-- 								</div> -->
<!-- 							</div> -->


<!-- 						</div> -->
<!-- 					</div> -->

					
					
					<!--    ===========================
		Department Equipments End
===========================     -->
				</div>
				
				<!-- Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_obstr_gyna">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea id="dep_equip_obstr_gyna_rmk" name="dep_equip_obstr_gyna_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="dep_equip_obstr_gyna_rmk_btn">Submit</a>
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
	id="exampleModal_dep_equip_anatomy_rmk">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" >
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea id="dep_equip_anatomy_rmk" name="dep_equip_anatomy_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="dep_equip_anatomy_rmk_btn">Submit</a>
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
	id="exampleModal_dep_equip_cm_medi_rmk">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea id="dep_equip_cm_medi_rmk" name="dep_equip_cm_medi_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="dep_equip_cm_medi_rmk_btn">Submit</a>
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
	id="exampleModal_dep_equip_equip_dtl_rmk">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" >
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea id="dep_equip_equip_dtl_rmk" name="dep_equip_equip_dtl_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="dep_equip_equip_dtl_rmk_btn">Submit</a>
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
	id="exampleModal_dep_equip_acts_regu_rmk">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" >
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea id="dep_equip_acts_regu_rmk" name="dep_equip_acts_regu_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="dep_equip_acts_regu_rmk_btn">Submit</a>
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
	id="exampleModal_dep_equip_hom_phar_rmk">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" >
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea id="dep_equip_hom_phar_rmk" name="dep_equip_hom_phar_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="dep_equip_hom_phar_rmk_btn">Submit</a>
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
	id="exampleModal_dep_equip_organon_medi_rmk">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" >
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea id="dep_equip_organon_medi_rmk" name="dep_equip_organon_medi_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="dep_equip_organon_medi_rmk_btn">Submit</a>
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
	id="exampleModal_dep_equip_patho_micro_rmk">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" >
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea id="dep_equip_patho_micro_rmk" name="dep_equip_patho_micro_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="dep_equip_patho_micro_rmk_btn">Submit</a>
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
	id="exampleModal_dep_equip_physio_biocheme_rmk">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" >
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea id="dep_equip_physio_biocheme_rmk" name="dep_equip_physio_biocheme_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="dep_equip_physio_biocheme_rmk_btn">Submit</a>
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
	id="exampleModal_dep_equip_biochemestry_rmk">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" >
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea id="dep_equip_biochemestry_rmk" name="dep_equip_biochemestry_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="dep_equip_biochemestry_rmk_btn">Submit</a>
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
	id="exampleModal_dep_equip_prac_of_medici_rmk">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea id="dep_equip_prac_of_medici_rmk" name="dep_equip_prac_of_medici_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="dep_equip_prac_of_medici_rmk_btn">Submit</a>
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
	id="exampleModal_dep_equip_repetory_rmk">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea id="dep_equip_repetory_rmk" name="dep_equip_repetory_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="dep_equip_repetory_rmk_btn">Submit</a>
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
	id="exampleModal_dep_equip_surgery_rmk">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea id="dep_equip_surgery_rmk" name="dep_equip_surgery_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="dep_equip_surgery_rmk_btn">Submit</a>
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
	id="exampleModal_dep_equip_hom_mat_medi_rmk">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea id="dep_equip_hom_mat_medi_rmk" name="dep_equip_hom_mat_medi_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="dep_equip_hom_mat_medi_rmk_btn">Submit</a>
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
	id="exampleModal_dep_equip_psychiatry_rmk">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea  id="dep_equip_psychiatry_rmk" name="dep_equip_psychiatry_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="dep_equip_psychiatry_rmk_btn">Submit</a>
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
	id="exampleModal_dep_equip_pediatrics_rmk">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea  id="dep_equip_pediatrics_rmk" name="dep_equip_pediatrics_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="dep_equip_pediatrics_rmk_btn">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->


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
				
			</form:form>
		</div>
	</div>
</section>

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
<form:form action="${viewUrl}" method="post" id="viewForm_clg_staff_list"
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
<c:url value="View_Hospital_Equipment_ReportUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_hos_eqp"
	name="viewForm" modelAttribute="hos_eqp_id">
	<input type="hidden" name="hos_eqp_id" id="hos_eqp_id" value="0" />
</form:form>
<c:url value="Declaration_ViewUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_declaration"
	name="viewForm" modelAttribute="declaration_id">
	<input type="hidden" name="declaration_id" id="declaration_id" value="0" />
</form:form>

<!-- hemburgermenu js start -->
<script src="assets/vendor/hamburger-menu/hemburgermenu.js" type="text/javascript"></script>
<!-- hemburgermenu css end -->

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	
	if('${role}'!="NCH"){
		$(".btnedit").css("visibility", "hidden");
		$(".viewmenu").addClass("d-none");
		

	}
	
				var irrigation_facility ="";
				irrigation_facility = "${getHomeo_Pharm_Child[0].irrigation_facility}";
				if(irrigation_facility == "1"){
					irrigation_facility="No"
				}
				if(irrigation_facility == "0"){
					irrigation_facility="Yes"
				}
				
				var educational_tour_conducted ="";
				educational_tour_conducted = "${getHomeo_Pharm_Child[0].educational_tour_conducted}";
				if(educational_tour_conducted == "1"){
					educational_tour_conducted="No"
				}
				if(educational_tour_conducted == "0"){
					educational_tour_conducted="Yes"
				}
				
				$("#irrigation_facility_label").text(irrigation_facility);
				$("#educational_tour_conducted_label").text(educational_tour_conducted);

				$("#institute_id").val('${inst_id}');
				$("#main_view_id").val('${main_view_id}');

				//GET MAIN ID AND REMARKS
				var getView_idFrom_Institute_id ='${getView_idFrom_Institute_id}';
				if(getView_idFrom_Institute_id != "[]"){
					$("#hid_college_infrastructure_remark").val('${getView_idFrom_Institute_id[0].id}');
					
					$("#dep_equip_obstr_gyna_rmk").val('${getView_idFrom_Institute_id[0].dep_equip_obstr_gyna_rmk}');
					$("#dep_equip_anatomy_rmk").val('${getView_idFrom_Institute_id[0].dep_equip_anatomy_rmk}');
					$("#dep_equip_cm_medi_rmk").val('${getView_idFrom_Institute_id[0].dep_equip_cm_medi_rmk}');
					$("#dep_equip_equip_dtl_rmk").val('${getView_idFrom_Institute_id[0].dep_equip_equip_dtl_rmk}');
					$("#dep_equip_acts_regu_rmk").val('${getView_idFrom_Institute_id[0].dep_equip_acts_regu_rmk}');
					$("#dep_equip_hom_phar_rmk").val('${getView_idFrom_Institute_id[0].dep_equip_hom_phar_rmk}');
					$("#dep_equip_organon_medi_rmk").val('${getView_idFrom_Institute_id[0].dep_equip_organon_medi_rmk}');
					$("#dep_equip_patho_micro_rmk").val('${getView_idFrom_Institute_id[0].dep_equip_patho_micro_rmk}');
					$("#dep_equip_physio_biocheme_rmk").val('${getView_idFrom_Institute_id[0].dep_equip_physio_biocheme_rmk}');
					$("#dep_equip_biochemestry_rmk").val('${getView_idFrom_Institute_id[0].dep_equip_biochemestry_rmk}');
					$("#dep_equip_prac_of_medici_rmk").val('${getView_idFrom_Institute_id[0].dep_equip_prac_of_medici_rmk}');
					$("#dep_equip_repetory_rmk").val('${getView_idFrom_Institute_id[0].dep_equip_repetory_rmk}');
					$("#dep_equip_surgery_rmk").val('${getView_idFrom_Institute_id[0].dep_equip_surgery_rmk}');
					$("#dep_equip_hom_mat_medi_rmk").val('${getView_idFrom_Institute_id[0].dep_equip_hom_mat_medi_rmk}');
					$("#dep_equip_psychiatry_rmk").val('${getView_idFrom_Institute_id[0].dep_equip_psychiatry_rmk}');
					$("#dep_equip_pediatrics_rmk").val('${getView_idFrom_Institute_id[0].dep_equip_pediatrics_rmk}');
					
				}
				
	$.ajaxSetup({
		async : false
	});
});


document.addEventListener('DOMContentLoaded', function() {
	

	if('${role}'=="NCH"){
		
	document.getElementById('dep_equip_obstr_gyna_rmk_btn').onclick = function() {
		Save_As_Remarks();
	};
	document.getElementById('dep_equip_anatomy_rmk_btn').onclick = function() {
		Save_As_Remarks();
	};
	document.getElementById('dep_equip_cm_medi_rmk_btn').onclick = function() {
		Save_As_Remarks();
	};
	document.getElementById('dep_equip_equip_dtl_rmk_btn').onclick = function() {
		Save_As_Remarks();
	};
	document.getElementById('dep_equip_acts_regu_rmk_btn').onclick = function() {
		Save_As_Remarks();
	};
	document.getElementById('dep_equip_hom_phar_rmk_btn').onclick = function() {
		Save_As_Remarks();
	};
	document.getElementById('dep_equip_organon_medi_rmk_btn').onclick = function() {
		Save_As_Remarks();
	};
	document.getElementById('dep_equip_patho_micro_rmk_btn').onclick = function() {
		Save_As_Remarks();
	};
	document.getElementById('dep_equip_physio_biocheme_rmk_btn').onclick = function() {
		Save_As_Remarks();
	};
	document.getElementById('dep_equip_biochemestry_rmk_btn').onclick = function() {
		Save_As_Remarks();
	};
	document.getElementById('dep_equip_prac_of_medici_rmk_btn').onclick = function() {
		Save_As_Remarks();
	};
	document.getElementById('dep_equip_repetory_rmk_btn').onclick = function() {
		Save_As_Remarks();
	};
	document.getElementById('dep_equip_surgery_rmk_btn').onclick = function() {
		Save_As_Remarks();
	};
	document.getElementById('dep_equip_hom_mat_medi_rmk_btn').onclick = function() {
		Save_As_Remarks();
	};
	document.getElementById('dep_equip_psychiatry_rmk_btn').onclick = function() {
		Save_As_Remarks();
	};
	document.getElementById('dep_equip_pediatrics_rmk_btn').onclick = function() {
		Save_As_Remarks();
	};
	
}
	
	document.getElementById('View_Alcohol_License_btn').onclick = function() {
		file_view($("#View_Alcohol_License_hid").val(),"clg_reg_dep_equip_obstetric_and_gynacology","copy_of_alchoho");
	};
	
	document.getElementById('View_Photographs_btn').onclick = function() {
		file_view($("#View_Photographs_hid").val(),"clg_reg_dep_equip_obstetric_and_gynacology","photographs_of_cadavers");
	};
	
	document.getElementById('View_Purchase_Bill_btn').onclick = function() {
		file_view($("#View_Purchase_Bill_hid").val(),"clg_reg_dep_equip_obstetric_and_gynacology","upload_purchase_bill");
	};

	
});


function Save_As_Remarks() {

	$.ajaxSetup({
	    async: false
	});	
		
		var form_data = new FormData(document.getElementById("View_Department_Equipments"));
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
	    	  	$('#exampleModal_obstr_gyna').modal('hide');
	    		$('#exampleModal_dep_equip_anatomy_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_cm_medi_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_equip_dtl_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_acts_regu_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_hom_phar_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_organon_medi_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_patho_micro_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_physio_biocheme_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_biochemestry_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_prac_of_medici_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_repetory_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_surgery_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_hom_mat_medi_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_psychiatry_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_pediatrics_rmk').modal('hide');
	    		
	    		
	      }
	      else{
	    	  alert(j);
	    	    $('#exampleModal_obstr_gyna').modal('hide');
	    		$('#exampleModal_dep_equip_anatomy_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_cm_medi_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_equip_dtl_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_acts_regu_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_hom_phar_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_organon_medi_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_patho_micro_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_physio_biocheme_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_biochemestry_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_prac_of_medici_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_repetory_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_surgery_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_hom_mat_medi_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_psychiatry_rmk').modal('hide');
	    		$('#exampleModal_dep_equip_pediatrics_rmk').modal('hide');
	      }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	}
	

		
		
		//FOR C:URL

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
		}

		});

		function ViewData_basic_info(){
			
			var institute_id  = $("#institute_id").val();
			
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
	//document view
		function file_view(id,val,field) {
			 $("#exampleModal").modal('show');
			 var pdf1="CommonDocPartOne?kmlId2="+id+"&val444="+val+"&fildname1="+field+" ";
			 $("#pdfmodelcanvas") .attr('src',pdf1);
		}

</script>
