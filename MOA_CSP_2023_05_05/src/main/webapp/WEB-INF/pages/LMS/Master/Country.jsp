<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->
<script type="text/javascript" src="js\watermark\common.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->


<section class="dashboard-page">
	<div class="container-fluid">

		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							Country Master<span class="text-red font-size12 enter_by"></span>
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Country
									Master</li>
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
					<form:form name="Country" id="Country" action="CountryAction"
						method="post" modelAttribute="CountryCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
							<h6 class="mb-25">Country Master</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Country<span class="mandatory">*</span></label> <input
											type="text" id="name" name="name" minlength="2" maxlength="100"
											class="autocomplete  txt-transupp" autocomplete="off" placeholder="Country" >
									</div>
									<!-- end input onkeypress="return onlyAlphabets(event,this);"-->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status" class="singleselect form-control form-control-lg">
												<c:forEach var="item" items="${getStatusMasterList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<!-- end select -->
								</div>
							</div>
							</div>
<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
							<ul class="buttons-group mainbtn">

								<li><a id="btn-search"
									class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover btnsave" type="submit" value="Save" />
								</li>
								<li><a href="Country" class="main-btn dark-btn n btn-hover btnreset"
									type="button">Reset</a></li>
							</ul>
							</div>
							</div>
							</div>
						</div>

						<!-- end card -->
					
	<section class="single-detail-block">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_Country">
							<thead>
								<tr>
									<th><h6>Sr No</h6></th>
									<th id="2"><h6>Country</h6></th>
									<th><h6>Action</h6></th>
								</tr>
								<!-- 						end table row -->
							</thead>
							<tbody>
							</tbody>
						</table>
						<!-- 				end table -->
					</div>
				</div>
				<!-- 		end card -->
			</div>
			<!-- 	end col -->
		</div>
		</section>
		</form:form>
				</div>
			</div>
		</div>
		
	</div>
</section>

<c:url value="getSearch_Country_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Country_name1">
	<input type="hidden" name="Country_name1" id="Country_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_Country" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="delete_Country" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Countryreport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_Country');
		table = dataTable('search_Country');
		$('#btn-search').on('click', function() {
			table.ajax.reload();
		});

		if ('${list.size()}' == "") {
			$("div#Country_Search").hide();
		}

		if ('${Country_name1}' == "") {
		} else {
			$("#name").val('${Country_name1}');
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

	
	if ('${status1}' != "") {
		$("Select#status").val('${status1}');
	}
	
	document.addEventListener('DOMContentLoaded', function () {	
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

		document.getElementById('btn-search').onclick = function() {
			return Validation();
		};

		document.getElementById('name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		document.getElementById('name').onkeypress = function() {
			return lettersAndSpaceCheck(document.form1.name);
		};
		
		});
	
	
	function setTimeLoadForTable(){
		
		
			
		//checkLength
		
		document.querySelectorAll('.ADDCountry').forEach((items, index) => {
			
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;

				var cid = document.getElementById('CounId'+val).value;
 				
				if (confirm('Are You Sure You Want to Update This Country ?')) {
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
				if (confirm('Are You Sure You Want to Delete Country ?')) {
					deleteData(cdid);
				} else {
					return false;
				}
			})
		});
		
	}

	function data(search_Country) {
		jsondata = [];
		var table = $('#' + search_Country).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var country = $("#name").val();
		var status = $("#status").val();

		$.post("getFiltercountry_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			country : country,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].name,j[i].action ]);

			}
			
		});
		$.post("getTotalcountry_dataCount?" + key + "=" + value, {
			Search : Search,
			country : country
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
		if ($("input#name").val().trim() == "") {
			alert("Please Enter Country.");
			$("input#name").focus();
			return false;
		}
		var an2 =  $("#name").val();
// 		if( an2 == ""){
// 			alert("Enter Country Name");
// 			$("#name").focus();
// 			return false;
// 	   	}
// 		if(   parseInt(an2) <= 0){
// 			alert("Please Enter Valid character");
// 			$("#aadhar_no2").focus();
// 			return false;
// 	   	}
		
		 var minLength = 2;
		 var charLength = an2.length;
	       if(charLength < minLength){
	       	alert("Please Enter Minimum 2 character");
				$("input#name").focus();
				return false;
	       }
	       if ($("select#status").val() == "0") {
				alert("Please Select Status.");
				$("select#status").focus();
				return false;
			}
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status.");
			$("select#status").focus();
			return false;
		}
		return true;
	}
	
	
	
// 	 function onlyAlphabets(e, t) {
//          try {
//              if (window.event) {
//                  var charCode = window.event.keyCode;
//              }
//              else if (e) {
//                  var charCode = e.which;
//              }
//              else { return true; }
//              if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))
//                  return true;
//              else
//                  return false;
//          }
//          catch (err) {
//              alert(err.Description);
//          }
//      }


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

	
</Script>


