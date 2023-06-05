<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- select2 start -->
<link href="admin/js/dropDown/select2.min.css" rel="Stylesheet">
<script src="admin/js/dropDown/select2.min.js" type="text/javascript"></script>
<!-- select2 end -->

<!-- pagination start -->
<script src="admin/assets/vendor/twbs-pagination/jquery.twbsPagination.min.js" type="text/javascript"></script>
<script src="admin/assets/vendor/twbs-pagination/twbsPaginationScript.js" type="text/javascript"></script>
<link href="admin/assets/vendor/twbs-pagination/twbsPaginationStyle.css" rel="Stylesheet"></link>
<!-- pagination end -->

<link rel="stylesheet" href="admin/assets/vendor/common_custom_style.css">
<link rel="stylesheet" href="admin/assets/vendor/common_custom_responsive.css">

<section class="page-content">
<!--  Intro Single  -->
	<section class="intro-single">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-lg-8">
					<div class="title-single-box">
						<h1 class="title-single">Search Result</h1>
						<!--    <span class="color-text-a">Your message has been sent. Thank you! </span> -->
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="landingpage">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">Search Result</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Intro Single-->

	<!--  advance search page -->
	<section class="advance-search">
		<div class="container">
			<div class="row">
				<div class="col-12 col-sm-12 col-md-12 col-lg-12">
					<div class="search-block">
						<form action="#">
							<!-- advance search section start -->
							<div class="search-inner-block">
								<form action="#">
									<div class="row justify-content-center align-items-end">
										<div class="col-12 col-sm-12 col-md-12 col-lg-12">
											<div class="title-box-d">
												<h5 class="title-d advance-search-title">Advance Search</h5>
											</div>
										</div>
									
										<div class="col-12 col-sm-12 col-md-6 col-lg-4 col-xl-2 col-xxl-2">
											<div class="form-group">
												<label for="search_by_ayush">Search by Ayush system</label>
												<div class="select-position">
													<select type="text" name="medicine_system" id="medicine_system"
														class="singleselect form-control form-control-lg">
														<option value="0">--Select--</option>
													 <c:forEach var="item" items="${getsysList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-12 col-sm-12 col-md-6 col-lg-4 col-xl-2 col-xxl-2">
											<div class="form-group">
												<label for="search_by_category">Search by category</label>
												<div class="select-position">
													<select type="text" name="category" id="category" class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getCategorylist}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-12 col-sm-12 col-md-6 col-lg-4 col-xl-2 col-xxl-2">
											<div class="form-group">
												<label for="search_by_category">Search Field</label>
												<div class="select-position">
													<select type="text" name="search_field" id="search_field" class="singleselect form-control form-control-lg">
														<option value="0">--Select--</option>
													<c:forEach var="item" items="${getSearchFieldList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-12 col-sm-12 col-md-6 col-lg-4 col-xl-3 col-xxl-3" id="insthid">
											<div class="form-group">
												<label for="search_by_category">Institute Name</label>
												<div class="select-position">
													<select type="text" name="institute_name" id="institute_name" class="singleselect form-control form-control-lg">
														<option value="0">--Select--</option>
													<c:forEach var="item" items="${getInstituteList}" varStatus="num"> 
														<option value="${item.id}" name="${item.institute_name}">${item.institute_name}</option> 
												    </c:forEach> 
													</select>
												</div>
											</div>
										</div>
											<div class="col-12 col-sm-12 col-md-6 col-lg-4 col-xl-3 col-xxl-3" id="search_namehid">
											<div class="form-group">
												<label for="search_title">Title</label>
												<input type="text"
													name="name" id="name" maxlength="150"
													class="form-control form-control-lg"
													placeholder="Search Keyword">
											</div>
										</div>
<!-- 										<div class="col-12 col-sm-12 col-md-3 col-lg-2 col-xl-2 col-xxl-2"> -->
<!-- 											<div class="form-group"> -->
<!-- 												<label for="search_by_category">Sort By</label> -->
<!-- 												<div class="select-position"> -->
<!-- 													<select type="text" -->
<!-- 														name="search_by_category" -->
<!-- 														class="singleselect form-control form-control-lg" -->
<!-- 														placeholder="Search Keyword"> -->
<!-- 														<option value="">-- Select --</option>											 -->
<!-- 														<option value="">Recent</option> -->
<!-- 														<option value="">Most Updated</option>													 -->
<!-- 													</select> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
										<div class="col-12 col-sm-12 col-md-2 col-lg-3 col-xl-2 col-xxl-2">
											<div class="form-group">
												<button type="submit" class="btn btn-a">Search</button>
											</div>
										</div>										
									</div>
								</form>
							</div>
							<!-- advance search section end -->
						</form>
					</div>
				</div>	
				<div class="col-12 col-sm-12 col-md-12 col-lg-12">
					<div class="search-result-block custom-pagination-content">
					<div class="search-result-header">
							<div class="display-sorting">
								<div class="form-group d-inline-flex">
									<label class="display-sort-label" for="search_by_category">Sort By :</label>
									<div class="select-position">
										<select type="text" name="search_by_category"
											class="form-control form-control-lg form-select select-xs"
											placeholder="Search Keyword">
											<option value="">-- Select --</option>
											<option value="">Recent</option>
											<option value="">Most Updated</option>
										</select>
									</div>
								</div>
							</div>
							<div class="display-totle-pagecount"><p class="pagecount">Displaying 1 - 10 of 20 Pages</p></div>
						</div>
						<!-- topic block start here -->
						<div class="topic-block" id="Advance_Search_Details">						
<!-- 							<h5 class="topic-title"><a href="#">Ayurveda</a></h5> -->
<!-- 							<div class="topic-ownerdetail"> -->
<!-- 								<ul class="topic-details-list"> -->
<!-- 									<li><b>Author name:</b>Dr. Avinash</li> -->
<!-- 									<li><b>Institute Name:</b>Ayurveda Institute</li> -->
<!-- 									<li><b>Journal:</b>Ayurveda</li> -->
<!-- 									<li><b>Abstract:</b>Ayurveda Research</li> -->
<!-- 								</ul> -->
<!-- 							</div> -->
<!-- 							<div class="field-addon"> -->
<!-- 								<ul class="topic-action"> -->
<!-- 									<li class="field-action-list"> -->
<!-- 										<a class="field-tab" title="Click to open and close comment section" data-bs-toggle="collapse" href="#collapsecmnt" role="button" aria-expanded="false" aria-controls="collapsecmnt"><i class="bi bi-chat-left-dots"></i>Comment</a> -->
<!-- 									</li> -->
<!-- 									<li class="field-action-list field-rating-star"><span class="field-tab"><i class="bi bi-bookmark-star"></i>Rating</span> -->
<!-- 										<span class="rating-star"> -->
<!-- 											<span class="rate-list filled"><a href="#"><i class="bi bi-star-fill"></i></a></span> -->
<!-- 											<span class="rate-list filled"><a href="#"><i class="bi bi-star-fill"></i></a></span> -->
<!-- 											<span class="rate-list filled"><a href="#"><i class="bi bi-star-fill"></i></a></span> -->
<!-- 											<span class="rate-list"><a href="#"><i class="bi bi-star-fill"></i></a></span> -->
<!-- 											<span class="rate-list"><a href="#"><i class="bi bi-star-fill"></i></a></span>											 -->
<!-- 										</span> -->
<!-- 									</li> -->
<!-- 								</ul> -->
<!-- 							</div> -->
<!-- 							form toggle start -->
<!-- 							<div class="form-toggle collapse" id="collapsecmnt"> -->
<%-- 								<form action="#"> --%>
<!-- 									<div class="form-toggle-inner"> -->
<!-- 										<div class="close-btn"><button class="btn btn-a btn-sm" title="close" type="button" data-bs-toggle="collapse" data-bs-target="#collapsecmnt" aria-expanded="false" aria-controls="collapsecmnt"><i class="bi bi-x-lg"></i></button></div> -->
<!-- 										<div class="row"> -->
<!-- 											<div class="col-12 col-sm-12 col-md-12 col-lg-12"> -->
<!-- 												<h5>Comment section</h5> -->
<!-- 											</div> -->
<!-- 											<div class="col-12 col-sm-12 col-md-12 col-lg-12"> -->
<!-- 												<div class="input-style-form-check"> -->
<!-- 													<label for="relevant-option" class="inline-label">Is this link relevant for?<span class="mandatory">*</span></label> -->
<!-- 													<div class="form-check radio-style"> -->
<!-- 														<input type="radio" id="yes" name="relevant-option" class="form-check-input" value="0"> -->
<!-- 														<label for="yes" class="form-check-label">Yes</label> -->
<!-- 													</div> -->
<!-- 													<div class="form-check radio-style"> -->
<!-- 														<input type="radio" id="some_what" name="relevant-option" class="form-check-input" value="1"> -->
<!-- 														<label for="some_what" class="form-check-label">Some What</label> -->
<!-- 													</div> -->
<!-- 													<div class="form-check radio-style"> -->
<!-- 														<input type="radio" id="no" name="relevant-option" class="form-check-input" value="2"> -->
<!-- 														<label for="no" class="form-check-label">No</label> -->
<!-- 													</div> -->
<!-- 												</div>												 -->
<!-- 											</div> -->
<!-- 											<div class="col-12 col-sm-12 col-md-12 col-lg-12"> -->
<!-- 												<div class="form-group"> -->
<!-- 													<label>Give your comments:</label> -->
<!-- 													<textarea name="comments" class="form-control" cols="45" rows="4" placeholder="Give your comments here"></textarea> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="col-12 col-sm-12 col-md-12 col-lg-12 text-center"> -->
<!-- 												<div class="form-group"> -->
<!-- 													<button type="submit" class="btn btn-a">Submit</button> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<%-- 								</form> --%>
<!-- 							</div> -->
<!-- 							form toggle end -->
<!-- 							<div class="topic-details"> -->
<!-- 								<p>Ayurveda means "the science of life" (ayur means "life" and veda means "science" in Sanskrit). Ayurveda is a discipline of the upaveda or "auxiliary knowledge" in Vedic tradition.</p> -->
<!-- 							</div> -->
						</div>		
						<!-- topic block end here -->				
<!-- 						<div class="topic-block">						 -->
<!-- 							<h5 class="topic-title"><a href="#">Homoeopathy</a></h5> -->
<!-- 							<div class="topic-ownerdetail"> -->
<!-- 								<ul class="topic-details-list"> -->
<!-- 									<li><b>Author name:</b>Dr. Vihana</li> -->
<!-- 									<li><b>Institute Name:</b>Homoeopathy Institute</li> -->
<!-- 									<li><b>Journal:</b>Homoeopathy</li> -->
<!-- 									<li><b>Abstract:</b>Homoeopathy Research</li> -->
<!-- 								</ul> -->
<!-- 							</div> -->
<!-- 							<div class="field-addon"> -->
<!-- 								<ul class="topic-action"> -->
<!-- 									<li class="field-action-list"><a href="#" class="field-tab"><i class="bi bi-chat-left-dots"></i>Comment</a></li> -->
<!-- 									<li class="field-action-list field-rating-star"><span class="field-tab"><i class="bi bi-bookmark-star"></i>Rating</span> -->
<!-- 										<span class="rating-star"> -->
<!-- 											<span class="rate-list filled"><a href="#"><i class="bi bi-star-fill"></i></a></span> -->
<!-- 											<span class="rate-list filled"><a href="#"><i class="bi bi-star-fill"></i></a></span> -->
<!-- 											<span class="rate-list"><a href="#"><i class="bi bi-star-fill"></i></a></span> -->
<!-- 											<span class="rate-list"><a href="#"><i class="bi bi-star-fill"></i></a></span> -->
<!-- 											<span class="rate-list"><a href="#"><i class="bi bi-star-fill"></i></a></span>											 -->
<!-- 										</span> -->
<!-- 									</li> -->
<!-- 								</ul> -->
<!-- 							</div> -->
<!-- 							<div class="topic-details"> -->
<!-- 								<p>Homoeopathy is a combination of two Greek words: Homois and Pathos. Homois means similar and pathos means suffering. In other words, homoeopathy is a system of treating diseases with remedies.</p> -->
<!-- 							</div> -->
<!-- 						</div>							 -->
					</div>		
					<!-- pagination start -->
						<div class="custom-pagination"><ul class="pagination" id="pagination"></ul></div>
					<!-- pagination end -->			
				</div>				
			</div>
			</div>
	</section>
	<!-- End Contact Single-->
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
// 				if(i == 0){
// 								$("#Advance_Search_Details").append('<h5 class="topic-title" id="title"><a target="_blank" href="'+j[i][3]+'">'+j[i][1]+'</a></h5>'
// 										+'<div class="topic-ownerdetail">'
// 										+'<ul class="topic-details-list">'
// 										+'<li><b>Author name:</b>'+j[i][5]+'</li>'
// 										+'<li><b>Institute Name:</b>'+j[i][4]+'</li>'
// 										+'<li><b>Journal:</b>'+j[i][6]+'</li>'
// 										+'<li><b>Abstract:</b>'+j[i][7]+'</li>'
// 										+'</ul>'
// 										+'<input type="hidden" id="p_id'+j[i][0]+'" name="p_id'+j[i][0]+'" value="'+j[i][0]+'" />'
// 										+'</div>'
// 										+'<div class="field-addon">'
// 										+'<ul class="topic-action">'
// 										+'<li class="field-action-list">'
// 										+'<a class="field-tab" title="Click to open and close comment section" data-bs-toggle="collapse" href="#collapsecmnt'+i+'" role="button" aria-expanded="false" aria-controls="collapsecmnt"><i class="bi bi-chat-left-dots"></i>Comment</a>'
// 										+'</li>'
// 										+'<li class="field-action-list field-rating-star"><span class="field-tab"><i class="bi bi-bookmark-star"></i>Rating</span>'
// 										+'<span class="rating-star">'
// 										+'<span class="rate-list filled"><a href="#"><i class="bi bi-star-fill"></i></a></span>'
// 										+'<span class="rate-list filled"><a href="#"><i class="bi bi-star-fill"></i></a></span>'
// 										+'<span class="rate-list filled"><a href="#"><i class="bi bi-star-fill"></i></a></span>'
// 										+'<span class="rate-list"><a href="#"><i class="bi bi-star-fill"></i></a></span>'
// 										+'<span class="rate-list"><a href="#"><i class="bi bi-star-fill"></i></a></span>'										
// 										+'</span>'
// 										+'</li>'
// 										+'</ul>'
// 										+'</div>'
// // 									<!-- form toggle start -->
// 									+'<div class="form-toggle collapse" id="collapsecmnt'+i+'">'
// 									+'<form action="#" id="form_cmt_id">'
// 									+'<div class="form-toggle-inner">'
// 									+'<div class="close-btn"><button class="btn btn-a btn-sm" title="close" type="button" data-bs-toggle="collapse" data-bs-target="#collapsecmnt" aria-expanded="false" aria-controls="collapsecmnt"><i class="bi bi-x-lg"></i></button></div>'
// 									+'<div class="row">'
// 									+'<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
// 									+'<h5>Comment section</h5>'
// 									+'</div>'
// 									+'<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
// 									+'<div class="input-style-form-check">'
// 									+'<label for="relevant-option" class="inline-label">Is this link relevant for?<span class="mandatory">*</span></label>'
// 									+'<div class="form-check radio-style">'
// 									+'<input type="radio" id="yes" name="relevant-option" class="form-check-input" value="0">'
// 									+'<label for="yes" class="form-check-label">Yes</label>'
// 									+'</div>'
// 									+'<div class="form-check radio-style">'
// 									+'<input type="radio" id="some_what" name="relevant-option" class="form-check-input" value="1">'
// 									+'<label for="some_what" class="form-check-label">Some What</label>'
// 									+'</div>'
// 									+'<div class="form-check radio-style">'
// 									+'<input type="radio" id="no" name="relevant-option" class="form-check-input" value="2">'
// 									+'<label for="no" class="form-check-label">No</label>'
// 									+'</div>'
// 									+'</div>'												
// 									+'</div>'
// 									+'<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
// 									+'<div class="form-group">'
// 									+'<label>Give your comments:</label>'
// 									+'<textarea name="comment'+j[i][0]+'" id="comment'+j[i][0]+'" class="form-control" cols="45" rows="4" placeholder="Give your comments here"></textarea>'
// 									+'</div>'
// 									+'</div>'
// 									+'<div class="col-12 col-sm-12 col-md-12 col-lg-12 text-center">'
// 									+'<div class="form-group">'
// 									+'<button type="button" class="btn btn-a" id="cmt_btn'+j[i][0]+'">Submit</button>'
// 									+'</div>'
// 									+'</div>'
// 									+'</div>'
// 									+'</div>'
// 									+'</form>'
// 									+'</div>'
// // 									<!-- form toggle end -->
// 									+'<div class="topic-details">'
// 									+'<p id="desc_content">'+j[i][2]+'</p>'
// 									+'</div>');	
// 							}else{
								$("#Advance_Search_Details").append('<h5 class="topic-title" id="title"><a target="_blank" href="'+j[i][3]+'">'+j[i][1]+'</a></h5>'
										+'<div class="topic-ownerdetail">'
										+'<ul class="topic-details-list">'
										+'<li><b>Author name:</b>'+j[i][5]+'</li>'
										+'<li><b>Institute Name:</b>'+j[i][4]+'</li>'
										+'<li><b>Journal:</b>'+j[i][6]+'</li>'
										+'<li><b>Abstract:</b>'+j[i][7]+'</li>'
										+'</ul>'
										+'<input type="hidden" id="p_id'+j[i][0]+'" name="p_id'+j[i][0]+'" value="'+j[i][0]+'" />'
										+'</div>'
										+'<div class="field-addon">'
										+'<ul class="topic-action">'
										+'<li class="field-action-list">'
										+'<a class="field-tab" title="Click to open and close comment section" data-bs-toggle="collapse" href="#collapsecmnt'+i+'" role="button" aria-expanded="false" aria-controls="collapsecmnt"><i class="bi bi-chat-left-dots"></i>Comment</a>'
										+'</li>'
										+'<li class="field-action-list field-rating-star"><span class="field-tab"><i class="bi bi-bookmark-star"></i>Rating</span>'
										+'<span class="rating-star">'
										+'<span class="rate-list filled"><a href="#"><i class="bi bi-star-fill"></i></a></span>'
										+'<span class="rate-list filled"><a href="#"><i class="bi bi-star-fill"></i></a></span>'
										+'<span class="rate-list filled"><a href="#"><i class="bi bi-star-fill"></i></a></span>'
										+'<span class="rate-list"><a href="#"><i class="bi bi-star-fill"></i></a></span>'
										+'<span class="rate-list"><a href="#"><i class="bi bi-star-fill"></i></a></span>'										
										+'</span>'
										+'</li>'
										+'</ul>'
										+'</div>'
// 									<!-- form toggle start -->
									+'<div class="form-toggle collapse" id="collapsecmnt'+i+'">'
									+'<form action="#" id="form_cmt_id">'
									+'<div class="form-toggle-inner">'
									+'<div class="close-btn"><button class="btn btn-a btn-sm" title="close" type="button" data-bs-toggle="collapse" data-bs-target="#collapsecmnt" aria-expanded="false" aria-controls="collapsecmnt"><i class="bi bi-x-lg"></i></button></div>'
									+'<div class="row">'
									+'<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
									+'<h5>Comment section</h5>'
									+'</div>'
									+'<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
									+'<label for="relevant-option" class="inline-label">Is this link relevant for?<span class="mandatory">*</span></label>'
									+'<div class="input-style-form-check">'
									+'<div class="form-check radio-style">'
									+'<input type="radio" id="yes" name="relevant-option" class="form-check-input" value="0">'
									+'<label for="yes" class="form-check-label">Yes</label>'
									+'</div>'
									+'<div class="form-check radio-style">'
									+'<input type="radio" id="some_what" name="relevant-option" class="form-check-input" value="1">'
									+'<label for="some_what" class="form-check-label">Some What</label>'
									+'</div>'
									+'<div class="form-check radio-style">'
									+'<input type="radio" id="no" name="relevant-option" class="form-check-input" value="2">'
									+'<label for="no" class="form-check-label">No</label>'
									+'</div>'
									+'</div>'												
									+'</div>'
									+'<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
									+'<div class="form-group">'
									+'<label>Give your comments:</label>'
									+'<textarea name="comment'+j[i][0]+'" id="comment'+j[i][0]+'" class="form-control" cols="45" rows="4" placeholder="Give your comments here"></textarea>'
									+'</div>'
									+'</div>'
									+'<div class="col-12 col-sm-12 col-md-12 col-lg-12 text-center">'
									+'<div class="form-group">'
									+'<button type="button" class="btn btn-a" id="cmt_btn'+j[i][0]+'">Submit</button>'
									+'</div>'
									+'</div>'
									+'</div>'
									+'</div>'
									+'</form>'
									+'</div>'
// 									<!-- form toggle end -->
									+'<div class="topic-details">'
									+'<p id="desc_content">'+j[i][2]+'</p>'
									+'</div>');										
// 								}


								addOnclick(j[i][0]);

				}
				} else {
					$("#Advance_Search_Details")
							.append('<label for="one" class="">Data Not Available</label>');
				}
	
		});
	}
	
	
	
	function addOnclick(index){
		console.log(index)
	$.ajaxSetup({
	    async: false
	});
		
		document.getElementById('cmt_btn'+index).onclick = function() {
// 			formopen_att(index);
			Commentdata(index);
			};
		}
	
// 	function advanceSearch_dataFetch(){
// 		$.ajaxSetup({
// 			async : false
// 		});
// 		var category = $("select#category").val();
// // 		var search_field = $("select#search_field").val();
// // 		alert(category);
// 		$.post("getFilterAdvance_Enhance_Research_dataSearch?"+key+"="+value,{category:category,search_field:search_field},function(j) {
// // 			if (j != "") {
// 			for(var i=0;i<j.length;i++){
// 						if(i == 0){
// 							$("div#Advance_Search_Details").append('<a target="_blank" href="'+j[i][3]+'"><h5 class="section-title" id="title">'+j[i][1]+'</h5></a><p id=desc_content>'+j[i][2]+'</p>');	
// 						}else{
// 							$("div#Advance_Search_Details").append('<a target="_blank" href="'+j[i][3]+'"><h5 class="section-title" id="title">'+j[i][1]+'</h5></a><p id=desc_content>'+j[i][2]+'</p>');					
// 							}
// 			}
// // 			} else {
// // 				$("div#Advance_Search_Details")
// // 						.append(
// // 								'<label for="one" class="">Data Not Available</label>');
// // 			}

// 	});
// 	}
	
	
	function advanceSearch_dataFetch_after_FieldSearch(){
		$.ajaxSetup({
			async : false
		});
		debugger;
		var category = $("select#category").val();
// 		var search_field = $("select#search_field").val();
		var institute_name = $("select#institute_name").val();
		var medicine_system = $("select#medicine_system").val();
// 		alert(medicine_system);
	alert(category);
		
		$('#Advance_Search_Details').empty();
		
		$.post("getFilterAdvance_Enhance_Research_dataSearch?"+key+"="+value,{category:category,
			institute_name:institute_name,medicine_system:medicine_system},function(j) {
			if (j != "") {
			for(var i=0;i<j.length;i++){
// 						if(i == 0){
// 							$("#Advance_Search_Details").append('<h5 class="topic-title" id="title"><a target="_blank" href="'+j[i][3]+'">'+j[i][1]+'</a></h5>'
// 									+'<div class="topic-ownerdetail">'
// 									+'<ul class="topic-details-list">'
// 									+'<li><b>Author name:</b>'+j[i][5]+'</li>'
// 									+'<li><b>Institute Name:</b>'+j[i][4]+'</li>'
// 									+'<li><b>Journal:</b>'+j[i][6]+'</li>'
// 									+'<li><b>Abstract:</b>'+j[i][7]+'</li>'
// 									+'</ul>'
// 									+'<input type="hidden" id="p_id'+j[i][0]+'" name="p_id'+j[i][0]+'" value="'+j[i][0]+'" />'									+'</div>'
// 									+'<div class="field-addon">'
// 									+'<ul class="topic-action">'
// 									+'<li class="field-action-list">'
// 									+'<a class="field-tab" title="Click to open and close comment section" data-bs-toggle="collapse" href="#collapsecmnt'+i+'" role="button" aria-expanded="false" aria-controls="collapsecmnt"><i class="bi bi-chat-left-dots"></i>Comment</a>'
// 									+'</li>'
// 									+'<li class="field-action-list field-rating-star"><span class="field-tab"><i class="bi bi-bookmark-star"></i>Rating</span>'
// 									+'<span class="rating-star">'
// 									+'<span class="rate-list filled"><a href="#"><i class="bi bi-star-fill"></i></a></span>'
// 									+'<span class="rate-list filled"><a href="#"><i class="bi bi-star-fill"></i></a></span>'
// 									+'<span class="rate-list filled"><a href="#"><i class="bi bi-star-fill"></i></a></span>'
// 									+'<span class="rate-list"><a href="#"><i class="bi bi-star-fill"></i></a></span>'
// 									+'<span class="rate-list"><a href="#"><i class="bi bi-star-fill"></i></a></span>'										
// 									+'</span>'
// 									+'</li>'
// 									+'</ul>'
// 									+'</div>'
// //									<!-- form toggle start -->
// 									+'<div class="form-toggle collapse" id="collapsecmnt'+i+'">'
// 									+'<form action="#" id="form_cmt_id">'
// 									+'<div class="form-toggle-inner">'
// 									+'<div class="close-btn"><button class="btn btn-a btn-sm" title="close" type="button" data-bs-toggle="collapse" data-bs-target="#collapsecmnt" aria-expanded="false" aria-controls="collapsecmnt"><i class="bi bi-x-lg"></i></button></div>'
// 									+'<div class="row">'
// 									+'<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
// 									+'<h5>Comment section</h5>'
// 									+'</div>'
// 									+'<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
// 									+'<div class="input-style-form-check">'
// 									+'<label for="relevant-option" class="inline-label">Is this link relevant for?<span class="mandatory">*</span></label>'
// 									+'<div class="form-check radio-style">'
// 									+'<input type="radio" id="yes" name="relevant-option" class="form-check-input" value="0">'
// 									+'<label for="yes" class="form-check-label">Yes</label>'
// 									+'</div>'
// 									+'<div class="form-check radio-style">'
// 									+'<input type="radio" id="some_what" name="relevant-option" class="form-check-input" value="1">'
// 									+'<label for="some_what" class="form-check-label">Some What</label>'
// 									+'</div>'
// 									+'<div class="form-check radio-style">'
// 									+'<input type="radio" id="no" name="relevant-option" class="form-check-input" value="2">'
// 									+'<label for="no" class="form-check-label">No</label>'
// 									+'</div>'
// 									+'</div>'												
// 									+'</div>'
// 									+'<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
// 									+'<div class="form-group">'
// 									+'<label>Give your comments:</label>'
// 									+'<textarea name="comment'+j[i][0]+'" id="comment'+j[i][0]+'" class="form-control" cols="45" rows="4" placeholder="Give your comments here"></textarea>'
// 									+'</div>'
// 									+'</div>'
// 									+'<div class="col-12 col-sm-12 col-md-12 col-lg-12 text-center">'
// 									+'<div class="form-group">'
// 									+'<button type="button" class="btn btn-a" id="cmt_btn'+j[i][0]+'">Submit</button>'
// 									+'</div>'
// 									+'</div>'
// 									+'</div>'
// 									+'</div>'
// 									+'</form>'
// 									+'</div>'
// //									<!-- form toggle end -->
// 								+'<div class="topic-details">'
// 								+'<p id="desc_content">'+j[i][2]+'</p>'
// 								+'</div>');							
// 							}else{
								
								$("#Advance_Search_Details").append('<h5 class="topic-title" id="title"><a target="_blank" href="'+j[i][3]+'">'+j[i][1]+'</a></h5>'
										+'<div class="topic-ownerdetail">'
										+'<ul class="topic-details-list">'
										+'<li><b>Author name:</b>'+j[i][5]+'</li>'
										+'<li><b>Institute Name:</b>'+j[i][4]+'</li>'
										+'<li><b>Journal:</b>'+j[i][6]+'</li>'
										+'<li><b>Abstract:</b>'+j[i][7]+'</li>'
										+'</ul>'
										+'<input type="hidden" id="p_id'+j[i][0]+'" name="p_id'+j[i][0]+'" value="'+j[i][0]+'" />'
										+'</div>'
										+'<div class="field-addon">'
										+'<ul class="topic-action">'
										+'<li class="field-action-list">'
										+'<a class="field-tab" title="Click to open and close comment section" data-bs-toggle="collapse" href="#collapsecmnt'+i+'" role="button" aria-expanded="false" aria-controls="collapsecmnt"><i class="bi bi-chat-left-dots"></i>Comment</a>'
										+'</li>'
										+'<li class="field-action-list field-rating-star"><span class="field-tab"><i class="bi bi-bookmark-star"></i>Rating</span>'
										+'<span class="rating-star">'
										+'<span class="rate-list filled"><a href="#"><i class="bi bi-star-fill"></i></a></span>'
										+'<span class="rate-list filled"><a href="#"><i class="bi bi-star-fill"></i></a></span>'
										+'<span class="rate-list filled"><a href="#"><i class="bi bi-star-fill"></i></a></span>'
										+'<span class="rate-list"><a href="#"><i class="bi bi-star-fill"></i></a></span>'
										+'<span class="rate-list"><a href="#"><i class="bi bi-star-fill"></i></a></span>'										
										+'</span>'
										+'</li>'
										+'</ul>'
										+'</div>'
// 									<!-- form toggle start -->
									+'<div class="form-toggle collapse" id="collapsecmnt'+i+'">'
									+'<form action="#" id="form_cmt_id">'
									+'<div class="form-toggle-inner">'
									+'<div class="close-btn"><button class="btn btn-a btn-sm" title="close" type="button" data-bs-toggle="collapse" data-bs-target="#collapsecmnt" aria-expanded="false" aria-controls="collapsecmnt"><i class="bi bi-x-lg"></i></button></div>'
									+'<div class="row">'
									+'<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
									+'<h5>Comment section</h5>'
									+'</div>'
									+'<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
									+'<label for="relevant-option" class="inline-label">Is this link relevant for?<span class="mandatory">*</span></label>'
									+'<div class="input-style-form-check">'
									+'<div class="form-check radio-style">'
									+'<input type="radio" id="yes" name="relevant-option" class="form-check-input" value="0">'
									+'<label for="yes" class="form-check-label">Yes</label>'
									+'</div>'
									+'<div class="form-check radio-style">'
									+'<input type="radio" id="some_what" name="relevant-option" class="form-check-input" value="1">'
									+'<label for="some_what" class="form-check-label">Some What</label>'
									+'</div>'
									+'<div class="form-check radio-style">'
									+'<input type="radio" id="no" name="relevant-option" class="form-check-input" value="2">'
									+'<label for="no" class="form-check-label">No</label>'
									+'</div>'
									+'</div>'												
									+'</div>'
									+'<div class="col-12 col-sm-12 col-md-12 col-lg-12">'
									+'<div class="form-group">'
									+'<label>Give your comments:</label>'
									+'<textarea name="comment'+j[i][0]+'" id="comment'+j[i][0]+'" class="form-control" cols="45" rows="4" placeholder="Give your comments here"></textarea>'
									+'</div>'
									+'</div>'
									+'<div class="col-12 col-sm-12 col-md-12 col-lg-12 text-center">'
									+'<div class="form-group">'
									+'<button type="button" class="btn btn-a" id="cmt_btn'+j[i][0]+'">Submit</button>'
									+'</div>'
									+'</div>'
									+'</div>'
									+'</div>'
									+'</form>'
									+'</div>'
// 									<!-- form toggle end -->
									+'<div class="topic-details">'
									+'<p id="desc_content">'+j[i][2]+'</p>'
									+'</div>');	
								addOnclick(j[i][0]);			
// 							}
			}
			} else {
				$("#Advance_Search_Details")
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
		document.getElementById('medicine_system').onchange = function() {
			return advanceSearch_dataFetch_after_FieldSearch();
		};
// 		document.getElementById('cmt_btn').onclick = function() {
// 			return Commentdata();
// 		};
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



function Commentdata(index) {
	$.ajaxSetup({
	    async: false
	});
// 	if ($("input#name").val().trim() == "") {
// 		alert("Please Enter Name");
// 		$("input#name").focus();
// 		return false;
// 	}
// 	if ($("input#email").val().trim() == "") {
// 		alert("Please Enter Email");
// 		$("input#email").focus();
// 		return false;
// 	}
// 	if ($("input#subject").val().trim() == "") {
// 		alert("Please Enter Subject");
// 		$("input#subject").focus();
// 		return false;
// 	}
	if ($("#comment"+index).val().trim() == "") {
		alert("Please Enter Comment");
		$("#comment"+index).focus();
		return false;
	}
	
	//var form = $("#Contact_US").serialize();
	
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
// 	var name =$("#name").val();
// 	var email =$("#email").val();
	var comment =$("#comment"+index).val();
	var p_id =$("#p_id"+index).val();
 	$.post("Comment_Details_SaveAction?"+key+"="+value,{comment:comment,p_id:p_id},function(j) {
		if(j == "Data Saved Successfully"){
        alert("Your Comment Has Been Sucessfully Submitted");
//         location.reload();
		}
	});

}
</script>




                       