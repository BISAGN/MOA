<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<link rel="stylesheet" href="assets/vendor/vtab&htab_with_accordion/vtab&htab_with_acco_form_style.css">


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

<section class="dashboard-page degree_recognition">
<div class="container-fluid">
	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>Degree Recognition</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
						<li class="breadcrumb-item active" aria-current="page">Degree Recognition</li>
						</ol>
					</nav>
				</div>
			</div>
			<!-- end col -->
		</div>
		<!-- end row -->
	</div>



		<div class="row">
			<div class="col-12">

				<div class="card-style mb-30">
					<div class="row">
						<div class="col-12">
						 
						 
<!-- 						 <div class="h-tab"> -->
  
<!-- <ul class="h-tab_tab-head"> -->
<!--   <li class="htab1 active"><a href="Deg_rec_WithinIndia_Url">Application For Degree Awarding Universities Within India</a></li> -->
<!--   <li class="htab2 "><a href="Deg_rec_OutsideIndia_Url">Application For Degree Awarding Universities Outside India</a></li> -->

<!-- </ul> -->
  
<!-- <div class="h-tab_container"> -->

<!--   <div id="htab1" class="h-tab_content"> -->
  
    
<!--     <div class="v-tab row m-0"> -->
  
<!-- <ul class="v-tab_tab-head col-4 col-sm-4 col-md-3 col-lg-2"> -->
<!--   <li class="active" rel="vtab1">Form-35A (UG Courses)</li> -->
<!--   <li rel="vtab2">Form-35B (PG Courses)</li> -->
<!-- </ul> -->
  
<!-- <div class="v-tab_container col-8 col-sm-8 col-md-9 col-lg-10"> -->

           <div id="vtab1" class="v-tab_content">
					
				
							<!-- Start For Section 35 2-->
							
							<div class="row">
								<div class="col-12">
									<h3 class="text-center b-top mt-3 pt-2">Format for the details of recognized medical qualification for display in NCISM website (For Section 35)</h3>
										<div class="table-wrapper table-responsive simple-table">
											<table class="table" id="search_Course">
												<thead>
													<tr>
														<th><h6>Ser No.</h6></th>
														<th><h6>State</h6></th>
														<th><h6>Name of University</h6></th>
														<th><h6>Name of College</h6></th>
														<th><h6>Medical Qualification</h6></th>
														<th><h6>Medical Abbreviation</h6></th>
														<th><h6>Sequence Code</h6></th>
														<th><h6>Validity Period</h6></th>
														<th><h6>Specimen Degree certificate submitted by University</h6></th>
														<th><h6>Copy of Notification</h6></th>
													</tr>
													<!--end table row -->
												</thead>
												<tbody id="family_sibtbody">
												<tr id="tr_sibling1">
													<td>1</td>
													<td>
														<div class="">
															<div class="input-style-2">
																<input type="text" name="pg_subject" id="pg_subject" class="form-control"
																	placeholder="Enter PG Subject.">
															</div>
														</div>
													</td>
													<td>
														<div class="">
															<div class="input-style-2">
																<input type="text" name="nomenclature_dg" id="nomenclature_dg" class="form-control"
																	placeholder="Enter PG Subject.">
															</div>
														</div>
													</td>
													<td>
														<div class="">
															<div class="input-style-2">
																<input type="text" name="pg_subject" id="pg_subject" class="form-control"
																	placeholder="Enter PG Subject.">
															</div>
														</div>
													</td>
													
													
													<td>
														<div class="">
															<div class="input-style-2">
																<input type="text" name="file_date" id="file_date" maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
																	class="form-control" style="width: 85%; display: inline;"
																	onfocus="this.style.color='#000000'"
																	onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
																	onkeyup="clickclear(this, 'DD/MM/YYYY')"
																	onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
																	aria-required="true" autocomplete="off"
																	style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">
															</div>
														</div>
													</td>
												 <td>
													<div class="">
															<div class="select-style-1">
													<div class="select-position">
														<select name="all_state" id="all_state" class="form-control">
															<option value="0">--Select--</option>
															<option value="1">Bihar</option>
															<option value="2">Chhattisgarh</option>
															<option value="3">Gujarat</option>
														</select>
													</div>
												</div>
													</div>
												</td>
													<td>
														<div class="">
															<div class="select-style-1">
													<div class="select-position">
														<select name="all_university_name" id="all_university_name" class="form-control">
															<option value="0">--Select--</option>
															<option value="1">Bihar</option>
															<option value="2">Chhattisgarh</option>
															<option value="3">Gujarat</option>
														</select>
													</div>
												</div>
													</div>
													</td>
													<td>
														<div class="">
															<div class="select-style-1">
													<div class="select-position">
														<select name="college_name" id="college_name" class="form-control">
															<option value="0">--Select--</option>
															<option value="1">Bihar</option>
															<option value="2">Chhattisgarh</option>
															<option value="3">Gujarat</option>
														</select>
													</div>
												</div>
													</div>
													</td>
													
													<td>
														<div class="">
															<div class="input-style-2">
																<input type="text" name="medical_qua" id="medical_qua"
																maxlength="10" class="form-control" aria-required="true" autocomplete="off" placeholder="Enter Medical qualification">
															</div>
														</div>
													</td>
													<td>
														<div class="">
															<div class="input-style-2">
																<input type="text" name="medical_abbrv" id="medical_abbrv"
																	class="form-control" placeholder="Enter Medical abbrv" >
															</div>
														</div>
													</td>
													<td>
														<div class="">
															<div class="input-style-2">
																<input type="text" name="sequence_code" id="sequence_code"
																	class="form-control" placeholder="Enter Sequence Code">
															</div>
														</div>
													</td>
													<td>
														<div class="">
															<div class="input-style-2">
																<input type="text" name="velidity_period" id="velidity_period"
											maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
											class="form-control-sm form-control effect-9 "
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');"
											aria-required="true" autocomplete="off" value="DD/MM/YYYY">
															</div>
														</div>
													</td>
            
						<td class="hide-action">
						<ul class="buttons-group multi-btn-group">
						<li><a class="main-btn info-btn-outline btn-hover btn-sm" value="Save" title="SAVE" id="family_from_save1" onclick="all_state_form(1);"><i class="lni lni-checkmark"></i></a></li>
						<li><a class="main-btn success-btn-outline btn-hover btn-sm" style="display: none;" value="ADD" title="ADD" id="all_state_add1" onclick="all_state_add_fn(1); "><i class="lni lni-plus"></i></a></li>
						<li><a class="main-btn danger-btn-outline btn-hover btn-sm" style="display: none;" value="Delete" title="Delete" id="all_state_remove1" onclick="all_state_remove_fn(1);"><i class="lni lni-trash-can"></i></a></li>
					</ul>
			</td>
												</tr>
											</tbody>
											</table>
											<!-- 										end table -->
										</div>
									<!-- 				end card -->
								</div>
								<!-- 		end col -->
							</div>
					<!-- End For Section 35 2-->
 </div>
  <!-- #tab2 -->
</div>
</div>
</div>
</div>
 <!-- #tab1 -->
</div></div><!-- </div></div></div></div></div> -->
</section>

<script src="assets/vendor/vtab&htab_with_accordion/vtab&htab_with_acco_form.js"></script>
<script>

$(document).ready(function() {
	
        if(window.location.href.includes("msg"))
        {
                 var url = window.location.href.split("?msg")[0];
                 window.location = url;
        }
        datepicketDate('commencement_dt');
        datepicketDate('file_date');
        datepicketDate('velidity_period');
//         datepicketDate('date_of_result_final_year');
//         datepicketDate('date_of_starting_internship');
//         datepicketDate('date_of_completion_internship');
//         datepicketDate('to_date1');
       // datepicketDate('from_date1');
       
});


function Awarded_Degree(){
	var awarded_degree = $('input:radio[name=awarded_degree]:checked').val();
	if(awarded_degree == "Yes"){
        $("div#Awarded_Degree_yes").show();
		$("div#Awarded_Degree_No").hide();
	}
	else if(awarded_degree == "No"){
		$("div#Awarded_Degree_No").show();
        $("div#Awarded_Degree_yes").hide();
	}
}

function Degree_conti(){
	var continued_degree = $('input:radio[name=continued_degree]:checked').val();
	if(continued_degree == "Yes"){
		$("div#Degree_conti_No").hide();
	}
	 if(continued_degree == "No"){
        $("div#Degree_conti_No").show();
	}
}


function form_pg_course(){
		$("div#form_b_pg").hide();
        $("div#form_pg_course").show();
}

function form_pg_diploma_course(){
	    $("div#form_pg_diploma").show();
		$("div#form_b_pg").hide();
	    $("div#form_pg_course").hide();
       
}

function form_pg_dip_pre(){
	    $("div#form_pg_diploma").hide();
		$("div#form_b_pg").hide();
	    $("div#form_pg_course").show();
}

function form_pg_pre(){
	    $("div#form_pg_diploma").hide();
		$("div#form_b_pg").show();
	    $("div#form_pg_course").hide();
}

function checkgmail(email1) {
	
		 document.getElementById("email").innerHTML="";
		if(/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
			
		}else{
			alert("Please Enter Valid Email Address");
			$("input#email").focus();
			$("input#email").val('');
			return false ;
		}
	 } 
	 
/*-------------------------START SAVE FOR FORM_A_UG_DETAILS----------------------------- */
 
function form_35_a_ug() {
    var formvalues = $("#form_a_ug_details").serialize();
           var form_a_ug_id = $('#form_a_ug_id').val();
            $.post('form35_a_ug_action?' + key + "=" + value, formvalues, function(data) {
                  if(data.error == null) {
                         if(data.form_a_ug_id != null) { 
                          $('#form_a_ug_id').val(data.form_a_ug_id);
                         alert(data.saved);
                         } else {
                         alert(data.updated);
                         }
                 }  
                 else {
                         alert(data.error)
                 }
         }).fail(function(xhr, textStatus, errorThrown) {
                 alert("fail to fetch")
         });
 }
 
 
/*------------------SINGLE SAVE ADDMORE FOR ALL STATE FORM------------------------ */

function all_state_form(ps) {
	
// 	  if($("select#name_of_inst"+ps).val() == "0") {
//           alert("Please Select Name Of Institute");
//           $("select#name_of_inst"+ps).focus();
//           return false;
// 	  }
// 	  else if($("input#student_name_to_migrated"+ps).val() == "") {
//          alert("Please Enter Student Name");
//          $("input#student_name_to_migrated"+ps).focus();
//          return false;
// 	  }
//       else if($("input#migrated_dt_to"+ps).val() == "DD/MM/YYYY" || $("input#migrated_dt_to"+ps).val() == ""  ) {
//       		alert("Please Enter Date Of Migration");
//       		$("input#migrated_dt_to"+ps).focus();
//       		return false;
//      }
//      else if($("input#professional_year_migrated"+ps).val() == "") {
//               alert("Please Enter Professional Year Migrated");
//               $("input#professional_year_migrated"+ps).focus();
//               return false;
//     }
//     else if($("input#university_enrollment_number"+ps).val() == "") {
//                alert("Please Enter University Enrollment Number");
//               $("input#university_enrollment_number"+ps).focus();
//               return false;
//     }
//     else if($("input#remarks_form_d"+ps).val() == "") {
//             alert("Please Enter Remarks");
//             $("input#remarks_form_d"+ps).focus();
//             return false;
//     }
	  
	var file_no =$('#file_no' + ps).val();
	var all_state = $('#all_state' + ps).val();
	var file_date = $("#file_date"+ ps).val();
	var all_university_name = $("#all_university_name"+ps).val();
	var college_name = $("#college_name"+ps).val();
	var medical_qua = $("#medical_qua" + ps).val();
	var medical_abbrv = $("#medical_abbrv" + ps).val();
	var sequence_code = $("#sequence_code" + ps).val();
	var velidity_period = $("#velidity_period" + ps).val();
	var all_state_id = $('#all_state_id' + ps).val();
	
	$.post('all_state_form_Add?' + key + "=" + value, {
		
		file_no: file_no,
		all_state: all_state,
		file_date: file_date,
		all_university_name: all_university_name,
		college_name: college_name,
		medical_qua:medical_qua,
		medical_abbrv: medical_abbrv,
		sequence_code: sequence_code,
		velidity_period: velidity_period,
		all_state_id :all_state_id
		
	}, function(data) {
		if(data.error == null) {
			if(data.all_state_id != null) {
				$('#all_state_id' + ps).val(data.all_state_id);
				$("#all_state_add" + ps).show();
				$("#all_state_remove" + ps).show();
				alert(data.saved);
			} else {
				alert(data.updated);
			}
		} else alert(data.error);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

/* ---------------------------------------ADD FOR SINGLE SAVE------------------------------- */

sib = 1;
sib_srno = 1;

function all_state_add_fn(q) {
		if($('#all_state_add' + q).length) {
			$("#all_state_add" + q).hide();
		}
		
	if(q != 0) sib_srno += 1;
	sib = q + 1;
	
	$("table#family_table").append('<tr id="tr_sibling' + sib + '">' + '<td class="sib_srno">' + sib_srno + '</td>' 
		+ '<td><div class="input-style-2"><input name="file_no' + sib + '" id="file_no' + sib + '"class="form-control" placeholder="Enter file no."></div></td>'
		+ '<td><div class="input-style-2"><input type="text" id="all_state'+sib+'" name="all_state'+sib+'" placeholder="Enter all_state" class="form-control" autocomplete= "off" maxlength="50"></div></td>'
		+ '<td><div class="input-style-2"><input type="text"  id="file_date'+sib+'" name="file_date'+sib+'" maxlength="10"  value="DD/MM/YYYY"  placeholder="Enter To Date" class="form-control"></div></td> '
		+ '<td><div class="select-style-1"><div class="select-position"><select name="all_university_name" id="all_university_name" class="form-control"><option value="0">--Select--</option><option value="1">Bihar</option>'
		+ '<option value="2">Chhattisgarh</option><option value="3">Gujarat</option></select></div></div></td> '
		+ '<td style="display:none;"><input type="text" id="all_state_id' + sib + '" name="all_state_id' + sib + '"   value="0" class="form-control autocomplete" autocomplete="off"></td>' 
		+ '<td><div class="select-style-1"><div class="select-position"><select name="college_name" id="college_name" class="form-control"><option value="0">--Select--</option>'
		+ '<option value="1">Bihar</option><option value="2">Chhattisgarh</option><option value="3">Gujarat</option></select><div></td>'
		+ '<td><div class="input-style-2"><input type="text" id="medical_qua'+sib+'" name="medical_qua' + sib + '" placeholder="Enter qoalification" class="form-control"></div></td>'
		+ '<td><div class="input-style-2"><input type="text" id="medical_abbrv'+sib+'" name="medical_abbrv' + sib + '" placeholder="Enter abbreviation" class="form-control"></div></td>'
		+ '<td><div class="input-style-2"><input type="text" id="sequence_code'+sib+'" name="sequence_code' + sib + '" placeholder="Enter sequence code" class="form-control"></div></td>'
		+ '<td><div class="input-style-2"><input type="text"  id="velidity_period'+sib+'" name="velidity_period'+sib+'" maxlength="10"  value="DD/MM/YYYY"  placeholder="Enter To Date" class="form-control"></div></td>'
		+ '<td class="hide-action"><ul class="buttons-group multi-btn-group"><li><a class="main-btn info-btn-outline btn-hover btn-sm" value = "SAVE" title = "SAVE" id = "family_save' + sib + '" onclick="all_state_form(' + sib + ');" ><i class="lni lni-checkmark"></i></a></li>' + '<li><a style="display:none;" class="main-btn success-btn-outline btn-hover btn-sm" value = "ADD" title = "ADD" id = "all_state_add' + sib + '" onclick="all_state_add_fn(' + sib + ');" ><i class="lni lni-plus"></i></a></li>' + '<li><a style="display:none;" class="main-btn danger-btn-outline btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "all_state_remove' + sib + '" onclick="all_state_remove_fn(' + sib + ');"><i class="lni lni-trash-can"></i></a></li>' + '</ul></td></tr>');
	
	datepicketDate('file_date' + sib);
	datepicketDate('velidity_period' + sib);
}

/* -----------------------------------DELETE------------------------------------ */

function all_state_remove_fn(R) {
	var rc = confirm("Are You Sure! You Want To Delete");
	if(rc) {
		var all_state_id = $('#all_state_id' + R).val();
		$.post('all_state_delete_action?' + key + "=" + value, {
			all_state_id: all_state_id
			
		}, function(data) {
			if(data == '1') {
				$("tr#tr_sibling" + R).remove();
				if(R == sib) {
					R = R - 1;
					var temp = true;
					for(i = R; i >= 1; i--) {
						if($('#all_state_add' + i).length) {
							$("#all_state_add" + i).show();
							temp = false;
							sib = i;
							break;
						}
					}
					if(temp) {
						all_state_add_fn(0);
					}
				}
				$('.sib_srno').each(function(i, obj) {
					obj.innerHTML = i + 1;
					sib_srno = i + 1;
				});
				alert("Data Deleted Successfully");
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
}

 </script>