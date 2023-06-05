<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="js/watermark/common.js"></script>
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

<!-- <script src="js/Calender/jquery-ui.js" type="text/javascript"></script> -->
<!-- <link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css"> -->
<!-- <link rel="stylesheet" href="assets/font/bootstrap-icons/bootstrap-icons.css"> -->
<!-- <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css" /> -->
<!-- datatable style and js end-->

<!-- <style> -->
<!--  div#ui-datepicker-div { -->
<!--      z-index: 1500 !important; -->
<!--  }  -->
<!-- </style> -->
<section class="dashboard-page">
	<div class="container-fluid free-course-view">

		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Skill Enhancement</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Skill
									Enhancement</li>
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
					<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Skill Enhancement</h6>
							<div type="hidden" class="row" id="freecoursedatadiv"></div>
						</div>
						<!-- end card -->
					</div>
				</div>
			</div>
		</div>
	</div>
</section>


<div class="modal fade custom-modal bd-example-modal-lg" tabindex="-1"
	role="dialog" aria-labelledby="myLargeModalLabel" id="askquerymodal"
	aria-hidden="true">
	<div class="modal-dialog modal-xl modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Upload Certificate</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>


			<!-- 				<h3 class="modal-title" id="myLargeModalLabel">UPLOAD CERTIFICATES</h3> -->
			<!-- 				<i data-dismiss="modal" aria-label="Close" class="bi bi-x-lg"></i> -->
			<!-- 			</div> -->

			<c:url value="uplode_certificateUrl" var="uplode_certificateUrl" />
			<form:form action="${uplode_certificateUrl}" id="popup_Form_submit"
				modelAttribute="UplodeCertificate_CMD" name="popup_Form_submit"
				method="post" enctype="multipart/form-data">


				<div class="modal-body">
					<div class="custom-modal-inner" id="headData1" name="headData1">
						<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-1">
									<label for="username">Upload Certificate<span
										class="mandatory">*</span></label> <input type="file" accept=".pdf"
										id="uplode_certificate" name="uplode_certificate"
										class="form-control">
									<!-- 									<input type="hidden" id="id" name="id" value="0" class="mt-3 form-control" autocomplete="off" /> -->
								</div>
								<!-- end input -->
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-1">
									<label for="username">Course Name<span
										class="mandatory"></span></label> <input id="coursename"
										name="coursename" readonly="readonly" autocomplete="off" />
									<!-- 									<input type="hidden" id="id" name="id" value="0" class="mt-3" autocomplete="off" /> -->
								</div>
								<!-- end input -->
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="username">Start Date<span class="mandatory">*</span></label>
									<input type="text" name="start_date" id="start_date"
										maxlength="10" class="form-control-sm form-control "
										aria-required="true" autocomplete="off" value="DD/MM/YYYY"
										placeholder="Select Start Date" />
									<!-- 									<input type="hidden" id="id" name="id" value="0" class="mt-3" autocomplete="off" /> -->
								</div>
							</div>

							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="username">End Date<span class="mandatory">*</span></label>
									<input type="text" name="end_date" id="end_date" maxlength="10"
										class="form-control-sm form-control " aria-required="true"
										autocomplete="off" value="DD/MM/YYYY"
										placeholder="Select End Date" />
									<!-- 									<input type="hidden" id="id" name="id" value="0" class="mt-3" autocomplete="off"  /> -->
								</div>

							</div>
						</div>
					</div>
					</div>
					
					
					<!-- Bottom Button Start -->
					<div class="modal-footer">
							<ul class="buttons-group">
										<li><input class="main-btn success-btn  btn-hover btnsubmit"
											id="submit" type="submit" /></li>
										<li><a href="Free_Course_ViewUrl"
											class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a>
										</li>
									</ul>
								</div>
					
					<!-- Bottom Button End -->
				<input type="hidden" name="id3" id="id3" value="0" />
			</form:form>
		</div>
	</div>
</div>



<!-- ask query modal start -->
<!-- <div class="modal fade custom-modal" id="askquerymodal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true"> -->
<!--   <div class="modal-dialog modal-dialog-centered"> -->
<!--     <div class="modal-content"> -->
<!--       <div class="modal-header"> -->
<!--         <h3 class="modal-title" id="staticBackdropLabel">You may ask your query</h3> -->
<!--         <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> -->
<!--       </div> -->
<!--       <div class="modal-body"> -->
<!--         <div class="custom-modal-inner" id="headData" name="headData"> -->

<%--         <c:url value="uplode_certificateUrl" var="uplode_certificateUrl" /> --%>
<%-- <form:form action="${uplode_certificateUrl}"  id="popup_Form_submit" modelAttribute="UplodeCertificate_CMD" --%>
<%-- 	name="popup_Form_submit"  method="post" enctype="multipart/form-data"> --%>


<!--         	<div class="card-style mb-30"> -->
<!-- 					<h6 class="mb-25">UPLOAD CERTIFICATES</h6> -->
<!-- 						<div class="row"> -->
<!-- 							<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
<!-- 								<div class="input-style-1"> -->
<!-- 									<label for="username">Upload Certificate<span class="mandatory">*</span></label>  -->
<!-- 									<input type="file" accept=".pdf" id="uplode_certificate" name="uplode_certificate" class="form-control"> -->
<!-- 									<input type="hidden" id="id" name="id" value="0" class="mt-3 form-control" autocomplete="off" /> -->
<!-- 								</div> -->
<!-- 								end input -->
<!-- 							</div> -->
<!-- 							<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
<!-- 								<div class="input-style-1"> -->
<!-- 									<label for="username">Course Name<span class="mandatory"></span></label>  -->
<!-- 									<input id="coursename" name="coursename" readonly="readonly" autocomplete="off" /> -->
<!-- 									<input type="hidden" id="id" name="id" value="0" class="mt-3" autocomplete="off" /> -->
<!-- 								</div> -->
<!-- 								end input -->
<!-- 							</div> -->
<!-- 							<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
<!-- 									<div class="input-style-1"> -->
<!-- 									<label for="username">Start Date<span class="mandatory">*</span></label>  -->
<!-- 									<input type="text" name="start_date" id="start_date" -->
<!-- 									maxlength="10"  -->
<!-- 									class="form-control-sm form-control " -->
<!-- 									aria-required="true" autocomplete="off" -->
<!-- 									style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"  placeholder="Select Start Date" /> -->
<!-- 									<input type="hidden" id="id" name="id" value="0" class="mt-3" autocomplete="off" /> -->
<!-- 								</div>																											 -->
<!-- 								</div> -->

<!-- 								<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
<!-- 									<div class="input-style-1"> -->
<!-- 									<label for="username">End Date<span class="mandatory">*</span></label>  -->
<!-- 									<input type="text" name="end_date" id="end_date" maxlength="10" -->
<!-- 									class="form-control-sm form-control " -->
<!-- 									aria-required="true" autocomplete="off" -->
<!-- 									style="color: rgb(0, 0, 0);" value="DD/MM/YYYY" placeholder="Select End Date" /> -->
<!-- 									<input type="hidden" id="id" name="id" value="0" class="mt-3" autocomplete="off"  /> -->
<!-- 									</div>																											 -->

<!-- 								</div> -->

<!-- 						</div>					 -->

<!-- 						<ul class="buttons-group mainbtn"> -->

<!-- 							<li> -->
<!-- 								<input class="main-btn info-btn btn-hover"  id="submit" type="submit"/> -->
<!-- 							</li> -->
<!-- 							<li> -->
<!-- 								<a href="Free_Course_ViewUrl" class="main-btn dark-btn n btn-hover" type="button">Reset</a> -->
<!-- 							</li> -->
<!-- 						</ul> -->
<!-- 				</div> -->

<!--         <input type="hidden" name="id3" id="id3" value="0" /> -->
<%-- </form:form> --%>

<!--         </div> -->
<!--       </div> -->

<!--     </div> -->
<!--   </div> -->
<!-- </div> -->
<!-- ask query modal end -->





<c:url value="Up_CertificateUrl" var="Up_CertificateUrl" />
<form:form action="${Up_CertificateUrl}" method="post" id="savedateForm"
	name="savedateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="uplode_certificate" var="uplode_certificate" />
<form:form action="${uplode_certificate}" method="post" id="popup_Form"
	name="popup_Form" target="result">
	<input type="hidden" name="id3" id="id3" value="0" />
</form:form>

<c:url value="getDownloadPdfonlineCourse" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="downloadPdf"
	name="downloadPdf" modelAttribute="doc_id1">
	<input type="hidden" name="pageUrl" id="pageUrl"
		value="redirect:Free_Course_ViewUrl" />
	<input type="hidden" name="doc_id1" id="doc_id1" value="" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	
	 if('${list.size()}' == ""){
		   $("div#search_Alumniview").hide();
		 }
	 data2();
	 
	 if(window.location.href.includes("doc_id1"))
		{
			 var url = window.location.href.split("?doc_id1")[0];
			 window.location = url;
		}
	 else
	 if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}
	 
	    datepicketDate('start_date');
		datepicketDate('end_date');
		
		$( "#start_date" ).datepicker("option", "minDate", 0);
	 	$( "#end_date").datepicker("option", "minDate", 0);
	 	
		$( "#start_date").datepicker( "option", "maxDate", null);
		$( "#end_date").datepicker( "option", "maxDate", null);
});





function data2(){
$.post("getFilterFc_url_data2?" + key + "=" + value, {}, function(j) {
		for (var i = 0; i < j.length; i++) {
			
			if(i == 0){
				$( "div#freecoursedatadiv" ).append("<div class='col-12 col-sm-12 col-md-12 col-lg-3'><div class='card-style card-box'><div class='card-head card-box-head'><h4 class='title-head'><a href='#'>"+j[i][0]+"</a></h4></div><div class='card-content'><div class='card-link text-overwrap card-box-link'><a class='#'>"+j[i][2]+"</a></div><div class='card-detail'><p class='card-box-text'>"+j[i][1]+"</p><a class='readmore' href='Free_Course_ViewUrl'>Read more ></a></div><div class='card-box-footer'><ul class='buttons-group mainbtn'><li id='popskill1"+j[i][4]+"' ><a href='#' class='main-btn info-btn btn-hover btndownload'><i class='lni lni-download mr-5'></i>Download</a></li><li class='course-btn' id='popskill"+j[i][4]+"' ><a href='#' class='main-btn secondary-btn btn-hover btncerti'><i class='bi bi-mortarboard mr-5'></i>Certificate</a></li></ul></div></div></div></div>");
				setpopskill(j[i][4]);
				setpopskill1(j[i][4]);
// 				href='Free_Course_ViewUrl"+j[i][4]+"'
			}
			else{
				$( "div#freecoursedatadiv" ).append("<div class='col-12 col-sm-12 col-md-12 col-lg-3'><div class='card-style card-box'><div class='card-head card-box-head'><h4 class='title-head'><a href='#'>"+j[i][0]+"</a></h4></div><div class='card-content'><div class='card-link text-overwrap card-box-link'><a class='#'>"+j[i][2]+"</a></div><div class='card-detail'><p class='card-box-text'>"+j[i][1]+"</p><a class='readmore' href='Free_Course_ViewUrl'>Read more ></a></div><div class='card-box-footer'><ul class='buttons-group mainbtn'><li id='popskill1"+j[i][4]+"' ><a href='#' class='main-btn info-btn btn-hover btndownload'><i class='lni lni-download mr-5'></i>Download</a></li><li class='course-btn'  id='popskill"+j[i][4]+"'><a href='#' class='main-btn secondary-btn btn-hover btncerti'><i class='bi bi-mortarboard mr-5'></i>Certificate</a></li></ul></div></div></div></div>");
				setpopskill(j[i][4]);
				setpopskill1(j[i][4]);
			}
			
		}
	});
}


function Uplode_Certificate(id) {
	$("#id1").val(id);
	document.getElementById('savedateForm').submit();
}

function download_file(id){  
	$("#doc_id1").val(id); 
	document.getElementById("downloadPdf").submit();
} 

//////POPUP HISTORY/////////////////////////////////

function Pop_Up_History1(id) {
		
		$.post( "uploadcertificatePopup?" + key
								+ "=" + value,
						{
								updateid : id
						},
						function(j) {
							$('#coursename').val(j.coursename);
						});
		
		$('#askquerymodal').modal('show');
		
	}





function setpopskill(val){
	document.getElementById('popskill'+val).onclick = function () {
		Pop_Up_History1(val);
	};	
}
function setpopskill1(val){
	document.getElementById('popskill1'+val).onclick = function () {
		download_file(val)
	};	
}



document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('start_date').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
});
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('start_date').onfocus = function() {
		return this.style.color='#000000';
	};
});
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('start_date').onblur = function() {
		return clickrecall(this,'DD/MM/YYYY');
		//validateDate_BackDate(this.value,this);
	};
});
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('start_date').onkeyup = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
});
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('start_date').onchange = function() {
		 onchangeCount(this.value);
		 return clickrecall(this,'DD/MM/YYYY');
		//validateDate_FutureDate(this.value,this);
	};
});
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('end_date').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
});
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('end_date').onfocus = function() {
		 this.style.color='#000000';
	};
});
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('end_date').onblur = function() {
		return clickrecall(this,'DD/MM/YYYY');
	};
});
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('end_date').onkeyup = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
});
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('end_date').onchange = function() {
		onchangeCount(this.value);
		clickrecall(this,'DD/MM/YYYY');
		checkTodate(this,start_date);
		
	};
});

document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('submit').onclick = function() {
		return Validate();
	};
});

function Validate() {
	
	if ($("#uplode_certificate").val().trim() == "") {
		alert("Please Uplode Certificate");
		$("#uplode_certificate").focus();
		return false;
	}
	var uplode_certificate = $("#uplode_certificate").val();
	var lastDot = uplode_certificate.lastIndexOf('.');
	var fileName = uplode_certificate.substring(0, lastDot);
	var ext = uplode_certificate.substring(lastDot + 1);
	
	if(ext != "pdf"){
		alert("Please Upload PDF File");
		$("input#uplode_certificate").focus();
		return false;
	}
	
	
	if ($("#start_date").val() == "" || $("#start_date").val() == "DD/MM/YYYY") {
		alert("Please Select Start Date");
		$("#start_date").focus();
		return false;
	}
	if ($("#end_date").val() == "" || $("#end_date").val() == "DD/MM/YYYY") {
		alert("Please Select End Date");
		$("#end_date").focus();
		return false;
	}
	return true;
}
</script>
;
