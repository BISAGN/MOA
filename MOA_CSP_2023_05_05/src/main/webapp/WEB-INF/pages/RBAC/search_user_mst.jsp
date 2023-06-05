<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!-- <script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script>  -->
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
<link rel="stylesheet" href="js/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/watermark/watermark.css">
<script src="js/watermark/watermark_onclick.js" type="text/javascript"></script>
<script src="js/watermark/printallpages.js" type="text/javascript"></script> 
<link href="js/amin_module/rbac/datatables/jquery.dataTables.min.css" rel="stylesheet"> 
<script src="js/amin_module/rbac/datatables/jquery.dataTables.js"></script>
<link rel="stylesheet" href="js/amin_module/rbac/report/criteriareportdesign.css">
<!-- INTERNAL REMOVE START (NEED TO ADD IN PARTICULAR JSP)-->
			<link rel="stylesheet" href="assets/vendor/internal_css.css">
<!-- INTERNAL REMOVE END--> 


	<script nonce="${cspNonce}" type="text/javascript">
		window.history.forward();
		function noBack() {
			window.history.forward();
		}
		$(document).ready(function () {
			$('html').bind('cut copy paste', function (e) {
				e.preventDefault();
		    });
			$("html").on("contextmenu",function(e){
				return false;
		    }); 
		});
	</script>
</head>
<body>


<section class="dashboard-page">
<div class="container-fluid">
	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>Search User Master</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item">
								<a href="#">Dashboard</a>
							</li>
							<li class="breadcrumb-item active" aria-current="page">Search User Master</li>
						</ol>
					</nav>
				</div>
			</div>
			<!-- end col -->
		</div>
		<!-- end row -->
	</div>
	<div class="form-elements-wrapper">
		<div class="row">
			<div class="col-12">
				<!-- input style start -->
       	<form:form name="search_user_mst" id="search_user_mst" action="#" method='POST'>
				<div class="card-style mb-30">
					<h6 class="mb-25">Search User Master</h6>
						<div class="row">

							
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
						                  <label>Search By <span class="mandatory">*</span></label>
						                  <div class="select-position">
						                     <select name="access_lvl" class="form-control" id ="access_lvl"  > <!--onchange="getaccess_lev(this.value);" -->
		             							<option value="">--Select--</option>
		             							<option value="All">All</option>
		             							<option value="Username">User Id</option>
		             						 </select>
						                  </div>
						           </div>
							</div>
							
						<div id="username_div" style="display: none;">	
				
					<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-1">
				                  <label>User Id<span class="mandatory">*</span></label>
				                 <input  id="login_name" class="form-control" name="login_name"  maxlength="70" class="autocomplete UpperClassName txt-transupp" 
				                 placeholder=" Search"  autocomplete="off">
				                </div>
							</div>		
	 				</div>
								
						</div>	
													
							
						
					
						<ul class="buttons-group mainbtn">
							<li>
								<a class="main-btn secondary-btn btn-hover btn-iconic-icon" id="btn-search"><i class="lni lni-search-alt"></i>Search</a>
							</li>
							<li>
								<input type="reset"  class="main-btn dark-btn btn-hover" value="Reset" id="btn-reset">
							</li>
							
							<li>
								<a id="printId" class="main-btn success-btn-outline btn-hover btn-iconic-icon" disabled><i class="lni lni-printer"></i>Print</a>
							</li>
							
						</ul>
						
							<div class="container"  id="divPrint" class="custom_user_status_divShow">	
		<div id="divShow"></div>		
		<div class="watermarked" data-watermark="" id="divwatermark"class="custom_user_status_watermarked">
			<span id="ip"></span>
			<datatables:table  id="applicanttbl1" url="getUserReportList" serverSide="true" pipelining="true" pipeSize="3"	row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_"  displayLength="10" lengthMenu="10,20,100,500,5000" jqueryUI="true" 
				bDestroy="true" filterable="false" sortable="true" processing="true" border="1"  autoWidth="true" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true"  scrollX="100%"  scrollCollapse="true" >  
			     <datatables:column title="Id" property="userid" searchable="false" data-halign="left" data-valign="left" />
			     <datatables:column title="User Name" property="login_name" searchable="false" data-halign="left" data-valign="left" />
			     <datatables:column title="User Id" property="username" searchable="false" data-halign="left" data-valign="left" />
			     <datatables:column title="Role" property="role" searchable="false" data-halign="left" data-valign="left" />
			     <datatables:column title="Access Level" property="access_lvl" searchable="false" data-halign="left" data-valign="left" />
			     <datatables:column title="Sub Access level" property="sub_access_lvl" searchable="false" data-halign="left" data-valign="left" />
			     <datatables:column title="Action" property="Action" searchable="false" sortable="false" id='thAction' /> <!--  id='thAction'  -->
			</datatables:table>  
		</div>		
	</div>
									
				
				</div>
				<!-- end card -->
            </form:form>
			</div>
		</div>
	</div>

	



</div>
</section>


<%-- 	<form:form name="search_user_mst" id="search_user_mst" action="#" method='POST'>
<div class="container">
     <div class="card">
         		<div class="card-header"> <h5>Search User Master</h5><!-- <strong>Schedule Details </strong> --></div>
         		<div class="card-body card-block" >
         			<div class="row mb-3">								
               					<div class="col-md-2">
                 					<label for="text-input"><strong style="color: red;">*</strong> <B>Search By &emsp;: </B></label> 
               					</div>
               					<div class="col-md-3">
               						<select name="access_lvl" class="form-control" id ="access_lvl"  onchange="access_lev(this.value)"> <!--onchange="getaccess_lev(this.value);" -->
             							<option value="">--Select--</option>
             							<option value="All">All</option>
             							<option value="Username">User Id</option>
             						 </select>
               					</div>
 					</div> 	
 					<div id="username_div" style="display: none;">			 
         			<div class="row mb-3">
	               				<div class="col-md-2">
	                				<label for="text-input" ><strong style="color: red;">*</strong> <B>User Id &emsp;:</B> </label> 
	               				</div>
	               				<div class="col-md-3">
	                				<input  id="login_name" class="form-control" name="login_name"  maxlength="70" style="font-family: 'FontAwesome',Arial;"  placeholder="&#xF002; Search"  autocomplete="off">
								</div>
	 				</div>
	 				</div>
	 			</div> <!-- end of card-body -->
	 			
         		<div class="card-footer">
         	    	<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall();">   
	              	<i class="fa fa-search"></i><input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">
					<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
				</div> <!-- end of card-footer -->
				
     </div> <!-- end of card -->
</div> <!-- end of container -->
	</form:form
	<div class="container"  id="divPrint" style="display: none">	
		<div id="divShow"></div>		
		<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
			<span id="ip"></span>
			<datatables:table  id="applicanttbl1" url="getUserReportList" serverSide="true" pipelining="true" pipeSize="3"	row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_"  displayLength="10" lengthMenu="10,20,100,500,5000" jqueryUI="true" 
				bDestroy="true" filterable="false" sortable="true" processing="true" border="1"  autoWidth="true" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true"  scrollX="100%"  scrollCollapse="true" >  
			     <datatables:column title="Id" property="userid" searchable="false" data-halign="left" data-valign="left" />
			     <datatables:column title="User Name" property="login_name" searchable="false" data-halign="left" data-valign="left" />
			     <datatables:column title="User Id" property="username" searchable="false" data-halign="left" data-valign="left" />
			     <datatables:column title="Role" property="role" searchable="false" data-halign="left" data-valign="left" />
			     <datatables:column title="Access Level" property="access_lvl" searchable="false" data-halign="left" data-valign="left" />
			     <datatables:column title="Sub Access level" property="sub_access_lvl" searchable="false" data-halign="left" data-valign="left" />
			     <datatables:column title="Action" property="Action" searchable="false" sortable="false" id='thAction' /> <!--  id='thAction'  -->
			</datatables:table>  
		</div>		
	</div> --%>

	<c:url value="search_user_by_role" var="searchUrl" />
		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="">
		<input type="hidden" name="access_lvl1" id="access_lvl1" value="0"/>
		<input type="hidden" name="user_name" id="user_name" value=""/>
	</form:form> 
 		
	<c:url value="update_user_mstUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
 
	<c:url value="search_user_mstUrl" var="mainFormUrl" />
	<form:form action="${mainFormUrl}" method="GET" id="mainForm" name="mainForm" ></form:form>
<script nonce="${cspNonce}" type="text/javascript">
function getUsername() {
	var wepetext=$("#login_name");
	wepetext.autocomplete({
		source: function( request, response ) {
			$.ajax({
	        	type: 'POST',
	        	url: "getUsernameList?"+key+"="+value,
	        	data: {userName:$("#login_name").val()},
	          	success: function( data ) {
					var susval = [];
	        	  	var length = data.length-1;
	        	  	var enc = data[length].substring(0,16);
		        	for(var i = 0;i<data.length;i++){
		        		susval.push(dec(enc,data[i]));
		        	}
		        	response( susval ); 
	          	}
	        });
		},
		minLength: 1,
		autoFill: true,     	     
	});
}
document.addEventListener('DOMContentLoaded', function () {		
	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	document.getElementById('btn-search').onclick = function() {
		return Search();
	};
	document.getElementById('btn-reset').onclick = function() {
		return clearall();
	};
	document.getElementById('printId').onclick = function() {
		return printDiv();
	};
	document.getElementById('access_lvl').onchange = function() {
		return access_lev(this.value);
	};

});
$(document).ready(function () {
	getUsername();
	if('${access_lvl1}' != ""){
		$("select#access_lvl").val('${access_lvl1}');
		
		if('${access_lvl1}' == "Username"){
			$("div#username_div").show();
	   		$("#login_name").show();
	   		$("#login_name").val('${user_name}');
	   	} 
		$("div#divwatermark").val('').addClass('watermarked'); 
   		watermarkreport();
   		$("#divPrint").show();
   		document.getElementById("printId").disabled = false;	
   	}
});
function Search(){
	if($("#access_lvl").val() == "")
	{
		alert("Please Select Options");
		return false;
	}
	else{
		if($("#access_lvl").val() == "Username"){
			if($("#login_name").val()!=""){
				$("#user_name").val($("#login_name").val());
			}
			else{
				alert("Please Enter Username");
				return false;
			} 
		}
		$("#access_lvl1").val($("#access_lvl").val());
		document.getElementById('searchForm').submit();
	}
}
var access_lvl,sub_access_lvl,role_id;
function access_lev(v)
{
	$("#login_name").val(""); 	
	var sub_lvl="";
	if((v != "" ) || (v != '' ) ){ 		
		if(v == "All"){
			document.getElementById('username_div').style.display='none';
		}
		else if(v == "Username"){
			document.getElementById('username_div').style.display='block';
			document.getElementById('login_name').style.display='block';
			getUsername();
		}
		else{
			document.getElementById('username_div').style.display='none';
		 	document.getElementById('login_name').value="";
		 	document.getElementById('login_name').style.display='none';
		}	 
	}else{
		document.getElementById('username_div').style.display='none';
	 	document.getElementById('login_name').value="";
	 	document.getElementById('login_name').style.display='none';
		alert("Access level is not defined.");
	}
} 
function clearall()
{		
	document.getElementById('divPrint').style.display='none';
	document.getElementById("printId").disabled = true;
	$("div#username_div").hide();
	$("#login_name").hide();
}
function printDiv() {
	var printLbl = [];
	var printVal = [];
	printDivOptimize('divPrint','Search User',printLbl,printVal);
}
</script>
<script nonce="${cspNonce}" type="text/javascript">
var newWin;
function editData(userid){	
	document.getElementById('updateid').value=userid;
	document.getElementById('updateForm').submit();
}
</script>