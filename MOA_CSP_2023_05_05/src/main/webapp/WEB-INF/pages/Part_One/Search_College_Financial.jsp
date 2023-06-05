<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

<section class="dashboard-page search_regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>View College Financial</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									View College Financial</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->

		<div class="form-elements-wrapper search-regulation-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="" id=""
						action="Search_College_FinancialAction?${_csrf.parameterName}=${_csrf.token}"
						method="post" modelAttribute="Search_College_Financial_CMD">
						<!-- input style start -->
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">View College Financial</h6>
								<div class="row">
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label for="fname">Fixed Deposits</label> <input type="text"
												name="fix_deposite" id="fix_deposite" class="form-control"
												placeholder="Fixed Deposits">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label for="fname">Current Account</label> <input type="text"
												name="current_acct" id="current_acct" class="form-control"
												placeholder="Current Account">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label for="fname">Saving Account</label> <input type="text"
												name="saving_acct" id="saving_acct" class="form-control"
												placeholder="Saving Account">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label for="fname">Project Cost</label> <input type="text"
												name="project_cost" id="project_cost" class="form-control"
												placeholder="Project Cost">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label for="fname">Income Statement</label> <input type="text"
												name="income_statement" id="income_statement"
												class="form-control" placeholder="Income Statement">
										</div>
									</div>
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">

										<ul class="buttons-group mainbtn">
											<li><a href="college_financial"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i
													class="lni lni-chevron-left"></i>Back</a></li>
											<li><a type="button"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												id="btn-reload" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="Search_College_Financial_url"
												class="main-btn dark-btn btn-hover btnreset" value="Reset">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->

						</div>

						<section class="single-detail-block">
							<div class="tables-wrapper">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="card-style mb-30">

											<div
												class="table-wrapper table-responsive custom-datatable-p">
												<table id="getSearch_College_Financial" class="table">
													<thead>
														<tr>

															<th><h6>Sr No</h6></th>
															<th><h6>Fixed Deposits</h6></th>
															<th><h6>Current Account</h6></th>
															<th><h6>Saving Account</h6></th>
															<th><h6>Project Cost</h6></th>
															<th><h6>Income Statement</h6></th>
															<th><h6>Action</h6></th>
														</tr>
														<!-- end table row-->
													</thead>
													<tbody class="custom-datatablepra">

													</tbody>
												</table>
												<!-- end table -->
											</div>
										</div>
										<!-- end card -->
									</div>
								</div>
							</div>
						</section>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>


<%-- <c:url value="Edit_Teacher_dtlUrl" var="editUrl" /> --%>
<%-- <form:form action="${editUrl}" method="post" id="updateForm" --%>
<%-- 	name="updateForm" modelAttribute="id2"> --%>
<!-- 	<input type="hidden" name="id2" id="id2"> -->
<%-- </form:form> --%>

<%-- <c:url value="delete_Teacher_dtl_Url" var="deleteUrl" /> --%>
<%-- <form:form action="${deleteUrl}" method="post" id="deleteForm" --%>
<%-- 	name="deleteForm" modelAttribute="id1"> --%>
<!-- 	<input type="hidden" name="id1" id="id1" value="0" /> -->
<%-- </form:form> --%>

<c:url value="View_Search_College_FinancialUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="id6">
	<input type="hidden" name="id6" id="id6" value="0" />
	<input type="hidden" name="statusview" id="statusview" value="0" />
</form:form>

<%-- <c:url value="App_Teacher_dtlUrl" var="App_MedUrl" /> --%>
<%-- <form:form action="${App_MedUrl}" method="post" id="AppForm" --%>
<%-- 	name="AppForm" modelAttribute="id7"> --%>
<!-- 	<input type="hidden" name="id7" id="id7" value="0" /> -->
<!-- 	<input type="hidden" name="status7" id="status7" value="0" /> -->
<%-- </form:form> --%>


<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {

// 	datepicketDate('date_of_birth');
	
	mockjax1('getSearch_College_Financial');
	table = dataTable('getSearch_College_Financial');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
});

function data(getSearch_College_Financial) {
	
	jsondata = [];
var table = $('#' + getSearch_College_Financial).DataTable();
	var info = table.page.info();
//		var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];

// if(document.querySelector('input[name="council_check"]:checked')==true){
	var fix_deposite= $('#fix_deposite').val();
	var current_acct = $("#current_acct").val();
	var saving_acct = $("#saving_acct").val();
	var project_cost = $('#project_cost').val();
	var income_statement = $('#income_statement').val();
	
	$.post("getFilterSearch_College_Financial_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		fix_deposite : fix_deposite,
		current_acct : current_acct,
		saving_acct : saving_acct,
		project_cost : project_cost,
		income_statement : income_statement
	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			
			jsondata.push([ j[i].ser,j[i].fix_deposite,j[i].current_acct, j[i].saving_acct,j[i].project_cost,j[i].income_statement,j[i].action]);
			
		}
	});
	$.post("getTotalSearch_College_Financial_dataCount?" + key + "=" + value, {
		Search : Search,
		fix_deposite : fix_deposite,
		current_acct : current_acct,
		saving_acct : saving_acct,
		project_cost : project_cost,
		income_statement : income_statement
		
	}, function(j) {
		
		FilteredRecords = j;
		});
	setTimeout(setTimeLoadForTable, 1000);
}

function setTimeLoadForTable() {
	
	document.querySelectorAll('.viewData').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('viewId'+val).value;
			
			if (confirm('Are You Sure You Want to View Detail ?')) {
				ViewData(hid);
			} else {
				return false;
			}
		})
	});
	
}

function ViewData(id){
	$("#id6").val(id);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
}

</script>
