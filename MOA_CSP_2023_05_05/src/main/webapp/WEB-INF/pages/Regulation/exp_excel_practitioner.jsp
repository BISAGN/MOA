<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/datePicketValidation.js"></script>


<!-- <script src="js/Calender/datePicketValidation.js"></script> -->
<!-- <link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css"> -->
<!-- <script src="js/Calender/jquery-ui.js"></script> -->

 

<!--  div#ui-datepicker-div { -->
<!-- 	width: min-content !important; -->
<!-- } -->
<!-- div#ui-datepicker-div table thead { -->
<!-- 	width: 100% !important; -->
<!-- 	background-color: #257758; -->
<!-- } -->
<!-- div#ui-datepicker-div table tbody { -->
<!-- 	height: auto !important; -->
<!-- } -->
<!-- .ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-all -->
<!-- 	{ -->
<!-- 	background: #198754 !important; -->
<!-- }  -->
 

<section class="dashboard-page">
<div class="container-fluid">
	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="title mb-30">
					<h2>Upload Data</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">Upload Data</li>
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
			<div class="col-12 col-lg-12 com-md-12 col-sm-12">
				<!-- input style start -->
                <form:form action="Exp_Excel_practitioner_action?${_csrf.parameterName}=${_csrf.token}" method="POST" modelAttribute="Exp_Excel_practitioner_cmd" enctype="multipart/form-data">
				<div class="card-style mb-30">
				<div class="custom-field-block">
					<h6 class="mb-25">Upload Data</h6>
						<div class="row">



					<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="text-input">Role Type <span class="mandatory">*</span></label>
								<div class="select-position custom-d-none"  id = "div_role">  
	<!-- 										<select name="role_type" id="role_type" > -->
<!-- 								        	<option value="0">--Select--</option> -->
<%-- 											<c:forEach var="item" items="${getRoleNameList}" varStatus="num" > --%>
<%-- 		             								<option value="${item.roleId}">${item.role}</option> --%>
<%-- 		             							</c:forEach> --%>
<!-- 										</select> -->
										
										
<!-- 										<select name="role_type" id="role_type" > -->
<!-- 								        	<option value="0">--Select--</option> -->
<!-- 								        	<option value="25">Students</option> -->
<!-- 								        	<option value="35">Interns</option> -->
<!-- 								        	<option value="27">Practitioners</option> -->
<!--  										</select> -->
 										<select name="role_type" id="role_type" class="singleselect form-control form-control-lg" >
 										<option value="0">--Select--</option>
								        	<c:if test="${role != 'State Council'}" >
								        	<option value="25">Students</option>
								        	<option value="35">Interns</option>
								        	</c:if>
								           <c:if test="${role == 'State Council'}" >
								        	<option value="27">Practitioners</option>
								         </c:if>
 										</select>
 										
									</div>
								
								</div>				
									<label id = "role_type_lbl" ></label>				
							 
							</div>





							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-1">
									<label for="username">Upload File<span class="mandatory">*</span></label>
									<input type="file" accept=".xls" id="u_file1" name="u_file1" class="form-control" autocomplete="off">
								</div>			
								
								<!-- end select -->
							</div>
		
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-1">
															<label for="username">Date<span class="mandatory">*</span></label> 
															<input type="text" name="upload_date" id="upload_date" maxlength="10"  
															class="form-control-sm form-control effect-9 "  
															aria-required="true" autocomplete="off" value="DD/MM/YYYY" >
								</div>
							</div>
							</div>
								
								
								
								
								
								
						</div>					
			<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
						<ul class="buttons-group mainbtn">

							<!-- <li>
							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch" type="button"><i class="lni lni-search-alt"></i>search</a>
							</li> -->
							<li>
								<input id="btn-save" class="main-btn info-btn btn-hover btnsave" type="submit" value="Save" >
							</li> 
							
							<li>
								<input type="button" id="btnExport" class="main-btn active-btn btn-hover  btn-iconic-icon btnexport" value="Export Sample Template Format" >
							</li>
							<li><a
								class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
								id="btn-reload" value="Search"><i class="lni lni-search-alt"></i>Search</a>
							</li>
							<li>
								<a href="exp_excel_practitioner_url" class="main-btn dark-btn btn-hover btnreset" type="button" value="Reset">Reset</a>
							</li>
							
						</ul>
						</div>
						</div>
						</div>
						</div>
						<!-- Bottom Button End -->
					
				
				<!-- end card -->
            </form:form>
			</div>
		</div>
	</div>
	<section class="single-detail-block">
	    <div class="tables-wrapper">
            <div class="row">
              <div class="col-lg-12 col-md-12 col-sm-12">
                
                <div class="card-style mb-30">
                  <div class="table-wrapper table-responsive custom-datatable-p">
                    <table id="Search_exp_excel" class="table">
                      <thead>
                        <tr>
                         <th><h6>Sr No.</h6></th>
						 <th><h6>Name</h6></th>
						 <th><h6>Date of birth</h6></th>
						 <th><h6>Aadhar card no</h6></th>
						 <th><h6>Email</h6></th>
						 <th><h6>Mobile number</h6></th>
						 <th><h6>Admission date</h6></th>
						 <th><h6>System</h6></th>
						 <th><h6>Degree</h6></th>
						 <th><h6>Enrollment no</h6></th>
						 <th><h6>Upload date</h6></th>
				         <th><h6>Gender</h6></th>
				         <th><h6>Action</h6></th>
                        </tr>
                        <!-- end table row-->
                      </thead>
                      <tbody class="custom-datatablepra">
                      </tbody>
                    </table>
                    <!-- end table -->
                  </div>
                </div>
                <!-- end card -->
              </div>
              <!-- end col -->
            </div>
            <!-- end row -->
          </div>
          </section>
          </div>
     
          </div>
</section>
 

<c:url value="Exp_practitioner_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 


<c:url value="intern_Regulation_Url" var="intern_Regulation_Url" />
<form:form action="${intern_Regulation_Url}" method="get" id="intern_Regulation_Urlinstform"
	name="intern_Regulation_Urlinstform" modelAttribute="id7">
	<input type="hidden" name="id7" id="id7" value="0" />
</form:form>
<script nonce="${cspNonce}" type="text/javascript">
$(document).ready( function() {
	
		
	mockjax1('Search_exp_excel');
	table = dataTable('Search_exp_excel');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
			 if('${role}' == "State Council"){
		 		 $("#role_type").val("27");
		 		 $("#div_role").hide();
		 		 $("#role_type_lbl").text("Practitioners");
			  } else{
				  $("#div_role").show();
			  }
			datepicketDate('upload_date');
			
		});
		
document.addEventListener('DOMContentLoaded', function() {
 
	document.getElementById('upload_date').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('upload_date').onfocus = function() {
			return this.style.color='#000000';
		};
// 		document.getElementById('upload_date').onchange = function() {
// 			return calculate_age('upload_date');
// 		};
		document.getElementById('upload_date').onblur = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		document.getElementById('upload_date').onkeyup = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('upload_date').onchange = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		document.getElementById('upload_date').onchange = function() {
			validateDate_FutureDate(this.value,this);
		};
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		
		document.getElementById('btnExport').onclick = function() {
				getExcel();
		};
});
		
function getExcel() {
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('search2').submit();
}

function setTimeLoadForTable(){
	
	document.querySelectorAll('.popdegreep').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('InstiaccessId'+val).value;
			if (confirm('Are You Sure You Want to View Detail?')) {
				Pop_Up_DegreeP(gdid);
			} else {
				return false;
			}
		})
	});	
};
	
function Pop_Up_DegreeP(id)
{  
	$("#id7").val(id); 
// 	alert(id);
	document.getElementById('intern_Regulation_Urlinstform').submit();
}

function Validation(){
	if ($("#u_file1").val().trim() == "") {
		alert("Please Upload the File");
		$("input#u_file1").focus();
		return false;
	}
	
	var u_file1 = $("#u_file1").val();
	var lastDot = u_file1.lastIndexOf('.');
	var fileName = u_file1.substring(0, lastDot);
	var ext = u_file1.substring(lastDot + 1);
	
	if(ext != "xls"){
		alert("Please Upload .xls Format Excel File");
		$("input#u_file1").focus();
		return false;
	}
	
	 if ($("#upload_date").val() == "" || $("#upload_date").val() == "DD/MM/YYYY") {
			alert("Please Select Date");
			$("#upload_date").focus() 
			return false;
	 }
	 
	 if ($("select#role_type").val() == "0" ) {
			alert("Please Select Role Type");
			$("select#role_type").focus() 
			return false;
	 }
	 
	return true;
}

function data(Search_exp_excel) {
	jsondata = [];
	var table = $('#' + Search_exp_excel).DataTable();
	var info = table.page.info();
//		var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	//var orderColunm = $(table.column(order[0][0]).header()).attr('id').toLowerCase();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];
	var role_type = $("#role_type").val();
	var upload_date = $("#upload_date").val();
	
	 
	$.post("getFilter_Exp_Excel_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		role_type : role_type,
		upload_date : upload_date
		 
		
	}, function(j) {
		for (var i = 0; i < j.length; i++) {
				    jsondata.push([ j[i].ser,j[i].name,j[i].dob,j[i].aadhar_card,j[i].email,j[i].mobile_no,j[i].admission_date,j[i].system,j[i].degree,j[i].enrollment_no,j[i].upload_date,j[i].gender,j[i].vm1]);
		}
	});
	
	$.post("getTotalEdu_Exp_Excel_dataCount?" + key + "=" + value, {
		 
	}, function(j) {
		FilteredRecords = j;
		});
	setTimeout(setTimeLoadForTable, 1000);
}
</script>