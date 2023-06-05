<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript"	src="js/amin_module/rbac/jquery-1.12.3.js"></script>

<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">

<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>


<section class="dashboard-page">
	<div class="container-fluid">

		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2><span id="lbladd"></span>Homoeopathy University In India</h2>
						<span class="text-red font-size12 enter_by"></span>

					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Homoeopathy
									University In India</li>
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
					<form:form name="fellow_ship_hom_in_india" id="fellow_ship_hom_in_india"
						action="Fellow_Ship_HOM_in_India_Action" method="post"
						class="form-horizontal"
						modelAttribute="Fellow_Ship_HOM_in_India_CMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Homoeopathy University In India</h6>
							<div class="row">

	                             <div class="col-lg-4 col-md-6 col-sm-12">
									<div class="input-style-2">
										<label>Country<strong class="mandatory">*</strong></label> 
											 <input type="text" name="country_id" id="country_id" 
											         class="form-control" readonly="readonly" value="India">
									</div>
								 </div>
											
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
												<select name="medical_course_name" id="medical_course_name">
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
																aria-required="true" autocomplete="off"
																value="DD/MM/YYYY">
								</div>	
								<div class="input-style-2 mt-3">
								<input type='hidden' id="id" name="id" value="0" />
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

								

								<ul class="buttons-group mainbtn">

									<li><a id="btn-reload"
										class="main-btn secondary-btn btn-hover btn-iconic-icon"
										type="button"><i class="lni lni-search-alt"></i>Search</a></li>

									<li><input id="btn-save"
										class="main-btn info-btn btn-hover" type="submit"
										onclick="return Validation();" value="Save" /></li>
									<li><a href="FellowShipHOMInIndiaUrl"
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
									<th><h6>Country</h6></th>
									<th><h6>Name of University</h6></th>
									<th><h6>Name of College</h6></th>
									<th><h6>Abbreviation</h6></th>
									<th><h6>Recognized Medical Qualification</h6></th>
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
		</div></div>
</section>


<c:url value="deleteFellow_ship_Hom_in_india_Url" var="deleteFellow_ship_Hom_in_india_Url" />
<form:form action="${deleteFellow_ship_Hom_in_india_Url}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id33">
	<input type="hidden" name="id33" id="id33" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {

	datepicketDate('validity_period');
	mockjax1('search_system');
	table = dataTable('search_system');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$('#country_id option[value="50"]').attr('selected', true)
});

	function setTimeLoadForTable(){
		
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
				
//	 			var vpf = vp.substring(8,10) +"/"+vp.substring(5,7)+"/"+vp.substring(0,4);
				
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
		var country_id = $("#country_id").val();
		var university_name = $("#university_name").val();
		var college_id = $("#college_id").val();
		var abbreviation = $("#abbreviation").val();
		var medical_course_name = $("#medical_course_name").val();
		var validity_period = $("#validity_period").val();
		var digital_code = $("#digital_code").val();
		

		$.post("getFilter_fellow_ship_hom_in_india_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			country_id : country_id,
			university_name : university_name,
			college_id : college_id,
			abbreviation : abbreviation,
			medical_course_name : medical_course_name,
			validity_period : validity_period,
			digital_code : digital_code

		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([j[i].sr_no,j[i].country_id,j[i].university_name,j[i].institute_name,
					j[i].abbreviation,j[i].qualification,j[i].validity_period,j[i].digital_code,j[i].action ]);
			}
		});

		$.post("getTotal_fellow_ship_hom_in_india_dataCount?" + key + "=" + value, {
			Search : Search,
			country_id : country_id,
			university_name : university_name,
			college_id : college_id,
			abbreviation : abbreviation,
			medical_course_name : medical_course_name,
			validity_period : validity_period,
			digital_code : digital_code
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 10);
	}

	function getAbbreviationListfellow(){
		$("#Abbreviation").empty();
		var institute_id = $("#college_id").val();
		
		$.post("getAbbreviationListfellow?" + key + "=" + value, {
			
			institute_id : institute_id
			
		}, function(j) {
			$("#abbreviation").val(j);
		});
		
	}
	
	
	function EditData(id,country_id,university_name,college_id,abbreviation,medical_course_name,validity_period,digital_code) {
		document.getElementById('lbladd').innerHTML = "Update  ";
		$("select#country_id").val(country_id);
		$("#university_name").val(university_name);
		getInstituteFellow();
		$("#college_id").val(college_id);
		$("input#abbreviation").val(abbreviation);
		$("#medical_course_name").val(medical_course_name);
		$("input#validity_period").val(validity_period);
		$("input#digital_code").val(digital_code);
		document.getElementById('id').value=id;
		
	}
	function deleteData(id) {
		$("#id33").val(id);
		document.getElementById('deleteForm').submit();
		}	

function Validation() {
	if ($("#country_id").val().trim() == "0") {
		alert("Please Select Country.");
		$("select#country_id").focus();
		return false;
	}
	if ($("#university_name").val().trim() == "0") {
		alert("Please Select University.");
		$("select#university_name").focus();
		return false;
	}
	if ($("#college_id").val().trim() == "0") {
		alert("Please Select College.");
		$("select#college_id").focus();
		return false;
	}
	
	if ($("#abbreviation").val().trim() == "0") {
		alert("Please Enter Abbreviation .");
		$("input#abbreviation").focus();
		return false;
	}
	if ($("#medical_course_name").val().trim() == "0") {
		alert("Please Enter Qualification .");
		$("input#medical_course_name").focus();
		return false;
	}
	if ($("#validity_period").val().trim() == "0") {
		alert("Please Select Validity Period .");
		$("input#validity_period").focus();
		return false;
	}
}

	
	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		
		document.getElementById('abbreviation').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		
		document.getElementById('college_id').onchange = function() {
			return getAbbreviationListfellow();
		};
		document.getElementById('university_name').onchange = function() {
			getInstituteFellow();
		};
	});
	function getInstituteFellow() {
		var selval = $("#university_name").val();0
		$.post("getInstituteUrlfellowship?" + key + "=" + value,
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
</script>
