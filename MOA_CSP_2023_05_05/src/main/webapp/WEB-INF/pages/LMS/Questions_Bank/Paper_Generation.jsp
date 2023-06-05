<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- <script type="text/javascript" src="js/common_js/course_planning.js"></script> -->
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
					<h2>Paper Generation</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">Paper Generation</li>
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
               <form:form name="papergeneration" id="papergeneration" action="papergenerationAction" method='POST' commandName="papergenerationCMD" >
				<div class="card-style mb-30">
				 <div class="custom-field-block">
					<h6 class="mb-25">Paper Generation</h6>
						<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input" >System<span class="mandatory">*</span></label>
									<div class="select-position">
										<input type="hidden" id="id" name="id"  value="0" autocomplete="off"> 
									
										<select class="singleselect form-control form-control-lg" name="system_id" id="system_id">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getSystemList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
										</select>
									</div>
								</div>								
								<!-- end select -->
							</div>
							
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Degree<span class="mandatory">*</span></label>
									<div class="select-position">
										<input type="hidden" id="id" name="id" value="0" autocomplete="off"> 
									
										<select class="singleselect form-control form-control-lg" name="degree_id" id="degree_id" >
												<option value="0">--Select--</option>
												
										</select>
									</div>
								</div>								
							
								
								<!-- end select -->
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Set<span class="mandatory">*</span></label>
									<div class="select-position">
										<select class="singleselect form-control form-control-lg" name="set_id" id="set_id">
											<option value="0">--Select--</option>
										</select>
									</div>
								</div>								
								
								
								<!-- end select -->
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Course<span class="mandatory">*</span></label>
									<div class="select-position">
												<input type="hidden" id="id" name="id" value="0" autocomplete="off"> 
									
												 <select class="singleselect form-control form-control-lg" name="course_id" id="course_id"  >
													<option value="0">--Select--</option>
												</select>
									</div>
								</div>								
							
								
								<!-- end select -->
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Module<span class="mandatory">*</span></label>
									<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="module_id" id="module_id" >
												<option value="0">--Select--</option>
											</select>
									</div>
								</div>								
							
								
								<!-- end select -->
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-1">
									<label for="text-input">Exam Name<span class="mandatory">*</span></label>
									<input type="text" id="exam_name" name="exam_name" class="form-control" class="autocomplete UpperClassName txt-transupp"
									 placeholder="Enter Exam Name " autocomplete="off"  >	
								</div>
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-1">
									<label for="text-input">Total Question<span class="mandatory">*</span></label>
									<input type="number" id="total_que" value="0"  min="0" name="total_que" placeholder="Enter Total Question "
									class="autocomplete UpperClassName txt-transupp" autocomplete="off" minlength="1" maxlength="2" required   
									oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"/> 
								</div>
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-1">
									<label for="text-input">Total Marks<span class="mandatory">*</span></label>
									<input type="text" id="total_marks" name="total_marks" class="autocomplete UpperClassName txt-transupp" 
									placeholder="Enter Total Marks" autocomplete="off" maxlength="5;" >
								</div>
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-1">
									<label for="text-input">Easy<span class="mandatory">*</span></label>
									
									
									<input type="text" id="easy" name="easy" class="autocomplete UpperClassName txt-transupp"
									 placeholder="Enter Easy"  autocomplete="off" maxlength="5;"   >
								</div>
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-1">
									<label for="text-input">Medium<span class="mandatory">*</span></label>
									
									<input type="text" id="medium" name="medium" class="autocomplete UpperClassName txt-transupp"
									placeholder="Enter Medium"  autocomplete="off" maxlength="5;"  >
								</div>
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-1">
									<label for="text-input">Hard<span class="mandatory">*</span></label>
										<input type="text" id="hard" name="hard"" class="autocomplete UpperClassName txt-transupp" 
										placeholder="Enter Hard" autocomplete="off" maxlength="5;" >
										
								</div>
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-1">
									<label for="text-input">Passing Marks<span class="mandatory">*</span></label>
							
									<input type="text" id="passing_marks" name="passing_marks"  class="autocomplete UpperClassName txt-transupp"  
									 placeholder="Enter Passing Marks" autocomplete="off" maxlength="5;" >
								</div>
							</div>
			
						</div>	
						</div>				
					<!-- Bottom Button Start -->
								<div class="btn-bottom">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12">
						<ul class="buttons-group mainbtn">

							<li>
							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch" type="button" ><i class="lni lni-search-alt"></i>Search</a>
							</li>
							<li>
								<input   id="btn-save" class="main-btn info-btn btn-hover btnsave" type="submit" value="Save">
							</li>
							<li>
								<a href="Paper_GenerationUrl" class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a>
							</li>
						</ul>
						</div>
						</div>
						</div>
				</div>
				<!-- end card -->
 
<c:url value="deletecourse_Url" var="deletecourse_Url" />
<form:form action="${deletecourse_Url}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>
<section class="single-detail-block">
<div class="row">
	<div class="col-lg-12 col-md-12 col-sm-12">
		<div class="card-style mb-30">
			<div class="table-wrapper table-responsive custom-datatable-p">
				<table class="table" id="search_paper_gen">
					<thead>
							<tr>
								<th><h6>Sr No</h6></th>
								<th ><h6>System</h6></th>
								<th ><h6>Course</h6></th>
								<th ><h6>Set</h6></th>
								<th ><h6>Module</h6></th>
								<th ><h6>Exam name</h6></th>
								<th ><h6>Total question</h6></th>
								<th ><h6>Total marks</h6></th>
								<th ><h6>Easy</h6></th>
								<th ><h6>Medium</h6></th>
								<th ><h6>Hard</h6></th>
								<th ><h6>Passing marks</h6></th>
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




<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {

	mockjax1('search_paper_gen');
	table = dataTable('search_paper_gen');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	
	if(window.location.href.includes("msg"))
	{
		 var url = window.location.href.split("?msg")[0];
		 window.location = url;
	}
});

function data(search_paper_gen) {
	jsondata = [];
	var table = $('#' + search_paper_gen).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
	var orderType = order[0][1];

	var system_id = $("#system_id").val();
	var course_id = $("#course_id").val();
	var set_id = $("#set_id").val();
	var module_id = $("#module_id").val();
	var exam_name = $("#exam_name").val();
	var total_que = $("#total_que").val();
	var total_marks = $("#total_marks").val();
	var easy = $("#easy").val();
	var medium = $("#medium").val();
	var hard = $("#hard").val();
	var passing_marks = $("#passing_marks").val();
	
	$.post("getFilterPaper_Gen_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		
		system_id:system_id,
		course_id:course_id,
		set_id:set_id,
		module_id:module_id,
		exam_name:exam_name,
		total_que:total_que,
		total_marks:total_marks,
		easy:easy,
		medium:medium,
		hard:hard,
		passing_marks:passing_marks
		
	}, function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].ser,j[i].system_name, j[i].course_name,j[i].setname,j[i].module_name,j[i].exam_name,j[i].total_que,j[i].total_marks,
				j[i].easy,j[i].medium,j[i].hard,j[i].passing_marks,j[i].action ]);
		}
	});
	$.post("getTotalPaper_Gen_dataCount?" + key + "=" + value, {
		system_id:system_id,
		course_id:course_id,
		set_id:set_id,
		module_id:module_id,
		exam_name:exam_name,
		total_que:total_que,
		total_marks:total_marks,
		easy:easy,
		medium:medium,
		hard:hard,
		passing_marks:passing_marks
	}, function(j) {
		FilteredRecords = j;
		});
}

function getDegreeNamebysystem() {

	var system_name = $("select#system_id").val();
	
	$
			.post(
					"getDegreeListBySystem?" + key + "=" + value,
					{
						system_name : system_name
					
					},
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

function getSetNamebyDegree() {

	var system_name = $("select#system_id").val();
	var degree = $("select#degree_id").val();
	$
			.post(
					"getSetListByDegree?" + key + "=" + value,
					{
						system_name : system_name,
						degree : degree
					},
					function(j) {
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#set_id").html(options);
					});
}

function getCourseNamebySet() {

	var system_name = $("select#system_id").val();
	var degree = $("select#degree_id").val();
	var setname = $("select#set_id").val();
	$
			.post(
					"getCourseListBySet?" + key + "=" + value,
					{
						system_name : system_name,
						degree : degree,
						setname : setname
						
					},
					function(j) {
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#course_id").html(options);
					});
}

function getModuleNamebycourse() {

	var course_name = $("select#course_id").val();
	
	$
			.post(
					"getModuleListByCourse?" + key + "=" + value,
					{
						
						course_name : course_name
					},
					function(j) {
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#module_id").html(options);
					});
}
function Validation(){
	
	if ($("#system_id").val() == "0") {
		alert("Please Select System.");
		$("select#system_id").focus();
		return false;
	}
	if ($("#degree_id").val() == "0") {
		alert("Please Select Degree.");
		$("select#degree_id").focus();
		return false;
	} 
	if ($("#set_id").val() == "0") {
		alert("Please Select Set.");
		$("select#set_id").focus();
		return false;
	} 
	
	if ($("#course_id").val() == "0") {
		alert("Please Select Course.");
		$("select#course_id").focus();
		return false;
	} 
	if ($("#module_id").val() == "0") {
		alert("Please Select Module.");
		$("select#module_id").focus();
		return false;
	} 
	if ($("#exam_name").val().trim() == "") {
		alert("Please Enter Exam Name.");
		$("input#exam_name").focus();
		return false;
	}
	if ($("#total_que").val() == "0") {
		alert("Please Enter Total Question.");
		$("input#total_que").focus();
		return false;
	}
	if ($("#total_marks").val().trim() == "") {
		alert("Please Enter Total Marks.");
		$("input#total_marks").focus();
		return false;
	}
	if ($("#easy").val().trim() == "") {
		alert("Please Enter Easy Question Marks.");
		$("input#easy").focus();
		return false;
	}
	
	if ($("#medium").val().trim() == "") {
		alert("Please Enter Medium Question Marks.");
		$("input#medium").focus();
		return false;
	}
	if ($("#hard").val().trim() == "") {
		alert("Please Enter Hard Question Marks.");
		$("input#hard").focus();
		return false;
	}
	if ($("#passing_marks").val().trim() == "") {
		alert("Please Enter Passing Marks.");
		$("input#passing_marks").focus();
		return false;
	}
	
	var total_marks = $("input#total_marks").val();
	var passing_marks = $("input#passing_marks").val();
	if (parseInt(total_marks) < parseInt(passing_marks)) {
		alert("Passing Marks Should Be Less Than Total Marks.");
		$("input#total_marks").focus();
		$("input#passing_marks").focus();
		return false;
	}
	return true;
}

$('#total_que').keypress(function(evt) {
	  if (evt.which == "0".charCodeAt(0) && $(this).val().trim() == "") {
		  alert("Please Do Not Enter First Digit 0" );
		  $("input#total_que").focus();
	  return false;
	   }
	});

$('#total_marks').keypress(function(evt) {
	  if (evt.which == "0".charCodeAt(0) && $(this).val().trim() == "") {
		  alert("Please Do Not Enter First Digit 0");
		  $("input#total_marks").focus();
	  return false;
	   }
	});
$('#easy').keypress(function(evt) {
	  if (evt.which == "0".charCodeAt(0) && $(this).val().trim() == "") {
		  alert("Please Do Not Enter First Digit 0");
		  $("input#easy").focus();
	  return false;
	   }
	});
$('#medium').keypress(function(evt) {
	  if (evt.which == "0".charCodeAt(0) && $(this).val().trim() == "") {
		  alert("Please Do Not Enter First Digit 0");
		  $("input#medium").focus();
	  return false;
	   }
	});
$('#hard').keypress(function(evt) {
	  if (evt.which == "0".charCodeAt(0) && $(this).val().trim() == "") {
		  alert("Please Do Not Enter First Digit 0");
		  $("input#hard").focus();
	  return false;
	   }
	});	
$('#passing_marks').keypress(function(evt) {
	  if (evt.which == "0".charCodeAt(0) && $(this).val().trim() == "") {
		  alert("Please Do Not Enter First Digit 0" );
		  $("input#passing_marks").focus();
	  return false;
	   }
	});

document.addEventListener('DOMContentLoaded', function () {			
	
	document.getElementById('btn-save').onclick = function () {
				return Validation();
			};
			document.getElementById('system_id').onchange = function () {
				getDegreeNamebysystem(); 
			};
			document.getElementById('degree_id').onchange = function () {
				getSetNamebyDegree(); 
			};
			document.getElementById('set_id').onchange = function () {
				getCourseNamebySet();
			};
			document.getElementById('course_id').onchange = function () {
				getModuleNamebycourse();
			};
			document.getElementById('total_que').onkeypress = function () {
				return isNumberKey0to9(event);
			};
			document.getElementById('total_marks').onkeypress = function () {
				return isNumberKey0to9(event);
			};
			document.getElementById('easy').onkeypress = function () {
				return isNumberKey0to9(event);
			};
			document.getElementById('medium').onkeypress = function () {
				return isNumberKey0to9(event);
			};
			document.getElementById('hard').onkeypress = function () {
				return isNumberKey0to9(event);
			};
			document.getElementById('passing_marks').onkeypress = function () {
				return isNumberKey0to9(event);
			};
			
	});
	
///no field max length define 	
$(function () {
	   $( "#total_que" ).change(function() {
	   var max = parseInt($(this).attr('max'));
	   var min = parseInt($(this).attr('min'));
	   if ($(this).val() > max)
	   {
	      $(this).val(max);
	   }
	   else if ($(this).val() < min)
	   {
	      $(this).val(min);
	   }       
	 }); 
	});
</script>