<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
							<span id="lbladd"></span>Link System & Degree Master
						</h2>
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
									System & Degree Master</li>
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
					<form:form action="System_Degree_Mapping_Master_action"
						method="POST" modelAttribute="System_Degree_Mapping_Master_cmd">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Link System & Degree Master</h6>
								<div class="row">

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">System<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="system_name" id="system_name"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${s_name}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="input-style-1 mt-3">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
										</div>

										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="degree_name" id="degree_name"
													class="singleselect form-control form-control-lg">
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
												autocomplete="off" />
										</div>
										<!-- end select -->
									</div>

								</div>
							</div>
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li><input id="btn-save"
												class="main-btn info-btn btn-hover btn-save" value="Save"
												type="submit" /></li>
											<li><a href="System_Degree_Mapping_Master_Url"
												class="main-btn dark-btn n btn-hover btnreset" type="button">Reset</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<!-- end card -->

						<section class="single-detail-block">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="card-style mb-30">
										<div class="table-wrapper table-responsive custom-datatable-p">
											<table class="table" id="search_system_degree_mapping">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th id="2"><h6>System</h6></th>
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
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

<c:url value="Edit_System_Degree_Mapping_Master_Url"
	var="Edit_System_Degree_Mapping_Master_Url" />
<form:form action="${Edit_System_Degree_Mapping_Master_Url}"
	method="post" id="updateForm" name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="delete_System_Degree_Mapping_Master_Url"
	var="delete_System_Degree_Mapping_Master_Url" />
<form:form action="${delete_System_Degree_Mapping_Master_Url}"
	method="post" id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		mockjax1('search_system_degree_mapping');
		table = dataTable('search_system_degree_mapping');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}
	});
	
document.addEventListener('DOMContentLoaded', function () {	
	
		
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
				
		});

function setTimeLoadForTable(){
		
		
		
		document.querySelectorAll('.ADDSys_deg').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var sydid = document.getElementById('SysDegId'+val).value;
				var sydname = document.getElementById('SysDegName'+val).value;
				var sydstatus = document.getElementById('SysDegStatus'+val).value;
 				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					EditData(sydid,sydname,sydstatus);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.DeleteSys_deg').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var syddid = document.getElementById('DESysDegId'+val).value;
				
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(syddid);
				} else {
					return false;
				}
			})
		});
		
	}
	
	function data(search_system_degree_mapping) {
		jsondata = [];
		var table = $('#' + search_system_degree_mapping).DataTable();
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
		var degree_name = $("#degree_name").val();
		var system_name = $("#system_name").val();
		var status = $("#status").val();

		$.post("getFilter_system_degree_mapping_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			system_name:system_name,
			degree_name:degree_name,
			status:status
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].sr_no,j[i].system_name, j[i].degree_name,j[i].action ]);
			}
		});
		$.post("getTotal_system_degree_mapping_dataCount?" + key + "=" + value, {
			Search : Search,
			system_name:system_name,
			degree_name:degree_name
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
		
		if ($("select#system_name").val().trim() == "0") {
			alert("Please Select System");
			$("select#system_name").focus();
			return false;
		}
		if ($("select#degree_name").val().trim() == "0") {
			alert("Please Select Degree");
			$("select#degree_name").focus();
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

</script>
