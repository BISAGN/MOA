<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- <link href="admin/js/dropDown/select2.min.css" rel="Stylesheet"></link> -->
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- <script src="js/dropDown/select2.min.js" type="text/javascript"></script> -->
<!-- datatable style and js end-->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">

						<h2>
							<span id="lbladd"></span>Help Desk Report
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Help
									Desk Report</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="report_form" id="report_form"
						action="report_form_Action" method="post"
						modelAttribute="report_form_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<!-- <h6 class="mb-25">Classroom Master</h6> -->
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">



										<div class="input-style-1">
											<label for="username">Inquiry Number<span
												class="mandatory">*</span></label> <input type="text" id="inq_no"
												name="inq_no" placeholder="Inquiry Number"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" />
											<!-- 												<select class="form-control form-control-lg" -->
											<!-- 													name="Inq_Cat" id="Inq_cat"> -->
											<!-- 													<option value="0">--Select--</option> -->
											<%-- 													<c:forEach var="item" items="${getInq_CatList}" varStatus="num"> --%>
											<%-- 														<option value="${item.id}" name="${item.id}">${item.inq_cat}</option> --%>
											<%-- 													</c:forEach> --%>
											<!-- 												</select> -->
										</div>


										<!-- 									<div class="input-style-1"> -->
										<!-- 										<input type="hidden" id="id" name="id" value="0" autocomplete="off" /> -->
										<!--                                	 	</div> -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Inquiry Category<span
												class="mandatory">*</span></label>

											<div class="select-position">
												<!-- 											<input type="text" id="inq_cat" name="inq_cat" -->
												<!-- 												placeholder="Inquiry Number" -->
												<!-- 												class="autocomplete UpperClassName txt-transupp" -->
												<!-- 												autocomplete="off" maxlength="50"/> -->
												<select class="singleselect form-control form-control-lg"
													name="inq_cat" id="inq_cat">
													<option value="0">-- Select --</option>
													<c:forEach var="item" items="${getInq_CatList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.inq_cat}</option>
													</c:forEach>
												</select>
											</div>
										</div>

									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">

											<label>State<strong><span class="mandatory">*</span></strong></label>
											<div class="select-position">
												<select name="per_state" id="per_state"
													class="singleselect form-control form-control-lg">
													<option value="0">-- Select --</option>
													<c:forEach var="item" items="${MedStateName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">

										<div class="input-style-1">
											<label for="username">Description<span
												class="mandatory">*</span></label> <input id="des" name="des"
												placeholder="Description"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" />
										</div>

									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Status<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="status_search" id="status_search">
													<option value="0">-- Select --</option>
													<option value="1">Under Process</option>
													<option value="2">Close</option>
												</select>
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
													<!-- 											<li><input id="btn-save" -->
													<!-- 												class="main-btn info-btn btn-hover btnsave" type="submit" -->
													<!-- 												value="Save" /></li> -->
													<li><a href="Report_Link_Role_Mster_Url"
														class="main-btn dark-btn n btn-hover btnreset "
														type="button">Reset</a></li>
												</ul>
											</div>
										</div>
									</div>
									<!-- Bottom Button End -->
								</div>
							</div>
						</div>
					</form:form>
				</div>

				<div class="modal fade custom-modal" tabindex="-1" role="dialog"
					aria-labelledby="myLargeModalLabel" aria-hidden="true"
					id="Inq_Case_model">
					<div
						class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
						<div class="modal-content">

							<!--  Modal Header -->
							<div class="modal-header">
								<h3 class="modal-title">Response</h3>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">
								<div class="row align-items-end Inq_Case">
									<input type="hidden" id="hod_inq_rep" name="hod_inq_rep"
										value="0" autocomplete="off" />

									<div class="col-12 col-sm-12 col-md-12 col-lg-12">
										<div class="input-style-1">
											<label>Note<span class="mandatory">*</span></label>
											<textarea placeholder="Enter Note" rows="5" id="note"></textarea>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<div class="select-style-1">
											<label for="username">Status<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="form-control form-control-lg" name="status"
													id="status">
													<option value="0">-- Select --</option>
													<option value="1">Under Process</option>
													<option value="2">Close</option>
												</select>
											</div>
										</div>

										<div class="input-style-1">
											<input type="hidden" id="hidcase1" name="hidcase1" value="0"
												autocomplete="off" />
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<div class="input-style-1" id="inq_btdw">
											<a href="#" class="main-btn info-btn btn-hover btndownload">
												<i class="lni lni-download mr-5"></i>Download
											</a> <input type="hidden" id="hiddlid" name="hiddlid" value="0"
												autocomplete="off" />
										</div>
									</div>


								</div>
							</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<ul class="buttons-group">

									<li id="inq_btcat"><a class="main-btn info-btn btn-hover"
										data-bs-dismiss="modal">Save</a></li>
									<li><a class="main-btn dark-btn n btn-hover" type="button"
										data-bs-dismiss="modal">Close</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>


				<section class="single-detail-block">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="card-style mb-30">
								<div class="table-wrapper table-responsive custom-datatable-p">
									<table class="table" id="Report_Link_Role_Master">
										<thead>
											<tr>
												<th><h6>Sr No</h6></th>
												<th id="${item.id}"><h6>State Name</h6></th>
												<th id="${item.id}"><h6>Inquiry Category</h6></th>
												<th id="${item.id}"><h6>Inquiry Number</h6></th>
												<th id="${item.id}"><h6>Description</h6></th>
												<th id="${item.id}"><h6>Email</h6></th>
												<th id="${item.id}"><h6>Status</h6></th>
												<th class="action"><h6>Action</h6></th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
	</div>
</section>

<%--  <c:url value="getSearch_Report_Link_Role_Master_Url" var="searchUrl" />  --%>
<%-- <form:form action="${searchUrl}" method="post" id="searchForm" 	name="searchForm" modelAttribute="Report_Link_role">   --%>
<!-- 	<input type="hidden" name="Report_Link_Role" id="Report_Link_Role" />  -->
<!-- 	<input type="hidden" name="status1" id="status1" value="0" />  -->
<%--   </form:form>   --%>

<%--  <c:url value="Edit_Report_Link_Role_Mster_Url" var="Edit_Report_Link_Role_Mster_Url" />  --%>
<%--  <form:form action="${Edit_Report_Link_Role_Mster_Url}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">  --%>
<!--  	<input type="hidden" name="id2" id="id2">  -->
<%--  </form:form>  --%>

<c:url value="Inquiry_Link_Role_Mstr_Url"
	var="Inquiry_Link_Role_Mstr_Url" />
<form:form action="${Inquiry_Link_Role_Mstr_Url}" method="post"
	id="redirectForm" name="redirectForm" modelAttribute="hidcase">
	<input type="hidden" name="hidcase" id="hidcase" value="0" />
</form:form>

<c:url value="getDownloadPdfUrlforhd_Doc" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="getDownloadPdfForm"
	name="getDownloadPdfForm" modelAttribute="doc_id1">
	<input type="hidden" name="pageUrl" id="pageUrl"
		value="redirect:Report_Link_Role_Mster_Url" />
	<input type="hidden" name="doc_id1" id="doc_id1" value="" />
</form:form>




<%--  <c:url value="Report_Link_Role_Master2" var="searchUrl" />  --%>
<%--   <form:form action="${searchUrl}" method="post" id="search2"  --%>
<%--   	name="search2" modelAttribute="comd1">    --%>
<!--  	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />   -->
<%--  </form:form>    --%>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('Report_Link_Role_Master');
		table = dataTable('Report_Link_Role_Master');
		$('#btn-reload').on('click', function() {
			
			table.ajax.reload();
		});
	});
	
	
	
	document.addEventListener('DOMContentLoaded', function () {	
		
		document.getElementById('inq_btcat').onclick = function() {
			getInq_Cat_dtl();
		};
		document.getElementById('inq_no').onkeypress = function () {
			return isNumberKey0to9(event, this);
		 };
	document.getElementById('inq_btdw').onclick = function() {
		 pdfdl();
	};

// 	document.getElementById('Inq_Cat').onkeypress = function() {
// 		return onlyAlphaNumeric(this, event);
// 	};
	
// 	document.getElementById('role').onkeypress = function() {
// 		return onlyAlphaNumeric(this, event);
// 	};
	
// 	document.getElementById('user').onkeypress = function() {
// 		return onlyAlphaNumeric(this, event);
// 	};
// 	document.getElementById('role').onchange = function() {
// 		return getuser();
// 	};
	});	
	
	
	function setTimeLoadForTable(){
		
		
		document.querySelectorAll('.ADDInquiryFrom').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('hid_inq_rep'+val).value;

				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid);
				} else {
					return false;
				}

			})
		});
		document.querySelectorAll('.redirectOnclick').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var cid = document.getElementById('hid_asg_rep'+val).value;
				
				if (confirm('Are You Sure You Want to Asign Detail ?')) {
					redirectData(cid);
				} else {
					return false;
				}
			})
		});
		
	}
	
	function pdfdl() {
		
		var g =$("#hiddlid").val()
		
		$("#doc_id1").val(g); 
		document.getElementById("getDownloadPdfForm").submit();
	} 
  	
	function data(Report_Link_Role_Master) {
		
		jsondata = [];
		var table = $('#' + Report_Link_Role_Master).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
		var orderType = order[0][1];
		
		var inq_no = $("#inq_no").val();
		var inq_cat = $("#inq_cat").val();
		var per_state = $("#per_state").val();
		var des = $("#des").val();
		var status_search = $("#status_search").val();


		$.post("getFilterInq_Report_datalist?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			inq_no : inq_no,
			inq_cat : inq_cat,
			per_state : per_state,
			des : des,
			status : status_search

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].state_name, j[i].inq_cat, j[i].inq_no, j[i].description, j[i].email, j[i].status, j[i].action ]);
			}
		});
		$.post("getTotalInq_Report_dataCount1?" + key + "=" + value, {
			Search : Search,
			inq_no : inq_no,
			inq_cat : inq_cat,
			per_state : per_state,
			des : des,
			status : status_search
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
		
	}
	

	
// 	$(".Inq_Case").click(function() {
// 		alert(id);
// 	});
	
// 	function editData(id) {
// 		$("#id2").val(id);
// 		document.getElementById('updateForm').submit();
// 	}

	function redirectData(cid) {
// 		alert(cid);
		$("#hidcase").val(cid);
		document.getElementById('redirectForm').submit();
	}
	

// 	function Search() {
// 		$("select#Inquiry_Number").val(Inq_Cat);
// 		$("select#role").val(role);
// 		$("select#user").val(user);
// 		document.getElementById('searchForm').submit();
// 	}

// 	function Validation() {

// 		if ($("#inq_no").val() == "") {
// 			alert("Please Enter Inquiry Number.");
// 			$("select#inq_no").focus();
// 			return false;
// 		}
// 		if ($("#role").val() == "0") {
// 			alert("Please Select state.");
// 			$("select#per_state").focus();
// 			return false;
// 		}
// 		if ($("#user").val() == "0") {
// 			alert("Please Enter Description.");
// 			$("select#des").focus();
// 			return false;
// 		}
// 		return true;
// 	}	


function editData(hid){
	$("#hidcase1").val(hid);
	$("#hiddlid").val(hid);
	var hid = $("#hiddlid").val();
//      alert("hid");
	$.post("getInq_Reportchildeditstatus?" + key + "=" + value, {
// 		note : note,
// 		status : status
		hid: hid
		
	}, function(j) {
		console.log(j)
		if (j.length>1) {
			$("#note").val(j[j.length-1].note);
			$("#status").val(j[j.length-1].status);
		}
		else {
			$("#note").val("");
			$("#status").val("0");
		}
		
// 		if (j== 0) {
// 			alert("Unable To Save");
			
// 			setTimeout(function () {
// 				$('#"Inq_Case_model"').modal('show');		
// 			        }, 700);
// 		}else{
			
// 			alert("Data Saved Successfully");
			
// 		}

});
}

function getInq_Cat_dtl(){
	var hid = $("#hidcase1").val();
	var note = $("#note").val();
	var status = $("#status").val();
	
	$.post("getInq_RepDetails?" + key + "=" + value, {
		hid : hid,
		note : note,
		status : status
		
}, function(j) {
	if (j== 0) {
		alert("Unable To Save");
		
		setTimeout(function () {
			$('#"Inq_Case_model"').modal('show');		
		        }, 700);
	}else{
		
		alert("Data Saved Successfully");
		$("#btn-reload").trigger("click");
	}
});
}

</Script>
