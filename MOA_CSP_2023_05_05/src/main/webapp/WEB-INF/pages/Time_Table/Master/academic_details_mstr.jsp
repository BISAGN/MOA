<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

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
<script type="text/javascript" src="js/watermark/common.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Academic Details Master</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Academic
									Details Master</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="Academic_Master" id="Academic_Master"
						action="Academic_Master_Action" method="post"
						modelAttribute="Academic_MasterCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<!-- <h6 class="mb-25">Academic Details	Master</h6> -->
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Academic Details<span class="mandatory">*</span></label>
											<input type="text" id="academic_details_name"
												name="academic_details_name"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="100"
												placeholder="Academic Details" />

										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Refer Code<span class="mandatory">*</span></label> <input
												type="number" id="refer_code" name="refer_code"
												class=" form-control UpperClassName txt-transupp"
												autocomplete="off" placeholder="Refer Code" />
										</div>
										<!--max="99" -->
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
											<li><a href="Academic_mstrUrl"
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
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_academic">
							<thead>
								<tr>
									<th id="sr_no"><h6>Sr No</h6></th>
									<th id="academic_details_name"><h6>Academic Details</h6></th>
									<th id="refer_code"><h6>Refer Code</h6></th>
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

<c:url value="getSearch_Academic_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Academic_details_name1">
	<input type="hidden" name="Academic_details_name1"
		id="Academic_details_name1" />
	<input type="hidden" name="Refer_code1" id="Refer_code1" value="0" />
</form:form>

<c:url value="Edit_Academic_mstrUrl" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id14">
	<input type="hidden" name="id14" id="id14">
</form:form>

<c:url value="delete_Url21" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id21">
	<input type="hidden" name="id21" id="id21" value="0" />
</form:form>

<c:url value="Systemreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_academic');
		table = dataTable('search_academic');
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
	document.addEventListener('DOMContentLoaded', function () {	
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

		document.getElementById('academic_details_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		
		document.getElementById('refer_code').onkeypress = function() {
			return isNumberKey0to9(event, this);
			
		};
		
		document.getElementById('refer_code').onchange = function() {
// 			checkMinLength(this.id,3);
			checkMaxLength(this.id,3);
			
		};
	});
	
	function data(search_academic) {
		
		jsondata = [];
		var table = $('#' + search_academic).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr("id")
				.toLowerCase();
		var orderType = order[0][1];
		var academic_details_name = $("#academic_details_name").val();
		var refer_code = $("#refer_code").val();
		

		$.post("getFilterAcademic_Details_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			academic_details_name : academic_details_name,
			refer_code : refer_code
			

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].sr_no, j[i].academic_details_name, j[i].refer_code,j[i].action ]);
			}
		});
		$.post("getTotalAcademic_Details_dataCount?" + key + "=" + value, {
			Search : Search,
			academic_details_name : academic_details_name,
			refer_code : refer_code
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
	function editData(id) {

		$("#id14").val(id);
		document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
		$("#id21").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function Search() {
		$("#Academic_details_name1").val($('#academic_details_name').val());
		$("#Refer_code1").val($('#refer_code').val());
		document.getElementById('searchForm').submit();
	}

	function Validation() {

		if ($("#academic_details_name").val().trim() == "") {
			alert("Please Enter Academic Details Name.");
			$("input#academic_details_name").focus();
			return false;
		}
		
		if ($("#refer_code").val() =="") {
				alert("Please Enter Refer Code.");
				$("input#refer_code").focus();
				return false;
		}
		
		if ($("#refer_code").val() <= 0 ) {
			alert("Please Enter Proper Refer Code.");
			$("input#refer_code").focus();
			return false;
		}
		
 		if ($("#refer_code").val().length < 3 ) {
			alert("Please Enter minimum 3 digit Code.");
			$("input#refer_code").focus();
			return false;
		}
		return true;
	}
	
	function checkMinLength(id,minleg){
		var charLength = $("#"+id).val().length;
		   if(charLength < minleg){
		   		alert('Pincode Should Contain Minimum '+minleg+' Digit'); 
		   		$("#"+id).val("");
		   		  $("#"+id).focus();
		   }  
	}

	function checkMaxLength(id,maxleg){
		var charLength = $("#"+id).val().length;
		   if(charLength > maxleg){
		   		alert('Pincode Should Contain Maximum '+maxleg+' Digit');
		   		$("#"+id).val("");
		   		  $("#"+id).focus();
		   }  
	}
	
	
</Script>
