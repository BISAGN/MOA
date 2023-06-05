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
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script> -->



<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<c:if test='${not empty msg}'>
<input type='hidden' name='msg' id='msg' value='${msg}'/>
</c:if>
<form:form name="SearchFormname" id="SearchFormid" action="Searchexamination_centreAction" method="POST" modelAttribute="Searchexamination_centreCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>SEARCH EXAMINATION CENTRE</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
<form:input type="hidden" id="id" path="id" ></form:input>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Command Name<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
		     <form:select id="cc_command_id" path="cc_command_id"  class="form-control">
          <c:forEach var="item" items="${getcccommandnameListDDL}" varStatus="num"  >
               <option value="${item.id}" name="${item.cc_command_name}" >${item.cc_command_name}</option>
         </c:forEach>
	 </form:select>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Exam Name<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
		     <form:select id="ec_exam_id" path="ec_exam_id"  class="form-control">
          <c:forEach var="item" items="${getecexamnameListDDL}" varStatus="num"  >
               <option value="${item.id}" name="${item.ec_exam_name}" >${item.ec_exam_name}</option>
         </c:forEach>
	 </form:select>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Exam Name<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="ecc_name" path="ecc_name"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Conduction Formation<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="ecc_cond_formation" path="ecc_cond_formation"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Centre Name<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="center_code" path="center_code"  class="form-control" autocomplete='off'  ></form:input>
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



 <c:url value="EditExamination_centreUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deleteexamination_centreUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
<input type="hidden" name="deleteid" id="deleteid"/>
</form:form>




<c:url value="Searchexamination_centreUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="SearchForm" name="SearchForm">
</form:form>




<div class="container" align="center">
<div class="col-md-12">
<table id="examination_centre_reporttbl" class="display table no-margin table-striped  table-hover  table-bordered report_print">
	<thead>
        <tr>
<th>Command Name </th>
<th>Exam Name </th>
<th>Exam Name </th>
<th>Conduction Formation </th>
<th>Centre Name </th>
          <th >Action</th>
    </tr>
	</thead>
	</table>
</div>
</div>
 <script>


$(document).ready(function () {

mockjax1('examination_centre_reporttbl');
table = dataTable('examination_centre_reporttbl');
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

	$.post("getExamination_centreReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].cc_command_id, j[i].ec_exam_id, j[i].ecc_name, j[i].ecc_cond_formation, j[i].center_code,j[i].id]);
		}
	});
	$.post("getExamination_centreTotalCount?"+key+"="+value,{Search:Search},function(j) {
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
  if($("select#cc_command_id").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Command Name</div>";
   } 
  if($("select#ec_exam_id").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Exam Name</div>";
   } 
  if($("input#ecc_name").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Exam Name</div>";
   } 
  if($("input#ecc_cond_formation").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Conduction Formation</div>";
   } 
  if($("input#center_code").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Centre Name</div>";
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
