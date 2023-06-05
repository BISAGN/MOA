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
						<h2>View Student Details</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									View Student Details</li>
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
						<div class="hm-title"><h4 class="hm-title-text">Student Details View Form Report</h4></div>
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
			<!-- humburger menu for collage regulation - part-1 end -->
			<form:form name="View_Student_Details" id="View_Student_Details" action="View_Student_DetailsAction?${_csrf.parameterName}=${_csrf.token}"
			 method="post" modelAttribute="View_Student_Details_CMD">
				<div class="card-style mb-30">
					<!-- ===========================
							Student Details Start
						=========================== -->

					<div class="field-box">
						<div class="row">
						
						<input type="hidden" id="hid_college_infrastructure_remark" name="hid_college_infrastructure_remark" value="0">
						<input type="hidden" id="institute_id" name="institute_id" value="0">
						<input type="hidden" id="main_view_id" name="main_view_id" value="0">
						
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Information of Admitted Students</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_admitted_student">
									<i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="admitedstud_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Year of Admission</h6></th>
													<th><h6 name="${year[0]}">${year[0]}</h6></th>
													<th><h6 name="${year[1]}">${year[1]}</h6></th>
													<th><h6 name="${year[2]}">${year[2]}</h6></th>
													<th><h6 name="${year[3]}">${year[3]}</h6></th>
													<th><h6 name="${year[4]}">${year[4]}</h6></th>
												</tr>
												<!-- end table row-->
											</thead>
											<tbody id="">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Total Students admitted for UG Course with Govt
															Quota</p>
													</td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[0].govt_quota_ug}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[1].govt_quota_ug}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[2].govt_quota_ug}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[3].govt_quota_ug}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[4].govt_quota_ug}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Total Students admitted for UG Course with
															Management Quota</p>
													</td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[0].mang_quota_ug}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[1].mang_quota_ug}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[2].mang_quota_ug}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[3].mang_quota_ug}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[4].mang_quota_ug}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Total Students admitted for PG Course with Govt
															Quota</p>
													</td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[0].govt_quota_pg}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[1].govt_quota_pg}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[2].govt_quota_pg}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[3].govt_quota_pg}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[4].govt_quota_pg}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Total Students admitted for PG Course with
															Management Quota</p>
													</td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[0].mang_quota_pg}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[1].mang_quota_pg}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[2].mang_quota_pg}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[3].mang_quota_pg}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[4].mang_quota_pg}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Students admitted by Court order</p>
													</td>
													<td><p id="court_order_label"></p></td>
													<td><p id="court_order_label1"></p></td>
													<td><p id="court_order_label2"></p></td>
													<td><p id="court_order_label3"></p></td>
													<td><p id="court_order_label4"></p></td>
<%-- 													<td><p>${getAllinfo_Admitted_Stu_Details_View[0].court_order}</p></td> --%>
<%-- 													<td><p>${getAllinfo_Admitted_Stu_Details_View[1].court_order}</p></td> --%>
<%-- 													<td><p>${getAllinfo_Admitted_Stu_Details_View[2].court_order}</p></td> --%>
<%-- 													<td><p>${getAllinfo_Admitted_Stu_Details_View[3].court_order}</p></td> --%>
<%-- 													<td><p>${getAllinfo_Admitted_Stu_Details_View[4].court_order}</p></td> --%>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Name of the Last Student Admitted</p>
													</td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[0].last_student}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[1].last_student}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[2].last_student}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[3].last_student}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[4].last_student}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Date of Admission of the last Student</p>
													</td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[0].last_stu_add_date}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[1].last_stu_add_date}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[2].last_stu_add_date}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[3].last_stu_add_date}</p></td>
													<td><p>${getAllinfo_Admitted_Stu_Details_View[4].last_stu_add_date}</p></td>
												</tr>
												<!-- end table row -->
											</tbody>
										</table>
									</div>
								</div>
							</section>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Whether college has intimated the students
										regarding the admission under court order</label> <span
										class="value-bind" id="intimatedcheck_label"> </span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Undertaking has been taken from students (If
										admitted though court order)</label> <span class="value-bind" id="undertakingcheck_label">
									</span>
								</div>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Admitted Student Documents</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_admitted_student_doc">
									<i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_expancedoc"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="table_uploaded_expanxedoc">
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
														<p>List of PG students allotted to individual guide in
															the last 5 academic years</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a href="#"
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="add_student_doc1_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="add_student_doc1_hid" name="add_student_doc1_hid" value="${getStu_Details_Upload_Doc_View[0].id}"/>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Name, Mobile No. and Email Id of all UG/PG admitted
															students with their NEET/AIAPGET score/admission criteria
															in the last 5 academic years</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a href="#"
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="add_student_doc2_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Undertaking of the students (About the provisional
															nature of admission)</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a href="#"
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="add_student_doc3_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>PG students attendance register/Biometric
															attendance</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a href="#"
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="add_student_doc4_view_btn"><i class="lni lni-eye"></i></a></li>
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

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Passed Out Student Information</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_passed_student">
									<i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="admitedstud_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Year of Admission</h6></th>
													<th><h6>${getStu_Details_Pass_Stu_View[0].year}</h6></th>
													<th><h6>${getStu_Details_Pass_Stu_View[1].year}</h6></th>
													<th><h6>${getStu_Details_Pass_Stu_View[2].year}</h6></th>
													<th><h6>${getStu_Details_Pass_Stu_View[3].year}</h6></th>
													<th><h6>${getStu_Details_Pass_Stu_View[4].year}</h6></th>
												</tr>
												<!-- end table row-->
											</thead>
											<tbody id="">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Total Final Year students appeared for exams for UG
															Course</p>
													</td>
													<td><p>${getStu_Details_Pass_Stu_View[0].appeared_stu_ug}</p></td>
													<td><p>${getStu_Details_Pass_Stu_View[1].appeared_stu_ug}</p></td>
													<td><p>${getStu_Details_Pass_Stu_View[2].appeared_stu_ug}</p></td>
													<td><p>${getStu_Details_Pass_Stu_View[3].appeared_stu_ug}</p></td>
													<td><p>${getStu_Details_Pass_Stu_View[4].appeared_stu_ug}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Total Final Year students passed out in exams for
															UG Course</p>
													</td>
													<td><p>${getStu_Details_Pass_Stu_View[0].passed_stu_ug}</p></td>
													<td><p>${getStu_Details_Pass_Stu_View[1].passed_stu_ug}</p></td>
													<td><p>${getStu_Details_Pass_Stu_View[2].passed_stu_ug}</p></td>
													<td><p>${getStu_Details_Pass_Stu_View[3].passed_stu_ug}</p></td>
													<td><p>${getStu_Details_Pass_Stu_View[4].passed_stu_ug}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Total Final Year students appeared for exams for PG
															Course</p>
													</td>
													<td><p>${getStu_Details_Pass_Stu_View[0].appeared_stu_pg}</p></td>
													<td><p>${getStu_Details_Pass_Stu_View[1].appeared_stu_pg}</p></td>
													<td><p>${getStu_Details_Pass_Stu_View[2].appeared_stu_pg}</p></td>
													<td><p>${getStu_Details_Pass_Stu_View[3].appeared_stu_pg}</p></td>
													<td><p>${getStu_Details_Pass_Stu_View[4].appeared_stu_pg}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Total Final Year students passed out in exams for
															PG Course</p>
													</td>
													<td><p>${getStu_Details_Pass_Stu_View[0].passed_stu_pg}</p></td>
													<td><p>${getStu_Details_Pass_Stu_View[1].passed_stu_pg}</p></td>
													<td><p>${getStu_Details_Pass_Stu_View[2].passed_stu_pg}</p></td>
													<td><p>${getStu_Details_Pass_Stu_View[3].passed_stu_pg}</p></td>
													<td><p>${getStu_Details_Pass_Stu_View[4].passed_stu_pg}</p></td>
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
									<h5 class="custom-title-tag">Internship & Housejob Details</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_internship_details">
									<i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total No. of Interns (Upload List Batch Wise)</label> <span
										class="value-bind daobtn" id=""> <a href="#"
										class="main-btn dark-btn btn-hover btn-sm btnview"
										title="View" id="no_intern_view_btn"><i class="lni lni-eye"></i></a></span>
								</div>
								<input type="hidden" id="no_intern_hid" name="no_intern_hid" value="${getStu_Details_Upload_Doc_View[0].id}"/>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Rotation Programme ?</label> <span class="value-bind"
										id="rotationcheck_label"> </span>
								</div>
							</div>
<!-- 							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12"> -->
<!-- 								<div class="custom-data-value"> -->
<!-- 									<label>Total interns in O.P.D</label> <span class="value-bind" -->
<%-- 										id=""> ${getStu_Details_Upload_Doc_View[0].internsopd }</span> --%>
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Interns Duty hour/day in O.P.D</label> <span
										class="value-bind" id=""> ${getStu_Details_Upload_Doc_View[0].internsdutyopd }</span>
								</div>
							</div>
<!-- 							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12"> -->
<!-- 								<div class="custom-data-value"> -->
<!-- 									<label>Total interns in IPD</label><span class="value-bind" -->
<%-- 										id=""> ${getStu_Details_Upload_Doc_View[0].internsipd }</span> --%>
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Interns Duty hour/day in IPD</label> <span
										class="value-bind" id="">${getStu_Details_Upload_Doc_View[0].internsdutyipd } </span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Any migration of intern from/to College.</label> <span
										class="value-bind" id="migrationcheck_label"> </span>
								</div>
							</div>
<!-- 							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12"> -->
<!-- 								<div class="custom-data-value"> -->
<!-- 									<label>Migration List</label> <span class="value-bind daobtn" -->
<!-- 										id=""> <a href="#" -->
<!-- 										class="main-btn dark-btn btn-hover btn-sm btnview" -->
<!-- 										title="View"><i class="lni lni-eye"></i></a></span> -->
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Whether intern students prescribe medicine</label> <span
										class="value-bind" id=""> ${getStu_Details_Upload_Doc_View[0].prescribe }</span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Seminar for Internee</label> <span class="value-bind"
										id=""> ${getStu_Details_Upload_Doc_View[0].seminar }</span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Whether they have Provisional Registration with
										State Homoeopathic Board/ Council ?</label> <span class="value-bind"
										id="pro_regcheck_label"> </span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Number of students who have not completed
										internship for more than two years of passing final BHMS
										examination</label> <span class="value-bind" id=""> ${getStu_Details_Upload_Doc_View[0].internship_not_completed }</span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>House Job</label> <span class="value-bind" id="house_job_label">${getStu_Details_Upload_Doc_View[0].house_job }
									</span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Number of Students undergoing House Job</label> <span
										class="value-bind" id="no_house_job_label"> ${getStu_Details_Upload_Doc_View[0].no_house_job }</span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Graded teaching work performed by P.G. Students</label>
									<span class="value-bind" id=""> ${getStu_Details_Upload_Doc_View[0].graded_teaching }</span>
								</div>
							</div>
						</div>
					</div>

					<!-- ===========================
							Student Details End
						=========================== -->
				</div>
				
<!-- Admitted Students Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_admitted_student">
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
								<textarea id="stu_dtl_info_admit_stude_rmk" name="stu_dtl_info_admit_stude_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="admitted_student_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Admitted Students Modal end -->

<!-- Admitted Students Documents Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_admitted_student_doc">
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
								<textarea id="stu_dtl_upl_doc_rmk" name="stu_dtl_upl_doc_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="Admiitted_student_doc_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Admitted Students Documents Modal end -->

<!-- Passed Students Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_passed_student">
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
								<textarea id="stu_dtl_pass_stu_rmk" name="stu_dtl_pass_stu_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="passed_student_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Passed Students Modal end -->

<!-- Internship Details Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_internship_details">
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
								<textarea id="stu_dtl_intern_housejod_rmk" name="stu_dtl_intern_housejod_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="internship_details_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Internship Details Modal end -->
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

//GET MAIN ID AND REMARKS
var getView_idFrom_Institute_id ='${getView_idFrom_Institute_id}';
if(getView_idFrom_Institute_id != "[]"){
	$("#hid_college_infrastructure_remark").val('${getView_idFrom_Institute_id[0].id}');
	$("#stu_dtl_info_admit_stude_rmk").val('${getView_idFrom_Institute_id[0].stu_dtl_info_admit_stude_rmk}');
	$("#stu_dtl_upl_doc_rmk").val('${getView_idFrom_Institute_id[0].stu_dtl_upl_doc_rmk}');
	$("#stu_dtl_pass_stu_rmk").val('${getView_idFrom_Institute_id[0].stu_dtl_pass_stu_rmk}');
	$("#stu_dtl_intern_housejod_rmk").val('${getView_idFrom_Institute_id[0].stu_dtl_intern_housejod_rmk}');
}

$("#main_view_id").val('${main_view_id}');
$.ajaxSetup({
		async : false
	});

var intimatedcheck ="";
intimatedcheck = "${getStu_Details_Upload_Doc_View[0].intimatedcheck}";
if(intimatedcheck == "2"){
	intimatedcheck="No"
}
if(intimatedcheck == "1"){
	intimatedcheck="Yes"
}
$("#intimatedcheck_label").text(intimatedcheck);

var undertakingcheck ="";
undertakingcheck = "${getStu_Details_Upload_Doc_View[0].undertakingcheck}";
if(undertakingcheck == "2"){
	undertakingcheck="No"
}
if(undertakingcheck == "1"){
	undertakingcheck="Yes"
}
$("#undertakingcheck_label").text(undertakingcheck);

var rotationcheck ="";
rotationcheck = "${getStu_Details_Upload_Doc_View[0].rotationcheck}";
if(rotationcheck == "2"){
	rotationcheck="No"
}
if(rotationcheck == "1"){
	rotationcheck="Yes"
}
$("#rotationcheck_label").text(rotationcheck);

var migrationcheck ="";
migrationcheck = "${getStu_Details_Upload_Doc_View[0].migrationcheck}";
if(migrationcheck == "2"){
	migrationcheck="No"
}
if(migrationcheck == "1"){
	migrationcheck="Yes"
}
$("#migrationcheck_label").text(migrationcheck);

var pro_regcheck ="";
pro_regcheck = "${getStu_Details_Upload_Doc_View[0].pro_regcheck}";
if(pro_regcheck == "2"){
	pro_regcheck="No"
}
if(pro_regcheck == "1"){
	pro_regcheck="Yes"
}
$("#pro_regcheck_label").text(pro_regcheck);


////shivali

var court_order ="";
court_order = "${getAllinfo_Admitted_Stu_Details_View[0].court_order}";
if(court_order == "2"){
	court_order="No"
}
if(court_order == "1"){
	court_order="Yes"
}
$("#court_order_label").text(court_order);

var court_order1 ="";
court_order1 = "${getAllinfo_Admitted_Stu_Details_View[1].court_order}";
if(court_order1 == "2"){
	court_order1="No"
}
if(court_order1 == "1"){
	court_order1="Yes"
}
$("#court_order_label1").text(court_order1);

var court_order2 ="";
court_order2 = "${getAllinfo_Admitted_Stu_Details_View[2].court_order}";
if(court_order2 == "2"){
	court_order2="No"
}
if(court_order2 == "1"){
	court_order2="Yes"
}
$("#court_order_label2").text(court_order2);

var court_order3 ="";
court_order3 = "${getAllinfo_Admitted_Stu_Details_View[3].court_order}";
if(court_order3 == "2"){
	court_order3="No"
}
if(court_order3 == "1"){
	court_order3="Yes"
}
$("#court_order_label3").text(court_order3);

var court_order4 ="";
court_order4 = "${getAllinfo_Admitted_Stu_Details_View[4].court_order}";
if(court_order4 == "2"){
	court_order4="No"
}
if(court_order4 == "1"){
	court_order4="Yes"
}
$("#court_order_label4").text(court_order4);


	
});


document.addEventListener('DOMContentLoaded', function() {

	if('${role}'=="NCH"){
		
	document.getElementById('admitted_student_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('Admiitted_student_doc_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('passed_student_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('internship_details_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
}
	
});


function Save_As_Draft_Remarks() {
	
	$.ajaxSetup({
	    async: false
	});	
	
		var form_data = new FormData(document.getElementById("View_Student_Details"));
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
      	  	$('#exampleModal_admitted_student').modal('hide');
      	  	$('#exampleModal_admitted_student_doc').modal('hide');
	      	$('#exampleModal_passed_student').modal('hide');
	      	$('#exampleModal_internship_details').modal('hide');
        }
        else{
      	  alert(j);
      	  	$('#exampleModal_admitted_student').modal('hide');
	      	$('#exampleModal_admitted_student_doc').modal('hide');
	      	$('#exampleModal_passed_student').modal('hide');
	      	$('#exampleModal_internship_details').modal('hide');
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
	
// document view

	document.getElementById('add_student_doc1_view_btn').onclick = function() {
		file_view($("#add_student_doc1_hid").val(),"clg_reg_student_dtl_upload_doc","lastfiveguide");
	};
	
	document.getElementById('add_student_doc2_view_btn').onclick = function() {
		file_view($("#add_student_doc1_hid").val(),"clg_reg_student_dtl_upload_doc","neet_score");
	};
	
	document.getElementById('add_student_doc3_view_btn').onclick = function() {
		file_view($("#add_student_doc1_hid").val(),"clg_reg_student_dtl_upload_doc","undertakingofstudent");
	};
	
	document.getElementById('add_student_doc4_view_btn').onclick = function() {
		file_view($("#add_student_doc1_hid").val(),"clg_reg_student_dtl_upload_doc","biometricattendance");
	};
	
	document.getElementById('no_intern_view_btn').onclick = function() {
		file_view($("#add_student_doc1_hid").val(),"clg_reg_student_dtl_upload_doc","total_intern");
	};


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

//view Document

function file_view(id,val,field) {
	 $("#exampleModal").modal('show');
	 var pdf1="CommonDocPartOne?kmlId2="+id+"&val444="+val+"&fildname1="+field+" ";
	 $("#pdfmodelcanvas") .attr('src',pdf1);
}

</script>
