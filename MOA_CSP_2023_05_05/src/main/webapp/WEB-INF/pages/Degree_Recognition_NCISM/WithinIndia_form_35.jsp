<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<link rel="stylesheet"
	href="assets/vendor/vtab&htab_with_accordion/vtab&htab_with_acco_form_style.css">


<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<section class="dashboard-page degree_recognition">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>Degree Recognition</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Form
									Recognition</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>



		<div class="row">
			<div class="col-12">

				<div class="card-style mb-30">
					<div class="row">
						<div class="col-12">


							<div class="h-tab">

								<ul class="h-tab_tab-head">
									<li class="htab1 active"><a href="Deg_rec_WithinIndia_Url">Application
											For Degree Awarding Universities Within India</a></li>
									<!-- 									<li class="htab2 "><a href="Deg_rec_OutsideIndia_Url">Application -->
									<!-- 											For Degree Awarding Universities Outside India</a></li> -->

								</ul>

								<div class="h-tab_container">

									<div id="htab1" class="h-tab_content">


										<div class="v-tab row m-0">

											<ul class="v-tab_tab-head col-4 col-sm-4 col-md-3 col-lg-2">
												<li class="active" rel="vtab1">Form-35A (UG Courses)</li>
												<li rel="vtab2">Form-35B (PG Courses)</li>
											</ul>

											<div
												class="v-tab_container col-8 col-sm-8 col-md-9 col-lg-10">

												<div id="vtab1" class="v-tab_content">
													<!--Start Form-35A (UG Courses)-->
													<div id="form_a_ug" class="content tabcontent">
														<form id="form_a_ug_details">
															<div class="row">
																<input type="hidden" id="form_a_ug_id"
																	name="form_a_ug_id" value="0"
																	class="form-control autocomplete" autocomplete="off">
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="select-style-1">
																		<label for="text-input">Name Of University<span
																			class="mandatory">*</span></label>
																		<div class="select-position">
																			<select name="university_name" id="university_name"
																				class="form-control form-control-lg form-control-a effect-9 select2 narrow wrap">
																				<option value="0" name="select">--Select
																					University--</option>
																				<!-- 																				<option value="1">Bihar</option> -->
																				<!-- 																				<option value="2">Chhattisgarh</option> -->
																				<!-- 																				<option value="3">Gujarat</option> -->
																				<c:forEach var="item" items="${getUniverCityList}"
																					varStatus="num">
																					<option value="${item.id}" name="${item.id}">${item.university_name}</option>
																				</c:forEach>
																			</select>
																		</div>
																	</div>
																	<!-- end select -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-1">
																		<label for="">Address Of University<span
																			class="mandatory">*</span></label>
																		<textarea id="university_address"
																			name="university_address" maxlength="250"
																			placeholder="Enter Address Of University"></textarea>
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Registered Email-ID<span
																			class="mandatory">*</span></label> <input type="email"
																			id="email" name="email" class="email" maxlength="30"
																			onchange="checkgmail(this.value)"
																			pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
																			value="${email_txt}" autocomplete="off"
																			placeholder="Enter Registered Email-ID (abc@example.com)">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Contact Person Name<span
																			class="mandatory">*</span></label> <input id="contact_name"
																			name="contact_name" maxlength="100" type="text"
																			placeholder="Enter Contact Person Name">
																	</div>
																	<!-- end input -->
																</div>




																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="select-style-1">
																		<label for="text-input">Contact Person
																			Designation<span class="mandatory">*</span>
																		</label>
																		<div class="select-position">
																			<select name=contact_designation
																				id="contact_designation" class="form-control valid"
																				aria-invalid="false">
																				<option value="0" selected="selected">--
																					Select Designation --</option>
																				<c:forEach var="item" items="${getDesignationList}"
																					varStatus="num">
																					<option value="${item.id}" name="${item.id}">${item.designation}</option>
																				</c:forEach>
																			</select>
																		</div>
																	</div>
																	<!-- end select -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Contact Person Mob NO.<span
																			class="mandatory">*</span></label> <input id="contact_mobile"
																			name="contact_mobile" autocomplete="off"
																			maxlength="10" type="text"
																			placeholder="Contact Person Mob NO.">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Contact Person Email-ID<span
																			class="mandatory">*</span></label> <input id="contact_email"
																			name="contact_email" autocomplete="off"
																			maxlength="100" type="text"
																			placeholder="Contact Person Email-ID">
																	</div>
																	<!-- end input -->
																</div>


																<div class="col-12 col-sm-12 col-md-6 col-lg-4">

																	<div class="select-style-1">
																		<label for="text-input">Medical Stream<span
																			class="mandatory">*</span></label>
																		<div class="select-position">
																			<select name="medical_stream" id="medical_stream">
																				<option value="0">--Select--</option>
																				<c:forEach var="item" items="${getSystemByNCISM}"
																					varStatus="num">
																					<option value="${item.id}"
																						name="${item.system_name}">${item.system_name}</option>
																				</c:forEach>
																			</select>
																		</div>
																	</div>
																	<!-- end select -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="select-style-1">
																		<label for="text-input">Name Of Institute<span
																			class="mandatory">*</span></label>
																		<div class="select-position">
																			<select name="institute_name" id="institute_name">
																				<option value="0">--Select--</option>
																				<c:forEach var="item"
																					items="${getInstituteListForNcism}" varStatus="num">
																					<option value="${item.id}"
																						name="${item.institute_name}">${item.institute_name}</option>
																				</c:forEach>
																			</select>
																		</div>
																		<!-- end select -->
																	</div>
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Nomenclature Of Degree<span
																			class="mandatory">*</span></label> <input
																			id="nomenclature_degree" name="nomenclature_degree"
																			autocomplete="off" maxlength="100" type="text"
																			placeholder="Nomenclature Of Degree">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Abbreviation Of Degree(if any)</label>
																		<!-- 										<span class="mandatory">*</span>  -->
																		<input id="abbreviation_degree"
																			name="abbreviation_degree" autocomplete="off"
																			maxlength="100" type="text"
																			placeholder="Abbreviation Of Degree">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Admission of first batch<span
																			class="mandatory">*</span></label> <input type="month"
																			id="first_batch_admission"
																			name="first_batch_admission" autocomplete="off"
																			maxlength="100" placeholder="Nomenclature Of Degree">
																	</div>
																	<!-- end input -->
																</div>

																<!-- 							</div> -->

																<!-- 							<div class="row"> -->
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<!-- 								<div class="col-12 col-lg-12 col-md-12 col-sm-12"> -->
																	<div class="input-style-2">
																		<label for="">Issued Of Provisional
																			Certificate/Awarded Degree<span class="mandatory">*</span>
																		</label>
																	</div>
																	<div class="input-style-form-check">
																		<div class="form-check radio-style">
																			<label class="form-check-label" for="radio-1">
																				<input type="radio" id="awarded_degree1"
																				name="awarded_degree" class="form-check-input"
																				value="Yes" onclick="Awarded_Degree();"> Yes
																			</label>
																		</div>
																		<div class="form-check radio-style">
																			<label class="form-check-label" for="radio-1">
																				<input type="radio" id="awarded_degree2"
																				name="awarded_degree" value="No"
																				class="form-check-input" onclick="Awarded_Degree();">
																				No
																			</label>
																		</div>
																	</div>
																</div>

																<div class="row" id="Awarded_Degree_yes"
																	style="display: none;">
																	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																		<div class="input-style-1">
																			<label for="">Month & Year of passing of
																				final year examination<span class="mandatory">*</span>
																			</label> <input type="month" id="final_year_examination"
																				name="final_year_examination" autocomplete="off"
																				maxlength="100" type="text"
																				placeholder="Enter Month & Year of passing of final year examination">
																		</div>
																		<!-- end input -->
																	</div>

																	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																		<div class="input-style-1">
																			<label for="">Month & Year of Completion Of
																				Internship<span class="mandatory">*</span>
																			</label> <input type="month" id="completion_of_internship"
																				name="completion_of_internship" autocomplete="off"
																				maxlength="100" type="text"
																				placeholder="Enter Month & Year of Completion Of Internship">
																		</div>
																		<!-- end input -->
																	</div>

																	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																		<div class="input-style-1">
																			<label for="">Month & Year of Provisional
																				Certificate/Award<span class="mandatory">*</span>
																			</label> <input id="provisional_certificate"
																				name="provisional_certificate" autocomplete="off"
																				maxlength="100" type="month"
																				placeholder="Enter Month & Year of Provisional Certificate/Award">
																		</div>
																		<!-- end input -->
																	</div>
																</div>
																<div class="row" id="Awarded_Degree_No"
																	style="display: none;">
																	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																		<div class="input-style-1">
																			<label for="">Month & Year of passing of
																				final year examination<span class="mandatory">*</span>
																			</label> <input type="month" id="examinaton_year"
																				name="examinaton_year" autocomplete="off"
																				maxlength="100" type="text"
																				placeholder="Enter Month & Year of passing of final year examination">
																		</div>
																		<!-- end input -->
																	</div>

																	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																		<div class="input-style-2">
																			<label for="">Date of commencement Of
																				Internship<span class="mandatory">*</span>
																			</label>
																			<!-- <input type="text" id="commencement_dt" name="commencement_dt" autocomplete="off" maxlength="100" type="text" placeholder="Enter Date of commencement Of Internship"> -->

																			<input type="text" name="commencement_dt"
																				id="commencement_dt" maxlength="10"
																				class="form-control-sm form-control effect-9"
																				aria-required="true" autocomplete="off"
																				value="DD/MM/YYYY">
																		</div>
																		<!-- end input -->
																	</div>

																	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																		<div class="input-style-1">
																			<label for="">Expected Month & Year of
																				Completion of inernship<span class="mandatory">*</span>
																			</label> <input type="month" id="expected_month_year"
																				name="expected_month_year" autocomplete="off"
																				maxlength="100" type="text"
																				placeholder="Enter Expected Month & Year of Completion of inernship">
																		</div>
																		<!-- end input -->
																	</div>
																</div>


																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<!-- 								<div class="col-12 col-lg-12 col-md-12 col-sm-12"> -->
																	<div class="input-style-2">
																		<label for="">Degree Award To Be Continued ?<span
																			class="mandatory">*</span></label>
																	</div>
																	<div class="input-style-form-check">
																		<div class="form-check radio-style">
																			<label class="form-check-label" for="radio-1">
																				<input type="radio" id="continued_degree1"
																				name="continued_degree" class="form-check-input"
																				value="Yes" onclick="Degree_conti();"> Yes
																			</label>
																		</div>
																		<div class="form-check radio-style">
																			<label class="form-check-label" for="radio-1">
																				<input type="radio" id="continued_degree2"
																				name="continued_degree" value="No"
																				class="form-check-input" onclick="Degree_conti();">
																				No
																			</label>
																		</div>
																	</div>
																</div>

																<div class="row" id="Degree_conti_No"
																	style="display: none;">
																	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																		<div class="input-style-1">
																			<label for="">Month & Year of award of degree
																				to the last student of last batch<span
																				class="mandatory">*</span>
																			</label> <input type="month" id="last_batch_year"
																				name="last_batch_year" autocomplete="off"
																				maxlength="100" type="text"
																				placeholder="Enter Month & Year award of degree to the last student of last batch">
																		</div>
																		<!-- end input -->
																	</div>
																</div>
																<div>
																	<div class="row">
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<!-- 																			<h5 class="mb-15"></h5> -->
																			<h5 class="mb-2">
																				<input type="checkbox" id="checkbox1"
																					name="checkbox1"> <label for="text-input"
																					class="co d-inline"></label>Declaration/Certification:
																			</h5>
																			<p>It is certified that the details above
																				furnished/enclosed are true to the best of my
																				knowledge. I ensure that this university /awarding
																				body is following, in to the rules and the
																				regulations prescribed by Govt. of
																				India/NCISM/erstwhile CCIM time and implementing the
																				same in the ASUS colleges affiliated to this
																				University/awarding body.</p>
																		</div>
																	</div>

																	<div class="row float-end">
																		<div class="col-12 col-sm-12 col-md-12 col-lg-12">
																			<div class="upload_sign"></div>
																			<h5 class="mb-15">
																				SIGNATURE OF REGISTAR OF THE <br /> UNIVERSITY WITH
																				SEAL & DATE
																			</h5>
																		</div>

																	</div>
																</div>
																<ul class="buttons-group mainbtn">
																	<li><a href="view_detailfor_35A_Url"
																		id="btn-reload"
																		class="main-btn secondary-btn btn-hover btn-iconic-icon"
																		type="button"><i class="lni lni-search-alt"></i>Search
																			Details</a></li>
																	<li><input type="button" id="btn_savedraftug"
																		class="main-btn info-btn btn-hover"
																		value="Save as Draft" onclick="return form_35_a_ug();"></li>
																	<li><input type="button" id="btn_save"
																		class="main-btn success-btn  btn-hover"
																		value="Submit for approval"
																		onclick="Submit_Approval_UG();"></li>
																	<li><a href="Deg_rec_WithinIndia_Url"
																		class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
																</ul>
																<!-- End Form-35A (UG Courses)-->
															</div>
														</form>
													</div>
												</div>
												<!-- #tab1 -->
												<!--  FORM-35B (PG COURSES)-->

												<div id="vtab2" class="v-tab_content">
													<div id="form_b_pg" class="content tabcontent">
														<form id="form_b_pg_details">

															<div class="row">
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<input type="hidden" id="form_b_pg_id"
																		name="form_b_pg_id" value="0"
																		class="form-control autocomplete" autocomplete="off">
																	<div class="select-style-1">
																		<label for="text-input">Name Of Affiliating
																			University<span class="mandatory">*</span>
																		</label>
																		<div class="input-style-2">
																			<input type="text" name="aff_university_name"
																				id="aff_university_name"
																				placeholder="Name of Affiliating University">
																		</div>
																	</div>
																	<!-- end select -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-1">
																		<label for="">Address Of University<span
																			class="mandatory">*</span></label>
																		<textarea id="aff_university_address"
																			name="aff_university_address" maxlength="250"
																			placeholder="Address Of University"></textarea>
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Registered Email-ID<span
																			class="mandatory">*</span></label> <input id="reg_email"
																			name="reg_email" maxlength="100" type="text"
																			placeholder="Registered Email-ID">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Contact Person Name<span
																			class="mandatory">*</span></label> <input id="cont_name"
																			name="cont_name" maxlength="100" type="text"
																			placeholder="Contact Person Name">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">

																	<div class="input-style-2">
																		<label for="text-input">Contact Person
																			Designation<span class="mandatory">*</span>
																		</label>
																		<div class="input-style-2">
																			<input type="text" name="cont_designation"
																				id="cont_designation"
																				placeholder="Contact Person Designation">

																		</div>
																	</div>
																	<!-- end select -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Contact Person Mob No.<span
																			class="mandatory">*</span></label> <input id="cont_mobile_no"
																			name="cont_mobile_no" autocomplete="off"
																			maxlength="100" type="text"
																			placeholder="Contact Person Mob No.">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Contact Person Email-ID<span
																			class="mandatory">*</span></label> <input id="cont_email_id"
																			name="cont_email_id" autocomplete="off"
																			maxlength="100" type="text"
																			placeholder="Contact Person Email-ID">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="select-style-1">
																		<label for="text-input">Medical Stream<span
																			class="mandatory">*</span></label>
																		<div class="select-position">
																			<select name="medical_system" id="medical_system">
																				<option value="0">--Select--</option>
																				<c:forEach var="item" items="${getSystemList}"
																					varStatus="num">
																					<option value="${item.id}"
																						name="${item.system_name}">${item.system_name}</option>
																				</c:forEach>
																			</select>
																		</div>
																	</div>
																	<!-- end select -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="select-style-1">
																		<label for="text-input">Name Of Institute<span
																			class="mandatory">*</span></label>
																		<div class="select-position">
																			<select name="insti_name" id="insti_name">
																				<option value="0">--Select--</option>
																				<c:forEach var="item"
																					items="${getInstituteListForNcism}" varStatus="num">
																					<option value="${item.id}"
																						name="${item.institute_name}">${item.institute_name}</option>
																				</c:forEach>
																			</select>
																		</div>
																		<!-- end select -->
																	</div>
																</div>


																<ul class="buttons-group mainbtn">
																	<li><a href=view_detailfor_35B_Url id="btn-reload"
																		class="main-btn secondary-btn btn-hover btn-iconic-icon"
																		type="button"><i class="lni lni-search-alt"></i>Search
																			Details</a></li>
																	<li><input type="button" id="btn_save"
																		class="main-btn info-btn btn-hover"
																		value="Save as Draft" onclick="form_35_b_pg();">
																	<li><input type="button" id="btn_save"
																		class="main-btn success-btn  btn-hover"
																		value="Submit for approval"
																		onclick="Submit_Approval_PG_B();"></li>
																	<li><a href="#"
																		class="main-btn dark-btn  btn-hover btn-iconic-icon btn-iconic-left"
																		onclick="form_pg_course();">Next <i
																			class="lni lni-chevron-right"></i></a></li>
																</ul>
															</div>
														</form>
													</div>

													<!--START (PG COURSES) FOR RECOGNITION 35 B -->

													<div id="form_pg_course" class="content tabcontent"
														style="display: none;">
														<form id="form_c_pg_details">
															<div class="row">
																<input type="hidden" id="form_pg_course_id"
																	name="form_pg_course_id" value="0"
																	class="form-control autocomplete" autocomplete="off">
																<h3 class="b-top mt-3 pt-2">Details Of Medical
																	Qualifications (PG courses) to be recognized:</h3>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">PG Subject<span
																			class="mandatory">*</span></label> <input type="text"
																			name="pg_sub" id="pg_sub" class="form-control"
																			placeholder="Enter PG Subject.">
																	</div>
																	<!-- end input -->
																</div>
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Nomenclature Of Degree<span
																			class="mandatory">*</span></label> <input id="nom_of_degree"
																			name="nom_of_degree" maxlength="100" type="text"
																			placeholder="Enter Nomenclature Of Degree">
																	</div>
																	<!-- end input -->
																</div>
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Abbreviation Of Degree<span
																			class="mandatory">*</span></label> <input id="abbr_of_degree"
																			name="abbr_of_degree" maxlength="100" type="text"
																			placeholder="Enter Abbreviation Of Degree">
																	</div>
																	<!-- end input -->
																</div>
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Month & Year Of Admission Of
																			First Batch<span class="mandatory">*</span>
																		</label> <input id="year_of_addmission"
																			name="year_of_addmission" maxlength="100"
																			type="month"
																			placeholder="Enter Month & Year Of Admission Of First Batch">
																	</div>
																	<!-- end input -->
																</div>
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Month & Year Of Passing Of First
																			Batch<span class="mandatory">*</span>
																		</label>
																		<!-- Final Year Examination Of -->
																		<input id="month_of_passing" name="month_of_passing"
																			maxlength="100" type="month"
																			placeholder="Enter Month & Year Of Passing Of First Batch">
																	</div>
																	<!-- end input -->
																</div>
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Month & Year Of Issue Of
																			Provisional Certificate<span class="mandatory">*</span>
																		</label> <input id="issue_of_provisional"
																			name="issue_of_provisional" maxlength="100"
																			type="month"
																			placeholder="Enter Month & Year Of Issue Of Provisional Certificate">
																	</div>
																	<!-- end input -->
																</div>
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Date Of Letter Of Permission<span
																			class="mandatory">*</span></label> <input type="text"
																			name="date_of_latter" id="date_of_latter"
																			maxlength="10"
																			onclick="clickclear(this, 'DD/MM/YYYY')"
																			class="form-control-sm form-control effect-9 "
																			onfocus="this.style.color='#000000'"
																			onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
																			onkeyup="clickclear(this, 'DD/MM/YYYY')"
																			onchange="clickrecall(this,'DD/MM/YYYY');"
																			aria-required="true" autocomplete="off"
																			value="DD/MM/YYYY">
																	</div>
																	<!-- end input -->
																</div>
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Ref. Of Letter Of Permission<span
																			class="mandatory">*</span></label> <input id="ref_of_latter"
																			name="ref_of_latter" maxlength="100" type="file"
																			accept=".PDF"
																			placeholder="Upload Ref. Of Letter Of Permission">
																	</div>
																	<!-- end input -->
																</div>

																<ul class="buttons-group mainbtn">
																	<li><a href="#"
																		class="main-btn dark-btn  btn-hover btn-iconic-icon"
																		onclick="form_pg_pre();"><i
																			class="lni lni-chevron-left"></i>previous</a></li>
																	<li><a href=view_detailfor_35C_Url id="btn-reload"
																		class="main-btn secondary-btn btn-hover btn-iconic-icon"
																		type="button"><i class="lni lni-search-alt"></i>Search
																			Details</a></li>
																	<li><input type="button" id="btn_save"
																		class="main-btn info-btn btn-hover"
																		value="Save as Draft" onclick="form_pg_medical();">
																	<li><input type="button" id="btn_save"
																		class="main-btn success-btn  btn-hover"
																		value="Submit for approval"
																		onclick="Submit_Approval_PG_C();"></li>
																	<li><a href="#"
																		class="main-btn dark-btn  btn-hover btn-iconic-icon btn-iconic-left"
																		onclick="form_pg_diploma_course();">Next <i
																			class="lni lni-chevron-right"></i></a></li>

																</ul>
															</div>

														</form>
													</div>
													<!--END (PG COURSES)  FOR RECOGNITION 35 B -->

													<!--START (PG-DIPLOMA COURSES)  35 B -->
													<div id="form_pg_diploma" class="content tabcontent"
														style="display: none;">
														<form id="form_pg_diploma_details">
															<div class="row">
																<input type="hidden" id="form_pg_diploma_id"
																	name="form_pg_diploma_id" value="0"
																	class="form-control autocomplete" autocomplete="off">
																<h3 class="b-top mt-3 pt-2">Details Of Medical
																	Qualifications (PG Diploma courses) to be recognized:</h3>
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>PG Subject:<span class="mandatory">*</span></label>
																		<input type="text" id="pg_dip_subject"
																			name="pg_dip_subject"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="PG Subject" />

																	</div>

																</div>
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>Nomenclature of Degree:<span
																			class="mandatory">*</span></label> <input type="text"
																			id="nomenclature_dg_dip" name="nomenclature_dg_dip"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="Nomenclature of Degree" />

																	</div>

																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>Abbreviation of Degree:<span
																			class="mandatory">*</span></label> <input type="text"
																			id="abbreviation_dg_dip" name="abbreviation_dg_dip"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="Abbreviation of Degree" />

																	</div>

																</div>
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>Month & Year Of Admission Of First
																			Batch:<span class="mandatory">*</span>
																		</label> <input type="month" id="year_admi_first_batch_dip"
																			name="year_admi_first_batch_dip"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="Month & Year Of Admission Of
																			First Batch" />

																	</div>

																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Month & Year Of Passing Of Final
																			Year Examination Of First Batch<span
																			class="mandatory">*</span>
																		</label> <input id="passing_year_of_finalyear_dip"
																			name="passing_year_of_finalyear_dip" maxlength="100"
																			type="month"
																			placeholder="Month & Year Of Passing Of
																			Final Year Examination Of First Batch">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Month & Year Of Issue Of
																			Provisional Certificate/Award Of Degree<span
																			class="mandatory">*</span>
																		</label> <input id="year_provisional_certi_dip"
																			name="year_provisional_certi_dip" maxlength="100"
																			type="month"
																			placeholder="Month & Year Of Issue Of
																			Provisional Certificate/Award Of Degree">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Date<span class="mandatory">*</span></label>

																		<input type="text" name="permission_lattter_dt"
																			id="permission_lattter_dt" maxlength="10"
																			class="form-control-sm form-control effect-9"
																			aria-required="true" autocomplete="off"
																			value="DD/MM/YYYY">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Ref. Of Letter Of Permission.<span
																			class="mandatory">*</span></label> <input
																			id="ref_permission_latter"
																			name="ref_permission_latter" autocomplete="off"
																			maxlength="100" type="file">
																	</div>
																	<!-- end input -->
																</div>

															</div>
															<div class="row">
																<div class="col-12">


																	<ul class="buttons-group mainbtn">
																		<li><a href="#"
																			class="main-btn dark-btn  btn-hover btn-iconic-icon"
																			onclick="form_pg_pre();"><i
																				class="lni lni-chevron-left"></i>previous</a></li>
																		<li><a href=view_detailfor_35D_Url
																			id="btn-reload"
																			class="main-btn secondary-btn btn-hover btn-iconic-icon"
																			type="button"><i class="lni lni-search-alt"></i>Search
																				Details</a></li>
																		<li><input type="button" id="btn_save"
																			class="main-btn info-btn btn-hover"
																			value="Save as Draft"
																			onclick="form_pg_diploma_medical();"></li>
																		<li><input type="button" id="btn_save"
																			class="main-btn success-btn  btn-hover"
																			value="Submit for approval"
																			onclick="Submit_Approval_PG_D();"></li>



																	</ul>
																	<!-- 				end card -->
																</div>
																<!-- 		end col -->
															</div>
														</form>
													</div>
													<!--END (PG-DIPLOMA COURSES)  35 B -->
												</div>
												<!-- #tab2 -->
											</div>
										</div>
									</div>
								</div>
								<!-- #tab1 -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<script
	src="assets/vendor/vtab&htab_with_accordion/vtab&htab_with_acco_form.js">
	
</script>
<script>
	$(document).ready(function() {

		if (window.location.href.includes("msg")) {
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		}
		//        alert('${date_of_latter}')
		datepicketDate('file_date');
		datepicketDate('velidity_period');
		datepicketDate('commencement_dt');
		datepicketDate('date_of_latter');
		datepicketDate('permission_lattter_dt');
		//         datepicketDate('date_of_completion_internship');
		//         datepicketDate('to_date1');
		// datepicketDate('from_date1');

		//          if('${pddata}' != "[]"){
		//      	    $("#university_name").val('${pddata[0][0]}');
		//      		$("#university_address").val('${pddata[0][1]}');
		//      		$("#email").val('${pddata[0][2]}');
		//      		$("#contact_name").val('${pddata[0][3]}');
		//      		$("#contact_designation").val('${pddata[0][4]}');
		//      		$("#contact_email").val('${pddata[0][5]}');
		//      		$("#contact_mobile").val('${pddata[0][6]}');
		//      		$("#medical_stream").val('${pddata[0][7]}');
		//      		$("#institute_name").val('${pddata[0][8]}');
		//      		$("#nomenclature_degree").val('${pddata[0][9]}');
		//      		$("#abbreviation_degree").val('${pddata[0][10]}');
		//      		$("#first_batch_admission").val('${pddata[0][11]}');
		//      		$("#awarded_degree").val('${pddata[0][12]}');
		//      		$("#final_year_examination").val('${pddata[0][13]}');
		//      		$("#completion_of_internship").val('${pddata[0][14]}');
		//      		$("#provisional_certificate").val('${pddata[0][15]}');
		//      		$("#examinaton_year").val('${pddata[0][16]}');
		//      		//$("#commencement_dt").val('${pddata[0][17]}');

		//      		var commencement_dt = '${pddata[0][17]}';
		//             var dob= commencement_dt.substring(0,10);
		//             var dob_y = dob.substring(0,4);
		//             var dob_m = dob.substring(5,7);
		//             var dob_d = dob.substring(8,10);
		//             var dob_t = dob_d+"/"+dob_m+"/"+dob_y;
		//             $("#commencement_dt").val(dob_t);

		//      		$("#expected_month_year").val('${pddata[0][18]}');
		//      		$("#continued_degree").val('${pddata[0][19]}');
		//      		$("#last_batch_year").val('${pddata[0][20]}');
		//          }

		//          if('${pgdata}' != "[]"){
		//       	    $("#aff_university_name").val('${pgdata[0][0]}');
		//       		$("#aff_university_address").val('${pgdata[0][1]}');
		//       		$("#reg_email").val('${pgdata[0][2]}');
		//       		$("#cont_name").val('${pgdata[0][3]}');
		//       		$("#cont_designation").val('${pgdata[0][4]}');
		//       		$("#cont_mobile_no").val('${pgdata[0][5]}');
		//       		$("#cont_email_id").val('${pgdata[0][6]}');
		//       		$("#medical_system").val('${pgdata[0][7]}');
		//       		$("#insti_name").val('${pgdata[0][8]}');
		//           }

		//          if('${mpgdata}' != "[]"){
		//        	    $("#pg_sub").val('${mpgdata[0][0]}');
		//        		$("#nom_of_degree").val('${mpgdata[0][1]}');
		//        		$("#abbr_of_degree").val('${mpgdata[0][2]}');
		//        		$("#year_of_addmission").val('${mpgdata[0][3]}');
		//        		$("#month_of_passing").val('${mpgdata[0][4]}');
		//        		$("#issue_of_provisional").val('${mpgdata[0][5]}');

		//        		var date_of_latter = '${mpgdata[0][6]}';
		//             var dob= commencement_dt.substring(0,10);
		//             var dob_y = dob.substring(0,4);
		//             var dob_m = dob.substring(5,7);
		//             var dob_d = dob.substring(8,10);
		//             var dob_t = dob_d+"/"+dob_m+"/"+dob_y;
		//             $("#date_of_latter").val(dob_t);
		//             $("#ref_of_latter").val('${mpgdata[0][7]}');
		//            }

		//            if('${mpgddata}' != "[]"){
		//        	    $("#pg_dip_subject").val('${mpgddata[0][0]}');
		//        		$("#nomenclature_dg_dip").val('${mpgddata[0][1]}');
		//        		$("#abbreviation_dg_dip").val('${mpgddata[0][2]}');
		//        		$("#year_admi_first_batch_dip").val('${mpgddata[0][3]}');
		//        		$("#passing_year_of_finalyear_dip").val('${mpgddata[0][4]}');
		//        		$("#year_provisional_certi_dip").val('${mpgddata[0][5]}');

		//        	   var permission_lattter_dt = '${mpgddata[0][6]}';
		//             var dob= commencement_dt.substring(0,10);
		//             var dob_y = dob.substring(0,4);
		//             var dob_m = dob.substring(5,7);
		//             var dob_d = dob.substring(8,10);
		//             var dob_t = dob_d+"/"+dob_m+"/"+dob_y;
		//             $("#permission_lattter_dt").val(dob_t);

		//             $("#ref_permission_latter").val('${mpgddata[0][7]}');
		//            }

	});

	function Awarded_Degree() {
		var awarded_degree = $('input:radio[name=awarded_degree]:checked')
				.val();
		if (awarded_degree == "Yes") {
			$("div#Awarded_Degree_yes").show();
			$("div#Awarded_Degree_No").hide();
		} else if (awarded_degree == "No") {
			$("div#Awarded_Degree_No").show();
			$("div#Awarded_Degree_yes").hide();
		}
	}

	function Degree_conti() {
		var continued_degree = $('input:radio[name=continued_degree]:checked')
				.val();
		if (continued_degree == "Yes") {
			$("div#Degree_conti_No").hide();
		}
		if (continued_degree == "No") {
			$("div#Degree_conti_No").show();
		}
	}

	function form_pg_course() {
		$("div#form_b_pg").hide();
		$("div#form_pg_course").show();
	}

	function form_pg_diploma_course() {
		$("div#form_pg_diploma").show();
		$("div#form_b_pg").hide();
		$("div#form_pg_course").hide();

	}

	function form_pg_dip_pre() {
		$("div#form_pg_diploma").hide();
		$("div#form_b_pg").hide();
		$("div#form_pg_course").show();
	}

	function form_pg_pre() {
		$("div#form_pg_diploma").hide();
		$("div#form_b_pg").show();
		$("div#form_pg_course").hide();
	}

	function checkgmail(email1) {

		document.getElementById("cont_email_id").innerHTML = "";
		if (/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {

		} else {
			alert("Please Enter Valid Email Address");
			$("input#cont_email_id").focus();
			$("input#cont_email_id").val('');
			return false;
		}
	}
	function checkgmail(email) {

		document.getElementById("email").innerHTML = "";
		if (/@gmail.com\s*$/.test(email) || /@yahoo.com\s*$/.test(email)) {

		} else {

			alert("Please Enter Valid Email ID.");
			$("input#email").focus();
			$("input#email").val('');
			return false;
		}
	}

	function checkgmail(email) {

		document.getElementById("contact_email").innerHTML = "";
		if (/@gmail.com\s*$/.test(email) || /@yahoo.com\s*$/.test(email)) {

		} else {

			alert("Please Enter Valid Email ID.");
			$("input#contact_email").focus();
			$("input#contact_email").val('');
			return false;
		}
	}

	function checkgmail(email) {

		document.getElementById("reg_email").innerHTML = "";
		if (/@gmail.com\s*$/.test(email) || /@yahoo.com\s*$/.test(email)) {

		} else {

			alert("Please Enter Valid Email ID.");
			$("input#reg_email").focus();
			$("input#reg_email").val('');
			return false;
		}
	}

	function mobileNumber(obj) {
		if (obj.value.length < 10) {
			alert('Please Enter valid Number');
			$('#' + obj.id).focus();
			$('#' + obj.id).val("");
			return false;
		}
		_mobile = obj.value;

		var regExp = /^[6789]\d{9}$/;
		if (_mobile == '' || !regExp.test(_mobile)) {
			alert('Please Enter Number Start with 6789 Digit');
			$('#' + obj.id).focus();
			$('#' + obj.id).val("");
			return false;
		}
	}

	/*-------------------------START SAVE FOR FORM_A_UG_DETAILS----------------------------- */

	function form_35_a_ug() {

		var formvalues = $("#form_a_ug_details").serialize();
		var form_a_ug_id = $('#form_a_ug_id').val();
		$.post('form35_a_ug_action?' + key + "=" + value, formvalues,
				function(data) {
					if (data.error == null) {
						if (data.form_a_ug_id != null) {
							$('#form_a_ug_id').val(data.form_a_ug_id);
							alert(data.saved);
						} else {
							alert(data.updated);
						}
					} else {
						alert(data.error)
					}
				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}

	/* --------------------- FINAL SUBMIT UG -------------------------- */

	function Submit_Approval_UG() {

		// 			if ($("#university_name").val() == "0") {
		// 				alert("Please Enter Name Of University.");
		// 				$("#university_name").focus();
		// 				return false;
		// 			}
		// 		       else if ($("textarea#university_address").val().trim() == "") {
		// 				alert("Please Enter Address Of University");
		// 				$("textarea#university_address").focus();
		// 				return false;
		// 			} 
		// // 		else if ($("input#uni_name").val().trim() == "") {
		// // 				alert("Please Enter Affiliating University Name");
		// // 				$("input#uni_name").focus();
		// // 				return false;
		// // 			} 
		// 		//else if ($("select#country_id").val().trim() == "0") {
		// // 				alert("Please Enter Country");
		// // 				$("select#country_id").focus();
		// // 				return false;
		// // 			}else if ($("input#state").val().trim() == "") {
		// // 				alert("Please Enter State");
		// // 				$("input#state").focus();
		// // 				return false;
		// // 				} 
		// // 			else if ($("input#district").val().trim() == "") {
		// // 				alert("Please Enter District.");
		// // 				$("input#district").focus();
		// // 				return false;
		// // 			} else if ($("input#city").val().trim() == "") {
		// // 				alert("Please Enter City");
		// // 				$("input#city").focus();
		// // 				return false;
		// // 			}
		// // 			else if ($("input#uni_address").val().trim() == "") {
		// // 				alert("Please Enter Address Of University");
		// // 				$("input#uni_address").focus();
		// // 				return false;
		// // 				} 
		// 			else if ($("input#email").val().trim() == "") {
		// 				alert("Please Enter Registered Email-ID");
		// 				$("input#email").focus();
		// 				return false;
		// 				} 
		// 			else if ($("input#contact_name").val().trim() == "") {
		// 				alert("Please Enter Contact Person Name");
		// 				$("input#contact_name").focus();
		// 				return false;
		// 				} 
		// 			 else if ($("select#contact_designation").val() == "0") {
		// 					alert("Please Select Contact Person Designation");
		// 					$("select#contact_designation").focus();
		// 					return false;
		// 			}
		// 			else if ($("input#contact_mobile").val().trim() == "") {
		// 					alert("Please Enter Contact Person Mobile No.");
		// 					$("input#contact_mobile").focus();
		// 					return false;
		// 		 	}
		// 			else if ($("input#contact_email").val().trim() == "") {
		// 				alert("Please Enter Contact Person Email-ID");
		// 				$("input#contact_email").focus();
		// 				return false;
		// 				}
		// 			else if ($("select#medical_stream").val().trim() == "0") {
		// 					alert("Please Select Madical Stream");
		// 					$("select#medical_stream").focus();
		// 					return false;
		// 			}
		// 			else if ($("input#institute_name").val().trim() == "") {
		// 				alert("Please Enter Institute Name");
		// 				$("input#institute_name").focus();
		// 				return false;
		// 				}
		// 			else if ($("input#nomenclature_degree").val().trim() == "") {
		// 				alert("Please Enter Nomenclature  Of Degree");
		// 				$("input#nomenclature_degree").focus();
		// 				return false;
		// 				}
		// // 			else if ($("input#abbreviation_degree").val().trim() == "") { if any 
		// // 				alert("Please Enter Abbreviation Of Degree");
		// // 				$("input#abbreviation_degree").focus();
		// // 				return false;
		// // 					}
		// 			else if ($("input#first_batch_admission").val().trim() == "") {
		// 				alert("Please Enter Year Of Admission Of First Batch");
		// 				$("input#first_batch_admission").focus();
		// 				return false;
		// 				}

		// 			else if ($('input[name=awarded_degree]:checked').length == 0) {
		// 				alert("Please select Awarded Degree");
		// 				$("input#awarded_degree").focus();
		// 			}
		// 						 if{ 
		// 							 if($('input:radio[name=awarded_degree]:checked').val() == "yes"){
		// 								if($("input#final_year_examination").val().trim() == ""){
		// 									 alert("Please Enter Final Year Of Award Of Degree");
		// 								     $("input#final_year_examination").focus();
		// 								     return false;	
		// 									}
		// 								if($("input#completion_of_internship").val().trim() == ""){
		// 									 alert("Please Enter Completion of Internship");
		// 								     $("input#completion_of_internship").focus();
		// 								     return false;	
		// 									}
		// 								 if($("input#provisional_certificate").val().trim() == ""){
		// 									 alert("Please Enter Provisional Certificate");
		// 								     $("input#provisional_certificate").focus();
		// 								     return false;	
		// 									}
		// 							  }
		// 							 else if($('input:radio[name=awarded_degree]:checked').val() == "no"){
		// 									if($("input#examinaton_year").val().trim() == ""){
		// 										 alert("Please Enter Examinaton Year Of Award Of Degree");
		// 									     $("input#examinaton_year").focus();
		// 									     return false;	
		// 										}
		// 									if($("input#commencement_dt").val().trim() == "DD/MM/yyyy"){
		// 										 alert("Please Enter Completion of Internship");
		// 									     $("input#commencement_dt").focus();
		// 									     return false;	
		// 										}
		// 									 if($("input#provisional_certificate").val().trim() == ""){
		// 										 alert("Please Enter Provisional Certificate");
		// 									     $("input#provisional_certificate").focus();
		// 									     return false;	
		// 										}
		// 								  }

		// 						  }

		// 			else if ($("input#m_fir_bat").val().trim() == "") {
		// 				alert("Please Enter Month Of Admission Of First Batch");
		// 				$("input#m_fir_bat").focus();
		// 				return false;
		// 				}
		// 			else if ($("input#y_fir_bat_std_award").val().trim() == "") {
		// 				alert("Please Enter Year Of Awarded Degree To First Student Of First Batch");
		// 				$("input#y_fir_bat_std_award").focus();
		// 				return false;
		// 				}
		// 			else if ($("input#m_fir_bat_std_award").val().trim() == "") {
		// 				alert("Please Enter MonthOf Awarded DegreeToFirst Student Of FirstBatch");
		// 				$("input#m_fir_bat_std_award").focus();
		// 				return false;
		// 				}

		// 				else if ($('input[name=condition]:checked').length == 0) {
		// 					alert("Please select condition");
		// 					$("input#condition").focus();
		// 				}
		// 							 if{ 
		// 								 if($('input:radio[name=condition]:checked').val() == "No"){
		// 									if($("input#y_last_bat_std_award").val().trim() == ""){
		// 										 alert("Please Enter Year Of Award Of Degree To The Last Student Of Last Batch");
		// 									     $("input#y_last_bat_std_award").focus();
		// 									     return false;	
		// 										}
		// 									if($("input#m_last_bat_std_award").val().trim() == ""){
		// 										 alert("Please Enter Month Of Award Of Degree To The Last Student Of Last Batch");
		// 									     $("input#m_last_bat_std_award").focus();
		// 									     return false;	
		// 										}
		// 								  }
		// 		}

		if ($('#form_a_ug_id').val() == 0) {
			alert("Please Saved your detail first");
			return false;
		}

		var checkBox = document.getElementById("checkbox1");
		if (checkBox.checked == false) {

			alert("Please Accept Terms And Condition");
			return false;
		}

		$.post("Submit_Approval_35Data_institute?" + key + "=" + value,
				function(data) {
					if (data.error == null) {
						if (data.saved != null) {
							$('#institute_status').val(data.institute_status);
							// 					 alert(data.saved);
							alert("Data subbmited successfully");
							location.reload();
						}
					} else {
						alert(data.error)
					}
				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});

	}

	/*-------------------------START SAVE FOR FORM_B_PG_DETAILS----------------------------- */

	function form_35_b_pg() {
		var formvalues = $("#form_b_pg_details").serialize();
		var form_b_pg_id = $('#form_b_pg_id').val();
		$.post('form35_b_pg_action?' + key + "=" + value, formvalues,
				function(data) {
					if (data.error == null) {
						if (data.form_b_pg_id != null) {

							$('#form_b_pg_id').val(data.form_b_pg_id);

							alert(data.saved);
						} else {
							alert(data.updated);
						}
					} else {
						alert(data.error)
					}
				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
	/*-------------------------SUBMIT APPROVAL FOR FORM_B_PG_DETAILS----------------------------- */

	function Submit_Approval_PG_B() {

		if ($("input#aff_university_name").val().trim() == "") {
			alert("Please Enter University Name");
			$("input#aff_university_name").focus();
			return false;
		} else if ($("textarea#aff_university_address").val().trim() == "") {
			alert("Please Enter Address Of University");
			$("textarea#aff_university_address").focus();
			return false;
		} else if ($("input#reg_email").val().trim() == "") {
			alert("Please Enter Email");
			$("input#reg_email").focus();
			return false;
		} else if ($("input#cont_name").val().trim() == "") {
			alert("Please Enter Contact Person Name");
			$("input#cont_name").focus();
			return false;
		} else if ($("input#cont_designation").val().trim() == "") {
			alert("Please Enter Contact Person Designation");
			$("input#cont_designation").focus();
			return false;
		}

		else if ($("input#cont_mobile_no").val().trim() == "") {
			alert("Please Enter Contact Person Mobile No.");
			$("input#cont_mobile_no").focus();
			return false;
		} else if ($("input#cont_email_id").val().trim() == "") {
			alert("Please Enter Contact Person Email Id");
			$("input#cont_email_id").focus();
			return false;
		} else if ($("select#medical_system").val() == "0") {
			alert("Please Select Medical Stream");
			$("select#medical_system").focus();
			return false;
		}

		//  	else if($("input#nomenclature_degree").val().trim() == "") {
		//  	    alert("Please Enter Nomenclature Of Degree");
		//  	    $("input#nomenclature_degree").focus();
		// 			return false;
		// 		}

		if ($('#form_b_pg_id').val() == 0) {
			alert("Please Saved your detail first");
			return false;
		}
		$.post("Submit_Approval_35_From_B?" + key + "=" + value,
				function(data) {
					if (data.error == null) {
						if (data.saved != null) {
							$('#institute_status').val(data.institute_status);
							// 						 alert(data.saved);
							alert("Data subbmited successfully");
							location.reload();
						}
					} else {
						alert(data.error)
					}
				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}

	/* --------------------- save for form_pg_medical -------------------------- */
	function form_pg_medical() {

		var formvalues = $("#form_c_pg_details").serialize();
		var form_pg_course_id = $('#form_pg_course_id').val();
		$
				.post(
						'form_pg_medical_action?' + key + "=" + value,
						formvalues,
						function(data) {
							if (data.error == null) {
								if (data.form_pg_course_id != null) {
									$('#form_pg_course_id').val(
											data.form_pg_course_id);
									alert(data.saved);
								} else {
									alert(data.updated);
								}
							} else {
								alert(data.error)
							}
						}).fail(function(xhr, textStatus, errorThrown) {
					alert("fail to fetch")
				});
	}

	function Submit_Approval_PG_C() {

		// 	 if($("input#aff_university_name").val().trim() == "") {
		//  	    alert("Please Enter University Name");
		//  	    $("input#aff_university_name").focus();
		// 			return false;
		// 		}
		// 	else if($("textarea#aff_university_address").val().trim() == "") {
		// 	    alert("Please Enter Address Of University");
		// 	    $("textarea#aff_university_address").focus();
		// 		return false;
		// 	}
		// 	else if($("input#reg_email").val().trim() == "") {
		// 	    alert("Please Enter Email");
		// 	    $("input#reg_email").focus();
		// 		return false;
		// 	}
		// 	else if($("input#cont_name").val().trim() == "") {
		// 	    alert("Please Enter Contact Person Name");
		// 	    $("input#cont_name").focus();
		// 		return false;
		// 	}
		// 	else if($("input#cont_designation").val().trim() == "") {
		// 	    alert("Please Enter Contact Person Designation");
		// 	    $("input#cont_designation").focus();
		// 		return false;
		// 	}

		// 	else if($("input#cont_mobile_no").val().trim() == "") {
		// 	    alert("Please Enter Contact Person Mobile No.");
		// 	    $("input#cont_mobile_no").focus();
		// 		return false;
		// 	}
		// 	else if($("input#cont_email_id").val().trim() == "") {
		// 	    alert("Please Enter Contact Person Email Id");
		// 	    $("input#cont_email_id").focus();
		// 		return false;
		// 	}
		// 	else if($("select#medical_stream").val() == "0") {
		// 	    alert("Please Select Medical Stream");
		// 	    $("select#medical_stream").focus();
		// 		return false;
		// 	}

		// 	else if($("input#nomenclature_degree").val().trim() == "") {
		// 	    alert("Please Enter Nomenclature Of Degree");
		// 	    $("input#nomenclature_degree").focus();
		// 		return false;
		// 	}

		if ($('#form_pg_course_id').val() == 0) {
			alert("Please Saved your detail first");
			return false;
		}
		$.post("Submit_Approval_35_From_C?" + key + "=" + value,
				function(data) {
					if (data.error == null) {
						if (data.saved != null) {
							$('#institute_status').val(data.institute_status);
							//						 alert(data.saved);
							alert("Data subbmited successfully");
							location.reload();
						}
					} else {
						alert(data.error)
					}
				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}

	/* --------------------- save for form_pg_diploma_medical -------------------------- */
	function form_pg_diploma_medical() {

		var formvalues = $("#form_pg_diploma_details").serialize();
		var form_pg_diploma_id = $('#form_pg_diploma_id').val();
		$.post(
				'form_pg_diploma_medical_action?' + key + "=" + value,
				formvalues,
				function(data) {
					if (data.saved == null) {
						if (data.form_pg_diploma_id != null) {
							$('#form_pg_diploma_id').val(
									data.form_pg_diploma_id);
							alert(data.saved);
						} else {
							alert(data.updated);
						}
					} else {
						alert(data.error)
					}
				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}

	function Submit_Approval_PG_D() {

		// 	 if($("input#aff_university_name").val().trim() == "") {
		//  	    alert("Please Enter University Name");
		//  	    $("input#aff_university_name").focus();
		// 			return false;
		// 		}
		// 	else if($("textarea#aff_university_address").val().trim() == "") {
		// 	    alert("Please Enter Address Of University");
		// 	    $("textarea#aff_university_address").focus();
		// 		return false;
		// 	}
		// 	else if($("input#reg_email").val().trim() == "") {
		// 	    alert("Please Enter Email");
		// 	    $("input#reg_email").focus();
		// 		return false;
		// 	}
		// 	else if($("input#cont_name").val().trim() == "") {
		// 	    alert("Please Enter Contact Person Name");
		// 	    $("input#cont_name").focus();
		// 		return false;
		// 	}
		// 	else if($("input#cont_designation").val().trim() == "") {
		// 	    alert("Please Enter Contact Person Designation");
		// 	    $("input#cont_designation").focus();
		// 		return false;
		// 	}

		// 	else if($("input#cont_mobile_no").val().trim() == "") {
		// 	    alert("Please Enter Contact Person Mobile No.");
		// 	    $("input#cont_mobile_no").focus();
		// 		return false;
		// 	}
		// 	else if($("input#cont_email_id").val().trim() == "") {
		// 	    alert("Please Enter Contact Person Email Id");
		// 	    $("input#cont_email_id").focus();
		// 		return false;
		// 	}
		// 	else if($("select#medical_stream").val() == "0") {
		// 	    alert("Please Select Medical Stream");
		// 	    $("select#medical_stream").focus();
		// 		return false;
		// 	}

		// 	else if($("input#nomenclature_degree").val().trim() == "") {
		// 	    alert("Please Enter Nomenclature Of Degree");
		// 	    $("input#nomenclature_degree").focus();
		// 		return false;
		// 	}

		if ($('#form_pg_diploma_id').val() == 0) {
			alert("Please Saved your detail first");
			return false;
		}
		$.post("Submit_Approval_35_From_D?" + key + "=" + value,
				function(data) {
					if (data.error == null) {
						if (data.saved != null) {
							$('#institute_status').val(data.institute_status);
							//						 alert(data.saved);
							alert("Data subbmited successfully");
							location.reload();
						}
					} else {
						alert(data.error)
					}
				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
</script>