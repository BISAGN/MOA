<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!-- single select, search with select start -->
<link href="admin/js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="admin/js/dropDown/select2.min.js" type="text/javascript"></script>
<!-- single select, search with select end -->

<!-- common style start -->
<link rel="stylesheet" href="admin/assets/vendor/common_custom_style.css">
<link rel="stylesheet" href="admin/assets/vendor/common_custom_responsive.css">
<!-- common style end -->
<!-- <script type="text/javascript" src="admin/assets/vendor/audio-lang/audio-lang-change.js"></script> -->


<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Search Result</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="ncism_inst_helptopics">Search Result</a></li>
								<li class="breadcrumb-item active" aria-current="page">Search Result</li>
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
			<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Category<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="category" id="category">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getCategorylist}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="username">Search Field<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="search_field" id="search_field">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getSearchFieldList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
											<div class="input-style-1">
												<input type="hidden" id="id" name="id" value="0"
													class="mt-3" autocomplete="off" />
											</div>
										</div>
										<!-- end select -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3" id="insthid">
										<div class="select-style-1">
											<label for="text-input">Institute Name<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="institute_name" id="institute_name">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getInstituteList}"
														varStatus="num"> 
														<option value="${item.id}" name="${item.institute_name}">${item.institute_name}</option> 
												    </c:forEach> 
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>
									 <div class="col-12 col-sm-12 col-md-6 col-lg-3" id="search_namehid">
										<div class="input-style-1">
											<label for="text-input">Search Box<span class="mandatory">*</span>
											</label> <input id="name"
												name="name" maxlength="50"
												autocomplete="off"
												placeholder="Please Search Here...">
										</div>
										<!-- end select -->
									</div>
			
			<div class="col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="card-style mb-30 guide_block">
					<div class="counter-section" id="Advance_Search_Details">

<!-- 						<h5 class="section-title">Overview</h5> -->

<!-- 						<p>As part of Admission process filling the e-form service is -->
<!-- 							available to all the registered institute in the AYUSH Grid -->
<!-- 							Portal. This service enables to institutes to fill the e-form of -->
<!-- 							all the student admitted after <b>Sign In</b> to the portal.</p> -->



<!-- 						<h5 class="section-title">Prerequisites for availing this -->
<!-- 							service</h5> -->
<!-- 						<p>Institute will receive an email from commission with the -->
<!-- 							credentials</p> -->

					</div>

				</div>
			</div>

		</div>
		</div>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(
			function() {

				if (window.location.href.includes("msg")) {
					var url = window.location.href.split("?msg")[0];
					window.location = url;
				}
				
				if('${search_box}' != ""){
					globalSearch('${search_box}');
				}
			    $("#insthid").hide();
			    $("#search_namehid").hide();	

// 				advanceSearch_dataFetch();	
// 				mockjax1('getSearch_Advance');
// 				table = dataTable('getSearch_Advance');
// 				$('#btn-reload').on('click', function() {
// 					table.ajax.reload();
// 				});
			});
	
	function globalSearch(searchstring){
			$.ajaxSetup({
				async : false
			});
			$.post("globalsearchAER?"+key+"="+value,{searchstring:searchstring},function(j) {
				if (j != "") {
				for(var i=0;i<j.length;i++){
				if(i == 0){
								$("div#Advance_Search_Details").append('<a target="_blank" href="'+j[i][3]+'"><h5 class="section-title" id="title">'+j[i][1]+'</h5></a><p id=desc_content>'+j[i][2]+'</p><p id=desc_content>Institute Name:'+j[i][4]+'</p>');	
							}else{
								$("div#Advance_Search_Details").append('<a target="_blank" href="'+j[i][3]+'"><h5 class="section-title" id="title">'+j[i][1]+'</h5></a><p id=desc_content>'+j[i][2]+'</p><p id=desc_content>Institute Name:'+j[i][4]+'</p>');					
								}
				}
				} else {
					$("div#Advance_Search_Details")
							.append('<label for="one" class="">Data Not Available</label>');
				}
	
		});
	}
	
	function advanceSearch_dataFetch(){
		$.ajaxSetup({
			async : false
		});
		var category = $("select#category").val();
// 		var search_field = $("select#search_field").val();
// 		alert(category);
		$.post("getFilterAdvance_Enhance_Research_dataSearch?"+key+"="+value,{category:category,search_field:search_field},function(j) {
// 			if (j != "") {
			for(var i=0;i<j.length;i++){
						if(i == 0){
							$("div#Advance_Search_Details").append('<a target="_blank" href="'+j[i][3]+'"><h5 class="section-title" id="title">'+j[i][1]+'</h5></a><p id=desc_content>'+j[i][2]+'</p>');	
						}else{
							$("div#Advance_Search_Details").append('<a target="_blank" href="'+j[i][3]+'"><h5 class="section-title" id="title">'+j[i][1]+'</h5></a><p id=desc_content>'+j[i][2]+'</p>');					
							}
			}
// 			} else {
// 				$("div#Advance_Search_Details")
// 						.append(
// 								'<label for="one" class="">Data Not Available</label>');
// 			}

	});
	}
	
	
	function advanceSearch_dataFetch_after_FieldSearch(){
		$.ajaxSetup({
			async : false
		});
		var category = $("select#category").val();
// 		var search_field = $("select#search_field").val();
		var institute_name = $("select#institute_name").val();
// 		alert(institute_name);
		
		$('div#Advance_Search_Details').empty();
		
		$.post("getFilterAdvance_Enhance_Research_dataSearch?"+key+"="+value,{category:category,
			institute_name:institute_name},function(j) {
			if (j != "") {
			for(var i=0;i<j.length;i++){
						if(i == 0){
							$("div#Advance_Search_Details").append('<a target="_blank" href="'+j[i][3]+'"><h5 class="section-title" id="title">'+j[i][1]+'</h5></a><p id=desc_content>'+j[i][2]+'</p><p id=desc_content>Institute Name:'+j[i][4]+'</p>');	
						}else{
							$("div#Advance_Search_Details").append('<a target="_blank" href="'+j[i][3]+'"><h5 class="section-title" id="title">'+j[i][1]+'</h5></a><p id=desc_content>'+j[i][2]+'</p><p id=desc_content>Institute Name:'+j[i][4]+'</p>');					
							}
			}
			} else {
				$("div#Advance_Search_Details")
						.append(
								'<label for="one" class="">Data Not Available</label>');
			}

	});
	}
	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('category').onchange = function() {
			return advanceSearch_dataFetch_after_FieldSearch();
		};
// 		document.getElementById('search_field').onchange = function() {
// 			return advanceSearch_dataFetch_after_FieldSearch();
// 		};
		document.getElementById('search_field').onchange = function() {
			return Hideshow_Field();
		};
		document.getElementById('institute_name').onchange = function() {
			return advanceSearch_dataFetch_after_FieldSearch();
		};
	});
	
function Hideshow_Field(){
	
	var search_field = $("select#search_field").val();
	if(search_field == "1" || search_field == "2" || search_field == "3" || search_field == "5"){
		    $("#insthid").hide();
		    $("#search_namehid").show();	
		
	}
	else if(search_field == "4"){
	    $("#insthid").show();
	    $("#search_namehid").hide();	
	
    }
	else{
	    $("#insthid").hide();
	    $("#search_namehid").hide();	

	}
}

</script>



