<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
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
<script type="text/javascript" src="js\watermark\common.js"></script>
<!-- <link href="js/dropDown/select2.min.css" rel="Stylesheet"></link> -->
<!-- <script src="js/dropDown/select2.min.js" type="text/javascript"></script> -->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span> Practical Learning Objectives
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Practical Learning Objectives</li>
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
					<form:form name="nch_t4_learning_object" id="nch_t4_learning_object"
						action="nch_t4_learning_objectAction" method='POST'
						commandName="nch_t4_learning_objectCMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">T4 - Practical Learning Objectives</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">System<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="system_id" id="system_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getSystemList}"
														varStatus="num">
														<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="degree_id" id="degree_id"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Professional<span
												class="mandatory">*</span></label>
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
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Practical<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="practical_id"
													class="select2 narrow wrap form-control form-control-lg form-control-a disablecopypaste"
													id="practical_id">
													<option value="0">--Select--</option>
												</select>
											</div>
											<!-- Hidden Start -->
											<input type="hidden" id="count_hidden_att"
												name="count_hidden_att" class="form-control autocomplete"
												value="1"> <input type='hidden' id='id' name="id"
												value='0' />
											<!-- Hidden End -->

										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Learning Outcome<span class="mandatory">*</span></label>
											<div class="select-position">
											<select class="" id="div_iv_fluids" name="">
												<option value="0" id="learning_outcome0" class="hida overSelect">--Select--</option>
											</select>
											<c:forEach var="item" items="${Learning_OutcomeList}" varStatus="num">
											 <input type="hidden" id="learning_outcome1" name="learning_outcome"
												autocomplete="off" class="form-control-sm form-control"
												value="">
												</c:forEach>
										</div>
										<div id="div_iv_fluids_2" class="multiselect">
											<div id="div_cb_dd"
												class="form-check radio-style checkbox-style align-items-center">
												d-flex
											</div>
										</div>
										</div>
											
										<input type="hidden" id="learning_outcome_hid"
										name="learning_outcome_hid" class="form-control autocomplete">
										
									</div>
								</div>
							</div>
							<section class="single-detail-block">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<h6 class="mb-10"></h6>
										<div
											class="table-wrapper table-responsive custom-table  b-top mt-0">
											<table class="table" id="att_Tb">
												<!-- 							<section class="single-detail-block"> -->
												<!-- 								<div class="row"> -->
												<!-- 									<div class="col-lg-12 col-md-12 col-sm-12"> -->
												<!-- 										<div -->
												<!-- 											class="table-wrapper table-responsive custom-table-p simple-table"> -->
												<!-- 											<table class="table" id="att_Tb"> -->
												<thead>
													<tr>
														<th rowspan="2"><h6>Sr No</h6></th>
														<th rowspan="2"><h6>
																Generic Competency<span class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																Subject Area <span class="mandatory" maxlength="1000">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																Millers Knows/Knows how/ Shows how/Does<span
																	class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																Specific Competency<span class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																Special learning objectives<span class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																Blooms Domain<span class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																Guilberts level<span class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																Must know/ Desire to know/ Nice to know<span
																	class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																TL Method/Media<span class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																Formative Assessment <span class="mandatory">*</span>
															</h6></th>
														 <th rowspan="2"><h6>
																Summative Assessment <span class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																Integration Horizontal/ Vertical/ Spiral <span
																	class="mandatory">*</span>
															</h6></th> 
															<th rowspan="2"><h6>
																Term <span
																	class="mandatory">*</span>
															</h6></th> 
															

														<th rowspan="2"><h6>Action</h6></th>
													</tr>
												</thead>
												<tbody id="att_Tbbody">
													<tr id="tr_id_att">
														<td>1</td>
														<td>
															<div class="input-style-1">
																<input type="text" id="generic_compte1"
																	name="generic_compte1"
																	class="autocomplete UpperClassName txt-transupp"
																	autocomplete="off" maxlength="100"
																	placeholder="Generic Competency" />
															</div>
														</td>
														<td>
															<div class="input-style-1">
																<input type="text" id="subject_area1" name="subject_area1"
																	class="autocomplete UpperClassName txt-transupp"
																	autocomplete="off" maxlength="100"
																	placeholder="Subject Area" />
															</div>
														</td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select name="millers_knows1" id="millers_knows1">
																		<option value="0">--Select--</option>
																		<c:forEach var="item"
																			items="${getMillers_level}" varStatus="num">
																			<option value="${item.id}" name="${item.rep_und}">${item.rep_und}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="input-style-1">
																<input type="text" id="specific_compet1"
																	name="specific_compet1"
																	class="autocomplete UpperClassName txt-transupp"
																	autocomplete="off" maxlength="100"
																	placeholder="Specific Competency" />
															</div>
														</td>
														<td>
															<div class="input-style-1">
																<input type="text" id="spec_learn_object1"
																	name="spec_learn_object1"
																	class="autocomplete UpperClassName txt-transupp"
																	autocomplete="off" maxlength="100"
																	placeholder="Special learning objectives" />
															</div>
														</td>
														<td>
															
															<div class="select-style-1">
																<div class="select-position">
																	<select name="blooms_domain1" id="blooms_domain1">
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${NchgetBloom_Domain}"
																			varStatus="num">
																			<option value="${item.id}" name="${item.domain}">${item.domain}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select name="guilberts_level1" id="guilberts_level1">
																		<option value="0">--Select--</option>
																		<c:forEach var="item"
																			items="${getguilberts_levelList}" varStatus="num">
																			<option value="${item.id}" name="${item.guilberts_level}">${item.guilberts_level}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select name="must_know_dknow_nk1"
																		id="must_know_dknow_nk1">
																		<option value="0">--Select--</option>
																		<c:forEach var="item"
																			items="${Nchget_Mk_Dk_Nk}" varStatus="num">
																			<option value="${item.id}" name="${item.scope}">${item.scope}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select name="tl_mthd_med1" id="tl_mthd_med1">
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${Nchgett_l_method}"
																			varStatus="num">
																			<option value="${item.id}" name="${item.method}">${item.method}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select name="formtive_assessmt1" id="formtive_assessmt1">
																		<option value="0">--Select--</option>
																		<c:forEach var="item"
																			items="${getnch_formative_and_summativeList}" varStatus="num">
																			<option value="${item.id}" name="${item.formative}">${item.formative}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select name="sumtive_assessmt1" id="sumtive_assessmt1">
																		<option value="0">--Select--</option>
																		<c:forEach var="item"
																			items="${getnch_formative_and_summativeList}" varStatus="num">
																			<option value="${item.id}" name="${item.formative}">${item.formative}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="input-style-1">
																<input type="text" id="integ_horivtspi1"
																	name="integ_horivtspi1"
																	class="autocomplete UpperClassName txt-transupp"
																	autocomplete="off" maxlength="100"
																	placeholder="Integration Horizontal/ Vertical/ Spiral" />

															</div>
														</td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select name="nch_term1" id="nch_term1">
																		<option value="0">--Select--</option>
<%-- 																		<c:forEach var="item" items="${geti3_termList}" --%>
<%-- 																			varStatus="num"> --%>
<%-- 																			<option value="${item.id}" name="${item.term}">${item.term}</option> --%>
<%-- 																		</c:forEach> --%>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<ul class="buttons-group">
																<li value="ADD" title="ADD" id="id_add_att1"><a
																	class="main-btn success-btn btn-hover btn-sm btnaddmore"
																	title="Add"><i class="lni lni-plus"></i></a></li>

															</ul>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</section>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a href="Search_Nch_T4_Learning_Object_Url"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li><input value="Save" id="btn-save"
												class="main-btn info-btn btn-hover btnsave" type="button" /></li>
											<li><a href="Nch_T4_Learning_Object_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
											<li><a id="btn-view"
												class="main-btn dark-btn btn-hover btn-iconic-icon btnview"
												type="button"><i class="lni lni-eye"></i>View</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
						<div id="pop">
							<div class="tables-wrapper">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="card-style mb-30">
											<div class="custom-multi-table">
												<h6 class="mb-10">Table 4: Learning objectives
													(Practical)</h6>
												<div
													class="table-wrapper table-responsive custom-table custom-table-v2">
													<table class="table table-striped" id="pop">
														<thead>
															<tr>

																<!-- 										<h6 class="mb-10">Table 4: Learning objectives (Practical)</h6> -->
																<!-- <!-- 											<h3>Table 4: Learning objectives (Practical)</h3> -->
																<!-- 											<div class="table-wrapper table-responsive custom-table" -->
																<!-- 												id="container-table"> -->
																<!-- 												<table class="table" id="pop"> -->
																<!-- 													<thead> -->
																<!-- 														<tr> -->

																<th><h6>Generic Competency</h6></th>
																<th><h6>Subject Area</h6></th>
																<th><h6>Millers Knows/Knows how/ Shows
																		how/Does</h6></th>
																<th><h6>Specific Competency</h6></th>
																<th><h6>Special learning objectives</h6></th>
																<th><h6>Blooms Domain</h6></th>
																<th><h6>Guilberts level</h6></th>
																<th><h6>Must know/ Desire to know/ Nice to
																		know</h6></th>
																<th><h6>TL Method/Media</h6></th>
																<th><h6>Formative Assessment</h6></th>
																 <th><h6>Summative assessment</h6></th>
																<th><h6>Integration Horizontal/ Vertical/
																		Spiral</h6></th>  
															  <th><h6>Term </h6></th>  

															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$("#pop").hide();
		
		
		//console.log('${getnch_formative_and_summativeList}')
		

	});

	function getpop() {
		var course_id = $("#course_id").val();
		//alert(course_id);
		var practical = "";
		$.post("NchT4SearchLearningObject_ChildUrl?" + key + "=" + value, {
			course_id : course_id
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				if (i == 0) {
					practical = j[i][0];
					$("table#pop").append(
							'<tr><td colspan="13"><p>Practical - '
							+ j[i][0] + '</p></td></tr>'
							
							+ '<tr>' + '<td><p id="generic_compte">'
							+ j[i][1] + '</p></td>'
							
							+ '<td><p id="subject_area">' + j[i][2]
							+ '</p></td>'
							
							+ '<td><p id="millers_knows">' + j[i][3]
							+ '</p></td>'
							
							+ '<td><p id="specific_compet">' + j[i][4]
							+ '</p></td>'
							
							+ '<td><p id="spec_learn_object">' + j[i][5]
							+ '</p></td>'
							
							+ '<td><p id="blooms_domain">' + j[i][6]
							+ '</p></td>'
							
							+ '<td><p id="guilberts_level">'  +j[i][7]
							+  '</p></td>'
							
							+ '<td><p id="must_know_dknow_nk">' + j[i][8]
						    + '</p></td>'
						    
							+ '<td><p id="tl_mthd_med">' + j[i][9]
							+ '</p></td>'
							
							+ '<td><p id="formtive_assessmt">' + j[i][10]
							+ '</p></td>'
							
							+ '<td><p id="sumtive_assessmt">' + j[i][11]
							+ '</p></td>'
							
							+ '<td><p id="integ_horivtspi">' + j[i][12]
							+ '</p></td>'
							
							+ '<td><p id="nch_term">' + j[i][13]
							+ '</p></td>' + '</tr>');
				} else {
					if (practical != j[i][0]) {
						practical = j[i][0];
						$("table#pop").append(
								'<tr><td colspan="13"><p>Practical - '
										+ j[i][0] + '</p></td></tr>'
										+ '<tr>' + '<td><p id="generic_compte">'
										+ j[i][1] + '</p></td>'
										+ '<td><p id="subject_area">' + j[i][2]
										+ '</p></td>'
										+ '<td><p id="millers_knows">' + j[i][3]
										+ '</p></td>'
										+ '<td><p id="specific_compet">' + j[i][4]
										+ '</p></td>'
										+ '<td><p id="spec_learn_object">' + j[i][5]
										+ '</p></td>'
										+ '<td><p id="blooms_domain">' + j[i][6]
										+ '</p></td>'
										+ '<td><p id="guilberts_level">'  +j[i][7]
										+  '</p></td>'
										+ '<td><p id="must_know_dknow_nk">' + j[i][8]
									    + '</p></td>'
										+ '<td><p id="tl_mthd_med">' + j[i][9]
										+ '</p></td>'
										+ '<td><p id="formtive_assessmt">' + j[i][10]
										+ '</p></td>'
										+ '<td><p id="sumtive_assessmt">' + j[i][11]
										+ '</p></td>'
										+ '<td><p id="integ_horivtspi">' + j[i][12]
										+ '</p></td>' 
										+ '<td><p id="nch_term">' + j[i][13]
										+ '</p></td>' + '</tr>');
					} else if (practical == j[i][0]) {
						$("table#pop").append(
								'<tr><td colspan="12"><p>Practical - '
								+ j[i][0] + '</p></td></tr>'
								+ '<tr>' + '<td><p id="generic_compte">'
								+ j[i][1] + '</p></td>'
								+ '<td><p id="subject_area">' + j[i][2]
								+ '</p></td>'
								+ '<td><p id="millers_knows">' + j[i][3]
								+ '</p></td>'
								+ '<td><p id="specific_compet">' + j[i][4]
								+ '</p></td>'
								+ '<td><p id="spec_learn_object">' + j[i][5]
								+ '</p></td>'
								+ '<td><p id="blooms_domain">' + j[i][6]
								+ '</p></td>'
								+ '<td><p id="guilberts_level">'  +j[i][7]
								+  '</p></td>'
								+ '<td><p id="must_know_dknow_nk">' + j[i][8]
							    + '</p></td>'
								+ '<td><p id="tl_mthd_med">' + j[i][9]
								+ '</p></td>'
								+ '<td><p id="formtive_assessmt">' + j[i][10]
								+ '</p></td>'
								+ '<td><p id="sumtive_assessmt">' + j[i][11]
								+ '</p></td>'
								+ '<td><p id="integ_horivtspi">' + j[i][12]
								+ '</p></td>'
								+ '<td><p id="nch_term">' + j[i][13]
								+ '</p></td>' + '</tr>');
					}
				}
			}
		});
		$("#pop").show();
	}

	function getdegreelistbysystem() {
		var system_name = $("#system_id").val();
		$
				.post('getDegreeListbysystem1?' + key + "=" + value, {
					system_name : system_name
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#degree_id").html(options);
						});
	}
	function getcourselistbysystemdegreeprof() {
		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();

		$
				.post('getCourseList_for_Curri?' + key + "=" + value, {
					degree_id : degree_id,
					system_id : system_id,
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
	function getTerm_listByProf(R) {
		var system = $("#system_id").val();
		var degree = $("#degree_id").val();
		var professional = $("#professional_id").val();

		$
				.post('getTerm_listByProf_for_Curri?' + key + "=" + value, {
					system : system,
					degree : degree,
					professional : professional
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#nch_term" +R).html(options);
						});
	}
// 	function getPracticalListbyCourse() {

// 		var course_id = $("#course_id").val();
// 		alert(course_id)
// 		$
// 				.post('getPracticalListby_Course_NCH?' + key + "=" + value, {
// 					course_id : course_id
// 				})
// 				.done(
// 						function(j) {
// 							var options = '<option value="' + "0" + '">'
// 									+ "--Select--" + '</option>';
// 							for (var i = 0; i < j.length; i++) {
// 								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
// 										+ j[i][1] + '</option>';
// 							}
// 							$("select#practical_id").html(options);
// 						});
// 	}
	function getPracticallistbyCourse() {
		var course_id = $("#course_id").val();
		
	
		$
				.post('getCourseToPractical?' + key + "=" + value, {
					course_id : course_id
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#practical_id").html(options);
						});
	}

	function getcobycourse(ser) {
		var course_id = $("#course_id").val();
		var professional_id = $("#professional_id").val();
		$
				.post('getCobyCourse?' + key + "=" + value, {
					course_id : course_id
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#a3_couse_outcome" + ser).html(options);
						});
	}

	//Add-More-Add
	var att = 1;
	function formopen_att(R) {
		$("#att_Tb").show();

		$("#id_add_att" + R).hide();
		$("#att_id_remove" + R).hide();

		att = 0;
		att = parseInt(R) + 1;

		if (att < 51) {

			$("input#count_hidden_att").val(att);//current serial No
			$("table#att_Tb")
			.append(
					'<tr align="center" id="tr_id_att'+att+'"><td>'
					+ att
					+ '</td>'
									 + '<td><div class="input-style-1"><input type="text" id="generic_compte'+att+'"name="generic_compte'+att+'"class="autocomplete UpperClassName txt-transupp"autocomplete="off" maxlength="100" placeholder="Generic Competency"></div></td>'
									
									+ '<td class="min-width"><div class="input-style-1"><input type="text" id="subject_area'+att+'"name="subject_area'+att+'"class="autocomplete UpperClassName txt-transupp"autocomplete="off" maxlength="100" placeholder="Subject Area"></div></td>'
									
									+ '<td><div class="select-style-1"><div class="select-position"><select id="millers_knows'+att+'" name="millers_knows'+att+'"class="form-control"><option value="0">--Select--</option>'
									+ '<c:forEach var="item" items="${getMillers_level}" varStatus="num"><option value="${item.id}" name="${item.rep_und}">${item.rep_und}</option></c:forEach>'
									+ '</select></div></div> </td>'
									
									+ '<td><div class="input-style-1"><input type="text" id="specific_compet'+att+'"name="specific_compet'+att+'"maxlength="250" class="form-control"autocomplete="off" placeholder=" Specific Competency"></div></td>'
									
									+ '<td><div class="input-style-1"><input type="text" id="spec_learn_object'+att+'"name="spec_learn_object'+att+'"maxlength="250" class="form-control"autocomplete="off" placeholder="Special learning objectives"></div></td>'
									
									+ '<td><div class="select-style-1"><div class="select-position"><select id="blooms_domain'+att+'" name="blooms_domain'+att+'" class="form-control"><option value="0">--Select--</option>'
									+ '<c:forEach var="item" items="${NchgetBloom_Domain}" varStatus="num"><option value="${item.id}" name="${item.domain}">${item.domain}</option></c:forEach>'
									+ '</select></div></div> </td>'
									
									+ '<td><div class="select-style-1"><div class="select-position"><select id="guilberts_level'+att+'" name="guilberts_level'+att+'" class="form-control"><option value="0">--Select--</option>'
									+ '<c:forEach var="item" items="${getguilberts_levelList}" varStatus="num"><option value="${item.id}" name="${item.guilberts_level}">${item.guilberts_level}</option></c:forEach>'
									+ '</select></div></div> </td>'
									
									+ '<td><div class="select-style-1"><div class="select-position"><select id="must_know_dknow_nk'+att+'" name="must_know_dknow_nk'+att+'" class="form-control"><option value="0">--Select--</option>'
									+ '<c:forEach var="item" items="${Nchget_Mk_Dk_Nk}" varStatus="num"><option value="${item.id}" name="${item.scope}">${item.scope}</option></c:forEach>'
									+ '</select></div></div> </td>'
									
									+ '<td><div class="select-style-1"><div class="select-position"><select id="tl_mthd_med'+att+'" name="tl_mthd_med'+att+'" class="form-control"><option value="0">--Select--</option>'
									+ '<c:forEach var="item" items="${Nchgett_l_method}" varStatus="num"><option value="${item.id}" name="${item.method}">${item.method}</option></c:forEach>'
									+ '</select></div></div> </td>'
									
									+ '<td><div class="select-style-1"><div class="select-position"><select id="formtive_assessmt'+att+'" name="formtive_assessmt'+att+'" class="form-control">'
									+ '<option value="0">--Select--</option><c:forEach var="item" items="${getnch_formative_and_summativeList}" varStatus="num"><option value="${item.id}" >${item.formative}</option></c:forEach></select></div></div></td>'
									
									+ '<td><div class="select-style-1"><div class="select-position"><select id="sumtive_assessmt'+att+'" name="sumtive_assessmt'+att+'" class="form-control" >'
									+ '<option value="0">--Select--</option><c:forEach var="item" items="${getnch_formative_and_summativeList}" varStatus="num"><option value="${item.id}" >${item.formative}</option></c:forEach></select></div></div></td>'
						
									+ '<td class="min-width"> <div class="input-style-1"><input type="text" id="integ_horivtspi'+att+'" name="integ_horivtspi'+att+'"  maxlength="250" class="form-control"  autocomplete="off" placeholder="Integration Horizontal/ Vertical/ Spiral"></div></td>'
									 
									+ '<td><div class="select-style-1"><div class="select-position"><select id="nch_term'+att+'" name="nch_term'+att+'" class="form-control">'
									+ '<option value="0">--Select--</option> </select></div></div></td>'

									+ '<td><ul class="buttons-group"><li value = "ADD" title = "ADD" id = "id_add_att'+att+'" ><a class="main-btn success-btn btn-hover btn-sm"><i class="lni lni-plus"></i></a></li><li value="REMOVE" title = "REMOVE" id = "att_id_remove'+att+'" ><a class="main-btn danger-btn btn-hover btn-sm"><i class="lni lni-trash-can"></i></a></li></td>'
									+ '</tr>');
			 
			addOnclick(att);
			removeOnclick(att);
			getcobycourse(att);
			getTerm_listByProf(att);
		} else {
			alert("Please Enter max 50 Quantity");
			if (att == 51) {
				att = att - 1;
				$("#rp_id_remove" + att).show();
			}
		}
		var curcnt = $("#count_hidden_att").val();
		$("#new_count_hidden").val(curcnt);
	}
	//Add-More-Remove
	function formopen_re_att(R) {
		var del_index = $("#idofprocedure" + R).val();
		if (String(del_index) == "undefined") {
			del_index = "0";
		} else {
			del_index = del_index;
		}
		var del_field_val = $("#del_id_hidden").val();

		if (del_field_val == "0,undefined") {
			$("#del_id_hidden").val(del_index);
		} else {
			$("#del_id_hidden").val(del_field_val + "," + del_index);
		}
		$("tr#tr_id_att" + R).remove();
		att = att - 1;
		$("input#count_hidden_att").val(att);
		if (R > 2) {
			$("#id_add_att" + att).show();
			$("#att_id_remove" + att).show();
		}
		if (R == 2) {
			$("#id_add_att" + att).show();
		}
		var ncc = $("#new_count_hidden").val();
		ncc = ncc - 1;
		$("#new_count_hidden").val(ncc);
	}

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('btn-save').onclick = function() {
			if (Validation()) {
				$("#nch_t4_learning_object").submit();
			}
		};

		document.getElementById('system_id').onchange = function() {
			getdegreelistbysystem();
		};
		document.getElementById('btn-view').onclick = function() {
			return View_Validation();
		};
		document.getElementById('professional_id').onchange = function() {
			getcourselistbysystemdegreeprof();
			getTerm_listByProf(1);
		};
		document.getElementById('course_id').onchange = function() {
			getPracticallistbyCourse();
			getcobycourse(1);
		};
		document.getElementById('practical_id').onchange = function() {
			Nchgetlearn_outListby_Practical();
		};
		
		document.getElementById('id_add_att1').onclick = function() {
			formopen_att(1)
		};
		document.getElementById('div_iv_fluids').onclick = function() {
			showCheckboxes(this);
		};
		// 	document.getElementById('btn-view').onclick = function() {
		// 		getpop();
		// 	};
	});

	function Validation() {
		if ($("#system_id").val() == "0") {
			alert("Please Select System.");
			$("select#system_id").focus();
			return false;
		}
		if ($("#degree_id").val() == "0") {
			alert("Please Select Degree.");
			$("select#degree_id").focus();
			return false;
		}
		if ($("#professional_id").val() == "0") {
			alert("Please Select professional.");
			$("select#professional_id").focus();
			return false;
		}
		if ($("#course_id").val() == "0") {
			alert("Please Select Subject .");
			$("select#course_id").focus();
			return false;
		}
		if ($("#practical_id").val() == "0") {
			alert("Please Select Practical.");
			$("select#practical_id").focus();
			return false;
		}
		 var count = $("#count_hidden_att").val();
	
		  for (var i = 1; i <= count; i++) {
			  
			 if ($("#generic_compte" + i).val() == "") {
				alert("Please Enter Generic Competency.");
				$("#generic_compte" + i).focus();
				return false;
			}
			if ($("#subject_area" + i).val() == "") {
				alert("Please Enter Subject Area.");
				$("#subject_area" + i).focus();
				return false;
			}
			if ($("#millers_knows" + i).val() == "0") {
				alert("Please Select Millers Knows/Knows how/ Shows how/Does.");
				$("select#millers_knows" + i).focus();
				return false;
			}
			
			if ($("#specific_compet"+i).val() == "") {
	 			alert("Please Enter Specific Competency.");
	 			$("#specific_compet"+i).focus();
	 			return false;
	 		}
			if ($("#spec_learn_object"+i).val() == "") {
	 			alert("Please Enter Special learning objectives.");
	 			$("#spec_learn_object"+i).focus();
	 			return false;
	 		}
			if ($("#blooms_domain" + i).val() == "0") {
				alert("Please Select Blooms Domain.");
				$("select#blooms_domain" + i).focus();
				return false;
			}
			if ($("#guilberts_level" + i).val() == "0") {
				alert("Please Select Guilberts level.");
				$("select#guilberts_level" + i).focus();
				return false;
			}
			if ($("#must_know_dknow_nk" + i).val() == "0") {
				alert("Please Select Must know/ Desire to know/ Nice to know.");
				$("select#must_know_dknow_nk" + i).focus();
				return false;
			}
			if ($("#tl_mthd_med"+i).val() == "0") {
	 			alert("Please Enter TL Method/Media.");
	 			$("#j3_integration"+i).focus();
	 			return false;
	 		}
		
			if ($("#formtive_assessmt" + i).val() == "0") {
				alert("Please Select Formative Assessment.");
				$("select#formtive_assessmt" + i).focus();
				return false;
			}

			if ($("#sumtive_assessmt" + i).val() == "0") {
				alert("Please Select Summative assessment.");
				$("select#sumtive_assessmt" + i).focus();
				return false;
			}
			if ($("#integ_horivtspi"+i).val() == "") {
	 			alert("Please Enter Integration Horizontal/ Vertical/Spiral.");
	 			$("#integ_horivtspi"+i).focus();
	 			return false;
	 		}
			if ($("#nch_term"+i).val() == "0") {
	 			alert("Please Select Term.");
	 			$("#integ_horivtspi"+i).focus();
	 			return false;
	 		}
		 		
		}  
		  
		return true;
	} 
	
	function View_Validation() {
		if ($("#system_id").val() == "0") {
			alert("Please Select System.");
			$("select#system_id").focus();
			return false;
		}
		if ($("#degree_id").val() == "0") {
			alert("Please Select Degree.");
			$("select#degree_id").focus();
			return false;
		}
		if ($("#professional_id").val() == "0") {
			alert("Please Select professional.");
			$("select#professional_id").focus();
			return false;
		}
		if ($("#course_id").val() == "0") {
			alert("Please Select Subject .");
			$("select#course_id").focus();
			return false;
		}
		getpop();
		return true;
	}
	function addOnclick(index) {
		document.getElementById('id_add_att' + index).onclick = function() {
			formopen_att(index);
		};
	}
	function removeOnclick(index) {
		document.getElementById('att_id_remove' + index).onclick = function() {
			formopen_re_att(index);
		};
	}
	
	function Nchgetlearn_outListby_Practical() {
	
		var practical_id = $("#practical_id").val();
	
				$.post('Nchgetlearn_outListby_Practical?' + key + "=" + value, {
					practical_id : practical_id
				})
				.done(function(j) {
					$("div#div_cb_dd").empty();
					for(var p=0;p<j.length;p++){
						var q = p+1;
						
						
						$("div#div_cb_dd")
						.append(
							'<input class="multi form-check-input mr-5" type="checkbox" id="learning_outcome'+q+'" name="learning_outcome" value="'+j[p][0]+'"/>'
							+'<label class="lbl"  for="first">'+j[p][1]+'</label>');
						setonclickofCBDD(q);
						}
			});
	}



	function checkoptioninedit(sids){
		var len = "";
		
		var practical_id = $("#practical_id").val();
		$.post('Nchgetlearn_outListby_Practical?' + key + "=" + value, {
			practical_id : practical_id
				}).done(function(j) {
					
					len = j.length;
					
					$("div#div_cb_dd").empty();
							for(var p=0;p<len;p++){
								var q = p+1;
							
							$("div#div_cb_dd")
							.append(
								'<input class="multi form-check-input mr-5" type="checkbox" id="learning_outcome'+q+'" name="learning_outcome" value="'+j[p][0]+'"/>'
								+'<label class="lbl"  for="first">'+j[p][1]+'</label>');
							setonclickofCBDD(q);
							}
				});
		
		var match = sids.split(":");
		for (var a in match)
		{
		    var variable = match[a];
		    	for(var i = 1;i<= len ; i++){
					var temp_data = $('#learning_outcome' + i).val();
					if(variable.trim() == temp_data.trim()){
						$('#learning_outcome' + i).click();
					}
		    	}
		}
	}

	function mycheckindex(myindex) {
		var gsida = [];
		var ele = document.getElementsByName("learning_outcome");

		console.log("ele.length - " + ele.length);
		for (var i = 0; i < ele.length; i++) {
			if (ele[i].checked) {
				/* gsida.push(gsid[i].value); */
				gsida.push(ele[i].value);
				/* remarksa.push(remarks[i].value); */
			}
		}
		console.log(myindex);
		document.getElementById('learning_outcome_hid').value = gsida
				.toString();
	}

	var show = true;
	var temp;
	function showCheckboxes(obj) {

		var checkboxes = obj.id + "_2";
		var checkboxRead = checkboxes.substring(4, checkboxes.length);
		checkboxRead = checkboxRead.substring(0, checkboxRead.length - 2);
		var data_check = $("#" + checkboxRead).is('[readonly]');
		if (show && data_check == false) {
			$("#" + checkboxes).show();
			temp = checkboxes;
			show = false;
		} else {
			$("#" + checkboxes).hide();
			show = true;
		}
		window.addEventListener('mouseup', function(event) {
			var pol = document.getElementById(temp)
			if (event.target != pol
					&& event.target.parentNode.parentNode != pol) {
				pol.style.display = 'none';
			}
		});
	}
	function setonclickofCBDD(obj){
		document.getElementById('learning_outcome'+obj).onclick = function() {
			mycheckindex(obj);
		};
	}
</script>