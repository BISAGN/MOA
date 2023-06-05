
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<script src="js/miso/commonJS/addmorefunctionality.js" type="text/javascript"></script>
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Book Details</h2>
					</div>
				</div>
	<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12">
					<!-- input style start -->
					<form:form name="edit_book_Url" id="edit_book_Url" action="edit_book_Url_Action"
						method="post" class="form-horizontal" modelAttribute="BookCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Book Details</h6>
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
											type="number" id="book_price" name="book_price"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" min="1"  placeholder="Enter Book Price" />
									</div>
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Book Quantity<span class="mandatory">*</span></label> <input
											type="number" id=total_book_qty name="total_book_qty"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" min="1" max="100" placeholder="Enter Book Quantity" />
									</div>
								</div>
							
								
							
								
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
									<input type="hidden" id="id" name="id" class="form-control"
											value="0" autocomplete="off">
							<ul class="buttons-group mainbtn">
								
								<li><input id="btn-save" class="main-btn info-btn btn-hover" type="submit" value="Update" /></li>
								<li><a href="show_all_booksUrl"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
							</ul>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	
		
	</section>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	datepicketDate('date_of_entered');
	
	$("#book_number").val('${BookCMD.book_number}');
	$("#book_name").val("${BookCMD.book_name}");
	$("#book_author").val("${BookCMD.book_author}"); 
	$("#book_price").val("${BookCMD.book_price}");
	$("#total_book_qty").val("${BookCMD.total_book_qty}");
	$("#book_dept").val("${BookCMD.book_dept}");
	$("#date_of_entered").val(formatDate("${BookCMD.date_of_entered}"));
	
	$("#id").val('${BookCMD.id}');

	
});


//csp----------------------------
document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	
	document.getElementById('book_number').oninput = function() {
		this.value = this.value.toUpperCase();
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
	
	document.getElementById('book_price').onkeydown = function() {
		this.value = this.value.toUpperCase();
	};
	
});

function formatDate (input) {
	  var datePart = input.match(/\d+/g),
	  year = datePart[0].substring(0), 
	  month = datePart[1], day = datePart[2];

	  return day+'/'+month+'/'+year;
	}

	formatDate ('2010/01/18');
	
	function setTimeLoadForTable(){
	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	
	}
 

 function editData(id) {
	
	$("#id3").val(id);
 	document.getElementById('updateForm').submit();
 }

 function deleteData(id) {
	
 	$("#id4").val(id);
	document.getElementById('deleteForm').submit();

 }

function Validation() {
		if ($("#book_number").val() =="" ) {
			alert("Please Enter Book No.");
			$("input#book_number").focus();
			return false;
		}
		
		if ($("#book_name").val() =="" ) {
			alert("Please Enter Book Name.");
			$("input#book_name").focus();
			return false;
		}
		
		if ($("#book_author").val() =="" ) {
			alert("Please Enter Book Author.");
			$("input#book_author").focus();
			return false;
		}
		
		if ($("#book_price").val() =="" ) {
			alert("Please Enter Book Price.");
			$("input#book_price").focus();
			return false;
		}
		
		if (parseInt($("#book_price").val()) < 1 || parseInt($("#book_price").val()) > 5000   ) {
			alert("Please Enter Valid Book Price.");
			$("input#book_price").focus();
		return false;
		} 
		
		if ($("#total_book_qty").val() =="" ) {
			alert("Please Enter Book Quantity.");
			$("input#total_book_qty").focus();
			return false;
		}
		
		 if (parseInt($("#total_book_qty").val()) < 1 || parseInt($("#total_book_qty").val()) > 100   ) {
			alert("Please Enter Valid Book Quantity.");
			$("input#total_book_qty").focus();
			return false;
		} 
		
		 if($("select#book_dept").val() == "0"){
				alert("Please Select Book Course");
				$("select#book_dept").focus();
				return false;
		  } 
	
	
	return true;
} 

function AvoidSpace(event) {
    var k = event ? event.which : window.event.keyCode;
    if (k == 32) return false;  
}

</script>

