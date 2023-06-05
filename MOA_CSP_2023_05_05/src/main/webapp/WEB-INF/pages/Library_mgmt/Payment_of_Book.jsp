<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

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
<script type="text/javascript" src="js\watermark\common.js"></script>



<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>


<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Library Management System</h2>
					</div>
				</div>
		</div> 
			</div>
			<!-- end row -->
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12">
					<!-- input style start -->
					<form:form name="" id="Member_Book_Req_Action"
						action="Member_Book_Req_Action" method="post"
						class="form-horizontal" modelAttribute="BookReqCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">

							<div class="row">
								<div class="title mb-30">
									<h2>Payment of Book</h2>
								</div>

								<!-- <div class="col-12 col-sm-12 col-md-6 col-lg-4">

									<div class="input-style-1">
										<label>Member ID<span class="mandatory">*</span></label> <input
											type="text" id="member_id" name="member_id"
											placeholder="Enter Member Id" value=""
											onchange="getMemberDetails();">
										<div class="note-text">
											<span class="mandatory">Please Enter Valid Member ID</span>
										</div>
									</div>


									end select
								</div> -->
								<!-- <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Member Name<span class="mandatory">*</span></label> <input
											type="text" id="member_name" name="member_name"
											placeholder="Enter Member Id" readonly="readonly">
									</div>
									end input
								</div> -->

								<%-- <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Member Course<span
											class="mandatory">*</span></label>
										<div class="select-position">
											<select name="member_dept" id="member_dept">
												<option value="0" selected="selected" name="select">--Select--</option>

												<c:forEach var="item" items="${systemList}" varStatus="num">
													<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>

												</c:forEach>
											</select>
										</div>
									</div>

								</div>
								 --%>
								

									<!-- <div class="col-md-4">
												<div class="input-style-2">
											<label class=" form-control-label">Member Joined Date</label>
															<label  class=" form-control-label"
																name="member_joined_date" id="member_joined_date" ></label>
															
														</div>
										

											
										<input type="date" name="appointment_date" id="appointment_date" class="form-control autocomplete"  
										onclick="date_time1();"
										autocomplete="off"/>
										</div>
 -->


								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Book Name</label> <label class=" form-control-label" id="book_list" name="book_list"> </label>
									</div>
								</div>
								
								<div class="col-md-4">
												<div class="input-style-2">
											<label class=" form-control-label">Book Purchased Date</label>
															<label  class=" form-control-label"
																name="book_purchased_date" id="book_purchased_date" ></label>
															
														</div>
										

											<!-- 
										<input type="date" name="appointment_date" id="appointment_date" class="form-control autocomplete"  
										onclick="date_time1();"
										autocomplete="off"/> -->
										</div>


								<!-- <div class="col-md-4">
									<div class="input-style-2">
										<label class=" form-control-label">Book Purchased Date</label>
										</strong></label> <input type="text" name="book_purchased_date" id="book_purchased_date"
											maxlength="10" class="form-control-sm form-control effect-9 "
											aria-required="true" autocomplete="off" value="DD/MM/YYYY">

									</div>


									
										<input type="date" name="appointment_date" id="appointment_date" class="form-control autocomplete"  
										onclick="date_time1();"
										autocomplete="off"/>
								</div> -->



								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Book Price<span class="mandatory">*</span></label> <input
											type="number" id="book_price" name="book_price"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="100"
											placeholder="Enter Book Price" />
									</div>
								</div>



							</div>

							<ul class="buttons-group mainbtn">

								<!-- 								<li><a id="btn-reload" -->
								<!-- 									class="main-btn secondary-btn btn-hover btn-iconic-icon" -->
								<!-- 									type="button"><i class="lni lni-search-alt"></i>Search</a></li> -->
								<li><input id="btn-save"
									class="main-btn success-btn btn-hover btn-iconic-icon btnpay" type="submit"
									value="Payment" /></li>
<!-- 								<li><a id="btn-reset" href="payment_req" -->
<!-- 									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li> -->
							</ul>
						</div>


					<input type="hidden" id="multiSelect_Book" name="multiSelect_Book" />
					<input type="hidden" id="book_charges" name="book_charges" />
					<input type="hidden" id="member_id" name="member_id" />


					<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>

		<!-- 		<div class="row"> -->
		<!-- 			<div class="col-12"> -->
		<!-- 				<div class="card-style mb-30"> -->
		<!-- 					<div class="table-wrapper table-responsive custom-datatable-p"> -->
		<!-- 						<table class="table" id="search_system"> -->
		<!-- 							<thead> -->
		<!-- 								<tr> -->
		<!-- 									<th><h4>Ser No</h4></th> -->
		<%-- 									<th id="${item.id}"><h6>Hostel Name</h6></th> --%>
		<!-- 									<th><h6>Registration No.</h6></th> -->
		<!-- 									<th><h6>Room No.</h6></th> -->
		<!-- 									<th><h6>Amenities</h6></th> -->
		<!-- 									<th><h6>Rent</h6></th> -->
		<!-- 									<th><h6>Action</h6></th> -->

		<!-- 								</tr> -->
		<!-- 														end table row -->
		<!-- 							</thead> -->
		<!-- 							<tbody> -->
		<!-- 							</tbody> -->
		<!-- 						</table> -->
		<!-- 										end table -->
		<!-- 					</div> -->
		<!-- 				</div> -->
		<!-- 						end card -->
		<!-- 			</div> -->
		<!-- 			<!-- 	end col -->
		<!-- 		</div>  -->
	</div>
</section>
<c:url value="getSearch_Student_Room_Request" var="searchUrl3" />
<form:form action="${searchUrl3}" method="post" id="searchForm"
	name="searchForm" modelAttribute="searchUrl3">
	<input type="hidden" name="searchUrl3" id="searchUrl3" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_Student_Room_RequestUrl"
	var="Edit_Student_Room_RequestUrl" />
<form:form action="${Edit_Student_Room_RequestUrl}" method="post"
	id="updateForm" name="updateForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4">
</form:form>

<c:url value="delete_Url_Action1" var="delete_Url_Action1" />
<form:form action="${delete_Url_Action1}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id5">
	<input type="hidden" name="id5" id="id5" value="0" />
</form:form>

<c:url value="Student_Room_Requestreport2" var="searchUrl4" />
<form:form action="${searchUrl4}" method="post" id="search3"
	name="search2" modelAttribute="comd2">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>
<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {
	
	
	
	datepicketDate('book_purchased_date');
// 	datepicketDate('member_joined_date');

	
	$("#member_id").val("${LibCMD[0].member_id}");
	$("#member_name").val("${LibCMD[0].member_name}");

	$("#member_dept").val("${LibCMD[0].member_dept}");

	var member_joined_date = "${LibCMD[0].member_joined_date}";
	$("#member_joined_date").text(member_joined_date.substring(0,10));
	var today = new Date();
	$("#book_purchased_date").text(today.getDate()+"-"+(today.getMonth()+1)+"-"+today.getFullYear());
	
	
	
	$("#book_price").val("${book_price}");
	$("#book_price").attr('disabled', "true");
	$("#book_charges").val("${book_price}")
	
	
	
	var book_list = "${book_list}";
	$("#multiSelect_Book").val("${book_list}");
	const book_list_array = book_list.split(",");
	var count = 0;
	var book_list_name="";
	<c:forEach var="item" items="${BookList}" varStatus="num">

	var j = "${item.id}";
	var book_name = "${item.book_name}";

	if(book_list_array[count] == j){
		book_list_name = book_list_name + book_name +" , " ;
		count++;
	}
	
</c:forEach>

	$("#book_list").text(book_list_name.substring(0,book_list_name.length - 1));
	
	$(".multiselect").toggle();
	
	$(".mandatory").hide();

	$(".multiselect").hide();

	$("#book_select").click(function(){
		$(".multiselect").toggle();

	});
	
	
	
	
	
	
	
	
	
// 	$('#book_select').live('click', function(e) {  
// 		$(".multiselect").toggle();
//     });
	
	
	mockjax1('search_system');
	table = dataTable('search_system');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$('.UpperClassName').keyup(function() {
		this.value = this.value.toUpperCase();
	});
	
	if('${studentdtlCMD}' != null && '${studentdtlCMD}' != ""){
		
		$("#hostel_id").val('${studentdtlCMD.hostel_id}');
		$("#hostel_id").attr('disabled', "true");
		setRoomNo();
		$("#room_no").attr('disabled', "true");


		$("select#Amenities").val('${studentdtlCMD.amenities}');
		$("#Amenities").attr('disabled', "true");

		$("input#rent").val('${studentdtlCMD.rent}');
		$("#rent").attr('disabled', "true");

		$("#registration_no").val('${studentdtlCMD.registration_no}');
		$("#registration_no").attr('disabled', "true");

		$("#btn-save").hide();
		$("#btn-reset").hide();

	}
	
	
});


function setTimeLoadForTable(){
	
	document.getElementById('btn-save').onclick = function() {
		
	};
	document.getElementById('hostel_id').onkeypress = function() {
		
	};
	document.getElementById('room_no').onkeypress = function() {
		
	};
	/* document.getElementById('Amenities').onkeypress = function() {
		return Validation();
	}; */
	/* document.getElementById('no_of_beds').onkeypress = function() {
		
	}; */
	document.getElementById('rent').onkeypress = function() {
		
	};
document.getElementById('registration_no').onkeypress = function() {
		
	};
	
	document.querySelectorAll('.ADDStudent_Room_Request ').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('apIdAGE'+val).value;
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				editData(hid);
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

// function data(search_Student_Room_Request ) {
	
// 	jsondata = [];
// 	var table = $('#' + search_Student_Room_Request ).DataTable();
// 	var info = table.page.info();
// 	var pageLength = info.length;
// 	var startPage = info.start;
// 	var endPage = info.end;
// 	var Search = table.search();
// 	var order = table.order();
// 	var orderColunm = $(table.column(order[0][0]).header()).html()
// 			.toLowerCase();
// 	var orderType = order[0][1];
// 	var hostel_id = $("#hostel_id").val();
// 	var room_no = $("#room_no").val();
// /* 	var Amenities = $("#Amenities").val();
// /*  */	
 
//   var no_of_beds = $("#no_of_beds").val();
//  	var rent = $("#rent").val();
// 	var registration_no = $("#registration_no").val();



// 	$.post("getFilterStudent_Room_Request_data?" + key + "=" + value, {
// 		startPage : startPage,
// 		pageLength : pageLength,
// 		Search : Search,
// 		orderColunm : orderColunm,
// 		orderType : orderType,
// 		hostel_id : hostel_id,
// 		room_no : room_no,
// /* 		Amenities :Amenities,
//  */		
 
// /*  		no_of_beds : no_of_beds,
//  */ 		
 		
// 		rent : rent,
// 		registration_no : registration_no


// 	}, function(j) {
// 		for (var i = 0; i < j.length; i++) {
// 			jsondata.push([j[i].ser, j[i].hostel_name,j[i].registration_no,j[i].room_no,j[i].amenities,j[i].rent,j[i].action]);
// 		}
// 	});
// 	$.post("getTotalStudent_Room_Request_dataCount?" + key + "=" + value, {
// 		Search : Search,
// 		hostel_id  : hostel_id ,
// 		room_no :room_no,
// /* 		Amenities :Amenities,
//  */		no_of_beds :no_of_beds,
// 		rent :rent,
// 		registration_no :registration_no


// 	}, function(j) {

// 		FilteredRecords = j;

// 	});
// 	setTimeout(setTimeLoadForTable, 1000);
// }


// function editData(id) {

// 	$("#id4").val(id);
// 	document.getElementById('updateForm').submit();
// }

// function deleteData(id) {
	
// 	$("#id5").val(id);
// 	document.getElementById('deleteForm').submit();
// }

// function Search() {
// 	$("#hostel_id1").val($('#hostel_id').val());
// 	$("#room_no1").val($('#room_no').val());
// /* 	$("#Amenities1").val($('#Amenities').val());
//  */	$("#no_of_beds1").val($('#no_of_beds').val());
// 	$("#rent1").val($('#rent').val());
// 	$("#registration_no").val($('#registration_no').val());

// 	document.getElementById('searchForm').submit();
// }


// function Validation() {
// 		if ($("#member_id").val() =="" ) {
// 			alert("Please Enter Member Id.");
// 			$("input#member_id").focus();
// 			return false;
// 		}
		
// 		if ($("#member_name").val() =="" ) {
// 			alert("Please Enter Member Name.");
// 			$("input#member_name").focus();
// 			return false;
// 		}
	
		
// 		if($("select#member_dept").val() == "0"){
// 			alert("Please Select Member Course");
// 			$("select#member_dept").focus();
// 			return false;
// 	  	}
		
	
		
		
	
		
		

// 	return true;
// }  

	
	
function getBookPrice(obj){
	var book_id = $("#"+obj.id).val();
	
// 	alert(book);
	
	
	$
	.post(
			"getBookPrice?" + key + "=" + value,
			{
				book_id : book_id
			},
			function(j) {
				var charges = $("#book_charges").val();

				if($("#"+obj.id).is(':checked')){
				charges = parseInt(charges) + parseInt(j[0].book_price/j[0].total_book_qty);
				$("#book_charges").val(charges);
				$("label#book_price").text(charges);

				}else{					
					if(charges != 0){
						charges = charges - parseInt(j[0].book_price/j[0].total_book_qty);
						$("#book_charges").val(charges);
						$("label#book_price").text(charges);

					}

				}
			});
	
	

	
}


	
	function getMemberDetails(){
		var member_id = $("#member_id").val();
		
		$
		.post(
				"getMemberDetails?" + key + "=" + value,
				{
					member_id : member_id
				},
				function(j) {
					if(j.length > 0){
						$("#member_name").val(j[0].member_name);
						$(".mandatory").hide();

					}else{
						$(".mandatory").show();

					}
				});
		
	}
	
	
</script>
