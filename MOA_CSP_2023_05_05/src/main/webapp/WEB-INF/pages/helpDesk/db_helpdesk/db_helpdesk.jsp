<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Help Desk</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Help
									Desk</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<!-- title-wrapper end -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12 col-sm-12 col-md-12 col-lg-12">
				<form:form name="Inq_in_db_helpdesk_form" id="Inq_in_db_helpdesk_form"
						action="" method="post" 
						modelAttribute=""/>
					<div class="card-style mb-30">
						<div class="custom-field-block">
							<div class="row">
							<div class="col-12 col-sm-12 col-md-12 col-lg-12">
						 	<div class="alert alert-success custom-d-none" id="case_succ_msg_div">
							 <strong>Successfully!</strong> Your case is reported. <strong>Case ID:</strong> <span class='case_number'></span> <br> Please, check case status after some period of duration.
							 </div>
							 </div>
							
							    <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Module<span
											class="mandatory">*</span>
										</label>
										<div class="select-position">
<!-- 											<select class="singleselect form-control form-control-lg"> -->
<!-- 												<option value="0">Select</option> -->
<!-- 												<option value="1">Module one</option> -->
<!-- 												<option value="2">Module two</option> -->
<!-- 												<option value="3">Module three</option> -->
                                                <select  name="module" id="module" class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getModuleNameList}" varStatus="num">
													<option value="${item.id}">${item.module_name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Sub Module<span
											class="mandatory">*</span>
										</label>
										<div class="select-position">
                                             <select  name="sub_module" id="sub_module" class="singleselect form-control form-control-lg">
											<option value="0">--Select--</option>
											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Screen Name<span
											class="mandatory">*</span>
										</label>
										<div class="select-position">
                                           <select  name="screen_name" id="screen_name" class="singleselect form-control form-control-lg">
											<option value="0">--Select--</option>
											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Help Topics<span
											class="mandatory">*</span>
										</label>
										<select name="inq_cat" id="inq_cat" class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getinnerInq_CatList}" varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.inq_cat}</option>
													</c:forEach>
													</select>
									</div>
								</div>
								
<!-- 								<div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
<!-- 									<div class="input-style-2"> -->
<!-- 										<label>Issue Summary<span class="mandatory">*</span></label> -->
<!-- 										<textarea name="issue_summary" class="form-control" name="issue_summary" id="issue_summary" maxlength="250" -->
<!-- 										cols="45" rows="8" placeholder="Issue Summary" required></textarea> -->
<!-- 										<textarea placeholder="Issue Summary"></textarea> -->
<!-- 									</div> -->
<!-- 								</div> -->
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Description<span class="mandatory">*</span></label>
										<textarea name="description" class="form-control" name="description" id="description" maxlength="500"
										cols="45" rows="8" placeholder="Description" required></textarea>
<!-- 										<textarea placeholder="Description"></textarea> -->
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Any Attachment</label> 
										<input type="file"
												accept="image/*" id="attachment" class="form-control"
												name="attachment" accept=".jpg" class="form-control">
											<input type="hidden" id="upload_img_hid"
												name="attachment" class="form-control">

									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Mobile Number<span class="mandatory"></span></label> 
										<input type="text" name="mobile_no" id="mobile_no" class="form-control" placeholder="Enter Mobile No." 
                                                	 maxlength="10" minlength="10"  >
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Email ID<span class="mandatory">*</span></label> 
										<input type="text" name="email" id="email" class="form-control" placeholder="abc@example.com" 
                                                	maxlength="70"  pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$" value="${email_txt}">
									</div>
								</div>
<!-- 								<div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
<!-- 									<div class="input-style-1"> -->
<!-- 											<label>State<strong><span class="mandatory">*</span></strong></label>
											<div class="select-position">
												<select name="per_state" id="per_state"
													class="singleselect form-control form-control-lg">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${MedStateName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
							</div>
						</div>
						<!-- Bottom Button Start -->
						<div class="btn-bottom">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12">
									<ul class="buttons-group mainbtn">
<!-- 										<li><a href="#" class="main-btn info-btn btn-hover btnsubmit">Submit</a></li> -->
                                      <li><input id="btn-save"
												class="main-btn info-btn btn-hover btnsave" type="button"
												value="Save" /></li>
												<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
									</ul>
								</div>
							</div>
						</div>
						
						<section class="single-detail-block">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="table-wrapper table-responsive custom-datatable-p">
							<table class="table" id="db_helpdesk_Master">
								<thead>
									<tr>
										<th><h6>Sr No</h6></th>
										<th id="${item.id}"><h6>State Name</h6></th>
										<th id="${item.id}"><h6>Inquiry Category</h6></th>
										<th id="${item.id}"><h6>Inquiry Number</h6></th>
										<th id="${item.id}"><h6>Description</h6></th>
										<th id="${item.id}"><h6>Email</h6></th>
										<th id="${item.id}"><h6>Module</h6></th>
										<th id="${item.id}"><h6>Sub Module</h6></th>
										<th id="${item.id}"><h6>Screen Name</h6></th>
										<th id="${item.id}"><h6>Status</h6></th>
									</tr>
								</thead>
							</table>
						</div>
					</div>	
				</div>		
			</div>
		</section>
						<!-- Bottom Button End -->
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
// 		case_succ_msg_div
// 		$("#case_succ_msg_div").show();
		try {
			var inqno = '${inqno}';
// 			alert(inqno);
			if (inqno != "") {
				$("#case_succ_msg_div").show();
				$("#case_succ_msg_div").html("<strong>Successfully!</strong> Your case is reported. Your <strong>Case ID:</strong> <span class='case_number'>"+inqno+"</span> <br>Please check case status using Case ID after some Time.");
			}
		} catch (e) {
		}
		
		
		
	});
	
	$(document).ready(function() {

		mockjax1('db_helpdesk_Master');
		table = dataTable('db_helpdesk_Master');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
	});
	
// 	mockjax1('db_helpdesk_Master');
// 	table = dataTable('db_helpdesk_Master');
// 	$('#btn-reload').on('click', function() {
// 		table.ajax.reload();
// 	});
	
document.addEventListener('DOMContentLoaded', function() {
	

document.getElementById('inq_cat').onkeypress = function() {
	return onlyAlphabetsStringSpace(event,this)
	
// 	$("#module_id1").val($("select#screen_module_id").val());
// 	$("#sub_module_id1").val($("select#screen_submodule_id").val());
// 	$('select#screen_module_id').change(function() {
// 	    var mid = this.value; 
// 	    var sList = new Array();
// 	    var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
// 		<c:forEach var="item" items="${getSubModuleNameList}" varStatus="num" >
// 			if('${item.module.id}' == mid){
// 				options += '<option value="${item.id}">${item.submodule_name}</option>';
// 			}
// 		</c:forEach>
// 		$("select#screen_submodule_id").html(options); 
// 	});  
	
};
document.getElementById('module').onchange = function() {
	getsubmodule();
};
document.getElementById( 'sub_module').onchange = function() {
	getscreenname();
};

document.getElementById('btn-save').onclick = function() {
	return db_helpdesk();
};

});

// function getsubmodule() {
// 	$.ajaxSetup({
// 		async : false
// 	});	
// 	debugger;
// 	  var screen_module_id = $("select#screen_module_id").val();

//       $.post("getsubmoduleform?" + key + "=" + value,{screen_module_id : screen_module_id},
// 					function(j) {
// 						var options = '<option value="' + "0" + '">'
// 								+ "select" + '</option>';
// 						for (var i = 0; i < j.length; i++) {

// 							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
// 									+ j[i][1]+ '</option>';
// 						}
// 						$("select#sub_module_id").html(options);
// 					});
// }
   

function data(db_helpdesk_Master) {
		
		jsondata = [];
		var table = $('#' + db_helpdesk_Master).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var inq_no = $("#inq_no").val();
		var inq_cat = $("#inq_cat").val();
		var per_state = $("#per_state").val();
		var des = $("#des").val();
		var module = $("#module").val();
		var sub_module = $("#sub_module").val();
		var screen_name = $("#screen_name").val();
		var status_search = $("#status_search").val();

		$.post("getFilterdb_helpdesklist?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			inq_no : inq_no,
			inq_cat : inq_cat,
			per_state : per_state,
			des : des,
			module : module,
			sub_module : sub_module,
			screen_name : screen_name,
			status : status_search
			
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].state_name, j[i].inq_cat, j[i].inq_no, j[i].description, j[i].email, j[i].module,j[i].sub_module,j[i].screen_name,j[i].status]);
			}
		});
		$.post("getTotaldb_helpdeskCount?" + key + "=" + value, {
			Search : Search,
			inq_no : inq_no,
			inq_cat : inq_cat,
			per_state : per_state,
			des : des,
			module : module,
			sub_module : sub_module,
			screen_name : screen_name,
			status : status_search
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
function setTimeLoadForTable(){
}
	
function getsubmodule() {
	
	var module = $("#module").val();

	$
			.post('getsubmoduleform?' + key + "=" + value,
					{
				module : module,
						
					})
			.done(
					function(j) {
						
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							//alert(j[i][0])
							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#sub_module").html(options);
					});
}

function getscreenname() {
	$.ajaxSetup({
		async : false
	});
// 	debugger;
	var module = $("select#module").val();
	var sub_module = $("select#sub_module").val();
// alert(screen_module_id);
	$.post("getscreennameform?" + key + "=" + value,
					{
		                  module : module,
						sub_module : sub_module
					},
					function(j) {
// 						alert(j);
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
// 						$("select#sub_module_id").html(options);
						$("select#screen_name").html(options);
					});
}
function Validate() {
	if ($("select#module").val() == "0") {
		alert("Please Select Module.");
		$("select#module").focus();
		return false;
	}
	if ($("select#sub_module").val() == "0") {
		alert("Please Select Sub Module.");
		$("select#sub_module").focus();
		return false;
	}
	if ($("select#screen_name").val() == "0") {
		alert("Please Select screen_name.");
		$("select#screen_name").focus();
		return false;
	}
	if ($("select#inq_cat").val() == "0") {
		alert("Please Select Help Topic.");
		$("select#inq_cat").focus();
		return false;
	}
	if ($("#description").val().trim() == "") {
		alert("Please Enter description.");
		$("input#description").focus();
		return false;
	}
	if ($("#mobile_no").val().trim() == "") {
		alert("Please Enter mobile_no.");
		$("input#mobile_no").focus();
		return false;
	}
	if ($("#email").val().trim() == "") {
		alert("Please Enter email.");
		$("input#email").focus();
		return false;
	}
	 return true;
}

function db_helpdesk() {
	$.ajaxSetup({
	    async: false
	});
// 	debugger;
// 	if ($("input#name").val().trim() == "") {
// 		alert("Please Enter Name");
// 		$("input#name").focus();
// 		return false;
// 	}
// 	if ($("input#email").val().trim() == "") {
// 		alert("Please Enter Email");
// 		$("input#email").focus();
// 		return false;
// 	}
// 	if ($("input#subject").val().trim() == "") {
// 		alert("Please Enter Subject");
// 		$("input#subject").focus();
// 		return false;
// 	}
// 	if ($("textarea#message").val().trim() == "") {
// 		alert("Please Enter Message");
// 		$("textarea#message").focus();
// 		return false;
// 	}
	//var form = $("#Contact_US").serialize();
	
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	
	var module =$("#module").val();
	var sub_module =$("#sub_module").val();
	var screen_name =$("#screen_name").val();
	var inq_cat =$("#inq_cat").val();
	var description =$("#description").val();
	var mobile_no =$("#mobile_no").val();
	var email =$("#email").val();
	
 	$.post("Inq_in_db_helpdesk_Action?"+key+"="+value,{module:module,sub_module:sub_module,screen_name:screen_name,inq_cat:inq_cat,description:description,
 		mobile_no:mobile_no,email:email},function(j) {
		if(j[1] == "Data Saved Successfully"){
 			$("#case_succ_msg_div").show();
        alert("Your Inquiry Has Been Sucessfully Submitted");
        $('.case_number').text(j[0]);
//         location.reload();
		}
	});

  
}


</script>
