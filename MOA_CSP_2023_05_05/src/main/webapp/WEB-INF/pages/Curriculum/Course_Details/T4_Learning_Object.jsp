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
							<span id="lbladd1"></span>T4 - Practical Learning Objectives
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">T4 -
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
					<form:form name="t4_learning_object" id="t4_learning_object"
						action="t4_learning_objectAction" method='POST'
						commandName="t4_learning_objectCMD" enctype="multipart/form-data">
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
											<input type="hidden" id="count_hidden_att"
												name="count_hidden_att" class="form-control autocomplete"
												value="1"> <input type='hidden' id='id' name="id"
												value='0' />
										</div>
									</div>
								</div>
							</div>
							<section class="single-detail-block">
								<div class="row">
									<div class="col-lg-12">
										<h6 class="mb-10"></h6>
										<div class="table-wrapper table-responsive custom-table  b-top mt-0">
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
																A3 Course Outcome<span class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																B3 Learning Objective(At the end of the session,the
																students should be able to)<span class="mandatory"
																	maxlength="1000">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																C3 Domain/sub<span class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																D3 Must to know/desirable to know/Nice to know<span
																	class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																E3 Level Does/Shows how/Knows how/Know<span
																	class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																F3 T-L method<span class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																G3 Assessment<span class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																H3 Formative/Summative<span class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																I3 Term<span class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>
																J3 Integration<span class="mandatory">*</span>
															</h6></th>
														<th rowspan="2"><h6>Action</h6></th>
													</tr>
												</thead>
												<tbody id="att_Tbbody">
													<tr id="tr_id_att">
														<td>1</td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select name="a3_couse_outcome1" id="a3_couse_outcome1">
																		<option value="0">--Select--</option>
																		<%-- 										<c:forEach var="item" items="${getCobyCourse}" varStatus="num"> --%>
																		<%-- 													<option value="${item.id}" name="${item.course_outcome}">${item.course_outcome}</option> --%>
																		<%-- 										</c:forEach> --%>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="input-style-1">
																<input type="text" id="b3_learning_obj1"
																	name="b3_learning_obj1"
																	class="autocomplete UpperClassName txt-transupp"
																	autocomplete="off" maxlength="100"
																	placeholder="B3 Learning Obj" />
															</div>
														</td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select name="c3_domain_sub1" id="c3_domain_sub1">
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${getc3_domain_subList}"
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
																	<select name="d3_desirable_know1"
																		id="d3_desirable_know1">
																		<option value="0">--Select--</option>
																		<c:forEach var="item"
																			items="${getd3_desirable_knowList}" varStatus="num">
																			<option value="${item.id}" name="${item.scope}">${item.scope}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select name="e3_level_show_know1"
																		id="e3_level_show_know1">
																		<option value="0">--Select--</option>
																		<c:forEach var="item"
																			items="${gete3_level_show_knowList}" varStatus="num">
																			<option value="${item.id}" name="${item.rep_und}">${item.rep_und}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select name="f3_t_l_method1" id="f3_t_l_method1">
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${getf3_t_l_methodList}"
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
																	<select name="g3_assessment1" id="g3_assessment1">
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${getg3_assessmentList}"
																			varStatus="num">
																			<option value="${item.id}"
																				name="${item.assessment_method}">${item.assessment_method}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select name="h3_formative_summative1"
																		id="h3_formative_summative1">
																		<option value="0">--Select--</option>
																		<c:forEach var="item"
																			items="${geth3_formative_summativeList}"
																			varStatus="num">
																			<option value="${item.id}"
																				name="${item.assessment_type}">${item.assessment_type}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select name="i3_term1" id="i3_term1">
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
															<div class="input-style-1">
																<input type="text" id="j3_integration1"
																	name="j3_integration1"
																	class="autocomplete UpperClassName txt-transupp"
																	autocomplete="off" maxlength="100"
																	placeholder="J3 Integrtion" />

															</div>
														</td>
														<td>
															<ul class="buttons-group">
																<li value="ADD" title="ADD" id="id_add_att1"><a
																 class="main-btn success-btn btn-hover btn-sm btnaddmore" title="Add"><i
																		class="lni lni-plus"></i></a></li>

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
											<li><a href="Search_T4_Learning_Object_Url"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li><input value="Save" id="btn-save"
												class="main-btn info-btn btn-hover btnsave" type="button" /></li>
											<li><a href="T4_Learning_Object_Url"
												class="main-btn dark-btn btn-hover btnreset"  type="button">Reset</a></li>
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
									<div class="col-lg-12">
										<div class="card-style mb-30">
										
										<h6 class="mb-10">Table 4: Learning objectives (Practical)</h6>
								<div class="table-wrapper table-responsive custom-table custom-table-v2">
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
															<th><h6 class="ml-5 bold">A4 Course
																	outcome</h6></th>
															<th><h6 class="ml-5 bold">B4 Learning
																	Objective (At the end of the session, the students
																	should be able to)</h6></th>
															<th><h6 class="ml-5 bold">C4 Domain /sub</h6></th>
															<th><h6 class="ml-5 bold">D4 Must to
																	know/desirable to know/Nice to know</h6></th>
															<th><h6 class="ml-5 bold">E4 Level Does/
																	Shows how/ Knows how/Know</h6></th>
															<th><h6 class="ml-5 bold">F4 T-L method</h6></th>
															<th><h6 class="ml-5 bold">G4 Assessment</h6></th>
															<th><h6 class="ml-5 bold">H4 Formative
																	/summative</h6></th>
															<th><h6 class="ml-5 bold">I4 Term</h6></th>
															<th><h6 class="ml-5 bold">J4 Integration</h6></th>
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
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$("#pop").hide();

	});

	function getpop() {
		var course_id = $("#course_id").val();
		var practical = "";
		 $("table#pop > tbody").empty();
		$.post("getView_T4_Screenviewdata?" + key + "=" + value, {
			course_id : course_id
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				if (i == 0) {
					practical = j[i][0];
					$("table#pop").append(
							'<tr><td colspan="10"><p>Practical - '
									+ j[i][0] + '</p></td></tr>' + '<tr>'
									+ '<td><p id="co_code">' + j[i][1]
									+ '</p></td>' + '<td><p id="b3">'
									+ j[i][2] + '</p></td>'
									+ '<td><p id="domain">' + j[i][3]
									+ '</p></td>'
									+ '<td><p id="scope">' + j[i][4]
									+ '</p></td>'
									+ '<td><p id="rep_und">' + j[i][5]
									+ '</p></td>'
									+ '<td><p id="method">' + j[i][6]
									+ '</p></td>'
									+ '<td><p id="assessment_method">'
									+ j[i][7] + '</p></td>'
									+ '<td><p id="assessment_type">'
									+ j[i][8] + '</p></td>'
									+ '<td><p id="term">' + j[i][9]
									+ '</p></td>' + '<td><p id="j3">'
									+ j[i][10] + '</p></td>' + '</tr>');
				} else {
					if (practical != j[i][0]) {
						practical = j[i][0];
						$("table#pop").append(
								'<tr><td colspan="10"><p>Practical - '
										+ j[i][0] + '</p></td></tr>'
										+ '<tr>' + '<td><p id="co_code">'
										+ j[i][1] + '</p></td>'
										+ '<td><p id="b3">' + j[i][2]
										+ '</p></td>'
										+ '<td><p id="domain">' + j[i][3]
										+ '</p></td>'
										+ '<td><p id="scope">' + j[i][4]
										+ '</p></td>'
										+ '<td><p id="rep_und">' + j[i][5]
										+ '</p></td>'
										+ '<td><p id="method">' + j[i][6]
										+ '</p></td>'
										+ '<td><p id="assessment_method">'
										+ j[i][7] + '</p></td>'
										+ '<td><p id="assessment_type">'
										+ j[i][8] + '</p></td>'
										+ '<td><p id="term">' + j[i][9]
										+ '</p></td>'
										+ '<td><p id="j3">' + j[i][10]
										+ '</p></td>' + '</tr>');
					} else if (practical == j[i][0]) {
						$("table#pop").append(
								'<tr>' + '<td><p id="co_code">' + j[i][1]
										+ '</p></td>'
										+ '<td><p id="b3">' + j[i][2]
										+ '</p></td>'
										+ '<td><p id="domain">' + j[i][3]
										+ '</p></td>'
										+ '<td><p id="scope">' + j[i][4]
										+ '</p></td>'
										+ '<td><p id="rep_und">' + j[i][5]
										+ '</p></td>'
										+ '<td><p id="method">' + j[i][6]
										+ '</p></td>'
										+ '<td><p id="assessment_method">'
										+ j[i][7] + '</p></td>'
										+ '<td><p id="assessment_type">'
										+ j[i][8] + '</p></td>'
										+ '<td><p id="term">' + j[i][9]
										+ '</p></td>'
										+ '<td><p id="j3">' + j[i][10]
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

	function getPracticalListbyCourse() {

		var course_id = $("#course_id").val();
		$
				.post('getPracticalListby_Course1?' + key + "=" + value, {
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
							$("select#i3_term" +R).html(options);
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
									+ '<td><div class="select-style-1"><div class="select-position"><select id="a3_couse_outcome'+att+'" name="a3_couse_outcome'+att+'" class="form-control" ><option value="0">--Select--</option>'
									+ '<c:forEach var="item" items="${getCourse_OutcomeList}" varStatus="num"><option value="${item.id}" name="${item.course_outcome}">${item.course_outcome}</option></c:forEach>'
									+ '</select></div></div> </td>'

									+ '<td class="min-width"><div class="input-style-1"><input type="text" id="b3_learning_obj'+att+'" name="b3_learning_obj'+att+'"  maxlength="250" class="form-control"  autocomplete="off" placeholder="B3 Learning Objective" ></div></td>'
									+ '<td><div class="select-style-1"><div class="select-position"><select id="c3_domain_sub'+att+'" name="c3_domain_sub'+att+'" class="form-control" ><option value="0">--Select--</option>'
									+ '<c:forEach var="item" items="${getc3_domain_subList}" varStatus="num"><option value="${item.id}" name="${item.domain}">${item.domain}</option></c:forEach>'
									+ '</select></div></div> </td>'
									+ '<td><div class="select-style-1"><div class="select-position"><select id="d3_desirable_know'+att+'" name="d3_desirable_know'+att+'" class="form-control" ><option value="0">--Select--</option>'
									+ '<c:forEach var="item" items="${getd3_desirable_knowList}" varStatus="num"><option value="${item.id}" name="${item.scope}">${item.scope}</option></c:forEach>'
									+ '</select></div></div> </td>'
									+ '<td><div class="select-style-1"><div class="select-position"><select id="e3_level_show_know'+att+'" name="e3_level_show_know'+att+'" class="form-control" ><option value="0">--Select--</option>'
									+ '<c:forEach var="item" items="${gete3_level_show_knowList}" varStatus="num"><option value="${item.id}" name="${item.rep_und}">${item.rep_und}</option></c:forEach>'
									+ '</select></div></div> </td>'
									+ '<td><div class="select-style-1"><div class="select-position"><select id="f3_t_l_method'+att+'" name="f3_t_l_method'+att+'" class="form-control" ><option value="0">--Select--</option>'
									+ '<c:forEach var="item" items="${getf3_t_l_methodList}" varStatus="num"><option value="${item.id}" name="${item.method}">${item.method}</option></c:forEach>'
									+ '</select></div></div> </td>'
									+ '<td><div class="select-style-1"><div class="select-position"><select id="g3_assessment'+att+'" name="g3_assessment'+att+'" class="form-control" ><option value="0">--Select--</option>'
									+ '<c:forEach var="item" items="${getg3_assessmentList}" varStatus="num"><option value="${item.id}" name="${item.assessment_method}">${item.assessment_method}</option></c:forEach>'
									+ '</select></div></div> </td>'
									+ '<td><div class="select-style-1"><div class="select-position"><select id="h3_formative_summative'+att+'" name="h3_formative_summative'+att+'" class="form-control" ><option value="0">--Select--</option>'
									+ '<c:forEach var="item" items="${geth3_formative_summativeList}" varStatus="num"><option value="${item.id}" name="${item.assessment_type}">${item.assessment_type}</option></c:forEach>'
									+ '</select></div></div> </td>'
									+ '<td><div class="select-style-1"><div class="select-position"><select id="i3_term'+att+'" name="i3_term'+att+'" class="form-control" ><option value="0">--Select--</option>'
// 									+ '<c:forEach var="item" items="${geti3_termList}" varStatus="num"><option value="${item.id}" name="${item.term}">${item.term}</option></c:forEach>'
									+ '</select></div></div> </td>'

									+ '<td class="min-width"><div class="input-style-1"><input type="text" id="j3_integration'+att+'" name="j3_integration'+att+'"  maxlength="250" class="form-control"  autocomplete="off" placeholder="J3 Integration" ></div></td>'

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
				$("#t4_learning_object").submit();
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
			getPracticalListbyCourse();
			getcobycourse(1);
		};
		document.getElementById('id_add_att1').onclick = function() {
			formopen_att(1)
		};
		// 	document.getElementById('btn-view').onclick = function() {
		// 		getpop();
		// 	};
	});

	function Validation() {
		if ($("#system_id").val().trim() == "0") {
			alert("Please Select System.");
			$("select#system_id").focus();
			return false;
		}
		if ($("#degree_id").val().trim() == "0") {
			alert("Please Select Degree.");
			$("select#degree_id").focus();
			return false;
		}
		if ($("#professional_id").val().trim() == "0") {
			alert("Please Select professional.");
			$("select#professional_id").focus();
			return false;
		}
		if ($("#course_id").val().trim() == "0") {
			alert("Please Select Subject .");
			$("select#course_id").focus();
			return false;
		}
		if ($("#practical_id").val().trim() == "0") {
			alert("Please Select Practical.");
			$("select#practical_id").focus();
			return false;
		}
		var count = $("#count_hidden_att").val();
		for (var i = 1; i <= count; i++) {

			if ($("#a3_couse_outcome" + i).val().trim() == "0") {
				alert("Please Select A3 Course Outcome.");
				$("#a3_couse_outcome" + i).focus();
				return false;
			}
			if ($("#b3_learning_obj" + i).val().trim() == "") {
				alert("Please Enter B3 Learning Objective.");
				$("#b3_learning_obj" + i).focus();
				return false;
			}
			if ($("#c3_domain_sub" + i).val().trim() == "0") {
				alert("Please Select C3 Domain/sub.");
				$("select#c3_domain_sub" + i).focus();
				return false;
			}
			if ($("#d3_desirable_know" + i).val().trim() == "0") {
				alert("Please Select D3 Must to know/desirable to know/Nice to know.");
				$("select#d3_desirable_know" + i).focus();
				return false;
			}
			if ($("#e3_level_show_know" + i).val().trim() == "0") {
				alert("Please Select E3 Level Does/Shows how/Knows how/Know.");
				$("select#e3_level_show_know" + i).focus();
				return false;
			}
			if ($("#f3_t_l_method" + i).val().trim() == "0") {
				alert("Please Select F3 T-L method.");
				$("select#f3_t_l_method" + i).focus();
				return false;
			}
			if ($("#g3_assessment" + i).val().trim() == "0") {
				alert("Please Select G3 Assessment.");
				$("select#g3_assessment" + i).focus();
				return false;
			}
			if ($("#h3_formative_summative" + i).val().trim() == "0") {
				alert("Please Select H3 Formative/Summative.");
				$("select#h3_formative_summative" + i).focus();
				return false;
			}
			if ($("#i3_term" + i).val().trim() == "0") {
				alert("Please Select I3 Term.");
				$("select#i3_term" + i).focus();
				return false;
			}
			// 		if ($("#j3_integration"+i).val().trim() == "") {
			// 			alert("Please Enter J3 Integration.");
			// 			$("#j3_integration"+i).focus();
			// 			return false;
			// 		}
		}
		return true;
	}
	function View_Validation() {
		if ($("#system_id").val().trim() == "0") {
			alert("Please Select System.");
			$("select#system_id").focus();
			return false;
		}
		if ($("#degree_id").val().trim() == "0") {
			alert("Please Select Degree.");
			$("select#degree_id").focus();
			return false;
		}
		if ($("#professional_id").val().trim() == "0") {
			alert("Please Select professional.");
			$("select#professional_id").focus();
			return false;
		}
		if ($("#course_id").val().trim() == "0") {
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
</script>