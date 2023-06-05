<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>


<!-- datatable style and js end-->
<link rel="stylesheet" 	href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- <script src="js/excel/xlsx-0.7.7.core.min.js"></script>   -->
<!-- <script src="js/excel/xls-0.7.4.core.min.js"></script>  -->
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
							<span id="lbladd">UG Student Details</span>
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

							<!-- Start Form B -->

							<div class="tab tablinks dg-rec-block" id="tab_id2">
								<button class="tab-toggle">Admitted Students</button>
							</div>

							<div class="content tabcontent" id="Form_B">
								<form id="Form_B_details" enctype="multipart/form-data">
									<input type="hidden" id="h_id" name="h_id" value="a2">
									<input type="hidden" id="form_b_id" name="form_b_id" value="0"
										class="form-control autocomplete">

									<h4 class="heading">Add Admitted Students For Each
										College/Institute As Per Proforma Provide</h4>

									<!-- --------------------upload excel---------------------- -->

									<div class="row">
										<div class="col-12 col-sm-12 col-md-12 col-lg-12">
											<div class="custom-choose-one">
													<div class="input-style-1 mb-0">
													<h5 class="text-medium mb-10">Choose Any One</h5>
												</div>
												<div class="input-style-form-check">
													<div class="form-check radio-style">
														<input type="radio" class="form-check-input" id="Upload"
															name="Choise" value="Upload"> <label for="Upload"
															class="form-check-label">Upload Through Excel</label>
													</div>
													<div class="form-check radio-style">
														<input type="radio" class="form-check-input" id="Fillform"
															name="Choise" value="Fillform" checked="checked">
														<label class="form-check-label" for="Fillform">Fill
															Up Form</label>
													</div>
												</div>
											</div>

											<!-- end input -->
										</div>

										<input type="hidden" id="actiontype" name="actiontype"
											value="add"> <input type="hidden" id="seid"
											name="seid" value="0">

									</div>

									<div id="UploadExcel" class="hide">
										<div class="row">
											<div class="col-12 col-sm-12 col-md-6 col-lg-4">
												<div class="input-style-2">
													<label>Upload Excel<span class="mandatory">*</span></label>
													<input type="file" class="form-control" accept=".xls"
														placeholder="upload your document" name="upload_excel"
														id="upload_excel" tabindex="5"> <span
														class='errorClass' id='upload_excel_lbl'>${upload_excel_lbl_msg}</span>
												</div>
												<!-- end input -->
											</div>

										</div>
									</div>

									<!----------------------end upload excel---------------------- -->
									<div id="fillform" class="">
										<div class="row">
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Name Of Student <strong class="mandatory">*</strong></label>
													<input type="text" name="student_name" id="student_name"
														class="form-control" placeholder="Enter Student Name"
														maxlength="25" autocomplete="off">
												</div>
											</div>
								
									
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date of Admission<strong class="mandatory">*</strong></label>
													<input type="text" name="date_of_admission"
														id="date_of_admission" maxlength="10"
														class="form-control-sm form-control" aria-required="true"
														autocomplete="off" value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>Neet Rank<strong class="mandatory">*</strong></label>
													<div class="form-check radio-style">
														 <input type="radio" id="neet_rank1" name="neet_rank"
															class="form-check-input" value="Yes"> 
															<label class="form-check-label" for="radio-1">Yes</label>
													</div>
													<div class="form-check radio-style">
														 <input type="radio" name="neet_rank" id="neet_rank2"
															class="form-check-input" value="No"> <label class="form-check-label" for="radio-1">No</label>
													</div>
												</div>
											</div>
											
											<div class="custom-d-none" id="mention_div">
							<div class="row">
								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="input-style-2">
										<label>Rank<strong class="mandatory">*</strong></label> 
											<div class="with_icon">
											<input type="text" name="rank" id="rank" class="form-control"
														placeholder="Enter Rank"
														autocomplete="off" maxlength="5">
											 </div> 
									</div>
								</div>
							</div>
						</div>

<!-- 											<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 												<div class="input-style-2"> -->
<!-- 													<label>Rank<strong class="mandatory">*</strong></label>  -->
<!-- 													<input type="text" name="rank" id="rank" class="form-control" -->
<!-- 														placeholder="Enter Rank" -->
<!-- 														autocomplete="off" maxlength="5"> -->
<!-- 												</div> -->
<!-- 											</div> -->
											
											
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Marks<strong class="mandatory">*</strong></label> 
													<input type="text" name="marks" id="marks" class="form-control"
														placeholder="Enter Marks" autocomplete="off" maxlength="3">
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>All India Quota<strong class="mandatory">*</strong></label>
													<div class="form-check radio-style">
														 <input type="radio" id="all_india1" name="all_india"
															class="form-check-input" value="Yes"> 
															<label class="form-check-label" for="radio-1">Yes</label>
													</div>
													<div class="form-check radio-style">
														 <input type="radio" name="all_india" id="all_india2"
															class="form-check-input" value="No"> <label class="form-check-label" for="radio-2">No</label>
													</div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>State Quota<strong class="mandatory">*</strong></label>
													
													<div class="form-check radio-style">
														<input type="radio" name="state" id="state1"
															class="form-check-input" value="Yes"><label class="form-check-label" for="radio-1">Yes</label>
													</div>
													<div class="form-check radio-style">
														 <input type="radio" name="state" id="state2"
															class="form-check-input" value="No"><label class="form-check-label" for="radio-1">No</label>
													</div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>Management Quota<strong class="mandatory">*</strong></label>
													
													<div class="form-check radio-style">
														<input type="radio" name="management_quota" id="management_quota1"
															class="form-check-input" value="Yes"><label class="form-check-label" for="radio-1">Yes</label>
													</div>
													<div class="form-check radio-style">
														 <input type="radio" name="management_quota" id="management_quota2"
															class="form-check-input" value="No"><label class="form-check-label" for="radio-1">No</label>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>Admission Authority<strong class="mandatory">*</strong></label>
													<div class="form-check radio-style">
														 <input type="radio" name="admission_authority"
															id="admission_authority1" class="form-check-input"
															value="Yes"> 
															<label class="form-check-label" for="radio-1">Yes</label>
													</div>
													<div class="form-check radio-style">
														 <input type="radio" name="admission_authority"
															id="admission_authority2" class="form-check-input"
															value="No"> 
															<label class="form-check-label" for="radio-1">No</label>
													</div>
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>Court Order and Others<strong class="mandatory">*</strong></label>
													
													<div class="form-check radio-style">
														<input type="radio" name="court_order" id="court_order1"
															class="form-check-input" value="Yes">
															<label class="form-check-label" for="radio-1"> Yes</label>
													</div>
													<div class="form-check radio-style">
														 <input type="radio" name="court_order" id="court_order2"
															class="form-check-input" value="No">
															<label class="form-check-label" for="radio-1">No</label>
													</div>
												</div>
											</div>
											
<!-- 						<div class="custom-d-none" id="mention_div"> -->
<!-- 							<div class="row"> -->
<!-- 								<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 									<div class="input-style-2"> -->
<!-- 										<label>Upload File Court Order<strong class="mandatory">*</strong></label>  -->
<!-- 											<div class="with_icon"> -->
<!-- 											<input type="file" accept=".pdf" id="court_order_file"   -->
<!--  														name="court_order_file" class="form-control profile_image">  -->
<!-- 											 </div>  -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->

						
								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="input-style-2">
										<label>Upload The Copy Of Court Order<strong class="mandatory">*</strong></label> 
											<div class="with_icon">
											<input type="file" accept=".pdf" id="court_order_file1"  
 														name="court_order_file1" class="form-control profile_image"> 
 														
 											<input type="hidden" id="document_upload_hid1" name="document_upload_hid1" class="form-control" >
 											
											 </div> 
									</div>
								</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date of Enrollment In University<strong
														class="mandatory">*</strong></label> 
														<input type="text" name="date_of_enroll_university"	id="date_of_enroll_university" maxlength="10"
														class="form-control-sm form-control effect-9"
														aria-required="true" autocomplete="off" value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>University Enrollment Number<strong
														class="mandatory">*</strong></label> 
														<input type="text"	name="uni_enroll_number" id="uni_enroll_number"
														class="form-control-sm form-control effect-9"
														aria-required="true"
														oninput="this.value = this.value.toUpperCase()"
														maxlength="15"
														 autocomplete="off"
														placeholder="Enter University Number">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date Of Internship Completion<strong
														class="mandatory">*</strong></label> 
														<input type="text" name="date_of_intern_compl" id="date_of_intern_compl"
														maxlength="10" class="form-control-sm form-control effect-9"
														aria-required="true" autocomplete="off" value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Remarks,If Any</label>
													<textarea name="remarks_form_b" id="remarks_form_b"
														class="form-control" placeholder="Enter Remarks"
														autocomplete="off" maxlength="500"></textarea>
												</div>
											</div>
											<input type="hidden" id="userId" name="userId" value="${userId}">
										</div>
									</div>
									<div>
										<ul class="buttons-group mainbtn">
<!-- 											<li><input type="button" id="btn_save" -->
<!-- 												class="main-btn info-btn btn-hover btnsave" value="Save Details"> -->
												
												<li><a type="button" id="btn_save_a" class="main-btn info-btn btn-hover btnsave" >Save Details</a>
								          </li>
<!-- 											<li><a href="StudentEdit_Url" id="btn-reload" -->
<!-- 												class="main-btn deactive-btn btn-hover btnupda" -->
<!-- 												type="button">Edit & View Details</a></li> -->
												
												<li><a href="Student_Details_UG"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
									
											<li id="export_btn" class="dg-rec-none">
											<a href="/js/upload_xls/Degree_Recognition/export_form_A.xls"
												name='downlaod' id='downlaod' tabindex="4"
												class="main-btn active-btn btn-hover  btn-iconic-icon btnexport"> 
												<i class="lni lni-share-alt"></i>Export Sample Template Format</a></li>
										</ul>
									</div>
								</form>
							</div>
							<!-- Start Form C -->

							<div class="tab tablinks d-none" id="tab_id3" >
								<button class="tab-toggle"> Hospital Attached</button>
							</div>
							<div id="Form_C" class="content tabcontent">
								<form id="Form_C_details">
										<input type="hidden" id="form_c_id" name="form_c_id" value="0">
									<h4 class="heading">Add Details College Wise/Institute
										Wise Regarding The Hospital Attached</h4>
									<div class="row">

<!-- 										<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 											<div class="select-style-1"> -->
<!-- 												<input type="hidden" id="id_c" name="id_c"> <label>Name -->
<!-- 													Of Homoeopathic Medical College/Institute<strong -->
<!-- 													class="mandatory">*</strong> -->
<!-- 												</label> -->
<!-- 												<div class="select-position"> -->
<!-- 													<select name="name_homoeopathic_medical_clg" -->
<!-- 														id="name_homoeopathic_medical_clg" class="singleselect form-control form-control-lg"> -->
<!-- 														<option value="0">---Select institute---</option> -->
<%-- 														<c:forEach var="item" items="${getInstituteListbyUserID}" --%>
<%--  															varStatus="num">  --%>
<%-- 															<option value="${item.institute_name}" name="${item.institute_name}">${item.institute_name}</option>  --%>
<%-- 														</c:forEach>  --%>
<!-- 													</select> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->

										
									<div class="col-lg-6 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Name Of Homoeopathic Medical College/Institute<strong class="mandatory">*</strong>
												</label> <input type="text" name="name_homoeopathic_medical_clg"
													id="name_homoeopathic_medical_clg" class="form-control"
													placeholder="Enter name of Attached Homoeopathic Hospital"
													autocomplete="off" maxlength="100" readonly>
											</div>
										</div>
										<div class="col-lg-6 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Name Of Attached Homoeopathic
													Hospital/Institute<strong class="mandatory">*</strong>
												</label> <input type="text" name="attached_homoeopath_hospital"
													id="attached_homoeopath_hospital" class="form-control"
													placeholder="Enter name of Attached Homoeopathic Hospital"
													autocomplete="off" maxlength="100">
											</div>
										</div>

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Name Of Attached Super Speciality Hospital<strong
													class="mandatory">*</strong>
												</label> <input name="super_speciality_hospital"
													id="super_speciality_hospital" class="form-control"
													placeholder="Enter Name of Attached Super Speciality Hospital"
													autocomplete="off" maxlength="100">
											</div>
										</div>

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>MOU Date<strong class="mandatory">*</strong></label>
												<input type="text" name="mou_date" id="mou_date"
													maxlength="10"
													class="form-control-sm form-control effect-9 "
													aria-required="true" autocomplete="off" value="DD/MM/YYYY">
											</div>
										</div>
<!-- 										<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 											<div class="input-style-2"> -->
<!-- 												<label>Copy Of MOU Date<strong class="mandatory">*</strong></label> -->
<!-- 												<input type="file" name="copy_of_mou" id="copy_of_mou" -->
<!-- 													class="form-control" accept=".pdf"> -->
<!-- 											</div> -->
<!-- 										</div> -->
										
											<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
													<label for="text-input">Copy Of MOU<strong class="mandatory">* </strong>
													</label>
													<div class="with_icon">
														<input type="file" id="copy_of_mou"
															name="copy_of_mou" accept=".pdf"
															class="form-control"> 
													</div>
												</div>
											</div>
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Name Of Hospital Staff<strong
													class="mandatory">*</strong></label> <input
													name="name_of_hospital_staff" id="name_of_hospital_staff"
													class="form-control"
													placeholder="Enter Name of Hospital Staff"
													autocomplete="off" maxlength="50">
											</div>
										</div>

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="select-style-1">
												<label>Designation<strong class="mandatory">*</strong>
												</label>
												<div class="select-position">
													<select name="designation" id="designation"
														class="singleselect form-control form-control-lg" aria-invalid="false">
														<option value="0" selected="selected">-- Select
															Designation --</option>
														<c:forEach var="item" items="${getDesignationList}"
															varStatus="num">
															<option value="${item.designation}" name="${item.designation}">${item.designation}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Qualification<strong class="mandatory">*</strong></label>
												<input name="qualification" id="qualification"
													class="form-control" placeholder="Enter Qualification"
													autocomplete="off" maxlength="50">
											</div>
										</div>

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-form-check">
												<label>Full Time/Part Time<strong class="mandatory">*</strong></label>
												<div class="form-check radio-style">
													<input type="radio" id="fulltime_parttime1"
														name="fulltime_parttime" class="form-check-input"
														value="Full Time"> <label class="form-check-label"
														for="radio-1">Full Time</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" id="fulltime_parttime2"
														name="fulltime_parttime" class="form-check-input"
														value="Part Time"> <label class="form-check-label"
														for="radio-1">Part Time</label>
												</div>
											</div>
										</div>

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Remarks</label>
												<textarea type="text" name="remarks_form_c"
													id="remarks_form_c" class="form-control"
													placeholder="Enter Remarks" autocomplete="off"
													maxlength="500"></textarea>
											</div>
										</div>
										<input type="hidden" id="userId" name="userId"
											value="${userId}">

										<div class="col-lg-12 col-md-12 col-sm-12">
											<ul class="buttons-group mainbtn">
<!-- 												<li><input type="button" id="btn-save" -->
<!-- 													class="main-btn info-btn btn-hover btnsave" value="Save Details"></li> -->
<li><a type="button" id="btn-save" class="main-btn info-btn btn-hover btnsave" >Save Details</a>
												<li><a href="StudentEdit_Url" id="btn-reload"
													class="main-btn deactive-btn btn-hover btnupda"
													type="button"><i></i>Edit & View Details</a></li>
													<li><a href="Student_Details_UG"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>

											</ul>
										</div>

									</div>

								</form>
							</div>

							<div class="tab tablinks dg-rec-block" id="tab_id4">
								<button class="tab-toggle">Migrated Students</button>
							</div>

							<div id="Form_D" class="content tabcontent">
							
								<form id="Form_D_details">
								
									<h4 class="heading">Add Details For Those Who Migrated To
										Other College</h4>
									<!-- <span class="mandatory" >(All fields are mandatory)</span> -->
									<div class="row">
										<div class="table-wrapper table-responsive simple-table">
											<input type="hidden" id="sb_hid" name="sb_hid"/>
											<table class="table" id="family_table_to">
										
												<thead>
													<tr>
														<th><h6>S.No</h6></th>
														<th><h6>Name of Institution</h6></th>
														<th><h6>Name of Student</h6></th>
														<th><h6>Date Of Migration</h6></th>
														<th><h6>Professional Year Of Migration</h6></th>
														<th><h6>University Enrolment Number</h6></th>
														<th><h6>Remarks</h6></th>
														<th class="hide-action"><h6>Action</h6></th>
													</tr>
												</thead>
						<tbody id="family_sibtbody_to">
							<tr id="tr_sibling_to1">
								<td>1</td>
								<td>
									<div class="">
										<div class="select-style-1">
											<div class="select-position">
												<select name="name_of_inst1" id="name_of_inst1"
													class="form-control">

													<option value="0">-Select-</option>
													<c:forEach var="item" items="${getInstituteListbyUserID}"
 														varStatus="num"> 
 														<option value="${item.institute_name}"
 															name="${item.institute_name.trim()}">${item.institute_name.trim()}</option> 
													</c:forEach> 
												</select>
											</div>
										</div>
									</div>
									
									
<!-- 										<div class=""> -->
<!-- 											<div class="input-style-2"> -->
<!-- 												 <input type="text" name="name_of_inst1" -->
<!-- 													id="name_of_inst1" class="form-control" -->
<!-- 													placeholder="Enter name of Attached Homoeopathic Hospital" -->
<!-- 													autocomplete="off" maxlength="100" readonly> -->
<!-- 											</div> -->
<!-- 										</div> -->
										
								</td>

								<td>
									<div class="">
										<div class="input-style-2">
											<input type="text" name="student_name_to_migrated1"
												id="student_name_to_migrated1" class="form-control"
												placeholder="Enter student name" maxlength="25" autocomplete="off">
										</div>
									</div>
								</td>
								<td>
									<div class="">
										<div class="input-style-2">
											<input type="text" name="migrated_dt_to1"
												id="migrated_dt_to1" maxlength="10"
												class="form-control"
												aria-required="true" autocomplete="off"
												 value="DD/MM/YYYY">
										</div>
									</div>
								</td>
								<td>
									<div class="">
										<div class="input-style-2">
											<input type="month" name="professional_year_migrated1"
												id="professional_year_migrated1" maxlength="10"
												class="form-control" aria-required="true"
												autocomplete="off" value="YYYY">
										</div>
									</div>
								</td>
								<td>
									<div class="">
										<div class="input-style-2">
											<input type="text" name="university_enrollment_number1"
												id="university_enrollment_number1"
												class="form-control"
												placeholder="Enter University Enrollment Number"  autocomplete="off">
										</div>
									</div>
								</td>
								<td>
									<div class="">
										<div class="input-style-2">
											<textarea type="text" name="remarks_form_d1"
												id="remarks_form_d1" class="form-control"
												placeholder="Enter Remarks" maxlength="500"></textarea>
										</div>
									</div>
								</td>

								<td class="hide-action">
															<ul class="buttons-group multi-btn-group">
																<li><a
																	class="main-btn info-btn-outline btn-hover btn-sm"
																	value="Save" title="SAVE" id="family_to_save1"><i
																		class="lni lni-checkmark"></i></a></li>
																
																<li><a
																	class="main-btn success-btn-outline btn-hover btn-sm dg-rec-none"
																	value="ADD" title="ADD"
																	id="Migrated_Students_to_add1"><i
																		class="lni lni-plus"></i></a></li>
																	
																<li><a
																	class="main-btn danger-btn-outline btn-hover btn-sm dg-rec-none"
																	value="Delete" title="Delete"
																	id="Migrated_Students_to_remove1" ><i
																		class="lni lni-trash-can"></i></a></li>
																		
															</ul>
														</td>
								
														
														
							</tr>
						</tbody>
						</table>
						<input type="hidden" id="sib_ch_id1" name="sib_ch_id1"
							value="0" class="form-control autocomplete"
							autocomplete="off"> <input type="hidden" id="p_id"
							name="p_id" value="0" class="form-control autocomplete"
							autocomplete="off">
					</div>
				</div>

									<br> <br>

									<h4 class="heading">Add Details For Those Who Migrated
										From Other College</h4>
									<!-- 									<span class="mandatory" >(All fields are mandatory)</span> -->
									<div class="row">
										<div class="table-wrapper table-responsive simple-table">
										<input type="hidden" id="sc_hid" name="sc_hid"/>
											<table class="table" id="family_table_from">
												<thead>
													<tr>
														<th rowspan="2"><h6>S.No</h6></th>
														<th rowspan="2"><h6>Name of Institution</h6></th>
														<th rowspan="2"><h6>Name of Student</h6></th>
														<th rowspan="2"><h6>Date Of Migration</h6></th>
														<th rowspan="2"><h6>Professional Year Of Migration</h6></th>
														<th rowspan="2"><h6>University Enrolment Number</h6></th>
														<th rowspan="2"><h6>Remarks</h6></th>
														<th rowspan="2" class="hide-action"><h6>Action</h6></th>
													</tr>
												</thead>
												<tbody id="family_sibtbody">
													<tr id="tr_sibling1">
														<td>1</td>
														<td>
															<div class="">
																<div class="select-style-1">
																	<div class="select-position">
																		<select name="name_of_institution1"
																			id="name_of_institution1" class="form-control">
																			<option value="0">-Select-</option>
																			<c:forEach var="item" items="${getInstituteListbyUserID}"
 																				varStatus="num"> 
																				<option value="${item.institute_name}" 
 																					name="${item.institute_name.trim()}">${item.institute_name.trim()}</option> 
 																			</c:forEach> 
																		</select>
																	</div>
																</div>
															</div>


<!-- 										<div class=""> -->
<!-- 											<div class="input-style-2"> -->
<!-- 												 <input type="text" name="name_of_institution1" -->
<!-- 													id="name_of_institution1" class="form-control" -->
<!-- 													placeholder="Enter name of Attached Homoeopathic Hospital" -->
<!-- 													autocomplete="off" maxlength="100" readonly> -->
<!-- 											</div> -->
<!-- 										</div> -->
														</td>
														<td>
															<div class="">
																<div class="input-style-2">
																	<input type="text" name="name_of_students_migrated1"
																		id="name_of_students_migrated1" class="form-control"
																		placeholder="Enter student name" autocomplete="off">
																</div>
															</div>
														</td>
														<td>
															<div class="">
																<div class="input-style-2">
																	<input type="text" name="dt_of_migration1"
																		id="dt_of_migration1" maxlength="10"
																		class="form-control"
																		aria-required="true" autocomplete="off"
																		 value="DD/MM/YYYY">
																</div>
															</div>
														</td>
														<td>
															<div class="">
																<div class="input-style-2">
																	<input type="month" name="professional_year1"
																		id="professional_year1" maxlength="10"
																		class="form-control" aria-required="true"
																		autocomplete="off" value="YYYY">
																</div>
															</div>
														</td>
														<td>
															<div class="">
																<div class="input-style-2">
																	<input type="text" name="university_enroll_num1"
																		id="university_enroll_num1" class="form-control"
																		placeholder="Enter University Enrollment Number"
																		 autocomplete="off">
																</div>
															</div>
														</td>
														<td>
															<div class="">
																<div class="input-style-2">
																	<textarea type="text" name="remarks_migrated1"
																		id="remarks_migrated1" class="form-control"
																		placeholder="Enter Remarks" maxlength="500"></textarea>
																</div>
															</div>
														</td>

														<td class="hide-action">
															<ul class="buttons-group multi-btn-group">
																<li><a
																	class="main-btn info-btn-outline btn-hover btn-sm"
																	value="Save" title="SAVE" id="family_from_save1"><i
																		class="lni lni-checkmark"></i></a></li>
																<li><a
																	class="main-btn success-btn-outline btn-hover btn-sm dg-rec-none"
																	value="ADD" title="ADD"
																	id="Migrated_Students_from_add1"><i
																		class="lni lni-plus"></i></a></li>
																<li><a
																	class="main-btn danger-btn-outline btn-hover btn-sm dg-rec-none"
																	value="Delete" title="Delete"
																	id="Migrated_Students_from_remove1" ><i
																		class="lni lni-trash-can"></i></a></li>
															</ul>
														</td>
													</tr>
												</tbody>

											</table>
										</div>
									</div>
									<input type="hidden" id="sib_xy_id1" name="sib_xy_id1"
										value="0" class="form-control autocomplete" autocomplete="off">
									<input type="hidden" id="pm_id" name="pm_id" value="0"
										class="form-control autocomplete" autocomplete="off">
									<ul class="buttons-group mainbtn">
<!-- 										<li><a href="StudentEdit_Url" id="btn-reload" -->
<!-- 											class="main-btn deactive-btn btn-hover btnupda" -->
<!-- 											type="button"><i></i>Edit & View Details</a></li> -->
											<li><a href="Student_Details_UG"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
									</ul>
									
								</form>
							</div>

							<div class="tab tablinks dg-rec-block" id="tab_id6">
								<button class="tab-toggle">Intern Students For Course</button>
							</div>
							<div id="Form_F" class="content tabcontent">
								<form id="form_F_details">
									<h4 class="heading">Add Details Of Intern Students For
										Course</h4>
									<!-- 									<span class="mandatory">(All fields are mandatory)</span> -->
									<div class="row">
										<input type="hidden" id="form_f_id" name="form_f_id" value="0"
											class="form-control autocomplete" autocomplete="off">
											<input type="hidden" id="sd_hid" name="sd_hid"/>
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<input type="hidden" id="id_f" name="id_f"> <label>Name
													Of Students<strong class="mandatory">*</strong>
												</label> <input type="text" name="name_of_students"
													id="name_of_students" class="form-control"
													placeholder="Enter name of Students" autocomplete="off">
											</div>
										</div>
										<input type="hidden" id="f_h_id" name="f_h_id">

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Year Of Admission<strong class="mandatory">*</strong></label>
												<input type="month" name="year_of_admission"
													id="year_of_admission" maxlength="10" class="form-control"
													aria-required="true" autocomplete="off" value="YYYY"
													placeholder="Enter Year Of Admission">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Date Of Result Of Final Year BHMS/Course
													Completed<strong class="mandatory">*</strong></label> <input type="text"
													name="date_of_result_final_year"
													id="date_of_result_final_year" maxlength="10"
													class="form-control-sm form-control effect-9 "
													aria-required="true" autocomplete="off" value="DD/MM/YYYY">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Provisional Registration No.<strong
													class="mandatory">*</strong></label> <input type="text"
													name="provisional_reg_no" id="provisional_reg_no"
													class="form-control"
													placeholder="Enter Provisional Registration No"
													autocomplete="off" 
													maxlength="15">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Year Of Provisional Registration(From State
													Board)<strong class="mandatory">*</strong>
												</label> <input type="month" name="year_of_provisional_reg"
													id="year_of_provisional_reg" maxlength="10"
													class="form-control" aria-required="true"
													autocomplete="off"
													placeholder="Enter Year Of Provisional Registration"
													value="YYYY">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Date Of Starting Of Internship<strong
													class="mandatory">*</strong></label> <input type="text"
													name="date_of_starting_internship"
													id="date_of_starting_internship" maxlength="10"
													class="form-control-sm form-control effect-9 "
													aria-required="true" autocomplete="off" value="DD/MM/YYYY">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Date Of Completion Of Internship<strong
													class="mandatory">*</strong></label> <input type="text"
													name="date_of_completion_internship"
													id="date_of_completion_internship" maxlength="10"
													class="form-control-sm form-control effect-9 "
													aria-required="true" autocomplete="off" value="DD/MM/YYYY">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Remarks If Any</label>
												<textarea type="text" name="remark_form_f"
													id="remark_form_f" class="form-control" autocomplete="off"
													placeholder="Enter Reamark" maxlength="500"></textarea>
											</div>
										</div>
										<input type="hidden" id="inst_status" name="inst_status"
											value="0">
										<div class="col-lg-12 col-md-12 col-sm-12">
											<ul class="buttons-group mainbtn">
<!-- 												<li><input type="button" id="btn-save1" -->
<!-- 													class="main-btn info-btn btn-hover btnsave" value="Save Details"> -->
        <li><a type="button" id="btn-save1" class="main-btn info-btn btn-hover btnsave" >Save Details</a>
<!-- 												<li><a href="StudentEdit_Url" id="btn-reload" -->
<!-- 													class="main-btn deactive-btn btn-hover btnupda" -->
<!-- 													type="button"><i></i>Edit & View Details</a></li> -->
													<li><a href="Student_Details_UG"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
											</ul>
										</div>
									</div>
								</form>
							</div>
							
							
							
							<div class="tab tablinks dg-rec-block" id="tab_id6">
								<button class="tab-toggle">Enclosure</button>
							</div>
							<div id="Form_Declaration" class="content tabcontent">
									<h4 class="heading">Enclosure</h4>
										<form:form name="Form_dec_details" id="Form_dec_details" action="form_enclosure_action" method="post"
						      						 modelAttribute="UG_enclosureCMD" enctype="multipart/form-data">			
									<div class="row">
										<input type="hidden" id="form_dec_id" name="form_dec_id" value="0"
											class="form-control autocomplete" autocomplete="off">
										<div class="custom-choose-one">
								<div class="input-style-form-check_block check-multi-list">
									<div class="form-check checkbox-style">
										<label for="" class="co d-inline">The submission of following information supported by necessary documents 
										by medical institution shall be essential to recognize the qualification to be listed and maintained 
										in the Degree Recognition List by the Homoeopathy Education Board:- 
									</label> <br><label class="">
											i). *All the documents to be endorsed with Registrar (Seal) of Concerned University and signature 
											of principal of respective college/ Institute</label>
									<label class="">
											 ii). Details of number of Homoeopathic Medical Colleges/Institution affiliated to university, at present 
											  (and in past)(with their names & addresses and intake capacity);</label> 
									<label class="">iii). Copy of University Ordinance/Regulation (duly attested by Register of University)</label>
									<label class="">iv). Upload / provide details of number of admissions made in Degree Course under the University in its 
														 affiliated colleges showing the names of candidates, date of admission, 
														 NEET score- if applicable, date of examination of the course year wise in which they have appeared & result of the same.</label>
									<label class="">v). Upload / provide details of migration of students from one college to another and vice-versa in enclosed format; and whether permission
														 of erstwhile Council and University was taken in such cases.</label>
									<label class="">vi). Provide details of any failure student of the course, if so number there of (college wise) and number of chances availed by them (separately)
														 to clear successfully the said examination. </label>
									<label class="">vii). Upload / provide details of dates of starting & completion of compulsory internship training  by each student who has passed the course examination in all subjects with name of training hospital where he or she has undergone such training 
														 (with their provisional registration given by State Council of Homoeopathy). </label>
									<label class="">viii). Copy of approval of such college from Central Government for that specific year.</label>
									<label class="">ix). Any other relevant information as & when required.</label>
									
									</div>
								</div>
							</div>
							
<!-- 							<div class="col-lg-6 col-md-6 col-sm-12"> -->
<!-- 								<div class="input-style-2"> -->
<!-- 									<label>Upload Signature<span class="mandatory">*</span></label>  -->
									
<!-- 									<div class="with_icon"> -->
<!-- 										<input type="file" accept="image/*" id="upload_sign" -->
<!-- 											   name="upload_sign" class="form-control">  -->
<!-- 										<input type="hidden" id="hid_upload_signature" -->
<!-- 											   name="hid_upload_signature" class="form-control" value=""> -->
								    
<!-- 								   		<ul class="buttons-group d-flex justify-content-center"> -->
<%-- 											<li><a id="dlsign_id" href="DocumentImagePath?i_id=${data[0].id}" download --%>
<!-- 													class="main-btn info-btn-outline btn-hover btn-sm"> -->
<!-- 											<i class="lni lni-download"></i></a></li> -->
<!-- 										</ul> -->
<!-- 									</div>						 -->
							
<!-- <!-- 								<div class="note-text"> --> 
<%-- <%-- 									<span class="errorClass" id="upload_file_sign">${exp_path_msg}</span> --%> 
<!-- <!-- 								</div> --> 
<!-- 							</div> -->
<!-- 						</div> -->
						
						<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label for="username">Upload Signature<span class="mandatory">*</span></label> 
												<div class="with_icon">
												<input type="file" accept="image/*"
												id="upload_signature" name="upload_signature" class="form-control"
												autocomplete="off" />
												</div>
										</div>
									</div>
						
						<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Date<strong
													class="mandatory">*</strong></label> <input type="text"
													name="declaration_date"
													id="declaration_date" maxlength="10"
													class="form-control-sm form-control effect-9 "
													aria-required="true" autocomplete="off" value="DD/MM/YYYY">
											</div>
										</div>

															
										<div class="col-lg-12 col-md-12 col-sm-12">
											<ul class="buttons-group mainbtn">
        										<li><a type="button" id="btn-save-declaration" class="main-btn info-btn btn-hover btnsave" >Final Submit</a>
        											<li><a href="StudentEdit_Url" id="btn-reload" 
 													class="main-btn deactive-btn btn-hover btnupda"
													type="button"><i></i>Edit & View Details</a></li> 
												<li><a href="Student_Details_UG" class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
											</ul>
										</div>
									
									</div></form:form>
								
							</div>
							
							
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--</body> -->
	</div>
	
</section>

<script type="text/javascript"
	src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>

<c:url value="UploadPaper_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

        $(document).ready(function() {
        	$("#fillform").show();
        	$("#UploadExcel").hide();
        	
                if(window.location.href.includes("msg"))
                {
                         var url = window.location.href.split("?msg")[0];
                         window.location = url;
                }
                datepicketDate('date_of_admission');
                datepicketDate('date_of_enroll_university');
                datepicketDate('date_of_intern_compl');
                datepicketDate('date_of_result_final_year');
                datepicketDate('date_of_starting_internship');
                datepicketDate('date_of_completion_internship');
                datepicketDate('migrated_dt_to1');
                datepicketDate('dt_of_migration1');
                datepicketDate('mou_date');
                datepicketDate('declaration_date');
                
                $("#name_homoeopathic_medical_clg").val('${getper_dtl[0][0]}');
                
//                 $("#form_b_id").val('${data[0].form_b_id}');
//                 alert(  $("#form_b_id").val())
                
//                 $("#name_of_inst1").val('${getper_dtl[0][0]}');
              
//                 $("#name_of_institution1").val('${getper_dtl[0][0]}');

        });

//         document.getElementById('upload_sign').onchange = function() { 
//      	   imgFileSizeValidation(this,this.value,"upload_sign","50kb","upload_file_sign");
//      	   document.getElementById('upload_signature_doc_preview').src = window.URL.createObjectURL(this.files[0])
//      };
 /////////////////////// Form B Validation //////////////////
        
  function Save_B() {
	  if($("#h_id").val()=="a1"){
		if($("#upload_excel").val() == 0){
    		alert("Please Upload Excel File");
    		$("input#upload_excel").focus();
    		return false;
    		}
		}
	else if($("#h_id").val()=="a2"){
		           if($("input#student_name").val().trim() == "") {
	               alert("Please Enter Student Name.");
	               $("input#student_name").focus();
	               return false;
    		 	 }
	        	 else if($("input#date_of_admission").val().trim()=="DD/MM/YYYY"){
	                    alert("Please Enter Date Of Admission");
	                    $("input#date_of_admission").focus();
	                    return false;
	        	 }
	        	  else if($('input[name=neet_rank]:checked').length == 0){
			        	 alert("Please Select Neet Rank(Yes or No)");
		                 $("input#neet_rank").focus();
		                 return false;
			         }
			           
			          if($('input:radio[name=rank]:checked').val() == "Yes"){				
				         if($("#rank").val() == ""){
				        	 alert("Please Enter Rank ");
		  					$("input#rank").focus();
		  					return false;
		  				}
	  			    }
		         else if($("input#marks").val().trim() == "") {
		                alert("Please Enter Marks");
		                $("input#marks").focus();
		                return false;
 				 }
		         else if($('input[name=all_india]:checked').length == 0){
	     				 alert("Please Select All india (Yes or No)");
	     				 $("input#all_india").focus();
	     				 return false;
	     		 }
                 else if($('input[name=state]:checked').length == 0){
	                 	alert("Please Select State (Yes or No)");
	                 	$("input#state").focus();
	                 	return false;
	             }
                 else if($('input[name=management_quota]:checked').length == 0){
	                 	alert("Please Select Management Quota (Yes or No)");
	                 	$("input#management_quota").focus();
	                 	return false;
	             }
                 else if($('input[name=admission_authority]:checked').length == 0){
	                 	alert("Please Select Admission Authority (Yes or No)");
	                 	$("input#admission_authority").focus();
	                 	return false;
	             }
		         else if($('input[name=court_order]:checked').length == 0){
		        	 alert("Please Select court_order (Yes or No)");
	                 $("input#court_order").focus();
	                 return false;
		         }
		          if($('input:radio[name=court_order]:checked').val() == "No"){				
			         if($("#court_order_file").val() == ""){
			        	 alert("Please Upload Court Order File ");
	  					$("input#court_order_file").focus();
	  					return false;
	  				}
  			    }
		         if($("input#date_of_enroll_university").val().trim()=="DD/MM/YYYY"){
	                	 alert("Please Enter Date Of Enrollment In University");
	                	 $("input#date_of_enroll_university").focus();
	                 	return false;
	             }
	             else if($("input#uni_enroll_number").val().trim()==""){
               		alert("Please Enter University Enrollment Number");
              		 $("input#uni_enroll_number").focus();
              		 return false;
                }
	            else if($("input#date_of_intern_compl").val().trim()=="DD/MM/YYYY"){
              		alert("Please Enter Date Of Internship Completion");
               		$("input#date_of_intern_compl").focus();
               		return false;
                }
			}
	 
	var upload_excel = $("#upload_excel").val();
 	var lastDot = upload_excel.lastIndexOf('.');
 	var fileName = upload_excel.substring(0, lastDot);
 	var ext = upload_excel.substring(lastDot + 1);
 	
 	    var formData = new FormData();
	    formData.append("filePath", $("#court_order_file1")[0].files[0]);

// 		var filePath = $('#court_order_file').val(); // get the file path
// 		console.log(filePath);
// 		formData.append('filePath', filePath);
		var serializedData = $(this).serialize();
	
	formData.append('serializedData', serializedData);
	formData.append('formID', $("#form_b_id").val());
	formData.append('h_id', $("#h_id").val());
	formData.append('userId', $("#userId").val());
	formData.append('student_name', $("#student_name").val());
	formData.append('date_of_admission', $("#date_of_admission").val());
	formData.append('neet_rank', $('input[name=neet_rank]:checked').val());
	formData.append('rank', $("#rank").val());
	formData.append('marks', $("#marks").val());
	formData.append('all_india', $('input[name=all_india]:checked').val());
	formData.append('state', $('input[name=state]:checked').val());
	formData.append('management_quota', $('input[name=management_quota]:checked').val());
	formData.append('admission_authority', $('input[name=admission_authority]:checked').val());
	formData.append('court_order', $('input[name=court_order]:checked').val());
	formData.append('date_of_enroll_university', $("#date_of_enroll_university").val());
	formData.append('uni_enroll_number', $("#uni_enroll_number").val());
	formData.append('date_of_intern_compl', $("#date_of_intern_compl").val());
	formData.append('remarks_form_b', $("#remarks_form_b").val());
	
 	$.ajax({
 		
 	 		url : 'form_b_action?_csrf=' + value,
			type : "POST",
			data : formData,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
			
 	}).done(function(data) {
 	    	 if(data.error == null) {
				 if($("#h_id").val()=="a1")
				 {
               			alert(data.saved);
				 }
				 if($("#h_id").val()=="a2")
				 {
	                 if(data.form_b_id  > 0)
	                 {
	             		  $("#form_b_id").val(data);
	             	      alert("Data Saved Sucessfully");
// 	             	      window.location.reload();
	                 }
				 } 
				 else 
				 {
                  alert(data);
                 }
  } 
  else {
 	 alert(data.error);
  }
//  		location.reload();		
}).fail(function(jqXHR, textStatus) {
 		alert("fail to fetch")
 	});
}

/////////////////////// Form C Validation //////////////////

  
//   function Save_C() {
		
//   	    if($("select#name_homoeopathic_medical_clg").val() == "0") {
//   		        alert("Please Enter Name Homoeopathic Medical College");
//   		        $("select#name_homoeopathic_medical_clg").focus();
//   		        return false;
//   		}
//           else if($("input#attached_homoeopath_hospital").val().trim() == "") {
//                   alert("Please Enter Attached Homoeopath Hospital");
//                   $("input#attached_homoeopath_hospital").focus();
//                   return false;
//           }
//           else if($("input#super_speciality_hospital").val().trim() == "") {
//                   alert("Please Enter Super Speciality Hospital");
//                   $("input#super_speciality_hospital").focus();
//                   return false;
//   		}
//           else if($("input#mou_date").val().trim()=="DD/MM/YYYY") {
//   	            alert("Please Enter MOU Date");
//   	            $("input#mou_date").focus();
//   	            return false;
//           }
//           else if($("input#copy_of_mou").val().trim() == "") {
//              		alert("Please Enter Copy Of MOU Date");
//          	 		$("input#copy_of_mou").focus();
//          			 return false; 
//   		} 
//           else if($("input#name_of_hospital_staff").val().trim() == "") {
//                   alert("Please Enter Name Of Hospital Staff");
//                   $("input#name_of_hospital_staff").focus();
//                   return false;
//           }
//           else if($("select#designation").val() == "0") {
//                   alert("Please Enter Designation");
//                   $("select#designation").focus();
//                   return false;
//           }
//           else if($("input#qualification").val().trim() == "") {
//               alert("Please Enter Qualification");
//               $("input#qualification").focus();
//               return false;
//           }
//           else if($('input[name=fulltime_parttime]:checked').length == 0)
//   		 {
// 				 alert("Please Select Full Time Or Part Time");
// 				 $("input#fulltime_parttime").focus();
// 				 return false;
//   		 }

//         var formData = new FormData();
// 		var filePath = $('#copy_of_mou').val(); // get the file path
// 		console.log(filePath);
// 		formData.append('filePath', filePath);
		
// 		var serializedData = $(this).serialize();
// 		formData.append('serializedData', serializedData);
// 		formData.append('formID', $("#form_c_id").val());
// 		formData.append('userId', $("#userId").val());
// 		formData.append('name_homoeopathic_medical_clg', $("#name_homoeopathic_medical_clg").val());
// 		formData.append('attached_homoeopath_hospital', $("#attached_homoeopath_hospital").val());
// 		formData.append('super_speciality_hospital', $("#super_speciality_hospital").val());
// 		formData.append('mou_date', $("#mou_date").val());
// 		formData.append('name_of_hospital_staff', $("#name_of_hospital_staff").val());
// 		formData.append('designation', $("#designation").val());
// 		formData.append('qualification', $("#qualification").val());
// 		formData.append('fulltime_parttime', $("#fulltime_parttime2").val());
// 		formData.append('remarks_form_c', $("#remarks_form_c").val());
// 		$.ajax({
// 			url : 'form_c_action?_csrf=' + value,
// 			type : "POST",
// 			data : formData,
// 			enctype : 'multipart/form-data',
// 			processData : false,
// 			contentType : false
// 		}).done(function(data) {
//    		 if(data.form_c_id  > 0){
//         		$("#form_c_id").val(data);
//         		alert("Data Saved Sucessfully");
//         	  window.location.reload();
//           }
//           else{
//         	  alert(data);
//           }
//    	}).fail(function(jqXHR, textStatus) {
//    		alert("fail to fetch")
//    	});
// }	
	
///////////////////////addmore  Form D1 Validation //////////////////

function Migrated_Students_to(ps) {
	
	  if($("select#name_of_inst"+ps).val() == "0") {
          alert("Please Select Name Of Institute");
          $("select#name_of_inst"+ps).focus();
          return false;
	  }
	  else if($("input#student_name_to_migrated"+ps).val() == "") {
         alert("Please Enter Student Name");
         $("input#student_name_to_migrated"+ps).focus();
         return false;
	  }
      else if($("input#migrated_dt_to"+ps).val() == "DD/MM/YYYY" || $("input#migrated_dt_to"+ps).val() == ""  ) {
      		alert("Please Enter Date Of Migration");
      		$("input#migrated_dt_to"+ps).focus();
      		return false;
     }
     else if($("input#professional_year_migrated"+ps).val() == "") {
              alert("Please Enter Professional Year Migrated");
              $("input#professional_year_migrated"+ps).focus();
              return false;
    }
    else if($("input#university_enrollment_number"+ps).val() == "") {
               alert("Please Enter University Enrollment Number");
              $("input#university_enrollment_number"+ps).focus();
              return false;
    }
	  
	var name_of_inst =$('#name_of_inst' + ps).val();
	var student_name_to_migrated = $('#student_name_to_migrated' + ps).val();
	var migrated_dt_to1 = $("#migrated_dt_to"+ ps).val();
	var professional_year_migrated = $("#professional_year_migrated"+ps).val();
	var university_enrollment_number = $("#university_enrollment_number"+ps).val();
	var remarks_form_d = $("#remarks_form_d" + ps).val();
	var sib_ch_id = $('#sib_ch_id' + ps).val();
	$.post('form_d_action_Add?' + key + "=" + value, {
		
		name_of_inst: name_of_inst,
		student_name_to_migrated: student_name_to_migrated,
		migrated_dt_to1: migrated_dt_to1,
		professional_year_migrated: professional_year_migrated,
		university_enrollment_number: university_enrollment_number,
		remarks_form_d:remarks_form_d,
		sib_ch_id :sib_ch_id
	}, function(data) {
		if(data.error == null) {
			if(data.sibId != null) {
				$('#sib_ch_id' + ps).val(data.sibId);
				$("#Migrated_Students_to_add" + ps).show();
				$("#Migrated_Students_to_remove" + ps).show();
				alert(data.saved);
				  
			} else {
				alert(data.updated);
			}
		} else alert(data.error);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}


/* ---------------------------------------ADD------------------------------------------------ */
  
 sib = 1;
 sib_srno = 1;

 
function Migrated_Students_to_add_fn(r) {
	
	if($('#Migrated_Students_to_add' + r).length) {
	   $("#Migrated_Students_to_add" + r).hide();
	}
	if(r != 0) sib_srno += 1;
	sib = r + 1;
	$("table#family_table_to").append('<tr id="tr_sibling_to' + sib + '">' + '<td class="sib_srno">' + sib_srno + '</td>' 
		+ '<td><div class="input-style-2"><select name="name_of_inst' + sib + '" id="name_of_inst' + sib + '"class="form-control" ><option value="0">---Select College---</option><c:forEach var="item" items="${getInstituteListbyUserID}" varStatus="num"><option value="${item.institute_name}" name="${item.institute_name.trim()}">${item.institute_name.trim()}</option></c:forEach></select></div></td>'	
		+ '<td><div class="input-style-2"><input type="text" id="student_name_to_migrated'+sib+'" name="student_name_to_migrated'+sib+'" placeholder="Enter Student Name" class="form-control" autocomplete= "off" maxlength="50"></div></td>'
		+ '<td><div class="input-style-2"><input type="text"  id="migrated_dt_to'+sib+'" name="migrated_dt_to'+sib+'" maxlength="10"  value="DD/MM/YYYY"  placeholder="Enter To Date" class="form-control"></div></td> '
		+ '<td><div class="input-style-2"><input type="month"  id="professional_year_migrated'+sib+'" name="professional_year_migrated'+sib+'" maxlength="10"  value="YYYY"  placeholder="Enter To Date" class="form-control"></div></td> '
		+ '<td class="dg-rec-none"><input type="text" id="sib_ch_id' + sib + '" name="sib_ch_id' + sib + '"   value="0" class="form-control autocomplete" autocomplete="off"></td>' 
		+ '<td><div class="input-style-2"><input type="text" id="university_enrollment_number'+sib+'" name="university_enrollment_number'+sib+'" placeholder="Enter University Enrollment number " class="form-control" autocomplete= "off" maxlength="50"></div></td>'
		+ '<td><div class="input-style-2"><input type="text" id="remarks_form_d'+sib+'" name="remarks_form_d' + sib + '" placeholder="Enter Remarks" class="form-control"></div></td>'
		+ '<td class="hide-action"><ul class="buttons-group multi-btn-group"><li><a class="main-btn info-btn-outline btn-hover btn-sm" value = "SAVE" title = "SAVE" id = "family_save_to' + sib + '"  ><i class="lni lni-checkmark"></i></a></li>' + '<li><a class="main-btn success-btn-outline btn-hover btn-sm dg-rec-none" value = "ADD" title = "ADD" id = "Migrated_Students_to_add' + sib + '" ><i class="lni lni-plus"></i></a></li>' + '<li><a class="main-btn danger-btn-outline btn-hover btn-sm dg-rec-none" value="REMOVE" title = "REMOVE" id = "Migrated_Students_to_remove' + sib + '" ><i class="lni lni-trash-can"></i></a></li>' + '</ul></td></tr>');
	
	datepicketDate('migrated_dt_to' + sib);
// 	$("#name_of_inst"+sib).val('${getper_dtl[0][0]}');
	addOnclick1(sib);
}

/* -----------------------------------delete------------------------------------ */
function Migrated_Students_to_remove_fn(R) {
	var rc = confirm("Are You Sure! You Want To Delete");
	if(rc) {
		var sib_ch_id = $('#sib_ch_id' + R).val();
		$.post('form_d_Add_delete_action?' + key + "=" + value, {
			sib_ch_id: sib_ch_id
			
		}, function(data) {
			if(data == '1') {
				$("tr#tr_sibling_to" + R).remove();
				if(R == sib) {
					R = R - 1;
					var temp = true;
					for(i = R; i >= 1; i--) {
						if($('#Migrated_Students_to_add' + i).length) {
							$("#Migrated_Students_to_add" + i).show();
							temp = false;
							sib = i;
							break;
						}
					}
					if(temp) {
						Migrated_Students_add_fn(0);
					}
				}
				$('.sib_srno').each(function(i, obj) {
					obj.innerHTML = i + 1;
					sib_srno = i + 1;
				});
				alert("Data Deleted Successfully");
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
}

///////////////////////addmore Form D1 Validation Migrated From //////////////////

function Migrated_Students_from(ms) {
	  if($("select#name_of_institution"+ms).val() == "0") {
         alert("Please Select Name Of Institute");
         $("select#name_of_institution"+ms).focus();
         return false;
	  }
	  else if($("input#name_of_students_migrated"+ms).val() == "") {
        alert("Please Enter Student Name");
        $("input#name_of_students_migrated"+ms).focus();
        return false;
	  }
     else if($("input#dt_of_migration"+ms).val() == "DD/MM/YYYY" || $("input#dt_of_migration"+ms).val() == ""  ) {
     		alert("Please Enter Date Of Migration");
     		$("input#dt_of_migration"+ms).focus();
     		return false;
    }
     else if($("input#professional_year"+ms).val() == "") {
         alert("Please Enter Professional Year");
         $("input#professional_year"+ms).focus();
         return false;
    }
   else if($("input#university_enroll_num"+ms).val() == "") {
              alert("Please Enter University Enrollment Number");
             $("input#university_enroll_num"+ms).focus();
             return false;
   }
  
	var name_of_institution =$('#name_of_institution' + ms).val();
	var name_of_students_migrated = $('#name_of_students_migrated' + ms).val();
	var dt_of_migration1 = $("#dt_of_migration"+ ms).val();
	var professional_year = $("#professional_year"+ms).val();
	var university_enroll_num = $("#university_enroll_num"+ms).val();
	var remarks_migrated = $("#remarks_migrated" + ms).val();
	var sib_xy_id = $('#sib_xy_id' + ms).val();
	$.post('form_d_action_from?' + key + "=" + value, {
		
		name_of_institution: name_of_institution,
		name_of_students_migrated: name_of_students_migrated,
		dt_of_migration1: dt_of_migration1,
		professional_year: professional_year,
		university_enroll_num: university_enroll_num,
		remarks_migrated:remarks_migrated,
		sib_xy_id :sib_xy_id
	}, function(data) {
		if(data.error == null) {
			if(data.xyId != null) {
				$('#sib_xy_id' + ms).val(data.xyId);
				$("#Migrated_Students_from_add" + ms).show();
				$("#Migrated_Students_from_remove" + ms).show();
				alert(data.saved);
				  
			} else {
				alert(data.updated);
			}
		} else alert(data.error);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

/* ---------------------------------------ADD Migrated From------------------------------------------------ */

xy = 1;
xy_srno = 1;

function Migrated_Students_from_add_fn(q) {
	if($('#Migrated_Students_from_add' + q).length) {
		$("#Migrated_Students_from_add" + q).hide();
	}
	if(q != 0) xy_srno += 1;
	xy = q + 1;
	$("table#family_table_from").append('<tr id="tr_sibling' + xy + '">' + '<td class="xy_srno">' + xy_srno + '</td>'
		+ '<td><div class="input-style-2"><select name="name_of_institution' + xy + '" id="name_of_institution' + xy + '"class="form-control" ><option value="0">---Select College---</option><c:forEach var="item" items="${getInstituteListbyUserID}" varStatus="num"><option value="${item.institute_name}" name="${item.institute_name.trim()}">${item.institute_name.trim()}</option></c:forEach></select></div></td>'
		+ '<td><div class="input-style-2"><input type="text" id="name_of_students_migrated'+xy+'" name="name_of_students_migrated'+xy+'" placeholder="Enter Student Name" class="form-control" autocomplete= "off" maxlength="50"></div></td>'
		+ '<td><div class="input-style-2"><input type="text"  id="dt_of_migration'+xy+'" name="dt_of_migration'+xy+'" maxlength="10"  value="DD/MM/YYYY"  placeholder="Enter To Date" class="form-control"></div></td> '
		+ '<td><div class="input-style-2"><input type="month"  id="professional_year'+xy+'" name="professional_year'+xy+'" maxlength="10"  value="MM/YYYY"  placeholder="Enter To Professional mYear" class="form-control"></div></td> '
		+ '<td class="dg-rec-none"><input type="text" id="sib_xy_id' + xy + '" name="sib_xy_id' + xy + '"   value="0" class="form-control autocomplete" autocomplete="off"></td>' 
		+ '<td><div class="input-style-2"><input type="text" id="university_enroll_num'+xy+'" name="university_enroll_num'+xy+'" placeholder="Enter University Enrollment Number " class="form-control" autocomplete= "off" maxlength="50"></div></td>'
		+ '<td><div class="input-style-2"><input type="text" id="remarks_migrated'+xy+'" name="remarks_migrated' + xy + '" placeholder="Enter Remarks" class="form-control" autocomplete= "off"></div></td>'
		+ '<td class="hide-action"><ul class="buttons-group multi-btn-group"><li><a class="main-btn info-btn-outline btn-hover btn-sm" value = "SAVE" title = "SAVE" id = "family_save_from' + xy + '"  ><i class="lni lni-checkmark"></i></a></li>' + '<li><a class="main-btn success-btn-outline btn-hover btn-sm dg-rec-none" value = "ADD" title = "ADD" id = "Migrated_Students_from_add' + xy + '"  ><i class="lni lni-plus"></i></a></li>' + '<li><a class="main-btn danger-btn-outline btn-hover btn-sm dg-rec-none" value="REMOVE" title = "REMOVE" id = "Migrated_Students_from_remove' + xy + '" ><i class="lni lni-trash-can"></i></a></li>' + '</ul></td></tr>');
	datepicketDate('dt_of_migration' + xy);
	addOnclick2(xy);
}
/* -----------------------------------delete------------------------------------ */
function Migrated_Students_from_remove_fn(R) {
	var rc = confirm("Are You Sure! You Want To Delete");
	if(rc) {
		var sib_xy_id = $('#sib_xy_id' + R).val();
		$.post('form_d_Add_delete_action_from?' + key + "=" + value, {
			sib_xy_id: sib_xy_id
			
		}, function(data) {
			if(data == '1') {
				$("tr#tr_sibling" + R).remove();
				if(R == xy) {
					R = R - 1;
					var temp = true;
					for(i = R; i >= 1; i--) {
						if($('#Migrated_Students_from_add' + i).length) {
							$("#Migrated_Students_from_add" + i).show();
							temp = false;
							sib = i;
							break;
						}
					}
					if(temp) {
						Migrated_Students_from_add(0);
					}
				}
				$('.xy_srno').each(function(i, obj) {
					obj.innerHTML = i + 1;
					sib_srno = i + 1;
				});
				alert("Data Deleted Successfully");
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
}
/* ---------------------------------------------------------------------  */       
function save_form_f() {
// 	alert("-----------fff")
	if($("input#name_of_students").val().trim() == "") {
   	 alert("Please Enter Name Of Students");
   	 $("input#name_of_students").focus();
  	     return false;
	    }
   else if($("input#year_of_admission").val().trim() == "") {
          alert("Please Enter Year Of Admission");
          $("input#year_of_admission").focus();
          return false;
   }
   else if($("input#date_of_result_final_year").val() == "DD/MM/YYYY") {
       alert("Please Enter Date Of Result Final Year");
       $("input#date_of_result_final_year").focus();
       return false;
  }
   else if($("input#provisional_reg_no").val().trim() == "") {
          alert("Please Enter Provisional Registration Number");
          $("input#provisional_reg_no").focus();
          return false;
   }
   else if($("input#year_of_provisional_reg").val().trim() == "") {
      alert("Please Enter Year Of Provisional Registration");
      $("input#year_of_provisional_reg").focus();
      return false;
   }
   else if($("input#date_of_starting_internship").val().trim() == "DD/MM/YYYY") {
        alert("Please Enter Date Of Starting Internship");
        $("input#date_of_starting_internship").focus();
        return false;
   }	
   else if($("input#date_of_completion_internship").val().trim() == "DD/MM/YYYY") {
        alert("Please Enter Date Of Completion Internship");
        $("input#date_of_completion_internship").focus();
        return false;
	}
	  var form_f_id = $('#form_f_id').val();
	  var formvalues = $("#form_F_details").serialize();
	   $.post('form_f_action?' + key + "=" + value, formvalues, function(data) {
		 if(data.error == null) {
			if(data.form_f_id != null) { 
     		    $('#form_f_id').val(data.form_f_id);
     		   alert("Data Saved Sucessfully");
//                      location.reload();
			} else {
			alert(data.updated);
			}
		}  
		else {
			alert(data.error)
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function UploadExcel() {
  	
   	$("#UploadExcel").show();
   	$("#fillform").hide();
    $("#h_id").val("a1");
    $("#export_btn").show();
}
function fillform() {

  	$("#fillform").show();
  	$("#UploadExcel").hide();
    $("#h_id").val("a2");
    $("#export_btn").hide();
}
function getExcel() {

	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('search2').submit();
}

document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn_save_a').onclick = function(){
			return Save_B();
	};

	document.getElementById('downlaod').onclick = function(){
			getExcel();
	};
	document.getElementById('btn-save').onclick = function(){
		return Save_C();
	};
	document.getElementById('btn-save1').onclick = function(){
		return save_form_f();
	};	
	document.getElementById('btn-save-declaration').onclick = function(){
		if(declaration_institute()){
			$("#Form_dec_details").submit();
			}
	};
	
	document.getElementById('date_of_admission').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_admission').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('date_of_admission').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('date_of_admission').onkeyup = function() {
		return	clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_admission').onchange = function() {
		  clickrecall(this,'DD/MM/YYYY');
		  return validateDate_FutureDate(this.value,this); 
	};
	
	document.getElementById('date_of_enroll_university').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_enroll_university').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('date_of_enroll_university').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('date_of_enroll_university').onkeyup = function() {
		return	clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_enroll_university').onchange = function() {
		  clickrecall(this,'DD/MM/YYYY');
		  return validateDate_FutureDate(this.value,this); 
	};
	
	document.getElementById('date_of_intern_compl').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_intern_compl').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('date_of_intern_compl').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('date_of_intern_compl').onkeyup = function() {
		return	clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_intern_compl').onchange = function() {
		  clickrecall(this,'DD/MM/YYYY');
		  return validateDate_FutureDate(this.value,this); 
	};
	
	document.getElementById('mou_date').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('mou_date').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('mou_date').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('mou_date').onkeyup = function() {
		return	clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('mou_date').onchange = function() {
		  clickrecall(this,'DD/MM/YYYY');
		  return validateDate_FutureDate(this.value,this); 
	};
	
	document.getElementById('migrated_dt_to1').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('migrated_dt_to1').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('migrated_dt_to1').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('migrated_dt_to1').onkeyup = function() {
		return	clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('migrated_dt_to1').onchange = function() {
		  clickrecall(this,'DD/MM/YYYY');
		  return validateDate_FutureDate(this.value,this); 
	};
	
// 	document.getElementById('migrated_dt_to'+ps).onclick = function() {
// 		return clickclear(this, 'DD/MM/YYYY');
// 	};
// 	document.getElementById('migrated_dt_to'+ps).onfocus = function() {
// 		this.style.color='#000000';
// 	};
// 	document.getElementById('migrated_dt_to'+ps).onblur = function() {
// 			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
// 	};
// 	document.getElementById('migrated_dt_to'+ps).onkeyup = function() {
// 		return	clickclear(this, 'DD/MM/YYYY');
// 	};
// 	document.getElementById('migrated_dt_to'+ps).onchange = function() {
// 		  clickrecall(this,'DD/MM/YYYY');
// 		  return validateDate_FutureDate(this.value,this); 
// 	};
	
	document.getElementById('dt_of_migration1').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('dt_of_migration1').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('dt_of_migration1').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('dt_of_migration1').onkeyup = function() {
		return	clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('dt_of_migration1').onchange = function() {
		  clickrecall(this,'DD/MM/YYYY');
		  return validateDate_FutureDate(this.value,this); 
	};
	
// 	document.getElementById('dt_of_migration'+ms).onclick = function() {
// 		return clickclear(this, 'DD/MM/YYYY');
// 	};
// 	document.getElementById('dt_of_migration'+ms).onfocus = function() {
// 		this.style.color='#000000';
// 	};
// 	document.getElementById('dt_of_migration'+ms).onblur = function() {
// 			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
// 	};
// 	document.getElementById('dt_of_migration'+ms).onkeyup = function() {
// 		return	clickclear(this, 'DD/MM/YYYY');
// 	};
// 	document.getElementById('dt_of_migration'+ms).onchange = function() {
// 		  clickrecall(this,'DD/MM/YYYY');
// 		  return validateDate_FutureDate(this.value,this); 
// 	};
	
	document.getElementById('date_of_result_final_year').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_result_final_year').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('date_of_result_final_year').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('date_of_result_final_year').onkeyup = function() {
		return	clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_result_final_year').onchange = function() {
		  clickrecall(this,'DD/MM/YYYY');
		  return validateDate_FutureDate(this.value,this); 
	};
	
	document.getElementById('date_of_starting_internship').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_starting_internship').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('date_of_starting_internship').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('date_of_starting_internship').onkeyup = function() {
		return	clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_starting_internship').onchange = function() {
		  clickrecall(this,'DD/MM/YYYY');
		  return validateDate_FutureDate(this.value,this); 
	};
	document.getElementById('date_of_completion_internship').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_completion_internship').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('date_of_completion_internship').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('date_of_completion_internship').onkeyup = function() {
		return	clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_completion_internship').onchange = function() {
		  clickrecall(this,'DD/MM/YYYY');
		  return validateDate_FutureDate(this.value,this); 
	};
	document.getElementById('student_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('student_name_to_migrated1').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('name_of_students_migrated1').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('name_of_students').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('remarks_form_b').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('attached_homoeopath_hospital').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('super_speciality_hospital').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('name_of_hospital_staff').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('qualification').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('remarks_form_c').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('university_enrollment_number1').onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('rank').onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('marks').onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('university_enroll_num1').onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('provisional_reg_no').onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('Upload').onclick = function() {
		UploadExcel();
	};
	document.getElementById('Fillform').onclick = function() {
		fillform();
	};
	document.getElementById('neet_rank1').onclick = function() {
		Neet_Rank();
	};
	document.getElementById('neet_rank2').onclick = function() {
		Neet_Rank();
	};
	document.getElementById('uni_enroll_number').onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	
	//////////// Migrated To SAVE ///////////
	document.getElementById('family_to_save1').onclick = function() {
		 Migrated_Students_to(1);
	};

	document.getElementById('Migrated_Students_to_add1').onclick = function() {
		Migrated_Students_to_add_fn(1);
	};
	
	////////////Migrated From SAVE ///////////
	document.getElementById('family_from_save1').onclick = function() {
		Migrated_Students_from(1);
	};
	////////////Migrated From Add ///////////
	document.getElementById('Migrated_Students_from_add1').onclick = function() {
		Migrated_Students_from_add_fn(1);
	};
	

});

function addOnclick1(sib){
		document.getElementById('Migrated_Students_to_add'+sib).onclick = function() {
			Migrated_Students_to_add_fn(sib);
		};
		document.getElementById('Migrated_Students_to_remove'+sib).onclick = function() {
			Migrated_Students_to_remove_fn(sib);
		};
		document.getElementById('family_save_to'+sib).onclick = function() {
			Migrated_Students_to(sib);
		};
	}
function addOnclick2(xy){
	document.getElementById('Migrated_Students_from_add'+xy).onclick = function() {
		Migrated_Students_from_add_fn(xy);
	};
	document.getElementById('Migrated_Students_from_remove'+xy).onclick = function() {
		Migrated_Students_from_remove_fn(xy);
	};
	document.getElementById('family_save_from'+xy).onclick = function() {
		Migrated_Students_from(xy);
	};
}
function Neet_Rank(){
	var neet_rank = $('input:radio[name=neet_rank]:checked').val();
	if(neet_rank == "Yes"){
        $("div#mention_div").show();
	}
	else if(neet_rank == "No"){
        $("div#mention_div").hide();
	}
}

function declaration_institute() {
	
   	  if ($("#upload_signature").val().trim() == "") {
		  			alert("Please Upload Signature");
		  			$("input#upload_signature").focus();
		  			return false;
	  }
      else  if($("input#declaration_date").val().trim()=="DD/MM/YYYY"){
   	 	alert("Please Enter Date Enclosure");
   	 	$("input#declaration_date").focus();
    	return false;
      }
	  return true;
}

</script>