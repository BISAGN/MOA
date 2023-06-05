<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<section class="dashboard-page">
<div class="container-fluid">
	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>Student Admitted Report</h2>
				</div>
			</div>
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">Student Admitted Report</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</div>

	<div class="form-elements-wrapper">
		<div class="row">
			<div  class="col-12 col-sm-12 col-md-12 col-lg-12">
                <form:form name="" id="" action="Search_Student_RegAction" method="post" class="form-horizontal" commandName="Search_Student_Reg_CMD"> 
					<div class="card-style mb-30">
						<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-4" id="university_div">
								<div class="select-style-1">
									<label for="text-input">University<span class="mandatory"></span></label>
									<div class="select-position">
										<select name="university_id" id="university_id">
								        	<option value="0">---Select---</option>
											<c:forEach var="item" items="${getUniverCityList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
											</c:forEach>
										</select>
									</div>
								</div>	
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4" id="institute_div">
								<div class="select-style-1">
									<label for="text-input">Institute<span class="mandatory"></span></label>
									<div class="select-position">
										<select name="institute_id" id="institute_id" >
								        	<option value="0">---Select---</option>
											<c:forEach var="item" items="${getinstitutelist}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
											</c:forEach>
										</select>
									</div>
								</div>								
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-1">
									<label for="text-input">Name<span class="mandatory"></span></label>
									<input type="text" id="name" name="name" placeholder="Enter Name" class="autocomplete UpperClassName txt-transupp"
										autocomplete="off"   />
								</div>						
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-1">
									<label for="text-input">Ayush Id<span class="mandatory"></span></label>
									<input type="text" id="ayush_id" name="ayush_id" placeholder="Enter Ayush Id" class="autocomplete UpperClassName txt-transupp"
										autocomplete="off"   />
								</div>							
								<!-- end select -->
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="text-input">Gender<span class="mandatory"></span></label>
									<div class="select-position">
										<select name="gender" id="gender" class="form-control">
											<option value="0">--Select--</option>
												<c:forEach var="item" items="${getgenderList}" varStatus="num">
											    <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>	
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2">
								<label>Date of Birth </label> 
								<input type="text" name="date_of_birth" id="date_of_birth" maxlength="10"
									class="form-control-sm form-control"
									aria-required="true" autocomplete="off"
									value="DD/MM/YYYY">
							</div>						
						</div>
					</div>		
						<ul class="buttons-group mainbtn">

							<li>
							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button" value="Search"><i class="lni lni-search-alt"></i>Search</a>
							</li>
							<li>
								<a href="SearchStudents_RegistrationUrl" class="main-btn dark-btn n btn-hover" type="button">Reset</a>
							</li>
							
							<li><a
									class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon"
									id="pdfex"><i class="lni lni-printer" id="printId"
										value="PDF" title="Export to PDF"></i> PDF </a></li>

								<li><a
									 class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon"
									id="btnExport"><i class="lni lni-printer" value="PDF"
										title="Export to PDF"></i> EXCEL </a></li>
						</ul>
				</div>
			</form:form>
		</div>
	</div>
	
	<div class="row">
	<div  class="col-12 col-sm-12 col-md-12 col-lg-12">
		<div class="card-style mb-30">
			<div class="table-wrapper table-responsive custom-datatable-p">
				<table class="table" id="getSearch_Stu_Reg">
					<thead>
							<tr>
				       		    <th ><h6>Ser No</h6></th >
				       		    <th ><h6>Ayush Id</h6></th >
				       		    <th ><h6>University</h6></th >
				       		    <th ><h6>Institute</h6></th > 
				            	<th ><h6>Name</h6></th >
				            	<th ><h6>Surname</h6></th >
				            	<th ><h6>Father Name</h6></th >
				            	<th ><h6>Mother Name</h6></th >
				            	<th ><h6>Gender</h6></th >
				            	<th ><h6>Date of Birth</h6></th >
				            	<th ><h6>Category</h6></th >
				            	<th ><h6>Religion</h6></th >
				            	<th ><h6>Marital Status</h6></th >
				            	<th ><h6>Nationality</h6></th >
				            	<th ><h6>State/UT (Domicile)</h6></th >
				            	<th ><h6>District</h6></th >
				            	<th ><h6>Neet All India Rank</h6></th >
				            	<th ><h6>Neet Marks</h6></th >
				            	<th ><h6>Neet Percentile</h6></th >
				            	<th ><h6>University Enrollment No</h6></th >
				            	<th class="action"><h6>View</h6></th >
				            </tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

 </div>
</section>
<c:url value="Search_Stu_RegPopupUrl" var="Search_Stu_RegPopupUrl"/>
<form:form action="${Search_Stu_RegPopupUrl}" method="post" id="studentpopup_Form"
	name="studentpopup_Form" modelAttribute="id" target="result">
	<input type="hidden" name="popid" id="popid" value="0" />
</form:form>

<c:url value="NCH_Std_details_view_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm5" name="applicationUrlForm5" modelAttribute="ch_eid">
<input type="hidden" name="ch_eid" id="ch_eid" value="0"/>	
</form:form>


<c:url value="Student_Registration_Report_PDF" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="university_id1" id="university_id1" value="0" />
	<input type="hidden" name="institute_id1" id="institute_id1"
		value="0" />
	<input type="hidden" name="name1" id="name1" value="0" />
	<input type="hidden" name="ayush_id1" id="ayush_id1" value="0" />
	<input type="hidden" name="gender1" id="gender1"
		value="0" />
	<input type="hidden" name="date_of_birth1" id="date_of_birth1"
		value="0" />
</form:form>



<c:url value="Student_Registration_Report_Excel" var="searchUrl1" />
<form:form action="${searchUrl1}" method="post" id="search3"
	name="search3" modelAttribute="comd1">
	<input type="hidden" name="university_id2" id="university_id2" value="0" />
	<input type="hidden" name="institute_id2" id="institute_id2"
		value="0" />
	<input type="hidden" name="name2" id="name2" value="0" />
	<input type="hidden" name="ayush_id2" id="ayush_id2" value="0" />
	<input type="hidden" name="gender2" id="gender2"
		value="0" />
	<input type="hidden" name="date_of_birth2" id="date_of_birth2"
		value="0" />
</form:form>

<script  nonce="${cspNonce}">
$(document).ready(function() {
	datepicketDate('date_of_birth');
	
	var role = '${roleid}';
	
	if (role == '19') {
			$("#university_div").addClass("d-none");
			$("#university_id").val('${uni_id}');
			
			get_inst_by_uni_nch('${uni_id}');
	}
	
	if (role == '21') {
		$("#university_div").addClass("d-none");
		$("#institute_div").addClass("d-none");
		$("#institute_id").val('${inst_id}');
	}
	
	mockjax1('getSearch_Stu_Reg');
	table = dataTable('getSearch_Stu_Reg');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	
});

function data(getSearch_Stu_Reg) {
	jsondata = [];
	var table = $('#' + getSearch_Stu_Reg).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
	var orderType = order[0][1];
	
	var university_id = $("#university_id").val();
	var institute_id = $("#institute_id").val();
	var name = $("#name").val();
	var ayush_id = $("#ayush_id").val();
	var gender = $("#gender").val();
	var date_of_birth = $("#date_of_birth").val();

	$.post("getFilterSearch_Stu_Reg_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength: pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		
		university_id:university_id,
		institute_id:institute_id,
		name:name,
		ayush_id:ayush_id,
		gender:gender,
		date_of_birth:date_of_birth
		
	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			
			var enroll = ""
			if (j[i].university_enrollment_no !="" && j[i].university_enrollment_no !=null && j[i].university_enrollment_no !="null") {
				enroll= j[i].university_enrollment_no;
			}else {
				enroll="-";
			}
			
			jsondata.push([ j[i].ser,j[i].ayush_id,j[i].university_name,j[i].institute_name,j[i].pers_name,j[i].pers_surname,j[i].pers_father_name,j[i].pers_mother_name,
				j[i].gender_name,j[i].pers_date_of_birth,j[i].category,j[i].religion,j[i].marital_status,j[i].nationality
				,j[i].state_name,j[i].district_name,j[i].neet_rank,j[i].neet_marks,j[i].neet_percentile,enroll,j[i].action]);
		}
	});
	$.post("getTotalSearch_Stu_Reg_dataCount?" + key + "=" + value, {
	
		university_id:university_id,
		institute_id:institute_id,
		name:name,
		ayush_id:ayush_id,
		gender:gender,
		date_of_birth:date_of_birth
		
	}, function(j) {
		FilteredRecords = j;
		});
	
	setTimeout(setTimeLoadForTable, 500);
}

function setTimeLoadForTable(){
	document.querySelectorAll('.VIEWdetails').forEach((items, index) => {
		items.addEventListener('click', event => {
			debugger;
			var val=parseInt(index)+1;
			var hid = document.getElementById('viewId'+val).value;
			if (confirm('Are You Sure You Want to Show Detail ?')) {
				Pop_Up_Stu_Reg(hid);
			} else {
				return false;
			}
		})
	});
}

function Pop_Up_Stu_Reg(a) {

// 	var x = screen.width/2 - 1100/2;
//     var y = screen.height/2 - 900/2;
//     popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
// 	window.onfocus = function () { 
// 	}
// 	$("input#popid").val(a);
// 	document.getElementById('studentpopup_Form').submit();

	$("input#ch_eid").val(a);
	document.getElementById('applicationUrlForm5').submit();


}

function get_inst_by_uni_nch(val) {
	$.ajaxSetup({
		async : false
	});
	
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	$.post('get_inst_by_uni_nch_ctrl?' + key + "=" + value,{university_id : val},function(j) {
// 		alert(j);
			var options = '<option value="' + "0" + '">'
						+ "--Select--" + '</option>';
					for (var i = 0; i < j.length; i++) {
						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
								+ j[i][1] + '</option>';
					}
				$("select#institute_id").html(options);
		
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
	
	
}


function Student_Registration_Report_Excel() {
	// 		alert(1);
	$("#university_id2").val($("#university_id").val());
	$("#institute_id2").val($("#institute_id").val());
	$("#name2").val($("#name").val());
	$("#ayush_id2").val($("#ayush_id").val());
	$("#gender2").val($("#gender").val());
	$("#date_of_birth2").val($("#date_of_birth").val());

	document.getElementById('search3').submit();

}
function Student_Registration_Report_PDF() {
// 			alert("HIIIIIIIIII");


debugger;

	$("#university_id1").val($("#university_id").val());
	$("#institute_id1").val($("#institute_id").val());
	$("#name1").val($("#name").val());
	$("#ayush_id1").val($("#ayush_id").val());
	$("#gender1").val($("#gender").val());
	$("#date_of_birth1").val($("#date_of_birth").val());

	document.getElementById('search2').submit();

}
	
//csp----------------------------
document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('date_of_birth').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_birth').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('date_of_birth').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('date_of_birth').onkeyup = function() {
		return	clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_birth').onchange = function() {
		  clickrecall(this,'DD/MM/YYYY');
		  return validateDate_FutureDate(this.value,this); 
	};
	document.getElementById('pdfex').onclick = function() {
		Student_Registration_Report_PDF();
	};
	document.getElementById('btnExport').onclick = function() {
		Student_Registration_Report_Excel();
	};
	
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
			document.getElementById('university_id').onchange = function() {
				get_inst_by_uni_nch(this.value);
			};
			
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
