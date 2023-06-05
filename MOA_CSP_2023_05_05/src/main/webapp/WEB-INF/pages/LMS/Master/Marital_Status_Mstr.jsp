<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- <script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script type="text/javascript" src="js\watermark\common.js"></script>

<section class="dashboard-page">
<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Marital Status Master</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a
									href="commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">Marital Status Master</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12">
					<!-- input style start -->
					<form:form name="marital_status_mstr" id="marital_status_mstr" action="marital_statusAction"
						method="post" class="form-horizontal" commandName="marital_statusCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Marital Status Master</h6>
							<div class="row">
								<div class="col-12 col-lg-4 col-md-4 col-sm-12">
									<div class="input-style-2">
										<label>Marital Status<span class="mandatory">*</span></label> <input
											type="text" id="marital_status" name="marital_status" class="form-control autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="100" placeholder="Marital Status"/>
									</div>
								</div>

								<div class="col-12 col-lg-4 col-md-4 col-sm-12">
									<div class="select-style-1">
										<label>Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status" class="singleselect form-control form-control-lg">
												<c:forEach var="item" items="${ActiveInActiveList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>

											</select>
										</div>
									</div>
								</div>
									<ul class="buttons-group mainbtn">
										<li><a type="button" id="btn-reload"
											class="main-btn secondary-btn btn-hover btn-iconic-icon"><i
												class="lni lni-search-alt"></i>Search</a></li>
										<li><input type="submit" id="btn-save" id="btn-save"
											
											class="main-btn info-btn btn-hover" value="Save">
										<li><a href="Marital_Status_Url"
											class="main-btn dark-btn n btn-hover">Reset</a></li>
									</ul>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	<div class="row">
		<div class="col-12">
			<div class="card-style mb-30">
				<div class="table-wrapper table-responsive custom-datatable-p"
					id="container-table">
					<table class="table" id="search_marital_status">
						<thead>
							<tr>
								<th><h6>Ser no</h6></th>
								<th id="${item.id}"><h6>Marital Status</h6></th>
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

<c:url value="Edit_marital_statusUrl" var="Edit_marital_statusUrl" />
<form:form action="${Edit_marital_statusUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="delete_marital_statusUrl" var="delete_marital_statusUrl" />
<form:form action="${delete_marital_statusUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_marital_status');
		table = dataTable('search_marital_status');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
// 		if ('${msg}' != "") {
// 			alert('${msg}');
// 		}
	});

	function data(search_marital_status) {
		jsondata = [];
		var table = $('#' + search_marital_status).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var marital_status = $("#marital_status").val();
		var status = $("#status").val();

		$.post("getFiltermarital_status_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			marital_status : marital_status,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].marital_status, j[i].action ]);
			}
		});
		$.post("getTotalmarital_status_dataCount?" + key + "=" + value, {
			Search : Search,
			marital_status : marital_status
		}, function(j) {

			FilteredRecords = j;
		});
		
		setTimeout(setTimeLoadForTable, 1000);
	}

	function editData(id) {

		$("#id2").val(id);
		document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}

	function Validation() {

		if ($("#marital_status").val().trim() == "") {
			alert("Please Enter Marital Status.");
			$("#marital_status").focus();
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

	  document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		document.getElementById('marital_status').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
	  });
		
		
		function setTimeLoadForTable(){
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		
// 		document.getElementById('term_id').onkeypress = function() {
// 			return onlyAlphabetsStringSpace(this, event);
// 		};
		document.getElementById('marital_status').onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this);		
		};


		
		document.querySelectorAll('.Editmarital_status').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
			
				var editid = document.getElementById('apIdAGE'+val).value;
				if (confirm('Are You Sure You Want to Edit Detail?')) {
					editData(editid);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.deletemarital_status').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var sdid = document.getElementById('deleteID'+val).value;
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(sdid);
				} else {
					return false;
				}
			})
		});
		
	}
	

</Script>
