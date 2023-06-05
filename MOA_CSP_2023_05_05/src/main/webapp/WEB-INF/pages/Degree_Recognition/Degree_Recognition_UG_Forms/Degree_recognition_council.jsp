<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <script type="text/javascript" src="assets/js/jquery-3.6.0.min.js"></script> -->
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript"	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">

<!-- <script src="js/amin_module/webmaster/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">

<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

<script type="text/javascript" src="js/watermark/common.js"></script>

<link rel="stylesheet" href="assets/vendor/internal_css.css">

<section class="search_regulation Degree_Recognition">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>
							<span id="lbladd">View UG Details</span>
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Degree Recognition</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->

		<div class="custom_v_tab">
			<div class="row">
				<div class="col-lg-12">
					<!-- input style start -->
					<div class="card-style mb-30">
						<div class="tabs content_h800">

							<!-- Start Forms  -->
							<!-- Form A UG -->

							<div class="tab tablinks dg-rec-block" id="tab_id7">
								<button class="tab-toggle">First Choose The University and Institute</button>
							</div>
							<div class="content tabcontent" id="Form_z">
								<h4 class="heading">Choose The University and Institute</h4>
								<div class="inst_block mb-10">
									<h6 class="mb-2">Instruction</h6>
									<ul class="inst_list">
										<li><p class="inst_text">Press last tab to Approve all the form details</p></li>
									</ul>
								</div>
								<div class="row">
									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="select-style-1">
											<label>Select University<strong class="mandatory">*</strong></label>
											<div class="select-position">
												<select name="uni_id" id="uni_id" class="form-control-lg select2 narrow wrap">
													<option value="0">---Select University---</option>
													<c:forEach var="item" items="${getMedUniversityName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														<%-- 																<option value="${item.university_name}" name="${item.university_name}">${item.university_name}</option> --%>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="select-style-1">
											<label>Select Institute<strong class="mandatory">*</strong></label>
											<div class="select-position">
												<select name="institute_id" id="institute_id"
													class="form-control-lg select2 narrow wrap">
													<option value="0">---Select Institute---</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="select-style-1">
											<label>Council Status<strong class="mandatory">*</strong></label>
											<input type="hidden" id="id" name="id" class="form-control"
												value="0" autocomplete="off">
											<div class="select-position">
												<select name="institute_status" id="institute_status"
													class="singleselect form-control form-control-lg">
													<option value="">---Select Status---</option>
													<option value="0">Pending</option>
													<option value="1">Approved</option>
													<option value="-1">Rejected</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<ul class="buttons-group mainbtn">
									<li><a id="btn-reload"
										class="main-btn secondary-btn btn-hover btn-iconic-icon"
										type="button"><i
											class="lni lni-search-alt"></i>Search Details</a></li>
									<li>
								</ul>
							</div>

							<div class="tab tablinks dg-rec-block" id="tab_id1">
								<button class="tab-toggle">Undergraduate Course In
									Homoeopathy In India</button>
							</div>
							<div class="content tabcontent" id="Form_A">
								<h4 class="heading">Undergraduate Course In Homoeopathy In
									India</h4>
								<div class="row">
									<div class="col-12">
										<div class="card-style mb-30">
											<div class="table-wrapper custom-datatable-p">
									<input type="hidden" id="acoun_hid" name="acoun_hid"/>
												<table class="table" id="search_system_a_council">
													<thead>
														<tr>
															<th><h6>Ser No</h6></th>
															<th><h6>University</h6></th>
															<th><h6>Institute Name</h6></th>
															<th><h6>Name Of Applicant University</h6></th>
																<th><h6>Name Of Undergraduate Course</h6></th>
																<th><h6>Abbreviation Of Undergraduate Course</h6></th>
														        <th><h6>Academic Year Applied For</h6></th>
														        <th><h6>Academic Intake Capacity Permitted</h6></th>
														        <th><h6>Number Of Students Admitted</h6></th>
														        <th><h6>Remarks</h6></th>
														        <th><h6>Permission From Central Government Document</h6></th>
														        <th><h6>Application For Admitted Academic Session Document</h6></th>
															
															<th><h6>Action</h6></th>
														</tr>
													</thead>
													<tbody class="custom-datatablepra"></tbody>
												</table>
											</div>
										</div>
									</div>
									
								</div>
							</div>

							<!-- Form B Institute -->

							<div class="tab tablinks dg-rec-block" id="tab_id2">
								<button class="tab-toggle">Admitted Students For Each
									College/Institute As Per Proforma Provide</button>
							</div>
							<div class="content tabcontent" id="Form_B">
								<input type="hidden" id="h_id" name="h_id" value="a2"> <input
									type="hidden" id="form_b_id" name="form_b_id" value="0"
									class="form-control autocomplete"> <input type="hidden"
									id="userId" name="userId" value="${userId}">
								<h4 class="heading">Admitted Students For Each
									College/Institute As Per Proforma Provide</h4>
								<div class="row">
									<div class="col-12">
										<div class="card-style mb-30">
											<div class="table-wrapper custom-datatable-p">
											<input type="hidden" id="bcoun_hid" name="bcoun_hid"/>
												<table class="table" id="search_system_b_council">
													<thead>
														<tr>
															<th><h6>Ser No</h6></th>
															<th><h6>Institute Name</h6></th>
															<th><h6>Student Name</h6></th>
															<th><h6>Date Of Admission</h6></th>
															<th><h6>Neet Rank</h6></th>
															<th><h6>Rank</h6></th>
															<th><h6>Marks</h6></th>
															<th><h6>All India Quota</h6></th>
															<th><h6>State Quota</h6></th>
															<th><h6>Management Quota</h6></th>
															<th><h6>Admission Authority</h6></th>
															<th><h6>CourtOrder & Others</h6></th>
															<th><h6>Date of Enrollment In University</h6></th>
															<th><h6>University Enrollment Number</h6></th>
															<th><h6>Date Of Internship Completion</h6></th>
															<th><h6>Remarks</h6></th>
															<th><h6>Action</h6></th>
														</tr>
													</thead>
													<tbody class="custom-datatablepra"></tbody>
												</table>
											</div>
										</div>
									</div>
									
								</div>
							</div>

							<!-- Form C UG -->

<!-- 							<div class="tab tablinks dg-rec-block" id="tab_id3"> -->
<!-- 								<button class="tab-toggle">College Wise/Institute Wise -->
<!-- 									Regarding The Hospital Attached</button> -->
<!-- 							</div> -->

<!-- 							<div id="Form_C" class="content tabcontent"> -->
<%-- 								<form id="Form_C_details"> --%>
<!-- 									<h4 class="heading">College Wise/Institute Wise Regarding -->
<!-- 										The Hospital Attached</h4> -->
<!-- 									<div class="tables-wrapper"> -->
<!-- 										<div class="row"> -->
<!-- 											<div class="col-lg-12"> -->
<!-- 												<input type="hidden" name="review_id1" id="review_id1" -->
<%-- 													value="${review_id}" /> --%>
<!-- 												<div class="card-style mb-30"> -->
<!-- 													<div class="table-wrapper custom-datatable-p"> -->
<!-- 							<input type="hidden" id="ccoun_hid" name="ccoun_hid"/> -->
<!-- 														<table class="table" id="search_system_c_council"> -->
<!-- 															<thead> -->
<!-- 																<tr> -->
<!-- 																	<th><h6>Ser No</h6></th> -->
<!-- 																		<th><h6>Institute Name</h6></th> -->
<!-- 																	<th><h6>Name Of Homoeopathic Medical Collegee</h6></th> -->
<!-- 																	<th><h6>Name Of Attached Homoeopathic Hospital</h6></th> -->
<!-- 																	<th><h6>Name Of Attached Super Speciality Hospital</h6></th> -->
<!-- 																	<th><h6>MOU Date</h6></th> -->
<!-- 																	<th><h6>Copy Of MOU Date</h6></th> -->
<!-- 																	<th><h6>Name Of Hospital Staff</h6></th> -->
<!-- 																	<th><h6>Designation</h6></th> -->
<!-- 																	<th><h6>Qualification</h6></th> -->
<!-- 																	<th><h6>FullTime/Part Time</h6></th> -->
<!-- 																	<th><h6>Remarks</h6></th> -->
<!-- 																	<th><h6>Action</h6></th> -->
<!-- 																</tr> -->
<!-- 																end table row -->
<!-- 															</thead> -->
<!-- 															<tbody class="custom-datatablepra"></tbody> -->
<!-- 														</table> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
											
<!-- 										</div> -->
<!-- 									</div> -->
<%-- 								</form> --%>
<!-- 							</div> -->
							<!-- Form D Institute -->

							<div class="tab tablinks dg-rec-block" id="tab_id4">
								<button class="tab-toggle">Migrated Students College
									Wise/Institute Wise</button>
							</div>

							<div id="Form_D" class="content tabcontent">
								<form id="Form_D_details">
									<h4 class="heading">For Those Who Migrated To Other
										College</h4>
									<div class="tables-wrapper">
										<div class="row">
											<div class="col-lg-12">
												<input type="hidden" name="review_id1" id="review_id1"
													value="${review_id}" />
												<div class="card-style mb-30">
													<div class="table-wrapper custom-datatable-p">
													<input type="hidden" id="dtocoun_hid" name="dtocoun_hid"/>
														<table class="table" id="search_system_dto_council">
															<thead>
																<tr>
																	<th><h6>Ser No</h6></th>
																	<th><h6>Institute Name</h6></th>
																	<th><h6>Name of Institution</h6></th>
																	<th><h6>Name of Student</h6></th>
																	<th><h6>Date Of Migration</h6></th>
																	<th><h6>Professional Year Of Migration</h6></th>
																	<th><h6>University Enrollment Number</h6></th>
																	<th><h6>Remarks</h6></th>
																	<th><h6>Action</h6></th>
																</tr>
																<!-- end table row-->
															</thead>
															<tbody class="custom-datatablepra"></tbody>
														</table>
													</div>
												</div>
											</div>
											
										</div>
									</div>

									<h4 class="heading">For Those Who Migrated From Other College</h4>
									<div class="tables-wrapper">
										<div class="row">
											<div class="col-lg-12">
												<input type="hidden" name="review_id1" id="review_id1"
													value="${review_id}" />
												<div class="card-style mb-30">
													<div class="table-wrapper custom-datatable-p">
													<input type="hidden" id="dfromcoun_hid" name="dfromcoun_hid"/>
														<table class="table" id="search_system_dfrom_council">
															<thead>
																<tr>
																	<th><h6>Ser No</h6></th>
																	<th><h6>Institute Name</h6></th>
																	<th><h6>Name of Institution</h6></th>
																	<th><h6>Name of Student</h6></th>
																	<th><h6>Date Of Migration</h6></th>
																	<th><h6>Professional Year Of Migration</h6></th>
																	<th><h6>University Enrollment Number</h6></th>
																	<th><h6>Remarks</h6></th>
																	<th><h6>Action</h6></th>
																</tr>
																<!-- end table row-->
															</thead>
															<tbody class="custom-datatablepra"></tbody>
														</table>
													</div>
												</div>
											</div>
											
										</div>
									</div>

								</form>
							</div>
							<!-- Form E UG -->
<!-- 							<div class="tab tablinks dg-rec-block" id="tab_id5"> -->
<!-- 								<button class="tab-toggle">Examiners Appointed in For -->
<!-- 									Examination</button> -->
<!-- 							</div> -->

<!-- 							<div id="Form_F" class="content tabcontent"> -->
<!-- 								<h4 class="heading">Examiners Appointed in For Examination</h4> -->
<!-- 								<div class="row"> -->
<!-- 									<div class="col-lg-12"> -->
<!-- 										<input type="hidden" name="review_id1" id="review_id1" -->
<%-- 											value="${review_id}" /> --%>
<!-- 										<div class="card-style mb-30"> -->
<!-- 											<div class="table-wrapper custom-datatable-p"> -->
<!-- 											<input type="hidden" id="ecoun_hid" name="ecoun_hid"/> -->
<!-- 												<table class="table" id="search_system_e_council"> -->
<!-- 													<thead> -->
<!-- 														<tr> -->
<!-- 															<th><h6>Ser No</h6></th> -->
<!-- 															<th><h6>University</h6></th> -->
<!-- 															<th><h6>Name Of Examiners External/Internal</h6></th> -->
<!-- 															<th><h6>Subject</h6></th> -->
<!-- 															<th><h6>Year</h6></th> -->
<!-- 															<th><h6>Qualification</h6></th> -->
<!-- 															<th><h6>Teaching Experience</h6></th> -->
<!-- 															<th><h6>Teachers Code(Mention The Number)</h6></th> -->
<!-- 															<th><h6>Registration Number</h6></th> -->
<!-- 															<th><h6>Date Of University Appointment Letter</h6></th> -->
<!-- 															<th><h6>Action</h6></th> -->
<!-- 														</tr> -->
<!-- 													</thead> -->
<!-- 													<tbody class="custom-datatablepra"></tbody> -->
<!-- 												</table> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
									
<!-- 								</div> -->
<!-- 							</div> -->
							<!-- Form F Institute -->
							<div class="tab tablinks dg-rec-block" id="tab_id6">
								<button class="tab-toggle">Intern Students For Course</button>
							</div>

							<div id="Form_F" class="content tabcontent">
								<h4 class="heading">Intern Student For Course</h4>
								<div class="row">
									<div class="col-lg-12">
										<input type="hidden" name="review_id1" id="review_id1"
											value="${review_id}" />
										<div class="card-style mb-30">
											<div class="table-wrapper custom-datatable-p">
											<input type="hidden" id="fcoun_hid" name="fcoun_hid"/>
												<table class="table" id="search_system_f_council">
													<thead>
														<tr>
															<th><h6>Ser No</h6></th>
															<th><h6>Institute Name</h6></th>
															<th><h6>Name Of Students</h6></th>
															<th><h6>Year Of Admission</h6></th>
															<th><h6>Date Of Result Of Final Year BHMS/Course Completed</h6></th>
															<th><h6>Provisional Register Number</h6></th>
															<th><h6>Year Of Provisional Registration(From State Board)</h6></th>
															<th><h6>Date Of Starting Internship</h6></th>
															<th><h6>Date Of Completion Internship</h6></th>
															<th><h6>Remarks If Any</h6></th>
															<th><h6>Action</h6></th>
														</tr>
													</thead>
													<tbody class="custom-datatablepra"></tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><input type="button" id="btn-saveA"
												class="main-btn success-btn  btn-hover" value="Submit For Approval"></li>
										</ul>
									</div>
								</div>
							</div>

							<!-- End Forms -->

						</div>
						<!-- End tabcontent -->
					</div>
					<!-- End Card style 12 -->
				</div>
				<!-- End col log 12 -->
			</div>
			<!--  End row -->
		</div>
		<!-- End Custom V tab -->



	</div>
	<!-- End container-fluid -->
</section>

		<!--  The Modal   -->
		<div class="modal" id="myModal">
  			<div class="modal-dialog modal-dialog-top">
  			  <div class="modal-content">

<!--       Modal Header -->
     			 <div class="modal-header">
     			   <h4 class="modal-title">Clarification Data</h4>
     			 </div>

<!--       Modal body -->
      <div class="modal-body">
		<div class="modal-header">
					<div class="input-style-1">
						<label>Enter Clarification<strong class="mandatory">*</strong></label> 
						<input type="text" name="reject_remarks" id="reject_remarks" class="form-control"
							placeholder="Enter Clarification Of Data" autocomplete="off">
					</div>
			</div>     
	  </div>
	 <div class="col-lg-12 col-md-12 col-sm-12">
		<ul class="buttons-group mainbtn">
			<li><input type="button" id="btn-save" class="main-btn success-btn  btn-hover" value="Submit Clarification">
				<button type="button" id="myModalClose" class="btn btn-danger" data-bs-dismiss="modal" id="modal-clos-btn">Close</button>
				    
				     <input type="hidden" id="rejVal" name="rejVal">
			</li>
		</ul>
    </div>
    </div>
  </div>
</div>

<!--  Model A UG -->
<!--  modal start -->
<div class="modal fade custom-modal" id="ugamodal"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="staticBackdropLabel">View Data</h3>
				<button type="button" class="btn-close" id="btn-close"
					data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData">
					<div class="row">
						<input type="hidden" id="h_id" name="h_id">
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Applicant University Granting
									Qualification :</label> <span class="value-bind"
									id="name_of_applicant_university"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Full Name Of The Undergraduate Course :</label> <span
									class="value-bind" id="undergraduate_course"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Abbreviation of Undergraduate Course:</label> <span
									class="value-bind" id="abbre_undergraduate_course"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Application For Admitted Academic Session:</label>
								<span class="value-bind" id="academic_session"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">College/Center/Medical institute :</label> <span
									class="value-bind" id="name_of_college"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Country :</label> <span class="value-bind"
									id="country"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">State :</label> <span class="value-bind"
									id="state"></span>
							</div>
						</div>

						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">District:</label> <span class="value-bind"
									id="district"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">City :</label> <span class="value-bind" id="city"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Postal Address :</label> <span class="value-bind"
									id="postal_address"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Email :</label> <span class="value-bind"
									id="email"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Website :</label> <span class="value-bind"
									id="website"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Academic Year Applied for :</label> <span
									class="value-bind" id="academic_year_applied_for"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Permission from Central Government :</label> <span
									class="value-bind" id="permission_from_central_gov"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Admission Intake Capacity permitted :</label> <span
									class="value-bind" id="admission_intake"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Number of Student Admitted :</label> <span
									class="value-bind" id="num_of_student_admitted"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Remarks :</label> <span class="value-bind"
									id="remarks"></span>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<ul class="buttons-group">
							<li><button type="button"
									class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
									data-dismiss="modal" aria-label="Close">Close</button></li>
							<li><a href="#0" class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon dg-rec-none" id="pdfex12">
							 <i id="printId" value="PDF" title="Export to PDF"><i class="bi bi-file-pdf"></i></i>Download Print
							</a></li>
							<input type="button" class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon"
								value=" Download Print" id="pdfex">
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- modal end -->

<!--  Model B UG -->
<!--  modal start -->
<div class="modal fade custom-modal" id="ugbmodal"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="staticBackdropLabel">View Data</h3>
				<button type="button" class="btn-close" id="btn-close"
					data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData">
					<div class="row">
						<input type="hidden" id="b_id" name="b_id">
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Name Of Student :</label> <span class="value-bind"
									id="student_name"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Date of Admission :</label> <span
									class="value-bind" id="date_of_admission"></span>
							</div>
						</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Neet Rank:</label> <span class="value-bind" id="neet_rank"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Rank:</label> <span class="value-bind" id="rank"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Marks:</label> <span class="value-bind" id="marks"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">All India :</label> <span class="value-bind"
									id="all_india"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">State :</label> <span class="value-bind"
									id="state"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Management :</label> <span class="value-bind"
									id="management_quota"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Admission Authority :</label> <span
									class="value-bind" id="admission_authority"></span>
							</div>
						</div>

						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Court Order & Others:</label> <span
									class="value-bind" id="court_order"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Date of Enrollment In University :</label> <span
									class="value-bind" id="date_of_enroll_university"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">University Enrollment Number :</label> <span
									class="value-bind" id="uni_enroll_number"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Date Of Internship Completion :</label> <span
									class="value-bind" id="date_of_intern_compl"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Remarks :</label> <span class="value-bind"
									id="remarks_form_b"></span>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<ul class="buttons-group">
							<li><button type="button"
									class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
									data-dismiss="modal" aria-label="Close">Close</button></li>
							<li><a href="#0" class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon dg-rec-none"
								id="pdfbex12"> <i id="printId" value="PDF"
									title="Export to PDF"><i class="bi bi-file-pdf"></i></i>
									Download Print
							</a></li>
							<input type="button"
								class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon"
								value=" Download Print" id="pdfbex">
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- modal end -->

<!--  Model C UG -->
<!--  modal start -->
<div class="modal fade custom-modal" id="ugcmodal"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="staticBackdropLabel">View Data</h3>
				<button type="button" class="btn-close" id="btn-close"
					data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData">
					<div class="row">
						<input type="hidden" id="c_id" name="c_id">
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Name Of Homoeopathic Medical
									College/Institute :</label> <span class="value-bind"
									id="name_homoeopathic_medical_clg"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Name Of Attached Homoeopathic
									Hospital/Institute :</label> <span class="value-bind"
									id="attached_homoeopath_hospital"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Name Of Attached Super Speciality
									Hospital:</label> <span class="value-bind"
									id="super_speciality_hospital"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">MOU Date:</label> <span class="value-bind"
									id="mou_date"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Copy Of MOU Date :</label> <span
									class="value-bind" id="copy_of_mou"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Hospital Staff :</label> <span class="value-bind"
									id="name_of_hospital_staff"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Designation :</label> <span class="value-bind"
									id="designation"></span>
							</div>
						</div>

						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Qualification:</label> <span class="value-bind"
									id="qualification"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Full Time/Part Time :</label> <span
									class="value-bind" id="fulltime_parttime"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Remarks :</label> <span class="value-bind"
									id="remarks_form_c"></span>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<ul class="buttons-group">
							<li><button type="button"
									class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
									data-dismiss="modal" aria-label="Close">Close</button></li>
							<li ><a href="#0"
								class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon dg-rec-none"
								id="pdfcex12"> <i id="printId" value="PDF"
									title="Export to PDF"><i class="bi bi-file-pdf"></i></i>
									Download Print
							</a></li>
							<input type="button"
								class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon"
								value=" Download Print" id="pdfcex">
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- modal end -->

<!--  Model D UG -->
<!--  modal start -->
<div class="modal fade custom-modal" id="ugdmodal"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="staticBackdropLabel">View Data</h3>
				<button type="button" class="btn-close" id="btn-close"
					data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData">
					<div class="row">
						<input type="hidden" id="d_id" name="d_id">
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Name Of Student :</label> <span class="value-bind"
									id="student_name_to_migrated"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Date Of Migration :</label> <span
									class="value-bind" id="migrated_dt_to"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Professional Year Of Migration:</label> <span
									class="value-bind" id="professional_year_migrated"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">University Enrollment Number:</label> <span
									class="value-bind" id="university_enrollment_number"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Remarks :</label> <span class="value-bind"
									id="remarks_form_d"></span>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<ul class="buttons-group">
							<li><button type="button"
									class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
									data-dismiss="modal" aria-label="Close">Close</button></li>
							<li><a href="#0"
								class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon dg-rec-none"
								id="pdfdex12"> <i id="printId" value="PDF"
									title="Export to PDF"><i class="bi bi-file-pdf"></i></i>
									Download Print
							</a></li>
							<input type="button"
								class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon"
								value=" Download Print" id="pdfdex">
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- modal end -->


<!--  Model E UG -->
<!--  modal start -->
<div class="modal fade custom-modal" id="ugemodal"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="staticBackdropLabel">View Data</h3>
				<button type="button" class="btn-close" id="btn-close"
					data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData">
					<div class="row">
						<input type="hidden" id="e_id" name="e_id">
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Name Of Student :</label> <span class="value-bind"
									id="name_of_students_migrated"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Date Of Migration :</label> <span
									class="value-bind" id="dt_of_migration"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Professional Year Of Migration:</label> <span
									class="value-bind" id="professional_year"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">University Enrollment Number:</label> <span
									class="value-bind" id="university_enroll_num"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Remarks :</label> <span class="value-bind"
									id="remarks_migrated"></span>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<ul class="buttons-group">
							<li><button type="button"
									class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
									data-dismiss="modal" aria-label="Close">Close</button></li>
							<li><a href="#0" class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon dg-rec-none"
								id="pdfdex12"> <i id="printId" value="PDF"
									title="Export to PDF"><i class="bi bi-file-pdf"></i></i>
									Download Print
							</a></li>
							<input type="button"
								class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon"
								value=" Download Print" id="pdfdfromex">
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- modal end -->
<!--  Model F UG -->
<!--  modal start -->
<div class="modal fade custom-modal" id="ugfmodal"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="staticBackdropLabel">View Data</h3>
				<button type="button" class="btn-close" id="btn-close"
					data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData">
					<div class="row">
						<input type="hidden" id="f_id" name="f_id">
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Name Of Examiners External/Internal :</label> <span
									class="value-bind" id="name_of_examiners"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Subject :</label> <span class="value-bind"
									id="subject_examiners"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Year:</label> <span class="value-bind"
									id="year_examiners"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Qualification:</label> <span class="value-bind"
									id="qualification_examiners"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Teaching Experience :</label> <span
									class="value-bind" id="teaching_experience"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Teachers Code :</label> <span class="value-bind"
									id="teacher_code"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Registration Number :</label> <span
									class="value-bind" id="reg_number"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Date Of University Appointment Letter :</label> <span
									class="value-bind" id="d_university_appointment"></span>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<ul class="buttons-group">
							<li><button type="button"
									class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
									data-dismiss="modal" aria-label="Close">Close</button></li>
							<li><a href="#0" class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon dg-rec-none"
								id="pdfdex12"> <i id="printId" value="PDF"
									title="Export to PDF"><i class="bi bi-file-pdf"></i></i>
									Download Print
							</a></li>
							<input type="button" class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon"
								value=" Download Print" id="pdffex">
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- modal end -->

<!--  Model G UG -->
<!--  modal start -->
<div class="modal fade custom-modal" id="uggmodal"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="staticBackdropLabel">View Data</h3>
				<button type="button" class="btn-close" id="btn-close"
					data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData">
					<div class="row">
						<input type="hidden" id="g_id" name="g_id">
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Name Of Students :</label> <span
									class="value-bind" id="name_of_students"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Year Of Admission :</label> <span
									class="value-bind" id="year_of_admission"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Date Of Result Of Final Year BHMS/Course
									Completed:</label> <span class="value-bind"
									id="date_of_result_final_year"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Provisional Registration No:</label> <span
									class="value-bind" id="provisional_reg_no"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Year Of Provisional Registration :</label> <span
									class="value-bind" id="year_of_provisional_reg"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Date Of Starting Of Internship :</label> <span
									class="value-bind" id="date_of_starting_internship"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Date Of Completion Of Internship :</label> <span
									class="value-bind" id="date_of_completion_internship"></span>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2 data-view">
								<label for="">Remarks :</label> <span class="value-bind"
									id="remark_form_f"></span>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<ul class="buttons-group">
							<li><button type="button"
									class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
									data-dismiss="modal" aria-label="Close">Close</button></li>
							<li><a href="#0" class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon dg-rec-none"
								id="pdfdex12"> <i id="printId" value="PDF"
									title="Export to PDF"><i class="bi bi-file-pdf"></i></i>
									Download Print
							</a></li>
							<input type="button"
								class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon"
								value=" Download Print" id="pdfgex">
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- modal end -->

<script type="text/javascript" src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>

<c:url value="Undergraduate_Course_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search1" name="search1">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="course_id1" id="course_id1" value="0" />
</form:form>

<c:url value="Admitted_Students_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search2" name="search2">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="add_stud" id="add_stud" value="0" />
</form:form>

<c:url value="Institute_Wise_Regarding_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search3" name="search3">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="inst_stud" id="inst_stud" value="0" />
</form:form>

<c:url value="Migrated_To_Other_College_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search4" name="search4">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="migrat_stud" id="migrat_stud" value="0" />
</form:form>

<c:url value="Migrated_From_Other_College_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search5" name="search5">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="migrat_from_stud" id="migrat_from_stud"
		value="0" />
</form:form>

<c:url value="Examiners_Appointed_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search6" name="search6">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="exam_stud" id="exam_stud" value="0" />
</form:form>

<c:url value="Intern_Student_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search7" name="search7">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="intern_stud" id="intern_stud" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {
	
        if(window.location.href.includes("msg"))
        {
                 var url = window.location.href.split("?msg")[0];
                 window.location = url;
        }
		
		$("#tab_id1").hide();
		$("#tab_id2").hide();
// 		$("#tab_id3").hide();
		$("#tab_id4").hide();
// 		$("#tab_id5").hide();
		$("#tab_id6").hide();
		
});

function search_detail(){

	  if($("select#uni_id").val().trim() == "0") 
	  {
	       alert("Please Select University ");
	       $("select#uni_id").focus();
	       return false;
	  }
	  else if($("select#institute_id").val().trim() == "0") 
	  {
	       alert("Please Select Institute ");
	       $("select#institute_id").focus();
	       return false;
	  }
	  else if($("select#institute_status").val().trim() == "") 
	  {
	       alert("Please Select Council Status ");
	       $("select#institute_status").focus();
	       return false;
	  }
	
	mockjax1('search_system_a_council');
	table1 = dataTable('search_system_a_council');
	
	mockjax1('search_system_b_council');
	table2 = dataTable('search_system_b_council');
	
// 	mockjax1('search_system_c_council');
// 	table3 = dataTable('search_system_c_council');
	
	mockjax1('search_system_dto_council');
	table4 = dataTable('search_system_dto_council');
	
	mockjax1('search_system_dfrom_council');
	table5 = dataTable('search_system_dfrom_council');
	
// 	mockjax1('search_system_e_council');
// 	table6 = dataTable('search_system_e_council');
	
	mockjax1('search_system_f_council');
	table7 = dataTable('search_system_f_council');

	table1.ajax.reload();
	table2.ajax.reload();
// 	table3.ajax.reload();
	table4.ajax.reload();
	table5.ajax.reload();
// 	table6.ajax.reload();
	table7.ajax.reload();
	

	$("#tab_id1").show();
	$("#tab_id2").show();
// 	$("#tab_id3").show();
	$("#tab_id4").show();
// 	$("#tab_id5").show();
	$("#tab_id6").show();
	
	if($("#institute_status").val() == "0") 
	{
		 $("btn-saveA").show();
    }
	if($("#institute_status").val() == "1") 
	{
		 $("btn-saveA").hide();
    }
	if($("#institute_status").val() == "-1") 
	{
		 $("btn-saveA").hide();
    }
}

////////////////////////////////

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('uni_id').onchange = function() {
			 getInstitute();
		};
		
		document.getElementById('pdfex').onclick = function() {
			getDownloadPdfUploadFile();
		};
		
		document.getElementById('pdfbex').onclick = function() {
			getDownloadPdfBUploadFile();
		};
		
		document.getElementById('pdfcex').onclick = function() {
			getDownloadPdfCUploadFile();
		};
		
		document.getElementById('pdfdex').onclick = function() {
			getDownloadPdfDUploadFile();
		};
		
		document.getElementById('pdfdfromex').onclick = function() {
			getDownloadPdfDFromUploadFile();
		};
		
		document.getElementById('pdffex').onclick = function() {
			getDownloadPdfFUploadFile();
		};
		
		document.getElementById('pdfgex').onclick = function() {
			getDownloadPdfGUploadFile();
		};
		
		document.getElementById('btn-reload').onclick = function() {
			return search_detail();
		};
		document.getElementById('btn-saveA').onclick = function() {
			return Submit_Approval_Council();
		};
		document.getElementById('btn-save').onclick = function() {
			return Reject_council();
		};
	});
	
	function getDownloadPdfUploadFile(){  
		 
		var h_id = $("#h_id").val();
		$("#course_id1").val(h_id); 
		document.getElementById("search1").submit();
	} 
	
	function getDownloadPdfBUploadFile(){  
		var b_id = $("#b_id").val();
		$("#add_stud").val(b_id); 
		document.getElementById("search2").submit();
	} 
	
	function getDownloadPdfCUploadFile(){  
		var c_id = $("#c_id").val();
		$("#inst_stud").val(c_id); 
		document.getElementById("search3").submit();
	} 

	function getDownloadPdfDUploadFile(){  
		var d_id = $("#d_id").val();
		$("#migrat_stud").val(d_id); 
		document.getElementById("search4").submit();
	}
	
	function getDownloadPdfDFromUploadFile(){  
		var e_id = $("#e_id").val();
		$("#migrat_from_stud").val(e_id); 
		document.getElementById("search5").submit();
	}
	
	function getDownloadPdfFUploadFile(){  
		var f_id = $("#f_id").val();
		$("#exam_stud").val(f_id); 
		document.getElementById("search6").submit();
	}
	
	function getDownloadPdfGUploadFile(){  
		var g_id = $("#g_id").val();
		$("#intern_stud").val(g_id); 
		document.getElementById("search7").submit();
	}
	


	function getInstitute() {
		var selval = $("#uni_id").val();0
		$.post("getInstituteUrl?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {
							var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								console.log(j[i]);
								options += '<option   value="' + j[i][3].id + '" name="'+j[i][3].id+'" >'
										+ j[i][3].institute_name + '</option>';
							}
							$("select#institute_id").html(options);
						});
	}
///////////////////////////////


//----------------------VIEW-TABLE---------------------------

function data(tablename){
	 if(tablename=="search_system_a_council"){
		 jsondata = [];
			var table = $('#' + tablename).DataTable();
			var info = table.page.info();
			var pageLength = info.length;
			var startPage = info.start;
			var endPage = info.end;
			var Search = table.search();
			var order = table.order();
			var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
			var orderType = order[0][1];
			
			var name_of_applicant_university = $("#name_of_applicant_university").val();
			var name_ug_course = $("#name_ug_course").val();
			var abbre_undergraduate_course = $("#abbre_undergraduate_course").val();
			var academic_year_applied_for = $("#academic_year_applied_for").val();
// 			var permission_from_central_gov = $("#permission_from_central_gov").val();
			var admission_intake = $("#admission_intake").val();
			var num_of_student_admitted = $("#num_of_student_admitted").val();
			var remarks=$("#remarks").val();
			var uni_id= $("#uni_id").val();
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			

			
			$.post("getFilter_UG_a?" + key + "=" + value, {
				
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				id:0,
				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
				
				
			},
				      function(j) {
				    	  console.log(j)
				    	  
				    	  if($("#institute_status").val() == "-1" || $("#institute_status").val() == "1") {
		    		   		table.column(12).visible(false);
		    	   			}
		    	   			else{
		    		   				table.column(11).visible(true);
		    	   			}
							for (var i = 0; i < j.length; i++) {
								jsondata.push([j[i].ser, //0
									j[i].university_name,//1
									j[i].institute_name,//2
									j[i].name_of_applicant_university,//3
									j[i].name_ug_course,//4
									j[i].abbre_undergraduate_course,//5
									j[i].academic_year_applied_for,//6
									j[i].admission_intake,//7
									j[i].num_of_student_admitted,//8
									j[i].remarks,//9
									j[i].vmp1,//10
									j[i].vmp2,//11
									j[i].action]);//12
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_UG_aListCount?" + key + "=" + value, {
				id:0,
				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
			}, function(j) {
				FilteredRecords = j;
				$("#acoun_hid").val(j);
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
			setTimeout(setTimeLoadForTable, 1000);

	 }
	 else if(tablename=="search_system_b_council") {
 		
		 jsondata = [];
			var table = $('#' + tablename).DataTable();
			var info = table.page.info();
			var pageLength = info.length;
			var startPage = info.start;
			var endPage = info.end;
			var Search = table.search();
			var order = table.order();
			var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
			var orderType = order[0][1];
			
			var student_name = $("#student_name").val();
			var date_of_admission = $("#date_of_admission").val();
			var neet_rank = $("#neet_rank").val();
			var rank = $("#rank").val();
			var marks = $("#marks").val();
			var all_india = $("#all_india").val();
			var state=$("#state").val();
			var management_quota=$("#management_quota").val();
			var admission_authority = $("#admission_authority").val();
			var court_order = $("#court_order").val();
			var date_of_enroll_university = $("#date_of_enroll_university").val();
			var uni_enroll_number = $("#uni_enroll_number").val();
			var date_of_intern_compl =$("#date_of_intern_compl").val();
			var remarks_form_b= $("#remarks_form_b").val();
			var uni_id= $("#uni_id").val();
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
			
			$.post("getFilter_UG_b?" + key + "=" + value, {
				
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				id:0,
				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
			},
			
				      function(j) {
				
				console.log(j)
		    	  
		    	   if($("#institute_status").val() == "-1" || $("#institute_status").val() == "1") {
		    		   table.column(15).visible(false);
		    	   }
		    	   else{
		    		   table.column(15).visible(true);
		    	   }
				
							for (var i = 0; i < j.length; i++) {
								jsondata.push([j[i].ser, //0
									j[i].inst_name,  //1
									j[i].student_name,//2
									j[i].date_of_admission,//3
									j[i].neet_rank,//4
									j[i].rank, //5
									j[i].marks,//6
									j[i].all_india,//7
									j[i].state,//8
									j[i].management_quota,//9
									j[i].admission_authority,//10
									j[i].court_order,//11
									j[i].date_of_enroll_university,//12
									j[i].uni_enroll_number, //13
									j[i].date_of_intern_compl,//14
									j[i].remarks_form_b,//15
									j[i].action]);//16
							}
 						});
 			$.ajaxSetup({
 				async : false
 			});
 			
 			$.post("getFilter_UG_bListCount?" + key + "=" + value, {
 				id:0,
 				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
 			}, function(j) {
 				FilteredRecords = j;
 				$("#bcoun_hid").val(j);
 				}).fail(function(xhr, textStatus, errorThrown, exception) {
 				  	 alert(errorThrownMsg(xhr,exception));
 			});
 			setTimeout(setTimeLoadForTableb, 1000);
 	 }
// 	 else if(tablename=="search_system_c_council") {
  		
//  		 jsondata = [];
//  			var table = $('#' + tablename).DataTable();
//  			var info = table.page.info();
//  			var pageLength = info.length;
//  			var startPage = info.start;
//  			var endPage = info.end;
//  			var Search = table.search();
//  			var order = table.order();
//  			var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
//  			var orderType = order[0][1];
 			
//  			var name_homoeopathic_medical_clg = $("#name_homoeopathic_medical_clg").val();
//  			var attached_homoeopath_hospital = $("#attached_homoeopath_hospital").val();
//  			var super_speciality_hospital = $("#super_speciality_hospital").val();
//  			var mou_date = $("#mou_date").val();
//  			var copy_of_mou = $("#copy_of_mou").val();
//  			var name_of_hospital_staff = $("#name_of_hospital_staff").val();
//  			var designation = $("#designation").val();
//  			var qualification = $("#qualification").val();
//  			var fulltime_parttime = $("#fulltime_parttime").val();
//  			var remarks_form_c = $("#remarks_form_c").val();
//  			var uni_id= $("#uni_id").val();
// 			var institute_id= $("#institute_id").val();
// 			var institute_status= $("#institute_status").val();
// 			var university_approved_status = $("#university_approved_status").val();

//  			$.post("getFilter_UG_c?" + key + "=" + value, {
 				
//  				startPage : startPage,
//  				pageLength : pageLength,
//  				Search : Search,
//  				orderColunm : orderColunm,
//  				orderType : orderType,
//  				id:0,
//  				uni_id : uni_id,
// 				institute_id : institute_id,
// 				institute_status : institute_status
//  			},
//  				      function(j) {
//  				console.log(j)
		    	  
// 		    	   if($("#institute_status").val() == "-1" || $("#institute_status").val() == "1") {
// 		    		   table.column(12).visible(false);
// 		    	   }
// 		    	   else{
// 		    		   table.column(12).visible(true);
// 		    	   }
//  							for (var i = 0; i < j.length; i++) {
//  								jsondata.push([j[i].ser, //0
//  									j[i].inst_name,//1
//  									j[i].name_homoeopathic_medical_clg,//2
//  									j[i].attached_homoeopath_hospital,//3
//  									j[i].super_speciality_hospital,//4
//  									j[i].mou_date,//5
//  									j[i].copy_of_mou,//6
//  									j[i].name_of_hospital_staff,//7
//  									j[i].designation,//8
//  									j[i].qualification,//9
//  									j[i].fulltime_parttime,//10
//  									j[i].remarks_form_c,//11
//  									j[i].action]);//12
//  							}
//  						});
//  			$.ajaxSetup({
//  				async : false
//  			});
 			
//  			$.post("getFilter_UG_cListCount?" + key + "=" + value, {
//  				id:0,
//  				uni_id : uni_id,
// 				institute_id : institute_id,
// 				institute_status : institute_status
//  			}, function(j) {
//  				FilteredRecords = j;
//  				$("#ccoun_hid").val(j);
//  				}).fail(function(xhr, textStatus, errorThrown, exception) {
//  				  	 alert(errorThrownMsg(xhr,exception));
//  			});
//  			setTimeout(setTimeLoadForTablec, 1000);
//  	 }
	 else if(tablename=="search_system_dto_council") {
		
		 jsondata = [];
			var table = $('#' + tablename).DataTable();
			var info = table.page.info();
			var pageLength = info.length;
			var startPage = info.start;
			var endPage = info.end;
			var Search = table.search();
			var order = table.order();
			var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
			var orderType = order[0][1];
			
			var name_of_inst = $("#name_of_inst").val();
			var student_name_to_migrated = $("#student_name_to_migrated").val();
			var migrated_dt_to = $("#migrated_dt_to").val();
			var professional_year_migrated = $("#professional_year_migrated").val();
			var university_enrollment_number = $("#university_enrollment_number").val();
			var remarks_form_d = $("#remarks_form_d").val();
			var uni_id= $("#uni_id").val();
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
			$.post("getFilter_UG_dto?" + key + "=" + value, {
				
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				id:0,
				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
			},
				      function(j) {
				console.log(j)
		    	  
		    	   if($("#institute_status").val() == "-1" || $("#institute_status").val() == "1") {
		    		   table.column(8).visible(false);
		    	   }
		    	   else{
		    		   table.column(8).visible(true);
		    	   }
							for (var i = 0; i < j.length; i++) {
								jsondata.push([j[i].ser, //0
									j[i].inst_name, //1
									j[i].name_of_inst,//2
									j[i].student_name_to_migrated,//3
							           j[i].migrated_dt_to,//4
							           j[i].professional_year_migrated,//5
							           j[i].university_enrollment_number,//6
							           j[i].remarks_form_d,//7
							           j[i].action]);//8
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_UG_dtoListCount?" + key + "=" + value, {
				id:0,
				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
			}, function(j) {
				FilteredRecords = j;
				$("#dtocoun_hid").val(j);
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
			setTimeout(setTimeLoadForTabledto, 1000);

	 }
	 else if(tablename=="search_system_dfrom_council") {
		 jsondata = [];
			var table = $('#' + tablename).DataTable();
			var info = table.page.info();
			var pageLength = info.length;
			var startPage = info.start;
			var endPage = info.end;
			var Search = table.search();
			var order = table.order();
			var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
			var orderType = order[0][1];
			
			var name_of_institution = $("#name_of_institution").val();
			var name_of_students_migrated = $("#name_of_students_migrated").val();
			var dt_of_migration = $("#dt_of_migration").val();
			var professional_year = $("#professional_year").val();
			var university_enroll_num = $("#university_enroll_num").val();
			var remarks_migrated = $("#remarks_migrated").val();
			var uni_id= $("#uni_id").val();
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
			$.post("getFilter_UG_dfrom?" + key + "=" + value, {
				
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				id:0,
				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
			},
				      function(j) {
				console.log(j)
		    	  
		    	   if($("#institute_status").val() == "-1" || $("#institute_status").val() == "1") {
		    		   table.column(8).visible(false);
		    	   }
		    	   else{
		    		   table.column(8).visible(true);
		    	   }
							for (var i = 0; i < j.length; i++) {
								jsondata.push([j[i].ser, //0
									j[i].inst_name,//1
									j[i].name_of_institution,//2
									j[i].name_of_students_migrated,//3
							           j[i].dt_of_migration,//4
							           j[i].professional_year,//5
							           j[i].university_enroll_num,//6
							           j[i].remarks_migrated,//7
							           j[i].action]);//8
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_UG_dfromListCount?" + key + "=" + value, {
				id:0,
				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
			}, function(j) {
				FilteredRecords = j;
				$("#dfromcoun_hid").val(j);
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
	
			setTimeout(setTimeLoadForTableefrom, 1000);

	 }
// 	 else if(tablename=="search_system_e_council") {
   		
//  		 jsondata = [];
//  			var table = $('#' + tablename).DataTable();
//  			var info = table.page.info();
//  			var pageLength = info.length;
//  			var startPage = info.start;
//  			var endPage = info.end;
//  			var Search = table.search();
//  			var order = table.order();
//  			var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
//  			var orderType = order[0][1];
 			
//  			var name_of_examiners = $("#name_of_examiners").val();
//  			var subject_examiners = $("#subject_examiners").val();
//  			var year_examiners = $("#year_examiners").val();
//  			var qualification_examiners = $("#qualification_examiners").val();
//  			var teaching_experience = $("#teaching_experience").val();
//  			var teacher_code = $("#teacher_code").val();
//  			var reg_number = $("#reg_number").val();
//  			var d_university_appointment = $("#d_university_appointment").val();
//  			var uni_id= $("#uni_id").val();
// 			var institute_id= $("#institute_id").val();
// 			var institute_status= $("#institute_status").val();
// 			var university_approved_status = $("#university_approved_status").val();
 			

//  			$.post("getFilter_UG_e?" + key + "=" + value, {
 				
//  				startPage : startPage,
//  				pageLength : pageLength,
//  				Search : Search,
//  				orderColunm : orderColunm,
//  				orderType : orderType,
//  				id:0,
//  				uni_id : uni_id,
// 				institute_id : institute_id,
// 				institute_status : institute_status
//  			},
//  				      function(j) {
//  				    	  console.log(j)
 				    	  
 				    	  
// 				    	  if($("#institute_status").val() == "-1" || $("#institute_status").val() == "1") {
// 		    		   		table.column(10).visible(false);
// 		    	   			}
// 		    	   			else{
// 		    		   				table.column(9).visible(true);
// 		    	   			}
 				    	  
//  							for (var i = 0; i < j.length; i++) {
//  								jsondata.push([j[i].ser,//0
//  									j[i].university_name,//1
//  									j[i].name_of_examiners,//2
//  									j[i].subject_examiners,//3
//  									j[i].year_examiners,//4
//  									j[i].qualification_examiners,//5
//  									j[i].teaching_experience,//6
//  									j[i].teacher_code,//7
//  									j[i].reg_number,//8
//  									j[i].d_university_appointment,//9
//  									j[i].action]);//10
//  							}
//  						});
//  			$.ajaxSetup({
//  				async : false
//  			});
 			
//  			$.post("getFilter_UG_eListCount?" + key + "=" + value, {
//  				id:0,
//  				uni_id : uni_id,
// 				institute_id : institute_id,
// 				institute_status : institute_status
//  			}, function(j) {
//  				FilteredRecords = j;
//  				$("#ecoun_hid").val(j);
//  				}).fail(function(xhr, textStatus, errorThrown, exception) {
//  				  	 alert(errorThrownMsg(xhr,exception));
//  			});
//  			setTimeout(setTimeLoadForTablef, 1000);

//  	 }
	 else if (tablename=="search_system_f_council") {
     		
		jsondata = [];
		var table = $('#' + tablename).DataTable();
		
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		
		var  name_of_students = $("#name_of_students").val();
		var year_of_admission = $("#year_of_admission").val();
		var date_of_result_final_year = $("#date_of_result_final_year").val();
		var provisional_reg_no = $("#provisional_reg_no").val();
		var year_of_provisional_reg_no = $("#year_of_provisional_reg_no").val();
		var date_of_starting_internship = $("#date_of_starting_internship").val();
		var date_of_completion_internship = $("#date_of_completion_internship").val();
		var remark_form_f = $("#remark_form_f").val();
		var uni_id= $("#uni_id").val();
		var institute_id= $("#institute_id").val();
		var institute_status= $("#institute_status").val();
		var university_approved_status = $("#university_approved_status").val();
		
		$.post("getFilter_UG_f?" + key + "=" + value , {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				id:0,
				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
			},function(j) {
				
				console.log(j)
		    	  
		    	   if($("#institute_status").val() == "-1" || $("#institute_status").val() == "1") {
		    		   table.column(10).visible(false);
		    	   }
		    	   else{
		    		   table.column(10).visible(true);
		    	   }
				
					for (var i = 0; i < j.length; i++) {
						jsondata.push([j[i].ser, //0
							j[i].inst_name,//1
							j[i].name_of_students,//2
							j[i].year_of_admission,//3
							j[i].date_of_result_final_year,//4
							j[i].provisional_reg_no,//5
							j[i].year_of_provisional_reg,//6
							j[i].date_of_starting_internship,//7
							j[i].date_of_completion_internship,//8
							j[i].remark_form_f,//9
							j[i].action]);//10
					}
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
				 });

		$.ajaxSetup({
			async : false
		});
		
		$.post("getFilter_UG_fListCount?" + key + "=" + value, {
			id:0,
			uni_id : uni_id,
			institute_id : institute_id,
			institute_status : institute_status
		}, function(j) {
			FilteredRecords = j;
			$("#fcoun_hid").val(j);
			}).fail(function(xhr, textStatus, errorThrown, exception) {
			  	 alert(errorThrownMsg(xhr,exception));
		});
		setTimeout(setTimeLoadForTableg, 1000);

	}
}

function setTimeLoadForTable(){
	document.querySelectorAll('.ADDuga').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdUga'+val).value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				viewData1(hid);
			} else {
				return false;
			}
		})
	});
	
	document.querySelectorAll('.clarificationformaData').forEach((items, index) => {
		items.addEventListener('click', event => {
			debugger;
			var val=parseInt(index)+1;
			var hid1 = document.getElementById('RejectformaId'+val).value;
				if (confirm('Are You Sure You Want to Clarified this Data ?')) {
					$('#myModal').show();
					var modal1 = document.getElementById('myModal');
					var span1 = document.getElementById('myModalClose');
					$("#rejVal").val(hid1);
					span1.onclick = function() {
					    modal1.style.display = "none";
					}
				} else {
					return false;
				}
		});
	});
	
}

//-------------POPUP viewData1--------------

function viewData1(id) {
 $.post('getviewdatauga?'+key+"="+value, {
	 id:id
	}).done(function(j) {

		    $("#h_id").val(j[0].id);
			$("span#name_of_applicant_university").text(j[0].name_of_applicant_university);
			$("span#undergraduate_course").text(j[0].undergraduate_course);
			$("span#abbre_undergraduate_course").text(j[0].abbre_undergraduate_course);
			$("span#academic_session").text(j[0].academic_session);
			$("span#name_of_college").text(j[0].name_of_college);
			$("span#country").text(j[0].country);
			$("span#state").text(j[0].state);
			$("span#district").text(j[0].district);
			$("span#city").text(j[0].city);
			$("span#postal_address").text(j[0].postal_address);
			$("span#email").text(j[0].email);
			$("span#website").text(j[0].website);
			$("span#academic_year_applied_for").text(j[0].academic_year_applied_for);
			$("span#permission_from_central_gov").text(j[0].permission_from_central_gov);
			$("span#admission_intake").text(j[0].admission_intake);
			$("span#num_of_student_admitted").text(j[0].num_of_student_admitted);
			$("span#remarks").text(j[0].remarks);
			
	});
}



function setTimeLoadForTableb(){
	document.querySelectorAll('.ADDugb').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdUgb'+val).value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				viewData2(hid);
			} else {
				return false;
			}
		})
	});
	
	document.querySelectorAll('.clarificationformbData').forEach((items, index) => {
		items.addEventListener('click', event => {
			debugger;
			var val=parseInt(index)+1;
			var hid1 = document.getElementById('RejectformbId'+val).value;
				if (confirm('Are You Sure You Want to Clarified this Data ?')) {
					//Reject_council(hid1);
					$('#myModal').show();
					var modal1 = document.getElementById('myModal');
					var span1 = document.getElementById('myModalClose');
					$("#rejVal").val(hid1);
					span1.onclick = function() {
					    modal1.style.display = "none";
					}

				} else {
					return false;
				}
		});
	});
}


//-------------POPUP viewData2--------------

function viewData2(id) {
 $.post('getviewdataugb?'+key+"="+value, {
	 id:id
	}).done(function(j) {

		    $("#b_id").val(j[0].id);
			$("span#student_name").text(j[0].student_name);
			$("span#date_of_admission").text(j[0].date_of_admission);
			$("span#neet_rank").text(j[0].neet_rank);
			$("span#rank").text(j[0].rank);
			$("span#marks").text(j[0].marks);
			$("span#all_india").text(j[0].all_india);
			$("span#state").text(j[0].state);
			$("span#management_quota").text(j[0].management_quota);
			$("span#admission_authority").text(j[0].admission_authority);
			$("span#court_order").text(j[0].court_order);
			$("span#date_of_enroll_university").text(j[0].date_of_enroll_university);
			$("span#uni_enroll_number").text(j[0].uni_enroll_number);
			$("span#date_of_intern_compl").text(j[0].date_of_intern_compl);
			$("span#remarks_form_b").text(j[0].remarks_form_b);
			
	});
}


// function setTimeLoadForTablec(){
// 	document.querySelectorAll('.ADDugc').forEach((items, index) => {
// 		items.addEventListener('click', event => {
			
// 			var val=parseInt(index)+1;
// 			var hid = document.getElementById('apIdUgc'+val).value;
// 			if (confirm('Are You Sure You Want to View Detail ?')) {
// 				viewData3(hid);
// 			} else {
// 				return false;
// 			}
// 		})
// 	});
	
// 	document.querySelectorAll('.clarificationformcData').forEach((items, index) => {
// 		items.addEventListener('click', event => {
// 			debugger;
// 			var val=parseInt(index)+1;
// 			var hid1 = document.getElementById('RejectformcId'+val).value;
// 				if (confirm('Are You Sure You Want to Clarified this Data ?')) {
// 					//Reject_council(hid1);
// 					$('#myModal').show();
// 					var modal1 = document.getElementById('myModal');
// 					var span1 = document.getElementById('myModalClose');
// 					$("#rejVal").val(hid1);
// 					span1.onclick = function() {
// 					    modal1.style.display = "none";
// 					}

// 				} else {
// 					return false;
// 				}
// 		});
// 	});
// }


//-------------POPUP viewData3--------------

function viewData3(id) {
 $.post('getviewdataugc?'+key+"="+value, {
	 id:id
	}).done(function(j) {

		    $("#c_id").val(j[0].id);
			$("span#name_homoeopathic_medical_clg").text(j[0].name_homoeopathic_medical_clg);
			$("span#attached_homoeopath_hospital").text(j[0].attached_homoeopath_hospital);
			$("span#super_speciality_hospital").text(j[0].super_speciality_hospital);
			$("span#mou_date").text(j[0].mou_date);
			$("span#copy_of_mou").text(j[0].copy_of_mou);
			$("span#name_of_hospital_staff").text(j[0].name_of_hospital_staff);
			$("span#designation").text(j[0].designation);
			$("span#qualification").text(j[0].qualification);
			$("span#fulltime_parttime").text(j[0].fulltime_parttime);
			$("span#remarks_form_c").text(j[0].remarks_form_c);
			
	});
}

function setTimeLoadForTabledto(){
	document.querySelectorAll('.ADDugd').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hidto = document.getElementById('apIdUgd'+val).value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				viewData4(hidto);
			} else {
				return false;
			}
		})
	});
	
	document.querySelectorAll('.clarificationformdData').forEach((items, index) => {
		items.addEventListener('click', event => {
			debugger;
			var val=parseInt(index)+1;
			var hid1 = document.getElementById('RejectformdId'+val).value;
				if (confirm('Are You Sure You Want to Clarified this Data ?')) {
					$('#myModal').show();
					var modal1 = document.getElementById('myModal');
					var span1 = document.getElementById('myModalClose');
					$("#rejVal").val(hid1);
					span1.onclick = function() {
					    modal1.style.display = "none";
					}

				} else {
					return false;
				}
		});
	});
}


//-------------POPUP viewData4--------------

function viewData4(id) {
 $.post('getviewdataugd?'+key+"="+value, {
	 id:id
	}).done(function(j) {
			
			$("#d_id").val(j[0].id);
			$("span#student_name_to_migrated").text(j[0].student_name_to_migrated);
			$("span#migrated_dt_to").text(j[0].migrated_dt_to);
			$("span#professional_year_migrated").text(j[0].professional_year_migrated);
			$("span#university_enrollment_number").text(j[0].university_enrollment_number);
			$("span#remarks_form_d").text(j[0].remarks_form_d);
			
	});
}

function setTimeLoadForTableefrom(){
	document.querySelectorAll('.ADDuge').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hidto = document.getElementById('apIdUge'+val).value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				viewData5(hidto);
			} else {
				return false;
			}
		})
	});
	
	document.querySelectorAll('.clarificationformdfromData').forEach((items, index) => {
		items.addEventListener('click', event => {
			debugger;
			var val=parseInt(index)+1;
			var hid1 = document.getElementById('RejectformdfromId'+val).value;
				if (confirm('Are You Sure You Want to Clarified this Data ?')) {
					$('#myModal').show();
					var modal1 = document.getElementById('myModal');
					var span1 = document.getElementById('myModalClose');
					$("#rejVal").val(hid1);
					span1.onclick = function() {
					    modal1.style.display = "none";
					}

				} else {
					return false;
				}
		});
	});
}


//-------------POPUP viewData5--------------

function viewData5(id) {
 $.post('getviewdatauge?'+key+"="+value, {
	 id:id
	}).done(function(j) {
			
			$("#e_id").val(j[0].id);
			$("span#name_of_students_migrated").text(j[0].name_of_students_migrated);
			$("span#dt_of_migration").text(j[0].dt_of_migration);
			$("span#professional_year").text(j[0].professional_year);
			$("span#university_enroll_num").text(j[0].university_enroll_num);
			$("span#remarks_migrated").text(j[0].remarks_migrated);
			
	});
}


// function setTimeLoadForTablef(){
// 	document.querySelectorAll('.ADDugf').forEach((items, index) => {
// 		items.addEventListener('click', event => {
			
// 			var val=parseInt(index)+1;
// 			var hid = document.getElementById('apIdUgf'+val).value;
// 			if (confirm('Are You Sure You Want to View Detail ?')) {
// 				viewData6(hid);
// 			} else {
// 				return false;
// 			}
// 		})
// 	});
	
// 	document.querySelectorAll('.clarificationformeData').forEach((items, index) => {
// 		items.addEventListener('click', event => {
// 			debugger;
// 			var val=parseInt(index)+1;
// 			var hid1 = document.getElementById('RejectformeId'+val).value;
// 				if (confirm('Are You Sure You Want to Clarified this Data ?')) {
// 					$('#myModal').show();
// 					var modal1 = document.getElementById('myModal');
// 					var span1 = document.getElementById('myModalClose');
// 					$("#rejVal").val(hid1);
// 					span1.onclick = function() {
// 					    modal1.style.display = "none";
// 					}

// 				} else {
// 					return false;
// 				}
// 		});
// 	});
// }


//-------------POPUP viewData6--------------

function viewData6(id) {
 $.post('getviewdataugf?'+key+"="+value, {
	 id:id
	}).done(function(j) {
			
			$("#f_id").val(j[0].id);
			$("span#name_of_examiners").text(j[0].name_of_examiners);
			$("span#subject_examiners").text(j[0].subject_examiners);
			$("span#year_examiners").text(j[0].year_examiners);
			$("span#qualification_examiners").text(j[0].qualification_examiners);
			$("span#teaching_experience").text(j[0].teaching_experience);
			$("span#teacher_code").text(j[0].teacher_code);
			$("span#reg_number").text(j[0].reg_number);
			$("span#d_university_appointment").text(j[0].d_university_appointment);
			
	});
}

function setTimeLoadForTableg(){
	document.querySelectorAll('.ADDugg').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdUgg'+val).value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				viewData7(hid);
			} else {
				return false;
			}
		})
	});
	
	document.querySelectorAll('.clarificationformfData').forEach((items, index) => {
		items.addEventListener('click', event => {
			debugger;
			var val=parseInt(index)+1;
			var hid1 = document.getElementById('RejectformfId'+val).value;
				if (confirm('Are You Sure You Want to Clarified this Data ?')) {
					$('#myModal').show();
					var modal1 = document.getElementById('myModal');
					var span1 = document.getElementById('myModalClose');
					$("#rejVal").val(hid1);
					span1.onclick = function() {
					    modal1.style.display = "none";
					}

				} else {
					return false;
				}
		});
	});
	
}


//-------------POPUP viewData6--------------

function viewData7(id) {
 $.post('getviewdataugg?'+key+"="+value, {
	 id:id
	}).done(function(j) {
			
			$("#g_id").val(j[0].id);
			$("span#name_of_students").text(j[0].name_of_students);
			$("span#year_of_admission").text(j[0].year_of_admission);
			$("span#date_of_result_final_year").text(j[0].date_of_result_final_year);
			$("span#provisional_reg_no").text(j[0].provisional_reg_no);
			$("span#year_of_provisional_reg").text(j[0].year_of_provisional_reg);
			$("span#date_of_starting_internship").text(j[0].date_of_starting_internship);
			$("span#date_of_completion_internship").text(j[0].date_of_completion_internship);
			$("span#remark_form_f").text(j[0].remark_form_f);
			
	});
}

function Reject_council() {
	     if($("input#reject_remarks").val() == "") {
	       alert("Please Enter Clarification Of Data ");
	       $("input#reject_remarks").focus();
	       return false;
		  }
	 
		 $.post("reject_action_council?"+key+"="+value,{
			
			id : $("#rejVal").val(),
			reject_remarks : $("#reject_remarks").val()
			
		}).done(function(j) {
			
			alert(j);
			$("#modal-clos-btn").click();
			location.reload();
			
		}).fail(function(xhr, textStatus, errorThrown) {
      		alert("fail to fetch")
		});
}

function Submit_Approval_Council(){
	var acoun_hid = $("#acoun_hid").val();
 	var bcoun_hid = $("#bcoun_hid").val();
//  	var ccoun_hid = $("#ccoun_hid").val();
 	var dtocoun_hid = $("#dtocoun_hid").val();
 	var dfromcoun_hid = $("#dfromcoun_hid").val();
//  	var ecoun_hid = $("#ecoun_hid").val();
 	var fcoun_hid = $("#fcoun_hid").val();
 	
		$.post("Final_Submit_Council?" +  key + "=" + value,{acoun_hid:acoun_hid,bcoun_hid:bcoun_hid,
			dtocoun_hid:dtocoun_hid,dfromcoun_hid:dfromcoun_hid,fcoun_hid:fcoun_hid},
				function(data) {
			alert(data);
			location.reload();
	}).fail(function(xhr, textStatus, errorThrown) {
     alert("fail to fetch")
    });
}




</script>
