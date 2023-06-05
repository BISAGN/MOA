<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/common/multicheck.js" type="text/javascript"></script>
<!-- datatable style and js start-->
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
							<span id="lbladd"></span> T1-link Course Outcome And Program
							Outcome
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">T1-link
									Course Outcome And Program Outcome </li>
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
					<form:form name="linkmaster_co_po" id="linkmaster_co_po"
						action="link_co_and_po_action" method="post"
						class="form-horizontal" modelAttribute="link_co_and_po_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">T1-link Course Outcome And Program
									Outcome</h6>
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
											<label for="username">Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="degree_id" id="degree_id">
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
														<option value="${item.id}" name="${item.id}">${item.professional}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Subject<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="course_id" id="course_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getCourseList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Course Outcome<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="course_outcome_id" id="course_outcome_id">
													<option value="0">--Select--</option>

												</select>
											</div>
										</div>
									</div>
									<!-- 								<div class="col-12 col-sm-12 col-md-6 col-lg-4"></div> -->
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-with-selection">
											<div class="input-style-1 mb-0">
												<label id="programoutcome_name"> Program Outcome (0)<span
													class="mandatory">*</span></label> <input type="text"
													id="search_data" autocomplete="off"
													placeholder="Search Program Outcome">
											</div>
											<!-- <div class="col-two custom-d-none" id="checkboxes" class="chklist">< -->
											<div class="col-two" id="checkboxes" class="chklist"></div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-with-selection">
											<div class="input-style-1 mb-0">
												<label>Selected Program Outcome<span
													class="mandatory">*</span></label> <input type="text" id="value"
													name="value" maxlength="70"
													placeholder="Selected Program Outcome" />
											</div>
											<div class="badges-groups">
												<ul id="show_list" class="buttons-group">
												</ul>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1 mt-3">
											<!-- Hidden Start -->
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" /> <input type="hidden" id="Edit_ids"
												name="Edit_ids" value="0" autocomplete="off" /> <input
												type="hidden" name="old_programoutcome"
												id="old_programoutcome" /> <input type="hidden"
												name="new_programoutcome" id="new_programoutcome" /> <input
												type="hidden" name="add_programoutcome"
												id="add_programoutcome" /> <input type="hidden"
												name="remove_programoutcome" id="remove_programoutcome" />
											<!-- Hidden End -->
										</div>
									</div>
								</div>
							</div>
							<ul class="buttons-group mainbtn">
								<li id="btn-reload1"><a
									href="Link_Course_Outcome_And_Program_Outcome_Url"
									class="main-btn dark-btn-outline  btn-hover btn-iconic-icon">
										<i class="lni lni-chevron-left"></i>Back
								</a></li>
								<li id="btn-update"><input
									class="main-btn deactive-btn btn-hover" type="button"
									value="Update" /></li>
								<li id="btn-reload"><a
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li id="btn-save"><input
									class="main-btn info-btn btn-hover" type="button" value="Save" /></li>

								<li id="btn-reload2"><a
									href="Link_Course_Outcome_And_Program_Outcome_Url"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
								<li id="btn-view"><a
									class="main-btn dark-btn btn-hover btn-iconic-icon btnview"
									type="button"><i class="lni lni-eye"></i>View</a></li>
							</ul>
						</div>

						<div class="tables-wrapper">
							<div class="row">
								<div id="pop">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="card-style mb-30">
											<h3>Link Course Outcome And Program Outcome</h3>
											<div
												class="table-wrapper table-responsive custom-table custom-table-v2"
												id="container-table">
												<table class="table table-striped" id="popT1">
													<thead>
														<tr>
															<th><p>CO No</p></th>
															<th><p>A1 Course learning Outcome (CO) AyUG RS
																	At the end of the Subject AyUG , the student should
																	be able to</p></th>
															<th><p>B1 Course learning Outcome matched with
																	program learning outcomes..</p></th>
														</tr>

													</thead>
													<tbody></tbody>
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
			<section class="single-detail-block">
				<div class="row">
					<div id="view_tbl">
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="card-style mb-30">
								<div class="table-wrapper table-responsive custom-datatable-p">
									<table class="table" id="search_Co_Po">
										<thead>
											<tr>
												<th id="1"><h6>Sr No</h6></th>
												<th id="7"><h6>System</h6></th>
												<th id="9"><h6>Degree</h6></th>
												<th id="11"><h6>Professional</h6></th>
												<th id="12"><h6>Subject</h6></th>
												<th id="14"><h6>Course Outcome</h6></th>
												<th id="15"><h6>Program Outcome</h6></th>
												<th><h6>Action</h6></th>
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
			</section>
			<div class="modal fade custom-modal bd-example-modal-lg"
				tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
				id="modelWindow" aria-hidden="true">
				<div class="modal-dialog modal-xl modal-dialog-scrollable">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title" id="myLargeModalLabel">Program
								Outcome</h3>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
							<!-- 				<i data-dismiss="modal" aria-label="Close" class="bi bi-x-lg"></i> -->
						</div>
						<div class="modal-body">
							<div class="custom-modal-inner" id="headData1" name="headData1">
								<div class="row">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table model-table" id="Po_Data_Url">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Program Outcome</h6></th>
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
												class="main-btn dark-btn n btn-hover"
												data-bs-dismiss="modal" data-dismiss="modal"
												aria-label="Close">Close</button></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
</section>

<c:url value="Co_Po_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
 		$("#pop").hide();
		$("#btn-update").hide();
		$("#btn-reload1").hide();
		
		initiateChkFn('new_programoutcome', 'old_programoutcome',
				'add_programoutcome', 'remove_programoutcome',
				'Program Outcome', 'programoutcome_name');
		
		mockjax1('search_Co_Po');
		table = dataTable('search_Co_Po');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
	});
	
	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('btn-save').onclick = function() {
			if(Validation()){
				 $("#linkmaster_co_po").submit();
			}
		};
		document.getElementById('btn-view').onclick = function() {
			return View_Validation();	
		};
		document.getElementById('btn-update').onclick = function() {
			return UpdateFn();
		};
		document.getElementById('system_id').onchange = function() {
			getdegreelistbysystem();
		};
		
		document.getElementById('professional_id').onchange = function() {
			getcourselistby_professional();
		};
		
		document.getElementById('course_id').onchange = function() {
			getCobyCourse();
		};
		document.getElementById('degree_id').onchange = function() {
			getPOlistbyDegree(this.value);
		};
		document.getElementById('search_data').onkeyup = function() {
			fnFilterChk(this.value);
		};
		
		
		
		
	});

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
	
	function getCobyCourse() {
		var course_id = $("#course_id").val();
		$.post('getCobyCourse?' + key + "=" + value,{
					course_id : course_id
				}).done(function(j) {
							
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#course_outcome_id").html(options);
		});
	}

function getPOlistbyDegree(degree_id) {
	$('div#checkboxes').empty();
	$
			.post('getPO_listby_Degree?' + key + "=" + value, {
				degree_id : degree_id
			})
			.done(
					function(data) {
						
						var options = '';
						for (var i = 0; i < data.length; i++) {
							options += '<label for="one"  class="chklist"> <input class="customeCheck" type="checkbox" name="multisub"  id="multisub'
									+ data[i][0]
									+ '" value='
									+ data[i][0]
									+ '  />'
									+ data[i][1]
									+ '</label>';
						}
						
						$("div#checkboxes").append(options);
						
						for (var i = 0; i < data.length; i++) {
							chxboxOnclick(data[i][0]);
						}
						
			}).fail(function(xhr, textStatus, errorThrown) {
	});
}

function chxboxOnclick(ser){
 	document.getElementById('multisub'+ser).onclick = function(){
 		chkClick();
	};
}	
	function data(search_Co_Po) {
		jsondata = [];
		var table = $('#' + search_Co_Po).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr('id')
				.toLowerCase();
		var orderType = order[0][1];
		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();
		var course_id = $("#course_id").val();
		var course_outcome_id = $("#course_outcome_id").val();
		var programoutcome_name = $("#new_programoutcome").val();
		var status = $("#status").val();
		$.post("getFilterCo_Po_Data_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			system_id :system_id,
			degree_id : degree_id,
			professional_id : professional_id,
			course_id : course_id,
			course_outcome_id : course_outcome_id,
			programoutcome_name : programoutcome_name
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser,j[i].system_name,j[i].degree_name, j[i].professional,j[i].course_name,j[i].course_outcome, j[i].vd,j[i].action ]);
			}
		});
	
		$.post("getTotalCo_Po_Data_dataCount?" + key + "=" + value, {
			Search : Search,
			system_id :system_id,
			degree_id : degree_id,
			professional_id : professional_id,
			course_id : course_id,
			course_outcome_id : course_outcome_id,
			programoutcome_name : programoutcome_name
		}, function(j) {
			FilteredRecords = j;
		});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
function setTimeLoadForTable(){
	
	document.querySelectorAll('.ADDCo').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var hid = document.getElementById('apId'+val).value;//parent id 1
			var hsys = document.getElementById('apsys'+val).value;
			var hdeg = document.getElementById('apd'+val).value;//degreeid 2
			var hco = document.getElementById('apco'+val).value;//co o id 3
			var hpi = document.getElementById('appi'+val).value;//professional_id 4
			var hcid = document.getElementById('apcid'+val).value;//course_id 5
			var hpo = document.getElementById('appo'+val).value;//poid 6
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				editData(hid,hsys,hdeg,hco,hpi,hcid,hpo);
			} else {
				return false;
			}
		})
	});
	
	
	document.querySelectorAll('.deleteOnclick').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var hid = document.getElementById('deleteID'+val).value;
			if (confirm('Are You Sure You Want to Delete Detail ?')) {
				deleteData(hid);
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
				getPopup_Po_Datalist(hid);
			} else {
				return false;
			}
		})
	});
}
		
function editData(id,system_id,degree_id,course_outcome_id,professional_id,course_id,poid) {
	$.ajaxSetup({
		async : false
	});
	$("#btn-update").show();
	$("#btn-save").hide();
	$("#btn-reload").hide();
	$("#btn-reload2").hide();
	$("#btn-reload1").show();
	$("#btn-view").hide();
	$("#view_tbl").hide();
	
	document.getElementById('lbladd').innerHTML = "Update ";
	$("select#system_id").val(system_id);
	$('#system_id').trigger('change');
	getdegreelistbysystem();
	$("select#degree_id").val(degree_id);
	getPOlistbyDegree(degree_id);
	$("select#professional_id").val(professional_id);
	$('#professional_id').trigger('change');
	getcourselistby_professional();
	$("select#course_id").val(course_id);
	getCobyCourse();
	$("select#course_outcome_id").val(course_outcome_id);
	$('#course_outcome_id').trigger('change');
	
	$("#Edit_ids").val(id);
	setChk(poid);
	document.getElementById('id').value=id;
}

function deleteData(id) {
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}
		

function UpdateFn(){
	$.ajaxSetup({
		async : false
	});
	var id = $("#id").val();
	var system_id = $("#system_id").val();
	var degree_id = $("#degree_id").val();
	var professional_id = $("#professional_id").val();
	var course_id = $("#course_id").val();
	var course_outcome_id = $("#course_outcome_id").val();
	
	
	var add_programoutcome = $("#add_programoutcome").val();
	var remove_programoutcome = $("#remove_programoutcome").val();
	var old_programoutcome = $("#old_programoutcome").val();
	var new_programoutcome = $("#new_programoutcome").val();
	
	$.post('update_Co_Po_Action?' + key + "=" + value, {
		id : id,
		system_id : system_id,
		degree_id : degree_id,
		professional_id : professional_id,
		course_id : course_id,
		course_outcome_id : course_outcome_id,
		add_programoutcome : add_programoutcome,
		remove_programoutcome : remove_programoutcome,
		old_programoutcome : old_programoutcome,
		new_programoutcome : new_programoutcome
		
	}).done(function(j) {
		if(j == "Data Updated Successfully"){
			alert(j);
			window.location.reload();
		}else{
			alert(j);
		}
	});
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
			if ($("#course_id").val().trim() == "0") {
				alert("Please Select Subject.");
				$("select#course_id").focus();
				return false;
			}
			if ($("#course_outcome_id").val().trim() == "0") {
				alert("Please Select Course Outcome");
				$("select#course_outcome_id").focus();
				return false;
			}
			if($("#programoutcome_name").val() == "0" || $("#programoutcome_name").val() == ""){
				alert("Please Select Program Outcome.");
				$("#programoutcome_name").focus();
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
		
	function getPopup_Po_Datalist(hid) {
		$('tbody#att_TbbodyNameMed').empty();
		var poidar = hid.split(",");
		for(var p=0;p<poidar.length;p++){
			var poid = poidar[p];
			var ser = parseInt(p) + 1;
			$.post("Pro_out_Data_Url?" + key+ "=" + value,
					{ hid : poid },function(j) {
						for (var i = 0; i < j.length; i++) {
							$("tbody#att_TbbodyNameMed")
								.append(
									'<tr id="tr_id_attNameMed'+ser+'"><td class="min-width">'
											+ ser
											+ '</td>'
											+ '<td class="min-width">'
											+ j[i][0]
											+ '</td>'
											+ '</tr>');
						}
					});
		}
		$('#modelWindow').modal('show');
		
		
	}
	
	function getpop() {
		
		$("#view_tbl").hide();
        $("table#popT1 > tbody").empty();
		var course_id = $("#course_id").val();
		$.post("getView_CO_PO_data?"+key+"="+value,{course_id:course_id},function(j) {
			for(var i=0;i<j.length;i++){
				
				paper = j[i][4];
					$("table#popT1").append('<tr id="popT1R">' 
								+'<td><p id="co_code">'+j[i][0]+'</p></td>'
								+'<td><p id="co">'+j[i][1]+'</p></td>'
								+'<td><p id="po">'+j[i][2]+'</p></td>'
								+'</tr>');
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
	</script>