<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script type="text/javascript" src="js\watermark\common.js"></script>
<link rel="stylesheet" href="assets/db_css/db_custom_style.css">
<link href="assets/vendor/bs-wizard/bs_wizard.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="assets/vendor/internal_css.css">

<section class="search_regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>Track Status(UG)</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a>
								</li>

								<li class="breadcrumb-item active" aria-current="page">Track Status</li>
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
						<form:form name="track_status" id="track_status" action="track_statusAction"
						method="post" class="form-horizontal" modelAttribute="track_statusCMD">
					<div class="card-style mb-30">
						<h6 class="mb-25">Track Status(UG)</h6>
						  <input type="hidden" id="userId" name="userId"> 
						<div class="row">
<!-- 								<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
<!--                									 <div class="select-style-1"> -->
<!-- 									                  <label>Role<strong class="mandatory">*</strong></label> -->
<!-- 									                <div class="select-position"> -->
<!-- 									                  <select name="choose_role" id="choose_role" class="form-control customselect">						  -->
<!-- 															<option value="">---choose role---</option> -->
<!-- 															<option value="1">Institute</option> -->
<!--  														    <option value="2">University</option>  -->
<!-- 															<option value="3">Commission</option>  -->
<!-- 														</select></div> -->
<!-- 										         </div> -->
<!-- 								</div> -->
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
               									 <div class="select-style-1">
									                  <label>Status<strong class="mandatory">*</strong></label>
									                  <div class="select-position">
									                  <select name="institute_status" id="institute_status" class="form-control customselect">						 
															<option value="">---Select Status---</option>
															<option value="0">Pending</option>
															<option value="1">Approved</option>
<!-- 														<option value="-1">Rejected</option> -->
														</select></div>
										         </div>
								</div>
								
						</div>
						<ul class="buttons-group mainbtn">
						
						<li><a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon"
								type="button" value="Search"><i class="lni lni-search-alt"></i>Search</a></li>
									
							<li><a href="track_UG_status" class="main-btn dark-btn n btn-hover" value="Reset">Reset</a></li>
								
<!-- 							<li><a  id="status_view" class="main-btn active-btn btn-hover" >Preview</a></li> -->
							
							<li><button id="download"	class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon"
									type="button"><i class="bi bi-file-pdf"></i>Download PDF</button></li>
						</ul>
					</div>
					</form:form>
					
				</div>
			</div>
		</div>
   <div> 
<!-- 	<h2 style=" color: red;"> <label id ="sts" name ="sts" value=""  > </label></h2> -->
</div>  
<div class="custom-bs-wizard">
<div class="row">
<div class="col-12 col-sm-12 col-md-12 col-lg-12" id="track_div">
	<div class="bs-wizard">
		<div class="bs-wizard-step">
			<div class="text-left bs-wizard-stepnum">INSTITUTE</div>
			<div class="progress">
				<div class="progress-bar "></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>

		<div class="bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">UNIVERSITY</div>
			<div class="progress">
				<div class="progress-bar "></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>

		<div class="bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">COMMISSION</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>
		
	</div>
	</div>

	<div class="col-12 col-sm-12 col-md-12 col-lg-12 custom-d-none" id="track_div0">
		<div class="bs-wizard">
		<div class="bs-wizard-step step-1 active">
			<!-- active -->
			<div class="text-left bs-wizard-stepnum stepnum-active1">INSTITUTE</div>
			<div class="progress">
				<div class="progress-bar step-1"></div>
			</div>
			<a href="#" class="bs-wizard-dot check bi bi-check step-1"></a>
		</div>

		<div class="bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">UNIVERSITY</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>

		<div class="bs-wizard-step ">
			<div class="text-left bs-wizard-stepnum">COMMISSION </div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot"></a>
		</div>
	</div>
	</div>
	
		<div class="col-12 col-sm-12 col-md-12 col-lg-12 custom-d-none" id="track_div2">
		<div class="bs-wizard">
		<div class="bs-wizard-step step-2 active">
			<div class="text-left bs-wizard-stepnum stepnum-active2">INSTITUTE</div>
			<div class="progress">
				<div class="progress-bar"></div>
			</div>
			<a href="#" class="bs-wizard-dot  check bi bi-check step-2"></a>
		</div>
		
		<div class="bs-wizard-step step-2 active">
			<div class="text-left bs-wizard-stepnum stepnum-active2" >UNIVERSITY</div>
			<div class="progress progress-line" style="background-color: #5d657b;" > <!--  -->
					<div class="progress-bar"></div>
				</div>
				<a href="#" class="bs-wizard-dot check bi bi-check step-2"></a>
			</div>
			
			<div class="bs-wizard-step ">
				<div class="text-left bs-wizard-stepnum">COMMISSION</div>
				<div class="progress">
					<div class="progress-bar"></div>
				</div>
				<a href="#" class="bs-wizard-dot"></a>
			</div>
		
		</div>
		</div>	
	
	<div class="col-12 col-sm-12 col-md-12 col-lg-12 custom-d-none" id="track_div4"><!-- style="display: none" -->
		<div class="bs-wizard">
			<div class=" bs-wizard-step step-4 active">
				<div class="text-left bs-wizard-stepnum stepnum-active4">INSTITUTE</div>
				<div class="progress">
					<div class="progress-bar"></div>
				</div>
				<a href="#" class="bs-wizard-dot  check bi bi-check step-4"></a>
			</div>
	
			<div class=" bs-wizard-step step-4 active">
				<div class="text-left bs-wizard-stepnum stepnum-active4">UNIVERSITY</div>
				<div class="progress">
					<div class="progress-bar"></div>
				</div>
				<a href="#" class="bs-wizard-dot  check bi bi-check step-4"></a>
			</div>
	
			<div class=" bs-wizard-step step-4 active">
				<div class="text-left bs-wizard-stepnum stepnum-active4">COMMISSION</div>
				<div class="progress ">
					<div class="progress-bar "></div>
				</div>
				<a href="#" class="bs-wizard-dot check bi bi-check step-4"></a>
			</div>
		</div>
	</div>
    </div>
   </div>           
  
<div class="row">
			<div class="col-12">
				<div class="card-style mb-30"> 
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="track_student_status">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th><h6>Institute Name</h6></th>
									<th><h6>University Name</h6></th>
									<th><h6>Current Month & Year</h6></th>
									<th><h6>Status</h6></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<c:url value="getSearch_Status" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchstatus" name="searchstatus" modelAttribute="institute_status1">
	<input type="hidden" name="institute_status1" id="institute_status1" />
	<input type="hidden" name="choose_role1" id="choose_role1" />
<!-- <input type="hidden" name="status1" id="status1" value="0" /> -->
</form:form>

<c:url value="Download_Pro_Form_A" var="Download_Pro_Form_A" />
<form:form action="${Download_Pro_Form_A}" method="post" id="printForm" name="printForm">
	<input type="hidden" id="user_id1" name="user_id1" value="0"/>
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

	$(document).ready(function() {
		
			var userId = '${userId}';
			
			alert("1")
			
			$.post('get_track_status?' + key + "=" + value,{ userId : userId }).done(function(j) {
				
				alert("enter")
				
				var sts1="";
				var sts2="";
				var sts3="";
				
				alert("2")
				
		 var sizeofj = j.length;

		    if(sizeofj == 1 ){
		    	alert("3")
	 			$("div#track_div").show();
	 			$("div#track_div0").hide();
	 			$("div#track_div1").hide();
	 			}
				
		 		if (sizeofj > 0){
		 			alert("4")
		 			console.log(j);
		 			sts1 = j[0];
		 			sts2 = j[1];
		 			
			    if(sts1 == 0){
			    	alert("ooooo11111")
				    	$("div#track_div").hide();
			 			$("div#track_div0").show();
			 			$("div#track_div1").hide();
		 			}
			    if(sts2 == 0){
			    	alert("ooooo222")
				    	$("div#track_div").hide();
			 			$("div#track_div0").hide();
			 			$("div#track_div2").show();
		 			}
			    if(sts3 == 1){
			     	alert("ooooo3333")
				    	$("div#track_div").hide();
			 			$("div#track_div0").hide();
			 			$("div#track_div4").show();
		 			}
		 		}
		    });
			$("#userId").val(${userId});
				 
				mockjax1('track_student_status');
				table = dataTable('track_student_status');
				$('#btn-reload').on('click', function() {
					table.ajax.reload();
				});
				
				$("#userId").change(function(){
					var userId = $(this).val()
					  if(userId == ""){
							$("div#track_div").hide();
							$("div#track_div0").hide();
							$("div#track_div1").hide();
							$("div#track_div2").hide();
							$("div#track_div3").hide();	
							$("div#track_div4").hide();	
				 		}
					});
				
				$.ajaxSetup({
					async : false
				});
			});
function data(track_student_status) {
	
		jsondata = [];
		var table = $('#' + track_student_status).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var institute_status = $("#institute_status").val();
		
		$.post("getFilter_All_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			institute_status : institute_status

		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				var remark ="";
				if(j[i].remark == null){
					remark ="-";
				}else{
					remark=j[i].remark;
				}
				jsondata.push([ j[i].ser, j[i].inst_name,j[i].uni_name,j[i].month_year,j[i].status]);
			}
		});
		
		$.post("getTotal_All_dataCount?" + key + "=" + value, {
			Search : Search,
			institute_status: institute_status
		}, function(j) {
        
			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
}
function setTimeLoadForTable(){



}
function track_search() {
	$("#institute_status1").val($('#institute_status').val());
	$("#choose_role1").val($('#choose_role').val());
	document.getElementById('searchstatus').submit();
}

//DOWNLOAD MAIN PDF 
function getPDF() {
		$("#user_id1").val(p_id);
		document.getElementById('printForm').submit();
	}
function View_Data(p_id) {
	$("#id2").val(p_id);
	document.getElementById('View_Form').submit();
}	

document.addEventListener('DOMContentLoaded', function() {
	
document.getElementById('btn-reload').onkeypress = function() {
	 return track_search();
};
document.getElementById('download').onclick = function() {
	return getPDF();
};

});
</script>
