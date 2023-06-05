<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>


<section class="dashboard-page editprofile">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span>Edit Profile
						</h2>
					</div>
				</div>
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Edit Profile</li>
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
					 <form:form  name="course" id="Edit_Profile" action="Edit_ProfileAction"
					  method='POST' commandName="Edit_ProfileCMD"   enctype="multipart/form-data">
						<div class="card-style mb-30">
							
							 <div class="details">
			 <h4 class="line_text">Technical Details </h4>
			 
			 <div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">

								    
								      <div>
									<img class='d-block img5050 imageZomm' alt="No Image" id="myImg" src="MedicalImagePathC?i_id=${GetTechnical_Details_Data[0].id}" />
								     </div>
								     </div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Degree</label>
									<div class="select-position">
										<select name="degree_id" id="degree_id" class="form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getDegreeList}" varStatus="num">
													<option value="${item.id}" name="${item.degree_name}">${item.degree_name}</option>
												</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Specialization</label>
									<div class="select-position">
										<select name="specialization_id" id="specialization_id">
											<option value="0">--Select--</option>
											<option value="1">Eye Specialist</option>
											<option value="2">Surgeon</option>
											<option value="3">Neurosurgery</option>
										</select>
									</div>
								</div>
							</div>

							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Others</label>
									<input type="text" id="others" name="others"
										class="form-control" autocomplete="off"
										placeholder="Others">
								</div>
							</div>
							</div>
							</div>
							
							 <div class="details">
			 <h4 class="line_text">Basic Details</h4>
			 
			 <div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Name</label>
									<input type="text" id="alum_name" name="alum_name"
										class="form-control" autocomplete="off"
										placeholder="Name">
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Address</label>
									<textarea id="alum_address" name="alum_address" placeholder="Address">${GetTechnical_Details_Data[0].alum_address}</textarea>
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Email Id</label>
									<input type="text" id="alum_email" name="alum_email"
										class="form-control" autocomplete="off"
										placeholder="Email Id">
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Mobile No.</label>
									<input type="text" id="alum_mo_no" name="alum_mo_no"
										class="form-control" autocomplete="off"
										placeholder="Mobile No.">
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Instagram Id</label>
									<input type="text" id="alum_insta_id" name="alum_insta_id"
										class="form-control" autocomplete="off"
										placeholder="Instagram Id">
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Facebook Id</label>
									<input type="text" id="alum_fb_id" name="alum_fb_id"
										class="form-control" autocomplete="off"
										placeholder="Facebook Id">
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Linkedin Id</label>
									<input type="text" id="alum_linkdin_id" name="alum_linkdin_id"
										class="form-control" autocomplete="off"
										placeholder="Linkedin Id">
								</div>
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Current Occupation</label>
									<input type="text" id="alum_curr_occu" name="alum_curr_occu"
										class="form-control" autocomplete="off"
										placeholder="Current Occupation">
								</div>
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Passing Year</label>
									<input type="text" id="alum_passing_year" name="alum_passing_year"
										class="form-control" autocomplete="off"
										placeholder="Passing Year">
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Batch</label>
									<input type="text" id="alum_batch" name="alum_batch"
										class="form-control" autocomplete="off"
										placeholder="Batch">
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
							<div class="select-style-1">
										<label>Sector<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="sector" id="sector" class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${sector_id}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
							</div>
							</div>
							
							<div class="details">
			 <h4 class="line_text">Upload Image </h4>
			 
			 <div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-6">
									<div class="input-style-2">
										<label for="text-input">Upload Image</label>
											<div class="d-flex">
									<input type="file" accept="image/*" id="alum_photo" name="alum_photo" class="form-control"> 
									<input type="hidden" id="upload_img_hid" name="upload_img_hid" class="form-control" >
<!-- 									onclick="return imageView(this.value);"  -->
                                     <div hidden="hidden">
									<img class='d-block img5050 imageZomm' alt="No Image" id="myImg" src="MedicalImagePathC?i_id=${GetTechnical_Details_Data[0].id}" />
								     </div>
								     
								    
								     </div>
								    </div> 
								</div> 
								</div>
								</div>

 									
								
								
							<input type="hidden" id="count_hidden_att"
								name="count_hidden_att" class="form-control autocomplete"
								value="1">

							<ul class="buttons-group mainbtn">
<!-- 								<li> -->
<!-- 							    <a id="btn-view" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button" ><i class="lni lni-view-alt"></i>View</a> -->
<!-- 								</li> -->
								<li><input value="Update" id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" /></li>
								<li><a href="Technical_Details_Url"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
							</ul>
							<input type='hidden' id='id' name="id" value='0' />
						</div>

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
        <span class="close">&times;</span> 
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

	


<c:url value="Edit_Profile_Url" var="Edit_Profile_Url" />
	<form:form action="${Edit_Profile_Url}" method="post" id="editForm" name="editForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {

$('#id').val('${GetTechnical_Details_Data[0].id}');

// alert("iimm----"+'${GetTechnical_Details_Data[0].alum_photo}')

$('input#upload_img_hid').val('${GetTechnical_Details_Data[0].alum_photo}');
$('#degree_id').val('${GetTechnical_Details_Data[0].degree_id}');
$('#specialization_id').val('${GetTechnical_Details_Data[0].specialization_id}');
$('#others').val('${GetTechnical_Details_Data[0].others}');
$('#alum_name').val('${GetTechnical_Details_Data[0].alum_name}');
$('#alum_email').val('${GetTechnical_Details_Data[0].alum_email}');
$('#alum_mo_no').val('${GetTechnical_Details_Data[0].alum_mo_no}');
$('#alum_insta_id').val('${GetTechnical_Details_Data[0].alum_insta_id}');
$('#alum_fb_id').val('${GetTechnical_Details_Data[0].alum_fb_id}');
$('#alum_linkdin_id').val('${GetTechnical_Details_Data[0].alum_linkdin_id}');
$('#alum_curr_occu').val('${GetTechnical_Details_Data[0].alum_curr_occu}');
$('#alum_passing_year').val('${GetTechnical_Details_Data[0].alum_passing_year}');
$('#alum_batch').val('${GetTechnical_Details_Data[0].alum_batch}');
$('#sector').val('${GetTechnical_Details_Data[0].sector}');
$('#sector').trigger('change');

});

//Get the modal
function imageView(){


var modal = document.getElementById("myModal");
var span = document.getElementsByClassName("close")[0];

//debugger;
// Get the image and insert it inside the modal - use its "alt" text as a caption
var img = document.getElementById("myImg");

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



	function getdegreelistbysystem() {
		var system_name = $("#system_id").val();
		$
				.post('getDegreeListbysystem?' + key + "=" + value, {
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

	function EditData(id) {
		$("#id1").val(id);
		document.getElementById('editForm').submit();
	}
	
	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
	});


	function addOnclick(index) {
		document.getElementById('id_add_att' + index).onclick = function() {
			formopen_att(index);
		};
		document.getElementById('lecture_hours' + index).onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('non_lecture_hours' + index).onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
	}
	function removeOnclick(index) {
		document.getElementById('att_id_remove' + index).onclick = function() {
			formopen_re_att(index);
		};
	}
	
	
	
</script>