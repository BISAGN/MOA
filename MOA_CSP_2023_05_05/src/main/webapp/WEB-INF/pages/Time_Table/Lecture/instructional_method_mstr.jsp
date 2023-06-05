<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Instructional Methods Master</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Instructional
									Methods Master</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="Instruction_Master" id="Instruction_Master"
						action="Instruction_Master_Action" method="post"
						 modelAttribute="Instruction_MasterCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<!-- <h6 class="mb-25">Instructional Methods Master</h6> -->
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Instructional Methods<span class="mandatory">*</span></label>
											<input type="text" id="instructional_method_name"
											class="autocomplete UpperClassName txt-transupp"
												name="instructional_method_name" autocomplete="off"
												maxlength="1000" placeholder="Instructional Methods" />

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
											<li><a href="Instructional_MasterUrl"
												class="main-btn dark-btn n btn-hover btnreset" type="button">Reset</a></li>
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
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="table-wrapper table-responsive custom-datatable-p">
							<table class="table" id="search_methods">
								<thead>
									<tr>
										<th><h6>Sr No</h6></th>
										<th id="${item.id}"><h6>Instructional Methods</h6></th>
										<%--<th id="${item.id}"><h6>Refer Code</h6></th> --%>
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
</section>

<c:url value="getSearch_Instructional_Method_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Instructional_method_name1">
	<input type="hidden" name="Instructional_method_name1"
		id="Instructional_method_name1" />
</form:form>

<c:url value="Edit_Instructional_Methods_mstrUrl" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id15">
	<input type="hidden" name="id15" id="id15">
</form:form>

<c:url value="delete_Url23" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id23">
	<input type="hidden" name="id23" id="id23" value="0" />
</form:form>

<c:url value="Systemreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_methods');
		table = dataTable('search_methods');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
		
		if(window.location.href.includes("msg"))
		{
// 			 if(confirm('${msg}')){
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
// 			 }
		}
	});
	
	function setTimeLoadForTable(){
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

		document.getElementById('instructional_method_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		
		document.querySelectorAll('.ADDSystem').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apIdAGE'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid);
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
	
	function data(search_methods) {
		
		jsondata = [];
		var table = $('#' + search_methods).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var instructional_method_name = $("#instructional_method_name").val();
		
		

		$.post("getFilter_Instrucational_Method_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			instructional_method_name : instructional_method_name
			
			

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].sr_no, j[i].instructional_method_name ,j[i].action ]);
			}
		});
		$.post("getTotal_Instrucational_Method_dataCount?" + key + "=" + value, {
			Search : Search,
			instructional_method_name : instructional_method_name
			
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
	function editData(id) {

		$("#id15").val(id);
		document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
		$("#id23").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function Search() {
		document.getElementById('searchForm').submit();
	}
	function Validation() {

		if ($("#instructional_method_name").val().trim() == "") {
			alert("Please Enter Instructional Method.");
			$("input#instructional_method_name").focus();
			return false;
		}
		return true;
	}
	

</Script>