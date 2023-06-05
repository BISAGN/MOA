<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- <link rel="stylesheet" href="js/InfiniteScroll_old/css/site.css"> -->
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
						<h2>Link System & Degree</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">Link
									System & Degree</li>
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
					<form:form action="System_Degree_Mapping_Inst_action" method="POST"
						modelAttribute="System_Degree_Mapping_Inst_cmd">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Link System & Degree</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label class=" form-control-label" for="username">System
												<span class="mandatory">*</span>
											</label> <input type="hidden" value="${system[0][1]}" id="system_hid"
												name="system_hid"
												class="form-control form-control-lg form-control-a effect-9"
												value="0"> <input type="text"
												value="${system[0][0]}" id="system_id" name="system_id"
												class="form-control form-control-lg form-control-a effect-9"
												readonly="readonly">

											<!-- 										<div class="select-position"> -->
											<!-- 											<select name="system_id" id="system_id" class="form-control"> -->
											<!-- 												onchange="getAprrovedDegree();" -->
											<!-- 												<option value="0">--Select--</option> -->
											<%-- 												<c:forEach var="item" items="${s_name}" varStatus="num"> --%>
											<%-- 													<option value="${item.id}" name="${item.system_name}">${item.system_name}</option> --%>
											<%-- 												</c:forEach> --%>
											<!-- 											</select> <input type="hidden" id="id" name="id" value="0" -->
											<!-- 												class="form-control" autocomplete="off" /> <input -->
											<!-- 												type="hidden" id="institute_id" name="institute_id" -->
											<!-- 												value="0" class="form-control" autocomplete="off" /> -->

											<!-- 										</div> -->
										</div>

										<!-- end select -->
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label class=" form-control-label">Degree<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="degree_id" id="degree_id"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getDegreeList}"
														varStatus="num">
														<option value="${item.id}" name="${item.degree_name}">${item.degree_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label class=" form-control-label">Status<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="status" id="status"
													class="singleselect form-control form-control-lg">
													<c:forEach var="item" items="${ActiveInActiveList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="input-style-1 mt-3">
											<input type="hidden" id="id" name="id" value="0"
												class="form-control" autocomplete="off" />
										</div>
										<!-- end select -->
									</div>
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li><input id="btn-save"
												class="main-btn info-btn btn-hover btnsave" value="Save"
												type="submit"> <!-- onclick="return Validation();"  --></li>
											<li><a href="System_Degree_Mapping_InstUrl"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a>
											</li>
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
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-datatable-p">
								<table class="table" id="search_system_degree_mapping_inst">
									<thead>
										<tr>
											<th><h6>Sr no</h6></th>
											<!-- 								<th id="2"><h6>System</h6></th> -->
											<th id="3"><h6>Degree</h6></th>
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
			</section>
		</div>
	</div>
</section>

<c:url value="Edit_System_Degree_Mapping_Inst_Url"
	var="Edit_System_Degree_Mapping_Inst_Url" />
<form:form action="${Edit_System_Degree_Mapping_Inst_Url}" method="post"
	id="updateForm" name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="delete_System_Degree_Mapping_Inst_Url"
	var="delete_System_Degree_Mapping_Inst_Url" />
<form:form action="${delete_System_Degree_Mapping_Inst_Url}"
	method="post" id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>


<script nonce="${cspNonce}" type="text/javascript">
	
	$(document).ready(function() {
		$("#institute_id").val('${institute_id}');
		mockjax1('search_system_degree_mapping_inst');
		table = dataTable('search_system_degree_mapping_inst');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
 
		getAprrovedDegree('${system[0][1]}');
		
		if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}
		
	});

// 	csp start
	
	function setTimeLoadForTable(){
		
// 		document.getElementById('system_id').onchange = function() {
// 			getAprrovedDegree();
// 		};
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		
		document.querySelectorAll('.ADD_Degree').forEach((items, index) => {
			items.addEventListener('click', event => {
				debugger;
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('Id_degree'+val).value;
				var degreeName = document.getElementById('degreeName'+val).value;
				var status = document.getElementById('status'+val).value;
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					EditData(hid,degreeName,status);
				} else {
					return false;
				}
			})
		});
		document.querySelectorAll('.Delete_degree').forEach((items, index) => {
			items.addEventListener('click', event => {
				debugger;
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('deleteID'+val).value;
// 				var hname = document.getElementById('apItmeNumber'+val).value;
// 				var hpnum = document.getElementById('apBoqId'+val).value;
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(hid);
				} else {
					return false;
				}
			})
		});

	}
	
// 	csp end
	
	function data(search_system_degree_mapping_inst) {
		jsondata = [];
		var table = $('#' + search_system_degree_mapping_inst).DataTable();
		var info = table.page.info();
 		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var degree_id = $("#degree_id").val();
// 		var system_id = $("#system_id").val();
		var status = $("#status").val();
		var institute_id = $("#institute_id").val();
		
		$.post("getFilter_system_degree_mapping_instdata?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
// 			system_id:system_id,
			degree_id:degree_id,
			status:status,
			institute_id:institute_id
			
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].degree_name,j[i].action ]);
			}
		});
		$.post("getTotal_system_degree_mapping_instdataCount?" + key + "=" + value, {
// 			system_id:system_id,
			degree_id:degree_id,
			institute_id:institute_id
		}, function(j) {
			FilteredRecords = j;
			});
		setTimeout(setTimeLoadForTable, 1000);
	}


	function EditData(id) {

		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}

	
	function Validation() {
		
// 		if ($("#system_id").val().trim() == "0") {
// 			alert("Please Select System");
// 			$("input#system_id").focus();
// 			return false;
// 		}
		
		if ($("select#degree_id").val().trim() == "0") {
			alert("Please Select Degree");
			$("select#degree_id").focus();
			return false;
		}

		if ($("select#status").val() == "2") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}

		return true;
	}

	function getAprrovedDegree(id) {
		
// 		var selval = $("#system_hid").val();
		var selval = id;
		$
				.post(
						"getdegrebysysidlist?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {
							if(j.length>0){
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#degree_id").html(options);
							}
						});
	}
	
	
</script>
