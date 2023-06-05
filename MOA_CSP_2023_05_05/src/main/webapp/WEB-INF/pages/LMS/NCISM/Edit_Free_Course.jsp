<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="js/watermark/common.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<!-- <style>
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


</style> -->

<section class="dashboard-page">
<div class="container-fluid">

	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>Skill Enhancement</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">Skill Enhancement</li>
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
			<div class="col-12">
				<!-- input style start -->
               <form:form action="edit_Online_Coures_Action" method="POST" class="form-horizontal" modelAttribute="edit_OnlinCouresCMD"  enctype="multipart/form-data">
					<div class="card-style mb-30">
					<h6 class="mb-25">Skill Enhancement</h6>
						<div class="row">

							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-2">
									<label for="username">Course<span class="mandatory">*</span></label>
									<input id="coursename" name="coursename" placeholder="Course Name" class="autocomplete UpperClassName txt-transupp"
										autocomplete="off" onkeypress="return onlyAlphabetsStringSpace(this,event);" />
								</div>
								<div class="input-style-2 mt-3">
									<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
								</div>
								<!-- end input -->
							</div>
					
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-2">
									<label for="username">File Uplode<span class="mandatory">*</span></label>
									<input type="file" accept=".pdf" id="upload_file" name="upload_file" class="form-control"
										autocomplete="off" />
								</div>
								<div class="input-style-2 mt-3">
									<input type="hidden" id="hid_upload_file" name="hid_upload_file" value="0" autocomplete="off" />
								</div>
								<!-- end input -->
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-2">
									<label for="username">Url<span class="mandatory">*</span></label>
									<input id="url" name="url" placeholder="EX.WWW.GOOGLE.COM" class="autocomplete UpperClassName txt-transupp"
										autocomplete="off" />
								</div>
								<div class="input-style-2 mt-3">
									<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
								</div>
								<!-- end input -->
							</div>
								
							
							<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="input-style-2">
										<label for="username">Start Date<span class="mandatory">*</span></label> 
												<input type="text" name="start_date" id="start_date" maxlength="10"
																class="form-control-sm form-control effect-9 "
																aria-required="true" autocomplete="off"
																value="DD/MM/YYYY" placeholder="Select Start Date">
											</div>	
											<div class="input-style-2 mt-3">
													<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
											</div>																																			
								</div>
								
										<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="input-style-2">
															<label for="username">End Date<span class="mandatory">*</span></label> 
															<input type="text" name="end_date" id="end_date" maxlength="10" 
															class="form-control-sm form-control effect-9 " 
															aria-required="true" autocomplete="off" value="DD/MM/YYYY" placeholder="Select End Date" >
															
												</div>
												<div class="input-style-2 mt-3">
													<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
												</div>
										</div>
										
										
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-1">
									<label for="username">Description<span class="mandatory">*</span></label>
									<textarea type="text" id="description" name="description" rows="5" cols="50" maxlength="250"
											placeholder="Description" autocomplete="off"></textarea>
								</div>
								<div class="input-style-2 mt-3">
									<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
								</div>
								<!-- end input -->
							</div>	

									</div>
										<div class="input-style-2 mt-3">
												<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
										</div>	

						<ul class="buttons-group mainbtn">

							<!-- <li>
							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button" value="Search"><i class="lni lni-search-alt"></i>search</a>
							</li> -->
							<li>
			                    <a href="Online_Coures_Url" class="main-btn dark-btn-outline  btn-hover btn-iconic-icon"><i class="lni lni-chevron-left"></i>Back</a>
			                </li>
							<li>
								<input type="submit" id="btn-save" class="main-btn deactive-btn btn-hover" value="Update">
							</li> 
							<li>
								<input type="reset" class="main-btn dark-btn n btn-hover" value="Reset">
							</li>
						</ul>
				</div>
	

				</form:form>
			</div>
		</div>
	</div>

</div>
</section>

<!-- 		old			 -->

<%-- <form:form action="edit_Online_Coures_Action" method="POST" class="form-horizontal"
	modelAttribute="edit_OnlinCouresCMD"  enctype="multipart/form-data">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header title">
				<h5>
					<span id="lbladd"></span> <b> FREE ONLINE COURSES</b>
				</h5>
			</div>
						<div class="card-body card-block">
				<div class="row">
					<div class="col-md-6 left_content">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="username"> COURSE NAME<span class="star_design"></span> <span style="color:#ff0000">*</span></label>
							</div>
							<div class="col-md-8">
								<input id="coursename" name="coursename"
									class="form-control" autocomplete="off" maxlength="50"
									placeholder=" " onkeypress="return onlyAlphabetsStringSpace(this,event);"/>
								<div class="col-md-6">
									<input type="hidden" id="id" name="id" value="0" class="form-control"
										autocomplete="off" />
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-6 left_content">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="username"> FILE UPLODE<span class="star_design"></span></label>
							</div>
							<div class="col-md-8">
								<input type="file" accept=".pdf" id="upload_file"
												name="upload_file" class="form-control effect-9">
								<div class="col-md-6">
									<input type="hidden" id="hid_upload_file" name="hid_upload_file" value="0" class="form-control"
										autocomplete="off" />
								</div>
							</div>
						</div>
					</div>
					</div>
					</div>
				<div class="card-body card-block">
				<div class="row">
					<div class="col-md-6 left_content">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="username">URL<span class="star_design"></span> <span style="color:#ff0000">*</span></label>
							</div>
							<div class="col-md-8">
								<input id="url" name="url" class="form-control" autocomplete="off" placeholder="EX.WWW.GOOGLE.COM"/>
								<div class="col-md-6">
									<input type="hidden" id="id" name="id" value="0" class="form-control"
										autocomplete="off" />
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-6 left_content">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="username"> DESCRIPTION <span class="star_design"></span> <span style="color:#ff0000">*</span></label>
							</div>
							<div class="col-md-8">
									<textarea type="text" id="description" name="description" class="form-control"  autocomplete="off"></textarea>
								<div class="col-md-6">
									<input type="hidden" id="id" name="id" value="0" class="form-control"
										autocomplete="off" />
								</div>
							</div>
						</div>
					</div>
					</div>
			
					
				</div>
					<div class="card-body card-block">
				<div class="row">
					<div class="col-md-6 left_content">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="username"> START DATE<span class="star_design"></span></label>
							</div>
							<div class="col-md-6">
								<input type="text" name="start_date" id="start_date"
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
								<div class="col-md-6">
									<input type="hidden" id="id" name="id" value="0" class="form-control"
										autocomplete="off" />
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-6 left_content">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="username"> END DATE <span class="star_design"></span></label>
							</div>
							<div class="col-md-6">
								<input type="text" name="end_date" id="end_date"
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
								<div class="col-md-6">
									<input type="hidden" id="id" name="id" value="0" class="form-control"
										autocomplete="off" />
								</div>
						</div>
					</div>
					</div>
			
					</div>
				</div>
				
	               <input type="hidden" name="id" id="id" value="0" />

			<div class="card-footer" align="center">
			 <a href="Online_Coures_Url" id=""	class="btn-cancel">BACK</a>
				  <button type="reset" class="btn-clear" value="Reset" onclick="clearall();"> RESET</button>
				<input type="submit" class="btn-update" value="UPDATE" onclick="return Validation();"> 
			</div>


		</div>
	</div>
</form:form> --%>



<script nonce="${cspNonce}" type="text/javascript">


$(document).ready(function() {
	$.ajaxSetup({
		async : false
	});	
	datepicketDate('start_date');
	datepicketDate('end_date');
	
	$( "#start_date" ).datepicker("option", "minDate", 0);
 	$( "#end_date").datepicker("option", "minDate", 0);
 	
	$( "#start_date").datepicker( "option", "maxDate", null);
	$( "#end_date").datepicker( "option", "maxDate", null);
	
	
	$('#id').val('${Onlinecourese.id}');
	$('input#coursename').val('${Onlinecourese.coursename}');
	$('input#hid_upload_file').val('${Onlinecourese.upload_file}');
	$('input#url').val('${Onlinecourese.url}');
	$('input#start_date').val('${Onlinecourese.start_date}');
	$('input#end_date').val('${Onlinecourese.end_date}');
	$('textarea#description').val('${Onlinecourese.description}');
});


	function Validation() {

		if ($("#coursename").val().trim() == "") {
			alert("Please Enter Course Name.");
			$("input#coursename").focus();
			return false;
		}
		if ($("#hid_upload_file").val().trim() == "") {
			alert("Please Upload the File");
			$("#hid_upload_file").focus();
			return false;
		}
		if ($("#url").val().trim() == "") {
			alert("Please Enter Url.");
			$("input#url").focus();
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
		if ($("#description").val().trim() == "") {
			alert("Please Enter Description.");
			$("textarea#description").focus();
			return false;
		}

		return true;

	}
	
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('coursename').onkeypress = function() {
			onlyAlphabetsStringSpace(this,event);
		};

		document.getElementById('start_date').onclick = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		
		document.getElementById('start_date').onfocus = function() {
			this.style.color='#000000';
		};
		document.getElementById('start_date').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');
// 			validateDate_BackDate(this.value,this);
		};
		document.getElementById('start_date').onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('start_date').onchange = function() {
			onchangeCount(this.value);
			clickrecall(this,'DD/MM/YYYY');
			//validateDate_FutureDate(this.value,this);
		};
		document.getElementById('end_date').onclick = function() {
			 clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('end_date').onfocus = function() {
			 this.style.color='#000000';
		};
		document.getElementById('end_date').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');
         //validateDate_BackDate(this.value,this);
		};
		document.getElementById('end_date').onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('end_date').onchange = function() {
			checkTodate(this,start_date);
			onchangeCount(this.value);
			clickrecall(this,'DD/MM/YYYY');
			//validateDate_FutureDate(this.value,this);
		};
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
	});
</script>





