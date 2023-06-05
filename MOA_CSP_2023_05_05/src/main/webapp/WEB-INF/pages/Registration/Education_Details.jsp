<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link href="js/tunneldesign/tunnelold.css" rel="Stylesheet"></link>
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
							<span id="lbladd1"></span> Basic Education Details
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page"><span
									id="lbladd2"></span> Basic Education Details</li>
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
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="tunnel_design">
							<div class="square tunnel_visited">
								<a href="#" class="tunnel_text" id="tunnel_1"> Personal
									Details</a>
							</div>
							<div class="square tunnel_active" id="tunnel_2">
								<h5 class="tunnel_text">Basic Education Details</h5>
							</div>
							<div class="square ">
								<a href="#" class="tunnel_text" id="tunnel_3">Upload
									Document</a>
							</div>
							<div class="square">
								<a href="#" class="tunnel_text" id="tunnel_4">Declaration</a>
							</div>
						</div>
					</div>
					<form:form name="Edu_Det_Action" id="Edu_Det_Action"
						action="Edu_Det_Action?${_csrf.parameterName}=${_csrf.token}"
						method='POST' modelAttribute="Edu_Det_CMD"
						enctype="multipart/form-data">
					
						<div class="card-style mb-30">
							<section class="detail-block">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="custom-data-value custom-title custom-title-bg">
											<h5 class="custom-title-tag"><span id="lbladd3"></span> Education Details</h5>
										</div>
										<div class="inst_block simple-instruction">
											<strong>Instruction :</strong> 10th and 12th Result Details
											are Mandatory.
										</div>
									</div>
									<div class="col-lg-12 col-sm-12">
										<div class="table-wrapper table-responsive simple-table">
											<table class="table" id="att_Tb">
												<thead>
													<tr>
														<th><h6>
																Name of Exam<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>
																Board/University<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>
																School/College<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>
																Subject<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>
																Year of Passing<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>
																Percentage of Marks<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>
																Division<span class="mandatory"> *</span>
															</h6></th>
														<th><h6>
																Upload Document<span class="mandatory"> *</span>
															</h6> <span class="table-note">[Maximum file size upto
																200 kb And Accept Only .pdf File]</span></th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td class="select-wrapper">
															<div class="select-style-1">
																<div class="select-position">
																	<select id="academic" name="academic">
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${getAcademicList}"
																			varStatus="num">

																			<c:if test="${item.id<15}">
																				<option value="${item.id}">${item.academic}</option>
																			</c:if>

																		</c:forEach>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="input-style-1">
																<input id="board_or_university"
																	name="board_or_university" value="" autocomplete="off"
																	placeholder="Enter Board or University" maxlength="70" />
																<div class="note-text">
																	<span class="errorClass" id='board_or_university_lbl'></span>
																</div>
															</div>
														</td>
														<td>
															<div class="input-style-1">
																<input id="school_or_collage" name="school_or_collage"
																	placeholder="Enter School or Collage" value=""
																	autocomplete="off" maxlength="70" />
																<div class="note-text">
																	<span class="errorClass" id='school_or_collage_lbl'></span>
																</div>
															</div>
														</td>
														<td>
															<div class="input-style-1">
																<input id="subject" name="subject" value=""
																	placeholder="Enter Subject" autocomplete="off"
																	maxlength="50" />
																<div class="note-text">
																	<span class="errorClass" id='subject_lbl'></span>
																</div>
															</div>
														</td>
														<td class='select-wrapper'>
															<div class="select-style-1">
																<div class="select-position">
																	<input type="hidden" id="" value="">
																	<form:select id="passing_year" path="passing_year">
																		<option value="0">--Select--</option>
																	</form:select>
																</div>
															</div>
														</td>
														<td>
															<div class="input-style-1">
																<form:input id="obtain_marks" path="obtain_marks"
																	placeholder="Enter Percentage" value=""
																	autocomplete="off" maxlength="5" />
																<div class="note-text">
																	<span class="errorClass" id='marks_obtained_lbl'></span>
																</div>
															</div>
														</td>
														<td class="select-wrapper">
															<div class="select-style-1">
																<div class="select-position">
																	<select id="grade" name="grade">
																		<option value="" selected="selected">--
																			Select --</option>
																			<c:forEach var="item" items="${getresultDivisionList}"	varStatus="num">
																				<option value="${item.id}">${item.division_name}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="input-style-1">
																<input type="file" accept=".pdf" id="doc_path"
																	name="doc_path" class="form-control"> <input
																	type="hidden" id="doc_path_hid" name="doc_path_hid"
																	class="form-control" value="" />
																<div class="note-text">
																	<span class="errorClass" id="document_lbl1"></span> <span
																		class='tikClass' id="document_lbltik"></span>
																</div>
															</div>
														</td>
													</tr>
												</tbody>
											</table>
											<!-- Hidden Start -->
											<input type="hidden" name="p_id" id="p_id" value="0" /> <input
												type="hidden" name="tbpdid" id="tbpdid" value="0" /> <input
												type="hidden" name="id_org" id="id_org" value="0" /> <input
												type="hidden" id="education_id_form"
												name="education_id_form" value="0">
											<!-- Hidden End -->
										</div>
										<!-- Hidden Start -->
										<input type="hidden" id="count_hidden_att"
											name="count_hidden_att" class="form-control autocomplete"
											value="1"> <input type="hidden" id="jlength"
											name="jlength" class="form-control autocomplete" value="0">
										<!-- Hidden End -->
									</div>
								</div>
							</section>

							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a id="aIdPrevious" href="Personal_Details_Url"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i
													class="lni lni-chevron-left"></i> Previous</a></li>
											<li><input type="submit" id="save_btn"
												class="main-btn info-btn btn-hover btnsave" value="Save"></li>
											<li><a href="Edu_Det_Url"
												class="main-btn dark-btn btn-hover btnreset" id="clear_btn">Reset</a></li>
											<li><a id="aIdNext"
												class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left btnnext">Next<i
													class="lni lni-chevron-right"></i></a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>

						<!-- end card -->
					</form:form>
				</div>
			</div>

			<section class="single-detail-block">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-datatable-p"
								id="qualification_tbl_div">
								<table class="table" id="search_education_details_table">
									<thead>
										<tr>
											<th><h6>Sr No</h6></th>
											<th><h6>Name of Exam</h6></th>
											<th><h6>Board/University</h6></th>
											<th><h6>School/College</h6></th>
											<th><h6>Subject</h6></th>
											<th><h6>Year of Passing</h6></th>
											<th><h6>Percentage of Marks</h6></th>
											<th><h6>Division</h6></th>
											<th><h6>Download</h6></th>
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
<c:url value="getDownloadPdfUrlforeduDoc" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="getDownloadPdfForm"
	name="getDownloadPdfForm" modelAttribute="doc_id1">
	<input type="hidden" name="pageUrl" id="pageUrl"
		value="redirect:Edu_Det_Url" />
	<input type="hidden" name="doc_id1" id="doc_id1" value="" />
</form:form>

<c:url value="delete_education" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Personal_Details_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm"
	name="applicationUrlForm" modelAttribute="pers_details_hid">
</form:form>

<c:url value="Edu_Det_Url" var="mainFormUrl1" />
<form:form action="${mainFormUrl1}" method="GET" id="mainForm11"
	name="mainForm11" modelAttribute="eid">
	<input type="hidden" name="eid" id="eid" value="0" />
</form:form>

<c:url value="Total_Exp_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm4"
	name="applicationUrlForm4" modelAttribute="tp_eid">
	<input type="hidden" name="tp_eid" id="tp_eid" value="0" />
</form:form>

<c:url value="doc_uploadUrl" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm5"
	name="applicationUrlForm5" modelAttribute="doc_eid">
	<input type="hidden" name="doc_eid" id="doc_eid" value="0" />
</form:form>

<c:url value="Reshuffling_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm6"
	name="applicationUrlForm6" modelAttribute="ch_eid">
	<input type="hidden" name="ch_eid" id="ch_eid" value="0" />
</form:form>

<script nonce="${cspNonce}">

$(document).ready(function () {
	
	var index = '${getAcademicList.size()}';
	$("#academic").find('[value="'+index+'"]').remove();
	 $("#tbpdid").val('${tbpdid}');
	 //$("#p_id").val('${tbpdid}');

//  var msg = '${msg}';
// 	if (msg != "")
// 			{
// 			alert(msg);
// 			}
// 	 try{
// 	 	   if(window.location.href.includes("msg="))
// 	 		{
// 	 			var url = window.location.href.split("?")[0];
// 	 			window.location.href=url;
// 	 		} 	
// 	 	}
// 	 	catch (e) {
// 	 	} 
inyear();

mockjax1('search_education_details_table');
	table = dataTable('search_education_details_table');
	$('#srch').on('click', function(){	
		
    	table.ajax.reload();
    });
	
	//getPerDetails();
	get_p_id_pers_info();
});

function get_p_id_pers_info() {
	
	$.ajaxSetup({
		async : false
	});
	
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	var userid =  "${userid}";
	$.post('get_p_id_pers_info_ctrl?' + key + "=" + value,{userid : userid},function(j) {
		//alert(j)
		$("#p_id").val(j[0][0]);
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
}


/////////personal_details  from registration table/////Start
function getPerDetails() {
	
	
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	var userid =  "${userid}";
	
	$.post('getPersonaldetails?' + key + "=" + value,{userid : userid},function(j) {
		
		alert(j);
		
			$("#p_id").val(j[0][10]);
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	
	}
/////////////End	
	
function getDownloadPdfEdu(id){   

	$("#doc_id1").val(id); 
	document.getElementById("getDownloadPdfForm").submit();
} 

function setValueOfNotificationPath() {
	
	 if ($("#doc_path").val() != "" || $("#doc_path").val() != null) {
			var doc_path_hid=$("#doc_path").val();
			document.getElementById("doc_path_hid").value=doc_path_hid;
		}
		else{
			document.getElementById("doc_path_hid").value="";
		}
}

function deleteData(id){

		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
}
		
function editData(id){
	$.post("geteditEducation_data_ctrl?" + key + "=" + value, {id:id}, function(j) {
		$("#id").val(id);
		$("select#academic").val(j[0][1]);
		$("input#board_or_university").val(j[0][2]);
		$("input#school_or_collage").val(j[0][3]);
		$("input#subject").val(j[0][4]);
		$("select#passing_year").val(j[0][5]);
		$("input#obtain_marks").val(j[0][6]);
		$("select#grade").val(j[0][7]);
		$("input#doc_path_hid").val(j[0][8]);
		document.getElementById('id_org').value=id;
	});
		document.getElementById('lbladd1').innerHTML = "Update ";
		document.getElementById('lbladd2').innerHTML = "Update ";
		document.getElementById('lbladd3').innerHTML = "Update ";
		document.getElementById('save_btn').value="Update";
		document.getElementById('save_btn').className ='main-btn deactive-btn btn-hover';
// 		document.getElementById('butval1').className = 'butzz'
}

function inyear() {
	 var currentYear = new Date().getFullYear()
	    max = currentYear 
	    var option = "";
	    for (var year = currentYear-10 ; year <= max; year++) {
	        var option = document.createElement("option");
	        option.text = year;
	        option.value = year;	        
	        document.getElementById("passing_year").appendChild(option)	        
	    }
	   // document.getElementById("passing_year").value = currentYear;	
}

function data(search_education_details_table) {
		jsondata = [];
		var table = $('#' + search_education_details_table).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var academic = $("#academic").val();
		var passing_year = $("#passing_year").val();
		var institute_name = $("#institute_name").val();
		var obtain_marks = $("#obtain_marks").val();
		var total_marks = $("#total_marks").val();
		var grade = $("#grade").val();
		var doc_path = $("#doc_path").val();
		
		$.post("getFilterEducation_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			academic:academic,
			passing_year:passing_year,
			institute_name:institute_name,
			obtain_marks:obtain_marks,
			total_marks:total_marks,
			grade:grade,
			doc_path:doc_path
			
		}, function(j) {
			if (j.length == 0) {
				$("#jlength").val('1');
			}
			
			
		for (var i = 0; i < j.length; i++) {
		
		jsondata.push([j[i][0],j[i][1],j[i][7],j[i][8],j[i][9],j[i][2],j[i][4],j[i][6],j[i][10],j[i][11]]);
		
		}
	});

		$.post("getTotalEducation_dataCount?" + key + "=" + value, {Search:Search}, function(j) {
			FilteredRecords = j;
		});
		setTimeout(setTimeLoadForTable, 500);
// 		setTimeout(setremovehide, 500);
	}

// function setremovehide(){
// $('#download').removeClass('sorting');
// $('#download').addClass('sorting_disabled no_sorting');
// }
// sorting_disabled
	
	
function setTimeLoadForTable(){
	document.querySelectorAll('.addAPP').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('upDOWN'+val).value;
			if (confirm('Are You Sure You Want to Edit This?')) {
				editData(hid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.removeAPP').forEach((items, index) => {
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
	
	document.querySelectorAll('.eddownload').forEach((items, index) => {
		items.addEventListener('click', event => {
			debugger;
			var val=parseInt(index)+1;
			var hid = document.getElementById('dlID'+val).value;
			if (confirm('Are You Sure You Want to Download This Education Details ?')) {
				getDownloadPdfEdu(hid);
			} else {
				return false;
			}
		})
	});
}
	
	
	
function clear_field(){
	
	$("#academic").val('0').change();
// 	document.getElementById("document_lbltik").innerHTML=""; 
	 $("select#passing_year").val("0");
	 $("#subject").val("");
	 $("#obtain_marks").val("");
     $("#total_marks").val("");
     $("select#grade").val("0");
     $("#doc_path").val("");
     $("#board_or_university").val("");
     $("#school_or_collage").val("");
	$("#education_id_form").val("");
	$("input#doc_path_hid").val("");
	
	document.getElementById("document_lbltik").innerHTML=""; 
	document.getElementById('save_btn').value="Save";
	document.getElementById('save_btn').className = 'main-btn info-btn btn-hover';
// 	document.getElementsByClassName('fa fa-pencil-square-o')[0].style.visibility = 'hidden';
// 	document.getElementsByClassName('fa fa-save')[0].style.visibility = 'visible';
	
}

function count_percentage(){
	
	document.getElementById("marks_obtained_lbl").innerHTML="";
	document.getElementById("total_marks_lbl").innerHTML="";
	var total = parseFloat($('#total_marks').val()); 
 	var obtained = parseFloat($('#obtain_marks').val());
 	
 	 if(obtained == "0" || obtained == "")
	    {
	        document.getElementById("marks_obtained_lbl").innerHTML="<i class='fa fa-exclamation'></i>Please Enter Obtained Marks";
	        return false;
			
	    }
	    if(total == "0" || total == "")
	    {
	       document.getElementById("total_marks_lbl").innerHTML="<i class='fa fa-exclamation'></i>Please Enter Total Marks";
	       return false;
	    }
 	
//  	 var perc="";
//  	 if(obtained <= total){
//     	  if(total == "0" || obtained == "0"){
//               perc="0";
//          }
//     	 else{
//     		 perc = ((obtained/total) * 100).toFixed(2);
//          }
//      	$('#pers_edu_percentage').val(perc);
       
//       }
//       else 
	
    	  if( total<=obtained ) {
     	document.getElementById("marks_obtained_lbl").innerHTML="<i class='fa fa-exclamation'></i>Marks Obtained should be less then Total Marks";
  //   	$('#pers_edu_percentage').val("0");
     	$('#total_marks').val("0");
     	$('#obtain_marks').val("0");
     }
}
	
function isValidateClientSide(){
	
	if ($("select#academic").val().trim() == "" || $("select#academic").val().trim() == "0") {
		alert("Please Select Name of Exam");
		$("select#academic").focus();
		return false;
	}
	
	 if ($("#board_or_university").val().trim() == "") {
			alert("Please Enter the Board/University");
			$("#board_or_university").focus();
			return false;
		} 
	 
	 if ($("#school_or_collage").val().trim() == "") {
			alert("Please Enter the School/College");
			$("#school_or_collage").focus();
			return false;
		} 
		
	if ($("#subject").val().trim() == "") {
			alert("Please Enter the Subject");
			$("#subject").focus();
			return false;
		}
	
	if ($("select#passing_year").val().trim() == "" || $("select#passing_year").val().trim() == "0"){
		alert("Please Select Year of Passing");
		 $("select#passing_year").focus();
		return false;
	}

// 	if ($("#institute_name").val().trim() == "") {				
// 		alert("Please Enter Institute Name");
// 		 $("#institute_name").focus();
// 		return false;
// 	}
	
// 	var maxLength = 50;
// 	 var charLength = $("input#institute_name").val();
	
//    if(charLength > maxLength){
//    	alert("Please Enter Institute Name should be less then 50 Characters");
// 			$("input#institute_name").focus();
// 			return false;
//    }  
   
//    if ($("#institute_name").val() == "") {				
// 		alert("Please Enter Institute Name");
// 		 $("#institute_name").focus();
// 		return false;
// 	}
   
//    var minlength = 3;
// 	 var charLength = $("input#institute_name").val().length;
	
//        if(charLength < minlength){
//        		alert("Please Enter Institute Name should not be less then 3 Characters");
// 			$("input#institute_name").focus();
// 			return false;
//        }
	
	 var om =  $("#obtain_marks").val().trim()
	
       if (om == "") {				
   		alert("Please Enter Percentage of Marks");
   		 $("#obtain_marks").focus();
   		return false;
   	}
       if (parseFloat(om) <= 0 ) {				
      		alert("Please Enter Valid Percentage of Marks");
      		 $("#obtain_marks").focus();
      		return false;
      	}
       
       var maxLength = 5;
		 var charLength = om.length;
		
	       if(charLength > maxLength){
	       	alert("Please Enter Percentage of Marks should be less then 6 Characters");
				$("input#obtain_marks").focus();
				return false;
	       } 
	    
	       if (om > 100 ) {				
	      		alert("Percentage Of Marks Can't Have More Than 100 Percent");
	      		 $("#obtain_marks").focus();
	      		return false;
	      	}
		
// 	       if ($("#total_marks").val().trim() <= "0" || $("#total_marks").val().trim() == "") {				
// 	      		alert("Please Enter Total Marks");
// 	      		 $("#total_marks").focus();
// 	      		return false;
// 	      	}  
// 		var maxLength = 5;
// 		 var charLength = $("input#total_marks").val().length;
// 	       if(charLength >= maxLength){
// 	       	alert("Please Enter Out of Marks should be less then 5 Characters");
// 				$("input#total_marks").focus();
// 				return false;
// 	       }  
	       if ($("select#grade").val() == 0) {
				alert("Please Select Division");
				$("select#grade").focus();
				return false;
			}
	       
		if($("input#doc_path").val() == "" && $("input#doc_path_hid").val() == "")
		 {
			if($("#doc_path_hid").val() == ""){
			 alert("Please Upload Document");
			 $("input#doc_path").focus();
			 return false;
			}
		  }
}

// function getPage()
// {  
// 	$("#pers_exper_hid").val("${pers_adv_details_session}");
// 	document.getElementById("applicationUrlForm4").submit();
// }
// function getEduPage()
// {  
// 	$("#eid").val('${tbpdid}');
// 	$("#pers_exper_hid").val("${pers_adv_details_session}");
// 	document.getElementById("mainForm11").submit();
// }

function getPreviousPage()
{  
	$("#pers_details_hid").val($("#p_id").val());
	document.getElementById("applicationUrlForm").submit();
}

function Edu_next(){
	
	var j = $("#jlength").val()
	if (parseInt(j) > 0) {
// 		 alert("Please Fill Atleast One Education Details");
// 		 return false;
	}
	
	$("#doc_eid").val($("#p_id").val());
	$("#pers_exper_hid").val("${pers_adv_details_session}");
	document.getElementById("applicationUrlForm5").submit();	
}

function getExpPage(){
	$("#tp_eid").val($("#p_id").val());
	$("#pers_exper_hid").val("${pers_adv_details_session}");
	document.getElementById("applicationUrlForm4").submit();	
}

function getDocPage(){
	$("#doc_eid").val($("#p_id").val());
	$("#pers_exper_hid").val("${pers_adv_details_session}");
	document.getElementById("applicationUrlForm5").submit();	
}
function getchoicePage(){
	$("#ch_eid").val($("#p_id").val());
	$("#pers_exper_hid").val("${pers_adv_details_session}");
	document.getElementById("applicationUrlForm6").submit();	
}


function Getchekyear(current_year) {
	
	$.ajaxSetup({
		async : false
	});
	
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	var p_id =  $("#p_id").val();
	var current_edu =  $("#academic").val();
	
	if (current_edu == "0") {
		alert("Please Select Name of Exam");
		$("#passing_year").val('0');
			$("#academic").focus();
			return false;
	}
	
	$.post('Getedu_chekyear_nch_ctrl?' + key + "=" + value,{p_id : p_id},function(j) {
		for (var i = 0; i < j.length; i++) {
			if (parseInt( j[i][0]) > parseInt( current_edu)) {
				var gap2 = (parseInt(j[i][1])) - (parseInt(2));		
				if (gap2 < current_year) {
					alert("Select Year "+gap2+" or Smaller Then That.");
					$("#passing_year").val(0);
				}
			}
			if (parseInt( j[i][0]) <  parseInt(current_edu)) {
				var gap = ((parseInt( j[i][1])) + (parseInt(2)));
				if (current_year < (parseInt(gap)))  {
					alert("Select Year "+gap+" or Grater Then That.");			
					$("#passing_year").val(0);
				}
			}
		}
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
}


//csp----------------------------
document.addEventListener('DOMContentLoaded', function() {

			document.getElementById('save_btn').onclick = function () {
				return isValidateClientSide();
			};
			document.getElementById('clear_btn').onclick = function() {
				return clear_field();
			};
			document.getElementById('board_or_university').onkeypress = function() {
				return onlyAlphabetsStringSpace(event, this);
			};
			
			document.getElementById('passing_year').onchange = function() {
				return Getchekyear(this.value);
			};
			
			document.getElementById('school_or_collage').onkeypress = function() {
				return onlyAlphabetsStringSpace(event, this);
			};
			document.getElementById('subject').onkeypress = function() {
				return onlyAlphabetsStringSpace(event, this);
			};
			document.getElementById('obtain_marks').onkeypress = function() {
				return isNumberKeydecimal(this, event);
			};
			document.getElementById('doc_path').onchange = function() {
				return pdfFileSizeValidation(this, this.value, "doc_path", "200kb",
						"document_lbltik", "document_lbl1", this.accept);
			};
			
			document.getElementById('tunnel_1').onclick = function() {
// 				if(confirm('Are you sure you want to Proceed?')){getPreviousPage();}else{return false;}
				getPreviousPage();
			};
			document.getElementById('tunnel_3').onclick = function() {
// 				if(confirm('Are you sure you want to Proceed?')){Edu_next();}else{return false;}
				Edu_next();
			};
			
			document.getElementById('aIdNext').onclick = function() {
// 				if (confirm('Are you sure you want to Proceed?')) {
					return Edu_next();
// 				} 
// 				else {
// 					return false;
// 				}
			};
			document.getElementById('aIdPrevious').onclick = function() {
// 				if (confirm('Are you sure you want to Proceed?')) {
					return getPreviousPage();
// 				} else {
// 					return false;
// 				}
			};

	});
</script>
