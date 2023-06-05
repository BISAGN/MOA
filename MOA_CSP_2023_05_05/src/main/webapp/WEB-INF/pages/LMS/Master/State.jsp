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

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							State Master<span class="text-red font-size12 enter_by"></span>
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">State
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
					<form:form name="State" id="State" action="StateAction"
						method="post" modelAttribute="StateCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">State Master</h6>
								<div class="row">


									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label>Country<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="country_id" id="country_id"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${country_id}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>State<span class="mandatory">*</span></label> <input
												type="text" id="state_name" name="state_name"
												class=" form-control" autocomplete="off" maxlength="50"
												placeholder="State" />
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>Abbreviation<span class="mandatory">*</span></label> <input
												type="text" id="state_abbr" name="state_abbr"
												class=" form-control UpperClassName txt-transupp"
												autocomplete="off" maxlength="50" placeholder="Abbreviation"
												onkeypress="lettersAndSpaceCheck(document.form1.name)" />
										</div>
										<!-- end input    onkeypress="return onlyAlphabets(event,this);"  -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label>Status<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="status" id="status"
													class="singleselect form-control form-control-lg">
													<!-- 												<option value="0">--Select--</option> -->
													<c:forEach var="item" items="${getStatusMasterList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
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
											<li><a href="State"
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
										<div id="divShow"></div>
										<div class="watermarked" data-watermark="" id="divwatermark">
											<span id="ip"></span>
											<div
												class="table-wrapper table-responsive custom-datatable-p"
												id="state_search_div">
												<table class="table" id="state_search">
													<thead>
														<tr>
															<th><h6>Sr No</h6></th>
															<th><h6>Country</h6></th>
															<th><h6>State</h6></th>
															<th><h6>Abbreviation</h6></th>
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
					</form:form>
				</div>
			</div>
		</div>


	</div>
</section>

<c:url value="getSearch_State_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="State_name1">
	<input type="hidden" name="country_id1" id="country_id1" />
	<input type="hidden" name="State_name1" id="State_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_State" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="delete_State" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Statereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>



<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		mockjax1('state_search');
		table = dataTable('state_search');
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
	
		
		document.getElementById('state_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		
		document.getElementById('state_name').oninput = function () {
			 this.value=this.value.toUpperCase()
		};
		
		
		document.getElementById('state_abbr').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		
		document.getElementById('state_abbr').oninput = function () {
			 this.value=this.value.toUpperCase()
		};
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		
		});
	
	function setTimeLoadForTable(){
		
	

		document.querySelectorAll('.ADDState').forEach((items, index) => {
			
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var stid = document.getElementById('StateId'+val).value;
 				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(stid);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.DeleteState').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var stdid = document.getElementById('DStateId'+val).value;
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(stdid);
				} else {
					return false;
				}
			})
		});
		
	}

	function data(state_search) {
		jsondata = [];
		var table = $('#' + state_search).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var state = $("#state_name").val();
		var status = $("#status").val();
		var country_id = $("select#country_id").val();
		var state_abbr = $("#state_abbr").val();

		$.post("getFilterstate_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			state : state,
			status : status,
			country_id : country_id,
			state_abbr : state_abbr

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].country_name, j[i].state_name, j[i].state_abbr, j[i].action ]);
			}
		});
		$.post("getTotalstate_dataCount?" + key + "=" + value, {
			Search : Search,
			state : state,
			country_id : country_id,
			state_abbr : state_abbr
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}


	function Search() {

		$("#state_id1").val($('#state_id').val());

		$("#State_name1").val($('#state_name').val());
		$("#status1").val($('#status').val());
		
		document.getElementById('searchForm').submit();
	}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}

	function editData(id) {
		$("#id2").val(id);
		document.getElementById('updateForm').submit();
	}
	
	function Validation() {
		if ($("#country_id").val() == "0") {
			alert("Please Select Country.");
			$("#country_id").focus();
			return false;
		}
		if ($("input#state_name").val().trim() == "") {
			alert("Please Enter State.");
			$("input#state_name").focus();
			return false;
		}
		if ($("select#status").val() == "0") {
			alert("Please Select Status.");
			$("select#status").focus();
			return false;
		}
		if ($("input#state_abbr").val().trim() == "") {
			alert("Please Enter Abbreviation.");
			$("input#state_abbr").focus();
			return false;
		}
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status.");
			$("select#status").focus();
			return false;
		}
		
		
	}
	
	
// 	function onlyAlphabets(e, t) {
//         try {
//             if (window.event) {
//                 var charCode = window.event.keyCode;
//             }
//             else if (e) {
//                 var charCode = e.which;
//             }
//             else { return true; }
//             if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))
//             	alert(helloe);
//                 return true;
//             else
//                 return false;
//         }
//         catch (err) {
//             alert(err.Description);
//         }
//     }

function lettersAndSpaceCheck(name)
{
   var regEx = /^[a-z][a-z\s]*$/;
   if(name.value.match(regEx))
     {
      return true;
     }
   else
     {
     alert("Please enter letters and space only.");
     return false;
     }
}    
</Script>




