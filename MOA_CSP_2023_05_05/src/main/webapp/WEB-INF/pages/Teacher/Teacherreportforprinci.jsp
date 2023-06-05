<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<!-- <script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<script type="text/javascript" src="js/watermark/common.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
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


<section class="dashboard-page search_regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Verify Faculty Details</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Verify Faculty Details</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->

		<div class=" form-elements-wrapper search-regulation-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="" id=""
						action="Search_tea_dtlAction?${_csrf.parameterName}=${_csrf.token}"
						method="post" modelAttribute="Search_tea_dtl_CMD">
						<!-- input style start -->
						<div class="card-style mb-30">
							<div class="custom-field-block">

								<h6 class="mb-25">Verify Faculty Details</h6>
								<div class="row">

									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label>Ayush Id <strong class="mandatory"></strong></label> <input
												type="text" name="ayush_id" id="ayush_id"
												class="form-control" placeholder="Enter Ayush Id">
										</div>
									</div>

									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label>Teacher Code <strong class="mandatory"></strong></label>
											<input type="text" name="teacher_code" id="teacher_code"
												class="form-control" placeholder="Enter Teacher Code"
												maxlength="100">
										</div>
									</div>

									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label>Faculty Name <strong class="mandatory"></strong></label>
											<input type="text" name="first_name" id="first_name"
												class="form-control" placeholder="Enter Faculty Name"
												maxlength="100">
										</div>
									</div>


									<c:if test="${role != 'Faculty_NCH'}">
										<div class="col-lg-4 col-md-6 col-sm-12" id="ins">
											<div class="select-style-1">
												<label>Institute <strong class="mandatory">
												</strong></label>
												<div class="select-position">
													<select name="institute" id="institute"
														class="form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getInsttituteList}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</c:if>
									<c:if test="${role != 'Faculty_NCH'}">
										<div class="col-lg-4 col-md-6 col-sm-12" id="uni">
											<div class="select-style-1">
												<label>University <strong class="mandatory">
												</strong></label>
												<div class="select-position">
													<select name="university" id="university"
														class="form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getUniList}"
															varStatus="num">
															<option value="${item.id}" name="${item.university_name}">${item.university_name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</c:if>

									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="select-style-1">
											<label>Gender <strong class="mandatory"> </strong></label>
											<div class="select-position">
												<select name="gender" id="gender" class="form-control">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getgenderList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>


									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-2">
											<label>Date of Birth<strong class="mandatory"></strong></label>
											<input type="text" id="date_of_birth" name="date_of_birth"
												class="form-control" aria-required="true" autocomplete="off"
												value="DD/MM/YYYY" placeholder="Select Date of Birth">

										</div>
									</div>

									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label>State Registration No <strong
												class="mandatory"></strong></label> <input type="text"
												name="state_reg_no" id="state_reg_no" class="form-control"
												placeholder="Enter State Registration No.">
										</div>
									</div>

									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label>Name of the State Board <strong
												class="mandatory"></strong></label> <input type="text"
												name="state_board_name" id="state_board_name"
												class="form-control"
												placeholder="Enter Name of the State Board" maxlength="100">
										</div>
									</div>

									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-2">
											<label>Date of Registration <strong class="mandatory"></strong></label>
											<input type="text" id="date_of_reg" name="date_of_reg"
												class="form-control" aria-required="true" autocomplete="off"
												value="DD/MM/YYYY" placeholder="Select Date of Registration">

										</div>
									</div>

									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label>Central Registration No<strong
												class="mandatory"></strong></label> <input type="text"
												name="central_reg_no" id="central_reg_no"
												class="form-control"
												placeholder="Enter Central Registration No.">
										</div>
									</div>

									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="select-style-1">
											<label>State Name <strong class="mandatory">
											</strong></label>
											<div class="select-position">
												<select name="per_state" id="per_state" class="form-control">
													<option value="0">--Select State--</option>
													<c:forEach var="item" items="${getMedStateName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="select-style-1">
											<label>District <strong class="mandatory"> </strong></label>
											<div class="select-position">
												<select name="per_district" id="per_district"
													class="form-control">
													<option value="0">--Select District--</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label>City/Village Name<strong class="mandatory">
											</strong></label> <input type="text" name="per_village" id="per_village"
												class="form-control" placeholder="Enter City/Village"
												maxlength="100">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 d-none">
										<div class="input-style-1">
											<label>Year of Experience<strong class="mandatory">
											</strong></label> <input type="text" name="yr_of_exp" id="yr_of_exp"
												class="form-control" placeholder="Enter Year of Experience"
												onkeypress="" maxlength="100">
										</div>
									</div>

									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="select-style-1">
											<label>Status <strong class="mandatory">* </strong></label>
											<div class="select-position">
												<select name="status" id="status" class="form-control">
													<option value="0">Pending</option>
													<option value="1">Approve</option>
													<option value="3">Verify/Enable To Edit</option>
													<option value="4"> Edit</option>
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
											<li><a href="commonDashboard"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i
													class="lni lni-chevron-left"></i>Back</a></li>
											<li><a type="button"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												id="btn-reload" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="Search_teacher_detail_princi_url"
												class="main-btn dark-btn btn-hover btnreset"
												value="Reset">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<!-- Bottom Button End -->
						<section class="single-detail-block">
						<div class="tables-wrapper">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="card-style mb-30">

										<div class="table-wrapper table-responsive custom-datatable-p">
											<table id="getSearch_teacher_dtl" class="table">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th><h6>Ayush ID</h6></th>
														<th><h6>Teacher code</h6></th>
														<th><h6>Faculty name</h6></th>
														<th><h6>Gender</h6></th>
														<th><h6>Date of birth</h6></th>
														<th><h6>State</h6></th>
														<th><h6>District</h6></th>
														<th><h6>City/Village</h6></th>
														<th><h6>State registration no.</h6></th>
														<!--             	<th >NAME OF THE STATE BOARD</th> -->
														<th><h6>Date of registration</h6></th>
														<th><h6>Central registration no.</h6></th>
														<th><h6>Year of experience</h6></th>
														<th><h6>Action</h6></th>

													</tr>
													<!-- end table row-->
												</thead>
												<tbody class="custom-datatablepra">

												</tbody>
											</table>
											<!-- end table -->
										</div>
									</div>
								</div>
							</div>
						</div>
						</section>
						<!-- end card -->
					</form:form>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
	</div>
</section>


<c:url value="Edit_Teacher_dtlUrl" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="delete_Teacher_dtl_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="View_Teacher_dtlUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="id6">
	<input type="hidden" name="id6" id="id6" value="0" />
	<input type="hidden" name="statusview" id="statusview" value="0" />
</form:form>

<c:url value="App_Teacher_dtlUrl" var="App_MedUrl" />
<form:form action="${App_MedUrl}" method="post" id="AppForm"
	name="AppForm" modelAttribute="id7">
	<input type="hidden" name="id7" id="id7" value="0" />
	<input type="hidden" name="status7" id="status7" value="0" />
</form:form>





<!-- accept and reject start-->
<c:url value="faculty_principal_Approve_url"
	var="faculty_principal_Approve_url" />
<form:form action="${faculty_principal_Approve_url}" method="post"
	id="AcceptFaculty" name="AcceptFaculty" modelAttribute="Acceptid">
	<input type="hidden" name="Acceptid" id="Acceptid" value="0" />
</form:form>

<c:url value="faculty_principal_Reject_url"
	var="faculty_principal_Reject_url" />
<form:form action="${faculty_principal_Reject_url}" method="post"
	id="RejectFaculty" name="RejectFaculty" modelAttribute="Rejecttid">
	<input type="hidden" name="Rejectid" id="Rejectid" value="0" />
</form:form>

<c:url value="faculty_principal_approveedit_url"
	var="faculty_principal_approveedit_url" />
<form:form action="${faculty_principal_approveedit_url}" method="post"
	id="appeditFaculty" name="appeditFaculty" modelAttribute="appeditid">
	<input type="hidden" name="appeditid" id="appeditid" value="0" />
</form:form>

<!-- accept and reject end-->




<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	if('${role}'=="University_NCH") {
		
		 $("select#university").val('${logininfo}');
		 getuniversitybyinst() ;
			 //$("#ins").hide();
		 $("#uni").hide();
		
	}else if('${role}'=="Institute_NCH") {
		 $("select#institute").val('${logininfo}');
		 getuniversitybyinst() ;
			 //$("#ins").hide();
			 $("#uni").hide();
		 $("#ins").hide();
	}
	datepicketDate('date_of_reg');
	datepicketDate('date_of_birth');
	
	mockjax1('getSearch_teacher_dtl');
	table = dataTable('getSearch_teacher_dtl');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
});

function data(getSearch_teacher_dtl) {
	
	jsondata = [];
var table = $('#' + getSearch_teacher_dtl).DataTable();
	var info = table.page.info();
//		var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];

	var ayush_id = $("#ayush_id").val();
	var teacher_code = $("#teacher_code").val();
	var first_name = $("#first_name").val();
	var gender = $("#gender").val();
	var date_of_birth = $("#date_of_birth").val();
	var per_state = $("#per_state").val();
	var per_district = $("#per_district").val();
	var per_village = $("#per_village").val();
	var state_reg_no = $("#state_reg_no").val();
	var state_board_name = $("#state_board_name").val();
	var date_of_reg = $("#date_of_reg").val();
	var central_reg_no = $("#central_reg_no").val();
	var yr_of_exp = $("#yr_of_exp").val();
	var status = $("#status").val();
	
	var university='0';
	var institute='0';
	if('${role}'=='NCH'){
		 university=$("select#university").val();
		 institute=$("select#institute").val();
	}
	else if('${role}'=='University_NCH'){
		 university=$("select#university").val();
		 institute=$("select#institute").val();
	}
	
	
	$.post("getFilterTeacher_dtl_princi_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		ayush_id : ayush_id,
		teacher_code : teacher_code,
		first_name : first_name,
		gender : gender,
		date_of_birth : date_of_birth,
		per_state : per_state,per_district : per_district,per_village : per_village,
		state_reg_no : state_reg_no,state_board_name : state_board_name,date_of_reg : date_of_reg,central_reg_no : central_reg_no,
		yr_of_exp : yr_of_exp,institute:institute,university:university,status:status
		
	}, function(j) {
		
		for (var i = 0; i < j.length; i++) {
		//	alert(j[i].principal_status);
		
		var enroll = ""
			if (j[i].state_reg_no !="" && j[i].state_reg_no !=null && j[i].state_reg_no !="null") {
				enroll= j[i].state_reg_no;
			}else {
				enroll="-";
			}
		
		var central_reg = ""
			if (j[i].central_reg_no !="" && j[i].central_reg_no !=null && j[i].central_reg_no !="null") {
				enroll= j[i].central_reg_no;
			}else {
				central_reg="-";
			}
		
		var teacher_code=""
			if (j[i].principal_status == 0 ) {
				teacher_code= "-"
			}else {
				teacher_code=j[i].teacher_code;
			}
		
		var date_of_reg=""
			if (j[i].date_of_reg !="" && j[i].date_of_reg !=null && j[i].date_of_reg !="null") {
				date_of_reg= j[i].date_of_reg;
			}else {
				date_of_reg="-";
			}

		
			jsondata.push([ j[i].ser,j[i].ayush_id,teacher_code,j[i].first_name, j[i].gender,j[i].date_of_birth,j[i].state,j[i].district,j[i].per_village,
				enroll,date_of_reg,central_reg,j[i].yr_of_exp,j[i].action]);
			
		}
	});
	$.post("DataTableTeacher_dtl_principal_DataTotalCount?" + key + "=" + value, {
		Search : Search,
		ayush_id : ayush_id,
		teacher_code : teacher_code,
		first_name : first_name,
		gender : gender,
		date_of_birth : date_of_birth,
		per_state : per_state,per_district : per_district,per_village : per_village,
		state_reg_no : state_reg_no,state_board_name : state_board_name,date_of_reg : date_of_reg,central_reg_no : central_reg_no,
		yr_of_exp : yr_of_exp,institute:institute,university:university,status:status
		
	}, function(j) {
		
		FilteredRecords = j;
		});
	setTimeout(setTimeLoadForTable, 1000);
}

function setTimeLoadForTable() {
	//onkeypress="return onlyAlphaNumeric(event, this)"
	
	document.getElementById('ayush_id').onkeypress = function() {
		return onlyAlphaNumeric(event, this);
	};
	//onkeypress="return onlyAlphabetsStringSpace(event,this)"
	
	document.getElementById('first_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(event,this);
	};
	//"date_of_birth"
	//onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" onchange="clickrecall(this,'DD/MM/YYYY');"
	
	document.getElementById('date_of_birth').onblur = function() {
		clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('date_of_birth').onkeyup = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_birth').onchange = function() {
		clickrecall(this,'DD/MM/YYYY');
	};
	//state_reg_no
	//onkeypress="return onlyAlphaNumeric(event, this)"
	document.getElementById('state_reg_no').onkeypress = function() {
		return onlyAlphaNumeric(event, this);
	};
	//state_board_name
	//onkeypress="return onlyAlphabetsStringSpace(event,this)"
	document.getElementById('state_board_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(event,this);
	};
	//date_of_reg
	//onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" onchange="clickrecall(this,'DD/MM/YYYY');"
	document.getElementById('date_of_reg').onblur = function() {
		clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('date_of_reg').onkeyup = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_reg').onchange = function() {
		clickrecall(this,'DD/MM/YYYY');
	};
	//central_reg_no
	//onkeypress="return onlyAlphaNumeric(event, this)"
	document.getElementById('central_reg_no').onkeypress = function() {
		return onlyAlphaNumeric(event, this);
	};
	//per_village
	//onkeypress="return onlyAlphabetsStringSpace(event,this)"
	document.getElementById('per_village').onkeypress = function() {
		return onlyAlphabetsStringSpace(event,this);
	};
	if('${role}'=='NCH'){
	document.getElementById('institute').onchange = function() {
		
		return getuniversitybyinst();	
	};
	}
document.getElementById('per_state').onchange = function() {
		
		return getDistrict();	
	};
	
	document.querySelectorAll('.viewData').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('viewId'+val).value;
			
			if (confirm('Are You Sure You Want to View Detail ?')) {
				ViewData(hid);
			} else {
				return false;
			}
		})
	});
	
	
	document.querySelectorAll('.approveData').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('approveId'+val).value;
			
			if (confirm('Are You Sure You Want to Approve Detail ?')) {
				Accepturl(hid);
			} else {
				return false;
			}
		})
	});
	
	document.querySelectorAll('.rejectData').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('RejectId'+val).value;
			
			if (confirm('Are You Sure You Want to Enable to Edit Detail ?')) {
				Rejecturl(hid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.EditData').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('editId'+val).value;
			
			if (confirm('Are You Sure You Want to  Edit Detail ?')) {
				debugger;
				AppEditurl(hid);
			} else {
				return false;
			}
		})
	});
	
	
}



function editData(id) {
	
	$("#id2").val(id);
	document.getElementById('updateForm').submit();
}

function deleteData(id) {
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}

function ViewData(id){
	$("#id6").val(id);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
}

function Approved(id){
	$("#id7").val(id);
	$("input#status7").val('${status1}');
	document.getElementById('AppForm').submit();
}

function getDistrict() {
	
	var selval = $("#per_state").val();
	$
			.post(
					"getDistrictUrl?" + key + "=" + value,
					{
						selval : selval
					},
					function(j) {
					
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i].district_id + '" name="'+j[i].district_id+'" >'
									+ j[i].district_name + '</option>';
						}
						$("select#per_district").html(options);
					});
}


function getuniversitybyinst() {
	debugger;
	$.ajaxSetup({
		async : false
	});
	var institute_id = $("select#institute").val();
	
	if(institute_id != "" && institute_id != null){
		 $.post("getuniversitybyinstlist_ctrl?" + key + "=" + value,{institute_id : institute_id},
					function(j) {
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
							+ j[i][1]+ '</option>';
						}
						$("select#university").html(options);
		});
	}
}



function Accepturl(id) {
	
	//alert("id=="+id)
	//debugger;
	$("#Acceptid").val(id);
	document.getElementById('AcceptFaculty').submit();
		}

function Rejecturl(id) {
	$("#Rejectid").val(id);
	document.getElementById('RejectFaculty').submit();
	}
	
	
function AppEditurl(id) {
	debugger;
	$("#appeditid").val(id);
	document.getElementById('appeditFaculty').submit();
	}
	
	
	
	


</script>
