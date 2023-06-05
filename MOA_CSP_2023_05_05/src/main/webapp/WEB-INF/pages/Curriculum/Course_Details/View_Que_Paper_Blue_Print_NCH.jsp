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

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span>T6-g-Question Paper Blue Print 
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">T6-g-Question
									Paper Blue Print</li>
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
					<form:form name="course" id="view_que_paper_blue_print_nchAction"
						action="view_que_paper_blue_print_nchAction" method='POST'
						commandName="view_que_paper_blue_print_nchCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">T6-g-Question Paper Blue Print</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
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
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
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
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
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
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="username">Subject<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="course_id" id="course_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${CourseList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select> <input type="hidden" id="count_hidden_att"
													name="count_hidden_att" class="form-control autocomplete"
													value="1">
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
											<li><a href="View_Que_Paper_Blue_Print_NCH_url"
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
					</form:form>
				</div>
			</div>

			<div id="pop">
				<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="card-style mb-30">
							<div class="custom-multi-table">
								<h6>Question paper Blue print</h6>

								<div
									class="table-wrapper table-responsive custom-table custom-table-v2">
									<!-- id="container-table" -->
									<table class="table table-striped" id="pop">

										<thead>
											<tr>
												<th colspan="3"><h6 id="lbl_paper_heading_1"></h6></th>
											</tr>
											<tr>
												<th><h6>Sr No.</h6></th>
												<th><h6>Question Type</h6></th>
												<th><h6>Set</h6></th>
											</tr>

										</thead>
										<tbody id="tbodySR1">

										</tbody>
									</table>
								</div>
							</div>
						</div>
						
						</div>
						</div>
						
						</div>
						<div id="pop2">
							<div class="row">
							
								<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="card-style mb-30">
									<div class="custom-multi-table">

										<div
											class="table-wrapper table-responsive custom-table custom-table-v2">
											<!-- id="container-table" -->
											<table class="table table-striped" id="pop2">


												<thead>
													<tr>
														<th colspan="3"><h6 id="lbl_paper_heading_2"
																></h6></th>
													</tr>
													<tr>
														<th><h6>Sr No.</h6></th>
														<th><h6>Question
																Type</h6></th>
														<th><h6>Set</h6></th>
													</tr>

												</thead>
												<tbody id="tbodySR2">

												</tbody>
											</table>
										</div>
									</div>
									</div>
									
								</div>
							</div>
					</div>
					
		</div>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	$.ajaxSetup({
		async : false
	});
	$(document).ready(function() {
		$("#pop").hide();
		$("#pop2").hide();
	});

	// //2nd pop

	function getpop() {
		var course_id = $("#course_id").val();
		$
				.post(
						"getView_QuePaperBluePrintNCHviewdata?" + key + "="
								+ value,
						{
							course_id : course_id
						},
						function(j) {
							debugger;

							$("tr#popTR").empty();

							var paper = "";
							var quetyp = "";
							var noofpaper = 0;
							var ser = 1;

							for (var i = 0; i < j.length; i++) {

								paper = j[i][5];
								quetyp = j[i][1];

								if (paper == "PAPER I") {

									$("#lbl_paper_heading_1").text(paper);

									if (quetyp == "MULTIPLE CHOICE QUESTIONS") {
										$("tbody#tbodySR1")
												.append(
														'<tr id="tr_p1_mcq'+ser+'"> <td><p class="ml-5 bold"> '
																+ ser
																+ '</p></td>'
																+ '<td><ul>'
																+ '<li><p id="multiple_choice'+ser+'" class="ml-5 bold">'
																+ j[i][1]
																+ '</p></li>'
																+ '<li><p id="que'+ser+'" class="ml-5 bold">'
																+ j[i][2]
																+ '</p></li>'
																+ '<li><p id="marks_each'+ser+'" class="ml-5 bold">'
																+ j[i][3]
																+ ' mark each</p></li>'
																+ '<li><p id="all_compulsory'+ser+'" class="ml-5 bold">'
																+ j[i][4]
																+ '</p></li><br>'
																+ '<li><p>Must Know Part : 15 MCQ</p></li><br>'
																+ '<li><p>Desirable to Know : 3 MCQ</p></li><br>'
																+ '<li><p>Nice to Know : 2 MCQ</p></li>'
																+ '</ul>'
																+ '</td>'
																+ '<td id="set'+ser+'"></td></tr>'

												);

										$
												.post(
														"getPaper_Format_NCH_data?"
																+ key + "="
																+ value,
														{
															course_id : course_id,
															mk_dk : '16,17,18',
															qt : 'mcq',
															paper : paper
														},
														function(j) {
															$("td#set" + ser)
																	.empty();
															for (var i = 0; i < j.length; i++) {
																$(
																		"td#set"
																				+ ser)
																		.append(
																				'<p class="ml-5 bold"> '
																						+ j[i][0]
																						+ ' </p><br>');
															}
														});

									}

									if (quetyp == "SHORT ANSWER QUESTIONS") {
										$("tbody#tbodySR1")
												.append(
														'<tr id="tr_p1_saq'+ser+'"> <td><p class="ml-5 bold"> '
																+ ser
																+ ' </p></td>'
																+ '<td><ul>'
																+ '<li><p id="multiple_choice'+ser+'" class="ml-5 bold">'
																+ j[i][1]
																+ '</p></li>'
																+ '<li><p id="que'+ser+'" class="ml-5 bold">'
																+ j[i][2]
																+ '</p></li>'
																+ '<li><p id="marks_each'+ser+'" class="ml-5 bold">'
																+ j[i][3]
																+ ' mark each</p></li>'
																+ '<li><p id="all_compulsory'+ser+'" class="ml-5 bold">'
																+ j[i][4]
																+ '</p></li><br>'
																+ '<li><p>Must Know Part : 7 SAQ</p></li><br>'
																+ '<li><p>Desirable to Know : 1  SAQ</p></li><br>'
																+ '<li><p>Nice to Know : Nil</p></li>'
																+ '</ul>'
																+ '</td>'
																+ '<td id="set'+ser+'"></td></tr>'

												);

										$
												.post(
														"getPaper_Format_NCH_data?"
																+ key + "="
																+ value,
														{
															course_id : course_id,
															mk_dk : '16,17',
															qt : 'saq',
															paper : paper
														},
														function(j) {
															$("td#set" + ser)
																	.empty();
															for (var i = 0; i < j.length; i++) {
																$(
																		"td#set"
																				+ ser)
																		.append(
																				'<p class="ml-5 bold"> '
																						+ j[i][0]
																						+ ' </p><br>');
															}
														});

									}
									if (quetyp == "LONG ANSWER QUESTIONS") {
										$("tbody#tbodySR1")
												.append(
														'								<tr id="tr_p1_laq'+ser+'"><td><p class="ml-5 bold"> '
																+ ser
																+ ' </p></td>'
																+ '<td><ul>'
																+ '<li><p id="multiple_choice'+ser+'" class="ml-5 bold">'
																+ j[i][1]
																+ '</p></li>'
																+ '<li><p id="que'+ser+'" class="ml-5 bold">'
																+ j[i][2]
																+ '</p></li>'
																+ '<li><p id="marks_each'+ser+'" class="ml-5 bold">'
																+ j[i][3]
																+ ' mark each</p></li>'
																+ '<li><p id="all_compulsory'+ser+'" class="ml-5 bold">'
																+ j[i][4]
																+ '</p></li><br>'
																+ '<li><p>All questions on must know</p></li><br>'
																+ '<li><p>No Questions on Nice to know</p></li><br>'
																+ '<li><p>and Desirable to know</p></li>'
																+ '</ul>'
																+ '</td>'
																+ '<td id="set'+ser+'"></td></tr>');

										$
												.post(
														"getPaper_Format_NCH_data?"
																+ key + "="
																+ value,
														{
															course_id : course_id,
															mk_dk : '16',
															qt : 'laq',
															paper : paper
														},
														function(j) {
															$("td#set" + ser)
																	.empty();
															for (var i = 0; i < j.length; i++) {
																$(
																		"td#set"
																				+ ser)
																		.append(
																				'<p class="ml-5 bold"> '
																						+ j[i][0]
																						+ ' </p><br>');
															}
														});

									}
									ser++;
									noofpaper++;
								}
								if (paper == "PAPER II") {

									$("#lbl_paper_heading_2").text(paper);

									if (quetyp == "MULTIPLE CHOICE QUESTIONS") {
										$("tbody#tbodySR2")
												.append(
														'<tr id="tr_p2_mcq'+ser+'"> <td><p class="ml-5 bold"> '
																+ ser
																+ ' </p></td>'
																+ '<td><ul>'
																+ '<li><p id="multiple_choice'+ser+'" class="ml-5 bold">'
																+ j[i][1]
																+ '</p></li>'
																+ '<li><p id="que'+ser+'" class="ml-5 bold">'
																+ j[i][2]
																+ '</p></li>'
																+ '<li><p id="marks_each'+ser+'" class="ml-5 bold">'
																+ j[i][3]
																+ ' mark each</p></li>'
																+ '<li><p id="all_compulsory'+ser+'" class="ml-5 bold">'
																+ j[i][4]
																+ '</p></li><br>'
																+ '<li><p>Must Know Part : 15 MCQ</p></li><br>'
																+ '<li><p>Desirable to Know : 3 MCQ</p></li><br>'
																+ '<li><p>Nice to Know : 2 MCQ</p></li>'
																+ '</ul>'
																+ '</td>'
																+ '<td id="set'+ser+'"></td></tr>');

										$
												.post(
														"getPaper_Format_NCH_data?"
																+ key + "="
																+ value,
														{
															course_id : course_id,
															mk_dk : '16,17,18',
															qt : 'mcq',
															paper : paper
														},
														function(j) {
															$("td#set" + ser)
																	.empty();
															for (var i = 0; i < j.length; i++) {
																$(
																		"td#set"
																				+ ser)
																		.append(
																				'<p class="ml-5 bold"> '
																						+ j[i][0]
																						+ ' </p><br>');
															}
														});
									}
									if (quetyp == "SHORT ANSWER QUESTIONS") {
										$("tbody#tbodySR2")
												.append(
														'<tr id="tr_p2_saq'+ser+'"> <td><p class="ml-5 bold"> '
																+ ser
																+ ' </p></td>'
																+ '<td><ul>'
																+ '<li><p id="multiple_choice'+ser+'" class="ml-5 bold">'
																+ j[i][1]
																+ '</p></li>'
																+ '<li><p id="que'+ser+'" class="ml-5 bold">'
																+ j[i][2]
																+ '</p></li>'
																+ '<li><p id="marks_each'+ser+'" class="ml-5 bold">'
																+ j[i][3]
																+ ' mark each</p></li>'
																+ '<li><p id="all_compulsory'+ser+'" class="ml-5 bold">'
																+ j[i][4]
																+ '</p></li><br>'
																+ '<li><p>Must Know Part : 7 SAQ</p></li><br>'
																+ '<li><p>Desirable to Know : 1  SAQ</p></li><br>'
																+ '<li><p>Nice to Know : Nil</p></li>'
																+ '</ul>'
																+ '</td>'
																+ '<td id="set'+ser+'"></td></tr>');
										$
												.post(
														"getPaper_Format_NCH_data?"
																+ key + "="
																+ value,
														{
															course_id : course_id,
															mk_dk : '16,17',
															qt : 'saq',
															paper : paper
														},
														function(j) {
															$("td#set" + ser)
																	.empty();
															for (var i = 0; i < j.length; i++) {
																$(
																		"td#set"
																				+ ser)
																		.append(
																				'<p class="ml-5 bold"> '
																						+ j[i][0]
																						+ ' </p><br>');
															}
														});

									}
									if (quetyp == "LONG ANSWER QUESTIONS") {
										$("tbody#tbodySR2")
												.append(
														'<tr id="tr_p2_laq'+ser+'"><td><p class="ml-5 bold"> '
																+ ser
																+ ' </p></td>'
																+ '<td><ul>'
																+ '<li><p id="multiple_choice'+ser+'" class="ml-5 bold">'
																+ j[i][1]
																+ '</p></li>'
																+ '<li><p id="que'+ser+'" class="ml-5 bold">'
																+ j[i][2]
																+ '</p></li>'
																+ '<li><p id="marks_each'+ser+'" class="ml-5 bold">'
																+ j[i][3]
																+ ' mark each</p></li>'
																+ '<li><p id="all_compulsory'+ser+'" class="ml-5 bold">'
																+ j[i][4]
																+ '</p></li><br>'
																+ '<li><p>All questions on must know</p></li><br>'
																+ '<li><p>No Questions on Nice to know</p></li><br>'
																+ '<li><p>and Desirable to know</p></li>'
																+ '</ul>'
																+ '</td>'
																+ '<td id="set'+ser+'"></td></tr> ');

										$
												.post(
														"getPaper_Format_NCH_data?"
																+ key + "="
																+ value,
														{
															course_id : course_id,
															mk_dk : '16',
															qt : 'laq',
															paper : paper
														},
														function(j) {
															$("td#set" + ser)
																	.empty();
															for (var i = 0; i < j.length; i++) {
																$(
																		"td#set"
																				+ ser)
																		.append(
																				'<p class="ml-5 bold"> '
																						+ j[i][0]
																						+ ' </p><br>');
															}
														});
									}
									ser++;
									noofpaper++;
								}
							}
						});
		$("#pop").show();
		$("#pop2").show();
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

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('system_id').onchange = function() {
			getdegreelistbysystem();
		};
		document.getElementById('degree_id').onchange = function() {
			getcourselistbysystemdegreeprof();
		};
		document.getElementById('professional_id').onchange = function() {
			getcourselistbysystemdegreeprof();
		};
		document.getElementById('btn-view').onclick = function() {
			return View_Validation();
		};
		// 	document.getElementById('btn-view').onclick = function() {
		// 		getpop();
		// 	};

	});
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
			alert("Please Select Professional.");
			$("select#professional_id").focus();
			return false;
		}
		if ($("#course_id").val().trim() == "0") {
			alert("Please Select Subject.");
			$("select#course_id").focus();
			return false;
		}
		getpop();
		return true;
	}
</script>