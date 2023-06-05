<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/common/commonmethod.js"></script>

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
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>Fee Payment Report</h2>
					</div>
				</div>
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Fee
									Payment Report</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<form:form name="ug_pg_fee_payment_Form"
						id="ug_pg_fee_payment_Form" action="ug_pg_fee_payment_Action"
						method="post"
						commandName="ug_pg_fee_payment_Reg_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Type Of Degree<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="type_of_degree" id="type_of_degree">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${gettype_of_degree}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>

												</select>
											</div>
										</div>
										<input type="hidden" id="id" name="id" value="0" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="degree_name" id="degree_name">
													<option value="0">--Select--</option>

												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Profession<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="term_id" id="term_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${gettermList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
											<input type="hidden" id="id" name="id" value="0" class="mt-3"
												autocomplete="off" />
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Student Name <span class="mandatory"></span></label> <input
												type="text" id="name" name="name"
												class="autocomplete xt-transupp" autocomplete="off"
												maxlength="100" placeholder="Student Name " /> <input
												type="hidden" id="studentId_hid" name="studentId_hid"
												value="0" autocomplete="off" />
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Status<span class="mandatory"></span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="status1" id="status1">
													
													<option value="0">Pending</option>
													<option value="1">Paid</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								</div>
								<div class="btn-bottom">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12">
											<ul class="buttons-group mainbtn">
												<li><a id="btn-reload"
													class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
													type="button"><i class="lni lni-search-alt"></i>Search</a>
												</li>
												<li><a href="Search_ug_pg_fee_payment_Url"
													class="main-btn dark-btn n btn-hover btnreset"
													type="button">Reset</a></li>
												<li><a
													class="main-btn success-btn btn-hover btn-iconic-icon btnexcel"
													id="btnExport"><i class="bi bi-file-earmark-excel"
														value="PDF" title="Export to PDF"></i> EXCEL </a></li>
												<li><a
													class="main-btn secondary-btn-outline btn-hover btn-iconic-icon btnpdf"
													id="pdfex"><i class="bi bi-file-pdf" id="printId"
														value="PDF" title="Export to PDF"></i> PDF </a></li>

											</ul>
										</div>
									</div>
								</div>
							</div>
					</form:form>
				</div>
			</div>
			<section class="single-detail-block">
				<div class="row">
					<div class="col-12 col-sm-12 col-md-12 col-lg-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-datatable-p">
								<table class="table" id="Search_ug_pg_fee_payment">
									<thead>
										<tr>
											<th>Sr No</th>
											<th>Student Name</th>
											<th>Type Of Degree</th>
											<th>Fees Paid Status</th>
											<th>Degree</th>
											<th>Profession</th>
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
	
	<!-- Hidden Start --> 
	<c:url value="ug_pg_fee_payment" var="ug_pg_fee_payment" />
<form:form action="${ug_pg_fee_payment}" method="post"
	id="ug_pg_fee_payment_Form" name="ug_pg_fee_payment_Form"
	modelAttribute="id" target="result">
	<input type="hidden" name="feespayid" id="feespayid" value="0" />
</form:form>
<c:url value="getug_pg_fee_payment_Report_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="type_of_degree1" id="type_of_degree1"
		value="0" />
	<input type="hidden" name="degree_name1" id="degree_name1" value="0" />
	<input type="hidden" name="term_id1" id="term_id1" value="0" />
	<input type="hidden" name="studentId_hid1" id="studentId_hid1"
		value="0" />
		<input type="hidden" name="status2" id="status2" value="0" />
		<input type="hidden" name="name1" id="name1" value="0" />
</form:form>
<c:url value="ug_pg_fee_payment_Report_PDF" var="searchUrl1" />
<form:form action="${searchUrl1}" method="post" id="search3"
	name="search3" modelAttribute="comd1">
	<input type="hidden" name="type_of_degree2" id="type_of_degree2"
		value="0" />
	<input type="hidden" name="degree_name2" id="degree_name2" value="0" />
	<input type="hidden" name="term_id2" id="term_id2" value="0" />
	<input type="hidden" name="studentId_hid2" id="studentId_hid2"
		value="0" />
		<input type="hidden" name="status3" id="status3" value="0" />
		<input type="hidden" name="name2" id="name2" value="0" />

</form:form>
<!-- Hidden End --> 
</section>

<script type="text/javascript" nonce="${cspNonce}">
	$(document).ready(function() {

		mockjax1('Search_ug_pg_fee_payment');
		table = dataTable('Search_ug_pg_fee_payment');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});

	});
	//csp----------------------------
	document.addEventListener('DOMContentLoaded', function() {

		// 	document.getElementById('status1').onclick = function() {
		// 		return clickclear(this, 'DD/MM/YYYY');
		// 	};
		// 	

		document.getElementById('type_of_degree').onchange = function() {
			return getdegreefrom_type_of_degree();
		};

		document.getElementById('btnExport').onclick = function() {
			getug_pg_fee_payment_Report_Excel();
		};

		document.getElementById('pdfex').onclick = function() {
			ug_pg_fee_payment_Report_PDF();
		};

	});
	function data(Search_ug_pg_fee_payment) {
		jsondata = [];
		var table = $('#' + Search_ug_pg_fee_payment).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];

		var name = $("#name").val();
		var status1 = $("#status1").val();
		var type_of_degree = $('#type_of_degree').val();
		var degree_name = $("#degree_name").val();
		var term_id = $("#term_id").val();

		$.post("getFilterSearch_ug_pg_fee_payment_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			name : name,
			status1 : status1,
			type_of_degree : type_of_degree,
			degree_name : degree_name,
			term_id : term_id

		}, function(j) {

			var tod = "";
			var prof = "";
			var fps = "";

			for (var i = 0; i < j.length; i++) {
				debugger;
				// 			if(j[i].semester == "1"){
				// 				prof="FIRST PROFESSIONAL";
				// 			}
				// 			if(j[i].semester == "2"){
				// 				prof="SECOND PROFESSIONAL";
				// 			}
				// 			if(j[i].semester == "3"){
				// 				prof="THIRD PROFESSIONAL";
				// 			}
				// 			if(j[i].semester == "4"){
				// 				prof="FOURTH PROFESSIONAL";
				// 			}
				if (j[i].type_of_degree == "Student_NCISM") {
					tod = "UG";
				}
				if (j[i].type_of_degree == "Student_NCH") {
					tod = "UG";
				}
				if (j[i].type_of_degree == "ADM_UG_Student_NCISM") {
					tod = "UG";
				}
				if (j[i].type_of_degree == "ADM_UG_Student_NCH") {
					tod = "UG";
				}
				if (j[i].type_of_degree == "ADM_PG_Student_NCISM") {
					tod = "PG";
				}
				if (j[i].type_of_degree == "ADM_PG_Student_NCH") {
					tod = "PG";
				}
				if (j[i].fee_paid_status == "0") {
					fps = "Pending";
				}
				if (j[i].fee_paid_status == "1") {
					fps = "Paid";
				}
				jsondata.push([ j[i].ser, j[i].name, tod, fps,
						j[i].degree_name, j[i].prof_name ]);
			}
		});
		$.post("getTotalSearch_ug_pg_fee_payment_dataCount?" + key + "="
				+ value, {

			name : name,
			status1 : status1,
			type_of_degree : type_of_degree,
			degree_name : degree_name,
			term_id : term_id

		}, function(j) {
			FilteredRecords = j;
		});

		//setTimeout(setTimeLoadForTable, 500);
	}

	function getprofession_listbydegree() {
		var degree_name = $("#degree_id").val();
		$
				.post('getprofession_listbydegree?' + key + "=" + value, {
					degree_name : degree_name
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#profession_id").html(options);
						});
	}

	//type of degree 

	function getdegreefrom_type_of_degree() {
		//debugger;

		var type_of_degree = $("select#type_of_degree").val();

		//alert(system_id);

		$
				.post(
						"degreefrom_fromybyinstlist_ctrl?" + key + "=" + value,
						{
							type_of_degree : type_of_degree
						},
						function(j) {

							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#degree_name").html(options);
						});
	}

	function getug_pg_fee_payment_Report_Excel() {
		// 		alert(1);
		$("#type_of_degree1").val($("#type_of_degree").val());
		$("#degree_name1").val($("#degree_name").val());
		$("#term_id1").val($("#term_id").val());
		$("#status2").val($("#status1").val());
		$("#studentId_hid1").val($("#studentId_hid").val());
		$("#name1").val($("#name").val());

		document.getElementById('search2').submit();

	}
	function ug_pg_fee_payment_Report_PDF() {
		// 		alert("HIIIIIIIIII");
		$("#type_of_degree2").val($("#type_of_degree").val());
		$("#degree_name2").val($("#degree_name").val());
		$("#term_id2").val($("#term_id").val());
		$("#status3").val($("#status1").val());
		$("#studentId_hid2").val($("#studentId_hid").val());
		$("#name2").val($("#name").val());

		document.getElementById('search3').submit();

	}
</Script>
