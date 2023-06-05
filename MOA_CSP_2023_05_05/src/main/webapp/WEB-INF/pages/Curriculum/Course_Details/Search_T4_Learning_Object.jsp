
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
							<span id="lbladd1"></span>Search T4 - Practical Learning
							Objectives
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Search
									T4 - Practical Learning Objectives</li>
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
					<form:form name="searcht4_learning_object"
						id="searcht4_learning_object"
						action="searcht4_learning_objectAction" method='POST'
						commandName="searcht4_learning_objectCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Search T4 - Practical Learning Objectives</h6>
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
												</select> <input type="hidden" id="count_hidden_att"
													name="count_hidden_att" class="form-control autocomplete"
													value="1"> <input type='hidden' id='id' name="id"
													value='0' />
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
										<li><a href="T4_Learning_Object_Url"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
													<i class="lni lni-chevron-left"></i>Back
											</a></li>
											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
					</form:form>
				</div>
			</div>
		<section class="single-detail-block">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_Topic">
							<thead>
								<tr>
									<th id="1"><h6>Sr No</h6></th>
									<th id="3"><h6>System</h6></th>
									<th id="4"><h6>Degree</h6></th>
									<th id="5"><h6>Professional</h6></th>
									<th id="6"><h6>Subject</h6></th>
									<th id="7"><h6>Practical</h6></th>
									<th id="8">View</th>
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
		<div class="modal fade custom-modal bd-example-modal-lg" tabindex="-1"
			role="dialog" aria-labelledby="myLargeModalLabel" id="modelWindow"
			aria-hidden="true">
			<div class="modal-dialog modal-xl modal-dialog-scrollable">
				<div class="modal-content">
				<div class="modal-header">
							<h3 class="modal-title">Table 4: Learning objectives (Practical)
							</h3>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
					<div class="modal-body custom-modal-table">
						<div class="custom-modal-inner">
							<div class="table-wrapper table-responsive custom-table">
								<table class="table model-table"
									id="T4SearchLearningObject_ChildUrl">
									<thead>
										<tr>
											<th><h6>Sr No</h6></th>
											<!-- 0 -->
											<th><h6>A3 Course Outcome</h6></th>
											<th><h6>B3 Learning Objective(At the end of the
													session,the students should be able to)</h6></th>
											<th><h6>C3 Domain/sub</h6></th>
											<th><h6>D3 Must to know/desirable to know/Nice to
													know</h6></th>
											<th><h6>E3 Level Does/Shows how/Knows how/Know</h6></th>
											<th><h6>F3 T-L method</h6></th>
											<th><h6>G3 Assessment</h6></th>
											<th><h6>H3 Formative/Summative</h6></th>
											<th><h6>I3 Term</h6></th>
											<th><h6>J3 Integration</h6></th>
											<!-- 									<th><h6>Action</h6></th> -->
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

<c:url value="Edit_T4_Search_Learning_Object_Url"
	var="Edit_T4_Search_Learning_Object_Url" />
<form:form action="${Edit_T4_Search_Learning_Object_Url}" method="post"
	id="editForm" name="editForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" />
</form:form>

<c:url value="delete_T4_Search_Learning_Object_Url"
	var="delete_T4_Search_Learning_Object_Url" />
<form:form action="${delete_T4_Search_Learning_Object_Url}"
	method="post" id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {

	mockjax1('search_Topic');
	table = dataTable('search_Topic');
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
function getcourselistbysystemdegreeprof() {
	var system_id = $("#system_id").val();
	var degree_id = $("#degree_id").val();
	var professional_id = $("#professional_id").val();
	
	
	$.post('getCourseList_for_Curri?' + key + "=" + value,
		{
			degree_id : degree_id,
			system_id : system_id,
			professional_id:professional_id
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

function getPracticalListbyCourse() {
	
	var course_id = $("#course_id").val();
	$
			.post('getPracticalListby_Course?' + key + "=" + value, {
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

document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('system_id').onchange = function() {
		getdegreelistbysystem();
	};
	document.getElementById('professional_id').onchange = function() {
		getcourselistbysystemdegreeprof();
	};
	document.getElementById('course_id').onchange = function() {
		getPracticalListbyCourse();
	};
// 	document.getElementById('id_add_att1').onclick = function() {
// 		formopen_att(1);
// 	};
});

function data(search_Topic) {
	jsondata = [];
	var table = $('#' + search_Topic).DataTable();
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

	$.post("getFilterT4SearchLearningObject_data?" + key + "=" + value, {
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
		
// 	}, function(j) {
// 		for (var i = 0; i < j.length; i++) {
// 			jsondata.push([ j[i].ser,j[i].course_outcome,j[i].b3_learning_obj,j[i].domain,j[i].scope,j[i].rep_und,
// 				j[i].method,j[i].assessment_method,j[i].assessment_type, j[i].term ,j[i].j3_integration,j[i].action]);
// 		}
}, function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].ser,j[i].system_name,j[i].degree_name,j[i].professional,j[i].course_name,j[i].practical,
				j[i].vd,j[i].action]);
		}
	});
	$.post("getTotalT4SearchLearningObject_dataCount?" + key + "=" + value, {
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

function EditData(id) {
	$("#id1").val(id);
	document.getElementById('editForm').submit();
}

function deleteData(id) {
	$("#id2").val(id);
	document.getElementById('deleteForm').submit();
}

function setTimeLoadForTable(){
	
	document.querySelectorAll('.ADDtopic').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var cid = document.getElementById('topicId'+val).value;
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				EditData(cid);
				//Pop_Up_view_child_data(cid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.deleteTopic').forEach((items, index) => {
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
// 			debugger;
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
// 	debugger;
	$('tbody#att_TbbodyNameMed').empty();
		$
				.post( 
						"T4SearchLearningObject_ChildUrl?" + key
								+ "=" + value,
						{
							hid : hid
						},
						function(j) {
							for (var i = 0; i < j.length; i++) {
								var ser = parseInt(i) + 1;
//								alert(j.length);
								$("tbody#att_TbbodyNameMed")
									.append(
										'<tr id="tr_id_attNameMed'+ser+'"><td class="min-width">'
												+ ser
												+ '</td>'
// 												+ '<td class="min-width">'
// 												+ j[i].ser
// 												+ '</td>'
												+ '<td class="min-width">'
												+ j[i][0]
												+ '</td>'
												+ '<td class="min-width">'
												+ j[i][1]
												+ '</td>'
												+ '<td class="min-width">'
												+ j[i][2]
												+ '</td>'
												+ '<td class="min-width">'
												+ j[i][3]
												+ '</td>'
												+ '<td class="min-width">'
												+ j[i][4]
												+ '</td>'
												+ '<td class="min-width">'
												+ j[i][5]
												+ '</td>'
												+ '<td class="min-width">'
												+ j[i][6]
												+ '</td>'
												+ '<td class="min-width">'
												+ j[i][7]
												+ '</td>'
												+ '<td class="min-width">'
												+ j[i][8]
												+ '</td>'
												+ '<td class="min-width">'
												+ j[i][9]
												+ '</td>'
												+ '</tr>');
							}
						});
		
		  $('#modelWindow').modal('show');
}

</script>