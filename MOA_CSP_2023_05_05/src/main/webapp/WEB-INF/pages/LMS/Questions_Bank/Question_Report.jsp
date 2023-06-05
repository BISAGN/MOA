<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
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

<script type="text/javascript" src="js\watermark\common.js"></script>
<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Question Report</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Question
									Report</li>
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
					<form:form name="question_report" id="question_report"
						action="question_reportAction" method="post"
						modelAttribute="QuestionReportCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Question Report</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>Course<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="course_id" id="course_id">
													<!-- 											onchange="getcourselistbyset2();" -->
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getCourse_upload_Paper}"
														varStatus="num">
														<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>Set<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="set_id" id="set_id">
													<!-- 											onchange="getcourselistbymodule2();" -->
													<option value="0">--Select--</option>
													<%-- 												<c:forEach var="item" items="${s_name}" varStatus="num"> --%>
													<%-- 													<option value="${item.id}" name="${item.system_name}">${item.system_name}</option> --%>
													<%-- 												</c:forEach> --%>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>Module<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="module_id" id="module_id">
													<option value="0">--Select--</option>
													<%-- 												<c:forEach var="item" items="${s_name}" varStatus="num"> --%>
													<%-- 													<option value="${item.id}" name="${item.system_name}">${item.system_name}</option> --%>
													<%-- 												</c:forEach> --%>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>

							<!-- end select -->
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a href="Upload_Paper_url"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
													<i class="lni lni-chevron-left"></i>Back
											</a></li>
											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											
											<li><a href="Question_ReportUrl"
												class="main-btn dark-btn btn-hover btnreset" type="button"
												value="Reset">Reset</a></li>
											

										</ul>
									</div>
								</div>
							</div>

						</div>
			<section class="single-detail-block">		
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div class="card-style mb-30">
				<div class="table-wrapper table-responsive custom-datatable-p">
					<table class="table" id="search_system">
						<thead>
							<tr>
								<th><h6>Sr No</h6></th>
								<th id="${item.id}"><h6>Questions</h6></th>
								<th><h6>Option A</h6></th>
								<th><h6>Option B</h6></th>
								<th><h6>Option C</h6></th>
								<th><h6>Option D</h6></th>
								<th><h6>Correct Answer</h6></th>
								<!-- 									<th><h6>Difficulty Level</h6></th> -->
								<th><h6>Marks</h6></th>
								<th><h6>Time</h6></th>
								<th><h6>Exam name</h6></th>


							</tr>
							<!-- 						end table row -->
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
<c:url value="getSearch_Question_Report" var="getSearch_Question_Report" />
<form:form action="${getSearch_Question_Report}" method="post"
	id="searchForm" name="searchForm" modelAttribute="searchUrl2">
	<input type="hidden" name="searchUrl2" id="searchUrl2" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>



<c:url value="Question_Report2" var="Student_Room_Requestreport2" />
<form:form action="${Student_Room_Requestreport2}" method="post"
	id="search2" name="searcho" modelAttribute="comdo">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>


<script nonce="${cspNonce}" type="text/javascript">
	// 	document.getElementById('btn-save').onclick = function() {
	// 		return Validation();
	// 	};
	/* document.getElementById('hostel_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	 document.getElementById('room_no').onkeypress = function() {
		return Validation();
	}; 
	 document.getElementById('no_of_rooms').onkeypress = function() {
		return Validation();
	}; 
	 document.getElementById('institute_id').onkeypress = function() {
		return Validation();
	};  */

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('course_id').onchange = function() {
			return getcourselistbyset2();
		};
		document.getElementById('set_id').onchange = function() {
			return getcourselistbymodule2();
		};

		// 		document.getElementById('save').onclick = function() {
		// 			 return getQuizList();
		// 		};
	});
	$(document).ready(function() {
		mockjax1('search_system');
		table = dataTable('search_system');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		if (window.location.href.includes("msg")) {
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		}
	});

	function getExcel2() {

		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('search2').submit();
	}

	function getcourselistbyset2() {
		var course_id = $("#course_id").val();

		$
				.post('getcourselistby_set2?' + key + "=" + value, {
					course_id : course_id
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#set_id").html(options);
						});
	}

	function getcourselistbymodule2() {
		var course_id = $("#course_id").val();

		$
				.post('getcourselistby_module2?' + key + "=" + value, {
					course_id : course_id
				})
				.done(
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

	function data(search_system) {

		jsondata = [];
		var table = $('#' + search_system).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];

		var course_id = $("#course_id").val();
		var set_id = $("#set_id").val();
		var module_id = $("#module_id").val();

		$.post("getFilterQuestion_Report_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			course_id : course_id,
			set_id : set_id,
			module_id : module_id

		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				var options = (j[i].answer).split(",");
				jsondata.push([ j[i].ser, j[i].question, options[0],
						options[1], options[2], options[3], j[i].correct_ans,
						j[i].marks, j[i].time, j[i].exam_name ]);
			}
		});

		$.post("getTotalQuestion_Report_dataCount?" + key + "=" + value, {
			Search : Search,
			course_id : course_id,
			set_id : set_id,
			module_id : module_id

		}, function(j) {

			FilteredRecords = j;

		});
		//  	setTimeout(setTimeLoadForTable, 1000);
	}

	function Search() {
		$("#course_id1").val($('#course_id').val());
		$("#set_id1").val($('#set_id').val());
		/* 	$("#rent1").val($('#rent').val());
		 */$("#module_id1").val($('#module_id').val());

		document.getElementById('searchForm').submit();
	}
	function Validation() {
		if ($("#course_id").val() == "") {
			alert("Please Enter Hostel Id.");
			$("input#course_id").focus();
			return false;
		}

		if ($("#set_id").val() == "") {
			alert("Please Enter Amenities.");
			$("input#set_id").focus();
			return false;
		}
		/* f ($("#rent").val() =="" ) {
			alert("Please Enter No Of Beds.");
			$("input#no_of_beds").focus();
			return false;
		} */
		if ($("#module_id").val() == "") {
			alert("Please Enter Registration No.");
			$("input#module_id").focus();
			return false;
		}

	}
</script>
