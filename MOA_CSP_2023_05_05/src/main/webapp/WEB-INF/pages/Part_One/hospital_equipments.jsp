<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">

	<section class="dashboard-page">
		<div class="container-fluid">
			<!-- title-wrapper start -->
			<div class="title-wrapper pt-30">
				<div class="row align-items-center">
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="title mb-30">
							<h2>Hospital Equipments</h2>
						</div>
					</div>
					<!-- end col -->
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="breadcrumb-wrapper mb-30">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
									<li class="breadcrumb-item active" aria-current="page">
										Hospital Equipments</li>
								</ol>
							</nav>
						</div>
					</div>
					<!-- end col -->
				</div>
				<!-- end row -->
			</div>
			<!-- title-wrapper end -->
			<div class="form-elements-wrapper custom_v_tab">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card-style mb-30">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="inst_block simple-instruction">
										<strong>Instruction :</strong> If any of the value is not
										available or not applicable then please put it as 0
									</div>
								</div>
									<div class="col-lg-6 col-md-6 col-sm-12 col-12">
									<div class="custom-data-value custom-data-value-form">
										<label>Institution Name :</label> <span class="value-bind">${roleloginName}</span>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 col-12">
									<div class="custom-data-value custom-data-value-form">
										<label>Institution State :</label> <span class="value-bind">${username}</span>
									</div>

								</div>

							</div>
						</div>
					</div>
					<!-- ===========================
							Hospital Equipments Start
						=========================== -->
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card-style mb-30">
							<div class="tabs content_h750">
								<!-- Instruments and Equipments Start -->
								<div class="tab">
									<button class="tab-toggle" id="defaultOpen">Instruments & Equipments</button>
								</div>
								<div class="content">
									<h4 class="heading">Instruments & Equipments</h4>
									<form:form name="hospital_equipment" id="hospital_equipment" action="hospital_equipments" method="post" class=""
										commandName="HECMD">
										<div class="row">
										
										<input type="hidden" id="basic_info_id" name="basic_info_id" value="0">
										
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="comp_print_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>Articles</h6></th>
																<th><h6>Total Equipments<strong class="mandatory">*</strong>
																	</h6></th>
															</tr>
														
														</thead>
														<tbody id="">
													    	<c:forEach var="j" items="${getarticaldata}"
 														     	varStatus="num"> 
															
																<tr>
																<td>
																<p>${num.index+1}</p>
																<input type="hidden" id="hosp_art_hid${num.index+1}"
																		name="hosp_art_hid${num.index+1}" value="0"
																		class="form-control ">
																</td>
																<td>
																	<p>${j[1]}</p>
																	<input type="hidden" id="articles${num.index+1}"
																		name="articles${num.index+1}" value="${j[0]}"
																		class="form-control">
																	
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" maxlength="9" name="total_equipments${j[0]}"
																			id="total_equipments${j[0]}" value = "0" class="form-control"
																			placeholder="Total Equipments">
																	</div>
																</td>
															
																</tr>
																
																</c:forEach>
															
														
															<!-- end table row -->
														</tbody>
													</table>
												</div>
												<!-- end row -->
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-lg-6 col-md-6 col-sm-12">
											<input type="hidden" id="hid_upload_document" name="hid_upload_document" value="0">
												<div class="input-style-1">
													<label for="fname">If Other Hospital/Operation Theater/Labour/IPD equipment are available.
													</label><strong class="mandatory">*</strong> <input type="file" id="sother_equip"
														name="sother_equip" class="form-control">
														<input type="hidden" id="hid_sother_equip" name="hid_sother_equip"
															class="form-control" value="">
												</div>
											</div>
											<div class="col-lg-6 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Upload list of equipments in Pathology laboratory & Biochemistry laboratory.
													</label><strong class="mandatory">*</strong> <input type="file" id="patho_bio_equip"
														name="patho_bio_equip" class="form-control">
														<input type="hidden" id="hid_patho_bio_equip" name="hid_patho_bio_equip"
															class="form-control" value="">
												</div>
											</div>
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input
															class="main-btn success-btn btn-hover btnsubmit" type="button" id= "hosp_Ev"
															value="Submit"></li>
															<li><a 
																class="main-btn dark-btn-outline btn-hover btn-iconic-icon viewData d-none"><i
																	class="lni lni-eye"></i>View</a>
																	<input type='hidden' id="viewId${parent_id}" value="${institude}"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- Instruments and Equipments End -->
							</div>
						</div>
					</div>
					<!-- ===========================
							Hospital Equipments End
						=========================== -->
				</div>
			</div>
			<!-- end row -->
		</div>
		<!-- end container -->
	</section>
	<!-- regulation components end -->
<c:url value="View_Hospital_Equipment_ReportUrl" var="viewUrl" />
	<form:form action="${viewUrl}" method="post" id="viewForm"
		name="viewForm" modelAttribute="hos_eqp_view_id">
		<input type="hidden" name="hos_eqp_id" id="hos_eqp_view_id" value="0" />
		<input type="hidden" name="statusview" id="statusview" value="0" />
	</form:form>
	<script type="text/javascript"
		src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>
		
	<script type="text/javascript" nonce="${cspNonce}">
	
	$(document).ready(function() {
		
		
		var depdata = '${getarticaldata}';
		
		$.ajaxSetup({
			async : false
		});
		if ('${role}'=='NCH') {
			$(".viewData").addClass("d-none")
		}
		if ('${role}'=='Institute_NCH') {
			$(".viewData").removeClass("d-none")
		} 
		
		$("#basic_info_id").val('${basic_info_id}');
		
		
		
	//-------------Start----------------Data Fetch For----------------
		var dataparent ='${dataeqi}';
// 		alert(dataparent);
			
		<c:forEach var="j" items="${dataeqi}"
				varStatus="num">
			var index = '${num.index+1}';
			$("#hosp_art_hid"+index).val('${j.id}');
			$("#articles"+index).val('${j.articles}');
			$("#total_equipments"+index).val('${j.total_equipments}');
		</c:forEach>
			
	// //--------------EnD------------------Data Fetch For ----------------		

			
		
	 	getUpload_Document_Details();
	
	});
	
	
	
	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('hosp_Ev').onclick = function() {
			save_Hospital_Equipmente_data();
		};
		<c:forEach var="j" items="${getarticaldata}" varStatus="num">
		
		document.getElementById('total_equipments'+${j[0]}).onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		
		</c:forEach>
	
	});
	
	function save_Hospital_Equipmente_data() {

		$.ajaxSetup({
		    async: false
		});	
		
		
		var basic_info_id = $("#basic_info_id").val();
		if(basic_info_id == "0"){
			alert("Please Save Basic details First");
			return false;
		}
		
		 <c:forEach var="j" items="${getarticaldata}" varStatus="num">
			
			var res = CheckNullorBlankZero('total_equipments'+${j[0]});
		 	if (res !== "true") {
		 		alert(res +"Total Equipments ${j[1]}");
		 		$('#total_equipments'+${j[0]}).focus();
		 		return false;
		 	}
		 	</c:forEach>
		 	if ($("#sother_equip").val().trim() == "") {
				alert("Please Upload If Other Hospital/Operation Theater/Labour/IPD equipment are available");
				$("input#sother_equip").focus();
				return false;
			}
			if ($("#patho_bio_equip").val().trim() == "") {
				alert("Please Upload Upload list of equipments in Pathology laboratory & Biochemistry laboratory");
				$("input#patho_bio_equip").focus();
				return false;
			}
		var form_data = new FormData(document.getElementById("hospital_equipment"));
		
			$.ajax({
		        url: 'Hospital_Equipment_Action?' + key + "=" + value,
		        type: "POST",
		        data: form_data,
		        enctype: 'multipart/form-data',
		        processData: false,
		        contentType: false
		      }).done(function(j) {
		    	  
		    	  if(j>0){
		    		  $("#hid_upload_document").val(j);
		    		  alert("Data Saved Successfully"); 
		    		  location.reload();
		    	  }
		    	  else{
		    		  alert(j);  
		    	  }
		   	
				}).fail(function(xhr, textStatus, errorThrown) {alert("Something Went Wrong")
		         
		      }).fail(function(jqXHR, textStatus) {

		      });

	}
	
	function getUpload_Document_Details() {
		$.ajaxSetup({
			async : false
		});
		
		$.post("getHosp_Equipment_Document_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			$("#hid_upload_document").val(j[0].id);
			$("#hid_sother_equip").val(j[0].sother_equip);
			$("#hid_patho_bio_equip").val(j[0].patho_bio_equip);
			
		});
	}
	
	document.querySelectorAll('.viewData').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var hid = document.getElementById('viewId${parent_id}').value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				ViewData(hid);
			} else {
				return false;
			}
		})
	});

	function ViewData(id){

		$("#hos_eqp_view_id").val(id);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
	}
	
		
</script>		



