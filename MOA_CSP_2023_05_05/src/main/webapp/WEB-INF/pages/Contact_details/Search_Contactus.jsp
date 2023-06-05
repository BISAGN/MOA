<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 
<!-- <script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script> -->
<script src="js/common/multicheck.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<script>
	var username = "${username}";
</script>
<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
					<span id="lbladd"></span>
						<h2>Search Contact Details</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Search Contact Details</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12">
					<!-- input style start -->
<%-- 					<form:form name="ProvisionalformA" id="ProvisionalformA" action="ProvisionalAction" --%>
<%-- 						method="post" class="form-horizontal" modelAttribute="ProvisionalCMD"> --%>
						<div class="card-style mb-30">
							<h6 class="mb-25">Search Contact Details</h6>
							<div class="row">
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Name</label> <input
											type="text" id="name" name=name
											class="autocomplete xt-transupp"
											autocomplete="off" maxlength="100" placeholder="Name" />
										
									</div>
								</div>
								
								
 								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Email</label> <input
											type="text" id="email" name=email
											class="autocomplete xt-transupp"
											autocomplete="off" maxlength="100" placeholder="Email" />
										
									</div>
								</div>
								
								
 								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Subject</label> <input
											type="text" id="subject" name=subject
											class="autocomplete xt-transupp"
											autocomplete="off" maxlength="100" placeholder="Subject" />
										
									</div>
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Message</label> <input
											type="text" id="message" name=message
											class="autocomplete xt-transupp"
											autocomplete="off" maxlength="100" placeholder="Message" />
										
									</div>
								</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-2">
											<label>From Date</label> 
												<input type="text" name="from_date" id="from_date" maxlength="10" 
												class="form-control-sm form-control" 
												aria-required="true" autocomplete="off" value="DD/MON/YYYY" placeholder="DD/MON/YYYY">														
									</div>
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-2">
											<label>To Date</label> 
												<input type="text" name="to_date" id="to_date" maxlength="10" 
												class="form-control-sm form-control" 
												aria-required="true" autocomplete="off" value="DD/MON/YYYY" placeholder="DD/MON/YYYY">														
									</div>
							</div>
							
							</div>
							<ul class="buttons-group mainbtn">

								<li> <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button" value="Search">
								<i class="lni lni-search-alt"></i>Search</a></li>
							
								<li><a href="Search_Contact_Us_Url"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
							</ul>
						</div>
						<!-- end card -->
<%-- 					</form:form> --%>
				</div>
			</div>
		</div>
            <div class="custom-datatable-main">
                <div class="card-style mb-30">
                    <div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="Search_Contactus">
                   		   <thead>
                       		 <tr>
							<th align="center"><h6>Ser No.</h6></th>
			 				<th><h6>Name</h6></th>
			 				<th><h6>Email</h6></th>
							<th><h6>Subject</h6></th>
							<th><h6>Message</h6></th>
							<th><h6>Date</h6></th>
							
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
<!--             </div> -->
 </section> 
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="assets/js/main.js"></script>


<script nonce="${cspNonce}" type="text/javascript">
var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";

$(document).ready(function() {
	mockjax1('Search_Contactus');
	table = dataTable('Search_Contactus');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$.ajaxSetup({
		async : false
	});
	datepicketDate('from_date');
	datepicketDate('to_date');
	
	$( "#from_date").datepicker( "option", "maxDate", null);
	$( "#to_date").datepicker( "option", "maxDate", null);
});


function data(Search_Contactus) {
	debugger;
	jsondata = [];
	var table = $('#' + Search_Contactus).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
	.toLowerCase();
	var orderType = order[0][1];
	
	
	var name = $("#name").val();
	var email = $("#email").val();
	var subject = $("#subject").val();
	var message = $("#message").val();
	var date = $("#date").val();
	
debugger;
	$.post("getSearch_contactus_dataList?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		name : name,
		email : email,
		subject : subject,
		message : message,
		date : date

	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].ser,j[i].name, j[i].email,j[i].subject,j[i].message, j[i].date ]);
		}
	});
	$.post("getTotalSearch_contactus_dataCount?" + key + "=" + value, {
		Search : Search,
		name : name,
		email : email,
		subject : subject,
		message : message,
		date : date
		
	
	}, function(j) {

		FilteredRecords = j;

	});
	setTimeout(setTimeLoadForTable, 1000);
}

function setTimeLoadForTable(){

	document.getElementById('name').onkeypress = function() {
				 return onlyAlphabetsStringSpace(this,event);
	};
	
	document.getElementById('btn-reload').onkeypress = function() {
		 return SearchContacts();
};

		}
function SearchContacts() {
	$("#name").val($('#name').val());
	$("#email").val($('#email').val());
	$("#subject").val($('#subject').val());
	$("#message").val($('#message').val());
	$("#date").val($('#date').val());
	
	document.getElementById('searchForm').submit();
}
</script>
    
    
    