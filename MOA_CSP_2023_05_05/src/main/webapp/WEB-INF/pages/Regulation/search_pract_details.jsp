
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

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link href="assets/vendor/bs-wizard/bs_wizard.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="assets/vendor/internal_css.css">


<script nonce="${cspNonce}" type="text/javascript"> 
	var username = "${username}"; 
</script>


<section class="dashboard-page search_regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Search Details For Practitioner</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a>
								</li>

								<li class="breadcrumb-item active" aria-current="page">
									Search Details For Practitioner</li>
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
			<div class="search-regulation-wrapper">
				<div class="row custom-d-none">

					<div class="col-lg-12 col-md-12 col-sm-12">
						<!-- input style start -->
						<div class="card-style mb-30">
							<h6 class="mb-25">SEARCH DETAILS FOR PRACTITIONER</h6>
							<div class="row">
								<!--                  <div class="col-lg-4 col-md-6 col-sm-12"> -->
								<!--                 <div class="input-style-1"> -->
								<!--                   <label> NRH No <strong class="mandatory">  </strong> </label> -->
								<!--                   <input id="nrh_en_no" name="nrh_en_no" class="form-control" autocomplete="off" maxlength="25" placeholder="Maximum 25 Character"> -->
								<!-- 				 <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">  -->

								<!--                 </div> -->
								<!--                 </div> -->


								<div class="col-lg-3 col-md-6 col-sm-12 col-12">
									<div class="input-style-1">
										<label>Full Name <strong class="mandatory"></strong></label> <input
											id="first_name" name="first_name" class="form-control"
											autocomplete="off" maxlength="25" placeholder="Full Name">
										<input type="text" id="id" name="id" class="form-control"
											value="0" autocomplete="off">
									</div>
								</div>


								<!-- <div class="col-lg-3 col-md-6 col-sm-12 col-12">
					 <div class="input-style-1">
					 <label>Date Of Birth</label>
					  <input type="date"   id="dob" onchange=" ">
                  <span class="icon"><i class="lni lni-chevron-down"></i></span>
 					  </div>
				 </div> -->
								<div class="col-lg-3 col-md-6 col-sm-12 col-12">
									<div class="input-style-2">
										<label>Date Of Birth </label> <input type="text" name="dob"
											id="dob" maxlength="10"
											class="form-control-sm form-control effect-9 "
											aria-required="true" autocomplete="off" value="DD/MM/YYYY">
									</div>
								</div>

								<div class="col-lg-3 col-md-6 col-sm-12 col-12">
									<div class="select-style-1">
										<label>Gender <strong class="mandatory"> </strong></label>
										<div class="singleselect form-control form-control-lg">
											<select name="gender" id="gender"
												class="form-control customselect">
												<option value="-1">--Select--</option>
												<option value="1">Male</option>
												<option value="2">Female</option>
												<option value="3">Other</option>
											</select>

										</div>
									</div>
								</div>



								<!--                  <div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
								<!--                 <div class="input-style-2"> -->
								<!--                   <label> Registration District<strong class="mandatory">  </strong> </label> -->
								<!--                   <select name="registration_district" id="registration_district" class="form-control autocomplete"> -->
								<!-- 			  </select>    -->
								<!--                 </div> -->
								<!--                 </div> -->
								<div class="col-lg-3 col-md-6 col-sm-12 col-12">
									<div class="select-style-1">
										<label> Status </label> <input type="hidden" id="id" name="id"
											class="form-control" value="0" autocomplete="off">
										<div class="select-position">
											<input type="hidden" id="userId" name="userId"> <select
												name="status" id="status" class="form-control customselect">
												<option value=" ">--Select---</option>
												<option value="1">Approved</option>
												<option value="2">Reverted</option>
											</select>
										</div>

									</div>
								</div>



							</div>

							<!-- <-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<!-- 											<li> -->
											<!-- 												<button type="button" -->
											<!-- 													class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch" -->
											<!-- 													id="btn-reload" value="Search"> -->
											<!-- 													<i class="lni lni-search-alt"></i>Search -->
											<!-- 												</button> -->
											<!-- 											</li> -->

											<li><a
												class="main-btn secondary-btn btn-hover btn-iconic-icon btn-search"
												id="btn-reload" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="Search_prac_detailsUrl"
												class="main-btn dark-btn btn-hover btnreset" value="Reset">Reset</a>
											</li>

										</ul>
									</div>
								</div>
							</div>


						</div>

					</div>
				</div>

			</div>

			<div>
				<h2 class="progress-lineh2">
					<label id="sts" name="sts" value=""> </label>
				</h2>

			</div>

			<!--                   start -->
			<c:if test="${ role  != 'Student_NCH' }">
				<div class="card-style mb-30">
					<div class="custom-bs-wizard">
						<div class="row">
							<div class="col-12 col-sm-12 col-md-12 col-lg-12 custom-d-none"
								id="track_div">
								<div class="bs-wizard">
									<div class="bs-wizard-step">
										<div class="text-left bs-wizard-stepnum">PRACTITIONER</div>
										<div class="progress">
											<div class="progress-bar "></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>

									<div class="bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">UNIVERSITY</div>
										<div class="progress">
											<div class="progress-bar "></div>
										</div>
										<!-- 			<a href="#" class="bs-wizard-dot"></a> -->
									</div>

									<div class="bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">STATE COUNCIL</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>

									<div class="bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">NCH COMMISSION</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>

									<div class="bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">NRH NUMBER
											GENERATED</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>
								</div>
							</div>

							<div class="col-12 col-sm-12 col-md-12 col-lg-12 custom-d-none"
								id="track_div0">
								<div class="bs-wizard">
									<div class="bs-wizard-step step-1 active">
										<!-- active -->
										<div class="text-left bs-wizard-stepnum stepnum-active1">PRACTITIONER</div>
										<div class="progress">
											<div class="progress-bar step-1"></div>
										</div>
										<a href="#" class="bs-wizard-dot check bi bi-check step-1"></a>
									</div>

									<div class="bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">UNIVERSITY</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>

									<div class="bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">STATE COUNCIL</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>

									<div class="bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">NCH COMMISSION</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>

									<div class="bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">NRH NUMBER
											GENERATED</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>

								</div>
							</div>




							<div class="col-12 col-sm-12 col-md-12 col-lg-12 custom-d-none"
								id="track_div5">
								<div class="bs-wizard">
									<div class="bs-wizard-step step-2 active">
										<div class="text-left bs-wizard-stepnum stepnum-active2">PRACTITIONER</div>
										<div class="progress">
											<div class="progress-bar step-2"></div>
										</div>
										<a href="#" class="bs-wizard-dot  check bi bi-check step-2"></a>
									</div>

									<!-- 		<div class="bs-wizard-step step-2 active"> -->
									<!-- 			<div class="text-left bs-wizard-stepnum stepnum-active2">COLLAGE</div> -->
									<!-- 			<div class="progress"> -->
									<!-- 				<div class="progress-bar step-2"></div> -->
									<!-- 			</div> -->
									<!-- 			<a href="#" class="bs-wizard-dot  check bi bi-check step-2"></a> -->
									<!-- 		</div> -->


									<div class="bs-wizard-step step-2 active">
										<!-- active -->
										<div class="text-left bs-wizard-stepnum stepnum-active2">COLLAGE</div>
										<div class="progress progress-line">
											<div class="progress-bar "></div>
										</div>
										<a href="#" class="bs-wizard-dot step-2"></a>
									</div>


									<div class="bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">UNIVERSITY</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>
									<div class="bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">STATE COUNCIL</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>

									<div class="bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">NCH COMMISSION</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>

									<div class="bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">NRH NUMBER
											GENERATED</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>

								</div>
							</div>

							<div class="col-12 col-sm-12 col-md-12 col-lg-12 custom-d-none"
								id="track_div1">
								<div class="bs-wizard">
									<div class="bs-wizard-step step-2 active">
										<div class="text-left bs-wizard-stepnum stepnum-active2">PRACTITIONER</div>
										<div class="progress">
											<div class="progress-bar step-2"></div>
										</div>
										<a href="#" class="bs-wizard-dot  check bi bi-check step-2"></a>
									</div>

									<div class="bs-wizard-step step-2 active">
										<div class="text-left bs-wizard-stepnum stepnum-active2">COLLAGE</div>
										<div class="progress">
											<div class="progress-bar step-2"></div>
										</div>
										<a href="#" class="bs-wizard-dot  check bi bi-check step-2"></a>
									</div>


									<div class="bs-wizard-step step-2 active">
										<!-- active -->
										<div class="text-left bs-wizard-stepnum stepnum-active2">UNIVERSITY</div>
										<div class="progress progress-line">
											<div class="progress-bar "></div>
										</div>
										<a href="#" class="bs-wizard-dot step-2"></a>
									</div>

									<div class="bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">STATE COUNCIL</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>

									<div class="bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">NCH COMMISSION</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>

									<div class="bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">NRH NUMBER
											GENERATED</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>

								</div>
							</div>


							<div class="col-12 col-sm-12 col-md-12 col-lg-12 custom-d-none"
								id="track_div2">
								<div class="bs-wizard">
									<div class="bs-wizard-step step-3 active">
										<div class="text-left bs-wizard-stepnum stepnum-active3">PRACTITIONER</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot  check bi bi-check step-3"></a>
									</div>


									<div class="bs-wizard-step step-3 active">
										<div class="text-left bs-wizard-stepnum stepnum-active3">COLLAGE</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot check bi bi-check step-3"></a>
									</div>


									<div class="bs-wizard-step step-3 active">
										<div class="text-left bs-wizard-stepnum stepnum-active3">UNIVERSITY</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot check bi bi-check step-3"></a>
									</div>

									<div class="bs-wizard-step step-3 active">
										<!-- active -->
										<div class="text-left bs-wizard-stepnum stepnum-active3">STATE
											COUNCIL</div>
										<div class="progress progress-line">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot step-3"></a>
									</div>

									<div class="bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">NCH COMMISSION</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>

									<div class="bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">NRH NUMBER
											GENERATED</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>
								</div>
							</div>

							<div class="col-12 col-sm-12 col-md-12 col-lg-12 custom-d-none"
								id="track_div3">
								<div class="bs-wizard">
									<div class="bs-wizard-step step-4 active">
										<div class="text-left bs-wizard-stepnum stepnum-active4">PRACTITIONER</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot  check bi bi-check step-4"></a>
									</div>


									<div class=" bs-wizard-step step-4 active">
										<div class="text-left bs-wizard-stepnum stepnum-active4">COLLAGE</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot  check bi bi-check step-4"></a>
									</div>


									<div class=" bs-wizard-step step-4 active">
										<div class="text-left bs-wizard-stepnum stepnum-active4">UNIVERSITY</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot  check bi bi-check step-4"></a>
									</div>

									<div class=" bs-wizard-step step-4 active">
										<div class="text-left bs-wizard-stepnum stepnum-active4">STATE
											COUNCIL</div>
										<div class="progress ">
											<div class="progress-bar "></div>
										</div>
										<a href="#" class="bs-wizard-dot  check bi bi-check step-4"></a>
									</div>

									<div class=" bs-wizard-step step-4 active">
										<!-- active -->
										<div class="text-left bs-wizard-stepnum stepnum-active4">NCH
											COMMISSION</div>
										<div class="progress progress-line">
											<div class="progress-bar "></div>
										</div>
										<a href="#" class=" bs-wizard-dot step-4"></a>
									</div>

									<div class=" bs-wizard-step ">
										<div class="text-left bs-wizard-stepnum">NRH NUMBER
											GENERATED</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot"></a>
									</div>
								</div>
							</div>


							<div class="col-12 col-sm-12 col-md-12 col-lg-12 custom-d-none"
								id="track_div4">
								<div class="bs-wizard">
									<div class=" bs-wizard-step step-5 active">
										<div class="text-left bs-wizard-stepnum stepnum-active5">PRACTITIONER</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot  check bi bi-check step-5"></a>
									</div>


									<div class=" bs-wizard-step step-5 active">
										<div class="text-left bs-wizard-stepnum stepnum-active5">COLLAGE</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot  check bi bi-check step-5"></a>
									</div>

									<div class=" bs-wizard-step step-5 active">
										<div class="text-left bs-wizard-stepnum stepnum-active5">UNIVERSITY</div>
										<div class="progress">
											<div class="progress-bar"></div>
										</div>
										<a href="#" class="bs-wizard-dot  check bi bi-check step-5"></a>
									</div>

									<div class=" bs-wizard-step step-5 active">
										<div class="text-left bs-wizard-stepnum stepnum-active5">STATE
											COUNCIL</div>
										<div class="progress ">
											<div class="progress-bar "></div>
										</div>
										<a href="#" class="bs-wizard-dot check bi bi-check step-5"></a>
									</div>

									<div class=" bs-wizard-step step-5 active">
										<!-- active -->
										<div class="text-left bs-wizard-stepnum stepnum-active5">NCH
											COMMISSION</div>
										<div class="progress">
											<div class="progress-bar "></div>
										</div>
										<a href="#" class=" bs-wizard-dot check bi bi-check step-5"></a>
									</div>

									<div class=" bs-wizard-step step-5 active">
										<!-- active -->
										<div class="text-left bs-wizard-stepnum stepnum-active5">NRH
											NUMBER GENERATED</div>
										<div class="progress progress-line">
											<div class="progress-bar "></div>
										</div>
										<a href="#" class=" bs-wizard-dot check bi bi-check step-5"></a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:if>




			<!--         <div  align="center"> -->
			<!-- 			 <input type="hidden" id="CheckVal" name="CheckVal"> -->
			<!-- 				  <b><input type=checkbox id='nSelAll' name='tregn' onclick='setselectall();'>Select all [ <span id="tregn">0</span> -->
			<!-- 				   <span id='nTotRow1'>/</span><span id="tregnn"  >  </span>]</b>  -->
			<!-- 					</div> -->
			<section class="single-detail-block">
				<div class="tables-wrapper">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="card-style mb-30">

								<div class="table-wrapper table-responsive custom-datatable-p">
									<table id="Search_State_Prac" class="table">
										<thead>
											<tr>
												<th align="center"><h6>Sr No.</h6></th>
												<!-- 					<th>Select</th> -->
												<!-- 				<th id="2">NRH Enrollment No</th> -->
												<th><h6>Ayush ID/ABHA No.</h6></th>
												<th><h6>NRH No.</h6></th>
												<th><h6>State registration No.</h6></th>
												<th><h6>Valid up to</h6></th>
												<th><h6>Registration state</h6></th>
												<th><h6>Full name</h6></th>
												<th><h6>Gender</h6></th>
												<th><h6>Photo</h6></th>
												<th><h6>Father name</h6></th>

												<th><h6>Present district</h6></th>
												<th><h6>Present state</h6></th>
												<th><h6>Present pincode</h6></th>

												<th><h6>Permanent district</h6></th>
												<th><h6>Permanent state</h6></th>
												<th><h6>Permanent pincode</h6></th>

												<th><h6>Email ID</h6></th>
												<th><h6>Date of birth</h6></th>
												<th><h6>Nationality</h6></th>
												<th><h6>Revert remarks</h6></th>



												<!-- 				 <th>Registration No.</th> -->
												<!-- 				 <th>Date Of Registration</th> -->
												<!-- 				 <th>Name Of The Register</th> -->


												<th><h6>View details</h6></th>



											</tr>
											<!-- end table row-->
										</thead>
										<tbody>

										</tbody>
									</table>
									<!-- end table -->
								</div>
							</div>
							<!-- end card -->
						</div>
						<!-- end col -->
					</div>
					<!-- end row -->
				</div>
			</section>

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

<%-- <c:url value="pract_viewUrl" var="pract_viewUrl" /> --%>
<%-- <form:form action="${pract_viewUrl}" method="post" id="viewForm" --%>
<%-- 	name="viewForm" modelAttribute="viewid"> --%>
<!-- 	<input type="hidden" name="viewid" id="viewid" value="0" /> -->
<!-- <!-- 	<input type="hidden" name="status1" id="status1" value="0" /> -->

<%-- </form:form> --%>


<c:url value="getView" var="getView" />
<form:form action="${getView}" method="post" id="search2" name="search2"
	modelAttribute="id7">
	<input type="hidden" name="id7" id="id7" value="0" />
	<input type="hidden" name="typeReport7" id="typeReport7" value="0" />
	<input type="hidden" name="reportname8" id="reportname8" value="0" />
</form:form>




<c:url value="getCertificatePDF" var="getCertificatePDF" />
<form:form action="${getCertificatePDF}" method="post" id="search1"
	name="search1" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3" value="0" />
	<input type="hidden" name="typeReport2" id="typeReport2" value="0" />
	<input type="hidden" name="reportname1" id="reportname1" value="0" />
</form:form>

<%-- <c:url value="getCertificatePDF" var="searchUrl" /> --%>
<%-- <form:form action="${searchUrl}" method="post" id="search1" name="search1" modelAttribute="comd1"> --%>
<!-- 		<input type="hidden" name="typeReport" id="typeReport" value="0" /> -->
<!-- 	<input type="hidden" name="reportname1" id="reportname1" value="0" /> -->
<!-- 		<input type="hidden" name="begin_date1" id="begin_date1" value="0"/> -->
<%-- </form:form> --%>


<!-- The Modal -->
<!-- <div id="myModal" class="modal"> -->
<!--   <span class="close">&times;</span> -->

<!--   <img class="modal-content" id="img01"> -->
<!--   <div id="caption"></div> -->
<!-- </div> -->


<!-------------- passport-size-photo modal ------------------->
<div class="modal image-modal pass-photo " id="myModal">
	<div class="modal-dialog modal-sm modal-dialog-centered">
		<div class="modal-content">
			<!-- Modal Header -->
			<span class="close">&times;</span>
			<!-- Modal body -->
			<div class="modal-body">
				<div class="modal-img">
					<img id="img01">
					<div id="caption"></div>
				</div>
			</div>
		</div>
	</div>
</div>



<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {
	
	datepicketDate('dob');});

function getPDF(id)
{  
	$("#id3").val(id); 
	document.getElementById('typeReport2').value='pdfL';
	document.getElementById('search1').submit();
}


function setselectall(){
 
	if ($("#nSelAll").prop("checked")) {
		$(".nrCheckBox").prop('checked', true);
	} else {
		$(".nrCheckBox").prop('checked', false);
	}
	
	var l = $('[name="cbox"]:checked').length;
	 $("#tregn").val(l);
	document.getElementById('tregn').innerHTML = l;
	
}
	
	$(document).ready(function() {
		
 
		
		var userId = '${userId}';
		
		$.post('Get_Search_Status?' + key + "=" + value,{ userId : userId }).done(function(j) {
			var sts1="";
			var sts2="";
			var sts3="";
			var sts4="";
			
		 var sizeofj = j.length;
		 
	    if(sizeofj == 0 ){
 			$("div#track_div").hide();
 			$("div#track_div0").hide();
 			$("div#track_div1").hide();
 			$("div#track_div2").hide();
 			$("div#track_div3").hide();
 			$("div#track_div4").hide();

 			}
			
	 		if (sizeofj > 0){
	 			console.log(j);
	 			sts1 = j[0];
	 			sts2 = j[1];
	 			sts3 = j[2];
	 			sts4 = j[3];
	 			sts5 = j[5];
// 	 			alert(sts1)
	 			
// 	 			alert(sts2)
	 			
// 	 			alert(sts3)
	 			
// 	 			alert(sts4)
		   
	 			 
			   if(sts1 == 2 || sts2 == 2 || sts3 == 2 || sts4 == 2 || sts5 == 2){
	 				$("#status").val("2");
	 				$("#sts").text("Your Application is Reverted");
	 				return false;
	 			}
			   else  if(sts1 == 5  ){
	 				//$("#status").val("2");
	 				$("#sts").text("You are Inactive");
	 				return false;
	 			}
			   else   if(sts1 == 4 ){
	 				//$("#status").val("2");
	 				$("#sts").text("You are Suspended");
	 				return false;
	 			}
		   
			   else{
				   
				   if(sts1 == 0 &&  sts2 == 0 && sts3 == 0 && sts4 == 0){
		 			/* 	alert(sts1)
		 				alert(sts2)
		 				alert(sts3) */
		 				
		 				 $("div#track_div").hide();
		 	 			$("div#track_div0").show();
		 	 			$("div#track_div1").hide();
		 	 			$("div#track_div2").hide();
		 	 			$("div#track_div3").hide();
		 	 			$("div#track_div4").hide();

			 			}
		   
		   
				   else	if(sts1 == 0){
		    	$("div#track_div").hide();
	 			$("div#track_div0").show();
	 			$("div#track_div1").hide();
	 			$("div#track_div2").hide();
	 			$("div#track_div3").hide();
	 			$("div#track_div4").hide();

	 		}
				   else  if(sts2 == 0){
		    	$("div#track_div").hide();
	 			$("div#track_div0").hide();
	 			$("div#track_div1").show();
	 			$("div#track_div2").hide();
	 			$("div#track_div3").hide();
	 			$("div#track_div4").hide();

	 		}
				   else    if(sts3 == 0){
		    	$("div#track_div").hide();
	 			$("div#track_div0").hide();
	 			$("div#track_div1").hide();
	 			$("div#track_div2").show();
	 			$("div#track_div3").hide();
	 			$("div#track_div4").hide();

	 		}
				  else  if(sts4 == 0){
		    	$("div#track_div").hide();
	 			$("div#track_div0").hide();
	 			$("div#track_div1").hide();
	 			$("div#track_div2").hide();
	 			$("div#track_div3").show();
	 			$("div#track_div4").hide();

	 			} 
				  else  if(sts5 == 0){
				    	$("div#track_div").hide();
			 			$("div#track_div0").hide();
			 			$("div#track_div1").hide();
			 			$("div#track_div2").hide();
			 			$("div#track_div3").hide();
			 			$("div#track_div4").hide();
			 			$("div#track_div5").show();

			 			} 



		 			 
	 		}
	 		
	 		  if(sts4 == 1 && sts1 != 2 && sts2 != 2 && sts3 != 2 ){
	 			$("#status").val("1");
 	 			$("div#track_div").hide();
	 			$("div#track_div0").hide();
	 			$("div#track_div1").hide();
	 			$("div#track_div2").hide();
	 			$("div#track_div3").hide();
	 			$("div#track_div4").show();
	 			
	 		 }
// 	 		 else{
// 	 			alert(sts1)
//  	 				if(sts1 == 2 || sts2 == 2 || sts3 == 2 || sts4 == 2){
// 	 				$("#status").val("2");
// 	 				$("#sts").text("Your Application is Reverted");
//  	 			}
	 			
	 			
// 	 		 }
	 		}
	    });
		
		
		
		
		
		$("#userId").val(${userId});
	// alert("username--"+'${role}')
		 
		mockjax1('Search_State_Prac');
		table = dataTable('Search_State_Prac');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		
		$('#btn-gs').change(function(){
			table.ajax.reload();
		});


		$("div#track_div").hide	();
// 					$("div#track_div0").hide();
// 					$("div#track_div1").hide();
// 					$("div#track_div2").hide();
// 					$("div#track_div3").hide();
// 					$("div#track_div4").hide();	

		
		$.ajaxSetup({
			async : false
		});
		
	});
 
	function data(Search_State_Prac) {
		 
		//debugger;
		jsondata = [];
		var table = $('#' + Search_State_Prac).DataTable();
		var info = table.page.info();
// 		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		//var orderColunm = $(table.column(order[0][0]).header()).attr('id').toLowerCase();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];


		//var nrh_en_no = $("#nrh_en_no").val();
		var first_name = $("#first_name").val();
		var gender = $("#gender").val();
		var photo_path = $("#photo_path").val();
// 		var father_name = $("#father_name").val();
// 		var pre_address = $("#pre_address").val();
// 		var pre_district = $("#pre_district").val();
// 		var pre_state = $("#pre_state").val();
// 		var pre_pincode = $("#pre_pincode").val();
// 		var per_address = $("#per_address").val();
// 		var per_district = $("#per_district").val();
// 		var per_state = $("#per_state").val();
		 
// 		var per_pincode = $("#per_pincode").val();
// 		var aadhaar_no = $("#aadhaar_no").val();
// 		var fax_no = $("#fax_no").val();
// 		var mo_no = $("#mo_no").val();
// 		var alt_mo_no = $("#alt_mo_no").val();
// 		var email_id = $("#email_id").val();
 		var dob = $("#dob").val();
// 		var nationality = $("#nationality").val();
// 		var degree = $("#degree").val();
// 		var university = $("#university").val();
// 		var month_year = $("#month_year").val();
// 		var reg_no = $("#reg_no").val();
// 		var date_of_reg = $("#date_of_reg").val();
// 		var name_reg = $("#name_reg").val();
// 		var reg_renew_permanent = $("#reg_renew_permanent").val();
// 		var name_of_hospital_teaching = $("#name_of_hospital_teaching").val();
// 		var name_of_patient = $("#name_of_patient").val();
// 		var crh_no = $("#crh_no").val();
// 		var cch_no = $("#cch_no").val();
// 		var nch_no = $("#nch_no").val();
  		 var status = $("#status").val();
		  
// 		 var type_of_degree = $("#type_of_degree").val();
// 		 var reg_no = $("#reg_no").val();
// 		 var registration_state = $("#registration_state").val();
// 		 var degree_name = $("#DegreeName1").val();
// 		 var place_of_working = $("#place_of_working1").val();
// 	 	 var registration_for_type = $("select#reg_renew_permanent").val();
 	 	
 	 	
 	 	
 	 	var state_id = "0";
 	 	var university_id = "0";
 	 	
 	 var role = 	'${role}';
 	 	
 	 	if(role == "University_NCH" || role == "State Council" || role == "NCH"){
 	 		var userId = "0";
  	 	}
 	 	else{
 	 		var userId = $("#userId").val();
  	 	}
		
 	 	
 	 	if(role == "State Council"){
 	 		   state_id = '${state_id}' ;
 	 	}
 	 	if(role == "University_NCH"){
 	 		  university_id = '${university_id}' ;
 	 	}
	 	 
	 	    $.post("getFilter_Prac_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			first_name:first_name,
			 gender:gender,
 			 dob:dob,
 			 status:status,
 			 userId:userId,
 			state_id :state_id,
 			university_id:university_id
 			
  		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
			
			$("#tregnn").text(" "+j.length);	
			
				var gender ="";
				  gender = j[i].gender;
				if(gender == "1"){
					gender="Male"
				}
				if(gender == "2"){
					gender="Female"
				}
				if(gender == "3"){
					gender="Other"
				}
				
				 
				var ayu_abha ="";
				
				
// 	 			 if(j[i].ayush_id=="" && j[i].abha_no!=""){
// 	 				 ayu_abha = ('ABHA No. : '+ j[i].abha_no + '</br>' );
// 	 			 }
// 	 			 else if(j[i].ayush_id!="" && j[i].abha_no=="") {
// 	 				 ayu_abha = ('Ayush Id: '+j[i].ayush_id + '</br>');
// 	 			 }
// 	 			 else if(j[i].ayush_id=='' && j[i].abha_no ==''){
// 	 				 ayu_abha = ('');
// 	 			 }
// 	 			 else {
// 	 				 ayu_abha = ('Ayush Id: '+j[i].ayush_id + '</br>' +'ABHA No. : '+ j[i].abha_no + '</br>' );
// 	 			 }
				 
				 
// 				 if(j[i].ayush_id!="" && j[i].ayush_id!=null){
					 
// 					 ayu_abha = ('Ayush Id: '+j[i].ayush_id + '</br>');
// //	 				 ayu_abha = ('ABHA No. : '+ j[i].abha_no + '</br>' );
// 				 }
				 
				
				 
// 				  if(j[i].abha_no.trim()!="" && j[i].abha_no.trim()!=null){
// 					  ayu_abha = ('ABHA No. : '+ j[i].abha_no + '</br>' );
// 				 }
				  
				   
// 				  if((j[i].ayush_id!="" && j[i].ayush_id!=null) ) {
// 					  ayu_abha = ('Ayush Id: '+j[i].ayush_id + '</br>' +'ABHA No. : '+ j[i].abha_no + '</br>' );
// 				  }
				 
				
				var ayus = j[i].ayush_id ;
				var  ayu_abha;
				var abha  ;
				if( abha == "null" || abha == null || abha == " " || abha == "0"){
					abha ="";
					 
					    ayu_abha =  ('Ayush Id: '+ayus+ '</br>' +'  '+abha+'</br>' );
				}
				else{
					  abha = j[i].abha_no ;
					  
					     ayu_abha =  ('Ayush Id: '+ayus+ '</br>' +'ABHA No. : '+abha+'</br>' );
				}
				
				 if(j[i].nrh_en_no == null){
				
						jsondata.push([ j[i].ser ,(ayu_abha),j[i].nrh_en_no,j[i].state_reg_no,j[i].valid_up_to,j[i].reg_state,
							j[i].first_name, gender, j[i].img, j[i].father_name,
							j[i].pre_district, j[i].pre_state, j[i].pre_pincode, 
							j[i].per_district, j[i].per_state, j[i].per_pincode,
							j[i].email_id
							, j[i].dob, j[i].nationality, j[i].reject_remarks,
							  j[i].vm  
							 
							  ]);
				 }
				 else{
					 
						jsondata.push([ j[i].ser ,(ayu_abha),j[i].nrh_en_no,j[i].state_reg_no,j[i].valid_up_to,j[i].reg_state,
							j[i].first_name, gender, j[i].img, j[i].father_name,
							j[i].pre_district, j[i].pre_state, j[i].pre_pincode, 
							j[i].per_district, j[i].per_state, j[i].per_pincode,
							j[i].email_id
							, j[i].dob, j[i].nationality, j[i].reject_remarks,
							  j[i].vm +j[i].vd
							 
							  ]); 
				 }
				
			
			}
		});
		
		
		$.post("getTotal_Prac_dataCount?" + key + "=" + value, {
			//nrh_en_no:nrh_en_no,
			first_name:first_name,
			 gender:gender,
			 dob:dob,
			 status:status,
			 userId:userId,
			 state_id:state_id,
			 university_id:university_id
			 
		}, function(j) {
			
			FilteredRecords = j;

			});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
function EditData(id) {
		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}



	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	
	
// 	function ViewData(id) {
// 		$("#id1").val(id);
// 		document.getElementById('viewForm').submit();
// 	}

	 
	
	function ViewData(id) {
		//alert(f_status);
// 		$("#status1").val(status); 
		document.getElementById('viewid').value = id;
		document.getElementById('viewForm').submit();
	
	}



		
		// Get the modal
		function imageView(obj){

		var modal = document.getElementById("myModal");
		var span = document.getElementsByClassName("close")[0];


		// Get the image and insert it inside the modal - use its "alt" text as a caption
		var img = document.getElementById("myImg"+obj);

		var modalImg = document.getElementById("img01");
		var captionText = document.getElementById("caption");

		img.onclick = function(){
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

	$ .post( "getDistrictOnstate?" + key + "=" + value,
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

function getDegreeName(obj,R){
	var DegreeName = $("#"+obj.id).val();
	$.post('getDegreeName?' + key + "=" + value,{DegreeName:DegreeName},function(k) {
		var options = '';
		for (var i = 0; i < k.length; i++) {
			options += '<option value="'+k[i].id+'" name="' + k[i].degree_name+ '" >'+ k[i].degree_name + '</option>';
		}
		$("#DegreeName"+R).html(options);
//			for (var i = 0; i < data.length - 1; i++) {
//				datap = data[i].split(":");
//				options += '<option value="'+datap[i].id+'" name="' + datap[]+ '" >'+ datap[0] + '</option>';
//			}
//			$("#rank").html(options);
//			var q = '${list.rank}';
//			if(q != ""){
//				$("#rank").val(q);
//			}
	});

	
}
function download_file(id) {
	
// 	var id = $("#file_id").val(); 
	var val= $("#val1").val();
	var fildname= $("#fildname1").val();
	
	var pdfView="kmlFileDownload69?kmlId2="+id;
	
    fileName="TEST_DOC";
    fileURL=pdfView;
    if (!window.ActiveXObject) {
        var save = document.createElement('a');
        save.href = fileURL;
        save.target = '_blank';
        var filename = fileURL.substring(fileURL.lastIndexOf('/')+1);
        save.download = fileName || filename;
	       if ( navigator.userAgent.toLowerCase().match(/(ipad|iphone|safari)/) && navigator.userAgent.search("Chrome") < 0) {
				document.location = save.href; 
			}else{
		        var evt = new MouseEvent('click', {
		            'view': window,
		            'bubbles': true,
		            'cancelable': false
		        });
		        save.dispatchEvent(evt);
		        (window.URL || window.webkitURL).revokeObjectURL(save.href);
			}	
    }
    else if ( !! window.ActiveXObject && document.execCommand)     {
        var _window = window.open(fileURL, '_blank');
        _window.document.close();
        _window.document.execCommand('SaveAs', true, fileName || fileURL)
        _window.close();
    }
}



function Pop_Up_Degree(id)
{  
	$("#id7").val(id); 
	document.getElementById('typeReport7').value='pdfL';
	document.getElementById('search2').submit();
}


function setTimeLoadForTable(){
	
	document.getElementById('first_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('dob').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('dob').onfocus = function() {
		return this.style.color='#000000';
	};
	document.getElementById('dob').onblur = function() {
		return clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('dob').onkeyup = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('dob').onchange = function() {
		return clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);calculate_age('date_of_birth');
	};
	document.getElementById('userId').onchange = function() {
		statusChange();
	};
	document.querySelectorAll('.imgcsp').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('imghid'+val).value;
//				if (confirm('Are You Sure You Want to View Detail?')) {
				imageView(gdid);
//				} else {
//					return false;
//				}
		})
	});
	document.querySelectorAll('.popdg').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('DCounId'+val).value;
			if (confirm('Are You Sure You Want to View Detail?')) {
				Pop_Up_Degree(gdid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.pdfdown').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('DCounpdf'+val).value;
			if (confirm('Are You Sure You Want to download File ?')) {
				getPDF(gdid);
			} else {
				return false;
			}
		})
	});
}
</script>












