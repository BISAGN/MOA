<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- <link href="js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="js/dropDown/select2.min.js" type="text/javascript"></script> -->
<!-- datatable style and js end-->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd"></span>Faculty To Subject Master
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">Faculty
									To Subject Master</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form action="Faculty_to_Course_Master_action" method="POST"
						modelAttribute="Faculty_to_Course_Master_cmd">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<!-- <h6 class="mb-25">Degree</h6> -->
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="username">Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="form-control form-control-lg"
													name="degree" id="degree">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getDegreeList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.degree_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										
									<div class="input-style-1">
										<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
                               	 	</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Professional<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="form-control form-control-lg"
													name="professional" id="professional">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getProfessionalList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.professional}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="username">Subject<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="course" id="course"
													class="form-control form-control-lg">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getCourseList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.course_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									<div class="input-style-1">
								         <input type="hidden" id="id" name="id" value="0" autocomplete="off" />
                                    </div>
                                    
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="username">Faculty<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="faculty" id="faculty"
													class="select2 form-control form-control-lg">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getFacultyList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									<div class="input-style-1">
										<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
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
											<li><input id="btn-save"
												class="main-btn info-btn btn-hover btnsave" type="submit"
												value="Save"></li>
											<li><a href="Faculty_to_CourseUrl"
												class="main-btn dark-btn n btn-hover btnreset" type="button">Reset</a></li>
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
							<table class="table" id="search_faculty_to_course_master">
								<thead>
									<tr>
										<th><h6>Sr No</h6></th>
										<th><h6>Degree</h6></th>
										<th><h6>Professional</h6></th>
										<th><h6>Subject</h6></th>
										<th><h6>Faculty</h6></th>
										<th><h6>Action</h6></th>
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
		</div>
	</div>
</section>
<c:url value="getSearch_Faculty_to_Course_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="System_name1">
	<input type="hidden" name="System_name1" id="System_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>
<c:url value="Edit_Faculty_to_CourseUrl" var="Edit_Faculty_to_CourseUrl" />
<form:form action="${Edit_Faculty_to_CourseUrl}" method="post"
	id="updateForm" name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>

<c:url value="Delete_Url2" var="Delete_Url2" />
<form:form action="${Delete_Url2}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id7">
	<input type="hidden" name="id7" id="id7" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		mockjax1('search_faculty_to_course_master');
		table = dataTable('search_faculty_to_course_master');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		getAprrovedDegree('${system[0][1]}');
	});
	
function setTimeLoadForTable(){	
	
	document.querySelectorAll('.editOnclick').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('apIdAGE'+val).value;
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				EditData(hid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.deleteOnclick').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('deleteID'+val).value;
			
			if (confirm('Are You Sure You Want to Delete Detail ?')) {
				deleteData(hid);
			} else {
				return false;
			}
		})
	});
	
	
}

document.addEventListener('DOMContentLoaded', function () {	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	
	document.getElementById('degree').onkeypress = function() {
		return isNumberKey0to9(event);
	};
	
	document.getElementById('professional').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	
	document.getElementById('professional').onchange = function() {
		getcoursedtl();
	};
	
	document.getElementById('course').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	
	document.getElementById('faculty').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	});
		
	function data(search_faculty_to_course_master) {
		jsondata = [];
		var table = $('#' + search_faculty_to_course_master).DataTable();
		var info = table.page.info();
		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var degree = $("#degree").val();
		var professional = $("#professional").val();
		var course = $("#course").val();
		var faculty = $("#faculty").val();

		$.post("getFilterFacultytoCourse_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			degree : degree,
			professional : professional,
			course : course,
			faculty : faculty
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].degree_name, j[i].professional, j[i].course_name, j[i].faculty_name, j[i].action ]);
			}
		});
		$.post("getTotalFacultytoCourse_dataCount?" + key + "=" + value, {
			Search : Search,
			degree : degree,
			professional : professional,
			course : course,
			faculty : faculty
		}, function(j) {
			FilteredRecords = j;
		});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
	function EditData(id) {
		$("#id2").val(id);
		document.getElementById('updateForm').submit();
	}
	
	function deleteData(id) {
		$("#id7").val(id);
		document.getElementById('deleteForm').submit();
	}
	
  	function Validation() {
  		
  		if ($("select#degree").val().trim() == "0") {
			alert("Please Select Degree.");
			$("Select#degree").focus();
			return false;
		}
  		
  		if ($("select#professional").val().trim() == "0") {
			alert("Please Select Professional.");
			$("Select#professional").focus();
			return false;
		}
  		if ($("select#course").val().trim() == "0") {
			alert("Please Select Course.");
			$("Select#course").focus();
			return false;
		}
  		if ($("select#faculty").val().trim() == "0") {
			alert("Please Select Faculty.");
			$("Select#faculty").focus();
			return false;
		}
		
		if ($("#course").val().trim() == "") {
			alert("Please Enter Course");
			$("input#course").focus();
			return false;
		}
		if ($("#faculty").val().trim() == "") {
			alert("Please Enter Faculty");
			$("input#faculty").focus();
			return false;
		}
		
		return true;
	}
  	
  	
  	function getcoursedtl(){
  		
  		var degree_id = $("#degree").val();
  		var professional_id = $("#professional").val();
  		
  		if ($("#degree").val().trim() == 0){
  			alert("please select degree");
  			$("select#degree").focus();
  			 $("#professional").val("0");
  		}
  			
  		
  		$.post("getCourseList_for_Curri?" + key + "=" + value, {
  			
  			degree_id : degree_id,
  			professional_id : professional_id
  			
		}, function(j) {
			var options = '<option value="' + "0" + '">'
					+ "--Select--" + '</option>';
			for (var i = 0; i < j.length; i++) {

				options += '<option   value="' + j[i][0] + '" name="'+j[i][0]+'" >'
						+ j[i][1] + '</option>';
			}
			$("select#course").html(options);
		});
  	}
  	
  	function getFacultyDtl(){
  		var course_id = $("#course").val();

  		$.post("getFacutlyDetails?" + key + "=" + value, {
  			course_id : course_id
		}, function(j) {
			var options = '<option value="' + "0" + '">'
					+ "--Select--" + '</option>';
			for (var i = 0; i < j.length; i++) {

				options += '<option   value="' + j[i][0] + '" name="'+j[i][0]+'" >'
						+ j[i][1] + '</option>';
			}
			$("select#faculty").html(options);
		});
  	}
  	
function getAprrovedDegree(id) {
		
// 		var selval = $("#system_hid").val();
		var selval = id;
		$.post("getdegrebysysidlist?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {
							if(j.length>0){
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
  	
</script>