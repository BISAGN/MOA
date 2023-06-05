<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- <script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<script src="js/amin_module/rbac/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/watermark/watermark.css">
<script src="js/watermark/watermark_onclick.js" type="text/javascript"></script>
<script src="js/watermark/printallpages.js" type="text/javascript"></script>
 <!-- INTERNAL REMOVE START (NEED TO ADD IN PARTICULAR JSP)-->
			<link rel="stylesheet" href="assets/vendor/internal_css.css">
<!-- INTERNAL REMOVE END--> 
<script nonce="${cspNonce}" type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
// 	$(document).ready(function () {
		
// 		$.ajaxSetup({
// 			async: false
// 		});
		

	</script>
	
<section class="dashboard-page">
<div class="container-fluid">

	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>Screen Master</h2>
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
							<li class="breadcrumb-item active" aria-current="page">Screen Master</li>
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
                <form:form name="screen_mst" id="screen_mst" action="screen_mstAction" method='POST' modelAttribute="screen_mstCMD">
					<div class="card-style mb-30">
					<h6 class="mb-25">Screen Master</h6>
						<div class="row">

							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Screen Name<span class="mandatory">*</span></label>
									<input id="screen_name" name="screen_name" class="autocomplete UpperClassName txt-transupp"
										autocomplete="off" maxlength="80" placeholder="Enter Screen Name" />
								</div>
								<!-- end input -->
							</div>
		
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Screen URL<span class="mandatory">*</span></label>
									<input id="screen_url" name="screen_url" class="autocomplete UpperClassName txt-transupp"
										autocomplete="off" maxlength="125" placeholder="Enter Screen Url" />
								</div>
								<!-- end input -->
							</div>

							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label>Module<span class="mandatory">*</span></label>
									<div class="select-position">
										<select  name="module.id" id="screen_module_id" class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getModuleNameList}" varStatus="num">
													<option value="${item.id}">${item.module_name}</option>
												</c:forEach>
										</select>
									</div>
								</div>
							</div>	

							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label>Sub-Module<span class="mandatory">*</span></label>
									<div class="select-position">
										<select  name="sub_module.id" id="screen_submodule_id" class="singleselect form-control form-control-lg">
											<option value="0">--Select--</option>
										</select>
									</div>
								</div>
							</div>		

							<div>
								<input type="hidden" name="screen_id" id="screen_id" />
							</div>
						</div>		

						<ul class="buttons-group mainbtn">

<!-- 							<li> -->
<!-- 							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button" value="Search" ><i class="lni lni-search-alt"></i>Search</a> -->
<!-- 							</li>  -->
							<li  id="save_btn">
								<input class="main-btn info-btn btn-hover" type="submit" value="Save" >
							</li> 
							<li id="update_btn"  >
								<input type="submit" class="main-btn deactive-btn btn-hover" value="Update" >
							</li> 
							<li><a  href="screen_mstUrl"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
						</ul>
				</div>

				</form:form>
			</div>
		</div>
	</div>

<div class="row">
	<div class="col-12">
		<div class="card-style mb-30">
			<div id="divSerachInput">
				<input id="searchInput" type="text"
						placeholder="&#xF002; Search Word" size="35" class="form-control custom_screen_mst_searchip">
			</div>
			
			<div class="table-wrapper table-responsive custom-datatable-p simple-table" id="att_Tb">
				<table class="table" id="ScreenReport">
					<thead>
						<tr>
							<th><h6>Ser No</h6></th>
							<th><h6>Screen Name</h6></th>
							<th><h6>Module Name</h6></th>
							<th><h6>Sub Module Name</h6></th>
							<th class='lastCol'><h6>Action</h6></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}" varStatus="num">
							<tr>
								<td>${num.index+1}</td>
								<td>${item.screen_name}</td>
								<td>${item.module_name}</td>
								<td>${item.submodule_name}</td>
								<td>${item.id}</td>
							</tr>
						</c:forEach>
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
<c:url value="screen_report" var="screen_reportUrl" />
	<form:form action="${screen_reportUrl}" method="post" id="searchForm"
		name="searchForm">
		<input type="hidden" name="module_id1" id="module_id1" value="0" />
		<input type="hidden" name="sub_module_id1" id="sub_module_id1" value="0" />
	</form:form>

	<c:url value="screen_mstAction" var="screen_mstAction" />
<form:form action="${screen_mstAction}" method="post" id="updateForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>
	
<%-- <form:form name="screen_mst" id="screen_mst" action="screen_mstAction" method='POST' modelAttribute="screen_mstCMD">
<div class="container">
	<div class="card">
				<div class="card-header">
					<h5>Screen Master</h5>
				</div> <!-- end of card-header -->
				<div class="card-body card-block">
					<div class="row mb-3">
							<div class="col-md-2">
								<label for="text-input">Screen Name <strong
									style="color: red;">*</strong></label>
							</div>
							<div class="col-md-4">
								<input id="screen_name" name="screen_name"
									style="font-family: 'FontAwesome', Arial; text-transform: uppercase"
									class="form-control" autocomplete="off" maxlength="80">
							</div>
							<div class="col-md-2">
								<label for="text-input">Screen URL <strong
									style="color: red;">*</strong></label>
							</div>
							<div class="col-md-4">
								<input id="screen_url" name="screen_url"
									style="font-family: 'FontAwesome', Arial;"
									class="form-control" autocomplete="off" maxlength="125">
							</div>
					</div>
					<div class="row mb-3">
							<div class="col-md-2">
								<label class=" form-control-label">Module <strong
									style="color: red;">*</strong></label>
							</div>
							<div class="col-md-4">
								<select name="module.id" class="form-control"
									id="screen_module_id">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getModuleNameList}"
										varStatus="num">
										<option value="${item.id}">${item.module_name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-2">
								<label class=" form-control-label">Sub-Module <strong
									style="color: red;">*</strong></label>
							</div>
							<div class="col-md-4">
								<!-- <select name="" class="form-control" id ="modulelist"  onchange="myFunction(event)">  </select> -->
								<select name="sub_module.id" class="form-control"
									id="screen_submodule_id">
									<option value="0">--Select--</option>
								</select>
							</div>
					</div>
					<input type="hidden" name="screen_id" id="screen_id" />
				</div> <!-- end of card-body -->
				<div class="card-footer">
					<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall();"> 
					<input type="submit" id="save_btn" class="btn btn-primary btn-sm" value="Save" onclick="return isValid();"> 
					<input type="submit" id="update_btn" class="btn btn-secondary btn-sm" value="Update" onclick="return isValid();" style="display: none;">
					<i class="fa fa-search"></i><input type="button" class="btn btn-info btn-sm" onclick="return reportScreen();" value="Search">
				</div><!-- end of card-footer -->
			</div> <!-- end of card -->
		</div> <!-- end of container -->

	</form:form>


	<div class="container">
	<div id="divSerachInput">
			<input id="searchInput" type="text" style="font-family: 'FontAwesome', Arial; margin-bottom: 5px;width: 50%;" placeholder="&#xF002; Search Word" size="35" class="form-control">
	</div>
	<div id="divPrint">
			<div id="divShow"></div>
			<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
				<table id="ScreenReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead>
						<tr style="font-size: 15px;">
							<th style="text-align: center; width: 10%;">Ser No</th>
							<th style="width: 40%;">Screen Name</th>
							<th style="width: 15%;">Module Name</th>
							<th style="width: 15%;">Sub Module Name</th>
							<th class='lastCol' style="text-align: center; width: 10%;">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}" varStatus="num">
							<tr style="font-size: 13px;">
								<td style="text-align: center; width: 10%;">${num.index+1}</td>
								<td style="width: 40%;">${item.screen_name}</td>
								<td style="width: 15%;">${item.module_name}</td>
								<td style="width: 15%;">${item.submodule_name}</td>
								<td style="text-align: center; width: 10%;">${item.id}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	</div>
	</div>
	<c:url value="screen_report" var="screen_reportUrl" />
	<form:form action="${screen_reportUrl}" method="post" id="searchForm"
		name="searchForm">
		<input type="hidden" name="module_id1" id="module_id1" value="0" />
		<input type="hidden" name="sub_module_id1" id="sub_module_id1" value="0" />
	</form:form> --%>

<script nonce="${cspNonce}" type="text/javascript">
   	function isValid()
   	{	
		if($("input#screen_name").val()==""){
			alert("Please Enter Screen Name");
			$("input#screen_name").focus();
			return false;
		}
   		if($("input#screen_url").val()==""){
   			alert("Please Select Screen URL");
   			$("input#screen_url").focus();
   			return false;
   		} 
   		if($("select#screen_module_id").val() == 0){
   			alert("Please Select Module");
   			$("select#screen_module_id").focus();
   			return false;
   		} 
   		if($("select#screen_submodule_id").val() == 0){
   			alert("Please Select Sub Module");
   			$("select#screen_submodule_id").focus();
   			return false;
   		} 
	   	return true;
	}
   	
   	
function setTimeLoadForTable(){
		
		document.querySelectorAll('.updateOnclick').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('updateID'+val).value;
				var hsc_name = document.getElementById('apsmoAGE'+val).value;
				var hsc_url = document.getElementById('apsmosAGE1'+val).value;
				var hmid = document.getElementById('apsnaAGE'+val).value;
				var hsmid = document.getElementById('apdocAGE'+val).value;
				
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					Update(hid,hsc_name,hsc_url,hmid,hsmid);
				} else {

				}
			})
		});
// 		setTimeout(setTimeLoadForTable, 1000);
}
	document.addEventListener('DOMContentLoaded', function() {

		
// 		document.getElementById('btn-reload').onclick = function() {
// 			return  reportScreen();
// 		}; 
		document.getElementById('save_btn').onclick = function() {
			return  isValid();
		}; 
		document.getElementById('update_btn').onclick = function() {
			return  isValid();
		}; 
// 		document.getElementById('reset_btn').onclick = function() {
// 			return  clearall();
// 		}; 
		
	});
	
	function updateData(id) {
		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}
   	</script>
<script nonce="${cspNonce}" type="text/javascript">
   	$(document).ready(function () {
   		$("#searchInput").on("keyup", function() {
  			var value = $(this).val().toLowerCase();
  			$("#ScreenReport tbody tr").filter(function() { 
  			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
  			});
	  	});
		
		
	
   		setTimeLoadForTable();
	    $('select#screen_module_id').change(function() {
		    var mid = this.value; 
		    var sList = new Array();
		    var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
			<c:forEach var="item" items="${getSubModuleNameList}" varStatus="num" >
				if('${item.module.id}' == mid){
					options += '<option value="${item.id}">${item.submodule_name}</option>';
				}
			</c:forEach>
			$("select#screen_submodule_id").html(options); 
		});  
	    $('html').bind('cut copy paste', function (e) {
			e.preventDefault();
		});
		   
		$("html").on("contextmenu",function(e){
			return false;
		}); 
		$('#screen_name').keyup(function() {
			this.value = this.value.toUpperCase();
		}); 
		watermarkreport();
			
		if('${module_id1}' != ""){
		    $("div#divwatermark").val('').addClass('watermarked'); 
	   		$("div#divSerachInput").show();
	   		$("#divPrint").show();
	   		$("#screen_module_id").val('${module_id1}');
	   		sm_id_sel('${module_id1}','${sub_module_id1}');
	    }
	    
	
		    
		try{
	    	if(window.location.href.includes("msg="))
	    	{
	    		var url = window.location.href.split("?msg")[0];
	    		window.location = url;
	    	} 	
	    }
		catch (e) {} 
		
   	});
  
   function sm_id_sel(mid,smid){
	   var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
	   <c:forEach var="item" items="${getSubModuleNameList}" varStatus="num" >
		if('${item.module.id}' == mid){
			 if('${item.id}' == smid){
				options += '<option value="${item.id}" selected >${item.submodule_name}</option>';
			 }else{
				 options += '<option value="${item.id}" >${item.submodule_name}</option>';
			 }
		}	
		</c:forEach>
		$("select#screen_submodule_id").html(options);
   }
   
   function clearall()
   {		
   	document.getElementById('divPrint').style.display='none';
  
   	$("#searchInput").val("");
   	$("div#divSerachInput").hide();  
   	
   }
  
</script>

<script nonce="${cspNonce}" type="text/javascript">
function reportScreen(){
	
	$("#module_id1").val($("select#screen_module_id").val());
	$("#sub_module_id1").val($("select#screen_submodule_id").val());
	
	document.getElementById('searchForm').submit();
}
</script>

<script nonce="${cspNonce}" type="text/javascript">
function Update(id,sc_name,sc_url,mid,smid,sid){	
	document.getElementById('screen_name').value=sc_name;
	document.getElementById('screen_url').value=sc_url;
	document.getElementById("screen_url").readOnly = true; 
	
	$("select#screen_module_id").val(mid);	
	sm_id_sel(mid,smid);
	$('#screen_module_id').trigger('change');
	$("select#screen_submodule_id").val(smid);	
	$('#screen_submodule_id').trigger('change');
	document.getElementById('screen_id').value=id;
	document.getElementById('update_btn').style.display='inline-block'; 
	document.getElementById('save_btn').style.display='none'; 
}

</script>
<script nonce="${cspNonce}" type="text/javascript">
$(function(){
	$('#screen_name').keyup(function(){	
		yourInput= this.value.toUpperCase();
		re = /[0-9`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\]/gi;
		var isSplChar = re.test(yourInput);
		if(isSplChar)
		{
			//alert("Don't Enter Special Character");
			var no_spl_char = yourInput.replace(/[0-9`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\]/gi, '');
			$(this).val(no_spl_char);
		}
	});
});
</script>