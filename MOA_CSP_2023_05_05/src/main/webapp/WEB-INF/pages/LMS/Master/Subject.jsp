<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<form:form name="Subject" id="Subject" action="SubjectAction" method="post" class="form-horizontal" modelAttribute="SubjectCMD">
<div class="container-fluid">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5><b>SUBJECT MASTER</b></h5>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> System Name</label>
								</div>
								<div class="col-md-8">
									<select name="system_id" id="system_id" class="form-control" onchange="fn_pre_domicile_System();">
										<option value="0">--Select--</option>
										
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
									<label class=" form-control-label"><strong style="color: red;">* </strong> Course Name</label>
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
									<label class=" form-control-label"><strong style="color: red;">* </strong> Subject Name</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="subject_name" name="subject_name" maxlength="50" class="form-control" autocomplete="off" onkeypress="return onlyAlphabetsStringSpace(this,event);" oninput="this.value = this.value.toUpperCase()" > 
									<input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Status</label>
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
					<a href="Subject" class="btn btn-success btn-sm"><button type="button" class="btn-clear" value="Reset" > RESET</button></a> 
					<input type="submit" class="btn-save" value="Save" onclick="return Validate();">
					<button type="button" class="btn-search" id="btn-reload" ><i class="fa fa-search">SEARCH</i></button>
				</div>
			</div>
		</div>
	</div>
	</div>

	<br>

	<div class="container">
		<table id="Subject_Search"
			class="display table no-margin table-striped  table-hover  table-bordered">
			<thead>
				<tr>
					<th style="font-size: 15px; width: 10%;">Ser No</th>
					<th style="font-size: 15px">System Name</th>
					<th style="font-size: 15px">Course Name</th>
					<th style="font-size: 15px">Subject Name</th>
					<th style="font-size: 15px; width: 20%;">Action</th>
				</tr>
			</thead>

			<tbody>

			</tbody>

		</table>
	</div>
</form:form>
<c:url value="getSearch_Subject_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Subject_name1">
	<input type="hidden" name="system_id1" id="system_id1" />
	<input type="hidden" name="course_id1" id="course_id1" />
	<input type="hidden" name="Subject_name1" id="Subject_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_Subject" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="delete_Subject" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Subjectreport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>
<script nonce="${cspNonce}" type="text/javascript">

	$(document).ready(function() {

		mockjax1('Subject_Search');
		table = dataTable('Subject_Search');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});

	});
</script>
<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		if ('${list.size()}' == "") {
			$("div#Subject_Search").hide();
		}
		if ('${system_id1}' != "") {
			$("#system_id").val('${system_id1}');
		}
		if ('${course_id1}' != "") {
			$("#course_id").val('${course_id1}');
		}
		if ('${Subject_name1}' != "") {
			$("#subject_name").val('{Subject_name1}');
		}
		if ('${status1}' != "") {
			$("Select#status").val('${status1}');
		}
	});
	
	
	function data(search_system) {
		
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
		var system = $("#system_id").val();
		var course = $("#course_id").val();
		var subject = $("#subject_name").val();
		var status = $("#status").val();


		$.post("getFiltersubject?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			system:system,
			course:course,
			subject:subject,
			status:status
			
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ i + 1,j[i].system_name,j[i].course_name,j[i].subject_name,j[i].action ]);
			}
		});
		$.post("getTotalsubject_dataCount?" + key + "=" + value, {

			Search : Search,
			system:system,
			course:course,
			subject:subject,
			status:status
		}, function(j) {
			
			FilteredRecords = j;

			});
	}
	

	function Search() {
		$("#course_id1").val($('#course_id').val());
		$("#system_id1").val($('#system_id').val());
		$("#Subject_name1").val($('#subject_name').val());
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
		if ($("input#subject_name").val().trim() == "") {
			alert("Please Enter Subject Name");
			$("input#subject_name").focus();
			return false;
		}
		if ($("select#status").val() == "0") {
			alert("Please Select Status.");
			$("select#status").focus();
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
