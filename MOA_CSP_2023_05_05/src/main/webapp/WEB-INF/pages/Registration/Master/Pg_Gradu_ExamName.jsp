<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
					<span id="lbladd"></span>
						<h2>Exam </h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Exam
									</li>
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
					<form:form name="pg_gradu_Examname_form" id="pg_gradu_Examname_form" action="pg_gradu_Examname_Action"
						method="post" class="form-horizontal" modelAttribute="pg_gradu_Examname_CMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Exam </h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Name Of Exam <span class="mandatory">*</span></label> <input
											type="text" id="name_of_the_exam" name="name_of_the_exam"
											class="autocomplete xt-transupp"
											autocomplete="off" maxlength="100" placeholder="Name Of Exam" />
										
									</div>
									<!-- end input -->
								</div>

								
								
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="username">System<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="system_id" id="system_id">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${s_name}" varStatus="num">
										<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
									</c:forEach>
								</select>
									</div>
								</div>								
								<div class="input-style-2 mt-3">
									<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
								</div>
								
								<!-- end select -->
							</div>

							
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="username">Degree<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="degree_id" id="degree_id">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${d_name}" varStatus="num">
										<option value="${item.id}" name="${item.degree_name}">${item.degree_name}</option>
									</c:forEach>
								</select>
									</div>
								</div>								
								<!-- end select -->
							</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status">
												<c:forEach var="item" items="${ActiveInActiveList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="input-style-2 mt-3">
										<input type="hidden" id="id" name="id" value="0"
											autocomplete="off" />
									</div>
									
								</div>
								<!-- end select -->

							</div>

							<ul class="buttons-group mainbtn">

								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" value="Save" />
								</li>
								<li><a href="pg_gradu_Examname_Url"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
							</ul>
						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>


		<div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="gradu_Examname">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th id="${item.id}"><h6>Exam</h6></th>
									<th><h6>System</h6></th>
									<th><h6>Degree</h6></th>
									<th><h6>Action</h6></th>
									
								</tr>
								<!-- 						end table row -->
							</thead>
							<tbody>
							</tbody>
						</table>
						<!-- 				end table -->
					</div>
				</div>
				<!-- 		end card -->
			</div>
			<!-- 	end col -->
		</div>
	</div>
</section>

<c:url value="pg_gradu_Examname_type" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="pg_gradu_Examname_name1">
	<input type="hidden" name="pg_gradu_Examname_name1" id="pg_gradu_Examname_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_pg_gradu_Examname_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="pg_gradu_Examname_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="pg_gradu_Examnamereport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('gradu_Examname');
		table = dataTable('gradu_Examname');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {	
			this.value = this.value.toUpperCase();
		});
	});
	
	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
//		document.getElementById('name_of_the_exam').onkeypress = function() {
//			return onlyAlphabetsStringSpace(this, event);
//		};
		
		document.getElementById('system_id').onchange = function() {
			return GetSystemFromDegree();
		};
	});
	
	function setTimeLoadForTable(){
		

 		document.querySelectorAll('.ADDExamname').forEach((items, index) => {
 			items.addEventListener('click', event => {
 				var val=parseInt(index)+1;
 				var hid = document.getElementById('apIdExam'+val).value;
 				var hpEname = document.getElementById('apnameExam'+val).value;
 				var hpEsystem = document.getElementById('apsystemExam'+val).value;
 				var hpEdegree = document.getElementById('apdegreeExam'+val).value;
				var hstatus = document.getElementById('apstatusAGE'+val).value;
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
 					editData(hid,hpEname,hpEsystem,hpEdegree,hstatus);
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
		
	}
	
	function data(gradu_Examname) {
		
		jsondata = [];
		var table = $('#' + gradu_Examname).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var name_of_the_exam = $("#name_of_the_exam").val();
		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();
		var status = $("#status").val();

		$.post("getFilterexamname_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			name_of_the_exam : name_of_the_exam,
			system_id : system_id,
			degree_id : degree_id,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].name_of_the_exam,j[i].system_name,j[i].degree_name, j[i].action ]);
			}
		});
		$.post("getTotalexamname_dataCount?" + key + "=" + value, {
			name_of_the_exam : name_of_the_exam,
			system_id : system_id,
			degree_id : degree_id,
			Search : Search,
			status : status

		
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
	function GetSystemFromDegree() {

		var system_id = $("select#system_id").val();

		//alert(system_id);
		
		$
				.post(
						"getSystemlistFromDegree?" + key + "=" + value,
						{
							system_id : system_id
						},
						function(j) {
							
							
							//alert(j);
							
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#degree_id").html(options);
						});
	}
	

	
	function editData(id,name_of_the_exam,system_id,degree_id) {
		
		document.getElementById('lbladd').innerHTML = "Update ";
		$("input#name_of_the_exam").val(name_of_the_exam);
		$("select#system_id").val(system_id);
		GetSystemFromDegree();
		$("select#degree_id").val(degree_id);
		//$("select#status").val(status);
		document.getElementById('id').value=id;
	}
	

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
// 	function Search() {
// 		$("#name_of_the_exam").val($('#name_of_the_exam').val());
// 		$("#system_id").val($('#system_id').val());
// 		$("#degree_id").val($('#degree_id').val());
// 		$("#status1").val($('#status').val());
// 		document.getElementById('searchForm').submit();
// 	}

	function Validation() {
		if ($("#name_of_the_exam").val().trim() == "") {
			alert("Please Enter Name Of Exam.");
			$("input#name_of_the_exam").focus();
			return false;
		}
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
		
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status.");
			$("select#status").focus();
			return false;
		}
		return true;
	}
	

</Script>
