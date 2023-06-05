<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script> -->
<!-- <script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> -->
<!-- <script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> -->
<!-- <script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script type="text/javascript" src="js\watermark\common.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
					<span id="lbladd"></span>
						<h2>No. Of Attempt</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">No. Of Attempt 
									</li>
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
					<form:form name="No_Of_Attempt" id="No_Of_Attempt" action="No_Of_Attempt_Action"
						method="post" class="form-horizontal" modelAttribute="No_Of_Attempt_CMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">No. Of Attempt </h6>
							<div class="row">
							
							  <div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">System<span class="mandatory">*</span></label>
									<div class="select-position">
									<select name="system_id" id="system_id">
									  <option value="0">--Select--</option>
										<c:forEach var="item" items="${getSystemList}" varStatus="num">
													<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
										</c:forEach>
								   </select>
							     </div>
								</div>					
							  </div>
							  <div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Degree<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="degree_id" id="degree_id" class="form-control">
												<option value="0">--Select--</option>
											</select>
										</div>
									</div>								
							  </div>
							  <div class="col-12 col-sm-12 col-md-6 col-lg-3">
								 <div class="select-style-1">
									<label for="text-input">Professional<span class="mandatory">*</span></label>
									  <div class="select-position">
											<select name="professional_id" id="professional_id">
									      <option value="0">--Select--</option>
										<c:forEach var="item" items="${getprofessionalList}" varStatus="num">
											<option value="${item.id}" name="${item.professional}">${item.professional}</option>
										</c:forEach>
								     </select>										
								 </div>
								 </div>								
							   </div>
					
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="input-style-2">
										<label>No. Of Attempt<span class="mandatory">*</span></label> <input
											type="number" id="no_of_attempt" name="no_of_attempt"
											class="autocomplete txt-transupp"
											autocomplete="off" maxlength="100" placeholder="Enter No.Of Attempt" />
										
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="select-style-1">
										<label for="username">Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status">
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
								<li><a href="Document_Attachments_Url"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
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
						<table class="table" id="search_system">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th id="${item.id}"><h6>System</h6></th>
									<th id="${item.id}"><h6>Degree</h6></th>
									<th id="${item.id}"><h6>Professional</h6></th>
									<th id="${item.id}"><h6>No. Of Attempt</h6></th>
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

<c:url value="getSearch_No_Of_Attempt_Master" var="getSearch_No_Of_Attempt_Master" />
<form:form action="${getSearch_No_Of_Attempt_Master}" method="post" id="searchForm"
	name="searchForm" modelAttribute="searchUrl2">
	<input type="hidden" name="searchUrl2" id="searchUrl2" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_No_Of_Attempt_MasterUrl" var="Edit_No_Of_Attempt_MasterUrl" />
<form:form action="${Edit_No_Of_Attempt_MasterUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3">
</form:form>

<c:url value="delete_No_Of_Attempt_Action" var="delete_No_Of_Attempt_Action" />
<form:form action="${delete_No_Of_Attempt_Action}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4" value="0" />
</form:form>

<c:url value="Attachment_Masterreport2" var="searchUrlp" />
<form:form action="${searchUrlp}" method="post" id="search2"
	name="searcho" modelAttribute="comdo">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>
<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {

	
	mockjax1('search_system');
	table = dataTable('search_system');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$('.UpperClassName').keyup(function() {
		this.value = this.value.toUpperCase();
	});
});
function getdegreelistbysystem111() {
	var system_name = $("#system_id").val();
	$
			.post('getDegreeListbysystem2?' + key + "=" + value, {
				system_name : system_name
			})
			.done(
					function(j) {
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#degree_id").html(options);
					});
}
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	document.getElementById('system_id').onclick = function() {
		getdegreelistbysystem111();
	};

});

function setTimeLoadForTable(){
	
	 

	document.querySelectorAll('.ADDNo_Of_Attempt__Master ').forEach((items, index) => {
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

function data(search_system ) {
	
	jsondata = [];
	var table = $('#' +search_system ).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];
	var system_id = $("#system_id").val();
	var degree_id = $("#degree_id").val();
	var professional_id = $("#professional_id").val();
	var no_of_attempt = $("#no_of_attempt").val();
	var status = $("#status").val();
	
	$.post("getFilterNo_Of_Attempt_Master_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		system_id : system_id,
		degree_id : degree_id,
		professional_id : professional_id,
		no_of_attempt : no_of_attempt,
		status : status
		
	}, function(j) {
console.log(j)
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].ser,j[i].system_name,j[i].degree_name,j[i].professional,j[i].no_of_attempt,j[i].action]);
		}
	});
	$.post("getTotalNo_Of_Attempt_Master_dataCount?" + key + "=" + value, {
		Search : Search,
		system_id  : system_id, 
		degree_id : degree_id,
		professional_id : professional_id,
		no_of_attempt : no_of_attempt,
		status : status
	}, function(j) {

		FilteredRecords = j;

	});
	setTimeout(setTimeLoadForTable, 1000);
}


function editData(id) {

	$("#id3").val(id);
	document.getElementById('updateForm').submit();
}

function deleteData(id) {
	
	$("#id4").val(id);
	document.getElementById('deleteForm').submit();
}


	function Validation() {

		if ($("#system_id").val().trim() == "0") {
			alert("Please Select System.");
			$("input#system_id").focus();
			return false;
		}
		if ($("#degree_id").val().trim() == "0") {
			alert("Please Select Degree.");
			$("input#degree_id").focus();
			return false;
		}
		if ($("#professional_id").val().trim() == "0") {
			alert("Please Select Professional.");
			$("input#professional_id").focus();
			return false;
		}
		if ($("#no_of_attempt").val().trim() == "") {
			alert("Please Enter No Of Attempt.");
			$("input#no_of_attempt").focus();
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
