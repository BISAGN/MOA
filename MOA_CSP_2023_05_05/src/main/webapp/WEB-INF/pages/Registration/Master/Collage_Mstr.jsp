<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<style>

table thead {
    display: block;
    width: 100%!IMPORTANT;

}
table.display.table.no-margin.table-striped.table-hover.table-bordered.dataTable.no-footer {
    min-width: 100% !important;
    width: 100% !important;
}
.dataTables_scrollHeadInner {
    /* min-width: 100% !important; */
    width: 100% !important;
}
table td, table th {
    box-sizing: content-box;
    width: 371px !important;
}
</style>

<form:form action="collageMstr_action" method="POST" class="form-horizontal" modelAttribute="collageMstr_cmd">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header title">
				<h5>
					<span id="lbladd"></span><b>COLLEGE MASTER</b>
				</h5>
			</div>
						<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
								<div class="col-md-6">
						
										<div class="col-md-6">
								<label for="username">COLLEGE NAME<span class="star_design"></span> <span style="color:#ff0000">*</span> </label>
										</div>
										<div class="col-md-6">
                                     <input id="collage_name" name="collage_name" class="form-control" autocomplete="off" maxlength="250" style="text-transform :uppercase"
									 placeholder="Enter College Name" />
								
										</div>
							
								</div>
						<div class="row form-group">
													
							
							<div class="col-md-6">
								<label for="username">COLLEGE CODE<span class="star_design"></span> <span style="color:#ff0000">*</span> </label>
							</div>
							<div class="col-md-6">
								<input id="collage_code" name="collage_code"
									class="form-control" autocomplete="off" maxlength="250"
									 placeholder="Enter College Code"/>
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
									<!-- <option value="0">--SELECT--</option> -->
									<option value="1">ACTIVE</option>
									<option value="2">INACTIVE</option>
								</select>
								<div class="col-md-6">
									
								</div>
									</div>
							
								</div>
					
				
					</div>
					<br><br><br>
					

					</div>
				
				<input type="hidden" name="id_org" id="id_org" value="0"/>
			  <div class="card-footer" align="center">
				<a href="Collage_MstrUrl" class="btn-clear">Reset</a>
				 <input type="submit" class="btn-save" value="Save"onclick="return Validation();">
				 <i
					class="action_icons searchButton"></i><input type="button"
					class="btn-search" id="btn-reload" value="Search">
			</div>
		
	</div>
</form:form>


<div class="card-body card-block" style="text-align: left;" id="clg_tbl_div">
		<table id="search_clg_details_table" class="display table no-margin table-striped  table-hover  table-bordered">
			<thead>
				<tr>
					<th align="center">Ser No</th>
					<th id="2">College Name</th>
					<th id="3">College Code</th>
					<th class="action">Action</th>
				</tr>
			</thead>
		</table>
	</div>

<c:url value="delete_collage" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form> 


<script >

$(document).ready(function() {
	
	 var msg = '${msg}';
		if (msg != "")
				{
				alert(msg);
				}
		 try{
		 	   if(window.location.href.includes("msg="))
		 		{
		 			var url = window.location.href.split("?")[0];
		 			window.location.href=url;
		 		} 	
		 	}
		 	catch (e) {
		 	}

	mockjax1('search_clg_details_table');
	table = dataTable('search_clg_details_table');
	$('#btn-reload').on('click', function(){	
    	table.ajax.reload();
    });
});



function data(search_clg_details_table) {
	jsondata = [];
	var table = $('#' + search_clg_details_table).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];

	var collage_name = $("#collage_name").val();
 	var collage_code = $("#collage_code").val();
 	var status = $("#status").val();
	
	$.post("getFiltercollage_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		collage_name:collage_name,
		collage_code:collage_code,
		status:status
		
		
	}, function(j) {
		
	for (var i = 0; i < j.length; i++) {
	jsondata.push([j[i][0],j[i][1],j[i][2],j[i][3]]);
	
	}
});

	$.post("getTotalcollage_dataCount?" + key + "=" + value, {Search:Search}, function(j) {
		FilteredRecords = j;
	});
}

function editData(id,collage_name,collage_code,status){

	document.getElementById('lbladd').innerHTML = "UPDATE ";
	
		$("#id").val(id);
	
	$("input#collage_name").val(collage_name);
	$("input#collage_code").val(collage_code);
	$("select#status").val(status);
	
	document.getElementById('id_org').value=id;
}

function deleteData(id){
	
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}


function Validation(){
	
	   if ($("#collage_name").val().trim() == "") {
			alert("Please Enter the College Name");
			$("#collage_name").focus();
			return false;
		}
	   
	   if ($("#collage_code").val().trim() == "") {
			alert("Please Enter the College Code");
			$("#collage_code").focus();
			return false;
		}
	   
	   if ($("#status").val().trim() == "0") {
			alert("Please Select the Status");
			$("#status").focus();
			return false;
		}
	
}

</script>






