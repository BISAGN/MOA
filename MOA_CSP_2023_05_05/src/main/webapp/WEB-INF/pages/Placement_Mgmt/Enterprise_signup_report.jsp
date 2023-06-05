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
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- datatable style and js end-->
 
<section class="dashboard-page">
<div class="container-fluid">

<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title">
					<span id="lbladd"></span>
						<h2>Enterprise/Hospital/Other Report</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Enterprise/Hospital/Other Report</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12">
					<!-- input style start -->
					<form:form name="linkmaster" id="linkmaster" action="placement_company_reg_Action"
						method="post" class="form-horizontal" modelAttribute="placement_company_reg_CMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<h6 class="mb-25">Link Graduate Attribute And Program Outcome</h6>
							<div class="row">
					
								  <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Enterprise Name</label> 
										<input type="text" id="company_name" name="company_name"
										placeholder="Enterprise Name"
										maxlength="50" class="form-control" autocomplete="off">
									</div>
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Owner's Name</label> 
										<input type="text" id="name" name="name"
										placeholder="Owner's Name"
										maxlength="50" class="form-control" autocomplete="off">
									</div>
								</div>
								
								 <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
									<label>Email Id </label>
									<input type="email" id="email_id" name="email_id" pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
									 class="form-control" autocomplete="off"  placeholder="Email Id">
									</div>
									</div>
									
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
									<label>Mobile Number</label>
									<input type="text" id="mobile_no" name="mobile_no"
									  maxlength="10" minlength="10" placeholder="Mobile Number">
									</div>
									</div>
								
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Address</label> 
										<input type="text" id="address" name="address"
										placeholder="Address"
										maxlength="50" class="form-control" autocomplete="off">
									</div>
								    </div>
								    
								     <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">State</label>
										<div class="select-position">
											<select name="state" id="state"
											 class="singleselect form-control form-control-lg autocomplete" >
											<option value="0">--Select--</option>
											 <c:forEach var="item" items="${MedStateName}" varStatus="num">
											 <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											 </c:forEach>
											 </select>
										</div>
									</div>
								</div>
											
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">District</label>
										<div class="select-position">
											<select name="per_district" id="per_district"
											 class="singleselect form-control form-control-lg autocomplete" >
											 <option value="0">--Select--</option>
											 </select>
										</div>
									</div>
								</div>			
													
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="username">Pin Code</label>
										<input type="text" id="pincode" name="pincode"
										 maxlength="6" minlength="6" class="form-control" autocomplete="off" placeholder="Pin Code">

									</div>
								    </div>
					 
<!-- 									<div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
<!-- 									<div class="input-style-1"> -->
<!-- 										<label for="username">Product And Services<span class="mandatory">*</span></label> -->
<!-- 										<input type="text" id="product" name="product" -->
<!-- 										 maxlength="60" class="form-control" autocomplete="off" placeholder="Product And Service"> -->
<!-- 									</div> -->
<!-- 								    </div> -->
								    
								    <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="username">Website URL</label>
										<input type="text" id="web_url" name="web_url"
										 maxlength="50" class="form-control" autocomplete="off" placeholder="Website URL">
									</div>
								    </div>
								    
<!-- 								     <div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
<!-- 								     <div class="input-style-1"> -->
<!-- 								     <label>Date Of Birth<strong class="mandatory">*</strong></label>  -->
<!-- 								    <select class="form-dropdown validate[required] form-validation-error" id="input_29" name="q29_yearEstablished" style="width:450px" data-component="dropdown" required=""><option value=""> </option><option value="2022"> 2022 </option><option value="2021"> 2021 </option><option value="2020"> 2020 </option><option value="2019"> 2019 </option><option value="2018"> 2018 </option><option value="2017"> 2017 </option><option value="2016"> 2016 </option><option value="2015"> 2015 </option><option value="2014"> 2014 </option><option value="2013"> 2013 </option><option value="2012"> 2012 </option><option value="2011"> 2011 </option><option value="2010"> 2010 </option><option value="2009"> 2009 </option><option value="2008"> 2008 </option><option value="2007"> 2007 </option><option value="2006"> 2006 </option><option value="2005"> 2005 </option><option value="2004"> 2004 </option><option value="2003"> 2003 </option><option value="2002"> 2002 </option><option value="2001"> 2001 </option><option value="2000"> 2000 </option><option value="1999"> 1999 </option><option value="1998"> 1998 </option><option value="1997"> 1997 </option><option value="1996"> 1996 </option><option value="1995"> 1995 </option><option value="1994"> 1994 </option><option value="1993"> 1993 </option><option value="1992"> 1992 </option><option value="1991"> 1991 </option><option value="1990"> 1990 </option><option value="1989"> 1989 </option><option value="1988"> 1988 </option><option value="1987"> 1987 </option><option value="1986"> 1986 </option><option value="1985"> 1985 </option><option value="1984"> 1984 </option><option value="1983"> 1983 </option><option value="1982"> 1982 </option><option value="1981"> 1981 </option><option value="1980"> 1980 </option><option value="1979"> 1979 </option><option value="1978"> 1978 </option><option value="1977"> 1977 </option><option value="1976"> 1976 </option><option value="1975"> 1975 </option><option value="1974"> 1974 </option><option value="1973"> 1973 </option><option value="1972"> 1972 </option><option value="1971"> 1971 </option><option value="1970"> 1970 </option><option value="1969"> 1969 </option><option value="1968"> 1968 </option><option value="1967"> 1967 </option><option value="1966"> 1966 </option><option value="1965"> 1965 </option><option value="1964"> 1964 </option><option value="1963"> 1963 </option><option value="1962"> 1962 </option><option value="1961"> 1961 </option><option value="1960"> 1960 </option><option value="1959"> 1959 </option><option value="1958"> 1958 </option><option value="1957"> 1957 </option><option value="1956"> 1956 </option><option value="1955"> 1955 </option><option value="1954"> 1954 </option><option value="1953"> 1953 </option><option value="1952"> 1952 </option><option value="1951"> 1951 </option><option value="1950"> 1950 </option><option value="1949"> 1949 </option><option value="1948"> 1948 </option><option value="1947"> 1947 </option><option value="1946"> 1946 </option><option value="1945"> 1945 </option><option value="1944"> 1944 </option><option value="1943"> 1943 </option><option value="1942"> 1942 </option><option value="1941"> 1941 </option><option value="1940"> 1940 </option><option value="1939"> 1939 </option><option value="1938"> 1938 </option><option value="1937"> 1937 </option><option value="1936"> 1936 </option><option value="1935"> 1935 </option><option value="1934"> 1934 </option><option value="1933"> 1933 </option><option value="1932"> 1932 </option><option value="1931"> 1931 </option><option value="1930"> 1930 </option><option value="1929"> 1929 </option><option value="1928"> 1928 </option><option value="1927"> 1927 </option><option value="1926"> 1926 </option><option value="1925"> 1925 </option><option value="1924"> 1924 </option><option value="1923"> 1923 </option><option value="1922"> 1922 </option></select> -->
<!-- 									</div> -->
<!-- 									</div> -->
  
							</div>
							<ul class="buttons-group mainbtn">
<!-- 								<li><input class="main-btn info-btn btn-hover" type="button" value="Search" id="search_reg" name="search_reg"  /></li> -->
								<li><a
								class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
								id="btn-reload" value="Search"><i class="lni lni-search-alt"></i>Search</a>
							</li>
								<li><a href="Enterprise_Report_Url"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
									<li class="custom-d-none" id="apprvbt"><input type="button"
									class="main-btn success-btn btn-hover" value="Interested"
									 ></li>
							<li class="custom-d-none" id="rejbt"><input type="button"
								class="main-btn danger-btn btn-hover" value="Not Interested"  name="rejbt"  >
							</li>
									
							</ul>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		</div>
</section>
<!--  <div class="modal-footer"> -->
<!-- 				<ul class="buttons-group"> -->
<!-- 					<li><button type="button" -->
<!-- 							class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal" -->
<!-- 							data-dismiss="modal" aria-label="Close" id ="ok">Submit</button></li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
 
	<div class="card-style mb-30 selectsection" id="checkheaddiv" >
			<input type="hidden" id="CheckVal" name="CheckVal">
			<input type="hidden" id="CheckVal2" name="CheckVal2">
			 <input type="hidden" id="CheckVal3" name="CheckVal3">
			  <input type="hidden" id="CheckVal5" name="CheckVal5">
			   <input type="hidden" id="CheckVal6" name="CheckVal6">
			 <input
				class="form-check-input" type=checkbox id='nSelAll' name='tregn' >Select all [<span id="tregn">0</span><span
				id='nTotRow1'>/</span><span id="tregnn"> </span>]
		</div>
		
        <div class="tables-wrapper">
            <div class="row">
              <div class="col-lg-12">
                
                <div class="card-style mb-30">
                  <div class="table-wrapper table-responsive custom-datatable-p">
                    <table id="Search_regulation_Master" class="table">
                      <thead>
                        <tr>
                         <th><h6>Ser No.</h6></th>
                         <th id="apprvchk"><h6>Select for </h6></th>
						 <th><h6>Enterprise Name</h6></th>
						 <th><h6>Owner's Name</h6></th>
						 <th><h6>Email Id</h6></th>
						 <th><h6>Mobile Number</h6></th>
						 <th><h6>Landline Number</h6></th>
						 <th><h6>Address</h6></th>
						 <th><h6>State</h6></th>
						 <th><h6>District</h6></th>
						 <th><h6>Pin Code</h6></th>
				         <th><h6>Hours Of Operation From</h6></th>
                     	  <th><h6>Hours Of Operation To</h6></th>
<!--                      	  <th><h6>Product And Services</h6></th> -->
                     	  <th><h6>Website URL</h6></th>
                     	  <th><h6>Company Logo</h6></th>
						 <th><h6>Company Pictures</h6></th>
						 
                       
                       
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
<c:url value="Regulation_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search1" name="search1" >
<input type="hidden" name="typeReport" id="typeReport" value=""/>
<!--         <input type="hidden" name="status123" id="status123" value="0"/> -->
</form:form>
<!-- end -->

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
		mockjax1('Search_regulation_Master');
		table = dataTable('Search_regulation_Master');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
	});
	
	document.addEventListener('DOMContentLoaded', function() {

 
// 		document.getElementById('save_reg').onclick = function() {
// 			return Validate();
// 		};
		document.getElementById('state').onchange = function() {
 			getDistrictper();
		};
		document.getElementById('apprvbt').onclick = function() {
			return setApproveStatus();
			 
		};
		document.getElementById('rejbt').onclick = function() {
			
			return setRejectStatus();
		
		};
		
// 		document.getElementById('rejbt').onclick = function() {
// 			//alert("1234");
// 			   $('#modelWindow').modal('show');
			   
// 			  findselected();
// 				var a = $("#CheckVal").val();
			 
// 				if(a == ""){
// 					alert("Please Select the Data for Reject"); 
// 				}
				 
			   
// //	 		 return setRejectStatus();
// 		};
		document.getElementById('nSelAll').onclick = function() {
			setselectall();
		};
		
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
		
		var company_name = $("#company_name").val();
		var name = $("#name").val();
		var email_id = $("#email_id").val();
		var mobile_no = $("#mobile_no").val();
		var ph_no = $("#ph_no").val();
		var address = $("#address").val();
		var state = $("#state").val();
		var per_district = $("#per_district").val();
		var pincode = $("#pincode").val();
		var hours_from = $("#hours_from").val();
		var hours_to = $("#hours_to").val();
// 		var product = $("#product").val();
		var web_url = $("#web_url").val();
		var photo_path = $("#photo_path").val();
		 
		var photo_path_pic = $("#photo_path_pic").val();
		 
		$.post("getFilter_Enterprise_signup_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			company_name:company_name,
			name:name,
			email_id:email_id,
			mobile_no:mobile_no,
			ph_no:ph_no,
			address:address,state:state,per_district:per_district,pincode:pincode,hours_from:hours_from,hours_to:hours_to
			 ,web_url:web_url,photo_path:photo_path,photo_path_pic:photo_path_pic
			
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
					    jsondata.push([ j[i].ser,j[i].chekboxaction,
						j[i].company_name, 
						j[i].name,
			 			j[i].email_id,j[i].mobile_no,j[i].ph_no,j[i].address,j[i].state,j[i].per_district,j[i].pincode,
			 			j[i].hours_from,j[i].hours_to,j[i].web_url,j[i].img,j[i].img1
			 			 
			 			
			 			]);
			}
		});
		
		$.post("getTotalEdu_Enterprise_signup_dataCount?" + key + "=" + value, {
			company_name:company_name,
			name:name,
			email_id:email_id,
			mobile_no:mobile_no,
			ph_no:ph_no,
			address:address,state:state,per_district:per_district,pincode:pincode,hours_from:hours_from,hours_to:hours_to
			 ,web_url:web_url,photo_path:photo_path,photo_path_pic:photo_path_pic
		}, function(j) {
			FilteredRecords = j;
			$("#tregnn").html(j);
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
	

function setTimeLoadForTable(){
			
// 			document.getElementById('nrh_en_no').onkeypress = function () {
// 				return onlyAlphabetsStringSpace(this,event);
// 			};
			 
// 			document.getElementById('pdfex').onclick = function() {
// 				getPDFExecl('pdfL');
// 			};
			
	$('.nrCheckBox').on('change', function() {
		checkCKBT();
	});
			
			
		}
		
function setApproveStatus(){
	findselected();
	 var a = $("#CheckVal").val();
	 var email_id = $("#CheckVal5").val();
	 var notified = $("#CheckVal6").val();
// 	 alert(notified);
// 	 var upto = $("#CheckVal2").val();
// 	 var state = $("#CheckVal3").val();
 
	 console.log(a);
	
	if(a == ""){
		alert("Please Select the Data for Approval"); 
	}
 
	else{
			$.post("Approve_FromStudent_to_enterprise_Data?"+key+"="+value, {a:a ,email_id :email_id,notified:notified }).done(function(j) {
			
				alert("Your Interest Has Been Recorded.");

			location.reload();
		}); 
	}
}

function setRejectStatus(){
	findselected();
	 var a = $("#CheckVal").val();
// 	 var upto = $("#CheckVal2").val();
// 	 var state = $("#CheckVal3").val();

	 console.log(a);
	
	if(a == ""){
		alert("Please Select the Data for Approval"); 
	}
 
	else{
			$.post("Reject_FromStudent_to_enterprise_Data?"+key+"="+value, {a:a}).done(function(j) {
			
				alert("Your Interest Has Been Recorded.");

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
	
	var nrSel2=$('.nrCheckBox:checkbox:checked').map(function() {
	var upto=$("#valid_upto"+$(this).attr('id')).val();
		 
// 		 var y = upto.substring(0,4);
// 		 var m = upto.substring(5,7);
// 		 var d = upto.substring(8,10);
// 		 var upto2 = d+"/"+m+"/"+y;
// 		return upto2;
	}).get();
	
	var c=nrSel2.join(':');
	$("#CheckVal2").val(c);
	
	var nrSel3=$('.nrCheckBox:checkbox:checked').map(function() {
		var state = $("#nrCHid"+ $(this).attr('id')).val();
		return state;
	}).get();
		
	var z=nrSel3.join(':');
	$("#CheckVal3").val(z);
	
	var nrSel5=$('.nrCheckBox:checkbox:checked').map(function() {
		var email = $("#email"+ $(this).attr('id')).val();
		return email;
	}).get();
		
	var z5=nrSel5.join(':');
	$("#CheckVal5").val(z5);
	
	
	var nrSel6=$('.nrCheckBox:checkbox:checked').map(function() {
		var notif = $("#notif"+ $(this).attr('id')).val();
		return notif;
	}).get();
	
	var z6=nrSel6.join(':');
	$("#CheckVal6").val(z6);
	
	
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

function getDistrictper() {
	var selval = $("#state").val();
	$("select#per_district").empty();
	$ .post( "getDistrictOnstate_placement_report?" + key + "=" + value, { selval : selval },
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

</script>