<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!--  <!-- Favicons --> 
<!-- <link href="admin/assets/img/favicon.png" rel="icon"> -->
<!--  <!-- Font CSS Files --> 
<!-- <link href="admin/assets/font/bootstrap-icons/bootstrap-icons.css" -->
<!-- 	rel="stylesheet"> -->
<!--  <!-- Vendor CSS Files --> 
<!-- <link href="admin/assets/vendor/animate.css/animate.min.css" rel="stylesheet"> -->
<!-- <link href="admin/assets/vendor/bootstrap/css/bootstrap.min.css" -->
<!-- 	rel="stylesheet"> -->
<!-- <link href="admin/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet"> -->
<!-- <!-- Template Main CSS File 	 --> 
<!-- <link href="admin/assets/css/style.css" rel="stylesheet"> -->
<!-- <link href="admin/assets/css/responsive.css" rel="stylesheet"> -->
<!-- <script src="admin/layout_file/bootstrap_5/bootstrap.bundle.min.js"></script> -->
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>Ayush Grid</title>
<!-- Favicons -->
<link href="admin/assets/img/favicon.ico" rel="icon">
<!-- Font CSS Files -->
<link href="admin/assets/font/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
<!-- Vendor CSS Files -->
<link href="admin/assets/vendor/animate.css/animate.min.css" rel="stylesheet">
<link href="admin/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="admin/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
<link href="admin/assets/vendor/hover.css" rel="stylesheet" media="all">
<!-- Template Main CSS File -->
<link href="admin/assets/css/style.css" rel="stylesheet">
<link href="admin/assets/css/responsive.css" rel="stylesheet">
<link href="admin/assets/css/custom_pages_style.css" rel="stylesheet">  
<link rel="stylesheet" href="admin/js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="admin/assets/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="admin/js/InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="admin/js/InfiniteScroll/js/jquery.mockjax.js"></script>
	<!-- <link rel="stylesheet" href="admin/assets/db_css/db_custom_style.css"> -->   
<script>
	var username = "${username}";
</script>
  <%-- <section class="search_regulation">
      <div class="container-fluid">
        <!-- title-wrapper start -->
        <div class="title-wrapper pt-30">
          <div class="row align-items-center">
            <div class="col-md-6">
              <div class="title mb-30">
                <h2>Nch Search For User</h2>
              </div>
            </div>
            <!-- end col -->
<!--             <div class="col-md-6"> -->
<!--               <div class="breadcrumb-wrapper mb-30"> -->
<!--                 <nav aria-label="breadcrumb"> -->
<!--                   <ol class="breadcrumb"> -->
<!--                     <li class="breadcrumb-item"> -->
<!--                       <a href="#0">Start View</a> -->
<!--                     </li> -->
<!--                     <li class="breadcrumb-item"><a href="#0">Regulation Forms</a></li> -->
<!--                     <li class="breadcrumb-item active" aria-current="page"> -->
<!--                       Report Form A -->
<!--                     </li> -->
<!--                   </ol> -->
<!--                 </nav> -->
<!--               </div> -->
<!--             </div> -->
            <!-- end col -->
          </div>
          <!-- end row -->
        </div>
        <!-- title-wrapper end -->
        <div class="search-regulation-wrapper">
          <div class="row">
          <div class="col-lg-12">
              <!-- input style start -->
              <div class="card-style mb-30">
                <h6 class="mb-25">NCH SEARCH FOR USER </h6>
               <div class="row">
							<div class="col-lg-4 col-md-6 col-sm-12">
								<div class="input-style-2">
									<label>First Name <strong class="mandatory"></strong></label> <input
										id="first_name" name="first_name" class="form-control"
										autocomplete="off" maxlength="25"
										placeholder="Maximum 25 Character"
										onkeypress="return onlyAlphabetsStringSpace(this,event);">
									<input type="hidden" id="id" name="id" class="form-control"
										value="0" autocomplete="off">
								</div>
							</div>
							<div class="col-lg-4 col-md-6 col-sm-12">
								<div class="input-style-2">
									<label> NRH No. <strong class="mandatory"> </strong>
									</label> <input id="nrh_en_no" name="nrh_en_no" class="form-control"
										autocomplete="off" maxlength="25"
										placeholder="Maximum 25 Character"> <input
										type="hidden" id="id" name="id" class="form-control" value="0"
										autocomplete="off">
								</div>
							</div>
<!-- 							<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 								<div class="input-style-2"> -->
<!-- 									<label>Registration Number<strong class="mandatory"> -->
<!-- 									</strong></label> <input type="text" id="reg_no" name="reg_no" -->
<!-- 										onkeypress="return isNumberOnly(event)" -->
<!-- 										oninput="this.value = this.value.toUpperCase()" maxlength="10" -->
<!-- 										class="form-control autocomplete" autocomplete="off"> -->
<!-- 								</div> -->
<!-- 							</div> -->
						</div>
                <div class="row">
							<div class="col-lg-4 col-md-6 col-sm-12">
								<div class="input-style-2">
									<label>State<strong class="mandatory"> </strong></label> <select
										name="per_state" id="per_state"
										class="form-control customselect" >
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${MedStateName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							  <div class="col-lg-4 col-md-6 col-sm-12">
								<div class="input-style-2">
									<label>Registration State<strong class="mandatory">
									</strong></label> <select name="registration_state" id="registration_state"
										class="form-control customselect" >
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${MedStateName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
<!-- 							<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 								<div class="input-style-2"> -->
<!-- 									<label>Date Of First Registration<strong -->
<!-- 										class="mandatory"></strong></label> <input type="date" -->
<!-- 										id="date_of_reg" name="date_of_reg" class="form-control" -->
<!-- 										autocomplete="off" maxlength="25" -->
<!-- 										placeholder="Maximum 25 Character" -->
<!-- 										onkeypress="return onlyAlphabetsStringSpace(this,event);"> -->
<!-- 									<span class="icon"><i class="lni lni-chevron-down"></i></span> -->
<!-- 								</div> -->
<!-- 							</div> -->
						</div>
                <div class="row">
<!-- 							<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 								<div class="input-style-2"> -->
<!-- 									<label> District<strong class="mandatory"> </strong> -->
<!-- 									</label> <select name="per_district" id="per_district" -->
<!-- 										class="form-control customselect"> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 								<div class="input-style-2"> -->
<!-- 									<label>Place Of Working<strong class="mandatory"> -->
<!-- 									</strong></label> <select name="place_of_working1" id="place_of_working1" -->
<!-- 										class="form-control customselect" style="width: 90%"> -->
<!-- 										<option value="0">--Select--</option> -->
										<c:forEach var="item" items="${PlaceOfWorking}"
											varStatus="num">
											<option value="${item.id}"
												name="${item.place_of_working_practitioner}">${item.place_of_working_practitioner}</option>
										</c:forEach>
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
						</div>
        <div class="row">
<!-- 							<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 								<div class="input-style-2"> -->
<!-- 									<label>Type of Degree<strong class="mandatory"> -->
<!-- 									</strong></label> <select name="type_of_degree" id="type_of_degree" -->
<!-- 										class="form-control customselect" -->
<!-- 										onchange="getDegreeName(this,1);"> -->
<!-- 										<option value="0">--Select--</option> -->
										<c:forEach var="item" items="${TypeOfDegree}" varStatus="num">
											<option value="${item.id}" name="${item.type_of_degree}">${item.type_of_degree}</option>
										</c:forEach>
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 								<div class="input-style-2"> -->
<!-- 									<label>Degree<strong class="mandatory"> </strong></label> <select -->
<!-- 										name="DegreeName1" id="DegreeName1" -->
<!-- 										class="form-control customselect"> -->
<!-- 										<option value="0">--Select--</option> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 								<div class="input-style-2"> -->
<!-- 									<label>Registration Is Renewable Or Permanent<strong -->
<!-- 										class="mandatory"> </strong> -->
<!-- 									</label> <select name="reg_renew_permanent" id="reg_renew_permanent" -->
<!-- 										class="form-control customselect"> -->
<!-- 										<option value="0">--Select--</option> -->
<!-- 										<option value="0">Renewable</option> -->
<!-- 										<option value="1">Permanent</option> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
						</div>
              <div class="row">
<!--                  <div class="col-lg-4 col-md-6 col-sm-12"> -->
<!--                 <div class="input-style-2"> -->
<!--                   <label> Registration District<strong class="mandatory">  </strong> </label> -->
<!--                   <select name="registration_district" id="registration_district" class="form-control autocomplete"> -->
<!-- 			  </select>    -->
<!--                 </div> -->
<!--                 </div> -->
<!--                                 <div class="col-lg-4 col-md-6 col-sm-12"> -->
<!--                 <div class="input-style-2"> -->
<!--                   <label> Status <strong class="mandatory"> </strong> </label> -->
<!--                   <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off"> -->
<!--                   <select name="institute_status" id="institute_status" class="form-control customselect">						  -->
<!-- 										<option value="0">Pending</option> -->
<!-- 										<option value="1">Approved</option> -->
<!-- 										<option value="2">Reject</option> -->
<!-- 								</select> -->
<!--                 </div> -->
<!--                 </div> -->
                 </div>  
                  <ul class="buttons-group mainbtn">
                  <li>
                    <a href="Search_NCH_forUser_Url" class="main-btn success-btn btn-hover btn-clear" value="Reset">Reset</a>
                  </li>
<!--                     <li> -->
<!--                     <input type="submit" class="main-btn secondary-btn btn-hover btn-save" value="Save" onclick="return Validate();"> -->
<!--                   </li> -->
                  <li>
                    <input type="button" class="main-btn active-btn btn-hover btn-search" id="btn-reload" value="Search">
                  </li>
<!--                   <li> -->
<!--                     <input type="button" class="main-btn primary-btn btn-hover" value="Approve" onclick="return setApproveStatus();"> -->
<!--                   </li> -->
<!--                   <li> -->
<!--                     <input type="button" class="main-btn danger-btn btn-hover" value="Reject" onclick="return setRejectStatus();"> -->
<!--                   </li> -->
                </ul>
              </div>
              <!-- end card -->
            </div>
          </div>
          <!-- end row -->
        </div>
       <div class="tables-wrapper">
            <div class="row">
              <div class="col-lg-12">
                <div class="card-style mb-30">
                  <div class="table-wrapper table-responsive custom-datatable-p">
                    <table id="Search_State_Prac" class="table">
                      <thead>
                        <tr>
							<th align="center">Ser No.</th>
			 				<th>Photo Path</th>
			 				<th>First Name</th>
							<th >NRH Enrollment No.</th>
							<th>State</th>
							<th>Registration State</th>
						</tr>
                        <!-- end table row-->
                      </thead>
                      <tbody class="custom-datatablepra">
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
       </div>
        </section> --%>
 <body class="theme-color-v2">
 <section class="language-block text-right">
		<!-- <div class="container-fluid">
				<ul class="list-unstyled list-inline custom-m-line lang-custom-m-line">
					<li class="list-inline-item"><a href="nchlandinghindi">हिन्दी</a></li>
					<li class="list-inline-item"><a href="nchlanding">English</a></li>
				</ul>
		</div> -->
		 <div class="container">
		<div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
    Language
  </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
    <li><a class="dropdown-item" href="#">हिन्दी</a></li>
    <li><a class="dropdown-item" href="#">English</a></li>
  </ul>
</div>
</div>
	</section>
	<header>
		<nav
			class="navbar navbar-default navbar-trans navbar-expand-lg fixed-top">
			<div class="container">
				<div class="logo-main">
					<a href="landingpage"> <img src="admin/assets/img/grid-logo.png"
						class="img-fluid"> <span class="logo-subtext"><img
							alt="moa-logo" src="admin/assets/img/moa-icon.jpg" class="moa-logo">Ministry
							of AYUSH</span>
					</a>
				</div>
				<button class="navbar-toggler collapsed" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarDefault"
					aria-controls="navbarDefault" aria-expanded="false"
					aria-label="Toggle navigation">
					<span></span> <span></span> <span></span>
				</button>
				<div class="navbar-collapse collapse justify-content-center"
					id="navbarDefault">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link active"
							href="nchlanding">Home</a></li>
						<!-- <li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Ayush System</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="ayurveda">Ayurveda</a> <a
									class="dropdown-item" href="#">Yoga</a> <a
									class="dropdown-item" href="#">Naturopathy</a> <a
									class="dropdown-item" href="#">Unani</a> <a
									class="dropdown-item" href="#">Siddha</a> <a
									class="dropdown-item" href="#">Sowa-Rigpa</a> <a
									class="dropdown-item" href="#">Homoeopathy</a>
							</div></li> -->
								  <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Ayush System</a>
                        <div class="dropdown-menu">
                           <a class="dropdown-item" href="nchayurveda">Ayurveda</a> 
                           <a class="dropdown-item" href="nchunani">Unani</a>
                           <a class="dropdown-item" href="nchsiddha">Siddha</a> 
                           <a class="dropdown-item" href="nchsowa">Sowa-Rigpa</a> 
                           <a class="dropdown-item" href="nchhomoeopathy">Homoeopathy</a>
                        </div>
                     </li>
                            <li class="nav-item"><a class="nav-link" href="aboutnch">About NCH</a></li>
					<!-- 	<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">About NCH</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="aboutncism">NCISM</a> <a
									class="dropdown-item" href="aboutnch">NCH</a> <a
									class="dropdown-item" href="aboutmoa">Ministry of
									Ayush(MOA)</a>
							</div></li>  -->
							  <li class="nav-item"><a class="nav-link" href="nchcontact">Contact Us</a></li>
					<!-- 	<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Information</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="admin">Admin</a> <a
									class="dropdown-item" href="staff">Staff</a> <a
									class="dropdown-item" href="student">Student</a> <a
									class="dropdown-item" href="practitioner">Practitioner</a> <a
									class="dropdown-item" href="placement">Placement</a> <a
									class="dropdown-item" href="openapiplatform">Open API
									Platform</a> <a class="dropdown-item" href="coreregistration">Core
									Registries / Directories</a> <a class="dropdown-item"
									href="education">Education</a>
							</div></li>
						<li class="nav-item"><a class="nav-link" href="alumni">Alumni</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">E-learning</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="coursemanagement">Course
									management</a> <a class="dropdown-item" href="learningplatforms">Learning
									Platform</a> <a class="dropdown-item" href="eclassroom">E-classroom
									Module</a> <a class="dropdown-item" href="trainingandwebcast">Training
									& Webcast</a> <a class="dropdown-item" href="#">Collaboration
									utilities</a> <a class="dropdown-item" href="#">Research</a>
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Others</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">Regulation</a> <a
									class="dropdown-item" href="nchcontact">Contact US</a> <a
									class="dropdown-item" href="feedback">Feedback</a>
							</div></li> -->
					</ul>
				</div>
				<div class="custom-btn">
					<div class="authentication-detail">
						<a href="nchsignin" class="btn btn-bh">SignIn</a> 
						<a href="nchsignup" class="btn btn-bh" style="display: none;">SignUp</a>
						<!-- <div class="navbar-toggle-box navbar-toggle-box-collapse">
							<button type="button" class="btn btn-b-n"
								data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01">
								<i class="bi bi-search"></i>
							</button>
						</div> -->
					</div>
				</div>
				<div class="site-logo">
					<ul class="site-logo-inner d-flex">
					<!-- 	<li class="logo-s1"><a href="#"><img
								src="admin/assets/img/ncism_logo.png"></a></li> -->
						<li class="logo-s1"><a href="#"><img
								src="admin/assets/img/nchlogo.png"></a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<!-- Header/Navbar End-->
  <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">Nch Search For User</h1>
             <!--  <span class="color-text-a">Your message has been sent. Thank you! </span> -->
            </div>
          </div>
          <div class="col-md-12 col-lg-4">
            <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="landingpage">Home</a>
                </li>
                <li class="breadcrumb-item active" aria-current="page">
                  Nch Search For User
                </li>
              </ol>
            </nav>
          </div>
        </div>
      </div>
    </section>    
    <section class="contact">
      <div class="container">
        <div class="row">
              <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action="forms/contact.php" method="post" role="form" class="php-email-form">
                  <div class="row justify-content-center">
                    <div class="col-lg-3 col-md-6 col-sm-12 col-12">
                      <div class="form-group">
                        <label>First Name <strong class="mandatory"></strong></label> 
                        <input id="first_name" name="first_name" class="form-control" autocomplete="off" maxlength="25"
								placeholder="First Name">
						<input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
                      </div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-sm-12 col-12">
                      <div class="form-group">
                        <label> NRH No. <strong class="mandatory"> </strong>
									</label> <input id="nrh_en_no" name="nrh_en_no" class="form-control"
										autocomplete="off" maxlength="25"
										placeholder="NRH No."> <input
										type="hidden" id="id" name="id" class="form-control" value="0"
										autocomplete="off">
                      </div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-sm-12 col-12">
                      <div class="form-group">
                       <label>State<strong class="mandatory"> </strong></label> <select
										name="per_state" id="per_state"
										class="form-control customselect" >
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${MedStateName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
                      </div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-sm-12 col-12">
                      <div class="form-group">
                        <label>Registration State<strong class="mandatory">
									</strong></label> <select name="registration_state" id="registration_state"
										class="form-control customselect" >
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${MedStateName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>

									</select>
                      </div>
                    </div>
					<div class="col-lg-12 col-md-12 col-sm-12 mb-3 justify-content-center d-flex">
										<div class="custom-btn footer-btn">
                                               <ul class="footer-btn-list">
													<li class="f-btn"><input type="button" class="btn btn-bh" id="btn-reload" value="Search"></li>
													<li class="f-btn"><a href="Search_NCH_forUser_Url" class="link-color">Reset</a></li>													
												</ul>	
										</div>
						</div>
                  </div>
                </form>
            <div class="custom-datatable-main">
                <div class="card-style mb-30">
                  <div class="table-wrapper table-responsive custom-datatable-p">
                    <table id="Search_State_Prac" class="table dataTable no-footer">
                      <thead>
                        <tr>
							<th align="center"><h6>Ser No.</h6></th>
			 				<th><h6>Photo Path</h6></th>
			 				<th><h6>First Name</h6></th>
							<th><h6>NRH Enrollment No.</h6></th>
							<th><h6>State</h6></th>
							<th><h6>Registration State</h6></th>
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
              </div>
            </div>
          </div>
    </section> 
    <footer class="section-footer">
         <div class="container">
            <div class="row">
               <div class="col-lg-5 col-md-6 col-sm-12">
                  <div class="widget-a">
                     <div class="w-header-a mb-2">
                        <a href="#"><img alt="moa-logo" src="admin/assets/img/ayushlogo.png"></a>
                     </div>
                     <div class="w-body-a">
                        <p class="w-text-a color-text-a">Jawahar Lal Nehru Bhartiya
                           Chikitsa Avam Homoeopathy Anusandhan Bhawan, 61-65,
                           Institutional Area, Janakpuri "D" Block, New Delhi-110058
                        </p>
                     </div>
                     <div class="w-footer-a">
                        <ul class="list-unstyled">
                           <li class="color-a"><span class="color-text-a">Phone
                              :</span> <a href="tel:1800-11-0180,1964">1800-11-0180,1964</a>
                           </li>
                           <li class="color-a"><span class="color-text-a">Email
                              :</span> <a href="mailto:webmanager-ayush@gov.in">webmanager-ayush@gov.in</a>
                           </li>
                        </ul>
                     </div>
                  </div>
               </div>
               <div class="col-lg-2 col-md-6 col-sm-12 section-md-t3">
                  <div class="widget-a">
                     <div class="w-header-a">
                        <h3 class="w-title-a text-brand">Ayush Grid</h3>
                     </div>
                     <div class="w-body-a">
                        <ul class="list-unstyled">
                           <li class="item-list-a"><i class="bi bi-chevron-right"></i>
                              <a href="#">Website Policy</a>
                           </li>
                           <li class="item-list-a"><i class="bi bi-chevron-right"></i>
                              <a href="#">Disclaimer</a>
                           </li>                           
                           <li class="item-list-a"><i class="bi bi-chevron-right"></i>
                              <a href="#">Privacy Policy</a>
                           </li>
                           <li class="item-list-a"><i class="bi bi-chevron-right"></i>
                              <a href="nchcontact">Contact Us</a>
                           </li>
                        </ul>
                     </div>
                  </div>
               </div>
               <div class="col-lg-3 col-md-6 col-sm-12 section-md-t3">
                  <div class="w-header-a">
                     <h3 class="w-title-a text-brand">Connect With Us</h3>
                  </div>
                  <div class="socials-a">
                     <ul class="list-inline">
                        <li class="list-inline-item">
                        	<a href="#" class="fb-icon hvr-bounce-in"><i class="bi bi-facebook" aria-hidden="true"></i></a>
                        </li>
                        <li class="list-inline-item">
                        	<a href="#" class="tw-icon hvr-bounce-in"><i class="bi bi-twitter" aria-hidden="true"></i></a>                        	
                        </li>
                        <li class="list-inline-item">
                        	<a href="#" class="insta-icon hvr-bounce-in"><i class="bi bi-instagram" aria-hidden="true"></i></a>                        
                        </li>
                        <li class="list-inline-item">
                        	<a href="#" class="ld-icon hvr-bounce-in"><i class="bi bi-linkedin" aria-hidden="true"></i></a>                        
                        </li>
                     </ul>
                  </div>
               </div>
               <div class="col-lg-2 col-md-6 col-sm-12">
                  <div class="logo-footer-right">
                     <a href="#"><img src="admin/assets/img/ayush-grid.png" class="img-fluid"></a>
                  </div>
               </div>
            </div>
            <div class="row">
               <div class="col-lg-12 col-md-12 col-sm-12">
                  <div class="copyright-footer text-center">
                     <p class="copyright">
                        Website designed, developed, Content managed &amp; hosted by <a href="https://bisag-n.gov.in/">Bisag-N</a>
                     </p>
                  </div>
               </div>
            </div>
         </div>
      </footer>
</body>

<script src="admin/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="admin/assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="admin/assets/js/main.js"></script>
<%-- <c:url value="Edit_edu_reg_mstrUrl" var="Edit_edu_reg_mstrUrl" /> --%>
<%-- <form:form action="${Edit_edu_reg_mstrUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id1"> --%>
<!-- 	<input type="hidden" name="id1" id="id1" value="0" /> -->
<%-- </form:form> --%>
<%--  <c:url value="delete_edu_reg_mstr_Url" var="delete_edu_reg_mstr_Url" /> --%>
<%-- <form:form action="${delete_edu_reg_mstr_Url}" method="post" id="deleteForm" --%>
<%-- 	name="deleteForm" modelAttribute="id2"> --%>
<!-- 	<input type="hidden" name="id2" id="id2" value="0" /> -->
<%-- </form:form> --%>
<%-- <c:url value="Excel_Auth_Posted_query" var="excelUrl" /> --%>
<%-- <form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="cont_comd_ex"> --%>
<!-- 	 <input type="hidden" name="cont_comd_ex" id="cont_comd_ex"  value="0"> -->
<!-- 	   <input type="hidden" name="cont_corps_ex" id="cont_corps_ex" value="0"> -->
<!-- 	   <input type="hidden" name="cont_div_ex" id="cont_div_ex" value="0"> -->
<!-- 	   <input type="hidden" name="cont_bde_ex" id="cont_bde_ex" value="0"> -->
<!-- 	   <input type="hidden" name="cont_comd_txt" id="cont_comd_txt" > -->
<!-- 	   <input type="hidden" name="cont_corps_txt" id="cont_corps_txt"> -->
<!-- 	   <input type="hidden" name="cont_div_txt" id="cont_div_txt"> -->
<!-- 	   <input type="hidden" name="cont_bde_txt" id="cont_bde_txt"> -->
<!-- 	   <input type="hidden" name="unit_name_ex" id="unit_name_ex"> -->
<!-- 	   <input type="hidden" name="sus_no_ex" id="sus_no_ex"> -->
<!-- 	   <input type="hidden" name="typeReport1" id="typeReport1" value="0" /> -->
<%-- </form:form>  --%>

<!-- The Modal -->
<div id="myModal" class="modal">
  <span class="close">&times;</span>
  <img class="modal-content" id="img01">
  <div id="caption"></div>
</div>
<script>
// function setselectall(){
 
// 	if ($("#nSelAll").prop("checked")) {
// 		$(".nrCheckBox").prop('checked', true);
// 	} else {
// 		$(".nrCheckBox").prop('checked', false);
// 	}
	
// 	var l = $('[name="cbox"]:checked').length;
// 	 $("#tregn").val(l);
// 	document.getElementById('tregn').innerHTML = l;
	
// }
	$(document).ready(function() {
		mockjax1('Search_State_Prac');
		table = dataTable('Search_State_Prac');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$.ajaxSetup({
			async : false
		});
	});
	function data(Search_State_Prac) {
		jsondata = [];
		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
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
		
		var nrh_en_no = $("#nrh_en_no").val();
		var first_name = $("#first_name").val();
		var per_state = $("#per_state").val();
		var registration_state = $("#registration_state").val();

		$.post("getFilter_NCH_Prac_foruserdata?" + key + "=" + value, {
			
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			
			nrh_en_no:nrh_en_no,
			first_name:first_name,
 		  	 per_state:per_state,
			 registration_state:registration_state
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
			//$("#tregnn").text(" "+j.length);	
				jsondata.push([ j[i].ser,j[i].img, j[i].first_name, j[i].nrh_en_no, j[i].per_state, j[i].regisration_state
					  ]);
			}
		});
		$.post("getTotalNCH_Prac_forUserdataCount?" + key + "=" + value, {
			nrh_en_no:nrh_en_no,
			first_name:first_name,
			 per_state:per_state,
			 registration_state:registration_state
		}, function(j) {
			FilteredRecords = j;
			});
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
		
function setTimeLoadForTable(){

	document.getElementById('first_name').onkeypress = function() {
				 return onlyAlphabetsStringSpace(this,event);
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
		}
	</script>