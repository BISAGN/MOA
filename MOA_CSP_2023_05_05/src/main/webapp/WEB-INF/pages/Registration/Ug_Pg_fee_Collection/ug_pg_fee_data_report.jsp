<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
<!-- 						<span id="lbladd"></span> -->
						<h2>Fees Data Report</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Fees Data Report</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<!-- input style start -->
					<form:form name="ug_pg_Fees_Data_form" id="ug_pg_Fees_Data_form"
						action="Fees_Data_Action1" method="post"
						class="form-horizontal" modelAttribute="ug_pg_FeesData_CMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Fees Data Report</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="select-style-1">
										<label for="text-input">System<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="system_id" id="system_id" class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
<%-- 												<c:forEach var="item" items="${getSystemList}" varStatus="num"> --%>
<%-- 													<option value="${item.id}" name="${item.system_name}">${item.system_name}</option> --%>
<%-- 												</c:forEach> --%>
											</select>
										</div>
									</div>
									<!-- end select -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="select-style-1">
										<label for="username">Degree<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="degree_name" id="degree_name">
												<option value="0">--Select--</option>
												<%-- <c:forEach var="item" items="${d_name}" varStatus="num">
													<option value="${item.id}" name="${item.degree_name}">${item.degree_name}</option>
												</c:forEach> --%>
											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="select-style-1">
										<label for="username">professional<span
											class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="prof_id" id="prof_id">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${gettermList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
											<input type="hidden" id="id" name="id" value="0" class="mt-3"
												autocomplete="off" /> <input type="hidden"
												id="count_hidden_att" name="count_hidden_att"
												class="form-control autocomplete" value="1">
									</div>
								</div>
								<ul class="buttons-group mainbtn">
									<li><a id="btn-reload"
										class="main-btn secondary-btn btn-hover btn-iconic-icon"
										type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								</ul>
							</div>
						</div>
						<input type="hidden" id="institute_id" name="institute_id"
							value="">
						<input type="hidden" id="no_of_record_hid" name="no_of_record_hid"
							value="${list.size()}">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-table"
								id="div_table">
								<table class="table" id="tb_stulist">
									<thead>
										<tr>
											<th><h6>Ser No</h6></th>
											<th><h6>Student Name</h6></th>
											<th id="th_tb_col_tf_lbl"><h6>Total
													Fees Amount</h6></th>
											<th id="th_tb_col_pf_lbl"><h6>Paid
													Fees Amount</h6></th>
										</tr>
									</thead>
									<tbody>
										<tr>
										</tr>
										<c:if test="${list.size() == 0}">
											<tr>
												<td colspan="7">Data Not Available</td>
											</tr>
										</c:if>
										<c:if test="${list.size() > 0}">
											<c:forEach var="item" items="${list}" varStatus="num">
												<tr>
													<td>${num.index+1}</td>
													<td>${item.name}</td>
													<td>${item.total_fees}</td>
													<td>${item.paid_fees}</td>
													
												
<!-- 													 <td> -->
<%-- 														${item.action} --%>
<!-- 													</td>  -->


													<%-- <td>
													<input type="hidden" id="name_hid${num.index+1}" name="name_hid${num.index+1}" value="${item.name}">
													<input type="hidden" id="ayu_hid${num.index+1}" name="ayu_hid${num.index+1}" value="${item.ayush_id}">
													<input type="hidden" id="dob_hid${num.index+1}" name="dob_hid${num.index+1}" value="${item.dob}">
													<input type="hidden" id="mo_hid${num.index+1}" name="mo_hid${num.index+1}" value="${item.mobile_no}">
													<input type="hidden" id="cat_hid${num.index+1}" name="cat_hid${num.index+1}" value="${item.category}">
													<input type="hidden" id="degree_hid${num.index+1}" name="degree_hid${num.index+1}" value="${item.degree}">
													<input type="hidden" id="prof_hid${num.index+1}" name="prof_hid${num.index+1}" value="${item.semester}">
													
													
													<input type="text" id="total_fees${num.index+1}" name="total_fees${num.index+1}" value="${item.feesvalue}" />
												</td> --%>

													<%-- 												<td id="td_tb_col_updt${num.index+1}" style="display:none"> --%>
													<%-- 													<a class="main-btn dark-btn-outline btn-hover btn-sm" id="updt_btn${num.index+1}"><i class="lni lni-book"></i></a> --%>
													<!-- 												</td> -->
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
							</div>
							<ul class="buttons-group mainbtn">
								
<!-- 								<li><input id="btn-save" -->
<!-- 									class="main-btn info-btn btn-hover" type="submit" value="Save" /></li> -->
								<li><a href="Fees_Data_Report_Url"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
								<li><a
									class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon"
									id="pdfex"><i class="lni lni-printer" id="printId"
										value="PDF" title="Export to PDF"></i> PDF </a></li>

								<li><a
									class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon"
									id="btnExport"><i class="lni lni-printer" value="PDF"
										title="Export to PDF"></i> EXCEL </a></li>
							</ul>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- <div class="modal fade custom-modal bd-example-modal-lg" tabindex="-1" -->
<!-- 	role="dialog" aria-labelledby="myLargeModalLabel" id="modelWindow" -->
<!-- 	aria-hidden="true"> -->
<!-- 	<div class="modal-dialog modal-xl modal-dialog-scrollable"> -->
<!-- 		<div class="modal-content"> -->
<!-- 			<div class="modal-body custom-modal-table"> -->
<!-- 				<div class="custom-modal-inner"> -->
<!-- 					<div class="table-wrapper table-responsive custom-table"> -->
<!-- 						<table class="table model-table" id="getStu_ChildUrl"> -->
<!-- 							<thead> -->
<!-- 								<tr> -->
<!-- 									<th>Ser No</th> -->
<!-- 									<th>Part</th> -->
<!-- 									<th>Amount</th> -->
<!-- 									<th>Update</th> -->
<!-- 								</tr> -->
<!-- 							</thead> -->
<!-- 							<tbody id="att_TbbodyNameMed"> -->
<!-- 							</tbody> -->
<!-- 						</table> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="modal-footer"> -->
<!-- 					<ul class="buttons-group"> -->
<!-- 						<li><button type="button" -->
<!-- 								class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal" -->
<!-- 								data-dismiss="modal" aria-label="Close">Close</button></li> -->
<!-- 					</ul> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->

<c:url value="Search_fees_data_Report_Url" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="degree_name1">
	<input type="hidden" name="system_id1" id="system_id1" value="0" />
	<input type="hidden" name="degree_name1" id="degree_name1" value="0" />
	<input type="hidden" name="prof_id1" id="prof_id1" value="0" />
	<input type="hidden" name="url1" id="url1" value="0" />
</form:form>
<c:url value="getFees_Data_Report_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="name1" id="name1" value="0" />
	<input type="hidden" name="total_fees1" id="total_fees1" value="0" />
	<input type="hidden" name="paid_fees1" id="paid_fees1" value="0" />
	<input type="hidden" name="system_id2" id="system_id2" value="0" />
	<input type="hidden" name="degree_name2" id="degree_name2" value="0" />
	<input type="hidden" name="prof_id2" id="prof_id2" value="0" />
	
</form:form>
<c:url value="Fees_Data_Report_PDF" var="searchUrl1" />
<form:form action="${searchUrl1}" method="post" id="search3"
	name="search3" modelAttribute="comd1">
	<input type="hidden" name="name2" id="name2" value="0" />
	<input type="hidden" name="total_fees2" id="total_fees1" value="0" />
	<input type="hidden" name="paid_fees2" id="paid_fees2" value="0" />
	<input type="hidden" name="system_id3" id="system_id3" value="0" />
	<input type="hidden" name="degree_name3" id="degree_name3" value="0" />
	<input type="hidden" name="prof_id3" id="prof_id3" value="0" />
	
</form:form>
<script nonce="${cspNonce}" type="text/javascript">
	
	$(document).ready(function() {
// 		alert(${list[0]})
// 		$("#th_tb_col_tf_lbl").hide();
// 		$("#th_tb_col_tf_inp").hide();
		$("#th_tb_col_view").show();
		
		if('${list.size()}' != 0){
			<c:forEach var="item" items="${list}" varStatus="num">
				$("#td_tb_col_tf_lbl"+${num.index+1}).hide(); 
// 				$("#td_tb_col_view"+${num.index+1}).hide(); 
				$("#td_tb_col_tf_inp"+${num.index+1}).hide(); 
			</c:forEach>
		}
		getSystemListFromInstitute();
		
		if('${system_id1}' != "" && '${institute_id}' != ""){
			$("#system_id").val('${system_id1}');
			getDegreeListbysystem111();
			$("#degree_name").val('${degree_name1}');
			$("#prof_id").val('${prof_id1}');
			$('#prof_id').trigger('change');
			$("#institute_id").val('${institute_id}');
			$("#url1").val('${url1}');
		}
	});

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('btn-reload').onclick = function() {
			var sys =$("#system_id").val();
			var deg =$("#degree_name").val();
			var prof =$("#prof_id").val();
			if(ValidationSearch()){
				if(sys!="0" && deg!="0" && prof !="0") {
			 		Search();
				}
			}
		};
		document.getElementById('system_id').onchange = function() {
			getDegreeListbysystem111();
		};
		document.getElementById('btnExport').onclick = function() {
			if(ValidationSearch()){
				getCollaboration_category_Report_Excel();
			}
		};
		document.getElementById('pdfex').onclick = function() {
			if(ValidationSearch()){
				Collaboration_category_Report_PDF();
			}
		};
	});
	
	function Search(){
		
		$("#system_id1").val($("#system_id").val());
		$("#degree_name1").val($("#degree_name").val());
		$("#prof_id1").val($("#prof_id").val());
		$("#url1").val(window.location.href);
		document.getElementById('searchForm').submit();
	}
	
	function setUpdatebtnonclick(ser){
		document.getElementById('updtId'+ser).onclick = function() {
			updaterowsdata(ser);
 		};
	}
	
	function updatedata(ser){
		
		var total_fees = $("#total_fees"+ser).val();
// 		var stuid = $("#viewhidId"+ser).val();
		var stuid = $("#stu_hid"+ser).val();
		var part = $("#no_of_part_hid"+ser).val();
		
		$.post('updatefeesdata?' + key + "=" + value, {
			stuid : stuid,total_fees : total_fees,part : part
		}).done(function(j) {
			alert(j);
			window.location = $("#url1").val();
		});
	}

	function getSystemListFromInstitute() {
		
		$.ajaxSetup({
			async: false
		});
		$.post("getSystemListFromInstituteExam1?" + key + "=" + value,
			{ },function(j) {
							
				var options = '<option value="' + "0" + '">'
						+ "--Select--" + '</option>';
				for (var i = 0; i < j.length; i++) {

					options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
							+ j[i][1] + '</option>';
				}
				$("select#system_id").html(options);
				
			});
	}
	function getDegreeListbysystem111() {
		var system_name = $("#system_id").val();
		$
				.post('getDegreeListbysystem111?' + key + "=" + value, {
					system_name : system_name
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#degree_name").html(options);
						});
	}
	
	function getCollaboration_category_Report_Excel() {
		$("#name1").val($("#name").val());
		$("#total_fees1").val($("#total_fees").val());
		$("#paid_fees1").val($("#paid_fees").val());
		$("#system_id2").val($("#system_id").val());
		$("#degree_name2").val($("#degree_name").val());
		$("#prof_id2").val($("#prof_id").val());
		document.getElementById('search2').submit();
	}
	function Collaboration_category_Report_PDF() {
		$("#name1").val($("#name").val());
		$("#total_fees1").val($("#total_fees").val());
		$("#paid_fees1").val($("#paid_fees").val());
		$("#system_id3").val($("#system_id").val());
		$("#degree_name3").val($("#degree_name").val());
		$("#prof_id3").val($("#prof_id").val());
		document.getElementById('search3').submit();
	}
	
	function ValidationSearch(){
		if ($("#system_id").val().trim() == "0") {
			alert("Please Select System.");
			$("select#system_id").focus();
			return false;
		}
		if ($("#degree_name").val().trim() == "0") {
			alert("Please Select Degree.");
			$("select#degree_name").focus();
			return false;
		}
		if ($("#prof_id").val().trim() == "0") {
			alert("Please Select professional.");
			$("select#prof_id").focus();
			return false;
		}
		return true;
	}

</Script>

