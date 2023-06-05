
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.js"></script>
<link rel="stylesheet" href="js/assets/collapse/1collapse.css">
<link rel="stylesheet" href="assets/db_css/db_custom_style.css">
<script src="js/miso/commonJS/addmorefunctionality.js" type="text/javascript"></script>
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
</head>
<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Book Details</h2>
					</div>
				</div>
				</div>
				</div>
			
	<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12">
					<!-- input style start -->
					<form:form name="show_all_booksUrl" id="show_all_booksUrl" action="show_all_booksAction"
						method="post" class="form-horizontal" modelAttribute="BookCMD">
						<div class="card-style mb-30">
							<div class="row">
							  <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Book No.<span class="mandatory">*</span></label>
										<input
											type="text" id="book_number" name="book_number"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="100" placeholder="Enter Book No." />
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Book Name<span class="mandatory">*</span></label> <input
											type="text" id="book_name" name="book_name"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="100" placeholder="Enter Book Name" />
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Book Author<span class="mandatory">*</span></label> <input
											type="text" id="book_author" name="book_author"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="100" placeholder="Enter Book Author" />
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Book Price<span class="mandatory">*</span></label> <input
											type="text" id="book_price" name="book_price"
											class="autocomplete UpperClassName txt-transupp" 
											autocomplete="off"  placeholder="Enter Book Price" />
									</div>
								</div>
								
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Book Quantity<span class="mandatory">*</span></label> <input
											type="text" id=total_book_qty name="total_book_qty"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off"  placeholder="Enter Book Quantity" />
									</div>
								</div>
								
								
<!-- 								<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 									<div class="input-style-2"> -->
<!-- 									<label class=" form-control-label">Date of Entered<strong class="mandatory">*</strong></label></label>  -->
<!-- 										<input type="text" name="date_of_entered" id="date_of_entered" maxlength="10" -->
<!-- 										class="form-control-sm form-control effect-9 " -->
<!-- 										aria-required="true" autocomplete="off" -->
<!-- 										value="DD/MM/YYYY"> -->
<!-- 								</div> -->
<!-- 								</div> -->
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="select-style-1">
												<label for="username">Book Course<span
													class="mandatory">*</span></label>
												<div class="select-position">
													<select name="book_dept" id="book_dept"> 
														<option value="0" selected="selected" name="select">--Select--</option>
													
														<c:forEach var="item" items="${systemList}" varStatus="num">
															<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>

														</c:forEach>
													</select>
												</div>
											</div>

										</div>
							</div>
							<!-- <ul class="buttons-group mainbtn">
								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input id="btn-save" class="main-btn info-btn btn-hover" type="submit" value="Save" /></li>
								<li><a href="show_all_booksUrl"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
							</ul> -->
							
								<ul class="buttons-group mainbtn">

									<li><input id="btn-save"
										class="main-btn info-btn btn-hover" type="submit"
										onclick="return Validation();" value="Save" /></li>
									<li><a href="show_all_booksUrl"
										class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
								</ul>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	
		 <div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_all_books">
							<thead>
								<tr>
									<th id= "1"><h6>Ser No</h6></th>
									<th id= "2"><h6>Book No.</h6></th>
									<th id= "3"><h6>Book Name</h6></th>
									<th id= "4"><h6>Book Author</h6></th>
									<th id= "5"><h6>Book Price</h6></th>
									<th id= "6"><h6>Book Quantity</h6></th>
<!-- 									<th id= "7"><h6>Date of Entered</h6></th> -->
									<th id= "8"><h6>Book Course</h6></th>
									<th><h6>Action</h6></th>

								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		</div>
	</section>
<c:url value="edit_book_Url" var="edit_book_Url" />
<form:form action="${edit_book_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3">
</form:form>

<c:url value="delete_book" var="delete_book" />
<form:form action="${delete_book}" method="post"
	id="deleteForm" name="deleteForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4" value="0" />
</form:form>
<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	datepicketDate('date_of_entered');
	mockjax1('search_all_books');
	table = dataTable('search_all_books');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$('.UpperClassName').keyup(function() {
		this.value = this.value.toUpperCase();
		
	});
	
});

//csp----------------------------
document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	
	document.getElementById('book_number').onkeydown = function() {
		return AvoidSpace(event);
	};

	document.getElementById('book_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this,event);
	};
	
	document.getElementById('book_author').oninput = function() {
		this.value = this.value.toUpperCase();
	};
	 
	document.getElementById('book_price').onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	
});

function setTimeLoadForTable(){
	

	document.querySelectorAll('.ADDBooks ').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('apIdAGE'+val).value;
			var hapbook = document.getElementById('apbook'+val).value;
			var hapbook_n = document.getElementById('apbook_n'+val).value;
			var hapbook_a = document.getElementById('apbook_a'+val).value;
			var hapbook_p = document.getElementById('apbook_p'+val).value;
			var hapbook_q = document.getElementById('apbook_q'+val).value;
			var hapbook_date = document.getElementById('apbook_date'+val).value;
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				editData(hid,hapbook,hapbook_n,hapbook_a,hapbook_p,hapbook_q,hapbook_date);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.deleteOnclick').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('deleteID'+val).value;
			
			if (confirm('Are You Sure You Want to Delete Detail ?')) {
				deleteData(hid);
			} else {
				return false;
			}
		})
	});
}

function data(search_all_books) {
	
	jsondata = [];
	var table = $('#' + search_all_books).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).attr('id')
			.toLowerCase();
	var orderType = order[0][1];
	var book_number = $("#book_number").val();
	var book_name = $("#book_name").val();
	var book_author = $("#book_author").val();
	var book_price = $("#book_price").val();
	var total_book_qty = $("#total_book_qty").val();
// 	var date_of_entered = $("#date_of_entered").val();
	var book_dept = $("#book_dept").val();

	$.post("getFilterall_books_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		book_number : book_number,
		book_name : book_name,
		book_author : book_author,
		book_price : book_price,
		total_book_qty : total_book_qty,
// 		date_of_entered : date_of_entered,
		book_dept : book_dept
		
		

	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].ser,j[i].book_number, j[i].book_name, j[i].book_author, j[i].book_price, j[i].total_book_qty,j[i].book_dept, j[i].action ]);
		}
	});
	$.post("getTotalBookMstr_dataCount?" + key + "=" + value, {
		Search : Search,
		book_number : book_number,
		book_name : book_name,
		book_author : book_author,
		book_price : book_price,
		total_book_qty : total_book_qty,
// 		date_of_entered : date_of_entered,
		book_dept : book_dept
	}, function(j) {
		FilteredRecords = j;
	});
	setTimeout(setTimeLoadForTable, 1000);
}

function editData(id) {
	
	$("#id3").val(id);
	document.getElementById('updateForm').submit();
}

function deleteData(id) {
	
	$("#id4").val(id);
	document.getElementById('deleteForm').submit();
}

function Search() {
	$("#book_number1").val($('#book_number').val());
	$("#book_name1").val($('#book_name').val());
	$("#book_author1").val($('#book_author').val()); 
	$("#total_book_qty1").val($('#total_book_qty').val()); 
	//$("#date_of_entered1").val($('#date_of_entered').val());
	$("#book_dept1").val('$book_dept').val();
	

	document.getElementById('searchForm').submit();
} 
 
 
//  function Validation() {
	 
// 		if ($("#book_number").val() =="" ) {
// 			alert("Please Enter Book No.");
// 			$("input#book_number").focus();
// 			return false;
// 		}
		
// 		if ($("#book_name").val() =="" ) {
// 			alert("Please Enter Book Name.");
// 			$("input#book_name").focus();
// 			return false;
// 		}
		
// 		if ($("#book_author").val() =="" ) {
// 			alert("Please Enter Book Author.");
// 			$("input#book_author").focus();
// 			return false;
// 		}
// 		if ($("#book_price").val() =="" ) {
// 			alert("Please Enter Book Price.");
// 			$("input#book_price").focus();
// 			return false;
// 		}
// 		if (parseInt($("#book_price").val()) < 1 || parseInt($("#book_price").val()) > 30000   ) {
// 				alert("Please Enter Valid Book Price.");
// 				$("input#book_price").focus();
// 			return false;
// 			}
		
// 		if ($("#total_book_qty").val() =="" ) {
// 			alert("Please Enter Book Quantity.");
// 			$("input#total_book_qty").focus();
// 			return false;
// 		}
		

// 		if (parseInt($("#total_book_qty").val()) < 1 || parseInt($("#total_book_qty").val() > 500)   ) {
// 				alert("Please Enter Valid Book Quantity.");
// 				$("input#total_book_qty").focus();
// 				return false;
// 			}
		
		 
// 		 if($("select#book_dept").val() == "0"){
// 				alert("Please Select Book Course");
// 				$("select#book_dept").focus();
// 				return false;
// 		  } 
	
	
// 	return true;
// } 
 
 function AvoidSpace(event) {
	    var k = event ? event.which : window.event.keyCode;
	    if (k == 32) return false;  
	}


</script>
