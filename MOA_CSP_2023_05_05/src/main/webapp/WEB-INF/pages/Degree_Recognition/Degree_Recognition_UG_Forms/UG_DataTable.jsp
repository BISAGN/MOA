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
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
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
						<h2><span id="lbladd">Edit Or Submit UG</span></h2>
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
						 
							 <div class="tab tablinks dg-rec-block" id="tab_id1">
								<button class="tab-toggle">Undergraduate Course In Homoeopathy In India</button>
							</div>  
							 <div class="content tabcontent" id="Form_A">
									<h4 class="heading">Undergraduate Course In Homoeopathy In India</h4>
									<div class="row">
										<div class="col-12">
											<div class="card-style mb-30">
												<div class="table-wrapper custom-datatable-p">
												<input type="hidden" id="ssug_hid" name="ssug_hid"/>
                  									<table class="table" id="search_system_ug">
                      									<thead>
                       										 <tr>
										                        <th><h6>Ser No</h6></th>
																<th><h6>Name Of Applicant University</h6></th>
																<th><h6>Name Of Undergraduate Course</h6></th>
																<th><h6>Abbreviation Of Undergraduate Course</h6></th>
																<th><h6>Institute Name</h6></th>
														        <th><h6>Academic Year Applied For</h6></th>
														        <th><h6>Academic File Upload</h6></th>
														        <th><h6>Permission From Central Government Document</h6></th>
														        <th><h6>Admission Intake Capacity Permitted</h6></th>
														        <th><h6>Number Of Students Admitted</h6></th>
														        <th><h6>Postal Address</h6></th>
														        <th><h6>Email</h6></th>
														        <th><h6>Website</h6></th>
														        <th><h6>Remarks</h6></th>
														        <th><h6>Clarification</h6></th>
														        <th><h6>Action</h6></th>
                       								 		</tr>
                      									</thead>
                      									
                   							 		</table>
                  								</div>	
                  							</div>
                  						</div>
                  						
                  						<div class="col-lg-12 col-md-12 col-sm-12">
											<ul class="buttons-group mainbtn">
												<li><input type="button" id="btn-save1" class="main-btn success-btn  btn-hover" 
												 value="Submit For Approval"></li>
											</ul>
											<ul class="buttons-group mainbtn">
												<li><input type="button" id="btn-reject-submit-ug" class="main-btn success-btn  btn-hover" 
												value="Submit For Re-Approval"></li>
											</ul>
									</div>
                  					</div>
							</div>
							

							
<!-- 							<div class="tab tablinks dg-rec-block" id="tab_id6" > -->
<!-- 								<button class="tab-toggle">Examiners Appointed in For Examination</button> -->
<!-- 							</div> -->
							
<!-- 							<div id="Form_F" class="content tabcontent"> -->
<!-- 							<h4 class="heading">Examiners Appointed in For Examination</h4> -->
<!-- 								<div class="row"> -->
<!-- 									<div class="col-lg-12"> -->
<%-- 										<input type="hidden" name="review_id1" id="review_id1" value="${review_id}"/> --%>
<!--                 							<div class="card-style mb-30"> -->
<!--                 								<div class="table-wrapper custom-datatable-p"> -->
<!--                 								<input type="hidden" id="ssex_hid" name="ssex_hid"/> -->
<!--                   									<table class="table" id="search_system_examiners"> -->
<!--                       									<thead> -->
<!--                        										 <tr> -->
<!-- 			                       								 	<th><h6>Ser No</h6></th> -->
<!-- 											                        <th><h6>Name Of Examiners External/Internal</h6></th> -->
<!-- 																	<th><h6>Subject</h6></th> -->
<!-- 															        <th><h6>Year</h6></th> -->
<!-- 															        <th><h6>Qualification</h6></th> -->
<!-- 															        <th><h6>Teaching Experience</h6></th> -->
<!-- 															        <th><h6>Teachers Code(Mention The Number)</h6></th> -->
<!-- 															        <th><h6>Registration Number</h6></th> -->
<!-- 											 				        <th><h6>Date Of University Appointment Letter</h6></th> -->
<!-- 											 				        <th><h6>Clarification</h6></th> -->
<!-- 											 				        <th><h6>Action</h6></th> -->
<!-- 			                       							 </tr> -->
<!--                       							       </thead> -->
<!--                       					              <tbody class="custom-datatablepra"></tbody> -->
<!--                    								 </table> -->
<!--                   							</div> -->
<!--                   						</div> -->
<!--                  					 </div> -->
<!--                  					 <div class="col-lg-12 col-md-12 col-sm-12"> -->
<!-- 											<ul class="buttons-group mainbtn"> -->
<!-- 												<li><input type="button" id="btn-save1" class="main-btn success-btn  btn-hover"  -->
<!-- 												 value="Submit For Approval"></li> -->
<!-- 											</ul> -->
											
<!-- 											<ul class="buttons-group mainbtn"> -->
<!-- 												<li><input type="button" id="btn-reject-submit" class="main-btn success-btn  btn-hover"  -->
<!-- 												 value="Submit For Re-Approval"></li> -->
<!-- 											</ul> -->
<!-- 									</div>	 -->
<!-- 								</div>						 -->
<!-- 							</div> -->
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
<c:url value="UG_Edit_Update_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm" name="updateForm" modelAttribute="eid">
	<input type="hidden" name="eid" id="eid">
</form:form>

<c:url value="UG_Examiners_Edit_Update_Url" var="Edit_Examiners_Url" />
<form:form action="${Edit_Examiners_Url}" method="post" id="updateExaminersForm" name="updateExaminersForm" modelAttribute="exid">
	<input type="hidden" name="exid" id="exid">
</form:form>

<%-- <c:url value="permission_pdf" var="permission_pdf" /> --%>
<%-- <form:form action="${permission_pdf}" method="post" id="search4" --%>
<%-- 	name="search4" modelAttribute="id3"> --%>
<!-- 	<input type="hidden" name="id31" id="id31" value="0" /> -->
<!-- 	<input type="hidden" name="typeReport2" id="typeReport2" value="0" /> -->
<!-- 	<input type="hidden" name="reportname1" id="reportname1" value="0" /> -->
<%-- </form:form> --%>


<%-- <c:url value="getDownloadUrlDocFormA" var="downloadUrl" /> --%>
<%-- <form:form action="${downloadUrl}" method="post" id="getDownloadPdfForm" name="getDownloadPdfForm" modelAttribute="doc_id1"> --%>
<!--      	<input type="hidden" name="pageUrl" id="pageUrl" value="dash1"/> -->
<!--         <input type="hidden" name="doc_id1" id="doc_id1" value="" /> -->
<!--         <input type="hidden" name="doc_column" id="doc_column" value="permission_from_central_gov"/> -->
<!--         <input type="hidden" name="fildname" id="fildname" value="permission_from_central_gov"/>  -->
<!--         <input type="hidden" name="table_name" id="table_name" value="dg_rec_ug_form_a_parent"/>   -->
<%-- </form:form> --%>

<c:url value="getDownloadUrlDocFormA" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="search3" name="search3" modelAttribute="upload_docformA">
	<input type="hidden" name="pageUrl" id="pageUrl"/>       		
	<input type="hidden" name="upload_docformA" id="upload_docformA" value=""/>		
</form:form>
<c:url value="getDownloadUrlDocFormB" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="search4" name="search4" modelAttribute="upload_docformB">
	<input type="hidden" name="pageUrl" id="pageUrl"/>       		
	<input type="hidden" name="upload_docformB" id="upload_docformB" value=""/>		
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

        $(document).ready(function() {
       
                if(window.location.href.includes("msg"))
                {
                         var url = window.location.href.split("?msg")[0];
                         window.location = url;
                }
                $("#tab_id1").hide();
                $("#tab_id6").hide();
        });
        
        function select_status(){
        	
        	 if($("select#university_status").val() == "") {
      	       alert("Please Select university status");
      	       $("select#university_status").focus();
      	       return false;
      		  }
        	 
        	mockjax1('search_system_ug');
         	table1 = dataTable('search_system_ug');
         	
//          	mockjax1('search_system_examiners');
//          	table3 = dataTable('search_system_examiners');
         	
         	table1.ajax.reload();
//   			table3.ajax.reload();
  			
  			 $("#tab_id1").show();
//              $("#tab_id6").show();
             
             if($("#university_status").val() == "0") 
      		{
      					
      					$("#btn-reject-submit").hide();
      					$("#btn-reject-submit-ug").hide();
      		}
             if($("#university_status").val() == "1") 
     		{
     					
      					$("#btn-reject-submit").hide();
      					$("#btn-reject-submit-ug").hide();
      					$("#btn-save1").hide();
      					
     		}
             if($("#university_status").val() == "-1") 
      		{
      					$("#btn-save").hide();
      					$("#btn-reject-submit").show();
      					$("#btn-reject-submit-ug").show();
      					$("#btn-save1").hide();
      		}

        }

//----------------------VIEW-TABLE---------------------------

        function data(tablename){
       
        	 if(tablename=="search_system_ug"){
        		
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
        			var institute_name = $("#institute_name").val();
        			var academic_year_applied_for = $("#academic_year_applied_for").val();
        			var academic_file_upload = $("#academic_file_upload").val();
        			var permission_from_central_gov = $("#permission_from_central_gov").val();
        			var admission_intake = $("#admission_intake").val();
        			var num_of_student_admitted = $("#num_of_student_admitted").val();
//         			var country=$("#country").val();
//         			var state_id = $("#state_id").val();
//         			var district = $("#district").val();
//         			var city = $("#city").val();
        			var postal_address = $("#postal_address").val();
        			var email =$("#email").val();
        			var website= $("#website").val();
        			var remarks=$("#remarks").val();
        			var university_id = $("#university_id").val();
        			var university_status = $("#university_status").val();

        			$.post("getFilter_UG?" + key + "=" + value, {
        				
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
        								
        								if($("#university_status").val() == "0") {
        						    		   table.column(14).visible(false);
        						    	   }
        						    	   else{
        						    		   table.column(15).visible(true);
        						    	   }
        								if($("#university_status").val() == "1") {
     						    		   table.column(14).visible(false);
     						    		   table.column(15).visible(false);
     						    	   }
     						    	   else{
     						    		   table.column(13).visible(true);
     						    	   }        								
//         									jsondata.push([j[i].ser,//0
//         										j[i].institute_id,//1
//         										j[i].name_of_applicant_university,//2
//         										j[i].name_ug_course,//3
//         										j[i].abbre_undergraduate_course,//4
//         										j[i].academic_year_applied_for,//5
//         										j[i].admission_intake,//6
//         										j[i].num_of_student_admitted,//7
//             									j[i].remarks,//8
//             									j[i].vmp1,//9
//             									j[i].vmp2,//10
//             									j[i].reject_remarks,//11
//             									j[i].action]);//12
            									
            									jsondata.push([j[i].ser,//0
            										j[i].name_of_applicant_university,//1
            										j[i].name_ug_course,//2
            										j[i].abbre_undergraduate_course,//3
            										j[i].institute_name,//4
            										j[i].academic_year_applied_for,//5
            										j[i].vmp1,//6
            										j[i].vmp2,//7
            										j[i].admission_intake,//8
            										j[i].num_of_student_admitted,//9
            										j[i].postal_address,//10
            										j[i].email,//11
            										j[i].website,//12
                									j[i].remarks,//13
                									j[i].reject_remarks,//14
                									j[i].action]);//15
                									

        							}
        						});
        			$.ajaxSetup({
        				async : false
        			});
        			
        			$.post("getFilter_UGListCount?" + key + "=" + value, {
        				id:0,
        				university_id : university_id,
        				university_status : university_status
        				
        			}, function(j) {
        				FilteredRecords = j;
        				$("#ssug_hid").val(j);
        				}).fail(function(xhr, textStatus, errorThrown, exception) {
        				  	 alert(errorThrownMsg(xhr,exception));
        			});
        			setTimeout(setTimeLoadForTable1, 1000);
        	 }
//         	 else if(tablename=="search_system_examiners") {
          		
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
         			
//          			var name_of_examiners = $("#name_of_examiners").val();
//          			var subject_examiners = $("#subject_examiners").val();
//          			var year_examiners = $("#year_examiners").val();
//          			var qualification_examiners = $("#qualification_examiners").val();
//          			var teaching_experience = $("#teaching_experience").val();
//          			var teacher_code = $("#teacher_code").val();
//          			var reg_number = $("#reg_number").val();
//          			var d_university_appointment = $("#d_university_appointment").val();
//          			var university_id = $("#university_id").val();
//         			var university_status = $("#university_status").val();

//          			$.post("getFilter_Examiners_appointed?" + key + "=" + value, {
         				
//          				startPage : startPage,
//          				pageLength : pageLength,
//          				Search : Search,
//          				orderColunm : orderColunm,
//          				orderType : orderType,
//          				id:0,
//          				university_id : university_id,
//          				university_status : university_status
//          			},
//          				      function(j) {
//          				    	  console.log(j)
//          							for (var i = 0; i < j.length; i++) {
         								
//          								if($("#university_status").val() == "0") {
//      						    		   table.column(9).visible(false);
//      						    	   }
//      						    	   else{
//      						    		   table.column(10).visible(true);
//      						    	   }
//      								if($("#university_status").val() == "1") {
//   						    		   table.column(9).visible(false);
//   						    		   table.column(10).visible(false);
//   						    	   }
//   						    	   else{
//   						    		   table.column(8).visible(true);
//   						    	   }  
         								
//          	  								jsondata.push([j[i].ser,//0
//          	  									j[i].name_of_examiners,//1
//          	  									j[i].subject_examiners,//2
//          	  									j[i].year_examiners,//3
//              									j[i].qualification_examiners,//4
//              									j[i].teaching_experience,//5
//              									j[i].teacher_code,//6
//              									j[i].reg_number,//7
//              									j[i].d_university_appointment,//8
//              									j[i].reject_remarks,//9
//              									j[i].action]);//10
         								
//          							}
//          						});
//          			$.ajaxSetup({
//          				async : false
//          			});
         			
//          			$.post("getFilter_Examiners_appointedListCount?" + key + "=" + value, {
//          				id:0,
//          				university_id : university_id,
//          				university_status : university_status
//          			}, function(j) {
//          				FilteredRecords = j;
//          				$("#ssex_hid").val(j);
//          				}).fail(function(xhr, textStatus, errorThrown, exception) {
//          				  	 alert(errorThrownMsg(xhr,exception));
//          			});
//          			setTimeout(setTimeLoadForTable3, 1000);
//          	 }
         	 
}

///////////// UG FORM A /////////////////

function setTimeLoadForTable1(){
       document.querySelectorAll('.ADDUG').forEach((items, index) => {
        items.addEventListener('click', event => {
        		
        		var val=parseInt(index)+1;
        		var hid = document.getElementById('ugIdAGE'+val).value;
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
		
		items.addEventListener('click', event => {
			var sid = document.getElementById('DCounpdf'+val).value;
			getDownloadPdfattsub(sid);
		})
	});
     
	document.querySelectorAll('.pdfdownpermission').forEach((items, index) => {
		
		var val=parseInt(index)+1;
		
		items.addEventListener('click', event => {
			var ahid = document.getElementById('DPermissionpdf'+val).value;
			getDownloadPdfpermission(ahid);
		})
	});
	
}

function getDownloadPdfattsub(val){
	$("#upload_docformA").val(val);

	document.getElementById("search3").submit();
} 
function getDownloadPdfpermission(val){
	$("#upload_docformB").val(val);

	document.getElementById("search4").submit();
} 
function editData(id) {
        	document.getElementById('eid').value=id;	
        	document.getElementById('updateForm').submit();
}

///////////// UG FORM E/////////////////

// function setTimeLoadForTable3(){
//        document.querySelectorAll('.ADDExaminers').forEach((items, index) => {
//         items.addEventListener('click', event => {
        		
//         		var val=parseInt(index)+1;
//         		var hid = document.getElementById('examIdAGE'+val).value;
//         		if (confirm('Are You Sure You Want to Edit Detail ?')) {
//         			editData3(hid);
//         		} else {
//         			return false;
//         		}
//         	})
//         });
// }

// function editData3(id) {
	
//       	document.getElementById('exid').value=id;	
//       	document.getElementById('updateExaminersForm').submit();
// }

function Submit_Approval_Datafor_university(){
	var ssug_hid = $("#ssug_hid").val();
//  	var ssex_hid = $("#ssex_hid").val();
 	
		$.post("Submit_Approval_Data_university?" +  key + "=" + value, {ssug_hid:ssug_hid},function(data) {
			alert(data);
            location.reload();

	}).fail(function(xhr, textStatus, errorThrown) {
     alert("fail to fetch")
    });
}

function Submit_Approval_University_Reject_ug(){
	
	$.post("Submit_Approval_Reject_Data_ug?" +  key + "=" + value, function(data) {
		
		alert(data);
		location.reload();

}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
}
function Submit_Approval_University_Reject_examiners(){
	
	$.post("Submit_Approval_Reject_Data_examiners?" +  key + "=" + value, function(data) {
		
		alert(data);
		location.reload();

}).fail(function(xhr, textStatus, errorThrown) {
 alert("fail to fetch")
});
}


document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn-reload').onclick = function(){
			return select_status();
	};
	document.getElementById('btn-reject-submit-ug').onclick = function(){
		return Submit_Approval_University_Reject_ug();
	};
	document.getElementById('btn-save1').onclick = function(){
		    return Submit_Approval_Datafor_university();
	};
	document.getElementById('btn-reject-submit').onclick = function(){
	    return Submit_Approval_University_Reject_examiners()();
	};
});

</script>