<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd"></span>Scholarship Wise Student Fees
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Scholarship Wise Student Fees</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<form:form name="ug_pg_Feecollect_form" id="ug_pg_Feecollect_form"
						action="ug_pg_retcollect_Action1" method="post"
						modelAttribute="ug_pg_Feecollect_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<div class="row">
<!-- 								<h5 class="text-medium text-medium mb-10 mt-20">Mandatory &amp; -->
<!-- 								Important Instruction</h5> -->
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="inst_block simple-instruction">
										<strong>Instruction :</strong> If There Is No Scholarship <span
											class="concat-string"> <b>Save Is Mandatory</b>
										</span>
									</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="degree_name" id="degree_name">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>Professional<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="prof_id" id="prof_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${gettermList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
											<!-- Hidden Start -->
											<input type="hidden" id="id" name="id" value="0" class="mt-3"
												autocomplete="off" /> <input type="hidden"
												id="count_hidden_att" name="count_hidden_att"
												class="form-control autocomplete" value="1">
													<!-- Hidden End -->
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- Hidden Start -->
						<input type="hidden" id="institute_id" name="institute_id"
							value="">
						<input type="hidden" id="no_of_record_hid" name="no_of_record_hid"
							value="${list.size()}">
						<!-- Hidden End -->
						<section class="single-detail-block">
							<div class="row">
								<div class="col-12 col-sm-12 col-md-12 col-lg-12">
									<div class="card-style mb-30">
										<div class="table-wrapper table-responsive custom-table"
											id="div_table">
											<table class="table" id="tb_stulist">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th><h6>Student Name</h6></th>
														<th><h6>Ayush Id</h6></th>
														<th><h6>Date Of Birth</h6></th>
														<th><h6>Mobile Number</h6></th>
														<th><h6>Caste Of Category</h6></th>
														<th id="th_tb_col_tf_lbl"><h6>Total Fees Amount</h6></th>
														<th id="th_tb_col_tf_inp"><h6>Total Fees Amount</h6></th>
														<th id="th_tb_col_view"><h6>Save/Update</h6></th>

													</tr>
												</thead>
												<tbody>
													<tr>
													</tr>

													<c:if test="${list.size() == 0}">
														<tr>
															<td colspan="7"><p class="no-data">Data Not
																	Available</p></td>
														</tr>
													</c:if>
													<c:if test="${list.size() > 0}">
														<c:forEach var="item" items="${list}" varStatus="num">
															<tr>
																<td><p>${num.index+1}</p></td>
																<td><p>${item.name}</p></td>
																<td><p>${item.ayush_id}</p></td>
																<td><p>${item.dob}</p></td>
																<td><p>${item.mobile_no}</p></td>
																<td><p>${item.category}</p></td>
																<td id="td_tb_col_tf_lbl${num.index+1}"><p>${item.feesvalue}</p></td>
																<td id="td_tb_col_tf_inp${num.index+1}"
																	class="td_tb_col_tf_inp">
																	<div class="input-style-1">
																		<input type="text" id="total_fees${num.index+1}"
																			name="total_fees${num.index+1}"
																			value="${item.feesvalue}">
																	</div> <!-- Hidden Start --> <input type="hidden"
																	id="no_of_part_hid${num.index+1}"
																	name="no_of_part_hid${num.index+1}"
																	value="${item.no_of_part}"> <input
																	type="hidden" id="system_hid${num.index+1}"
																	name="system_hid${num.index+1}" value="${item.system}">
																	<input type="hidden" id="stu_hid${num.index+1}"
																	name="stu_hid${num.index+1}" value="${item.id}">
																	<input type="hidden" id="degree_hid${num.index+1}"
																	name="degree_hid${num.index+1}" value="${item.degree}">
																	<input type="hidden" id="prof_hid${num.index+1}"
																	name="prof_hid${num.index+1}" value="${item.semester}">
																	<input type="hidden" id="ins_hid${num.index+1}"
																	name="ins_hid${num.index+1}"
																	value="${item.institude_id}"> <!-- Hidden End -->
																</td>
																<td id="td_tb_col_view${num.index+1}">
																	${item.action}</td>
															</tr>
														</c:forEach>
													</c:if>
												</tbody>
											</table>
										</div>
										<!-- Bottom Button Start -->   
										<div class="btn-bottom">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12">
													<ul class="buttons-group mainbtn">
														<li><a id="btn-reload"
															class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
															type="button"><i class="lni lni-search-alt"></i>Search</a></li>
														<li><input id="btn-save"
															class="main-btn success-btn btn-hover" type="submit"
															value="Save" /></li>
														<li><a href="set_student_fees_amount_Url"
															class="main-btn dark-btn btn-hover btnreset"
															type="button">Reset</a></li>
													</ul>
												</div>
											</div>
										</div>
										<!-- Bottom Button End -->   
									</div>
								</div>
							</div>
						</section>
					</form:form>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade custom-modal bd-example-modal-lg" tabindex="-1"
		role="dialog" aria-labelledby="myLargeModalLabel" id="modelWindow"
		aria-hidden="true">
		<div
			class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title">Update Fee</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body custom-modal-table">
					<div class="custom-modal-inner">
						<div class="table-wrapper table-responsive custom-table">
							<table class="table model-table" id="getStu_ChildUrl">
								<thead>
									<tr>
										<th>Sr No</th>
										<th>Part</th>
										<th>Amount</th>
										<th>Update</th>
									</tr>
								</thead>
								<tbody id="att_TbbodyNameMed">
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<ul class="buttons-group">
						<li><a type="button" class="main-btn dark-btn n btn-hover"
							data-bs-dismiss="modal">Close</a></li>
					</ul>
				</div>

			</div>
		</div>
	</div>
	<!-- Hidden Start -->
	<c:url value="Search_set_student_fees_amount_Url" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm"
		name="searchForm" modelAttribute="degree_name1">
		<input type="hidden" name="degree_name1" id="degree_name1" value="0" />
		<input type="hidden" name="prof_id1" id="prof_id1" value="0" />
		<input type="hidden" name="url1" id="url1" value="0" />
	</form:form>
	<!-- Hidden End -->
</section>



<script nonce="${cspNonce}" type="text/javascript">
	
	$(document).ready(function() {
		
		$("#th_tb_col_tf_lbl").hide();
		$("#th_tb_col_tf_inp").hide();
		$("#th_tb_col_view").hide();
		
		if('${list.size()}' != 0){
			
			<c:forEach var="item" items="${list}" varStatus="num">
				$("#td_tb_col_tf_lbl"+${num.index+1}).hide(); 
				$("#td_tb_col_view"+${num.index+1}).hide(); 
				$("#td_tb_col_tf_inp"+${num.index+1}).hide();
				
				
				
				if('${item.isc}' == 0){
					Savebtnonclick(${num.index+1});
					addOnclick1(${num.index+1});
				}else{
					setViewbtnonclick(${num.index+1});
				}
				
			</c:forEach>
		}
		
		getDegreeListFromInstitute();
		
		if('${degree_name1}' != "" && '${prof_id1}' != "" && '${institute_id}' != ""){
			
			$("#degree_name").val('${degree_name1}');
			$("#prof_id").val('${prof_id1}');
			$('#prof_id').trigger('change');
			$("#institute_id").val('${institute_id}');
			$("#url1").val('${url1}');
			isSaved('${institute_id}','${degree_name1}','${prof_id1}');
		}
	});

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('btn-reload').onclick = function() {
			var deg=$("#degree_name").val();
			var pro = $("#prof_id").val();
			if(deg!="0" && pro!="0") {
			 Search();
			}
		};
	});
	function addOnclick(att){
		document.getElementById('amount'+att).onkeypress = function () {
			return isNumberKey0to9(event, this);
		 };
		 document.getElementById('updtId'+att).onclick = function () {
// 			return validation1(att);
			if (validation1(att)) {
				setUpdatebtnonclick(att);
			}
		};
	}
	function addOnclick1(att){
		document.getElementById('total_fees'+att).onkeypress = function () {
				return isNumberKey0to9(event, this);
			 };
// 		document.getElementById('saveId'+att).onclick = function () {
// //  			return validation1(att);
// 			if (validation(att)) {
// 				Savebtnonclick(att);
// 			}
// 		};
		
	}
	function addOnclicksave(att){
		document.getElementById('save-btn').onclick = function () {
//			return validation1(att);
		if (validation(att)) {
			$("#btn-save").submit();
		}
	};
	}
	function validation(ps){
		
		var amount = $('#total_fees' + ps).val();
		
		var res = CheckNullorBlank('total_fees'+ps);
		if (res !== "true") {
			alert(res + "Total Fees Amount");
			$('#total_fees'+ps).focus();
// 			return false;
		}
		return true;
	}
	
	function validation1(ps){
		
		var amount = $('#amount' + ps).val();
		
		var res = CheckNullorBlank('amount'+ps);
		if (res !== "true") {
			alert(res + "Amount");
			$('#amount'+ps).focus();
// 			return false;
		}
		return true;
	}
	
	function Search(){
		$("#degree_name1").val($("#degree_name").val());
		$("#prof_id1").val($("#prof_id").val());
		$("#url1").val(window.location.href);
		document.getElementById('searchForm').submit();
	}
	
	function isSaved(inst_id,degree,prof){
		$.post('checkisdatasaved?' + key + "=" + value, {
			institute_id : inst_id,degree : degree,prof : prof
		}).done(function(j) {
// 			debugger;
			if(j > 0){
				$("#th_tb_col_view").show();
				$("#th_tb_col_tf_lbl").show();
// 				var list[] = '${list}';
				for(var i=1;i<='${list.size()}';i++){
					$("#td_tb_col_view"+i).show();
					$("#td_tb_col_tf_lbl"+i).show();
// 					setViewbtnonclick(i);
				}
				$("#btn-save").hide();
			}else{
				$("#th_tb_col_tf_inp").show();
				document.querySelectorAll('.td_tb_col_tf_inp').forEach((items, index) => {
						var val=parseInt(index)+1;
						$("#td_tb_col_tf_inp"+val).show();
				});
			}
		});
	} 
	//csp
	function setViewbtnonclick(ser){
// 		alert(ser);
		document.getElementById('viewId'+ser).onclick = function() {
//  			  updatedata(ser);
			Pop_Up_view_child_data(ser);

 		};
	}
	
	function setUpdatebtnonclick(ser){
		document.getElementById('updtId'+ser).onclick = function() {
			updaterowsdata(ser);
 		};
	}
	
	function Savebtnonclick(ser){
// 		alert(ser);
		document.getElementById('saveId'+ser).onclick = function() {
			saverowsdata(ser);
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
	
	function getDegreeListFromInstitute() {
		
		$.ajaxSetup({
			async: false
		});
		$.post("getDegreeListFromInstituteExam?" + key + "=" + value,
			{ },function(j) {
							
				var options = '<option value="' + "0" + '">'
						+ "--Select--" + '</option>';
				for (var i = 0; i < j.length; i++) {

					options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
							+ j[i][1] + '</option>';
				}
				$("select#degree_name").html(options);
				
			});
	}
	
// 	function setTimeLoadForTable(){
// 		document.querySelectorAll('.UPDTdetails').forEach((items, index) => {
// 			items.addEventListener('click', event => {
// 				var val=parseInt(index)+1;
// 				var editid = document.getElementById('updtId'+val).value;
// 				alert(editid)
				
// 				if (confirm('Are You Sure You Want to Edit Detail ?')) {
// 					updatedata(editid);
// 				} else {
// 					return false;
// 				}
// 			})
// 		});
// 	}
	
	function Pop_Up_view_child_data(ser) {
// 		var vd=j[i].vd;
		var studentid = $("#viewhidId"+ser).val();
		var checkES = $("#chkES"+ser).val();
		
		if(checkES == "1"){
			$('tbody#att_TbbodyNameMed').empty();
			$
					.post( 
							"getStuFees_ChildUrl?" + key
									+ "=" + value,
							{
								studentid : studentid
							},
							function(j) {
								if(j.length == 0){
									$("tbody#att_TbbodyNameMed")
									.append(
										'<tr><td colspan="4">DATA NOT AVAILABLE</td></tr>');
								}
								if(j.length > 0){
									for (var i = 0; i < j.length; i++) {
										var ser = parseInt(i) + 1;
										var ud=j[i].ud;
										$("tbody#att_TbbodyNameMed")
											.append(
												'<tr id="tr_id_attNameMed'+ser+'"><td class="min-width"><p>'
														+ ser
														+ '</p></td>'
														+ '<td class="min-width"><p>'
														+ j[i].part_ser
														+ '</p></td>'
														+ '<td class="min-width">'
														+ '<div class="input-style-1">'
														+'<input type="text" id="amount'+ser+'" name="amount'+ser+'" value="'
														+ j[i].amount+'"/>'
														+ '</div>'
														+ '</td>'
// 														+'<input type="hidden" id="stu_rowhid'+ser+'" name="stu_rowhid'+ser+'" value="'
// 														+ j[i].id+'"/>'
														+'<td class="min-width">'
														+'<p id="updtbtn'+ser+'">'
														+ ud
														+'</p>'
														+'</td>'
														+ '</tr>');
										setUpdatebtnonclick(parseInt(i+1));
										addOnclick(ser);
									}
								}
								
							});
			
			  $('#modelWindow').modal('show');
		}
		
		
		if(checkES == "2"){
			
			var no_of_part = $("#no_of_part_hid"+ser).val();
			var total_fees = $("#total_fees"+ser).val(); 
			$('tbody#att_TbbodyNameMed').empty();
			for (var i = 0; i < no_of_part; i++) {
				$("tbody#att_TbbodyNameMed").append('<tr id="tr_id_attNameMed'+ser+'"><td class="min-width"><p>'
							+ ser
							+ '</p></td>'
							+ '<td class="min-width"><p>'
							+ (i+1)
							+ '</p></td>'
							+ '<td class="min-width">'
							+ '<div class="input-style-1">'
							+'<input type="text" id="amount'+ser+'" name="amount'+ser+'" value="'
							+ (total_fees/no_of_part)+'"/>'
							+ '</div>'
							+ '</td>'
// 							+'<td class="min-width">'
// 							+'<label id="updtbtn'+ser+'">'
// 							+ ud
// 							+'</label>'
// 							+'</td>'
							+'<ul class="buttons-group mainbtn action">'
							+'<li><span class="status-btn active-btn" id="updtId'+ (i+1) +'">Save</span>'
							+'<input type="hidden" id="updthidId' + (i+1) + '" value="'+$("#stu_hid"+ser).val()+'"></li></ul>'
							+ '</tr>');
				
				setUpdatebtnonclick(parseInt(i+1));
				addOnclick(att);
				
			}
			
			$('#modelWindow').modal('show');
			
		}
		
			 
	}
	
function updaterowsdata(ser){
		
		var amount = $("#amount"+ser).val();
		var sturowid = $("#updthidId"+ser).val();
		
		$.post('updaterowfeesdata?' + key + "=" + value, {
			id : sturowid,amount : amount
		}).done(function(j) {
			alert(j);
			window.location = $("#url1").val();
		});
	}
	
function saverowsdata(ser){
	
	var system = $("#system_hid"+ser).val();
	var degree = $("#degree_hid"+ser).val();
	var professional = $("#prof_hid"+ser).val();
	var ins_id = $("#institute_id").val();
	var amount = $("#total_fees"+ser).val();
	var sturowid = $("#savehidId"+ser).val();
	var no_of_part = $("#no_of_part_hid"+ser).val();
	
	$.post('saverowfeesdata?' + key + "=" + value, {
		id : sturowid,
		system_hid:system,
		degree_hid:degree,
		total_fees : amount,
		no_of_part_hid:no_of_part,
		prof_hid:professional,
		institute_id:ins_id
	}).done(function(j) {
		alert(j);
		window.location = $("#url1").val();
	});
}


</Script>

