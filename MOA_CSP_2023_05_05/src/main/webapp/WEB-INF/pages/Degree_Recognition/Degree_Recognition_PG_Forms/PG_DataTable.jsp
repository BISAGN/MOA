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

<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

<!-- datatable style and js end-->
<link rel="stylesheet" href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/watermark/common.js"></script>


<section class="search_regulation Degree_Recognition">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2><span id="lbladd">Edit Or Submit PG</span></h2>
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
								<button class="tab-toggle">First Choose The University</button>
							 </div>
							<div class="content tabcontent" id="Form_D">
									<h4 class="heading">Choose The University</h4>
									<div class="inst_block mb-10">
										<h6 class="mb-2">Instruction</h6>
								<ul class="inst_list">
									<li><p class="inst_text">Press last tab to Approve all the form details</p></li>
								</ul>
                               </div>
									<div class="row">
									
									<div class="col-lg-4 col-md-6 col-sm-12">
               									 <div class="select-style-1">
									                  <label>University Status<strong class="mandatory">*</strong></label>
									                  <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off"><div class="select-position">
									                  <select name="university_status" id="university_status" class="singleselect form-control form-control-lg">						 
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
						<li><a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button"><i class="lni lni-search-alt">
						</i>Search Details</a></li></ul>
							</div>
							 <div class="tab tablinks" id="tab_id1" style="display: block;">
								<button class="tab-toggle">Postgraduate Course Qualification In Homoeopathy In India</button>
							</div>  
							 <div class="content tabcontent" id="Form_A">
									<h4 class="heading">Postgraduate Course In Homoeopathy In India</h4>
									<div class="row">
										<div class="col-12">
											<div class="card-style mb-30">
												<div class="table-wrapper custom-datatable-p">
												<input type="hidden" id="apg_hid" name="apg_hid"/>
                  									<table class="table" id="search_system_pg">
                      									<thead>
                       										 <tr>
										                        <th><h6>Ser No</h6></th>
																<th><h6>Name Of the Applicant University granting qualification</h6></th>
														        <th><h6>Full name Of the postgraduate course</h6></th>
														        <th><h6>Abbreviation of postgradute course</h6></th>
														        <th><h6>Application for admitted academic session/batch</h6></th>
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
														        <th id="add_pga"><h6>Clarification</h6></th>
														        <th id="edita_hide"><h6>Action</h6></th>
                       								 		</tr>
                      									</thead>
                      					 			<tbody class="custom-datatablepra"></tbody>
                   							 		</table>
                  								</div>	
                  							</div>
                  						</div>
                  						<div class="col-lg-12 col-md-12 col-sm-12">
											
											<ul class="buttons-group mainbtn">
												<li><input type="button" id="btn-reject-submit-pg" class="main-btn success-btn  btn-hover" 
												value="Submit For Re-Approval"></li>
											</ul>
									</div>
                  					</div>
							</div>
							
							<div class="tab tablinks" id="tab_id3" style="display: block;">
								<button class="tab-toggle">Teaching Staff For PostGraduate Course</button>
							</div>
							
							<div id="Form_C" class="content tabcontent">
								<form id="Form_C_details">
									<h4 class="heading">Teaching Staff For PostGraduate Course</h4>
							<div class="tables-wrapper">
								<div class="row">
									<div class="col-lg-12">
                							<div class="card-style mb-30">
                								<div class="table-wrapper custom-datatable-p">
                								<input type="hidden" id="bpg_hid" name="bpg_hid"/>
                  									<table class="table" id="search_system_teaching">
                      									<thead>
		                       								 <tr>
										                        <th><h6>Ser No</h6></th>
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
														        <th id="add_pgc"><h6>Clarification</h6></th>
														       <th id="editb_hide"><h6>Action</h6></th>
		                       								 </tr>
                        			
                      									</thead>
                      					 <tbody class="custom-datatablepra"></tbody>
                   							 		</table>
                  								</div>
                  							</div>
                 				 	</div>	
                 				 	<div class="col-lg-12 col-md-12 col-sm-12">
											
											<ul class="buttons-group mainbtn">
												<li><input type="button" id="btn-reject-teaching" class="main-btn success-btn  btn-hover" 
												value="Submit For Re-Approval"></li>
											</ul>
									</div>
								</div>
							</div>
								</form> 	
							</div>
							
							<div class="tab tablinks" id="tab_id6" style="display: block;">
								<button class="tab-toggle">Examiners Appointed in For Examination</button>
							</div>
							
							<div id="Form_F" class="content tabcontent">
							<h4 class="heading">Examiners Appointed in For Examination</h4>
								<div class="row">
									<div class="col-lg-12">
                							<div class="card-style mb-30">
                								<div class="table-wrapper custom-datatable-p">
                						<input type="hidden" id="cpg_hid" name="cpg_hid"/>
                  									<table class="table" id="search_system_examiners_pg">
                      									<thead>
                       										 <tr>
			                       								 	<th><h6>Ser No</h6></th>
																	<th><h6>Subject</h6></th>
															       	<th><h6>Name Of Examiner</h6></th>
															       	<th><h6>Date Of Examiners</h6>
															       	<th id="add_pgf"><h6>Clarification</h6></th>
											 				        <th id="editc_hide"><h6>Action</h6></th>
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
											
											<ul class="buttons-group mainbtn">
												<li><input type="button" id="btn-reject-submit-examiners" class="main-btn success-btn  btn-hover" 
												 value="Submit For Re-Approval"></li>
											</ul>
									</div>	
								</div>						
							</div>
						</div>
					</div>
				</div>
			</div><!--  End row -->
</div>
</div><!-- End container-fluid -->
</section>

<script type="text/javascript"
	src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>
	
<c:url value="UploadPaper_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<!-- UG FORM A -->
<c:url value="PG_Edit_Update_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm" name="updateForm" modelAttribute="eid">
	<input type="hidden" name="eid" id="eid">
</form:form>

<!-- <!-- UG FORM C --> 

<c:url value="PG_Teaching_Edit_Update_Url" var="Edit_Teaching_Url" />
<form:form action="${Edit_Teaching_Url}" method="post" id="updateTeachingForm" name="updateTeachingForm" modelAttribute="hid">
	<input type="hidden" name="hid" id="hid">
</form:form>

<!-- <!-- UG FORM F--> 

<c:url value="PG_Examiners_Edit_Update_Url" var="Edit_Examiners_Url" />
<form:form action="${Edit_Examiners_Url}" method="post" id="updateExaminersForm" name="updateExaminersForm" modelAttribute="exmid">
	<input type="hidden" name="exmid" id="exmid">
</form:form>


<script nonce="${cspNonce}" type="text/javascript">

        $(document).ready(function() {
        	//alert('${roleid}'+"-----role id")
       
                if(window.location.href.includes("msg"))
                {
                         var url = window.location.href.split("?msg")[0];
                         window.location = url;
                }
                
                
                $("#tab_id1").hide();
                $("#tab_id3").hide();
                $("#tab_id6").hide();
         		
        });
        
                function select_status(){
                	
                	 if($("select#university_status").val() == "") {
                	       alert("Please Select university status");
                	       $("select#university_status").focus();
                	       return false;
                		  }
                	 
                	mockjax1('search_system_pg');
                 	table1 = dataTable('search_system_pg');
                 	
                 	mockjax1('search_system_teaching');
                 	table2 = dataTable('search_system_teaching');
                 	
                 	mockjax1('search_system_examiners_pg');
                 	table3 = dataTable('search_system_examiners_pg');
                 	
                 	 $("#tab_id1").show();
                     $("#tab_id3").show();
                     $("#tab_id6").show();
                     
                     if($("#university_status").val() == "0") 
               		 {
               					$("#edita_hide").show();
               					$("#editb_hide").show();
               					$("#editc_hide").show();
               					$("#btn-save").show();
               					
               					$("#btn-reject-submit-pg").hide();
               					$("#btn-reject-teaching").hide();
               					$("#btn-reject-submit-examiners").hide();
               					
               					$("#add_pga").hide();
               					$("#add_pgc").hide();
               					$("#add_pgf").hide();
               					
               					
               		}
                     if($("#university_status").val() == "1") 
               		 {
               					$("#edita_hide").hide();
               					$("#editb_hide").hide();
               					$("#editc_hide").hide();
               					$("#btn-save").hide();
               					
               					$("#btn-reject-submit-pg").hide();
               					$("#btn-reject-teaching").hide();
               					$("#btn-reject-submit-examiners").hide();
               					
               					$("#add_pga").hide();
               					$("#add_pgc").hide();
               					$("#add_pgf").hide();
               					
               		}
                     if($("#university_status").val() == "-1") 
               		 {
               					$("#edita_hide").show();
               					$("#editb_hide").show();
               					$("#editc_hide").show();
               					$("#btn-save").hide();
               					
               					$("#btn-reject-submit-pg").show();
               					$("#btn-reject-teaching").show();
               					$("#btn-reject-submit-examiners").show();
               					
               					$("#add_pga").show();
               					$("#add_pgc").show();
               					$("#add_pgf").show();
               					
               		}
              		
                }
       
                document.addEventListener('DOMContentLoaded', function() {
                	
                	document.getElementById('btn-reload').onclick = function(){
                			return select_status();
                	};
                	document.getElementById('btn-save').onclick = function(){
            			return Submit_Approval_Datafor_university_PG();
            	     };
            	     document.getElementById('btn-reject-submit-pg').onclick = function(){
             			return Approval_University_Reject_pg();
             	     };
             	    document.getElementById('btn-reject-teaching').onclick = function(){
             			return Approval_University_Reject_teaching();
             	     };
             	    document.getElementById('btn-reject-submit-examiners').onclick = function(){
             			return Approval_University_Reject_examiners();
             	     };
                });
               
       
        
//----------------------VIEW-TABLE---------------------------

        function data(tablename){
       
        	 if(tablename=="search_system_pg"){
        		
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
        			var postgraduate_course = $("#postgraduate_course").val();
        			var abbre_postgraduate_course = $("#abbre_postgraduate_course").val();
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
        			var university_id = $("#university_id").val();
        			var university_status = $("#university_status").val();
        			
        			$.post("getFilter_PG?" + key + "=" + value, {
        				
        				startPage : startPage,
        				pageLength : pageLength,
        				Search : Search,
        				orderColunm : orderColunm,
        				orderType : orderType,
        				id:0,
        				university_id : university_id,
        				university_status : university_status
        				
        			},
        				      function(j) {
         				
        				    	  console.log(j)
        							for (var i = 0; i < j.length; i++) {
        								
        								if($("#university_status").val() == "1") {
        										jsondata.push([j[i].ser,j[i].name_of_applicant_university,j[i].postgraduate_course,j[i].abbre_postgraduate_course,j[i].academic_session,j[i].name_of_college,
            									j[i].country,j[i].state,j[i].district,j[i].city,j[i].postal_address,j[i].email,j[i].website,j[i].academic_year_applied_for,
            									j[i].permission_from_central_gov,j[i].admission_intake,j[i].num_of_student_admitted,j[i].remarks]);
          								}
        								else if($("#university_status").val() == "-1") {
        									jsondata.push([j[i].ser,j[i].name_of_applicant_university,j[i].postgraduate_course,j[i].abbre_postgraduate_course,j[i].academic_session,j[i].name_of_college,
            									j[i].country,j[i].state,j[i].district,j[i].city,j[i].postal_address,j[i].email,j[i].website,j[i].academic_year_applied_for,
            									j[i].permission_from_central_gov,j[i].admission_intake,j[i].num_of_student_admitted,j[i].remarks,j[i].reject_remarks,j[i].action]);
         								}
        								else{
        									jsondata.push([j[i].ser,j[i].name_of_applicant_university,j[i].postgraduate_course,j[i].abbre_postgraduate_course,j[i].academic_session,j[i].name_of_college,
        									j[i].country,j[i].state,j[i].district,j[i].city,j[i].postal_address,j[i].email,j[i].website,j[i].academic_year_applied_for,
        									j[i].permission_from_central_gov,j[i].admission_intake,j[i].num_of_student_admitted,j[i].remarks,j[i].action]);
        								}
        							}
        						});
        			$.ajaxSetup({
        				async : false
        			});
        			
        			$.post("getFilter_PGListCount?" + key + "=" + value, {
        				id:0,
        				university_id : university_id,
        				university_status : university_status
        			}, function(j) {
        				FilteredRecords = j;
        				$("#apg_hid").val(j);
        				}).fail(function(xhr, textStatus, errorThrown, exception) {
        				  	 alert(errorThrownMsg(xhr,exception));
        			});
        			setTimeout(setTimeLoadForTable1, 1000);
        	 }
        	 else if(tablename=="search_system_teaching") {
         		
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
         			var university_id = $("#university_id").val();
        			var university_status = $("#university_status").val();
         			$.post("getFilter_Teaching_staff?" + key + "=" + value, {
         				
         				startPage : startPage,
         				pageLength : pageLength,
         				Search : Search,
         				orderColunm : orderColunm,
         				orderType : orderType,
         				id:0,
         				university_id : university_id,
        				university_status : university_status
         			},
         				      function(j) {
         				    	  console.log(j)
         							for (var i = 0; i < j.length; i++) {
         								
         								if($("#university_status").val() == "1") {
         									jsondata.push([j[i].ser,j[i].name_of_college_pg,j[i].name_of_teaching_staff,j[i].phone,
             									j[i].email_id,j[i].designation,j[i].department,j[i].registration_no,j[i].date_of_reg,
             									j[i].date_of_birth,j[i].qualification_awarding_authority,j[i].year_of_award,
             									j[i].date_of_appointment,j[i].fulltime_parttime,j[i].post_teaching,j[i].exp_from,j[i].exp_to,j[i].total_teaching_exp_in_year]);
      								     }
         								else if($("#university_status").val() == "-1") {
         									jsondata.push([j[i].ser,j[i].name_of_college_pg,j[i].name_of_teaching_staff,j[i].phone,
             									j[i].email_id,j[i].designation,j[i].department,j[i].registration_no,j[i].date_of_reg,
             									j[i].date_of_birth,j[i].qualification_awarding_authority,j[i].year_of_award,
             									j[i].date_of_appointment,j[i].fulltime_parttime,j[i].post_teaching,j[i].exp_from,j[i].exp_to,j[i].total_teaching_exp_in_year,j[i].reject_remarks,j[i].action]);
         								}
         								else{
         								jsondata.push([j[i].ser,j[i].name_of_college_pg,j[i].name_of_teaching_staff,j[i].phone,
         									j[i].email_id,j[i].designation,j[i].department,j[i].registration_no,j[i].date_of_reg,
         									j[i].date_of_birth,j[i].qualification_awarding_authority,j[i].year_of_award,
         									j[i].date_of_appointment,j[i].fulltime_parttime,j[i].post_teaching,j[i].exp_from,j[i].exp_to,j[i].total_teaching_exp_in_year,j[i].action]);
         								}
         							}
         						});
         			$.ajaxSetup({
         				async : false
         			});
         			
         			$.post("getFilter_Teaching_staffListCount?" + key + "=" + value, {
         				id:0,
         				university_id : university_id,
        				university_status : university_status
         			}, function(j) {
         				FilteredRecords = j;
         				$("#bpg_hid").val(j);
         				}).fail(function(xhr, textStatus, errorThrown, exception) {
         				  	 alert(errorThrownMsg(xhr,exception));
         			});
         			setTimeout(setTimeLoadForTable2, 1000);
         	 }
        	 else if(tablename=="search_system_examiners_pg") {
          		
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
         		
         			var university_id = $("#university_id").val();
        			var university_status = $("#university_status").val();

         			$.post("getFilter_Examiners_pg?" + key + "=" + value, {
         				
         				startPage : startPage,
         				pageLength : pageLength,
         				Search : Search,
         				orderColunm : orderColunm,
         				orderType : orderType,
         				id:0,
         				university_id : university_id,
        				university_status : university_status
         			},
         				      function(j) {
         				    	  console.log(j)
         							for (var i = 0; i < j.length; i++) {
         								
         								if($("#university_status").val() == "1") {
             								jsondata.push([j[i].ser,j[i].subject,j[i].name_of_examiners,j[i].date_of_examination]);

      								     }
         								else if($("#university_status").val() == "-1") {
             								jsondata.push([j[i].ser,j[i].subject,j[i].name_of_examiners,j[i].date_of_examination,j[i].reject_remarks,j[i].action]);

         								}
         								else{
         								jsondata.push([j[i].ser,j[i].subject,j[i].name_of_examiners,j[i].date_of_examination,j[i].action]);
         								}
         							}
         						});
         			$.ajaxSetup({
         				async : false
         			});
         			
         			$.post("getFilter_Examiners_pgListCount?" + key + "=" + value, {
         				id:0,
        				university_id : university_id,
        				university_status : university_status
         			}, function(j) {
         				FilteredRecords = j;
         				$("#cpg_hid").val(j);
         				}).fail(function(xhr, textStatus, errorThrown, exception) {
         				  	 alert(errorThrownMsg(xhr,exception));
         			});
         			setTimeout(setTimeLoadForTable3, 1000);
         	 }
         	 
 }

// ///////////// UG FORM A /////////////////

function setTimeLoadForTable1(){
       document.querySelectorAll('.ADDPG').forEach((items, index) => {
        items.addEventListener('click', event => {
        		
        		var val=parseInt(index)+1;
        		var hid = document.getElementById('pgIdAGE'+val).value;
        		if (confirm('Are You Sure You Want to Edit Detail ?')) {
        			editData(hid);
        		} else {
        			return false;
        		}
        	})
        });
}

function editData(id) {
        	document.getElementById('eid').value=id;	
        	document.getElementById('updateForm').submit();
}

// ///////////// UG FORM C /////////////////

function setTimeLoadForTable2(){
       document.querySelectorAll('.ADDTeaching').forEach((items, index) => {
        items.addEventListener('click', event => {
        		
        		var val=parseInt(index)+1;
        		var hid = document.getElementById('teachingIdAGE'+val).value;
        		if (confirm('Are You Sure You Want to Edit Detail ?')) {
        			editData2(hid);
        		} else {
        			return false;
        		}
        	})
        });
}

function editData2(id) {
        	document.getElementById('hid').value=id;	
        	document.getElementById('updateTeachingForm').submit();
}

// ///////////// UG FORM E/////////////////

function setTimeLoadForTable3(){
       document.querySelectorAll('.ADDExam').forEach((items, index) => {
        items.addEventListener('click', event => {
        		
        		var val=parseInt(index)+1;
        		var hid = document.getElementById('examIdAGE'+val).value;
        		if (confirm('Are You Sure You Want to Edit Detail ?')) {
        			editData3(hid);
        		} else {
        			return false;
        		}
        	})
        });
}

function editData3(id) {
        	document.getElementById('exmid').value=id;	
        	document.getElementById('updateExaminersForm').submit();
}
function Submit_Approval_Datafor_university_PG(){
	var apg_hid = $("#apg_hid").val();
 	var bpg_hid = $("#bpg_hid").val();
 	var cpg_hid = $("#cpg_hid").val();
 	
	$.post("Submit_Approval_Data_university_pg?" +  key + "=" + value,{apg_hid:apg_hid,bpg_hid:bpg_hid,cpg_hid:cpg_hid}, function(data) {
		alert(data);
        location.reload();

}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
}


function Approval_University_Reject_pg(){
	
	$.post("Reject_Data_pg?" +  key + "=" + value, function(data) {
		
		alert(data);
		location.reload();

}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
}
function Approval_University_Reject_teaching(){
	
	$.post("Reject_Data_teaching?" +  key + "=" + value, function(data) {
		
		alert(data);
		location.reload();

}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
}
function Approval_University_Reject_examiners(){
	
	$.post("Reject_Data_exami?" +  key + "=" + value, function(data) {
		
		alert(data);
		location.reload();

}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
}
</script>