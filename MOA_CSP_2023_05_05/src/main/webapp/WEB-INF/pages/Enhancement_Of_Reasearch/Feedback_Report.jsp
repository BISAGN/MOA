<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<section class="dashboard-page">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Feedback Report</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Feedback Report</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<!-- title-wrapper end -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12 col-sm-12 col-md-12 col-lg-12">
					<form:form name="" id="" action="Search_Student_RegAction"
						method="post" 
						commandName="Search_Student_Reg_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4"
										id="university_div">
										<div class="select-style-1">
											<label for="text-input">Type of Issue<span
												class="mandatory"></span></label>
											<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="type_of_issue" id="type_of_issue" >
											    <option value="0" id="server" name=" ">--select--</option>
												<option value="15" id="server" name=" ">Server Issue</option>
												<option value="25" id="content" name="Student">Content Issue</option>
												<option value="35" id="design" name="Intern">Design Issue</option>												
											</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="text-input"> First Name<span class="mandatory"></span></label>
											<input type="text" id="first_name" name="first_name"
												placeholder="Enter First Name"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" />
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="text-input">Last Name<span class="mandatory"></span></label>
											<input type="text" id="last_name" name="last_name"
												placeholder="Enter Last Name"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" />
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="text-input">Email<span class="mandatory"></span></label>
											<input type="text" id="email" name="email"
												placeholder="Enter Email"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" />
										</div>
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
											<li><a href="Feedback_Report_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a>
											</li>

<!-- 											<li id="pdfex"><a -->
<!-- 												class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon btnpdf" -->
<!-- 												><i class="bi bi-file-pdf" id="printId" -->
<!-- 													value="PDF" title="Export to PDF"></i> PDF </a></li> -->
<!-- 											<li id="btnExport"><a -->
<!-- 												class="main-btn success-btn btn-hover btn-iconic-icon btnexcel" -->
<!-- 												><i class="bi bi-file-earmark-excel" value="PDF" -->
<!-- 													title="Export to PDF"></i> EXCEL </a></li> -->
													
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
					</form:form>
				</div>
			</div>
			<section class="single-detail-block">
				<div class="row">
					<div class="col-12 col-sm-12 col-md-12 col-lg-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-datatable-p">
								<table class="table" id="getSearch_Feedback">
									<thead>
										<tr>
											<th><h6>Sr No</h6></th>
											<th><h6>Type of Issue</h6></th>
											<th><h6>First Name</h6></th>
											<th><h6>Last Name</h6></th>
											<th><h6>Email</h6></th>
											<th><h6>Phone Number</h6></th>
											<th><h6>Feedback</h6></th>
<!-- 											<th class="action"><h6>View</h6></th> -->
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
<c:url value="Search_Stu_RegPopupUrl" var="Search_Stu_RegPopupUrl" />
<form:form action="${Search_Stu_RegPopupUrl}" method="post"
	id="studentpopup_Form" name="studentpopup_Form" modelAttribute="id"
	target="result">
	<input type="hidden" name="popid" id="popid" value="0" />
</form:form>

<c:url value="NCH_Std_details_view_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm5"
	name="applicationUrlForm5" modelAttribute="ch_eid">
	<input type="hidden" name="ch_eid" id="ch_eid" value="0" />
</form:form>


<c:url value="Student_Registration_Report_PDF" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="university_id1" id="university_id1"
		value="0" />
	<input type="hidden" name="institute_id1" id="institute_id1" value="0" />
	<input type="hidden" name="name1" id="name1" value="0" />
	<input type="hidden" name="ayush_id1" id="ayush_id1" value="0" />
	<input type="hidden" name="gender1" id="gender1" value="0" />
	<input type="hidden" name="date_of_birth1" id="date_of_birth1"
		value="0" />
</form:form>



<c:url value="Student_Registration_Report_Excel" var="searchUrl1" />
<form:form action="${searchUrl1}" method="post" id="search3"
	name="search3" modelAttribute="comd1">
	<input type="hidden" name="university_id2" id="university_id2"
		value="0" />
	<input type="hidden" name="institute_id2" id="institute_id2" value="0" />
	<input type="hidden" name="name2" id="name2" value="0" />
	<input type="hidden" name="ayush_id2" id="ayush_id2" value="0" />
	<input type="hidden" name="gender2" id="gender2" value="0" />
	<input type="hidden" name="date_of_birth2" id="date_of_birth2"
		value="0" />
</form:form>

<script nonce="${cspNonce}">
$(document).ready(function() {
	
	mockjax1('getSearch_Feedback');
	table = dataTable('getSearch_Feedback');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	
});

function data(getSearch_Feedback) {
	jsondata = [];
	var table = $('#' + getSearch_Feedback).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
	var orderType = order[0][1];
	
	var type_of_issue = $("#type_of_issue").val();
	var first_name = $("#first_name").val();
	var last_name = $("#last_name").val();
	var email = $("#email").val();
	

	$.post("getFilterSearch_Feedback_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength: pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		
		type_of_issue:type_of_issue,
		first_name:first_name,
		last_name:last_name,
		email:email
		
	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			
			jsondata.push([ j[i].ser,j[i].type_of_issue,j[i].first_name,j[i].last_name,j[i].email,j[i].ph_no,j[i].your_feedback ]);
		}
	});
	$.post("getTotalSearch_Feedback_dataCount?" + key + "=" + value, {
		Search : Search,
		type_of_issue:type_of_issue,
		first_name:first_name,
		last_name:last_name,
		email:email
		
	}, function(j) {
		FilteredRecords = j;
		});
	
	setTimeout(setTimeLoadForTable, 1000);
}
function setTimeLoadForTable(){
	document.querySelectorAll('.VIEWdetails').forEach((items, index) => {
		items.addEventListener('click', event => {
			debugger;
			var val=parseInt(index)+1;
			var hid = document.getElementById('viewId'+val).value;
			if (confirm('Are You Sure You Want to Show Detail ?')) {
				Pop_Up_Stu_Reg(hid);
			} else {
				return false;
			}
		})
	});
}

</Script>
