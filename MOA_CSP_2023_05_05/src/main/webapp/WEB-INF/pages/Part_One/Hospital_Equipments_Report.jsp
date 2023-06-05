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
						<h2>View Hospital Equipments</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									View Hospital Equipments</li>
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
						action="Search_Hospital_InfrastractureAction?${_csrf.parameterName}=${_csrf.token}"
						method="post" modelAttribute="Search_Hospital_Infrastracture_CMD">
						<!-- input style start -->
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">View Hospital Equipments</h6>
								<div class="row">

<!-- 									<div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
<!-- 										<div class="select-style-1"> -->
<!-- 											<label>Articles </label> -->
<!-- 											<div class="select-position"> -->
<!-- 											<select class="singleselect form-control form-control-lg" name="articles" id="articles"> -->
<!-- 												<option value="0">--Select--</option> -->
<%-- 												<c:forEach var="item" items="${getarticaldata}" varStatus="num"> --%>
<%-- 													<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 												</c:forEach> --%>
<!-- 											</select> -->
<!-- 										</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
									
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="select-style-1">
											<label>Institute Name </label>
											<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="instId" id="instId">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedInstituteName}" varStatus="num">
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
											<li><a href="hospital_equipments"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i
													class="lni lni-chevron-left"></i>Back</a></li>
											<li><a type="button"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												id="btn-reload" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="Search_Hospital_Equipment_Report_url"
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
												<table id="getSearch_Hospital_Equipments" class="table">
													<thead>
														<tr>

															<th><h6>Sr No</h6></th>
															<th><h6>Institute Name</h6></th>
															<th><h6>Articles & Equipment</h6></th>
<!-- 															<th><h6>Total Equipment</h6></th> -->
															<th><h6>Action</h6></th>
														</tr>
														<!-- end table row-->
													</thead>
													<tbody class="custom-datatablepra">
<%-- 													<c:forEach var="item" items="${list}" --%>
<%-- 										varStatus="num"> --%>
<!-- 													<tr> -->
<!-- 													<td></td> -->
<!-- 													<td></td> -->
<!-- 													<td></td> -->
<!-- 													</tr> -->
<%-- 													</c:forEach> --%>
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

<c:url value="View_Hospital_Equipment_ReportUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="id6">
	<input type="hidden" name="id6" id="id6" value="0" />
	<input type="hidden" name="statusview" id="statusview" value="0" />
</form:form>

<%-- <c:url value="View_Hospital_Equipment_ReportUrl" var="View_SearchUrl" /> --%>
<%-- <form:form action="${viewUrl}" method="post" id="View_SearchForm" --%>
<%--       name="View_SearchForm" modelAttribute="id6"> --%>
<!-- 	<input type="hidden" name="id7" id="id7" value="0" /> -->
<!-- 	<input type="hidden" name="status7" id="status7" value="0" /> -->
<%-- </form:form> --%>


<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
// alert(${list[0]})
	mockjax1('getSearch_Hospital_Equipments');
	table = dataTable('getSearch_Hospital_Equipments');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
});


function data(getSearch_Hospital_Equipments) {
	
	jsondata = [];
    var table = $('#' + getSearch_Hospital_Equipments).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];
	var articles= $("#articles").val();
	var instId = $("#instId").val();
		
	console.log(articles);
	
	var username = $("#username").val();
	
	$.post("getFilterSearch_Hospital_Equipment_Report_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		articles : articles,
		instId : instId
		
		
	}
	, function(j) {
// debugger;
// 		var articles ="";
// 		var instId ="";
// 		var ser=0;
// 		var total_equipments="";
		
		for (var i = 0; i < j.length; i++) {
// 			ser = i+1;
// 			if(i == 0){
// 				instId = j[i].institute_id;
// 			}
			
// 			if(instId == j[i].institute_id){
// 				if(articles == ""){
// 					articles = j[i].a2;
// 					total_equipments = j[i].total_equipments;
// 				}else{
// 					articles = articles+"<br>"+j[i].a2;
// 					total_equipments = total_equipments+"<br>"+j[i].total_equipments;
// 				}
// 			}
			
// 			if(instId != j[i].institute_id){
// 				jsondata.push([ ser,j[i].institute_name,articles,total_equipments,j[i].action]);
// 				instId = j[i].institute_id;
// 			}
			
// 			if(i == j.length-1){
// 				jsondata.push([ ser,j[i].institute_name,articles,total_equipments,j[i].action]);
// 			}
			
			jsondata.push([j[i].ser,j[i].institute_name,j[i].articles,j[i].action]);
		}
	});
	$.post("getTotalSearch_Hospital_Equipment_Report_dataCount?" + key + "=" + value, {
		Search : Search,
		articles : articles,
		instId : instId,
		
		
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
