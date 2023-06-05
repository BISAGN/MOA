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
									<li class="htab2 "><a href="Deg_rec_OutsideIndia_Url">Application
											For Degree Awarding Universities Outside India</a></li>

								</ul>

								<div class="h-tab_container">

									<div id="htab1" class="h-tab_content">


										<div class="v-tab row m-0">

											<ul class="v-tab_tab-head col-4 col-sm-4 col-md-3 col-lg-2">
												<li class="active" rel="vtab1">Form-36A (UG Courses)</li>
												<li rel="vtab2">Form-36B (PG Courses)</li>
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
																<!-- 																<input type="" id="institute_status"name="institute_status"> -->
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>Institute Coducting Courses Name:<span
																			class="mandatory">*</span></label> <input type="text"
																			id="inst_coures_name" name="inst_coures_name"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="Institute Coducting Courses Name" />

																	</div>

																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>Approved Courses Name:<span
																			class="mandatory">*</span></label> <input type="text"
																			id="aprrove_cours_name" name="aprrove_cours_name"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="Approved Courses Name" />

																	</div>

																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>Affiliating University Name:<span
																			class="mandatory">*</span></label> <input type="text"
																			id="uni_name" name="uni_name"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="Affiliating University Name" />

																	</div>

																</div>


																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="select-style-1">
																		<label for="username">Country<span
																			class="mandatory">*</span></label>
																		<div class="select-position">
																			<select name="country_id" id="country_id"
																				onchange="getCountry()"
																				class="form-control form-control-lg form-control-a effect-9 select2 narrow wrap">
																				<option value="0" selected="selected" name="select">--Select--</option>
																				<c:forEach var="item" items="${getCountryName}"
																					varStatus="num">
																					<option value="${item.id}" name="${item.id}">${item.name}</option>
																				</c:forEach>
																			</select>
																		</div>
																	</div>
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>State:<span class="mandatory">*</span></label>
																		<input type="text" id="state" name="state"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="State" />

																	</div>

																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>District:<span class="mandatory">*</span></label>
																		<input type="text" id="district" name="district"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="District" />

																	</div>

																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>City:<span class="mandatory">*</span></label> <input
																			type="text" id="city" name="city"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100" placeholder="city" />

																	</div>

																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>Address Of University:<span
																			class="mandatory">*</span></label> <input type="text"
																			id="uni_address" name="uni_address"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="Address Of University" />

																	</div>

																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Registered Email-ID<span
																			class="mandatory">*</span></label> <input id="reg_email_id"
																			name="reg_email_id" maxlength="100" type="text"
																			placeholder="Registered Email-ID">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Contact Person Name<span
																			class="mandatory">*</span></label> <input id="con_per_name"
																			name="con_per_name" maxlength="100" type="text"
																			placeholder="Contact Person Name">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">

																	<div class="select-style-1">
																		<label for="text-input">Contact Person
																			Designation<span class="mandatory">*</span>
																		</label>
																		<div class="select-position">
																			<select name="con_per_desg" id="con_per_desg"
																				class="form-control valid" aria-invalid="false">
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
																			class="mandatory">*</span></label> <input id="con_per_mob_no"
																			name="con_per_mob_no" autocomplete="off"
																			maxlength="100" type="text"
																			placeholder="Contact Person Mob NO.">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Contact Person Email-ID<span
																			class="mandatory">*</span></label> <input
																			id="con_per_email_id" name="con_per_email_id"
																			autocomplete="off" maxlength="100" type="text"
																			placeholder="Contact Person Email-ID">
																	</div>
																	<!-- end input -->
																</div>


																<div class="col-12 col-sm-12 col-md-6 col-lg-4">

																	<div class="select-style-1">
																		<label for="text-input">Medical Stream<span
																			class="mandatory">*</span></label>
																		<div class="select-position">
																			<select name="madical_stream" id="madical_stream">
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
																	<div class="input-style-2">
																		<label for="">Nomenclature Of Degree<span
																			class="mandatory">*</span></label> <input id="nomlat_degree"
																			name="nomlat_degree" autocomplete="off"
																			maxlength="100" type="text"
																			placeholder="Nomenclature Of Degree">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-lg-4 col-md-4 col-sm-12 col-12">
																	<div class="input-style-2">
																		<label for="">Abbreviation Of Degree<span
																			class="mandatory"></span></label> <input id="abbre_degree"
																			name="abbre_degree" autocomplete="off"
																			maxlength="100" type="text"
																			placeholder="Abbreviation Of Degree">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-1">
																		<label for=""> Month & Year Of Admission Of First
																			Batch<span class="mandatory">*</span>
																		</label> <input id="y_fir_bat" name="y_fir_bat"
																			autocomplete="off" maxlength="100" type="month"
																			placeholder="Year Of Admission OF First Batch">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-1">
																		<label for=""> month & Year Of Awarded Degree To First
																			Student Of First Batch<span class="mandatory">*</span>
																		</label> <input id="y_fir_bat_std_award"
																			name="y_fir_bat_std_award" autocomplete="off"
																			maxlength="100" type="month"
																			placeholder="Year Of Awarded Degree To First Student OF First Batch">
																	</div>
																	<!-- end input -->
																</div>

																<div class="row">
																	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																		<!-- 								<div class="col-12 col-lg-12 col-md-12 col-sm-12"> -->
																		<div class="input-style-2">
																			<label for="">Degree Award To Be Continued ?<span
																				class="mandatory">*</span></label>
																		</div>
																		<div class="input-style-form-check">
																			<div class="form-check radio-style">
																				<label class="form-check-label" for="radio-1">
																					<input type="radio" id="condition1"
																					name="condition" class="form-check-input"
																					value="Yes" onclick="Degree_conti();"> Yes
																				</label>
																			</div>
																			<div class="form-check radio-style">
																				<label class="form-check-label" for="radio-1">
																					<input type="radio" id="condition2"
																					name="condition" value="No"
																					class="form-check-input" onclick="Degree_conti();">
																					No
																				</label>
																			</div>
																		</div>
																	</div>
																</div>


																<div class="row" id="Condition_conti_No"
																	style="display: none;">
																	<div class="col-12 col-sm-12 col-md-6 col-lg-4"
																		id="y_last_bat">
																		<div class="input-style-1">
																			<label for="">Month & Year Of Degree Awarded To Last
																				Student Of Last Batch<span class="mandatory">*</span>
																			</label> <input id="y_last_bat_std_award"
																				name="y_last_bat_std_award" autocomplete="off"
																				maxlength="100" type="month"
																				placeholder="Year Of Admission OF First Batch">
																		</div>

																	</div>

																	
																</div>
																<ul class="buttons-group mainbtn">
																	<!-- 								<li><a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" -->
																	<!-- 									type="button"><i class="lni lni-search-alt"></i>Search</a></li> -->
																	<li><a href="view_detailfor_36A_Url"
																		id="btn-reload"
																		class="main-btn secondary-btn btn-hover btn-iconic-icon"
																		type="button"><i class="lni lni-search-alt"></i>Search
																			Details</a></li>
																	<li><input type="button" id="btn_save"
																		class="main-btn info-btn btn-hover"
																		value="Save as Draft" onclick="form_36_a_ug();"></li>
																	<li><input type="button" id="btn_save"
																		class="main-btn success-btn  btn-hover"
																		value="Submit for approval"
																		onclick="Submit_Approval_Datafor_institute();"></li>
																	<li><a href="Deg_rec_OutsideIndia_Url"
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
																<input type="hidden" id="form_b_pg_id"
																	name="form_b_pg_id" value="0"
																	class="form-control autocomplete" autocomplete="off">
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>Institute Coducting Courses Name:<span
																			class="mandatory">*</span></label> <input type="text"
																			id="inst_cod_coures_name" name="inst_cod_coures_name"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="Institute Coducting Courses Name" />

																	</div>

																</div>
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>Approved Courses Name:<span
																			class="mandatory">*</span></label> <input type="text"
																			id="aprrove_courses_name" name="aprrove_courses_name"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="Approved Courses Name" />

																	</div>

																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>Affiliating University Name:<span
																			class="mandatory">*</span></label> <input type="text"
																			id="aff_uni_name" name="aff_uni_name"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="Affiliating University Name" />

																	</div>

																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-1">
																		<label for="">Address Of University<span
																			class="mandatory">*</span></label>
																		<textarea id="university_address"
																			name="university_address" maxlength="250"
																			placeholder="Address Of University"></textarea>
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Registered Email-ID<span
																			class="mandatory">*</span></label> <input
																			id="registered_email_id" name="registered_email_id"
																			maxlength="100" type="text"
																			placeholder="Registered Email-ID">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Contact Person Name<span
																			class="mandatory">*</span></label> <input
																			id="con_person_name" name="con_person_name"
																			maxlength="100" type="text"
																			placeholder="Contact Person Name">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">

																	<div class="select-style-1">
																		<label for="text-input">Contact Person
																			Designation<span class="mandatory">*</span>
																		</label>
																		<div class="select-position">
																			<select name="con_person_desg" id="con_person_desg"
																				class="form-control valid" aria-invalid="false">
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
																			class="mandatory">*</span></label> <input
																			id="con_person_mob_no" name="con_person_mob_no"
																			autocomplete="off" maxlength="100" type="text"
																			placeholder="Contact Person Mob NO.">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Contact Person Email-ID<span
																			class="mandatory">*</span></label> <input
																			id="con_person_email_id" name="con_person_email_id"
																			autocomplete="off" maxlength="100" type="text"
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


																<ul class="buttons-group mainbtn">

																	<li><a href="view_detailfor_36B_Url"
																		id="btn-reload"
																		class="main-btn secondary-btn btn-hover btn-iconic-icon"
																		type="button"><i class="lni lni-search-alt"></i>Search
																			Details</a></li>
																	<li><input type="button" id="btn_save"
																		class="main-btn info-btn btn-hover"
																		value="Save as Draft" onclick="return form_36_b_pg();"></li>
																	<li><input type="button" id="btn_save"
																		class="main-btn success-btn  btn-hover"
																		value="Submit for approval"
																		onclick="Submit_Approval_PG_Course();"></li>
																	<li><a href="Deg_rec_OutsideIndia_Url"
																		class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
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
																<input type="hidden" id="form_c_pg_id"
																	name="form_c_pg_id" value="0"
																	class="form-control autocomplete" autocomplete="off">
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>PG Subject:<span class="mandatory">*</span></label>
																		<input type="text" id="pg_subject" name="pg_subject"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="PG Subject" />

																	</div>

																</div>
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>Nomenclature of Degree:<span
																			class="mandatory">*</span></label> <input type="text"
																			id="nom_degree" name="nom_degree"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="Nomenclature of Degree" />

																	</div>

																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>Abbreviation of Degree:<span
																			class="mandatory">*</span></label> <input type="text"
																			id="abb_degree" name="abb_degree"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="Abbreviation of Degree" />

																	</div>

																</div>
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>Month & Year Of Admission Of
																			First Batch:<span
																			class="mandatory">*</span></label> <input type="text"
																			id="m_y_add_fir_bat" name="m_y_add_fir_bat"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="Month & Year Of Admission Of
																			First Batch" />

																	</div>

																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Month & Year Of Passing Of
																			Final Year Examination Of First Batch<span
																			class="mandatory">*</span>
																		</label> <input id="m_y_exam_fir_bat" name="m_y_exam_fir_bat"
																			maxlength="100" type="text"
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
																		</label> <input id="m_y_pro_cer" name="m_y_pro_cer"
																			maxlength="100" type="text"
																			placeholder="Month & Year Of Issue Of
																			Provisional Certificate/Award Of Degree">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Date<span class="mandatory">*</span></label>

																		<input type="text" name="pg_diploma_date" id="pg_diploma_date"
																			maxlength="10"
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
																			id="ref_letter_per" name="ref_letter_per"
																			autocomplete="off" maxlength="100" type="file">
																	</div>
																	<!-- end input -->
																</div>

															</div>
															<div class="row">
																<div class="col-12">


																	<ul class="buttons-group mainbtn">
																	<li><a href="view_detailfor_36C_Url"
																		id="btn-reload"
																		class="main-btn secondary-btn btn-hover btn-iconic-icon"
																		type="button"><i class="lni lni-search-alt"></i>Search
																			Details</a></li>
																		<li><a href="#"
																			class="main-btn dark-btn  btn-hover btn-iconic-icon"
																			onclick="form_pg_pre();"><i
																				class="lni lni-chevron-left"></i>previous</a></li>
																		<li><input type="button" id="btn_save"
																			class="main-btn info-btn btn-hover"
																			value="Save as Draft"
																			onclick="return form_36_c_pg();"></li>
																		<li><input type="button" id="btn_save"
																			class="main-btn success-btn  btn-hover"
																			value="Submit for approval"
																			onclick="Submit_Approval_PG_C_Course();"></li>

																		<li><a href="#"
																			class="main-btn dark-btn  btn-hover btn-iconic-icon btn-iconic-left"
																			onclick="form_pg_diploma_course();">Next <i
																				class="lni lni-chevron-right"></i></a></li>

																	</ul>
																<!-- 				end card -->
																		</div>
																<!-- 		end col -->
															</div>
														</form>


													</div>
													<!--END (PG COURSES)  FOR RECOGNITION 35 B -->

													<!--START (PG-DIPLOMA COURSES)  35 B -->
													<div id="form_pg_diploma" class="content tabcontent"
														style="display: none;">
														<form id="form_d_pg_details">

															<div class="row">
																<input type="hidden" id="form_d_pg_id"
																	name="form_d_pg_id" value="0"
																	class="form-control autocomplete" autocomplete="off">
																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">

																		<label>PG Subject:<span class="mandatory">*</span></label>
																		<input type="text" id="pg_dip_subject" name="pg_dip_subject"
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

																		<label>Month & Year Of Admission Of
																			First Batch:<span
																			class="mandatory">*</span></label> <input type="text"
																			id="year_admi_first_batch_dip" name="year_admi_first_batch_dip"
																			class="autocomplete UpperClassName txt-transupp"
																			autocomplete="off" maxlength="100"
																			placeholder="Month & Year Of Admission Of
																			First Batch" />

																	</div>

																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Month & Year Of Passing Of
																			Final Year Examination Of First Batch<span
																			class="mandatory">*</span>
																		</label> <input id="passing_year_of_finalyear_dip" name="passing_year_of_finalyear_dip"
																			maxlength="100" type="text"
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
																		</label> <input id="year_provisional_certi_dip" name="year_provisional_certi_dip"
																			maxlength="100" type="text"
																			placeholder="Month & Year Of Issue Of
																			Provisional Certificate/Award Of Degree">
																	</div>
																	<!-- end input -->
																</div>

																<div class="col-12 col-sm-12 col-md-6 col-lg-4">
																	<div class="input-style-2">
																		<label for="">Date<span class="mandatory">*</span></label>

																		<input type="text" name="permission_lattter_dt" id="permission_lattter_dt"
																			maxlength="10"
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
																			id="ref_permission_latter" name="ref_permission_latter"
																			autocomplete="off" maxlength="100" type="file">
																	</div>
																	<!-- end input -->
																</div>

															</div>
															<div class="row">
																<div class="col-12">


																	<ul class="buttons-group mainbtn">
																	<li><a href="view_detailfor_36D_Url"
																		id="btn-reload"
																		class="main-btn secondary-btn btn-hover btn-iconic-icon"
																		type="button"><i class="lni lni-search-alt"></i>Search
																			Details</a></li>
																		<li><a href="#"
																			class="main-btn dark-btn  btn-hover btn-iconic-icon"
																			onclick="form_pg_pre();"><i
																				class="lni lni-chevron-left"></i>previous</a></li>
																		<li><input type="button" id="btn_save"
																			class="main-btn info-btn btn-hover"
																			value="Save as Draft"
																			onclick="return form_36_d_pg();"></li>
																		<li><input type="button" id="btn_save"
																			class="main-btn success-btn  btn-hover"
																			value="Submit for approval"
																			onclick="Submit_Approval_PG_D_Course();"></li>

																		

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
	function Degree_conti() {
		var condition = $('input:radio[name=condition]:checked').val();
		if (condition == "Yes") {
			$("div#Condition_conti_No").hide();
		}
		if (condition == "No") {
			$("div#Condition_conti_No").show();
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

		document.getElementById("reg_email_id").innerHTML = "";
		if (/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {

		} else {
			alert("Please Enter Valid Email Address");
			$("input#reg_email_id").focus();
			$("input#reg_email_id").val('');
			return false;
		}
	}

	/*-------------------------START SAVE FOR FORM_A_UG_DETAILS----------------------------- */

	function form_36_a_ug() {
	
		var formvalues = $("#form_a_ug_details").serialize();
		var form_a_ug_id = $('#form_a_ug_id').val();
		$.post('form36_a_ug_action?' + key + "=" + value, formvalues,
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

	///  FINAL SUBMIT UG  //////////////////

	function Submit_Approval_Datafor_institute() {

		if ($("input#inst_coures_name").val().trim() == "") {
			alert("Please Enter Institute Coducting Courses Name.");
			$("input#inst_coures_name").focus();
			return false;
		} else if ($("input#aprrove_cours_name").val().trim() == "") {
			alert("Please Enter Approved Courses Name");
			$("input#aprrove_cours_name").focus();
			return false;
		} else if ($("input#uni_name").val().trim() == "") {
			alert("Please Enter Affiliating University Name");
			$("input#uni_name").focus();
			return false;
		} else if ($("select#country_id").val().trim() == "0") {
			alert("Please Enter Country");
			$("select#country_id").focus();
			return false;
		} else if ($("input#state").val().trim() == "") {
			alert("Please Enter State");
			$("input#state").focus();
			return false;
		} else if ($("input#district").val().trim() == "") {
			alert("Please Enter District.");
			$("input#district").focus();
			return false;
		} else if ($("input#city").val().trim() == "") {
			alert("Please Enter City");
			$("input#city").focus();
			return false;
		} else if ($("input#uni_address").val().trim() == "") {
			alert("Please Enter Address Of University");
			$("input#uni_address").focus();
			return false;
		} else if ($("input#reg_email_id").val().trim() == "") {
			alert("Please Enter Registered Email-ID");
			$("input#reg_email_id").focus();
			return false;
		} else if ($("input#con_per_name").val().trim() == "") {
			alert("Please Enter Contact Person Name");
			$("input#con_per_name").focus();
			return false;
		} else if ($("select#con_per_desg").val().trim() == "0") {
			alert("Please Select Contact Person Designation");
			$("select#con_per_desg").focus();
			return false;
		} else if ($("input#con_per_mob_no").val().trim() == "") {
			alert("Please Enter Contact Person Mobile No.");
			$("input#con_per_mob_no").focus();
			return false;
		} else if ($("input#con_per_email_id").val().trim() == "") {
			alert("Please Enter Contact Person Email-ID");
			$("input#con_per_email_id").focus();
			return false;
		} else if ($("select#madical_stream").val().trim() == "0") {
			alert("Please Select Madical Stream");
			$("select#madical_stream").focus();
			return false;
		} else if ($("input#nomlat_degree").val().trim() == "") {
			alert("Please Enter Nomenclature Of Degree");
			$("input#nomlat_degree").focus();
			return false;
		} else if ($("input#abbre_degree").val().trim() == "") {
			alert("Please Enter Abbreviation Of Degree");
			$("input#abbre_degree").focus();
			return false;
		} else if ($("input#y_fir_bat").val().trim() == "") {
			alert("Please Enter Year Of Admission Of First Batch");
			$("input#y_fir_bat").focus();
			return false;
		} else if ($("input#m_fir_bat").val().trim() == "") {
			alert("Please Enter Month Of Admission Of First Batch");
			$("input#m_fir_bat").focus();
			return false;
		} else if ($("input#y_fir_bat_std_award").val().trim() == "") {
			alert("Please Enter Year Of Awarded Degree To First Student Of First Batch");
			$("input#y_fir_bat_std_award").focus();
			return false;
		} else if ($("input#m_fir_bat_std_award").val().trim() == "") {
			alert("Please Enter MonthOf Awarded DegreeToFirst Student Of FirstBatch");
			$("input#m_fir_bat_std_award").focus();
			return false;
		}

	
	 					 
		if ($('#form_a_ug_id').val() == 0) {
			alert("Please Saved your detail first");
			return false;
		}
		$.post("Submit_Approval_Data_institute_UG?" + key + "=" + value,
				function(data) {
					if (data.error == null) {
						if (data.saved != null) {
							$('#institute_status').val(data.institute_status);
							// alert(data.saved);
							alert("Data subbmited successfully");
							location.reload();
						}
					} else {
						alert(data.error)
					}
				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
		// $("#inst_status").val(1);
		//status(2) = Submit data , status(1) = Update data ,status(0) = Save as Draft data
	}

	/*-------------------------START SAVE FOR FORM_B_PG_DETAILS----------------------------- */

	function form_36_b_pg() {
		var formvalues = $("#form_b_pg_details").serialize();
		var form_b_pg_id = $('#form_b_pg_id').val();
		$.post('form36_b_pg_action?' + key + "=" + value, formvalues,
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

	///  FINAL SUBMIT PG  //////////////////

	function Submit_Approval_PG_Course() {
		// 		alert("dhc")
		if ($("input#inst_cod_coures_name").val().trim() == "") {
			alert("Please Enter Institute Coducting Courses Name.");
			$("input#inst_cod_coures_name").focus();
			return false;
		} else if ($("input#aprrove_courses_name").val().trim() == "") {
			alert("Please Enter Approved Courses Name");
			$("input#aprrove_courses_name").focus();
			return false;
		} else if ($("input#aff_uni_name").val().trim() == "") {
			alert("Please Enter Affiliating University Name");
			$("input#aff_uni_name").focus();
			return false;
		}
		// 		else if ($("select#country_id").val().trim() == "0") {
		// 			alert("Please Enter Country");
		// 			$("select#country_id").focus();
		// 			return false;
		// 		}else if ($("input#state").val().trim() == "") {
		// 			alert("Please Enter State");
		// 			$("input#state").focus();
		// 			return false;
		//  		} 
		// 		else if ($("input#district").val().trim() == "") {
		// 			alert("Please Enter District.");
		// 			$("input#district").focus();
		// 			return false;
		// 		} else if ($("input#city").val().trim() == "") {
		// 			alert("Please Enter City");
		// 			$("input#city").focus();
		// 			return false;
		// 		}
		else if ($("textarea#university_address").val().trim() == "") {
			alert("Please Enter Address Of University");
			$("textarea#university_address").focus();
			return false;
		} else if ($("input#registered_email_id").val().trim() == "") {
			alert("Please Enter Registered Email-ID");
			$("input#registered_email_id").focus();
			return false;
		} else if ($("input#con_person_name").val().trim() == "") {
			alert("Please Enter Contact Person Name");
			$("input#con_person_name").focus();
			return false;
		} else if ($("select#con_person_desg").val() == "0") {
			alert("Please Select Contact Person Designation");
			$("select#con_person_desg").focus();
			return false;
		} else if ($("input#con_person_mob_no").val().trim() == "") {
			alert("Please Enter Contact Person Mobile No.");
			$("input#con_person_mob_no").focus();
			return false;
		} else if ($("input#con_person_email_id").val().trim() == "") {
			alert("Please Enter Contact Person Email-ID");
			$("input#con_person_email_id").focus();
			return false;
		} else if ($("select#medical_stream").val() == "0") {
			alert("Please Select Medical Stream");
			$("select#medical_stream").focus();
			return false;
		}
		// 		else if ($("input#nomlat_degree").val().trim() == "") {
		// 			alert("Please Enter Nomenclature Of Degree");
		// 			$("input#nomlat_degree").focus();
		// 			return false;
		//  		}
		// 		else if ($("input#abbre_degree").val().trim() == "") {
		// 			alert("Please Enter Abbreviation Of Degree");
		// 			$("input#abbre_degree").focus();
		// 			return false;
		//  		}
		// 		else if ($("input#y_fir_bat").val().trim() == "") {
		// 			alert("Please Enter Year Of Admission Of First Batch");
		// 			$("input#y_fir_bat").focus();
		// 			return false;
		//  		}
		// 		else if ($("input#m_fir_bat").val().trim() == "") {
		// 			alert("Please Enter Month Of Admission Of First Batch");
		// 			$("input#m_fir_bat").focus();
		// 			return false;
		//  		}
		// 		else if ($("input#y_fir_bat_std_award").val().trim() == "") {
		// 			alert("Please Enter Year Of Awarded Degree To First Student Of First Batch");
		// 			$("input#y_fir_bat_std_award").focus();
		// 			return false;
		//  		}
		// 		else if ($("input#m_fir_bat_std_award").val().trim() == "") {
		// 			alert("Please Enter MonthOf Awarded DegreeToFirst Student Of FirstBatch");
		// 			$("input#m_fir_bat_std_award").focus();
		// 			return false;
		//  		}

		// 		else if ($('input[name=condition]:checked').length == 0) {
		// 			alert("Please select condition");
		// 			$("input#condition").focus();
		// 		}
		// 					 if{
		// 						if($('input:radio[name=condition]:checked').val() == "No"){
		// 							if($("input#y_last_bat_std_award").val().trim() == ""){
		// 								 alert("Please Enter Year Of Award Of Degree To The Last Student Of Last Batch");
		// 							     $("input#y_last_bat_std_award").focus();
		// 							     return false;	
		// 								}
		// 							if($("input#m_last_bat_std_award").val().trim() == ""){
		// 								 alert("Please Enter Month Of Award Of Degree To The Last Student Of Last Batch");
		// 							     $("input#m_last_bat_std_award").focus();
		// 							     return false;	
		// 								}
		// 						  }
		// 					}

		if ($('#form_b_pg_id').val() == 0) {
			alert("Please Saved your detail first");
			return false;
		}

		if ($('#form_b_pg_id').val() == 0) {
			alert("Please Saved your detail first");
			return false;
		}

		$.post("Submit_Approval_PGfor_Course?" + key + "=" + value,
				function(data) {

					debugger;
					if (data.error == null) {

						if (data.saved != null) {
							$('#institute_status').val(data.institute_status);
							// alert(data.saved);
							alert("Data subbmited successfully");
							location.reload();
						}
					} else {
						alert(data.error)
					}
				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
		// $("#inst_status").val(1);
		//status(2) = Submit data , status(1) = Update data ,status(0) = Save as Draft data
	}
	
	
	/////////////////// SAVE FOR FORM C //////////////
	
	
		function form_36_c_pg() {
		var formvalues = $("#form_c_pg_details").serialize();
		var form_c_pg_id = $('#form_c_pg_id').val();
		$.post('form36_c_pg_action?' + key + "=" + value, formvalues,
				function(data) {
					if (data.error == null) {
						if (data.form_c_pg_id != null) {

							$('#form_c_pg_id').val(data.form_c_pg_id);

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
	
		$(document).ready(function() {
			
	  
	       
// 	        datepicketDate('file_date');
// 	        datepicketDate('velidity_period');
	        datepicketDate('pg_diploma_date');
//	         datepicketDate('date_of_starting_internship');
//	         datepicketDate('date_of_completion_internship');
//	         datepicketDate('to_date1');
	       // datepicketDate('from_date1');
	       
	});
		
		//final submit c //
		
		function Submit_Approval_PG_C_Course() {
		
		if ($("input#pg_subject").val().trim() == "") {
			alert("Please Enter PG Subject.");
			$("input#pg_subject").focus();
			return false;
		} else if ($("input#nom_degree").val().trim() == "") {
			alert("Please Enter Nomenclature Of Degree");
			$("input#nom_degree").focus();
			return false;
		} else if ($("input#abb_degree").val().trim() == "") {
			alert("Please Enter Abbreviation Of Degree");
			$("input#abb_degree").focus();
			return false;
		}
		 else if ($("input#m_y_add_fir_bat").val().trim() == "") {
			alert("Please Enter Month & Year Of Admission Of First Batch");
			$("input#m_y_add_fir_bat").focus();
			return false;
		} 
		 else if ($("input#m_y_exam_fir_bat").val().trim() == "") {
			alert("Please Enter Month & Year Of Passing Of Final Year Examination Of First Batch");
			$("input#m_y_exam_fir_bat").focus();
			return false;
		}
		 else if ($("input#m_y_pro_cer").val().trim() == "") {
			alert("Please Enter Month & Year Of Issue Of Provisional Certificate/Award Of Degree.");
			$("input#m_y_pro_cer").focus();
			return false;
		 
		 }
		if ($('#form_c_pg_id').val() == 0) {
			alert("Please Saved your detail first");
			return false;
		}

		

		$.post("Submit_Approval_PG_C_for_Course?" + key + "=" + value,
				function(data) {

					debugger;
					if (data.error == null) {

						if (data.saved != null) {
							$('#university_status').val(data.university_status);
							// alert(data.saved);
							alert("Data subbmited successfully");
							location.reload();
						}
					} else {
						alert(data.error)
					}
				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
		// $("#inst_status").val(1);
		//status(2) = Submit data , status(1) = Update data ,status(0) = Save as Draft data
	}
		
		
		/////////////////// SAVE FOR FORM D //////////////
		
		
		function form_36_d_pg() {
		var formvalues = $("#form_d_pg_details").serialize();
		var form_d_pg_id = $('#form_d_pg_id').val();
		$.post('form36_d_pg_action?' + key + "=" + value, formvalues,
				function(data) {
					if (data.error == null) {
						if (data.form_d_pg_id != null) {

							$('#form_d_pg_id').val(data.form_d_pg_id);

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
	
		$(document).ready(function() {
			
	  
	       
// 	        datepicketDate('file_date');
// 	        datepicketDate('velidity_period');
	        datepicketDate('permission_lattter_dt');
//	         datepicketDate('date_of_starting_internship');
//	         datepicketDate('date_of_completion_internship');
//	         datepicketDate('to_date1');
	       // datepicketDate('from_date1');
	       
	});
	
            //final submit D //
		
		function Submit_Approval_PG_D_Course() {
		
		if ($("input#pg_subject").val().trim() == "") {
			alert("Please Enter PG Subject.");
			$("input#pg_subject").focus();
			return false;
		} else if ($("input#nom_degree").val().trim() == "") {
			alert("Please Enter Nomenclature Of Degree");
			$("input#nom_degree").focus();
			return false;
		} else if ($("input#abb_degree").val().trim() == "") {
			alert("Please Enter Abbreviation Of Degree");
			$("input#abb_degree").focus();
			return false;
		}
		 else if ($("input#m_y_add_fir_bat").val().trim() == "") {
			alert("Please Enter Month & Year Of Admission Of First Batch");
			$("input#m_y_add_fir_bat").focus();
			return false;
		} 
		 else if ($("input#m_y_exam_fir_bat").val().trim() == "") {
			alert("Please Enter Month & Year Of Passing Of Final Year Examination Of First Batch");
			$("input#m_y_exam_fir_bat").focus();
			return false;
		}
		 else if ($("input#m_y_pro_cer").val().trim() == "") {
			alert("Please Enter Month & Year Of Issue Of Provisional Certificate/Award Of Degree.");
			$("input#m_y_pro_cer").focus();
			return false;
		 }

		if ($('#form_d_pg_id').val() == 0) {
			alert("Please Saved your detail first");
			return false;
		}

		

		$.post("Submit_Approval_PG_D_for_Course?" + key + "=" + value,
				function(data) {

					debugger;
					if (data.error == null) {

						if (data.saved != null) {
							$('#university_status').val(data.university_status);
							// alert(data.saved);
							alert("Data subbmited successfully");
							location.reload();
						}
					} else {
						alert(data.error)
					}
				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
		// $("#inst_status").val(1);
		//status(2) = Submit data , status(1) = Update data ,status(0) = Save as Draft data
	}
		
</script>