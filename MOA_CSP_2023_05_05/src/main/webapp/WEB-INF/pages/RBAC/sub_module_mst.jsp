<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
	<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script>
	<link rel="stylesheet" href="js/watermark/watermark.css">
	<script src="js/watermark/watermark_onclick.js" type="text/javascript"></script>
<!-- INTERNAL REMOVE START (NEED TO ADD IN PARTICULAR JSP)-->
			<link rel="stylesheet" href="assets/vendor/internal_css.css">
<!-- INTERNAL REMOVE END-->
	<script nonce="${cspNonce}" type="text/javascript">
		window.history.forward();
		function noBack() {
			window.history.forward();
		}
		
		$(document).ready(function () {
		
		     $('#submodule_name').keyup(function() {
		        this.value = this.value.toUpperCase();
		    });
		     watermarkreport();
		    try{
	    		   if(window.location.href.includes("msg="))
	    			{
	    				var url = window.location.href.split("?msg")[0];
	    				window.location = url;
	    			} 	
	    		}
	    		catch (e) {
	    			
	    		} 
	    		
	    		
	    		$("#searchInput").on("keyup", function() {
		  			var value = $(this).val().toLowerCase();
		  			$("#SubModuleReport tbody tr").filter(function() { 
		  			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		  			});
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
					<h2>Sub Module Master</h2>
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
							<li class="breadcrumb-item active" aria-current="page">Sub Module Master</li>
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
                 <form:form name="sub_module_mst" id="sub_module_mst" action="sub_module_mstAction" method='POST' modelAttribute="sub_module_mstCMD">
					<div class="card-style mb-30">
					<h6 class="mb-25">Sub Module Master</h6>
						<div class="row">
		
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-2">
									<label for="text-input">Sub Module Name <span class="mandatory">*</span></label>
									<input id="submodule_name" name="submodule_name" class="autocomplete UpperClassName txt-transupp"
										autocomplete="off" maxlength="255" placeholder="Enter Sub Module Name" />
								</div>
								<!-- end input -->
							</div>
            							
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label>Module<span class="mandatory">*</span></label>
									<div class="select-position">
										<select  name="module.id" id="module_id">
												<option value="0">--Select--</option>
		               							<c:forEach var="item" items="${getModuleNameList}" varStatus="num" >
		               								<option value="${item.id}">${item.module_name}</option>
		               							</c:forEach>
										</select>
									</div>
								</div>								
								
								<!-- end select -->
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div id="modulecheckboxes"></div>	
							</div>

							<div>
								<input type="hidden" name="submodule_id" id="submodule_id"/>
								<input type="hidden" name="submodule_old" id="submodule_old"/>
								<input type="hidden" name="module_old" id="module_old"/>
							</div>
						</div>		
	              		
						<ul class="buttons-group mainbtn">

						<!-- 	<li>
							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button" value="Search"><i class="lni lni-search-alt"></i>search</a>
							</li> -->
							<li id="btn-save">
								<input class="main-btn info-btn btn-hover" type="submit" value="Save" >
							</li> 
							<li id="update_btn" class="custom-d-none">
							<input type="submit" class="main-btn deactive-btn btn-hover" value="Update" >
							</li>
							<li><a  href="sub_module_mstUrl"
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
		 		<input id="searchInput" class="sub_mod" type="text" placeholder="&#xF002; Search Word"  size="35" class="form-control">
			</div>
			<div class="table-wrapper table-responsive custom-datatable-p simple-table" id="att_Tb">
				<table class="table" id="SubModuleReport">
					<thead>
						<tr>
							<th><h6>Ser No</h6></th>	
							<th><h6>Sub Module Name</h6></th>	
							<th><h6>Module Name</h6></th>		
							<th class='lastCol'><h6>Action</h6></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}" varStatus="num" >
							<tr>
								<td>${num.index+1}</td>
								<td>${item.submodule_name}</td>
								<td>${item.module_name}</td>	
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

<!-- 		old 	 	-->

  <%-- <form:form name="sub_module_mst" id="sub_module_mst" action="sub_module_mstAction" method='POST' modelAttribute="sub_module_mstCMD">
	<div class="container">
       	<div class="card">
         		<div class="card-header"> <h5>Sub Module Master</h5></div><!-- end of card-header -->
         			<div class="card-body card-block">
         				<div class="row mb-3">
	               					<div class="col-md-2">
	                 					<label for="text-input" >Sub Module Name <strong style="color: red;">*</strong></label> 
	               					</div>
	               					<div class="col-md-4">
	                 					<input  id="submodule_name" name="submodule_name" style="font-family: 'FontAwesome',Arial;text-transform: uppercase;"  class="form-control" autocomplete="off" maxlength="255">
									</div>
 						</div>
 						<div class="row mb-3">
	                					<div class="col-md-2">
	                  						<label class=" form-control-label">Module <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-md-4">
               								<!-- <select name="" class="form-control" id ="modulelist"  onchange="myFunction(event)">  </select> -->
               								<select name="module.id" class="form-control" id ="module_id" >
		                 						<option value="0">--Select--</option>
		               							<c:forEach var="item" items="${getModuleNameList}" varStatus="num" >
		               								<option value="${item.id}">${item.module_name}</option>
		               							</c:forEach>
		                 					</select>
               							</div>
						</div>
							
						 <div class="row mb-3">
               					<div class="col-md-6">
               						<div id="modulecheckboxes"></div>	                					
               				    </div>
	                    </div>
							 
							  <input type="hidden" name="submodule_id" id="submodule_id"/>
							  <input type="hidden" name="submodule_old" id="submodule_old"/>
							  <input type="hidden" name="module_old" id="module_old"/>
							  
				 </div> <!-- end of card-body -->
				 
         			<div class="card-footer">
         	            <input type="reset" class="btn btn-success btn-sm" value="Clear">    	
	              		<input type="submit" id="save_btn" class="btn btn-primary btn-sm" value="Save" onclick="return isValid();">
	              		<input type="submit" id="update_btn" class="btn btn-secondary btn-sm" value="Update" style="display: none" onclick="return isValid();" >       		
         	        </div> <!-- end of card-footer -->      	 
		</div> <!-- end of card -->
	</div>     <!-- end of container -->   
   </form:form>
	<div class="container">
	<div id="divSerachInput">
	 	<input id="searchInput" type="text" style="font-family: 'FontAwesome',Arial;margin-bottom: 5px;width: 50%;" placeholder="&#xF002; Search Word"  size="35" class="form-control">
	</div>
   <div id="divPrint" >
     <div id="divShow"></div>
		 <div  class="watermarked" data-watermark="" id="divwatermark" >
				<span id="ip"></span>
				<table id="SubModuleReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
					<thead>
						<tr style="font-size: 15px ;">
							<th width="10%">Ser No</th>	
							<th width="40%">Sub Module Name</th>	
							<th width="40%">Module Name</th>		
							<th width="10%" style="text-align: center;" class='lastCol'>Action</th>
						</tr>
					</thead> 
					<tbody >
						<c:forEach var="item" items="${list}" varStatus="num" >
								<tr style="font-size: 13px;">
									<td width="10%">${num.index+1}</td>
									<td width="40%">${item.submodule_name}</td>
									<td width="40%">${item.module_name}</td>	
									<td width="10%" align="center">${item.id}</td>					
								</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
	</div>
</div> --%>
  <script nonce="${cspNonce}" type="text/javascript">
function isValid()
   {	
	   if($("input#submodule_name").val()==""){
			alert("Please Enter Sub Module Name");
			$("input#submodule_name").focus();
			return false;
		}
	   
   		 if($("select#module_id").val()== 0){
   			alert("Please Select Module");
   			$("select#modulelist").focus();
   			return false;
   		}  
   	
   	return true;
  
   }
      
</script>  

<script nonce="${cspNonce}" type="text/javascript">

	
	document.querySelectorAll('.updateOnclick').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('id'+val).value;
			var hprof = document.getElementById('smname'+val).value;
			var pd = document.getElementById('mid'+val).value;
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				Update(hid,hprof,pd);
			} else {
				return false;
			}
		})
	});
	

function Update(id,name,mid){	
	debugger;
	document.getElementById('submodule_name').value=name;
	document.getElementById('submodule_id').value=id; 	
	$("select#module_id").val(mid);
	document.getElementById('update_btn').style.display='inline-block'; 
	document.getElementById('btn-save').style.display='none'; 
}

document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('btn-save').onclick = function() {
		return isValid();
	};
	
	document.getElementById('update_btn').onclick = function() {
		return isValid();
	};
});

</script>
</body>
</html>
