<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/commonJS/addmorefunctionality.js"></script>
<script src="js/common/multicheck.js"></script>
<link rel="stylesheet" href="js/common/multicheck.css">
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<link rel="stylesheet" href="assets/db_css/db_custom_style.css">
<link rel="stylesheet" href="assets/vendor/internal_css.css">
<!-- <script> -->
<%-- // 	var username = "${username}"; --%>
<%-- // 	var role = "${role}"; --%>

<!-- </script> -->

<section class="search_regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30" id="header_print">
						<h2>National Commission For Homoeopathy</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#0">Start View</a></li>
								<li class="breadcrumb-item"><a href="#0">Regulation
										Forms</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Report Form A</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
		<form:form name="Pract_view" id="Pract_view" action="Pract_viewAction"
			method="post" class="form-horizontal" modelAttribute="Pract_viewCMD">
			<div class="search-pract-details-view">
				<div class="row">

					<div class="col-lg-12">
						<!-- input style start -->
						<div class="card-style mb-30" id="card_view">
							<!--  <h6 class="mb-25">NATIONAL REGISTER FOR HOMOEOPATHY </h6> -->
							<!--      <img src="" alt="photo_path" name="photo_path" id="photo_path" width="500" height="600"> -->

							<!--                 <img class='d-block img5050 imageZomm' alt='No Image' id='myImg"+rs.getString("id")+"'  -->
							<!--                 src='MedicalImagePath7?i_id="+rs.getString("id")+"' /> -->
							<div class="col-lg-12 text-center viewstyle">
								<img src="assets/img/nchlogo.png" class="printlogo custom_right"><span
									class="custom_vertical"><b>राष्ट्रीय होम्योपैथी आयोग</b><br>
									<b> NATIONAL COMMISSION FOR HOMOEOPATHY</b></span>
							</div>
							<div class="col-lg-12 text-center viewstyle">
								<span><b>NATIONAL REGISTER FOR HOMOEOPATHY </b></span><img
									id="identity_image_preview" alt="Officer Image"
									src="js/images/No_Image.jpg" class="custom_getView1" />
							</div>

							<!-- <input id="photo_path" name="photo_path" class="form-control"
										autocomplete="off" maxlength="25" > -->
							<div class="row">

								<div class="col-lg-6 col-md-6 col-sm-6 col-6">

									<div class="input-style-2" class="custom_getView">
										<label>NRH Enrollment Number </label>

									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">
										<label id="nrh_en_no1"> </label> <input type="hidden"
											id="nrh_en_no" name="nrh_en_no" class="form-control"
											autocomplete="off" maxlength="25" items="${item[0]}">
										<input type="hidden" id="viewid" name="viewid"
											class="form-control" value="0" autocomplete="off">
									</div>
								</div>

							</div>
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">
										<label>Name of Professional</label>

									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">
										<label id="first_name1"></label> <input type="hidden"
											id="first_name" name="first_name" class="form-control"
											autocomplete="off" maxlength="25">

									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">
										<label>Father's Name </label>

									</div>
								</div>

								<div class="ccol-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">
										<label id="father_name1"> </label> <input type="hidden"
											id="father_name" name="father_name" class="form-control"
											autocomplete="off" maxlength="25">
										<!-- <input type="hidden" id="id" name="id" class="form-control"
										value="0" autocomplete="off"> -->
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">
										<label>Present Correspondence address </label>

									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">
										<label id="pre_corre_add1"></label> <input type="hidden"
											id="pre_corre_add" name="pre_corre_add" class="form-control"
											autocomplete="off" maxlength="25"> <select
											name="pres_state" id="pres_state"
											class="form-control custom_get custom-d-none">
											<option value="0" selected="selected">-- Select
												State --</option>
											<c:forEach var="item" items="${MedStateName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">
										<label>Permanent Address </label>

									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">
										<label id="per_address1"></label> <input type="hidden"
											id="per_address" name="per_address" class="form-control"
											autocomplete="off" maxlength="25"> <select
											name="perm_state" id="perm_state"
											class="form-control custom_get custom-d-none">
											<option value="0" selected="selected">-- Select
												State --</option>
											<c:forEach var="item" items="${MedStateName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>

									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">
										<label>E-mail Id </label>

									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">
										<label class="email-text-lowercase" id="email_id1"></label> <input type="hidden"
											id="email_id" name="email_id" class="form-control"
											autocomplete="off" maxlength="25">

									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">
										<label>Date of Birth </label>

									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">
										<label id="dob1"></label> <input type="hidden" id="dob"
											name="dob" class="form-control" autocomplete="off"
											maxlength="25">

									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">
										<label>Nationality </label>

									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">

										<label id="nationality1"></label> <select type="hidden"
											name="nationality" id="nationality"
											class="form-control custom_get custom-d-none">
											<option value="0" selected="selected">-- Select
												Country --</option>
											<c:forEach var="item" items="${getMedNationality}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
										<!-- <input id="nationality" name="nationality" class="form-control"
										autocomplete="off" maxlength="25" > -->

									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">
										<label>Name of Medical Degree or Diploma Obtained and
											University With The Month And Year if Passing Qualification </label>

									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">

										<label id="degree_uni_my1"></label>
										<!-- 								<label id="uni_my1"></label> -->
										<!-- 								<label id="my1"></label> -->
										<!-- 								<select name="degree" id="degree" -->
										<!-- 																	style="text-transform: uppercase; display:none;" class="form-control"> -->
										<!-- 																	<option value="0" selected="selected">-- -->
										<!-- 																		Select Degree --</option> -->
										<%-- 																	<c:forEach var="item" items="${getDegreeList}" --%>
										<%-- 																		varStatus="num"> --%>
										<%-- 																		<option value="${item.id}" name="${item.degree_name}">${item.degree_name}</option> --%>
										<%-- 																	</c:forEach> --%>
										<!-- 																</select> -->

										<input type="hidden" id="degree_uni_my" name="degree_uni_my"
											class="form-control" autocomplete="off" maxlength="25">

									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">
										<label>State Registration Number </label>

									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2" class="custom_getView">
										<label id="reg_no1"></label> <input type="hidden" id="reg_no"
											name="reg_no" class="form-control" autocomplete="off"
											maxlength="25">

									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-6 custom-d-none">
									<div class="input-style-2 custom_getView">
										<label>Date of Registration </label>

									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2 custom_getView custom-d-none">
										<label id="date_of_reg1"></label> <input type="hidden"
											id="date_of_reg" name="date_of_reg" class="form-control"
											autocomplete="off" maxlength="25">

									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2 custom_getView">
										<label>Names(s) of The Register(National/State) </label>

									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2 custom_getView">
										<label id="register_state1"></label>
										<!-- <input id="register_state" name="register_state" class="form-control"
										autocomplete="off" maxlength="25" > -->
										<select name="register_state" id="register_state"
											class="form-control custom_get custom-d-none">
											<option value="0" selected="selected">-- Select
												State --</option>
											<c:forEach var="item" items="${MedStateName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>

									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2 custom_getView">
										<label>Name of Hospital or University With Complete
											Address For Purposes of Teaching or Research or Practice, of
											Medicine </label>

									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2 custom_getView">
										<label id="hos_name_add1"></label>
										<!-- 										<label id="hos_name"></label> -->
										<!-- 									<label id="add1"></label> -->
										<!-- 									<label id="add2"></label> -->
										<!-- 									<label id="add3"></label> -->
										<input type="hidden" id="hos_name_add" name="hos_name_add"
											class="form-control" autocomplete="off" maxlength="25">

									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2 custom_getView">
										<label>Name of Person in University or Hospital Who
											Will be Responsible For Legal Issues Regarding Patient Care
											Provided by Doctor Concerned </label>

									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2 custom_getView">
										<label id="name_of_res_p1"></label> <input type="hidden"
											id="name_of_res_p" name="name_of_res_p" class="form-control"
											autocomplete="off" maxlength="25"> <input
											type="hidden" id="id" name="id" class="form-control"
											value="0" autocomplete="off">
									</div>
								</div>
							</div>


							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2 custom_getView">
										<label>Valid Upto </label>

									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-6">
									<div class="input-style-2 custom_getView">
										<label id="valid_up_to1"></label> <input type="hidden"
											id="valid_up_to" name="valid_up_to" class="form-control"
											autocomplete="off" maxlength="25"> <input
											type="hidden" id="id" name="id" class="form-control"
											value="0" autocomplete="off">
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">

									<div class="custom-choose-one">
										<div class="input-style-form-check_block check-multi-list">
											<div class="form-check checkbox-style px-0">

												<label class="check-list">The Enrollment in National
													Register for Homoeopathy(NRH) shall remain valid till the
													name/registration number of the applicant remains live in
													the State Register.</label> <label class="check-list">This
													is a computer generated document, no signature required.</label> <label
													class="check-list">Above information is subject to
													verification from the data available on NCH
													website:www.nch.org.in.</label>
												<!-- 													<label class="check-list">Above information is -->
												<!-- 													subject to verification from the data available on NCH -->
												<!-- 													website:www.nch.org.in.</label> -->
											</div>
										</div>
									</div>

									<!-- 									<div class="inst_block"> -->

									<!-- 										<ul class="inst_list"> -->
									<!-- 											<li><p class="inst_text">The Enrollment in National -->
									<!-- 													Register for Homoeopathy(NRH) shall remain valid till the -->
									<!-- 													name/registration number of the applicant remains live in -->
									<!-- 													the State Register.</p></li> -->
									<!-- 											<li><p class="inst_text">This is a computer -->
									<!-- 													generated document, no signature required.</p></li> -->
									<!-- 											<li><p class="inst_text">Above information is -->
									<!-- 													subject to verification from the data available on NCH -->
									<!-- 													website:www.nch.org.in.</p></li> -->
									<!-- 										</ul> -->
									<!-- 									</div> -->

									<!-- <div class="viewcont pt30">
						<ul><div class="d-flex"><div>
 <span class="pointer custom_color">&#x2022</span></div>
  <div><li class = "cust_size">The Enrollment in National Register for Homoeopathy(NRH) shall remain valid till the name/registration number of the applicant remains live in the State Register.  </li></div>
  </div>
  <div class="d-flex"><div>
 <span class="pointer custom_color">&#x2022</span></div>
  <div><li class = "cust_size"> This is a computer generated document, no signature required. </li></div></div>
   <div class="d-flex"><div>
 <span class="pointer custom_color">&#x2022</span></div>
  <div><li class = "cust_size">Above information is subject to verification from the data available on NCH website:www.nch.org.in.</li></div></div>
</ul>  
</div> -->
								</div>
							</div>





							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">

											<li>
<!-- 												                    <a href="edu_search_pro_clg_reg_url" class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback" value="Back"><i class="lni lni-chevron-left"></i>Back</a> -->

												<c:if test="${ role  == 'Practitioner_NCH' }">
													<a href="Search_prac_detailsUrl"
														class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
														<i class="lni lni-chevron-left"></i>Back
													</a>
												</c:if> <c:if test="${ role  == 'University_NCH' }">
													<a href="edu_search_pro_uni_reg_url"
														class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
														<i class="lni lni-chevron-left"></i>Back
													</a>
<!-- 													--06-03-2023 -->
												</c:if> 
												<c:if test="${ role  == 'State_Council_NCH' }">
													<a href="Search_State_pracUrl"
														class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
														<i class="lni lni-chevron-left"></i>Back
													</a>
												</c:if> 
												<c:if test="${ role  == 'NCH' }">
													<a href="Search_NCH_pracUrl"
														class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"
														value="Back"> <i class="lni lni-chevron-left"></i>Back
													</a>
												</c:if>
											</li>
											<!--                     <li> -->
											<!--                     <input type="submit" class="main-btn secondary-btn btn-hover btn-save" value="Save" onclick="return Validate();"> -->
											<!--                   </li> -->


											<!-- 	                    <li> -->
											<!-- 	                     <a href="#0" class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" onclick="printDiv('card_view');"> -->
											<!-- 	                     <i class="lni lni-printer" id="printId" value="PDF" title="Export to PDF" ></i>  -->
											<!-- 	                     PDF </a> -->
											<!-- 	                  	</li> -->
											<!-- 									=============03/09/2022 ============ -->
											<li><a href="#0"
												class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon btnpdf"
												id="pdfdwnload"> PDF <%-- 	                  	  --%> <i
													class="bi bi-file-pdf" id="printId" value="PDF"
													title="Export to PDF"></i>
											</a> <%-- 	                  	<input type="button" class="main-btn info-btn btn-hover" value="Get print form" onclick="DownloadData('${Pract_viewCMD.id}');"> --%>
												<input type="hidden" name="logo_path" id="logo_path"
												value="" /></li>
										</ul>
									</div>
								</div>
							</div>
						</div>


					</div>
					<!-- end card -->

				</div>

			</div>
			<!-- end row -->


	</form:form>
	</div>
	</div>
	</div>
	</div>
	

</section>

<c:url value="Edit_edu_reg_mstrUrl" var="Edit_edu_reg_mstrUrl" />
<form:form action="${Edit_edu_reg_mstrUrl}" method="post"
	id="updateForm" name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="delete_edu_reg_mstr_Url" var="delete_edu_reg_mstr_Url" />
<form:form action="${delete_edu_reg_mstr_Url}" method="post"
	id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>
<c:url value="Excel_Auth_Posted_query" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm"
	name="ExcelForm" modelAttribute="cont_comd_ex">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>


<!-- start -->

<c:url value="getpractitionerList" var="getpractitionerList" />
<form:form action="${getpractitionerList}" method="post"
	id="downloadForm" name="viewForm" modelAttribute="doid1">
	<input type="hidden" name="typeReport" id="typeReport" value="0" />
	<input type="hidden" name="emp_id3" id="emp_id3" value="0" />
</form:form>

<!-- The Modal -->
<div id="myModal" class="modal">
	<span class="close">&times;</span> <img class="modal-content"
		id="img01">
	<div id="caption"></div>
</div>


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(
			function() {

				var prest_name = '${Pract_viewCMD.pre_state}';
				$("#pres_state").val(prest_name);
				prest_name = $("#pres_state option:selected").text();

				var prestdist_name = '${Pract_viewCMD.pre_district}';
				$("#pre_district").val(prestdist_name);
				prestdist_name = $("#pre_district option:selected").text();

				//alert('${list[0][10]}');
				// for(var i = 0 ;i<list.size();i++){

				// }

				var hos_namevar = $("label#hos_name_add1").text(
						'${list[0][10]}');
				var name_ofvar = $("label#name_of_res_p1").text(
						'${list[0][11]}');
				var hos_degree_univar = $("label#degree_uni_my1").text(
						'${alist[0][0]}');

				// 		  var strh = '${list[0][10]}' ;
				// 			var ssh = strh.split(',');
				// 			var arryh = [];
				// 		  	arryh.push([ssh[0], ssh[1],ssh[2],ssh[3]);

				// 				  var place_name =  arry[0][0];
				// 				  var hos_ad1 =  arry[0][1];
				// 				  var hos_ad2 =  arry[0][2];
				// 				  var hos_ad3 =  arry[0][3];

				// 				  for(var j = 0 ;j<ssh.length;i++){

				// 					  	$("label#hos_name").text(place_name);
				// 						 $("label#add1").text(hos_ad1);
				// 						 $("label#add2").text(hos_ad2);
				// 						 $("label#add3").text(hos_ad3);
				// 				   	}

				// 	var hos_degree_univar_array = h.split('|');
				// 	alert("hhos_degree_univar_array--"+hos_degree_univar_array);
				// 	function sliceIntoChunks(arr, chunkSize) {
				// 	    const res = [];
				// 	    for (let i = 0; i < arr.length; i += chunkSize) {
				// 	        const chunk = arr.slice(i, i + chunkSize);
				// 	        res.push(chunk);
				// 	    }
				// 	    return res;
				// 	}

				// 	const arr = ${list[0][10]};
				// 	console.log(sliceIntoChunks(arr, 3));

				var perst_name = '${Pract_viewCMD.per_state}';
				$("#pres_state").val(perst_name);
				perst_name = $("#pres_state option:selected").text();

				var perstdist_name = '${Pract_viewCMD.per_district}';
				$("#per_district").val(perstdist_name);
				perstdist_name = $("#per_district option:selected").text();

				var nati_name = '${Pract_viewCMD.nationality}';
				$("#nationality").val(nati_name);
				nati_name = $("#nationality option:selected").text();

				//var todayDate = $("#dob").val('${Pract_viewCMD.dob}').toISOString().slice(0, 10);
				//console.log(todayDate);

				$("#nrh_en_no").val('${Pract_viewCMD.nrh_en_no}');
				$("#nrh_en_no1").text('${Pract_viewCMD.nrh_en_no}');
				$("#first_name").val('${Pract_viewCMD.first_name}');
				$("#first_name1").text('${Pract_viewCMD.first_name}');
				$("#father_name").val('${Pract_viewCMD.father_name}');
				$("#father_name1").text('${Pract_viewCMD.father_name}');
				//	alert('${Pract_viewCMD.pre_address_details1}');
				$("#pre_corre_add").val(
						'${Pract_viewCMD.pre_address_details1}' + ' '
								+ '${Pract_viewCMD.pre_address_details2}' + ' '
								+ '${Pract_viewCMD.pre_address_details3}' + ' '
								+ prest_name + ' ' + prestdist_name + ','
								+ '${Pract_viewCMD.pre_pincode}');
				$("#pre_corre_add1").text(
						'${Pract_viewCMD.pre_address_details1}' + ' '
								+ '${Pract_viewCMD.pre_address_details2}' + ' '
								+ '${Pract_viewCMD.pre_address_details3}' + ' '
								+ prest_name + ' ' + prestdist_name + ','
								+ '${Pract_viewCMD.pre_pincode}');
				$("#per_address").val(
						'${Pract_viewCMD.per_address_details1}' + ' '
								+ '${Pract_viewCMD.per_address_details2}' + ' '
								+ '${Pract_viewCMD.per_address_details3}' + ' '
								+ perst_name + ' ' + perstdist_name + ','
								+ '${Pract_viewCMD.per_pincode}');
				$("#per_address1").text(
						'${Pract_viewCMD.per_address_details1}' + ' '
								+ '${Pract_viewCMD.per_address_details2}' + ' '
								+ '${Pract_viewCMD.per_address_details3}' + ' '
								+ perst_name + ' ' + perstdist_name + ','
								+ '${Pract_viewCMD.per_pincode}');
				$("#email_id").val('${Pract_viewCMD.email_id}');
				$("#email_id1").text('${Pract_viewCMD.email_id}');

				// 	var hosp_list += j[i].place_of_working_name +", "+ j[i].hos_address1  +" "+ j[i].hos_address2  +" "+ j[i].hos_address3;
				// 	name_per += j[i].name_of_res_p ;

				// 	$("#hos_name_add").val(hosp_list);
				// 	$("#hos_name_add1").text(hosp_list);

				var ee = '${Pract_viewCMD.dob}';

				// 	$("#dob").val(ee);
				//var dateObject = new Date(ee);
				// 	document.body.innerHTML = dateObject.toString();
				//var todayDate = dateObject.toISOString().slice(0, 10);
				// 	alert(todayDate);

				const date = new Date(ee);
				const formattedDate = date.toLocaleDateString('en-GB', {
					day : 'numeric',
					month : 'numeric',
					year : 'numeric'
				}).replace(/ /g, '/');
				//	console.log(formattedDate);

				$("#dob1").text(formattedDate);

				var dt_reg2 = '${Pract_viewCMD.date_of_reg}';

				const date1 = new Date(dt_reg2);
				const formattedDate1 = date1.toLocaleDateString('en-GB', {
					day : 'numeric',
					month : 'numeric',
					year : 'numeric'
				}).replace(/ /g, '/');
				//	console.log(formattedDate1);

				$("#date_of_reg").val('${Pract_viewCMD.date_of_reg}');
				$("#date_of_reg1").text(formattedDate1);
				$("#reg_no").val('${Pract_viewCMD.state_reg_no}');
				$("#reg_no1").text('${Pract_viewCMD.state_reg_no}');
				$("#register_state").val('${Pract_viewCMD.per_state}');
				$("#register_state1").text(perst_name);
				$("#photo_path").val('${Pract_viewCMD.photo_path}');

				$("#valid_up_to").val('${Pract_viewCMD.valid_up_to}');
				$("#valid_up_to1").text('${Pract_viewCMD.valid_up_to}');
				$("#id").val('${Pract_viewCMD.id}');
				var idforimg = '${Pract_viewCMD.id}';
				$('#identity_image_preview').attr("src",
						"MedicalImagePath7?i_id=" + idforimg + " ");

				//alert(${Pract_viewCMD.nationality})
				$("#nationality").val('${Pract_viewCMD.nationality}');
				$("#nationality1").text(nati_name);

			});

	//=========CSP START

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('pdfdwnload').onclick = function() {
			DownloadData('${Pract_viewCMD.id}');
		};
	});
	function setselectall() {

		if ($("#nSelAll").prop("checked")) {
			$(".nrCheckBox").prop('checked', true);
		} else {
			$(".nrCheckBox").prop('checked', false);
		}

		var l = $('[name="cbox"]:checked').length;
		$("#tregn").val(l);
		document.getElementById('tregn').innerHTML = l;

	}

	function EditData(id) {
		// 		alert("id---"+id)
		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}

	function findselected() {
		var nrSel = $('.nrCheckBox:checkbox:checked').map(function() {
			return $(this).attr('id');
		}).get();

		var b = nrSel.join(':');
		$("#CheckVal").val(b);
		$('#tregn').text(nrSel.length);
	}

	function setApproveStatus() {

		findselected();

		var a = $("#CheckVal").val();

		if (a == "") {
			alert("Please Select the Data for Approval");
		} else {
			$.post("Approve_regulation_Data?" + key + "=" + value, {
				a : a,
				status : "1"
			}).done(function(j) {
				alert(j);
				location.reload();

			});
		}
	}
	function setRejectStatus() {

		findselected();

		var a = $("#CheckVal").val();

		if (a == "") {
			alert("Please Select the Data for Revert Back");
		} else {

			$.post("Approve_regulation_Data?" + key + "=" + value, {
				a : a,
				status : "2"
			}).done(function(j) {
				alert(j);
				location.reload();
				Search();
			});
		}
	}

	// Get the modal
	function imageView(obj) {

		var modal = document.getElementById("myModal");
		var span = document.getElementsByClassName("close")[0];

		// Get the image and insert it inside the modal - use its "alt" text as a caption
		var img = document.getElementById("myImg" + obj);

		var modalImg = document.getElementById("img01");
		var captionText = document.getElementById("caption");

		img.onclick = function() {
			modal.style.display = "block";
			modalImg.src = this.src;
			//captionText.innerHTML = this.alt;
		}

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
		}

	}
</script>


<script nonce="${cspNonce}" type="text/javascript">
	function getExcel() {

		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('ExcelForm').submit();
	}

	function getDistrictper() {
		var selval = $("#per_state").val();
		$("select#per_district").empty();

		$
				.post(
						"getDistrictOnstate?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {

							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i].district_id + '" name="'+j[i].district_id+'" >'
										+ j[i].district_name + '</option>';
							}
							$("select#per_district").html(options);

						});
	}

	function getDegreeName(obj) {
		var DegreeName = $("#" + obj.id).val();
		$
				.post(
						'getDegreeName?' + key + "=" + value,
						{
							DegreeName : DegreeName
						},
						function(k) {
							var options = '';
							for (var i = 0; i < k.length; i++) {
								options += '<option value="'+k[i].id+'" name="' + k[i].degree_name+ '" >'
										+ k[i].degree_name + '</option>';
							}
							$("#degree_name").html(options);

						});

	}

	function printDiv(divName) {

		var printContents = document.getElementById(divName).innerHTML;
		var originalContents = document.body.innerHTML;

		document.body.innerHTML = printContents;
		$("#header_print").show();

		var prest_name = '${Pract_viewCMD.pre_state}';
		$("#pres_state").val(prest_name);
		prest_name = $("#pres_state option:selected").text();

		var prestdist_name = '${Pract_viewCMD.pre_district}';
		$("#pre_district").val(prestdist_name);
		prestdist_name = $("#pre_district option:selected").text();

		var perst_name = '${Pract_viewCMD.per_state}';
		$("#pres_state").val(perst_name);
		perst_name = $("#pres_state option:selected").text();

		var perstdist_name = '${Pract_viewCMD.per_district}';
		$("#per_district").val(perstdist_name);
		perstdist_name = $("#per_district option:selected").text();

		var nati_name = '${Pract_viewCMD.nationality}';
		$("#nationality").val(nati_name);
		nati_name = $("#nationality option:selected").text();

		//var todayDate = $("#dob").val('${Pract_viewCMD.dob}').toISOString().slice(0, 10);
		//console.log(todayDate);

		$("#nrh_en_no").val('${Pract_viewCMD.nrh_en_no}');
		$("#nrh_en_no1").text('${Pract_viewCMD.nrh_en_no}');
		$("#first_name").val('${Pract_viewCMD.first_name}');
		$("#first_name1").text('${Pract_viewCMD.first_name}');
		$("#father_name").val('${Pract_viewCMD.father_name}');
		$("#father_name1").text('${Pract_viewCMD.father_name}');
		//	alert('${Pract_viewCMD.pre_address_details1}');
		$("#pre_corre_add").val(
				'${Pract_viewCMD.pre_address_details1}' + ' '
						+ '${Pract_viewCMD.pre_address_details2}' + ' '
						+ '${Pract_viewCMD.pre_address_details3}' + ' '
						+ prest_name + ' ' + prestdist_name + ','
						+ '${Pract_viewCMD.pre_pincode}');
		$("#pre_corre_add1").text(
				'${Pract_viewCMD.pre_address_details1}' + ' '
						+ '${Pract_viewCMD.pre_address_details2}' + ' '
						+ '${Pract_viewCMD.pre_address_details3}' + ' '
						+ prest_name + ' ' + prestdist_name + ','
						+ '${Pract_viewCMD.pre_pincode}');
		$("#per_address").val(
				'${Pract_viewCMD.per_address_details1}' + ' '
						+ '${Pract_viewCMD.per_address_details2}' + ' '
						+ '${Pract_viewCMD.per_address_details3}' + ' '
						+ perst_name + ' ' + perstdist_name + ','
						+ '${Pract_viewCMD.per_pincode}');
		$("#per_address1").text(
				'${Pract_viewCMD.per_address_details1}' + ' '
						+ '${Pract_viewCMD.per_address_details2}' + ' '
						+ '${Pract_viewCMD.per_address_details3}' + ' '
						+ perst_name + ' ' + perstdist_name + ','
						+ '${Pract_viewCMD.per_pincode}');
		$("#email_id").val('${Pract_viewCMD.email_id}');
		$("#email_id1").text('${Pract_viewCMD.email_id}');

		var ee = '${Pract_viewCMD.dob}';

		// 	$("#dob").val(ee);
		//var dateObject = new Date(ee);
		// 	document.body.innerHTML = dateObject.toString();
		//var todayDate = dateObject.toISOString().slice(0, 10);
		// 	alert(todayDate);

		const date = new Date(ee);
		const formattedDate = date.toLocaleDateString('en-GB', {
			day : 'numeric',
			month : 'numeric',
			year : 'numeric'
		}).replace(/ /g, '/');
		//	console.log(formattedDate);

		$("#dob1").text(formattedDate);

		var dt_reg2 = '${Pract_viewCMD.date_of_reg}';

		const date1 = new Date(dt_reg2);
		const formattedDate1 = date1.toLocaleDateString('en-GB', {
			day : 'numeric',
			month : 'numeric',
			year : 'numeric'
		}).replace(/ /g, '/');
		//	console.log(formattedDate1);

		$("#date_of_reg").val('${Pract_viewCMD.date_of_reg}');
		$("#date_of_reg1").text(formattedDate1);
		$("#reg_no").val('${Pract_viewCMD.state_reg_no}');
		$("#reg_no1").text('${Pract_viewCMD.state_reg_no}');
		$("#register_state").val('${Pract_viewCMD.per_state}');
		$("#register_state1").text(perst_name);
		$("#photo_path").val('${Pract_viewCMD.photo_path}');

		$("#valid_up_to").val('${Pract_viewCMD.valid_up_to}');
		$("#valid_up_to1").text('${Pract_viewCMD.valid_up_to}');
		$("#id").val('${Pract_viewCMD.id}');
		var idforimg = '${Pract_viewCMD.id}';
		$('#identity_image_preview').attr("src",
				"MedicalImagePath7?i_id=" + idforimg + " ");

		//alert(${Pract_viewCMD.nationality})
		$("#nationality").val('${Pract_viewCMD.nationality}');
		$("#nationality1").text(nati_name);

		//alert(${Pract_viewCMD.nationality})
		var id = $("#id").val();
		$.post("getViewFromHosp?" + key + "=" + value, {

			id : id

		}, function(j) {
			var hosp_list = "";
			var name_per = "";
			for (var i = 0; i < j.length; i++) {
				if (i == 0) {
					hosp_list += j[i].place_of_working_name + ", "
							+ j[i].hos_address1 + " " + j[i].hos_address2 + " "
							+ j[i].hos_address3;
					name_per += j[i].name_of_res_p;
				} else {
					hosp_list += ", " + j[i].place_of_working_name + ", "
							+ j[i].hos_address1 + " " + j[i].hos_address2 + " "
							+ j[i].hos_address3;
					name_per += ", " + j[i].name_of_res_p;
				}
			}
			$("#hos_name_add").val(hosp_list);
			$("#name_of_res_p").val(name_per);

		});

		$.post("getViewFromDegree?" + key + "=" + value, {

			id : id

		}, function(j) {
			var degree_list = "";
			for (var i = 0; i < j.length; i++) {
				if (i == 0) {
					var dg_name = j[i].degree_name;
					$("#degree").val(dg_name);
					dg_name = $("#degree option:selected").text();

					//							console.log($("#degree").attr('name'));
					degree_list += dg_name + ", " + j[i].name_of_institute
							+ " " + j[i].month_and_year_of_degree;
				} else
					degree_list += ", " + dg_name + ", "
							+ j[i].name_of_institute + " "
							+ j[i].month_and_year_of_degree;
			}
			$("#degree_uni_my").val(degree_list);

		});

		// 	$("#card_ view").show();

		window.print();

		document.body.innerHTML = originalContents;
		window.location.reload();

	}

	function formatDate(date) {
		var d = new Date(date), month = '' + (d.getMonth() + 1), day = ''
				+ d.getDate(), year = d.getFullYear();

		if (month.length < 2)
			month = '0' + month;
		if (day.length < 2)
			day = '0' + day;

		return [ day, month, year ].join('-');
	}

	//   console.log(formatDate('Sun May 11,2014'));

	function DownloadData(id) {

		$("#emp_id3").val(id);
		document.getElementById('typeReport').value = 'pdfL';
		document.getElementById('downloadForm').submit();
	}
</script>












