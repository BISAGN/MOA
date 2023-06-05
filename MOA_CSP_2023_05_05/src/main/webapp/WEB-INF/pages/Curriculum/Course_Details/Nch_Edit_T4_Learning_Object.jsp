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
<script type="text/javascript" src="js\watermark\common.js"></script>
<!-- <link href="js/dropDown/select2.min.css" rel="Stylesheet"></link> -->
<!-- <script src="js/dropDown/select2.min.js" type="text/javascript"></script> -->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span>Edit Practical Learning Objectives
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Edit
									Practical Learning Objectives</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12">
					<!-- input style start -->
					<form:form name="course" id="edit_nch_t4_learning_object"
						action="edit_nch_t4_learning_objectAction" method='POST'
						commandName="edit_nch_t4_learning_objectCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<h6 class="mb-25">Edit Practical Learning Objectives</h6>
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
										<label for="text-input">Subject<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="course_id"
												class="singleselect form-control form-control-lg"
												id="course_id">
												<option value="0">--Select--</option>
											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
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
							<div class="row">
								<div class="col-12">
									<div
										class="table-wrapper table-responsive custom-table-p simple-table b-top mt-0">
										<table class="table" id="att_Tb">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Generic Competency</h6></th>
													<th><h6>Subject Area</h6></th>
													<th><h6>Millers Knows/Knows how/ Shows how/Does</h6></th>
													<th><h6>Specific Competency</h6></th>
													<th><h6>Special learning objectives</h6></th>
													<th><h6>Blooms Domain</h6></th>
													<th><h6>Guilberts level</h6></th>
													<th><h6>Must know/ Desire to know/ Nice to know</h6></th>
													<th><h6>TL Method/Media</h6></th>
													<th><h6>Formative Assessment</h6></th>
													<th><h6>summative assessment</h6></th>
													<th><h6>Integration Horizontal/ Vertical/ Spiral</h6></th>
													<th><h6>Term </h6></th>
													<th><h6>Action</h6></th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
										<!-- Hidden start -->
										<input type="hidden" id="count" name="count" value="1">
										<input type="hidden" id="new_count_hidden"
											name="new_count_hidden" class="form-control autocomplete">
										<input type="hidden" id="old_count" name="old_count"
											class="form-control autocomplete">
												<!-- Hidden end -->
									</div>
								</div>
							</div>
						</div>
						<ul class="buttons-group mainbtn">
							<li><input href="#0" class="main-btn deactive-btn btn-hover"
								value="Update" id="btn-save" type="submit"></li>
							<li><a href="Search_Nch_T4_Learning_Object_Url"
								class="main-btn dark-btn-outline  btn-hover btn-iconic-icon">
									<i class="lni lni-chevron-left"></i>Back
							</a></li>
						</ul>
						<input type='hidden' id='pmid' name="pmid" value='0' />
						<!-- end card -->
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

		$("#pmid").val('${list[0][0]}');
		$("select#system_id").val('${list[0][1]}');
		$('#system_id').trigger('change');
		getdegreelistbysystem();
		$("select#degree_id").val('${list[0][2]}');
		$('#degree_id').trigger('change');
		$('select#professional_id').val('${list[0][3]}');
		$('#professional_id').trigger('change');
		getcourselistbysystemdegreeprof();
		$('select#course_id').val('${list[0][4]}');
// 		alert('${list[0][4]}')
		getPracticallistbyCourse('${list[0][4]}');
		$('select#practical_id').val('${list[0][5]}');
		// 	$('#practical_id').trigger('change');
		Nchgetlearn_outListby_Practical();
// 	alert('${list[0][6]}')
		checkoptioninedit('${list[0][6]}');
		$('select#learning_outcome1').val('${list[0][6]}');

		addmoredatafetch1();
		$.ajaxSetup({
			async : false
		});
	});

	function addmoredatafetch1() {
		if ("${liteLearningchildlist.size()}" > 0) {
			formopen_att(0);
		}
		var ser = 0;
		var ind = 1;
		<c:forEach var="j" items="${liteLearningchildlist}" varStatus="num">

		if (ser != "0") {
			formopen_att(ser);
		}

		var id = "${j[0]}";
		var generic_compte = "${j[1]}";
		var subject_area = "${j[2]}";
		var millers_knows = "${j[3]}";
		var specific_compet = "${j[4]}";
		var spec_learn_object = "${j[5]}";
		var blooms_domain = "${j[6]}";
		var guilberts_level = "${j[7]}";
		var must_know_dknow_nk = "${j[8]}";
		var tl_mthd_med = "${j[9]}";
		var formtive_assessmt = "${j[10]}";
		var sumtive_assessmt = "${j[11]}";
		var integ_horivtspi = "${j[12]}";
		var nch_term = "${j[13]}";

		$("#eid" + ind).val(id);
		$("input#generic_compte" + ind).val(generic_compte);
		$("input#subject_area" + ind).val(subject_area);
		$("select#millers_knows" + ind).val(millers_knows);
		$("input#specific_compet" + ind).val(specific_compet);
		$("input#spec_learn_object" + ind).val(spec_learn_object);
		$("select#blooms_domain" + ind).val(blooms_domain);
		$('#blooms_domain').trigger('change');
		$("select#guilberts_level" + ind).val(guilberts_level);
		$("select#must_know_dknow_nk" + ind).val(must_know_dknow_nk);
		$("select#tl_mthd_med" + ind).val(tl_mthd_med);
		$("select#formtive_assessmt" + ind).val(formtive_assessmt);
		$("select#sumtive_assessmt" + ind).val(sumtive_assessmt);
		$("input#integ_horivtspi" + ind).val(integ_horivtspi);
		$("select#nch_term" + ind).val(nch_term);

		ser = parseInt(ser) + 1;
		ind = parseInt(ind) + 1;
		</c:forEach>

		$("#old_count").val(ser);
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
							if ('${list[0][2]}' != '') {
								$("select#degree_id").val('${list[0][2]}');
							}

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
							if ('${list[0][4]}' != '') {
								$('select#course_id').val('${list[0][4]}');
							}

						});
	}

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
	var att = 1;
	function formopen_att(R) {
		$("#att_Tb").show();

		$("#id_add_att" + R).hide();
		$("#att_id_remove" + R).hide();

		att = 0;
		att = parseInt(R) + 1;

		if (att < 51) {

			$("input#new_count_hidden").val(att);//current serial No
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
                                     
									+ '<td><div class="select-style-1"><div class="select-position"><select id="blooms_domain'+att+'" name="blooms_domain'+att+'" class="form-control" >'
									+ '<option value="0">--Select--</option><c:forEach var="item" items="${NchgetBloom_Domain}" varStatus="num"> <option value="${item.id}" name="${item.domain}">${item.domain}</option> </c:forEach>  </select></div></div></td>'
	
									//+ '<td><div class="select-style-1"><div class="select-position"><select id="blooms_domain'+att+'" name="blooms_domain'+att+'" class="form-control"><option value="0">--Select--</option>'
									//+ '<c:forEach var="item" items="${getc3_domain_subList}" varStatus="num"><option value="${item.id}" name="${item.domain}">${item.domain}</option></c:forEach>'
									//+ '</select></div></div> </td>'

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

			$("table#att_Tb").append(
					'<input type="hidden" name="eid'+att+'" id="eid'+att+'"/>');
			addOnclick(att);
			removeOnclick(att);
			getTerm_listByProf(att);

		} else {
			alert("Please Enter max 50 Quantity");
			if (att == 51) {
				att = att - 1;
				$("#rp_id_remove" + att).show();
			}
		}
	}
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
			return Validation();
		};
		document.getElementById('system_id').onchange = function() {
			getdegreelistbysystem();
		};
		document.getElementById('degree_id').onchange = function() {
			getcourselistbysystemdegreeprof();
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
		document.getElementById('div_iv_fluids').onclick = function() {
			showCheckboxes(this);
		};
		//document.getElementById('id_add_att1').onclick = function() {
		//formopen_att(1)
		//};
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
			alert("Please Select Practical .");
			$("select#practical_id").focus();
			return false;
		}
 
		var count = $("#new_count_hidden").val();
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
	 			alert("Please Enter Integration Horizontal/ Vertical/Spiral");
	 			$("#integ_horivtspi"+i).focus();
	 			return false;
	 		} 
			if ($("#nch_term"+i).val() == "0") {
	 			alert("Please Select Term.");
	 			$("#nch_term"+i).focus();
	 			return false;
	 		} 
		}
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
		
		var match = sids.split(",");
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