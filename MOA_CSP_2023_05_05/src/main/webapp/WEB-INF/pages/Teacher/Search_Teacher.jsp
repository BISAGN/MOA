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
					<h2>Faculty Report</h2>
				</div>
			</div>
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">Faculty Report</li>
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
						
						
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-1">
									<label for="text-input">Ayush Id<span class="mandatory"></span></label>
									<input type="text" id="ayush_id" name="ayush_id" placeholder="Enter Ayush Id" class="autocomplete UpperClassName txt-transupp"
										autocomplete="off"   />
								</div>							
								<!-- end select -->
							</div>

                 <div class="col-12 col-sm-12 col-md-6 col-lg-4">
                	<div class="input-style-1">
                 	 <label>Teacher Code <strong class="mandatory"></strong></label>
                 	<input type="text" name="teacher_code" id="teacher_code" class="form-control" placeholder="Enter Teacher Code"  maxlength="100"></div>
                </div>
                
               <div class="col-12 col-sm-12 col-md-6 col-lg-4">
                	<div class="input-style-2">
                  		<label>Faculty Name <strong class="mandatory"></strong></label>
                 		<input type="text" name="name" id="name" class="form-control" placeholder="Enter Faculty Name"  maxlength="100">
               	 	</div>
                </div>
						

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
								<div class="select-style-1 " >
									<label for="text-input">UG/PG<span class="mandatory"></span></label>
													<div class="select-position">
														<select name="ug_pg" id="ug_pg" class="form-control autocomplete">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${TypeOfDegree}" varStatus="num">card-style
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
														</div>
													</div>
							</div>
							
							
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">	
							<div class="select-style-1 " id="hide_sub1" >
								<label for="text-input">Subject<span class="mandatory"></span></label>
														<div class="select-position">
															<select name="subject" id="subject" class="form-control autocomplete">
																<option value="0">--Select--</option>
						 										<c:forEach var="item" 
						 						items="${getSubjectList}" varStatus="num">
						 						<option value="${item.id}" 
						 						name="${item.subject_name}">${item.subject_name}</option> 
						 						</c:forEach> 
															</select>
														</div>
													</div>
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
										aria-required="true" autocomplete="off" value="DD/MM/YYYY">
								</div>						
						</div>
						
						
					<div class="col-12 col-sm-12 col-md-6 col-lg-4">
                				<div class="input-style-2">
                  				<label>Experience <strong class="mandatory"></strong></label>
                 				<input type="text" name="experience" id="experience" class="form-control" placeholder="Enter Experience"  maxlength="100">
               	 		</div>
               	 	</div>
               	 	
               	 							<div class="col-12 col-sm-12 col-md-6 col-lg-4" id="state_div">
														<div class="select-style-1">
															<label>State Name <strong class="mandatory">
															</strong></label>
															<div class="select-position">
																<select name="state" id="state" class="form-control">
																<option value="0">--Select State--</option>
													<c:forEach var="item" items="${getMedStateName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
																</select>
															</div>
														</div>
													</div>
													
													
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="select-style-1">
															<label>District <strong class="mandatory">
															</strong></label>
															<div class="select-position">
																<select name="district" id="district" class="form-control">
																	<option value="0">--Select District--</option>
																</select>
															</div>
														</div>
													</div>
													
													
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										                <div class="input-style-1">
										                 	<label>City/Village Name<strong class="mandatory">  </strong></label> <input type="text" name="village" id="village" class="form-control" placeholder="Enter City/Village" 
										                           maxlength="100" >
										                </div>
										            </div>					
               	 	
               	 	
               	 	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
                				<div class="input-style-2">
                  				<label>Other Qualification <strong class="mandatory"></strong></label>
                 				<input type="text" name="othquali" id="othquali" class="form-control" placeholder="Enter Other qualification"  maxlength="100">
               	 		</div>
               	 	</div>
						
						
						
					</div>		
						<ul class="buttons-group mainbtn">

							<li>
							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button" value="Search"><i class="lni lni-search-alt"></i>Search</a>
							</li>
							<li>
								<a href="Search_teacher_report" class="main-btn dark-btn n btn-hover" type="button">Reset</a>
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
				<table class="table" id="getSearch_teacher">
					<thead>
							<tr>
				       		    <th ><h6>Ser No</h6></th >
				       		    <th ><h6>Ayush Id</h6></th >
				       		    <th ><h6>Teacher Code</h6></th >
				       		    <th ><h6>Faculty Name</h6></th >
				       		    <th ><h6>University</h6></th >
				       		    <th ><h6>Institute</h6></th > 
				            	

				            	<th ><h6>Gender</h6></th >
				            	<th ><h6>DOB</h6></th >
				            	<th ><h6>UG/PG</h6></th >
				            	<th ><h6>Subject</h6></th >

				            	<th ><h6>State</h6></th >
				            	<th ><h6>District</h6></th >
				            	<th ><h6>Village</h6></th >
				            	<th ><h6>Experience</h6></th >
				            	<th ><h6>Othe Qualificationr</h6></th >
				            	
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


<%-- <c:url value="Student_Registration_Report_PDF" var="searchUrl" /> --%>
<%-- <form:form action="${searchUrl}" method="post" id="search2" --%>
<%-- 	name="search2" modelAttribute="comd1">  --%>
<!-- 	<input type="hidden" name="university_id1" id="university_id1" value="0" /> -->
<!-- 	<input type="hidden" name="institute_id1" id="institute_id1" value="0" /> -->
<!-- 	<input type="hidden" name="name1" id="name1" value="0" /> -->
<!-- 	<input type="hidden" name="ayush_id1" id="ayush_id1" value="0" /> -->
<!-- 	<input type="hidden" name="gender1" id="gender1" -->
<!--  		value="0" />  -->
<!--  	<input type="hidden" name="date_of_birth1" id="date_of_birth1" value="0" />  -->
<%--  </form:form>  --%>


<c:url value="Teacher_Report_PDF" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
  	name="search2" modelAttribute="comd1"> 
	<input type="hidden" name="ayush_id1" id="ayush_id1" value="0" />
	<input type="hidden" name="teacher_code1" id="teacher_code1" value="0" />
	<input type="hidden" name="faculty_name1" id="faculty_name1" value="0" />
	<input type="hidden" name="university_id1" id="university_id1" value="0" />
	<input type="hidden" name="institute_id1" id="institute_id1" value="0" />
	<input type="hidden" name="ug_pg1" id="ug_pg1" value="0" />
	<input type="hidden" name="subject1" id="subject1" value="0" />
	<input type="hidden" name="gender1" id="gender1" value="0" />
	<input type="hidden" name="dob1" id="dob1" value="0" />
	<input type="hidden" name="exp1" id="exp1" value="0" />
	<input type="hidden" name="state1" id="state1" value="0" />
	<input type="hidden" name="district1" id="district1" value="0" />
	<input type="hidden" name="village1" id="village1" value="0" />
	<input type="hidden" name="othquali1" id="othquali1" value="0" />
</form:form> 







<%-- <c:url value="Student_Registration_Report_Excel" var="searchUrl1" /> --%>
<%-- <form:form action="${searchUrl1}" method="post" id="search3" --%>
<%-- 	name="search3" modelAttribute="comd1"> --%>
<!-- 	<input type="hidden" name="university_id2" id="university_id2" value="0" /> -->
<!-- 	<input type="hidden" name="institute_id2" id="institute_id2" -->
<!-- 		value="0" /> -->
<!-- 	<input type="hidden" name="name2" id="name2" value="0" /> -->
<!-- 	<input type="hidden" name="ayush_id2" id="ayush_id2" value="0" /> -->
<!-- 	<input type="hidden" name="gender2" id="gender2" -->
<!-- 		value="0" /> -->
<!-- 	<input type="hidden" name="date_of_birth2" id="date_of_birth2" -->
<!-- 		value="0" /> -->
<%-- </form:form> --%>


<c:url value="Teacher_Report_Excel" var="searchUrl1" />
<form:form action="${searchUrl1}" method="post" id="search3"
	name="search3" modelAttribute="comd1">
	<input type="hidden" name="ayush_id2" id="ayush_id2" value="0" />
	<input type="hidden" name="teacher_code2" id="teacher_code2" value="0" />
	<input type="hidden" name="faculty_name2" id="faculty_name2" value="0" />
	<input type="hidden" name="university_id2" id="university_id2" value="0" />
	<input type="hidden" name="institute_id2" id="institute_id2" value="0" />
	<input type="hidden" name="ug_pg2" id="ug_pg2" value="0" />
	<input type="hidden" name="subject2" id="subject2" value="0" />
	<input type="hidden" name="gender2" id="gender2" value="0" />
	<input type="hidden" name="dob2" id="dob2" value="0" />
	<input type="hidden" name="exp2" id="exp2" value="0" />
	<input type="hidden" name="state2" id="state2" value="0" />
	<input type="hidden" name="district2" id="district2" value="0" />
	<input type="hidden" name="village2" id="village2" value="0" />
	<input type="hidden" name="othquali2" id="othquali2" value="0" />
</form:form>


			<c:url value="View_Teacher_dtlUrl" var="viewUrl" />
			<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="id6">
				<input type="hidden" name="id6" id="id6" value="0"/> 
				<input type="hidden" name="statusview" id="statusview" value="0"/> 
			</form:form>

<script  nonce="${cspNonce}">
$(document).ready(function() {
	datepicketDate('date_of_birth');
	
	var role = '${roleid}';
	
	console.log('${inst_id}');
	
	
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
	if (role == '30') {
		$("#state_div").addClass("d-none");
		//$("#institute_div").addClass("d-none");
		$("#state").val('${state_id}');
		getDistrict('{state_id}');
	}
	
	mockjax1('getSearch_teacher');
	table = dataTable('getSearch_teacher');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	
});

function data(getSearch_teacher) {
	jsondata = [];
	var table = $('#' + getSearch_teacher).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
	var orderType = order[0][1];
	
	var ayush_id = $("#ayush_id").val();
	var teacher_code = $("#teacher_code").val();
	var name = $("#name").val();
	var university_id = $("#university_id").val();
	var institute_id = $("#institute_id").val();
	
	var ug_pg = $("#ug_pg").val();
	var subject = $("#subject").val();
	var gender = $("#gender").val();
	var date_of_birth = $("#date_of_birth").val();
	var experience = $("#experience").val();
	var state = $("#state").val();
	var district = $("#district").val();
	var village = $("#village").val();
	var othquali=$("#othquali").val();
	

	$.post("getFilterTeacher_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength: pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		
		ayush_id : ayush_id,
		teacher_code : teacher_code,
		name : name,
		university_id:university_id,
		institute_id:institute_id,
		ug_pg : ug_pg,
		subject : subject,
		gender:gender,
		date_of_birth:date_of_birth,
		experience : experience,
		state : state,
		district : district,
		village : village,
		othquali : othquali
		
	}, function(j) {
		
		for (var i = 0; i < j.length; i++) {
			
			var enroll = ""
			if (j[i].name_of_exam_degree !="" && j[i].name_of_exam_degree !=null && j[i].name_of_exam_degree !="null") {
				enroll= j[i].name_of_exam_degree;
			}else {
				enroll="-";
			}
			
			jsondata.push([ j[i].ser,j[i].ayush_id,j[i].teacher_code,j[i].first_name,j[i].university_name,j[i].institute_name,
				j[i].gender,j[i].date_of_birth,j[i].degree_name,j[i].subject
				,j[i].state,j[i].district,j[i].per_village,j[i].yr_of_exp,enroll,j[i].action]);
		}
	});
	$.post("DataTableTeacher__DataTotalCount?" + key + "=" + value, {
	
		ayush_id : ayush_id,
		teacher_code : teacher_code,
		name : name,
		university_id:university_id,
		institute_id:institute_id,
		ug_pg : ug_pg,
		subject : subject,
		gender:gender,
		date_of_birth:date_of_birth,
		experience : experience,
		state : state,
		district : district,
		village : village,
		othquali : othquali
		
	}, function(j) {
		FilteredRecords = j;
		});
	
	setTimeout(setTimeLoadForTable, 1000);
}

// function setTimeLoadForTable(){
// 	document.querySelectorAll('.VIEWdetails').forEach((items, index) => {
// 		items.addEventListener('click', event => {
// 			debugger;
// 			var val=parseInt(index)+1;
// 			var hid = document.getElementById('viewId'+val).value;
// 			if (confirm('Are You Sure You Want to Show Detail ?')) {
// 				Pop_Up_Stu_Reg(hid);
// 			} else {
// 				return false;
// 			}
// 		})
// 	});
// }

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

function Teacher_Report_Excel() {	
	// 		alert(1);
			$("#ayush_id2").val($("#ayush_id").val());
			$("#teacher_code2").val($("#teacher_code").val());
			$("#faculty_name2").val($("#name").val());
			$("#university_id2").val($("#university_id").val());
			$("#institute_id2").val($("#institute_id").val());
			$("#ug_pg2").val($("#ug_pg").val());
			$("#subject2").val($("#subject").val());
			$("#gender2").val($("#gender").val());
			$("#dob2").val($("#date_of_birth").val());
			$("#exp2").val($("#experience").val());
			$("#state2").val($("#state").val());
			$("#district2").val($("#district").val());
			$("#village2").val($("#village").val());
			$("#othquali2").val($("#othquali").val());

	document.getElementById('search3').submit();

}






function Student_Registration_Report_PDF() {




	$("#university_id1").val($("#university_id").val());
	$("#institute_id1").val($("#institute_id").val());
	$("#name1").val($("#name").val());
	$("#ayush_id1").val($("#ayush_id").val());
	$("#gender1").val($("#gender").val());
	$("#date_of_birth1").val($("#date_of_birth").val());

	document.getElementById('search2').submit();

}



		function Teacher_Report_PDF() {
				
		
					$("#ayush_id1").val($("#ayush_id").val());
					$("#teacher_code1").val($("#teacher_code").val());
					$("#faculty_name1").val($("#name").val());
					$("#university_id1").val($("#university_id").val());
					$("#institute_id1").val($("#institute_id").val());
					$("#ug_pg1").val($("#ug_pg").val());
					$("#subject1").val($("#subject").val());
					$("#gender1").val($("#gender").val());
					$("#dob1").val($("#date_of_birth").val());
					$("#exp1").val($("#experience").val());
					$("#state1").val($("#state").val());
					$("#district1").val($("#district").val());
					$("#village1").val($("#village").val());
					$("#othquali1").val($("#othquali").val());
			
			
			
			document.getElementById('search2').submit();
			
			}





	
//csp----------------------------
// document.addEventListener('DOMContentLoaded', function() {
	
	function setTimeLoadForTable() {
	
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
// 	document.getElementById('pdfex').onclick = function() {
// 		Student_Registration_Report_PDF();
// 	};
	document.getElementById('pdfex').onclick = function() {
		Teacher_Report_PDF();
	};
// 	document.getElementById('btnExport').onclick = function() {
// 		Student_Registration_Report_Excel();
// 	};
	document.getElementById('btnExport').onclick = function() {
		Teacher_Report_Excel();
	};
	document.getElementById('state').onchange = function() {
		return getDistrict();	
	};
	
	document.querySelectorAll('.viewData').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('viewId'+val).value;
			
			if (confirm('Are You Sure You Want to View Detail ?')) {
				ViewData(hid);
			} else {
				return false;
			}
		})
	});
	
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

	}
	
	
	
function getDistrict() {
	
	var selval = $("#state").val();
	$
			.post(
					"getDistrictUrl?" + key + "=" + value,
					{
						selval : selval
					},
					function(j) {
					
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i].district_id + '" name="'+j[i].district_id+'" >'
									+ j[i].district_name + '</option>';
						}
						$("select#district").html(options);
					});
}
	

function ViewData(id){
	$("#id6").val(id);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
}
	
	
// 		function getuniversitybyinst(obj) {
// 						$.ajaxSetup({
// 						async : false
// 								});
// 				var institute_id = $("select#NameOfUniversity"+obj).val();
				
// 				if(institute_id != "" && institute_id != null){
// 					 $.post("getstatelistbylogin?" + key + "=" + value,{institute_id : institute_id},
// 								function(j) {
// 									var options = '<option value="' + "0" + '">'
// 											+ "--Select--" + '</option>';
// 									for (var i = 0; i < j.length; i++) {
// 										options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
// 										+ j[i][1]+ '</option>';
// 									}
// 									options += '<option value="OTHER" name="OTHER">OTHER</option>';
// 									$("select#NameOfAffUni"+obj).html(options);
// 					});
// 				}
// 		}
	
	
	
	
	

</Script>
