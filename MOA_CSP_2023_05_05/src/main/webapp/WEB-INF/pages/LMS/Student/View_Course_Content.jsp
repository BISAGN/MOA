<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- <script src="js/sweetalert/sweetalert.min.js"></script> -->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>View Course Contents</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">View
									Course Contents</li>
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
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<form:form name="stu_elect_course" id="stu_elect_course" action=""
							method="post" commandName="stu_elect_course_CMD"
							enctype="multipart/form-data">
							<div class="custom-part-col">
								<div class="row">
									<div class="col-12 col-lg-3 col-md-12 col-sm-12">
										<div class="aside">
											<h3 class="filter-title">Filters</h3>
											<ul class="col-list list-none">
												<li class="col-list-inner">
													<div class="select-style-1">
														<label for="text-input">Type Content<strong
															class="mandatory">*</strong></label>
														<div class="select-position">
															<select id="type_content" name="type_content"
																class="singleselect form-control form-control-lg">
																<option value="0">All</option>
																<c:forEach var="item" items="${getTypeOfContent}"
																	varStatus="num">
																	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</li>
												<li class="col-list-inner">
													<div class="select-style-1">
														<label for="text-input">Course<strong
															class="mandatory">*</strong></label>
														<div class="select-position">
															<select id="course_name" name="course_name"
																class="singleselect form-control form-control-lg">
																<option value="0">All</option>
																<%-- 															<c:forEach var="item" items="${course_name}" --%>
																<%-- 																varStatus="num"> --%>
																<%-- 																<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
																<%-- 															</c:forEach> --%>
															</select>
														</div>
													</div>
												</li>
												<li class="col-list-inner">
													<div class="select-style-1">
														<label for="text-input">Module<strong
															class="mandatory">*</strong></label>
														<div class="select-position">
															<select id="module_name" name="module_name"
																class="singleselect form-control form-control-lg">
																<option value="0">All</option>
																<%-- 															<c:forEach var="item" items="${module_name}" --%>
																<%-- 																varStatus="num"> --%>
																<%-- 																<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
																<%-- 															</c:forEach> --%>
															</select>
														</div>
													</div>
												</li>
												<li class="col-list-inner">
													<div class="select-style-1">
														<label for="text-input">Level Of Module<strong
															class="mandatory">*</strong></label>
														<div class="select-position">
															<select id="level_of_module" name="level_of_module"
																class="singleselect form-control form-control-lg">
																<option value="0">All</option>
																<%-- 															<c:forEach var="item" items="${level_list}" --%>
																<%-- 																varStatus="num"> --%>
																<%-- 																<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
																<%-- 															</c:forEach> --%>
															</select> <input type="hidden" name="timerCount" id="timerCount"
																value="" />
														</div>
													</div>
												</li>
											</ul>
										</div>
									</div>
									<div class="col-12 col-lg-9 col-md-12 col-sm-12">
										<div id="getStudentCard" class="row"></div>
									</div>
								</div>
							</div>

						</form:form>
					</div>
				</div>
			</div>
		</div>
		<!-- End Row -->
	</div>
	<!-- end container -->
</section>

<!-- ask query modal start -->
<div class="modal fade custom-modal" id="askquerymodal"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="staticBackdropLabel">You may ask
					your query</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData" name="headData">
				</div>
			</div>
			<!--       <div class="modal-footer" > -->
			<!--       	<ul class="buttons-group" > -->
			<!--        		<li><button type="button" class="main-btn info-btn btn-hover">Submit</button></li>  -->
			<!--       	</ul>      	                -->
			<!--       </div> -->
		</div>
	</div>
</div>
<!-- ask query modal end -->


<c:url value="deletecourse_Url" var="deletecourse_Url" />
<form:form action="${deletecourse_Url}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>

<div class="container" hidden="hidden">
	<table id="search_Course" class="display  no-margin ">
		<thead>
			<tr>
				<th align="center">Sr No</th>
				<th align="center">Course</th>
				<th align="center">Module</th>
				<th align="center">Type of content</th>
				<th align="center">Level of module</th>
				<th align="center">View video</th>
				<th align="center">View file</th>
				<th align="center">View note</th>
				<th align="center">View ppt</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>

<!-- The Modal -->
<div class="modal image-modal" id="videoModal">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<!-- 							<h3 class="modal-title">Modal title</h3> -->
				<button type="button" class="btn-close close"
					data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<!--         <span class="close">&times;</span>  -->
			<!-- Modal body -->
			<div class="modal-body">
				<div class="modal-img">
					<div id="videodiv"></div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<!-- 								<li><a type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal">Close</a></li> -->
					<!-- 								<li><a onclick="notification();" class="main-btn success-btn  btn-hover" type="button">Submit</a> -->
					<!-- 								</li> -->
				</ul>
			</div>
		</div>
	</div>
</div>

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

<c:url value="Exam_Paper_url" var="Exam_Paper_url" />
<form:form action="${Exam_Paper_url}" method="GET" id="ExamForm"
	name="ExamForm" modelAttribute="">
	<input type="hidden" name="course_id1" id="course_id1" value="0" />
	<input type="hidden" name="module_id1" id="module_id1" value="0" />
</form:form>

<!-- accept and reject end-->

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		
		if('${count}'!=""){
		} 
		datepicketDate('from_date');
		
		mockjax1('search_Course');
		table = dataTable('search_Course');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
	});

	function setTimeLoadForTable(){
		document.querySelectorAll('.topiccal').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('topicvid'+val).value;
				var hname = document.getElementById('topicvid_c'+val).value;
				var hpnum = document.getElementById('topicvid_s'+val).value;
				var hpnum1 = document.getElementById('topicvid_m'+val).value;
				var hpnum2 = document.getElementById('topicvid_lm'+val).value;
				
// 				if (confirm('Are You Sure You Want to Download File ?')) {
					videotopicCall(hid,hname,hpnum,hpnum1,hpnum2);
// 				} else {
// 					return false;
// 				}
			})
		});
		
		document.querySelectorAll('.filedown').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('file'+val).value;
				var hname = document.getElementById('file_c'+val).value;
				if (confirm('Are You Sure You Want to Download File ?')) {
					downloadnote_file(hid,hname);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.othernote1').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('notedown'+val).value;
				var hname = document.getElementById('notedown_c'+val).value;
				if (confirm('Are You Sure You Want to Download Notes ?')) {
					downloadnote_file(hid,hname);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.downppt').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('ppt1'+val).value;
				var hname = document.getElementById('ppt_c'+val).value;
				if (confirm('Are You Sure You Want to Download PPT ?')) {
					downloadnote_file(hid,hname);
				} else {
					return false;
				}
			})
		});
	}
	
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
		var module_name = $("select#module_name").val();
		var type_content = $("select#type_content").val();
		var level_of_module = $("select#level_of_module").val();
		var ref_video = $("#ref_video").val();

		$.post("getFilterviewcourse__data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			course_name:course_name,
			module_name:module_name,
			type_content:type_content,
			level_of_module:level_of_module
		
		}, function(j) {
			
			$("div#getStudentCard").empty();// empty card
			
			if(j.length == 0){
				$("#getStudentCard").append('<label for="one" class="">Data Not Available</label>');
			}else{
			
			for (var i = 0; i < j.length; i++) {
			
				jsondata.push([ i + 1,j[i].course_name, j[i].module_name, j[i].type_of_content,j[i].level_of_course,j[i].level_of_module,j[i].credit,j[i].action,j[i].action1,j[i].action2,j[i].action3 ]);
				var ser = i+1;
				
				if(j.length-1>i){
					
					if(j[i].module_id==j[i+1].module_id){
				
				$("#getStudentCard").append('<div class='+'"col-xl-4 col-lg-6 col-md-12 col-12"'+' >'
						+'<div class="card custom-card">'
						/* +'<div class="cource-heading">'
							+'<h3 class="cource-title"><a href="#">'+ j[i].course_name +'</a></h3>'											
						+'</div>' */
						+'<div class="card-body">'
							+'<h4 class="card-title"><a href="#">'+ j[i].course_name +'</a></h4>'	
							+'<div class="card-link"><a href="#" class="readmore">'+j[i].module_name+'</a></div>'
							+'<div class="cardbox-listingview">'
								+'<ul class="cardbox-footer b-top">'
								+'<li class="cbox-title-with-value">Type of Content: <span class="title-value">	'+j[i].type_of_content+'</span></li>'  
// 								+'<li class="cbox-title-with-value">Level of Course:<span class="title-value">'+j[i].level_of_course+'</span></li>' 
								+'<li class="cbox-title-with-value">Level of Module:<span class="title-value">'+j[i].level_of_module+'</span></li>'
							+'</ul>'
							+'</div>'							
							+'<ul class="buttons-group b-top justify-content-center multi-btn-group">'
							+'<li>'+j[i].action+'</li>'
							+'<li>'+j[i].action1+'</li>'
							+'<li>'+j[i].action2+'</li>'
							+'<li>'+j[i].action3+'</li>'
// 							+'<li><a href="#"  class="main-btn info-btn-outline btn-hover btn-sm" id="exampaper'+j[i].course_id+'_'+j[i].module_id+'"><i class="lni lni-remove-file"></i></a></li>'//onclick="return getExamPaperFunction('+j[i].course_id+','+j[i].module_id+');"
							+'</ul>'
// 							+'<a class="main-btn deactive-btn btn-hover btn-iconic-icon mb-1" id="exampaper'+ser+'_'+j[i].course_id+'_'+j[i].module_id+'"><i class="bi bi-box-arrow-in-up-right"></i>Start Exam</a>'
							+'<a class="main-btn active-btn btn-hover btn-iconic-icon" data-bs-toggle="modal" data-bs-target="#askquerymodal" id="queryopen'+ser+'_'+j[i].course_id+'_'+j[i].module_id+'" ><i class="lni lni-question-circle"></i>Ask Query</a>'	///onclick="getdata('+ser+','+j[i].course_id+','+j[i].module_id+');"																			
							+'</div>'
							+'</div>'
							+'</div>')
							//setExamPaper(j[i].course_id,j[i].module_id,ser); //shivali
							setQuery(ser,j[i].course_id,j[i].module_id);
					}else{
						$("#getStudentCard").append('<div class='+'"col-xl-4 col-lg-6 col-md-12 col-12"'+' >'
								+'<div class="card custom-card">'
								/* +'<div class="cource-heading">'
									+'<h3 class="cource-title"><a href="#">'+ j[i].course_name +'</a></h3>'											
								+'</div>' */
								+'<div class="card-body">'
									+'<h4 class="card-title"><a href="#">'+ j[i].course_name +'</a></h4>'	
									+'<div class="card-link"><a href="#" class="readmore">'+j[i].module_name+'</a></div>'
									+'<div class="cardbox-listingview">'
										+'<ul class="cardbox-footer b-top">'
										+'<li class="cbox-title-with-value">Type of Content: <span class="title-value">	'+j[i].type_of_content+'</span></li>'  
//		 								+'<li class="cbox-title-with-value">Level of Course:<span class="title-value">'+j[i].level_of_course+'</span></li>' 
										+'<li class="cbox-title-with-value">Level of Module:<span class="title-value">'+j[i].level_of_module+'</span></li>'
									+'</ul>'
									+'</div>'							
									+'<ul class="buttons-group b-top justify-content-center multi-btn-group">'
									+'<li>'+j[i].action+'</li>'
									+'<li>'+j[i].action1+'</li>'
									+'<li>'+j[i].action2+'</li>'
									+'<li>'+j[i].action3+'</li>'
//		 							+'<li><a href="#"  class="main-btn info-btn-outline btn-hover btn-sm" id="exampaper'+j[i].course_id+'_'+j[i].module_id+'"><i class="lni lni-remove-file"></i></a></li>'//onclick="return getExamPaperFunction('+j[i].course_id+','+j[i].module_id+');"
									+'</ul>'
									+'<a class="main-btn deactive-btn btn-hover btn-iconic-icon mb-1" id="exampaper'+ser+'_'+j[i].elecourseid+'_'+j[i].module_id+'"><i class="bi bi-box-arrow-in-up-right"></i>Start Exam</a>'
									+'<a class="main-btn active-btn btn-hover btn-iconic-icon" data-bs-toggle="modal" data-bs-target="#askquerymodal" id="queryopen'+ser+'_'+j[i].course_id+'_'+j[i].module_id+'" ><i class="lni lni-question-circle"></i>Ask Query</a>'	///onclick="getdata('+ser+','+j[i].course_id+','+j[i].module_id+');"																			
									+'</div>'
									+'</div>'
									+'</div>')
									setExamPaper(j[i].elecourseid,j[i].module_id,ser); //shivali
									setQuery(ser,j[i].course_id,j[i].module_id);
					}
				}else{
					$("#getStudentCard").append('<div class='+'"col-xl-4 col-lg-6 col-md-12 col-12"'+' >'
							+'<div class="card custom-card">'
							/* +'<div class="cource-heading">'
								+'<h3 class="cource-title"><a href="#">'+ j[i].course_name +'</a></h3>'											
							+'</div>' */
							+'<div class="card-body">'
								+'<h4 class="card-title"><a href="#">'+ j[i].course_name +'</a></h4>'	
								+'<div class="card-link"><a href="#" class="readmore">'+j[i].module_name+'</a></div>'
								+'<div class="cardbox-listingview">'
									+'<ul class="cardbox-footer b-top">'
									+'<li class="cbox-title-with-value">Type of Content: <span class="title-value">	'+j[i].type_of_content+'</span></li>'  
//	 								+'<li class="cbox-title-with-value">Level of Course:<span class="title-value">'+j[i].level_of_course+'</span></li>' 
									+'<li class="cbox-title-with-value">Level of Module:<span class="title-value">'+j[i].level_of_module+'</span></li>'
								+'</ul>'
								+'</div>'							
								+'<ul class="buttons-group b-top justify-content-center multi-btn-group">'
								+'<li>'+j[i].action+'</li>'
								+'<li>'+j[i].action1+'</li>'
								+'<li>'+j[i].action2+'</li>'
								+'<li>'+j[i].action3+'</li>'
//	 							+'<li><a href="#"  class="main-btn info-btn-outline btn-hover btn-sm" id="exampaper'+j[i].course_id+'_'+j[i].module_id+'"><i class="lni lni-remove-file"></i></a></li>'//onclick="return getExamPaperFunction('+j[i].course_id+','+j[i].module_id+');"
								+'</ul>'
								+'<a class="main-btn deactive-btn btn-hover btn-iconic-icon mb-1" id="exampaper'+ser+'_'+j[i].course_id+'_'+j[i].module_id+'"><i class="bi bi-box-arrow-in-up-right"></i>Start Exam</a>'
								+'<a class="main-btn active-btn btn-hover btn-iconic-icon" data-bs-toggle="modal" data-bs-target="#askquerymodal" id="queryopen'+ser+'_'+j[i].course_id+'_'+j[i].module_id+'" ><i class="lni lni-question-circle"></i>Ask Query</a>'	///onclick="getdata('+ser+','+j[i].course_id+','+j[i].module_id+');"																			
								+'</div>'
								+'</div>'
								+'</div>')
								setExamPaper(j[i].course_id,j[i].module_id,ser); //shivali
								setQuery(ser,j[i].course_id,j[i].module_id);
				}
			}
		}
			setTimeout(setTimeLoadForTable, 1000);
		});
// 		$.post("getTotalviewcourse_dataCount?" + key + "=" + value, {
// 			course_name:course_name,module_name:module_name,type_content:type_content,level_of_module:level_of_module
// 		}, function(j) {
// 			FilteredRecords = j;
// 			});
	}
	
	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}
// 	function editData(id) {
		
// 		$("#id1").val(id);
// 		document.getElementById('updateForm').submit();
// 	}
	


function download_file(id) {
	
	var pdfView="kmlFileDownload4?kmlFileId455="+id+"&fildname1=assignment_upload";
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
    else if ( !! window.ActiveXObject && document.execCommand)     {
        var _window = window.open(fileURL, '_blank');
        _window.document.close();
        _window.document.execCommand('SaveAs', true, fileName || fileURL)
        _window.close();
    }
}


 
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
 


function videotopicCall(obj,courseid,ms,module,lm) {
// 	alert(obj+"-"+courseid+"-"+ms+"-"+module+"H-H"+lm);
	var status="";
	var lm2 ="";
	var timer="";
	if(lm != "1"){
		lm2= lm-1;
	}else{
		lm2 = lm;
	}
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	$.post('getdataForSeqVal?' + key + "=" + value, {
		courseid : courseid,lm : lm2,module_id:module,
	}).done(function(j) {
		status = j[0];
		timer = j[1];
	});
	if(lm == "1"){
		
		var id=obj;
		$('#id').val(obj);
		$('#video_id').val(id);
				$('div#videodiv').empty();
			
// 					$("div#videodiv").append('<video onended="video_ended('+courseid+','+lm+')" onpause="gettimeFunction('+courseid+','+lm+','+module+')" onplay="startv('+courseid+','+lm+','+module+')" id="ref_video" controls width="1500" height="600"><source id="sourceid" src="kmlLOFileDownload6?kmlFileId65='+id+'&fildname=ref_video" type="video/mp4"></video>'
// 						    +'</tr>');
					
					$("div#videodiv").append('<video   id="ref_video" controls width="100%" height="100%" src="video/'+id+','+module+'" type="video/mp4"></video>'
							    +'</tr>');
					
// 				     $("div#videodiv").append('<video src="video/tom-jerry" id="vd" width="720px" height="480px" controls preload="none">'
// 							    +'</tr>');  

				 	var firstlink = document.getElementById('ref_video');
				    firstlink.currentTime = timer;
				    
				    var supposedCurrentTime = timer;
				    firstlink.addEventListener('timeupdate', function() {
				      if (!firstlink.seeking) {
				    	  	$("#timerCount").val(firstlink.currentTime);
				    		supposedCurrentTime = firstlink.currentTime;
				      }
				    });
				    firstlink.addEventListener('seeking', function() {
				      var delta = firstlink.currentTime - supposedCurrentTime;
				      if (delta > 0.01) {
				    	  firstlink.currentTime = supposedCurrentTime;
				      }
				    });
				    firstlink.addEventListener('ended', function() {
				    	supposedCurrentTime = 0;
				    	
				    	video_ended(courseid,ms,lm,module);
				    	
				    });
				    firstlink.addEventListener('pause', function() {
				    	supposedCurrentTime = 0;
				    	gettimeFunction(courseid,lm,module);
				    	
				    });
				var modal = document.getElementById("videoModal");
				var span = document.getElementsByClassName("close")[0];

		        document.getElementById('videoModal').style.display = 'block';
		  span.onclick = function() {
		  modal.style.display = "none";
		
		  }
		
	}else{
		if(status=="1" || lm == 1){
			var id=obj;
			$('#id').val(obj);
			$('#video_id').val(id);
					$('div#videodiv').empty();
					
// 						$("div#videodiv").append('<video onended="video_ended('+courseid+','+lm+')" onpause="gettimeFunction('+courseid+','+lm+')" id="ref_video" controls width="1500" height="600"><source id="sourceid" src="kmlLOFileDownload6?kmlFileId65='+id+'&fildname=ref_video" type="video/mp4"></video>'
// 							    +'</tr>');
						
					$("div#videodiv").append('<video   id="ref_video" controls width="100%" height="100%" src="video/'+id+','+module+'" type="video/mp4"></video>'
							    +'</tr>');
						
// 					  $("div#videodiv").append('<video src="video/tom-jerry" id="vd" width="720px" height="480px" controls preload="none">'
// 							    +'</tr>');  





						var firstlink = document.getElementById('ref_video');
					    firstlink.currentTime = timer;
					    
					    
					    var supposedCurrentTime = timer;
					    firstlink.addEventListener('timeupdate', function() {
					      if (!firstlink.seeking) {
					    	  $("#timerCount").val(firstlink.currentTime);
					    		supposedCurrentTime = firstlink.currentTime;
					      }
					    });
					    firstlink.addEventListener('seeking', function() {
					      var delta = firstlink.currentTime - supposedCurrentTime;
					      if (delta > 0.01) {
					    	  firstlink.currentTime = supposedCurrentTime;
					      }
					    });
					    firstlink.addEventListener('ended', function() {
					    	supposedCurrentTime = 0;
					    	
					    	video_ended(courseid,ms,lm,module);
					    	
					    });
					    firstlink.addEventListener('pause', function() {
					    	supposedCurrentTime = 0;
					    	gettimeFunction(courseid,lm,module);
					    	
					    });
					    
					  
					    
					    
					    
					var modal = document.getElementById("videoModal");
					var span = document.getElementsByClassName("close")[0];

			        document.getElementById('videoModal').style.display = 'block';

			  span.onclick = function() {
			  modal.style.display = "none";
			}
		}else{
			alert("Please Complete Previous Level Video First");
		}
	}
// 	var dom = document.getElementById('ref_video');
// 	videojs(dom, {}, function(){
// 	    this.on('loadedmetadata', function(){
// 	        this.currentTime(5);
// 	    });
// 	});
}

// accept and reject start
function Accepturl(id) {
	
	$("#Acceptid").val(id);
	document.getElementById('AcceptCourse').submit();
}

function Rejecturl(id) {

	$("#Rejectid").val(id);
	document.getElementById('RejectCourse').submit();
}
	
function download_file1(id) {
	
	var pdfView="kmlFileDownload445?kmlFileId456="+id+"&fildname1=assignment_upload";
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
    else if ( !! window.ActiveXObject && document.execCommand)     {
        var _window = window.open(fileURL, '_blank');
        _window.document.close();
        _window.document.execCommand('SaveAs', true, fileName || fileURL)
        _window.close();
    }
}

//accept and reject end

//video history record
	function video_ended(courseid,ms,lm,module) {
//  	alert(courseid+"-"+ms+"-"+lm);
		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		var username = '${username}';
		$.post('getVideoviewstatus?' + key + "=" + value, {
			courseid : courseid,
			lm : lm,
			ms : ms,
			module : module
		}).done(function(j) {
// 					alert(j);
		});
	}
	
	function gettimeFunction(courseid,lm,module){
		var actualTime = $("#timerCount").val();
		var video = document.getElementById('ref_video');
		var v1=actualTime;
	//	video.currentTime=5;
		
		$.post('getTimeviewstatus?' + key + "=" + value, {
			courseid : courseid,
			v1 : v1,
			module : module
		}).done(function(j) {
					//alert(j);
		});
	}
	
	function startv (courseid,lm,module){
		gettimeFunction(courseid,lm,module);
	}
	
	function datachange() {
		table.ajax.reload();
	}
	
function getcourse_idbytypeofcontent() {
		
		var type_content = $("select#type_content").val();

	      $.post("getcourseDatabytypeofcontent?" + key + "=" + value,{type_content : type_content},
						function(j) {
	    	
							var options = '<option value="' + "0" + '">'
									+ "All" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1]+ '</option>';
							}
							$("select#course_name").html(options);
						});
	}
	
function getmodule_idbycourse() {
		  var course_name = $("select#course_name").val();

	      $.post("getmoduleDatabycourse?" + key + "=" + value,{course_name : course_name},
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "All" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1]+ '</option>';
							}
							$("select#module_name").html(options);
						});
	}
	//shivali
 function getlevel_of_modulebyModule() {
	var module_name = $("select#module_name").val();

      $.post("getlevel_of_modulebyModule?" + key + "=" + value,{module_name : module_name},
					function(j) {
    	                
						var options = '<option value="' + "0" + '">'
								+ "All" + '</option>';
						for (var i = 0; i < j.length; i++) {
							
							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1]+ '</option>';
						}
						$("select#level_of_module").html(options);
					});
}
	
function getdata(ser,courseid,moduleid)
{
 	var options = "<div class='row'><div class='col-12 col-lg-12 col-md-12 col-sm-12'><div class='input-style-1 mb-0'><label>Enter your Query</label><textarea placeholder='Enter Query here' id='message"+ser+"' rows='5' cols='50' name='message"+ser+"' ></textarea></div></div></div>";
 	options += "<input type='hidden' id='hidcourseid"+ser+"' name='hidcourseid"+ser+"' class='form-control' value='"+courseid+"'>";
 	options += "<input type='hidden' id='hidmoduleid"+ser+"' name='hidmoduleid"+ser+"' class='form-control' value='"+moduleid+"'>";
    options += "<div class='modal-footer'><ul class='buttons-group'><li><button type='button' class='main-btn success-btn  btn-hover' data-dismiss='modal' id='squery"+ser+"'>Submit</button></li><li><a type='button' class='main-btn dark-btn n btn-hover' data-bs-dismiss='modal'>Close</a></li></ul></div>";//onclick='return submitData("+ser+");'
 	
    
    
    
	$("#headData").html(options);
	setquerysubmit(ser);
// 	setquerysubmit1(ser);
}

function submitData(ser){
debugger;
	var key = "${_csrf.parameterName}";
 	var value = "${_csrf.token}";
 	
 	var courseid = $("#hidcourseid"+ser).val();
 	var moduleid = $("#hidmoduleid"+ser).val();
 	var message = $("#message"+ser).val();

$.post('getAskQueryMethod?'+key+"="+value, {courseid:courseid,moduleid:moduleid,message:message}).done(function(j) {
	
var msg = j;
if(msg!=""){
	
if (msg == 1) {
// 	swal({
// 		  title: "Please Enter Message!",
// 		  text:"Please Enter Message",
// 		  icon: "error",
// 		  closeOnClickOutside: false,
// 		}).then((willDelete) => {
// 			$('#askquerymodal').modal('hide');
// 		});
	if(confirm("Please Enter Message")){
		$('#askquerymodal').modal('hide');
	}
}
else {
	
// 	swal({
// 		  title: "success!",
// 		  text:msg,
// 		  icon: "success",
// 		  closeOnClickOutside: false,
// 		}).then((willDelete) => {
// 			$('#askquerymodal').modal('hide');
// 		});
		if(confirm(msg)){
			$('#askquerymodal').modal('hide');
		}
      }
    }
  });
}		
	

function Validation(val) {
	
	if ($("textarea#message"+val).val() == "") {
		alert("Please Enter Message.");
		$("textarea#message"+val).focus();
		return false;
	}
	return true;
}
	
function getExamPaperFunction(course_id,module_id){
// 	debugger;
// 	$.post('getExamPaper?' + key + "=" + value, {
// 		course_id : course_id,
// 		module_id : module_id
// 	}).done(function(j) {
// 		if(j > 0){
// 			alert('Your Exam was already Finished');
// 			return false;
// 		}else{
// 			$(location).prop('href', 'Exam_Paper_url')
// 		}
// 	});
	
	
	//shivali
	$.post('getExamPaperSequence?' + key + "=" + value, {
		course_id : course_id,
		module_id : module_id
	}).done(function(j) {
		if(j == 2){
			alert('Please Complete All Level Videos First');
			return false;
		}else{
			$("#course_id1").val(course_id);
			$("#module_id1").val(module_id);
			document.getElementById('ExamForm').submit();
		}
	});
}

//shivali changes
function setExamPaper(val,val2,ser){
	document.getElementById('exampaper'+ser+'_'+val+'_'+val2).onclick = function () {
		return getExamPaperFunction(val,val2)
	};
}

function setQuery(val,val2,val3){
	document.getElementById('queryopen'+val+'_'+val2+'_'+val3).onclick = function () {
		 getdata(val,val2,val3)
	};
}

function setquerysubmit(val){
	document.getElementById('squery'+val).onclick = function () {
		 Validation(val);
		 return submitData(val);
	};
}
// function setquerysubmit1(val){
// 	document.getElementById('squery'+val).onclick = function () {
// 		return Validation(val)
// 	};
// }

document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('type_content').onchange = function() {
		datachange();getcourse_idbytypeofcontent();
	};
	
	document.getElementById('course_name').onchange = function() {
		datachange();getmodule_idbycourse();
	};
	
	document.getElementById('module_name').onchange = function() {
		datachange();getlevel_of_modulebyModule();
	};
	
	document.getElementById('level_of_module').onchange = function() {
		datachange();
	};
});
</script>