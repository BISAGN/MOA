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
					<h2>District Master</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item">
								<a href="http://localhost:8024/admin/commonDashboard">Dashboard</a>
							</li>
							<li class="breadcrumb-item active" aria-current="page">District Master</li>
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
                <form:form name="District" id="District" action="DistrictAction" method="post" modelAttribute="DistrictCMD">
				<div class="card-style mb-30">
				<div class="custom-field-block">
					<h6 class="mb-25">District Master</h6>
						<div class="row">

							<div class="col-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label>Country<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="country_id" id="country_id" class="singleselect form-control form-control-lg">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedCountryName}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>

										</select>
									</div>
								</div>								
								
								<!-- end select -->
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label>State<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="state_id" id="state_id" class="singleselect form-control form-control-lg">
											<option value="0">--Select--</option>
<%-- 											<c:forEach var="item" items="${getMedStateName}" --%>
<%-- 												varStatus="num"> --%>
<%-- 												<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 											</c:forEach> --%>
										</select>
										</div>
									</div>								
								
								<!-- end select -->
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label>District<span class="mandatory">*</span></label> 
									<input type="text" id="district_name" name="district_name" maxlength="50" autocomplete="off"
											 placeholder="District"> 
											<input type="hidden" id="id" name="id" class="form-control" autocomplete="off">
								</div>
								<!-- end input -->
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label>Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status" class="singleselect form-control form-control-lg">

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
							<li>
							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch" type="button"><i class="lni lni-search-alt"></i>Search</a>
							</li>
							<li>
								<input id="btn-save" class="main-btn info-btn btn-hover btn-save btnsave" value="Save" type="submit">
							</li>
							<li>
								<a href="District" class="main-btn dark-btn n btn-hover btnreset" type="button">Reset</a>
							</li>
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
			<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="District_Search">
							<thead>
								<tr>
									<th><h6>Sr No</h6></th>
									<th><h6>Country</h6></th>
									<th><h6>State</h6></th>
									<th><h6>District</h6></th>
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
  </form:form>
			</div>
		</div>
	</div>
</div>
</section>

<c:url value="getSearch_District_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="District_name1">
	<input type="hidden" name="country_id1" id="country_id1" />
	<input type="hidden" name="state_id1" id="state_id1" />
	<input type="hidden" name="District_name1" id="District_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_District" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="delete_District" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Districtreport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('District_Search');
		table = dataTable('District_Search');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		
		if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}

	});
</script>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		if ('${list.size()}' == "") {
			$("div#District_Search").hide();
		}
		if ('${country_id1}' != "") {
			$("#country_id").val('${country_id1}');
		}
		if ('${state_id1}' != "") {
			$("#state_id").val('${state_id1}');
		}
		if ('${District_name1}' != "") {
			$("#district_name").val('${District_name1}');
		}
		if ('${status1}' != "") {
			$("Select#status").val('${status1}');
		}
	});
	
document.addEventListener('DOMContentLoaded', function () {	
	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	
	document.getElementById('country_id').onchange = function() {
		fn_pre_domicile_Country();
	};
	
	document.getElementById('district_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	
	document.getElementById('district_name').oninput = function () {
		 this.value=this.value.toUpperCase();
		};
		
		});
	
	function setTimeLoadForTable(){
		

		document.querySelectorAll('.ADDDistrict').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var dtid = document.getElementById('DistrictId'+val).value;
				var dtname = document.getElementById('DistName'+val).value;
				var dtstatus = document.getElementById('DistStatus'+val).value;
 				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(dtid,dtname,dtstatus);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.DeleteDist').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var dtdid = document.getElementById('DEDistId'+val).value;
				
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(dtdid);
				} else {
					return false;
				}
			})
		});
		
	}
	
	function data(search_system) {
		jsondata = [];
		var table = $('#' + search_system).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var country = $("#country_id").val();
		var state = $("#state_id").val();
		var district = $("#district_name").val();
		var status = $("#status").val();

		$.post("getFilterdistrict?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			country:country,
			state:state,
			district:district,
			status:status
			
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser,j[i].name,j[i].state_name,j[i].district_name,j[i].action ]);
			}
		});
		$.post("getTotaldistrict_dataCount?" + key + "=" + value, {

			Search : Search,
			country:country,
			state:state,
			district:district,
			status:status
		}, function(j) {
			
			FilteredRecords = j;

			});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
	function Search() {
		$("#state_id1").val($('#state_id').val());
		$("#country_id1").val($('#country_id').val());
		$("#District_name1").val($('#district_name').val());
		$("#status1").val($('#status').val());
		document.getElementById('searchForm').submit();
	}
	
	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function editData(id) {
		$("#id2").val(id);
		$("#updateForm").submit();
	}
	
	function Validation() {
		
		if ($("select#country_id").val() == 0) {
			alert("Please Select Country");
			$("select#country_id").focus();
			return false;
		}
		if ($("select#state_id").val() == 0) {
			alert("Please Select State");
			$("select#state_id").focus();
			return false;
		}
		if ($("input#district_name").val().trim() == "") {
			alert("Please Enter District");
			$("input#district_name").focus();
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
	

	function fn_pre_domicile_Country() {
		var text = $("#country_id option:selected").text();
		var country_id = $('select#country_id').val();
		$
				.post("getStateListFormcon1?" + key + "=" + value, {
					country_id : country_id
				})
				.done(
						function(j) {
							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#state_id").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}
</Script>
