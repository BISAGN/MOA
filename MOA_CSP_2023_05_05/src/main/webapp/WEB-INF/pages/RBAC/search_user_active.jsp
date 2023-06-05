<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<section class="dashboard-page">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Active/Inactive User</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Active/Inactive User
									</li>
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
					<form:form name="" id="" action="search_user_active_Action"
						method="post" 
						commandName="Search_Student_Reg_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4"
										id="university_div">
										<div class="select-style-1">
											<label for="text-input">Role<span
												class="mandatory"></span></label>
											<div class="select-position">
												<select name="role_id" id="role_id">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getRoleNameList}"
														varStatus="num">
														<option value="${item.roleId}" name="${item.role}">${item.role}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="text-input">Username<span
												class="mandatory"></span></label> <input type="text" id="username"
												name="username" placeholder="Enter Username"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" />
										</div>
									</div>
										<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="text-input">Login Name<span class="mandatory"></span></label>
											<input type="text" id="loginname" name="loginname"
												placeholder="Enter Login Name"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" />
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="text-input">E-mail Id<span class="mandatory"></span></label>
											<input type="text" id="email_id" name="email_id"
												placeholder="Enter Login Name"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" />
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4"
										>
										<div class="select-style-1">
											<label for="text-input">Status<span
												class="mandatory"></span></label>
											<div class="select-position">
												<select name="enable_id" id="enable_id">
													<option value="1">Active</option>
													<option value="0">Inactive</option>
												</select>
											</div>
										</div>
									</div>
									
									
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">

											<li  id="btn-reload"><a
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="search_user_active_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a>
											</li>

<!-- 											<li id="pdfex"><a -->
<!-- 												class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon btnpdf" -->
<!-- 												><i class="bi bi-file-pdf" id="printId" -->
<!-- 													value="PDF" title="Export to PDF"></i> PDF </a></li> -->
<!-- 											<li id="btnExport"><a -->
<!-- 												class="main-btn success-btn btn-hover btn-iconic-icon btnexcel" -->
<!-- 												><i class="bi bi-file-earmark-excel" value="PDF" -->
<!-- 													title="Export to PDF"></i> EXCEL </a></li> -->
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
					</form:form>
				</div>
			</div>
			<section class="single-detail-block">
				<div class="row">
					<div class="col-12 col-sm-12 col-md-12 col-lg-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-datatable-p">
								<table class="table" id="getSearch_user_tbl">
									<thead>
										<tr>
											<th><h6>Sr No</h6></th>
											<th><h6>Username</h6></th>
											<th><h6>Login Name</h6></th>
											<th><h6>Email Id</h6></th>
											<th class="action"><h6>Action</h6></th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>

	</div>
</section>
<%-- <c:url value="Search_Stu_RegPopupUrl" var="Search_Stu_RegPopupUrl" /> --%>
<%-- <form:form action="${Search_Stu_RegPopupUrl}" method="post" --%>
<%-- 	id="studentpopup_Form" name="studentpopup_Form" modelAttribute="id" --%>
<%-- 	target="result"> --%>
<!-- 	<input type="hidden" name="popid" id="popid" value="0" /> -->
<%-- </form:form> --%>

<c:url value="USER_Login_Active_Url" var="actUrl" />
<form:form action="${actUrl}" method="POST" id="activeurl"
 	name="activeurl" modelAttribute="user_id1"> 
	<input type="hidden" name="user_id1" id="user_id1" value="0" />
 </form:form>
 
 <c:url value="USER_Login_InActive_Url" var="inactUrl" />
<form:form action="${inactUrl}" method="POST" id="inactiveurl"
 	name="inactiveurl" modelAttribute="user_id2"> 
	<input type="hidden" name="user_id2" id="user_id2" value="0" />
 </form:form>

<%-- <c:url value="Student_Registration_Report_PDF" var="searchUrl" /> --%>
<%-- <form:form action="${searchUrl}" method="post" id="search2" --%>
<%-- 	name="search2" modelAttribute="comd1"> --%>
<!-- 	<input type="hidden" name="university_id1" id="university_id1" -->
<!-- 		value="0" /> -->
<!-- 	<input type="hidden" name="institute_id1" id="institute_id1" value="0" /> -->
<!-- 	<input type="hidden" name="name1" id="name1" value="0" /> -->
<!-- 	<input type="hidden" name="ayush_id1" id="ayush_id1" value="0" /> -->
<!-- 	<input type="hidden" name="gender1" id="gender1" value="0" /> -->
<!-- 	<input type="hidden" name="date_of_birth1" id="date_of_birth1" -->
<!-- 		value="0" /> -->
<%-- </form:form> --%>

<%-- <c:url value="Student_Registration_Report_Excel" var="searchUrl1" /> --%>
<%-- <form:form action="${searchUrl1}" method="post" id="search3" --%>
<%-- 	name="search3" modelAttribute="comd1"> --%>
<!-- 	<input type="hidden" name="university_id2" id="university_id2" -->
<!-- 		value="0" /> -->
<!-- 	<input type="hidden" name="institute_id2" id="institute_id2" value="0" /> -->
<!-- 	<input type="hidden" name="name2" id="name2" value="0" /> -->
<!-- 	<input type="hidden" name="ayush_id2" id="ayush_id2" value="0" /> -->
<!-- 	<input type="hidden" name="gender2" id="gender2" value="0" /> -->
<!-- 	<input type="hidden" name="date_of_birth2" id="date_of_birth2" -->
<!-- 		value="0" /> -->
<%-- </form:form> --%>

<script nonce="${cspNonce}">
$(document).ready(function() {
// 	datepicketDate('date_of_birth');
	
// 	var role = '${roleid}';
	
// 	if (role == '19') {
// 			$("#university_div").addClass("d-none");
// 			$("#university_id").val('${uni_id}');
			
// 			get_inst_by_uni_nch('${uni_id}');
// 	}
	
// 	if (role == '21') {
// 		$("#university_div").addClass("d-none");
// 		$("#institute_div").addClass("d-none");
// 		$("#institute_id").val('${inst_id}');
// 	}
	
	mockjax1('getSearch_user_tbl');
	table = dataTable('getSearch_user_tbl');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	
});

function data(getSearch_user_tbl) {
	jsondata = [];
	var table = $('#' + getSearch_user_tbl).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
	var orderType = order[0][1];
	
	debugger;
	
	var role_id = $("#role_id").val();
	var username = $("#username").val();
	var loginname = $("#loginname").val();
	var email_id = $("#email_id").val();
	var enable_id = $("#enable_id").val();
	
// 	var ayush_id = $("#ayush_id").val();
// 	var gender = $("#gender").val();
// 	var date_of_birth = $("#date_of_birth").val();

	$.post("getFilterSearch_User_Active_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength: pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		
		role_id:role_id,
		username:username,
		loginname:loginname,
		email_id:email_id,
		enable_id:enable_id
		
	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].ser,j[i].username,j[i].login_name,j[i].email_id,j[i].action]);
		}
	});
	
	$.post("getTotalSearch_User_Active_dataCount?" + key + "=" + value, {
	
		role_id:role_id,
		username:username,
		loginname:loginname,
		email_id:email_id,
		enable_id:enable_id
	}, function(j) {
		FilteredRecords = j;
		});
	
	setTimeout(setTimeLoadForTable, 500);
}

function setTimeLoadForTable(){
	document.querySelectorAll('.btninact').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var hid = document.getElementById('InActiveID'+val).value;
			if (confirm('Are You Sure You Want to Inactive This User ?')) {
				InActiveUser(hid);
			} else {
				return false;
			}
		})
	});
	
	document.querySelectorAll('.btnactive').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var hid = document.getElementById('ActiveID'+val).value;
			if (confirm('Are You Sure You Want to Active This User ?')) {
				ActiveUser(hid);
			} else {
				return false;
			}
		})
	});
}

function ActiveUser(val) {
// 	alert("Activated  Success");
	$("input#user_id1").val(val);
	document.getElementById('activeurl').submit();
}

function InActiveUser(val) {
// 	alert("InActive Success");
	$("input#user_id2").val(val);
	document.getElementById('inactiveurl').submit();
}


// function Pop_Up_Stu_Reg(a) {
// // 	var x = screen.width/2 - 1100/2;
// //     var y = screen.height/2 - 900/2;
// //     popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
// // 	window.onfocus = function () { 
// // 	}
// // 	$("input#popid").val(a);
// // 	document.getElementById('studentpopup_Form').submit();

// 	$("input#ch_eid").val(a);
// 	document.getElementById('applicationUrlForm5').submit();


// }

// function get_inst_by_uni_nch(val) {
// 	$.ajaxSetup({
// 		async : false
// 	});
	
// 	var key = "${_csrf.parameterName}";
// 	var value = "${_csrf.token}";
// 	$.post('get_inst_by_uni_nch_ctrl?' + key + "=" + value,{university_id : val},function(j) {
// // 		alert(j);
// 			var options = '<option value="' + "0" + '">'
// 						+ "--Select--" + '</option>';
// 					for (var i = 0; i < j.length; i++) {
// 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
// 								+ j[i][1] + '</option>';
// 					}
// 				$("select#institute_id").html(options);
		
// 	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
// 	});
	
	
// }


// function Student_Registration_Report_Excel() {
// 	// 		alert(1);
// 	$("#university_id2").val($("#university_id").val());
// 	$("#institute_id2").val($("#institute_id").val());
// 	$("#name2").val($("#name").val());
// 	$("#ayush_id2").val($("#ayush_id").val());
// 	$("#gender2").val($("#gender").val());
// 	$("#date_of_birth2").val($("#date_of_birth").val());

// 	document.getElementById('search3').submit();

// }
// function Student_Registration_Report_PDF() {
// // 			alert("HIIIIIIIIII");



// 	$("#university_id1").val($("#university_id").val());
// 	$("#institute_id1").val($("#institute_id").val());
// 	$("#name1").val($("#name").val());
// 	$("#ayush_id1").val($("#ayush_id").val());
// 	$("#gender1").val($("#gender").val());
// 	$("#date_of_birth1").val($("#date_of_birth").val());

// 	document.getElementById('search2').submit();

// }
	
//csp----------------------------
document.addEventListener('DOMContentLoaded', function() {
	
// 	document.getElementById('date_of_birth').onclick = function() {
// 		return clickclear(this, 'DD/MM/YYYY');
// 	};
// 	document.getElementById('date_of_birth').onfocus = function() {
// 		this.style.color='#000000';
// 	};
// 	document.getElementById('date_of_birth').onblur = function() {
// 			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
// 	};
// 	document.getElementById('date_of_birth').onkeyup = function() {
// 		return	clickclear(this, 'DD/MM/YYYY');
// 	};
// 	document.getElementById('date_of_birth').onchange = function() {
// 		  clickrecall(this,'DD/MM/YYYY');
// 		  return validateDate_FutureDate(this.value,this); 
// 	};
// 	document.getElementById('pdfex').onclick = function() {
// 		Student_Registration_Report_PDF();
// 	};
// 	document.getElementById('btnExport').onclick = function() {
// 		Student_Registration_Report_Excel();
// 	};
	
// 	document.getElementById('save_btn').onclick = function () {
// 			if (this.value == "Save") {
// 				return isValidateClientSide();
// 			}
// 			if (this.value == "Update") {
// 				if(confirm('Are you sure you want to Proceed?')){return isValidateClientSide(); }else{return false;}
// 			}
// 		};
// 			document.getElementById('clear_btn').onclick = function() {
// 					return clear_field();
// 			};
// 			document.getElementById('board_or_university').onkeypress = function() {
// 				return onlyAlphabetsStringSpace(event, this);
// 			};
// 			document.getElementById('school_or_collage').onkeypress = function() {
// 				return onlyAlphabetsStringSpace(event, this);
// 			};
// 			document.getElementById('subject').onkeypress = function() {
// 				return onlyAlphabetsStringSpace(event, this);
// 			};
// 			document.getElementById('obtain_marks').onkeypress = function() {
// 				return isNumberKeydecimal(this, event);
// 			};
// 			document.getElementById('university_id').onchange = function() {
// 				get_inst_by_uni_nch(this.value);
// 			};
			
// 			document.getElementById('tunnel_1').onclick = function() {
// 				if(confirm('Are you sure you want to Proceed?')){getPreviousPage();}else{return false;}
// 			};
// 			document.getElementById('tunnel_3').onclick = function() {
// 				if(confirm('Are you sure you want to Proceed?')){Edu_next();}else{return false;}
// 			};
			
// 			document.getElementById('aIdNext').onclick = function() {
// 				if (confirm('Are you sure you want to Proceed?')) {
// 					return Edu_next();
// 				} else {
// 					return false;
// 				}
// 			};
// 			document.getElementById('aIdPrevious').onclick = function() {
// 				if (confirm('Are you sure you want to Proceed?')) {
// 					return getPreviousPage();
// 				} else {
// 					return false;
// 				}
// 			};

	});

</Script>
