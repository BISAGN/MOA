<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
						<h2><span id="lbladd"></span> Department Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Department
									Master</li>
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
					<form:form name="System" id="System" action="ClgDeptAvailabilityAction"
						method="post" modelAttribute="ClgDeptAvailabilityCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Department Master</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-2">
											<label>Department<span class="mandatory">*</span></label> <input
												type="text" id="department" name="department"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="100" placeholder="Department" />
										</div>
										<!-- end input -->
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
										<div class="input-style-2 mt-3">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
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
												class="main-btn info-btn btn-hover btnsave" type="submit"
												value="Save" /></li>
											<li><a href="get_Reg_Clg_Dept_Availability_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
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
						<table class="table" id="search_system">
							<thead>
								<tr>
									<th><h6>Sr No</h6></th>
									<th id="${item.id}"><h6>Department</h6></th>
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





<c:url value="clg_dept_delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>



<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_system');
		table = dataTable('search_system');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
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
		
		document.getElementById('department').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		
	});
	
	function setTimeLoadForTable(){
		
		document.querySelectorAll('.ADDClgDeptAvailability').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apId'+val).value;
				var hdepartment = document.getElementById('apDepartment'+val).value;
				var hstatus = document.getElementById('apStatus'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,hdepartment,hstatus);
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
	
	function data(search_system) {
		
		jsondata = [];
		var table = $('#' + search_system).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var department = $("#department").val();
		var status = $("#status").val();

		$.post("getFilterClgDeptAvailability_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			department : department,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].department,j[i].action ]);
			}
		});
		$.post("getTotalClgDeptAvailability_dataCount?" + key + "=" + value, {
			Search : Search,
			department : department
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	function editData(id,department,status) {
		document.getElementById('lbladd').innerHTML = "Update ";
		document.getElementById('btn-save').value = "Update ";
		$("input#department").val(department);
		$("select#status").val(status);
		$('#status').trigger('change');
		document.getElementById('id').value=id;
	}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}


	function Validation() {

		if ($("#department").val().trim() == "") {
			alert("Please Enter Department.");
			$("input#department").focus();
			return false;
		}
		if ($("select#status").val() == "0") {
			alert("Please Select Status.");
			$("select#status").focus();
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
