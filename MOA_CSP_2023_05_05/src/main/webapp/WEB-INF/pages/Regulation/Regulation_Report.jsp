<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/commonJS/addmorefunctionality.js"></script>
<!-- <script src="js/common/multicheck.js"></script> -->
<!-- <link rel="stylesheet" href="js/common/multicheck.css"> -->
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- datatable style and js end-->
<!-- <link rel="stylesheet" href="admin/assets/db_css/db_custom_style.css"> -->
<script nonce="${cspNonce}" type="text/javascript">
	var username = "${username}";
</script>
<section class="dashboard-page regulation_report">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Regulation Report</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Regulation Report</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Regulation Report</h6>
							<div class="row">

								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="input-style-1">
										<label> Name <strong class="mandatory"></strong></label> <input
											id="first_name" name="first_name" class="form-control"
											autocomplete="off" maxlength="25" placeholder="Full Name">
										<input type="hidden" id="id" name="id" class="form-control"
											value="0" autocomplete="off">
									</div>
								</div>

								<div class="col-lg-4 col-md-6 col-sm-12 col-12">
									<div class="input-style-2">
										<label>Date Of Birth </label> <input type="text" name="dob"
											id="dob" maxlength="10"
											class="form-control-sm form-control effect-9 "
											aria-required="true" autocomplete="off" value="DD/MM/YYYY">
									</div>
								</div>
<!-- --04-03-2023 -->
								<div class="col-12 col-sm-12 col-md-6 col-lg-4 d-none">
									<div class="select-style-1">
										<label for="fname">Gender <strong class="mandatory">*
										</strong></label> <select name="gender" id="gender" class="singleselect form-control form-control-lg ">
											<!-- style="text-transform: uppercase" -->
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getgenderList}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
<!-- --04-03-2023 remove only single select -->
								<div class="col-lg-4 col-md-6 col-sm-12" id="st">
									<div class="select-style-1">
										<label>Registration State<strong class="mandatory">
										</strong></label>
										<div class="select-position">
											<select name=per_state id="per_state"
												class="form-control form-control-lg">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${MedStateName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="select-style-1">
										<label> University Name <strong class="mandatory">
										</strong>
										</label>
										<div class="select-position">
											<select name="institute_name" id="institute_name"
												class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getInstituteList}"
													varStatus="num">
													<option value="${item.id}" name="${item.id}">${item.university_name}</option>
												</c:forEach>
											</select>
										</div>

									</div>
								</div>


								<div class="col-lg-4 col-md-6 col-sm-12 custom-d-none">
									<div class="select-style-1">
										<label> Status <strong class="mandatory"> </strong>
										</label> <input type="hidden" id="id" name="id" class="form-control"
											value="0" autocomplete="off">
										<div class="select-position">
											<select name="institute_status" id="institute_status"
												class="singleselect form-control form-control-lg">
												<option value="0">Pending</option>
												<option value="1">Approved</option>
												<option value="2">Reverted</option>
											</select>
										</div>
									</div>
								</div>





								<!-- 			<div class="col-lg-6 col-md-6 col-sm-12"> -->
								<!--                   <div class="input-style-2"> -->
								<!--                   <label>State <strong class="mandatory"> </strong></label> -->
								<!--                    <div class="select-style-2"> -->
								<!-- 					<div class="select-position"> -->

								<!-- 							<select name="per_state" id="per_state" -->
								<!-- 								class="form-control autocomplete"> -->
								<!-- 								<option value="0">--Select--</option> -->
								<%-- 								<c:forEach var="item" items="${MedStateName}" --%>
								<%-- 									varStatus="num"> --%>
								<%-- 									<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
								<%-- 								</c:forEach> --%>
								<!-- 							</select> -->
								<!-- 						</div> -->
								<!-- 					</div> -->
								<!-- 		          </div> -->
								<!--                </div> -->
								<div class="col-lg-4 col-md-6 col-sm-12 ">
									<div class="input-style-1">
										<input type="hidden" id="id" name="id" class="form-control"
											value="0" autocomplete="off"> <label>NRH No.
											<strong class="mandatory"> </strong>
										</label> <input id="nrh_en_no" name="nrh_en_no" class="form-control"
											autocomplete="off" maxlength="25"
											placeholder="Maximum 25 Character">
									</div>
								</div>

								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="input-style-1">
										<label>State Registration No. <strong
											class="mandatory"> </strong></label> <input id="state_reg_no"
											name="state_reg_no" class="form-control" autocomplete="off"
											maxlength="25" placeholder="Maximum 25 Character">
									</div>
								</div>


								<div class="col-lg-4 col-md-6 col-sm-12 d-none">
									<div class="input-style-2">
										<label>From Date <strong class="mandatory"> </strong></label>
										<input type="text" name="from_date" id="from_date"
											maxlength="10" class="form-control-sm form-control effect-9 "
											aria-required="true" autocomplete="off" value="DD/MM/YYYY">
									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-12 d-none">
									<div class="input-style-2">
										<label>To Date <strong class="mandatory"> </strong></label> <input
											type="text" name="to_date" id="to_date" maxlength="10"
											class="form-control-sm form-control effect-9 "
											aria-required="true" autocomplete="off" value="DD/MM/YYYY">
									</div>
								</div>


								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="select-style-1">
										<label> Status <strong class="mandatory"> </strong>
										</label>
										<div class="select-position">
											<select name="status" id="status"
												class="singleselect form-control form-control-lg">
												<option value="1">Active</option>
												<option value="5">Inactive</option>
												<option value="4">Suspended</option>
											</select>
										</div>

									</div>
								</div>






								<div class="col-lg-4 col-md-6 col-sm-12 custom-d-none">
									<div class="input-style-1">
										<input type="hidden" id="id" name="id" class="form-control"
											value="0" autocomplete="off"> <label>Status <strong
											class="mandatory"> </strong></label>
										<div class="select-style-1">
											<div class="select-position">
												<select name="status" id="status"
													class="singleselect form-control form-control-lg">
													<option value="0">Pending</option>
													<option value="1">Approve</option>
													<option value="2">Reverted</option>
												</select>
											</div>
										</div>
									</div>
								</div>


								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="select-style-1">
										<label> Type <strong class="mandatory"> </strong>
										</label>
										<div class="select-position">
											<select name="type_status" id="type_status"
												class="singleselect form-control form-control-lg">
												<option value="NEW">Upadation</option>
												<option value="RENEW">Revision</option>
											</select>
										</div>
									</div>
								</div>
</div>

							</div>

							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li>
												<input type="button"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												id="btn-reload" value="Search">
											</li>
											<li><a
												class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon btnpdf"
												id="pdfex"><i class="bi bi-file-pdf" id="printId"
													value="PDF" title="Export to PDF"></i> PDF </a></li>
													<li><a class="main-btn success-btn btn-hover btn-iconic-icon btnexcel" id="btnExport"><i class="bi bi-file-earmark-excel" value="PDF" title="Export to PDF"></i> EXCEL </a></li>
													<li><a href="Regulation_Report_Url"
												class="main-btn dark-btn btn-hover btn-clear" value="Reset">Reset</a>
											</li>
										</ul>
									</div>
								</div>
							</div>

						
					</div>
					<!-- end card -->
				</div>
			</div>


	<section class="single-detail-block">
			<div class="tables-wrapper">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">

						<div class="card-style mb-30">
							<div class="row">
								<div class="col-12 col-lg-12 com-md-12 col-sm-12">
									<div class="dropdown custom-col-filter">
										<button class="main-btn dark-btn-outline dropdown-toggle"
											type="button" id="droupCheck" data-bs-toggle="dropdown"
											aria-expanded="false">
											<i class="bi bi-funnel anchor"></i>Column Filter
										</button>
										<ul class="dropdown-menu"
											aria-labelledby="dropdownMenuButton1">
											<li>
												<div class="form-check checkbox-style">
													<input class="form-check-input toggle-vis2" type="checkbox"
														value="" id="checkbox-1" data-column="11"> <label
														class="form-check-label" for="checkbox-1">Ayush Id
														/Abha No</label>
												</div>
											</li>
											<li>
												<div class="form-check checkbox-style">
													<input class="form-check-input toggle-vis2" type="checkbox"
														value="" id="checkbox-1" data-column="12"> <label
														class="form-check-label" for="checkbox-1">Nrh No</label>
												</div>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="table-wrapper table-responsive custom-datatable-p">
								<table id="Search_regulation_Master" class="table">
									<thead>
										<tr>
											<th><h6>Sr No.</h6></th>

											<th><h6>Name of the professional with recent
													photograph</h6></th>
											<th><h6>Father's name</h6></th>
											<th><h6>Present correspondence address</h6></th>
											<th><h6>Permanent address</h6></th>
											<th><h6>Email address with mobile no.</h6></th>
											<th><h6>Date of birth and nationality</h6></th>
											<th><h6>Name of medical degree or diploma obtained
													and university</h6></th>
											<th><h6>Registration number,date,state</h6></th>
											<th><h6>Name of hospital or institute with complete
													address for purposes of teaching or research or practice of
													medicine</h6></th>
											<th><h6>Name of person in institution or hospital
													who will be responsible for legal issues regarding patient
													care provided by doctor concerned</h6></th>
											<th><h6>Ayush ID/ABHA No.</h6></th>
											<th id="2"><h6>NRH enrollment no.</h6></th>


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
<!-- start new pdf -->
<c:url value="Regulation_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search1" name="search1">
<!-- --04-03-2023 -->
	<input type="hidden" name="Search4" id="Search4" value="0" />
	<input type="hidden" name="nrh_en_no1" id="nrh_en_no1" value="0" />
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="first_name1" id="first_name1" value="0" />
	<input type="hidden" name="status1" id="status1" value="0" />
	<input type="hidden" name="per_state1" id="per_state1" value="0" />
	<input type="hidden" name="from_date1" id="from_date1" value="0" />
	<input type="hidden" name="to_date1" id="to_date1" value="0" />
	<input type="hidden" name="gender1" id="gender1" value="0" />
	<input type="hidden" name="state_reg_no1" id="state_reg_no1" value="0" />
	<input type="hidden" name="dob1" id="dob1" value="0" />
	<input type="hidden" name="institute_name1" id="institute_name1" value="0" />
	<input type="hidden" name="type_status1" id="type_status1" value="0" />
	<!--         <input type="hidden" name="status123" id="status123" value="0"/> -->
</form:form>
<!-- end -->

<c:url value="Regulation_Report_Excel" var="searchUrl1" />
<form:form action="${searchUrl1}" method="post" id="search3"
	name="search3" modelAttribute="comd1">
	
	<input type="hidden" name="Search2" id="Search2" value="0" />
	<input type="hidden" name="nrh_en_no2" id="nrh_en_no2" value="0" />
	<input type="hidden" name="first_name2" id="first_name2" value="0" />
	<input type="hidden" name="status2" id="status2" value="0" />
	<input type="hidden" name="per_state2" id="per_state2" value="0" />
	<input type="hidden" name="from_date2" id="from_date2" value="0" />
	<input type="hidden" name="to_date2" id="to_date2" value="0" />
	<input type="hidden" name="gender2" id="gender2" value="0" />
	<input type="hidden" name="state_reg_no2" id="state_reg_no2" value="0" />
	<input type="hidden" name="dob2" id="dob2" value="0" />
	<input type="hidden" name="institute_name2" id="institute_name2" value="0" />
	<input type="hidden" name="type_status2" id="type_status2" value="0" />
			
</form:form>
<script nonce="${cspNonce}" type="text/javascript">
function frm_toFn() {
	
	var from_date = $("#from_date").val();
	var to_date = $("#to_date").val();
 //  alert("from_date---"+from_date)
// 	alert("to---"+to_date)
 	if(from_date != "DD/MM/YYYY" && to_date !="DD/MM/YYYY"){
		
		if(from_date > to_date ){
			alert("To Date can not be less than From Date.")
			return false ;
		}
		
	}
	 
}
	$(document).ready(function() {
		if('${role}'=="State_Council_NCH"){ 
			var st_id = ${state_id};
			 if( st_id != null && st_id != "0" && st_id != ""){
				 $("#per_state").val(st_id); 
				  $("#per_state").attr("disabled","disabled");
			 }
		}
	/* 	
		if('${role}'=="NCH"){
			 debugger;
			 alert('${role}'=="NCH" );
			 var st_id = 182;
				alert(st_id);
				 if( st_id != null && st_id != "0" && st_id != ""){
					 $("#per_state").val(st_id); 
					  $("#per_state").attr("disabled","disabled");
		 }
	} */
		 $('.toggle-vis2').on('click', function (e) {
		        var column = table.column($(this).attr('data-column'));
		        column.visible(!column.visible());
			  
		    });
		 

		 document.getElementById('btnExport').onclick = function() {
		 				Regulation_Report_Excel();
		 			};
		    
  var checkList = document.getElementById('droupCheck');
checkList.getElementsByClassName('anchor')[0].onclick = function(evt) {
if (checkList.classList.contains('visible'))
 checkList.classList.remove('visible');
else
 checkList.classList.add('visible');
}
		//$("#st_id").val('${state_id}');
		 var institute_id = ${institute_id}; 
		 if( institute_id != null && institute_id != "0" && institute_id != ""){
			 $("#institute_name").val(institute_id); 
			  $("#institute_name").attr("disabled","disabled");
			  $("#st").hide();
 		 }
		datepicketDate('from_date');
		datepicketDate('to_date');
		datepicketDate('dob');
		
		mockjax1('Search_regulation_Master');
		table = dataTable('Search_regulation_Master');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
	});
 
	function data(Search_regulation_Master) {
		jsondata = [];
		var table = $('#' + Search_regulation_Master).DataTable();
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
		
		var state_reg_no = $("#state_reg_no").val();
		var nrh_en_no = $("#nrh_en_no").val();
		var first_name = $("#first_name").val();
		var gender = $("#gender").val();
		var photo_path = $("#photo_path").val();
		var father_name = $("#father_name").val();
		var pre_address = $("#pre_address").val();
		var pre_district = $("#pre_district").val();
		var pre_state = $("#pre_state").val();
		var pre_pincode = $("#pre_pincode").val();
		var per_address = $("#per_address").val();
		var per_district = $("#per_district").val();
		var per_state = $("#per_state").val();
		var per_pincode = $("#per_pincode").val();
		var mo_no = $("#mo_no").val();
		var alt_mo_no = $("#alt_mo_no").val();
		var email_id = $("#email_id").val();
		var dob = $("#dob").val(); 
		 
		var nationality = $("#nationality").val();
 
		var date_of_reg = $("#date_of_reg").val();
		var name_reg = $("#name_reg").val();
		var name_of_institute = $("#name_of_institute").val();
 
		var status = $("#status").val();
		 var institute_name = $("#institute_name").val();
		var from_date = $("#from_date").val();
		var to_date = $("#to_date").val();
		var type_status = $("#type_status").val(); 
	 	
		$.post("getFilter_Reg_Report_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			nrh_en_no:nrh_en_no,
			first_name:first_name,
			per_state:per_state,
			status:status,
			from_date:from_date,
			to_date:to_date ,first_name :first_name ,gender : gender ,state_reg_no:state_reg_no ,dob :dob ,institute_name :institute_name ,
			type_status :type_status
			
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				
			 var registration_for_type ="";
			registration_for_type = j[i].registration_for_type;
			if(j[i].registration_for_type == "0"){
				j[i].registration_for_type="Renewable"
			}
			if(j[i].registration_for_type == "1"){
				j[i].registration_for_type="Permanent"
			}
			var ayus = j[i].ayush_id ;
			var  ayu_abha;
			var abha  ;
			if( abha == "null" || abha == null || abha == " " || abha == "0"){
				abha ="";
				    ayu_abha =  ('Ayush Id: '+ayus+ '</br>' +'  '+abha+'</br>' );
			}
			else{
				  abha = j[i].abha_no ;
				  ayu_abha =  ('Ayush Id: '+ayus+ '</br>' +'ABHA No. : '+abha+'</br>' );
			}
				
					    jsondata.push([ j[i].ser,
						 j[i].img, 
						j[i].father_name,
						(j[i].pre_corre_add),
						( j[i].permanent_add) , 
			 			(  j[i].email_id + '</br>' + j[i].mo_no + '</br>' ),
			 			(j[i].dob_nationality ) , 
			 			(j[i].degree_my_inst),
			 			(j[i].no_date_name_renew),
			 			(j[i].hos_name_addr),
			 			j[i].name_of_res_p, (ayu_abha ), j[i].nrh_en_no,
			 			j[i].chekboxaction ]);
			}
		});
		
		$.post("getTotalEdu_Reg_Report_dataCount?" + key + "=" + value, {
			nrh_en_no:nrh_en_no,
			first_name:first_name,
			per_state:per_state,
			from_date:from_date,
			to_date:to_date,
			status:status  ,gender:gender ,state_reg_no:state_reg_no ,dob:dob ,institute_name:institute_name ,type_status :type_status
		}, function(j) {
			FilteredRecords = j;
			});
		setTimeout(setTimeLoadForTable, 1000);
	}
	//start pdf
	
	function downloadnote_file(id,fildname) {
	
	var pdfView="kmlFileDownload4441?kmlFileId455="+id+"&fildname1="+fildname+"";
    fileName="TopicContent";
    fileURL=pdfView;
    if (!window.ActiveXObject) {
        var save = document.createElement('a');
        save.href = fileURL;
        save.target = '_blank';
        var filename = fileURL.substring(fileURL.lastIndexOf('/')+1);
        save.download = fileName || filename;
	       if ( navigator.userAgent.toLowerCase().match(/(ipad|iphone|safari)/) && navigator.userAgent.search("Chrome") < 0) {
				document.location = save.href; 
			}else{
		        var evt = new MouseEvent('click', {
		            'view': window,
		            'bubbles': true,
		            'cancelable': false
		        });
		        save.dispatchEvent(evt);
		        (window.URL || window.webkitURL).revokeObjectURL(save.href);
			}	
    }
    else if ( !! window.ActiveXObject && document.execCommand)  {
        var _window = window.open(fileURL, '_blank');
        _window.document.close();
        _window.document.execCommand('SaveAs', true, fileName || fileURL)
        _window.close();
    }
}
	//start new pdf
	
	function getPDFExecl3(pdf_excel){	
		debugger;
 	document.getElementById('typeReport').value=pdf_excel;
// 	var status = $("#status").val();
//	document.getElementById('status123').value=status;	
	
	
// 	--04-03-2023
	$("#Search4").val($("#Search").val());
	$("#nrh_en_no1").val($("#nrh_en_no").val());
	$("#first_name1").val($("#first_name").val());
	$("#status1").val($("#status").val());
	
	$("#per_state1").val($("#per_state").val());
	
	$("#from_date1").val($("#from_date").val());
	$("#to_date1").val($("#to_date").val());
	$("#gender1").val($("#gender").val());
	
	$("#state_reg_no1").val($("#state_reg_no").val());
	$("#dob1").val($("#dob").val());
	$("#institute_name1").val($("#institute_name").val());
	$("#type_status1").val($("#type_status").val());
	document.getElementById('search1').submit();
}
	//end
		function dateChange(){
		var from_date = $("#from_date").val();
		var to_date = $("#to_date").val(from_date);
	}
	

function setTimeLoadForTable(){
			
// 			document.getElementById('nrh_en_no').onkeypress = function () {
// 				return onlyAlphabetsStringSpace(this,event);
// 			};

	
			document.getElementById('dob').onclick = function() {
				return clickclear(this, 'DD/MM/YYYY');
			};
			document.getElementById('dob').onfocus = function() {
				return this.style.color='#000000';
			};
			document.getElementById('dob').onblur = function() {
				return clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
			};
			document.getElementById('dob').onkeyup = function() {
				return clickclear(this, 'DD/MM/YYYY');
			};
			document.getElementById('dob').onchange = function() {
				return clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);
			};
			document.getElementById('dob').onchange = function() {
				return clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);
			};
		
			document.getElementById('from_date').onclick = function() {
				return clickclear(this, 'DD/MM/YYYY');
			};
			document.getElementById('from_date').onfocus = function() {
				return this.style.color='#000000';
			};
			document.getElementById('from_date').onblur = function() {
				return clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
			};
			document.getElementById('from_date').onkeyup = function() {
				return clickclear(this, 'DD/MM/YYYY');
			};
			document.getElementById('from_date').onchange = function() {
				return clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);
			};
			document.getElementById('from_date').onchange = function() {
				return clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);
			};
			document.getElementById('to_date').onclick = function() {
				return clickclear(this, 'DD/MM/YYYY');
			};
			document.getElementById('to_date').onfocus = function() {
				return this.style.color='#000000';
			};
			document.getElementById('to_date').onblur = function() {
				return clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
			};
			document.getElementById('to_date').onkeyup = function() {
				return clickclear(this, 'DD/MM/YYYY');
			};
			document.getElementById('to_date').onchange = function() {
				return clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);
			};
			document.getElementById('to_date').onchange = function() {
				return clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);
			};
			document.getElementById('pdfex').onclick = function() {
				getPDFExecl('pdfL');
			};
			
			document.getElementById('pdfex').onclick = function() {
				getPDFExecl3('pdfL');
			};
			
			document.getElementById('from_date').onchange = function() {
				return  frm_toFn();
			};
			document.getElementById('to_date').onchange = function() {
				return  frm_toFn();
			};

			
			
		}
		
function Regulation_Report_Excel() {
	debugger;
	$("#Search2").val($("#Search").val());
	$("#nrh_en_no2").val($("#nrh_en_no").val());
	$("#first_name2").val($("#first_name").val());
	$("#status2").val($("#status").val());
	
	$("#per_state2").val($("#per_state").val());
	
	$("#from_date2").val($("#from_date").val());
	$("#to_date2").val($("#to_date").val());
	$("#gender2").val($("#gender").val());
	
	$("#state_reg_no2").val($("#state_reg_no").val());
	$("#dob2").val($("#dob").val());
	$("#institute_name2").val($("#institute_name").val());
	$("#type_status2").val($("#type_status").val());


document.getElementById('search3').submit();

}
</script>