<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script  src="js/Calender/datePicketValidation.js"></script>

<section class="dashboard-page">
	<div class="container-fluid" id="page-top">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>Student Details</h2>
						<label id="ayush_id"> </label>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Student Details</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12">
					
					<div class="card-style mb-30">
						<!-- row -->
						<div class="row">
							<div class="col-md-12">
								<form:form  id="e_form_student"  
									action="personal_details_Action" method='POST'
									commandName="personal_details_CMD"
									enctype="multipart/form-data">
									<section>
															<div class="row">
																<div class="tables-wrapper">
																	<div class="row">
																		<div class="col-lg-12">
																			<div
																				class="table-wrapper table-responsive custom-table">
																				<table class="table" id="family_table">
<!-- 																				id="addNameOfMed" -->
																					<thead>
																						<tr>
																							<th><h6>Ser No</th>
																							<th><h6>Name of the Institute<strong class="mandatory">*
																							</strong></h6></th>
																							<th><h6>Institute ID<strong class="mandatory">*
																							</strong></h6></th>
																							<th><h6>State<strong class="mandatory">*
																							</strong></h6></th>
																							<th><h6>Candidate's Name<strong class="mandatory">*
																							</strong></h6></th>
																							<th><h6>Mother's Name<strong
																								class="mandatory">* </strong></h6></th>
																							<th><h6>Father's Name<strong
																								class="mandatory">* </strong></h6></th>
																								<th><h6>Email ID<strong class="mandatory">*</strong>
																							</h6></th>
																							<th><h6>DOB<strong class="mandatory">*</strong>
																							</h6></th>
																							<th><h6>NEET Application No<strong class="mandatory">*</strong></h6></th>
																							<th><h6>NEET Roll No<strong class="mandatory">*</strong></h6></th>
																							
																							<th><h6>ALL INDIA NEET Rank<strong class="mandatory">*</strong></h6></th>
																							
																							<th><h6>NEET Percentile<strong class="mandatory">*</strong></h6></th>
																							
																							<th><h6>Marks Obtained<strong class="mandatory">*</strong></h6></th>
																							
																							<th><h6>Quota<strong class="mandatory">*</strong></h6></th>
																							
																							<th><h6>Counselling Authority<strong class="mandatory">*</strong></h6></th>
																							
																							<th><h6>Category<strong class="mandatory">*</strong></h6></th>
																							
																							<th>Action</th>
																						</tr>
																						<!-- end table row-->
																					</thead>
																<tbody id="family_sibtbody">
																	<!-- 																					id="att_TbbodyNameMed" -->
																	<tr id="tr_sibling1">
																		<!-- 																						id="tr_id_attNameMed" -->
																		<td class="min-width">
																			<div class="lead">
																				<div class="lead-text">
																					<p>1</p>
																				</div>
																			</div>
																		</td>

																		<td>
																			<div class="select-style-1">
																				<div class="select-position">
																					<select class="form-control form-control-lg" name="name_of_institute1" id="name_of_institute1">
																					<option value="0">-- Select --</option>
 																						<c:forEach var="item" items="${getapp_instituteNameList}" 
 																							varStatus="num"> 
 																							<option value="${item[0]}" name="${item[1]}">${item[1]}</option>  
 																						</c:forEach> 
																					</select>
																				</div>
																			</div>
																		</td>
																		
																		<td>
																			<div class="input-style-1">
																				<input type="text" id="institute_id1"
																					name="institute_id1"
																					placeholder="Enter Institute Id" maxlength="30" value="${list[0][0]}"
																					autocomplete="off">
																			</div>
																		</td>
																		
																		<td>
																			<div class="select-style-1">
																				<div class="select-position">
																					<select name="state_id1" id="state_id1">
																					<option value="0">-- Select --</option>
																						<c:forEach var="item" items="${getMedStateName}" varStatus="num"> 
																							<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
																						</c:forEach> 
																					</select>
																				</div>
																			</div>
																		</td>

																		<td>
																			<div class="input-style-1">
																				<input type="text" id="cand_name1" name="cand_name1"
																					placeholder="Enter Name of Applicant"
																					maxlength="30" autocomplete="off">
																			</div>
																		</td>

																		<td>
																			<div class="input-style-1">
																				<input type="text" id="mother_name1"
																					name="mother_name1" placeholder="Enter Mother Name"
																					maxlength="30" autocomplete="off">
																			</div>
																		</td>

																		<td>
																			<div class="input-style-1">
																				<input type="text" id="father_name1"
																					name="father_name1" placeholder="Enter Father Name"
																					maxlength="30" autocomplete="off">
																			</div>
																		</td>
																		
																		<td>
																			<div class="input-style-1">
																				<input type="text" id="email1"
																					name="email1" placeholder="Enter Email"
																					maxlength="30" autocomplete="off">
																			</div>
																		</td>


																		<td>
																			<div class="input-style-2">
																				<input type="text" id="dob1" name="dob1"
																					placeholder="DD/MM/YYYY" autocomplete="off">
																			</div>
																		</td>

																		<td>
																			<div class="input-style-1">
																				<input type="text" id="neet_application_no1"
																					name="neet_application_no1" maxlength="12"
																					autocomplete="off"
																					placeholder="Enter NEET Application No">
																			</div>
																		</td>

																		<td>
																			<div class="input-style-1">
																				<input type="text" id="neet_roll_no1"
																					name="neet_roll_no1" maxlength="10"
																					autocomplete="off" minlength="12" maxlength="12" placeholder="Enter NEET Roll No">
																			</div>
																		</td>

																		<td>
																			<div class="input-style-1">
																				<input type="text" id="neet_rank1" name="neet_rank1" minlength="7"
																					maxlength="7" class="form-control"
																					autocomplete="off"
																					placeholder="Enter NEET All India Rank">
																			</div>
																		</td>

																		<td>
																			<div class="input-style-1">
																				<input type="text" id="neet_percentile1"
																					name="neet_percentile1" maxlength="5" minlength="5"
																					autocomplete="off"
																					placeholder="Enter NEET Percentile">
																			</div>
																		</td>

																		<td>
																			<div class="input-style-1">
																				<input type="text" id="neet_marks1"
																					name="neet_marks1" maxlength="3" minlength="3" autocomplete="off"
																					placeholder="Enter NEET Marks">
																			</div>
																		</td>


																		<td>
																			<div class="select-style-1">
																				<div class="select-position">
																					<select name="quota_id1" id="quota_id1">
																						<option value="0">-- Select --</option>
																					
																						<c:forEach var="item" items="${getQuotaList}"
																							varStatus="num"> 
																							<option value="${item.id}" name="${item.quota}">${item.quota}</option> 
																						</c:forEach> 
																					</select>
																				</div>
																			</div>
																		</td>

																		<td>
																			<div class="select-style-1">
																				<div class="select-position">
																					<select name="counselling_authority1"
																						id="counselling_authority1">
																						<option value="0">-- Select --</option>
																					
																						<c:forEach var="item" items="${getCounselingAuthoList}"
																							varStatus="num"> 
																							<option value="${item.id}" name="${item.counseling_authority}">${item.counseling_authority}</option> 
																						</c:forEach> 
																					</select>
																				</div>
																			</div>
																		</td>


																		<td>
																			<div class="select-style-1">
																				<div class="select-position">
																					<select name="category_id1" id="category_id1">
																						<option value="0">-- Select --</option>
																						<c:forEach var="item" items="${getcategorylist}"
																							varStatus="num">
																							<option value="${item.id}"
																								name="${item.category}">${item.category}</option>
																						</c:forEach>
																					</select>
																				</div>
																			</div>
																		</td>



																		<td>
																			<div class="action">
																				<ul class="buttons-group mainbtn daobtn">

																					<li><a
																						class="main-btn info-btn-outline btn-hover btn-sm"
																						value="Save" title="SAVE" id="family_save1"><i
																							class="lni lni-checkmark"></i></a></li>
																					<li><a
																						class="main-btn success-btn-outline btn-hover btn-sm custom-d-none"
																						value="ADD" title="ADD"
																						id="Migrated_Students_add1"><i
																							class="lni lni-plus"></i></a></li>
																					<li><a
																						class="main-btn danger-btn-outline btn-hover btn-sm  custom-d-none"
																						value="Delete" title="Delete"
																						id="Migrated_Students_remove1"><i
																							class="lni lni-trash-can"></i></a></li>
																				</ul>
																				<!-- 																								style="display: none;" -->
																			</div>
																		</td>
																	</tr>
																	<!-- end table row -->
																</tbody>
															</table>
																				<input type="hidden" id="family_saveh1" name="family_saveh1"
																					class="form-control autocomplete" value="1">
<!-- 																				<input type="hidden" id="count_hidden_att_name_med" -->
<!-- 																					name="count_hidden_att_name_med" -->
<!-- 																					class="form-control autocomplete" value="1"> -->
<!-- 																				<input type="hidden" id="count_hidden_att_name_med1" -->
<!-- 																					name="count_hidden_att_name_med1" -->
<!-- 																					class="form-control autocomplete" value="1"> -->
																				<!-- end table -->
																				<input type="hidden" id="form_d_id" name="form_d_id" value="0" class="form-control autocomplete" autocomplete="off">
																				<input type="hidden" id="sib_ch_id1" name="sib_ch_id1" value="0" class="form-control autocomplete" autocomplete="off">
																				<input type="hidden" id="p_id_quali" name="p_id_quali" value="0" class="form-control autocomplete" autocomplete="off">
																				<input type="hidden" id="hid_quali1" name="hid_quali1" value="0" class="form-control autocomplete" autocomplete="off">
																			</div>
																			<!-- end card -->
																		</div>
																		<!-- end col -->
																	</div>
																	<!-- end row -->
																</div>

	<input type="hidden" id="indno" name="indno" value="0" class="form-control autocomplete" autocomplete="off">
															</div>
														
									</section>
<!-- 									<ul class="buttons-group mainbtn"> -->
<!-- 										<li><input type="submit" id="save_btn" -->
<!-- 											class="main-btn info-btn btn-hover" value="Save"> -->
<!-- 											</li> -->
<!-- 									</ul> -->
									<input type="hidden" name="e_id" id="e_id" value="0" />
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>







<script  nonce="${cspNonce}" type="text/javascript" >

$(document).ready(function() {
	
	datepicketDate('dob1');
	
	if('${list}' != "[]"){
		
		 $("#institute_id1").val('${list[0][0]}');
		 $("#name_of_institute1").val('${list[0][3]}');
		 $("#name_of_institute1").change();
		 $("#state_id1").val('${list[0][4]}');
		 $("#state_id1").attr("readonly","readonly");
		 $("#institute_id1").attr("readonly","readonly");
		 $("#name_of_institute1").attr("readonly","readonly");
		
	}
	
});
    
// $("#state_id1").attr("readonly","readonly");
    
function formOpenNameMed(R){
	
	 $("#family_table").show();
		 
		 att=0;
		 $("#Migrated_Students_add"+R).hide();
		 $("#Migrated_Students_remove"+R).hide();
		 att=parseInt($("#family_saveh1").val())+1;
		 att= parseInt(R)+1;
		
		 if(att < 51){
			 
			 $("input#family_saveh1").val(att);//current serial No
			 $("table#family_table").append(		'<tr id="tr_sibling1'+att+'">'
				+'<td class="min-width">'
				+'	<div class="lead">'
				+'	<div class="lead-text">'
				+'		<p>'+att+'</p>'
				+'	</div>'
				+'</div>'
				+'</td>'
				+'<td>'
				+'<div class="select-style-1">'
				+'<div class="select-position">'
				+'<select class="form-control form-control-lg" name="name_of_institute'+att+'" id="name_of_institute'+att+'">'
				+'	<option value="0">-- Select --</option><c:forEach var="item" items="${getapp_instituteNameList}" varStatus="num">'
				+'		<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select>'
				+'</div></div>'
				+'</td>'
				+'<td>'
				+'<div class="input-style-1">'
				+'	<input type="text" id="institute_id'+att+'"'
				+'		name="institute_id'+att+'"'
				+'		placeholder="Enter Institute Id" maxlength="30"'
				+'		autocomplete="off">'
				+'</div>'
				+'</td>'
				+'<td>'
				+'<div class="select-style-1">'
				+'	<div class="select-position">'
				+'		<select name="state_id'+att+'" id="state_id'+att+'">'
				+'			<option value="0">-- Select --</option><c:forEach var="item" items="${getMedStateName}"	varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option>	</c:forEach></select>'
				+'	</div>'
				+'</div>'
				+'</td>'

				+'<td>'
				+'<div class="input-style-1">'
				+'	<input type="text" id="cand_name'+att+'" name="cand_name'+att+'"'
				+'		placeholder="Enter Name of Applicant"'
				+'		maxlength="30" autocomplete="off">'
				+'</div>'
				+'</td>'
				+'<td>'
				+'<div class="input-style-1">'
				+'	<input type="text" id="mother_name'+att+'"'
				+'		name="mother_name'+att+'" placeholder="Enter Mother Name"'
				+'		maxlength="30" autocomplete="off">'
				+'</div>'
				+'</td>'
				+'<td>'
				+'<div class="input-style-1">'
				+'	<input type="text" id="father_name'+att+'"'
				+'		name="father_name'+att+'" placeholder="Enter Father Name"'
				+'		maxlength="30" autocomplete="off">'
				+'</div>'
				+'</td>'
				+'<td>'
				+'<div class="input-style-1">'
				+'	<input type="text" id="email'+att+'"'
				+'		name="email'+att+'" placeholder="Enter Email"'
				+'		maxlength="30" autocomplete="off">'
				+'</div>'
				+'</td>'
				+'<td>'
				+'<div class="input-style-2">'
				+'	<input type="text" id="dob'+att+'" name="dob'+att+'"'
				+'		placeholder="DD/MM/YYYY" autocomplete="off">'
				+'</div>'
				+'</td>'
				+'<td>'
				+'					<div class="input-style-1">'
				+'	<input type="text" id="neet_application_no'+att+'"'
				+'		name="neet_application_no'+att+'" maxlength="12"'
				+'		autocomplete="off"'
				+'		placeholder="Enter NEET Application No">'
				+'</div>'
				+'</td>'
				+'<td>'
				+'<div class="input-style-1">'
				+'	<input type="text" id="neet_roll_no'+att+'"'
				+'		name="neet_roll_no'+att+'" maxlength="10"'
				+'		autocomplete="off" placeholder="Enter NEET Roll No">'
				+'</div>'
				+'</td>'
				+'<td>'
				+'<div class="input-style-1">'
				+'	<input type="text" id="neet_rank'+att+'" name="neet_rank'+att+'"'
				+'		maxlength="7" class="form-control"'
				+'		autocomplete="off"'
				+'		placeholder="Enter NEET All India Rank">'
				+'</div>'
				+'</td>'
				+'<td>'
				+'<div class="input-style-1">'
				+'	<input type="text" id="neet_percentile'+att+'"'
				+'		name="neet_percentile'+att+'" maxlength="5"'
				+'		autocomplete="off"'
				+'		placeholder="Enter NEET Percentile">'
				+'</div>'
				+'</td>'
				+'<td>'
				+'<div class="input-style-1">'
				+'	<input type="text" id="neet_marks'+att+'"'
				+'		name="neet_marks'+att+'" maxlength="3" autocomplete="off"'
				+'		placeholder="Enter NEET Marks">'
				+'</div>'
				+'</td>'
				+'<td>'
				+'<div class="select-style-1">'
				+'	<div class="select-position">'
				+'		<select name="quota_id'+att+'" id="quota_id'+att+'">'
				+'			<option value="0">-- Select --</option><c:forEach var="item" items="${getQuotaList}"	varStatus="num"><option value="${item.id}" name="${item.quota}">${item.quota}</option>	</c:forEach></select>'
				+'	</div>'
				+'</div>'
				+'</td>'
				+'<td>'
				+'<div class="select-style-1">'
				+'	<div class="select-position">'
				+'		<select name="counselling_authority'+att+'"'
				+'			id="counselling_authority'+att+'">'
				+'			<option value="0">-- Select --</option><c:forEach var="item" items="${getCounselingAuthoList}"	varStatus="num"><option value="${item.id}" name="${item.counseling_authority}">${item.counseling_authority}</option>	</c:forEach></select>'
				+'	</div>'
				+'</div>'
				+'</td>'
				+'<td>'
				+'<div class="select-style-1">'
				+'	<div class="select-position">'
				+'		<select name="category_id'+att+'" id="category_id'+att+'">'
				+'			<option value="0">-- Select --</option>	<c:forEach var="item" items="${getcategorylist}" varStatus="num"><option value="${item.id}"	name="${item.category}">${item.category}</option></c:forEach></select>'
				+'	</div>'
				+'</div>'
				+'</td>'
				+'<td>'
				+'<div class="action">'
				+'	<ul class="buttons-group mainbtn daobtn">'
				+'		<li><a'
				+'			class="main-btn info-btn-outline btn-hover btn-sm"'
				+'			value="Save" title="SAVE" id="family_save'+att+'"><i'
				+'				class="lni lni-checkmark"></i></a></li>'
				+'		<li><a'
				+'			class="main-btn success-btn-outline btn-hover btn-sm custom-d-none"'
				+'			value="ADD" title="ADD"'
				+'			id="Migrated_Students_add'+att+'"><i'
				+'				class="lni lni-plus"></i></a></li>'
				+'		<li><a'
				+'			class="main-btn danger-btn-outline btn-hover btn-sm  custom-d-none"'
				+'			value="Delete" title="Delete"'
				+'			id="Migrated_Students_remove'+att+'"><i'
				+'				class="lni lni-trash-can"></i></a></li>'
				+'	</ul>'
				+'</div>'
				+'</td>'
				+'</tr>');
			  addOnclick(att);
		 }else{
					alert("Please Enter max 50 Quantity");
					 if ( att == 51){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
	}
	
    
document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('family_save1').onclick = function() {
		return Save_As_Draft_Quali(1);
	};
 	document.getElementById('Migrated_Students_add1').onclick = function() {
		return formOpenNameMed(1);
	};
	document.getElementById('Migrated_Students_remove1').onclick = function() {
		return formopen_re_NameMed(1);
	};	
	document.getElementById('cand_name1').onkeypress = function () {
		return onlyAlphabetsStringSpace(event, this);
	 };
	document.getElementById('mother_name1').onkeypress = function () {
		return onlyAlphabetsStringSpace(event, this);
	 };
		document.getElementById('father_name1').onkeypress = function () {
		return onlyAlphabetsStringSpace(event, this);
	 };
	document.getElementById('name_of_institute1').onchange = function () {
		getInsCodeListbyInsName(1);
	};
	document.getElementById('neet_application_no1').onkeypress = function () {
		return isNumberKey0to9(event, this);
	 };
	document.getElementById('neet_roll_no1').onkeypress = function () {
		return isNumberKey0to9(event, this);
	 };
	document.getElementById('neet_rank1').onkeypress = function () {
		return isNumberKey0to9(event, this);
	 };
	document.getElementById('neet_percentile1').onkeypress = function () {
		return isNumberKey0to9(event, this);
	 };
	document.getElementById('neet_marks1').onkeypress = function () {
		return isNumberKey0to9(event, this);
	 };
	
});


function addOnclick(att){
	
	if('${list}' != "[]"){
		
		 $("#institute_id"+att).val('${list[0][0]}');
		 $("#name_of_institute"+att).val('${list[0][3]}');
		 $("#name_of_institute"+att).change();
		 $("#state_id"+att).val('${list[0][4]}');
		 $("#state_id"+att).attr("readonly","readonly");
		 $("#institute_id"+att).attr("readonly","readonly");
		 $("#name_of_institute"+att).attr("readonly","readonly");
		
	}
	
	datepicketDate('dob'+att);
	
	document.getElementById('Migrated_Students_add'+att).onclick = function() {
		formOpenNameMed(att);
	};
	document.getElementById('Migrated_Students_remove'+att).onclick = function() {
		return formopen_re_NameMed(att);
	};
	document.getElementById('family_save'+att).onclick = function() {
		Save_As_Draft_Quali(att);
	};
	
	document.getElementById('name_of_institute'+att).onchange = function () {
		getInsCodeListbyInsName(att);
	};
	document.getElementById('cand_name'+att).onkeypress = function () {
		return onlyAlphabetsStringSpace(event, this);
	 };
	document.getElementById('mother_name'+att).onkeypress = function () {
		return onlyAlphabetsStringSpace(event, this);
	 };
		document.getElementById('father_name'+att).onkeypress = function () {
		return onlyAlphabetsStringSpace(event, this);
	 };
 	document.getElementById('neet_application_no'+att).onkeypress = function () {
		return isNumberKey0to9(event, this);
	 };
	document.getElementById('neet_roll_no'+att).onkeypress = function () {
		return isNumberKey0to9(event, this);
	 };
		document.getElementById('neet_rank'+att).onkeypress = function () {
		return isNumberKey0to9(event, this);
	 };
	document.getElementById('neet_percentile'+att).onkeypress = function () {
		return isNumberKey0to9(event, this);
	 };
	document.getElementById('neet_marks'+att).onkeypress = function () {
		return isNumberKey0to9(event, this);
	 };

	$('.select2').select2({
    	containerCssClass: "wrap"
	});
	
}

    
    
    
function formopen_re_NameMed(R){

//		var qualtification_idhid2= $("input#qualtification_id"+R).val();
// 	var result = confirm("Are you sure you want to delete?");
// 	var p_id = $("input#p_id").val();
// 	var hid_quali1 = $("input#hid_quali"+att).val();
// 	$.post(
// 			"delete_child_quali?" + key + "=" + value,
// 			{
// 				p_id : p_id,
// 				hid_quali1 : hid_quali1
// 			},
// 			function(j) {
// 				alert(j);
// 			});
	
	 $("tr#tr_sibling1"+R).remove();
	 att = att-1;
	 $("input#count_hidden_att_name_med").val(att);
	 $("#Migrated_Students_add"+att).show();
	 $("#Migrated_Students_remove"+att).show();	
	 
}    
    
    

    
function Save_As_Draft_Quali(ps) {
// 	alert(ps);
	$.ajaxSetup({
	    async: false
	});	
	
	var type_of_degree_id = $('#institute_id' + ps).val();
	var type_of_degree_id = $('#name_of_institute' + ps).val();
	
	var res = CheckNullorBlank('name_of_institute'+ps);
	if (res !== "true") {
		alert(res + "Name of Institute");
		$('#name_of_institute'+ps).focus();
		return false;
	}
	
	var res = CheckNullorBlank('institute_id'+ps);
	if (res !== "true") {
		alert(res + "Institute Id");
		$('#institute_id'+ps).focus();
		return false;
	}
	
	var res = CheckNullorBlank('state_id'+ps);
	if (res !== "true") {
		alert(res + "state_id");
		$('#state_id'+ps).focus();
		return false;
	}
	
	var res = CheckNullorBlank('cand_name'+ps);
	if (res !== "true") {
		alert(res + "cand_name");
		$('#cand_name'+ps).focus();  
		return false;
	}
	
	var res = CheckNullorBlank('mother_name'+ps);
	if (res !== "true") {
		alert(res + "mother_name");
		$('#mother_name'+ps).focus();  
		return false;
	}
	
	var res = CheckNullorBlank('father_name'+ps);
	if (res !== "true") {
		alert(res + "father_name");
		$('#father_name'+ps).focus();  
		return false;
	}
	
	var res = CheckNullorBlank('email'+ps);
	if (res !== "true") {
		alert(res + "email");
		$('#email'+ps).focus();  
		return false;
	}
	
	var res = CheckNullorBlank('dob'+ps);
	if (res !== "true") {
		alert(res + "dob");
		$('#dob'+ps).focus();  
		return false;
	}
	
	var res = CheckNullorBlank('neet_application_no'+ps);
	if (res !== "true") {
		alert(res + "neet_application_no");
		$('#neet_application_no'+ps).focus();  
		return false;
	}
	
	var res = CheckNullorBlank('neet_roll_no'+ps);
	if (res !== "true") {
		alert(res + "neet_roll_no");
		$('#neet_roll_no'+ps).focus();  
		return false;
	}
	
	var res = CheckNullorBlank('neet_rank'+ps);
	if (res !== "true") {
		alert(res + "neet_rank");
		$('#neet_rank'+ps).focus();  
		return false;
	}
	
	var res = CheckNullorBlank('neet_percentile'+ps);
	if (res !== "true") {
		alert(res + "neet_percentile");
		$('#neet_percentile'+ps).focus();
		return false;
	}
	
	var res = CheckNullorBlank('neet_marks'+ps);
	if (res !== "true") {
		alert(res + "neet_marks");
		$('#neet_marks'+ps).focus();  
		return false;
	}
	
	var res = CheckNullorBlank('quota_id'+ps);
	if (res !== "true") {
		alert(res + "quota_id");
		$('#quota_id'+ps).focus();  
		return false;
	}
	
	var res = CheckNullorBlank('counselling_authority'+ps);
	if (res !== "true") {
		alert(res + "counselling_authority");
		$('#counselling_authority'+ps).focus();  
		return false;
	}
	
	var res = CheckNullorBlank('category_id'+ps);
	if (res !== "true") {
		alert(res + "category_id");
		$('#category_id'+ps).focus();  
		return false;
	}
	
	$("#indno").val(ps);
	var form_data = new FormData(document.getElementById("e_form_student"));
	$.ajax({
		url: 'e_form_nch_student_Action?_csrf='  + value,
		type: "POST",
		data: form_data,
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false
	}).done(function(data) {
		
		console.log(data.msg);
		
			 if(data.err != undefined){
				 console.log(data.err);
				
				 
// 	      		 $("#hid_quali1").val(data);
	      		 alert(data.err);
	      		$("#"+data.field+""+ps).focus(); 
	      		 
// 	      		$("#Migrated_Students_add"+ps).show(); 
// 		    	$("#Migrated_Students_remove"+ps).show(); 
	        }
	        else{
	        	alert(data.msg);
		      	$("#Migrated_Students_add"+ps).show(); 
		    	$("#Migrated_Students_remove"+ps).show(); 
	    	    
	        }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});

 }
    
// function getcourselistbydate() {
	function getInsCodeListbyInsName(index) {
		var name_of_institute = $("#name_of_institute"+index).val();
		$
				.post('getInsCodeListbyInsName_ctrl?' + key + "=" + value, {
					name_of_institute : name_of_institute
				})
				.done(
						function(j) {
							for (var i = 0; i < j.length; i++) {
								$("#institute_id"+index).val(j[0].code);
								$("#state_id"+index).val(j[0].state_id);
								
								 $("#state_id"+index).attr("readonly","readonly");
								 $("#institute_id"+index).attr("readonly","readonly");
								
							}
							
						});
	}   
    
</script>