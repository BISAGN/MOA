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
<form:form name="SearchFormname" id="SearchFormid" action="Searchpartbd_compens_chanceAction" method="POST" modelAttribute="Searchpartbd_compens_chanceCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>SEARCH PARTBD COMPENS CHANCE</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
<form:input type="hidden" id="id" path="id" ></form:input>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Examination<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:radiobutton id="es_id1" path="es_id"  value="0" />Part B  </label> &nbsp;&nbsp;
           <label for='' class="form-check-label"><form:radiobutton id="es_id2" path="es_id"  value="1" />Part D  </label> &nbsp;&nbsp;
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Personal No.<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="opd_personal_id" path="opd_personal_id"  class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Qualifying Area/Operation<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
		     <form:select id="pcc_area" path="pcc_area"  class="form-control">
 		      <option value="">--Select--</option>
		      <option value="a">a</option>
		      <option value="b">b</option>
	 </form:select>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Date of posting into the area<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="pcc_area_entry_date" path="pcc_area_entry_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Date of posting out<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="pcc_area_leave_date" path="pcc_area_leave_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Absence Period (in Days)<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="pcc_absence" path="pcc_absence"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Chance granted earlier, if any<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="pcc_chances" path="pcc_chances"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Chance to be availed in year<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="pcc_avail_year" path="pcc_avail_year"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Chance asked for (mention year)<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="pcc_ask_year" path="pcc_ask_year"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Remarks<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="pcc_remarks" path="pcc_remarks"  class="form-control" autocomplete='off'  ></form:input>
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



 <c:url value="EditPartbd_compens_chanceUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deletepartbd_compens_chanceUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
<input type="hidden" name="deleteid" id="deleteid"/>
</form:form>




<c:url value="Searchpartbd_compens_chanceUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="SearchForm" name="SearchForm">
</form:form>




<div class="container" align="center">
<div class="col-md-12">
<table id="partbd_compens_chance_reporttbl" class="display table no-margin table-striped  table-hover  table-bordered report_print">
	<thead>
        <tr>
<th>Personal No. </th>
<th>Qualifying Area/Operation </th>
<th>Chance granted earlier </th>
<th> if any </th>
<th>Chance asked for (mention year) </th>
<th>Date of posting into the area </th>
<th>Date of posting out </th>
<th>Chance to be availed in year </th>
<th>Absence Period (in Days) </th>
<th>Remarks </th>
          <th >Action</th>
    </tr>
	</thead>
	</table>
</div>
</div>
 <script>


$(document).ready(function () {

mockjax1('partbd_compens_chance_reporttbl');
table = dataTable('partbd_compens_chance_reporttbl');
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

	$.post("getPartbd_compens_chanceReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].opd_personal_id, j[i].pcc_area, j[i].pcc_chances, j[i].pcc_ask_year, j[i].pcc_area_entry_date, j[i].pcc_area_leave_date, j[i].pcc_avail_year, j[i].pcc_absence, j[i].pcc_remarks,j[i].id]);
		}
	});
	$.post("getPartbd_compens_chanceTotalCount?"+key+"="+value,{Search:Search},function(j) {
		FilteredRecords = j;
	});
}
 $('#pcc_area_entry_date').datepicker({
	showOn: 'both',
	buttonImageOnly: true,
	buttonImage: 'js/Calender/cal_ico.png',
	dateFormat: 'dd/mm/yy',
changeMonth: true,
changeYear: true,
yearRange: '1890:2099'
});
$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
 $('#pcc_area_leave_date').datepicker({
	showOn: 'both',
	buttonImageOnly: true,
	buttonImage: 'js/Calender/cal_ico.png',
	dateFormat: 'dd/mm/yy',
changeMonth: true,
changeYear: true,
yearRange: '1890:2099'
});
$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
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
   errMsg += "<div class='col-md-3'>Please Enter Personal No.</div>";
   } 
  if($("input[name='es_id']:checked").val() == undefined  || $("input[name='es_id']:checked").val() == "undefined")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Examination</div>";
   } 
  if($("select#pcc_area").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Qualifying Area/Operation</div>";
   } 
  if($("input#pcc_chances").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Chance granted earlier, if any</div>";
   } 
  if($("input#pcc_ask_year").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Chance asked for (mention year)</div>";
   } 
  if($("input#pcc_area_entry_date").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Date of posting into the area</div>";
   } 
  if($("input#pcc_area_leave_date").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Date of posting out</div>";
   } 
  if($("input#pcc_avail_year").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Chance to be availed in year</div>";
   } 
  if($("input#pcc_absence").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Absence Period (in Days)</div>";
   } 
  if($("input#pcc_remarks").val() == "")
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
