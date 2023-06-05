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
<script type="text/javascript" src="js/watermark/common.js"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>


<section class="dashboard-page">
<div class="container-fluid">

	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>Specialization Master</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">Specialization Master</li>
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
			<div class="col-12">
				<!-- input style start -->
               <form:form action="Specialization_Action" method="POST" class="form-horizontal" modelAttribute="SpecializationCMD"  enctype="multipart/form-data">
					<div class="card-style mb-30">
					<h6 class="mb-25">Specialization Master</h6>
						<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="text-input">Degree:<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="degree" id="degree">
								        	<option value="0">---Select---</option>
								        	<c:forEach var="item" items="${getdegreeList}" varStatus="num">
												<option value="${item.id}" name="${item.id}">${item.degree_name}</option> 
											</c:forEach>
										</select>
									</div>
								</div>								
								
								<!-- end select -->
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Specialization Name<span class="mandatory">*</span></label> <input
											type="text" id="specialization_name" name="specialization_name"
											 placeholder="Specialization Name" />
										
									</div>
									<!-- end input -->
								</div>
							
								<div class="input-style-2 mt-3">
										<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
								</div>																																			
							</div>
							
						</div>
										<div class="input-style-2 mt-3">
												<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
										</div>	

						<ul class="buttons-group mainbtn">
								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" value="Save" />
								</li>
								<li><a href="specialization_detailsUrl"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
						</ul>
					</form:form>
				</div>
	
			</div>
		</div>
	</div>
			<div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_specialization">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th id="${item.id}"><h6>Degree</h6></th>
									<th id="${item.id}"><h6>Specialization Name</h6></th>
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






<c:url value="getSearch_Specialization" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="specializationForm"
	name="searchForm" modelAttribute="Academic_details1">
	<input type="hidden" name="Degree1" id="Degree1" value="0" />
	<input type="hidden" name="Specializtion_name1" id="Specializtion_name1" value="0" />
	
</form:form>

<c:url value="Edit_Specializtion_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id8">
	<input type="hidden" name="id8" id="id8">
</form:form>

<c:url value="delete_Url28" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id28">
	<input type="hidden" name="id28" id="id28" value="0" />
</form:form>

<c:url value="Systemreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>
<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		
		mockjax1('search_specialization');
		table = dataTable('search_specialization');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
// 		if(window.location.href.includes("msg"))
// 		{
// 			 var url = window.location.href.split("?msg")[0];
// 			 window.location = url;
// 		}
	});
	
	function setTimeLoadForTable(){
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		document.getElementById('degree').onkeypress = function() {
 			return onlyAlphabetsStringSpace(this, event);
 		};
		document.getElementById('specialization_name').onkeypress = function() {
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
	

function data(search_specialization) {
		
		jsondata = [];
		var table = $('#' + search_specialization).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var degree = $("#degree").val();
		var specialization_name = $("#specialization_name").val();
		
 		$.post("getFilterspecialization_data?" + key + "=" + value, {
 			startPage : startPage,
 			pageLength : pageLength,
 			Search : Search,
 			orderColunm : orderColunm,
 			orderType : orderType,
 			degree : degree,
 			specialization_name : specialization_name
 			
 		},
 		function(j) {

 			for (var i = 0; i < j.length; i++) {
 				jsondata.push([ j[i].ser, j[i].degree_name, j[i].specialization_name, j[i].action ]);
 			}
 		});
		$.post("getTotalspecialization_dataCount?" + key + "=" + value, {
 			Search : Search,
 			degree : degree,
 			specialization_name : specialization_name,
 			
 			
 		}, function(j) {

 			FilteredRecords = j;

 		});
 		setTimeout(setTimeLoadForTable, 1000);
 	}
	
	function editData(id) {

	$("#id8").val(id);
	document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
	$("#id28").val(id);
	document.getElementById('deleteForm').submit();
	}	


	function Search() {
		$("#Degree1").val($('#degree').val());
		$("#Specializtion_name1").val($('#specialization_name').val());
		document.getElementById('specializationForm').submit();
	}
	

	function Validation() {
		if ($("#degree").val().trim() == "") {
			alert("Please Enter Degree.");
			$("input#degree").focus();
			return false;
		}

		if ($("#specialization_name").val().trim() == "") {
			alert("Please Enter Specialization Name.");
			$("input#specialization_name").focus();
			return false;
		}
		
		return true;
	}
	

</Script>
