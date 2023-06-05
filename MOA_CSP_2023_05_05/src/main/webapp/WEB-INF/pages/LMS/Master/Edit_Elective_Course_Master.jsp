<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->
<!-- <script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- <link rel="stylesheet" href="js/InfiniteScroll_old/css/site.css"> -->
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
							<span id="lbladd"></span>Update Link Course & Degree Master
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update
									Link Course & Degree Master</li>
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
					<form:form action="Edit_Elective_Course_Master_action"
						method="POST" modelAttribute="Edit_Elective_Course_Master_cmd"
						name="edit_elective_course_form" id="edit_elective_course_form"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Update Link Course & Degree Master</h6>
								<div class="row">

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>Degree<strong class="mandatory">* </strong></label>
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

									<!-- 									<div class="input-style-1"> -->
									<!-- 										<label for="text-input">Course<span class="mandatory">*</span></label> -->
									<!-- 										<input id="course_name" name="course_name" autocomplete="off" -->
									<!-- 											maxlength="100" placeholder="Course" /> <input -->
									<!-- 											type="hidden" id="id" name="id" class="mt-3" value="0" -->
									<!-- 											autocomplete="off"> -->
									<!-- 									</div> -->

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">

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
											<label for="text-input">Upload Image <strong
												class="mandatory">* </strong></label>
											<div class="d-flex">
												<input type="file" accept="image/*" id="upload_img"
													name="upload_img" class="form-control"> <input
													type="hidden" id="upload_img_hid" name="upload_img_hid"
													class="form-control">
												<div hidden="hidden">
													<img class='d-block img5050 imageZomm' alt="No Image"
														id="myImg"
														src="MedicalImagePath?i_id=${Edit_ele_course_mstr_Details.id}" />
												</div>

												<ul
													class="buttons-group d-flex justify-content-center uplaod-image">
													<li type="button" id="ima"><a href="#" type="button"
														class="main-btn dark-btn btn-hover btn-sm btnview"> <i
															class="lni lni-eye"></i></a></li>
													<li><a
														href="MedicalImagePath?i_id=${Edit_ele_course_mstr_Details.id}"
														download
														class="main-btn info-btn btn-hover btn-sm btndownload"> <i
															class="lni lni-download"></i></a></li>
												</ul>
											</div>
										</div>
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
												class="mandatory">* </strong></label>
											<div class="d-flex">
												<input type="file" id="demo_video" name="demo_video"
													class="form-control" /> <input type="hidden"
													id="hid_demo_video" value="0" name="hid_demo_video"
													class="mt-3" />

												<ul
													class="buttons-group d-flex justify-content-center uplaod-image">
													<li><a
														class="main-btn active-btn btn-hover btn-sm btnvideo"
														id="return_demovideo"> <i class="bi bi-play-circle">
														</i></a></li>
												</ul>
											</div>
											<!-- end input -->
										</div>
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
										<input type="hidden" id="id" name="id" value="0" />
									</div>


								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group d-flex justify-content-center">
											<li><a href="elective_course_master_url"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
													<i class="lni lni-chevron-left"></i>Back
											</a></li>
											<li><input type="submit" id="update" value="Update"
												class="main-btn deactive-btn btn-hover btnupda" /></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- The Modal -->
<div class="modal image-modal" id="myModal">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
<!-- 							<h3 class="modal-title">Modal title</h3> -->
							<button type="button" class="btn-close close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						
<!-- 						<span class="close">&times;</span> -->
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

<!-- start -->

<script nonce="${cspNonce}" type="text/javascript">


$(document).ready(function() {
	
	 $.ajaxSetup({
			async : false
		});
	
	 $('#id').val('${Edit_ele_course_mstr_Details.id}');
	 $('#degree_id').val('${Edit_ele_course_mstr_Details.degree_id}');
	 $('#degree_id').trigger('change'); 
	 $('#degree_id').change();
	 $('select#course_name').val('${Edit_ele_course_mstr_Details.course_name}');
	 $('#course_name').trigger('change'); 
	// getSemesterBYDegree();
	  getProfessionBYDegree();
	 $('select#semester_id').val('${Edit_ele_course_mstr_Details.semester_id}');
	 $('#semester_id').trigger('change'); 
	 $('input#upload_img_hid').val('${Edit_ele_course_mstr_Details.upload_img}');
	 $('textarea#course_description').val('${Edit_ele_course_mstr_Details.course_description}');
	 $('select#status').val('${Edit_ele_course_mstr_Details.status}');
	 $('#status').trigger('change'); 
	 $('input#hid_demo_video').val('${Edit_ele_course_mstr_Details.demo_video}');
	 
});

function Validation(){
			
	if ($("select#degree_id").val() == "0") {
		alert("Please Select Degree");
		$("select#degree_id").focus();
		return false;
	}
	if ($("select#course_name").val().trim() == "0") {
		alert("Please Select Course");
		$("select#course_name").focus();
		return false;
	}
	if ($("#upload_img_hid").val().trim() == "") {
		alert("Please Select Upload Image");
		$("select#upload_img").focus();
		return false;
	}
	if ($("select#semester_id").val() == "0") {
		alert("Please Select Semester");
		$("select#semester_id").focus();
		return false;
	}
	 if($("#demo_video").val().trim() == ""){
		 	alert("Please Select Demo Video");
		 	$("select#demo_video").focus();
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
function saveData(){
			if(isValidate()==true){
			var form = $('#edit_elective_course_form')[0];

				var data = new FormData(form);

				$.ajax({
					type : "POST",
					enctype : 'multipart/form-data',
					url : "Edit_Elective_Course_Master_action?"+key+"="+value,
					data : data,
					processData : false,
					contentType : false,
					cache : false,
					timeout : 600000,
					success : function(data) {
						
						//	alert(data);
								
							if( data=="Update Successfully"){
							
								 window.location = 'elective_course_master_url?msg='+data;	
							}
						}
					//}
				});
			}
		}

</script>
<!-- start -->

<script nonce="${cspNonce}" type="text/javascript">
// Get the modal
function imageView(){


var modal = document.getElementById("myModal");
var span = document.getElementsByClassName("close")[0];

//debugger;
// Get the image and insert it inside the modal - use its "alt" text as a caption
var img = document.getElementById("myImg");

var course_name = $("#course_name").val();
var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");

//img.onclick = function(){
  modal.style.display = "block";
  modalImg.src = img.src;
 // captionText.innerHTML = img.alt;
//}

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
span.onclick = function() { 
  modal.style.display = "none";
 }
}

function save2() {
    window.open(canvas.toDataURL('image/png'));
    var gh = canvas.toDataURL('png');

    var a  = document.createElement('a');
    a.href = gh;
    a.download = 'image.png';

    a.click()
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



document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('update').onclick = function() {
		return Validation();
	};

	document.getElementById('degree_id').onchange = function() {
// 		getSemesterBYDegree(this);
		getProfessionBYDegree();
	};
	document.getElementById('upload_img_hid').onclick = function() {
		return imageView(this.value);
	};
	
	document.getElementById('ima').onclick = function() {
		return imageView();
	};
	
	document.getElementById('return_demovideo').onclick = function() {
		return demovideo(${Edit_ele_course_mstr_Details.id});
	};
	
});

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
<!-- end -->
