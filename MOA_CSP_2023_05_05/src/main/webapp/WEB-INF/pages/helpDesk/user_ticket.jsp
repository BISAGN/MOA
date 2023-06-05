

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>
<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/printWatermark/common.js"></script>
<script src="js/watermark/printallpages_old.js" type="text/javascript"></script> 
<link rel="stylesheet" href="js/watermark/watermark.css">
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="layout_file/css/bootstrap.min.css">
<link rel="stylesheet" href="layout_file/css/style.css">
<script src="js/commonJS/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script>
var username="${username}";
</script>
<%-- <form:form name="userticket" id="userticket" action="userticketAction" method='POST' modelAttribute="userticketCMD"> --%>

<div class="container">
	<div class="card">
		<div class="card-header"> 
		   <h5>View Ticket Details</h5>
		</div> <!-- end of card-header -->
		<div class="card-body card-block">
			<div  class="watermarked report_print" data-watermark="" id="divwatermark" style="display: block;">
			   <div class="row mb-2">
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">Ticket Id</label>
		                </div>
		                <div class="col-md-4">
		                 <input type="hidden" id="id" name="id" value="${id}"/>
		                <%--  <input id="ticket" name="ticket" class="form-control" autocomplete="off" value="${ticket}" readonly="readonly">  --%>
		                <label id="ticket"  >${ticket}</label>
						</div>
               </div>
			<div class="row mb-2">
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">User</label>
		                </div>
		                <div class="col-md-4">
		                <label id="username"  >${username}</label>
		                <%-- <input id="username" name="username" class="form-control" placeholder=" " autocomplete="off" value="${username}"readonly="readonly"> --%>
						</div>
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">Date </label>
		                </div>
		                <div class="col-md-4">
		                <label id="date1">${created_on}</label>
		                <input type="hidden" id=completed_dt name="completed_dt" class="form-control" readonly="readonly" value="${completed_dt}"></input>
		                 <input type="hidden" id=assigned_dt name="assigned_dt" class="form-control" readonly="readonly" value="${assigned_dt}"></input>
						</div>
                <%-- <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	<label for="text-input" class=" form-control-label">SUS No </label>
		                </div>
		                <div class="col-12 col-md-8">
		                <label id="sus_no"  >${sus_no}</label>
               				<input id="sus_no" name="sus_no" class="form-control" placeholder="" value="${sus_no}" readonly="readonly">
						</div>
	             	</div>
               </div> --%>
             </div>
            <div class="row mb-2">	
				<%-- <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	<label for="text-input" class=" form-control-label">Unit Name</label>
		                </div>
		                <div class="col-12 col-md-8">
		                <label id="unit_name"  >${unit_name}</label>
               				<input id="unit_name" name="unit_name" class="form-control"placeholder=""  value="${unit_name}"readonly="readonly">
						</div>
	             	</div>
               </div> --%>
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">Module </label>
		                </div>
		                <div class="col-md-4">
		                <label id="module"  >${moduleName}</label>
               				<%-- <input  id="module"  class="form-control"  readonly="readonly" value="${moduleName}"> --%>
               				<input type="hidden" id="moduleHid" name="module" value="${moduleId}"> 
               			</div>
           </div>
		<div class="row mb-2">
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">Sub Module </label>
		                </div>
		                <div class="col-md-4">
		                <label id="sub_module"  >${sub_module}</label>
               				<%-- <input  id="sub_module" class="form-control" value="${sub_module}" readonly="readonly"> --%> 
						</div>
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">Screen Name </label>
		                </div>
		                <div class="col-md-4">
		                <label id="screen_name"  >${screen_name}</label>
               				 <%-- <input  id="screen_name"  class="form-control"  value="${screen_name}" readonly="readonly"> --%>
						</div>
           </div>
			<div class="row mb-2">	
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">Help Topic  </label>
		                </div>
		                <div class="col-md-4">
		                <label id="help_topic"  >${help_topic}</label>
               				<%-- <input  id="help_topic"  class="form-control" value="${help_topic}"readonly="readonly"> --%> 
                         </div>
		              <div class="col-md-2">	             		
    	                   <label for="text-input" class=" form-control-label">Screenshot </label> 
    	                  
                       </div>
					    <div class="col-md-4" id="d1">
							<a href='#' onclick="getmodule_help('${id}');"class="btn btn-primary btn-sm" >Download</a>
						</div>
						 <div class="col-md-4" id="d2">
								 <h4>No Screenshot Available</h4> 
						</div> 
                </div>
 	         <div class="row mb-2">				  
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;" >*</strong>Issue summary </label>
		                </div>
		                <div class="col-md-10">
		                <label id="issue_summary"  >${issue_summary}</label>
               				<%-- <input type="text" id="issue_summary" name="issue_summary" placeholder="Maximum 100 characters" value="${issue_summary}" class="form-control"  autocomplete="off" maxlength="100" readonly="readonly"></input> --%>
						</div>
               </div>
               <div class="row mb-2">	
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Description (Max 1000 words)</label>
		                </div>
		                <div class="col-md-10">
		                <label id="description"  >${description}</label>
						 <%-- <textarea rows="2" cols="250" id="description" style="height:150px;" name="description" class="form-control" autocomplete="off" maxlength="1000" readonly="readonly">${description}</textarea> --%>
						</div>
                </div>
	        </div>
	        </div> <!-- end of card-body -->
                      </div> <!-- end of card -->
            <div style="text-align:center;">
               <button id="printId" class="btn btn-primary btn-sm btn_report" onclick="printDiv('printableArea');"> <i class="fa fa-print"></i> Print</button>
            </div>
	</div> <!-- end of container -->

<div id="printableArea" style="display: none;">
<div  class="watermarked" data-watermark="" id="divShow" style="display: block;">
	 	</div>
</div>
<%-- </form:form> --%>
 <c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled"/>
</c:if>
<c:url value="getmodule_help" var="imageDownloadUrl" />
        <form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="id1">
        	<input type="hidden" name="id1" id="id1" value="0"/>
        	<input type="hidden" name="pageUrl" id="pageUrl" value=""/>
        	<input type="hidden" name="contraint" id="contraint" value=""/>
        </form:form>  
        <script>
      
        function getmodule_help(id)
        {  
    	   document.getElementById("id1").value=id;
    	   document.getElementById("contraint").value="";
    	   document.getElementById("pageUrl").value="user_ticketTiles";
           document.getElementById("getDownloadImageForm").submit();
        }  
</script>
<script type="text/javascript">
 
$(document).ready(function() {
	if('${msg}'!=""){
		alert($("#msg").val());		
	}
	 if('${screen_shot}'!=""){
		 $("div#d1").show();
		 $("div#d2").hide();
	  } else{
		 $("div#d1").hide();
		 $("div#d2").show(); 
	 }
  	
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	if('${ticket_status}' == '0')
	{
		var date = '${created_on}';
	 	$("input#date1").text(date);
	}
	if('${ticket_status}' == '1')
	{
		var date = '${assigned_dt}';
	 	$("input#assigned_dt").val(date);
	}
	if('${ticket_status}' == '2')
	{
		var date = '${completed_dt}';
	 	$("input#completed_dt").val(date);
	}
});
var created_on = '${created_on}'
created_on = created_on.substring(0, 10); 
$("#date1").text( ParseDateColumncommission(created_on));
</script>  

<script>
function printDiv() 
{
	$("div#divwatermark").val('').addClass('watermarked'); 
  	watermarkreport();
	var printLbl = [];
	var printVal = [];
	 if('${ticket_status}' == "0"){
		status = "New";
		printLbl = ["Ticket Id :","Created on :","Created by :", "Module :", "Sub Module :", "Screen Name :", "Help Topic :","Status:","Issue summary :","Description :"];
		 printVal = ['${ticket}','${created_on}','${username}','${moduleName}','${sub_module}','${screen_name}','${help_topic}',status,'${issue_summary}','${description}'];
	}else if('${ticket_status}' == "1"){
		status = "In Progress";
		printLbl = ["Ticket Id :","Created on :","Created by :", "Module :", "Sub Module :", "Screen Name :", "Help Topic :","Assign on :","Status:","Issue summary :","Description :"];
		 printVal = ['${ticket}','${created_on}','${username}','${moduleName}','${sub_module}','${screen_name}','${help_topic}','${assigned_dt}',status,'${issue_summary}','${description}'];
	}else if('${ticket_status}' == "2"){
		status = "Completed";
		printLbl = ["Ticket Id :","Created on :","Created by :", "Module :", "Sub Module :", "Screen Name :", "Help Topic :","Assign on :","Status:", "completed on:","Issue summary :","Description :"];
		 printVal = ['${ticket}','${created_on}','${username}','${moduleName}','${sub_module}','${screen_name}','${help_topic}','${assigned_dt}',status,'${completed_dt}','${issue_summary}','${description}'];
	}else if('${ticket_status}' == "3"){
		status = "Feedback";
	}else if('${ticket_status}' == "4"){
		status = "Feature Request";
	}
	printDivOptimizehelp('printableArea','Ticket Details',printLbl,printVal,"");
	
 }
</script>