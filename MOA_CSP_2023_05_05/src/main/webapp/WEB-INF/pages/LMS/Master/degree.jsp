<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="js/watermark/common.js"></script>
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
					<h2><span id="lbladd"></span>Degree Master</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item">
								<a href="commonDashboard">Dashboard</a>
							</li>
							<li class="breadcrumb-item active" aria-current="page">Degree Master</li>
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
                <form:form action="Degree_Master_action" method="POST"  modelAttribute="Degree_Master_cmd">
				<div class="card-style mb-30">
				<div class="custom-field-block">
					<h6 class="mb-25">Degree Master</h6>
						<div class="row">

							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							
								<div class="select-style-1">
									<label for="username">Type Of Degree<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="type_of_degree" id="type_of_degree" class="singleselect form-control form-control-lg">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${gettype_of_degree}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>

								</select>
									</div>
								</div>	
								<div class="input-style-1">
								<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
                                </div>						
								
							
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-2">
									<label for="username">Degree<span class="mandatory">*</span></label>
									<input id="degree_name" name="degree_name" autocomplete="off" maxlength="50"
										placeholder="Degree" /> 
									<input type="hidden" id="id" name="id" value="0" class="mt-3" autocomplete="off" />
									</div>								
							</div>
							
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								
									<div class="select-style-1">
										<label for="username">Profession<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="semester" id="semester" class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${gettermListforSet}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								
							</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-2">
									<label for="username">Degree Code<span class="mandatory">*</span></label> 
									<input id="degree_code" name="degree_code" class="form-control"
									autocomplete="off" maxlength="10" placeholder="Degree Code" />
								</div>
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="username">Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status" class="singleselect form-control form-control-lg">
									<c:forEach var="item" items="${ActiveInActiveList}" varStatus="num">
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
								<input id="btn-save" class="main-btn info-btn btn-hover btnsave" type="submit" value="Save">
							</li>
							<li>
								<a href="degree_master_url" class="main-btn dark-btn n btn-hover btnreset" type="button">Reset</a>
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
						<table class="table" id="search_degree_master">
							<thead>
								<tr>
									<th id="1"><h6>Sr No</h6></th>
									<th id="2"><h6>Type of degree</h6></th>
									<th id="3"><h6>Degree</h6></th>
									<th id="4"><h6>Profession</h6></th>
									<th id="5"><h6>Degree code</h6></th>
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


<c:url value="Edit_Degree_mstrUrl" var="Edit_Degree_mstrUrl" />
<form:form action="${Edit_Degree_mstrUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="delete_Degree_mstr_Url" var="delete_Degree_mstr_Url" />
<form:form action="${delete_Degree_mstr_Url}" method="post"
	id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		mockjax1('search_degree_master');
		table = dataTable('search_degree_master');
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
	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	
//		document.getElementById('semester').onkeypress = function() {
//			return isNumberKey0to9(event);
//		};
	
	document.getElementById('degree_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	
//		document.getElementById('degree_name').oninput = function () {
//			 this.value=this.value.toUpperCase()
//			};	
		});
	

function setTimeLoadForTable(){
		
			document.querySelectorAll('.ADDDegree').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var dgid = document.getElementById('DegId'+val).value;
				var dgname = document.getElementById('DegName'+val).value;
				var dgstatus = document.getElementById('DegStatus'+val).value;
				
 				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					EditData(dgid,dgname,dgstatus);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.DeleteDeg').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var dgdid = document.getElementById('DEDegId'+val).value;
				
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(dgdid);
				} else {
					return false;
				}
			})
		});
	}
	
	function data(search_degree_master) {
		jsondata = [];
		var table = $('#' + search_degree_master).DataTable();
		var info = table.page.info();
		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var type_of_degree = $("#type_of_degree").val();
		var degree_name = $("#degree_name").val();
		var semester = $("#semester").val();
		var degree_code = $("#degree_code").val();
		var status = $("#status").val();

		$.post("getFilter_degree_master_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			type_of_degree : type_of_degree,
			degree_name : degree_name,
			semester : semester,
			degree_code : degree_code,
			status : status
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].sr_no, j[i].type_of_degree, j[i].degree_name,
						j[i].prof_name,j[i].degree_code, j[i].action ]);
			}
		});
		$.post("getTotal_degree_master_dataCount?" + key + "=" + value, {
			Search : Search,
			type_of_degree : type_of_degree,
			degree_name : degree_name,
			semester : semester,
			degree_code : degree_code,
			status : status
		}, function(j) {
			FilteredRecords = j;
		});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
	function EditData(id) {
		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}
	
	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function Validation() {
		
		if ($("select#type_of_degree").val() == 0) {
			alert("Please Select Type of Degree");
			$("select#type_of_degree").focus();
			return false;
		}
		
		if ($("#degree_name").val().trim() == "") {
			alert("Please Enter Degree");
			$("input#degree_name").focus();
			return false;
		}
		if ($("#semester").val().trim() == "") {
			alert("Please select Profession");
			$("select#semester").focus();
			return false;
		}
		if ($("#degree_code").val().trim() == "") {
			alert("Please Enter Degree Code");
			$("input#degree_code").focus();
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
</script>