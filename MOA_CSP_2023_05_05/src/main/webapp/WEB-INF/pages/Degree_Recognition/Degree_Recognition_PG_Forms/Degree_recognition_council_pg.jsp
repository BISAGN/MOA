<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
	
	
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script> -->
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script> -->


<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

<!-- datatable style and js end-->
<link rel="stylesheet"
	href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/excel/xlsx-0.7.7.core.min.js"></script>  
<script src="js/excel/xls-0.7.4.core.min.js"></script> 

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
							<span id="lbladd">View PG Details</span>
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
						 
						 <div class="tab tablinks dg-rec-block" id="tab_id7">
								<button class="tab-toggle">First Choose The University
									and Institute</button>
						 </div>
						<div class="content tabcontent" id="Form_z">
								<h4 class="heading">Choose The University and Institute</h4>
								<div class="inst_block mb-10">
									<h6 class="mb-2">Instruction</h6>
									<ul class="inst_list">
										<li><p class="inst_text">Press last tab to Approve
												all the form details</p></li>
									</ul>
								</div>
								<div class="row">
									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="select-style-1">
											<label>Select University<strong class="mandatory">*</strong></label>
											<div class="select-position">
												<select name="uni_id" id="uni_id" class="singleselect form-control form-control-lg">
													<option value="0">---Select University---</option>
													<c:forEach var="item" items="${getMedUniversityName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
													class="singleselect form-control form-control-lg">
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
								
						 <!-- Start Forms  -->
						 										<!-- Form A UG -->
						 	
						 	<div class="tab tablinks" id="tab_id1">
								<button class="tab-toggle">Postgraduate Course In Homoeopathy In India</button>
							</div>  
							 <div class="content tabcontent" id="Form_A">
									<h4 class="heading">Postgraduate Course In Homoeopathy In India</h4>
									<div class="row">
										<div class="col-12">
											<div class="card-style mb-30">
												<div class="table-wrapper custom-datatable-p">
                  									<table class="table" id="search_system_a_council_pg">
                      									<thead>
                       										 <tr>
										                        <th><h6>Ser No</h6></th>
										                        <th><h6>University</h6></th>
																<th><h6>Applicant University Granting Qualification</h6></th>
														        <th><h6>Full Name Of The Postgraduate Course</h6></th>
														        <th><h6>Abbreviation of Postgraduate Course</h6></th>
														        <th><h6>Application For Admitted Academic Session/Batch</h6></th>
														        <th><h6>Name Of College</h6></th>
														        <th><h6>Country</h6></th>
										 				        <th><h6>State</h6></th>
														        <th><h6>District</h6></th>
														        <th><h6>City</h6></th>
														        <th><h6>Postal Address</h6></th>
														        <th><h6>Email</h6></th>
														        <th><h6>Website</h6></th>
														        <th><h6>Academic Year Applied for</h6></th>
														        <th><h6>Permission from Central Government/Court-Order</h6></th>
														        <th><h6>Admission Intake Capacity permitted</h6></th>
														        <th><h6>Number of Student Admitted</h6></th>
														        <th><h6>Remarks</h6></th>
														        <th id="hid_aview"><h6>View</h6></th>
														        <th id="hid_arej"><h6>Reject</h6></th>
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
																
							<div class="tab tablinks" id="tab_id2">
								<button class="tab-toggle">Students Admitted As regular Candidate(Year And Subject Wise) For M.D.(HOM)</button>
							</div>  
							 <div class="content tabcontent" id="Form_B">
									<h4 class="heading">Students Admitted As regular Candidate(Year And Subject Wise) For M.D.(HOM)</h4>
									<div class="row">
										<div class="col-12">
											<div class="card-style mb-30">
												<div class="table-wrapper custom-datatable-p">
                  									<table class="table" id="search_system_b_council_pg">
                      									<thead>
                       										 <tr>
										                        <th><h6>Ser No</h6></th>
										                        <th><h6>Institute Name</h6></th>
																<th><h6>Student Name</h6></th>
														        <th><h6>Date Of Admission</h6></th>
														        <th><h6>Date Of Registration</h6></th>
														        <th><h6>University For The Course</h6></th>
														        <th><h6>Rank</h6></th>
														        <th><h6>Marks</h6></th>
														        <th><h6>All India</h6></th>
														        <th><h6>State</h6></th>
										 				        <th><h6>Admission Authority</h6></th>
														        <th><h6>CourtOrder & Others</h6></th>
														        <th><h6>Qualification Name</h6></th>
														        <th><h6>Year Of Award For Admission To M.D.(Hom.)</h6></th>
														        <th><h6>Date Of Completion Of Internship</h6></th>
														        <th><h6>Date Of Registration With State</h6></th>
														        <th><h6>Date Of Completion Of MD Part-1</h6></th>
														        <th><h6>Date Of Completion Of MD Part-2</h6></th>
														        <th><h6>Date Of Declaration Of MD(Hom.)Part-2</h6></th>
														        <th><h6>Remarks</h6></th>
														        <th id="hid_bview"><h6>View</h6></th>
														        <th id="hid_brej"><h6>Reject</h6></th>
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
							
							<div class="tab tablinks" id="tab_id3">
								<button class="tab-toggle">Teaching Staff For PostGraduate Course</button>
							</div>  
							 <div class="content tabcontent" id="Form_A">
									<h4 class="heading">Teaching Staff For PostGraduate Course</h4>
									<div class="row">
										<div class="col-12">
											<div class="card-style mb-30">
												<div class="table-wrapper custom-datatable-p">
                  									<table class="table" id="search_system_c_council_pg">
                      									<thead>
                       										 <tr>
										                        <th><h6>Ser No</h6></th>
										                        <th><h6>University</h6></th>
										                        <th><h6>College/Center/Medical institute</h6></th>
														        <th><h6>Name Of Teaching Staff</h6></th>
														        <th><h6>Phone</h6></th>				
														        <th><h6>Email ID</h6></th>
														        <th><h6>Designation</h6></th>
														        <th><h6>Department</h6></th>
														        <th><h6>Registration No</h6></th>
														        <th><h6>Date Of Registration</h6></th>
														        <th><h6>Date Of Birthday</h6></th>
														        <th><h6>Qualification Awarding Authority</h6></th>
														        <th><h6>Year Of Award</h6></th>
														        <th><h6>Date Of Appointment</h6></th>
														        <th><h6>Full Time/Part Time</h6></th>
														        <th><h6>post</h6></th>
														        <th><h6>Experience From</h6></th>
														        <th><h6>Experience To</h6></th>
														        <th><h6>Total Teaching Experience In Years</h6></th>
														        <th id="hid_cview"><h6>View</h6></th>
														        <th id="hid_crej"><h6>Reject</h6></th>
                       								 		</tr>
                      									</thead>
                      					 			<tbody class="custom-datatablepra"></tbody>
                   							 		</table>
                  								</div>	
                  							</div>
                  						</div>
                  					</div>
							</div>
																		<!-- Form D Institute -->
																
							<div class="tab tablinks" id="tab_id4">
								<button class="tab-toggle">Details Of Guides And Examiners For M.D.(HOM) Course/Any Other Post Graduate Course</button>
							</div>  
							 <div class="content tabcontent" id="Form_D">
									<h4 class="heading">Details Of Guides And Examiners For M.D.(HOM) Course/Any Other Post Graduate Course</h4>
									<div class="row">
										<div class="col-12">
											<div class="card-style mb-30">
												<div class="table-wrapper custom-datatable-p">
                  									<table class="table" id="search_system_d_council_pg">
                      									<thead>
                       										 <tr>
										                        <th><h6>Ser No</h6></th>
										                         <th><h6>Institute Name</h6></th>
																<th><h6>Name Of Homoeopathic Medical College</h6></th>
														        <th><h6>Name Of Guide</h6></th>
														        <th><h6>Name Of Students</h6></th>
														        <th><h6>Topic Of Dissertation</h6></th>
														        <th><h6>Whether Guide Participated In Examination</h6></th>
														        <th><h6>Date Of Submission</h6></th>
														        <th><h6>Date Of Acceptance</h6></th>
														        <th><h6>Whether Student published Article From Dissertation/h6></th>
														        <th><h6>If Yes, Mention The Details</h6></th>
														        <th><h6>Article Title</h6></th>
														        <th><h6>Month And Year</h6></th>
														        <th id="hid_dview"><h6>View</h6></th>
														        <th id="hid_drej"><h6>Reject</h6></th>
                       								 		</tr>
                      									</thead>
                      					 			<tbody class="custom-datatablepra"></tbody>
                   							 		</table>
                  								</div>	
                  							</div>
                  						</div>
                  					</div>
							</div>									
																	<!-- Form E Institute -->
																
							<div class="tab tablinks" id="tab_id5">
								<button class="tab-toggle">Work Done by Post Graduate Students</button>
							</div>  
							 <div id="Form_3" class="content tabcontent custom-htab-acco">
								<div class="">
									<ul id="tabs" class="nav nav-tabs" role="tablist">
										<li class="nav-item" id="tab_id5">
										<a id="Form_3_a" href="#form-A" class="nav-link active" data-toggle="tab"
											role="tab">A.Disseration Topic</a>
										</li>
										<li class="nav-item" id="tab_id5" >
											<a id="Form_3_b" href="#form-B" class="nav-link"
											data-toggle="tab" role="tab">B.Lacture taken</a>
										</li>
										
										<li class="nav-item" id="tab_id5">
											<a id="Form_3_c" href="#form-C" class="nav-link"
											data-toggle="tab" role="tab">C.Assignment</a>
										</li>
										
										<li class="nav-item" id="tab_id5">
											<a id="Form_3_d" href="#form-D" class="nav-link"
											data-toggle="tab" role="tab">D.Persentations given</a>
										</li>
									</ul>

									

						<div id="content" class="tab-content" role="tablist">
						
																	<!-- Form PG(3)(a) -->
						<div id="form-A" class="tab-pane fade show active" role="tabpanel" aria-labelledby="Form_3_a">
								<div id="collapse-A" class="collapse show" data-parent="#content" role="tabpanel" aria-labelledby="heading-A">
									<div id="Form_3_a">
										<form id="Form_PG3a_details">
											<h4 class="heading mt-3">Disseratation Topic of P.G. Student-Year wise/Depratment wise</h4>
												<div class="tables-wrapper">
													<div class="row">
														<div class="col-lg-12">
                											<div class="card-style mb-30">
																<div class="table-wrapper custom-datatable-p">
																	<table class="table" id="search_system_disseration_pg_ea">
																		<thead>
																			<tr>
																				<th rowspan="2"><h6>Ser No</h6></th>
																				<th rowspan="2"><h6>Institute Name</h6></th>
																				<th rowspan="2"><h6>Name of student</h6></th>
																				<th colspan="2"><h6>Duration of house job Dates</h6></th>
																				<th rowspan="2"><h6>Name of Topic</h6></th>
																				<th rowspan="2"><h6>Outcomes/Result/Conclusion obtained from disseration</h6></th>
																				<th rowspan="2"><h6>Date of submission</h6></th>
																				<th rowspan="2"><h6>Any publication from Disseration(Yes/No)</h6></th>
																				<th rowspan="2"><h6>If yes,mention(article title/journal)</h6></th>
																				<th id="hid_eaview"><h6>View</h6></th>
														        				<th id="hid_earej"><h6>Reject</h6></th>
																			</tr>
																			<tr>
																				<th><h6>From date</h6></th>
																				<th><h6>To date</h6></th>
																			</tr>
																		</thead>
																	</table><!-- end table -->
																</div>
															</div>
														</div>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div><!-- End Tab Form A -->	
																		
																<!-- Form PG(3)(B) -->
						<div id="form-B" class="tab-pane fade show active" role="tabpanel" aria-labelledby="Form_3_b">
								<div id="collapse-A" class="collapse show" data-parent="#content" role="tabpanel" aria-labelledby="heading-A">
									<div id="Form_3_b">
										<form id="Form_PG3a_details">
											<h4 class="heading mt-3">Lecture Taken by P.G. Students,Batch</h4>
												<div class="tables-wrapper">
													<div class="row">
														<div class="col-lg-12">
                											<div class="card-style mb-30">
																<div class="table-wrapper custom-datatable-p">
																	<table class="table" id="search_system_disseration_pg_eb">
																		<thead>
																			<tr>
																				<th><h6>Ser No</h6></th>
																				<th><h6>Institute Name</h6></th>
																				<th><h6>Name of student</h6></th>
																				<th><h6>Class</h6></th>
																				<th><h6>Date</h6></th>
																				<th><h6>Day</h6></th>
																				<th><h6>Time</h6></th>
																				<th><h6>Topic</h6></th>
																				<th id="hid_ebview"><h6>View</h6></th>
														        				<th id="hid_ebrej"><h6>Reject</h6></th>
																			</tr>
																			
																		</thead>
																	</table><!-- end table -->
																</div>
															</div>
														</div>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div><!-- End Tab Form B -->	
																<!-- Form PG(3)(C) -->
						<div id="form-C" class="tab-pane fade show active" role="tabpanel" aria-labelledby="Form_3_c">
								<div id="collapse-A" class="collapse show" data-parent="#content" role="tabpanel" aria-labelledby="heading-A">
									<div id="Form_3_c">
										<form id="Form_PG3c_details">
											<h4 class="heading mt-3">List Of Assignment P.G. Students</h4>
												<div class="tables-wrapper">
													<div class="row">
														<div class="col-lg-12">
                											<div class="card-style mb-30">
																<div class="table-wrapper custom-datatable-p">
																	<table class="table" id="search_system_disseration_pg_ec">
																		<thead>
																			<tr>
																				<th><h6>Ser No</h6></th>
																				<th><h6>Institute Name</h6></th>
																				<th><h6>Name Of Students</h6></th>
																				<th><h6>List Of Assignment</h6></th>
																				<th id="hid_ecview"><h6>View</h6></th>
														        				<th id="hid_ecrej"><h6>Reject</h6></th>
																			</tr>
																			
																		</thead>
																	</table><!-- end table -->
																</div>
															</div>
														</div>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div><!-- End Tab Form B -->	
																<!-- Form PG(3)(D) -->
						<div id="form-D" class="tab-pane fade show active" role="tabpanel" aria-labelledby="Form_3_d">
								<div id="collapse-A" class="collapse show" data-parent="#content" role="tabpanel" aria-labelledby="heading-A">
									<div id="Form_3_d">
										<form id="Form_PG3d_details">
											<h4 class="heading mt-3">List Of Presentation Seminar Attended By P.G. Students</h4>
												<div class="tables-wrapper">
													<div class="row">
														<div class="col-lg-12">
                											<div class="card-style mb-30">
																<div class="table-wrapper custom-datatable-p">
																	<table class="table" id="search_system_disseration_pg_ed">
																		<thead>
																			<tr>
																				<th><h6>Ser No</h6></th>
																				<th><h6>Institute Name</h6></th>
																				<th><h6>Name Of Students</h6></th>
																				<th><h6>List Of Presentation</h6></th>
																				<th><h6>Title Of The Seminar Attended</h6></th>
																				<th id="hid_edview"><h6>View</h6></th>
														        				<th id="hid_edrej"><h6>Reject</h6></th>
																			</tr>
																			
																		</thead>
																	</table><!-- end table -->
																</div>
															</div>
														</div>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div><!-- End Tab Form D -->	
						</div><!-- End Tablist -->
								
							</div><!-- End Tab Forms Div Class -->
						</div> <!-- End Tab Forms  -->					
														<!-- Form F UG -->
							
							
							<div class="tab tablinks" id="tab_id6">
								<button class="tab-toggle">Examiners Appointed in For Examination</button>
							</div>  
							 <div class="content tabcontent" id="Form_A">
									<h4 class="heading">Examiners Appointed in For Examination</h4>
									<div class="row">
										<div class="col-12">
											<div class="card-style mb-30">
												<div class="table-wrapper custom-datatable-p">
                  									<table class="table" id="search_system_f_council_pg">
                      									<thead>
                       										 <tr>
										                        <th><h6>Ser No</h6></th>
										                        <th><h6>University</h6></th>
										                        <th><h6>Subject</h6></th>
															    <th><h6>Name Of Examiner</h6></th>
															    <th><h6>Date Of Examiners</h6>
														        <th id="hid_fview"><h6>View</h6></th>
														        <th id="hid_frej"><h6>Reject</h6></th>
                       								 		</tr>
                      									</thead>
                      					 			<tbody class="custom-datatablepra"></tbody>
                   							 		</table>
                  								</div>	
                  							</div>
                  						</div>
                  						 <div class="col-lg-12 col-md-12 col-sm-12">
											<ul class="buttons-group mainbtn">
												<li><input type="button" id="btn-save" class="main-btn success-btn  btn-hover" 
												value="Submit For Approval"></li>
											</ul>
									   </div>	
                  					</div>
							</div>
							
													<!-- End Forms -->
							
						</div><!-- End tabcontent -->
					</div><!-- End Card style 12 -->
				</div><!-- End col log 12 -->
			</div><!--  End row -->
		</div><!-- End Custom V tab -->
		
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
			<li><input type="button" id="btn-reject" class="main-btn success-btn  btn-hover" value="Submit Clarification">
				<button type="button" id="myModalClose" class="btn btn-danger" data-bs-dismiss="modal" id="modal-clos-btn">Close</button>
				    
				     <input type="hidden" id="rejVal" name="rejVal">
			</li>
		</ul>
    </div>
    </div>
  </div>
</div>

	</div><!-- End container-fluid -->
</section>

													<!--  Model A PG -->
<!--  modal start -->
<div class="modal fade custom-modal" id="pgamodal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="staticBackdropLabel">View Data</h3>
        <button type="button" class="btn-close" id="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="custom-modal-inner" id="headData">
        <div class="row">
        	<input type="hidden" id="h_id" name="h_id">
             <div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Applicant University Granting Qualification :</label> 
					<span class="value-bind" id="name_of_applicant_university"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Full Name Of The Postgraduate Course :</label> 
					<span class="value-bind" id="postgraduate_course"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Abbreviation of Postgraduate Course:</label> 
					<span class="value-bind" id="abbre_postgraduate_course"></span>
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
					<label for="">College/Center/Medical institute :</label> 
					<span class="value-bind" id="name_of_college"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Country :</label> 
					<span class="value-bind" id="country"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">State :</label> 
					<span class="value-bind" id="state"></span>
				</div>
			</div>
			
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">District:</label> 
					<span class="value-bind" id="district"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">City :</label> 
					<span class="value-bind" id="city"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Postal Address :</label> 
					<span class="value-bind" id="postal_address"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Email :</label> 
					<span class="value-bind" id="email"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Website :</label> 
					<span class="value-bind" id="website"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Academic Year Applied for :</label> 
					<span class="value-bind" id="academic_year_applied_for"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Permission from Central Government :</label> 
					<span class="value-bind" id="permission_from_central_gov"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Admission Intake Capacity permitted :</label> 
					<span class="value-bind" id="admission_intake"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Number of Student Admitted :</label> 
					<span class="value-bind" id="num_of_student_admitted"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Remarks :</label> 
					<span class="value-bind" id="remarks"></span>
				</div>
			</div>
			
	    </div>       
				<div class="modal-footer">
					<ul class="buttons-group">
						<li><button type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
								data-dismiss="modal" aria-label="Close">Close</button></li>
								
							<li><a href="#0" class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" id="pdfex">
							 <i id="printId" value="PDF" title="Export to PDF"><i class="bi bi-file-pdf"></i></i>Download Print
							</a></li>
					</ul>
				</div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- modal end -->

													<!--  Model B PG -->
<!--  modal start -->
<div class="modal fade custom-modal" id="pgbmodal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="staticBackdropLabel">View Data</h3>
        <button type="button" class="btn-close" id="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="custom-modal-inner" id="headData">
        <div class="row">
        <input type="hidden" id="b_id" name="b_id">
             <div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Name Of Student Name :</label> 
					<span class="value-bind" id="student_name"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Date Of Admission :</label> 
					<span class="value-bind" id="date_of_admission"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Date Of Registration:</label> 
					<span class="value-bind" id="date_of_registration"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">University For Course Name:</label> 
					<span class="value-bind" id="course_name"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Rank :</label> 
					<span class="value-bind" id="rank"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Mark :</label> 
					<span class="value-bind" id="marks"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">All India :</label> 
					<span class="value-bind" id="all_india"></span>
				</div>
			</div>
			
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">State:</label> 
					<span class="value-bind" id="state"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Admission Authority :</label> 
					<span class="value-bind" id="admission_authority"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Court Order :</label> 
					<span class="value-bind" id="court_order"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Qualification Name :</label> 
					<span class="value-bind" id="qualification_name"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Year Of Award Admission :</label> 
					<span class="value-bind" id="year_of_award_admission"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Date Of Registration State :</label> 
					<span class="value-bind" id="date_of_registration_state"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Date Of Completion MD Pat-1 :</label> 
					<span class="value-bind" id="date_of_completion_md_part1"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Date Of Completion MD Pat-2 :</label> 
					<span class="value-bind" id="date_of_completion_md_part2"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Date Of Declaration Of MD</label> 
					<span class="value-bind" id="date_of_declaration_of_md"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Date Of Completion Internship</label> 
					<span class="value-bind" id="date_of_completion_internship"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Remarks :</label> 
					<span class="value-bind" id="remarks"></span>
				</div>
			</div>
			
	    </div>       
				<div class="modal-footer">
					<ul class="buttons-group">
						<li><button type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
								data-dismiss="modal" aria-label="Close">Close</button></li>
								
			<li><a href="#0" class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" id="pdfbex"> 
			<i id="printId" value="PDF"	title="Export to PDF"><i class="bi bi-file-pdf"></i></i>Download Print</a></li>
					</ul>
				</div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- modal end -->
										<!--  Model C PG -->
<!--  modal start -->
<div class="modal fade custom-modal" id="pgcmodel" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="staticBackdropLabel">View Data</h3>
        <button type="button" class="btn-close" id="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="custom-modal-inner" id="headData">
        <div class="row">
         <input type="hidden" id="c_id" name="c_id">
             <div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">College/Center/Medical institute :</label> 
					<span class="value-bind" id="name_of_college_pg"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Name Of Teaching Staff :</label> 
					<span class="value-bind" id="name_of_teaching_staff"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Phone Number:</label> 
					<span class="value-bind" id="phone"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Email ID:</label> 
					<span class="value-bind" id="email_id"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Designation :</label> 
					<span class="value-bind" id="designation"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Department :</label> 
					<span class="value-bind" id="department"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Registration No. :</label> 
					<span class="value-bind" id="registration_no"></span>
				</div>
			</div>
			
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Date Of Registration:</label> 
					<span class="value-bind" id="date_of_reg"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Date Of Birthday :</label> 
					<span class="value-bind" id="date_of_birth"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Qualification Awarding Authority :</label> 
					<span class="value-bind" id="qualification_awarding_authority"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Year Of Award :</label> 
					<span class="value-bind" id="year_of_award"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Date Of Appointment :</label> 
					<span class="value-bind" id="date_of_appointment"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Full Time/Part Time :</label> 
					<span class="value-bind" id="fulltime_parttime"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">post :</label> 
					<span class="value-bind" id="post"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Experience From :</label> 
					<span class="value-bind" id="exp_from"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Experience To :</label> 
					<span class="value-bind" id="exp_to"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Total Teaching Experience In Years :</label> 
					<span class="value-bind" id="total_teaching_exp_in_year"></span>
				</div>
			</div>
			
	    </div>       
				<div class="modal-footer">
					<ul class="buttons-group">
						<li><button type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
								data-dismiss="modal" aria-label="Close">Close</button></li>
					<li><a href="#0" class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" id="pdfcex"> 
			<i id="printId" value="PDF"	title="Export to PDF"><i class="bi bi-file-pdf"></i></i>Download Print</a></li>
			
					</ul>
				</div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- modal end -->
											<!--  Model D PG -->
<!--  modal start -->
<div class="modal fade custom-modal" id="pgdmodal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="staticBackdropLabel">View Data</h3>
        <button type="button" class="btn-close" id="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="custom-modal-inner" id="headData">
        <div class="row">
        <input type="hidden" id="d_id" name="d_id">
             <div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Name Of Homoeopathic Medical College :</label> 
					<span class="value-bind" id="name_of_homoeopathic_medical_college"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Name Of Guide :</label> 
					<span class="value-bind" id="name_of_guide"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Name Of Students For Guide:</label> 
					<span class="value-bind" id="name_of_student_for_guide"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Topic Of Dissertation:</label> 
					<span class="value-bind" id="topic_of_dissertation"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Whether Guide Participated In Examination :</label> 
					<span class="value-bind" id="whether_guide_participated_in_examination"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Date Of Submission :</label> 
					<span class="value-bind" id="date_of_submission"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Date Of Acceptance :</label> 
					<span class="value-bind" id="date_of_acceptance"></span>
				</div>
			</div>
			
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Whether Student Published Article:</label> 
					<span class="value-bind" id="whether_student_published_article"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Mention Details :</label> 
					<span class="value-bind" id="mention_details"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Article Title :</label> 
					<span class="value-bind" id="article_title"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Month Year Exam :</label> 
					<span class="value-bind" id="month_year_exam"></span>
				</div>
			</div>
	    </div>       
				<div class="modal-footer">
					<ul class="buttons-group">
						<li><button type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
								data-dismiss="modal" aria-label="Close">Close</button></li>
					
					<li><a href="#0" class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" id="pdfdex"> 
			<i id="printId" value="PDF"	title="Export to PDF"><i class="bi bi-file-pdf"></i></i>Download Print</a></li>
			
					</ul>
				</div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- modal end -->

												<!--  Model E-A PG -->
<!--  modal start -->
<div class="modal fade custom-modal" id="pgeamodal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="staticBackdropLabel">View Data</h3>
        <button type="button" class="btn-close" id="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="custom-modal-inner" id="headData">
        <div class="row">
               <input type="hidden" id="e_a_id" name="e_a_id">
       
             <div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Name Of Student :</label> 
					<span class="value-bind" id="student_name_pg"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Duration Of House Job From :</label> 
					<span class="value-bind" id="from_date"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Duration Of House Job To :</label> 
					<span class="value-bind" id="to_date"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Name Of Topic :</label> 
					<span class="value-bind" id="name_of_topic"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Conclusion Obtained :</label> 
					<span class="value-bind" id="conclusion_obtain"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Date Of Submission :</label> 
					<span class="value-bind" id="date_of_submission_pg"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Publication From :</label> 
					<span class="value-bind" id="publication"></span>
				</div>
			</div>
			
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Mention Article:</label> 
					<span class="value-bind" id="mention_article"></span>
				</div>
			</div>
				<div class="modal-footer">
					<ul class="buttons-group">
						<li><button type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
								data-dismiss="modal" aria-label="Close">Close</button></li>
								
					<li><a href="#0" class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" id="pdfeaex"> 
			<i id="printId" value="PDF"	title="Export to PDF"><i class="bi bi-file-pdf"></i></i>Download Print</a></li>
					</ul>
				</div>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
<!-- modal end -->
												<!--  Model E-B PG -->
<!--  modal start -->
<div class="modal fade custom-modal" id="pgebmodal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="staticBackdropLabel">View Data</h3>
        <button type="button" class="btn-close" id="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="custom-modal-inner" id="headData">
        <div class="row">
               <input type="hidden" id="e_b_id" name="e_b_id">
       
             <div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Name Of Student :</label> 
					<span class="value-bind" id="student_name"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Class :</label> 
					<span class="value-bind" id="student_class"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Date :</label> 
					<span class="value-bind" id="lecture_date"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Day :</label> 
					<span class="value-bind" id="lecture_day"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Time :</label> 
					<span class="value-bind" id="lecture_time"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Topic :</label> 
					<span class="value-bind" id="topic"></span>
				</div>
			</div>
				<div class="modal-footer">
					<ul class="buttons-group">
						<li><button type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
								data-dismiss="modal" aria-label="Close">Close</button></li>
						<li><a href="#0" class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" id="pdfebex"> 
			<i id="printId" value="PDF"	title="Export to PDF"><i class="bi bi-file-pdf"></i></i>Download Print</a></li>
					</ul>
				</div>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
<!-- modal end -->
									<!--  Model E-C PG -->
<!--  modal start -->
<div class="modal fade custom-modal" id="pgecmodal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="staticBackdropLabel">View Data</h3>
        <button type="button" class="btn-close" id="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="custom-modal-inner" id="headData">
        <div class="row">
                      <input type="hidden" id="e_c_id" name="e_c_id">
       
             <div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Name Of Student :</label> 
					<span class="value-bind" id="student_name_formc"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">List Of Assignment :</label> 
					<span class="value-bind" id="list_of_assignment_formc"></span>
				</div>
			</div>
				<div class="modal-footer">
					<ul class="buttons-group">
						<li><button type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
								data-dismiss="modal" aria-label="Close">Close</button></li>
								
					<li><a href="#0" class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" id="pdfecex"> 
			<i id="printId" value="PDF"	title="Export to PDF"><i class="bi bi-file-pdf"></i></i>Download Print</a></li>
					</ul>
				</div>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
<!-- modal end -->
												<!--  Model E-D PG -->
<!--  modal start -->
<div class="modal fade custom-modal" id="pgedmodal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="staticBackdropLabel">View Data</h3>
        <button type="button" class="btn-close" id="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="custom-modal-inner" id="headData">
        <div class="row">
                      <input type="hidden" id="e_d_id" name="e_d_id">
       
             <div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Name Of Student :</label> 
					<span class="value-bind" id="student_name_presen"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">List Of Presentation :</label> 
					<span class="value-bind" id="list_of_presentations"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Title Of Seminar Attended :</label> 
					<span class="value-bind" id="title_of_seminar_attended"></span>
				</div>
			</div>
				<div class="modal-footer">
					<ul class="buttons-group">
						<li><button type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
								data-dismiss="modal" aria-label="Close">Close</button></li>
						<li><a href="#0" class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" id="pdfedex"> 
			<i id="printId" value="PDF"	title="Export to PDF"><i class="bi bi-file-pdf"></i></i>Download Print</a></li>
					</ul>
				</div>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
<!-- modal end -->

										<!--  Model F PG -->
<!--  modal start -->
<div class="modal fade custom-modal" id="pgfmodel" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="staticBackdropLabel">View Data</h3>
        <button type="button" class="btn-close" id="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="custom-modal-inner" id="headData">
        <div class="row">
                       <input type="hidden" id="f_id" name="f_id">
        
             <div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Subject :</label> 
					<span class="value-bind" id="subject"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Name Of Examiner :</label> 
					<span class="value-bind" id="name_of_examiners"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Date Of Examination:</label> 
					<span class="value-bind" id="date_of_examination"></span>
				</div>
			</div>
	    </div>       
				<div class="modal-footer">
					<ul class="buttons-group">
						<li><button type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
								data-dismiss="modal" aria-label="Close">Close</button></li>
						<li><a href="#0" class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" id="pdffex"> 
			<i id="printId" value="PDF"	title="Export to PDF"><i class="bi bi-file-pdf"></i></i>Download Print</a></li>
					</ul>
				</div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- modal end -->
<script type="text/javascript" src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>
	
<%-- <c:url value="UploadPaper_Excel" var="searchUrl" /> --%>
<%-- <form:form action="${searchUrl}" method="post" id="search2" --%>
<%-- 	name="search2" modelAttribute="comd1"> --%>
<!-- 	<input type="hidden" name="typeReport1" id="typeReport1" value="0" /> -->
<%-- </form:form> --%>

<c:url value="Postgraduate_Course_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search1" name="search1">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="course_id1" id="course_id1" value="0" />
</form:form>

<c:url value="PG_Admitted_Students_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search2" name="search2">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="add_stud_pg" id="add_stud_pg" value="0" />
</form:form>

<c:url value="PG_Teaching_Staff_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search3" name="search3">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="teach_pg" id="teach_pg" value="0" />
</form:form>

<c:url value="PG_Examiners_MD_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search4" name="search4">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="ex_pg" id="ex_pg" value="0" />
</form:form>

<c:url value="PG_Dissertation_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search5" name="search5">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="dis_pg" id="dis_pg" value="0" />
</form:form>

<c:url value="PG_lecture_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search6" name="search6">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="lec_pg" id="lec_pg" value="0" />
</form:form>

<c:url value="PG_Assignment_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search7" name="search7">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="ass_pg" id="ass_pg" value="0" />
</form:form>

<c:url value="PG_Presentation_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search8" name="search8">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="pre_pg" id="pre_pg" value="0" />
</form:form>

<c:url value="PG_Examiner_List_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search9" name="search9">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="exl_pg" id="exl_pg" value="0" />
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
		$("#tab_id3").hide();
		$("#tab_id4").hide();
		$("#tab_id5").hide();
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
	
	mockjax1('search_system_a_council_pg');
  	table1 = dataTable('search_system_a_council_pg');
  	
  	mockjax1('search_system_b_council_pg');
  	table2 = dataTable('search_system_b_council_pg');
  	
  	mockjax1('search_system_c_council_pg');
  	table3 = dataTable('search_system_c_council_pg');
  	
  	mockjax1('search_system_d_council_pg');
  	table4 = dataTable('search_system_d_council_pg');
  	
  	mockjax1('search_system_disseration_pg_ea');
  	table5 = dataTable('search_system_disseration_pg_ea');
  	
	mockjax1('search_system_disseration_pg_eb');
  	table6 = dataTable('search_system_disseration_pg_eb');
  	
  	mockjax1('search_system_disseration_pg_ec');
  	table7 = dataTable('search_system_disseration_pg_ec');
  	
  	mockjax1('search_system_disseration_pg_ed');
  	table8 = dataTable('search_system_disseration_pg_ed');
  	
  	mockjax1('search_system_f_council_pg');
  	table9 = dataTable('search_system_f_council_pg');

	table1.ajax.reload();
	table2.ajax.reload();
	table3.ajax.reload();
	table4.ajax.reload();
	table5.ajax.reload();
	table6.ajax.reload();
	table7.ajax.reload();
	table8.ajax.reload();
	table9.ajax.reload();
	
	
	    $("#tab_id1").show();
	    $("#tab_id2").show();
		$("#tab_id3").show();
		$("#tab_id4").show();
		$("#tab_id5").show();
 		$("#tab_id6").show();

 		if($("#institute_status").val() == "-1") {
 			 $("#hid_aview").hide();
 			 $("#hid_arej").hide();
 			 
 			 $("#hid_bview").hide();
 			 $("#hid_brej").hide();
 			 
 			 $("#hid_cview").hide();
			 $("#hid_crej").hide();
			 
			 $("#hid_dview").hide();
			 $("#hid_drej").hide();
			 
			 $("#hid_fview").hide();
 			 $("#hid_frej").hide();
 			 
 			 $("#hid_eaview").hide();
 			 $("#hid_earej").hide();
 			 
 			 $("#hid_ebview").hide();
			 $("#hid_ebrej").hide();
 			
			 $("#hid_ecview").hide();
			 $("#hid_ecrej").hide();
			 
			 $("#hid_edview").hide();
			 $("#hid_edrej").hide();
			 
 			 $("#btn-save").hide();
 		}
 		if($("#institute_status").val() == "1") {
			 $("#hid_aview").hide();
			 $("#hid_arej").hide();
			 
			 $("#hid_bview").hide();
 			 $("#hid_brej").hide();
 			 
			 $("#hid_cview").hide();
			 $("#hid_crej").hide();
			 
			 $("#hid_dview").hide();
			 $("#hid_drej").hide();
			 
			 $("#hid_fview").hide();
 			 $("#hid_frej").hide();
 			 
 			 $("#hid_eaview").hide();
			 $("#hid_earej").hide();
			 
 			 $("#hid_ebview").hide();
			 $("#hid_ebrej").hide();
			 
			 $("#hid_ecview").hide();
			 $("#hid_ecrej").hide();
			 
			 $("#hid_edview").hide();
			 $("#hid_edrej").hide();
			 
 			 $("#btn-save").hide();
		}
}
document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('uni_id').onchange = function() {
		 getInstitute();
	};
	document.getElementById('btn-reload').onclick = function() {
		return search_detail();
	};
	document.getElementById('btn-save').onclick = function() {
		return Submit_Approval_Council_PG();
	};
	document.getElementById('btn-reject').onclick = function() {
		return Reject_council_pg();
	};
	
	document.getElementById('pdfex').onclick = function() {
		getDownloadPdfUploadFileA();
	};
	document.getElementById('pdfbex').onclick = function() {
		getDownloadPdfUploadFileB();
	};
	document.getElementById('pdfcex').onclick = function() {
		getDownloadPdfUploadFileC();
	};
	document.getElementById('pdfdex').onclick = function() {
		getDownloadPdfUploadFileD();
	};
	document.getElementById('pdfeaex').onclick = function() {
		getDownloadPdfUploadFileEA();
	};
	document.getElementById('pdfebex').onclick = function() {
		getDownloadPdfUploadFileEB();
	};
	document.getElementById('pdfecex').onclick = function() {
		getDownloadPdfUploadFileEC();
	};
	document.getElementById('pdfedex').onclick = function() {
		getDownloadPdfUploadFileED();
	};
	document.getElementById('pdffex').onclick = function() {
		getDownloadPdfUploadFileF();
	};
});

function getDownloadPdfUploadFileA(){  
	 
	var h_id = $("#h_id").val();
	$("#course_id1").val(h_id); 
	document.getElementById("search1").submit();
} 

function getDownloadPdfUploadFileB(){  
	var b_id = $("#b_id").val();
	$("#add_stud_pg").val(b_id); 
	document.getElementById("search2").submit();
} 

function getDownloadPdfUploadFileC(){  
	var c_id = $("#c_id").val();
	$("#teach_pg").val(c_id); 
	document.getElementById("search3").submit();
} 

function getDownloadPdfUploadFileD(){  
	var d_id = $("#d_id").val();
	$("#ex_pg").val(d_id); 
	document.getElementById("search4").submit();
} 

function getDownloadPdfUploadFileEA(){  
	var e_a_id = $("#e_a_id").val();
	$("#dis_pg").val(e_a_id); 
	document.getElementById("search5").submit();
}

function getDownloadPdfUploadFileEB(){  
	var e_b_id = $("#e_b_id").val();
	$("#lec_pg").val(e_b_id); 
	document.getElementById("search6").submit();
}

function getDownloadPdfUploadFileEC(){  
	var e_c_id = $("#e_c_id").val();
	$("#ass_pg").val(e_c_id); 
	document.getElementById("search7").submit();
}

function getDownloadPdfUploadFileED(){  
	var e_d_id = $("#e_d_id").val();
	$("#pre_pg").val(e_d_id); 
	document.getElementById("search8").submit();
}

function getDownloadPdfUploadFileF(){  
	var f_id = $("#f_id").val();
	$("#exl_pg").val(f_id); 
	document.getElementById("search9").submit();
}



function getInstitute() {
	var selval = $("#uni_id").val();0
	$.post("getInstituteUrlPG?" + key + "=" + value,
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
//----------------------VIEW-TABLE---------------------------

function data(tablename){
	//alert(tablename);
	 if(tablename=="search_system_a_council_pg"){
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
			var undergraduate_course = $("#postgraduate_course").val();
			var abbre_undergraduate_course = $("#abbre_postgraduate_course").val();
			var academic_session = $("#academic_session").val();
			var name_of_college = $("#name_of_college").val();
			var country=$("#country").val();
			var state = $("#state").val();
			var district = $("#district").val();
			var city = $("#city").val();
			var Postal_address = $("#Postal_address").val();
			var email =$("#email").val();
			var website= $("#website").val();
			var academic_year_applied_for=$("#academic_year_applied_for").val();
			var permission_from_central_gov=$("#permission_from_central_gov").val();
			var admission_intake=$("#admission_intake").val();
			var num_of_student_admitted=$("#num_of_student_admitted").val();
			var remarks=$("#remarks").val();
			
			var uni_id= $("#uni_id").val();
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			$.post("getFilter_PG_a?" + key + "=" + value, {
				

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
					for (var i = 0; i < j.length; i++) {
						
						if($("#institute_status").val() == "-1") {
							jsondata.push([j[i].ser,j[i].university_name,j[i].name_of_applicant_university,j[i].postgraduate_course,j[i].abbre_postgraduate_course,j[i].academic_session,j[i].name_of_college,
								j[i].country,j[i].state,j[i].district,j[i].city,j[i].postal_address,j[i].email,j[i].website,j[i].academic_year_applied_for,
								j[i].permission_from_central_gov,j[i].admission_intake,j[i].num_of_student_admitted,j[i].remarks]);
							}
						else{
								jsondata.push([j[i].ser,j[i].university_name,j[i].name_of_applicant_university,j[i].postgraduate_course,j[i].abbre_postgraduate_course,j[i].academic_session,j[i].name_of_college,
									j[i].country,j[i].state,j[i].district,j[i].city,j[i].postal_address,j[i].email,j[i].website,j[i].academic_year_applied_for,
									j[i].permission_from_central_gov,j[i].admission_intake,j[i].num_of_student_admitted,j[i].remarks,j[i].action,j[i].f1]);
						}	
						}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_PG_aListCount?" + key + "=" + value, {
				id:0,
 				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
 			setTimeout(setTimeLoadForTable, 1000);

	 }
	 else if(tablename=="search_system_b_council_pg") {
	 		
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
			var date_of_registration = $("#date_of_registration").val();
			var course_name = $("#course_name").val();
			var rank = $("#rank").val();
			var marks = $("#marks").val();
			var all_india = $("#all_india").val();
			var state=$("#state").val();
			var admission_authority = $("#admission_authority").val();
			var court_order = $("#court_order").val();
			var qualification_name = $("#qualification_name").val();
			var year_of_award_admission = $("#year_of_award_admission").val();
			var date_of_registration_state = $("#date_of_registration_state").val();
			var date_of_completion_md_part1 = $("#date_of_completion_md_part1").val();
			var date_of_completion_md_part2 = $("#date_of_completion_md_part2").val();
			var date_of_declaration_of_md = $("#date_of_declaration_of_md").val();
			var date_of_completion_internship =$("#date_of_completion_internship").val();
			var remarks= $("#remarks").val();
			var uni_id= $("#uni_id").val();
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
		
			$.post("getFilter_PG_b?" + key + "=" + value, {
				
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
					for (var i = 0; i < j.length; i++) {
						
						if($("#institute_status").val() == "-1") {
							jsondata.push([j[i].ser,j[i].inst_name,j[i].student_name,j[i].date_of_admission,j[i].date_of_registration,j[i].course_name,
								j[i].rank,j[i].marks,j[i].all_india,j[i].state,j[i].admission_authority,j[i].court_order,j[i].qualification_name,
								j[i].year_of_award_admission, j[i].date_of_registration_state,j[i].date_of_completion_md_part1,j[i].date_of_completion_md_part2,j[i].date_of_declaration_of_md,
								j[i].date_of_completion_internship,j[i].remarks]);
							}
						else{
								jsondata.push([j[i].ser,j[i].inst_name,j[i].student_name,j[i].date_of_admission,j[i].date_of_registration,j[i].course_name,
    								j[i].rank,j[i].marks,j[i].all_india,j[i].state,j[i].admission_authority,j[i].court_order,j[i].qualification_name,
    								j[i].year_of_award_admission, j[i].date_of_registration_state,j[i].date_of_completion_md_part1,j[i].date_of_completion_md_part2,j[i].date_of_declaration_of_md,
    								j[i].date_of_completion_internship,j[i].remarks,j[i].action,j[i].f1]);
						}
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_PG_bListCount?" + key + "=" + value, {
				id:0,
 				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
  			setTimeout(setTimeLoadForTableb, 1000);
 	 }
	 else if(tablename=="search_system_c_council_pg") {
 		
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
			
			var name_of_college_pg = $("#name_of_college_pg").val();
			var name_of_teaching_staff = $("#name_of_teaching_staff").val();
			var phone = $("#phone").val();
			var email_id = $("#email_id").val();
			var designation = $("#designation").val();
			var department = $("#department").val();
			var registration_no = $("#registration_no").val();
			var date_of_reg = $("#date_of_reg").val();
			var date_of_birth = $("#date_of_birth").val();
			var qualification_awarding_authority = $("#qualification_awarding_authority").val();
			var year_of_award = $("#year_of_award").val();
			var date_of_appointment = $("#date_of_appointment").val();
			var fulltime_parttime = $("#fulltime_parttime").val();
			var post_teaching = $("#post_teaching").val();
			var exp_from = $("#exp_from").val();
			var exp_to = $("#exp_to").val();
			var total_teaching_exp_in_year = $("#total_teaching_exp_in_year").val();
			var uni_id= $("#uni_id").val();
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			$.post("getFilter_PG_c?" + key + "=" + value, {
				
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
					for (var i = 0; i < j.length; i++) {
						
						if($("#institute_status").val() == "-1") {
							jsondata.push([j[i].ser,j[i].university_name,j[i].name_of_college_pg,j[i].name_of_teaching_staff,j[i].phone,
								j[i].email_id,j[i].designation,j[i].department,j[i].registration_no,j[i].date_of_reg,
								j[i].date_of_birth,j[i].qualification_awarding_authority,j[i].year_of_award,
								j[i].date_of_appointment,j[i].fulltime_parttime,j[i].post_teaching,j[i].exp_from,j[i].exp_to,j[i].total_teaching_exp_in_year]);
							}
						else{
								jsondata.push([j[i].ser,j[i].university_name,j[i].name_of_college_pg,j[i].name_of_teaching_staff,j[i].phone,
									j[i].email_id,j[i].designation,j[i].department,j[i].registration_no,j[i].date_of_reg,
									j[i].date_of_birth,j[i].qualification_awarding_authority,j[i].year_of_award,
									j[i].date_of_appointment,j[i].fulltime_parttime,j[i].post_teaching,j[i].exp_from,j[i].exp_to,j[i].total_teaching_exp_in_year,j[i].action,j[i].f1]);
						}
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_PG_cListCount?" + key + "=" + value, {
				id:0,
 				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
  			setTimeout(setTimeLoadForTablec, 1000);
 	 }
	 else if(tablename=="search_system_d_council_pg") {
	 		
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
			
			var name_of_homoeopathic_medical_college = $("#name_of_homoeopathic_medical_college").val();
			var name_of_guide = $("#name_of_guide").val();
			var name_of_student_for_guide = $("#name_of_student_for_guide").val();
			var topic_of_dissertation = $("#topic_of_dissertation").val();
			var whether_guide_participated_in_examination = $("#whether_guide_participated_in_examination").val();
			var date_of_submission = $("#date_of_submission").val();
			var date_of_acceptance = $("#date_of_acceptance").val();
			var whether_student_published_article = $("#whether_student_published_article").val();
			var mention_details = $("#mention_details").val();
			var article_title = $("#article_title").val();
			var month_year_exam = $("#month_year_exam").val();
			var institute_status= $("#institute_status").val();
			var uni_id= $("#uni_id").val();
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
			$.post("getFilter_PG_d?" + key + "=" + value, {
				
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
					for (var i = 0; i < j.length; i++) {
						
						if($("#institute_status").val() == "-1") {
							jsondata.push([j[i].ser,j[i].inst_name,j[i].name_of_homoeopathic_medical_college,j[i].name_of_guide,
								j[i].name_of_student_for_guide, j[i].topic_of_dissertation, j[i].whether_guide_participated_in_examination,
								j[i].date_of_submission,j[i].date_of_acceptance,j[i].whether_student_published_article,j[i].mention_details,
								j[i].article_title,j[i].month_year_exam]);
							}
						else{
								jsondata.push([j[i].ser,j[i].inst_name,j[i].name_of_homoeopathic_medical_college,j[i].name_of_guide,
									j[i].name_of_student_for_guide, j[i].topic_of_dissertation, j[i].whether_guide_participated_in_examination,
									j[i].date_of_submission,j[i].date_of_acceptance,j[i].whether_student_published_article,j[i].mention_details,
									j[i].article_title,j[i].month_year_exam,j[i].action,j[i].f1]);
						}
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_PG_dListCount?" + key + "=" + value, {
				id:0,
 				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
  			setTimeout(setTimeLoadForTabled, 1000);
 	 }
	 else if(tablename=="search_system_disseration_pg_ea") {
	 		
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
			
			var student_name_pg = $("#student_name_pg").val();
  			var from_date = $("#from_date").val();
  			var to_date = $("#to_date").val();
  			var name_of_topic = $("#name_of_topic").val();
  			var conclusion_obtain = $("#conclusion_obtain").val();
  			var date_of_submission_pg = $("#date_of_submission_pg").val();
  			var publication = $("#publication").val();
  			var mention_article = $("#mention_article").val();
  			
			var institute_status= $("#institute_status").val();
			var uni_id= $("#uni_id").val();
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
			$.post("getFilter_PG_ea?" + key + "=" + value, {
				
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
					for (var i = 0; i < j.length; i++) {
						
						if($("#institute_status").val() == "-1") {
							jsondata.push([j[i].ser,j[i].inst_name,j[i].student_name_pg,j[i].from_date,j[i].to_date,j[i].name_of_topic,j[i].conclusion_obtain,
								j[i].date_of_submission_pg,j[i].publication,j[i].mention_article]);
							}
						else{
							jsondata.push([j[i].ser,j[i].inst_name,j[i].student_name_pg,j[i].from_date,j[i].to_date,j[i].name_of_topic,j[i].conclusion_obtain,
								j[i].date_of_submission_pg,j[i].publication,j[i].mention_article,j[i].action,j[i].f1]);
						}
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_PG_eaListCount?" + key + "=" + value, {
				id:0,
 				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
   			setTimeout(setTimeLoadForTableea, 1000);
 	 }
	 else if(tablename=="search_system_disseration_pg_eb") {
	 		
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
  			var student_class = $("#student_class").val();
  			var lecture_date = $("#lecture_date").val();
  			var lecture_day = $("#lecture_day").val();
  			var lecture_time = $("#lecture_time").val();
  			var topic = $("#topic").val();
  			
			var institute_status= $("#institute_status").val();
			var uni_id= $("#uni_id").val();
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
			$.post("getFilter_PG_eb?" + key + "=" + value, {
				
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
					for (var i = 0; i < j.length; i++) {
						
						if($("#institute_status").val() == "-1") {
							jsondata.push([j[i].ser,j[i].inst_name,j[i].student_name,j[i].student_class,j[i].lecture_date,j[i].lecture_day,j[i].lecture_time,
								j[i].topic]);
							}
						else{
							jsondata.push([j[i].ser,j[i].inst_name,j[i].student_name,j[i].student_class,j[i].lecture_date,j[i].lecture_day,j[i].lecture_time,
								j[i].topic,j[i].action,j[i].f1]);
						}
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_PG_ebListCount?" + key + "=" + value, {
				id:0,
 				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
    			setTimeout(setTimeLoadForTableeb, 1000);
 	 }
	 else if(tablename=="search_system_disseration_pg_ec") {
	 		
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
			
			var student_name_formc = $("#student_name_formc").val();
  			var list_of_assignment_formc = $("#list_of_assignment_formc").val();
  			
			var institute_status= $("#institute_status").val();
			var uni_id= $("#uni_id").val();
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
			$.post("getFilter_PG_ec?" + key + "=" + value, {
				
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
					for (var i = 0; i < j.length; i++) {
						
						if($("#institute_status").val() == "-1") {
							jsondata.push([j[i].ser,j[i].inst_name,j[i].student_name_formc,j[i].list_of_assignment_formc]);
							}
						else{
							jsondata.push([j[i].ser,j[i].inst_name,j[i].student_name_formc,j[i].list_of_assignment_formc,j[i].action,j[i].f1]);
						}
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_PG_ecListCount?" + key + "=" + value, {
				id:0,
 				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
    			setTimeout(setTimeLoadForTableec, 1000);
 	 }
	 else if(tablename=="search_system_disseration_pg_ed") {
	 		
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
			
			var student_name_presen = $("#student_name_presen").val();
  			var list_of_presentations = $("#list_of_presentations").val();
  			var title_of_seminar_attended = $("#title_of_seminar_attended").val();
  			
			var institute_status= $("#institute_status").val();
			var uni_id= $("#uni_id").val();
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
			$.post("getFilter_PG_ed?" + key + "=" + value, {
				
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
					for (var i = 0; i < j.length; i++) {
						
						if($("#institute_status").val() == "-1") {
							jsondata.push([j[i].ser,j[i].inst_name,j[i].student_name_presen,j[i].list_of_presentations,j[i].title_of_seminar_attended]);
							}
						else{
							jsondata.push([j[i].ser,j[i].inst_name,j[i].student_name_presen,j[i].list_of_presentations,j[i].title_of_seminar_attended,j[i].action,j[i].f1]);
						}
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_PG_edListCount?" + key + "=" + value, {
				id:0,
 				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
    			setTimeout(setTimeLoadForTableed, 1000);
 	 }
	 else if(tablename=="search_system_f_council_pg") {
  		
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
			
			var subject = $("#subject").val();
			var name_of_examiners = $("#name_of_examiners").val();
			var date_of_examination = $("#date_of_examination").val();
			var uni_id= $("#uni_id").val();
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			

			$.post("getFilter_PG_f?" + key + "=" + value, {
				
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
							for (var i = 0; i < j.length; i++) {
								
								if($("#institute_status").val() == "-1") {
									jsondata.push([j[i].ser,j[i].university_name,j[i].subject,j[i].name_of_examiners,j[i].date_of_examination]);

								}
								else
									{
								jsondata.push([j[i].ser,j[i].university_name,j[i].subject,j[i].name_of_examiners,j[i].date_of_examination,j[i].action,j[i].f1]);
									}
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_PG_fListCount?" + key + "=" + value, {
				id:0,
 				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
  			setTimeout(setTimeLoadForTablef, 1000);
 	 }

}

//////////////////// Form A ////////////////////////////
function setTimeLoadForTable(){
	document.querySelectorAll('.ADDPG').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdpg'+val).value;
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
 $.post('getviewdatapga?'+key+"="+value, {
	 id:id
	}).done(function(j) {
		
 		 	$("#h_id").val(j[0].id);
			$("span#name_of_applicant_university").text(j[0].name_of_applicant_university);
			$("span#postgraduate_course").text(j[0].postgraduate_course);
			$("span#abbre_postgraduate_course").text(j[0].abbre_postgraduate_course);
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

////////////////////Form B ////////////////////////////

function setTimeLoadForTableb(){
	document.querySelectorAll('.ADDPGb').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdbpg'+val).value;
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
 $.post('getviewdatapgd?'+key+"="+value, {
	 id:id
	}).done(function(j) {
		     $("#b_id").val(j[0].id);
			$("span#student_name").text(j[0].student_name);
			$("span#date_of_admission").text(j[0].date_of_admission);
			$("span#date_of_registration").text(j[0].date_of_registration);
			$("span#course_name").text(j[0].course_name);
			$("span#rank").text(j[0].rank);
			$("span#marks").text(j[0].marks);
			$("span#all_india").text(j[0].all_india);
			$("span#state").text(j[0].state);
			$("span#admission_authority").text(j[0].admission_authority);
			$("span#court_order").text(j[0].court_order);
			$("span#qualification_name").text(j[0].qualification_name);
			$("span#year_of_award_admission").text(j[0].year_of_award_admission);
			$("span#date_of_registration_state").text(j[0].date_of_registration_state);
			$("span#date_of_completion_md_part1").text(j[0].date_of_completion_md_part1);
			$("span#date_of_completion_md_part2").text(j[0].date_of_completion_md_part2);
			$("span#date_of_declaration_of_md").text(j[0].date_of_declaration_of_md);
			$("span#date_of_completion_internship").text(j[0].date_of_completion_internship);
			$("span#remarks").text(j[0].remarks);
	});
}

////////////////////Form C ////////////////////////////

function setTimeLoadForTablec(){
	document.querySelectorAll('.ADDPGteaching').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdTeaching'+val).value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				viewData3(hid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.clarificationformcData').forEach((items, index) => {
		items.addEventListener('click', event => {
			debugger;
			var val=parseInt(index)+1;
			var hid1 = document.getElementById('RejectformcId'+val).value;
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

//-------------POPUP viewData3--------------

function viewData3(id) {
 $.post('getviewdatapgb?'+key+"="+value, {
	 id:id
	}).done(function(j) {
		 $("#c_id").val(j[0].id);
			$("span#name_of_college_pg").text(j[0].name_of_college_pg);
			$("span#name_of_teaching_staff").text(j[0].name_of_teaching_staff);
			$("span#phone").text(j[0].phone);
			$("span#email_id").text(j[0].email_id);
			$("span#designation").text(j[0].designation);
			$("span#department").text(j[0].department);
			$("span#registration_no").text(j[0].registration_no);
			$("span#date_of_reg").text(j[0].date_of_reg);
			$("span#date_of_birth").text(j[0].date_of_birth);
			$("span#qualification_awarding_authority").text(j[0].qualification_awarding_authority);
			$("span#year_of_award").text(j[0].year_of_award);
			$("span#date_of_appointment").text(j[0].date_of_appointment);
			$("span#fulltime_parttime").text(j[0].fulltime_parttime);
			$("span#post_teaching").text(j[0].post_teaching);
			$("span#exp_from").text(j[0].exp_from);
			$("span#exp_to").text(j[0].exp_to);
			$("span#total_teaching_exp_in_year").text(j[0].total_teaching_exp_in_year);
			
	});
}
////////////////////Form D////////////////////////////

function setTimeLoadForTabled(){
	document.querySelectorAll('.ADDPGd').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIddpg'+val).value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				viewData4(hid);
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
 $.post('getviewdatapge?'+key+"="+value, {
	 id:id
	}).done(function(j) {
// alert("----jn")
		  $("#d_id").val(j[0].id);
			$("span#name_of_homoeopathic_medical_college").text(j[0].name_of_homoeopathic_medical_college);
			$("span#name_of_guide").text(j[0].name_of_guide);
			$("span#name_of_student_for_guide").text(j[0].name_of_student_for_guide);
			$("span#topic_of_dissertation").text(j[0].topic_of_dissertation);
			$("span#whether_guide_participated_in_examination").text(j[0].whether_guide_participated_in_examination);
			$("span#date_of_submission").text(j[0].date_of_submission);
			$("span#date_of_acceptance").text(j[0].date_of_acceptance);
			$("span#whether_student_published_article").text(j[0].whether_student_published_article);
			$("span#mention_details").text(j[0].mention_details);
			$("span#article_title").text(j[0].article_title);
			$("span#month_year_exam").text(j[0].month_year_exam);
	});
}



////////////////////Form E-A////////////////////////////

function setTimeLoadForTableea(){
	document.querySelectorAll('.ADDPGea').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdeapg'+val).value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				viewData5(hid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.clarificationformeaData').forEach((items, index) => {
		items.addEventListener('click', event => {
			debugger;
			var val=parseInt(index)+1;
			var hid1 = document.getElementById('RejectformeaId'+val).value;
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

//-------------POPUP viewData5-------------

function viewData5(id) {
 $.post('getviewdatapgea?'+key+"="+value, {
	 id:id
	}).done(function(j) {
// alert("----jn")
		  $("#e_a_id").val(j[0].id);
			$("span#student_name_pg").text(j[0].student_name_pg);
			$("span#from_date").text(j[0].from_date);
			$("span#to_date").text(j[0].to_date);
			$("span#name_of_topic").text(j[0].name_of_topic);
			$("span#conclusion_obtain").text(j[0].conclusion_obtain);
			$("span#date_of_submission_pg").text(j[0].date_of_submission_pg);
			$("span#publication").text(j[0].publication);
			$("span#mention_article").text(j[0].mention_article);
			
	});
}

////////////////////Form E-B////////////////////////////

function setTimeLoadForTableeb(){
	document.querySelectorAll('.ADDPGeb').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdebpg'+val).value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				viewData5b(hid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.clarificationformebData').forEach((items, index) => {
		items.addEventListener('click', event => {
			debugger;
			var val=parseInt(index)+1;
			var hid1 = document.getElementById('RejectformebId'+val).value;
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

//-------------POPUP viewData5-------------

function viewData5b(id) {
 $.post('getviewdatapgeb?'+key+"="+value, {
	 id:id
	}).done(function(j) {
// alert("----jn")
		   $("#e_b_id").val(j[0].id);
			$("span#student_name").text(j[0].student_name);
			$("span#student_class").text(j[0].student_class);
			$("span#lecture_date").text(j[0].lecture_date);
			$("span#lecture_day").text(j[0].lecture_day);
			$("span#lecture_time").text(j[0].lecture_time);
			$("span#topic").text(j[0].topic);
	});
}

////////////////////Form E-C////////////////////////////

function setTimeLoadForTableec(){
	document.querySelectorAll('.ADDPGec').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdecpg'+val).value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				viewData5c(hid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.clarificationformecData').forEach((items, index) => {
		items.addEventListener('click', event => {
			debugger;
			var val=parseInt(index)+1;
			var hid1 = document.getElementById('RejectformecId'+val).value;
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

//-------------POPUP viewData5-------------

function viewData5c(id) {
 $.post('getviewdatapgec?'+key+"="+value, {
	 id:id
	}).done(function(j) {
		$("#e_c_id").val(j[0].id);
			$("span#student_name_formc").text(j[0].student_name_formc);
			$("span#list_of_assignment_formc").text(j[0].list_of_assignment_formc);
	});
}

////////////////////Form E-D////////////////////////////

function setTimeLoadForTableed(){
	document.querySelectorAll('.ADDPGed').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdedpg'+val).value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				viewData5d(hid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.clarificationformedData').forEach((items, index) => {
		items.addEventListener('click', event => {
			debugger;
			var val=parseInt(index)+1;
			var hid1 = document.getElementById('RejectformedId'+val).value;
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

//-------------POPUP viewData5-------------

function viewData5d(id) {
 $.post('getviewdatapged?'+key+"="+value, {
	 id:id
	}).done(function(j) {
		$("#e_d_id").val(j[0].id);
			$("span#student_name_presen").text(j[0].student_name_presen);
			$("span#list_of_presentations").text(j[0].list_of_presentations);
			$("span#title_of_seminar_attended").text(j[0].title_of_seminar_attended);
	});
}



function setTimeLoadForTablef(){
	document.querySelectorAll('.ADDExaminers').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdExaminers'+val).value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				viewData6(hid);
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

//-------------POPUP viewData6-------------

function viewData6(id) {
 $.post('getviewdatapgc?'+key+"="+value, {
	 id:id
	}).done(function(j) {
		$("#f_id").val(j[0].id);
			$("span#subject").text(j[0].subject);
			$("span#name_of_examiners").text(j[0].name_of_examiners);
			$("span#date_of_examination").text(j[0].date_of_examination);
			
			
	});
}


function Reject_council_pg() {
    if($("input#reject_remarks").val() == "") {
      alert("Please Enter Clarification Of Data ");
      $("input#reject_remarks").focus();
      return false;
	  }

	 $.post("reject_action_council_pg?"+key+"="+value,{
		
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
function Submit_Approval_Council_PG(){
//	alert("---in submit")
	
	$.post("Final_Submit_Council_PG?" +  key + "=" + value, function(data) {
		alert(data);
//			 if(data.error == null) {
//			 	if(data.id != null){
//// 					 $('#council_approved_status').val(1);
//					 alert(data.saved);
//			 		}
//			 }
//		 else {
//     	   alert(data.error)
//			  }
}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
}

</script>
