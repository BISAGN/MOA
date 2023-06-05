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
<!-- <script type="text/javascript" src="js\watermark\--common.js"></script> -->

<section class="dashboard-page">
	<div class="container-fluid">
	<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd"></span> Add Graduate Attributes
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
									Graduate Attributes</li>
							</ol>
						</nav>
					</div>
				</div>
					<!-- end col -->
			</div>
		</div>
		<!-- title-wrapper end -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12 col-sm-12 col-md-12 col-lg-12">
					<!-- input style start -->
					<form:form name="Graduate_Attributesform"
						id="Graduate_Attributesform" action="Graduate_AttributesAction"
						method="post" 
						modelAttribute="Graduate_AttributesCMD">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Add Graduate Attributes Master</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Code<span class="mandatory">*</span></label> <input
											type="text" id="code" name="code"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="5" placeholder="Code" />
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Graduate Attributes<span class="mandatory">*</span></label>
										<input type="text" id="graduate_attributes"
											name="graduate_attributes"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="250"
											placeholder="Graduate Attributes" />
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
										<div class="input-style-1 mt-3">
										<input type="hidden" id="id" name="id" value="0"
											autocomplete="off" />
									</div>
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
						           <a  href="Graduate_Attributes_Mstr_Url" class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i class="lni lni-chevron-left"></i>Back</a>
						        </li>
						        <li id="btn-update"><input 
									class="main-btn deactive-btn btn-hover btnupda" type="button"
									value="Update" /></li>
									
								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
									
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover btnsave" type="button" value="Save" /></li>
									
								<li id="btn-reload2"><a  href="Graduate_Attributes_Mstr_Url"
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
			<div class="col-12 col-sm-12 col-md-12 col-lg-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_graduate_attributes">
							<thead>
								<tr>
									<th id="1"><h6>Ser No</h6></th>
									<th id="3"><h6>Code</h6></th>
									<th id="4"><h6>Graduate Attributes</h6></th>
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

<c:url value="Graduate_Attributes_Mstr_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Graduate_Attributesreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$("#btn-update").hide();
		$("#btn-reload1").hide();

		mockjax1('search_graduate_attributes');
		table = dataTable('search_graduate_attributes');
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
			if( Validation()){
			   $("#Graduate_Attributesform").submit();
			}
		};
		document.getElementById('btn-update').onclick = function() {
			if( Validation()){
			   $("#Graduate_Attributesform").submit();
			}
		};
		document.getElementById('code').onkeypress = function() {
	 		return OnlyAlphaNumericTrim(event, this);
		};
// 		document.getElementById('code').onkeypress = function() {
// 			return isNumberKey0to4(event);
// 		};
		document.getElementById('graduate_attributes').onkeypress = function() {
	 		return noSpace(event, this);
		};
		
	});
	
     function setTimeLoadForTable(){
		
		document.querySelectorAll('.ADDGraduate_Attributes').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apIdAGE'+val).value;
				var hcode = document.getElementById('apcodeAGE'+val).value;
				var hprof = document.getElementById('approfAGE'+val).value;
				var hstatus = document.getElementById('apstatusAGE'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,hcode,hprof,hstatus);
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
	
	function data(search_graduate_attributes) {
		
		jsondata = [];
		var table = $('#' + search_graduate_attributes).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr('id')
				.toLowerCase();
		var orderType = order[0][1];
		var code = $("#code").val();
		var graduate_attributes = $("#graduate_attributes").val();
		var status = $("#status").val();

		$.post("getFilterGraduate_Attributes_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			code:code,
			graduate_attributes : graduate_attributes,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].code,j[i].graduate_attributes, j[i].action ]);
			}
		});
		$.post("getTotalGraduate_Attributes_dataCount?" + key + "=" + value, {
			Search : Search,
			code:code,
			graduate_attributes : graduate_attributes,
			status : status
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
	function editData(id,code,graduate_attributes,status) {
		$("#btn-update").show();
		$("#btn-save").hide();
		$("#tbl").hide();
		$("#btn-reload").hide();
		$("#btn-reload2").hide();
		$("#btn-reload1").show();
		
		
		document.getElementById('lbladd').innerHTML = " Update ";
		$("input#code").val(code);
		$("input#graduate_attributes").val(graduate_attributes);
		$("select#status").val(status);
		document.getElementById('id').value=id;
	}
	
	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function Validation() {
		if ($("#code").val().trim() == "") {
			alert("Please Enter Code.");
			$("input#code").focus();
			return false;
		}
		if ($("#graduate_attributes").val().trim() == "") {
			alert("Please Enter Graduate Attributes.");
			$("input#graduate_attributes").focus();
			return false;
		}
		
		if (isNaN($("#graduate_attributes").val()) == false) {
			alert("Please Enter Valid Graduate Attributes.");
			$("input#graduate_attributes").focus();
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