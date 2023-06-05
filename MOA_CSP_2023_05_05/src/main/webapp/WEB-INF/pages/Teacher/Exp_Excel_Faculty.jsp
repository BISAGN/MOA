<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<!-- <script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- <link rel="stylesheet" href="js/InfiniteScroll_old/css/site.css"> -->
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
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>



<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Upload Faculty Data</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Upload
									Student Data</li>
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
					<form:form
						action="Exp_Excel_Faculty_action?${_csrf.parameterName}=${_csrf.token}"
						method="POST" modelAttribute="UserLogin_cmd"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Upload Faculty Data</h6>
								<div class="row">

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="username">Upload File<span
												class="mandatory">*</span></label> <input type="file" accept=".xls"
												id="u_file1" name="u_file1" class="form-control"
												autocomplete="off"> <span class="errorClass"
												id="doc_path_doc1_lbl1">${doc_path_doc1_msg}</span> <span
												class='tikClass' id="doc_path_doc_lbltik1"></span>
										</div>
										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-2">
											<label for="username">Date<span class="mandatory">*</span></label>
											<input type="text" name="upload_date" id="upload_date"
												maxlength="10"
												class="form-control-sm form-control effect-9 "
												aria-required="true" autocomplete="off" value="DD/MM/YYYY">
										</div>
									</div>
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<!-- <li>
							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button"><i class="lni lni-search-alt"></i>search</a>
							</li> -->
											<li><a
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												id="btn-reload" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><input id="btn-save"
												class="main-btn info-btn btn-hover btnsave" type="submit"
												value="Save"></li>
											<li><a href="exp_excel_faculty_url"
												class="main-btn dark-btn btn-hover btnreset" type="button"
												value="Reset">Reset</a></li>
											<li><input type="button" id="btnExport"
												class="main-btn active-btn btn-hover  btn-iconic-icon btnexport"
												value="Export Sample Template Format"></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
						<!-- end card -->
					</form:form>
				</div>
				<section class="single-detail-block">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="card-style mb-30">
								<div class="table-wrapper table-responsive custom-datatable-p"
									id="qualification_tbl_div">
									<table class="table" id="Exp_Exl_Faculty_table">
										<thead>
											<tr>
												<th><h6>Sr No</h6></th>
												<th><h6>Faculty name</h6></th>
												<th><h6>Aadhaar number</h6></th>
												<th><h6>Email</h6></th>
												<th><h6>Mobile no.</h6></th>
												<th><h6>Upload date</h6></th>
												<th><h6>Action</h6></th>

												<!-- 								<th><h6>UG/PG</h6></th> -->

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

		<c:url value="Exp_Excel_Faculty" var="searchUrl" />
		<form:form action="${searchUrl}" method="post" id="search2"
			name="search2" modelAttribute="comd1">
			<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
		</form:form>
		
		
			<c:url value="deleteFac_dtlUrl" var="deleteFacUrl" />
			<form:form action="${deleteFacUrl}" method="post" id="deleteFac" name="deleteFac" modelAttribute="deleteId">
<!-- 				<input type="hidden" name="id6" id="id6" value="0"/>  -->
				<input type="hidden" name="deleteId" id="deleteId" value="0"/> 
			</form:form>
			
			<c:url value="EditFac_dtlUrl" var="EditFac_dtlUrl" />
			<form:form action="${EditFac_dtlUrl}" method="post" id="editFac" name="editFac" modelAttribute="editId">
				<input type="hidden" name="editId" id="editId" value="0"/> 
			</form:form>
		
		
		
	</div>
</section>


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		datepicketDate('upload_date');

		mockjax1('Exp_Exl_Faculty_table');
		table = dataTable('Exp_Exl_Faculty_table');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});

	});

	function getExcel() {

		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('search2').submit();
	}
	
	

	function setTimeLoadForTable() {

		document.getElementById('u_file1').onchange = function() {
			pdfFileSizeValidation(this, this.value, "u_file1", "200kb",
					"doc_path_doc_lbltik1", "doc_path_doc1_lbl1", this.accept)
		};

		document.getElementById('btnExport').onclick = function() {
			getExcel();
		};
		document.getElementById('btn-save').onclick = function() {
			return Validation();	
		};

		document.getElementById('upload_date').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('upload_date').onfocus = function() {
			return this.style.color = '#000000';
		};
// 		document.getElementById('upload_date').onblur = function() {
// 			validateDate_BackDate(this.value, this);
// 			return clickrecall(this, 'DD/MM/YYYY');

// 		};
// 		document.getElementById('upload_date').onkeyup = function() {
// 			return clickclear(this, 'DD/MM/YYYY');
// 		};
		document.getElementById('upload_date').onchange = function() {
			validateDate_FutureDate(this.value, this);
			return clickrecall(this, 'DD/MM/YYYY');

		};
		
// 		document.querySelectorAll('.deleteFaculty').forEach((items, index) => {
// 			items.addEventListener('click', event => {
				
// 				debugger;
// 				var val=parseInt(index)+1;
				
// 				var hid = document.getElementById('deleteFacId'+val).value;
				
// 				if (confirm('Are You Sure You Want to Delete Detail ?')) {
// 					deleteFacData(hid);
// 				} else {
// 					return false;
// 				}
// 			})
// 		});
		
		document.querySelectorAll('.editFaculty').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('editFacId'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editFacData(hid);
				} else {
					return false;
				}
			})
		});

	}
 
	function Validation() {

		if ($("#u_file1").val().trim() == "") {
			alert("Please Upload the File");
			$("input#u_file1").focus();
			return false;
		}
		

		if ($("#upload_date").val() == ""
				|| $("#upload_date").val() == "DD/MM/YYYY") {
			alert("Please Select Date");
			$("#upload_date").focus()
			return false;
		}

		return true;
	}

	function data(Exp_Exl_Faculty_table) {
		jsondata = [];
		var table = $('#' + Exp_Exl_Faculty_table).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var name = $("#name").val();
		var dob = $("#dob").val();
		var aadhar_card = $("#aadhar_card").val();
		var email = $("#email").val();
		var mobile_no = $("#mobile_no").val();
		var role_type = $("#role_type").val();
		var upload_date = $("#upload_date").val();
		// 	alert(role_type);

		$.post("getFilterFaculty_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			name : name,
			dob : dob,
			aadhar_card : aadhar_card,
			email : email,
			mobile_no : mobile_no,
			role_type : role_type,
			upload_date : upload_date

		}, function(j) {
			if (j.length == 0) {
				$("#jlength").val('1');
			}

			for (var i = 0; i < j.length; i++) {

				jsondata.push([ j[i][6], j[i][1], j[i][2], j[i][3], j[i][4],
						j[i][5],j[i][8] ]);

			}
		});

		$.post("getTotalFaculty_dataCount?" + key + "=" + value, {
			Search : Search
		}, function(j) {
			FilteredRecords = j;
		});
		 	setTimeout(setTimeLoadForTable, 1000);
		//		setTimeout(setremovehide, 500);
	}
	
	function editFacData(id){
		$("#editId").val(id);
		document.getElementById('editFac').submit();
	}

	
// 	function deleteFacData(id){
// 		$("#deleteId").val(id);
// 		document.getElementById('deleteFac').submit();
// 	}
	
	
	
	
	
	
	
</script>