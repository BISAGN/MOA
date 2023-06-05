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

<link rel="stylesheet" href="assets/vendor/internal_css.css">


<!-- <style>
.container {
  border: 2px solid #dedede;
  background-color: #f1f1f1;
  border-radius: 5px;
  padding: 10px;
  margin: 10px 0;
}

.darker {
  border-color: #ccc;
  background-color: #ddd;
}

.container::after {
  content: "";
  clear: both;
  display: table;
}

.container img {
  float: left;
  max-width: 60px;
  width: 100%;
  margin-right: 20px;
  border-radius: 50%;
}

.container img.right {
  float: right;
  margin-left: 20px;
  margin-right:0;
}

.time-right {
  float: right;
  color: #aaa;
}

.time-left {
  float: left;
  color: #999;
}

.chatmsgscroll {
    overflow-y: scroll;
    max-height: 400px;
}
</style> -->


<section class="dashboard-page mentor-page" id="app_req_div">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Approve Student Request</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Approve Student 
								 Request</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		
		<div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_Student_Room_Request">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th><h6>Student Name</h6></th>
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


<section class="dashboard-page mentor-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-4 col-md-4 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Mentee Details</h2>
					</div>
				</div>
				<!-- end col -->
				
				<div class="col-lg-4 col-md-4 col-sm-12 col-12">
					<ul>
						<li>
							<a href="Create_Meeting_Url" class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left btnnext">Click here to create room for webinar
								<i class="lni lni-chevron-right"></i>
							</a>
						</li>
					</ul>
				</div>
				
				<div class="col-lg-4 col-md-4 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Mentee Details
								 Request</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		
		<div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_Mentee_Room_Request">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th><h6>Mentee</h6></th>
 									<th><h6>Messages</h6></th>
									
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
		
		<input type="hidden" name="stuUid" id="stuUid" value="" />
		
	</div>
	
	<div class="alert alert-success alert-dismissible" id="alert_div">
					<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
					<strong>Success!</strong><label id="alert_txt_lbl"></label>
		</div>
	
	<!-- ask query modal start -->
<form:form action="${setApproveRequestAction}" method="post" id="setApproveRequestForm"
	name="setApproveRequestForm" modelAttribute="setApproveRequestForm">
	<div class="modal fade custom-modal" id="askquerymodal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      
	      <div class="modal-header">
	        <h3 class="modal-title" id="staticBackdropLabel">You may ask your query</h3>
	        <button type="button" class="btn-close" id="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      
	      <div class="modal-body">
			
	        <div class="custom-modal-inner" id="headData">
	        </div>
	      </div>
	      
	      <div class="modal-footer">
	      <ul class="buttons-group">
		      <li>
			  	<i class="main-btn secondary-btn btn-hover bi bi-upload mr-5" id="attchfile"> Share Files</i>
		     </li>
		     <li>
		     	<button type="button" class="status-btn success-btn" data-dismiss="modal" id="squery">Send</button>
		     </li>
		     <li class="d-none">
		     	<input type="file" accept="*" id="file_input" class="form-control" name="file_input" class="form-control">
		     </li>
	      </ul>
	      </div>
	      
	    </div>
	  </div>
	</div>
</form:form>
<!-- ask query modal end -->
	
</section>

<c:url value="getDownloadPdfUrlforMMfile" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search1" name="search1">
<!-- 	<input type="hidden" name="typeReport1" id="typeReport1" value="" /> -->
	<input type="hidden" name="doc_id1" id="doc_id1" value="0" />
	<input type="hidden" name="pageUrl1" id="pageUrl1" value="" />
</form:form>	

<c:url value="Approve_Request_url" var="Approve_Request_url" />
			<form:form action="${Approve_Request_url}" method="post" id="AcceptFaculty"
			name="AcceptFaculty" modelAttribute="Acceptid">
			<input type="hidden" name="Acceptid" id="Acceptid" value="0" />
</form:form>

<c:url value="Reject_Request__url" var="Reject_Request__url" />
			<form:form action="${Reject_Request__url}" method="post" id="RejectFaculty"
			name="RejectFaculty" modelAttribute="Rejecttid">
			<input type="hidden" name="Rejectid" id="Rejectid" value="0" />
</form:form>

<c:url value="getSearch_Student_Room_Request" var="getSearch_Student_Room_Request" />
<form:form action="${getSearch_Student_Room_Request}" method="post" id="searchForm"
	name="searchForm" modelAttribute="searchUrl2">
	<input type="hidden" name="searchUrl2" id="searchUrl2" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>


<%-- <c:url value="Approve_Student_Room_RequestAction" var="Approve_Student_Room_RequestAction" />
<form:form action="${Approve_Student_Room_RequestAction}" method="post" id="approveForm"
	name="approveForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3">
</form:form> --%>

<%-- <c:url value="Reject_Student_Room_RequestAction" var="Reject_Student_Room_RequestAction" />
<form:form action="${Reject_Student_Room_RequestAction}" method="post" id="rejectForm"
	name="rejectForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4" value="0" />
</form:form> --%>

<c:url value="Approve_Requestreport" var="Approve_Requestreport" />
<form:form action="${Approve_Requestreport}" method="post" id="search2"
	name="searcho" modelAttribute="comdo">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>
<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {

	var role = '${role}';
	
	if(role.includes("Councillor")){
		$("#app_req_div").show();
	}else{
		$("#app_req_div").hide();
	}
	
	mockjax1('search_Student_Room_Request');
	table1 = dataTable('search_Student_Room_Request');
	
	mockjax1('search_Mentee_Room_Request');
	table2 = dataTable('search_Mentee_Room_Request');
	
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	
	$('#btn-close').on('click', function() {
		location.reload();
	});
	
	
	$.ajaxSetup({
		async : false
	});
	
	$('.UpperClassName').keyup(function() {
		this.value = this.value.toUpperCase();
	});
	
});


function setTimeLoadForTable(){
 
	document.querySelectorAll('.ADDApprove_Request ').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('apIdAGE'+val).value;
			
			if (confirm('Are You Sure You Want to Approve Detail ?')) {
				ApproveData(hid);
			} else {
				return false;
			}
		})
	});

	document.querySelectorAll('.deleteOnclick').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('deleteID'+val).value;
			
			if (confirm('Are You Sure You Want to Reject Detail ?')) {
				RejectData(hid);
			} else {
				return false;
			}
		})
	});
	
	document.querySelectorAll('.AskQuery').forEach((items, index) => {
		items.addEventListener('click', event => {
				var val=parseInt(index)+1;
// 				var student_user_id = '${userId}';
				var student_user_id =  document.getElementById('apSUIdAGE1'+val).value;
				$("#stuUid").val(student_user_id);
				var roleID =  document.getElementById('apROIdAGE'+val).value;
// 				alert(faculty_user_id);
// 				if (confirm('Are You Sure You Want to Ask Something ?')) {
					
					getdata(student_user_id,roleID);
					
// 				} else {
// 					return false;
// 				}
		});
	})
	
	
	
}

 function data(tablename) {
	 if(tablename=="search_Student_Room_Request"){
		 
		 $.ajaxSetup({
			    async: false
			});
		 
   	 jsondata = [];
 	var table = $('#' + tablename ).DataTable();
//   	alert(search_Student_Room_Request);
 	var info = table.page.info();
 	var pageLength = info.length;
 	var startPage = info.start;
 	var endPage = info.end;
 	var Search = table.search();
 	var order = table.order();
 	var orderColunm = $(table.column(order[0][0]).header()).html()
 			.toLowerCase();
	var orderType = order[0][1];
 	var student_user_id = $("#student_user_id").val();

 	$.post("getSearch_Approve_Request_data?" + key + "=" + value, {
 		startPage : startPage,
 		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm, 
		orderType : orderType
 	}, function(j) {
 		
 		console.log("table1-->   "+j);
 		
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].ser,j[i].student,j[i].action]);
		}
	});
 	
 	$.ajaxSetup({
		async : false
	});
 	
 	$.post("getTotalSearch_Approve_request_dataCount?" + key + "=" + value, {
 		Search : Search
 	}, function(j) {
		FilteredRecords = j;
 	}).fail(function(xhr, textStatus, errorThrown, exception) {
	  	 alert(errorThrownMsg(xhr,exception));
	});
 	 
 }
	 else if(tablename=="search_Mentee_Room_Request") {	
	 	jsondata = [];
	 	var table = $('#' + tablename).DataTable();
// 		alert(search_Mentee_Room_Request);
	 	var info = table.page.info();
	 	var pageLength = info.length;
	 	var startPage = info.start;
	 	var endPage = info.end;
	 	var Search = table.search();
	 	var order = table.order();
	 	var orderColunm = $(table.column(order[0][0]).header()).html()
	 			.toLowerCase();
		var orderType = order[0][1];
		var student_user_id = $("#student_user_id").val();
// 	 	var students = $("#students").val();

	 	$.post("getSearch_Mentee_Request_data?" + key + "=" + value, {
	 		startPage : startPage,
	 		pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm, 
			orderType : orderType,
// 			students : students
	 	}, function(j) {
	 		console.log("table2-->   "+j)
	 		
			for (var i = 0; i < j.length; i++) {
				jsondata.push([j[i].ser,j[i].students,j[i].action]);
			}
	 		setTimeout(setTimeLoadForTable, 1000);
		});
	 	
	 	$.ajaxSetup({
			async : false
		});
	 	
		$.post("getTotalSearch_Mentee_request_dataCount?" + key + "=" + value, {
	 		Search : Search
	 	}, function(j) {
			FilteredRecords = j;
	 	}).fail(function(xhr, textStatus, errorThrown, exception) {
		  	 alert(errorThrownMsg(xhr,exception));
		});
	 	
// 	 	$.post("getTotalSearch_Mentee_request_dataCount?" + key + "=" + value, {
// 	 		Search : Search
// // 	 		message : message
// 	 	}, function(j) {
// 			FilteredRecords = j;
// 	 	}).fail(function(xhr, textStatus, errorThrown, exception) {
// 		  	 alert(errorThrownMsg(xhr,exception));
		 	
// 		});
// 	 	setTimeout(setTimeLoadForTable, 1000);
	 }
 }

 function ApproveData(id) {

 	$("#Acceptid").val(id);
 	document.getElementById('AcceptFaculty').submit();
 }

 function RejectData(id) {
	
 	$("#Rejectid").val(id);
 	document.getElementById('RejectFaculty').submit();
 }

 function Search() {
 	$("#student_user_id1").val($('#student_user_id').val());


 	document.getElementById('searchForm').submit();
 	}
 
 function getdata(student_user_id,roleID){
	 
	 var msgs = "";
	 var msgIds = "";
	 var role = '${role}';
	 
	 $.post('getMessages?'+key+"="+value, {
			student_user_id:student_user_id,
		}).done(function(j) {
			
		if(j.length > 0){
			
			msgs="<div id='msgs_div' class='chatmsgscroll'>";
			
			for(var i=0;i<j.length;i++){
				
				var cs="";
				var ts="";
				var is="";
// 				var cv = i % 2;
				
				if((role.toLowerCase().includes("councillor") && j[i].from.toLowerCase().includes("councillor")) 
						|| (role.toLowerCase().includes("faculty") && j[i].from.toLowerCase().includes("faculty"))){
					
					cs = "container";
					ts="time-right";
					is="";
					
				}else{
					
					cs = "container darker";
					ts="time-left";
					is="right";
					
				}
				
				if(j[i].file == "" || j[i].file == "null" || j[i].file == null){
					msgs +="<div class='"+cs+"'>"
					  +"<img src='assets/db_img/profile-image.png' alt='' class='"+is+"' >"
					  +"<p>"+j[i].message+"</p>"
					  +"<span class='"+ts+"'>"+j[i].timing+"</span>"
					+"</div>";
				}
				if(j[i].file != "" && j[i].file != "null" && j[i].file != null){
					msgs +="<div class='"+cs+"'>"
					  +"<img src='assets/db_img/profile-image.png' alt='' class='"+is+"' >"
					  +"<p>"+j[i].message+"</p>"
					  +"<ul><li><a class='main-btn info-btn btn-hover btn-sm' id='downloadfile"+j[i].id+"'><i class='lni lni-download'></i></a></li></ul>"
					  +"<span class='"+ts+"'>"+j[i].timing+"</span>"
					+"</div>";
				}
				
				msgIds += j[i].id+",";
				
			}
			
			msgs+="</div>";
			
			var options = msgs + "<div class='row'><div class='col-12 col-lg-12 col-md-12 col-sm-12'><div class='input-style-1 mb-0'><label>Enter your Query</label><textarea placeholder='Enter Query here' id='message' rows='5' cols='50' name='message' ></textarea></div></div></div>";
		 	options += "<input type='hidden' id='faculty_user_id' name='faculty_user_id' class='form-control' value=' '>";
		 	options += "<input type='hidden' id='student_user_id' name='student_user_id' class='form-control' value='"+student_user_id+"'>";
		 	options += "<input type='hidden' id='role_id' name='role_id' class='form-control' value='"+roleID+"'>";
		 	
		 	$("#headData").html(options);
			setquerysubmit();
			
			$.post('ChangeMessagesStatus?'+key+"="+value, {
				 msgIds:msgIds
				}).done(function(j) {
					console.log(j);
				});
			
		}else{
			
			msgs="<div id='msgs_div' class='chatmsgscroll'>";
			msgs +="<div class='"+cs+"'>"
			  +"<span class='"+ts+"'>NO MESSAGES AVAILABLE</span>"
			+"</div>";
			msgs+="</div>";
			
			var options = msgs + "<div class='row'><div class='col-12 col-lg-12 col-md-12 col-sm-12'><div class='input-style-1 mb-0'><label>Enter your Query</label><textarea placeholder='Enter Query here' id='message' rows='5' cols='50' name='message' ></textarea></div></div></div>";
		 	options += "<input type='hidden' id='faculty_user_id' name='faculty_user_id' class='form-control' value=' '>";
		 	options += "<input type='hidden' id='student_user_id' name='student_user_id' class='form-control' value='"+student_user_id+"'>";
		 	options += "<input type='hidden' id='role_id' name='role_id' class='form-control' value='"+roleID+"'>";
		 	
		 	$("#headData").html(options);
		 	
		 	setquerysubmit();
		 	
		}
			
	  });
	 
// 	 	var options = msgs + "<div class='row'><div class='col-12 col-lg-12 col-md-12 col-sm-12'><div class='input-style-1 mb-0'><label>Enter your Query</label><textarea placeholder='Enter Query here' id='message' rows='5' cols='50' name='message' ></textarea></div></div></div>";
// 	 	options += "<input type='hidden' id='faculty_user_id' name='faculty_user_id' class='form-control' value=' '>";
// 	 	options += "<input type='hidden' id='student_user_id' name='student_user_id' class='form-control' value='"+student_user_id+"'>";
// 	 	options += "<input type='hidden' id='role_id' name='role_id' class='form-control' value='"+roleID+"'>";
		
// 	 	$("#headData").html(options);
// 		setquerysubmit();
		
// 		 $.post('ChangeMessagesStatus?'+key+"="+value, {
// 			 msgIds:msgIds
// 		}).done(function(j) {
// 			console.log(j);
// 		});

	 setdownloadonclick();

}
 
 function setdownloadonclick(){
		
		$.post('getMessages?'+key+"="+value, {
			student_user_id:$("#stuUid").val()
			}).done(function(j) {
		
				for(var d=0;d<j.length;d++){
					if(j[d].file != "" && j[d].file != "null" && j[d].file != null){
						var indId = j[d].id;
						document.getElementById('downloadfile'+indId).onclick = function () {
							 downloadfile(indId);
						};
					}
				}
				
		});
	}
 
 function downloadfile(id){
		document.getElementById('pageUrl1').value = 'approved_request_Url';
		document.getElementById('doc_id1').value = id;
		document.getElementById('search1').submit();
	}
 
 function setquerysubmit(){
		document.getElementById('squery').onclick = function () {
			
			return AskQueryFn4();
		};
		document.getElementById('attchfile').onclick = function () {
			
			 AttchFile();
		};
	}
 
 function AttchFile(){
	 $("#file_input").click();
 }
 
 function AskQueryFn4(){

    	var form_data = new FormData(document.getElementById("setApproveRequestForm"));
		
    	$.ajax({
				url: 'getAskQueryMethodforapprove',
				type: "POST",
				enctype: 'multipart/form-data',
				data:form_data,
				processData: false,
				contentType: false
			}).done(
			function(data) {
				alert(data);
				$('.btn-close').click();
			})
		.fail(function(jqXHR, textStatus) {
			alert(jqXHR.responseText);
		});

 }
 


</script>		