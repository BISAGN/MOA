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
<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Return Alloted Books</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Return
									Alloted Books</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>

		<div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_system">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th id="${item.id}"><h6>Member ID</h6></th>
									<th><h6>Member Name</h6></th>
									<th><h6>Book Request Date</h6></th>
									<th><h6>Course</h6></th>
									<th><h6>Book Name</h6></th>
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
	</div>
</section>
<c:url value="getSearch_Return_Book" var="getSearch_Return_Book" />
<form:form action="${getSearch_Return_Book}" method="post"
	id="searchForm" name="searchForm" modelAttribute="searchUrl2">
	<input type="hidden" name="searchUrl2" id="searchUrl2" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="return_book_req" var="return_book_req" />
<form:form action="${return_book_req}" method="post" id="returnForm"
	name="updateForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3">
</form:form>

<c:url value="Return_Book_Requestreport2"
	var="Return_Book_Requestreport2" />
<form:form action="${Return_Book_Requestreport2}" method="post"
	id="search2" name="searcho" modelAttribute="comdo">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
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

	function setTimeLoadForTable() {

		document.querySelectorAll('.returnbook').forEach((items, index)=>{
			items.addEventListener('click', event =>{
				
				var val=parseInt(index)+1;
				var id = document.getElementById('returnID'+val).value;
				if (confirm('Are You Sure You Want to Return Book ?')) {
					ReturnBook(id);
				} else {
					return false;
				}
			})
		});

	}

	function data(search_Student_Room_Request) {

		jsondata = [];
		var table = $('#' + search_Student_Room_Request).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var member_id = $("#member_id").val();
		var book_req_date = $("#book_req_date").val();
		var system_name = $("#system_name").val();
		var book_name = $("#book_name").val();

		$.post("getFilterReturn_Book_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			member_id : member_id,
			book_req_date : book_req_date,
			system_name : system_name,
			book_name : book_name

		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser,j[i].member_p_id, j[i].member_name, j[i].book_req_date,
						j[i].system_name, j[i].book_name,j[i].action ]);
			}
		});
		$.post("getTotalReturn_Book_dataCount?" + key + "=" + value, {
			Search : Search,
			member_id : member_id,
			book_req_date : book_req_date,
			system_name : system_name,
			book_name : book_name

		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	function Search() {
		$("#member_id1").val($('#member_id').val());
		$("#book_req_date1").val($('#book_req_date').val());
		$("#system_name1").val($('#system_name').val());
		$("#book_name1").val($('#book_name').val());

		document.getElementById('searchForm').submit();
	}
	
	
	
	function ReturnBook(id){
		$("#id3").val(id);
		document.getElementById('returnForm').submit();
		
	}
	
	
	function Validation() {
		if ($("#member_id").val() == "") {
			alert("Please Enter Member Id.");
			$("input#hostel_id").focus();
			return false;
		}
		if ($("#book_req_date").val() == "") {
			alert("Please Enter Book Request Date.");
			$("input#room_no").focus();
			return false;
		}
		if ($("#system_name").val() == "") {
			alert("Please Enter System Name.");
			$("input#Amenities").focus();
			return false;
		}
		if ($("#book_name").val() == "") {
			alert("Please Enter Book Name.");
			$("input#no_of_beds").focus();
			return false;
		}

		if (confirm("Are you Sure you Want to Submit your Request")) {
			return true;
		} else {
			return false;
		}
	}
</script>
