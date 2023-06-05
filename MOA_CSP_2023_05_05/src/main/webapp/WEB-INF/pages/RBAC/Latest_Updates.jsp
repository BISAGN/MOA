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
<script src="js/Calender/datePicketValidation.js"></script>
<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Latest Updates</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Latest Updates
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
					<form:form name="Latest_UpdatesUrl" id="Latest_UpdatesUrl" action="Latest_UpdatesUrlaction"
						method="post" class="form-horizontal" modelAttribute="updatecmd" >
						<div class="card-style mb-30">
							<h6 class="mb-25">Latest Updates</h6>
							<div class="row">
								
								 <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="text-input">Updates<span class="mandatory">*</span></label>
										<textarea id="updates" name="updates" rows="5"
											cols="300" autocomplete="off" placeholder="Enter Your Updates"></textarea>


									</div>
								</div>
								
								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="input-style-2">
										<label>From Date <strong class="mandatory"> *</strong></label>
										<input type="text" name="from_date" id="from_date" maxlength="10" class="form-control-sm form-control effect-9 " autocomplete="off" placeholder="DD/MM/YYYY">
									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="input-style-2">
										<label>To Date <strong class="mandatory">* </strong></label> 
										<input type="text" name="to_date" id="to_date" maxlength="10" class="form-control-sm form-control effect-9 " 	autocomplete="off" placeholder="DD/MM/YYYY">
									</div>
								</div>
								<div class="col-12 col-lg-4 col-md-4 col-sm-12">
									<div class="input-style-2">
										<label>URL<span class="mandatory">*</span></label> <input
											type="text" id="url" name="url" autocomplete="off"
											maxlength="50" placeholder="Enter URL" />
									</div>
									<!-- end input -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
										<label for="username">Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status" class="singleselect form-control form-control-lg">
												<c:forEach var="item" items="${ActiveInActiveList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
								</div>
								

									<!-- end select -->
								</div>
								<div class="input-style-form-check">
								   <div class="form-check checkbox-style mb-20">
									<input class="form-check-input" type="checkbox" value="Ayush Portal" id="checkbox-1" name="checkbox-1"/> 
									<label class="form-check-label" for="checkbox-1"> Ayush Port</label>
<!-- 									<input type="hidden" id="ayu_port" name="ayu_port" /> -->
								  </div>
								   <div class="form-check checkbox-style mb-20">
									<input class="form-check-input" type="checkbox" value="NCH Portal" id="checkbox-2" name="checkbox-2"/> 
									<label class="form-check-label" for="checkbox-2"> NCH Port </label>
<!-- 									<input type="hidden" id="nch_port" name="nch_port" /> -->
								  </div>
								  <div class="form-check checkbox-style mb-20">
									<input class="form-check-input" type="checkbox" value="NCISM Portal" id="checkbox-3" name="checkbox-3" /> 
							       		<label class="form-check-label" for="checkbox-3"> NCISM Port </label>
<!-- 									<input type="hidden" id="ncism_port" name="ncism_port" /> -->
						   		  </div>
						       </div>
								
								
								
								<input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
								

							<ul class="buttons-group mainbtn">

								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit"  value="Save" />
								</li>
								<li><a href=Latest_UpdatesUrl
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
									<th id="${item.id}"><h6>Updates</h6></th>
									<th><h6>From Date</h6></th>
									<th><h6>To Date</h6></th>
									<th><h6>URL</h6></th>
									<th><h6>Updates For</h6></th>
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
</div>
</section>
<c:url value="getSearch_Latest_Updates" var="getSearch_Latest_Updates" />
<form:form action="${getSearch_Latest_Updates}" method="post" id="searchForm"
	name="searchForm" modelAttribute="searchUrl2">
	<input type="hidden" name="searchUrl2" id="searchUrl2" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_Latest_UpdatesUrl" var="Edit_Latest_UpdatesUrl" />
<form:form action="${Edit_Latest_UpdatesUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3">
</form:form>

<c:url value="delete_Latest_Updates_Action" var="delete_Latest_Updates_Action" />
<form:form action="${delete_Latest_Updates_Action}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4" value="0" />
</form:form>

<c:url value="Marque_Masterreport2" var="searchUrlp" />
<form:form action="${searchUrlp}" method="post" id="search2"
	name="searcho" modelAttribute="comdo">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>
<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {
	
// 	const today = new Date();
// 	const yyyy = today.getFullYear();
// 	let mm = today.getMonth() + 1;
// 	let dd = today.getDate();

// 	if (dd < 10) dd = '0' + dd;
// 	if (mm < 10) mm = '0' + mm;

// 	const formattedToday = dd + '/' + mm + '/' + yyyy;

	datepicketDate('from_date');
	$( "#from_date").datepicker( "option", "maxDate", null);
// 	setMinDate('from_date',formattedToday);
	datepicketDate('to_date');
	$( "#to_date").datepicker( "option", "maxDate", null);
// 	setMinDate('to_date',formattedToday);
	
	mockjax1('search_system');
	table = dataTable('search_system');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	
	$('.UpperClassName').keyup(function() {
		this.value = this.value.toUpperCase();
	});
	
});
document.addEventListener('DOMContentLoaded', function () {		
	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};

// 	document.getElementById('checkbox-1').onclick = function() {
// // 		if(document.getElementById("checkbox-1").checked){
// // 			$("#ayu_port").val(1);
// // 		}else{
// // 			$("#ayu_port").val(0);
// // 		}
// 	};
// 	document.getElementById('checkbox-2').onclick = function() {
// // 		if(document.getElementById("checkbox-2").checked){
// // 			$("#nch_port").val(2);
// // 		}else{
// // 			$("#nch_port").val(0);
// // 		}
// 	};
// 	document.getElementById('checkbox-3').onclick = function() {
// 		if(document.getElementById("checkbox-3").checked){
// 			$("#ncism_port").val(3);
// 		}else{
// 			$("#ncism_port").val(0);
// 		}
// 	};

});

function setTimeLoadForTable(){
	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	}; 

// 	document.getElementById('Marque').onkeypress = function() {
// 		return onlyAlphabetsStringSpace(this, event);
// 	};

// 	document.getElementById('Marque').onblur = function() {
// 		return onlyAlphabetsStringSpace(this, event);
// 	}; 
	/* document.getElementById('hostel_address').onkeypress = function() {
		return Validation();
	}; */
	/* document.getElementById('no_of_rooms').onkeypress = function() {
		return Validation();
	}; */
	/* document.getElementById('institute_id').onkeypress = function() {
		return Validation();
	}; */
	document.querySelectorAll('.ADDupdates_Master ').forEach((items, index) => {
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
	var updates = $("#updates").val();
	var from_date = $("#from_date").val();
	var to_date = $("#to_date").val();
	var url = $("#url").val();
// 	var Marque = $("#nch_port").val();
// 	var Marque = $("#ncism_port").val();
	var status = $("#status").val();
	
// 	if(hostel_name == null || hostel_name == "null"){
// 		hostel_name = ""
// 	}
// 	if(hostel_address == null){
// 		hostel_address = ""
// 	}
// 	if(no_of_rooms == null){
// 		no_of_rooms = ""
// 	}
	
	$.post("getFilterLatest_Updates_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		updates : updates,
		from_date : from_date,
		to_date : to_date,
		url : url,
// 		ayu_port : ayu_port,
// 		nch_port : nch_port,
// 		ncism_port : ncism_port,
		status : status
	}, function(j) {
console.log(j)
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].ser, j[i].updates,j[i].from_date,j[i].to_date,j[i].url,j[i].updates_for, j[i].action]);
		}
	});
	$.post("getTotalLatest_Updates_dataCount?" + key + "=" + value, {
		Search : Search,
		updates  : updates,
		from_date  : from_date,
		to_date  : to_date,
		url : url,
// 		ayu_port  : ayu_port,
// 		nch_port  : nch_port,
// 		ncism_port  : ncism_port,
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

function Search() {
	$("#updates1").val($('#updates').val());
	$("#from_date1").val($('#from_date').val());
	$("#to_date1").val($('#to_date').val());
	$("#url1").val($('#url').val());
// 	$("#ayu_port1").val($('#ayu_port').val());
// 	$("#nch_port1").val($('#nch_port').val());
// 	$("#ncism_port1").val($('#ncism_port').val());
	$("#status1").val($('#status').val());

	document.getElementById('searchForm').submit();
}
function Validation() {
	if ($("#updates").val() =="" ) {
		alert("Please Enter Updates.");
		$("input#updates").focus();
		return false;
	}
	var res = CheckNullorBlank('from_date');
	if (res !== "true") {
		alert(res + "From Date");
		$('#from_date').focus();
		return false;
	}
	var res = CheckNullorBlank('to_date');
	if (res !== "true") {
		alert(res + "To Date");
		$('#to_date').focus();
		return false;
	}
	
	var from_date = $("#from_date").val();
	var to_date = $("#to_date").val();
	
	var s2 = from_date.split('/');
	from_date = s2[2]+"-"+s2[1]+"-"+s2[0];
	
	var e2 = to_date.split('/');
	to_date = e2[2]+"-"+e2[1]+"-"+e2[0]
	
	from_date = new Date(from_date);
	to_date = new Date(to_date);
	
	 	if(from_date != "DD/MM/YYYY" && to_date !="DD/MM/YYYY"){
			
			if(from_date > to_date ){
				alert("To Date can not be less than From Date.")
				$("#ending_date").val("DD/MM/YYYY");
				return false ;
			}
	 	}
	 	
	 if ($("#url").val() =="" ) {
			alert("Please Enter URL.");
			$("input#url").focus();
			return false;
	}
	 
	if ($("select#status").val() == "2") {
		alert("Only Select Active Status.");
		$("select#status").focus();
		return false;
	}
	return true;
} 

</script>