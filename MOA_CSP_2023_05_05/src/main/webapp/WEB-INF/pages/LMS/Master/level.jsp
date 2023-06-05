<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- <script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
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
<script type="text/javascript" src="js\watermark\common.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Level Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Level
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
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form name="Level" id="Level" action="LevelAction"
						method="post" modelAttribute="LevelCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Level Master</h6>
								<div class="row">
									<div class="col-12 col-lg-4 col-md-4 col-sm-12">
										<div class="input-style-1">
											<label>Level<span class="mandatory">*</span></label> <input
												type="text" id="level_name" name="level_name"
												autocomplete="off" maxlength="50" placeholder="Level" />
										</div>
										<!-- end input -->
									</div>


									<div class="col-12 col-lg-4 col-md-4 col-sm-12">
										<div class="input-style-1">
											<label>Sequence<span class="mandatory">*</span></label> <input
												type="text" id="sequence_name" name="sequence_name"
												autocomplete="off" maxlength="2" placeholder="Sequence" />
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-lg-4 col-md-4 col-sm-12">
										<div class="select-style-1">
											<label>Status<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="status" id="status"
													class="singleselect form-control form-control-lg">
													<c:forEach var="item" items="${ActiveInActiveList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>

												</select>
												<div class="col-md-6">
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
												<li><a type="button" id="btn-reload"
													class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"><i
														class="lni lni-search-alt"></i>Search</a></li>
												<li><input type="submit" id="btn-save" id="btn-save"
													class="main-btn info-btn btn-hover btnsave" value="Save">
												<li><a href="LevelUrl"
													class="main-btn dark-btn n btn-hover btnreset">Reset</a></li>
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
											<table class="table" id="search_level">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th id="${item.id}"><h6>Level</h6></th>
														<th><h6>Sequence</h6></th>
														<th><h6>Action</h6></th>
													</tr>
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


<c:url value="getSearch_Level_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Level_name1">
	<input type="hidden" name="Level_name1" id="Level_name1" />
</form:form>

<c:url value="Edit_levelUrl" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="delete_LevelUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Levelreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

	$(document).ready(function() {

		mockjax1('search_level');
		table = dataTable('search_level');
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
		return Validation();
	};

	document.getElementById('level_name').onkeypress = function() {
		return onlyAlphaNumeric(event,this);
	};
	
	document.getElementById('sequence_name').onkeypress = function() {
		return isNumberKey0to9(event);
	};

		
		});
	
	
function setTimeLoadForTable(){
		
		
		document.querySelectorAll('.ADDLevel').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var lid = document.getElementById('LevelId'+val).value;
 				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(lid);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.DeleteLevel').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var ldid = document.getElementById('DELevelId'+val).value;
				
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(ldid);
				} else {
					return false;
				}
			})
		});
		
	}

	function data(search_level) {
	
		jsondata = [];
		var table = $('#' + search_level).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var level_name = $("#level_name").val();
		var status = $("#status").val();
		var sequence_name = $("#sequence_name").val();

		$.post("getFilterlevel_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			level_name : level_name,
			status : status,
			sequence_name : sequence_name

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].level_name, j[i].sequence_name,
						j[i].action ]);
			}
		});
		$.post("getTotallevel_dataCount?" + key + "=" + value, {
			level_name : level_name,
			sequence_name : sequence_name
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

	function Validation() {

		if ($("#level_name").val().trim() == "") {
			alert("Please Enter Level.");
			$("input#level_name").focus();
			return false;
		}
		if($("#level_name").val() =="0"){
			alert("Please Enter Valid Level.");
			$("input#level_name").focus();
			return false;
		}
		if ($("#sequence_name").val().trim() == "") {
			alert("Please Enter Sequence.");
			$("input#sequence_name").focus();
			return false;
		}
		if ($("select#status").val() == "0") {
			alert("Please Select Status.");
			$("select#status").focus();
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
