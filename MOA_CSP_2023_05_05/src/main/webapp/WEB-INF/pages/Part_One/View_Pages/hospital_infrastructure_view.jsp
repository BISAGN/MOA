<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script src="assets/vendor/hamburger-menu/hemburgermenu.js" type="text/javascript"></script>
<link rel="stylesheet" href="assets/vendor/hamburger-menu/hemburgermenu.css">

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>View Hospital Infrastructure</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									View Hospital Infrastructure</li>
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
						<div class="hm-title"><h4 class="hm-title-text">Hospital Infrastructure view form report</h4></div>
					</div>

					<nav class="mobile-menu">
						<h5 class="hm-menu-title">Form Reports Links</h5>
						<ul>
							<li><a id="basics_information_view">Institution Information</a></li>
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
	<form:form name="View_College_Hosp_infra" id="View_College_Hosp_infra" action=""
			 method="post" modelAttribute="View_College_Hosp_infra_CMD">
				<div class="card-style mb-30">
					<div class="field-box">
			<input type="hidden" id="hid_college_infrastructure_remark" name="hid_college_infrastructure_remark" value="0">
						<input type="hidden" id="institute_id" name="institute_id" value="0">
						<input type="hidden" id="main_view_id" name="main_view_id" value="0">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
										<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Hospital Administration
										Infrastructure</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#hos_administ"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_admiinfadepeqi"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="admininfra_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Departments</h6></th>
													<th><h6>
															Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
														</h6></th>
												</tr>

											</thead>
											<tbody id="">
												<c:forEach var="j" items="${View_Hospital_Administrator1}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.hospital_department_name}</p>
													</td>
													<td>
														<p>${j.available_area}</p>
													</td>
																																		
												</tr>
												
										</c:forEach> 

													<td colspan="2">
														<p>
															<b>Total Constructed Area in Admin Block</b>
														</p>
													</td>
													<td>
														<p>${View_Hospital_Administrator1[0].total_avail_area}</p>
													</td>
												</tr>

												<!-- end table row -->
											</tbody>
										</table>
									</div>
								</div>
							</section>
							<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value">
									<label>Does the teaching hospital of the college
										fulfill the Statutory Requirements of the concerned
										State/Union Territory/Local Authority to establish and run the
										hospital?</label> <span class="value-bind" id="council_check_label">${View_Infast_rede1[0].statutory_requirements_for_hospital}</span>
								</div>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Hospital OPD Infrastructure</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#opddept"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_opdinfadepeqi"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="opdinfra_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Departments</h6></th>
													<th><h6>
															Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
														</h6></th>
												</tr>

											</thead>
											<tbody id="">
													<c:forEach var="j" items="${View_Hospital_OPD2}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.opd_department_name}</p>
													</td>
													<td>
														<p>${j.available_area}</p>
													</td>
																																		
												</tr>
												
										</c:forEach> 

												<tr>

													<td colspan="2">
														<p>
															<b>Total Constructed Area in OPD Block</b>
														</p>
													</td>
													<td>
														<p>${View_Hospital_OPD2[0].total_avail_area}</p>
													</td>
												</tr>
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
								<div
									class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Hospital IPD Infrastructure</h5>
									<a href="#"	class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag"
										title="Remarks" data-bs-toggle="modal"data-bs-target="#ipddept">
										<i class="lni lni-pencil-alt"></i></a>
								</div>

							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_ipdinfadepeqi"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="ipd_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Departments</h6></th>
													<th><h6>
															Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
														</h6></th>
												</tr>

											</thead>
											<tbody id="">
												<c:forEach var="j" items="${View_Hospital_IPD3}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.ipd_department_name}</p>
													</td>
													<td>
														<p>${j.available_area}</p>
													</td>
																																		
												</tr>
												
										</c:forEach> 

												<tr>
													<td colspan="2">
														<p>
															<b>Total Constructed Area in IPD Block</b>
														</p>
													</td>
													<td>
														<p>${View_Hospital_IPD3[0].total_avail_area}</p>
													</td>
												</tr>

												<!-- end table row -->
											</tbody>
										</table>
									</div>
								</div>
							</section>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Casualty Department</label> <span class="value-bind" id="casualty_dept_ipd">${View_Hospital_IPD3[0].casualty_dept}</span>
								</div>
							</div>
							<input type="hidden" id="casualty_dept_doc_view_btn_hid" value="${View_Hospital_IPD3[0].doc_id}"  
														name="casualty_dept_doc_view_btn_hid" class="form-control"> 
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12" id="hidden_casultyservice_document">
								<div class="custom-data-value">
									<label>If Casulty Services available upload details here</label> <span class="value-bind" id="casualty_ser_doc"><a 
										   class="main-btn dark-btn btn-hover btn-sm btnview" title="View" id="casualty_dept_doc_view_btn"><i class="lni lni-eye"></i></a></span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Publication Treatment Outcome in Ipd/Opd</label> <span class="value-bind" id="publi_treat_ipd_lbl">${View_Hospital_IPD3[0].tretment_outcome_ipd}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12" id="hidden_treat_outcome_document">
								<div class="custom-data-value">
									<label>If Publication Treatment Outcome in Ipd/Opd available upload details here </label> <span class="value-bind" id="casualty_ser_doc"><a 
										   class="main-btn dark-btn btn-hover btn-sm btnview" title="View" id="publi_treat_ipd_doc_view_btn"><i class="lni lni-eye"></i></a></span>
								</div>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
										<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Operation Theatre Infrastructure</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#otdept"><i class="lni lni-pencil-alt"></i></a>
								
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_opetheinfra"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="ot_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Departments</h6></th>
													<th><h6>
															Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
														</h6></th>
												</tr>

											</thead>
											<tbody id="">
												<c:forEach var="j" items="${View_Hospital_OT4}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.ot_department_name}</p>
													</td>
													<td>
														<p>${j.available_area}</p>
													</td>
																																		
												</tr>
												
										</c:forEach> 

												<tr>

													<td colspan="2">
														<p>
															<b>Total Constructed Area in Operation Theatre Block</b>
														</p>
													</td>
													<td>
														<p>${View_Hospital_OT4[0].total_avail_area}</p>
													</td>
												</tr>
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
									<h5 class="custom-title-tag">Rehabilitation Unit including
										Physiotherapy & Yoga Infrastructure</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#rehabunit"><i class="lni lni-pencil-alt"></i></a>
							
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_yogaphyinfra"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="reh_py_yoga_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Departments</h6></th>
													<th><h6>
															Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
														</h6></th>
												</tr>

											</thead>
											<tbody id="">
												<c:forEach var="j" items="${View_Hospital_Reha_unit5}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.department_name}</p>
													</td>
													<td>
														<p>${j.available_area}</p>
													</td>
																																		
												</tr>
												
										</c:forEach> 


												<tr>
													<td colspan="2">
														<p><B>Total Constructed Area in Rehabilitation Unit
															including Physiotherapy & Yoga</B></p>
													</td>
													<td>
														<p>${View_Hospital_Reha_unit5[0].total_avail_area}</p>
													</td>
												</tr>

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
									<h5 class="custom-title-tag">Clinical Laboratory
										Infrastructure</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#clinical"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_yogaphyinfra"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="cl_pm_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Departments</h6></th>
													<th><h6>
															Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
														</h6></th>
												</tr>

											</thead>
											<tbody id="">
												<c:forEach var="j" items="${View_Hospital_CL6}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.department_name}</p>
													</td>
													<td>
														<p>${j.available_area}</p>
													</td>
																																		
												</tr>
												
										</c:forEach> 

												<tr>
													<td colspan="2">
																<p><b>Total Constructed Area in Clinical Laboratory</b>		</p>
													</td>
													<td>
														<p>${View_Hospital_CL6[0].total_avail_area}</p>
													</td>
												</tr>
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
									<h5 class="custom-title-tag">Radiology or Sonography
										Infrastructure</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#radioso"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_radsonoinfra"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="radiologist_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Departments</h6></th>
													<th><h6>
															Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
														</h6></th>
												</tr>

											</thead>
											<tbody id="">
												<c:forEach var="j" items="${View_Hospital_Radiology_Sonography7}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.department_name}</p>
													</td>
													<td>
														<p>${j.available_area}</p>
													</td>
																																		
												</tr>
												
										</c:forEach> 

													<td colspan="2">
														<p>
															<b>Total Constructed Area in Radiology or Sonography
																Block</b>
														</p>
													</td>
													<td>
														<p>${View_Hospital_Radiology_Sonography7[0].total_avail_area}</p>
													</td>
												</tr>

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
									<h5 class="custom-title-tag">Hospital Kitchen and Canteen Block</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#kitche"><i class="lni lni-pencil-alt"></i></a>
								</div>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_radsonoinfra"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="radiologist_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Departments</h6></th>
													<th><h6>
															Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
														</h6></th>
												</tr>

											</thead>
											<tbody id="">
												<c:forEach var="j" items="${View_Hospital_Kitchen_Canteen8}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.kitchen_department_name}</p>
													</td>
													<td>
														<p>${j.available_area}</p>
													</td>
																																		
												</tr>
												
										</c:forEach> 

													<td colspan="2">
														<p>
															<b>Total Constructed Area in Kitchen and Canteen Block (in Sq. mtr)</b>
														</p>
													</td>
													<td>
														<p>${View_Hospital_Kitchen_Canteen8[0].total_avail_area}</p>
													</td>
												</tr>

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
									<h5 class="custom-title-tag">Store Details</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#stored"><i class="lni lni-pencil-alt"></i></a>
								</div>
									
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_radsonoinfra"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="radiologist_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Departments</h6></th>
													<th><h6>
															Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
														</h6></th>
												</tr>

											</thead>
											<tbody id="">
												<c:forEach var="j" items="${View_Hospital_Hosp_Stores9}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.store_department_name}</p>
													</td>
													<td>
														<p>${j.available_area}</p>
													</td>
																																		
												</tr>
												
										</c:forEach> 

													<td colspan="2">
														<p>
															<b>Total Constructed Area in Store Block (in Sq. mtr)</b>
														</p>
													</td>
													<td>
														<p>${View_Hospital_Hosp_Stores9[0].total_avail_area}</p>
													</td>
													<tr>
													<td colspan="2">
														<p>
															<b>Grand Total Constructed Area of the Hospital (in Sq. mtr)</b>
														</p>
													</td>
													<td>
														<p>${View_Hospital_Hosp_Stores9[0].grand_total_avail_area}</p>
													</td>
												</tr>

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
									<h5 class="custom-title-tag">Other Infrastructure</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#otherdtl"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_otherinfra"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="otherinfra_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Departments</h6></th>
													<th><h6>
															Available Area (in Sq. mtr.)<strong class="mandatory">*</strong>
														</h6></th>
												</tr>

											</thead>
											<tbody id="">
													<c:forEach var="j" items="${View_Hospital_Infrastructure_Details10}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.department_name}</p>
													</td>
													<td>
														<p>${j.available_area}</p>
													</td>
																																		
												</tr>
												
										</c:forEach> 
											</tbody>
										</table>
									</div>
								</div>
							</section>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-4 col-md-6 col-sm-12 col-12">
							<div class="custom-data-value">
								<label>Ambulance Services</label> <span class="value-bind"
									id="ambulance_serv_label"></span>
							</div>
						</div>
						<div class="col-lg-4 col-md-6 col-sm-12 col-12">
						<input type="hidden" id="ambulance_serv_view_hid" value="${View_Hospital_IPD3[0].amb_id}"  
														name="ambulance_serv_view_hid" class="form-control"> 
							<div class="custom-data-value">
								<label>If Ambulance Services available upload details here</label> <span class="value-bind" id="ambulance_serv"><a 
										   class="main-btn dark-btn btn-hover btn-sm btnview" title="View" id="ambulance_serv_view_btn"><i class="lni lni-eye"></i></a></span>
							</div>
						</div>
						<div class="col-lg-4 col-md-6 col-sm-12 col-12">
							<div class="custom-data-value">
								<label>Sitting arrangement for internees/students in
									Various Out Patient Department</label> <span class="value-bind"
									id="sitting_arrangment_label"></span>
							</div>
						</div>
						<div class="col-lg-4 col-md-6 col-sm-12 col-12">
							<div class="custom-data-value">
								<label>Central Research Laboratory</label> <span
									class="value-bind" id="central_research_lab_label"></span>
							</div>
						</div>
						<div class="col-lg-4 col-md-6 col-sm-12 col-12">
							<div class="custom-data-value">
								<label>Casualty Department</label> <span class="value-bind"
									id="casualty_dept_label"></span>
							</div>
						</div>
					</div>

					<!--    ===========================
		Hospital Infrastructure End
===========================     -->
			
				
<!-- Model start 1-->

<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="hos_administ">
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
								<textarea placeholder="Enter Remarks" rows="5" id="hosp_infra_hos_admi_rmk" name="hosp_infra_hos_admi_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="hosp_infra_hos_admi_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 	Model End -->
<!-- Model start 1-->

<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="opddept">
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
								<textarea placeholder="Enter Remarks" rows="5" id="hosp_infra_hos_opd_rmk" name="hosp_infra_hos_opd_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="hosp_infra_hos_opd_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 	Model End -->
<!-- Model start 1-->

<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="ipddept">
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
								<textarea placeholder="Enter Remarks" rows="5" id="hosp_infra_hos_ipd_rmk" name="hosp_infra_hos_ipd_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="hosp_infra_hos_ipd_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 	Model End -->
<!-- Model start 1-->

<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="otdept">
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
								<textarea placeholder="Enter Remarks" rows="5" id="hosp_infra_ot_rmk" name="hosp_infra_ot_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="hosp_infra_ot_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 	Model End -->
<!-- Model start 1-->

<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="rehabunit">
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
								<textarea placeholder="Enter Remarks" rows="5" id="hosp_infra_rehab_uni_rmk" name="hosp_infra_rehab_uni_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="hosp_infra_rehab_uni_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 	Model End -->
<!-- Model start 1-->

<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="clinical">
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
								<textarea placeholder="Enter Remarks" rows="5" id="hosp_infra_clini_lab_rmk" name="hosp_infra_clini_lab_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="hosp_infra_clini_lab_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 	Model End -->
<!-- Model start 1-->

<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="radioso">
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
								<textarea placeholder="Enter Remarks" rows="5" id="hosp_infra_radio_sono_rmk" name="hosp_infra_radio_sono_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="hosp_infra_radio_sono_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 	Model End -->
<!-- Model start 1-->

<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="kitche">
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
								<textarea placeholder="Enter Remarks" rows="5" id="hosp_infra_hos_kitchen_rmk" name="hosp_infra_hos_kitchen_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="hosp_infra_hos_kitchen_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 	Model End -->
<!-- Model start 1-->

<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="stored">
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
								<textarea placeholder="Enter Remarks" rows="5" id="hosp_infra_store_dtl_rmk" name="hosp_infra_store_dtl_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="hosp_infra_store_dtl_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 	Model End -->
<!-- Model start 10-->

<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="otherdtl">
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
								<textarea placeholder="Enter Remarks" rows="5" id="hosp_infra_other_infra_dtl_rmk" name="hosp_infra_other_infra_dtl_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="hosp_infra_other_infra_dtl_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 	Model End -->
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

<script src="assets/vendor/hamburger-menu/hemburgermenu.js" type="text/javascript"></script>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
// 	alert('${getInstname[0].institute_name}')
	if('${role}'!="NCH"){
		$(".btnedit").css("visibility", "hidden");
		$(".viewmenu").addClass("d-none");
		

	}
				
$.ajaxSetup({
		async : false
	});
	
$("#institute_id").val('${inst_id}');
$("#main_view_id").val('${main_view_id}');


	var ambulance_serv ="";
	ambulance_serv = "${View_Hospital_Infra_Details11[0].ambulance_serv}";
	if(ambulance_serv == "1"){
		ambulance_serv="Yes"
	}
	if(ambulance_serv == "0"){
		ambulance_serv="No"
	}
	
	var sitting_arrangment ="";
	sitting_arrangment = "${View_Hospital_Infra_Details11[0].sitting_arrangment}";
	if(sitting_arrangment == "1"){
		sitting_arrangment="Yes"
	}
	if(sitting_arrangment == "0"){
		sitting_arrangment="No"
	}
	
	var central_research_lab ="";
	central_research_lab = "${View_Hospital_Infra_Details11[0].central_research_lab}";
	if(central_research_lab == "1"){
		central_research_lab="Yes"
	}
	if(central_research_lab == "0"){
		central_research_lab="No"
	}
	
	var casualty_dept ="";
	casualty_dept = "${View_Hospital_Infra_Details11[0].casualty_dept}";
	if(casualty_dept == "1"){
		casualty_dept="Yes"
	}
	if(casualty_dept == "0"){
		casualty_dept="No"
	}

$("#ambulance_serv_label").text(ambulance_serv);
$("#sitting_arrangment_label").text(sitting_arrangment);
$("#central_research_lab_label").text(central_research_lab);
$("#casualty_dept_label").text(casualty_dept);

var casualty_dept_ipd ="";
casualty_dept_ipd = "${View_Hospital_IPD3[0].casualty_dept}";
if(casualty_dept_ipd == "1"){
	casualty_dept_ipd="Yes"
$("div#hidden_casultyservice_document").show();
}
if(casualty_dept_ipd == "0"){
	casualty_dept_ipd="No"
 $("div#hidden_casultyservice_document").hide();
}	
$("#casualty_dept_ipd").text(casualty_dept_ipd);

var publi_treat_ipd_lbl ="";
publi_treat_ipd_lbl = "${View_Hospital_IPD3[0].treatment_outcome_ipd}";
if(publi_treat_ipd_lbl == "1"){
	publi_treat_ipd_lbl="Yes"
   $("div#hidden_treat_outcome_document").show();
}
if(publi_treat_ipd_lbl == "0"){
	publi_treat_ipd_lbl="No"
   $("div#hidden_treat_outcome_document").hide();
}	
$("#publi_treat_ipd_lbl").text(publi_treat_ipd_lbl);


var council_check_label ="";
council_check_label = "${View_Infast_rede1[0].statutory_requirements_for_hospital}";
if(council_check_label == "1"){
	council_check_label="Yes"
}
if(council_check_label == "0"){
	council_check_label="No"
}	
$("#council_check_label").text(council_check_label);

//GET MAIN ID AND REMARKS
var getView_idFrom_Institute_id ='${getView_idFrom_Institute_id}';
if(getView_idFrom_Institute_id != "[]"){
	$("#hid_college_infrastructure_remark").val('${getView_idFrom_Institute_id[0].id}');
	$("#hosp_infra_hos_admi_rmk").val('${getView_idFrom_Institute_id[0].hosp_infra_hos_admi_rmk}');
	$("#hosp_infra_hos_opd_rmk").val('${getView_idFrom_Institute_id[0].hosp_infra_hos_opd_rmk}');
	$("#hosp_infra_hos_ipd_rmk").val('${getView_idFrom_Institute_id[0].hosp_infra_hos_ipd_rmk}');
	$("#hosp_infra_ot_rmk ").val('${getView_idFrom_Institute_id[0].hosp_infra_ot_rmk }');
	$("#hosp_infra_rehab_uni_rmk").val('${getView_idFrom_Institute_id[0].hosp_infra_rehab_uni_rmk}');
	$("#hosp_infra_clini_lab_rmk").val('${getView_idFrom_Institute_id[0].hosp_infra_clini_lab_rmk}');
	$("#hosp_infra_radio_sono_rmk").val('${getView_idFrom_Institute_id[0].hosp_infra_radio_sono_rmk}');
	$("#hosp_infra_hos_kitchen_rmk").val('${getView_idFrom_Institute_id[0].hosp_infra_hos_kitchen_rmk}');
	$("#hosp_infra_store_dtl_rmk").val('${getView_idFrom_Institute_id[0].hosp_infra_store_dtl_rmk}');
	$("#hosp_infra_other_infra_dtl_rmk").val('${getView_idFrom_Institute_id[0].hosp_infra_other_infra_dtl_rmk}');
}
	
});
document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('hosp_infra_hos_admi_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('hosp_infra_hos_opd_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('hosp_infra_hos_ipd_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('hosp_infra_ot_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('hosp_infra_rehab_uni_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('hosp_infra_clini_lab_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('hosp_infra_radio_sono_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('hosp_infra_hos_kitchen_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('hosp_infra_store_dtl_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('hosp_infra_other_infra_dtl_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	

	});


	function Save_As_Draft_Remarks() {

	$.ajaxSetup({
	    async: false
	});	
		
		var form_data = new FormData(document.getElementById("View_College_Hosp_infra"));
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
	    	  	$('#hos_administ').modal('hide');
	    	  	$('#opddept').modal('hide');
	    	  	$('#ipddept').modal('hide');
	    	  	$('#otdept').modal('hide');
	    	  	$('#rehabunit').modal('hide');
	    	  	$('#clinical').modal('hide');
	    	  	$('#radioso').modal('hide');
	    	  	$('#kitche').modal('hide');
	    	  	$('#stored').modal('hide');
	    	  	$('#otherdtl').modal('hide');
	      }
	      else{
	    	  alert(j);
	    		$('#hos_administ').modal('hide');
	    	  	$('#opddept').modal('hide');
	    	  	$('#ipddept').modal('hide');
	    	  	$('#otdept').modal('hide');
	    	  	$('#rehabunit').modal('hide');
	    	  	$('#clinical').modal('hide');
	    	  	$('#radioso').modal('hide');
	    	  	$('#kitche').modal('hide');
	    	  	$('#stored').modal('hide');
	    	  	$('#otherdtl').modal('hide');
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
		
		///doc view
		document.getElementById('casualty_dept_doc_view_btn').onclick = function() {
			file_view($("#casualty_dept_doc_view_btn_hid").val(),"clg_reg_hosp_other_ipd_detail","ipd_casulty_document");
		};
		document.getElementById('publi_treat_ipd_doc_view_btn').onclick = function() {
			file_view($("#casualty_dept_doc_view_btn_hid").val(),"clg_reg_hosp_other_ipd_detail","treatment_outcome_ipd_document");
		};
		document.getElementById('ambulance_serv_view_btn').onclick = function() {
			file_view($("#ambulance_serv_view_hid").val(),"clg_reg_other_infra_details","ambulance_document");
		};

	});
	
	//view Document

	function file_view(id,val,field) {
		 $("#exampleModal").modal('show');
		 var pdf1="CommonDocPartOne?kmlId2="+id+"&val444="+val+"&fildname1="+field+" ";
		 $("#pdfmodelcanvas") .attr('src',pdf1);
	}
	
	function ViewData_basic_info(){
		
		var institute_id  = $("#institute_id").val();
		
// 		$("#basic_info_id").val(institute_id);
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



</script>



