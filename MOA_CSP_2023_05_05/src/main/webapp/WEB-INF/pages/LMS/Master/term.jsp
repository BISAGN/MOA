<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->

<script type="text/javascript" src="js/watermark/common.js"></script>

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
						<h2>Term Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">Term
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
					<form:form name="Term" id="Term" action="TermAction" method="post"
						commandName="TermCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Term Master</h6>
								<div class="row">
									<div class="col-12 col-lg-4 col-md-4 col-sm-12">
										<div class="input-style-1">
											<label>Term<span class="mandatory">*</span></label> <input
												type="text" id="term" name="term" autocomplete="off"
												maxlength="1" placeholder="Term" />
										</div>
										<!-- end input -->
									</div>

									<!-- Start profession -->

									<div class="col-12 col-lg-4 col-md-4 col-sm-12">
										<div class="input-style-1">
											<label>Profession Name<span class="mandatory">*</span></label>
											<input class="autocomplete UpperClassName txt-transupp"
												type="text" id="prof_name" name="prof_name"
												autocomplete="off" placeholder="Profession Name" />
										</div>
										<!-- end profession -->
									</div>


									<div class="col-12 col-lg-4 col-md-4 col-sm-12">
										<div class="select-style-1">
											<label>Status <span class="mandatory">*</span></label>
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
									</div>
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a type="button" id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><input type="submit" id="btn-save" id="btn-save"
												class="main-btn info-btn btn-hover btnsave" value="Save"></li>
											<li><a href="Term_url"
												class="main-btn dark-btn n btn-hover btnreset">Reset</a></li>
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
										<div class="table-wrapper table-responsive custom-datatable-p"
											id="container-table">
											<table class="table" id="search_term">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th id="${item.id}"><h6>Term</h6></th>
														<th><h6>Profession name</h6></th>
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


<c:url value="getSearch_Term_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Term_name1">
	<input type="hidden" name="Term_name1" id="Term_name1" />
</form:form>

<c:url value="Edit_termUrl" var="Edit_termUrl" />
<form:form action="${Edit_termUrl}" method="get" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="deleteterm_Url" var="deleteterm_Url" />
<form:form action="${deleteterm_Url}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Systemreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_term');
		table = dataTable('search_term');
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

	function data(search_term) {

		jsondata = [];
		var table = $('#' + search_term).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var term = $("#term").val();
		var prof_name = $("#prof_name").val();
		var status = $("#status").val();

		$.post("getFilterterm_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			term : term,
			prof_name : prof_name,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].term, j[i].prof_name, j[i].action ]);
			}
		});
		$.post("getTotalterm_dataCount?" + key + "=" + value, {
			Search : Search,
			term : term,
			prof_name : prof_name 
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

		if ($("#term").val().trim() == "") {
			alert("Please Enter Term.");
			$("input#term").focus();
			return false;
		}
		if ($("#prof_name").val().trim() == "") {
			alert("Please Enter Profession Name.");
			$("input#prof_name").focus();
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
	
	
	
document.addEventListener('DOMContentLoaded', function () {	
	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	
	document.getElementById('term').onkeypress = function() {
		return isNumberKey0to9(event);
	};
		
		});
	
function setTimeLoadForTable(){
		
			
		document.querySelectorAll('.addEdit').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apdAGE'+val).value;
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