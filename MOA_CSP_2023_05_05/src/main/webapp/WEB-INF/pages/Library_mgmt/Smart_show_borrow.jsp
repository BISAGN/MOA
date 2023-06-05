<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<script type="text/javascript" src="js\watermark\common.js"></script>
<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Book Borrow and Return Details</h2>
					</div>
				</div>
<!-- 				end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Book Borrow and Return Details</li>
							</ol>
						</nav>
					</div>
				</div>
<!-- 				end col -->
			</div>
<!-- 			end row -->
		</div>
		
		<div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_system">
							<thead>
								<tr>
									<th><h6>Ser No.</th>
									<th><h6>Member Name</th>
									<th><h6>List of Books</th>
									<th><h6>Date of Borrow</th>
									<th><h6>Date of Return</th>
 									<th><h6>Action</h6></th>
									
								</tr>
<!-- 														end table row -->
							</thead>
							<tbody>
							</tbody>
						</table>
<!-- 										end table -->
					</div>
				</div>
<!-- 						end card -->
			</div>
<!-- 				end col -->
		</div>
	</div>
</section>
<c:url value="getSearch_show_all_membersUrl" var="searchUrl2" />
<form:form action="${searchUrl2}" method="post" id="searchForm"
	name="searchForm" modelAttribute="searchUrl2">
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_show_all_membersUrl" var="Edit_Url2" />
<form:form action="${Edit_Url2}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3">
</form:form>

<c:url value="delete_show_all_membersUrl" var="delete_Room_Url_Action" />
<form:form action="${delete_Room_Url_Action}" method="post"
	id="deleteForm" name="deleteForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4" value="0" />
</form:form>

<c:url value="show_all_membersUrlreport2" var="searchUrlo" />
<form:form action="${searchUrlo}" method="post" id="search2"
	name="searcho" modelAttribute="comdo">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce='r02122i021210p215a12455l12411' type="text/javascript">
$(document).ready(function() {

	
	mockjax1('search_system');
	table = dataTable('search_system');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$('.UpperClassName').keyup(function() {
		this.value = this.value.toUpperCase();
	});
});


function setTimeLoadForTable(){
	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	document.getElementById('member_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
		
	};
	 document.getElementById('state_name').onkeypress = function() {
		return Validation();
	}; 
	document.getElementById('district_name').onkeypress = function() {
		return Validation();
	};
	document.getElementById('city_name').onkeypress = function() {
		return Validation();
	};
	document.getElementById('member_phone_number').onkeypress = function() {
		return Validation();
	}; 
	document.getElementById('member_joined_date').onkeypress = function() {
		return Validation();
	};
	document.getElementById('member_taken_book').onkeypress = function() {
		return Validation();
	};
	document.getElementById('member_return_book').onkeypress = function() {
		return Validation();
	};
	document.getElementById('member_fee').onkeypress = function() {
		return Validation();
	};
	document.getElementById('member_books_quantity').onkeypress = function() {
		return Validation();
	};
	document.getElementById('member_dept').onkeypress = function() {
		return Validation();
	};
	document.getElementById('member_age').onkeypress = function() {
		return Validation();
	};
	
	document.querySelectorAll('.ADDMember_Pojo ').forEach((items, index) => {
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
			
			if (confirm('Are You Sure You Want to Reject Detail ?')) {
				RejectData(hid);
			} else {
				return false;
			}
		})
	});
}

function data(search_system) {
	debugger;
	jsondata = [];
	var table = $('#' + search_system ).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];
	var member_name = $("#member_name").val();
	/* var member_address = $("#member_address").val(); */
	/* var member_phone_number = $("#member_phone_number").val(); */
	var member_joined_date = $("#member_joined_date").val();
	/* var member_taken_book = $("#member_taken_book").val();
	var member_return_book = $("#member_return_book").val();
	var member_fee = $("#member_fee").val();
	var member_books_quantity = $("#member_books_quantity").val();
	var member_dept = $("#member_dept").val();
	var member_gender = $("#member_gender").val();
	var member_age = $("#member_age").val(); */


	$.post("getFiltershow_all_member_Master_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		member_name : member_name,
/* 		member_address : member_address, */
		/* member_phone_number : member_phone_number, */
		member_joined_date : member_joined_date,
		/* member_taken_book : member_taken_book,
		member_return_book : member_return_book,
		member_fee : member_fee,
		member_books_quantity : member_books_quantity,
		member_dept : member_dept,
		member_gender : member_gender,
		member_age : member_age */


 	}, function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].ser, j[i].member_name,j[i].member_joined_date,j[i].action]);
		}
	});
 	$.post("getTotalApproveStudent_Room_Request_dataCount?" + key + "=" + value, {
 		Search : Search,
 		member_name  : member_name ,
 		member_joined_date :member_joined_date,


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
	$("#member_name1").val($('#member_name').val());
/* 	$("#member_address1").val($('#member_address').val()); */
	/* $("#member_phone_number1").val($('#member_phone_number').val()); */
	$("#member_joined_date1").val($('#member_joined_date').val());
	/* $("#member_taken_book1").val($('#member_taken_book').val());
	$("#member_return_book1").val($('#member_return_book').val());
	$("#member_fee1").val($('#member_fee').val());
	$("#member_books_quantity1").val($('#member_books_quantity').val());
	$("#member_dept1").val($('#member_dept').val());
	$("#member_gender1").val($('#member_gender').val());
	$("#member_age1").val($('#member_age').val()); */
	

	document.getElementById('searchForm').submit();
}
function Validation() {
		if ($("#hostel_id").val() =="" ) {
			alert("Please Enter Hostel Id.");
			$("input#hostel_id").focus();
			return false;
		}
		 if ($("#room_no").val() =="" ) {
			alert("Please Enter Room No.");
			$("input#room_no").focus();
			return false;
		} 
		 if ($("#Amenities").val() =="" ) {
			alert("Please Enter Amenities.");
			$("input#Amenities").focus();
			return false;
		} 
		 if ($("#no_of_beds").val() =="" ) {
			alert("Please Enter No Of Beds.");
			$("input#no_of_beds").focus();
			return false;
		} 
		if ($("#rent").val() =="" ) {
			alert("Please Enter No Of Beds.");
			$("input#no_of_beds").focus();
			return false;
		}
		if ($("#registration_no").val() =="" ) {
			alert("Please Enter Registration No.");
			$("input#registration_no").focus();
			return false;
		}
		
		if(confirm("Are you Sure you Want to Submit your Request")){
		return true;}
		else{
		return false;
	} }
</script>		