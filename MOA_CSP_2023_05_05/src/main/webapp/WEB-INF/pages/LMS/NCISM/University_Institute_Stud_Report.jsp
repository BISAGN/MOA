<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
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
<script type="text/javascript" src="js\watermark\common.js"></script>


<section class="dashboard-page">
	<div class="container-fluid">

		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Students Course Enrollment Report</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Students
									Course Enrollment Report</li>
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
					<form:form name="CourseContentDetail" id="CourseContentDetail"
						method="post" 
						modelAttribute="CourseContentDetailCMD">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Students Course Enrollment Report</h6>
							<div class="row">

								<div class="col-12 col-sm-12 col-md-6 col-lg-4" id="hide_show">
									<div class="select-style-1">
										<label>College</label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="institute_id" id="institute_id">
												<!-- 										onchange="getDegreeListFromInstitute();" -->
												<option value="0">--Select--</option>
												<%-- 										<c:forEach var="item" items="${getSystemList}" varStatus="num"> --%>
												<%-- 											<option value="${item.id}" name="${item.id}">${item.system_name}</option> --%>
												<%-- 										</c:forEach> --%>
											</select>
										</div>
									</div>

									<!-- end select -->
								</div>



								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Degree</label>

										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="degree_id" id="degree_id">
												<!-- 										onchange="getCoursesListFromDegree();" -->
												<option value="0">--Select--</option>
											</select>
										</div>
										<div class="input-style-1 mt-3">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
										</div>
									</div>

									<!-- end select -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Course</label>

										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="course_id" id="course_id">
												<!-- 										onchange="getModuleListFromCourses();" -->
												<option value="0">--Select--</option>
											</select>
										</div>
										<div class="input-style-1 mt-3">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
										</div>
									</div>

									<!-- end select -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Module</label>

										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="module_id" id="module_id">
												<option value="0">--Select--</option>
											</select>
										</div>
										<div class="input-style-1 mt-3">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
										</div>
									</div>
									<input type="hidden" id="institute_id" name="institute_id"
										value="0" autocomplete="off" />
									<!-- end select -->
								</div>
								</div>


								<!-- 							<div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
								<!-- 								<div class="select-style-1"> -->
								<!-- 									<label>Status</label> -->
								<!-- 									<div class="select-position"> -->
								<!-- 										<select class="singleselect form-control form-control-lg" name="status_id" id="status_id"> -->
								<!-- 											<option value="0">ALL</option> -->
								<!-- 											<option value="1">COMPLETED</option> -->
								<!-- 											<option value="2">NOT COMPLETED</option> -->
								<%-- 									<c:forEach var="item" items="${getSystemList}" varStatus="num"> --%>
								<%-- 											<option value="${item.id}" name="${item.id}">${item.system_name}</option> --%>
								<%-- 									</c:forEach> --%>
								<!-- 										</select> -->
								<!-- 									</div> -->
								<!-- 								</div>								 -->

								<!-- 								end select -->
								<!-- 							</div> -->

							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<!-- <li>
								<a id="btn-save" class="main-btn info-btn btn-hover" type="submit">Save</a>
							</li> -->
											<li><a href="UniversityInstituteStudReportUrl"
												class="main-btn dark-btn btn-hover btnreset" type="button"
												value="Reset">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>


  		<section class="single-detail-block">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="table-wrapper table-responsive custom-datatable-p">
							<table class="table"
								id="search_UniversityInstituteStudReportDetails">
								<thead>
									<tr>
										<th id="1"><h6>Sr No</h6></th>
										<%-- 								<th id="${item.id}">University</th> --%>
										<!-- 								<th>Institute</th> -->
										<th id="2"><h6>Ayush Id</h6></th>
										<th id="3"><h6>Student</h6></th>
										<th id="4"><h6>Mobile No</h6></th>
										<th id="5"><h6>Gender</h6></th>
										<c:if test="${role=='University_NCISM'}">
											<th id="6"><h6>College</h6></th>
										</c:if>
										<th id="7"><h6>Degree</h6></th>
										<th id="8"><h6>Course</h6></th>
										<th id="9"><h6>View marks</h6></th>
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

			<div class="modal fade custom-modal bd-example-modal-lg"
				tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-xl modal-dialog-scrollable">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title" id="myLargeModalLabel">Student
								Report</h3>
							<i data-dismiss="modal" aria-label="Close" class="bi bi-x-lg"></i>
						</div>
						<div class="modal-body custom-modal-table">
							<div class="custom-modal-inner">
								<div class="table-wrapper table-responsive custom-table">
									<table class="table model-table"
										id="University_Institute_Stud_Result_View_Url">
										<thead>
											<tr>
												<th>Sr No</th>
												<th>Course</th>
												<th>Set</th>
												<th>Obtain marks</th>
												<th>Total marks</th>
											</tr>
											<!-- end table row-->
										</thead>
										<tbody id="att_TbbodyNameMed">
										</tbody>
									</table>
									<!-- end table -->
								</div>
							</div>

							<div class="modal-footer">
								<ul class="buttons-group">
									<li><button type="button"
											class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
											data-dismiss="modal" aria-label="Close">Close</button></li>
								</ul>
							</div>

						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- student report modal end -->

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$.ajaxSetup({
			async : false
		});
		
	
		//hide_show
		if(${role=='Institute_NCISM'}){
			$("#hide_show").hide();
		}

		getInstituteListFromUniversity();

		mockjax1('search_UniversityInstituteStudReportDetails');
		table = dataTable('search_UniversityInstituteStudReportDetails');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});

		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
// 		getDegreeListFromInstitute();

	});
	
	
// 	csp start
	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('institute_id').onchange = function() {
			getDegreeListFromInstitute();
		};
		
		document.getElementById('degree_id').onchange = function() {
			getCoursesListFromDegree();
		};
		
		document.getElementById('course_id').onchange = function() {
			getModuleListFromCourses();
		};
	});
	function setTimeLoadForTable(){
		
		document.querySelectorAll('.View_Poppup').forEach((items, index) => {
			items.addEventListener('click', event => {
			
				var val=parseInt(index)+1;
				
				var poppup_Id = document.getElementById('poppup_Id'+val).value;
				var institute_id = document.getElementById('institute_id'+val).value;
				var course_name = document.getElementById('course_name'+val).value;
				var userid = document.getElementById('userid'+val).value;
			
				if (confirm('Are You Sure You Want to View Detail ?')) {
					Pop_Up_History1(poppup_Id,institute_id,course_name,userid);
				} else {
					return false;
				}
			})
		});
		

	}
	
// 	csp end

	function data(search_UniversityInstituteStudReportDetails) {
		jsondata = [];
		var table = $('#' + search_UniversityInstituteStudReportDetails)
				.DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];

		var institute_id = $("#institute_id").val();
		var degree_id = $("#degree_id").val();
		var course_id = $("#course_id").val();
		var module_id = $("#module_id").val();

		$.post("getFilterUniversityInstituteStudReportDetails_data?" + key
				+ "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			institute_id : institute_id,
			degree_id : degree_id,
			course_id : course_id,
			module_id : module_id

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].sr_no, j[i].ayush_id, j[i].name,
						j[i].mobile_no, j[i].gender_name
						<c:if test="${role=='University_NCISM'}">
				,j[i].institute_name
				</c:if>,
						j[i].degree_name, j[i].course_name, j[i].action ]);
			}
		});
		$.post("getTotalUniversityInstituteStudReport_dataCount?" + key + "="
				+ value, {
			Search : Search,
			institute_id : institute_id,
			degree_id : degree_id,
			course_id : course_id,
			module_id : module_id
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	function getInstituteListFromUniversity() {

		var university_id = $("select#university_id").val();

		$
				.post(
						"getInstituteListFromUniversity?" + key + "=" + value,
						{

							university_id : university_id
						},
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#institute_id").html(options);
						});
	}

	function getDegreeListFromInstitute() {

		var institute_id = $("select#institute_id").val();
		$
				.post(
						"getDegreeListFromInstitute?" + key + "=" + value,
						{

							institute_id : institute_id
						},
						function(j) {
							
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#degree_id").html(options);
						});
	}

	function getCoursesListFromDegree() {

		var degree_id = $("select#degree_id").val();

		$
				.post(
						"getCoursesListFromDegree?" + key + "=" + value,
						{

							degree_id : degree_id
						},
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#course_id").html(options);
						});
	}

	function getModuleListFromCourses() {

		var course_id = $("select#course_id").val();

		$
				.post(
						"getModuleListFromCourses?" + key + "=" + value,
						{

							course_id : course_id
						},
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#module_id").html(options);
						});
	}

	// function getDegreeListFromModule() {

	// 	var module_id = $("select#module_id").val();

	// 	$
	// 			.post(
	// 					"getDegreeListFromModule?" + key + "=" + value,
	// 					{

	// 						module_id : module_id
	// 					},
	// 					function(j) {
	// 						var options = '<option value="' + "0" + '">'
	// 								+ "--Select--" + '</option>';
	// 						for (var i = 0; i < j.length; i++) {

	// 							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
	// 									+ j[i][1] + '</option>';
	// 						}
	// 						$("select#degree_id").html(options);
	// 					});
	// }

	//////POPUP HISTORY/////////////////////////////////

	function Pop_Up_History1(id, institute_id, course_name, userid) {
	$('tbody#att_TbbodyNameMed').empty();
		$
				.post(
						"University_Institute_Stud_Result_View_Url?" + key
								+ "=" + value,
						{
							uid : userid
						},
						function(j) {
							for (var i = 0; i < j.length; i++) {
								var ser = parseInt(i) + 1;
								$("tbody#att_TbbodyNameMed")
										.append(
												'<tr id="tr_id_attNameMed'+ser+'"><td class="min-width">'
														+ ser
														+ '</td>'
														+ '<td class="min-width">'
														+ j[i][1]
														+ '</td>'
														+ '<td class="min-width">'
														+ j[i][0]
														+ '</td>'
														+ '<td class="min-width">'
														+ j[i][2]
														+ '</td>'
														+ '<td class="min-width">'
														+ j[i][3]
														+ '</td>'
														+ '</tr>');
							}
						});
	}
</script>