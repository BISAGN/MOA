<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
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
						<h2>Course Content Detail Report</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Course
									Content Detail Report</li>
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
						method="post" class="form-horizontal"
						modelAttribute="CourseContentDetailCMD">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Course Content Detail Report</h6>
							<div class="row">

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label> Type Of Lecture</label>

										<div class="select-position">
											<select name="type_of_content" id="type_of_content">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getTypeOfcontent}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
										<div class="input-style-1 mt-3">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
										</div>
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>System</label>
										<div class="select-position">

											<select name="system_name" id="system_name">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getSystemList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<!-- end select -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Degree</label>

										<div class="select-position">

											<select name="degree" id="degree">
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
										<label>Set</label>
										<div class="select-position">

											<select name="setname" id="setname">
												<option value="0">--Select--</option>
											</select>
										</div>
									</div>

									<!-- end select -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Course</label>

										<div class="select-position">


											<select name="course_name" id="course_name">
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
											<select name="module_name" id="module_name">
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
									<div class="input-style-2">
										<label>Start Date </label> <input type="text"
											name="start_date" id="start_date" maxlength="10"
											class="form-control-sm form-control " aria-required="true"
											autocomplete="off" value="DD/MM/YYYY"
											placeholder="DD/MM/YYYY">
									</div>
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>End Date </label> <input type="text" name="end_date"
											id="end_date" maxlength="10"
											class="form-control-sm form-control" aria-required="true"
											autocomplete="off" value="DD/MM/YYYY"
											placeholder="DD/MM/YYYY">
									</div>
								</div>
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
											<!-- <li>
								<a id="btn-save" class="main-btn info-btn btn-hover btnsave" type="submit">Save</a>
							</li> -->
											<li><a href="CourseContentDetailUrl"
												class="main-btn dark-btn n btn-hover btnreset" type="button"
												value="Reset">Reset</a></li>
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
											<table class="table" id="search_CourseContentDetail">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th id="${item.id}"><h6>System</h6></th>
														<th><h6>Degree</h6></th>
														<th><h6>Type of lecture</h6></th>
														<th><h6>Course</h6></th>
														<th><h6>Set</h6></th>
														<th><h6>Module</h6></th>
														<th><h6>Level of module</h6></th>
														<th><h6>Start date</h6></th>
														<th><h6>End date</h6></th>
														<th><h6>Fee</h6></th>
														<th><h6>Duration</h6></th>
														<th><h6>Course switch duration</h6></th>

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


<c:url value="getSearch_CourseContentDetail_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="CourseContentDetail_name1">
	<input type="hidden" name="CourseContentDetail_name1"
		id="CourseContentDetail_name1" />
</form:form>

<c:url value="Levelreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {
	 $.ajaxSetup({
			async : false
		});
	
	mockjax1('search_CourseContentDetail');
	table = dataTable('search_CourseContentDetail');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$('.UpperClassName').keyup(function() {
	       this.value = this.value.toUpperCase();
	   	});

	datepicketDate('start_date');
	datepicketDate('end_date');
	$( "#start_date").datepicker( "option", "maxDate", null);
	$( "#end_date").datepicker( "option", "maxDate", null);
	
	setTimeout(setTimeLoadForTable, 1000);
	
});

function setTimeLoadForTable(){

	document.getElementById('system_name').onchange = function() {
		getDegreeNamebysystem();
	};
	
	document.getElementById('degree').onchange = function() {
		getSetNamebyDegree();
	};
	
	document.getElementById('setname').onchange = function() {
		getCourseNamebySet();
	};
	
	document.getElementById('course_name').onchange = function() {
		getModuleNamebycourse();
	};
	
	document.getElementById('start_date').onclick = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	
	document.getElementById('start_date').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('start_date').onblur = function() {
		clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('start_date').onkeyup = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('start_date').onchange = function() {
		clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('end_date').onclick = function() {
		 clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('end_date').onfocus = function() {
		 this.style.color='#000000';
	};
	document.getElementById('end_date').onblur = function() {
		clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('end_date').onkeyup = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('end_date').onchange = function() {
		clickrecall(this,'DD/MM/YYYY');
	};

}

function data(search_CourseContentDetail) {
	jsondata = [];
	var table = $('#' + search_CourseContentDetail).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];
	var system_name = $("#system_name").val();
	var degree = $("#degree").val();
	var course_name = $("#course_name").val();
	var setname = $("#setname").val();
	var module_name = $("#module_name").val();
	var start_date = $("#start_date").val();
	var end_date = $("#end_date").val();
	var course_fee = $("#course_fee").val();
	var course_switch_duration = $("#course_switch_duration").val();
	var video_duration = $("#video_duration").val();
	var type_of_content = $("#type_of_content").val();
	var level_of_course = $("#level_of_course").val();
	var level_of_module = $("#level_of_module").val();
	var point = $("#point").val();


	$.post("getFilterCourseContentDetail_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength: pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		
		system_name:system_name,
		degree : degree,
		course_name:course_name,
		setname:setname,
		module_name:module_name,
		start_date:start_date,
		end_date:end_date,
		type_of_content:type_of_content
		
	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].sr_no, j[i].system_name,j[i].degree_name,j[i].type_of_content,j[i].course_name,j[i].setname,j[i].module_name,j[i].level_of_module,j[i].start_date,j[i].end_date,j[i].course_fee
				,j[i].duration,j[i].course_switch_duration]);
		}
	});
	$.post("getTotalCourseContentDetail_dataCount?" + key + "=" + value, {
	
		system_name:system_name,
		degree : degree,
		course_name:course_name,
		setname:setname,
		module_name:module_name,
		start_date:start_date,
		end_date:end_date,
		type_of_content:type_of_content
	}, function(j) {
		
		FilteredRecords = j;

		});
}

document.querySelectorAll('.ADDSystem').forEach((items, index) => {
	items.addEventListener('click', event => {
		debugger;
		var val=parseInt(index)+1;
		
		var hid = document.getElementById('apIdAGE'+val).value;
		if (confirm('Are You Sure You Want to Edit Detail ?')) {
			editData(hid);
		} else {
			return false;
		}
	})
});

	
		function getDegreeNamebysystem() {
		var system_name = $("select#system_name").val();
		$.post(
						"getDegreeListBySystem?" + key + "=" + value,
						{
							system_name : system_name
						},
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#degree").html(options);
						});
	}
		
		
		function getSetNamebyDegree() {

			var system_name = $("select#system_name").val();
			var degree = $("select#degree").val();
			$
					.post(
							"getSetListByDegree?" + key + "=" + value,
							{
								system_name : system_name,
								degree : degree
							},
							function(j) {
								var options = '<option value="' + "0" + '">'
										+ "--Select--" + '</option>';
								for (var i = 0; i < j.length; i++) {

									options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
											+ j[i][1] + '</option>';
								}
								$("select#setname").html(options);
							});
		}
		
		

	function getCourseNamebySet() {

		var system_name = $("select#system_name").val();
		var degree = $("select#degree").val();
		var setname = $("select#setname").val();
		$
				.post(
						"getCourseListBySet?" + key + "=" + value,
						{
							system_name : system_name,
							degree : degree,
							setname : setname
							
						},
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#course_name").html(options);
						});
	}


	function getModuleNamebycourse() {

		var course_name = $("select#course_name").val();
		
		$
				.post(
						"getModuleListByCourse?" + key + "=" + value,
						{
							
							course_name : course_name
						},
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#module_name").html(options);
						});
	}
	
</Script>
