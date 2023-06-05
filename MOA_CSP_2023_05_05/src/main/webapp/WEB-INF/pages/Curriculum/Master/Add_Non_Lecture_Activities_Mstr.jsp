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
<script type="text/javascript" src="js\watermark\common.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
	<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">

						<h2>
							<span id="lbladd"></span> Add Non Lecture Activities
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Add
									Non Lecture Activities</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper start -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form name="Teaching_learning_methodform"
						id="Teaching_learning_methodform"
						action="Teaching_learning_methodAction" method="post"
						modelAttribute="Teaching_learning_methodCMD">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Add Non Lecture Activities</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Teaching Learning Method<span class="mandatory">*</span></label>
										<input type="text" id="teaching_learning_method"
											name="teaching_learning_method"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="100"
											placeholder="Teaching Learning Method" /> <input
											type="hidden" id="id" name="id" value="0" autocomplete="off" />
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="status" id="status">
												<c:forEach var="item" items="${ActiveInActiveList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="input-style-1 mt-3">
										<input type="hidden" id="id" name="id" value="0"
											autocomplete="off" />
									</div>

									<!-- end select -->
								</div>

							</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
							<ul class="buttons-group mainbtn">
							
									<li id="btn-reload1" >
						           <a href="Teaching_learning_method_Mstr_Url" class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i class="lni lni-chevron-left"></i>Back</a>
						        </li>
						        
						        <li id="btn-update"><input
									class="main-btn deactive-btn btn-hover btnupda" type="button"
									value="Update" /></li>
						        
								<li id="btn-reload"><a
									class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
									
								<li id="btn-save"><input
									class="main-btn info-btn btn-hover btnsave" type="button" value="Save" />
								</li>
								
								<li id="btn-reload2"><a href="Teaching_learning_method_Mstr_Url"
									class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
								
							</ul>
							</div>
							</div>
							</div>
							<!-- Bottom Button End -->
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
						<table class="table" id="search_teaching_learning_method">
							<thead>
								<tr>
									<th id="1"><h6>Sr No</h6></th>
									<th id="3"><h6>Teaching learning method</h6></th>
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
		</section>
		</div>
	</div>
</section>



<c:url value="Edit_Teaching_learning_method_Mstr_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="Teaching_learning_method_Mstr_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Teaching_learning_method_name1report2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$("#btn-update").hide();
		$("#btn-reload1").hide();

		mockjax1('search_teaching_learning_method');
		table = dataTable('search_teaching_learning_method'); 
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
				 $("#Teaching_learning_methodform").submit();
			}
		};
		document.getElementById('btn-update').onclick = function() {
			if(Validation()){
				 $("#Teaching_learning_methodform").submit();
			}
		};
		document.getElementById('teaching_learning_method').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
	});
	
	function setTimeLoadForTable(){
		
		
		document.querySelectorAll('.ADDTeaching_learning_method').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apIdAGE'+val).value;
				var hprof = document.getElementById('approfAGE'+val).value;
				var hstatus = document.getElementById('apstatusAGE'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,hprof,hstatus);
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
	
	function data(search_teaching_learning_method) {
		
		jsondata = [];
		var table = $('#' + search_teaching_learning_method).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr('id')
				.toLowerCase();
		var orderType = order[0][1];
		var teaching_learning_method = $("#teaching_learning_method").val();
		var status = $("#status").val();

		$.post("getFilterTeaching_learning_method_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			teaching_learning_method : teaching_learning_method,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].teaching_learning_method, j[i].action ]);
			}
		});
		$.post("getTotalTeaching_learning_method_dataCount?" + key + "=" + value, {
			Search : Search,
			teaching_learning_method : teaching_learning_method,
			status : status
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
	function editData(id,teaching_learning_method,status) {
		$("#btn-update").show();
		$("#btn-save").hide();
		$("#tbl").hide();
		$("#btn-reload").hide();
		$("#btn-reload2").hide();
		$("#btn-reload1").show();
		
		document.getElementById('lbladd').innerHTML = "Update ";
		$("input#teaching_learning_method").val(teaching_learning_method);
		$("select#status").val(status);
		document.getElementById('id').value=id;
	}
	

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function Validation() {

		if ($("#teaching_learning_method").val() == "") {
			alert("Please Enter Teaching Learning Method.");
			$("input#teaching_learning_method").focus();
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
	

</Script>
