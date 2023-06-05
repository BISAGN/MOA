<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
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
<!-- datatable style and js end-->


<script src="js/Pdf_View/pdf.js" type="text/javascript"></script>
<script src="js/Pdf_View/pdf.worker.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="js/Pdf_View/pdfview.css">


<section class="dashboard-page">
	<div class="container-fluid">

		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>E-Form Student Report</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">E-Form
									Student Report</li>
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
					<form:form name="course" id="course" action="CourseAction"
						method='POST' commandName="courseCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Admission Student Details Report</h6>
								<div class="row">


									<div class="col-12 col-sm-12 col-md-6 col-lg-3 custom-d-none"
										id="system_div">
										<div class="select-style-1">
											<label for="university_id">System</label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="system_id" id="system_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getSystemForAll}"
														varStatus="num">
														<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3 custom-d-none"
										id="university_div">
										<div class="select-style-1">
											<label for="university_id">Name of University</label>
											<div class="select-position">
												<select class="select2 form-control form-control-lg"
													name="university_id" id="university_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getUniverCityList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>



									<div class="col-12 col-sm-12 col-md-6 col-lg-3"
										id="institute_div">
										<div class="select-style-1">
											<label for="text-input">Type Of Degree</label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="type_of_degree" id="type_of_degree">
<!-- 													<option value="0">--Select--</option> -->
													<c:forEach var="item" items="${gettype_of_degree}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									
									<div class="col-12 col-sm-12 col-md-6 col-lg-3 custom-d-none" id="pg_degree_div">
										<div class="select-style-1">
											<label for="text-input">Degree</label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="pg_degree" id="pg_degree">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getDegreePG}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									
									<div class="col-12 col-sm-12 col-md-6 col-lg-3 custom-d-none" id="pg_sub_div">
										<div class="select-style-1">
											<label for="text-input">Subject</label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="pg_subject" id="pg_subject">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
									</div>
									
									<div class="col-12 col-sm-12 col-md-6 col-lg-3 custom-d-none" id="pg_intake_div">
										<div class="select-style-1">
											<label for="text-input">Intake Type</label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg" name="intake_id"
														id="intake_id">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getintake_typelist}"
															varStatus="num">
															<option value="${item.id}" name="${item.intake}">${item.intake}</option>
														</c:forEach>
													</select>
											</div>
										</div>
									</div>
									
									
									<div class="col-12 col-sm-12 col-md-6 col-lg-3  custom-d-none"
										id="institute_div">
										<div class="select-style-1">
											<label for="text-input">Name of Institute</label>
											<div class="select-position">
												<select class="select2 form-control form-control-lg"
													name="name_of_institute" id="name_of_institute">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getapp_instituteNameList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>


									<div class="d-none">
										<div class="col-12 col-sm-12 col-md-6 col-lg-3">
											<div class="input-style-1">
												<label for="text-input">Institute Id</label> <input
													type="text" id="institute_id" name="institute_id"
													placeholder="Enter Institute Id" maxlength="30"
													autocomplete="off">
											</div>
										</div>


										<div class="col-12 col-sm-12 col-md-6 col-lg-3">
											<div class="select-style-1">
												<label for="text-input">State</label>
												<div class="select-position">
													<select class=" form-control form-control-lg"
														name="state_id" id="state_id">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getMedStateName}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>

									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">First Name</label> <input type="text"
												id="name" name="name" placeholder="Enter First Name"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="20" />
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Middle Name & Last Name</label> <input
												type="text" id="last_name" name="last_name"
												placeholder="Enter Middle Name & Last Name"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="20" />
										</div>
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Mother's Name</label> <input
												type="text" id="mother_name" name=mother_name
												placeholder="Enter Mother's Name"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="20" />
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Father's Name</label> <input
												type="text" id="father_name" name=father_name
												placeholder="Enter Father's Name"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="20" />
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Date Of Birth</label> <input
												type="text" name="dob" id="dob" maxlength="10"
												aria-required="true" autocomplete="off" value="DD/MM/YYYY"
												class="form-control-sm form-control effect-9 ">
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Email Id</label> <input type="text"
												id="email" name="email" placeholder="Enter Email"
												pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" />
											<!-- 										onchange="checkgmail(this.value)" -->
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input" id="lbl_neet_app_no">NEET Application No.</label> <input
												type="text" id="neet_application_no"
												name="neet_application_no" maxlength="12" autocomplete="off"
												placeholder="Enter NEET Application No">
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input" id="lbl_neet_roll_no">NEET Roll No.</label> <input
												type="text" id="neet_roll_no" name=neet_roll_no
												maxlength="12" autocomplete="off"
												placeholder="Enter NEET Roll No.">
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input" id="lbl_neet_air">ALL INDIA NEET Rank</label> <input
												type="text" id="neet_rank" name="neet_rank" maxlength="7"
												autocomplete="off" placeholder="Enter ALL INDIA NEET Rank">
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input" id="lbl_neet_per">NEET Percentile</label> <input
												type="text" id="neet_percentile" name="neet_percentile"
												maxlength="5" autocomplete="off"
												placeholder="Enter NEET Percentile">
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input" id="lbl_neet_marks">Marks Obtained</label> <input
												type="text" id="neet_marks" name="neet_marks" maxlength="3"
												autocomplete="off" placeholder="Enter Marks Obtained">
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Quota</label>
											<div class="select-position">
												<select class=" form-control form-control-lg"
													name="quota_id" id="quota_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getQuotaList}"
														varStatus="num">
														<option value="${item.id}" name="${item.quota}">${item.quota}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Counselling Authority</label>
											<div class="select-position">
												<select class=" form-control form-control-lg"
													name="counselling_authority" id="counselling_authority">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getCounselingAuthoList}"
														varStatus="num">
														<option value="${item.id}"
															name="${item.counseling_authority}">${item.counseling_authority}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Category</label>
											<div class="select-position">
												<select class=" form-control form-control-lg"
													name="category_id" id="category_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getcategorylist}"
														varStatus="num">
														<option value="${item.id}" name="${item.category}">${item.category}</option>
													</c:forEach>
												</select>
											</div>
											<div class="input-style-1 m-0">
												<input type='hidden' id='id' name="id" value='0' /> <input
													type="hidden" id="video_id" name="video_id" value='0' />
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Data Filled Date</label> <input
												type="text" name="dwda" id="dwda" maxlength="10"
												aria-required="true" autocomplete="off" value="DD/MM/YYYY"
												class="form-control-sm form-control effect-9 ">
										</div>
									</div>
									<div class="input-style-1 m-0">
										<!-- 							<input type="hidden" id="institude_id" name="institude_id"> -->
										<input type="hidden" id="system_id" name="system_id" value="0">
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
												type="button" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<!-- <li>
											<input id="btn-save" class="main-btn info-btn btn-hover" type="submit" value="Save" onclick="return Validation();">
											</li>  -->
											<li><a href="StudentDetailsReport_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a>
											</li>

											<li><a
												class="main-btn success-btn btn-hover btn-iconic-icon btnexcel"
												id="btnExport"><i class="lni lni-printer" value="EXCEL"
													title="Export to EXCEL"></i> EXCEL </a></li>
											<li><a
												class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon btnpdf"
												id="pdfex"><i class="bi bi-file-pdf" id="printId"
													value="PDF" title="Export to PDF"></i>PDF</a></li>
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
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-datatable-p">
								<table class="table" id="search_StudentDetails">
									<thead>
										<tr align="center">
											<th><h6>Sr No</h6></th>
											<th><h6>Data Filled Date</h6></th>
											<th><h6>Name of Institute</h6></th>
											<th><h6>Institute Id</h6></th>
											<th><h6>State</h6></th>
											<th><h6>First Name</h6></th>
											<th><h6>Middle Name & Last Name</h6></th>
											<th><h6>Mother's Name</h6></th>
											<th><h6>Father's Name</h6></th>
											<th><h6>Date Of Birth</h6></th>
											<th><h6>Email Id</h6></th>
											<th><h6 id="th_neet_app_no">NEET Application No</h6></th>
											<th><h6 id="th_neet_roll_no">NEET Roll No</h6></th>
											<th><h6 id="th_neet_air">ALL INDIA NEET Rank</h6></th>
											<th><h6 id="th_neet_per">NEET Percentile</h6></th>
											<th><h6>Marks Obtained</h6></th>
											<th><h6>Quota</h6></th>
											<th><h6>Counselling Authority</h6></th>
											<th><h6>Category</h6></th>
											<th><h6>Date Of Fees Payment</h6></th>
											<th><h6>View Fees Receipt</h6></th>
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
		</div>
	</div>
</section>









<c:url value="deletecourse_Url" var="deletecourse_Url" />
<form:form action="${deletecourse_Url}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>



<c:url value="getstudentdetails_Report_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="name_of_institute1" id="name_of_institute1"
		value="0" />
	<input type="hidden" name="institute_id1" id="institute_id1" value="0" />
	<input type="hidden" name="state_id1" id="state_id1" value="0" />
	<input type="hidden" name="name1" id="name1" value="0" />
	<input type="hidden" name="last_name1" id="last_name1" value="0" />
	<input type="hidden" name="mother_name1" id="mother_name1" value="0" />
	<input type="hidden" name="father_name1" id="father_name1" value="0" />
	<input type="hidden" name="dob1" id="dob1" value="0" />
	<input type="hidden" name="email1" id="email1" value="0" />
	<input type="hidden" name="neet_application_no1"
		id="neet_application_no1" value="0" />
	<input type="hidden" name="neet_roll_no1" id="neet_roll_no1" value="0" />
	<input type="hidden" name="neet_rank1" id="neet_rank1" value="0" />
	<input type="hidden" name="neet_percentile1" id="neet_percentile1"
		value="0" />
	<input type="hidden" name="neet_marks1" id="neet_marks1" value="0" />
	<input type="hidden" name="quota_id1" id="quota_id1" value="0" />
	<input type="hidden" name="counselling_authority1"
		id="counselling_authority1" value="0" />
	<input type="hidden" name="category_id1" id="category_id1" value="0" />
	<input type="hidden" name="university_id1" id="university_id1"
		value="0" />
	<input type="hidden" name="type_of_degree1" id="type_of_degree1"
		value="0" />
		
	<input type="hidden" name="pg_degree1" id="pg_degree1" value="0" />
	<input type="hidden" name="pg_subject1" id="pg_subject1" value="0" />
	<input type="hidden" name="pg_intake1" id="pg_intake1" value="0" />
		

</form:form>

<c:url value="Admission_Student_Details_PDF" var="searchUrl1" />
<form:form action="${searchUrl1}" method="post" id="search3"
	name="search3" modelAttribute="comd1">
	<input type="hidden" name="name_of_institute2" id="name_of_institute2"
		value="0" />
	<input type="hidden" name="institute_id2" id="institute_id2" value="0" />
	<input type="hidden" name="state_id2" id="state_id2" value="0" />
	<input type="hidden" name="name2" id="name2" value="0" />
	<input type="hidden" name="last_name2" id="last_name2" value="0" />
	<input type="hidden" name="mother_name2" id="mother_name2" value="0" />
	<input type="hidden" name="father_name2" id="father_name2" value="0" />
	<input type="hidden" name="dob2" id="dob2" value="0" />
	<input type="hidden" name="email2" id="email2" value="0" />
	<input type="hidden" name="neet_application_no2"
		id="neet_application_no2" value="0" />
	<input type="hidden" name="neet_roll_no2" id="neet_roll_no2" value="0" />
	<input type="hidden" name="neet_rank2" id="neet_rank2" value="0" />
	<input type="hidden" name="neet_percentile2" id="neet_percentile2"
		value="0" />
	<input type="hidden" name="neet_marks2" id="neet_marks2" value="0" />
	<input type="hidden" name="quota_id2" id="quota_id2" value="0" />
	<input type="hidden" name="counselling_authority2"
		id="counselling_authority2" value="0" />
	<input type="hidden" name="category_id2" id="category_id2" value="0" />
	<input type="hidden" name="university_id2" id="university_id2"
		value="0" />
	<input type="hidden" name="type_of_degree2" id="type_of_degree2"
		value="0" />
		
	<input type="hidden" name="pg_degree2" id="pg_degree2" value="0" />
	<input type="hidden" name="pg_subject2" id="pg_subject2" value="0" />
	<input type="hidden" name="pg_intake2" id="pg_intake2" value="0" />
</form:form>





<div class="modal fade custom-modal custom-modal-pdf" tabindex="-1"
	aria-labelledby="myLargeModalLabel" id="exampleModal"
	aria-hidden="true">
	<div
		class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Document Preview</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<div class="custom-modal-inner" id="headData1" name="headData1">
					<div class="row">
						
						<div class="col-lg-12 col-md-12 col-sm-12 col-12 text-center">

							<div id="pdfmodelcanvas"></div>

						</div>
					</div>
				</div>


			</div>
			<div class="modal-footer" id="modal-footer"></div>

		</div>
	</div>
</div> 








<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		// 		var type_of_degree = "${gettype_of_degree[0][1]}";
		var role = '${roleid}';

		console.log(role);

		if (role == '20') { /// uni
			$("#university_id").val('${uni_id}');
			$("#institute_div").show();
			get_inst_by_uni_nch('${uni_id}');
		}

		if (role == '22') { ///inst
			$("#university_id").val('${uni_id}');
			$("#name_of_institute").val('${inst_id}');
		}

		if (role == '61' || role == '62' || role == '63' || role == '64') {// BOARD
			$("#system_id").val('${system_id}');
			$("#institute_div").show();
			$("#university_div").show();
		}

		if (role == '16') {
			// 		$("#system_id").val('${system_id}');
			$("#system_div").show();
			$("#institute_div").show();
			$("#university_div").show();
		}

		mockjax1('search_StudentDetails');
		table = dataTable('search_StudentDetails');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});

		datepicketDate('dob');
		datepicketDate('dwda');
		datepicketDate('admission_date');
		datepicketDate('upload_date');

		$("#admission_date").datepicker("option", "maxDate", null);
		$("#upload_date").datepicker("option", "maxDate", null);

	});

	function data(Exp_Exl_Nch_table) {
		jsondata = [];
		var table = $('#' + Exp_Exl_Nch_table).DataTable();
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
		var dob = $("#dob").val();
		var email = $("#email").val();
		var mobile_no = $("#mobile_no").val();
		var system = $("#system_id").val();
		var degree = $("#degree").val();
		var institute_id = $("#name_of_institute").val();
		var institute_code = $("#institute_id").val();
		var state_id = $("#state_id").val();
		var last_name = $("#last_name").val();

		var mother_name = $("#mother_name").val();
		var father_name = $("#father_name").val();

		var neet_application_no = $("#neet_application_no").val();
		var neet_roll_no = $("#neet_roll_no").val();
		var neet_rank = $("#neet_rank").val();
		var neet_percentile = $("#neet_percentile").val();
		var neet_marks = $("#neet_marks").val();
		var quota_id = $("#quota_id").val();
		var counselling_authority = $("#counselling_authority").val();
		var category_id = $("#category_id").val();
		var university_id = $("#university_id").val();
// 		var type_of_degree = "${gettype_of_degree[0][0]}";
		var type_of_degree = $("#type_of_degree").val();
		
		var pg_degree = $("#pg_degree").val();
		var pg_subject = $("#pg_subject").val();
		var pg_intake = $("#intake_id").val();

		$.post("getFilterstudentdetails_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			institute_id : institute_id,
			institute_code : institute_code,
			state_id : state_id,
			name : name,
			last_name : last_name,
			mother_name : mother_name,
			father_name : father_name,
			dob : dob,
			email : email,
			neet_application_no : neet_application_no,
			neet_roll_no : neet_roll_no,
			neet_rank : neet_rank,
			neet_percentile : neet_percentile,
			neet_marks : neet_marks,
			quota_id : quota_id,
			counselling_authority : counselling_authority,
			category_id : category_id,
			system : system,
			university_id : university_id,
			type_of_degree : type_of_degree,
			pg_degree : pg_degree,
			pg_subject : pg_subject,
			pg_intake : pg_intake

		}, function(j) {
			if (j.length == 0) {
				$("#jlength").val('1');
			}

			for (var i = 0; i < j.length; i++) {

				jsondata.push([ j[i][0],j[i][19], j[i][1], j[i][2], j[i][3], j[i][4],
						j[i][5], j[i][6], j[i][7], j[i][8], j[i][9], j[i][10],
						j[i][11], j[i][12], j[i][13], j[i][14], j[i][15],
						j[i][16], j[i][17], j[i][20], j[i][21] ]);
			}
		});

		$.post("getTotalstudentdetailsCount?" + key + "=" + value, {

			Search : Search,
			institute_id : institute_id,
			institute_code : institute_code,
			state_id : state_id,
			name : name,
			last_name : last_name,
			mother_name : mother_name,
			father_name : father_name,
			dob : dob,
			email : email,
			neet_application_no : neet_application_no,
			neet_roll_no : neet_roll_no,
			neet_rank : neet_rank,
			neet_percentile : neet_percentile,
			neet_marks : neet_marks,
			quota_id : quota_id,
			counselling_authority : counselling_authority,
			category_id : category_id,
			system : system,
			university_id : university_id,
			type_of_degree : type_of_degree,
			pg_degree : pg_degree,
			pg_subject : pg_subject,
			pg_intake : pg_intake
		}, function(j) {
			FilteredRecords = j;
		});
		
		
		setTimeout(setTimeLoadForTable, 1000);
	}

	function getdegreelistbysystem() {
		var system_name = $("#system").val();
		$
				.post('getdegreelistby_system1?' + key + "=" + value, {
					system_name : system_name
				})
				.done(
						function(j) {
							if (j.length > 0) {
								var options = '<option value="' + "0" + '">'
										+ "--Select--" + '</option>';
								for (var i = 0; i < j.length; i++) {
									options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
											+ j[i][1] + '</option>';
								}
								$("select#degree").html(options);
							}
						});
	}
	function checkgmail(email1) {

		document.getElementById("email").innerHTML = "";
		if (/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {

		} else {

			alert("Please Enter Valid Email Address");
			$("input#email").focus();
			$("input#email").val('');
			return false;
		}
	}

	function mobileNumber(obj) {
		if (obj.value.length < 10) {
			alert('Please Enter valid Number');
			$('#' + obj.id).focus();
			$('#' + obj.id).val("");
			return false;
		}
		_mobile = obj.value;
		var regExp = /^[6789]\d{9}$/;
		if (_mobile == '' || !regExp.test(_mobile)) {
			alert('Please Enter Number Start with 6789 Digit');
			$('#' + obj.id).focus();
			$('#' + obj.id).val("");
			return false;
		}
	}

	function checkLength() {

		// 	var ayush_id = $("#ayush_id").val().trim();

		// 		var minLength = 20;
		// 		var charLength = ayush_id.length;

		// 		if (charLength < minLength) {
		// 			alert("Ayush Id Should Contain Minimum 20 Digit");
		// 			$("input#ayush_id").focus();
		// 			return false;
		// 		}

		var maxLength = 20;
		var charLength = ayush_id.length;

		if (charLength > maxLength) {
			alert("Ayush Id Should Contain Maximum 20 Digit");
			$("input#ayush_id").focus();
			return false;
		}

		var student_name = $("#name").val().trim();

		// 		var charLength1 = student_name.length;
		// 		var minLength1 = 20;

		// 		if (charLength1 < minLength1) {
		// 			alert("Student Should Contain Minimum 20 Digit");
		// 			$("input#name").focus();
		// 			return false;
		// 		}

		var charLengthname = student_name.length;
		var maxLength1 = 20;

		if (charLengthname > maxLength1) {
			alert("Student Should Contain Maximum 20 Digit");
			$("input#name").focus();
			return false;
		}

		var enrollment_no = $("#enrollment_no").val().trim();
		var charLengthenrolll_no = enrollment_no.length;
		var maxLength2 = 20;

		if (charLengthenrolll_no > maxLength2) {
			alert("Enrollment No Should Contain Maximum 20 Digit");
			$("input#enrollment_no").focus();
			return false;
		}

	}

	// start csp
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('dob').onclick = function() {
			clickclear(this, 'DD/MM/YYYY');
		};

		document.getElementById('dob').onfocus = function() {
			this.style.color = '#000000';
		};

		document.getElementById('dob').onblur = function() {
			clickrecall(this, 'DD/MM/YYYY');
			validateDate_BackDate(this.value, this);
		};

		document.getElementById('dob').onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('dob').onchange = function() {
			clickrecall(this, 'DD/MM/YYYY');
			validateDate_FutureDate(this.value, this);
		};
		document.getElementById('university_id').onchange = function() {
			get_inst_by_uni_nch(this.value);
		};

		document.getElementById('btnExport').onclick = function() {
			getstudentdetails_Report_Report_Excel();
		};

		document.getElementById('pdfex').onclick = function() {
			Admission_Student_Details_PDF();
		};
		document.getElementById('type_of_degree').onchange = function() {
			ToDonchange();
		};
		
		document.getElementById('pg_degree').onchange = function() {
			getPGSubjectsofDegree();
		};

	});
	
	function ToDonchange(){
		if($("#type_of_degree").val()=="16"){
			$("#pg_degree_div").show();
			$("#pg_sub_div").show();
			$("#pg_intake_div").show();
			
			$("#lbl_neet_app_no").text("AIAPGET Application No.");
			$('#neet_application_no').attr('placeholder','AIAPGET Application No.');
			$("#lbl_neet_roll_no").text("AIAPGET Roll No.");
			$('#neet_roll_no').attr('placeholder','AIAPGET Roll No.');
			$("#lbl_neet_air").text("AIAPGET All India Rank");
			$('#neet_rank').attr('placeholder','AIAPGET All India Rank');
			$("#lbl_neet_per").text("AIAPGET Percentile");
			$('#neet_percentile').attr('placeholder','AIAPGET Percentile');
			$("#th_neet_app_no").text("AIAPGET Application No.");
			$("#th_neet_roll_no").text("AIAPGET Roll No.");
			$("#th_neet_air").text("AIAPGET All India Rank");
			$("#th_neet_per").text("AIAPGET Percentile");
			
		}else{
			$("#pg_degree_div").hide();
			$("#pg_sub_div").hide();
			$("#pg_intake_div").hide();
			
			$("#lbl_neet_app_no").text("NEET Application No.");
			$('#neet_application_no').attr('placeholder','NEET Application No.');
			$("#lbl_neet_roll_no").text("NEET Roll No.");
			$('#neet_roll_no').attr('placeholder','NEET Roll No.');
			$("#lbl_neet_air").text("NEET All India Rank");
			$('#neet_rank').attr('placeholder','NEET All India Rank');
			$("#lbl_neet_per").text("NEET Percentile");
			$('#neet_percentile').attr('placeholder','NEET Percentile');
			$("#th_neet_app_no").text("NEET Application No.");
			$("#th_neet_roll_no").text("NEET Roll No.");
			$("#th_neet_air").text("NEET All India Rank");
			$("#th_neet_per").text("NEET Percentile");
		}
	}

	function getstudentdetails_Report_Report_Excel() {
		$("#name_of_institute1").val($("#name_of_institute").val());
		$("#institute_id1").val($("#name_of_institute").val());
		$("#institute_code1").val($("#institute_code").val());
		$("#state_id1").val($("#state_id").val());
		$("#name1").val($("#name").val());
		$("#last_name1").val($("#last_name").val());
		$("#mother_name1").val($("#mother_name").val());
		$("#father_name1").val($("#father_name").val());
		$("#dob1").val($("#dob").val());
		$("#email1").val($("#email").val());
		$("#system1").val($("#system").val());
		$("#degree1").val($("#degree").val());
		$("#neet_application_no1").val($("#neet_application_no").val());
		$("#neet_roll_no1").val($("#neet_roll_no").val());
		$("#neet_rank1").val($("#neet_rank").val());
		$("#neet_percentile1").val($("#neet_percentile").val());
		$("#neet_marks1").val($("#neet_marks").val());
		$("#quota_id1").val($("#quota_id").val());
		$("#counselling_authority1").val($("#counselling_authority").val());
		$("#category_id1").val($("#category_id").val());
		$("#university_id1").val($("#university_id").val());
		$("#type_of_degree1").val($("#type_of_degree").val());
		$("#pg_degree1").val($("#pg_degree").val());
		$("#pg_subject1").val($("#pg_subject").val());
		$("#pg_intake1").val($("#intake_id").val());

		document.getElementById('search2').submit();

	}

	function Admission_Student_Details_PDF() {
		//		 		alert("HIIIIIIIIII");
		$("#name_of_institute2").val($("#name_of_institute").val());
		$("#institute_id2").val($("#name_of_institute").val());
		$("#state_id2").val($("#state_id").val());
		$("#name2").val($("#name").val());
		$("#last_name2").val($("#last_name").val());
		$("#mother_name2").val($("#mother_name").val());
		$("#father_name2").val($("#father_name").val());
		$("#dob2").val($("#dob").val());
		$("#email2").val($("#email").val());
		$("#neet_application_no2").val($("#neet_application_no").val());
		$("#neet_roll_no2").val($("#neet_roll_no").val());
		$("#neet_rank2").val($("#neet_rank").val());
		$("#neet_percentile2").val($("#neet_percentile").val());
		$("#neet_marks2").val($("#neet_marks").val());
		$("#quota_id2").val($("#quota_id").val());
		$("#counselling_authority2").val($("#counselling_authority").val());
		$("#category_id2").val($("#category_id").val());
		$("#university_id2").val($("#university_id2").val());
		$("#type_of_degree2").val($("#type_of_degree").val());
		$("#pg_degree2").val($("#pg_degree").val());
		$("#pg_subject2").val($("#pg_subject").val());
		$("#pg_intake2").val($("#intake_id").val());

		document.getElementById('search3').submit();
	}

	function get_inst_by_uni_nch(val) {
		$.ajaxSetup({
			async : false
		});
		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		$
				.post(
						'get_inst_by_uni_nch_excel_ctrl?' + key + "=" + value,
						{
							university_id : val
						},
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][0]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#name_of_institute").html(options);

						}).fail(function(xhr, textStatus, errorThrown) {
					alert("fail to fetch")
				});

	}
	
	function getPGSubjectsofDegree() {
		var degree = $("#pg_degree").val();
		$.post('getPGSubjectsofInstitute?' + key + "=" + value,{  
			degree : degree
			}).done(function(j) {
							var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i].id + '" name="'+j[i].course_name+'" >'+ j[i].course_name + '</option>';
							}
							$("select#pg_subject").html(options);
		});
	}

	// end csp
	
	
	function setTimeLoadForTable() {
		document.querySelectorAll('.view_fees_receipt').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				var gdid = document.getElementById('receiptid'+val).value;
				
				if (confirm('Are You Sure You Want to View Detail?')) {
					file_view(gdid,7,4);
				} else {
					return false;
				}
			})
		});
	}
	
		function file_view(id,val,field) {
			 $("#PicturePDFId").val(id);
			 $("#val1").val(val);
			 $("#fildname1").val(field);
			 
			var pdf1="kmlFileDownloadFinalDynemic44?kmlId2="+id+"&val444="+val+"&fildname1="+field+" ";
			PDFView(pdf1,id,val,field);
			
		}	
	
		function PDFView(path1,idx,val,field){
			
			console.log(path1);
			
			$("#pdfmodelcanvas").empty();
			$("#modal-footer").empty();
			
				var canvas_data = "";
				
				canvas_data += '<div id="my_pdf_viewer'+idx+'" class="custom-pdf-viewer">'										
										+'<div id="canvas_container">'
										+'<div>'
										+'<canvas id="pdf_renderer'+idx+'"  width="600px" height="500px" ></canvas>'
										+'<input type="hidden" value="'+idx+'" id="PicturePDFId" /> <input'
										+'	type="hidden" value="'+val+'" id="val1" /> <input type="hidden"'
										+'	value="'+field+'" id="fildname1" />'
										+'</div>'
										+'</div>'
										+'</div>';

			$("#pdfmodelcanvas").append(canvas_data);
								
				var btn="";
					btn +=   '<div class="modal-footer-left">'
										+'<ul class="buttons-group">'
										+'<li><a  id="zoom_in'+idx+'"	class="main-btn active-btn btn-hover btn-sm btnzoomin"'
										+'title="Zoom In"><i class="lni lni-zoom-in"></i></a></li>'
										+'<li><a  id="zoom_out'+idx+'"	class="main-btn active-btn btn-hover btn-sm btnzoomout"'
										+'title="Zoom Out"><i class="lni lni-zoom-out"></i></a></li>'
										+'</ul>'
										+'</div>'
										+'<div class="modal-footer-center">'
										+'<ul class="buttons-group" >'
										+'<li class="footer_btn_control"><a id="go_previous'+idx+'" type="button"'
										+'class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i class="lni lni-chevron-left"'
										+'></i>Previous</a></li>'
										+'<li><a type="button" class="main-btn dark-btn n "><span '
										+'class="pdf-page">Page: <span id="page_num'+idx+'">1</span>/ <span id="page_count'+idx+'">'
										+'</span></span></a></li>'
										+'<li><a id="go_next'+idx+'" type="button" '
										+'class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left btnnext">Next'
										+'<i class="lni lni-chevron-right"></i>'
										+'</a></li>'
										+'</ul>'
										+'</div>'
										+'<div class="modal-footer-right">'
										+'<ul class="buttons-group">'
										+'<li><a id="downloadbtn'+idx+'" type="button"'
										+'class="main-btn info-btn btn-hover btn-sm btndownload"><i class="lni lni-download mr-5">'
										+'</i>Download</a></li>'
										+'</ul>'
										+'</div>';


					$("#modal-footer").append(btn);
					 $("#exampleModal").modal('show');
	
	var pdfDoc = null,
    pageNum = 1,
    pageRendering = false,
    pageNumPending = null,
    scale = 1.5,
    canvas = document.getElementById('pdf_renderer'+idx),
    ctx = canvas.getContext('2d');

function renderPage(num) {
  pageRendering = true;
  pdfDoc.getPage(num).then(function(page) {
    var viewport = page.getViewport({scale: scale});
    canvas.height = viewport.height;
    canvas.width = viewport.width;

    var renderContext = {
      canvasContext: ctx,
      viewport: viewport
    };
    var renderTask = page.render(renderContext);

    renderTask.promise.then(function() {
      pageRendering = false;
      if (pageNumPending !== null) {
        renderPage(pageNumPending);
        pageNumPending = null;
      }
    });
  });

  document.getElementById('page_num'+idx).textContent = num;
}


function queueRenderPage(num) {
  if (pageRendering) {
    pageNumPending = num;
  } else {
    renderPage(num);
  }
}

function onPrevPage() {
  if (pageNum <= 1) {
    return;
  }
  pageNum--;
  queueRenderPage(pageNum);
}

document.getElementById('go_previous'+idx).addEventListener('click', onPrevPage);

 
function onNextPage() {
  if (pageNum >= pdfDoc.numPages) {
    return;
  }
  pageNum++;
  queueRenderPage(pageNum);
}
document.getElementById('go_next'+idx).addEventListener('click', onNextPage);

document.getElementById('zoom_in'+idx).addEventListener('click', (e) => {
    if(pdfDoc == null) return;
    if (scale!= 4) {
    scale += 0.5;
   }
    queueRenderPage(pageNum);
});

document.getElementById('zoom_out'+idx).addEventListener('click', (e) => {
	 if(pdfDoc == null) return;
	 
	 if (scale!= 1) {
		 scale -= 0.5;
	}
	    queueRenderPage(pageNum);
});

document.getElementById('downloadbtn'+idx).addEventListener('click', (e) => {
	download_file();
});


 
pdfjsLib.getDocument(path1).promise.then(function(pdfDoc_) {

	 if (pdfDoc) {
         pdfDoc.destroy();
     }
     pdfDoc = pdfDoc_;
  document.getElementById('page_count'+idx).textContent = pdfDoc.numPages;
  renderPage(pageNum);
});
	
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	

	
	
		function download_file() {
			var id = $("#PicturePDFId").val();
			var val= $("#val1").val();
			var fildname= $("#fildname1").val();
			var pdfView="kmlFileDownloadFinalDynemic44?kmlId2="+id+"&val444="+val+"&fildname1="+fildname+"";
			
		    fileName="TEST_DOC";
		    fileURL=pdfView;
		    if (!window.ActiveXObject) {
		        var save = document.createElement('a');
		        save.href = fileURL;
		        save.target = '_blank';
		        var filename = fileURL.substring(fileURL.lastIndexOf('/')+1);
		        save.download = fileName || filename;
			       if ( navigator.userAgent.toLowerCase().match(/(ipad|iphone|safari)/) && navigator.userAgent.search("Chrome") < 0) {
						document.location = save.href; 
					}else{
				        var evt = new MouseEvent('click', {
				            'view': window,
				            'bubbles': true,
				            'cancelable': false
				        });
				        save.dispatchEvent(evt);
				        (window.URL || window.webkitURL).revokeObjectURL(save.href);
					}	
		    }
		    else if ( !! window.ActiveXObject && document.execCommand)     {
		        var _window = window.open(fileURL, '_blank');
		        _window.document.close();
		        _window.document.execCommand('SaveAs', true, fileName || fileURL)
		        _window.close();
		    }
		}
	
	
</script>