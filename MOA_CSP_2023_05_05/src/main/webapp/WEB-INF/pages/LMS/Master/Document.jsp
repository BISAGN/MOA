<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> 
<script type="text/javascript" src="js/watermark/common.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/watermark/cueWatermark.css">
<script src="js/watermark/cueWatermark.js" type="text/javascript"></script>

<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" />
</c:if>

<form:form name="Documentform" id="Documentform" action="Doc_Action"
	method='POST' modelAttribute="Doc_cmd">

	<div class="container-fluid" align="center">
		<div class="card">
			<div class="card-header">
				<h5>DOCUMENT MASTER</h5>
			</div>
			<div class="card-body card-block">
				<div class="row">
					<div class="col-lg-6">
						<div class="row">
							<div class="col-6">
								<label for="text-input" class=" form-control-label">Document
									Name <strong style="color: red;">*</strong>
								</label>
							</div>
							<div class="col-6 ">
								<form:input id="doc_name" path="doc_name" class="form-control"
									maxlength="25" placeholder="Maximum 25 Character"
									onkeypress="return onlyAlphabetsStringSpace(event,this);"></form:input>
								<div class="col-6">
									<form:hidden id="id" path="id" value="0" class="form-control"
										autocomplete="off" />
								</div>
							</div>
						</div>
					</div>

					<div class="col-lg-6">
						<div class="row">
							<div class="col-6">
								<label for="text-input" class=" form-control-label">Document
									Type <strong style="color: red;">*</strong>
								</label>
							</div>
							<div class="col-6">
								<form:select id="doc_type" path="doc_type" class="form-control"
									multiple="multiple" onchange="arcboardCheck();">
									<option value="PDF">PDF</option>
									<option value="JPEG">JPEG</option>
									<option value="JPG">JPG</option>
									<option value="PNG">PNG</option>
								</form:select>

								<input type="hidden" id="doc_type_hid" name="doc_type_hid"
									class="form-control">
							</div>
						</div>
					</div>

				</div>
			</div>

			<div class="card-footer" align="center">
				<button type="button" class="btn btn-success btn-sm">
					<a href="DocUrl"><i class="fa fa-trash-o"></i>&nbsp;Clear</a>
				</button>
				<button class="btn btn-primary btn-sm" value="Save"
					onclick="return Validation();">
					<i class="fa fa-save"></i>&nbsp;Save
				</button>


			</div>

		</div>
	</div>

</form:form>

<div style="width: 70%; padding-left: 1%; display: block;" id="divSerachInput">
	<input id="searchInput" type="text" placeholder="&#xF002; Find If Any :-Document Name/Document Type"  size="35" class="form-control report_search">
</div>

<div class="container-fluid" id="getdocSearch">
	<div class="">
		<div id="divShow" style="display: block;"></div>
		<div class="watermarked">
			<span id="ip"></span>
			<table id="getPostSearch"
				class="table no-margin table-striped  table-hover  table-bordered">
				<thead>
					<tr>
						<th>Sr No</th>
						<th>Document Name</th>
						<th>Document Type</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center;" colspan="5">Data
								Not Available</td>
						</tr>
					</c:if>

					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td>${num.index+1}</td>
							<td>${item[0]}</td>
							<td>${item[1]}</td>
							<td>${item[2]}${item[3]}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<c:url value="search_Document" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="doc_name1">
	<input type="hidden" name="doc_name1" id="doc_name1" value="0" />
</form:form>

<c:url value="delete_Document" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="doc_name11">
	<input type="hidden" name="doc_name11" id="doc_name11" value="0" />
</form:form>

<script>

$(document).ready(function() 
{
	$("#searchInput").val("");
	 $("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#getdocSearch tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
});


	function editData(id, doc_name, doc_type) {
		$("#id").val(id);
		$("#doc_name").val(doc_name);
		$("#doc_type").val(doc_type);

		document.getElementById('id').value = id;
	}

	function deleteData(id) {

		$("#doc_name11").val(id);
		document.getElementById('deleteForm').submit();
	}

	function Validation() {

		if ($("input#doc_name").val() == "") {
			alert("Please Enter Document Name.");
			$("input#doc_name").focus();
			return false;
		}

		var maxLength = 25;
		var charLength = $("input#doc_name").val().length;

		if (charLength > maxLength) {
			alert("Please Enter Document should be less then 25.");
			$("input#doc_name").focus();
			return false;
		}

		if ($("select#doc_type").val() == null) {
			alert("Please Select Document Type.");
			$("select#doc_type").focus();
			return false;
		}
		return true;
	}

	function arcboardCheck() {

		if ($("#doc_type").val() != "" || $("#doc_type").val() != null) {
			var exam_center = $("#doc_type").val().join(",");
			document.getElementById("doc_type_hid").value = exam_center;
		} else {
			document.getElementById("doc_type_hid").value = "";
		}
	}
</script>

