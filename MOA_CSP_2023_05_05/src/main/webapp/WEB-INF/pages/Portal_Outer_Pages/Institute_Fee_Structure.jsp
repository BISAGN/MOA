<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--start pooja -->
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<link rel="stylesheet" href="admin/assets/db_css/db_style.css">
<link href="admin/js/jquery/jquery-ui.css" rel="Stylesheet"></link>
<script src="admin/assets/db_js/CommonValidation.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="admin/assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<link rel="stylesheet" href="admin/assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<script type="text/javascript" src="admin/assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="admin/assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!--End pooja -->
<script type="text/javascript" src="js\watermark\common.js"></script>
<script src="js/common/multicheck.js"></script>
<script src="js/Dashboard/multicheck.css"></script>



<link href="admin/assets/img/favicon.ico" rel="icon">
<link href="admin/assets/font/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="admin/assets/vendor/animate.css/animate.min.css"
	rel="stylesheet">
<link href="admin/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="admin/assets/vendor/swiper/swiper-bundle.min.css"
	rel="stylesheet">

<link href="admin/js/dropDown/select2.min.css" rel="Stylesheet"></link>

<script src="admin/js/dropDown/select2.min.js" type="text/javascript"></script>

<link href="admin/assets/vendor/hover.css" rel="stylesheet" media="all">
<link href="admin/assets/css/style.css" rel="stylesheet">
<link href="admin/assets/css/custom_pages_style.css" rel="stylesheet">
<link href="admin/assets/css/responsive.css" rel="stylesheet">
<link rel="stylesheet"
	href="admin/assets/vendor/common_custom_style.css">
<link href="admin/assets/vendor/svg-animation.css" rel="stylesheet"
	media="all">




<section class="page-content">
	<!--  Intro Single  -->
	<section class="intro-single custom_head">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-lg-8">
					<div class="title-single-box">
						<h1 class="title-single">Institute Information</h1>
						<!--    <span class="color-text-a">Your message has been sent. Thank you! </span> -->
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="landingpage">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">Institute Information</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Intro Single-->

	<section class="dashboard-page studentenrollmen" id="instituteinformationdiv">
		<div class="container" id="studentenrollmentdiv">
			<div class="form-elements-wrapper">
				<div class="row">
					<div class="col-12">
						<!-- input style start -->
						<div class="card-style mb-30">
							<h6 class="mb-25">Institute Information</h6>

							<div class="row">
								<div class="col-12">

									<div class="col-12 col-sm-12 col-md-12 col-lg-12">
										<div class="input-style-2 mb-0">
											<lable>Choose System</lable>
										</div>
									</div>

									<div class="input-style-form-check">

										<c:forEach var="item" items="${getsystemlist}" varStatus="num">
											<div class="form-check radio-style">
												<input type="radio" id="${item.system_name}"
													name="system_name" class="form-check-input"
													value="${item.id}"> <label
													for="${item.system_name}" class="form-check-label" >${item.system_name}</label>
											</div>
										</c:forEach>
											<div class="form-check radio-style">
												<input type="radio" id="ALL"
													name="system_name" class="form-check-input"
													value="001"> <label
													for="ALL" class="form-check-label">ALL</label>
											</div>

									</div>

								</div>

								<%-- <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1 mb-30">
										<label>Select Book<span class="mandatory">*</span></label>
										<div id="book_opt" name="book_opt" class="select-position">
											<select class="" id="book_select" name="book_select">
												<option value="0" id="" class="">--Select--</option>
											</select>
										</div>

										<div id="book_option" class="multiselect">

											<c:forEach var="item" items="${getstatelist}" varStatus="num">

												<div class="form-check radio-style checkbox-style ">
													<input class="multi form-check-input mr-5" type="checkbox"
														id="book_option_${num.index+1}"
														name="book_option_${num.index+1}" value="${item.state_id}">
													<label class="lbl" value="${item.state_name}" for="first">${item.state_name}</label>
												</div>
											</c:forEach>



											<input class="multi form-check-input mr-5" type="hidden"
												id="multiSelect_Book" name="multiSelect_Book" value="">


										</div>
									</div>
								</div> --%>

								<div class="col-12 col-sm-12 col-md-12 col-lg-12">

									<div class="select-style-2 mb-0">
										<label>Select Institute</label>

										<div class="select-position">
											<select name="institute_id" id="institute_id"
												class="form-control ">
												<option value="0" selected="selected">-- Select --</option>

											</select>
										</div>

									</div>

									<ul class="buttons-group mainbtn">
										<li><input id="btn-reload" name="btn-reload"
											class="main-btn info-btn btn-hover" type="button"
											value="Search" tabindex="13" /></li>
										<li><a href="InstituteInformation" id="reset"
											class="main-btn dark-btn n btn-hover" type="reset"
											value="Clear" tabindex="14">Reset</a></li>
									</ul>
									<!-- end input -->
								</div>
							</div>

							<div class="custom-d-none" id="table_view">
								<div class="row">
									<div class="col-12">
										<div class="card-style mb-30 db_theme_default">
											<div
												class="table-wrapper table-responsive custom-datatable-p">
												<table class="table" id="institute_fee_structure">
													<thead>
														<tr>
															<th><h6>No</h6></th>
															<th><h6>System Type</h6></th>
															<th><h6>University</h6></th>
															<th><h6>Institute Code</h6></th>
															<th><h6>Institute Name</h6></th>
															<th><h6>Institute Image</h6></th>
															<th><h6>Institute Abbreviation</h6></th>
															<th><h6>Institute Unique Code</h6></th>
															<th><h6>Total Seats</h6></th>
															<th><h6>Phone Number</h6></th>
															<th><h6>Email ID</h6></th>
															<th><h6>Country</h6></th>
															<th><h6>State</h6></th>
															<th><h6>District</h6></th>
															<th><h6>Address</h6></th>
															<th><h6>View Other Details</h6></th>
														</tr>
														<!-- 						end table row -->
													</thead>
													<tbody>
													</tbody>
												</table>
												<!-- 				end table -->
											</div>
										</div>
										<!-- 		end card -->
									</div>
									<!-- 	end col -->
								</div>

							</div>
							
							
						</div>

				</div>
			</div>
		</div>

	</section>
	
<!-- <div class="modal fade" id="modelWindow" aria-hidden="true"
	aria-labelledby="okModalToggle" tabindex="-1">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-body">
				<h3 class="modal-title" id="myLargeModalLabel">Institute Fee Details</h3>
				<div class="row">
					<table class="table" id="viewotherdetailtable">
						<thead>
							<tr>
								<th>Ser</th>
								<th>Category</th>
								<th><h6>Fees Type</h6></th>
								<th><h6>Fees</h6></th>
								<th><h6>Hostel Facility Available</h6></th>
							</tr>
						</thead>
						<tbody id="att_TbbodyNameMed">
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group" data-bs-dismiss="modal">
					<li><a href="#0" class="btn btn-danger" id="cls_modelid">Close</a></li>
				</ul>

			</div>
		</div>
	</div>
</div> -->


<div class="modal fade custom-modal bd-example-modal-lg" tabindex="-1"
	role="dialog" aria-labelledby="myLargeModalLabel" id="modelWindow" aria-hidden="true">
	<div class="modal-dialog modal-xl modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="myLargeModalLabel">Institute Fee Details</h3>
				<i data-dismiss="modal" aria-label="Close" data-bs-dismiss="modal" class="bi bi-x-lg"></i>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner">
						<table class="table"
							id="Students_MarksheetUrl">
							<thead>
								<tr>
									<th>Ser</th>
									<th>Category</th>
									<th>Fees Type</th>
									<th>Fees</th>
									<th>Hostel Facility Available</th>
								</tr>
							</thead>
							<tbody id="att_TbbodyNameMed">
							</tbody>
						</table>
				</div>
				<div class="modal-footer">
					<ul class="buttons-group">
						<li><button type="button"
								class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
								data-dismiss="modal" aria-label="Close">Close</button></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>


<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	$.ajaxSetup({
		async: false
	});
	
	mockjax1('institute_fee_structure');
 	table = dataTable('institute_fee_structure');
 	$('#btn-reload').on('click', function() {
 		table.ajax.reload();
 		
 	});
 	
// 	$(".multiselect").toggle();
	
// 	$(".mandatory").hide();

// 	$(".multiselect").hide();
// 	$("#hid").hide();

	$("#book_select").click(function(){
		$(".multiselect").toggle();

	});
 	
});




	document.addEventListener('DOMContentLoaded', function() {
		 
		 	$('input:radio[name=system_name]').change(function () {
			var system_id = $("input[name='system_name']:checked").val()
			return getInstitutelist(system_id);
			
	        });
		 	
		 	
		 	
			document.getElementById('btn-reload').onclick = function() {
				hide_show_datatable();
			};
		 	
		
	});

	//GET INSTITUTE LIST
	function getInstitutelist(system_id) {
	
				$.post(
						"getInstituteList?" + key + "=" + value,
						{
							system_id : system_id
						},
						function(j) {
							
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#institute_id").html(options);
						});
	}
	
	function setTimeLoadForTable(){
		
		
		document.querySelectorAll('.view_details').forEach((items, index) => {
			
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var veiw_id = document.getElementById('viewdetails'+val).value;
				if (confirm('Are You Sure You Want to View Detail?')) {
					View_Datails(veiw_id);
				} else {
					return false;
				}
			})
		});
		
	}
	
	//VIEW POP-UP MESSAGE
	function View_Datails(veiw_id) {
		
		$('tbody#att_TbbodyNameMed').empty();
			$
					.post( 
							"Institute_All_Fee_Details?" + key
									+ "=" + value,
							{
								veiw_id : veiw_id
								
							},
							function(j) {
// 								alert(j);
								for (var i = 0; i < j.length; i++) {
									var ser = parseInt(i) + 1;
									$("tbody#att_TbbodyNameMed")
											.append(
													'<tr id="tr_id_attNameMed'+ser+'"><td class="min-width">'
															+ ser
															+ '</td>'
															+ '<td class="min-width">'
															+ j[i][0]
															+ '</td>'
															+ '<td class="min-width">'
															+ j[i][1]
															+ '</td>'
															+ '<td class="min-width">'
															+ j[i][2]
															+ '</td>'
															+ '<td class="min-width">'
															+ j[i][3]
															+ '</td>'
															+ '</tr>');
									
								}
							});
			
			  $('#modelWindow').modal('show');
		}

	//DATA TABLE
	function data(institute_fee_structure) {
		
		jsondata = [];
		var table = $('#' + institute_fee_structure).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
		.toLowerCase();
		var orderType = order[0][1];
		var institute_id = $("#institute_id").val();
		
		$.post("getFilterInstitute_Fee_Structure_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			institute_id : institute_id

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].system_name, j[i].university_name,j[i].code,j[i].institute_name,j[i].img,j[i].college_abbr,
					j[i].college_unique_id,j[i].no_of_part,j[i].institute_mob_no,j[i].institute_email,j[i].name,j[i].state_name,j[i].district_name,
					j[i].address,j[i].view]);
			}
		});
		$.post("getTotalInstitute_Fee_Structure_dataCount?" + key + "=" + value, {
			Search : Search,
			institute_id : institute_id
			
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
	function hide_show_datatable(){
		 $('#table_view').show();
	}

</script>	
