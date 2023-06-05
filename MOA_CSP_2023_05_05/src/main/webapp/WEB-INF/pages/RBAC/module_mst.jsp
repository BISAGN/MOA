<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> 
<link rel="stylesheet" href="js/watermark/watermark.css">
<script src="js/watermark/watermark_onclick.js" type="text/javascript"></script>



	<script nonce="${cspNonce}" type="text/javascript">
		window.history.forward();
		function noBack() {
			window.history.forward();
		}
		
		$(document).ready(function (){
			
			$("#searchInput").on("keyup", function() {
				var value = $(this).val().toLowerCase();
				$("#ModuleReport tbody tr").filter(function() { 
				$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
				});
		});
			setTimeLoadForTable();
			
			 $('html').bind('cut copy paste', function (e) {
		        e.preventDefault();
		    });
		   
		    $("html").on("contextmenu",function(e){
		        return false;
		    }); 
		    
		    $('#module_name').keyup(function() {
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
			});
		
		
	</script>
	
<section class="dashboard-page">
<div class="container-fluid">

	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>Module Master</h2>
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
							<li class="breadcrumb-item active" aria-current="page">Module Master</li>
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
                 <form:form name="module_mst" id="module_mst" action="module_mstAction" method='POST' modelAttribute="module_mstCMD"> 
					<div class="card-style mb-30">
					<h6 class="mb-25">Module Master</h6>
						<div class="row">

							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-2">
									<label for="text-input">Module Name <span class="mandatory">*</span></label>
									<input id="module_name" name="module_name" class="autocomplete UpperClassName txt-transupp"
										autocomplete="off" maxlength="20" placeholder="Enter Module Name" />
									<input id="module_old_name" name="module_old_name" type="hidden" class="autocomplete UpperClassName txt-transupp"
										autocomplete="off" />
								</div>
								<!-- end input -->
							</div>


						</div>		
           	
						<ul class="buttons-group mainbtn">

						<!-- 	<li>
							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button" value="Search"><i class="lni lni-search-alt"></i>search</a>
							</li> -->
							<li>
								<input id="btn-save" class="main-btn info-btn btn-hover" type="submit" value="Save" >
							</li> 
							<li>
							<input type="submit" id="update_btn" class="main-btn deactive-btn btn-hover" value="Update" class="custom-d-none">
							</li>
							<li><a  href="module_mstUrl"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
						</ul>
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
						placeholder="&#xF002; Search Word" size="35" class="form-control custom_mst_role_searchip">
			</div>
			<div class="table-wrapper table-responsive custom-datatable-p simple-table" id="att_Tb">
				<table class="table" id="ModuleReport">
					<thead>
							<tr>
								<th><h6>Ser No</h6></th>	
								<th><h6>Module Name</h6></th>			
								<th class='lastCol'><h6>Action</h6></th>
							</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}" varStatus="num" >
						<tr>
							<td>${num.index+1}</td>
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

  <%-- <form:form name="module_mst" id="module_mst" action="module_mstAction" method='POST' modelAttribute="module_mstCMD">
	<div class="container">
       	<div class="card">
       		<div class="card-header"> <h5>Module Master</h5><!-- <strong>Schedule Details </strong> --></div>
      			<div class="card-body card-block">
      				<div class="row mb-3"> 
             					<div class="col-md-2">
               					<label for="text-input"  >Module Name <strong style="color: red;">*</strong></label> 
             					</div>
             					<div class="col-md-4">
               				     <input  id="module_name" name="module_name" style="font-family: 'FontAwesome',Arial;"  class="form-control" autocomplete="off" maxlength="20">
					        	<input  id="module_old_name" name="module_old_name" type="hidden" class="form-control" autocomplete="off" >
						</div>
				</div>
			</div><!-- end of card-body -->
       		<div class="card-footer">
     	        <input type="reset" class="btn btn-success btn-sm" value="Clear">    	
           		<input type="submit"  id="save_btn" class="btn btn-primary btn-sm" value="Save" onclick="return isValid();">
           		<input type="submit" id="update_btn" class="btn btn-secondary btn-sm" value="Update" onclick="return isValid();" style="display: none">
           	</div><!-- end of card-footer -->
        </div> <!-- end of card -->
</div> <!-- end of container -->
</form:form>

<div class="container"  id="divPrint"  >
 		<div id="divShow"></div>
		 <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
			<span id="ip"></span>
			<table id="ModuleReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
				<thead>
					<tr style="font-size: 15px;">
						<th style=" width:10%;">Ser No</th>	
						<th style=" width:80%;">Module Name</th>			
						<th style="text-align: center;  width:10%;" class='lastCol'>Action</th>
					</tr>
				</thead> 
				<tbody >
					<c:forEach var="item" items="${list}" varStatus="num" >
						<tr style="font-size: 13px;">
							<td style=" width:10%;">${num.index+1}</td>
							<td style=" width:80%;">${item.module_name}</td>	
							<td style="text-align: center;  width:10%;">${item.id}</td>														
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
</div> --%>

<script nonce="${cspNonce}" type="text/javascript">
$("#update_btn").hide();
function isValid()
{
	if($("#module_name").val() == ""){
		alert("Please Enter Module Name");
		$("#module_name").focus();
		return false;
   	}
	return true;
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

<script nonce="${cspNonce}" type="text/javascript">	

document.querySelectorAll('.updateOnclick').forEach((items, index) => {
	items.addEventListener('click', event => {
		
		var val=parseInt(index)+1;
		var hid = document.getElementById('id'+val).value;
		var hprof = document.getElementById('name'+val).value;
		
		
		if (confirm('Are You Sure You Want to Edit Detail ?')) {
			Update(hid,hprof);
		} else {
			return false;
		}
	})
});

	function Update(id,name){
		$("#update_btn").show();
		$("#btn-save").hide();
		document.getElementById('module_name').value=name;
		document.getElementById('module_old_name').value=id;
		document.getElementById('update_btn').style.display='inline-block'; 
		document.getElementById('save_btn').style.display='none'; 
	}
</script>