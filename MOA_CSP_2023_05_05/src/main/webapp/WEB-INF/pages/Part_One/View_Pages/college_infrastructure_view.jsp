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
						<h2>View College Infrastructure</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									View College Infrastructure</li>
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
						<div class="hm-title"><h4 class="hm-title-text">College Infrastructure view form report</h4></div>
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
		
			<form:form name="View_College_Infrastracture" id="View_College_Infrastracture" action="View_College_InfrastractureAction?${_csrf.parameterName}=${_csrf.token}"
			 method="post" modelAttribute="View_College_InfrastractureCMD">
				<div class="card-style mb-30">
					<!-- ===========================
							College Infrastructure Start
						=========================== -->
					<div class="field-box">
						<div class="row">
						
						<input type="hidden" id="hid_college_infrastructure_remark" name="hid_college_infrastructure_remark" value="0">
						<input type="hidden" id="institute_id" name="institute_id" value="0">
						<input type="hidden" id="main_view_id" name="main_view_id" value="0">
						
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">College Council & Website Details</h5>
									<a class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_college_council">
									<i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Is College Council available?</label> <span
										class="value-bind" id="council_check_label">${View_College_InfrastractureCMD.council_check}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Council Details</label> <span class="value-bind daobtn"
										id=""> <a
										class="main-btn dark-btn btn-hover btn-sm btnview"
										title="View" id="council_clg_infra_doc_view"><i class="lni lni-eye"></i></a></span>
										<input type="hidden" id="clg_infra_doc_id" value="${View_Search_College_Infrastracturech2[0].id}"  
														name="clg_infra_doc_id" class="form-control"> 
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Last website update date</label> <span
										class="value-bind" id="site_updat_dt_label">${View_College_InfrastractureCMD.website_update_date}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Is CCTV installed as per CCH/NCH instruction ?</label> <span
										class="value-bind" id="cctv_status_label">${View_College_InfrastractureCMD.cctv_status}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Website Login Url</label> <span class="value-bind" id="">${View_College_InfrastractureCMD.login_url}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Username</label> <span class="value-bind" id="">${View_College_InfrastractureCMD.username}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Password</label> <span class="value-bind" id="">${View_College_InfrastractureCMD.password}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Is Biometric available ?</label> <span
										class="value-bind" id="biometric_status_label">${View_College_InfrastractureCMD.biometric_status}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Working hours of College</label> <span
										class="value-bind" id="">${View_College_InfrastractureCMD.college_working_hours}</span>
								</div>
							</div>

							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Is College website is as per MSR and All UG/PG
										students details updated on college website</label> <span
										class="value-bind" id="college_website_label">${View_College_InfrastractureCMD.college_website}</span>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Camera Location</h5>
									<a class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_camera_location">
									<i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_cameralocation_data"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="cameralocation_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Location</h6></th>
													<th><h6>Location Image</h6></th>
												</tr>
											</thead>
											<tbody id="">
											<c:forEach var="item" items="${getClg_camera_locationinfoReport}" varStatus="num">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p id="">Location</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a 
																class="main-btn dark-btn btn-hover btn-sm btnview1"
																title="View" id="camera_clg_infra_doc_view${num.index}"><i class="lni lni-eye"></i></a></li>
														</ul>
														<input type="hidden" id="camera_loc_clg_infra_doc_id${num.index}" value="${item.id}"  
														name="camera_loc_clg_infra_doc_id${num.index}" class="form-control"> 
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

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Progress of institution</h5>
									<a class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_progress_institution">
									<i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="cameralocation_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>

													<th><h6>Important Information of College</h6></th>
													<th><h6>Progress made by College</h6></th>
												</tr>
											</thead>
											<tbody id="">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Construction of college and hospital building</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].cons_clg_hospital}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Appointment of Teaching staff</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].app_teaching_staff}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Appointment of Non-Teaching staff</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].app_non_teaching_staff}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Appointment of Paramedical and other Hospital staff</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].app_paramedical}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Expansion of various departments of college</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].expansion_various_dept}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Expansion of Herbal Garden, Plantation of New
															Plants</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].expansion_herbal_ganden}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Hospital OPD</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].hospital_opd}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Hospital IPD</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].hospital_ipd}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Any national/international/state level seminars,
															ROTP, CME etc</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].seminars}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Publication by college and teaching staff</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].pulication_by_clg}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Research activities if any</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].research_activities}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Awards won by teaching staffs and students</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].award_details}</p></td>
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
									<h5 class="custom-title-tag">Constructed Area Of College</h5>
									<a class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_constructed_area">
									<i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="particularsarea_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>

													<th><h6>Particulars</h6></th>
													<th><h6>Available Area (in Sq. mtr.)</h6></th>
												</tr>
											</thead>
											<tbody id="">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Construction of college and hospital building</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].college_const}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Total Area of Lecturer Halls</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].lecturer_hall}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Auditorium/Seminar Hall/Exam hall</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].exam_hall}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Central Library</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].central_library}</p></td>
												</tr>
<!-- 												<tr> -->
<!-- 													<td class="sr-no"> -->
<!-- 														<p></p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<p>Common Rooms: Separate for boys and girls</p> -->
<!-- 													</td> -->
<%-- 													<td><p>${View_Search_College_Infrastracturech2[0].boys_common_rooms}</p></td> --%>
<!-- 												</tr> -->
<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Common Rooms for boys</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].boys_common_room}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Common Rooms for girls</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].girl_common_room}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Canteen</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].canteen}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Administrative section</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].administrative_section}</p></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</section>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
										<h5 class="custom-title-tag">Details of Teaching Department</h5>
										<a class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_teaching_dept">
									<i class="lni lni-pencil-alt"></i></a>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="teachingarea_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Teaching Departments</h6></th>
													<th><h6>Available Area (in Sq. mtr.)</h6></th>
												</tr>
											</thead>
											<tbody id="">
											<c:forEach var="item" items="${View_Search_College_Infrastracturech3}"
												varStatus="num">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${item.department_name}</p>
													</td>
													<td><p>${item.area_of_department}</p></td>
												</tr>
												</c:forEach>
<!-- 												<tr> -->
<!-- 													<td class="sr-no"> -->
<!-- 														<p></p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<p>Physiology including Biochemistry</p> -->
<!-- 													</td> -->
<!-- 													<td><p>No Data</p></td> -->
<!-- 												</tr> -->
<!-- 												<tr> -->
<!-- 													<td class="sr-no"> -->
<!-- 														<p></p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<p>Organon of Medicine</p> -->
<!-- 													</td> -->
<!-- 													<td><p>No Data</p></td> -->
<!-- 												</tr> -->
<!-- 												<tr> -->
<!-- 													<td class="sr-no"> -->
<!-- 														<p></p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<p>Homoeopathic Pharmacy</p> -->
<!-- 													</td> -->
<!-- 													<td><p>No Data</p></td> -->
<!-- 												</tr> -->
<!-- 												<tr> -->
<!-- 													<td class="sr-no"> -->
<!-- 														<p></p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<p>Homoeopathic Materia Medica</p> -->
<!-- 													</td> -->
<!-- 													<td><p>No Data</p></td> -->
<!-- 												</tr> -->
<!-- 												<tr> -->
<!-- 													<td class="sr-no"> -->
<!-- 														<p></p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<p>Pathology & Microbiology</p> -->
<!-- 													</td> -->
<!-- 													<td><p>No Data</p></td> -->
<!-- 												</tr> -->
<!-- 												<tr> -->
<!-- 													<td class="sr-no"> -->
<!-- 														<p></p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<p>Forensic Medicine & Toxicology</p> -->
<!-- 													</td> -->
<!-- 													<td><p>No Data</p></td> -->
<!-- 												</tr> -->
<!-- 												<tr> -->
<!-- 													<td class="sr-no"> -->
<!-- 														<p></p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<p>Practice of Medicine</p> -->
<!-- 													</td> -->
<!-- 													<td><p>No Data</p></td> -->
<!-- 												</tr> -->
<!-- 												<tr> -->
<!-- 													<td class="sr-no"> -->
<!-- 														<p></p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<p>Surgery</p> -->
<!-- 													</td> -->
<!-- 													<td><p>No Data</p></td> -->
<!-- 												</tr> -->
<!-- 												<tr> -->
<!-- 													<td class="sr-no"> -->
<!-- 														<p></p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<p>Obstetrics & Gynecology</p> -->
<!-- 													</td> -->
<!-- 													<td><p>No Data</p></td> -->
<!-- 												</tr> -->
<!-- 												<tr> -->
<!-- 													<td class="sr-no"> -->
<!-- 														<p></p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<p>Community Medicine</p> -->
<!-- 													</td> -->
<!-- 													<td><p>No Data</p></td> -->
<!-- 												</tr> -->
<!-- 												<tr> -->
<!-- 													<td class="sr-no"> -->
<!-- 														<p></p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<p>Repertory</p> -->
<!-- 													</td> -->
<!-- 													<td><p>No Data</p></td> -->
<!-- 												</tr> -->
<!-- 												<tr> -->
<!-- 													<td class="sr-no"> -->
<!-- 														<p></p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<p>Pediatrics</p> -->
<!-- 													</td> -->
<!-- 													<td><p>No Data</p></td> -->
<!-- 												</tr> -->
<!-- 												<tr> -->
<!-- 													<td class="sr-no"> -->
<!-- 														<p></p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<p>Psychiatry</p> -->
<!-- 													</td> -->
<!-- 													<td><p>No Data</p></td> -->
<!-- 												</tr> -->
												<tr>
													<td colspan="2">
														<p>
															<b>Total Teaching Departments Area</b>
														</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech3[0].total_area_of_department}</p></td>
												</tr>
												<tr>
													<td colspan="2">
														<p>
															<b>Total Constructed Area of College (in sq. mt.)</b>
														</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech3[0].total_area_of_college}</p></td>
												</tr>
												<tr>
													<td colspan="2">
														<p>
															<b>Total Lecture Halls</b>
														</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech3[0].total_lecture_hall}</p></td>
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
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
										<h5 class="custom-title-tag">Books</h5>
										<a class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_library_books">
									<i class="lni lni-pencil-alt"></i></a>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="centrallibrarybook_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Books Type</h6></th>
													<th><h6>Total Books</h6></th>
												</tr>
											</thead>
											<tbody id="">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Text Book</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].text_book}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Reference Book</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].regerence_book}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>CCH/NCH, CCRH & Government Publications</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].govn_publication}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>New Addition</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].new_addition}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Book Bank</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].book_bank}</p></td>
												</tr>
												<tr>
													<td colspan="2">
														<p>
															<b>Total No. of Books</b>
														</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].total_book_no}</p></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</section>
						
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
										<h5 class="custom-title-tag">Journal</h5>
										<a class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_library_journal">
									<i class="lni lni-pencil-alt"></i></a>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="centrallibraryjournal_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Journal Type</h6></th>
													<th><h6>Total Journal</h6></th>
												</tr>
											</thead>
											<tbody id="">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Subscribed</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].subscribed_no}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Complementary</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].complementary_no}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>News paper</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].news_paper_no}</p></td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Back Issue</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].back_issue_no}</p></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</section>
							
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Central Library</h5>
									<a class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_central_library">
									<i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Working Hours</label> <span class="value-bind" id=""> ${View_Search_College_Infrastracturech2[0].library_working_hours}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Reading Room for Teaching Faculty capacity</label> <span
										class="value-bind" id=""> ${View_Search_College_Infrastracturech2[0].student_reading_room_capacity}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Reading Room for Students with capacity</label> ${View_Search_College_Infrastracturech2[0].faculty_reading_room_capacity}<span
										class="value-bind" id=""></span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Reading Room Purpose</label> <span class="value-bind"
										id=""> ${View_Search_College_Infrastracturech2[0].rading_room_purpose}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Home Lending</label> <span class="value-bind" id=""> ${View_Search_College_Infrastracturech2[0].home_lending}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Photocopying Facility</label> <span class="value-bind"
										id=""> ${View_Search_College_Infrastracturech2[0].photocopying_facility}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Computers with Internet Facility</label> <span
										class="value-bind" id="computers_facility_label"> </span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Whether Cataloguing of books done?</label> <span
										class="value-bind" id="cataloguing_books_label"> </span>
								</div>
							</div>
<!-- 							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12"> -->
<!-- 								<div class="custom-data-value"> -->
<!-- 									<label>System of Cataloguing</label> <span class="value-bind" -->
<%-- 										id="cataloguing_system_label"> ${View_Search_College_Infrastracturech2[0].cataloguing_system}</span> --%>
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Name of Library Assistant</label> <span class="value-bind" 
									id=""> ${View_Search_College_Infrastracturech2[0].librarian_name}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Qualification of Librarian</label> <span
										class="value-bind" id=""> ${View_Search_College_Infrastracturech2[0].librarian_qualification}</span>
								</div>
							</div>
<!-- 							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12"> -->
<!-- 								<div class="custom-data-value"> -->
<!-- 									<label>Other information</label> <span class="value-bind" -->
<%-- 									 id=""> ${View_Search_College_Infrastracturech2[0].other_information}</span> --%>
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Is eLibrary Facility Available?</label> <span
										class="value-bind" id="elibrary_facility_avail_label"> </span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12" id="total_no_comp_div">
								<div class="custom-data-value">
									<label>Total No. of Computers</label> <span class="value-bind"
									 id=""> ${View_Search_College_Infrastracturech2[0].total_computers}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12" id="total_no_subscription_div">
								<div class="custom-data-value">
									<label>Total No. of Subscriptions</label> <span class="value-bind"
									 id=""> ${View_Search_College_Infrastracturech2[0].total_subscription}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12" id="total_no_ebboks_div">
								<div class="custom-data-value">
									<label>Total No. of eBooks</label> <span class="value-bind"
									 id=""> ${View_Search_College_Infrastracturech2[0].total_ebooks}</span>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
										<h5 class="custom-title-tag">Library Assistants</h5>
										<a class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_library_assistant">
										<i class="lni lni-pencil-alt"></i></a>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="centrallibraryjournal_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Library Assistants Name</h6></th>
													<th><h6>Qualification</h6></th>
												</tr>
											</thead>
											<tbody id="doc_Tbbody">
											<c:forEach var="j" items="${View_Search_College_Infrastracturech4}"
												varStatus="num">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${j.library_assistants_name}</p>
													</td>
													<td><p>${j.assistants_qualification}</p></td>
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
									<h5 class="custom-title-tag">Details Of Hostel</h5>
									<a class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_hostel_details">
									<i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="hosteldetail_table">
											<thead>
												<tr>
													<th><h6>Hostel</h6></th>
													<th><h6>Area (sq.mtr.)</h6></th>
													<th><h6>Own / Rented</h6></th>
													<th><h6>Total Rooms</h6></th>
													<th><h6>Capacity Per Room</h6></th>
													<th><h6>Total Occupied Room</h6></th>
													<th><h6>Mess Facility</h6></th>
													<th><h6>Warden Facility</h6></th>
												</tr>
											</thead>
											<tbody id="">
												<tr>
													<td>
														<p>Boys</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].boys_area}</p></td>
													<td><p id="boys_own_rented_td">${View_Search_College_Infrastracturech2[0].boys_own_rented}</p></td>
													<td><p>${View_Search_College_Infrastracturech2[0].boys_room_no}</p></td>
													<td><p>${View_Search_College_Infrastracturech2[0].boys_capacity}</p></td>
													<td><p>${View_Search_College_Infrastracturech2[0].boys_occupied_room}</p></td>
													<td><p id="boys_mess_facility_td">${View_Search_College_Infrastracturech2[0].boys_mess_facility}</p></td>
													<td><p id="boys_warden_facility_td">${View_Search_College_Infrastracturech2[0].boys_warden_facility}</p></td>
												</tr>
												<tr>
													<td>
														<p>Girls</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].girls_area}</p></td>
													<td><p id="girls_own_rented_td">${View_Search_College_Infrastracturech2[0].girls_own_rented}</p></td>
													<td><p>${View_Search_College_Infrastracturech2[0].girls_room_no}</p></td>
													<td><p>${View_Search_College_Infrastracturech2[0].girls_capacity}</p></td>
													<td><p>${View_Search_College_Infrastracturech2[0].girls_occupied_room}</p></td>
													<td><p id="girls_mess_facility_td">${View_Search_College_Infrastracturech2[0].girls_mess_facility}</p></td>
													<td><p id="girls_warden_facility_td">${View_Search_College_Infrastracturech2[0].girls_warden_facility}</p></td>
												</tr>
												<tr>
													<td>
														<p>Guest</p>
													</td>
													<td><p>${View_Search_College_Infrastracturech2[0].guest_area}</p></td>
													<td><p id="guest_own_rented_td">${View_Search_College_Infrastracturech2[0].guest_own_rented}</p></td>
													<td><p>${View_Search_College_Infrastracturech2[0].guest_room_no}</p></td>
													<td><p>${View_Search_College_Infrastracturech2[0].guest_capacity}</p></td>
														<td><p>${View_Search_College_Infrastracturech2[0].guest_occupied_room}</p></td>
													<td><p id="guest_mess_facility_td">${View_Search_College_Infrastracturech2[0].guest_mess_facility}</p></td>
													<td><p id="guest_warden_facility_td">${View_Search_College_Infrastracturech2[0].guest_warden_facility}</p></td>
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
									<h5 class="custom-title-tag">Details Of Mess</h5>
									<a class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_mess_details">
									<i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Types of Kitchen</label> <span
										class="value-bind" id="type_of_mess"> ${View_Search_College_Infrastracturech2[0].type_of_mess}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total area of Mess (in sq.mt)</label> <span
										class="value-bind" id=""> ${View_Search_College_Infrastracturech2[0].mess_total_area}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total No. of Cooks</label> <span
										class="value-bind" id=""> ${View_Search_College_Infrastracturech2[0].mess_total_cooks}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Capacity of Mess</label> <span
										class="value-bind" id=""> ${View_Search_College_Infrastracturech2[0].mess_total_capacity}</span>
								</div>
							</div>

						</div>
					</div>


					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Herbal Garden</h5>
									<a class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_herbal_garden">
									<i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total area of the Herbal Garden (in sq.mt)</label> <span
										class="value-bind" id=""> ${View_Search_College_Infrastracturech2[0].total_area}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total cultivated species</label> <span
										class="value-bind" id=""> ${View_Search_College_Infrastracturech2[0].total_cultivated_species}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>List of plant species</label> <span
										class="value-bind daobtn" id=""> <a href="#"
										class="main-btn dark-btn btn-hover btn-sm btnview"
										title="View" id="plant_clg_infra_doc_view"><i class="lni lni-eye"></i></a></span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Photograph and list of herbal garden & medicinal plants with names</label> <span class="value-bind daobtn" id=""> <a
										 class="main-btn dark-btn btn-hover btn-sm btnview"
										title="View" id="herbal_clg_infra_doc_view"><i class="lni lni-eye"></i></a></span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Is irrigation facility available?</label> <span
										class="value-bind" id="irrigation_facility_label"> </span>
								</div>
							</div>

						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">College Infrastructure Additional Details</h5>
									<a class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_additional_details">
									<i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Is transport Facility available?</label> <span
										class="value-bind" id="transport_facility_label"> </span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Is sports & games Facility available?</label> <span
										class="value-bind" id="sports_facility_label"> </span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Is there any due for previous visitation/
										inspection pending for payment from the college?</label> <span
										class="value-bind" id="inspection_pending_label"> </span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Is there any penalty amount due from the college
										for payment?</label> <span class="value-bind" id="penalty_amount_label"> </span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Has the college implemented Swasthya Rakshan
										programme by adopting 5 villages/colonies?</label> <span
										class="value-bind" id="swasthya_rakshan_programme_label"> </span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Has the compliance report of the shortcomings
										been submitted to Council?</label> <span class="value-bind" id="compliance_report_label">
									</span>
								</div>
							</div>
								
								<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Is Ambulance Facility available?</label> <span class="value-bind" id="ambulance_report_label">
									</span>
								</div>
							</div>
							
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Is Compliance Report available?</label> <span class="value-bind" id="compliance_report_label1">
									</span>
								</div>
							</div>
							
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12" id="upload_comliance_report_div">
								<div class="custom-data-value">
									<label>Upload Compliance Report</label> <span class="value-bind daobtn" id=""> <a
										 class="main-btn dark-btn btn-hover btn-sm btnview"
										title="View" id="compliance_clg_infra_doc_view"><i class="lni lni-eye"></i></a></span>
								</div>
							</div>
							
						</div>
					</div>
					<!-- ===========================
							College Infrastructure End
						=========================== -->

				</div>
				
				
<!-- College Council Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_college_council">
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
								<textarea id="clg_infra_clg_coun_web_dtl_rmk" name="clg_infra_clg_coun_web_dtl_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="college_council_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- College Council Modal end -->
<!-- Camera Location Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_camera_location">
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
								<textarea id="clg_infra_cam_loc_rmk" name="clg_infra_cam_loc_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="camera_location_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Camera Location Modal end -->

<!-- Prograss Institution Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_progress_institution">
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
								<textarea id="clg_infra_progress_inst_rmk" name="clg_infra_progress_inst_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="progress_institution_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Prograss Institution Modal end -->

<!-- Constructed Area Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_constructed_area">
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
								<textarea id="clg_infra_dtl_various_clg_teach_dept_rmk" name="clg_infra_dtl_various_clg_teach_dept_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="constructed_area_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Constructed Area Modal end -->

<!-- Details of Teaching Deppartment Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_teaching_dept">
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
								<textarea id="clg_infra_dtl_teach_dept_rmk" name="clg_infra_dtl_teach_dept_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="teaching_department_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Details of Teaching Deppartment Modal end -->

<!-- Library Books Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_library_books">
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
								<textarea id="clg_infra_books_rmk" name="clg_infra_books_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="library_book_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Library Books Modal end -->

<!-- Library Journal Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_library_journal">
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
								<textarea id="clg_infra_no_of_journal_rmk" name="clg_infra_no_of_journal_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="library_journal_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Library Journal Modal end -->

<!-- Central Library Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_central_library">
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
								<textarea id="clg_infra_lib_dtl_rmk" name="clg_infra_lib_dtl_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="central_library_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Central Library Modal end -->

<!-- Library Assistant Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_library_assistant">
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
								<textarea id="clg_infra_lib_ass_rmk" name="clg_infra_lib_ass_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="library_assistant_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Library Assistant Modal end -->

<!-- Hostel Details Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_hostel_details">
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
								<textarea id="clg_infra_details_hostel_rmk" name="clg_infra_details_hostel_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="hostel_details_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Hostel Details Modal end -->

<!-- Mess Details Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_mess_details">
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
								<textarea id="clg_infra_details_mess_rmk" name="clg_infra_details_mess_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="mess_details_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Mess Details Modal end -->

<!-- Herbal Garden Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_herbal_garden">
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
								<textarea id="clg_infra_herbal_garden_rmk" name="clg_infra_herbal_garden_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="herbal_garden_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Herbal Garden Modal end -->

<!-- Additional Details Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal_additional_details">
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
								<textarea id="clg_infra_additional_info_rmk" name="clg_infra_additional_info_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="additional_information_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Additional Details Modal end -->
				
				
			</form:form>
		</div>
	</div>
</section>

<!-- Canvas Modal start -->

<div class="modal fade custom-modal custom-modal-pdf" tabindex="-1"
	aria-labelledby="myLargeModalLabel" id="clg_infra_doc"
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
<!-- 	<input type="hidden" name="basic_info_id" id="basic_info_id" value="0" /> -->
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
	if('${role}'!="NCH"){
		$(".btnedit").css("visibility", "hidden");
		$(".viewmenu").addClass("d-none");
		

	}
// 	alert("${View_Search_College_Infrastracturech4[0]}")
// 	alert('${getInstname[0].institute_name}')
// 				var website_update_date=""
// 				if (website_update_date !="" && website_update_date !=null
// 						&& website_update_date !="null") {
// 					website_update_date= ${View_College_InfrastractureCMD.website_update_date};
// 				}else {
// 					website_update_date="-";
// 				}

	$("#institute_id").val('${inst_id}');

	//GET MAIN ID AND REMARKS
	var getView_idFrom_Institute_id ='${getView_idFrom_Institute_id}';
	if(getView_idFrom_Institute_id != "[]"){
		$("#hid_college_infrastructure_remark").val('${getView_idFrom_Institute_id[0].id}');
		$("#clg_infra_clg_coun_web_dtl_rmk").val('${getView_idFrom_Institute_id[0].clg_infra_clg_coun_web_dtl_rmk}');
		$("#clg_infra_cam_loc_rmk").val('${getView_idFrom_Institute_id[0].clg_infra_cam_loc_rmk}');
		$("#clg_infra_progress_inst_rmk").val('${getView_idFrom_Institute_id[0].clg_infra_progress_inst_rmk}');
		$("#clg_infra_details_hostel_rmk").val('${getView_idFrom_Institute_id[0].clg_infra_details_hostel_rmk}');
		$("#clg_infra_details_mess_rmk").val('${getView_idFrom_Institute_id[0].clg_infra_details_mess_rmk}');
		$("#clg_infra_herbal_garden_rmk").val('${getView_idFrom_Institute_id[0].clg_infra_herbal_garden_rmk}');
		$("#clg_infra_additional_info_rmk").val('${getView_idFrom_Institute_id[0].clg_infra_additional_info_rmk}');
		$("#clg_infra_dtl_various_clg_teach_dept_rmk").val('${getView_idFrom_Institute_id[0].clg_infra_dtl_various_clg_teach_dept_rmk}');
		$("#clg_infra_dtl_teach_dept_rmk").val('${getView_idFrom_Institute_id[0].clg_infra_dtl_teach_dept_rmk}');
		$("#clg_infra_no_of_journal_rmk").val('${getView_idFrom_Institute_id[0].clg_infra_no_of_journal_rmk}');
		$("#clg_infra_books_rmk").val('${getView_idFrom_Institute_id[0].clg_infra_books_rmk}');
		$("#clg_infra_lib_dtl_rmk").val('${getView_idFrom_Institute_id[0].clg_infra_lib_dtl_rmk}');
		$("#clg_infra_lib_ass_rmk").val('${getView_idFrom_Institute_id[0].clg_infra_lib_ass_rmk}');
	}

				
	var council_check ="";
	council_check = "${View_College_InfrastractureCMD.council_check}";
	if(council_check == "2"){
		council_check="No"
	}
	if(council_check == "1"){
		council_check="Yes"
	}
	
	var biometric_status ="";
	biometric_status = "${View_College_InfrastractureCMD.biometric_status}";
	if(biometric_status == "2"){
		biometric_status="No"
	}
	if(biometric_status == "1"){
		biometric_status="Yes"
	}
	
	var cctv_status ="";
	cctv_status = "${View_College_InfrastractureCMD.cctv_status}";
	if(cctv_status == "2"){
		cctv_status="No"
	}
	if(cctv_status == "1"){
		cctv_status="Yes"
	}
	
	var college_website ="";
	college_website = "${View_College_InfrastractureCMD.college_website}";
	if(college_website == "2"){
		college_website="No"
	}
	if(college_website == "1"){
		college_website="Yes"
	}
	
	var computers_facility ="";
	computers_facility = "${View_Search_College_Infrastracturech2[0].computers_facility}";
	if(computers_facility == "2"){
		computers_facility="No"
	}
	if(computers_facility == "1"){
		computers_facility="Yes"
	}
	
	var cataloguing_books ="";
	cataloguing_books = "${View_Search_College_Infrastracturech2[0].cataloguing_books}";
	if(cataloguing_books == "2"){
		cataloguing_books="No"
	}
	if(cataloguing_books == "1"){
		cataloguing_books="Yes"
	}
	
	var irrigation_facility ="";
	irrigation_facility = "${View_Search_College_Infrastracturech2[0].irrigation_facility}";
	if(irrigation_facility == "2"){
		irrigation_facility="No"
	}
	if(irrigation_facility == "1"){
		irrigation_facility="Yes"
	}
	
	var transport_facility ="";
	transport_facility = "${View_Search_College_Infrastracturech2[0].trasport_facility}";
	if(transport_facility == "2"){
		transport_facility="No"
	}
	if(transport_facility == "1"){
		transport_facility="Yes"
	}
	
	var sports_facility ="";
	sports_facility = "${View_Search_College_Infrastracturech2[0].sports_facility}";
	if(sports_facility == "2"){
		sports_facility="No"
	}
	if(sports_facility == "1"){
		sports_facility="Yes"
	}
	
	var inspection_pending ="";
	inspection_pending = "${View_Search_College_Infrastracturech2[0].inspection_pending}";
	if(inspection_pending == "2"){
		inspection_pending="No"
	}
	if(inspection_pending == "1"){
		inspection_pending="Yes"
	}
	
	var penalty_amount ="";
	penalty_amount = "${View_Search_College_Infrastracturech2[0].penalty_amount}";
	if(penalty_amount == "2"){
		penalty_amount="No"
	}
	if(penalty_amount == "1"){
		penalty_amount="Yes"
	}
	
	var swasthya_rakshan_programme ="";
	swasthya_rakshan_programme = "${View_Search_College_Infrastracturech2[0].swasthya_rakshan_programme}";
	if(swasthya_rakshan_programme == "2"){
		swasthya_rakshan_programme="No"
	}
	if(swasthya_rakshan_programme == "1"){
		swasthya_rakshan_programme="Yes"
	}
	
	var compliance_report ="";
	compliance_report = "${View_Search_College_Infrastracturech2[0].compliance_report}";
	if(compliance_report == "2"){
		compliance_report="No"
	}
	if(compliance_report == "1"){
		compliance_report="Yes"
	}
	
	var boys_own_rented ="";
	boys_own_rented = "${View_Search_College_Infrastracturech2[0].boys_own_rented}";
	if(boys_own_rented == "2"){
		boys_own_rented="No"
	}
	if(boys_own_rented == "1"){
		boys_own_rented="Yes"
	}
	
	var girls_own_rented ="";
	girls_own_rented = "${View_Search_College_Infrastracturech2[0].girls_own_rented}";
	if(girls_own_rented == "2"){
		girls_own_rented="No"
	}
	if(girls_own_rented == "1"){
		girls_own_rented="Yes"
	}
	
	var boys_mess_facility ="";
	boys_mess_facility = "${View_Search_College_Infrastracturech2[0].boys_mess_facility}";
	if(boys_mess_facility == "2"){
		boys_mess_facility="No"
	}
	if(boys_mess_facility == "1"){
		boys_mess_facility="Yes"
	}
	
	var girls_mess_facility ="";
	girls_mess_facility = "${View_Search_College_Infrastracturech2[0].girls_mess_facility}";
	if(girls_mess_facility == "2"){
		girls_mess_facility="No"
	}
	if(girls_mess_facility == "1"){
		girls_mess_facility="Yes"
	}
	
	var boys_warden_facility ="";
	boys_warden_facility = "${View_Search_College_Infrastracturech2[0].boys_warden_facility}";
	if(boys_warden_facility == "2"){
		boys_warden_facility="No"
	}
	if(boys_warden_facility == "1"){
		boys_warden_facility="Yes"
	}
	
	var girls_warden_facility ="";
	girls_warden_facility = "${View_Search_College_Infrastracturech2[0].girls_warden_facility}";
	if(girls_warden_facility == "2"){
		girls_warden_facility="No"
	}
	if(girls_warden_facility == "1"){
		girls_warden_facility="Yes"
	}
	
	$("#council_check_label").text(council_check);
	$("#cctv_status_label").text(cctv_status);
	$("#biometric_status_label").text(biometric_status);
	$("#college_website_label").text(college_website);
	$("#computers_facility_label").text(computers_facility);
	$("#cataloguing_books_label").text(cataloguing_books);
	$("#irrigation_facility_label").text(irrigation_facility);
	$("#transport_facility_label").text(transport_facility);
	$("#sports_facility_label").text(sports_facility);
	$("#inspection_pending_label").text(inspection_pending);
	$("#penalty_amount_label").text(penalty_amount);
	$("#swasthya_rakshan_programme_label").text(swasthya_rakshan_programme);
	$("#compliance_report_label").text(compliance_report);
	$("#boys_own_rented_td").text(boys_own_rented);
	$("#boys_mess_facility_td").text(boys_mess_facility);
	$("#boys_warden_facility_td").text(boys_warden_facility);
	$("#girls_own_rented_td").text(girls_own_rented);
	$("#girls_mess_facility_td").text(girls_mess_facility);
	$("#girls_warden_facility_td").text(girls_warden_facility);
	

	
$.ajaxSetup({
async : false
});


var type_of_mess ="";
type_of_mess = "${View_Search_College_Infrastracturech2[0].type_of_mess}";
if(type_of_mess == "2"){
type_of_mess="No"
}
if(type_of_mess == "1"){
type_of_mess="Yes"
}
$("#type_of_mess").text(type_of_mess);


var guest_own_rented_td ="";
guest_own_rented_td = "${View_Search_College_Infrastracturech2[0].guest_own_rented}";
if(guest_own_rented_td == "2"){
guest_own_rented_td="No"
}
if(guest_own_rented_td == "1"){
guest_own_rented_td="Yes"
}
$("#guest_own_rented_td").text(guest_own_rented_td);

var guest_mess_facility_td ="";
guest_mess_facility_td = "${View_Search_College_Infrastracturech2[0].guest_mess_facility}";
if(guest_mess_facility_td == "2"){
guest_mess_facility_td="No"
}
if(guest_mess_facility_td == "1"){
guest_mess_facility_td="Yes"
}
$("#guest_mess_facility_td").text(guest_mess_facility_td);

var guest_warden_facility_td ="";
guest_warden_facility_td = "${View_Search_College_Infrastracturech2[0].guest_warden_facility}";
if(guest_warden_facility_td == "2"){
guest_warden_facility_td="No"
}
if(guest_warden_facility_td == "1"){
guest_warden_facility_td="Yes"
}
$("#guest_warden_facility_td").text(guest_warden_facility_td);


var ambulance_report_label ="";
ambulance_report_label = "${View_Search_College_Infrastracturech2[0].ambulance_facility}";
if(ambulance_report_label == "2"){
ambulance_report_label="No"
}
if(ambulance_report_label == "1"){
ambulance_report_label="Yes"
}
$("#ambulance_report_label").text(ambulance_report_label);	

var compliance_report_label1 ="";
compliance_report_label1 = "${View_Search_College_Infrastracturech2[0].compliance_report_check}";
if(compliance_report_label1 == "2"){
compliance_report_label1="No"
$("#upload_comliance_report_div").hide();
}
if(compliance_report_label1 == "1"){
compliance_report_label1="Yes"
$("#upload_comliance_report_div").show();
}
$("#compliance_report_label1").text(compliance_report_label1);	

var elibrary_facility_avail_label ="";
elibrary_facility_avail_label = "${View_Search_College_Infrastracturech2[0].elibrary_check}";
if(elibrary_facility_avail_label == "2"){
elibrary_facility_avail_label="No"
$("#total_no_comp_div").hide();
$("#total_no_subscription_div").hide();
$("#total_no_ebboks_div").hide();
}
if(elibrary_facility_avail_label == "1"){
elibrary_facility_avail_label="Yes"
$("#total_no_comp_div").show();
$("#total_no_subscription_div").show();
$("#total_no_ebboks_div").show();
}
$("#elibrary_facility_avail_label").text(elibrary_facility_avail_label);	

		
		
	});




function Save_As_Draft_Remarks() {

$.ajaxSetup({
    async: false
});	
	
	var form_data = new FormData(document.getElementById("View_College_Infrastracture"));
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
    	  	$('#exampleModal_college_council').modal('hide');
    	  	$('#exampleModal_camera_location').modal('hide');
    	  	$('#exampleModal_progress_institution').modal('hide');
    	  	$('#exampleModal_constructed_area').modal('hide');
    	  	$('#exampleModal_teaching_dept').modal('hide');
    	  	$('#exampleModal_library_books').modal('hide');
    	  	$('#exampleModal_library_journal').modal('hide');
    	  	$('#exampleModal_central_library').modal('hide');
    	  	$('#exampleModal_library_assistant').modal('hide');
    	  	$('#exampleModal_hostel_details').modal('hide');
    	  	$('#exampleModal_mess_details').modal('hide');
    	  	$('#exampleModal_herbal_garden').modal('hide');
    	  	$('#exampleModal_additional_details').modal('hide');
      }
      else{
    	  alert(j);
    	  	$('#exampleModal_college_council').modal('hide');
	  	  	$('#exampleModal_camera_location').modal('hide');
	  	  	$('#exampleModal_progress_institution').modal('hide');
	  	  	$('#exampleModal_constructed_area').modal('hide');
	  	  	$('#exampleModal_teaching_dept').modal('hide');
	  	  	$('#exampleModal_library_books').modal('hide');
	  	  	$('#exampleModal_library_journal').modal('hide');
	  	  	$('#exampleModal_central_library').modal('hide');
	  	  	$('#exampleModal_library_assistant').modal('hide');
	  	  	$('#exampleModal_hostel_details').modal('hide');
	  	  	$('#exampleModal_mess_details').modal('hide');
	  	  	$('#exampleModal_herbal_garden').modal('hide');
	  	  	$('#exampleModal_additional_details').modal('hide');
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
	
	///remark
	document.getElementById('college_council_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('camera_location_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('progress_institution_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('constructed_area_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('teaching_department_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('library_book_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('library_journal_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('central_library_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('library_assistant_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('hostel_details_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('mess_details_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('herbal_garden_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('additional_information_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	
	}
	
// 	document View

	document.getElementById('council_clg_infra_doc_view').onclick = function() {
		file_view($("#clg_infra_doc_id").val(),"clg_reg_infra_college_council","council_document");
	};
	document.getElementById('plant_clg_infra_doc_view').onclick = function() {
		file_view($("#clg_infra_doc_id").val(),"clg_reg_infra_herbal_garden","plant_species_list");
	};
	document.getElementById('herbal_clg_infra_doc_view').onclick = function() {
		file_view($("#clg_infra_doc_id").val(),"clg_reg_infra_herbal_garden","herbal_garden_list");
	};
	document.getElementById('compliance_clg_infra_doc_view').onclick = function() {
		file_view($("#clg_infra_doc_id").val(),"clg_reg_infra_additional_information","compliance_report_doc");
	};
});

document.querySelectorAll('.btnview1').forEach((items, index) => {
	items.addEventListener('click', event => {
// 		debugger;
		var val=parseInt(index);
		var cid = document.getElementById('camera_loc_clg_infra_doc_id'+val).value;
// 		alert(cid)
		file_view(cid,"clg_reg_infra_collge_council_camera","camera_location");
// 		CameraClick(cid);
	})
});

// function CameraClick(index){
// // 	document.getElementById('camera_clg_infra_doc_view'+index).onclick = function() {
// 		file_view($("#camera_loc_clg_infra_doc_id"+index).val(),8,20);
// 	//};
// }

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

//view Document

function file_view(id,val,field) {
	 $("#clg_infra_doc").modal('show');
	 var pdf1="CommonDocPartOne?kmlId2="+id+"&val444="+val+"&fildname1="+field+" ";
	 $("#pdfmodelcanvas") .attr('src',pdf1);
}

</script>

