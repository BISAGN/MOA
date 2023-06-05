<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/commonJS/addmorefunctionality.js"></script>
<script src="js/common/multicheck.js"></script>	
<link rel="stylesheet" href="js/common/multicheck.css">
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<link rel="stylesheet" href="admin/assets/db_css/db_custom_style.css">
 

<script>
	var username = "${username}";
</script>


<section class="search_b_regulation">
      <div class="container-fluid">
        <!-- title-wrapper start -->
        <div class="title-wrapper pt-30">
          <div class="row align-items-center">
            <div class="col-md-6">
              <div class="title mb-30">
                <h2>STATE SEARCH FOR PRACTITIONER</h2>
              </div>
            </div>
            <!-- end col -->
            <div class="col-md-6">
              <div class="breadcrumb-wrapper mb-30">
                <nav aria-label="breadcrumb">
                  <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                      <a href="#0">Modules</a>
                    </li>
                    <li class="breadcrumb-item"><a href="#0">Regulation Form B</a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                      State Search
                    </li>
                  </ol>
                </nav>
              </div>
            </div>
            <!-- end col -->
          </div>
          <!-- end row -->
        </div>
        <!-- title-wrapper end -->
        
        <div class="search-b-regulation-wrapper">
          <div class="row">
          
          <div class="col-lg-12">
              <!-- input style start -->
              <div class="card-style mb-30">
                <h6 class="mb-25">STATE SEARCH FOR PRACTITIONER </h6>
               <div class="row">
<!--                  <div class="col-lg-4 col-md-6 col-sm-12"> -->
<!--                 <div class="input-style-2"> -->
<!--                   <label> NRH No <strong class="mandatory">  </strong> </label> -->
<!--                   <input id="nrh_en_no" name="nrh_en_no" class="form-control" autocomplete="off" maxlength="25" placeholder="Maximum 25 Character"> -->
<!-- 				 <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">  -->
                
<!--                 </div> -->
<!--                 </div> -->
               <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label>First Name <strong class="mandatory"></strong></label>
                  <input id="first_name" name="first_name" class="form-control" autocomplete="off" maxlength="25" placeholder="First Name" onkeypress="return onlyAlphabetsStringSpace(this,event);">
                  <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
                </div>
                </div>
                

                 <div class="col-lg-4 col-md-6 col-sm-12">
					 <div class="input-style-2">
					 <label>Date Of Birth</label>
					  <input type="date"   id="dob" onchange=" ">
                  <span class="icon"><i class="bi bi-calendar"></i></span>
 					  </div>
				 </div>
				 
				 <div class="col-lg-4 col-md-6 col-sm-12">
														<div class="select-style-1">
															<label>Gender <strong class="mandatory">
															</strong></label>
															<div class="select-position">
																<select name="gender" id="gender" class="form-control">
																<option value=" ">--Select--</option>
 																	<option value="0">Male</option>
																	<option value="1">Female</option>
																	<option value="2">Other</option>
																</select>

															</div>
														</div>
													</div>
				 
                 </div>
                
                
               
                <div class="row">
                
                 
                
                 <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="input-style-2">
                 <label>Registration Number<strong class="mandatory">  </strong></label> <input type="text"
				 id="reg_no" name="reg_no" onkeypress="return isNumberOnly(event)" oninput="this.value = this.value.toUpperCase()" maxlength="10" class="form-control autocomplete"	autocomplete="off" placeholder="Registration Number">  
                </div>
                </div>
                
                 <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label>Date Of First Registration<strong class="mandatory"></strong></label>
                  <input type="date" id="date_of_reg" name="date_of_reg" class="form-control" autocomplete="off" maxlength="25" placeholder="Maximum 25 Character" onkeypress="return onlyAlphabetsStringSpace(this,event);">
                 <span class="icon"><i class="bi bi-calendar"></i></span>
                </div>
                </div>
                
                 <div class="col-lg-4 col-md-6 col-sm-12">
               <div class="select-style-1">
                  <label>Registration State<strong class="mandatory"> </strong></label>
                   <div class="select-position">
                    <select name="registration_state" id="registration_state" class="form-control customselect" 	onchange="getDistrictper();">
						 <option value="0">--Select--</option>
							 <c:forEach var="item" items="${MedStateName}" varStatus="num">
					 	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
							 </c:forEach>

														</select></div>
                  
                  </div>
                </div>
                
                 </div>
      
                <div class="row" style="display: none">
                
                 <div class="col-lg-4 col-md-6 col-sm-12">
               <div class="select-style-1">
                  <label>State<strong class="mandatory"> </strong></label> <div class="select-position">
                 <select name="per_state" id="per_state" class="form-control customselect" 	onchange="getDistrictper();">
						 <option value="0">--Select--</option>
							 <c:forEach var="item" items="${MedStateName}" varStatus="num">
					 	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
							 </c:forEach>

														</select></div>
                   
                   </div>
                </div>
                
                 <div class="col-lg-4 col-md-6 col-sm-12">
                 <div class="select-style-1">
                  <label> District<strong class="mandatory">  </strong> </label> <div class="select-position">
                <select name="per_district" id="per_district" class="form-control customselect">
			  </select>  </div>
                </div>
                </div>
               
               
                 <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="select-style-1">
                  <label>Place Of Working<strong class="mandatory"> </strong></label> <div class="select-position">
                 <select name="place_of_working1" id="place_of_working1" class="form-control customselect">
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${PlaceOfWorking}"
																			varStatus="num">
																			<option value="${item.id}"
																				name="${item.place_of_working_practitioner}">${item.place_of_working_practitioner}</option>
																		</c:forEach>
																</select></div>
                 
                 </div>
                </div>
                
                
                 </div>
                
     
        <div class="row" style="display: none">
             
                <div class="col-lg-4 col-md-6 col-sm-12">
               <div class="select-style-1">
                  <label>Type of Degree<strong class="mandatory"> </strong></label>
                <div class="select-position">
                <select name="type_of_degree" id="type_of_degree"
																class="form-control customselect"
																onchange="getDegreeName(this,1);">
																	<option value="0">--Select--</option>
																	<c:forEach var="item" items="${TypeOfDegree}"
																		varStatus="num">
																		<option value="${item.id}"
																			name="${item.type_of_degree}">${item.type_of_degree}</option>
																	</c:forEach>
															</select></div>
                  </div>
                </div>
                
                
                 <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="select-style-1">
                  <label>Degree<strong class="mandatory"> </strong></label><div class="select-position">
 						<select name="DegreeName1" id="DegreeName1" class="form-control customselect">
						 <option value="0">--Select--</option>
						 </select></div>
 
                 </div>
                </div>
                
                     
                 <div class="col-lg-4 col-md-6 col-sm-12">
                 <div class="select-style-1">
                  <label>Registration Is Renewable Or Permanent<strong class="mandatory">  </strong> </label><div class="select-position">
                  <select name="reg_renew_permanent" id="reg_renew_permanent" class="form-control customselect">
						 <option value="0">--Select--</option>
						  <option value="0">Renewable</option>
						   <option value="1">Permanent</option>
						 </select></div>
                </div>
                </div>
                
                 </div>
                         
                
              <div class="row">
                
                
                
<!--                  <div class="col-lg-4 col-md-6 col-sm-12"> -->
<!--                 <div class="input-style-2"> -->
<!--                   <label> Registration District<strong class="mandatory">  </strong> </label> -->
<!--                   <select name="registration_district" id="registration_district" class="form-control autocomplete"> -->
<!-- 			  </select>    -->
<!--                 </div> -->
<!--                 </div> -->
                                <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="select-style-1">
                  <label> Status <strong class="mandatory"> </strong> </label>
                  <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off"><div class="select-position">
                  <select name="institute_status" id="institute_status" class="form-control customselect" onchange="statusChange()">						 
										<option value="0">Pending</option>
										<option value="1">Approved</option>
										<option value="2">Reject</option>
								</select></div>
                
                </div>
                </div>
                 
                
                
                 </div>  
                
                  <ul class="buttons-group mainbtn">
                   <li>
                    <a class="main-btn secondary-btn btn-hover btn-iconic-icon btn-search" id="btn-reload" value="Search"><i class="lni lni-search-alt"></i>Search</a>
                  </li>
                  <li>
                    <a href="b_Search_State_pracUrl" class="main-btn dark-btn n btn-hover btn-clear" value="Reset">Reset</a>
                  </li>
<!--                     <li> -->
<!--                     <input type="submit" class="main-btn secondary-btn btn-hover btn-save" value="Save" onclick="return Validate();"> -->
<!--                   </li> -->
                 
                  <li>
                   
                    <input type="button" class="main-btn success-btn  btn-hover" value="Approve" id="apprvbt" style="display: none" onclick="return setApproveStatus();">
                  </li>
                  <li>
                    <input type="button" class="main-btn danger-btn btn-hover" value="Reject" id="rejbt"  style="display: none" onclick="return setRejectStatus();">
                  </li>
                  
                </ul>
                
               
              </div>
              <!-- end card -->
          
            </div>
            
          </div>
          <!-- end row -->
        </div>
     
       
        <div  class="card-style mb-30 selectsection" id="checkheaddiv">
			 <input type="hidden" id="CheckVal" name="CheckVal">
				  <input class="form-check-input" type=checkbox id='nSelAll'  name='tregn' onclick='setselectall();'>Select all [<span id="tregn">0</span><span id='nTotRow1'>/</span><span id="tregnn"  >  </span>]
					</div><br>
       
       <div class="tables-wrapper">
            <div class="row">
              <div class="col-lg-12">
                <div class="card-style mb-30">
                
                  <div class="table-wrapper table-responsive custom-datatable-p">
                    <table id="b_Search_State_Prac" class="table">
                      <thead>
                        <tr>
				<th align="center">Ser NO</th>
				 <th id="apprvchk">Select for approve</th>
 				<th>First Name</th>
				<th>Gender</th>
				 <th>Photo</th>
				 <th>Father Name</th>
 				 <th>Present District</th>
				 <th>Present State</th>
				 <th>Present Pincode</th>
 				 <th>Permanent District</th>
				 <th>Permanent State</th>
				 <th>Permanent Pincode</th>
				 <th>Email Id</th>
				 <th>Date Of Birth</th>
				 <th>Nationality</th>
				 <th>Degree Details</th>
				 <th>Practice Details</th>
<!--  				 <th>Registration No.</th> -->
<!-- 				 <th>Date Of Registration</th> -->
<!-- 				 <th>Name Of The Register</th> -->

				
				
				
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
        
        <c:url value="DegreePopupUrl" var="DegreePopupUrl" />
<form:form action="${DegreePopupUrl}" method="post" id="degreepopup_Form"
	name="degreepopup_Form" modelAttribute="id" target="result">
	<input type="hidden" name="popid" id="popid" value="0" />
</form:form>

<c:url value="IOCHPopupUrl" var="IOCHPopupUrl" />
<form:form action="${IOCHPopupUrl}" method="post" id="IOCHpopup_Form"
	name="IOCHpopup_Form" modelAttribute="popidIOCH" target="result">
	<input type="hidden" name="popidIOCH" id="popidIOCH" value="0" />
</form:form>
 

<c:url value="Edit_edu_b_reg_mstrUrl" var="Edit_edu_b_reg_mstrUrl" />
<form:form action="${Edit_edu_b_reg_mstrUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>


 <c:url value="delete_edu_b_reg_mstr_Url" var="delete_edu_b_reg_mstr_Url" />
<form:form action="${delete_edu_b_reg_mstr_Url}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>
<c:url value="Excel_Auth_Posted_query" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="cont_comd_ex">
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

<!-- The Modal -->
<div id="myModal" class="modal">
  <span class="close">&times;</span>

  <img class="modal-content" id="img01">
  <div id="caption"></div>
</div>
        
        
        <script>
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
	
	$(document).ready(function() {
		mockjax1('b_Search_State_Prac');
		table = dataTable('b_Search_State_Prac');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
			
			var institute_status = $("#institute_status").val();
			if(institute_status == "1" || institute_status == "2"){
				 removecol();	
			}
			if(institute_status == "0"){
 				  addcol();
			}
			 
			
		});

		
		$.ajaxSetup({
			async : false
		});
		 
	});
 
	function statusChange(){
		 var institute_status = $("#institute_status").val();
		 if(institute_status=='0'){
			 $("#rejbt").show();
			 $("#apprvbt").show();
			 $("#checkheaddiv").show();
			 checkCKBT();
		 }
		 else if(institute_status=='1' || institute_status=='2'){
			 $("#rejbt").hide();
			 $("#apprvbt").hide();
			 $("#checkheaddiv").hide();
			// checkCKBT();
		 }
	}
function removecol() {	
   var tble = document.getElementById('b_Search_State_Prac'); 
   var row = tble.rows;  
   var i = 1; 
   for (var x = 0; x < row.length; x++) {
       row[x].deleteCell(i);
   }
}
	
function addcol() {
   var tble = document.getElementById('b_Search_State_Prac');
	tble.clear();
   var row = tble.rows;  
   var i = 1; 
   for (var x = 0; x < row.length; x++) {
       row[x].insertCell(i);
   }
}


	 	 
		  
	 
		
	 
	
	
	
	
	
	
	
	function data(b_Search_State_Prac) {
		//debugger;
		jsondata = [];
		var table = $('#' + b_Search_State_Prac).DataTable();
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


		//var nrh_en_no = $("#nrh_en_no").val();
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
		var aadhaar_no = $("#aadhaar_no").val();
		var fax_no = $("#fax_no").val();
		var mo_no = $("#mo_no").val();
		var alt_mo_no = $("#alt_mo_no").val();
		var email_id = $("#email_id").val();
		var dob = $("#dob").val();
		var nationality = $("#nationality").val();
		var reg_no = $("#reg_no").val();
		var date_of_reg = $("#date_of_reg").val();
		var name_reg = $("#name_reg").val();
		var reg_renew_permanent = $("#reg_renew_permanent").val();
		var name_of_patient = $("#name_of_patient").val();
		 var institute_status = $("#institute_status").val();
		 
		 
		 var type_of_degree = $("#type_of_degree").val();
		 var reg_no = $("#reg_no").val();
		 var registration_state = $("#registration_state").val();
		 var degree_name = $("#DegreeName1").val();
		 var place_of_working = $("#place_of_working1").val();
	 	 var registration_for_type = $("select#reg_renew_permanent").val();
		$.post("getFilter_b_State_Prac_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			//nrh_en_no:nrh_en_no,
			first_name:first_name,
			institute_status:institute_status,
			 gender:gender,
			 reg_no:reg_no,
			 registration_state:registration_state,
			 per_state:per_state,
			 per_district:per_district,
			 type_of_degree:type_of_degree,
			 degree_name:degree_name,
			 place_of_working:place_of_working,
			 registration_for_type:registration_for_type,
			 dob:dob,
			 date_of_reg:date_of_reg
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
			
			$("#tregnn").text(" "+j.length);	
			
				$("#tregnn").text(" "+j.length);	
		 	
			var gender ="";
			  gender = j[i].gender;
			if(gender == "0"){
				gender="Male"
			}
			if(gender == "1"){
				gender="Female"
			}
			if(gender == "2"){
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
		 
			 var institute_status = $("#institute_status").val();
				 if(institute_status=='0'){
					 $("#apprvchk").show();
					 $("#checkheaddiv").show();
					 
				jsondata.push([ j[i].ser ,j[i].chekboxaction,j[i].first_name, gender, j[i].img, j[i].father_name,
					 j[i].pre_district, j[i].pre_state, j[i].pre_pincode,  
					j[i].per_district, j[i].per_state, j[i].per_pincode,
					 j[i].email_id , j[i].dob, j[i].nationality,j[i].vd, j[i].vioch
						  ]);
				 }
				 else if(institute_status=='1' || institute_status=='2'){
					 $("#apprvchk").hide();
					 $("#checkheaddiv").hide();
					 jsondata.push([ j[i].ser ,j[i].chekboxaction,j[i].first_name, gender, j[i].img, j[i].father_name
							 ,j[i].pre_district, j[i].pre_state, j[i].pre_pincode,
							j[i].per_district, j[i].per_state, j[i].per_pincode ,
							j[i].email_id , j[i].dob, j[i].nationality,j[i].vd, j[i].vioch
						
								  ]);
				 }
			}
		});
		
		
		$.post("getTotal_b_State_Prac_dataCount?" + key + "=" + value, {
			//nrh_en_no:nrh_en_no,
			first_name:first_name,
			institute_status:institute_status,
			 gender:gender,
			 reg_no:reg_no,
			 registration_state:registration_state,
			 per_state:per_state,
			 per_district:per_district,
			 type_of_degree:type_of_degree,
			 degree_name:degree_name,
			 place_of_working:place_of_working,
			 registration_for_type:registration_for_type,
			 dob:dob,
			 date_of_reg:date_of_reg
		}, function(j) {
			
			FilteredRecords = j;

			});
		
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
		if(a == ""){
			alert("Please Select the Data for Approval"); 
		}else{
 				$.post("Approve_From_b_State_Data?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
				
 					alert("Approved Successfully");

				location.reload();
			}); 
 				
		}	
	}
	
	
	
	
	
	function setRejectStatus(){
		 
		findselected();
		
		var a = $("#CheckVal").val();

		if(a == ""){
			alert("Please Select the Data for Reject"); 
		}else{

				$.post("Reject_From_b_State_Data?"+key+"="+value, {a:a,status:"2"}).done(function(j) {
					alert("Rejected Successfully");
 				location.reload();
			}); 
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
	
	
	
// 	function setRejectStatus(){
		 
// 		findselected();
		
// 		var a = $("#CheckVal").val();

// 		if(a == ""){
// 			alert("Please Select the Data for Reject"); 
// 		}else{

// 				$.post("Approve_FromState_Data?"+key+"="+value, {a:a,status:"2"}).done(function(j) {
// 					alert("Rejected Successfully");
//  				location.reload();
// 			}); 
// 		}	
// 	}
	
	
	
	



		
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
	

<script>

function getExcel() {	

	
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
} 



function getDistrictper() {
	var selval = $("#per_state").val();
	$("select#per_district").empty();

	$
			.post(
					"getDistrictOnstate?" + key + "=" + value,
					{
						selval : selval
					},
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

function getDegreeName(obj,R){
	var DegreeName = $("#"+obj.id).val();
	$.post('getDegreeName?' + key + "=" + value,{DegreeName:DegreeName},function(k) {
		var options = '';
		for (var i = 0; i < k.length; i++) {
			options += '<option value="'+k[i].id+'" name="' + k[i].degree_name+ '" >'+ k[i].degree_name + '</option>';
		}
		$("#DegreeName"+R).html(options);
//			for (var i = 0; i < data.length - 1; i++) {
//				datap = data[i].split(":");
//				options += '<option value="'+datap[i].id+'" name="' + datap[]+ '" >'+ datap[0] + '</option>';
//			}
//			$("#rank").html(options);
//			var q = '${list.rank}';
//			if(q != ""){
//				$("#rank").val(q);
//			}
	});
	
	
	
	
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

function Pop_Up_Degree(a) {

	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	}
	
	$("input#popid").val(a);
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

</script>





        

 