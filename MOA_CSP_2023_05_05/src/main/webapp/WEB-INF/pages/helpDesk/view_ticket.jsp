<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>
<script type="text/javascript" src="js/printWatermark/common.js"></script>
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/watermark/watermark.css">
<link rel="stylesheet" href="layout_file/css/bootstrap.min.css">
<link rel="stylesheet" href="layout_file/css/style.css">
<script src="js/watermark/printallpages_old.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>
<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script>
var username="${username}";

$(document).ready(function() {
	 if('${msg1}' != ""){
		 alert('${msg1}');	 
	 }
});
</script>
<form:form name="viewticket" id="viewticket" action="viewticketAction" method='POST' modelAttribute="viewticketCMD">

<div class="container">
	<div class="card">
		<div class="card-header"> <h5>view Ticket</h5></div> <!-- end of card-header -->
		<div class="card-body card-block">
		<div  class="watermarked report_print" data-watermark="" id="divwatermark" style="display: block;">
		   <div class="row mb-2">
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">Ticket Id</label>
		                </div>
		                <div class="col-md-4">
		                 <input type="hidden" id="id" name="id" value="${id}"/>
		                 <label id="ticket"  >${ticket}</label>
		           <%--      <input id="ticket" name="ticket" class="form-control" autocomplete="off" value="${ticket}" readonly="readonly"> --%>
						</div>
               </div>
               <div class="row mb-2"> 
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">User</label>
		                </div>
		                <div class="col-md-4">
		                <label id="username"  >${username}</label>
		                <%-- <input id="username" name="username" class="form-control" placeholder=" " autocomplete="off" value="${username}" readonly="readonly"> --%>
						</div> 
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">Date </label>
		                </div>
		                <div class="col-md-4">
		                <label id="date1">${created_on}</label>
		                <%-- <input id="date1" name="date1" class="form-control" readonly="readonly" value="${created_on}"></input> --%>
		                <input type="hidden" id=completed_dt name="completed_dt" class="form-control" readonly="readonly" value="${completed_dt}"></input>
		                 <input type="hidden" id=assigned_dt name="assigned_dt" class="form-control" readonly="readonly" value="${assigned_dt}"></input>
						</div>
               <%--  <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	<label for="text-input" class=" form-control-label">SUS No </label>
		                </div>
		                <div class="col-12 col-md-8">
		                <label id="sus_no"  >${sus_no}</label>
               				<input id="sus_no" name="sus_no" class="form-control" placeholder="" value="${sus_no}"   readonly="readonly">
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
               				<input id="unit_name" name="unit_name" class="form-control"placeholder=""  value="${unit_name}"  readonly="readonly">
						</div>
	             	</div>
               </div> --%> 
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">Module  </label>
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
		                   <%--  <input  id="sub_module"  class="form-control"  readonly="readonly" value="${sub_module}"> --%>
						</div> 
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">Screen Name </label>
		                </div>
		                <div class="col-md-4">
		                 <label id="screen_name"  >${screen_name}</label>
		                   <%--  <input  id="screen_name"  class="form-control"  readonly="readonly" value="${screen_name}" > --%>
						</div>
           </div>
			<div class="row mb-2"> 
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">Help Topic  </label>
		                </div>
		                <div class="col-md-4">
		                 <label id="help_topic"  >${help_topic}</label>
               				<%-- <input  id="help_topic"  class="form-control" readonly="readonly" value="${help_topic}">  --%>
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
	                	<label for="text-input" class=" form-control-label">Issue summary </label>
	                </div>
	                <div class="col-md-10">
	                <label id="issue_summary"  >${issue_summary}</label>
	             		<%-- <input type="text" id="issue_summary" name="issue_summary" placeholder="Maximum 100 characters" value="${issue_summary}" class="form-control"  autocomplete="off" maxlength="100" readonly="readonly"></input> --%>
					</div>
               </div>
              <div class="row mb-2">	
            	<div class="col-md-2">	             		
                	<label for="text-input" class=" form-control-label">Description (Max 1000 words)</label>
				</div>
           		<div class="col-md-10">
           		<label id="description"  >${description}</label>
					<%-- <textarea rows="2" cols="250" id="description" style="height:150px;" name="description" class="form-control" autocomplete="off" maxlength="1000" readonly="readonly">${description}</textarea> --%>
				</div>
            </div>
            <div class="row mb-2">
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class="form-control-label">Assign To User <strong style="color: red;">*</strong></label>
		                </div>
		                <div class="col-md-4">
		                	<input type="hidden" id="agent_name_hid" class="form-control" value="${assigned_to}">
               				<select id="agent_name" name="agent_name" class="form-control"></select>
						</div>
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label">Status <strong style="color: red;">*</strong>
							</label>
						</div>
						<div class="col-md-4">
							<input type="hidden" id="ticket_status_hid" class="form-control" value="${ticket_status}">
							<select name="ticket_status" id="ticket_status" class="form-control">
								<option   value="0">--Select--</option>
								<option   value="1">In Progress</option>
								<option   value="2">Completed</option>
							</select>
						</div>
                </div>
	</div>
	</div><!-- end of card-body -->
             </div> <!-- end of card -->
                  <div style="text-align:center;">
                   <input id="update1" type="submit" class="btn btn-secondary btn-sm" value="Update" onclick="return isvalidData();"> 
                          <i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv('printableArea');">
                    	<!-- <button id="printId" class="btn btn-primary btn-sm btn_report" onclick="printDiv('printableArea');"> <i class="fa fa-print"></i> Print</button> -->
                  </div>
  </div> <!-- end of container -->
       		
	
<div id="printableArea" style="display: none;">
<div  class="watermarked" id="divShow" style="display: block;">
	 	</div>
	 	
</div>                     
</form:form>
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
       	   	document.getElementById("contraint").value="Edit";
       	   	document.getElementById("pageUrl").value="viewticketTiles";
           	document.getElementById("getDownloadImageForm").submit();
       	}  
</script>
<script>
function isvalidData(){
	var status = document.getElementById('ticket_status').value;
 if($("#agent_name").val() == "0"){
		alert("Please Select Agent Name");
		$("#agent_name").focus();
		return false;
	}
 if(status == "0" ){
		alert("Please Select Status");
		$("#ticket_status").focus();
		return false;
	} 
 return true;
}
</script> 
<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled" />
</c:if>
<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	
 	 if('${screen_shot}'!= ""){
 		 $("div#d1").show();
 		 $("div#d2").hide();
	  }else{
 		 $("div#d1").hide();
		 $("div#d2").show(); 
 	 }

     var created_on = '${created_on}'
 	created_on = created_on.substring(0, 10); 
     $("#date1").text( ParseDateColumncommission(created_on));
		 
	if('${ticket_status}' == '0')
	{
		var date = '${created_on}';
	 	$("input#date1").val(date);
	}
	if('${ticket_status}' == '1')
	{
		$("#ticket_status").val('${ticket_status}');
		$("#agent_name").val('${assigned_to}');
		var date = '${assigned_dt}';
	 	$("input#assigned_dt").val(date);
	}
	if('${ticket_status}' == '2')
	{
		$("#ticket_status").val('${ticket_status}');
		$("#agent_name").val('${assigned_to}');
		var date = '${completed_dt}';
	 	$("input#completed_dt").val(date);
	 	
	 	 document.getElementById("ticket_status").disabled = true;
	 	 document.getElementById("agent_name").disabled = true;
	 	 
		 $("input#update1").hide();
	}
	if('${msg}'!=""){
		alert($("#msg").val());		
	}
	var module_hid = document.getElementById("moduleHid").value;
	
	if('${moduleId}' == 'others'){
		module_hid = '0';
	}else{
		module_hid = document.getElementById("moduleHid").value;
	}
	
	var key = "${_csrf.parameterName}";
 	var value = "${_csrf.token}";
   	$.post("getUserNameList?"+key+"="+value,{module_id : module_hid}, function(j) {
	   var agent_name_hid = $("#agent_name_hid").val();
		var options = '<option   value="0">'+ "--Select--" + '</option>';
		for ( var i = 0; i < j.length; i++) {
			if(agent_name_hid == j[i][1] ){
				options += '<option   value="' + j[i][1] + '" name="'+j[i][0]+'" selected=selected>'+ j[i][1] + '</option>';
			}	
			else{
				options += '<option   value="' + j[i][1] + '" name="'+j[i][0]+'" >'+ j[i][1] + '</option>';
			}
		}
		$("select#agent_name").html(options);
	});
});
/* function printDiv() 
{
	$("div#divwatermark").val('').addClass('watermarked'); 
  	watermarkreport();
	var printLbl = [];
	var printVal = [];
	 if('${ticket_status}' == "0"){
		status = "New";
		printLbl = ["Ticket Id :","Created on :","Created by :", "SUS No :", "Unit  :", "Module :", "Sub Module :", "Screen Name :", "Help Topic :","Assign to :","Issue summary :","Status:","Description :"];
		 printVal = [document.getElementById('ticket').innerHTML,document.getElementById('date1').innerHTML,document.getElementById('username').innerHTML,document.getElementById('sus_no').innerHTML,document.getElementById('unit_name').innerHTML,document.getElementById('module').innerHTML,document.getElementById('sub_module').innerHTML,document.getElementById('screen_name').innerHTML,document.getElementById('help_topic').innerHTML,document.getElementById('agent_name').value,document.getElementById('issue_summary').innerHTML,status,document.getElementById('description').innerHTML];
	}else if('${ticket_status}' == "1"){
		status = "In Progress";
		printLbl = ["Ticket Id :","Created on :","Created by :", "SUS No :", "Unit  :", "Module :", "Sub Module :", "Screen Name :", "Help Topic :","Assign to :","Assign on :","Issue summary :","Status:","Description :"];
		 printVal = [document.getElementById('ticket').innerHTML,document.getElementById('date1').innerHTML,document.getElementById('username').innerHTML,document.getElementById('sus_no').innerHTML,document.getElementById('unit_name').innerHTML,document.getElementById('module').innerHTML,document.getElementById('sub_module').innerHTML,document.getElementById('screen_name').innerHTML,document.getElementById('help_topic').innerHTML,document.getElementById('agent_name').value,document.getElementById('assigned_dt').value,document.getElementById('issue_summary').innerHTML,status,document.getElementById('description').innerHTML];
	}else if('${ticket_status}' == "2"){
		status = "Completed";
		printLbl = ["Ticket Id :","Created on :","Created by :", "SUS No :", "Unit  :", "Module :", "Sub Module :", "Screen Name :", "Help Topic :","Assign to :","Assign on :","completed on:","Issue summary :","Status:","Description :"];
		 printVal = [document.getElementById('ticket').innerHTML,document.getElementById('date1').innerHTML,document.getElementById('username').innerHTML,document.getElementById('sus_no').innerHTML,document.getElementById('unit_name').innerHTML,document.getElementById('module').innerHTML,document.getElementById('sub_module').innerHTML,document.getElementById('screen_name').innerHTML,document.getElementById('help_topic').innerHTML,document.getElementById('agent_name').value,document.getElementById('assigned_dt').value,document.getElementById('completed_dt').innerHTML,document.getElementById('issue_summary').innerHTML,status,document.getElementById('description').innerHTML];
	}else if('${ticket_status}' == "3"){
		status = "Feedback";
	}else if('${ticket_status}' == "4"){
		status = "Feature Request";
	}
	printDivOptimizehelp('printableArea','Ticket Details',printLbl,printVal,"select#status");
 } */
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
		 printVal = ['${ticket}','${created_on}','${username}','${moduleName}','${sub_module}','${screen_name}','${help_topic}',document.getElementById('assigned_dt').innerHTML,status,'${issue_summary}','${description}'];
	}else if('${ticket_status}' == "2"){
		status = "Completed";
		printLbl = ["Ticket Id :","Created on :","Created by :", "Module :", "Sub Module :", "Screen Name :", "Help Topic :","Assign on :","Status:", "completed on:","Issue summary :","Description :"];
		 printVal = ['${ticket}','${created_on}','${username}','${moduleName}','${sub_module}','${screen_name}','${help_topic}',document.getElementById('assigned_dt').innerHTML,status,document.getElementById('completed_dt').innerHTML,'${issue_summary}','${description}'];
	}else if('${ticket_status}' == "3"){
		status = "Feedback";
	}else if('${ticket_status}' == "4"){
		status = "Feature Request";
	}
	printDivOptimizehelp('printableArea','Ticket Details',printLbl,printVal,"");
	
 }
</script>