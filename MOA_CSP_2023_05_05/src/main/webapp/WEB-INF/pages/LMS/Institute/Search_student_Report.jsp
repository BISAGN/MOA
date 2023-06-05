<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->


<section class="dashboard-page">
<div class="container-fluid">

	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>STUDENT ENROLLMENT REPORT</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item">
								<a href="#">Ncism</a>
							</li>
							<li class="breadcrumb-item active" aria-current="page">STUDENT ENROLLMENT REPORT</li>
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
                <form:form name="" id="" action="Search_maintenance_demand_Action" method="post" class="form-horizontal" commandName="Search_maintenance_demand_CMD"> 
					<div class="card-style mb-30">
					<h6 class="mb-25">STUDENT ENROLLMENT REPORT</h6>
						<div class="row">
								
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-2">
									<label for="username">User Name<span class="mandatory">*</span></label>
									<input type="text" id="user_name" name="user_name" class="autocomplete UpperClassName txt-transupp"
										autocomplete="off" maxlength="25" placeholder="User Name" />
								</div>
								<!-- end input -->
							</div>

							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="username">System Name<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="system_name" id="system_name" >
								        	<option value="0">---Select---</option>
											<c:forEach var="item" items="${getsysList}" varStatus="num">
												<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>								
								
								<!-- end select -->
							</div>
										
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="username">Set Name<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="set_name" id="set_name">
								        	<option value="0">---Select---</option>
											<c:forEach var="item" items="${getSetList}" varStatus="num">
												<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>								
								
								<!-- end select -->
							</div>
								
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="username">Course Name<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="course_name" id="course_name">
								        <option value="0">---Select---</option>
										<c:forEach var="item" items="${getCourseNamelist}"
											varStatus="num">
											<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
										</c:forEach>
										</select>
									</div>
								</div>								
								
								<!-- end select -->
							</div>
			
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="username">Approval Status</label>
									<div class="select-position">
										<select name="app_status" id="app_status">
								        	<option value="0">Pending</option>
		 									<option value="1">Approve</option>
		  									<option value="2">Reject</option>
										</select>
									</div>
								</div>								
								
								<!-- end select -->
							</div>

						</div>		

						<ul class="buttons-group mainbtn">

							<li>
							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button" value="Search"><i class="lni lni-search-alt"></i>search</a>
							</li>
							<!-- <li>
								<a id="btn-save" class="main-btn info-btn btn-hover" type="submit">Save</a>
							</li> -->
							<li>
								<a href="Search_student_report_url" class="main-btn dark-btn n btn-hover" type="button" value="Reset">Reset</a>
							</li>
						</ul>
				</div>
	

				</form:form>
			</div>
		</div>
	</div>

<div class="row">
	<div class="col-12">
		<div class="card-style mb-30">
			<div class="table-wrapper table-responsive custom-datatable-p">
				<table class="table" id="getSearch_Stud_Reg">
					<thead>
							<tr>
				       		    <th align="center">Ser No</th>
								<th align="center">User Name</th>
								<th align="center">System Name</th>
								<th align="center">Course Name</th>
								<th align="center">Set Name</th>
			
								<th class="action">Action</th>
				            </tr>
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

<%-- <form:form name="" id="" action="Search_maintenance_demand_Action"
	method="post" class="form-horizontal"
	commandName="Search_maintenance_demand_CMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>STUDENT ENROLLMENT REPORT</h5>
				</div>
				<div class="card-body card-block">

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="username">USER NAME<span
										class="star_design"></span> <span style="color: #ff0000">*</span></label>
								</div>
								<div class="col-md-8">
									<input id="user_name" name="user_name" class="form-control"
										autocomplete="off" maxlength="25" />

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="username">SYSTEM NAME<span
										class="star_design"></span> <span style="color: #ff0000">*</span></label>
								</div>
								<div class="col-md-8">
									<select name="system_name" id="system_name"
										class="form-control">
										<option value="0">---SELECT---</option>
										<c:forEach var="item" items="${getsysList}" varStatus="num">
											<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<br>
					<br>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="username">SET NAME<span class="star_design"></span>
										<span style="color: #ff0000">*</span></label>
								</div>
								<div class="col-md-8">
									<select name="set_name" id="set_name" class="form-control">
										<option value="0">---SELECT---</option>
										<c:forEach var="item" items="${getSetList}" varStatus="num">
											<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="username">COURSE NAME<span
										class="star_design"></span> <span style="color: #ff0000">*</span></label>
								</div>
								<div class="col-md-8">
									<select name="course_name" id="course_name"
										class="form-control">
										<option value="0">---SELECT---</option>
										<c:forEach var="item" items="${getCourseNamelist}"
											varStatus="num">
											<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<br /> <br />
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="username">APPROVAL STATUS</label>
							</div>
							<div class="col-md-8">
								<select name="app_status" id="app_status" class="form-control">

									<option value="0">PENDING</option>
									<option value="1">APPROVE</option>
									<option value="2">REJECT</option>

								</select>
							</div>
						</div>
					</div>


				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href=Search_student_report_url class="btn-clear"><button
							type="button" class="btn-clear" value="Reset">RESET</button></a>
					<button type="button" class="btn-search" id="btn-reload">
						<i class="fa fa-search">SEARCH</i>
					</button>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<table id="getSearch_Stud_Reg"
			class="display table no-margin table-striped  table-hover  table-bordered">
			<thead>
				<tr>
					<th align="center">SER NO</th>
					<th align="center">USER NAME</th>
					<th align="center">SYSTEM NAME</th>
					<th align="center">COURSE NAME</th>
					<th align="center">SET NAME</th>

					<th class="action">ACTION</th>
				</tr>
			</thead>
		</table>
	</div>
</form:form> --%>

<c:url value="Search_student_Approve_url"
	var="Search_student_Approve_url" />
<form:form action="${Search_student_Approve_url}" method="post"
	id="AcceptRegistration" name="AcceptRegistration"
	modelAttribute="Acceptid">
	<input type="hidden" name="Acceptid" id="Acceptid" value="0" />
	<input type="hidden" name="name1" id="name1" value="0" />
</form:form>

<c:url value="Search_student_Reject_url" var="Search_student_Reject_url" />
<form:form action="${Search_student_Reject_url}" method="post"
	id="RejectRegistration" name="RejectRegistration"
	modelAttribute="Rejecttid">
	<input type="hidden" name="Rejectid" id="Rejectid" value="0" />
</form:form>



<script nonce="${cspNonce}" type="text/javascript">

	$(document).ready(function() {

		mockjax1('getSearch_Stud_Reg');
		table = dataTable('getSearch_Stud_Reg');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
	});

	function data(getSearch_Stud_Reg) {
		jsondata = [];
		var table = $('#' + getSearch_Stud_Reg).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];

		var user_name = $("#user_name").val();
		var system_name = $("#system_name").val();
		var set_name = $("#set_name").val();
		var course_name = $("#course_name").val();
		var app_status = $("#app_status").val();

		$.post("getFilterstudent_reg_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			user_name : user_name,
			system_name : system_name,
			set_name : set_name,
			course_name : course_name,
			app_status : app_status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].login_name, j[i].system_name,
						j[i].course_name, j[i].setname, j[i].action ]);

			}
		});
		$.post("getTotalstudent_reg_dataCount?" + key + "=" + value, {
			user_name : user_name,
			system_name : system_name,
			set_name : set_name,
			course_name : course_name,
			app_status : app_status

		}, function(j) {

			FilteredRecords = j;
		});
	}
	
	function Accepturl(id) {

		$("#Acceptid").val(id);
		$("#name1").val(name);
		document.getElementById('AcceptRegistration').submit();
	}

	function Rejecturl(id) {

		$("#Rejectid").val(id);
		document.getElementById('RejectRegistration').submit();
	}
</script>
