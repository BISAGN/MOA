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
<section class="dashboard-page">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Contact Form Report</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Contact Form Report</li>
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
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="text-input">Name<span class="mandatory"></span></label>
											<input type="text" id="name" name="name"
												placeholder="Enter Name"
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
											<li><a href="ContactUs_Report_Url"
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
								<table class="table" id="getSearch_Contact_Us">
								
									<thead>
										<tr>
											<th><h6>Sr No</h6></th>
											<th><h6>Name</h6></th>
											<th><h6>Email</h6></th>
											<th><h6>Subject</h6></th>
											<th><h6>Message</h6></th>
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



<script nonce="${cspNonce}">
$(document).ready(function() {
	
	
	mockjax1('getSearch_Contact_Us');
	table = dataTable('getSearch_Contact_Us');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	
});

function data(getSearch_Contact_Us) {
	jsondata = [];
	var table = $('#' + getSearch_Contact_Us).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
	var orderType = order[0][1];
	
	var name = $("#name").val();
	var email = $("#email").val();

	$.post("getFilterSearch_Contact_Us_data1?" + key + "=" + value, {
		startPage : startPage,
		pageLength: pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		name:name,
		email:email
		
	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			
			jsondata.push([j[i].ser,j[i].name,j[i].email,j[i].subject,j[i].message ]);
		}
	});
	$.post("getTotalSearch_Contact_Us_dataCount?" + key + "=" + value, {
		Search : Search,
		name:name,
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
