<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
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
					<span id="lbladd"></span>
						<h2>Faculty Subject Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Faculty Subject
									Master</li>
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
					<form:form name="FacultySubjectform" id="FacultySubjectform" action="FacultySubjectAction"
						method="post" class="form-horizontal" modelAttribute="FacultySubjectCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Faculty Subject Master</h6>
							<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Course<strong class="mandatory">*</strong></label>
										<div class="select-position">
											<select name="fac_course_id" id="fac_course_id" class="singleselect form-control form-control-lg" >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getFacultyCourseList}"
													varStatus="num">
													<option value="${item.id}" name="${item.id}">${item.course_name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<!-- end select -->
								</div> 
								
							
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Subject Name<span class="mandatory">*</span></label> <input
											type="text" id="subject_name" name="subject_name"
											class="autocomplete txt-transupp"
											autocomplete="off" maxlength="100" placeholder="Subject Name" />
										
									</div>
									<!-- end input -->
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
									<div class="input-style-2 mt-3">
										<input type="hidden" id="id" name="id" value="0"
											autocomplete="off" />
									</div>

									<!-- end select -->
								</div>

							</div>

							<ul class="buttons-group mainbtn">

								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" value="Save" />
								</li>
								<li><a href="Faculty_Subject_Url"
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
						<table class="table" id="search_subject">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th id="${item.id}"><h6>Course Name</h6></th>
									<th id="${item.id}"><h6>Subject Name</h6></th>
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
</section>



<c:url value="Edit_Faculty_Subject_Mstr_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="Faculty_Subject_Mstr_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Subjectreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_subject');
		table = dataTable('search_subject');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
	/* 	$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		}); */
	});
	
	document.addEventListener('DOMContentLoaded', function () {	

		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		
	 
	 /* 	document.getElementById('subject_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		}; 
		  */
	});
	
	function setTimeLoadForTable(){
		
		document.querySelectorAll('.ADDSubject').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apIdAGE'+val).value;
				var hcou = document.getElementById('apcouAGE'+val).value;
				var hsub = document.getElementById('apsubAGE'+val).value;
				var hstatus = document.getElementById('apstatusAGE'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,hcou,hsub,hstatus);
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
	
	function data(search_subject) {
		
		jsondata = [];
		var table = $('#' + search_subject).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var fac_course_id = $("#fac_course_id").val();
		var subject_name = $("#subject_name").val();
		var status = $("#status").val();

		$.post("getFilterSubject_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			fac_course_id : fac_course_id,
			subject_name : subject_name,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].course_name,j[i].subject_name, j[i].action ]);
			}
		});
		$.post("getTotalSubject_dataCount?" + key + "=" + value, {
			fac_course_id : fac_course_id,
			subject_name : subject_name,
			Search : Search
			
			
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
	function editData(id,fac_course_id,subject_name,status) {
	
		document.getElementById('lbladd').innerHTML = "Update ";
		$("select#fac_course_id").val(fac_course_id);
		$('#fac_course_id').trigger('change');
		$("input#subject_name").val(subject_name);
		$("select#status").val(status);
		$('#status').trigger('change');
		document.getElementById('id').value=id;
	}
	

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function Validation() {

		if ($("#subject_name").val().trim() == "") {
			alert("Please Enter Subject Name.");
			$("input#subject_name").focus();
			return false;
		}
		if ($("#fac_course_id").val().trim() == "0") {
			alert("Please Enter Course.");
			$("input#fac_course_id").focus();
			return false;
		}
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status.");
			$("select#status").focus();
			return false;
		}
	
		return true;
	}

</Script> 