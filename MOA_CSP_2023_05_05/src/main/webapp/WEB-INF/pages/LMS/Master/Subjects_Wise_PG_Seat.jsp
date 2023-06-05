<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<section class="dashboard-page">
<div class="container-fluid">

	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="title mb-30">
					<h2><span id="lbladd"></span> Subject wise PG Seat</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item">
								<a href="http://localhost:8024/admin/commonDashboard">Dashboard</a>
							</li>
							<li class="breadcrumb-item active" aria-current="page">Subject wise PG Seat</li>
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
                <form:form name="Subject_Wise_pg_Seat" id="Subject_Wise_pg_Seat" action="SubjectwisepgseatAction" method="post" modelAttribute="SubjectwisepgseatCMD">
				<div class="card-style mb-30">
				<div class="custom-field-block">
					<h6 class="mb-25">Subjects wise PG Seat</h6>
						<div class="row">

							<div class="col-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label>Institute Name<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="institute_id" id="institute_id" class="select2 form-control form-control-lg">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getInstituteList}"
												varStatus="num">
												<option value="${item.id}" name="${item.institute_name}">${item.institute_name}</option>
											</c:forEach>

										</select>
									</div>
								</div>								
								
								<!-- end select -->
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label>Degree<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="degree" id="degree" class="singleselect form-control form-control-lg">
											<option value="0">--Select--</option>
<!-- 											<option value="1">1</option> -->
<!-- 											<option value="2">2</option> -->
<%-- 											<c:forEach var="item" items="${getMedStateName}" --%>
<%-- 												varStatus="num"> --%>
<%-- 												<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 											</c:forEach> --%>
										</select>
										</div>
									</div>								
								
								<!-- end select -->
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label>PG Subject <span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="pg_subject" id="pg_subject" class="singleselect form-control form-control-lg">
											<option value="0">--Select--</option>
<!-- 											<option value="1">1</option> -->
<!-- 											<option value="2">2</option> -->
<%-- 											<c:forEach var="item" items="${getMedStateName}" --%>
<%-- 												varStatus="num"> --%>
<%-- 												<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 											</c:forEach> --%>
										</select>
										</div>
									</div>								
								
								<!-- end select -->
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label>Seat<span class="mandatory">*</span></label> 
									<input type="text" id="seat" name="seat" maxlength="4" autocomplete="off"
											 placeholder="Seat"> 
											<input type="hidden" id="id" name="id" class="form-control" autocomplete="off">
								</div>
								<!-- end input -->
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label>Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status" class="singleselect form-control form-control-lg">

											<c:forEach var="item" items="${getStatusMasterList}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
										</div>
									</div>								
								<input type='hidden' id="eid" name="eid" value="0" />
								<!-- end select -->
							</div>
						</div>					
					</div>
					<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">	
						<ul class="buttons-group mainbtn">
							<li>
							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch" type="button"><i class="lni lni-search-alt"></i>Search</a>
							</li>
							<li><input id="btn-update"
									class="main-btn deactive-btn btn-hover btnupda" type="button"
									value="Update" /></li>
							<li>
								<input id="btn-save" class="main-btn info-btn btn-hover btn-save btnsave" value="Save" type="button">
							</li>
							<li>
								<a href="Subjects_Wise_Pg_Seat_MasterUrl" class="main-btn dark-btn n btn-hover btnreset" type="button">Reset</a>
							</li>
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
						<table class="table" id="Swps_Search">
							<thead>
								<tr>
									<th id = "1" ><h6>Sr No</h6></th>
									<th id = "2"><h6>Institute Name</h6></th>
									<th id = "4"><h6>Degree</h6></th>
									<th id = "6"><h6>PG-Subject</h6></th>
									<th id = "8"><h6>Seats</h6></th>
									<th id = "9"><h6>Action</h6></th>
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

<c:url value="getSearch_sdpcl_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="System_name1">
	<input type="hidden" name="System_name1" id="System_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="EditSubjects_Wise_Pg_Seat_MasterUrl" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>
<c:url value="delete_swps_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Systemreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {
	$("#btn-update").hide();
	$("#btn-reload1").hide();

	mockjax1('Swps_Search');
	table = dataTable('Swps_Search');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$('.UpperClassName').keyup(function() {
		this.value = this.value.toUpperCase();
	});
	if(window.location.href.includes("msg"))
	{
		 var url = window.location.href.split("?msg")[0];
		 window.location = url;
	}
});

function setTimeLoadForTable(){

	
	document.querySelectorAll('.ADDSystem').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdAGE'+val).value;
			var hinstituteid = document.getElementById('hinstituteid'+val).value;
			var hdegree= document.getElementById('hdegree'+val).value;
			var hpdsubject = document.getElementById('hpdsubject'+val).value;
			var hseat = document.getElementById('hseat'+val).value;
			
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				editData(hid,hinstituteid,hdegree,hpdsubject,hseat);
			} else {
				return false;
			}
		})
	});
	
	document.querySelectorAll('.deleteOnclick').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('deleteID'+val).value;
			
			
			if (confirm('Are You Sure You Want to Delete Data ?')) {
				deleteData(hid);
			} else {
				return false;
			}
		})
	});
	
	
}
function data(Swps_Search) {
		
	jsondata = [];
	var table = $('#' + Swps_Search).DataTable();
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
	var degree = $("#degree").val();
	var pg_subject = $("#pg_subject").val();
	var seat = $("#seat").val();
	var status = $("#status").val();

	$.post("getFilteswps_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		institute_id:institute_id,
		degree:degree,
		pg_subject:pg_subject,
		seat:seat,
		status:status
		
	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].ser,j[i].institute_name,j[i].degree_name,j[i].course_name,j[i].seat,j[i].action ]);
		}
	});
	$.post("getFilteswps_dataCount?" + key + "=" + value, {

		Search : Search,
		institute_id:institute_id,
		degree:degree,
		pg_subject:pg_subject,
		seat:seat,
		status:status
	}, function(j) {
		
		FilteredRecords = j;

		});
	setTimeout(setTimeLoadForTable, 1000);
}

function editData(id,institute_id,degree,pg_subject,seat) {
	$("#btn-update").show();
	$("#btn-save").hide();
	$("#tbl").hide();
	$("#btn-reload").hide();
	$("#btn-reload2").hide();
	$("#btn-reload1").show();
	
	
	document.getElementById('lbladd').innerHTML = "Update ";
	$("select#institute_id").val(institute_id);
	$('#institute_id').trigger('change');
	getPGDegreeofInst();
	$("select#degree").val(degree);
	getPGSubjectsofDegree();
	$("select#pg_subject").val(pg_subject);
	$('#pg_subject').trigger('change');
	$("#seat").val(seat);
	
	document.getElementById('eid').value=id;
}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	


function getPGDegreeofInst() {
	var inst_id = $("#institute_id").val();
	$.post('getPGDegreeofInst?' + key + "=" + value,{  
		inst_id : inst_id
		}).done(function(j) {
						var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							options += '<option   value="' + j[i].id + '" name="'+j[i].degree_name+'" >'+ j[i].degree_name + '</option>';
						}
						$("select#degree").html(options);
	});
}
function getPGSubjectsofDegree() {
	var degree = $("#degree").val();
	$.post('getPGSubjectsofDegree?' + key + "=" + value,{  
		degree : degree
		}).done(function(j) {
						var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							options += '<option   value="' + j[i].id + '" name="'+j[i].course_name+'" >'+ j[i].course_name + '</option>';
						}
						$("select#pg_subject").html(options);
	});
}


document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('btn-save').onclick = function() {
		if(Validation()){
			 $("#Subject_Wise_pg_Seat").submit();
		}
	};
	document.getElementById('btn-update').onclick = function() {
		if(Validation()){
			 $("#Subject_Wise_pg_Seat").submit();
		}
	};
	
	document.getElementById('seat').onkeypress = function() {
		return isNumberKey0to9(event);
	};
	document.getElementById('institute_id').onchange = function() {
		getPGDegreeofInst();
	};
	document.getElementById('degree').onchange = function() {
		getPGSubjectsofDegree();
	};

});

function Validation() {
	debugger;
	if ($("#institute_id").val().trim() == "0") {
		alert("Please Select Institute Name.");
		$("select#institute_id").focus();
		return false;
	}
	if ($("#degree").val().trim() == "0") {
		alert("Please Select Degree.");
		$("select#degree").focus();
		return false;
	}
	if ($("#pg_subject").val().trim() == "0") {
		alert("Please Select PG Subject.");
		$("select#pg_subject").focus();
		return false;
	}
     if ($("#seat").val().trim() == "") {
			alert("Please Enter Seat.");
			$("input#seat").focus();
			return false;
	}
	if ($("select#status").val() == "0") {
		alert("Please Select Status.");
		$("select#status").focus();
		return false;
	}
	if ($("select#status").val() == "2") {
		alert("Only Select Active Status");
		$("select#status").focus();
		return false;
	}
	return true;
 }

</script>