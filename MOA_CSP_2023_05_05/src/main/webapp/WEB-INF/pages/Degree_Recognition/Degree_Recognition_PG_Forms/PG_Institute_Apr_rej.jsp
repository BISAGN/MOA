<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <script type="text/javascript" src="assets/js/jquery-3.6.0.min.js"></script> -->
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript"	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<link rel="stylesheet"
	href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/amin_module/webmaster/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">

<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/new_js/jquery.dataTables.min.js"></script> -->
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/new_js/jquery.mockjax.js"></script> -->

<section class="search_regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>Approved Institute Student Details</h2>
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
							 <div class="tab tablinks" id="tab_id7" >
								<button class="tab-toggle">First Choose The Institute</button>
							</div>
							<div class="content tabcontent" id="Form_z">
									<h4 class="heading">Choose The Institute</h4>
									<div class="inst_block mb-10">
										<h6 class="mb-2">Instruction</h6>
								<ul class="inst_list">
									<li><p class="inst_text">Press last tab to Approve all the form details</p></li>
								</ul>
                               </div>
									<div class="row">
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="select-style-1">
													<label>Select Institute</label><!-- <strong class="mandatory">*</strong> -->
													<div class="select-position">
														<select name="institute_id" id="institute_id" class="singleselect form-control form-control-lg">
															<option value="0">---Select Institute---</option>
															<c:forEach var="item" items="${getInstituteListbyUserID}" varStatus="num">
																<option value="${item.userid}" name="${item.institute_name}">${item.institute_name}</option>
															</c:forEach>
														</select>
													</div>
												</div>
										</div>
									<div class="col-lg-4 col-md-6 col-sm-12">
               									 <div class="select-style-1">
									                  <label>University Status<strong class="mandatory">*</strong></label>
									                  <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off"><div class="select-position">
									                  <select name="institute_status" id="institute_status" class="singleselect form-control form-control-lg">						 
															<option value="">---Select Status---</option>
<!-- 															<option value="0">Draft</option> -->
															<option value="0">Pending</option>
															<option value="1">Approved</option>
															<option value="-1">Rejected</option>
														</select></div>
										         </div>
								</div>
										</div>
										<ul class="buttons-group mainbtn">
												<li><a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button">
												<i class="lni lni-search-alt"></i>Search Details</a></li>
												<li>
											</ul>
							</div>
							 <div class="tab tablinks" id="tab_id1" style="display: none;">
								<button class="tab-toggle">Students Admitted As regular Candidate(Year And Subject Wise) For M.D.(HOM)</button>
							</div> 
							
							<div class="content tabcontent" id="Form_B">
							<form id="Form_B_details">
							<div class="tables-wrapper">
								<div class="row">
									<div class="col-lg-12">
											<input type="hidden" name="review_id1" id="review_id1" value="${review_id}"/>
                						<div class="card-style mb-30">
                						<div class="table-wrapper custom-datatable-p">
                  							<table class="table" id="search_system_admitted_pg">
                      									<thead>
                       										 <tr>
										                        <th><h6>Ser No</h6></th>
										                        <th><h6>Login Institute</h6></th>
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
														        <th><h6>Action</h6></th>
                       								 		</tr>
                      									</thead>
                      					 			<tbody class="custom-datatablepra"></tbody>
                   							 		</table>
                  						</div></div>
                 				 </div>	
							</div>
							</div>
							</form>
							</div>

																<!-- Form C start -->
 							<div class="tab tablinks" id="tab_id2" style="display: none;">
								<button class="tab-toggle">Details Of Guides And Examiners For M.D.(HOM) Course/Any Other Post Graduate Course</button>
							</div> 
							
							<div class="content tabcontent" id="Form_C">
							<form id="Form_C_details">
							<div class="tables-wrapper">
								<div class="row">
									<div class="col-lg-12">
                						<div class="card-style mb-30">
                						<div class="table-wrapper custom-datatable-p">
                  							<table class="table" id="search_system_examiner_pg">
                      									<thead>
                       										 <tr>
										                        <th><h6>Ser No</h6></th>
										                         <th><h6>Login Institute</h6></th>
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
														        <th><h6>Action</h6></th>
		                       								 </tr>
                      									</thead>
                      					 			<tbody class="custom-datatablepra"></tbody>
                   							 		</table>
                  						</div></div>
                 				 </div>	
							</div>
							</div>
							</form>
							</div>
							
							<!-- Start PG FORM WORK DONE PG ADD MORE  -->
							
							<div class="tab tablinks" id="tab_id3" style="display: none;">
								<button class="tab-toggle">Work Done by Post Graduate
									Students</button>
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

									<!-- Form PG(3)(a) -->

						<div id="content" class="tab-content" role="tablist">
						
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
																	<input type="hidden" id="pgdis_hid" name="pgdis_hid"/>
																	<table class="table" id="search_system_disseration_pg">
																		<thead>
																			<tr>
																				<th rowspan="2"><h6>Ser No</h6></th>
																				<th rowspan="2"><h6>Login Institute</h6></th>
																				<th rowspan="2"><h6>Name of student</h6></th>
																				<th colspan="2"><h6>Duration of house job Dates</h6></th>
																				<th rowspan="2"><h6>Name of Topic</h6></th>
																				<th rowspan="2"><h6>Outcomes/Result/Conclusion obtained from disseration</h6></th>
																				<th rowspan="2"><h6>Date of submission</h6></th>
																				<th rowspan="2"><h6>Any publication from Disseration(Yes/No)</h6></th>
																				<th rowspan="2"><h6>If yes,mention(article title/journal)</h6></th>
																				<th rowspan="2"><h6>Action</h6></th>
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
										<form id="Form_PG3b_details">
											<h4 class="heading mt-3">Lecture Taken</h4>
												<div class="tables-wrapper">
													<div class="row">
														<div class="col-lg-12">
                											<div class="card-style mb-30">
																<div class="table-wrapper custom-datatable-p">
																	<input type="hidden" id="pglec_hid" name="pglec_hid"/>
																	<table class="table" id="search_system_lecture_pg">
																		<thead>
																			<tr>
																				<th><h6>S.No</h6></th>
																				<th><h6>Login Institute</h6></th>
																				<th><h6>Name of student</h6></th>
																				<th><h6>Class</h6></th>
																				<th><h6>Date</h6></th>
																				<th><h6>Day</h6></th>
																				<th><h6>Time</h6></th>
																				<th><h6>Topic</h6></th>
																				<th><h6>Action</h6></th>
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
											<h4 class="heading mt-3">Assignment</h4>
												<div class="tables-wrapper">
													<div class="row">
														<div class="col-lg-12">
                											<div class="card-style mb-30">
																<div class="table-wrapper custom-datatable-p">
																	<input type="hidden" id="pgassi_hid" name="pgassi_hid"/>
																	<table class="table" id="search_system_assignment_pg">
																		<thead>
																			<tr>
																				<th><h6>S.No</h6></th>
																				 <th><h6>Login Institute</h6></th>
																				<th><h6>Name of student</h6></th>
																				<th><h6>List Of Assignment</h6></th>
																				<th><h6>Action</h6></th>
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
							</div><!-- End Tab Form C -->
							
							<!-- Form PG(3)(D) -->
									
							<div id="form-D" class="tab-pane fade show active" role="tabpanel" aria-labelledby="Form_3_d">
								<div id="collapse-A" class="collapse show" data-parent="#content" role="tabpanel" aria-labelledby="heading-A">
									<div id="Form_3_d">
										<form id="Form_PG3d_details">
											<h4 class="heading mt-3">Presentation</h4>
												<div class="tables-wrapper">
													<div class="row">
														<div class="col-lg-12">
                											<div class="card-style mb-30">
																<div class="table-wrapper custom-datatable-p">
																	<input type="hidden" id="pgpre_hid" name="pgpre_hid"/>
																	<table class="table" id="search_system_presentation_pg">
																		<thead>
																			<tr>
																				<th><h6>S.No</h6></th>
																				  <th><h6>Login Institute</h6></th>
																				<th><h6>Name of student</h6></th>
																				<th><h6>List Of Presentation</h6></th>
																				<th><h6>Title Of The Seminar Attended/Topic</h6></th>
																				<th><h6>Action</h6></th>
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
							
							 <div class="col-lg-12 col-md-12 col-sm-12">
											<ul class="buttons-group mainbtn">
												<li><input type="button" id="btn-save" class="main-btn success-btn  btn-hover" 
												onclick="Submit_Approval_Datafor_institute_PG();" value="Submit For Approval"></li>
											</ul>
									</div>
									
						</div><!-- End Tablist -->
								
							</div><!-- End Tab Forms Div Class -->
						</div> <!-- End Tab Forms  -->
							
				</div>
			</div>
		</div>
		<!--   </body> -->
	</div>
	</div>

	</div>
	
	<!--  The Modal   -->
 <div class="modal" id="myModal">
  <div class="modal-dialog modal-dialog-top">
    <div class="modal-content">
				<!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Clarification Data</h4>
      </div>

				<!-- Modal body -->
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
<!--  End Model -->
</section>
<script type="text/javascript" src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>

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
//     datepicketDate('date_of_admission');
//     datepicketDate('date_of_exam');
//     datepicketDate('date_of_result');
//     datepicketDate('date_of_result_final_year');
//     datepicketDate('date_of_starting_internship');
//     datepicketDate('date_of_completion_internship');
//     datepicketDate('year_of_admission');
//     datepicketDate('year_of_provisional_reg_no');
//     datepicketDate('to_migrated_date');
//     datepicketDate('mou_date');
//     datepicketDate('completion_of_course');
//     datepicketDate('d_university_appointment');

    $("#tab_id1").hide();
	$("#tab_id2").hide();
	$("#tab_id3").hide();

});
        
function select_institute(){
	
	 if($("select#institute_id").val().trim() == "0")
	 {
	       alert("Please Select Institute");
	       $("select#institute_id").focus();
	       return false;
     }
	 else if($("select#institute_status").val().trim() == "")
	 {
		 alert("Please Select University Status");
	       $("select#institute_status").focus();
	       return false;
	 }
	 
	mockjax1('search_system_admitted_pg');
	table1 = dataTable('search_system_admitted_pg');
	
	mockjax1('search_system_examiner_pg');
	table2 = dataTable('search_system_examiner_pg');
	
	mockjax1('search_system_disseration_pg');
	table3 = dataTable('search_system_disseration_pg');
	
	mockjax1('search_system_lecture_pg');
	table4 = dataTable('search_system_lecture_pg');
	
	mockjax1('search_system_assignment_pg');
	table5 = dataTable('search_system_assignment_pg');

	mockjax1('search_system_presentation_pg');
	table6 = dataTable('search_system_presentation_pg');
	
	table1.ajax.reload();
	table2.ajax.reload();
	table3.ajax.reload();
	table4.ajax.reload();
	table5.ajax.reload();
	table6.ajax.reload();
	
	$("#tab_id1").show();
	$("#tab_id2").show();
	$("#tab_id3").show();

}

document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn-reload').onclick = function() {
		return select_institute();
	};
	
	document.getElementById('btn-reject').onclick = function(){
		return Reject_pg_ins();
	};
});
      //----------------------VIEW-TABLE---------------------------
function data(tablename){
    	  
	 if(tablename=="search_system_admitted_pg"){
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
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
			$.post("getFilter_studentAdmitted_list?" + key + "=" + value, {
			
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				id:0,
				institute_id : institute_id,
				status : institute_status
			},
				      function(j) {
				    	  console.log(j)
							for (var i = 0; i < j.length; i++) {
								jsondata.push([j[i].ser,j[i].inst_name,j[i].student_name,j[i].date_of_admission,j[i].date_of_registration,j[i].course_name,
								j[i].rank,j[i].marks,j[i].all_india,j[i].state,j[i].admission_authority,j[i].court_order,j[i].qualification_name,
								j[i].year_of_award_admission, j[i].date_of_registration_state,j[i].date_of_completion_md_part1,j[i].date_of_completion_md_part2,j[i].date_of_declaration_of_md,
								j[i].date_of_completion_internship,j[i].remarks,j[i].action]);
								
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_Admitted_pg_ListCount?" + key + "=" + value, {
				id:0,
				institute_id : institute_id,
				status : institute_status
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
			setTimeout(setTimeLoadForTable1, 1000);
	 }
	 else if(tablename=="search_system_examiner_pg"){
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
			var whether_student_published_article=$("#whether_student_published_article").val();
			var mention_details = $("#mention_details").val();
			var article_title = $("#article_title").val();
			var month_year_exam = $("#month_year_exam").val();
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
			$.post("getFilter_examiners_md_list?" + key + "=" + value, {
			
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				id:0,
				institute_id : institute_id,
				status : institute_status
			},
				      function(j) {
				    	  console.log(j)
							for (var i = 0; i < j.length; i++) {
								jsondata.push([j[i].ser,j[i].inst_name,j[i].name_of_homoeopathic_medical_college,j[i].name_of_guide,j[i].name_of_student_for_guide,j[i].topic_of_dissertation,
								j[i].whether_guide_participated_in_examination,j[i].date_of_submission,j[i].date_of_acceptance,j[i].whether_student_published_article,
								j[i].mention_details,j[i].article_title,j[i].month_year_exam,j[i].action]);
								
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_examiners_md_ListCount?" + key + "=" + value, {
				id:0,
				institute_id : institute_id,
				status : institute_status
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
			setTimeout(setTimeLoadForTable2, 1000);
	 }
	 else if(tablename=="search_system_disseration_pg"){
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
			var mention_article=$("#mention_article").val();
			
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
			$.post("getFilter_disseration_list?" + key + "=" + value, {
			
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				id:0,
				institute_id : institute_id,
				status : institute_status
			},
				      function(j) {
				    	  console.log(j)
							for (var i = 0; i < j.length; i++) {
								jsondata.push([j[i].ser,j[i].inst_name,j[i].student_name_pg,j[i].from_date,j[i].to_date,j[i].name_of_topic,
								j[i].conclusion_obtain,j[i].date_of_submission_pg,j[i].publication,j[i].mention_article,j[i].action]);
								
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_disseration_ListCount?" + key + "=" + value, {
				id:0,
				institute_id : institute_id,
				status : institute_status
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
 			setTimeout(setTimeLoadForTable3, 1000);
	 }
	 else if(tablename=="search_system_lecture_pg"){
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
			
			
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
			$.post("getFilter_lecture_list?" + key + "=" + value, {
			
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				id:0,
				institute_id : institute_id,
				status : institute_status
			},
				      function(j) {
				    	  console.log(j)
							for (var i = 0; i < j.length; i++) {
								jsondata.push([j[i].ser,j[i].inst_name,j[i].student_name,j[i].student_class,j[i].lecture_date,j[i].lecture_day,
								j[i].lecture_time,j[i].topic,j[i].action]);
								
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_lectuture_ListCount?" + key + "=" + value, {
				id:0,
				institute_id : institute_id,
				status : institute_status
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
 			setTimeout(setTimeLoadForTable4, 1000);
	 }
	 else if(tablename=="search_system_assignment_pg"){
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
			
			
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
			$.post("getFilter_assignment_list?" + key + "=" + value, {
			
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				id:0,
				institute_id : institute_id,
				status : institute_status
			},
				      function(j) {
				    	  console.log(j)
							for (var i = 0; i < j.length; i++) {
								jsondata.push([j[i].ser,j[i].inst_name,j[i].student_name_formc,j[i].list_of_assignment_formc,j[i].action]);
								
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_assignment_ListCount?" + key + "=" + value, {
				id:0,
				institute_id : institute_id,
				status : institute_status
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
 			setTimeout(setTimeLoadForTable5, 1000);
	 }
	 else if(tablename=="search_system_presentation_pg"){
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
			
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
			$.post("getFilter_presentation_list?" + key + "=" + value, {
			
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				id:0,
				institute_id : institute_id,
				status : institute_status
			},
				      function(j) {
				    	  console.log(j)
							for (var i = 0; i < j.length; i++) {
								jsondata.push([j[i].ser,j[i].inst_name,j[i].student_name_presen,j[i].list_of_presentations,j[i].title_of_seminar_attended,j[i].action]);
								
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_presentation_ListCount?" + key + "=" + value, {
				id:0,
				institute_id : institute_id,
				status : institute_status
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
 			setTimeout(setTimeLoadForTable6, 1000);
	 }
}
      
      
      
function setTimeLoadForTable1(){
	document.querySelectorAll('.clarificationAdmittedData').forEach((items, index) => {
		items.addEventListener('click', event => {		
			var val=parseInt(index)+1;
			var hid = document.getElementById('RejectAdmittedId'+val).value;
				if (confirm('Are You Sure You Want to Clarified this Data ?')) {
					$('#myModal').show();
					var modal1 = document.getElementById('myModal');
					var span1 = document.getElementById('myModalClose');
					$("#rejVal").val(hid);
					span1.onclick = function() {
					    modal1.style.display = "none";
					}
				} else {
					return false;
				}
		});
	});
}
function setTimeLoadForTable2(){
	document.querySelectorAll('.clarificationExamimdData').forEach((items, index) => {
		items.addEventListener('click', event => {		
			var val=parseInt(index)+1;
			var hid = document.getElementById('RejectExamimdId'+val).value;
				if (confirm('Are You Sure You Want to Clarified this Data ?')) {
					$('#myModal').show();
					var modal1 = document.getElementById('myModal');
					var span1 = document.getElementById('myModalClose');
					$("#rejVal").val(hid);
					span1.onclick = function() {
					    modal1.style.display = "none";
					}
				} else {
					return false;
				}
		});
	});
}
function setTimeLoadForTable3(){
	document.querySelectorAll('.clarificationdissertationData').forEach((items, index) => {
		items.addEventListener('click', event => {		
			var val=parseInt(index)+1;
			var hid = document.getElementById('RejectdissertaId'+val).value;
				if (confirm('Are You Sure You Want to Clarified this Data ?')) {
					$('#myModal').show();
					var modal1 = document.getElementById('myModal');
					var span1 = document.getElementById('myModalClose');
					$("#rejVal").val(hid);
					span1.onclick = function() {
					    modal1.style.display = "none";
					}
				} else {
					return false;
				}
		});
	});
}
function setTimeLoadForTable4(){
	document.querySelectorAll('.clarificationlectureData').forEach((items, index) => {
		items.addEventListener('click', event => {		
			var val=parseInt(index)+1;
			var hid = document.getElementById('RejectlectId'+val).value;
				if (confirm('Are You Sure You Want to Clarified this Data ?')) {
					$('#myModal').show();
					var modal1 = document.getElementById('myModal');
					var span1 = document.getElementById('myModalClose');
					$("#rejVal").val(hid);
					span1.onclick = function() {
					    modal1.style.display = "none";
					}
				} else {
					return false;
				}
		});
	});
}
function setTimeLoadForTable5(){
	document.querySelectorAll('.clarificationAssignData').forEach((items, index) => {
		items.addEventListener('click', event => {		
			var val=parseInt(index)+1;
			var hid = document.getElementById('RejectassignId'+val).value;
				if (confirm('Are You Sure You Want to Clarified this Data ?')) {
					$('#myModal').show();
					var modal1 = document.getElementById('myModal');
					var span1 = document.getElementById('myModalClose');
					$("#rejVal").val(hid);
					span1.onclick = function() {
					    modal1.style.display = "none";
					}
				} else {
					return false;
				}
		});
	});
}
function setTimeLoadForTable6(){
	document.querySelectorAll('.clarificationPreseData').forEach((items, index) => {
		items.addEventListener('click', event => {		
			var val=parseInt(index)+1;
			var hid = document.getElementById('RejectpreId'+val).value;
				if (confirm('Are You Sure You Want to Clarified this Data ?')) {
					$('#myModal').show();
					var modal1 = document.getElementById('myModal');
					var span1 = document.getElementById('myModalClose');
					$("#rejVal").val(hid);
					span1.onclick = function() {
					    modal1.style.display = "none";
					}
				} else {
					return false;
				}
		});
	});
}
function Reject_pg_ins() {
	
    if($("input#reject_remarks").val() == "") {
      alert("Please Enter Clarification Of Data ");
      $("input#reject_remarks").focus();
      return false;
	  }

	$.post("reject_action_pg?"+key+"="+value,{
		
		id : $("#rejVal").val(),
		reject_remarks : $("#reject_remarks").val(),
		
	}).done(function(j) {
		
		alert(j);
		$("#modal-clos-btn").click();
		location.reload();
		
	}).fail(function(xhr, textStatus, errorThrown) {
  		alert("fail to fetch")
	});
}
      
</script>