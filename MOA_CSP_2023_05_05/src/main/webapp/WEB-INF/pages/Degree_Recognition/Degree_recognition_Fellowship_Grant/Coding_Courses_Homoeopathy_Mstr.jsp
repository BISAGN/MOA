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
<script type="text/javascript" src="js/watermark/common.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Coding Of Courses In Homoeopathy</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Coding
									Of Courses In Homoeopathy</li>
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
					<form:form name="Code_Courses_Hom_Master"
						id="Code_Courses_Hom_Master" action="Code_Courses_Hom_Action"
						method="post" class="form-horizontal"
						modelAttribute="Code_Courses_HomCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Coding Of Courses In Homoeopathy</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Sr.No.<span class="mandatory">*</span></label> <input
											type="text" id="sr_no" name="sr_no"
											class="autocomplete txt-transupp" autocomplete="off"
											maxlength="1000" placeholder="Sr.No." />

									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>QUALIFICATION<span class="mandatory">*</span></label> <input
											type="text" id="qualification" name="qualification"
											class=" form-control txt-transupp" autocomplete="off"
											max="1000" placeholder="Qualification" />
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>CODE<span class="mandatory">*</span></label> <input
											type="text" id="code" name="code"
											class=" form-control txt-transupp" autocomplete="off"
											max="1000" placeholder="Code" />
									</div>
									<!-- end input -->
								</div>

							</div>

							<ul class="buttons-group mainbtn">

								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" value="Save" />
								</li>
								<li><a href="Code_Course_HomUrl"
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
						<table class="table" id="search_code_courses_hom">
							<thead>
								<tr>
									<th><h6>No</h6></th>
									
									<th id="${item.id}"><h6>QUALIFIICATION</h6></th>
									<th id="${item.id}"><h6>CODE</h6></th>
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

<c:url value="getSearch_Code_Courses_Hom_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Code_Courses_Hom_name1">
	<input type="hidden" name="Code_Courses_Hom_name1" id="Code_Courses_Hom_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_Code_Courses_Hom_Mstr_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="Code_Courses_Hom_Mstr_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Code_Courses_Homreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_code_courses_hom');
		table = dataTable('search_code_courses_hom');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
		
		if(window.location.href.includes("msg"))
		{
// 			 if(confirm('${msg}')){
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
// 			 }
		}
	});
	
	function setTimeLoadForTable(){
		
	

// 		document.getElementById('academic_details_name').onkeypress = function() {
// 			return onlyAlphabetsStringSpace(this, event);
// 		};
		/* 
		document.getElementById('refer_code').onkeyup = function() {
			return isNumberKey0to9(event, this);
		}; */
		
		document.querySelectorAll('.ADDSystem').forEach((items, index) => {
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
	
	function data(search_code_courses_hom) {
		
		jsondata = [];
		var table = $('#' + search_code_courses_hom).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var sr_no = $("#sr_no").val();
		var qualification = $("#qualification").val();
		var code = $("#code").val();
		

		$.post("getFilter_coding_courses_hom_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			qualification : qualification,
			code : code

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([j[i].sr_no,j[i].qualification, j[i].code,j[i].action ]);
			}
		});
		$.post("getTotal_coding_courses_hom_dataCount?" + key + "=" + value, {
			Search : Search,
			qualification : qualification,
			code : code
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
	function editData(id) {

		$("#id2").val(id);
		document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function Search() {
		$("#sr_no").val($('#sr_no').val());
		$("#qualification").val($('#qualification').val());
		$("#code").val($('#code').val());
		document.getElementById('searchForm').submit();
	}

	function Validation() {

		if ($("#sr_no").val().trim() == "") {
			alert("Please Enter Sr. No.");
			$("input#sr_no").focus();
			return false;
		}
		
		if ($("input#qualification").val().trim() == "") {
			alert("Please Enter Qualification.");
			$("input#qualification").focus();
			return false;
		}
		
		if ($("input#code").val().trim() == "") {
			alert("Please EnterCode.");
			$("input#code").focus();
			return false;
		}
		
		return true;
	}
	
	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		

		
	});
	
</Script>
