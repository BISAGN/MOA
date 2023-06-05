<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<style>
.line_design {
	display: flex;
	align-items: center;
	width: 100%;
}

.line_text {
	font-size: 14px !important;
}

.line_text {
	color: #4397ac;
	font-size: 16px !important;
	font-weight: bold;
	border: 2px solid #4397ac;
	width: 300px;
	padding: 5px;
	margin-bottom: 16px;
	text-align: center;
}

.line {
	width: 100%;
	display: block;
	/* margin-top: 1rem; */
	margin-bottom: 1rem;
	border: 0;
	border-top-color: currentcolor;
	border-top-style: none;
	border-top-width: 0px;
	border-top: 2px solid #2e689f;
}

div#ui-datepicker-div {
	width: min-content !important;
}

div#ui-datepicker-div table thead {
	width: 100% !important;
	background-color: #257758;
}
div#ui-datepicker-div table tbody {
	height: auto !important;
}

.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-all
	{
	background: #198754 !important;
}
.videomodal {
  display: none;
  position: fixed; 
  z-index: 1000; 
  padding-top: 10px;
  left: 0;
  top: 0;
  width: 100%; 
  height: 100%; 
  overflow: auto; 
  background-color: rgb(0,0,0); 
  background-color: rgba(0,0,0,0.4); 
  color: white;
}
.videomodal-content {
    background-color: #113f64;
    margin: auto;
    padding: 20px;
    border: 7px solid #ffffff;
    width: 80%;
    /* height: 100%; */
}

.Vclose {
  color: #dc3545;
  float: right;
  font-size: 28px;
  font-weight: bold;
}
.Vclose:hover,
.Vclose:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

@media (max-width: 1368px){
.videomodal-content {
    width: 65%;
}
}


</style>

<script type="text/javascript"> 

/* 	var intervalID = setInterval(update_values,3000); */
/* 	function update_values() { */
/* 		$('#result').text(playtimeofjason); */
/* 	}; */
/*     function stopTextColor() { */
/*     	clearInterval(intervalID); */
/* 	} */
/* 	var playtimeofjason ; */
/*     var src ;  */
 
</script>


<form:form name="course" id="course" action="StudentCourseAction"
	method='POST' modelAttribute="StudentcourseCMD"
	enctype="multipart/form-data">
	<div class="container">
		<div class="card">
			<div class="card-header" align="center">
				<h5>
					<span id="lbladd1"></span>STUDENT COURSE CONTENTS
				</h5>
			</div>
			<div class="card-body card-block">
				<div class="col-md-12">
					<div class="col-md-6">

						<div class="row form-group">
							<div class="col-md-3">
								<label for="text-input" class=" form-control-label">Course
									Name<strong style="color: red;">*</strong>
								</label>
							</div>
							<div class="col-12 col-md-6">

								<input type="hidden" id="id" name="id" class="form-control"
									value="0" autocomplete="off"> <select
									name="course_name" class="form-control" id="course_name">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${course_name}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>


								</select>

							</div>
						</div>
					</div>


					<div class="col-md-6">
						<div class="row form-group">

							<div class="col col-md-3">
								<label for="text-input" class=" form-control-label">Module
									Name <strong style="color: red;">*</strong>
								</label>
							</div>
							<div class="col-12 col-md-6">
								<input type="hidden" id="id" name="id" class="form-control"
									value="0" autocomplete="off"> <select id="module_name"
									name="module_name" class="form-control"
									onchange="getCourse(this.value);">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${module_name}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>

								</select>
							</div>

						</div>
					</div>
				</div>
				<br>
				<br>

				<div class="col-md-12">

					<div class="col-md-6">
						<div class="row form-group">

							<c:if test="${role=='NCISM'}">
								<div class="col-md-3">

									<label for="text-input" class=" form-control-label">Video<strong
										style="color: red;">*</strong></label>
								</div>
								<div class="col-md-6">

									<input type="file" id="ref_video" name="ref_video"
										class="form-control effect-9"> <input type="hidden"
										id="video_hid" name="video_hid" class="form-control">
									<span class="focus-border"><i></i></span>

								</div>
							</c:if>


						</div>
					</div>


					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-3">
								<label for="text-input" class=" form-control-label">From
									Date<strong style="color: red;">*</strong>
								</label>
							</div>
							<div class="col-md-6 col-12 mb-3 ">

								<input type="text" name="from_date" id="from_date"
									maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
									class="form-control-sm form-control effect-9 "
									style="width: 84%; display: inline;"
									onfocus="this.style.color='#000000'"
									onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
									onkeyup="clickclear(this, 'DD/MM/YYYY')"
									onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this); "
									aria-required="true" autocomplete="off"
									style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"> <span
									class="focus-border"><i></i></span>

							</div>


						</div>
					</div>
				</div>


				<!-- 			accept reject approval div -->


				<div class="col-md-12">
					<c:if test="${role=='NCISM'}">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="username">APPROVAL STATUS<span
										class="star_design"></span> <span style="color: #ff0000"></span></label>
								</div>
								<div class="col-md-6 col-12 mb-3 ">
									<select name="app_status" id="app_status" class="form-control">
										<option value="0">PENDING</option>
										<option value="1">APPROVE</option>
										<option value="2">REJECT</option>
									</select>
								</div>
							</div>
						</div>
					</c:if>


					<c:if test="${role=='STUDENT'}">
						<div style="display: none">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="username">APPROVAL STATUS<span
										class="star_design"></span> <span style="color: #ff0000"></span></label>
								</div>
								<div class="col-md-6 col-12 mb-3 ">
									<select name="app_status" id="app_status" class="form-control">

										<option value="1">APPROVE</option>

									</select>

								</div>

							</div>
						</div>
					</c:if>
					<c:if test="${role=='NCISM'}">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3 ">
									<label class=" form-control-label">Upload File <strong
										style="color: red;">* </strong>
									</label>
								</div>
								<div class="col-md-6 col-12 mb-3 ">
									<input type="file" accept=".pdf" id="upload_file"
										name="upload_file" class="form-control effect-9">
									<!-- 											<input type="hidden" id="upload_file_hid" -->
									<!-- 												name="upload_file_hid" class="form-control"> -->

									<span class="focus-border"><i></i></span>
								</div>
							</div>
						</div>
					</c:if>

				</div>



			</div>
		</div>
		<div class="card-footer" align="center">
			<a href="StudentCoursecontentUrl" class="btn btn-primary btn-sm">Clear</a>
			<input type="submit" class="btn btn-success btn-sm" value="Save"
				onclick="return isvalidData();"> <a href="commonDashboard"
				class="btn btn-danger btn-sm">Cancel</a>
			<button type="button" class="btn btn-info btn-sm" id="btn-reload">
				<i class="fa fa-search">SEARCH</i>
			</button>



		</div>
		<input type='hidden' id='id' name="id" value='0' /> <input
			type='hidden' id='video_id' name="video_id" value='0' />
	</div>

</form:form>



<c:url value="deletecourse_Url" var="deletecourse_Url" />
<form:form action="${deletecourse_Url}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>


<div class="container">
	<table id="search_Student_Course"
		class="display table no-margin table-striped  table-hover  table-bordered">
		<thead>
			<tr>
				<th align="center">SER NO</th>
					<th id= "${item.id}">COURSE NAME</th>
				<th id="2">MODULE NAME</th>
				<th class="action">ACTION</th>
			</tr>
		</thead>
		<tbody >
		</tbody >
	</table>
</div>

	 
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
	</div>		

<!-- accept and reject start-->
<c:url value="Student_Course_Approve_url" var="Student_Course_Approve_url" />
			<form:form action="${Student_Course_Approve_url}" method="post" id="AcceptCourse"
			name="AcceptCourse" modelAttribute="Acceptid">
			<input type="hidden" name="Acceptid" id="Acceptid" value="0" />
</form:form>

<c:url value="Student_Course_Reject_url" var="Student_Course_Reject_url" />
			<form:form action="${Student_Course_Reject_url}" method="post" id="RejectCourse"
			name="RejectCourse" modelAttribute="Rejecttid">
			<input type="hidden" name="Rejectid" id="Rejectid" value="0" />
</form:form>

<!-- accept and reject end-->

	<script>
	


	$(document).ready(function() {
		
		datepicketDate('from_date');
		
		
		mockjax1('search_Student_Course');
		table = dataTable('search_Student_Course');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
	});

	function data(search_Student_Course) {
		jsondata = [];
		var table = $('#' + search_Student_Course).DataTable();
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
		var ref_video = $("#ref_video").val();
		var app_status = $("select#app_status").val();
		
		

		$.post("getFilterStudent_course_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			course_name:course_name,
			module_name:module_name,
			ref_video:ref_video,
			app_status:app_status
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ i + 1,j[i].course_name, j[i].module_name ,j[i].action
					
					
					
					 ]);
			}
		});
		$.post("getTotalStudent_course_dataCount?" + key + "=" + value, {
			course_name:course_name,module_name:module_name,ref_video:ref_video,app_status:app_status
		}, function(j) {
			FilteredRecords = j;
			});
	}
	
	function deleteData(id) {
		$("#id2").val(id);
		
		document.getElementById('deleteForm').submit();
	}

	
	
</script>
<script>


function download_file(id) {
	
	var pdfView="kmlFileDownload444?kmlFileId455="+id+"&fildname1=assignment_upload";
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

</script> 

<script>

function videotopicCall(obj) {
	var id=obj;
	$('#video_id').val(id);
	$('#id').val(obj);
			$('div#videodiv').empty();
				$("div#videodiv").append('<div>Current Time : <span  id="currentTime">0</span></div><div>Total time : <span id="totalTime">0</span></div><div><button  onclick="setCurTime()" type="button">Set time position to 5 seconds</button></div><video  onended="video_ended()" id="my_video" controls width="100%" height="80%"><source id="sourceid" src="kmlLOFileDownload6?kmlFileId65='+id+'&fildname=ref_video" type="video/mp4"></video>'
					    +'</tr>');
			var modal = document.getElementById("videoModal");
			var span = document.getElementsByClassName("Vclose")[0];

	        document.getElementById('videoModal').style.display = 'block';

	  span.onclick = function() {
	  modal.style.display = "none";
	}
	
}



// current and duration time start

var vid = document.getElementById("my_video");

function setCurTime() { 
	var myVid = document.getElementById("my_video");
	myVid.currentTime = "5";
	alert(myVid.duration);
	
  console.log(myVid.currentTime,"tk");
} 

function timev(obj) {
  	var curtime = obj.currentTime;
	console.log(curtime,  "ttttttttttttt");
	
	
}
setInterval(function(){
	var vid = document.getElementById("my_video");	
	
	const element = document.getElementById("currentTime");
	element.innerHTML = vid.currentTime;
	
	const ele = document.getElementById("totalTime");
	ele.innerHTML = vid.duration;
		
	},1000);


	
//current and duration time end


// accept and reject start
function Accepturl(id) {
	debugger;
	$("#Acceptid").val(id);
	document.getElementById('AcceptCourse').submit();
		}

function Rejecturl(id) {
	$("#Rejectid").val(id);
	document.getElementById('RejectCourse').submit();
	}

//accept and reject end


// video history record

function video_ended() {
	
	var user_id = '${userId}';
	var video_id=  $('#video_id').val();
	var key = "${_csrf.parameterName}";
 	var value = "${_csrf.token}";
 	var username = '${username}';
	$.post('getVideoviewhistoryrecords?'+key+"="+value, {user_id : user_id, video_id : video_id,username:username}).done(function(j) {
	});
}
</script>



















