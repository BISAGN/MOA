<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- <script type="text/javascript" -->
<!-- 	src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
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
<script type="text/javascript" src="js\watermark\common.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<span id="lbladd"></span>
						<h2>Feedback Subcategory Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Feedback
									Subcategory Master</li>
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
					<form:form name="SubCategoryform" id="SubCategoryform"
						action="SubCategoryAction" method="post" class="form-horizontal"
						modelAttribute="SubCategoryCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Feedback Subcategory Master</h6>
							<div class="row">

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Feedback Category<span
											class="mandatory">*</span></label>
										<div class="select-position">
											<select name="category" id="category" class="singleselect form-control form-control-lg">
												<option value="0" selected="selected" name="select">--Select--</option>

												<c:forEach var="item" items="${getfeedbackcat}"
													varStatus="num">
													<option value="${item.id}" name="${item.category}">${item.category}</option>

												</c:forEach>
											</select>
										</div>
									</div>

								</div>


								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Subcategory<span class="mandatory">*</span></label> <input
											type="text" id="subcategory" name="subcategory"
											class="autocomplete  txt-transupp" autocomplete="off"
											maxlength="100" placeholder="Subcategory" /> <input
											type="hidden" id="id" name="id" value="0" autocomplete="off" />
									</div>
									<!-- end input -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Feedback Detail<span class="mandatory">*</span></label>
										<input type="text" id="feedback_detail" name="feedback_detail"
											class="autocomplete  txt-transupp" autocomplete="off"
											maxlength="100" placeholder="Feedback Detail" /> <input
											type="hidden" id="id" name="id" value="0" autocomplete="off" />
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status" class="singleselect form-control form-control-lg">
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

							<ul class="buttons-group mainbtn">

								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" value="Save" />
								</li>
								<li><a href="Feedback_SubCategory_Mstr_Url"
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
						<table class="table" id="search_category">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th id="${item.id}"><h6>Category</h6></th>
									<th id="${item.id}"><h6>Subcategory</h6></th>
									<th id="${item.id}"><h6>Feedback Detail</h6></th>
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

<c:url value="getSearchCategory_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Category_name1">
	<input type="hidden" name="Category_name1" id="Category_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_Feedback_SubCategory_Mstr_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="Feedback_SubCategory_Mstr_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Categoryreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_category');
		table = dataTable('search_category');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
	});
	
document.addEventListener('DOMContentLoaded', function () {	
	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};

	document.getElementById('category').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	document.getElementById('feedback_detail').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
		
		});
	
	function setTimeLoadForTable(){
		
		
		document.querySelectorAll('.ADDCategory').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apIdAGE'+val).value;
				var hcate = document.getElementById('apcateAGE'+val).value;
				var hfd = document.getElementById('apfdAGE'+val).value;
				var hsub = document.getElementById('apsubcateAGE'+val).value;
				var hstatus = document.getElementById('apstatusAGE'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,hcate,hsub,hfd,hstatus);
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
	
	function data(search_category) {
		
		jsondata = [];
		var table = $('#' + search_category).DataTable();
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
		var subcategory = $("#subcategory").val();
		var feedback_detail = $("#feedback_detail").val();
		var status = $("#status").val();

		$.post("getFilterFeedBack_SubCategory_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			category : category,
			subcategory:subcategory,
			feedback_detail : feedback_detail,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].category, j[i].subcategory, j[i].feedback_detail,j[i].action ]);
			}
		});
		$.post("getTotalFeedback_SubCategory_dataCount?" + key + "=" + value, {
			Search : Search,
			category : category,
			subcategory:subcategory,
			feedback_detail : feedback_detail,
			status : status
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
	function editData(id,category,subcategory,feedback_detail,status) {
		document.getElementById('lbladd').innerHTML = "Update ";
		$("select#category").val(category);
		$('#category').trigger('change');
		$("input#subcategory").val(subcategory);

		$("input#feedback_detail").val(feedback_detail);
		$("select#status").val(status);
		$('#status').trigger('change');
	
		document.getElementById('id').value=id;
	}
	

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function Search() {
		$("#category").val($('#category').val());
		$("#feedback_detail").val($('#feedback_detail').val());
		$("#status1").val($('#status').val());
		document.getElementById('searchForm').submit();
	}

	function Validation() {

		
		if ($("#category").val() == "0") {
			alert("Please Select Feedback Category.");
			$("input#category").focus();
			return false;
		}
		if ($("#subcategory").val() == "") {
			alert("Please Enter Feedback Subcategory.");
			$("input#category").focus();
			return false;
		}
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status.");
			$("select#status").focus();
			return false;
		}
		
		if ($("#feedback_detail").val() == "") {
			alert("Please Enter Feedback Detail.");
			$("input#feedback_detail").focus();
			return false;
		}
		return true;
	}
	

</Script>
