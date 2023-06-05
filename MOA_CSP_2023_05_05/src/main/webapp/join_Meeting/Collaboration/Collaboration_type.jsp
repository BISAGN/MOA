<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						<h2><span id="lbladd"></span> Collaboration Type</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Collaboration
									Type</li>
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
					<form:form name="Collaboration_type_form" id="Collaboration_type_form" action="Collaboration_type_Action"
						method="post" class="form-horizontal" modelAttribute="Collaboration_type_CMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Collaboration Type</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Collaboration Type<span class="mandatory">*</span></label> <input
											type="text" id="collaboration_type" name=collaboration_type
											class="autocomplete xt-transupp"
											autocomplete="off" maxlength="100" placeholder="collaboration Type" />
										
									</div>
									<!-- end input -->
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
									<div class="input-style-2 mt-3">
										<input type="hidden" id="id" name="id" value="0"
											autocomplete="off" />
									</div>

									<!-- end select -->
								</div>

							</div>

							<ul class="buttons-group mainbtn">

								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" value="Save" />
								</li>
								<li><a href="Collaboration_type_Url"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
<!-- 								<li><a -->
<!-- 									class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" -->
<!-- 									id="pdfex"><i class="lni lni-printer" id="printId" -->
<!-- 										value="PDF" title="Export to PDF"></i> PDF </a></li> -->

<!-- 								<li><a -->
<!-- 									class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" -->
<!-- 									id="btnExport"><i class="lni lni-printer" value="PDF" -->
<!-- 										title="Export to PDF"></i> EXCEL </a></li> -->
							</ul>
						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>


		<div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="Collboration_type">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th id="${item.id}"><h6>Collaboration Type</h6></th>
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

<c:url value="getSearch_Collaboration_type_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Certificate_name1">
	<input type="hidden" name="Certificate_name1" id="Collaboration_type_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_Collaboration_type_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="Collaboration_type_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>


<c:url value="getCollaboration_type_Report_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="collaboration_type1" id="collaboration_type1" value="0" />
	
</form:form>
<c:url value="Collaboration_type_Report_PDF" var="searchUrl1" />
<form:form action="${searchUrl1}" method="post" id="search3"
	name="search3" modelAttribute="comd1">
	<input type="hidden" name="collaboration_type2" id="collaboration_type2" value="0" />
</form:form>
<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('Collboration_type');
		table = dataTable('Collboration_type');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
	});
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('btnExport').onclick = function() {
			getCollaboration_type_Report_Excel();
		};
		document.getElementById('pdfex').onclick = function() {
			Collaboration_type_Report_PDF();
		};
	});
	function setTimeLoadForTable(){
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

		document.getElementById('collaboration_type').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		
		document.querySelectorAll('.ADDCollaboratontype').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apIdAGE'+val).value;
				var hprof = document.getElementById('aprepocAGE'+val).value;
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
	
	function data(Collboration_type) {
		
		jsondata = [];
		var table = $('#' + Collboration_type).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var collaboration_type = $("#collaboration_type").val();
		var status = $("#status").val();

		$.post("getFilterCollaboration_typedata?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			collaboration_type : collaboration_type,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].collaboration_type, j[i].action ]);
			}
		});
		$.post("getTotalCollaboration_typedataCount?" + key + "=" + value, {
			collaboration_type : collaboration_type,
			Search : Search,
			status : status
		
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
	function editData(id,collaboration_type,status) {
		
		document.getElementById('lbladd').innerHTML = "Update ";
		$("input#collaboration_type").val(collaboration_type);
		$("select#status").val(status);
		$("#btn-save").val("Update");
		document.getElementById('id').value=id;
	}
	

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function Search() {
		$("#collaboration_type").val($('#collaboration_type').val());
		$("#status1").val($('#status').val());
		document.getElementById('searchForm').submit();
	}

	function Validation() {

		if ($("#collaboration_type").val().trim() == "") {
			alert("Please Enter Collaboration Type.");
			$("input#collaboration_type").focus();
			return false;
		}
		if($("#btn-save").val() == "Save"){
			if ($("select#status").val() == "2") {
				alert("Only Select Active Status.");
				$("select#status").focus();
				return false;
			}
		}
		return true;
	}
	function getCollaboration_type_Report_Excel() {
		// 		alert(1);
		$("#collaboration_type1").val($("#collaboration_type").val());
		
		document.getElementById('search2').submit();

	}
	function Collaboration_type_Report_PDF() {
		// 		alert("HIIIIIIIIII");
		$("#collaboration_type1").val($("#collaboration_type").val());
		

		document.getElementById('search3').submit();

	}

</Script>
