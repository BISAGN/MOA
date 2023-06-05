<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <script src="js/miso/miso_js/jquery-2.2.3.min.js"></script> -->
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>


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
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="title mb-30">
					<h2>Classroom Master</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-lg-6 col-md-6 col-sm-12 col-lg-12 col-md-12 col-sm-12">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item">
								<a href="#">Institute</a>
							</li>
							<li class="breadcrumb-item active" aria-current="page">Classroom master</li>
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
			<div class="col-lg-12 col-md-12 col-sm-12">
				<!-- input style start -->
               <form:form name="Classroom" id="Classroom" action="ClassroomAction" method="post"  modelAttribute="ClassroomCMD">
				<div class="card-style mb-30">
					<h6 class="mb-25">Classroom master</h6>
						<div class="row">
							

							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-1">
            					     
            					      <label class=" form-control-label"> Classroom name <span class="mandatory">*</span></label>
                 					   <input type="text" id="classroom_name" name="classroom_name" class="form-control autocomplete" autocomplete="off" 
                 					   onkeypress="return onlyAlphabetsStringSpace(this,event); " oninput="this.value = this.value.toUpperCase()"  /> 
				                </div>
												
								<!-- end select -->
							</div>
							
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-1">
	            					     
	            					      <label class=" form-control-label"> Block name <span class="mandatory">*</span></label>
	                 					   <input type="text" id="block_name" name="block_name" class="form-control autocomplete" autocomplete="off" 
	                 					   onkeypress="return onlyAlphabetsStringSpace(this,event); " oninput="this.value = this.value.toUpperCase()" /> 
					            </div>
							</div>
							
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="input-style-1">
	            					     
	            					      <label class=" form-control-label">Strength<span class="mandatory">*</span></label>
	                 					  <input type="text" id="strength" name="strength" class="form-control autocomplete" autocomplete="off"
	                 					   onkeypress="" oninput="this.value = this.value.toUpperCase()"  /> 
						                
					            </div>
								
								
							</div>
							
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									 <label class=" form-control-label">Status<span class="mandatory">*</span></label>
									<div class="select-position">
										<input type="hidden" id="id" name="id" class="form-control"
									value="0" autocomplete="off"> 
												 <select name="status" class="form-control" id="status">
												<c:forEach var="item" items="${ActiveInActiveList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
			
											</select>
									</div>
								</div>								
								<div class="input-style-1 mt-3">
								<input type="hidden" id="id" name="id" value="0" class="form-control" autocomplete="off" />
								</div>
								
								<!-- end select -->
							</div>
							
			

						</div>					
					<!-- Bottom Button Start -->
								<div class="btn-bottom">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12">
						<ul class="buttons-group mainbtn">

							<li>
							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon btn-search" type="button" ><i class="lni lni-search-alt"></i>Search</a>
							</li>
							<li>
								
								<input id="btn-save"  class="main-btn info-btn btn-hover" value="Save" type="submit" onclick="return Validate();" >
								
							</li>
							<li>
								<a href="ClassroomUrl" class="main-btn dark-btn n btn-hover btn-clear" type="button">Reset</a>
							</li>
						</ul>
						</div>
						</div>
						</div>
						
				</div>
				<!-- end card -->
            </form:form>
			</div>
		</div>
	</div>

	

<div class="row">
	<div class="col-lg-12 col-md-12 col-sm-12">
		<div class="card-style mb-30">
			<div class="table-wrapper table-responsive custom-datatable-p">
				<table class="table" id="search_classroom">
					<thead>
							<tr>
								<th align="center">Sr no</th>
									<th id= "${item.id}">Classroom name</th>
									<th id= "${item.id}">Block name</th>
									<th id= "${item.id}">Strength</th>
								
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

<%-- <form:form name="Classroom" id="Classroom" action="ClassroomAction" method="post" class="form-horizontal" modelAttribute="ClassroomCMD"> 
<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5><b>CLASSROOM MASTER</b></h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              			
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> CLASSROOM NAME</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="classroom_name" name="classroom_name" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabetsStringSpace(this,event); " oninput="this.value = this.value.toUpperCase()"  /> 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> BLOCK NAME</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="block_name" name="block_name" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabetsStringSpace(this,event); " oninput="this.value = this.value.toUpperCase()" /> 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> STRENGTH</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="strength" name="strength" class="form-control autocomplete" autocomplete="off" onkeypress="" oninput="this.value = this.value.toUpperCase()"  /> 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                   <label class=" form-control-label"><strong style="color: red;">* </strong>STATUS</label>
						                </div>
						                <div class="col-md-8">
						                  <select name="status" class="form-control"
									id="status">
									<c:forEach var="item" items="${ActiveInActiveList}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>

								</select>
								<div class="col-md-6">
									<input type="hidden" id="id" name="id" value="0" class="form-control"
										autocomplete="off" />
								</div>
						                </div>
						            </div>
	              				</div>
	              				
	              				
	              			
	              				
	              				
	              				
	              			</div>
	              			
	              			
	              		
            			</div>
									
						<div class="card-footer" align="center" class="form-control">
							<a href="ClassroomUrl" class="btn btn-success btn-sm"><button type="button" class="btn-clear" value="Reset" > RESET</button></a> 
							
		              		<input type="submit" class="btn-save" value="Save"  onclick="return Validate();">
<!-- 		              		<i class="action_icons searchButton"></i><input type="button" id="btn-reload" class="btn btn-info btn-sm" onclick="Search();" value="Search"> -->
		              		<button type="button" class="btn-search" id="btn-reload" ><i class="fa fa-search">SEARCH</i></button>
			            </div> 		
	        	</div>
			</div>
	</div>

		
		
		<div class="container">
	<table id="search_classroom"
		class="display table no-margin table-striped  table-hover  table-bordered">
		<thead>
			<tr>
				<th align="center">SER NO</th>
					<th id= "${item.id}">CLASSROOM NAME</th>
					<th id= "${item.id}">BLOCK NAME</th>
					<th id= "${item.id}">STRENGTH</th>
				
				<th class="action">ACTION</th>
			</tr>
		</thead>
		
		<tbody >
		
		</tbody >
		
	</table>
</div>
		 
		
</form:form> --%>

<c:url value="getSearch_Classroom_Master" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="Classroom_name1">
			<input type="hidden" name="Classroom_name1" id="Classroom_name1" />
	</form:form> 
	
	  <c:url value="Edit_classroomUrl" var="Edit_Url"/>
	<form:form action="${Edit_Url}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
		  <input type="hidden" name="id2" id="id2">
	</form:form>
	
  <c:url value="delete_Url3" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
	</form:form>  
 <c:url value="Classroomreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 
<script>
$(document).ready(function() {

	mockjax1('search_classroom');
	table = dataTable('search_classroom');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});

});

function data(search_classroom) {
	//debugger;
	jsondata = [];
	var table = $('#' + search_classroom).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];

	//var wksp=$("#veh_id").val() ;
	var classroom_name = $("#classroom_name").val();
	var block_name = $("#block_name").val();
	var strength = $("#strength").val();
	var status = $("#status").val();


	$.post("getFilterclassroom_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		classroom_name:classroom_name,
		block_name:block_name,
		strength:strength,
		status:status
		
	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].ser, j[i].classroom_name,j[i].block_name,j[i].strength,j[i].action ]);
		}
	});
	$.post("getTotalclassroom_dataCount?" + key + "=" + value, {
		classroom_name:classroom_name,
		block_name:block_name,
		strength:strength
	}, function(j) {
		
		FilteredRecords = j;

		});
}

function editData(id) {
	
	$("#id2").val(id);
	document.getElementById('updateForm').submit();
}

function deleteData(id) {
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}


	</Script>
