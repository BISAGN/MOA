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
<!-- <script type="text/javascript" src="js\watermark\common.js"></script> -->
<!-- <link href="js/dropDown/select2.min.css" rel="Stylesheet"></link> -->
<!-- <script src="js/dropDown/select2.min.js" type="text/javascript"></script> -->


<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Student Result Declaration</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Student
									Result Declaration</li>
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
					<form:form name="student_result_declare"
						id="student_result_declare" action="student_result_declareAction"
						method='POST' commandName="student_result_declareCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Student Result Declaration</h6>
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
												<select class="singleselect form-control form-control-lg"
													name="degree_id" id="degree_id">
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
													<c:forEach var="item" items="${getTerm}" varStatus="num">
														<option value="${item.id}" name="${item.term}">${item.term}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Institute<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="institute_id" id="institute_id"
													class="singleselect form-control form-control-lg">
													<option value="0" name="select">--Select--</option>
													<c:forEach var="item"
														items="${getinstitute_listbyuniversity}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-2">
											<label>Date of Exam<span class="mandatory">*</span></label> <input
												type="text" name="date_of_exam" id="date_of_exam"
												maxlength="10"
												class="form-control-sm form-control effect-9 "
												aria-required="true" autocomplete="off" value="DD/MM/YYYY"
												placeholder="DD/MM/YYYY">
											<!-- 																onclick="clickclear(this, 'DD/MM/YYYY')" -->
											<!-- 																onfocus="this.style.color='#000000'" -->
											<!-- 																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" -->
											<!-- 																onkeyup="clickclear(this, 'DD/MM/YYYY')" -->
											<!-- 																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" -->
										</div>


										<!-- 									<div class="select-style-1"> -->
										<!-- 										<label for="text-input"> Year<span -->
										<!-- 											class="mandatory">*</span></label> -->
										<!-- 										<div class="input-style-2"> -->
										<!-- 											<input type="month" id="mon_year" -->
										<!-- 												name="mon_year" -->
										<!-- 												class="form-control-sm form-control effect-9 hasDatepicker" > -->
										<!-- 										</div> -->
										<!-- 									</div> -->
									</div>
									<!-- 							<div class="col-12 col-sm-12 col-md-6 col-lg-4 d-none" id="subjectselect"> -->
									<!-- 										<div class="select-style-1"> -->
									<!-- 											<label for="text-input">Select Subject<span class="mandatory">*</span></label> -->
									<!-- 											<div class="select-position"> -->
									<!-- 											<select class="" id="div_iv_fluids" name=""> -->
									<!-- 												<option value="0" id="course_id1" class="hida overSelect">--Select--</option> -->
									<!-- 											</select> -->
									<%-- 											<c:forEach var="item" items="${Learning_OutcomeList}" varStatus="num"> --%>
									<!-- 											 <input type="hidden" id="course_id1" name="course_id" -->
									<!-- 												autocomplete="off" class="form-control-sm form-control" -->
									<!-- 												value=""> -->
									<%-- 												</c:forEach> --%>
									<!-- 										</div> -->
									<!-- 										<div id="div_iv_fluids_2" class="multiselect"> -->
									<!-- 											<div id="div_cb_dd" -->
									<!-- 												class="form-check radio-style checkbox-style align-items-center"> -->
									<!-- 											</div> -->
									<!-- 										</div> -->
									<!-- 										</div> -->

									<!-- 										<input type="hidden" id="course_id_hid" -->
									<!-- 										name="course_id_hid" class="form-control autocomplete"> -->

									<!-- 									</div> -->
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="Student_Result_Declare_url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<section class="single-detail-block">
							<div id="pop">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12" id="pop_data">
										<div class="card-style mb-30 ">

											<div class="col-12 col-sm-12 col-md-6 col-lg-4 d-none"
												id="subjectselect">
												<div class="select-style-1" id="subjecthide">
													<label for="text-input">Select Subject<span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select class="" id="div_iv_fluids" name="">
															<option value="0" id="course_id" class="hida overSelect">--Select--</option>
														</select>
														<c:forEach var="item" items="${Learning_OutcomeList}"
															varStatus="num">
															<input type="hidden" id="course_id1" name="course_id"
																autocomplete="off" class="form-control-sm form-control"
																value="">
														</c:forEach>
													</div>
													<div id="div_iv_fluids_2" class="multiselect">
														<div id="div_cb_dd"
															class="form-check radio-style checkbox-style align-items-center">
														</div>
													</div>
												</div>

												<input type="hidden" id="course_id_hid" name="course_id_hid"
													class="form-control autocomplete">

											</div>
											<div class="table-wrapper table-responsive custom-table"
												id="container-table">
												<table class="table">
													<thead>
														<tr>
															<th><h6>
																	<input class="form-check-input" type=checkbox
																		id='nSelAll' name='tregn'> Select all [<span
																		id="tregn">0</span><span id='nTotRow1'>/</span> <span
																		id="tregnn"> </span>]
																</h6></th>
															<th><h6>Sr No</h6></th>
															<th><h6>System</h6></th>
															<th><h6>Degree</h6></th>
															<th><h6>Professional</h6></th>
															<th><h6>Institute</h6></th>
															<th><h6>Ayush ID</h6></th>
															<th><h6>Student name</h6></th>
															<th><h6>Date of birth</h6></th>
															<th><h6>No of attempted</h6></th>
															<th><h6>No of detains</h6></th>
														</tr>
													</thead>
													<tbody id="tbody_pop">
													</tbody>
												</table>
											</div>

											<div class="col-lg-12 col-md-12 col-sm-12">
												<div id="checkheaddiv">
													<input type="hidden" id="CheckVal" name="CheckVal">
													<input type="hidden" id="CheckValAyu" name="CheckValAyu">
													<input type="hidden" id="CheckVal2" name="CheckVal2">
													<input type="hidden" id="CheckVal3" name="CheckVal3">
													<!-- 			<input class="form-check-input" type=checkbox id='nSelAll' name='tregn' >Select all [<span id="tregn">0</span><span -->
													<!-- 				id='nTotRow1'>/</span><span id="tregnn"> </span>] -->
													<div class="btn-bottom">
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12">
																<ul class="buttons-group mainbtn">
																	<li><input type="button"
																		class="main-btn success-btn btn-hover btnprom"
																		value="Promote" id="pass"></li>
																	<li><input type="button"
																		class="main-btn active-btn btn-hover btnsupple"
																		value="Supplementary" id="suppri"></li>
																	<li><input type="button"
																		class="main-btn danger-btn btn-hover btndeta"
																		value="Detain" id="fail"></li>
																</ul>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</section>
<!-- 						<section class="single-detail-block"> -->
<!-- 							<div id="pop_not"> -->
<!-- 								<div class="row"> -->
<!-- 									<div class="col-lg-12 col-md-12 col-sm-12"> -->
<!-- 										<div class="card-style mb-30"> -->
<!-- 											<div -->
<!-- 												class="table-wrapper table-responsive custom-table b-top" -->
<!-- 												id="container-table"> -->
<!-- 												<table class="table" id="pop1_not"> -->
<!-- 													<thead> -->

<!-- 														<tr class="learncount middle-center"> -->
<!-- 															<td colspan="9"><p> -->
<!-- 																	<br> No Data Available -->
<!-- 																</p></td> -->
<!-- 														</tr> -->

<!-- 													</thead> -->
<!-- 												</table> -->

<!-- 																						end table -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 																end card -->
<!-- 									</div> -->
<!-- 													end col -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</section> -->
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$("#pop").hide();
		$("#subjecthide").hide();
		mockjax1('search_Admission_Confirmation');
		table = dataTable('search_Admission_Confirmation');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		datepicketDate('date_of_exam');
		$("#pop1_not").hide();
		$("#pop_not").hide();

		if ($("#subjectselect").hasClass('d-none')) {
			$("#subjectselect").removeClass("d-none")
		}

	});

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('nSelAll').onclick = function() {
			setselectall();
		};
		document.getElementById('system_id').onchange = function() {
			getdegreelistbysystem();
		};
		document.getElementById('btn-reload').onclick = function() {
			getpop();
		};
		document.getElementById('nSelAll').onclick = function() {
			setselectall();
		};
		document.getElementById('pass').onclick = function() {
			return setPassStatus();
		};
		document.getElementById('suppri').onclick = function() {
			return setSupprimentryStatus();
		};
		document.getElementById('fail').onclick = function() {
			return setDetainStatus();
		};
		document.getElementById('professional_id').onchange = function() {
			Nchgetcourseby_professional();
		};
		document.getElementById('div_iv_fluids').onclick = function() {
			showCheckboxes(this);
		};
		// 		document.getElementById('course_id1').onclick = function() {
		// 			mycheckindex(1);
		// 		};
	});

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

	function Nchgetcourseby_professional() {
		debugger;

		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();

		if (professional_id == "4") {
			professional_id = "15";
		}
		if (professional_id == "5") {
			professional_id = "16";
		}
		if (professional_id == "6") {
			professional_id = "17";
		}

		$
				.post('getCourseList_for_Curri?' + key + "=" + value, {
					system_id : system_id,
					degree_id : degree_id,
					professional_id : professional_id
				})
				.done(
						function(j) {
							$("div#div_cb_dd").empty();
							for (var p = 0; p < j.length; p++) {
								var q = p + 1;

								$("div#div_cb_dd")
										.append(
												'<input class="multi form-check-input mr-5" type="checkbox" id="course_id'+q+'" name="course_id" value="'+j[p][0]+'"/>'
														+ '<label class="lbl"  for="first">'
														+ j[p][1] + '</label>');

								setonclickofCBDD(q);
							}
						});
	}

	function checkoptioninedit(sids) {
		var len = "";

		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();

		if (professional_id == "1") {
			professional_id = "15";
		}
		if (professional_id == "2") {
			professional_id = "16";
		}
		if (professional_id == "3") {
			professional_id = "17";
		}

		$
				.post('Nchgetlearn_outmeListby_Topic?' + key + "=" + value, {
					system_id : system_id,
					degree_id : degree_id,
					professional_id : professional_id
				})
				.done(
						function(j) {

							len = j.length;

							$("div#div_cb_dd").empty();
							for (var p = 0; p < len; p++) {
								var q = p + 1;

								$("div#div_cb_dd")
										.append(
												'<input class="multi form-check-input mr-5" type="checkbox" id="course_id'+q+'" name="course_id" value="'+j[p][0]+'"/>'
														+ '<label class="lbl"  for="first">'
														+ j[p][1] + '</label>');
								setonclickofCBDD(q);
							}
						});

		var match = sids.split(":");
		for ( var a in match) {
			var variable = match[a];
			for (var i = 1; i <= len; i++) {
				var temp_data = $('#course_id' + i).val();
				if (variable.trim() == temp_data.trim()) {
					$('#course_id' + i).click();
				}
			}
		}
	}

	function mycheckindex(myindex) {
		var gsida = [];
		var ele = document.getElementsByName("course_id");

		console.log("ele.length - " + ele.length);
		for (var i = 0; i < ele.length; i++) {
			if (ele[i].checked) {
				/* gsida.push(gsid[i].value); */
				gsida.push(ele[i].value);
				/* remarksa.push(remarks[i].value); */
			}
		}
		console.log(myindex);
		document.getElementById('course_id_hid').value = gsida.toString();
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

	function getpop() {

		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id option:selected").text();
		var institute_id = $("#institute_id").val();

		if (professional_id == "--Select--") {
			professional_id = "0";
		}

		// 		if(system_id == "0"){
		// 			alert("Please Select System");
		// 			return false;
		// 		}
		// 		if(degree_id == "0"){
		// 			alert("Please Select Degree");
		// 			return false;
		// 		}
		// 		if(professional_id == ""){
		// 			alert("Please Select Professional");
		// 			return false;
		// 		}
		// 		if(institute_id == "0"){
		// 			alert("Please Select Institute");
		// 			return false;
		// 		}
		var ser = 0;
		$
				.post(
						"getstu_res_declare_data?" + key + "=" + value,
						{
							system_id : system_id,
							degree_id : degree_id,
							professional_id : professional_id,
							institute_id : institute_id
						},
						function(j) {
							if (j == 0) {
								$("#tbody_pop")
										.append(
												'<tr><td colspan="10"><p>No Data Available</p></td></tr>');
							}
							if (j.length > 0) {
								$("#tbody_pop").empty();
								for (var i = 0; i < j.length; i++) {
									ser = i + 1;
									$("#tbody_pop")
											.append(
													'<tr><td><div class="check-input-primary"><input class="form-check-input mr-5 mul_check chb nrCheckBox" type="checkbox" name="multiselect'
															+ (i + 1)
															+ '" id="'
															+ ser
															+ '" value="'
															+ j[i][4]
															+ '"/>'
															+ '<input class="form-check-input mr-5 mul_check chb" type="hidden" name="ayushid'+ser+'" id="ayushid'+ser+'" value="'+j[i][0]+'"/></td>'
															+ '<td><p id="ser_no">'
															+ j[i][3]
															+ '</p></div></td>'
															+ '<td><p id="system_id">'
															+ j[i][5]
															+ '</p><input type="hidden" id="system_name'
															+ parseInt(i + 1)
															+ '" name="system_name'
															+ parseInt(i + 1)
															+ '" value="'
															+ j[i][5]
															+ '" /></td>'
															+ '<td><p id="degree_id">'
															+ j[i][6]
															+ '</p><input type="hidden" id="degree_name'
															+ parseInt(i + 1)
															+ '" name="degree_name'
															+ parseInt(i + 1)
															+ '" value="'
															+ j[i][6]
															+ '" /></td>'
															+ '<td><p id="term_id">'
															+ j[i][7]
															+ '</p><input type="hidden" id="term'
															+ parseInt(i + 1)
															+ '" name="term'
															+ parseInt(i + 1)
															+ '" value="'
															+ j[i][7]
															+ '" /></td>'
															+ '<td><p id="institute_id">'
															+ j[i][8]
															+ '</p><input type="hidden" id="institute_name'
															+ parseInt(i + 1)
															+ '" name="institute_name'
															+ parseInt(i + 1)
															+ '" value="'
															+ j[i][8]
															+ '" /></td>'
															+ '<td><p id="ayush_id">'
															+ j[i][0]
															+ '</p><input type="hidden" id="ayush_id'
															+ parseInt(i + 1)
															+ '" name="ayush_id'
															+ parseInt(i + 1)
															+ '" value="'
															+ j[i][0]
															+ '" /></td>'
															+ '<td><p id="name">'
															+ j[i][1]
															+ '</p><input type="hidden" id="name'
															+ parseInt(i + 1)
															+ '" name="name'
															+ parseInt(i + 1)
															+ '" value="'
															+ j[i][1]
															+ '" /></td>'
															+ '<td><p id="dob">'
															+ j[i][2]
															+ '</p><input type="hidden" id="dob'
															+ parseInt(i + 1)
															+ '" name="dob'
															+ parseInt(i + 1)
															+ '" value="'
															+ j[i][2]
															+ '" /></td>'
															+ '<td><p id="count">'
															+ j[i][9]
															+ '</p><input type="hidden" id="count'
															+ parseInt(i + 1)
															+ '" name="count'
															+ parseInt(i + 1)
															+ '" value="'
															+ j[i][9]
															+ '" /></td>'
															+ '<td><p id="count1">'
															+ j[i][10]
															+ '</p><input type="hidden" id="count1'
															+ parseInt(i + 1)
															+ '" name="count1'
															+ parseInt(i + 1)
															+ '" value="'
															+ j[i][10]
															+ '" /></td>'
															+ '<input type="hidden" id="countLength" name="countLength" value="0" /></tr>');

								}
								for (var i = 0; i < j.length; i++) {
									ser = i + 1;
									chbonclickchangecount(ser);
								}
								$("#tregnn").text(j.length);
								$("#countLength").val(j.length);
							}

						});
		$("#pop").show();
	}

	function chbonclickchangecount(ser) {
		document.getElementById("" + ser).onclick = function() {
			debugger;
			var cnt = $("#tregn").text();
			if ($("#" + ser).prop('checked') == false) {
				$("#tregn").text(parseInt(cnt) - 1);
			}
			if ($("#" + ser).prop('checked') == true) {
				$("#tregn").text(parseInt(cnt) + 1);
			}
		};

	}

	function setselectall() {

		if ($("#nSelAll").prop("checked")) {
			$(".nrCheckBox").prop('checked', true);
		} else {
			$(".nrCheckBox").prop('checked', false);
		}
		var l = $('[name="cbox"]:checked').length;
		$("#tregn").val(l);
		document.getElementById('tregn').innerHTML = l;

		findselected();
	}

	function findselected() {
		$("#CheckVal").val("");
		$("#CheckValAyu").val("");
		var nrSel = $('.nrCheckBox:checkbox:checked').map(function() {
			return $(this).attr('value');
		}).get();
		// 	debugger;
		aidval = "";
		var ser = $('.nrCheckBox:checkbox:checked').map(function() {
			return $(this).attr('id');
		}).get();

		for (var i = 0; i < ser.length; i++) {
			if (i == (ser.length - 1)) {
				aidval += $("#ayushid" + ser[i]).val();
			} else {
				aidval += $("#ayushid" + ser[i]).val() + ",";
			}
		}

		var b = nrSel.join(':');

		$("#CheckVal").val(b);
		$("#CheckValAyu").val(aidval);
		$('#tregn').text(nrSel.length);
	}
	function setonclickofCBDD(obj) {
		document.getElementById('course_id' + obj).onclick = function() {
			mycheckindex(obj);
		};
	}

	function setPassStatus() {
		findselected();
		if (Validation()) {

			var a = $("#CheckVal").val();
			var b = $("#CheckValAyu").val();
			var system_id = $("#system_id").val();
			var degree_id = $("#degree_id").val();
			var professional_id = $("#professional_id").val();
			var institute_id = $("#institute_id").val();
			var date_of_exam = $("#date_of_exam").val();

			// 	 var course_id = $("course_id").val();

			$.post("Pass_Student_Data?" + key + "=" + value, {
				a : a,
				system_id : system_id,
				degree_id : degree_id,
				professional_id : professional_id,
				institute_id : institute_id,
				date_of_exam : date_of_exam,
				b : b
			}).done(function(j) {

				alert(j);
				location.reload();
			});
		}
	}

	function setSupprimentryStatus() {
		$("#subjecthide").show();
		findselected();
		if (Suppri_Validation()) {

			var a = $("#CheckVal").val();
			var b = $("#CheckValAyu").val();
			var system_id = $("#system_id").val();
			var degree_id = $("#degree_id").val();
			var professional_id = $("#professional_id").val();
			var institute_id = $("#institute_id").val();
			var date_of_exam = $("#date_of_exam").val();

			var course_id = $("course_id").val();

			var count = $("#countLength").val();
			console.log(count);
			var check_list = "";
			var check_list2 = "";

			for (var i = 1; i <= count; i++) {
				if ($('input[name="multiselect' + i + '"]:checked').is(
						':checked')) {
					check_list += $(
							'input[name="multiselect' + i + '"]:checked').val()
							+ ",";
					check_list2 += $('input[name="ayushid' + i + '"]').val()
							+ ",";
				}

			}
			check_list = check_list.substring(0, check_list.length - 1);

			check_list2 = check_list2.substring(0, check_list2.length - 1);
			console.log(check_list2);
			var course = $("#course_id_hid").val();

			$.post("Supprimentry_Student_Data?" + key + "=" + value, {
				a : check_list,
				system_id : system_id,
				degree_id : degree_id,
				professional_id : professional_id,
				institute_id : institute_id,
				date_of_exam : date_of_exam,
				b : check_list2,
				course : course
			}).done(function(j) {

				alert(j);
				location.reload();
			});
		}
	}

	function setDetainStatus() {
		findselected();
		if (Validation()) {
			var a = $("#CheckVal").val();
			var b = $("#CheckValAyu").val();
			var system_id = $("#system_id").val();
			var degree_id = $("#degree_id").val();
			var professional_id = $("#professional_id").val();
			var institute_id = $("#institute_id").val();
			var date_of_exam = $("#date_of_exam").val();

			$.post("Detain_Student_Data?" + key + "=" + value, {
				a : a,
				system_id : system_id,
				degree_id : degree_id,
				professional_id : professional_id,
				institute_id : institute_id,
				date_of_exam : date_of_exam,
				b : b
			}).done(function(j) {

				alert(j);
				location.reload();
			});
		}
	}

	function Suppri_Validation() {

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
		if ($("#institute_id").val().trim() == "0") {
			alert("Please Select Institute.");
			$("select#institute_id").focus();
			return false;
		}
		if ($("#date_of_exam").val() == ""
				|| $("#date_of_exam").val() == "DD/MM/YYYY") {
			alert("Please Select the Date Of Exam");
			$("#date_of_exam").focus();
			return false;
		}
		if ($("#CheckVal").val().trim() == "") {
			alert("Please Select Student.");
			$("input#CheckVal").focus();
			return false;
		}
		if ($("#course_id_hid").val().trim() == "") {
			alert("Please Select Subject.");
			$("select#course_id_hid").focus();
			return false;
		}
		return true;
	}

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
			alert("Please Select Professional.");
			$("select#professional_id").focus();
			return false;
		}
		if ($("#institute_id").val().trim() == "0") {
			alert("Please Select Institute.");
			$("select#institute_id").focus();
			return false;
		}
		if ($("#date_of_exam").val() == ""
				|| $("#date_of_exam").val() == "DD/MM/YYYY") {
			alert("Please Select the Date Of Exam");
			$("#date_of_exam").focus();
			return false;
		}
		if ($("#CheckVal").val().trim() == "") {
			alert("Please Select Student.");
			$("input#CheckVal").focus();
			return false;
		}
		return true;
	}
</script>