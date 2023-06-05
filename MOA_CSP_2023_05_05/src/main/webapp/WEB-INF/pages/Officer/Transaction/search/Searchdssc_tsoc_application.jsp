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
<form:form name="SearchFormname" id="SearchFormid" action="Searchdssc_tsoc_applicationAction" method="POST" modelAttribute="Searchdssc_tsoc_applicationCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>SEARCH DSSC_TSOC_APPLICATION</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
<form:input type="hidden" id="id" path="id" ></form:input>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Personal No.<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="oa_application_id" path="oa_application_id"  class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">DSSC<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:radiobutton id="dta_dssc1" path="dta_dssc"  value="0" />null  </label> &nbsp;&nbsp;
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">TSOC<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:radiobutton id="dta_tsoc1" path="dta_tsoc"  value="1" />null  </label> &nbsp;&nbsp;
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Both<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:radiobutton id="dta_course_pref1" path="dta_course_pref"  value="2" />null  </label> &nbsp;&nbsp;
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Type of Entry<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
		     <form:select id="dta_type_of_entry" path="dta_type_of_entry"  class="form-control">
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
	        <label for="text-input" class=" form-control-label">Regiment</label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_regiment" path="dta_regiment"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Address of Correspondence<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_address_corres1" path="dta_address_corres1"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Address of Correspondence<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_address_coress2" path="dta_address_coress2"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Medical Category (SHAPE)<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_medical_cat" path="dta_medical_cat"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Ser. No.<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_jccourse_no" path="dta_jccourse_no"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Begin Date<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_jccourse_beg_date" path="dta_jccourse_beg_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">End Date<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_jccourse_end_date" path="dta_jccourse_end_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Course No.<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_182daycourse_no" path="dta_182daycourse_no"  class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Date of Commencement<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_182daycourse_init_date" path="dta_182daycourse_init_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Date of Termination<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_182daycourse_term_date" path="dta_182daycourse_term_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Waiver Granted<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:checkbox id="dta_waiver1" path="dta_waiver"  value="0" />null</label> &nbsp;&nbsp;
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">First Year<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_firstchance_year" path="dta_firstchance_year"  class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">First Centre<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_firstchance_center" path="dta_firstchance_center"  class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Second Year<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_secondchance_year" path="dta_secondchance_year"  class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Second Centre<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_secondchance_center" path="dta_secondchance_center"  class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Third Year<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_thirdchance_year" path="dta_thirdchance_year"  class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Third Centre<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_thirdchance_center" path="dta_thirdchance_center"  class="form-control" autocomplete='off' maxlength="" ></form:input>
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



 <c:url value="EditDssc_tsoc_applicationUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deletedssc_tsoc_applicationUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
<input type="hidden" name="deleteid" id="deleteid"/>
</form:form>




<c:url value="Searchdssc_tsoc_applicationUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="SearchForm" name="SearchForm">
</form:form>




<div class="container" align="center">
<div class="col-md-12">
<table id="dssc_tsoc_application_reporttbl" class="display table no-margin table-striped  table-hover  table-bordered report_print">
	<thead>
        <tr>
<th>Personal No. </th>
<th>DSSC </th>
<th>TSOC </th>
<th>Both </th>
<th>Type of Entry </th>
<th>Medical Category (SHAPE) </th>
<th>Address of Correspondence </th>
<th>Address of Correspondence </th>
<th>Ser. No. </th>
<th>Begin Date </th>
<th>End Date </th>
<th>Course No. </th>
<th>Date of Commencement </th>
<th>Date of Termination </th>
<th>Waiver Granted </th>
<th>First Year </th>
<th>First Centre </th>
<th>Second Year </th>
<th>Second Centre </th>
<th>Third Year </th>
<th>Third Centre </th>
          <th >Action</th>
    </tr>
	</thead>
	</table>
</div>
</div>
 <script>


$(document).ready(function () {

mockjax1('dssc_tsoc_application_reporttbl');
table = dataTable('dssc_tsoc_application_reporttbl');
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

	$.post("getDssc_tsoc_applicationReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].oa_application_id, j[i].dta_dssc, j[i].dta_tsoc, j[i].dta_course_pref, j[i].dta_type_of_entry, j[i].dta_medical_cat, j[i].dta_address_corres1, j[i].dta_address_coress2, j[i].dta_jccourse_no, j[i].dta_jccourse_beg_date, j[i].dta_jccourse_end_date, j[i].dta_182daycourse_no, j[i].dta_182daycourse_init_date, j[i].dta_182daycourse_term_date, j[i].dta_waiver, j[i].dta_firstchance_year, j[i].dta_firstchance_center, j[i].dta_secondchance_year, j[i].dta_secondchance_center, j[i].dta_thirdchance_year, j[i].dta_thirdchance_center,j[i].id]);
		}
	});
	$.post("getDssc_tsoc_applicationTotalCount?"+key+"="+value,{Search:Search},function(j) {
		FilteredRecords = j;
	});
}
 $('#dta_jccourse_beg_date').datepicker({
	showOn: 'both',
	buttonImageOnly: true,
	buttonImage: 'js/Calender/cal_ico.png',
	dateFormat: 'dd/mm/yy',
changeMonth: true,
changeYear: true,
yearRange: '1890:2099'
});
$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
 $('#dta_jccourse_end_date').datepicker({
	showOn: 'both',
	buttonImageOnly: true,
	buttonImage: 'js/Calender/cal_ico.png',
	dateFormat: 'dd/mm/yy',
changeMonth: true,
changeYear: true,
yearRange: '1890:2099'
});
$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
 $('#dta_182daycourse_init_date').datepicker({
	showOn: 'both',
	buttonImageOnly: true,
	buttonImage: 'js/Calender/cal_ico.png',
	dateFormat: 'dd/mm/yy',
changeMonth: true,
changeYear: true,
yearRange: '1890:2099'
});
$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
 $('#dta_182daycourse_term_date').datepicker({
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
  if($("input#oa_application_id").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Personal No.</div>";
   } 
  if($("input[name='dta_dssc']:checked").val() == undefined  || $("input[name='dta_dssc']:checked").val() == "undefined")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter DSSC</div>";
   } 
  if($("input[name='dta_tsoc']:checked").val() == undefined  || $("input[name='dta_tsoc']:checked").val() == "undefined")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter TSOC</div>";
   } 
  if($("input[name='dta_course_pref']:checked").val() == undefined  || $("input[name='dta_course_pref']:checked").val() == "undefined")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Both</div>";
   } 
  if($("select#dta_type_of_entry").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Type of Entry</div>";
   } 
  if($("input#dta_medical_cat").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Medical Category (SHAPE)</div>";
   } 
  if($("input#dta_address_corres1").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Address of Correspondence</div>";
   } 
  if($("input#dta_address_coress2").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Address of Correspondence</div>";
   } 
  if($("input#dta_jccourse_no").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Ser. No.</div>";
   } 
  if($("input#dta_jccourse_beg_date").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Begin Date</div>";
   } 
  if($("input#dta_jccourse_end_date").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter End Date</div>";
   } 
  if($("input#dta_182daycourse_no").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Course No.</div>";
   } 
  if($("input#dta_182daycourse_init_date").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Date of Commencement</div>";
   } 
  if($("input#dta_182daycourse_term_date").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Date of Termination</div>";
   } 
  if($("input[name='dta_waiver']:checked").val() == undefined  || $("input[name='dta_waiver']:checked").val() == "undefined")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Waiver Granted</div>";
   } 
  if($("input#dta_firstchance_year").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter First Year</div>";
   } 
  if($("input#dta_firstchance_center").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter First Centre</div>";
   } 
  if($("input#dta_secondchance_year").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Second Year</div>";
   } 
  if($("input#dta_secondchance_center").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Second Centre</div>";
   } 
  if($("input#dta_thirdchance_year").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Third Year</div>";
   } 
  if($("input#dta_thirdchance_center").val() == "")
   {
   errCount++;
   errMsg += "<div class='col-md-3'>Please Enter Third Centre</div>";
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
