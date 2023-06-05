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
							<span id="lbladd">MEDICAL INST BY DIRECT NOTIFICATION </span>
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
								<li class="breadcrumb-item active" aria-current="page">MEDICAL
									INST BY DIRECT NOTIFICATION</li>
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
			<input type= "hidden"  id="h_id" name="h_id">
				<div class="col-12">
					<!-- input style start -->
					<form id="form_section_35_all_state">
						<div class="card-style mb-30">
							<h6 class="mb-25">MEDICAL INST BY DIRECT NOTIFICATION</h6>
							<div class="row">
								<input type="hidden" id="form_section_35_state_id"
									name="form_section_35_state_id" value="0"
									class="form-control autocomplete" autocomplete="off">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="username">File no.<span class="mandatory">*</span></label>
										<input type="text" name=file_no id="file_no"
											class="form-control" placeholder="Enter file no.">
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label for="username">File Date<span
											class="mandatory">*</span>
										</label> <input type="text" name="file_date" id="file_date"
											maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
											class="form-control-sm form-control effect-9 "
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');"
											aria-required="true" autocomplete="off" value="DD/MM/YYYY">
									</div>
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="username">State.<span class="mandatory">*</span></label>
										<input type="text" name="all_state" id="all_state"
											class="form-control" placeholder="State.">
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Name of University<span
											class="mandatory">*</span></label>
										<div class="select-position">
											<select name="all_university_name" id="all_university_name"
												class="form-control">
												<option value="0" name="select">--Select
													University--</option>
												<c:forEach var="item" items="${getUniverCityList}"
													varStatus="num">
													<option value="${item.id}" name="${item.id}">${item.university_name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Name of College<span
											class="mandatory">*</span></label>
										<div class="select-position">
											<select name="college_name" id="college_name"
												class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getInstituteListForNcism}"
													varStatus="num">
													<option value="${item.id}" name="${item.institute_name}">${item.institute_name}</option>
												</c:forEach>
											</select>
										</div>
									</div>

								</div>




								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label for="username">Medical Qualification<span
											class="mandatory">*</span>
										</label> <input type="text" name="medical_qua" id="medical_qua"
											maxlength="10" class="form-control" aria-required="true"
											autocomplete="off" placeholder="Enter Medical qualification">
									</div>
									<div class="input-style-2 mt-3">
										<input type="hidden" id="id" name="id" value="0"
											autocomplete="off" />
									</div>
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label for="username">Medical Abbreviation<span
											class="mandatory">*</span>
										</label> <input type="text" name="medical_abbrv" id="medical_abbrv"
											maxlength="10" class="form-control" aria-required="true"
											autocomplete="off" placeholder="Enter Medical qualification">
									</div>
									<div class="input-style-2 mt-3">
										<input type="hidden" id="id" name="id" value="0"
											autocomplete="off" />
									</div>
								</div>


								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label for="username">Sequence Code<span
											class="mandatory">*</span>
										</label> <input type="text" name="sequence_code" id="sequence_code"
											class="form-control" placeholder="Enter Medical abbrv">
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label for="username">Validity Period<span
											class="mandatory">*</span>
										</label> <input type="text" name="velidity_period"
											id="velidity_period" maxlength="10"
											onclick="clickclear(this, 'DD/MM/YYYY')"
											class="form-control-sm form-control effect-9 "
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');"
											aria-required="true" autocomplete="off" value="DD/MM/YYYY">
									</div>
								</div>




								<input type="hidden" id="id" name="id" class="form-control"
									value="0" autocomplete="off">

								<ul class="buttons-group mainbtn">
									<li><a href="" id="btn-reload"
										class="main-btn secondary-btn btn-hover btn-iconic-icon"
										type="button"><i class="lni lni-search-alt"></i>Search
											Details</a></li>
									<li><input type="button" id="btn_savedraftug"
										class="main-btn info-btn btn-hover" value="Save as Draft"
										onclick="return form_35_section_all_state_ug();"></li>
									<li><input type="button" id="btn_save"
										class="main-btn success-btn  btn-hover"
										value="Submit for approval"
										onclick="Submit_Approval_Section_35_State();"></li>
									<li><a href="Deg_rec_WithinIndia_Url"
										class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
								</ul>
							</div>
						</div>
						<!-- end card -->
					</form>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="card-style mb-30">
					
					<div class="table-wrapper custom-datatable-p">
						<table class="table" id="search_system">
							<thead>
								<tr>
									<th><h6>Ser No.</h6></th>
									<th><h6>File No.</h6></th>
									<th><h6>File Date</h6></th>
									<th><h6>State</h6></th>
									<th><h6>Name Of University.</h6></th>
									<th><h6>Name Of College</h6></th>
									<th><h6>Medical Qualification</h6></th>
									<th><h6>Medical Abbreviation</h6></th>
									<th><h6>Sequence Code</h6></th>
									<th><h6>Validity Period</h6></th>
									<th><h6>View Detail</h6></th>
								</tr>
								<!-- end table row-->
							</thead>
							<tbody class="custom-datatablepra">
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>

<!-- 				<div class="row"> -->
<!-- 					<div class="col-12"> -->
<!-- 						<div class="card-style mb-30"> -->
<!-- 							<div class="table-wrapper table-responsive custom-datatable-p"> -->
<!-- 								<table class="table" id="search_system"> -->
<!-- 									<thead> -->
<!-- 										<tr> -->
<!-- 											<th><h6>Sr No</h6></th> -->
<!-- 											<th><h6>Name of applicant</h6></th> -->
<!-- 											<th><h6>Country</h6></th> -->
<!-- 											<th><h6>Name of University</h6></th> -->
<!-- 											<th><h6>Name of College</h6></th> -->
<!-- 											<th><h6>Abbreviation</h6></th> -->
<!-- 											<th><h6>Name of Recognized Medical Course</h6></th> -->
<!-- 											<th><h6>Validity Period</h6></th> -->
<!-- 											<th><h6>Digital Code</h6></th> -->
<!-- 											<th><h6>Action</h6></th> -->


<!-- 										</tr> -->
<!-- 																end table row -->
<!-- 									</thead> -->
<!-- 									<tbody> -->
<!-- 									</tbody> -->
<!-- 								</table> -->
<!-- 												end table -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 								end card -->
<!-- 					</div> -->
<!-- 						end col
		
<!-- 		<!-- 		</div> --> 
<!-- 	</div> -->
</section>
<c:url value="getSearch_University" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Uni_details1">
	<input type="hidden" name="country_name1" id="country_name1" value="0" />
	<input type="hidden" name="university_name1" id="university_name1"
		value="0" />
	<input type="hidden" name="college_name1" id="college_name1" />
	<input type="hidden" name="abbreviation1" id="abbreviation1" value="0" />
	<input type="hidden" name="medical_course_name1"
		id="medical_course_name1" value="0" />
	<input type="hidden" name="validity_period1" id="validity_period1"
		value="0" />
	<input type="hidden" name="adigital_code1" id="digital_code1" value="0" />
</form:form>



<c:url value="degree_recognition_list_delete" var="deleteUrl" />
<form:form action="${degree_recognition_list_delete}" method="post"
	id="deleteForm" name="deleteForm" modelAttribute="id33">
	<input type="hidden" name="id33" id="id33" value="0" />
</form:form>

<c:url value="Systemreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>
<script nonce='r02122i021210p215a12455l12411' type="text/javascript">

function setTimeLoadForTable2(){
	document.querySelectorAll('.ADDMigratedTo').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdMig'+val).value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				viewData2(hid);
			} else {
				return false;
			}
		})
	});
}

// function setTimeLoadForTable(){
	
// 	document.getElementById('btn-save').onclick = function() {
// 		return Validation();
// 	};
// 	document.querySelectorAll('.editOnclick').forEach((items, index) => {
// 		items.addEventListener('click', event => {
			
// 			var val=parseInt(index)+1;
// 			var ap = document.getElementById('apIdAGE'+val).value;	
// 			var app = document.getElementById('appID'+val).value;
// 			var con = document.getElementById('conID'+val).value;
// 			var uni = document.getElementById('uniID'+val).value;
// 			var clg = document.getElementById('clgID'+val).value;
// 			var abb = document.getElementById('abbID'+val).value;
// 			var qua = document.getElementById('quaID'+val).value;
// 			var vp = document.getElementById('vpID'+val).value;
// 			var dc = document.getElementById('dcId'+val).value;
			
// // 			var vpf = vp.substring(8,10) +"/"+vp.substring(5,7)+"/"+vp.substring(0,4);
			
// 			if (confirm('Are You Sure You Want to Edit Detail ?')) {
// 				EditData(ap,app,con,uni,clg,abb,qua,vp,dc);
// 			} else {
// 				return false;
// 			}
// 		})
// 	});
// }

	function viewData2(id) {
	 $.post('getviewdatasectionallstate35?'+key+"="+value, {
		 id:id
		}).done(function(j) {
// 			alert(j);
// 			    var options = $("#headData").append(options);
			    $("#h_id").val(j[0].id);
				$("span#file_no").text(j[0].file_no);
				$("span#file_date").text(j[0].file_date);
				$("span#all_state").text(j[0].all_state);
				$("span#all_university_name").text(j[0].all_university_name);
				$("span#college_name").text(j[0].college_name);
				$("span#medical_qua").text(j[0].medical_qua);
				$("span#medical_abbrv").text(j[0].medical_abbrv);
				$("span#sequence_code").text(j[0].sequence_code);
				$("span#velidity_period").text(j[0].velidity_period);
				
		});
}

function data(tablename){
	 if(tablename=="search_system"){
		 jsondata = [];
			var table = $('#' + tablename).DataTable();
			var info = table.page.info();
			var pageLength = info.length;
			var startPage = info.start;
			var endPage = info.end;
			var Search = table.search();
			var order = table.order();
			var orderColunm = $(table.column(order[0][0]).header()).html()
					.toLowerCase();
			var orderType = order[0][1];
			
			var file_no = $("#file_no").val();
			var file_no = $("#file_date").val();
			var all_state = $("#all_state").val();
			var all_university_name = $("#all_university_name").val();
			var college_name = $("#college_name").val();
			var medical_qua = $("#medical_qua").val();
			var medical_abbrv = $("#medical_abbrv").val();
			var sequence_code = $("#sequence_code").val();
			var velidity_period = $("#velidity_period").val();
			
			$.post("getFilter_section_35_all_state_list?" + key + "=" + value, {
				
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				id:0
			},
			
				      function(j) {
				    	  console.log(j)
							for (var i = 0; i < j.length; i++) {
								jsondata.push([j[i].ser,j[i].file_no,j[i].file_no, j[i].all_state, j[i].all_university_name,j[i].college_name,j[i].medical_qua,j[i].medical_abbrv,j[i].sequence_code,j[i].velidity_period,j[i].action]);
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_section_35_all_state_Count?" + key + "=" + value, {
				id:0
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
			setTimeout(setTimeLoadForTable2, 1000);	 
	}
}

	


function Validation() {
	
	if ($("#applicant_name").val() =="" ) {
		alert("Please Enter Applicant Name");
		$("input#applicant_name").focus();
		return false;
	}
		if ($("#country_id").val() =="0" ) {
			alert("Please Select Country Name");
			$("select#country_id").focus();
			return false;
		}
		if ($("#university_id").val() =="0" ) {
			alert("Please Enter University Name");
			$("select#university_id").focus();
			return false;
		}
		if ($("#college_id").val() =="0" ) {
			alert("Please Enter College Name.");
			$("select#college_id").focus();
			return false;
		}
		if ($("#abbreviation").val() =="" ) {
			alert("Please Enter Abbreviation");
			$("input#abbreviation").focus();
			return false;
		}
		if ($("#qualification").val() =="0" ) {
			alert("Please Select Qualification");
			$("select#qualification").focus();
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
</script>

<script type="text/javascript">
$(document).ready(function() {
	
	datepicketDate('velidity_period');	
	datepicketDate('file_date');	
	mockjax1('search_system');
	table = dataTable('search_system');
	$('#btn-reload').on('click', function(){
    	table.ajax.reload();
    });
});





///save start//////

function form_35_section_all_state_ug() {
	
	var formvalues = $("#form_section_35_all_state").serialize();
	var form_section_35_state_id = $('#form_section_35_state_id').val();
	$.post('form35_section_all_state_action?' + key + "=" + value, formvalues,
			function(data) {
				if (data.error == null) {
					if (data.form_section_35_state_id != null) {
						$('#form_section_35_state_id').val(data.form_section_35_state_id);
						alert(data.saved);
					} else {
						alert(data.updated);
					}
				} else {
					alert(data.error)
				}
			}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

///  FINAL SUBMIT UG  //////////////////

 function Submit_Approval_Section_35_State() {

// 	if ($("input#inst_coures_name").val().trim() == "") {
// 		alert("Please Enter Institute Coducting Courses Name.");
// 		$("input#inst_coures_name").focus();
// 		return false;
// 	} else if ($("input#aprrove_cours_name").val().trim() == "") {
// 		alert("Please Enter Approved Courses Name");
// 		$("input#aprrove_cours_name").focus();
// 		return false;
// 	} else if ($("input#uni_name").val().trim() == "") {
// 		alert("Please Enter Affiliating University Name");
// 		$("input#uni_name").focus();
// 		return false;
// 	} else if ($("select#country_id").val().trim() == "0") {
// 		alert("Please Enter Country");
// 		$("select#country_id").focus();
// 		return false;
// 	} else if ($("input#state").val().trim() == "") {
// 		alert("Please Enter State");
// 		$("input#state").focus();
// 		return false;
// 	} else if ($("input#district").val().trim() == "") {
// 		alert("Please Enter District.");
// 		$("input#district").focus();
// 		return false;
// 	} else if ($("input#city").val().trim() == "") {
// 		alert("Please Enter City");
// 		$("input#city").focus();
// 		return false;
// 	} else if ($("input#uni_address").val().trim() == "") {
// 		alert("Please Enter Address Of University");
// 		$("input#uni_address").focus();
// 		return false;
// 	} else if ($("input#reg_email_id").val().trim() == "") {
// 		alert("Please Enter Registered Email-ID");
// 		$("input#reg_email_id").focus();
// 		return false;
// 	} else if ($("input#con_per_name").val().trim() == "") {
// 		alert("Please Enter Contact Person Name");
// 		$("input#con_per_name").focus();
// 		return false;
// 	} else if ($("select#con_per_desg").val().trim() == "0") {
// 		alert("Please Select Contact Person Designation");
// 		$("select#con_per_desg").focus();
// 		return false;
// 	} else if ($("input#con_per_mob_no").val().trim() == "") {
// 		alert("Please Enter Contact Person Mobile No.");
// 		$("input#con_per_mob_no").focus();
// 		return false;
// 	} else if ($("input#con_per_email_id").val().trim() == "") {
// 		alert("Please Enter Contact Person Email-ID");
// 		$("input#con_per_email_id").focus();
// 		return false;
// 	} else if ($("select#madical_stream").val().trim() == "0") {
// 		alert("Please Select Madical Stream");
// 		$("select#madical_stream").focus();
// 		return false;
// 	} else if ($("input#nomlat_degree").val().trim() == "") {
// 		alert("Please Enter Nomenclature Of Degree");
// 		$("input#nomlat_degree").focus();
// 		return false;
// 	} else if ($("input#abbre_degree").val().trim() == "") {
// 		alert("Please Enter Abbreviation Of Degree");
// 		$("input#abbre_degree").focus();
// 		return false;
// 	} else if ($("input#y_fir_bat").val().trim() == "") {
// 		alert("Please Enter Year Of Admission Of First Batch");
// 		$("input#y_fir_bat").focus();
// 		return false;
// 	} else if ($("input#m_fir_bat").val().trim() == "") {
// 		alert("Please Enter Month Of Admission Of First Batch");
// 		$("input#m_fir_bat").focus();
// 		return false;
// 	} else if ($("input#y_fir_bat_std_award").val().trim() == "") {
// 		alert("Please Enter Year Of Awarded Degree To First Student Of First Batch");
// 		$("input#y_fir_bat_std_award").focus();
// 		return false;
// 	} else if ($("input#m_fir_bat_std_award").val().trim() == "") {
// 		alert("Please Enter MonthOf Awarded DegreeToFirst Student Of FirstBatch");
// 		$("input#m_fir_bat_std_award").focus();
// 		return false;
// 	}


 					 
	if ($('#form_section_35_state_id').val() == 0) {
		alert("Please Saved your detail first");
		return false;
	}
	$.post("Submit_Approval_Data_Section_35_State_?" + key + "=" + value,
			function(data) {
				if (data.error == null) {
					if (data.saved != null) {
						$('#status').val(data.status);
						// alert(data.saved)
						alert("Data subbmited successfully");
						location.reload();
					}
				} else {
					alert(data.error)
				}
			}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}
</script>





