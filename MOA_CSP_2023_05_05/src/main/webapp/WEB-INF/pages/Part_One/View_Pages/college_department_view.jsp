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
						<h2>View College Departments</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									View College Departments</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

	

		<div class="form-elements-wrapper tunnel-form preview-form">
			<form:form name="View_College_Department" id="View_College_Department" action="" method="post" modelAttribute="">
				<div class="card-style mb-30">
			
			
					<!-- humburger menu for collage regulation - part-1 start -->
			<div class="row viewmenu">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="hamburger-menu">
						<div class="bar" title="Click here for menu">
						</div>
						<div class="hm-title"><h4 class="hm-title-text">College regulation view form report</h4></div>
					</div>
				<input type="hidden" id="institute_id" name="institute_id" value="0">
				
				<input type="hidden" id="hid_college_infrastructure_remark" name="hid_college_infrastructure_remark" value="0">
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
					
					
					<!-- ===========================
							College Department Start
						=========================== -->
						
						
						
						
					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
										<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Computer & Printer
										Availability</h5>
									<a class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#computerprinter"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="comp_print_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Department</h6></th>
													<th><h6>Computer</h6></th>
													<th><h6>Printer</h6></th>
												</tr>
											</thead>
											<tbody id="">
											<c:forEach var="j" items="${getComp_Printer_Avail_View}"
										varStatus="num">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${j.department}</p>
													</td>
													<td>
														<c:if test="${j.computer == '1'}">
															<p id="computer_label1">Available</p>
														</c:if>
														<c:if test="${j.computer == '2'}">
															<p id="computer_label2">Not Available</p>
														</c:if>
														
													</td>
													<td>
													<c:if test="${j.printer == '1'}">
															<p id="printer_label1">Available</p>
														</c:if>
														<c:if test="${j.printer == '2'}">
															<p id="printer_label2">Not Available</p>
														</c:if>
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
									<h5 class="custom-title-tag">UG Department Form</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#ugdepartment"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="ug_depart_table">
											<thead>
												<tr>
													<th rowspan="2"><h6>Sr No</h6></th>
													<th rowspan="2"><h6>Department</h6></th>
													<th rowspan="2"><h6>Computer/Printer Availability
														</h6></th>
													<th rowspan="2"><h6>Dept.Library Books</h6></th>
													<th rowspan="2"><h6>Teaching/Training Material</h6></th>
													<th colspan="3"><h6>Museum</h6></th>
													<th colspan="4"><h6>Lectures / Practicals /
															Clinicals carried out online and offline in last academic
															year</h6></th>
												</tr>
												<tr>
													<th><h6>Charts</h6></th>
													<th><h6>Models</h6></th>
													<th><h6>Specimens</h6></th>
													<th><h6>Theory</h6></th>
													<th><h6>Practical / Clinical</h6></th>
													<th><h6>Tutorial</h6></th>
													<th><h6>Seminar</h6></th>
												</tr>
												<!-- end table row-->
											</thead>
											<tbody id="">
											<c:forEach var="j" items="${getUG_Depart_From_View}"
										varStatus="num">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${j.department}</p>
													</td>
													<td>
													<c:if test="${j.computer_printer_avail == '1'}">
															<p id="computer_printer_label1">Available</p>
													</c:if>
													<c:if test="${j.computer_printer_avail == '2'}">
															<p id="computer_printer_label2">Not Available</p>
													</c:if>
													</td>
													<td><p>${j.dept_library_books}</p></td>
													<td><p>${j.teacher_training_material}</p></td>
													<td><p>${j.museum_charts}</p></td>
													<td><p>${j.museum_models}</p></td>
													<td><p>${j.museum_specimens}</p></td>
													<td><p>${j.theory}</p></td>
													<td><p>${j.practical}</p></td>
													<td><p>${j.tutorial}</p></td>
													<td><p>${j.seminar}</p></td>
												</tr>
												</c:forEach>
												<!-- end table row -->
											</tbody>
										</table>
									</div>
								</div>
							</section>
							<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value">
									<label>Number of educational tours conducted for
										teaching & practical purpose in last academic year</label> <span
										class="value-bind" id=""> ${getDepart_tours_View[0].edu_tours_teach_pract_purpose_ug}</span>
								</div>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">PG Department Form</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#pgdepartment"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="pg_depart_table">
											<thead>
												<tr>
													<th rowspan="2"><h6>Sr No</h6></th>
													<th rowspan="2"><h6>Subject</h6></th>

													<th colspan="5"><h6>Lectures / Practicals /
															Clinicals carried out online and offline in last academic
															year</h6></th>
												</tr>
												<tr>
													<th><h6>Theory</h6></th>
													<th><h6>Practicals / Clinicals</h6></th>
													<th><h6>Seminars</h6></th>
													<th><h6>Case Presentation</h6></th>
													<th><h6>Journal Meeting</h6></th>
												</tr>
												<!-- end table row-->
											</thead>
											<tbody id="">
											<c:forEach var="j" items="${getPG_Depart_From_View}"
										varStatus="num">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${j.subject}</p>
													</td>
													<td><p>${j.theory}</p></td>
													<td><p>${j.practical}</p></td>
													<td><p>${j.seminar}</p></td>
													<td><p>${j.case_presentation}</p></td>
													<td><p>${j.journal_meeting}</p></td>
												</tr>
												</c:forEach>
												<!-- end table row -->
											</tbody>
										</table>
									</div>
								</div>
							</section>
							<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value">
									<label>Number of educational tours conducted for
										teaching & practical purpose in last academic year</label> <span
										class="value-bind" id=""> ${getDepart_tours_View[0].edu_tours_teaching_pract_purpose_pg}</span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value">
									<label>Educational tours conducted in last academic
										year</label> <span class="value-bind daobtn"> <a 
										class="main-btn dark-btn btn-hover btn-sm btnview"
										title="View" id="edu_tour_con_lst_conduct_view"><i class="lni lni-eye"></i></a></span>
										<input type="hidden" id="edu_tour_con_lst_conduct_view_hid" name="edu_tour_con_lst_conduct_view_hid"
										 value="${getDepart_tours_View[0].id}"/>
								</div>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Academic Performance</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#acadperformance"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="acade_per_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Department</h6></th>
													<th><h6>Advance Teaching Plan</h6></th>
													<th><h6>Teaching Diary</h6></th>
													<th><h6>Journal / Practical Notebook</h6></th>
												</tr>
												<!-- end table row-->
											</thead>
											<tbody id="">
											<c:forEach var="j" items="${getDepart_academic_performance_View}"
										varStatus="num">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${j.department}</p>
													</td>
													<td>
													<c:if test="${j.advance_teaching_plan == '1'}">
															<p id="advance_teaching_plan_label1">Available</p>
													</c:if>
													<c:if test="${j.advance_teaching_plan == '2'}">
															<p id="advance_teaching_plan_label2">Not Available</p>
													</c:if>
													<td>
													<c:if test="${j.teaching_diary == '1'}">
															<p id="teaching_diary_label1">Available</p>
													</c:if>
													<c:if test="${j.teaching_diary == '2'}">
															<p id="teaching_diary_label2">Not Available</p>
													</c:if>
													<td>
													<c:if test="${j.journal_practical == '1'}">
															<p id="journal_practical_label1">Available</p>
													</c:if>
													<c:if test="${j.journal_practical == '2'}">
															<p id="journal_practical_label2">Not Available</p>
													</c:if>
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
					
					
					
					<!-- ===========================
							College Department End
						=========================== -->

				</div>
				
				<!-- Modal start 1-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="computerprinter">
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
								<textarea placeholder="Enter Remarks" rows="5" id="clg_dep_comp_printer_avail_rmk" name="clg_dep_comp_printer_avail_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_dep_comp_printer_avail_remark"  class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

<!-- Modal start 2-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="ugdepartment">
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
								<textarea placeholder="Enter Remarks" rows="5" id="clg_dep_ug_rmk" name="clg_dep_ug_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_dep_ug_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

<!-- Modal start 3-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="pgdepartment">
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
								<textarea placeholder="Enter Remarks" rows="5" id="clg_dep_pg_rmk" name="clg_dep_pg_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_dep_pg_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

<!-- Modal start 4-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="acadperformance">
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
								<textarea placeholder="Enter Remarks" rows="5" id="clg_dep_acad_performance_rmk" name="clg_dep_acad_performance_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_dep_acad_performance_rmk_submit" class="main-btn success-btn  btn-hover" type="button">Submit</a>
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


<!-- Canvas Modal satrt -->

<div class="modal fade custom-modal custom-modal-pdf" tabindex="-1"
	aria-labelledby="myLargeModalLabel" id="dept_tour_view_doc"
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


<script src="assets/vendor/hamburger-menu/hemburgermenu.js" type="text/javascript"></script>

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







<script type="text/javascript" nonce="${cspNonce}">
	$(document).ready(function() {
		if('${role}'!="NCH"){
			$(".btnedit").css("visibility", "hidden");
			$(".viewmenu").addClass("d-none");

		}
		$.ajaxSetup({
			async : false
		});
		
		$("#institute_id").val('${inst_id}');
// 		alert('${getDepart_tours_View[0].id}');
		

	});
	

	function Save_As_Draft_Remarks() {

		$.ajaxSetup({
		    async: false
		});	
			
			var form_data = new FormData(document.getElementById("View_College_Department"));
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
		    	  	$('#computerprinter').modal('hide');
		    	  	$('#ugdepartment').modal('hide');
		    	  	$('#pgdepartment').modal('hide');
		    	  	$('#acadperformance').modal('hide');
		      }
		      else{
		    	  alert(j);
		    		$('#computerprinter').modal('hide');
		    	  	$('#ugdepartment').modal('hide');
		    	  	$('#pgdepartment').modal('hide');
		    	  	$('#acadperformance').modal('hide');
		      }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
		}
	
document.addEventListener('DOMContentLoaded', function() {
	
	
// 	document.getElementById('basics_information_view').onclick = function() {
// 		ViewData_basic_info();
// 	};
// 	document.getElementById('college_infrastructure_view').onclick = function() {
// 		ViewData_college_infra();
// 	};
// 	document.getElementById('college_department_view').onclick = function() {
// 		ViewData_college_department();
// 	};
// 	document.getElementById('college_financial_view').onclick = function() {
// 		ViewData_college_financial();
// 	};
// 	document.getElementById('student_details_view_page').onclick = function() {
// 		ViewData_student_dtl();
// 	};
// 	document.getElementById('college_staff_info_view').onclick = function() {
// 		ViewData_clg_staff_info();
// 	};
// 	document.getElementById('college_staff_list_view').onclick = function() {
// 		ViewData_clg_staff_list();
// 	};
// 	document.getElementById('department_equipments_view').onclick = function() {
// 		ViewData_department_eqp();
// 	};
// 	document.getElementById('hospital_infrastructure_view').onclick = function() {
// 		ViewData_hos_infra();
// 	};
// 	document.getElementById('hospital_ipdopd_view').onclick = function() {
// 		ViewData_hos_ipd();
// 	};
// 	document.getElementById('otherhospital_detail_view').onclick = function() {
// 		ViewData_other_hos_dtl();
// 	};
// 	document.getElementById('hospital_staffdetails_view').onclick = function() {
// 		ViewData_hos_staff_dtl();
// 	};
// 	document.getElementById('hospital_staff_list_view').onclick = function() {
// 		ViewData_hos_staff_list();
// 	};
// 	document.getElementById('hospital_equipments_view').onclick = function() {
// 		ViewData_hos_eqp();
// 	};
// 	document.getElementById('declaration_view').onclick = function() {
// 		ViewData_declaration();
// 	};
if('${role}'=="NCH"){
	document.getElementById('clg_dep_comp_printer_avail_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	
	document.getElementById('clg_dep_ug_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_dep_pg_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_dep_acad_performance_rmk_submit').onclick = function() {
		Save_As_Draft_Remarks();
	};
	
	//FOR C:URL

// 	document.addEventListener('DOMContentLoaded', function() {
		
		
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
		
		
		////document view canvas
		
		document.getElementById('edu_tour_con_lst_conduct_view').onclick = function() {
			file_view($("#edu_tour_con_lst_conduct_view_hid").val(),"clg_reg_clg_dept_tours","edu_tours_pg");
		};

	});

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

	

// });

function file_view(id,val,field) {
	 $("#dept_tour_view_doc").modal('show');
	 var pdf1="CommonDocPartOne?kmlId2="+id+"&val444="+val+"&fildname1="+field+" ";
	 $("#pdfmodelcanvas") .attr('src',pdf1);
}


</script>

