<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<!-- <script type="text/javascript" src="js/jquery/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/printWatermark/common.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script> -->

<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<c:if test='${not empty msg}'>
<input type='hidden' name='msg' id='msg' value='${msg}'/>
</c:if>
<form:form name="Formname" id="Formid" action="rank_code_masterAction" method="POST" modelAttribute="rank_code_masterCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>RANK CODE MASTER</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Rank<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="rc_rank_name" path="rc_rank_name"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="rc_rank_name_lbl">${rc_rank_name_lbl}</span>
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



<c:url value="EditRank_code_masterUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deleterank_code_masterUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
<input type="hidden" name="deleteid" id="deleteid"/>
</form:form>




<div class="container" align="center">
<div class="col-md-12">
<table id="rank_code_master_reporttbl" class="display table no-margin table-striped  table-hover  table-bordered report_print">
	<thead>
        <tr>
<th>Rank </th>
          <th>Action</th>
    </tr>
	</thead>
	</table>
</div>
</div>
 <script>


$(document).ready(function () {

mockjax1('rank_code_master_reporttbl');
table = dataTable('rank_code_master_reporttbl');
$('#btn-reload').on('click', function(){
	table.ajax.reload();
});


});
function data(tableName){
	jsondata = [];
	var table = $('#rank_code_master_reporttbl').DataTable();
	var info = table.page.info();


	var currentPage = info.page;
    var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];

	$.post("getRank_code_masterReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].rc_rank_name,j[i].id]);
		}
	});
	$.post("getRank_code_masterTotalCount?"+key+"="+value,{Search:Search},function(j) {
		FilteredRecords = j;
	});
}
 function isValidateClientSide()
  {
    var errCount = 0;
 document.getElementById("rc_rank_name_lbl").innerHTML="";
  if($("input#rc_rank_name").val().trim() == "")
   {
   errCount++;
 document.getElementById("rc_rank_name_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Rank"; 
 $("#rc_rank_name").focus(); 
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
