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
							<h2>Hospital OPD & IPD Details</h2>
						</div>
					</div>
					<!-- end col -->
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="breadcrumb-wrapper mb-30">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="#0">Dashboard</a></li>
									<li class="breadcrumb-item active" aria-current="page">
										Hospital OPD & IPD Details</li>
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
							Hospital OPD & IPD Details Start
						=========================== -->
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<!-- input style start -->
						<div class="card-style mb-30">
							<div class="tabs content_h750">
								<!-- OPD Patients Start -->
								<div class="tab">
									<button class="tab-toggle" id="defaultOpen">OPD
										Patients</button>
								</div>
								<div class="content">
									<h4 class="heading">OPD Patients</h4>
									<form:form name="depart_ipd_opd" id="depart_ipd_opd" action="" method="post" class=""
										commandName="">
										
												<div class="row">
												
												<input type="hidden" id="basic_info_id" name="basic_info_id" value="0">
												
													<div class="col-lg-12 col-md-12 col-sm-12 col-12">
														<div class="table-wrapper table-responsive custom-table">
															<table class="table" id="opd_patientstable">
																<thead>
																	<tr>
																		<th><h6>Sr No</h6></th>
																		<th><h6>Department</h6></th>
																		<th><h6>
																				Jan <span id="jan_cu_yr_opd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Feb <span id="feb_cu_yr_opd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				March <span id="mar_cu_yr_opd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				April <span id="april_cu_yr_opd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				May <span id="may_cu_yr_opd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				June <span id="june_cu_yr_opd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				July <span id="july_cu_yr_opd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Aug <span id="aug_cu_yr_opd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Sept <span id="sept_cu_yr_opd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Oct <span id="oct_cu_yr_opd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Nov <span id="nov_cu_yr_opd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Dec <span id="dec_cu_yr_opd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																	</tr>
																	<!-- end table row-->
																</thead>
																<tbody id="">
																<c:if test="${empty getHosptal_opd_ipd_TableDepartmentFetch}">
																	${getHosptal_opd_ipd_TableDepartment}
																</c:if>
																<c:if test="${not empty getHospital_ipd_TableDepartment}">
																	${getHosptal_opd_ipd_TableDepartmentFetch}
																</c:if>
<%-- 																	${getHosptal_opd_ipd_TableDepartment} --%>

																	<!-- end table row -->
																</tbody>
															</table>
														</div>
														<!-- end card -->
													</div>
													<!-- end col -->
												
										</div>
										<hr>
										<div class="row">
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Grand Total<strong
														class="mandatory">*</strong>
													</label> <input type="text" name="grand_totalopd"
														id="grand_totalopd" class="form-control" value=""
														placeholder="Grand Total">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Total Wards<strong
														class="mandatory">*</strong>
													</label> <input type="text" name="total_wardsopd"
														id="total_wardsopd" class="form-control"
														placeholder="Total Wards">
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>OPD Patients Total<strong
														class="mandatory">*</strong>
													</label> 
													<label id="opd_patients_total_label"></label> 
<!-- 													<input type="text" name="opd_patients_total_label" -->
<!-- 														id="opd_patients_total_label" class="form-control" value="" -->
<!-- 														placeholder="Grand Total"> -->
<!-- 													<span class="value-bind" id="opd_patients_total_label"></span> -->
														
												</div>
											</div>
											
<!-- 											<input type="text" id="gm_hidden" -->
<!-- 																		name="gm_hidden" value="0" -->
<!-- 																		class="form-control "> -->
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover btnsave"
															type="button" id="department" value="Save"></li>
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
								<!-- OPD Patients End -->
								<!-- IPD Patients Start -->
								<div class="tab">
									<button class="tab-toggle">IPD Patients</button>
								</div>
								<div class="content">
									<h4 class="heading">IPD Patients</h4>
									<form:form name="depart_ipd_patients_form" id="depart_ipd_patients_form" action="" method="post" class=""
										commandName="">
										<div class="row">
											<div class="tables-wrapper">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12 col-12">
														<div class="table-wrapper table-responsive custom-table">
															<table class="table" id="ipd_patientstable">
																<thead>
																	<tr>
																		<th><h6>Sr No</h6></th>
																		<th><h6>Department</h6></th>
																		<th><h6>
																				Jan <span id="jan_cu_yr_ipd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Feb <span id="feb_cu_yr_ipd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				March <span id="mar_cu_yr_ipd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				April <span id="april_cu_yr_ipd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				May <span id="may_cu_yr_ipd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				June <span id="june_cu_yr_ipd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				July <span id="july_cu_yr_ipd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Aug <span id="aug_cu_yr_ipd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Sept <span id="sept_cu_yr_ipd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Oct <span id="oct_cu_yr_ipd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Nov <span id="nov_cu_yr_ipd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Dec <span id="dec_cu_yr_ipd"></span><strong class="mandatory">*</strong>
																			</h6></th>
																	</tr>
																	<!-- end table row-->
																</thead>
																<tbody id="">
																<c:if test="${empty getHospital_ipd_patientTableDepart_Fetch}">
																	${getHospital_ipd_TableDepartment}
																</c:if>
																<c:if test="${not empty getHospital_ipd_TableDepartment}">
																	${getHospital_ipd_patientTableDepart_Fetch}
																</c:if>
<%-- 																	${getHospital_ipd_TableDepartment} --%>
																
																	<!-- end table row -->
																</tbody>
															</table>
														</div>
														<!-- end card -->
													</div>
													<!-- end col -->
												</div>
												<!-- end row -->
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Grand Total<strong
														class="mandatory">*</strong>
													</label> <input type="text" name="grand_totalipd"
														id="grand_totalipd" class="form-control"
														placeholder="Grand Total">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>IPD Patients Total<strong class="mandatory">*</strong></label> 
<!-- 													<input type="text" name="ipd_patients_total_label" -->
<!-- 														id="ipd_patients_total_label" class="form-control" -->
<!-- 														placeholder="IPD Patients Total"> -->
<!-- 													<span class="value-bind" id="ipd_patients_total_label"></span> -->
                                                        <label id="ipd_patients_total_label"></label> 
												</div>
											</div>
<!-- 									<div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
<!-- 								        <div class="custom-data-value"> -->
<!-- 									    <label>IPD Patients Total</label> <span class="value-bind" -->
<%-- 										id="ipd_patients_total_label">${getInstname[0].institute_name}</span> --%>
<!-- 								       </div> -->
<!-- 							       </div> -->
											

										</div>
										
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover"
															type="button" value="Save" id="ipd_patients_btn"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- UG End-->
								<!-- PG Start -->
								<div class="tab">
									<button class="tab-toggle">Bed Days Occupied</button>
								</div>
								<div class="content">
									<h4 class="heading">Bed Days Occupied</h4>
									<form:form name="Bed_Days_Occupied_Formid" id="Bed_Days_Occupied_Formid" action="" method="post" class=""
										commandName="">
												<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="inst_block simple-instruction">
								<strong>Instruction :</strong> To calculate total number of bed days occupied during the last month, please calculate the date wise total number of patients, remained on
                                bed at midnight.
							    </div>
							    </div>
							 						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
														<div class="table-wrapper table-responsive custom-table">
															<table class="table" id="bedoccupied_table">
																<thead>
																	<tr>
																		<th><h6>Sr No</h6></th>
																		<th><h6>Department</h6></th>
																			<th><h6>
																				Jan <span id="jan_cu_yr_bdo"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Feb <span id="feb_cu_yr_bdo"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				March <span id="mar_cu_yr_bdo"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				April <span id="april_cu_yr_bdo"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				May <span id="may_cu_yr_bdo"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				June <span id="june_cu_yr_bdo"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				July <span id="july_cu_yr_bdo"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Aug <span id="aug_cu_yr_bdo"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Sept <span id="sept_cu_yr_bdo"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Oct <span id="oct_cu_yr_bdo"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Nov <span id="nov_cu_yr_bdo"></span><strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Dec <span id="dec_cu_yr_bdo"></span><strong class="mandatory">*</strong>
																			</h6></th>
																	</tr>
																	<!-- end table row-->
																</thead>
																<tbody id="">
																<c:if test="${empty getHospital_Bed_Days_Occupied_TableDepartFetch}">
																	${getHospital_Bed_Days_Occupied_TableDepart}
																</c:if>
																<c:if test="${not empty getHospital_Bed_Days_Occupied_TableDepart}">
																	${getHospital_Bed_Days_Occupied_TableDepartFetch}
																</c:if>
<%-- 																${getHospital_Bed_Days_Occupied_TableDepart} --%>

																	<!-- end table row -->
																</tbody>
															</table>
														</div>
														<!-- end card -->
													</div>
													<!-- end col -->
												
										</div>
										<hr>
										<div class="row">
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Grand Total<strong
														class="mandatory">*</strong>
													</label> <input type="text" name="grand_totalbdo"
														id="grand_totalbdo" value="" class="form-control"
														placeholder="Grand Total">
												</div>
											</div>

										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover"
															type="button" value="Save" id="bdo_btn"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- PG End -->
								<!-- Beds Existed Start -->
								<div class="tab">
									<button class="tab-toggle">Beds Existed</button>
								</div>
								<div class="content">
									<h4 class="heading">Beds Existed</h4>
									<form:form name="Bed_Existed_Formid" id="Bed_Existed_Formid" action="" method="post" class=""
										commandName="">
										
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<div class="table-wrapper table-responsive custom-table">
														<table class="table" id="bedsexisted_table">
															<thead>
																<tr>
																	<th><h6>
																			Sr No<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Department<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Existing Beds for UG<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Additional Bed for Existing PG (Clinical Dept)<strong
																				class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Total Available Beds<strong class="mandatory">*</strong>
																		</h6></th>
																</tr>
																<!-- end table row-->
															</thead>
															<tbody id="">
															<c:if test="${empty getHospital_Bed_ExistedDepartFetch}">
																	${getHospital_Bed_ExistedDepart}
																</c:if>
																<c:if test="${not empty getHospital_Bed_ExistedDepart}">
																	${getHospital_Bed_ExistedDepartFetch}
																</c:if>
<%-- 															${getHospital_Bed_ExistedDepart} --%>
															
<!-- 																<tr> -->
<!-- 																	<td class="sr-no"> -->
<!-- 																		<p></p> -->
<!-- 																	</td> -->
<!-- 																	<td> -->
<!-- 																		<p>General Medicine</p> -->
<!-- 																	</td> -->
<!-- 																	<td> -->
<!-- 																		<div class="input-style-1"> -->
<!-- 																			<input type="text" value="90" name="gm_existbed" -->
<!-- 																				id="gm_existbed" class="form-control" -->
<!-- 																				placeholder="Total UG Beds"> -->
<!-- 																		</div> -->
<!-- 																	</td> -->
<!-- 																	<td> -->
<!-- 																		<div class="input-style-1"> -->
<!-- 																			<input type="text" value="100" name="gm_addionalbed" -->
<!-- 																				id="gm_addionalbed" class="form-control" -->
<!-- 																				placeholder="Total PG Beds"> -->
<!-- 																		</div> -->
<!-- 																	</td> -->
<!-- 																	<td> -->
<!-- 																		<div class="input-style-1"> -->
<!-- 																			<input type="text" value="190" name="gm_totalbed" -->
<!-- 																				id="gm_totalbed" class="form-control" -->
<!-- 																				placeholder="Total Available Beds"> -->
<!-- 																		</div> -->
<!-- 																	</td> -->
<!-- 																</tr> -->

<!-- 																<tr> -->
<!-- 																	<td class="sr-no"> -->
<!-- 																		<p></p> -->
<!-- 																	</td> -->
<!-- 																	<td> -->

<!-- 																		<p>Obstetrics & Gynecology</p> -->

<!-- 																	</td> -->
<!-- 																	<td> -->
<!-- 																		<div class="input-style-1"> -->
<!-- 																			<input type="text" value="60" name="og_existbed" -->
<!-- 																				id="og_existbed" class="form-control" -->
<!-- 																				placeholder="Total UG Beds"> -->
<!-- 																		</div> -->
<!-- 																	</td> -->
<!-- 																	<td> -->
<!-- 																		<div class="input-style-1"> -->
<!-- 																			<input type="text" value="80" name="og_addionalbed" -->
<!-- 																				id="og_addionalbed" class="form-control" -->
<!-- 																				placeholder="Total PG Beds"> -->
<!-- 																		</div> -->
<!-- 																	</td> -->
<!-- 																	<td> -->
<!-- 																		<div class="input-style-1"> -->
<!-- 																			<input type="text" value="130" name="og_totalbed" -->
<!-- 																				id="og_totalbed" class="form-control" -->
<!-- 																				placeholder="Total Available Beds"> -->
<!-- 																		</div> -->
<!-- 																	</td> -->
<!-- 																</tr> -->

<!-- 																<tr> -->
<!-- 																	<td class="sr-no"> -->
<!-- 																		<p></p> -->
<!-- 																	</td> -->
<!-- 																	<td> -->
<!-- 																		<p>Pediatrics</p> -->
<!-- 																	</td> -->
<!-- 																	<td> -->
<!-- 																		<div class="input-style-1"> -->
<!-- 																			<input type="text" name="pediatrics_existbed" -->
<!-- 																				id="pediatrics_existbed" value="40" class="form-control" -->
<!-- 																				placeholder="Total UG Beds"> -->
<!-- 																		</div> -->
<!-- 																	</td> -->
<!-- 																	<td> -->
<!-- 																		<div class="input-style-1"> -->
<!-- 																			<input type="text" name="pediatrics_addionalbed" -->
<!-- 																				id="pediatrics_addionalbed" value="80" class="form-control" -->
<!-- 																				placeholder="Total PG Beds"> -->
<!-- 																		</div> -->
<!-- 																	</td> -->
<!-- 																	<td> -->
<!-- 																		<div class="input-style-1"> -->
<!-- 																			<input type="text" name="pediatrics_totalbed" -->
<!-- 																				id="pediatrics_totalbed" value="120" class="form-control" -->
<!-- 																				placeholder="Total Available Beds"> -->
<!-- 																		</div> -->
<!-- 																	</td> -->
<!-- 																</tr> -->

<!-- 																<tr> -->
<!-- 																	<td class="sr-no"> -->
<!-- 																		<p></p> -->
<!-- 																	</td> -->
<!-- 																	<td> -->

<!-- 																		<p>Surgery</p> -->

<!-- 																	</td> -->
<!-- 																	<td> -->
<!-- 																		<div class="input-style-1"> -->
<!-- 																			<input type="text" name="surgery_existbed" -->
<!-- 																				id="surgery_existbed" value="30" class="form-control" -->
<!-- 																				placeholder="Total UG Beds"> -->
<!-- 																		</div> -->
<!-- 																	</td> -->
<!-- 																	<td> -->
<!-- 																		<div class="input-style-1"> -->
<!-- 																			<input type="text" name="surgery_addionalbed" -->
<!-- 																				id="surgery_addionalbed" value="90" class="form-control" -->
<!-- 																				placeholder="Total PG Beds"> -->
<!-- 																		</div> -->
<!-- 																	</td> -->
<!-- 																	<td> -->
<!-- 																		<div class="input-style-1"> -->
<!-- 																			<input type="text" name="surgery_totalbed" -->
<!-- 																				id="surgery_totalbed" value="120" class="form-control" -->
<!-- 																				placeholder="Total Available Beds"> -->
<!-- 																		</div> -->
<!-- 																	</td> -->
<!-- 																</tr> -->
																<!-- end table row -->
															</tbody>
														</table>
													</div>
													<!-- end card -->
												</div>
												<!-- end col -->
											</div>
											<!-- end row -->
											<hr>
											<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="inst_block">
								<h6 class="mb-1">Instruction</h6>
								<ul class="inst_list">
									<li><p class="inst_text"><b>P-</b> Total Number of inpatient days (Total of inpatient
days on bed in midnight) during the last Calendar
Year</p></li>
									<li><p class="inst_text"><b>B -</b> Total number of beds in IPD</p></li>
									
								</ul>
							</div>
								</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<div class="input-style-1">
														<label>Total Number of Beds Existed in
															the last calendar year<strong class="mandatory">*</strong>
														</label> <input type="text" value="" name="grand_totalexisted"
															id="grand_totalexisted" class="form-control"
															placeholder="Total Existed Beds">
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12 col-12">
													<div class="input-style-1">
														<label>Bed Occupancy in % (P X 100 / 365 X B)<strong class="mandatory">*</strong>
														</label> <input type="text" value="" name="bed_occupancy"
															id="bed_occupancy" class="form-control"
															placeholder="Total Existed Beds">
													</div>
												</div>
											</div>
											<div class="footer_btn">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12 col-12">
														<ul class="buttons-group mainbtn">
															<li><input class="main-btn info-btn btn-hover btnsave"
																type="button" value="Save" id="bed_occupancy_btn"></li>
														</ul>
													</div>
												</div>
											</div>
									</form:form>
								</div>
								<!-- Beds Existed End -->
								<!-- Upload Document Start-->
								<div class="tab">
									<button class="tab-toggle">Upload Document</button>
								</div>
								<div class="content">
									<h4 class="heading">Upload Document</h4>
									<form:form name="Hos_Upload_Document_Form" id="Hos_Upload_Document_Form" action="" method="post" class=""
										commandName="" enctype="multipart/form-data">
										<div class="row">
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Add Diet & ECG Document</label> <input type="file" id="add_diet_ecg_doc"
														name="add_diet_ecg_doc" class="form-control" accept=".pdf">
												</div>
<!-- 													<input type="text" id="upload_docopdipd_hid" -->
<!-- 															name="upload_docopdipd_hid" value="0" class="form-control "> -->
															<input type="hidden" id="add_diet_ecg_doc_hid"
															name="add_diet_ecg_doc_hid" value="0" class="form-control ">
															<div class="note-text">
																	<span class="errorClass" id="add_diet_ecg_doc_lbl">${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="add_diet_ecg_doc_lbltik"></span>
															</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>X-ray/USG Register of last one
														calendar year<strong class="mandatory">*</strong>
													</label> <input type="file" id="xray_usg_opdipd" name="xray_usg_opdipd"
														class="form-control" accept="pdf">
												</div>
												<input type="hidden" id="xray_usg_opdipd_hid"
															name="xray_usg_opdipd_hid" value="0" class="form-control ">
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>OPD and IPD Register of last one
														calendar year<strong class="mandatory">*</strong>
													</label> <input type="file" id="register_opdipd"
														name="register_opdipd" class="form-control" accept="pdf">
												</div>
												<input type="hidden" id="register_opdipd_hid"
															name="register_opdipd_hid" value="0" class="form-control ">
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Medicine Stock register OPD and
														IPD of last one calendar year<strong class="mandatory">*</strong>
													</label> <input type="file" id="medi_stock_opdipd"
														name="medi_stock_opdipd" class="form-control" accept="pdf">
												</div>
												<input type="hidden" id="medi_stock_opdipd_hid"
															name="medi_stock_opdipd_hid" value="0" class="form-control">
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Lab Investigation register for
														OPD and IPD of last one calendar year<strong
														class="mandatory">*</strong>
													</label> <input type="file" id="last_inveopdipd"
														name="last_inveopdipd" class="form-control" accept="pdf">
												</div>
												<input type="hidden" id="last_inveopdipd_hid"
															name="last_inveopdipd_hid" value="0" class="form-control ">
											</div>

										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn success-btn btn-hover btn btnsubmit"
															type="button" value="Submit" id="upload_doc_btn"></li>
													</ul>
												</div>
											</div>
										</div>
										<input type="hidden" id="upload_doc_hid" value="0"
														name="upload_doc_hid" class="form-control">
									</form:form>
								</div>
								<!-- Upload Document End-->
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
	<script type="text/javascript"
		src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>

<c:url value="hospital_ipdopd_view" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="hos_ipd_view_id">
	<input type="hidden" name="hos_ipd_id" id="hos_ipd_view_id" value="0" />
	<input type="hidden" name="statusview" id="statusview" value="0" />
</form:form>


<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {
// 	debugger;
// 	var depdata = [];
	var depdata = '${getHosptal_opd_ipd_listDepartment}';
	
	
// 	alert(depdata.length);
	
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
	
	GrandSum();
	IpdGrandSum();
	BDOGrandSum();
// 	debugger;
// $("#upload_doc_hid").val('${getHosptal_opd_ipd_UploadDocumentsFetch[0][0]}');
// $("#support_doc_hid").val('${getHosptal_opd_ipd_UploadDocumentsFetch[0][1]}');
// $("#xray_usg_opdipd_hid").val('${getHosptal_opd_ipd_UploadDocumentsFetch[0][2]}');
// $("#register_opdipd_hid").val('${getHosptal_opd_ipd_UploadDocumentsFetch[0][3]}');
// $("#medi_stock_opdipd_hid").val('${getHosptal_opd_ipd_UploadDocumentsFetch[0][4]}');
// $("#last_inveopdipd_hid").val('${getHosptal_opd_ipd_UploadDocumentsFetch[0][5]}');


// new Date().getFullYear()
// document.getElementById("year").innerHTML = new Date().getFullYear();

hos_opd_ipd_getcurrent_yr();


	//====================opd Start=====================//
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_janopd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_febopd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_marchopd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_aprilopd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_mayopd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_juneopd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_julyopd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_augopd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_septopd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_octopd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_novopd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_decopd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	//=====================ipd=Start=================================//
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_janipd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_febipd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_marchipd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_aprilipd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_mayipd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_juneipd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_julyipd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_augipd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_septipd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_octipd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_novipd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
		document.getElementById('gm_decipd'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	</c:forEach>
	
	
	////=========================BDO Start========================//
	<c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
	document.getElementById('gm_janbdo'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
		return isNumberKeydecimal(this, event);
	};
</c:forEach>

 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
	document.getElementById('gm_febbdo'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
		return isNumberKeydecimal(this, event);
	};
</c:forEach>

 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
	document.getElementById('gm_marchbdo'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
		return isNumberKeydecimal(this, event);
	};
</c:forEach>

 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
	document.getElementById('gm_aprilbdo'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
		return isNumberKeydecimal(this, event);
	};
</c:forEach>

 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
	document.getElementById('gm_maybdo'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
		return isNumberKeydecimal(this, event);
	};
</c:forEach>

 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
	document.getElementById('gm_junebdo'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
		return isNumberKeydecimal(this, event);
	};
</c:forEach>

 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
	document.getElementById('gm_julybdo'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
		return isNumberKeydecimal(this, event);
	};
</c:forEach>

 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
	document.getElementById('gm_augbdo'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
		return isNumberKeydecimal(this, event);
	};
</c:forEach>
 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
	document.getElementById('gm_septbdo'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
		return isNumberKeydecimal(this, event);
	};
</c:forEach>

 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
	document.getElementById('gm_octbdo'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
		return isNumberKeydecimal(this, event);
	};
</c:forEach>

 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
	document.getElementById('gm_novbdo'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
		return isNumberKeydecimal(this, event);
	};
</c:forEach>

 <c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
	document.getElementById('gm_decbdo'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
		return isNumberKeydecimal(this, event);
	};
</c:forEach>
	
	//====================Bed Existed Start==============================//
<c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
document.getElementById('gm_existbed'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
	return isNumberKeydecimal(this, event);
};
</c:forEach>

<c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
document.getElementById('gm_addionalbed'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
	return isNumberKeydecimal(this, event);
};
</c:forEach>

<c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
document.getElementById('gm_bo_hidden'+"${getHosptal_opd_ipd_listDepartment[0][0]}").onkeypress = function() {
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

	$("#hos_ipd_view_id").val(id);
$("#statusview").val($("#statusA").val());
document.getElementById('viewForm').submit();
} 

document.addEventListener('DOMContentLoaded', function () {	
// 	document.querySelectorAll('.ipdGranf').forEach((items, index) => {
// 		items.addEventListener('blur', event => {
// 			IpdGrandSum();
// 		})
// 	});
	document.querySelectorAll('.ipd_Grand').forEach((items, index) => {
		items.addEventListener('blur', event => {
			IpdGrandSum();
		})
	});
	document.querySelectorAll('.bdo_Grand').forEach((items, index) => {
		items.addEventListener('blur', event => {
			BDOGrandSum();
		})
	});
// 	document.querySelectorAll('.main-btn').forEach((items, index) => {
// 		items.addEventListener('click', event => {
// 			alert("Data Save Successfully");
// 		})
// 	});
		document.querySelectorAll('.grand').forEach((items, index) => {
			items.addEventListener('blur', event => {
				GrandSum();
			})
		});
	document.getElementById('department').onclick = function() {
	 return Save_As_Draft_deprt_ipd_opd();
    };

   document.getElementById('ipd_patients_btn').onclick = function() {
	return Save_As_Draft_depart_ipd_Patients();
   };
   document.getElementById('bdo_btn').onclick = function() {
    return Save_As_Draft_depart_BDO();
   };
   document.getElementById('bed_occupancy_btn').onclick = function() {
     return Save_As_Draft_depart_Bed_Existed();
   };
   document.getElementById('upload_doc_btn').onclick = function() {
	 return Save_As_Draft_depart_Hos_Upload_Document();
   };
   
   
   document.getElementById('add_diet_ecg_doc').onchange = function() {
		return pdfFileSizeValidation(this,this.value,"add_diet_ecg_doc","200kb","add_diet_ecg_doc_lbltik","add_diet_ecg_doc_lbl",this.accept)
	};
	
});
function Save_As_Draft_deprt_ipd_opd() {
	$.ajaxSetup({
		async : false
	});
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}

	<c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
	
	var res = CheckNullorBlank('gm_janopd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"OPD Patients January Month ${j[1]}");
 		$('#gm_janopd'+${j[0]}).focus();
 		return false;
 	}
	
 	var res = CheckNullorBlank('gm_febopd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"OPD Patients February Month ${j[1]}");
 		$('#gm_febopd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_marchopd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"OPD Patients March Month ${j[1]}");
 		$('#gm_marchopd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_aprilopd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"OPD Patients April Month ${j[1]}");
 		$('#gm_aprilopd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_mayopd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"OPD Patients May Month ${j[1]}");
 		$('#gm_mayopd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_juneopd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"OPD Patients June Month ${j[1]}");
 		$('#gm_juneopd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_julyopd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"OPD Patients July Month ${j[1]}");
 		$('#gm_julyopd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_augopd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"OPD Patients August Month ${j[1]}");
 		$('#gm_augopd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_septopd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"OPD Patients September Month ${j[1]}");
 		$('#gm_septopd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_octopd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"OPD Patients October Month ${j[1]}");
 		$('#gm_octopd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_novopd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"OPD Patients November Month ${j[1]}");
 		$('#gm_novopd'+${j[0]}).focus();
 		return false;
 	}
 	
	var res = CheckNullorBlank('gm_decopd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"OPD Patients December Month ${j[1]}");
 		$('#gm_decopd'+${j[0]}).focus();
 		return false;
 	}
 	
 	
	</c:forEach>
	
      var res = CheckNullorBlank('grand_totalopd');
		if (res !== "true") {
			alert(res +"OPD Patients Grand Total");
			$('#grand_totalopd').focus();
			return false;
		}	
		var res = CheckNullorBlank('total_wardsopd');
		if (res !== "true") {
			alert(res +"OPD Patients Total Wards OPD");
			$('#total_wardsopd').focus();
			return false;
		}
	var form_data = new FormData(document.getElementById("depart_ipd_opd"));
	$.ajax({
		url : 'Hospital_ipd_opd_departAction?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j) {
		if (j > 0) {
			$("#hid_financial_council").val(j);
// 			alert("Data Save Sucessfully");
			location.reload();
		} else {
			alert(j);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}


function Save_As_Draft_depart_ipd_Patients() {
	$.ajaxSetup({
		async : false
	});
	
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	
<c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
	
	var res = CheckNullorBlank('gm_janipd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"IPD Patients January Month ${j[1]}");
 		$('#gm_janipd'+${j[0]}).focus();
 		return false;
 	}
	
 	var res = CheckNullorBlank('gm_febipd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"IPD Patients February Month ${j[1]}");
 		$('#gm_febipd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_marchipd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"IPD Patients March Month ${j[1]}");
 		$('#gm_marchipd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_aprilipd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"IPD Patients April Month ${j[1]}");
 		$('#gm_aprilipd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_mayipd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"IPD Patients May Month ${j[1]}");
 		$('#gm_mayipd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_juneipd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"IPD Patients June Month ${j[1]}");
 		$('#gm_juneipd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_julyipd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"IPD Patients July Month ${j[1]}");
 		$('#gm_julyipd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_augipd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"OPD Patients August Month ${j[1]}");
 		$('#gm_augipd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_septipd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"IPD Patients September Month ${j[1]}");
 		$('#gm_septipd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_octipd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"IPD Patients October Month ${j[1]}");
 		$('#gm_octipd'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_novopd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"OPD Patients November Month ${j[1]}");
 		$('#gm_novipd'+${j[0]}).focus();
 		return false;
 	}
 	
	var res = CheckNullorBlank('gm_decipd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"IPD Patients December Month ${j[1]}");
 		$('#gm_decipd'+${j[0]}).focus();
 		return false;
 	}
 	
 	
	</c:forEach>
	
	var res = CheckNullorBlank('grand_totalipd');
	if (res !== "true") {
		alert(res +"IPD Patients Grand Total IPD");
		$('#grand_totalipd').focus();
		return false;
	}
// 	debugger;
	var form_data = new FormData(document
			.getElementById("depart_ipd_patients_form"));
	$.ajax({
		url : 'Hospital_ipd_patients_formAction?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j) {
		if (j > 0) {
			$("#hid_financial_council").val(j);
// 			alert("Data Save Sucessfully");
			location.reload();
		} else {
			alert(j);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function Save_As_Draft_depart_BDO() {
	$.ajaxSetup({
		async : false
	});
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
<c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
	
	var res = CheckNullorBlank('gm_janbdo'+${j[0]});
 	if (res !== "true") {
 		alert(res +"Bed Days Occupied January Month ${j[1]}");
 		$('#gm_janbdo'+${j[0]}).focus();
 		return false;
 	}
	
 	var res = CheckNullorBlank('gm_febbdo'+${j[0]});
 	if (res !== "true") {
 		alert(res +"Bed Days Occupied February Month ${j[1]}");
 		$('#gm_febbdo'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_marchbdo'+${j[0]});
 	if (res !== "true") {
 		alert(res +"Bed Days Occupied March Month ${j[1]}");
 		$('#gm_marchbdo'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_aprilbdo'+${j[0]});
 	if (res !== "true") {
 		alert(res +"Bed Days Occupied April Month ${j[1]}");
 		$('#gm_aprilbdo'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_maybdo'+${j[0]});
 	if (res !== "true") {
 		alert(res +"Bed Days Occupied May Month ${j[1]}");
 		$('#gm_maybdo'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_junebdo'+${j[0]});
 	if (res !== "true") {
 		alert(res +"Bed Days Occupied June Month ${j[1]}");
 		$('#gm_junebdo'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_julybdo'+${j[0]});
 	if (res !== "true") {
 		alert(res +"Bed Days Occupied July Month ${j[1]}");
 		$('#gm_julybdo'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_augbdo'+${j[0]});
 	if (res !== "true") {
 		alert(res +"Bed Days Occupied August Month ${j[1]}");
 		$('#gm_augbdo'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_septbdo'+${j[0]});
 	if (res !== "true") {
 		alert(res +"Bed Days Occupied September Month ${j[1]}");
 		$('#gm_septbdo'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_octbdo'+${j[0]});
 	if (res !== "true") {
 		alert(res +"Bed Days Occupied October Month ${j[1]}");
 		$('#gm_octbdo'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_novopd'+${j[0]});
 	if (res !== "true") {
 		alert(res +"Bed Days Occupied November Month ${j[1]}");
 		$('#gm_novbdo'+${j[0]}).focus();
 		return false;
 	}
 	
	var res = CheckNullorBlank('gm_decbdo'+${j[0]});
 	if (res !== "true") {
 		alert(res +"Bed Days Occupied December Month ${j[1]}");
 		$('#gm_decbdo'+${j[0]}).focus();
 		return false;
 	}
 	
 	
	</c:forEach>
	
	
	var res = CheckNullorBlank('grand_totalbdo');
	if (res !== "true") {
		alert(res +"Bed Days Occupied Grand Total");
		$('#grand_totalbdo').focus();
		return false;
	}
// 	debugger;
	var form_data = new FormData(document
			.getElementById("Bed_Days_Occupied_Formid"));
	$.ajax({
		url : 'Bed_Days_Occupied_FormAction?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j) {
		if (j > 0) {
			$("#hid_financial_council").val(j);
// 			alert("Data Save Sucessfully");
			location.reload();
		} else {
			alert(j);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function Save_As_Draft_depart_Bed_Existed() {
	$.ajaxSetup({
		async : false
	});
	
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
<c:forEach var="j" items="${getHosptal_opd_ipd_listDepartment}" varStatus="num">
	
	var res = CheckNullorBlank('gm_existbed'+${j[0]});
 	if (res !== "true") {
 		alert(res +"Existing Bed For UG ${j[1]}");
 		$('#gm_existbed'+${j[0]}).focus();
 		return false;
 	}
	
 	var res = CheckNullorBlank('gm_addionalbed'+${j[0]});
 	if (res !== "true") {
 		alert(res +"Additional Bed for Existing PG ${j[1]}");
 		$('#gm_addionalbed'+${j[0]}).focus();
 		return false;
 	}
 	
 	var res = CheckNullorBlank('gm_totalbed'+${j[0]});
 	if (res !== "true") {
 		alert(res +"Total Available Beds ${j[1]}");
 		$('#gm_totalbed'+${j[0]}).focus();
 		return false;
 	}
 	
	</c:forEach>
	
	var res = CheckNullorBlank('grand_totalexisted');
	if (res !== "true") {
		alert(res +"Total Number of Beds Existed in the last calendar year");
		$('#grand_totalexisted').focus();
		return false;
	}
	var res = CheckNullorBlank('bed_occupancy');
	if (res !== "true") {
		alert(res +"Bed Occupancy in % (P X 100 / 365 X B)");
		$('#bed_occupancy').focus();
		return false;
	}
// 	debugger;
	var form_data = new FormData(document
			.getElementById("Bed_Existed_Formid"));
	$.ajax({
		url : 'Bed_Existed_FormAction?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j) {
		if (j > 0) {
			$("#hid_financial_council").val(j);
// 			alert("Data Save Sucessfully");
			location.reload();
		} else {
			alert(j);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}


function Save_As_Draft_depart_Hos_Upload_Document() {
	$.ajaxSetup({
		async : false
	});
// 	debugger;
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
// 	if($("#upload_doc_hid").val() == "0"){
		if ($("#add_diet_ecg_doc").val().trim() == "") {
			alert("Please Upload Add Diet ECG Document");
			$("input#add_diet_ecg_doc").focus();
			return false;
		}
		if ($("#xray_usg_opdipd").val().trim() == "") {
			alert("Please Upload X-ray/USG Register of last two calendar year");
			$("input#xray_usg_opdipd").focus();
			return false;
		}
		if ($("#register_opdipd").val().trim() == "") {
			alert("Please Upload OPD and IPD Register of last two calendar year");
			$("input#register_opdipd").focus();
			return false;
		}
		if ($("#medi_stock_opdipd").val().trim() == "") {
			alert("Please Upload Medicine Stock register OPD and IPD of last two calendar year");
			$("input#medi_stock_opdipd").focus();
			return false;
		}
		if ($("#last_inveopdipd").val().trim() == "") {
			alert("Please Upload Lab Investigation register for OPD and IPD of last two calendar year");
			$("input#last_inveopdipd").focus();
			return false;
		}
// 	}

// 	debugger;
	var form_data = new FormData(document.getElementById("Hos_Upload_Document_Form"));
	$.ajax({
		url : 'Hos_Upload_Document_FormAction?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j) {
		if (j > 0) {
			$("#hid_financial_council").val(j);
// 			alert("Data Save Sucessfully");
			location.reload();
		} else {
			alert(j);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function BDOGrandSum(){
	var grantotal=0;
	const collection = document.getElementsByClassName("bdo_Grand");
	for (let i = 0; i < collection.length; i++) {
	  if(collection[i].value!=''){
		  grantotal=grantotal+ parseInt(collection[i].value)
	  }
	}
	 $("#grand_totalbdo").val(grantotal);
	
	 //bed existed_total
	 var bed_occupancy_total=0;
	 bed_occupancy_total = ($("#grand_totalipd").val() * 100) / (365 *  $("#grand_totalbdo").val());
	 $("#bed_occupancy").val(bed_occupancy_total.toFixed(2));
//
	 var jantotal=0;
		const Jancollection = document.getElementsByClassName("bdojan");
		for (let i = 0; i < Jancollection.length; i++) {
		  if(Jancollection[i].value!=''){
			  jantotal=jantotal+ parseInt(Jancollection[i].value)
		  }
		}
		 $("#monthtotal_janbdo").val(jantotal);
		 
		  var febtotal=0;
		const Febcollection = document.getElementsByClassName("bdofeb");
		for (let i = 0; i < Febcollection.length; i++) {
		  if(Febcollection[i].value!=''){
			  febtotal=febtotal+ parseInt(Febcollection[i].value)
		  }
		}
		 $("#monthtotal_febbdo").val(febtotal);
		 
		 
		  var martotal=0;
		const Marcollection = document.getElementsByClassName("bdomarch");
		for (let i = 0; i < Marcollection.length; i++) {
		  if(Marcollection[i].value!=''){
			  martotal=martotal+ parseInt(Marcollection[i].value)
		  }
		}
		 $("#monthtotal_marchbdo").val(martotal);
		 
		   var apprtotal=0;
		const Apprcollection = document.getElementsByClassName("bdoappr");
		for (let i = 0; i < Apprcollection.length; i++) {
		  if(Apprcollection[i].value!=''){
			  apprtotal=apprtotal+ parseInt(Apprcollection[i].value)
		  }
		}
		 $("#monthtotal_aprilbdo").val(apprtotal);
		 
		  var maytotal=0;
		const Maycollection = document.getElementsByClassName("bdomay");
		for (let i = 0; i < Maycollection.length; i++) {
		  if(Maycollection[i].value!=''){
			  maytotal=maytotal+ parseInt(Maycollection[i].value)
		  }
		}
		 $("#monthtotal_maybdo").val(maytotal);
		 
			 var juntotal=0;
				const Juncollection = document.getElementsByClassName("bdojun");
				for (let i = 0; i < Juncollection.length; i++) {
				  if(Juncollection[i].value!=''){
					  juntotal=juntotal+ parseInt(Juncollection[i].value)
				  }
				}
				 $("#monthtotal_junebdo").val(juntotal);
			 
			 var jultotal=0;
				const Julcollection = document.getElementsByClassName("bdojuly");
				for (let i = 0; i < Julcollection.length; i++) {
				  if(Julcollection[i].value!=''){
					  jultotal=jultotal+ parseInt(Julcollection[i].value)
				  }
				}
				 $("#monthtotal_julybdo").val(jultotal);
				 
				 var augtotal=0;
					const Augcollection = document.getElementsByClassName("bdoAug");
					for (let i = 0; i < Augcollection.length; i++) {
					  if(Augcollection[i].value!=''){
						  augtotal=augtotal+ parseInt(Augcollection[i].value)
					  }
					}
					 $("#monthtotal_augbdo").val(augtotal);
					 
					 var septotal=0;
						const Sepcollection = document.getElementsByClassName("bdosep");
						for (let i = 0; i < Sepcollection.length; i++) {
						  if(Sepcollection[i].value!=''){
							  septotal=septotal+ parseInt(Sepcollection[i].value)
						  }
						}
						 $("#monthtotal_septbdo").val(septotal);
						
						 var octtotal=0;
							const Octcollection = document.getElementsByClassName("bdooct");
							for (let i = 0; i < Octcollection.length; i++) {
							  if(Octcollection[i].value!=''){
								  octtotal=octtotal+ parseInt(Octcollection[i].value)
							  }
							}
					 $("#monthtotal_octbdo").val(octtotal); 
					 var novtotal=0;
						const Novcollection = document.getElementsByClassName("bdonov");
						for (let i = 0; i < Novcollection.length; i++) {
						  if(Novcollection[i].value!=''){
							  novtotal=novtotal+ parseInt(Novcollection[i].value)
						  }
						}
						 $("#monthtotal_novbdo").val(novtotal); 
						 
						
						 var dectotal=0;
							const Deccollection = document.getElementsByClassName("bdodec");
							for (let i = 0; i < Deccollection.length; i++) {
							  if(Deccollection[i].value!=''){
								  dectotal=dectotal+ parseInt(Deccollection[i].value)
							  }
							}
							 $("#monthtotal_decbdo").val(dectotal); 
}
function IpdGrandSum(){
	var grantotal=0;
	const collection = document.getElementsByClassName("ipd_Grand");
	for (let i = 0; i < collection.length; i++) {
	  if(collection[i].value!=''){
		  grantotal=grantotal+ parseInt(collection[i].value)
	  }
	}
	 $("#grand_totalipd").val(grantotal);
	 
	 //ipd patients total start
	 var ipd_patients_total_final=0;
	 
	 ipd_patients_total_final = grantotal/300;
	 $("#ipd_patients_total_label").text(ipd_patients_total_final);
	//ipd patients total end 

	 var jantotal=0;
		const Jancollection = document.getElementsByClassName("ipdjan");
		for (let i = 0; i < Jancollection.length; i++) {
		  if(Jancollection[i].value!=''){
			  jantotal=jantotal+ parseInt(Jancollection[i].value)
		  }
		}
		 $("#monthtotal_janipd").val(jantotal);
		 
		  var febtotal=0;
		const Febcollection = document.getElementsByClassName("ipdfeb");
		for (let i = 0; i < Febcollection.length; i++) {
		  if(Febcollection[i].value!=''){
			  febtotal=febtotal+ parseInt(Febcollection[i].value)
		  }
		}
		 $("#monthtotal_febipd").val(febtotal);
		 
		 
		  var martotal=0;
		const Marcollection = document.getElementsByClassName("ipdmarch");
		for (let i = 0; i < Marcollection.length; i++) {
		  if(Marcollection[i].value!=''){
			  martotal=martotal+ parseInt(Marcollection[i].value)
		  }
		}
		 $("#monthtotal_marchipd").val(martotal);
		 
		   var apprtotal=0;
		const Apprcollection = document.getElementsByClassName("ipdappr");
		for (let i = 0; i < Apprcollection.length; i++) {
		  if(Apprcollection[i].value!=''){
			  apprtotal=apprtotal+ parseInt(Apprcollection[i].value)
		  }
		}
		 $("#monthtotal_aprilipd").val(apprtotal);
		 
		  var maytotal=0;
		const Maycollection = document.getElementsByClassName("ipdmay");
		for (let i = 0; i < Maycollection.length; i++) {
		  if(Maycollection[i].value!=''){
			  maytotal=maytotal+ parseInt(Maycollection[i].value)
		  }
		}
		 $("#monthtotal_mayipd").val(maytotal);
		 
			 var juntotal=0;
				const Juncollection = document.getElementsByClassName("ipdjun");
				for (let i = 0; i < Juncollection.length; i++) {
				  if(Juncollection[i].value!=''){
					  juntotal=juntotal+ parseInt(Juncollection[i].value)
				  }
				}
				 $("#monthtotal_juneipd").val(juntotal);
			 
			 var jultotal=0;
				const Julcollection = document.getElementsByClassName("ipdjuly");
				for (let i = 0; i < Julcollection.length; i++) {
				  if(Julcollection[i].value!=''){
					  jultotal=jultotal+ parseInt(Julcollection[i].value)
				  }
				}
				 $("#monthtotal_julyipd").val(jultotal);
				 
				 var augtotal=0;
					const Augcollection = document.getElementsByClassName("ipdAug");
					for (let i = 0; i < Augcollection.length; i++) {
					  if(Augcollection[i].value!=''){
						  augtotal=augtotal+ parseInt(Augcollection[i].value)
					  }
					}
					 $("#monthtotal_augipd").val(augtotal);
					 
					 var septotal=0;
						const Sepcollection = document.getElementsByClassName("ipdsep");
						for (let i = 0; i < Sepcollection.length; i++) {
						  if(Sepcollection[i].value!=''){
							  septotal=septotal+ parseInt(Sepcollection[i].value)
						  }
						}
						 $("#monthtotal_septipd").val(septotal);
						
						 var octtotal=0;
							const Octcollection = document.getElementsByClassName("ipdoct");
							for (let i = 0; i < Octcollection.length; i++) {
							  if(Octcollection[i].value!=''){
								  octtotal=octtotal+ parseInt(Octcollection[i].value)
							  }
							}
					 $("#monthtotal_octipd").val(octtotal); 
					 var novtotal=0;
						const Novcollection = document.getElementsByClassName("ipdnov");
						for (let i = 0; i < Novcollection.length; i++) {
						  if(Novcollection[i].value!=''){
							  novtotal=novtotal+ parseInt(Novcollection[i].value)
						  }
						}
						 $("#monthtotal_novipd").val(novtotal); 
						 
						
						 var dectotal=0;
							const Deccollection = document.getElementsByClassName("ipddec");
							for (let i = 0; i < Deccollection.length; i++) {
							  if(Deccollection[i].value!=''){
								  dectotal=dectotal+ parseInt(Deccollection[i].value)
							  }
							}
							 $("#monthtotal_decipd").val(dectotal); 
}
function GrandSum(){
	var grantotal=0;
	const collection = document.getElementsByClassName("grand");
	for (let i = 0; i < collection.length; i++) {
	  if(collection[i].value!=''){
		  grantotal=grantotal+ parseInt(collection[i].value)
	  }
	}
	 $("#grand_totalopd").val(grantotal);
	 
	 //opd patients total start
	 var opd_patients_total_final=0;
	 
	 opd_patients_total_final = grantotal/300;
	 $("#opd_patients_total_label").text(opd_patients_total_final);
	//opd patients total end 
	
	 var jantotal=0;
	const Jancollection = document.getElementsByClassName("jan");
	for (let i = 0; i < Jancollection.length; i++) {
	  if(Jancollection[i].value!=''){
		  jantotal=jantotal+ parseInt(Jancollection[i].value)
	  }
	}
	 $("#gm_janopd_mon").val(jantotal);
	 
	  var febtotal=0;
	const Febcollection = document.getElementsByClassName("feb");
	for (let i = 0; i < Febcollection.length; i++) {
	  if(Febcollection[i].value!=''){
		  febtotal=febtotal+ parseInt(Febcollection[i].value)
	  }
	}
	 $("#gm_febopd_mon").val(febtotal);
	 
	 
	  var martotal=0;
	const Marcollection = document.getElementsByClassName("mar");
	for (let i = 0; i < Marcollection.length; i++) {
	  if(Marcollection[i].value!=''){
		  martotal=martotal+ parseInt(Marcollection[i].value)
	  }
	}
	 $("#gm_marchopd_mon").val(martotal);
	 
	   var apprtotal=0;
	const Apprcollection = document.getElementsByClassName("appr");
	for (let i = 0; i < Apprcollection.length; i++) {
	  if(Apprcollection[i].value!=''){
		  apprtotal=apprtotal+ parseInt(Apprcollection[i].value)
	  }
	}
	 $("#gm_aprilopd_mon").val(apprtotal);
	 
	  var maytotal=0;
	const Maycollection = document.getElementsByClassName("may");
	for (let i = 0; i < Maycollection.length; i++) {
	  if(Maycollection[i].value!=''){
		  maytotal=maytotal+ parseInt(Maycollection[i].value)
	  }
	}
	 $("#gm_mayopd_mon").val(maytotal);
	 
		 var juntotal=0;
			const Juncollection = document.getElementsByClassName("jun");
			for (let i = 0; i < Juncollection.length; i++) {
			  if(Juncollection[i].value!=''){
				  juntotal=juntotal+ parseInt(Juncollection[i].value)
			  }
			}
			 $("#gm_juneopd_mon").val(juntotal);
		 
		 var jultotal=0;
			const Julcollection = document.getElementsByClassName("jul");
			for (let i = 0; i < Julcollection.length; i++) {
			  if(Julcollection[i].value!=''){
				  jultotal=jultotal+ parseInt(Julcollection[i].value)
			  }
			}
			 $("#gm_julyopd_mon").val(jultotal);
			 
			 var augtotal=0;
				const Augcollection = document.getElementsByClassName("aug");
				for (let i = 0; i < Augcollection.length; i++) {
				  if(Augcollection[i].value!=''){
					  augtotal=augtotal+ parseInt(Augcollection[i].value)
				  }
				}
				 $("#gm_augopd_mon").val(augtotal);
				 
				 var septotal=0;
					const Sepcollection = document.getElementsByClassName("sep");
					for (let i = 0; i < Sepcollection.length; i++) {
					  if(Sepcollection[i].value!=''){
						  septotal=septotal+ parseInt(Sepcollection[i].value)
					  }
					}
					 $("#gm_septopd_mon").val(septotal);
					
					 var octtotal=0;
						const Octcollection = document.getElementsByClassName("oct");
						for (let i = 0; i < Octcollection.length; i++) {
						  if(Octcollection[i].value!=''){
							  octtotal=octtotal+ parseInt(Octcollection[i].value)
						  }
						}
				 $("#gm_octopd_mon").val(octtotal); 
				 var novtotal=0;
					const Novcollection = document.getElementsByClassName("nov");
					for (let i = 0; i < Novcollection.length; i++) {
					  if(Novcollection[i].value!=''){
						  novtotal=novtotal+ parseInt(Novcollection[i].value)
					  }
					}
					 $("#gm_novopd_mon").val(novtotal); 
					 
					
					 var dectotal=0;
						const Deccollection = document.getElementsByClassName("dec");
						for (let i = 0; i < Deccollection.length; i++) {
						  if(Deccollection[i].value!=''){
							  dectotal=dectotal+ parseInt(Deccollection[i].value)
						  }
						}
						 $("#gm_decopd_mon").val(dectotal); 
			
					 
}


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
