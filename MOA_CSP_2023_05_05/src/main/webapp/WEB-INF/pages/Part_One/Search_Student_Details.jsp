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
						<h2>View Student Details</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									View Student Details</li>
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
						action="Search_Student_DetailsAction?${_csrf.parameterName}=${_csrf.token}"
						method="post" modelAttribute="Search_Student_Details_CMD">
						<!-- input style start -->
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">View Student Details</h6>
								<div class="row">
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label for="fname">Last Student</label> <input type="text"
												name="last_student" id="last_student" class="form-control"
												placeholder="Last Student">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">


										<div class="select-style-1">
											<label>Year of Admission <strong class="mandatory">*
											</strong></label>
											<div class="select-position">
												<select class="form-control" name="year" id="year">
													<!-- style="text-transform: uppercase" -->
													<option value="0" selected="selected">--Select
														Year --</option>
													<%-- 																	<c:forEach var="item" items="${year}" --%>
													<!-- 																		varStatus="num"> -->
													<option value="${year[0]}" name="${year[0]}">${year[0]}</option>
													<option value="${year[1]}" name="${year[1]}">${year[1]}</option>
													<option value="${year[2]}" name="${year[2]}">${year[2]}</option>
													<option value="${year[3]}" name="${year[3]}">${year[3]}</option>
													<option value="${year[4]}" name="${year[4]}">${year[4]}</option>
													<%-- 																	</c:forEach> --%>
												</select>
											</div>
										</div>



									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Total interns in O.P.D</label> <input type="text"
												id="internsopd" name="internsopd" class="form-control"
												placeholder="Total interns in O.P.D">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Total interns in IPD</label> <input type="text"
												id="internsipd" name="internsipd" class="form-control"
												placeholder="Total interns in IPD">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Whether intern students prescribe medicine</label> <input
												type="text" id="prescribe" name="prescribe"
												class="form-control" placeholder="Prescribe Medicine">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Seminar for Internee</label> <input type="text"
												id="seminar" name="seminar" class="form-control"
												placeholder="Seminar for Internee">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>House Job</label> <input type="text" id="house_job"
												name="house_job" class="form-control"
												placeholder="House Job">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Number of Students undergoing House Job</label> <input
												type="text" id="no_house_job" name="no_house_job"
												class="form-control"
												placeholder="Number of Students undergoing House Job">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<label>Any migration of intern from/to College </label>
										<div class="input-style-form-check">

											<div class="form-check radio-style">
												<input type="radio" id="migration_Yes" name="migrationcheck"
													class="form-check-input" value="0"> <label
													for="migration_Yes" class="form-check-label">Yes</label>
											</div>
											<div class="form-check radio-style">
												<input type="radio" id="migration_No" name="migrationcheck"
													class="form-check-input" value="1"> <label
													for="migration_No" class="form-check-label">No</label>
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
											<li><a href="student_details"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i
													class="lni lni-chevron-left"></i>Back</a></li>
											<li><a type="button"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												id="btn-reload" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="Search_Student_Details_url"
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
												<table id="getSearch_Student_Details" class="table">
													<thead>
														<tr>

															<th><h6>Sr No</h6></th>
															<th><h6>Last Student</h6></th>
															<th><h6>Year of Admission</h6></th>
															<th><h6>Total interns in O.P.D</h6></th>
															<th><h6>Total interns in IPD</h6></th>
															<th><h6>Whether intern students prescribe
																	medicine</h6></th>
															<th><h6>Seminar for Internee</h6></th>
															<th><h6>House Job</h6></th>
															<th><h6>Number of Students undergoing House Job</h6></th>
															<th><h6>Any migration of intern from/to College</h6></th>
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

<c:url value="View_Search_Student_DetailsUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="id6">
	<input type="hidden" name="id6" id="id6" value="0" />
	<input type="hidden" name="statusview" id="statusview" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
// 	datepicketDate('date_of_birth');
// alert('${year}')
	
	mockjax1('getSearch_Student_Details');
	table = dataTable('getSearch_Student_Details');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	var app = "";
	app+="<div class='select-style-1'>"
			+"<label>Year of Admission </label>"
			+"<div class='select-position'>"
			+"<select name='year' id='year'"
			+"	class='singleselect form-control form-control-lg'>"
			+"	<option value='0' selected='selected'>-- Select--</option>"
			+"<option value='"+getYear(1)+"'>"+getYear(1)+"-"+getYear(0)+"</option>"
			+"	<option value='"+getYear(2)+"'>"+getYear(2)+"-"+getYear(1)+"</option>"
			+"	<option value='"+getYear(3)+"'>"+getYear(3)+"-"+getYear(2)+"</option>"
			+"	<option value='"+getYear(4)+"'>"+getYear(4)+"-"+getYear(3)+"</option>"
			+"	<option value='"+getYear(5)+"'>"+getYear(5)+"-"+getYear(4)+"</option>"
			+"</select>"
			+"</div>"
			+"</div>";


// 	app+="<div class='select-style-1'>"
// 			+"<label>Year of Admission </label>"
// 			+"<div class='select-position'>"
// 			+"<select name='year' id='year'"
// 			+"	class='singleselect form-control form-control-lg'>"
// 			+"	<option value='0' selected='selected'>-- Select--</option>"
// 			+"<option value='"+calendar.getTime()+"</option>"
// 			+"</select>"
// 			+"</div>"
// 			+"</div>";
			
	$('#year_append').append(app);
});

function data(getSearch_Student_Details) {
	
	jsondata = [];
var table = $('#' + getSearch_Student_Details).DataTable();
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

	var last_student= $('#last_student').val();
	var year = $("#year").val();
	var internsopd= $('#internsopd').val();
	var internsipd= $('#internsipd').val();
	var prescribe= $('#prescribe').val();
	var seminar= $('#seminar').val();
	var house_job= $('#house_job').val();
	var no_house_job= $('#no_house_job').val();
	var migrationcheck= $('input[name="migrationcheck"]:checked').val();
	
	$.post("getFilterSearch_Student_Details_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		last_student : last_student,
		year : year,
		internsopd : internsopd,
		internsipd : internsipd,
		prescribe : prescribe,
		seminar : seminar,
		house_job : house_job,
		no_house_job : no_house_job,
		migrationcheck : migrationcheck
	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			
			var migrationcheck ="";
			migrationcheck = j[i].migrationcheck;
			if(migrationcheck == "1"){
				migrationcheck="Yes"
			}
			if(migrationcheck == "2"){
				migrationcheck="No"
			}
			
			jsondata.push([ j[i].ser,j[i].last_student,j[i].year,j[i].internsopd,j[i].internsipd,j[i].prescribe,
				j[i].seminar,j[i].house_job,j[i].no_house_job,migrationcheck,j[i].action]);
			
		}
	});
	$.post("getTotalSearch_Student_Details_dataCount?" + key + "=" + value, {
		Search : Search,
		last_student : last_student,
		year : year,
		internsopd : internsopd,
		internsipd : internsipd,
		prescribe : prescribe,
		seminar : seminar,
		house_job : house_job,
		no_house_job : no_house_job,
		migrationcheck : migrationcheck
		
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

document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('internsopd').onkeypress = function() {
		return isNumberKey0to9(event,this);
	};
	document.getElementById('internsipd').onkeypress = function() {
		return isNumberKey0to9(event,this);
	};
	document.getElementById('no_house_job').onkeypress = function() {
		return isNumberKey0to9(event,this);
	};
});

function ViewData(id){
	$("#id6").val(id);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
}

function getYear(val){
	var date = new Date();
	date.setDate( date.getDate());
	date.setFullYear( date.getFullYear() - val );
// 	$("#searchDateFrom").val(date.getFullYear());
	return date.getFullYear();
	console.log(date.getFullYear());
	}

</script>
