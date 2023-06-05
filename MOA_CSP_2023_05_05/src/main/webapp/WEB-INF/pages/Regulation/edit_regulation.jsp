<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<script>
	var username = "${username}";
</script>
<style>

.s010 form {
    width: 100%;
    max-width: 100%;
    margin: 0;
}.s010 {
    /* min-height: 53vh !important; */
    display: -ms-flexbox;
    display: flex;
    -ms-flex-pack: center;
    justify-content: center;
    -ms-flex-align: center;
    align-items: center;
    background: transparent;
    padding: 15px;
    font-family: 'Lato', sans-serif;
}
.s010 form .inner-form .input-field input {
    background: linear-gradient(to right, #175658 0%, #175658 28%, #2e9970 91%, #2e9970 100%);
}
.s010 form .inner-form .basic-search:hover{
   transform: scale(1.01);
}
.inner-form label {
    display: inline-block;
    color: #175658;
    font-weight: bold;
}
.inner-form .form-control {
    color: #175658;
    border: 2px solid #175658;
}
.advance-search input::placeholder {
    color: #175658 !important;
}
.input-field input::placeholder {
  color: #fff !important;
  font-weight: bold !important;
}
.s010 form .inner-form .input-field .btn-search {
/*  background: linear-gradient(to right, #175658 0%, #175658 28%, #2e9970 91%, #2e9970 100%); */
}
.btn-select {
    background-color: #175658;
}
.btn-select:before {
    position: absolute;
    content: "";
    border-top: 5px solid #1f6e61;
    border-left: 5px solid #257d66;
    border-right: 5px solid #2e9970;
    border-bottom: 5px solid #2d976f;
    top: 0px;
    right: 0px;
    bottom: 0px;
    left: 0px;
    transition: 0.5s;
}
.btn-select:hover {
    box-shadow: 5px 5px #175658, -5px -5px #2e9970;
    cursor: pointer;
}
 .btn-select {
    min-width: max-content;
    position: relative;
    display: inline-block;
    text-align: center;
    text-transform: uppercase;
    padding: 8px 15px;
    margin: 0px 2%;
    font-size: 120%;
    font-weight: 600;
    color: #ecf0f1;
    transition: 0.5s;
    border: 0;
}

.btn-select:hover::before {
	border: 0px;
}
.section-heading {
    background: #2e9970;
    width: fit-content;
    margin: auto;
    padding: 5px 20px;
    color: white;
    box-shadow: 0px 0px 10px #175658;
}
.section-heading h2 {
    font-weight: bold;
}
.form-control:focus {
    box-shadow: 0 0 0 0.25rem rgb(46 153 112 / 47%);
}
.container {
	min-width: 90%;
}

.dataTables_scrollHeadInner {
    width: calc( 100% - 12px ) !important;
}
table.table{
    /* min-width: 100% !important; */
    width: 100% !important;
}


td.sorting_1 {
    text-align: center;
}
td:nth-child(3) {
  width: 100%;
  text-align: -webkit-center;
  
}
#container-table {
	box-shadow: none !important;
	margin-bottom: 25px;
	padding: none !important;
}
table.table{
    /* min-width: 100% !important; */
    width: 100% !important;
}
table td, table th {
         width: 525px !important;
}
</style>


<style>
body {font-family: Arial, Helvetica, sans-serif;}

#myImg {
  border-radius: 5px;
  cursor: pointer;
  transition: 0.3s;
}

#myImg:hover {opacity: 0.7;}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
}

/* Modal Content (image) */
.modal-content {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
}

/* Caption of Modal Image */
#caption {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
  text-align: center;
  color: #ccc;
  padding: 10px 0;
  height: 150px;
}

/* Add Animation */
.modal-content, #caption {  
  -webkit-animation-name: zoom;
  -webkit-animation-duration: 0.6s;
  animation-name: zoom;
  animation-duration: 0.6s;
}

@-webkit-keyframes zoom {
  from {-webkit-transform:scale(0)} 
  to {-webkit-transform:scale(1)}
}

@keyframes zoom {
  from {transform:scale(0)} 
  to {transform:scale(1)}
}

/* The Close Button */
.close {
  color: #dc3545;
  float: right;
  font-size: 28px;
  font-weight: bold;
}
.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px){
  .modal-content {
    width: 100%;
  }
}
</style>

<!-- coll start -->
<!-- START CHANGE OF NAME DETAIL -->
<form:form name="Edit_Regulation" id="Edit_Regulation" action="Edit_Regulation_Action" method="post" class="form-horizontal" modelAttribute="Edit_RegulationCMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>Regulation</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;"></span>
					</h6>
				</div>
				<div class="card-body card-block">

<!-- coll start -->
<!-- START CHANGE OF NAME DETAIL -->
<%-- 	    <form id="form_change_of_name"> --%>
		<div class="card">
			<div class="panel-group" id="accordion5">
				<div class="panel panel-default" id="a_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion5" href="#" id="a_final" onclick="divN(this)"
								><b>PERSONAL DETAILS</b></a>
						</h4>
					</div>
					<div id="collapse1a" class="panel-collapse collapse">
						  <div class="card-body card-block">
			            <br>
			            
					<div class="row">
						<div class="col-md-12">	              					
	              				 <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>UPLOAD PHOTOGRAPH</label>
						                </div>
						               <div class="col-md-8">
						                <input type="file" accept="image/*" id="photo_path"
										name="photo_path" class="form-control" onclick="return imageView(this.value);"> <input
										type="hidden" id="upload_img_hid" name="upload_img_hid"
										class="form-control"> <span class="focus-border"><i></i></span>
										<img class='d-block img5050 imageZomm' alt="No Image" id="myImg" src="MedicalImagePath1?i_id=${Edit_reg_mstr_Details.id}" />
						                  </div>
						                  <button type="button" onclick="imageView();"><i class="fa fa-eye" title="Print" style='color: #04474a; font-size: x-large;' ></i></button>
										<a href="MedicalImagePath1?i_id=${Edit_reg_mstr_Details.id}" download>
											<i class="fa fa-download"  title="Print" style='color: #04474a; font-size: x-large;' ></i></a>
						                  
						                 
						            </div>
	              				</div>	  
	              				
	              				<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> NRH ENROLMENT NUMBER</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="nrh_en_no" name="nrh_en_no"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control" autocomplete="off">
										<input type="text" id="id" name="id" value="0" class="form-control"
										autocomplete="off" />
								</div>
							</div>
						</div>
	              		 </div>
	              			
	              			
	              			<div class="col-md-12">	  
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>FIRST NAME </label>
						                </div>
						                <div class="col-md-8">
						             <input type="text" id="first_name" name="first_name"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control" autocomplete="off">
						                </div>
						            </div>
	              				</div>	                    						              				            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>GENDER </label>
						                </div>
						                <div class="col-md-8">
						                   			<label for="male" class="form-check-label"
										style="margin: 17px; text-transform: uppercase"> <input
										type="radio" id="male" name="gender"
										class="form-check-input" value="0" required />Male
									</label> <label for="female" class="form-check-label"
										style="margin: 15px; text-transform: uppercase"> <input
										type="radio" id="female" name="gender"
										class="form-check-input" value="1" required />Female
									</label> <label for="other" class="form-check-label"
										style="margin: 10px; text-transform: uppercase"> <input
										type="radio" id="other" name="gender"
										class="form-check-input" value="2" required />Other
									</label>
				                	  </div>
						            </div>
	              				</div>	     
	              			 </div>	
	              			
	              			
	              			
	              			<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>FATHER'S NAME </label>
						                </div>
						                <div class="col-md-8">
						                   	<input type="text" id="father_name" name="father_name"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control" autocomplete="off">
				                	  </div>
						            </div>
	              				</div>	                      						              				            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>AADHAAR NUMBER </label>
						                </div>
						                <div class="col-md-8">
						                   	<input type="text" id="aadhaar_no" name="aadhaar_no"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control" autocomplete="off" onkeypress="return isAadhar(this,event);">
				                	  </div>
						            </div>
	              				</div>	     
	              			 </div>	
	              			
	              			
	              			
	              			<div class="col-md-12">	
	              			     <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>MOBILE NUMBER </label>
						                </div>
						                <div class="col-md-8">
						                   	<input type="text" id="mo_no" name="mo_no" onkeypress="return isNumberOnly(event)"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control" autocomplete="off">
				                	  </div>
						            </div>
	              				</div>	     
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>ALTERNATIVE MOBILE NUMBER </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="alt_mo_no" name="alt_mo_no" onkeypress="return isNumberOnly(event)"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control" autocomplete="off">
				                	  </div>
						            </div>
	              				</div>	        
	              			</div>	
	              			
	              			
	              			<div class="col-md-12">	
	              			     <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>FAX NUMBER </label>
						                </div>
						                <div class="col-md-8">
						                   	<input type="text" id="fax_no" name="fax_no" onkeypress="return isNumberOnly(event)"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control" autocomplete="off">
				                	  </div>
						            </div>
	              				</div>	     
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>EMAIL ID </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="email_id" name="email_id"
										 maxlength="50"
										class="form-control" autocomplete="off">
				                	  </div>
						            </div>
	              				</div>	        
	              			 </div>	
	              			
	              			
	              			<div class="col-md-12">	
	              			     <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>DATE OF BIRTH</label>
						                </div>
<!-- 						                <div class="col-md-8"> -->
<!-- 						                   	<input type="date" id="dob" name="dob" -->
<!-- 										oninput="this.value = this.value.toUpperCase()" maxlength="50" -->
<!-- 										class="form-control" autocomplete="off"> -->
<!-- 				                	  </div> -->
										<div class="col-md-8">
						                  	<input type="text" name="dob" id="dob"
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
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>NATIONALITY </label>
						                </div>
						                <div class="col-md-8">
						     <select name="nationality" id="nationality" style="text-transform: uppercase" class="form-control">
									<option value="o" selected="selected"> -- Select Country -- </option>
									<option value=1>INDIA</option>
									<option value="2">CANADA</option>
									<option value="3">RUSSIA</option>
								</select>
<!-- 								 <select name="nationality_id" id="nationality_id" class="form-control" onchange="fn_pre_domicile_Country();" > -->
<!-- 												<option value="0">--Select--</option> -->
<%-- 												<c:forEach var="item" items="${country_id}" varStatus="num"> --%>
<%-- 												<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 												</c:forEach> --%>
<!-- 											</select>   -->
				                	  </div>
						            </div>
	              				</div>	        
	              			 </div>	
	              			
	              			
							</div>
					     	
						</div>
				 
					</div>
				</div>
			</div>
		</div>
<%-- 	</form> --%>
	<!-- END CHANGE OF PERSONAL DETAIL -->



<!-- START CHANGE OF ADDRESS DETAIL -->
<%-- 	    <form id="form_change_of_name"> --%>
		<div class="card">
			<div class="panel-group" id="accordion5">
				<div class="panel panel-default" id="b_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="b_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion5" href="#" id="b_final" onclick="divN(this)"
								><b>ADDRESS DETAILS</b></a>
						</h4>
					</div>
					<div id="collapse1b" class="panel-collapse collapse">
						  <div class="card-body card-block">
			            <br>
			            
						<div class="row">
						 
	              			
	              			<label class=" form-control-label" style="margin-left:10px;"><h4> Permanent Address</h4></label>
	              			<div class="col-md-12">	
	              			     <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>ADDRESS LINE 1</label>
						                </div>
						                <div class="col-md-8">
						                  <textarea id="per_address" name="per_address"
										class="form-control autocomplete" autocomplete="off">
										</textarea>
				                	  </div>
						            </div>
	              				</div>	     
	              				
 
	              			 </div>	
	              			
	              			
	              			
	              			<div class="col-md-12">	
	              			     <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>STATE</label>
						                </div>
						                <div class="col-md-8">
						                <select name="per_state" id="per_state"
										class="form-control autocomplete" onchange="getDistrict();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${MedStateName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
	              			
				                	  </div>
						            </div>
	              				</div>	     
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>DISTRICT </label>
						                </div>
						                <div class="col-md-8">
						                  <select name="per_district" id="per_district"
										class="form-control autocomplete">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${MedDistrictName}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
				                	  </div>
						            </div>
	              				</div>	        
	              			 </div>	
	              			
	              			<div class="col-md-12">	
	              			     <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>PIN CODE</label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" id="per_pincode" name="per_pincode" onkeypress="return isNumberOnly(event)"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control" autocomplete="off">
	              			
				                	  </div>
						            </div>
	              				</div>	     
	              		 
	              			 </div>	
	              			
	              			
	          
	              		 
	              			
	              			
	             <div class="col-md-12">
			 	<label class=" form-control-label"  style="margin-left:10px;"><h4> Present Address</h4></label>
			 </div>  
	              			
	              	 	<div class="col-md-12" style="font-size: 15px;">	
           		<input type="checkbox" id="check_address" name="check_address" onclick="copy_address()">
               	<label for="text-input" class=" form-control-label" style="color: #dd1a3e; margin-left:10px;">  Same as Permanent Address </label>
            </div>		
	              			
	              			 
	              			
	              			
	              	<div class="col-md-12">	
	              			     <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>ADDRESS LINE 1</label>
						                </div>
						                <div class="col-md-8">
						                  <textarea id="pre_address" name="pre_address"
										class="form-control autocomplete" autocomplete="off">
										</textarea>
				                	  </div>
						            </div>
	              				</div>	     
	              				
 
	              			 </div>	
	              			
	              			
	              			
	              			<div class="col-md-12">	
	              			     <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>STATE</label>
						                </div>
						                <div class="col-md-8">
						                <select name="pre_state" id="pre_state"
										class="form-control autocomplete" onchange="getDistrict();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${MedStateName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
	              			
				                	  </div>
						            </div>
	              				</div>	     
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>DISTRICT </label>
						                </div>
						                <div class="col-md-8">
						                  <select name="pre_district" id="pre_district"
										class="form-control autocomplete">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${MedDistrictName}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
				                	  </div>
						            </div>
	              				</div>	        
	              			 </div>	
	              			
	              			<div class="col-md-12">	
	              			     <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>PIN CODE</label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" id="pre_pincode" name="pre_pincode" onkeypress="return isNumberOnly(event)"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control" autocomplete="off">
	              			
				                	  </div>
						            </div>
	              				</div>	     
	              		 
	              			 </div>		
	              			 
	              			 				<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>NAMES OF THE REGISTER(NATIONAL/STATE)</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="name_reg" name="name_reg"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>WHETHER REGISRATION IS RENEWABLE OR PERMANENT</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="reg_renew_permanent" name="reg_renew_permanent"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
					</div>


	              			 	
	              			 
	              			
	              			
	              			 	
	              			
	              			
	              			 
	              			
	              			
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
<%-- 	</form> --%>
	<!-- END CHANGE OF ADDRESS DETAIL -->
	
	
	
	<!-- 	NAME OF MEDICAL DEGREE OR DIPLOMA OBTAINED -->

		<div class="card">
			<div class="panel-group" id="accordion5">
				<div class="panel panel-default" id="c_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="c_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion5" href="#" id="c_final" onclick="divN(this)"
								><b>NAME OF MEDICAL DEGREE OR DIPLOMA OBTAINED</b></a>
						</h4>
					</div>
					<div id="collapse1c" class="panel-collapse collapse">
						  <div class="card-body card-block">
			            <br>
			            
					<div class="row">
								<div class="col-md-12">
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>NAME OF MEDICAL DEGREE OR DIPLOMA</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="degree" name="degree"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>NAME OF UNIVERSITY</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="university" name="university"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					
					
								<div class="col-md-12">
						
						
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>MONTH AND YEAR OF PASSING QUALIFICATION</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="month_year" name="month_year"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					
	              			
	              			
	  
	              			
	              			
							</div>
					     	
						</div>
				 
					</div>
				</div>
			</div>
		</div>

<!-- END NAME OF MEDICAL DEGREE OR DIPLOMA OBTAINED -->


<!-- 	              			 REGISTRATION PARTICULARS START	 -->
	
	
		<div class="card">
			<div class="panel-group" id="accordion5">
				<div class="panel panel-default" id="d_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="d_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion5" href="#" id="d_final" onclick="divN(this)"
								><b>REGISTRATION PARTICULARS</b></a>
						</h4>
					</div>
					<div id="collapse1d" class="panel-collapse collapse">
						  <div class="card-body card-block">
			            <br>
			            
					<div class="row">
		<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>REGISTRATION NUMBER</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="reg_no" name="reg_no" onkeypress="return isNumberOnly(event)"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"><strong -->
<!-- 										style="color: red;">* </strong>DATE OF REGISTRATION</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<input type="date" id="date_of_reg" name="date_of_reg" -->
<!-- 										oninput="this.value = this.value.toUpperCase()" maxlength="50" -->
<!-- 										class="form-control autocomplete" autocomplete="off"> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
				<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>DATE OF REGISTRATION</label>
								</div>
								<div class="col-md-8">
<!-- 									<input type="text" id="date_of_reg" name="date_of_reg" -->
<!-- 										oninput="this.value = this.value.toUpperCase()" maxlength="50" -->
<!-- 										class="form-control autocomplete" autocomplete="off"> -->
												<input type="text" name="date_of_reg" id="date_of_reg"
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
					 		
	              			
	              			<div class="col-md-12">	
	              			     <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>NAME'S OF THE REGISTER(NATIONAL/STATE) </label>
						                </div>
						                <div class="col-md-8">
						                   	<input type="text" id="name_reg" name="name_reg"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control" autocomplete="off">
				                	  </div>
						            </div>
	              				</div>	     
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>REGISTRATION IS RENEWABLE OR PERMANENT </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="reg_renew_permanent" name="reg_renew_permanent"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control" autocomplete="off">
				                	  </div>
						            </div>
	              				</div>	        
	              			</div>	
	              			
	              			
	              			
	  
	              			
	              			
							</div>
					     	
						</div>
				 
					</div>
				</div>
			</div>
		</div>
	
		              			 
<!-- 	              			 REGISTRATION PARTICULARS END	 -->



<!-- 	              			 HOSPITAL START	 -->

		<div class="card">
			<div class="panel-group" id="accordion5">
				<div class="panel panel-default" id="e_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="e_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion5" href="#" id="e_final" onclick="divN(this)"
								><b>HOSPITAL</b></a>
						</h4>
					</div>
					<div id="collapse1e" class="panel-collapse collapse">
						  <div class="card-body card-block">
			            <br>
			            
					<div class="row">
		<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> NAME OF HOSPITAL TEACHING</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="name_of_hospital_teaching" name="name_of_hospital_teaching"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>NAME OF PATIENT</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="name_of_patient" name="name_of_patient"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					
					
	              			
	              			
	       	
	              			
	              			
	              			
	              			<div class="col-md-12">	
	              			     <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>CRH_NO</label>
						                </div>
						                <div class="col-md-8">
						                   	<input type="text" id="crh_no" name="crh_no" 
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control" autocomplete="off">
				                	  </div>
						            </div>
	              				</div>	     
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>CCH_NO</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="cch_no" name="cch_no"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control" autocomplete="off">
				                	  </div>
						            </div>
	              				</div>	        
	              			</div>	
	              			
	              			
	              				<div class="col-md-12">	
	              			     <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>NCH_NO</label>
						                </div>
						                <div class="col-md-8">
						                   	<input type="text" id="nch_no" name="nch_no"
										oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control" autocomplete="off">
				                	  </div>
						            </div>
	              				</div>	     
	              			      
	              			</div>	
	              			
	              			
	              			
	  
	              			
	              			
							</div>
					     	
						</div>
				 
					</div>
				</div>
			</div>
		</div>

<!-- 	              			 HOSPITAL END	 -->

<div class="card-footer" align="center">
	        		     <a href="edu_search_reg_url" id=""	class="btn-cancel">BACK</a>
	        		     <button type="reset" class="btn-clear" value="Reset" onclick="clearall();"> RESET</button>
	              		 <input type="submit" class="btn-update" value="UPDATE" onclick="return Validation();"> 
	              	</div>
</form:form>

<div id="myModal" class="modal">
  <span class="close">&times;</span>

  <img class="modal-content" id="img01">
  <div id="caption"></div>
</div>

<!-- start -->

<script type="text/javascript">
function Validation() {
	 
	return true;
}

function ParseDateColumn(data) {
	
	  var date="";
	  if(data!=null && data!=""){			 
		 var d = new Date(data);			 
		 var day=('0' + d.getDate()).slice(-2);
		 var month=('0' + (d.getMonth() + 1)).slice(-2);
		 var year=""+d.getFullYear();		    				 
		//date=year+"-"+month+"-"+day;
		date= day+"/"+month+"/"+year;
		}     
	  return date;
		    
	}

$(document).ready(function() {
 
	
	 $('input#upload_img_hid').val('${REG_NCH_FORM_A_P.photo_path}'); 
	$('#id').val('${Edit_reg_mstr_Details.id}');
	$('input#nrh_en_no').val('${Edit_reg_mstr_Details.nrh_en_no}');
	//$('input#photo_path').val('${Edit_reg_mstr_Details.photo_path}');
	 $('input#first_name').val('${Edit_reg_mstr_Details.first_name}'); 
	 //$('input#gender').val('${Edit_reg_mstr_Details.gender}'); 
	 if ('${Edit_reg_mstr_Details.gender}' =="0")
		 {
		 $('#male').prop("checked",true);
		 }
	  if ('${Edit_reg_mstr_Details.gender}' =="1")
	 {
		  $('#female').prop("checked",true);
	 }
	 if  ('${Edit_reg_mstr_Details.gender}' =="2")
	 {
		 $('#other').prop("checked",true);
	 }
	 $('input#aadhaar_no').val('${Edit_reg_mstr_Details.aadhaar_no}');
	 $('input#mo_no').val('${Edit_reg_mstr_Details.mo_no}'); 
	 $('input#alt_mo_no').val('${Edit_reg_mstr_Details.alt_mo_no}'); 
	 $('input#fax_no').val('${Edit_reg_mstr_Details.fax_no}'); 
	 $('input#email_id').val('${Edit_reg_mstr_Details.email_id}'); 
	 
  
	// var dob = '${Edit_reg_mstr_Details.dob}'.substring(0,10);
	 // $('#dob').val('${Edit_reg_mstr_Details.dob}');
	 var d=  $("#dob").val(ParseDateColumn('${Edit_reg_mstr_Details.dob}'));
	 
	 $('input#father_name').val('${Edit_reg_mstr_Details.father_name}'); 
	 $('select#nationality').val('${Edit_reg_mstr_Details.nationality}'); 
	 $('textarea#per_address').val('${Edit_reg_mstr_Details.per_address}'); 
	 $('select#per_state').val('${Edit_reg_mstr_Details.per_state}'); 
	 $('select#per_district').val('${Edit_reg_mstr_Details.per_district}');
	 $('input#per_pincode').val('${Edit_reg_mstr_Details.per_pincode}');
	 $('textarea#pre_address').val('${Edit_reg_mstr_Details.pre_address}');
	 $('select#pre_state').val('${Edit_reg_mstr_Details.pre_state}');
	 $('select#pre_district').val('${Edit_reg_mstr_Details.pre_district}');
	 $('input#pre_pincode').val('${Edit_reg_mstr_Details.pre_pincode}');
	 $('input#name_reg').val('${Edit_reg_mstr_Details.name_reg}');
	 $('input#reg_renew_permanent').val('${Edit_reg_mstr_Details.reg_renew_permanent}');
	 $('input#degree').val('${Edit_reg_mstr_Details.degree}');
	 $('input#university').val('${Edit_reg_mstr_Details.university}');
	 $('input#month_year').val('${Edit_reg_mstr_Details.month_year}');
	 $('input#reg_no').val('${Edit_reg_mstr_Details.reg_no}');
	  
	 
	 $("#date_of_reg").val(ParseDateColumn('${Edit_reg_mstr_Details.date_of_reg}'));
	 
	 
	// $('input#date_of_reg').val('${Edit_reg_mstr_Details.date_of_reg}');
	 
	 
	 $('input#name_of_hospital_teaching').val('${Edit_reg_mstr_Details.name_of_hospital_teaching}');
	 $('input#name_of_patient').val('${Edit_reg_mstr_Details.name_of_patient}');
	 $('input#crh_no').val('${Edit_reg_mstr_Details.crh_no}');
	 $('input#cch_no').val('${Edit_reg_mstr_Details.cch_no}');
	 $('input#nch_no').val('${Edit_reg_mstr_Details.nch_no}');
//$("#policy_draft_hid").val('${hidden_draft}');
	//$('select#status').val('${Edit_ele_course_mstr_Details.status}');
});

</script>
<SCRIPT>

function isAadhar(e,evt) {
	if(e.value==0 || e.value=="null" || e.value == null){
		e.value="";
		}

	var bool=isNumber(evt);	
	if(bool){
	  var value = e.value;
	  value = value.replace(/\D/g, "").split(/(?:([\d]{4}))/g).filter(s => s.length >0 ).join("-");
	  e.value=value;
	  return true;
	  }
	  else{
	  return false;}
	}
function isNumber(evt) {
	
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	return false;
	}
	return true;
	}
$(document).ready(function() {
		
		$.ajaxSetup({
			async : false
		});
		

		$.ajaxSetup({
			async : false
		});
		 
		datepicketDate('date_of_reg');
		datepicketDate('dob');
  

		
		
		

	});
	</SCRIPT>
	<script>
	

	function divN(obj){
		var id = obj.id;
	 
		 var sib_id = $("#"+id).parent().parent().next().attr('id');
		var hasC=$("#"+sib_id).hasClass("show");
			$(".panel-collapse").removeClass("show");
			 $('.droparrow').each(function(i, obj) {
				 $("#"+obj.id).attr("class", "droparrow collapsed");
				 });
		
			
			if (hasC) {	
			$("#"+id).addClass( " collapsed");		 
			}  else {				
				$("#"+sib_id).addClass("show");	
				$("#"+id).removeClass("collapsed");
		    }
			
			  
	}
	
	//copy address
	function copy_address(){
    if($("#check_address").prop('checked') == true){        
    	 
            $("#pre_address").val($("#per_address").val());
            $("#pre_state").val($("#per_state").val());
            $("#pre_district").val($("#per_district").val());
            $("#pre_district").val($("#per_district").val());
            $("#pre_pincode").val($("#per_pincode").val());
           // fn_pers_addr_Country();
           //  fn_pers_addr_state();
        
    }
    else{
    	alert("else")
            $("#pre_address").val("");
    	  $("#pre_state").val("");
          $("#pre_district").val("");
          $("#pre_district").val("");
          $("#pre_pincode").val("");
    }
}
	
	
	function imageView(obj){
alert(obj);
debugger;
		var modal = document.getElementById("myModal");
		var span = document.getElementsByClassName("close")[0];

		//debugger;
		// Get the image and insert it inside the modal - use its "alt" text as a caption
		var img = document.getElementById("myImg");

// 		var course_name = $("#course_name").val();
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
	
	function saveData(){
		if(isValidate()==true){
		var form = $('#Edit_Regulation')[0];

			var data = new FormData(form);

			$.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : "Edit_Regulation_Action?"+key+"="+value,
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 600000,
				success : function(data) {
					
					//	alert(data);
							
						if( data=="Update Successfully"){
						
							 window.location = 'Edit_edu_reg_mstrUrl?msg='+data;	
						}
					}
				//}
			});
		}
	}

	
	function isNumberOnly(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	    return true;
	}
	
//-------


	</script>




