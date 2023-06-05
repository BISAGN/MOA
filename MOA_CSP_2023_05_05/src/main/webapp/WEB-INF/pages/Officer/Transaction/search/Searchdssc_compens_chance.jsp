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
<form:form name="SearchFormname" id="SearchFormid" action="Searchdssc_compens_chanceAction" method="POST" modelAttribute="Searchdssc_compens_chanceCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>SEARCH DSSC COMPENS CHANCE</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
<form:input type="hidden" id="id" path="id" ></form:input>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Select Examination<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:radiobutton id="dcc_exam_type1" path="dcc_exam_type"  value="0" />DSSC  </label> &nbsp;&nbsp;
           <label for='' class="form-check-label"><form:radiobutton id="dcc_exam_type2" path="dcc_exam_type"  value="1" />TSOC  </label> &nbsp;&nbsp;
           <label for='' class="form-check-label"><form:radiobutton id="dcc_exam_type3" path="dcc_exam_type"  value="2" />Both  </label> &nbsp;&nbsp;
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
	            <label for="text-input" class="form-control-label">Name of Area/Fmn<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dcc_area" path="dcc_area"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Type of Area<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
		     <form:select id="dcc_area_type" path="dcc_area_type"  class="form-control">
 		      <option value="">--Select--</option>
		      <option value="a">a</option>
		      <option value="b">b</option>
		      <option value="c">c</option>
	 </form:select>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Type of Entry<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
		     <form:select id="dcc_entry_type" path="dcc_entry_type"  class="form-control">
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
	            <label for="text-input" class="form-control-label">Year Part D Passed<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dcc_partd" path="dcc_partd"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Year JC Course Done<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dcc_jc_year" path="dcc_jc_year"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Year(s) chances availed for DSSC/TSOC<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dcc_chances" path="dcc_chances"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Dispenasation granted earlier if any<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dcc_earlier_dispens" path="dcc_earlier_dispens"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Dispensation asked for year<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dcc_dispes_year" path="dcc_dispes_year"  class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Date of entry in the area<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dcc_area_entry_date" path="dcc_area_entry_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Date of leaving the area<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dcc_area_leave_date" path="dcc_area_leave_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Year Compensatory chance to be utilized<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dcc_compens_year" path="dcc_compens_year"  class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Leave Duration<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dcc_leave" path="dcc_leave"  class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Remarks<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dcc_remarks" path="dcc_remarks"  class="form-control" autocomplete='off'  ></form:input>
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



 <c:url value="EditDssc_compens_chanceUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deletedssc_compens_chanceUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
<input type="hidden" name="deleteid" id="deleteid"/>
</form:form>




<c:url value="Searchdssc_compens_chanceUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="SearchForm" name="SearchForm">
</form:form>




<div class="container" align="center">
<div class="col-md-12">
<table id="dssc_compens_chance_reporttbl" class="display table no-margin table-striped  table-hover  table-bordered report_print">
	<thead>
        <tr>
<th>Personal No. </th>
<th>Name of Area/Fmn </th>
<th>Type of Entry </th>
<th>Year Part D Passed </th>
<th>Year JC Course Done </th>
<th>Year(s) chances availed for DSSC/TSOC </th>
<th>Dispenasation granted earlier if any </th>
<th>Dispensation asked for year </th>
<th>Date of entry in the area </th>
<th>Date of leaving the area </th>
<th>Year Compensatory chance to be utilized </th>
<th>Leave Duration </th>
<th>Remarks </th>
          <th >Action</th>
    </tr>
	</thead>
	</table>
</div>
</div>
 <script>


$(document).ready(function () {

mockjax1('dssc_compens_chance_reporttbl');
table = dataTable('dssc_compens_chance_reporttbl');
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

	$.post("getDssc_compens_chanceReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].opd_personal_id, j[i].dcc_area, j[i].dcc_entry_type, j[i].dcc_partd, j[i].dcc_jc_year, j[i].dcc_chances, j[i].dcc_earlier_dispens, j[i].dcc_dispes_year, j[i].dcc_area_entry_date, j[i].dcc_area_leave_date, j[i].dcc_compens_year, j[i].dcc_leave, j[i].dcc_remarks,j[i].id]);
		}
	});
	$.post("getDssc_compens_chanceTotalCount?"+key+"="+value,{Search:Search},function(j) {
		FilteredRecords = j;
	});
}
 $('#dcc_area_entry_date').datepicker({
	showOn: 'both',
	buttonImageOnly: true,
	buttonImage: 'js/Calender/cal_ico.png',
	dateFormat: 'dd/mm/yy',
changeMonth: true,
changeYear: true,
yearRange: '1890:2099'
});
$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
 $('#dcc_area_leave_date').datepicker({
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
  if($("input[name='dcc_exam_type']:checked").val() == undefined  || $("input[name='dcc_exam_type']:checked").val() == "undefined")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Select Examination</div>";
   } 
  if($("input#dcc_area").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Name of Area/Fmn</div>";
   } 
  if($("select#dcc_area_type").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Type of Area</div>";
   } 
  if($("select#dcc_entry_type").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Type of Entry</div>";
   } 
  if($("input#dcc_partd").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Year Part D Passed</div>";
   } 
  if($("input#dcc_jc_year").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Year JC Course Done</div>";
   } 
  if($("input#dcc_chances").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Year(s) chances availed for DSSC/TSOC</div>";
   } 
  if($("input#dcc_earlier_dispens").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Dispenasation granted earlier if any</div>";
   } 
  if($("input#dcc_dispes_year").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Dispensation asked for year</div>";
   } 
  if($("input#dcc_area_entry_date").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Date of entry in the area</div>";
   } 
  if($("input#dcc_area_leave_date").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Date of leaving the area</div>";
   } 
  if($("input#dcc_compens_year").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Year Compensatory chance to be utilized</div>";
   } 
  if($("input#dcc_leave").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Leave Duration</div>";
   } 
  if($("input#dcc_remarks").val() == "")
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
