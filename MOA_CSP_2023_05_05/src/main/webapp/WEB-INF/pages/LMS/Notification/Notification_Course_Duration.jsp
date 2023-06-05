<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->



<style>
div#ui-datepicker-div {
	width: min-content !important;
}
div#ui-datepicker-div table thead {
	width: 100% !important;
	background-color: #257758;
}
div#ui-datepicker-div table tbody {
	height: auto !important;
}
.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-all
	{
	background: #198754 !important;
}
.videomodal {
  display: none;
  position: fixed; 
  z-index: 1000; 
  padding-top: 10px;
  left: 0;
  top: 0;
  width: 100%; 
  height: 100%; 
  overflow: auto; 
  background-color: rgb(0,0,0); 
  background-color: rgba(0,0,0,0.4); 
  color: white;
}
.videomodal-content {
    background-color: #113f64;
    margin: auto;
    padding: 20px;
    border: 7px solid #ffffff;
    width: 80%;
    /* height: 100%; */
}

.Vclose {
  color: #dc3545;
  float: right;
  font-size: 28px;
  font-weight: bold;
}
.Vclose:hover,
.Vclose:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

@media (max-width: 1368px){
.videomodal-content {
    width: 65%;
	}
}
</style>

 


<form:form  name="sendNoticourseDuration" id="sendNoticourseDuration" action="CourseAction" method='POST'
	commandName="courseCMD"   enctype="multipart/form-data">
	<div class="container">
		<div class="card">
			<div class="card-header" align="center">
				<h5>
					<span id="lbladd1"></span> SEARCH AND SEND NOTIFICATION FOR COURSE TIMELINE
				</h5>
			</div>
			<div class="card-body card-block">
				<div class="col-12 mb-2">
					<div class="col-6">
						<div class="row form-group">
							<div class="col-12 col-lg-6">
								<label for="text-input" class=" form-control-label">SYSTEM</label>
							</div>
							<div class="col-12 col-lg-6">
								<select name="system" class="form-control"
									id="system" onchange="getDaysofcourseDuration();">
									<option value="0">--Select--</option>
										<c:forEach var="item" items="${getSystemList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
								</select>
							</div>
						</div>
					</div>
					
					<div class="col-6">
						<div class="row form-group">
							<div class="col-12 col-lg-6">
								<label for="text-input" class=" form-control-label">DEGREE
								</label>
							</div>
							<div class="col-12 col-lg-6">
								<select name="degree" class="form-control"
									id="degree">
									<option value="0">--Select--</option>
										<c:forEach var="item" items="${getDegreeList}" varStatus="num">
											<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
										</c:forEach>
								</select>
							</div>
						</div>
					</div>
					
					
					
				</div>
				<br>
				<div class="col-12  mb-2">
					<div class="col-6">
					
						<div class="row form-group">
							<div class="col-12 col-lg-6">
								<label for="text-input" class=" form-control-label">COURSE
								</label>
							</div>
							<div class="col-12 col-lg-6">
<!-- 								<input type="text" id="course" name="course" class="form-control" autocomplete="off"> -->
									<select name="course" class="form-control" id="course" onchange="getDaysofcourseDuration();">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${course_name_list}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					
						<div class="col-6">
						<div class="row form-group">
							<div class="col-12 col-lg-6">
								<label for="text-input" class=" form-control-label">DAYS LEFT</label>
							</div>
							<div class="col-12 col-lg-6">
								<label for="text-input" id="days_left" class=" form-control-label"></label>
							</div>
						</div>
					</div>
					
				</div>
				<br><br>
				
				<div class="col-12  mb-2">
				<div class="col-6">
						<div class="row form-group">
							<div class="col-12 col-lg-6">
								<label for="text-input" class=" form-control-label">AYUSH ID
								</label>
							</div>
							<div class="col-12 col-lg-6">
								<input type="text" id="ayush_id" name="ayush_id" class="form-control" placeholder="Search by Ayush ID" autocomplete="off"> 
							</div>
						</div>
				</div>

					<div class="col-6">
						<div class="row form-group">
							<div class="col-12 col-lg-6">
								<label for="text-input" class=" form-control-label">NAME</label>
							</div>
							<div class="col-12 col-lg-6">
								<input type="text" id="name" name="name" class="form-control" placeholder="Search by Name" autocomplete="off"> 
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="card-footer" align="center">
				<a href="SendNotiCourseDuration" class="btn-clear">Reset</a>
				<input type="submit" class="btn-save" value="Save"
					id="btn-save">
				<button type="button" class="btn-search" id="btn-reload">
					<i class="fa fa-search">SEARCH</i>
				</button>
				<button class="btn-print"><i class="fa fa-send"></i> Send Notification to All</button>
			</div>
			<input type='hidden' id='id' name="id" value='0' /> 
			<input type='hidden' id='video_id' name="video_id" value='0' />
		</div>
	</div>
	</form:form>	


<div class="container">
	<table id="search_NotiCourseData"
		class="display table no-margin table-striped  table-hover  table-bordered">
		<thead>
			<tr>
				<th align="center">SER NO</th>
				<th>STUDENT NAME</th>
				<th>AYUSH ID</th>
				<th>SYSTEM NAME</th>
				<th>DEGREE NAME</th>
				<th>COURSE NAME</th>
				<th>SELECT</th>
				<th>SEND</th>
			</tr>
		</thead>
		<tbody >
		</tbody >
	</table>
</div>
	 
	
<script>
	
	$(document).ready(function() {
		 
		 mockjax1('search_NotiCourseData');
			table = dataTable('search_NotiCourseData');
			$('#btn-reload').on('click', function() {
				table.ajax.reload();
			});
	});

	function data(search_NotiCourseData) {
		jsondata = [];
		var table = $('#' + search_NotiCourseData).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		
		var course_name =  $("#course").val();
		var system_name = $("#system").val();
		var degree_name = $("#degree").val();
		var ayushid = $("#ayush_id").val();
		var name = $("#name").val();
		
		$.post("getFilterNoticourse_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			course : course_name,
			system : system_name,
			degree : degree_name,
			ayushid : ayushid,
			name : name
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ i + 1,j[i].login_name, j[i].ayush_id, j[i].system_name, j[i].degree_name,j[i].course_name,j[i].cb,j[i].sendBtn
					 ]);
			}
		});
		$.post("getTotalNoticourse_dataCount?" + key + "=" + value, {
			Search : Search,course : course_name,system : system_name,degree : degree_name,ayushid : ayushid,name : name
		}, function(j) {
			FilteredRecords = j;
			});
	}
	
	
</script>


<script>

// function Accepturl(id) {
// 	$("#Acceptid").val(id);
// 	document.getElementById('AcceptCourse').submit();
// }

// function Rejecturl(id) {
// 	$("#Rejectid").val(id);
// 	document.getElementById('RejectCourse').submit();
// }


	
	
	function Validation() {
		if ($("#type_of_content").val().trim() == "0") {
			alert("Please Select Type Of Content.");
			$("select#type_of_content").focus();
			return false;
		}
	}
	
	function getDaysofcourseDuration() {
		$("#days_left").text('');
		if($("#system").val() != 0 && $("#course").val() != 0){
			$.post('getDaysofcourseDuration?' + key + "=" + value, {
				system:$("#system").val(),
				course:$("#course").val()
				}).done(function(j){
					if(j != ""){
						$("#days_left").text(j[0][0]);
					}
			});
		}else{
			alert("Please Select System & Course");
		}
	}
	
	
</script>

