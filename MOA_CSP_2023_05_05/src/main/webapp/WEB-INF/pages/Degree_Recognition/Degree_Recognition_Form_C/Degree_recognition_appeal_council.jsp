<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <script type="text/javascript" src="assets/js/jquery-3.6.0.min.js"></script> -->
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript"	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">

<!-- <script src="js/amin_module/webmaster/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">

<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

<script type="text/javascript" src="js/watermark/common.js"></script>

<link rel="stylesheet" href="assets/vendor/internal_css.css">

<section class="search_regulation Degree_Recognition">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>
							<span id="lbladd">View Appeal Details</span>
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Appeal Form</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->

		<div class="custom_v_tab">
			<div class="row">
				<div class="col-lg-12">
					<!-- input style start -->
					<div class="card-style mb-30">
						<div class="tabs content_h800">

							<!-- Start Forms  -->
							<!-- Form A UG -->

							<div class="tab tablinks dg-rec-block" id="tab_id7">
								<button class="tab-toggle">First Choose The University</button>
							</div>
							<div class="content tabcontent" id="Form_z">
								<h4 class="heading">Choose The University and Institute</h4>
								<div class="inst_block mb-10">
									<h6 class="mb-2">Instruction</h6>
									<ul class="inst_list">
										<li><p class="inst_text">Press last tab to Approve all the form details</p></li>
									</ul>
								</div>
								<div class="row">
									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="select-style-1">
											<label>Select University<strong class="mandatory">*</strong></label>
											<div class="select-position">
												<select name="uni_id" id="uni_id" class="form-control-lg select2 narrow wrap">
													<option value="0">---Select University---</option>
													<c:forEach var="item" items="${getMedUniversityName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														<%-- 																<option value="${item.university_name}" name="${item.university_name}">${item.university_name}</option> --%>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
<!-- 									<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 										<div class="select-style-1"> -->
<!-- 											<label>Select Institute<strong class="mandatory">*</strong></label> -->
<!-- 											<div class="select-position"> -->
<!-- 												<select name="institute_id" id="institute_id" -->
<!-- 													class="form-control-lg select2 narrow wrap"> -->
<!-- 													<option value="0">---Select Institute---</option> -->
<!-- 												</select> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="select-style-1">
											<label>Council Status<strong class="mandatory">*</strong></label>
											<input type="hidden" id="id" name="id" class="form-control"
												value="0" autocomplete="off">
											<div class="select-position">
												<select name="institute_status" id="institute_status"
													class="singleselect form-control form-control-lg">
													<option value="">---Select Status---</option>
													<option value="0">Pending</option>
													<option value="1">Approved</option>
													<option value="-1">Rejected</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<ul class="buttons-group mainbtn">
									<li><a id="btn-reload"
										class="main-btn secondary-btn btn-hover btn-iconic-icon"
										type="button"><i
											class="lni lni-search-alt"></i>Search Details</a></li>
									<li>
								</ul>
							</div>

							<div class="tab tablinks dg-rec-block" id="tab_id1">
								<button class="tab-toggle">Appeal Form</button>
							</div>
							<div class="content tabcontent" id="Form_A">
								<h4 class="heading">Appeal Form</h4>
								<div class="row">
									<div class="col-12">
										<div class="card-style mb-30">
											<div class="table-wrapper custom-datatable-p">
												<table class="table" id="search_system_appeal_council">
													<thead>
														<tr>
															<th><h6>Ser No</h6></th>
															<th><h6>University</h6></th>
															<th><h6>Name of the aggrieved University</h6></th>
															<th><h6>Address of the aggrieved University</h6></th>
															<th><h6>E-Mail ID of concerned  University Registrar</h6></th>
														    <th><h6>Contact person Name</h6></th>
														    <th><h6>Contact person Designation</h6></th>
														    <th><h6>Contact person Mobile No</h6></th>
														    <th><h6>Contact person email ID</h6></th>
														    <th><h6>Name of the institute</h6></th>
														    <th><h6>Nomenclature of Degree</h6></th>
														    <th><h6>Abbreviation of Degree</h6></th>
														    <th><h6>Date of First Application to the Board</h6></th>
														    <th><h6>Date of denial of the Application</h6></th>
														    <th><h6>The reason of the denial of application</h6></th>
														    <th><h6>Appeal / Prayer of the University </h6></th>
														    <th><h6>The justification of Appeal along with documents </h6></th>
															<th><h6>Action</h6></th>
														</tr>
													</thead>
													<tbody class="custom-datatablepra"></tbody>
												</table>
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
	</div>
</section>

<script type="text/javascript" src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>


<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	
        if(window.location.href.includes("msg"))
        {
                 var url = window.location.href.split("?msg")[0];
                 window.location = url;
        }
		
		$("#tab_id1").hide();
		
});

function search_detail(){

	  if($("select#uni_id").val().trim() == "0") 
	  {
	       alert("Please Select University ");
	       $("select#uni_id").focus();
	       return false;
	  }
	  else if($("select#institute_status").val().trim() == "") 
	  {
	       alert("Please Select Council Status ");
	       $("select#institute_status").focus();
	       return false;
	  }
	
	mockjax1('search_system_appeal_council');
	table1 = dataTable('search_system_appeal_council');
	

	table1.ajax.reload();
	

	$("#tab_id1").show();
	
	if($("#institute_status").val() == "0") 
	{
		 $("btn-saveA").show();
    }
	if($("#institute_status").val() == "1") 
	{
		 $("btn-saveA").hide();
    }
	if($("#institute_status").val() == "-1") 
	{
		 $("btn-saveA").hide();
    }
}

////////////////////////////////

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('uni_id').onchange = function() {
			 getInstitute();
		};
		
		document.getElementById('btn-reload').onclick = function() {
			return search_detail();
		};
// 		document.getElementById('btn-saveA').onclick = function() {
// 			return Submit_Approval_Council();
// 		};
// 		document.getElementById('btn-save').onclick = function() {
// 			return Reject_council();
// 		};
	});
	
	


	function getInstitute() {
		var selval = $("#uni_id").val();0
		$.post("getInstituteUrl?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {
							var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								console.log(j[i]);
								options += '<option   value="' + j[i][3].id + '" name="'+j[i][3].id+'" >'
										+ j[i][3].institute_name + '</option>';
							}
							$("select#institute_id").html(options);
						});
	}
///////////////////////////////


//----------------------VIEW-TABLE---------------------------

function data(search_system_appeal_council){
	jsondata = [];
	var table = $('#' + tablename).DataTable();
			var info = table.page.info();
			var pageLength = info.length;
			var startPage = info.start;
			var endPage = info.end;
			var Search = table.search();
			var order = table.order();
			var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
			var orderType = order[0][1];
			
			var aggrieved_university_name = $("#aggrieved_university_name").val();
			var aggrieved_university_address = $("#aggrieved_university_address").val();
			var university_registrar_email_id = $("#university_registrar_email_id").val();
			var contact_person_name = $("#contact_person_name").val();
			var contact_person_designation = $("#contact_person_designation").val();
			var contact_person_email_id = $("#contact_person_email_id").val();
			var institute_name=$("#institute_name").val();
			var univercity_id= $("#univercity_id").val();
			var nomenclature_of_degree= $("#nomenclature_of_degree").val();
			var denial_application_date=$("#denial_application_date").val();
			var reason=$("#reason").val();
			var document=$("#document").val();
			var abbreviation_of_degree=$("#abbreviation_of_degree").val();
			var first_application_date=$("#first_application_date").val();
			var prayer_of_the_university=$("#prayer_of_the_university").val();
			var contact_person_mobile_no= $("#contact_person_mobile_no").val();
			var institute_status= $("#institute_status").val();
			var university_approved_status = $("#university_approved_status").val();
			
			$.post("getFilter_appeal_formc?" + key + "=" + value, {
				
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				id:0,
				univercity_id : univercity_id,
				institute_status : institute_status
				
				
			},
				      function(j) {
				    	  console.log(j)
				    	  
// 				    	  if($("#institute_status").val() == "-1" || $("#institute_status").val() == "1") {
// 		    		   		table.column(12).visible(false);
// 		    	   			}
// 		    	   			else{
// 		    		   				table.column(11).visible(true);
// 		    	   			}
							for (var i = 0; i < j.length; i++) {
								jsondata.push([j[i].ser, //0
									j[i].university_name,//1
									j[i].aggrieved_university_name,//3
									j[i].aggrieved_university_address,//4
									j[i].university_registrar_email_id,//5
									j[i].contact_person_name,//6
									j[i].contact_person_designation,//7
									j[i].contact_person_email_id,//8
									j[i].institute_name,//9
									j[i].nomenclature_of_degree,//10
									j[i].denial_application_date,//10
									j[i].reason,//10
									j[i].document,//10
									j[i].abbreviation_of_degree,//10
									j[i].first_application_date,//10
									j[i].prayer_of_the_university,//10
									j[i].contact_person_mobile_no,//10
									j[i].current_month_year,//10
									j[i].action]);//12
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_UG_aListCount?" + key + "=" + value, {
				id:0,
				uni_id : uni_id,
				institute_id : institute_id,
				institute_status : institute_status
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
// 			setTimeout(setTimeLoadForTable, 1000);
	 }


function setTimeLoadForTable(){
	document.querySelectorAll('.ADDuga').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdUga'+val).value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				viewData1(hid);
			} else {
				return false;
			}
		})
	});
	
	document.querySelectorAll('.clarificationformaData').forEach((items, index) => {
		items.addEventListener('click', event => {
			debugger;
			var val=parseInt(index)+1;
			var hid1 = document.getElementById('RejectformaId'+val).value;
				if (confirm('Are You Sure You Want to Clarified this Data ?')) {
					$('#myModal').show();
					var modal1 = document.getElementById('myModal');
					var span1 = document.getElementById('myModalClose');
					$("#rejVal").val(hid1);
					span1.onclick = function() {
					    modal1.style.display = "none";
					}
				} else {
					return false;
				}
		});
	});
	
}





</script>
