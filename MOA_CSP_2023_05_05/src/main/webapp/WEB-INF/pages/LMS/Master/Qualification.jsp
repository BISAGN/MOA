<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> 
<script type="text/javascript" src="js/watermark/common.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/watermark/cueWatermark.css">
<script src="js/watermark/cueWatermark.js" type="text/javascript"></script>

<style>
.table th:nth-child(2), .table td:nth-child(2){
   width: 135px;
}
.table th:nth-child(3), .table td:nth-child(3),
.table th:nth-child(4), .table td:nth-child(4){
   width: 100px;
}
.table th:nth-child(5), .table td:nth-child(5){
   width: 110px;
}
@media (max-width: 1199px){
   .col-md-2{
       flex: 0 0 20%;
       max-width: 20%;
   }
}
</style>
<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" />
</c:if>

<form:form name="Mqualificationform" id="Mqualificationform"
	action="Mqualification_Action" method='POST'
	modelAttribute="Mqualification_cmd">
	<div class="container-fluid" align="center">
		<div class="card">
			<div class="card-header">
				<h5>Qualification</h5>
			</div>
			<div class="card-body card-block">


				<div class="row">
					<div class="col-lg-6">
						<div class="row form-group">
							<div class="col-6">
								<label for="text-input" class=" form-control-label">Qualification
									<strong style="color: red;">*</strong>
								</label>
							</div>
							<div class="col-6">
								<input type="text" id="qualification" name="qualification"
									class="form-control" autocomplete="off" maxlength="20" />
							</div>
						</div>
					</div>
				</div>
			</div>

			<input type="hidden" id="qualification_id" name="qualification_id"
				value="0" />
			<div class="card-footer" align="center">
				<button type="button" class="btn btn-success btn-sm">
					<a href="QualificationUrl"><i class="fa fa-trash-o"></i>&nbsp;
						Clear</a>
				</button>
				<button type="submit" class="btn btn-primary btn-sm" value="Save"
					onclick="return isValidateClientSide();">
					<i class="fa fa-save"></i>&nbsp;Save
				</button>

			</div>

		</div>
	</div>
</form:form>

<c:url value="deleteQualificationUrlAdd" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
	<input type="hidden" name="deleteid" id="deleteid" value="0"/>
</form:form>   

<div style="width: 70%; padding-left: 1%; display: block;" id="divSerachInput">
	<input id="searchInput" type="text" placeholder="&#xF002; Find If Any :- Qualification "  size="35" class="form-control report_search">
</div>
<div class="container-fluid" id="divPrint" style="display: block;">
	<div id="divShow" style="display: block;"></div>

	<div class="watermarked">
		<table id="QualificationReport"
			class="table no-margin table-striped  table-hover  table-bordered ">
			<thead>
				<tr>
					<th class='firstCol'>Sr No.</th>
					<th>Qualification</th>
					<th class='lastCol'>Action</th>
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
						<td class='firstCol'>${num.index+1}</td>
						<td>${item[0]}</td>
						<td class='lastCol'>${item[1]}${item[2]}</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<br>
<script type="text/javascript">
	function isValidateClientSide() {

		if ($("input#qualification").val() == "") {
			alert("Please Enter Qualification");
			$("input#qualification").focus();
			return false;
		}

		return true;
	}
</script>
<script>

	$(document).ready(function() {

		if ('${msg}' != "") {
			window.alert = function(al, $) {
				return function(msg) {
					// al.call(window,msg);
					$(window).trigger("okbuttonclicked");
				};
			}(window.alert, window.jQuery);

			$(window).on("okbuttonclicked", function() {
				console.log("you clicked ok");
				window.location = window.location.href.split("?")[0];
			});
			alert('${msg}');
			jQuery("div#errorDiv").show();
		}

	});

	function printDiv() {
		var printLbl = [];
		var printVal = [];
		printDivOptimize('divPrint', 'Qualification', printLbl, printVal,
				"divwatermark");
	}
</script>

 <script>

function editData(id,quali){	
	
	$("#qualification_id").val(id);	
	$("#qualification").val(quali);	

}

function deleteData(id){		
	document.getElementById('deleteid').value=id;
	document.getElementById('deleteForm').submit();
}  
</script>
