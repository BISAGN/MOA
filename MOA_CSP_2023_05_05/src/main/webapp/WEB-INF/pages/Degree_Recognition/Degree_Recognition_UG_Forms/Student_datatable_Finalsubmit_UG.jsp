<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!--   <script type="text/javascript" src="assets/js/jquery-3.6.0.min.js"></script> -->

<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">

<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

<!-- datatable style and js end-->
<link rel="stylesheet" href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="assets/vendor/internal_css.css">

<section class="search_regulation Degree_Recognition">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2><span id="lbladd">Edit Or Submit UG Student Details</span></h2>
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
									                  <label>Institute Status<strong class="mandatory">*</strong></label>
									                  <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off"><div class="select-position">
									                  <select name="institute_status" id="institute_status" class="form-control customselect">						 
															<option value="">---Select Status---</option>
<!-- 															<option value="0">Draft</option> -->
<!-- 															<option value="0">Pending</option> -->
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
							 <div class="tab tablinks dg-rec-none" id="tab_id2">
								<button class="tab-toggle">Admitted Students</button>
							</div>  
							 <div class="content tabcontent" id="Form_B">
								<input type="hidden" id="h_id" name="h_id" value="a2">
								<input type="hidden" id="form_b_id" name="form_b_id" value="0" class="form-control autocomplete">
							<input type="hidden" id="userId" name="userId" value="${userId}"> 
								
									<h4 class="heading">Admitted Students For Each College/Institute As Per Proforma Provide</h4>
									
									<div class="row">
										<div class="col-12">
											<div class="card-style mb-30">
												<div class="table-wrapper custom-datatable-p">
													<input type="hidden" id="ssa_hid" name="ssa_hid"/>
                  									<table class="table" id="search_system_admitted">
                      									<thead>
                       										 <tr>
										                        <th><h6>Ser No</h6></th>
																<th><h6>Student Name</h6></th>
														        <th><h6>Date Of Admission</h6></th>
<!-- 														        <th><h6>Rank</h6></th> -->
<!-- 														        <th><h6>Marks</h6></th> -->
<!-- 														        <th><h6>All India</h6></th> -->
<!-- 														        <th><h6>State</h6></th> -->
<!-- 										 				        <th><h6>Admission Authority</h6></th> -->
														        <th><h6>CourtOrder & Others</h6></th>
														        <th><h6 >Upload File Court Order</h6></th>
														        <th><h6>Date of Enrollment In University</h6></th>
														        <th><h6>University Enrollment Number</h6></th>
														        <th><h6>Date Of Internship Completion</h6></th>
														        <th><h6>Remarks</h6></th>
														        <th><h6>Clarification</h6></th>
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
														<li><input type="button" id="btn-reject-submit-admitted" 
														           class="main-btn success-btn  btn-hover" 
												                   value="Submit For Re-Approval"></li>
													</ul>
											   </div>
                  					</div>
							</div>
							
<!-- 							<div class="tab tablinks dg-rec-none" id="tab_id3"> -->
<!-- 								<button class="tab-toggle">Hospital Attached</button> -->
<!-- 							</div> -->
							
<!-- 							<div id="Form_C" class="content tabcontent"> -->
<%-- 								<form id="Form_C_details"> --%>
<!-- 									<h4 class="heading">College Wise/Institute Wise Regarding The Hospital Attached</h4> -->
<!-- 							<div class="tables-wrapper"> -->
<!-- 								<div class="row"> -->
<!-- 									<div class="col-lg-12"> -->
<%-- 										<input type="hidden" name="review_id1" id="review_id1" value="${review_id}"/> --%>
<!--                 							<div class="card-style mb-30"> -->
<!--                 								<div class="table-wrapper custom-datatable-p"> -->
<!--                 							<input type="hidden" id="ssh_hid" name="ssh_hid"/> -->
<!--                   									<table class="table" id="search_system_Hospital"> -->
<!--                       									<thead> -->
<!-- 		                       								 <tr> -->
<!-- 										                        <th><h6>Ser No</h6></th> 0 -->
<!-- 																<th><h6>Name Of Homoeopathic Medical Collegee</h6></th>1 -->
<!-- 														        <th><h6>Name Of Attached Homoeopathic Hospital</h6></th>2 -->
<!-- 														        <th><h6>Name Of Attached Super Speciality Hospital</h6></th>3 -->
<!-- 														        <th><h6>MOU Date</h6></th>4 -->
<!-- 														        <th><h6>Copy Of MOU Date</h6></th>5 -->
<!-- 														        <th><h6>Name Of Hospital Staff</h6></th>6 -->
<!-- 														        <th><h6>Designation</h6></th>7 -->
<!-- 														        <th><h6>Qualification</h6></th>8 -->
<!-- 														        <th><h6>Full Time Or Part Time</h6></th>9 -->
<!-- 														        <th><h6>Remarks</h6></th>10 -->
<!-- 														        <th id="add_hos"><h6>Clarification</h6></th>11 -->
<!-- 														        <th id="edit_hide_hospi"><h6>Action</h6></th>12 -->
														        
<!-- 		                       								 </tr> -->
<!--                         			end table row -->
<!--                       									</thead> -->
<!--                       					 <tbody class="custom-datatablepra"></tbody> -->
<!--                    							 		</table> -->
<!--                   								</div> -->
<!--                   							</div> -->
<!--                  				 	</div>	 -->
<!--                  				 	<div class="col-lg-12 col-md-12 col-sm-12"> -->
<!--                    									<ul class="buttons-group mainbtn"> -->
<!-- 														<li><input type="button" id="btn-reject-submit-hospital"  -->
<!-- 														           class="main-btn success-btn  btn-hover"  -->
<!-- 												                   value="Submit For Re-Approval"></li> -->
<!-- 													</ul> -->
<!-- 											   </div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<%-- 								</form> 	 --%>
<!-- 							</div> -->
							
							<div class="tab tablinks dg-rec-none" id="tab_id4">
								<button class="tab-toggle">Migrated Students</button>
							</div>
							
							<div id="Form_D" class="content tabcontent">
								<form id="Form_D_details">
									<h4 class="heading">For Those Who Migrated To Other College</h4>
							<div class="tables-wrapper">
								<div class="row">
									<div class="col-lg-12">
										<input type="hidden" name="review_id1" id="review_id1" value="${review_id}"/>
                							<div class="card-style mb-30">
                								<div class="table-wrapper custom-datatable-p">
                								<input type="hidden" id="ssmt_hid" name="ssmt_hid"/>
                  									<table class="table" id="search_system_migrated_to">
                      									<thead>
		                       								 <tr>
										                        <th><h6>Ser No</h6></th><!--  0 -->
																<th><h6>Name of Institution</h6></th><!--  1 -->
														        <th><h6>Name of Student</h6></th><!--  2 -->
														        <th><h6>Date Of Migration</h6></th><!--  3 -->
														        <th><h6>Professional Year Of Migration</h6></th><!--  4 -->
														        <th><h6>University Enrollment Number</h6></th><!--  5 -->
														        <th><h6>Remarks</h6></th><!-- 6 -->
														        <th><h6>Clarification</h6></th><!--  7 -->
														        <th><h6>Action</h6></th><!--  8 -->
		                       								 </tr>
<!--                         			end table row -->
                      									</thead>
                      					 <tbody class="custom-datatablepra"></tbody>
                   							 		</table>
                  								</div>
                  							</div>
                 				 	</div>	
                 				 	<div class="col-lg-12 col-md-12 col-sm-12">
                   									<ul class="buttons-group mainbtn">
														<li><input type="button" id="btn-reject-submit-migto" 
														           class="main-btn success-btn  btn-hover" 
												                   value="Submit For Re-Approval"></li>
													</ul>
											   </div>
								</div>
							</div>
							
							<h4 class="heading">For Those Who Migrated From Other College</h4>
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
																<th><h6>Name of Institution</h6></th>
														        <th><h6>Name of Student</h6></th>
														        <th><h6>Date Of Migration</h6></th>
														        <th><h6>Professional Year Of Migration</h6></th>
														        <th><h6>University Enrollment Number</h6></th>
														        <th><h6>Remarks</h6></th>
														        <th><h6>Clarification</h6></th>
														        <th><h6>Action</h6></th>
		                       								 </tr>
<!--                         			end table row -->
                      									</thead>
                      					 <tbody class="custom-datatablepra"></tbody>
                   							 		</table>
                  								</div>
                  							</div>
                 				 	</div>	
                 				 	<div class="col-lg-12 col-md-12 col-sm-12">
                   									<ul class="buttons-group mainbtn">
														<li><input type="button" id="btn-reject-submit-migfrom" 
														           class="main-btn success-btn  btn-hover" 
												                   value="Submit For Re-Approval"></li>
													</ul>
											   </div>
								</div>
							</div>
							
								</form> 	
							</div>
							
							<div class="tab tablinks dg-rec-none" id="tab_id6">
								<button class="tab-toggle">Intern Students</button>
							</div>
							
							<div id="Form_F" class="content tabcontent">
							<h4 class="heading">Intern Student For Course</h4>
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
											                        <th><h6>Name Of Students</h6></th>
																	<th><h6>Year Of Admission</h6></th>
															        <th><h6>Date Of Result Of Final Year BHMS/Course Completed</h6></th>
															        <th><h6>Provisional Register Number</h6></th>
															        <th><h6>Year Of Provisional Registration(From State Board)</h6></th>
															        <th><h6>Date Of Starting Internship</h6></th>
															        <th><h6>Date Of Completion Internship</h6></th>
											 				        <th><h6>Remarks If Any</h6></th>
											 				        <th><h6>Clarification</h6></th>
											 				        <th><h6>Action</h6></th>
			                       							 </tr>
                      							       </thead>
                      					              <tbody class="custom-datatablepra"></tbody>
                   								 </table>
                  							</div>
                  						</div>
                 					 </div>	
<!--                  					 <div class="col-lg-12 col-md-12 col-sm-12"> -->
<!--                    									<ul class="buttons-group mainbtn"> -->
<!-- 														<li><input type="button" id="btn-reject-submit-intern"  -->
<!-- 														           class="main-btn success-btn  btn-hover"  -->
<!-- 												                   value="Submit For Re-Approval"></li> -->
<!-- 													</ul> -->
<!-- 											   </div> -->
<!--                  					 <div class="col-lg-12 col-md-12 col-sm-12"> -->
										<ul class="buttons-group mainbtn">
												<li><input type="button" id="btn-save" class="main-btn success-btn  btn-hover" 
												 value="Submit For Approval"></li>
												 
												 <li><input type="button" id="btn-reject-submit-intern" 
														           class="main-btn success-btn  btn-hover" 
												                   value="Submit For Re-Approval"></li>
											</ul>
<!-- 									</div> -->
									
								</div>						
							</div>
						</div>
					</div>
				</div>
			</div><!--  End row -->
</div>
</div><!-- End container-fluid -->
</section>

<!--  modal start -->
<div class="modal fade custom-modal" id="studentmodal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="staticBackdropLabel">Edit Data</h3>
        <button type="button" class="btn-close" id="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="custom-modal-inner" id="headData">
        </div>
      </div>
    </div>
  </div>
</div>
<!-- modal end -->

<c:url value="Student_Edit_Update_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm" name="updateForm" modelAttribute="eid">
	<input type="hidden" name="eid" id="eid">
</form:form>

<!-- UG FORM C -->

<c:url value="UG_Hospital_Edit_Update_Url" var="Edit_Hospital_Url" />
<form:form action="${Edit_Hospital_Url}" method="post" id="updateHospitalForm" name="updateHospitalForm" modelAttribute="hid">
	<input type="hidden" name="hid" id="hid">
</form:form>

<c:url value="Student_Intern_Edit_Url" var="Edit_Intern_Url" />
<form:form action="${Edit_Intern_Url}" method="post" id="updateInternForm" name="updateInternForm" modelAttribute="insid">
	<input type="hidden" name="insid" id="insid">
</form:form>

<script type="text/javascript"
	src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>
	
<c:url value="UploadPaper_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<c:url value="getDownloadUrladmitted" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="search3" name="search3" modelAttribute="upload_docformA">
	<input type="hidden" name="pageUrl" id="pageUrl"/>       		
	<input type="hidden" name="upload_docformA" id="upload_docformA" value=""/>		
</form:form>
<script nonce="${cspNonce}" type="text/javascript">

        $(document).ready(function() {
        	
                if(window.location.href.includes("msg"))
                {
                         var url = window.location.href.split("?msg")[0];
                         window.location = url;
                }
        		
//                 $("#tab_id2").hide();
//                 $("#tab_id3").hide();
//         		$("#tab_id4").hide();
//         		$("#tab_id6").hide();
        		
        });
        
        document.addEventListener('DOMContentLoaded', function() {
        	
        	document.getElementById('btn-reload').onclick = function(){
        		return select_status();
        	};
        	
        	document.getElementById('btn-save').onclick = function(){
        		return Submit_Approval_Datafor_institute();
        	};
        	document.getElementById('btn-updatefrom').onclick = function(){
        		return UpdateMigratedfrom();
        	};
        	
        	document.getElementById('btn-updateto').onclick = function(){
        		alert("----to----")
        		return UpdateMigratedto();
        	};
        	
        	document.getElementById('btn-reject-submit-admitted').onclick = function(){
        		return Submit_Approval_Reject_Admitted();
        	};
        	
        	document.getElementById('btn-reject-submit-hospital').onclick = function(){
        		return Submit_Approval_Reject_Hospital();
        	};
        	
        	document.getElementById('btn-reject-submit-migto').onclick = function(){
        		return Submit_Approval_Reject_Migto();
        	};
        	
        	document.getElementById('btn-reject-submit-migfrom').onclick = function(){
        		return Submit_Approval_Reject_Migfrom();
        	};
        	
        	document.getElementById('btn-reject-submit-intern').onclick = function(){
        		return Submit_Approval_Reject_intern();
        	};
        	document.getElementById('migrated_dt_to').onclick = function() {
        		return clickclear(this, 'DD/MM/YYYY');
        	};
        	document.getElementById('migrated_dt_to').onfocus = function() {
        		this.style.color='#000000';
        	};
        	document.getElementById('migrated_dt_to').onblur = function() {
        			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
        	};
        	document.getElementById('migrated_dt_to').onkeyup = function() {
        		return	clickclear(this, 'DD/MM/YYYY');
        	};
        	document.getElementById('migrated_dt_to').onchange = function() {
        		  clickrecall(this,'DD/MM/YYYY');
        		  return validateDate_FutureDate(this.value,this); 
        	};
        	
        	document.getElementById('dt_of_migration').onclick = function() {
        		return clickclear(this, 'DD/MM/YYYY');
        	};
        	document.getElementById('dt_of_migration').onfocus = function() {
        		this.style.color='#000000';
        	};
        	document.getElementById('dt_of_migration').onblur = function() {
        			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
        	};
        	document.getElementById('dt_of_migration').onkeyup = function() {
        		return	clickclear(this, 'DD/MM/YYYY');
        	};
        	document.getElementById('dt_of_migration').onchange = function() {
        		  clickrecall(this,'DD/MM/YYYY');
        		  return validateDate_FutureDate(this.value,this); 
        	};
        });
function select_status(){
	
	 if($("select#institute_status").val() == "") {
	       alert("Please Select Status");
	       $("select#institute_status").focus();
	       return false;
		  }
		
		  mockjax1('search_system_admitted');
	  	  table1 = dataTable('search_system_admitted');
	  	
// 	  	  mockjax1('search_system_Hospital');
// 	  	  table2 = dataTable('search_system_Hospital');
	  	
	  	  mockjax1('search_system_migrated_to');
		  table3 = dataTable('search_system_migrated_to');
			
		  mockjax1('search_system_migrated_from');
		  table4 = dataTable('search_system_migrated_from');
			
	  	  mockjax1('search_system_intern');
		  table5 = dataTable('search_system_intern');
	
		    table1.ajax.reload();
// 			table2.ajax.reload();
			table3.ajax.reload();
			table4.ajax.reload();
			table5.ajax.reload();
			 
		$("#tab_id2").show();
	 	$("#tab_id3").show();
	 	$("#tab_id4").show();
		$("#tab_id6").show();

// 		if($("#institute_status").val() == "0") 
// 		{
// 			$("#btn-save").show();
// 			        $("#btn-reject-submit-admitted").hide();
// 			        $("#btn-reject-submit-hospital").hide();
// 			        $("#btn-reject-submit-migto").hide();
// 			        $("#btn-reject-submit-migfrom").hide();
// 			        $("#btn-reject-submit-intern").hide();
// 			        $("#edit_hide_hospi").show();
// 		     	    $("#add_admitted").hide();
// 					$("#add_hos").hide();
// 					$("#add_mig_to").hide();
// 					$("#add_mig_from").hide();
// 					$("#add_intern").hide();
// 	    }
		if($("#institute_status").val() == "1") 
		{
			        $("#btn-reject-submit-admitted").hide();
			        $("#btn-reject-submit-hospital").hide();
			        $("#btn-reject-submit-migto").hide();
			        $("#btn-reject-submit-migfrom").hide();
			        $("#btn-reject-submit-intern").hide();
					$("#btn-save").hide();
		}
		if($("#institute_status").val() == "-1") 
		{
			        $("#btn-reject-submit-admitted").show();
			        $("#btn-reject-submit-hospital").show();
			        $("#btn-reject-submit-migto").show();
			        $("#btn-reject-submit-migfrom").show();
			        $("#btn-reject-submit-intern").show();
					$("#btn-save").hide();
		     	    $("#add_admitted").show();
		     	    $("#add_hos").show();
		     	    $("#add_mig_to").show();
		     	    $("#add_mig_from").show();
		     	    $("#add_intern").show();
		     	    $("#edit_hide").show();
		     	    $("#edit_hide_hospi").show();
		     	    $("#edit_hide_mig_to").show();
		     	    $("#edit_hide_mig_from").show();
		     	    $("#edit_hide_intern").show();
	    }
}

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
        			var rank = $("#rank").val();
        			var marks = $("#marks").val();
        			var all_india = $("#all_india").val();
        			var state=$("#state").val();
        			var admission_authority = $("#admission_authority").val();
        			var court_order = $("#court_order").val();
        			var court_order_file = $("#court_order_file").val();
        			var date_of_enroll_university = $("#date_of_enroll_university").val();
        			var uni_enroll_number = $("#uni_enroll_number").val();
        			var date_of_intern_compl =$("#date_of_intern_compl").val();
        			var remarks_form_b= $("#remarks_form_b").val();
        			var institute_status= $("#institute_status").val();
        			
        			var inst_status = $("#inst_status").val();
        			$.post("getFilter_Admitted_Student?" + key + "=" + value, {
        				
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
								
// 								if($("#institute_status").val() == "0") {
// 						    		   table.column(14).visible(false);
// 						    	   }
// 						    	   else{
// 						    		   table.column(15).visible(true);
// 						    	   }
								if($("#institute_status").val() == "1") {
						    		   table.column(9).visible(false);
						    	   }
						    	   else{
						    		   table.column(10).visible(true);
						    	   }
								
									jsondata.push([j[i].ser, //0
										j[i].student_name,//1
										j[i].date_of_admission,//2
// 										j[i].rank,//3
// 										j[i].marks,//4
// 										j[i].all_india,//5
// 										j[i].state,//6
// 										j[i].admission_authority,//7 
										j[i].court_order,//3
										j[i].vmp1,//4
										j[i].date_of_enroll_university,//5
										j[i].uni_enroll_number, //6
										j[i].date_of_intern_compl,//7
										j[i].remarks_form_b,//8
										j[i].reject_remarks,//9
										j[i].action]);//10
							}
   						});
        			$.ajaxSetup({
        				async : false
        			});
        			
        			$.post("getFilter_Admitted_StudentListCount?" + key + "=" + value, {
        				id:0,
        				institute_status : institute_status
        				
        			}, function(j) {
        				FilteredRecords = j;
        				$("#ssa_hid").val(j);
        				}).fail(function(xhr, textStatus, errorThrown, exception) {
        				  	 alert(errorThrownMsg(xhr,exception));
        			});
        			setTimeout(setTimeLoadForTable1, 1000);
        	 }
//         	 else if(tablename=="search_system_Hospital") {
          		
//          		 jsondata = [];
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
//          			var institute_status= $("#institute_status").val();

//          			$.post("getFilter_Hospital_Attached?" + key + "=" + value, {
         				
//          				startPage : startPage,
//          				pageLength : pageLength,
//          				Search : Search,
//          				orderColunm : orderColunm,
//          				orderType : orderType,
//          				id:0,
//          				institute_status : institute_status
//          			},
//          				      function(j) {
//          				           console.log(j)
//          							for (var i = 0; i < j.length; i++) {
//                  				    	  if($("#institute_status").val() == "0") {
//         						    		   table.column(11).visible(false);
//         						    	   }
//         						    	   else{
//         						    		   table.column(12).visible(true);
//         						    	   }
//         								if($("#institute_status").val() == "1") {
//         						    		   table.column(11).visible(false);
//         						    		   table.column(12).visible(false);
//         						    	   }
//         						    	   else{
//         						    		   table.column(10).visible(true);
//         						    	   }
//          									jsondata.push([j[i].ser, //0
//          										j[i].name_homoeopathic_medical_clg, //1
//          										j[i].attached_homoeopath_hospital,//2
//          										j[i].super_speciality_hospital,//3
//              									j[i].mou_date,//4
//              									j[i].copy_of_mou,//5
//              									j[i].name_of_hospital_staff,//6
//              									j[i].designation,//7
//              									j[i].qualification,//8
//              									j[i].fulltime_parttime,//9
//              									j[i].remarks_form_c,//10
//              									j[i].reject_remarks,//11
//              									j[i].action]);//12
//          							}
//          						});
//          			$.ajaxSetup({
//          				async : false
//          			});
         			
//          			$.post("getFilter_Hospital_AttachedListCount?" + key + "=" + value, {
//          				id:0,
//          				institute_status : institute_status
         				
//          			}, function(j) {
//          				FilteredRecords = j;
//          				$("#ssh_hid").val(j);
//          				}).fail(function(xhr, textStatus, errorThrown, exception) {
//          				  	 alert(errorThrownMsg(xhr,exception));
//          			});
//          			setTimeout(setTimeLoadForTable2, 1000);
//          	 }
        	 else if(tablename=="search_system_migrated_to") {
        		
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
        			var inst_status = $("#inst_status").val();
        			var institute_status= $("#institute_status").val();
        			$.post("getFilter_Migrated_Student?" + key + "=" + value, {
        				
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
        								
//         							 if($("#institute_status").val() == "0") {
//       						    		   table.column(7).visible(false);
//       						    	   }
//       						    	   else{
//       						    		   table.column(8).visible(true);
//       						    	   }
      							     if($("#institute_status").val() == "1") {
      						    		   table.column(7).visible(false);
      						    		   
      						    	   }
      						    	   else{
      						    		   table.column(8).visible(true);
      						    	   }        								
     									jsondata.push([j[i].ser, //0
     										j[i].name_of_inst,//1
     										j[i].student_name_to_migrated,//2
     										j[i].migrated_dt_to,//3
         									j[i].professional_year_migrated,//4
         									j[i].university_enrollment_number,//5
         									j[i].remarks_form_d,//6
         									j[i].reject_remarks,//7
         									j[i].action]);//8
     									
        							}
        						});
        			$.ajaxSetup({
        				async : false
        			});
        			
        			$.post("getFilter_Migrated_StudentListCount?" + key + "=" + value, {
        				id:0,
        				institute_status : institute_status
        			}, function(j) {
        				FilteredRecords = j;
        				$("#ssmt_hid").val(j);
        				}).fail(function(xhr, textStatus, errorThrown, exception) {
        				  	 alert(errorThrownMsg(xhr,exception));
        			});
        			setTimeout(setTimeLoadForTable3, 1000);
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
        			var inst_status = $("#inst_status").val();
        			var institute_status= $("#institute_status").val();
        			
        			$.post("getFilter_Migrated_Student_From?" + key + "=" + value, {
        				
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
        								
//         								 if($("#institute_status").val() == "0") {
//         						    		   table.column(7).visible(false);
//         						    	   }
//         						    	   else{
//         						    		   table.column(8).visible(true);
//         						    	   }
        							     if($("#institute_status").val() == "1") {
        						    		   table.column(7).visible(false);
        						    	   }
        						    	   else{
        						    		   table.column(8).visible(true);
        						    	   }     
      								
      									jsondata.push([j[i].ser,//0
      										j[i].institute_name,//1
      										j[i].name_of_students_migrated,//2
      										j[i].dt_of_migration,//3
          									j[i].professional_year,//4
          									j[i].university_enroll_num,//5
          									j[i].remarks_migrated,//6
          									j[i].reject_remarks,//7
          									j[i].action]);//8
        							}
        						});
        			$.ajaxSetup({
        				async : false
        			});
        			
        			$.post("getFilter_Migrated_Student_FromListCount?" + key + "=" + value, {
        				id:0,
        				institute_status : institute_status
        				
        			}, function(j) {
        				FilteredRecords = j;
        				$("#ssmf_hid").val(j);
        				}).fail(function(xhr, textStatus, errorThrown, exception) {
        				  	 alert(errorThrownMsg(xhr,exception));
        			});
        			setTimeout(setTimeLoadForTable4, 1000);
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
        		
        		var  name_of_students = $("#name_of_students").val();
        		var year_of_admission = $("#year_of_admission").val();
        		var date_of_result_final_year = $("#date_of_result_final_year").val();
        		var provisional_reg_no = $("#provisional_reg_no").val();
        		var year_of_provisional_reg_no = $("#year_of_provisional_reg_no").val();
        		var date_of_starting_internship = $("#date_of_starting_internship").val();
        		var date_of_completion_internship = $("#date_of_completion_internship").val();
        		var remark_form_f = $("#remark_form_f").val();
        		var inst_status = $("#inst_status").val();
        		var institute_status= $("#institute_status").val();
        		
        		$.post("getFilter_Intern_Student?" + key + "=" + value , {
        				startPage : startPage,
        				pageLength : pageLength,
        				Search : Search,
        				orderColunm : orderColunm,
        				orderType : orderType,
        				id:0,
        				institute_status : institute_status
        			},function(j) {
        					for (var i = 0; i < j.length; i++) {
        						
//         						if($("#institute_status").val() == "0") {
// 						    		   table.column(9).visible(false);
// 						    	   }
// 						    	   else{
// 						    		   table.column(10).visible(true);
// 						    	   }
							     if($("#institute_status").val() == "1") {
						    		   table.column(9).visible(false);
						    	   }
						    	   else{
						    		   table.column(10).visible(true);
						    	   }     
		
        							jsondata.push([j[i].ser,//0
        								j[i].name_of_students,//1
        								j[i].year_of_admission, //2
        								j[i].date_of_result_final_year,//3
        								j[i].provisional_reg_no,//4
            							j[i].year_of_provisional_reg,//5
            							j[i].date_of_starting_internship,//6
            							j[i].date_of_completion_internship, //7
            							j[i].remark_form_f,//8
            							j[i].reject_remarks,//9
            							j[i].action]);//10
        							
        					}
        				}).fail(function(xhr, textStatus, errorThrown, exception) {
        				  	 alert(errorThrownMsg(xhr,exception));
        				 });

        		$.ajaxSetup({
        			async : false
        		});
        		
        		$.post("getFilter_Intern_StudentListCount?" + key + "=" + value, {
        			id:0,
        			institute_status : institute_status
        		}, function(j) {
        			FilteredRecords = j;
        			$("#ssi_hid").val(j);
        			}).fail(function(xhr, textStatus, errorThrown, exception) {
        			  	 alert(errorThrownMsg(xhr,exception));
        		});
        		setTimeout(setTimeLoadForTable5, 1000);
        	}
}

 
function editData(id) {
	document.getElementById('eid').value=id;	
	document.getElementById('updateForm').submit();
}

function editData4(id) {
	document.getElementById('insid').value=id;	
	document.getElementById('updateInternForm').submit();
}

///////////// Admitted /////////////////

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

//DOWNLOAD PDF

document.querySelectorAll('.pdfdown').forEach((items, index) => {
	
	var val=parseInt(index)+1;
	console.log(val);	
	items.addEventListener('click', event => {
		var sid = document.getElementById('DCounpdf'+val).value;
		getDownloadPdfattsub(sid);
	})
});
}

function getDownloadPdfattsub(val){
	$("#upload_docformA").val(val);

	document.getElementById("search3").submit();
} 
///////////// UG FORM C /////////////////

function setTimeLoadForTable2(){
       document.querySelectorAll('.ADDHospital').forEach((items, index) => {
        items.addEventListener('click', event => {
        		
        		var val=parseInt(index)+1;
        		var hid = document.getElementById('hospiIdAGE'+val).value;
        		if (confirm('Are You Sure You Want to Edit Detail ?')) {
        			editData5(hid);
        		} else {
        			return false;
        		}
        	})
        });
}

function editData5(id) {
        	document.getElementById('hid').value=id;	
        	document.getElementById('updateHospitalForm').submit();
}
///////////// Migrated To /////////////////

function setTimeLoadForTable3(){
	document.querySelectorAll('.ADDMigratedTo').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdMig'+val).value;
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
	 
		 $.post('getmigrateddata?'+key+"="+value, {
			 id:id
			}).done(function(j) {
				var options = '<h4 class="heading">For Those Who Migrated To Other College</h4>'
				+'<div class="row"><div class="table-wrapper table-responsive simple-table">'
				+'<table class="table" id="family_table_from"><thead>'
				+'<tr>'
				+'<th rowspan="2"><h6>S.No</h6></th>'
				+'<th rowspan="2"><h6>Name of Institution</h6></th>'
				+'<th rowspan="2"><h6>Name of Student</h6></th>'
				+'<th rowspan="2"><h6>Date Of Migration</h6></th>'
				+'<th rowspan="2"><h6>Professional Year Of Migration</h6></th>'
				+'<th rowspan="2"><h6>University Enrollment Number</h6></th>'
				+'<th rowspan="2"><h6>Remarks</h6></th>'
				+'</tr>'
				+'</thead>'
				+'<tbody id="family_sibtbody">'
				+'<tr id="tr_sibling1">'
				+'<td>1</td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<select name="name_of_inst" id="name_of_inst" class="form-control">'
				+'<option value="0">---Select College---</option>'
				+'<c:forEach var="item" items="${getInstituteListbyUserID}" varStatus="num">'
				+'<option value="${item.institute_name}" name="${item.institute_name.trim()}">${item.institute_name.trim()}</option>'
				+'</c:forEach></select></div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="name_of_students_migrated" id="name_of_students_migrated" autocomplete="off" class="form-control" placeholder="Enter student name">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="dt_of_migration" id="dt_of_migration" maxlength="10"'
				+'class="form-control"'
				+'aria-required="true" autocomplete="off"'
				+'style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="month" name="professional_year" id="professional_year" maxlength="10" class="form-control" aria-required="true" autocomplete="off" value="YYYY">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="university_enroll_num" id="university_enroll_num" class="form-control" placeholder="Enter University Enrollment Number" onkeypress="return isNumber(event)">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="remarks_migrated" id="remarks_migrated" class="form-control" placeholder="Enter Remarks">'
				+'<input type="hidden" name="mig_id" id="mig_id" class="form-control">'
				+'</div></div></td>'
				+'</tr></tbody></table></div></div>'
				+'<div class="col-lg-12 col-md-12 col-sm-12">'
				+'<ul class="buttons-group mainbtn">'
				+'<li><input type="button" id="btn-updateto" class="main-btn info-btn btn-hover" value="Update">'
				+'</ul>'
			    +'</div>';
				
				$("#headData").append(options);
				$("#name_of_inst").val(j[0].name_of_inst);
				$("#name_of_students_migrated").val(j[0].student_name_to_migrated);
				$("#dt_of_migration").val(j[0].migrated_dt_to2);
				$("#professional_year").val(j[0].professional_year_migrated);
				$("#university_enroll_num").val(j[0].university_enrollment_number);
				$("#remarks_migrated").val(j[0].remarks_form_d);
				$("#mig_id").val(j[0].id);
				
			    datepicketDate('dt_of_migration');
		  });
	}
	
function UpdateMigratedto(){
	
	  if($("select#name_of_inst").val() == "0") {
       alert("Please Select Name Of Institute");
       $("select#name_of_inst").focus();
       return false;
	  }
	  if($("input#student_name_to_migrated").val() == "") {
      alert("Please Enter Student Name");
      $("input#student_name_to_migrated").focus();
      return false;
	  }
      if($("input#migrated_dt_to").val() == "DD/MM/YYYY" || $("input#migrated_dt_to").val() == ""  ) {
   		alert("Please Enter Date Of Migration");
   		$("input#migrated_dt_to").focus();
   		return false;
      }
      if($("input#professional_year_migrated").val() == "") {
           alert("Please Enter Professional Year Migrated");
           $("input#professional_year_migrated").focus();
           return false;
      }
      if($("input#university_enrollment_number").val() == "") {
            alert("Please Enter University Enrollment Number");
           $("input#university_enrollment_number").focus();
           return false;
      }
      if($("input#remarks_form_d").val() == "") {
         alert("Please Enter Remarks");
         $("input#remarks_form_d").focus();
         return false;
      }

  var  name_of_institution = $("#name_of_institution").val();
  var  name_of_students_migrated = $("#name_of_students_migrated").val();
  var  dt_of_migration = $("#dt_of_migration").val();
  var  professional_year = $("#professional_year").val();
  var  university_enroll_num = $("#university_enroll_num").val();
  var  remarks_migrated = $("#remarks_migrated").val();
  var  mig_id = $("#mig_id").val();

$.post("edit_migratedstudent_Action?" + key + "=" + value, {
	  name_of_institution : name_of_institution,name_of_students_migrated : name_of_students_migrated,
	  dt_of_migration : dt_of_migration,professional_year : professional_year,university_enroll_num : university_enroll_num,
	  remarks_migrated : remarks_migrated,mig_id : mig_id
	}, function(j) {
		alert(j);
		$("#btn-close").click();
		location.reload();
	});

}

///////////// Migrated From /////////////////

function setTimeLoadForTable4(){
	document.querySelectorAll('.ADDMigratedFrom').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdMigfrom'+val).value;
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
	 
		 $.post('getmigrateddatafrom?'+key+"="+value, {
			 id:id
			}).done(function(j) {
				
				var options = '<h4 class="heading">For Those Who Migrated From Other College</h4>'
				+'<span class="mandatory" >(All fields are mandatory)</span>'
				+'<div class="row"><div class="table-wrapper table-responsive simple-table">'
				+'<table class="table" id="family_table_from"><thead>'
				+'<tr>'
				+'<th rowspan="2"><h6>S.No</h6></th>'
				+'<th rowspan="2"><h6>Name of Institution</h6></th>'
				+'<th rowspan="2"><h6>Name of Student</h6></th>'
				+'<th rowspan="2"><h6>Date Of Migration</h6></th>'
				+'<th rowspan="2"><h6>Professional Year Of Migration</h6></th>'
				+'<th rowspan="2"><h6>University Enrollment Number</h6></th>'
				+'<th rowspan="2"><h6>Remarks</h6></th>'
				+'</tr>'
				+'</thead>'
				+'<tbody id="family_sibtbody">'
				+'<tr id="tr_sibling1">'
				+'<td>1</td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<select name="name_of_institution" id="name_of_institution" class="form-control" >'
				+'<option value="0">---Select College---</option>'
				+'<c:forEach var="item" items="${getInstituteListbyUserID}" varStatus="num">'
				+'<option value="${item.institute_name}" name="${item.institute_name.trim()}">${item.institute_name.trim()}</option>'
				+'</c:forEach></select></div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="name_of_students_migrated" id="name_of_students_migrated" class="form-control" placeholder="Enter student name">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="dt_of_migration" id="dt_of_migration" maxlength="10"'
				+'class="form-control"'
				+'aria-required="true" autocomplete="off"'
				+'style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="month" name="professional_year" id="professional_year" maxlength="10" class="form-control" aria-required="true" autocomplete="off" value="YYYY">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="university_enroll_num" id="university_enroll_num" class="form-control" placeholder="Enter University Enrollment Number" onkeypress="return isNumber(event)">'
				+'</div></div></td>'
				+'<td><div class=""><div class="input-style-2">'
				+'<input type="text" name="remarks_migrated" id="remarks_migrated" class="form-control" placeholder="Enter Remarks">'
				+'<input type="hidden" name="migf_id" id="migf_id" class="form-control">'
				+'</div></div></td>'
				+'</tr></tbody></table></div></div>'
				+'<div class="col-lg-12 col-md-12 col-sm-12">'
				
			    +'</div>'
			    +'<ul class="buttons-group mainbtn">'
				+'<li><input type="button" id="btn-updatefrom" class="main-btn info-btn btn-hover" value="Update">'
				+'</ul>';
				
				$("#headData").append(options);
				$("#name_of_institution").val(j[0].name_of_institution);
				$("#name_of_students_migrated").val(j[0].name_of_students_migrated);
				$("#dt_of_migration").val(j[0].dt_of_migration2);
				$("#professional_year").val(j[0].professional_year);
				$("#university_enroll_num").val(j[0].university_enroll_num);
				$("#remarks_migrated").val(j[0].remarks_migrated);
				$("#migf_id").val(j[0].id);
				
			    datepicketDate('dt_of_migration');
		  });
	}
	
function UpdateMigratedfrom(){
	
	  if($("select#name_of_institution").val() == "0") {
       alert("Please Select Name Of Institute");
       $("select#name_of_institution").focus();
       return false;
	  }
	  if($("input#name_of_students_migrated").val() == "") {
      alert("Please Enter Student Name");
      $("input#name_of_students_migrated").focus();
      return false;
	  }
     if($("input#dt_of_migration").val() == "DD/MM/YYYY" || $("input#dt_of_migration").val() == ""  ) {
   		alert("Please Enter Date Of Migration");
   		$("input#dt_of_migration").focus();
   		return false;
   }
   if($("input#professional_year").val() == "") {
           alert("Please Enter Professional Year Migrated");
           $("input#professional_year").focus();
           return false;
  }
  if($("input#university_enroll_num").val() == "") {
            alert("Please Enter University Enrollment Number");
           $("input#university_enroll_num").focus();
           return false;
  }
  if($("input#remarks_migrated").val() == "") {
         alert("Please Enter Remarks");
         $("input#remarks_migrated").focus();
         return false;
 }

  var  name_of_institution = $("#name_of_institution").val();
  var  name_of_students_migrated = $("#name_of_students_migrated").val();
  var  dt_of_migration = $("#dt_of_migration").val();
  var  professional_year = $("#professional_year").val();
  var  university_enroll_num = $("#university_enroll_num").val();
  var  remarks_migrated = $("#remarks_migrated").val();
  var  migf_id = $("#migf_id").val();

$.post("edit_migratedstudentfrom_Action?" + key + "=" + value, {
	  name_of_institution : name_of_institution,name_of_students_migrated : name_of_students_migrated,
	  dt_of_migration : dt_of_migration,professional_year : professional_year,university_enroll_num : university_enroll_num,
	  remarks_migrated : remarks_migrated,migf_id : migf_id
	}, function(j) {
		alert(j);
		$("#btn-close").click();
		location.reload();
	});

}

///////////////// Intern ///////////////////

function setTimeLoadForTable5(){
	document.querySelectorAll('.ADDIntern').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('insIdAGE'+val).value;
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				editData4(hid);
			} else {
				return false;
			}
		})
	});
}

function Submit_Approval_Datafor_institute(){
 	var ssa_hid = $("#ssa_hid").val();
 	var ssmt_hid = $("#ssmt_hid").val();
	var ssmf_hid = $("#ssmf_hid").val();
	var ssi_hid = $("#ssi_hid").val();
		$.post("Submit_Approval_Data_institute?" +  key + "=" + value,{ssa_hid:ssa_hid,ssmt_hid:ssmt_hid,
																	   ssmf_hid:ssmf_hid,ssi_hid:ssi_hid},function(data) {
			alert(data);
			location.reload();
	}).fail(function(xhr, textStatus, errorThrown) {
     alert("fail to fetch")
    });
}

function Submit_Approval_Reject_Admitted(){
	
	$.post("Submit_Approval_Reject_Data_institute_admitted?" +  key + "=" + value, function(data) {
		
		alert(data);
		location.reload();

}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
}

function Submit_Approval_Reject_Hospital(){
	
	$.post("Submit_Approval_Reject_Data_institute_hospital?" +  key + "=" + value, function(data) {
		
		alert(data);
		location.reload();

}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
}

function Submit_Approval_Reject_Migto(){
	
	$.post("Submit_Approval_Reject_Data_institute_migto?" +  key + "=" + value, function(data) {
		
		alert(data);
		location.reload();

}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
}
function Submit_Approval_Reject_Migfrom(){
	
	$.post("Submit_Approval_Reject_Data_institute_migfrom?" +  key + "=" + value, function(data) {
		
		alert(data);
		location.reload();

}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
}
function Submit_Approval_Reject_intern(){
	
	$.post("Submit_Approval_Reject_Data_institute_intern?" +  key + "=" + value, function(data) {
		
		alert(data);
		location.reload();

}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
	
}





</script>