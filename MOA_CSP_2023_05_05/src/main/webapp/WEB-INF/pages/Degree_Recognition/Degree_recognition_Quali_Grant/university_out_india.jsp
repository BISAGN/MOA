<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script type="text/javascript" src="js/watermark/common.js"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>


<section class="dashboard-page">
	<div class="container-fluid">

		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2><span id="lbladd">University Out India</h2><span class="text-red font-size12 enter_by"></span>
						
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">University Out India</li>
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
					<form:form name="university_out" id="university_out"
						action="University_Out_Action" method="post"
						class="form-horizontal" modelAttribute="University_Out_CMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">University Out India</h6>
							<div class="row">
								
								<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Country<strong class="mandatory">*</strong></label> 
											 <input type="text" name="country_name"
														id="country_name" class="form-control" readonly="readonly" value="India">
												</div>
								</div>
								
									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="select-style-1">
											<label>Select University<strong class="mandatory">*</strong></label>
											<div class="select-position">
												<select name="university_name" id="university_name" > <!-- class="singleselect form-control form-control-lg" -->
													<option value="0">---Select University---</option>
													<c:forEach var="item" items="${getMedUniversityName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="select-style-1">
											<label>Name of College<strong class="mandatory">*</strong></label>
											<div class="select-position">
												<select name="college_name" id="college_name">
													<option value="0">---Select Institute---</option>
												</select>
											</div>
										</div>
									</div>
								
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										
										<label>Abbreviation:<span class="mandatory">*</span></label>
										<input type="text" id="abbreviation" name="abbreviation"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="100" placeholder="Abbreviation" />
											
								</div>
									<!-- end input -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Name of Recognized Medical Course<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="medical_course_name" id="medical_course_name" >
													<option value="0" name="select">--Select--</option>
											    	<c:forEach var="item" items="${getMedicalList}" varStatus="num">													 
														<option value="${item[0]}" name="${item[0]}">${item[1]}</option> 
 													</c:forEach> 
												</select>
											</div>
									</div>
								</div>
								
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-2">
										<label for="username">Validity Period</label> 
												<input type="text" name="validity_period" id="validity_period" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control-sm form-control effect-9 "
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');"
																aria-required="true" autocomplete="off"
																value="DD/MM/YYYY">
								</div>	
								<div class="input-style-2 mt-3">
										<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
								</div>																																			
							</div>
								
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Digital Code<span class="mandatory">*</span></label> <input
											type="number" id="digital_code" name="digital_code" 
											class=" form-control UpperClassName txt-transupp" autocomplete="off" max="1000" placeholder="Digital Code" />
									</div>
									<!-- end input -->
								</div>
								


								<input type="hidden" id="id" name="id" class="form-control"
									value="0" autocomplete="off">

								<ul class="buttons-group mainbtn">

									<li><a id="btn-reload"
										class="main-btn secondary-btn btn-hover btn-iconic-icon"
										type="button"><i class="lni lni-search-alt"></i>Search</a></li>

									<li><input id="btn-save"
										class="main-btn info-btn btn-hover" type="submit"
										onclick="return Validation();" value="Save" /></li>
									<li><a href="UniversityOutIndiaUrl"
										class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
								</ul>
								</div>
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
						<table class="table" id="search_system_out">
							<thead>
								<tr>
									<th><h6>Sr No</h6></th>
									<th><h6>Country</h6></th>
									<th><h6>Name of University</h6></th>
									<th><h6>Name of College</h6></th>
									<th><h6>Abbreviation</h6></th>
									<th><h6>Name of Recognized Medical Course</h6></th>
									<th><h6>Validity Period</h6></th>
									<th><h6>Digital Code</h6></th>
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
		
</section>
<c:url value="getSearch_Out_University" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Uni_details1">
	<input type="hidden" name="country_name1" id="country_name1" value="0" />
	<input type="hidden" name="university_name1" id="university_name1" value="0" />
	<input type="hidden" name="college_name1" id="college_name1" />
	<input type="hidden" name="abbreviation1" id="abbreviation1" value="0" />
	<input type="hidden" name="medical_course_name1" id="medical_course_name1" value="0" />
	<input type="hidden" name="validity_period1" id="validity_period1" value="0" />
	<input type="hidden" name="adigital_code1" id="digital_code1" value="0" />
</form:form>

<c:url value="delete_Url34" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id34">
	<input type="hidden" name="id34" id="id34" value="0" />
</form:form>
<c:url value="Systemreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

function setTimeLoadForTable(){
	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	document.querySelectorAll('.editOnclick').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var ap = document.getElementById('apIdAGE'+val).value;			
			var con = document.getElementById('conID'+val).value;
			var uni = document.getElementById('uniID'+val).value;
			var clg = document.getElementById('clgID'+val).value;
			var abb = document.getElementById('abbID'+val).value;
			var qua = document.getElementById('quaID'+val).value;
			var vp = document.getElementById('vpID'+val).value;
			var dc = document.getElementById('dcId'+val).value;
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				EditData(ap,con,uni,clg,abb,qua,vp,dc);
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

function data(search_system_out) {
	
	jsondata = [];
	var table = $('#' + search_system_out ).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
	var orderType = order[0][1];
	var country_name = $("#country_name").val();
	var university_name = $("#university_name").val();
	var college_name = $("#college_name").val();
	var abbreviation = $("#abbreviation").val();
 	var medical_course_name = $("#medical_course_name").val();
 	var validity_period = $("#validity_period").val();
 	var digital_code = $("#digital_code").val();
 
	
// 	if(hostel_name == null || hostel_name == "null"){
// 		hostel_name = ""
// 	}
// 	if(hostel_address == null){
// 		hostel_address = ""
// 	}
// 	if(no_of_rooms == null){
// 		no_of_rooms = ""
// 	}
	
	
	$.post("getFilter_Out_University?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		country_name : country_name,
		university_name : university_name,
		college_name : college_name,
		abbreviation : abbreviation,
		medical_course_name : medical_course_name,
		validity_period : validity_period,
		digital_code : digital_code
	}, function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].sr_no,j[i].country_name,j[i].university_name,j[i].institute_name,j[i].abbreviation,j[i].qualification,j[i].validity_period,j[i].digital_code,j[i].action ]);
		}
	});
	$.post("getTotal_Out_University?" + key + "=" + value, {
		Search : Search,
		country_name : country_name,
		university_name : university_name,
		college_name : college_name,
		abbreviation : abbreviation,
		medical_course_name : medical_course_name,
		validity_period : validity_period,
		digital_code : digital_code
		
		
	}, function(j) {
		FilteredRecords = j;

	});
	setTimeout(setTimeLoadForTable, 1000);
}

function EditData(id,country_name,university_name,college_name,abbreviation,medical_course_name,validity_period,digital_code) {
	document.getElementById('lbladd').innerHTML = "Update  ";
	$("select#country_name").val(country_name);
	$("#university_name").val(university_name);
	 getInstitute();
	$("#college_name").val(college_name);
	$("input#abbreviation").val(abbreviation);
	$("#medical_course_name").val(medical_course_name);
	$("input#validity_period").val(validity_period);
	$("input#digital_code").val(digital_code);
	document.getElementById('id').value=id;
	
	var $select2 = $('.select2').select2({
    	containerCssClass: "wrap"
	});
	
}

function deleteData(id) {
	$("#id34").val(id);
	document.getElementById('deleteForm').submit();
	}	

function Search() {
	$("#country_name").val($('#country_name').val());
	$("#university_name").val($('#university_name').val());
	$("#college_name").val($('#college_name').val());
	$("#abbreviation").val($('#abbreviation').val());
	$("#medical_course_name").val($('#medical_course_name').val());
	$("#validity_period").val($('#validity_period').val());
	$("#digital_code").val($('#digital_code').val());

	document.getElementById('searchForm').submit();
}
function Validation() {
		
		
		if ($("#country_name").val() =="0" ) {
			alert("Please Enter Country Name");
			$("input#country_name").focus();
			return false;
		}
		if ($("#university_name").val() =="" ) {
			alert("Please Enter University Name");
			$("input#university_name").focus();
			return false;
		}
		if ($("#college_name").val() =="" ) {
			alert("Please Enter College Name");
			$("input#college_name").focus();
			return false;
		}
		if ($("#abbreviation").val() =="" ) {
			alert("Please Enter Abbreviation");
			$("input#abbreviation").focus();
			return false;
		}
		if ($("#medical_course_name").val() =="" ) {
			alert("Please Enter Medical Course Name");
			$("input#medical_course_name").focus();
			return false;
		}
		if ($("#validity_period").val() =="" ) {
			alert("Please Enter Validity Period");
			$("input#validity_period").focus();
			return false;
		}
		if ($("#digital_code").val() =="" ) {
			alert("Please Enter Digital Code");
			$("input#digital_code").focus();
			return false;
		}
		
		return true;
	} 

$(document).ready(function() {
		
		datepicketDate('validity_period');
		
	mockjax1('search_system_out');
	table = dataTable('search_system_out');
	$('#btn-reload').on('click', function(){
    	table.ajax.reload();
    });
});


function getAbbreviationListout(){
	$("#Abbreviation").empty();
	var institute_id = $("#college_name").val();
	
	$.post("getAbbreviationListoutindia?" + key + "=" + value, {
		
		institute_id : institute_id
		
	}, function(j) {
		$("#abbreviation").val(j);
	});
	
}

document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	
	document.getElementById('abbreviation').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('university_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('college_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('medical_course_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('university_name').onchange = function() {
		 getInstitute();
	};
	document.getElementById('college_name').onchange = function() {
		return getAbbreviationListout();
	};
});
function getInstitute() {
	var selval = $("#university_name").val();0
	$.post("getInstituteUrlindiaout?" + key + "=" + value,
					{
						selval : selval
					},
					function(j) {
						var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							console.log(j[i]);
							options += '<option   value="' + j[i][3].id + '" name="'+j[i][3].id+'" >'
									+ j[i][3].institute_name + '</option>';
						}
						$("select#college_name").html(options);
					});
}

</script>




