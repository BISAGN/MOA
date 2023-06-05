 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<link rel="shortcut icon" href="admin/layout_file/images/favicon.png" >
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
 
<script src="js/Calender/jquery-ui.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">
<link rel="stylesheet" href="admin/assets/db_css/db_custom_style.css">

 
<script>
	var username = "${username}";
</script>

<section class="dashboard-page b_edu_act_inact">
      <div class="container-fluid">
        <!-- title-wrapper start -->
        <div class="title-wrapper pt-30">
          <div class="row align-items-center">
            <div class="col-md-6">
              <div class="title mb-30">
                <h2> USER ACTIVE INACTIVE</h2>
              </div>
            </div>
            <!-- end col -->
            <div class="col-md-6">
              <div class="breadcrumb-wrapper mb-30">
                <nav aria-label="breadcrumb">
                  <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                      <a href="#0">Start View</a>
                    </li>
                    <li class="breadcrumb-item"><a href="#0">Regulation Form B</a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                      User Active Inactive
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
              <form:form name="b_regulation_Report" id="b_regulation_Report" action="b_regulation_Report_action" method='POST' modelAttribute="b_regulation_Report_cmd" enctype="multipart/form-data">
              <div class="card-style mb-30">
              
                <h6 class="mb-25">Report</h6>
               <div class="row">
              
                 <div class="col-lg-4 col-md-6 col-sm-12">
                 
                  <div class="input-style-2">
                  <label>State <strong class="mandatory"> </strong></label>
                   <div class="select-style-2">
                  <div class="select-position">
                  <select name="pre_state" id="pre_state" class="select2 narrow wrap form-control-sm form-control effect-9" onchange="getDistrict();">
					 <option value="0">--Select--</option>
						 <c:forEach var="item" items="${MedStateName}" varStatus="num">
						 <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
						 </c:forEach>
												</select>
                  </div>
                </div>
                   </div>
                   
                   
               </div>
<!--                <div class="col-lg-4 col-md-6 col-sm-12"> -->
<!--                 <div class="input-style-2"> -->
<!--                   <label>Ayush Number<strong class="mandatory"> </strong></label> -->
<!--                   <input id="first_name" name="first_name" class="form-control" autocomplete="off" maxlength="25" placeholder="Maximum 25 Character" onkeypress="return onlyAlphabetsStringSpace(this,event);"> -->
                  
<!--                 </div> -->
<!--                 </div> -->
                
                <div class="col-lg-4 col-md-6 col-sm-12">
                 <div class="input-style-2">
                  <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
                  <label>NRH No <strong class="mandatory"> </strong></label>
                  <input id="nrh_en_no" name="nrh_en_no" class="form-control" autocomplete="off" maxlength="25" placeholder="Maximum 25 Character" onkeypress="return onlyAlphabetsStringSpace(this,event);">
                 
                   </div>
                </div>
                
                   <div class="col-lg-4 col-md-6 col-sm-12">
                   <div class="input-style-2">
  				<label> From Date <strong class="mandatory">  </strong> </label>
                  <input type="date" id="from_date" onchange="dateChange();">
                  <span class="icon"><i class="bi bi-calendar"></i></span>
                </div>
               </div>
               <div class="col-lg-4 col-md-6 col-sm-12">
                   
                   <div class="input-style-2">
  				<label> To Date <strong class="mandatory"></strong> </label>
                  <input type="date" id="to_date">
                  <span class="icon"><i class="bi bi-calendar"></i></span>
                </div>
                </div>
                
                
                <div class="col-lg-4 col-md-6 col-sm-12">
                 <div class="input-style-2">
                  <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
                  <label>Status <strong class="mandatory"> </strong></label>
                   <div class="select-style-2">
                  <div class="select-position">
                    <select name="status" id="status" class="form-control customselect"  onchange="statusChange()">						 
										<option value="1">Active</option>
										<option value="5">Inactive</option>
										<option value="4">Suspended</option>
								</select>
								
                  </div>
                </div>
                   </div>
                </div>
                
                
                  <ul class="buttons-group mainbtn">
                
<!--                     <li> -->
<!--                     <input type="submit" class="main-btn secondary-btn btn-hover btn-save" value="Save" onclick="return Validate();"> -->
<!--                   </li> -->
                  <li>
 <!--                     <a type="button" class="main-btn primary-btn-outline btn-hover btn-iconic-icon" id="btn-reload" ><i class="lni lni-search-alt"></i>Search</a> -->
                    <a type="button" class="main-btn secondary-btn btn-hover btn-iconic-icon btn-search" id="btn-reload" value="Search"><i class="lni lni-search-alt"></i>Search</a>
                   </li>
                   
                   <li>
                    <a href="b_edu_act_sus_userUrl" class="main-btn dark-btn n btn-hover" value="Reset">Reset</a>
                  </li>
                   
                  <li>
                    <input type="button" id="act" class="main-btn primary-btn btn-hover" value="Active" onclick="return setApproveStatus();" style="display: none;">
                  </li>
                  <li>
                    <input type="button"id="inact" class="main-btn danger-btn btn-hover"  value="Inactive" onclick="return setRejectStatus();" style="display: none;">
                  </li>
                  
             	 <li>
                    <input type="button" id="suspend" class="main-btn deactive-btn btn-hover" value="Suspend" onclick="return setSuspendStatus();" style="display: none;">
                  </li>
               
                	
<!--                   <li> -->
<!--                     <a   class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" onclick="getPDFExecl('pdfL');"><i class="lni lni-printer" id="printId" value="PDF" title="Export to PDF" ></i> PDF </a> -->
<!--                   </li> -->
                </ul>
                </div>
               
              </div>
               </form:form>
              <!-- end card -->
          
            </div>
            
          </div>
          <!-- end row -->
        </div>
        
      
 

	 <br>
     <div class="card-style mb-30 selectsection" id="checkheaddiv">
<!--     <div class="form-check checkbox-style mb-20" align="center"> -->
                  <input class="form-check-input" type="hidden" id="CheckVal" name="CheckVal">
                  <b><label class="form-check-label" for="checkbox-1"><input class="form-check-input" type=checkbox id='nSelAll' name='tregn' onclick='setselectall();'>
                    Select all[<span id="tregn">0</span><span id='nTotRow1'>/</span><span id="tregnn"  >  </span>]</label></b>
                </div>
       <!--  <div align="center">
			 <input type="hidden" id="CheckVal" name="CheckVal">
				  <b><input type=checkbox id='nSelAll' name='tregn' onclick='setselectall();'>Select all [<span id="tregn">0</span><span id='nTotRow1'>/</span><span id="tregnn"  >  </span>]</b> 
					</div> --><br>
       
       <div class="tables-wrapper">
            <div class="row">
              <div class="col-lg-12">
                <div class="card-style mb-30">
                
                  <div class="table-wrapper table-responsive custom-datatable-p">
                 
                  
                    <table id="Search_b_regulation_Master" class="table">
                      <thead>
			<tr>
				<th align="center">Ser NO</th>
				<TH ID="2">Select</TH>
				<TH ID="2">NRH ENROLLMENT NO</TH>
				<th>Name Of The Professional With Recent Photograph</th>
				<th>Father's Name</th>
   				 <th> Email Address</th>
				 <th>Date of Birth And Nationality</th>
<!-- 				 <th>Name Of Medical Degree or -->
<!-- 				  Diploma Obtained And University  -->
<!-- 				  With The Month And Year Of  -->
<!-- 				  Passing Qualification</th> -->
<!-- 				 <th>Registration Particulars: -->
<!-- 				 1.Registration Number -->
<!-- 				 2.Date of Registration -->
<!-- 				 3.Name Of The Register(National/State) -->
				 
<!-- 				 </th> -->
<!-- 				 <th>Name Of Hospital Or Institute With Complete -->
<!-- 				  Address For Purpose Of Teaching Or Reserach  -->
<!-- 				  Or Practice Of Medicine</th> -->
<!-- 				 <th>Name Of -->
<!-- 				  Person In -->
<!-- 				  Institution Or -->
<!-- 				  Hospital With Will Be -->
<!-- 				  Responsible For Legal -->
<!-- 				  Issues Regarding  -->
<!-- 				  Patient Can  -->
<!-- 				  Provided By -->
<!-- 				  Doctor Concerned</th> -->
<!-- 				<th class="action">ACTION</th> -->
				
				
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
        </section>


<script>
	
	$(document).ready(function() {
		mockjax1('Search_b_regulation_Master');
		table = dataTable('Search_b_regulation_Master');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
			 
		});
	});
	 
	var status = $("#status").val();	
	if(status == "1"){
 		$("#inact").show();
		$("#suspend").show();
	}
	if(status == "5"){
 		$("#act").show();
		$("#suspend").show();
	}
	if(status == "4"){
 		$("#act").show();
		$("#inact").show();
	}
	
	
	
	
	function statusChange(){
		var status = $("#status").val();	
		if(status == "1"){
	 		$("#inact").show();
			$("#suspend").show();
			$("#act").hide();
		}
		if(status == "5"){
	 		$("#act").show();
			$("#suspend").show();
			$("#inact").hide();
		}
		if(status == "4"){
	 		$("#act").show();
			$("#inact").show();
			$("#suspend").hide();
		}
	}
	
	
	function data(Search_b_regulation_Master) {
	
		//debugger;
		jsondata = [];
		var table = $('#' + Search_b_regulation_Master).DataTable();
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
// 		var ph_no = $("#ph_no").val();
		var fax_no = $("#fax_no").val();
		var mo_no = $("#mo_no").val();
		var alt_mo_no = $("#alt_mo_no").val();
		var email_id = $("#email_id").val();
		var dob = $("#dob").val();
		var nationality = $("#nationality").val();
		var degree = $("#degree").val();
		var university = $("#university").val();
		var month_year = $("#month_year").val();
		var reg_no = $("#reg_no").val();
		var date_of_reg = $("#date_of_reg").val();
		var name_reg = $("#name_reg").val();
		var reg_renew_permanent = $("#reg_renew_permanent").val();
		var name_of_hospital_teaching = $("#name_of_hospital_teaching").val();
		var name_of_patient = $("#name_of_patient").val();
		var crh_no = $("#crh_no").val();
		var cch_no = $("#cch_no").val();
		var nch_no = $("#nch_no").val();
		var status = $("#status").val();
	//	alert("status-----"+status)
		var from_date = $("#from_date").val();
		var to_date = $("#to_date").val();
		
		

		
		$.post("getFilter_b_Reg_Report_dataAct?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			nrh_en_no:nrh_en_no,
			first_name:first_name,
			pre_state:pre_state,
			status:status,
			from_date:from_date,
			to_date:to_date
			
			
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				
				$("#tregnn").text(" "+j.length);	
				//e.reg_no,to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.name_reg,e.reg_renew_permanent,\n"
				var registration_for_type ="";
				registration_for_type = j[i].registration_for_type;
				if(registration_for_type == "0"){
					registration_for_type="Renewable"
				}
				if(registration_for_type == "1"){
					registration_for_type="Permanent"
				}
				jsondata.push([ j[i].ser , j[i].chekboxaction,j[i].nrh_en_no,j[i].img, 
								j[i].father_name,
								//(j[i].pre_address + '</br>' + j[i].pre_district + '</br>' + j[i].pre_state + '</br>' +  j[i].pre_pincode),
								//(j[i].per_address + '</br>' + j[i].per_district + '</br>' + j[i].per_state + '</br>' +  j[i].per_pincode) , 
					 			( 'Email : ' + j[i].email_id),
					 			('DOB: '+j[i].dob + '</br>' +'Nationality : '+ j[i].nationality + '</br>' ) , 
 					 			('Registration Number : ' + j[i].reg_no + '</br>'+ 'Date Of Registration: '+ j[i].date_of_reg + '</br>'+'Name Of Registration : ' + j[i].reg_auth
 					 					),
					   			 
									   
					j[i].chekboxaction,j[i].action ]);
			}
		});
		
		
		$.post("getTotal_b_Edu_Reg_Report_dataCountAct?" + key + "=" + value, {
			nrh_en_no:nrh_en_no,
			first_name:first_name,
			pre_state:pre_state,
			from_date:from_date,
			to_date:to_date,
			status:status
		}, function(j) {
			
			FilteredRecords = j;

			});
	}
	
	
	//start pdf
	
	function downloadnote_file(id,fildname) {
	
	var pdfView="kmlFileDownload4441Act1?kmlFileId455="+id+"&fildname1="+fildname+"";
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
	//end

	
	
	
	//start new pdf
	
	function getPDFExecl(pdf_excel){	
		  
	document.getElementById('typeReport').value=pdf_excel;
	document.getElementById('search1').submit();	
}
	//end
	
	////////janki
function setselectall(){
 
	if ($("#nSelAll").prop("checked")) {
		$(".nrCheckBox").prop('checked', true);
	} else {
		$(".nrCheckBox").prop('checked', false);
	}
	
	var l = $('[name="cbox"]:checked').length;
	 $("#tregn").val(l);
	document.getElementById('tregn').innerHTML = l;
	
}


function findselected(){
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		return $(this).attr('id');
	}).get();
		
	var b=nrSel.join(':');
	$("#CheckVal").val(b);
	$('#tregn').text(nrSel.length);
}

function setApproveStatus(){
	
	findselected();
	
	var a = $("#CheckVal").val();

	if(a == ""){
		alert("Please Select the Data for Approval"); 
	}else{
 			$.post("b_Active_User_Data?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
 			alert(j);
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
		 
			$.post("b_Active_User_Data?"+key+"="+value, {a:a,status:"5"}).done(function(j) {
				alert(j);
				location.reload();
			Search();
		}); 
	}	
}
function setSuspendStatus(){
	 
	findselected();
	
	var a = $("#CheckVal").val();

	if(a == ""){
		alert("Please Select the Data for Reject"); 
	}else{
		 
			$.post("b_Active_User_Data?"+key+"="+value, {a:a,status:"4"}).done(function(j) {
				alert(j);
				location.reload();
			Search();
		}); 
	}	
}

//start date change when selected

function dateChange(){
	var from_date = $("#from_date").val();
	var to_date = $("#to_date").val(from_date);
}
//end
	
</script>








