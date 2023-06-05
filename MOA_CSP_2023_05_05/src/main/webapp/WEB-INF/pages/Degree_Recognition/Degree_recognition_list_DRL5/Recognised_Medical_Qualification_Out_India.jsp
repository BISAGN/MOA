<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<link href="js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="js/dropDown/select2.min.js" type="text/javascript"></script>
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
						<h2>
							<span id="lbladd">MEDICAL INST OUTSIDE INDIA AS PER SCHEDULE (III)</span>
						</h2>
						<span class="text-red font-size12 enter_by"></span>

					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">MEDICAL INST OUTSIDE INDIA AS PER SCHEDULE (III)</li>
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
					<form:form name="university" id="university"
						action="Reco_Med_Qua_Out_India_Action" method="post" class="form-horizontal"
						modelAttribute="Reco_Med_Qua_Out_India_CMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">MEDICAL INST OUTSIDE INDIA AS PER SCHEDULE (III)</h6>
							<div class="row">

<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="select-style-1">
											<label>Select University<strong class="mandatory">*</strong></label>
											<div class="select-position">
												<select name="university_name" id="university_name" >
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
												<select name="college_id" id="college_id">
													<option value="0">---Select Institute---</option>
												</select>
											</div>
										</div>
									</div>
									
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="input-style-2">
												<label>Abbreviation<span class="mandatory">*</span></label> <input
													type="text" id="abbreviation" name=abbreviation
													class=" form-control UpperClassName txt-transupp"
													autocomplete="off" max="1000" placeholder="Abbreviation" />
									   	 </div>
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
			<input type="hidden" id="id" name="id" class="form-control"	value="0">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Remarks<span class="mandatory">*</span></label> <input
											type="text" id="remarks" name="remarks"
											class=" form-control UpperClassName txt-transupp"
											autocomplete="off" max="1000" placeholder="Enter Remarks" />
									</div>
								</div>


								<ul class="buttons-group mainbtn">

									<li><a id="btn-reload"
										class="main-btn secondary-btn btn-hover btn-iconic-icon"
										type="button"><i class="lni lni-search-alt"></i>Search</a></li>

									<li><input id="btn-save"
										class="main-btn info-btn btn-hover" type="submit"
										onclick="return Validation();" value="Save" /></li>
									<li><a href="recognitionmedicalqualificationoutindiaurl"
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
						<table class="table" id="search_system">
							<thead>
								<tr>
									<th><h6>Sr No</h6></th>
									<th><h6>Name of University</h6></th>
									<th><h6>Name of College</h6></th>
									<th><h6>Abbreviation</h6></th>
									<th><h6>Recognized Medical Qualification</h6></th>
									<th><h6>Remarks</h6></th>
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

<c:url value="deleterec_medical_quali_out_india" var="deleterec_medical_quali_out_india" />
<form:form action="${deleterec_medical_quali_out_india}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	
	mockjax1('search_system');
	table = dataTable('search_system');
	$('#btn-reload').on('click', function(){
		table.ajax.reload();
	});
});

document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	
	document.getElementById('university_name').onchange = function() {
		getInstituteMedicalC();
	};
	
	document.getElementById('college_id').onchange = function() {
		return getAbbreviationListmeC();
	};
	
});

function getInstituteMedicalC() {
	var selval = $("#university_name").val();0
	$.post("getInstituteUrlmedic?" + key + "=" + value,
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
						$("select#college_id").html(options);
					});
}

function getAbbreviationListmeC(){
	$("#Abbreviation").empty();
	var institute_id = $("#college_id").val();
	
	$.post("getAbbreviationListmeC?" + key + "=" + value, {
		
		institute_id : institute_id
		
	}, function(j) {
		$("#abbreviation").val(j);
	});
	
}

function data(search_system) {
	
	jsondata = [];
	var table = $('#' + search_system).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];
	var university_name = $("#university_name").val();
	var college_id = $("#college_id").val();
	var abbreviation = $("#abbreviation").val();
	var medical_course_name = $("#medical_course_name").val();
	var remarks = $("#remarks").val();
	
	$.post("getFilter_rec_med_qua_out_ind_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		university_name : university_name,
		college_id : college_id,
		abbreviation : abbreviation,
		medical_course_name : medical_course_name,
		remarks : remarks
		
	}, function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].sr_no,j[i].university_name,j[i].institute_name,
				j[i].abbreviation,j[i].qualification,j[i].remarks,j[i].action ]);
		}
	});
	$.post("getTotal_rec_med_qua_out_ind_dataCount?" + key + "=" + value, {
		Search : Search,
		university_name : university_name,
		college_id : college_id,
		abbreviation : abbreviation,
		medical_course_name : medical_course_name,
		remarks : remarks
		
	}, function(j) {
		FilteredRecords = j;

	});
	setTimeout(setTimeLoadForTable, 10);
}

function setTimeLoadForTable(){
	
	document.querySelectorAll('.editOnclickC').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var ap = document.getElementById('apIdAGE'+val).value;			
			var uni = document.getElementById('unicID'+val).value;
			var clg = document.getElementById('clgcID'+val).value;
			var abb = document.getElementById('abbcID'+val).value;
			var qu = document.getElementById('quacID'+val).value;
			var dc = document.getElementById('dccId'+val).value;
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				EditData(ap,uni,clg,abb,qu,dc);
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

function EditData(id,university_name,college_id,abbreviation,medical_course_name,remarks) {
	document.getElementById('lbladd').innerHTML = "Update  ";
	$("#university_name").val(university_name);
	getInstituteMedicalC();
	$("#college_id").val(college_id);
	$("input#abbreviation").val(abbreviation);
	$("#medical_course_name").val(medical_course_name);
	$("input#remarks").val(remarks);
	document.getElementById('id').value=id;
}

function deleteData(id) {
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
	}	

function Search() {
	$("#university_id").val($('#university_id').val());
	$("#abbreviation").val($('#abbreviation').val());
	$("#qualification").val($('#qualification').val());
	$("#remark").val($('#remark').val());

	document.getElementById('searchForm').submit();
}
function Validation() {
	
		
	if ($("#university_id").val() =="" ) {
		alert("Please Enter Name of University");
		$("input#university_id").focus();
		return false;
	}

	if ($("#qualification").val() =="" ) {
		alert("Please Enter Recognized Medical Course");
		$("input#qualification").focus();
		return false;
	}
	if ($("#abbreviation").val() =="" ) {
		alert("Please Enter Abbreviation");
		$("input#abbreviation").focus();
		return false;
	}
	if ($("#remark").val() =="" ) {
		alert("Please Enter Remark");
		$("input#remark").focus();
		return false;
	}
		
		return true;
	} 
	



</script>





