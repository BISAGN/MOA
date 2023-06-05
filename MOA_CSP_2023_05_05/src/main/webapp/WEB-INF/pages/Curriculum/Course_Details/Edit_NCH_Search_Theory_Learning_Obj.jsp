<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

<section class="dashboard-page">
<div class="container-fluid">
	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2><span id="lbladd1"></span>Edit Theory Learning Objectives</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">Edit Theory Learning Objectives</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<div class="form-elements-wrapper">
		<div class="row">
			<div class="col-12">
				<!-- input style start -->
                <form:form  name="course" id="edit_nch_learning_object" action="edit_NCH_Theory_learning_objAction" method='POST' commandName="edit_NCH_Theory_learning_objCMD"   enctype="multipart/form-data">
				<div class="card-style mb-30">
					<h6 class="mb-25">Edit Theory Learning Objectives</h6>
						<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="text-input">System<span class="mandatory">*</span></label>
									<div class="select-position">
									<select class="singleselect form-control form-control-lg" name="system_id" id="system_id">
									  <option value="0">--Select--</option>
										<c:forEach var="item" items="${getSystemList}" varStatus="num">
													<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
										</c:forEach>
								   </select>
							     </div>
								</div>					
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="text-input">Degree<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="degree_id" id="degree_id" class="form-control">
												<option value="0">--Select--</option>
											</select>
										</div>
									</div>								
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="text-input">Professional<span class="mandatory">*</span></label>
									  <div class="select-position">
											<select class="singleselect form-control form-control-lg" name="professional_id" id="professional_id">
									      <option value="0">--Select--</option>
										<c:forEach var="item" items="${getprofessionalList}" varStatus="num">
											<option value="${item.id}" name="${item.professional}">${item.professional}</option>
										</c:forEach>
								     </select>										
								 </div>
									</div>								
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="text-input">Subject<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="course_id"  id="course_id">
												<option value="0">--Select--</option>
											</select>
									   </div>
									</div>								
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="text-input">Topic<span class="mandatory">*</span></label>
										<div class="select-position">
										   <select class="singleselect form-control form-control-lg" name="topic_id" id="topic_id">
									         <option value="0">--Select--</option>
								    		</select>										
								      </div>
									</div>	
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Learning Outcome<span class="mandatory">*</span></label>
											<div class="select-position">
											<select class="" id="div_iv_fluids" name="">
												<option value="0" id="learning_outcome0" class="hida overSelect">--Select--</option>
											</select>
											<c:forEach var="item" items="${Learning_OutcomeList}" varStatus="num">
											 <input type="hidden" id="learning_outcome1" name="learning_outcome"
												autocomplete="off" class="form-control-sm form-control"
												value="">
												</c:forEach>
										</div>
										<div id="div_iv_fluids_2" class="multiselect">
											<div id="div_cb_dd"
												class="form-check radio-style checkbox-style align-items-center">
											</div>
										</div>
										</div>
											
										<input type="hidden" id="learning_outcome_hid"
										name="learning_outcome_hid" class="form-control autocomplete">
										
									</div>
							
						</div>
				<div class="row">
	              <div class="col-12">
					 <div class="table-wrapper table-responsive custom-table-p simple-table b-top mt-0">
						<table class="table" id="att_Tb">
							<thead>
								<tr>
														<th><h6>Sr No</h6></th>
														<th><h6>Generic Competency</h6></th>
														<th><h6>Subject Area</h6></th>
														<th><h6>Millers Level:Does/Shows how/ Knows how/ Knows</h6></th>
														<th><h6>Specific Competency</h6></th>
														<th><h6>SLO/ Outcome</h6></th>
														<th><h6>Blooms Domain</h6></th>
														<th><h6>Guilbert's Level</h6></th>
														<th><h6>Must Know/ Desirable to know/ nice to know</h6></th>
														<th><h6>T-L Methods</h6></th>
														<th><h6>Formative Assessment</h6></th>
														<th><h6>Summative Assessment</h6></th>
														<th><h6>Integration Departments- Horizontal/ Vertical/ Spiral</h6></th>
														<th><h6>Action</h6></th>
								</tr>
								</thead>
							<tbody >
							</tbody>
						</table>
						<input type="hidden" id="count" name="count" value="1"> 
				<input type="hidden" id="new_count_hidden" name="new_count_hidden"  class="form-control autocomplete"> 
				<input type="hidden" id="old_count" name="old_count"  class="form-control autocomplete"> 
		       </div>
		  </div>
	</div>
</div>
						<ul class="buttons-group mainbtn">
							<li>
							<input href="#0" class="main-btn deactive-btn btn-hover" value="Update"
								id="btn-save" type="submit">
							</li>
<!-- 							<li> -->
<!-- 								<a href="T3_Search_Learning_Object_Url" class="main-btn dark-btn n btn-hover" type="button">Back</a> -->
<!-- 							</li> -->
							<li> <a  href=Nch_Search_Theory_Learning_Object_Url class="main-btn dark-btn-outline  btn-hover btn-iconic-icon">
					             <i class="lni lni-chevron-left"></i>Back</a></li>
						</ul>
						<input type='hidden' id='pmid' name="pmid" value='0' /> 
				<!-- end card -->
             </form:form>
            </div>
		 </div>
	  </div>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {
	$.ajaxSetup({
		async : false
	});
// 	debugger;
	$("#pmid").val('${list[0][0]}');
	$("select#system_id").val('${list[0][1]}');
	$('#system_id').trigger('change');
	getdegreelistbysystem();
	$("select#degree_id").val('${list[0][2]}');
	$('select#professional_id').val('${list[0][3]}');
	$('#professional_id').trigger('change');
	getcourselistbysystemdegreeprof();
	$('select#course_id').val('${list[0][4]}');
	getTopicListbyCourse();
	$('select#topic_id').val('${list[0][5]}');
	$('#topic_id').trigger('change');
	Nchgetlearn_outListby_Topic();
	
	checkoptioninedit('${list[0][6]}');
	$('select#learning_outcome1').val('${list[0][6]}');

	addmoredatafetch1();
		$.ajaxSetup({
			async : false
		});
});

function addmoredatafetch1(){
// 	debugger;
	if("${liteLearningchildlist.size()}" > 0){
		formopen_att(0);
	}
	var ser = 0;
	var ind =1;
	<c:forEach var="j" items="${liteLearningchildlist}" varStatus="num"> 
		
		if(ser != "0"){
			formopen_att(ser);
		}
		
		var id = "${j[0]}";
		var generic_competency = "${j[1]}";
		var subject_area = "${j[2]}";
		var millers_level = "${j[3]}";
		var specific_competency = "${j[4]}";
		var slo_outcome = "${j[5]}";
		var blooms_domain = "${j[6]}";
		var guilberts_level = "${j[7]}";
		var mk_dk = "${j[8]}";
		var tl_methods = "${j[9]}";
		var form_assessment = "${j[10]}";
		var summ_assessment = "${j[11]}";
		var int_departments = "${j[12]}";
		
		$("#eid"+ind).val(id);
		$("input#generic_competency"+ind).val(generic_competency);
        $("input#subject_area"+ind).val(subject_area);
        $("select#millers_level"+ind).val(millers_level);
        $("input#specific_competency"+ind).val(specific_competency);
        $("input#slo_outcome"+ind).val(slo_outcome);
        $("select#blooms_domain"+ind).val(blooms_domain);
        $("select#guilberts_level"+ind).val(guilberts_level);
        $('#guilberts_level').trigger('change');
        $("select#mk_dk"+ind).val(mk_dk);
        $("select#tl_methods"+ind).val(tl_methods);
        $("select#form_assessment"+ind).val(form_assessment);
        $("select#summ_assessment"+ind).val(summ_assessment);
        $("input#int_departments"+ind).val(int_departments);
  		
		ser=parseInt(ser)+1;
		ind=parseInt(ind)+1;
	</c:forEach>
	
	$("#old_count").val(ser);
}

function getdegreelistbysystem() {
	var system_name = $("#system_id").val();
	$
			.post('getDegreeListbysystem1?' + key + "=" + value, {
				system_name : system_name
			})
			.done(
					function(j) {
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#degree_id").html(options);
						if('${list[0][2]}' != ''){
							$("select#degree_id").val('${list[0][2]}');
						}
					});
}

function getcourselistbysystemdegreeprof() {
	var system_id = $("#system_id").val();
	var degree_id = $("#degree_id").val();
	var professional_id = $("#professional_id").val();
	$
			.post('getCourseList_for_Curri?' + key + "=" + value,
					{
						degree_id : degree_id,
						system_id : system_id,
						professional_id:professional_id
					})
			.done(
					function(j) {
						
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#course_id").html(options);
						if('${list[0][4]}' != ''){
							$('select#course_id').val('${list[0][4]}');
						}
					});
}

function getTopicListbyCourse() {
	
	var course_id = $("#course_id").val();
	$
			.post('get_NCH_TopicListbyCourse?' + key + "=" + value, {
				course_id : course_id
			})
			.done(
					function(j) {

						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#topic_id").html(options);
						if('${list[0][5]}' != ''){
							$('select#topic_id').val('${list[0][5]}');
						}
					});
}
var att=1;
function formopen_att(R){
	$("#att_Tb").show();
	
		 $("#id_add_att"+R).hide();
		 $("#att_id_remove"+R).hide();
		 
		 att=0;
		 att= parseInt(R)+1;
		 
		 if(att < 51){
			 
			 $("input#new_count_hidden").val(att);//current serial No
			 $("table#att_Tb").append('<tr align="center" id="tr_id_att'+att+'"><td>'+att+'</td>'
				
				 
				 +'<td class="min-width"><div class="input-style-1"><input type="text" id="generic_competency'+att+'" name="generic_competency'+att+'"  class="form-control"  autocomplete="off" placeholder="Generic Competency" ></div></td>'
				 
				 +'<td class="min-width"><div class="input-style-1"><input type="text" id="subject_area'+att+'" name="subject_area'+att+'"  class="form-control"  autocomplete="off" placeholder="Subject Area" ></div></td>'
				 
				 +'<td><div class="select-style-1"><div class="select-position"><select id="millers_level'+att+'" name="millers_level'+att+'" class="form-control" ><option value="0">--Select--</option>'
				 +'<c:forEach var="item" items="${getMillers_level}" varStatus="num"><option value="${item.id}" name="${item.rep_und}">${item.rep_und}</option></c:forEach>' 
				 +'</select></div></div> </td>'
				 
				 +'<td class="min-width"><div class="input-style-1"><input type="text" id="specific_competency'+att+'" name="specific_competency'+att+'"  class="form-control"  autocomplete="off" placeholder="Specific Competency" ></div></td>'
				 
				 +'<td class="min-width"><div class="input-style-1"><input type="text" id="slo_outcome'+att+'" name="slo_outcome'+att+'"  class="form-control"  autocomplete="off" placeholder="SLO/ Outcome" ></div></td>'
				 
				 
				 +'<td><div class="select-style-1"><div class="select-position"><select id="blooms_domain'+att+'" name="blooms_domain'+att+'" class="form-control" ><option value="0">--Select--</option>'
				 +'<c:forEach var="item" items="${NchgetBloom_Domain}" varStatus="num"><option value="${item.id}" name="${item.domain}">${item.domain}</option></c:forEach>' 
				 +'</select></div></div> </td>'
				 
				 
				 +'<td><div class="select-style-1"><div class="select-position"><select id="guilberts_level'+att+'" name="guilberts_level'+att+'" class="form-control" ><option value="0">--Select--</option>'
				 +'<c:forEach var="item" items="${getguilberts_levelList}" varStatus="num"><option value="${item.id}" name="${item.guilberts_level}">${item.guilberts_level}</option></c:forEach>' 
				 +'</select></div></div> </td>'
				 
				 +'<td><div class="select-style-1"><div class="select-position"><select id="mk_dk'+att+'" name="mk_dk'+att+'" class="form-control" ><option value="0">--Select--</option>'
				 +'<c:forEach var="item" items="${Nchget_Mk_Dk_Nk}" varStatus="num"><option value="${item.id}" name="${item.scope}">${item.scope}</option></c:forEach>' 
				 +'</select></div></div> </td>'
				 
				 
				 +'<td><div class="select-style-1"><div class="select-position"><select id="tl_methods'+att+'" name="tl_methods'+att+'" class="form-control" ><option value="0">--Select--</option>'
				 +'<c:forEach var="item" items="${Nchgett_l_method}" varStatus="num"><option value="${item.id}" name="${item.method}">${item.method}</option></c:forEach>' 
				 +'</select></div></div> </td>'
				 
				 
				 +'<td><div class="select-style-1"><div class="select-position"><select id="form_assessment'+att+'" name="form_assessment'+att+'" class="form-control" ><option value="0">--Select--</option>'
				 +'<c:forEach var="item" items="${getnch_formative_and_summativeList}" varStatus="num"><option value="${item.id}" name="${item.formative}">${item.formative}</option></c:forEach>' 
				 +'</select></div></div> </td>'
				 
				 +'<td><div class="select-style-1"><div class="select-position"><select id="summ_assessment'+att+'" name="summ_assessment'+att+'" class="form-control" ><option value="0">--Select--</option>'
				 +'<c:forEach var="item" items="${getnch_formative_and_summativeList}" varStatus="num"><option value="${item.id}" name="${item.formative}">${item.formative}</option></c:forEach>' 
				 +'</select></div></div> </td>'
				
				 +'<td class="min-width"><div class="input-style-1"><input type="text" id="int_departments'+att+'" name="int_departments'+att+'"  class="form-control"  autocomplete="off" placeholder="Integration Departments" ></div></td>'
//					 +'<td class="min-width"><div class="input-style-2"><input type="text" id="non_lecture_hours'+att+'" name="non_lecture_hours'+att+'"  maxlength="3" class="form-control"  autocomplete="off" placeholder="Non Lecture Hours" ></div></td>'
				 +'<td><ul class="buttons-group"><li value = "ADD" title = "ADD" id = "id_add_att'+att+'" ><a class="main-btn success-btn btn-hover btn-sm"><i class="lni lni-plus"></i></a></li><li value="REMOVE" title = "REMOVE" id = "att_id_remove'+att+'" ><a class="main-btn danger-btn btn-hover btn-sm"><i class="lni lni-trash-can"></i></a></li></td>'
	   		     +'</tr>');
			 
			 $("table#att_Tb").append('<input type="hidden" name="eid'+att+'" id="eid'+att+'"/>');
			     addOnclick(att);
			     removeOnclick(att);
		 
			}else{
					alert("Please Enter max 50 Quantity");
					 if ( att == 51){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
}

function Nchgetlearn_outListby_Topic() {
	var topic_id = $("#topic_id").val();
	
			$.post('Nchgetlearn_outmeListby_Topic?' + key + "=" + value, {
				topic_id : topic_id
			})
			.done(function(j) {
				$("div#div_cb_dd").empty();
				for(var p=0;p<j.length;p++){
					var q = p+1;
					
					
					$("div#div_cb_dd")
					.append(
						'<input class="multi form-check-input mr-5" type="checkbox" id="learning_outcome'+q+'" name="learning_outcome" value="'+j[p][0]+'"/>'
						+'<label class="lbl"  for="first">'+j[p][1]+'</label>');
					setonclickofCBDD(q);
					}
		});
}



function checkoptioninedit(sids){

	var len = "";
// 	alert("hi-----------")
	
	var topic_id = $("#topic_id").val();
	$.post('Nchgetlearn_outmeListby_Topic?' + key + "=" + value, {
				topic_id : topic_id
			}).done(function(j) {

				len = j.length;
				$("div#div_cb_dd").empty();
						for(var p=0;p<len;p++){
							
							var q = p+1;



						$("div#div_cb_dd").append(
							'<input class="multi form-check-input mr-5" type="checkbox" id="learning_outcome'+q+'" name="learning_outcome" value="'+j[p][0]+'"/>'
							+'<label class="lbl"  for="first">'+j[p][1]+'</label>');
// var match = sids.split(",");

						
						setonclickofCBDD(q);
						}
			});
	
	var match = sids.split(",");
	for (var a in match)
	{
	
	    var variable = match[a];
	    	for(var i = 1;i<= len ; i++){
// 		    	debugger;
($('#learning_outcome' + i).val());
				var temp_data = $('#learning_outcome' + i).val();
				if(variable.trim() == temp_data.trim()){
					$('#learning_outcome' + i).click();
				}
	    	}
	}

}
function setonclickofCBDD(obj){
	document.getElementById('learning_outcome'+obj).onclick = function() {
		mycheckindex(obj);
	};
}
function mycheckindex(myindex) {
	var gsida = [];
	var ele = document.getElementsByName("learning_outcome");

// 	console.log("ele.length - " + ele.length);
	for (var i = 0; i < ele.length; i++) {
		if (ele[i].checked) {
			/* gsida.push(gsid[i].value); */
			gsida.push(ele[i].value);
			/* remarksa.push(remarks[i].value); */
		}
	}
// 	alert(gsida.toString());
	document.getElementById('learning_outcome_hid').value = gsida.toString();
}

var show = true;
var temp;
function showCheckboxes(obj) {

	var checkboxes = obj.id + "_2";
	var checkboxRead = checkboxes.substring(4, checkboxes.length);
	checkboxRead = checkboxRead.substring(0, checkboxRead.length - 2);
	var data_check = $("#" + checkboxRead).is('[readonly]');
	if (show && data_check == false) {
		$("#" + checkboxes).show();
		temp = checkboxes;
		show = false;
	} else {
		$("#" + checkboxes).hide();
		show = true;
	}
	window.addEventListener('mouseup', function(event) {
		var pol = document.getElementById(temp)
		if (event.target != pol
				&& event.target.parentNode.parentNode != pol) {
			pol.style.display = 'none';
		}
	});
}


function formopen_re_att(R){
	var del_index = $("#idofprocedure"+R).val();
	if(String(del_index) == "undefined"){
		del_index="0";
	}else{
		del_index = del_index;
	}
	 var del_field_val = $("#del_id_hidden").val();
	 
	 if(del_field_val == "0,undefined"){
		 $("#del_id_hidden").val(del_index);
	 }else{
		 $("#del_id_hidden").val(del_field_val+","+del_index);
	 }
	
	 $("tr#tr_id_att"+R).remove();
	 att = att-1;
	 $("input#count_hidden_att").val(att);
	 if(R > 2){
		 $("#id_add_att"+att).show();
		 $("#att_id_remove"+att).show();
	 }
	 if(R == 2){
		 $("#id_add_att"+att).show();
	 }
	 var ncc = $("#new_count_hidden").val();
	 ncc = ncc-1;
	 $("#new_count_hidden").val(ncc);
}

document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	document.getElementById('system_id').onchange = function() {
		getdegreelistbysystem();
	};
	document.getElementById('degree_id').onchange = function() {
		getcourselistbysystemdegreeprof();
	};
	document.getElementById('professional_id').onchange = function() {
		getcourselistbysystemdegreeprof();
	};
	document.getElementById('course_id').onchange = function() {
		getTopicListbyCourse();
	};
	document.getElementById('topic_id').onchange = function() {
		Nchgetlearn_outListby_Topic();
	};
	document.getElementById('div_iv_fluids').onclick = function() {
		showCheckboxes(this);
	};
// 	document.getElementById('id_add_att1').onclick = function() {
// 		formopen_att(1)
// 	};
});

function Validation() {
// 	var count = "${Learning_OutcomeList.size()}";
// 	var check_list = "";
// 	for(var i = 1; i <= count; i++){
// 		if ($('input[name="div_iv_fluids_2'+i+'"]:checked').is(':checked')){
// 			check_list += $('input[name="div_iv_fluids_2'+i+'"]:checked').val() +",";
// 			$("#learning_outcome_hid").val(check_list);
// 		}

		
// 	}
	
// 	var temp = $("#learning_outcome_hid").val();
// 	temp = temp.substring(0,temp.length-1);
	
// 	$("#learning_outcome_hid").val(temp);

// alert(learning_outcome_hid)
	
	if ($("#system_id").val().trim() == "0") {
		alert("Please Select System.");
		$("select#system_id").focus();
		return false;
	}
	if ($("#degree_id").val().trim() == "0") {
		alert("Please Select Degree.");
		$("select#degree_id").focus();
		return false;
	}
	if ($("#professional_id").val().trim() == "0") {
		alert("Please Select professional.");
		$("select#professional_id").focus();
		return false;
	}
	if ($("#course_id").val().trim() == "0") {
		alert("Please Select Subject .");
		$("select#course_id").focus();
		return false;
	}
	if ($("#topic_id").val().trim() == "0") {
		alert("Please Select Topic .");
		$("select#topic_id").focus();
		return false;
	}
	
	var count = $("#new_count_hidden").val();
	for(var i=1;i<=count;i++){
		
		var count = "${Learning_OutcomeList.size()}";
		var check_list = "";
		for(var i = 1; i <= count; i++){
			if ($('input[name="div_iv_fluids_2'+i+'"]:checked').is(':checked')){
				check_list += $('input[name="div_iv_fluids_2'+i+'"]:checked').val() +",";
				$("#learning_outcome_hid").val(check_list);
			}

			
		}
		
		var temp = $("#learning_outcome_hid").val();
		temp = temp.substring(0,temp.length);
		
		$("#learning_outcome_hid").val(temp);
// 		if ($("#generic_competency" + i).val()== "") {
// 			alert("Please Enter Generic Competency.");
// 			$("#generic_competency" + i).focus();
// 			return false;
// 		}
// 		if ($("#subject_area" + i).val() == "") {
// 			alert("Please Enter Subject Area.");
// 			$("select#subject_area" + i).focus();
// 			return false;
// 		}
// 		if ($("#millers_level" + i).val()== "0") {
// 			alert("Please Select Millers Level.");
// 			$("select#millers_level" + i).focus();
// 			return false;
// 		}
// 		if ($("#specific_competency" + i).val() == "") {
// 			alert("Please Enter Specific Competency.");
// 			$("select#specific_competency" + i).focus();
// 			return false;
// 		}
// 		if ($("#slo_outcome" + i).val()== "") {
// 			alert("Please Enter SLO/ Outcome.");
// 			$("select#slo_outcome" + i).focus();
// 			return false;
// 		}
// 		if ($("#blooms_domain" + i).val() == "0") {
// 			alert("Please Select Blooms Domain.");
// 			$("select#blooms_domain" + i).focus();
// 			return false;
// 		}
// 		if ($("#guilberts_level" + i).val() == "0") {
// 			alert("Please Select Guilbert's Level.");
// 			$("select#guilberts_level" + i).focus();
// 			return false;
// 		}
// 		if ($("#mk_dk" + i).val() == "0") {
// 			alert("Please Select Must Know/Desirable to know/nice to know.");
// 			$("select#mk_dk" + i).focus();
// 			return false;
// 		}
// 		if ($("#tl_methods" + i).val() == "0") {
// 			alert("Please Select T-L Methods.");
// 			$("select#tl_methods" + i).focus();
// 			return false;
// 		}
// 		if ($("#form_assessment" + i).val() == "0") {
// 			alert("Please Select Formative Assessment.");
// 			$("select#form_assessment" + i).focus();
// 			return false;
// 		}
// 		if ($("#summ_assessment" + i).val() == "0") {
// 			alert("Please Select Summative Assessment.");
// 			$("select#summ_assessment" + i).focus();
// 			return false;
// 		}
// 		if ($("#int_departments" + i).val() == "") {
// 			alert("Please Select Integration Departments.");
// 			$("select#int_departments" + i).focus();
// 			return false;
// 		}
	}
}
function addOnclick(index){
	document.getElementById('id_add_att'+index).onclick = function() {
		formopen_att(index);
	};
}
function removeOnclick(index){
	document.getElementById('att_id_remove'+index).onclick = function() {
		formopen_re_att(index);
	};
}
</script>