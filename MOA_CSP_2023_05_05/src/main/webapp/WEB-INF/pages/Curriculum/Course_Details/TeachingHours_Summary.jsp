<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<script type="text/javascript" src="js/watermark/common.js"></script>
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
<link rel="stylesheet" href="assets/vendor/internal_css.css">

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Add Summary Of The Course</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Add
									Summary Of The Course</li>
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
				<div class="col-12">
					<!-- input style start -->
					<form:form action="teach_hrs_summary_action" method="POST"
						class="form-horizontal" modelAttribute="teach_hrs_summary_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Add Summary Of The Course</h6>
								<div class="row">
									<input type="hidden" id="id" name="id" value="0"
										class="form-control">
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">System <span
												class="mandatory">*</span>
											</label>
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
										<!-- end select -->
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
										<!-- end select -->
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
										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Subject<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="course_id" id="course_id">
													<option value="0">--Select--</option>

												</select>
											</div>
										</div>
										<!-- end select -->
									</div>
								</div>
							</div>


							<!-- 								<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
							<!-- 									<div class="input-style-1"> -->
							<!-- 										<label>Total Number of Teaching Hours<span -->
							<!-- 											class="mandatory">*</span></label> <input type="text" -->
							<!-- 											id="total_teac_hrs" name="total_teac_hrs" -->
							<!-- 											class="autocomplete UpperClassName txt-transupp" -->
							<!-- 											autocomplete="off" maxlength="2" -->
							<!-- 											placeholder="Total Number of Teaching Hours" /> -->
							<!-- 									</div> -->
							<!-- 									end input -->
							<!-- 								</div> -->


							<div
								class="table-wrapper table-responsive custom-table-p simple-table b-top">
								<table class="table" id="table_cbc">
									<thead>
										<tr>
											<th><h6>Sr No</h6></th>
											<th><h6>
													Hours Type<strong class="mandatory">* </strong>
												</h6></th>
											<th><h6>
													Lecture Types<strong class="mandatory">* </strong>
												</h6></th>
											<th><h6>
													Paper<strong class="mandatory">* </strong>
												</h6></th>
											<th><h6>
													No of Hours <strong class="mandatory">* </strong>
												</h6></th>
											<th><h6>Action</h6></th>
										</tr>
										<!-- 						end table row -->
									</thead>
									<tbody id="tbody_cbc">
										<tr id="tr_id_cbc">
											<td>1</td>
											<td>
												<div class="select-style-1">
													<div class="select-position">
														<select class="form-control" id="hours_type1"
															name="hours_type1">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getTypeofHoursList}"
																varStatus="num">
																<option value="${item.id}" name="${item.type_of_hours}">${item.type_of_hours}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</td>

											<td>
												<div class="select-style-1">
													<div class="select-position">
														<select class="form-control" id="lecture_type1"
															name="lecture_type1">
															<option value="0">--Select--</option>

															<c:forEach var="item" items="${getTypeofTeachingList}"
																varStatus="num">
																<option value="${item.id}"
																	name="${item.type_of_teaching}">${item.type_of_teaching}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</td>

											<td>
												<div class="select-style-1">
													<div class="select-position">
														<select class="form-control" id="paper1" name="paper1">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getpaperList}"
																varStatus="num">
																<option value="${item.id}" name="${item.paper}">${item.paper}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</td>
											<td>
												<div class="input-style-1">
													<input type="text" id="no_of_hours1" name="no_of_hours1"
														maxlength="3" class="form-control"> <input
														type="hidden" id="teach_hrs_editid1"
														name="teach_hrs_editid1" value="0" class="form-control">
												</div>
											</td>
											<!-- <td>
													<input  type="hidden" id="teach_hrs_editid1" name="teach_hrs_editid1" value="0" class="form-control">
												</td> -->
											<td>
												<ul class="buttons-group">
													<li value="ADD" title="ADD" id="id_add_cbc1"><a
														class="main-btn success-btn btn-hover btn-sm"><i
															class="lni lni-plus"></i></a></li>
												</ul>
											</td>
										</tr>
									</tbody>
								</table>
								<input type="hidden" id="count_hidden_cbc"
									name="count_hidden_cbc" class="form-control autocomplete"
									value="1"> <input type="hidden"
									id="teach_hrs_old_count" name="teach_hrs_old_count"
									class="form-control autocomplete" value="0"> <input
									type="hidden" id="parent_id" name="parent_id"
									class="form-control autocomplete" value="0">


								<!-- 				end table -->
							</div>

						</div>

						<div class="row">
							<div class="col-lg-12">
								<div class="card-style mb-30">
									<h6 class="mb-10">Examination Summary</h6>
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="table_exam_sum">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>

													<th><h6>
															Paper<strong class="mandatory">* </strong>
														</h6></th>

													<th><h6>
															Theory Components Marks<strong class="mandatory">*
															</strong>
														</h6></th>

													<th><h6>Action</h6></th>
												</tr>
												<!-- 	end table row -->
											</thead>
											<tbody id="tbody_exam_sum">
												<tr id="tr_id_exam_sum">
													<td>1</td>

													<td>
														<div class="select-style-1">
															<div class="select-position">
																<select class="form-control" id="exam_paper1"
																	name="exam_paper1">
																	<option value="0">--Select--</option>
																	<c:forEach var="item" items="${getpaperList}"
																		varStatus="num">
																		<option value="${item.id}" name="${item.paper}">${item.paper}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
													</td>
													<td>
														<div class="input-style-1">
															<input type="text" id="theory_comp_marks1" maxlength="3"
																name="theory_comp_marks1" class="form-control">
															<input type="hidden" id="exm_smry_editid1"
																name="exm_smry_editid1" value="0" class="form-control">
														</div>
													</td>

													<td>
														<ul class="buttons-group">
															<li value="ADD" title="ADD" id="id_add_exam_sum1"><a
																class="main-btn success-btn btn-hover btn-sm"><i
																	class="lni lni-plus"></i></a></li>
														</ul>
													</td>
													<!-- <td>
												<input  type="hidden" id="exm_smry_editid1" name="exm_smry_editid1" value="0" class="form-control">
												</td> -->
												</tr>
											</tbody>
										</table>
										<input type="hidden" id="count_hidden_exam_sum"
											name="count_hidden_exam_sum"
											class="form-control autocomplete" value="1"> <input
											type="hidden" id="exam_sum_old_count"
											name="exam_sum_old_count" class="form-control autocomplete"
											value="0">

										<!-- 				end table -->
									</div>

									<div class="row mt-2 mb-2">
										<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-2">

											<div id="checkboxes" class="col-single">
												<div class="form-check radio-style checkbox-style">
													<label for="one" class="chklist"> Practical </label> <input
														type="checkbox" id="pract_cb" name="pract_cb"
														autocomplete="off" maxlength="25" class="form-check-input">

													<input type="hidden" id="pract_cb_hid" name="pract_cb_hid"
														autocomplete="off" value="0" class="form-input">
												</div>
											</div>
										</div>
										<span class="d-content d-none" id="pract_mark">
											<div class="col-6 col-sm-12 col-md-6 col-lg-2 ">
												<label for="one" class="chklist"> Marks </label>
											</div>
											<div class="col-6 col-sm-12 col-md-6 col-lg-4 ">
												<input type="text" id="practical_marks"
													name="practical_marks" maxlength="3" class="form-control"
													value="0">
											</div>
										</span>
									</div>


									<div class="row mt-2 mb-2">
										<div class="col-6 col-sm-12 col-md-6 col-lg-6 mb-2">
											<div id="checkboxes" class="col-single">
												<div class="form-check radio-style checkbox-style">
													<label for="one" class="chklist"> Viva </label> <input
														type="checkbox" id="viva_cb" name="viva_cb"
														autocomplete="off" maxlength="25" class="form-check-input">

													<input type="hidden" id="viva_cb_hid" name="viva_cb_hid"
														value="0" autocomplete="off" class="form-input">

												</div>
											</div>
										</div>
										<span class="d-content d-none" id="vv_mark">
											<div class="col-6 col-sm-12 col-md-6 col-lg-2 ">
												<label for="one" class="chklist"> Marks </label>
											</div>
											<div class="col-6 col-sm-12 col-md-6 col-lg-4 ">
												<input type="text" id="viva_marks" name="viva_marks"
													maxlength="3" class="form-control" value="0">
											</div>
										</span>
									</div>

									<div class="row mt-2 mb-2">
										<div class="col-6 col-sm-12 col-md-6 col-lg-6 mb-2">
											<div id="checkboxes" class="col-single">
												<div class="form-check radio-style checkbox-style">
													<label for="one" class="chklist"> Elective </label> <input
														type="checkbox" id="ele_cb" name="ele_cb"
														autocomplete="off" maxlength="25" class="form-check-input">
													<input type="hidden" id="ele_cb_hid" name="ele_cb_hid"
														value="0" autocomplete="off" class="form-input">

												</div>
											</div>
										</div>
										<span class="d-content d-none" id="ele_mark">
											<div class="col-6 col-sm-12 col-md-6 col-lg-2 ">
												<label for="one" class="chklist"> Marks </label>
											</div>
											<div class="col-6 col-sm-12 col-md-6 col-lg-4 ">
												<input type="text" id="elective_marks" name="elective_marks"
													maxlength="3" class="form-control" value="0">
											</div>
										</span>
									</div>

									<div class="row mt-2 mb-2">
										<div class="col-6 col-sm-12 col-md-6 col-lg-6 mb-2">
											<div id="checkboxes" class="col-single">
												<div class="form-check radio-style checkbox-style">
													<label for="one" class="chklist"> IA </label> <input
														type="checkbox" id="ia_cb" name="ia_cb" autocomplete="off"
														maxlength="25" class="form-check-input"> <input
														type="hidden" id="ia_cb_hid" name="ia_cb_hid" value="0"
														autocomplete="off" class="form-input"> <input
														type="hidden" id="cid" name="cid" value="0"
														autocomplete="off" class="form-input">

												</div>
											</div>
										</div>
										<span class="d-content d-none" id="ia_mark">
											<div class="col-6 col-sm-12 col-md-6 col-lg-2 ">
												<label for="one" class="chklist"> Marks </label>
											</div>
											<div class="col-6 col-sm-12 col-md-6 col-lg-4 ">
												<input type="text" id="ia_marks" name="ia_marks"
													class="form-control" maxlength="3" value="0">
											</div>
										</span>
									</div>


									<div class="row mt-2 mb-2">
										<div class="col-6 col-sm-12 col-md-6 col-lg-6 mb-2">
											<div id="checkboxes" class="col-single">
												<div class="form-check radio-style checkbox-style">
													<label for="one" class="chklist"> PA </label> <input
														type="checkbox" id="pa_cb" name="pa_cb" autocomplete="off"
														maxlength="25" class="form-check-input"> <input
														type="hidden" id="paid" name="paid" value="0"
														autocomplete="off" class="form-input">
													<!-- 													<input -->
													<!-- 													type="hidden" id="paid" name="paid" value="0" -->
													<!-- 													autocomplete="off" class="form-input"> -->

												</div>
											</div>
										</div>
										<span class="d-content d-none" id="pa_mark">
											<div class="col-6 col-sm-12 col-md-6 col-lg-2 ">
												<label for="one" class="chklist"> Marks </label>
											</div>
											<div class="col-6 col-sm-12 col-md-6 col-lg-4 ">
												<input type="text" id="pa_marks" name="pa_marks"
													class="form-control" maxlength="3" value="0">
											</div>
										</span> <input type="hidden" id="data_fetch" name="data_fetch"
											class="form-control" value="0">
									</div>


									<!-- Bottom Button Start -->
									<div class="btn-bottom">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input type="submit" id="btn-save"
														class="main-btn info-btn btn-hover btnsave" value="Save"></li>
													<li><input type="submit" id="btn-update"
														class="main-btn deactive-btn btn-hover btnupda"
														value="Update"></li>
													<li><a href="teach_hrs_summary_Url"
														class="main-btn dark-btn btn-hover btnreset">Reset</a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- Bottom Button End -->

						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

<c:url value="Edit_type_of_content_Url" var="Edit_type_of_content_Url" />
<form:form action="${Edit_type_of_content_Url}" method="post"
	id="updateForm" name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="deletetype_of_content_Url" var="deletetype_of_content_Url" />
<form:form action="${deletetype_of_content_Url}" method="post"
	id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>



<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

// 		mockjax1('search_type_of_content');
// 		table = dataTable('search_type_of_content');
// 		$('#btn-reload').on('click', function() {
// 			table.ajax.reload();
// 		});

	$("#btn-update").hide();

	});

function setTimeLoadForTable(){
		

		document.querySelectorAll('.ADDContent').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var conid = document.getElementById('ContntId'+val).value;
				var conname = document.getElementById('tyContnt'+val).value;
				var constatus = document.getElementById('ContntStatus'+val).value;
 				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(conid,conname,constatus);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.DeleteContent').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var condid = document.getElementById('DEContntId'+val).value;
				
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(condid);
				} else {
					return false;
				}
			})
		});
	}
	
// 	function data(search_type_of_content) {
// 		jsondata = [];
// 		var table = $('#' + search_type_of_content).DataTable();
// 		var info = table.page.info();
// 		var pageLength = info.length;
// 		var startPage = info.start;
// 		var endPage = info.end;
// 		var Search = table.search();
// 		var order = table.order();
// 		var orderColunm = $(table.column(order[0][0]).header()).html()
// 				.toLowerCase();
// 		var orderType = order[0][1];
// 		var type_of_content = $("#type_of_content").val();
// 		var status = $("#status").val();

// 		$.post("getFiltertype_of_content_data?" + key + "=" + value, {
// 			startPage : startPage,
// 			pageLength : pageLength,
// 			Search : Search,
// 			orderColunm : orderColunm,
// 			orderType : orderType,
// 			type_of_content : type_of_content,
// 			status : status,
// 		}, function(j) {
// // 			for (var i = 0; i < j.length; i++) {
// // 				jsondata.push([ j[i].ser, j[i].type_of_content, j[i].action ]);
// // 			}
// 		});
// 		$.post("getTotaltype_of_content_dataCount?" + key + "=" + value, {
// 			Search : Search,
// 			type_of_content : type_of_content
// 		}, function(j) {
// 			FilteredRecords = j;
// 		});
// 		setTimeout(setTimeLoadForTable, 1000);
// 	}

	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}
	function editData(id) {
		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}

	function Validation() {
		
		
		if ($("select#system_id").val() == "0") {
			alert("Please Select System.");
			$("select#system_id").focus();
			return false;
		}
		if ($("select#degree_id").val() == "0") {
			alert("Please Select Degree.");
			$("select#degree_id").focus();
			return false;
		}
		
		
		if ($("select#professional_id").val() == "0") {
			alert("Please Select Professional.");
			$("select#professional_id").focus();
			return false;
		}
		
		if ($("select#course_id").val() == "0") {
			alert("Please Select Subject.");
			$("select#course_id").focus();
			return false;
		}
		if ($("select#status").val() == "0") {
			alert("Please Select Status.");
			$("select#status").focus();
			return false;
		}
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
		
		 var count_cbc =  $("#count_hidden_cbc").val();
		
	for (var i = 1; i <= parseInt(count_cbc); i++) {

			if ($("select#hours_type" + i).val() == "0") {
				alert("Please Select Hours Type.");
				$("select#hours_type" + i).focus();
				return false;
			}

			if ($("select#lecture_type" + i).val() == "0") {
				alert("Please Select Lecture Type.");
				$("select#lecture_type" + i).focus();
				return false;
			}

			if ($("select#paper" + i).val() == "0") {
				alert("Please Select Paper.");
				$("select#paper" + i).focus();
				return false;
			}

			if ($("#no_of_hours" + i).val().trim() == "" || $("#no_of_hours" + i).val().trim() == "0") {
				alert("Please Enter No of Hours.");
				$("input#no_of_hours" + i).focus();
				return false;
			}
		}

		var count_exam = $("#count_hidden_exam_sum").val();
		for (var j = 1; j <= parseInt(count_exam); j++) {
			if ($("select#exam_paper" + j).val() == "0") {
				alert("Please Select Paper.");
				$("select#exam_paper" + j).focus();
				return false;
			}
			if ($("#theory_comp_marks" + j).val().trim() == "" || $("#theory_comp_marks" + j).val().trim() == "0") {
				alert("Please Enter Theory Components Marks.");
				$("input#theory_comp_marks" + j).focus();
				return false;
			}
		}
		
		var pract_cb = $('input[name="pract_cb"]:checkbox:checked').map(
				function() {
					return this.value;
				}).get();
		if (pract_cb == "on"  && $("#practical_marks").val().trim() == "0" || $("#practical_marks").val().trim() == "") {
			alert("Please Enter Practical Marks.");
			$("input#practical_marks").focus();
			return false;
		} 
		
		var viva_cb = $('input[name="viva_cb"]:checkbox:checked').map(
				function() {
					return this.value;
				}).get();
		if (viva_cb == "on"  && $("#viva_marks").val().trim() == "0" || $("#viva_marks").val().trim() == "") {
			alert("Please Enter Viva Marks.");
			$("input#viva_marks").focus();
			return false;
		} 
		
		var ele_cb = $('input[name="ele_cb"]:checkbox:checked').map(
				function() {
					return this.value;
				}).get();
		if (ele_cb == "on"  && $("#elective_marks").val().trim() == "0" || $("#elective_marks").val().trim() == "") {
			alert("Please Enter Elective Marks.");
			$("input#elective_marks").focus();
			return false;
		} 
		
		var ia_cb = $('input[name="ia_cb"]:checkbox:checked').map(
				function() {
					return this.value;
				}).get();
		if (ia_cb == "on"  && $("#ia_marks").val().trim() == "0" || $("#ia_marks").val().trim() == "") {
			alert("Please Enter IA Marks.");
			$("input#ia_marks").focus();
			return false;
		} 
		var pa_cb = $('input[name="pa_cb"]:checkbox:checked').map(
				function() {
					return this.value;
				}).get();
		if (pa_cb == "on"  && $("#pa_marks").val().trim() == "0" || $("#pa_marks").val().trim() == "") {
			alert("Please Enter PA Marks.");
			$("input#pa_marks").focus();
			return false;
		} 
	}

	function Getinput_tbody_cbc(q) {
		var ser = 0;

		if ($('#tbody_cbc' + q).length) {
			$("#tbody_cbc" + q).hide();
		}
		ser = parseInt(q) + 1;

		$("#count_hidden_cbc").val(ser);
		$("table#table_cbc")
				.append('<tr id="tr_id_cbc'+ser+'">'
								+ '<td>'
								+ ser
								+ '</td>'
								+ '<td><div class="select-style-1"><div class="select-position">'
								+ '<select class="form-control" id="hours_type'+ser+'" name="hours_type'+ser+'">'
								+ '<option value="0">--Select--</option><c:forEach var="item" items="${getTypeofHoursList}" varStatus="num"> '
								+ '<option value="${item.id}" name="${item.type_of_hours}">${item.type_of_hours}</option></c:forEach>'
								+ '</select></div></div></td>'
								+ '<td><div class="select-style-1"><div class="select-position">'
								+ '<select class="form-control" id="lecture_type'+ser+'" name="lecture_type'+ser+'">'
								+ '<option value="0">--Select--</option><c:forEach var="item" items="${getTypeofTeachingList}" varStatus="num">'
								+ '<option value="${item.id}" name="${item.type_of_teaching}">${item.type_of_teaching}</option></c:forEach>'
								+ '</select></div></div></td>'
								+ '<td><div class="select-style-1"><div class="select-position">	<select class="form-control" id="paper'+ser+'" name="paper'+ser+'"><option value="0">--Select--</option>'
								+ '<c:forEach var="item" items="${getpaperList}"	varStatus="num"><option value="${item.id}" name="${item.paper}">${item.paper}</option></c:forEach>'
								+ '</select></div></div></td>'
								+ '<td><div class="input-style-1"><input type="text" id="no_of_hours'+ser+'"  maxlength="3"'
								+'name="no_of_hours'+ser+'" class="form-control"> </div></td>'
								+ '<td > <ul class="buttons-group">'
								+ '	<li value="ADD" title="ADD" id="id_add_cbc'+ser+'"><a class="main-btn success-btn btn-hover btn-sm"><i class="lni lni-plus"></i></a></li>'
								+ '<li value="REMOVE" title="REMOVE" id="id_remove_cbc'+ser+'"><a class="main-btn danger-btn btn-hover btn-sm"><i class="lni lni-trash-can"></i></a></li>'
								+ '</ul></td>');
		
				
				 $("table#table_cbc").append( '<input  type="hidden" id="teach_hrs_editid'+ser+'" name="teach_hrs_editid'+ser+'" value="0" class="form-control">');

		addMoreCsp(ser);
		removeMoreCsp(ser);
		
		$("#id_add_cbc" + q).hide();
		$("#id_remove_cbc" + q).hide();
	}

	function remove_tbody_cbc(R) {
		$("tr#tr_id_cbc" + R).remove();
		var att = R - 1;
		$("input#count_hidden_cbc").val(att);
		$("#id_add_cbc" + att).show();
		$("#id_remove_cbc" + att).show();
	}

	function addMoreCsp(obj) {
		document.getElementById('id_add_cbc' + obj).onclick = function() {
			Getinput_tbody_cbc(obj);
		};

		document.getElementById('no_of_hours' + obj).onkeypress = function() {
			return isNumberKey0to9(event);
		};
	}
	function removeMoreCsp(obj) {
		document.getElementById('id_remove_cbc' + obj).onclick = function() {
			remove_tbody_cbc(obj);
		};
	}

	function Getinput_tbody_exam_sum(q) {

		var ser = 0;

		if ($('#tbody_exam_sum' + q).length) {
			$("#tbody_exam_sum" + q).hide();
		}

		ser = parseInt(q) + 1;

		$("#count_hidden_exam_sum").val(ser);
		$("table#table_exam_sum")
				.append(
						'<tr id="tr_id_exam_sum'+ser+'">'
								+ '<td>'
								+ ser
								+ '</td>'
								+ '<td><div class="select-style-1"><div class="select-position">'
								+ '	<select class="form-control" id="exam_paper'+ser+'" name="exam_paper'+ser+'"><option value="0">--Select--</option>'
								+ '<c:forEach var="item" items="${getpaperList}"	varStatus="num"> <option value="${item.id}" name="${item.paper}">${item.paper}</option>	</c:forEach>'
								+ '	</select></div></div></td>'
								+ '<td><div class="input-style-1"><input type="text" id="theory_comp_marks'+ser+'"  maxlength="3"'
								+ 'name="theory_comp_marks'+ser+'" class="form-control">	</div></td>'
								+ '<td > <ul class="buttons-group">'
								+ '	<li value="ADD" title="ADD" id="id_add_exam_sum'+ser+'"><a class="main-btn success-btn btn-hover btn-sm"><i class="lni lni-plus"></i></a></li>'
								+ '<li value="REMOVE" title="REMOVE" id="id_remove_exam_sum'+ser+'"><a class="main-btn danger-btn btn-hover btn-sm"><i class="lni lni-trash-can"></i></a></li>'
								+ '</ul></td>');
		
		$("table#table_exam_sum").append( '<input  type="hidden" id="exm_smry_editid'+ser+'" name="exm_smry_editid'+ser+'" value="0" class="form-control">');

		add_exam_sum(ser);
		remove_exam_sum(ser);

		$("#id_add_exam_sum" + q).hide();
		$("#id_remove_exam_sum" + q).hide();
	}

	function remove_tbody_exam_sum(R) {
		$("tr#tr_id_exam_sum" + R).remove();
		var att = R - 1;
		$("input#count_hidden_exam_sum").val(att);
		$("#id_add_exam_sum" + att).show();
		$("#id_remove_exam_sum" + att).show();
	}

	function add_exam_sum(obj) {
		document.getElementById('id_add_exam_sum' + obj).onclick = function() {
			Getinput_tbody_exam_sum(obj);
		};

		document.getElementById('theory_comp_marks' + obj).onkeypress = function() {
			return isNumberKey0to9(event);
		};
	}

	function remove_exam_sum(obj) {
		document.getElementById('id_remove_exam_sum' + obj).onclick = function() {
			remove_tbody_exam_sum(obj);
		};
	}

	// csp----------------------

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		
		document.getElementById('btn-update').onclick = function() {
			return Validation();
		};

		document.getElementById('id_add_cbc1').onclick = function() {
			Getinput_tbody_cbc('1');
		};

		document.getElementById('id_add_exam_sum1').onclick = function() {
			Getinput_tbody_exam_sum('1');
		};

		document.getElementById('system_id').onchange = function() {
			getdegreelistbysystem();
		};
		
		document.getElementById('course_id').onchange = function() {
			fetchsaveddata();
		};

		document.getElementById('practical_marks').onfocus = function() {
			this.select();
		};
		document.getElementById('viva_marks').onfocus = function() {
			this.select();
		};
		document.getElementById('elective_marks').onfocus = function() {
			this.select();
		};
		document.getElementById('ia_marks').onfocus = function() {
			this.select();
		};
		document.getElementById('pa_marks').onfocus = function() {
			this.select();
		};
		document.getElementById('professional_id').onchange = function() {
			getcourselistby_professional();
		};

		document.getElementById('pract_cb').onclick = function() {
			var paramvar = $('input[name="pract_cb"]:checkbox:checked').map(
					function() {
						return this.value;
					}).get();
			if (paramvar == "on") {
				$("#pract_mark").removeClass("d-none");
				$("#pract_cb_hid").val("1");

			} else {
				$("#pract_mark").addClass("d-none");
				$("#practical_marks").val("0");
				$("#pract_cb_hid").val("0");
			}
		};

		document.getElementById('viva_cb').onclick = function() {
			var paramvar = $('input[name="viva_cb"]:checkbox:checked').map(
					function() {
						return this.value;
					}).get();
			if (paramvar == "on") {
				$("#vv_mark").removeClass("d-none");
				$("#viva_cb_hid").val("1");
			} else {
				$("#vv_mark").addClass("d-none");
				$("#viva_marks").val("0");
				$("#viva_cb_hid").val("0");
			}
		};

		document.getElementById('ele_cb').onclick = function() {
			var paramvar = $('input[name="ele_cb"]:checkbox:checked').map(
					function() {
						return this.value;
					}).get();
			if (paramvar == "on") {
				$("#ele_mark").removeClass("d-none");
				$("#ele_cb_hid").val("1");
			} else {
				$("#ele_mark").addClass("d-none");
				$("#elective_marks").val("0");
				$("#ele_cb_hid").val("0");
			}
		};

		document.getElementById('ia_cb').onclick = function() {
			var paramvar = $('input[name="ia_cb"]:checkbox:checked').map(
					function() {
						return this.value;
					}).get();
			if (paramvar == "on") {
				$("#ia_mark").removeClass("d-none");
				$("#ia_cb_hid").val("1");
			} else {
				$("#ia_mark").addClass("d-none");
				$("#ia_marks").val("0");
				$("#ia_cb_hid").val("0");
			}
		};
		document.getElementById('pa_cb').onclick = function() {
			var paramvar = $('input[name="pa_cb"]:checkbox:checked').map(
					function() {
						return this.value;
					}).get();
			if (paramvar == "on") {
				$("#pa_mark").removeClass("d-none");
				$("#paid").val("1");
			} else {
				$("#pa_mark").addClass("d-none");
				$("#pa_marks").val("0");
				$("#paid").val("0");
			}
		};

		document.getElementById('no_of_hours1').onkeypress = function() {
			return isNumberKey0to9(event);
		};

		document.getElementById('theory_comp_marks1').onkeypress = function() {
			return isNumberKey0to9(event);
		};

		document.getElementById('practical_marks').onkeypress = function() {
			return isNumberKey0to9(event);
		};

		document.getElementById('viva_marks').onkeypress = function() {
			return isNumberKey0to9(event);
		};
		document.getElementById('elective_marks').onkeypress = function() {
			return isNumberKey0to9(event);
		};

		document.getElementById('ia_marks').onkeypress = function() {
			return isNumberKey0to9(event);
		};
		document.getElementById('pa_marks').onkeypress = function() {
			return isNumberKey0to9(event);
		};

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
// 							if('${list[0][2]}' != ''){
// 								$("select#degree_id").val('${list[0][2]}');
// 							}
						});
	}

	
	function getcourselistby_professional() {
		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();
		
		$.post('getCourseList_for_Curri?' + key + "=" + value,{  
			degree_id : degree_id,
			professional_id : professional_id,
			system_id : system_id
			}).done(function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#course_id").html(options);
		});
	}
	
	
	function fetchsaveddata() {
		
		if ($("select#system_id").val() == "0") {
			alert("Please Select System.");
			$("select#system_id").focus();
			return false;
		}
		if ($("select#degree_id").val() == "0") {
			alert("Please Select Degree.");
			$("select#degree_id").focus();
			return false;
		}
		
		
		if ($("select#professional_id").val() == "0") {
			alert("Please Select Professional.");
			$("select#professional_id").focus();
			return false;
		}
		
		if ($("select#course_id").val() == "0") {
			alert("Please Select Course.");
			$("select#course_id").focus();
			return false;
		}
		
		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();
		var course_id = $("#course_id").val();
		
		$.post('getfetchsaveddata_ctrl?' + key + "=" + value,
						{
							system_id : system_id,
							degree_id : degree_id,
							professional_id:professional_id,
							course_id:course_id
							
						}).done(function(j) {
							
							if (j.length > 0 ) {
								$("#btn-update").show();
								$("#btn-save").hide();
								
								$("#data_fetch").val("1");
							}
							
							var x = j[0][0];
							
							fetchteach_hrsdata(x);
							fetchexam_sumdata(x);
							
							$("#parent_id").val(x);
						});
	}
	
	
function fetchteach_hrsdata(p_id) {
		
		$.post('getfetchteach_hrssaveddata_ctrl?' + key + "=" + value,{p_id : p_id}).done(function(j) {
			
							var ser = 0;
								var ind =1;
							for (var i = 0; i < j.length; i++) {
								
								
								if(ser != "0"){
									Getinput_tbody_cbc(ser);
								}
								
								var id = j[i][0];
								var hours_type = j[i][1];
								var lecture_type = j[i][2];
								var paper = j[i][3];
								var no_of_hours = j[i][4];
								
								$("#teach_hrs_editid"+ind).val(id);
						        $("#hours_type"+ind).val(hours_type);
						        $("#hours_type"+ind).attr('style','pointer-events: none; touch-action: none; background: rgb(233, 236, 239);');
								$("#hours_type"+ind).attr('tabindex','-1');
						  		$("#lecture_type"+ind).val(lecture_type);
						  		$("#lecture_type"+ind).attr('style','pointer-events: none; touch-action: none; background: rgb(233, 236, 239);');
								$("#lecture_type"+ind).attr('tabindex','-1');
						  		$("#paper"+ind).val(paper);
						  		$("#no_of_hours"+ind).val(no_of_hours);
						  		
								ser=parseInt(ser)+1;
								ind=parseInt(ind)+1;
							}
							
							$("#teach_hrs_old_count").val(ser);
							
		});
	}
	
	
function fetchexam_sumdata(p_id) {
	
	$.post('getfetchexam_sumsaveddata_ctrl?' + key + "=" + value,{p_id : p_id}).done(function(j) {
						
						console.log(j);
						
						var ser = 0;
						var ind =1;
					
					for (var i = 0; i < j.length; i++) {
						
						
						if(ser != "0"){
							Getinput_tbody_exam_sum(ser);
						}
						
						var id = j[i][0];
						var exam_paper = j[i][1];
						var theory_comp_marks = j[i][2];
						var pract_cb = j[i][3];
						var practical_marks = j[i][4];
						var viva_cb = j[i][5];
						var viva_marks = j[i][6];
						var ele_cb = j[i][7];
						var elective_marks = j[i][8];
						var ia_cb = j[i][9];
						var ia_marks = j[i][10];
						var tt = j[i][11];
						var pa_cb = j[i][12];
						var pa_marks = j[i][13];
						
					var a =	$("#exm_smry_editid"+ind).val(id);

				        $("#exam_paper"+ind).val(exam_paper);
				        $("#exam_paper"+ind).attr('style','pointer-events: none; touch-action: none; background: rgb(233, 236, 239);');
						$("#exam_paper"+ind).attr('tabindex','-1');
				  		$("#theory_comp_marks"+ind).val(theory_comp_marks);
				  		$("#cid").val(tt);
				  		
				  		if (pract_cb == "1") {
				  			$("#pract_cb_hid").val(pract_cb);
				  			$("#pract_cb").attr("checked",true);
				  			$("#practical_marks").val(practical_marks);
				  			$("#pract_mark").removeClass("d-none");
						}
				  		
				  		if (viva_cb == "1") {
				  			$("#viva_cb_hid").val(viva_cb);
				  			$("#viva_cb").attr("checked",true);
				  			$("#viva_marks").val(viva_marks);
				  			$("#vv_mark").removeClass("d-none");
						}
				  		
				  		
				  		if (ele_cb == "1") {
				  			$("#ele_cb_hid").val(ele_cb);
				  			$("#ele_cb").attr("checked",true);
				  			$("#elective_marks").val(elective_marks);
				  			$("#ele_mark").removeClass("d-none");
						}
				  		
				  		if (ia_cb == "1") {
				  			$("#ia_cb_hid").val(ia_cb);
				  			$("#ia_cb").attr("checked",true);
				  			$("#ia_marks").val(ia_marks);
				  			$("#ia_mark").removeClass("d-none");
						}
				  		if (pa_cb == "1") {
				  			$("#paid").val(pa_cb);
				  			$("#pa_cb").attr("checked",true);
				  			$("#pa_marks").val(pa_marks);
				  			$("#pa_mark").removeClass("d-none");
						}
				  		
						ser=parseInt(ser)+1;
						ind=parseInt(ind)+1;
					}
					
					$("#exam_sum_old_count").val(ser);
	});
	
}
	
</script>