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
						<h2>Religion Master</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a
									href="commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">Religion Master</li>
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
					<form:form name="religion_mstr" id="religion_mstr" action="religionAction"
						method="post" class="form-horizontal" commandName="religionCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Religion Master</h6>
							<div class="row">
								<div class="col-12 col-lg-4 col-md-4 col-sm-12">
									<div class="input-style-2">
										<label>Religion<Mmapspan class="mandatory">*</span></label> <input
										class="form-control autocomplete UpperClassName txt-transupp"	type="text" id="religion" name="religion"
											autocomplete="off" maxlength="100" placeholder="Religion"/>
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
											onclick="return Validation();"
											class="main-btn info-btn btn-hover" value="Save">
										<li><a href="Religion_Url"
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
					<table class="table" id="search_Religion">
						<thead>
							<tr>
								<th><h6>Ser no</h6></th>
								<th id="${item.id}"><h6>Religion</h6></th>
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

<c:url value="Edit_religionUrl" var="Edit_religionUrl" />
<form:form action="${Edit_religionUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="delete_religionUrl" var="delete_religionUrl" />
<form:form action="${delete_religionUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_Religion');
		table = dataTable('search_Religion');
		$('#btn-reload').on('click', function() {
			debugger;
			
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
// 		if ('${msg}' != "") {
// 			alert('${msg}');
// 		}
	});

	function data(search_Religion) {
		jsondata = [];
		var table = $('#' + search_Religion).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var religion = $("#religion").val();
		var status = $("#status").val();

		$.post("getFilterReligion_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			religion : religion,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].religion, j[i].action ]);
			}
		});
		$.post("getTotalReligion_dataCount?" + key + "=" + value, {
			Search : Search,
			religion : religion
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

		if ($("#religion").val().trim() == "") {
			alert("Please Enter Religion .");
			$("#religion").focus();
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

			document.getElementById('reg_type').onkeypress = function() {
				return onlyAlphabetsStringSpace(this, event);
			};
		  });
	

	function setTimeLoadForTable(){
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		}; 
		document.getElementById('religion').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		}; 
		/* document.getElementById('hostel_address').onkeypress = function() {
			return Validation();
		}; */
		/* document.getElementById('no_of_rooms').onkeypress = function() {
			return Validation();
		}; */
		/* document.getElementById('institute_id').onkeypress = function() {
			return Validation();
		}; */
		document.querySelectorAll('.ADDRelion_Master').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apIdAGE'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid);
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
	

// 	  document.addEventListener('DOMContentLoaded', function() {
// 		document.getElementById('btn-save').onclick = function() {
// 			return Validation();
// 		};
// 		document.getElementById('religion').onkeypress = function() {
// 			return onlyAlphabetsStringSpace(this, event);
// 		};
// 	});
</Script>
