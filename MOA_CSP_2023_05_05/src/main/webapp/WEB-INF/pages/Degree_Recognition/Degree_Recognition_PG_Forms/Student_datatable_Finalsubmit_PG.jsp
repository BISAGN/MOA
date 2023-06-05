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
>


<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script> -->
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script> -->


<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

<!-- datatable style and js end-->
<link rel="stylesheet"
	href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/watermark/common.js"></script>
<style>
.Degree_Recognitiossn .hide-action .multi-btn-group {
	justify-content: center;
}

.Degree_Recognition .hide-action ul.buttons-group li {
	padding: 0 3px;
}
</style>
<section class="search_regulation Degree_Recognition">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>
							<span id="lbladd">Degree Recognition Student</span>
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Degree
									Recognition</li>
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
							<div class="tab tablinks" id="tab_id7">
								<button class="tab-toggle">First Choose The Institute</button>
							</div>
							<div class="content tabcontent" id="Form_z">
								<h4 class="heading">Choose The Institute</h4>
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
											<label>Institute Status<strong class="mandatory">*</strong></label>
											<input type="hidden" id="id" name="id" class="form-control"
												value="0" autocomplete="off">
											<div class="select-position">
												<select name="institute_status" id="institute_status"
													class="form-control customselect">
													<option value="">---Select Status---</option>
													<!-- 															<option value="0">Draft</option> -->
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
										type="button"><i class="lni lni-search-alt"></i>Search
											Details</a></li>
									<li>
								</ul>
							</div>
							<!-- START FORM A -->

							<div class="tab tablinks" id="tab_id2" style="display: none;">
								<button class="tab-toggle">Student Admitted
								</button>
							</div>

							<div class="content tabcontent" id="Form_B">

								<input type="hidden" id="h_id" name="h_id" value="a2"> <input
									type="hidden" id="form_b_id" name="form_b_id" value="0"
									class="form-control autocomplete">

								<h4 class="heading">Students Admitted As regular
									Candidate(Year And Subject Wise) For M.D.(HOM)</h4>
								<div class="row">
									<div class="col-12">
										<div class="card-style mb-30">
											<div class="table-wrapper custom-datatable-p">
												<input type="hidden" id="pgad_hid" name="pgad_hid" />
												<table class="table" id="search_system_admitted">
													<thead>
														<tr>
															<th><h6>Ser No</h6></th>
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
															<th><h6>Year Of Award For Admission To
																	M.D.(Hom.)</h6></th>
															<th><h6>Date Of Completion Of Internship</h6></th>
															<th><h6>Date Of Registration With State</h6></th>
															<th><h6>Date Of Completion Of MD Part-1</h6></th>
															<th><h6>Date Of Completion Of MD Part-2</h6></th>
															<th><h6>Date Of Declaration Of MD(Hom.)Part-2</h6></th>
															<th><h6>Remarks</h6></th>
															<th id="add_admitted"><h6>Clarification</h6></th>
															<th id="hide_ea"><h6>Action</h6></th>
														</tr>
													</thead>
													<tbody class="custom-datatablepra"></tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><input type="button"
												id="btn-reject-submit-student-admitted"
												class="main-btn success-btn  btn-hover"
												value="Submit For Re-Approval"></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- END FORM A -->

							<!-- START FORM B -->

<!-- 							<div class="tab tablinks" id="tab_id4" style="display: none;"> -->
<!-- 								<button class="tab-toggle">Details Of Guides And -->
<!-- 									Examiners For M.D.(HOM) Course/Any Other Post Graduate Course</button> -->
<!-- 							</div> -->

<!-- 							<div id="Form_D" class="content tabcontent"> -->
<%-- 								<form id="Form_D_details"> --%>
<!-- 									<h4 class="heading">Details Of Guides And Examiners For -->
<!-- 										M.D.(HOM) Course/Any Other Post Graduate Course</h4> -->
<!-- 									<div class="tables-wrapper"> -->
<!-- 										<div class="row"> -->
<!-- 											<div class="col-lg-12"> -->
<!-- 												<div class="card-style mb-30"> -->
<!-- 													<div class="table-wrapper custom-datatable-p"> -->
<!-- 														<input type="hidden" id="pgex_hid" name="pgex_hid" /> -->
<!-- 														<table class="table" id="search_system_examiners_pg"> -->
<!-- 															<thead> -->
<!-- 																<tr> -->
<!-- 																	<th><h6>Ser No</h6></th> -->
<!-- 																	<th><h6>Name Of Homoeopathic Medical College</h6></th> -->
<!-- 																	<th><h6>Name Of Guide</h6></th> -->
<!-- 																	<th><h6>Name Of Students</h6></th> -->
<!-- 																	<th><h6>Topic Of Dissertation</h6></th> -->
<!-- 																	<th><h6>Whether Guide Participated In -->
<!-- 																			Examination</h6></th> -->
<!-- 																	<th><h6>Date Of Submission</h6></th> -->
<!-- 																	<th><h6>Date Of Acceptance</h6></th> -->
<!-- 																	<th><h6>Whether Student published Article -->
<!-- 																			From Dissertation/h6></th> -->
<!-- 																	<th><h6>If Yes, Mention The Details</h6></th> -->
<!-- 																	<th><h6>Article Title</h6></th> -->
<!-- 																	<th><h6>Month And Year</h6></th> -->
<!-- 																	<th id="add_exami"><h6>Clarification</h6></th> -->
<!-- 																	<th id="hide_eb"><h6>Action</h6></th> -->
<!-- 																</tr> -->
<!-- 																end table row -->
<!-- 															</thead> -->
<!-- 															<tbody class="custom-datatablepra"></tbody> -->
<!-- 														</table> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="col-lg-12 col-md-12 col-sm-12"> -->
<!-- 												<ul class="buttons-group mainbtn"> -->
<!-- 													<li><input type="button" -->
<!-- 														id="btn-reject-submit-examiners-md" -->
<!-- 														class="main-btn success-btn  btn-hover" -->
<!-- 														value="Submit For Re-Approval"></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<%-- 								</form> --%>
<!-- 							</div> -->
							<!-- END FORM B -->

							<!-- Start PG FORM WORK DONE PG ADD MORE  -->

							<div class="tab tablinks" id="tab_id8" style="display: none;">
								<button class="tab-toggle">Work Done by Post Graduate
									Students</button>
							</div>

							<div id="Form_3" class="content tabcontent custom-htab-acco">
								<div class="">
<!-- 									<ul id="tabs" class="nav nav-tabs" role="tablist"> -->
<!-- 										<li class="nav-item" id="tab_id5"><a id="Form_3_a" -->
<!-- 											href="#form-A" class="nav-link active" data-toggle="tab" -->
<!-- 											role="tab">A.Disseration Topic</a></li> -->
<!-- 										<li class="nav-item" id="tab_id5"><a id="Form_3_b" -->
<!-- 											href="#form-B" class="nav-link" data-toggle="tab" role="tab">B.Lacture -->
<!-- 												taken</a></li> -->

<!-- 										<li class="nav-item" id="tab_id5"><a id="Form_3_c" -->
<!-- 											href="#form-C" class="nav-link" data-toggle="tab" role="tab">C.Assignment</a> -->
<!-- 										</li> -->

<!-- 										<li class="nav-item" id="tab_id5"><a id="Form_3_d" -->
<!-- 											href="#form-D" class="nav-link" data-toggle="tab" role="tab">D.Persentations -->
<!-- 												given</a></li> -->

<!-- 									</ul> -->

									<!-- Form PG(3)(a) -->

									<div id="content" class="tab-content" role="tablist">

										<div id="form-A" class="tab-pane fade show active"
											role="tabpanel" aria-labelledby="Form_3_a">
											<div id="collapse-A" class="collapse show"
												data-parent="#content" role="tabpanel"
												aria-labelledby="heading-A">
												<div id="Form_3_a">
													<form id="Form_PG3a_details">
														<h4 class="heading mt-3">Disseratation Topic of P.G.
															Student-Year wise/Depratment wise</h4>
														<div class="tables-wrapper">
															<div class="row">
																<div class="col-lg-12">
																	<div class="card-style mb-30">
																		<div class="table-wrapper custom-datatable-p">
																			<input type="hidden" id="pgdis_hid" name="pgdis_hid" />
																			<table class="table"
																				id="search_system_disseration_pg">
																				<thead>
																					<tr>
																						<th rowspan="2"><h6>Ser No</h6></th>
																						<th rowspan="2"><h6>Name of student</h6></th>
																						<th colspan="2"><h6>Duration of house
																								job Dates</h6></th>
																						<th rowspan="2"><h6>Name of Topic</h6></th>
																						<th rowspan="2"><h6>Outcomes/Result/Conclusion
																								obtained from disseration</h6></th>
																						<th rowspan="2"><h6>Date of submission</h6></th>
																						<th rowspan="2"><h6>Any publication from
																								Disseration(Yes/No)</h6></th>
																						<th rowspan="2"><h6>If
																								yes,mention(article title/journal)</h6></th>
																						<th rowspan="2" id="add_diss"><h6>Clarification</h6></th>
																						<th rowspan="2" id="hide_ec"><h6>Action</h6></th>
																					</tr>
																					<tr>
																						<th><h6>From date</h6></th>
																						<th><h6>To date</h6></th>
																					</tr>
																				</thead>
																			</table>
																			<!-- end table -->
																		</div>
																	</div>
																</div>
															</div>
															<div class="col-lg-12 col-md-12 col-sm-12">
																<ul class="buttons-group mainbtn">
																	<li><input type="button"
																		id="btn-reject-dissertation"
																		class="main-btn success-btn  btn-hover"
																		value="Submit For Re-Approval"></li>
																</ul>
															</div>
														</div>
													</form>
												</div>
											</div>
										</div>
										<!-- End Tab Form A -->


										<!-- Form PG(3)(B) -->

										<div id="form-B" class="tab-pane fade show active"
											role="tabpanel" aria-labelledby="Form_3_b">
											<div id="collapse-A" class="collapse show"
												data-parent="#content" role="tabpanel"
												aria-labelledby="heading-A">
												<div id="Form_3_b">
													<form id="Form_PG3b_details">
														<h4 class="heading mt-3">Lecture Taken</h4>
														<div class="tables-wrapper">
															<div class="row">
																<div class="col-lg-12">
																	<div class="card-style mb-30">
																		<div class="table-wrapper custom-datatable-p">
																			<input type="hidden" id="pglec_hid" name="pglec_hid" />
																			<table class="table" id="search_system_lecture_pg">
																				<thead>
																					<tr>
																						<th><h6>S.No</h6></th>
																						<th><h6>Name of student</h6></th>
																						<th><h6>Class</h6></th>
																						<th><h6>Date</h6></th>
																						<th><h6>Day</h6></th>
																						<th><h6>Time</h6></th>
																						<th><h6>Topic</h6></th>
																						<th id="add_lec"><h6>Clarification</h6></th>
																						<th id="hide_ed"><h6>Action</h6></th>
																					</tr>
																				</thead>
																			</table>
																			<!-- end table -->
																		</div>
																	</div>
																</div>
																<div class="col-lg-12 col-md-12 col-sm-12">
																	<ul class="buttons-group mainbtn">
																		<li><input type="button" id="btn-reject-lecture"
																			class="main-btn success-btn  btn-hover"
																			value="Submit For Re-Approval"></li>
																	</ul>
																</div>
															</div>
														</div>
													</form>
												</div>
											</div>
										</div>
										<!-- End Tab Form B -->

										<!-- Form PG(3)(C) -->

<!-- 										<div id="form-C" class="tab-pane fade show active" -->
<!-- 											role="tabpanel" aria-labelledby="Form_3_c"> -->
<!-- 											<div id="collapse-A" class="collapse show" -->
<!-- 												data-parent="#content" role="tabpanel" -->
<!-- 												aria-labelledby="heading-A"> -->
<!-- 												<div id="Form_3_c"> -->
<%-- 													<form id="Form_PG3c_details"> --%>
<!-- 														<h4 class="heading mt-3">Assignment</h4> -->
<!-- 														<div class="tables-wrapper"> -->
<!-- 															<div class="row"> -->
<!-- 																<div class="col-lg-12"> -->
<!-- 																	<div class="card-style mb-30"> -->
<!-- 																		<div class="table-wrapper custom-datatable-p"> -->
<!-- 																			<input type="hidden" id="pgassi_hid" -->
<!-- 																				name="pgassi_hid" /> -->
<!-- 																			<table class="table" id="search_system_assignment_pg"> -->
<!-- 																				<thead> -->
<!-- 																					<tr> -->
<!-- 																						<th><h6>S.No</h6></th> -->
<!-- 																						<th><h6>Name of student</h6></th> -->
<!-- 																						<th><h6>List Of Assignment</h6></th> -->
<!-- 																						<th id="add_assi"><h6>Clarification</h6></th> -->
<!-- 																						<th id="hide_ee"><h6>Action</h6></th> -->
<!-- 																					</tr> -->
<!-- 																				</thead> -->
<!-- 																			</table> -->
<!-- 																			end table -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 																<div class="col-lg-12 col-md-12 col-sm-12"> -->
<!-- 																	<ul class="buttons-group mainbtn"> -->
<!-- 																		<li><input type="button" -->
<!-- 																			id="btn-reject-assignment" -->
<!-- 																			class="main-btn success-btn  btn-hover" -->
<!-- 																			value="Submit For Re-Approval"></li> -->
<!-- 																	</ul> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</div> -->
<%-- 													</form> --%>
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
										<!-- End Tab Form C -->

										<!-- Form PG(3)(D) -->

										<div id="form-D" class="tab-pane fade show active"
											role="tabpanel" aria-labelledby="Form_3_d">
											<div id="collapse-A" class="collapse show"
												data-parent="#content" role="tabpanel"
												aria-labelledby="heading-A">
												<div id="Form_3_d">
													<form id="Form_PG3d_details">
														<h4 class="heading mt-3">Presentation</h4>
														<div class="tables-wrapper">
															<div class="row">
																<div class="col-lg-12">
																	<div class="card-style mb-30">
																		<div class="table-wrapper custom-datatable-p">
																			<input type="hidden" id="pgpre_hid" name="pgpre_hid" />
																			<table class="table"
																				id="search_system_presentation_pg">
																				<thead>
																					<tr>
																						<th><h6>S.No</h6></th>
																						<th><h6>Name of student</h6></th>
																						<th><h6>List Of Presentation</h6></th>
																						<th><h6>Title Of The Seminar
																								Attended/Topic</h6></th>
																						<th id="add_pre"><h6>Clarification</h6></th>
																						<th id="hide_ef"><h6>Action</h6></th>
																					</tr>
																				</thead>
																			</table>
																			<!-- end table -->
																		</div>
																	</div>
																</div>
																<div class="col-lg-12 col-md-12 col-sm-12">
																	<ul class="buttons-group mainbtn">
																		<li><input type="button"
																			id="btn-reject-presentation"
																			class="main-btn success-btn  btn-hover"
																			value="Submit For Re-Approval"></li>
																	</ul>
																</div>
															</div>
														</div>
													</form>
												</div>
											</div>
										</div>
										<!-- End Tab Form D -->

										<div class="col-lg-12 col-md-12 col-sm-12">
											<ul class="buttons-group mainbtn">
												<li><input type="button" id="btn-save"
													class="main-btn success-btn  btn-hover"
													value="Submit For Approval"></li>
											</ul>
										</div>

									</div>
									<!-- End Tablist -->

								</div>
								<!-- End Tab Forms Div Class -->
							</div>
							<!-- End Tab Forms  -->

						</div>
						<!-- End tabs content -->
					</div>
					<!-- End card style -->
				</div>
			</div>
			<!-- End row -->
		</div>
		<!-- End Custom Vtab -->
	</div>
	<!--  End container fluid -->
</section>

<!--  modal start -->
<div class="modal fade custom-modal" id="Dissertationmodal"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="staticBackdropLabel">Edit Data</h3>
				<button type="button" class="btn-close" id="btn-close"
					data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData"></div>
			</div>
		</div>
	</div>
</div>
<!-- modal end -->

<c:url value="Student_Edit_PG_Update_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="eid">
	<input type="hidden" name="eid" id="eid">
</form:form>

<c:url value="Student_Edit_Examiners_PG_Update_Url"
	var="Edit_Examinerspg_Url" />
<form:form action="${Edit_Examinerspg_Url}" method="post"
	id="updateExaminerspgForm" name="updateExaminerspgForm"
	modelAttribute="c">
	<input type="hidden" name="expg_id" id="expg_id">
</form:form>

<script type="text/javascript"
	src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>

<c:url value="UploadPaper_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
        $(document).ready(function() {
       
                if(window.location.href.includes("msg"))
                {
                         var url = window.location.href.split("?msg")[0];
                         window.location = url;
                }
        		
        });

        $("#tab_id2").hide();
// 	 	$("#tab_id4").hide();
		$("#tab_id8").hide();
		
        function select_status(){
        	
        	 if($("select#institute_status").val() == "") {
      	       alert("Please Select Status");
      	       $("select#institute_status").focus();
      	       return false;
      		  }
        	 
        	mockjax1('search_system_admitted');
           	table1 = dataTable('search_system_admitted');
           	
//            	mockjax1('search_system_examiners_pg');
//        		table2 = dataTable('search_system_examiners_pg');
       		
       		mockjax1('search_system_disseration_pg');
       		table3 = dataTable('search_system_disseration_pg');
       		
       		mockjax1('search_system_lecture_pg');
       		table4 = dataTable('search_system_lecture_pg');
       		
//        		mockjax1('search_system_assignment_pg');
//        		table5 = dataTable('search_system_assignment_pg');
       		
       		mockjax1('search_system_presentation_pg');
       		table6 = dataTable('search_system_presentation_pg');
       		
       		table1.ajax.reload();
// 			table2.ajax.reload();
			table3.ajax.reload();
			table4.ajax.reload();
// 			table5.ajax.reload();
			table6.ajax.reload();
			
			$("#tab_id2").show();
		 	$("#tab_id4").show();
			$("#tab_id8").show();
        
        if($("#institute_status").val() == "0") 
		{  
        			$("#btn-save").show();
			        $("#btn-reject-submit-student-admitted").hide();
			        $("#btn-reject-submit-examiners-md").hide();
			        $("#btn-reject-dissertation").hide();
			        $("#btn-reject-lecture").hide();
			        $("#btn-reject-assignment").hide();
			        $("#btn-reject-presentation").hide();
		     	    
			        $("#add_admitted").hide();
			        $("#add_exami").hide();
			        $("#add_diss").hide();
			        $("#add_lec").hide();
			        $("#add_assi").hide();
			        $("#add_pre").hide();
			        
	    }
        if($("#institute_status").val() == "1") 
		{
        	$("#btn-save").hide();
	        $("#btn-reject-submit-examiners-md").hide();
	        $("#btn-reject-dissertation").hide();
	        $("#btn-reject-lecture").hide();
	        $("#btn-reject-assignment").hide();
	        $("#btn-reject-presentation").hide();
	        
	        $("#hide_ea").hide();
	        $("#hide_eb").hide();
	        $("#hide_ec").hide();
	        $("#hide_ed").hide();
	        $("#hide_ee").hide();
	        $("#hide_ef").hide();
	        
	        
	        $("#add_admitted").hide();
	        $("#add_exami").hide();
	        $("#add_diss").hide();
	        $("#add_lec").hide();
	        $("#add_assi").hide();
	        $("#add_pre").hide();
		}
		if($("#institute_status").val() == "-1") 
		{
				$("#btn-save").hide();
		        $("#btn-reject-submit-examiners-md").show();
		        $("#btn-reject-dissertation").show();
		        $("#btn-reject-lecture").show();
		        $("#btn-reject-assignment").show();
		        $("#btn-reject-presentation").show();
	    }
   }
        document.addEventListener('DOMContentLoaded', function() {
        	
        	document.getElementById('btn-reload').onclick = function() {
        		return select_status();
        	};
        	document.getElementById('btn-save').onclick = function() {
        		return Submit_Approval_Datafor_institute_PG();
        	};
        	document.getElementById('btn-reject-submit-student-admitted').onclick = function(){
        		return Submit_Approval_Reject_Student_Admitted();
        	};
        	document.getElementById('btn-reject-submit-examiners-md').onclick = function(){
        		return Submit_Approval_Reject_Student_Examiners_MD();
        	};
        	document.getElementById('btn-reject-dissertation').onclick = function(){
        		return Submit_Approval_Reject_dissertation();
        	};
        	document.getElementById('btn-reject-lecture').onclick = function(){
        		return Submit_Approval_Reject_lecture();
        	};
        	document.getElementById('btn-reject-assignment').onclick = function(){
        		return Submit_Approval_Reject_assignment();
        	};
        	document.getElementById('btn-reject-presentation').onclick = function(){
        		return Submit_Approval_Reject_Student_presentation();
        	};
        	
        });
//----------------------VIEW-TABLE---------------------------

        function data(tablename){
        	 if(tablename=="search_system_admitted"){
        		
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
        			var institute_status= $("#institute_status").val();
        			
        			$.post("getFilter_Admitted_Student_pg_list?" + key + "=" + value, {
        				
        				startPage : startPage,
        				pageLength : pageLength,
        				Search : Search,
        				orderColunm : orderColunm,
        				orderType : orderType,
        				id:0,
        				institute_status : institute_status
        			},
        				      function(j) {
        				    	  console.log(j)
        							for (var i = 0; i < j.length; i++) {
        								
        								if($("#institute_status").val() == "1") 
        								{
        									   jsondata.push([j[i].ser, j[i].student_name,j[i].date_of_admission,j[i].date_of_registration,j[i].course_name,
                								j[i].rank,j[i].marks,j[i].all_india,j[i].state,j[i].admission_authority,j[i].court_order,j[i].qualification_name,
                								j[i].year_of_award_admission, j[i].date_of_registration_state,j[i].date_of_completion_md_part1,j[i].date_of_completion_md_part2,j[i].date_of_declaration_of_md,
                								j[i].date_of_completion_internship,j[i].remarks]);
        								}
        								else if($("#institute_status").val() == "-1") 
        								{
        									   jsondata.push([j[i].ser, j[i].student_name,j[i].date_of_admission,j[i].date_of_registration,j[i].course_name,
                								j[i].rank,j[i].marks,j[i].all_india,j[i].state,j[i].admission_authority,j[i].court_order,j[i].qualification_name,
                								j[i].year_of_award_admission, j[i].date_of_registration_state,j[i].date_of_completion_md_part1,j[i].date_of_completion_md_part2,j[i].date_of_declaration_of_md,
                								j[i].date_of_completion_internship,j[i].remarks,j[i].reject_remarks,j[i].action]);
             							}
        								else
        								{
		        								jsondata.push([j[i].ser, j[i].student_name,j[i].date_of_admission,j[i].date_of_registration,j[i].course_name,
		        								j[i].rank,j[i].marks,j[i].all_india,j[i].state,j[i].admission_authority,j[i].court_order,j[i].qualification_name,
		        								j[i].year_of_award_admission, j[i].date_of_registration_state,j[i].date_of_completion_md_part1,j[i].date_of_completion_md_part2,j[i].date_of_declaration_of_md,
		        								j[i].date_of_completion_internship,j[i].remarks,j[i].action]);
        								}
        							}
        						});
        			$.ajaxSetup({
        				async : false
        			});
        			
        			$.post("getFilter_Admitted_StudentList_pg_Count?" + key + "=" + value, {
        				id:0,
        				institute_status : institute_status
        			}, function(j) {
        				FilteredRecords = j;
        				$("#pgad_hid").val(j);
        				}).fail(function(xhr, textStatus, errorThrown, exception) {
        				  	 alert(errorThrownMsg(xhr,exception));
        			});
        			setTimeout(setTimeLoadForTable1, 1000);
        	 }
        	 
//         	 else if(tablename=="search_system_examiners_pg") {
//         		 jsondata = [];
//         			var table = $('#' + tablename).DataTable();
//         			var info = table.page.info();
//         			var pageLength = info.length;
//         			var startPage = info.start;
//         			var endPage = info.end;
//         			var Search = table.search();
//         			var order = table.order();
//         			var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
//         			var orderType = order[0][1];
        			
//         			var name_of_homoeopathic_medical_college = $("#name_of_homoeopathic_medical_college").val();
//         			var name_of_guide = $("#name_of_guide").val();
//         			var name_of_student_for_guide = $("#name_of_student_for_guide").val();
//         			var topic_of_dissertation = $("#topic_of_dissertation").val();
//         			var whether_guide_participated_in_examination = $("#whether_guide_participated_in_examination").val();
//         			var date_of_submission = $("#date_of_submission").val();
//         			var date_of_acceptance = $("#date_of_acceptance").val();
//         			var whether_student_published_article = $("#whether_student_published_article").val();
//         			var mention_details = $("#mention_details").val();
//         			var article_title = $("#article_title").val();
//         			var month_year_exam = $("#month_year_exam").val();
//         			var institute_status= $("#institute_status").val();
        			
//         			$.post("getFilter_Examiners_Student_pg_List?" + key + "=" + value, {
        				
//         				startPage : startPage,
//         				pageLength : pageLength,
//         				Search : Search,
//         				orderColunm : orderColunm,
//         				orderType : orderType,
//         				id:0,
//          				institute_status : institute_status

//         			},
//         				      function(j) {
//         				    	  console.log(j)
//         							for (var i = 0; i < j.length; i++) {
        						
//         				    	  if($("#institute_status").val() == "1") 
//   								  {
//         				    		  jsondata.push([j[i].ser,j[i].name_of_homoeopathic_medical_college,j[i].name_of_guide,
//       									j[i].name_of_student_for_guide, j[i].topic_of_dissertation, j[i].whether_guide_participated_in_examination,
//       									j[i].date_of_submission,j[i].date_of_acceptance,j[i].whether_student_published_article,j[i].mention_details,
//       									j[i].article_title,j[i].month_year_exam]);
//   								 }
// 	  								else if($("#institute_status").val() == "-1") 
// 	  								{
// 	  									jsondata.push([j[i].ser,j[i].name_of_homoeopathic_medical_college,j[i].name_of_guide,
// 	    									j[i].name_of_student_for_guide, j[i].topic_of_dissertation, j[i].whether_guide_participated_in_examination,
// 	    									j[i].date_of_submission,j[i].date_of_acceptance,j[i].whether_student_published_article,j[i].mention_details,
// 	    									j[i].article_title,j[i].month_year_exam,j[i].reject_remarks,j[i].action]);
// 	       							}
// 	  								else
// 	  								{
// 	  									jsondata.push([j[i].ser,j[i].name_of_homoeopathic_medical_college,j[i].name_of_guide,
// 	    									j[i].name_of_student_for_guide, j[i].topic_of_dissertation, j[i].whether_guide_participated_in_examination,
// 	    									j[i].date_of_submission,j[i].date_of_acceptance,j[i].whether_student_published_article,j[i].mention_details,
// 	    									j[i].article_title,j[i].month_year_exam,j[i].action]);
// 	  								}
//         							}
//         						});
//         			$.ajaxSetup({
//         				async : false
//         			});
        			
//         			$.post("getFilter_Examiners_Student_pgListCount?" + key + "=" + value, {
//         				id:0,
//         				institute_status : institute_status
//         			}, function(j) {
//         				FilteredRecords = j;
//         				$("#pgex_hid").val(j);
//         				}).fail(function(xhr, textStatus, errorThrown, exception) {
//         				  	 alert(errorThrownMsg(xhr,exception));
//         			});
//         			setTimeout(setTimeLoadForTable2, 1000);
//         	 }
        	 else if(tablename=="search_system_disseration_pg") {
         		
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
         			$.post("getFilter_Dissertation_Student_pg_List?" + key + "=" + value, {
         				
         				startPage : startPage,
         				pageLength : pageLength,
         				Search : Search,
         				orderColunm : orderColunm,
         				orderType : orderType,
         				id:0,
         				institute_status : institute_status

         			},
         				      function(j) {
         				    	  console.log(j)
         							for (var i = 0; i < j.length; i++) {
         								
         								 if($("#institute_status").val() == "1") 
         								  {
         									jsondata.push([j[i].ser,j[i].student_name_pg,j[i].from_date,
             									j[i].to_date, j[i].name_of_topic, j[i].conclusion_obtain,
             									j[i].date_of_submission_pg,j[i].publication,j[i].mention_article]);
         								 }
       	  								else if($("#institute_status").val() == "-1") 
       	  								{
       	  								jsondata.push([j[i].ser,j[i].student_name_pg,j[i].from_date,
         									j[i].to_date, j[i].name_of_topic, j[i].conclusion_obtain,
         									j[i].date_of_submission_pg,j[i].publication,j[i].mention_article,j[i].reject_remarks,
         									j[i].action]);
       	       							}
       	  								else
       	  								{
       	  								jsondata.push([j[i].ser,j[i].student_name_pg,j[i].from_date,
         									j[i].to_date, j[i].name_of_topic, j[i].conclusion_obtain,
         									j[i].date_of_submission_pg,j[i].publication,j[i].mention_article,
         									j[i].action]);
       	  								}
         							}
         						});
         			$.ajaxSetup({
         				async : false
         			});
         			
         			$.post("getFilter_Dissertation_Student_pgListCount?" + key + "=" + value, {
         				id:0,
         				institute_status : institute_status
         			}, function(j) {
         				FilteredRecords = j;
         				$("#pgdis_hid").val(j);
         				}).fail(function(xhr, textStatus, errorThrown, exception) {
         				  	 alert(errorThrownMsg(xhr,exception));
         			});
         			setTimeout(setTimeLoadForTable3, 1000);
         	 }
        	 else if(tablename=="search_system_lecture_pg") {
          		
          	
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
          			$.post("getFilter_Lecture_Student_pg_List?" + key + "=" + value, {
          				
          				startPage : startPage,
          				pageLength : pageLength,
          				Search : Search,
          				orderColunm : orderColunm,
          				orderType : orderType,
          				id:0,
         				institute_status : institute_status

          			},
          				      function(j) {
          				    	  console.log(j)
          							for (var i = 0; i < j.length; i++) {
          								
          								if($("#institute_status").val() == "1") 
       								  {
          									jsondata.push([j[i].ser,j[i].student_name,j[i].student_class,
              									j[i].lecture_date,j[i].lecture_day,j[i].lecture_time,
              									j[i].topic]);
       								 }
     	  								else if($("#institute_status").val() == "-1") 
     	  								{
     	  									jsondata.push([j[i].ser,j[i].student_name,j[i].student_class,
              									j[i].lecture_date,j[i].lecture_day,j[i].lecture_time,
              									j[i].topic,j[i].reject_remarks,j[i].action]);
     	       							}
     	  								else
     	  								{
     	  									jsondata.push([j[i].ser,j[i].student_name,j[i].student_class,
              									j[i].lecture_date,j[i].lecture_day,j[i].lecture_time,
              									j[i].topic,j[i].action]);
     	  								}
          								
          							}
          						});
          			$.ajaxSetup({
          				async : false
          			});
          			
          			$.post("getFilter_Lecture_Student_pgListCount?" + key + "=" + value, {
          				id:0,
          				institute_status : institute_status
          			}, function(j) {
          				FilteredRecords = j;
          				$("#pglec_hid").val(j);
          				}).fail(function(xhr, textStatus, errorThrown, exception) {
          				  	 alert(errorThrownMsg(xhr,exception));
          			});
          			setTimeout(setTimeLoadForTable4, 1000);
          	 }
//         	 else if(tablename=="search_system_assignment_pg") {
//           		 jsondata = [];
//           			var table = $('#' + tablename).DataTable();
//           			var info = table.page.info();
//           			var pageLength = info.length;
//           			var startPage = info.start;
//           			var endPage = info.end;
//           			var Search = table.search();
//           			var order = table.order();
//           			var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
//           			var orderType = order[0][1];
          			
//           			var student_name_formc = $("#student_name_formc").val();
//           			var list_of_assignment_formc = $("#list_of_assignment_formc").val();
//           			var institute_status= $("#institute_status").val();          			
//           			$.post("getFilter_assignment_pg_List?" + key + "=" + value, {
          				
//           				startPage : startPage,
//           				pageLength : pageLength,
//           				Search : Search,
//           				orderColunm : orderColunm,
//           				orderType : orderType,
//           				id:0,
//          				institute_status : institute_status

//           			},
//           				      function(j) {
//           				    	  console.log(j)
//           							for (var i = 0; i < j.length; i++) {
          								
//           								if($("#institute_status").val() == "1") 
//          								 {
//           									jsondata.push([j[i].ser,j[i].student_name_formc,j[i].list_of_assignment_formc]);
//          								 }
//        	  								else if($("#institute_status").val() == "-1") 
//        	  								{
//        	  								jsondata.push([j[i].ser,j[i].student_name_formc,j[i].list_of_assignment_formc,j[i].reject_remarks,j[i].action]);
//        	       							}
//        	  								else
//        	  								{
//        	  								jsondata.push([j[i].ser,j[i].student_name_formc,j[i].list_of_assignment_formc,j[i].action]);
//        	  								}
//           							}
//           						});
//           			$.ajaxSetup({
//           				async : false
//           			});
          			
//           			$.post("getFilter_assignment_pgListCount?" + key + "=" + value, {
//           				id:0,
//           				institute_status : institute_status
//           			}, function(j) {
//           				FilteredRecords = j;
//           				$("#pgassi_hid").val(j);
//           				}).fail(function(xhr, textStatus, errorThrown, exception) {
//           				  	 alert(errorThrownMsg(xhr,exception));
//           			});
//           			setTimeout(setTimeLoadForTable5, 1000);
//           	 }
        	 else if(tablename=="search_system_presentation_pg") {
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
          			$.post("getFilter_presentation_pg_List?" + key + "=" + value, {
          				
          				startPage : startPage,
          				pageLength : pageLength,
          				Search : Search,
          				orderColunm : orderColunm,
          				orderType : orderType,
          				id:0,
         				institute_status : institute_status

          			},
          				      function(j) {
          				    	  console.log(j)
          							for (var i = 0; i < j.length; i++) {
          								
          								if($("#institute_status").val() == "1") 
        								 {
              								jsondata.push([j[i].ser,j[i].student_name_presen,j[i].list_of_presentations,j[i].title_of_seminar_attended]);
        								 }
      	  								else if($("#institute_status").val() == "-1") 
      	  								{
              								jsondata.push([j[i].ser,j[i].student_name_presen,j[i].list_of_presentations,j[i].title_of_seminar_attended,j[i].reject_remarks,j[i].action]);
      	       							}
      	  								else
      	  								{
              								jsondata.push([j[i].ser,j[i].student_name_presen,j[i].list_of_presentations,j[i].title_of_seminar_attended,j[i].action]);
      	  								}
          								
          							}
          						});
          			$.ajaxSetup({
          				async : false
          			});
          			
          			$.post("getFilter_presentation_pgListCount?" + key + "=" + value, {
          				id:0,
          				institute_status : institute_status
          			}, function(j) {
          				FilteredRecords = j;
          				$("#pgpre_hid").val(j);
          				}).fail(function(xhr, textStatus, errorThrown, exception) {
          				  	 alert(errorThrownMsg(xhr,exception));
          			});
          			setTimeout(setTimeLoadForTable6, 1000);
          	 }
}

 

///////////// Admitted /////////////////

function editData(id) {
	document.getElementById('eid').value=id;	
	document.getElementById('updateForm').submit();
}

function setTimeLoadForTable1(){
document.querySelectorAll('.ADDStudent').forEach((items, index) => {
	items.addEventListener('click', event => {
		
		var val=parseInt(index)+1;
		var hid = document.getElementById('apIdAGE'+val).value;
		if (confirm('Are You Sure You Want to Edit Detail ?')) {
			editData(hid);
		} else {
			return false;
		}
	})
});
}

///////////// Examiners /////////////////

// function editDataExaminerspg(id) {
// 	document.getElementById('expg_id').value=id;	
// 	document.getElementById('updateExaminerspgForm').submit();
// }

// function setTimeLoadForTable2(){
// 	document.querySelectorAll('.ADDExaminerspg').forEach((items, index) => {
// 		items.addEventListener('click', event => {
			
// 			var val=parseInt(index)+1;
// 			var hid = document.getElementById('apIdExaminpg'+val).value;
// 			if (confirm('Are You Sure You Want to Edit Detail ?')) {
// 				editDataExaminerspg(hid);
// 			} else {
// 				return false;
// 			}
// 		})
// 	});
// }
///////////// Dissertation /////////////////

function setTimeLoadForTable3(){
	document.querySelectorAll('.ADDDissertation').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdDissertation'+val).value;
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				editData2(hid);
			} else {
				return false;
			}
		})
	});
}

function editData2(id){
	
	$("#headData").empty();
	 
		 $.post('getdissertationdatafrom?'+key+"="+value, {
			 id:id
			}).done(function(j) {
								
				var options = '<h4 class="heading">Disseratation Topic of P.G. Student-Year wise/Depratment wise</h4>'
				+'<div class="row"><div class="table-wrapper table-responsive custom-table">'
				+'<table class="table" id="family_table"><thead>'
				+'<tr>'
				+'<th rowspan="2"><h6>S.No</h6></th>'
				+'<th rowspan="2"><h6>Name of student</h6></th>'
				+'<th colspan="2"><h6>Duration of house job Dates</h6></th>'
				+'<th rowspan="2"><h6>Name of Topic</h6></th>'
				+'<th rowspan="2"><h6>Outcomes/Result/Conclusion obtained from disseration</h6></th>'
				+'<th rowspan="2"><h6>Date of submission</h6></th>'
				+'<th rowspan="2"><h6>Any publication from Disseration(Yes/No)</h6></th>'
				+'<th rowspan="2"><h6>If yes,mention(article title/journal)</h6></th>'
				+'</tr>'
				+'<tr>'
				+'<th><h6>From date</h6></th>'
				+'<th><h6>To date</h6></th>'
				+'</tr>'
				+'</thead>'
				+'<tbody id="family_sibtbody">'
				+'<tr id="tr_sibling1">'
				+'<td>1</td>'
				
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="student_name_pg" id="student_name_pg" class="form-control" placeholder="Enter student name">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="from_date" id="from_date" maxlength="10" onclick="clickclear(this, "DD/MM/YYYY")"'
				+'class="form-control" style="width: 85%; display: inline;"'
				+'onfocus="this.style.color="#000000""'
				+'onblur="clickrecall(this,"DD/MM/YYYY");validateDate_BackDate(this.value,this);"'
				+'onkeyup="clickclear(this, "DD/MM/YYYY")"'
				+'onchange="clickrecall(this,"DD/MM/YYYY");validateDate_FutureDate(this.value,this);"'
				+'aria-required="true" autocomplete="off"'
				+'style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="to_date" id="to_date" maxlength="10" onclick="clickclear(this, "DD/MM/YYYY")"'
				+'class="form-control" style="width: 85%; display: inline;"'
				+'onfocus="this.style.color="#000000""'
				+'onblur="clickrecall(this,"DD/MM/YYYY");validateDate_BackDate(this.value,this);"'
				+'onkeyup="clickclear(this, "DD/MM/YYYY")"'
				+'onchange="clickrecall(this,"DD/MM/YYYY");validateDate_FutureDate(this.value,this);"'
				+'aria-required="true" autocomplete="off"'
				+'style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="name_of_topic" id="name_of_topic" maxlength="10" class="form-control" aria-required="true" autocomplete="off">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="conclusion_obtain" id="conclusion_obtain" class="form-control" placeholder="Enter University Enrollment Number" onkeypress="return isNumber(event)">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="date_of_submission_pg" id="date_of_submission_pg" maxlength="10" onclick="clickclear(this, "DD/MM/YYYY")"'
				+'class="form-control" style="width: 85%; display: inline;"'
				+'onfocus="this.style.color="#000000""'
				+'onblur="clickrecall(this,"DD/MM/YYYY");validateDate_BackDate(this.value,this);"'
				+'onkeyup="clickclear(this, "DD/MM/YYYY")"'
				+'onchange="clickrecall(this,"DD/MM/YYYY");validateDate_FutureDate(this.value,this);"'
				+'aria-required="true" autocomplete="off"'
				+'style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="publication" id="publication" class="form-control" placeholder="Enter Remarks">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="mention_article" id="mention_article" class="form-control" placeholder="Enter Remarks">'
				+'<input type="hidden" name="disse_id" id="disse_id" class="form-control">'
				+'</div></div></td>'
				+'</tr></tbody></table></div></div>'
				+'<div class="col-lg-12 col-md-12 col-sm-12">'
				+'<ul class="buttons-group mainbtn">'
				+'<li><input type="button" id="btn-update" class="main-btn info-btn btn-hover" value="Update" onclick="UpdateDisseratation();">'
				+'</ul>'
			    +'</div>';
				
				$("#headData").append(options);
				$("#student_name_pg").val(j[0].student_name_pg);
				$("#from_date").val(j[0].from_date);
				$("#to_date").val(j[0].to_date);
				$("#name_of_topic").val(j[0].name_of_topic);
				$("#conclusion_obtain").val(j[0].conclusion_obtain);
				$("#date_of_submission_pg").val(j[0].date_of_submission_pg);
				$("#publication").val(j[0].publication);
				$("#mention_article").val(j[0].mention_article);
				$("#disse_id").val(j[0].id);
				
			    datepicketDate('from_date');
			    datepicketDate('to_date');
			    datepicketDate('date_of_submission_pg');
			    
		  });
	}
	
function UpdateDisseratation(){
	
	  if($("select#student_name_pg").val() == "0") 
	  {
    	 alert("Please Select Student Name");
    	 $("select#student_name_pg").focus();
    	 return false;
	  }
      if($("input#from_date").val() == "DD/MM/YYYY" || $("input#from_date").val() == ""  ) 
      {
 		alert("Please Select From Date");
 		$("input#from_date").focus();
 		return false;
      }
      if($("input#to_date").val() == "DD/MM/YYYY" || $("input#to_date").val() == ""  ) 
      {
   		alert("Please Select To Date");
   		$("input#to_date").focus();
   		return false;
      }
      if($("input#name_of_topic").val() == "") {
         alert("Please Enter Name Of Topic");
         $("input#name_of_topic").focus();
         return false;
      }
      if($("input#conclusion_obtain").val() == "") {
          alert("Please Enter Conclusion Obtain");
         $("input#conclusion_obtain").focus();
         return false;
      }
      if($("input#date_of_submission_pg").val() == "DD/MM/YYYY" || $("input#date_of_submission_pg").val() == ""  ) 
      {
 		alert("Please Select Date Of Submission");
 		$("input#date_of_submission_pg").focus();
 		return false;
      }
      if($("input#publication").val() == "") 
      {
       alert("Please Enter Publication");
       $("input#publication").focus();
       return false;
      }
      if($("input#mention_article").val() == "") 
      {
       alert("Please Enter Mention Article");
       $("input#mention_article").focus();
       return false;
      }
    
var  student_name_pg = $("#student_name_pg").val();
var  from_date = $("#from_date").val();
var  to_date = $("#to_date").val();
var  name_of_topic = $("#name_of_topic").val();
var  conclusion_obtain = $("#conclusion_obtain").val();
var  date_of_submission_pg = $("#date_of_submission_pg").val();
var  publication = $("#publication").val();
var  mention_article = $("#mention_article").val();
var  disse_id = $("#disse_id").val();

$.post("edit_dissertation_Action?" + key + "=" + value, {
	student_name_pg : student_name_pg,
	from_date : from_date,
	to_date : to_date,
	name_of_topic : name_of_topic,
	conclusion_obtain : conclusion_obtain,
	date_of_submission_pg : date_of_submission_pg,
	publication : publication,
	mention_article : mention_article,
	disse_id : disse_id
	}, function(j) {
		alert(j);
		$("#btn-close").click();
		location.reload();
	});

}

///////////// Lecture Taken /////////////////

function setTimeLoadForTable4(){
	document.querySelectorAll('.ADDlecture').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdlecture'+val).value;
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				editData3(hid);
			} else {
				return false;
			}
		})
	});
}

function editData3(id){
	
	$("#headData").empty();
	 
		 $.post('getlacturetakenpgdata?'+key+"="+value, {
			 id:id
			}).done(function(j) {
								
				var options = '<h4 class="heading">Lecture Taken By P.G.Students,Batch</h4>'
				+'<div class="row"><div class="table-wrapper table-responsive custom-table">'
				+'<table class="table" id="att_Tbb"><thead>'
				+'<tr>'
				+'<th><h6>S.No</h6></th>'
				+'<th><h6>Name of student</h6></th>'
				+'<th><h6>Class</h6></th>'
				+'<th><h6>Date</h6></th>'
				+'<th><h6>Day</h6></th>'
				+'<th><h6>Time</h6></th>'
				+'<th><h6>Topic</h6></th>'
				+'</tr>'
				+'</thead>'
				+'<tbody id="att_Tbbody">'
				+'<tr id="tr_sibling2">'
				+'<td>1</td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="student_name" id="student_name" class="form-control" placeholder="Enter student name">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="student_class" id="student_class">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="lecture_date" id="lecture_date" maxlength="10" onclick="clickclear(this, "DD/MM/YYYY")"'
				+'class="form-control" style="width: 85%; display: inline;"'
				+'style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="lecture_day" id="lecture_day" maxlength="10" class="form-control" aria-required="true" autocomplete="off">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="lecture_time" id="lecture_time" class="form-control" placeholder="Enter Lecture Time" onkeypress="return isNumber(event)">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="topic" id="topic" class="form-control" placeholder="Enter Lecture Topic">'
				+'<input type="hidden" name="lt_id" id="lt_id" class="form-control">'
				+'</div></div></td>'
				+'</tr></tbody></table></div></div>'
				+'<div class="col-lg-12 col-md-12 col-sm-12">'
				+'<ul class="buttons-group mainbtn">'
				+'<li><input type="button" id="btn-update" class="main-btn info-btn btn-hover" value="Update" onclick="UpdateLecture();">'
				+'</ul>'
			    +'</div>';
				
				$("#headData").append(options);
				$("#student_name").val(j[0].student_name);
				$("#student_class").val(j[0].student_class);
				$("#lecture_date").val(j[0].lecture_date);
				$("#lecture_day").val(j[0].lecture_day);
				$("#lecture_time").val(j[0].lecture_time);
				$("#date_of_submission_pg").val(j[0].date_of_submission_pg);
				$("#topic").val(j[0].topic);
				$("#lt_id").val(j[0].id);
				
			    datepicketDate('lecture_date');
			  
			    
		  });
	}
	
function UpdateLecture(){
	
	  if($("select#student_name").val() == "0") 
	  {
    	 alert("Please Select Student Name");
    	 $("select#student_name").focus();
    	 return false;
	  }
      if($("input#student_class").val() == "") {
         alert("Please Enter student Class");
         $("input#student_class").focus();
         return false;
      }
      if($("input#lecture_date").val() == "DD/MM/YYYY" || $("input#lecture_date").val() == ""  ) 
      {
 		alert("Please Select Lecture Date");
 		$("input#lecture_date").focus();
 		return false;
      }
      if($("input#lecture_day").val() == "") 
      {
       alert("Please Enter Lecture Day");
       $("input#lecture_day").focus();
       return false;
      }
      if($("input#lecture_time").val() == "") 
      {
       alert("Please Enter Lecture Time");
       $("input#lecture_time").focus();
       return false;
      }
      if($("input#topic").val() == "") 
      {
       alert("Please Enter Topic");
       $("input#topic").focus();
       return false;
      }
    
var  student_name = $("#student_name").val();
var  student_class = $("#student_class").val();
var  lecture_date = $("#lecture_date").val();
var  lecture_day = $("#lecture_day").val();
var  lecture_time = $("#lecture_time").val();
var  topic = $("#topic").val();
var  lt_id = $("#lt_id").val();

$.post("edit_lacture_taken_Action_PG?" + key + "=" + value, {
	student_name : student_name,
	student_class : student_class,
	lecture_date : lecture_date,
	lecture_day : lecture_day,
	lecture_time : lecture_time,
	topic : topic,
	lt_id :lt_id
	}, function(j) {
		alert(j);
		$("#btn-close").click();
		location.reload();
	});

}
// Assignment //

// function setTimeLoadForTable5(){
// 	document.querySelectorAll('.ADDAssignment').forEach((items, index) => {
// 		items.addEventListener('click', event => {
			
// 			var val=parseInt(index)+1;
// 			var hid = document.getElementById('apIdAss'+val).value;
// 			if (confirm('Are You Sure You Want to Edit Detail ?')) {
// 				editData4(hid);
// 			} else {
// 				return false;
// 			}
// 		})
// 	});
// }

// function editData4(id){
	
// 	$("#headData").empty();
	 
// 		 $.post('getassignmentpgdata?'+key+"="+value, {
// 			 id:id
// 			}).done(function(j) {
// 								alert("--jjj")
// 				var options = '<h4 class="heading">Assignment Of P.G. Student</h4>'
// 				+'<div class="row"><div class="table-wrapper table-responsive custom-table">'
// 				+'<table class="table" id="family_table_c"><thead>'
// 				+'<tr>'
// 				+'<th><h6>S.No</h6></th>'
// 				+'<th><h6>Name of student</h6></th>'
// 				+'<th><h6>List Of Assignment</h6></th>'
// 				+'</tr>'
// 				+'</thead>'
// 				+'<tbody id="att_assign">'
// 				+'<tr id="tr_sibling">'
// 				+'<td>1</td>'
// 				+'<td><div class=""><div class="input-style-2">'
// 				+'<input type="text" name="student_name_formc" id="student_name_formc" class="form-control" placeholder="Enter student name">'
// 				+'</div></div></td>'
// 				+'<td><div class=""><div class="input-style-2">'
// 				+'<input type="text" name="list_of_assignment_formc" id="list_of_assignment_formc">'
// 				+'<input type="hidden" name="ass_id" id="ass_id" class="form-control">'
// 				+'</div></div></td>'
// 				+'</tr></tbody></table></div></div>'
// 				+'<div class="col-lg-12 col-md-12 col-sm-12">'
// 				+'<ul class="buttons-group mainbtn">'
// 				+'<li><input type="button" id="btn-update" class="main-btn info-btn btn-hover" value="Update" onclick="UpdateAssign();">'
// 				+'</ul>'
// 			    +'</div>';
				
// 				$("#headData").append(options);
// 				$("#student_name_formc").val(j[0].student_name_formc);
// 				$("#list_of_assignment_formc").val(j[0].list_of_assignment_formc);
// 				$("#ass_id").val(j[0].id);
						    
// 		  });
// 	}
	
function UpdateAssign(){
	
	  if($("select#student_name_formc").val() == "0") 
	  {
    	 alert("Please Select Student Name");
    	 $("select#student_name_formc").focus();
    	 return false;
	  }
      if($("input#list_of_assignment_formc").val() == "") {
         alert("Please Enter List Of Assignmnet");
         $("input#list_of_assignment_formc").focus();
         return false;
      }
      
    
var  student_name_formc = $("#student_name_formc").val();
var  list_of_assignment_formc = $("#list_of_assignment_formc").val();
var  ass_id = $("#ass_id").val();

$.post("edit_assignment_Action_PG?" + key + "=" + value, {
	student_name_formc : student_name_formc,
	list_of_assignment_formc : list_of_assignment_formc,
	ass_id :ass_id
	}, function(j) {
		alert(j);
		$("#btn-close").click();
		location.reload();
	});

}

// Presentation //

function setTimeLoadForTable6(){
	document.querySelectorAll('.ADDPresentation').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdPres'+val).value;
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				editDataPresentation(hid);
			} else {
				return false;
			}
		})
	});
}

function editDataPresentation(id){
// 	alert("oooo----");
	$("#headData").empty();
// 	alert("oooo---hhhh-");
		 $.post('getpresentationpgdata?'+key+"="+value, {
			 id:id
			}).done(function(j) {
// 				alert("oooo---ccccc-");		
				var options = '<h4 class="heading">Presentation</h4>'
					+'<span class="mandatory" >(All fields are mandatory)</span>'
					+'<div class="row"><div class="table-wrapper table-responsive simple-table">'
					+'<table class="table" id="family_table_from"><thead>'
					+'<tr>'
					+'<th><h6>S.No</h6></th>'
					+'<th><h6>Name of student</h6></th>'
					+'<th><h6>List Of Presentation</h6></th>'
					+'<th><h6>Title Of The Seminar Attended</h6></th>'
					+'</tr>'
					+'</thead>'
					+'<tbody id="family_sibtbody">'
					+'<tr id="tr_sibling4">'
					+'<td>1</td>'
					+'<td><div class=""><div class="input-style-2">'
					+'<input type="text" name="student_name_presen" id="student_name_presen" class="form-control" placeholder="Enter student name">'
					+'</div></div></td>'
					+'<td><div class=""><div class="input-style-2">'
					+'<input type="text" name="list_of_presentations" id="list_of_presentations" class="form-control" placeholder="Enter List Of Assignment">'
					+'<input type="hidden" name="pre_id" id="pre_id" class="form-control">'
					+'</div></div></td>'
					+'<td><div class=""><div class="input-style-2">'
					+'<input type="text" name="title_of_seminar_attended" id="title_of_seminar_attended" class="form-control" placeholder="Enter student name">'
					+'</div></div></td>'
					+'</tr></tbody></table></div></div>'
					+'<div class="col-lg-12 col-md-12 col-sm-12">'
					+'<ul class="buttons-group mainbtn">'
					+'<li><input type="button" id="btn-update" class="main-btn info-btn btn-hover" value="Update" onclick="UpdatePresentation();">'
					+'</ul>'
				    +'</div>';
				
				$("#headData").append(options);
				$("#student_name_presen").val(j[0].student_name_presen);
				$("#list_of_presentations").val(j[0].list_of_presentations);
				$("#title_of_seminar_attended").val(j[0].title_of_seminar_attended);
				$("#pre_id").val(j[0].id);
				
			    
		  });
	}

function UpdatePresentation(){
	
		  if($("select#student_name_presen").val() == "0") {
			 alert("Please Enter Student Name");
			 $("select#student_name_presen").focus();
			 return false;
		  }
		  if($("input#list_of_presentations").val() == "") {
			alert("Please Enter List Of Presentation");
			$("input#list_of_presentations").focus();
			return false;
		  }
		  if($("input#title_of_seminar_attended").val() == "") {
				alert("Please Enter Title Of Seminar Attended");
				$("input#title_of_seminar_attended").focus();
				return false;
		}
		  
var  student_name_presen = $("#student_name_presen").val();
var  list_of_presentations = $("#list_of_presentations").val();
var  title_of_seminar_attended = $("#title_of_seminar_attended").val();
var pre_id = $("#pre_id").val();

$.post("edit_presentation_Action_PG?" + key + "=" + value, {
	student_name_presen : student_name_presen,
	list_of_presentations : list_of_presentations,
	title_of_seminar_attended : title_of_seminar_attended,
	pre_id : pre_id
	}, function(j) {
		alert(j);
		$("#btn-close").click();
		location.reload();
	});

}

function Submit_Approval_Datafor_institute_PG(){
 	var pgad_hid = $("#pgad_hid").val();
 	var pgex_hid = $("#pgex_hid").val();
 	var pgdis_hid = $("#pgdis_hid").val();
	var pglec_hid = $("#pglec_hid").val();
	var pgassi_hid = $("#pgassi_hid").val();
	var pgpre_hid = $("#pgpre_hid").val();
		$.post("Submit_Approval_Data_institute_pg?" +  key + "=" + value,{pgad_hid:pgad_hid,pgex_hid:pgex_hid,pgdis_hid:pgdis_hid,
			pglec_hid:pglec_hid,pgassi_hid:pgassi_hid,pgpre_hid:pgpre_hid},function(data) {
			alert(data);
			location.reload();
	}).fail(function(xhr, textStatus, errorThrown) {
     alert("fail to fetch")
    });
}




//////////////////////////Admitted Student Re-Approval ///////////////////////////////////

function Submit_Approval_Reject_Student_Admitted(){
	
	$.post("Reject_Data_institute_student_admitted?" +  key + "=" + value, function(data) {
		
		alert(data);
		location.reload();

}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
}

//////////////////////////Examiners-MD Student Re-Approval ///////////////////////////////////

function Submit_Approval_Reject_Student_Examiners_MD(){
	
	$.post("Reject_Data_institute_student_exami_md?" +  key + "=" + value, function(data) {
		
		alert(data);
		location.reload();

}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
}

//////////////////////////Dissertation Student Re-Approval ///////////////////////////////////

function Submit_Approval_Reject_dissertation(){
	
	$.post("Reject_Data_institute_dissertation?" +  key + "=" + value, function(data) {
		
		alert(data);
		location.reload();

}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
}

//////////////////////////Lecture Student Re-Approval ///////////////////////////////////

function Submit_Approval_Reject_lecture(){
	
	$.post("Reject_Data_institute_lecture?" +  key + "=" + value, function(data) {
		
		alert(data);
		location.reload();

}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
}

//////////////////////////Assignment Student Re-Approval ///////////////////////////////////

function Submit_Approval_Reject_assignment(){
	
	$.post("Reject_Data_institute_assignment?" +  key + "=" + value, function(data) {
		
		alert(data);
		location.reload();

}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
}


//////////////////////////Presentation Student Re-Approval ///////////////////////////////////

function Submit_Approval_Reject_Student_presentation(){
	
	$.post("Reject_Data_institute_presentation?" +  key + "=" + value, function(data) {
		
		alert(data);
		location.reload();

}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
}


</script>