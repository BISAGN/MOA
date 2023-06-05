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
						<h2>View Hospital OPD & IPD Details</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									View Hospital OPD & IPD Details</li>
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
						<div class="hm-title"><h4 class="hm-title-text">Hospital OPD & IPD view form report</h4></div>
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
		<form:form name="View_Hospital_opd_ipd_form" id="View_Hospital_opd_ipd_form" action=""
			 method="post" modelAttribute="">
				<div class="card-style mb-30">
					<!--    ===========================
		Hospital OPD & IPD Details Start
===========================     -->

					<div class="field-box">
						<div class="row">
						
						<input type="hidden" id="hid_college_infrastructure_remark" name="hid_college_infrastructure_remark" value="0">
						<input type="hidden" id="institute_id" name="institute_id" value="0">
						<input type="hidden" id="main_view_id" name="main_view_id" value="0">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">OPD Patients</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModalopd"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_opdpatient"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="opd_patientstable">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Department</h6></th>
													<th><h6>Jan <span id="jan_cu_yr_opd"></span></h6></th>
													<th><h6>Feb <span id="feb_cu_yr_opd"></span></h6></th>
													<th><h6>March <span id="mar_cu_yr_opd"></span></h6></th>
													<th><h6>April <span id="april_cu_yr_opd"></span></h6></th>
													<th><h6>May <span id="may_cu_yr_opd"></span></h6></th>
													<th><h6>June <span id="june_cu_yr_opd"></span></h6></th>
													<th><h6>July <span id="july_cu_yr_opd"></span></h6></th>
													<th><h6>Aug <span id="aug_cu_yr_opd"></span></h6></th>
													<th><h6>Sept <span id="sept_cu_yr_opd"></span></h6></th>
													<th><h6>Oct <span id="oct_cu_yr_opd"></span></h6></th>
													<th><h6>Nov <span id="nov_cu_yr_opd"></span></h6></th>
													<th><h6>Dec <span id="dec_cu_yr_opd"></span></h6></th>
												</tr>
												<!-- end table row-->
											</thead>
											<tbody id="">
												<c:forEach var="j" items="${View_OPD_Patients1}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.department_name}</p>
													</td>
													<td>
														<p>${j.jan}</p>
													</td>
													<td>
														<p>${j.feb}</p>
													</td>
													<td>
														<p>${j.mar}</p>
													</td>
													<td>
														<p>${j.appr}</p>
													</td>
													<td>
														<p>${j.may}</p>
													</td>
													<td>
														<p>${j.jun}</p>
													</td>
													<td>
														<p>${j.july}</p>
													</td>
													<td>
														<p>${j.aug}</p>
													</td>
													<td>
														<p>${j.sep}</p>
													</td>
													<td>
														<p>${j.oct}</p>
													</td>
													<td>
														<p>${j.nov}</p>
													</td>
													<td>
														<p>${j.dec}</p>
													</td>
													
												</tr>
												
										</c:forEach> 
												<tr>
													<td colspan="2">
														<p>
															<b>Month Total</b>
														</p>
													</td>
													<td><p>${View_OPD_Patientssum1[0].jan}</p></td>
													<td><p>${View_OPD_Patientssum1[0].feb}</p></td>
													<td><p>${View_OPD_Patientssum1[0].mar}</p></td>
													<td><p>${View_OPD_Patientssum1[0].appr}</p></td>
													<td><p>${View_OPD_Patientssum1[0].may}</p></td>
													<td><p>${View_OPD_Patientssum1[0].jun}</p></td>
													<td><p>${View_OPD_Patientssum1[0].july}</p></td>
													<td><p>${View_OPD_Patientssum1[0].aug}</p></td>
													<td><p>${View_OPD_Patientssum1[0].sep}</p></td>
													<td><p>${View_OPD_Patientssum1[0].oct}</p></td>
													<td><p>${View_OPD_Patientssum1[0].nov}</p></td>
													<td><p>${View_OPD_Patientssum1[0].dec}</p></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</section>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Grand Total</label> <span class="value-bind" id="grandtotalld">${View_OPD_Patientssum1[0].grandtotal}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total Wards</label> <span class="value-bind"
										id="total_wordlabel">
										${View_OPD_Patients1[0].total_wardsopd}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>OPD Patients Total</label> <span class="value-bind"
										id="opd_patients_total_label">${View_OPD_Patientssum1[0].opd_patients_total}</span>
								</div>
							</div>
						
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">IPD Patients</h5>
								    <a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModalIpd"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_ipdpatient"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="ipd_patientstable">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Department</h6></th>
													<th><h6>Jan <span id="jan_cu_yr_ipd"></span></h6></th>
													<th><h6>Feb <span id="feb_cu_yr_ipd"></span></h6></th>
													<th><h6>March <span id="mar_cu_yr_ipd"></span></h6></th>
													<th><h6>April <span id="april_cu_yr_ipd"></span></h6></th>
													<th><h6>May <span id="may_cu_yr_ipd"></span></h6></th>
													<th><h6>June <span id="june_cu_yr_ipd"></span></h6></th>
													<th><h6>July <span id="july_cu_yr_ipd"></span></h6></th>
													<th><h6>Aug <span id="aug_cu_yr_ipd"></span></h6></th>
													<th><h6>Sept <span id="sept_cu_yr_ipd"></span></h6></th>
													<th><h6>Oct <span id="oct_cu_yr_ipd"></span></h6></th>
													<th><h6>Nov <span id="nov_cu_yr_ipd"></span></h6></th>
													<th><h6>Dec <span id="dec_cu_yr_ipd"></span></h6></th>
												</tr>
												<!-- end table row-->
											</thead>
										<tbody id="">
												<c:forEach var="j" items="${View_IPD_Patients2}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.department_name}</p>
													</td>
													<td>
														<p>${j.jan}</p>
													</td>
													<td>
														<p>${j.feb}</p>
													</td>
													<td>
														<p>${j.mar}</p>
													</td>
													<td>
														<p>${j.appr}</p>
													</td>
													<td>
														<p>${j.may}</p>
													</td>
													<td>
														<p>${j.jun}</p>
													</td>
													<td>
														<p>${j.july}</p>
													</td>
													<td>
														<p>${j.aug}</p>
													</td>
													<td>
														<p>${j.sep}</p>
													</td>
													<td>
														<p>${j.oct}</p>
													</td>
													<td>
														<p>${j.nov}</p>
													</td>
													<td>
														<p>${j.dec}</p>
													</td>
													
												</tr>
												
										</c:forEach> 
												<tr>
													<td colspan="2">
														<p>
															<b>Month Total</b>
														</p>
													</td>
													<td><p>${View_IPD_Patientssum2[0].jan}</p></td>
													<td><p>${View_IPD_Patientssum2[0].feb}</p></td>
													<td><p>${View_IPD_Patientssum2[0].mar}</p></td>
													<td><p>${View_IPD_Patientssum2[0].appr}</p></td>
													<td><p>${View_IPD_Patientssum2[0].may}</p></td>
													<td><p>${View_IPD_Patientssum2[0].jun}</p></td>
													<td><p>${View_IPD_Patientssum2[0].july}</p></td>
													<td><p>${View_IPD_Patientssum2[0].aug}</p></td>
													<td><p>${View_IPD_Patientssum2[0].sep}</p></td>
													<td><p>${View_IPD_Patientssum2[0].oct}</p></td>
													<td><p>${View_IPD_Patientssum2[0].nov}</p></td>
													<td><p>${View_IPD_Patientssum2[0].dec}</p></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</section>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Grand Total</label> <span class="value-bind" id="">${View_IPD_Patientssum2[0].grandtotal}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>IPD Patients Total</label> <span class="value-bind"
										id="ipd_patients_total_label">${View_IPD_Patientssum2[0].ipd_patients_total}</span>
								</div>
							</div>
						</div>
					</div>

										
					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Bed Days Occupied</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModalBDO"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_bedoccupied"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="bedoccupied_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Department</h6></th>
													<th><h6>
															Jan <span id="jan_cu_yr_bdo"></span>
														</h6></th>
													<th><h6>
															Feb <span id="feb_cu_yr_bdo"></span>
														</h6></th>
													<th><h6>
															March <span id="mar_cu_yr_bdo"></span>
														</h6></th>
													<th><h6>
															April <span id="april_cu_yr_bdo"></span>
														</h6></th>
													<th><h6>
															May <span id="may_cu_yr_bdo"></span>
														</h6></th>
													<th><h6>
															June <span id="june_cu_yr_bdo"></span>
														</h6></th>
													<th><h6>
															July <span id="july_cu_yr_bdo"></span>
														</h6></th>
													<th><h6>
															Aug <span id="aug_cu_yr_bdo"></span>
														</h6></th>
													<th><h6>
															Sept <span id="sept_cu_yr_bdo"></span>
														</h6></th>
													<th><h6>
															Oct <span id="oct_cu_yr_bdo"></span>
														</h6></th>
													<th><h6>
															Nov <span id="nov_cu_yr_bdo"></span>
														</h6></th>
													<th><h6>
															Dec <span id="dec_cu_yr_bdo"></span>
														</h6></th>
												</tr>
												<!-- end table row-->
											</thead>
										<tbody id="">
												<c:forEach var="j" items="${View_Bed_Days_Occupied3}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.department_name}</p>
													</td>
													<td>
														<p>${j.jan}</p>
													</td>
													<td>
														<p>${j.feb}</p>
													</td>
													<td>
														<p>${j.mar}</p>
													</td>
													<td>
														<p>${j.appr}</p>
													</td>
													<td>
														<p>${j.may}</p>
													</td>
													<td>
														<p>${j.jun}</p>
													</td>
													<td>
														<p>${j.july}</p>
													</td>
													<td>
														<p>${j.aug}</p>
													</td>
													<td>
														<p>${j.sep}</p>
													</td>
													<td>
														<p>${j.oct}</p>
													</td>
													<td>
														<p>${j.nov}</p>
													</td>
													<td>
														<p>${j.dec}</p>
													</td>
													
												</tr>
												
										</c:forEach> 
												<tr>
													<td colspan="2">
														<p>
															<b>Month Total</b>
														</p>
													</td>
													<td><p>${View_Bed_Days_Occupiedsum3[0].jan}</p></td>
													<td><p>${View_Bed_Days_Occupiedsum3[0].feb}</p></td>
													<td><p>${View_Bed_Days_Occupiedsum3[0].mar}</p></td>
													<td><p>${View_Bed_Days_Occupiedsum3[0].appr}</p></td>
													<td><p>${View_Bed_Days_Occupiedsum3[0].may}</p></td>
													<td><p>${View_Bed_Days_Occupiedsum3[0].jun}</p></td>
													<td><p>${View_Bed_Days_Occupiedsum3[0].july}</p></td>
													<td><p>${View_Bed_Days_Occupiedsum3[0].aug}</p></td>
													<td><p>${View_Bed_Days_Occupiedsum3[0].sep}</p></td>
													<td><p>${View_Bed_Days_Occupiedsum3[0].oct}</p></td>
													<td><p>${View_Bed_Days_Occupiedsum3[0].nov}</p></td>
													<td><p>${View_Bed_Days_Occupiedsum3[0].dec}</p></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</section>
							
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Grand Total</label> <span class="value-bind" id="">${View_Bed_Days_Occupiedsum3[0].grandtotal}</span>
								</div>
							</div>
						</div>
					</div>
					
					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Beds Existed</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModalBE"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_bedoccupied"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="bedoccupied_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Department</h6></th>
													<th><h6>Existing Beds for UG</h6></th>
													<th><h6>
															Additional Bed for Existing PG (Clinical Dept)
														</h6></th>
													<th><h6>
															Total Available Beds
														</h6></th>
												</tr>
												<!-- end table row-->
											</thead>
										<tbody id="">
												<c:forEach var="j" items="${View_Beds_Existed4}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.department_name}</p>
													</td>
													<td>
														<p>${j.existbed}</p>
													</td>
													<td>
														<p>${j.addionalbed}</p>
													</td>
													<td>
														<p>${j.totalbed}</p>
													</td>
																																							
												</tr>
												
										</c:forEach> 
												
											</tbody>
										</table>
									</div>
								</div>
							</section>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total Number of Beds Existed in the last calendar year</label> <span class="value-bind" id="grandtotalld">${View_Beds_Existed4[0].grand_totalexisted}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Bed Occupancy in % (P X 100 / 365 X B)</label> <span class="value-bind"
										id="total_bed_occupancy">
										${View_Beds_Existed4[0].bed_occupancy}</span>
								</div>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Upload Documents</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModalDU"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none" id="no_uploaded_doc"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="table_uploaded_doc">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Document Name</h6></th>
													<th><h6>View</h6></th>
												</tr>
											</thead>
											<tbody id="">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Add Diet & ECG Document</p>
													</td>
													<td>
														<input type="hidden" id="medicalqualificationAtt_id" value="${docdata[0][0]}"  
														name="medicalqualificationAtt_id" class="form-control"> 
														<ul class="buttons-group">
															<li><a 
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="support_doc_view"><i class="lni lni-eye"></i></a></li>
														</ul>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>X-ray/USG Register of last one calendar year</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a 
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="xray_usg_regview"><i class="lni lni-eye"></i></a></li>
														</ul>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>OPD and IPD Register of last one calendar year</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a 
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="opd_ipd_reg_view"><i class="lni lni-eye"></i></a></li>
														</ul>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Medicine Stock register OPD and IPD of last one
															calendar year</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a 
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="stock_reg_view"><i class="lni lni-eye"></i></a></li>
														</ul>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Lab Investigation register for OPD and IPD of last
															one calendar year</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a 
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="invest_opd_ipd_reg_view"><i class="lni lni-eye"></i></a></li>
														</ul>
													</td>
												</tr>

											</tbody>
										</table>
									</div>
								</div>
							</section>
						</div>
					</div>
					<!--    ===========================
		Hospital OPD & IPD Details End
===========================     -->
				</div>
				
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModalopd">
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
								<textarea placeholder="Enter Remarks" rows="5" id="hos_opd_ipd_opd_patients_rmk" name="hos_opd_ipd_opd_patients_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="hos_opd_remark_btn">Submit</a>
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
	id="exampleModalIpd">
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
								<textarea placeholder="Enter Remarks" rows="5" id="hos_opd_ipd_ipd_patients_rmk" name="hos_opd_ipd_ipd_patients_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="hos_ipd_remark_btn">Submit</a>
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
	id="exampleModalBDO">
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
								<textarea placeholder="Enter Remarks" rows="5" id="hos_opd_ipd_bed_day_occu_rmk" name="hos_opd_ipd_bed_day_occu_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="hos_bdo_remark_btn">Submit</a>
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
	id="exampleModalBE">
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
								<textarea placeholder="Enter Remarks" rows="5" id="hos_opd_ipd_bed_existed_rmk" name="hos_opd_ipd_bed_existed_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="hos_BE_remark_btn">Submit</a>
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
	id="exampleModalDU">
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
								<textarea placeholder="Enter Remarks" rows="5" id="hos_opd_ipd_upload_doc_rmk" name="hos_opd_ipd_upload_doc_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="hos_DU_remark_btn">Submit</a>
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


$.ajaxSetup({
		async : false
	});
	
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
	$("#hos_opd_ipd_opd_patients_rmk").val('${getView_idFrom_Institute_id[0].hos_opd_ipd_opd_patients_rmk}');
	$("#hos_opd_ipd_ipd_patients_rmk").val('${getView_idFrom_Institute_id[0].hos_opd_ipd_ipd_patients_rmk}');
	$("#hos_opd_ipd_bed_day_occu_rmk").val('${getView_idFrom_Institute_id[0].hos_opd_ipd_bed_day_occu_rmk}');
	$("#hos_opd_ipd_bed_existed_rmk").val('${getView_idFrom_Institute_id[0].hos_opd_ipd_bed_existed_rmk}');
	$("#hos_opd_ipd_upload_doc_rmk").val('${getView_idFrom_Institute_id[0].hos_opd_ipd_upload_doc_rmk}');
	
}

hos_opd_ipd_getcurrent_yr();
	
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
	document.getElementById('hos_opd_remark_btn').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('hos_ipd_remark_btn').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('hos_bdo_remark_btn').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('hos_BE_remark_btn').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('hos_DU_remark_btn').onclick = function() {
		Save_As_Draft_Remarks();
	};
	}
	
// 	document View

	document.getElementById('support_doc_view').onclick = function() {
		file_view($("#medicalqualificationAtt_id").val(),"clg_reg_hos_opd_ipd_upload_documents","add_diet_ecg_doc");
	};
	document.getElementById('xray_usg_regview').onclick = function() {
		file_view($("#medicalqualificationAtt_id").val(),"clg_reg_hos_opd_ipd_upload_documents","xray_usg_opdipd");
	};
	document.getElementById('opd_ipd_reg_view').onclick = function() {
		file_view($("#medicalqualificationAtt_id").val(),"clg_reg_hos_opd_ipd_upload_documents","register_opdipd");
	};
	document.getElementById('stock_reg_view').onclick = function() {
		file_view($("#medicalqualificationAtt_id").val(),"clg_reg_hos_opd_ipd_upload_documents","medi_stock_opdipd");
	};
	document.getElementById('invest_opd_ipd_reg_view').onclick = function() {
		file_view($("#medicalqualificationAtt_id").val(),"clg_reg_hos_opd_ipd_upload_documents","last_inveopdipd");
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
		
		var form_data = new FormData(document.getElementById("View_Hospital_opd_ipd_form"));
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
	    		$('#exampleModalopd').modal('hide');
	    		$('#exampleModalIpd').modal('hide');
	    		$('#exampleModalBDO').modal('hide');
	    		$('#exampleModalBE').modal('hide');
	    		$('#exampleModalDU').modal('hide');
	      }
	      else{
	    	  alert(j);
	    	    $('#exampleModalopd').modal('hide');
	    		$('#exampleModalIpd').modal('hide');
	    		$('#exampleModalBDO').modal('hide');
	    		$('#exampleModalBE').modal('hide');
	    		$('#exampleModalDU').modal('hide');
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


//view Document year
function hos_opd_ipd_getcurrent_yr(){
	$("#jan_cu_yr_opd").text( (new Date).getFullYear());
	$("#feb_cu_yr_opd").text( (new Date).getFullYear());
	$("#mar_cu_yr_opd").text( (new Date).getFullYear());
	$("#april_cu_yr_opd").text( (new Date).getFullYear());
	$("#may_cu_yr_opd").text( (new Date).getFullYear());
	$("#june_cu_yr_opd").text( (new Date).getFullYear());
	$("#july_cu_yr_opd").text( (new Date).getFullYear());
	$("#aug_cu_yr_opd").text( (new Date).getFullYear());
	$("#sept_cu_yr_opd").text( (new Date).getFullYear());
	$("#oct_cu_yr_opd").text( (new Date).getFullYear());
	$("#nov_cu_yr_opd").text( (new Date).getFullYear());
	$("#dec_cu_yr_opd").text( (new Date).getFullYear());
	
	$("#jan_cu_yr_ipd").text( (new Date).getFullYear());
	$("#feb_cu_yr_ipd").text( (new Date).getFullYear());
	$("#mar_cu_yr_ipd").text( (new Date).getFullYear());
	$("#april_cu_yr_ipd").text( (new Date).getFullYear());
	$("#may_cu_yr_ipd").text( (new Date).getFullYear());
	$("#june_cu_yr_ipd").text( (new Date).getFullYear());
	$("#july_cu_yr_ipd").text( (new Date).getFullYear());
	$("#aug_cu_yr_ipd").text( (new Date).getFullYear());
	$("#sept_cu_yr_ipd").text( (new Date).getFullYear());
	$("#oct_cu_yr_ipd").text( (new Date).getFullYear());
	$("#nov_cu_yr_ipd").text( (new Date).getFullYear());
	$("#dec_cu_yr_ipd").text( (new Date).getFullYear());
	
	$("#jan_cu_yr_bdo").text( (new Date).getFullYear());
	$("#feb_cu_yr_bdo").text( (new Date).getFullYear());
	$("#mar_cu_yr_bdo").text( (new Date).getFullYear());
	$("#april_cu_yr_bdo").text( (new Date).getFullYear());
	$("#may_cu_yr_bdo").text( (new Date).getFullYear());
	$("#june_cu_yr_bdo").text( (new Date).getFullYear());
	$("#july_cu_yr_bdo").text( (new Date).getFullYear());
	$("#aug_cu_yr_bdo").text( (new Date).getFullYear());
	$("#sept_cu_yr_bdo").text( (new Date).getFullYear());
	$("#oct_cu_yr_bdo").text( (new Date).getFullYear());
	$("#nov_cu_yr_bdo").text( (new Date).getFullYear());
	$("#dec_cu_yr_bdo").text( (new Date).getFullYear());
}

</script>

