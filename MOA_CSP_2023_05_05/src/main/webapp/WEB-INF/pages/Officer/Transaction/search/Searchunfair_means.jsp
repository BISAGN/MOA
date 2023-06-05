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
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>-->


<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<c:if test='${not empty msg}'>
<input type='hidden' name='msg' id='msg' value='${msg}'/>
</c:if>
<form:form name="SearchFormname" id="SearchFormid" action="Searchunfair_meansAction" method="POST" modelAttribute="Searchunfair_meansCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>SEARCH UNFAIR MEANS</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
<form:input type="hidden" id="id" path="id" ></form:input>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Personal Number<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="opd_personal_id" path="opd_personal_id"  class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Select Examination<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:radiobutton id="es_id1" path="es_id"  value="0" />Part B  </label> &nbsp;&nbsp;
           <label for='' class="form-check-label"><form:radiobutton id="es_id2" path="es_id"  value="1" />Part D  </label> &nbsp;&nbsp;
           <label for='' class="form-check-label"><form:radiobutton id="es_id3" path="es_id"  value="2" />DSSC/TSOC  </label> &nbsp;&nbsp;
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Subject<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
		     <form:select id="sc_subject_id" path="sc_subject_id"  class="form-control">
          <c:forEach var="item" items="${getscsubjectnameListDDL}" varStatus="num"  >
               <option value="${item.sc_subject_id}" name="${item.sc_subject_name}" >${item.sc_subject_name}</option>
         </c:forEach>
	 </form:select>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Remarks<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="um_remarks" path="um_remarks"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
    </div>
       <div class='card-footer' align='center'>
           <input type='reset' class='btn btn-success btn-sm' value='Clear' onclick='clearall()'>
           <input type='button' class='btn btn-primary btn-sm' value='Search'  onclick='return searchreport();'>
           <input type='button' id='printId' class='btn btn-info btn-sm' value='Print' onclick='printDiv();'>
       </div>
    </div>
  </div>
</form:form>



 <c:url value="EditUnfair_meansUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deleteunfair_meansUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
<input type="hidden" name="deleteid" id="deleteid"/>
</form:form>




<c:url value="Searchunfair_meansUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="SearchForm" name="SearchForm">
</form:form>




<div class="container" align="center">
<div class="col-md-12">
<table id="unfair_means_reporttbl" class="display table no-margin table-striped  table-hover  table-bordered report_print">
	<thead>
        <tr>
<th>Personal Number </th>
<th>Remarks </th>
          <th >Action</th>
    </tr>
	</thead>
	</table>
</div>
</div>
 <script>


$(document).ready(function () {

mockjax1('unfair_means_reporttbl');
table = dataTable('unfair_means_reporttbl');
$('#btn-reload').on('click', function(){
	table.ajax.reload();
});


});
function data(tableName){
	jsondata = [];
	var info = table.page.info();


	var currentPage = info.page;
    var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];

	$.post("getUnfair_meansReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].opd_personal_id, j[i].um_remarks,j[i].id]);
		}
	});
	$.post("getUnfair_meansTotalCount?"+key+"="+value,{Search:Search},function(j) {
		FilteredRecords = j;
	});
}
 function searchreport()
  {
	$(".errMsgServer").empty();
	$(".errMsgClient").empty();
	$("div#divLine").hide();
	var errMsg = "";
    var errCount = 0;
  if($("input#opd_personal_id").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Personal Number</div>";
   } 
  if($("input[name='es_id']:checked").val() == undefined  || $("input[name='es_id']:checked").val() == "undefined")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Select Examination</div>";
   } 
  if($("select#sc_subject_id").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Subject</div>";
   } 
  if($("input#um_remarks").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Remarks</div>";
   } 
  if (errCount > 0) {
  $(".errMsgClient").append("<div class='row'>" + errMsg + "</div>");
  $(".errMsgClient").slideDown('slow');
  $(".errMsgClient").show("slow");
  $("div#divLine").show();
  return false;
   } 
    else
  { 
	    document.getElementById('SearchForm').submit();
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
