<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<link rel="stylesheet" 	href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- <script src="js/excel/xlsx-0.7.7.core.min.js"></script>   -->
<!-- <script src="js/excel/xls-0.7.4.core.min.js"></script>  -->
<script type="text/javascript" src="js/watermark/common.js"></script>

<link rel="stylesheet" href="assets/vendor/internal_css.css">

<section class="search_regulation Degree_Recognition">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<span id="lbladd">PG Student Details</span>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Degree Recognition</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->

		<div class="custom_v_tab">
			<div class="row">
				<div class="col-lg-12">
					<!-- input style start -->
					<div class="card-style mb-30">
						<div class="tabs content_h800">

							<!-- ===========================Form for Students Details PG(1)============================= -->

							<div class="tab tablinks dg-rec-block" id="tab_id2">
								<button class="tab-toggle">Students Admitted</button>
							</div>

							<div class="content tabcontent" id="Form_PG">
<%-- 								<form id="Form_PG_details"> --%>

	
						       
									<div class="col-lg-4 col-md-4 col-sm-12 col-12">
										<div class="input-style-2">
											<input type="hidden" id="h_id" name="h_id" value="a2" class="form-control"> 
												<input type="hidden" id="form_pg_id" name="form_pg_id" value="0"
												class="form-control autocomplete">
										</div>
									</div>
									<h3 class="heading">Number Of Students Admitted As regular
										Candidate (Year & Subject Wise)</h3>
									<div class="row">
																	<div class="col-12 col-sm-12 col-md-12 col-lg-12">
											<div class="custom-choose-one">
												<div class="input-style-2 mb-0">
													<h5 class="mb-2">Choose Any One</h5>
												</div>
												<div class="input-style-form-check">
													<div class="form-check radio-style"> <!-- onclick="UploadExcel(); -->
														<input type="radio" class="form-check-input" id="Upload"
															name="Choise" value="Upload"> <label for="Upload"
															class="form-check-label">Upload Through Excel</label>
													</div>
													<div class="form-check radio-style" > <!-- onclick="fillform();" -->
														<input type="radio" class="form-check-input" id="Fillform"
															name="Choise" value="Fillform" checked="checked">
														<label class="form-check-label" for="ST">Fill Up
															Form</label>
													</div>
												</div>
											</div>
											<input type="hidden" id="actiontype" name="actiontype"
												value="add"> <input type="hidden" id="seid"
												name="seid" value="0">
										</div>
									</div>
									<div class="vtab-main-block">
										<div id="UploadExcel" class="hide">
											<div class="row">


												<div class="col-12 col-sm-12 col-md-6 col-lg-4">
													<div class="input-style-2">
														<label>Downlaod Format</label>
														<ul class="buttons-group">
															<li><a type="button" id="downlaod"
																class="main-btn info-btn square-btn btn-hover  mr-5"
																value="EXPORT EXCEL"> <i
																	class="lni lni-download mr-5"></i> Export Excel
															</a></li>


														</ul>
													</div>
												</div>

												<div class="col-12 col-sm-12 col-md-6 col-lg-4">
													<div class="input-style-2">
														<label>Upload Excel<span class="mandatory">*</span></label>
														<input type="file" class="form-control" accept=".xlsx"
															placeholder="upload your document" name="upload_excel"
															id="upload_excel" tabindex="5"> <span
															class='errorClass' id='upload_excel_lbl'>${upload_excel_lbl_msg}</span>
													</div>
												</div>
											</div>
										</div>
<form:form name="Form_PG_details" id="Form_PG_details" action="Student_Details_PG_Action" method="post"
						       modelAttribute="PG_Form_student_CMD" enctype="multipart/form-data">
										<div id="fillform">
											<div class="row">
													
											
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-2">
														<label>Name Of the Student <strong
															class="mandatory">*</strong></label> <input type="text"
															name="student_name" id="student_name"
															class="form-control" placeholder="Enter name of Students" autocomplete="off" onkeypress="return onlyAlphabets(event);">
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-2">
														<label>Date Of Admission <strong class="mandatory">*</strong>
														</label> <input type="text" name="date_of_admission"
															id="date_of_admission" class="form-control"
															aria-required="true" autocomplete="off"
															value="DD/MM/YYYY">
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-2">
														<label>Date of Registration<strong
															class="mandatory">*</strong>
														</label> <input type="text" name="date_of_registration"
															id="date_of_registration" class="form-control"
															aria-required="true" autocomplete="off" maxlength="10"
															value="DD/MM/YYYY">
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="select-style-1">
														<label for="username">University For The Course<span
															class="mandatory">*</span></label>
														<div class="select-position">
															<select name="course_name" id="course_name" class="singleselect form-control form-control-lg">
																<option value="">Course Name</option>
																<c:forEach var="item" items="${course_name}"
																	varStatus="num">
																	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>Neet Rank<strong class="mandatory">*</strong></label>
													<div class="form-check radio-style">
														 <input type="radio" id="neet_rank1" name="neet_rank"
															class="form-check-input" value="Yes"> 
															<label class="form-check-label" for="radio-1">Yes</label>
													</div>
													<div class="form-check radio-style">
														 <input type="radio" name="neet_rank" id="neet_rank2"
															class="form-check-input" value="No"> <label class="form-check-label" for="radio-1">No</label>
													</div>
												</div>
											</div>
						<div class="custom-d-none" id="rank_div">
							<div class="row">
								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="input-style-2">
										<label>Rank<strong class="mandatory">*</strong></label> 
											<div class="with_icon">
											<input type="text" name="rank" id="rank" class="form-control"
														placeholder="Enter Rank"
														autocomplete="off" maxlength="5">
											 </div> 
									</div>
								</div>
							</div>
						</div>
											
<!-- 												<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 													<div class="input-style-2"> -->
<!-- 														<label>Rank<strong class="mandatory">*</strong></label> <input -->
<!-- 															name="rank" id="rank" class="rank" -->
<!-- 															placeholder="Enter Rank" autocomplete="off"> -->
<!-- 													</div> -->
<!-- 												</div> -->
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-2">
														<label>Marks<strong class="mandatory">*</strong></label> <input
															type="text" name="marks" id="marks" class="form-control"
															placeholder="Enter Marks" autocomplete="off" maxlength="3">
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">

													<div class="input-style-form-check">
														<label>All India Quota<strong class="mandatory">*</strong></label>
														<div class="form-check radio-style">

															<input type="radio" name="all_india" id="all_india1" 
																class="form-check-input" value="Yes"> <label
																class="form-check-label" for="radio-1"> Yes</label>
														</div>
														<div class="form-check radio-style">

															<input type="radio" name="all_india" id="all_india2"
																class="form-check-input" value="No"> <label
																class="form-check-label" for="radio-1"> No</label>
														</div>
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-form-check">
														<label>State Quota<strong class="mandatory">*</strong></label>
														<div class="form-check radio-style">

															<input type="radio" name="state" id="state1"
																class="form-check-input" value="Yes"> <label
																class="form-check-label" for="radio-1">Yes</label>
														</div>
														<div class="form-check radio-style">

															<input type="radio" name="state" id="state2"
																class="form-check-input" value="No"> <label
																class="form-check-label" for="radio-1"> No</label>
														</div>
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>Management Quota<strong class="mandatory">*</strong></label>
													
													<div class="form-check radio-style">
														<input type="radio" name="management_quota" id="management_quota"
															class="form-check-input" value="Yes"><label class="form-check-label" for="radio-1">Yes</label>
													</div>
													<div class="form-check radio-style">
														 <input type="radio" name="management_quota" id="management_quota"
															class="form-check-input" value="No"><label class="form-check-label" for="radio-1">No</label>
													</div>
												</div>
											</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-form-check">
														<label>Admission Authority<strong
															class="mandatory">*</strong></label>
														<div class="form-check radio-style">

															<input type="radio" name="admission_authority"
																id="admission_authority1" class="form-check-input"
																value="Yes"> <label class="form-check-label"
																for="radio-1">Yes</label>
														</div>
														<div class="form-check radio-style">

															<input type="radio" name="admission_authority"
																id="admission_authority2" class="form-check-input"
																value="No"> <label class="form-check-label"
																for="radio-1">No</label>
														</div>
													</div>
												</div>

											<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-form-check">
														<label>Court Order and Others<strong
															class="mandatory">*</strong></label>
														<div class="form-check radio-style">

															<input type="radio" name="court_order"
																id="court_order1" class="form-check-input"
																value="Yes"> <label class="form-check-label"
																for="radio-1">Yes</label>
														</div>
														<div class="form-check radio-style">

															<input type="radio" name="court_order"
																id="court_order2" class="form-check-input"
																value="No"> <label class="form-check-label"
																for="radio-1">No</label>
														</div>
													</div>
												</div>
													<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label for="username">Upload The Copy Of Court Order<span class="mandatory">*</span></label> 
												<div class="with_icon">
												<input type="file" accept=".pdf"
												id="court_order_file_upload_pg" name="court_order_file_upload_pg" class="form-control"
												autocomplete="off" />
												</div>
										</div>
									</div>
													
											
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-2">
														<label>Qualification Name <strong
															class="mandatory">*</strong>
														</label> <input type="text" name="qualification_name"
															id="qualification_name" maxlength="10"
															class="form-control"
															placeholder="Enter Name Of Awarding Authority" autocomplete="off">

													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-2">
														<label>Year Of Award For Admission To M.D.(Hom.)<strong
															class="mandatory">*</strong></label> <input type="month"
															name="year_of_award_admission"
															id="year_of_award_admission" class="form-control"
															placeholder="Enter Year Of Award" autocomplete="off">

													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-2">
														<label>Date of Completion Of Internship <strong
															class="mandatory">*</strong>
														</label> <input type="text" name="date_of_completion_internship"
															id="date_of_completion_internship" maxlength="10"
															class="form-control-sm form-control effect-9 "
															aria-required="true" autocomplete="off"
															value="DD/MM/YYYY">
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-2">
														<label>Date of Registration With State <strong
															class="mandatory">*</strong>
														</label> <input type="text" name="date_of_registration_state"
															id="date_of_registration_state" maxlength="10"
															class="form-control-sm form-control effect-9 "
															aria-required="true" autocomplete="off"
															value="DD/MM/YYYY">
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-2">
														<label>Date of Completion Of MD Part-1 <strong
															class="mandatory">*</strong>
														</label> <input type="text" name="date_of_completion_md_part1"
															id="date_of_completion_md_part1" maxlength="10"
															class="form-control-sm form-control effect-9 "
															aria-required="true" autocomplete="off"
															value="DD/MM/YYYY">
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-2">
														<label>Date of Completion Of MD Part-2 <strong
															class="mandatory">*</strong>
														</label> <input type="text" name="date_of_completion_md_part2"
															id="date_of_completion_md_part2" maxlength="10"
															class="form-control-sm form-control effect-9 "
															aria-required="true" autocomplete="off"
															value="DD/MM/YYYY">
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-2">
														<label>Date of Declaration Of MD(Hom.)Part-2 <strong
															class="mandatory">*</strong>
														</label> <input type="text" name="date_of_declaration_of_md"
															id="date_of_declaration_of_md" maxlength="10"
															class="form-control-sm form-control effect-9 "
															aria-required="true" autocomplete="off"
															value="DD/MM/YYYY">
													</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-2">
														<label>Remarks<strong></strong></label> <input
															type="text" name="remarks" id="remarks"
															class="form-control" placeholder="Enter Remarks, if any" autocomplete="off">
													</div>
												</div>
											</div>
										
										</div>
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<ul class="buttons-group mainbtn">
													<li><input type="button" id="btn-save-exc"
														class="main-btn info-btn btn-hover" value="Save as Draft">
														<li><a href="Student_Details_PG" class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
												</ul>
												<input type="hidden" id="userId" name="userId"
													value="${userId}"> <input type="hidden" id="status"
													name="status" value="0">
											</div>
										</div>
										</form:form>
									</div>
									
<%-- 								</form> --%>

							</div>

							<!-- ===========================Form for Students Details PG(2)============================= -->

							<div class="tab tablinks d-none" id="tab_id4" >
								<button class="tab-toggle">Details Of Guides And
									Examiners For M.D.(HOM) Course/Any Other Post Graduate Course</button>
							</div>

							<div class="content tabcontent" id="Form_PG2">
								<form id="Form_PG_details2">
									<h3 class="heading">Details Of Guides And Examiners For
										M.D.(HOM) Course/Any Other Post Graduate Course</h3>
									<div id="fillform">
										<div class="row">
										<input type="hidden"id="form_pg_id2" name="form_pg_id2" value="0" class="form-control autocomplete">
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label>Name Of Homoeopathic Medical College<strong
														class="mandatory">*</strong></label>
													<div class="select-position">
														<select name="name_of_homoeopathic_medical_college"
															id="name_of_homoeopathic_medical_college"
															class="form-control">
															<option value="0">---Select---</option>
															<c:forEach var="item" items="${getInstituteListbyUserID}"
																varStatus="num">
																<option value="${item.institute_name}"
																	name="${item.institute_name}">${item.institute_name}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Name Of Guide<strong
														class="mandatory">*</strong></label> <input type="text"
														name="name_of_guide" id="name_of_guide"
														class="form-control" placeholder="Name Of Guide/Co-Guide" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Name Of Students<strong
														class="mandatory">*</strong>
													</label> <input type="text" name="name_of_student_for_guide"
														id="name_of_student_for_guide" maxlength="10"
														class="form-control-sm form-control effect-9"
														placeholder="Enter Name Of Student For Guide" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Topic Of Dissertation<strong
														class="mandatory">*</strong>
													</label> <input type="text" name="topic_of_dissertation"
														id="topic_of_dissertation" class="form-control"
														placeholder="Enter Topic Of Dissertation" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>Whether Guide Participated In Examination<strong class="mandatory">*</strong></label>
														<div class="form-check radio-style">
															
															<input type="radio" id="whether_guide_participated_in_examination1" 
																	name="whether_guide_participated_in_examination" class="form-check-input" value="Yes">
																	<label class="form-check-label" for="radio-1"> Yes</label>
														</div>
														<div class="form-check radio-style">
															
															<input type="radio" name="whether_guide_participated_in_examination"
																	 id="whether_guide_participated_in_examination2" class="form-check-input" value="No">
																<label class="form-check-label" for="radio-1"> No</label>
														</div>
													</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date Of Submission<strong class="mandatory">*</strong>
													</label> <input type="text" name="date_of_submission"
														id="date_of_submission" class="form-control"
														placeholder="Enter Date Of Submission"
														aria-required="true" autocomplete="off" value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date Of Acceptance<strong
														class="mandatory">*</strong>
													</label> <input type="text" name="date_of_acceptance"
														id="date_of_acceptance" class="form-control"
														placeholder="Enter Date Of Submission"
														aria-required="true" autocomplete="off" value="DD/MM/YYYY">
												</div>
											</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>Whether Student published Article From Dissertation<strong class="mandatory">*</strong></label>
														<div class="form-check radio-style">
															<input type="radio" id="whether_student_published_article1" name="whether_student_published_article" class="form-check-input" value="Yes">
																 <label class="form-check-label" for="radio-1">Yes</label>
														</div>
														<div class="form-check radio-style">
															
															<input type="radio" name="whether_student_published_article" id="whether_student_published_article2" class="form-check-input" value="No">
																  <label class="form-check-label" for="radio-1">No</label>
														</div>
													</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>If Yes,Mention The Details<strong class="mandatory">*</strong></label>
													<div class="form-check radio-style">
														
															<input type="radio" id="mention_details1" name="mention_details" class="form-check-input" value="Yes" > 
															<label class="form-check-label" for="radio-1">Yes</label></div>
															<div class="form-check radio-style">
															<input type="radio" id="mention_details2" name="mention_details" value="No" class="form-check-input" >
															<label class="form-check-label" for="radio-1">No</label></div>
												</div>
											</div>
							<div class="custom-d-none" id="mention_div">
											<div class="row">
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Article Title<strong
														class="mandatory">*</strong></label> <input type="text"
														name="article_title" id="article_title" maxlength="10"
														class="form-control-sm form-control effect-9"
														aria-required="true" autocomplete="off">
												</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Month And Year<strong
														class="mandatory">*</strong></label> 
														<input type="month" name="month_year_exam" id="month_year_exam" autocomplete="off" class="form-control"
														placeholder="Enter Result" autocomplete="off">
												</div>
												</div>
												
												</div>
											</div>
											<ul class="buttons-group mainbtn">
												<li><input type="button" id="btn-save2" class="main-btn info-btn btn-hover" value="Save as Draft">
													<input type="hidden" id="userId" name="userId" value="${userId}"> 
													<input type="hidden" id="status" name="status" value="0">
												<li>

											</ul>
											
										</div>
									</div>
								</form>
							</div>

							<!-- ===========================End of Form for Students Details PG(2)============================= -->

							<!-- ===========================Form for Students Details PG(3)============================= -->


							<div class="tab tablinks dg-rec-block" id="tab_id8">
								<button class="tab-toggle">Work Done by Post Graduate
									Students</button>
							</div>

							<div id="Form_3" class="content tabcontent">
								<div class="">
									<ul id="tabs" class="nav nav-tabs" role="tablist">
										<li class="nav-item" id="tab_id5">
										<a id="Form_3_a" href="#form-A" class="nav-link active" data-toggle="tab"
											role="tab">A.Disseration Topic</a>
										</li>
											
										<li class="nav-item dg-rec-block" id="tab_id5">
											<a id="Form_3_b" href="#form-B" class="nav-link"
											data-toggle="tab" role="tab">B.Lacture taken</a>
										</li>
										
										<li class="nav-item dg-rec-none" id="tab_id5">
											<a id="Form_3_c" href="#form-C" class="nav-link"
											data-toggle="tab" role="tab">C.Assignment</a>
										</li>
										
										<li class="nav-item dg-rec-block" id="tab_id5">
											<a id="Form_3_d" href="#form-D" class="nav-link"
											data-toggle="tab" role="tab">C.Persentations given</a>
										</li>
										
									</ul>

									<!-- 									Form PG(3)(a) -->

									<div id="content" class="tab-content" role="tablist">
										<div id="form-A" class="tab-pane fade show active"
											role="tabpanel" aria-labelledby="Form_3_a">


									<div id="collapse-A" class="collapse show" data-parent="#content" role="tabpanel" aria-labelledby="heading-A">
												<div class="tabcontent" id="Form_3_a">
													<form id="Form_PG3a_details">
														<h4 class="heading mt-3">Disseratation Topic of P.G.
															Student-Year wise/Depratment wise</h4>
															<div class="table-wrapper table-responsive simple-table">
														<table class="table" id="family_table">
																<thead>
																	<tr>
																		<th rowspan="2"><h6>Ser No</h6></th>
																		<th rowspan="2"><h6>Name of student</h6></th>
																		<th colspan="2"><h6>Duration of house job Dates</h6></th>
																		<th rowspan="2"><h6>Name of Topic</h6></th>
<!-- 																		<th rowspan="2"><h6>Outcomes/Result/Conclusion obtained from disseration</h6></th> -->
																		<th rowspan="2"><h6>Date of submission</h6></th>
																		<th rowspan="2"><h6>Any publication from Disseration(Yes/No)</h6></th>
																		<th rowspan="2"><h6>If yes,mention(article title/journal)</h6></th>
																		<th rowspan="2"><h6>Action</h6></th>
																	</tr>
																	<tr>
																		<th><h6>From date</h6></th>
																		<th><h6>To date</h6></th>
																	</tr>
																</thead>
																<tbody id="family_sibtbody">
													<tr id="tr_sibling1">
														<td>1</td>
																		<td>
																			<div class="">
																				<div class="input-style-2">
																					<input type="text" name="student_name_pg1"
																						id="student_name_pg1" class="form-control"
																						placeholder="Enter student name" autocomplete="off">
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="">
																				<div class="input-style-2">
																					<input type="text" name="from_date1" id="from_date1"
																						maxlength="10" class="form-control-sm form-control effect-9 "
																						aria-required="true" autocomplete="off"
																						value="DD/MON/YYYY"
																						placeholder="From Date Of House Job">
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="">
																				<div class="input-style-2">
																					<input type="text" name="to_date1" id="to_date1"
																						maxlength="10" class="form-control-sm form-control effect-9 "
																						aria-required="true" autocomplete="off"
																						value="DD/MON/YYYY"
																						placeholder="To Date Of House Job">
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="">
																				<div class="input-style-2">
																					<input type="text" name="name_of_topic1"
																						id="name_of_topic1" class="form-control"
																						placeholder="Enter Name Of Topic" autocomplete="off">
																				</div>
																			</div>
																		</td>
<!-- 																		<td> -->
<!-- 																			<div class="input-style-2"> -->
<!-- 																				<input type="text" name="conclusion_obtain1" -->
<!-- 																					id="conclusion_obtain1" class="form-control" -->
<!-- 																					placeholder="Enter conclusion obtain" autocomplete="off"> -->
<!-- 																			</div> -->
<!-- 																		</td> -->
																		<td>
																			<div class="input-style-2">
																				<input type="text" name="date_of_submission_pg1"
																					id="date_of_submission_pg1" maxlength="10"
																					class="form-control-sm form-control effect-9 " aria-required="true"
																					autocomplete="off" value="DD/MON/YYYY"
																					placeholder="Enter Date Of submission">
																			</div>
																		</td>
																		<td>	
																			<div class="input-style-2">
																				<input type="text" name="publication1"
																					id="publication1" class="form-control"
																					placeholder="Enter publication" autocomplete="off">
																			</div>
																		</td>
																		<td>
																			<div class="input-style-2">
																				<input type="text" name="mention_article1"
																					id="mention_article1" class="form-control"
																					placeholder="Enter Theory & Practicals Including Viva-Voce" autocomplete="off">
																			</div>
																		</td>
						<td class="hide-action">
						<ul class="buttons-group multi-btn-group">
						<li><a class="main-btn info-btn-outline btn-hover btn-sm" value="Save" title="SAVE" id="familyA_save1" ><i class="lni lni-checkmark"></i></a></li>
 						<li><a class="main-btn success-btn-outline btn-hover btn-sm dg-rec-none"  value="ADD" title="ADD" id="Disseratation_Topic_add1"><i class="lni lni-plus"></i></a></li>
						<li><a class="main-btn danger-btn-outline btn-hover btn-sm dg-rec-none"  value="Delete" title="Delete" id="Disseratation_Topic_remove1" ><i class="lni lni-trash-can"></i></a></li>
						
					</ul>
			        </td>
												</tr>
											</tbody>
															</table>

															<!-- 				end table -->
														</div>
			<input type="hidden" id="forma_id1" name="forma_id1" value="0" class="form-control autocomplete" autocomplete="off">

													</form>
												</div>
											</div>
										</div>

										<!-- Form PG(3)(b) -->

										<div id="form-B" class="tab-pane fade show" role="tabpanel"
											aria-labelledby="Form_3_b">

											<div id="collapse-B" class="collapse show"
												data-parent="#content" role="tabpanel"
												aria-labelledby="heading-B">
												<div id="Form_3_b">
													<form id="Form_PG3b_details">
														<input type="hidden" id="form_pg_id3b" name="form_pg_id3b"
															value="0" class="form-control autocomplete">
														<h4 class="heading mt-3">Lecture Taken By P.G.
															Students,Batch</h4>
														<div class="table-wrapper table-responsive custom-table">
															<table class="table" id="att_Tbb">
															
																<thead>
																	<tr>
																		<th rowspan="2"><h6>S.No</h6></th>
																		<th rowspan="2"><h6>Name of student</h6></th>
																		<th rowspan="2"><h6>Class</h6></th>
																		<th rowspan="2"><h6>Date</h6></th>
																		<th rowspan="2"><h6>Day</h6></th>
																		<th rowspan="2"><h6>Time</h6></th>
																		<th rowspan="2"><h6>Topic</h6></th>
																		<th rowspan="2"><h6>Action</h6></th>
																	</tr>
																</thead>
																<tbody id="att_Tbbody">
																	<tr id="tr_id_attb">
																		<td>1</td>
																		<td>
																			<div class="">
																				<div class="input-style-2">
																					<input type="text" name="student_name_lec1"
																						id="student_name_lec1" class="form-control"
																						placeholder="Enter student name" autocomplete="off">
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="">
																				<div class="input-style-2">
																					<input type="text" name="student_class_lec1"
																						id="student_class_lec1" class="form-control"
																						placeholder="Enter Class" autocomplete="off">
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="">
																				<div class="input-style-2">
																					<input type="text" name="lecture_date1" id="lecture_date1"
																						maxlength="10" class="form-control-sm form-control effect-9 "
																						aria-required="true" autocomplete="off"
																						value="DD/MON/YYYY"
																						placeholder="Enter Date">
																				</div>
																			</div>
																			
																		</td>
																		<td>
																			<div class="">
																				<div class="input-style-2">
																					<input type="text" name="lecture_day1"
																						id="lecture_day1" class="form-control"
																						placeholder="Enter Day" autocomplete="off"> 
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="">
																				<div class="input-style-2">
																					<input type="time" name="lecture_time1"
																						id="lecture_time1" class="form-control"
																						placeholder="Enter Time" autocomplete="off">
																				</div>
																			</div>
																		</td>
																		<td>
																			<div class="">
																				<div class="input-style-2">
																					<input type="text" name="topic1"
																						id="topic1" class="form-control"
																						placeholder="Enter Topic" autocomplete="off">
																				</div>
																			</div>
																		</td>
																		<td class="hide-action">
																			<ul class="buttons-group multi-btn-group">
																				<li><a
																					class="main-btn info-btn-outline btn-hover btn-sm"
																					value="Save" title="SAVE" id="familyB_save1"><i
																						class="lni lni-checkmark"></i></a></li>
																				<li><a
																					class="main-btn success-btn-outline btn-hover btn-sm dg-rec-none"
																					 value="ADD" title="ADD"
																					id="Form3b_Students_add1"><i
																						class="lni lni-plus"></i></a></li>
																				<li><a
																					class="main-btn danger-btn-outline btn-hover btn-sm dg-rec-none"
																					 value="Delete"
																					title="Delete" id="Form3b_Students_remove1"><i
																						class="lni lni-trash-can"></i></a></li>
																			</ul>
																		</td>
																	</tr>
																</tbody>
															</table>

					<input type="hidden" id="sib_ch_idb1" name="sib_ch_idb1" value="0" class="form-control autocomplete"> 
														</div>
													</form>
												</div>
											</div>
										</div>

										<div id="form-C" class="tab-pane fade show" role="tabpanel"
											aria-labelledby="Form_3_c" class="d-none">

											<div id="collapse-C" class="collapse show" role="tabpanel"
												data-parent="#content" aria-labelledby="heading-C">
												<div id="Form_3_c">
													<form id="Form_PG3c_details">
														<div class="row">
															<div class="col-12 col-sm-12 col-md-6 col-lg-12">
																<div class="input-style-2 mb-0">
																	<h3 class="mb-2"></h3>
																</div>

																<div class="row">
																	<div
																		class="table-wrapper table-responsive simple-table">
																		<table class="table" id="family_table_c">
																			<thead>
																				<tr>
																					<th rowspan="2"><h6>S.No</h6></th>
																					<th rowspan="2"><h6>Name of student</h6></th>
																					<th rowspan="2"><h6>List Of Assignment</h6></th>
																					<th rowspan="2" class="hide-action"><h6>Action</h6></th>
																				</tr>
																			</thead>
																			<tbody id="family_sibtbody">
																				<tr id="tr_sibling2">
																					<td>1</td>
																					<td>
																						<div class="">
																							<div class="input-style-2">
																								<input type="text" name="student_name_formc1"
																									id="student_name_formc1" class="form-control"
																									placeholder="Enter student name" autocomplete="off">
																							</div>
																						</div>
																					</td>

																					<td>
																						<div class="">
																							<div class="input-style-2">
																								<input type="text"
																									name="list_of_assignment_formc1"
																									id="list_of_assignment_formc1"
																									class="form-control"
																									placeholder="Enter List Of Assignment" autocomplete="off">
																							</div>
																						</div>
																					</td>


										<td class="hide-action">
											<ul class="buttons-group multi-btn-group">
												<li><a
													class="main-btn info-btn-outline btn-hover btn-sm"
													value="Save" title="SAVE" id="family_savec1"><i
														class="lni lni-checkmark"></i></a></li>
												<li><a
													class="main-btn success-btn-outline btn-hover btn-sm dg-rec-none"
												 value="ADD" title="ADD"
													id="list_of_assignment_add1"><i
														class="lni lni-plus"></i></a></li>
												<li><a
													class="main-btn danger-btn-outline btn-hover btn-sm dg-rec-none"
													 value="Delete" title="Delete" id="list_of_assignment_remove1"><i
														class="lni lni-trash-can"></i></a></li>
											</ul>
										</td>
																</tr>
															</tbody>
														</table>
<input type="hidden" id="sib_ch_idc1" name="sib_ch_idc1" value="0" class="form-control autocomplete"> 
													</div>
												</div>
											</div>
										</div>
									</form>
							</div>
						</div>
					</div>

<div id="form-D" class="tab-pane fade show" role="tabpanel"
	aria-labelledby="Form_3_d">

	<div id="collapse-D" class="collapse show" role="tabpanel"
		data-parent="#content" aria-labelledby="heading-D">
		<div class="" id="Form_3_d">
			<form id="Form_PG3c_details">
				<div class="row">
					<div class="col-12 col-sm-12 col-md-6 col-lg-12">
						<div class="input-style-2 mb-0">
							<h3 class="mb-2"></h3>
						</div>

						<div class="row">
							<div
								class="table-wrapper table-responsive simple-table">
								<table class="table" id="family_table3">
									<thead>
										<tr>
											<th rowspan="2"><h6>S.No</h6></th>
											<th rowspan="2"><h6>Name of student</h6></th>
											<th rowspan="2"><h6>List Of Presentations</h6></th>
											<th rowspan="2"><h6>Title Of The Seminar Attended/Topic</h6></th>
											<th rowspan="2"><h6>Action</h6></th>
										</tr>
									</thead>
									<tbody id="family_sibtbody">
										<tr id="tr_sibling3">
											<td>1</td>
											<td>
												<div class="">
													<div class="input-style-2">
														<input type="text" name="student_name_presen1"
															id="student_name_presen1" class="form-control"
															placeholder="Enter student name" autocomplete="off">
													</div>
												</div>
											</td>

											<td>
												<div class="">
													<div class="input-style-2">
														<input type="text" name="list_of_presentations1"
															id="list_of_presentations1"
															class="form-control"
															placeholder="Enter List Of Presentations" autocomplete="off">
													</div>
												</div>
											</td>
											<td>
												<div class="">
													<div class="input-style-2">
														<input type="text"
															name="title_of_seminar_attended1"
															id="title_of_seminar_attended1"
															class="form-control"
															placeholder="Enter Title Of Seminar Attended" autocomplete="off">
													</div>
												</div>
											</td>


											<td class="hide-action">
												<ul class="buttons-group multi-btn-group">
													<li><a
														class="main-btn info-btn-outline btn-hover btn-sm"
														value="Save" title="SAVE" id="family_save1"><i
															class="lni lni-checkmark"></i></a></li>
													<li><a
														class="main-btn success-btn-outline btn-hover btn-sm dg-rec-none"
														 value="ADD" title="ADD"
														id="list_of_presentation_given_add1" ><i
															class="lni lni-plus"></i></a></li>
													<li><a
														class="main-btn danger-btn-outline btn-hover btn-sm dg-rec-none"
														 value="Delete"
														title="Delete"
														id="list_of_presentation_given_remove1"><i class="lni lni-trash-can"></i></a></li>
																			</ul>
																		</td>
																	</tr>
																</tbody>
															</table>
<input type="hidden" id="formd_id1" name="formd_id1" value="0" class="form-control autocomplete"> 
														</div>
													</div>
												</div>
											</div>
										</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

							<!--===========================End of Form for Students Details PG(3)============================= -->

						</div>
					</div>
				</div>
			</div>
			<!--</body> -->
		</div>
	</div>

</section>
<script type="text/javascript"
	src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>
<c:url value="Stu_PG__Exp_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">


$(document).ready(function() {
	$("div#fillform").show();
	$("div#UploadExcel").hide();
	
        if(window.location.href.includes("msg"))
        {
                 var url = window.location.href.split("?msg")[0];
                 window.location = url;
        }
        datepicketDate('date_of_admission');
        datepicketDate('date_of_registration');
        datepicketDate('date_of_registration_state');
        datepicketDate('date_of_completion_md_part1');
        datepicketDate('date_of_completion_md_part2');
        datepicketDate('date_of_declaration_of_md');
        datepicketDate('date_of_enroll_university');
        datepicketDate('date_of_intern_compl');
        datepicketDate('date_of_result_final_year');
        datepicketDate('date_of_starting_internship');
        datepicketDate('date_of_completion_internship');
        datepicketDate('dt_of_migration1');
        datepicketDate('date_of_completion_internship');
        datepicketDate('date_of_submission');
        datepicketDate('date_of_acceptance');
        datepicketDate('whether_examiner_approved_university');
        datepicketDate('lecture_date1');

        datepicketDate('from_date1');
        datepicketDate('to_date1');
        datepicketDate('date_of_submission_pg1');
        	
        
        mockjax1('search_system');
    	table1 = dataTable('search_system');
    	
    	mockjax1('search_system_intern');
		table3 = dataTable('search_system_intern');
		
});

// $("#date_of_admission").datepicker({
// 	dateFormat: 'dd-mm-yy',
// 	maxDate: new Date(),
// 	changeMonth: true,
// 	changeYear: true,
// 	yearRange: '1890:2099',
// 	showButtonPanel: true,
// beforeShowDay: disableDays 

// });

//function datepicketDate(date_of_admission,date_of_registration){
//	$('#'+date_of_admission,date_of_registration).datepicker({showOn: 'both',
////			buttonImageOnly: false,
////			buttonImage: 'js/Calender/calendar.png',			
//	buttonText: '<span class="icon"><i class="lni lni-calendar"></i></span>',
//	showButtonPanel: false,
//	dateFormat: 'dd/mm/yy',
//	changeMonth: true,
//	changeYear: true,
//	yearRange: '1890:2099',
//	maxDate: new Date()
//});
//}

// $("#date_of_admission").datepicker({
// 	dateFormat: 'dd-mm-yy',
// 	changeMonth: true,
// 	changeYear: true,
// 	yearRange: '1890:2099',
// 	showButtonPanel: true,
//     beforeShowDay: disableDays 
// });
 
function onlyAlphabets(e, t) {
	       return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || e.charCode == 32;   
	   }
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
function UploadExcel() {
  	
   	$("div#UploadExcel").show();
   	$("div#fillform").hide();
    $("#h_id").val("a1");
}
function fillform() {

  	$("div#fillform").show();
  	$("div#UploadExcel").hide();
    $("#h_id").val("a2");
}
function getExcel() {

	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('search2').submit();
}
$('#name_of_college').on('change', function() {
	var ss = $("#name_of_college").find('option:selected').attr("name");
	$("#institute_name_new").val(ss);
});

document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn-save-exc').onclick = function(){
		if(Save_as_draft_PG()){
			$("#Form_PG_details").submit();
			}
	};
	
	document.getElementById('btn-save2').onclick = function(){
		Save_as_draft_PG2();
	};
	document.getElementById('downlaod').onclick = function(){
		getExcel();
	};
	document.getElementById('Upload').onclick = function(){
		UploadExcel();
	};
	document.getElementById('Fillform').onclick = function(){
		fillform();
	};
	document.getElementById('mention_details1').onclick = function(){
		Supplementry();
	};
	document.getElementById('mention_details2').onclick = function(){
		Supplementry();
	};
	//------1------
	document.getElementById('familyA_save1').onclick = function(){
		Disseratation_Topic(1);
	};
	document.getElementById('Disseratation_Topic_add1').onclick = function(){
	    Disseratation_Topic_add_fn(1);
	};
	document.getElementById('Disseratation_Topic_remove1').onclick = function(){
		Disseratation_Topic_remove_fn(1);
	};
	//------2------
	document.getElementById('familyB_save1').onclick = function(){
		Form3b_Students(1);
	};
	document.getElementById('Form3b_Students_add1').onclick = function(){
		Form3b_Students_add_fn(1);
	};
	document.getElementById('Form3b_Students_remove1').onclick = function(){
 		Form3b_Students_remove_fn(1);
	};
	//------3------
	document.getElementById('family_savec1').onclick = function(){
		list_of_assignment(1);
	};
	document.getElementById('list_of_assignment_add1').onclick = function(){
		list_of_assignment_add_fn(1);
	};
	document.getElementById('list_of_assignment_remove1').onclick = function(){
		list_of_assignment_remove_fn(1);
	};
	//------4------
	document.getElementById('family_save1').onclick = function(){
		list_of_presentation_given(1);
	};
	document.getElementById('list_of_presentation_given_add1').onclick = function(){
		list_of_presentation_given_add_fn(1);
	};
	document.getElementById('list_of_presentation_given_remove1').onclick = function(){
		list_of_presentation_given_remove_fn(1);
	};
	document.getElementById('student_name').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('rank').onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('neet_rank1').onclick = function(){
		rank_detail();
	};
	document.getElementById('neet_rank2').onclick = function(){
		rank_detail();
	};
	document.getElementById('marks').onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('qualification_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('name_of_guide').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('name_of_student_for_guide').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('topic_of_dissertation').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('student_name_pg1').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('conclusion_obtain1').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('publication1').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('mention_article1').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('name_of_topic1').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('student_name_lec1').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('topic1').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('student_name_formc1').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('list_of_assignment_formc1').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('student_name_presen1').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('list_of_presentations1').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('title_of_seminar_attended1').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	
});

function dissertation(sib){
	
	document.getElementById('familyA_save'+sib).onclick = function() {
		Disseratation_Topic(sib);
	};
	document.getElementById('Disseratation_Topic_add'+sib).onclick = function() {
		Disseratation_Topic_add_fn(sib);
	};
	document.getElementById('Disseratation_Topic_remove'+sib).onclick = function() {
		Disseratation_Topic_remove_fn(sib);
	};
}
function lacture(sibb){

	document.getElementById('familyB_save'+sibb).onclick = function() {
		Form3b_Students(sibb);
	};
	document.getElementById('Form3b_Students_add'+sibb).onclick = function() {
		Form3b_Students_add_fn(sibb);
	};
	document.getElementById('Form3b_Students_remove'+sibb).onclick = function() {
		Form3b_Students_remove_fn(sibb);
	};
}
function assignment(sibc){

	document.getElementById('family_savec'+sibc).onclick = function() {
		list_of_assignment(sibc);
	};
	document.getElementById('list_of_assignment_add'+sibc).onclick = function() {
		list_of_assignment_add_fn(sibc);
	};
	document.getElementById('list_of_assignment_remove'+sibc).onclick = function() {
		list_of_assignment_remove_fn(sibc);
	};}
function presentation(sibd){

	document.getElementById('family_saved'+sibd).onclick = function() {
		list_of_presentation_given(sibd);
	};
	document.getElementById('list_of_presentation_given_add'+sibd).onclick = function() {
		list_of_presentation_given_add_fn(sibd);
	};
	document.getElementById('list_of_presentation_given_remove'+sibd).onclick = function() {
		list_of_presentation_given_remove_fn(sibd);
	};
}


//////////////////Save Form PG(1) //////////////////////

function Save_as_draft_PG() {
	/* if($("#form_b_id").val()==""){
		  alert("Please Enter Admitted Students Detail");
          return false;
	} */
// 	if($("#h_id").val()=="a1"){
// 		if($("#upload_excel").val() == 0){
//     		alert("Please Upload Excel File");
//     		$("input#upload_excel").focus();
//     		return false;
//     		}
// 		}
// 	else if($("#h_id").val()=="a2"){
		           if($("input#student_name").val().trim() == "") {
	               alert("Please Enter Student Name.");
	               $("input#student_name").focus();
	               return false;
    		 	 }
	        	 else if($("input#date_of_admission").val().trim()=="DD/MM/YYYY"){
	                    alert("Please Enter Date Of Admission");
	                    $("input#date_of_admission").focus();
	                    return false;
	        	 }
	        	 else if($("input#date_of_registration").val().trim()=="DD/MM/YYYY"){
	                    alert("Please Enter Date Of Registration");
	                    $("input#date_of_registration").focus();
	                    return false;
	        	 }
	        	 else if($("select#course_name").val().trim() == "") {
		                alert("Please Enter Course Name");
		                $("select#course_name").focus();
		                return false;
		         }
		         else if($('input[name=neet_rank]:checked').length == 0){
		        	 alert("Please Select Neet Rank(Yes or No)");
	                 $("input#neet_rank").focus();
	                 return false;
		         }
		           
		          if($('input:radio[name=rank]:checked').val() == "Yes"){				
			         if($("#rank").val() == ""){
			        	 alert("Please Enter Rank ");
	  					$("input#rank").focus();
	  					return false;
	  				}
  			    }
		          
		         else if($("input#marks").val().trim() == "") {
		                alert("Please Enter Marks");
		                $("input#marks").focus();
		                return false;
 				 }
		         else if($('input[name=all_india]:checked').length == 0){
	     				 alert("Please Select All india Quota (Yes or No)");
	     				 $("input#all_india").focus();
	     				 return false;
	     		 }
                 else if($('input[name=state]:checked').length == 0){
	                 	alert("Please Select State Quota(Yes or No)");
	                 	$("input#state").focus();
	                 	return false;
	             }
                 else if($('input[name=management_quota]:checked').length == 0){
	                 	alert("Please Select Management Quota (Yes or No)");
	                 	$("input#management_quota").focus();
	                 	return false;
	             }
                 else if($('input[name=admission_authority]:checked').length == 0){
	                 	alert("Please Select Admission Authority (Yes or No)");
	                 	$("input#admission_authority").focus();
	                 	return false;
	             }
		         else if($('input[name=court_order]:checked').length == 0){
		        	 alert("Please Select court_order (Yes or No)");
	                 $("input#court_order").focus();
	                 return false;
		         }
		          if ($("#court_order_file_upload_pg").val().trim() == "") {
		  			alert("Please Upload Court Order File");
		  			$("input#court_order_file_upload_pg").focus();
		  			return false;
		  		}
		          else if($("input#qualification_name").val().trim() == "") {
		                alert("Please Enter Qualification Name");
		                $("input#qualification_name").focus();
		                return false;
		         }
		          else if($("input#year_of_award_admission").val().trim() == "") {
		                alert("Please Enter Year Of Admission");
		                $("input#year_of_award_admission").focus();
		                return false;
		         }
		          else if($("input#date_of_completion_internship").val().trim()=="DD/MM/YYYY"){
	              		alert("Please Enter Date Of Completion Internship");
	               		$("input#date_of_completion_internship").focus();
	               		return false;
	                }
		          else if($("input#date_of_registration_state").val().trim()=="DD/MM/YYYY"){
	                	 alert("Please Enter Date Of Registration State");
	                	 $("input#date_of_registration_state").focus();
	                 	return false;
	             }
		          else  if($("input#date_of_completion_md_part1").val().trim()=="DD/MM/YYYY"){
                	 alert("Please Enter Date Of Completion Md Part1");
                	 $("input#date_of_completion_md_part1").focus();
                 	return false;
                 }
		          else  if($("input#date_of_completion_md_part2").val().trim()=="DD/MM/YYYY"){
	                	 alert("Please Enter Date Of Completion Md Part2");
	                	 $("input#date_of_completion_md_part2").focus();
	                 	return false;
	                 }
		          else  if($("input#date_of_declaration_of_md").val().trim()=="DD/MM/YYYY"){
	                	 alert("Please Enter Date Of Declaration Md ");
	                	 $("input#date_of_declaration_of_md").focus();
	                 	return false;
	                 }
		          return true;
// 			}
//     	var upload_excel = $("#upload_excel").val();
//     	var lastDot = upload_excel.lastIndexOf('.');
//     	var fileName = upload_excel.substring(0, lastDot);
//     	var ext = upload_excel.substring(lastDot + 1);
//      	var form_pg_id = $('#form_pg_id').val();
//     	var form_data = new FormData(document.getElementById("Form_PG_details"));
//     	$.ajax({
//     		url: 'Student_Details_PG_Action?_csrf=' + value,
//     		type: "POST",
//     		data: form_data,
//     		enctype: 'multipart/form-data',
//     		processData: false,
//     		contentType: false
//     	}).done(function(data) {
//     		alert(data);
// 		    		if(data.error == null) {
// 						 if($("#h_id").val()=="a1"){
// 		                  alert(data.saved);
// 						}
// 						 else if($("#h_id").val()=="a2"){
// 							  $('#form_pg_id').val(data.form_pg_id);
// 		                    alert(data.saved);
// 		                    location.reload();
// 						 } else {
// 		                     alert(data.updated);
// 		             }
// 		     	} else alert(data.error);
// 			}).fail(function(jqXHR, textStatus) {
//     		alert("fail to fetch")
//     		});
   }
   
////////////////////////Save Form PG(2) //////////////////////
   
function Save_as_draft_PG2() {
	  var form_pg_id2 = $('#form_pg_id2').val();
	  var formvalues = $("#Form_PG_details2").serialize();
	   $.post('Student_Details_PG2_Action?' + key + "=" + value, formvalues, function(data) {
		   
		 if(data.error == null) {
			if(data.form_pg_id2 != null) { 
   		    $('#form_pg_id2').val(data.form_pg_id2);
                   alert(data.saved);
                   location.reload();
			} else {
			alert(data.updated);
			}
		}  
		else {
			alert(data.error)
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

//////////////////////addmore  Form c (1) Validation //////////////////

function Disseratation_Topic(ps) {
	
	
	  if($("input#student_name_pg"+ps).val().trim() == "") 
	 		 {
	              alert("Please Enter Student Name");
	              $("input#student_name_pg"+ps).focus();
	              return false;
	          }
			 
		 else if($("input#from_date"+ps).val().trim()== "DD/MON/YYYY") {
         alert("Please Enter Date");
         $("input#from_date"+ps).focus();
         return false;
	     }
			 
		 else if($("input#to_date"+ps).val().trim()== "DD/MON/YYYY") {
	         alert("Please Enter Date");
	         $("input#to_date"+ps).focus();
	         return false;
	     }
		 else if($("input#name_of_topic"+ps).val().trim()== "") {
	         alert("Please Enter Name of Topic");
	         $("input#name_of_topic"+ps).focus();
	         return false;
	     }
		
// 		 else if($("input#conclusion_obtain"+ps).val().trim() == "") 
// 			 {
// 		     alert("Please Enter Conclusion");
// 		     $("input#conclusion_obtain"+ps).focus();
// 		     return false;
// 		 }
		 else if($("input#date_of_submission_pg"+ps).val().trim()== "DD/MON/YYYY") {
		 alert("Please Enter Date");
		 $("input#date_of_submission_pg"+ps).focus();
		 return false;
		}
		 
		else if($("input#publication"+ps).val().trim() == "") 
		 {
		 alert("Please Enter Publication");
		 $("input#publication"+ps).focus();
		 return false;
		}
		else if($("input#mention_article"+ps).val().trim() == "") 
		 {
		 alert("Please Enter Article");
		 $("input#mention_article"+ps).focus();
		 return false;
		}
		  
		 
	var student_name_pg =$('#student_name_pg'+ps ).val();
	var from_date = $('#from_date'+ps).val();
	var to_date = $('#to_date'+ps).val();
	var name_of_topic =$('#name_of_topic'+ps ).val();
	var conclusion_obtain =$('#conclusion_obtain'+ps ).val();
	var date_of_submission_pg =$('#date_of_submission_pg'+ps ).val();
	var publication =$('#publication'+ps ).val();
	var mention_article =$('#mention_article'+ps ).val();
	var forma_id = $('#forma_id' + ps).val();
	
	$.post('Disseratation_Topic_Action?' + key + "=" + value, {
		
		student_name_pg : student_name_pg,
		from_date : from_date,
		to_date : to_date,
		name_of_topic : name_of_topic,
		conclusion_obtain : conclusion_obtain,
		date_of_submission_pg : date_of_submission_pg,
		publication : publication,
		mention_article : mention_article,
		forma_id :forma_id
	}, function(data) {
		if(data.error == null) {
			if(data.sibId != null) {
				$('#forma_id' + ps).val(data.sibId);
				$("#Disseratation_Topic_add" + ps).show();
				$("#Disseratation_Topic_remove" + ps).show();
				alert(data.saved);
			} else {
				alert(data.updated);
			}
		} else alert(data.error);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}
/* ---------------------------------------ADD------------------------------------------------ */
  
 sib = 1;
 sib_srno = 1;

function Disseratation_Topic_add_fn(q) {
	if($('#Disseratation_Topic_add' + q).length) {
		$("#Disseratation_Topic_add" + q).hide();
	}
	if(q != 0) sib_srno += 1;
	sib = q + 1;
	$("table#family_table").append('<tr id="tr_sibling' + sib + '">' + '<td class="sib_srno">' + sib_srno + '</td>' 
		+ '<td><div class="input-style-2"><input type="text" id="student_name_pg'+sib+'" name="student_name_pg'+sib+'" placeholder="Enter Student Name" class="form-control" autocomplete= "off" maxlength="50"></div></td>'
		+ '<td><div class="input-style-2"><input type="text"  id="from_date'+sib+'" name="from_date'+sib+'" maxlength="10"  value="DD/MON/YYYY"  placeholder="Enter To Date" class="form-control"></div></td> '
		+ '<td><div class="input-style-2"><input type="text"  id="to_date'+sib+'" name="to_date'+sib+'" maxlength="10"  value="DD/MON/YYYY"  placeholder="Enter To Date" class="form-control"></div></td> '
		+ '<td><div class="input-style-2"><input type="text" id="name_of_topic'+sib+'" name="name_of_topic'+sib+'" placeholder="Enter Name of Topic" class="form-control" autocomplete= "off" maxlength="50"></div></td>'
// 		+ '<td><div class="input-style-2"><input type="text" id="conclusion_obtain'+sib+'" name="conclusion_obtain'+sib+'" placeholder="Enter Conclusion" class="form-control" autocomplete= "off" maxlength="50"></div></td>'
		+ '<td class="dg-rec-none"><input type="text" id="forma_id' + sib + '" name="forma_id' + sib + '"   value="0" class="form-control autocomplete" autocomplete="off"></td>' 
		+ '<td><div class="input-style-2"><input type="text"  id="date_of_submission_pg'+sib+'" name="date_of_submission_pg'+sib+'" maxlength="10"  value="DD/MON/YYYY"  placeholder="Enter To Date" class="form-control"></div></td> '
		+ '<td><div class="input-style-2"><input type="text" id="publication'+sib+'" name="publication' + sib + '" placeholder="Enter Publication" class="form-control"></div></td>'
		+ '<td><div class="input-style-2"><input type="text" id="mention_article'+sib+'" name="mention_article'+sib+'" placeholder="Enter Article" class="form-control" autocomplete= "off" maxlength="50"></div></td>'
    	+ '<td class="hide-action"><ul class="buttons-group multi-btn-group"><li><a class="main-btn info-btn-outline btn-hover btn-sm" value = "SAVE" title = "SAVE" id = "familyA_save' + sib + '"  ><i class="lni lni-checkmark"></i></a></li>' + '<li><a  class="main-btn success-btn-outline btn-hover btn-sm dg-rec-none" value = "ADD" title = "ADD" id = "Disseratation_Topic_add' + sib + '" ><i class="lni lni-plus"></i></a></li>' + '<li><a class="main-btn danger-btn-outline btn-hover btn-sm dg-rec-none" value="REMOVE" title = "REMOVE" id = "Disseratation_Topic_remove' + sib + '" ><i class="lni lni-trash-can"></i></a></li>' + '</ul></td></tr>');

	// save --> onclick="Disseratation_Topic(' + sib + ');"
 //add --> onclick="Disseratation_Topic_add_fn(' + sib + ');" 
 // remove---> onclick="Disseratation_Topic_remove_fn(' + sib + ');"
	datepicketDate('from_date' + sib);
	datepicketDate('to_date' + sib);
	datepicketDate('date_of_submission_pg' + sib);
	dissertation(sib);
}

/* -----------------------------------delete------------------------------------ */

function Disseratation_Topic_remove_fn(R) {
	var rc = confirm("Are You Sure! You Want To Delete");
	if(rc) {
		var forma_id = $('#forma_id' + R).val();
		$.post('Disseratation_Topic_Delete?' + key + "=" + value, {
			forma_id: forma_id
			
		}, function(data) {
			if(data == '1') {
				$("tr#tr_sibling" + R).remove();
				if(R == sib) {
					R = R - 1;
					var temp = true;
					for(i = R; i >= 1; i--) {
						if($('#lDisseratation_Topic_add' + i).length) {
							$("#lDisseratation_Topic_add" + i).show();
							temp = false;
							sib = i;
							break;
						}
					}
					if(temp) {
						Disseratation_Topic_add_fn(0);
					}
				}
				$('.sib_srno').each(function(i, obj) {
					obj.innerHTML = i + 1;
					sib_srno = i + 1;
				});
				alert("Data Deleted Successfully");
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
}


// //////////////////////addmore  FormC(2) Validation //////////////////

function Form3b_Students(ps) {
	
	
	 if($("input#student_name_lec"+ps).val().trim() == "") 
		 {
          alert("Please Enter Student Name");
          $("input#student_name_lec"+ps).focus();
          return false;
      }
	else if($("input#student_class_lec"+ps).val().trim()== "")
	{
		alert("Please Student Class");
		$("input#student_class_lec"+ps).focus();
		return false;
	}
	 else if($("input#lecture_date"+ps).val().trim()== "DD/MON/YYYY") 
	 {
         alert("Please Enter Date");
         $("input#lecture_date"+ps).focus();
         return false;
	    }
	else if($("input#lecture_day"+ps).val().trim()== "") {
	    alert("Please Enter Lecture Day");
	    $("input#lecture_day"+ps).focus();
	    return false;
	}
	else if($("input#lecture_time"+ps).val().trim() == "") 
		 {
	    alert("Please Enter Lecture Time");
	    $("input#lecture_time"+ps).focus();
	    return false;
	}
	else if($("input#topic"+ps).val().trim()== "")
	{
		alert("Please Enter Topic");
		$("input#topic"+ps).focus();
		return false;
	}

			var student_name_lec =$("#student_name_b" + ps).val();
			var student_class_lec = $("#student_class_b" + ps).val();
			var lecture_date = $("#lecture_date"+ ps).val();
			var lecture_day = $("#lecture_day"+ps).val();
			var lecture_time = $("#lecture_time"+ps).val();
			var topic = $("#lecture_topic" + ps).val();
			var sib_ch_idb = $('#sib_ch_idb' + ps).val();
			$.post('form_3b_action_Add?' + key + "=" + value, {

				student_name_lec: student_name_lec,
				student_class_lec: student_class_lec,
					lecture_date: lecture_date,
					lecture_day: lecture_day,
					lecture_time: lecture_time,
					topic:topic,
					sib_ch_idb :sib_ch_idb
					}, function(data) {
					if(data.error == null) {
					if(data.sibId != null) {
					$('#sib_ch_idb' + ps).val(data.sibId);
					$("#Form3b_Students_add" + ps).show();
					$("#Form3b_Students_remove" + ps).show();
					alert(data.saved);
					} else {
					alert(data.updated);
					}
					} else alert(data.error);
					}).fail(function(xhr, textStatus, errorThrown) {
					alert("fail to fetch")
					});
}


// /* ---------------------------------------ADD------------------------------------------------ */

sibb = 1;
sibb_srno = 1;

function Form3b_Students_add_fn(qa) {

//debugger;

if($('#Form3b_Students_add' + qa).length) {
$("#Form3b_Students_add" + qa).hide();
}



if(qa != 0) sibb_srno += 1;
sibb = qa + 1;
$("table#att_Tbb").append('<tr id="tr_id_attb' + sibb + '">' + '<td class="sibb_srno">' + sibb_srno + '</td>' 
+ '<td><div class="input-style-2"><input type="text" id="student_name_lec'+sibb+'" name="student_name_lec'+sibb+'" placeholder="Enter Student Name" class="form-control" autocomplete= "off" maxlength="50"></div></td>'
+ '<td><div class="input-style-2"><input type="text"  id="student_class_lec'+sibb+'" name="student_class_lec'+sibb+'" maxlength="10"  placeholder="Enter To Date" class="form-control"></div></td> '
+ '<td><div class="input-style-2"><input type="text"  id="lecture_date'+sibb+'" name="lecture_date'+sibb+'" maxlength="10"  value="DD/MON/YYYY"  placeholder="Enter Date" class="form-control"></div></td> '
+ '<td class="dg-rec-none"><input type="text" id="sib_ch_idb' + sibb + '" name="sib_ch_idb' + sibb + '"   value="0" class="form-control autocomplete" autocomplete="off"></td>' 
+ '<td><div class="input-style-2"><input type="text" id="lecture_day'+sibb+'" name="lecture_day'+sibb+'" placeholder="Enter Day" class="form-control" autocomplete= "off" maxlength="50"></div></td>'
+ '<td><div class="input-style-2"><input type="time" id="lecture_time'+sibb+'" name="lecture_time' + sibb+ '" placeholder="Enter Time" class="form-control"></div></td>'
+ '<td><div class="input-style-2"><input type="text" id="topic'+sibb+'" name="topic' + sibb+ '" placeholder="Enter Topic" class="form-control"></div></td>'
+ '<td class="hide-action"><ul class="buttons-group multi-btn-group"><li><a class="main-btn info-btn-outline btn-hover btn-sm" value = "SAVE" title = "SAVE" id = "familyB_save' + sibb + '" ><i class="lni lni-checkmark"></i></a></li>' + '<li><a class="main-btn success-btn-outline btn-hover btn-sm dg-rec-none" value = "ADD" title = "ADD" id = "Form3b_Students_add' + sibb + '" ><i class="lni lni-plus"></i></a></li>' + '<li><a class="main-btn danger-btn-outline btn-hover btn-sm dg-rec-none" value="REMOVE" title = "REMOVE" id = "Form3b_Students_remove' + sibb + '" ><i class="lni lni-trash-can"></i></a></li>' + '</ul></td></tr>');

datepicketDate('lecture_date' + sibb);
//--->>onclick="Form3b_Students(' + sibb + ');" 
//add --> onclick="Form3b_Students_add_fn(' + sibb + ');" 
//remove -->> onclick="Form3b_Students_remove_fn(' + sibb + ');"
 lacture(sibb);
}

// /* -----------------------------------delete------------------------------------ */
function Form3b_Students_remove_fn(R) {
		var rc = confirm("Are You Sure! You Want To Delete");
		if(rc) {
		var sib_ch_idb = $('#sib_ch_idb' + R).val();
		$.post('lecture_pg_student_action?' + key + "=" + value, {
			sib_ch_idb: sib_ch_idb
		
		}, function(data) {
		if(data == '1') {
		$("tr#tr_id_attb" + R).remove();
		if(R == sibb) {
		R = R - 1;
		var temp = true;
		for(i = R; i >= 1; i--) {
			if($('#Form3b_Students_add' + i).length) {
				$("#Form3b_Students_add" + i).show();
				temp = false;
				sibb = i;
				break;
			}
		}
		if(temp) {
			Form3b_Students_add_fn(0);
		}
		}
		$('.sibb_srno').each(function(i, obj) {
		obj.innerHTML = i + 1;
		sibb_srno = i + 1;
		});
		alert("Data Deleted Successfully");
		} else {
		alert("Data not Deleted ");
		}
		}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
		});
		}
}
// //////////////////////addmore  FormC(3) Validation//////////////////

function list_of_assignment(as) {
	
	
	  if($("input#student_name_formc"+as).val().trim() == "") 
	  {
	              alert("Please Enter Student Name");
	              $("input#student_name_formc"+as).focus();
	              return false;
	  }
	  else if($("input#list_of_assignment_formc"+as).val().trim()=="")
	   {
	              alert("Please Enter List Of Assignment");
	              $("input#list_of_assignment_formc"+as).focus();
	              return false;
	 }  
			 
	var student_name_formc =$('#student_name_formc'+as ).val();
	var list_of_assignment_formc = $('#list_of_assignment_formc'+as).val();
	var sib_ch_idc = $('#sib_ch_idc' + as).val();
	$.post('form_c_action_Add?' + key + "=" + value, {
		
		student_name_formc : student_name_formc,
		list_of_assignment_formc : list_of_assignment_formc,
		sib_ch_idc :sib_ch_idc
	}, function(data) {
		if(data.error == null) {
			if(data.sibId != null) {
				$('#sib_ch_idc' + as).val(data.sibId);
				$("#list_of_assignment_add" + as).show();
				$("#list_of_assignment_remove" + as).show();
				alert(data.saved);
			} else {
				alert(data.updated);
			}
		} else alert(data.error);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}


/* ---------------------------------------ADD------------------------------------------------ */
  
 sibc = 1;
 sibc_srno = 1;

function list_of_assignment_add_fn(q) {
	
// 	debugger;
	
	if($('#list_of_assignment_add' + q).length) {
		$("#list_of_assignment_add" + q).hide();
	}
	if(q != 0) sibc_srno += 1;
	sibc = q + 1;
	$("table#family_table_c").append('<tr id="tr_sibling2' + sibc + '">' + '<td class="sibc_srno">' + sibc_srno + '</td>' 
		+ '<td class="dg-rec-none"><input type="text" id="sib_ch_idc' + sibc + '" name="sib_ch_idc' + sibc + '"   value="0" class="form-control autocomplete" autocomplete="off"></td>' 
		+ '<td><div class="input-style-2"><input type="text" id="student_name_formc'+sibc+'" name="student_name_formc'+sibc+'" placeholder="Enter Student Name" class="form-control" autocomplete= "off" maxlength="50"></div></td>'
		+ '<td><div class="input-style-2"><input type="text" id="list_of_assignment_formc'+sibc+'" name="list_of_assignment_formc' + sibc + '" placeholder="Enter Lisat Of Assignment" class="form-control"></div></td>'
		+ '<td class="hide-action"><ul class="buttons-group multi-btn-group"><li><a class="main-btn info-btn-outline btn-hover btn-sm" value = "SAVE" title = "SAVE" id = "family_savec' + sibc + '" ><i class="lni lni-checkmark"></i></a></li>' + '<li><a class="main-btn success-btn-outline btn-hover btn-sm dg-rec-none" value = "ADD" title = "ADD" id = "list_of_assignment_add' + sibc + '" ><i class="lni lni-plus"></i></a></li>' + '<li><a class="main-btn danger-btn-outline btn-hover btn-sm dg-rec-none" value="REMOVE" title = "REMOVE" id = "list_of_assignment_remove' + sibc + '" ><i class="lni lni-trash-can"></i></a></li>' + '</ul></td></tr>');
	
	assignment(sibc);

} //onclick="list_of_assignment(' + sibc + ');" 
// onclick="list_of_assignment_add_fn(' + sibc + ');" 
//onclick="list_of_assignment_remove_fn(' + sibc + ');"

/* -----------------------------------delete------------------------------------ */
function list_of_assignment_remove_fn(R) {
	var rc = confirm("Are You Sure! You Want To Delete");
	if(rc) {
		var sib_ch_idc = $('#sib_ch_idc' + R).val();
		$.post('form_c_Add_delete_action?' + key + "=" + value, {
			sib_ch_idc: sib_ch_idc
			
		}, function(data) {
			if(data == '1') {
				$("tr#tr_sibling2" + R).remove();
				if(R == sibc) {
					R = R - 1;
					var temp = true;
					for(i = R; i >= 1; i--) {
						if($('#list_of_assignment_add' + i).length) {
							$("#list_of_assignment_add" + i).show();
							temp = false;
							sibc = i;
							break;
						}
					}
					if(temp) {
						list_of_assignment_add_fn(0);
					}
				}
				$('.sibc_srno').each(function(i, obj) {
					obj.innerHTML = i + 1;
					sibc_srno = i + 1;
				});
				alert("Data Deleted Successfully");
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
}

// //////////////////////Save Form PG(3)(d)//////////////////

function list_of_presentation_given(ps) {
	
	
	  if($("input#student_name_presen"+ps).val().trim() == "") 
	  {
	              alert("Please Enter Student Name");
	              $("input#student_name_presen"+ps).focus();
	              return false;
	  }
	   else if($("input#list_of_presentations"+ps).val().trim() == "") 
	  {
         alert("Please Enter List Of Presentation");
         $("input#list_of_presentations"+ps).focus();
         return false;
      }
	  else if($("input#title_of_seminar_attended"+ps).val().trim() == "") 
	  {
         alert("Please Enter Title Of Seminar Attended");
         $("input#title_of_seminar_attended"+ps).focus();
         return false;
      }
	var student_name_presen =$('#student_name_presen'+ps ).val();
	var list_of_presentations = $('#list_of_presentations'+ps).val();
	var title_of_seminar_attended = $('#title_of_seminar_attended'+ps).val();
	var formd_id = $('#formd_id' + ps).val();
	
	$.post('form_d_action_Add1?' + key + "=" + value, {
		
		student_name_presen : student_name_presen,
		list_of_presentations : list_of_presentations,
		title_of_seminar_attended : title_of_seminar_attended,
		formd_id :formd_id
	}, function(data) {
		if(data.error == null) {
			if(data.sibId != null) {
				$('#formd_id' + ps).val(data.sibId);
				$("#list_of_presentation_given_add" + ps).show();
				$("#list_of_presentation_given_remove" + ps).show();
				alert(data.saved);
			} else {
				alert(data.updated);
			}
		} else alert(data.error);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}


// /* ---------------------------------------ADD------------------------------------------------ */
  
 sibd = 1;
 sibd_srno = 1;

function list_of_presentation_given_add_fn(qd) {
	
// 	debugger;
	
	if($('#list_of_presentation_given_add' + qd).length) {
		$("#list_of_presentation_given_add" + qd).hide();
	}
	if(qd != 0) sibd_srno += 1;
	sibd = qd + 1;
	$("table#family_table3").append('<tr id="tr_sibling3' + sibd + '">' + '<td class="sib_srno">' + sibd_srno + '</td>' 
		+ '<td class="dg-rec-none"><input type="text" id="formd_id' + sibd + '" name="formd_id' + sibd + '"   value="0" class="form-control autocomplete" autocomplete="off"></td>' 
		+ '<td><div class="input-style-2"><input type="text" id="student_name_presen'+sibd+'" name="student_name_presen'+sibd+'" placeholder="Enter Student Name" class="form-control" autocomplete= "off" maxlength="50"></div></td>'
		+ '<td><div class="input-style-2"><input type="text" id="list_of_presentations'+sibd+'" name="list_of_presentations' + sibd + '" placeholder="Enter Lisat Of Assignment" class="form-control"></div></td>'
		+ '<td><div class="input-style-2"><input type="text" id="title_of_seminar_attended'+sibd+'" name="title_of_seminar_attended' + sibd + '" placeholder="Enter Lisat Of Assignment" class="form-control"></div></td>'
		+ '<td class="hide-action"><ul class="buttons-group multi-btn-group"><li><a class="main-btn info-btn-outline btn-hover btn-sm" value = "SAVE" title = "SAVE" id = "family_saved' + sibd + '"  ><i class="lni lni-checkmark"></i></a></li>' + '<li><a class="main-btn success-btn-outline btn-hover btn-sm dg-rec-none" value = "ADD" title = "ADD" id = "list_of_presentation_given_add' + sibd + '" ><i class="lni lni-plus"></i></a></li>' + '<li><a class="main-btn danger-btn-outline btn-hover btn-sm dg-rec-none" value="REMOVE" title = "REMOVE" id = "list_of_presentation_given_remove' + sibd + '" ><i class="lni lni-trash-can"></i></a></li>' + '</ul></td></tr>');
	presentation(sibd);
	//onclick="list_of_presentation_given(' + sibd + ');"
}// onclick="list_of_presentation_given_add_fn(' + sibd + ');" 
// onclick="list_of_presentation_given_remove_fn(' + sibd + ');"

// /* -----------------------------------delete------------------------------------ */
function list_of_presentation_given_remove_fn(R) {
	var rc = confirm("Are You Sure! You Want To Delete");
	if(rc) {
		var formd_id = $('#formd_id' + R).val();
		$.post('form_d_Add_delete_action1?' + key + "=" + value, {
			formd_id: formd_id
			
		}, function(data) {
			if(data == '1') {
				$("tr#tr_sibling3" + R).remove();
				if(R == sibd) {
					R = R - 1;
					var temp = true;
					for(i = R; i >= 1; i--) {
						if($('#llist_of_presentation_given_add' + i).length) {
							$("#list_of_presentation_given_add" + i).show();
							temp = false;
							sibd = i;
							break;
						}
					}
					if(temp) {
						list_of_presentation_given_add_fn(0);
					}
				}
				$('.sibd_srno').each(function(i, obj) {
					obj.innerHTML = i + 1;
					sibd_srno = i + 1;
				});
				alert("Data Deleted Successfully");
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
}

function Supplementry(){
	var mention_details = $('input:radio[name=mention_details]:checked').val();
	if(mention_details == "Yes"){
        $("div#mention_div").show();
	}
	else if(mention_details == "No"){
        $("div#mention_div").hide();
	}
}

function rank_detail(){
	var neet_rank = $('input:radio[name=neet_rank]:checked').val();
	if(neet_rank == "Yes"){
        $("div#rank_div").show();
	}
	else if(neet_rank == "No"){
        $("div#rank_div").hide();
	}
}
</script>