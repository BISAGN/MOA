<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />

<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="js/InfiniteScroll_old/css/site.css">

<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<style>

.s010 form {
    width: 100%;
    max-width: 100%;
    margin: 0;
}.s010 {
    /* min-height: 53vh !important; */
    display: -ms-flexbox;
    display: flex;
    -ms-flex-pack: center;
    justify-content: center;
    -ms-flex-align: center;
    align-items: center;
    background: transparent;
    padding: 15px;
    font-family: 'Lato', sans-serif;
}
.s010 form .inner-form .input-field input {
    background: linear-gradient(to right, #175658 0%, #175658 28%, #2e9970 91%, #2e9970 100%);
}
.s010 form .inner-form .basic-search:hover{
   transform: scale(1.01);
}
.inner-form label {
    display: inline-block;
    color: #175658;
    font-weight: bold;
}
.inner-form .form-control {
    color: #175658;
    border: 2px solid #175658;
}
.advance-search input::placeholder {
    color: #175658 !important;
}
.input-field input::placeholder {
  color: #fff !important;
  font-weight: bold !important;
}
.s010 form .inner-form .input-field .btn-search {
/*  background: linear-gradient(to right, #175658 0%, #175658 28%, #2e9970 91%, #2e9970 100%); */
}
.btn-select {
    background-color: #175658;
}
.btn-select:before {
    position: absolute;
    content: "";
    border-top: 5px solid #1f6e61;
    border-left: 5px solid #257d66;
    border-right: 5px solid #2e9970;
    border-bottom: 5px solid #2d976f;
    top: 0px;
    right: 0px;
    bottom: 0px;
    left: 0px;
    transition: 0.5s;
}
.btn-select:hover {
    box-shadow: 5px 5px #175658, -5px -5px #2e9970;
    cursor: pointer;
}
 .btn-select {
    min-width: max-content;
    position: relative;
    display: inline-block;
    text-align: center;
    text-transform: uppercase;
    padding: 8px 15px;
    margin: 0px 2%;
    font-size: 120%;
    font-weight: 600;
    color: #ecf0f1;
    transition: 0.5s;
    border: 0;
}

.btn-select:hover::before {
	border: 0px;
}
.section-heading {
    background: #2e9970;
    width: fit-content;
    margin: auto;
    padding: 5px 20px;
    color: white;
    box-shadow: 0px 0px 10px #175658;
}
.section-heading h2 {
    font-weight: bold;
}
.form-control:focus {
    box-shadow: 0 0 0 0.25rem rgb(46 153 112 / 47%);
}
.container {
	min-width: 90%;
}
#container-table {
	box-shadow: 3px 2px 5px 5px #d8dadd;
	margin-bottom: 25px;
	padding: 10px 10px;
}
.dataTables_scrollHeadInner {
    width: calc( 100% - 12px ) !important;
}
table.table{
    /* min-width: 100% !important; */
    width: 100% !important;
}
table td, table th {
    width: 372px !important;
}
.dataTables_scroll table thead {
   width: calc( 100% - 12px ) !important;
}
td.sorting_1 {
    TEXT-ALIGN: center;
}
td:nth-child(4) {
  width: 100%;
  text-align: -webkit-center;
  
}
.dataTables_scrollBody> table#search_Subcategory {
    overflow: scroll;
}

</style>

<script>
// 	var username = "${username}";
</script>

<form:form action="Categorysub_action" method="POST" class="form-horizontal"
	modelAttribute="Categorysub_cmd">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header title">
				<h5>
					<span id="lbladd"></span><b>SUB POLICY CATEGORY MASTER</b>
				</h5>
			</div>
						<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
								<div class="col-md-6">
						
										<div class="col-md-6">
								<label for="username">POLICY CATEGORY<span class="star_design"></span> <span style="color:#ff0000">*</span> </label>
										</div>
										<div class="col-md-6">

								<select name="policycategory" class="form-control"
									id="policycategory">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${PoicyCategoryList}"
										varStatus="num">
										<option value="${item.id}">${item.policycategory}</option>
									</c:forEach>
								</select>
										</div>
							
								</div>
						<div class="row form-group">
													
							
							<div class="col-md-6">
								<label for="username">SUB POLICY CATEGORY<span class="star_design"></span> <span style="color:#ff0000">*</span> </label>
							</div>
							<div class="col-md-6">
								<input id="subcategory" name="subcategory"
									class="form-control" autocomplete="off" maxlength="250"
									 />
								<div class="col-md-6">
									<input type="hidden" id="id" name="id" value="0" class="form-control"
										autocomplete="off" />
								</div>
							</div>
	
					</div>
				
					</div>
					<br><br><br>
					<div class="col-md-12">
								<div class="col-md-6">
						
											<div class="col-md-6">
								<label for="username">STATUS<span class="star_design"></span> <span style="color:#ff0000">*</span> </label>
							</div>
										<div class="col-md-6">

								<select name="status" class="form-control"
									id="status">
									<c:forEach var="item" items="${ActiveInActiveList}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>

								</select>
								<div class="col-md-6">
									<input type="hidden" id="id" name="id" value="0" class="form-control"
										autocomplete="off" />
								</div>
									</div>
							
								</div>
					
				
					</div>
					<br><br><br>
					
<!-- 					<div class="col-md-12"> -->
				
<!-- 					<div class="col-md-6 left_content"> -->
<!-- 						<div class="row form-group"> -->
<!-- 							<div class="col-md-6"> -->
<!-- 								<label for="username">STATUS<span class="star_design"></span></label> -->
<!-- 							</div> -->
<!-- 							<div class="col-md-6"> -->
<!-- 								<select name="status" id="status" class="form-control"> -->
<!-- 												<option value="1">Active</option> -->
<!-- 												<option value="2">Inactive</option> -->
<!-- 								</select> -->
<!-- 								<div class="col-md-6"> -->
<!-- 									<input type="hidden" id="id" name="id" value="0" class="form-control" -->
<!-- 										autocomplete="off" /> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
				
<!-- 			</div> -->
					</div>
				
				
			<div class="card-footer" align="center">
				<a href="subcategory_url" class="btn-clear">Reset</a>
				<input type="submit" class="btn-save" value="Save"
					onclick="return Validation();"> <i
					class="action_icons searchButton"></i><input type="button"
					class="btn-search" id="btn-reload" value="Search">
			</div>
		
	</div>
	</div>
	</div>
</form:form>


<div class="container">
	<table id="search_Subcategory"
		class="display table no-margin table-striped  table-hover  table-bordered">
		<thead>
			<tr>
				<th align="center">SER NO</th>
					<th id= "${item.id}">POLICY CATEGORY</th>
				<th id="2">SUB POLICY CATEGORY</th>
				<th class="action">ACTION</th>
			</tr>
		</thead>
		
		<tbody >
		
		</tbody >
		
	</table>
</div>


<c:url value="Edit_subcategoryUrl" var="Edit_subcategoryUrl" />
<form:form action="${Edit_subcategoryUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="deletesubcategory_Url" var="deletesubcategory_Url" />
<form:form action="${deletesubcategory_Url}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>


<script>
	
	
	$(document).ready(function() {

		mockjax1('search_Subcategory');
		table = dataTable('search_Subcategory');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});

	});

	function data(search_Subcategory) {
		//debugger;
		jsondata = [];
		var table = $('#' + search_Subcategory).DataTable();
		var info = table.page.info();
// 		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];

		var subcategory = $("#subcategory").val();
		var status = $("#status").val();
		var policycategory = $("#policycategory").val();


		$.post("getFiltersubcategory_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			subcategory:subcategory,
			status:status,
			policycategory:policycategory
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ i + 1,j[i].policycategory, j[i].subcategory,j[i].action ]);
			}
		});
		$.post("getTotalsubcategory_dataCount?" + key + "=" + value, {
			subcategory:subcategory,policycategory:policycategory
		}, function(j) {
			FilteredRecords = j;

			

			});
		
	}
function editData(id) {
		
		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}
	
function deleteData(id) {
	$("#id2").val(id);
	document.getElementById('deleteForm').submit();
}


function Validation(){
	if ($("#policycategory").val() == "0") {
		alert("Please Select Policy Category");

		return false;
	} 
	
	
	if ($("#subcategory").val().trim() == "") {
		alert("Please Enter Policy sub category ");
		$("input#policycategory").focus();
		return false;
	}

	if ($("select#status").val() == "2") {
		alert("Only Select Active Status");
		$("select#status").focus();
		return false;
	}

	return true;
	

}
	</script>