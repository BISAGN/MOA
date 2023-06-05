<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<!-- <script type="text/javascript" src="js/printWatermark/common.js"></script> -->
<!-- <link rel="stylesheet" href="js/printWatermark/cueWatermark.css"> -->
<!-- <script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script> -->
<!-- <link rel="stylesheet" href="js/watermark/cueWatermark.css"> -->
<!-- <script src="js/watermark/cueWatermark.js" type="text/javascript"></script> -->

<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->




<c:if test='${not empty msg}'>
<input type='hidden' name='msg' id='msg' value='${msg}'/>
</c:if>
<form:form name="Formname" id="Formid" action="command_code_masterAction" method="POST" modelAttribute="command_code_masterCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>COMMAND CODE MASTER</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Command Name<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="cc_command_name" path="cc_command_name"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="cc_command_name_lbl">${cc_command_name_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Command Order<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="cc_command_order" path="cc_command_order"  class="form-control" autocomplete='off' maxlength="" ></form:input>
 <span class="errorClass" id="cc_command_order_lbl">${cc_command_order_lbl}</span>
        </div>
    </div>
</div>
    </div>
       <div class='card-footer' align='center'>
           <input type='reset' class='btn btn-success btn-sm' value='Clear' onclick='clearall()'>
           <input type='submit' class='btn btn-primary btn-sm' value='Save'  onclick='return isValidateClientSide()'>
           <input type='button' id='printId' class='btn btn-info btn-sm' value='Print' onclick='printDiv();'>
       </div>
    </div>
  </div>
</form:form>



<c:url value="EditCommand_code_masterUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deletecommand_code_masterUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
<input type="hidden" name="deleteid" id="deleteid"/>
</form:form>




<div class="container" align="center">
<div class="col-md-12">
<table id="command_code_master_reporttbl" class="display table no-margin table-striped  table-hover  table-bordered report_print">
	<thead>
        <tr>
<th>Command Name </th>
<th>Command Order </th>
          <th>Action</th>
    </tr>
	</thead>
	</table>
</div>
</div>
 <script>


$(document).ready(function () {

mockjax1('command_code_master_reporttbl');
table = dataTable('command_code_master_reporttbl');
$('#btn-reload').on('click', function(){
	table.ajax.reload();
});


});

function mockjax1(tableName){
	$.mockjax({
	    url: '/Data',
	    responseTime: 1000,
	    response: function(settings){
	    	$.ajaxSetup({
				async : false
			});
			data(tableName);
			this.responseText = {
	    		draw: settings.data.draw,
				data: jsondata,
				recordsTotal: jsondata.length,
	            recordsFiltered: FilteredRecords
			};
	     }
	});
}

function data(tableName){
	jsondata = [];
	var table = $('#command_code_master_reporttbl').DataTable();
	var info = table.page.info();


	var currentPage = info.page;
    var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];

	$.post("getCommand_code_masterReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].cc_command_name, j[i].cc_command_order,j[i].id]);
		}
	});
	$.post("getCommand_code_masterTotalCount?"+key+"="+value,{Search:Search},function(j) {
		FilteredRecords = j;
	});
}
 function isValidateClientSide()
  {
    var errCount = 0;
 document.getElementById("cc_command_name_lbl").innerHTML="";
 document.getElementById("cc_command_order_lbl").innerHTML="";
  if($("input#cc_command_name").val().trim() == "")
   {
   errCount++;
 document.getElementById("cc_command_name_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Command Name"; 
 $("#cc_command_name").focus(); 
   } 
  if($("input#cc_command_order").val().trim() == "")
   {
   errCount++;
 document.getElementById("cc_command_order_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Command Order"; 
 $("#cc_command_order").focus(); 
   } 
 if (errCount > 0) { 
	return false;
 } else {
	return true;
 }
 return true;
  } 
 function editData(obj){

	    document.getElementById('updateid').value=obj;
	    document.getElementById('updateForm').submit();
}


 function deleteData(obj){

	    document.getElementById('deleteid').value=obj;
	    document.getElementById('deleteForm').submit();
}
 </script>
