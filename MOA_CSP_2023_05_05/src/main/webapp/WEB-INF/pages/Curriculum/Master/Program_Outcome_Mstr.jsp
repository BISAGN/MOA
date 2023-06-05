<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">

						<h2>
							<span id="lbladd"></span> Add Program Outcome
						</h2>
					</div>
					<!-- end col -->
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Add
									Program Outcome</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
		</div>
		<!-- title-wrapper end -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="Program_Outcomeform" id="Program_Outcomeform"
						action="Program_OutcomeAction" method="post"
						 modelAttribute="Program_OutcomeCMD">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Add Program Outcome</h6>
							<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="text-input">System<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="system_id" id="system_id">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getSystemList}"
													varStatus="num">
													<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="text-input">Degree<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="degree_id" id="degree_id" class="form-control">
												<option value="0">--Select--</option>
											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="text-input">Professional<span
											class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="professional_id" id="professional_id">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getprofessionalList}"
													varStatus="num">
													<option value="${item.id}" name="${item.professional}">${item.professional}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Subject<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="course_id" id="course_id">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${CourseList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<!-- 									<div class="input-style-1 mt-3"> -->
									<!-- 										<input type="hidden" id="id" name="id" value="0" -->
									<!-- 											autocomplete="off" /> -->
									<!-- 									</div> -->

									<!-- end select -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Code<span class="mandatory">*</span></label> <input
											type="text" id="code" name="code"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="5" placeholder="Code" />
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Program Outcome<span class="mandatory">*</span></label>
										<input type="text" id="program_outcome" name="program_outcome"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="500"
											placeholder="Program Outcome" />
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Status</label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="status" id="status">
												<c:forEach var="item" items="${ActiveInActiveList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
											<div class="input-style-1 mt-3">
										<input type="hidden" id="id" name="id" value="0"
											autocomplete="off" />
									</div>
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
							<li id="btn-reload1">
						           <a  href="Program_Outcome_Mstr_Url" class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i class="lni lni-chevron-left"></i>Back</a>
						        </li>
						        
						        <li id="btn-update"><input 
									class="main-btn deactive-btn btn-hover btnupda" type="button"
									value="Update" /></li>
									
								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover btnsave" type="button" value="Save" />
								
								<li  id="btn-reload2"><a href="Program_Outcome_Mstr_Url"
									class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
									
								
							</ul>
							</div>
							</div>
							</div>
						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>


		<section class="single-detail-block">
		<div class="row" id="tbl">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_program_outcome">
							<thead>
								<tr>
									<th id="1"><h6>Ser No</h6></th>
									<th id="6"><h6>System</h6></th>
									<th id="4"><h6>Degree</h6></th>
									<th id="5"><h6>Professional</h6></th>
									<th id="3"><h6>Subject</h6></th>
									<th id="3"><h6>Code</h6></th>
									<th id="4"><h6>Program Outcome</h6></th>
									<th><h6>Action</h6></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		</section>
		</div>
	</div>
</section>

<c:url value="getSearch_Program_Outcome_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Program_Outcome_name1">
	<input type="hidden" name="Program_Outcome_name1" id="Program_Outcome_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Program_Outcome_Mstr_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Program_Outcomereport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$("#btn-update").hide();
		$("#btn-reload1").hide();

		mockjax1('search_program_outcome');
		table = dataTable('search_program_outcome');
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
	
	document.addEventListener('DOMContentLoaded', function () {	
		document.getElementById('btn-save').onclick = function() {
			if(Validation()){
				$("#Program_Outcomeform").submit();
			}
		};
		document.getElementById('btn-update').onclick = function() {
			if(Validation()){
				$("#Program_Outcomeform").submit();
			}
		};
		document.getElementById('code').onkeypress = function() {
	 		return OnlyAlphaNumericTrim(event, this);
		};
		document.getElementById('program_outcome').onkeypress = function() {
	 		return noSpace(event,this);
		};
		document.getElementById('system_id').onchange = function() {
			getdegreelistbysystem111();
		};
		document.getElementById('professional_id').onchange = function() {
			getcourselistby_professional();
		};
	});
	
	function setTimeLoadForTable(){
		document.querySelectorAll('.ADDProgram_Outcome').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				var hid = document.getElementById('apIdAGE'+val).value;
				var hpsys = document.getElementById('approfsys'+val).value;
				var hprodegree= document.getElementById('hprodegree'+val).value;
				var hidprofessional= document.getElementById('hidprofessional'+val).value;
				var hcourse = document.getElementById('apcoofCO'+val).value;
				var hcode = document.getElementById('apcodeAGE'+val).value;
				var hprof = document.getElementById('approfAGE'+val).value;
				var hstatus = document.getElementById('apstatusAGE'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,hpsys,hprodegree,hidprofessional,hcourse,hcode,hprof,hstatus);
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
	
	function data(search_program_outcome) {
		
		jsondata = [];
		var table = $('#' + search_program_outcome).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr('id')
				.toLowerCase();
		var orderType = order[0][1];
		var system_id = $("#system_id").val();
		var code = $("#code").val();
		var program_outcome = $("#program_outcome").val();
		var status = $("#status").val();
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();
		var course_id = $("#course_id").val();

		$.post("getFilterProgram_Outcome_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			system_id:system_id,
			code:code,
			program_outcome : program_outcome,
			status : status,
			degree_id : degree_id ,
			professional_id : professional_id,
			course_id : course_id
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser,j[i].system_name,j[i].degree_name,j[i].professional,j[i].course_name,j[i].code, j[i].program_outcome, j[i].action ]);
			}
		});
		$.post("getTotalProgram_Outcome_dataCount?" + key + "=" + value, {
			Search : Search,
			system_id:system_id,
			code:code,
			program_outcome : program_outcome,
			status : status,
			degree_id : degree_id ,
			professional_id : professional_id,
			course_id : course_id
		}, function(j) {
			FilteredRecords = j;
		});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
	function editData(id,system_id,degree_id,professional_id,course_id,code,program_outcome,status) {
		$("#btn-update").show();
		$("#btn-save").hide();
		$("#tbl").hide();
		$("#btn-reload").hide();
		$("#btn-reload2").hide();
		$("#btn-reload1").show();
		
		document.getElementById('lbladd').innerHTML = "Update ";
		$("select#system_id").val(system_id);
		$('#system_id').trigger('change');
		getdegreelistbysystem111();
		$("select#degree_id").val(degree_id);
		$('#degree_id').trigger('change');
		$("select#professional_id").val(professional_id);
		$('#professional_id').trigger('change');
		$("select#course_id").val(course_id);
		$('#course_id').trigger('change');
		$("input#code").val(code);
		$("input#program_outcome").val(program_outcome);
		$("select#status").val(status);
		document.getElementById('id').value=id;
	}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}

	function Validation() {
		if($("#system_id").val() == "0" || $("#system_id").val() == ""){
			alert("Please Select System.");
			$("#system_id").focus();
			return false;
		}
		if ($("#degree_id").val().trim() == "0") {
			alert("Please Select Degree.");
			$("select#degree_id").focus();
			return false;
		}
		if ($("#professional_id").val().trim() == "0") {
			alert("Please Select Professional.");
			$("select#professional_id").focus();
			return false;
		}
		if ($("#course_id").val().trim() == "0") {
			alert("Please Select Subject.");
			$("select#course_id").focus();
			return false;
		}
		if ($("#code").val().trim() == "") {
			alert("Please Enter Code.");
			$("input#code").focus();
			return false;
		}
		if ($("#program_outcome").val().trim() == "") {
			alert("Please Enter Program Outcome.");
			$("input#program_outcome").focus();
			return false;
		}
		if (isNaN($("#program_outcome").val()) == false) {
			alert("Please Enter Valid Program Outcome.");
			$("input#program_outcome").focus();
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
	
	function getdegreelistbysystem111() {
		var system_name = $("#system_id").val();
		$
				.post('getDegreeListbysystem1?' + key + "=" + value, {
					system_name : system_name
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#degree_id").html(options);
						});
	}
	function getcourselistby_professional() {
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();
		
		$.post('getCourseList_for_Curri?' + key + "=" + value,{  
			degree_id : degree_id,
			professional_id : professional_id
			}).done(function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#course_id").html(options);
		});
	}
</Script>