<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
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

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span>Practical Wise Learning Outcome
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Practical
									Wise Learning Outcome</li>
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
					<form:form name="Practicallearning_outcome"
						id="Practicallearning_outcome"
						action="Practical_Learning_OutcomeAction" method='POST'
						commandName="Practical_Learning_OutcomeCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Practical Wise Learning Outcome</h6>
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
										<!-- end select -->
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
										<!-- end select -->
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
										<!-- end select -->
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
										<!-- end select -->
										<input type="hidden" id="count_hidden_att"
											name="count_hidden_att" class="form-control autocomplete"
											value="1"> <input type='hidden' id='id' name="id"
											value='0' />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Practical<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="practical_id"
													class="singleselect form-control form-control-lg"
													id="practical_id">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>
								</div>
							</div>
							<section class="single-detail-block">
								<div class="row">
									<div class="col-lg-12">
										<h6 class="mb-10"></h6>
										<div
											class="table-wrapper table-responsive custom-table  b-top mt-0">
											<table class="table" id="att_Tb">

												<thead>
													<tr>
														<th rowspan="2"><h6>Sr No</h6></th>
														<th rowspan="2"><h6>Learning Outcome</h6></th>
														<th rowspan="2"><h6>Action</h6></th>
													</tr>
												</thead>
												<tbody id="att_Tbbody">
													<tr id="tr_id_att">
														<td>1</td>
														<td class="min-width">
															<div class="input-style-1">
																<input type="text" id="learni_outcome1"
																	name="learni_outcome1" class="form-control"
																	autocomplete="off"
																	placeholder="Learning Outcome">
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
											<!-- 				end table -->
										</div>
										<!-- 		end card -->
									</div>
									<!-- 	end col -->
								</div>
							</section>
							<!-- Bottom Button Start -->

							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li id="btn-reload"><a
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li><input value="Save" id="btn-save"
												class="main-btn info-btn btn-hover btnsave" type="button" /></li>
											<li><a href="nch_practical_learning_outcome_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>

											<!-- 								<li><a id="btn-view" -->
											<!-- 									class="main-btn dark-btn btn-hover btn-iconic-icon btnview" -->
											<!-- 									type="button"><i  class="lni lni-eye"></i>View</a></li> -->
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>


			<section class="single-detail-block">
				<div class="row" id="view_tbl">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-datatable-p">
								<table class="table" id="Practlearning_outcome">
									<thead>
										<tr>
											<th id="1"><h6>Sr No</h6></th>
											<th id="3"><h6>System</h6></th>
											<th id="4"><h6>Degree</h6></th>
											<th id="5"><h6>Professional</h6></th>
											<th id="6"><h6>Subject</h6></th>
											<th id="6"><h6>Practical</h6></th>
											<th id="7">View</th>
											<th><h6>Action</h6></th>
										</tr>
									</thead>
									<tbody class="custom-datatablepra">
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</section>
			<div class="modal fade custom-modal bd-example-modal-lg"
				tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
				id="modelWindow" aria-hidden="true">
				<div class="modal-dialog modal-lg modal-dialog-scrollable">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title">Practical Wise Learning Outcome</h3>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>

						<div class="modal-body custom-modal-table">
							<div class="custom-modal-inner">
								<div class="table-wrapper table-responsive custom-table">
									<table class="table model-table"
										id="Teaching_Learning_Method_ChildUrl">
										<thead>
											<tr>
												<th>Sr No</th>
												<th>Learning Outcome</th>
											</tr>
										</thead>
										<tbody id="att_TbbodyNameMed">
										</tbody>
									</table>
								</div>
							</div>
							<div class="modal-footer">
								<ul class="buttons-group">
									<li><button type="button"
											class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
											data-dismiss="modal" aria-label="Close">Close</button></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<c:url value="Edit_Practical_Learning_OutcomeUrl"
	var="Edit_Practical_Learning_OutcomeUrl" />
<form:form action="${Edit_Practical_Learning_OutcomeUrl}" method="post"
	id="editForm" name="editForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" />
</form:form>

<c:url value="deletePractical_Learning_OutcomeUrl"
	var="deletePractical_Learning_OutcomeUrl" />
<form:form action="${deletePractical_Learning_OutcomeUrl}" method="post"
	id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {

	$("#pop").hide();
	mockjax1('Practlearning_outcome');
	table = dataTable('Practlearning_outcome');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
		
	});
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


	function getcourselistby_professional() {
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();
		
		$.post('getCourseList_for_Curri?' + key + "=" + value,{  
			degree_id : degree_id,
			professional_id : professional_id
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



//Add-More-Add
var att=1;
function formopen_att(R){
	$("#att_Tb").show();
	
		 $("#id_add_att"+R).hide();
		 $("#att_id_remove"+R).hide();
		 
		 att=0;
		 att= parseInt(R)+1;
		 
		 if(att < 51){
				 
				 $("input#count_hidden_att").val(att);//current serial No
				 $("table#att_Tb").append('<tr align="center" id="tr_id_att'+att+'"><td>'+att+'</td>'
					 +'<td class="min-width"><div class="input-style-1"><input type="text" id="learni_outcome'+att+'" name="learni_outcome'+att+'" class="form-control"  autocomplete="off" placeholder="Practical Learning Outcome" ></div></td>'
					 +'<td><ul class="buttons-group"><li value = "ADD" title = "ADD" id = "id_add_att'+att+'" ><a class="main-btn success-btn btn-hover btn-sm"><i class="lni lni-plus"></i></a></li><li value="REMOVE" title = "REMOVE" id = "att_id_remove'+att+'" ><a class="main-btn danger-btn btn-hover btn-sm"><i class="lni lni-trash-can"></i></a></li></td>'
		   		     +'</tr>');
				     addOnclick(att);
				     removeOnclick(att);
			}else{
					alert("Please Enter max 50 Quantity");
					 if ( att == 51){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
		 var curcnt = $("#count_hidden_att").val();
		 $("#new_count_hidden").val(curcnt);
}
//Add-More-Remove
function formopen_re_att(R){
	var del_index = $("#idofprocedure"+R).val();
	if(String(del_index) == "undefined"){
		del_index="0";
	}else{
		del_index = del_index;
	}
	 var del_field_val = $("#del_id_hidden").val();
	 
	 if(del_field_val == "0,undefined"){
		 $("#del_id_hidden").val(del_index);
	 }else{
		 $("#del_id_hidden").val(del_field_val+","+del_index);
	 }
	 $("tr#tr_id_att"+R).remove();
	 att = att-1;
	 $("input#count_hidden_att").val(att);
	 if(R > 2){
		 $("#id_add_att"+att).show();
		 $("#att_id_remove"+att).show();
	 }
	 if(R == 2){
		 $("#id_add_att"+att).show();
	 }
	 var ncc = $("#new_count_hidden").val();
	 ncc = ncc-1;
	 $("#new_count_hidden").val(ncc);
}

document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('btn-save').onclick = function() {
		if(Validation()){
			 $("#Practicallearning_outcome").submit();
		}
	};
// 	document.getElementById('btn-view').onclick = function() {
// 		return View_Validation();
// 	};
	document.getElementById('system_id').onchange = function() {
		getdegreelistbysystem();
	};
	document.getElementById('professional_id').onchange = function() {
			getcourselistby_professional();
		};
		document.getElementById('course_id').onchange = function() {
			getPracticallistbyCourse();
		};
	document.getElementById('id_add_att1').onclick = function() {
		formopen_att(1)
	};
// 	document.getElementById('non_lecture_tl_method1').onkeypress = function() {
// 		return onlyAlphabetsStringSpace(event,this);
// 	};
// 	document.getElementById('no_of_activities1').onkeypress = function() {
// 		return isNumberKey0to9(event,this);
// 	};
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
		alert("Please Select Subject.");
		$("select#course_id").focus();
		return false;
	}
	var count = $("#count_hidden_att").val();
	for(var i=1;i<=count;i++){
		
		if ($("#learni_outcome"+i).val() == "") {
			alert("Please Enter Teaching Learning Method.");
			$("#learni_outcome"+i).focus();
			return false;
		}
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
		alert("Please Select Subject.");
		$("select#course_id").focus();
		return false;
	}
	getpop();
	return true;	
}

function data(Practlearning_outcome) {
	jsondata = [];
	var table = $('#' + Practlearning_outcome).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).attr('id').toLowerCase();
	var orderType = order[0][1];
	
	var system_id = $("#system_id").val();
	var degree_id = $("#degree_id").val();
	var professional_id = $("#professional_id").val();
	var course_id = $("#course_id").val();
	var practical_id = $("#practical_id").val();

	$.post("getFilterPract_learniOutcome?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		system_id:system_id,
		degree_id:degree_id,
		professional_id:professional_id,
		course_id:course_id,
		practical_id:practical_id
		
	}, function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].ser,j[i].system_name,j[i].degree_name,j[i].professional,j[i].course_name,j[i].practical,j[i].vd,j[i].action]);
		}
	});
	$.post("getTotalPract_learniOutcome?" + key + "=" + value, {
		system_id:system_id,
		degree_id:degree_id,
		professional_id:professional_id,
		course_id:course_id,
		practical_id:practical_id

	}, function(j) {
		FilteredRecords = j;
		});
	setTimeout(setTimeLoadForTable, 500);
}

function addOnclick(index){
	document.getElementById('id_add_att'+index).onclick = function() {
		formopen_att(index);
	};
	
// 	document.getElementById('non_lecture_tl_method'+index).onkeypress = function() {
// 		return onlyAlphabetsStringSpace(event,this);
// 	};
// 	document.getElementById('no_of_activities'+index).onkeypress = function() {
// 		return isNumberKey0to9(event,this);
// 	};
}
	
function removeOnclick(index){
	document.getElementById('att_id_remove'+index).onclick = function() {
		formopen_re_att(index);
	};
}

function EditData(id) {
	$("#id1").val(id);
	document.getElementById('editForm').submit();
}

function deleteData(id) {
	$("#id2").val(id);
	document.getElementById('deleteForm').submit();
}

function setTimeLoadForTable(){
	
	document.querySelectorAll('.ADDTopic_Learning_Outcome').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var cid = document.getElementById('Topic_Learning_OutcomeId'+val).value;
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				EditData(cid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.deleteTeaching_Learning_Method').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var dcid = document.getElementById('deleteID'+val).value;
			
			if (confirm('Are You Sure You Want to Delete Detail ?')) {
				deleteData(dcid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.VIEWdetails').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var hid = document.getElementById('viewId'+val).value;
			if (confirm('Are You Sure You Want to Show Detail ?')) {
				Pop_Up_view_child_data(hid);
			} else {
				return false;
			}
		})
	});
}

function Pop_Up_view_child_data(hid) {
	
	$('tbody#att_TbbodyNameMed').empty();
		$
				.post( 
						"Practical_Learning_Outcome_ChilddUrl?" + key
								+ "=" + value,
						{
							hid : hid
						},
						function(j) {
							for (var i = 0; i < j.length; i++) {
								var ser = parseInt(i) + 1;
								$("tbody#att_TbbodyNameMed")
									.append(
										'<tr id="tr_id_attNameMed'+ser+'"><td class="min-width">'
												+ ser
												+ '</td>'
												+ '<td class="min-width">'
												+ j[i][1]
												+ '</td>'
												+ '</tr>');
							}
						});
		
		  $('#modelWindow').modal('show');
}


</script>

