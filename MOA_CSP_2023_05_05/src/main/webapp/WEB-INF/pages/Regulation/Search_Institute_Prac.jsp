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
<!-- datatable style and js end-->
<link rel="stylesheet" href="assets/db_css/db_custom_style.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- <link rel="stylesheet" href="assets/vendor/internal_css.css"> -->
<!-- <script> -->
<%-- 	var username = "${username}"; --%>
<!-- </script> -->


<section class="dashboard-page search_regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>University Search For Practitioner</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									University Search For Practitioner</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->

		<div class="form-elements-wrapper search-regulation-wrapper">
			<div class="row">

				<div class="col-12 col-lg-12 com-md-12 col-sm-12">
					<!-- input style start -->
					<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">University Search For Practitioner</h6>
							<div class="row">

								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="input-style-1">
										<label>Full Name <strong class="mandatory"> </strong></label>
										<input id="first_name" name="first_name"
											class="for  </div>m-control" autocomplete="off"
											maxlength="25" placeholder="Full Name"> <input
											type="hidden" id="id" name="id" class="form-control"
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

								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="select-style-1">
										<label>Gender <strong class="mandatory"> </strong></label>
										<div class="select-position">
											<select name="gender" id="gender"
												class="singleselect form-control form-control-lg">
												<option value=" ">--Select--</option>
												<option value="1">Male</option>
												<option value="2">Female</option>
												<option value="3">Other</option>
											</select>

										</div>
									</div>
								</div>

							</div>


							<div class="row">
								<div class="col-lg-4 col-md-6 col-sm-12 custom-d-none">
									<div class="input-style-2">
										<label>Date Of First Registration<strong
											class="mandatory"> </strong></label> <input type="date"
											id="date_of_reg" name="date_of_reg" class="form-control"
											autocomplete="off" maxlength="25"
											placeholder="Maximum 25 Character"> <span
											class="icon"><i class="bi bi-calendar"></i></span>
									</div>
								</div>

								<div class="col-lg-4 col-md-6 col-sm-12 custom-d-none">
									<!--                  style="display: block; -->
									<div class="select-style-1">
										<label>Registration State<strong class="mandatory">
										</strong></label>
										<div class="select-position">
											<select name="registration_state" id="registration_state"
												class="singleselect form-control form-control-lg">
												<!--   onchange="getDistrictper();" -->
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
								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="select-style-1">
										<label for="text-input">Role Type <span
											class="mandatory">*</span></label>
										<div class="select-position">
											<!-- 										<select name="role_type" id="role_type" > -->
											<!-- 								        	<option value="0">--Select--</option> -->
											<%-- 											<c:forEach var="item" items="${getRoleNameList}" varStatus="num" > --%>
											<%-- 		             								<option value="${item.roleId}">${item.role}</option> --%>
											<%-- 		             							</c:forEach> --%>
											<!-- 										</select> -->
											<select name="role_type" id="role_type"
												class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
												<option value="25">Students</option>
												<option value="35">Interns</option>
												<option value="27">Practitioners</option>

											</select>
										</div>
										<input type="hidden" id="inst_id">
									</div>
								</div>
							</div>
						</div>

						<!-- Bottom Button Start -->
						<div class="btn-bottom">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12">
									<ul class="buttons-group mainbtn">
										<li><a
											class="main-btn secondary-btn btn-hover btn-iconic-icon btn-search"
											id="btn-reload" value="Search"><i
												class="lni lni-search-alt"></i>Search</a></li>
										<li><a href="edu_search_reg_url"
											class="main-btn dark-btn n btn-hover btn-clear" value="Reset">Reset</a>
										</li>

										<li id="apprvbt" class="custom-d-none"><input
											type="button" class="main-btn success-btn  btn-hover"
											value="Approve"></li>
										<li id="rejbt" class="custom-d-none"><input type="button"
											class="main-btn danger-btn btn-hover " value="Revert Back">
										</li>

									</ul>
								</div>
							</div>
						</div>
						<!-- Bottom Button End -->
					</div>




					<!-- end card -->

				</div>

			</div>


			<div class="modal fade custom-modal bd-example-modal-lg"
				tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
				aria-hidden="true" id="modelWindow">
				<div class="modal-dialog modal-xl modal-dialog-scrollable">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title" id="reject_remarks">Enter Remarks</h3>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>

						<div class="modal-body custom-modal-table">
							<div class="custom-modal-inner">
								<div class="table-wrapper table-responsive custom-table">
									<table class="table model-table" id="addAttDoc">
										<thead>
											<tr>
												<th>Sr No.</th>
												<th>Name</th>
												<th>Remarks</th>
											</tr>
										</thead>
									</table>
									<div id="dynamicDataTable"></div>
								</div>
							</div>

						</div>
						<div class="modal-footer">
							<ul class="buttons-group">
								<li>
									<button type="button" class="main-btn dark-btn n btn-hover"
										data-bs-dismiss="modal" data-dismiss="modal"
										aria-label="Close" id="ok">Submit</button>
								</li>
							</ul>
						</div>

					</div>
				</div>
			</div>


			<!-- 		    <div class="container-fluid"> -->
			<!-- 			 <div class="card-style mb-30 selectsection" id="checkheaddiv"> -->
			<!-- 			    <input  type="hidden" id="CheckVal" name="CheckVal"> -->
			<!-- 			    <input class="form-check-input" type=checkbox id='nSelAll' name='tregn'> <label class="form-check-label">Select all [<span id="tregn">0</span><span id='nTotRow1'>/</span><span id="tregnn"  >  </span></label>] -->
			<!-- 			 </div>  -->
			<!-- 			 </div> -->
			<section class="single-detail-block">
				<div class="tables-wrapper">
					<div class="row">
						<div class="col-12 col-lg-12 com-md-12 col-sm-12">
							<div class="card-style mb-30">
								<div class="table-wrapper table-responsive custom-datatable-p">
									<table id="Search_regulation_Master" class="table">
										<thead>
											<tr>
												<th><h6>Sr No.</h6></th>
												<!-- 				 <th id="apprvchk"><h6>Select</h6></th> -->
												<th><h6>View & verify</h6></th>
												<th><h6>Ayush ID/ABHA No.</h6></th>
												<th><h6>Full name</h6></th>
												<th><h6>Gender</h6></th>
												<th><h6>Photo</h6></th>
												<th><h6>Father name</h6></th>
												<th><h6>Present district</h6></th>
												<th><h6>Present state</h6></th>
												<th><h6>Present pincode</h6></th>
												<th><h6>Permanent district</h6></th>
												<th><h6>Permanent state</h6></th>
												<th><h6>Permanent pincode</h6></th>
												<th><h6>Email ID</h6></th>
												<th><h6>Date of birth</h6></th>
												<th><h6>Nationality</h6></th>
												<!-- 				 <th><h6>Reject Remarks</h6></th> -->
												<!-- 				 <th><h6>Name Of Medical Degree</h6></th> -->
												<!-- 				 <th><h6>Name of University</h6></th> -->
												<th><h6>View details</h6></th>
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


<c:url value="uni_DegreePopupUrl" var="uni_DegreePopupUrl" />
<form:form action="${uni_DegreePopupUrl}" method="post"
	id="degreepopup_Form" name="degreepopup_Form" modelAttribute="id"
	target="result">
	<input type="hidden" name="popid" id="popid" value="0" />
	<input type="hidden" name="name_of_institute" id="name_of_institute"
		value="0" />

</form:form>

<c:url value="IOCHPopupUrl" var="IOCHPopupUrl" />
<form:form action="${IOCHPopupUrl}" method="post" id="IOCHpopup_Form"
	name="IOCHpopup_Form" modelAttribute="popidIOCH" target="result">
	<input type="hidden" name="popidIOCH" id="popidIOCH" value="0" />
</form:form>


<!-- //janki -->
<c:url value="pract_University_previewUrl"
	var="pract_University_previewUrl" />
<form:form action="${pract_University_previewUrl}" method="post"
	id="preview_uni_Form" name="preview_uni_Form" modelAttribute="u_id">
	<input type="hidden" name="u_id" id="u_id" value="0" />
</form:form>











<c:url value="Edit_edu_reg_mstrUrl" var="Edit_edu_reg_mstrUrl" />
<form:form action="${Edit_edu_reg_mstrUrl}" method="post"
	id="updateForm" name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>


<c:url value="delete_edu_reg_mstr_Url" var="delete_edu_reg_mstr_Url" />
<form:form action="${delete_edu_reg_mstr_Url}" method="post"
	id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>
<c:url value="Excel_Auth_Posted_query" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm"
	name="ExcelForm" modelAttribute="cont_comd_ex">
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
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<c:url value="getView" var="getView" />
<form:form action="${getView}" method="post" id="search2" name="search2"
	modelAttribute="id7">
	<input type="hidden" name="id7" id="id7" value="0" />
	<input type="hidden" name="typeReport7" id="typeReport7" value="0" />
	<input type="hidden" name="reportname8" id="reportname8" value="0" />
</form:form>

<c:url value="getCertificatePDF" var="getCertificatePDF" />
<form:form action="${getCertificatePDF}" method="post" id="search1"
	name="search1" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3" value="0" />
	<input type="hidden" name="typeReport2" id="typeReport2" value="0" />
	<input type="hidden" name="reportname1" id="reportname1" value="0" />
</form:form>


<!-------------- passport-size-photo modal ------------------->
<div class="modal image-modal pass-photo " id="myModal">
	<div class="modal-dialog modal-sm modal-dialog-centered">
		<div class="modal-content">
			<!-- Modal Header -->
			<span class="close">&times;</span>
			<!-- Modal body -->
			<div class="modal-body">
				<div class="modal-img">
					<img id="img01">
					<div id="caption"></div>
				</div>
			</div>
		</div>
	</div>
</div>



<script nonce="${cspNonce}" type="text/javascript">
 	
	$(document).ready(function() {
		 
 		datepicketDate('dob');;
		 
		 $("#inst_id").val('${institute_id}');
		mockjax1('Search_regulation_Master');
		table = dataTable('Search_regulation_Master');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
			
			
			setTimeout(findselected, 1000);
			var institute_status = $("#institute_status").val();
// 			if(institute_status == "1" || institute_status == "2"){
// 				 removecol();	
// 			}
// 			if(institute_status == "0"){
// 				addcol();
// 			}
			 
		});
		$.ajaxSetup({
			async : false
		});
		$.ajaxSetup({
			async : false
		});
		
	});
	
	
	function statusChange(){
		 var institute_status = $("#institute_status").val();
// 		 if(institute_status=='0'){
// 			 $("#rejbt").show();
// 			 $("#apprvbt").show();
//  			 checkCKBT();
// 			  addcol();
// 		 }
// 		 else if(institute_status=='1' || institute_status=='2'){
// 			 $("#rejbt").hide();
// 			 $("#apprvbt").hide();
// 		 }
	}

	function removecol() {	
    var tble = document.getElementById('Search_regulation_Master'); 
    var row = tble.rows;  
    var i = 1; 
    for (var x = 0; x < row.length; x++) {
        row[x].deleteCell(i);
       
        
    }
}
	
	function addcol() {
	   var tble = document.getElementById('Search_regulation_Master');
		tble.clear();
	   var row = tble.rows;  
	   var i = 1; 
	   for (var x = 0; x < row.length; x++) {
	       row[x].insertCell(i);
	   }
	}
	function setTimeLoadForTable(){
		
		document.getElementById('first_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this,event);
		};

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
			return clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);calculate_age('dob');
		};
		document.getElementById('date_of_reg').onkeyup = function() {
			return  onlyAlphabetsStringSpace(this,event);
		};
		document.getElementById('institute_status').onchange = function() {
			   statusChange();
		};
		
// 		document.getElementById('nSelAll').onclick = function() {
// 			setselectall();
// 		};
		
		document.getElementById('apprvbt').onclick = function() {
			return setApproveStatus();
		};
		
		document.getElementById('ok').onclick = function() {
			
			return setRejectStatus();
		};

		
		document.getElementById('rejbt').onclick = function() {
			
			  $('#modelWindow').modal('show');
			   
			  findselected();
				var a = $("#CheckVal").val();
			 
				if(a == ""){
					alert("Please Select the Data for Revert Back"); 
				}else{
	   
					 dynamicTable(a);

				}
 
		};
		
		
	document.querySelectorAll('.instview').forEach((items, index) => {
			
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
 				var cid = document.getElementById('inst1'+val).value;
				var cname = document.getElementById('inst2'+val).value;
				var cnum = document.getElementById('inst3'+val).value;
 				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					EditData(cid,cname,cnum);
				} else {
					return false;
				}
			})
		});
	
	document.querySelectorAll('.practview_degree').forEach((items, index) => {
		
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;

			var cid = document.getElementById('prac_degree'+val).value;
			var name_of_institute = document.getElementById('name_of_institute'+val).value;
 			if (confirm('Are You Sure You Want to View Detail ?')) {
				Pop_Up_Degree(cid,name_of_institute);
			} else {
				return false;
			}
		})
	});
		
  document.querySelectorAll('.pract_hos').forEach((items, index) => {
		
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;

			var cid = document.getElementById('prac_hoss'+val).value;
				
			if (confirm('Are You Sure You Want to View Detail ?')) {
				Pop_Up_IOCH(cid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.popdegreep').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('popdegreepId'+val).value;
			if (confirm('Are You Sure You Want to View Detail?')) {
				Pop_Up_DegreeP(gdid);
			} else {
				return false;
			}
		})
	});	
	document.querySelectorAll('.degreepdf').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('degreepdfId'+val).value;
			if (confirm('Are You Sure You Want to download File ?')) {
				getPDF(gdid);
			} else {
				return false;
			}
		})
	});
	
	
	
	
	
	
	
	
	//janki
	
	document.querySelectorAll('.p_a_u').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
 			var gdid = document.getElementById('p_a_uId'+val).value;
			if (confirm('Are You Sure You Want to View and Verify Detail?')) {
				fn_pre_app_uni(gdid);
			} else {
				return false;
			}
		})
	});
	
}
	
	
	

	
	
	
	
	
	
	
	
	function data(Search_regulation_Master) {
 		jsondata = [];
		var table = $('#' + Search_regulation_Master).DataTable();
		var info = table.page.info();
 		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
 		var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
		var orderType = order[0][1];
 		 var first_name = $("#first_name").val();
		 var gender = $("#gender").val();
		 var institute_status = $("#institute_status").val();
 		 var registration_state = $("#registration_state").val();
		 var dob = $("#dob").val();
		 var date_of_reg = $("#date_of_reg").val();
		 var institute_name = $("#inst_id").val();
		 var role_type = $("#role_type").val();
		$.post("getFilter_Edu_Reg_master_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
 			 first_name:first_name,
			 institute_status:institute_status,
			 gender:gender,
 			 registration_state:registration_state,
			 dob:dob,
			 date_of_reg:date_of_reg,
			 institute_name:institute_name,role_type:role_type
			 
		}, function(j) {
		
			for (var i = 0; i < j.length; i++) {
			
			$("#tregnn").text(" "+j.length);	
			var gender ="";
			  gender = j[i].gender;
			if(gender == "1"){
				gender="Male"
			}
			if(gender == "2"){
				gender="Female"
			}
			if(gender == "3"){
				gender="Other"
			}
			var registration_for_type ="";
			registration_for_type = j[i].registration_for_type;
			if(registration_for_type == "0"){
				registration_for_type="Renewable"
			}
			if(registration_for_type == "1"){
				registration_for_type="Permanent"
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
		 
			 var institute_status = $("#institute_status").val();
			  
			 jsondata.push([ j[i].ser  ,j[i].p_a_u ,(ayu_abha ),j[i].first_name, gender, j[i].img, j[i].father_name
					 ,j[i].pre_district, j[i].pre_state, j[i].pre_pincode,
					j[i].per_district, j[i].per_state, j[i].per_pincode ,
					j[i].email_id , j[i].dob, j[i].nationality  , j[i].vm1   
			 
					 ]);
			 
			 
// 				 if(institute_status=='0'){
// 					 $("#apprvchk").show();
//  					 $("#checkheaddiv").show();
//  					 jsondata.push([ j[i].ser ,j[i].chekboxaction,(ayu_abha ),j[i].first_name, gender, j[i].img, j[i].father_name,
// 					 j[i].pre_district, j[i].pre_state, j[i].pre_pincode,  
// 					 j[i].per_district, j[i].per_state, j[i].per_pincode,
// 					 j[i].email_id , j[i].dob, j[i].nationality, j[i].reject_remarks,j[i].vd, j[i].vioch
// 					, j[i].vm1 + "<br>"
// 						  ]);
// 				 }
// 				 else if(institute_status=='1' || institute_status=='2'){
//  					 $("#apprvchk").hide();
//  					 $("#checkheaddiv").hide();
// 					 jsondata.push([ j[i].ser ,j[i].chekboxaction,(ayu_abha ),j[i].first_name, gender, j[i].img, j[i].father_name
// 							 ,j[i].pre_district, j[i].pre_state, j[i].pre_pincode,
// 							j[i].per_district, j[i].per_state, j[i].per_pincode ,
// 							j[i].email_id , j[i].dob, j[i].nationality, j[i].reject_remarks,j[i].vd, j[i].vioch
// 						, j[i].vm1   
// 								  ]);
// 				 }
			
			
			
			
			}


		});
		
		
		$.post("getTotalEdu_Reg_master_dataCount?" + key + "=" + value, {
			//nrh_en_no:nrh_en_no,
				Search : Search,
			first_name:first_name,
			institute_status:institute_status,
			 gender:gender,
			 registration_state:registration_state,
			 dob:dob,
	  		 date_of_reg:date_of_reg,
	  		 institute_name:institute_name,role_type:role_type
			   
		}, function(j) {
			
			FilteredRecords = j;

			});
		setTimeout(setTimeLoadForTable, 1000);
	}


	
	
	function setselectall(){
		if ($("#nSelAll").prop("checked")) {
			$(".nrCheckBox").prop('checked', true);
		} else {
			$(".nrCheckBox").prop('checked', false);
		}
		var l = $('[name="cbox"]:checked').length;
		 $("#tregn").val(l);
		document.getElementById('tregn').innerHTML = l;
		checkCKBT();
	}
	
	function checkCKBT(){
		var lchk = $('input[name="cbox"]:checked').length;
		if(lchk>0 ){
			$("#rejbt").show();
			 $("#apprvbt").show();
		}
		else{
			$("#rejbt").hide();
			 $("#apprvbt").hide();
		}
	}

	
	function findselected(){
		var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
			return $(this).attr('id');
		}).get();
			
		var b=nrSel.join(':');
		$("#CheckVal").val(b);
		$('#tregn').text(nrSel.length);
	}
	
	
function EditData(id) {
 		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}

 	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}
	
 
	
	function setApproveStatus(){
		
		findselected();
		
		var a = $("#CheckVal").val();
		var b = $("#inst_id").val();
		if(a == ""){
			alert("Please Select the Data for Approval"); 
		}else{
			
			
// 			$.post("get_medicalcildIDUrl?"+key+"="+value, {a:a}).done(function(data) {
// 	 			alert(data+"--data");
	 			 
//  			}); 
			
			
			
	 			$.post("Approve_regulation_Data?"+key+"="+value, {a:a,status:"1",b:b}).done(function(j) {
	 			alert(j);
	 			location.reload();
 			}); 
		}	
	}
	function setRejectStatus(){
		 
		findselected();
		
		var a = $("#CheckVal").val();
		
		var tempSt='';
		var f = a.split(":");

		for (var i = 0; i < f.length; i++) {
			
			//	alert($("#remarks"+f[i]).val());
				
				if($("#remarks"+f[i]).val() == ""){
					alert("Please Enter the Remarks for Revert Back"); 
					return false;
				}
				
				
				
				if(i==0){
					tempSt+=$("#remarks"+f[i]).val();
				}else{
					tempSt+=","+$("#remarks"+f[i]).val();
				}
				
//	 			if($("#remarks"+f[i] == ""){
//	 				alert("Please Enter the Remarks for Revert Back"); 
//	 			}
				
				
			}
		
		if(tempSt == ""){
			alert("Please Select the Data for Revert Back"); 
		}else{
			 
				$.post("Reject_regulation_Data?"+key+"="+value, {a:a,status:"2",tempSt:tempSt}).done(function(j) {
				//	alert(j);
				alert("Revert Back Successfully");
					location.reload();
				Search();
			}); 
		}	
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
		
		
	</script>
<script nonce="${cspNonce}" type="text/javascript">

function getExcel() {	
	
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
} 



function getDistrictper() {
	var selval = $("#per_state").val();
	$("select#per_district").empty();

	$ .post( "getDistrictOnstate?" + key + "=" + value, { selval : selval },
					function(j) {
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							options += '<option   value="' + j[i].district_id + '" name="'+j[i].district_id+'" >'
									+ j[i].district_name + '</option>';
						}
						$("select#per_district").html(options);
						
					});
}

function getDegreeName(obj){
	var DegreeName = $("#"+obj.id).val();
	$.post('getDegreeName?' + key + "=" + value,{DegreeName:DegreeName},function(k) {
		var options = '';
		for (var i = 0; i < k.length; i++) {
			options += '<option value="'+k[i].id+'" name="' + k[i].degree_name+ '" >'+ k[i].degree_name + '</option>';
		}
		$("#degree_name").html(options);
 
	});
	
}






function Pop_Up_Degree(a,b) {
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	}
	
	$("input#popid").val(a);
	$("input#name_of_institute").val(b);
	
	document.getElementById('degreepopup_Form').submit();
	
}

function Pop_Up_IOCH(a) {
 	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	}
 	$("input#popidIOCH").val(a);
	document.getElementById('IOCHpopup_Form').submit();
	
}

function Pop_Up_DegreeP(id)
{  
	$("#id7").val(id); 
	document.getElementById('typeReport7').value='pdfL';
	document.getElementById('search2').submit();
}

function getPDF(id)
{  
	$("#id3").val(id); 
	document.getElementById('typeReport2').value='pdfL';
	document.getElementById('search1').submit();
}

function dynamicTable(a){
	 
	var R = a.split(":");
	 
	$("div#dynamicDataTable").html("");
	
	for(var i = 1 ; i <= R.length ;i ++ ){
		 
		var  seq =   i;
	 
		$("#dynamicDataTable").append("<div id='dynamicDataTable"+seq+"'></div>");
 		var R = a.split(":");
 		var idx = R[i-1]
		var nam = "";
			
	$.post("get_Parctname_by_InstiReject_id?" + key + "=" + value, {
		id:idx
		}, function(p) {
				nam = p;
		});
		
		
		
		$("div#dynamicDataTable"+seq).append(
				'<table class="table model-table" id="addAttDoc'+seq+'">'
				+'<tbody id="att_TbbodyattDoc'+seq+'">'
				+'	<tr id="tr_id_attDoc'+seq+'">'
				+'		<td class="min-width">'
				+'			<div class="lead">'
 				+'				<div class="lead-text">'
				+'					<p>'+i+'</p>'
				+'				</div>'
				+'			</div>'
					+'		</td>'
							+'	<td class="min-width">'
 							+'	<div class="input-style-1">'
 							+'<label id="name'+R[i-1]+'" name="name'+R[i-1]+'"  value="'+nam+'">'+nam+'</label>'
 							+'	<input type="text" hidden="hidden" id="client'+seq+'" name="client'+seq+'"  value="'+R[(i - 1)]+'"> '
 							+'</div> '
							+'</td>'
							+'	<td class="min-width">'
 							+'	<div class="input-style-1">'
							+'	<input type="text" id="remarks'+R[i-1]+'" name="remarks'+R[i-1]+'" placeholder= "Enter Your Remarks"  class="form-control"> '
 							+'</div> '
							+'</td>'
							 +'</tr> </tbody> </table>' 
			);
		
 	
	}
	 
 
	}
	
	//Uni Degree Preview jsp
	
 	
	function fn_pre_app_uni(id)
	{  
		 $("#u_id").val(id); 
		document.getElementById('preview_uni_Form').submit();
	}
	
	
	
	
	
	
	
	
	
	
	
</script>












