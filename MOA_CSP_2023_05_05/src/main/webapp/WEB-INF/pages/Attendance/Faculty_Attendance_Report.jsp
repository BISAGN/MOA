<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->
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
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<!-- <link rel="stylesheet" href="assets/vendor/internal_css.css"> -->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span> Faculty Attendance Report
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Faculty
									Attendance Report</li>
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
					<!-- 							input style start -->
					<form:form name="faculty_attendance_report"
						id="faculty_attendance_report"
						action="faculty_attendance_reportAction" method="post"
						modelAttribute="faculty_attendance_reportCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Faculty Attendance Report</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Date<span class="mandatory"></span></label>
											<div class="input-style-2">
												<input type="month" name="month" id="month"
													class="form-control-sm form-control effect-9 hasDatepicker"
													placeholder="Enter Academic Year Applied For"
													autocomplete="off">

											</div>
										</div>
										<!-- 											end select -->
									</div>

									<!-- 										<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
									<!-- 											<div class="select-style-1"> -->
									<!-- 												<label for="text-input">Name<span class="mandatory"></span></label> -->
									<!-- 												<div class="input-style-2"> -->
									<!-- 													<input type="text" name="searchInputname" id="searchInputname" -->
									<!-- 														class="form-control-sm form-control" -->
									<!-- 														placeholder="Search ..." -->
									<!-- 														autocomplete="off"> -->

									<!-- 												</div> -->
									<!-- 											</div> -->
									<!-- <!-- 											end select -->
									<!-- 										</div> -->

									<!-- 										<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
									<!-- 											<div class="select-style-1"> -->
									<!-- 												<label for="text-input">Teacher Code<span class="mandatory"></span></label> -->
									<!-- 												<div class="input-style-2"> -->
									<!-- 													<input type="text" name="searchInpuTC" id="searchInputTC" -->
									<!-- 														class="form-control-sm form-control" -->
									<!-- 														placeholder="Search ..." -->
									<!-- 														autocomplete="off"> -->

									<!-- 												</div> -->
									<!-- 											</div> -->
									<!-- <!-- 											end select -->
									<!-- 										</div> -->

								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="Faculty_Attend_Report_url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>

						<!-- 						end card -->
						<section class="single-detail-block">
							<div id="view_tbl">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="card-style mb-30">
											<div
												class="table-wrapper table-responsive custom-table b-top">
												<table class="table" id="search_Add_Non_Lecture_Activities"
													id="getStudentAttendReportSearch">
													<thead>
														<tr>
															<th><h6>Sr No</h6></th>
															<th id="name"><h6>Name</h6></th>
															<th id="name"><h6>Teacher Code</h6></th>
															<th id="1"><h6>01</h6></th>
															<th id="2"><h6>02</h6></th>
															<th id="3"><h6>03</h6></th>
															<th id="4"><h6>04</h6></th>
															<th id="5"><h6>05</h6></th>
															<th id="6"><h6>06</h6></th>
															<th id="7"><h6>07</h6></th>
															<th id="8"><h6>08</h6></th>
															<th id="9"><h6>09</h6></th>
															<th id="10"><h6>10</h6></th>
															<th id="11"><h6>11</h6></th>
															<th id="12"><h6>12</h6></th>
															<th id="13"><h6>13</h6></th>
															<th id="14"><h6>14</h6></th>
															<th id="15"><h6>15</h6></th>
															<th id="16"><h6>16</h6></th>
															<th id="17"><h6>17</h6></th>
															<th id="18"><h6>18</h6></th>
															<th id="19"><h6>19</h6></th>
															<th id="20"><h6>20</h6></th>
															<th id="21"><h6>21</h6></th>
															<th id="22"><h6>22</h6></th>
															<th id="23"><h6>23</h6></th>
															<th id="24"><h6>24</h6></th>
															<th id="25"><h6>25</h6></th>
															<th id="26"><h6>26</h6></th>
															<th id="27"><h6>27</h6></th>
															<th id="28"><h6>28</h6></th>
															<c:if test="${month != 02}">
																<th id="29"><h6>29</h6></th>
																<th id="30"><h6>30</h6></th>
																<c:if
																	test="${month == 01 || month == 03 || month == 05 || month == 07 || month == 08 || month == 10 || month == 12}">
																	<th id="31"><h6>31</h6></th>
																</c:if>
															</c:if>
															<th id="name"><h6>Total Present Days</h6></th>
														</tr>
													</thead>
													<tbody>
														<c:if test="${list.size()==0}">
															<tr>
																<td colspan="7"><p>Data not available</p></td>
															</tr>
														</c:if>
														<c:forEach var="item" items="${list}" varStatus="num">
															<tr>
																<td><p>${num.index+1}</p></td>
																<td id="name2"><p>${item[0]}</p></td>
																<td id="name2"><p>${item[1]}</p></td>
																<td>${item[2]}</p></td>
																<td id="02"><p>${item[3]}</p></td>
																<td id="03"><p>${item[4]}</p></td>
																<td id="04"><p>${item[5]}</p></td>
																<td id="05"><p>${item[6]}</p></td>
																<td id="06"><p>${item[7]}</p></td>
																<td id="07"><p>${item[8]}</p></td>
																<td id="08"><p>${item[9]}</p></td>
																<td id="09"><p>${item[10]}</p></td>
																<td id="10"><p>${item[11]}</p></td>
																<td id="11"><p>${item[12]}</p></td>
																<td id="12"><p>${item[13]}</p></td>
																<td id="13"><p>${item[14]}</p></td>
																<td id="14"><p>${item[15]}</p></td>
																<td id="15"><p>${item[16]}</p></td>
																<td id="16"><p>${item[17]}</p></td>
																<td id="17"><p>${item[18]}</p></td>
																<td id="18"><p>${item[19]}</p></td>

																<td id="19"><p>${item[20]}</p></td>
																<td id="20"><p>${item[21]}</p></td>
																<td id="21"><p>${item[22]}</p></td>
																<td id="22"><p>${item[23]}</p></td>

																<td id="23"><p>${item[24]}</p></td>
																<td id="24"><p>${item[25]}</p></td>
																<td id="25"><p>${item[26]}</p></td>
																<td id="26"><p>${item[27]}</p></td>
																<td id="27"><p>${item[28]}</p></td>
																<td id="28"><p>${item[29]}</p></td>
																<c:if test="${month == 02}">
																	<td id="28"><p>${item[30]}</p></td>
																</c:if>
																<c:if test="${month != 02}">
																	<td id="29"><p>${item[30]}</p></td>
																	<%-- 								<c:if test="${30<=date}"><p> --%>
																	<td id="30"><p>${item[31]}</p></td>
																	<c:if
																		test="${month == 01 || month == 03 || month eq 5 || month == 07 || month == 08 || month == 10 || month == 12}">
																		<td id="31"><p>${item[31]}</p></td>
																		<td id="32"><p>${item[33]}</p></td>
																	</c:if>
																	<c:if
																		test="${month == 04 || month == 06 || month eq 9 || month == 11}">
																		<td id="31"><p>${item[32]}</p></td>
																	</c:if>
																</c:if>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
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

<c:url value="get_FacultyAtdncReport" var="get_FacultyAtdncReport" />

<form:form action="${get_FacultyAtdncReport}" method="post" id="report1"
	name="report1">
	<input type="hidden" name="typeReport" id="typeReport" value="0" />
	<input type="hidden" name="date2" id="date2" />
</form:form>
<c:url value="search_month_Faculty_Attend" var="searchmonthUrl" />
<form:form action="${searchmonthUrl}" method="post" id="searchForm"
	name="searchForm">
	<input type="hidden" name="month1" id="month1" />
	<input type="hidden" name="name1" id="name1" />
	<input type="hidden" name="teach_code1" id="teach_code1" />
</form:form>


<script type="text/javascript" nonce="${cspNonce}">
	$(document).ready(
			function() {
				// 						alert('${mo_yr}')
				if ('${month_year}' != "") {
					$("#month").val('${month_year}');
				} else {
					var date = new Date().toISOString().split("T")[0];
					var dt = date.substring(0, 7)
					$("#month").val(dt);
				}
				// 				if('${mo_yr}' != ""){
				// 					$("#month").val('${mo_yr}');
				// 				}
				// 				if('${name1}' != ""){
				// 					$("#searchInputname").val('${name1}');
				// 				}
				// 				if('${teach_code1}' != ""){
				// 					$("#searchInputTC").val('${teach_code1}');
				// 				}

				// 		datepicketDate('month');
				$("#searchInputname").on(
						"keyup",
						function() {
							var value = $(this).val().toLowerCase();
							$("#getStudentAttendReportSearch tbody tr").filter(
									function() {
										$(this).toggle(
												$(this).text().toLowerCase()
														.indexOf(value) > -1)
									});
						});
			});

	function Search() {
		month = $("#month").val();
		//alert("month---" + month);
		$("#month1").val($("#month").val());
		$("#name1").val($("#searchInputname").val());
		$("#teach_code1").val($("#searchInputTC").val());
		$("#searchForm").submit();
	}
	function get_FacultyAtdncPDF() {
		$("#date2").val($("#month").val());
		document.getElementById('typeReport').value = 'pdfL';
		document.getElementById('report1').submit();
	}
	////////CSP start================================================	
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('btn-reload').onclick = function() {
			Search();
		};
		// 		document.getElementById('month').onclick = function() {
		// 			clickclear(this, 'YYYY/MM');
		// 		};
		// 		document.getElementById('month').onfocus = function() {
		// 			this.style.color='#000000';
		// 		};
		// 		document.getElementById('month').onblur = function() {
		// 			clickrecall(this,'MM/YYYY');
		// 			validateDate_BackDate(this.value,this);
		// 		};
		// 		document.getElementById('month').onkeyup = function() {
		// 			clickclear(this, 'MM/YYYY');
		// 		};
		// 		document.getElementById('month').onchange = function() {
		// 			clickrecall(this,'MM/YYYY');
		// 			validateDate_FutureDate(this.value,this);
		// 		};
	});
</script>
