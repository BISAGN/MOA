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
							<span id="lbladd"></span> D3-Scope Of Understanding Master
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">D3-Scope
									Of Understanding Master</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
	<!-- title-wrapper end -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form name="Scopeform" id="Scopeform"
						action="D3_Scope_Of_UnderstandingAction" method="post"
						 modelAttribute="D3ScopeCMD">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">D3-Scope Of Understanding Master</h6>
							<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="text-input">System<span class="mandatory">*</span></label>
									<div class="select-position">
									<select class="singleselect form-control form-control-lg" name="system_id" id="system_id">
									  <option value="0">--Select--</option>
										<c:forEach var="item" items="${getSystemList}" varStatus="num">
													<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
										</c:forEach>
								   </select>
							     </div>
								</div>					
								<!-- end select -->
							</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Scope<span class="mandatory">*</span></label> <input
											type="text" id="scope" name="scope"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="250" placeholder="Scope" />
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
									<li id="btn-reload1">
					             <a  href="D3_Scope_Of_Understanding_Mstr_Url" class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i class="lni lni-chevron-left"></i>Back</a>
					           </li>
					           
					           <li  id="btn-update"><input
									class="main-btn deactive-btn btn-hover btnupda" type="button"
									value="Update" /></li>
									
								<li id="btn-reload"><a 
									class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
									
								<li  id="btn-save"><input
									class="main-btn info-btn btn-hover btnsave" type="button" value="Save" />
								</li>
								
								<li id="btn-reload2"><a  href="D3_Scope_Of_Understanding_Mstr_Url"
									class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
									
							</ul>
							</div>
							</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		<section class="single-detail-block">	
		<div class="row" id="tbl">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_scope">
							<thead>
								<tr>
									<th id="1"><h6>Sr No</h6></th>
									<th id="6"><h6>System</h6></th>
									<th id="3"><h6>Scope</h6></th>
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

<c:url value="Edit_D3_Scope_Of_Understanding_Mstr_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="D3_Scope_Of_Understanding_Mstr_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$("#btn-update").hide();
		$("#btn-reload1").hide();

		mockjax1('search_scope');
		table = dataTable('search_scope');
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
				 $("#Scopeform").submit();
			}
		};
		document.getElementById('btn-update').onclick = function() {
			if(Validation()){
				 $("#Scopeform").submit();
			}
		};

		document.getElementById('scope').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
	});
	
	function setTimeLoadForTable(){
		
		document.querySelectorAll('.ADDScope').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				var hid = document.getElementById('apIdSCOPE'+val).value;
				var hsid = document.getElementById('approsAGE'+val).value;
				var hscope = document.getElementById('approfSCOPE'+val).value;
				var hstatus = document.getElementById('apstatusSCOPE'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,hsid,hscope,hstatus);
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
	
	function data(search_scope) {
		
		jsondata = [];
		var table = $('#' + search_scope).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr('id')
				.toLowerCase();
		var orderType = order[0][1];
		var scope = $("#scope").val();
		var status = $("#status").val();
		var system_id = $("#system_id").val();

		$.post("getFilterScope_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			scope : scope,
			status : status,
			system_id : system_id
			
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser,j[i].system_name, j[i].scope, j[i].action ]);
			}
		});
		$.post("getTotalScope_dataCount?" + key + "=" + value, {
			Search : Search,
			scope : scope,
			status : status,
			system_id : system_id
		}, function(j) {
			FilteredRecords = j;
		});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
	function editData(id,system_id,scope,status) {
		$("#btn-update").show();
		$("#btn-save").hide();
		$("#tbl").hide();
		$("#btn-reload").hide();
		$("#btn-reload2").hide();
		$("#btn-reload1").show();
		
		document.getElementById('lbladd').innerHTML = "Update ";
		$("select#system_id").val(system_id);
		$('#system_id').trigger('change');
		$("input#scope").val(scope);
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

		if ($("#scope").val().trim() == "") {
			alert("Please Enter Scope.");
			$("input#scope").focus();
			return false;
		}
/* 		var text = $("#scope").val(); */
/* 		var gc = 0; */
/*         for(var i=0;i<text.length;i++){ */
/*         	var charCode = text.charCodeAt(i); */
/*         	if(!( (charCode > 64 && charCode < 91) || (charCode == 32) )){ */
/*         		gc++; */
/*         	} */
/*         } */
/*         if(gc > 0){ */
/*        		alert("Only Capital Letters Are Allowed"); */
/*        		$("#scope").focus(); */
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