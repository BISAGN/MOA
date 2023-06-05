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
						<h2>Category Master</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a
									href="commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">Category Master</li>
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
					<form:form name="category_mstr" id="category_mstr" action="categoryAction"
						method="post" class="form-horizontal" commandName="categoryCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Category Master</h6>
							<div class="row">
								<div class="col-12 col-lg-4 col-md-4 col-sm-12">
									<div class="input-style-2">
										<label>Category<span class="mandatory">*</span></label> <input
											type="text" id="category" name="category" class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="100" placeholder="Category"/>
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
										<li><a href="Category_Url"
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
					<table class="table" id="search_Category">
						<thead>
							<tr>
								<th><h6>Ser no</h6></th>
								<th id="${item.id}"><h6>Category</h6></th>
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

<c:url value="Edit_categoryUrl" var="Edit_categoryUrl" />
<form:form action="${Edit_categoryUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="delete_categoryUrl" var="delete_categoryUrl" />
<form:form action="${delete_categoryUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_Category');
		table = dataTable('search_Category');
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

	function data(search_Category) {
		jsondata = [];
		var table = $('#' + search_Category).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var category = $("#category").val();
		var status = $("#status").val();

		$.post("getFilterCategory_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			category : category,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].category, j[i].action ]);
			}
		});
		$.post("getTotalCategory_dataCount?" + key + "=" + value, {
			Search : Search,
			category : category
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

		if ($("#category").val().trim() == "") {
			alert("Please Enter Category.");
			$("input#category").focus();
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

// 	  document.addEventListener('DOMContentLoaded', function() {
// 		document.getElementById('btn-save').onclick = function() {
// 			return Validation();
// 		};
// 		document.getElementById('category').onkeypress = function() {
// 			return onlyAlphabetsStringSpace(this, event);
// 		};
// 	});
	  
	document.addEventListener('DOMContentLoaded', function () {	
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		}; 

		document.getElementById('category').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		}; 
//	 	document.getElementById('Attachment').onblur = function() {
//	 		return onlyAlphabetsStringSpace(this, event);
//	 	}; 
		/* document.getElementById('hostel_address').onkeypress = function() {
			return Validation();
		}; */
		/* document.getElementById('no_of_rooms').onkeypress = function() {
			return Validation();
		}; */
		/* document.getElementById('institute_id').onkeypress = function() {
			return Validation();
		}; */	
	
	});  
	  
	  function setTimeLoadForTable(){
			
		
			document.querySelectorAll('.ADDCategory_Master').forEach((items, index) => {
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
	  
</Script>
