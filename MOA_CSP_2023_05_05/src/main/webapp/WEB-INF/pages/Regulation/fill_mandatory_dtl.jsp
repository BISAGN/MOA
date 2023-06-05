

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>

<title>Ministry of Ayush</title>

<!-- Favicons -->
<!-- <link href="assets/img/favicon.ico" rel="icon"> -->

<!-- Font CSS Files -->
<!-- <link href="assets/font/bootstrap-icons/bootstrap-icons.css" -->
<!-- 	rel="stylesheet"> -->

<!-- Vendor CSS Files -->
<!-- <link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet"> -->
<!-- <link href="assets/vendor/bootstrap/css/bootstrap.min.css" -->
<!-- 	rel="stylesheet"> -->
<!-- <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet"> -->

<!-- <!-- Template Main CSS File --> 
<!-- <link href="assets/css/style.css" rel="stylesheet"> -->
<!-- <link href="assets/css/custom_pages_style.css" rel="stylesheet"> -->
<!-- <link href="assets/css/responsive.css" rel="stylesheet"> -->


<!-- //// -->


<!-- <link rel="shortcut icon" href="admin/layout_file/images/favicon.png"> -->

<!-- <script src="admin/layout_file/bootstrap_5/bootstrap.bundle.min.js"></script> -->


<!-- <script type="text/javascript" src="admin/js/common/commonmethod.js"></script> -->
<!-- <script type="text/javascript" src="admin/js/AES_ENC_DEC/lib/aes.js"></script> -->
<!-- <script type="text/javascript" src="admin/js/AES_ENC_DEC/lib/pbkdf2.js"></script> -->
<!-- <script type="text/javascript" src="admin/js/AES_ENC_DEC/AesUtil.js"></script> -->

<!-- <link rel="stylesheet" href="admin/js/autoComplate/autoComplate.css"> -->

<!-- <script type="text/javascript" src="admin/js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<!-- <script type="text/javascript" src="js/watermark/common.js"></script> -->
<!-- <!-- <link rel="stylesheet" href="admin/js/InfiniteScroll_old/css/site.css"> -->
<!-- <link rel="stylesheet" href="admin/js/autoComplate/autoComplate.css"> -->

<!-- <link href="admin/js/dropDown/select2.min.css" rel="Stylesheet"></link> -->
<!-- <script src="admin/js/dropDown/select2.min.js" type="text/javascript"></script> -->



<!-- <link href="admin/js/jquery/jquery-ui.css" rel="Stylesheet"></link> -->
<!-- <script src="admin/js/jquery/jquery-ui.js" type="text/javascript"></script> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="admin/js/InfiniteScroll/css/datatables.min.css"> -->
<!-- <script type="text/javascript" -->
<!-- 	src="admin/js/InfiniteScroll/js/jquery.dataTables.min.js"></script> -->
<!-- <script type="text/javascript" -->
<!-- 	src="admin/js/InfiniteScroll/js/jquery.mockjax.js"></script> -->
 
<script>
	var username = "${username}";
</script>
<style>
.sidebar-nav-wrapper {
    -webkit-transition: none;
    -moz-transition: nones;
    -ms-transition: nones;
    -o-transition: none;
    transition: none;
    transition-property: none;
    transition-duration: none;
    transition-timing-function: none;
    width: 0;
    opacity: 0; 
}
.main-wrapper {
     -webkit-transition: none;
    -moz-transition: nones;
    -ms-transition: nones;
    -o-transition: none;
    transition: none;
}
</style>
</head>
<section class="dashboard-page edu_fill_dtl">
      <div class="container-fluid">
        <!-- title-wrapper start -->
        <div class="title-wrapper pt-30">
          <div class="row align-items-center">
            <div class="col-md-6">
              <div class="title mb-30">
                <h2> Obligatory Details</h2>
              </div>
            </div>
            <!-- end col -->
            <div class="col-md-6">
              <div class="breadcrumb-wrapper mb-30">
                <nav aria-label="breadcrumb">
                  <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                      <a href="#0">Start View</a>
                    </li>
<!--                     <li class="breadcrumb-item"><a href="#0">Regulation Forms</a></li> -->
                    <li class="breadcrumb-item active" aria-current="page">
                     Obligatory Details
                    </li>
                  </ol>
                </nav>
              </div>
            </div>
            <!-- end col -->
          </div>
          <!-- end row -->
        </div>
        <!-- title-wrapper end -->
        
             <div class="search-regulation-wrapper">
          <div class="row">
          
          <div class="col-lg-12">
              <!-- input style start -->
              <form:form name="fill_mandatory" id="fill_mandatory" action="fill_mandatory_action" method='POST' modelAttribute="fill_mandatory_cmd" enctype="multipart/form-data">
              <div class="card-style mb-30">
              
                <h6 class="mb-25"> Obligatory Details</h6>
               <div class="row">
              
              
              
<!--               <div class="col-lg-4 col-md-6 col-sm-12"> -->
<!--                <div class="input-style-2"> -->
<!--   				<label> Ayush Id /Abha No<strong class="mandatory">  </strong> </label> -->
<!--                  <input id="ayush_id" name="ayush_id" 	class="form-control-sm form-control effect-9 form-control form-control-lg form-control-a disablecopypaste" -->
<!-- 				 autocomplete="off" value="" maxlength="50" placeholder="Enter Your Ayush Id Or Abha Number " /> 
<!--                 </div> -->
<!--                </div> -->
              	 
              	 
              	 			<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-form-check">
														<div  class="input-style-2">
<!-- 														29-07-2022 Urmik -->
															<label> Please Select Any one : <strong class="mandatory">* </strong> </label>
															</div>
															<div class="form-check radio-style">
															<input type="radio" id="ayush_id_r" name="a_a_num" class="form-check-input" value="0" required checked/>
<!-- 															29-07-2022 Urmik -->
															<label for="ayush_id_r" class="form-check-label">Ayush ID </label>		
		  																																							
															</div>
															<div class="form-check radio-style">															
															<input type="radio" id="abha_no_r" name="a_a_num" class="form-check-input" value="1" required onclick="fnayushdiv()" />
															<label for="abha_no_r" class="form-check-label">ABHA No.</label> 														
															</div>
 				 														
														</div>
													</div>
              	 
              	 
              	 
              	 
              	      <div class="col-lg-4 col-md-6 col-sm-12">
               <div class="input-style-2" id="ayush_div" style="display: none">
<!--                -----------------------janki today -->
  				<label>Ayush ID <strong class="mandatory">  </strong> </label>
                <input id="ayush_id" 	name="ayush_id" 	class="form-control-sm form-control effect-9 form-control form-control-lg form-control-a disablecopypaste"
				 autocomplete="off" value="" maxlength="8" readonly="readonly" placeholder="Enter Your Ayush Id" /> 
                
                </div>
                
                <div class="input-style-2"  id="abha_div" style="display: none">
                <!--                -----------------------janki today -->
  				<label> ABHA No. <strong class="mandatory">  </strong> </label>
                 <input id="abha_no" name="abha_no" 	class="form-control-sm form-control effect-9 form-control form-control-lg form-control-a disablecopypaste"
				 autocomplete="off" value="" maxlength="50" placeholder="Enter Your ABHA No. " />
                </div>
                
               </div>
                
 
<!--                 <div class="col-lg-4 col-md-6 col-sm-12"> -->
<!--                  <div class="input-style-2"> -->
<!--                   <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off"> -->
<!--                   <label>Registration State <strong class="mandatory"> </strong></label> -->
<!--                   <select name="regisration_state" id="regisration_state" -->
<!-- 										class="form-control form-control-lg form-control-a disablecopypaste autocomplete"> -->
<!-- 										<option value="0">--Select--</option> -->
<%-- 										<c:forEach var="item" items="${MedStateName}" varStatus="num"> --%>
<%-- 											<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 										</c:forEach> --%>
<!-- 									</select> -->
<!--                    </div> -->
<!--                 </div> -->
                
        
                
                
<!--                    <div class="col-lg-4 col-md-6 col-sm-12"> -->
<!--                    <div class="input-style-2"> -->
<!--   				<label> University Name<strong class="mandatory">  </strong> </label> -->
<!--                  <select name="institute_name" class="form-control form-control-lg form-control-a disablecopypaste" -->
<!-- 													id="institute_name" placeholder="Enter Your Institute Name" -->
<!-- 													onchange="InstituteChangeFn(this.value)"> -->
<!-- 													<option value="0">--Select--</option> -->
<%-- 													<c:forEach var="item" items="${getInstituteList}" --%>
<%-- 														varStatus="num"> --%>
<%-- 														<option value="${item.id}" name="${item.id}">${item.institute_name}</option> --%>
<%-- 													</c:forEach> --%>
<!-- 												</select> -->
<!--                 </div> -->
<!--                </div> -->
            
           		<div id="hideshownew1" >
									<div class="row">
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label class=" form-control-label">Name<span
													class="mandatory"></span></label> <input type="text" id="name"
													name="name" maxlength="50"  readonly="readonly" class="form-control form-control-lg form-control-a disablecopypaste"
													placeholder="Enter Your Name" autocomplete="off">
											</div>
										</div>

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label class=" form-control-label">Email ID<span
													class="mandatory"></span></label> <input type="email"
													id="email_id" name="email_id" maxlength="50"  readonly="readonly"
													class="form-control form-control-lg form-control-a disablecopypaste"
													placeholder="Enter Your Email Id" autocomplete="off">
												<div class="col-lg-12">
													<input type="hidden" id="id" name="id" value="0"
														class="form-control form-control-lg form-control-a disablecopypaste"
														autocomplete="off" />
												</div>
											</div>
 									</div>
										
										
										<div class="col-lg-4 col-md-6 col-sm-12" id="aadhardiv">
										<div class="input-style-2">
												<label class=" mandatory">Aadhar Number<strong style="color: red;"></strong>
												</label>
											<input type="text" id="aadhaar_no" name="aadhaar_no" readonly="readonly"
																maxlength="14" class="form-control form-control-lg form-control-a disablecopypaste autocomplete" autocomplete="off"
																placeholder="Aadhaar Number">
	              			
										</div>
									</div>
										
										  <div class="col-lg-4 col-md-6 col-sm-12" id="hideshow_instituteid">
<!-- 										<div class="col-lg-6 col-md-6 col-sm-12 mb-3" id="hideshow_instituteid">  -->
											<div class="input-style-2">
												<label class=" form-control-label">University Name<span
													class="mandatory">*</span></label>
											<select name="institute_name" class="form-control form-control-lg form-control-a disablecopypaste"
												 id="institute_name" placeholder="Enter Your University Name"    >
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getInstituteList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.university_name}</option>
													</c:forEach>
											 </select>
											</div>
										</div>
           
										

<!-- 										<div class="col-lg-6 col-md-6 col-sm-12 mb-3" id="hideshow_stateid" > -->
										<div class="col-lg-4 col-md-6 col-sm-12" id="hideshow_stateid">
											<div class="input-style-2">
												<label class=" form-control-label">University State<span
													class="mandatory">*</span></label>
													 <select name="institute_state"
													id="institute_state" 
													class="form-control form-control-lg form-control-a disablecopypaste autocomplete"
													>
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${MedStateName}" 
 														varStatus="num"> 
 														<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
 													</c:forEach> 
												</select>
											</div>
										</div>
										
									 


<div class="col-lg-4 col-md-6 col-sm-12"> 

                 <div class="input-style-2">
                  <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
                  <label>Registration State <strong class="mandatory">*</strong></label>
                  <select name="regisration_state" id="regisration_state"
										class="form-control form-control-lg form-control-a disablecopypaste autocomplete">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${MedStateName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
                   </div>
                </div>
								 
<!-- 		//janki							 -->
<!--  <div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 								<div class="select-style-1"> -->
<!-- 									<label> University Name <strong class="mandatory"> </strong> </label>  -->
<!-- 									<input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off"> -->
<!-- 									<div class="select-position"> -->
<!-- 										<select name="institute_name" id="institute_name" class="form-control customselect" onchange="statusChange()"> -->
<!-- 											<option value="0">--Select--</option> -->
<%-- 													<c:forEach var="item" items="${getInstituteList}" varStatus="num"> --%>
<%-- 														<option value="${item.id}" name="${item.id}">${item.university_name}</option> --%>
<%-- 													</c:forEach> --%>
<!-- 										</select> -->
<!-- 									</div> -->

<!-- 								</div> -->
<!-- 							</div> -->
							 		 
           
                
                
        
                
                  <ul class="buttons-group mainbtn">
                
<!--                     <li> -->
<!--                     <input type="submit" class="main-btn secondary-btn btn-hover btn-save" value="Save" onclick="return Validate();"> -->
<!--                   </li> -->
                  <li>
					<input type="submit" 	class="main-btn success-btn  btn-hover btn-save" id="draft" value="Save"  >                   </li>
                    <li>
<!--                     <a href="fill_mandatory_Url" class="main-btn dark-btn n btn-hover" value="Reset">Reset</a> -->
                  </li>
                   
                </ul>
                </div>
               
              </div>
               </form:form>
              <!-- end card -->
          
            </div>
            
          </div>
          <!-- end row -->
        </div>
        
      
 

	 <br>
 
       
        
            </div>
        </section>


 


<script>

function fnayushdiv() {
 
 var a = $('input:radio[name=a_a_num]:checked').val();
	 
	if(a == "0"){
		$("#ayush_div").show();
		$("#abha_div").hide();
	}
	if(a == "1"){
		$("#abha_div").show();
		$("#ayush_div").hide();
	}
	
}





function newdatavalid() {
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
 	var newusername = '${username}';
	   
	$.post("newdatavalidfetch?" + key + "=" + value, { newusername : newusername }, function(j) {
		 
		if (j.length != 0) {
			$("#ayush_id").val(j[0][1]);
	 		$("#name").val(j[0][2]);
	 		 $("#email_id").val(j[0][3]);
	 		  $("select#institute_name").val(j[0][4]);
	 		 InstituteChangeFn(j[0][4])
	 		
 		}
		 
	});
}




//-----------------------------23-06-22 urmik autocomplete
$(document).ready(function() {
	
	
    $("#menu-toggle").hide();
	$(".sidebar-nav-wrapper").hide();
	$(".main-wrapper").css("margin-left","0"); 
	/* document.getElementById("menu-toggle").style.display = "none";
	document.getElementsByClassName("sidebar-nav-wrapper").style.display = "none";
	document.getElementsByClassName("main-wrapper").style.margin-left = "0"; */
	
	var user = '${username}' ;
	$("#aadhaar_no").val(user);
	
	
	newdatavalid();
	
	var a = $('input:radio[name=a_a_num]:checked').val();
	 
	if(a == "0"){
		$("#ayush_div").show();
		$("#abha_div").hide();
	}
	if(a == "1"){
		$("#abha_div").show();
		$("#ayush_div").hide();
	}
	
	
	
	
	
	
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";

	//		alert(ser);
	jQuery("#ayush_id")
			.keypress(
					function(evt) {
						debugger;
						if (evt.key.length == 1) {
							var job_no = evt.key;
						} else {
							var job_no = $("#ayush_id")
									.val()
									+ evt.key;
						}

						// 		alert(job_no);
						var jobNoAuto = jQuery("#ayush_id");
						jobNoAuto
								.autocomplete({
									source : function(
											request,
											response) {
										jQuery
												.ajax({
													type : 'POST',
													url : "getaayushid_Autocomplete?"
															+ key
															+ "="
															+ value,
													data : {
														getcolumnname : job_no
													},
													success : function(
															data) {
														// 			        	  alert(data);
														// 			        	  debugger;
														var jobval = [];
														var length = data.length - 1;
														if (data.length - 1 != 0) {
															var enc = data[length]
																	.substring(
																			0,
																			16);
														}
														for (var i = 0; i < data.length; i++) {
															jobval
																	.push(dec(
																			enc,
																			data[i]));
														}
														var dataCountry1 = jobval
																.join("|");
														var myResponse = [];
														var autoTextVal = jobNoAuto
																.val();
														jQuery
																.each(
																		dataCountry1
																				.toString()
																				.split(
																						"|"),
																		function(
																				i,
																				e) {
																			var newE = e
																					.substring(
																							0,
																							autoTextVal.length);
																			if (e
																					.toLowerCase()
																					.includes(
																							autoTextVal
																									.toLowerCase())) {
																				myResponse
																						.push(e);
																			}
																		});
														response(myResponse);
													}
												});
									},
									minLength : 1,
									autoFill : true,
									change : function(
											event, ui) {
										if (ui.item) {
											autocomplete_data_fetch();
											return true;

										} else {
											// 			        	  alert("Please Enteraayush id");
											document
													.getElementById("ayush_id").value = "";
											jobNoAuto
													.val("");
											jobNoAuto
													.focus();
											return false;
										}
									},
									select : function(
											event, ui) {
										var course = ui.item.value;

										// 			    	  $.post("getTotalDuration?" + key + "=" + value, {course:course}, function(j) {
										// 							if (j.length != 0) {
										// 								$("#max_duration"+ser).val(j[0][0]);
										// 							}
										// 						});
									}
								});
					});
	
	
	
	
	
	
});

</script>

<script>


// -----------------------------23-06-22 urmik autocomplete
function autocomplete_data_fetch() {
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	// 		alert($("#ayush_id").val());
	var ayush_id = $("#ayush_id").val();
	$.post("getaayushdataautocomplete?" + key + "=" + value, {
		ayush_id : ayush_id
	}, function(j) {
//						 alert(j[0][2]);
		if (j.length != 0) {
			$("#name").val(j[0][0]);
			$("#email_id").val(j[0][1]);
			$("#institute_name").val(j[0][2]);
			$("#institute_state").val(j[0][3]);
			$("#regisration_state").val(j[0][4]);
		}
	});

}


function InstituteChangeFn(id) {

	
//	alert(id);
	var institute_name = $("select#institute_name").val();

	$
			.post( "getStateDatabyInstitute?" + key + "=" + value,
					{
						institute_name : institute_name
					},
					function(j) {
						var options ='';
// 						var options = '<option value="' + "0" + '">'
// 								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#institute_state").html(options);
					});
// 					StateChangeFn($("select#institute_state").val());
}



function StateChangeFn(id) {

	
//	alert(id);
	var state_name = $("select#institute_state").val();

	$
			.post(
					"getInstituteDatabyState?" + key + "=" + value,
					{
						state_name : state_name
					},
					function(j) {

						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#institute_name").html(options);
					});
}

function Validate(){ 
	
	if ($("#ayush_id").val().trim() == "") {
		alert("Please Enter Ayush Id");
		$("input#ayush_id").focus();
		return false;
	}
	
	if ($("#name").val().trim() == "") {
		alert("Please Enter Name");
		$("input#name").focus();
		return false;
	}
	
	if ($("#email_id").val().trim() == "") {
		alert("Please Enter Email Id");
		$("input#email_id").focus();
		return false;
	}
	
	
	if ($("#aadhaar_no").val().trim() == "") {
		alert("Please Enter Aadhaar No");
		$("input#aadhaar_no").focus();
		return false;
	}
	
	
// 	----------------------29-07-2022 Urmik
	if ($("select#institute_name").val() == "0") {
		alert("Please Select University Name");
		$("select#institute_name").focus();
		return false;
	}
	
// 	----------------------29-07-2022 Urmik
	if ($("select#institute_state").val() == "0") {
		alert("Please Select University State ");
		$("select#institute_state").focus();
		return false;
	}
	
	
// 	----------------------29-07-2022 Urmik
	if ($("select#regisration_state").val() == "0") {
		alert("Please Select Registration State");
		$("select#regisration_state").focus();
		return false;
	}
	
	
}

document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('ayush_id_r').onclick = function() {
	 fnayushdiv();
	};
	
	document.getElementById('name').oninput = function () {
		this.value = this.value.toUpperCase()
	};
	
	document.getElementById('aadhaar_no').onkeypress = function() {
		return isAadhar(this,event);
	};
	
	document.getElementById('aadhaar_no').oninput = function () {
		this.value = this.value.toUpperCase()
	};
	
	document.getElementById('institute_name').onchange = function() {
		InstituteChangeFn(this.value);
	};
	
	document.getElementById('institute_state').onchange = function() {
		StateChangeFn(this.value);
	};
	
	document.getElementById('draft').onclick = function() {
		return Validate();
		};
	
	
// 	document.getElementById('per_state').onchange = function() {
// 		return   changeAddress(); getDistrict();
// 	};
});

</script>
</body>
</html>