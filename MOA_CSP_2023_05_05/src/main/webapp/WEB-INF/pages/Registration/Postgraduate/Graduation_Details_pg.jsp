<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link href="js/tunneldesign/tunnelold.css" rel="Stylesheet"></link>

<!-- <link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css"> -->
<!-- <script src="js/Calender/jquery-ui.js"></script> -->
<!-- <script src="js/Calender/datePicketValidation.js"></script> -->
<!-- <script src="js/dropDown/select2.min.js" type="text/javascript"></script> -->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span> Graduation Details
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page"><span
									id="lbladd2"></span> Graduation Details</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="tunnel_design">
							<div class="square tunnel_visited">
								<a href="#" class="tunnel_text" id="tunnel_1"> Personal
									Details</a>
							</div>
							<div class="square tunnel_active" id="tunnel_2">
								<h5 class="tunnel_text">Graduation Details</h5>
							</div>
							<div class="square ">
								<a href="#" class="tunnel_text" id="tunnel_3">Upload
									Document</a>
							</div>
							<div class="square">
								<a href="#" class="tunnel_text" id="tunnel_4">Declaration</a>
							</div>
						</div>
					</div>
					<form:form name="Graduation_Det_PG" id="Graduation_Det_PG"
						action="Graduation_Det_PG_Action?${_csrf.parameterName}=${_csrf.token}"
						method='POST' modelAttribute="Graduation_Det_PG_CMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30" id="page-top">
							<section class="detail-block">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="custom-data-value custom-title custom-title-bg">
											<h5 class="custom-title-tag">Graduation Details</h5>
										</div>
									</div>
									<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="table-wrapper table-responsive custom-table b-top">
											<table class="table" id="addunitserved">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th><h6>
																Name of exam<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>
																Month and Year<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>
																University<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>
																No of Attempts<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>Action</h6></th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td><p>1</p></td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select id="name_of_exam1" name="name_of_exam1">
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${getname_of_examList}"
																			varStatus="num">
																			<option value="${item.id}"
																				name="${item.name_of_the_exam}">${item.name_of_the_exam}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="input-style-1">
																<input type="month" id="month_year1" name="month_year1">
																<!-- Hidden Start -->
																<input type="hidden" id="editid1" name="editid1"
																	value="0">
																<!-- Hidden End -->
															</div>
														</td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select id="university1" name="university1"
																		class="form-control select2 narrow wrap">
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${getuniversitylist}"
																			varStatus="num">
																			<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																		</c:forEach>
																		<option value="-1">OTHER</option>
																	</select>
																</div>
																<input type="text" id="universityother1"
																	name="universityother1"
																	class="form-control autocomplete d-none"
																	autocomplete="off" placeholder="Other University">

															</div>
														</td>
														<td>
															<div class="input-style-1">
																<input id="no_of_attempts1" name="no_of_attempts1"
																	placeholder="Enter No of Attempts" autocomplete="off"
																	maxlength="2" />
															</div>
														</td>
														<td>
															<ul class="buttons-group mainbtn action">
																<li><a
																	class="main-btn success-btn btn-hover btn-sm addminusbut"
																	title="ADD" id="id_add_att1" type="button"><i
																		class="lni lni-plus"></i></a></li>
															</ul>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<!-- Hidden Start -->
								<input type="hidden" id="count_hidden_att"
									name="count_hidden_att" class="form-control autocomplete"
									value="1"> <input type="hidden" id="new_count_hidden"
									name="new_count_hidden" class="form-control autocomplete"
									value="1"> <input type="hidden" id="old_count"
									name="old_count" class="form-control autocomplete" value="0">
								<input type="hidden" id="jlength" name="jlength"
									class="form-control autocomplete" value="0">
								<!-- Hidden End -->
							</section>
							<section class="detail-block">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="table-wrapper table-responsive custom-table">
											<table class="table" id="gradu_indepth">
												<thead>

													<tr>
														<th rowspan="2"><h6>Sr No</h6></th>
														<th rowspan="2"><h6>
																Subject<span class="mandatory"> *</span>
															</h6></th>
														<th rowspan="2"><h6>
																No of Attempts<span class="mandatory"> *</span>
															</h6></th>
														<th colspan="2"><h6>1st year</h6></th>
														<th colspan="2"><h6>2nd year</h6></th>
														<th colspan="2"><h6>3rd year</h6></th>
														<th colspan="2"><h6>4th year</h6></th>
														<th rowspan="2"><h6>
																Percentage of Marks<span class="mandatory"> *</span>
															</h6></th>
														<th rowspan="2"><h6>
																Remarks<span class="mandatory"> *</span>
															</h6></th>
														<th rowspan="2"><h6>Action</h6></th>
													</tr>
													<tr>
														<th><h6>
																Max Marks<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>
																Obtain Marks<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>
																Max Marks<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>
																Obtain Marks<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>
																Max Marks<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>
																Obtain Marks<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>
																Max Marks<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>
																Obtain Marks<span class="mandatory"> *</span>
															</h6></th>
													</tr>

												</thead>
												<tbody>
													<tr>
														<td><p>1</p></td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select id="subject1" name="subject1">
																		<option value="0">--Select--</option>


																		<c:forEach var="item" items="${getSubjectList}"
																			varStatus="num">
																			<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="input-style-1">
																<input id="semwise_no_of_atmpts1" type="text"
																	name="semwise_no_of_atmpts1" autocomplete="off"
																	placeholder="Enter no of attempts" maxlength="3" />
															</div>
														</td>
														<td><div class="input-style-1">
																<input id="maxmark_y1_1" type="text" name="maxmark_y1_1"
																	autocomplete="off" placeholder="Enter Max Marks"
																	maxlength="3" />
															</div></td>
														<td><div class="input-style-1">
																<input id="obtainmark_y1_1" type="text"
																	name="obtainmark_y1_1" autocomplete="off"
																	placeholder="Enter Obtain Marks" maxlength="3" />
															</div></td>
														<td><div class="input-style-1">
																<input id="maxmark_y2_1" type="text" name="maxmark_y2_1"
																	autocomplete="off" placeholder="Enter Max Marks"
																	maxlength="3" />
															</div></td>
														<td><div class="input-style-1">
																<input id="obtainmark_y2_1" type="text"
																	name="obtainmark_y2_1" autocomplete="off"
																	placeholder="Enter Obtain Marks" maxlength="3" />
															</div></td>
														<td><div class="input-style-1">
																<input id="maxmark_y3_1" type="text" name="maxmark_y3_1"
																	autocomplete="off" placeholder="Enter Max Marks"
																	maxlength="3" />
															</div></td>
														<td><div class="input-style-1">
																<input id="obtainmark_y3_1" type="text"
																	name="obtainmark_y3_1" autocomplete="off"
																	placeholder="Enter Obtain Marks" maxlength="3" />
															</div></td>
														<td><div class="input-style-1">
																<input id="maxmark_y4_1" type="text" name="maxmark_y4_1"
																	autocomplete="off" placeholder="Enter Max Marks"
																	maxlength="3" />
															</div></td>
														<td><div class="input-style-1">
																<input id="obtainmark_y4_1" type="text"
																	name="obtainmark_y4_1" autocomplete="off"
																	placeholder="Enter Obtain Marks" maxlength="3" />
															</div></td>

														<td><div class="input-style-1">
																<input id="percentage_of_marks1" type="text"
																	name="percentage_of_marks1" autocomplete="off"
																	placeholder="Enter percentage of Marks" maxlength="5" />
															</div></td>
														<td><div class="input-style-1">
																<input id="remarks1" type="text" name="remarks1"
																	autocomplete="off" placeholder="Enter remarks"
																	maxlength="50" />
																<!-- Hidden Start -->
																<input type="hidden" id="semwiseeditid1"
																	name="semwiseeditid1" value="0">
																<!-- Hidden End -->
															</div></td>
														<td>
															<ul class="buttons-group mainbtn action">
																<li><a
																	class="main-btn success-btn btn-hover btn-sm addminusbut"
																	title="ADD" id="id_add_indepth1" type="button"><i
																		class="lni lni-plus"></i></a></li>
															</ul>
														</td>
													</tr>
												</tbody>
											</table>
											<!-- Hidden Start -->
								<input type="hidden" name="p_id" id="p_id" value="0" /> <input
									type="hidden" name="tbpdid" id="tbpdid" value="0" /> <input
									type="hidden" name="id_org" id="id_org" value="0" /> <input
									type="hidden" name="first_name" id="first_name" value="0" />
								<!-- Hidden End -->
											<!-- Hidden Start -->
											<input type="hidden" id="count_hidden_indepth"
												name="count_hidden_indepth"
												class="form-control autocomplete" value="1"> <input
												type="hidden" id="new_count_indepth_hidden"
												name="new_count_indepth_hidden"
												class="form-control autocomplete" value="1"> <input
												type="hidden" id="old_count_indepth_hidden"
												name="old_count_indepth_hidden"
												class="form-control autocomplete" value="0">
											<!-- Hidden End -->

										</div>
										<!-- Bottom Button Start -->
										<div class="btn-bottom">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><a id="aIdPrevious" href=Personal_Details_PG_Url
															class="main-btn dark-btn-outline btn-hover btn-iconic-icon btn-iconic-left"><i
																class="lni lni-chevron-left"></i> Previous</a></li>
														<li><input type="submit" id="save_btn"
															class="main-btn info-btn btn-hover btnsave" value="Save"></li>

														<li><a href="Graduation_Det_PG_Url"
															class="main-btn dark-btn btn-hover btnreset"
															id="clear_btn">Reset</a></li>
														<li><a id="aIdNext"
															class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left btnnext">Next<i
																class="lni lni-chevron-right"></i></a></li>
													</ul>
												</div>
											</div>
										</div>
										<!-- Bottom Button End -->
									</div>
								</div>
								
							</section>

						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<section class="detail-block">
		<div class="row">
			<div class="col-12">
				<div class="row" hidden="hidden">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-table"
								id="qualification_tbl_div">
								<table class="table" id="search_graduation_details_table">
									<thead>
										<tr>
											<th><h6>Sr No</h6></th>
											<th><h6>Name of exam</h6></th>
											<th><h6>Month and Year</h6></th>
											<th><h6>University</h6></th>
											<th><h6>No of Attempts</h6></th>
											<th><h6>Action</h6></th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</section>

<c:url value="getDownloadPdfUrlforedu_PGDoc" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="getDownloadPdfForm"
	name="getDownloadPdfForm" modelAttribute="doc_id1">
	<input type="hidden" name="pageUrl" id="pageUrl"
		value="redirect:Edu_Det_Url" />
	<input type="hidden" name="doc_id1" id="doc_id1" value="" />
</form:form>

<c:url value="delete_PG_education" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Personal_Details_PG_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm"
	name="applicationUrlForm" modelAttribute="pers_details_hid">
</form:form>

<c:url value="Edu_Det_PG_Url" var="mainFormUrl1" />
<form:form action="${mainFormUrl1}" method="GET" id="mainForm11"
	name="mainForm11" modelAttribute="eid">
	<input type="hidden" name="eid" id="eid" value="0" />
</form:form>

<c:url value="Total_Exp_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm4"
	name="applicationUrlForm4" modelAttribute="tp_eid">
	<input type="hidden" name="tp_eid" id="tp_eid" value="0" />
</form:form>

<c:url value="doc_upload_PGUrl" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm5"
	name="applicationUrlForm5" modelAttribute="doc_eid">
	<input type="hidden" name="doc_eid" id="doc_eid" value="0" />
</form:form>

<c:url value="Reshuffling_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm6"
	name="applicationUrlForm6" modelAttribute="ch_eid">
	<input type="hidden" name="ch_eid" id="ch_eid" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		// 	datepicketDate('month_year');
		// 	$( "#month_year" ).datepicker("option", "dateFormat", "yy/mm");	
		// 	$( "#month_year" ).datepicker("option", "showButtonPanel", true);	
		// 	var index = '${getname_of_examList.size()}';
		// 	$("#name_of_exam").find('[value="'+index+'"]').remove();
		// 	 $("#tbpdid").val('${tbpdid}');
		//$("#p_id").val('${tbpdid}');
		//  var msg = '${msg}';
		// 	if (msg != "")
		// 			{
		// 			alert(msg);
		// 			}
		// 	 try{
		// 	 	   if(window.location.href.includes("msg="))
		// 	 		{
		// 	 			var url = window.location.href.split("?")[0];
		// 	 			window.location.href=url;
		// 	 		} 	
		// 	 	}
		// 	 	catch (e) {
		// 	 	} 
		//inyear();

		// 		mockjax1('search_graduation_details_table');
		// 		table = dataTable('search_graduation_details_table');
		// 		$('#srch').on('click', function() {
		// 			table.ajax.reload();
		// 		});
		//getPerDetails();
		get_p_id_pers_info();
		addmoredatafetch1();
		addmoredatafetch2();
	});

	function get_p_id_pers_info() {
		$.ajaxSetup({
			async : false
		});

		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		var userid = "${userId}";
		$.post('get_p_id_info_PG_ctrl?' + key + "=" + value, {
			userid : userid
		}, function(j) {
			$("#p_id").val(j[0][0]);
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});

		$.post('get_ayush_id_PG_ctrl?' + key + "=" + value, {
			userid : userid
		}, function(j) {
			// 		alert(j);
			// 		$("#ayush_id").html('Ayush id:- '+j[0][0]);
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});

	}

	/////////personal_details  from registration table/////Start
	// function getPerDetails() {
	// 	var key = "${_csrf.parameterName}";
	// 	var value = "${_csrf.token}";
	// 	var userid =  "${userid}";

	// 	$.post('getPersonaldetails?' + key + "=" + value,{userid : userid},function(j) {

	// 		alert(j);

	// 			$("#p_id").val(j[0][10]);
	// 		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	// 		});

	// 	}
	/////////////End	

	function getDownloadPdfEdu(id) {

		$("#doc_id1").val(id);
		document.getElementById("getDownloadPdfForm").submit();
	}

	function setValueOfNotificationPath() {

		if ($("#doc_path").val() != "" || $("#doc_path").val() != null) {
			var doc_path_hid = $("#doc_path").val();
			document.getElementById("doc_path_hid").value = doc_path_hid;
		} else {
			document.getElementById("doc_path_hid").value = "";
		}
	}

	function deleteData(id) {

		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}

	// function editData(id){
	// 	$.post("geteditEducation_PG_data_ctrl?" + key + "=" + value, {id:id}, function(j) {
	// 		$("#id").val(id);
	// 		$("select#name_of_exam").val(j[0][1]);
	// 		$("input#board_or_university").val(j[0][2]);
	// 		$("input#school_or_collage").val(j[0][3]);
	// 		$("input#subject").val(j[0][4]);
	// 		$("select#passing_year").val(j[0][5]);
	// 		$("input#percentage_of_marks").val(j[0][6]);
	// 		$("select#division").val(j[0][7]);
	// 		$("input#doc_path_hid").val(j[0][8]);
	// 		document.getElementById('id_org').value=id;
	// 	});
	// 		document.getElementById('lbladd1').innerHTML = "Update ";
	// 		document.getElementById('lbladd2').innerHTML = "Update ";
	// 		document.getElementById('lbladd3').innerHTML = "Update ";
	// 		document.getElementById('save_btn').value="Update";
	// 		document.getElementById('save_btn').className ='main-btn deactive-btn btn-hover';
	// // 		document.getElementById('butval1').className = 'butzz'
	// }

	// function inyear() {
	// 	 var currentYear = new Date().getFullYear()
	// 	    max = currentYear 
	// 	    var option = "";
	// 	    for (var year = currentYear-10 ; year <= max; year++) {
	// 	        var option = document.createElement("option");
	// 	        option.text = year;
	// 	        option.value = year;	        
	// 	        document.getElementById("passing_year").appendChild(option)	        
	// 	    }
	// 	   // document.getElementById("passing_year").value = currentYear;	
	// }

	function data(search_graduation_details_table) {
		jsondata = [];
		var table = $('#' + search_graduation_details_table).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];

		var name_of_exam = $("#name_of_exam").val();
		var month_year = $("#month_year").val();
		var university = $("#university").val();
		var no_of_attempts = $("#no_of_attempts").val();

		$.post("getFilterGraduation_PG_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			name_of_exam : name_of_exam,
			month_year : month_year,
			university : university,
			no_of_attempts : no_of_attempts
		}, function(j) {
			// 			if (j.length == 0) {
			// 				$("#jlength").val('1');
			// 			}
			// alert(j);
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i][0], j[i][1], j[i][2], j[i][3], j[i][4],
						j[i][5] ]);
			}
		});

		$.post("getTotalGraduation_PG_dataCount?" + key + "=" + value, {
			Search : Search
		}, function(j) {
			FilteredRecords = j;
		});
		// 		setTimeout(setTimeLoadForTable, 500);
	}

	function setTimeLoadForTable() {
		// 	document.querySelectorAll('.addAPP').forEach((items, index) => {
		// 		items.addEventListener('click', event => {
		// 			var val=parseInt(index)+1;
		// 			var hid = document.getElementById('upDOWN'+val).value;
		// 			if (confirm('Are You Sure You Want to Edit This?')) {
		// 				editData(hid);
		// 			} else {
		// 				return false;
		// 			}
		// 		})
		// 	});
		// 	document.querySelectorAll('.removeAPP').forEach((items, index) => {
		// 		items.addEventListener('click', event => {
		// 			var val=parseInt(index)+1;
		// 			var hid = document.getElementById('deleteID'+val).value;
		// 			if (confirm('Are You Sure You Want to Delete Detail ?')) {
		// 				deleteData(hid);
		// 			} else {
		// 				return false;
		// 			}
		// 		})
		// 	});

		// 	document.querySelectorAll('.eddownload').forEach((items, index) => {
		// 		items.addEventListener('click', event => {
		// 			var val=parseInt(index)+1;
		// 			var hid = document.getElementById('dlID'+val).value;
		// 			if (confirm('Are You Sure You Want to Download This Education Details ?')) {
		// 				getDownloadPdfEdu(hid);
		// 			} else {
		// 				return false;
		// 			}
		// 		})
		// 	});

	}

	function clear_field() {

		$("#name_of_exam").val('0').change();
		// 	document.getElementById("document_lbltik").innerHTML=""; 
		$("select#passing_year").val("0");
		$("#subject").val("");
		$("#percentage_of_marks").val("");
		$("#total_marks").val("");
		$("select#division").val("0");
		$("#doc_path").val("");
		$("#board_or_university").val("");
		$("#school_or_collage").val("");
		$("#education_id_form").val("");
		$("input#doc_path_hid").val("");

		document.getElementById("document_lbltik").innerHTML = "";
		document.getElementById('save_btn').value = "Save";
		document.getElementById('save_btn').className = 'main-btn info-btn btn-hover';
		// 	document.getElementsByClassName('fa fa-pencil-square-o')[0].style.visibility = 'hidden';
		// 	document.getElementsByClassName('fa fa-save')[0].style.visibility = 'visible';

	}

	// function count_percentage(){

	// 	document.getElementById("marks_obtained_lbl").innerHTML="";
	// 	document.getElementById("total_marks_lbl").innerHTML="";
	// 	var total = parseFloat($('#total_marks').val()); 
	//  	var obtained = parseFloat($('#percentage_of_marks').val());

	//  	 if(obtained == "0" || obtained == "")
	// 	    {
	// 	        document.getElementById("marks_obtained_lbl").innerHTML="<i class='fa fa-exclamation'></i>Please Enter Obtained Marks";
	// 	        return false;
	// 	    }
	// 	    if(total == "0" || total == "")
	// 	    {
	// 	       document.getElementById("total_marks_lbl").innerHTML="<i class='fa fa-exclamation'></i>Please Enter Total Marks";
	// 	       return false;
	// 	    }

	//  	 var perc="";
	//  	 if(obtained <= total){
	//     	  if(total == "0" || obtained == "0"){
	//               perc="0";
	//          }
	//     	 else{
	//     		 perc = ((obtained/total) * 100).toFixed(2);
	//          }
	//      	$('#pers_edu_percentage').val(perc);

	//       }
	//       else 

	//     	  if( total<=obtained ) {
	//      	document.getElementById("marks_obtained_lbl").innerHTML="<i class='fa fa-exclamation'></i>Marks Obtained should be less then Total Marks";
	//   //   	$('#pers_edu_percentage').val("0");
	//      	$('#total_marks').val("0");
	//      	$('#percentage_of_marks').val("0");
	//      }
	// }

	function isValidateClientSide() {

		for (at = 1; at <= $('#count_hidden_att').val(); at++) {

			if ($("#name_of_exam" + at).val() == '0') {
				alert("Please Select Name of Exam In Row " + at);
				$("#name_of_exam" + at).focus();
				return false;
			}
			if ($("#month_year" + at).val() == "") {
				alert("Please Select Month Year In Row " + at);
				$("#month_year" + at).focus();
				return false;
			}

			if ($("#university" + at).val() == "0") {
				alert("Please Select University In Row " + at);
				$("#university" + at).focus();
				return false;
			}

			if ($("#no_of_attempts" + at).val() == "") {
				alert("Please Select No of Attempts In Row " + at);
				$("#no_of_attempts" + at).focus();
				return false;
			}
		}

		for (at = 1; at <= $('#count_hidden_indepth').val(); at++) {

			if ($("#subject" + at).val() == '0') {
				alert("Please Select Subject Name In Row " + at);
				$("#subject" + at).focus();
				return false;
			}

			if ($("#semwise_no_of_atmpts" + at).val() == "") {
				alert("Please Enter No of Atmpts In Row " + at);
				$("#semwise_no_of_atmpts" + at).focus();
				return false;
			}

			if ($("#maxmark_y1_" + at).val() == "") {
				alert("Please Enter Max Marks 1st Year In Row " + at);
				$("#maxmark_y1_" + at).focus();
				return false;
			}

			if ($("#obtainmark_y1_" + at).val() == "") {
				alert("Please Enter Obtain Marks 1st Year In Row " + at);
				$("#obtainmark_y1_" + at).focus();
				return false;
			}

			if (parseInt($("#maxmark_y1_" + at).val()) < parseInt($(
					"#obtainmark_y1_" + at).val())) {
				alert("Please Enter Obtain Marks Less then Max Marks In 1st Year In Row "
						+ at);
				$("#obtainmark_y1_" + at).focus();
				return false;
			}

			if ($("#maxmark_y2_" + at).val() == "") {
				alert("Please Enter Max Marks 2nd Year In Row " + at);
				$("#maxmark_y2_" + at).focus();
				return false;
			}

			if ($("#obtainmark_y2_" + at).val() == "") {
				alert("Please Enter Obtain Marks 2nd Year In Row " + at);
				$("#obtainmark_y2_" + at).focus();
				return false;
			}

			if (parseInt($("#maxmark_y2_" + at).val()) < parseInt($(
					"#obtainmark_y2_" + at).val())) {
				alert("Please Enter Obtain Marks Less then Max Marks In 2nd Year In Row "
						+ at);
				$("#obtainmark_y2_" + at).focus();
				return false;
			}

			if ($("#maxmark_y3_" + at).val() == "") {
				alert("Please Enter Max Marks 3rd Year In Row " + at);
				$("#maxmark_y3_" + at).focus();
				return false;
			}

			if ($("#obtainmark_y3_" + at).val() == "") {
				alert("Please Enter Obtain Marks 3rd Year In Row " + at);
				$("#obtainmark_y3_" + at).focus();
				return false;
			}

			if (parseInt($("#maxmark_y3_" + at).val()) < parseInt($(
					"#obtainmark_y3_" + at).val())) {
				alert("Please Enter Obtain Marks Less then Max Marks In 3rd Year In Row "
						+ at);
				$("#obtainmark_y3_" + at).focus();
				return false;
			}

			if ($("#maxmark_y4_" + at).val() == "") {
				alert("Please Enter Max Marks 4th Year In Row " + at);
				$("#maxmark_y4_" + at).focus();
				return false;
			}

			if ($("#obtainmark_y4_" + at).val() == "") {
				alert("Please Enter Obtain Marks 4th Year In Row " + at);
				$("#obtainmark_y4_" + at).focus();
				return false;
			}

			if (parseInt($("#maxmark_y4_" + at).val()) < parseInt($(
					"#obtainmark_y4_" + at).val())) {
				alert("Please Enter Obtain Marks Less then Max Marks In 4th Year In Row "
						+ at);
				$("#obtainmark_y4_" + at).focus();
				return false;
			}

			var om = $("#percentage_of_marks" + at).val().trim()
			if (om == "") {
				alert("Please Enter Percentage Of Marks In Row " + at);
				$("#percentage_of_marks" + at).focus();
				return false;
			}
			if (parseFloat(om) <= 0) {
				alert("Please Enter Valid Percentage Of Marks In Row " + at);
				$("#percentage_of_marks" + at).focus();
				return false;
			}

			var maxLength = 5;
			var charLength = om.length;

			if (charLength > maxLength) {
				alert("Percentage Of Marks Contain Maximum 5 Characters In Row "
						+ at);
				$("input#percentage_of_marks" + at).focus();
				return false;
			}

			if (parseFloat(om) > 100) {
				alert("Percentage Of Marks Can't Have More Than 100 Percent In Row "
						+ at);
				$("#percentage_of_marks" + at).focus();
				return false;
			}

			// 		if($("#percentage_of_marks"+at).val() == ""){
			// 			alert("Please Enter Percentage Of Marks In Row "+at);
			// 			$("#percentage_of_marks"+at).focus();
			// 			return false;
			// 		}

			if ($("#remarks" + at).val() == "") {
				alert("Please Enter remarks In Row " + at);
				$("#remarks" + at).focus();
				return false;
			}

		}

		// 	if ($("select#name_of_exam").val().trim() == "" || $("select#name_of_exam").val().trim() == "0") {
		// 		alert("Please Select Name of Exam");
		// 		$("select#name_of_exam").focus();
		// 		return false;
		// 	}

		// 	 if ($("#board_or_university").val().trim() == "") {
		// 			alert("Please Enter the Board/University");
		// 			$("#board_or_university").focus();
		// 			return false;
		// 		} 

		// 	 if ($("#school_or_collage").val().trim() == "") {
		// 			alert("Please Enter the School/College");
		// 			$("#school_or_collage").focus();
		// 			return false;
		// 		} 

		// 	if ($("#subject").val().trim() == "") {
		// 			alert("Please Enter the Subject");
		// 			$("#subject").focus();
		// 			return false;
		// 		}

		// 	if ($("select#passing_year").val().trim() == "" || $("select#passing_year").val().trim() == "0"){
		// 		alert("Please Select Year of Passing");
		// 		 $("select#passing_year").focus();
		// 		return false;
		// 	}

		// 	if ($("#institute_name").val().trim() == "") {			
		// 		alert("Please Enter Institute Name");
		// 		 $("#institute_name").focus();
		// 		return false;
		// 	}
		// 	var maxLength = 50;
		// 	 var charLength = $("input#institute_name").val();
		//    if(charLength > maxLength){
		//    	alert("Please Enter Institute Name should be less then 50 Characters");
		// 			$("input#institute_name").focus();
		// 			return false;
		//    }  
		//    if ($("#institute_name").val() == "") {				
		// 		alert("Please Enter Institute Name");
		// 		 $("#institute_name").focus();
		// 		return false;
		// 	}
		//    var minlength = 3;
		// 	 var charLength = $("input#institute_name").val().length;

		//        if(charLength < minlength){
		//        		alert("Please Enter Institute Name should not be less then 3 Characters");
		// 			$("input#institute_name").focus();
		// 			return false;
		//        }

		// 	 var om =  $("#percentage_of_marks").val().trim()

		//        if (om == "") {				
		//    		alert("Please Enter Percentage of Marks");
		//    		 $("#percentage_of_marks").focus();
		//    		return false;
		//    	}
		//        if (parseFloat(om) <= 0 ) {				
		//       		alert("Please Enter Valid Percentage of Marks");
		//       		 $("#percentage_of_marks").focus();
		//       		return false;
		//       	}

		//        var maxLength = 5;
		// 		 var charLength = om.length;

		// 	       if(charLength > maxLength){
		// 	       	alert("Please Enter Percentage of Marks should be less then 6 Characters");
		// 				$("input#percentage_of_marks").focus();
		// 				return false;
		// 	       } 

		// 	       if (om > 100 ) {				
		// 	      		alert("Percentage Of Marks Can't Have More Than 100 Percent");
		// 	      		 $("#percentage_of_marks").focus();
		// 	      		return false;
		// 	      	}

		// 	       if ($("#total_marks").val().trim() <= "0" || $("#total_marks").val().trim() == "") {				
		// 	      		alert("Please Enter Total Marks");
		// 	      		 $("#total_marks").focus();
		// 	      		return false;
		// 	      	}  

		// 		var maxLength = 5;
		// 		 var charLength = $("input#total_marks").val().length;

		// 	       if(charLength >= maxLength){
		// 	       	alert("Please Enter Out of Marks should be less then 5 Characters");
		// 				$("input#total_marks").focus();
		// 				return false;
		// 	       }  
		// 	       if ($("select#division").val() == 0) {
		// 				alert("Please Select Division");
		// 				$("select#division").focus();
		// 				return false;
		// 			}

		// 		if($("input#doc_path").val() == "" && $("input#doc_path_hid").val() == "")
		// 		 {
		// 			if($("#doc_path_hid").val() == ""){
		// 			 alert("Please Upload Document");
		// 			 $("input#doc_path").focus();
		// 			 return false;
		// 			}
		// 		  }
	}

	// function getPage()
	// {  
	// 	$("#pers_exper_hid").val("${pers_adv_details_session}");
	// 	document.getElementById("applicationUrlForm4").submit();
	// }
	// function getEduPage()
	// {  
	// 	$("#eid").val('${tbpdid}');
	// 	$("#pers_exper_hid").val("${pers_adv_details_session}");
	// 	document.getElementById("mainForm11").submit();
	// }

	function getPreviousPage() {

		$("#pers_details_hid").val($("#p_id").val());
		document.getElementById("applicationUrlForm").submit();
	}

	function Edu_next() {

		// 	var j = $("#jlength").val()
		// 	if (parseInt(j) > 0) {
		// 		 alert("Please Fill Atleast One Education Details");
		// 		 return false;
		// 	}

		$("#doc_eid").val($("#p_id").val());
		$("#pers_exper_hid").val("${pers_adv_details_session}");
		document.getElementById("applicationUrlForm5").submit();
	}

	function getExpPage() {
		$("#tp_eid").val($("#p_id").val());
		$("#pers_exper_hid").val("${pers_adv_details_session}");
		document.getElementById("applicationUrlForm4").submit();
	}

	function getDocPage() {
		$("#doc_eid").val($("#p_id").val());
		$("#pers_exper_hid").val("${pers_adv_details_session}");
		document.getElementById("applicationUrlForm5").submit();
	}
	function getchoicePage() {
		$("#ch_eid").val($("#p_id").val());
		$("#pers_exper_hid").val("${pers_adv_details_session}");
		document.getElementById("applicationUrlForm6").submit();
	}

	//csp------------------------
	document
			.addEventListener(
					'DOMContentLoaded',
					function() {

						document.getElementById('id_add_att1').onclick = function() {
							formopen_att(1)
						};

						document.getElementById('id_add_indepth1').onclick = function() {
							formopen_indepth(1)
						};

						document.getElementById('save_btn').onclick = function() {
							return isValidateClientSide();
						};

						// 		document.getElementById('universityother1').onkeypress = function() {
						// 			return onlyAlphabetsStringSpace(event, this);
						// 		};

						document.getElementById('university1').onchange = function() {
							otheroptionopen(1);
						};

						// 	document.getElementById('universityother1'+att).onclick = function() {
						// 		 OtherOption(this,att);
						// 		};

						document.getElementById('no_of_attempts1').onkeypress = function() {
							return isNumberKey0to9(event);
						};

						document.getElementById('semwise_no_of_atmpts1').onkeypress = function() {
							return isNumberKey0to9(event);
						};

						document.getElementById('maxmark_y1_1').onkeypress = function() {
							return isNumberKey0to9(event);
						};

						document.getElementById('obtainmark_y1_1').onkeypress = function() {
							return isNumberKey0to9(event);
						};
						document.getElementById('maxmark_y2_1').onkeypress = function() {
							return isNumberKey0to9(event);
						};

						document.getElementById('obtainmark_y2_1').onkeypress = function() {
							return isNumberKey0to9(event);
						};

						document.getElementById('maxmark_y3_1').onkeypress = function() {
							return isNumberKey0to9(event);
						};
						document.getElementById('obtainmark_y3_1').onkeypress = function() {
							return isNumberKey0to9(event);
						};
						document.getElementById('maxmark_y4_1').onkeypress = function() {
							return isNumberKey0to9(event);
						};

						document.getElementById('obtainmark_y4_1').onkeypress = function() {
							return isNumberKey0to9(event);
						};

						document.getElementById('percentage_of_marks1').onkeypress = function() {
							return isNumberKeydecimal(this, event);
						};

						document.getElementById('remarks1').onkeypress = function() {
							return onlyAlphabetsStringSpace(event, this);
						};

						// 			document.getElementById('clear_btn').onclick = function() {
						// 					return clear_field();
						// 			};

						// 			document.getElementById('school_or_collage').onkeypress = function() {
						// 				return onlyAlphabetsStringSpace(event, this);
						// 			};
						// 			document.getElementById('subject').onkeypress = function() {
						// 				return onlyAlphabetsStringSpace(event, this);
						// 			};
						// 			document.getElementById('percentage_of_marks').onkeypress = function() {
						// 				return isNumberKeydecimal(this, event);
						// 			};
						// 			document.getElementById('doc_path').onchange = function() {
						// 				return pdfFileSizeValidation(this, this.value, "doc_path", "200kb",
						// 						"document_lbltik", "document_lbl1", this.accept);
						// 			};

						document.getElementById('tunnel_1').onclick = function() {
							// 				if(confirm('Are you sure you want to Proceed?')){getPreviousPage();}else{return false;}
							getPreviousPage();
						};
						document.getElementById('tunnel_3').onclick = function() {
							// 				if(confirm('Are you sure you want to Proceed?')){Edu_next();}else{return false;}
							Edu_next();
						};

						document.getElementById('aIdNext').onclick = function() {
							// 				if (confirm('Are you sure you want to Proceed?')) {
							return Edu_next();
							// 				} else {
							// 					return false;
							// 				}
						};

						document.getElementById('aIdPrevious').onclick = function() {
							// 				if (confirm('Are you sure you want to Proceed?')) {
							return getPreviousPage();
							// 				} else {
							// 					return false;
							// 				}
						};
					});

	$(".monthpicker")
			.datepicker(
					{
						//showOn: 'both',
						changeMonth : true,
						changeYear : true,
						dateFormat : "yy-mm",
						buttonText : '<span class="icon"><i class="lni lni-calendar"></i></span>',
						showButtonPanel : true,
						currentText : "This Month",
						maxDate : new Date(),
						onChangeMonthYear : function(year, month, inst) {
							$(this).val(
									$.datepicker.formatDate('M-yy', new Date(
											year, month - 1, 1)));
						},
						onClose : function(dateText, inst) {
							var month = $(".ui-datepicker-month :selected")
									.val();
							var year = $(".ui-datepicker-year :selected").val();
							$(this).val(
									$.datepicker.formatDate('M-yy', new Date(
											year, month, 1)));
						}
					}).focus(function() {
				$(".ui-datepicker-calendar").hide();
			}).after(
					$("<a href='javascript: void(0);'>clear</a>").click(
							function() {
								$(this).prev().val('');
							}));

	//Add-More-Add
	var att = 1;
	function formopen_att(R) {

		if ($("select#doc_name" + att).val() == "0") {
			alert("Please Select Doument Name In Row " + att);
			$("select#doc_name" + att).focus();
			return false;
		}

		if ($("select#doc_type" + att).val() == "0") {
			alert("Please Select Doument Type In Row " + att);
			$("select#doc_type" + att).focus();
			return false;
		}

		if ($("#upload_document" + att).val() == "") {
			alert("Please Upload File Row " + att);
			$("#upload_document" + att).focus();
			return false;
		}

		$("#addunitserved").show();
		$("#id_add_att" + R).hide();
		$("#att_id_remove" + R).hide();
		att = 0;
		att = parseInt(R) + 1;
		if (att < 5) {

			$("input#count_hidden_att").val(att);//current serial No
			$("table#addunitserved")
					.append(
							'<tr  id="tr_id_att'+att+'"><td><p>'
									+ att
									+ '</p></td>'
									+ ' <td><div class="select-style-1"><div class="select-position">'
									+ '<select id="name_of_exam'+att+'" name="name_of_exam'+att+'"class="form-control">'
									+ '<option value="0">--Select--</option><c:forEach var="item" items="${getname_of_examList}" varStatus="num">'
									+ '<option value="${item.id}" name="${item.name_of_the_exam}">${item.name_of_the_exam}</option></c:forEach></select>'
									+ '</div></div></td>'
									+ '<td><div class="input-style-2">'
									+ '<input  type="month" id="month_year'+att+'" name="month_year'+att+'" class="form-control ">'
									+ '</div></td>'

									+ '<td><div class="select-style-1"><div class="select-position">'
									+ '<select id="university'+att+'" name="university'+att+'" class="form-control select2 narrow wrap">'
									+ '<option value="0">--Select--</option><c:forEach var="item" items="${getuniversitylist}" varStatus="num">'
									+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach>'
									+ '<option value="-1" name="OTHER">OTHER</option>'
									+ '	</select></div><input type="text"  id="universityother'+att+'" name="universityother'+att+'" class="form-control autocomplete d-none" autocomplete="off" placeholder="Other University"></div> </td>'
									+ '	$("select#university"+obj).html(options);</select></div></div></td>'

									+ '<td ><div class="input-style-1"><input id="no_of_attempts'+att+'" name="no_of_attempts'+att+'"'
								    +'class="form-control"  placeholder="Enter No of Attempts" autocomplete="off"	maxlength="2" /> '
									+ '</div></td>'
									+ '<td ><ul class="buttons-group mainbtn"><li><a class="main-btn success-btn btn-hover btn-sm" value = "ADD" title = "ADD" id = "id_add_att'+att+'"  ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_remove'+att+'"><i class="lni lni-trash-can"></i></a></li></ul></td>'
									+ '</tr>');

			$("table#addunitserved")
					.append(
							'<input  type="hidden" id="editid'+att+'" name="editid'+att+'" value="0" class="form-control">');

			addOnclick(att);
			removeOnclick(att);
			uni_oc(att);
			noa(att)

			var $select2 = $('.select2').select2({
				containerCssClass : "wrap"
			});
		} else {
			alert("Please Enter max 4 Quantity");
			if (att == 5) {
				att = att - 1;
				$("#att_id_remove" + att).show();
			}
		}
		var curcnt = $("#count_hidden_att").val();
		$("#new_count_hidden").val(curcnt);
	}

	//Add-More-Remove
	function formopen_re_att(R) {

		// 	id="editid'+att+'"

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
		// 	 $("input#count_hidden_att").val(att);
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

	function addOnclick(index) {
		document.getElementById('id_add_att' + index).onclick = function() {
			formopen_att(index);
		};

		document.getElementById('university' + index).onchange = function() {
			otheroptionopen(index);
		};

	}

	function removeOnclick(index) {
		document.getElementById('att_id_remove' + index).onclick = function() {
			formopen_re_att(index);
		};
	}

	function uni_oc(index) {
		document.getElementById('university' + index).onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
	}

	function noa(index) {
		document.getElementById('no_of_attempts' + index).onkeypress = function() {
			return isNumberKey0to9(event);
		};
	}

	function addmoredatafetch1() {
		// 		debugger;
		var ser = 0;
		var ind = 1;
		<c:forEach var="j" items="${edit_gradu_det}" varStatus="num">
		if (ser != "0") {
			formopen_att(ser);
		}

		var id = "${j[0]}";
		var name_of_exam = "${j[1]}";
		var month_year = "${j[2]}";
		var university = "${j[3]}";
		var no_of_attempts = "${j[4]}";
		// 			var otheruniversity = "${j[5]}";

		$("#editid" + ind).val(id);
		$("#name_of_exam" + ind).val(name_of_exam);
		$("#name_of_exam" + ind).attr('readonly', true);
		$("#name_of_exam" + ind).attr('tabindex', '-1');
		$("#month_year" + ind).val(month_year);
		$("#university" + ind).val(university);
		$("#no_of_attempts" + ind).val(no_of_attempts);
		// 	  		$("#universityother"+ind).val(otheruniversity);

		if (university == "-1") {
			$("#university" + ind).val(university.toUpperCase());
			// 			$("#universityother" + ind).show();
			if ($("#universityother" + ind).hasClass("d-none")) {
				$("#universityother" + ind).removeClass("d-none")
			}
			$("#universityother" + ind).val('${j[5]}');
		} else {
			// 			$("#universityother" + ind).hide();

			if (!$("#universityother" + ind).hasClass('d-none')) {
				$("#universityother" + ind).addClass("d-none")
			}
		}

		ser = parseInt(ser) + 1;
		ind = parseInt(ind) + 1;
		</c:forEach>

		$("#old_count").val(ser);

		var $select2 = $('.select2').select2({
			containerCssClass : "wrap"
		});

	}

	// Add-More-Add 2nd tbl
	var att = 1;
	function formopen_indepth(R) {

		if ($("select#doc_name" + att).val() == "0") {
			alert("Please Select Doument Name In Row " + att);
			$("select#doc_name" + att).focus();
			return false;
		}

		if ($("select#doc_type" + att).val() == "0") {
			alert("Please Select Doument Type In Row " + att);
			$("select#doc_type" + att).focus();
			return false;
		}

		if ($("#upload_document" + att).val() == "") {
			alert("Please Upload File Row " + att);
			$("#upload_document" + att).focus();
			return false;
		}

		$("#gradu_indepth").show();
		$("#id_add_indepth" + R).hide();
		$("#id_remove_indepth" + R).hide();
		att = 0;
		att = parseInt(R) + 1;
		if (att < 13) {

			$("input#count_hidden_indepth").val(att);//current serial No
			$("table#gradu_indepth")
					.append(
							'<tr  id="tr_id_indepth'+att+'"><td><p>'
									+ att
									+ '</p></td>'
									+ ' <td><div class="select-style-1"><div class="select-position">'
									+ '<select id="subject'+att+'" name="subject'+att+'" class="form-control">'
									+ '<option value="0">--Select--</option> <c:forEach var="item" items="${getSubjectList}" varStatus="num"> '
									+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select>'
									+ '</div></div></td>'
									+ '<td> <div class="input-style-1"><input id="semwise_no_of_atmpts'+att+'" type="text"'
							 +'name="semwise_no_of_atmpts'+att+'" class="form-control" autocomplete="off" placeholder="Enter no of attempts" maxlength="2" />'
									+ '	</div> </td>'
									+ '<td><div class="input-style-1">'
									+ '<input id="maxmark_y1_'+att+'" type="text" name="maxmark_y1_'+att+'" class="form-control"' 
							 +'			autocomplete="off" placeholder="Enter Max Marks" maxlength="3" />'
									+ '	</div></td>'
									+ '<td><div class="input-style-1">'
									+ '<input id="obtainmark_y1_'+att+'" type="text"	name="obtainmark_y1_'+att+'" class="form-control"' 
							 +'			autocomplete="off" placeholder="Enter Obtain Marks" maxlength="3"/>'
									+ '	</div></td>'
									+ '						<td><div class="input-style-1">	<input id="maxmark_y2_'+att+'" type="text"'
							 +'name="maxmark_y2_'+att+'" class="form-control"	autocomplete="off" placeholder="Enter Max Marks" maxlength="3"/>'
									+ '	</div></td>'
									+ '<td><div class="input-style-1">'
									+ '<input id="obtainmark_y2_'+att+'" type="text"	name="obtainmark_y2_'+att+'" class="form-control"' 
							 +'			autocomplete="off" placeholder="Enter Obtain Marks" maxlength="3"/>'
									+ '	</div></td>'
									+ '<td><div class="input-style-1">	<input id="maxmark_y3_'+att+'" type="text"'
							 +'name="maxmark_y3_'+att+'" class="form-control"	autocomplete="off" placeholder="Enter Max Marks" maxlength="3"/>'
									+ '	</div></td>'
									+ '<td><div class="input-style-1"> <input id="obtainmark_y3_'+att+'" type="text"'
							 +'name="obtainmark_y3_'+att+'" class="form-control" 	autocomplete="off" placeholder="Enter Obtain Marks" maxlength="3"/>'
									+ '	</div></td>'
									+ '<td><div class="input-style-1"> <input id="maxmark_y4_'+att+'" type="text"'
							 +'name="maxmark_y4_'+att+'" class="form-control" autocomplete="off" placeholder="Enter Max Marks" maxlength="3"/>'
									+ '	</div></td>'
									+ '<td><div class="input-style-1">'
									+ '<input id="obtainmark_y4_'+att+'" type="text"	name="obtainmark_y4_'+att+'" class="form-control"' 
							 +'			autocomplete="off" placeholder="Enter Obtain Marks" maxlength="3" />'
									+ '	</div></td>'
									+ '<td><div class="input-style-1">	<input id="percentage_of_marks'+att+'" type="text"'
							 +'name="percentage_of_marks'+att+'" class="form-control"	autocomplete="off" placeholder="Enter percentage of Marks" maxlength="5"/>'
									+ '	</div></td>'
									+ '<td><div class="input-style-1">	<input id="remarks'+att+'" type="text"'
							 +'name="remarks'+att+'" class="form-control"	autocomplete="off" placeholder="Enter remarks" maxlength="50"/>'
									+ '</div></td>'
									+ '<td ><ul class="buttons-group mainbtn"><li><a class="main-btn success-btn btn-hover btn-sm" value = "ADD" title = "ADD" id = "id_add_indepth'+att+'"  ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "id_remove_indepth'+att+'"><i class="lni lni-trash-can"></i></a></li></ul></td>'
									+ '</tr>');

			$("table#gradu_indepth")
					.append(
							'<input  type="hidden" id="semwiseeditid'+att+'" name="semwiseeditid'+att+'" value="0" class="form-control">');

			addOnclick_indepth(att);
			removeOnclick_indepth(att);
			semnoa_oc(att);
			mmy1(att);
			omy1(att);
			mmy2(att);
			omy2(att);
			mmy3(att);
			omy3(att);
			mmy4(att);
			omy4(att);
			pom_ok(att);
			rem_ok(att);

		} else {
			alert("Please Enter max 12 Quantity");
			if (att == 13) {
				att = att - 1;
				$("#id_remove_indepth" + att).show();
			}
		}

		var curcnt = $("#count_hidden_indepth").val();
		$("#new_count_indepth_hidden").val(curcnt);
	}
	//Add-More-Remove
	function formopen_re_indepth(R) {
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

		$("tr#tr_id_indepth" + R).remove();
		att = att - 1;
		$("input#count_hidden_indepth").val(att);
		if (R > 2) {
			$("#id_add_indepth" + att).show();
			$("#id_remove_indepth" + att).show();
		}
		if (R == 2) {
			$("#id_add_indepth" + att).show();
		}
		var ncc = $("#new_count_indepth_hidden").val();
		ncc = ncc - 1;
		$("#new_count_indepth_hidden").val(ncc);
	}

	function addOnclick_indepth(index) {
		document.getElementById('id_add_indepth' + index).onclick = function() {
			formopen_indepth(index);
		};

	}

	function removeOnclick_indepth(index) {
		document.getElementById('id_remove_indepth' + index).onclick = function() {
			formopen_re_indepth(index);
		};
	}

	function semnoa_oc(index) {
		document.getElementById('semwise_no_of_atmpts' + index).onkeypress = function() {
			return isNumberKey0to9(event);
		};
	}

	function mmy1(index) {
		document.getElementById('maxmark_y1_' + index).onkeypress = function() {
			return isNumberKey0to9(event);
		};
	}

	function omy1(index) {
		document.getElementById('obtainmark_y1_' + index).onkeypress = function() {
			return isNumberKey0to9(event);
		};
	}
	function mmy2(index) {
		document.getElementById('maxmark_y2_' + index).onkeypress = function() {
			return isNumberKey0to9(event);
		};
	}

	function omy2(index) {
		document.getElementById('obtainmark_y2_' + index).onkeypress = function() {
			return isNumberKey0to9(event);
		};
	}

	function mmy3(index) {
		document.getElementById('maxmark_y3_' + index).onkeypress = function() {
			return isNumberKey0to9(event);
		};
	}

	function omy3(index) {
		document.getElementById('obtainmark_y3_' + index).onkeypress = function() {
			return isNumberKey0to9(event);
		};
	}
	function mmy4(index) {
		document.getElementById('maxmark_y4_' + index).onkeypress = function() {
			return isNumberKey0to9(event);
		};
	}
	function omy4(index) {
		document.getElementById('obtainmark_y4_' + index).onkeypress = function() {
			return isNumberKey0to9(event);
		};
	}
	function pom_ok(index) {
		document.getElementById('percentage_of_marks' + index).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	}
	function rem_ok(index) {
		document.getElementById('remarks' + index).onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
	}

	function addmoredatafetch2() {
		var ser = 0;
		var ind = 1;
		<c:forEach var="j" items="${edit_gradu_semwise_det}" varStatus="num">
		if (ser != "0") {
			formopen_indepth(ser);
		}

		var id = "${j[0]}"
		var subject = "${j[1]}";
		var semwise_no_of_atmpts = "${j[2]}";

		var maxmark_y1_ = "${j[3]}";
		var obtainmark_y1_ = "${j[4]}";

		var maxmark_y2_ = "${j[5]}";
		var obtainmark_y2_ = "${j[6]}";

		var maxmark_y3_ = "${j[7]}";
		var obtainmark_y3_ = "${j[8]}";

		var maxmark_y4_ = "${j[9]}";
		var obtainmark_y4_ = "${j[10]}";

		var percentage_of_marks = "${j[11]}";
		var remarks = "${j[12]}";

		// 			 ,subject, semwise_no_of_atmpts, maxmark_y1_,obtainmark_y1_,maxmark_y2_,obtainmark_y2_,
		// 			 maxmark_y3_,obtainmark_y3_,maxmark_y4_,obtainmark_y4_,percentage_of_marks,remarks 

		$("#semwiseeditid" + ind).val(id);
		$("#subject" + ind).val(subject);
		$("#subject" + ind).attr('readonly', true);

		$("#subject" + ind).attr('tabindex', '-1');

		$("#semwise_no_of_atmpts" + ind).val(semwise_no_of_atmpts);

		$("#maxmark_y1_" + ind).val(maxmark_y1_);
		$("#obtainmark_y1_" + ind).val(obtainmark_y1_);

		$("#maxmark_y2_" + ind).val(maxmark_y2_);
		$("#obtainmark_y2_" + ind).val(obtainmark_y2_);

		$("#maxmark_y3_" + ind).val(maxmark_y3_);
		$("#obtainmark_y3_" + ind).val(obtainmark_y3_);

		$("#maxmark_y4_" + ind).val(maxmark_y4_);
		$("#obtainmark_y4_" + ind).val(obtainmark_y4_);

		$("#percentage_of_marks" + ind).val(percentage_of_marks);
		$("#remarks" + ind).val(remarks);

		ser = parseInt(ser) + 1;
		ind = parseInt(ind) + 1;
		</c:forEach>

		$("#old_count_indepth_hidden").val(ser);

	}

	function otheroptionopen(index) {

		console.log(index);

		var uni = $("#university" + index).val();

		if (uni == "-1") {
			// 			$("#universityother" + index).show();

			if ($("#universityother" + index).hasClass("d-none")) {
				$("#universityother" + index).removeClass("d-none")
			}

		} else {
			$("#universityother" + index).val("");
			// 			$("#universityother" + index).hide();

			if (!$("#universityother" + index).hasClass('d-none')) {
				$("#universityother" + index).addClass("d-none")
			}

		}

	}
</script>
