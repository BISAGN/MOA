<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Student Free Course Report</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Student
									Free Course Report</li>
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
					<form:form action=" " method="POST" class="form-horizontal"
						modelAttribute="  " enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Student Free Course Report</h6>
								<div class="row">

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Student</label>
											<div class="select-position">
												<select name="username1" id="username1">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getStudentlist}"
														varStatus="num">
														<option value="${item.username}" name="${item.username}">${item.username}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Course</label>
											<div class="select-position">
												<select name="coursename" id="coursename">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getfreecoursename}"
														varStatus="num">
														<option value="${item.coursename}"
															name="${item.coursename}">${item.coursename}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>

								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<!-- <li>
								<a id="btn-save" class="main-btn info-btn btn-hover" type="submit">Save</a>
							</li> -->
											<li><a href="Student_Coures_Dtl"
												class="main-btn dark-btn n btn-hover" type="button">Reset</a>
											</li>
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
									<div class="table-wrapper table-responsive custom-datatable-p">
										<table class="table" id="search_StudentDtl">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Student</h6></th>
													<th><h6>Course</h6></th>
													<th><h6>Start date</h6></th>
													<th><h6>End date</h6></th>
													<th><h6>Action</h6></th>
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
						</section>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>



<c:url value="getstudentDocument" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="downloadPdf"
	name="downloadPdf" modelAttribute="doc_id1">
	<input type="hidden" name="pageUrl" id="pageUrl"
		value="redirect:Student_Coures_Dtl" />
	<input type="hidden" name="doc_id1" id="doc_id1" value="" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	
// setTimeout(setTimeLoadForTable, 1000);

	$(document).ready(function() {

		datepicketDate('start_date');
		datepicketDate('end_date');

		mockjax1('search_StudentDtl');
		table = dataTable('search_StudentDtl');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});

		if ('${list.size()}' == "") {
			$("div#search_StudentDtl").hide();
		}
		
		if(window.location.href.includes("doc_id1"))
		{
			 var url = window.location.href.split("?doc_id1")[0];
			 window.location = url;
		}
		
		
		
	});
	

	
	function setTimeLoadForTable(){
		document.querySelectorAll('.downloadOnclick').forEach((items, index) => {
			items.addEventListener('click', event => {
				debugger;
				var val=parseInt(index)+1;
				var hid = document.getElementById('downloadid'+val).value;
				if (confirm('Are You Sure You Want to Download Detail ?')) {
					download_file(hid);
				} else {
					return false;
				}
			})
		});
	
	}
	
	

	function download_file(id) {
		$("#doc_id1").val(id);
		document.getElementById("downloadPdf").submit();
	}

	function data(search_StudentDtl) {
		jsondata = [];
		var table = $('#' + search_StudentDtl).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var username = $("#username1").val();
		var coursename = $("#coursename").val();
		var start_date = null;
		var end_date = null;

		$.post("getFilterstu_dtl_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			username : username,
			coursename : coursename,
			start_date : start_date,
			end_date : end_date

		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i][6], j[i][2], j[i][3], j[i][0], j[i][1],
						j[i][4] ]);
			}
		});
		$.post("getTotalstu_dtl_dataCount?" + key + "=" + value, {
			Search : Search,
			start_date : start_date,
			end_date : end_date,
			username : username,
			coursename : coursename
		}, function(j) {
			FilteredRecords = j;
		});
		setTimeout(setTimeLoadForTable, 1000);
	}
</script>
