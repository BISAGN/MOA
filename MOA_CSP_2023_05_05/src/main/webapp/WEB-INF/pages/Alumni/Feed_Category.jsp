<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
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
						<h2>Feed Category</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Feed Category
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
					<form:form name="feed_id" id="feed_id" action="feed_categoryAction"
						method="post" class="form-horizontal" modelAttribute="feed_categoryCMD">
						<div class="card-style mb-30">
							<span id="lbladd"></span>
							<h6 class="mb-25">Feed Category</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Feed Category<span class="mandatory">*</span></label> <input
											type="text" id="feed_category" name="feed_category"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="100" placeholder="Feed Category" />
										
									</div>
									<!-- end input -->
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
								<li><a href="Feed_Category_Url"
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
									<th id="${item.id}"><h6>Feed Category</h6></th>
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

<c:url value="getSearch_feed_category" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="feed_category">
	<input type="hidden" name="feed_category" id="feed_category" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="edit_feedcategory_Action" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="delete_FC_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Categoryreport" var="searchUrl" />
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
		
		if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}
	});
	
	function setTimeLoadForTable(){
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

		document.getElementById('feed_category').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		
	
		document.querySelectorAll('.ADDFC').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('FCId'+val).value;
				var tydeg = document.getElementById('FCmst'+val).value;
				var tydstatus = document.getElementById('FCStatus'+val).value;
					
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,tydeg,tydstatus);
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
		var feed_category = $("#feed_category").val();
		var status = $("#status").val();

		$.post("getFilterfeed_category_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			feed_category : feed_category,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].feed_category,j[i].action ]);
			}
		});
		$.post("getTotalfeed_category_dataCount?" + key + "=" + value, {
			Search : Search,
			feed_category : feed_category
		
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
// 	function editData(id) {

// 		$("#id2").val(id);
// 		document.getElementById('updateForm').submit();
// 	}

	function editData(id,feed_category,status) {
		document.getElementById('lbladd').innerHTML = "Update ";
		$("input#feed_category").val(feed_category);
		$("select#status").val(status);
		document.getElementById('id').value=id;
		
	}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function Search() {
		$("#feed_category1").val($('#feed_category').val());
		$("#status1").val($('#status').val());
		document.getElementById('searchForm').submit();
	}

	function Validation() {

		if ($("#feed_category").val().trim() == "") {
			alert("Please Enter Feed Category");
			$("input#feed_category").focus();
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
