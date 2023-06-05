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
						<h2>Document Attachments Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Document Attachments
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
					<form:form name="Documentform" id="Documentform" action="DocumentAction"
						method="post" class="form-horizontal" modelAttribute="DocumentCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Document Master</h6>
							<div class="row">
							
						 	 
							 
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Screen Module<strong class="mandatory">*</strong></label>
										<div class="select-position">
											<select name="screen_module_id" id="screen_module_id" class="singleselect form-control form-control-lg" >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getScreenModuleList}"
													varStatus="num">
													 <option value="${item.id}" name="${item.id}">${item.module_name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<!-- end select -->
								</div> 
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Screen Sub Module<strong class="mandatory">*</strong></label>
										<div class="select-position">
											<select name="screen_submodule_id" id="screen_submodule_id" class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getScreenSubModuleList}"
													varStatus="num">
													 <option value="${item.id}" name="${item.id}">${item.submodule_name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<!-- end select -->
								</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Screen Name<strong class="mandatory">*</strong></label>
										<div class="select-position">
											<select name="screen_id" id="screen_id"  class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
												<%-- <c:forEach var="item" items="${getScreenList}"
													varStatus="num">
													 <option value="${item.id}" name="${item.id}">${item.screen_name}</option>
												</c:forEach> --%>
											</select>
										</div>
									</div>
									<!-- end select -->
								</div> 
								
									
								
							<%-- 	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Screen Name<strong class="mandatory">*</strong></label>
										<div class="select-position">
											<select name="fac_course_id" id="fac_course_id" ><!-- onchange="getSemesterBYDegree(this);" -->
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getFacultyCourseList}"
													varStatus="num">
													<option value="${item.id}" name="${item.id}">${item.course_name}</option>
												</c:forEach>
											</select>
										</div>
									</div> 
									<!-- end select -->
								</div> --%>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Document Name<span class="mandatory">*</span></label> <input
											type="text" id="doc_name" name="doc_name"
											class="autocomplete txt-transupp"
											autocomplete="off" maxlength="100" placeholder="Document Name" />
										
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
								<li><a href="Document_Attachments_Url"
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
						<table class="table" id="search_document">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th id="${item.id}"><h6>Module Name</h6></th>
									<th id="${item.id}"><h6>SubModule Name</h6></th>
									<th id="${item.id}"><h6>Screen Name</h6></th>
									<th id="${item.id}"><h6>Document Name</h6></th>
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

<c:url value="getSearch_Document_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Document_name1">
	<input type="hidden" name="Document_name1" id="Document_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_Document_Mstr_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="Document_Mstr_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Documentreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_document');
		table = dataTable('search_document');
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

		document.getElementById('doc_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		document.getElementById('screen_module_id').onchange = function() {
			getScreen_SubmoduleName_From_Screen_Module();
		};
		document.getElementById('screen_submodule_id').onchange = function() {
			getScreen_Name_From_Screen_SubModule();
		};
	});
	
	function setTimeLoadForTable(){
		
		
		document.querySelectorAll('.ADDDocument').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apIdAGE'+val).value;
				var hsmo = document.getElementById('apsmoAGE'+val).value;
				var hsmos = document.getElementById('apsmosAGE'+val).value;
				var hsna = document.getElementById('apsnaAGE'+val).value;
				var hdoc = document.getElementById('apdocAGE'+val).value;
				var hstatus = document.getElementById('apstatusAGE'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,hsmo,hsmos,hsna,hdoc,hstatus);
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
	
	function data(search_document) {
		
		jsondata = [];
		var table = $('#' + search_document).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		
		var screen_module_id = $("#screen_module_id").val();
		var screen_submodule_id = $("#screen_submodule_id").val();
		
		var screen_id = $("#screen_id").val();
		var doc_name = $("#doc_name").val();
		var status = $("#status").val();

		$.post("getFilterDocument_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			
			screen_module_id : screen_module_id,
			screen_submodule_id : screen_submodule_id,
			screen_id : screen_id,
			doc_name : doc_name,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].module_name,j[i].submodule_name, j[i].screen_name, j[i].doc_name, j[i].action ]);
			}
		});
		$.post("getTotalDocument_dataCount?" + key + "=" + value, {
			Search : Search,
			
			screen_module_id : screen_module_id,
			screen_submodule_id : screen_submodule_id,
			screen_id : screen_id,
			doc_name : doc_name,
			status : status

		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
	function editData(id,screen_module_id,screen_submodule_id,screen_id,doc_name,status) {
		debugger;
		document.getElementById('lbladd').innerHTML = "Update ";
		$("select#screen_module_id").val(screen_module_id);
		getScreen_SubmoduleName_From_Screen_Module();
		$('#screen_module_id').trigger('change');
		$("select#screen_submodule_id").val(screen_submodule_id);
		getScreen_Name_From_Screen_SubModule();
		$("select#screen_id").val(screen_id);
		$("input#doc_name").val(doc_name);
		$("select#status").val(status);
		$('#status').trigger('change');
		document.getElementById('id').value=id;
	}
	

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function Search() {
		$("#screen_module_id1").val($('#screen_module_id').val());
		$("#screen_submodule_id1").val($('#screen_submodule_id').val());
		$("#screen_id1").val($('#screen_id').val());
		$("#doc_name1").val($('#doc_name').val());
		$("#status1").val($('#status').val());
		document.getElementById('searchForm').submit();
	}

	function Validation() {

		if ($("#doc_name").val().trim() == "") {
			alert("Please Enter Document Name.");
			$("input#doc_name").focus();
			return false;
		}
		if ($("#screen_module_id").val().trim() == "0") {
			alert("Please Enter Screen Module.");
			$("input#screen_module_id").focus();
			return false;
		}
		if ($("#screen_submodule_id").val().trim() == "0") {
			alert("Please Enter Screen SubModule.");
			$("input#screen_submodule_id").focus();
			return false;
		}
		if ($("#screen_id").val().trim() == "0") {
			alert("Please Enter Screen Name.");
			$("input#screen_id").focus();
			return false;
		}
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status.");
			$("select#status").focus();
			return false;
		}
		return true;
	}
	function getScreen_SubmoduleName_From_Screen_Module() {

		var screen_module_id = $("#screen_module_id").val();
//        alert(screen_module_id);
		$
				.post(
						"getsubmodule_name_FromScreen_Module?" + key + "=" + value,
						{
							screen_module_id : screen_module_id
						},
						function(j) {
// 							alert(j);
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][0]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#screen_submodule_id").html(options);
						});
	}
	function getScreen_Name_From_Screen_SubModule() {

		var screen_submodule_id = $("#screen_submodule_id").val();
//        alert(screen_module_id);
		$
				.post(
						"getScreen_NameFromScreen_SubModule?" + key + "=" + value,
						{
							screen_submodule_id : screen_submodule_id
						},
						function(j) {
// 							alert(j);
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][0]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#screen_id").html(options);
						});
	}
	
	

</Script>
