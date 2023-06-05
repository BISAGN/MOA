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
						<h2>Type of Degree Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">Type
									of Degree Master</li>
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
					<form:form action="type_of_degree_action" method="POST"
						modelAttribute="type_of_degree_cmd">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Type of Degree Master</h6>
								<div class="row">
									<div class="col-12 col-lg-4 col-md-4 col-sm-12">
										<div class="input-style-1">
											<label>Type of Degree<span class="mandatory">*</span></label>
											<input id="type_of_degree" name="type_of_degree"
												placeholder="Type of Degree" maxlength="100" /> <input
												type="hidden" id="id" name="id" value="0"
												class="form-control" placeholder="Type of Degree"
												autocomplete="off" />
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-lg-4 col-md-4 col-sm-12">
										<div class="select-style-1">
											<label>Status<span class="mandatory">*</span></label>
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
											<li><input type="submit" id="btn-save" value="Save"
												class="main-btn info-btn btn-hover btnsave">
											<li><a href="type_of_degree_url"
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
											<table class="table" id="search_Type_of_Degree">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th id="${item.id}"><h6>Type of degree</h6></th>
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

<c:url value="Edit_type_of_degreeurl" var="Edit_type_of_degreeurl" />
<form:form action="${Edit_type_of_degreeurl}" method="post"
	id="updateForm" name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="delete_type_of_degreeurl" var="delete_type_of_degreeurl" />
<form:form action="${delete_type_of_degreeurl}" method="post"
	id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>


<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {

	mockjax1('search_Type_of_Degree');
	table = dataTable('search_Type_of_Degree');
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

	document.getElementById('type_of_degree').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	
	
	});

function setTimeLoadForTable(){
	

	document.querySelectorAll('.ADDTyDeg').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var tydid = document.getElementById('TyDegId'+val).value;
			var tydeg = document.getElementById('TyDeg'+val).value;
			var tydstatus = document.getElementById('TyDegStatus'+val).value;
				
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				editData(tydid,tydeg,tydstatus);
			} else {
				return false;
			}
		})
	});
	
	document.querySelectorAll('.DeleteTyDeg').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var tydid = document.getElementById('DETyDegId'+val).value;
			
			if (confirm('Are You Sure You Want to Delete Detail ?')) {
				deleteData(tydid);
			} else {
				return false;
			}
		})
	});
	
}

function data(search_Type_of_Degree) {
	
	jsondata = [];
	var table = $('#' + search_Type_of_Degree).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
	var orderType = order[0][1];
	
	var type_of_degree = $("#type_of_degree").val();
	var status = $("#status").val();

	$.post("getFiltertype_of_degree_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		
		type_of_degree:type_of_degree,
		status:status,
		
	}, function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].ser,j[i].type_of_degree,j[i].action ]);
		}
	});
	$.post("getTotaltype_of_degree_dataCount?" + key + "=" + value, {
		Search : Search,
		type_of_degree:type_of_degree,

	}, function(j) {
		FilteredRecords = j;
		});
	setTimeout(setTimeLoadForTable, 1000);
}

	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function editData(id) {
		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}

	function Validation() {

		if ($("#type_of_degree").val().trim() == "") {
			alert("Please Enter Type of Degree.");
			$("input#type_of_degree").focus();
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
</script>