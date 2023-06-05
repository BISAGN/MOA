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

<link href="js/tunneldesign/tunnelold.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- datatable style and js end-->

<div class="container-fluid">
<section class="dashboard-page">
	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>Create Webinar</h2>
				</div>
			</div>
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">Create Webinar</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</div>

	<div class="form-elements-wrapper">
		<div class="row">
			<div class="col-12">
                <form:form name="" id="" action="Create_Meeting_Action" method="post" class="form-horizontal" commandName="Create_Meeting_CMD"> 
					<div class="card-style mb-30">
					<h6 class="mb-25">Create Webinar</h6>
						<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-4 d-none">
								<div class="input-style-1">
									<label for="text-input">MeetingId <span class="mandatory"></span></label>
									<input type="text" id="meeting_id" name="meeting_id" maxlength="50" class="autocomplete UpperClassName txt-transupp""
										autocomplete="off"   />
								</div>						
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-1">
									<label for="text-input">Subject <span class="mandatory"></span></label>
									<input type="text" id="name" name="name" maxlength="50" class="autocomplete UpperClassName txt-transupp"
										autocomplete="off"   />
								</div>							
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-1">
									<label for="text-input">Attendee Password <span class="mandatory"></span></label>
									<input type="text" id="attendeepw_id" name="attendeepw_id" maxlength="50" class="autocomplete UpperClassName txt-transupp"
										autocomplete="off"   />
								</div>							
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-1">
									<label for="text-input">Moderator Password <span class="mandatory"></span></label>
									<input type="text" id="moderatorpw_id" name="moderatorpw_id" maxlength="50" class="autocomplete UpperClassName txt-transupp"
										autocomplete="off"   />
								</div>							
							</div>
<!-- 							<div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
<!-- 								<div class="input-style-1"> -->
<!-- 									<label for="text-input">Full Name <span class="mandatory"></span></label> -->
<!-- 									<input type="text" id="fullname_id" name="fullname_id" maxlength="50" class="autocomplete UpperClassName txt-transupp" -->
<!-- 										autocomplete="off"   /> -->
<!-- 								</div>							 -->
<!-- 							</div> -->
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-1">
									<label for="text-input">Welcome Message<span class="mandatory"></span></label>
									<textarea id="welcome" name="welcome" placeholder="Message" rows="5" maxlength="250"></textarea>
								</div>							
							</div>
							<div class="input-style-form-check_block">
								<div class="form-check checkbox-style mb-20 d-none">
									<input class="form-check-input" type="checkbox" id="checkbox-1"  checked /> 
									<label class="form-check-label" for="checkbox-1"> Record</label>
									<input type="hidden" id="recordhid" value="1" name="recordhid" />
								</div>
								<div class="form-check checkbox-style mb-20 d-none">
									<input class="form-check-input" type="checkbox" id="checkbox-2" checked/> 
									<label class="form-check-label" for="checkbox-2"> AutoStartRecording </label>
									<input type="hidden" id="autostartrechid" value="1" name="autostartrechid" />
								</div>
								<div class="form-check checkbox-style mb-20 d-none">
									<input class="form-check-input" type="checkbox" id="checkbox-3" checked/> 
									<label class="form-check-label" for="checkbox-3"> AllowStartStopRecording </label>
									<input type="hidden" id="allowstartstoprechid" value="1" name="allowstartstoprechid" />
								</div>
							</div>
						</div>		
						<ul class="buttons-group mainbtn">
								<li><a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input id="btn-create" class="main-btn info-btn btn-hover " type="submit" value="Create"></li>
								<li><a href="Create_Meeting_Url" class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
						</ul>
				</div>
				<input type="hidden" id="linkhid" name="linkhid" />
			</form:form>
		</div>
	</div>
 </div>
</section>
</div>

<div class="row">
	<div class="col-12">
		<div class="card-style mb-30">
			<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_create_meeting">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
<!-- 									<th><h6>MeetingId</h6></th> -->
									<th><h6>Subject</h6></th>
									<th><h6>Attendee Password</h6></th>
									<th><h6>Moderator Password</h6></th>
<!-- 									<th><h6>Full Name</h6></th> -->
									<th><h6>Welcome</h6></th>
<!-- 									<th><h6>Record</h6></th> -->
<!-- 									<th><h6>AutoStartRecording</h6></th> -->
<!-- 									<th><h6>AllowStartStopRecording</h6></th> -->
									<th><h6>Link</h6></th>
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

<c:url value="getSearch_Create_Meeting" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Create_meeting_name1">
	<input type="hidden" name="Create_meeting_name1" id="Create_meeting_name1" />
</form:form>
<c:url value="Create_Meeting_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>
    
<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
// 		var ec = ENC("meetingid", "test123");
// 		console.log(ec);
// 		var dc = dec("meetingid", ec);
// 		console.log(dc);
// 		alert(btoa("category=textile&user=user1"));
// 		alert(atob("Y2F0ZWdvcnk9dGV4dGlsZSZ1c2VyPXVzZXIx"));
		
		mockjax1('search_create_meeting');
		table = dataTable('search_create_meeting');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
// 		$('.UpperClassName').keyup(function() {
// 			this.value = this.value.toUpperCase();
// 		});
	$("#meeting_id").val("${max_id}");
	m_id="${max_id}";
	var encm_id = btoa(m_id+"id");
	$("#linkhid").val(encm_id);
	});
	
	function setTimeLoadForTable(){
		
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
		
		document.querySelectorAll('.copy').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				
				if (confirm('COPIED..!!')) {
					
					navigator.clipboard.writeText(document.getElementById('viewID'+val).value);
					
				} else {
					return false;
				}
			})
		});
		
	}
	
document.addEventListener('DOMContentLoaded', function () {				
	document.getElementById('btn-create').onclick = function() {
		return Validation();
	};
	document.getElementById('checkbox-1').onclick = function() {
		if(document.getElementById("checkbox-1").checked){
			$("#recordhid").val(1);
		}else{
			$("#recordhid").val(0);
		}
	};
	document.getElementById('checkbox-2').onclick = function() {
		if(document.getElementById("checkbox-2").checked){
			$("#autostartrechid").val(1);
		}else{
			$("#autostartrechid").val(0);
		}
	};
	document.getElementById('checkbox-3').onclick = function() {
		if(document.getElementById("checkbox-3").checked){
			$("#allowstartstoprechid").val(1);
		}else{
			$("#allowstartstoprechid").val(0);
		}
	};
	document.getElementById('meeting_id').onchange = function(){
		vclink();
	}

});

function data(search_create_meeting) {
		
	jsondata = [];
	var table = $('#' + search_create_meeting).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];
// 	var meeting_id = $("#meeting_id").val();
	var name = $("#name").val();
	var attendeepw_id = $("#attendeepw_id").val();
	var moderatorpw_id = $("#moderatorpw_id").val();
	var welcome = $("#welcome").val();
	var record = $("#recordhid").val();
	var autostartrecording = $("#autostartrechid").val();
	var allowstartstoprecording = $("#allowstartstoprechid").val();
	

	$.post("getFilterCreate_Meeting_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
// 		meeting_id : meeting_id,
		name : name,
		attendeepw_id : attendeepw_id,
		moderatorpw_id : moderatorpw_id,
		welcome : welcome,
		record : record,
		autostartrecording : autostartrecording,
		allowstartstoprecording : allowstartstoprecording		

	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].ser, j[i].name, j[i].attendeepw_id, j[i].moderatorpw_id, j[i].welcome, j[i].link, j[i].action ]);
		}
	});
	
	$.post("getTotalCreate_Meeting_dataCount?" + key + "=" + value, {
		Search : Search,
// 		meeting_id : meeting_id,
		name : name,
		attendeepw_id : attendeepw_id,
		moderatorpw_id : moderatorpw_id,
		welcome : welcome,
		record : record,
		autostartrecording : autostartrecording,
		allowstartstoprecording : allowstartstoprecording
		
		},function(j) {
		FilteredRecords = j;

	});
	setTimeout(setTimeLoadForTable, 2000);
}
	
	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}

	function vclink(){
		var m_id = $("#meeting_id").val();
		var encm_id = btoa(m_id+"id");
		$("#linkhid").val(encm_id);
	}
	
	function Validation(){
// 		if ($("input#meeting_id").val() =="" ) {
// 			alert("Please Enter Meeting Id.");
// 			$("input#meeting_id").focus();
// 			return false;
// 		}
		if ($("input#name").val() =="" ) {
			alert("Please Enter Name.");
			$("input#name").focus();
			return false;
		}
		if ($("input#attendeepw_id").val() =="" ) {
			alert("Please Enter AttendeePW Id.");
			$("input#attendeepw_id").focus();
			return false;
		}
		if ($("input#moderatorpw_id").val() =="" ) {
			alert("Please Enter ModeratorPW Id.");
			$("input#moderatorpw_id").focus();
			return false;
		}
		if ($("#welcome").val() =="" ) {
			alert("Please Enter Welcome.");
			$("#welcome").focus();
			return false;
		}
		return true;
	}
	
</script>



