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

<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" />
</c:if>

<form:form name="MNationalityform" id="MNationalityform"
	action="MNationality_Action" method='POST'
	modelAttribute="MNationality_cmd">
	<div class="container-fluid" align="center">
		<div class="card">
			<div class="card-header">
				<h5>Nationality</h5>
			</div>
			<div class="card-body card-block">
				<div class="row">
					<div class="col-lg-6">
						<div class="row form-group">
							<div class="col-6">
								<label for="text-input" class=" form-control-label">Nationality
									<strong style="color: red;">*</strong>
								</label>
							</div>
							<div class="col-6 ">
								<form:input id="nationality" path="nationality"
									class="form-control" maxlength="50"
									onkeypress="return onlyAlphabetsStringSpace(event, this);"
									autocomplete="off" cssStyle="text-transform: uppercase;"></form:input>
								<span class="errorClass" id="nationality_lbl"></span>
								<form:errors path="nationality" id="nationality"
									cssClass="errorClass1"></form:errors>
							</div>
						</div>
					</div>
				</div>
			</div>
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

<c:url value="updateNationalityUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form>

<c:url value="deleteNationalityUrlAdd" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="deleteid">
	<input type="hidden" name="deleteid" id="deleteid" value="0" />
</form:form>

<c:url value="NationalityUrl" var="mainFormUrl" />
<form:form action="${mainFormUrl}" method="GET" id="mainForm"
	name="mainForm">
</form:form>

<div style="width: 70%; padding-left: 1%; display: block;" id="divSerachInput">
	<input id="searchInput" type="text" placeholder="&#xF002; Find If Any :- Nationality "  size="35" class="form-control report_search">
</div>

<div class="container-fluid" id="divPrint" style="display: block;">
	<div id="divShow" style="display: block;"></div>

	<div class="watermarked">

		<table id="nationality"
			class="table no-margin table-striped  table-hover  table-bordered">
			<thead>
				<tr>
					<th class='firstCol'>Sr No.</th>
					<th>Nationality</th>
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
						<td>${item.nationality}</td>
						<td class='lastCol'>${item.id}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<br>

<script type="text/javascript">
	function isValidateClientSide() {

		if ($("input#nationality").val() == 0) {
			alert("Please Enter Nationality.");
			$("input#nationality").focus();
			return false;
		}

		return true;
	}
</script>

<script>

	$(document)
			.ready(
					function() {
						var myResponse = [];
						var myResponse1 = [];
						var wepetext = $("#nationality");
						wepetext
								.autocomplete({
									source : function(request, response) {
										$
												.ajax({
													type : 'POST',
													url : "getnationalityList?"
															+ key + "=" + value,
													data : {
														getcolumnname : document
																.getElementById('nationality').value
													},
													success : function(data) {
														if (data.length > 1) {
															var susval = [];
															var length = data.length - 1;
															var enc = data[length].columnName1
																	.substring(
																			0,
																			16);
															for (var i = 0; i < data.length - 1; i++) {
																susval
																		.push(dec(
																				enc,
																				data[i].columnCode)
																				+ "::"
																				+ dec(
																						enc,
																						data[i].columnName));
															}
															var dataCountry1 = susval
																	.join("|");
															myResponse = [];
															myResponse1 = [];
															var autoTextVal = wepetext
																	.val();
															$
																	.each(
																			dataCountry1
																					.toString()
																					.split(
																							"|"),
																			function(
																					i,
																					e) {
																				var newE = e
																						.split("::")[1]
																						.substring(
																								0,
																								autoTextVal.length);
																				if (e
																						.split("::")[1]
																						.toLowerCase()
																						.includes(
																								autoTextVal
																										.toLowerCase())) {
																					myResponse
																							.push(e
																									.split("::")[1]);
																					myResponse1
																							.push(e
																									.split("::")[0]);
																				}
																			});
															response(myResponse);
														}
													}
												});
									},
									minLength : 1,
									autoFill : true,
								});

						try {
							if (window.location.href.includes("msg=")) {
								var url = window.location.href.split("?msg")[0];
								var m = window.location.href.split("?msg")[1];
								window.location = url;
								if (m.includes("Updated+Successfully")) {
									window.opener.getRefreshReport(
											'nationality', m);
									window.close('', '_parent', '');
								}
							}
							if (document.getElementById("msg").value == "Data Already Exists!") {
							} else if (document.getElementById("msg").value != "") {
								document.getElementById("printId").disabled = true;
								$("div#divwatermark").val('').removeClass(
										'watermarked');
								$("div#divSerachInput").hide();
								$("div#divPrint").hide();
							} else {
								document.getElementById("printId").disabled = false;
							}

						} catch (e) {
							// TODO: handle exception
						}

						if ('${list.size()}' == 0) {
							$("div#divSerachInput").hide();
							document.getElementById("printId").disabled = true;
							$("#nationality")
									.append(
											"<tr><td colspan='3' style='text-align :center;'>Data Not Available</td></tr>");
						}

						$("div#divwatermark").val('').addClass('watermarked');
						watermarkreport();
						$("#searchInput").val("");
						$("#searchInput")
								.on(
										"keyup",
										function() {
											var value = $(this).val()
													.toLowerCase();
											$("#nationality tbody tr")
													.filter(
															function() {
																$(this)
																		.toggle(
																				$(
																						this)
																						.text()
																						.toLowerCase()
																						.indexOf(
																								value) > -1)
															});
										});

					});
	function printDiv() {
		var printLbl = [];
		var printVal = [];
		printDivOptimize('divPrint', 'Nationality', printLbl, printVal,
				"divwatermark");
	}
	function getRefreshReport(page, msg) {
		if (msg.includes("Updated+Successfully")) {
			if (page == "nationality") {
				closeWindow();
				$("body").removeClass("body_back");
				document.getElementById('mainForm').submit();
			}
		}
	}
	function clearall() {
		document.getElementById('mainForm').submit();
	}
</script> 

 <script>

		function editData(id) {

			document.getElementById('updateid').value = id;
			document.getElementById('updateForm').submit();
		}
		function closeWindow() {
			newWin.close();
		}

		function deleteData(id) {

			document.getElementById('deleteid').value = id;
			document.getElementById('deleteForm').submit();
		}
	</script>  
