<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>

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
						<h2>Approve Alumni Request</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Approve Alumni Request</li>
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
						<div class="card-style mb-30">
							<h6 class="mb-25">Create Event</h6>
							<div class="row">
								<input type="hidden" name="id" id="id" value="0" />
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Name <span class="mandatory"></span></label> <input
											type="text" id="name" name="name"
											class="autocomplete xt-transupp" autocomplete="off"
											maxlength="100" placeholder="Search by Name" />
									</div>
									<!-- end input -->
								</div>
								
								 <div class="col-lg-4 col-md-6 col-sm-12">
									<div class="select-style-1">
										<label>Status <strong class="mandatory">*
										</strong></label>
										<div class="select-position">
											<select name="status" id="status" class="form-control">
												<option value="0">--Pending--</option>
												<option value="1">Approve</option> 
												<option value="2">Reject</option>
											</select>
										</div>
									</div>
								</div>
								
							</div>
						</div>
						
						<div>
							<ul class="buttons-group mainbtn">

								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" value="Save" />
								</li>
								<li><a href="AppAlumSignupReqUrl"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>

							</ul>
						</div>
						<!-- end card -->
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_Student_Room_Request">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th><h6>Name</h6></th>
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

<c:url value="AppRejAlumReqUrl" var="AppRejAlumReqUrl" />
	<form:form action="${AppRejAlumReqUrl}" method="post" id="AppRejAlmForm"
	name="AppRejAlmForm" modelAttribute="ARId">
	<input type="hidden" name="ARId" id="ARId" value="0" />
	<input type="hidden" name="ARTmp" id="ARTmp" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {

	mockjax1('search_Student_Room_Request');
	table1 = dataTable('search_Student_Room_Request');
	
	$('#btn-reload').on('click', function() {
		table1.ajax.reload();
	});
	
});

 function data(tablename) {
	 
	jsondata = [];
	
 	var table = $('#' + tablename ).DataTable();
 	var info = table.page.info();
 	var pageLength = info.length;
 	var startPage = info.start;
 	var endPage = info.end;
 	var Search = table.search();
 	var order = table.order();
 	var orderColunm = $(table.column(order[0][0]).header()).html()
 			.toLowerCase();
	var orderType = order[0][1];
 	var status = $("#status").val();
 	var name = $("#name").val();

 	$.post("getAlumni_Request_data?" + key + "=" + value, {
 		startPage : startPage,
 		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm, 
		orderType : orderType,
		status : status,
		name : name
 	}, function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].ser,j[i].name,j[i].action]);
		}
	});
 	
 	$.post("getTotalAlumni_Request_data_dataCount?" + key + "=" + value, {
 		Search : Search,
 		status : status,
 		name : name
 	}, function(j) {
		FilteredRecords = j;
 	}).fail(function(xhr, textStatus, errorThrown, exception) {
	  	 alert(errorThrownMsg(xhr,exception));
	});
 	 
 	setTimeout(setTimeLoadForTable, 1000);
 }
 
 function setTimeLoadForTable(){
	 
		document.querySelectorAll('.ADDApprove_Request ').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apIdAGE'+val).value;
				
				if (confirm('Are You Sure You Want to Approve Detail ?')) {
					AppRejData(hid,1);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.ADDReject_Request ').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('reIdAGE'+val).value;
				
				if (confirm('Are You Sure You Want to Reject Detail ?')) {
					AppRejData(hid,2);
				} else {
					return false;
				}
			})
		});

	}

 function AppRejData(id,tmp) {
 	$("#ARId").val(id);
 	$("#ARTmp").val(tmp);
 	document.getElementById('AppRejAlmForm').submit();
 }

</script>		