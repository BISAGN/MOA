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
<!-- <script type="text/javascript" src="js\watermark\common.js"></script> -->
<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>View Exam Result</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">View
									Exam Result</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="" id="" action="Students_Marks_ReportAction"
						method="post" commandName="Students_Marks_Report_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">View Exam Result</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Course<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="courses" id="courses">
													<!-- 										onchange="getSetnameFromcourse_name()" -->
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getStu_Marks_Replist}"
														varStatus="num">
														<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Set<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="set" id="set">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getinstitutelist}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
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
												type="button" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="Students_Marks_ReportUrl"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
					
			<section class="single-detail-block">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="table-wrapper table-responsive custom-datatable-p">
							<table class="table" id="getSearch_Stu_Marks">
								<thead>
									<tr>
										<th align="center"><h6>Sr No</h6></th>
										<th align="center"><h6>Course</h6></th>
										<th align="center"><h6>Set</h6></th>
										<th class="action"><h6>View marks</h6></th>
									</tr>
								</thead>
							</table>
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

<div class="modal fade custom-modal bd-example-modal-xl" tabindex="-1"
	role="dialog" aria-labelledby="myLargeModalLabel" id="modelWindow"
	aria-hidden="true">
	<div class="modal-dialog modal-xl modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="myLargeModalLabel">Student
					Marksheet</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
<!-- 				<i data-dismiss="modal" aria-label="Close" data-bs-dismiss="modal" -->
<!-- 					class="bi bi-x-lg"></i> -->
			</div>
			<div class="modal-body custom-modal-table">
				<div class="custom-modal-inner">
					<div class="table-wrapper table-responsive custom-table">
						<table class="table model-table" id="Students_MarksheetUrl">
							<thead>
								<tr>
									<th>Sr No</th>
									<th>Course</th>
									<th>Set</th>
									<th>Module</th>
									<th>Obtain marks</th>
									<th>Total marks</th>
								</tr>
							</thead>
							<tbody id="att_TbbodyNameMed">
							</tbody>
						</table>
					</div>
				</div>
				</div>
				
				<div class="modal-footer">
					<ul class="buttons-group">
						<li><a type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal">Close</a></li>
					</ul>
				</div>
				</div>
				</div>
				</div>
			
<%--  <c:url value="Search_Stu_RegPopupUrl" var="Search_Stu_RegPopupUrl" />  --%>
<%-- <form:form action="${Search_Stu_RegPopupUrl}" method="post" id="studentpopup_Form" --%>
<%--  	name="studentpopup_Form" modelAttribute="id" target="result">  --%>
<!-- 	<input type="hidden" name="popid" id="popid" value="0" />  -->
<%--   </form:form>   --%>




<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {
	
	mockjax1('getSearch_Stu_Marks');
	table = dataTable('getSearch_Stu_Marks');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
});


// csp start

function setTimeLoadForTable(){
	
	document.getElementById('courses').onchange = function() {
		getSetnameFromcourse_name()();
	};
	
	
	document.querySelectorAll('.VIEWdetails').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var course_name = document.getElementById('courses'+val).value;
			var setname = document.getElementById('set'+val).value;
//				var userid = document.getElementById('userid'+val).value;
		
			if (confirm('Are You Sure You Want to View Detail ?')) {
				Pop_Up_History1(course_name,setname);
				
			} else {
				return false;
			}
		})
	});
	
}


function getSetnameFromcourse_name() {
// 	debugger;
	
	var course_name = $("select#courses").val();
	

      $.post("getSetnamedataFromcourse_name?" + key + "=" + value,{course_name : course_name},
					function(j) {
						var options = '<option value="' + "0" + '">'
								+ "ALL" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1]+ '</option>';
						}
						$("select#set").html(options);
					});
}



function data(getSearch_Stu_Marks) {
	jsondata = [];
	var table = $('#' + getSearch_Stu_Marks).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
	var orderType = order[0][1];
	
	var courses = $("#courses").val();
	var set = $("#set").val();
	

	$.post("getFilterSearch_Stu_Marks_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength: pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		
		courses:courses,
		set:set,
		
	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].ser,j[i].course_name,j[i].setname,j[i].action ]);
		}
	});
	$.post("getTotalSearch_Stu_Marks_dataCount?" + key + "=" + value, {
	
		courses:courses,
		set:set,
		
	}, function(j) {
		FilteredRecords = j;
		});
	
	setTimeout(setTimeLoadForTable, 500);
}

	function Pop_Up_History1(course_name, setname) {
		
		
		
		var userid = '${userId}' 
// 		alert(userid    +","+ course_name +","+ setname);
		
		$('tbody#att_TbbodyNameMed').empty();
			$
					.post( 
							"Students_MarksheetUrl?" + key
									+ "=" + value,
							{
								userid : userid,
								course_name:course_name,
								setname:setname
							},
							function(j) {
// 								alert(j)
								for (var i = 0; i < j.length; i++) {
									var ser = parseInt(i) + 1;
									$("tbody#att_TbbodyNameMed")
											.append(
													'<tr id="tr_id_attNameMed'+ser+'"><td class="min-width">'
															+ ser
															+ '</td>'
															+ '<td class="min-width">'
															+ j[i][0]
															+ '</td>'
															+ '<td class="min-width">'
															+ j[i][1]
															+ '</td>'
															+ '<td class="min-width">'
															+ j[i][2]
															+ '</td>'
															+ '<td class="min-width">'
															+ j[i][3]
															+ '</td>'
															+ '<td class="min-width">'
															+ j[i][4]
															+ '</td>'
															+ '</tr>');
								}
							});
			
			  $('#modelWindow').modal('show');
		}
	
</Script>
