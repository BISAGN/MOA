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
<c:if test='${not empty msg}'>
<input type='hidden' name='msg' id='msg' value='${msg}'/>
</c:if>
<form:form name="EditFormname" id="EditFormid" action="EditJC_COURSE_DETAILSAction" method="POST" modelAttribute="EditJC_COURSE_DETAILSCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>EDIT JC_COURSE_DETAILS</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
<form:input type="hidden" id="id" path="id" value="${EditJC_COURSE_DETAILSCMD1.id}" ></form:input>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">JC COURSE No<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="jcc_no" path="jcc_no" value="${EditJC_COURSE_DETAILSCMD1.jcc_no}" class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">BEG DATE<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="jcc_beg_date" path="jcc_beg_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">END DATE<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="jcc_end_date" path="jcc_end_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
        </div>
    </div>
</div>
    </div>
       <div class='card-footer' align='center'>
           <input type='reset' class='btn btn-success btn-sm' value='Clear' onclick='clearall()'>
           <input type='submit' class='btn btn-primary btn-sm' value='Update'  onclick='return isValidateClientSide()'>
       </div>
    </div>
  </div>
</form:form>



 <script>


$(document).ready(function () {

	if('${EditJC_COURSE_DETAILSCMD1.jcc_beg_date}' ==null){
	$("#jcc_beg_date").val("");
}
else{
	 $("#jcc_beg_date").val(ParseDateColumncommission('${EditJC_COURSE_DETAILSCMD1.jcc_beg_date}'));



}
 $('#jcc_beg_date').datepicker({
	showOn: 'both',
	buttonImageOnly: true,
	buttonImage: 'js/Calender/cal_ico.png',
	dateFormat: 'dd/mm/yy',
changeMonth: true,
changeYear: true,
yearRange: '1890:2099'
});
$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});


	if('${EditJC_COURSE_DETAILSCMD1.jcc_end_date}' ==null){
	$("#jcc_end_date").val("");
}
else{
	 $("#jcc_end_date").val(ParseDateColumncommission('${EditJC_COURSE_DETAILSCMD1.jcc_end_date}'));



}
 $('#jcc_end_date').datepicker({
	showOn: 'both',
	buttonImageOnly: true,
	buttonImage: 'js/Calender/cal_ico.png',
	dateFormat: 'dd/mm/yy',
changeMonth: true,
changeYear: true,
yearRange: '1890:2099'
});
$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});


});



function ParseDateColumncommission(data) {

	  var date="";
	  if(data!=null && data!=""){
		 var d = new Date(data);
		 var day=('0' + d.getDate()).slice(-2);
		 var month=('0' + (d.getMonth() + 1)).slice(-2);
		 var year=""+d.getFullYear();
		 date=day+"/"+month+"/"+year;
		   		}
		    return date;
	}
 function isValidateClientSide()
  {
    var errCount = 0;
 document.getElementById("jcc_beg_date_lbl").innerHTML="";
 document.getElementById("jcc_end_date_lbl").innerHTML="";
 document.getElementById("jcc_no_lbl").innerHTML="";
  else if($("input#jcc_beg_date").val() == "DD/MM/YYYY")
   {
   errCount++;
 document.getElementById("jcc_beg_date_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter BEG DATE"; 
 $("#jcc_beg_date").focus(); 
   } 
  else if($("input#jcc_end_date").val() == "DD/MM/YYYY")
   {
   errCount++;
 document.getElementById("jcc_end_date_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter END DATE"; 
 $("#jcc_end_date").focus(); 
   } 
  if($("input#jcc_no").val().trim() == "")
   {
   errCount++;
 document.getElementById("jcc_no_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter JC COURSE No"; 
 $("#jcc_no").focus(); 
   } 
 if (errCount > 0) { 
	return false;
 } else {
	return true;
 }
 return true;
  } 
 </script>
