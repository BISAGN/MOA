<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!-- <script src="js/common/multicheck.js" type="text/javascript"></script> -->
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js End-->
<!-- <link href="js/dropDown/select2.min.css" rel="Stylesheet"></link> -->
<!-- <script src="js/dropDown/select2.min.js" type="text/javascript"></script> -->

<!-- INTERNAL REMOVE START-->
<link rel="stylesheet" href="assets/vendor/internal_css.css">
<!-- INTERNAL REMOVE END-->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd"></span> Link Graduate Attribute And System
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Link
									Graduate Attribute And System</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form name="roleMst" id="roleMst"
						action="Link_Graduate_Attribute_And_System_Action" method='POST'
						modelAttribute="Link_Graduate_Attribute_System_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Link College System Master</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">System<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="system_id" id="system_id">
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
												<select name="degree_id" id="degree_id"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Status<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="status" id="status">
													<c:forEach var="item" items="${ActiveInActiveList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>

										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<label class="buttonmerge" for="text-input">Graduate
											Attribute<span class="mandatory">*</span>
										</label>
										<div class="btnmerge-groupadd">
											<div class="select-style-1 buttonmergeinput">
												<div class="select-position">
													<select name="graduate_id" id="graduate_id"
														class="singleselect form-control form-control-lg">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getGraduateattribute}"
															varStatus="num">
															<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="btnmerge">
												<ul class="buttons-group justify-content-end">
													<li><a
														class="main-btn success-btn btn-hover btn-sm addminusbut"
														title="ADD MORE GRADUATE ATTRIBUTE IN LIST" id="popup">
															<i class="lni lni-plus"></i>
													</a></li>
												</ul>
											</div>
										</div>
										<label class="buttonmerge" for="text-input"> <span
											class="mandatory lgradsystem"> [ You can add options
												directly in dropdown menu from here by clicking on '+'
												button ] </span>
										</label> <label class="buttonmerge" for="text-input"> <span
											class="mandatory lgradsystem"> [ * fields indicates
												mandatory ] </span>
										</label>
									</div>
								</div>
								<div>
									<input type="hidden" id="id" name="id" value="0"
										autocomplete="off" />
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">

										<ul class="buttons-group mainbtn">
											<li id="btn-reload1"><a
												href="link_graduate_attribute_system_url"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
													<i class="lni lni-chevron-left"></i>Back
											</a></li>

											<li id="btn-update"><input
												class="main-btn deactive-btn btn-hover btnupda"
												type="button" value="Update" /></li>

											<li id="btn-reload"><a
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"> <i class="lni lni-search-alt"></i>Search
											</a></li>

											<li id="btn-save"><input
												class="main-btn info-btn btn-hover btnsave" type="button"
												value="Save"></li>

											<li id="btn-reload2"><a
												href="link_graduate_attribute_system_url"
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
								<table class="table"
									id="search_graduateattribute_system_mapping">
									<thead>
										<tr>
											<th id="1"><h6>Sr No</h6></th>
											<th id="3"><h6>System</h6></th>
											<th id="5"><h6>Degree</h6></th>
											<th id="8"><h6>Graduate Attribute</h6></th>
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


			<div class="modal fade custom-modal bd-example-modal-lg"
				tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
				id="modelWindow" aria-hidden="true">
				<div class="modal-dialog modal-lg modal-dialog-scrollable">

<!-- 					<h3 class="modal-title">Modal title</h3> -->
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title" id="myLargeModalLabel">Graduate
								Attribute</h3>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
							<!-- 				<i data-dismiss="modal" aria-label="Close" class="bi bi-x-lg"></i> -->
						</div>
						<div class="modal-body">
							<div class="custom-modal-inner" id="headData1" name="headData1">
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<div class="input-style-1">
											<label>Add Graduate Attribute<span class="mandatory">*</span></label>
											<input type="text" id="graduatt" name="graduatt"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="250"
												placeholder="Please Enter Here" />
										</div>
										<!-- 											<div class="btnmerge-groupadd"> -->
										<!-- 												<div class="btnmerge"> -->
										<!-- 													<ul class="buttons-group justify-content-end"> -->
										<!-- 														<li><a -->
										<!-- 															class="main-btn success-btn btn-hover btn-sm addminusbut" -->
										<!-- 															title="ADD" id="btn-savegraatt">Add </a></li> -->
										<!-- 													</ul> -->
										<!-- 												</div> -->
										<!-- 											</div> -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<div class="input-style-1">
											<label>Code<span class="mandatory ">*</span></label> <input
												type="text" id="code" name="code"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="5"
												placeholder="Please Enter code" />
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<ul class="buttons-group">
								<li><a type="button" class="main-btn dark-btn n btn-hover"
									data-bs-dismiss="modal">Close</a></li>
								<li><a
									class="main-btn success-btn btn-hover btn-sm addminusbut"
									title="ADD" id="btn-savegraatt">Add </a></li>
							</ul>
						</div>




						<!-- 							<div class="modal-footer"> -->
						<!-- 								<ul class="buttons-group"> -->
						<!-- 									<li><button type="button" -->
						<!-- 											class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal" -->
						<!-- 											data-dismiss="modal" aria-label="Close">Close</button></li> -->
						<!-- 								</ul> -->
						<!-- 							</div> -->
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</section>
<c:url value="Link_Graatt_Sys_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	
	$(document).ready(function() {
		//$.ajax({ async: false /*, your_other_ajax_options_here */ });
		$("#pop").hide();
		$("#btn-update").hide();
		$("#btn-reload1").hide();
		
		mockjax1('search_graduateattribute_system_mapping');
		table = dataTable('search_graduateattribute_system_mapping');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}
	});
	
document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn-save').onclick = function() {
		if(Validation()){
			 $("#roleMst").submit();
		}
	};
	document.getElementById('btn-savegraatt').onclick = function() {
		return pop_Validation();
	};
	document.getElementById('btn-update').onclick = function() {
		if(Validation()){
			$("#roleMst").submit();
		}
		//return UpdateFn();
	};
// 	document.getElementById('code').onkeypress = function() {
//  		return onlyAlphaNumeric(event, this);
// 	};
	document.getElementById('popup').onclick = function() {
		Pop_Up_History1();
	};
	document.getElementById('system_id').onchange = function() {
		getdegreelistbysystem();
	};
});

function setTimeLoadForTable(){
	
document.querySelectorAll('.ADDC3_graatt').forEach((items, index) => {
	items.addEventListener('click', event => {
		
		var val=parseInt(index)+1;
		var hid = document.getElementById('apId'+val).value;
		var hprof = document.getElementById('appsys'+val).value;
		var hdegree = document.getElementById('appdegree'+val).value;
		var hgraatt = document.getElementById('appatt'+val).value;
		var hstatus = document.getElementById('apstatus'+val).value;
		
		if (confirm('Are You Sure You Want to Edit Detail ?')) {
			debugger;
			editData(hid,hprof,hdegree,hgraatt,hstatus);
		} else {
			return false;
		}
	})
});

document.querySelectorAll('.deletegraatt').forEach((items, index) => {
	items.addEventListener('click', event => {
		
		var val=parseInt(index)+1;
		var hid = document.getElementById('deleteID1'+val).value;
		
		if (confirm('Are You Sure You Want to Delete Detail ?')) {
			deleteData(hid);
		} else {
			return false;
		}
	})
 });
}
	
	function data(search_graduateattribute_system_mapping) {
		jsondata = [];
		var table = $('#' + search_graduateattribute_system_mapping).DataTable();
		var info = table.page.info();
 		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr('id')
				.toLowerCase();
		var orderType = order[0][1];
		var system_name = $("#system_id").val();
		var degree_name = $("#degree_id").val();
		var graduate_attribute = $("#graduate_id").val();
		var status = $("#status").val();

		$.post("getFilter_graduate_attribute_system_mapping_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			system_name:system_name,
			degree_name:degree_name,
			graduate_attribute:graduate_attribute,
			status:status
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser,j[i].system_name, j[i].degree_name,j[i].graduate_attributes,j[i].action ]);
			}
		});
		$.post("getTotal_graduate_attribute_system_mapping_data?" + key + "=" + value, {
			Search : Search,
			system_name:system_name,
			degree_name:degree_name,
			graduate_attribute:graduate_attribute,
			status:status
		}, function(j) {
			FilteredRecords = j;
			});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
	function editData(id,sysname,degree,gradatt,status) {
		$.ajaxSetup({
			async : false
		});
		

		$("#btn-update").show();
		$("#btn-save").hide();
		$("#tbl").hide();
		$("#btn-reload").hide();
		$("#btn-reload2").hide();
		$("#btn-reload1").show();
		
		document.getElementById('lbladd').innerHTML = "Update";
		$("select#system_id").val(sysname);
		$('.singleselect').trigger('change'); 
// 		$('#system_id').trigger('change');
		getdegreelistbysystem();
		$("select#degree_id").val(degree);
		$("select#graduate_id").val(gradatt);
		$('#graduate_id').trigger('change');
		$("select#status").val(status);
		$("#id").val(id);
	}
	
	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
function Pop_Up_History1() {
		
	$('#modelWindow').modal('show');
}
function getdegreelistbysystem() {
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

function saveGA(){
	var Addgrad_att = $("#graduatt").val();
	var Addcode = $("#code").val();
	$.post("Addgraduateatt?" + key + "=" + value, {
		Addgrad_att : Addgrad_att,
		Addcode : Addcode
	}, function(j) {
		if(j == "1"){
			alert("Option Added Successfully");
			location.reload();
		}else{
			alert(j);
		}
	});
}

function Validation(){ 
	if($("#system_id").val() == "0" || $("#system_id").val() == ""){
		alert("Please Select System.");
		$("#system_id").focus();
		return false;
	}
	if($("#degree_id").val() == "0" || $("#degree_id").val() == ""){
		alert("Please Select Degree.");
		$("#degree_id").focus();
		return false;
	}
	if($("#graduate_id").val() == "0" || $("#graduate_id").val() == ""){
		alert("Please Select Graduate Attribute");
		$("#graduate_id").focus();
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

function pop_Validation(){ 
	if($("#graduatt").val() == "" ){
		alert("Please Enter Graduate Attribute.");
		$("#graduatt").focus();
		return false;
	}
	if($("#code").val() == "" ){
		alert("Please Enter Code.");
		$("#code").focus();
		return false;
	}
	saveGA();
	return true;
	
}
	
</script>
