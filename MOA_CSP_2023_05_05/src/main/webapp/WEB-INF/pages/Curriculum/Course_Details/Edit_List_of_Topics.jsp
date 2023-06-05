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
					<h2><span id="lbladd1"></span>Edit T2 - Add Content Of Subject</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">Edit T2 - Add Content Of Subject</li>
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
                <form:form  name="course" id="edit_list_topics" action="edit_list_topicsAction" method='POST' commandName="edit_list_topicsCMD"   enctype="multipart/form-data">
				<div class="card-style mb-30">
					<h6 class="mb-25">Edit T2 - Add Content Of Subject</h6>
						<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">System<span class="mandatory">*</span></label>
									<div class="select-position">
									<select name="system_id" id="system_id">
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
											<select name="degree_id" id="degree_id" class="form-control">
												<option value="0">--Select--</option>
											</select>
										</div>
									</div>								
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Professional<span class="mandatory">*</span></label>
									  <div class="select-position">
											<select name="professional_id" id="professional_id">
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
									<label for="text-input">Subject<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="course_id" class="form-control" id="course_id">
												<option value="0">--Select--</option>
											</select>
									   </div>
									</div>								
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Paper<span class="mandatory">*</span></label>
										<div class="select-position">
										   <select name="paper_id" id="paper_id">
									         <option value="0">--Select--</option>
												<c:forEach var="item" items="${getpaperList}" varStatus="num">
													<option value="${item.id}" name="${item.paper}">${item.paper}</option>
												</c:forEach>
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
									<th rowspan="2"><h6>Sr No</h6></th>
									<th rowspan="2"><h6>Topic</h6></th>
									<th rowspan="2"><h6>Term</h6></th>
									<th rowspan="2"><h6>Lecture Hours</h6></th>
									<th rowspan="2"><h6>Non Lecture Hours</h6></th>
									<th rowspan="2"><h6>Action</h6></th>
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
							<li> <a  href="Search_List_of_Topics_Url" class="main-btn dark-btn-outline  btn-hover btn-iconic-icon">
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
	$("#pmid").val('${list[0][0]}');
	$("select#system_id").val('${list[0][1]}');
	getdegreelistbysystem();	
	$("select#degree_id").val('${list[0][2]}');
	$('select#professional_id').val('${list[0][3]}');
	getcourselistbydegree();	
	$('select#course_id').val('${list[0][4]}');
	$('select#paper_id').val('${list[0][5]}');
	addmoredatafetch1();
		$.ajaxSetup({
			async : false
		});
});

function addmoredatafetch1(){
	debugger;
	if("${litechildlist.size()}" > 0){
		formopen_att(0);
	}
	var ser = 0;
	var ind =1;
	<c:forEach var="j" items="${litechildlist}" varStatus="num"> 
		
		if(ser != "0"){
			formopen_att(ser);
		}
		
		var id = "${j[0]}";
		var topic_id = "${j[1]}";
		var lecture_hours = "${j[2]}";
		var non_lecture_hours = "${j[3]}";
		var term_id = "${j[4]}";
		
		$("#eid"+ind).val(id);
		$("#topic_id"+ind).val(topic_id);
        $("#term_id"+ind).val(term_id);
        $("#lecture_hours"+ind).val(lecture_hours);
        $("#non_lecture_hours"+ind).val(non_lecture_hours);
  		
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
					});
}
function getTopiclistbyCourse(R) {
	var course_id = $("#course_id").val();
	var degree_id = $("#degree_id").val();
	$
			.post('getCourseToTopic?' + key + "=" + value, {
				course_id : course_id,
				degree_id : degree_id
			})
			.done(
					function(j) {
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#topic_id"+R).html(options);
					});
}

function getcourselistbydegree() {
	var system_id = $("#system_id").val();
	var degree_id = $("#degree_id").val();
	$
			.post('getCourseList_for_Curri?' + key + "=" + value,
					{
						degree_id : degree_id,
						system_id : system_id
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
					});
}
function getTerm_listByProf(R) {
	var system = $("#system_id").val();
	var degree = $("#degree_id").val();
	var professional = $("#professional_id").val();

	$
			.post('getTerm_listByProf_for_Curri?' + key + "=" + value, {
				system : system,
				degree : degree,
				professional : professional
			})
			.done(
					function(j) {
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#term_id" +R).html(options);
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
					
					 +'<td><div class="select-style-1"><div class="select-position"><select id="topic_id'+att+'" name="topic_id'+att+'" class="form-control" ><option value="0">--Select--</option>'
//  					 +'<c:forEach var="item" items="${getTopicList}" varStatus="num"><option value="${item.id}" name="${item.topic}">${item.topic}</option></c:forEach>' 
					 +'</select></div></div> </td>'
					 
					 +'<td><div class="select-style-1"><div class="select-position"><select id="term_id'+att+'" name="term_id'+att+'" class="form-control" ><option value="0">--Select--</option>'
// 					 +'<c:forEach var="item" items="${geti3_termList}" varStatus="num"><option value="${item.id}" name="${item.term}">${item.term}</option></c:forEach>' 
					 +'</select></div></div> </td>'
					 +'<td class="min-width"><div class="input-style-2"><input type="text" id="lecture_hours'+att+'" name="lecture_hours'+att+'"  maxlength="3" class="form-control"  autocomplete="off" placeholder="Lecture Hours" ></div></td>'
					 +'<td class="min-width"><div class="input-style-2"><input type="text" id="non_lecture_hours'+att+'" name="non_lecture_hours'+att+'"  maxlength="3" class="form-control"  autocomplete="off" placeholder="Non Lecture Hours" ></div></td>'
					 +'<td><ul class="buttons-group"><li value = "ADD" title = "ADD" id = "id_add_att'+att+'" ><a class="main-btn success-btn btn-hover btn-sm"><i class="lni lni-plus"></i></a></li><li value="REMOVE" title = "REMOVE" id = "att_id_remove'+att+'" ><a class="main-btn danger-btn btn-hover btn-sm"><i class="lni lni-trash-can"></i></a></li></td>'
		   		     +'</tr>');
				 
				 $("table#att_Tb").append('<input type="hidden" name="eid'+att+'" id="eid'+att+'"/>');
				     addOnclick(att);
				     removeOnclick(att);
				     getTopiclistbyCourse(att);
				     getTerm_listByProf(att);
		 
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
	document.getElementById('course_id').onchange = function() {
		getTopiclistbyCourse(1);
	};
	document.getElementById('system_id').onchange = function() {
		getdegreelistbysystem();
	};
	document.getElementById('degree_id').onchange = function() {
		getcourselistbydegree();
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
		alert("Please Select Subject .");
		$("select#course_id").focus();
		return false;
	}
	if ($("#paper_id").val().trim() == "0") {
		alert("Please Select Paper .");
		$("select#paper_id").focus();
		return false;
	}
	
	var count = $("#new_count_hidden").val();
	for(var i=1;i<=count;i++){
		
		if ($("#topic_id"+i).val().trim() == "0") {
			alert("Please Select Topic.");
			$("#topic_id"+i).focus();
			return false;
		}
		if ($("#term_id"+i).val().trim() == "0") {
			alert("Please Select Term.");
			$("select#term_id"+i).focus();
			return false;
		}
		if ($("#lecture_hours"+i).val().trim() == "") {
			alert("Please Enter Lecture Hours.");
			$("#lecture_hours"+i).focus();
			return false;
		}
		if ($("#non_lecture_hours"+i).val().trim() == "") {
			alert("Please Enter Non Lecture Hours.");
			$("#non_lecture_hours"+i).focus();
			return false;
		}
		return true;
	}
}
function addOnclick(index){
	document.getElementById('id_add_att'+index).onclick = function() {
		formopen_att(index);
	};
	document.getElementById('lecture_hours'+index).onkeypress = function() {
		return isNumberKey0to9(event,this);
	};
	document.getElementById('non_lecture_hours'+index).onkeypress = function() {
		return isNumberKey0to9(event,this);
	};
}
function removeOnclick(index){
	document.getElementById('att_id_remove'+index).onclick = function() {
		formopen_re_att(index);
	};
}
</script>