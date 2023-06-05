<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<script type="text/javascript" src="js/jquery/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/printWatermark/common.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>
<c:if test='${not empty msg}'>
<input type='hidden' name='msg' id='msg' value='${msg}'/>
</c:if>
<form:form name="EditFormname" id="EditFormid" action="Editmarks_entryAction" method="POST" modelAttribute="Editmarks_entryCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>EDIT MARKS_ENTRY</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
<form:input type="hidden" id="id" path="id" value="${Editmarks_entryCMD1.id}" ></form:input>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Subject<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
		     <form:select id="sc_subject_id" path="sc_subject_id"  class="form-control">
 		      <option value="">--Select--</option>
         <c:forEach var="item" items="${getscsubjectnameListDDL}" varStatus="num"  >
               <option value="${item.sc_subject_id}" name="${item.sc_subject_name}" <c:if test="${item.sc_subject_id eq Editmarks_entryCMD1.sc_subject_id}">selected="selected"</c:if>>${item.sc_subject_name}</option>
         </c:forEach>
	 </form:select>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">First Entry<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:radiobutton id="ab_first_entry1" path="ab_first_entry"  value="null" />null  </label> &nbsp;&nbsp;
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Second Entry<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:radiobutton id="ab_second_entry1" path="ab_second_entry"  value="null" />null  </label> &nbsp;&nbsp;
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Index No.<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="oa_application_id" path="oa_application_id" value="${Editmarks_entryCMD1.oa_application_id}" class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	        <label for="text-input" class=" form-control-label">Marks</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="ab_marks_obtained" path="ab_marks_obtained" value="${Editmarks_entryCMD1.ab_marks_obtained}" class="form-control" autocomplete='off' maxlength="" ></form:input>
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

	if('${Editmarks_entryCMD1.ab_first_entry}' == 'null')
	$("#ab_first_entry1").prop("checked", true);

	if('${Editmarks_entryCMD1.ab_second_entry}' == 'null')
	$("#ab_second_entry1").prop("checked", true);

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
 document.getElementById("oa_application_id_lbl").innerHTML="";
 document.getElementById("sc_subject_id_lbl").innerHTML="";
 document.getElementById("ab_first_entry_lbl").innerHTML="";
 document.getElementById("ab_second_entry_lbl").innerHTML="";
  if($("input#oa_application_id").val().trim() == "")
   {
   errCount++;
 document.getElementById("oa_application_id_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Index No."; 
 $("#oa_application_id").focus(); 
   } 
  if($("select#sc_subject_id").val() == "")
   {
   errCount++;
 document.getElementById("sc_subject_id_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Subject"; 
 $("#sc_subject_id").focus(); 
   } 
  if($("input[name='ab_first_entry']:checked").val() == undefined  || $("input[name='ab_first_entry']:checked").val() == "undefined")
   {
   errCount++;
 document.getElementById("ab_first_entry_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter First Entry"; 
 $("#ab_first_entry").focus(); 
   } 
  if($("input[name='ab_second_entry']:checked").val() == undefined  || $("input[name='ab_second_entry']:checked").val() == "undefined")
   {
   errCount++;
 document.getElementById("ab_second_entry_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Second Entry"; 
 $("#ab_second_entry").focus(); 
   } 
 if (errCount > 0) { 
	return false;
 } else {
	return true;
 }
 return true;
  } 
 </script>
