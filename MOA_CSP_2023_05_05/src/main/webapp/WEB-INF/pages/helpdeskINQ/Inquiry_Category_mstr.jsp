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
						
						<h2> <span id="lbladd"></span> Inquiry Category Master</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Inquiry Category Master</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="Inq_Cat_form" id="Inq_Cat_form"
						action="Inq_Cat_Action" method="post" 
						modelAttribute="Inq_Cat_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<!-- <h6 class="mb-25">Classroom Master</h6> -->
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Inquiry Category<span class="mandatory">*</span></label> <input
												type="text" id="inq_cat" name="inq_cat"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="100" placeholder="Inquiry Category" />
										<input type="hidden" id="id" name="id" value="0"
											autocomplete="off" />
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Type<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="type" id="type">
													<option value="1">Internal</option>
													<option value="0">External</option>
												</select>
											</div>
										</div>
										
									<div class="input-style-1">
										<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
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
									<div class="input-style-2">
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
											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li><input id="btn-save"
												class="main-btn info-btn btn-hover btnsave" type="submit"
												value="Save" /></li>
											<li><a href="Inquiry_Category_Master_Url"
												class="main-btn dark-btn btn-hover btnreset " type="button">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
					</form:form>
				</div>	

		<section class="single-detail-block">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="table-wrapper table-responsive custom-datatable-p">
							<table class="table" id="search_Inquiry_Category_Mstr">
								<thead>
									<tr>
										<th><h6>Sr No</h6></th>
										<th id="${item.id}"><h6>Inquiry Category</h6></th>
										<th id="${item.id}"><h6>Type</h6></th>
										<th><h6>Action</h6></th>
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

 <c:url value="getSearch_Inquiry_Category_Mstr_Url" var="searchUrl" /> 
<form:form action="${searchUrl}" method="post" id="searchForm" 
	name="searchForm" modelAttribute="Inquiry_Cat1"> 
	<input type="hidden" name="Inquiry_Cat1" id="Inquiry_Cat1" /> 
	<input type="hidden" name="status1" id="status1" value="0" /> 
 </form:form> 

 <c:url value="Edit_Inquiry_Category_Mstr_Url" var="Edit_Url" /> 
 <form:form action="${Edit_Url}" method="post" id="updateForm" 	name="updateForm" modelAttribute="id2"> 
 	<input type="hidden" name="id2" id="id2"> 
 </form:form> 

 <c:url value="Inquiry_Category_Mstr_Delete_Url" var="deleteUrl" /> 
 <form:form action="${deleteUrl}" method="post" id="deleteForm" 
	name="deleteForm" modelAttribute="id1"> 
 	<input type="hidden" name="id1" id="id1" value="0" /> 
 </form:form> 

 <c:url value="Inquiry_Category_Mstr2" var="searchUrl" /> 
 <form:form action="${searchUrl}" method="post" id="search2" 
 	name="search2" modelAttribute="comd1"> 
 	<input type="hidden" name="typeReport1" id="typeReport1" value="0" /> 
 </form:form> 

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_Inquiry_Category_Mstr');
		table = dataTable('search_Inquiry_Category_Mstr');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.Upperinq_cat').keyup(function() {
			this.value = this.value.toUpperCase();
		});
	});
	
	function setTimeLoadForTable(){
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

		document.getElementById('inq_cat').onkeypress = function() {
// 			return onlyAlphaNumeric(this, event);
		};
		
		document.querySelectorAll('.ADDinq_cat').forEach((items, index) => {
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
	
	function data(search_Inquiry_Category_Mstr) {
		
		jsondata = [];
		var table = $('#' + search_Inquiry_Category_Mstr).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var inq_cat = $("#inq_cat").val();
		var type = $("#type").val();
		var status = $("#status").val();

		$.post("getFilterInq_Catdata?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			inq_cat : inq_cat,
			type : type,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].inq_cat, j[i].type, j[i].action ]);
			}
		});
		$.post("getTotalInq_Cat_dataCount?" + key + "=" + value, {
			Search : Search,
			inq_cat : inq_cat,
			type : type
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
	function editData(id,inq_cat,type,status) {
		document.getElementById('lbladd').innerHTML = "Update ";
		$("input#inq_cat").val(inq_cat);
		$("select#type").val(type);
		document.getElementById('id').value=id;
	}
	

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function Search() {
		$("#inq_cat").val($('#inq_cat').val());
		$("#type").val($('#type').val());
		$("#status1").val($('#status').val());
		document.getElementById('searchForm').submit();
	}

	function Validation() {

		if ($("#inq_cat").val() == "") {
			alert("Please Enter Inquiry Category.");
			$("input#inq_cat").focus();
			return false;
		}
		if ($("select#type").val() == "") {
			alert("Please Enter Inquiry Category.");
			$("select#type").focus();
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
