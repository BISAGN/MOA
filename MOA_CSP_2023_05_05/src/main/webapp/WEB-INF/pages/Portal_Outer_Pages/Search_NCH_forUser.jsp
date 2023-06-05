<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- datatable style and js start-->
<link rel="stylesheet"
	href="admin/assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<link rel="stylesheet"
	href="admin/assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<script type="text/javascript"
	src="admin/assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="admin/assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- <link rel="stylesheet" href="admin/assets/db_css/db_custom_style.css"> -->


<!-- single select, search with select start -->
<link href="admin/js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="admin/js/dropDown/select2.min.js" type="text/javascript"></script>
<!-- single select, search with select end -->

<!-- common style start -->
<link rel="stylesheet"
	href="admin/assets/vendor/common_custom_style.css">
<!-- common style end -->

<script nonce="${cspNonce}">
	var username = "${username}";
</script>
<%-- <section class="search_regulation">
      <div class="container-fluid">
        <!-- title-wrapper start -->
        <div class="title-wrapper pt-30">
          <div class="row align-items-center">
            <div class="col-md-6">
              <div class="title mb-30">
                <h2>Nch Search For User</h2>
              </div>
            </div>
            <!-- end col -->
<!--             <div class="col-md-6"> -->
<!--               <div class="breadcrumb-wrapper mb-30"> -->
<!--                 <nav aria-label="breadcrumb"> -->
<!--                   <ol class="breadcrumb"> -->
<!--                     <li class="breadcrumb-item"> -->
<!--                       <a href="#0">Start View</a> -->
<!--                     </li> -->
<!--                     <li class="breadcrumb-item"><a href="#0">Regulation Forms</a></li> -->
<!--                     <li class="breadcrumb-item active" aria-current="page"> -->
<!--                       Report Form A -->
<!--                     </li> -->
<!--                   </ol> -->
<!--                 </nav> -->
<!--               </div> -->
<!--             </div> -->
            <!-- end col -->
          </div>
          <!-- end row -->
        </div>
        <!-- title-wrapper end -->
        <div class="search-regulation-wrapper">
          <div class="row">
          <div class="col-lg-12">
              <!-- input style start -->
              <div class="card-style mb-30">
                <h6 class="mb-25">NCH SEARCH FOR USER </h6>
               <div class="row">
							<div class="col-lg-4 col-md-6 col-sm-12">
								<div class="input-style-2">
									<label>First Name <strong class="mandatory"></strong></label> <input
										id="first_name" name="first_name" class="form-control"
										autocomplete="off" maxlength="25"
										placeholder="Maximum 25 Character"
										onkeypress="return onlyAlphabetsStringSpace(this,event);">
									<input type="hidden" id="id" name="id" class="form-control"
										value="0" autocomplete="off">
								</div>
							</div>
							<div class="col-lg-4 col-md-6 col-sm-12">
								<div class="input-style-2">
									<label> NRH No. <strong class="mandatory"> </strong>
									</label> <input id="nrh_en_no" name="nrh_en_no" class="form-control"
										autocomplete="off" maxlength="25"
										placeholder="Maximum 25 Character"> <input
										type="hidden" id="id" name="id" class="form-control" value="0"
										autocomplete="off">
								</div>
							</div>
<!-- 							<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 								<div class="input-style-2"> -->
<!-- 									<label>Registration Number<strong class="mandatory"> -->
<!-- 									</strong></label> <input type="text" id="reg_no" name="reg_no" -->
<!-- 										onkeypress="return isNumberOnly(event)" -->
<!-- 										oninput="this.value = this.value.toUpperCase()" maxlength="10" -->
<!-- 										class="form-control autocomplete" autocomplete="off"> -->
<!-- 								</div> -->
<!-- 							</div> -->
						</div>
                <div class="row">
							<div class="col-lg-4 col-md-6 col-sm-12">
								<div class="input-style-2">
									<label>State<strong class="mandatory"> </strong></label> <select
										name="per_state" id="per_state"
										class="form-control customselect" >
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${MedStateName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							  <div class="col-lg-4 col-md-6 col-sm-12">
								<div class="input-style-2">
									<label>Registration State<strong class="mandatory">
									</strong></label> <select name="registration_state" id="registration_state"
										class="form-control customselect" >
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${MedStateName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
<!-- 							<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 								<div class="input-style-2"> -->
<!-- 									<label>Date Of First Registration<strong -->
<!-- 										class="mandatory"></strong></label> <input type="date" -->
<!-- 										id="date_of_reg" name="date_of_reg" class="form-control" -->
<!-- 										autocomplete="off" maxlength="25" -->
<!-- 										placeholder="Maximum 25 Character" -->
<!-- 										onkeypress="return onlyAlphabetsStringSpace(this,event);"> -->
<!-- 									<span class="icon"><i class="lni lni-chevron-down"></i></span> -->
<!-- 								</div> -->
<!-- 							</div> -->
						</div>
                <div class="row">
<!-- 							<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 								<div class="input-style-2"> -->
<!-- 									<label> District<strong class="mandatory"> </strong> -->
<!-- 									</label> <select name="per_district" id="per_district" -->
<!-- 										class="form-control customselect"> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 								<div class="input-style-2"> -->
<!-- 									<label>Place Of Working<strong class="mandatory"> -->
<!-- 									</strong></label> <select name="place_of_working1" id="place_of_working1" -->
<!-- 										class="form-control customselect" style="width: 90%"> -->
<!-- 										<option value="0">--Select--</option> -->
										<c:forEach var="item" items="${PlaceOfWorking}"
											varStatus="num">
											<option value="${item.id}"
												name="${item.place_of_working_practitioner}">${item.place_of_working_practitioner}</option>
										</c:forEach>
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
						</div>
        <div class="row">
<!-- 							<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 								<div class="input-style-2"> -->
<!-- 									<label>Type of Degree<strong class="mandatory"> -->
<!-- 									</strong></label> <select name="type_of_degree" id="type_of_degree" -->
<!-- 										class="form-control customselect" -->
<!-- 										onchange="getDegreeName(this,1);"> -->
<!-- 										<option value="0">--Select--</option> -->
										<c:forEach var="item" items="${TypeOfDegree}" varStatus="num">
											<option value="${item.id}" name="${item.type_of_degree}">${item.type_of_degree}</option>
										</c:forEach>
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 								<div class="input-style-2"> -->
<!-- 									<label>Degree<strong class="mandatory"> </strong></label> <select -->
<!-- 										name="DegreeName1" id="DegreeName1" -->
<!-- 										class="form-control customselect"> -->
<!-- 										<option value="0">--Select--</option> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 								<div class="input-style-2"> -->
<!-- 									<label>Registration Is Renewable Or Permanent<strong -->
<!-- 										class="mandatory"> </strong> -->
<!-- 									</label> <select name="reg_renew_permanent" id="reg_renew_permanent" -->
<!-- 										class="form-control customselect"> -->
<!-- 										<option value="0">--Select--</option> -->
<!-- 										<option value="0">Renewable</option> -->
<!-- 										<option value="1">Permanent</option> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
						</div>
              <div class="row">
<!--                  <div class="col-lg-4 col-md-6 col-sm-12"> -->
<!--                 <div class="input-style-2"> -->
<!--                   <label> Registration District<strong class="mandatory">  </strong> </label> -->
<!--                   <select name="registration_district" id="registration_district" class="form-control autocomplete"> -->
<!-- 			  </select>    -->
<!--                 </div> -->
<!--                 </div> -->
<!--                                 <div class="col-lg-4 col-md-6 col-sm-12"> -->
<!--                 <div class="input-style-2"> -->
<!--                   <label> Status <strong class="mandatory"> </strong> </label> -->
<!--                   <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off"> -->
<!--                   <select name="institute_status" id="institute_status" class="form-control customselect">						  -->
<!-- 										<option value="0">Pending</option> -->
<!-- 										<option value="1">Approved</option> -->
<!-- 										<option value="2">Reject</option> -->
<!-- 								</select> -->
<!--                 </div> -->
<!--                 </div> -->
                 </div>  
                  <ul class="buttons-group mainbtn">
                  <li>
                    <a href="Search_NCH_forUser_Url" class="main-btn success-btn btn-hover btn-clear" value="Reset">Reset</a>
                  </li>
<!--                     <li> -->
<!--                     <input type="submit" class="main-btn secondary-btn btn-hover btn-save" value="Save" onclick="return Validate();"> -->
<!--                   </li> -->
                  <li>
                    <input type="button" class="main-btn active-btn btn-hover btn-search" id="btn-reload" value="Search">
                  </li>
<!--                   <li> -->
<!--                     <input type="button" class="main-btn primary-btn btn-hover" value="Approve" onclick="return setApproveStatus();"> -->
<!--                   </li> -->
<!--                   <li> -->
<!--                     <input type="button" class="main-btn danger-btn btn-hover" value="Reject" onclick="return setRejectStatus();"> -->
<!--                   </li> -->
                </ul>
              </div>
              <!-- end card -->
            </div>
          </div>
          <!-- end row -->
        </div>
       <div class="tables-wrapper">
            <div class="row">
              <div class="col-lg-12">
                <div class="card-style mb-30">
                  <div class="table-wrapper table-responsive custom-datatable-p">
                    <table id="Search_State_Prac" class="table">
                      <thead>
                        <tr>
							<th align="center">Ser No.</th>
			 				<th>Photo Path</th>
			 				<th>First Name</th>
							<th >NRH Enrollment No.</th>
							<th>State</th>
							<th>Registration State</th>
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
        </section> --%>
<section class="page-content">
	<section class="intro-single">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-lg-8">
					<div class="title-single-box">
						<h1 class="title-single">Nch Search For User</h1>
						<!--  <span class="color-text-a">Your message has been sent. Thank you! </span> -->
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="landingpage">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">Nch
								Search For User</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<section class="contact">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="card-style mb-30">
						<form action="forms/contact.php" method="post" role="form"
							class="php-email-form">
							<div class="row justify-content-center">
								<div class="col-lg-3 col-md-6 col-sm-12 col-12">
									<div class="form-group">
										<label>First Name <strong class="mandatory"></strong></label>
										<input id="first_name" name="first_name" class="form-control"
											autocomplete="off" maxlength="25" placeholder="First Name">
										<input type="hidden" id="id" name="id" class="form-control"
											value="0" autocomplete="off">
									</div>
								</div>
								<div class="col-lg-3 col-md-6 col-sm-12 col-12">
									<div class="form-group">
										<label> NRH No. <strong class="mandatory"> </strong>
										</label> <input id="nrh_en_no" name="nrh_en_no" class="form-control"
											autocomplete="off" maxlength="25" placeholder="NRH No.">
										<input type="hidden" id="id" name="id" class="form-control"
											value="0" autocomplete="off">
									</div>
								</div>
								<div class="col-lg-3 col-md-6 col-sm-12 col-12">
									<div class="form-group">
										<label>State<strong class="mandatory"> </strong></label> <select
											name="per_state" id="per_state"
											class="singleselect form-control form-control-lg customselect">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${MedStateName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-lg-3 col-md-6 col-sm-12 col-12">
									<div class="form-group">
										<label>Registration State<strong class="mandatory">
										</strong></label> <select name="registration_state" id="registration_state"
											class="singleselect form-control form-control-lg customselect">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${MedStateName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>

										</select>
									</div>
								</div>
								<div
									class="col-lg-12 col-md-12 col-sm-12 mb-3 justify-content-center d-flex">
									<div class="custom-btn footer-btn">
										<ul class="footer-btn-list">
											<li class="f-btn"><input type="button"
												class="main-btn secondary-btn btn-hover" id="btn-reload" value="Search"></li>
											<li class="f-btn"><a href="Search_NCH_forUser_Url"
												class="link-color main-btn dark-btn n btn-hover">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="db_theme_default">
						<div class="tables-wrapper">
							<div class="row">
								<div class="col-lg-12">
									<div class="card-style mb-30">
										<div class="table-wrapper table-responsive custom-datatable-p">
											<table id="Search_State_Prac" class="table">
												<thead>
													<tr>
														<th align="center"><h6>Ser No.</h6></th>
														<th><h6>Photo Path</h6></th>
														<th><h6>First Name</h6></th>
														<th><h6>NRH Enrollment No.</h6></th>
														<th><h6>State</h6></th>
														<th><h6>Registration State</h6></th>
													</tr>
													<!-- end table row-->
												</thead>
												<tbody>
												</tbody>
											</table>
											<!-- end table -->
										</div>
									</div>
									<!-- end card -->
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</section>
<%-- <c:url value="Edit_edu_reg_mstrUrl" var="Edit_edu_reg_mstrUrl" /> --%>
<%-- <form:form action="${Edit_edu_reg_mstrUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id1"> --%>
<!-- 	<input type="hidden" name="id1" id="id1" value="0" /> -->
<%-- </form:form> --%>
<%--  <c:url value="delete_edu_reg_mstr_Url" var="delete_edu_reg_mstr_Url" /> --%>
<%-- <form:form action="${delete_edu_reg_mstr_Url}" method="post" id="deleteForm" --%>
<%-- 	name="deleteForm" modelAttribute="id2"> --%>
<!-- 	<input type="hidden" name="id2" id="id2" value="0" /> -->
<%-- </form:form> --%>
<%-- <c:url value="Excel_Auth_Posted_query" var="excelUrl" /> --%>
<%-- <form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="cont_comd_ex"> --%>
<!-- 	 <input type="hidden" name="cont_comd_ex" id="cont_comd_ex"  value="0"> -->
<!-- 	   <input type="hidden" name="cont_corps_ex" id="cont_corps_ex" value="0"> -->
<!-- 	   <input type="hidden" name="cont_div_ex" id="cont_div_ex" value="0"> -->
<!-- 	   <input type="hidden" name="cont_bde_ex" id="cont_bde_ex" value="0"> -->
<!-- 	   <input type="hidden" name="cont_comd_txt" id="cont_comd_txt" > -->
<!-- 	   <input type="hidden" name="cont_corps_txt" id="cont_corps_txt"> -->
<!-- 	   <input type="hidden" name="cont_div_txt" id="cont_div_txt"> -->
<!-- 	   <input type="hidden" name="cont_bde_txt" id="cont_bde_txt"> -->
<!-- 	   <input type="hidden" name="unit_name_ex" id="unit_name_ex"> -->
<!-- 	   <input type="hidden" name="sus_no_ex" id="sus_no_ex"> -->
<!-- 	   <input type="hidden" name="typeReport1" id="typeReport1" value="0" /> -->
<%-- </form:form>  --%>

<!-- The Modal -->
<div id="myModal" class="modal">
	<span class="close">&times;</span> <img class="modal-content"
		id="img01">
	<div id="caption"></div>
</div>
<script nonce="${cspNonce}">
// function setselectall(){
 
// 	if ($("#nSelAll").prop("checked")) {
// 		$(".nrCheckBox").prop('checked', true);
// 	} else {
// 		$(".nrCheckBox").prop('checked', false);
// 	}
	
// 	var l = $('[name="cbox"]:checked').length;
// 	 $("#tregn").val(l);
// 	document.getElementById('tregn').innerHTML = l;
	
// }
	$(document).ready(function() {
		mockjax1('Search_State_Prac');
		table = dataTable('Search_State_Prac');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$.ajaxSetup({
			async : false
		});
	});
	function data(Search_State_Prac) {
		jsondata = [];
		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		var table = $('#' + Search_State_Prac).DataTable();
		var info = table.page.info();
// 		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		//var orderColunm = $(table.column(order[0][0]).header()).attr('id').toLowerCase();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		
		var nrh_en_no = $("#nrh_en_no").val();
		var first_name = $("#first_name").val();
		var per_state = $("#per_state").val();
		var registration_state = $("#registration_state").val();

		$.post("getFilter_NCH_Prac_foruserdata?" + key + "=" + value, {
			
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			
			nrh_en_no:nrh_en_no,
			first_name:first_name,
 		  	 per_state:per_state,
			 registration_state:registration_state
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
			//$("#tregnn").text(" "+j.length);	
				jsondata.push([ j[i].ser,j[i].img, j[i].first_name, j[i].nrh_en_no, j[i].per_state, j[i].regisration_state
					  ]);
			}
		});
		$.post("getTotalNCH_Prac_forUserdataCount?" + key + "=" + value, {
			nrh_en_no:nrh_en_no,
			first_name:first_name,
			 per_state:per_state,
			 registration_state:registration_state
		}, function(j) {
			FilteredRecords = j;
			});
	}
		// Get the modal
		function imageView(obj){

		var modal = document.getElementById("myModal");
		var span = document.getElementsByClassName("close")[0];

		// Get the image and insert it inside the modal - use its "alt" text as a caption
		var img = document.getElementById("myImg"+obj);

		var modalImg = document.getElementById("img01");
		var captionText = document.getElementById("caption");

		img.onclick = function(){
		  modal.style.display = "block";
		  modalImg.src = this.src;
		  //captionText.innerHTML = this.alt;
		}

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() { 
		  modal.style.display = "none";
		}
		}
		
function setTimeLoadForTable(){

	document.getElementById('first_name').onkeypress = function() {
				 return onlyAlphabetsStringSpace(this,event);
	};
	document.querySelectorAll('.imgcsp').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('imghid'+val).value;
//				if (confirm('Are You Sure You Want to View Detail?')) {
				imageView(gdid);
//				} else {
//					return false;
//				}
		})
	});
		}
	</script>