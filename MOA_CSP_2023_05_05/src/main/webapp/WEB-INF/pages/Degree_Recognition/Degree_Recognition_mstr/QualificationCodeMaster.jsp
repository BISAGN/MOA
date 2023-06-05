<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->
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

<script nonce="${cspNonce}" type="text/javascript">
	var username = "${username}";
</script>



<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd"></span>Qualification Code Master
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Set
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
					<form:form action="qualificationMaster_action" method="POST"
						modelAttribute="qual_cmd">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Qualification Code Master</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="username">Qualification Name<span class="mandatory">*</span></label>
											<input id="qualification_name" name="qualification_name" autocomplete="off"
												maxlength="500" placeholder="Qualification Name" /> <input type="hidden"
												id="id" name="id" value="0" class="mt-3" autocomplete="off" />
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<div class="input-style-1">
											<label for="username">Codes<span class="mandatory">*</span></label>
											<input id="codes" name="codes" autocomplete="off"
												maxlength="50" placeholder="Codes" /> <input type="hidden"
												id="id" name="id" value="0" class="mt-3" autocomplete="off" />
										</div>
										</div>
										<div class="input-style-1 mt-3">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
										</div>

										<!-- end select -->
									</div>

									<!-- 								<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
									<!-- 									<div class="input-style-1"> -->
									<!-- 										<label for="username">Profession Name<span class="mandatory">*</span></label> -->
									<!-- 										<input id="prof_name" name="prof_name" autocomplete="off" -->
									<!-- 											maxlength="50" placeholder="Profession Name" />  -->
									<!-- 											<input type="hidden" id="id" name="id" value="0" class="mt-3" -->
									<!-- 											autocomplete="off" /> -->
									<!-- 									</div> -->
									<!-- 									end input -->
									<!-- 								</div> -->

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Status<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="status" id="status"
													class="singleselect form-control form-control-lg">
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
											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li><input id="btn-save"
												class="main-btn info-btn btn-hover btnsave" type="submit"
												value="Save" /></li>
											<li><a href="setmaster_url"
												class="main-btn dark-btn n btn-hover btnreset" type="button">Reset</a></li>
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
											<table class="table" id="search_qualification">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th id="2"><h6>Qualification Name</h6></th>
														<th id="3"><h6>Codes</h6></th>
														
														<th><h6>Action</h6></th>
													</tr>
													<!-- 	
																		end table row -->
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


<script nonce="${cspNonce}" type="text/javascript">
	
	$(document).ready(function() {

		mockjax1('search_qualification');
		table = dataTable('search_qualification');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
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
	
// 	document.getElementById('term_id').onkeypress = function() {
// 		return onlyAlphabetsStringSpace(this, event);
// 	};
// 	document.getElementById('setname').onkeypress = function() {
// 		return onlyAlphabetsStringSpace(event,this);		
// 	};
	});

	function setTimeLoadForTable(){
		
		

// 		document.querySelectorAll('.ADDSet').forEach((items, index) => {
// 			debugger;
// 			items.addEventListener('click', event => {
				
// 				var val=parseInt(index)+1;
				
// 				var sid = document.getElementById('SetId'+val).value;
 				
// 				if (confirm('Are You Sure You Want to Edit Detail ?')) {
// 					editData(sid);
// 				} else {
// 					return false;
// 				}
// 			})
// 		});
		
		document.querySelectorAll('.EditSet').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
			
				var editid = document.getElementById('EditId'+val).value;
				if (confirm('Are You Sure You Want to Edit Detail?')) {
					editData(editid);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.deleteSet').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var sdid = document.getElementById('DSetId'+val).value;
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(sdid);
				} else {
					return false;
				}
			})
		});
		
	}
	
	function data(search_qualification) {
		jsondata = [];
		var table = $('#' + search_qualification).DataTable();
		var info = table.page.info();
// 		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		//var orderColunm = $(table.column(order[0][0]).header()).attr('id').toLowerCase();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];

		//var wksp=$("#veh_id").val() ;
		var qualification_name = $("#qualification_name").val();
		var codes = $("#codes").val();
	

		$.post("getFilterqualification_code_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			qualification_name:qualification_name,
			codes:codes,
			status:status
			
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([j[i].ser, j[i].qualification_name, j[i].codes,j[i].action]);
			}
		});
		$.post("getTotalqualification_codeCount?" + key + "=" + value, {
			Search : Search,
			qualification_name:qualification_name,
			codes:codes,
			status:status
		}, function(j) {
			
			FilteredRecords = j;

			});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
function editData(id) {
		
		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}

	function Validation(){
		
				if ($("#setname").val().trim() == "") {
					alert("Please Enter Set Name.");
					$("input#setname").focus();
					return false;
				}
			
				if ($("#term_id").val() == "0") {
					alert("Please Enter Profession Name.");
					$("#term_id").focus();
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

	</script>



