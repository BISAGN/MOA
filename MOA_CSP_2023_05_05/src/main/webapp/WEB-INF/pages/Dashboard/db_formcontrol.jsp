<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Datepicker start -->
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- Datepicker End -->
<!-- Multi Select start -->
<!-- <link rel="stylesheet"
	href="assets/vendor/multiselect/jquery.multiselect.css">
<script type="text/javascript"
	src="assets/vendor/multiselect/jquery.multiselect.js"></script> -->
<!-- Multi Select end -->


<!-- tab components start -->
<section class="dashboard-page">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Form Elements</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#0">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Form Elements</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->

		<!-- form-elements-wrapper start -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<div class="card-style mb-30">
						<div class="inst_block">
							<h6 class="mb-10">Ayush Dependency</h6>
							<div class="row">
								<div class="col-lg-4 col-md-6 col-sm-12">
									<ul class="inst_list">
										<li><p class="inst_text">Bootstrap</p>
											<ul class="inst_list">
												<li><p class="inst_text">
														Current Version :
														<code>V5.1.3</code>
													</p></li>
											</ul></li>
									</ul>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-12">
									<ul class="inst_list">
										<li><p class="inst_text">jQuery</p>
											<ul class="inst_list">
												<li><p class="inst_text">
														Current Version :
														<code>V3.6.0.min.js</code>
													</p></li>
											</ul></li>
									</ul>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-12">
									<ul class="inst_list">
										<li><p class="inst_text">Bootstrap Icon</p>
											<ul class="inst_list">
												<li><p class="inst_text">
														Previous Version :
														<code>V1.7.0 [1-Nov-2021]</code>
													</p></li>
												<li><p class="inst_text">
														Current Version :
														<code>V1.10.2 [30-Nov-2022]</code>
													</p></li>
											</ul></li>
									</ul>
								</div>

							</div>
						</div>
					</div>
					<!-- end card -->
				</div>
			</div>
			<div class="title-wrapper pt-20">
				<div class="row align-items-center">
					<div class="col-md-12">
						<div class="title mb-30">
							<h3>Ayush Dashboard Form Elements</h3>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-12 col-lg-6 col-md-12 col-sm-12">
					<div class="title mb-30">
						<h4>Text Input</h4>
					</div>
					<!-- input style start -->
					<div class="card-style mb-30">

						<ul class="custom-uniqueid">
							<li><p class="custom-id custom-active-badge">
									<span class="custom-id-title">Ayush ID:</span><span
										class="custom-id-value" id="ayush_id">AYU123456789</span>
								</p></li>
							<li><p class="custom-id custom-success-badge">
									<span class="custom-id-title">NR No:</span><span
										class="custom-id-value" id="ayush_id">Pract123456789</span>
								</p></li>
						</ul>

						<div class="input-style-1">
							<label>Full Name<span class="mandatory">*</span></label> <input
								type="text" placeholder="Full Name" />
						</div>

						<div class="input-style-1">
							<label for="">Input With Button<span class="mandatory">*</span></label>
							<div class="input-group custom-input-group">
								<input id="" name="newpassword"
									class="form-control form-control-lg">								
						        <ul class="buttons-group">
								<li><a href="#" class="main-btn active-btn btn-hover btn-sm btnedit" title="Edit"><i class="lni lni-pencil-alt"></i></a></li>
								</ul>
							</div>
							
						</div>
						
						<div class="select-style-1">
							<label for="">Select With Button<span class="mandatory">*</span></label>
							<div class="input-group custom-input-group">
								<div class="select-position form-select">
								<select class="">
									<option value="0">Select category</option>
									<option value="1">Category one</option>
									<option value="2">Category two</option>
									<option value="3">Category three</option>
								</select>
							    </div>								
						        <ul class="buttons-group">
								<li><a href="#" class="main-btn active-btn btn-hover btn-sm btnedit" title="Edit"><i class="lni lni-pencil-alt"></i></a></li>
								</ul>
							</div>
							
						</div>

						<div class="input-style-1">
							<label>Aadhaar Card <span class="mandatory">*</span></label> <input
								type="text" maxlength="14" id="aadhaar" autocomplete="off"
								placeholder="Aadhaar Card" />
						</div>


						<label class="buttonmerge">First Name <strong
							class="mandatory">*</strong></label>
						<div class="groupadd">
							<div class="select-style-1">
								<div class="select-position">
									<select name="cand_prefix" id="cand_prefix"
										class="singleselect form-control form-control-lg">
										<option value="0">Select</option>
										<option value="1">Mr</option>
										<option value="2">Ms</option>
										<option value="3">Mrs</option>
									</select>

								</div>
							</div>
							<div class="input-style-1 buttonmergeinput">
								<input type="text" name="first_name" id="first_name"
									class="form-control " placeholder="Enter First Name"
									maxlength="50">
							</div>
						</div>

						<label class="buttonmerge">Mobile Number <strong
							class="mandatory">*</strong></label>
						<div class="groupadd">
							<div class="select-style-1">
								<div class="select-position">
									<select name="country-code" id="country-code"
										class="singleselect form-control form-control-lg">
										<option value="0">Select</option>
										<option value="1">+91</option>
										<option value="2">+92</option>
										<option value="3">+93</option>
									</select>

								</div>
							</div>
							<div class="input-style-1 buttonmergeinput">
								<input type="text" name="mobile-number" id="mobile-number"
									class="form-control" placeholder="Enter Mobile Number"
									maxlength="50">
							</div>
						</div>

						<div class="input-style-1">
							<label for="fname">Aadhaar Card <strong class="mandatory">*</strong></label>
							<div class="groupadd custom-aadhar-input">
								<input type="text" name="aadhar_no1" id="aadhar_no1"
									class="form-control " maxlength="4" placeholder="XXXX">
								<input type="text" name="aadhar_no2" id="aadhar_no2"
									class="form-control " maxlength="4" placeholder="XXXX">
								<input type="text" name="aadhar_no3" id="aadhar_no3"
									class="form-control " maxlength="4" placeholder="XXXX">
							</div>
						</div>

						<div class="input-style-1">
							<label>Enter Number<span class="mandatory">*</span></label> <input
								type="number" placeholder="Enter Number" />
						</div>

						<div class="input-style-1 input-sm">
							<label>Pincode<span class="mandatory">*</span></label> <input
								type="text" placeholder="Pincode" />
						</div>

						<div class="input-style-1">
							<label>Choose File<span class="mandatory">*</span></label> <input
								type="file" class="form-control" />
						</div>

						<div class="input-style-1">
							<label>Input With Note<span class="mandatory">*</span></label> <input
								type="text" placeholder="Enter dummy data">
							<div class="note-text">
								<span class="mandatory">Mention the note sentence style</span>
							</div>
						</div>

						<div class="input-style-1">
							<label>Input With Note<span class="mandatory">*</span></label> <input
								type="text" placeholder="Enter dummy data">
							<div class="note-text note-text-success">
								<span class="mandatory">mention the successful note
									sentence style</span>
							</div>
						</div>

						<div class="input-style-1">
							<label>Input With Error & Tik Class<span
								class="mandatory">*</span></label> <input type="text"
								placeholder="Enter dummy data">
							<div class="note-text">
								<span class="errorClass" id="">Kindly add custom-d-none
									class when you use this input</span> <span class='tikClass' id="">Kindly
									add custom-d-none class when you use this input</span>
							</div>
						</div>
						<!-- end input -->
						<!-- <div class="input-style-2">
							<input type="text" placeholder="Full Name" /> <span class="icon">
								<i class="lni lni-user"></i>
							</span>
						</div>				
						<div class="input-style-3">
							<input type="text" placeholder="Full Name" /> <span class="icon"><i
								class="lni lni-user"></i></span>
						</div> -->
						<!-- end input -->
					</div>
					<!-- end card -->
					<!-- ======= input style end ======= -->
					<!-- ======= select style start ======= -->
					<div class="title mb-30">
						<h4>Select</h4>
					</div>
					<div class="card-style mb-30">
						<h6 class="mb-25">Selects</h6>

						<div class="select-style-1">
							<label>Custom Single Select (singleselect form-control
								form-control-lg)<span class="mandatory">*</span>
							</label>
							<div class="select-position">
								<select class="singleselect form-control form-control-lg">
									<option value="0">Select</option>
									<option value="1">Category one</option>
									<option value="2">Category two</option>
									<option value="3">Category three</option>
								</select>
							</div>
						</div>
						<!-- end select -->

						<div class="select-style-1">
							<label>Search With Select (select2 form-control
								form-control-lg)<span class="mandatory">*</span>
							</label>
							<div class="select-position">
								<select name="" id=""
									class="select2 form-control form-control-lg">
									<option value="0">Select</option>
									<option>Select category</option>
									<option>Select category 1</option>
									<option>Select category 2</option>
								</select>
							</div>
						</div>

						<div class="select-style-1">
							<label>Single Select (Bootstrap Default)<span
								class="mandatory">*</span></label>
							<div class="select-position">
								<select required>
									<option value="" selected>Select category</option>
									<option value="1">Category one</option>
									<option value="2">Category two</option>
									<option value="3">Category three</option>
								</select>
							</div>
						</div>
						<!-- end select -->
						<div class="select-style-1 custom-select2-multi">
							<label>Multi Select<span
								class="mandatory">*</span></label>
							<div class="select-position">
								<select
									class="multiselect2 form-control form-control-lg category"
									id="category_1" name="category_1" tabindex="14"
									multiple="multiple">
									<option value="1">Category one</option>
									<option value="2">Category two</option>
									<option value="3">Category three</option>
								</select>
							</div>

						</div>
						<!-- start select -->
						<!-- <div class="select-style-1 mb-0">
							<label>Multi Select<span class="mandatory">*</span></label>
							<div class="select-position">
								<select class="" id="multiselect" name="">
									<option value="0" id="" class="">--Select--</option>
								</select>
							</div>
						</div>
						<div id="multiselect_option" class="multiselect">
							<div class="form-check radio-style checkbox-style ">
								<input class="multi form-check-input mr-5" type="checkbox"
									id="in_course_id11" name="in_course_id1" value="97"> <label
									class="lbl" value="course_id1" for="first">Basic of
									Microbiology</label>
							</div>
							<div class="form-check radio-style checkbox-style ">
								<input class="multi form-check-input mr-5" type="checkbox"
									id="in_course_id12" name="in_course_id1" value="98"> <label
									class="lbl" value="course_id2" for="first">Introduction
									to Epidemiology</label>
							</div>
							<div class="form-check radio-style checkbox-style ">
								<input class="multi form-check-input mr-5" type="checkbox"
									id="in_course_id13" name="in_course_id1" value="99"> <label
									class="lbl" value="course_id3" for="first">Basics of
									pharmacology</label>
							</div>
							<div class="form-check radio-style checkbox-style ">
								<input class="multi form-check-input mr-5" type="checkbox"
									id="in_course_id14" name="in_course_id1" value="100"> <label
									class="lbl" value="course_id4" for="first">Introduction
									to phytochemistry</label>
							</div>
							<div class="form-check radio-style checkbox-style ">
								<input class="multi form-check-input mr-5" type="checkbox"
									id="in_course_id15" name="in_course_id1" value="101"> <label
									class="lbl" value="course_id5" for="first">Basics of
									physiotherapy</label>
							</div>
							<div class="form-check radio-style checkbox-style ">
								<input class="multi form-check-input mr-5" type="checkbox"
									id="in_course_id16" name="in_course_id1" value="102"> <label
									class="lbl" value="course_id6" for="first">Introduction
									to science in sanskrit</label>
							</div>
							<div class="form-check radio-style checkbox-style ">
								<input class="multi form-check-input mr-5" type="checkbox"
									id="in_course_id17" name="in_course_id1" value="103"> <label
									class="lbl" value="course_id7" for="first">Basics of
									Manuscriptology</label>
							</div>
							<div class="form-check radio-style checkbox-style ">
								<input class="multi form-check-input mr-5" type="checkbox"
									id="in_course_id18" name="in_course_id1" value="104"> <label
									class="lbl" value="course_id8" for="first">Introduction
									to unani system of medicine</label>
							</div>
							<div class="form-check radio-style checkbox-style ">
								<input class="multi form-check-input mr-5" type="checkbox"
									id="in_course_id19" name="in_course_id1" value="105"> <label
									class="lbl" value="course_id9" for="first">Introduction
									to siddha system of medicine</label>
							</div>
							<div class="form-check radio-style checkbox-style ">
								<input class="multi form-check-input mr-5" type="checkbox"
									id="in_course_id116" name="in_course_id1" value="113">
								<label class="lbl" value="course_id16" for="first">Introduction
									to samkhya - karika</label>
							</div>
							<div class="form-check radio-style checkbox-style">
								<input class="multi form-check-input mr-5" type="checkbox"
									id="in_course_id121" name="in_course_id1" value="118">
								<label class="lbl" value="course_id21" for="first">Basics
									of Sports medicine</label>
							</div>
						</div> -->
						<!-- end select -->
					</div>
					<!-- end card -->
					<!-- ======= select style end ======= -->
					<!-- ======= Time & Date Picker start ======= -->
					<div class="title mb-30">
						<h4>Time & Date Picker</h4>
					</div>
					<div class="card-style mb-30">
						<h6 class="mb-25">Time and Date</h6>
						<div class="input-style-2">
							<label>Date Picker<strong class="mandatory">* </strong>
							</label> <input type="text" name="dob" id="dob" maxlength="10"
								onclick="clickclear(this, 'DD/MM/YYYY')"
								class="form-control-sm form-control effect-9 "
								onfocus="this.style.color='#000000'"
								onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
								onkeyup="clickclear(this, 'DD/MM/YYYY')"
								onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this); calculate_age('dob');  "
								aria-required="true" autocomplete="off" value="DD/MM/YYYY">
						</div>
						<!-- <div class="input-style-1">
							<label>Date</label> <input type="date" />
						</div> -->
						<!-- end input -->
						<!-- <div class="input-style-2">
							<input type="date" /> <span class="icon"><i
								class="lni lni-chevron-down"></i></span>
						</div> -->
						<!-- end input -->
						<div class="input-style-1">
							<label>Time<span class="mandatory">*</span></label> <input
								type="time" />
							<!-- <button type="button" class="custom-icon-input"><span class="icon"><i class="bi bi-eye-slash"></i></span></button> -->
						</div>
						<!-- end input -->
					</div>
					<!-- end card -->
					<!-- ======= Time & Date Picker End ======= -->

					<!-- ======= input switch style start ======= -->
					<!-- <div class="card-style mb-30">
						<h6 class="mb-25">Toggle switch input</h6>
						<div class="form-check form-switch toggle-switch mb-30">
							<input class="form-check-input" type="checkbox"
								id="toggleSwitch1" /> <label class="form-check-label"
								for="toggleSwitch1">Default switch checkbox input</label>
						</div>
						<div class="form-check form-switch toggle-switch">
							<input class="form-check-input" type="checkbox"
								id="toggleSwitch2" checked /> <label class="form-check-label"
								for="toggleSwitch2">Default switch checkbox input</label>
						</div>
					</div> -->
					<!-- ======= input switch style end ======= -->
				</div>
				<!-- end col -->
				<div class="col-12 col-lg-6 col-md-12 col-sm-12">
					<div class="title mb-30">
						<h4>Textarea</h4>
					</div>
					<!-- ======= textarea style start ======= -->
					<div class="card-style mb-30">
						<h6 class="mb-25">Textarea</h6>
						<div class="input-style-1">
							<label>Message</label>
							<textarea placeholder="Message" rows="5"></textarea>
						</div>
						<!-- end textarea -->
						<!-- <div class="input-style-3">
							<textarea placeholder="Message" rows="5"></textarea>
							<span class="icon"><i class="lni lni-text-format"></i></span>
						</div> -->
						<!-- end textarea -->
					</div>
					<!-- ======= textarea style end ======= -->
					<!-- ======= checkbox style start ======= -->
					<div class="title mb-30">
						<h4>Checkbox</h4>
					</div>
					<div class="card-style mb-30">
						<h5 class="text-medium mb-10">Checkbox (Block-style)</h5>
						<div class="input-style-form-check_block">
							<!-- <label for="fname"></label> -->
							<div class="form-check checkbox-style mb-20">
								<input class="form-check-input" type="checkbox" value=""
									id="checkbox-1" /> <label class="form-check-label"
									for="checkbox-1"> Default Checkbox</label>
							</div>
							<!-- end checkbox -->
							<div class="form-check checkbox-style mb-20">
								<input class="form-check-input" type="checkbox" value=""
									id="checkbox-2" disabled /> <label class="form-check-label"
									for="checkbox-2"> Disabled Checkbox</label>
							</div>
							<!-- end checkbox -->
							<div class="form-check checkbox-style checkbox-success mb-20">
								<input class="form-check-input" type="checkbox" value=""
									id="checkbox-3" /> <label class="form-check-label"
									for="checkbox-3"> Success Checkbox</label>
							</div>
							<!-- end checkbox -->
							<div class="form-check checkbox-style checkbox-warning mb-20">
								<input class="form-check-input" type="checkbox" value=""
									id="checkbox-4" /> <label class="form-check-label"
									for="checkbox-4"> Warning Checkbox</label>
							</div>
							<!-- end checkbox -->
							<div class="form-check checkbox-style checkbox-danger mb-20">
								<input class="form-check-input" type="checkbox" value=""
									id="checkbox-5" /> <label class="form-check-label"
									for="checkbox-5"> Danger Checkbox</label>
							</div>
							<!-- end checkbox -->
						</div>
						<h5 class="text-medium mb-10">Checkbox (Inline-style)</h5>
						<div class="input-style-form-check">
							<!-- <label for="fname"></label> -->
							<div class="form-check checkbox-style">
								<input type="checkbox" id="male1" name="gender"
									class="form-check-input" value="0" required=""> <label
									for="male1" class="form-check-label">Checkbox 1</label>
							</div>
							<div class="form-check checkbox-style">
								<input type="checkbox" id="female1" name="gender"
									class="form-check-input" value="1" required=""> <label
									for="female1" class="form-check-label">Checkbox 2</label>
							</div>
							<div class="form-check checkbox-style">
								<input type="checkbox" id="other1" name="gender"
									class="form-check-input" value="2" required=""> <label
									for="other1" class="form-check-label">Checkbox 3</label>
							</div>
						</div>
					</div>
					<!-- ======= checkbox style end ======= -->
					<!-- ======= radio style start ======= -->
					<div class="title mb-30">
						<h4>Radio</h4>
					</div>
					<div class="card-style mb-30">
						<h5 class="text-medium mb-10">Radio (Block-style)</h5>
						<div class="input-style-form-check_block">
							<!-- <label for="fname">Radio (Block-style)</label> -->
							<div class="form-check radio-style mb-20">
								<input class="form-check-input" type="radio" value=""
									id="radio-1" /> <label class="form-check-label" for="radio-1">
									Default Radio</label>
							</div>
							<!-- end radio -->
							<div class="form-check radio-style mb-20">
								<input class="form-check-input" type="radio" value=""
									id="radio-2" disabled /> <label class="form-check-label"
									for="radio-2"> Disabled Radio</label>
							</div>
							<!-- end radio -->
							<div class="form-check radio-style radio-success mb-20">
								<input class="form-check-input" type="radio" value=""
									id="radio-3" /> <label class="form-check-label" for="radio-3">
									Success Radio</label>
							</div>
							<!-- end radio -->
							<div class="form-check radio-style radio-warning mb-20">
								<input class="form-check-input" type="radio" value=""
									id="radio-4" /> <label class="form-check-label" for="radio-4">
									Warning Radio</label>
							</div>
							<!-- end radio -->
							<div class="form-check radio-style radio-danger mb-20">
								<input class="form-check-input" type="radio" value=""
									id="radio-5" /> <label class="form-check-label" for="radio-5">
									Danger Radio</label>
							</div>
							<!-- end radio -->
						</div>
						<h5 class="text-medium mb-10">Radio (Inline-style)</h5>
						<div class="input-style-form-check">
							<!-- <label for="fname"></label> -->
							<div class="form-check radio-style">
								<input type="radio" id="male" name="gender"
									class="form-check-input" value="0" required=""> <label
									for="male" class="form-check-label">Radio 1</label>
							</div>
							<div class="form-check radio-style">
								<input type="radio" id="female" name="gender"
									class="form-check-input" value="1" required=""> <label
									for="female" class="form-check-label">Radio 2</label>
							</div>
							<div class="form-check radio-style">
								<input type="radio" id="other" name="gender"
									class="form-check-input" value="2" required=""> <label
									for="other" class="form-check-label">Radio 3</label>
							</div>
						</div>
						<h5 class="text-medium mb-10">Radio with Border
							(Inline-style)</h5>
						
							<label>Choose Any One</label>
							<div class="custom-choose-one">
							<div class="input-style-form-check">
								<div class="form-check radio-style">
									<input type="radio" id="Upload" class="form-check-input"
										name="Choise" value="Upload"> <label for="Upload"
										class="form-check-label">Upload Through
										Excel</label>
								</div>
								<div class="form-check radio-style">
									<input type="radio" id="Fillform" class="form-check-input" 
										name="Choise" value="Fillform"> <label  for="Fillform"
										class="form-check-label">Fill Up Form</label>
								</div>
							</div>
						</div>
					
						
					</div>
					<!-- ======= radio style end ======= -->
					<div class="title mb-30">
						<h4>Badges</h4>
					</div>
					<div class="card-style mb-30">
						<!-- <h5 class="text-medium mb-10">Square Buttons</h5> -->
						<ul class="buttons-group">
							<li><span class="status-btn active-btn">Active</span></li>
							<li><span class="status-btn success-btn">Done</span></li>
							<li><span class="status-btn info-btn">Pending</span></li>
							<li><span class="status-btn close-btn">Close</span></li>
						</ul>
					</div>
					<!-- modal start -->
					<div class="title mb-30">
						<h4>Modal</h4>
					</div>
					<!-- modal end -->
					<!-- modal start -->
					<div class="card-style mb-30">
						<p class="text-sm text-h">
							If you want to change the modal size then use
							<code>.modal-xl, .modal-lg, .modal-sm</code>
							class For Example :
							<code>class="modal-dialog modal-xl"</code>
						</p>
						<ul class="buttons-group">
							<li><a class="main-btn secondary-btn btn-hover"
								data-bs-toggle="modal" data-bs-target="#exampleModal">Modal</a></li>
							<li><a class="main-btn secondary-btn btn-hover"
								data-bs-toggle="modal" data-bs-target="#exampleModalone">Modal
									With Inputs</a></li>
						</ul>
					</div>
					<!-- end card -->
				</div>
				<!-- form-elements-wrapper end -->
				<!-- ======= Button Start ======= -->
				<div class="title-wrapper pt-30">
					<div class="row align-items-center">
						<div class="col-md-12">
							<div class="title mb-30">
								<h4>Buttons</h4>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="card-style mb-30">
							<h5 class="text-medium mb-10">Text Buttons</h5>
							<ul class="buttons-group">
								<li><a href="#" class="main-btn info-btn btn-hover btnsave">Save</a>
								</li>
								<li><a href="#"
									class="main-btn success-btn  btn-hover btnappr">Approve</a></li>
								<li><a href="#"
									class="main-btn deactive-btn btn-hover btnupda">Update</a></li>
								<li><a href="#"
									class="main-btn active-btn btn-hover btnactive">Active</a></li>
								<li><a href="#"
									class="main-btn light-btn btn-hover btninact">Inactive</a></li>
								<li><a href="#"
									class="main-btn danger-btn btn-hover btnsusp">Suspend</a></li>
								<li><a href="#"
									class="main-btn dark-btn btn-hover btnreset">Reset</a></li>
								<li><a href="#"
									class="main-btn light-btn btn-hover btnclear">Clear</a></li>
								<li><a href="#"
									class="main-btn success-btn btn-hover btnprom">Promote</a></li>
								<li><a href="#"
									class="main-btn danger-btn btn-hover btndeta">Detain</a></li>
								<li><a href="#"
									class="main-btn active-btn btn-hover btnsupple">Supplementary</a>
								</li>
								<li><a href="#"
									class="main-btn secondary-btn btn-hover btnatte">Attend</a></li>
								<li><a href="#"
									class="main-btn active-btn btn-hover btnreturn">Return</a></li>
								<li><a href="#"
									class="main-btn info-btn btn-hover btnsubmit">Submit</a></li>
								<li><a href="#"
									class="main-btn success-btn btn-hover btnverify">Verify</a></li>
								<li><a href="#"
									class="main-btn active-btn btn-hover btnpend">Pending</a></li>
								<li><a href="#"
									class="main-btn danger-btn btn-hover btnreject">Reject</a></li>

							</ul>

							<h5 class="text-medium mb-10 mt-20">Disable Button For All
								Buttons</h5>
							<p class="text-sm text-h">
								If you want to Disable the Button then use
								<code>.disabled</code>
								class For Example :
								<code>class="btn-hover disabled"</code>
							</p>
							<ul class="buttons-group">
								<li><a href="#"
									class="main-btn info-btn btn-hover disabled btnsave">Save</a></li>
							</ul>
							<h5 class="text-medium mb-10 mt-20">Icon with Text Buttons</h5>
							<ul class="buttons-group">
								<li><a href="#0"
									class="main-btn success-btn btn-hover btn-iconic-icon btnpay"><i
										class="lni lni-wallet"></i>Payment</a></li>
								<li><a href="#"
									class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"><i
										class="lni lni-search-alt"></i>Search</a></li>
								<li><a href="#"
									class="main-btn deactive-btn btn-hover btn-iconic-icon btnaddmore"><i
										class="lni lni-plus"></i>Add More</a></li>
								<li><a href="#"
									class="main-btn info-btn btn-hover btndownload"><i
										class="lni lni-download mr-5"></i>Download</a></li>
								<li><a href="#"
									class="main-btn secondary-btn btn-hover btncerti"><i
										class="bi bi-mortarboard mr-5"></i>certificate</a></li>
								<li><a href="#0"
									class="main-btn primary-btn btn-hover btn-iconic-icon btnprimary"><i
										class="lni lni-home"></i>Primary</a></li>
								<li><a href="#0"
									class="main-btn success-btn btn-hover btn-iconic-icon btnexcel"><i
										class="bi bi-file-earmark-excel"></i>Excel</a></li>
								<li><a href="#0"
									class="main-btn dark-btn btn-hover btn-iconic-icon btnview"><i
										class="lni lni-eye"></i>View</a></li>
								<li><a href="#0"
									class="main-btn danger-btn btn-hover btn-iconic-icon btnclose"><i
										class="lni lni-close"></i>Close</a></li>
								<li><a href="#" class="main-btn active-btn btn-hover  btn-iconic-icon btnexport"><i class="lni lni-share-alt"></i>Export</a></li>
								<li>
									<button class="learn-more btnback">
										<span class="circle" aria-hidden="true"> <span
											class="icon arrow"></span>
										</span> <span class="button-text">Back</span>
									</button>
								</li>
							</ul>
							<h5 class="text-medium mb-10 mt-20">Outline Border Icon with
								Text Buttons</h5>
							<ul class="buttons-group">
								<li><a href="#"
									class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i
										class="lni lni-chevron-left"></i>Back</a></li>
								<!-- <li>
                   						 <a href="#" class="main-btn primary-btn-outline btn-hover btn-iconic-icon"><i class="lni lni-search-alt"></i>search</a>
                					  </li> -->
								<li><a href="#"
									class="main-btn active-btn-outline btn-hover btn-iconic-icon btnenroll"><i
										class="lni lni-checkmark"></i>Enroll Now</a></li>
								<li><a href="#"
									class="main-btn success-btn-outline btn-hover btn-iconic-icon btnprint"><i
										class="lni lni-printer"></i>Print</a></li>
								<li><a href="#"
									class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon btnpdf"><i
										class="bi bi-file-pdf"></i>PDF</a></li>
								<li><a href="#"
									class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left btnnext">Next<i
										class="lni lni-chevron-right"></i></a></li>
							</ul>
							<h5 class="text-medium mb-10 mt-20">Iconic Buttons</h5>
							<ul class="buttons-group">
								<li><a href="#"
									class="main-btn success-btn btn-hover btn-sm btnaddmore"
									title="Add"><i class="lni lni-plus"></i></a></li>
								<li><a href="#"
									class="main-btn danger-btn btn-hover btn-sm btnremove"
									title="Remove"><i class="lni lni-trash-can"></i></a></li>
								<li><a href="#"
									class="main-btn active-btn btn-hover btn-sm btnedit"
									title="Edit"><i class="lni lni-pencil-alt"></i></a></li>
								<li><a href="#"
									class="main-btn dark-btn btn-hover btn-sm btnview" title="View"><i
										class="lni lni-eye"></i></a></li>
								<li><a href="#"
									class="main-btn info-btn btn-hover btn-sm btndownload"
									title="Download"><i class="lni lni-download"></i></a></li>
								<li><a href="#"
									class="main-btn info-btn btn-hover btn-sm btnassign"
									title="Assign"><i class="bi bi-person-check-fill"></i></a></li>
								<li><a href="#"
									class="main-btn success-btn btn-hover btn-sm btnapprove"
									title="Approve"><i class="lni lni-checkmark"></i></a></li>
								<li><a href="#"
									class="main-btn danger-btn btn-hover btn-sm btnclose"
									title="Close"><i class="lni lni-close"></i></a></li>
								<li><a href="#"
									class="main-btn active-btn btn-hover btn-sm btnvideo"
									title="Video"><i class="bi bi-play-circle"></i></a></li>
								<li><a href="#" class="main-btn dark-btn btn-hover btn-sm btncopy"
									title="Copy"><i class="bi bi-clipboard"></i></a></li>
								<li><a href="#" class="main-btn active-btn btn-hover btn-sm btnsave"
									title="Save"><i class="lni lni-save"></i></a></li>
								<li><a href="#"
									class="main-btn active-btn btn-hover btn-sm btnaddmore"
									title="Active"><i class="bi bi-person-check btnactive"></i></a></li>
								<li><a href="#"
									class="main-btn light-btn btn-hover btn-sm btninact"
									title="Inactive"><i class="bi bi-person-slash"></i></a></li>
							</ul>
							<h5 class="text-medium mb-10 mt-20">Outline Border Iconic
								Buttons</h5>
							<ul class="buttons-group multi-btn-group">
								<li><a href="#"
									class="main-btn dark-btn-outline btn-hover btn-sm btnview"
									title="View"><i class="lni lni-eye"></i></a></li>
								<li><a href="#"
									class="main-btn active-btn-outline btn-hover btn-sm btnvideo"
									title="Video"><i class="bi bi-play-circle"></i></a></li>
								<li><a href="#"
									class="main-btn success-btn-outline btn-hover btn-sm btndownload"
									title="Download"><i class="lni lni-download"></i></a></li>
								<li><a href="#"
									class="main-btn dark-btn-outline btn-hover btn-sm btnback"
									title="Book"><i class="lni lni-book"></i></a></li>
								<li><a href="#"
									class="main-btn secondary-btn-outline btn-hover btn-sm btngallery"
									title="Gallery"><i class="lni lni-gallery"></i></a></li>
							</ul>
						</div>
						<!-- end card -->
					</div>
				</div>
				<!-- ======= Button end ======= -->
				<div class="title-wrapper pt-30">
					<div class="row align-items-center">
						<div class="col-md-12">
							<div class="title mb-30">
								<h4>Alerts</h4>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="card-style mb-30">
							<div class="alert alert-success alert-dismissible">
								<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
								<strong>Success!</strong> This alert box could indicate a
								successful or positive action.
							</div>
							<div class="alert alert-info alert-dismissible">
								<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
								<strong>Info!</strong> This alert box could indicate a neutral
								informative change or action.
							</div>
							<div class="alert alert-warning alert-dismissible">
								<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
								<strong>Warning!</strong> This alert box could indicate a
								warning that might need attention.
							</div>
							<div class="alert alert-danger alert-dismissible">
								<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
								<strong>Danger!</strong> This alert box could indicate a
								dangerous or potentially negative action.
							</div>
							<div class="alert alert-primary alert-dismissible">
								<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
								<strong>Primary!</strong> Indicates an important action.
							</div>
							<div class="alert alert-secondary alert-dismissible">
								<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
								<strong>Secondary!</strong> Indicates a slightly less important
								action.
							</div>
							<div class="alert alert-dark alert-dismissible">
								<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
								<strong>Dark!</strong> Dark grey alert.
							</div>

						</div>
						<!-- end card -->
					</div>
				</div>
				<div class="title-wrapper pt-30">
					<div class="row align-items-center">
						<div class="col-md-12">
							<div class="title mb-30">
								<h4>Instruction</h4>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="card-style mb-30">
							<h5 class="text-medium text-medium mb-10">Step Instruction</h5>
							<div class="inst_block">
								<h6 class="mb-1">Instruction</h6>
								<ul class="inst_list">
									<li><p class="inst_text">If any of the value is not
											available or not applicable then please put it as 0</p></li>
									<li><p class="inst_text">Please click on 'Download
											templates' to download the templates being uploaded in
											applicable sections of the form.</p></li>
									<li><p class="inst_text">For uploading the required
											files, take a print on the letterhead of the Institution, and
											after getting it stamped and signed, upload the scanned copy
											by clicking at the appropriate option.</p></li>
									<li><p class="inst_text">Please click on <b class="concat-string">Module > <a class="text-heighlight" href="">'Link'</a></b></p></li>
								</ul>
							</div>
							<h5 class="text-medium text-medium mb-10 mt-20">Mandatory &
								Important Instruction</h5>
							<div class="inst_block simple-instruction">
								<strong>Instruction :</strong> Upload file size upto 50kb and
								support file extension .png, .jpeg, .jpg <span
									class="concat-string"> & <b>Upload court order</b> file
									size upto 200kb and support file extension .pdf
								</span>
							</div>
							<h5 class="text-medium text-medium mb-10 mt-20">Declaration
								/ Undertaking</h5>
							<div class="custom-choose-one">
								<div class="input-style-form-check_block check-multi-list">
									<div class="form-check checkbox-style">
										<input type="checkbox" id="Declaration" name="Declaration"
											autocomplete="off" maxlength="25" class="form-check-input">
										<input type="hidden" id="hiddenUpdate" name="hiddenUpdate"
											class="form-control autocomplete" value="0"> <label
											class="check-list">I have carefully read the concept
											and rules regarding my admission, I fully understand that my
											admission is provisional and is subject to final approval and
											enrollment by the University. I also understand that my
											provisional admission is without prejudice to the
											directives/rules and regulations/orders/confirmation from the
											designated and competent authorities of the state/central
											government or the hon'ble court. </label> <label class="check-list">
											I hereby agree to abide by the terms and conditions or the
											rules pertaining to admission as prescribed by the competent
											authorities and admit that they are binding upon me legally
											and legitimately.</label> <label class="check-list"> I
											undertake to pay the fees fixed by the Competent Authority,
											University.</label> <label class="check-list">I undertake to
											see daily notices exhibited on the noticed board of the
											college, observe and maintain a strict discipline as the
											student and otherwise, in the college premises including
											hostel and campus.</label>
									</div>
								</div>
							</div>
						</div>
						<!-- end card -->
					</div>
				</div>
				<!-- accordian-wrapper start -->
				<div class="title-wrapper pt-30">
					<div class="row align-items-center">
						<div class="col-md-12">
							<div class="title mb-30">
								<h4>Accordian</h4>
							</div>
						</div>
					</div>
				</div>
				<!-- accordian-wrapper end -->
				<div class="buttons-cards-wrapper">
					<div class="row">
						<div class="col-12 col-lg-6 col-md-12 col-sm-12">
							<div class="card-style mb-30">
								<h5 class="text-medium text-medium mb-10">Flush</h5>
								<div class="accordion accordion-flush"
									id="accordionFlushExample">
									<div class="accordion-item accordion-itemflush">
										<h2 class="accordion-header" id="flush-headingOne">
											<button class="accordion-button accordion-secondary-btn"
												type="button" data-bs-toggle="collapse"
												data-bs-target="#flush-collapseOne" aria-expanded="true"
												aria-controls="flush-collapseOne">Accordion Item #1</button>
										</h2>
										<div id="flush-collapseOne"
											class="accordion-collapse collapse show"
											aria-labelledby="flush-headingOne"
											data-bs-parent="#accordionFlushExample">
											<div class="accordion-body">
												Placeholder content for this accordion, which is intended to
												demonstrate the
												<code>.accordion-flush</code>
												class. This is the first item's accordion body.
											</div>
										</div>
									</div>
									<div class="accordion-item accordion-itemflush">
										<h2 class="accordion-header" id="flush-headingTwo">
											<button
												class="accordion-button accordion-secondary-btn collapsed"
												type="button" data-bs-toggle="collapse"
												data-bs-target="#flush-collapseTwo" aria-expanded="false"
												aria-controls="flush-collapseTwo">Accordion Item #2</button>
										</h2>
										<div id="flush-collapseTwo"
											class="accordion-collapse collapse"
											aria-labelledby="flush-headingTwo"
											data-bs-parent="#accordionFlushExample">
											<div class="accordion-body">
												Placeholder content for this accordion, which is intended to
												demonstrate the
												<code>.accordion-flush</code>
												class. This is the second item's accordion body. Let's
												imagine this being filled with some actual content.
											</div>
										</div>
									</div>
									<div class="accordion-item accordion-itemflush">
										<h2 class="accordion-header" id="flush-headingThree">
											<button
												class="accordion-button accordion-secondary-btn collapsed"
												type="button" data-bs-toggle="collapse"
												data-bs-target="#flush-collapseThree" aria-expanded="false"
												aria-controls="flush-collapseThree">Accordion Item
												#3</button>
										</h2>
										<div id="flush-collapseThree"
											class="accordion-collapse collapse"
											aria-labelledby="flush-headingThree"
											data-bs-parent="#accordionFlushExample">
											<div class="accordion-body">
												Placeholder content for this accordion, which is intended to
												demonstrate the
												<code>.accordion-flush</code>
												class. This is the third item's accordion body. Nothing more
												exciting happening here in terms of content, but just
												filling up the space to make it look, at least at first
												glance, a bit more representative of how this would look in
												a real-world application.
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- end card -->
						</div>
						<!-- end col -->
						<div class="col-12 col-lg-6 col-md-12 col-sm-12">
							<div class="card-style mb-30">
								<h5 class="text-medium mb-10">Always Open</h5>
								<div class="accordion" id="accordionPanelsStayOpenExample">
									<div class="accordion-item accordion-itemstyle">
										<h2 class="accordion-header" id="panelsStayOpen-headingOne">
											<button
												class="accordion-button accordion-itemstylena accordion-primary-button "
												type="button" data-bs-toggle="collapse"
												data-bs-target="#panelsStayOpen-collapseOne"
												aria-expanded="true"
												aria-controls="panelsStayOpen-collapseOne">Accordion
												Item #1</button>
										</h2>
										<div id="panelsStayOpen-collapseOne"
											class="accordion-collapse collapse show"
											aria-labelledby="panelsStayOpen-headingOne">
											<div class="accordion-body">
												<strong>This is the first item's accordion body.</strong> It
												is shown by default, until the collapse plugin adds the
												appropriate classes that we use to style each element. These
												classes control the overall appearance, as well as the
												showing and hiding via CSS transitions. You can modify any
												of this with custom CSS or overriding our default variables.
												It's also worth noting that just about any HTML can go
												within the
												<code>.accordion-body</code>
												, though the transition does limit overflow.
											</div>
										</div>
									</div>
									<div class="accordion-item accordion-itemstyle">
										<h2 class="accordion-header" id="panelsStayOpen-headingTwo">
											<button
												class="accordion-button accordion-itemstylena accordion-primary-button collapsed"
												type="button" data-bs-toggle="collapse"
												data-bs-target="#panelsStayOpen-collapseTwo"
												aria-expanded="false"
												aria-controls="panelsStayOpen-collapseTwo">Accordion
												Item #2</button>
										</h2>
										<div id="panelsStayOpen-collapseTwo"
											class="accordion-collapse collapse"
											aria-labelledby="panelsStayOpen-headingTwo">
											<div class="accordion-body">
												<strong>This is the second item's accordion body.</strong>
												It is hidden by default, until the collapse plugin adds the
												appropriate classes that we use to style each element. These
												classes control the overall appearance, as well as the
												showing and hiding via CSS transitions. You can modify any
												of this with custom CSS or overriding our default variables.
												It's also worth noting that just about any HTML can go
												within the
												<code>.accordion-body</code>
												, though the transition does limit overflow.
											</div>
										</div>
									</div>
									<div class="accordion-item accordion-itemstyle">
										<h2 class="accordion-header" id="panelsStayOpen-headingThree">
											<button
												class="accordion-button accordion-itemstylena accordion-primary-button collapsed"
												type="button" data-bs-toggle="collapse"
												data-bs-target="#panelsStayOpen-collapseThree"
												aria-expanded="false"
												aria-controls="panelsStayOpen-collapseThree">
												Accordion Item #3</button>
										</h2>
										<div id="panelsStayOpen-collapseThree"
											class="accordion-collapse collapse"
											aria-labelledby="panelsStayOpen-headingThree">
											<div class="accordion-body">
												<strong>This is the third item's accordion body.</strong> It
												is hidden by default, until the collapse plugin adds the
												appropriate classes that we use to style each element. These
												classes control the overall appearance, as well as the
												showing and hiding via CSS transitions. You can modify any
												of this with custom CSS or overriding our default variables.
												It's also worth noting that just about any HTML can go
												within the
												<code>.accordion-body</code>
												, though the transition does limit overflow.
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- end card -->
						</div>
						<!-- end col -->
					</div>
					<!-- end row -->
				</div>
				<!-- accordian-wrapper start -->
				<div class="title-wrapper pt-30">
					<div class="row align-items-center">
						<div class="col-md-12">
							<div class="title mb-30">
								<h4>Icons</h4>
							</div>
						</div>
					</div>
				</div>
				<!-- accordian-wrapper end -->
				<div class="buttons-cards-wrapper">
					<div class="row">
						<div class="col-12 col-lg-12 col-md-12 col-sm-12">
							<div class="card-style mb-30">
								<h5 class="text-medium text-medium mb-10">Icons Pack</h5>
								<div class="accordion accordion-flush"
									id="accordionFlushExampleone">
									<div class="accordion-item accordion-itemflush">
										<h2 class="accordion-header" id="flush-headingfour">
											<button class="accordion-button accordion-secondary-btn"
												type="button" data-bs-toggle="collapse"
												data-bs-target="#flush-collapsefour" aria-expanded="true"
												aria-controls="flush-collapsefour">Bootstarp Icons</button>
										</h2>
										<div id="flush-collapsefour"
											class="accordion-collapse collapse show"
											aria-labelledby="flush-headingfour"
											data-bs-parent="#accordionFlushExampleone">
											<div class="accordion-body">
												<p class="text-sm">
													Previous Version :
													<code>V 1.7.0 [1-Nov-2021]</code>
												</p>
												<p class="text-sm">
													Current Version :
													<code>V 1.10.2 [30-Nov-2022]</code>
												</p>
												<p class="text-sm">
													URL :
													<code>
														<a href="https://icons.getbootstrap.com/" target="_blank">icons.getbootstrap.com</a>
													</code>
												</p>
											</div>
										</div>
									</div>
									<div class="accordion-item accordion-itemflush">
										<h2 class="accordion-header" id="flush-headingfive">
											<button
												class="accordion-button accordion-secondary-btn collapsed"
												type="button" data-bs-toggle="collapse"
												data-bs-target="#flush-collapsefive" aria-expanded="false"
												aria-controls="flush-collapsefive">Dashboard Icons</button>
										</h2>
										<div id="flush-collapsefive"
											class="accordion-collapse collapse"
											aria-labelledby="flush-headingfive"
											data-bs-parent="#accordionFlushExampleone">
											<div class="accordion-body">
												<div class="icons-wrapper">
													<!-- <div class="title d-flex justify-content-between">
                    <div class="left">
                      <h6 class="text-medium mb-10">Icons Pack</h6>
                    </div>
                  </div> -->
													<!-- End Title -->
													<ul class="icons">
														<li class="trigger Free"><i class="lni lni-500px"></i><span>500px</span>
														</li>
														<li class="trigger Free"><i class="lni lni-add-files"></i><span>add-files</span>
														</li>
														<li class="trigger Free"><i class="lni lni-adobe"></i><span>adobe</span>
														</li>
														<li class="trigger Free"><i class="lni lni-agenda"></i><span>agenda</span>
														</li>
														<li class="trigger Free"><i class="lni lni-airbnb"></i><span>airbnb</span>
														</li>
														<li class="trigger Free"><i class="lni lni-alarm"></i><span>alarm</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-alarm-clock"></i><span>alarm-clock</span>
														</li>
														<li class="trigger Free"><i class="lni lni-amazon"></i><span>amazon</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-amazon-original"></i><span>amazon-original</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-amazon-pay"></i><span>amazon-pay</span></li>
														<li class="trigger Free"><i class="lni lni-ambulance"></i><span>ambulance</span>
														</li>
														<li class="trigger Free"><i class="lni lni-amex"></i><span>amex</span>
														</li>
														<li class="trigger Free"><i class="lni lni-anchor"></i><span>anchor</span>
														</li>
														<li class="trigger Free"><i class="lni lni-android"></i><span>android</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-android-original"></i><span>android-original</span>
														</li>
														<li class="trigger Free"><i class="lni lni-angellist"></i><span>angellist</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-angle-double-down"></i><span>angle-double-down</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-angle-double-left"></i><span>angle-double-left</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-angle-double-right"></i><span>angle-double-right</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-angle-double-up"></i><span>angle-double-up</span>
														</li>
														<li class="trigger Free"><i class="lni lni-angular"></i><span>angular</span>
														</li>
														<li class="trigger Free"><i class="lni lni-apartment"></i><span>apartment</span>
														</li>
														<li class="trigger Free"><i class="lni lni-app-store"></i><span>app-store</span>
														</li>
														<li class="trigger Free"><i class="lni lni-apple"></i><span>apple</span>
														</li>
														<li class="trigger Free"><i class="lni lni-apple-pay"></i><span>apple-pay</span>
														</li>
														<li class="trigger Free"><i class="lni lni-archive"></i><span>archive</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-arrow-down"></i><span>arrow-down</span></li>
														<li class="trigger Free"><i
															class="lni lni-arrow-down-circle"></i><span>arrow-down-circle</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-arrow-left"></i><span>arrow-left</span></li>
														<li class="trigger Free"><i
															class="lni lni-arrow-left-circle"></i><span>arrow-left-circle</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-arrow-right"></i><span>arrow-right</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-arrow-right-circle"></i><span>arrow-right-circle</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-arrow-top-left"></i><span>arrow-top-left</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-arrow-top-right"></i><span>arrow-top-right</span>
														</li>
														<li class="trigger Free"><i class="lni lni-arrow-up"></i><span>arrow-up</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-arrow-up-circle"></i><span>arrow-up-circle</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-arrows-horizontal"></i><span>arrows-horizontal</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-arrows-vertical"></i><span>arrows-vertical</span>
														</li>
														<li class="trigger Free"><i class="lni lni-atlassian"></i><span>atlassian</span>
														</li>
														<li class="trigger Free"><i class="lni lni-aws"></i><span>aws</span>
														</li>
														<li class="trigger Free"><i class="lni lni-backward"></i><span>backward</span>
														</li>
														<li class="trigger Free"><i class="lni lni-baloon"></i><span>baloon</span>
														</li>
														<li class="trigger Free"><i class="lni lni-ban"></i><span>ban</span>
														</li>
														<li class="trigger Free"><i class="lni lni-bar-chart"></i><span>bar-chart</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-basketball"></i><span>basketball</span></li>
														<li class="trigger Free"><i class="lni lni-behance"></i><span>behance</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-behance-original"></i><span>behance-original</span>
														</li>
														<li class="trigger Free"><i class="lni lni-bi-cycle"></i><span>bi-cycle</span>
														</li>
														<li class="trigger Free"><i class="lni lni-bitbucket"></i><span>bitbucket</span>
														</li>
														<li class="trigger Free"><i class="lni lni-bitcoin"></i><span>bitcoin</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-blackboard"></i><span>blackboard</span></li>
														<li class="trigger Free"><i class="lni lni-blogger"></i><span>blogger</span>
														</li>
														<li class="trigger Free"><i class="lni lni-bluetooth"></i><span>bluetooth</span>
														</li>
														<li class="trigger Free"><i class="lni lni-bold"></i><span>bold</span>
														</li>
														<li class="trigger Free"><i class="lni lni-bolt"></i><span>bolt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-bolt-alt"></i><span>bolt-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-book"></i><span>book</span>
														</li>
														<li class="trigger Free"><i class="lni lni-bookmark"></i><span>bookmark</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-bookmark-alt"></i><span>bookmark-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-bootstrap"></i><span>bootstrap</span>
														</li>
														<li class="trigger Free"><i class="lni lni-bricks"></i><span>bricks</span>
														</li>
														<li class="trigger Free"><i class="lni lni-bridge"></i><span>bridge</span>
														</li>
														<li class="trigger Free"><i class="lni lni-briefcase"></i><span>briefcase</span>
														</li>
														<li class="trigger Free"><i class="lni lni-brush"></i><span>brush</span>
														</li>
														<li class="trigger Free"><i class="lni lni-brush-alt"></i><span>brush-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-bubble"></i><span>bubble</span>
														</li>
														<li class="trigger Free"><i class="lni lni-bug"></i><span>bug</span>
														</li>
														<li class="trigger Free"><i class="lni lni-bulb"></i><span>bulb</span>
														</li>
														<li class="trigger Free"><i class="lni lni-bullhorn"></i><span>bullhorn</span>
														</li>
														<li class="trigger Free"><i class="lni lni-burger"></i><span>burger</span>
														</li>
														<li class="trigger Free"><i class="lni lni-bus"></i><span>bus</span>
														</li>
														<li class="trigger Free"><i class="lni lni-cake"></i><span>cake</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-calculator"></i><span>calculator</span></li>
														<li class="trigger Free"><i class="lni lni-calendar"></i><span>calendar</span>
														</li>
														<li class="trigger Free"><i class="lni lni-camera"></i><span>camera</span>
														</li>
														<li class="trigger Free"><i class="lni lni-candy"></i><span>candy</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-candy-cane"></i><span>candy-cane</span></li>
														<li class="trigger Free"><i class="lni lni-capsule"></i><span>capsule</span>
														</li>
														<li class="trigger Free"><i class="lni lni-car"></i><span>car</span>
														</li>
														<li class="trigger Free"><i class="lni lni-car-alt"></i><span>car-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-caravan"></i><span>caravan</span>
														</li>
														<li class="trigger Free"><i class="lni lni-cart"></i><span>cart</span>
														</li>
														<li class="trigger Free"><i class="lni lni-cart-full"></i><span>cart-full</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-certificate"></i><span>certificate</span>
														</li>
														<li class="trigger Free"><i class="lni lni-check-box"></i><span>check-box</span>
														</li>
														<li class="trigger Free"><i class="lni lni-checkmark"></i><span>checkmark</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-checkmark-circle"></i><span>checkmark-circle</span>
														</li>
														<li class="trigger Free"><i class="lni lni-chef-hat"></i><span>chef-hat</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-chevron-down"></i><span>chevron-down</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-chevron-down-circle"></i><span>chevron-down-circle</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-chevron-left"></i><span>chevron-left</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-chevron-left-circle"></i><span>chevron-left-circle</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-chevron-right"></i><span>chevron-right</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-chevron-right-circle"></i><span>chevron-right-circle</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-chevron-up"></i><span>chevron-up</span></li>
														<li class="trigger Free"><i
															class="lni lni-chevron-up-circle"></i><span>chevron-up-circle</span>
														</li>
														<li class="trigger Free"><i class="lni lni-chrome"></i><span>chrome</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-circle-minus"></i><span>circle-minus</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-circle-plus"></i><span>circle-plus</span>
														</li>
														<li class="trigger Free"><i class="lni lni-clipboard"></i><span>clipboard</span>
														</li>
														<li class="trigger Free"><i class="lni lni-close"></i><span>close</span>
														</li>
														<li class="trigger Free"><i class="lni lni-cloud"></i><span>cloud</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-cloud-check"></i><span>cloud-check</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-cloud-download"></i><span>cloud-download</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-cloud-network"></i><span>cloud-network</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-cloud-sync"></i><span>cloud-sync</span></li>
														<li class="trigger Free"><i
															class="lni lni-cloud-upload"></i><span>cloud-upload</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-cloudy-sun"></i><span>cloudy-sun</span></li>
														<li class="trigger Free"><i class="lni lni-code"></i><span>code</span>
														</li>
														<li class="trigger Free"><i class="lni lni-code-alt"></i><span>code-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-codepen"></i><span>codepen</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-coffee-cup"></i><span>coffee-cup</span></li>
														<li class="trigger Free"><i class="lni lni-cog"></i><span>cog</span>
														</li>
														<li class="trigger Free"><i class="lni lni-cogs"></i><span>cogs</span>
														</li>
														<li class="trigger Free"><i class="lni lni-coin"></i><span>coin</span>
														</li>
														<li class="trigger Free"><i class="lni lni-comments"></i><span>comments</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-comments-alt"></i><span>comments-alt</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-comments-reply"></i><span>comments-reply</span>
														</li>
														<li class="trigger Free"><i class="lni lni-compass"></i><span>compass</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-construction"></i><span>construction</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-construction-hammer"></i><span>construction-hammer</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-consulting"></i><span>consulting</span></li>
														<li class="trigger Free"><i
															class="lni lni-control-panel"></i><span>control-panel</span>
														</li>
														<li class="trigger Free"><i class="lni lni-cpanel"></i><span>cpanel</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-creative-commons"></i><span>creative-commons</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-credit-cards"></i><span>credit-cards</span>
														</li>
														<li class="trigger Free"><i class="lni lni-crop"></i><span>crop</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-cross-circle"></i><span>cross-circle</span>
														</li>
														<li class="trigger Free"><i class="lni lni-crown"></i><span>crown</span>
														</li>
														<li class="trigger Free"><i class="lni lni-css3"></i><span>css3</span>
														</li>
														<li class="trigger Free"><i class="lni lni-cup"></i><span>cup</span>
														</li>
														<li class="trigger Free"><i class="lni lni-customer"></i><span>customer</span>
														</li>
														<li class="trigger Free"><i class="lni lni-cut"></i><span>cut</span>
														</li>
														<li class="trigger Free"><i class="lni lni-dashboard"></i><span>dashboard</span>
														</li>
														<li class="trigger Free"><i class="lni lni-database"></i><span>database</span>
														</li>
														<li class="trigger Free"><i class="lni lni-delivery"></i><span>delivery</span>
														</li>
														<li class="trigger Free"><i class="lni lni-dev"></i><span>dev</span>
														</li>
														<li class="trigger Free"><i class="lni lni-diamond"></i><span>diamond</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-diamond-alt"></i><span>diamond-alt</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-diners-club"></i><span>diners-club</span>
														</li>
														<li class="trigger Free"><i class="lni lni-dinner"></i><span>dinner</span>
														</li>
														<li class="trigger Free"><i class="lni lni-direction"></i><span>direction</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-direction-alt"></i><span>direction-alt</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-direction-ltr"></i><span>direction-ltr</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-direction-rtl"></i><span>direction-rtl</span>
														</li>
														<li class="trigger Free"><i class="lni lni-discord"></i><span>discord</span>
														</li>
														<li class="trigger Free"><i class="lni lni-discover"></i><span>discover</span>
														</li>
														<li class="trigger Free"><i class="lni lni-display"></i><span>display</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-display-alt"></i><span>display-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-docker"></i><span>docker</span>
														</li>
														<li class="trigger Free"><i class="lni lni-dollar"></i><span>dollar</span>
														</li>
														<li class="trigger Free"><i class="lni lni-domain"></i><span>domain</span>
														</li>
														<li class="trigger Free"><i class="lni lni-download"></i><span>download</span>
														</li>
														<li class="trigger Free"><i class="lni lni-dribbble"></i><span>dribbble</span>
														</li>
														<li class="trigger Free"><i class="lni lni-drop"></i><span>drop</span>
														</li>
														<li class="trigger Free"><i class="lni lni-dropbox"></i><span>dropbox</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-dropbox-original"></i><span>dropbox-original</span>
														</li>
														<li class="trigger Free"><i class="lni lni-drupal"></i><span>drupal</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-drupal-original"></i><span>drupal-original</span>
														</li>
														<li class="trigger Free"><i class="lni lni-dumbbell"></i><span>dumbbell</span>
														</li>
														<li class="trigger Free"><i class="lni lni-edge"></i><span>edge</span>
														</li>
														<li class="trigger Free"><i class="lni lni-cool"></i><span>cool</span>
														</li>
														<li class="trigger Free"><i class="lni lni-friendly"></i><span>friendly</span>
														</li>
														<li class="trigger Free"><i class="lni lni-happy"></i><span>happy</span>
														</li>
														<li class="trigger Free"><i class="lni lni-sad"></i><span>sad</span>
														</li>
														<li class="trigger Free"><i class="lni lni-smile"></i><span>smile</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-speechless"></i><span>speechless</span></li>
														<li class="trigger Free"><i class="lni lni-suspect"></i><span>suspect</span>
														</li>
														<li class="trigger Free"><i class="lni lni-tounge"></i><span>tounge</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-empty-file"></i><span>empty-file</span></li>
														<li class="trigger Free"><i class="lni lni-enter"></i><span>enter</span>
														</li>
														<li class="trigger Free"><i class="lni lni-envato"></i><span>envato</span>
														</li>
														<li class="trigger Free"><i class="lni lni-envelope"></i><span>envelope</span>
														</li>
														<li class="trigger Free"><i class="lni lni-eraser"></i><span>eraser</span>
														</li>
														<li class="trigger Free"><i class="lni lni-euro"></i><span>euro</span>
														</li>
														<li class="trigger Free"><i class="lni lni-exit"></i><span>exit</span>
														</li>
														<li class="trigger Free"><i class="lni lni-exit-down"></i><span>exit-down</span>
														</li>
														<li class="trigger Free"><i class="lni lni-exit-up"></i><span>exit-up</span>
														</li>
														<li class="trigger Free"><i class="lni lni-eye"></i><span>eye</span>
														</li>
														<li class="trigger Free"><i class="lni lni-facebook"></i><span>facebook</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-facebook-filled"></i><span>facebook-filled</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-facebook-messenger"></i><span>facebook-messenger</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-facebook-original"></i><span>facebook-original</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-facebook-oval"></i><span>facebook-oval</span>
														</li>
														<li class="trigger Free"><i class="lni lni-figma"></i><span>figma</span>
														</li>
														<li class="trigger Free"><i class="lni lni-files"></i><span>files</span>
														</li>
														<li class="trigger Free"><i class="lni lni-firefox"></i><span>firefox</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-firefox-original"></i><span>firefox-original</span>
														</li>
														<li class="trigger Free"><i class="lni lni-fireworks"></i><span>fireworks</span>
														</li>
														<li class="trigger Free"><i class="lni lni-first-aid"></i><span>first-aid</span>
														</li>
														<li class="trigger Free"><i class="lni lni-flag"></i><span>flag</span>
														</li>
														<li class="trigger Free"><i class="lni lni-flag-alt"></i><span>flag-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-flags"></i><span>flags</span>
														</li>
														<li class="trigger Free"><i class="lni lni-flickr"></i><span>flickr</span>
														</li>
														<li class="trigger Free"><i class="lni lni-flower"></i><span>flower</span>
														</li>
														<li class="trigger Free"><i class="lni lni-folder"></i><span>folder</span>
														</li>
														<li class="trigger Free"><i class="lni lni-forward"></i><span>forward</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-frame-expand"></i><span>frame-expand</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-fresh-juice"></i><span>fresh-juice</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-full-screen"></i><span>full-screen</span>
														</li>
														<li class="trigger Free"><i class="lni lni-funnel"></i><span>funnel</span>
														</li>
														<li class="trigger Free"><i class="lni lni-gallery"></i><span>gallery</span>
														</li>
														<li class="trigger Free"><i class="lni lni-game"></i><span>game</span>
														</li>
														<li class="trigger Free"><i class="lni lni-gift"></i><span>gift</span>
														</li>
														<li class="trigger Free"><i class="lni lni-git"></i><span>git</span>
														</li>
														<li class="trigger Free"><i class="lni lni-github"></i><span>github</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-github-original"></i><span>github-original</span>
														</li>
														<li class="trigger Free"><i class="lni lni-goodreads"></i><span>goodreads</span>
														</li>
														<li class="trigger Free"><i class="lni lni-google"></i><span>google</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-google-drive"></i><span>google-drive</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-google-pay"></i><span>google-pay</span></li>
														<li class="trigger Free"><i
															class="lni lni-google-wallet"></i><span>google-wallet</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-graduation"></i><span>graduation</span></li>
														<li class="trigger Free"><i class="lni lni-graph"></i><span>graph</span>
														</li>
														<li class="trigger Free"><i class="lni lni-grid"></i><span>grid</span>
														</li>
														<li class="trigger Free"><i class="lni lni-grid-alt"></i><span>grid-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-grow"></i><span>grow</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-hacker-news"></i><span>hacker-news</span>
														</li>
														<li class="trigger Free"><i class="lni lni-hammer"></i><span>hammer</span>
														</li>
														<li class="trigger Free"><i class="lni lni-hand"></i><span>hand</span>
														</li>
														<li class="trigger Free"><i class="lni lni-handshake"></i><span>handshake</span>
														</li>
														<li class="trigger Free"><i class="lni lni-harddrive"></i><span>harddrive</span>
														</li>
														<li class="trigger Free"><i class="lni lni-headphone"></i><span>headphone</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-headphone-alt"></i><span>headphone-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-heart"></i><span>heart</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-heart-filled"></i><span>heart-filled</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-heart-monitor"></i><span>heart-monitor</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-helicopter"></i><span>helicopter</span></li>
														<li class="trigger Free"><i class="lni lni-helmet"></i><span>helmet</span>
														</li>
														<li class="trigger Free"><i class="lni lni-help"></i><span>help</span>
														</li>
														<li class="trigger Free"><i class="lni lni-highlight"></i><span>highlight</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-highlight-alt"></i><span>highlight-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-home"></i><span>home</span>
														</li>
														<li class="trigger Free"><i class="lni lni-hospital"></i><span>hospital</span>
														</li>
														<li class="trigger Free"><i class="lni lni-hourglass"></i><span>hourglass</span>
														</li>
														<li class="trigger Free"><i class="lni lni-html5"></i><span>html5</span>
														</li>
														<li class="trigger Free"><i class="lni lni-image"></i><span>image</span>
														</li>
														<li class="trigger Free"><i class="lni lni-inbox"></i><span>inbox</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-indent-decrease"></i><span>indent-decrease</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-indent-increase"></i><span>indent-increase</span>
														</li>
														<li class="trigger Free"><i class="lni lni-infinite"></i><span>infinite</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-information"></i><span>information</span>
														</li>
														<li class="trigger Free"><i class="lni lni-instagram"></i><span>instagram</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-instagram-filled"></i><span>instagram-filled</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-instagram-original"></i><span>instagram-original</span>
														</li>
														<li class="trigger Free"><i class="lni lni-invention"></i><span>invention</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-invest-monitor"></i><span>invest-monitor</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-investment"></i><span>investment</span></li>
														<li class="trigger Free"><i class="lni lni-island"></i><span>island</span>
														</li>
														<li class="trigger Free"><i class="lni lni-italic"></i><span>italic</span>
														</li>
														<li class="trigger Free"><i class="lni lni-java"></i><span>java</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-javascript"></i><span>javascript</span></li>
														<li class="trigger Free"><i class="lni lni-jcb"></i><span>jcb</span>
														</li>
														<li class="trigger Free"><i class="lni lni-joomla"></i><span>joomla</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-joomla-original"></i><span>joomla-original</span>
														</li>
														<li class="trigger Free"><i class="lni lni-jsfiddle"></i><span>jsfiddle</span>
														</li>
														<li class="trigger Free"><i class="lni lni-juice"></i><span>juice</span>
														</li>
														<li class="trigger Free"><i class="lni lni-key"></i><span>key</span>
														</li>
														<li class="trigger Free"><i class="lni lni-keyboard"></i><span>keyboard</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-keyword-research"></i><span>keyword-research</span>
														</li>
														<li class="trigger Free"><i class="lni lni-laptop"></i><span>laptop</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-laptop-phone"></i><span>laptop-phone</span>
														</li>
														<li class="trigger Free"><i class="lni lni-laravel"></i><span>laravel</span>
														</li>
														<li class="trigger Free"><i class="lni lni-layers"></i><span>layers</span>
														</li>
														<li class="trigger Free"><i class="lni lni-layout"></i><span>layout</span>
														</li>
														<li class="trigger Free"><i class="lni lni-leaf"></i><span>leaf</span>
														</li>
														<li class="trigger Free"><i class="lni lni-library"></i><span>library</span>
														</li>
														<li class="trigger Free"><i class="lni lni-lifering"></i><span>lifering</span>
														</li>
														<li class="trigger Free"><i class="lni lni-line"></i><span>line</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-line-dashed"></i><span>line-dashed</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-line-dotted"></i><span>line-dotted</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-line-double"></i><span>line-double</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-line-spacing"></i><span>line-spacing</span>
														</li>
														<li class="trigger Free"><i class="lni lni-lineicons"></i><span>lineicons</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-lineicons-alt"></i><span>lineicons-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-link"></i><span>link</span>
														</li>
														<li class="trigger Free"><i class="lni lni-linkedin"></i><span>linkedin</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-linkedin-original"></i><span>linkedin-original</span>
														</li>
														<li class="trigger Free"><i class="lni lni-list"></i><span>list</span>
														</li>
														<li class="trigger Free"><i class="lni lni-lock"></i><span>lock</span>
														</li>
														<li class="trigger Free"><i class="lni lni-lock-alt"></i><span>lock-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-magnet"></i><span>magnet</span>
														</li>
														<li class="trigger Free"><i class="lni lni-magnifier"></i><span>magnifier</span>
														</li>
														<li class="trigger Free"><i class="lni lni-mailchimp"></i><span>mailchimp</span>
														</li>
														<li class="trigger Free"><i class="lni lni-map"></i><span>map</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-map-marker"></i><span>map-marker</span></li>
														<li class="trigger Free"><i class="lni lni-mashroom"></i><span>mashroom</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-mastercard"></i><span>mastercard</span></li>
														<li class="trigger Free"><i class="lni lni-medium"></i><span>medium</span>
														</li>
														<li class="trigger Free"><i class="lni lni-magento"></i><span>magento</span>
														</li>
														<li class="trigger Free"><i class="lni lni-menu"></i><span>menu</span>
														</li>
														<li class="trigger Free"><i class="lni lni-mic"></i><span>mic</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-microphone"></i><span>microphone</span></li>
														<li class="trigger Free"><i
															class="lni lni-microscope"></i><span>microscope</span></li>
														<li class="trigger Free"><i class="lni lni-microsoft"></i><span>microsoft</span>
														</li>
														<li class="trigger Free"><i class="lni lni-minus"></i><span>minus</span>
														</li>
														<li class="trigger Free"><i class="lni lni-mobile"></i><span>mobile</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-money-location"></i><span>money-location</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-money-protection"></i><span>money-protection</span>
														</li>
														<li class="trigger Free"><i class="lni lni-more"></i><span>more</span>
														</li>
														<li class="trigger Free"><i class="lni lni-more-alt"></i><span>more-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-mouse"></i><span>mouse</span>
														</li>
														<li class="trigger Free"><i class="lni lni-move"></i><span>move</span>
														</li>
														<li class="trigger Free"><i class="lni lni-music"></i><span>music</span>
														</li>
														<li class="trigger Free"><i class="lni lni-network"></i><span>network</span>
														</li>
														<li class="trigger Free"><i class="lni lni-night"></i><span>night</span>
														</li>
														<li class="trigger Free"><i class="lni lni-nodejs"></i><span>nodejs</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-nodejs-alt"></i><span>nodejs-alt</span></li>
														<li class="trigger Free"><i class="lni lni-notepad"></i><span>notepad</span>
														</li>
														<li class="trigger Free"><i class="lni lni-npm"></i><span>npm</span>
														</li>
														<li class="trigger Free"><i class="lni lni-offer"></i><span>offer</span>
														</li>
														<li class="trigger Free"><i class="lni lni-opera"></i><span>opera</span>
														</li>
														<li class="trigger Free"><i class="lni lni-package"></i><span>package</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-page-break"></i><span>page-break</span></li>
														<li class="trigger Free"><i
															class="lni lni-pagination"></i><span>pagination</span></li>
														<li class="trigger Free"><i
															class="lni lni-paint-bucket"></i><span>paint-bucket</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-paint-roller"></i><span>paint-roller</span>
														</li>
														<li class="trigger Free"><i class="lni lni-pallet"></i><span>pallet</span>
														</li>
														<li class="trigger Free"><i class="lni lni-paperclip"></i><span>paperclip</span>
														</li>
														<li class="trigger Free"><i class="lni lni-patreon"></i><span>patreon</span>
														</li>
														<li class="trigger Free"><i class="lni lni-pause"></i><span>pause</span>
														</li>
														<li class="trigger Free"><i class="lni lni-paypal"></i><span>paypal</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-paypal-original"></i><span>paypal-original</span>
														</li>
														<li class="trigger Free"><i class="lni lni-pencil"></i><span>pencil</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-pencil-alt"></i><span>pencil-alt</span></li>
														<li class="trigger Free"><i class="lni lni-phone"></i><span>phone</span>
														</li>
														<li class="trigger Free"><i class="lni lni-phone-set"></i><span>phone-set</span>
														</li>
														<li class="trigger Free"><i class="lni lni-php"></i><span>php</span>
														</li>
														<li class="trigger Free"><i class="lni lni-pie-chart"></i><span>pie-chart</span>
														</li>
														<li class="trigger Free"><i class="lni lni-pilcrow"></i><span>pilcrow</span>
														</li>
														<li class="trigger Free"><i class="lni lni-pin"></i><span>pin</span>
														</li>
														<li class="trigger Free"><i class="lni lni-pinterest"></i><span>pinterest</span>
														</li>
														<li class="trigger Free"><i class="lni lni-pizza"></i><span>pizza</span>
														</li>
														<li class="trigger Free"><i class="lni lni-plane"></i><span>plane</span>
														</li>
														<li class="trigger Free"><i class="lni lni-play"></i><span>play</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-play-store"></i><span>play-store</span></li>
														<li class="trigger Free"><i class="lni lni-plug"></i><span>plug</span>
														</li>
														<li class="trigger Free"><i class="lni lni-plus"></i><span>plus</span>
														</li>
														<li class="trigger Free"><i class="lni lni-pointer"></i><span>pointer</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-pointer-down"></i><span>pointer-down</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-pointer-left"></i><span>pointer-left</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-pointer-right"></i><span>pointer-right</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-pointer-top"></i><span>pointer-top</span>
														</li>
														<li class="trigger Free"><i class="lni lni-popup"></i><span>popup</span>
														</li>
														<li class="trigger Free"><i class="lni lni-postcard"></i><span>postcard</span>
														</li>
														<li class="trigger Free"><i class="lni lni-pound"></i><span>pound</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-power-switch"></i><span>power-switch</span>
														</li>
														<li class="trigger Free"><i class="lni lni-printer"></i><span>printer</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-producthunt"></i><span>producthunt</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-protection"></i><span>protection</span></li>
														<li class="trigger Free"><i class="lni lni-pulse"></i><span>pulse</span>
														</li>
														<li class="trigger Free"><i class="lni lni-pyramids"></i><span>pyramids</span>
														</li>
														<li class="trigger Free"><i class="lni lni-python"></i><span>python</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-question-circle"></i><span>question-circle</span>
														</li>
														<li class="trigger Free"><i class="lni lni-quora"></i><span>quora</span>
														</li>
														<li class="trigger Free"><i class="lni lni-quotation"></i><span>quotation</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-radio-button"></i><span>radio-button</span>
														</li>
														<li class="trigger Free"><i class="lni lni-rain"></i><span>rain</span>
														</li>
														<li class="trigger Free"><i class="lni lni-react"></i><span>react</span>
														</li>
														<li class="trigger Free"><i class="lni lni-reddit"></i><span>reddit</span>
														</li>
														<li class="trigger Free"><i class="lni lni-reload"></i><span>reload</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-remove-file"></i><span>remove-file</span>
														</li>
														<li class="trigger Free"><i class="lni lni-reply"></i><span>reply</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-restaurant"></i><span>restaurant</span></li>
														<li class="trigger Free"><i class="lni lni-revenue"></i><span>revenue</span>
														</li>
														<li class="trigger Free"><i class="lni lni-road"></i><span>road</span>
														</li>
														<li class="trigger Free"><i class="lni lni-rocket"></i><span>rocket</span>
														</li>
														<li class="trigger Free"><i class="lni lni-rss-feed"></i><span>rss-feed</span>
														</li>
														<li class="trigger Free"><i class="lni lni-ruler"></i><span>ruler</span>
														</li>
														<li class="trigger Free"><i class="lni lni-ruler-alt"></i><span>ruler-alt</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-ruler-pencil"></i><span>ruler-pencil</span>
														</li>
														<li class="trigger Free"><i class="lni lni-rupee"></i><span>rupee</span>
														</li>
														<li class="trigger Free"><i class="lni lni-save"></i><span>save</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-school-bench"></i><span>school-bench</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-school-bench-alt"></i><span>school-bench-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-scooter"></i><span>scooter</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-scroll-down"></i><span>scroll-down</span>
														</li>
														<li class="trigger Free"><i class="lni lni-search"></i><span>search</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-search-alt"></i><span>search-alt</span></li>
														<li class="trigger Free"><i class="lni lni-select"></i><span>select</span>
														</li>
														<li class="trigger Free"><i class="lni lni-seo"></i><span>seo</span>
														</li>
														<li class="trigger Free"><i class="lni lni-service"></i><span>service</span>
														</li>
														<li class="trigger Free"><i class="lni lni-share"></i><span>share</span>
														</li>
														<li class="trigger Free"><i class="lni lni-share-alt"></i><span>share-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-shield"></i><span>shield</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-shift-left"></i><span>shift-left</span></li>
														<li class="trigger Free"><i
															class="lni lni-shift-right"></i><span>shift-right</span>
														</li>
														<li class="trigger Free"><i class="lni lni-ship"></i><span>ship</span>
														</li>
														<li class="trigger Free"><i class="lni lni-shopify"></i><span>shopify</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-shopping-basket"></i><span>shopping-basket</span>
														</li>
														<li class="trigger Free"><i class="lni lni-shortcode"></i><span>shortcode</span>
														</li>
														<li class="trigger Free"><i class="lni lni-shovel"></i><span>shovel</span>
														</li>
														<li class="trigger Free"><i class="lni lni-shuffle"></i><span>shuffle</span>
														</li>
														<li class="trigger Free"><i class="lni lni-signal"></i><span>signal</span>
														</li>
														<li class="trigger Free"><i class="lni lni-sketch"></i><span>sketch</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-skipping-rope"></i><span>skipping-rope</span>
														</li>
														<li class="trigger Free"><i class="lni lni-skype"></i><span>skype</span>
														</li>
														<li class="trigger Free"><i class="lni lni-slack"></i><span>slack</span>
														</li>
														<li class="trigger Free"><i class="lni lni-slice"></i><span>slice</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-slideshare"></i><span>slideshare</span></li>
														<li class="trigger Free"><i class="lni lni-slim"></i><span>slim</span>
														</li>
														<li class="trigger Free"><i class="lni lni-snapchat"></i><span>snapchat</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-sort-alpha-asc"></i><span>sort-alpha-asc</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-sort-amount-asc"></i><span>sort-amount-asc</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-sort-amount-dsc"></i><span>sort-amount-dsc</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-soundcloud-original"></i><span>soundcloud-original</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-soundcloud"></i><span>soundcloud</span></li>
														<li class="trigger Free"><i
															class="lni lni-spellcheck"></i><span>spellcheck</span></li>
														<li class="trigger Free"><i
															class="lni lni-spinner-solid"></i><span>spinner-solid</span>
														</li>
														<li class="trigger Free"><i class="lni lni-spinner"></i><span>spinner</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-spinner-arrow"></i><span>spinner-arrow</span>
														</li>
														<li class="trigger Free"><i class="lni lni-spotify"></i><span>spotify</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-spotify-original"></i><span>spotify-original</span>
														</li>
														<li class="trigger Free"><i class="lni lni-spray"></i><span>spray</span>
														</li>
														<li class="trigger Free"><i class="lni lni-sprout"></i><span>sprout</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-stackoverflow"></i><span>stackoverflow</span>
														</li>
														<li class="trigger Free"><i class="lni lni-stamp"></i><span>stamp</span>
														</li>
														<li class="trigger Free"><i class="lni lni-star"></i><span>star</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-star-empty"></i><span>star-empty</span></li>
														<li class="trigger Free"><i
															class="lni lni-star-filled"></i><span>star-filled</span>
														</li>
														<li class="trigger Free"><i class="lni lni-star-half"></i><span>star-half</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-stats-down"></i><span>stats-down</span></li>
														<li class="trigger Free"><i class="lni lni-stats-up"></i><span>stats-up</span>
														</li>
														<li class="trigger Free"><i class="lni lni-steam"></i><span>steam</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-sthethoscope"></i><span>sthethoscope</span>
														</li>
														<li class="trigger Free"><i class="lni lni-stop"></i><span>stop</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-strikethrough"></i><span>strikethrough</span>
														</li>
														<li class="trigger Free"><i class="lni lni-stripe"></i><span>stripe</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-stumbleupon"></i><span>stumbleupon</span>
														</li>
														<li class="trigger Free"><i class="lni lni-sun"></i><span>sun</span>
														</li>
														<li class="trigger Free"><i class="lni lni-support"></i><span>support</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-surf-board"></i><span>surf-board</span></li>
														<li class="trigger Free"><i class="lni lni-swift"></i><span>swift</span>
														</li>
														<li class="trigger Free"><i class="lni lni-syringe"></i><span>syringe</span>
														</li>
														<li class="trigger Free"><i class="lni lni-tab"></i><span>tab</span>
														</li>
														<li class="trigger Free"><i class="lni lni-tag"></i><span>tag</span>
														</li>
														<li class="trigger Free"><i class="lni lni-target"></i><span>target</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-target-customer"></i><span>target-customer</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-target-revenue"></i><span>target-revenue</span>
														</li>
														<li class="trigger Free"><i class="lni lni-taxi"></i><span>taxi</span>
														</li>
														<li class="trigger Free"><i class="lni lni-teabag"></i><span>teabag</span>
														</li>
														<li class="trigger Free"><i class="lni lni-telegram"></i><span>telegram</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-telegram-original"></i><span>telegram-original</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-text-align-center"></i><span>text-align-center</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-text-align-justify"></i><span>text-align-justify</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-text-align-left"></i><span>text-align-left</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-text-align-right"></i><span>text-align-right</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-text-format"></i><span>text-format</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-text-format-remove"></i><span>text-format-remove</span>
														</li>
														<li class="trigger Free"><i class="lni lni-thought"></i><span>thought</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-thumbs-down"></i><span>thumbs-down</span>
														</li>
														<li class="trigger Free"><i class="lni lni-thumbs-up"></i><span>thumbs-up</span>
														</li>
														<li class="trigger Free"><i class="lni lni-thunder"></i><span>thunder</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-thunder-alt"></i><span>thunder-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-ticket"></i><span>ticket</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-ticket-alt"></i><span>ticket-alt</span></li>
														<li class="trigger Free"><i class="lni lni-timer"></i><span>timer</span>
														</li>
														<li class="trigger Free"><i class="lni lni-train"></i><span>train</span>
														</li>
														<li class="trigger Free"><i class="lni lni-train-alt"></i><span>train-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-trash-can"></i><span>trash-can</span>
														</li>
														<li class="trigger Free"><i class="lni lni-travel"></i><span>travel</span>
														</li>
														<li class="trigger Free"><i class="lni lni-tree"></i><span>tree</span>
														</li>
														<li class="trigger Free"><i class="lni lni-trees"></i><span>trees</span>
														</li>
														<li class="trigger Free"><i class="lni lni-trello"></i><span>trello</span>
														</li>
														<li class="trigger Free"><i class="lni lni-trowel"></i><span>trowel</span>
														</li>
														<li class="trigger Free"><i class="lni lni-tshirt"></i><span>tshirt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-tumblr"></i><span>tumblr</span>
														</li>
														<li class="trigger Free"><i class="lni lni-twitch"></i><span>twitch</span>
														</li>
														<li class="trigger Free"><i class="lni lni-twitter"></i><span>twitter</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-twitter-filled"></i><span>twitter-filled</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-twitter-original"></i><span>twitter-original</span>
														</li>
														<li class="trigger Free"><i class="lni lni-ubuntu"></i><span>ubuntu</span>
														</li>
														<li class="trigger Free"><i class="lni lni-underline"></i><span>underline</span>
														</li>
														<li class="trigger Free"><i class="lni lni-unlink"></i><span>unlink</span>
														</li>
														<li class="trigger Free"><i class="lni lni-unlock"></i><span>unlock</span>
														</li>
														<li class="trigger Free"><i class="lni lni-upload"></i><span>upload</span>
														</li>
														<li class="trigger Free"><i class="lni lni-user"></i><span>user</span>
														</li>
														<li class="trigger Free"><i class="lni lni-users"></i><span>users</span>
														</li>
														<li class="trigger Free"><i class="lni lni-ux"></i><span>ux</span>
														</li>
														<li class="trigger Free"><i class="lni lni-vector"></i><span>vector</span>
														</li>
														<li class="trigger Free"><i class="lni lni-video"></i><span>video</span>
														</li>
														<li class="trigger Free"><i class="lni lni-vimeo"></i><span>vimeo</span>
														</li>
														<li class="trigger Free"><i class="lni lni-visa"></i><span>visa</span>
														</li>
														<li class="trigger Free"><i class="lni lni-vk"></i><span>vk</span>
														</li>
														<li class="trigger Free"><i class="lni lni-volume"></i><span>volume</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-volume-high"></i><span>volume-high</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-volume-low"></i><span>volume-low</span></li>
														<li class="trigger Free"><i
															class="lni lni-volume-medium"></i><span>volume-medium</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-volume-mute"></i><span>volume-mute</span>
														</li>
														<li class="trigger Free"><i class="lni lni-wallet"></i><span>wallet</span>
														</li>
														<li class="trigger Free"><i class="lni lni-warning"></i><span>warning</span>
														</li>
														<li class="trigger Free"><i class="lni lni-website"></i><span>website</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-website-alt"></i><span>website-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-wechat"></i><span>wechat</span>
														</li>
														<li class="trigger Free"><i class="lni lni-weight"></i><span>weight</span>
														</li>
														<li class="trigger Free"><i class="lni lni-whatsapp"></i><span>whatsapp</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-wheelbarrow"></i><span>wheelbarrow</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-wheelchair"></i><span>wheelchair</span></li>
														<li class="trigger Free"><i class="lni lni-windows"></i><span>windows</span>
														</li>
														<li class="trigger Free"><i class="lni lni-wordpress"></i><span>wordpress</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-wordpress-filled"></i><span>wordpress-filled</span>
														</li>
														<li class="trigger Free"><i class="lni lni-world"></i><span>world</span>
														</li>
														<li class="trigger Free"><i class="lni lni-world-alt"></i><span>world-alt</span>
														</li>
														<li class="trigger Free"><i class="lni lni-write"></i><span>write</span>
														</li>
														<li class="trigger Free"><i class="lni lni-yahoo"></i><span>yahoo</span>
														</li>
														<li class="trigger Free"><i
															class="lni lni-ycombinator"></i><span>ycombinator</span>
														</li>
														<li class="trigger Free"><i class="lni lni-yen"></i><span>yen</span>
														</li>
														<li class="trigger Free"><i class="lni lni-youtube"></i><span>youtube</span>
														</li>
														<li class="trigger Free"><i class="lni lni-zip"></i><span>zip</span>
														</li>
														<li class="trigger Free"><i class="lni lni-zoom-in"></i><span>zoom-in</span>
														</li>
														<li class="trigger Free"><i class="lni lni-zoom-out"></i><span>zoom-out</span>
														</li>
													</ul>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- end card -->
						</div>
						<!-- end col -->
					</div>
					<!-- end row -->
				</div>
				<!-- title-wrapper start -->
				<div class="title-wrapper pt-30">
					<div class="row align-items-center">
						<div class="col-md-12">
							<div class="title mb-30">
								<h4>Various Tables</h4>
							</div>
						</div>
					</div>
				</div>
				<!-- title-wrapper end -->
				<!-- tables-wrapper start -->
				<div class="tables-wrapper">
					<div class="row">
						<div class="col-lg-12">
							<div class="card-style mb-30">
								<h6 class="mb-10">Simple Table With Search</h6>
								<div class="custom-table-filter">
									<div class="row">
										<div class="col-lg-12">
											<div class="custom-table-search">
												<label>Search: <input type="search" class=""
													placeholder="" aria-controls="search_system"></label>
											</div>
										</div>
									</div>
								</div>
								<div class="table-wrapper table-responsive custom-table">
									<table class="table">
										<thead>
											<tr>
												<th><h6>Sr No</h6></th>
												<th><h6>Lead</h6></th>
												<th><h6>Email</h6> <span class="mandatory table-note">mention
														the note sentence style</span></th>
												<th><h6>Phone No</h6></th>
												<th><h6>Company</h6></th>
												<th><h6>Action</h6></th>
											</tr>
											<!-- end table row-->
										</thead>
										<tbody>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Courtney Henry</p>
												</td>
												<td>
													<p>
														<a href="#0">yourmail@gmail.com</a>
													</p>
												</td>
												<td>
													<p>(303)555 3343523</p>
												</td>
												<td>
													<p>UIdeck digital agency</p>
												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>

													<p>Meet</p>

												</td>
												<td>
													<p>
														<a href="#0">yourmail@gmail.com</a>
													</p>
												</td>
												<td>
													<p>(303)555 3343523</p>
												</td>
												<td>
													<p>Graygrids digital agency</p>
												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>

													<p>David Smith</p>

												</td>
												<td>
													<p>
														<a href="#0">yourmail@gmail.com</a>
													</p>
												</td>
												<td>
													<p>(303)555 3343523</p>
												</td>
												<td>
													<p>Abc agency</p>
												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>

													<p>Jonathon</p>

												</td>
												<td>
													<p>
														<a href="#0">yourmail@gmail.com</a>
													</p>
												</td>
												<td>
													<p>(303)555 3343523</p>
												</td>
												<td>
													<p>Creative IT Agency</p>
												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>

													<p>Anna Lee</p>

												</td>
												<td>
													<p>
														<a href="#0">yourmail@gmail.com</a>
													</p>
												</td>
												<td>
													<p>(303)555 3343523</p>
												</td>
												<td>
													<p>Halal It agency</p>
												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>

													<p>Gray Simon</p>

												</td>
												<td>
													<p>
														<a href="#0">yourmail@gmail.com</a>
													</p>
												</td>
												<td>
													<p>(303)555 3343523</p>
												</td>
												<td>
													<p>DesignCourse</p>
												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->
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
					<div class="row">
						<div class="col-lg-12">
							<div class="card-style mb-30">
								<h6 class="mb-10">Table With Inputs</h6>
								<div class="table-wrapper table-responsive custom-table">
									<table class="table ">
										<thead>
											<tr>
												<th><h6>Select</h6></th>
												<th><h6>Account No</h6></th>
												<th><h6>Inputs</h6></th>
												<th><h6>Action</h6></th>
											</tr>
											<!-- end table row-->
										</thead>
										<tbody>
											<tr>
												<td>
													<div class="check-input-primary">
														<input class="form-check-input" type="checkbox"
															id="checkbox-1" />
													</div>
												</td>
												<td>
													<p>AC336 508 2157</p>
												</td>
												<td>
													<div class="input-style-1">
														<input type="text" placeholder="Full Name">
													</div>
												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->
											<tr>
												<td>
													<div class="check-input-primary">
														<input class="form-check-input" type="checkbox"
															id="checkbox-2" />
													</div>
												</td>
												<td>
													<p>AC336 756 0987</p>
												</td>
												<td>
													<div class="input-style-1">
														<input type="text" placeholder="Full Name">
													</div>
												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->

											<!-- end table row -->
											<tr>
												<td>
													<div class="check-input-primary">
														<input class="form-check-input" type="checkbox"
															id="checkbox-3" />
													</div>
												</td>
												<td>
													<p>Custom Single Select (singleselect form-control
														form-control-lg)</p>
												</td>
												<td>

													<div class="select-style-1">

														<div class="select-position">
															<select class="singleselect form-control form-control-lg">
																<option value="0">Select</option>
																<option value="1">Custom Single Select
																	(singleselect form-control form-control-lg)</option>
																<option value="2">Category two</option>
																<option value="3">Category three</option>
															</select>
														</div>
													</div>

												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->
											<tr>
												<td>
													<div class="check-input-primary">
														<input class="form-check-input" type="checkbox"
															id="checkbox-4" />
													</div>
												</td>
												<td>
													<p>Search With Select (select2 form-control
														form-control-lg)</p>
												</td>
												<td>

													<div class="select-style-1">

														<div class="select-position">
															<select name="" id=""
																class="select2 form-control form-control-lg">
																<option value="0">Select</option>
																<option>Select category</option>
																<option>Select category 1</option>
																<option>Select category 2</option>
															</select>
														</div>
													</div>

												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->
											<tr>
												<td>
													<div class="check-input-primary">
														<input class="form-check-input" type="checkbox"
															id="checkbox-3" />
													</div>
												</td>
												<td>
													<p>Single Select (Bootstrap Default)</p>
												</td>
												<td>
													<div class="select-style-1">

														<div class="select-position">
															<select required>
																<option value="" selected>Select category</option>
																<option value="1">Category one</option>
																<option value="2">Category two</option>
																<option value="3">Category three</option>
															</select>
														</div>
													</div>
												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td>
													<div class="check-input-primary">
														<input class="form-check-input" type="checkbox"
															id="checkbox-5" />
													</div>
												</td>
												<td>
													<p>AC336 654 0987</p>
												</td>
												<td>
													<div class="input-style-form-check">

														<div class="form-check radio-style">
															<input type="radio" id="radio11" name="gender"
																class="form-check-input" value="0"> <label
																for="radio11" class="form-check-label">Small
																Radio 1</label>
														</div>
														<div class="form-check radio-style">
															<input type="radio" id="radio12" name="gender"
																class="form-check-input" value="1"> <label
																for="radio12" class="form-check-label">Small
																Radio 2</label>
														</div>

													</div>
												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->
											<tr>
												<td>
													<div class="check-input-primary">
														<input class="form-check-input" type="checkbox"
															id="checkbox-6" />
													</div>
												</td>
												<td>
													<p>AC336 234 9804</p>
												</td>
												<td>
													<div class="input-style-form-check_block">

														<div class="form-check radio-style">
															<input type="radio" id="radio21" name="gender"
																class="form-check-input" value="0"> <label
																for="radio21" class="form-check-label">Large
																Radio 1</label>
														</div>
														<div class="form-check radio-style">
															<input type="radio" id="radio22" name="gender"
																class="form-check-input" value="1"> <label
																for="radio22" class="form-check-label">Large
																Radio 2</label>
														</div>

													</div>
												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->
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

					<div class="row">
						<div class="col-lg-12">
							<div class="card-style mb-30">
								<h6 class="mb-10">Table With No Data</h6>
								<div class="table-wrapper table-responsive custom-table">
									<table class="table ">
										<thead>
											<tr>
												<th><h6>Select</h6></th>
												<th><h6>Account No</h6></th>
												<th><h6>Inputs</h6></th>
												<th><h6>Action</h6></th>
											</tr>
											<!-- end table row-->
										</thead>
										<tbody>
											<tr>
												<td colspan="4">
													<p class="no-data">No Data Available</p>
												</td>
											</tr>
											<!-- end table row -->


											<!-- end table row -->
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
					<div class="row">
						<div class="col-lg-12">
							<div class="card-style mb-30">

								<h6 class="mb-10">Hierarchical Table Header</h6>
								<div
									class="table-wrapper table-responsive custom-table custom-table-v2">
									<table class="table table-striped">
										<thead>
											<tr>
												<th colspan="5"><h6>Paper I</h6></th>
											</tr>
											<tr>
												<th colspan="1"><h6>Time : 3 Hours</h6></th>
												<th colspan="3"><h6>All questions are compulsory</h6></th>
												<th colspan="1"><h6>Total Marks : 100</h6></th>
											</tr>
											<tr>
												<th><h6>Lead</h6></th>
												<th><h6>Email</h6></th>
												<th><h6>Phone No</h6></th>
												<th><h6>Company</h6></th>
												<th><h6>Action</h6></th>
											</tr>

											<!-- end table row-->
										</thead>
										<tbody>

										</tbody>
									</table>
									<!-- end table -->
								</div>

								<h6 class="mb-10">Multi-purpose Customized Table</h6>
								<div
									class="table-wrapper table-responsive custom-table custom-table-v2">
									<table class="table table-striped">
										<thead>
											<tr>
												<th><h6>Lead</h6></th>
												<th><h6>Email</h6></th>
												<th><h6>Phone No</h6></th>
												<th><h6>Company</h6></th>
												<th><h6>Action</h6></th>
											</tr>
											<!-- end table row-->
										</thead>
										<tbody>
											<tr>
												<td>

													<p>Courtney Henry</p>

												</td>
												<td>
													<p>
														<a href="#0">yourmail@gmail.com</a>
													</p>
												</td>
												<td>
													<p>(303)555 3343523</p>
												</td>
												<td>
													<p>UIdeck digital agency</p>
												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->
											<tr>
												<td>

													<p>Meet</p>

												</td>
												<td>
													<p>
														<a href="#0">yourmail@gmail.com</a>
													</p>
												</td>
												<td>
													<p>(303)555 3343523</p>
												</td>
												<td>
													<p>Graygrids digital agency</p>
												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->
											<tr>
												<td>

													<p>David Smith</p>

												</td>
												<td>
													<p>
														<a href="#0">yourmail@gmail.com</a>
													</p>
												</td>
												<td>
													<p>(303)555 3343523</p>
												</td>
												<td>
													<p>Abc agency</p>
												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->
											<tr>
												<td>

													<p>Jonathon</p>

												</td>
												<td>
													<p>
														<a href="#0">yourmail@gmail.com</a>
													</p>
												</td>
												<td>
													<p>(303)555 3343523</p>
												</td>
												<td>
													<p>Creative IT Agency</p>
												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->
											<tr>
												<td>

													<p>Anna Lee</p>

												</td>
												<td>
													<p>
														<a href="#0">yourmail@gmail.com</a>
													</p>
												</td>
												<td>
													<p>(303)555 3343523</p>
												</td>
												<td>
													<p>Halal It agency</p>
												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->
											<tr>
												<td>

													<p>Gray Simon</p>

												</td>
												<td>
													<p>
														<a href="#0">yourmail@gmail.com</a>
													</p>
												</td>
												<td>
													<p>(303)555 3343523</p>
												</td>
												<td>
													<p>DesignCourse</p>
												</td>
												<td>
													<ul class="buttons-group mainbtn daobtn action">
														<li><a href="#"
															class="main-btn danger-btn btn-hover btn-sm btnremove"
															title="Remove"><i class="lni lni-trash-can"></i></a></li>
													</ul>
												</td>
											</tr>
											<!-- end table row -->
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
				<!-- tables-wrapper end -->
				<!-- title-wrapper start -->
				<div class="title-wrapper pt-30">
					<div class="row align-items-center">
						<div class="col-md-12">
							<div class="title mb-30">
								<h2>Below All Design For Designer Future Purpose</h2>
							</div>
						</div>
					</div>
				</div>
				<!-- title-wrapper end -->
				<!-- button-cards-wrapper start -->
				<div class="buttons-cards-wrapper">
					<div class="row">
						<div class="col-12 col-lg-6 col-md-12 col-sm-12">
							<div class="card-style mb-30">
								<h5 class="text-medium mb-10">Square Buttons</h5>
								<ul class="buttons-group">
									<li><a href="#0"
										class="main-btn primary-btn square-btn btn-hover">Primary</a></li>
									<li><a href="#0"
										class="main-btn secondary-btn square-btn btn-hover">Secondary</a>
									</li>
									<li><a href="#0"
										class="main-btn success-btn square-btn btn-hover">Success</a></li>
									<li><a href="#0"
										class="main-btn danger-btn square-btn btn-hover">Danger</a></li>
									<li><a href="#0"
										class="main-btn warning-btn square-btn btn-hover">Warning</a></li>
									<li><a href="#0"
										class="main-btn info-btn square-btn btn-hover">Info</a></li>
									<li><a href="#0"
										class="main-btn dark-btn square-btn btn-hover">Dark</a></li>
									<li><a href="#0"
										class="main-btn light-btn square-btn btn-hover">Light</a></li>
									<li><a href="#0"
										class="main-btn active-btn square-btn btn-hover">Active</a></li>
									<li><a href="#0"
										class="main-btn deactive-btn square-btn btn-hover">Deactive</a>
									</li>
									<li><a href="#0"
										class="main-btn primary-btn square-btn btn-hover btn-iconic-icon"><i
											class="lni lni-home"></i>Primary</a></li>
								</ul>
							</div>
							<!-- end card -->
						</div>
						<!-- end col -->
						<div class="col-12 col-lg-6 col-md-12 col-sm-12">
							<div class="card-style mb-30">
								<h5 class="text-medium mb-10">
									Default Buttons <span class="text-sm text-regular">(4px
										Corner Round)</span>
								</h5>
								<ul class="buttons-group">
									<li><a href="#0" class="main-btn primary-btn btn-hover">Primary</a>
									</li>
									<li><a href="#0" class="main-btn secondary-btn btn-hover">Secondary</a>
									</li>
									<li><a href="#0" class="main-btn success-btn btn-hover">Success</a>
									</li>
									<li><a href="#0" class="main-btn danger-btn btn-hover">Danger</a>
									</li>
									<li><a href="#0" class="main-btn warning-btn btn-hover">Warning</a>
									</li>
									<li><a href="#0" class="main-btn info-btn btn-hover">Info</a>
									</li>
									<li><a href="#0" class="main-btn dark-btn btn-hover">Dark</a>
									</li>
									<li><a href="#0" class="main-btn light-btn btn-hover">Light</a>
									</li>
									<li><a href="#0" class="main-btn active-btn btn-hover">Active</a>
									</li>
									<li><a href="#0" class="main-btn deactive-btn btn-hover">Deactive</a>
									</li>
								</ul>
							</div>
							<!-- end card -->
						</div>
						<!-- end col -->
						<div class="col-12 col-lg-6 col-md-12 col-sm-12">
							<div class="card-style mb-30">
								<h5 class="text-medium mb-10">Square Outline Buttons</h5>
								<ul class="buttons-group">
									<li><a href="#0"
										class="main-btn primary-btn-outline square-btn btn-hover">Primary</a>
									</li>
									<li><a href="#0"
										class="main-btn secondary-btn-outline square-btn btn-hover">Secondary</a>
									<li><a href="#0"
										class="main-btn success-btn-outline square-btn btn-hover">Success</a>
									</li>
									<li><a href="#0"
										class="main-btn danger-btn-outline square-btn btn-hover">Danger</a>
									</li>
									<li><a href="#0"
										class="main-btn warning-btn-outline square-btn btn-hover">Warning</a>
									</li>
									<li><a href="#0"
										class="main-btn info-btn-outline square-btn btn-hover">Info</a>
									</li>
									<li><a href="#0"
										class="main-btn dark-btn-outline square-btn btn-hover">Dark</a>
									</li>
									<li><a href="#0"
										class="main-btn light-btn-outline square-btn btn-hover">Light</a>
									</li>
									<li><a href="#0"
										class="main-btn active-btn-outline square-btn btn-hover">Active</a>
									</li>
									<li><a href="#0"
										class="main-btn deactive-btn-outline square-btn btn-hover">Deactive</a>
									</li>
								</ul>
							</div>
							<!-- end card -->
						</div>
						<!-- end col -->
						<div class="col-12 col-lg-6 col-md-12 col-sm-12">
							<div class="card-style mb-30">
								<h5 class="text-medium mb-10">
									Default Buttons <span class="text-sm text-regular">(4px
										Corner Round)</span>
								</h5>
								<ul class="buttons-group">
									<li><a href="#0"
										class="main-btn primary-btn-outline btn-hover">Primary</a></li>
									<li><a href="#0"
										class="main-btn secondary-btn-outline btn-hover">Secondary</a></li>
									<li><a href="#0"
										class="main-btn success-btn-outline btn-hover">Success</a></li>
									<li><a href="#0"
										class="main-btn danger-btn-outline btn-hover">Danger</a></li>
									<li><a href="#0"
										class="main-btn warning-btn-outline btn-hover">Warning</a></li>
									<li><a href="#0"
										class="main-btn info-btn-outline btn-hover">Info</a></li>
									<li><a href="#0"
										class="main-btn dark-btn-outline btn-hover">Dark</a></li>
									<li><a href="#0"
										class="main-btn light-btn-outline btn-hover">Light</a></li>
									<li><a href="#0"
										class="main-btn active-btn-outline btn-hover">Active</a></li>
									<li><a href="#0"
										class="main-btn deactive-btn-outline btn-hover">Deactive</a></li>
								</ul>
							</div>
							<!-- end card -->
						</div>
						<!-- end col -->
						<div class="col-12 col-lg-6 col-md-12 col-sm-12">
							<div class="card-style mb-30">
								<h5 class="text-medium mb-10">
									Medium Rounded Buttons <span class="text-sm text-regular">
										(10px Corner Round) </span>
								</h5>
								<ul class="buttons-group">
									<li><a href="#0"
										class="main-btn primary-btn rounded-md btn-hover">Primary</a></li>
									<li><a href="#0"
										class="main-btn secondary-btn rounded-md btn-hover">Secondary</a>
									</li>
									<li><a href="#0"
										class="main-btn success-btn rounded-md btn-hover">Success</a></li>
									<li><a href="#0"
										class="main-btn danger-btn rounded-md btn-hover">Danger</a></li>
									<li><a href="#0"
										class="main-btn warning-btn rounded-md btn-hover">Warning</a></li>
									<li><a href="#0"
										class="main-btn info-btn rounded-md btn-hover">Info</a></li>
									<li><a href="#0"
										class="main-btn dark-btn rounded-md btn-hover">Dark</a></li>
									<li><a href="#0"
										class="main-btn light-btn rounded-md btn-hover">Light</a></li>
									<li><a href="#0"
										class="main-btn active-btn rounded-md btn-hover">Active</a></li>
									<li><a href="#0"
										class="main-btn deactive-btn rounded-md btn-hover">Deactive</a>
									</li>
								</ul>
							</div>
							<!-- end card -->
						</div>
						<!-- end col -->
						<div class="col-12 col-lg-6 col-md-12 col-sm-12">
							<div class="card-style mb-30">
								<h5 class="text-medium mb-10">
									Rounded Full Buttons <span class="text-sm text-regular">(30px
										Corner Round)</span>
								</h5>
								<ul class="buttons-group">
									<li><a href="#0"
										class="main-btn primary-btn rounded-full btn-hover">Primary</a>
									</li>
									<li><a href="#0"
										class="main-btn secondary-btn rounded-full btn-hover">Secondary</a>
									</li>
									<li><a href="#0"
										class="main-btn success-btn rounded-full btn-hover">Success</a>
									</li>
									<li><a href="#0"
										class="main-btn danger-btn rounded-full btn-hover">Danger</a></li>
									<li><a href="#0"
										class="main-btn warning-btn rounded-full btn-hover">Warning</a>
									</li>
									<li><a href="#0"
										class="main-btn info-btn rounded-full btn-hover">Info</a></li>
									<li><a href="#0"
										class="main-btn dark-btn rounded-full btn-hover">Dark</a></li>
									<li><a href="#0"
										class="main-btn light-btn rounded-full btn-hover">Light</a></li>
									<li><a href="#0"
										class="main-btn active-btn rounded-full btn-hover">Active</a></li>
									<li><a href="#0"
										class="main-btn deactive-btn rounded-full btn-hover">Deactive</a>
									</li>
								</ul>
							</div>
							<!-- end card -->
						</div>
						<!-- end col -->
						<div class="col-12 col-lg-6 col-md-12 col-sm-12">
							<div class="card-style mb-30">
								<h5 class="text-medium mb-10">
									Medium Rounded Outline Buttons <span
										class="text-sm text-regular"> (10px Corner Round) </span>
								</h5>
								<ul class="buttons-group">
									<li><a href="#0"
										class="main-btn primary-btn-outline rounded-md btn-hover">Primary</a>
									</li>
									<li><a href="#0"
										class="main-btn secondary-btn-outline rounded-md btn-hover">Secondary</a>
									</li>
									<li><a href="#0"
										class="main-btn success-btn-outline rounded-md btn-hover">Success</a>
									</li>
									<li><a href="#0"
										class="main-btn danger-btn-outline rounded-md btn-hover">Danger</a>
									</li>
									<li><a href="#0"
										class="main-btn warning-btn-outline rounded-md btn-hover">Warning</a>
									</li>
									<li><a href="#0"
										class="main-btn info-btn-outline rounded-md btn-hover">Info</a>
									</li>
									<li><a href="#0"
										class="main-btn dark-btn-outline rounded-md btn-hover">Dark</a>
									</li>
									<li><a href="#0"
										class="main-btn light-btn-outline rounded-md btn-hover">Light</a>
									</li>
									<li><a href="#0"
										class="main-btn active-btn-outline rounded-md btn-hover">Active</a>
									</li>
									<li><a href="#0"
										class="main-btn deactive-btn-outline rounded-md btn-hover">Deactive</a>
									</li>
								</ul>
							</div>
							<!-- end card -->
						</div>
						<!-- end col -->
						<div class="col-12 col-lg-6 col-md-12 col-sm-12">
							<div class="card-style mb-30">
								<h5 class="text-medium mb-10">
									Rounded Full Outline Buttons <span class="text-sm text-regular">(30px
										Corner Round)</span>
								</h5>
								<ul class="buttons-group">
									<li><a href="#0"
										class="main-btn primary-btn-outline rounded-full btn-hover">Primary</a>
									</li>
									<li><a href="#0"
										class="main-btn secondary-btn-outline rounded-full btn-hover">Secondary</a>
									</li>
									<li><a href="#0"
										class="main-btn success-btn-outline rounded-full btn-hover">Success</a>
									</li>
									<li><a href="#0"
										class="main-btn danger-btn-outline rounded-full btn-hover">Danger</a>
									</li>
									<li><a href="#0"
										class="main-btn warning-btn-outline rounded-full btn-hover">Warning</a>
									</li>
									<li><a href="#0"
										class="main-btn info-btn-outline rounded-full btn-hover">Info</a>
									</li>
									<li><a href="#0"
										class="main-btn dark-btn-outline rounded-full btn-hover">Dark</a>
									</li>
									<li><a href="#0"
										class="main-btn light-btn-outline rounded-full btn-hover">Light</a>
									</li>
									<li><a href="#0"
										class="main-btn active-btn-outline rounded-full btn-hover">Active</a>
									</li>
									<li><a href="#0"
										class="main-btn deactive-btn-outline rounded-full btn-hover">Deactive</a>
									</li>
								</ul>
							</div>
							<!-- end card -->
						</div>
						<!-- end col -->
					</div>
					<!-- end row -->
				</div>
			</div>
			<!-- button-cards-wrapper start -->
			<div class="modal fade custom-modal" tabindex="-1" role="dialog"
				aria-labelledby="myLargeModalLabel" aria-hidden="true"
				id="exampleModal">
				<div
					class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title">Modal title</h3>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<div class="custom-modal-inner" id="headData1" name="headData1">
								<div class="row">

									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Message</label>
											<textarea placeholder="Message" rows="5"></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<ul class="buttons-group">
								<li><a type="button" class="main-btn dark-btn n btn-hover"
									data-bs-dismiss="modal">Close</a></li>
								<li><a onclick="notification();"
									class="main-btn success-btn  btn-hover" type="button">Submit</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>


			<div class="modal fade custom-modal" tabindex="-1" role="dialog"
				aria-labelledby="myLargeModalLabel" aria-hidden="true"
				id="exampleModalone">
				<div
					class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title">Modal title</h3>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<div class="custom-modal-inner" id="headData1" name="headData1">
								<div class="row">


									<div class="col-lg-6 col-md-6 col-sm-12 col-12">

										<div class="select-style-1">
											<label>Custom Single Select (singleselect
												form-control form-control-lg)<span class="mandatory">*</span>
											</label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg">
													<option value="0">Select</option>
													<option value="1">Custom Single Select
														(singleselect form-control form-control-lg)</option>
													<option value="2">Category two</option>
													<option value="3">Category three</option>
												</select>
											</div>
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-sm-12 col-12">
										<div class="select-style-1">
											<label>Search With Select (select2 form-control
												form-control-lg)<span class="mandatory">*</span>
											</label>
											<div class="select-position">
												<select name="" id=""
													class="select2 form-control form-control-lg">
													<option value="0">Select</option>
													<option>Select category</option>
													<option>Select category 1</option>
													<option>Select category 2</option>
												</select>
											</div>
										</div>
									</div>

									<div class="col-lg-6 col-md-6 col-sm-12 col-12">
										<div class="select-style-1">
											<label>Single Select (Bootstrap Default)<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select required>
													<option value="" selected>Select category</option>
													<option value="1">Category one</option>
													<option value="2">Category two</option>
													<option value="3">Category three</option>
												</select>
											</div>
										</div>
									</div>


								</div>
							</div>
						</div>
						<div class="modal-footer">
							<ul class="buttons-group">
								<li><a type="button" class="main-btn dark-btn n btn-hover"
									data-bs-dismiss="modal">Close</a></li>
								<li><a onclick="notification();"
									class="main-btn success-btn  btn-hover" type="button">Submit</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- form-elements-wrapper end -->
	</div>
</section>
<!-- tab components end -->

<script nonce="${cspNonce}">
	$(document).ready(function() {
		var valid_dt = '${valid_dt}';
		var y = valid_dt.substring(0, 4);
		var m = valid_dt.substring(5, 7);
		var d = valid_dt.substring(8, 10);
		var valid_dt1 = d + "/" + m + "/" + y;
		var today = new Date();
		var yyyy = today.getFullYear();
		var mm = today.getMonth() + 1; // Months start at 0!
		var dd = today.getDate();
		if (dd < 10)
			dd = '0' + dd;
		if (mm < 10)
			mm = '0' + mm;
		today = dd + '/' + mm + '/' + yyyy;
		var valid_dt2 = valid_dt.split('/').reverse().join('-');
		var today2 = today.split('/').reverse().join('-');
		if ("${hid}" != "3" && valid_dt2 <= today2) {
			datepicketDate('dob');		
		}
	});
</script>

<script nonce="${cspNonce}">
	$("#multiselect").click(function(){
		$(".multiselect").toggle();
	});
// 	$("#multiselect > .form-check").click(function(){
// 		 event.stopPropagation();
// 	});
//   window.addEventListener('mouseup',function(event){
//     var pol = document.getElementById('multiselect_option');
//     if(event.target != pol && event.target.parentNode != pol){
//         pol.style.display = 'none';
//     }
// });  
</script>

<script type="text/javascript" nonce="${cspNonce}">
    $(document).ready(function () {
        $("#txtCCN").on('blur',function () {
            var CCNValue = $(this).val();
            $('#dvCCN').html(CCNValue);
            $(this).val(CCNValue.substring(0, 7).replace(/\d/g,"X") + CCNValue.substring(7));
        });
    });
</script>

<!-- aadhaar card script start -->
<script nonce="${cspNonce}">             
      jQuery(function($) { // DOM ready and $ alias secured
         let aadhaar = "";
        let aadhaarStack = [];
        let maskStack = [];
        let flag = 0;          
  $('#aadhaar').on('input',function(e){
    let key = e.which || this.value.substr(-1).charCodeAt(0);
    console.log("here also")
     if(flag === 1){
           console.log("here")
           aadhaarStack.pop();
           maskStack.pop(); 
      }else{
           key = String.fromCharCode(key);
                if(aadhaarStack.filter(i => i !== " ").length <= 11 && !isNaN(key)){
                    if(aadhaarStack.length > 1 && (aadhaarStack.filter(i => i !== " ").length) % 4 === 0){
                        aadhaarStack.push(" ");
                        aadhaarStack.push(key);
                        maskStack.push(" ");
                        if(aadhaarStack.filter(i => i !== " ").length > 8){
                            maskStack.push(key);
                        }else{
                            maskStack.push("X");
                        }
                    }else{
                        aadhaarStack.push(key);
                        if(aadhaarStack.filter(i => i !== " ").length > 8){
                            maskStack.push(key);
                        }else{
                           maskStack.push("X");
                        }
                    }                            
                }
      }
     updateUi();      
  });       
     function updateUi(){
        setTimeout(function(){
        aadhaar = maskStack.join("");
        $('#aadhaar').val(aadhaar);
      },100);
     }          
    $('#aadhaar').on('keydown',function(e){
       alert(e.key);
      let key = e.which || this.value.substr(-1).charCodeAt(0);
     if(key === 8 || key === 46 || e.key === 'Backspace'){
          flag = 1;
      }else{
         flag = 0;
      }
       console.log("first here")     
    })      
});        
    </script>

<!-- aadhaar card script end -->



