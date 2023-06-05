<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- <script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script>	 -->
<script src="js/common/multicheck.js" type="text/javascript"></script>
<!-- <link rel="stylesheet" href="js/common/multicheck.css"> -->
<!-- datatable style and js start-->
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
						<h2><span id="lbladd"></span> Add Topic Wise Marks</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Add Topic Wise Marks</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form name="define_topic_wise_marks" id="define_topic_wise_marks" action="define_topic_wise_marksAction"
						method="post" modelAttribute="define_topic_wise_marksCMD">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Add Topic Wise Marks</h6>
							<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="text-input">System<span
											class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="system_id" id="system_id">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getSystemList}" varStatus="num">
													<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<!-- end select -->
								</div>
							  <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Degree<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="degree_id" id="degree_id">
											<option value="0">--Select--</option>
<%-- 												<c:forEach var="item" items="${getDegreeList}" --%>
<%-- 													varStatus="num"> --%>
<%-- 													<option value="${item.id}" name="${item.degree_name}">${item.degree_name}</option> --%>
<%-- 												</c:forEach> --%>
											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Professional<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="professional_id" id="professional_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getprofessionalList}"
														varStatus="num">
														<option value="${item.id}" name="${item.professional}">${item.professional}</option>
													</c:forEach>
												</select>
											</div>
										</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Subject<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="course_id" id="course_id">
											<option value="0">--Select--</option>
											</select>
										</div>
									</div>
								</div>
<!-- 								     TAKE REFERENCE FOR CHECKBOX-DD -->
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Marks<span class="mandatory">*</span></label> <input
											type="text" id="marks" name="marks"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="3" placeholder="Marks" />
									</div>
							</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1 mb-0">
											<label><span id=topic_name>Topic</span><span class="mandatory">*</span></label>
											 <input type="text" id="search_data"  class=" " autocomplete="off" placeholder="Search Topic">
										</div>
										<div class="col-12 mb-30" id="checkboxes">
										<c:forEach var="item" items="${getTopicListbyCourse}" varStatus="num">
											<div class="form-check radio-style checkbox-style d-flex align-items-center">
												<input class="form-check-input mr-5" type="checkbox" name="multisub" value="${item[0]}"  />
												<label for="one" class="d-flex align-items-center">
													${item[1]} </label>
											</div>
										</c:forEach>
									</div>
								</div>
<!-- 								<div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
<!-- 									<div class="input-with-selection"> -->
<!-- 										<div class="input-style-1 mb-0"> -->
<!-- 											<label>Selected Topic<span -->
<!-- 												class="mandatory">*</span></label> <input type="text" id="value" -->
<!-- 												name="value" maxlength="70" placeholder="Selected Topic" /> -->
<!-- 										</div> -->
<!-- 										<div class="badges-groups"> -->
<!-- 											<ul id="show_list" class="buttons-group"> -->
<!-- 											</ul> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-with-selection">
										<div class="input-style-1 mb-0">
											<label>Selected Topic<span
												class="mandatory">*</span></label> <input type="text" id="value"
												name="value" maxlength="70" placeholder="Selected Topic" />
										</div>
										<div class="badges-groups">
											<ul id="show_list" class="buttons-group">
											</ul>
										</div>
									</div>
										
										<input type="hidden" name="old_course_topic" id="old_course_topic" />
										<input type="hidden" name="new_course_topic" id="new_course_topic" />
										<input type="hidden" name="add_course_topic" id="add_course_topic" />
										<input type="hidden" name="remove_course_topic" id="remove_course_topic" />
										
								</div>
								<input type="hidden" id="id" name="id" value="0" >
							    <input type="hidden" id="Edit_ids" name="Edit_ids" value="0" 
										autocomplete="off" /> 
							</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
							<ul class="buttons-group mainbtn">
							
							<li id="btn-reload1"> <a href="define_topic_wise_mark_url" class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
					             <i class="lni lni-chevron-left"></i>Back</a></li>
					            
					            <li id="btn-update"><input class="main-btn deactive-btn btn-hover btnupda" type="button" value="Update" /></li>
					             
								<li id="btn-reload"><a
									class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
									
								<li id="btn-save"><input class="main-btn info-btn btn-hover btnsave" type="button" value="Save" /></li>
								
								<li  id="btn-reload2"><a href="define_topic_wise_mark_url"
									class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
									
							</ul>
							</div>
							</div>
							</div>
							<!-- Bottom Button End -->
						</div>
					</form:form>
				</div>
			</div>
			<section class="single-detail-block">
			<div  id="tbl">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_define_topic_wise_marks">
							<thead>
								<tr>
									<th id="1"><h6>Sr No</h6></th>
									<th id="3"><h6>System</h6></th>
									<th id="9"><h6>Degree</h6></th>
									<th id="9"><h6>Professional</h6></th>
									<th id="10"><h6>Subject</h6></th>
									<th id="7"><h6>Marks</h6></th>
									<th id="11"><h6> Topic</h6></th>
									<th><h6>Action</h6></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		</div>
		</section>
		</div>
	</div>
</section>

<c:url value="delete_define_topic_wise_mark_Url" var="deleteUrl" /> 
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
 	<input type="hidden" name="id1" id="id1" value="0" /> 
</form:form> 

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {

	$("#btn-update").hide();
	$("#btn-reload1").hide();
	
	initiateChkFn('new_course_topic', 'old_course_topic',
			'add_course_topic', 'remove_course_topic',
			'Topic', 'topic_name');
	
	mockjax1('search_define_topic_wise_marks');
	table = dataTable('search_define_topic_wise_marks');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$('.UpperClassName').keyup(function() {
		this.value = this.value.toUpperCase();
	});
});

function deleteData(id) {
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}
function getcourselistby_professional() {
	var degree_id = $("#degree_id").val();
	var professional_id = $("#professional_id").val();
	
	$.post('getCourseList_for_Curri?' + key + "=" + value,{  
		degree_id : degree_id,
		professional_id : professional_id
		}).done(function(j) {
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#course_id").html(options);
	});
}
function getTopicListby_Course(course_id) {
	$('div#checkboxes').empty();
	$
			.post('getTopicListbyCourse?' + key + "=" + value, {
				course_id : course_id
			})
			.done(
					function(data) {
						
						var options = '';
						for (var i = 0; i < data.length; i++) {
							options += '<label for="one"  class="chklist"> <input class="customeCheck" type="checkbox" name="multisub"  id="multisub'
									+ data[i][0]
									+ '" value='
									+ data[i][0]
									+ '  onclick="chkClick()"/>'
									+ data[i][1]
									+ '</label>';
						}
						$("div#checkboxes").append(options);
						for (var i = 0; i < data.length; i++) {
							chbonclick(data[i][0]);
						}
			}).fail(function(xhr, textStatus, errorThrown) {
	});
}

document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn-save').onclick = function() {
		if(Validation()){
			 $("#define_topic_wise_marks").submit();
		}
	};
	document.getElementById('system_id').onchange = function() {
		getdegreelistbysystem();
	};

	document.getElementById('professional_id').onchange = function() {
		
		getcourselistby_professional();
	};
	document.getElementById('course_id').onchange = function() {
		getTopicListby_Course(this.value);
	};
	document.getElementById('search_data').onkeyup = function() {
		fnFilterChk(this.value);
	};
	
	document.getElementById('btn-update').onclick = function() {
		UpdateFn();
	};
	document.getElementById('marks').onkeypress = function() {
		return isNumberKey0to9(event,this);
	};
});
function chbonclick(obj){
	document.getElementById('multisub'+obj).onclick = function() {
		 chkClick();
	};
}
function chxboxOnclick(ser){
 	document.getElementById('multisub'+ser).onclick = function(){
 		chkClick();
	};
}
function mycheckindex(myindex) {
	var gsida = [];
	var ele = document.getElementsByName("topic_name");

// 	console.log("ele.length - " + ele.length);
	for (var i = 0; i < ele.length; i++) {
		if (ele[i].checked) {
			/* gsida.push(gsid[i].value); */
			gsida.push(ele[i].value);
			/* remarksa.push(remarks[i].value); */
		}
	}
// 	alert(gsida.toString());
	document.getElementById('add_course_topic').value = gsida.toString();
	document.getElementById('remove_course_topic').value = gsida.toString();
	document.getElementById('old_course_topic').value = gsida.toString();
	document.getElementById('new_course_topic').value = gsida.toString();
}
function data(search_define_topic_wise_marks) {
	
	jsondata = [];
	var table = $('#' + search_define_topic_wise_marks).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).attr('id')
			.toLowerCase();
	var orderType = order[0][1];
	var system_id = $("#system_id").val();
	var degree_id = $("#degree_id").val();
	var professional_id = $("#professional_id").val();
	var course_id = $("#course_id").val();
	var marks = $("#marks").val();
	var topic_name = $("#new_course_topic").val();
	$.post("getFilterdefine_topic_wise_marks_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		system_id:system_id,
		degree_id : degree_id,
		professional_id : professional_id,
		course_id : course_id,
		marks : marks,
		topic_name : topic_name

	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].ser,j[i].system_name,j[i].degree_name,j[i].professional, j[i].course_name,j[i].marks,j[i].topic, j[i].action ]);
		}
	});
	$.post("getTotaldefine_topic_wise_marks_dataCount?" + key + "=" + value, {
		Search : Search,
		system_id:system_id,
		degree_id : degree_id,
		professional_id : professional_id,
		course_id : course_id,
		marks : marks,
		topic_name : topic_name
	}, function(j) {
		FilteredRecords = j;
	});
	setTimeout(setTimeLoadForTable, 1000);
}

function setTimeLoadForTable(){
	
	document.querySelectorAll('.ADDDefineTopic').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apId'+val).value;
			var hprof = document.getElementById('appsys'+val).value;
			var pd = document.getElementById('apd'+val).value;
			var pf = document.getElementById('apf'+val).value;
			var pc = document.getElementById('apc'+val).value;
			var tm = document.getElementById('aptm'+val).value;
			var st = document.getElementById('apst'+val).value;
			var cids = document.getElementById('apcids'+val).value;
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				editData(hid,hprof,pd,pf,pc,tm,st,cids);
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
		alert("Please Select Professional.");
		$("select#professional_id").focus();
		return false;
	}
	if ($("#course_id").val().trim() == "0") {
		alert("Please Select Subject.");
		$("select#course_id").focus();
		return false;
	}
	if ($("#marks").val().trim() == "") {
		alert("Please Enter Marks.");
		$("input#marks").focus();
		return false;
	}
	if ($("#new_course_topic").val().trim() == "0") {
		alert("Please Select Topic.");
		$("select#new_course_topic").focus();
		return false;
	}
	return true;
}
function Validation_Edit() {
	

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
		alert("Please Select Professional.");
		$("select#professional_id").focus();
		return false;
	}
	if ($("#course_id").val().trim() == "0") {
		alert("Please Select Subject.");
		$("select#course_id").focus();
		return false;
	}
	if ($("#marks").val().trim() == "") {
		alert("Please Enter Marks.");
		$("input#marks").focus();
		return false;
	}
	if ($("#old_course_topic").val().trim() == "") {
		alert("Please Select Topic.");
		$("select#old_course_topic").focus();
		return false;
	}
	if ($("#new_course_topic").val().trim() == "") {
		alert("Please Select Topic.");
		$("select#new_course_topic").focus();
		return false;
	}
	return true;
}


function editData(id,system_id,degree_id,professional_id,course_id,marks,topic_id,cids) {
	$("#btn-update").show();
	$("#btn-save").hide();
	$("#tbl").hide();
	$("#btn-reload").hide();
	$("#btn-reload2").hide();
	$("#btn-reload1").show();
	document.getElementById('lbladd').innerHTML = "Update ";
	$("select#system_id").val(system_id);
	$('#system_id').trigger('change');
	getdegreelistbysystem();
	$("select#degree_id").val(degree_id);
	getcourselistbydegree();
	$("select#professional_id").val(professional_id);
	$('#professional_id').trigger('change');
	getcourselistby_professional();
	$("select#course_id").val(course_id);
	$("#marks").val(marks);
	getTopicListby_Course(course_id);
	$("#Edit_ids").val(id);
	document.getElementById('id').value=id;
	setChk(topic_id.split(":"));

}

function cbedit(vals) {
	$.ajaxSetup({
		async : false
	});
	var vx = vals.split(':');
	$("input[type='checkbox'][name='multisub']").attr('checked', false);
	for(var i=0;i<vx.length;i++){
		$("input[type='checkbox'][name='multisub'][value='"+vx[i]+"']").click();
	}
}

function UpdateFn(){
	
	var id = $("#id").val();
	var system_id =$("#system_id").val();
	var degree_id = $("#degree_id").val();
	var professional_id = $("#professional_id").val();
	var course_id = $("#course_id").val();
	var marks = $("#marks").val();
	var add_course_topic = $("#add_course_topic").val();
	var remove_course_topic = $("#remove_course_topic").val();
	var old_course_topic = $("#old_course_topic").val();
	var new_course_topic = $("#new_course_topic").val();
	
	
	if($("#system_id").val() == "0" || $("#system_id").val() == ""){
		alert("Please Select System.");
		$("#system_id").focus();
		return false;
	}
	if ($("#degree_id").val().trim() == "0") {
		alert("Please Select Degree.");
		$("select#degree_id").focus();
		return false;
	}
	if ($("#course_id").val().trim() == "0") {
		alert("Please Select Course.");
		$("select#course_id").focus();
		return false;
	}
	if ($("#professional_id").val().trim() == "0") {
		alert("Please Select Professional.");
		$("select#professional_id").focus();
		return false;
	}
	if ($("#marks").val().trim() == "") {
		alert("Please Enter Marks.");
		$("input#marks").focus();
		return false;
	}
	if ($("#old_course_topic").val().trim() == "") {
		alert("Please Select Topic.");
		$("select#old_course_topic").focus();
		return false;
	}
	if ($("#new_course_topic").val().trim() == "") {
		alert("Please Select Topic.");
		$("select#new_course_topic").focus();
		return false;
	}
	
	$.post('updatedefine_topic_wise_marksAction?' + key + "=" + value, {
		id : id,
		system_id:system_id,
		degree_id : degree_id,
		professional_id : professional_id,
		course_id : course_id,
		marks : marks,
		add_course_topic : add_course_topic,
		remove_course_topic : remove_course_topic,
		old_course_topic : old_course_topic,
		new_course_topic : new_course_topic
		
	}).done(function(j) {
		if(j == "Data Updated Successfully"){
			alert(j);
			window.location.reload();
		}else{
			alert(j);
		}
	});
}
function chxboxOnclick(ser){
 	document.getElementById('multisub'+ser).onclick = function(){
 		chkClick();
	};
}




</script>