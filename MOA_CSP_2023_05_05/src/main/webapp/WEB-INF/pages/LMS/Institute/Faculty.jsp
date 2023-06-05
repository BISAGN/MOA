<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script>

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
					<h2>FACULTY MASTER</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item">
								<a href="#">Institute</a>
							</li>
							<li class="breadcrumb-item active" aria-current="page">Faculty Master</li>
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
            	 <form:form name="Faculty" id="Faculty" action="FacultyAction" method="post" class="form-horizontal" modelAttribute="FacultyCMD">
				<div class="card-style mb-30">
					<h6 class="mb-25">FACULTY MASTER</h6>
						<div class="row">
							

							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									 <label class=" form-control-label">System Name<span class="mandatory">*</span></label>
									<div class="select-position">
									<select name="system_id" id="system_id" class="form-control" onchange="fn_pre_domicile_System();">
										<option value="0">--Select--</option>
										<%-- <c:forEach var="item" items="${getMedSystemName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach> --%>
										
										<c:forEach var="item" items="${getsysList}"
										varStatus="num"> 
 										<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
 									</c:forEach> 
										
									</select>
									</div>
								</div>								
	
								<!-- end select -->
							</div>
							
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									 <label class=" form-control-label">Course Name<span class="mandatory">*</span></label>
									<div class="select-position">
									<select name="course_id" id="course_id" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getCourseNamelist}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
									</div>
								</div>	
							</div>
							
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="input-style-1">
	            					     
	            					      <label class=" form-control-label">Faculty Name<span class="mandatory">*</span></label>
	                 					<input type="text" id="faculty_name" name="faculty_name" maxlength="50" class="form-control" autocomplete="off"
	                 					 onkeypress="return onlyAlphabetsStringSpace(this,event);" oninput="this.value = this.value.toUpperCase()" > 
									<input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
						                
					            </div>
								
								
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="input-style-1">
	            					     
	            					      <label class=" form-control-label">Ayush Id <span class="mandatory">*</span></label>
	                 					<input type="text" id="ayush_id" name="ayush_id" maxlength="50" class="form-control" autocomplete="off"
	                 					 onkeypress="return onlyAlphabetsStringSpace(this,event);" oninput="this.value = this.value.toUpperCase()" > 
										<input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">						                
					            </div>
								
								
							</div>
							
							
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									 <label class=" form-control-label">Status<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="status" id="status" class="form-control">
																							
											<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>								
								<div class="input-style-2 mt-3">
								<input type="hidden" id="id" name="id" value="0" class="form-control" autocomplete="off" />
								</div>
								
								<!-- end select -->
							</div>
							
			

						</div>					
					
						<ul class="buttons-group mainbtn">

							<li>
							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon btn-search" type="button" ><i class="lni lni-search-alt"></i>Search</a>
							</li>
							<li>
								
								<input id="btn-save"  class="main-btn info-btn btn-hover" value="Save" type="submit" onclick="return Validate();" >
								
							</li>
							<li>
								<a href="FacultyUrl" class="main-btn dark-btn n btn-hover btn-clear" type="button">Reset</a>
							</li>
						</ul>
						

						
							
						
				
				
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
				<table class="table" id="Faculty_Search">
					<thead>
							<tr>
									<th style="font-size: 15px; width: 10%;">Ser No</th>
									<th style="font-size: 15px">System Name</th>
									<th style="font-size: 15px">Course Name</th>
									<th style="font-size: 15px">Faculty Name</th>
									<th style="font-size: 15px">Ayush Id</th>
									<th style="font-size: 15px; width: 20%;">Action</th>
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
<form:form name="Faculty" id="Faculty" action="FacultyAction" method="post" class="form-horizontal" modelAttribute="FacultyCMD">
<div class="container-fluid">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5><b>FACULTY MASTER</b></h5>
<!-- 					<h6 class="enter_by"> -->
<!-- 						<span style="font-size: 12px; color: red;">(To be entered by Ayush)</span> -->
<!-- 					</h6> -->
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> SYSTEM NAME</label>
								</div>
								<div class="col-md-8">
									<select name="system_id" id="system_id" class="form-control" onchange="fn_pre_domicile_System();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getMedSystemName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
										
										<c:forEach var="item" items="${getsysList}"
										varStatus="num"> 
 										<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
 									</c:forEach> 
										
									</select>
								</div>
								
								<br><br>
								
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> COURSE NAME</label>
								</div>
								<div class="col-md-8">
									<select name="course_id" id="course_id" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getCourseNamelist}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> FACULTY NAME</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="faculty_name" name="faculty_name" maxlength="50" class="form-control" autocomplete="off" onkeypress="return onlyAlphabetsStringSpace(this,event);" oninput="this.value = this.value.toUpperCase()" > 
									<input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> AYUSH ID</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="ayush_id" name="ayush_id" maxlength="50" class="form-control" autocomplete="off" onkeypress="return onlyAlphabetsStringSpace(this,event);" oninput="this.value = this.value.toUpperCase()" > 
									<input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;"> </strong>STATUS</label>
								</div>
								<div class="col-md-8">
									<select name="status" id="status" class="form-control">
																						
										<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="FacultyUrl" class="btn btn-success btn-sm"><button type="button" class="btn-clear" value="Reset" > RESET</button></a> 
					<input type="submit" class="btn-save" value="Save" onclick="return Validate();">
<!-- 					<i class="action_icons searchButton"></i><input type="button" id ="btn-reload" class="btn btn-info btn-sm"  value="Search"> -->
					<button type="button" class="btn-search" id="btn-reload" ><i class="fa fa-search">SEARCH</i></button>
				</div>
			</div>
		</div>
	</div>
	</div>

	<br>

	
	
			<div class="container">
	<table id="Faculty_Search"
		class="display table no-margin table-striped  table-hover  table-bordered">
		<thead>
			<tr>
				<th style="font-size: 15px; width: 10%;">Ser No</th>
							<th style="font-size: 15px">System Name</th>
							<th style="font-size: 15px">Course Name</th>
							<th style="font-size: 15px">Faculty Name</th>
							<th style="font-size: 15px">Ayush Id</th>
							<th style="font-size: 15px; width: 20%;">Action</th>
			</tr>
		</thead>
		
		<tbody >
		
		</tbody >
		
	</table>
</div>
</form:form> --%>


<c:url value="getSearch_Faculty_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="Faculty_name1">
	<input type="hidden" name="system_id1" id="system_id1" />
	<input type="hidden" name="course_id1" id="course_id1" />
	<input type="hidden" name="Faculty_name1" id="Faculty_name1" />
	<input type="hidden" name="Ayush_id1" id="Ayush_id1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>
<c:url value="Edit_Faculty" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>
<c:url value="delete_Faculty" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>
<c:url value="Facultyreport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>
<script>

$(document).ready(function() {

	mockjax1('Faculty_Search');
	table = dataTable('Faculty_Search');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});

});

</script>
<Script>
	$(document).ready(function() {
		if ('${list.size()}' == "") {
			$("div#Faculty_Search").hide();
		}
		if ('${system_id1}' != "") {
			$("#system_id").val('${system_id1}');
		}
		if ('${course_id1}' != "") {
			$("#course_id").val('${course_id1}');
		}
		if ('${Faculty_name1}' != "") {
			$("#faculty_name").val('{Faculty_name1}');
		}
		if ('${Ayush_id1}' != "") {
			$("#ayush_id").val('{Ayush_id1}');
		}
		if ('${status1}' != "") {
			$("Select#status").val('${status1}');
		}
	});
	
	
	function data(search_system) {
		//debugger;
		jsondata = [];
		var table = $('#' + search_system).DataTable();
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
		var system = $("#system_id").val();
		var course = $("#course_id").val();
		var faculty = $("#faculty_name").val();
		var ayush_id = $("#ayush_id").val();
		var status = $("#status").val();


		$.post("getFilterfaculty?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			system:system,
			course:course,
			faculty:faculty,
			ayush_id:ayush_id,
			status:status
			
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ i + 1,j[i].system_name,j[i].course_name,j[i].faculty_name,j[i].ayush_id,j[i].action ]);
			}
		});
		$.post("getTotalfaculty_dataCount?" + key + "=" + value, {

			Search : Search,
			system:system,
			course:course,
			faculty:faculty,
			ayush_id:ayush_id,
			status:status
		}, function(j) {
			
			FilteredRecords = j;

			});
	}
	

	function Search() {
		$("#course_id1").val($('#course_id').val());
		$("#system_id1").val($('#system_id').val());
		$("#Faculty_name1").val($('#faculty_name').val());
		$("#Ayush_id1").val($('#ayush_id').val());
		$("#status1").val($('#status').val());
		document.getElementById('searchForm').submit();
	}
	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	function editData(id) {
		$("#id2").val(id);
		$("#updateForm").submit();
	}
	function Validate() {
		if ($("select#system_id").val() == 0) {
			alert("Please Select System Name");
			$("select#system_id").focus();
			return false;
		}
		if ($("select#course_id").val() == 0) {
			alert("Please Select Course Name");
			$("select#course_id").focus();
			return false;
		}
		if ($("input#faculty_name").val().trim() == "") {
			alert("Please Enter Faculty Name");
			$("input#faculty_name").focus();
			return false;
		}
		if ($("input#ayush_id").val().trim() == "") {
			alert("Please Enter Ayush Id");
			$("input#ayush_id").focus();
			return false;
		}
		if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
		return true;
	}
	function fn_pre_domicile_System() {
		var text = $("#system_id option:selected").text();
		var contry_id = $('select#system_id').val();
		$
				.post("getCourseListFormcon1?" + key + "=" + value, {
					contry_id : contry_id
				})
				.done(
						function(j) {
							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#course_id").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}
</Script>
