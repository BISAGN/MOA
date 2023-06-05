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


				<section class="dashboard-page b_regulation_report">
      	<div class="container-fluid">
        <!-- title-wrapper start -->
        <div class="title-wrapper pt-30">
          <div class="row align-items-center">
            <div class="col-md-6">
              <div class="title mb-30">
                <h2>REGULATION REPORT</h2>
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
                    <li class="breadcrumb-item"><a href="#0">Regulation Forms</a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                     Regulation Report
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
        
            <div class="form-elements-wrapper">
         <div class="row">
          
          <div class="col-lg-12">
              <!-- input style start -->
              <div class="card-style mb-30">
                <h6 class="mb-25">REGULATION REPORT</h6>
        <div class="row">
			<div class="col-lg-6 col-md-6 col-sm-12">
                 
                  <div class="input-style-2">
                  <label>State <strong class="mandatory"> </strong></label>
                   <div class="select-style-2">
										<div class="select-position">
											<!-- <select name="per_state" id="per_state" class="select2 narrow wrap form-control-sm form-control effect-9" onchange="getDistrict();">
					 <option value="0">--Select--</option>
						 
						 <option value="24" name="DELHI">DELHI</option>
						 
						 <option value="19" name="GUJARAT">GUJARAT</option>
						 
						 <option value="20" name="MAHARASHTRA">MAHARASHTRA</option>
						 
						 <option value="21" name="PUNJAB">PUNJAB</option>
						 
						 <option value="22" name="RAJASTHAN">RAJASTHAN</option>
						 
						 <option value="23" name="UTTAR PRADESH">UTTAR PRADESH</option>
						 
				</select> -->
											<select name="per_state" id="per_state"
												class="form-control autocomplete">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${MedStateName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>

											</select>
										</div>
									</div>
                   </div>
               </div>
               
               <div class="col-lg-6 col-md-6 col-sm-12">
                 <div class="input-style-2">
                  <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
                  <label>NRH No <strong class="mandatory"> </strong></label>
                  <input id="nrh_en_no" name="nrh_en_no" class="form-control" autocomplete="off" maxlength="25" placeholder="Maximum 25 Character" onkeypress="return onlyAlphabetsStringSpace(this,event);">
                 
                   </div>
                </div>
                
                   <div class="col-lg-6 col-md-6 col-sm-12">
                   <div class="input-style-2">
                  <label>From Date <strong class="mandatory"> </strong></label>
                  <input type="date" id="from_date" onchange="dateChange();">
                  <span class="icon"><i class="lni lni-chevron-down"></i></span>
                   </div>
               </div>
               <div class="col-lg-6 col-md-6 col-sm-12">
                    <div class="input-style-2">
                  <label>To Date <strong class="mandatory"> </strong></label>
                  <input type="date" id="to_date">
                  <span class="icon"><i class="lni lni-chevron-down"></i></span>
                   </div>
                </div>
                
                
                <div class="col-lg-4 col-md-6 col-sm-12" style="display: none">
                 <div class="input-style-2">
                  <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
                  <label>Status <strong class="mandatory"> </strong></label>
                   <div class="select-style-2">
                  <div class="select-position">
                    <select name="status" id="status" class="form-control customselect">						 
										<option value="0">Pending</option>
										<option value="1">Approve</option>
										<option value="2">Reject</option>
								</select>
								
                  </div>
                </div>
                   </div>
                </div>
                
                <ul class="buttons-group mainbtn">
                  <li>
                    <a href="b_Regulation_Report_Url" class="main-btn dark-btn btn-hover btn-clear" value="Reset">Reset</a>
                  </li>
<!--                     <li> -->
<!--                     <input type="submit" class="main-btn secondary-btn btn-hover btn-save" value="Save" onclick="return Validate();"> -->
<!--                   </li> -->
                  <li>
<!--                      <a href="#0" type="button" class="main-btn primary-btn-outline btn-hover btn-iconic-icon" id="btn-reload"><i class="lni lni-search-alt"></i>search</a> -->
                      <input type="button" class="main-btn active-btn btn-hover btn-search" id="btn-reload" value="Search">
                
                  </li>
                  <li>
                   
      
                       <a class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" onclick="getPDFExecl('pdfL');" ><i class="lni lni-printer" id="printId" value="PDF" title="Export to PDF" ></i> PDF </a>
                  </li>
                  
                  
                </ul>
</div>
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
                    <table id="Search_b_regulation_Master" class="table">
                      <thead>
                        <tr>
                     
                         <th><h6>Ser No</h6></th>
				<th id="2"><h6>NRH Enrollment No</h6></th>
				<th><h6>Name Of The Professional With Recent Photograph</h6></th>
				<th><h6>Father's Name</h6></th>
				 <th><h6>Present Correspondence Address</h6></th>
				 <th><h6>Permanent Address</h6></th>
 				 <th><h6> Email Address</h6></th>
				 <th><h6>Date of Birth And Nationality</h6></th>
				 <th><h6>Registration Particulars:
				 1.Registration Number
				 2.Date of Registration
				 3.Name Of The Register(National/State)</h6>
				 </th>
				  
				
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
       
       
       <!-- start new pdf -->
<c:url value="b_Regulation_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search1" name="search1" >
	
        <input type="hidden" name="typeReport" id="typeReport" value=""/>
<!--         <input type="hidden" name="status123" id="status123" value="0"/> -->
      
</form:form>
<!-- end -->
       
       
       <script>
	
	$(document).ready(function() {
		mockjax1('Search_b_regulation_Master');
		table = dataTable('Search_b_regulation_Master');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
	});
 
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
		var mo_no = $("#mo_no").val();
		var alt_mo_no = $("#alt_mo_no").val();
		var email_id = $("#email_id").val();
		var dob = $("#dob").val();
		var nationality = $("#nationality").val();
		var reg_no = $("#reg_no").val();
		var date_of_reg = $("#date_of_reg").val();
		var name_reg = $("#name_reg").val();
		var name_of_institute = $("#name_of_institute").val();
 
		var status = $("#status").val();
		
		var from_date = $("#from_date").val();
		var to_date = $("#to_date").val();

		
		$.post("getFilter_b_Reg_Report_data?" + key + "=" + value, {
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
			to_date:to_date
			
			
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
		
				jsondata.push([ j[i].ser, j[i].nrh_en_no, j[i].img, 
								j[i].father_name,
								(j[i].pre_district + '</br>' + j[i].pre_state + '</br>' +  j[i].pre_pincode),
								( j[i].per_district + '</br>' + j[i].per_state + '</br>' +  j[i].per_pincode) , 
					 			( 'Email : ' + j[i].email_id),
					 			('DOB: '+j[i].dob + '</br>' +'Nationality : '+ j[i].nationality + '</br>' ) , 
 					 			('Registration Number: ' + j[i].reg_no + '</br>'+ 'Date Of Registration: '+ j[i].date_of_reg + '</br>'+'Name Of Registration : ' + j[i].reg_auth),
 					 			j[i].chekboxaction ]);
			}
		});
		
		
		$.post("getTotal_b_Edu_Reg_Report_dataCount?" + key + "=" + value, {
			nrh_en_no:nrh_en_no,
			first_name:first_name,
			per_state:per_state,
			from_date:from_date,
			to_date:to_date,
			status:status
			
		}, function(j) {
			
			FilteredRecords = j;

			});
	}
	
	
	//start pdf
	
	function downloadnote_file(id,fildname) {
	
	var pdfView="kmlFileDownload4442?kmlFileId455="+id+"&fildname1="+fildname+"";
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
	var status = $("#status").val();
//	document.getElementById('status123').value=status;	
	document.getElementById('search1').submit();	

}
	//end

		function dateChange(){
		var from_date = $("#from_date").val();
		var to_date = $("#to_date").val(from_date);
	}
	
</script>




       
       
       
       
       
       