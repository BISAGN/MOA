<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->

<link rel="shortcut icon" href="admin/layout_file/images/favicon.png" >
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/common/multicheck.js"></script>	
<link rel="stylesheet" href="js/common/multicheck.css">
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<link rel="stylesheet" href="assets/db_css/db_custom_style.css">

 

<script>
	var username = "${username}";
</script>

<section class="dashboard-page search_registration">
      <div class="container-fluid">
        <!-- title-wrapper start -->
        <div class="title-wrapper pt-30">
          <div class="row align-items-center">
            <div class="col-md-6">
              <div class="title mb-30">
                <h2>SEARCH REGISTRATION</h2>
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
                    <li class="breadcrumb-item"><a href="#0">Registration Forms</a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                     Search Registration
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
              <form:form name="Search_Pra_Registeration" id="Search_Pra_Registeration" action="Search_Pra_Registeration_action" method='POST' modelAttribute="Search_Pra_Registeration_cmd" enctype="multipart/form-data">
              <div class="card-style mb-30">
                <h6 class="mb-25">SEARCH REGISTRATION MASTER</h6>
               <div class="row">
               
                 <div class="col-lg-3 col-md-6 col-sm-12">
                <div class="input-style-2 select-style-1">
                  <label> University State </label>
<!--                  		<input id="institute_state" name="institute_state" class="form-control" autocomplete="off" maxlength="25" placeholder="Maximum 25 Character"> -->
									<div class="select-position">
									<select name="institute_state" id="institute_state" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getMedStateName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
									</div>
									<input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off"> 
                
                </div>
                </div>

          <!--       <div class="col-lg-3 col-md-6 col-sm-12">
                <div class="input-style-1">
                  <label> Status </label>
                  <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off" ><div class="select-position">
                  <select name="status" id="status" class="form-control customselect" onchange="statusChange()">						 
										<option value="0">Pending</option>
										<option value="1">Approved</option>
										<option value="2">Reverted</option>
								</select></div>
                
                </div>
                </div> -->
                
                
                
                
                
                
                  <div class="col-lg-3 col-md-6 col-sm-12">
                <div class="select-style-1">
                  <label> Status </label>
                 <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off" ><div class="select-position">
                  <select name="status" id="status" class="form-control customselect" onchange="statusChange()">						 
										<option value="0">Pending</option>
										<option value="1">Approved</option>
										<option value="2">Reverted</option>
								</select></div>
                
                </div>
                </div>
                
                
                
                
                
                
                
                
                 
                  <ul class="buttons-group mainbtn">
                  
                    <li>
                   <!--  <input type="button" class="main-btn active-btn btn-hover btn-search" id="btn-reload" value="Search"> -->
                   <a class="main-btn secondary-btn btn-hover btn-iconic-icon btn-search" id="btn-reload" value="Search"><i class="lni lni-search-alt"></i>Search</a>
                  </li>
                   <li>
                    <a href="Search_pra_RegistrationUrl" class="main-btn dark-btn btn-hover btn-clear" value="Reset">Reset</a>
                  </li>
<!--                     <li> -->
<!--                     <input type="submit" class="main-btn secondary-btn btn-hover btn-save" value="Save" onclick="return Validate();"> -->
<!--                   </li> -->
                  
                  
                  
                     <li>
                   
                    <input type="button" class="main-btn success-btn  btn-hover" value="Approve" id="apprvbt" style="display: none" onclick="return setApproveStatus();">
                  </li>
                  <li>
                    <input type="button" class="main-btn danger-btn btn-hover" value="Revert Back"  id="rejbt"  style="display: none" value="Reject"  onclick="return setRejectStatus();">
                  </li>
                  
                </ul>
                
                </div>
              </div>
              </form:form>
              <!-- end card -->
          
            </div>
            
          </div>
          <!-- end row -->
        </div>


<%-- <form:form name="Search_Pra_Registeration" id="Search_Pra_Registeration" action="Search_Pra_Registeration_action" method='POST' modelAttribute="Search_Pra_Registeration_cmd" enctype="multipart/form-data"> --%>
	<!-- <div class="container-fluid" align="center">
		<div class="card">
			<div class="card-header"><h5><span id="lbladd"></span>SEARCH PRACTITIONER REGISTERATION</h5></div> -->

			
<!-- 			<div class="card-footer" align="center"> -->
			
						
<!-- 			start new -->
<!-- <i class="fa fa-file-pdf-o"></i><input type="button" id="printId" class="btn btn-info btn-sm" value="PDF" title="Export to PDF" onclick="getPDFExecl('pdfL');"> -->
<!-- 			end -->
			
			<!-- 	<a href="edu_search_reg_url" class="btn-clear">Reset</a> -->
<!-- 				<input type="submit" class="btn-save" value="Save" onclick="return Validation();">  -->
<!-- 				<i class="action_icons searchButton"></i><input type="button" -->
<!-- 					class="btn-search" id="btn-reload" value="Search"> -->
				<!-- 	<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i> -->
		
<!-- 		 <input type="button" class="btn btn-success btn-sm" value="Approve" -->
<!-- 							onclick="return setApproveStatus();"> -->
<!-- 							<input type="button" class="btn btn-danger btn-sm" value="Revert Back" -->
<!-- 							onclick="return setRejectStatus();"> -->
		
		
<!-- 			</div> -->
			
<!-- 			 <div  align="center"> -->
		                 
<!-- 							</div> -->
			
			
		<!-- </div>
	</div> -->
<%-- </form:form> --%>


 	<div class="card-style mb-30 selectsection"  id="checkheaddiv">
			 <input type="hidden" id="CheckVal" name="CheckVal">
				  <b><input class="form-check-input" type=checkbox id='nSelAll' name='tregn' onclick='setselectall();'>Select all [<span id="tregn">0</span>
				   <span id='nTotRow1'>/</span><span id="tregnn"  ></span>]</b> 
					</div>

  <div class="tables-wrapper">
            <div class="row">
              <div class="col-lg-12">
                <div class="card-style mb-30">
                <form:form name="Search_Pra_Registeration" id="Search_Pra_Registeration" action="Search_Pra_Registeration_action" method='POST' modelAttribute="Search_Pra_Registeration_cmd" enctype="multipart/form-data">
               
                  <div class="table-wrapper table-responsive custom-datatable-p custom-datatable-p-search_pra">
                    <table id="Search_Pra_Registration_Master" class="table">
                      <thead>
                        <tr>
				<th><h6>Ser No.</h6></th>
				<th  id="apprvchk"><h6>Select</h6></th>
				<th><h6>Aayush Id</h6></th>
				<th><h6>Name</h6></th>
				 <th><h6>Email Id</h6></th>
				 <th><h6>University Name</h6></th>
				 <th><h6>University State</h6></th>
				
				
			</tr>
                        <!-- end table row-->
                      </thead>
                      <tbody class="custom-datatablepra">
                   
                      </tbody>
                    </table>
                    <!-- end table -->
                  </div>
                  </form:form>
                </div>
                <!-- end card -->
              </div>
              <!-- end col -->
            </div>
            <!-- end row -->
          </div>
       

<!-- <div class="container-fluid" >
	<table id="Search_Pra_Registration_Master" class="display table no-margin table-striped  table-hover  table-bordered ">
		<thead>
			<tr>
				<th align="center">Ser NO</h6></th>
				<th><h6>Aayush Id</h6></th>
				<th><h6>Name</h6></th>
				 <th><h6>Email Id</h6></th>
				 <th><h6>Institute Name</h6></th>
				 <th><h6>Institute State</h6></th>
				
				
			</tr>
		</thead>
		
		
		<tbody >
		
		</tbody >
		
	</table> -->
	
<!-- 		                 <div  align="center"> -->
<!-- 		                  <input type="button" class="btn-save" value="Approve" -->
<!-- 							onclick="return setApproveStatus();"> -->
<!-- 							<input type="button" class="btn btn-danger btn-sm" value="Revert Back" -->
<!-- 							onclick="return setRejectStatus();"> -->
							
<!-- 							</div> -->
							
							
							
				
</div>

<!-- start new pdf -->
<%-- <c:url value="Regulation_Report_Url_pdf" var="mprUrl2" /> --%>
<%-- <form:form action="${mprUrl2}" method="post" id="search1" name="search1" > --%>
	
<!--         <input type="hidden" name="typeReport" id="typeReport" value=""/> -->
      
<%-- </form:form> --%>
<!-- end -->


<script>
	
	$(document).ready(function() {
		mockjax1('Search_Pra_Registration_Master');
		table = dataTable('Search_Pra_Registration_Master');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
			removecol();
		});
	});
 
	function data(Search_Pra_Registration_Master) {
	
		//debugger;
		jsondata = [];
		var table = $('#' + Search_Pra_Registration_Master).DataTable();
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


		var institute_state = $("#institute_state").val();   
		var status = $("#status").val();
		
		

		
		$.post("getFilter_Pra_Registration_master_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			institute_state:institute_state,
			status:status,
			
			
			
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				 
				var status = $("#status").val();
				 if(status=='0'){
					 $("#apprvchk").show();
					 $("#checkheaddiv").show();
					jsondata.push([ j[i].ser ,j[i].chekboxaction,j[i].aayushid, 
									j[i].name, j[i].email_id, j[i].institute_name, j[i].institute_state ]);
				 }
				 else if(status=='1' || status=='2'){
					 $("#apprvchk").hide();
					 $("#checkheaddiv").hide();
					 
					 jsondata.push([ j[i].ser,j[i].chekboxaction ,j[i].aayushid, 
							j[i].name, j[i].email_id, j[i].institute_name, j[i].institute_state ]);
				 }
			}
			$("#tregnn").text(j.length);
		});
		
		
		$.post("getTotalPra_Registration_master_dataCount?" + key + "=" + value, {
			institute_state:institute_state,
			status:status,
			
			
			
		}, function(j) {
			
			FilteredRecords = j;

			});
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
	//end

	
	
	
	//start new pdf
	
	function getPDFExecl(pdf_excel){	
	
	document.getElementById('typeReport').value=pdf_excel;
	document.getElementById('search1').submit();	
}
	//end

// 	function dateChange(){
// 		var from_date = $("#from_date").val();
// 		var to_date = $("#to_date").val(from_date);
// 	}
	
	function removecol() {	
	
		// Getting the table
    var tble = document.getElementById('Search_Pra_Registration_Master'); 

    // Getting the rows in table.
    var row = tble.rows;  
    // Removing the column at index(1).  
    var i = 1; 
    
    for (var x = 0; x < row.length; x++) {

        // Deleting the ith cell of each row.
        row[x].deleteCell(i);
    }
}
	
	function findselected(){
		debugger;
		var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
			return $(this).attr('id');
		}).get();
			
		var b=nrSel.join(':');
		$("#CheckVal").val(b);
		$('#tregn').text(nrSel.length);
	}
	
	function singleappcount(){ 
		var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
			return $(this).attr('id');
		}).get();
			
		var b=nrSel.join(':');
		$('#tregn').text(nrSel.length);
	}
	
	
	function setApproveStatus(){
		
		findselected();
		var a = $("#CheckVal").val();
		if(a == ""){
			alert("Please Select the Data for Approval"); 
		}else{
	 			$.post("Approve_User_Data_prac?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
	 			alert(j);
	 			location.reload();
				 
			}); 
		}	
	}
	function setRejectStatus(){
		 
		findselected();
		
		var a = $("#CheckVal").val();

		if(a == ""){
			alert("Please Select the Data for Revert Back"); 
		}else{
			 
				$.post("Approve_User_Data_prac?"+key+"="+value, {a:a,status:"2"}).done(function(j) {
					alert(j);
					location.reload();
				Search();
			}); 
		}	
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
	
	function statusChange(){
		 var status = $("#status").val();
		 if(status=='0'){
			 $("#rejbt").show();
			 $("#apprvbt").show();
			 $("#checkheaddiv").show();
			 checkCKBT();
		 }
		 else if(status=='1' || status=='2'){
			 $("#rejbt").hide();
			 $("#apprvbt").hide();
			 $("#checkheaddiv").hide();
			 checkCKBT();
		 }
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
	
</script>














