<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- INTERNAL REMOVE START (NEED TO ADD IN PARTICULAR JSP)-->
<!-- 			<link rel="stylesheet" href="assets/vendor/internal_css.css"> -->
<!-- INTERNAL REMOVE END-->
<!-- </style> -->
<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span> View Assessment
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">View
									Assessment</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form name="course" id="search_exam_Action"
						action="search_exam_Action" method='POST'
						commandName="search_exam_CMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">View Assessment</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Degree<span class="mandatory">*</span></label>

											<input type="hidden" value="${degree[0][0]}" id="degree_hid"
												name="degree_hid"
												class="form-control form-control-lg form-control-a effect-9"
												value="0"> <input type="text"
												value="${degree[0][1]}" id="degree_id" name="degree_id"
												class="form-control form-control-lg form-control-a effect-9"
												readonly="readonly">
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Professional<span
												class="mandatory">*</span></label>
											<!-- 											<input type="hidden" -->
											<%-- 											value="${degree[0][2]}" id="professional_hid" --%>
											<!-- 											name="professional_hid" -->
											<!-- 											class="form-control form-control-lg form-control-a effect-9" -->
											<%-- 											value="0"> <input type="text" value="${degree[0][2]}" --%>
											<!-- 											id="professional_id" name="professional_id" -->
											<!-- 											class="form-control form-control-lg form-control-a effect-9"> -->
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="professional_id" id="professional_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getprofessionalList}"
														varStatus="num">
														<option value="${item.id}" name="${item.professional}">${item.professional}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Subject<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="course_id"
													class="singleselect form-control form-control-lg"
													id="course_id">
													<option value="0">--Select--</option>
												</select>
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

											<li><a href="View_Internal_assessment_Marks_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
											<li><a id="btn-view"
												class="main-btn dark-btn btn-hover btn-iconic-icon btnview"
												type="button"><i class="lni lni-eye"></i>View</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>

						<div id="pop" class="custom-d-none">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="card-style mb-30">
										<div class="table-wrapper table-responsive custom-table b-top"
											id="container-table">


											<!-- <table class="table" id="pop4">
							<thead>
								<tr class="learncount middle-center">
									<td colspan="9"><span><label class="bold">
												<br>
												<h4>6 C - Calculation Method for Internal assessment
													Marks (40 Marks)</h4>
										</label></span></td>
								</tr>
								<tr>
									<th rowspan="3" class="middle-center"><label
										class="ml-5 bold">TERM</label></th>
									<th colspan="5" class="middle-center"><label
										class="ml-5 bold">PERIODICAL ASSESSMENT*</label></th>
									<th rowspan="1" class="middle-center"><label
										class="ml-5 bold">TERM TEST**</label></th>
									<th colspan="2" class="middle-center"><label
										class="ml-5 bold">TERM ASSESSMENT</label></th>
								</tr>

								<tr>
									<th class="middle-center"><label class="ml-5 bold">A</label></th>
									<th class="middle-center"><label class="ml-5 bold">B</label></th>
									<th class="middle-center"><label class="ml-5 bold">C</label></th>
									<th class="middle-center"><label class="ml-5 bold">D</label></th>
									<th class="middle-center"><label class="ml-5 bold">E</label></th>
									<th class="middle-center"><label class="ml-5 bold">F</label></th>
									<th class="middle-center"><label class="ml-5 bold">G</label></th>
									<th class="middle-center"><label class="ml-5 bold">H</label></th>
								</tr>

								<tr>
									<th class="middle-center"><label class="ml-5 bold">1(15
											Marks)</label></th>
									<th class="middle-center"><label class="ml-5 bold">2(15
											Marks)</label></th>
									<th class="middle-center"><label class="ml-5 bold">3(15
											Marks)</label></th>
									<th class="middle-center"><label class="ml-5 bold">Average(A+B+C/3)</label></th>
									<th class="middle-center"><label class="ml-5 bold">Converted
											to 40 Marks (D/20*40)</label></th>
									<th class="middle-center"><label class="ml-5 bold">Term
											Test (Marks converted to 40)(15Marks)</label></th>
									<th class="middle-center"><label class="ml-5 bold">Sub
											Total_/80 Marks</label></th>
									<th class="middle-center"><label class="ml-5 bold">Term
											Assessment(.../40)</label></th>
								</tr>

								<tr>
									<th class="middle-center"><label class="ml-5 bold">FIRST</label></th>

									<th class="middle-center"><label id="marks_40_fa"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_fb"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_fc"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_fd"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_fe"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_ff"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_fg"
										class="ml-5 bold">E+F</label></th>
									<th class="middle-center"><label id="marks_40_fh"
										class="ml-5 bold">(E+F)/2</label></th>
								</tr>

								<tr>
									<th class="middle-center"><label class="ml-5 bold">SECOND</label></th>

									<th class="middle-center"><label id="marks_40_sa"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_sb"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_sc"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_sd"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_se"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_sf"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_sg"
										class="ml-5 bold">E+F</label></th>
									<th class="middle-center"><label id="marks_40_sh"
										class="ml-5 bold">(E+F)/2</label></th>
								</tr>
								<tr>
									<th class="middle-center"><label class="ml-5 bold">THIRD</label></th>

									<th class="middle-center"><label id="marks_40_ta"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_tb"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_tc"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_td"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_te"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_tf"
										class="ml-5 bold">NIL</label></th>
									<th class="middle-center"><label id="marks_40_tg"
										class="ml-5 bold"></label></th>
									<th class="middle-center"><label id="marks_40_th"
										class="ml-5 bold">E</label></th>
								</tr>

								<tr>
									<th colspan="1" class="middle-center"><label
										class="ml-5 bold">Final IA</label></th>
									<th colspan="8" class="middle-center"><label
										class="ml-5 bold">Average of Three Term Assessment
											Marks as Shown in ' H ' Column</label></th>
								</tr>
								<tr>
									<th colspan="1" class="middle-center"><label
										class="ml-5 bold"></label></th>
									<th colspan="8" class="middle-center"><label
										class="ml-5 bold">Maximum Marks in Parentheses *Select
											an Evaluation Method which is appropriate for the objectives
											of Topics from the Table 6 D for Periodic assessment. Conduct
											15 marks assessment and enter marks in A, B, and C. **
											Conduct Theory (100 Marks) [MCQ (20*1 Marks), SAQ (8*5), LAQ
											(4*10)] and Practical (100 Marks) Then convert to 40 marks</label></th>
								</tr>
							</thead>
						</table> -->
											<!--end table -->

											<div
												class="table-wrapper table-responsive custom-table custom-table-v2">

												<table class="table table-striped custom-d-none" id="pop1" >
													<thead>
														<tr>
															<th colspan="9"><h6>6 C - Calculation Method
																	for Internal assessment Marks (30 Marks)</h6></th>
														</tr>
														<tr>
															<th rowspan="3"><h6>TERM</h6></th>
															<th colspan="5"><h6>
																	PERIODICAL ASSESSMENT<span class="mandatory">*</span>
																</h6></th>
															<th colspan="1"><h6>
																	TERM TEST<span class="mandatory">**</span>
																</h6></th>
															<th colspan="2"><h6>TERM ASSESSMENT</h6></th>
														</tr>
														<tr>
															<th><h6>A</h6></th>
															<th><h6>B</h6></th>
															<th><h6>C</h6></th>
															<th><h6>D</h6></th>
															<th><h6>E</h6></th>
															<th><h6>F</h6></th>
															<th><h6>G</h6></th>
															<th><h6>H</h6></th>
														</tr>
														<tr>
															<th><h6>1(15 Marks)</h6></th>
															<th><h6>2(15 Marks)</h6></th>
															<th><h6>3(15 Marks)</h6></th>
															<th><h6>Average(A+B+C/3)</h6></th>
															<th><h6>Converted To 30 Marks (D/15*30)</h6></th>
															<th><h6>Term Test (Marks Converted To 30)</h6></th>
															<th><h6>Sub Total_/60 Marks</h6></th>
															<th><h6>Term Assessment(.../30)</h6></th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td><p>FIRST</p></td>
															<td><p id="marks_30_fa"></p></td>
															<td><p id="marks_30_fb"></p></td>
															<td><p id="marks_30_fc"></p></td>
															<td><p id="marks_30_fd"></p></td>
															<td><p id="marks_30_fe"></p></td>
															<td><p id="marks_30_ff"></p></td>
															<td><p id="marks_30_fg"></p></td>
															<td><p id="marks_30_fh"></p></td>
														</tr>
														<tr>
															<td><p>SECOND</p></td>
															<td><p id="marks_30_sa"></p></td>
															<td><p id="marks_30_sb"></p></td>
															<td><p id="marks_30_sc"></p></td>
															<td><p id="marks_30_sd"></p></td>
															<td><p id="marks_30_se"></p></td>
															<td><p id="marks_30_sf"></p></td>
															<td><p id="marks_30_sg"></p></td>
															<td><p id="marks_30_sh"></p></td>
														</tr>
														<tr>
															<td><p>THIRD</p></td>
															<td><p id="marks_30_ta"></p></td>
															<td><p id="marks_30_tb"></p></td>
															<td><p id="marks_30_tc"></p></td>
															<td><p id="marks_30_td"></p></td>
															<td><p id="marks_30_te"></p></td>
															<td><p id="marks_30_tf"></p></td>
															<td><p id="marks_30_tg"></p></td>
															<td><p id="marks_30_th"></p></td>
														</tr>
														<tr>

															<td><p>Final IA</p></td>
															<td colspan="8"><p>Average of Three Term
																	Assessment Marks as Shown in ' H ' Column</p></td>
														</tr>
														<tr>
															<td><p></p></td>
															<td colspan="8"><p>
																	Maximum Marks in Parentheses <br> <span
																		class="mandatory">*</span>Select an Evaluation Method
																	which is appropriate for the objectives of Topics from
																	the Table 6 D for Periodic assessment. Conduct 15 marks
																	assessment and enter marks in A, B, and C. <br>
																	<span class="mandatory">**</span> Conduct Theory (100
																	Marks) [MCQ (20*1 Marks), SAQ (8*5), LAQ (4*10)] and
																	Practical (100 Marks) Then convert to 15 marks
																</p></td>
														</tr>
													</tbody>
												</table>

												<table class="table custom-d-none" id="pop2">
													<thead>
														<tr>
															<th colspan="9"><h6>6 C - Calculation Method
																	for Internal assessment Marks (20 Marks)</h6></th>
														</tr>
														<tr>
															<th rowspan="3"><h6>TERM</h6></th>
															<th colspan="5"><h6>
																	PERIODICAL ASSESSMENT<span class="mandatory">*</span>
																</h6></th>
															<th colspan="1"><h6>
																	TERM TEST<span class="mandatory">**</span>
																</h6></th>
															<th colspan="2"><h6>TERM ASSESSMENT</h6></th>
														</tr>
														<tr>
															<th><h6>A</h6></th>
															<th><h6>B</h6></th>
															<th><h6>C</h6></th>
															<th><h6>D</h6></th>
															<th><h6>E</h6></th>
															<th><h6>F</h6></th>
															<th><h6>G</h6></th>
															<th><h6>H</h6></th>
														</tr>
														<tr>
															<th><h6>1(20 Marks)</h6></th>
															<th><h6>2(20 Marks)</h6></th>
															<th><h6>3(20 Marks)</h6></th>
															<th><h6>Average(A+B+C/20)</h6></th>
															<th><h6>Converted To 30 Marks (D/15*30)</h6></th>
															<th><h6>Term Test (MCQ+SAQ+LAQMarks and
																	Practical(Converted to 20))</h6></th>
															<th><h6>Sub Total</h6></th>
															<th><h6>Term Assessment</h6></th>
														</tr>

														<tr>
															<td><p>FIRST</p></td>
															<td><p id="marks_20_fa"></p></td>
															<td><p id="marks_20_fb"></p></td>
															<td><p id="marks_20_fc"></p></td>
															<td><p id="marks_20_fd"></p></td>
															<td><p id="marks_20_fe"></p></td>
															<td><p id="marks_20_ff"></p>D+E</td>
															<td><p id="marks_20_fg"></p>D+E/2</td>
														</tr>

														<tr>
															<td><p>SECOND</p></td>
															<td><p id="marks_20_sa"></p></td>
															<td><p id="marks_20_sb"></p></td>
															<td><p id="marks_20_sc"></p></td>
															<td><p id="marks_20_sd"></p></td>
															<td><p id="marks_20_se"></p></td>
															<td><p id="marks_20_sf"></p>D+E</td>
															<td><p id="marks_20_sg"></p>D+E/2</td>
														</tr>

														<tr>
															<td><p>THIRD</p></td>
															<td><p id="marks_20_ta"></p></td>
															<td><p id="marks_20_tb"></p></td>
															<td><p id="marks_20_tc"></p></td>
															<td><p id="marks_20_td"></p></td>
															<td><p id="marks_20_te"></p>NIL</td>
															<td><p id="marks_20_tf"></p>D</td>
															<td><p id="marks_20_tg"></p>D</td>
														</tr>

														<tr>

															<td><p>Final IA</p></td>
															<td colspan="8"><p>Average of Three Term
																	Assessment Marks as Shown in ' H ' Column</p></td>
														</tr>
														<tr>
															<td><p></p></td>
															<td colspan="8"><p>Average of Three Term
																	Assessment Marks as Shown in ' G ' Column</p></td>
														</tr>

													</thead>
												</table>

												<table class="table table-striped custom-d-none" id="pop3">
													<thead>
														<tr>
															<th colspan="9"><h6>6 C - Calculation Method
																	for Internal assessment Marks (15 Marks)</h6></th>
														</tr>
														<tr>
															<th rowspan="3"><h6>TERM</h6></th>
															<th colspan="5"><h6>
																	PERIODICAL ASSESSMENT<span class="mandatory">*</span>
																</h6></th>
															<th colspan="1"><h6>
																	TERM TEST<span class="mandatory">*</span>
																</h6></th>
															<th colspan="2"><h6>TERM ASSESSMENT</h6></th>
														</tr>
														<tr>
															<th><h6>A</h6></th>
															<th><h6>B</h6></th>
															<th><h6>C</h6></th>
															<th><h6>D</h6></th>
															<th><h6>E</h6></th>
															<th><h6>F</h6></th>
															<th><h6>G</h6></th>
															<th><h6>H</h6></th>
														</tr>
														<tr>
															<th><h6>1(15 Marks)</h6></th>
															<th><h6>2(15 Marks)</h6></th>
															<th><h6>3(15 Marks)</h6></th>
															<th><h6>Average(A+B+C/3)</h6></th>
															<th><h6>Converted To 15 Marks (D/15*15)</h6></th>
															<th><h6>Term Test (Marks Converted To
																	15)(15Marks)</h6></th>
															<th><h6>Sub Total_/30 Marks</h6></th>
															<th><h6>Term Assessment(.../15)</h6></th>
														</tr>
														<!-- end table header row-->
													</thead>
													<tbody>
														<tr>
															<td><p>FIRST</p></td>
															<td><p id="marks_15_fa"></p></td>
															<td><p id="marks_15_fb"></p></td>
															<td><p id="marks_15_fc"></p></td>
															<td><p id="marks_15_fd"></p></td>
															<td><p id="marks_15_fe"></p></td>
															<td><p id="marks_15_ff"></p></td>
															<td><p id="marks_15_fg"></p></td>
															<td><p id="marks_15_fh"></p></td>
														</tr>

														<tr>
															<td><p>SECOND</p></td>
															<td><p id="marks_15_sa"></p></td>
															<td><p id="marks_15_sb"></p></td>
															<td><p id="marks_15_sc"></p></td>
															<td><p id="marks_15_sd"></p></td>
															<td><p id="marks_15_se"></p></td>
															<td><p id="marks_15_sf"></p></td>
															<td><p id="marks_15_sg"></p></td>
															<td><p id="marks_15_sh"></p></td>
														</tr>

														<tr>
															<td><p>THIRD</p></td>
															<td><p id="marks_15_ta"></p></td>
															<td><p id="marks_15_tb"></p></td>
															<td><p id="marks_15_tc"></p></td>
															<td><p id="marks_15_td"></p></td>
															<td><p id="marks_15_te"></p></td>
															<td><p id="marks_15_tf"></p></td>
															<td><p id="marks_15_tg"></p></td>
															<td><p id="marks_15_th"></p></td>
														</tr>

														<tr>
															<td><p>Final IA</p></td>
															<td colspan="8"><p>Average of Three Term
																	Assessment Marks as Shown in ' H ' Column</p></td>
														</tr>
														<tr>
															<td><p></p></td>
															<td colspan="8"><p>
																	Maximum Marks in Parentheses <br> <span
																		class="mandatory">*</span>Select an Evaluation Method
																	which is appropriate for the objectives of Topics from
																	the Table 6 D for Periodic assessment. Conduct 15 marks
																	assessment and enter marks in A, B, and C. <br>
																	<span class="mandatory">**</span> Conduct Theory (100
																	Marks) [MCQ (20*1 Marks), SAQ (8*5), LAQ (4*10)] and
																	Practical (100 Marks) Then convert to 15 marks
																</p></td>
														</tr>
													</tbody>
												</table>
												<!-- end table -->

												<table class="table custom-d-none" id="pop4">
													<thead>
														<tr>
															<th colspan="9"><h6>6 C - Calculation Method
																	for Internal assessment Marks (40 Marks)</h6></th>
														</tr>
														<tr>
															<th rowspan="3"><h6>TERM</h6></th>
															<th colspan="5"><h6>
																	PERIODICAL ASSESSMENT<span class="mandatory">*</span>
																</h6></th>
															<th colspan="1"><h6>
																	TERM TEST<span class="mandatory">*</span>
																</h6></th>
															<th colspan="2"><h6>TERM ASSESSMENT</h6></th>
														</tr>
														<tr>
															<th><h6>A</h6></th>
															<th><h6>B</h6></th>
															<th><h6>C</h6></th>
															<th><h6>D</h6></th>
															<th><h6>E</h6></th>
															<th><h6>F</h6></th>
															<th><h6>G</h6></th>
															<th><h6>H</h6></th>
														</tr>
														<tr>
															<th><h6>1(15 Marks)</h6></th>
															<th><h6>2(15 Marks)</h6></th>
															<th><h6>3(15 Marks)</h6></th>
															<th><h6>Average(A+B+C/3)</h6></th>
															<th><h6>Converted To 30 Marks (D/15*40)</h6></th>
															<th><h6>Term Test (Marks Converted To 40)
																	(15Marks)</h6></th>
															<th><h6>Sub Total_/80 Marks</h6></th>
															<th><h6>Term Assessment(.../40)</h6></th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td><p>FIRST</p></td>
															<td><p id="marks_40_fa"></p></td>
															<td><p id="marks_40_fb"></p></td>
															<td><p id="marks_40_fc"></p></td>
															<td><p id="marks_40_fd"></p></td>
															<td><p id="marks_40_fe"></p></td>
															<td><p id="marks_40_ff"></p></td>
															<td><p id="marks_40_fg">E+F</p></td>
															<td><p id="marks_40_fh">E+F/2</p></td>
														</tr>
														<tr>
															<td><p>SECOND</p></td>
															<td><p id="marks_40_sa"></p></td>
															<td><p id="marks_40_sb"></p></td>
															<td><p id="marks_40_sc"></p></td>
															<td><p id="marks_40_sd"></p></td>
															<td><p id="marks_40_se"></p></td>
															<td><p id="marks_40_sf"></p></td>
															<td><p id="marks_40_sg">E+F</p></td>
															<td><p id="marks_40_sh">E+F/2</p></td>
														</tr>
														<tr>
															<td><p>THIRD</p></td>
															<td><p id="marks_40_ta"></p></td>
															<td><p id="marks_40_tb"></p></td>
															<td><p id="marks_40_tc"></p></td>
															<td><p id="marks_40_td"></p></td>
															<td><p id="marks_40_te"></p></td>
															<td><p id="marks_40_tf">NIL</p></td>
															<td><p id="marks_40_tg"></p></td>
															<td><p id="marks_40_th">E</p></td>
														</tr>
														<tr>

															<td><p>Final IA</p></td>
															<td colspan="8"><p>Average of Three Term
																	Assessment Marks as Shown in ' H ' Column</p></td>
														</tr>
														<tr>

															<td><p></p></td>
															<td colspan="8"><p>Maximum Marks in Parentheses
																	*Select an Evaluation Method which is appropriate for
																	the objectives of Topics from the Table 6 D for
																	Periodic assessment. Conduct 15 marks assessment and
																	enter marks in A, B, and C. ** Conduct Theory (100
																	Marks) [MCQ (20*1 Marks), SAQ (8*5), LAQ (4*10)] and
																	Practical (100 Marks) Then convert to 40 marks</p></td>
														</tr>
													</tbody>
												</table>

											</div>

										</div>
									</div>
									<!-- 						end card -->
								</div>
								<!-- 				end col -->
							</div>
						</div>


						<div class="row custom-d-none" id="pop_not" >
							<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="card-style mb-30">
									<div class="table-wrapper table-responsive custom-table b-top"
										id="container-table">
										<table class="table custom-d-none" id="pop1_not">
											<thead>

												<tr class="learncount middle-center">
													<td colspan="9"><p>
																No Data Available
														</p></td>
												</tr>

											</thead>
										</table>

										<!-- 										end table -->
									</div>
								</div>
								<!-- 						end card -->
							</div>
							<!-- 				end col -->
						</div>

					</form:form>
				</div>
			</div>
		</div>



	</div>
</section>


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$.ajaxSetup({
			async : false
		});
		$("#pop").hide();
		$("#pop1").hide();
		$("#pop2").hide();
		$("#pop3").hide();
		$("#pop4").hide();
		$("#pop1_not").hide();
		$("#pop_not").hide();
		
		var stu_prof = '${degree[0][3]}';
		
		if(stu_prof == "1"){
			stu_prof = "15";
		}
		if(stu_prof == "2"){
			stu_prof = "16";
		}
		if(stu_prof == "3"){
			stu_prof = "17";
		}
		
// 		$("#degree_id").val(stu_degree);
		$("select#degree_id").attr("disabled", true); 
// 		$("#professional_id").val(stu_prof);
//			$("select#professional_id").attr("disabled", true); 
		getcourselistby_professional();

	});

	document.addEventListener('DOMContentLoaded', function() {

// 		document.getElementById('degree_id').onchange = function() {
// 			getcourselistbysystemdegreeprof();
// 		};
// 		document.getElementById('degree_id').onchange = function() {
// 			getcourselistby_professional();
// 		};
		
		document.getElementById('professional_id').onchange = function() {
			getcourselistby_professional();
		};
		
		document.getElementById('btn-view').onclick = function() {
			View_Validation();
			getMarksbyCourseId();
		};
	});

	function getcourselistby_professional() {
		var degree_id = $("#degree_hid").val();
		var professional_id = $("#professional_id").val();
		$
				.post('getCourseList_for_Curri?' + key + "=" + value, {
					degree_id : degree_id,
					professional_id : professional_id
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#course_id").html(options);
						});
	}
	function viewInternal_ass_marks() {

		var IASCHEME = "";
		var course_id = $("#course_id").val();

		$.post('getMarksbyCourse?' + key + "=" + value, {
			course_id : course_id
		}).done(function(j) {

			IASCHEME = j;

			if (j == "30") {
				$("#pop").show();
				$("#pop1").show();
				$("#pop2").hide();
				$("#pop3").hide();
			}
			if (j == "20") {
				$("#pop").show();
				$("#pop1").hide();
				$("#pop2").show();
				$("#pop3").hide();
			}
			if (j == "15") {
				$("#pop").show();
				$("#pop1").hide();
				$("#pop2").hide();
				$("#pop3").show();
			}
			if (j == "40") {
				$("#pop").show();
				$("#pop1").hide();
				$("#pop2").hide();
				$("#pop3").hide();
				$("#pop4").show();
			}

		});
		let tA1=0,tA2=0,tA3=0,tB1=0,tB2=0,tB3=0,tC1=0,tC2=0,tC3=0;

		// 	var course_id = $("#course_id").val();
		var professional_id = $("#professional_id").val();
		
		if(professional_id == "1"){
			professional_id = "15";
		}
		if(professional_id == "2"){
			professional_id = "16";
		}
		if(professional_id == "3"){
			professional_id = "17";
		}
		
		$.post('getviewInternal_ass_marks_data?' + key + "=" + value, {
			course_id : course_id,
			professional_id : professional_id,
			exam_type : "PERIODIC ASSESSMENT PA",
			exam_seral : "1"
		}).done(function(j) {
			// 		debugger;
			const internal = [];
			
			for (var i = 0; i < j.length; i++) {
				debugger;
				if (j[i][1] == "I") {
					if (j[i][3] == "PERIODIC ASSESSMENT PA") {
						if (j[i][2] == "1") {
							$("#marks_" + IASCHEME + "_fa").text(j[i][0]);
							tA1 = j[i][0];
							internal.push(1);
						} else if (j[i][2] == "2") {
							$("#marks_" + IASCHEME + "_fb").text(j[i][0]);
							tA2 = j[i][0];

							internal.push(2);
						} else if (j[i][2] == "3") {
							$("#marks_" + IASCHEME + "_fc").text(j[i][0]);
							tA3 = j[i][0];

							internal.push(3);
						}

					}
					if (j[i][3] == "TERM TEST TT") {
						$("#marks_" + IASCHEME + "_ff").text(Math.round((parseInt(j[i][0])/100)*parseInt(IASCHEME)));
						internal.push(4);

					}
				}
				if (j[i][1] == "II") {
					if (j[i][3] == "PERIODIC ASSESSMENT PA") {
						if (j[i][2] == "1") {
							$("#marks_" + IASCHEME + "_sa").text(j[i][0]);
							tB1 = j[i][0];

							internal.push(5);

						}
						if (j[i][2] == "2") {
							$("#marks_" + IASCHEME + "_sb").text(j[i][0]);
							tB2 = j[i][0];

							internal.push(6);

						}
						if (j[i][2] == "3") {
							$("#marks_" + IASCHEME + "_sc").text(j[i][0]);
							tB3 = j[i][0];

							internal.push(7);

						}
					}
					if (j[i][3] == "TERM TEST TT") {
						$("#marks_" + IASCHEME + "_sf").text(Math.round((parseInt(j[i][0])/100)*parseInt(IASCHEME)));
						internal.push(8);

					}
				}
				if (j[i][1] == "III") {
					if (j[i][3] == "PERIODIC ASSESSMENT PA") {
						if (j[i][2] == "1") {
							$("#marks_" + IASCHEME + "_ta").text(j[i][0]);
							tC1 = j[i][0];

							internal.push(9);

						}
						if (j[i][2] == "2") {
							$("#marks_" + IASCHEME + "_tb").text(j[i][0]);
							tC2 = j[i][0];

							internal.push(10);

						}
						if (j[i][2] == "3") {
							$("#marks_" + IASCHEME + "_tc").text(j[i][0]);
							tC3 = j[i][0];

							internal.push(11);

						}
					}
					if (j[i][3] == "TERM TEST TT") {
						$("#marks_" + IASCHEME + "_tf").text(Math.round((parseInt(j[i][0])/100)*parseInt(IASCHEME)));
						internal.push(12);

					}
				}
			}

			var count = 12;
			var missing = new Array();

			for (var i = 1; i <= count; i++) {
				if (internal.indexOf(i) == -1) {
					missing.push(i);
				}
			}
			console.log(missing);

			for (var j = 0; j < missing.length; j++) {

				switch (missing[j]) {
				case 1:
					$("#marks_" + IASCHEME + "_fa").text('0');
					tA1 = 0;
					break;
				case 2:
					$("#marks_" + IASCHEME + "_fb").text('0');
					tA2 = 0;
					break;
				case 3:
					$("#marks_" + IASCHEME + "_fc").text('0');
					tA3 = 0;
					break;
				case 4:
					$("#marks_" + IASCHEME + "_ff").text('0');
					break;
				case 5:
					$("#marks_" + IASCHEME + "_sa").text('0');
					tB1 = 0;
					break;
				case 6:
					$("#marks_" + IASCHEME + "_sb").text('0');
					tB2 = 0;
					break;
				case 7:
					$("#marks_" + IASCHEME + "_sc").text('0');
					tB3 = 0;
					break;
				case 8:
					$("#marks_" + IASCHEME + "_sf").text('0');
					break;
				case 9:
					tC1 = 0;
					$("#marks_" + IASCHEME + "_ta").text('0');
					break;
				case 10:
					tC2 = 0;
					$("#marks_" + IASCHEME + "_tb").text('0');
					break;
				case 11:
					tC3 = 0;
					$("#marks_" + IASCHEME + "_tc").text('0');
					break;
				case 12:
					$("#marks_" + IASCHEME + "_tf").text('0');
					break;

				}

			}

			// 		alert(internal);

		});

		console.log((Math.round(parseInt(tA1) + parseInt(tA2) + parseInt(tA3) / 3)));
		$("#marks_" + IASCHEME + "_fd")
				.text(
						(Math.round((parseInt(tA1) + parseInt(tA2) + parseInt(tA3)) / 3)));

		$("#marks_" + IASCHEME + "_sd")
				.text(
						(Math.round((parseInt(tB1) + parseInt(tB2) + parseInt(tB3)) / 3)));

		$("#marks_" + IASCHEME + "_td")
				.text(
						(Math.round((parseInt(tC1) + parseInt(tC2) + parseInt(tC3)) / 3)));

		let D1=((parseInt(tA1) + parseInt(tA2) + parseInt(tA3)) / 3),D2=((parseInt(tB1) + parseInt(tB2) + parseInt(tB3)) / 3),D3=((parseInt(tC1) + parseInt(tC2) + parseInt(tC3)) / 3);
		$("#marks_" + IASCHEME + "_fe").text(Math.round((D1 / 15 * parseInt(IASCHEME))));

		$("#marks_" + IASCHEME + "_se").text(Math.round((D2 / 15* parseInt(IASCHEME))));

		$("#marks_" + IASCHEME + "_te").text(Math.round((D3 / 3) * parseInt(IASCHEME)));

		$("#marks_" + IASCHEME + "_fg").text(
				parseInt($("#marks_" + IASCHEME + "_fe").text())
						+ parseInt($("#marks_" + IASCHEME + "_ff").text()));

		$("#marks_" + IASCHEME + "_sg").text(
				parseInt($("#marks_" + IASCHEME + "_se").text())
						+ parseInt($("#marks_" + IASCHEME + "_sf").text()));

		$("#marks_" + IASCHEME + "_tg").text(
				parseInt($("#marks_" + IASCHEME + "_te").text())
						+ parseInt($("#marks_" + IASCHEME + "_tf").text()));

		$("#marks_" + IASCHEME + "_fh").text(
				parseInt(Math.round(($("#marks_" + IASCHEME + "_fg").text()) / 2)));

		$("#marks_" + IASCHEME + "_sh").text(
				parseInt(Math.round(($("#marks_" + IASCHEME + "_sg").text()) / 2)));

		$("#marks_" + IASCHEME + "_th").text(
				parseInt(Math.round(($("#marks_" + IASCHEME + "_tg").text()) / 2)));

	}

	function View_Validation() {
		if ($("#professional_id").val() == "0") {
			alert("Please Select Professional.");
			$("select#professional_id").focus();
			return false;
		}
		if ($("#course_id").val() == "0") {
			alert("Please Select Subject.");
			$("select#course_id").focus();
			return false;
		}
		viewInternal_ass_marks();
		return true;
		
	}

	function getcourselistbysystemdegreeprof() {
		var degree_id = $("#degree_hid").val();
		var professional_id = $("#professional_id").val();
		if(professional_id == "1"){
			professional_id = "15";
		}
		if(professional_id == "2"){
			professional_id = "16";
		}
		if(professional_id == "3"){
			professional_id = "17";
		}
		console.log(degree_id);
		console.log(professional_id);

		$
				.post('getCourseListByterm?' + key + "=" + value, {
					degree_id : degree_id,
					professional_id : professional_id
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#course_id").html(options);
						});
	}
	function getMarksbyCourseId() {
		var course_id = $("#course_id").val();
		$.post('getMarksbyCourse?' + key + "=" + value, {
			course_id : course_id
		}).done(function(j) {
			
			if (course_id == "47") {
				$("#pop1_not").show();
				$("#pop_not").show();
				
				$("#pop1").hide();
				$("#pop").hide();
				$("#pop2").hide();
				$("#pop").hide();
				$("#pop4").hide();
				$("#pop3").hide();
				$("#pop").hide();
				

			}
			
			if (course_id == "48") {
				$("#pop1_not").show();
				$("#pop_not").show();
				
				$("#pop1").hide();
				$("#pop").hide();
				$("#pop2").hide();
				$("#pop").hide();
				$("#pop4").hide();
				$("#pop3").hide();
				$("#pop").hide();
				

			}
			
			if (course_id == "49") {
				$("#pop1_not").show();
				$("#pop_not").show();
				
				$("#pop1").hide();
				$("#pop").hide();
				$("#pop2").hide();
				$("#pop").hide();
				$("#pop4").hide();
				$("#pop3").hide();
				$("#pop").hide();
				

			}
			if (j == "30") {
				$("#pop1").show();
				$("#pop").show();
				
				$("#pop1_not").hide();
				$("#pop_not").hide();
				$("#pop2").hide();
				$("#pop3").hide();
				$("#pop4").hide();
			}
			if (j == "20") {
				$("#pop2").show();
				$("#pop").show();
				
				$("#pop1").hide();
				$("#pop1_not").hide();
				$("#pop_not").hide();
				$("#pop3").hide();
				$("#pop4").hide();
			}
			if (j == "15") {
				
				$("#pop3").show();
				$("#pop").show();
				
				$("#pop1").hide();
				$("#pop2").hide();
				$("#pop4").hide();
				$("#pop1_not").hide();
				$("#pop_not").hide();
			}
			if (j == "40") {
				
				$("#pop4").show();
				$("#pop").show();
				
				$("#pop1").hide();
				$("#pop2").hide();
				$("#pop3").hide();
				$("#pop1_not").hide();
				$("#pop_not").hide();
			}
			
// 			$("select#").html(options);
		});
	}

	function findMissingNumbers(array) {
		var resultsArray = [];
		var missedNumbers = 0;

		for (var i = 0; i < array.length; i++) {
			var expectedValue = i + 1 + missedNumbers;

			if (array[i] != expectedValue) {
				var segmentCountOfMissedNumbers = array[i] - expectedValue;

				for (var ii = 0; ii < segmentCountOfMissedNumbers; ii++) {
					resultsArray.push(expectedValue + ii);
				}
				missedNumbers = missedNumbers + segmentCountOfMissedNumbers;
			}
		}

		if (resultsArray.length > 0) {
			return resultsArray;
		}
	}
</script>
