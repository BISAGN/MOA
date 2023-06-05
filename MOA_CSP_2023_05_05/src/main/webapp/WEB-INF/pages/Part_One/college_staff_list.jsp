<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<body>
	<section class="dashboard-page">
		<div class="container-fluid">
			<!-- title-wrapper start -->
			<div class="title-wrapper pt-30">
				<div class="row align-items-center">
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="title mb-30">
							<h2>College Staff List</h2>
						</div>
					</div>
					<!-- end col -->
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="breadcrumb-wrapper mb-30">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>

									<li class="breadcrumb-item active" aria-current="page">
										College Staff List</li>
								</ol>
							</nav>
						</div>
					</div>
					<!-- end col -->
				</div>
				<!-- end row -->
			</div>
			<!-- title-wrapper end -->
			<div class="form-elements-wrapper custom_v_tab">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card-style mb-30">
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-12 col-12">
									<div class="custom-data-value custom-data-value-form">
										<label>Institution Name :</label> <span class="value-bind">${login_name}</span>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 col-12">
									<div class="custom-data-value custom-data-value-form">
										<label>College Code :</label> <span class="value-bind">${dataforinstnc[0][1]}</span>
									</div>

								</div>
							</div>
						</div>
					</div>
					<!--    ===========================
		 Staff List Start
===========================     -->
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card-style mb-30">
							<div class="tabs content_h700">
								<!-- 	Add Teaching Faculty Start -->
								<div class="tab">
									<button class="tab-toggle" id="defaultOpen">List of
										Teachers</button>
								</div>
								<div class="content">
									<h4 class="heading">List of Teachers</h4>
									<form:form name="teacher_detail" id="teacher_detail" action="" method="post" class=""
										commandName="">
										<div class="row">
										    <div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="inst_block simple-instruction">
													<strong>Instruction :</strong> Upload educational,
													experience, appointment, joining, ESI, PF, Form 16, account
													statement in single zip/pdf
												</div>
											</div>
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="teacherlist_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>
																		Name<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		PAN<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Post<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Department<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		UG / PG<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>Remarks</h6></th>
															</tr>
															<!-- end table row-->
														</thead>
														<tbody id="">
														${full_time_teacher_details}
														</tbody>
													</table>

												</div>
												<!-- end card -->
											</div>
											<!-- end col -->
										</div>
										<!-- end row -->
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover btnsave" id="teacher_details_save"
															type="button" value="Save"></li>
															<li><a class="main-btn dark-btn-outline btn-hover btn-iconic-icon viewData d-none"><i
															class="lni lni-eye"></i>View</a>
															<input type='hidden' id="viewId${parent_id}" value="${institude}"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- 	Add Teaching Faculty End -->
								<!-- 	Add Guest Faculty Start -->
								<div class="tab">
									<button class="tab-toggle">Guest faculty</button>
								</div>
								<div class="content">
									<h4 class="heading">Guest faculty</h4>
									<form:form name="guest_teacher_detail" id="guest_teacher_detail" action="" method="post" class=""
										commandName="">
										<div class="row">
										    <div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="inst_block simple-instruction">
													<strong>Instruction :</strong> Upload educational,
													experience, appointment, joining, ESI, PF, Form 16, account
													statement in single zip/pdf
												</div>
											</div>
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="gaustfaculty_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>
																		Name<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		PAN<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Post<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Department<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>Document</h6></th>
																<th><h6>
																		Remarks<strong class="mandatory">*</strong>
																	</h6></th>

															</tr>
															<!-- end table row-->
														</thead>
														<tbody id="">
														${guest_teacher_details}
														</tbody>
													</table>

												</div>
												<!-- end card -->
											</div>
											<!-- end col -->
										</div>
										<!-- end row -->
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover btnsave" id="guest_teacher_details_save"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- 	Add Guest Faculty End -->
								<!-- 	Add Teaching Faculty Start -->
								<div class="tab">
									<button class="tab-toggle">Teaching Faculty</button>
								</div>
								<div class="content">
									<h4 class="heading">Teaching Faculty</h4>
									<form:form name="teaching_faculty_detail" id="teaching_faculty_detail" action="" method="post" class=""
										commandName="">
										<div class="row">
										    <div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="inst_block simple-instruction">
													<strong>Instruction :</strong> Upload educational,
													experience, appointment, joining, ESI, PF, Form 16, account
													statement in single zip/pdf
												</div>
											</div>
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="teachfaculty_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>
																		Name<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		PAN<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Post<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Department<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>Document</h6></th>
																<th><h6>
																		Remarks<strong class="mandatory">*</strong>
																	</h6></th>

															</tr>
															<!-- end table row-->
														</thead>
														<tbody id="">
														${teaching_faculty_details}
														</tbody>
													</table>

												</div>
												<!-- end card -->
											</div>
											<!-- end col -->
										</div>
										<!-- end row -->
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover btnsave" id="teaching_faculty_details_save"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- 	Add Teaching Faculty End -->

								<!-- 	Add Non Teaching Faculty Start -->
								<div class="tab">
									<button class="tab-toggle">Non Teaching Faculty</button>
								</div>
								<div class="content">
									<h4 class="heading">Non Teaching Faculty</h4>
										<form:form name="non_teaching_faculty_detail" id="non_teaching_faculty_detail" action="" method="post" class=""
										commandName="">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="inst_block simple-instruction">
													<strong>Instruction :</strong> Upload educational,
													experience, appointment, joining, ESI, PF, Form 16, account
													statement in single zip/pdf
												</div>
											</div>
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="nonteachfaculty_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>
																		Name<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		PAN<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Post<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Department<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>Document</h6></th>
																<th><h6>
																		Remarks<strong class="mandatory">*</strong>
																	</h6></th>

															</tr>
															<!-- end table row-->
														</thead>
														<tbody id="">
														${getNon_Teaching_Faculty_details}
														</tbody>
													</table>

												</div>
												<!-- end card -->
											</div>
											<!-- end col -->
										</div>
										<!-- end row -->
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover btnsave" id="non_teaching_faculty_details_save"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- 	Add Non Teaching Faculty End -->
									<!-- Upload Document Start-->
								<div class="tab">
									<button class="tab-toggle">Upload Document</button>
								</div>
								<div class="content">
									<h4 class="heading">Upload Document</h4>
									<form:form name="document_detail" id="document_detail" action="" method="post" class=""
										commandName="">
										<div class="row">
										
										<input type="hidden" id="hid_document_detail" name="hid_document_detail" value="0">
										
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="inst_block simple-instruction">
									<strong>Instruction :</strong> Max Zip file upload size is limited to 15 MB only.
								</div>
							</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Scan copy of duly notarized affidavits of place of working by all the full time teachers
													<strong class="mandatory">*</strong></label> 
													<div class="input-group custom-input-group"> 
														<input type="file" id="full_time_teacher_affidavit" name="full_time_teacher_affidavit" class="form-control" accept=".zip">
														<input  type="hidden" id="hid_full_time_teacher_affidavit" name="hid_full_time_teacher_affidavit" value="0">
														
													<div class="note-text">
														<span class="errorClass" id="full_time_teacher_affidavit_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="full_time_teacher_affidavit_lbltik"></span>
													</div>
													
									<ul class="buttons-group">									
										<li><a  id="full_time_teacher_affidavit_download" href="AttachmentFilePath?i_id=${GetDocument_Details[0][0]}&doc_id=1" class="main-btn info-btn btn-hover btn-sm btndownload" title="Download Template">
										<i class="lni lni-download"></i></a></li>
									</ul></div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Scan copy of duly notarized affidavits of place of working by all the Guest faculty teachers
													<strong class="mandatory">*</strong></label>
													<div class="input-group custom-input-group">  
														<input type="file" id="guest_teacher_affidavit" name="guest_teacher_affidavit" class="form-control" accept=".zip">
														<input  type="hidden" id="hid_guest_teacher_affidavit" name="hid_guest_teacher_affidavit" value="0">
														
													<div class="note-text">
														<span class="errorClass" id="guest_teacher_affidavit_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="guest_teacher_affidavit_lbltik"></span>
													</div>
													
									<ul class="buttons-group">
										<li><a id="guest_teacher_affidavit_download" href="AttachmentFilePath?i_id=${GetDocument_Details[0][0]}&doc_id=2" class="main-btn info-btn btn-hover btn-sm btndownload" title="Download Template">
										<i class="lni lni-download"></i></a></li>
									</ul></div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Miscellaneous upload (documents related to teachers, which may be missed in teachers code portal)</label> 
													<input type="file" id="miscellaneous_doc" name="miscellaneous_doc" class="form-control" accept=".zip">
													<input  type="hidden" id="hid_miscellaneous_doc" name="hid_miscellaneous_doc" value="0">
													
													<div class="note-text">
														<span class="errorClass" id="miscellaneous_doc_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="miscellaneous_doc_lbltik"></span>
													</div>
													
												</div>
											</div>
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover btnsave" id="document_details_save"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- Upload Document End-->
								<div class="tab">
									<button class="tab-toggle">Declaration</button>
								</div>
								<div class="content ">
									<h4 class="heading">Declaration</h4>
									<form:form name="declaration_submit" id="declaration_submit" action="" method="post" class=""
										commandName="">
										<div class="top_content">
											<div class="row">
											
											<input type="hidden" id="hid_declaration_detail" name="hid_declaration_detail" value="0">
											
												<div class="col-lg-12 col-md-12 col-sm-12">
													<div class="input-style-1">
														<label>I <input type="text" name="declaration"
															id="declaration" class="form-control declaration_input"
															placeholder="Principal Name" readonly="readonly"> solemnly confirm that if any information provided by me found false, I shall be held responsible in the matter. I shall have
no objection if any legal action is taken by the CCH against me.
														</label>
													</div>
													<div class="custom-choose-one">
														<div class="input-style-form-check_block check-multi-list">
															<div class="form-check checkbox-style">
																<input type="checkbox" id="declaration_check"
																	name="declaration_check" autocomplete="off" maxlength="25"
																	class="form-check-input" value="1"> <label
																	class="check-list" for="Declaration">I Agree</label>
															</div>
														</div>
													</div>

												</div>
											</div>
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input
															class="main-btn success-btn btn-hover btnsubmit" id="final_submit"
															type="button" value="Submit"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>


							</div>
						</div>
					</div>
					<!--    ===========================
		Staff List End
===========================     -->
				</div>
			</div>
			<!-- end row -->
		</div>
		<!-- end container -->
		
		<div class="modal fade custom-modal" tabindex="-1" role="dialog"
				aria-labelledby="myLargeModalLabel" aria-hidden="true"
				id="teachlistremark">
				<div
					class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
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
											<textarea placeholder="Enter Remarks" rows="5"></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<ul class="buttons-group">
								<li><a type="button" class="main-btn dark-btn n btn-hover"
									data-bs-dismiss="modal">Close</a></li>
								<li><a class="main-btn success-btn  btn-hover" type="button">Submit</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			
			<div class="modal fade custom-modal" tabindex="-1" role="dialog"
				aria-labelledby="myLargeModalLabel" aria-hidden="true"
				id="guestremark">
				<div
					class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
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
											<textarea placeholder="Enter Remarks" rows="5"></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<ul class="buttons-group">
								<li><a type="button" class="main-btn dark-btn n btn-hover"
									data-bs-dismiss="modal">Close</a></li>
								<li><a class="main-btn success-btn  btn-hover" type="button">Submit</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			
			<div class="modal fade custom-modal" tabindex="-1" role="dialog"
				aria-labelledby="myLargeModalLabel" aria-hidden="true"
				id="teachingremark">
				<div
					class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
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
											<textarea placeholder="Enter Remarks" rows="5"></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<ul class="buttons-group">
								<li><a type="button" class="main-btn dark-btn n btn-hover"
									data-bs-dismiss="modal">Close</a></li>
								<li><a class="main-btn success-btn  btn-hover" type="button">Submit</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			
			<div class="modal fade custom-modal" tabindex="-1" role="dialog"
				aria-labelledby="myLargeModalLabel" aria-hidden="true"
				id="nonteachlistremark">
				<div
					class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
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
											<textarea placeholder="Enter Remarks" rows="5"></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<ul class="buttons-group">
								<li><a type="button" class="main-btn dark-btn n btn-hover"
									data-bs-dismiss="modal">Close</a></li>
								<li><a class="main-btn success-btn  btn-hover" type="button">Submit</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
	</section>
	<!--components end -->
	
		<c:url value="College_Staff_List_ViewUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="basic_clg_staff_list_id">
	<input type="hidden" name="basic_clg_staff_list_id" id="basic_clg_staff_list_id" value="0" />
	<input type="hidden" name="statusview" id="statusview" value="0" />
</form:form>

<script type="text/javascript"
		src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>
		
		
		
<script type="text/javascript" nonce="${cspNonce}">
	$(document).ready(function() {
		if ('${role}'=='NCH') {
			$(".viewData").addClass("d-none")
		}
		if ('${role}'=='Institute_NCH') {
			$(".viewData").removeClass("d-none")
		} 
		var valid_dt = '${valid_dt}';
		var y = valid_dt.substring(0, 4);
		var m = valid_dt.substring(5, 7);
		var d = valid_dt.substring(8, 10);
		var valid_dt1 = d + "/" + m + "/" + y;
		var today = new Date();
		var yyyy = today.getFullYear();
		var mm = today.getMonth() + 1; // Months start at 0!
		var dd = today.getDate();
		if (dd < 10)
			dd = '0' + dd;
		if (mm < 10)
			mm = '0' + mm;
		today = dd + '/' + mm + '/' + yyyy;
		var valid_dt2 = valid_dt.split('/').reverse().join('-');
		var today2 = today.split('/').reverse().join('-');
		if ("${hid}" != "3" && valid_dt2 <= today2) {
// 			datepicketDate('lsad1');
		}
		
		$("#declaration").val('${GetPrinacipal_Name[0][0]}');
		var declaration_check = '${GetDocument_Details[0][3]}'
			$("input[name='declaration_check'][value='" + declaration_check + "']").prop('checked', true);
		
		getTeacher_Details();
		getGuest_Teacher_Details();
		getTeaching_Faculty_Details();
		getNon_Teaching_Faculty_Details();
		getDocument_Details();
	
	});
	
// 	--------------------------------------------START-->TEACHER DETAILS---------------------------------------------
	
	//SAVE TEACHERS DETAILS
	function Save_As_Draft_Teacher_Details(sd) {
		
		$.ajaxSetup({
		    async: false
		});	
		
		<c:forEach var="j" items="${getAllFull_Time_Teacher_Details}" varStatus="num">
		var ugpgcheck = $("input[name='ugpgcheck${j[0]}']:checked").val();
		if( ugpgcheck == null ){
			alert("Please Select UG/PG");
			$('#ug'+${j[0]}).focus();
			return false;
	   	}
		</c:forEach>
		
		var form_data = new FormData(document.getElementById("teacher_detail"));
		$.ajax({
			url : 'Teacher_Details_Save_Draft_Action?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(j){
          if(j>0){
        	  alert("Data Save Sucessfully");
          }
          else{
        	  if(sd != -1){
        	  alert(j);
        	  }
//         	  var hid_value = $('#hid_passed_student_').val();
//         	  if(hid_value== "0"){
        	  location.reload();
//         	  }
          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
			
	 }
	
	//FETCH TEACHERS DETAILS
	function getTeacher_Details() {
		$.ajaxSetup({
			async : false
		});
		
		$.post("getTeacher_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
			$("#hid_teacher_details"+j[i].teacher_id).val(j[i].id);
			var ug_pg_check = j[i].ug_pg_check;
			$("input[name='ugpgcheck"+j[i].teacher_id+"'][value='"+ug_pg_check+"']").prop('checked', true);
			$("#remark"+j[i].teacher_id).val(j[i].remark);
			
			}
			
		});
	}
	
// 	--------------------------------------------END-->TEACHER DETAILS---------------------------------------------


// 	--------------------------------------------START-->GUEST TEACHER DETAILS---------------------------------------------
	
	//SAVE TEACHERS DETAILS
	function Save_As_Draft_Guest_Teacher_Details(sd) {
		
		$.ajaxSetup({
		    async: false
		});	

		<c:forEach var="j" items="${getAllGuest_teacher_details}" varStatus="num">
		var guest_teacher_doc = CheckNullorBlank('hid_guest_teacher_doc'+${j[0]});
		if(guest_teacher_doc !== "true"){
			var res = CheckNullorBlank('guest_teacher_doc'+${j[0]});
			if (res !== "true") {
				alert("Please Upload Document");
				$('#guest_teacher_doc'+${j[0]}).focus();
				return false;
			}
		}
		var res = CheckNullorBlank('guest_teacher_remark'+${j[0]});
		if (res !== "true") {
			alert(res +"Remark");
			$('#guest_teacher_remark'+${j[0]}).focus();
			return false;
		}
		</c:forEach>
		
		var form_data = new FormData(document.getElementById("guest_teacher_detail"));
		$.ajax({
			url : 'Guest_Teacher_Details_Save_Draft_Action?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(j){
          if(j>0){
        	  alert("Data Save Sucessfully");
          }
          else{
        	  if(sd != -1){
        	  alert(j);
        	  }
        	  location.reload();
          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
			
	 }
	
	//FETCH GUEST TEACHERS DETAILS
	function getGuest_Teacher_Details() {
		$.ajaxSetup({
			async : false
		});
		
		$.post("getGuest_Teacher_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
			$("#hid_guest_teacher_details"+j[i].guest_teacher_id).val(j[i].id);
			$("#hid_guest_teacher_doc"+j[i].guest_teacher_id).val(j[i].guest_teacher_doc);
			$("#guest_teacher_remark"+j[i].guest_teacher_id).val(j[i].guest_teacher_remark);
			
			}
			
		});
	}
	
// 	--------------------------------------------END-->GUEST TEACHER DETAILS---------------------------------------------


// 	--------------------------------------------START-->TEACHING FACULTY DETAILS---------------------------------------------
	
	//SAVE TEACHERS DETAILS
	function Save_As_Draft_Teaching_Faculty_Details(sd) {
		
		$.ajaxSetup({
		    async: false
		});	

		<c:forEach var="j" items="${getAllFull_Time_Teacher_Details}" varStatus="num">
		var hid_teacher_doc = CheckNullorBlank('hid_teacher_doc'+${j[0]});
		if(hid_teacher_doc !== "true"){
			var res = CheckNullorBlank('teacher_doc'+${j[0]});
			if (res !== "true") {
				alert("Please Upload Document");
				$('#teacher_doc'+${j[0]}).focus();
				return false;
			}
		}
		var res = CheckNullorBlank('teacher_remark'+${j[0]});
		if (res !== "true") {
			alert(res +"Remark");
			$('#teacher_remark'+${j[0]}).focus();
			return false;
		}
		</c:forEach>
		
		var form_data = new FormData(document.getElementById("teaching_faculty_detail"));
		$.ajax({
			url : 'Teaching_Staff_Details_Save_Draft_Action?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(j){
          if(j>0){
        	  alert("Data Save Sucessfully");
          }
          else{
        	  if(sd != -1){
        	  alert(j);
        	  }
        	  location.reload();
          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
			
	 }
	
	//FETCH GUEST TEACHERS DETAILS
	function getTeaching_Faculty_Details() {
		$.ajaxSetup({
			async : false
		});
		
		$.post("getTeaching_Staff_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
			$("#hid_teaching_faculty_details"+j[i].teacher_id).val(j[i].id);
			$("#hid_teacher_doc"+j[i].teacher_id).val(j[i].teacher_doc);
			$("#teacher_remark"+j[i].teacher_id).val(j[i].teacher_remark);
			
			}
			
		});
	}
	
// 	--------------------------------------------END-->TEACHING FACULTY DETAILS---------------------------------------------



// 	--------------------------------------------START-->NON TEACHING FACULTY DETAILS---------------------------------------------
	
	//SAVE TEACHERS DETAILS
	function Save_As_Draft_Non_Teaching_Faculty_Details(sd) {
		
		$.ajaxSetup({
		    async: false
		});	

		<c:forEach var="j" items="${Non_Teaching_Faculty_details}" varStatus="num">
		var hid_non_teacher_doc = CheckNullorBlank('hid_non_teacher_doc'+${j[0]});
		if(hid_non_teacher_doc !== "true"){
			var res = CheckNullorBlank('non_teacher_doc'+${j[0]});
			if (res !== "true") {
				alert("Please Upload Document");
				$('#non_teacher_doc'+${j[0]}).focus();
				return false;
			}
		}
		var res = CheckNullorBlank('non_teacher_remark'+${j[0]});
		if (res !== "true") {
			alert(res +"Remark");
			$('#non_teacher_remark'+${j[0]}).focus();
			return false;
		}
		</c:forEach>
		
		var form_data = new FormData(document.getElementById("non_teaching_faculty_detail"));
		$.ajax({
			url : 'Non_Teaching_Faculty_Details_Save_Draft_Action?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(j){
          if(j>0){
        	  alert("Data Save Sucessfully");
          }
          else{
        	  if(sd != -1){
        	  alert(j);
        	  }
        	  location.reload();
          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
			
	 }
	
	//FETCH GUEST TEACHERS DETAILS
	function getNon_Teaching_Faculty_Details() {
		$.ajaxSetup({
			async : false
		});
		
		$.post("getNon_Teaching_Faculty_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
			$("#hid_non_teaching_faculty_details"+j[i].non_teacher_id).val(j[i].id);
			$("#hid_non_teacher_doc"+j[i].non_teacher_id).val(j[i].non_teacher_doc);
			$("#non_teacher_remark"+j[i].non_teacher_id).val(j[i].non_teacher_remark);
			
			}
			
		});
	}
	
// 	--------------------------------------------END-->NON TEACHING FACULTY DETAILS---------------------------------------------



// 	--------------------------------------------START-->UPLOAD DOCUMENT DETAILS---------------------------------------------
	
	//SAVE TEACHERS DETAILS
	function Save_As_Draft_Document_Details(sd) {
		
		$.ajaxSetup({
		    async: false
		});	

		var hid_full_time_teacher_affidavit = CheckNullorBlank('hid_full_time_teacher_affidavit');
		if(hid_full_time_teacher_affidavit !== "true"){
			var res = CheckNullorBlank('full_time_teacher_affidavit');
			if (res !== "true") {
				alert("Please Upload Scan copy of duly notarized affidavits of place of working by all the full time teachers");
				$('#full_time_teacher_affidavit').focus();
				return false;
			}
		}
		var hid_guest_teacher_affidavit = CheckNullorBlank('hid_guest_teacher_affidavit');
		if(hid_guest_teacher_affidavit !== "true"){
			var res = CheckNullorBlank('guest_teacher_affidavit');
			if (res !== "true") {
				alert("Please Upload Scan copy of duly notarized affidavits of place of working by all the Guest faculty teachers");
				$('#guest_teacher_affidavit').focus();
				return false;
			}
		}
		
		var form_data = new FormData(document.getElementById("document_detail"));
		$.ajax({
			url : 'Document_Details_Save_Draft_Action?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(j){
          if(j>0){
        	  $("#hid_document_detail").val(j);
        	  $("#hid_declaration_detail").val(j);
        	  alert("Data Save Sucessfully");
        	  location.reload();
          }
          
          else{
        	  if(sd != -1){
        	  alert(j);
        	  }
        	  location.reload();
          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
			
	 }
	
	//FETCH GUEST TEACHERS DETAILS
	function getDocument_Details() {
		$.ajaxSetup({
			async : false
		});
		
		$.post("getDocument_Details?" + key + "=" + value, {
			
		}, function(j) {
				
			$("#hid_document_detail").val(j[0].id);
			$("#hid_declaration_detail").val(j[0].id);
			$("#hid_full_time_teacher_affidavit").val(j[0].full_time_teacher_affidavit);
			$("#hid_guest_teacher_affidavit").val(j[0].guest_teacher_affidavit);
			$("#hid_miscellaneous_doc").val(j[0].miscellaneous_doc);
			
			
		});
	}
	
	
	//SAVE DECLARATION CHECK
	function Save_As_Draft_Declaration_check(sd) {
		
		$.ajaxSetup({
		    async: false
		});	
		
		var declaration_check = $("input[name='declaration_check']:checked").val();
		if (declaration_check == null) {
			alert("Please Accept Terms And Condition");
			return false;
		}
		
		Save_As_Draft_Teacher_Details(-1);
		Save_As_Draft_Guest_Teacher_Details(-1);
		Save_As_Draft_Teaching_Faculty_Details(-1);
		Save_As_Draft_Non_Teaching_Faculty_Details(-1);
		Save_As_Draft_Document_Details(-1);
		
		var form_data = new FormData(document.getElementById("declaration_submit"));
		$.ajax({
			url : 'Declaration_check_Save_Draft_Action?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(j){
          if(j>0){
        	  $("#hid_document_detail").val(j);
        	  $("#hid_declaration_detail").val(j);
        	  alert("Data Save Sucessfully");
        	  location.reload();
          }
          else{
        	  if(sd != -1){
        	  alert(j);
        	  location.reload();
        	  }
          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
			
	 }
	
// 	--------------------------------------------END-->UPLOAD DOCUMENT DETAILS---------------------------------------------


// ================================CSP START=================================
	
	document.addEventListener('DOMContentLoaded', function() {
		
		//FULL TIME TEACHER START
		document.getElementById('teacher_details_save').onclick = function() {
			return Save_As_Draft_Teacher_Details();	
		};
		<c:forEach var="j" items="${getAllFull_Time_Teacher_Details}" varStatus="num">
		
		document.getElementById('remark'+${j[0]}).onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		
		</c:forEach>
		
		//GUEST TEACHER START
		document.getElementById('guest_teacher_details_save').onclick = function() {
			return Save_As_Draft_Guest_Teacher_Details();	
		};
		
		<c:forEach var="j" items="${getAllGuest_teacher_details}" varStatus="num">
		
		document.getElementById('guest_teacher_remark'+${j[0]}).onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('guest_teacher_doc'+${j[0]}).onchange = function() {
			return pdfFileSizeValidation(this,this.value,"guest_teacher_doc"+${j[0]},"200kb","guest_teacher_doc_lbltik"+${j[0]},"guest_teacher_doc_lbl"+${j[0]},this.accept)
		};
		
		</c:forEach>
		
		//TEACHING FACULTY START
		document.getElementById('teaching_faculty_details_save').onclick = function() {
			return Save_As_Draft_Teaching_Faculty_Details();	
		};
		<c:forEach var="j" items="${getAllFull_Time_Teacher_Details}" varStatus="num">
		
		document.getElementById('teacher_remark'+${j[0]}).onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('teacher_doc'+${j[0]}).onchange = function() {
			return pdfFileSizeValidation(this,this.value,"teacher_doc"+${j[0]},"200kb","teacher_doc_lbltik"+${j[0]},"teacher_doc_lbl"+${j[0]},this.accept)
		};
		
		</c:forEach>
		
		
		//NON TEACHING FACULTY START
		document.getElementById('non_teaching_faculty_details_save').onclick = function() {
			return Save_As_Draft_Non_Teaching_Faculty_Details();	
		};
		<c:forEach var="j" items="${Non_Teaching_Faculty_details}" varStatus="num">
		
		document.getElementById('non_teacher_remark'+${j[0]}).onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('non_teacher_doc'+${j[0]}).onchange = function() {
			return pdfFileSizeValidation(this,this.value,"non_teacher_doc"+${j[0]},"200kb","non_teacher_doc_lbltik"+${j[0]},"non_teacher_doc_lbl"+${j[0]},this.accept)
		};
		
		</c:forEach>
		
		
		//UPLOAD DOCUMENT START
		document.getElementById('document_details_save').onclick = function() {
			return Save_As_Draft_Document_Details();	
		};
		document.getElementById('full_time_teacher_affidavit').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"full_time_teacher_affidavit","15mb","full_time_teacher_affidavit_lbltik","full_time_teacher_affidavit_lbl",this.accept)
		};
		document.getElementById('guest_teacher_affidavit').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"guest_teacher_affidavit","15mb","guest_teacher_affidavit_lbltik","guest_teacher_affidavit_lbl",this.accept)
		};
		document.getElementById('miscellaneous_doc').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"miscellaneous_doc","15mb","miscellaneous_doc_lbltik","miscellaneous_doc_lbl",this.accept)
		};
		
		//DECLARATION CHECK START
		document.getElementById('final_submit').onclick = function() {
			return Save_As_Draft_Declaration_check();
		};
		
	});
	
// ================================CSP END=================================
	
	document.querySelectorAll('.viewData').forEach((items, index) => {
	items.addEventListener('click', event => {
		var val=parseInt(index)+1;
		var hid = document.getElementById('viewId${parent_id}').value;
		if (confirm('Are You Sure You Want to View Detail ?')) {
			ViewData(hid);
		} else {
			return false;
		}
	})
});

function ViewData(id){

$("#basic_clg_staff_list_id").val(id);
$("#statusview").val($("#statusA").val());
document.getElementById('viewForm').submit();
}

</script>
