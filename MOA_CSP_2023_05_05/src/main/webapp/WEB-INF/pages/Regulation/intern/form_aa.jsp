<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<script type="text/javascript" src="js\watermark\common.js"></script>
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

<script src="js/Calender/datePicketValidation.js"></script>
<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							Form AA<span class="text-red font-size12 enter_by"></span>
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Form
									AA</li>
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
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form name="form_aa" id="form_aa" action="form_aaAction"
						method="post" modelAttribute="form_aaCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>Registration State <strong class="mandatory">*</strong></label>
											<div class="select-position">
												<select name="reg_state" id="reg_state"
													class="form-control autocomplete">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${MedStateName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
											<label id="reg_state_lbl" name="reg_state_lbl" value="">
												<strong class="mandatory"> </strong>
											</label> <input type="hidden" id="userId" name="userId">
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>Transfer To State <strong class="mandatory">*</strong></label>
											<div class="select-position">
												<select name="to_state" id="to_state"
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

									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">

											<label> Address <strong class="mandatory">*</strong></label>
											<input id="address" name="address" class="form-control"
												autocomplete="off" maxlength="100" placeholder="Address">
										</div>
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-2">
											<label>From Date <strong class="mandatory">*</strong></label>
											<input type="text" name="from_date" id="from_date"
												maxlength="10"
												class="form-control-sm form-control effect-9 "
												aria-required="true" autocomplete="off" value="DD/MM/YYYY">
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-2">
											<label>To Date <strong class="mandatory">*</strong></label> <input
												type="text" name="to_date" id="to_date" maxlength="10"
												class="form-control-sm form-control effect-9 "
												autocomplete="off" value="DD/MM/YYYY">
										</div>
									</div>
									<!--  onclick="return datecompare(this,from_date)" -->
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a id="btn-search"
												class="main-btn secondary-btn btn-hover btn-iconic-icon"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li><input id="btn-save"
												class="main-btn info-btn btn-hover" type="submit"
												value="Save" /></li>
											<li><a href="form_aa_Url"
												class="main-btn dark-btn n btn-hover" type="button">Reset</a>
											</li>
											<li><input type="button" id="pay_btn"
												class="main-btn secondary-btn btn-hover btn-save d-none"
												value="Pay"></li>
										</ul>
									</div>
								</div>
							</div>
						</div>

						<!-- end card -->
					</form:form>
				</div>
			</div>

			<section class="single-detail-block">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-datatable-p">
								<table class="table" id="search_form_aa">
									<thead>
										<tr>
											<th><h6>Sr No</h6></th>
											<th><h6>Temporary state reg.no</h6></th>
											<th><h6>Valid upto</h6></th>
											<th id="2"><h6>Registered state</h6></th>
											<th><h6>To state</h6></th>
											<th><h6>From date</h6></th>
											<th><h6>To date</h6></th>
											<th><h6>Address</h6></th>
										</tr>
										<!--  end table row -->
									</thead>
									<tbody>
									</tbody>
								</table>
								<!-- 	end table -->
							</div>
						</div>
						<!-- 		end card -->
					</div>
					<!-- 	end col -->
				</div>
			</section>
		</div>
	</div>
</section>

<c:url value="getSearch_form_aa_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Country_name1">
	<input type="hidden" name="Country_name1" id="Country_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_form_aa" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="delete_form_aa" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="form_aareport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script type="text/javascript" nonce="${cspNonce}">
	$(document).ready(function() {
	 	 
// 			if ('${pay_status}' == 1 ) {
// 				$("#pay_btn").hide(); 
// 			}else{
//  				$("#pay_btn").show(); 
// 			}
			 
			$("#reg_state").val(${reg_state}); 
			$("#userId").val(${userId}); 
			datepicketDate('from_date');
			datepicketDate('to_date');
 
			
		mockjax1('search_form_aa');
		table = dataTable('search_form_aa');
		$('#btn-search').on('click', function() {
			table.ajax.reload();
		});

		if ('${list.size()}' == "") {
			$("div#form_aa_Search").hide();
		}

		  
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
		
		//url clear
		if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}
		if(window.location.href.includes("id1"))
		{
			 var url = window.location.href.split("?id1")[0];
			 window.location = url;
		}
	});

	function fnPay_btn() {
		 var a = $("#user_id").val();
		$.post("formaapaymentStatusUrl?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
	 			alert("Your payment has been processed successfully.");
	 	location.reload();
	});
	}
	
	
	function setTimeLoadForTable(){
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
//  		document.getElementById('btn-search').onclick = function() {
// 			return Validation();
// 		};
 		 
 
		
		//checkLength
		
		document.querySelectorAll('.ADDform_aa').forEach((items, index) => {
			
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;

				var cid = document.getElementById('CounId'+val).value;
 				
				if (confirm('Are You Sure You Want to Update This  ?')) {
					editData(cid);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.DeleteCoun').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var cdid = document.getElementById('DCounId'+val).value;
				if (confirm('Are You Sure You Want to Delete  ?')) {
					deleteData(cdid);
				} else {
					return false;
				}
			})
		});
		
	}

	function data(search_form_aa) {
		jsondata = [];
		var table = $('#' + search_form_aa).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html() .toLowerCase();
		var orderType = order[0][1];
	
		var reg_state = $("#reg_state").val();
		 
		var to_state = $("#to_state").val();
		var address = $("#address").val();
		var from_date = $("#from_date").val();
		var to_date = $("#to_date").val();
		 
		$.post("getFilterformaa_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			reg_state : reg_state,
			to_state : to_state ,
			address :address ,
			from_date : from_date ,
			to_date : to_date
			

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].st_temp_no ,j[i].valid_up_to , j[i].reg_state ,j[i].to_state ,j[i].from_date,j[i].to_date,j[i].address ]);

			}
			
		});
		$.post("getTotalform_aa_dataCount?" + key + "=" + value, {
			Search : Search,
			to_state : to_state ,
			address :address ,
			from_date : from_date ,
			to_date : to_date
		
		}, function(j) {

			FilteredRecords = j;

		});
		
		setTimeout(setTimeLoadForTable, 1000);
		
	}

	function Search() {
		$("#Country_name1").val($('#name').val());
		$("#status1").val($('#status').val());
		document.getElementById('searchForm').submit();
	}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}

	function editData(id) {
		$("#id2").val(id);
		document.getElementById('updateForm').submit();
	}

	function Validation() { 
		if ($("#reg_state").val().trim() == "0") {
			alert("Please Select Registration State");
			$("select#reg_state").focus();
			return false;
		}
		if ($("#to_state").val().trim() == "0") {
			alert("Please Enter Transfer To State.");
			$("select#to_state").focus();
			return false;
		}
		
		if ($("input#address").val().trim() == "") {
			alert("Please Enter Address.");
			$("input#address").focus();
			return false;
		}
		var from_date = $("#from_date").val();
		var to_date = $("#to_date").val();
	 	if(from_date != "DD/MM/YYYY" && to_date !="DD/MM/YYYY"){
			
			if(from_date > to_date ){
				alert("To Date can not be less than From Date.")
				return false ;
			}
		}
		return true;
	}
	
 


function lettersAndSpaceCheck(name)
{
   var regEx = /^[a-z][a-z\s]*$/;
   if(name.value.match(regEx))
     {
      return true;
     }
   else
     {
     alert("Please enter letters and space only.");
     return false;
     }
}   
//////////////////////////////////////////////////////////////////////////
function frm_toFn2() {
	
	var from_date = $("#from_date").val();
	var to_date = $("#to_date").val();
 //  alert("from_date---"+from_date)
// 	alert("to---"+to_date)
 	if(from_date != "DD/MM/YYYY" && to_date !="DD/MM/YYYY"){
		
		if(from_date > to_date ){
			alert("To Date can not be less than From Date.")
			 
		}
		
	}
	 
}

function ValidateEndDate() {
    var startDate = $("from_date").val();
    var endDate = $("to_date").val();
    if (startDate != '' && endDate !='') {
        if (Date.parse(startDate) > Date.parse(endDate)) {
            $("to_date").val('');
            alert("Start date should not be greater than end date");
        }
    }
    return false;
}

/*  function datecompare (obj,from_date) {
	alert("----")
	
	if(from_date.value !=""){
		
		
		var Date1= $("input#from_date").val();  
	 
			if(Date1 !=""){
				var id = obj.id;
				var myDate = document.getElementById(id).value;
				if ((Date.parse(myDate) < Date.parse(Date1)))
						{
					alert('Advertisement Last Date should not be before  Start Date');	
					obj.value = "";
				}
			}				
	}
	else
	{
		alert("Please Select  Start Date first.");
		$("input#from_date").focus();
		obj.value = "";
	}	
}  */
////////CSP start================================================	
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('to_date').onclick = function() {
		frm_toFn2();
	};
});

</Script>


