<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script>
 <script type="text/javascript" src="js/JS_CSS/wepe_cce.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<link href="js/tunneldesign/tunnelold.css" rel="Stylesheet"></link>

<style>
div#ui-datepicker-div {
    width: min-content !important;
}

/* div#ui-datepicker-div table thead { */
/*     width: 100% !important; */
/*     background-color: #050d4f; */
/* } */
/* div#ui-datepicker-div table tbody { */
/*     height: auto !important; */
/* } */
/* .ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-all { */
/*     background: #198754 !important; */
/* } */
/* table thead { */
/*     display: block; */
/*     width: 100%!IMPORTANT; */
/* } */
/* img.ui-datepicker-trigger { */
/* 	cursor: pointer; */
/* 	vertical-align: middle; */
/* 	position: absolute; */
/* 	top: 5px; */
/* 	right: 8px; */
/* } */

</style>




 <section class="dashboard-page">
<div class="container-fluid">
	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2><span id="lbladd1"></span>Experience Details</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item">
								<a href="commonDashboard">DAshboard</a>
							</li>
							<li class="breadcrumb-item active" aria-current="page">Experience Details</li>
						</ol>
					</nav>
				</div>
			</div>
			<!-- end col -->
		</div>
		<!-- end row -->
	</div>
	
	
<div class="card-style mb-30">
			 

<div class="tunnel_design row">
	 <div class="square tunnel_visited">
	 <a href="#" class="tunnel_text" onclick="if(confirm('Are you sure you want to Proceed?')){getPreviousPage();}else{return false;}"> Personal Details</a></div> 
	 
	 <div class="square tunnel_visited" >
	 <a href="#" class="tunnel_text" onclick="if(confirm('Are you sure you want to Proceed?')){getEduPage();}else{return false;}" href="Edu_Det_Url"class="tunnel_text"> Basic Education Details</a></div> 

 	<div class="square tunnel_active" id="divexpreq" style="display: block;"><h5 class="tunnel_text" >Experience Details</h5></div>
	 
<!--      <div class="square"><h5 class="tunnel_text">Upload Document</h5></div> -->
<!-- 	 <div class="square"><h5 class="tunnel_text">Choice Filling</h5></div> -->

	<div class="square ">
	<a href="#" class="tunnel_text" onclick="if(confirm('Are you sure you want to Proceed?')){getDocPage();}else{return false;}" href="doc_uploadUrl" >Upload Document</a></div>
	<div class="square">
	 <a href="#" onclick="if(confirm('Are you sure you want to Proceed?')){getchoicePage();}else{return false;}" href="Reshuffling_Url"><h5 class="tunnel_text"> Choice Filling</h5></a></div>

</div>

	</div>

	<div class="form-elements-wrapper">
		<div class="row">
			<div class="col-12">
				<!-- input style start -->
               <form:form name="Exp_detAction" id="Exp_detAction" action="Exp_detAction" method='POST' modelattribute="Exp_detcmd" enctype="multipart/form-data">
				<div class="card-style mb-30">
					<h6 class="mb-25">Experience Details</h6>
						
						
						<div class="row">
	<div class="col-12">
		<div class="card-style mb-30">
			<div class="table-experience-width">
			<div class="table-wrapper table-responsive custom-datatable-p simple-table">
						<table class="table " id="att_Tb">
							<thead>
								<tr>
									<th rowspan="2" style="">Name of Employer</th> 
									<th colspan="2" style="border-bottom: 1px solid #405972;">Period of Employment</th>
									<th colspan="3" style="border-bottom: 1px solid #405972;">Total</th>
									<th rowspan="2" style=" display: none;">Nature of Work Done</th>
									<th rowspan="2" style=" display: none;">Monthly Salary Drawn</th>
									<th rowspan="2" style=" display: none; ">Whether Permanent/ Temporary</th>
									<th rowspan="2" style=""><label style="color: #f36767;">[Maximum file size upto 200 kb and Allowed only *.pdf File]</label></th>
								</tr>
								<tr>
									<th style="">From</th> 		
									<th style="">To</th> 
									<th>Years</th>
									<th>Months</th>
									<th>Days</th>
								</tr>
							</thead>
							<tbody id="att_Tbbody" >
								<tr>
				    <td>
				    	<div class="input-style-1">
		                
		                  <input type="hidden" id="exp_id" name="exp_id_hid" class="form-control"  autocomplete="off" />
		                    <input id="exp_emp_name" name="exp_emp_name" class="form-control" maxlength="50" autocomplete="off" placeholder="Enter Employee Name" onkeypress="return onlyAlphabetsStringSpace(event,this)" cssStyle="text-transform: uppercase;"></input>
					    <span class="errorClass" id="exp_emp_name_lbl"></span>
		                </div>
				    </td>
				   <td style="display: none;"  id="gst_tin_reg_no_td">
				    	<input id="exp_emp_regno_gst_pin" name="exp_emp_regno_gst_pin" maxlength='50' class="form-control" autocomplete="off"  cssStyle="text-transform: uppercase;"></input>
				     	<span class="errorClass" id="exp_emp_regno_gst_pin_lbl"></span> 
				     </td>

				    <td style="display: none;" >
				    	<textarea id="exp_emp_address" name="exp_emp_address" maxlength="250" class="form-control" autocomplete="off" cssStyle="text-transform: uppercase;"></textarea>
				  		<span class="errorClass" id="exp_emp_address_lbl" ></span>
				   </td>
				    <td >
				    	<div class="input-style-2">
					
					<input type="text" name="exp_emp_fromdate" id="exp_emp_fromdate"
												maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control-sm form-control effect-9 " style="display: inline;"
												onfocus="this.style.color='#000000'"
												onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
												onkeyup="clickclear(this, 'DD/MM/YYYY')"
												onchange="onchangeCount(this.value);clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this); "
												aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"/>
				</div>	
				    	
				    	<input type="hidden" id="exp_emp_fromdatehidden" class="form-control" ></input>
				     	<span class="errorClass" id="exp_emp_fromdate_lbl" ></span>
				    </td>				   
				    <td>
				    	<div class="input-style-2">
				     	<input type="text" name="exp_emp_todate" id="exp_emp_todate"
												maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control-sm form-control effect-9 " style=" display: inline;"
												onfocus="this.style.color='#000000'"
												onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
												onkeyup="clickclear(this, 'DD/MM/YYYY')"
												onchange="onchangeCount(this.value);clickrecall(this,'DD/MM/YYYY'); validateDate_FutureDate(this.value,this);"
												aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"/>
						</div>						
				     	<span class="errorClass" id="exp_emp_todate_lbl" ></span>
				     	<input type="hidden" id="exp_emp_todatehidden" class="form-control" /> 
				     	<input type="hidden" id="count" name="count" class="form-control" value="0"></input>
				    </td> 	
				    
				   <td>
				 	   <div class="input-style-1">
		                
							<input id="exp_total_year" name="exp_total_year" class="form-control"   autocomplete="off" ></input>
		                </div>
				  
				   </td>
				   <td>
				   		 <div class="input-style-1">
		                
							   <input id="exp_total_month" name="exp_total_month" class="form-control"  autocomplete="off" ></input>
		                </div>
				  </td>
				   
	           	   <td>
	           	   		 <div class="input-style-1">
		                
							 <input id="exp_total_day" name="exp_total_day" class="form-control"    autocomplete="off" ></input>
	           	   			 <input type="hidden" id="no_days_check" name="no_days_check" class="form-control"    autocomplete="off"></input>
		                </div>
				    	
	           	   </td>
	               <td style=" display: none;">
	                	<textarea id="exp_emp_work" name="exp_emp_work" class="form-control" maxlength="100" autocomplete="off" cssStyle="text-transform: uppercase;"></textarea>
	               		<span class="errorClass" id="exp_emp_work_lbl" ></span>
	               </td>
	               <td style="display: none;" >
	               		<input id="exp_emp_salary" name="exp_emp_salary"  maxlength="6" onkeypress="return isNumberKey(event, this);"  class="form-control" autocomplete="off" ></input>
	               		<span class="errorClass" id="exp_emp_salary_lbl" ></span>
	               </td>
	               <td class="select-wrapper" style="display: none;" >
	               		<select id="exp_emp_perm_temp" name="exp_emp_perm_temp" class='form-control'>
	                		<option value="" >--Select--</option>
							<option value="Permanent">Permanent</option>
				         	<option value="Temporary">Temporary</option>
			         	</select>
			         	<span class="errorClass" id="exp_emp_perm_temp_lbl" ></span>
			         	 <input type="hidden" id="exp_emp_perm_temphid"   class="form-control"></input>
			       </td>
			       <td>
			       		 <div class="input-style-2">
									
							<input type="file" accept=".pdf" id="exp_emp_document" name="exp_emp_document" class="form-control" 
			       		onchange="setValueOfNotificationPath()"></input>
               			<input type="hidden"  id="document_hidd" name="document_hidd"></input>
               			
               		 	<span class="errorClass" id="document_lbl"></span> 
               		 	<span class='tikClass' id="document_lbltik"></span>	
										
											
									</div>
			       		
			      </td>
		       </tr>	
							</tbody>
						</table>
						<!-- 				end table -->
			</div>
			</div>
		</div>
		<!-- 		end card -->
	</div>
	<!-- 	end col -->
</div>
						
											
						<ul class="buttons-group mainbtn">
							<li>
							 
							 <a id="aId" href="Edu_Det_Url" class="main-btn active-btn-outline  btn-hover btn-iconic-icon btn-iconic-left" onclick="if(confirm('Are you sure you want to Proceed?')){return getEduPage();}else{return false;}" ><i class="lni lni-chevron-left"></i> Previous</a>	
							
							</li>
							<li>
							 <input type="submit" id="save_btn" class="main-btn info-btn btn-hover"  onclick="return isValidateClientSide();" value="Save">
							</li>
							<li>
							
								<a href="#" class="main-btn dark-btn btn-hover"  id="clear_btn" onclick="clear_field();">Reset</a>
							</li>
							<li>
								<a id="aId" class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left" onclick="if(confirm('Are you sure you want to Proceed?')){return Experi_next();}else{return false;}">Next<i class="lni lni-chevron-right"></i></a>
							</li>
						</ul>
						
						<input type="hidden" name="p_id" id="p_id" value="0"/>
						<input type="hidden" name="id_org" id="id_org" value="0"/>
				</div>
	
				
				<!-- end card -->
            </form:form>
			</div>
		</div>
	</div>
 

<div class="row">
	<div class="col-12">
		<div class="card-style mb-30">
			<div class="table-wrapper table-responsive custom-datatable-p" id="qualification_tbl_div">
						<table class="table" id="search_experience_details_table">
							<thead>
								<tr>
									<th align="center">Ser No</th>
									<th id="2">Address of Employer</th>
									<th id="3">Period of Employment From</th>
									<th id="4">Period of Employment To</th>
									<th id="5">Total Years</th>
									<th id="6">Total Months</th>
									<th id="7">Total Days</th>
									<th  id="8">Download<br></th>
									<th class="action">Action</th>
								</tr>
								<!-- 						end table row -->
							</thead>
							<tbody>
							</tbody>
						</table>
						<!-- 				end table -->
			</div>
		</div>
		<!-- 		end card -->
	</div>
	<!-- 	end col -->
</div>


</div>
</section> 
<%-- 
<form:form name="Exp_detAction" id="Exp_detAction" action="Exp_detAction" method='POST' modelattribute="Exp_detcmd" enctype="multipart/form-data">
<div class="datatablediv" id="page-top">
	<div class="card">
		<div class="card-header"> <span id="lbladd"></span><h5>Experience Details</h5></div>
		<div class="card-body card-block" style="text-align: center;">
          	<label for="text-input" class=" form-control-label" style="color: maroon; font-size: 14px;"><b>Required Experience Details:</b></label>		               
        <div class="table-responsive">
		<table id="AttributeReport" class="table no-margin table-striped  table-hover  table-bordered">
			<thead>
				<tr>
					<th rowspan="2" style="">Name of Employer</th> 
					<th colspan="2" style="border-bottom: 1px solid #405972;">Period of Employment</th>
					<th colspan="3" style="border-bottom: 1px solid #405972;">Total</th>
					<th rowspan="2" style=" display: none;">Nature of Work Done</th>
					<th rowspan="2" style=" display: none;">Monthly Salary Drawn</th>
					<th rowspan="2" style=" display: none; ">Whether Permanent/ Temporary</th>
					<th rowspan="2" style=""><label style="color: #f36767;">[Maximum file size upto 200 kb and Allowed only *.pdf File]</label></th>
				</tr>
				<tr>
					<th style="">From</th> 		
					<th style="">To</th> 
					<th>Years</th>
					<th>Months</th>
					<th>Days</th>
				</tr>
			</thead>
			  <tbody>
				<tr>
				    <td>
					    <input type="hidden" id="exp_id" name="exp_id_hid" class="form-control"  autocomplete="off" />
					   <input id="exp_emp_name" name="exp_emp_name" class="form-control" maxlength="50" autocomplete="off" placeholder="Enter Employee Name" onkeypress="return onlyAlphabetsStringSpace(event,this)" cssStyle="text-transform: uppercase;"></input>
					    <span class="errorClass" id="exp_emp_name_lbl"></span>
				    </td>
				   <td style="display: none;"  id="gst_tin_reg_no_td">
				    	<input id="exp_emp_regno_gst_pin" name="exp_emp_regno_gst_pin" maxlength='50' class="form-control" autocomplete="off"  cssStyle="text-transform: uppercase;"></input>
				     	<span class="errorClass" id="exp_emp_regno_gst_pin_lbl"></span> 
				     </td>

				    <td style="display: none;" >
				    	<textarea id="exp_emp_address" name="exp_emp_address" maxlength="250" class="form-control" autocomplete="off" cssStyle="text-transform: uppercase;"></textarea>
				  		<span class="errorClass" id="exp_emp_address_lbl" ></span>
				   </td>
				    <td style="width:160px;">
				    	<input type="text" name="exp_emp_fromdate" id="exp_emp_fromdate"
												maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control-sm form-control effect-9 " style="width: 80%; display: inline;"
												onfocus="this.style.color='#000000'"
												onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
												onkeyup="clickclear(this, 'DD/MM/YYYY')"
												onchange="onchangeCount(this.value);clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this); "
												aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"/>
				    	<input type="hidden" id="exp_emp_fromdatehidden" class="form-control" ></input>
				     	<span class="errorClass" id="exp_emp_fromdate_lbl" ></span>
				    </td>				   
				    <td  style="width:160px;">
				     	<input type="text" name="exp_emp_todate" id="exp_emp_todate"
												maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control-sm form-control effect-9 " style="width: 80%; display: inline;"
												onfocus="this.style.color='#000000'"
												onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
												onkeyup="clickclear(this, 'DD/MM/YYYY')"
												onchange="onchangeCount(this.value);clickrecall(this,'DD/MM/YYYY'); validateDate_FutureDate(this.value,this);"
												aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"/>
												
				     	<span class="errorClass" id="exp_emp_todate_lbl" ></span>
				     	<input type="hidden" id="exp_emp_todatehidden" class="form-control" /> 
				     	<input type="hidden" id="count" name="count" class="form-control" value="0"></input>
				    </td> 	
				    
				   <td><input id="exp_total_year" name="exp_total_year" class="form-control"   autocomplete="off" ></input></td>
				   <td><input id="exp_total_month" name="exp_total_month" class="form-control"  autocomplete="off" ></input></td>
				   
	           	   <td>
				    	<input id="exp_total_day" name="exp_total_day" class="form-control"    autocomplete="off" ></input>
	           	   		<input type="hidden" id="no_days_check" name="no_days_check" class="form-control"    autocomplete="off"></input>
	           	   </td>
	               <td style=" display: none;">
	                	<textarea id="exp_emp_work" name="exp_emp_work" class="form-control" maxlength="100" autocomplete="off" cssStyle="text-transform: uppercase;"></textarea>
	               		<span class="errorClass" id="exp_emp_work_lbl" ></span>
	               </td>
	               <td style="display: none;" >
	               		<input id="exp_emp_salary" name="exp_emp_salary"  maxlength="6" onkeypress="return isNumberKey(event, this);"  class="form-control" autocomplete="off" ></input>
	               		<span class="errorClass" id="exp_emp_salary_lbl" ></span>
	               </td>
	               <td class="select-wrapper" style="display: none;" >
	               		<select id="exp_emp_perm_temp" name="exp_emp_perm_temp" class='form-control'>
	                		<option value="" >--Select--</option>
							<option value="Permanent">Permanent</option>
				         	<option value="Temporary">Temporary</option>
			         	</select>
			         	<span class="errorClass" id="exp_emp_perm_temp_lbl" ></span>
			         	 <input type="hidden" id="exp_emp_perm_temphid"   class="form-control"></input>
			       </td>
			       <td>
			       		<input type="file" accept=".pdf" id="exp_emp_document" name="exp_emp_document" class="form-control" 
			       		onchange="setValueOfNotificationPath()"></input>
               			<input type="hidden"  id="document_hidd" name="document_hidd"></input>
               			
               		 	<span class="errorClass" id="document_lbl"></span> 
               		 	<span class='tikClass' id="document_lbltik"></span>
			      </td>
		       </tr>		       
		</tbody>
		</table>
		</div>
	</div>
	<input type="hidden" name="p_id" id="p_id" value="0"/>
	<input type="hidden" name="id_org" id="id_org" value="0"/>
 	<div class="card-footer" align="center">
 	     <a id="aId" href="Edu_Det_Url" class="btn btn-info btn-sm" onclick="if(confirm('Are you sure you want to Proceed?')){return getEduPage();}else{return false;}"><i class="fa fa-angle-double-left"></i>&nbsp;&nbsp;Previous</a> 
	    <input type="submit" id="save_btn" class="btn btn-primary btn-sm" value="Save" onclick="return isValidateClientSide();">
		<a  class="btn btn-danger btn-sm"  id="clear_btn"  onclick="clear_field();">Clear</a>
		 <a id="aId" class="btn btn-secondary btn-sm" onclick="if(confirm('Are you sure you want to Proceed?')){return Experi_next();}else{return false;}">
			Next &nbsp;&nbsp;<i class="fa fa-angle-double-right"></i></a>    
	</div>
	
	<div class="card-body card-block" style="text-align: left;" id="qualification_tbl_div">
		<table id="search_experience_details_table" class="display table no-margin table-striped  table-hover  table-bordered">
			<thead>
				<tr>
					<th align="center">Ser No</th>
					<th id="2">Address of Employer</th>
					<th id="3">Period of Employment From</th>
					<th id="4">Period of Employment To</th>
					<th id="5">Total Years</th>
					<th id="6">Total Months</th>
					<th id="7">Total Days</th>
					<th  id="8">Download<br></th>
					<th class="action">Action</th>
				</tr>
			</thead>
		</table>
	</div> 
</div>
</div>
</form:form> --%>

<c:url value="getDownloadPdfUrlforexp_Doc" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="getDownloadPdfForm" name="getDownloadPdfForm" modelAttribute="document_id1">
	<input type="hidden" name="pageUrl" id="pageUrl" value="redirect:Total_Exp_Url"/>       		
	<input type="hidden" name="document_id1" id="document_id1" value=""/>		
</form:form>

<c:url value="delete_experience" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
	</form:form> 
	
<%-- <c:url value="doc_uploadUrl" var="expUrl" /> --%>
<!-- <input type="hidden" name="doc_eid" id="doc_eid" value="0"/>  -->
<%-- <form:form action="${expUrl}" method="GET" id="mainForm12" name="mainForm12" > --%>
<%-- </form:form> --%>

<c:url value="Edu_Det_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm1" name="applicationUrlForm1" modelAttribute="pers_edu_hid">
</form:form>
<br>

<!-- ---------------------------------------------------------------------- -->


<c:url value="Personal_Details_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm" name="applicationUrlForm" modelAttribute="pers_details_hid">
</form:form>

<c:url value="Edu_Det_Url" var="mainFormUrl1" />
<form:form action="${mainFormUrl1}" method="GET" id="mainForm11" name="mainForm11" modelAttribute="eid">
<input type="hidden" name="eid" id="eid" value="0"/>	
</form:form>

<c:url value="doc_uploadUrl" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm5" name="applicationUrlForm5" modelAttribute="doc_eid">
<input type="hidden" name="doc_eid" id="doc_eid" value="0"/>	
</form:form>

<c:url value="Reshuffling_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm6" name="applicationUrlForm6" modelAttribute="ch_eid">
<input type="hidden" name="ch_eid" id="ch_eid" value="0"/>	
</form:form>


<script>

$(document).ready(function () {
	
	 var msg = '${msg}';
		if (msg != ""){
		alert(msg);
		}
	
	 try{
	 	   if(window.location.href.includes("msg="))
	 		{
	 			var url = window.location.href.split("?")[0];
	 			window.location.href=url;
	 		} 	
	 	}
	 	catch (e) {
	 	} 
	
	 datepicketDate('exp_emp_fromdate');
	 datepicketDate('exp_emp_todate');
//	inyear();

	mockjax1('search_experience_details_table');
		table = dataTable('search_experience_details_table');
		$('#srch').on('click', function(){	
			
	    	table.ajax.reload();
	    	
	    });
		
		get_p_id_pers_info();
	});
	
	
function get_p_id_pers_info() {
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	var userid =  "${userid}";
	$.post('get_p_id_pers_info_ctrl?' + key + "=" + value,{userid : userid},function(j) {
		//alert(j)
		$("#p_id").val(j[0][0]);
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
}

	
/////////personal_details/////Start

function getPerDetails() {
	
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	var userid =  "${userid}";
	$.post('getPersonaldetails?' + key + "=" + value,{userid : userid},function(j) {
			$("#p_id").val(j[0][10]);
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	
	}
/////////////End	

function onchangeCount(val){
	
	if(document.getElementById("exp_emp_fromdate").value!="" && document.getElementById("exp_emp_todate").value!=""){
	var joining=document.getElementById("exp_emp_fromdate").value.split('/').reverse().join('-');  
	var dish=document.getElementById("exp_emp_todate").value.split('/').reverse().join('-'); 
	var joining2=new Date(joining);
	var dishcharge=new Date(dish);
	
//  a = calcDate(dishcharge,joining2);
 var a=dateDiffInDays_Months_Years(joining2,dishcharge)
 $("#exp_total_year").val(a[0]);
 $("#exp_total_month").val(a[1]);
 $("#exp_total_day").val(a[2]);
	}
}

function dateDiffInDays_Months_Years(start, end) {
	
    var m1 = new Date(start);
    var m2 = new Date(end);
    var yDiff = m2.getFullYear() - m1.getFullYear();
    var mDiff = m2.getMonth() - m1.getMonth();
    var dDiff = m2.getDate() - m1.getDate();

    if (dDiff < 0) {
        var daysInLastFullMonth = getDaysInLastFullMonth(start);
        if (daysInLastFullMonth < m1.getDate()) {
            dDiff = daysInLastFullMonth + dDiff + (m1.getDate() - daysInLastFullMonth);
        } else {
            dDiff = daysInLastFullMonth + dDiff;
        }
        mDiff--;
    }
    if (mDiff < 0) {
        mDiff = 12 + mDiff;
        yDiff--;
    }
     console.log('Y:', yDiff, ', M:', mDiff, ', D:', dDiff);
     var arr = [];
     arr.push(yDiff)
     arr.push(mDiff)
     arr.push(dDiff)
//     var message = dDiff+"/"+mDiff+"/"+yDiff
    return arr
}

function getDaysInLastFullMonth(day) {
    var d = new Date(day);
    console.log(d.getDay() );

    var lastDayOfMonth = new Date(d.getFullYear(), d.getMonth() + 1, 0);
    console.log('last day of month:', lastDayOfMonth.getDate() );

    return lastDayOfMonth.getDate();
}

function clear_field(){
	
	$("#exp_emp_name").val('').change();
	$("#exp_emp_address").val('').change();
	$("#exp_emp_work").val('').change();
	document.getElementById("document_lbltik").innerHTML=""; 
	$("#exp_emp_regno_gst_pin").val("");
	$("#exp_emp_fromdate").val("");
	$("#exp_emp_todate").val("");
	$("#exp_total_year").val("0");
	$("#exp_total_month").val("0");
	$("#exp_total_day").val("0");
	$("#exp_emp_salary").val("0");
	$("#exp_emp_perm_temp").val("");
	$("#exp_emp_document").val("");
	$("#exp_id").val("");
	$("#document_hidd").val("");
	
	document.getElementById("document_lbltik").innerHTML=""; 
	document.getElementById('save_btn').value="Save";
	document.getElementById('save_btn').classList.toggle('btn-light', false);
	document.getElementsByClassName('fa fa-pencil-square-o')[0].style.visibility = 'hidden';
	document.getElementsByClassName('fa fa-save')[0].style.visibility = 'visible';
	
}

function getDownloadPdfExperience(id){   
	
		$("#document_id1").val(id); 
		document.getElementById("getDownloadPdfForm").submit();
	} 

function setValueOfNotificationPath() {
		
		 if ($("#exp_emp_document").val() != "" || $("#exp_emp_document").val() != null) {
				var document_hidd=$("#exp_emp_document").val();
				document.getElementById("document_hidd").value=document_hidd;
			}
			else{
				document.getElementById("document_hidd").value="";
				
			}
	}

function deleteData(id){
	
			$("#id1").val(id);
			document.getElementById('deleteForm').submit();
	}
	
function Experi_next(){
	$("#doc_eid").val('${tp_eid}');
	document.getElementById('applicationUrlForm5').submit();
}

function getPreviousPage()
{  
	$("#pers_edu_hid").val("${pers_adv_details_session}");
//	$("#pers_post_trade_hid").val("${pers_post_trade_session}");
	document.getElementById("applicationUrlForm1").submit();
}
			
function editData(id,exp_emp_name,exp_emp_fromdate,exp_emp_todate,exp_total_year,exp_total_month,exp_total_day,exp_emp_document){
	
			document.getElementById('lbladd').innerHTML = "Update ";
			
	 		$("#id").val(id);
			$("input#exp_emp_name").val(exp_emp_name);
			//var date_temp = exp_emp_fromdate.substring(8,10)+"-"+exp_emp_fromdate.substring(5,7)+"-"+exp_emp_fromdate.substring(0,4);
			$("input#exp_emp_fromdate").val(exp_emp_fromdate.substring(0,10));
			$("input#exp_emp_todate").val(exp_emp_todate.substring(0,10));
			$("#exp_total_year").val(exp_total_year);
			$("#exp_total_month").val(exp_total_month);
			$("#exp_total_day").val(exp_total_day);
			$("input#document_hidd").val(exp_emp_document);
			document.getElementById('id_org').value=id;
	}
	
	function data(search_experience_details_table) {
		jsondata = [];
		var table = $('#' + search_experience_details_table).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];

		var exp_emp_name = $("#exp_emp_name").val();
		var exp_emp_fromdate = $("#exp_emp_fromdate").val();
		var exp_emp_todate = $("#exp_emp_todate").val();
		var exp_emp_document = $("#exp_emp_document").val();
		
		$.post("getFilterExperience_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			exp_emp_name:exp_emp_name,
			exp_emp_fromdate:exp_emp_fromdate,
			exp_emp_todate:exp_emp_todate,
			exp_emp_document:exp_emp_document
			
		}, function(j) {
			
		for (var i = 0; i < j.length; i++) {
		jsondata.push([j[i][0],j[i][1],j[i][2],j[i][3],j[i][4],j[i][5],j[i][6],j[i][7],j[i][8]]);
		
		}
	});

		$.post("getTotalExperience_dataCount?" + key + "=" + value, {Search:Search}, function(j) {
			FilteredRecords = j;
		});
	}
	
	 function nextUrl(){
			
		 var key = "${_csrf.parameterName}";
			var value = "${_csrf.token}";	
			var p_id=$("#pers_edu_id").val();

		 	var form_data = new FormData(document.getElementById("Exp_detAction"));
		  $.ajax({
		         type: "POST",
		         enctype: 'multipart/form-data',
		         url: 'eduValidation?_csrf='+value,
		         data: form_data,
		         processData: false,
		         contentType: false,
		         cache: false,
		         async : false,
		         success: function (data) {
		        	 
		        	  if(data!="Saved Successfully"){
			            	 alert(data)
			            	
			             } else{
			            	 document.getElementById('mainForm12').submit();
			             }
			         }
			     }); 
	 }
	 
function isValidateClientSide(){	
	
   	if($("#exp_emp_name").val().trim() == ""){
      alert("Please Enter Name of Employer");
      $("#exp_emp_name").focus();
		 return false; 
	}
   	
	var maxLength = 50;
	 var charLength = $("input#exp_emp_name").val().length;

       if(charLength >= maxLength){
   
       	alert("Please Enter Name of Employer should be less then 50 Characters");
			$("input#exp_emp_name").focus();
			return false;
       }  
       
       if($("input#exp_emp_fromdate").val() == "" || $("#exp_emp_fromdate").val() == "DD/MM/YYYY"){
	    	 alert("Please Select From Date for Period of Employment");
	    	   $("input#exp_emp_fromdate").focus();
	       	return false; 
	     }
	     if($("input#exp_emp_todate").val() == "" || $("#exp_emp_todate").val() == "DD/MM/YYYY"){
	    	 alert("Please Select To Date for Period of Employment");
	    	 $("input#exp_emp_todate").focus();
	       	return false; 
	     }
	     
	     if($("input#exp_emp_document").val() == "" || $("input#exp_emp_document").val() == undefined){
	    	 if($("input#document_hidd").val() == ""){
	    	 alert("Please Upload Document");
	    	 $("input#exp_emp_document").focus();
	         return false; 
	     }
	     }
	     
////////////////////for date compare	
			
		if($("input#exp_emp_fromdate").val() != "" && $("input#exp_emp_todate").val() != ""){
			var exp_emp_fromdate = $("input#exp_emp_fromdate").val();
			var exp_emp_todate = $("input#exp_emp_todate").val();
			$.post("getDate_data?" + key + "=" + value, {exp_emp_fromdate:exp_emp_fromdate,
				exp_emp_todate:exp_emp_todate}, function(j) {
					
					if(j > 0){
				    	 $("input#count").val(j);
					}
			});
			
			if($("input#count").val() > 0){
				 alert("Please Select valid date");
		    	 $("input#exp_emp_fromdate").focus();
		         return false; 
			}else{
				return true; 
			}
		}
}

function getPreviousPage()
{   
	$("#pers_details_hid").val("${pers_adv_details_session}");
	document.getElementById("applicationUrlForm").submit();
}
function getEduPage(){  
	debugger;
	$("#eid").val($("#p_id").val());
	$("#pers_exper_hid").val("${pers_adv_details_session}");
	document.getElementById("mainForm11").submit();
}

function getDocPage(){
	$("#doc_eid").val($("#p_id").val());
	$("#pers_exper_hid").val("${pers_adv_details_session}");
	document.getElementById("applicationUrlForm5").submit();	
}

function getchoicePage(){
	$("#ch_eid").val($("#p_id").val());
	$("#pers_exper_hid").val("${pers_adv_details_session}");
	document.getElementById("applicationUrlForm6").submit();	
}
</script>