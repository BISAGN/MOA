<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />

<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="js/InfiniteScroll_old/css/site.css">
<link rel="stylesheet" href="js/autoComplate/autoComplate.css">


<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script type="text/javascript" src="js/common/commonmethod.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<link href="js/jquery/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/jquery/jquery-ui.js" type="text/javascript"></script>
<script src="js/sweetalert/sweetalert.min.js"></script>

<link href="js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="js/dropDown/select2.min.js" type="text/javascript"></script>


    
    
<style>
td.sorting_1 {
    text-align: center !important;
}

</style>

<style>
.s010 {
	background: #f8f9fa;
}


.s010 form .inner-form .input-field input {
    background: linear-gradient(to right, #175658 0%, #175658 28%, #2e9970 91%, #2e9970 100%);
}

.s010 form .inner-form .input-field .btn-search {
	background: linear-gradient(to right, #175658 0%, #175658 28%, #2e9970 91%, #2e9970 100%);
}

.s010 form {
    max-width: 90%;
}

.card{
	border: 0px;
}

.section-heading {
	padding-top: 10px;
}

.inner-form .form-control {
    color: #175658;
    border: 2px solid #175658;
        margin-bottom: -10px;
}
.card label {
	color: #175658;
}


span#select2-policy_category-container {
    font-weight: 600;
    line-height: 2;
    border: 2px solid #175658 !important;
    border-radius: 0.25rem;
    color: #124c30 !important;
    margin-bottom: 5px;
}
span#select2-policy_sub_category-container {
    font-weight: 600;
    line-height: 2;
    border: 2px solid #175658 !important;
    border-radius: 0.25rem;
    color: #124c30 !important;
      margin-bottom: 5px;
}


.placeholder_c input::placeholder {
  color: white !important;

}

#btn-gs::placeholder {
  color: white !important;
  opacity: 1; /* Firefox */
}

/* #btn-gs:-ms-input-placeholder { /* Internet Explorer 10-11 */ */
/*  color: red !important; */
/* } */

/* #btn-gs::-ms-input-placeholder { /* Microsoft Edge */ */
/*  color: red !important; */
/* } */
</style>
<style>

.progress {
	background-color: #46d6f7;
}

.bs-wizard {
	margin-top: 40px;
}

/*Form Wizard*/
.bs-wizard {
	border-bottom: solid 1px #e0e0e0;
	padding: 0 0 10px 0;
	margin-left: 130px;
	justify-content: center;
	padding: 3rem;
}

.bs-wizard>.bs-wizard-step {
	padding: 0;
	position: relative;
	width: 10%;
}

.bs-wizard>.bs-wizard-step+.bs-wizard-step {
	
}

.bs-wizard>.bs-wizard-step .bs-wizard-stepnum {
	color: #23adcc;
	font-size: 12px;
	margin-bottom: 5px;
	transform: rotate(-30deg);
	transform-origin: 3% 10%;
	font-weight: 700;
}

.bs-wizard>.bs-wizard-step .bs-wizard-info {
	color: #999;
	font-size: 14px;
}

.bs-wizard>.bs-wizard-step>.bs-wizard-dot {
	position: absolute;
	width: 30px;
	height: 30px;
	display: block;
	background: #3fbfdc;
	top: 45px;
	margin-top: -19px;
	margin-left: -15px;
	border-radius: 50%;
}

.bs-wizard>.bs-wizard-step>.bs-wizard-dot:after {
	content: ' ';
	width: 14px;
	height: 14px;
	background: #c1ebf5;
	border-radius: 50px;
	position: absolute;
	top: 8px;
	left: 8px;
}

.bs-wizard>.bs-wizard-step>.progress {
	position: relative;
	border-radius: 0px;
	height: 5px;
	box-shadow: none;
	margin: 20px 0;
}

.bs-wizard>.bs-wizard-step>.progress>.progress-bar {
	width: 0px;
	box-shadow: none;
	background: #fbbd19;
}

.bs-wizard>.bs-wizard-step.complete>.progress>.progress-bar {
	width: 100%;
}

.bs-wizard>.bs-wizard-step.active>.progress>.progress-bar {
	width: 0%;
}

.bs-wizard>.bs-wizard-step:first-child.active>.progress>.progress-bar {
	width: 0%;
}

.bs-wizard>.bs-wizard-step:last-child.active>.progress>.progress-bar {
	width: 100%;
}

.bs-wizard>.bs-wizard-step.disabled>.bs-wizard-dot {
	background-color: #f5f5f5;
}

.bs-wizard>.bs-wizard-step.disabled>.bs-wizard-dot:after {
	opacity: 0;
}

.bs-wizard>.bs-wizard-step:first-child>.progress {
	width: 100%;
}

.bs-wizard>.bs-wizard-step:last-child>.progress {
	width: 0%;
}

.bs-wizard>.bs-wizard-step.disabled a.bs-wizard-dot {
	pointer-events: none;
}

a.step-1 {
	background: #fbbd19 !important;
}

a.step-1:after {
	background: #fbbd19 !important;
}

a.step-2 {
	background: #007bff !important;
}

a.step-2:after {
	background: #007bff !important;
}

a.step-3 {
	background: #da0013 !important;
}

a.step-3:after {
	background: #da0013 !important;
}

a.step-4 {
	background: #d68a31 !important;
}

a.step-4:after {
	background: #d68a31 !important;
}

a.step-5 {
	
	
	
	background: green !important;
}

a.step-5:after {
/* 	background: #d68a31 !important; */
	
	
	background: green !important;
}

a.step-6 {
	background: #a419fb !important;
}

a.step-6:after {
	background: #a419fb !important;
}

.bs-wizard>.bs-wizard-step.active>.bs-wizard-dot {
	width: 38px;
	height: 38px;
	margin-top: -18px;
	margin-left: -18px;
	top: 39px;
}

.fa-check:before {
	position: absolute;
	top: 11px;
	left: 10px;
	color: white;
}

.bs-wizard>.bs-wizard-step.active>.bs-wizard-dot:after {
	position: initial;
}

.round {
	width: 38px;
	height: 38px;
	border-radius: 52px;
	position: absolute;
	top: 10px;
	left: 8px;
}

p.bs-text {
	font-weight: 500;
	position: absolute;
	top: 16px;
	left: 55px;
}

@media screen and (max-width: 1400px) {
	.bs-wizard>.bs-wizard-step .bs-wizard-stepnum {
		font-size: 10px;
	}
	.bs-wizard>.bs-wizard-step>.bs-wizard-dot {
		margin-top: -21px;
	}
}

@media screen and (max-width: 1280px) {
	.bs-wizard>.bs-wizard-step .bs-wizard-stepnum {
		font-size: 8px;
	}
	.bs-wizard>.bs-wizard-step>.bs-wizard-dot {
		margin-top: -24px;
	}
}

.bs-wizard>.bs-wizard-step.active.step-2>.progress {
	background-color: #007bff;
}

.bs-wizard>.bs-wizard-step.active.step-3>.progress {
	background-color: #da0013;
}

.bs-wizard>.bs-wizard-step.active.step-4>.progress {
background-color: #d68a31 ;
	
}
 .bs-wizard>.bs-wizard-step.active.step-5>.progress { 
 background-color: green;
 	 
 } 
/* .bs-wizard>.bs-wizard-step.active.step-6>.progress { */
/* 	background-color: #a419fb; */
/* } */
/*END Form Wizard*/
</style>


<div class="datatablediv" align="center">
		<div class="card">
	<div class="row">
		<div class="col-12">

			<div class="section-heading">
				<h2><b>TRACK POLICY STATUS</b></h2>
				<div class=""></div>
			</div>

		</div>
	</div>

	<div class="s010">
		<form>
			<div class="inner-form">
				<div class="basic-search">
					<div class="input-field">
					<input id="btn-gs" class ="placeholder_c" placeholder="Type Keywords"/>
						<div class="icon-wrap">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
								viewBox="0 0 24 24">
                  <path
									d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"></path>
                </svg>
						</div>
					</div>
				</div>
				<div class="advance-search">
				
<div class="row">
						<div class="col-md-3">
							<label for="text-input" class=" form-control-label">POLICY CATEGORY</label>
						</div>
						<div class="col-md-3">
							<label for="text-input" class=" form-control-label">POLICY SUB CATEGORY</label>
						</div>
						<div class="col-md-3">
							<label for="text-input" class=" form-control-label">POLICY TYPE</label>
						</div>						
						<div class="col-md-3">
							<label for="text-input" class=" form-control-label">POLICY NUMBER</label>
						</div>
						
					</div>
					
				<div class="row">
						
						
						<div class="col-md-3">
<!-- 							<input class="form-control" name="category" -->
<!-- 								id="category" type="text" onkeypress="AutoCompleteCategoryList(this);" autocomplete="off" placeholder="CATEGORY..."> -->
						<select name="policy_category" id="policy_category" onchange="getSubpolicy();" class="form-control-sm form-control effect-9 select2 narrow wrap" >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${policycat}" varStatus="num">
													<option value="${item.id}" name="${item.id}">${item.policycategory}</option>
												</c:forEach>
									</select>
									
									
<!-- 								<input class="form-control" name="category_hid" -->
<!-- 								id="category_hid" type="hidden" onkeypress="" autocomplete="off" "> -->
						</div>
						
						<div class="col-md-3">
<!-- 							<input class="form-control" name="sub_category" -->
<!-- 								id="sub_category" type="text" autocomplete="off" placeholder="SUB CATEGORY..."> -->
						<select name="policy_sub_category" id="policy_sub_category"  class="select2 narrow wrap form-control-sm form-control effect-9" >
												<option value="0">--Select--</option>
											<c:forEach var="item" items="${subpolicycat}" varStatus="num">
													<option value="${item.id}" name="${item.id}">${item.subcategory}</option>
												</c:forEach>
								</select>
								
						</div>
						
						
						<div class="col-md-3">
							<select name="policy_type" id="policy_type" class="form-control">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${policytypeList}" varStatus="num">
										<option value="${item[0]}">${item[1]}</option>
									</c:forEach>
								</select>
						</div>
						
						
						<div class="col-md-3">
							<input class="form-control" name="policy_number" id="policy_number" type="text"
								placeholder="POLICY NUMBER..."  autocomplete="off" onkeypress="AutoCompletePolicyNo(this);">
						</div>
					</div>
					
					

					
					<div class="row third">
						<div class="input-field">
							<!--                 <div class="result-count"> -->
							<!--                   <span>108 </span>results -->
							<!--                  </div> -->
							<div class="group-btn">
							<a href="Track_Policy_StatusUrl"><button type="button" class="btn-clear" id="delete">RESET</button></a>
							</div>
						
							<div class="group-btn">
								<i class="btn-search" id="btn-reload"><i
									class="fa fa-search" style="line-height: 39px;">Search</i></i>
								<!--                   <button class="btn-search" id="btn-reload">SEARCH</button> -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>


<br>

<div class="row bs-wizard px-4" style="border-bottom: 0; display: none" id="track_div">

		<div class="col-xs-3 bs-wizard-step">
			<div class="text-left bs-wizard-stepnum">MOA ENTITY</div>
			<div class="progress">
				<div class="progress-bar "></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">EXECUTIVE COMMITTEE</div>
			<div class="progress">
				<div class="progress-bar "></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">INDUSTRY EXPERTS</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">MOA</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>
		
		<div class="col-xs-3 bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">PUBLISHED</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>

	</div>

	<div class="row bs-wizard px-4" style="border-bottom: 0; display: none" id="track_div0">

		<div class="col-xs-3 bs-wizard-step step-1 active">
			<!-- active -->
			<div class="text-left bs-wizard-stepnum"
				style="color: #fbbd19 !important;">MOA ENTITY</div>
			<div class="progress">
				<div class="progress-bar step-1"></div>
			</div>
			<a href="#" class="bs-wizard-dot fa fa-check fa-lg step-1"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">EXECUTIVE COMMITTEE</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">INDUSTRY EXPERTS</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">MOA</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>
		
		<div class="col-xs-3 bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">PUBLISHED</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>


	</div>


	<div class="row bs-wizard px-4" style="border-bottom: 0; display: none" id="track_div1">

		<div class="col-xs-3 bs-wizard-step step-2 active">
			<div class="text-left bs-wizard-stepnum"
				style="color: #007bff !important;">MOA ENTITY</div>
			<div class="progress">
				<div class="progress-bar step-2"></div>
			</div>
			<a href="#" class="bs-wizard-dot  fa fa-check fa-lg step-2"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step step-2 active">
			<!-- active -->
			<div class="text-left bs-wizard-stepnum"
				style="color: #007bff !important;">EXECUTIVE COMMITTEE</div>
			<div class="progress" style="background-color: #46d6f7;">
				<div class="progress-bar "></div>
			</div>
			<a href="#" class="bs-wizard-dot fa fa-check fa-lg step-2"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">INDUSTRY EXPERTS</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">MOA</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>
		
			<div class="col-xs-3 bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">PUBLISHED</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>


	</div>


	<div class="row bs-wizard px-4" style="border-bottom: 0; display: none" id="track_div2">

		<div class="col-xs-3 bs-wizard-step step-3 active">
			<div class="text-left bs-wizard-stepnum"
				style="color: #da0013 !important;">MOA ENTITY</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot  fa fa-check fa-lg step-3"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step step-3 active">
			<div class="text-left bs-wizard-stepnum"
				style="color: #da0013 !important;">EXECUTIVE COMMITTEE</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot fa fa-check fa-lg step-3"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step step-3 active">
			<!-- active -->
			<div class="text-left bs-wizard-stepnum"
				style="color: #da0013 !important;">INDUSTRY EXPERTS</div>
			<div class="progress" style="background-color: #46d6f7;">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot fa  fa-check fa-lg step-3"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">MOA</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>
		
		<div class="col-xs-3 bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">PUBLISHED</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>

	</div>

	<div class="row bs-wizard px-4" style="border-bottom: 0; display: none" id="track_div3">

		<div class="col-xs-3 bs-wizard-step step-4 active">
			<div class="text-left bs-wizard-stepnum"
				style="color: #d68a31 !important;">MOA ENTITY</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot  fa fa-check fa-lg step-4"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step step-4 active">
			<div class="text-left bs-wizard-stepnum"
				style="color: #d68a31 !important;">EXECUTIVE COMMITTEE</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot  fa fa-check fa-lg step-4"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step step-4 active">
			<div class="text-left bs-wizard-stepnum"
				style="color: #d68a31 !important;">INDUSTRY EXPERTS</div>
			<div class="progress ">
				<div class="progress-bar "></div>
			</div>
			<a href="#" class="bs-wizard-dot  fa fa-check fa-lg step-4"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step step-4 active">
			<!-- active -->
			<div class="text-left bs-wizard-stepnum"
				style="color: #d68a31 !important;">MOA</div>
			<div class="progress" style="background-color: #46d6f7;">
				<div class="progress-bar "></div>
			</div>
			<a href="#" class=" bs-wizard-dot fa  fa-check fa-lg step-4"></a>
		</div>
		
		<div class="col-xs-3 bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">PUBLISHED</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>
		
	</div>
	
	<div class="row bs-wizard px-4" style="border-bottom: 0; display: none" id="track_div4">

		<div class="col-xs-3 bs-wizard-step step-5 active">
			<div class="text-left bs-wizard-stepnum"
				style="color: green !important;">MOA ENTITY</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot  fa fa-check fa-lg step-5"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step step-5 active">
			<div class="text-left bs-wizard-stepnum"
				style="color: green !important;">EXECUTIVE COMMITTEE</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot  fa fa-check fa-lg step-5"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step step-5 active">
			<div class="text-left bs-wizard-stepnum"
				style="color: green !important;">INDUSTRY EXPERTS</div>
			<div class="progress ">
				<div class="progress-bar "></div>
			</div>
			<a href="#" class="bs-wizard-dot  fa fa-check fa-lg step-5"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step step-5 active">
			<!-- active -->
			<div class="text-left bs-wizard-stepnum"
				style="color: green !important;">MOA</div>
			<div class="progress" >
				<div class="progress-bar "></div>
			</div>
			<a href="#" class=" bs-wizard-dot fa  fa-check fa-lg step-5"></a>
		</div>

		<div class="col-xs-3 bs-wizard-step step-5 active">
			<!-- active -->
			<div class="text-left bs-wizard-stepnum"
				style="color: green !important;">PUBLISHED</div>
			<div class="progress" style="background-color: #46d6f7;">
				<div class="progress-bar "></div>
			</div>
			<a href="#" class=" bs-wizard-dot fa  fa-check fa-lg step-5"></a>
		</div>
	</div>

	<br>
</div>

	
	<table id="search_policy_table"
		class="display table no-margin table-striped  table-hover  table-bordered">
		<thead>
			<tr>
				<th align="center">SER NO</th>
				<th id="8">POLICY CATEGORY</th>
				<th id="9">POLICY SUB CATEGORY</th>
				<th id="10">POLICY TYPE</th>
				<th id="1">POLICY</th>
				<th id="2">INITIAL DATE</th>
				<th id="3">POLICY NUMBER</th>
				<th id="4">DOCUMENT VERSION</th>
				<th id="5">LEVEL</th>
				<th id="6">POLICY STATUS</th>
				<th id="7">DOWNLOAD</th>
			</tr>
		</thead>
	</table>

</div>

<c:url value="getDownloadPdfUrlforeduDoc_policy_Track" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="getDownloadPdfForm" name="getDownloadPdfForm" modelAttribute="doc_id1">
	<input type="hidden" name="pageUrl" id="pageUrl" value="redirect:Track_Policy_StatusUrl"/>       		
	<input type="hidden" name="doc_id1" id="doc_id1" value=""/>		
</form:form>

<script>
	var page = 0;
	var length_menu = 10;
	$(document).ready(function() {

		var msg = '${msg}';

		if(msg!=""){
				swal({
					  title: "success!",
					  text: msg,
					  icon: "success",
					})
				.then(function() {
					window.history.pushState('', 'Policy Search', '/admin/Track_Policy_StatusUrl');
					});
		}


		var role = '${role}';
		if (role == "level 1") {
			$("#newPolicyDraft").show();
		} else {
			$("#newPolicyDraft").hide();
		}
		mockjax1('search_policy_table');
		table = dataTable('search_policy_table');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		
		$('#btn-gs').change(function(){
			table.ajax.reload();
		});

		$("div#track_div").hide();
					$("div#track_div0").hide();
					$("div#track_div1").hide();
					$("div#track_div2").hide();
					$("div#track_div3").hide();
					$("div#track_div4").hide();	
	});
</script>
<script>


	function data() {

		var table = $('#search_policy_table').DataTable();
		var info = table.page.info();
		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];

		jsondata = [];

	
		var policy_number = $("#policy_number").val();
		var category = $("#policy_category").val();
		var sub_category = $("#policy_sub_category").val();
		var policy_type = $("#policy_type").val();

		
		var globSearch = $("#btn-gs").val();

		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";


		$.post("getTackPolicyStatusdata?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				policy_number : policy_number,
				category : category,
				sub_category : sub_category,
				policy_type : policy_type,
				globSearch:globSearch
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					jsondata.push([ j[i][0], j[i][1], j[i][2], j[i][3], j[i][4] , j[i][5],j[i][6],j[i][7],j[i][8],j[i][9],j[i][10]  ]);
				}
			});
			$.post("getTackPolicyStatusCount?" + key + "=" + value, {
				Search : Search,
				policy_number : policy_number,
				category : category,
				sub_category : sub_category,
				policy_type : policy_type,
				globSearch:globSearch
			}, function(j) {
				FilteredRecords = j;
			});



			$.post('Get_policy_number_Search_Status?' + key + "=" + value,{ policy_number : policy_number }).done(function(j) {
										var sts1="";
										var sts2="";
										var sts3="";
										var sts4="";
										
						 		 var sizeofj = j.length;
						 		
								    if(sizeofj == 0 ){
							 			$("div#track_div").hide();
							 			$("div#track_div0").hide();
							 			$("div#track_div1").hide();
							 			$("div#track_div2").hide();
							 			$("div#track_div3").hide();
							 			$("div#track_div4").hide();

							 			}
										
								 		if (sizeofj > 0){
								 			console.log(j);
								 			sts1 = j[0];
								 			sts2 = j[1];
								 			sts3 = j[2];
								 			sts4 = j[3];


									    if(sts1 == 0){
									    	$("div#track_div").hide();
								 			$("div#track_div0").show();
								 			$("div#track_div1").hide();
								 			$("div#track_div2").hide();
								 			$("div#track_div3").hide();
								 			$("div#track_div4").hide();

								 		}
									    if(sts2 == 0){
									    	$("div#track_div").hide();
								 			$("div#track_div0").hide();
								 			$("div#track_div1").show();
								 			$("div#track_div2").hide();
								 			$("div#track_div3").hide();
								 			$("div#track_div4").hide();

								 		}
									    if(sts3 == 0){
									    	$("div#track_div").hide();
								 			$("div#track_div0").hide();
								 			$("div#track_div1").hide();
								 			$("div#track_div2").show();
								 			$("div#track_div3").hide();
								 			$("div#track_div4").hide();

								 		}
									    if(sts4 == 0){
									    	$("div#track_div").hide();
								 			$("div#track_div0").hide();
								 			$("div#track_div1").hide();
								 			$("div#track_div2").hide();
								 			$("div#track_div3").show();
								 			$("div#track_div4").hide();

								 			}  
								 		}
								 		
								 		 if(sts4 == 1){
								 			 
								 			$("div#track_div").hide();
								 			$("div#track_div0").hide();
								 			$("div#track_div1").hide();
								 			$("div#track_div2").hide();
								 			$("div#track_div3").hide();
								 			$("div#track_div4").show();

								 			
								 		 }
								 		
								    });

				}

				$("#policy_number").change(function(){
						var policy_number =	$(this).val()
						  if(policy_number == ""){
								$("div#track_div").hide();
								$("div#track_div0").hide();
								$("div#track_div1").hide();
								$("div#track_div2").hide();
								$("div#track_div3").hide();	
								$("div#track_div4").hide();	
					 		}
						});
						
						
			
</script>

<script>
function getDownloadPdfTrackPolicy(id){   
	
	$("#doc_id1").val(id); 
	document.getElementById("getDownloadPdfForm").submit();
} 
</script>

<script>

function AutoCompleteCategoryList(ele){
	
	var code = ele.value;
	var category =$("#"+ele.id);
	category.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "getCategoryList?" + key + "=" + value,
				data : {
					a : code
				},
				success : function(data) {
					var catval = [];
				//	var catval_i = [];
					var length = data.length - 1;
					for (var i = 0; i < data.length; i++) {
						catval.push(data[i][0]);
						//catval_i.push(data[i][1]);
					}
					
					response(catval);
					
// 					var catid = response(catval_i);
				//	$("#category_hid").val(catval_i);
				}
			});
		},
		minLength : 1,
		autoFill : true,
		change : function(event, ui) {
			
			if (ui.item) {
			
				return true;
			} else {
				alert("Please Enter Policy Category ");			
				category.val("");
				category.focus();
				return false;
			}
		},
		select : function(event, ui) {
			$(this).val(ui.item.value);
			alert(ui.item.value);
		}
	});
	
}

</script>
<script>

function AutoCompletePolicyNo(ele){
	
	var code = ele.value;
	var susNoAuto =$("#"+ele.id);
	var category= $("#policy_category").val();
	var sub_category= $("#policy_sub_category").val();
	var policy_type = $("#policy_type").val();
		
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "getpolicynoAuto?" + key + "=" + value,
				data : {
					a : code,
					policy_category : category,
					policy_sub_category : sub_category,
					policy_type : policy_type
				},
				success : function(data) {
					var susval = [];
					var length = data.length - 1;
					for (var i = 0; i < data.length; i++) {
						susval.push(data[i]);
					}
					response(susval);
				}
			});
		},
		minLength : 1,
		autoFill : true,
		change : function(event, ui) {
			if (ui.item) {
				return true;
			} else {
				alert("Please Enter Policy Number ");			
				susNoAuto.val("");
				susNoAuto.focus();
				table.ajax.reload();
				return false;
			}
		},
		select : function(event, ui) {

		}
	});
	
}



function getSubpolicydata(catval) {
	var selval = $("#category").val();
	$.post( "getSubpolicyUrl?" + key + "=" + value,
					{
						catval : catval
					},
					function(j) {

						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i].id + '" name="'+j[i].id+'" >'
									+ j[i].subcategory + '</option>';
						}
						$("#sub_category").html(options);
					});
}
		
		
function getSubpolicy() {
	var selval = $("#policy_category").val();
	$("#policy_number").val("");

	
	$
			.post(
					"getSubpolicyUrl?" + key + "=" + value,
					{
						selval : selval
					},
					function(j) {

						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i].id + '" name="'+j[i].id+'" >'
									+ j[i].subcategory + '</option>';
						}
						$("select#policy_sub_category").html(options);
					});
}


</script>



