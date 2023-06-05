<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="js/InfiniteScroll_old/css/site.css">
<script type="text/javascript" src="js/common/commonmethod.js"></script>
<link rel="stylesheet" href="js/autoComplate/autoComplate.css">

<link href="js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="js/dropDown/select2.min.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/sweetalert/sweetalert.min.js"></script>
<link href="js/jquery/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/jquery/jquery-ui.js" type="text/javascript"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->


<section class="dashboard-page">
<div class="container-fluid">
	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>Credit</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">Credit</li>
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
                <form:form name="credit" id="credit" action="creditAction" method='POST' commandName="creditCMD" >
				<div class="card-style mb-30">
					<h6 class="mb-25">Credit</h6>
						<div class="row">
						

												<div class="tables-wrapper">
													<div class="row">
														<div class="col-lg-12">


															<div class="table-wrapper table-responsive custom-table simple-table ">
																<table class="table" id="att_Tb">
																	<thead>
																		<tr>
																			<th><h6>Ser No</h6></th>
																			<th><h6>Course Number</h6></th>
																			<th><h6>Total Duration</h6></th>																		
																			<th><h6>No Of Days</h6></th>	
																			<th><h6>Points</h6></th>																		
																			<th><h6>Action</h6></th>
																			
																		
																		</tr>
																		<!-- end table row-->
																	</thead>
																	 <tbody id="att_Tbbody" >
								                                     <tr id="tr_id_att">
																			<td class="min-width">
																				<div class="lead">

																					<div class="lead-text">
																						<p>1</p>
																					</div>
																				</div>
																			</td>
																			
																			<td class="min-width">
																			
																			<div class="input-style-2">
																					<input id="course1" name="course1" class="form-control"
								maxlength="100"  autocomplete="off" onclick="autox(1);" placeholder="Course Number">
								<input type="hidden" id="course1hid" name="course1hid" class="form-control" autocomplete="off" placeholder="Course Number"></input>
																				</div>
	
																			</td>
																			<td class="min-width">
																			<div class="input-style-2">
																					<input type="text" id="max_duration1" name="max_duration1"  maxlength="50" 
									class="form-control" autocomplete="off" readonly  placeholder="Total Duration">
																				</div>
																			</td>
																		
																			

																			<td class="min-width">
																				<div class="input-style-2">
																					<input type="text" id="no_of_days1" name="no_of_days1"  maxlength="50"
									class="form-control"  autocomplete="off"  placeholder="No Of Days"> 
																				</div> 																
																			</td>
																			
																			<td class="min-width">
																				<div class="input-style-2">
																				<input type="text" id="point1" name="point1"  maxlength="50" 
									class="form-control"  autocomplete="off" placeholder="Points"> 
																					
																				</div> 																
																			</td>



																			<td class="min-width addminusbut">


																				<ul class="buttons-group mainbtn action">


																					<li><a
																						class="main-btn info-btn btn-hover btn-sm addminusbut"
																						value="ADD" title="ADD" id="id_add_att1" onclick="formopen_att(1);"><i
																							class="lni lni-plus"></i></a></li>

																				</ul>



																			</td>
																		</tr>
																		<!-- end table row -->

																	</tbody>
																</table>
																<!-- end table -->
															</div>

															<!-- end card -->
														</div>
														<!-- end col -->
													</div>
													<!-- end row -->





											</div>

			<%-- <div class="card-style mb-30">

                  <div class="table no-margin table-striped  table-hover  table-bordered table-bordered"
							id="att_Tb">
                    <table class="table">
                     <thead >
								<tr style="font-size: 15px;">
									<th style="text-align: center;">Ser No</th>
<!-- 									<th style="text-align: center; width: 15%;">SYSTEM</th> -->
<!-- 									<th style="text-align: center;  width: 15%;">COURSE</th> -->
									<th style="text-align: center;">Course Number</th>
									<th style="text-align: center;">Total Duration</th>
									<th style="text-align: center;">No Of Days</th>
									<th style="text-align: center;">Points</th>
									<th style="text-align: center;">Action</th>

								</tr>
							</thead>
                     <tbody id="att_Tbbody" >
								<tr id="tr_id_att">
								<td align="center">1</td>
								
<!-- 								<td align="center"> -->
<!-- 									<select name="system_id1" id="system_id1" class="form-control" onchange="GetSystemFromCourse(1);"> -->
<!-- 											<option value="0">--Select--</option> -->
											<c:forEach var="item" items="${getSystemList}" varStatus="num">
												<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
											</c:forEach>
<!-- 									</select> -->
<!-- 								</td> -->
<!-- 								<td align="center"> -->
<!-- 									<select name="course_id1" id="course_id1" class="form-control" onchange="SystemCourse_fetch(1);" > -->
<!-- 											<option value="0">--Select--</option> -->
<!-- 									</select> -->
<!-- 								</td> -->
								
								<td align="center">
									<input id="course1" name="course1" class="form-control"
								maxlength="100"  autocomplete="off"
								cssStyle="text-transform: uppercase;" onclick="autox(1);" ></input>

							
							
							<input type="hidden" id="course1hid" name="course1hid" class="form-control" autocomplete="off"  cssStyle="text-transform: uppercase;" ></input> 

								</td>
								
								<td align="center">
									<input type="text" id="max_duration1" name="max_duration1"  maxlength="50" 
									class="form-control" autocomplete="off" readonly  > 
								</td>
								
								<td align="center">
									<input type="text" id="no_of_days1" name="no_of_days1"  maxlength="50"
									class="form-control"  autocomplete="off"  > 
								</td>
								
								<td align="center">
									<input type="text" id="point1" name="point1"  maxlength="50" 
									class="form-control"  autocomplete="off" > 
								</td>
								
								<td align="center">
									<a class="btn btn-success btn-sm" value="ADD" title="ADD" id="id_add_att1" onclick="formopen_att(1);">
										<i class="lni lni-plus"></i>
									</a> 
								</td>
								</tr>
							</tbody>
                    </table>
                    <!-- end table -->
                  </div>
                </div> --%>
			</div>					
					
						<ul class="buttons-group mainbtn">

							<li>
							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon btn-search" type="button" value="Search"><i class="lni lni-search-alt"></i>Search</a>
							   
							</li>
							<li>
								<input id="btn-save" class="main-btn info-btn btn-hover" type="submit" value="Save"/>
							</li>
							<li>
								<a href="CreditUrl" class="main-btn dark-btn n btn-hover" type="button">Reset</a>
								
							</li>
						</ul>
						<div class="input-style-2 m-0">
							<input type="hidden" id="count_hidden_att" name="count_hidden_att" value="1">
						</div>
						
				
				
				</div>
				<!-- end card -->
            </form:form>
			</div>
		</div>
	</div>


<div class="row">
	<div class="col-12">
		<div class="card-style mb-30">
			<div class="table-wrapper table-responsive custom-datatable-p">
				<table class="table" id="search_Credit">
					<thead>
							<tr> 
								<th><h6>Ser No</h6></th>
								<th id="1"><h6>Course Number</h6></th>
								<th id="2"><h6>Total Duration</h6></th>
								<th id="3"><h6>No Of Days</h6></th>
								<th id="4"><h6>Points</h6></th>
							</tr>
													
					</thead>
					<tbody class="custom-datatablepra">
					</tbody>
				</table>
								
			</div>
		</div>
				
	</div>
		
</div> 

</div>
</section>
	
	
	<!--	old   -->	
				
<%-- <form:form name="credit" id="credit" action="creditAction" method='POST' commandName="creditCMD" >
	<div class="container" align="center">
		<div class="card">
			<div class="card-header"><h5><span id="lbladd"></span>CREDIT</h5></div>
				<div class="card-body card-block">
					<div class="col-12">
						<div class="container-fluid" id="personal_information" align="left">

					<table class="table table-hover  table-striped table-borderless "
							id="att_Tb" style="width: 100% !imporatant;">
							<thead style="background: #FFFFFF; color: #000000;">
								<tr style="font-size: 15px;">
									<th style="text-align: center; width: 5%;">Ser No</th>
<!-- 									<th style="text-align: center; width: 15%;">SYSTEM</th> -->
<!-- 									<th style="text-align: center;  width: 15%;">COURSE</th> -->
									<th style="text-align: center; width: 20%;">COURSE NUMBER</th>
									<th style="text-align: center; width: 20%;">TOTAL DURATION</th>
									<th style="text-align: center; width: 20%;">NO OF DAYS</th>
									<th style="text-align: center; width: 20%;">POINTS</th>
									<th style="text-align: center; width: 15%;">Action</th>
								</tr>
							</thead>
							<tbody id="att_Tbbody" >
								<tr id="tr_id_att">
								<td align="center">1</td>
								
<!-- 								<td align="center"> -->
<!-- 									<select name="system_id1" id="system_id1" class="form-control" onchange="GetSystemFromCourse(1);"> -->
<!-- 											<option value="0">--Select--</option> -->
											<c:forEach var="item" items="${getSystemList}" varStatus="num">
												<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
											</c:forEach>
<!-- 									</select> -->
<!-- 								</td> -->
<!-- 								<td align="center"> -->
<!-- 									<select name="course_id1" id="course_id1" class="form-control" onchange="SystemCourse_fetch(1);" > -->
<!-- 											<option value="0">--Select--</option> -->
<!-- 									</select> -->
<!-- 								</td> -->
								
								<td align="center">
									<input id="course1" name="course1" class="form-control"
								maxlength="100"  autocomplete="off"
								cssStyle="text-transform: uppercase;" onclick="autox(1);" ></input>

							
							
							<input type="hidden" id="course1hid" name="course1hid" class="form-control" autocomplete="off"  cssStyle="text-transform: uppercase;" ></input> 

								</td>
								
								<td align="center">
									<input type="text" id="max_duration1" name="max_duration1"  maxlength="50" 
									class="form-control" autocomplete="off" readonly  > 
								</td>
								
								<td align="center">
									<input type="text" id="no_of_days1" name="no_of_days1"  maxlength="50"
									class="form-control"  autocomplete="off"  > 
								</td>
								
								<td align="center">
									<input type="text" id="point1" name="point1"  maxlength="50" 
									class="form-control"  autocomplete="off" > 
								</td>
								
								<td align="center">
									<a class="btn btn-success btn-sm" value="ADD" title="ADD" id="id_add_att1" onclick="formopen_att(1);">
										<i class="fa fa-plus"></i>
									</a> 
								</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			
			<div class="card-footer" align="center">
				<a href="CreditUrl" class="btn-clear">Reset</a>
				<input type="submit" class="btn-save" value="Save" > 
				<i class="action_icons searchButton"></i><input type="button" class="btn-search" id="btn-reload" value="Search">
			</div>
			<input type="hidden" id="count_hidden_att" name="count_hidden_att" class="form-control autocomplete" value="1">
		</div>
	</div>
</form:form>

<div class="container">
	<table id="search_Credit"
		class="table no-margin table-striped  table-hover  table-bordered">
		<thead>
			<tr align="center"> 
				<th >SER NO</th>
				<th id="1">COURSE NUMBER</th>
				<th id="2">TOTAL DURATION</th>
				<th id="3">NO OF DAYS</th>
				<th id="4">POINTS</th>
			</tr>
		</thead>
		<tbody >
		</tbody >
	</table>
</div> --%>

<script>

$(document).ready(function() {

	mockjax1('search_Credit');
	table = dataTable('search_Credit');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
});




//Add-More-Add
var att=1;
function formopen_att(R){
	$("#att_Tb").show();
	
		 $("#id_add_att"+R).hide();
		 $("#att_id_remove"+R).hide();
		 
		 att=0;
		 att= parseInt(R)+1;
		 
		 if(att < 51){
				 
				 $("input#count_hidden_att").val(att);//current serial No
				 $("table#att_Tb").append('<tr align="center" id="tr_id_att'+att+'"><td>'+att+'</td>'
						 
// 						 +'<td align="center"><select id="system_id'+att+'" name="system_id'+att+'" class="form-control" onchange="GetSystemFromCourse('+att+');"><option value="0">--Select--</option>'
// 							+'<c:forEach var="item" items="${getSystemList}" varStatus="num"><option value="${item.id}" name="${item.system_name}">${item.system_name}</option></c:forEach>' 
// 							+'</select> </td>'
					    
// 						 +'<td align="center"><select id="course_id'+att+'" name="course_id'+att+'" class="form-control" onchange="SystemCourse_fetch('+att+');"><option value="0">--Select--</option>'
// 							+'</select> </td>'	
						 
						 +'<td class="min-width"><div class="input-style-2"><input type="text" id="course'+att+'" name="course'+att+'"  maxlength="50" class="form-control" autocomplete="off" placeholder="Course Number"  onclick="autox('+att+');"></div></td>'
						 +'<td class="min-width"><div class="input-style-2"><input type="text" id="max_duration'+att+'" name="max_duration'+att+'"  maxlength="50" class="form-control"  autocomplete="off" readonly placeholder="Total Duration" ></div></td>'
						 +'<td class="min-width"><div class="input-style-2"><input type="text" id="no_of_days'+att+'" name="no_of_days'+att+'"  maxlength="50" class="form-control"  autocomplete="off" placeholder="No Of Days" ></div></td>'
						 +'<td class="min-width"><div class="input-style-2"><input type="text" id="point'+att+'" name="point'+att+'"  maxlength="50" class="form-control"  autocomplete="off" placeholder="Points"></div></td>'

						 +'<td class="min-width"><div class="action"><ul class="buttons-group mainbtn"><li><a class="main-btn info-btn btn-hover btn-sm addminusbut" value = "ADD" title = "ADD" id = "id_add_att'+att+'" onclick="formopen_att('+att+');" ><i class="lni lni-plus"></i></a></li><li> <a class="main-btn danger-btn btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_remove'+att+'" onclick="formopen_re_att('+att+');"><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
			   		     +'</tr>');
		 
			}else{
					alert("Please Enter max 50 Quantity");
					 if ( att == 51){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
		 var curcnt = $("#count_hidden_att").val();
		 $("#new_count_hidden").val(curcnt);
	
}
//Add-More-Remove
function formopen_re_att(R){
	var del_index = $("#idofprocedure"+R).val();
	if(String(del_index) == "undefined"){
		del_index="0";
	}else{
		del_index = del_index;
	}
	 var del_field_val = $("#del_id_hidden").val();
	 
	 if(del_field_val == "0,undefined"){
		 $("#del_id_hidden").val(del_index);
	 }else{
		 $("#del_id_hidden").val(del_field_val+","+del_index);
	 }
	
	 $("tr#tr_id_att"+R).remove();
	 att = att-1;
	 $("input#count_hidden_att").val(att);
	 if(R > 2){
		 $("#id_add_att"+att).show();
		 $("#att_id_remove"+att).show();
	 }
	 if(R == 2){
		 $("#id_add_att"+att).show();
	 }
	 var ncc = $("#new_count_hidden").val();
	 ncc = ncc-1;
	 $("#new_count_hidden").val(ncc);
}
</script>

 <script>
		
function autox(ser){ 
// 		alert(ser);
jQuery("#course"+ser).keypress(function(){
		
		var job_no = this.value;
			 var jobNoAuto=jQuery("#course"+ser);
			 jobNoAuto.autocomplete({
			      source: function( request, response ) {
			        jQuery.ajax({
			        type: 'POST',
			        url: "getCourse_Autocomplete?"+key+"="+value,
			        data: {getcolumnname : job_no },
			          success: function( data ) {
			        	  var jobval = [];
			        	  var length = data.length-1;
			        	  if(data.length-1 != 0){
				        		var enc = data[length].substring(0,16);
				        	}
				        	for(var i = 0;i<data.length;i++){
				        		jobval.push(dec(enc,data[i]));
				        	}
				        	var dataCountry1 = jobval.join("|");
			            var myResponse = []; 
			            var autoTextVal=jobNoAuto.val();
						jQuery.each(dataCountry1.toString().split("|"), function(i,e){
							var newE = e.substring(0, autoTextVal.length);
							if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
							  myResponse.push(e);
							}
						});      	          
						response( myResponse ); 
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Valid Course Code");
			        	  document.getElementById("course"+ser).value="";
			        	  jobNoAuto.val("");	        	  
			        	  jobNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	  var course = ui.item.value;

			    	  $.post("getTotalDuration?" + key + "=" + value, {course:course}, function(j) {
							if (j.length != 0) {
								$("#max_duration"+ser).val(j[0][0]);
							}
						});
			      } 
			});	
	});
}
	
function Validate() {
	
	var count = $("#count_hidden_att").val();
	
	for(var i=1;i<=count;i++){
		
		if ($('#system_id'+i).val() == "0")   {
			alert("Please Select System Name.");
			$("#system_id"+i).focus();
			return false;
		}
		if ($('#course_id'+i).val() == "0")   {
			alert("Please Select Course Name.");
			$("#course_id"+i).focus();
			return false;
		}
		if ($('#course'+i).val() == "")   {
			alert("Please Enter Course Code.");
			$("#course"+i).focus();
			return false;
		}
		if ($('#no_of_days'+i).val() == "")   {
			alert("Please Enter No of Days.");
			$("#no_of_days"+i).focus();
			return false;
		}
		if ($('#point'+i).val() == "")   {
			alert("Please Enter Point.");
			$("#point"+i).focus();
			return false;
		}
	}
	
	return true;
}

function GetSystemFromCourse(obj) {
	
		var system_id = $("select#system_id"+obj).val();
		
      $.post("getSystemlistFromCourse?" + key + "=" + value,{system_id : system_id},
					function(j) {
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1]+ '</option>';
						}
						$("select#course_id"+obj).html(options);
					});
}


function SystemCourse_fetch(ser) {

	var system_id1 = $('#system_id'+ser).val();
	var course_id1 = $('#course_id'+ser).val();
	
	$.post("getSystemCourse_fetch?"+key+"="+value,{system_id1:system_id1,course_id1:course_id1},function(j) {
		$("#course"+ser).val(j[0][0]);
		$("#max_duration"+ser).val(j[0][1]);
	});
}


function data(search_Credit) {
	//debugger;
	jsondata = [];
	var table = $('#' + search_Credit).DataTable();
	var info = table.page.info();
//		var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
	var orderType = order[0][1];
	
	var course = $("#course1").val();
	var max_duration = $("#max_duration1").val();
	var no_of_days = $("#no_of_days1").val();
	var point = $("#point1").val();
	

	$.post("getFilterCredit_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		course:course,
		max_duration:max_duration,
		no_of_days:no_of_days,
		point:point
		
	}, function(j) {
		
		
		for (var i = 0; i < j.length; i++) {
// 			jsondata.push([ j[i].ser,j[i].course,j[i].max_duration,j[i].no_of_days, j[i].point]);
			jsondata.push([ j[i].ser,j[i].course,j[i].max_duration,j[i].no_of_days, j[i].point]);
		}
	});
	$.post("getTotalCredit_dataCount?" + key + "=" + value, {
		course:course,
		max_duration:max_duration,
		no_of_days:no_of_days,
		point:point

	}, function(j) {
		FilteredRecords = j;
		});
}

</script> 

