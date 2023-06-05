<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link href="js/tunneldesign/tunnelold.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/Pdf_View/pdf.js" type="text/javascript"></script>
<script src="js/Pdf_View/pdf.worker.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="js/Pdf_View/pdfview.css">

<section class="dashboard-page">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Student Details</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a id="coman"
									href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Student Details</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		
		<div class="form-elements-wrapper tunnel-form preview-form">
			<form:form name="student_declar" id="student_declar_view"
				action="student_declar_viewaction" method="post"
				modelAttribute="student_declar_viewCMD">
				<div class="card-style mb-30" id="card_view">
					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="auto-fill-form">
									<div class="upload_image">
										<img id="upload_photo_doc_preview" class="" />
									</div>
								</div>
								<!--  <div class="custom-data-value custom-title custom-title-bb">
										<h4 class="custom-title-tag">Student Detail Form </h4>
									</div>  -->
							</div>
						</div>
					</div>
					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg">
									<h5 class="custom-title-tag">Personal Details</h5>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Name of Applicant</label> <span class="value-bind"
										id="pers_name_label"> </span> <input type="hidden"
										id="pers_name" name="pers_name" class="form-control"
										autocomplete="off" maxlength="25"> <input
										type="hidden" id="viewid" name="viewid" class="form-control"
										value="0" autocomplete="off"> <select hidden="hidden"
										id="cand_prifix" name="cand_prifix" class="form-control"
										onchange="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getPrefixList}" varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.prefix}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Middle Name </label> <span class="value-bind"
										id="pers_middel_name_label"></span> <input type="hidden"
										id="pers_middel_name" name="pers_middel_name"
										class="form-control" autocomplete="off" maxlength="25">
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Surname </label> <span class="value-bind"
										id="pers_surname_label"></span> <input type="hidden"
										id="pers_surname" name="pers_surname" class="form-control"
										autocomplete="off" maxlength="25">
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Father's Name </label> <span class="value-bind"
										id="pers_father_name_label"></span> <input type="hidden"
										id="pers_father_name" name="pers_father_name"
										class="form-control" autocomplete="off" maxlength="25">
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Mother's Name </label> <span class="value-bind"
										id="pers_mother_name_label"></span> <input type="hidden"
										id="pers_mother_name" name="pers_mother_name"
										class="form-control" autocomplete="off" maxlength="25">
									<!-- <input type="hidden" id="id" name="id" class="form-control"
										value="0" autocomplete="off"> -->
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Date of Birth </label> <span class="value-bind"
										id="pers_date_of_birth_label"></span> <input type="hidden"
										id="pers_date_of_birth" name="pers_date_of_birth"
										class="form-control" autocomplete="off" maxlength="25">
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Gender</label> <span class="value-bind"
										id="pers_gender_label"></span>
									<!-- 									<input type ="hidden" id="pers_gender" name="pers_gender" class="form-control" -->
									<!-- 										autocomplete="off" maxlength="25" > -->
									<select name="pers_gender" id="pers_gender"
										class="form-control d-none">
										<option value="0">-- Select --</option>
										<c:forEach var="item" items="${getgenderList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Mobile Number </label> <span class="value-bind"
										id="pers_mob_no_label"></span> <input type="hidden"
										id="pers_mob_no" name="pers_mob_no" class="form-control"
										autocomplete="off" maxlength="25">
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>E-Mail ID </label> <span class="value-bind"
										id="pers_email_label"></span> <input type="hidden"
										id="pers_email" name="pers_email" class="form-control"
										autocomplete="off" maxlength="25">

								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Category </label> <span class="value-bind"
										id="pers_category_label"></span> <select type="hidden"
										name="pers_category" id="pers_category"
										class="form-control d-none">
										<option value="0" selected="selected">-- Select --</option>
										<c:forEach var="item" items="${getcategorylist}"
											varStatus="num">
											<option value="${item.id}" name="${item.category}">${item.category}</option>
										</c:forEach>
									</select>
									<!-- <input id="nationality" name="nationality" class="form-control"
										autocomplete="off" maxlength="25" > -->
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Religion </label> <span class="value-bind"
										id="pers_religion_label"></span> <select hidden="hidden"
										name="pers_religion" id="pers_religion"
										class="form-control d-none">
										<c:forEach var="item" items="${getreligionListdata}"
											varStatus="num">
											<option value="${item.id}" name="${item.religion}">${item.religion}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Marital Status </label> <span class="value-bind"
										id="pers_marital_status_label"></span> <select hidden="hidden"
										name="pers_marital_status" id="pers_marital_status"
										class="form-control d-none">
										<option value="0" selected="selected">-- Select
											Marital Status --</option>
										<c:forEach var="item" items="${getmsList}" varStatus="num">
											<option value="${item.id}" name="${item.marital_status}">${item.marital_status}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Nationality </label> <span class="value-bind"
										id="pers_nationality_label"></span> <select hidden="hidden"
										name="pers_nationality" id="pers_nationality"
										class="form-control d-none">
										<!-- onchange="getState();" -->
										<option value="0" selected="selected">-- Select --</option>
										<c:forEach var="item" items="${getMedCountryName}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>State/UT (Domicile) </label> <span class="value-bind"
										id="state_id_label"></span> <select hidden="hidden"
										name="state_id" id="state_id" class="form-control d-none">
										<!-- onchange="getDistrict();" -->
										<option value="0" selected="selected">-- Select State
											--</option>
										<c:forEach var="item" items="${getMedStateName}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>District </label> <span class="value-bind"
										id="district_id_label"></span> <select hidden="hidden"
										name="district_id" id="district_id"
										class="form-control d-none">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getMedDistrictName}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Village </label> <span class="value-bind"
										id="village_label"></span> <select hidden="hidden"
										name="village" id="village" class="form-control d-none">
										<option value="0" selected="selected">-- Select
											village --</option>
										<option value="1">Visnagar</option>
										<option value="2">Mansa</option>
										<option value="3">Kalol</option>
										<option value="3">Chandkheda</option>
									</select>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Aadhaar Number </label> <span class="value-bind"
										id="pers_aadhar_no_label"></span> <input type="hidden"
										id="pers_aadhar_no" name="pers_aadhar_no" class="form-control"
										autocomplete="off" maxlength="12">
								</div>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg">
									<h5 class="custom-title-tag">Address</h5>
								</div>
							</div>
							<div class="col-lg-4 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Permanent Address </label> <span
										class="value-bind address" id="pers_permanent_address_label"></span>
									
									
									
										<select hidden="hidden"
											name="pers_permanent_state" id="pers_permanent_state" class="form-control d-none">
											<option value="0">-- Select
												State --</option>
											<c:forEach var="item" items="${getMedStateName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
										
										 <select hidden="hidden"
											name="district_id" id="pers_permanent_district"
											class="form-control d-none">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									
									
								</div>
							</div>

							<div class="col-lg-4 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Present Address </label> <span
										class="value-bind address" id="pers_present_address"></span>
										
										
										<select hidden="hidden"
											name="pers_present_state" id="pers_present_state" class="form-control d-none">
											<option value="0">-- Select
												State --</option>
											<c:forEach var="item" items="${getMedStateName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
										
										 <select hidden="hidden"
											name="pers_present_district" id="pers_present_district"
											class="form-control d-none">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
										



								</div>
							</div>
							
							<div class="col-lg-4 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Corresponding Address </label> <span
										class="value-bind address" id="corre_address_label"></span>
										
										
										<select hidden="hidden" name="corre_state"
											id="corre_state" class="form-control d-none">
											<option value="0">-- Select State --</option>
											<c:forEach var="item" items="${getMedStateName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
										<select hidden="hidden" name="corre_district"
											id="corre_district" class="form-control d-none">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
										
										 



										
								</div>
							</div>
						</div>
					</div>
					
				<section class="single-detail-block">
					<div class="field-box">
						<div class="row">

							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg">
									<h5 class="custom-title-tag">NEET Details</h5>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none" id="no_neet_data"></span>
							</div>


							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="table-wrapper table-responsive simple-table"
									id="neettable">
									<table class="table">
										<thead>
											<tr>
												<th><h6>Roll No</h6></th>
												<th><h6>Application No</h6></th>
												<th><h6>All India Rank</h6></th>
												<th><h6>Marks</h6></th>
												<th><h6>Percentile</h6></th>

											</tr>
										</thead>
										<tbody id="neetdata">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					</section>
					
					<section class="single-detail-block">
					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg">
									<h5 class="custom-title-tag">Education Details</h5>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none" id="no_edu_data"></span>
							</div>

							<div class="col-lg-12 col-md-12 col-sm-12 col-12" id="edutable">
								<div class="table-wrapper table-responsive custom-table">
									<table class="table">
										<thead>
											<tr>
												<th><h6>Sr No</h6></th>
												<th><h6>Name of Exam</h6></th>
												<th><h6>Board/University</h6></th>
												<th><h6>School/College</h6></th>
												<th><h6>Subject</h6></th>
												<th><h6>Year of Passing</h6></th>
												<th><h6>Percentage of Marks</h6></th>
												<th><h6>Division</h6></th>
												<th><h6>View</h6></th>

											</tr>
										</thead>
										<tbody id="trdata">
										</tbody>
									</table>
								</div>
							</div>


							<%-- <div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div id="my_pdf_viewer"
										class="custom-pdf-viewer d-none">
										<div id="canvas_container">
											<div id="zoom_controls" class="pdf_zoom_control">
												<div class="zoom_btn">
													<button type="button"
														class="btn pdfbtnzoom btn-close danger-btn" value=""
														id="btn-close-pdf">
														<i class="lni lni-close" title='Close'></i>
													</button>
												</div>
												<br>
												<div class="zoom_btn">
													<button type="button" class="btn btn-sm pdfbtnzoom"
														value="">
														<i id="zoom_in" class="lni lni-zoom-in" title='Zoom in'></i>
													</button>
												</div>
												<br />
												<div class="zoom_btn">
													<button type="button" class="btn btn-sm pdfbtnzoom"
														value="">
														<i id="zoom_out" class="lni lni-zoom-out" title='Zoom out'></i>
													</button>
												</div>
											</div>
											<canvas id="pdf_renderer"></canvas>
											<input type="hidden" value="0" id="PicturePDFId" /> <input
												type="hidden" value="0" id="val1" /> <input type="hidden"
												value="0" id="fildname1" />
										</div>
										<div class="footer_btn">
											<div class="footer_btn_control">
												<i id="go_previous"
													class="lni lni lni-chevron-left pdfbtnzoom pdfbtnzoom-pre"
													title='Previous'></i> <input id="current_page"
													class="pdfbtnzoom-current-page" value="1" type="number" readonly="readonly" />
											</div>

											<div class="footer_btn_control">
												<i id="go_next"
													class="lni lni lni-chevron-right pdfbtnzoom pdfbtnzoom-next"
													title='Next'> </i>
											</div>
											<div class="footer_btn_control footer-down-btn"
												id="downloadbtn">
												<i id="downloadbtn" title="Download"
													
													class='lni lni-download pdfbtnzoom pdfbtnzoom-down-btn'></i>
											</div>
										</div>
									</div>
								</div> --%>


						</div>
					</div>
					</section>
					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg">
									<h5 class="custom-title-tag">Uploaded Documents</h5>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none" id="no_uploaded_doc"></span>
							</div>
							<section class="single-detail-block">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12"
								id="table_uploaded_doc">
								<div class="table-wrapper table-responsive custom-table">
									<table class="table">
										<thead>
											<tr>
												<th><h6>Sr No</h6></th>
												<th><h6>Document Name</h6></th>
												<th><h6>View</h6></th>

											</tr>
										</thead>
										<tbody id="trdata_uploaded_doc">
										</tbody>
									</table>
								</div>
							</div>
							</section>
						</div>
					</div>

					<div class="field-box">
						<div id="courtorder_div">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="custom-data-value custom-title custom-title-bg">
										<h5 class="custom-title-tag">Court Order</h5>
									</div>
								</div>
								<div class="inst_block simple-instruction d-none">
									<span class="concat-string" id="co_remarks"> </span>
								</div>

								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="full-data-value">
										<label>Check court order here : </label> <span id="draft_pdf"></span>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg">
									<h5 class="custom-title-tag">Admission Details</h5>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none" id="no_adm_data"></span>
							</div>
							<section class="single-detail-block">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12" id="table_adm">
								<div class="table-wrapper table-responsive simple-table">
									<table class="table">
										<thead>
											<tr>
												<th><h6>Ayush Id</h6></th>
												<th><h6>University</h6></th>
												<th><h6>Institute</h6></th>
												<th><h6>System</h6></th>
												<th><h6>Degree</h6></th>
												<th><h6>University Enrollment Number</h6></th>
											</tr>
										</thead>
										<tbody id="trdata_adm">
										</tbody>
									</table>
								</div>
							</div>
							</section>
						</div>
					</div>
					
					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="auto-fill-form">
									<div class="upload_sign">
									<p class="sign-label">
													Signature..<i class="lni lni-pencil"></i>
												</p>
										<img id="upload_signature_doc_preview" class="" />
									</div>
								</div>
							</div>
							<!-- Hidden Start -->
							<input type="hidden" id="p_id" value="0">
							<!-- Hidden End -->
						</div>
					</div>
				</div>
			</form:form>
		</div>
		
	</div>
	
</section>

<c:url value="doc_uploadNcism_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm5"
	name="applicationUrlForm5" modelAttribute="doc_eid">
	<input type="hidden" name="doc_eid" id="doc_eid" value="0" />
</form:form>

<c:url value="Personal_Details_Ncism_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm"
	name="applicationUrlForm" modelAttribute="pers_details_hid">
</form:form>

<c:url value="Edu_Det_Ncism_Url" var="mainFormUrl1" />
<form:form action="${mainFormUrl1}" method="GET" id="mainForm11"
	name="mainForm11" modelAttribute="eid">
	<input type="hidden" name="eid" id="eid" value="0" />
</form:form>

<c:url value="Edit_edu_reg_mstrUrl" var="Edit_edu_reg_mstrUrl" />
<form:form action="${Edit_edu_reg_mstrUrl}" method="post"
	id="updateForm" name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="delete_edu_reg_mstr_Url" var="delete_edu_reg_mstr_Url" />
<form:form action="${delete_edu_reg_mstr_Url}" method="post"
	id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>
<c:url value="Excel_Auth_Posted_query" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm"
	name="ExcelForm" modelAttribute="cont_comd_ex">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<!-- The Modal -->

<div class="modal fade custom-modal custom-modal-pdf" tabindex="-1"
	aria-labelledby="myLargeModalLabel" id="exampleModal"
	aria-hidden="true">
	<div
		class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Document Preview</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<div class="custom-modal-inner" id="headData1" name="headData1">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="alert alert-success alert-dismissible">
								<button type="button" class="btn-close" data-bs-dismiss="alert"></button>

							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-12 text-center">

							<div id="pdfmodelcanvas"></div>

						</div>
					</div>
				</div>


			</div>
			<div class="modal-footer" id="modal-footer"></div>


		</div>
	</div>
</div>



<script nonce="${cspNonce}">

// Std_dtl_viewCMD

$(document).ready(function() {
	
	var cand_prifix = '${Std_dtl_viewCMD.cand_prifix}';
	var pers_name = '${Std_dtl_viewCMD.pers_name}';
	var pers_middel_name = '${Std_dtl_viewCMD.pers_middel_name}';
	var pers_surname = '${Std_dtl_viewCMD.pers_surname}';
	
	$("#cand_prifix").val(cand_prifix);
	var cp = $("#cand_prifix :selected").text();
	
	var name = cp +". "+ pers_name;
	
	
 	$("#pers_name").val(name);
 	$("#pers_name_label").html(name);
 	

 	$("#pers_middel_name").val(pers_middel_name);
 	$("#pers_middel_name_label").html(pers_middel_name);
	
 	$("#pers_surname").val(pers_surname);
 	$("#pers_surname_label").html(pers_surname);
	
	var pers_father_name = '${Std_dtl_viewCMD.pers_father_name}';
	$("#pers_father_name_label").html(pers_father_name);
	
	var pers_mother_name = '${Std_dtl_viewCMD.pers_mother_name}';
	$("#pers_mother_name_label").html(pers_mother_name);
	
	var pers_date_of_birth = '${Std_dtl_viewCMD.pers_date_of_birth}';
	const date = new Date(pers_date_of_birth);
	const formattedDate = date.toLocaleDateString('en-GB', {
	  day: '2-digit', month: 'short', year: 'numeric'
	}).replace(/ /g, '/');
	
	$("#pers_date_of_birth_label").html(formattedDate);
	
	var pers_gender = '${Std_dtl_viewCMD.pers_gender}';
	$("#pers_gender").val(pers_gender);
	$("#pers_gender_label").html($("#pers_gender :selected").text());	
	
	var pers_mob_no = '${Std_dtl_viewCMD.pers_mob_no}';
	$("#pers_mob_no_label").html(pers_mob_no);
	
	var pers_email = '${Std_dtl_viewCMD.pers_email}';
	$("#pers_email_label").html(pers_email);
	
	var pers_category = '${Std_dtl_viewCMD.pers_category}';
	$("#pers_category").val(pers_category);
	$("#pers_category_label").html($("#pers_category :selected").text());
	
	var pers_religion = '${Std_dtl_viewCMD.pers_religion}';
	$("#pers_religion").val(pers_religion);
	$("#pers_religion_label").html($("#pers_religion :selected").text());
	
	var pers_marital_status = '${Std_dtl_viewCMD.pers_marital_status}';
	$("#pers_marital_status").val(pers_marital_status);
	$("#pers_marital_status_label").html($("#pers_marital_status :selected").text());
	
	var pers_nationality = '${Std_dtl_viewCMD.pers_nationality}';
	$("#pers_nationality").val(pers_nationality);
	$("#pers_nationality_label").html($("#pers_nationality :selected").text());	
	
	var state_id = '${Std_dtl_viewCMD.state_id}';
	$("#state_id").val(state_id);
	$("#state_id_label").html($("#state_id :selected").text());	
	
	var district_id = '${Std_dtl_viewCMD.district_id}';
	$("#district_id").val(district_id);
	$("#district_id_label").html($("#district_id :selected").text());	
	
	var village = '${Std_dtl_viewCMD.village}';
	$("#village_label").html(village);	
	
	var pers_aadhar_no = '${Std_dtl_viewCMD.pers_aadhar_no}';
	$("#pers_aadhar_no_label").html(pers_aadhar_no);
	
	var pers_permanent_house_no = '${Std_dtl_viewCMD.pers_permanent_house_no}';
	var pers_permanent_village = '${Std_dtl_viewCMD.pers_permanent_village}';
	var pers_permanent_postoffice = '${Std_dtl_viewCMD.pers_permanent_postoffice}';
	var pers_permanent_tehsil = '${Std_dtl_viewCMD.pers_permanent_tehsil}';
	var pers_permanent_policestation = '${Std_dtl_viewCMD.pers_permanent_policestation}';
	var pers_permanent_district = '${Std_dtl_viewCMD.pers_permanent_district}';
	var pers_permanent_state = '${Std_dtl_viewCMD.pers_permanent_state}';
	var pers_permanent_pincode = '${Std_dtl_viewCMD.pers_permanent_pincode}';
	var pers_permanent_lendmark = '${Std_dtl_viewCMD.pers_permanent_lendmark}';
	
	$("#pers_permanent_state").val(pers_permanent_state);
	var parm_st =	$("#pers_permanent_state :selected").text();
	$("#pers_permanent_district").val(pers_permanent_district);
	var parm_dest =	$("#pers_permanent_district :selected").text();

	var pers_permanent_address = pers_permanent_house_no + " " 
								+ pers_permanent_village + " "
								+ pers_permanent_postoffice + " " 
								+ pers_permanent_tehsil+" "
								+ parm_dest +" " 
								+ parm_st + " "
								+ pers_permanent_lendmark+" "
								+ pers_permanent_pincode;
	
	$("#pers_permanent_address_label").html(pers_permanent_address);
	
	var pers_present_house_no = '${Std_dtl_viewCMD.pers_present_house_no}';
	var pers_present_village = '${Std_dtl_viewCMD.pers_present_village}';
	var pers_present_postoffice = '${Std_dtl_viewCMD.pers_present_postoffice}';
	var pers_present_tehsil = '${Std_dtl_viewCMD.pers_present_tehsil}';
	var pers_present_policestation = '${Std_dtl_viewCMD.pers_present_policestation}';
	var pers_present_district = '${Std_dtl_viewCMD.pers_present_district}';
	var pers_present_state = '${Std_dtl_viewCMD.pers_present_state}';
	var pers_present_pincode = '${Std_dtl_viewCMD.pers_present_pincode}';
	var pers_present_lendmark = '${Std_dtl_viewCMD.pers_present_lendmark}';
	
	$("#pers_present_state").val(pers_present_state);
	var pres_st = $("#pers_present_state :selected").text();
	$("#pers_present_district").val(pers_present_district);
	var pres_dest = $("#pers_present_district :selected").text();

	var pers_present_address = pers_present_house_no + " "
			+ pers_present_village + " "
			+ pers_present_postoffice + " "
			+ pers_present_tehsil + " " + pres_dest + " "
			+ pres_st + " " 
			+ pers_present_lendmark + " "
			+ pers_present_pincode;
	
	$("#pers_present_address").html(pers_present_address);
	
//corresponding
	
	var corre_house_no = '${Std_dtl_viewCMD.corre_house_no}';
	var corre_village = '${Std_dtl_viewCMD.corre_village}';
	var corre_postoffice = '${Std_dtl_viewCMD.corre_postoffice}';
	var corre_tehsil = '${Std_dtl_viewCMD.corre_tehsil}';
	var corre_policestation = '${Std_dtl_viewCMD.corre_policestation}';
	var corre_district = '${Std_dtl_viewCMD.corre_district}';
	var corre_state = '${Std_dtl_viewCMD.corre_state}';
	var corre_pincode = '${Std_dtl_viewCMD.corre_pincode}';
	var corre_lendmark = '${Std_dtl_viewCMD.corre_lendmark}';
	
	$("#corre_state").val(corre_state);
	var corr_st = $("#corre_state :selected").text();
	$("#corre_district").val(corre_district);
	var corr_dest = $("#corre_district :selected").text();

	var corre_address = corre_house_no + " "
			+ corre_village + " " 
			+ corre_postoffice + " "
			+ corre_tehsil + " " 
			+ corr_dest + " "
			+ corr_st + " " 
			+ corre_lendmark + " "
			+ corre_pincode;
	
	$("#corre_address_label").html(corre_address);
	
	var neet_roll_no = '${Std_dtl_viewCMD.neet_roll_no}';
	var neet_application_no = '${Std_dtl_viewCMD.neet_application_no}';
	var neet_rank = '${Std_dtl_viewCMD.neet_rank}';
	var neet_marks = '${Std_dtl_viewCMD.neet_marks}';
	var neet_percentile = '${Std_dtl_viewCMD.neet_percentile}';
	
		neetdata = '<tr>'
		+'<td><p>'+ neet_roll_no +'</p></td>'
		+'<td><p>'+  neet_application_no +'</p></td>'
		+'<td><p>'+  neet_rank +'</p></td>'
		+'<td><p>'+  neet_marks +'</p></td>'
		+'<td><p>'+  neet_percentile +'</p></td>'
		+'</tr>';
	$("#neetdata").append(neetdata);
	
	var id = '${Std_dtl_viewCMD.id}';

if (id != "") {
	 getEdu_Detail(id);
	 getuploaded_doc(id)
	 
	 //getImagethumb(id);
	 $("#p_id").val(id);
		var signaturephoto="ViewRefImageFileNcism_Download5?kmlId="+id+"&kmlfildname=signature";
	 	$("img#upload_signature_doc_preview").attr('src',signaturephoto);
	 	
		var photographphoto="ViewRefImageFileNcism_Download5?kmlId="+id+"&kmlfildname=photograph";
		$("img#upload_photo_doc_preview").attr('src',photographphoto);
}
	
getadmDetails();
	
setTimeout(onclickbindview, 500);
	
});

function file_view(id,val,field) {
	 $("#exampleModal").modal('show');
// 	var val= $("#docid"+obj).val();
	 $("#PicturePDFId").val(id);
	 $("#val1").val(val);
	 $("#fildname1").val(field);
	var pdf1="kmlFileDownloadFinalDynemic44?kmlId2="+id+"&val444="+val+"&fildname1="+field+" ";
	
// 	 $("#embedpdf").attr('src', pdf1);
	
	PDFView(pdf1,id,val,field);
}

/////////personal_details  from registration table/////Start
function getadmDetails() {
	
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	var pid =  $("#p_id").val();
	
	$.post('get_ayush_idbypid_Ncism_ctrl?' + key + "=" + value,{pid : pid},function(j) {
		
		strdata ="";
		if (j.length > 0) {
		for (var i = 0; i < j.length; i++) {
			strdata += '<tr>'
			+'<td><p>'+ j[i][0] +'</p></td>'
			+'<td><p>'+  j[i][1] +'</p></td>'
			+'<td><p>'+  j[i][2] +'</p></td>'
			+'<td><p>'+  j[i][3] +'</p></td>'
			+'<td><p>'+  j[i][4] +'</p></td>'
			+'<td><p>'+  j[i][5] +'</p></td>'
			+'</tr>';
			
			console.log(j[i][5]);
		}
		//$('div#edutable').empty();
		$("#trdata_adm").append(strdata);
		}
		else {
			$('div#table_adm').empty();
// 			document.getElementById("no_adm_data").style.display = "block";
			
			if ($("#no_adm_data").hasClass("d-none")) {
				 $( "#no_adm_data").removeClass("d-none");
				 $( "#no_adm_data" ).addClass("d-block");
			}
			
			$("#no_adm_data").html("----  NO DATA AVAILABLE ----")
		}
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
	
	
	
	}
/////////////End

function getuploaded_doc(p_id){
	$.post("getuploaded_doc_Ncism_Ctrl?" + key + "=" + value, {
		p_id:p_id
	}, function(j) {
		strdata ="";
		if (j.length > 0) {
		for (var i = 0; i < j.length; i++) {
			strdata += '<tr>'
			+'<td><p>'+ j[i][0] +'</p></td>'
			+'<td><p>'+  j[i][1] +'</p></td>'
			+'<td><p>'+  j[i][3] +'</p></td>'
			+'</tr>';
		}
		$("#trdata_uploaded_doc").append(strdata);
		}
		else {
			$('div#table_uploaded_doc').empty();
// 			document.getElementById("no_uploaded_doc").style.display = "block";
			
				if ($("#no_uploaded_doc").hasClass("d-none")) {
				 $( "#no_uploaded_doc").removeClass("d-none");
				 $( "#no_uploaded_doc" ).addClass("d-block");
			}
			
			$("#no_uploaded_doc").html("----  NO DATA AVAILABLE ----")
		}
	});
	
	$.post("getuploaded_Court_Order_NCISM_Ctrl?" + key + "=" + value, {
		p_id:p_id
	}, function(j) {
		
		
		if (j.length > 0  && j[0][1] != "") {
			$("#draft_pdf").html(j[0][3]);
			$('div#courtorder_div').show();			
		}
		else {
			$('div#courtorder_div').hide();
		}
		
		var remarks = j[0][4];
			if (remarks != "" && remarks != null) {
				const child = document.getElementById('co_remarks');
				child.innerHTML=  "	<strong>Remark : </strong>"+  remarks;
				child.parentElement.classList.remove('d-none');
			} 		
	});
}

function getEdu_Detail(p_id){
//	alert("pid------->   "+p_id);
	$.post("getEdu_Detail_Ncism_Ctrl?" + key + "=" + value, {
		p_id:p_id
	}, function(j) {
		
		strdata ="";
		//var x= 0;
		
		if (j.length > 0) {
		for (var i = 0; i < j.length; i++) {
			
			strdata += '<tr>'
			+'<td><p>'+ j[i][0] +'</p></td>'
			+'<td><p>'+  j[i][1] +'</p></td>'
			+'<td><p>'+  j[i][2] +'</p></td>'
			+'<td><p>'+  j[i][3] +'</p></td>'
			+'<td><p>'+  j[i][4] +'</p></td>'
			+'<td><p>'+  j[i][5] +'</p></td>'
			+'<td><p>'+  j[i][6] +'</p></td>'
			+'<td><p>'+  j[i][7] +'</p></td>'
			+'<td><p>'+  j[i][8] +'</p></td>'
			+'</tr>';
		}
		$("#trdata").append(strdata);
		
		}
		else {
			$('div#edutable').empty();
// 			document.getElementById("no_edu_data").style.display = "block";
			
			if ($("#no_edu_data").hasClass("d-none")) {
				 $( "#no_edu_data").removeClass("d-none");
				 $( "#no_edu_data" ).addClass("d-block");
			}
			
			$("#no_edu_data").html("----  NO DATA AVAILABLE ----")
		}
	});
}

	// Get the modal
	function imageView(obj) {
		var modal = document.getElementById("myModal");
		var span = document.getElementsByClassName("close")[0];

		// Get the image and insert it inside the modal - use its "alt" text as a caption
		var img = document.getElementById("myImg" + obj);
		var modalImg = document.getElementById("img01");
		var captionText = document.getElementById("caption");

		img.onclick = function() {
			modal.style.display = "block";
			modalImg.src = this.src;
			//captionText.innerHTML = this.alt;
		}

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
		}

	}


 //csp-----------------
document.addEventListener('DOMContentLoaded', function() {
	
	});
 
/* //	===================for view
function PDFView(path1){
// 	$("div#my_pdf_viewer").show();
	if ($("#my_pdf_viewer").hasClass("d-none")) {
			 $( "#my_pdf_viewer" ).removeClass("d-none")
		}

	var pdfView1=path1;
	
  	var myState = {
            pdf: null,
            currentPage: 1,
            pdfScale: 1,
            zoom: 1.5
        }
      
        pdfjsLib.getDocument(pdfView1).then((pdf) =>
        {
      
            myState.pdf = pdf;
            render();
 
        });
 
        function render() {
            myState.pdf.getPage(myState.currentPage).then((page) => 
            {
                      
                var canvas = document.getElementById("pdf_renderer");
                var ctx = canvas.getContext('2d');
      
                var viewport = page.getViewport(myState.zoom);
 
                canvas.width = viewport.width;
                canvas.height = viewport.height;
          
                page.render({
                    canvasContext: ctx,
                    viewport: viewport
                });
            });
        }
 
        document.getElementById('go_previous').addEventListener('click', (e) => {
            if(myState.pdf == null || myState.currentPage == 1) 
              return;
            myState.currentPage -= 1;
            document.getElementById("current_page").value = myState.currentPage;
            render();
        });
 
        document.getElementById('go_next').addEventListener('click', (e) => 
        {
        	
            if(myState.pdf == null || myState.currentPage >= myState.pdf._pdfInfo.numPages) 
               return;
            myState.currentPage += 1;
            document.getElementById("current_page").value = myState.currentPage;
            render();
        });
 
        document.getElementById('current_page').addEventListener('keypress', (e) => {
            if(myState.pdf == null) return;
          
            var code = (e.keyCode ? e.keyCode : e.which);
          
            if(code == 13) {
                var desiredPage = 
                document.getElementById('current_page').valueAsNumber;
                                  
                if(desiredPage >= 1 && desiredPage <= myState.pdf._pdfInfo.numPages) {
                    myState.currentPage = desiredPage;
                    document.getElementById("current_page").value = desiredPage;
                    render();
                }
            }
        });        
 
        document.getElementById('zoom_in').addEventListener('click', (e) => {
            if(myState.pdf == null) return;
            myState.zoom += 0.5;
            render();
        });
 
        document.getElementById('zoom_out').addEventListener('click', (e) => {
            if(myState.pdf == null) return;
            myState.zoom -= 0.5;
            render();
        });
        
        document.getElementById('downloadbtn').addEventListener('click', (e) => {
	    	download_file();
	    });
	    
	    document.getElementById('btn-close-pdf').addEventListener('click', (e) => {
	    	hide_PDF();
	    });
        
} */




//===================for view=========================

function PDFView(path1,idx,val,field){
	
			$("#pdfmodelcanvas").empty();
			$("#modal-footer").empty();
			
				var canvas_data = "";
				
				canvas_data += '<div id="my_pdf_viewer'+idx+'" class="custom-pdf-viewer">'										
										+'<div id="canvas_container">'
										+'<div>'
										+'<canvas id="pdf_renderer'+idx+'"  width="600px" height="500px" ></canvas>'
										+'<input type="hidden" value="'+idx+'" id="PicturePDFId" /> <input'
										+'	type="hidden" value="'+val+'" id="val1" /> <input type="hidden"'
										+'	value="'+field+'" id="fildname1" />'
										+'</div>'
										+'</div>'
										+'</div>';

			$("#pdfmodelcanvas").append(canvas_data);
								
				var btn="";
					btn +=   '<div class="modal-footer-left">'
										+'<ul class="buttons-group">'
										+'<li><a  id="zoom_in'+idx+'"	class="main-btn active-btn btn-hover btn-sm btnzoomin"'
										+'title="Zoom In"><i class="lni lni-zoom-in"></i></a></li>'
										+'<li><a  id="zoom_out'+idx+'"	class="main-btn active-btn btn-hover btn-sm btnzoomout"'
										+'title="Zoom Out"><i class="lni lni-zoom-out"></i></a></li>'
										+'</ul>'
										+'</div>'
										+'<div class="modal-footer-center">'
										+'<ul class="buttons-group" >'
										+'<li class="footer_btn_control"><a id="go_previous'+idx+'" type="button"'
										+'class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i class="lni lni-chevron-left"'
										+'></i>Previous</a></li>'
										+'<li><a type="button" class="main-btn dark-btn n "><span '
										+'class="pdf-page">Page: <span id="page_num'+idx+'">1</span>/ <span id="page_count'+idx+'">'
										+'</span></span></a></li>'
										+'<li><a id="go_next'+idx+'" type="button" '
										+'class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left btnnext">Next'
										+'<i class="lni lni-chevron-right"></i>'
										+'</a></li>'
										+'</ul>'
										+'</div>'
										+'<div class="modal-footer-right">'
										+'<ul class="buttons-group">'
										+'<li><a id="downloadbtn'+idx+'" type="button"'
										+'class="main-btn info-btn btn-hover btn-sm btndownload"><i class="lni lni-download mr-5">'
										+'</i>Download</a></li>'
										+'</ul>'
										+'</div>';


					$("#modal-footer").append(btn);
	
	var pdfDoc = null,
    pageNum = 1,
    pageRendering = false,
    pageNumPending = null,
    scale = 1.5,
    canvas = document.getElementById('pdf_renderer'+idx),
    ctx = canvas.getContext('2d');

function renderPage(num) {
  pageRendering = true;
  pdfDoc.getPage(num).then(function(page) {
    var viewport = page.getViewport({scale: scale});
    canvas.height = viewport.height;
    canvas.width = viewport.width;

    var renderContext = {
      canvasContext: ctx,
      viewport: viewport
    };
    var renderTask = page.render(renderContext);

    renderTask.promise.then(function() {
      pageRendering = false;
      if (pageNumPending !== null) {
        renderPage(pageNumPending);
        pageNumPending = null;
      }
    });
  });

  document.getElementById('page_num'+idx).textContent = num;
}


function queueRenderPage(num) {
  if (pageRendering) {
    pageNumPending = num;
  } else {
    renderPage(num);
  }
}

function onPrevPage() {
  if (pageNum <= 1) {
    return;
  }
  pageNum--;
  queueRenderPage(pageNum);
}

document.getElementById('go_previous'+idx).addEventListener('click', onPrevPage);

 
function onNextPage() {
  if (pageNum >= pdfDoc.numPages) {
    return;
  }
  pageNum++;
  queueRenderPage(pageNum);
}
document.getElementById('go_next'+idx).addEventListener('click', onNextPage);

document.getElementById('zoom_in'+idx).addEventListener('click', (e) => {
    if(pdfDoc == null) return;
    if (scale!= 4) {
    scale += 0.5;
   }
    queueRenderPage(pageNum);
});

document.getElementById('zoom_out'+idx).addEventListener('click', (e) => {
	 if(pdfDoc == null) return;
	 
	 if (scale!= 1) {
		 scale -= 0.5;
	}
	    queueRenderPage(pageNum);
});

document.getElementById('downloadbtn'+idx).addEventListener('click', (e) => {
	download_file();
});


 
pdfjsLib.getDocument(path1).promise.then(function(pdfDoc_) {

	 if (pdfDoc) {
         pdfDoc.destroy();
     }
     pdfDoc = pdfDoc_;
  document.getElementById('page_count'+idx).textContent = pdfDoc.numPages;
  renderPage(pageNum);
});
	
}


function PDFView1(path1){

// $("div#my_pdf_viewer").show();
		if ($("#my_pdf_viewer").hasClass("d-none")) {
			 $( "#my_pdf_viewer" ).removeClass("d-none")
		}
		
		
		var pdfView1=path1;
		
	  	var myState = {
	            pdf: null,
	            currentPage: 1,
	            pdfScale: 1,
	            zoom: 1.5
	        }
	      
	        pdfjsLib.getDocument(pdfView1).then((pdf) =>
	        {
	      
	            myState.pdf = pdf;
	            render();
	 
	        });
	 
	        function render() {
	            myState.pdf.getPage(myState.currentPage).then((page) => 
	            {
	                      
	                var canvas = document.getElementById("pdf_renderer");
	                var ctx = canvas.getContext('2d');
	      
	                var viewport = page.getViewport(myState.zoom);
	 
	                canvas.width = viewport.width;
	                canvas.height = viewport.height;
	          
	                page.render({
	                    canvasContext: ctx,
	                    viewport: viewport
	                });
	            });
	        }
	 
	        document.getElementById('go_previous').addEventListener('click', (e) => {
	            if(myState.pdf == null || myState.currentPage == 1) 
	              return;
	            myState.currentPage -= 1;
	            document.getElementById("current_page").value = myState.currentPage;
	            render();
	        });
	 
	        document.getElementById('go_next').addEventListener('click', (e) => 
	        {
	        	
	            if(myState.pdf == null || myState.currentPage >= myState.pdf._pdfInfo.numPages) 
	               return;
	            myState.currentPage += 1;
	            document.getElementById("current_page").value = myState.currentPage;
	            render();
	        });
	 
	        document.getElementById('current_page').addEventListener('keypress', (e) => {
	            if(myState.pdf == null) return;
	          
	            var code = (e.keyCode ? e.keyCode : e.which);
	          
	            if(code == 13) {
	                var desiredPage = 
	                document.getElementById('current_page').valueAsNumber;
	                                  
	                if(desiredPage >= 1 && desiredPage <= myState.pdf._pdfInfo.numPages) {
	                    myState.currentPage = desiredPage;
	                    document.getElementById("current_page").value = desiredPage;
	                    render();
	                }
	            }
	        });        
	 
	        document.getElementById('zoom_in').addEventListener('click', (e) => {
	            if(myState.pdf == null) return;
	            myState.zoom += 0.5;
	            render();
	        });
	 
	        document.getElementById('zoom_out').addEventListener('click', (e) => {
	            if(myState.pdf == null) return;
	            myState.zoom -= 0.5;
	            render();
	        });
	        
	        document.getElementById('downloadbtn').addEventListener('click', (e) => {
		    	download_file();
		    });
		    
		    document.getElementById('btn-close-pdf').addEventListener('click', (e) => {
		    	hide_PDF();
		    });
	        
	} 
	
	
	function View_hide() {
		   if ($("#my_pdf_viewer").hasClass("d-none")) {
				 $( "#my_pdf_viewer" ).removeClass("d-none")
			}
		   
	}
	//HIDE VIEW FILE
	function hide_PDF() {
		   if (!$( "#my_pdf_viewer" ).hasClass('d-none')) {
				$( "#my_pdf_viewer" ).addClass("d-none")
			}
		   
	}
	
	function download_file() {
		
		var id = $("#PicturePDFId").val();
		var val= $("#val1").val();
		var fildname= $("#fildname1").val();
		
		var pdfView="kmlFileDownloadFinalDynemic44?kmlId2="+id+"&val444="+val+"&fildname1="+fildname+"";
		
	    fileName="TEST_DOC";
	    fileURL=pdfView;
	    if (!window.ActiveXObject) {
	        var save = document.createElement('a');
	        save.href = fileURL;
	        save.target = '_blank';
	        var filename = fileURL.substring(fileURL.lastIndexOf('/')+1);
	        save.download = fileName || filename;
		       if ( navigator.userAgent.toLowerCase().match(/(ipad|iphone|safari)/) && navigator.userAgent.search("Chrome") < 0) {
					document.location = save.href; 
				}else{
			        var evt = new MouseEvent('click', {
			            'view': window,
			            'bubbles': true,
			            'cancelable': false
			        });
			        save.dispatchEvent(evt);
			        (window.URL || window.webkitURL).revokeObjectURL(save.href);
				}	
	    }
	    else if ( !! window.ActiveXObject && document.execCommand)     {
	        var _window = window.open(fileURL, '_blank');
	        _window.document.close();
	        _window.document.execCommand('SaveAs', true, fileName || fileURL)
	        _window.close();
	    }
	}
	
	
	
	function onclickbindview() {
	
	document.querySelectorAll('.view_degree').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('docid'+val).value;
			if (confirm('Are You Sure You Want to View Detail?')) {
				file_view(gdid,2,2);
			} else {
				return false;
			}
		})
	});

	document.querySelectorAll('.view_uploaddoc').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('uploddocid'+val).value;
			if (confirm('Are You Sure You Want to View Detail?')) {
				file_view(gdid,1,1);
			} else {
				return false;
			}
		})
	});
	
	document.querySelectorAll('.view_uploadDraftdoc').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var gdid = document.getElementById('draftdocid'+val).value;
			if (confirm('Are You Sure You Want to View Detail?')) {
				file_view(gdid,6,3);
			} else {
				return false;
			}
		})
	});
	
	}
	
</script>












