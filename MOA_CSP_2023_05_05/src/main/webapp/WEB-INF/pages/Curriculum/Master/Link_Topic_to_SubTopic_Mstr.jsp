<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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

<section class="dashboard-page">
	<div class="container-fluid">
	<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">

						<h2>
							<span id="lbladd"></span> Link Topic & Sub-Topic Master
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Link
									Topic & SubTopic Master</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<!-- title-wrapper end -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form name="link_topic_subtopicmstr"
						id="link_topic_subtopicmstr"
						action="link_topic_subtopicmstrAction" method="post"
						modelAttribute="link_topic_subtopicmstrCMD">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Link Topic & SubTopic Master</h6>
							<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="text-input">System<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="system_id" id="system_id">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getSystemList}"
													varStatus="num">
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
												<select class="singleselect form-control form-control-lg"
													name="degree_id" id="degree_id" class="form-control">
													<option value="0">--Select--</option>
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
										<label for="text-input">Subject<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="course_id" id="course_id">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${CourseList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Topic<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="topic_id" id="topic_id">
												<option value="0">--Select--</option>
											</select>
										</div>
									</div>
								</div>
								<!-- 								     TAKE REFERENCE FOR CHECKBOX-DD -->
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="text-input">Sub Topic <strong
											class="mandatory">* </strong></label>
										<div class="select-position">
											<select class="" id="div_iv_fluids" name="">
												<option value="0" id="subtopic_id1" class="hida overSelect">--Select--</option>
											</select> <input type="hidden" id="subtopic_id1" name="subtopic_id"
												autocomplete="off" class="form-control-sm form-control"
												value="">
										</div>
										<div id="div_iv_fluids_2" class="multiselect">
											<div id="div_cb_dd"
												class="form-check radio-style checkbox-style align-items-center">
												<!-- d-flex -->
											</div>
										</div>
									</div>
									<input type="hidden" id="in_sub_topic_hid"
										name="in_sub_topic_hid" class="form-control autocomplete">
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="status" id="status">
												<c:forEach var="item" items="${ActiveInActiveList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="input-style-1 mt-3">
										<input type="hidden" id="id" name="id" value="0"
											autocomplete="off" /> <input type="hidden" id="Edit_ids"
											name="Edit_ids" value="0" autocomplete="off" />
									</div>
								</div>
							</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
							<ul class="buttons-group mainbtn">
								<li>
						           <a id="btn-reload1" href="Link_Topic_SubTopic_Mstr_Url" class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i class="lni lni-chevron-left"></i>Back</a>
						        </li>
						        <li><input id="btn-update"
									class="main-btn deactive-btn btn-hover btnupda" type="button"
									value="Update" /></li>
								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover btnsave" type="button" value="Save" /></li>
								
								<li><a id="btn-reload2" href="Link_Topic_SubTopic_Mstr_Url"
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
		<div class="row" id="tbl">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_Topic_SubTopicMStr">
							<thead>
								<tr>
									<th id="1"><h6>Ser No</h6></th>
									<th id="7"><h6>System</h6></th>
									<th id="5"><h6>Degree</h6></th>
									<th id="3"><h6>Professional</h6></th>
									<th id="4"><h6>Subject</h6></th>
									<th id="5"><h6>Topic</h6></th>
									<th id="7"><h6>Sub Topic</h6></th>
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
		</section>
	</div>
	</div>
</section>

<c:url value="Topic_SubTopic_Mstr_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {

	$("#btn-update").hide();
	$("#btn-reload1").hide();
	
	mockjax1('search_Topic_SubTopicMStr');
	table = dataTable('search_Topic_SubTopicMStr');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$('.UpperClassName').keyup(function() {
		this.value = this.value.toUpperCase();
	});
	if(window.location.href.includes("msg"))
	{
		 var url = window.location.href.split("?msg")[0];
		 window.location = url;
	}
});

function data(search_Topic_SubTopicMStr) {
	
	jsondata = [];
	var table = $('#' + search_Topic_SubTopicMStr).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).attr('id')
			.toLowerCase();
	var orderType = order[0][1];
	var course_id = $("#course_id").val();
	var topic_id = $("#topic_id").val();
	var subtopic_id = $("#in_sub_topic_hid").val();
	var status = $("#status").val();
	var system_id = $("#system_id").val();
	var degree_id = $("#degree_id").val();
	var professional_id = $("#professional_id").val();

	$.post("getFilterTopic_SubTopicMStr_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		course_id : course_id,
		topic_id : topic_id,
		subtopic_id : subtopic_id,
		status : status,
		system_id : system_id,
		degree_id : degree_id ,
		professional_id : professional_id
		

	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].ser,j[i].system_name,j[i].degree_name,j[i].professional,j[i].course_name,j[i].topic, j[i].sub_topictxt, j[i].action ]);
		}
	});
	$.post("getTotalTopic_SubTopicMStr_dataCount?" + key + "=" + value, {
		Search : Search,
		course_id : course_id,
		topic_id : topic_id,
		subtopic_id : subtopic_id,
		status : status,
		system_id : system_id,
		degree_id : degree_id ,
		professional_id : professional_id
	}, function(j) {
		FilteredRecords = j;
	});
	setTimeout(setTimeLoadForTable, 1000);
}

function getTopiclistbyCourse() {
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
						$("select#topic_id").html(options);
					});
}


function getsubtopiclistbytopic() {
	var topic_id = $("#topic_id").val();
	$.post('getsubtopiclistby_topic?' + key + "=" + value, {
				topic_id : topic_id
			}).done(function(j) {
				$("div#div_cb_dd").empty();
						for(var p=0;p<j.length;p++){
							var q = p+1;
						
						$("div#div_cb_dd")
						.append(
							'<input class="multi form-check-input mr-5" type="checkbox" id="subTopic_id'+q+'" name="subTopic_id" value="'+j[p][0]+'"/>'
							+'<label class="lbl"  for="first">'+j[p][1]+'</label>');
						setonclickofCBDD(q);
						}
			});
}

function checkoptioninedit(sids){
	var len = "";
	
	var topic_id = $("#topic_id").val();
	$.post('getsubtopiclistby_topic?' + key + "=" + value, {
				topic_id : topic_id
			}).done(function(j) {
				
				len = j.length;
				
				$("div#div_cb_dd").empty();
						for(var p=0;p<len;p++){
							var q = p+1;
						
						$("div#div_cb_dd")
						.append(
							'<input class="multi form-check-input mr-5" type="checkbox" id="subTopic_id'+q+'" name="subTopic_id" value="'+j[p][0]+'"/>'
							+'<label class="lbl"  for="first">'+j[p][1]+'</label>');
						setonclickofCBDD(q);
						}
			});
	
	var match = sids.split(":");
	for (var a in match)
	{
	    var variable = match[a];
	    	for(var i = 1;i<= len ; i++){
				var temp_data = $('#subTopic_id' + i).val();
				if(variable.trim() == temp_data.trim()){
					$('#subTopic_id' + i).click();
				}
	    	}
	}
}

function mycheckindex(myindex) {
	var gsida = [];
	var ele = document.getElementsByName("subTopic_id");

	console.log("ele.length - " + ele.length);
	for (var i = 0; i < ele.length; i++) {
		if (ele[i].checked) {
			/* gsida.push(gsid[i].value); */
			gsida.push(ele[i].value);
			/* remarksa.push(remarks[i].value); */
		}
	}
	console.log(myindex);
	document.getElementById('in_sub_topic_hid').value = gsida
			.toString();
}

function getdegreelistbysystem111() {
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


function setonclickofCBDD(obj){
			document.getElementById('subTopic_id'+obj).onclick = function() {
				mycheckindex(obj);
			};
}

document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('course_id').onchange = function() {
		getTopiclistbyCourse();
	};
	document.getElementById('div_iv_fluids').onclick = function() {
		showCheckboxes(this);
	};
	document.getElementById('topic_id').onchange = function() {
		getsubtopiclistbytopic();
	};
	document.getElementById('btn-save').onclick = function() {
		if(Validation()){
			 $("#link_topic_subtopicmstr").submit();
		}
	};
	document.getElementById('btn-update').onclick = function() {
		UpdateFn();
	};
	document.getElementById('system_id').onchange = function() {
		getdegreelistbysystem111();
	};
	document.getElementById('professional_id').onchange = function() {
		getcourselistby_professional();
	};
	
});

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

	function setTimeLoadForTable(){
		
		document.querySelectorAll('.ADDSubTopic').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				var hid = document.getElementById('apId'+val).value;
				var hsid = document.getElementById('approsAGE'+val).value;
				var hprodegree= document.getElementById('hprodegree'+val).value;
				var hidprofessional= document.getElementById('hidprofessional'+val).value;
				var hcourse = document.getElementById('apco'+val).value;
				var ht = document.getElementById('apt'+val).value;
				var hst = document.getElementById('apst'+val).value;
				var hstatus = document.getElementById('apstatus'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,hsid,hprodegree,hidprofessional,hcourse,ht,hst,hstatus);
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
	
	function editData(id,system_id,degree_id,professional_id,course_id,topic_id,subtopic_id,status) {
		$.ajaxSetup({
			async : false
		});
		$("#btn-update").show();
		$("#btn-save").hide();
		$("#tbl").hide();
		$("#btn-reload").hide();
		$("#btn-reload2").hide();
		$("#btn-reload1").show();
		document.getElementById('lbladd').innerHTML = "Update ";
		$("select#system_id").val(system_id);
		$('#system_id').trigger('change');
		getdegreelistbysystem111();
		$("select#degree_id").val(degree_id);
		$("select#professional_id").val(professional_id);
		$('#professional_id').trigger('change');
		$("select#course_id").val(course_id);
		$('#course_id').trigger('change');
		getTopiclistbyCourse();
		$("select#topic_id").val(topic_id);
		getsubtopiclistbytopic();
		checkoptioninedit(subtopic_id);
		$("select#status").val(status);
		$("#Edit_ids").val(id);
		document.getElementById('id').value=id;
	}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
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
		
		if ($("#topic_id").val().trim() == "0") {
			alert("Please Select Topic.");
			$("select#topic_id").focus();
			return false;
		}
		if ($("#in_sub_topic_hid").val().trim() == "") {
			alert("Please Select Sub Topic.");
			$("select#in_sub_topic_hid").focus();
			return false;
		}
		if ($("select#status").val() == "0") {
			alert("Please Select Status.");
			$("select#status").focus();
			return false;
		}
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
		return true;
	}
	
	function UpdateFn(){
		var course_id = $("#course_id").val();
		var topic_id = $("#topic_id").val();
		var st_old = $("#Edit_ids").val();
		var st_new = $("#in_sub_topic_hid").val();
		var status = $("#status").val();
		var system_id = $("#system_id").val();
// 		var degree_id = $("#degree_id").val();
// 		var professional_id = $("#professional_id").val();
		
		$.post('updateSubTopicAction?' + key + "=" + value, {
			course_id : course_id,
			topic_id : topic_id,
			st_old : st_old,
			st_new : st_new,
			status : status,
			system_id : system_id
// 			degree_id : degree_id,
// 			professional_id : professional_id

		}).done(function(j) {
			if(j == "Data Updated Successfully"){
				alert(j);
				window.location.reload();
			}else{
				alert(j);
			}
		});
	}
</Script>
