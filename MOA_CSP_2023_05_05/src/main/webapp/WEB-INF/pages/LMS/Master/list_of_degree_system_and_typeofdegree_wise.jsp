<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>



<section class="dashboard-page">
<div class="container-fluid">
	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2><span id="lbladd1"></span>List of Degree</h2>
				</div>
			</div>
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">List of Degree</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row" id="pop">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-table b-top"
						id="container-table">
						<table class="table" id="pop">
							<thead>
								<tr>
									<th  class="middle-center"><label class="ml-5 bold">Sr.No.</label></th>
									<th  class="middle-center"><label class="ml-5 bold">System</label></th>
									<th  class="middle-center"><label class="ml-5 bold">Type of Degree</label></th>
									<th  class="middle-center"><label class="ml-5 bold">Degree</label></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		
</div>
</section>

	
<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	getData();
	
});

document.addEventListener('DOMContentLoaded', function() {
	
});

function getData() {
	$.post("getListofdegSysToD?"+key+"="+value,{},function(j) {
		for(var i=0;i<j.length;i++){
			
				$("table#pop").append('<tr><td><label>'+(parseInt(i)+1)+'</label></td>' 
							+'<td><label>'+j[i][0]+'</label></td>' 
					        +'<td><label>'+j[i][1]+'</label></td>'
							+'<td><label>'+j[i][2]+'</label></td>'
							+'</tr>');
			
		}
  });
}

</script>