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
					
				</div>
<!-- 				end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Book Details</li>
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
									<th><h6>Book Title</th>
									<th><h6>Date of Entered</th>
 									<th><h6>Is book Available?</h6></th>
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
<c:url value="getSearch_Book_DetailsUrl" var="searchUrl2" />
<form:form action="${searchUrl2}" method="post" id="searchForm"
	name="searchForm" modelAttribute="searchUrl2">
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_Book_DetailsUrl" var="Edit_Url2" />
<form:form action="${Edit_Url2}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3">
</form:form>

<c:url value="delete_Book_DetailsUrl" var="delete_Book_Details_Url_Action" />
<form:form action="${delete_Room_Url_Action}" method="post"
	id="deleteForm" name="deleteForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4" value="0" />
</form:form>

<c:url value="book_detailsUrlreport2" var="searchUrlo" />
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
	document.getElementById('book_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
		
	};
	 document.getElementById('date_of_entered').onkeypress = function() {
		return Validation();
	}; 
	document.getElementById('is_book_available').onkeypress = function() {
		return Validation();
	};
	
	document.querySelectorAll('.ADDBook_Pojo ').forEach((items, index) => {
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
	var book_name = $("#book_name").val();
	var date_of_entered = $("#date_of_entered").val();
	var is_book_available = $("#is_book_available").val();


	$.post("getFilterBook_details_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		book_name : book_name,
		date_of_entered : date_of_entered,
		is_book_available : is_book_available,


 	}, function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].ser, j[i].book_name,j[i].date_of_entered,j[i].is_book_available,j[i].action]);
		}
	});
 	$.post("getbook_detils_MasterCount?" + key + "=" + value, {
 		Search : Search,
 		book_name  : book_name ,
 		date_of_entered : date_of_entered,
 		is_book_available : is_book_available


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
	$("#book_name1").val($('#book_name').val());
	$("#date_of_entered1").val($('#date_of_entered').val());
	$("#is_book_available1").val($('#is_book_available').val());

	document.getElementById('searchForm').submit();
}
function Validation() {
		if ($("#book_name").val() =="" ) {
			alert("Please Enter Book Name.");
			$("input#book_name").focus();
			return false;
		}
		 if ($("#date_of_entered").val() =="" ) {
			alert("Please Enter Date of Entered");
			$("input#date_of_entered").focus();
			return false;
		} 
		 if ($("#is_book_available").val() =="" ) {
			alert("Please Enter Is Book Available.");
			$("input#is_book_available").focus();
			return false;
		 }
		
		if(confirm("Are you Sure you Want to Submit your Request")){
		return true;}
		else{
		return false;
	} }
</script>		