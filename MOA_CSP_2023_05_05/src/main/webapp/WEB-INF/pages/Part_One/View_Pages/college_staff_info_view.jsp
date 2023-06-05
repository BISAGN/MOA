<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link rel="stylesheet" href="assets/vendor/hamburger-menu/hemburgermenu.css">
<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>View College Staff Information</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									View College Staff Information</li>
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
						<div class="hm-title"><h4 class="hm-title-text">College Staff Information view form report</h4></div>
					</div>

					<nav class="mobile-menu">
						<h5 class="hm-menu-title">Form Reports Links</h5>
						<ul>
							<li><a id="basics_information_view">College Staff Information</a></li>
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
		<form:form name="View_college_staff_info" id="View_college_staff_info" action="View_college_staff_info_Action?${_csrf.parameterName}=${_csrf.token}"
			 method="post" modelAttribute="View_college_staff_info_CMD">
				<div class="card-style mb-30">
				<input type="hidden" id="hid_college_infrastructure_remark" name="hid_college_infrastructure_remark" value="0">
						<input type="hidden" id="institute_id" name="institute_id" value="0">
						<input type="hidden" id="main_view_id" name="main_view_id" value="0">

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Teaching Staff (UG)</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" 
										title="Remarks" data-bs-toggle="modal" data-bs-target="#UG_Modal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="techstaff_ugtable">
											<thead>
												<tr>
													<th rowspan="3"><h6>Sr No</h6></th>
													<th rowspan="3"><h6>Department</h6></th>
													<th colspan="3"><h6>No of Existing Teacher</h6></th>
													<th rowspan="3"><h6>Total</h6></th>
												</tr>
												<tr>
													<th colspan="1"><h6>Professor</h6></th>
													<th colspan="1"><h6>Associate Professors /
															Readers</h6></th>
													<th colspan="1"><h6>Assistant Professors /
															Lecturers</h6></th>
												</tr>
												<tr>
													<th><h6>Full time</h6></th>
<!-- 													<th><h6>Guest Faculty</h6></th> -->
													<th><h6>Full time</h6></th>
<!-- 													<th><h6>Guest Faculty</h6></th> -->
													<th><h6>Full time</h6></th>
												</tr>
												<!-- end table row-->
											</thead>
											<tbody id="">
												<c:forEach var="j" items="${View_Teaching_Staff1}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.department_name}</p>
													</td>
													<td>
														<p>${j.prof_full_time}</p>
													</td>
													<td>
														<p>${j.associate_prof_full_time}</p>
													</td>
													<td>
														<p>${j.assistant_prof_full_time}</p>
													</td>
													<td>
														<p>${j.total}</p>
													</td>
																																		
												</tr>
												
										</c:forEach> 
												<tr>
													<td colspan="2">
														<p>Total</p>
													</td>
													<td><p>74</p></td>
													<td><p>54</p></td>
													<td><p>74</p></td>
													<td><p>96</p></td>
<!-- 													<td><p>96</p></td> -->
<!-- 													<td><p>384</p></td> -->
												</tr>
												<!-- end table row -->
											</tbody>
										</table>
									</div>
								</div>
							</section>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total Full Time</label> <span class="value-bind" id="">${View_Teaching_Staff1[0].total_full_time}
									</span>
								</div>
							</div>
<!-- 							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12"> -->
<!-- 								<div class="custom-data-value"> -->
<!-- 									<label>Total Guest faculty</label> <span class="value-bind" -->
<!-- 										id=""> </span> -->
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Teacher/Consultant of Modern Medicine</label> <span
										class="value-bind" id="">${View_Teaching_Staff1[0].consultant_modern_medi}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
								<label>Upload Stamp Acquittance Role of Teaching Staff</label>  <span
										class="value-bind" id=""><a class="main-btn dark-btn btn-hover btn-sm btnview"
										title="View" id="acquittance_role_view_btn"><i class="lni lni-eye"></i></a></span>
								</div>
								<input type="hidden" id="acquittance_role_hid" name="acquittance_role_hid" value="${View_Clg_Staff_Document[0].id}"/>
							</div>
						</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Teaching Staff (PG)</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" 
										title="Remarks" data-bs-toggle="modal" data-bs-target="#PG_Modal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="techstaff_pgtable">
											<thead>
												<tr>
													<th rowspan="3"><h6>Sr No</h6></th>
													<th rowspan="3"><h6>Department</h6></th>
													<th colspan="3"><h6>No of Existing Teacher</h6></th>
													<th rowspan="3"><h6>Total</h6></th>
												</tr>
												<tr>
													<th><h6>Professor</h6></th>
													<th><h6>Associate Professors / Readers</h6></th>
													<th><h6>Assistant Professors / Lecturers</h6></th>
												</tr>
												<!-- end table row-->
											</thead>
											<tbody id="">
												<c:forEach var="j" items="${View_Teaching_Staff_Pg2}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.department}</p>
													</td>
													<td>
														<p>${j.full_time_prof}</p>
													</td>
<!-- 													<td> -->
<%-- 														<p>${j.prof_guest_faculty}</p> --%>
<!-- 													</td> -->
													<td>
														<p>${j.asso_guest_faculty}</p>
													</td>
<!-- 													<td> -->
<%-- 														<p>${j.associate_prof_guest_faculty}</p> --%>
<!-- 													</td> -->
													<td>
														<p>${j.assis_lect}</p>
													</td>
													<td>
														<p>${j.total_teaching_staff}</p>
													</td>
																																		
												</tr>
												
										</c:forEach> 
												<tr>
													<td colspan="2">
														<p>Total</p>
													</td>
													<td colspan="4">
														<p>56</p>
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
									<h5 class="custom-title-tag">Non Teaching Staff</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" 
								title="Remarks" data-bs-toggle="modal" data-bs-target="#Non_teaching_Modal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="nontechstaff_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Post</h6></th>
													<th><h6>Available Staff</h6></th>
												</tr>
												<!-- end table row-->
											</thead>
											<tbody id="">
												<c:forEach var="j" items="${View_Non_Teaching_Staff_Info3}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.post}</p>
													</td>
													<td>
														<p>${j.available_staff}</p>
													</td>
<!-- 													<td> -->
<%-- 														<p>${j.prof_guest_faculty}</p> --%>
<!-- 													</td> -->
<!-- 													<td> -->
<%-- 														<p>${j.asso_guest_faculty}</p> --%>
<!-- 													</td> -->
<!-- 													<td> -->
<%-- 														<p>${j.associate_prof_guest_faculty}</p> --%>
<!-- 													</td> -->
<!-- 													<td> -->
<%-- 														<p>${j.assis_lect}</p> --%>
<!-- 													</td> -->
<!-- 													<td> -->
<%-- 														<p>${j.total_teaching_staff}</p> --%>
<!-- 													</td> -->
																																		
												</tr>
												
										</c:forEach> 
												<tr>
													<td colspan="2">
														<p>Total</p>
													</td>
													<td><p>${View_Non_Teaching_Staff_Info3[0].total_staff}</p></td>
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
									<h5 class="custom-title-tag">Last Academic Year Retired/Re-Employed/Newly
										Staff Details</h5>
										<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" 
								title="Remarks" data-bs-toggle="modal" data-bs-target="#last_year_teaching_Modal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="laxtyearteachstaff_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Information of Teachers</h6></th>
													<th><h6>Professors</h6></th>
													<th><h6>Associate Professors / Readers</h6></th>
													<th><h6>Assistant Professors / Lecturers</h6></th>
													<th><h6>Total</h6></th>
												</tr>
												<!-- end table row-->
											</thead>
											<tbody id="">
												<c:forEach var="j" items="${View_Last_Acadmic_Year_Staff4}"
  												varStatus="num">  
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													
													<td>
														<p>${j.teacher_info}</p>
													</td>
													<td>
														<p>${j.professor}</p>
													</td>
													<td>
														<p>${j.associate_prof}</p>
													</td>
													<td>
														<p>${j.assistant_prof}</p>
													</td>
													<td>
														<p>${j.total}</p>
													</td>
<!-- 													<td> -->
<%-- 														<p>${j.assis_lect}</p> --%>
<!-- 													</td> -->
<!-- 													<td> -->
<%-- 														<p>${j.total_teaching_staff}</p> --%>
<!-- 													</td> -->
																																		
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
									<h5 class="custom-title-tag">Salary Information</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" 
								title="Remarks" data-bs-toggle="modal" data-bs-target="#Salary_Info_Modal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="salary_infotable">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Salary Details</h6></th>
													<th><h6>Provide Appropriate Information</h6></th>
													<th><h6>Remarks/Please mention the Reason for No
														</h6></th>
														<th><h6>Attachment</h6></th>
												</tr>
												<!-- end table row-->
											</thead>
											<tbody id="">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Is the mode of payment through Bank?</p>
													</td>
													<td><p>${View_Staff_Salary_info5[0].bankpaycheck}</p></td>
													<td><p>${View_Staff_Salary_info5[0].bankpayremarks}</p></td>
													<td>
														<ul class="buttons-group">
															<li><a href="#"
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="mode_payment_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="mode_payment_hid" name="mode_payment_hid" value="${View_Staff_Salary_info5[0].id}"/>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Is GPF & EPF deducted from the salary?</p>
													</td>
													<td><p>${View_Staff_Salary_info5[0].gpfdeductcheck}</p></td>
													<td><p>${View_Staff_Salary_info5[0].gpfdeductremarks}</p></td>
													<td>
														<ul class="buttons-group">
															<li><a href="#"
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="gpf_epf_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="gpf_epf_hid" name="gpf_epf_hid" value="${View_Staff_Salary_info5[0].id}"/>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Is the teachers promotion policy as per norms of
															CCH/NCH?</p>
													</td>
													<td><p>${View_Staff_Salary_info5[0].cchnormscheck}</p></td>
													<td><p>${View_Staff_Salary_info5[0].cchnormsremarks}</p></td>
													<td>
														<ul class="buttons-group">
															<li><a href="#"
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="promotion_policy_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="promotion_policy_hid" name="promotion_policy_hid" value="${View_Staff_Salary_info5[0].id}"/>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>The existing pay scale of teaching staff is as per</p>
													</td>
													<td><p>${View_Staff_Salary_info5[0].staff_payscale}</p></td>
													<td><p>${View_Staff_Salary_info5[0].staff_payscale_remarks}</p></td>
													<td>
														<ul class="buttons-group">
															<li><a href="#"
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="pay_scale_teach_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="pay_scale_teach_hid" name="pay_scale_teach_hid" value="${View_Staff_Salary_info5[0].id}"/>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Pay Scale and Grade pay of Professor</p>
													</td>
													<td><p>${View_Staff_Salary_info5[0].payscalegradepay}</p></td>
													<td><p>${View_Staff_Salary_info5[0].payscalegradepay_remarks}</p></td>
													<td>
														<ul class="buttons-group">
															<li><a href="#"
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="pay_scale_professor_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="pay_scale_professor_hid" name="pay_scale_professor_hid" value="${View_Staff_Salary_info5[0].id}"/>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Pay Scale and Grade pay of Reader/Associate
															Professor</p>
													</td>
													<td><p>${View_Staff_Salary_info5[0].ass_pro_pay}</p></td>
													<td><p>${View_Staff_Salary_info5[0].ass_pro_pay_remarks}</p></td>
													<td>
														<ul class="buttons-group">
															<li><a href="#"
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="pay_scale_reader_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="pay_scale_reader_hid" name="pay_scale_reader_hid" value="${View_Staff_Salary_info5[0].id}"/>
													</td>
												</tr>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>

													<td>
														<p>Pay Scale and Grade pay of Lecturer/Assistant
															Professor</p>
													</td>
												<td><p>${View_Staff_Salary_info5[0].lectass_pro_pay}</p></td>
													<td><p>${View_Staff_Salary_info5[0].lectass_pro_pay_remarks}</p></td>
													<td>
														<ul class="buttons-group">
															<li><a href="#"
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="pay_scale_lecturer_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="pay_scale_lecturer_hid" name="pay_scale_lecturer_hid" value="${View_Staff_Salary_info5[0].id}"/>
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
									<h5 class="custom-title-tag">Teacher Promotion</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" 
								title="Remarks" data-bs-toggle="modal" data-bs-target="#teacher_promotion_Modal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Name of Faculty</label> <span
										class="value-bind" id=""> ${View_Clg_Techer_Prom6[0].faculty}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Designation</label> <span
										class="value-bind" id=""> ${View_Clg_Techer_Prom6[0].designation}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Upload Document</label> 
<%-- 									<span class="value-bind" id=""> ${View_Clg_Techer_Prom6[0].mess_total_cooks}</span> --%>
                                       <span class="value-bind daobtn" id=""> <a href="#"
										class="main-btn dark-btn btn-hover btn-sm btnview"
										title="View" id="upload_doc_view_btn"><i class="lni lni-eye"></i></a></span>
								</div>
								<input type="hidden" id="upload_doc_hid" name="upload_doc_hid" value="${View_Clg_Techer_Prom6[0].id}"/>
							</div>
<!-- 							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12"> -->
<!-- 								<div class="custom-data-value"> -->
<!-- 									<label>Capacity of Mess</label> <span -->
<%-- 										class="value-bind" id=""> ${View_Search_College_Infrastracturech2[0].mess_total_capacity}</span> --%>
<!-- 								</div> -->
<!-- 							</div> -->

						</div>
					</div>


					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">College Staff Documents</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" 
								title="Remarks" data-bs-toggle="modal" data-bs-target="#clg_staff_docModal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_clgstaffdoc"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="table_uploaded_clgstaffdoc">
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
														<p>Attendance Register & biometric attendance of last
															academic session of Teaching Staff</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a href="#"
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View"  id="biometric_atten_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="biometric_atten_hid" name="biometric_atten_hid" value="${View_Clg_Staff_Document[0].id}"/>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Attendance Register & biometric attendance of last
															academic session of Non-teaching Staff</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a href="#"
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View"  id="biometric_atten_non_teach_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="biometric_atten_non_teach_hid" name="biometric_atten_non_teach_hid" value="${View_Clg_Staff_Document[0].id}"/>
													</td>
												</tr>
<!-- 												<tr> -->
<!-- 													<td class="sr-no"> -->
<!-- 														<p></p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<p>Acquittance Role of Teaching Staff</p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<ul class="buttons-group"> -->
<!-- 															<li><a href="#" -->
<!-- 																class="main-btn dark-btn btn-hover btn-sm btnview" -->
<!-- 																title="View"  id="acquittance_role_view_btn"><i class="lni lni-eye"></i></a></li> -->
<%-- 														</ul><input type="hidden" id="acquittance_role_hid" name="acquittance_role_hid" value="${View_Clg_Staff_Document[0].id}"/> --%>
<!-- 													</td> -->
<!-- 												</tr> -->
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Acquittance Role of Non-teaching Staff</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a href="#"
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View"  id="acquittance_role_non_teach_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="acquittance_role_non_teach__hid" name="acquittance_role_non_teach__hid" value="${View_Clg_Staff_Document[0].id}"/>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Eligible Guide list as approved by the University</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a href="#"
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View"  id="eligible_guide_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="eligible_guide_hid" name="eligible_guide_hid" value="${View_Clg_Staff_Document[0].id}"/>
													</td>
												</tr>

											</tbody>
										</table>
									</div>
								</div>
							</section>
						</div>
					</div>
				</div>
				<!-- Modal satrt --------------1---->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="UG_Modal">
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
								<textarea id="clg_staff_teaching_staff_ug_rmk" name="clg_staff_teaching_staff_ug_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_ug_teaching_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->
<!-- Modal satrt --------------2--->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="PG_Modal">
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
								<textarea id="clg_staff_teaching_staff_pg_rmk" name="clg_staff_teaching_staff_pg_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_pg_teaching_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->
<!-- Modal satrt --------------3--->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="Non_teaching_Modal">
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
								<textarea id="clg_staff_non_tching_staff_rmk" name="clg_staff_non_tching_staff_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_non_teaching_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->
<!-- Modal satrt --------------4--->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="last_year_teaching_Modal">
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
								<textarea id="clg_staff_last_aca_yr_rmk" name="clg_staff_last_aca_yr_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_last_year_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->
<!-- Modal satrt --------------5--->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="Salary_Info_Modal">
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
								<textarea id="clg_staff_salary_info_rmk" name="clg_staff_salary_info_rmk"  placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_staff_salary_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->
<!-- Modal satrt -----------6-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="teacher_promotion_Modal">
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
								<textarea id="clg_staff_tchr_prmotion_rmk" name="clg_staff_tchr_prmotion_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_teacher_promo_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->
<!-- Modal satrt -------------7--->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="clg_staff_docModal">
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
								<textarea id=" clg_staff_upload_doc_rmk" name="clg_staff_upload_doc_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_upload_doc_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
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
<!-- hemburgermenu css end -->


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
	
//GET MAIN ID AND REMARKS
var getView_idFrom_Institute_id ='${getView_idFrom_Institute_id}';
if(getView_idFrom_Institute_id != "[]"){
	$("#hid_college_infrastructure_remark").val('${getView_idFrom_Institute_id[0].id}');
	$("#clg_staff_teaching_staff_ug_rmk").val('${getView_idFrom_Institute_id[0].clg_staff_teaching_staff_ug_rmk}');
	$("#clg_staff_teaching_staff_pg_rmk").val('${getView_idFrom_Institute_id[0].clg_staff_teaching_staff_pg_rmk}');
	$("#clg_staff_non_tching_staff_rmk").val('${getView_idFrom_Institute_id[0].clg_staff_non_tching_staff_rmk}');
	$("#clg_staff_last_aca_yr_rmk ").val('${getView_idFrom_Institute_id[0].clg_staff_last_aca_yr_rmk }');
	$("#clg_staff_salary_info_rmk").val('${getView_idFrom_Institute_id[0].clg_staff_salary_info_rmk}');
	$("#clg_staff_tchr_prmotion_rmk").val('${getView_idFrom_Institute_id[0].clg_staff_tchr_prmotion_rmk}');
	$("#clg_staff_upload_doc_rmk").val('${getView_idFrom_Institute_id[0].clg_staff_upload_doc_rmk}');
}
	
});


function ViewData_basic_info(){
	
	var institute_id  = $("#institute_id").val();
//		$("#basic_info_id").val(institute_id);
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
	
	
	
	///remark
	document.getElementById('clg_ug_teaching_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_pg_teaching_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_non_teaching_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_last_year_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_staff_salary_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_teacher_promo_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_upload_doc_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	
	}
	//document view
	
	document.getElementById('mode_payment_view_btn').onclick = function() {
		file_view($("#mode_payment_hid").val(),"clg_reg_staff_salary_information","bankpay_attachment");
	};
	
	document.getElementById('gpf_epf_view_btn').onclick = function() {
		file_view($("#gpf_epf_hid").val(),"clg_reg_staff_salary_information","gpfdeduct_attachment");
	};
	
	document.getElementById('promotion_policy_view_btn').onclick = function() {
		file_view($("#promotion_policy_hid").val(),"clg_reg_staff_salary_information","cchnorms_attachment");
	};
	
	document.getElementById('pay_scale_teach_view_btn').onclick = function() {
		file_view($("#pay_scale_teach_hid").val(),"clg_reg_staff_salary_information","staff_payscale_attachment");
	};
	
	document.getElementById('pay_scale_professor_view_btn').onclick = function() {
		file_view($("#pay_scale_professor_hid").val(),"clg_reg_staff_salary_information","payscalegradepay_attachment");
	};
	
	document.getElementById('pay_scale_reader_view_btn').onclick = function() {
		file_view($("#pay_scale_reader_hid").val(),"clg_reg_staff_salary_information","ass_pro_payattachment");
	};
	
	document.getElementById('pay_scale_lecturer_view_btn').onclick = function() {
		file_view($("#pay_scale_lecturer_hid").val(),"clg_reg_staff_salary_information","lectass_pro_payattachment");
	};
	
	document.getElementById('upload_doc_view_btn').onclick = function() {
		file_view($("#upload_doc_hid").val(),"clg_reg_college_teacher_promotion","document");
	};
	
	document.getElementById('biometric_atten_view_btn').onclick = function() {
		file_view($("#biometric_atten_hid").val(),"clg_reg_staff_upload_document_info","teaching_attendance");
	};
	
	document.getElementById('biometric_atten_non_teach_view_btn').onclick = function() {
		file_view($("#biometric_atten_non_teach_hid").val(),"clg_reg_staff_upload_document_info","non_teaching_attendance");
	};
	
	document.getElementById('acquittance_role_view_btn').onclick = function() {
		file_view($("#acquittance_role_hid").val(),"clg_reg_staff_upload_document_info","teaching_acquittance");
	};
	
	document.getElementById('acquittance_role_non_teach_view_btn').onclick = function() {
		file_view($("#acquittance_role_non_teach__hid").val(),"clg_reg_staff_upload_document_info","nonteaching_acquittance");
	};
	
	document.getElementById('eligible_guide_view_btn').onclick = function() {
		file_view($("#eligible_guide_hid").val(),"clg_reg_staff_upload_document_info","guide_list");
	};

	});



	function Save_As_Draft_Remarks() {

	$.ajaxSetup({
	    async: false
	});	
		
		var form_data = new FormData(document.getElementById("View_college_staff_info"));
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
	    	  	$('#UG_Modal').modal('hide');
	    	  	$('#PG_Modal').modal('hide');
	    	  	$('#Non_teaching_Modal').modal('hide');
	    	  	$('#last_year_teaching_Modal').modal('hide');
	    	  	$('#Salary_Info_Modal').modal('hide');
	    	  	$('#teacher_promotion_Modal').modal('hide');
	    	  	$('#clg_staff_docModal').modal('hide');
	      }
	      else{
	    	  alert(j);
	    	  	$('#UG_Modal').modal('hide');
	    	  	$('#PG_Modal').modal('hide');
	    	  	$('#Non_teaching_Modal').modal('hide');
	    	  	$('#last_year_teaching_Modal').modal('hide');
	    	  	$('#Salary_Info_Modal').modal('hide');
	    	  	$('#teacher_promotion_Modal').modal('hide');
	    	  	$('#clg_staff_docModal').modal('hide');
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

