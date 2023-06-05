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
<script type="text/javascript" src="js\watermark\common.js"></script>
<!-- <link href="js/dropDown/select2.min.css" rel="Stylesheet"></link> -->
<!-- <script src="js/dropDown/select2.min.js" type="text/javascript"></script> -->

<section class="dashboard-page">
<div class="container-fluid">
	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2><span id="lbladd1"></span>Edit T4 - Practical Learning Objectives</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">Edit T4 - Practical Learning Objectives</li>
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
                <form:form  name="course" id="edit_t4_learning_object" action="edit_t4_learning_objectAction" method='POST' commandName="edit_t4_learning_objectCMD"   enctype="multipart/form-data">
				<div class="card-style mb-30">
					<h6 class="mb-25">Edit T4 - Practical Learning Objectives</h6>
						<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
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
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Degree<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="degree_id" id="degree_id" class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
											</select>
										</div>
									</div>								
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
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
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Course<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="course_id" class="singleselect form-control form-control-lg" id="course_id">
												<option value="0">--Select--</option>
											</select>
									   </div>
									</div>								
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Practical<span class="mandatory">*</span></label>
										<div class="select-position">
										   <select name="practical_id" class="select2 narrow wrap form-control form-control-lg form-control-a disablecopypaste" id="practical_id">
									         <option value="0">--Select--</option>
								    		</select>										
								      </div>
									</div>	
							</div>
						</div>
				<div class="row">
	              <div class="col-12">
					 <div class="table-wrapper table-responsive custom-table-p simple-table b-top mt-0">
						<table class="table" id="att_Tb">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th> <!-- 0 -->
									<th><h6>A3 Course Outcome</h6></th>
									<th><h6>B3 Learning Objective(At the end of the session,the students should be able to)</h6></th>
									<th><h6>C3 Domain/sub</h6></th>
									<th><h6>D3 Must to know/desirable to know/Nice to know</h6></th>
									<th><h6>E3 Level Does/Shows how/Knows how/Know</h6></th>
									<th><h6>F3 T-L method</h6></th>
									<th><h6>G3 Assessment</h6></th>
									<th><h6>H3 Formative/Summative</h6></th>
									<th><h6>I3 Term</h6></th>
									<th><h6>J3 Integration</h6></th>
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
							<li>
								<a href="Search_T4_Learning_Object_Url" class="main-btn dark-btn n btn-hover" type="button">Back</a>
							</li>
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
	
	$("#pmid").val('${list[0][0]}');
	$("select#system_id").val('${list[0][1]}');
	$('#system_id').trigger('change');
	getdegreelistbysystem();
	$("select#degree_id").val('${list[0][2]}');
	$('#degree_id').trigger('change');
	$('select#professional_id').val('${list[0][3]}');
	$('#professional_id').trigger('change');
	getcourselistbysystemdegreeprof();
	$('select#course_id').val('${list[0][4]}');
	getPracticalListbyCourse();
	$('select#practical_id').val('${list[0][5]}');
// 	$('#practical_id').trigger('change');

	addmoredatafetch1();
		$.ajaxSetup({
			async : false
		});
});

function addmoredatafetch1(){
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
		var a3_couse_outcome = "${j[1]}";
		var b3_learning_obj = "${j[2]}";
		var c3_domain_sub = "${j[3]}";
		var d3_desirable_know = "${j[4]}";
		var e3_level_show_know = "${j[5]}";
		var f3_t_l_method = "${j[6]}";
		var g3_assessment = "${j[7]}";
		var h3_formative_summative = "${j[8]}";
		var i3_term = "${j[9]}";
		var j3_integration = "${j[10]}";
		
		$("#eid"+ind).val(id);
		$("select#a3_couse_outcome"+ind).val(a3_couse_outcome);
        $("input#b3_learning_obj"+ind).val(b3_learning_obj);
        $("select#c3_domain_sub"+ind).val(c3_domain_sub);
        $("select#d3_desirable_know"+ind).val(d3_desirable_know);
        $("select#e3_level_show_know"+ind).val(e3_level_show_know);
        $("select#f3_t_l_method"+ind).val(f3_t_l_method);
        $("select#g3_assessment"+ind).val(g3_assessment);
        $("select#h3_formative_summative"+ind).val(h3_formative_summative);
        $("select#i3_term"+ind).val(i3_term);
        $("input#j3_integration"+ind).val(j3_integration);
  		
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
			.post('getCourseList?' + key + "=" + value,
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

function getPracticalListbyCourse() {
	
	var course_id = $("#course_id").val();
	$
			.post('getPracticalListby_Course?' + key + "=" + value, {
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
						$("select#practical_id").html(options);
						if('${list[0][5]}' != ''){
							$('select#practical_id').val('${list[0][5]}');
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
					
					 +'<td><div class="select-style-1"><div class="select-position"><select id="a3_couse_outcome'+att+'" name="a3_couse_outcome'+att+'" class="form-control" ><option value="0">--Select--</option>'
					 +'<c:forEach var="item" items="${getCourse_OutcomeList}" varStatus="num"><option value="${item.id}" name="${item.course_outcome}">${item.course_outcome}</option></c:forEach>' 
					 +'</select></div></div> </td>'
					 +'<td class="min-width"><div class="input-style-2"><input type="text" id="b3_learning_obj'+att+'" name="b3_learning_obj'+att+'"  class="form-control"  autocomplete="off" placeholder="B3 Learning Obj" ></div></td>'
					 
					 +'<td><div class="select-style-1"><div class="select-position"><select id="c3_domain_sub'+att+'" name="c3_domain_sub'+att+'" class="form-control" ><option value="0">--Select--</option>'
					 +'<c:forEach var="item" items="${getc3_domain_subList}" varStatus="num"><option value="${item.id}" name="${item.domain}">${item.domain}</option></c:forEach>' 
					 +'</select></div></div> </td>'
					 
					 +'<td><div class="select-style-1"><div class="select-position"><select id="d3_desirable_know'+att+'" name="d3_desirable_know'+att+'" class="form-control" ><option value="0">--Select--</option>'
					 +'<c:forEach var="item" items="${getd3_desirable_knowList}" varStatus="num"><option value="${item.id}" name="${item.scope}">${item.scope}</option></c:forEach>' 
					 +'</select></div></div> </td>'
					 +'<td><div class="select-style-1"><div class="select-position"><select id="e3_level_show_know'+att+'" name="e3_level_show_know'+att+'" class="form-control" ><option value="0">--Select--</option>'
					 +'<c:forEach var="item" items="${gete3_level_show_knowList}" varStatus="num"><option value="${item.id}" name="${item.rep_und}">${item.rep_und}</option></c:forEach>' 
					 +'</select></div></div> </td>'
					 +'<td><div class="select-style-1"><div class="select-position"><select id="f3_t_l_method'+att+'" name="f3_t_l_method'+att+'" class="form-control" ><option value="0">--Select--</option>'
					 +'<c:forEach var="item" items="${getf3_t_l_methodList}" varStatus="num"><option value="${item.id}" name="${item.method}">${item.method}</option></c:forEach>' 
					 +'</select></div></div> </td>'
					 +'<td><div class="select-style-1"><div class="select-position"><select id="g3_assessment'+att+'" name="g3_assessment'+att+'" class="form-control" ><option value="0">--Select--</option>'
					 +'<c:forEach var="item" items="${getg3_assessmentList}" varStatus="num"><option value="${item.id}" name="${item.assessment_method}">${item.assessment_method}</option></c:forEach>' 
					 +'</select></div></div> </td>'
					 +'<td><div class="select-style-1"><div class="select-position"><select id="h3_formative_summative'+att+'" name="h3_formative_summative'+att+'" class="form-control" ><option value="0">--Select--</option>'
					 +'<c:forEach var="item" items="${geth3_formative_summativeList}" varStatus="num"><option value="${item.id}" name="${item.assessment_type}">${item.assessment_type}</option></c:forEach>' 
					 +'</select></div></div> </td>'
					 +'<td><div class="select-style-1"><div class="select-position"><select id="i3_term'+att+'" name="i3_term'+att+'" class="form-control" ><option value="0">--Select--</option>'
					 +'<c:forEach var="item" items="${geti3_termList}" varStatus="num"><option value="${item.id}" name="${item.term}">${item.term}</option></c:forEach>' 
					 +'</select></div></div> </td>'
					
					 +'<td class="min-width"><div class="input-style-2"><input type="text" id="j3_integration'+att+'" name="j3_integration'+att+'"  class="form-control"  autocomplete="off" placeholder="J3 Integration" ></div></td>'
// 					 +'<td class="min-width"><div class="input-style-2"><input type="text" id="non_lecture_hours'+att+'" name="non_lecture_hours'+att+'"  maxlength="3" class="form-control"  autocomplete="off" placeholder="Non Lecture Hours" ></div></td>'
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
		getPracticalListbyCourse();
	};
// 	document.getElementById('id_add_att1').onclick = function() {
// 		formopen_att(1)
// 	};
});

function Validation() {
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
		alert("Please Select Course .");
		$("select#course_id").focus();
		return false;
	}
	if ($("#practical_id").val().trim() == "0") {
		alert("Please Select Practical .");
		$("select#practical_id").focus();
		return false;
	}
	
	var count = $("#new_count_hidden").val();
	for(var i=1;i<=count;i++){
		
		if ($("#a3_couse_outcome"+i).val().trim() == "0") {
			alert("Please Select A3 Course Outcome.");
			$("#a3_couse_outcome"+i).focus();
			return false;
		}
		if ($("#b3_learning_obj"+i).val().trim() == "0") {
			alert("Please Select B3 Learning Objective.");
			$("select#b3_learning_obj"+i).focus();
			return false;
		}
		if ($("#c3_domain_sub"+i).val().trim() == "0") {
			alert("Please Select C3 Domain/sub.");
			$("select#c3_domain_sub"+i).focus();
			return false;
		}
		if ($("#d3_desirable_know"+i).val().trim() == "0") {
			alert("Please Select D3 Must to know/desirable to know/Nice to know.");
			$("select#d3_desirable_know"+i).focus();
			return false;
		}
		if ($("#e3_level_show_know"+i).val().trim() == "0") {
			alert("Please Select E3 Level Does/Shows how/Knows how/Know.");
			$("select#e3_level_show_know"+i).focus();
			return false;
		}
		if ($("#f3_t_l_method"+i).val().trim() == "0") {
			alert("Please Select F3 T-L method.");
			$("select#f3_t_l_method"+i).focus();
			return false;
		}
		if ($("#g3_assessment"+i).val().trim() == "0") {
			alert("Please Select G3 Assessment.");
			$("select#g3_assessment"+i).focus();
			return false;
		}
		if ($("#h3_formative_summative"+i).val().trim() == "0") {
			alert("Please Select H3 Formative/Summative.");
			$("select#g3_assessment"+i).focus();
			return false;
		}
		if ($("#i3_term"+i).val().trim() == "0") {
			alert("Please Select I3 Term.");
			$("select#i3_term"+i).focus();
			return false;
		}
		if ($("#j3_integration"+i).val().trim() == "0") {
			alert("Please Select J3 Integration.");
			$("select#j3_integration"+i).focus();
			return false;
		}
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