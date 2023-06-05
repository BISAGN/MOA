<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/watermark/common.js"></script>
<script type="text/javascript" src="js\watermark\common.js"></script>
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

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Register Alumni</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Register Alumni</li>
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
					<form:form action="clgRegisterAlumniAction?${_csrf.parameterName}=${_csrf.token}" method="POST" 
					class="form-horizontal" modelAttribute="clgRegisterAlumniCmd" enctype="multipart/form-data">
					
					<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="input-style-2 mb-0">
												<h3 class="mb-2">Choose Any One</h3>
											</div>
											<div class="input-style-form-check">
												<div class="form-check radio-style">
													<input type="radio" class="form-check-input" id="Upload"
														name="Choise" value="Upload"> <label for="Upload"
														class="form-check-label">Upload Through Excel</label>
												</div>
												<div class="form-check radio-style" >
													<input type="radio" class="form-check-input" id="Fillform"
														name="Choise" value="Fillform" checked="checked">
													<label class="form-check-label" for="ST">Fill Up
														Form</label>
												</div>
											</div>
											<!-- end input -->
					</div>
										
					<div id="UploadExcel" class="hide">
					<div class="row">
						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2">
								<label>Downlaod Format</label>
								<ul class="buttons-group">
									<li class="m-0">
									 <a id="downlaod" name="downlaod" tabindex="4"
										class="main-btn info-btn square-btn btn-hover"> <i
											class="lni lni-download mr-5"></i>Export Excel
									</a> 
									</li>
								</ul>
							</div>
							<!-- end input -->
						</div>

						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="input-style-2">
								<label>Upload Excel<span class="mandatory">*</span></label>
								<input type="file" class="form-control" accept=".xls"
									placeholder="upload your document" name="upload_excel1"
									id="upload_excel1" tabindex="5"> <span
									class='errorClass' id='upload_excel1_lbl'>${upload_excel1_lbl_msg}</span>
							</div>
							<input type="hidden" id="excorform" name="excorform" value="2">
							<!-- end input -->
						</div>
					</div>
					
					<ul class="buttons-group mainbtn" id="ul_btn_excel_id">
						<!-- <li><a id="btn-reload"
							class="main-btn secondary-btn btn-hover btn-iconic-icon"
							type="button"><i class="lni lni-search-alt"></i>Search</a></li> -->
						<li><input id="save_id" class="main-btn info-btn btn-hover"
							type="submit" value="Save" name="save_id"/>
						</li>
						<li><a href="clg_Add_Alumni_Url" id="reset"
							class="main-btn dark-btn n btn-hover" type="button">Reset</a>
						</li>
				</ul>
					
		<div class="tables-wrapper">
            <div class="row">
              <div class="col-lg-12">
                
                <div class="card-style mb-30" id="exp_excel">
                  <div class="table-wrapper table-responsive custom-datatable-p">
                    <table id="Search_exp_alum_excel" class="table">
                      <thead>
                        <tr>
                         <th><h6>Ser No.</h6></th>
						 <th><h6>Name</h6></th>
						 <th><h6>Mobile No</h6></th>
						 <th><h6>Email</h6></th>
						 <th><h6>Passing Year</h6></th>
						 <th><h6>Batch</h6></th>
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
          
				</div>
				<div id="fillform" class="">
					<div class="row">
						<div class="col-lg-4 col-md-6 col-sm-12">
							<div class="input-style-2">
								<label>Name<strong class="mandatory">*</strong></label>
								<input type="text" name="alum_name" id="alum_name" maxlength="50"
									class="form-control" placeholder="Enter name of Alumni">
							</div>
						</div>
						<div class="col-lg-4 col-md-6 col-sm-12">
							<div class="input-style-2">
								<label>Mobile Number<strong class="mandatory">*</strong></label>
								<input type="text" name="alum_mo_no" id="alum_mo_no" maxlength="10"
									class="form-control" placeholder="Enter Mobile Number of Alumni">
							</div>
						</div>
						<div class="col-lg-4 col-md-6 col-sm-12">
							<div class="input-style-2">
								<label>Email<strong class="mandatory">*</strong></label> <input
									name="alum_email" id="alum_email" class="form-control" maxlength="50"
									placeholder="Enter Email of Alumni" pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$">
							</div>
						</div>
						<div class="col-lg-4 col-md-6 col-sm-12">
							<div class="input-style-2">
								<label>Paasing Year<strong class="mandatory">*</strong></label> <input
									type="number" name="passing_year" id="passing_year"
									 class="form-control" maxlength="4"
									placeholder="Enter Passing Year of ALumni">
							</div>
						</div>
						<div class="col-lg-4 col-md-6 col-sm-12">
							<div class="input-style-2">
								<label>Batch<strong class="mandatory">*</strong></label>
								<input type="number" name="batch" id="batch" maxlength="4"
									class="form-control" placeholder="Enter Batch of Alumni">
							</div>
						</div>
					</div>
				</div>
				 <ul class="buttons-group mainbtn" id="ul_btn_form_id">
						<!-- <li><a id="btn-reload"
							class="main-btn secondary-btn btn-hover btn-iconic-icon"
							type="button"><i class="lni lni-search-alt"></i>Search</a></li> -->
						<li><input id="save_id" class="main-btn info-btn btn-hover"
							type="submit" value="Save" name="save_id"/>
						</li>
						<li><a href="clg_Add_Alumni_Url" id="reset"
							class="main-btn dark-btn n btn-hover" type="button">Reset</a>
						</li>
				</ul> 
					<!-- end card -->
				</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

	    

<c:url value="Exp_Excel_clg_reg_alumni" var="Exp_Excel_clg_reg_alumni" />
<form:form action="${Exp_Excel_clg_reg_alumni}" method="post" id="Exp_Excel2" name="Exp_Excel2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 


<script nonce="${cspNonce}" type="text/javascript">

$.ajaxSetup({
	async: false
});

$(document).ready(function() {
	mockjax1('Search_exp_alum_excel');
	table = dataTable('Search_exp_alum_excel');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	
	$("#fillform").show();
	$("#UploadExcel").hide();
	
	datepicketDate('post_date');
});

function UploadExcel() {
  	$("#UploadExcel").show();
  	$("#fillform").hide();
  	$("#excorform").val(1);
  	$("#ul_btn_excel_id").show();
  	$("#ul_btn_form_id").hide();
 }
 
function fillform() {
	$("#fillform").show();
	$("#UploadExcel").hide();
// 	$("#exp_excel").hide();
	$("#excorform").val(2);
	$("#ul_btn_form_id").show();
  	$("#ul_btn_excel_id").hide();
}

function setTimeLoadForTable(){
};

function Validation(){
	
	var excorform = $("#excorform").val();
	
	var u_file1 = $("#upload_excel1").val();
	var lastDot = u_file1.lastIndexOf('.');
	var fileName = u_file1.substring(0, lastDot);
	var ext = u_file1.substring(lastDot + 1);
	
	if(excorform == 1){
		if(ext != "xls"){
			alert("Please Upload Excel File");
			$("input#upload_excel1").focus();
			return false;
		}
		if ($("#upload_excel1").val().trim() == "") {
			alert("Please Upload the File");
			$("input#upload_excel1").focus();
			return false;
		}
	}
	
	if(excorform == 2){
		
		if ($("#alum_name").val().trim() == "") {
			alert("Please Enter Name");
			$("input#upload_excel1").focus();
			return false;
		}
		if ($("#alum_mo_no").val().trim() == "") {
			alert("Please Enter Mobile Number");
			$("input#upload_excel1").focus();
			return false;
		}
		if ($("#alum_email").val().trim() == "") {
			alert("Please Enter Email");
			$("input#alum_email").focus();
			return false;
		}
		
		if ($("#passing_year").val().trim() == "") {
			alert("Please Enter Passing Year");
			$("input#passing_year").focus();
			return false;
		}
		if ($("#batch").val().trim() == "") {
			alert("Please Enter Batch");
			$("input#batch").focus();
			return false;
		}
		
	}

	return true;
}

function getExcel() {
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('Exp_Excel2').submit();
}

function data(Search_exp_alum_excel) {
	jsondata = [];
	var table = $('#' + Search_exp_alum_excel).DataTable();
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
	
	 
	$.post("getFilter_Exp_Alum_Excel_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType
		 
		
	}, function(j) {
		for (var i = 0; i < j.length; i++) {
				    jsondata.push([ j[i].ser,j[i].alum_name,j[i].alum_mo_no,j[i].alum_email,j[i].alum_passing_year,j[i].alum_batch]);
		}
	});
	
	$.post("getTotalEdu_Exp_Alum_Excel_dataCount?" + key + "=" + value, {
		 
	}, function(j) {
		FilteredRecords = j;
		});
	setTimeout(setTimeLoadForTable, 1000);
}
	

document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('save_id').onclick = function(){
			return Validation();
	};
	
	document.getElementById('downlaod').onclick = function(){
			getExcel();
	};
	
	document.getElementById('Upload').onclick = function(){
		UploadExcel();
	};

	document.getElementById('Fillform').onclick = function(){
		fillform();
	};
	
});

</Script>
