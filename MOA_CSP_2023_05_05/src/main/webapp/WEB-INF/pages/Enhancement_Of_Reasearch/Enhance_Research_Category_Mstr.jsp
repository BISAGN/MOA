<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
							<span id="lbladd"></span>Category Master
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Category Master</li>
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
					<form:form name="Enhance_Research_Category_Mstr" id="Enhance_Research_Category_Mstr"
						action="Enhance_Research_Category_MstrAction" method='POST'
						modelAttribute="Enhance_Research_Category_MstrCMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Category Master</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Category<span class="mandatory">*</span>
											</label> <input id="category_name"
												name="category_name" maxlength="50"
												autocomplete="off"
												placeholder="Please Enter Title">
										</div>
										<!-- end select -->
									</div>

                                   <div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Status<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="status" id="status">
<!-- 													<option value="0">--Select--</option> -->
														<c:forEach var="item" items="${ActiveInActiveList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
											<div class="input-style-1">
												<input type="hidden" id="id" name="id" class="mt-3"
													value="0" autocomplete="off">
											</div>
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
										<li  id="btn-reload"><a
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><input id="btn-save"
												class="main-btn info-btn btn-hover btnsave" type="submit"
												value="Save" /></li>
											<li><a href="Enhance_Research_Category_Mstr_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a>
											</li>
										</ul>
									</div>
								</div>
							</div>

						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
			
				<section class="single-detail-block">
				<div class="row">
					<div class="col-12 col-sm-12 col-md-12 col-lg-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-datatable-p">
								<table class="table" id="getSearch_Enhance_Research_Category_Mstr">
									<thead>
										<tr>
											<th><h6>Sr No</h6></th>
											<th><h6>Category Name</h6></th>
											<th class="action"><h6>Action</h6></th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</section>
		
		</div>
		
	
	</div>
</section>

<c:url value="delete_Enhance_Research_Category_MstrUrl" var="delete_Enhance_Research_Category_MstrUrl" />
<form:form action="${delete_Enhance_Research_Category_MstrUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>


<c:url value="Edit_Enhance_Research_Category_MstrUrl" var="Edit_Enhance_Research_Category_MstrUrl" />
<form:form action="${Edit_Enhance_Research_Category_MstrUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>
<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(
			function() {

				if (window.location.href.includes("msg")) {
					var url = window.location.href.split("?msg")[0];
					window.location = url;
				}
				
				mockjax1('getSearch_Enhance_Research_Category_Mstr');
				table = dataTable('getSearch_Enhance_Research_Category_Mstr');
				$('#btn-reload').on('click', function() {
					table.ajax.reload();
				});
			});

	function data(getSearch_Enhance_Research_Category_Mstr) {
		jsondata = [];
		var table = $('#' + getSearch_Enhance_Research_Category_Mstr).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
		var orderType = order[0][1];
		
		var category_name = $("#category_name").val();
		var status = $("#status").val();
		

		$.post("getFilterSearch_Enhance_Research_Category_Mstr_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength: pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			
			category_name:category_name,
			status:status
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				
				jsondata.push([ j[i].ser,j[i].category_name,j[i].action ]);
			}
		});
		$.post("getTotalSearch_Enhance_Research_Category_Mstr_Count?" + key + "=" + value, {
			 Search : Search,
			category_name:category_name,
			status:status
			
		}, function(j) {
			FilteredRecords = j;
			});
		
		setTimeout(setTimeLoadForTable, 1000);
	}
	function setTimeLoadForTable(){
// 		document.querySelectorAll('.VIEWdetails').forEach((items, index) => {
// 			items.addEventListener('click', event => {
// 				debugger;
// 				var val=parseInt(index)+1;
// 				var hid = document.getElementById('viewId'+val).value;
// 				if (confirm('Are You Sure You Want to Show Detail ?')) {
// 					Pop_Up_Stu_Reg(hid);
// 				} else {
// 					return false;
// 				}
// 			})
// 		});


		document.querySelectorAll('.DeleteEnhance_Research_Category_Mstr').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var gdid = document.getElementById('DLT_ENH_RES_CATE_MSTR_ID'+val).value;
				
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(gdid);
				} else {
					return false;
				}
			})
		});
		document.querySelectorAll('.Edit_Enhance_Research_Category_Mstr').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var gid = document.getElementById('EDIT_ENH_RES_CATE_MSTR_ID'+val).value;
 				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(gid);
				} else {
					return false;
				}
			})
		});
	}
	
	document.addEventListener('DOMContentLoaded',function() {
						document.getElementById('btn-save').onclick = function() {
							return Validation();
						};
	});

	
	
	function Validation() {
		$.ajaxSetup({
		    async: false
		});
		if ($("input#category_name").val().trim() == "") {
			alert("Please Enter Category Name");
			$("input#category_name").focus();
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
	
	function editData(id) {

		$("#id2").val(id);
		document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}

</script>
