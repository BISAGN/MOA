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
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">

						<h2>
							<span id="lbladd"></span> G3-Assessment Method Master
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">G3-Assessment
									Method Master</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="G3_Assessment_Methodform"
						id="G3_Assessment_Methodform" action="G3_Assessment_MethodAction"
						method="post" 
						modelAttribute="G3_Assessment_MethodCMD">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">G3-Assessment Method Master</h6>
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
									<div class="input-style-1">
										<label>Assessment Method<span class="mandatory">*</span></label>
										<input type="text" id="assessment_method"
											name="assessment_method"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="500"
											placeholder="Assessment Method" />
									</div>
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
								</div>
							</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
							<ul class="buttons-group mainbtn">
							
									<li id="btn-reload1" >
						           <a href="G3_Assessment_Method_Mstr_Url" class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i class="lni lni-chevron-left"></i>Back</a>
						        </li>
						        
						        <li  id="btn-update"><input
									class="main-btn deactive-btn btn-hover btnupda" type="button"
									value="Update" /></li>
								<li id="btn-reload"><a 
									class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li id="btn-save"><input 
									class="main-btn info-btn btn-hover btnsave" type="button" value="Save" />
								</li>
								
								<li id="btn-reload2"><a  href="G3_Assessment_Method_Mstr_Url"
									class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
								
							</ul>
							</div>
							</div>
							</div>
							<!-- Bottom Button End -->
						</div>
					</form:form>
				</div>
			</div>
			<section class="single-detail-block">
		<div class="row" id="tbl">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_g3_assessment_method">
							<thead>
								<tr>
									<th id="1"><h6>Ser No</h6></th>
									<th id="6"><h6>System</h6></th>
									<th id="3"><h6>Assessment Method</h6></th>
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

<c:url value="G3_Assessment_Method_Mstr_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="G3_Assessment_Methodreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$("#btn-update").hide();
		$("#btn-reload1").hide();

		mockjax1('search_g3_assessment_method');
		table = dataTable('search_g3_assessment_method');
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
				 $("#G3_Assessment_Methodform").submit();
			}
		};
		document.getElementById('btn-update').onclick = function() {
			if(Validation()){
				 $("#G3_Assessment_Methodform").submit();
			}
		};
	});
	
	function setTimeLoadForTable(){
		
		document.querySelectorAll('.ADDG3_Assessment_Method').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apIdAGE'+val).value;
				var hpsys = document.getElementById('approfsys'+val).value;
				var hprof = document.getElementById('approfAGE'+val).value;
				var hstatus = document.getElementById('apstatusAGE'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,hpsys,hprof,hstatus);
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
	
	function data(search_g3_assessment_method) {
		
		jsondata = [];
		var table = $('#' + search_g3_assessment_method).DataTable();
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
		var assessment_method = $("#assessment_method").val();
		var status = $("#status").val();

		$.post("getFilterG3_Assessment_Method_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			system_id :system_id,
			assessment_method : assessment_method,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser,j[i].system_name, j[i].assessment_method, j[i].action ]);
			}
		});
		$.post("getTotalG3_Assessment_Method_dataCount?" + key + "=" + value, {
			Search : Search,
			system_id :system_id,
			assessment_method : assessment_method,
			status : status
		}, function(j) {
			FilteredRecords = j;
		});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
	function editData(id,system_id,assessment_method,status) {
		$("#btn-update").show();
		$("#btn-save").hide();
		$("#tbl").hide();
		$("#btn-reload").hide();
		$("#btn-reload2").hide();
		$("#btn-reload1").show();
		
		document.getElementById('lbladd').innerHTML = "Update ";
		$("select#system_id").val(system_id);
		$('#system_id').trigger('change');
		$("input#assessment_method").val(assessment_method);
		$("select#status").val(status);
		document.getElementById('id').value=id;
	}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}

	function Validation() {
		if ($("#system_id").val().trim() == "0") {
			alert("Please Select System.");
			$("select#system_id").focus();
			return false;
		}
		if ($("#assessment_method").val().trim() == "") {
			alert("Please Enter Assessment Method.");
			$("input#assessment_method").focus();
			return false;
		}
/* 		var text = $("#assessment_method").val(); */
/* 		var gc = 0; */
/*         for(var i=0;i<text.length;i++){ */
/*         	var charCode = text.charCodeAt(i); */
/*         	if(!( (charCode > 64 && charCode < 91) || (charCode == 32) )){ */
/*         		gc++; */
/*         	} */
/*         } */
/*         if(gc > 0){ */
/*        		alert("Only Capital Letters Are Allowed"); */
/*        		$("#assessment_method").focus(); */
/*        		return false; */
/*         } */
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status.");
			$("select#status").focus();
			return false;
		}
		return true;
	}
</Script>