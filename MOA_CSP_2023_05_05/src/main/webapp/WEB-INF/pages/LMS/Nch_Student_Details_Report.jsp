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
								<h6 class="mb-25">Uploaded E-Form Student Report</h6>
								<div class="row">

									<!-- 							<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
									<!-- 												<div class="select-style-1"> -->
									<!-- 													<label for="text-input">Degree</label> -->
									<!-- 												<div class="select-position"  id = "div_role">   -->
									<!-- 				 										<select class=" form-control form-control-lg" name="degree" id="degree" > -->
									<!-- 				 											<option value="0">--Select--</option> -->
									<%-- 				 											<c:forEach var="item" items="${getstu_DegreeList}" varStatus="num">  --%>
									<%-- 																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>  --%>
									<%-- 															</c:forEach>  --%>
									<!-- 				 										</select> -->
									<!-- 													</div> -->
									<!-- 												</div>				 -->
									<!-- 								</div> -->
									<!-- 							<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
									<!-- 											<div class="select-style-1"> -->
									<!-- 												<label for="text-input">UG/PG </label> -->
									<!-- 											<div class="select-position">   -->
									<!-- 			 										<select class=" form-control form-control-lg" name="role_type" id="role_type" > -->
									<!-- 			 										<option value="0">--Select--</option> -->
									<!-- 											        	<option value="26">Student</option> -->
									<!-- 											        	<option value="46">Admission - Under-Graduation</option> -->
									<!-- 											        	<option value="44">Admission - Post-Graduation</option> -->
									<!-- 			 										</select> -->
									<!-- 												</div> -->
									<!-- 											</div>				 -->
									<!-- 												<label id = "role_type_lbl" ></label>				 -->
									<!-- 							</div> -->



									<!-- 								getUniverCityList -->

									<%-- 	<div class="col-12 col-sm-12 col-md-6 col-lg-3 custom-d-none" id="system_div">
									<div class="select-style-1">
										<label>System<span
											class="mandatory">*</span></label> 
											<select name="system_id" id="system_id" class="singleselect form-control form-control-lg">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getSystemForAll}"
												varStatus="num">
												<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
											</c:forEach>
										</select>
									</div>
								</div> --%>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3 custom-d-none"
										id="system_div">
										<div class="select-style-1">
											<label for="system_id">System</label>
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
												<select class="select2 form-control form-control-lg"
													name="type_of_degree" id="type_of_degree">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${gettype_of_degree}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3 custom-d-none"
										id="institute_div">
										<div class="select-style-1">
											<label for="name_of_institute">Name of Institute</label>
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
										<div class="input-style-2">
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
											<label for="text-input">NEET Application No.</label> <input
												type="text" id="neet_application_no"
												name="neet_application_no" maxlength="12" autocomplete="off"
												placeholder="Enter NEET Application No">
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">NEET Roll No.</label> <input
												type="text" id="neet_roll_no" name=neet_roll_no
												maxlength="12" autocomplete="off"
												placeholder="Enter NEET Roll No.">
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">ALL INDIA NEET Rank</label> <input
												type="text" id="neet_rank" name="neet_rank" maxlength="7"
												autocomplete="off" placeholder="Enter ALL INDIA NEET Rank">
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">NEET Percentile</label> <input
												type="text" id="neet_percentile" name="neet_percentile"
												maxlength="5" autocomplete="off"
												placeholder="Enter NEET Percentile">
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Marks Obtained</label> <input
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
											<div class="input-style-1 m-0">
												<input type="hidden" id="system_id" name="system_id"
													value="0">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="input-style-1 m-0">
								<input type="hidden" id="system_id" name="system_id" value="0">
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
											<li><a href="NchStudentDetailsReport_Url"
												class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
											<li><a
												class="main-btn success-btn btn-hover btn-iconic-icon btnexcel"
												id="btnExport"><i class="bi bi-file-earmark-excel"
													value="EXCEL" title="Export to EXCEL"></i> EXCEL </a></li>
											<li><a
												class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon btnpdf"
												id="pdfex"><i class="bi bi-file-pdf" id="printId"
													value="PDF" title="Export to PDF"></i> PDF </a></li>
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
								<table class="table" id="Exp_Exl_Nch_table">
									<thead>
										<tr align="center">
											<th><h6>Ser No</h6></th>
											<th><h6>Name of Institute</h6></th>
											<th><h6>Institute Id</h6></th>
											<th><h6>State</h6></th>
											<th><h6>First Name</h6></th>
											<th><h6>Middle Name & Last Name</h6></th>
											<th><h6>Mother's Name</h6></th>
											<th><h6>Father's Name</h6></th>
											<th><h6>Date Of Birth</h6></th>
											<th><h6>Email Id</h6></th>
											<th><h6>NEET Application No</h6></th>
											<th><h6>NEET Roll No</h6></th>
											<th><h6>ALL INDIA NEET Rank</h6></th>
											<th><h6>NEET Percentile</h6></th>
											<th><h6>Marks Obtained</h6></th>
											<th><h6>Quota</h6></th>
											<th><h6>Counselling Authority</h6></th>
											<th><h6>Category</h6></th>
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
				</div>
			</section>
			<!-- 	end col -->
		</div>
	</div>

</section>
<c:url value="getstudentdetails_NCH_Report_Excel" var="searchUrl" />
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
	<input type="hidden" name="system1" id="system1" value="0" />
	<input type="hidden" name="type_of_degree1" id="type_of_degree1"
		value="0" />
</form:form>

<c:url value="Admission_Student_Details_NCH_PDF" var="searchUrl1" />
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
	<input type="hidden" name="system2" id="system2" value="0" />
	<input type="hidden" name="type_of_degree2" id="type_of_degree2"
		value="0" />
</form:form>


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		var role = '${roleid}';
		if (role == '19') { /// uni
			$("#university_id").val('${uni_id}');
			$("#institute_div").show();
			get_inst_by_uni_nch('${uni_id}');
		}
		if (role == '21') { ///inst
			$("#university_id").val('${uni_id}');
			$("#name_of_institute").val('${inst_id}');
		}

		if (role == '65' || role == '17') {// HOM_BOARD or NCH
			$("#system_id").val('45');
			$("#institute_div").show();
			$("#university_div").show();
		}

		// 		$("#system_div").show();
		// 		$("#university_div").show();
		// 		$("#institute_div").show();

		mockjax1('Exp_Exl_Nch_table');
		table = dataTable('Exp_Exl_Nch_table');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});

		datepicketDate('dob');
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

		$.post("getFilterStudent_data?" + key + "=" + value, {
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
			type_of_degree : type_of_degree

		}, function(j) {
			if (j.length == 0) {
				$("#jlength").val('1');
			}

			for (var i = 0; i < j.length; i++) {

				jsondata.push([ j[i][0], j[i][1], j[i][2], j[i][3], j[i][4],
						j[i][5], j[i][6], j[i][7], j[i][8], j[i][9], j[i][10],
						j[i][11], j[i][12], j[i][13], j[i][14], j[i][15],
						j[i][16], j[i][17] ]);
			}
		});

		$.post("getTotalStudent_dataCount?" + key + "=" + value, {

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
			type_of_degree : type_of_degree

		}, function(j) {

			FilteredRecords = j;
		});
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

		// 		document.getElementById('admission_date').onclick = function() {
		// 			clickclear(this, 'DD/MM/YYYY');
		// 		};

		// 		document.getElementById('admission_date').onfocus = function() {
		// 			this.style.color = '#000000';
		// 		};

		// 		document.getElementById('admission_date').onblur = function() {
		// 			clickrecall(this, 'DD/MM/YYYY');
		// 			validateDate_BackDate(this.value, this);
		// 		};

		// 		document.getElementById('admission_date').onkeyup = function() {
		// 			clickclear(this, 'DD/MM/YYYY');
		// 		};
		// 		document.getElementById('admission_date').onchange = function() {
		// 			clickrecall(this, 'DD/MM/YYYY');
		// 			validateDate_FutureDate(this.value, this);
		// 		};
		// 		document.getElementById('system').onchange = function() {
		// 			getdegreelistbysystem();
		// 		};
		document.getElementById('university_id').onchange = function() {
			get_inst_by_uni_nch(this.value);
		};

		// 		document.getElementById('upload_date').onclick = function() {
		// 			clickclear(this, 'DD/MM/YYYY');
		// 		};

		// 		document.getElementById('upload_date').onfocus = function() {
		// 			this.style.color = '#000000';
		// 		};

		// 		document.getElementById('upload_date').onblur = function() {
		// 			clickrecall(this, 'DD/MM/YYYY');
		// 			validateDate_BackDate(this.value, this);
		// 		};

		// 		document.getElementById('upload_date').onkeyup = function() {
		// 			clickclear(this, 'DD/MM/YYYY');
		// 		};
		// 		document.getElementById('upload_date').onchange = function() {
		// 			clickrecall(this, 'DD/MM/YYYY');
		// 			validateDate_FutureDate(this.value, this);
		// 		};
		// 		document.getElementById('email').onchange = function() {
		// 			checkgmail(this.value);
		// 		};
		// 		document.getElementById('ayush_id').onchange = function() {
		// 			checkLength();
		// 		};
		// 		document.getElementById('name').onchange = function() {
		// 			checkLength();
		// 		};

		// 		document.getElementById('name').onkeypress = function() {
		// 			return onlyAlphabetsStringSpace(event, this);
		// 		};

		// 		document.getElementById('mobile_no').onchange = function() {
		// 			return mobileNumber(this);
		// 		};

		// 		document.getElementById('mobile_no').onkeypress = function() {
		// 			return isNumberKey0to9(event);
		// 		};

		// 		document.getElementById('enrollment_no').onkeypress = function() {
		// 			return isNumberKey0to9(event);
		// 		};

		// 		document.getElementById('enrollment_no').onchange = function() {
		// 			checkLength();
		// 		};
		document.getElementById('btnExport').onclick = function() {
			getstudentdetails_Report_Report_Excel();
		};

		document.getElementById('pdfex').onclick = function() {
			Admission_Student_Details_PDF();
		};

	});

	// end csp

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
		$("#system1").val($("#system_id").val());
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
		$("#system2").val($("#system_id").val());
		$("#neet_application_no2").val($("#neet_application_no").val());
		$("#neet_roll_no2").val($("#neet_roll_no").val());
		$("#neet_rank2").val($("#neet_rank").val());
		$("#neet_percentile2").val($("#neet_percentile").val());
		$("#neet_marks2").val($("#neet_marks").val());
		$("#quota_id2").val($("#quota_id").val());
		$("#counselling_authority2").val($("#counselling_authority").val());
		$("#category_id2").val($("#category_id").val());
		$("#university_id2").val($("#university_id").val());
		$("#type_of_degree2").val($("#type_of_degree").val());

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
</script>