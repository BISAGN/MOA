<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link href="js/tunneldesign/tunnelold.css" rel="Stylesheet"></link>

<!-- <link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css"> -->
<!-- <script src="js/Calender/jquery-ui.js"></script> -->
<!-- <script src="js/Calender/datePicketValidation.js"></script> -->

<script nonce="${cspNonce}">
	var username = "${username}";
</script>

<section class="dashboard-page">
	<div class="container-fluid" id="page-top">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Declaration Form</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a id="coman"
									href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Declaration Form</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper preview-form">
			<form:form name="student_declar" id="student_declar_view"
				action="student_declar_viewaction" method="post"
				class="form-horizontal" modelAttribute="student_declar_viewCMD">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card-style mb-30" id="card_view">
							<div class="tunnel_design">
								<div class="square tunnel_visited">
									<a href="#" class="tunnel_text" id="tunnel_1"> Personal
										Details</a>
								</div>
								<div class="square tunnel_visited">
									<a href="#" class="tunnel_text" id="tunnel_2"
										href="Edu_Det_Url"> Graduation Details</a>
								</div>
								<div class="square tunnel_visited">
									<a href="#" class="tunnel_text" id="tunnel_3"
										href="doc_uploadUrl"> Upload Document</a>
								</div>
								<div class="square tunnel_active">
									<h5 class="tunnel_text">Declaration</h5>
								</div>
							</div>
						</div>
						<div class="card-style mb-30" id="card_view">
							<div class="field-box">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
										<div class="auto-fill-form">
											<div class="upload_image">
												<img id="upload_photo_doc_preview" class="" />
											</div>
										</div>
									</div>
								</div>
								<!-- <div class="custom-data-value custom-title custom-title-bb">
										<h4 class="custom-title-tag">Student Detail Form </h4>
									</div> -->
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
											<label>First Name</label> <span class="value-bind"
												id="first_name_label"> </span> <input type="hidden"
												id="first_name" name="first_name" class="form-control"
												autocomplete="off" maxlength="25"> <input
												type="hidden" id="viewid" name="viewid" class="form-control"
												value="0" autocomplete="off">
										</div>
									</div>
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Middle Name</label> <span class="value-bind"
												id="middle_name_label"> </span> <input type="hidden"
												id="middle_name" name="middle_name" class="form-control"
												autocomplete="off" maxlength="25">
										</div>
									</div>
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Surname</label> <span class="value-bind"
												id="surname_label"> </span> <input type="hidden"
												id="surname" name="surname" class="form-control"
												autocomplete="off" maxlength="25">
										</div>
									</div>
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Father's Name </label> <span class="value-bind"
												id="father_name_label"></span> <input type="hidden"
												id="father_name" name="father_name" class="form-control"
												autocomplete="off" maxlength="25">
										</div>
									</div>
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Mother's Name </label> <span class="value-bind"
												id="mother_name_label"></span> <input type="hidden"
												id="mother_name" name="mother_name" class="form-control"
												autocomplete="off" maxlength="25">
										</div>
									</div>
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Date of Birth </label> <span class="value-bind"
												id="date_of_birth_label"></span> <input type="hidden"
												id="date_of_birth" name="date_of_birth" class="form-control"
												autocomplete="off" maxlength="25">
										</div>
									</div>
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Gender</label> <span class="value-bind"
												id="gender_label"></span> <select name="gender" id="gender"
												class="form-control d-none">
												<option value="0">-- Select --</option>
												<c:forEach var="item" items="${getgenderList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Mobile Number </label> <span class="value-bind"
												id="mob_no_label"></span> <input type="hidden" id="mob_no"
												name="mob_no" class="form-control" autocomplete="off"
												maxlength="25">
										</div>
									</div>
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>E-Mail ID </label> <span class="value-bind"
												id="email_label"></span> <input type="hidden" id="email"
												name="email" class="form-control" autocomplete="off"
												maxlength="25">
										</div>
									</div>
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Category </label> <span class="value-bind"
												id="category_label"></span> <select type="hidden"
												name="category" id="category" class="form-control d-none">
												<option value="0" selected="selected">-- Select --</option>
												<c:forEach var="item" items="${getcategorylist}"
													varStatus="num">
													<option value="${item.id}" name="${item.category}">${item.category}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Religion </label> <span class="value-bind"
												id="religion_label"></span> <select hidden="hidden"
												name="religion" id="religion" class="form-control">
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
												id="marital_status_label"></span> <select hidden="hidden"
												name="marital_status" id="marital_status"
												class="form-control">
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
												id="nationality_label"></span> <select hidden="hidden"
												name="nationality" id="nationality" class="form-control">
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
											<label>Aadhaar Number </label> <span class="value-bind"
												id="aadhar_no_label"></span> <input type="hidden"
												id="aadhar_no" name="aadhar_no" class="form-control"
												autocomplete="off" maxlength="12">
										</div>
									</div>
								</div>
							</div>


							<div class="field-box">
								<div class="row">
									<div class="col-lg-12 col-sm-12">
										<div class="custom-data-value custom-title custom-title-bg">
											<h5 class="custom-title-tag">Address</h5>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Permanent Address </label> <span
												class="value-bind address" id="pers_permanent_address_label"></span>
											<select type="hidden" name="pers_permanent_address"
												id="pers_permanent_address" class="form-control d-none">
												<option value="0">-- Select Country --</option>
												<c:forEach var="item" items="${getMedNationality}"
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
											<select type="hidden" name="pers_present_address"
												id="pers_present_address" class="form-control d-none">
												<option value="0" selected="selected">-- Select
													Country --</option>
												<c:forEach var="item" items="${getMedNationality}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-6 col-12">
										<div class="custom-data-value">
											<label>Corresponding Address </label> <span
												class="value-bind address" id="corre_address"></span> <select
												type="hidden" name="corre_address" id="corre_address"
												class="form-control d-none">
												<option value="0" selected="selected">-- Select
													Country --</option>
												<c:forEach var="item" items="${getMedNationality}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
							</div>

							<div class="field-box">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
										<div class="custom-data-value custom-title custom-title-bg">
											<h5 class="custom-title-tag">Graduation Details</h5>
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
														<th><h6>Name of exam</h6></th>
														<th><h6>Year of Passing</h6></th>
														<th><h6>University</h6></th>
														<th><h6>No Of Attempts</h6></th>
													</tr>
												</thead>
												<tbody id="trdata">
												</tbody>
											</table>
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
									<div class="col-lg-12 col-md-12 col-sm-12 col-12"
										id="table_adm">
										<div class="table-wrapper table-responsive simple-table">
											<table class="table">
												<thead>
													<tr>
														<th><h6>Ayush Id</h6></th>
														<th><h6>University</h6></th>
														<th><h6>Institute</h6></th>
														<th><h6>System</h6></th>
														<th><h6>Degree</h6></th>
													</tr>
												</thead>
												<tbody id="trdata_adm">
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>

							<div class="field-box">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
										<div class="custom-data-value custom-title custom-title-bg">
											<h5 class="custom-title-tag">Undertaking</h5>
											<input type="hidden" id="reg_no" name="reg_no"
												class="form-control" autocomplete="off" maxlength="25">
										</div>
									</div>

									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
										<div class="custom-choose-one">
								<div class="input-style-form-check_block check-multi-list">
									<div class="form-check checkbox-style">
										<input type="checkbox" id="Declaration" name="Declaration" autocomplete="off" maxlength="25" class="form-check-input">
										<input type="hidden" id="hiddenUpdate" name="hiddenUpdate" class="form-control autocomplete" value="0"> <label class="check-list">
														 I have carefully read the concept and rules
														regarding my admission, I fully understand that my
														admission is provisional and is subject to final approval
														and enrollment by the University. I also understand that
														my provisional admission is without prejudice to the
														directives/rules and regulations/orders/confirmation from
														the designated and competent authorities of the
														state/central government or the hon'ble court. </label> <label class="check-list">
														I hereby agree to abide by the term and conditions or the
														rules pertaining to admission as prescribed by the
														competent authorities and admit that they are binding upon
														me legally and legitimately.</label> <label class="check-list"> I undertake
														to pay the fees fixed by the Competent Authority,
														University</label> <label class="check-list"> I undertake to see daily
														notices exhibited on the noticed board of the college,
														observe and maintain a strict discipline as the student
														and otherwise, in the college premises including hostel
														and campus.</label>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<!-- <div class="d-flex">
												<div class="mr-5">
													<input type="checkbox" id="Declaration" name="Declaration"
														autocomplete="off" maxlength="25" class="form-check-input">
												</div>

												<div class="">
													<div class="col-lg-12 col-12">
														<p class="und_text">
															<i class="bi bi-chevron-right"></i>I have carefully read
															the concept and rules regarding my admission, I fully
															understand that my admission is provisional and is
															subject to final approval and enrollment by the
															University. I also understand that my provisional
															admission is without prejudice to the directives/rules
															and regulations/orders/confirmation from the designated
															and competent authorities of the state/central government
															or the hon'ble court.
														</p>
													</div>

													<div class="col-lg-12 col-12">
														<p class="und_text">
															<i class="bi bi-chevron-right"></i>I hereby agree to
															abide by the term and conditions or the rules pertaining
															to admission as prescribed by the competent authorities
															and admit that they are binding upon me legally and
															legitimately.
														</p>
													</div>

													<div class="col-lg-12 col-12">
														<p class="und_text">
															<i class="bi bi-chevron-right"></i>I undertake to pay the
															fees fixed by the Competent Authority, University
														</p>
													</div>

													<div class="col-lg-12 col-12">
														<p class="und_text">
															<i class="bi bi-chevron-right"></i>I undertake to see
															daily notices exhibited on the noticed board of the
															college, observe and maintain a strict discipline as the
															student and otherwise, in the college premises including
															hostel and campus.
														</p>
													</div>
												</div>
						                </div>	 -->
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
								</div>
							</div>

							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a id="aIdPrevious"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btn-iconic-left"><i
													class="lni lni-chevron-left"></i> Previous</a></li>
											<li><a type="button" class="main-btn info-btn btn-hover btnsubmit"
												value="Submit" id="finalsubmit">Submit</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
					</div>
				</div>
			</form:form>
		</div>
		<!-- Hidden Start -->
		<input type="hidden" id="p_id" value="0">
		<!-- Hidden End -->
	</div>
</section>

<c:url value="doc_upload_PGUrl" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm5"
	name="applicationUrlForm5" modelAttribute="doc_eid">
	<input type="hidden" name="doc_eid" id="doc_eid" value="0" />
</form:form>

<c:url value="Personal_Details_PG_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm"
	name="applicationUrlForm" modelAttribute="pers_details_hid">
</form:form>

<c:url value="Graduation_Det_PG_Url" var="mainFormUrl1" />
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
	<!-- 	 <input type="hidden" name="cont_comd_ex" id="cont_comd_ex"  value="0"> -->
	<!-- 	   <input type="hidden" name="cont_corps_ex" id="cont_corps_ex" value="0"> -->
	<!-- 	   <input type="hidden" name="cont_div_ex" id="cont_div_ex" value="0"> -->
	<!-- 	   <input type="hidden" name="cont_bde_ex" id="cont_bde_ex" value="0"> -->
	<!-- 	   <input type="hidden" name="cont_comd_txt" id="cont_comd_txt" > -->
	<!-- 	   <input type="hidden" name="cont_corps_txt" id="cont_corps_txt"> -->
	<!-- 	   <input type="hidden" name="cont_div_txt" id="cont_div_txt"> -->
	<!-- 	   <input type="hidden" name="cont_bde_txt" id="cont_bde_txt"> -->
	<!-- 	   <input type="hidden" name="unit_name_ex" id="unit_name_ex"> -->
	<!-- 	   <input type="hidden" name="sus_no_ex" id="sus_no_ex"> -->
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>


<c:url value="Student_Personal_Pg_Details_Action"
	var="Student_Personal_Pg_Details_Action" />
<form:form action="${Student_Personal_Pg_Details_Action}" method="POST"
	id="student_submit" name="student_submit" modelAttribute="eid3">
	<input type="hidden" name="status2" id="status2" value="0" />
	<input type="hidden" name="eid3" id="eid3" value="0" />

</form:form>

<!-- The Modal -->
<div id="myModal" class="modal">
	<span class="close">&times;</span> <img class="modal-content"
		id="img01">
	<div id="caption"></div>
</div>


<script nonce="${cspNonce}" type="text/javascript">
	// Std_dtl_viewCMD

	$(document)
			.ready(
					function() {

						var first_name = '${Std_dtl_viewCMD.first_name}';
						var middel_name = '${Std_dtl_viewCMD.middel_name}';
						var surname = '${Std_dtl_viewCMD.surname}';

						// 	var name =  first_name +"  " + middel_name +"  "+ surname;

						$("#first_name").val(name);
						$("#first_name_label").html(first_name);

						$("#middel_name").val(name);
						$("#middle_name_label").html(middel_name);

						$("#surname").val(name);
						$("#surname_label").html(surname);

						var father_name = '${Std_dtl_viewCMD.father_name}';
						$("#father_name_label").html(father_name);

						var mother_name = '${Std_dtl_viewCMD.mother_name}';
						$("#mother_name_label").html(mother_name);

						var date_of_birth = '${Std_dtl_viewCMD.date_of_birth}';
						const date = new Date(date_of_birth);
						const formattedDate = date.toLocaleDateString('en-GB',
								{
									day : '2-digit',
									month : 'short',
									year : 'numeric'
								}).replace(/ /g, '/');

						$("#date_of_birth_label").html(formattedDate);

						var gender = '${Std_dtl_viewCMD.gender}';
						$("#gender").val(gender);
						$("#gender_label").html($("#gender :selected").text());

						var mob_no = '${Std_dtl_viewCMD.mob_no}';
						$("#mob_no_label").html(mob_no);

						var email = '${Std_dtl_viewCMD.email}';
						$("#email_label").html(email);

						var category = '${Std_dtl_viewCMD.category}';
						//$("#category_label").html(category);
						$("#category").val(category);
						$("#category_label").html(
								$("#category :selected").text());

						var religion = '${Std_dtl_viewCMD.religion}';
						$("#religion").val(religion);
						$("#religion_label").html(
								$("#religion :selected").text());

						var marital_status = '${Std_dtl_viewCMD.marital_status}';
						$("#marital_status").val(marital_status);
						$("#marital_status_label").html(
								$("#marital_status :selected").text());

						var nationality = '${Std_dtl_viewCMD.nationality}';
						$("#nationality").val(nationality);
						// 	$("#nationality").val('33');
						$("#nationality_label").html(
								$("#nationality :selected").text());

						var aadhar_no = '${Std_dtl_viewCMD.aadhar_no}';
						$("#aadhar_no_label").html(aadhar_no);

						var permanent_house_no = '${Std_dtl_viewCMD.permanent_house_no}';
						var permanent_village = '${Std_dtl_viewCMD.permanent_village}';
						var permanent_add_line1 = '${Std_dtl_viewCMD.permanent_add_line1}';
						var permanent_add_line2 = '${Std_dtl_viewCMD.permanent_add_line2}';
						var permanent_district = '${Std_dtl_viewCMD.permanent_district}';
						var permanent_state = '${Std_dtl_viewCMD.permanent_state}';
						var permanent_pincode = '${Std_dtl_viewCMD.permanent_pincode}';
						var permanent_lendmark = '${Std_dtl_viewCMD.permanent_lendmark}';

						var pers_permanent_address = permanent_house_no + " "
								+ permanent_add_line1 + " "
								+ permanent_add_line2 + " " + permanent_village
								+ " " + permanent_pincode;
						$("#pers_permanent_address_label").html(
								pers_permanent_address);

						var present_house_no = '${Std_dtl_viewCMD.present_house_no}';
						var present_village = '${Std_dtl_viewCMD.present_village}';
						var present_add_line1 = '${Std_dtl_viewCMD.present_add_line1}';
						var present_add_line2 = '${Std_dtl_viewCMD.present_add_line2}';
						var present_district = '${Std_dtl_viewCMD.present_district}';
						var present_state = '${Std_dtl_viewCMD.present_state}';
						var present_pincode = '${Std_dtl_viewCMD.present_pincode}';
						var present_lendmark = '${Std_dtl_viewCMD.present_lendmark}';

						var pers_present_address = present_house_no + " "
								+ present_add_line1 + " " + present_add_line2
								+ " " + present_village + " " + present_pincode;
						$("#pers_present_address").html(pers_present_address);

						var corre_house_no = '${Std_dtl_viewCMD.corre_house_no}';
						var corre_village = '${Std_dtl_viewCMD.corre_village}';
						var corre_add_line1 = '${Std_dtl_viewCMD.corre_add_line1}';
						var corre_add_line2 = '${Std_dtl_viewCMD.corre_add_line2}';
						var corre_district = '${Std_dtl_viewCMD.corre_district}';
						var corre_state = '${Std_dtl_viewCMD.corre_state}';
						var corre_pincode = '${Std_dtl_viewCMD.corre_pincode}';
						var corre_lendmark = '${Std_dtl_viewCMD.corre_lendmark}';

						var corre_address = corre_house_no + " "
								+ corre_add_line1 + " " + corre_add_line2 + " "
								+ corre_village + " " + corre_pincode;
						$("#corre_address").html(corre_address);

						var id = '${Std_dtl_viewCMD.id}';
						// 	var p_id = '${Std_dtl_viewCMD.p_id}';
						// 	$("#pres_state").val(prest_name);
						// alert(prest_name);

						if (id != "") {
							getEdu_Detail(id);
							//getImagethumb(id);
							$("#p_id").val(id);

							var signaturephoto = "ViewRefImage_PGFileDownload5?kmlId="
									+ id + "&kmlfildname=signature";
							$("img#upload_signature_doc_preview").attr('src',
									signaturephoto);

							var photographphoto = "ViewRefImage_PGFileDownload5?kmlId="
									+ id + "&kmlfildname=photograph";
							$("img#upload_photo_doc_preview").attr('src',
									photographphoto);
						}

						getadmDetails();

					});

	/////////personal_details  from registration table/////Start
	function getadmDetails() {

		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		var userid = "${userId}";
		$
				.post(
						'get_ayush_id_PG_ctrl?' + key + "=" + value,
						{
							userid : userid
						},
						function(j) {

							
							var ayushid ="-";
							
							strdata = "";
							if (j.length > 0) {
								for (var i = 0; i < j.length; i++) {
									
									if (j[i][0] != null && j[i][0] != "") {
										ayushid = j[i][0];
									}
									strdata += '<tr>' + '<td><p>' + ayushid
											+ '</p></td>' + '<td><p>' + j[i][1]
											+ '</p></td>' + '<td><p>' + j[i][2]
											+ '</p></td>' + '<td><p>' + j[i][3]
											+ '</p></td>' + '<td><p>' + j[i][4]
											+ '</p></td>' + '</tr>';
								}
								//$('div#edutable').empty();
								$("#trdata_adm").append(strdata);
							} else {
								$('div#table_adm').empty();
								document.getElementById("no_adm_data").style.display = "block";
								$("#no_adm_data").html(
										"----  NO DATA AVAILABLE ----")
							}
						}).fail(function(xhr, textStatus, errorThrown) {
					alert("fail to fetch")
				});

	}
	/////////////End
	function getEdu_Detail(p_id) {
		//	alert("pid------->   "+p_id);
		$.post("getGrdu_pg_Detail_Ctrl?" + key + "=" + value, {
			p_id : p_id
		}, function(j) {
			strdata = "";
			//var x= 0;

			if (j.length > 0) {
				for (var i = 0; i < j.length; i++) {
					strdata += '<tr>' + '<td><p>' + j[i][0] + '</p></td>' + '<td><p>'
							+ j[i][1] + '</p></td>' + '<td><p>' + j[i][2] + '</p></td>'
							+ '<td><p>' + j[i][3] + '</p></td>' + '<td><p>' + j[i][4]
							+ '</p></td>'
							// 			+'<td>'+  j[i][5] +'</td>'
							// 			+'<td>'+  j[i][6] +'</td>'
							// 			+'<td>'+  j[i][7] +'</td>'
							+ '</tr>';
				}
				//$('div#edutable').empty();
				$("#trdata").append(strdata);

			} else {
				$('div#edutable').empty();
				document.getElementById("no_edu_data").style.display = "block";
				$("#no_edu_data").html("----  NO DATA AVAILABLE ----")
			}
		});
	}

	function getpersdetailsPage() {
		$("#pers_details_hid").val($("#p_id").val());
		document.getElementById("applicationUrlForm").submit();
	}
	function getEduPage() {
		$("#eid").val($("#p_id").val());
		$("#pers_exper_hid").val("${pers_adv_details_session}");
		document.getElementById("mainForm11").submit();
	}

	function getPreviousPage() {
		$("#doc_eid").val($("#p_id").val());
		$("#pers_exper_hid").val("${pers_adv_details_session}");
		document.getElementById("applicationUrlForm5").submit();
	}

	function FinalSubmit() {
		var paramvar = $('input[name="Declaration"]:checkbox:checked').map(
				function() {
					return this.value;
				}).get();

		if (paramvar == "on") {
			$("#eid3").val('${Std_dtl_viewCMD.id}');
			document.getElementById('student_submit').submit();
		} else {
			alert("Please Accept the Undertaking");
		}
	}

	// $(document).ready(function() {
	// 	var prest_name = '${Pract_viewCMD.pre_state}';
	// 	$("#pres_state").val(prest_name);
	// 	prest_name = $("#pres_state option:selected").text();
	// 	var prestdist_name = '${Pract_viewCMD.pre_district}';
	// 	$("#pre_district").val(prestdist_name);
	// 	prestdist_name = $("#pre_district option:selected").text();
	// 	var perst_name = '${Pract_viewCMD.per_state}';
	// 	$("#pres_state").val(perst_name);
	// 	perst_name = $("#pres_state option:selected").text();
	// 	var perstdist_name = '${Pract_viewCMD.per_district}';
	// 	$("#per_district").val(perstdist_name);
	// 	perstdist_name = $("#per_district option:selected").text();
	// 	var nati_name = '${Pract_viewCMD.nationality}';
	// 	$("#nationality").val(nati_name);
	// 	nati_name = $("#nationality option:selected").text();
	// 	//var todayDate = $("#dob").val('${Pract_viewCMD.dob}').toISOString().slice(0, 10);
	// 	//console.log(todayDate);
	// 	$("#nrh_en_no").val('${Pract_viewCMD.nrh_en_no}');
	// 	$("#nrh_en_no1").text('${Pract_viewCMD.nrh_en_no}');
	// 	$("#first_name").val('${Pract_viewCMD.first_name}');
	// 	$("#first_name1").text('${Pract_viewCMD.first_name}');
	// 	$("#father_name").val('${Pract_viewCMD.father_name}');
	// 	$("#father_name1").text('${Pract_viewCMD.father_name}');
	// //	alert('${Pract_viewCMD.pre_address_details1}');
	// 	$("#pre_corre_add").val('${Pract_viewCMD.pre_address_details1}'+' '+'${Pract_viewCMD.pre_address_details2}'+' '+'${Pract_viewCMD.pre_address_details3}'+' '+prest_name+' '+prestdist_name+','+'${Pract_viewCMD.pre_pincode}');
	// 	$("#pre_corre_add1").text('${Pract_viewCMD.pre_address_details1}'+' '+'${Pract_viewCMD.pre_address_details2}'+' '+'${Pract_viewCMD.pre_address_details3}'+' '+prest_name+' '+prestdist_name+','+'${Pract_viewCMD.pre_pincode}');
	// 	$("#per_address").val('${Pract_viewCMD.per_address_details1}'+' '+'${Pract_viewCMD.per_address_details2}'+' '+'${Pract_viewCMD.per_address_details3}'+' '+perst_name+' '+perstdist_name+','+'${Pract_viewCMD.per_pincode}');
	// 	$("#per_address1").text('${Pract_viewCMD.per_address_details1}'+' '+'${Pract_viewCMD.per_address_details2}'+' '+'${Pract_viewCMD.per_address_details3}'+' '+perst_name+' '+perstdist_name+','+'${Pract_viewCMD.per_pincode}');
	// 	$("#email_id").val('${Pract_viewCMD.email_id}');
	// 	$("#email_id1").text('${Pract_viewCMD.email_id}');
	// 	var ee = '${Pract_viewCMD.dob}';
	// 	// 	$("#dob").val(ee);
	// 	//var dateObject = new Date(ee);
	// 	// 	document.body.innerHTML = dateObject.toString();
	// 	//var todayDate = dateObject.toISOString().slice(0, 10);
	// 	// 	alert(todayDate);
	// 	const date = new Date(ee);
	// 	const formattedDate = date.toLocaleDateString('en-GB', {
	// 	  day: 'numeric', month: 'numeric', year: 'numeric'
	// 	}).replace(/ /g, '/');
	// //	console.log(formattedDate);
	// 	$("#dob1").text(formattedDate);
	// 	var dt_reg2 = '${Pract_viewCMD.date_of_reg}';
	// 	const date1 = new Date(dt_reg2);
	// 	const formattedDate1 = date1.toLocaleDateString('en-GB', {
	// 	  day: 'numeric', month: 'numeric', year: 'numeric'
	// 	}).replace(/ /g, '/');
	// //	console.log(formattedDate1);
	// 	$("#date_of_reg").val('${Pract_viewCMD.date_of_reg}');
	// 	$("#date_of_reg1").text(formattedDate1);
	// 	$("#reg_no").val('${Pract_viewCMD.reg_no}');
	// 	$("#reg_no1").text('${Pract_viewCMD.reg_no}');
	// 	$("#register_state").val('${Pract_viewCMD.per_state}');
	// 	$("#register_state1").text(perst_name);
	// 	$("#photo_path").val('${Pract_viewCMD.photo_path}');
	// 	$("#valid_up_to").val('${Pract_viewCMD.valid_up_to}');
	// 	$("#valid_up_to1").text('${Pract_viewCMD.valid_up_to}');
	// 	$("#id").val('${Pract_viewCMD.id}');
	// 	var idforimg = '${Pract_viewCMD.id}';
	// 	 $('#identity_image_preview').attr("src", "MedicalImagePath7?i_id="+idforimg+" ");

	// 	//alert(${Pract_viewCMD.nationality})
	// 		$("#nationality").val('${Pract_viewCMD.nationality}');
	// 		$("#nationality1").text(nati_name);
	// });
	// function setselectall(){
	// 	if ($("#nSelAll").prop("checked")) {
	// 		$(".nrCheckBox").prop('checked', true);
	// 	} else {
	// 		$(".nrCheckBox").prop('checked', false);
	// 	}
	// 	var l = $('[name="cbox"]:checked').length;
	// 	 $("#tregn").val(l);
	// 	document.getElementById('tregn').innerHTML = l;
	// }
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
</script>


<script nonce="${cspNonce}" type="text/javascript">
	// function getExcel() {	
	// 	var cont_comd_txt = $("#cont_comd option:selected").text();
	//  	var cont_corps_txt=$("#cont_corps option:selected").text();
	//  	var cont_div_txt=$("#cont_div option:selected").text();
	//  	var cont_bde_txt=$("#cont_bde option:selected").text();
	// 	var cont_comd=$("#cont_comd").val();
	//  	var cont_corps=$("#cont_corps").val();
	//  	var cont_div=$("#cont_div").val();
	//  	var cont_bde=$("#cont_bde").val();
	//  	var unit_name=$("#unit_name").val();
	//  	var sus_no=$("#sus_no").val();
	//  	if(cont_comd_txt == "--Select--") {
	// 		cont_comd_txt = "";
	// 	}
	// 	if(cont_corps_txt == "--Select--") {
	// 		cont_corps_txt = "";		
	// 	}
	// 	if(cont_div_txt == "--Select--") {
	// 		cont_div_txt = "";
	// 	}
	// 	if(cont_bde_txt == "--Select--") {
	// 		cont_bde_txt = "";
	// 	}
	//  	$("#cont_comd_txt").val(cont_comd_txt);
	// 	$("#cont_corps_txt").val(cont_corps_txt);
	// 	$("#cont_div_txt").val(cont_div_txt);
	// 	$("#cont_bde_txt").val(cont_bde_txt);
	// 	$("#cont_comd_ex").val(cont_comd);
	// 	$("#cont_corps_ex").val(cont_corps);
	// 	$("#cont_div_ex").val(cont_div);
	// 	$("#cont_bde_ex").val(cont_bde);
	// 	$("#unit_name_ex").val(unit_name);
	// 	$("#sus_no_ex").val(sus_no);
	// 	document.getElementById('typeReport1').value = 'excelL';
	// 	document.getElementById('ExcelForm').submit();
	// } 
	//  function getDistrictper() {
	// 	var selval = $("#per_state").val();
	// 	$("select#per_district").empty();
	// 	$ .post( "getDistrictOnstate?" + key + "=" + value,
	// 					{
	// 						selval : selval
	// 					},
	// 					function(j) {

	// 						var options = '<option value="' + "0" + '">'
	// 								+ "--Select--" + '</option>';
	// 						for (var i = 0; i < j.length; i++) {

	// 							options += '<option   value="' + j[i].district_id + '" name="'+j[i].district_id+'" >'
	// 									+ j[i].district_name + '</option>';
	// 						}
	// 						$("select#per_district").html(options);

	// 					});
	// } 

	//  function getDegreeName(obj){
	// 	var DegreeName = $("#"+obj.id).val();
	// 	$.post('getDegreeName?' + key + "=" + value,{DegreeName:DegreeName},function(k) {
	// 		var options = '';
	// 		for (var i = 0; i < k.length; i++) {
	// 			options += '<option value="'+k[i].id+'" name="' + k[i].degree_name+ '" >'+ k[i].degree_name + '</option>';
	// 		}
	// 		$("#degree_name").html(options);

	// 	});

	// } 

	// function printDiv(divName){

	// 	var printContents = document.getElementById(divName).innerHTML;
	// 	var originalContents = document.body.innerHTML;

	// 	document.body.innerHTML = printContents;
	// 	$("#header_print").show();

	// 	var prest_name = '${Pract_viewCMD.pre_state}';
	// 	$("#pres_state").val(prest_name);
	// 	prest_name = $("#pres_state option:selected").text();

	// 	var prestdist_name = '${Pract_viewCMD.pre_district}';
	// 	$("#pre_district").val(prestdist_name);
	// 	prestdist_name = $("#pre_district option:selected").text();

	// 	var perst_name = '${Pract_viewCMD.per_state}';
	// 	$("#pres_state").val(perst_name);
	// 	perst_name = $("#pres_state option:selected").text();

	// 	var perstdist_name = '${Pract_viewCMD.per_district}';
	// 	$("#per_district").val(perstdist_name);
	// 	perstdist_name = $("#per_district option:selected").text();
	// 	var nati_name = '${Pract_viewCMD.nationality}';
	// 	$("#nationality").val(nati_name);
	// 	nati_name = $("#nationality option:selected").text();
	// 	//var todayDate = $("#dob").val('${Pract_viewCMD.dob}').toISOString().slice(0, 10);
	// 	//console.log(todayDate);
	// 	$("#nrh_en_no").val('${Pract_viewCMD.nrh_en_no}');
	// 	$("#nrh_en_no1").text('${Pract_viewCMD.nrh_en_no}');
	// 	$("#first_name").val('${Pract_viewCMD.first_name}');
	// 	$("#first_name1").text('${Pract_viewCMD.first_name}');
	// 	$("#father_name").val('${Pract_viewCMD.father_name}');
	// 	$("#father_name1").text('${Pract_viewCMD.father_name}');
	// //	alert('${Pract_viewCMD.pre_address_details1}');
	// 	$("#pre_corre_add").val('${Pract_viewCMD.pre_address_details1}'+' '+'${Pract_viewCMD.pre_address_details2}'+' '+'${Pract_viewCMD.pre_address_details3}'+' '+prest_name+' '+prestdist_name+','+'${Pract_viewCMD.pre_pincode}');
	// 	$("#pre_corre_add1").text('${Pract_viewCMD.pre_address_details1}'+' '+'${Pract_viewCMD.pre_address_details2}'+' '+'${Pract_viewCMD.pre_address_details3}'+' '+prest_name+' '+prestdist_name+','+'${Pract_viewCMD.pre_pincode}');
	// 	$("#per_address").val('${Pract_viewCMD.per_address_details1}'+' '+'${Pract_viewCMD.per_address_details2}'+' '+'${Pract_viewCMD.per_address_details3}'+' '+perst_name+' '+perstdist_name+','+'${Pract_viewCMD.per_pincode}');
	// 	$("#per_address1").text('${Pract_viewCMD.per_address_details1}'+' '+'${Pract_viewCMD.per_address_details2}'+' '+'${Pract_viewCMD.per_address_details3}'+' '+perst_name+' '+perstdist_name+','+'${Pract_viewCMD.per_pincode}');
	// 	$("#email_id").val('${Pract_viewCMD.email_id}');
	// 	$("#email_id1").text('${Pract_viewCMD.email_id}');
	// 	var ee = '${Pract_viewCMD.dob}';
	// 	// 	$("#dob").val(ee);
	// 	//var dateObject = new Date(ee);
	// 	// 	document.body.innerHTML = dateObject.toString();
	// 	//var todayDate = dateObject.toISOString().slice(0, 10);
	// 	// 	alert(todayDate);
	// 	const date = new Date(ee);
	// 	const formattedDate = date.toLocaleDateString('en-GB', {
	// 	  day: 'numeric', month: 'numeric', year: 'numeric'
	// 	}).replace(/ /g, '/');
	// //	console.log(formattedDate);

	// 	$("#dob1").text(formattedDate);

	// 	var dt_reg2 = '${Pract_viewCMD.date_of_reg}';

	// 	const date1 = new Date(dt_reg2);
	// 	const formattedDate1 = date1.toLocaleDateString('en-GB', {
	// 	  day: 'numeric', month: 'numeric', year: 'numeric'
	// 	}).replace(/ /g, '/');
	// //	console.log(formattedDate1);

	// 	$("#date_of_reg").val('${Pract_viewCMD.date_of_reg}');
	// 	$("#date_of_reg1").text(formattedDate1);
	// 	$("#reg_no").val('${Pract_viewCMD.reg_no}');
	// 	$("#reg_no1").text('${Pract_viewCMD.reg_no}');
	// 	$("#register_state").val('${Pract_viewCMD.per_state}');
	// 	$("#register_state1").text(perst_name);
	// 	$("#photo_path").val('${Pract_viewCMD.photo_path}');

	// 	$("#valid_up_to").val('${Pract_viewCMD.valid_up_to}');
	// 	$("#valid_up_to1").text('${Pract_viewCMD.valid_up_to}');
	// 	$("#id").val('${Pract_viewCMD.id}');
	// 	var idforimg = '${Pract_viewCMD.id}';
	// 	 $('#identity_image_preview').attr("src", "MedicalImagePath7?i_id="+idforimg+" ");

	// 	//alert(${Pract_viewCMD.nationality})
	// 		$("#nationality").val('${Pract_viewCMD.nationality}');
	// 		$("#nationality1").text(nati_name);

	// 		//alert(${Pract_viewCMD.nationality})
	// 		 var id = $("#id").val();
	// 			$.post("getViewFromHosp?" + key + "=" + value, {

	// 				id:id

	// 			}, function(j) {
	// 				var hosp_list = "";
	// 				var name_per = "";
	// 				for (var i = 0; i < j.length; i++) {
	// 						if(i==0){
	// 							hosp_list += j[i].place_of_working_name +", "+ j[i].hos_address1  +" "+ j[i].hos_address2  +" "+ j[i].hos_address3;
	// 							name_per += j[i].name_of_res_p ;
	// 						}
	// 						else{
	// 					hosp_list += ", "+ j[i].place_of_working_name +", "+ j[i].hos_address1  +" "+ j[i].hos_address2  +" "+ j[i].hos_address3 ;
	// 						name_per += ", "+j[i].name_of_res_p ;
	// 						}
	// 				}
	// 				$("#hos_name_add").val(hosp_list);
	// 				$("#name_of_res_p").val(name_per);

	// 			});

	// 			$.post("getViewFromDegree?" + key + "=" + value, {

	// 				id:id

	// 			}, function(j) {
	// 				var degree_list = "";
	// 				for (var i = 0; i < j.length; i++) {
	// 						if(i==0){
	// 							var dg_name = j[i].degree_name;
	// 							$("#degree").val(dg_name);
	// 							dg_name = $("#degree option:selected").text();

	// //							console.log($("#degree").attr('name'));
	// 							degree_list += dg_name +", "+ j[i].name_of_institute  +" "+ j[i].month_and_year_of_degree;
	// 						}
	// 						else
	// 							degree_list += ", "+ dg_name +", "+ j[i].name_of_institute  +" "+ j[i].month_and_year_of_degree  ;
	// 				}
	// 				$("#degree_uni_my").val(degree_list);

	// 			});

	// // 	$("#card_ view").show();

	// 	window.print();

	// 	document.body.innerHTML = originalContents;
	// 	window.location.reload();

	// }

	//   $(document).ready(function() {
	// 	mockjax1('Search_regulation_Master');
	// 	table = dataTable('Search_regulation_Master');
	// 	$('#btn-reload').on('click', function() {
	// 		table.ajax.reload();
	// 	});

	// 	$.ajaxSetup({
	// 		async : false
	// 	});

	// 	$.ajaxSetup({
	// 		async : false
	// 	});

	// 	  var id = $("#id").val();
	// 		$.post("getViewFromHosp?" + key + "=" + value, {

	// 			id:id

	// 		}, function(j) {
	// 			var hosp_list = "";
	// 			var name_per = "";
	// 			for (var i = 0; i < j.length; i++) {
	// 					if(i==0){
	// 						hosp_list += j[i].place_of_working_name +", "+ j[i].hos_address1  +" "+ j[i].hos_address2  +" "+ j[i].hos_address3;
	// 						name_per += j[i].name_of_res_p ;
	// 					}
	// 					else{
	// 				hosp_list += ", "+ j[i].place_of_working_name +", "+ j[i].hos_address1  +" "+ j[i].hos_address2  +" "+ j[i].hos_address3 ;
	// 					name_per += ", "+j[i].name_of_res_p ;
	// 					}
	// 			}
	// 			$("#hos_name_add").val(hosp_list);
	// 			$("#hos_name_add1").text(hosp_list);
	// 			$("#name_of_res_p").val(name_per);
	// 			$("#name_of_res_p1").text(name_per);

	// 		});

	// 		$.post("getViewFromDegree?" + key + "=" + value, {

	// 			id:id

	// 		}, function(j) {
	// 			var degree_list = "";
	// 			for (var i = 0; i < j.length; i++) {
	// 					if(i==0){
	// 						var dg_name = j[i].degree_name;
	// 						$("#degree").val(dg_name);
	// 						dg_name = $("#degree option:selected").text();

	// 						//start

	// 					var mo = j[i].month_and_year_of_degree;

	// 						const datemo = new Date(mo);
	// 						const formattedDate2 = datemo.toLocaleDateString(
	// 								'en-GB', {
	// 									month : 'short',
	// 									year : 'numeric'
	// 								}).replace(/ /g, '-');
	// 							console.log(formattedDate2);
	// 						//end

	// 						// 	console.log($("#degree").attr('name'));
	// 						degree_list += dg_name + ", "
	// 								+ j[i].name_of_institute + " "
	// 								+ formattedDate2;
	// 					} else
	// 						degree_list += ", " + dg_name + ", "
	// 								+ j[i].name_of_institute + " "
	// 								+ formattedDate2;
	// 				}
	// 				$("#degree_uni_my").val(degree_list);
	// 				$("#degree_uni_my1").text(degree_list);

	// 			});

	// 			//$("select#nationality").empty();

	// 			// 			$.post("getViewFromNationality?" + key + "=" + value, { id:id

	// 			// 			}, function(j) {

	// 			// 				alert("fdgdg--"+ j[i].nationality_id)

	// 			// 				var options = '<option value="' + "0" + '">'
	// 			// 				+ "--Select--" + '</option>';
	// 			// 		for (var i = 0; i < j.length; i++) {

	// 			// 			options += '<option   value="' + j[i].nationality_id + '" name="'+j[i].nationality_id+'" >'
	// 			// 					+ j[i].nationality + '</option>';
	// 			// 		}
	// 			// 		$("select#nationality").html(options);

	// 			// 			});
	// 		}); 

	//  function EditData(id) {
	// 	// 		alert("id---"+id)
	// 	$("#id1").val(id);
	// 	document.getElementById('updateForm').submit();
	// }

	// function deleteData(id) {
	// 	$("#id2").val(id);
	// 	document.getElementById('deleteForm').submit();
	// } 

	//  function findselected() {
	// 	var nrSel = $('.nrCheckBox:checkbox:checked').map(function() {
	// 		return $(this).attr('id');
	// 	}).get();

	// 	var b = nrSel.join(':');
	// 	$("#CheckVal").val(b);
	// 	$('#tregn').text(nrSel.length);
	// } 

	//  	function setApproveStatus() {

	// 	findselected();

	// 	var a = $("#CheckVal").val();

	// 	if (a == "") {
	// 		alert("Please Select the Data for Approval");
	// 	} else {
	// 		$.post("Approve_regulation_Data?" + key + "=" + value, {
	// 			a : a,
	// 			status : "1"
	// 		}).done(function(j) {
	// 			alert(j);
	// 			location.reload();

	// 		});
	// 	}
	// } 
	//  	function setRejectStatus() {
	// 	findselected();

	// 	var a = $("#CheckVal").val();

	// 	if (a == "") {
	// 		alert("Please Select the Data for Reject");
	// 	} else {

	// 		$.post("Approve_regulation_Data?" + key + "=" + value, {
	// 			a : a,
	// 			status : "2"
	// 		}).done(function(j) {
	// 			alert(j);
	// 			location.reload();
	// 			Search();
	// 		});
	// 	}
	// } 

	//csp----------------------------
	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('tunnel_1').onclick = function() {
			// 				if(confirm('Are you sure you want to Proceed?')){getpersdetailsPage();}else{return false;}
			getpersdetailsPage();
		};

		document.getElementById('tunnel_2').onclick = function() {
			// 				if(confirm('Are you sure you want to Proceed?')){getEduPage();}else{return false;}
			getEduPage();
		};
		document.getElementById('tunnel_3').onclick = function() {
			// 				if(confirm('Are you sure you want to Proceed?')){getPreviousPage();}else{return false;}
			getPreviousPage();
		};
		document.getElementById('aIdPrevious').onclick = function() {
			// 				if (confirm('Are you sure you want to Proceed?')) {
			return getPreviousPage();
			// 				} else {
			// 					return false;
			// 				}
		};
		document.getElementById('finalsubmit').onclick = function() {
			// 				if (confirm('Are you sure you want to Submit Data?')) {
			return FinalSubmit();
			// 				} else {
			// 					return false;
			// 				}
		};

	});
</script>












