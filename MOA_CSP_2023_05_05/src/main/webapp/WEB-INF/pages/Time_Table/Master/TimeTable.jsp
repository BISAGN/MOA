<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script> -->
<!-- <script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> -->
<!-- <script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> -->
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
<!-- <script type="text/javascript" src="js/watermark/common.js"></script> -->



<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<link href="assets/db_css/db_timetable.css" rel="Stylesheet"></link>

<section class="dashboard-page timetable-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							Create TimeTable<span class="text-red font-size12 enter_by"></span>
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Create
									TimeTable</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>

		<div class="form-elements-wrapper">

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form>
						<div class="card-style mb-30">
							<h6 class="mb-25">TimeTable Master</h6>
							<div class="inst_hide">
								<div class="inst_block">
									<h6 class="mb-1">Please follow the steps before creating a
										Time Table</h6>
									<ul class="inst_list">
										<li><p class="inst_text">
												<b>Step 1 : </b>First create your Faculty from <b
													class="concat-string">FACULTY DETAILS &gt; FACULTY FORM
													&gt; <a class="text-heighlight"
													href="exp_excel_faculty_url">Faculty Excel Upload</a>
												</b>
											</p></li>

										<li><p class="inst_text">
												<b>Step 2 : </b>Now link your Faculty to their Subject from <b
													class="concat-string">MASTERS &gt; <a
													class="text-heighlight" href="Faculty_to_CourseUrl">Link Faculty and Subject</a></b>
											</p></li>

										<li><p class="inst_text">
												<b>Step 3 : </b>Now add your Classroom from <b
													class="concat-string">MASTERS &gt; <a
													class="text-heighlight" href="Classroom_Mstr_Url">Classroom</a></b>
											</p></li>

										<li><p class="inst_text">
												<b>Step 4 : </b>Now create your Events from <b
													class="concat-string">MASTERS &gt; <a
													class="text-heighlight" href="event_mstrUrl">Event
														Master</a></b>
											</p></li>

										<li><p class="inst_text">
												<b>Step 5 : </b>Now create your Academic from <b
													class="concat-string">MASTERS &gt; <a
													class="text-heighlight" href="academic_detailsUrl">Academic</a></b>
											</p></li>

										<li><p class="inst_text">
												<b>Step 6 : </b>Then check your Academic Schedule from <b
													class="concat-string">TIME TABLE &gt; <a
													class="text-heighlight" href="Academic_Schedule_Url">Academic
														Schedule</a></b>
											</p></li>

										<li><p class="inst_text">
												<b>Step 7 : </b>Now you can create your time-table.
											</p></li>

									</ul>
								</div>
							</div>

							<div class="inst1_hide">
								<div class="inst_block simple-instruction">
									<strong>Instruction :</strong> To create your lectures please
									click on add [+] icon button.
								</div>
							</div>
							<div class="row d-flex justify-content-center">
								<div class="col-12 col-sm-12 col-md-8 col-lg-6">
									<ul class="tab-group" id="tab-group">
										<li class="tab one active"><a id="TTform">TimeTable
												For</a></li>
										<li class="tab two"><a id="TT">Create TimeTable</a></li>
									</ul>
								</div>
							</div>

							<div class="form_fillup">
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="" id="degree" class="">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getdegreeList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.degree_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>Professional<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="" id="professional" class="">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getProfessionalList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.professional}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>

								</div>


								<!-- Bottom Button Start -->
								<div class="btn-bottom">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12">

											<ul class="buttons-group mainbtn">


												<li><a href="TimeTable"
													class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
												<li><a
													class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left btnnext"
													id="btnext" type="submit">Next<i
														class="lni lni-chevron-right"></i></a></li>
											</ul>
										</div>
									</div>
								</div>
								<!-- Bottom Button End -->
								
							</div>

							<div class="create_tt">

								<div class="choose_section">
									<div class="option-group">
										<div class="option-container">

											<input class="option-input" checked="checked" id="option-1"
												type="radio" name="options" value="1" /> <input
												class="option-input" id="option-2" type="radio"
												name="options" value="2" /> <label class="option"
												for="option-1"> <span class="option__indicator"></span>
												<span class="option__label"> Weekly<sub>Timetable</sub></span>
											</label> <label class="option" for="option-2"> <span
												class="option__indicator"></span> <spanBISAG_LIVE_TESTING_21_02_2023
												class="option__label">Daily<sub>Timetable</sub></span>
											</label>

										</div>
									</div>
								</div>



								<div class="table_show">
									<div class="row m-0">
										<div class="col-12 p-0">


											<ul class="buttons-group mainbtn justify-content-end">
												<li class="p-0"><a
													class="main-btn deactive-btn btn-hover btn-iconic-icon"
													id="btn-extracl" data-bs-toggle="modal"
													data-bs-target="#ex_class" type="button"> <i
														class="lni lni-plus"></i>Periodic Exam/Extra Class
												</a></li>

											</ul>

											<div id="select_day">
												<div class="row m-0">
													<div class="col-12">
														<ul class="select_days">
															<li id="btmon">monday</li>
															<li id="bttue">tuesday</li>
															<li id="btwed">wednesday</li>
															<li id="btthu">thursday</li>
															<li id="btfri">friday</li>
															<li id="btsat">saturday</li>
															<li id="btsun">sunday</li>
														</ul>
													</div>
												</div>
											</div>

											<div class="table-responsive">
												<div class="timetable weekly">
													<div class="show_tt" id="viewweeklytimetable" name="one">
														<span class="viewtt">View Table</span> <i
															class="bi bi-eye"></i>
													</div>
													<!-- </div> -->
													<div class="week-names">
														<div>monday</div>
														<div>tuesday</div>
														<div>wednesday</div>
														<div>thursday</div>
														<div>friday</div>
														<div class="">saturday</div>
														<div class="weekend">sunday</div>
													</div>
													<div class="time-interval">
														<div>1</div>
														<div>2</div>
														<div>3</div>
														<div>4</div>
														<div>5</div>
														<div>6</div>
														<div>7</div>
														<div>8</div>
														<div>9</div>
													</div>
													<div class="content">
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekMONDAY1"></div>
																<input type="hidden" id="vttweekidMONDAY1" class="LayoutHiddenId"
																	name="vttweekidMONDAY1" value="0" autocomplete="off" />
																<!-- <div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getDelLecdataBTBT"
																		data-bs-toggle="modal" data-bs-target="#monday11">
																		<i class="lni lni-plus"></i>
																	</button>
																</div> -->

																<div class="col-12">

																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#monday11">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>

																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>



														<div>

															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTUESDAY1"></div>
																<input type="hidden" id="vttweekidTUESDAY1" class="LayoutHiddenId"
																	name="vttweekidTUESDAY1" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#tuesday21">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
															<!--       <div class="accent-red-gradient"></div> -->
														</div>

														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekWEDNESDAY1">
																</div>
																<input type="hidden" id="vttweekidWEDNESDAY1" class="LayoutHiddenId"
																	name="vttweekidWEDNESDAY1" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#wednesday31">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>

														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTHURSDAY1">
																</div>
																<input type="hidden" id="vttweekidTHURSDAY1" class="LayoutHiddenId"
																	name="vttweekidTHURSDAY1" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#thursday41">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
															<!--       <div class="accent-purple-gradient"></div> -->
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekFRIDAY1"></div>
																<input type="hidden" id="vttweekidFRIDAY1" class="LayoutHiddenId"
																	name="vttweekidFRIDAY1" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#friday51">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
															<!--       <div class="accent-cyan-gradient"></div> -->
														</div>
														<div>

															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSATURDAY1">
																</div>
																<input type="hidden" id="vttweekidSATURDAY1" class="LayoutHiddenId"
																	name="vttweekidSATURDAY1" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#saturday61">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>

														</div>

														<div class="weekend">

															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSUNDAY1"></div>
																<input type="hidden" id="vttweekidSUNDAY1" class="LayoutHiddenId"
																	name="vttweekidSUNDAY1" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#sunday71">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>

														</div>


														<div>

															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekMONDAY2"></div>
																<input type="hidden" id="vttweekidMONDAY2" class="LayoutHiddenId"
																	name="vttweekidMONDAY2" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#monday11">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!-- 																      <div class="accent-orange-gradient"></div> -->
															</div>

														</div>
														<div>

															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTUESDAY2"></div>
																<input type="hidden" id="vttweekidTUESDAY2" class="LayoutHiddenId"
																	name="vttweekidTUESDAY2" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#tuesday21">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>

														</div>
														<div>

															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekWEDNESDAY2">
																</div>
																<input type="hidden" id="vttweekidWEDNESDAY2" class="LayoutHiddenId"
																	name="vttweekidWEDNESDAY2" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#wednesday31">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>

														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTHURSDAY2">
																</div>
																<input type="hidden" id="vttweekidTHURSDAY2" class="LayoutHiddenId"
																	name="vttweekidTHURSDAY2" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#thursday41">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
															<!--       <div class="accent-cyan-gradient"></div> -->
														</div>
														<div>

															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekFRIDAY2"></div>
																<input type="hidden" id="vttweekidFRIDAY2" class="LayoutHiddenId"
																	name="vttweekidFRIDAY2" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#friday51">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>

														</div>
														<div class="">

															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSATURDAY2">
																</div>
																<input type="hidden" id="vttweekidSATURDAY2" class="LayoutHiddenId"
																	name="vttweekidSATURDAY2" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#saturday61">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>

														</div>
														<div class="weekend">

															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSUNDAY2"></div>
																<input type="hidden" id="vttweekidSUNDAY2" class="LayoutHiddenId"
																	name="vttweekidSUNDAY2" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#sunday71">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>

														</div>


														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekMONDAY3"></div>
																<input type="hidden" id="vttweekidMONDAY3" class="LayoutHiddenId"
																	name="vttweekidMONDAY3" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#monday11">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
															<!--       <div class="accent-pink-gradient"></div> -->
														</div>

														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTUESDAY3"></div>
																<input type="hidden" id="vttweekidTUESDAY3" class="LayoutHiddenId"
																	name="vttweekidTUESDAY3" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#tuesday21">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>

														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekWEDNESDAY3">
																</div>
																<input type="hidden" id="vttweekidWEDNESDAY3" class="LayoutHiddenId"
																	name="vttweekidWEDNESDAY3" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#wednesday31">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
															<!--       <div class="accent-purple-gradient"></div> -->
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTHURSDAY3">
																</div>
																<input type="hidden" id="vttweekidTHURSDAY3" class="LayoutHiddenId"
																	name="vttweekidTHURSDAY3" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#thursday41">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekFRIDAY3"></div>
																<input type="hidden" id="vttweekidFRIDAY3" class="LayoutHiddenId"
																	name="vttweekidFRIDAY3" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#friday51">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
															<!--     <div class="accent-green-gradient"></div> -->
														</div>
														<div class="">
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSATURDAY3">
																</div>
																<input type="hidden" id="vttweekidSATURDAY3" class="LayoutHiddenId"
																	name="vttweekidSATURDAY3" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#saturday61">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div class="weekend">
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSUNDAY3"></div>
																<input type="hidden" id="vttweekidSUNDAY3" class="LayoutHiddenId"
																	name="vttweekidSUNDAY3" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#sunday71>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>


														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekMONDAY4"></div>
																<input type="hidden" id="vttweekidMONDAY4" class="LayoutHiddenId"
																	name="vttweekidMONDAY4" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#monday11>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTUESDAY4"></div>
																<input type="hidden" id="vttweekidTUESDAY4" class="LayoutHiddenId"
																	name="vttweekidTUESDAY4" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#tuesday21>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekWEDNESDAY4">
																</div>
																<input type="hidden" id="vttweekidWEDNESDAY4" class="LayoutHiddenId"
																	name="vttweekidWEDNESDAY4" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#wednesday31>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTHURSDAY4">
																</div>
																<input type="hidden" id="vttweekidTHURSDAY4" class="LayoutHiddenId"
																	name="vttweekidTHURSDAY4" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#thursday41>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekFRIDAY4"></div>
																<input type="hidden" id="vttweekidSATURDAY4" class="LayoutHiddenId"
																	name="vttweekidSATURDAY4" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#friday51>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div class="">
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSATURDAY4">
																</div>
																<input type="hidden" id="vttweekidSATURDAY4" class="LayoutHiddenId"
																	name="vttweekidSATURDAY4" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#saturday61>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div class="weekend">
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSUNDAY4"></div>
																<input type="hidden" id="vttweekidSUNDAY4" class="LayoutHiddenId"
																	name="vttweekidSUNDAY4" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#sunday71>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>


														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekMONDAY5"></div>
																<input type="hidden" id="vttweekidMONDAY5" class="LayoutHiddenId"
																	name="vttweekidMONDAY5" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#monday11>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTUESDAY5"></div>
																<input type="hidden" id="vttweekidTUESDAY5" class="LayoutHiddenId"
																	name="vttweekidTUESDAY5" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#tuesday21>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekWEDNESDAY5">
																</div>
																<input type="hidden" id="vttweekidWEDNESDAY5" class="LayoutHiddenId"
																	name="vttweekidWEDNESDAY5" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#wednesday31>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTHURSDAY5">
																</div>
																<input type="hidden" id="vttweekidTHURSDAY5" class="LayoutHiddenId"
																	name="vttweekidTHURSDAY5" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#thursday41>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekFRIDAY5"></div>
																<input type="hidden" id="vttweekidFRIDAY5" class="LayoutHiddenId"
																	name="vttweekidFRIDAY5" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#friday51>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div class="">
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSATURDAY5">
																</div>
																<input type="hidden" id="vttweekidSATURDAY5" class="LayoutHiddenId"
																	name="vttweekidSATURDAY5" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#saturday61>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div class="weekend">
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSUNDAY5"></div>
																<input type="hidden" id="vttweekidSUNDAY5" class="LayoutHiddenId"
																	name="vttweekidSUNDAY5" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#sunday71>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>


														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekMONDAY6"></div>
																<input type="hidden" id="vttweekidMONDAY6" class="LayoutHiddenId"
																	name="vttweekidMONDAY6" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#monday11>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTUESDAY6"></div>
																<input type="hidden" id="vttweekidTUESDAY6" class="LayoutHiddenId"
																	name="vttweekidTUESDAY6" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#tuesday21>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekWEDNESDAY6">
																</div>
																<input type="hidden" id="vttweekidWEDNESDAY6" class="LayoutHiddenId"
																	name="vttweekidWEDNESDAY6" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#wednesday31>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTHURSDAY6">
																</div>
																<input type="hidden" id="vttweekidTHURSDAY6" class="LayoutHiddenId"
																	name="vttweekidTHURSDAY6" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#thursday41>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekFRIDAY6"></div>
																<input type="hidden" id="vttweekidFRIDAY6" class="LayoutHiddenId"
																	name="vttweekidFRIDAY6" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#friday51>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div class="">
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSATURDAY6">
																</div>
																<input type="hidden" id="vttweekidSATURDAY6" class="LayoutHiddenId"
																	name="vttweekidSATURDAY6" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#saturday61>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div class="weekend">
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSUNDAY6"></div>
																<input type="hidden" id="vttweekidSUNDAY6" class="LayoutHiddenId"
																	name="vttweekidSUNDAY6" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#sunday71>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekMONDAY7"></div>
																<input type="hidden" id="vttweekidMONDAY7" class="LayoutHiddenId"
																	name="vttweekidMONDAY7" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#monday11>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTUESDAY7"></div>
																<input type="hidden" id="vttweekidTUESDAY7" class="LayoutHiddenId"
																	name="vttweekidTUESDAY7" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#tuesday21>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekWEDNESDAY7">
																</div>
																<input type="hidden" id="vttweekidWEDNESDAY7" class="LayoutHiddenId"
																	name="vttweekidWEDNESDAY7" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#wednesday31>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTHURSDAY7">
																</div>
																<input type="hidden" id="vttweekidTHURSDAY7" class="LayoutHiddenId"
																	name="vttweekidTHURSDAY7" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#thursday41>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekFRIDAY7"></div>
																<input type="hidden" id="vttweekidFRIDAY7" class="LayoutHiddenId"
																	name="vttweekidFRIDAY7" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#friday51>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div class="">
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSATURDAY7">
																</div>
																<input type="hidden" id="vttweekidSATURDAY7" class="LayoutHiddenId"
																	name="vttweekidSATURDAY7" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#saturday61>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div class="weekend">
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSUNDAY7"></div>
																<input type="hidden" id="vttweekidSUNDAY7" class="LayoutHiddenId"
																	name="vttweekidSUNDAY7" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#sunday71>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekMONDAY8"></div>
																<input type="hidden" id="vttweekidMONDAY8" class="LayoutHiddenId"
																	name="vttweekidMONDAY8" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#monday11>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTUESDAY8"></div>
																<input type="hidden" id="vttweekidTUESDAY8" class="LayoutHiddenId"
																	name="vttweekidTUESDAY8" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#tuesday21>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekWEDNESDAY8">
																</div>
																<input type="hidden" id="vttweekidWEDNESDAY8" class="LayoutHiddenId"
																	name="vttweekidWEDNESDAY8" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#wednesday31>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTHURSDAY8">
																</div>
																<input type="hidden" id="vttweekidTHURSDAY8" class="LayoutHiddenId"
																	name="vttweekidTHURSDAY8" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#thursday41>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekFRIDAY8"></div>
																<input type="hidden" id="vttweekidFRIDAY8" class="LayoutHiddenId"
																	name="vttweekidFRIDAY8" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#friday51>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div class="">
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSATURDAY8">
																</div>
																<input type="hidden" id="vttweekidSATURDAY8" class="LayoutHiddenId"
																	name="vttweekidSATURDAY8" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#saturday61>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div class="weekend">
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSUNDAY8"></div>
																<input type="hidden" id="vttweekidSUNDAY8" class="LayoutHiddenId"
																	name="vttweekidSUNDAY8" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#sunday71>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekMONDAY9"></div>
																<input type="hidden" id="vttweekidMONDAY9" class="LayoutHiddenId"
																	name="vttweekidMONDAY9" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#monday11>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTUESDAY9"></div>
																<input type="hidden" id="vttweekidTUESDAY9" class="LayoutHiddenId"
																	name="vttweekidTUESDAY9" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#tuesday21>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekWEDNESDAY9">
																</div>
																<input type="hidden" id="vttweekidWEDNESDAY9" class="LayoutHiddenId"
																	name="vttweekidWEDNESDAY9" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#wednesday31>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekTHURSDAY9">
																</div>
																<input type="hidden" id="vttweekidTHURSDAY9" class="LayoutHiddenId"
																	name="vttweekidTHURSDAY9" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#thursday41>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div>
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekFRIDAY9"></div>
																<input type="hidden" id="vttweekidFRIDAY9" class="LayoutHiddenId"
																	name="vttweekidFRIDAY9" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#friday51>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div class="">
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSATURDAY9">
																</div>
																<input type="hidden" id="vttweekidSATURDAY9" class="LayoutHiddenId"
																	name="vttweekidSATURDAY9" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#saturday61>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														<div class="weekend">
															<div class="row m-0">
																<div class="fatch_valuett" id="vttweekSUNDAY9"></div>
																<input type="hidden" id="vttweekidSUNDAY9" class="LayoutHiddenId"
																	name="vttweekidSUNDAY9" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target=#sunday71>
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDelLecdataBT"
																		title="Delete" data-bs-toggle="modal">
																		<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																<!--       <div class="accent-orange-gradient"></div> -->
															</div>
														</div>
														
													</div>
												</div>




												<div id="mon">
													<div class="timetable day">
														<div class="show_tt" id="viewmon" name="one">
															<span class="viewtt">View Table</span> <i
																class="bi bi-eye"></i>
														</div>
														<div class="week-names">
															<div>monday</div>
														</div>
														<div class="time-interval">
															<div>1</div>
															<div>2</div>
															<div>3</div>
															<div>4</div>
															<div>5</div>
															<div>6</div>
															<div>7</div>
															<div>8</div>
															<div>9</div>
														</div>
														<div class="content">
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayMONDAY1"></div>
																	<input type="hidden" id="vttdayidMONDAY1"  class="DailyLayoutHiddenId"
																		name="vttdayidMONDAY1" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#monday11">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayMONDAY2"></div>
																	<input type="hidden" id="vttdayidMONDAY2" class="DailyLayoutHiddenId"
																		name="vttdayidMONDAY2" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#monday11">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayMONDAY3"></div>
																	<input type="hidden" id="vttdayidMONDAY3" class="DailyLayoutHiddenId"
																		name="vttdayidMONDAY3" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#monday11">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayMONDAY4"></div>
																	<input type="hidden" id="vttdayidMONDAY4" class="DailyLayoutHiddenId"
																		name="vttdayidMONDAY4" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#monday11">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayMONDAY5"></div>
																	<input type="hidden" id="vttdayidMONDAY5" class="DailyLayoutHiddenId"
																		name="vttdayidMONDAY5" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#monday11">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayMONDAY6"></div>
																	<input type="hidden" id="vttdayidMONDAY6" class="DailyLayoutHiddenId"
																		name="vttdayidMONDAY6" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#monday11">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayMONDAY7"></div>
																	<input type="hidden" id="vttdayidMONDAY7" class="DailyLayoutHiddenId"
																		name="vttdayidMONDAY7" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#monday11">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayMONDAY8"></div>
																	<input type="hidden" id="vttdayidMONDAY8" class="DailyLayoutHiddenId"
																		name="vttdayidMONDAY8" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#monday11">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayMONDAY9"></div>
																	<input type="hidden" id="vttdayidMONDAY9" class="DailyLayoutHiddenId"
																		name="vttdayidMONDAY9" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#monday11">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>

												<div id="tue">
													<div class="timetable day">
														<div class="show_tt" id="viewtue" name="one">
															<span class="viewtt">View Table</span> <i
																class="bi bi-eye"></i>
														</div>
														<div class="week-names">
															<div>tuesday</div>
														</div>
														<div class="time-interval">
															<div>1</div>
															<div>2</div>
															<div>3</div>
															<div>4</div>
															<div>5</div>
															<div>6</div>
															<div>7</div>
															<div>8</div>
															<div>9</div>
														</div>
														<div class="content">
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTUESDAY1"></div>
																	<input type="hidden" id="vttdayidTUESDAY1" class="DailyLayoutHiddenId"
																		name="vttdayidTUESDAY1" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#tuesday21">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>

															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTUESDAY2"></div>
																	<input type="hidden" id="vttdayidTUESDAY2" class="DailyLayoutHiddenId"
																		name="vttdayidTUESDAY2" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#tuesday21">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>

															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTUESDAY3"></div>
																	<input type="hidden" id="vttdayidTUESDAY3" class="DailyLayoutHiddenId"
																		name="vttdayidTUESDAY3" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#tuesday21">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>

															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTUESDAY4"></div>
																	<input type="hidden" id="vttdayidTUESDAY4" class="DailyLayoutHiddenId"
																		name="vttdayidTUESDAY4" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#tuesday21">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>

															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTUESDAY5"></div>
																	<input type="hidden" id="vttdayidTUESDAY5" class="DailyLayoutHiddenId"
																		name="vttdayidTUESDAY5" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#tuesday21">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>

															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTUESDAY6"></div>
																	<input type="hidden" id="vttdayidTUESDAY6" class="DailyLayoutHiddenId"
																		name="vttdayidTUESDAY6" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#tuesday21">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTUESDAY7"></div>
																	<input type="hidden" id="vttdayidTUESDAY7" class="DailyLayoutHiddenId"
																		name="vttdayidTUESDAY7" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#tuesday21">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTUESDAY8"></div>
																	<input type="hidden" id="vttdayidTUESDAY8" class="DailyLayoutHiddenId"
																		name="vttdayidTUESDAY8" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#tuesday21">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTUESDAY9"></div>
																	<input type="hidden" id="vttdayidTUESDAY9" class="DailyLayoutHiddenId"
																		name="vttdayidTUESDAY9" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#tuesday21">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>

														</div>
													</div>
												</div>

												<div id="wed">
													<div class="timetable day">
														<div class="show_tt" id="viewwed" name="one">
															<span class="viewtt">View Table</span> <i
																class="bi bi-eye"></i>
														</div>
														<div class="week-names">
															<div>wednesday</div>
														</div>
														<div class="time-interval">
															<div>1</div>
															<div>2</div>
															<div>3</div>
															<div>4</div>
															<div>5</div>
															<div>6</div>
															<div>7</div>
															<div>8</div>
															<div>9</div>
														</div>
														<div class="content">
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayWEDNESDAY1">
																	</div>
																	<input type="hidden" id="vttdayidWEDNESDAY1" class="DailyLayoutHiddenId"
																		name="vttdayidWEDNESDAY1" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#wednesday31">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayWEDNESDAY2">
																	</div>
																	<input type="hidden" id="vttdayidWEDNESDAY2" class="DailyLayoutHiddenId"
																		name="vttdayidWEDNESDAY2" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#wednesday31">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayWEDNESDAY3">
																	</div>
																	<input type="hidden" id="vttdayidWEDNESDAY3" class="DailyLayoutHiddenId"
																		name="vttdayidWEDNESDAY3" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#wednesday31">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayWEDNESDAY4">
																	</div>
																	<input type="hidden" id="vttdayidWEDNESDAY4" class="DailyLayoutHiddenId"
																		name="vttdayidWEDNESDAY4" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#wednesday31">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayWEDNESDAY5">
																	</div>
																	<input type="hidden" id="vttdayidWEDNESDAY5" class="DailyLayoutHiddenId"
																		name="vttdayidWEDNESDAY5" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#wednesday31">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayWEDNESDAY6">
																	</div>
																	<input type="hidden" id="vttdayidWEDNESDAY6" class="DailyLayoutHiddenId"
																		name="vttdayidWEDNESDAY6" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#wednesday31">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayWEDNESDAY7">
																	</div>
																	<input type="hidden" id="vttdayidWEDNESDAY7" class="DailyLayoutHiddenId"
																		name="vttdayidWEDNESDAY7" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#wednesday31">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayWEDNESDAY8">
																	</div>
																	<input type="hidden" id="vttdayidWEDNESDAY8" class="DailyLayoutHiddenId"
																		name="vttdayidWEDNESDAY8" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#wednesday31">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayWEDNESDAY9">
																	</div>
																	<input type="hidden" id="vttdayidWEDNESDAY9" class="DailyLayoutHiddenId"
																		name="vttdayidWEDNESDAY9" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#wednesday31">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>

												<div id="thu">
													<div class="timetable day">
														<div class="show_tt" id="viewthu" name="one">
															<span class="viewtt">View Table</span> <i
																class="bi bi-eye"></i>
														</div>
														<div class="week-names">
															<div>thursday</div>
														</div>
														<div class="time-interval">
															<div>1</div>
															<div>2</div>
															<div>3</div>
															<div>4</div>
															<div>5</div>
															<div>6</div>
															<div>7</div>
															<div>8</div>
															<div>9</div>
														</div>
														<div class="content">
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTHURSDAY1">
																	</div>
																	<input type="hidden" id="vttdayidTHURSDAY1" class="DailyLayoutHiddenId"
																		name="vttwdayidTHURSDAY1" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#thursday41">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTHURSDAY2">
																	</div>
																	<input type="hidden" id="vttdayidTHURSDAY2" class="DailyLayoutHiddenId"
																		name="vttdayidTHURSDAY2" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#thursday41">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTHURSDAY3">
																	</div>
																	<input type="hidden" id="vttdayidTHURSDAY3" class="DailyLayoutHiddenId"
																		name="vttdayidTHURSDAY3" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#thursday41">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTHURSDAY4">
																	</div>
																	<input type="hidden" id="vttdayidTHURSDAY4" class="DailyLayoutHiddenId"
																		name="vttdayidTHURSDAY4" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#thursday41">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTHURSDAY5">
																	</div>
																	<input type="hidden" id="vttdayidTHURSDAY5" class="DailyLayoutHiddenId"
																		name="vttdayidTHURSDAY5" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#thursday41">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTHURSDAY6">
																	</div>
																	<input type="hidden" id="vttdayidTHURSDAY6" class="DailyLayoutHiddenId"
																		name="vttdayidTHURSDAY6" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#thursday41">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTHURSDAY7">
																	</div>
																	<input type="hidden" id="vttdayidTHURSDAY7" class="DailyLayoutHiddenId"
																		name="vttdayidTHURSDAY7" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#thursday41">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTHURSDAY8">
																	</div>
																	<input type="hidden" id="vttdayidTHURSDAY8" class="DailyLayoutHiddenId"
																		name="vttdayidTHURSDAY8" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#thursday41">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayTHURSDAY9">
																	</div>
																	<input type="hidden" id="vttdayidTHURSDAY9" class="DailyLayoutHiddenId"
																		name="vttdayidTHURSDAY9" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#thursday41">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>


												<div id="fri">
													<div class="timetable day">
														<div class="show_tt" id="viewfri" name="one">
															<span class="viewtt">View Table</span> <i
																class="bi bi-eye"></i>
														</div>
														<div class="week-names">
															<div>friday</div>
														</div>
														<div class="time-interval">
															<div>1</div>
															<div>2</div>
															<div>3</div>
															<div>4</div>
															<div>5</div>
															<div>6</div>
															<div>7</div>
															<div>8</div>
															<div>9</div>
														</div>
														<div class="content">
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayFRIDAY1"></div>
																	<input type="hidden" id="vttdayidFRIDAY1" class="DailyLayoutHiddenId"
																		name="vttdayidFRIDAY1" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#friday51">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayFRIDAY2"></div>
																	<input type="hidden" id="vttdayidFRIDAY2" class="DailyLayoutHiddenId"
																		name="vttdayidFRIDAY2" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#friday51">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayFRIDAY3"></div>
																	<input type="hidden" id="vttdayidFRIDAY3" class="DailyLayoutHiddenId"
																		name="vttdayidFRIDAY3" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#friday51">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayFRIDAY4"></div>
																	<input type="hidden" id="vttdayidFRIDAY4" class="DailyLayoutHiddenId"
																		name="vttdayidFRIDAY4" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#friday51">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayFRIDAY5"></div>
																	<input type="hidden" id="vttdayidFRIDAY5" class="DailyLayoutHiddenId"
																		name="vttdayidFRIDAY5" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#friday51">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayFRIDAY6"></div>
																	<input type="hidden" id="vttdayidFRIDAY6" class="DailyLayoutHiddenId"
																		name="vttdayidFRIDAY6" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#friday51">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayFRIDAY7"></div>
																	<input type="hidden" id="vttdayidFRIDAY7" class="DailyLayoutHiddenId"
																		name="vttdayidFRIDAY7" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#friday51">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayFRIDAY8"></div>
																	<input type="hidden" id="vttdayidFRIDAY8" class="DailyLayoutHiddenId"
																		name="vttdayidFRIDAY8" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#friday51">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdayFRIDAY9"></div>
																	<input type="hidden" id="vttdayidFRIDAY9" class="DailyLayoutHiddenId"
																		name="vttdayidFRIDAY9" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#friday51">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>

												<div id="sat">
													<div class="timetable day">
														<div class="show_tt" id="viewsat" name="one">
															<span class="viewtt">View Table</span> <i
																class="bi bi-eye"></i>
														</div>
														<div class="week-names">
															<div>saturday</div>
														</div>
														<div class="time-interval">
															<div>1</div>
															<div>2</div>
															<div>3</div>
															<div>4</div>
															<div>5</div>
															<div>6</div>
															<div>7</div>
															<div>8</div>
															<div>9</div>
														</div>
														<div class="content">
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdaySATURDAY1">
																	</div>
																	<input type="hidden" id="vttdayidSATURDAY1" class="DailyLayoutHiddenId"
																		name="vttdayidSATURDAY1" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#saturday61">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdaySATURDAY2">
																	</div>
																	<input type="hidden" id="vttdayidSATURDAY2" class="DailyLayoutHiddenId"
																		name="vttdayidSATURDAY2" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#saturday61">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdaySATURDAY3">
																	</div>
																	<input type="hidden" id="vttdaydSATURDAY3" class="DailyLayoutHiddenId"
																		name="vttdayidSATURDAY3" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#saturday61">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdaySATURDAY4">
																	</div>
																	<input type="hidden" id="vttdayidSATURDAY4" class="DailyLayoutHiddenId"
																		name="vttdayidSATURDAY4" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#saturday61">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdaySATURDAY5">
																	</div>
																	<input type="hidden" id="vttdayidSATURDAY5" class="DailyLayoutHiddenId"
																		name="vttdayidSATURDAY5" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#saturday61">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdaySATURDAY6">
																	</div>
																	<input type="hidden" id="vttdayidSATURDAY6" class="DailyLayoutHiddenId"
																		name="vttdayidSATURDAY6" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#saturday61">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdaySATURDAY7">
																	</div>
																	<input type="hidden" id="vttdayidSATURDAY7" class="DailyLayoutHiddenId"
																		name="vttdayidSATURDAY7" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#saturday61">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdaySATURDAY8">
																	</div>
																	<input type="hidden" id="vttdayidSATURDAY8" class="DailyLayoutHiddenId"
																		name="vttdayidSATURDAY8" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#saturday61">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdaySATURDAY9">
																	</div>
																	<input type="hidden" id="vttdayidSATURDAY9" class="DailyLayoutHiddenId"
																		name="vttdayidSATURDAY9" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#saturday61">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>

												<div id="sun">
													<div class="timetable day">
														<div class="show_tt" id="viewsun" name="one">
															<span class="viewtt">View Table</span> <i
																class="bi bi-eye"></i>
														</div>
														<div class="week-names">
															<div>sunday</div>
														</div>
														<div class="time-interval">
															<div>1</div>
															<div>2</div>
															<div>3</div>
															<div>4</div>
															<div>5</div>
															<div>6</div>
															<div>7</div>
															<div>8</div>
															<div>9</div>
														</div>
														<div class="content">
															<div>
																<div class="row m-0">
																	<div class="fatch_valuedt" id="vttdaySUNDAY1"></div>
																	<input type="hidden" id="vttdayidSUNDAY1" class="DailyLayoutHiddenId"
																		name="vttdayidSUNDAY1" value="0" autocomplete="off" />
																	<div class="col-12">
																		<button type="button"
																			class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																			title="Add" data-bs-toggle="modal"
																			data-bs-target="#sunday71">
																			<i class="lni lni-plus"></i>
																		</button>
																		<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																		</button>
																	</div>
																</div>
															</div>
															<div>
															<div class="row m-0">
																<div class="fatch_valuedt" id="vttdaySUNDAY2"></div>
																<input type="hidden" id="vttdayidSUNDAY2" class="DailyLayoutHiddenId"
																	name="vttdayidSUNDAY2" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#sunday71">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																	</button>
																</div>
																</div>
															</div>
															<div>
																<div class="fatch_valuedt" id="vttdaySUNDAY3"></div>
																<input type="hidden" id="vttdayidSUNDAY3" class="DailyLayoutHiddenId"
																	name="vttdayidSUNDAY3" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#sunday71">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																	</button>
																</div>
															</div>
															<div>
																<div class="fatch_valuedt" id="vttdaySUNDAY4"></div>
																<input type="hidden" id="vttdayidSUNDAY4" class="DailyLayoutHiddenId"
																	name="vttdayidSUNDAY4" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#sunday71">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																	</button>
																</div>
															</div>
															<div>
																<div class="fatch_valuedt" id="vttdaySUNDAY5"></div>
																<input type="hidden" id="vttdayidSUNDAY5" class="DailyLayoutHiddenId"
																	name="vttdayidSUNDAY5" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#sunday71">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																	</button>
																</div>
															</div>
															<div>
																<div class="fatch_valuedt" id="vttdaySUNDAY6"></div>
																<input type="hidden" id="vttdayidSUNDAY6" class="DailyLayoutHiddenId"
																	name="vttdayidSUNDAY6" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#sunday71">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																	</button>
																</div>
															</div>
															<div>
																<div class="fatch_valuedt" id="vttdaySUNDAY7"></div>
																<input type="hidden" id="vttdayidSUNDAY7" class="DailyLayoutHiddenId"
																	name="vttdayidSUNDAY7" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#sunday71">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																	</button>
																</div>
															</div>
															<div>
																<div class="fatch_valuedt" id="vttdaySUNDAY8"></div>
																<input type="hidden" id="vttdayidSUNDAY8" class="DailyLayoutHiddenId"
																	name="vttdayidSUNDAY8" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#sunday71">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																	</button>
																</div>
															</div>
															<div>
																<div class="fatch_valuedt" id="vttdaySUNDAY9"></div>
																<input type="hidden" id="vttdayidSUNDAY9" class="DailyLayoutHiddenId"
																	name="vttdayidSUNDAY9" value="0" autocomplete="off" />
																<div class="col-12">
																	<button type="button"
																		class="main-btn success-btn-outline  btn-hover btn-iconic-icon btnaddmore getLecturedataBT"
																		title="Add" data-bs-toggle="modal"
																		data-bs-target="#sunday71">
																		<i class="lni lni-plus"></i>
																	</button>
																	<button type="button" class="main-btn danger-btn-outline btn-hover btn-iconic-icon btnaddmore getDailyDelLecdataBT"
																			title="Delete" data-bs-toggle="modal">
																			<i class="lni lni-trash-can"></i>
																	</button>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
								
						</div>
						<!-- end card -->
						
						<section class="single-detail-block main_table">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12">
											<div class="card-style mb-30">
												<div
													class="table-wrapper table-responsive custom-datatable-p">
													<table class="table" id="search_timetable">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th id="${item.id}"><h6>Degree</h6></th>
																<th id="${item.id}"><h6>Professional</h6></th>
																<th><h6>Action</h6></th>
															</tr>
															<!-- 						end table row -->
														</thead>
														<tbody>
														</tbody>
													</table>
													<!-- 				end table -->
												</div>
											</div>
											<!-- 		end card -->
										</div>
										<!-- 	end col -->
									</div>
								</section>
						
						<section class="single-detail-block sub_table">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card-style mb-30 ">
							
								<div class="table-wrapper table-responsive custom-table custom-table-v2">
								<h6 class="mb-10">Work Load Calculation</h6>
									<table class="table table-striped">
										<thead>
<!-- 											<tr> -->
<!-- 												<th colspan="5"><h6>Work Load Calculation</h6></th> -->
<!-- 											</tr> -->
											<tr>
												<th><h6>Sr No</h6></th>
												<th><h6>Subject</h6></th>
												<th><h6>Total Lecture Hours</h6></th>
												<th><h6>Planned Lecture Hours</h6></th>
												<th><h6>Remaining Lecture Hours</h6></th>
											</tr>
											<!-- end table row-->
										</thead>
										<tbody id="workLoadcalTbody">

										</tbody>
									</table>
									<!-- end table -->
								</div>
							
							</div>	
							</div>
							</div>
							</section>
					</form:form>
					
<!-- 					<div class="card-style mb-30"> -->
<!-- 						<div class="sub_table"> -->
<!-- 							<div class="table-wrapper table-responsive custom-table b-top"> -->
<!-- 								<table class="table"> -->
<!-- 									<thead> -->
<!-- 									<tr> -->
<!-- 										class="learncount middle-center" -->
<!-- 										<th colspan="5"> -->
<!-- 											<h6>WORK LOAD CALCULATION</h6> -->
<!-- 										</th> -->
<!-- 										<span><label class="bold"></label></span> -->
<!-- 									</tr> -->
<!-- 										<tr> -->
<!-- 											<th><h6>Ser No</h6></th> -->
<!-- 											<th id="th_tb_col_sf_lbl"><h6>SUBJECT</h6></th> -->
<!-- 											<th id="th_tb_col_tf_lbl"><h6>TOTAL LECTURE HOURS</h6></th> -->
<!-- 											<th id="th_tb_col_vf_lbl"><h6>PLANNED</h6></th> -->
<!-- 											<th id="th_tb_col_pf_lbl"><h6>RESULT</h6></th> -->
<!-- 										</tr> -->
<!-- 																				end table row -->
<!-- 									</thead> -->
<!-- 									<tbody id="workLoadcalTbody"> -->
										
<!-- 									</tbody> -->
<!-- 								</table> -->
<!-- 																end table -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
							
					
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade custom-modal" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true" id="monday11">
		<div
			class="modal-dialog modal-md modal-md modal-dialog-centered modal-dialog-scrollable">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h3 class="modal-title">Lecture Details</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="row">
						<form action="/action_page.php">

							<div class="row">
								<div class="col-6 col-sm-6 col-md-6 col-lg-6">
									<div class="input-style-1">
										<label for="appt">Start time<span class="mandatory">*</span></label>
										<input type="time" id="start_time11" name="appt"
											class="form-control">
									</div>
									<!-- end input -->
								</div>

								<div class="col-6 col-sm-6 col-md-6 col-lg-6">
									<div class="input-style-1">
										<label for="appt">End time<span class="mandatory">*</span></label>
										<input type="time" id="end_time11" name="appt"
											class="form-control">
									</div>
									<!-- end input -->
								</div>
							</div>
						</form>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Subject<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="course11" class="getFacultyDtlclass">
										<c:forEach var="item" items="${getCourseList}" varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.course_name}</option>
										</c:forEach>
									</select>
									<%-- <select name="" id="course11"
										class="form-control form-control-lg form-control-a effect-9 select2 narrow wrap"
										class="getFacultyDtlclass">
										<c:forEach var="item" items="${getCourseList}" varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.course_name}</option>
										</c:forEach>
									</select> --%>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Faculty<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="faculty11" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getFacultyList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Classroom<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="classroom11" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getClassroomList}"
											varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.classroom}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Lecture Type<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="lec_type11" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${gettype_of_teachingList}"
											varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.type_of_teaching}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>
						<div class="col-12 col-sm-12 col-md-12 col-lg-12"
							id="update_dtldiv11">
							<input type="hidden" id="hidweek11" name="hidweek11" value="0"
								autocomplete="off" /> <label>Update Details For :<strong
								class="mandatory" class="form-control-sm form-control effect-9 "
								aria-required="true" autocomplete="off">* </strong>
							</label>
							<div class="input-style-form-check">
								<div class="form-check radio-style">
									<input type="radio" id="every_lecture11" name="updtbtlecture11"
										checked class="form-check-input" value="every" required /> <label
										for="male" class="form-check-label">Every Lecture</label>
								</div>
								<div class="form-check radio-style">
									<input type="radio" id="single_lecture11"
										name="updtbtlecture11" class="form-check-input" value="single"
										required /> <label for="female" class="form-check-label">Single
										Lecture</label>
								</div>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-12 col-lg-12">
							<div class="input-style-2 custom-d-none" id="show_date1">
								<label for="username">Select Date<span class="mandatory">*</span></label><input type="text"
									name="select_date11" id="select_date11" maxlength="10"
									class="form-control-sm form-control effect-9 datepickercsp"
									aria-required="true" autocomplete="off"
									placeholder="DD/MM/YYYY">
							</div>
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">

					<ul class="buttons-group">

						<li id="getMonTTdtlli"><a class="main-btn info-btn btn-hover"
							data-bs-dismiss="modal"> Save</a></li>
						<li><a class="main-btn dark-btn n btn-hover" type="button"
							data-bs-dismiss="modal">Close</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade custom-modal" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true" id="tuesday21">
		<div
			class="modal-dialog modal-md modal-dialog-centered modal-dialog-scrollable">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h3 class="modal-title">Lecture Details</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="row">
						<form action="/action_page.php">
							<div class="row">
								<div class="col-6 col-sm-6 col-md-6 col-lg-6">
									<div class="input-style-1">
										<label for="appt">Start time<span class="mandatory">*</span></label>
										<input type="time" id="start_time21" name="appt"
											class="form-control">
									</div>
									<!-- end input -->
								</div>

								<div class="col-6 col-sm-6 col-md-6 col-lg-6">
									<div class="input-style-1">
										<label for="appt">End time<span class="mandatory">*</span></label>
										<input type="time" id="end_time21" name="appt"
											class="form-control">
									</div>
									<!-- end input -->
								</div>
							</div>
						</form>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Subject<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="course21" class="getFacultyDtlclass">
										<c:forEach var="item" items="${getCourseList}" varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.course_name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Faculty<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="faculty21" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getFacultyList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Classroom<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="classroom21" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getClassroomList}"
											varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.classroom}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Lecture Type<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="lec_type21" class="">
										<option value="0">--Select--</option>
									<c:forEach var="item" items="${gettype_of_teachingList}"
											varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.type_of_teaching}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>
						<div class="col-12 col-sm-12 col-md-12 col-lg-12"
							id="update_dtldiv21">
							<input type="hidden" id="hidweek21" name="hidweek21" value="0"
								autocomplete="off" />
							<div class="input-style-form-check">
								<label>Update Details For :<strong class="mandatory"
									class="form-control-sm form-control effect-9 "
									aria-required="true" autocomplete="off">* </strong>
								</label>
								<div class="form-check radio-style">
									<input type="radio" id="every_lecture21" name="updtbtlecture21"
										checked class="form-check-input" value="every" required /> <label
										for="male" class="form-check-label">Every Lecture</label>
								</div>
								<div class="form-check radio-style">
									<input type="radio" id="single_lecture21"
										name="updtbtlecture21" class="form-check-input" value="single"
										required /> <label for="female" class="form-check-label">Single
										Lecture</label>
								</div>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-12 col-lg-12">
							<div class="input-style-2 custom-d-none" id="show_date2">
								<label for="username">Select Date<span class="mandatory">*</span></label> <input type="text"
									name="select_date21" id="select_date21" maxlength="10"
									class="form-control-sm form-control effect-9 datepickercsp "
									aria-required="true" autocomplete="off"
									placeholder="DD/MM/YYYY">
							</div>
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">

					<ul class="buttons-group">

						<li id="getTueTTdtlli"><a class="main-btn info-btn btn-hover"
							data-bs-dismiss="modal"> Save</a></li>
						<li><a class="main-btn dark-btn n btn-hover" type="button"
							data-bs-dismiss="modal">Close</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade custom-modal" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true"
		id="wednesday31">
		<div
			class="modal-dialog modal-md modal-dialog-centered modal-dialog-scrollable">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h3 class="modal-title">Lecture Details</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="row">
						<form action="/action_page.php">
							<div class="row">
								<div class="col-6 col-sm-6 col-md-6 col-lg-6">
									<div class="input-style-1">
										<label for="appt">Start time<span class="mandatory">*</span></label>
										<input type="time" id="start_time31" name="appt"
											class="form-control">
									</div>
									<!-- end input -->
								</div>

								<div class="col-6 col-sm-6 col-md-6 col-lg-6">
									<div class="input-style-1">
										<label for="appt">End time<span class="mandatory">*</span></label>
										<input type="time" id="end_time31" name="appt"
											class="form-control">
									</div>
									<!-- end input -->
								</div>
							</div>
						</form>
						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Subject<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="course31" class="getFacultyDtlclass">
										<c:forEach var="item" items="${getCourseList}" varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.course_name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Faculty<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="faculty31" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getFacultyList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Classroom<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="classroom31" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getClassroomList}"
											varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.classroom}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Lecture Type<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="lec_type31" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${gettype_of_teachingList}"
											varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.type_of_teaching}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>
						<div class="col-12 col-sm-12 col-md-12 col-lg-12"
							id="update_dtldiv31">
							<input type="hidden" id="hidweek31" name="hidweek31" value="0"
								autocomplete="off" />
							<div class="input-style-form-check">
								<label>Update Details For :<strong class="mandatory"
									class="form-control-sm form-control effect-9 "
									aria-required="true" autocomplete="off">* </strong>
								</label>
								<div class="form-check radio-style">
									<input type="radio" id="every_lecture31" name="updtbtlecture31"
										checked class="form-check-input" value="every" required /> <label
										for="male" class="form-check-label">Every Lecture</label>
								</div>
								<div class="form-check radio-style">
									<input type="radio" id="single_lecture31"
										name="updtbtlecture31" class="form-check-input" value="single"
										required /> <label for="female" class="form-check-label">Single
										Lecture</label>
								</div>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-12 col-lg-12">
							<div class="input-style-2 custom-d-none" id="show_date3">
								<label for="username">Select Date<span class="mandatory">*</span></label> <input type="text"
									name="select_date31" id="select_date31" maxlength="10"
									class="form-control-sm form-control effect-9 datepickercsp "
									aria-required="true" autocomplete="off"
									placeholder="DD/MM/YYYY">
							</div>
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">

					<ul class="buttons-group">

						<li id="getWedTTdtlli"><a class="main-btn info-btn btn-hover"
							data-bs-dismiss="modal"> Save</a></li>
						<li><a class="main-btn dark-btn n btn-hover" type="button"
							data-bs-dismiss="modal">Close</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade custom-modal" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true" id="thursday41">
		<div
			class="modal-dialog modal-md modal-dialog-centered modal-dialog-scrollable">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h3 class="modal-title">Lecture Details</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="row">
						<form action="/action_page.php">
							<div class="row">
								<div class="col-6 col-sm-6 col-md-6 col-lg-6">
									<div class="input-style-1">
										<label for="appt">Start time<span class="mandatory">*</span></label>
										<input type="time" id="start_time41" name="appt"
											class="form-control">
									</div>
									<!-- end input -->
								</div>

								<div class="col-6 col-sm-6 col-md-6 col-lg-6">
									<div class="input-style-1">
										<label for="appt">End time<span class="mandatory">*</span></label>
										<input type="time" id="end_time41" name="appt"
											class="form-control">
									</div>
									<!-- end input -->
								</div>
							</div>
						</form>
						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Subject<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="course41" class="getFacultyDtlclass">
										<c:forEach var="item" items="${getCourseList}" varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.course_name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Faculty<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="faculty41" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getFacultyList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Classroom<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="classroom41" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getClassroomList}"
											varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.classroom}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Lecture Type<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="lec_type41" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${gettype_of_teachingList}"
											varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.type_of_teaching}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>
						<div class="col-12 col-sm-12 col-md-12 col-lg-12"
							id="update_dtldiv41">
							<input type="hidden" id="hidweek41" name="hidweek41" value="0"
								autocomplete="off" />
							<div class="input-style-form-check">
								<label>Update Details For :<strong class="mandatory"
									class="form-control-sm form-control effect-9 "
									aria-required="true" autocomplete="off">* </strong>
								</label>
								<div class="form-check radio-style">
									<input type="radio" id="every_lecture41" name="updtbtlecture41"
										checked class="form-check-input" value="every" required /> <label
										for="male" class="form-check-label">Every Lecture</label>
								</div>
								<div class="form-check radio-style">
									<input type="radio" id="single_lecture41"
										name="updtbtlecture41" class="form-check-input" value="single"
										required /> <label for="female" class="form-check-label">Single
										Lecture</label>
								</div>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-12 col-lg-12">
							<div class="input-style-2 custom-d-none" id="show_date4">
								<label for="username">Select Date<span class="mandatory">*</span></label> <input type="text"
									name="select_date41" id="select_date41" maxlength="10"
									class="form-control-sm form-control effect-9 datepickercsp "
									aria-required="true" autocomplete="off"
									placeholder="DD/MM/YYYY">
							</div>
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">

					<ul class="buttons-group">

						<li id="getThuTTdtlli"><a class="main-btn info-btn btn-hover"
							data-bs-dismiss="modal"> Save</a></li>
						<li><a class="main-btn dark-btn n btn-hover" type="button"
							data-bs-dismiss="modal">Close</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade custom-modal" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true" id="friday51">
		<div
			class="modal-dialog modal-md modal-dialog-centered modal-dialog-scrollable">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h3 class="modal-title">Lecture Details</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="row">
						<form action="/action_page.php">
							<div class="row">
								<div class="col-6 col-sm-6 col-md-6 col-lg-6">
									<div class="input-style-1">
										<label for="appt">Start time<span class="mandatory">*</span></label>
										<input type="time" id="start_time51" name="appt"
											class="form-control">
									</div>
									<!-- end input -->
								</div>

								<div class="col-6 col-sm-6 col-md-6 col-lg-6">
									<div class="input-style-1">
										<label for="appt">End time<span class="mandatory">*</span></label>
										<input type="time" id="end_time51" name="appt"
											class="form-control">
									</div>
									<!-- end input -->
								</div>
							</div>
						</form>
						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Subject<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="course51" class="getFacultyDtlclass">
										<c:forEach var="item" items="${getCourseList}" varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.course_name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Faculty<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="faculty51" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getFacultyList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Classroom<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="classroom51" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getClassroomList}"
											varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.classroom}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Lecture Type<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="lec_type51" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${gettype_of_teachingList}"
											varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.type_of_teaching}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>
						<div class="col-12 col-sm-12 col-md-12 col-lg-12"
							id="update_dtldiv51">
							<input type="hidden" id="hidweek51" name="hidweek51" value="0"
								autocomplete="off" />
							<div class="input-style-form-check">
								<label>Update Details For :<strong class="mandatory"
									class="form-control-sm form-control effect-9 "
									aria-required="true" autocomplete="off">* </strong>
								</label>
								<div class="form-check radio-style">
									<input type="radio" id="every_lecture51" name="updtbtlecture51"
										checked class="form-check-input" value="every" required /> <label
										for="male" class="form-check-label">Every Lecture</label>
								</div>
								<div class="form-check radio-style">
									<input type="radio" id="single_lecture51"
										name="updtbtlecture51" class="form-check-input" value="single"
										required /> <label for="female" class="form-check-label">Single
										Lecture</label>
								</div>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-12 col-lg-12">
							<div class="input-style-2 custom-d-none" id="show_date5">
								<label for="username">Select Date<span class="mandatory">*</span></label> <input type="text"
									name="select_date51" id="select_date51" maxlength="10"
									class="form-control-sm form-control effect-9 datepickercsp "
									aria-required="true" autocomplete="off"
									placeholder="DD/MM/YYYY">
							</div>
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">

					<ul class="buttons-group">

						<li id="getFriTTdtlli"><a class="main-btn info-btn btn-hover"
							data-bs-dismiss="modal"> Save</a></li>
						<li><a class="main-btn dark-btn n btn-hover" type="button"
							data-bs-dismiss="modal">Close</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade custom-modal" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true" id="saturday61">
		<div
			class="modal-dialog modal-md modal-dialog-centered modal-dialog-scrollable">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h3 class="modal-title">Lecture Details</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="row">
						<form action="/action_page.php">
							<div class="row">
								<div class="col-6 col-sm-6 col-md-6 col-lg-6">
									<div class="input-style-1">
										<label for="appt">Start time<span class="mandatory">*</span></label>
										<input type="time" id="start_time61" name="appt"
											class="form-control">
									</div>
									<!-- end input -->
								</div>

								<div class="col-6 col-sm-6 col-md-6 col-lg-6">
									<div class="input-style-1">
										<label for="appt">End time<span class="mandatory">*</span></label>
										<input type="time" id="end_time61" name="appt"
											class="form-control">
									</div>
									<!-- end input -->
								</div>
							</div>
						</form>
						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Subject<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="course61" class="getFacultyDtlclass">
										<c:forEach var="item" items="${getCourseList}" varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.course_name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Faculty<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="faculty61" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getFacultyList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Classroom<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="classroom61" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getClassroomList}"
											varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.classroom}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Lecture Type<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="lec_type61" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${gettype_of_teachingList}"
											varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.type_of_teaching}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>
						<div class="col-12 col-sm-12 col-md-12 col-lg-12"
							id="update_dtldiv61">
							<input type="hidden" id="hidweek61" name="hidweek61" value="0"
								autocomplete="off" />
							<div class="input-style-form-check">
								<label>Update Details For :<strong class="mandatory"
									class="form-control-sm form-control effect-9 "
									aria-required="true" autocomplete="off">* </strong>
								</label>
								<div class="form-check radio-style">
									<input type="radio" id="every_lecture61" name="updtbtlecture61"
										checked class="form-check-input" value="every" required /> <label
										for="male" class="form-check-label">Every Lecture</label>
								</div>
								<div class="form-check radio-style">
									<input type="radio" id="single_lecture61"
										name="updtbtlecture61" class="form-check-input" value="single"
										required /> <label for="female" class="form-check-label">Single
										Lecture</label>
								</div>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-12 col-lg-12">
							<div class="input-style-2 custom-d-none" id="show_date6">
								<label for="username">Select Date<span class="mandatory">*</span></label> <input type="text"
									name="select_date61" id="select_date61" maxlength="10"
									class="form-control-sm form-control effect-9 datepickercsp "
									aria-required="true" autocomplete="off"
									placeholder="DD/MM/YYYY">
							</div>
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">

					<ul class="buttons-group">

						<li id="getSatTTdtlli"><a class="main-btn info-btn btn-hover"
							data-bs-dismiss="modal"> Save</a></li>
						<li><a class="main-btn dark-btn n btn-hover" type="button"
							data-bs-dismiss="modal">Close</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade custom-modal" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true" id="sunday71">
		<div
			class="modal-dialog modal-md modal-dialog-centered modal-dialog-scrollable">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h3 class="modal-title">Lecture Details</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="row">
						<form action="/action_page.php">
							<div class="row">
								<div class="col-6 col-sm-6 col-md-6 col-lg-6">
									<div class="input-style-1">
										<label for="appt">Start time<span class="mandatory">*</span></label>
										<input type="time" id="start_time71" name="appt"
											class="form-control">
									</div>
									<!-- end input -->
								</div>

								<div class="col-6 col-sm-6 col-md-6 col-lg-6">
									<div class="input-style-1">
										<label for="appt">End time<span class="mandatory">*</span></label>
										<input type="time" id="end_time71" name="appt"
											class="form-control">
									</div>
									<!-- end input -->
								</div>
							</div>
						</form>
						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Subject<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="course71" class="getFacultyDtlclass">
										<c:forEach var="item" items="${getCourseList}" varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.course_name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Faculty<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="faculty71" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getFacultyList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Classroom<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="classroom71" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getClassroomList}"
											varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.classroom}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Lecture Type<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="" id="lec_type71" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${gettype_of_teachingList}"
											varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.type_of_teaching}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>
						<div class="col-12 col-sm-12 col-md-12 col-lg-12"
							id="update_dtldiv71">
							<input type="hidden" id="hidweek71" name="hidweek71" value="0"
								autocomplete="off" />
							<div class="input-style-form-check">
								<label>Update Details For :<strong class="mandatory"
									class="form-control-sm form-control effect-9 "
									aria-required="true" autocomplete="off">* </strong>
								</label>
								<div class="form-check radio-style">
									<input type="radio" id="every_lecture71" name="updtbtlecture71"
										checked class="form-check-input" value="every" required /> <label
										for="male" class="form-check-label">Every Lecture</label>
								</div>
								<div class="form-check radio-style">
									<input type="radio" id="single_lecture71"
										name="updtbtlecture71" class="form-check-input" value="single"
										required /> <label for="female" class="form-check-label">Single
										Lecture</label>
								</div>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-12 col-lg-12">
							<div class="input-style-2 custom-d-none" id="show_date7">
								<label for="username">Select Date<span class="mandatory">*</span></label> <input type="text"
									name="select_date71" id="select_date71" maxlength="10"
									class="form-control-sm form-control effect-9 datepickercsp "
									aria-required="true" autocomplete="off"
									placeholder="DD/MM/YYYY">
							</div>
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">

					<ul class="buttons-group">

						<li id="getSunTTdtlli"><a class="main-btn info-btn btn-hover"
							data-bs-dismiss="modal"> Save</a></li>
						<li><a class="main-btn dark-btn n btn-hover" type="button"
							data-bs-dismiss="modal">Close</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade custom-modal" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true" id="ex_class">
		<div
			class="modal-dialog modal-md modal-dialog-centered modal-dialog-scrollable">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h3 class="modal-title">Periodic Exam/Extra Class Details</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
		
				<div >
					<div class="inst_block simple-instruction">
						<strong>Instruction :</strong> Please Select the Options.
					</div>
				</div>				

				<!-- Modal body -->
				<div class="modal-body">
					<div class="row">
						<form action="/action_page.php">
							<div class="col-12 col-sm-12 col-md-12 col-lg-12">
								<input type="hidden" id="hidweek81" name="hidweek81" value="0"
									autocomplete="off" />
									
								<div class="input-style-form-check">
									<label>Choice :<strong class="mandatory"
										class="form-control-sm form-control effect-9 "
										aria-required="true" autocomplete="off">* </strong>
									</label>
									<div class="form-check radio-style">
										<input type="radio" id="periodic_exam"
											name="choice" checked class="form-check-input"
											value="periodic_exam" required /> <label for="periodic_exam"
											class="form-check-label">Periodic Exam</label>
									</div>
									<div class="form-check radio-style">
										<input type="radio" id="extra_class"
											name="choice" class="form-check-input"
											value="extra_class" required /> <label for="extra_class"
											class="form-check-label">Extra Class</label>
									</div>
								</div>
								<div class="input-style-form-check Ex_Class custom-d-none">
									<label>Extra Class For :<strong class="mandatory"
										class="form-control-sm form-control effect-9 "
										aria-required="true" autocomplete="off">* </strong>
									</label>
									<div class="form-check radio-style">
										<input type="radio" id="single_lecture81"
											name="updtbtlecture81" checked class="form-check-input"
											value="single_day" required /> <label for="single_day"
											class="form-check-label">Single Day</label>
									</div>
									<div class="form-check radio-style">
										<input type="radio" id="every_lecture81"
											name="updtbtlecture81" class="form-check-input"
											value="every_day" required /> <label for="every_day"
											class="form-check-label">Every Day</label>
									</div>
								</div>
							</div>
							
							<div class="col-12 col-sm-12 col-md-12 col-lg-12">
								<div class="input-style-2" id="show_date8"">
									<label for="username">Select Date<span class="mandatory">*</span></label> <input type="text"
										name="select_date81" id="select_date81" maxlength="10"
										class="form-control-sm form-control effect-9 datepickercsp "
										aria-required="true" autocomplete="off"
										placeholder="DD/MM/YYYY">
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1 custom-d-none" id="show_day">
									<label for="username">Day<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="day" id="day">
											<option value="0">Select</option>
											<option value="monday">Monday</option>
											<option value="tuesday">Tuesday</option>
											<option value="wednesday">Wednesday</option>
											<option value="thursday">Thursday</option>
											<option value="friday">Friday</option>
											<option value="saturday">Saturday</option>
											<option value="sunday">Sunday</option>
										</select>

									</div>
								</div>
								<div class="input-style-2 mt-3">
									<input type="hidden" id="id" name="id" value="0"
										autocomplete="off" />
								</div>

								<!-- end select -->
							</div>
							<div class="row">
								<div class="col-6 col-sm-6 col-md-6 col-lg-6">
									<div class="input-style-1">
										<label for="appt">Start time<span class="mandatory">*</span></label>
										<input type="time" id="start_time81" name="appt"
											class="form-control">
									</div>
									<!-- end input -->
								</div>

								<div class="col-6 col-sm-6 col-md-6 col-lg-6">
									<div class="input-style-1">
										<label for="appt">End time<span class="mandatory">*</span></label>
										<input type="time" id="end_time81" name="appt"
											class="form-control">
									</div>
									<!-- end input -->
								</div>
							</div>
						</form>
						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Subject<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="course81" id="course81"
										class="getFacultyDtlclass">
										<c:forEach var="item" items="${getCourseList}" varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.course_name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Faculty<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="faculty81" id="faculty81" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getFacultyList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12">
							<div class="select-style-1">
								<label>Classroom<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="classroom81" id="classroom81" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getClassroomList}"
											varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.classroom}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>

						<div class="col-6 col-sm-6 col-md-6 col-lg-12 Lec_Type custom-d-none">
							<div class="select-style-1">
								<label>Lecture Type<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="lec_type81" id="lec_type81" class="">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${gettype_of_teachingList}"
											varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.type_of_teaching}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- end select -->
						</div>
						<div id="Exam_Type">
						<div class="col-6 col-sm-6 col-md-6 col-lg-12"
										id="exam_type_hid">
										<div class="select-style-1">
											<label for="text-input">Exam Type<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="exam_type" id="exam_type" class="form-control form-control-lg">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getExam_Type}"
														varStatus="num">
														<c:if test="${item[0] == 8}">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:if>
														
													</c:forEach>
												</select>
											</div>
										</div>
						</div>
						<div class="col-6 col-sm-6 col-md-6 col-lg-12"
										id="exam_serial_hid">
										<div class="select-style-1">
											<label for="text-input">Exam Serial<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="exam_serial" id=exam_serial class="form-control form-control-lg">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getExam_SerialList}"
														varStatus="num">
														<option value="${item.id}" name="${item.exam_serial}">${item.exam_serial}</option>
													</c:forEach>
												</select>
											</div>
										</div>
						</div>
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">

					<ul class="buttons-group">
						<li id="exbtn_save"><a class="main-btn info-btn btn-hover"
							data-bs-dismiss="modal"> Save</a></li>
						<li id="exbtn_update" class="custom-d-none"><a
							class="main-btn deactive-btn btn-hover btn-update" type="button"
							data-bs-dismiss="modal">Update</a></li>
						<li><a class="main-btn dark-btn n btn-hover" type="button"
							data-bs-dismiss="modal">Close</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- ----------------------modal--------------------------- -->


<div id="ttview">
	<div class="modal-background">

		<div class="modal">

			<button type="button" class="btn-close" data-bs-dismiss="modal"
				aria-label="Close"></button>


			<div class="ttheading">View Your Time Table</div>
			<div class="info-text">
				<span class="">FN = Faculty Name ,</span> <span class="">CN =
					Course Name ,</span> <span class="">CL = Class Room</span>
			</div>

			<div id="select-week">
				<ul class="buttons-group nextpre-week">
					<li id="lastweekli"><a href="#" class="main-btn btn-hover pre"><i
							class="lni lni-chevron-left"></i></a></li>
					<li><a class="input-style-2 m-0"> <input type="text"
							id="weekrange" name="weekrange" class="form-control" readonly
							value="1/9/2022 - 7/9/2022">
					</a></li>
					<li id="nextweekli"><a href="#"
						class="main-btn  btn-hover next"><i
							class="lni lni-chevron-right"></i></a></li>
				</ul>
			</div>

			<div class="table-responsive">

				<div class="timetable weekly" id="weeklytimetable">
					<div class="week-names">
						<div>monday</div>
						<div>tuesday</div>
						<div>wednesday</div>
						<div>thursday</div>
						<div>friday</div>
						<div class="">saturday</div>
						<div class="weekend">sunday</div>
					</div>
					<div class="time-interval">
						<div>1</div>
						<div>2</div>
						<div>3</div>
						<div>4</div>
						<div>5</div>
						<div>6</div>
						<div>7</div>
						<div>8</div>
						<div>9</div>
					</div>
					<div class="content">
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekMONDAY1"></div>
							</div>
						</div>

						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTUESDAY1"></div>
								<!--       <div class="accent-orange-gradient"></div> -->
							</div>
							<!--       <div class="accent-red-gradient"></div> -->
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekWEDNESDAY1"></div>
								<!--       <div class="accent-orange-gradient"></div> -->
							</div>
							<!--       <div class="accent-pink-gradient"></div> -->
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTHURSDAY1"></div>
								<!--       <div class="accent-orange-gradient"></div> -->
							</div>
							<!--       <div class="accent-purple-gradient"></div> -->
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekFRIDAY1"></div>
								<!--       <div class="accent-orange-gradient"></div> -->
							</div>
							<!--       <div class="accent-cyan-gradient"></div> -->
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekSATURDAY1"></div>
								<!--       <div class="accent-orange-gradient"></div> -->
							</div>
						</div>

						<div class="weekend">
							<div class="row m-0">
								<div class="fatch_value" id="vweekSUNDAY1"></div>
								<!--       <div class="accent-orange-gradient"></div> -->
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekMONDAY2"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTUESDAY2"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekWEDNESDAY2"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTHURSDAY2"></div>
							</div>
							<!--       <div class="accent-cyan-gradient"></div> -->
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekFRIDAY2"></div>
							</div>
						</div>
						<div class="">
							<div class="row m-0">
								<div class="fatch_value" id="vweekSATURDAY2"></div>
							</div>
						</div>
						<div class="weekend">
							<div class="row m-0">
								<div class="fatch_value" id="vweekSUNDAY2"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekMONDAY3"></div>
							</div>
							<!--       <div class="accent-pink-gradient"></div> -->
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTUESDAY3"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekWEDNESDAY3"></div>
							</div>
							<!--       <div class="accent-purple-gradient"></div> -->
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTHURSDAY3"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekFRIDAY3"></div>
							</div>
							<!--     <div class="accent-green-gradient"></div> -->
						</div>
						<div class="">
							<div class="row m-0">
								<div class="fatch_value" id="vweekSATURDAY3"></div>
							</div>
						</div>
						<div class="weekend">
							<div class="row m-0">
								<div class="fatch_value" id="vweekSUNDAY3"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekMONDAY4"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTUESDAY4"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekWEDNESDAY4"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTHURSDAY4"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekFRIDAY4"></div>
							</div>
						</div>
						<div class="">
							<div class="row m-0">
								<div class="fatch_value" id="vweekSATURDAY4"></div>
							</div>
						</div>
						<div class="weekend">
							<div class="row m-0">
								<div class="fatch_value" id="vweekSUNDAY4"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekMONDAY5"></div>
							</div>
							<!--       <div class="accent-purple-gradient"></div> -->
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTUESDAY5"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekWEDNESDAY5"></div>
							</div>
							<!--     <div class="accent-orange-gradient"></div> -->
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTHURSDAY5"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekFRIDAY5"></div>
							</div>
							<!--     <div class="accent-cyan-gradient"></div> -->
						</div>
						<div class="">
							<div class="row m-0">
								<div class="fatch_value" id="vweekSATURDAY5"></div>
							</div>
						</div>
						<div class="weekend">
							<div class="row m-0">
								<div class="fatch_value" id="vweekSUNDAY5"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekMONDAY6"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTUESDAY6"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekWEDNESDAY6"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTHURSDAY6"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekFRIDAY6"></div>
							</div>
						</div>
						<div class="">
							<div class="row m-0">
								<div class="fatch_value" id="vweekSATURDAY6"></div>
							</div>
						</div>
						<div class="weekend">
							<div class="row m-0">
								<div class="fatch_value" id="vweekSUNDAY6"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekMONDAY7"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTUESDAY7"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekWEDNESDAY7"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTHURSDAY7"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekFRIDAY7"></div>
							</div>
						</div>
						<div class="">
							<div class="row m-0">
								<div class="fatch_value" id="vweekSATURDAY7"></div>
							</div>
						</div>
						<div class="weekend">
							<div class="row m-0">
								<div class="fatch_value" id="vweekSUNDAY7"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekMONDAY8"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTUESDAY8"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekWEDNESDAY8"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTHURSDAY8"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekFRIDAY8"></div>
							</div>
						</div>
						<div class="">
							<div class="row m-0">
								<div class="fatch_value" id="vweekSATURDAY8"></div>
							</div>
						</div>
						<div class="weekend">
							<div class="row m-0">
								<div class="fatch_value" id="vweekSUNDAY8"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekMONDAY9"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTUESDAY9"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekWEDNESDAY9"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekTHURSDAY9"></div>
							</div>
						</div>
						<div>
							<div class="row m-0">
								<div class="fatch_value" id="vweekFRIDAY9"></div>
							</div>
						</div>
						<div class="">
							<div class="row m-0">
								<div class="fatch_value" id="vweekSATURDAY9"></div>
							</div>
						</div>
						<div class="weekend">
							<div class="row m-0">
								<div class="fatch_value" id="vweekSUNDAY9"></div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="mondaytimetable">
				<div id="select-day">
					<div
						class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12  mx-auto">
						<div class="input-style-2 tt-date">
							<label for="username">Select Date</label> <input type="text"
								name="select_date1" id="select_date1" maxlength="10"
								class="form-control-sm form-control effect-9 datepickercsp select_date_class "
								aria-required="true" autocomplete="off" placeholder="DD/MM/YYYY">
						</div>
					</div>
				</div>
				<div class="table-responsive">
					<div class="timetable day">
						<!-- <div class="show_tt" id="one">View Table -->
						<!-- </div> -->
						<div class="week-names">
							<div>monday</div>
						</div>
						<div class="time-interval">
							<div>1</div>
							<div>2</div>
							<div>3</div>
							<div>4</div>
							<div>5</div>
							<div>6</div>
							<div>7</div>
							<div>8</div>
							<div>9</div>
						</div>
						<div class="content">
							<div>
								<div class="row m-0">
									<div class="fatch_value" id="vdayMONDAY1"></div>
								</div>
							</div>
							<div>
								<div class="fatch_value" id="vdayMONDAY2"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayMONDAY3"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayMONDAY4"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayMONDAY5"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayMONDAY6"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayMONDAY7"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayMONDAY8"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayMONDAY9"></div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="tuesdaytimetable">
				<div id="select-day">
					<div
						class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12  mx-auto">
						<div class="input-style-2 tt-date">
							<label for="username">Select Date</label> <input type="text"
								name="select_date2" id="select_date2" maxlength="10"
								class="form-control-sm form-control effect-9 datepickercsp select_date_class "
								aria-required="true" autocomplete="off" placeholder="DD/MM/YYYY">
						</div>
					</div>
				</div>

				<div class="table-responsive">
					<div class="timetable day">
						<!-- <div class="show_tt" id="one">View Table -->
						<!-- </div> -->
						<div class="week-names">
							<div>tuesday</div>
						</div>
						<div class="time-interval">
							<div>1</div>
							<div>2</div>
							<div>3</div>
							<div>4</div>
							<div>5</div>
							<div>6</div>
							<div>7</div>
							<div>8</div>
							<div>9</div>
						</div>
						<div class="content">
							<div>
								<div class="row m-0">
									<div class="fatch_value" id="vdayTUESDAY1"></div>
								</div>
							</div>
							<div>
								<div class="fatch_value" id="vdayTUESDAY2"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayTUESDAY3"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayTUESDAY4"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayTUESDAY5"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayTUESDAY6"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayTUESDAY7"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayTUESDAY8"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayTUESDAY9"></div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="wednesdaytimetable">
				<div id="select-day">
					<div
						class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12  mx-auto">
						<div class="input-style-2 tt-date">
							<label for="username">Select Date</label> <input type="text"
								name="select_date3" id="select_date3" maxlength="10"
								class="form-control-sm form-control effect-9 datepickercsp select_date_class "
								aria-required="true" autocomplete="off" placeholder="DD/MM/YYYY">
						</div>
					</div>
				</div>
				<div class="table-responsive">
					<div class="timetable day">
						<!-- <div class="show_tt" id="one">View Table -->
						<!-- </div> -->
						<div class="week-names">
							<div>wednesday</div>
						</div>
						<div class="time-interval">
							<div>1</div>
							<div>2</div>
							<div>3</div>
							<div>4</div>
							<div>5</div>
							<div>6</div>
							<div>7</div>
							<div>8</div>
							<div>9</div>
						</div>
						<div class="content">
							<div>
								<div class="row m-0">
									<div class="fatch_value" id="vdayWEDNESDAY1"></div>
								</div>
							</div>
							<div>
								<div class="fatch_value" id="vdayWEDNESDAY2"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayWEDNESDAY3"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayWEDNESDAY4"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayWEDNESDAY5"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayWEDNESDAY6"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayWEDNESDAY7"></div>
							</div>
							v<div>
								<div class="fatch_value" id="vdayWEDNESDAY8"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayWEDNESDAY9"></div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="thursdaytimetable">
				<div id="select-day">
					<div
						class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12  mx-auto">
						<div class="input-style-2 tt-date">
							<label for="username">Select Date</label> <input type="text"
								name="select_date4" id="select_date4" maxlength="10"
								class="form-control-sm form-control effect-9 datepickercsp select_date_class "
								aria-required="true" autocomplete="off" placeholder="DD/MM/YYYY">
						</div>
					</div>
				</div>

				<div class="table-responsive">
					<div class="timetable day">
						<!-- <div class="show_tt" id="one">View Table -->
						<!-- </div> -->
						<div class="week-names">
							<div>thursday</div>
						</div>
						<div class="time-interval">
							<div>1</div>
							<div>2</div>
							<div>3</div>
							<div>4</div>
							<div>5</div>
							<div>6</div>
							<div>7</div>
							<div>8</div>
							<div>9</div>
						</div>
						<div class="content">
							<!-- 							<div> -->
							<div>
								<div class="fatch_value" id="vdayTHURSDAY1"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayTHURSDAY2"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayTHURSDAY3"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayTHURSDAY4"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayTHURSDAY5"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayTHURSDAY6"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayTHURSDAY7"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayTHURSDAY8"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayTHURSDAY9"></div>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div id="fridaytimetable">
				<div id="select-day">
					<div
						class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12  mx-auto">
						<div class="input-style-2 tt-date">
							<label for="username">Select Date</label> <input type="text"
								name="select_date5" id="select_date5" maxlength="10"
								class="form-control-sm form-control effect-9 datepickercsp select_date_class "
								aria-required="true" autocomplete="off" placeholder="DD/MM/YYYY">
						</div>
					</div>
				</div>

				<div class="table-responsive">
					<div class="timetable day">
						<!-- <div class="show_tt" id="one">View Table -->
						<!-- </div> -->
						<div class="week-names">
							<div>friday</div>
						</div>
						<div class="time-interval">
							<div>1</div>
							<div>2</div>
							<div>3</div>
							<div>4</div>
							<div>5</div>
							<div>6</div>
							<div>7</div>
							<div>8</div>
							<div>9</div>
						</div>
						<div class="content">
							<div>
								<div class="row m-0">
									<div class="fatch_value" id="vdayFRIDAY1"></div>
								</div>
							</div>
							<div>
								<div class="fatch_value" id="vdayFRIDAY2"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayFRIDAY3"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayFRIDAY4"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayFRIDAY5"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayFRIDAY6"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayFRIDAY7"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayFRIDAY8"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdayFRIDAY9"></div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="saturdaytimetable">
				<div id="select-day">
					<div
						class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12  mx-auto">
						<div class="input-style-2 tt-date">
							<label for="username">Select Date</label> <input type="text"
								name="select_date6" id="select_date6" maxlength="10"
								class="form-control-sm form-control effect-9 datepickercsp select_date_class "
								aria-required="true" autocomplete="off" placeholder="DD/MM/YYYY">
						</div>
					</div>
				</div>

				<div class="table-responsive">
					<div class="timetable day">
						<!-- <div class="show_tt" id="one">View Table -->
						<!-- </div> -->
						<div class="week-names">
							<div>saturday</div>
						</div>
						<div class="time-interval">
							<div>1</div>
							<div>2</div>
							<div>3</div>
							<div>4</div>
							<div>5</div>
							<div>6</div>
							<div>7</div>
							<div>8</div>
							<div>9</div>
						</div>
						<div class="content">
							<!-- 							<div> -->
							<div>
								<div class="fatch_value" id="vdaySATURDAY1"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdaySATURDAY2"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdaySATURDAY3"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdaySATURDAY4"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdaySATURDAY5"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdaySATURDAY6"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdaySATURDAY7"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdaySATURDAY8"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdaySATURDAY9"></div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="sundaytimetable">
				<div id="select-day">
					<div
						class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12  mx-auto">
						<div class="input-style-2 tt-date">
							<label for="username">Select Date</label> <input type="text"
								name="select_date7" id="select_date7" maxlength="10"
								class="form-control-sm form-control effect-9 datepickercsp select_date_class "
								aria-required="true" autocomplete="off" placeholder="DD/MM/YYYY">
						</div>
					</div>
				</div>
				<div class="table-responsive">
					<div class="timetable day">
						<!-- <div class="show_tt" id="one">View Table -->
						<!-- </div> -->
						<div class="week-names">
							<div>sunday</div>
						</div>
						<div class="time-interval">
							<div>1</div>
							<div>2</div>
							<div>3</div>
							<div>4</div>
							<div>5</div>
							<div>6</div>
							<div>7</div>
							<div>8</div>
							<div>9</div>
						</div>
						<div class="content">
							<div>
								<div class="row m-0">
									<div class="fatch_value" id="vdaySUNDAY1"></div>
								</div>
							</div>
							<div>
								<div class="fatch_value" id="vdaySUNDAY2"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdaySUNDAY3"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdaySUNDAY4"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdaySUNDAY5"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdaySUNDAY6"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdaySUNDAY7"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdaySUNDAY8"></div>
							</div>
							<div>
								<div class="fatch_value" id="vdaySUNDAY9"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<c:url value="TTDelete_Url2" var="TTDelete_Url2" />
<form:form action="${TTDelete_Url2}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id7">
	<input type="hidden" name="id7" id="id7" value="0" />
</form:form>
<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {	
	$.ajaxSetup({
		async : false
	});
	mockjax1('search_timetable');
	table = dataTable('search_timetable');
// 	$('#BTN-RELOAD').ON('CLICK', FUNCTION() {
// 		TABLE.AJAX.RELOAD();
// 	});
	datepicketDate('ldate');
	$( "#ldate").datepicker( "option", "maxDate", null);
	datepicketDate('select_date11');
	$( "#select_date11").datepicker( "option", "maxDate", null);
	datepicketDate('select_date21');
	$( "#select_date21").datepicker( "option", "maxDate", null);
	datepicketDate('select_date31');
	$( "#select_date31").datepicker( "option", "maxDate", null);
	datepicketDate('select_date41');
	$( "#select_date41").datepicker( "option", "maxDate", null);
	datepicketDate('select_date51');
	$( "#select_date51").datepicker( "option", "maxDate", null);
	datepicketDate('select_date61');
	$( "#select_date61").datepicker( "option", "maxDate", null);
	datepicketDate('select_date71');
	$( "#select_date71").datepicker( "option", "maxDate", null);
	datepicketDate('select_date81');
	$( "#select_date81").datepicker( "option", "maxDate", null);
	datepicketDate('select_date1');
	$( "#select_date1").datepicker( "option", "maxDate", null);
	datepicketDate('select_date2');
	$( "#select_date2").datepicker( "option", "maxDate", null);
	datepicketDate('select_date3');
	$( "#select_date3").datepicker( "option", "maxDate", null);
	datepicketDate('select_date4');
	$( "#select_date4").datepicker( "option", "maxDate", null);
	datepicketDate('select_date5');
	$( "#select_date5").datepicker( "option", "maxDate", null);
	datepicketDate('select_date6');
	$( "#select_date6").datepicker( "option", "maxDate", null);
	datepicketDate('select_date7');
	$( "#select_date7").datepicker( "option", "maxDate", null);
	datepicketDate('select_date8');
	$( "#select_date8").datepicker( "option", "maxDate", null);
	
	$(".inst_hide").show();
	$(".inst1_hide").hide();
	$(".main_table").show();
	$(".sub_table").hide();
	
	$(".fatch_value").each(function(){
	    $(this).html("");
	    
	  });
	
// 	alert(getMonday(new Date())); // Mon Nov 08 2010
	var today_date = formatedate(getMonday(new Date()))
	newdate = new Date()
	
	$("#select_date1").val(today_date);
	newdate.setDate(getMonday(new Date()).getDate()+1);
	$("#select_date2").val(formatedate(newdate));
	newdate.setDate(getMonday(new Date()).getDate()+2);
	$("#select_date3").val(formatedate(newdate));
	newdate.setDate(getMonday(new Date()).getDate()+3);
	$("#select_date4").val(formatedate(newdate));
	newdate.setDate(getMonday(new Date()).getDate()+4);
	$("#select_date5").val(formatedate(newdate));
	newdate.setDate(getMonday(new Date()).getDate()+5);
	$("#select_date6").val(formatedate(newdate));
	newdate.setDate(getMonday(new Date()).getDate()+6);
	$("#select_date7").val(formatedate(newdate));
	$("#select_date8").val(today_date);
	
	lastweek();
	nextweek();
	
	getAprrovedDegree('${system[0][1]}');
	

});

function workLoadCalFn(){
	var degree = $("#degree").val();
	var prof = $("#professional").val();
	$("tbody#workLoadcalTbody").empty();
	
	$.post("getWorkLoadCalData?" + key + "=" + value, {
		degree : degree,
		prof : prof
		}, function(j) {

			if(j.length > 0){
				var trtd = '';
				for(var i=0; i<j.length; i++){
					trtd += '<tr><td><p>'+(i+1)+'</p></td>'
					+'<td><p>'+j[i][0]+'</p></td>'
					+'<td><p>'+j[i][1]+'</p></td>';
					var hours = getAddedHoursbyCourse(j[i][2]);
					if(hours != ""){
						var diff = parseInt(j[i][1]) - parseInt(hours);
						trtd += '<td><p>'+hours+'</p></td>'
						+'<td><p>'+diff+'</p></td>';
					}
					if(hours == ""){
						trtd += '<td><p>N/A</p></td>'
						+'<td><p>N/A</p></td>';
					}
					
				}
				$("tbody#workLoadcalTbody").append(trtd);
			}else{
				$("tbody#workLoadcalTbody").append('<tr><td colspan="5"><p class="no-data">Data Not Available</p></td></tr>');
			}
			
		
	});
	
}

function getAddedHoursbyCourse(course_id){
	var hours = "";
	$.post("getAddedHoursbyCourse?" + key + "=" + value, {
		course_id : course_id
		}, function(j) {
			hours = j;
	});
	return hours;
}

function getMonday(d) {
	  d = new Date(d);
	  var day = d.getDay(),
	      diff = d.getDate() - day + (day == 0 ? -6:1); // adjust when day is sunday
	  return new Date(d.setDate(diff));
	}
var d = getMonday(new Date());
	
	$(".create_tt").hide();
	$(".main_table").show();
	$(".sub_table").hide();
	
	function lastweek(){
		d.setDate(d.getDate() - 7);
		var d1 = formatedate(d);
		d.setDate(d.getDate() + 6);
		var d2 = formatedate(d);
		$("#weekrange").val(d1+" - "+d2);
		d.setDate(d.getDate() - 6);
		getweeklytimetable();
	}
	function nextweek(){
		d.setDate(d.getDate() + 7);
		var d1 = formatedate(d);
		d.setDate(d.getDate() + 6);
		var d2 = formatedate(d);
		$("#weekrange").val(d1+" - "+d2);
		d.setDate(d.getDate() - 6);
		getweeklytimetable();
	}
	function formatedate(dt) {
		return (dt.getDate()+"/"+(dt.getMonth()+1)+"/"+dt.getFullYear());
	}
	function EveryDay1(){		
		$("#show_date1").hide();	
	}
	function SingleDay1(){
		$("#show_date1").show();
	}
	function EveryDay2(){		
		$("#show_date2").hide();	
	}
	function SingleDay2(){
		$("#show_date2").show();
	}
	function EveryDay3(){		
		$("#show_date3").hide();	
	}
	function SingleDay3(){
		$("#show_date3").show();
	}
	function EveryDay4(){		
		$("#show_date4").hide();	
	}	
	function SingleDay4(){
		$("#show_date4").show();
	}
	function EveryDay5(){		
		$("#show_date5").hide();	
	}
	function SingleDay5(){
		$("#show_date5").show();
	}
	function EveryDay6(){		
		$("#show_date6").hide();	
	}
	function SingleDay6(){
		$("#show_date6").show();
	}
	function EveryDay7(){		
		$("#show_date7").hide();	
	}
	function SingleDay7(){
		$("#show_date7").show();
	}
	function EveryDay8(){		
		$("#show_day").show();
		$("#show_date8").hide();
	}
	function SingleDay8(){
		$("#show_date8").show();
		$("#show_day").hide();
	}
	function Per_Exam(){
		$(".Lec_Type").hide();
		$("#lec_type81").val("0");
		$(".Ex_Class").hide();
		$("#Exam_Type").show();
	}
	function Ex_List(){
		$(".Lec_Type").show();
		$(".Ex_Class").show();
		$("#Exam_Type").hide();
		$("#exam_type").val("0");
		$("#exam_serial").val("0");
	}
	
	function Form1() {
		$(".form_fillup").show();
		$(".create_tt").hide();
		$(".main_table").show();
		$(".sub_table").hide();
		$(".inst_hide").show();
		$(".inst1_hide").hide();
		
	}

	function Form2() { 
		
		$(".inst_hide").hide();
		$(".inst1_hide").show();
		var degree = $("#degree").val();
		var professional = $("#professional").val();
		if(degree == 0 || professional == 0){
			alert("Please Select Degree And Professional");
			return false;
		}else{
			$(".form_fillup").hide();
			$(".create_tt").show();
			$(".sub_table").show();
			$(".main_table").hide();
			$('label[for="option-1"]').click();

			getTemplatedata();
					
		}
	}
	
function Next() {
	
	$(".inst_hide").hide();
	$(".inst1_hide").show();
	
		$(".fatch_valuett").each(function(){
		    $(this).html("");
		    
		  });
		var degree = $("#degree").val();
		var professional = $("#professional").val();
		if(degree == 0){
			alert("Please Select Degree");
			$("select#degree").focus();
			return false;
		}
		if(professional == 0){
			alert("Please Select Professional");
			$("select#professional").focus();
			return false;
		}else{
		$(".tab-group .one.active").removeClass("active");
		$(".tab-group .two").addClass("active");
			$(".form_fillup").hide();
			$(".create_tt").show();
			$(".sub_table").show();
			$(".main_table").hide();
			$('label[for="option-1"]').click();

			getTemplatedata();
					
		}
				
			}
	
	
	function showvalue() {
// 		$(".form_fillup").hide();
		$(".fatch_value").show();
	}
 
	$(".tab-group .tab").click(function() {
		var degree = $("#degree").val();
		var professional = $("#professional").val();
		if(degree != 0 && professional != 0){
			$(".tab").removeClass("active");
			$(this).addClass("active");
		}
		
	});
	
	
	$(".weekly").hide();
	$("#select_day").hide();
	$("#mon").hide();
	$("#tue").hide();
	$("#wed").hide();
	$("#thu").hide();
	$("#fri").hide();
	$("#sat").hide();
	$("#sun").hide();
	
	function weeklyshow() {
		$(".weekly").fadeIn(2000);
		$("#select_day").hide();
		$("#mon").hide();
		$("#tue").hide();
		$("#wed").hide();
		$("#thu").hide();
		$("#fri").hide();
		$("#sat").hide();
		$("#sun").hide();
//		$(".select_days li").removeClass("active");
		
	}
	
	function dayshow() {
//		$(".select_days li").removeClass("active");
		
		$(".weekly").hide();
		$("#select_day").fadeIn(2000);
		$("#mon").hide();
		$("#tue").hide();
		$("#wed").hide();
		$("#thu").hide();
		$("#fri").hide();
		$("#sat").hide();
		$("#sun").hide();
	}
	
function montt() {
		$(".weekly").hide();
		$("#mon").fadeIn(2000);
		$("#tue").hide();
		$("#wed").hide();
		$("#thu").hide();
		$("#fri").hide();
		$("#sat").hide();
		$("#sun").hide();
		getDailyTemplatedata("monday")
}

function tuett() {
	$(".weekly").hide();
	$("#mon").hide();
	$("#tue").fadeIn(2000);
	$("#wed").hide();
	$("#thu").hide();
	$("#fri").hide();
	$("#sat").hide();
	$("#sun").hide();
	getDailyTemplatedata("tuesday")
}

function wedtt() {
	$(".weekly").hide();
	$("#mon").hide();
	$("#tue").hide();
	$("#wed").fadeIn(2000);
	$("#thu").hide();
	$("#fri").hide();
	$("#sat").hide();
	$("#sun").hide();
	getDailyTemplatedata("wednesday")
}

function thutt() {
	$(".weekly").hide();
	$("#mon").hide();
	$("#tue").hide();
	$("#wed").hide();
	$("#thu").fadeIn(2000);
	$("#fri").hide();
	$("#sat").hide();
	$("#sun").hide();
	getDailyTemplatedata("thursday")
}

function fritt() {
	$(".weekly").hide();
	$("#mon").hide();
	$("#tue").hide();
	$("#wed").hide();
	$("#thu").hide();
	$("#fri").fadeIn(2000);
	$("#sat").hide();
	$("#sun").hide();
	getDailyTemplatedata("friday")
}

function sattt() {
	$(".weekly").hide();
	$("#mon").hide();
	$("#tue").hide();
	$("#wed").hide();
	$("#thu").hide();
	$("#fri").hide();
// 	$("#sat").show();
	$("#sat").fadeIn(2000);
	$("#sun").hide();
	getDailyTemplatedata("saturday")
}

function suntt() {
	$(".weekly").hide();
	$("#mon").hide();
	$("#tue").hide();
	$("#wed").hide();
	$("#thu").hide();
	$("#fri").hide();
	$("#sat").hide();
// 	$("#sun").show();
	$("#sun").fadeIn(2000);
	getDailyTemplatedata("sunday")
}

function weeklytimetable() {
	
	$("#weeklytimetable").show();
	$("#select-week").show();
	$("#mondaytimetable").hide();
	$("#tuesdaytimetable").hide();
	$("#wednesdaytimetable").hide();
	$("#thursdaytimetable").hide();
	$("#fridaytimetable").hide();
	$("#saturdaytimetable").hide();
	$("#sundaytimetable").hide();
	
	getweeklytimetable();
}

function montimetable() {
	
	$("#weeklytimetable").hide();
	$("#mondaytimetable").show();
	$("#tuesdaytimetable").hide();
	$("#wednesdaytimetable").hide();
	$("#thursdaytimetable").hide();
	$("#fridaytimetable").hide();
	$("#saturdaytimetable").hide();
	$("#sundaytimetable").hide();
	$("#select-week").hide();
	
	getdailytimetable('select_date1');
}

function tuetimetable() {
	
	$("#weeklytimetable").hide();
	$("#mondaytimetable").hide();
	$("#tuesdaytimetable").show();
	$("#wednesdaytimetable").hide();
	$("#thursdaytimetable").hide();
	$("#fridaytimetable").hide();
	$("#saturdaytimetable").hide();	
	$("#sundaytimetable").hide();
	$("#select-week").hide();
	
	getdailytimetable('select_date2');
}

function wedtimetable() {
	
	$("#weeklytimetable").hide();
	$("#wednesdaytimetable").show();
	$("#mondaytimetable").hide();
	$("#tuesdaytimetable").hide();
	$("#thursdaytimetable").hide();
	$("#fridaytimetable").hide();
	$("#saturdaytimetable").hide();
	$("#sundaytimetable").hide();
	$("#select-week").hide();
	
	getdailytimetable('select_date3');
}

function thutimetable() {
	
	$("#weeklytimetable").hide();
	$("#thursdaytimetable").show();
	$("#mondaytimetable").hide();
	$("#tuesdaytimetable").hide();
	$("#wednesdaytimetable").hide();
	$("#fridaytimetable").hide();
	$("#saturdaytimetable").hide();	
	$("#sundaytimetable").hide();
	$("#select-week").hide();
	
	getdailytimetable('select_date4');
}

function fritimetable() {
	
	$("#weeklytimetable").hide();
	$("#fridaytimetable").show();
	$("#mondaytimetable").hide();
	$("#tuesdaytimetable").hide();
	$("#wednesdaytimetable").hide();
	$("#thursdaytimetable").hide();
	$("#saturdaytimetable").hide();	
	$("#sundaytimetable").hide();
	$("#select-week").hide();
	
	getdailytimetable('select_date5');
}

function sattimetable() {
	
	$("#weeklytimetable").hide();
	$("#saturdaytimetable").show();
	$("#mondaytimetable").hide();
	$("#tuesdaytimetable").hide();
	$("#wednesdaytimetable").hide();
	$("#thursdaytimetable").hide();
	$("#fridaytimetable").hide();
	$("#sundaytimetable").hide();
	$("#select-week").hide();
	
	getdailytimetable('select_date6');
}
function suntimetable() {
	
	$("#weeklytimetable").hide();
	$("#saturdaytimetable").hide();
	$("#mondaytimetable").hide();
	$("#tuesdaytimetable").hide();
	$("#wednesdaytimetable").hide();
	$("#thursdaytimetable").hide();
	$("#fridaytimetable").hide();
	$("#sundaytimetable").show();
	$("#select-week").hide();
	
	getdailytimetable('select_date7');
}


function getweeklytimetable(){
	
	var degree = $("#degree").val();
	var professional = $("#professional").val();	
	$(".fatch_value").each(function(){
	    $(this).html("");
	  });
	var sdate = formatedate(d);
	$.post("getweeklytimetable?" + key + "=" + value, {
		sdate:sdate, 
		degree:degree, 
		professional:professional
		
	}, function(j) {
		
		for(var i=0; i<j.length; i++){
			const myArray = j[i][0].split("||");
			for (var k = 0; k < myArray.length; k++) {
				$("#vweek"+j[i][1]+(k+1)).html(myArray[k])
			}
		}
		getweeklyExamList();
		getweeklyEventList();
		getweeklyTransitionalList();
	});
}

function getdailytimetable(did){	
	$(".fatch_value").each(function(){
	    $(this).html("");
	  });
	var degree = $("#degree").val();
	var professional = $("#professional").val();
	var sdate = $("#"+did).val();
	$.post("getDailyTimetable?" + key + "=" + value, {
		sdate:sdate, 
		degree:degree, 
		professional:professional
		}, function(j) {
		
		for(var i=0; i<j.length; i++){
			const myArray = j[i][0].split("||");
			for (var k = 0; k < myArray.length; k++) {
				$("#vday"+j[i][1]+(k+1)).html(myArray[k])
			}
		}
	});
}

function getTemplatedata(){
	var degree = $("#degree").val();
	var professional = $("#professional").val();
	$.post("getlayouttimetable?" + key + "=" + value, { 
		degree:degree, 
		professional:professional
		
	}, function(j) {
		for(var i=0; i<j.length; i++){
			const myArray = j[i][0].split("||");
			for (var k = 0; k < myArray.length; k++) {
				ttlList = myArray[k].split("-ttlid-");
				ttld = ttlList[0];
				ttlid = ttlList[1];
				$("#vttweek"+j[i][1]+(k+1)).html(ttld);
				$("#vttweekid"+j[i][1]+(k+1)).val(ttlid);
			}
		}
	});
}

function getDailyTemplatedata(day){
	$(".fatch_valuedt").each(function(){
	    $(this).html("");
	  });
	var degree = $("#degree").val();
	var professional = $("#professional").val();
	$.post("getDailyttlayout?" + key + "=" + value, { 
		day:day, 
		degree:degree, 
		professional:professional
		
	}, function(j) {
		for(var i=0; i<j.length; i++){
			const myArray = j[i][0].split("||");
			for (var k = 0; k < myArray.length; k++) {
				ttlList = myArray[k].split("-ttlid-");
				ttld = ttlList[0];
				ttlid = ttlList[1];
				$("#vttday"+j[i][1]+(k+1)).html(ttld);
				$("#vttdayid"+j[i][1]+(k+1)).val(ttlid);
			}
		}
	});
}

$(".select_days li").click(function() {
	$(".select_days li").removeClass("active");
	$(this).addClass("active");
});

</script>

<script nonce="${cspNonce}" type="text/javascript">
$('.show_tt').click(function(){
  var buttonId = $(this).attr('name');
  $('#ttview').removeAttr('class').addClass(buttonId);
  $('body').addClass('modal-active');
})

$('#ttview').click(function(){
  $(this).addClass('out');
  $('body').removeClass('modal-active');
});


$( ".modal > div" ).click(function( event ) {
	  event.stopPropagation();	 
});


function getMonTTdtl(){
	
	if (Validation(1,1)) {
		var degree = $("#degree").val();
		var course = $("#course11").val();
		var faculty = $("#faculty11").val();
		var professional = $("#professional").val();
		var classroom = $("#classroom11").val();
		var start_time = $("#start_time11").val();
		var end_time = $("#end_time11").val();
		var lec_type = $("#lec_type11").val();
		var hid = $("#hidweek11").val();
		var radiobt = $('input[name="updtbtlecture11"]:checked').val();
		var ldt = $("#select_date11").val();
		$.post("getTime_TableDetails?" + key + "=" + value, {
			degree : degree,
			course : course,
			professional : professional,
			faculty : faculty,
			classroom : classroom,
			start_time : start_time,
			end_time : end_time,
			lec_type : lec_type,
			hid : hid,
			radiobt : radiobt,
			ldt : ldt,
			day:"monday"
			
	}, function(j) {
		if (j=='Data Already Exist') {
			alert("Data Already Exist");
		}
		else if (j=='Please Add Proper Data In Academic Details') {
			alert("Please Add Proper Data In Academic Details");
		}
		else {
			getTemplatedata();
			getDailyTemplatedata("monday");
			workLoadCalFn();
		}
// 		getAddedHoursbyCourse(course);
// 		$("#vttweekMONDAY1").html(j);
// 		$("#vttdayMONDAY1").html(j);
		
	});
	}else{
		
		setTimeout(function () {
			$('#monday11').modal('show');	
        }, 700);
				
	}
}

function getTueTTdtl(){
	
	if (Validation(2,1)){
	var degree = $("#degree").val();
	var course = $("#course21").val();
	var faculty = $("#faculty21").val();
	var professional = $("#professional").val();
	var classroom = $("#classroom21").val();
	var start_time = $("#start_time21").val();
	var end_time = $("#end_time21").val();
	var lec_type = $("#lec_type21").val();
	var hid = $("#hidweek21").val();
	var radiobt = $('input[name="updtbtlecture21"]:checked').val();
	var ldt = $("#select_date21").val();
	$.post("getTime_TableDetails?" + key + "=" + value, {
		degree : degree,
		course : course,
		professional : professional,
		faculty : faculty,
		classroom : classroom,
		start_time : start_time,
		end_time : end_time,
		lec_type : lec_type,
		hid : hid,
		radiobt : radiobt,
		ldt : ldt,
		day:"tuesday"
		
}, function(j) {
	if (j=='Data Already Exist') {
		alert("Data Already Exist");
	}
	else if (j=='Please Add Proper Data In Academic Details') {
		alert("Please Add Proper Data In Academic Details");
	}
	else {
		getTemplatedata();
		getDailyTemplatedata("tuesday");
		workLoadCalFn();
	}
// 	getAddedHoursbyCourse(course);
// 	$("#vttweekTUESDAY1").html(j);
// 	$("#vttdayTUESDAY1").html(j);
	
});
	}else{
		
		setTimeout(function () {
			$('#tuesday21').modal('show');
        }, 700);
		
	}
		
}

function getWedTTdtl(){
	
	if (Validation(3,1)){
	
	var degree = $("#degree").val();
	var course = $("#course31").val();
	var faculty = $("#faculty31").val();
	var professional = $("#professional").val();
	var classroom = $("#classroom31").val();
	var start_time = $("#start_time31").val();
	var end_time = $("#end_time31").val();
	var lec_type = $("#lec_type31").val();
	var hid = $("#hidweek31").val();
	var radiobt = $('input[name="updtbtlecture31"]:checked').val();
	var ldt = $("#select_date31").val();
	$.post("getTime_TableDetails?" + key + "=" + value, {
		degree : degree,
		course : course,
		professional : professional,
		faculty : faculty,
		classroom : classroom,
		start_time : start_time,
		end_time : end_time,
		lec_type : lec_type,
		hid : hid,
		radiobt : radiobt,
		ldt : ldt,
		day:"wednesday"
		
}, function(j) {
	if (j=='Data Already Exist') {
		alert("Data Already Exist");
	}
	else if (j=='Please Add Proper Data In Academic Details') {
		alert("Please Add Proper Data In Academic Details");
	}
	else {
		
		getTemplatedata();
		getDailyTemplatedata("wednesday");
		workLoadCalFn();
	}
// 	getAddedHoursbyCourse(course);
//  	$("#vttweekWEDNESDAY1").html(j);
//  	$("#vttdayWEDNESDAY1").html(j);
	
});
	}else{
		
		setTimeout(function () {
			$('#wednesday31').modal('show');
        }, 700);
					
	}
}

function getThuTTdtl(){
	
	if (Validation(4,1)){
		
	var degree = $("#degree").val();
	var course = $("#course41").val();
	var faculty = $("#faculty41").val();
	var professional = $("#professional").val();
	var classroom = $("#classroom41").val();
	var start_time = $("#start_time41").val();
	var end_time = $("#end_time41").val();
	var lec_type = $("#lec_type41").val();
	var hid = $("#hidweek41").val();
	var radiobt = $('input[name="updtbtlecture41"]:checked').val();
	var ldt = $("#select_date41").val();
	$.post("getTime_TableDetails?" + key + "=" + value, {
		degree : degree,
		course : course,
		professional : professional,
		faculty : faculty,
		classroom : classroom,
		start_time : start_time,
		end_time : end_time,
		lec_type : lec_type,
		hid : hid,
		radiobt : radiobt,
		ldt : ldt,
		day:"thursday"
		
}, function(j) {
	if (j=='Data Already Exist') {
		alert("Data Already Exist");
	}
	else if (j=='Please Add Proper Data In Academic Details') {
		alert("Please Add Proper Data In Academic Details");
	}
	else {
		getTemplatedata();
		getDailyTemplatedata("thursday");
		workLoadCalFn();
	}
// 	getAddedHoursbyCourse(course);
// 	$("#vttweekTHURSDAY1").html(j);
// 	$("#vttdayTHURSDAY1").html(j);
	
});
	}else{
		setTimeout(function () {
			$('#thursday41').modal('show');	
        }, 700);
			
	}
}

function getFriTTdtl(){
	
	if (Validation(5,1)){
		
	var degree = $("#degree").val();
	var course = $("#course51").val();
	var faculty = $("#faculty51").val();
	var professional = $("#professional").val();
	var classroom = $("#classroom51").val();
	var start_time = $("#start_time51").val();
	var end_time = $("#end_time51").val();
	var lec_type = $("#lec_type51").val();
	var hid = $("#hidweek51").val();
	var radiobt = $('input[name="updtbtlecture51"]:checked').val();
	var ldt = $("#select_date51").val();
	$.post("getTime_TableDetails?" + key + "=" + value, {
		degree : degree,
		course : course,
		professional : professional,
		faculty : faculty,
		classroom : classroom,
		start_time : start_time,
		end_time : end_time,
		lec_type : lec_type,
		hid : hid,
		radiobt : radiobt,
		ldt : ldt,
		day:"friday"
		
}, function(j) {
	if (j=='Data Already Exist') {
		alert("Data Already Exist");
	}
	else if (j=='Please Add Proper Data In Academic Details') {
		alert("Please Add Proper Data In Academic Details");
	}
	else {
		getTemplatedata();
		getDailyTemplatedata("friday");
		workLoadCalFn();
	}
// 	getAddedHoursbyCourse(course);
//  	$("#vttweekFRIDAY1").html(j);
//  	$("#vttdayFRIDAY1").html(j);
	
});
	}else{
		setTimeout(function () {
			$('#friday51').modal('show');
        }, 700);
					
	}
}

function getSatTTdtl(){
	
	if (Validation(6,1)){

	var degree = $("#degree").val();
	var course = $("#course61").val();
	var faculty = $("#faculty61").val();
	var professional = $("#professional").val();
	var classroom = $("#classroom61").val();
	var start_time = $("#start_time61").val();
	var end_time = $("#end_time61").val();
	var lec_type = $("#lec_type61").val();
	var hid = $("#hidweek61").val();
	var radiobt = $('input[name="updtbtlecture61"]:checked').val();
	var ldt = $("#select_date61").val();
	$.post("getTime_TableDetails?" + key + "=" + value, {
		degree : degree,
		course : course,
		professional : professional,
		faculty : faculty,
		classroom : classroom,
		start_time : start_time,
		end_time : end_time,
		lec_type : lec_type,
		hid : hid,
		radiobt : radiobt,
		ldt : ldt,
		day:"saturday"
		
}, function(j) {
	if (j=='Data Already Exist') {
		alert("Data Already Exist");
	}
	else if (j=='Please Add Proper Data In Academic Details') {
		alert("Please Add Proper Data In Academic Details");
	}
	else {
		getTemplatedata();
		getDailyTemplatedata("saturday");
		workLoadCalFn();
	}
// 	getAddedHoursbyCourse(course);
//  	$("#vttweekSATURDAY1").html(j);
//  	$("#vttdaySATURDAY1").html(j);
});
	}else{
		
		setTimeout(function () {
			$('#saturday61').modal('show');			
        }, 700);
				
		
	}
}

function getSunTTdtl(){
	
	if (Validation(7,1)){
		
	var degree = $("#degree").val();
	var course = $("#course71").val();
	var faculty = $("#faculty71").val();
	var professional = $("#professional").val();
	var classroom = $("#classroom71").val();
	var start_time = $("#start_time71").val();
	var end_time = $("#end_time71").val();
	var lec_type = $("#lec_type71").val();
	var hid = $("#hidweek71").val();
	var radiobt = $('input[name="updtbtlecture71"]:checked').val();
	var ldt = $("#select_date71").val();
	$.post("getTime_TableDetails?" + key + "=" + value, {
		degree : degree,
		course : course,
		professional : professional,
		faculty : faculty,
		classroom : classroom,
		start_time : start_time,
		end_time : end_time,
		lec_type : lec_type,
		hid : hid,
		radiobt : radiobt,
		ldt : ldt,
		day:"sunday"
		
}, function(j) {
	if (j=='Data Already Exist') {
		alert("Data Already Exist");
	}
	else if (j=='Please Add Proper Data In Academic Details') {
		alert("Please Add Proper Data In Academic Details");
	}
	else {
		getTemplatedata();
		getDailyTemplatedata("sunday");
		workLoadCalFn();
	}
// 	getAddedHoursbyCourse(course);
//  	$("#vttweekSUNDAY1").html(j);
//  	$("#vttdaySUNDAY1").html(j);
	});
	}else{
		
		setTimeout(function () {
			$('#sunday71').modal('show');			
        }, 700);
				
	}
}

function getExTTdtl(){
	
	if (Validationforextraclass()){
	
	var degree = $("#degree").val();
	var course = $("#course81").val();
	var faculty = $("#faculty81").val();
	var professional = $("#professional").val();
	var classroom = $("#classroom81").val();
	var start_time = $("#start_time81").val();
	var end_time = $("#end_time81").val();
	var lec_type = $("#lec_type81").val();
	var exam_type = $("#exam_type").val();
	var exam_serial = $("#exam_serial").val();
	var hid = $("#hidweek81").val();
	var radiobt = $('input[name="updtbtlecture81"]:checked').val();
	var radiobt2 = $('input[name="choice"]:checked').val();
	var ldt = $("#select_date81").val();
	var day = $("#day").val();
	$.post("getExTime_TableDetails?" + key + "=" + value, {
		degree : degree,
		course : course,
		professional : professional,
		faculty : faculty,
		classroom : classroom,
		start_time : start_time,
		end_time : end_time,
		exam_type : exam_type,
		exam_serial : exam_serial,
		lec_type : lec_type,
		hid : hid,
		radiobt : radiobt,
		radiobt2 : radiobt2,
		ldt : ldt,
		day : day
		
}, function(j) {
	alert(j);
	if (j=='Data Already Exist') {
		alert("Data Already Exist");
	}
	else if (j=='Please Add Proper Data In Academic Details') {
		alert("Please Add Proper Data In Academic Details");
	}
	else {
		getTemplatedata();
		getDailyTemplatedata(day);
		workLoadCalFn();
	}
// 	getAddedHoursbyCourse(course);
// 	if (j=='Please Update the Data First') {
// 		alert("Please Update the Data First Data Already Exist");
// 	}
// 	else {
// 		(j=='Data Saved Successfully.') 
// 		alert("Data Saved Successfully.");
// 	}
	
//		$("#vttweekMONDAY1").html(j);
//		$("#vttdayMONDAY1").html(j);
	
});
	}
}

function getcoursedtl(){
		var degree_id = $("#degree").val();
		var professional_id = $("#professional").val();
		$.post("getCourseList_for_Curri?" + key + "=" + value, {
			degree_id : degree_id,
			professional_id : professional_id
	}, function(j) {
		var options = '<option value="' + "0" + '">'
				+ "--Select--" + '</option>';
		for (var i = 0; i < j.length; i++) {

			options += '<option   value="' + j[i][0] + '" name="'+j[i][0]+'" >'
					+ j[i][1] + '</option>';
		}
		$("select#course11").html(options);
		$("select#course21").html(options);
		$("select#course31").html(options);
		$("select#course41").html(options);
		$("select#course51").html(options);
		$("select#course61").html(options);
		$("select#course71").html(options);
		$("select#course81").html(options);
		
	});
	}
	
	function getFacultyDtl(ind){
		var course_id = $("#course"+ind).val();

		$.post("getfacutlydetails?" + key + "=" + value, {
			course_id : course_id
	}, function(j) {
		var options = '<option value="' + "0" + '">'
				+ "--Select--" + '</option>';
		for (var i = 0; i < j.length; i++) {

			options += '<option   value="' + j[i][0] + '" name="'+j[i][0]+'" >'
					+ j[i][1] + '</option>';
		}
		$("select#faculty"+ind).html(options);

	});
	}
	
	function getLecturedata(id, r1,r2){
// alert(id+"--"+r1+"--"+r2)		
		var lec_id = $("#"+id).val();
		$.post("getLecturedatadtl?" + key + "=" + value, { lec_id:lec_id
// 			lec_id : lec_id
		}, function(j) {
			if(j.length>0){
				
				$("#start_time"+r1+r2).val(j[0][0]);
				$("#end_time"+r1+r2).val(j[0][1]);
				$("select#course"+r1+r2).val(j[0][2]);
				getFacultyDtl(r1+""+r2);
				$("select#faculty"+r1+r2).val(j[0][3]);
				$("select#classroom"+r1+r2).val(j[0][4]);
				$("select#lec_type"+r1+r2).val(j[0][5]);
				$("#hidweek"+r1+r2).val(lec_id);
				$("#update_dtldiv"+r1+r2).show();
				
			}else{
				$("#start_time"+r1+r2).val("");
				$("#end_time"+r1+r2).val("");
				$("select#course"+r1+r2).val("0");
				$("select#faculty"+r1+r2).val("0");
				$("select#classroom"+r1+r2).val("0");
				$("select#lec_type"+r1+r2).val("0");
				$("#hidweek"+r1+r2).val("0");
				$("#show_date"+r1).hide();
				$("#every_lecture"+r1+r2).prop('checked', 'checked');
				$("#select_date"+r1+r2).val("DD/MM/YYYY");
				$("#update_dtldiv"+r1+r2).hide();
			}
		});
	}
	
	function getDelLecdata(id){
		// alert(id+"--"+r1+"--"+r2)		
				var lec_id = $("#"+id).val();
				$.post("getDelLecdatadtl?" + key + "=" + value, { lec_id:lec_id
//		 			lec_id : lec_id
				}, function(j) {
					alert(j);
					$(".fatch_valuett").each(function(){
					    $(this).html("");
					    
					  });
					$(".LayoutHiddenId").each(function(){
					    $(this).val("0");
					    
					  });
					
					getTemplatedata();
// 					getDailyTemplatedata(day);
					workLoadCalFn();
				});
			}
	
	function getDelLecdatadaywise(id){
		// alert(id+"--"+r1+"--"+r2)		
				var lec_id = $("#"+id).val();
				$.post("getDelLecdatadtl?" + key + "=" + value, { lec_id:lec_id
//		 			lec_id : lec_id
				}, function(j) {
					alert(j);
					$(".fatch_valuedt").each(function(){
					    $(this).html("");
					    
					  });
					$(".dailyLayoutHiddenId").each(function(){
					    $(this).val("0");
					    
					  });
					var day ="";debugger
					$("li.active").each(function(){
					  day = $(this).html().trim();
					  });
					
					getDailyTemplatedata(day);
					workLoadCalFn();
				});
			}
	
	////////// main table /////
	function editData(deg_id,prof_id) {
// 		document.getElementById('lbladd').innerHTML = "Update ";
		$("#degree").val(deg_id);
		
		$("#professional").val(prof_id);
		getcoursedtl();
		Next();
		workLoadCalFn();
// 		document.getElementById('id').value=id;
	}
	
	function deleteData(id) {
		$("#id7").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function deleteData(hid) {
		
		document.querySelectorAll('.deleteOnclick').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('deleteID'+val).value;
				
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(hid);
				} else {
					return false;
				}
			})
		});
		
	}
	function setTimeLoadForTable(){
		
// 		document.getElementById('exbtn_save').onclick = function() {
// 			return Validationforextraclass();
// 		};
		
		
		document.querySelectorAll('.ADDClassroom').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var deg_id = document.getElementById('apdeg_id'+val).value;
				var prof_id = document.getElementById('approf_id'+val).value;
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(deg_id,prof_id);
				} else {
					return false;
				}
			})
		});
	}
	
	function data(search_timetable) {
		
		jsondata = [];
		var table = $('#' + search_timetable).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];

		$.post("getTimetableList?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType

		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser,  j[i].degree_name,j[i].professional, j[i].action ]);
			}
		});
		$.post("getTimetableListCount?" + key + "=" + value, {
			Search : Search
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
function getAprrovedDegree(id) {
		
// 		var selval = $("#system_hid").val();
		var selval = id;
		$
				.post(
						"getdegrebysysidlist?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {
							if(j.length>0){
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#degree").html(options);
							}
						});
	}
	
	function getweeklyExamList(){
		
		var professional = $("#professional").val();	
		var sdate = formatedate(d);
// 		alert(sdate);
		$.post("getweeklyExamList?" + key + "=" + value, {sdate:sdate, professional:professional
			
		}, function(j) {
			for(var i=0; i<j.length; i++){
				for (var k = 1; k <=6 ; k++) {
					$("#vweek"+j[i][0]+(k)).html("EXAM");
				}
			}
		});
	}
	
// 	function getweeklyAcademicList(){
			
// 			var professional = $("#professional").val();	
// 			var sdate = formatedate(d);
// 	// 		alert(sdate);
// 			$.post("getweeklyAcademicList?" + key + "=" + value, {sdate:sdate, professional:professional
				
// 			}, function(j) {
// 				alert(j);
// // 				for(var i=0; i<j.length; i++){
// // 					for (var k = 1; k <=6 ; k++) {
// // 						$("#vweek"+j[i][0]+(k)).html("EXAM");
// // 					}
// // 				}
// 			});
// 		}
	
	function getweeklyEventList(){
			
		var event_date = formatedate(d);
		$.post("getweeklyEventList?" + key + "=" + value, {
			event_date:event_date
			
		}, function(j) {
			for(var i=0; i<j.length; i++){
				for (var k = 1; k <=6 ; k++) {
					$("#vweek"+j[i][0]+(k)).html(j[i][1]);
				}
			}
		});
	}
function getweeklyTransitionalList(){
		
		var professional = $("#professional").val();	
		var sdate = formatedate(d);
// 		alert(sdate);
		$.post("getweeklyTransitionalList?" + key + "=" + value, {sdate:sdate, professional:professional
			
		}, function(j) {
			for(var i=0; i<j.length; i++){
				for (var k = 1; k <=6 ; k++) {
					$("#vweek"+j[i][0]+(k)).html("TRANSITIONAL CURRICULUM");
				}
			}
		});
	}
	
	////---------------------------------------------csp------------------------------------////
	document.addEventListener('DOMContentLoaded', function () {	
		
		
		document.getElementById('btnext').onclick = function() {
			Next();
			workLoadCalFn();
		};
// 		document.getElementById('btnext').onclick = function() {
// 			workLoadCalFn();
// 		};
		document.getElementById('TTform').onclick = function() {			
			Form1();
		};
		
		document.getElementById('TT').onclick = function() {
			Form2();
		};
		document.getElementById('professional').onchange = function() {
			getcoursedtl();
		};
		document.getElementById('option-1').onclick = function() {
			weeklyshow();
		};
		document.getElementById('option-2').onclick = function() {
			dayshow();
		};
		document.getElementById('viewweeklytimetable').onclick = function() {
// 			alert(viewweeklytimetable);
			weeklytimetable();
		};
		document.getElementById('professional').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		
		document.getElementById('professional').onchange = function() {
			getcoursedtl();
		};
		document.getElementById('nextweekli').onclick = function() {
			nextweek();
		};
		document.getElementById('lastweekli').onclick = function() {
			lastweek();
		};
		
		document.getElementById('select_date81').onchange = function() {
			getExtraClassData();
		};
		document.getElementById('start_time81').onchange = function() {
			getExtraClassData();
		};
		document.getElementById('end_time81').onchange = function() {
			getExtraClassData();
		};
		////=================================csp for model=============================================////
		document.getElementById('btmon').onclick = function() {
			montt();
		};
		document.getElementById('bttue').onclick = function() {
			tuett();
		};
		document.getElementById('btwed').onclick = function() {
			wedtt();
		};
		document.getElementById('btthu').onclick = function() {
			thutt();
		};
		document.getElementById('btfri').onclick = function() {
			fritt();
		};
		document.getElementById('btsat').onclick = function() {
			sattt();
		};
		document.getElementById('btsun').onclick = function() {
			suntt();
		};
		
		
		document.getElementById('viewmon').onclick = function() {
			montimetable();
		};
		document.getElementById('viewtue').onclick = function() {
			tuetimetable();
		};
		document.getElementById('viewwed').onclick = function() {
			wedtimetable();
		};
		document.getElementById('viewthu').onclick = function() {
			thutimetable();
		};
		document.getElementById('viewfri').onclick = function() {
			fritimetable();
		};
		document.getElementById('viewsat').onclick = function() {
			sattimetable();
		};
		document.getElementById('viewsun').onclick = function() {
			suntimetable();
		};
		
		document.getElementById('getMonTTdtlli').onclick = function() {
			getMonTTdtl();
// 			getAddedHoursbyCourse(course);
		};
		document.getElementById('getTueTTdtlli').onclick = function() {
			getTueTTdtl();
// 			getAddedHoursbyCourse(course);
		};
		document.getElementById('getWedTTdtlli').onclick = function() {
			getWedTTdtl();
// 			getAddedHoursbyCourse(course);
		};
		document.getElementById('getThuTTdtlli').onclick = function() {
			getThuTTdtl();
// 			getAddedHoursbyCourse(course);
		};
		document.getElementById('getFriTTdtlli').onclick = function() {
			getFriTTdtl();
// 			getAddedHoursbyCourse(course);
		};
		document.getElementById('getSatTTdtlli').onclick = function() {
			getSatTTdtl();
// 			getAddedHoursbyCourse(course);
		};
		document.getElementById('getSunTTdtlli').onclick = function() {
			getSunTTdtl();
// 			getAddedHoursbyCourse(course);
		};
		document.getElementById('exbtn_save').onclick = function() {
			getExTTdtl();
// 			getAddedHoursbyCourse(course);
		};
		document.getElementById('exbtn_update').onclick = function() {
			getExtraClassDataUpdate();
// 			getAddedHoursbyCourse(course);
		};
		document.getElementById('every_lecture11').onclick = function() {
			EveryDay1();
		};
		document.getElementById('single_lecture11').onclick = function() {
			SingleDay1();
		};
		document.getElementById('every_lecture21').onclick = function() {
			EveryDay2();
		};
		document.getElementById('single_lecture21').onclick = function() {
			SingleDay2();
		};
		document.getElementById('every_lecture31').onclick = function() {
			EveryDay3();
		};
		document.getElementById('single_lecture31').onclick = function() {
			SingleDay3();
		};
		document.getElementById('every_lecture41').onclick = function() {
			EveryDay4();
		};
		document.getElementById('single_lecture41').onclick = function() {
			SingleDay4();
		};
		document.getElementById('every_lecture51').onclick = function() {
			EveryDay5();
		};
		document.getElementById('single_lecture51').onclick = function() {
			SingleDay5();
		};
		document.getElementById('every_lecture61').onclick = function() {
			EveryDay6();
		};
		document.getElementById('single_lecture61').onclick = function() {
			SingleDay6();
		};
		document.getElementById('every_lecture71').onclick = function() {
			EveryDay7();
		};
		document.getElementById('single_lecture71').onclick = function() {
			SingleDay7();
		};
		document.getElementById('every_lecture81').onclick = function() {
			EveryDay8();
		};
		document.getElementById('single_lecture81').onclick = function() {
			SingleDay8();
		};
		
		$('.getFacultyDtlclass').each(function() {
		    
		    document.getElementById(this.id).onchange = function() {
		            getFacultyDtl(this.id.substring(6,8));
		            
				};
		});
		$('.select_date_class').each(function() {
		    
		    document.getElementById(this.id).onchange = function() {
		    	getdailytimetable(this.id);
		            
				};
		});
		document.getElementById('periodic_exam').onclick = function() {
			Per_Exam();
		};
		document.getElementById('extra_class').onclick = function() {
			Ex_List();
		};
		
// ----------------------------------------------------
	$('.getLecturedataBT').each(function() {
		  var idcount = $(this).attr("data-bs-target");
		  var idcount0 = idcount.substring(idcount.length -2);
		  var idcount1 = idcount0.substring(0,1);
		  var idcount2 = idcount0.substring(1);
		    var hid = $(this).parent().siblings()[1].id;
		   $(this).on('click', function() {
			   getLecturedata(hid,idcount1,idcount2);
			 });
		   
		});
		
	$('.getDelLecdataBT').each(function() {
		    var hid = $(this).parent().siblings()[1].id;
		   $(this).on('click', function() {
			   getDelLecdata(hid);
			 });
		   
		});
	$('.getDailyDelLecdataBT').each(function() {
	    var hid = $(this).parent().siblings()[1].id;
	   $(this).on('click', function() {
		   getDelLecdatadaywise(hid);
		 });
	   
	});
	
		
	$('.datepickercsp').each(function() {
		   $(this).on('click', function() {
			   clickclear(this, 'DD/MM/YYYY');
			 });
		   $(this).on('focus', function() {
			   this.style.color='#000000'
			 });
		   $(this).on('blur', function() {
			   clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
			 });
		   $(this).on('keyup', function() {
			   clickclear(this, 'DD/MM/YYYY')
			 });
		   $(this).on('change', function() {
			   clickrecall(this,'DD/MM/YYYY');
			 });
		   
		});


	// ----------------------------------------------------
	});
	
	//----------------------------------Extra Class Methods------------------------------------------//
	
	function getExtraClassData() {
		var degree = $("#degree").val();
		var professional = $("#professional").val();
		var ecstart_time = $("#start_time81").val();
		var ecend_time = $("#end_time81").val(); 
		var ec_date = $("#select_date81").val();
// 		alert(ecstart_time);
		if (ecstart_time  != "" && ecend_time != "" && ec_date !="" && ec_date !="DD/MM/YYYY"){
		$.post("getExtraClassData?" + key + "=" + value, {
			degree : degree,
			professional : professional,
			ecstart_time : ecstart_time,
			ecend_time : ecend_time,
			ec_date : ec_date 
		}, function(j) {
			if (j.length>0) {
			$("#select_date81").prop("readonly", true);
// 			$("#select_date81").attr('style','pointer-events: none; touch-action: none; background: rgb(233, 236, 239);');
			$("#start_time81").prop("readonly", true);
			$("#end_time81").prop("readonly", true);
			$("select#course81").val(j[0].course);
			getFacultyDtl(81);
			$("select#faculty81").val(j[0].faculty);
			$("select#classroom81").val(j[0].classroom);
			$("select#lec_type81").val(j[0].lec_type);
			$("#exbtn_update").show();
			$("#exbtn_save").hide();
			}
		});
		}
	}
	
	function getExtraClassDataUpdate() {
		var degree = $("#degree").val();
		var professional = $("#professional").val();
		var course = $("#course81").val();
		var faculty = $("#faculty81").val();
		var classroom = $("#classroom81").val();
		var lec_type = $("#lec_type81").val();
		var ecstart_time = $("#start_time81").val();
		var ecend_time = $("#end_time81").val(); 
		var ec_date = $("#select_date81").val();
		var exam_type = $("#exam_type").val();
		var exam_serial = $("#exam_serial").val();
// 		alert(ecstart_time);
		if (ecstart_time  != "" && ecend_time != "" && ec_date !="" && ec_date !="DD/MM/YYYY"){
		$.post("getExtraClassDataUpdate?" + key + "=" + value, {
			degree : degree,
			professional : professional,
			course : course,
			faculty : faculty,
			classroom : classroom,
			lec_type : lec_type,
			ecstart_time : ecstart_time,
			ecend_time : ecend_time,
			ec_date : ec_date,
			exam_type : exam_type,
			exam_serial : exam_serial
		}, function(j) {
			
			if (j.length>0) {
			alert("Data Updated Successfully.");
			$("#select_date81").val("DD/MM/YYYY");
			$("#start_time81").val("");
			$("#end_time81").val("");
			$("select#course81").val("0");
			getFacultyDtl(81);
			$("select#faculty81").val("0");
			$("select#classroom81").val("0");
			$("select#lec_type81").val("0");
			$("#exbtn_update").hide();
			$("#exbtn_save").show();
			}
		});
		}
	}
	
	
	function Validation(r1,r2) {
		

			if($("input#start_time"+r1+r2).val() == ""){
				alert("Please Select Start Time");
				$("input#start_time").focus();
				return false;
		 	 }
			
			if($("input#end_time"+r1+r2).val() == ""){
				alert("Please Select End Time");
				$("input#end_time").focus();
				return false;
		 	 }
			
			
			if( $("#start_time"+r1+r2).val() > $("#end_time"+r1+r2).val())  {
				alert("Starting Time should be less than Ending Time.");
	 			$("#start_time").focus();
				return false;
		 	}
			
			
			if($("select#course"+r1+r2).val() == "0"){
				alert("Please Select Course");
				$("select#course").focus();
				return false;
		 	 }
			
			if($("select#faculty"+r1+r2).val() == "0"){
				alert("Please Select Faculty");
				$("select#course").focus();
				return false;
		 	 }
			
			if($("select#classroom"+r1+r2).val() == "0"){
				alert("Please Select Classroom");
				$("select#classroom").focus();
				return false;
		 	 }
			
			if($("select#lec_type"+r1+r2).val() == "0"){
				
				alert("Please Select Lecture Type");
				$("select#lec_type").focus();
				return false;
		 	 }
			if($("#hidweek"+r1+r2).val() != "0") {
				 if ($("#single_lecture"+r1+r2).is(":checked")){
					 
					if ($("#select_date"+r1+r2).val().trim() == "" || $("#select_date"+r1+r2).val().trim() == ("DD/MM/YYYY"))  {
					alert("Please Select Date");
					$("input#select_date"+r1+r2).focus();
					return false;
									}
					 }
				 }
		
	 return true;
	}
	
	function Validationforper_exam(){
		if ($("#periodic_exam").is(":checked")){
			 
			if ($("select#exam_type").val().trim() == "0") {
				alert("Please Select Exam Type.");
				$("select#exam_type").focus();
				return false;
			}
			
			if ($("select#exam_type").val().trim() == "8"){
				if ($("select#exam_serial").val().trim() == "0") {
					alert("Please Select Exam Serial.");
					$("select#exam_serial").focus();
					return false;
				}
			}

			setTimeout(function () {
				$('#ex_class').modal('show');		
			        }, 700);
			
			return false;
			
							
			 }
	}
	
	function Validationforextraclass() {
		
		 if ($("#single_lecture81").is(":checked")){
			 
			if ($("#select_date81").val().trim() == "" || $("#select_date81").val().trim() == ("DD/MM/YYYY"))  {
			alert("Please Select Date");
			$("input#select_date81").focus();

			setTimeout(function () {
				$('#ex_class').modal('show');		
			        }, 700);
			
			return false;
			
							}
			 }else{
				 if ($("select#day").val().trim() == "0")  {
						alert("Please Select Day");
						$("select#day").focus();
						
						setTimeout(function () {
							$('#ex_class').modal('show');		
						        }, 700);
						return false;
						
			 }
				 
			 }
		if($("input#start_time81").val() == ""){
			alert("Please Select Start Time");
			$("input#start_time81").focus();
			
			setTimeout(function () {
				$('#ex_class').modal('show');		
			        }, 700);
			return false;
	 	 }
		
		if($("input#end_time81").val() == ""){
			alert("Please Select End Time");
			$("input#end_time81").focus();
			setTimeout(function () {
				$('#ex_class').modal('show');		
			        }, 700);
			return false;
			
	 	 }
		
		if( $("#start_time81").val() > $("#end_time81").val())  {
			alert("Starting Time should be less than Ending Time.");
 			$("#start_time81").focus();
 			setTimeout(function () {
				$('#ex_class').modal('show');		
			        }, 700);
			return false;
	 	}
		
		
		
		
		if($("select#course81").val() == "0"){
			alert("Please Select Course");
			$("select#course81").focus();

			setTimeout(function () {
				$('#ex_class').modal('show');		
			        }, 700);
			return false;
		
	 	 }
		
		if($("select#faculty81").val() == "0"){
			alert("Please Select Faculty");
			$("select#course81").focus();

			setTimeout(function () {
				$('#ex_class').modal('show');		
			        }, 700);
			return false;
			
	 	 }
		
		if($("select#classroom81").val() == "0"){
			alert("Please Select Classroom");
			$("select#classroom81").focus();

			setTimeout(function () {
				$('#ex_class').modal('show');		
			        }, 700);
			return false;
			
	 	 }
		
		if($("#extra_class").is(":checked") && $("select#lec_type81").val() == "0"){
			alert("Please Select Lecture Type");
			$("select#lec_type81").focus();

			
			setTimeout(function () {
				$('#ex_class').modal('show');		
			        }, 700);
			
			return false;
			
	 	 }
	
	
 return true;
}
	
	
// 	//==================================DELETE FUNCTION======================
		
// function delMonTTli(){
	
// 	if (Validation(1,1)) {
// 		var degree = $("#degree").val();
// 		var course = $("#course11").val();
// 		var faculty = $("#faculty11").val();
// 		var professional = $("#professional").val();
// 		var classroom = $("#classroom11").val();
// 		var start_time = $("#start_time11").val();
// 		var end_time = $("#end_time11").val();
// 		var lec_type = $("#lec_type11").val();
// 		var hid = $("#hidweek11").val();
// 		var radiobt = $('input[name="updtbtlecture11"]:checked').val();
// 		var ldt = $("#select_date11").val();
// 		$.post("delTTdetails?" + key + "=" + value, {
// 			degree : degree,
// 			course : course,
// 			professional : professional,
// 			faculty : faculty,
// 			classroom : classroom,
// 			start_time : start_time,
// 			end_time : end_time,
// 			lec_type : lec_type,
// 			hid : hid,
// 			radiobt : radiobt,
// 			ldt : ldt,
// 			day:"monday"
			
// 	}, function(j) {
// 		if (j=='Data Already Exist') {
// 			alert("Data Already Exist");
// 		}
// 		else if (j=='Please Add Proper Data In Academic Details') {
// 			alert("Please Add Proper Data In Academic Details");
// 		}
// 		else {
// 			getTemplatedata();
// 			workLoadCalFn();
// 		}
// // 		getAddedHoursbyCourse(course);
// // 		$("#vttweekMONDAY1").html(j);
// // 		$("#vttdayMONDAY1").html(j);
		
// 	});
// 	}else{
		
// 		setTimeout(function () {
// 			$('#monday11').modal('show');	
//         }, 700);
				
// 	}
// }
	
</script>
