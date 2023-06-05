<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/watermark/common.js"></script>
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

<script nonce="${cspNonce}" type="text/javascript">
	var username = "${username}";
</script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd"></span>Link Course & Degree Master
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Link
									Course & Degree Master</li>
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
					<form:form name="Elective_Course_Master"
						id="Elective_Course_Master" action="Elective_Course_Master_action"
						method='POST' modelAttribute="Elective_Course_Master_cmd"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Link Course & Degree Master</h6>
								<div class="row">

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>Degree<strong class="mandatory">*</strong></label>
											<div class="select-position">
												<select name="degree_id" id="degree_id"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getDegreeList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.degree_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<!-- 									<div class="input-style-1"> -->
										<!-- 										<label for="text-input">Course<span class="mandatory">*</span></label> -->
										<!-- 										<input id="course_name" name="course_name" autocomplete="off" -->
										<!-- 											maxlength="100" placeholder="Course" /> <input -->
										<!-- 											type="hidden" id="id" name="id" class="mt-3" value="0" -->
										<!-- 											autocomplete="off"> -->
										<!-- 									</div> -->
										<div class="select-style-1">
											<label>Course<strong class="mandatory">*</strong></label>
											<div class="select-position">
												<select name="course_name" id="course_name"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getCourseList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.course_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- end input -->
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="text-input">Upload Image<strong
												class="mandatory">*</strong></label> <input type="file"
												accept="image/*" id="upload_img" class="form-control"
												name="upload_img" accept=".jpg" class="form-control">
											<input type="hidden" id="upload_img_hid"
												name="upload_img_hid" class="form-control">

										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>Profession<strong class="mandatory">*
											</strong></label>
											<div class="select-position">
												<select name="semester_id" id="semester_id"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="text-input">Demo Video<strong
												class="mandatory">*</strong></label> <input type="file"
												id="demo_video" accept=".mp4" name="demo_video"
												class="form-control" /> <input type="hidden"
												id="hid_demo_video" value="0" name="hid_demo_video"
												class="mt-3" /> <span class="errorClass"
												id="doc_upload_lbl"></span> <span class='tikClass'
												id="doc_upload_lbltik"></span>

										</div>
										<!-- end input -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Status<strong
												class="mandatory">* </strong></label>
											<div class="select-position">
												<select name="status" id="status"
													class="singleselect form-control form-control-lg">
													<c:forEach var="item" items="${ActiveInActiveList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="input-style-1">
											<input type="hidden" id="id" name="id" class="mt-3" value="0"
												autocomplete="off">
										</div>
										<!-- end select -->
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="text-input">Course Description<strong
												class="mandatory">* </strong></label>
											<textarea id="course_description" name="course_description"
												rows="5" cols="50" autocomplete="off" maxlength="500"
												placeholder="Course Description"></textarea>

											<input type="hidden" id="id" name="id" class="mt-3" value="0"
												autocomplete="off">
										</div>
									</div>

									

								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">

											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li><input type="submit" id="btn-save"
												class="main-btn info-btn btn-hover btnsave" value="Save" /></li>
											<li><a href="elective_course_master_url"
												class="main-btn dark-btn n btn-hover btnreset" type="button">Reset</a></li>
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
											<table class="table" id="search_elective_course_master">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th id="2"><h6>Degree</h6></th>
														<th id="3"><h6>Course</h6></th>
														<th id="4"><h6>Profession</h6></th>
														<th><h6>Upload image</h6></th>
														<th><h6>Demo video</h6></th>
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
<div class="modal image-modal" id="myModal">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<!-- Modal Header -->
			
			<div class="modal-header">
<!-- 							<h3 class="modal-title">Modal title</h3> -->
							<button type="button" class="btn-close close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
<!-- 			<span class="close">&times;</span> -->
			<!-- Modal body -->
			<div class="modal-body">
				<div class="modal-img">
					<img id="img01">
					<div id="caption"></div>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- The Modal -->
<div class="modal image-modal" id="videoModal">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
<!-- 							<h3 class="modal-title">Modal title</h3> -->
							<button type="button" class="btn-close video-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
<!-- 			<span class="video-close">&times;</span> -->
			<!-- Modal body -->
			<div class="modal-body">
				<div class="modal-img">
					<div id="videodiv">
						<div class="d-flex justify-content-center">
							<!-- 				<div class="content-title"> -->
							<!-- 					Current Time : <span id="">0</span> -->
							<!-- 				</div> -->
							<!-- 				<div class="content-title"> -->
							<!-- 					Total time : <span id="totalTime">0</span> -->
							<!-- 				</div> -->
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<c:url value="Edit_ele_course_mstrUrl" var="Edit_ele_course_mstrUrl" />
<form:form action="${Edit_ele_course_mstrUrl}" method="post"
	id="updateForm" name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>


<c:url value="delete_ele_course_mstr_Url"
	var="delete_ele_course_mstr_Url" />
<form:form action="${delete_ele_course_mstr_Url}" method="post"
	id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>

<!-- start -->



<script nonce="${cspNonce}" type="text/javascript">
	
	$(document).ready(function() {
		mockjax1('search_elective_course_master');
		table = dataTable('search_elective_course_master');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		
		if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}
		
// 		debugger;
// 			document.getElementById("demo_video").addEventListener("change", function () {
// 			    var file = this.files[0];
// 			    if (file) {
// 			        var mbSize = file.size / 1024 / 1024;
// 			        var fileIsMp4 = (file.type === "video/mp4");
// 			        if (mbSize > 1 || !fileIsMp4) {
// 			            alert("Please select video file");
// 			        } else {
// 			            alert("Video file selected Successfully");
// 			        }
// 			    }
// 			});
		
		
	});
	
	document.addEventListener('DOMContentLoaded', function () {	
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

		document.getElementById('degree_id').onchange = function() {
			//getSemesterBYDegree(this);
			getProfessionBYDegree();
		
		};
		
		//imgFileSizeValidation
		
		document.getElementById('upload_img').onchange = function() {
			return imgFileSizeValidation(this,this.value,'upload_img','50kb','upload_img_hid');
		};
		
		
		document.getElementById('demo_video').onchange = function() {
			pdfFileSizeValidation(this,this.value,'demo_video','100mb','doc_upload_lbltik','doc_upload_lbl',this.accept);
		};
		
	});
	
	
	
	
	function setTimeLoadForTable(){
		
				
		document.querySelectorAll('.ADDElect').forEach((items, index) => {
			
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var eid = document.getElementById('EleId'+val).value;
 				var ename = document.getElementById('EleName'+val).value;
				var estatus = document.getElementById('EleStatus'+val).value;
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					EditData(eid,ename,estatus);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.deleteEleOnclick').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var did = document.getElementById('DEleId'+val).value;
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(did);
				} else {
					return false;
				}
			})
		});
		
	}

	function data(search_elective_course_master) {
		jsondata = [];
		var table = $('#' + search_elective_course_master).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var course_name = $("#course_name").val();
		var status = $("#status").val();
		var upload_img = $("#upload_img").val();
		var degree_id = $("#degree_id").val();
		var semester_id = $("#semester_id").val();

		$.post("getFilter_Ele_Course_master_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			course_name:course_name,
			upload_img:upload_img,
			status:status,
			degree_id:degree_id,
			semester_id:semester_id
			
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].degree_name,j[i].course_name,j[i].profession,j[i].img,j[i].dv,j[i].action ]);
			}
		});
		$.post("getTotalEle_Course_master_dataCount?" + key + "=" + value, {
			course_name:course_name,
			upload_img:upload_img,
			status:status,
			degree_id:degree_id,
			semester_id:semester_id
		}, function(j) {
			
			FilteredRecords = j;

			});
		setTimeout(setTimeLoadForTable, 1000);
	}
	

	function EditData(id) {

		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}

	function Validation() {
		
		if ($("#degree_id").val() == "0") {
			alert("Please Select Degree");
			$("select#degree_id").focus();
			return false;
		}
		if ($("#course_name").val().trim() == "0") {
			alert("Please Select Course");
			$("select#course_name").focus();
			return false;
		}
		if ($("#upload_img").val().trim() == "") {
			alert("Please Upload Image");
			$("input#upload_img").focus();
			return false;
		}
		if ($("#semester_id").val() == "0") {
			alert("Please Select Semester");
			$("select#semester_id").focus();
			return false;
		}
		 if($("#demo_video").val().trim() == ""){
			 	alert("Please Upload Demo Video");
			 	$("#demo_video").focus();
				return false;
			 }
		if ($("#course_description").val().trim() == "") {
			alert("Please Enter Course Description .");
			$("textarea#course_description").focus();
			return false;
		}
		if ($("select#status").val() == "0") {
			alert("Please Select Status.");
			$("select#status").focus();
			return false;
		}
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status.");
			$("select#status").focus();
			return false;
		}

		return true;

	}
</script>
<script nonce="${cspNonce}" type="text/javascript">
// Get the modal
function imageView(obj){

var modal = document.getElementById("myModal");
var span = document.getElementsByClassName("close")[0];


// Get the image and insert it inside the modal - use its "alt" text as a caption
var img = document.getElementById("myImg"+obj);

var course_name = $("#course_name").val();
var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");

img.onclick = function(){
  modal.style.display = "block";
  modalImg.src = this.src;
  //captionText.innerHTML = this.alt;
}

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
span.onclick = function() { 
  modal.style.display = "none";
}
}

// function getSemesterBYDegree(obj){
	
// 	var degree_id = $("#degree_id").val();
	
// 		$.post("getDegreeListbysemester?"+key+"="+value,{degree_id:degree_id}, function(k) {
// 	 		var len = k[0][1];
// 			var options = '<option value="0">--Select--</option>' ;

// 				  for(var i=1;i<=len;i++){
// 						var id = '';
// 						var name = '';

// 						options += '<option value="'+i+'" name=" SEMESTER ' +i+ '" >SEMESTER '+ i + '</option>';
// 			}
					
// 			$("select#semester_id").html(options);
// 		});	
// 	}


function getProfessionBYDegree() {
	
	var degree_id = $("#degree_id").val();

	$
			.post('getProfListbysemester?' + key + "=" + value,
					{
						degree_id : degree_id,
						
					})
			.done(
					function(j) {
						
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							//alert(j[i][0])
							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#semester_id").html(options);
					});
}


	
	
function demovideo(id) {

	$('div#videodiv').empty();
	$("div#videodiv")
			.append(
					'<div class="d-flex justify-content-center"><div class="content-title">Current Time : <span  id="currentTime">0</span></div><div class="content-title">Total time : <span id="totalTime">0</span></div></div><video  id="my_video" controls width="100%" height="100%"><source id="sourceid"' 
					+'src="ElectDemovideoplay?Id='
					+ id
					+ '" type="video/mp4"></video>' + '</tr>');
	var modal = document.getElementById("videoModal");
	var span = document.getElementsByClassName("video-close")[0];
	document.getElementById('videoModal').style.display = 'block';
	span.onclick = function() {
		modal.style.display = "none";
	}
}

</script>

