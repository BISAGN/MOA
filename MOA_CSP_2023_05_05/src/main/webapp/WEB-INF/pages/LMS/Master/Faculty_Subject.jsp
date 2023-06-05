<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script> -->
<!-- <script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> -->
<!-- <script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> -->
<!-- <script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script type="text/javascript" src="js\watermark\common.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Faculty Subject</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Faculty Subject
									    </li>
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
				<div class="col-12">
					<!-- input style start -->
					<form:form name="Faculty_Subject_masterUrl" id="Faculty_Subject_masterUrl" action="Faculty_Subject_masterUrlaction"
						method="post" class="form-horizontal" modelAttribute="SystemCMD2" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<h6 class="mb-25">Faculty Subject</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="input-style-2">
										<label>Faculty Subject<span class="mandatory">*</span></label> <input
											type="text" id="Faculty_Subject" name="Faculty_Subject"  maxlength="50"  placeholder="Enter Faculty Subject"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" />
										
									</div>
									<!-- end input -->
								
								</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Faculty Course<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="fac_course_id" id="fac_course_id" class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${course_name}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<!-- end select -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status" class="singleselect form-control form-control-lg">
												<c:forEach var="item" items="${ActiveInActiveList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								

									<!-- end select -->
								</div>
								<input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
								

							<ul class="buttons-group mainbtn">

							<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" onclick="return Validation();" value="Save" />
								</li>
								<li><a href="Faculty_Subject_MasterUrl"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
							</ul>
						</div>
						<!-- end card -->
					</form:form>
			</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_system">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th id="${item.id}"><h6>Subject Name</h6></th>
									<th ><h6>Course Name</h6></th>
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
	</div>
</div>
</section>
<c:url value="getSearch_Faculty_Subject_Master" var="getSearch_Faculty_Subject_Master" />
<form:form action="${getSearch_Faculty_Subject_Master}" method="post" id="searchForm"
	name="searchForm" modelAttribute="searchUrl2">
	<input type="hidden" name="searchUrl2" id="searchUrl2" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_Faculty_Subject_MasterUrl" var="Edit_Faculty_Subject_MasterUrl" />
<form:form action="${Edit_Faculty_Subject_MasterUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3">
</form:form>

<c:url value="delete_Faculty_Subject_Action" var="delete_Faculty_Subject_Action" />
<form:form action="${delete_Faculty_Subject_Action}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4" value="0" />
</form:form>

<c:url value="Faculty_Subject_Masterreport2" var="searchUrlp" />
<form:form action="${searchUrlp}" method="post" id="search2"
	name="searcho" modelAttribute="comdo">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>
<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {

	
	mockjax1('search_system');
	table = dataTable('search_system');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$('.UpperClassName').keyup(function() {
		this.value = this.value.toUpperCase();
	});
});


function setTimeLoadForTable(){
	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	}; 
	document.getElementById('Faculty_Subject').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	}; 
	/* document.getElementById('hostel_address').onkeypress = function() {
		return Validation();
	}; */
	/* document.getElementById('no_of_rooms').onkeypress = function() {
		return Validation();
	}; */
	/* document.getElementById('institute_id').onkeypress = function() {
		return Validation();
	}; */
	document.querySelectorAll('.ADDFaculty_Subject__Master ').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('apIdAGE'+val).value;
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				editData(hid);
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

function data(search_system ) {
	
	jsondata = [];
	var table = $('#' +search_system ).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];
	var Faculty_Subject = $("#Faculty_Subject").val();
	var fac_course_id = $("#fac_course_id").val();
	var status = $("#status").val();
	
// 	if(hostel_name == null || hostel_name == "null"){
// 		hostel_name = ""
// 	}
// 	if(hostel_address == null){
// 		hostel_address = ""
// 	}
// 	if(no_of_rooms == null){
// 		no_of_rooms = ""
// 	}
	
	$.post("getFilterFaculty_Subject_Master_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		Faculty_Subject : Faculty_Subject,
		fac_course_id : fac_course_id,
		status : status
	}, function(j) {
console.log(j)
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].ser, j[i].subject_name,j[i].course_name, j[i].action]);
		}
	});
	$.post("getTotalFaculty_Subject_Master_dataCount?" + key + "=" + value, {
		Search : Search,
		Faculty_Subject  : Faculty_Subject,
		fac_course_id : fac_course_id,
		status : status
	}, function(j) {

		FilteredRecords = j;

	});
	setTimeout(setTimeLoadForTable, 1000);
}


function editData(id) {

	$("#id3").val(id);
	document.getElementById('updateForm').submit();
}

function deleteData(id) {
	
	$("#id4").val(id);
	document.getElementById('deleteForm').submit();
}

function Search() {
	$("#Faculty_Subject1").val($('#Faculty_Subject').val());
	$("#fac_course_id1").val($('#fac_course_id').val());
	$("#status1").val($('#status').val());

	document.getElementById('searchForm').submit();
}
function Validation() {
	if ($("#Faculty_Subject").val() =="" ) {
		alert("Please Enter Faculty Subject.");
		$("input#Faculty_Subject").focus();
		return false;
	}
	if ($("#fac_course_id").val() == "0") {
		alert("Please Select Faculty Course.");
		$("select#fac_course_id").focus();
		return false;
	}
	if ($("select#status").val() == "2") {
		alert("Only Select Active Status.");
		$("select#status").focus();
		return false;
	}
	return true;
} 
</script>