<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
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
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span>Course Content
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Course
									Content</li>
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
					<!-- input style start -->
					<form:form name="course" id="course" action="CourseAction"
						method='POST' commandName="courseCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Course Content</h6>
								<div class="row">

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">

										<div class="select-style-1">
											<label for="text-input">Type Of Lecture<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="type_of_content" id="type_of_content">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${toc_list}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">System<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="system_name" id="system_name">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${system_name_list}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="degree" id="degree">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Course<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="course_name" class="form-control" id="course_name">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
										<div class="input-style-1 mt-3">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off">
										</div>
										<!-- end select -->
									</div>
								</div>
							</div>
							<div class="inst_block simple-instruction">
								<strong>Instruction :</strong> Each Elective: Five Modules of
								Nine Hours Each (5*9=45)
							</div>
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12">
									<!-- 									<span class="mandatory"><b>Note:- Each Elective: -->
									<!-- 											Five Modules of Nine Hours Each (5*9=45)</b></span> -->
									<div
										class="table-wrapper table-responsive custom-table custom-table-v2">
										<table class="table table-striped" id="att_Tb">

											<thead>
												<tr>
													<th rowspan="2"><h6>Sr No</h6></th>
													<th rowspan="2"><h6>
															Module<span class="mandatory">*</span>
														</h6></th>
													<th rowspan="2"><h6>
															Level of Module<span class="mandatory">*</span>
														</h6></th>
													<th rowspan="2"><h6>
															Video<span class="mandatory">*</span>
														</h6></th>
													<th colspan="3"><h6>Self-guided learning</h6></th>
													<th rowspan="2"><h6>Action</h6></th>
												</tr>
												<tr>

													<th><h6>Upload File</h6></th>
													<th><h6>Other Note</h6></th>
													<th><h6>Upload PPT</h6></th>

												</tr>
												<!-- 						end table row -->
											</thead>

											<tbody id="att_Tbbody">
												<tr id="tr_id_att">
													<td><p>1</p></td>
													<td>
														<div class="select-style-1">
															<div class="select-position">
																<select
																	class="singleselect form-control form-control-lg"
																	id="module_name1" name="module_name1">
																	<option value="0">--Select--</option>
																</select>
															</div>
														</div>
													</td>
													<td>
														<div class="select-style-1">
															<div class="select-position">
																<select
																	class="singleselect form-control form-control-lg"
																	name="level_of_module1" id="level_of_module1">
																	<option value="0">--Select--</option>
																	<c:forEach var="item" items="${level_list}"
																		varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
													</td>
													<td>
														<div class="input-style-1">
															<input type="file" id="ref_video1" name="ref_video1"
																class="form-control" accept="video/*"
																onchange="getDuration(this)"> <input
																type="hidden" id="video_hid" name="video_hid"
																class="mt-3"> <input type="hidden"
																id="video_duration1" name="video_duration1" class="mt-3">
														</div>
													</td>
													<td>
														<div class="input-style-1">
															<input type="file" accept=".pdf" id="upload_file1"
																name="upload_file1" class="form-control">
														</div>
													</td>
													<td>
														<div class="input-style-1">
															<input type="file" id="other_note1" name="other_note1"
																class="form-control"> <input type="hidden"
																id="hid_other_note" name="hid_other_note" class="mt-3">
														</div>
													</td>
													<td>
														<div class="input-style-1">
															<input type="file" accept=".ppt, .pptx" id="upload_ppt1"
																name="upload_ppt1" class="form-control">
														</div>
													</td>
													<td>
														<ul class="buttons-group">
															<li value="ADD" title="ADD" id="id_add_att1">
																<!-- 											onclick="formopen_att(1);" --> <a
																class="main-btn success-btn btn-hover btn-sm"><i
																	class="lni lni-plus"></i></a>
															</li>
														</ul>
													</td>

												</tr>
											</tbody>
										</table>
										<input type="hidden" id="count_hidden_att"
											name="count_hidden_att" class="form-control autocomplete"
											value="1"> <input type='hidden' id='id' name="id"
											value='0' /> <input type='hidden' id='video_id'
											name="video_id" value='0' />
										<!-- 				end table -->
									</div>
									<!-- 		end card -->
								</div>
								<!-- 	end col -->
							</div>

							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li><input value="Save" id="btn-save"
												class="main-btn info-btn btn-hover" type="submit" /></li>
											<li><a href="CoursecontentUrl"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>

						</div>

						<!-- end card -->

						<section class="single-detail-block">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="card-style mb-30">
										<div class="table-wrapper table-responsive custom-datatable-p">
											<table class="table" id="search_Course">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th><h6>Type oF lecture</h6></th>
														<th><h6>System</h6></th>
														<th><h6>Degree</h6></th>
														<th><h6>Course</h6></th>
														<th><h6>Module</h6></th>
														<th><h6>Module level</h6></th>
														<th><h6>View/Download content</h6></th>
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
							</div>
							</section>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>


<!-- The Modal -->
<!-- <div class="modal fade custom-modal show" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" id="videoModal" aria-modal="true" style="display: block; padding-left: 0px;"> -->
<!-- 				<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable"> -->
<!-- 					<div class="modal-content"> -->
<div class="modal image-modal" id="videoModal">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
<!-- 							<h3 class="modal-title">Modal title</h3> -->
							<button type="button" class="btn-close close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
			<div class="modal-body">
							<div class="custom-modal-inner modal-img" id="headData1" name="headData1">
								<div class="row">
			
					<div id="videodiv">
						<div class="d-flex justify-content-center">
							<div class="content-title">
								Current Time : <span id="">0</span>
							</div>
							<div class="content-title">
								Total time : <span id="totalTime">0</span>
							</div>
							<div class="content-title" onclick="setCurTime()">Set time
								position to 5 seconds</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer mt-30">
							<ul class="buttons-group">
<!-- 								<li><a type="button" class="main-btn dark-btn n btn-hover close" data-bs-dismiss="modal">Close</a></li> -->
<!-- 								<li><a onclick="notification();" class="main-btn success-btn  btn-hover" type="button">Submit</a> -->
<!-- 								</li> -->
							</ul>
						</div>
	</div>
</div>
</div>

<!-- 
<div id="videoModal" class="videomodal" align="center">
		<div class="videomodal-content" align="center"> 
			<span class="Vclose">&times;</span>
			 <div class="col-md-12" align="center">
	       	 </div>	
			 <div class="col-md-12" align="center">
	       	 </div>
	       	 <div class="card-body card-block">
			<div id="videodiv">
				<div>
					Current Time : <span id="">0</span>
				</div>
				<div>
					Total time : <span id="totalTime">0</span>
				</div>
				<div>
					<button onclick="setCurTime()" type="button">
					Set time position to 5 seconds</button>
				</div>
			</div>
		</div>	
		</div>
	</div>	 -->

<c:url value="deletecourse_Url" var="deletecourse_Url" />
<form:form action="${deletecourse_Url}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>


<!-- accept and reject start-->
<c:url value="Course_Approve_url" var="Course_Approve_url" />
<form:form action="${Course_Approve_url}" method="post"
	id="AcceptCourse" name="AcceptCourse" modelAttribute="Acceptid">
	<input type="hidden" name="Acceptid" id="Acceptid" value="0" />
</form:form>
<c:url value="Course_Reject_url" var="Course_Reject_url" />
<form:form action="${Course_Reject_url}" method="post" id="RejectCourse"
	name="RejectCourse" modelAttribute="Rejecttid">
	<input type="hidden" name="Rejectid" id="Rejectid" value="0" />
</form:form>
<!-- accept and reject end-->


<script nonce="${cspNonce}" type="text/javascript">
//Add-More-Add
var att=1;
function formopen_att(R){
	$("#att_Tb").show();
	
		 $("#id_add_att"+R).hide();
		 $("#att_id_remove"+R).hide();
		 
		 att=0;
		 att= parseInt(R)+1;
		 
		 if(att < 51){
				 
				 $("input#count_hidden_att").val(att);//current serial No
				 $("table#att_Tb").append('<tr align="center" id="tr_id_att'+att+'"><td><p>'+att+'</p></td>'
						 +'<td><div class="select-style-1"><div class="select-position"><select id="module_name'+att+'" name="module_name'+att+'" class=""><option value="0">--Select--</option></select></div></div></td>'
						 +'<td><div class="select-style-1"><div class="select-position"><select name="level_of_module'+att+'" class="" id="level_of_module'+att+'"><option value="0">--Select--</option><c:forEach var="item" items="${level_list}" varStatus="num"><option value="${item[0]}"name="${item[1]}">${item[1]}</option></c:forEach></select></div></div></td>'
						 +'<td><div class="input-style-1"><input type="file" id="ref_video'+att+'" name="ref_video'+att+'" class="form-control" accept="video/*" onchange="getDuration(this)"> <input type="hidden" id="video_hid" name="video_hid" class="mt-3"> <input type="hidden" id="video_duration'+att+'" name="video_duration'+att+'" class="mt-3"></div></td>'
						 +'<td><div class="input-style-1"><input type="file" accept=".pdf" id="upload_file'+att+'" name="upload_file'+att+'" class="form-control"></td>'
						 +'<td><div class="input-style-1"><input type="file" id="other_note'+att+'" name="other_note'+att+'" class="form-control"> <input type="hidden" id="hid_other_note" name="hid_other_note" class="mt-3"></div></td>'
						 +'<td><div class="input-style-1"><input type="file" accept=".ppt, .pptx" id="upload_ppt'+att+'" name="upload_ppt'+att+'" class="form-control"></div></td>'
						 +'<td><ul class="buttons-group"><li value = "ADD" title = "ADD" id = "id_add_att'+att+'"><a class="main-btn success-btn btn-hover btn-sm"><i class="lni lni-plus"></i></a></li><li value="REMOVE" title = "REMOVE" id = "att_id_remove'+att+'"><a class="main-btn danger-btn btn-hover btn-sm"><i class="lni lni-trash-can"></i></a></li></td>'
			   		     +'</tr>');
				 
				 addOnclick(att);
		 
			}else{
					alert("Please Enter max 50 Quantity");
					 if ( att == 51){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
		 var curcnt = $("#count_hidden_att").val();
		 $("#new_count_hidden").val(curcnt);
		 getmodulebycourse(att);
	
}

function addOnclick(att){
	document.getElementById('id_add_att'+att).onclick = function() {
		formopen_att(att);
	};
	document.getElementById('att_id_remove'+att).onclick = function() {
		formopen_re_att(att);
	};
}
//Add-More-Remove
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

	
$(document).ready(function() {
		
		if ('${system_name}' != "") {
			$('#system_name').val('${system_name}');
			$('#system_name').change();
		}
		if ('${type_of_content}' != "") {
			$('#type_of_content').val('${type_of_content}');
			$('#type_of_content').change();
		}
		 if ('${course_name}' != "") {
			 $('#course_name').val('${course_name}');
			 $('#course_name').change();
		}
// 		 if ('${module_name}' != "") {
// 			 $('#module_name').val('${module_name}');
// 			 $('#module_name').change();
// 		}
// 		 if ('${level_of_course}' != "") {
// 			 $('#level_of_course').val('${level_of_course}');
// 			 $('#level_of_course').change();
// 		}
		 
		 
		 mockjax1('search_Course');
			table = dataTable('search_Course');
			$('#btn-reload').on('click', function() {
				table.ajax.reload();
			});
			
			if(window.location.href.includes("msg"))
			{
				 var url = window.location.href.split("?msg")[0];
				 window.location = url;
			}
	});

	function data(search_Course) {
		jsondata = [];
		var table = $('#' + search_Course).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var course_name =  $("select#course_name").val();
		var module_name = $("select#module_name1").val();
		var ref_video = $("#ref_video").val();
// 		var level_of_course = $("#level_of_course").val();
		var level_of_module = $("#level_of_module1").val();
// 		var app_status = $("select#app_status").val();
		var type_of_content = $("select#type_of_content").val();
		var system_name = $("select#system_name").val();
		var degree = $("select#degree").val();
		
		
		
		$.post("getFiltercourse_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			course_name:course_name,
			module_name:module_name,
// 			level_of_course:level_of_course,
			level_of_module:level_of_module,
// 			app_status:app_status,
			type_of_content:type_of_content,
			system_name:system_name,
			degree:degree
			
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([j[i].sr_no,j[i].type_of_content, j[i].system_name,j[i].degree_name, j[i].course_name, j[i].module_name,j[i].level_of_module  ,j[i].content ,j[i].action
					 ]);
			}
		});
		$.post("getTotalcourse_dataCount?" + key + "=" + value, {
			Search : Search,
			course_name:course_name,module_name:module_name,
			level_of_module:level_of_module,type_of_content:type_of_content,
			system_name:system_name,
			degree:degree
			
		}, function(j) {
			FilteredRecords = j;
			});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
function setTimeLoadForTable(){
		
		document.querySelectorAll('.Download').forEach((items, index) => {
			items.addEventListener('click', event => {
// 				debugger;
				var val=parseInt(index)+1;
				var hid = document.getElementById('download_id'+val).value;
				if (confirm('Are You Sure You Want to Download PDF ?')) {
					downloadnote_file(hid);
				} else {
					return false;
				}
			})
		});
		document.querySelectorAll('.Delete').forEach((items, index) => {
			items.addEventListener('click', event => {
// 				debugger;
				var val=parseInt(index)+1;
				var hid = document.getElementById('delete_id'+val).value ;
				if (confirm('Are You Sure You Want to delete Detail ?')) {
					deleteData(hid);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.Download_Other_NoteID').forEach((items, index) => {
			items.addEventListener('click', event => {
// 				debugger;
				var val=parseInt(index)+1;
				var hid = document.getElementById('download_id1'+val).value;
				if (confirm('Are You Sure You Want to Download Notes ?')) {
					downloadnote_file(hid);
				} else {
					return false;
				}
			})
		});
		document.querySelectorAll('.Download_PPT_Id').forEach((items, index) => {
			items.addEventListener('click', event => {
				debugger;
				var val=parseInt(index)+1;
				var hid = document.getElementById('download_ppt_id'+val).value;
				if (confirm('Are You Sure You Want to Download PPT ?')) {
					downloadnote_file(hid);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.Download_Vedio').forEach((items, index) => {
			items.addEventListener('click', event => {
				debugger;
				var val=parseInt(index)+1;
				var hid = document.getElementById('download_Vedio'+val).value;
				if (confirm('Are You Sure You Want to View Vedio ?')) {
					videotopicCall(hid);
				} else {
					return false;
				}
			})
		});
		
// 		document.querySelectorAll('.ApproveID').forEach((items, index) => {
// 			items.addEventListener('click', event => {
// 				var val=parseInt(index)+1;
// 				var hid = document.getElementById('approve_id'+val).value;
// 				if (confirm('Are You Sure You Want to Approve Detail ?')) {
// 					Accepturl(hid);
// 				} else {
// 					return false;
// 				}
// 			})
// 		});
		
		
	}
	
	
	
function deleteData(id) {
	$("#id2").val(id);
	document.getElementById('deleteForm').submit();
}


// function download_file(id) {
	
// 	var pdfView="kmlFileDownload444?kmlFileId455="+id+"&fildname1=assignment_upload";
//     fileName="TopicContent";
//     fileURL=pdfView;
//     if (!window.ActiveXObject) {
//         var save = document.createElement('a');
//         save.href = fileURL;
//         save.target = '_blank';
//         var filename = fileURL.substring(fileURL.lastIndexOf('/')+1);
//         save.download = fileName || filename;
// 	       if ( navigator.userAgent.toLowerCase().match(/(ipad|iphone|safari)/) && navigator.userAgent.search("Chrome") < 0) {
// 				document.location = save.href; 
// 			}else{
// 		        var evt = new MouseEvent('click', {
// 		            'view': window,
// 		            'bubbles': true,
// 		            'cancelable': false
// 		        });
// 		        save.dispatchEvent(evt);
// 		        (window.URL || window.webkitURL).revokeObjectURL(save.href);
// 			}	
//     }

//     else if ( !! window.ActiveXObject && document.execCommand)  {
//         var _window = window.open(fileURL, '_blank');
//         _window.document.close();
//         _window.document.execCommand('SaveAs', true, fileName || fileURL)
//         _window.close();
//     }
// }

function downloadnote_file(id,fildname) {
		
	var pdfView="kmlFileDownload444?kmlFileId455="+id+"&fildname1="+fildname+"";
    fileName="TopicContent";
    fileURL=pdfView;
    if (!window.ActiveXObject) {
        var save = document.createElement('a');
        save.href = fileURL;
        save.target = '_blank';
        var filename = fileURL.substring(fileURL.lastIndexOf('/')+1);
        save.download = fileName || filename;
	       if ( navigator.userAgent.toLowerCase().match(/(ipad|iphone|safari)/) && navigator.userAgent.search("Chrome") < 0) {
				document.location = save.href; 
			}else{
		        var evt = new MouseEvent('click', {
		            'view': window,
		            'bubbles': true,
		            'cancelable': false
		        });
		        save.dispatchEvent(evt);
		        (window.URL || window.webkitURL).revokeObjectURL(save.href);
			}	
    }

    else if ( !! window.ActiveXObject && document.execCommand)  {
        var _window = window.open(fileURL, '_blank');
        _window.document.close();
        _window.document.execCommand('SaveAs', true, fileName || fileURL)
        _window.close();
    }
}



function videotopicCall(obj) {
	var id=obj;
	$('#video_id').val(id);
	$('#id').val(obj);
	$('div#videodiv').empty();

	
			$("div#videodiv").append('<div class="d-flex justify-content-center"><div class="content-title">Current Time : <span  id="currentTime">0</span></div><div class="content-title">Total time : <span id="totalTime">0</span></div><div class="content-title" onclick="setCurTime()" type="button">Set time position to 5 seconds</div></div><video  onended="video_ended()" id="my_video" controls width="100%" height="100%"><source id="sourceid" src="kmlLOFileDownload6?kmlFileId65='+id+'&fildname=ref_video" type="video/mp4"></video>'
					    +'</tr>');
			var modal = document.getElementById("videoModal");
			var span = document.getElementsByClassName("close")[0];
	        document.getElementById('videoModal').style.display = 'block';
	  span.onclick = function() {
	  modal.style.display = "none";
	}
	  
	// current and duration time start
	  var vid = document.getElementById("my_video");
	  function setCurTime() { 
	  	var myVid = document.getElementById("my_video");
	  	myVid.currentTime = "5";
	  } 
	  function timev(obj) {
	    	var curtime = obj.currentTime;
	  }
	  setInterval(function(){
	  	var vid = document.getElementById("my_video");	
	  	
	  	const element = document.getElementById("currentTime");
	  	element.innerHTML = vid.currentTime;
	  	
	  	const ele = document.getElementById("totalTime");
	  	ele.innerHTML = vid.duration;
	  	},1000);  
	
}

// accept and reject start

// function Accepturl(id) {
// 	$("#Acceptid").val(id);
// 	document.getElementById('AcceptCourse').submit();
// }

// function Rejecturl(id) {
// 	$("#Rejectid").val(id);
// 	document.getElementById('RejectCourse').submit();
// }
	
	// video history record
	function video_ended() {
		var user_id = '${userId}';
		//alert(user_id);
		var video_id = $('#video_id').val();
		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		var username = '${username}';
		$.post('getVideoviewhistoryrecords?' + key + "=" + value, {
			user_id : user_id,
			video_id : video_id,
			username : username
		}).done(function(j) {
			// 		alert(j);
		});
	}

	//To-get video duration on change of video file upload
	window.URL = window.URL || window.webkitURL;
	function getDuration(control) {
		var ser = control.id.substring(9,10);
		var video = document.createElement('video');
		video.preload = 'metadata';
		video.onloadedmetadata = function() {
			window.URL.revokeObjectURL(video.src);
			//         alert("Duration : " + video.duration + " seconds");
			$("#video_duration"+ser).val(video.duration);
		}
		video.src = URL.createObjectURL(control.files[0]);
	}

	// csp----------------------------

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('btn-save').onclick = function () {
					return Validation();
		};
		document.getElementById('system_name').onchange = function() {
			return getdegreebysystem(this.value);
		};
		document.getElementById('degree').onchange = function() {
			return getcoursebydegree(this.value);

		};
		document.getElementById('course_name').onchange = function() {
			return getmodulebycourse(1);
		};
		//add more
		document.getElementById('id_add_att1').onclick = function() {
			formopen_att(1);
		};
// 		document.getElementById('att_id_remove1').onclick = function() {
// 			formopen_re_att(1);
// 		};
	});
	
	function Validation() {
		if ($("#type_of_content").val().trim() == "0") {
			alert("Please Select Type Of Lecture.");
			$("select#type_of_content").focus();
			return false;
		}
		if ($("#system_name").val().trim() == "0") {
			alert("Please Select System Name.");
			$("select#system_name").focus();
			return false;
		}
		if ($("#degree").val().trim() == "0") {
			alert("Please Select Degree Name.");
			$("select#degree").focus();
			return false;
		}
		if ($("#course_name").val().trim() == "0") {
			alert("Please Select Course Name.");
			$("select#course_name").focus();
			return false;
		}
		var count = $("#count_hidden_att").val();
		for(var i=1;i<=count;i++){
			if ($("#module_name"+i).val().trim() == "0") {
				alert("Please Select Module Name.");
				$("select#module_name"+i).focus();
				return false;
			}
			if ($("#level_of_module"+i).val().trim() == "0") {
				alert("Please Select Level Of Module Name.");
				$("select#level_of_module"+i).focus();
				return false;
			}
			if ($("#ref_video"+i).val().trim() == "" && $("#upload_file"+i).val().trim() == "" && $("#other_note"+i).val().trim() == "" && $("#upload_ppt"+i).val().trim() == "") {
				alert("Please Upload at Least One File.");
				$("input#ref_video"+i).focus();
				return false;
			}
		}
	}

function getdegreebysystem(obj) {
	var system_id = obj;
	$.post('getDegreefromSystem?' + key + "=" + value, {system_id : system_id}).done(function(j) {
		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
		for (var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		}
		$("select#degree").html(options);
	});
}
function getcoursebydegree(obj) {
	var degree_id = obj;
	var type_of_content = $("#type_of_content").val();
	
	$.post('getcoursefromdegree?' + key + "=" + value, {degree_id : degree_id,type_of_content : type_of_content}).done(function(j) {
		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
		for (var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		}
		$("select#course_name").html(options);
	});
}


function getmodulebycourse(ser) {
	var course_id = $("#course_name").val();
	$.post('getmodulefromcourse?' + key + "=" + value, {course_id : course_id}).done(function(j) {
		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
		for (var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		}
		$("select#module_name"+ser).html(options);
	});
}
</script>
