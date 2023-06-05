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
						<h2> <span id="lbladd"></span> Collaboration Sector</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Collaboration Sector </li>
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
					<form:form name="Collaboration_sector_form" id="Collaboration_sector_form" action="Collaboration_sector_Action"
						method="post" class="form-horizontal" modelAttribute="Collaboration_sector_CMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Collaboration Sector</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Collaboration Sector<span class="mandatory">*</span></label> <input
											type="text" id="sector_type" name=sector_type
											class="autocomplete xt-transupp"
											autocomplete="off" maxlength="100" placeholder="Collaboration Sector" />
										
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
								<li><a href=Collaboration_sector_Url
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
<!-- 								<li><a -->
<!-- 									class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" -->
<!-- 									id="pdfex"><i class="lni lni-printer" id="printId" -->
<!-- 										value="PDF" title="Export to PDF"></i> PDF </a></li> -->

<!-- 								<li><a -->
<!-- 									class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" -->
<!-- 									id="btnExport"><i class="lni lni-printer" value="PDF" -->
<!-- 										title="Export to PDF"></i> EXCEL </a></li> -->
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
						<table class="table" id="Collaboration_sector">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th id="${item.id}"><h6>Collaboration Sector</h6></th>
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

<c:url value="getSearch_Collaborationsector" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Registration_name1">
	<input type="hidden" name="Registration_name1" id="Collaborationsector_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_Collaboration_sector_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="Collaboration_sector_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<%-- <c:url value="Collaborationsectorreport" var="searchUrl" /> --%>
<%-- <form:form action="${searchUrl}" method="post" id="search2" --%>
<%-- 	name="search2" modelAttribute="comd1"> --%>
<!-- 	<input type="hidden" name="typeReport1" id="typeReport1" value="0" /> -->
<%-- </form:form> --%>

<c:url value="getCollaboration_sector_Report_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="sector_type1" id="sector_type1" value="0" />
	
</form:form>
<c:url value="Collaboration_sector_Report_PDF" var="searchUrl1" />
<form:form action="${searchUrl1}" method="post" id="search3"
	name="search3" modelAttribute="comd1">
	<input type="hidden" name="sector_type2" id="sector_type2" value="0" />
	
</form:form>
<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('Collaboration_sector');
		table = dataTable('Collaboration_sector');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
	});
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('btnExport').onclick = function() {
			getCollaboration_sector_Report_Excel();
		};
		document.getElementById('pdfex').onclick = function() {
			Collaboration_sector_Report_PDF();
		};
	});
	function setTimeLoadForTable(){
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

		document.getElementById('sector_type').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		
		document.querySelectorAll('.ADDCollaboratonsector').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apIdAGES'+val).value;
				var hprof = document.getElementById('aprepocAGES'+val).value;
				var hstatus = document.getElementById('apstatusAGES'+val).value;
				
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
	
	function data(Collaboration_sector) {
		
		jsondata = [];
		var table = $('#' + Collaboration_sector).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var sector_type = $("#sector_type").val();
		var status = $("#status").val();

		$.post("getFilterCollaboration_sectordata?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			sector_type : sector_type,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].sector_type, j[i].action ]);
			}
		});
		$.post("getTotalCollaboration_sectordataCount?" + key + "=" + value, {
			sector_type : sector_type,
			Search : Search,
			status : status
		
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
	function editData(id,sector_type,status) {
		
		document.getElementById('lbladd').innerHTML = "Update";
		$("input#sector_type").val(sector_type);
		$("select#status").val(status);
		$("#btn-save").val("Update");
		document.getElementById('id').value=id;
	}
	

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function Search() {
		$("#sector_type").val($('#sector_type').val());
		$("#status1").val($('#status').val());
		document.getElementById('searchForm').submit();
	}

	function Validation() {

		if ($("#sector_type").val().trim() == "") {
			alert("Please Enter Sector Type.");
			$("input#sector_type").focus();
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
	function getCollaboration_sector_Report_Excel() {
		// 		alert(1);
		$("#sector_type1").val($("#sector_type").val());
		
		document.getElementById('search2').submit();

	}
	function Collaboration_sector_Report_PDF() {
		// 		alert("HIIIIIIIIII");
		$("#sector_type1").val($("#sector_type").val());
		

		document.getElementById('search3').submit();

	}

</Script>
