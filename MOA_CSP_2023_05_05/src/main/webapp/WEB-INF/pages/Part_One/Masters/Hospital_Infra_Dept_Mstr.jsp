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
						<h2><span id="lbladd"></span> Hospital Department Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Hospital Department Master</li>
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
					<form:form name="HospitalInfraDept" id="HospitalInfraDept" action="Hospital_Infra_Dept_Action"
						method="post" modelAttribute="Hospital_Infra_Dept_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25"> Hospital Department Master</h6>
								<div class="row">									
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-2">
											<label>Hospital Department<span class="mandatory">*</span></label> <input
												type="text" id="hospital_department_name" name="hospital_department_name"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="100" placeholder="Hospital Department Name" />

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
									
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="hos_department_id">Hospital Infrastructure<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="hos_department_id" id="hos_department_id"
													class="singleselect form-control form-control-lg">
														<option value="0" >--Select--</option>
														<option value="1" >Hospital Administration</option>
														<option value="2" >Hospital OPD</option>
														<option value="3" >Hospital IPD</option>
														<option value="4" >Rehabiliatation Unit</option>
														<option value="5" >Operation Theatre</option>
														<option value="6" >Clinical Laboratory Unit</option>
														<option value="7" >Radiology or Sonography</option>
														<option value="8" >Hospital Kitchen and Canteen Block</option>
														<option value="9" >Stores Details</option>
														<option value="10" >Other Infrastructure Details</option>
												</select>
											</div>
										</div>
										<!-- end input -->
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
												class="main-btn info-btn btn-hover btn-save" type="submit"
												value="Save" /></li>
											<li><a href="get_Hospital_Infra_Dept_Mstr_Url"
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
						<table class="table" id="search_HospitalInfraDept">
							<thead>
								<tr>
									<th><h6>Sr No</h6></th>
									<th id="${item.id}"><h6>Hospital Department</h6></th>
									<th id="${item.id}"><h6>Hospital Infrastructure</h6></th>
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

<c:url value="hospital_infra_dept_delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_HospitalInfraDept');
		table = dataTable('search_HospitalInfraDept');
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
		
		
// 		document.getElementById('roleid').onkeypress = function() {
// 			return onlyAlphabetsStringSpace(this, event);
// 		};
		
	});
	
	function setTimeLoadForTable(){
	
		
		document.querySelectorAll('.ADDHospitalInfraDept').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apId'+val).value;
				var hospital_department_name = document.getElementById('aphospital_department_name'+val).value;
				var hospital_department_id = document.getElementById('aphospital_department_id'+val).value;
				var hstatus = document.getElementById('apStatus'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,hospital_department_name,hospital_department_id,hstatus);
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
	
	function data(search_HospitalInfraDept) {
		
		jsondata = [];
		var table = $('#' + search_HospitalInfraDept).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var hospital_department_name = $("#hospital_department_name").val().trim();
		var hos_department_id = $("#hos_department_id").val();
		var status = $("#status").val();

		 $.post("getFilterHospitalInfraDept_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			hospital_department_name : hospital_department_name,
			hos_department_id : hos_department_id,			
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {

				var text = $("#hos_department_id option[value="+j[i].hos_department_id+"]").text();
				jsondata.push([ j[i].ser, j[i].hospital_department_name,text,j[i].action ]);
			}
		});
		$.post("getTotalHospitalInfraDept_dataCount?" + key + "=" + value, {
			Search : Search,
			hospital_department_name : hospital_department_name,
			hos_department_id : hos_department_id,			
			status:status
		}, function(j) {

			FilteredRecords = j;

		}); 
		setTimeout(setTimeLoadForTable, 1000);
	}

	function editData(id,hospital_department_name,hospital_department_id,status) {
		document.getElementById('lbladd').innerHTML = "Update ";
		document.getElementById('btn-save').value = "Update ";
		$("#hospital_department_name").val(hospital_department_name);
		$("select#hos_department_id").val(hospital_department_id);
		$('#hos_department_id').trigger('change');
		$("select#status").val(status);
		$('#status').trigger('change');
		document.getElementById('id').value=id;
	}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}


	function Validation() {

		 if ($("#hospital_department_name").val().trim() == "") {
			alert("Please Enter Hospital Department.");
			$("input#roleid").focus();
			return false;
		}
		if ($("select#status").val() == "") {
			alert("Please Select Status.");
			$("select#status").focus();
			return false;
		}
		if ($("select#hos_department_id").val() == 0) {
			alert("Please Select Hospital Department.");
			$("select#hos_department_id").focus();
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
