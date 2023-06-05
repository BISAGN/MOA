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
														<select name="institute_id" id="institute_id" class="form-control-lg select2 narrow wrap"><!--singleselect form-control form-control-lg-->
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
									                  <select name="institute_status" id="institute_status" 
									                  		  class="singleselect form-control form-control-lg">						 
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
												<li><a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon"
												type="button"><i class="lni lni-search-alt"></i>Search Details</a></li>
												<li>
											</ul>
							</div>
							 <div class="tab tablinks" id="tab_id2">
								<button class="tab-toggle">Admitted Students For Each College/Institute As Per Proforma Provide</button>
							</div> 
							
							<div class="content tabcontent" id="Form_B">
							<form id="Form_B_details">
							<div class="tables-wrapper">
								<div class="row">
									<div class="col-lg-12">
									<input type="hidden" name="review_id1" id="review_id1" value="${review_id}"/>
                						<div class="card-style mb-30">
                						<div class="table-wrapper custom-datatable-p">
                							<input type="hidden" id="ssa_hid" name="ssa_hid"/>
                  							<table class="table" id="search_system_admitted">
                      									<thead>
                       										 <tr>
										                        <th><h6>Ser No</h6></th>
										                        <th><h6>Login Institute</h6></th>
																<th><h6>Student Name</h6></th>
														        <th><h6>Date Of Admission</h6></th>
														        <th><h6>Neet Rank</h6></th>
														        <th><h6>Rank</h6></th>
														        <th><h6>Marks</h6></th>
														        <th><h6>All India</h6></th>
														        <th><h6>State</h6></th>
														        <th><h6>Management</h6></th>
										 				        <th><h6>Admission Authority</h6></th>
														        <th><h6>CourtOrder & Others</h6></th>
														        <th><h6>Date of Enrollment In University</h6></th>
														        <th><h6>University Enrollment Number</h6></th>
														        <th><h6>Date Of Internship Completion</h6></th>
														        <th><h6>Remarks</h6></th>
														        <th id="uni_hide1"><h6>Action</h6></th>
<!-- 														        <th><h6>If Any Clarification</h6> -->
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
<!--  <div class="tab tablinks" id="tab_id5"> -->
<!-- 								<button class="tab-toggle">College Wise/Institute Wise Regarding The Hospital Attached</button> -->
<!-- 							</div>  -->
							
<!-- 							<div class="content tabcontent" id="Form_C"> -->
<%-- 							<form id="Form_C_details"> --%>
<!-- 							<div class="tables-wrapper"> -->
<!-- 								<div class="row"> -->
<!-- 									<div class="col-lg-12"> -->
<!--                 						<div class="card-style mb-30"> -->
<!--                 						<div class="table-wrapper custom-datatable-p"> -->
<!--                 							<input type="hidden" id="ssh_hid" name="ssh_hid"/> -->
<!--                   							<table class="table" id="search_system_hos_admitted"> -->
<!--                       									<thead> -->
<!--                        										 <tr> -->
<!-- 										                        <th><h6>Ser No</h6></th> -->
<!-- 										                        <th><h6>Login Institute</h6></th> -->
<!-- 																<th><h6>Name Of Homoeopathic Medical Collegee</h6></th> -->
<!-- 														        <th><h6>Name Of Attached Homoeopathic Hospital</h6></th> -->
<!-- 														        <th><h6>Name Of Attached Super Speciality Hospital</h6></th> -->
<!-- 														        <th><h6>MOU Date</h6></th> -->
<!-- 														        <th><h6>Copy Of MOU Date</h6></th> -->
<!-- 														        <th><h6>Name Of Hospital Staff</h6></th> -->
<!-- 														        <th><h6>Designation</h6></th> -->
<!-- 														        <th><h6>Qualification</h6></th> -->
<!-- 														        <th><h6>Full Time Or Part Time</h6></th> -->
<!-- 														        <th><h6>Remarks</h6></th> -->
<!-- 														        <th id="uni_hide2"><h6>Action</h6></th> -->
<!--                        								 		</tr> -->
<!--                       									</thead> -->
<!--                       					 			<tbody class="custom-datatablepra"></tbody> -->
<!--                    							 		</table> -->
<!--                   						</div></div> -->
<!--                  				 </div>	 -->
<!-- 							</div> -->
<!-- 							</div> -->
<%-- 							</form> --%>
<!-- 							</div> -->
							
							 <div class="tab tablinks" id="tab_id4">
								<button class="tab-toggle">Migrated Students College Wise/Institute Wise</button>
							</div> 
							
							<div class="content tabcontent" id="Form_D">
							<form id="Form_D_details">
							<div class="tables-wrapper">
								<div class="row">
									<div class="col-lg-12">
									<input type="hidden" name="review_id1" id="review_id1" value="${review_id}"/>
                						<div class="card-style mb-30">
                					<div class="table-wrapper custom-datatable-p">
                						<input type="hidden" id="ssmt_hid" name="ssmt_hid"/>
                  							<table class="table" id="search_system_migrated">
                      							<thead>
                       								 <tr>
								                        <th><h6>Ser No</h6></th>
										                <th><h6>Login Institute</h6></th>
														<th><h6>Name of Institution</h6></th>
												        <th><h6>Name of Student</h6></th>
												        <th><h6>Date Of Migration</h6></th>
												        <th><h6>Professional Year Of Migration</h6></th>
												        <th><h6>University Enrollment Number</h6></th>
												        <th><h6>Remarks</h6></th>
												        <th id="uni_hide3"><h6>Action</h6>
                       								 </tr>
                        <!-- end table row-->
                      							</thead>
                      					 <tbody class="custom-datatablepra">
                      </tbody>
                   							 </table>
                  						</div>
                  					</div>
                 				 </div>	
							</div>
							</div>
							
<!-- 							<h4 class="heading">For Those Who Migrated From Other College</h4> -->
							<div class="tables-wrapper">
								<div class="row">
									<div class="col-lg-12">
										<input type="hidden" name="review_id1" id="review_id1" value="${review_id}"/>
                							<div class="card-style mb-30">
                								<div class="table-wrapper custom-datatable-p">
                												<input type="hidden" id="ssmf_hid" name="ssmf_hid"/>
                  									<table class="table" id="search_system_migrated_from">
                      									<thead>
		                       								 <tr>
								                        <th><h6>Ser No</h6></th>
										                <th><h6>Login Institute</h6></th>
														<th><h6>Name of Institution</h6></th>
												        <th><h6>Name of Student</h6></th>
												        <th><h6>Date Of Migration</h6></th>
												        <th><h6>Professional Year Of Migration</h6></th>
												        <th><h6>University Enrollment Number</h6></th>
												        <th><h6>Remarks</h6></th>
												        <th id="uni_hide4"><h6>Action</h6>
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

							 <div class="tab tablinks" id="tab_id6" >
								<button class="tab-toggle">Intern Students For Course</button>
							</div> 
							
							<div class="content tabcontent" id="Form_F">
							<form id="form_F_details">
							<div class="tables-wrapper">
								<div class="row">
									<div class="col-lg-12">
									<input type="hidden" name="review_id1" id="review_id1" value="${review_id}"/>
                						<div class="card-style mb-30">
                						<div class="table-wrapper custom-datatable-p">
			
                         	<input type="hidden" id="ssi_hid" name="ssi_hid"/>
                      							<table class="table" id="search_system_intern">
                      							<thead>
                       								 <tr>
                       								 	<th><h6>Ser No</h6></th>
										                <th><h6>Login Institute</h6></th>
								                        <th><h6>Name Of Students</h6></th>
														<th><h6>Year Of Admission</h6></th>
												        <th><h6>Date Of Result Of Final Year BHMS/Course Completed</h6></th>
												        <th><h6>Provisional Register Number</h6></th>
												        <th><h6>Year Of Provisional Registration(From State Board)</h6></th>
												        <th><h6>Date Of Starting Internship</h6></th>
												        <th><h6>Date Of Completion Internship</h6></th>
								 				        <th><h6>Remarks If Any</h6></th>
								 				        <th id="uni_hide5"><h6>Action</h6>
                       								 </tr>
                        <!-- end table row-->
                      							</thead>
                      					 		<tbody class="custom-datatablepra"> </tbody>
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
							</form>
					</div>
				</div>
			</div>
		</div>
		<!--   </body> -->
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
			<li><input type="button" id="btn-save1" class="main-btn success-btn  btn-hover" value="Submit Clarification">
				<button type="button" id="myModalClose" class="btn btn-danger" data-bs-dismiss="modal" id="modal-clos-btn">Close</button>
			   <input type="hidden" id="rejVal" name="rejVal">
			</li>
		</ul>
    </div>
    </div>
  </div>
</div>
	</div>
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
                datepicketDate('date_of_admission');
                datepicketDate('date_of_exam');
                datepicketDate('date_of_result');
                datepicketDate('date_of_result_final_year');
                datepicketDate('date_of_starting_internship');
                datepicketDate('date_of_completion_internship');
                datepicketDate('year_of_admission');
                datepicketDate('year_of_provisional_reg_no');
                datepicketDate('to_migrated_date');
                datepicketDate('mou_date');
                datepicketDate('completion_of_course');
                datepicketDate('d_university_appointment');

        		$("#tab_id2").hide();
//         		$("#tab_id5").hide();
        		$("#tab_id4").hide();
        		$("#tab_id6").hide();
        		
        });
        
        function select_institute(){
        	
        	mockjax1('search_system_admitted');
        	table1 = dataTable('search_system_admitted');

//         	mockjax1('search_system_hos_admitted');
//         	table2 = dataTable('search_system_hos_admitted');
        	
        	mockjax1('search_system_migrated');
        	table3 = dataTable('search_system_migrated');

        	mockjax1('search_system_migrated_from');
        	table4 = dataTable('search_system_migrated_from');

        	mockjax1('search_system_intern');
        	table5 = dataTable('search_system_intern');

        	
//         	table1.ajax.reload();
//         	table2.ajax.reload();
//         	table3.ajax.reload();
//         	table4.ajax.reload();
//         	table5.ajax.reload();

        	$("#tab_id2").show();
        	$("#tab_id5").show();
         	$("#tab_id4").show();
        	$("#tab_id6").show();
        	
        	if($("#institute_status").val() == "1") 
    		{
    			$("#btn-save").hide();
    	    }
        	if($("#institute_status").val() == "-1") 
    		{
    			$("#uni_hide1").hide();
    			$("#uni_hide2").hide();
    			$("#uni_hide3").hide();
    			$("#uni_hide4").hide();
    			$("#uni_hide5").hide();
    			$("#btn-save").hide();
    	    }
        }

        document.addEventListener('DOMContentLoaded', function() {
        	
        	document.getElementById('btn-reload').onclick = function(){
    			return select_institute();
    		};
    		document.getElementById('btn-save').onclick = function(){
    			return Submit_Approval_Datafor_ins_university();
    		};
    		document.getElementById('btn-save1').onclick = function(){
    			return Reject_Intern();
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
			var neet_rank = $("#neet_rank").val();
			var rank = $("#rank").val();
			var marks = $("#marks").val();
			var all_india = $("#all_india").val();
			var admission_authority = $("#admission_authority").val();
			var court_order = $("#court_order").val();
			var course_name = $("#course_name").val();
			var date_of_exam = $("#date_of_exam").val();
			var result = $("#result").val();
			var date_of_result =$("#date_of_result").val();
			var state=$("#state").val();
			var management_quota=$("#management_quota").val();	
			var remarks_form_b= $("#remarks_form_b").val();
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
			$.post("getFilter_Admitted_list?" + key + "=" + value, {
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
				    	  
				    	   if($("#institute_status").val() == "-1" || $("#institute_status").val() == "1") {
				    		   table.column(15).visible(false);
				    	   }
				    	   else{
				    		   table.column(15).visible(true);
				    	   }
				    	   
							for (var i = 0; i < j.length; i++) {
								jsondata.push([j[i].ser,
									j[i].inst_name,
									j[i].student_name,
									j[i].date_of_admission,
									j[i].neet_rank,
									j[i].rank, 
									j[i].marks,
									j[i].all_india,
									j[i].state,
									j[i].management_quota,
									j[i].admission_authority, 
									j[i].court_order,
								j[i].date_of_enroll_university,
								j[i].uni_enroll_number,
								j[i].date_of_intern_compl,
								j[i].remarks_form_b,
								j[i].action]);
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_Admitted_ListCount?" + key + "=" + value, {
				id:0,
				institute_id : institute_id,
				status : institute_status
			}, function(j) {
				FilteredRecords = j;
				$("#ssa_hid").val(j);
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
			setTimeout(setTimeLoadForTable1, 1000);
	 }
// 	 else if(tablename=="search_system_hos_admitted") {
// 		    jsondata = [];
//          			var table = $('#' + tablename).DataTable();
//          			var info = table.page.info();
//          			var pageLength = info.length;
//          			var startPage = info.start;
//          			var endPage = info.end;
//          			var Search = table.search();
//          			var order = table.order();
//          			var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
//          			var orderType = order[0][1];
         			
//          			var name_homoeopathic_medical_clg = $("#name_homoeopathic_medical_clg").val();
//          			var attached_homoeopath_hospital = $("#attached_homoeopath_hospital").val();
//          			var super_speciality_hospital = $("#super_speciality_hospital").val();
//          			var mou_date = $("#mou_date").val();
//          			var copy_of_mou = $("#copy_of_mou").val();
//          			var name_of_hospital_staff = $("#name_of_hospital_staff").val();
//          			var designation = $("#designation").val();
//          			var qualification = $("#qualification").val();
//          			var fulltime_parttime = $("#fulltime_parttime").val();
//          			var remarks_form_c = $("#remarks_form_c").val();
//          			var institute_id= $("#institute_id").val();
//         			var institute_status= $("#institute_status").val();
//         			var university_approved_status = $("#university_approved_status").val();
         			
         			
// 			$.post("getFilter_Hospital_list?" + key + "=" + value, {
// 				startPage : startPage,
// 				pageLength : pageLength,
// 				Search : Search,
// 				orderColunm : orderColunm,
// 				orderType : orderType,
// 				id:0,
// 				institute_id : institute_id,
// 				status : institute_status
// 			},
// 			function(j) {
// 		    	  console.log(j)
		    	  
// 		    	   if($("#institute_status").val() == "-1" || $("#institute_status").val() == "1") {
// 		    		   table.column(12).visible(false);
// 		    	   }
// 		    	   else{
// 		    		   table.column(12).visible(true);
// 		    	   }
// 							for (var i = 0; i < j.length; i++) {
								
// 								jsondata.push([j[i].ser, //0
// 									j[i].inst_name, //1
// 									j[i].name_homoeopathic_medical_clg, //2
// 									j[i].attached_homoeopath_hospital, //3
// 									j[i].super_speciality_hospital, //4
//  									j[i].mou_date, //5
//  									j[i].copy_of_mou, //6
//  									j[i].name_of_hospital_staff,//7
//  									j[i].designation,//8
//  									j[i].qualification,//9
//  									j[i].fulltime_parttime,//10
//  									j[i].remarks_form_c,//11
//  									j[i].action]);//12
// 							}
// 						});
// 			$.ajaxSetup({
// 				async : false
// 			});
			
// 			$.post("getFilter_HospitalAttached_ListCount?" + key + "=" + value, {
// 				id:0,
// 				institute_id : institute_id,
// 				status : institute_status
// 			}, function(j) {
// 				FilteredRecords = j;
// 				$("#ssh_hid").val(j);
// 				}).fail(function(xhr, textStatus, errorThrown, exception) {
// 				  	 alert(errorThrownMsg(xhr,exception));
// 			});
// 			setTimeout(setTimeLoadForTable5, 1000);

// 	 }
	 else if(tablename=="search_system_migrated") {
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
			var university_approved_status = $("#university_approved_status").val();
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			$.post("getFilter_Migrated?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				id:0,
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
									            j[i].action]); 			//8					
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_Migrated_ListCount?" + key + "=" + value, {
				id:0,
				institute_id : institute_id,
				institute_status : institute_status
				
			}, function(j) {
				FilteredRecords = j;
				$("#ssmt_hid").val(j);
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
			setTimeout(setTimeLoadForTable2, 1000);
	 }
	 else if(tablename=="search_system_migrated_from") {
	
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
			var institute_id= $("#institute_id").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
			$.post("getFilter_Migrated_From?" + key + "=" + value, {
				
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				id:0,
				institute_id :institute_id,
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
			
			$.post("getFilter_Migrated_From_ListCount?" + key + "=" + value, {
				institute_id : institute_id,
				institute_status : institute_status,
				id:0
			}, function(j) {
				FilteredRecords = j;
				$("#ssmf_hid").val(j);
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
			setTimeout(setTimeLoadForTable3, 1000);
	 }
	 else if (tablename=="search_system_intern") {
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
		
		var name_of_students = $("#name_of_students").val();
		var year_of_admission = $("#year_of_admission").val();
		var date_of_result_final_year = $("#date_of_result_final_year").val();
		var provisional_reg_no = $("#provisional_reg_no").val();
		var year_of_provisional_reg_no = $("#year_of_provisional_reg_no").val();
		var date_of_starting_internship = $("#date_of_starting_internship").val();
		var date_of_completion_internship = $("#date_of_completion_internship").val();
		var remark_form_f = $("#remark_form_f").val();
		var institute_id= $("#institute_id").val();
		var institute_status= $("#institute_status").val();
		var university_approved_status = $("#university_approved_status").val();
		$.post("getFilter_Intern?" + key + "=" + value , {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				id:0,
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
							j[i].name_of_students, //2
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
		
		$.post("getFilter_Intern_ListCount?" + key + "=" + value, {
			id:0,
			institute_id : institute_id,
			institute_status : institute_status
		
		}, function(j) {
			FilteredRecords = j;
			$("#ssi_hid").val(j);
			}).fail(function(xhr, textStatus, errorThrown, exception) {
			  	 alert(errorThrownMsg(xhr,exception));
		});
		
		setTimeout(setTimeLoadForTable4, 1000);
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
	
	document.querySelectorAll('.clarificationMigratedData').forEach((items, index) => {
		items.addEventListener('click', event => {		
			var val=parseInt(index)+1;
			var hid = document.getElementById('RejectMigratedId'+val).value;
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
	
	document.querySelectorAll('.clarificationMigfromData').forEach((items, index) => {
		items.addEventListener('click', event => {		
			var val=parseInt(index)+1;
			var hid = document.getElementById('RejectMigfromId'+val).value;
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
	
	document.querySelectorAll('.clarificationInternData').forEach((items, index) => {
		items.addEventListener('click', event => {		
			var val=parseInt(index)+1;
			var hid = document.getElementById('RejectInternId'+val).value;
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

// function setTimeLoadForTable5(){
	
// 	document.querySelectorAll('.clarificationHospitalData').forEach((items, index) => {
// 		items.addEventListener('click', event => {		
// 			var val=parseInt(index)+1;
// 			var hid = document.getElementById('RejectHospitalId'+val).value;
// 				if (confirm('Are You Sure You Want to Clarified this Data ?')) {

// 					$('#myModal').show();
					
// 					var modal1 = document.getElementById('myModal');
// 					var span1 = document.getElementById('myModalClose');
// 					$("#rejVal").val(hid);
// 					span1.onclick = function() {
// 					    modal1.style.display = "none";
// 					}

// 				} else {
// 					return false;
// 				}
			
// 		});
// 	});
// }
function Reject_Intern() {
	
	     if($("input#reject_remarks").val() == "") {
	       alert("Please Enter Clarification Of Data ");
	       $("input#reject_remarks").focus();
	       return false;
		  }
	
		$.post("reject_action?"+key+"="+value,{
			
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


function Submit_Approval_Datafor_ins_university(){
	var ssa_hid = $("#ssa_hid").val();
 	var ssmt_hid = $("#ssmt_hid").val();
 	var ssmf_hid = $("#ssmf_hid").val();
 	var ssi_hid = $("#ssi_hid").val();
 	
		$.post("Submit_Approval_Data_ins_university?" +  key + "=" + value,{ssa_hid:ssa_hid,ssmt_hid:ssmt_hid,ssmf_hid:ssmf_hid,ssi_hid:ssi_hid}, function(data) {
			alert(data);
			location.reload();
	}).fail(function(xhr, textStatus, errorThrown) {
     alert("fail to fetch")
    });
}


</script>